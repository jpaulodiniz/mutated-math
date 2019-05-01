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
package org.apache.commons.math3.optimization.fitting;

import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class that implements a curve fitting specialized for sinusoids.
 *
 * Harmonic fitting is a very simple case of curve fitting. The
 * estimated coefficients are the amplitude a, the pulsation &omega; and
 * the phase &phi;: <code>f (t) = a cos (&omega; t + &phi;)</code>. They are
 * searched by a least square estimator initialized with a rough guess
 * based on integrals.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class HarmonicFitter extends CurveFitter<HarmonicOscillator.Parametric> {

    @Conditional
    public static boolean _mut71534 = false, _mut71535 = false, _mut71536 = false, _mut71537 = false, _mut71538 = false, _mut71539 = false, _mut71540 = false, _mut71541 = false, _mut71542 = false, _mut71543 = false, _mut71544 = false, _mut71545 = false, _mut71546 = false, _mut71547 = false, _mut71548 = false, _mut71549 = false, _mut71550 = false, _mut71551 = false, _mut71552 = false, _mut71553 = false, _mut71554 = false, _mut71555 = false, _mut71556 = false, _mut71557 = false, _mut71558 = false, _mut71559 = false, _mut71560 = false, _mut71561 = false, _mut71562 = false, _mut71563 = false, _mut71564 = false, _mut71565 = false, _mut71566 = false, _mut71567 = false, _mut71568 = false, _mut71569 = false, _mut71570 = false, _mut71571 = false, _mut71572 = false, _mut71573 = false, _mut71574 = false, _mut71575 = false, _mut71576 = false, _mut71577 = false, _mut71578 = false, _mut71579 = false, _mut71580 = false, _mut71581 = false, _mut71582 = false, _mut71583 = false, _mut71584 = false, _mut71585 = false, _mut71586 = false, _mut71587 = false, _mut71588 = false, _mut71589 = false, _mut71590 = false, _mut71591 = false, _mut71592 = false, _mut71593 = false, _mut71594 = false, _mut71595 = false, _mut71596 = false, _mut71597 = false, _mut71598 = false, _mut71599 = false, _mut71600 = false, _mut71601 = false, _mut71602 = false, _mut71603 = false, _mut71604 = false, _mut71605 = false, _mut71606 = false, _mut71607 = false, _mut71608 = false, _mut71609 = false, _mut71610 = false, _mut71611 = false, _mut71612 = false, _mut71613 = false, _mut71614 = false, _mut71615 = false, _mut71616 = false, _mut71617 = false, _mut71618 = false, _mut71619 = false, _mut71620 = false, _mut71621 = false, _mut71622 = false, _mut71623 = false, _mut71624 = false, _mut71625 = false, _mut71626 = false, _mut71627 = false, _mut71628 = false, _mut71629 = false, _mut71630 = false, _mut71631 = false, _mut71632 = false, _mut71633 = false, _mut71634 = false, _mut71635 = false, _mut71636 = false, _mut71637 = false, _mut71638 = false, _mut71639 = false, _mut71640 = false, _mut71641 = false, _mut71642 = false, _mut71643 = false, _mut71644 = false, _mut71645 = false, _mut71646 = false, _mut71647 = false, _mut71648 = false, _mut71649 = false, _mut71650 = false, _mut71651 = false, _mut71652 = false, _mut71653 = false, _mut71654 = false, _mut71655 = false, _mut71656 = false, _mut71657 = false, _mut71658 = false, _mut71659 = false, _mut71660 = false, _mut71661 = false, _mut71662 = false, _mut71663 = false, _mut71664 = false, _mut71665 = false, _mut71666 = false, _mut71667 = false, _mut71668 = false, _mut71669 = false, _mut71670 = false, _mut71671 = false, _mut71672 = false, _mut71673 = false, _mut71674 = false, _mut71675 = false, _mut71676 = false, _mut71677 = false, _mut71678 = false, _mut71679 = false, _mut71680 = false, _mut71681 = false, _mut71682 = false, _mut71683 = false, _mut71684 = false, _mut71685 = false, _mut71686 = false, _mut71687 = false, _mut71688 = false, _mut71689 = false, _mut71690 = false, _mut71691 = false, _mut71692 = false, _mut71693 = false, _mut71694 = false, _mut71695 = false, _mut71696 = false, _mut71697 = false, _mut71698 = false, _mut71699 = false, _mut71700 = false, _mut71701 = false, _mut71702 = false, _mut71703 = false, _mut71704 = false, _mut71705 = false, _mut71706 = false, _mut71707 = false, _mut71708 = false, _mut71709 = false, _mut71710 = false, _mut71711 = false, _mut71712 = false, _mut71713 = false, _mut71714 = false, _mut71715 = false, _mut71716 = false, _mut71717 = false, _mut71718 = false, _mut71719 = false, _mut71720 = false, _mut71721 = false, _mut71722 = false, _mut71723 = false, _mut71724 = false, _mut71725 = false, _mut71726 = false, _mut71727 = false, _mut71728 = false, _mut71729 = false, _mut71730 = false, _mut71731 = false, _mut71732 = false, _mut71733 = false, _mut71734 = false, _mut71735 = false, _mut71736 = false, _mut71737 = false, _mut71738 = false, _mut71739 = false, _mut71740 = false, _mut71741 = false, _mut71742 = false, _mut71743 = false, _mut71744 = false, _mut71745 = false, _mut71746 = false, _mut71747 = false, _mut71748 = false, _mut71749 = false, _mut71750 = false, _mut71751 = false, _mut71752 = false, _mut71753 = false, _mut71754 = false, _mut71755 = false, _mut71756 = false, _mut71757 = false, _mut71758 = false, _mut71759 = false, _mut71760 = false, _mut71761 = false, _mut71762 = false, _mut71763 = false, _mut71764 = false, _mut71765 = false, _mut71766 = false, _mut71767 = false, _mut71768 = false, _mut71769 = false, _mut71770 = false, _mut71771 = false, _mut71772 = false, _mut71773 = false, _mut71774 = false, _mut71775 = false, _mut71776 = false, _mut71777 = false, _mut71778 = false, _mut71779 = false, _mut71780 = false, _mut71781 = false, _mut71782 = false, _mut71783 = false, _mut71784 = false, _mut71785 = false, _mut71786 = false, _mut71787 = false, _mut71788 = false, _mut71789 = false, _mut71790 = false, _mut71791 = false, _mut71792 = false, _mut71793 = false, _mut71794 = false, _mut71795 = false, _mut71796 = false, _mut71797 = false, _mut71798 = false, _mut71799 = false, _mut71800 = false, _mut71801 = false, _mut71802 = false, _mut71803 = false, _mut71804 = false, _mut71805 = false, _mut71806 = false, _mut71807 = false, _mut71808 = false, _mut71809 = false, _mut71810 = false, _mut71811 = false, _mut71812 = false, _mut71813 = false, _mut71814 = false;

    /**
     * Simple constructor.
     * @param optimizer Optimizer to use for the fitting.
     */
    public HarmonicFitter(final DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    /**
     * Fit an harmonic function to the observed points.
     *
     * @param initialGuess First guess values in the following order:
     * <ul>
     *  <li>Amplitude</li>
     *  <li>Angular frequency</li>
     *  <li>Phase</li>
     * </ul>
     * @return the parameters of the harmonic function that best fits the
     * observed points (in the same order as above).
     */
    public double[] fit(double[] initialGuess) {
        return fit(new HarmonicOscillator.Parametric(), initialGuess);
    }

    /**
     * Fit an harmonic function to the observed points.
     * An initial guess will be automatically computed.
     *
     * @return the parameters of the harmonic function that best fits the
     * observed points (see the other {@link #fit(double[]) fit} method.
     * @throws NumberIsTooSmallException if the sample is too short for the
     * the first guess to be computed.
     * @throws ZeroException if the first guess cannot be computed because
     * the abscissa range is zero.
     */
    public double[] fit() {
        return fit((new ParameterGuesser(getObservations())).guess());
    }

    /**
     * This class guesses harmonic coefficients from a sample.
     * <p>The algorithm used to guess the coefficients is as follows:</p>
     *
     * <p>We know f (t) at some sampling points t<sub>i</sub> and want to find a,
     * &omega; and &phi; such that f (t) = a cos (&omega; t + &phi;).
     * </p>
     *
     * <p>From the analytical expression, we can compute two primitives :
     * <pre>
     *     If2  (t) = &int; f<sup>2</sup>  = a<sup>2</sup> &times; [t + S (t)] / 2
     *     If'2 (t) = &int; f'<sup>2</sup> = a<sup>2</sup> &omega;<sup>2</sup> &times; [t - S (t)] / 2
     *     where S (t) = sin (2 (&omega; t + &phi;)) / (2 &omega;)
     * </pre>
     * </p>
     *
     * <p>We can remove S between these expressions :
     * <pre>
     *     If'2 (t) = a<sup>2</sup> &omega;<sup>2</sup> t - &omega;<sup>2</sup> If2 (t)
     * </pre>
     * </p>
     *
     * <p>The preceding expression shows that If'2 (t) is a linear
     * combination of both t and If2 (t): If'2 (t) = A &times; t + B &times; If2 (t)
     * </p>
     *
     * <p>From the primitive, we can deduce the same form for definite
     * integrals between t<sub>1</sub> and t<sub>i</sub> for each t<sub>i</sub> :
     * <pre>
     *   If2 (t<sub>i</sub>) - If2 (t<sub>1</sub>) = A &times; (t<sub>i</sub> - t<sub>1</sub>) + B &times; (If2 (t<sub>i</sub>) - If2 (t<sub>1</sub>))
     * </pre>
     * </p>
     *
     * <p>We can find the coefficients A and B that best fit the sample
     * to this linear expression by computing the definite integrals for
     * each sample points.
     * </p>
     *
     * <p>For a bilinear expression z (x<sub>i</sub>, y<sub>i</sub>) = A &times; x<sub>i</sub> + B &times; y<sub>i</sub>, the
     * coefficients A and B that minimize a least square criterion
     * &sum; (z<sub>i</sub> - z (x<sub>i</sub>, y<sub>i</sub>))<sup>2</sup> are given by these expressions:</p>
     * <pre>
     *
     *         &sum;y<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>z<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;y<sub>i</sub>z<sub>i</sub>
     *     A = ------------------------
     *         &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>y<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>y<sub>i</sub>
     *
     *         &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>z<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>z<sub>i</sub>
     *     B = ------------------------
     *         &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>y<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>y<sub>i</sub>
     * </pre>
     * </p>
     *
     * <p>In fact, we can assume both a and &omega; are positive and
     * compute them directly, knowing that A = a<sup>2</sup> &omega;<sup>2</sup> and that
     * B = - &omega;<sup>2</sup>. The complete algorithm is therefore:</p>
     * <pre>
     *
     * for each t<sub>i</sub> from t<sub>1</sub> to t<sub>n-1</sub>, compute:
     *   f  (t<sub>i</sub>)
     *   f' (t<sub>i</sub>) = (f (t<sub>i+1</sub>) - f(t<sub>i-1</sub>)) / (t<sub>i+1</sub> - t<sub>i-1</sub>)
     *   x<sub>i</sub> = t<sub>i</sub> - t<sub>1</sub>
     *   y<sub>i</sub> = &int; f<sup>2</sup> from t<sub>1</sub> to t<sub>i</sub>
     *   z<sub>i</sub> = &int; f'<sup>2</sup> from t<sub>1</sub> to t<sub>i</sub>
     *   update the sums &sum;x<sub>i</sub>x<sub>i</sub>, &sum;y<sub>i</sub>y<sub>i</sub>, &sum;x<sub>i</sub>y<sub>i</sub>, &sum;x<sub>i</sub>z<sub>i</sub> and &sum;y<sub>i</sub>z<sub>i</sub>
     * end for
     *
     *            |--------------------------
     *         \  | &sum;y<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>z<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;y<sub>i</sub>z<sub>i</sub>
     * a     =  \ | ------------------------
     *           \| &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>z<sub>i</sub> - &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>z<sub>i</sub>
     *
     *            |--------------------------
     *         \  | &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>z<sub>i</sub> - &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>z<sub>i</sub>
     * &omega;     =  \ | ------------------------
     *           \| &sum;x<sub>i</sub>x<sub>i</sub> &sum;y<sub>i</sub>y<sub>i</sub> - &sum;x<sub>i</sub>y<sub>i</sub> &sum;x<sub>i</sub>y<sub>i</sub>
     *
     * </pre>
     * </p>
     *
     * <p>Once we know &omega;, we can compute:
     * <pre>
     *    fc = &omega; f (t) cos (&omega; t) - f' (t) sin (&omega; t)
     *    fs = &omega; f (t) sin (&omega; t) + f' (t) cos (&omega; t)
     * </pre>
     * </p>
     *
     * <p>It appears that <code>fc = a &omega; cos (&phi;)</code> and
     * <code>fs = -a &omega; sin (&phi;)</code>, so we can use these
     * expressions to compute &phi;. The best estimate over the sample is
     * given by averaging these expressions.
     * </p>
     *
     * <p>Since integrals and means are involved in the preceding
     * estimations, these operations run in O(n) time, where n is the
     * number of measurements.</p>
     */
    public static class ParameterGuesser {

        /**
         * Amplitude.
         */
        private final double a;

        /**
         * Angular frequency.
         */
        private final double omega;

        /**
         * Phase.
         */
        private final double phi;

        /**
         * Simple constructor.
         *
         * @param observations Sampled observations.
         * @throws NumberIsTooSmallException if the sample is too short.
         * @throws ZeroException if the abscissa range is zero.
         * @throws MathIllegalStateException when the guessing procedure cannot
         * produce sensible results.
         */
        public ParameterGuesser(WeightedObservedPoint[] observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.ParameterGuesser_197");
            if (ROR_less(observations.length, 4, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.ParameterGuesser_197", _mut71534, _mut71535, _mut71536, _mut71537, _mut71538)) {
                throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, observations.length, 4, true);
            }
            final WeightedObservedPoint[] sorted = sortObservations(observations);
            final double[] aOmega = guessAOmega(sorted);
            a = aOmega[0];
            omega = aOmega[1];
            phi = guessPhi(sorted);
        }

        /**
         * Gets an estimation of the parameters.
         *
         * @return the guessed parameters, in the following order:
         * <ul>
         *  <li>Amplitude</li>
         *  <li>Angular frequency</li>
         *  <li>Phase</li>
         * </ul>
         */
        public double[] guess() {
            return new double[] { a, omega, phi };
        }

        /**
         * Sort the observations with respect to the abscissa.
         *
         * @param unsorted Input observations.
         * @return the input observations, sorted.
         */
        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232");
            final WeightedObservedPoint[] observations = unsorted.clone();
            // elements in place. Insertion sort is very efficient in this case.
            WeightedObservedPoint curr = observations[0];
            for (int j = 1; ROR_less(j, observations.length, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71572, _mut71573, _mut71574, _mut71575, _mut71576); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232");
                WeightedObservedPoint prec = curr;
                curr = observations[j];
                if (ROR_less(curr.getX(), prec.getX(), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71539, _mut71540, _mut71541, _mut71542, _mut71543)) {
                    // the current element should be inserted closer to the beginning
                    int i = AOR_minus(j, 1, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71544, _mut71545, _mut71546, _mut71547);
                    WeightedObservedPoint mI = observations[i];
                    while ((_mut71567 ? ((ROR_greater_equals(i, 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71557, _mut71558, _mut71559, _mut71560, _mut71561)) || (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71562, _mut71563, _mut71564, _mut71565, _mut71566))) : ((ROR_greater_equals(i, 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71557, _mut71558, _mut71559, _mut71560, _mut71561)) && (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71562, _mut71563, _mut71564, _mut71565, _mut71566))))) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232");
                        observations[AOR_plus(i, 1, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71548, _mut71549, _mut71550, _mut71551)] = mI;
                        if (ROR_not_equals(i--, 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71552, _mut71553, _mut71554, _mut71555, _mut71556)) {
                            mI = observations[i];
                        }
                    }
                    observations[AOR_plus(i, 1, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.sortObservations_232", _mut71568, _mut71569, _mut71570, _mut71571)] = curr;
                    curr = observations[j];
                }
            }
            return observations;
        }

        /**
         * Estimate a first guess of the amplitude and angular frequency.
         * This method assumes that the {@link #sortObservations(WeightedObservedPoint[])} method
         * has been called previously.
         *
         * @param observations Observations, sorted w.r.t. abscissa.
         * @throws ZeroException if the abscissa range is zero.
         * @throws MathIllegalStateException when the guessing procedure cannot
         * produce sensible results.
         * @return the guessed amplitude (at index 0) and circular frequency
         * (at index 1).
         */
        private double[] guessAOmega(WeightedObservedPoint[] observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272");
            final double[] aOmega = new double[2];
            // initialize the sums for the linear model between the two integrals
            double sx2 = 0;
            double sy2 = 0;
            double sxy = 0;
            double sxz = 0;
            double syz = 0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            double f2Integral = 0;
            double fPrime2Integral = 0;
            final double startX = currentX;
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71645, _mut71646, _mut71647, _mut71648, _mut71649); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                // considering a linear model for f (and therefore constant f')
                final double dx = AOR_minus(currentX, previousX, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71577, _mut71578, _mut71579, _mut71580);
                final double dy = AOR_minus(currentY, previousY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71581, _mut71582, _mut71583, _mut71584);
                final double f2StepIntegral = AOR_divide(AOR_multiply(dx, (AOR_plus(AOR_plus(AOR_multiply(previousY, previousY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71585, _mut71586, _mut71587, _mut71588), AOR_multiply(previousY, currentY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71589, _mut71590, _mut71591, _mut71592), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71593, _mut71594, _mut71595, _mut71596), AOR_multiply(currentY, currentY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71597, _mut71598, _mut71599, _mut71600), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71601, _mut71602, _mut71603, _mut71604)), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71605, _mut71606, _mut71607, _mut71608), 3, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71609, _mut71610, _mut71611, _mut71612);
                final double fPrime2StepIntegral = AOR_divide(AOR_multiply(dy, dy, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71613, _mut71614, _mut71615, _mut71616), dx, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71617, _mut71618, _mut71619, _mut71620);
                final double x = AOR_minus(currentX, startX, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71621, _mut71622, _mut71623, _mut71624);
                f2Integral += f2StepIntegral;
                fPrime2Integral += fPrime2StepIntegral;
                sx2 += AOR_multiply(x, x, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71625, _mut71626, _mut71627, _mut71628);
                sy2 += AOR_multiply(f2Integral, f2Integral, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71629, _mut71630, _mut71631, _mut71632);
                sxy += AOR_multiply(x, f2Integral, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71633, _mut71634, _mut71635, _mut71636);
                sxz += AOR_multiply(x, fPrime2Integral, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71637, _mut71638, _mut71639, _mut71640);
                syz += AOR_multiply(f2Integral, fPrime2Integral, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71641, _mut71642, _mut71643, _mut71644);
            }
            // compute the amplitude and pulsation coefficients
            double c1 = AOR_minus(AOR_multiply(sy2, sxz, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71650, _mut71651, _mut71652, _mut71653), AOR_multiply(sxy, syz, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71654, _mut71655, _mut71656, _mut71657), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71658, _mut71659, _mut71660, _mut71661);
            double c2 = AOR_minus(AOR_multiply(sxy, sxz, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71662, _mut71663, _mut71664, _mut71665), AOR_multiply(sx2, syz, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71666, _mut71667, _mut71668, _mut71669), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71670, _mut71671, _mut71672, _mut71673);
            double c3 = AOR_minus(AOR_multiply(sx2, sy2, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71674, _mut71675, _mut71676, _mut71677), AOR_multiply(sxy, sxy, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71678, _mut71679, _mut71680, _mut71681), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71682, _mut71683, _mut71684, _mut71685);
            if ((_mut71704 ? ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71686, _mut71687, _mut71688, _mut71689), 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71690, _mut71691, _mut71692, _mut71693, _mut71694)) && (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71695, _mut71696, _mut71697, _mut71698), 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71699, _mut71700, _mut71701, _mut71702, _mut71703))) : ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71686, _mut71687, _mut71688, _mut71689), 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71690, _mut71691, _mut71692, _mut71693, _mut71694)) || (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71695, _mut71696, _mut71697, _mut71698), 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71699, _mut71700, _mut71701, _mut71702, _mut71703))))) {
                final int last = AOR_minus(observations.length, 1, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71718, _mut71719, _mut71720, _mut71721);
                // observations are sorted.
                final double xRange = AOR_minus(observations[last].getX(), observations[0].getX(), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71722, _mut71723, _mut71724, _mut71725);
                if (ROR_equals(xRange, 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71726, _mut71727, _mut71728, _mut71729, _mut71730)) {
                    throw new ZeroException();
                }
                aOmega[1] = AOR_divide(AOR_multiply(2, Math.PI, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71731, _mut71732, _mut71733, _mut71734), xRange, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71735, _mut71736, _mut71737, _mut71738);
                double yMin = Double.POSITIVE_INFINITY;
                double yMax = Double.NEGATIVE_INFINITY;
                for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71749, _mut71750, _mut71751, _mut71752, _mut71753); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272");
                    final double y = observations[i].getY();
                    if (ROR_less(y, yMin, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71739, _mut71740, _mut71741, _mut71742, _mut71743)) {
                        yMin = y;
                    }
                    if (ROR_greater(y, yMax, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71744, _mut71745, _mut71746, _mut71747, _mut71748)) {
                        yMax = y;
                    }
                }
                aOmega[0] = AOR_multiply(0.5, (AOR_minus(yMax, yMin, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71754, _mut71755, _mut71756, _mut71757)), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71758, _mut71759, _mut71760, _mut71761);
            } else {
                if (ROR_equals(c2, 0, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71705, _mut71706, _mut71707, _mut71708, _mut71709)) {
                    // procedure cannot produce sensible results.
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR);
                }
                aOmega[0] = FastMath.sqrt(AOR_divide(c1, c2, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71710, _mut71711, _mut71712, _mut71713));
                aOmega[1] = FastMath.sqrt(AOR_divide(c2, c3, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessAOmega_272", _mut71714, _mut71715, _mut71716, _mut71717));
            }
            return aOmega;
        }

        /**
         * Estimate a first guess of the phase.
         *
         * @param observations Observations, sorted w.r.t. abscissa.
         * @return the guessed phase.
         */
        private double guessPhi(WeightedObservedPoint[] observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359");
            // initialize the means
            double fcMean = 0;
            double fsMean = 0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71810, _mut71811, _mut71812, _mut71813, _mut71814); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                final double currentYPrime = AOR_divide((AOR_minus(currentY, previousY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71762, _mut71763, _mut71764, _mut71765)), (AOR_minus(currentX, previousX, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71766, _mut71767, _mut71768, _mut71769)), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71770, _mut71771, _mut71772, _mut71773);
                double omegaX = AOR_multiply(omega, currentX, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71774, _mut71775, _mut71776, _mut71777);
                double cosine = FastMath.cos(omegaX);
                double sine = FastMath.sin(omegaX);
                fcMean += AOR_minus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71778, _mut71779, _mut71780, _mut71781), cosine, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71782, _mut71783, _mut71784, _mut71785), AOR_multiply(currentYPrime, sine, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71786, _mut71787, _mut71788, _mut71789), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71790, _mut71791, _mut71792, _mut71793);
                fsMean += AOR_plus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71794, _mut71795, _mut71796, _mut71797), sine, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71798, _mut71799, _mut71800, _mut71801), AOR_multiply(currentYPrime, cosine, "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71802, _mut71803, _mut71804, _mut71805), "org.apache.commons.math3.optimization.fitting.HarmonicFitter.guessPhi_359", _mut71806, _mut71807, _mut71808, _mut71809);
            }
            return FastMath.atan2(-fsMean, fcMean);
        }
    }
}
