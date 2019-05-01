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
 * Implementation of the Cauchy distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Cauchy_distribution">Cauchy distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/CauchyDistribution.html">Cauchy Distribution (MathWorld)</a>
 * @since 1.1 (changed to concrete class in 3.0)
 */
public class CauchyDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut58678 = false, _mut58679 = false, _mut58680 = false, _mut58681 = false, _mut58682 = false, _mut58683 = false, _mut58684 = false, _mut58685 = false, _mut58686 = false, _mut58687 = false, _mut58688 = false, _mut58689 = false, _mut58690 = false, _mut58691 = false, _mut58692 = false, _mut58693 = false, _mut58694 = false, _mut58695 = false, _mut58696 = false, _mut58697 = false, _mut58698 = false, _mut58699 = false, _mut58700 = false, _mut58701 = false, _mut58702 = false, _mut58703 = false, _mut58704 = false, _mut58705 = false, _mut58706 = false, _mut58707 = false, _mut58708 = false, _mut58709 = false, _mut58710 = false, _mut58711 = false, _mut58712 = false, _mut58713 = false, _mut58714 = false, _mut58715 = false, _mut58716 = false, _mut58717 = false, _mut58718 = false, _mut58719 = false, _mut58720 = false, _mut58721 = false, _mut58722 = false, _mut58723 = false, _mut58724 = false, _mut58725 = false, _mut58726 = false, _mut58727 = false, _mut58728 = false, _mut58729 = false, _mut58730 = false, _mut58731 = false, _mut58732 = false, _mut58733 = false, _mut58734 = false, _mut58735 = false, _mut58736 = false, _mut58737 = false, _mut58738 = false, _mut58739 = false, _mut58740 = false, _mut58741 = false, _mut58742 = false, _mut58743 = false, _mut58744 = false, _mut58745 = false, _mut58746 = false, _mut58747 = false, _mut58748 = false, _mut58749 = false, _mut58750 = false, _mut58751 = false, _mut58752 = false, _mut58753 = false, _mut58754 = false, _mut58755 = false, _mut58756 = false, _mut58757 = false, _mut58758 = false, _mut58759 = false, _mut58760 = false, _mut58761 = false, _mut58762 = false, _mut58763 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 8589540077390120676L;

    /**
     * The median of this distribution.
     */
    private final double median;

    /**
     * The scale of this distribution.
     */
    private final double scale;

    /**
     * Inverse cumulative probability accuracy
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Creates a Cauchy distribution with the median equal to zero and scale
     * equal to one.
     */
    public CauchyDistribution() {
        this(0, 1);
    }

    /**
     * Creates a Cauchy distribution using the given median and scale.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param median Median for this distribution.
     * @param scale Scale parameter for this distribution.
     */
    public CauchyDistribution(double median, double scale) {
        this(median, scale, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Cauchy distribution using the given median and scale.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param median Median for this distribution.
     * @param scale Scale parameter for this distribution.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code scale <= 0}.
     * @since 2.1
     */
    public CauchyDistribution(double median, double scale, double inverseCumAccuracy) {
        this(new Well19937c(), median, scale, inverseCumAccuracy);
    }

    /**
     * Creates a Cauchy distribution.
     *
     * @param rng Random number generator.
     * @param median Median for this distribution.
     * @param scale Scale parameter for this distribution.
     * @throws NotStrictlyPositiveException if {@code scale <= 0}.
     * @since 3.3
     */
    public CauchyDistribution(RandomGenerator rng, double median, double scale) {
        this(rng, median, scale, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Cauchy distribution.
     *
     * @param rng Random number generator.
     * @param median Median for this distribution.
     * @param scale Scale parameter for this distribution.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code scale <= 0}.
     * @since 3.1
     */
    public CauchyDistribution(RandomGenerator rng, double median, double scale, double inverseCumAccuracy) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.CauchyDistribution.CauchyDistribution_121");
        if (ROR_less_equals(scale, 0, "org.apache.commons.math3.distribution.CauchyDistribution.CauchyDistribution_121", _mut58678, _mut58679, _mut58680, _mut58681, _mut58682)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, scale);
        }
        this.scale = scale;
        this.median = median;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.CauchyDistribution.cumulativeProbability_135");
        return AOR_plus(0.5, (AOR_divide(FastMath.atan(AOR_divide((AOR_minus(x, median, "org.apache.commons.math3.distribution.CauchyDistribution.cumulativeProbability_135", _mut58683, _mut58684, _mut58685, _mut58686)), scale, "org.apache.commons.math3.distribution.CauchyDistribution.cumulativeProbability_135", _mut58687, _mut58688, _mut58689, _mut58690)), FastMath.PI, "org.apache.commons.math3.distribution.CauchyDistribution.cumulativeProbability_135", _mut58691, _mut58692, _mut58693, _mut58694)), "org.apache.commons.math3.distribution.CauchyDistribution.cumulativeProbability_135", _mut58695, _mut58696, _mut58697, _mut58698);
    }

    /**
     * Access the median.
     *
     * @return the median for this distribution.
     */
    public double getMedian() {
        return median;
    }

    /**
     * Access the scale parameter.
     *
     * @return the scale parameter for this distribution.
     */
    public double getScale() {
        return scale;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.CauchyDistribution.density_158");
        final double dev = AOR_minus(x, median, "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58699, _mut58700, _mut58701, _mut58702);
        return AOR_multiply((AOR_divide(1, FastMath.PI, "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58703, _mut58704, _mut58705, _mut58706)), (AOR_divide(scale, (AOR_plus(AOR_multiply(dev, dev, "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58707, _mut58708, _mut58709, _mut58710), AOR_multiply(scale, scale, "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58711, _mut58712, _mut58713, _mut58714), "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58715, _mut58716, _mut58717, _mut58718)), "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58719, _mut58720, _mut58721, _mut58722)), "org.apache.commons.math3.distribution.CauchyDistribution.density_158", _mut58723, _mut58724, _mut58725, _mut58726);
    }

    /**
     * {@inheritDoc}
     *
     * Returns {@code Double.NEGATIVE_INFINITY} when {@code p == 0}
     * and {@code Double.POSITIVE_INFINITY} when {@code p == 1}.
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169");
        double ret;
        if ((_mut58737 ? (ROR_less(p, 0, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58727, _mut58728, _mut58729, _mut58730, _mut58731) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58732, _mut58733, _mut58734, _mut58735, _mut58736)) : (ROR_less(p, 0, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58727, _mut58728, _mut58729, _mut58730, _mut58731) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58732, _mut58733, _mut58734, _mut58735, _mut58736)))) {
            throw new OutOfRangeException(p, 0, 1);
        } else if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58738, _mut58739, _mut58740, _mut58741, _mut58742)) {
            ret = Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58743, _mut58744, _mut58745, _mut58746, _mut58747)) {
            ret = Double.POSITIVE_INFINITY;
        } else {
            ret = AOR_plus(median, AOR_multiply(scale, FastMath.tan(AOR_multiply(FastMath.PI, (AOR_minus(p, .5, "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58748, _mut58749, _mut58750, _mut58751)), "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58752, _mut58753, _mut58754, _mut58755)), "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58756, _mut58757, _mut58758, _mut58759), "org.apache.commons.math3.distribution.CauchyDistribution.inverseCumulativeProbability_169", _mut58760, _mut58761, _mut58762, _mut58763);
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
     * The mean is always undefined no matter the parameters.
     *
     * @return mean (always Double.NaN)
     */
    public double getNumericalMean() {
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     *
     * The variance is always undefined no matter the parameters.
     *
     * @return variance (always Double.NaN)
     */
    public double getNumericalVariance() {
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always negative infinity no matter
     * the parameters.
     *
     * @return lower bound of the support (always Double.NEGATIVE_INFINITY)
     */
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity no matter
     * the parameters.
     *
     * @return upper bound of the support (always Double.POSITIVE_INFINITY)
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
