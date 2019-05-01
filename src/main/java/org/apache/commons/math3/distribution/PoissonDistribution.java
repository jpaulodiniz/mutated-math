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
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Poisson distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Poisson_distribution">Poisson distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/PoissonDistribution.html">Poisson distribution (MathWorld)</a>
 */
public class PoissonDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut56438 = false, _mut56439 = false, _mut56440 = false, _mut56441 = false, _mut56442 = false, _mut56443 = false, _mut56444 = false, _mut56445 = false, _mut56446 = false, _mut56447 = false, _mut56448 = false, _mut56449 = false, _mut56450 = false, _mut56451 = false, _mut56452 = false, _mut56453 = false, _mut56454 = false, _mut56455 = false, _mut56456 = false, _mut56457 = false, _mut56458 = false, _mut56459 = false, _mut56460 = false, _mut56461 = false, _mut56462 = false, _mut56463 = false, _mut56464 = false, _mut56465 = false, _mut56466 = false, _mut56467 = false, _mut56468 = false, _mut56469 = false, _mut56470 = false, _mut56471 = false, _mut56472 = false, _mut56473 = false, _mut56474 = false, _mut56475 = false, _mut56476 = false, _mut56477 = false, _mut56478 = false, _mut56479 = false, _mut56480 = false, _mut56481 = false, _mut56482 = false, _mut56483 = false, _mut56484 = false, _mut56485 = false, _mut56486 = false, _mut56487 = false, _mut56488 = false, _mut56489 = false, _mut56490 = false, _mut56491 = false, _mut56492 = false, _mut56493 = false, _mut56494 = false, _mut56495 = false, _mut56496 = false, _mut56497 = false, _mut56498 = false, _mut56499 = false, _mut56500 = false, _mut56501 = false, _mut56502 = false, _mut56503 = false, _mut56504 = false, _mut56505 = false, _mut56506 = false, _mut56507 = false, _mut56508 = false, _mut56509 = false, _mut56510 = false, _mut56511 = false, _mut56512 = false, _mut56513 = false, _mut56514 = false, _mut56515 = false, _mut56516 = false, _mut56517 = false, _mut56518 = false, _mut56519 = false, _mut56520 = false, _mut56521 = false, _mut56522 = false, _mut56523 = false, _mut56524 = false, _mut56525 = false, _mut56526 = false, _mut56527 = false, _mut56528 = false, _mut56529 = false, _mut56530 = false, _mut56531 = false, _mut56532 = false, _mut56533 = false, _mut56534 = false, _mut56535 = false, _mut56536 = false, _mut56537 = false, _mut56538 = false, _mut56539 = false, _mut56540 = false, _mut56541 = false, _mut56542 = false, _mut56543 = false, _mut56544 = false, _mut56545 = false, _mut56546 = false, _mut56547 = false, _mut56548 = false, _mut56549 = false, _mut56550 = false, _mut56551 = false, _mut56552 = false, _mut56553 = false, _mut56554 = false, _mut56555 = false, _mut56556 = false, _mut56557 = false, _mut56558 = false, _mut56559 = false, _mut56560 = false, _mut56561 = false, _mut56562 = false, _mut56563 = false, _mut56564 = false, _mut56565 = false, _mut56566 = false, _mut56567 = false, _mut56568 = false, _mut56569 = false, _mut56570 = false, _mut56571 = false, _mut56572 = false, _mut56573 = false, _mut56574 = false, _mut56575 = false, _mut56576 = false, _mut56577 = false, _mut56578 = false, _mut56579 = false, _mut56580 = false, _mut56581 = false, _mut56582 = false, _mut56583 = false, _mut56584 = false, _mut56585 = false, _mut56586 = false, _mut56587 = false, _mut56588 = false, _mut56589 = false, _mut56590 = false, _mut56591 = false, _mut56592 = false, _mut56593 = false, _mut56594 = false, _mut56595 = false, _mut56596 = false, _mut56597 = false, _mut56598 = false, _mut56599 = false, _mut56600 = false, _mut56601 = false, _mut56602 = false, _mut56603 = false, _mut56604 = false, _mut56605 = false, _mut56606 = false, _mut56607 = false, _mut56608 = false, _mut56609 = false, _mut56610 = false, _mut56611 = false, _mut56612 = false, _mut56613 = false, _mut56614 = false, _mut56615 = false, _mut56616 = false, _mut56617 = false, _mut56618 = false, _mut56619 = false, _mut56620 = false, _mut56621 = false, _mut56622 = false, _mut56623 = false, _mut56624 = false, _mut56625 = false, _mut56626 = false, _mut56627 = false, _mut56628 = false, _mut56629 = false, _mut56630 = false, _mut56631 = false, _mut56632 = false, _mut56633 = false, _mut56634 = false, _mut56635 = false, _mut56636 = false, _mut56637 = false, _mut56638 = false, _mut56639 = false, _mut56640 = false, _mut56641 = false, _mut56642 = false, _mut56643 = false, _mut56644 = false, _mut56645 = false, _mut56646 = false, _mut56647 = false, _mut56648 = false, _mut56649 = false, _mut56650 = false, _mut56651 = false, _mut56652 = false, _mut56653 = false, _mut56654 = false, _mut56655 = false, _mut56656 = false, _mut56657 = false, _mut56658 = false, _mut56659 = false, _mut56660 = false, _mut56661 = false, _mut56662 = false, _mut56663 = false, _mut56664 = false, _mut56665 = false, _mut56666 = false, _mut56667 = false, _mut56668 = false, _mut56669 = false, _mut56670 = false, _mut56671 = false, _mut56672 = false, _mut56673 = false, _mut56674 = false, _mut56675 = false, _mut56676 = false, _mut56677 = false, _mut56678 = false, _mut56679 = false, _mut56680 = false, _mut56681 = false, _mut56682 = false, _mut56683 = false, _mut56684 = false, _mut56685 = false, _mut56686 = false, _mut56687 = false, _mut56688 = false, _mut56689 = false, _mut56690 = false, _mut56691 = false, _mut56692 = false, _mut56693 = false, _mut56694 = false, _mut56695 = false, _mut56696 = false, _mut56697 = false, _mut56698 = false, _mut56699 = false, _mut56700 = false, _mut56701 = false, _mut56702 = false, _mut56703 = false, _mut56704 = false, _mut56705 = false, _mut56706 = false, _mut56707 = false, _mut56708 = false, _mut56709 = false, _mut56710 = false, _mut56711 = false, _mut56712 = false, _mut56713 = false, _mut56714 = false, _mut56715 = false, _mut56716 = false, _mut56717 = false, _mut56718 = false, _mut56719 = false, _mut56720 = false, _mut56721 = false, _mut56722 = false, _mut56723 = false, _mut56724 = false, _mut56725 = false, _mut56726 = false, _mut56727 = false, _mut56728 = false, _mut56729 = false, _mut56730 = false, _mut56731 = false, _mut56732 = false, _mut56733 = false, _mut56734 = false, _mut56735 = false, _mut56736 = false, _mut56737 = false, _mut56738 = false, _mut56739 = false, _mut56740 = false, _mut56741 = false, _mut56742 = false, _mut56743 = false, _mut56744 = false, _mut56745 = false, _mut56746 = false, _mut56747 = false, _mut56748 = false, _mut56749 = false, _mut56750 = false, _mut56751 = false, _mut56752 = false, _mut56753 = false, _mut56754 = false, _mut56755 = false, _mut56756 = false, _mut56757 = false, _mut56758 = false, _mut56759 = false, _mut56760 = false, _mut56761 = false, _mut56762 = false, _mut56763 = false, _mut56764 = false, _mut56765 = false, _mut56766 = false, _mut56767 = false, _mut56768 = false, _mut56769 = false, _mut56770 = false, _mut56771 = false, _mut56772 = false, _mut56773 = false, _mut56774 = false, _mut56775 = false, _mut56776 = false, _mut56777 = false, _mut56778 = false, _mut56779 = false, _mut56780 = false, _mut56781 = false, _mut56782 = false, _mut56783 = false, _mut56784 = false, _mut56785 = false, _mut56786 = false, _mut56787 = false, _mut56788 = false, _mut56789 = false, _mut56790 = false, _mut56791 = false, _mut56792 = false, _mut56793 = false, _mut56794 = false, _mut56795 = false, _mut56796 = false, _mut56797 = false, _mut56798 = false, _mut56799 = false, _mut56800 = false, _mut56801 = false, _mut56802 = false, _mut56803 = false, _mut56804 = false, _mut56805 = false, _mut56806 = false, _mut56807 = false, _mut56808 = false, _mut56809 = false, _mut56810 = false, _mut56811 = false, _mut56812 = false, _mut56813 = false, _mut56814 = false, _mut56815 = false, _mut56816 = false, _mut56817 = false, _mut56818 = false, _mut56819 = false, _mut56820 = false, _mut56821 = false, _mut56822 = false, _mut56823 = false, _mut56824 = false, _mut56825 = false, _mut56826 = false, _mut56827 = false, _mut56828 = false, _mut56829 = false, _mut56830 = false, _mut56831 = false, _mut56832 = false, _mut56833 = false, _mut56834 = false;

    /**
     * Default maximum number of iterations for cumulative probability calculations.
     * @since 2.1
     */
    public static final int DEFAULT_MAX_ITERATIONS = 10000000;

    /**
     * Default convergence criterion.
     * @since 2.1
     */
    public static final double DEFAULT_EPSILON = 1e-12;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -3349935121172596109L;

    /**
     * Distribution used to compute normal approximation.
     */
    private final NormalDistribution normal;

    /**
     * Distribution needed for the {@link #sample()} method.
     */
    private final ExponentialDistribution exponential;

    /**
     * Mean of the distribution.
     */
    private final double mean;

    /**
     * Maximum number of iterations for cumulative probability. Cumulative
     * probabilities are estimated using either Lanczos series approximation
     * of {@link Gamma#regularizedGammaP(double, double, double, int)}
     * or continued fraction approximation of
     * {@link Gamma#regularizedGammaQ(double, double, double, int)}.
     */
    private final int maxIterations;

    /**
     * Convergence criterion for cumulative probability.
     */
    private final double epsilon;

    /**
     * Creates a new Poisson distribution with specified mean.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param p the Poisson mean
     * @throws NotStrictlyPositiveException if {@code p <= 0}.
     */
    public PoissonDistribution(double p) throws NotStrictlyPositiveException {
        this(p, DEFAULT_EPSILON, DEFAULT_MAX_ITERATIONS);
    }

    /**
     * Creates a new Poisson distribution with specified mean, convergence
     * criterion and maximum number of iterations.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param p Poisson mean.
     * @param epsilon Convergence criterion for cumulative probabilities.
     * @param maxIterations the maximum number of iterations for cumulative
     * probabilities.
     * @throws NotStrictlyPositiveException if {@code p <= 0}.
     * @since 2.1
     */
    public PoissonDistribution(double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        this(new Well19937c(), p, epsilon, maxIterations);
    }

    /**
     * Creates a new Poisson distribution with specified mean, convergence
     * criterion and maximum number of iterations.
     *
     * @param rng Random number generator.
     * @param p Poisson mean.
     * @param epsilon Convergence criterion for cumulative probabilities.
     * @param maxIterations the maximum number of iterations for cumulative
     * probabilities.
     * @throws NotStrictlyPositiveException if {@code p <= 0}.
     * @since 3.1
     */
    public PoissonDistribution(RandomGenerator rng, double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.PoissonDistribution_118");
        if (ROR_less_equals(p, 0, "org.apache.commons.math3.distribution.PoissonDistribution.PoissonDistribution_118", _mut56438, _mut56439, _mut56440, _mut56441, _mut56442)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, p);
        }
        mean = p;
        this.epsilon = epsilon;
        this.maxIterations = maxIterations;
        // Use the same RNG instance as the parent class.
        normal = new NormalDistribution(rng, p, FastMath.sqrt(p), NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
        exponential = new ExponentialDistribution(rng, 1, ExponentialDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a new Poisson distribution with the specified mean and
     * convergence criterion.
     *
     * @param p Poisson mean.
     * @param epsilon Convergence criterion for cumulative probabilities.
     * @throws NotStrictlyPositiveException if {@code p <= 0}.
     * @since 2.1
     */
    public PoissonDistribution(double p, double epsilon) throws NotStrictlyPositiveException {
        this(p, epsilon, DEFAULT_MAX_ITERATIONS);
    }

    /**
     * Creates a new Poisson distribution with the specified mean and maximum
     * number of iterations.
     *
     * @param p Poisson mean.
     * @param maxIterations Maximum number of iterations for cumulative
     * probabilities.
     * @since 2.1
     */
    public PoissonDistribution(double p, int maxIterations) {
        this(p, DEFAULT_EPSILON, maxIterations);
    }

    /**
     * Get the mean for the distribution.
     *
     * @return the mean for the distribution.
     */
    public double getMean() {
        return mean;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.probability_176");
        final double logProbability = logProbability(x);
        return ROR_equals(logProbability, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.PoissonDistribution.probability_176", _mut56443, _mut56444, _mut56445, _mut56446, _mut56447) ? 0 : FastMath.exp(logProbability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182");
        double ret;
        if ((_mut56458 ? (ROR_less(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56448, _mut56449, _mut56450, _mut56451, _mut56452) && ROR_equals(x, Integer.MAX_VALUE, "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56453, _mut56454, _mut56455, _mut56456, _mut56457)) : (ROR_less(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56448, _mut56449, _mut56450, _mut56451, _mut56452) || ROR_equals(x, Integer.MAX_VALUE, "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56453, _mut56454, _mut56455, _mut56456, _mut56457)))) {
            ret = Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56459, _mut56460, _mut56461, _mut56462, _mut56463)) {
            ret = -mean;
        } else {
            ret = AOR_minus(AOR_minus(AOR_minus(-SaddlePointExpansion.getStirlingError(x), SaddlePointExpansion.getDeviancePart(x, mean), "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56464, _mut56465, _mut56466, _mut56467), AOR_multiply(0.5, FastMath.log(MathUtils.TWO_PI), "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56468, _mut56469, _mut56470, _mut56471), "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56472, _mut56473, _mut56474, _mut56475), AOR_multiply(0.5, FastMath.log(x), "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56476, _mut56477, _mut56478, _mut56479), "org.apache.commons.math3.distribution.PoissonDistribution.logProbability_182", _mut56480, _mut56481, _mut56482, _mut56483);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.cumulativeProbability_198");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.cumulativeProbability_198", _mut56484, _mut56485, _mut56486, _mut56487, _mut56488)) {
            return 0;
        }
        if (ROR_equals(x, Integer.MAX_VALUE, "org.apache.commons.math3.distribution.PoissonDistribution.cumulativeProbability_198", _mut56489, _mut56490, _mut56491, _mut56492, _mut56493)) {
            return 1;
        }
        return Gamma.regularizedGammaQ(AOR_plus((double) x, 1, "org.apache.commons.math3.distribution.PoissonDistribution.cumulativeProbability_198", _mut56494, _mut56495, _mut56496, _mut56497), mean, epsilon, maxIterations);
    }

    /**
     * Calculates the Poisson distribution function using a normal
     * approximation. The {@code N(mean, sqrt(mean))} distribution is used
     * to approximate the Poisson distribution. The computation uses
     * "half-correction" (evaluating the normal distribution function at
     * {@code x + 0.5}).
     *
     * @param x Upper bound, inclusive.
     * @return the distribution function value calculated using a normal
     * approximation.
     */
    public double normalApproximateProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.normalApproximateProbability_220");
        // calculate the probability using half-correction
        return normal.cumulativeProbability(AOR_plus(x, 0.5, "org.apache.commons.math3.distribution.PoissonDistribution.normalApproximateProbability_220", _mut56498, _mut56499, _mut56500, _mut56501));
    }

    /**
     * {@inheritDoc}
     *
     * For mean parameter {@code p}, the mean is {@code p}.
     */
    public double getNumericalMean() {
        return getMean();
    }

    /**
     * {@inheritDoc}
     *
     * For mean parameter {@code p}, the variance is {@code p}.
     */
    public double getNumericalVariance() {
        return getMean();
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the mean parameter.
     *
     * @return lower bound of the support (always 0)
     */
    public int getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is positive infinity,
     * regardless of the parameter values. There is no integer infinity,
     * so this method returns {@code Integer.MAX_VALUE}.
     *
     * @return upper bound of the support (always {@code Integer.MAX_VALUE} for
     * positive infinity)
     */
    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
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
     * <strong>Algorithm Description</strong>:
     * <ul>
     *  <li>For small means, uses simulation of a Poisson process
     *   using Uniform deviates, as described
     *   <a href="http://mathaa.epfl.ch/cours/PMMI2001/interactive/rng7.htm"> here</a>.
     *   The Poisson process (and hence value returned) is bounded by 1000 * mean.
     *  </li>
     *  <li>For large means, uses the rejection algorithm described in
     *   <blockquote>
     *    Devroye, Luc. (1981).<i>The Computer Generation of Poisson Random Variables</i><br>
     *    <strong>Computing</strong> vol. 26 pp. 197-207.<br>
     *   </blockquote>
     *  </li>
     * </ul>
     * </p>
     *
     * @return a random value.
     * @since 2.2
     */
    @Override
    public int sample() {
        return (int) FastMath.min(nextPoisson(mean), Integer.MAX_VALUE);
    }

    /**
     * @param meanPoisson Mean of the Poisson distribution.
     * @return the next sample.
     */
    private long nextPoisson(double meanPoisson) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310");
        final double pivot = 40.0d;
        if (ROR_less(meanPoisson, pivot, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56502, _mut56503, _mut56504, _mut56505, _mut56506)) {
            double p = FastMath.exp(-meanPoisson);
            long n = 0;
            double r = 1.0d;
            double rnd = 1.0d;
            while (ROR_less(n, AOR_multiply(1000, meanPoisson, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56826, _mut56827, _mut56828, _mut56829), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56830, _mut56831, _mut56832, _mut56833, _mut56834)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310");
                rnd = random.nextDouble();
                r *= rnd;
                if (ROR_greater_equals(r, p, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56821, _mut56822, _mut56823, _mut56824, _mut56825)) {
                    n++;
                } else {
                    return n;
                }
            }
            return n;
        } else {
            final double lambda = FastMath.floor(meanPoisson);
            final double lambdaFractional = AOR_minus(meanPoisson, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56507, _mut56508, _mut56509, _mut56510);
            final double logLambda = FastMath.log(lambda);
            final double logLambdaFactorial = CombinatoricsUtils.factorialLog((int) lambda);
            final long y2 = ROR_less(lambdaFractional, Double.MIN_VALUE, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56511, _mut56512, _mut56513, _mut56514, _mut56515) ? 0 : nextPoisson(lambdaFractional);
            final double delta = FastMath.sqrt(AOR_multiply(lambda, FastMath.log(AOR_plus(AOR_divide(AOR_multiply(32, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56516, _mut56517, _mut56518, _mut56519), FastMath.PI, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56520, _mut56521, _mut56522, _mut56523), 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56524, _mut56525, _mut56526, _mut56527)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56528, _mut56529, _mut56530, _mut56531));
            final double halfDelta = AOR_divide(delta, 2, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56532, _mut56533, _mut56534, _mut56535);
            final double twolpd = AOR_plus(AOR_multiply(2, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56536, _mut56537, _mut56538, _mut56539), delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56540, _mut56541, _mut56542, _mut56543);
            final double a1 = AOR_multiply(FastMath.sqrt(AOR_multiply(FastMath.PI, twolpd, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56544, _mut56545, _mut56546, _mut56547)), FastMath.exp(AOR_divide(1, (AOR_multiply(8, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56548, _mut56549, _mut56550, _mut56551)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56552, _mut56553, _mut56554, _mut56555)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56556, _mut56557, _mut56558, _mut56559);
            final double a2 = AOR_multiply((AOR_divide(twolpd, delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56560, _mut56561, _mut56562, _mut56563)), FastMath.exp(AOR_divide(AOR_multiply(-delta, (AOR_plus(1, delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56564, _mut56565, _mut56566, _mut56567)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56568, _mut56569, _mut56570, _mut56571), twolpd, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56572, _mut56573, _mut56574, _mut56575)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56576, _mut56577, _mut56578, _mut56579);
            final double aSum = AOR_plus(AOR_plus(a1, a2, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56580, _mut56581, _mut56582, _mut56583), 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56584, _mut56585, _mut56586, _mut56587);
            final double p1 = AOR_divide(a1, aSum, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56588, _mut56589, _mut56590, _mut56591);
            final double p2 = AOR_divide(a2, aSum, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56592, _mut56593, _mut56594, _mut56595);
            final double c1 = AOR_divide(1, (AOR_multiply(8, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56596, _mut56597, _mut56598, _mut56599)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56600, _mut56601, _mut56602, _mut56603);
            double x = 0;
            double y = 0;
            double v = 0;
            int a = 0;
            double t = 0;
            double qr = 0;
            double qa = 0;
            for (; ; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310");
                final double u = random.nextDouble();
                if (ROR_less_equals(u, p1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56604, _mut56605, _mut56606, _mut56607, _mut56608)) {
                    final double n = random.nextGaussian();
                    x = AOR_minus(AOR_multiply(n, FastMath.sqrt(AOR_plus(lambda, halfDelta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56646, _mut56647, _mut56648, _mut56649)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56650, _mut56651, _mut56652, _mut56653), 0.5d, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56654, _mut56655, _mut56656, _mut56657);
                    if ((_mut56668 ? (ROR_greater(x, delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56658, _mut56659, _mut56660, _mut56661, _mut56662) && ROR_less(x, -lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56663, _mut56664, _mut56665, _mut56666, _mut56667)) : (ROR_greater(x, delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56658, _mut56659, _mut56660, _mut56661, _mut56662) || ROR_less(x, -lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56663, _mut56664, _mut56665, _mut56666, _mut56667)))) {
                        continue;
                    }
                    y = ROR_less(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56669, _mut56670, _mut56671, _mut56672, _mut56673) ? FastMath.floor(x) : FastMath.ceil(x);
                    final double e = exponential.sample();
                    v = AOR_plus(AOR_minus(-e, (AOR_divide(AOR_multiply(n, n, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56674, _mut56675, _mut56676, _mut56677), 2, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56678, _mut56679, _mut56680, _mut56681)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56682, _mut56683, _mut56684, _mut56685), c1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56686, _mut56687, _mut56688, _mut56689);
                } else {
                    if (ROR_greater(u, AOR_plus(p1, p2, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56609, _mut56610, _mut56611, _mut56612), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56613, _mut56614, _mut56615, _mut56616, _mut56617)) {
                        y = lambda;
                        break;
                    } else {
                        x = AOR_plus(delta, AOR_multiply((AOR_divide(twolpd, delta, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56618, _mut56619, _mut56620, _mut56621)), exponential.sample(), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56622, _mut56623, _mut56624, _mut56625), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56626, _mut56627, _mut56628, _mut56629);
                        y = FastMath.ceil(x);
                        v = AOR_minus(-exponential.sample(), AOR_divide(AOR_multiply(delta, (AOR_plus(x, 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56630, _mut56631, _mut56632, _mut56633)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56634, _mut56635, _mut56636, _mut56637), twolpd, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56638, _mut56639, _mut56640, _mut56641), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56642, _mut56643, _mut56644, _mut56645);
                    }
                }
                a = ROR_less(x, 0, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56690, _mut56691, _mut56692, _mut56693, _mut56694) ? 1 : 0;
                t = AOR_divide(AOR_multiply(y, (AOR_plus(y, 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56695, _mut56696, _mut56697, _mut56698)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56699, _mut56700, _mut56701, _mut56702), (AOR_multiply(2, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56703, _mut56704, _mut56705, _mut56706)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56707, _mut56708, _mut56709, _mut56710);
                if ((_mut56721 ? (ROR_less(v, -t, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56711, _mut56712, _mut56713, _mut56714, _mut56715) || ROR_equals(a, 0, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56716, _mut56717, _mut56718, _mut56719, _mut56720)) : (ROR_less(v, -t, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56711, _mut56712, _mut56713, _mut56714, _mut56715) && ROR_equals(a, 0, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56716, _mut56717, _mut56718, _mut56719, _mut56720)))) {
                    y = AOR_plus(lambda, y, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56722, _mut56723, _mut56724, _mut56725);
                    break;
                }
                qr = AOR_multiply(t, (AOR_minus(AOR_divide((AOR_plus(AOR_multiply(2, y, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56726, _mut56727, _mut56728, _mut56729), 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56730, _mut56731, _mut56732, _mut56733)), (AOR_multiply(6, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56734, _mut56735, _mut56736, _mut56737)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56738, _mut56739, _mut56740, _mut56741), 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56742, _mut56743, _mut56744, _mut56745)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56746, _mut56747, _mut56748, _mut56749);
                qa = AOR_minus(qr, AOR_divide((AOR_multiply(t, t, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56750, _mut56751, _mut56752, _mut56753)), (AOR_multiply(3, (AOR_plus(lambda, AOR_multiply(a, (AOR_plus(y, 1, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56754, _mut56755, _mut56756, _mut56757)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56758, _mut56759, _mut56760, _mut56761), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56762, _mut56763, _mut56764, _mut56765)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56766, _mut56767, _mut56768, _mut56769)), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56770, _mut56771, _mut56772, _mut56773), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56774, _mut56775, _mut56776, _mut56777);
                if (ROR_less(v, qa, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56778, _mut56779, _mut56780, _mut56781, _mut56782)) {
                    y = AOR_plus(lambda, y, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56783, _mut56784, _mut56785, _mut56786);
                    break;
                }
                if (ROR_greater(v, qr, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56787, _mut56788, _mut56789, _mut56790, _mut56791)) {
                    continue;
                }
                if (ROR_less(v, AOR_plus(AOR_minus(AOR_multiply(y, logLambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56792, _mut56793, _mut56794, _mut56795), CombinatoricsUtils.factorialLog((int) (AOR_plus(y, lambda, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56796, _mut56797, _mut56798, _mut56799))), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56800, _mut56801, _mut56802, _mut56803), logLambdaFactorial, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56804, _mut56805, _mut56806, _mut56807), "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56808, _mut56809, _mut56810, _mut56811, _mut56812)) {
                    y = AOR_plus(lambda, y, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56813, _mut56814, _mut56815, _mut56816);
                    break;
                }
            }
            return AOR_plus(y2, (long) y, "org.apache.commons.math3.distribution.PoissonDistribution.nextPoisson_310", _mut56817, _mut56818, _mut56819, _mut56820);
        }
    }
}
