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
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the F-distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/F-distribution">F-distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/F-Distribution.html">F-distribution (MathWorld)</a>
 */
public class FDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut57513 = false, _mut57514 = false, _mut57515 = false, _mut57516 = false, _mut57517 = false, _mut57518 = false, _mut57519 = false, _mut57520 = false, _mut57521 = false, _mut57522 = false, _mut57523 = false, _mut57524 = false, _mut57525 = false, _mut57526 = false, _mut57527 = false, _mut57528 = false, _mut57529 = false, _mut57530 = false, _mut57531 = false, _mut57532 = false, _mut57533 = false, _mut57534 = false, _mut57535 = false, _mut57536 = false, _mut57537 = false, _mut57538 = false, _mut57539 = false, _mut57540 = false, _mut57541 = false, _mut57542 = false, _mut57543 = false, _mut57544 = false, _mut57545 = false, _mut57546 = false, _mut57547 = false, _mut57548 = false, _mut57549 = false, _mut57550 = false, _mut57551 = false, _mut57552 = false, _mut57553 = false, _mut57554 = false, _mut57555 = false, _mut57556 = false, _mut57557 = false, _mut57558 = false, _mut57559 = false, _mut57560 = false, _mut57561 = false, _mut57562 = false, _mut57563 = false, _mut57564 = false, _mut57565 = false, _mut57566 = false, _mut57567 = false, _mut57568 = false, _mut57569 = false, _mut57570 = false, _mut57571 = false, _mut57572 = false, _mut57573 = false, _mut57574 = false, _mut57575 = false, _mut57576 = false, _mut57577 = false, _mut57578 = false, _mut57579 = false, _mut57580 = false, _mut57581 = false, _mut57582 = false, _mut57583 = false, _mut57584 = false, _mut57585 = false, _mut57586 = false, _mut57587 = false, _mut57588 = false, _mut57589 = false, _mut57590 = false, _mut57591 = false, _mut57592 = false, _mut57593 = false, _mut57594 = false, _mut57595 = false, _mut57596 = false, _mut57597 = false, _mut57598 = false, _mut57599 = false, _mut57600 = false, _mut57601 = false, _mut57602 = false, _mut57603 = false, _mut57604 = false, _mut57605 = false, _mut57606 = false, _mut57607 = false, _mut57608 = false, _mut57609 = false, _mut57610 = false, _mut57611 = false, _mut57612 = false, _mut57613 = false, _mut57614 = false, _mut57615 = false, _mut57616 = false, _mut57617 = false, _mut57618 = false, _mut57619 = false, _mut57620 = false, _mut57621 = false, _mut57622 = false, _mut57623 = false, _mut57624 = false, _mut57625 = false, _mut57626 = false, _mut57627 = false, _mut57628 = false, _mut57629 = false, _mut57630 = false, _mut57631 = false, _mut57632 = false, _mut57633 = false, _mut57634 = false, _mut57635 = false, _mut57636 = false, _mut57637 = false, _mut57638 = false, _mut57639 = false, _mut57640 = false, _mut57641 = false, _mut57642 = false, _mut57643 = false, _mut57644 = false, _mut57645 = false, _mut57646 = false, _mut57647 = false, _mut57648 = false, _mut57649 = false, _mut57650 = false, _mut57651 = false, _mut57652 = false, _mut57653 = false, _mut57654 = false, _mut57655 = false, _mut57656 = false, _mut57657 = false, _mut57658 = false, _mut57659 = false, _mut57660 = false, _mut57661 = false, _mut57662 = false, _mut57663 = false, _mut57664 = false, _mut57665 = false, _mut57666 = false, _mut57667 = false, _mut57668 = false, _mut57669 = false, _mut57670 = false, _mut57671 = false, _mut57672 = false, _mut57673 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -8516354193418641566L;

    /**
     * The numerator degrees of freedom.
     */
    private final double numeratorDegreesOfFreedom;

    /**
     * The numerator degrees of freedom.
     */
    private final double denominatorDegreesOfFreedom;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * Creates an F distribution using the given degrees of freedom.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param numeratorDegreesOfFreedom Numerator degrees of freedom.
     * @param denominatorDegreesOfFreedom Denominator degrees of freedom.
     * @throws NotStrictlyPositiveException if
     * {@code numeratorDegreesOfFreedom <= 0} or
     * {@code denominatorDegreesOfFreedom <= 0}.
     */
    public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom) throws NotStrictlyPositiveException {
        this(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates an F distribution using the given degrees of freedom
     * and inverse cumulative probability accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param numeratorDegreesOfFreedom Numerator degrees of freedom.
     * @param denominatorDegreesOfFreedom Denominator degrees of freedom.
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates.
     * @throws NotStrictlyPositiveException if
     * {@code numeratorDegreesOfFreedom <= 0} or
     * {@code denominatorDegreesOfFreedom <= 0}.
     * @since 2.1
     */
    public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, inverseCumAccuracy);
    }

    /**
     * Creates an F distribution.
     *
     * @param rng Random number generator.
     * @param numeratorDegreesOfFreedom Numerator degrees of freedom.
     * @param denominatorDegreesOfFreedom Denominator degrees of freedom.
     * @throws NotStrictlyPositiveException if {@code numeratorDegreesOfFreedom <= 0} or
     * {@code denominatorDegreesOfFreedom <= 0}.
     * @since 3.3
     */
    public FDistribution(RandomGenerator rng, double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom) throws NotStrictlyPositiveException {
        this(rng, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates an F distribution.
     *
     * @param rng Random number generator.
     * @param numeratorDegreesOfFreedom Numerator degrees of freedom.
     * @param denominatorDegreesOfFreedom Denominator degrees of freedom.
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates.
     * @throws NotStrictlyPositiveException if {@code numeratorDegreesOfFreedom <= 0} or
     * {@code denominatorDegreesOfFreedom <= 0}.
     * @since 3.1
     */
    public FDistribution(RandomGenerator rng, double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.FDistribution.FDistribution_132");
        if (ROR_less_equals(numeratorDegreesOfFreedom, 0, "org.apache.commons.math3.distribution.FDistribution.FDistribution_132", _mut57513, _mut57514, _mut57515, _mut57516, _mut57517)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, numeratorDegreesOfFreedom);
        }
        if (ROR_less_equals(denominatorDegreesOfFreedom, 0, "org.apache.commons.math3.distribution.FDistribution.FDistribution_132", _mut57518, _mut57519, _mut57520, _mut57521, _mut57522)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, denominatorDegreesOfFreedom);
        }
        this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
        this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * @since 2.1
     */
    public double density(double x) {
        return FastMath.exp(logDensity(x));
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.FDistribution.logDensity_162");
        final double nhalf = AOR_divide(numeratorDegreesOfFreedom, 2, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57523, _mut57524, _mut57525, _mut57526);
        final double mhalf = AOR_divide(denominatorDegreesOfFreedom, 2, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57527, _mut57528, _mut57529, _mut57530);
        final double logx = FastMath.log(x);
        final double logn = FastMath.log(numeratorDegreesOfFreedom);
        final double logm = FastMath.log(denominatorDegreesOfFreedom);
        final double lognxm = FastMath.log(AOR_plus(AOR_multiply(numeratorDegreesOfFreedom, x, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57531, _mut57532, _mut57533, _mut57534), denominatorDegreesOfFreedom, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57535, _mut57536, _mut57537, _mut57538));
        return AOR_minus(AOR_minus(AOR_minus(AOR_plus(AOR_minus(AOR_plus(AOR_multiply(nhalf, logn, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57539, _mut57540, _mut57541, _mut57542), AOR_multiply(nhalf, logx, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57543, _mut57544, _mut57545, _mut57546), "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57547, _mut57548, _mut57549, _mut57550), logx, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57551, _mut57552, _mut57553, _mut57554), AOR_multiply(mhalf, logm, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57555, _mut57556, _mut57557, _mut57558), "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57559, _mut57560, _mut57561, _mut57562), AOR_multiply(nhalf, lognxm, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57563, _mut57564, _mut57565, _mut57566), "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57567, _mut57568, _mut57569, _mut57570), AOR_multiply(mhalf, lognxm, "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57571, _mut57572, _mut57573, _mut57574), "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57575, _mut57576, _mut57577, _mut57578), Beta.logBeta(nhalf, mhalf), "org.apache.commons.math3.distribution.FDistribution.logDensity_162", _mut57579, _mut57580, _mut57581, _mut57582);
    }

    /**
     * {@inheritDoc}
     *
     * The implementation of this method is based on
     * <ul>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/F-Distribution.html">
     *   F-Distribution</a>, equation (4).
     *  </li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187");
        double ret;
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57583, _mut57584, _mut57585, _mut57586, _mut57587)) {
            ret = 0;
        } else {
            double n = numeratorDegreesOfFreedom;
            double m = denominatorDegreesOfFreedom;
            ret = Beta.regularizedBeta(AOR_divide((AOR_multiply(n, x, "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57588, _mut57589, _mut57590, _mut57591)), (AOR_plus(m, AOR_multiply(n, x, "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57592, _mut57593, _mut57594, _mut57595), "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57596, _mut57597, _mut57598, _mut57599)), "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57600, _mut57601, _mut57602, _mut57603), AOR_multiply(0.5, n, "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57604, _mut57605, _mut57606, _mut57607), AOR_multiply(0.5, m, "org.apache.commons.math3.distribution.FDistribution.cumulativeProbability_187", _mut57608, _mut57609, _mut57610, _mut57611));
        }
        return ret;
    }

    /**
     * Access the numerator degrees of freedom.
     *
     * @return the numerator degrees of freedom.
     */
    public double getNumeratorDegreesOfFreedom() {
        return numeratorDegreesOfFreedom;
    }

    /**
     * Access the denominator degrees of freedom.
     *
     * @return the denominator degrees of freedom.
     */
    public double getDenominatorDegreesOfFreedom() {
        return denominatorDegreesOfFreedom;
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
     * For denominator degrees of freedom parameter {@code b}, the mean is
     * <ul>
     *  <li>if {@code b > 2} then {@code b / (b - 2)},</li>
     *  <li>else undefined ({@code Double.NaN}).
     * </ul>
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.FDistribution.getNumericalMean_235");
        final double denominatorDF = getDenominatorDegreesOfFreedom();
        if (ROR_greater(denominatorDF, 2, "org.apache.commons.math3.distribution.FDistribution.getNumericalMean_235", _mut57612, _mut57613, _mut57614, _mut57615, _mut57616)) {
            return AOR_divide(denominatorDF, (AOR_minus(denominatorDF, 2, "org.apache.commons.math3.distribution.FDistribution.getNumericalMean_235", _mut57617, _mut57618, _mut57619, _mut57620)), "org.apache.commons.math3.distribution.FDistribution.getNumericalMean_235", _mut57621, _mut57622, _mut57623, _mut57624);
        }
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     *
     * For numerator degrees of freedom parameter {@code a} and denominator
     * degrees of freedom parameter {@code b}, the variance is
     * <ul>
     *  <li>
     *    if {@code b > 4} then
     *    {@code [2 * b^2 * (a + b - 2)] / [a * (b - 2)^2 * (b - 4)]},
     *  </li>
     *  <li>else undefined ({@code Double.NaN}).
     * </ul>
     */
    public double getNumericalVariance() {
        if (!numericalVarianceIsCalculated) {
            numericalVariance = calculateNumericalVariance();
            numericalVarianceIsCalculated = true;
        }
        return numericalVariance;
    }

    /**
     * used by {@link #getNumericalVariance()}
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271");
        final double denominatorDF = getDenominatorDegreesOfFreedom();
        if (ROR_greater(denominatorDF, 4, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57625, _mut57626, _mut57627, _mut57628, _mut57629)) {
            final double numeratorDF = getNumeratorDegreesOfFreedom();
            final double denomDFMinusTwo = AOR_minus(denominatorDF, 2, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57630, _mut57631, _mut57632, _mut57633);
            return AOR_divide((AOR_multiply(AOR_multiply(2, (AOR_multiply(denominatorDF, denominatorDF, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57634, _mut57635, _mut57636, _mut57637)), "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57638, _mut57639, _mut57640, _mut57641), (AOR_minus(AOR_plus(numeratorDF, denominatorDF, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57642, _mut57643, _mut57644, _mut57645), 2, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57646, _mut57647, _mut57648, _mut57649)), "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57650, _mut57651, _mut57652, _mut57653)), ((AOR_multiply(AOR_multiply(numeratorDF, (AOR_multiply(denomDFMinusTwo, denomDFMinusTwo, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57654, _mut57655, _mut57656, _mut57657)), "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57658, _mut57659, _mut57660, _mut57661), (AOR_minus(denominatorDF, 4, "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57662, _mut57663, _mut57664, _mut57665)), "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57666, _mut57667, _mut57668, _mut57669))), "org.apache.commons.math3.distribution.FDistribution.calculateNumericalVariance_271", _mut57670, _mut57671, _mut57672, _mut57673);
        }
        return Double.NaN;
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
     * The upper bound of the support is always positive infinity
     * no matter the parameters.
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
