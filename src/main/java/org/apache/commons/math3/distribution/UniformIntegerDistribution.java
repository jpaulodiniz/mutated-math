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

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the uniform integer distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Uniform_distribution_(discrete)"
 * >Uniform distribution (discrete), at Wikipedia</a>
 *
 * @since 3.0
 */
public class UniformIntegerDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut53102 = false, _mut53103 = false, _mut53104 = false, _mut53105 = false, _mut53106 = false, _mut53107 = false, _mut53108 = false, _mut53109 = false, _mut53110 = false, _mut53111 = false, _mut53112 = false, _mut53113 = false, _mut53114 = false, _mut53115 = false, _mut53116 = false, _mut53117 = false, _mut53118 = false, _mut53119 = false, _mut53120 = false, _mut53121 = false, _mut53122 = false, _mut53123 = false, _mut53124 = false, _mut53125 = false, _mut53126 = false, _mut53127 = false, _mut53128 = false, _mut53129 = false, _mut53130 = false, _mut53131 = false, _mut53132 = false, _mut53133 = false, _mut53134 = false, _mut53135 = false, _mut53136 = false, _mut53137 = false, _mut53138 = false, _mut53139 = false, _mut53140 = false, _mut53141 = false, _mut53142 = false, _mut53143 = false, _mut53144 = false, _mut53145 = false, _mut53146 = false, _mut53147 = false, _mut53148 = false, _mut53149 = false, _mut53150 = false, _mut53151 = false, _mut53152 = false, _mut53153 = false, _mut53154 = false, _mut53155 = false, _mut53156 = false, _mut53157 = false, _mut53158 = false, _mut53159 = false, _mut53160 = false, _mut53161 = false, _mut53162 = false, _mut53163 = false, _mut53164 = false, _mut53165 = false, _mut53166 = false, _mut53167 = false, _mut53168 = false, _mut53169 = false, _mut53170 = false, _mut53171 = false, _mut53172 = false, _mut53173 = false, _mut53174 = false, _mut53175 = false, _mut53176 = false, _mut53177 = false, _mut53178 = false, _mut53179 = false, _mut53180 = false, _mut53181 = false, _mut53182 = false, _mut53183 = false, _mut53184 = false, _mut53185 = false, _mut53186 = false, _mut53187 = false, _mut53188 = false, _mut53189 = false, _mut53190 = false, _mut53191 = false, _mut53192 = false, _mut53193 = false, _mut53194 = false, _mut53195 = false, _mut53196 = false, _mut53197 = false, _mut53198 = false, _mut53199 = false, _mut53200 = false, _mut53201 = false, _mut53202 = false, _mut53203 = false, _mut53204 = false, _mut53205 = false, _mut53206 = false, _mut53207 = false, _mut53208 = false, _mut53209 = false, _mut53210 = false, _mut53211 = false, _mut53212 = false, _mut53213 = false, _mut53214 = false, _mut53215 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20120109L;

    /**
     * Lower bound (inclusive) of this distribution.
     */
    private final int lower;

    /**
     * Upper bound (inclusive) of this distribution.
     */
    private final int upper;

    /**
     * Creates a new uniform integer distribution using the given lower and
     * upper bounds (both inclusive).
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param lower Lower bound (inclusive) of this distribution.
     * @param upper Upper bound (inclusive) of this distribution.
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     */
    public UniformIntegerDistribution(int lower, int upper) throws NumberIsTooLargeException {
        this(new Well19937c(), lower, upper);
    }

    /**
     * Creates a new uniform integer distribution using the given lower and
     * upper bounds (both inclusive).
     *
     * @param rng Random number generator.
     * @param lower Lower bound (inclusive) of this distribution.
     * @param upper Upper bound (inclusive) of this distribution.
     * @throws NumberIsTooLargeException if {@code lower > upper}.
     * @since 3.1
     */
    public UniformIntegerDistribution(RandomGenerator rng, int lower, int upper) throws NumberIsTooLargeException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.UniformIntegerDistribution_71");
        if (ROR_greater(lower, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.UniformIntegerDistribution_71", _mut53102, _mut53103, _mut53104, _mut53105, _mut53106)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, lower, upper, true);
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87");
        if ((_mut53117 ? (ROR_less(x, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53107, _mut53108, _mut53109, _mut53110, _mut53111) && ROR_greater(x, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53112, _mut53113, _mut53114, _mut53115, _mut53116)) : (ROR_less(x, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53107, _mut53108, _mut53109, _mut53110, _mut53111) || ROR_greater(x, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53112, _mut53113, _mut53114, _mut53115, _mut53116)))) {
            return 0;
        }
        return AOR_divide(1.0, (AOR_plus(AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53118, _mut53119, _mut53120, _mut53121), 1, "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53122, _mut53123, _mut53124, _mut53125)), "org.apache.commons.math3.distribution.UniformIntegerDistribution.probability_87", _mut53126, _mut53127, _mut53128, _mut53129);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95");
        if (ROR_less(x, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53130, _mut53131, _mut53132, _mut53133, _mut53134)) {
            return 0;
        }
        if (ROR_greater(x, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53135, _mut53136, _mut53137, _mut53138, _mut53139)) {
            return 1;
        }
        return AOR_divide((AOR_plus(AOR_minus(x, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53140, _mut53141, _mut53142, _mut53143), 1.0, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53144, _mut53145, _mut53146, _mut53147)), (AOR_plus(AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53148, _mut53149, _mut53150, _mut53151), 1.0, "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53152, _mut53153, _mut53154, _mut53155)), "org.apache.commons.math3.distribution.UniformIntegerDistribution.cumulativeProbability_95", _mut53156, _mut53157, _mut53158, _mut53159);
    }

    /**
     * {@inheritDoc}
     *
     * For lower bound {@code lower} and upper bound {@code upper}, the mean is
     * {@code 0.5 * (lower + upper)}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalMean_111");
        return AOR_multiply(0.5, (AOR_plus(lower, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalMean_111", _mut53160, _mut53161, _mut53162, _mut53163)), "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalMean_111", _mut53164, _mut53165, _mut53166, _mut53167);
    }

    /**
     * {@inheritDoc}
     *
     * For lower bound {@code lower} and upper bound {@code upper}, and
     * {@code n = upper - lower + 1}, the variance is {@code (n^2 - 1) / 12}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121");
        double n = AOR_plus(AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121", _mut53168, _mut53169, _mut53170, _mut53171), 1, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121", _mut53172, _mut53173, _mut53174, _mut53175);
        return AOR_divide((AOR_minus(AOR_multiply(n, n, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121", _mut53176, _mut53177, _mut53178, _mut53179), 1, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121", _mut53180, _mut53181, _mut53182, _mut53183)), 12.0, "org.apache.commons.math3.distribution.UniformIntegerDistribution.getNumericalVariance_121", _mut53184, _mut53185, _mut53186, _mut53187);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is equal to the lower bound parameter
     * of the distribution.
     *
     * @return lower bound of the support
     */
    public int getSupportLowerBound() {
        return lower;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is equal to the upper bound parameter
     * of the distribution.
     *
     * @return upper bound of the support
     */
    public int getSupportUpperBound() {
        return upper;
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
     */
    @Override
    public int sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162");
        final int max = AOR_plus((AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53188, _mut53189, _mut53190, _mut53191)), 1, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53192, _mut53193, _mut53194, _mut53195);
        if (ROR_less_equals(max, 0, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53196, _mut53197, _mut53198, _mut53199, _mut53200)) {
            // we use a simple rejection method.
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162");
                final int r = random.nextInt();
                if ((_mut53215 ? (ROR_greater_equals(r, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53205, _mut53206, _mut53207, _mut53208, _mut53209) || ROR_less_equals(r, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53210, _mut53211, _mut53212, _mut53213, _mut53214)) : (ROR_greater_equals(r, lower, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53205, _mut53206, _mut53207, _mut53208, _mut53209) && ROR_less_equals(r, upper, "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53210, _mut53211, _mut53212, _mut53213, _mut53214)))) {
                    return r;
                }
            }
        } else {
            // We can shift the range and directly generate a positive int.
            return AOR_plus(lower, random.nextInt(max), "org.apache.commons.math3.distribution.UniformIntegerDistribution.sample_162", _mut53201, _mut53202, _mut53203, _mut53204);
        }
    }
}
