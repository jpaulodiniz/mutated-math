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

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the Beta distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Beta_distribution">Beta distribution</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class BetaDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut56035 = false, _mut56036 = false, _mut56037 = false, _mut56038 = false, _mut56039 = false, _mut56040 = false, _mut56041 = false, _mut56042 = false, _mut56043 = false, _mut56044 = false, _mut56045 = false, _mut56046 = false, _mut56047 = false, _mut56048 = false, _mut56049 = false, _mut56050 = false, _mut56051 = false, _mut56052 = false, _mut56053 = false, _mut56054 = false, _mut56055 = false, _mut56056 = false, _mut56057 = false, _mut56058 = false, _mut56059 = false, _mut56060 = false, _mut56061 = false, _mut56062 = false, _mut56063 = false, _mut56064 = false, _mut56065 = false, _mut56066 = false, _mut56067 = false, _mut56068 = false, _mut56069 = false, _mut56070 = false, _mut56071 = false, _mut56072 = false, _mut56073 = false, _mut56074 = false, _mut56075 = false, _mut56076 = false, _mut56077 = false, _mut56078 = false, _mut56079 = false, _mut56080 = false, _mut56081 = false, _mut56082 = false, _mut56083 = false, _mut56084 = false, _mut56085 = false, _mut56086 = false, _mut56087 = false, _mut56088 = false, _mut56089 = false, _mut56090 = false, _mut56091 = false, _mut56092 = false, _mut56093 = false, _mut56094 = false, _mut56095 = false, _mut56096 = false, _mut56097 = false, _mut56098 = false, _mut56099 = false, _mut56100 = false, _mut56101 = false, _mut56102 = false, _mut56103 = false, _mut56104 = false, _mut56105 = false, _mut56106 = false, _mut56107 = false, _mut56108 = false, _mut56109 = false, _mut56110 = false, _mut56111 = false, _mut56112 = false, _mut56113 = false, _mut56114 = false, _mut56115 = false, _mut56116 = false, _mut56117 = false, _mut56118 = false, _mut56119 = false, _mut56120 = false, _mut56121 = false, _mut56122 = false, _mut56123 = false, _mut56124 = false, _mut56125 = false, _mut56126 = false, _mut56127 = false, _mut56128 = false, _mut56129 = false, _mut56130 = false, _mut56131 = false, _mut56132 = false, _mut56133 = false, _mut56134 = false, _mut56135 = false, _mut56136 = false, _mut56137 = false, _mut56138 = false, _mut56139 = false, _mut56140 = false, _mut56141 = false, _mut56142 = false, _mut56143 = false, _mut56144 = false, _mut56145 = false, _mut56146 = false, _mut56147 = false, _mut56148 = false, _mut56149 = false, _mut56150 = false, _mut56151 = false, _mut56152 = false, _mut56153 = false, _mut56154 = false, _mut56155 = false, _mut56156 = false, _mut56157 = false, _mut56158 = false, _mut56159 = false, _mut56160 = false, _mut56161 = false, _mut56162 = false, _mut56163 = false, _mut56164 = false, _mut56165 = false, _mut56166 = false, _mut56167 = false, _mut56168 = false, _mut56169 = false, _mut56170 = false, _mut56171 = false, _mut56172 = false, _mut56173 = false, _mut56174 = false, _mut56175 = false, _mut56176 = false, _mut56177 = false, _mut56178 = false, _mut56179 = false, _mut56180 = false, _mut56181 = false, _mut56182 = false, _mut56183 = false, _mut56184 = false, _mut56185 = false, _mut56186 = false, _mut56187 = false, _mut56188 = false, _mut56189 = false, _mut56190 = false, _mut56191 = false, _mut56192 = false, _mut56193 = false, _mut56194 = false, _mut56195 = false, _mut56196 = false, _mut56197 = false, _mut56198 = false, _mut56199 = false, _mut56200 = false, _mut56201 = false, _mut56202 = false, _mut56203 = false, _mut56204 = false, _mut56205 = false, _mut56206 = false, _mut56207 = false, _mut56208 = false, _mut56209 = false, _mut56210 = false, _mut56211 = false, _mut56212 = false, _mut56213 = false, _mut56214 = false, _mut56215 = false, _mut56216 = false, _mut56217 = false, _mut56218 = false, _mut56219 = false, _mut56220 = false, _mut56221 = false, _mut56222 = false, _mut56223 = false, _mut56224 = false, _mut56225 = false, _mut56226 = false, _mut56227 = false, _mut56228 = false, _mut56229 = false, _mut56230 = false, _mut56231 = false, _mut56232 = false, _mut56233 = false, _mut56234 = false, _mut56235 = false, _mut56236 = false, _mut56237 = false, _mut56238 = false, _mut56239 = false, _mut56240 = false, _mut56241 = false, _mut56242 = false, _mut56243 = false, _mut56244 = false, _mut56245 = false, _mut56246 = false, _mut56247 = false, _mut56248 = false, _mut56249 = false, _mut56250 = false, _mut56251 = false, _mut56252 = false, _mut56253 = false, _mut56254 = false, _mut56255 = false, _mut56256 = false, _mut56257 = false, _mut56258 = false, _mut56259 = false, _mut56260 = false, _mut56261 = false, _mut56262 = false, _mut56263 = false, _mut56264 = false, _mut56265 = false, _mut56266 = false, _mut56267 = false, _mut56268 = false, _mut56269 = false, _mut56270 = false, _mut56271 = false, _mut56272 = false, _mut56273 = false, _mut56274 = false, _mut56275 = false, _mut56276 = false, _mut56277 = false, _mut56278 = false, _mut56279 = false, _mut56280 = false, _mut56281 = false, _mut56282 = false, _mut56283 = false, _mut56284 = false, _mut56285 = false, _mut56286 = false, _mut56287 = false, _mut56288 = false, _mut56289 = false, _mut56290 = false, _mut56291 = false, _mut56292 = false, _mut56293 = false, _mut56294 = false, _mut56295 = false, _mut56296 = false, _mut56297 = false, _mut56298 = false, _mut56299 = false, _mut56300 = false, _mut56301 = false, _mut56302 = false, _mut56303 = false, _mut56304 = false, _mut56305 = false, _mut56306 = false, _mut56307 = false, _mut56308 = false, _mut56309 = false, _mut56310 = false, _mut56311 = false, _mut56312 = false, _mut56313 = false, _mut56314 = false, _mut56315 = false, _mut56316 = false, _mut56317 = false, _mut56318 = false, _mut56319 = false, _mut56320 = false, _mut56321 = false, _mut56322 = false, _mut56323 = false, _mut56324 = false, _mut56325 = false, _mut56326 = false, _mut56327 = false, _mut56328 = false, _mut56329 = false, _mut56330 = false, _mut56331 = false, _mut56332 = false, _mut56333 = false, _mut56334 = false, _mut56335 = false, _mut56336 = false, _mut56337 = false, _mut56338 = false, _mut56339 = false, _mut56340 = false, _mut56341 = false, _mut56342 = false, _mut56343 = false, _mut56344 = false, _mut56345 = false, _mut56346 = false, _mut56347 = false, _mut56348 = false, _mut56349 = false, _mut56350 = false, _mut56351 = false, _mut56352 = false, _mut56353 = false, _mut56354 = false, _mut56355 = false, _mut56356 = false, _mut56357 = false, _mut56358 = false, _mut56359 = false, _mut56360 = false, _mut56361 = false, _mut56362 = false, _mut56363 = false, _mut56364 = false, _mut56365 = false, _mut56366 = false, _mut56367 = false, _mut56368 = false, _mut56369 = false, _mut56370 = false, _mut56371 = false, _mut56372 = false, _mut56373 = false, _mut56374 = false, _mut56375 = false, _mut56376 = false, _mut56377 = false, _mut56378 = false, _mut56379 = false, _mut56380 = false, _mut56381 = false, _mut56382 = false, _mut56383 = false, _mut56384 = false, _mut56385 = false, _mut56386 = false, _mut56387 = false, _mut56388 = false, _mut56389 = false, _mut56390 = false, _mut56391 = false, _mut56392 = false, _mut56393 = false, _mut56394 = false, _mut56395 = false, _mut56396 = false, _mut56397 = false, _mut56398 = false, _mut56399 = false, _mut56400 = false, _mut56401 = false, _mut56402 = false, _mut56403 = false, _mut56404 = false, _mut56405 = false, _mut56406 = false, _mut56407 = false, _mut56408 = false, _mut56409 = false, _mut56410 = false, _mut56411 = false, _mut56412 = false, _mut56413 = false, _mut56414 = false, _mut56415 = false, _mut56416 = false, _mut56417 = false, _mut56418 = false, _mut56419 = false, _mut56420 = false, _mut56421 = false, _mut56422 = false, _mut56423 = false, _mut56424 = false, _mut56425 = false, _mut56426 = false, _mut56427 = false, _mut56428 = false, _mut56429 = false, _mut56430 = false, _mut56431 = false, _mut56432 = false, _mut56433 = false, _mut56434 = false, _mut56435 = false, _mut56436 = false, _mut56437 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -1221965979403477668L;

    /**
     * First shape parameter.
     */
    private final double alpha;

    /**
     * Second shape parameter.
     */
    private final double beta;

    /**
     * Normalizing factor used in density computations.
     * updated whenever alpha or beta are changed.
     */
    private double z;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

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
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     */
    public BetaDistribution(double alpha, double beta) {
        this(alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
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
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 2.1
     */
    public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this(new Well19937c(), alpha, beta, inverseCumAccuracy);
    }

    /**
     * Creates a &beta; distribution.
     *
     * @param rng Random number generator.
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @since 3.3
     */
    public BetaDistribution(RandomGenerator rng, double alpha, double beta) {
        this(rng, alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a &beta; distribution.
     *
     * @param rng Random number generator.
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 3.1
     */
    public BetaDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) {
        super(rng);
        this.alpha = alpha;
        this.beta = beta;
        z = Double.NaN;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the first shape parameter, {@code alpha}.
     *
     * @return the first shape parameter.
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * Access the second shape parameter, {@code beta}.
     *
     * @return the second shape parameter.
     */
    public double getBeta() {
        return beta;
    }

    /**
     * Recompute the normalization factor.
     */
    private void recomputeZ() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.recomputeZ_145");
        if (Double.isNaN(z)) {
            z = AOR_minus(AOR_plus(Gamma.logGamma(alpha), Gamma.logGamma(beta), "org.apache.commons.math3.distribution.BetaDistribution.recomputeZ_145", _mut56035, _mut56036, _mut56037, _mut56038), Gamma.logGamma(AOR_plus(alpha, beta, "org.apache.commons.math3.distribution.BetaDistribution.recomputeZ_145", _mut56039, _mut56040, _mut56041, _mut56042)), "org.apache.commons.math3.distribution.BetaDistribution.recomputeZ_145", _mut56043, _mut56044, _mut56045, _mut56046);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.density_152");
        final double logDensity = logDensity(x);
        return ROR_equals(logDensity, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.BetaDistribution.density_152", _mut56047, _mut56048, _mut56049, _mut56050, _mut56051) ? 0 : FastMath.exp(logDensity);
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.logDensity_158");
        recomputeZ();
        if ((_mut56062 ? (ROR_less(x, 0, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56052, _mut56053, _mut56054, _mut56055, _mut56056) && ROR_greater(x, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56057, _mut56058, _mut56059, _mut56060, _mut56061)) : (ROR_less(x, 0, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56052, _mut56053, _mut56054, _mut56055, _mut56056) || ROR_greater(x, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56057, _mut56058, _mut56059, _mut56060, _mut56061)))) {
            return Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(x, 0, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56063, _mut56064, _mut56065, _mut56066, _mut56067)) {
            if (ROR_less(alpha, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56102, _mut56103, _mut56104, _mut56105, _mut56106)) {
                throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, alpha, 1, false);
            }
            return Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(x, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56068, _mut56069, _mut56070, _mut56071, _mut56072)) {
            if (ROR_less(beta, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56097, _mut56098, _mut56099, _mut56100, _mut56101)) {
                throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, beta, 1, false);
            }
            return Double.NEGATIVE_INFINITY;
        } else {
            double logX = FastMath.log(x);
            double log1mX = FastMath.log1p(-x);
            return AOR_minus(AOR_plus(AOR_multiply((AOR_minus(alpha, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56073, _mut56074, _mut56075, _mut56076)), logX, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56077, _mut56078, _mut56079, _mut56080), AOR_multiply((AOR_minus(beta, 1, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56081, _mut56082, _mut56083, _mut56084)), log1mX, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56085, _mut56086, _mut56087, _mut56088), "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56089, _mut56090, _mut56091, _mut56092), z, "org.apache.commons.math3.distribution.BetaDistribution.logDensity_158", _mut56093, _mut56094, _mut56095, _mut56096);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.cumulativeProbability_181");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.BetaDistribution.cumulativeProbability_181", _mut56107, _mut56108, _mut56109, _mut56110, _mut56111)) {
            return 0;
        } else if (ROR_greater_equals(x, 1, "org.apache.commons.math3.distribution.BetaDistribution.cumulativeProbability_181", _mut56112, _mut56113, _mut56114, _mut56115, _mut56116)) {
            return 1;
        } else {
            return Beta.regularizedBeta(x, alpha, beta);
        }
    }

    /**
     * Return the absolute accuracy setting of the solver used to estimate
     * inverse cumulative probabilities.
     *
     * @return the solver absolute accuracy.
     * @since 2.1
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter
     * {@code beta}, the mean is {@code alpha / (alpha + beta)}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.getNumericalMean_209");
        final double a = getAlpha();
        return AOR_divide(a, (AOR_plus(a, getBeta(), "org.apache.commons.math3.distribution.BetaDistribution.getNumericalMean_209", _mut56117, _mut56118, _mut56119, _mut56120)), "org.apache.commons.math3.distribution.BetaDistribution.getNumericalMean_209", _mut56121, _mut56122, _mut56123, _mut56124);
    }

    /**
     * {@inheritDoc}
     *
     * For first shape parameter {@code alpha} and second shape parameter
     * {@code beta}, the variance is
     * {@code (alpha * beta) / [(alpha + beta)^2 * (alpha + beta + 1)]}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221");
        final double a = getAlpha();
        final double b = getBeta();
        final double alphabetasum = AOR_plus(a, b, "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56125, _mut56126, _mut56127, _mut56128);
        return AOR_divide((AOR_multiply(a, b, "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56129, _mut56130, _mut56131, _mut56132)), (AOR_multiply((AOR_multiply(alphabetasum, alphabetasum, "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56133, _mut56134, _mut56135, _mut56136)), (AOR_plus(alphabetasum, 1, "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56137, _mut56138, _mut56139, _mut56140)), "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56141, _mut56142, _mut56143, _mut56144)), "org.apache.commons.math3.distribution.BetaDistribution.getNumericalVariance_221", _mut56145, _mut56146, _mut56147, _mut56148);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
     */
    public double getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always 1 no matter the parameters.
     *
     * @return upper bound of the support (always 1)
     */
    public double getSupportUpperBound() {
        return 1;
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

    /**
     * {@inheritDoc}
     * <p>
     * Sampling is performed using Cheng algorithms:
     * </p>
     * <p>
     * R. C. H. Cheng, "Generating beta variates with nonintegral shape parameters.".
     *                 Communications of the ACM, 21, 317â€“322, 1978.
     * </p>
     */
    @Override
    public double sample() {
        return ChengBetaSampler.sample(random, alpha, beta);
    }

    /**
     * Utility class implementing Cheng's algorithms for beta distribution sampling.
     * <p>
     * R. C. H. Cheng, "Generating beta variates with nonintegral shape parameters.".
     *                 Communications of the ACM, 21, 317â€“322, 1978.
     * </p>
     * @since 3.6
     */
    private static final class ChengBetaSampler {

        /**
         * Returns one sample using Cheng's sampling algorithm.
         * @param random random generator to use
         * @param alpha distribution first shape parameter
         * @param beta distribution second shape parameter
         * @return sampled value
         */
        static double sample(RandomGenerator random, final double alpha, final double beta) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.sample_302");
            final double a = FastMath.min(alpha, beta);
            final double b = FastMath.max(alpha, beta);
            if (ROR_greater(a, 1, "org.apache.commons.math3.distribution.BetaDistribution.sample_302", _mut56149, _mut56150, _mut56151, _mut56152, _mut56153)) {
                return algorithmBB(random, alpha, a, b);
            } else {
                return algorithmBC(random, alpha, b, a);
            }
        }

        /**
         * Returns one sample using Cheng's BB algorithm, when both &alpha; and &beta; are greater than 1.
         * @param random random generator to use
         * @param a0 distribution first shape parameter (&alpha;)
         * @param a min(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @param b max(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @return sampled value
         */
        private static double algorithmBB(RandomGenerator random, final double a0, final double a, final double b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321");
            final double alpha = AOR_plus(a, b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56154, _mut56155, _mut56156, _mut56157);
            final double beta = FastMath.sqrt(AOR_divide((AOR_minus(alpha, 2., "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56158, _mut56159, _mut56160, _mut56161)), (AOR_minus(AOR_multiply(AOR_multiply(2., a, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56162, _mut56163, _mut56164, _mut56165), b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56166, _mut56167, _mut56168, _mut56169), alpha, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56170, _mut56171, _mut56172, _mut56173)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56174, _mut56175, _mut56176, _mut56177));
            final double gamma = AOR_plus(a, AOR_divide(1., beta, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56178, _mut56179, _mut56180, _mut56181), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56182, _mut56183, _mut56184, _mut56185);
            double r;
            double w;
            double t;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321");
                final double u1 = random.nextDouble();
                final double u2 = random.nextDouble();
                final double v = AOR_multiply(beta, (AOR_minus(FastMath.log(u1), FastMath.log1p(-u1), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56186, _mut56187, _mut56188, _mut56189)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56190, _mut56191, _mut56192, _mut56193);
                w = AOR_multiply(a, FastMath.exp(v), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56194, _mut56195, _mut56196, _mut56197);
                final double z = AOR_multiply(AOR_multiply(u1, u1, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56198, _mut56199, _mut56200, _mut56201), u2, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56202, _mut56203, _mut56204, _mut56205);
                r = AOR_minus(AOR_multiply(gamma, v, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56206, _mut56207, _mut56208, _mut56209), 1.3862944, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56210, _mut56211, _mut56212, _mut56213);
                final double s = AOR_minus(AOR_plus(a, r, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56214, _mut56215, _mut56216, _mut56217), w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56218, _mut56219, _mut56220, _mut56221);
                if (ROR_greater_equals(AOR_plus(s, 2.609438, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56222, _mut56223, _mut56224, _mut56225), AOR_multiply(5, z, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56226, _mut56227, _mut56228, _mut56229), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56230, _mut56231, _mut56232, _mut56233, _mut56234)) {
                    break;
                }
                t = FastMath.log(z);
                if (ROR_greater_equals(s, t, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56235, _mut56236, _mut56237, _mut56238, _mut56239)) {
                    break;
                }
            } while (ROR_less(AOR_plus(r, AOR_multiply(alpha, (AOR_minus(FastMath.log(alpha), FastMath.log(AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56240, _mut56241, _mut56242, _mut56243)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56244, _mut56245, _mut56246, _mut56247)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56248, _mut56249, _mut56250, _mut56251), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56252, _mut56253, _mut56254, _mut56255), t, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56256, _mut56257, _mut56258, _mut56259, _mut56260));
            w = FastMath.min(w, Double.MAX_VALUE);
            return Precision.equals(a, a0) ? AOR_divide(w, (AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56269, _mut56270, _mut56271, _mut56272)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56273, _mut56274, _mut56275, _mut56276) : AOR_divide(b, (AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56261, _mut56262, _mut56263, _mut56264)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBB_321", _mut56265, _mut56266, _mut56267, _mut56268);
        }

        /**
         * Returns one sample using Cheng's BC algorithm, when at least one of &alpha; and &beta; is smaller than 1.
         * @param random random generator to use
         * @param a0 distribution first shape parameter (&alpha;)
         * @param a max(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @param b min(&alpha;, &beta;) where &alpha;, &beta; are the two distribution shape parameters
         * @return sampled value
         */
        private static double algorithmBC(RandomGenerator random, final double a0, final double a, final double b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362");
            final double alpha = AOR_plus(a, b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56277, _mut56278, _mut56279, _mut56280);
            final double beta = AOR_divide(1., b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56281, _mut56282, _mut56283, _mut56284);
            final double delta = AOR_minus(AOR_plus(1., a, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56285, _mut56286, _mut56287, _mut56288), b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56289, _mut56290, _mut56291, _mut56292);
            final double k1 = AOR_divide(AOR_multiply(delta, (AOR_plus(0.0138889, AOR_multiply(0.0416667, b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56293, _mut56294, _mut56295, _mut56296), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56297, _mut56298, _mut56299, _mut56300)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56301, _mut56302, _mut56303, _mut56304), (AOR_minus(AOR_multiply(a, beta, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56305, _mut56306, _mut56307, _mut56308), 0.777778, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56309, _mut56310, _mut56311, _mut56312)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56313, _mut56314, _mut56315, _mut56316);
            final double k2 = AOR_plus(0.25, AOR_multiply((AOR_plus(0.5, AOR_divide(0.25, delta, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56317, _mut56318, _mut56319, _mut56320), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56321, _mut56322, _mut56323, _mut56324)), b, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56325, _mut56326, _mut56327, _mut56328), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56329, _mut56330, _mut56331, _mut56332);
            double w;
            for (; ; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362");
                final double u1 = random.nextDouble();
                final double u2 = random.nextDouble();
                final double y = AOR_multiply(u1, u2, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56333, _mut56334, _mut56335, _mut56336);
                final double z = AOR_multiply(u1, y, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56337, _mut56338, _mut56339, _mut56340);
                if (ROR_less(u1, 0.5, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56341, _mut56342, _mut56343, _mut56344, _mut56345)) {
                    if (ROR_greater_equals(AOR_minus(AOR_plus(AOR_multiply(0.25, u2, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56368, _mut56369, _mut56370, _mut56371), z, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56372, _mut56373, _mut56374, _mut56375), y, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56376, _mut56377, _mut56378, _mut56379), k1, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56380, _mut56381, _mut56382, _mut56383, _mut56384)) {
                        continue;
                    }
                } else {
                    if (ROR_less_equals(z, 0.25, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56346, _mut56347, _mut56348, _mut56349, _mut56350)) {
                        final double v = AOR_multiply(beta, (AOR_minus(FastMath.log(u1), FastMath.log1p(-u1), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56351, _mut56352, _mut56353, _mut56354)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56355, _mut56356, _mut56357, _mut56358);
                        w = AOR_multiply(a, FastMath.exp(v), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56359, _mut56360, _mut56361, _mut56362);
                        break;
                    }
                    if (ROR_greater_equals(z, k2, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56363, _mut56364, _mut56365, _mut56366, _mut56367)) {
                        continue;
                    }
                }
                final double v = AOR_multiply(beta, (AOR_minus(FastMath.log(u1), FastMath.log1p(-u1), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56385, _mut56386, _mut56387, _mut56388)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56389, _mut56390, _mut56391, _mut56392);
                w = AOR_multiply(a, FastMath.exp(v), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56393, _mut56394, _mut56395, _mut56396);
                if (ROR_greater_equals(AOR_minus(AOR_multiply(alpha, (AOR_plus(AOR_minus(FastMath.log(alpha), FastMath.log(AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56397, _mut56398, _mut56399, _mut56400)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56401, _mut56402, _mut56403, _mut56404), v, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56405, _mut56406, _mut56407, _mut56408)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56409, _mut56410, _mut56411, _mut56412), 1.3862944, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56413, _mut56414, _mut56415, _mut56416), FastMath.log(z), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56417, _mut56418, _mut56419, _mut56420, _mut56421)) {
                    break;
                }
            }
            w = FastMath.min(w, Double.MAX_VALUE);
            return Precision.equals(a, a0) ? AOR_divide(w, (AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56430, _mut56431, _mut56432, _mut56433)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56434, _mut56435, _mut56436, _mut56437) : AOR_divide(b, (AOR_plus(b, w, "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56422, _mut56423, _mut56424, _mut56425)), "org.apache.commons.math3.distribution.BetaDistribution.algorithmBC_362", _mut56426, _mut56427, _mut56428, _mut56429);
        }
    }
}
