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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class for representing <a href="http://en.wikipedia.org/wiki/Mixture_model">
 * mixture model</a> distributions.
 *
 * @param <T> Type of the mixture components.
 *
 * @since 3.1
 */
public class MixtureMultivariateRealDistribution<T extends MultivariateRealDistribution> extends AbstractMultivariateRealDistribution {

    @Conditional
    public static boolean _mut53216 = false, _mut53217 = false, _mut53218 = false, _mut53219 = false, _mut53220 = false, _mut53221 = false, _mut53222 = false, _mut53223 = false, _mut53224 = false, _mut53225 = false, _mut53226 = false, _mut53227 = false, _mut53228 = false, _mut53229 = false, _mut53230 = false, _mut53231 = false, _mut53232 = false, _mut53233 = false, _mut53234 = false, _mut53235 = false, _mut53236 = false, _mut53237 = false, _mut53238 = false, _mut53239 = false, _mut53240 = false, _mut53241 = false, _mut53242 = false, _mut53243 = false, _mut53244 = false, _mut53245 = false, _mut53246 = false, _mut53247 = false, _mut53248 = false, _mut53249 = false, _mut53250 = false, _mut53251 = false, _mut53252 = false, _mut53253 = false, _mut53254 = false, _mut53255 = false, _mut53256 = false, _mut53257 = false, _mut53258 = false, _mut53259 = false, _mut53260 = false, _mut53261 = false, _mut53262 = false, _mut53263 = false, _mut53264 = false, _mut53265 = false, _mut53266 = false, _mut53267 = false, _mut53268 = false, _mut53269 = false, _mut53270 = false, _mut53271 = false, _mut53272 = false, _mut53273 = false, _mut53274 = false, _mut53275 = false, _mut53276 = false, _mut53277 = false, _mut53278 = false, _mut53279 = false, _mut53280 = false;

    /**
     * Normalized weight of each mixture component.
     */
    private final double[] weight;

    /**
     * Mixture components.
     */
    private final List<T> distribution;

    /**
     * Creates a mixture model from a list of distributions and their
     * associated weights.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param components List of (weight, distribution) pairs from which to sample.
     */
    public MixtureMultivariateRealDistribution(List<Pair<Double, T>> components) {
        this(new Well19937c(), components);
    }

    /**
     * Creates a mixture model from a list of distributions and their
     * associated weights.
     *
     * @param rng Random number generator.
     * @param components Distributions from which to sample.
     * @throws NotPositiveException if any of the weights is negative.
     * @throws DimensionMismatchException if not all components have the same
     * number of variables.
     */
    public MixtureMultivariateRealDistribution(RandomGenerator rng, List<Pair<Double, T>> components) {
        super(rng, components.get(0).getSecond().getDimension());
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72");
        final int numComp = components.size();
        final int dim = getDimension();
        double weightSum = 0;
        for (int i = 0; ROR_less(i, numComp, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72", _mut53226, _mut53227, _mut53228, _mut53229, _mut53230); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72");
            final Pair<Double, T> comp = components.get(i);
            if (ROR_not_equals(comp.getSecond().getDimension(), dim, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72", _mut53216, _mut53217, _mut53218, _mut53219, _mut53220)) {
                throw new DimensionMismatchException(comp.getSecond().getDimension(), dim);
            }
            if (ROR_less(comp.getFirst(), 0, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72", _mut53221, _mut53222, _mut53223, _mut53224, _mut53225)) {
                throw new NotPositiveException(comp.getFirst());
            }
            weightSum += comp.getFirst();
        }
        // Check for overflow.
        if (Double.isInfinite(weightSum)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        }
        // Store each distribution and its normalized weight.
        distribution = new ArrayList<T>();
        weight = new double[numComp];
        for (int i = 0; ROR_less(i, numComp, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72", _mut53235, _mut53236, _mut53237, _mut53238, _mut53239); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72");
            final Pair<Double, T> comp = components.get(i);
            weight[i] = AOR_divide(comp.getFirst(), weightSum, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.MixtureMultivariateRealDistribution_72", _mut53231, _mut53232, _mut53233, _mut53234);
            distribution.add(comp.getSecond());
        }
    }

    /**
     * {@inheritDoc}
     */
    public double density(final double[] values) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.density_106");
        double p = 0;
        for (int i = 0; ROR_less(i, weight.length, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.density_106", _mut53244, _mut53245, _mut53246, _mut53247, _mut53248); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.density_106");
            p += AOR_multiply(weight[i], distribution.get(i).density(values), "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.density_106", _mut53240, _mut53241, _mut53242, _mut53243);
        }
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.sample_115");
        // Sampled values.
        double[] vals = null;
        // Determine which component to sample from.
        final double randomValue = random.nextDouble();
        double sum = 0;
        for (int i = 0; ROR_less(i, weight.length, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.sample_115", _mut53254, _mut53255, _mut53256, _mut53257, _mut53258); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.sample_115");
            sum += weight[i];
            if (ROR_less_equals(randomValue, sum, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.sample_115", _mut53249, _mut53250, _mut53251, _mut53252, _mut53253)) {
                // pick model i
                vals = distribution.get(i).sample();
                break;
            }
        }
        if (vals == null) {
            // the final iteration.
            vals = distribution.get(AOR_minus(weight.length, 1, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.sample_115", _mut53259, _mut53260, _mut53261, _mut53262)).sample();
        }
        return vals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reseedRandomGenerator(long seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.reseedRandomGenerator_144");
        // in order to maintain consistency between runs.
        super.reseedRandomGenerator(seed);
        for (int i = 0; ROR_less(i, distribution.size(), "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.reseedRandomGenerator_144", _mut53271, _mut53272, _mut53273, _mut53274, _mut53275); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.reseedRandomGenerator_144");
            // using the same sequence of random numbers.
            distribution.get(i).reseedRandomGenerator(AOR_plus(AOR_plus(i, 1, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.reseedRandomGenerator_144", _mut53263, _mut53264, _mut53265, _mut53266), seed, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.reseedRandomGenerator_144", _mut53267, _mut53268, _mut53269, _mut53270));
        }
    }

    /**
     * Gets the distributions that make up the mixture model.
     *
     * @return the component distributions and associated weights.
     */
    public List<Pair<Double, T>> getComponents() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.getComponents_162");
        final List<Pair<Double, T>> list = new ArrayList<Pair<Double, T>>(weight.length);
        for (int i = 0; ROR_less(i, weight.length, "org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.getComponents_162", _mut53276, _mut53277, _mut53278, _mut53279, _mut53280); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution.getComponents_162");
            list.add(new Pair<Double, T>(weight[i], distribution.get(i)));
        }
        return list;
    }
}
