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

import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
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
 * @since 2.0
 * @deprecated As of 3.3. Please use {@link HarmonicCurveFitter} and
 * {@link WeightedObservedPoints} instead.
 */
@Deprecated
public class HarmonicFitter extends CurveFitter<HarmonicOscillator.Parametric> {

    @Conditional
    public static boolean _mut39145 = false, _mut39146 = false, _mut39147 = false, _mut39148 = false, _mut39149 = false, _mut39150 = false, _mut39151 = false, _mut39152 = false, _mut39153 = false, _mut39154 = false, _mut39155 = false, _mut39156 = false, _mut39157 = false, _mut39158 = false, _mut39159 = false, _mut39160 = false, _mut39161 = false, _mut39162 = false, _mut39163 = false, _mut39164 = false, _mut39165 = false, _mut39166 = false, _mut39167 = false, _mut39168 = false, _mut39169 = false, _mut39170 = false, _mut39171 = false, _mut39172 = false, _mut39173 = false, _mut39174 = false, _mut39175 = false, _mut39176 = false, _mut39177 = false, _mut39178 = false, _mut39179 = false, _mut39180 = false, _mut39181 = false, _mut39182 = false, _mut39183 = false, _mut39184 = false, _mut39185 = false, _mut39186 = false, _mut39187 = false, _mut39188 = false, _mut39189 = false, _mut39190 = false, _mut39191 = false, _mut39192 = false, _mut39193 = false, _mut39194 = false, _mut39195 = false, _mut39196 = false, _mut39197 = false, _mut39198 = false, _mut39199 = false, _mut39200 = false, _mut39201 = false, _mut39202 = false, _mut39203 = false, _mut39204 = false, _mut39205 = false, _mut39206 = false, _mut39207 = false, _mut39208 = false, _mut39209 = false, _mut39210 = false, _mut39211 = false, _mut39212 = false, _mut39213 = false, _mut39214 = false, _mut39215 = false, _mut39216 = false, _mut39217 = false, _mut39218 = false, _mut39219 = false, _mut39220 = false, _mut39221 = false, _mut39222 = false, _mut39223 = false, _mut39224 = false, _mut39225 = false, _mut39226 = false, _mut39227 = false, _mut39228 = false, _mut39229 = false, _mut39230 = false, _mut39231 = false, _mut39232 = false, _mut39233 = false, _mut39234 = false, _mut39235 = false, _mut39236 = false, _mut39237 = false, _mut39238 = false, _mut39239 = false, _mut39240 = false, _mut39241 = false, _mut39242 = false, _mut39243 = false, _mut39244 = false, _mut39245 = false, _mut39246 = false, _mut39247 = false, _mut39248 = false, _mut39249 = false, _mut39250 = false, _mut39251 = false, _mut39252 = false, _mut39253 = false, _mut39254 = false, _mut39255 = false, _mut39256 = false, _mut39257 = false, _mut39258 = false, _mut39259 = false, _mut39260 = false, _mut39261 = false, _mut39262 = false, _mut39263 = false, _mut39264 = false, _mut39265 = false, _mut39266 = false, _mut39267 = false, _mut39268 = false, _mut39269 = false, _mut39270 = false, _mut39271 = false, _mut39272 = false, _mut39273 = false, _mut39274 = false, _mut39275 = false, _mut39276 = false, _mut39277 = false, _mut39278 = false, _mut39279 = false, _mut39280 = false, _mut39281 = false, _mut39282 = false, _mut39283 = false, _mut39284 = false, _mut39285 = false, _mut39286 = false, _mut39287 = false, _mut39288 = false, _mut39289 = false, _mut39290 = false, _mut39291 = false, _mut39292 = false, _mut39293 = false, _mut39294 = false, _mut39295 = false, _mut39296 = false, _mut39297 = false, _mut39298 = false, _mut39299 = false, _mut39300 = false, _mut39301 = false, _mut39302 = false, _mut39303 = false, _mut39304 = false, _mut39305 = false, _mut39306 = false, _mut39307 = false, _mut39308 = false, _mut39309 = false, _mut39310 = false, _mut39311 = false, _mut39312 = false, _mut39313 = false, _mut39314 = false, _mut39315 = false, _mut39316 = false, _mut39317 = false, _mut39318 = false, _mut39319 = false, _mut39320 = false, _mut39321 = false, _mut39322 = false, _mut39323 = false, _mut39324 = false, _mut39325 = false, _mut39326 = false, _mut39327 = false, _mut39328 = false, _mut39329 = false, _mut39330 = false, _mut39331 = false, _mut39332 = false, _mut39333 = false, _mut39334 = false, _mut39335 = false, _mut39336 = false, _mut39337 = false, _mut39338 = false, _mut39339 = false, _mut39340 = false, _mut39341 = false, _mut39342 = false, _mut39343 = false, _mut39344 = false, _mut39345 = false, _mut39346 = false, _mut39347 = false, _mut39348 = false, _mut39349 = false, _mut39350 = false, _mut39351 = false, _mut39352 = false, _mut39353 = false, _mut39354 = false, _mut39355 = false, _mut39356 = false, _mut39357 = false, _mut39358 = false, _mut39359 = false, _mut39360 = false, _mut39361 = false, _mut39362 = false, _mut39363 = false, _mut39364 = false, _mut39365 = false, _mut39366 = false, _mut39367 = false, _mut39368 = false, _mut39369 = false, _mut39370 = false, _mut39371 = false, _mut39372 = false, _mut39373 = false, _mut39374 = false, _mut39375 = false, _mut39376 = false, _mut39377 = false, _mut39378 = false, _mut39379 = false, _mut39380 = false, _mut39381 = false, _mut39382 = false, _mut39383 = false, _mut39384 = false, _mut39385 = false, _mut39386 = false, _mut39387 = false, _mut39388 = false, _mut39389 = false, _mut39390 = false, _mut39391 = false, _mut39392 = false, _mut39393 = false, _mut39394 = false, _mut39395 = false, _mut39396 = false, _mut39397 = false, _mut39398 = false, _mut39399 = false, _mut39400 = false, _mut39401 = false, _mut39402 = false, _mut39403 = false, _mut39404 = false, _mut39405 = false, _mut39406 = false, _mut39407 = false, _mut39408 = false, _mut39409 = false, _mut39410 = false, _mut39411 = false, _mut39412 = false, _mut39413 = false, _mut39414 = false, _mut39415 = false, _mut39416 = false, _mut39417 = false, _mut39418 = false, _mut39419 = false, _mut39420 = false, _mut39421 = false, _mut39422 = false, _mut39423 = false, _mut39424 = false, _mut39425 = false;

    /**
     * Simple constructor.
     * @param optimizer Optimizer to use for the fitting.
     */
    public HarmonicFitter(final MultivariateVectorOptimizer optimizer) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.ParameterGuesser_197");
            if (ROR_less(observations.length, 4, "org.apache.commons.math3.fitting.HarmonicFitter.ParameterGuesser_197", _mut39145, _mut39146, _mut39147, _mut39148, _mut39149)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232");
            final WeightedObservedPoint[] observations = unsorted.clone();
            // elements in place. Insertion sort is very efficient in this case.
            WeightedObservedPoint curr = observations[0];
            for (int j = 1; ROR_less(j, observations.length, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39183, _mut39184, _mut39185, _mut39186, _mut39187); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232");
                WeightedObservedPoint prec = curr;
                curr = observations[j];
                if (ROR_less(curr.getX(), prec.getX(), "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39150, _mut39151, _mut39152, _mut39153, _mut39154)) {
                    // the current element should be inserted closer to the beginning
                    int i = AOR_minus(j, 1, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39155, _mut39156, _mut39157, _mut39158);
                    WeightedObservedPoint mI = observations[i];
                    while ((_mut39178 ? ((ROR_greater_equals(i, 0, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39168, _mut39169, _mut39170, _mut39171, _mut39172)) || (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39173, _mut39174, _mut39175, _mut39176, _mut39177))) : ((ROR_greater_equals(i, 0, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39168, _mut39169, _mut39170, _mut39171, _mut39172)) && (ROR_less(curr.getX(), mI.getX(), "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39173, _mut39174, _mut39175, _mut39176, _mut39177))))) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232");
                        observations[AOR_plus(i, 1, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39159, _mut39160, _mut39161, _mut39162)] = mI;
                        if (ROR_not_equals(i--, 0, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39163, _mut39164, _mut39165, _mut39166, _mut39167)) {
                            mI = observations[i];
                        }
                    }
                    observations[AOR_plus(i, 1, "org.apache.commons.math3.fitting.HarmonicFitter.sortObservations_232", _mut39179, _mut39180, _mut39181, _mut39182)] = curr;
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272");
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
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39256, _mut39257, _mut39258, _mut39259, _mut39260); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                // considering a linear model for f (and therefore constant f')
                final double dx = AOR_minus(currentX, previousX, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39188, _mut39189, _mut39190, _mut39191);
                final double dy = AOR_minus(currentY, previousY, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39192, _mut39193, _mut39194, _mut39195);
                final double f2StepIntegral = AOR_divide(AOR_multiply(dx, (AOR_plus(AOR_plus(AOR_multiply(previousY, previousY, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39196, _mut39197, _mut39198, _mut39199), AOR_multiply(previousY, currentY, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39200, _mut39201, _mut39202, _mut39203), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39204, _mut39205, _mut39206, _mut39207), AOR_multiply(currentY, currentY, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39208, _mut39209, _mut39210, _mut39211), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39212, _mut39213, _mut39214, _mut39215)), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39216, _mut39217, _mut39218, _mut39219), 3, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39220, _mut39221, _mut39222, _mut39223);
                final double fPrime2StepIntegral = AOR_divide(AOR_multiply(dy, dy, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39224, _mut39225, _mut39226, _mut39227), dx, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39228, _mut39229, _mut39230, _mut39231);
                final double x = AOR_minus(currentX, startX, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39232, _mut39233, _mut39234, _mut39235);
                f2Integral += f2StepIntegral;
                fPrime2Integral += fPrime2StepIntegral;
                sx2 += AOR_multiply(x, x, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39236, _mut39237, _mut39238, _mut39239);
                sy2 += AOR_multiply(f2Integral, f2Integral, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39240, _mut39241, _mut39242, _mut39243);
                sxy += AOR_multiply(x, f2Integral, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39244, _mut39245, _mut39246, _mut39247);
                sxz += AOR_multiply(x, fPrime2Integral, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39248, _mut39249, _mut39250, _mut39251);
                syz += AOR_multiply(f2Integral, fPrime2Integral, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39252, _mut39253, _mut39254, _mut39255);
            }
            // compute the amplitude and pulsation coefficients
            double c1 = AOR_minus(AOR_multiply(sy2, sxz, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39261, _mut39262, _mut39263, _mut39264), AOR_multiply(sxy, syz, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39265, _mut39266, _mut39267, _mut39268), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39269, _mut39270, _mut39271, _mut39272);
            double c2 = AOR_minus(AOR_multiply(sxy, sxz, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39273, _mut39274, _mut39275, _mut39276), AOR_multiply(sx2, syz, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39277, _mut39278, _mut39279, _mut39280), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39281, _mut39282, _mut39283, _mut39284);
            double c3 = AOR_minus(AOR_multiply(sx2, sy2, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39285, _mut39286, _mut39287, _mut39288), AOR_multiply(sxy, sxy, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39289, _mut39290, _mut39291, _mut39292), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39293, _mut39294, _mut39295, _mut39296);
            if ((_mut39315 ? ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39297, _mut39298, _mut39299, _mut39300), 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39301, _mut39302, _mut39303, _mut39304, _mut39305)) && (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39306, _mut39307, _mut39308, _mut39309), 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39310, _mut39311, _mut39312, _mut39313, _mut39314))) : ((ROR_less(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39297, _mut39298, _mut39299, _mut39300), 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39301, _mut39302, _mut39303, _mut39304, _mut39305)) || (ROR_less(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39306, _mut39307, _mut39308, _mut39309), 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39310, _mut39311, _mut39312, _mut39313, _mut39314))))) {
                final int last = AOR_minus(observations.length, 1, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39329, _mut39330, _mut39331, _mut39332);
                // observations are sorted.
                final double xRange = AOR_minus(observations[last].getX(), observations[0].getX(), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39333, _mut39334, _mut39335, _mut39336);
                if (ROR_equals(xRange, 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39337, _mut39338, _mut39339, _mut39340, _mut39341)) {
                    throw new ZeroException();
                }
                aOmega[1] = AOR_divide(AOR_multiply(2, Math.PI, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39342, _mut39343, _mut39344, _mut39345), xRange, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39346, _mut39347, _mut39348, _mut39349);
                double yMin = Double.POSITIVE_INFINITY;
                double yMax = Double.NEGATIVE_INFINITY;
                for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39360, _mut39361, _mut39362, _mut39363, _mut39364); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272");
                    final double y = observations[i].getY();
                    if (ROR_less(y, yMin, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39350, _mut39351, _mut39352, _mut39353, _mut39354)) {
                        yMin = y;
                    }
                    if (ROR_greater(y, yMax, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39355, _mut39356, _mut39357, _mut39358, _mut39359)) {
                        yMax = y;
                    }
                }
                aOmega[0] = AOR_multiply(0.5, (AOR_minus(yMax, yMin, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39365, _mut39366, _mut39367, _mut39368)), "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39369, _mut39370, _mut39371, _mut39372);
            } else {
                if (ROR_equals(c2, 0, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39316, _mut39317, _mut39318, _mut39319, _mut39320)) {
                    // procedure cannot produce sensible results.
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR);
                }
                aOmega[0] = FastMath.sqrt(AOR_divide(c1, c2, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39321, _mut39322, _mut39323, _mut39324));
                aOmega[1] = FastMath.sqrt(AOR_divide(c2, c3, "org.apache.commons.math3.fitting.HarmonicFitter.guessAOmega_272", _mut39325, _mut39326, _mut39327, _mut39328));
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359");
            // initialize the means
            double fcMean = 0;
            double fsMean = 0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            for (int i = 1; ROR_less(i, observations.length, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39421, _mut39422, _mut39423, _mut39424, _mut39425); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359");
                // one step forward
                final double previousX = currentX;
                final double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                final double currentYPrime = AOR_divide((AOR_minus(currentY, previousY, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39373, _mut39374, _mut39375, _mut39376)), (AOR_minus(currentX, previousX, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39377, _mut39378, _mut39379, _mut39380)), "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39381, _mut39382, _mut39383, _mut39384);
                double omegaX = AOR_multiply(omega, currentX, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39385, _mut39386, _mut39387, _mut39388);
                double cosine = FastMath.cos(omegaX);
                double sine = FastMath.sin(omegaX);
                fcMean += AOR_minus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39389, _mut39390, _mut39391, _mut39392), cosine, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39393, _mut39394, _mut39395, _mut39396), AOR_multiply(currentYPrime, sine, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39397, _mut39398, _mut39399, _mut39400), "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39401, _mut39402, _mut39403, _mut39404);
                fsMean += AOR_plus(AOR_multiply(AOR_multiply(omega, currentY, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39405, _mut39406, _mut39407, _mut39408), sine, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39409, _mut39410, _mut39411, _mut39412), AOR_multiply(currentYPrime, cosine, "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39413, _mut39414, _mut39415, _mut39416), "org.apache.commons.math3.fitting.HarmonicFitter.guessPhi_359", _mut39417, _mut39418, _mut39419, _mut39420);
            }
            return FastMath.atan2(-fsMean, fcMean);
        }
    }
}
