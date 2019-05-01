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
package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Fits points to a {@link
 * org.apache.commons.math3.analysis.function.HarmonicOscillator.Parametric harmonic oscillator}
 * function.
 * <br/>
 * The {@link #withStartPoint(double[]) initial guess values} must be passed
 * in the following order:
 * <ul>
 *  <li>Amplitude</li>
 *  <li>Angular frequency</li>
 *  <li>phase</li>
 * </ul>
 * The optimal values will be returned in the same order.
 *
 * @since 3.3
 */
public class HarmonicCurveFitter extends AbstractCurveFitter {

    @Conditional
    public static boolean _mut39436 = false, _mut39437 = false, _mut39438 = false, _mut39439 = false, _mut39440 = false, _mut39441 = false, _mut39442 = false, _mut39443 = false, _mut39444 = false, _mut39445 = false, _mut39446 = false, _mut39447 = false, _mut39448 = false, _mut39449 = false, _mut39450 = false, _mut39451 = false, _mut39452 = false, _mut39453 = false, _mut39454 = false, _mut39455 = false, _mut39456 = false, _mut39457 = false, _mut39458 = false, _mut39459 = false, _mut39460 = false, _mut39461 = false, _mut39462 = false, _mut39463 = false, _mut39464 = false, _mut39465 = false, _mut39466 = false, _mut39467 = false, _mut39468 = false, _mut39469 = false, _mut39470 = false, _mut39471 = false, _mut39472 = false, _mut39473 = false, _mut39474 = false, _mut39475 = false, _mut39476 = false, _mut39477 = false, _mut39478 = false, _mut39479 = false, _mut39480 = false, _mut39481 = false, _mut39482 = false, _mut39483 = false, _mut39484 = false, _mut39485 = false, _mut39486 = false, _mut39487 = false, _mut39488 = false, _mut39489 = false, _mut39490 = false, _mut39491 = false, _mut39492 = false, _mut39493 = false, _mut39494 = false, _mut39495 = false, _mut39496 = false, _mut39497 = false, _mut39498 = false, _mut39499 = false, _mut39500 = false, _mut39501 = false, _mut39502 = false, _mut39503 = false, _mut39504 = false, _mut39505 = false, _mut39506 = false, _mut39507 = false, _mut39508 = false, _mut39509 = false, _mut39510 = false, _mut39511 = false, _mut39512 = false, _mut39513 = false, _mut39514 = false, _mut39515 = false, _mut39516 = false, _mut39517 = false, _mut39518 = false, _mut39519 = false, _mut39520 = false, _mut39521 = false, _mut39522 = false, _mut39523 = false, _mut39524 = false, _mut39525 = false, _mut39526 = false, _mut39527 = false, _mut39528 = false, _mut39529 = false, _mut39530 = false, _mut39531 = false, _mut39532 = false, _mut39533 = false, _mut39534 = false, _mut39535 = false, _mut39536 = false, _mut39537 = false, _mut39538 = false, _mut39539 = false, _mut39540 = false, _mut39541 = false, _mut39542 = false, _mut39543 = false, _mut39544 = false, _mut39545 = false, _mut39546 = false, _mut39547 = false, _mut39548 = false, _mut39549 = false, _mut39550 = false, _mut39551 = false, _mut39552 = false, _mut39553 = false, _mut39554 = false, _mut39555 = false, _mut39556 = false, _mut39557 = false, _mut39558 = false, _mut39559 = false, _mut39560 = false, _mut39561 = false, _mut39562 = false, _mut39563 = false, _mut39564 = false, _mut39565 = false, _mut39566 = false, _mut39567 = false, _mut39568 = false, _mut39569 = false, _mut39570 = false, _mut39571 = false, _mut39572 = false, _mut39573 = false, _mut39574 = false, _mut39575 = false, _mut39576 = false, _mut39577 = false, _mut39578 = false, _mut39579 = false, _mut39580 = false, _mut39581 = false, _mut39582 = false, _mut39583 = false, _mut39584 = false, _mut39585 = false, _mut39586 = false, _mut39587 = false, _mut39588 = false, _mut39589 = false, _mut39590 = false, _mut39591 = false, _mut39592 = false, _mut39593 = false, _mut39594 = false, _mut39595 = false, _mut39596 = false, _mut39597 = false, _mut39598 = false, _mut39599 = false, _mut39600 = false, _mut39601 = false, _mut39602 = false, _mut39603 = false, _mut39604 = false, _mut39605 = false, _mut39606 = false, _mut39607 = false, _mut39608 = false, _mut39609 = false, _mut39610 = false, _mut39611 = false, _mut39612 = false, _mut39613 = false, _mut39614 = false, _mut39615 = false, _mut39616 = false, _mut39617 = false, _mut39618 = false, _mut39619 = false, _mut39620 = false, _mut39621 = false, _mut39622 = false, _mut39623 = false, _mut39624 = false, _mut39625 = false, _mut39626 = false, _mut39627 = false, _mut39628 = false, _mut39629 = false, _mut39630 = false, _mut39631 = false, _mut39632 = false, _mut39633 = false, _mut39634 = false, _mut39635 = false, _mut39636 = false, _mut39637 = false, _mut39638 = false, _mut39639 = false, _mut39640 = false, _mut39641 = false, _mut39642 = false, _mut39643 = false, _mut39644 = false, _mut39645 = false, _mut39646 = false, _mut39647 = false, _mut39648 = false, _mut39649 = false, _mut39650 = false, _mut39651 = false, _mut39652 = false, _mut39653 = false, _mut39654 = false, _mut39655 = false, _mut39656 = false, _mut39657 = false, _mut39658 = false, _mut39659 = false, _mut39660 = false, _mut39661 = false, _mut39662 = false, _mut39663 = false, _mut39664 = false, _mut39665 = false, _mut39666 = false, _mut39667 = false, _mut39668 = false, _mut39669 = false, _mut39670 = false, _mut39671 = false, _mut39672 = false, _mut39673 = false, _mut39674 = false, _mut39675 = false, _mut39676 = false, _mut39677 = false, _mut39678 = false, _mut39679 = false, _mut39680 = false, _mut39681 = false, _mut39682 = false, _mut39683 = false, _mut39684 = false, _mut39685 = false, _mut39686 = false, _mut39687 = false, _mut39688 = false, _mut39689 = false, _mut39690 = false, _mut39691 = false, _mut39692 = false, _mut39693 = false, _mut39694 = false, _mut39695 = false, _mut39696 = false, _mut39697 = false, _mut39698 = false, _mut39699 = false, _mut39700 = false, _mut39701 = false, _mut39702 = false, _mut39703 = false, _mut39704 = false, _mut39705 = false, _mut39706 = false, _mut39707 = false, _mut39708 = false, _mut39709 = false, _mut39710 = false, _mut39711 = false, _mut39712 = false, _mut39713 = false, _mut39714 = false, _mut39715 = false, _mut39716 = false;

    /**
     * Parametric function to be fitted.
     */
    private static final HarmonicOscillator.Parametric FUNCTION = new HarmonicOscillator.Parametric();

    /**
     * Initial guess.
     */
    private final double[] initialGuess;

    /**
     * Maximum number of iterations of the optimization algorithm.
     */
    private final int maxIter;

    /**
     * Contructor used by the factory methods.
     *
     * @param initialGuess Initial guess. If set to {@code null}, the initial guess
     * will be estimated using the {@link ParameterGuesser}.
     * @param maxIter Maximum number of iterations of the optimization algorithm.
     */
    private HarmonicCurveFitter(double[] initialGuess, int maxIter) {
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    /**
     * Creates a default curve fitter.
     * The initial guess for the parameters will be {@link ParameterGuesser}
     * computed automatically, and the maximum number of iterations of the
     * optimization algorithm is set to {@link Integer#MAX_VALUE}.
     *
     * @return a curve fitter.
     *
     * @see #withStartPoint(double[])
     * @see #withMaxIterations(int)
     */
    public static HarmonicCurveFitter create() {
        return new HarmonicCurveFitter(null, Integer.MAX_VALUE);
    }

    /**
     * Configure the start point (initial guess).
     * @param newStart new start point (initial guess)
     * @return a new instance.
     */
    public HarmonicCurveFitter withStartPoint(double[] newStart) {
        return new HarmonicCurveFitter(newStart.clone(), maxIter);
    }

    /**
     * Configure the maximum number of iterations.
     * @param newMaxIter maximum number of iterations
     * @return a new instance.
     */
    public HarmonicCurveFitter withMaxIterations(int newMaxIter) {
        return new HarmonicCurveFitter(initialGuess, newMaxIter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        // Prepare least-squares problem.
        final int len = observations.size();
        final double[] target = new double[len];
        final double[] weights = new double[len];
        int i = 0;
        for (WeightedObservedPoint obs : observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.getProblem_106");
            target[i] = obs.getY();
            weights[i] = obs.getWeight();
            ++i;
        }
        final AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, observations);
        final double[] startPoint = initialGuess != null ? initialGuess : // Compute estimation.
        new ParameterGuesser(observations).guess();
        // observed points.
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(maxIter).start(startPoint).target(target).weight(new DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
    }

    /**
     * This class guesses harmonic coefficients from a sample.
     * <p>The algorithm used to guess the coefficients is as follows:</p>
     *
     * <p>We know \( f(t) \) at some sampling points \( t_i \) and want
     * to find \( a \), \( \omega \) and \( \phi \) such that
     * \( f(t) = a \cos (\omega t + \phi) \).
     * </p>
     *
     * <p>From the analytical expression, we can compute two primitives :
     * \[
     *     If2(t) = \int f^2 dt  = a^2 (t + S(t)) / 2
     * \]
     * \[
     *     If'2(t) = \int f'^2 dt = a^2 \omega^2 (t - S(t)) / 2
     * \]
     * where \(S(t) = \frac{\sin(2 (\omega t + \phi))}{2\omega}\)
     * </p>
     *
     * <p>We can remove \(S\) between these expressions :
     * \[
     *     If'2(t) = a^2 \omega^2 t - \omega^2 If2(t)
     * \]
     * </p>
     *
     * <p>The preceding expression shows that \(If'2 (t)\) is a linear
     * combination of both \(t\) and \(If2(t)\):
     * \[
     *   If'2(t) = A t + B If2(t)
     * \]
     * </p>
     *
     * <p>From the primitive, we can deduce the same form for definite
     * integrals between \(t_1\) and \(t_i\) for each \(t_i\) :
     * \[
     *   If2(t_i) - If2(t_1) = A (t_i - t_1) + B (If2 (t_i) - If2(t_1))
     * \]
     * </p>
     *
     * <p>We can find the coefficients \(A\) and \(B\) that best fit the sample
     * to this linear expression by computing the definite integrals for
     * each sample points.
     * </p>
     *
     * <p>For a bilinear expression \(z(x_i, y_i) = A x_i + B y_i\), the
     * coefficients \(A\) and \(B\) that minimize a least-squares criterion
     * \(\sum (z_i - z(x_i, y_i))^2\) are given by these expressions:</p>
     * \[
     *   A = \frac{\sum y_i y_i \sum x_i z_i - \sum x_i y_i \sum y_i z_i}
     *            {\sum x_i x_i \sum y_i y_i - \sum x_i y_i \sum x_i y_i}
     * \]
     * \[
     *   B = \frac{\sum x_i x_i \sum y_i z_i - \sum x_i y_i \sum x_i z_i}
     *            {\sum x_i x_i \sum y_i y_i - \sum x_i y_i \sum x_i y_i}
     *
     * \]
     *
     * <p>In fact, we can assume that both \(a\) and \(\omega\) are positive and
     * compute them directly, knowing that \(A = a^2 \omega^2\) and that
     * \(B = -\omega^2\). The complete algorithm is therefore:</p>
     *
     * For each \(t_i\) from \(t_1\) to \(t_{n-1}\), compute:
     * \[ f(t_i) \]
     * \[ f'(t_i) = \frac{f (t_{i+1}) - f(t_{i-1})}{t_{i+1} - t_{i-1}} \]
     * \[ x_i = t_i  - t_1 \]
     * \[ y_i = \int_{t_1}^{t_i} f^2(t) dt \]
     * \[ z_i = \int_{t_1}^{t_i} f'^2(t) dt \]
     * and update the sums:
     * \[ \sum x_i x_i, \sum y_i y_i, \sum x_i y_i, \sum x_i z_i, \sum y_i z_i \]
     *
     * Then:
     * \[
     *  a = \sqrt{\frac{\sum y_i y_i  \sum x_i z_i - \sum x_i y_i \sum y_i z_i }
     *                 {\sum x_i y_i  \sum x_i z_i - \sum x_i x_i \sum y_i z_i }}
     * \]
     * \[
     *  \omega = \sqrt{\frac{\sum x_i y_i \sum x_i z_i - \sum x_i x_i \sum y_i z_i}
     *                      {\sum x_i x_i \sum y_i y_i - \sum x_i y_i \sum x_i y_i}}
     * \]
     *
     * <p>Once we know \(\omega\) we can compute:
     * \[
     *    fc = \omega f(t) \cos(\omega t) - f'(t) \sin(\omega t)
     * \]
     * \[
     *    fs = \omega f(t) \sin(\omega t) + f'(t) \cos(\omega t)
     * \]
     * </p>
     *
     * <p>It appears that \(fc = a \omega \cos(\phi)\) and
     * \(fs = -a \omega \sin(\phi)\), so we can use these
     * expressions to compute \(\phi\). The best estimate over the sample is
     * given by averaging these expressions.
     * </p>
     *
     * <p>Since integrals and means are involved in the preceding
     * estimations, these operations run in \(O(n)\) time, where \(n\) is the
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
        public ParameterGuesser(Collection<WeightedObservedPoint> observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.ParameterGuesser_258");
            if (ROR_less(observations.size(), 4, "org.apache.commons.math3.fitting.HarmonicCurveFitter.ParameterGuesser_258", _mut39436, _mut39437, _mut39438, _mut39439, _mut39440)) {
                throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, observations.size(), 4, true);
            }
            final WeightedObservedPoint[] sorted = sortObservations(observations).toArray(new WeightedObservedPoint[0]);
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
        private List<WeightedObservedPoint> sortObservations(Collection<WeightedObservedPoint> unsorted) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294");
            final List<WeightedObservedPoint> observations = new ArrayList<WeightedObservedPoint>(unsorted);
            // elements in place. Insertion sort is very efficient in this case.
            WeightedObservedPoint curr = observations.get(0);
            final int len = observations.size();
            for (int j = 1; ROR_less(j, len, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39474, _mut39475, _mut39476, _mut39477, _mut39478); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294");
                WeightedObservedPoint prec = curr;
                curr = observations.get(j);
                if (ROR_less(curr.getX(), prec.getX(), "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39441, _mut39442, _mut39443, _mut39444, _mut39445)) {
                    // the current element should be inserted closer to the beginning
                    int i = AOR_minus(j, 1, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39446, _mut39447, _mut39448, _mut39449);
                    WeightedObservedPoint mI = observations.get(i);
                    while ((_mut39469 ? ((ROR_greater_equals(i, 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39459, _mut39460, _mut39461, _mut39462, _mut39463)) || (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39464, _mut39465, _mut39466, _mut39467, _mut39468))) : ((ROR_greater_equals(i, 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39459, _mut39460, _mut39461, _mut39462, _mut39463)) && (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39464, _mut39465, _mut39466, _mut39467, _mut39468))))) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294");
                        observations.set(AOR_plus(i, 1, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39450, _mut39451, _mut39452, _mut39453), mI);
                        if (ROR_not_equals(i--, 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39454, _mut39455, _mut39456, _mut39457, _mut39458)) {
                            mI = observations.get(i);
                        }
                    }
                    observations.set(AOR_plus(i, 1, "org.apache.commons.math3.fitting.HarmonicCurveFitter.sortObservations_294", _mut39470, _mut39471, _mut39472, _mut39473), curr);
                    curr = observations.get(j);
                }
            }
            return observations;
        }

        /**
         * Estimate a first guess of the amplitude and angular frequency.
         *
         * @param observations Observations, sorted w.r.t. abscissa.
         * @throws ZeroException if the abscissa range is zero.
         * @throws MathIllegalStateException when the guessing procedure cannot
         * produce sensible results.
         * @return the guessed amplitude (at index 0) and circular frequency
         * (at index 1).
         */
        private double[] guessAOmega(WeightedObservedPoint[] observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333");
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
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39547, _mut39548, _mut39549, _mut39550, _mut39551); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                // considering a linear model for f (and therefore constant f')
                final double dx = AOR_minus(currentX, previousX, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39479, _mut39480, _mut39481, _mut39482);
                final double dy = AOR_minus(currentY, previousY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39483, _mut39484, _mut39485, _mut39486);
                final double f2StepIntegral = AOR_divide(AOR_multiply(dx, (AOR_plus(AOR_plus(AOR_multiply(previousY, previousY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39487, _mut39488, _mut39489, _mut39490), AOR_multiply(previousY, currentY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39491, _mut39492, _mut39493, _mut39494), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39495, _mut39496, _mut39497, _mut39498), AOR_multiply(currentY, currentY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39499, _mut39500, _mut39501, _mut39502), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39503, _mut39504, _mut39505, _mut39506)), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39507, _mut39508, _mut39509, _mut39510), 3, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39511, _mut39512, _mut39513, _mut39514);
                final double fPrime2StepIntegral = AOR_divide(AOR_multiply(dy, dy, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39515, _mut39516, _mut39517, _mut39518), dx, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39519, _mut39520, _mut39521, _mut39522);
                final double x = AOR_minus(currentX, startX, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39523, _mut39524, _mut39525, _mut39526);
                f2Integral += f2StepIntegral;
                fPrime2Integral += fPrime2StepIntegral;
                sx2 += AOR_multiply(x, x, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39527, _mut39528, _mut39529, _mut39530);
                sy2 += AOR_multiply(f2Integral, f2Integral, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39531, _mut39532, _mut39533, _mut39534);
                sxy += AOR_multiply(x, f2Integral, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39535, _mut39536, _mut39537, _mut39538);
                sxz += AOR_multiply(x, fPrime2Integral, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39539, _mut39540, _mut39541, _mut39542);
                syz += AOR_multiply(f2Integral, fPrime2Integral, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39543, _mut39544, _mut39545, _mut39546);
            }
            // compute the amplitude and pulsation coefficients
            double c1 = AOR_minus(AOR_multiply(sy2, sxz, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39552, _mut39553, _mut39554, _mut39555), AOR_multiply(sxy, syz, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39556, _mut39557, _mut39558, _mut39559), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39560, _mut39561, _mut39562, _mut39563);
            double c2 = AOR_minus(AOR_multiply(sxy, sxz, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39564, _mut39565, _mut39566, _mut39567), AOR_multiply(sx2, syz, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39568, _mut39569, _mut39570, _mut39571), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39572, _mut39573, _mut39574, _mut39575);
            double c3 = AOR_minus(AOR_multiply(sx2, sy2, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39576, _mut39577, _mut39578, _mut39579), AOR_multiply(sxy, sxy, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39580, _mut39581, _mut39582, _mut39583), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39584, _mut39585, _mut39586, _mut39587);
            if ((_mut39606 ? ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39588, _mut39589, _mut39590, _mut39591), 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39592, _mut39593, _mut39594, _mut39595, _mut39596)) && (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39597, _mut39598, _mut39599, _mut39600), 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39601, _mut39602, _mut39603, _mut39604, _mut39605))) : ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39588, _mut39589, _mut39590, _mut39591), 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39592, _mut39593, _mut39594, _mut39595, _mut39596)) || (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39597, _mut39598, _mut39599, _mut39600), 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39601, _mut39602, _mut39603, _mut39604, _mut39605))))) {
                final int last = AOR_minus(observations.length, 1, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39620, _mut39621, _mut39622, _mut39623);
                // observations are sorted.
                final double xRange = AOR_minus(observations[last].getX(), observations[0].getX(), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39624, _mut39625, _mut39626, _mut39627);
                if (ROR_equals(xRange, 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39628, _mut39629, _mut39630, _mut39631, _mut39632)) {
                    throw new ZeroException();
                }
                aOmega[1] = AOR_divide(AOR_multiply(2, Math.PI, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39633, _mut39634, _mut39635, _mut39636), xRange, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39637, _mut39638, _mut39639, _mut39640);
                double yMin = Double.POSITIVE_INFINITY;
                double yMax = Double.NEGATIVE_INFINITY;
                for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39651, _mut39652, _mut39653, _mut39654, _mut39655); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333");
                    final double y = observations[i].getY();
                    if (ROR_less(y, yMin, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39641, _mut39642, _mut39643, _mut39644, _mut39645)) {
                        yMin = y;
                    }
                    if (ROR_greater(y, yMax, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39646, _mut39647, _mut39648, _mut39649, _mut39650)) {
                        yMax = y;
                    }
                }
                aOmega[0] = AOR_multiply(0.5, (AOR_minus(yMax, yMin, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39656, _mut39657, _mut39658, _mut39659)), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39660, _mut39661, _mut39662, _mut39663);
            } else {
                if (ROR_equals(c2, 0, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39607, _mut39608, _mut39609, _mut39610, _mut39611)) {
                    // procedure cannot produce sensible results.
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR);
                }
                aOmega[0] = FastMath.sqrt(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39612, _mut39613, _mut39614, _mut39615));
                aOmega[1] = FastMath.sqrt(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessAOmega_333", _mut39616, _mut39617, _mut39618, _mut39619));
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420");
            // initialize the means
            double fcMean = 0;
            double fsMean = 0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39712, _mut39713, _mut39714, _mut39715, _mut39716); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                final double currentYPrime = AOR_divide((AOR_minus(currentY, previousY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39664, _mut39665, _mut39666, _mut39667)), (AOR_minus(currentX, previousX, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39668, _mut39669, _mut39670, _mut39671)), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39672, _mut39673, _mut39674, _mut39675);
                double omegaX = AOR_multiply(omega, currentX, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39676, _mut39677, _mut39678, _mut39679);
                double cosine = FastMath.cos(omegaX);
                double sine = FastMath.sin(omegaX);
                fcMean += AOR_minus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39680, _mut39681, _mut39682, _mut39683), cosine, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39684, _mut39685, _mut39686, _mut39687), AOR_multiply(currentYPrime, sine, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39688, _mut39689, _mut39690, _mut39691), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39692, _mut39693, _mut39694, _mut39695);
                fsMean += AOR_plus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39696, _mut39697, _mut39698, _mut39699), sine, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39700, _mut39701, _mut39702, _mut39703), AOR_multiply(currentYPrime, cosine, "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39704, _mut39705, _mut39706, _mut39707), "org.apache.commons.math3.fitting.HarmonicCurveFitter.guessPhi_420", _mut39708, _mut39709, _mut39710, _mut39711);
            }
            return FastMath.atan2(-fsMean, fcMean);
        }
    }
}
