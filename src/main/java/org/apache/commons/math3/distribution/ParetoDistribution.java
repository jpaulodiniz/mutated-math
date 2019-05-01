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
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Pareto distribution.
 *
 * <p>
 * <strong>Parameters:</strong>
 * The probability distribution function of {@code X} is given by (for {@code x >= k}):
 * <pre>
 *  α * k^α / x^(α + 1)
 * </pre>
 * <p>
 * <ul>
 * <li>{@code k} is the <em>scale</em> parameter: this is the minimum possible value of {@code X},</li>
 * <li>{@code α} is the <em>shape</em> parameter: this is the Pareto index</li>
 * </ul>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Pareto_distribution">
 * Pareto distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/ParetoDistribution.html">
 * Pareto distribution (MathWorld)</a>
 *
 * @since 3.3
 */
public class ParetoDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut53780 = false, _mut53781 = false, _mut53782 = false, _mut53783 = false, _mut53784 = false, _mut53785 = false, _mut53786 = false, _mut53787 = false, _mut53788 = false, _mut53789 = false, _mut53790 = false, _mut53791 = false, _mut53792 = false, _mut53793 = false, _mut53794 = false, _mut53795 = false, _mut53796 = false, _mut53797 = false, _mut53798 = false, _mut53799 = false, _mut53800 = false, _mut53801 = false, _mut53802 = false, _mut53803 = false, _mut53804 = false, _mut53805 = false, _mut53806 = false, _mut53807 = false, _mut53808 = false, _mut53809 = false, _mut53810 = false, _mut53811 = false, _mut53812 = false, _mut53813 = false, _mut53814 = false, _mut53815 = false, _mut53816 = false, _mut53817 = false, _mut53818 = false, _mut53819 = false, _mut53820 = false, _mut53821 = false, _mut53822 = false, _mut53823 = false, _mut53824 = false, _mut53825 = false, _mut53826 = false, _mut53827 = false, _mut53828 = false, _mut53829 = false, _mut53830 = false, _mut53831 = false, _mut53832 = false, _mut53833 = false, _mut53834 = false, _mut53835 = false, _mut53836 = false, _mut53837 = false, _mut53838 = false, _mut53839 = false, _mut53840 = false, _mut53841 = false, _mut53842 = false, _mut53843 = false, _mut53844 = false, _mut53845 = false, _mut53846 = false, _mut53847 = false, _mut53848 = false, _mut53849 = false, _mut53850 = false, _mut53851 = false, _mut53852 = false, _mut53853 = false, _mut53854 = false, _mut53855 = false, _mut53856 = false, _mut53857 = false, _mut53858 = false, _mut53859 = false, _mut53860 = false, _mut53861 = false, _mut53862 = false, _mut53863 = false, _mut53864 = false, _mut53865 = false, _mut53866 = false, _mut53867 = false, _mut53868 = false, _mut53869 = false, _mut53870 = false, _mut53871 = false, _mut53872 = false, _mut53873 = false, _mut53874 = false, _mut53875 = false, _mut53876 = false, _mut53877 = false, _mut53878 = false, _mut53879 = false, _mut53880 = false, _mut53881 = false, _mut53882 = false, _mut53883 = false, _mut53884 = false, _mut53885 = false, _mut53886 = false, _mut53887 = false, _mut53888 = false, _mut53889 = false, _mut53890 = false, _mut53891 = false, _mut53892 = false, _mut53893 = false, _mut53894 = false, _mut53895 = false, _mut53896 = false, _mut53897 = false, _mut53898 = false, _mut53899 = false, _mut53900 = false, _mut53901 = false, _mut53902 = false;

    /**
     * Default inverse cumulative probability accuracy.
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20130424;

    /**
     * The scale parameter of this distribution.
     */
    private final double scale;

    /**
     * The shape parameter of this distribution.
     */
    private final double shape;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Create a Pareto distribution with a scale of {@code 1} and a shape of {@code 1}.
     */
    public ParetoDistribution() {
        this(1, 1);
    }

    /**
     * Create a Pareto distribution using the specified scale and shape.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param scale the scale parameter of this distribution
     * @param shape the shape parameter of this distribution
     * @throws NotStrictlyPositiveException if {@code scale <= 0} or {@code shape <= 0}.
     */
    public ParetoDistribution(double scale, double shape) throws NotStrictlyPositiveException {
        this(scale, shape, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a Pareto distribution using the specified scale, shape and
     * inverse cumulative distribution accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param scale the scale parameter of this distribution
     * @param shape the shape parameter of this distribution
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code scale <= 0} or {@code shape <= 0}.
     */
    public ParetoDistribution(double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), scale, shape, inverseCumAccuracy);
    }

    /**
     * Creates a Pareto distribution.
     *
     * @param rng Random number generator.
     * @param scale Scale parameter of this distribution.
     * @param shape Shape parameter of this distribution.
     * @throws NotStrictlyPositiveException if {@code scale <= 0} or {@code shape <= 0}.
     */
    public ParetoDistribution(RandomGenerator rng, double scale, double shape) throws NotStrictlyPositiveException {
        this(rng, scale, shape, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Pareto distribution.
     *
     * @param rng Random number generator.
     * @param scale Scale parameter of this distribution.
     * @param shape Shape parameter of this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code scale <= 0} or {@code shape <= 0}.
     */
    public ParetoDistribution(RandomGenerator rng, double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.ParetoDistribution_135");
        if (ROR_less_equals(scale, 0, "org.apache.commons.math3.distribution.ParetoDistribution.ParetoDistribution_135", _mut53780, _mut53781, _mut53782, _mut53783, _mut53784)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, scale);
        }
        if (ROR_less_equals(shape, 0, "org.apache.commons.math3.distribution.ParetoDistribution.ParetoDistribution_135", _mut53785, _mut53786, _mut53787, _mut53788, _mut53789)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
        }
        this.scale = scale;
        this.shape = shape;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Returns the scale parameter of this distribution.
     *
     * @return the scale parameter
     */
    public double getScale() {
        return scale;
    }

    /**
     * Returns the shape parameter of this distribution.
     *
     * @return the shape parameter
     */
    public double getShape() {
        return shape;
    }

    /**
     * {@inheritDoc}
     * <p>
     * For scale {@code k}, and shape {@code α} of this distribution, the PDF
     * is given by
     * <ul>
     * <li>{@code 0} if {@code x < k},</li>
     * <li>{@code α * k^α / x^(α + 1)} otherwise.</li>
     * </ul>
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.density_183");
        if (ROR_less(x, scale, "org.apache.commons.math3.distribution.ParetoDistribution.density_183", _mut53790, _mut53791, _mut53792, _mut53793, _mut53794)) {
            return 0;
        }
        return AOR_multiply(AOR_divide(FastMath.pow(scale, shape), FastMath.pow(x, AOR_plus(shape, 1, "org.apache.commons.math3.distribution.ParetoDistribution.density_183", _mut53795, _mut53796, _mut53797, _mut53798)), "org.apache.commons.math3.distribution.ParetoDistribution.density_183", _mut53799, _mut53800, _mut53801, _mut53802), shape, "org.apache.commons.math3.distribution.ParetoDistribution.density_183", _mut53803, _mut53804, _mut53805, _mut53806);
    }

    /**
     * {@inheritDoc}
     *
     * See documentation of {@link #density(double)} for computation details.
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194");
        if (ROR_less(x, scale, "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53807, _mut53808, _mut53809, _mut53810, _mut53811)) {
            return Double.NEGATIVE_INFINITY;
        }
        return AOR_plus(AOR_minus(AOR_multiply(FastMath.log(scale), shape, "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53812, _mut53813, _mut53814, _mut53815), AOR_multiply(FastMath.log(x), (AOR_plus(shape, 1, "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53816, _mut53817, _mut53818, _mut53819)), "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53820, _mut53821, _mut53822, _mut53823), "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53824, _mut53825, _mut53826, _mut53827), FastMath.log(shape), "org.apache.commons.math3.distribution.ParetoDistribution.logDensity_194", _mut53828, _mut53829, _mut53830, _mut53831);
    }

    /**
     * {@inheritDoc}
     * <p>
     * For scale {@code k}, and shape {@code α} of this distribution, the CDF is given by
     * <ul>
     * <li>{@code 0} if {@code x < k},</li>
     * <li>{@code 1 - (k / x)^α} otherwise.</li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.cumulativeProbability_211");
        if (ROR_less_equals(x, scale, "org.apache.commons.math3.distribution.ParetoDistribution.cumulativeProbability_211", _mut53832, _mut53833, _mut53834, _mut53835, _mut53836)) {
            return 0;
        }
        return AOR_minus(1, FastMath.pow(AOR_divide(scale, x, "org.apache.commons.math3.distribution.ParetoDistribution.cumulativeProbability_211", _mut53837, _mut53838, _mut53839, _mut53840), shape), "org.apache.commons.math3.distribution.ParetoDistribution.cumulativeProbability_211", _mut53841, _mut53842, _mut53843, _mut53844);
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated See {@link RealDistribution#cumulativeProbability(double,double)}
     */
    @Override
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
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
     * <p>
     * For scale {@code k} and shape {@code α}, the mean is given by
     * <ul>
     * <li>{@code ∞} if {@code α <= 1},</li>
     * <li>{@code α * k / (α - 1)} otherwise.</li>
     * </ul>
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.getNumericalMean_245");
        if (ROR_less_equals(shape, 1, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalMean_245", _mut53845, _mut53846, _mut53847, _mut53848, _mut53849)) {
            return Double.POSITIVE_INFINITY;
        }
        return AOR_divide(AOR_multiply(shape, scale, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalMean_245", _mut53850, _mut53851, _mut53852, _mut53853), (AOR_minus(shape, 1, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalMean_245", _mut53854, _mut53855, _mut53856, _mut53857)), "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalMean_245", _mut53858, _mut53859, _mut53860, _mut53861);
    }

    /**
     * {@inheritDoc}
     * <p>
     * For scale {@code k} and shape {@code α}, the variance is given by
     * <ul>
     * <li>{@code ∞} if {@code 1 < α <= 2},</li>
     * <li>{@code k^2 * α / ((α - 1)^2 * (α - 2))} otherwise.</li>
     * </ul>
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261");
        if (ROR_less_equals(shape, 2, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53862, _mut53863, _mut53864, _mut53865, _mut53866)) {
            return Double.POSITIVE_INFINITY;
        }
        double s = AOR_minus(shape, 1, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53867, _mut53868, _mut53869, _mut53870);
        return AOR_divide(AOR_divide(AOR_multiply(AOR_multiply(scale, scale, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53871, _mut53872, _mut53873, _mut53874), shape, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53875, _mut53876, _mut53877, _mut53878), (AOR_multiply(s, s, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53879, _mut53880, _mut53881, _mut53882)), "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53883, _mut53884, _mut53885, _mut53886), (AOR_minus(shape, 2, "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53887, _mut53888, _mut53889, _mut53890)), "org.apache.commons.math3.distribution.ParetoDistribution.getNumericalVariance_261", _mut53891, _mut53892, _mut53893, _mut53894);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The lower bound of the support is equal to the scale parameter {@code k}.
     *
     * @return lower bound of the support
     */
    public double getSupportLowerBound() {
        return scale;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The upper bound of the support is always positive infinity no matter the parameters.
     *
     * @return upper bound of the support (always {@code Double.POSITIVE_INFINITY})
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
     * <p>
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
    public double sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ParetoDistribution.sample_313");
        final double n = random.nextDouble();
        return AOR_divide(scale, FastMath.pow(n, AOR_divide(1, shape, "org.apache.commons.math3.distribution.ParetoDistribution.sample_313", _mut53895, _mut53896, _mut53897, _mut53898)), "org.apache.commons.math3.distribution.ParetoDistribution.sample_313", _mut53899, _mut53900, _mut53901, _mut53902);
    }
}
