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
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * For a function defined on some interval {@code (lo, hi)}, this class
 * finds an approximation {@code x} to the point at which the function
 * attains its minimum.
 * It implements Richard Brent's algorithm (from his book "Algorithms for
 * Minimization without Derivatives", p. 79) for finding minima of real
 * univariate functions.
 * <br/>
 * This code is an adaptation, partly based on the Python code from SciPy
 * (module "optimize.py" v0.5); the original algorithm is also modified
 * <ul>
 *  <li>to use an initial guess provided by the user,</li>
 *  <li>to ensure that the best point encountered is the one returned.</li>
 * </ul>
 *
 * @since 2.0
 */
public class BrentOptimizer extends UnivariateOptimizer {

    @Conditional
    public static boolean _mut59154 = false, _mut59155 = false, _mut59156 = false, _mut59157 = false, _mut59158 = false, _mut59159 = false, _mut59160 = false, _mut59161 = false, _mut59162 = false, _mut59163 = false, _mut59164 = false, _mut59165 = false, _mut59166 = false, _mut59167 = false, _mut59168 = false, _mut59169 = false, _mut59170 = false, _mut59171 = false, _mut59172 = false, _mut59173 = false, _mut59174 = false, _mut59175 = false, _mut59176 = false, _mut59177 = false, _mut59178 = false, _mut59179 = false, _mut59180 = false, _mut59181 = false, _mut59182 = false, _mut59183 = false, _mut59184 = false, _mut59185 = false, _mut59186 = false, _mut59187 = false, _mut59188 = false, _mut59189 = false, _mut59190 = false, _mut59191 = false, _mut59192 = false, _mut59193 = false, _mut59194 = false, _mut59195 = false, _mut59196 = false, _mut59197 = false, _mut59198 = false, _mut59199 = false, _mut59200 = false, _mut59201 = false, _mut59202 = false, _mut59203 = false, _mut59204 = false, _mut59205 = false, _mut59206 = false, _mut59207 = false, _mut59208 = false, _mut59209 = false, _mut59210 = false, _mut59211 = false, _mut59212 = false, _mut59213 = false, _mut59214 = false, _mut59215 = false, _mut59216 = false, _mut59217 = false, _mut59218 = false, _mut59219 = false, _mut59220 = false, _mut59221 = false, _mut59222 = false, _mut59223 = false, _mut59224 = false, _mut59225 = false, _mut59226 = false, _mut59227 = false, _mut59228 = false, _mut59229 = false, _mut59230 = false, _mut59231 = false, _mut59232 = false, _mut59233 = false, _mut59234 = false, _mut59235 = false, _mut59236 = false, _mut59237 = false, _mut59238 = false, _mut59239 = false, _mut59240 = false, _mut59241 = false, _mut59242 = false, _mut59243 = false, _mut59244 = false, _mut59245 = false, _mut59246 = false, _mut59247 = false, _mut59248 = false, _mut59249 = false, _mut59250 = false, _mut59251 = false, _mut59252 = false, _mut59253 = false, _mut59254 = false, _mut59255 = false, _mut59256 = false, _mut59257 = false, _mut59258 = false, _mut59259 = false, _mut59260 = false, _mut59261 = false, _mut59262 = false, _mut59263 = false, _mut59264 = false, _mut59265 = false, _mut59266 = false, _mut59267 = false, _mut59268 = false, _mut59269 = false, _mut59270 = false, _mut59271 = false, _mut59272 = false, _mut59273 = false, _mut59274 = false, _mut59275 = false, _mut59276 = false, _mut59277 = false, _mut59278 = false, _mut59279 = false, _mut59280 = false, _mut59281 = false, _mut59282 = false, _mut59283 = false, _mut59284 = false, _mut59285 = false, _mut59286 = false, _mut59287 = false, _mut59288 = false, _mut59289 = false, _mut59290 = false, _mut59291 = false, _mut59292 = false, _mut59293 = false, _mut59294 = false, _mut59295 = false, _mut59296 = false, _mut59297 = false, _mut59298 = false, _mut59299 = false, _mut59300 = false, _mut59301 = false, _mut59302 = false, _mut59303 = false, _mut59304 = false, _mut59305 = false, _mut59306 = false, _mut59307 = false, _mut59308 = false, _mut59309 = false, _mut59310 = false, _mut59311 = false, _mut59312 = false, _mut59313 = false, _mut59314 = false, _mut59315 = false, _mut59316 = false, _mut59317 = false, _mut59318 = false, _mut59319 = false, _mut59320 = false, _mut59321 = false, _mut59322 = false, _mut59323 = false, _mut59324 = false, _mut59325 = false, _mut59326 = false, _mut59327 = false, _mut59328 = false, _mut59329 = false, _mut59330 = false, _mut59331 = false, _mut59332 = false, _mut59333 = false, _mut59334 = false, _mut59335 = false, _mut59336 = false, _mut59337 = false, _mut59338 = false, _mut59339 = false, _mut59340 = false, _mut59341 = false, _mut59342 = false, _mut59343 = false, _mut59344 = false, _mut59345 = false, _mut59346 = false, _mut59347 = false, _mut59348 = false, _mut59349 = false, _mut59350 = false, _mut59351 = false, _mut59352 = false, _mut59353 = false, _mut59354 = false, _mut59355 = false, _mut59356 = false, _mut59357 = false, _mut59358 = false, _mut59359 = false, _mut59360 = false, _mut59361 = false, _mut59362 = false, _mut59363 = false, _mut59364 = false, _mut59365 = false, _mut59366 = false, _mut59367 = false, _mut59368 = false, _mut59369 = false, _mut59370 = false, _mut59371 = false, _mut59372 = false, _mut59373 = false, _mut59374 = false, _mut59375 = false, _mut59376 = false, _mut59377 = false, _mut59378 = false, _mut59379 = false, _mut59380 = false, _mut59381 = false, _mut59382 = false, _mut59383 = false, _mut59384 = false, _mut59385 = false, _mut59386 = false, _mut59387 = false, _mut59388 = false, _mut59389 = false, _mut59390 = false, _mut59391 = false, _mut59392 = false, _mut59393 = false, _mut59394 = false, _mut59395 = false, _mut59396 = false, _mut59397 = false, _mut59398 = false, _mut59399 = false, _mut59400 = false, _mut59401 = false, _mut59402 = false, _mut59403 = false, _mut59404 = false, _mut59405 = false, _mut59406 = false, _mut59407 = false, _mut59408 = false, _mut59409 = false, _mut59410 = false, _mut59411 = false, _mut59412 = false, _mut59413 = false, _mut59414 = false, _mut59415 = false, _mut59416 = false, _mut59417 = false, _mut59418 = false, _mut59419 = false, _mut59420 = false, _mut59421 = false, _mut59422 = false, _mut59423 = false, _mut59424 = false, _mut59425 = false, _mut59426 = false, _mut59427 = false, _mut59428 = false, _mut59429 = false, _mut59430 = false, _mut59431 = false, _mut59432 = false, _mut59433 = false, _mut59434 = false, _mut59435 = false, _mut59436 = false, _mut59437 = false, _mut59438 = false, _mut59439 = false, _mut59440 = false, _mut59441 = false, _mut59442 = false, _mut59443 = false, _mut59444 = false, _mut59445 = false, _mut59446 = false, _mut59447 = false, _mut59448 = false, _mut59449 = false, _mut59450 = false, _mut59451 = false;

    /**
     * Golden section.
     */
    private static final double GOLDEN_SECTION = AOR_multiply(0.5, (AOR_minus(3, FastMath.sqrt(5), "org.apache.commons.math3.optim.univariate.BrentOptimizer.eval_282", _mut59154, _mut59155, _mut59156, _mut59157)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.eval_282", _mut59158, _mut59159, _mut59160, _mut59161);

    /**
     * Minimum relative tolerance.
     */
    private static final double MIN_RELATIVE_TOLERANCE = AOR_multiply(2, FastMath.ulp(1d), "org.apache.commons.math3.optim.univariate.BrentOptimizer.eval_282", _mut59162, _mut59163, _mut59164, _mut59165);

    /**
     * Relative threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute threshold.
     */
    private final double absoluteThreshold;

    /**
     * The arguments are used implement the original stopping criterion
     * of Brent's algorithm.
     * {@code abs} and {@code rel} define a tolerance
     * {@code tol = rel |x| + abs}. {@code rel} should be no smaller than
     * <em>2 macheps</em> and preferably not much less than <em>sqrt(macheps)</em>,
     * where <em>macheps</em> is the relative machine precision. {@code abs} must
     * be positive.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param checker Additional, user-defined, convergence checking
     * procedure.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public BrentOptimizer(double rel, double abs, ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BrentOptimizer.BrentOptimizer_77");
        if (ROR_less(rel, MIN_RELATIVE_TOLERANCE, "org.apache.commons.math3.optim.univariate.BrentOptimizer.BrentOptimizer_77", _mut59166, _mut59167, _mut59168, _mut59169, _mut59170)) {
            throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
        }
        if (ROR_less_equals(abs, 0, "org.apache.commons.math3.optim.univariate.BrentOptimizer.BrentOptimizer_77", _mut59171, _mut59172, _mut59173, _mut59174, _mut59175)) {
            throw new NotStrictlyPositiveException(abs);
        }
        relativeThreshold = rel;
        absoluteThreshold = abs;
    }

    /**
     * The arguments are used for implementing the original stopping criterion
     * of Brent's algorithm.
     * {@code abs} and {@code rel} define a tolerance
     * {@code tol = rel |x| + abs}. {@code rel} should be no smaller than
     * <em>2 macheps</em> and preferably not much less than <em>sqrt(macheps)</em>,
     * where <em>macheps</em> is the relative machine precision. {@code abs} must
     * be positive.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public BrentOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UnivariatePointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113");
        final boolean isMinim = getGoalType() == GoalType.MINIMIZE;
        final double lo = getMin();
        final double mid = getStartValue();
        final double hi = getMax();
        // Optional additional convergence criteria.
        final ConvergenceChecker<UnivariatePointValuePair> checker = getConvergenceChecker();
        double a;
        double b;
        if (ROR_less(lo, hi, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59176, _mut59177, _mut59178, _mut59179, _mut59180)) {
            a = lo;
            b = hi;
        } else {
            a = hi;
            b = lo;
        }
        double x = mid;
        double v = x;
        double w = x;
        double d = 0;
        double e = 0;
        double fx = computeObjectiveValue(x);
        if (!isMinim) {
            fx = -fx;
        }
        double fv = fx;
        double fw = fx;
        UnivariatePointValuePair previous = null;
        UnivariatePointValuePair current = new UnivariatePointValuePair(x, isMinim ? fx : -fx);
        // Best point encountered so far (which is the initial guess).
        UnivariatePointValuePair best = current;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113");
            final double m = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59181, _mut59182, _mut59183, _mut59184)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59185, _mut59186, _mut59187, _mut59188);
            final double tol1 = AOR_plus(AOR_multiply(relativeThreshold, FastMath.abs(x), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59189, _mut59190, _mut59191, _mut59192), absoluteThreshold, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59193, _mut59194, _mut59195, _mut59196);
            final double tol2 = AOR_multiply(2, tol1, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59197, _mut59198, _mut59199, _mut59200);
            // Default stopping criterion.
            final boolean stop = ROR_less_equals(FastMath.abs(AOR_minus(x, m, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59201, _mut59202, _mut59203, _mut59204)), AOR_minus(tol2, AOR_multiply(0.5, (AOR_minus(b, a, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59205, _mut59206, _mut59207, _mut59208)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59209, _mut59210, _mut59211, _mut59212), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59213, _mut59214, _mut59215, _mut59216), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59217, _mut59218, _mut59219, _mut59220, _mut59221);
            if (!stop) {
                double p = 0;
                double q = 0;
                double r = 0;
                double u = 0;
                if (ROR_greater(FastMath.abs(e), tol1, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59222, _mut59223, _mut59224, _mut59225, _mut59226)) {
                    // Fit parabola.
                    r = AOR_multiply((AOR_minus(x, w, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59244, _mut59245, _mut59246, _mut59247)), (AOR_minus(fx, fv, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59248, _mut59249, _mut59250, _mut59251)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59252, _mut59253, _mut59254, _mut59255);
                    q = AOR_multiply((AOR_minus(x, v, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59256, _mut59257, _mut59258, _mut59259)), (AOR_minus(fx, fw, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59260, _mut59261, _mut59262, _mut59263)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59264, _mut59265, _mut59266, _mut59267);
                    p = AOR_minus(AOR_multiply((AOR_minus(x, v, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59268, _mut59269, _mut59270, _mut59271)), q, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59272, _mut59273, _mut59274, _mut59275), AOR_multiply((AOR_minus(x, w, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59276, _mut59277, _mut59278, _mut59279)), r, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59280, _mut59281, _mut59282, _mut59283), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59284, _mut59285, _mut59286, _mut59287);
                    q = AOR_multiply(2, (AOR_minus(q, r, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59288, _mut59289, _mut59290, _mut59291)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59292, _mut59293, _mut59294, _mut59295);
                    if (ROR_greater(q, 0, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59296, _mut59297, _mut59298, _mut59299, _mut59300)) {
                        p = -p;
                    } else {
                        q = -q;
                    }
                    r = e;
                    e = d;
                    if ((_mut59341 ? ((_mut59327 ? (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59301, _mut59302, _mut59303, _mut59304)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59305, _mut59306, _mut59307, _mut59308), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59309, _mut59310, _mut59311, _mut59312, _mut59313) || ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59314, _mut59315, _mut59316, _mut59317)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59318, _mut59319, _mut59320, _mut59321), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59322, _mut59323, _mut59324, _mut59325, _mut59326)) : (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59301, _mut59302, _mut59303, _mut59304)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59305, _mut59306, _mut59307, _mut59308), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59309, _mut59310, _mut59311, _mut59312, _mut59313) && ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59314, _mut59315, _mut59316, _mut59317)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59318, _mut59319, _mut59320, _mut59321), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59322, _mut59323, _mut59324, _mut59325, _mut59326))) || ROR_less(FastMath.abs(p), FastMath.abs(AOR_multiply(AOR_multiply(0.5, q, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59328, _mut59329, _mut59330, _mut59331), r, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59332, _mut59333, _mut59334, _mut59335)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59336, _mut59337, _mut59338, _mut59339, _mut59340)) : ((_mut59327 ? (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59301, _mut59302, _mut59303, _mut59304)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59305, _mut59306, _mut59307, _mut59308), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59309, _mut59310, _mut59311, _mut59312, _mut59313) || ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59314, _mut59315, _mut59316, _mut59317)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59318, _mut59319, _mut59320, _mut59321), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59322, _mut59323, _mut59324, _mut59325, _mut59326)) : (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59301, _mut59302, _mut59303, _mut59304)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59305, _mut59306, _mut59307, _mut59308), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59309, _mut59310, _mut59311, _mut59312, _mut59313) && ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59314, _mut59315, _mut59316, _mut59317)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59318, _mut59319, _mut59320, _mut59321), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59322, _mut59323, _mut59324, _mut59325, _mut59326))) && ROR_less(FastMath.abs(p), FastMath.abs(AOR_multiply(AOR_multiply(0.5, q, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59328, _mut59329, _mut59330, _mut59331), r, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59332, _mut59333, _mut59334, _mut59335)), "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59336, _mut59337, _mut59338, _mut59339, _mut59340)))) {
                        // Parabolic interpolation step.
                        d = AOR_divide(p, q, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59359, _mut59360, _mut59361, _mut59362);
                        u = AOR_plus(x, d, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59363, _mut59364, _mut59365, _mut59366);
                        // f must not be evaluated too close to a or b.
                        if ((_mut59385 ? (ROR_less(AOR_minus(u, a, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59367, _mut59368, _mut59369, _mut59370), tol2, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59371, _mut59372, _mut59373, _mut59374, _mut59375) && ROR_less(AOR_minus(b, u, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59376, _mut59377, _mut59378, _mut59379), tol2, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59380, _mut59381, _mut59382, _mut59383, _mut59384)) : (ROR_less(AOR_minus(u, a, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59367, _mut59368, _mut59369, _mut59370), tol2, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59371, _mut59372, _mut59373, _mut59374, _mut59375) || ROR_less(AOR_minus(b, u, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59376, _mut59377, _mut59378, _mut59379), tol2, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59380, _mut59381, _mut59382, _mut59383, _mut59384)))) {
                            if (ROR_less_equals(x, m, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59386, _mut59387, _mut59388, _mut59389, _mut59390)) {
                                d = tol1;
                            } else {
                                d = -tol1;
                            }
                        }
                    } else {
                        // Golden section step.
                        if (ROR_less(x, m, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59342, _mut59343, _mut59344, _mut59345, _mut59346)) {
                            e = AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59351, _mut59352, _mut59353, _mut59354);
                        } else {
                            e = AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59347, _mut59348, _mut59349, _mut59350);
                        }
                        d = AOR_multiply(GOLDEN_SECTION, e, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59355, _mut59356, _mut59357, _mut59358);
                    }
                } else {
                    // Golden section step.
                    if (ROR_less(x, m, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59227, _mut59228, _mut59229, _mut59230, _mut59231)) {
                        e = AOR_minus(b, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59236, _mut59237, _mut59238, _mut59239);
                    } else {
                        e = AOR_minus(a, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59232, _mut59233, _mut59234, _mut59235);
                    }
                    d = AOR_multiply(GOLDEN_SECTION, e, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59240, _mut59241, _mut59242, _mut59243);
                }
                // Update by at least "tol1".
                if (ROR_less(FastMath.abs(d), tol1, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59391, _mut59392, _mut59393, _mut59394, _mut59395)) {
                    if (ROR_greater_equals(d, 0, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59400, _mut59401, _mut59402, _mut59403, _mut59404)) {
                        u = AOR_plus(x, tol1, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59409, _mut59410, _mut59411, _mut59412);
                    } else {
                        u = AOR_minus(x, tol1, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59405, _mut59406, _mut59407, _mut59408);
                    }
                } else {
                    u = AOR_plus(x, d, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59396, _mut59397, _mut59398, _mut59399);
                }
                double fu = computeObjectiveValue(u);
                if (!isMinim) {
                    fu = -fu;
                }
                // User-defined convergence checker.
                previous = current;
                current = new UnivariatePointValuePair(u, isMinim ? fu : -fu);
                best = best(best, best(previous, current, isMinim), isMinim);
                if ((_mut59413 ? (checker != null || checker.converged(getIterations(), previous, current)) : (checker != null && checker.converged(getIterations(), previous, current)))) {
                    return best;
                }
                // Update a, b, v, w and x.
                if (ROR_less_equals(fu, fx, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59414, _mut59415, _mut59416, _mut59417, _mut59418)) {
                    if (ROR_less(u, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59437, _mut59438, _mut59439, _mut59440, _mut59441)) {
                        b = x;
                    } else {
                        a = x;
                    }
                    v = w;
                    fv = fw;
                    w = x;
                    fw = fx;
                    x = u;
                    fx = fu;
                } else {
                    if (ROR_less(u, x, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59419, _mut59420, _mut59421, _mut59422, _mut59423)) {
                        a = u;
                    } else {
                        b = u;
                    }
                    if ((_mut59429 ? (ROR_less_equals(fu, fw, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59424, _mut59425, _mut59426, _mut59427, _mut59428) && Precision.equals(w, x)) : (ROR_less_equals(fu, fw, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59424, _mut59425, _mut59426, _mut59427, _mut59428) || Precision.equals(w, x)))) {
                        v = w;
                        fv = fw;
                        w = u;
                        fw = fu;
                    } else if ((_mut59436 ? ((_mut59435 ? (ROR_less_equals(fu, fv, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59430, _mut59431, _mut59432, _mut59433, _mut59434) && Precision.equals(v, x)) : (ROR_less_equals(fu, fv, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59430, _mut59431, _mut59432, _mut59433, _mut59434) || Precision.equals(v, x))) && Precision.equals(v, w)) : ((_mut59435 ? (ROR_less_equals(fu, fv, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59430, _mut59431, _mut59432, _mut59433, _mut59434) && Precision.equals(v, x)) : (ROR_less_equals(fu, fv, "org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize_113", _mut59430, _mut59431, _mut59432, _mut59433, _mut59434) || Precision.equals(v, x))) || Precision.equals(v, w)))) {
                        v = u;
                        fv = fu;
                    }
                }
            } else {
                // Default termination (Brent's criterion).
                return best(best, best(previous, current, isMinim), isMinim);
            }
            incrementIterationCount();
        }
    }

    /**
     * Selects the best of two points.
     *
     * @param a Point and value.
     * @param b Point and value.
     * @param isMinim {@code true} if the selected point must be the one with
     * the lowest value.
     * @return the best point, or {@code null} if {@code a} and {@code b} are
     * both {@code null}. When {@code a} and {@code b} have the same function
     * value, {@code a} is returned.
     */
    private UnivariatePointValuePair best(UnivariatePointValuePair a, UnivariatePointValuePair b, boolean isMinim) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BrentOptimizer.best_298");
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (isMinim) {
            return ROR_less_equals(a.getValue(), b.getValue(), "org.apache.commons.math3.optim.univariate.BrentOptimizer.best_298", _mut59447, _mut59448, _mut59449, _mut59450, _mut59451) ? a : b;
        } else {
            return ROR_greater_equals(a.getValue(), b.getValue(), "org.apache.commons.math3.optim.univariate.BrentOptimizer.best_298", _mut59442, _mut59443, _mut59444, _mut59445, _mut59446) ? a : b;
        }
    }
}
