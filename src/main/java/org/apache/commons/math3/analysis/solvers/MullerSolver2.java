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
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the <a href="http://mathworld.wolfram.com/MullersMethod.html">
 * Muller's Method</a> for root finding of real univariate functions. For
 * reference, see <b>Elementary Numerical Analysis</b>, ISBN 0070124477,
 * chapter 3.
 * <p>
 * Muller's method applies to both real and complex functions, but here we
 * restrict ourselves to real functions.
 * This class differs from {@link MullerSolver} in the way it avoids complex
 * operations.</p><p>
 * Except for the initial [min, max], it does not require bracketing
 * condition, e.g. f(x0), f(x1), f(x2) can have the same sign. If a complex
 * number arises in the computation, we simply use its modulus as a real
 * approximation.</p>
 * <p>
 * Because the interval may not be bracketing, the bisection alternative is
 * not applicable here. However in practice our treatment usually works
 * well, especially near real zeroes where the imaginary part of the complex
 * approximation is often negligible.</p>
 * <p>
 * The formulas here do not use divided differences directly.</p>
 *
 * @since 1.2
 * @see MullerSolver
 */
public class MullerSolver2 extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut100142 = false, _mut100143 = false, _mut100144 = false, _mut100145 = false, _mut100146 = false, _mut100147 = false, _mut100148 = false, _mut100149 = false, _mut100150 = false, _mut100151 = false, _mut100152 = false, _mut100153 = false, _mut100154 = false, _mut100155 = false, _mut100156 = false, _mut100157 = false, _mut100158 = false, _mut100159 = false, _mut100160 = false, _mut100161 = false, _mut100162 = false, _mut100163 = false, _mut100164 = false, _mut100165 = false, _mut100166 = false, _mut100167 = false, _mut100168 = false, _mut100169 = false, _mut100170 = false, _mut100171 = false, _mut100172 = false, _mut100173 = false, _mut100174 = false, _mut100175 = false, _mut100176 = false, _mut100177 = false, _mut100178 = false, _mut100179 = false, _mut100180 = false, _mut100181 = false, _mut100182 = false, _mut100183 = false, _mut100184 = false, _mut100185 = false, _mut100186 = false, _mut100187 = false, _mut100188 = false, _mut100189 = false, _mut100190 = false, _mut100191 = false, _mut100192 = false, _mut100193 = false, _mut100194 = false, _mut100195 = false, _mut100196 = false, _mut100197 = false, _mut100198 = false, _mut100199 = false, _mut100200 = false, _mut100201 = false, _mut100202 = false, _mut100203 = false, _mut100204 = false, _mut100205 = false, _mut100206 = false, _mut100207 = false, _mut100208 = false, _mut100209 = false, _mut100210 = false, _mut100211 = false, _mut100212 = false, _mut100213 = false, _mut100214 = false, _mut100215 = false, _mut100216 = false, _mut100217 = false, _mut100218 = false, _mut100219 = false, _mut100220 = false, _mut100221 = false, _mut100222 = false, _mut100223 = false, _mut100224 = false, _mut100225 = false, _mut100226 = false, _mut100227 = false, _mut100228 = false, _mut100229 = false, _mut100230 = false, _mut100231 = false, _mut100232 = false, _mut100233 = false, _mut100234 = false, _mut100235 = false, _mut100236 = false, _mut100237 = false, _mut100238 = false, _mut100239 = false, _mut100240 = false, _mut100241 = false, _mut100242 = false, _mut100243 = false, _mut100244 = false, _mut100245 = false, _mut100246 = false, _mut100247 = false, _mut100248 = false, _mut100249 = false, _mut100250 = false, _mut100251 = false, _mut100252 = false, _mut100253 = false, _mut100254 = false, _mut100255 = false, _mut100256 = false, _mut100257 = false, _mut100258 = false, _mut100259 = false, _mut100260 = false, _mut100261 = false, _mut100262 = false, _mut100263 = false, _mut100264 = false, _mut100265 = false, _mut100266 = false, _mut100267 = false, _mut100268 = false, _mut100269 = false, _mut100270 = false, _mut100271 = false, _mut100272 = false, _mut100273 = false, _mut100274 = false, _mut100275 = false, _mut100276 = false, _mut100277 = false, _mut100278 = false, _mut100279 = false, _mut100280 = false, _mut100281 = false, _mut100282 = false, _mut100283 = false, _mut100284 = false, _mut100285 = false, _mut100286 = false, _mut100287 = false, _mut100288 = false, _mut100289 = false, _mut100290 = false, _mut100291 = false, _mut100292 = false, _mut100293 = false, _mut100294 = false, _mut100295 = false, _mut100296 = false, _mut100297 = false, _mut100298 = false, _mut100299 = false, _mut100300 = false, _mut100301 = false, _mut100302 = false, _mut100303 = false, _mut100304 = false, _mut100305 = false, _mut100306 = false, _mut100307 = false, _mut100308 = false, _mut100309 = false, _mut100310 = false, _mut100311 = false, _mut100312 = false, _mut100313 = false, _mut100314 = false, _mut100315 = false, _mut100316 = false, _mut100317 = false, _mut100318 = false, _mut100319 = false, _mut100320 = false, _mut100321 = false, _mut100322 = false, _mut100323 = false, _mut100324 = false, _mut100325 = false, _mut100326 = false, _mut100327 = false, _mut100328 = false, _mut100329 = false, _mut100330 = false, _mut100331 = false, _mut100332 = false, _mut100333 = false, _mut100334 = false, _mut100335 = false, _mut100336 = false, _mut100337 = false, _mut100338 = false, _mut100339 = false, _mut100340 = false, _mut100341 = false, _mut100342 = false, _mut100343 = false, _mut100344 = false, _mut100345 = false, _mut100346 = false, _mut100347 = false, _mut100348 = false, _mut100349 = false, _mut100350 = false, _mut100351 = false, _mut100352 = false, _mut100353 = false, _mut100354 = false, _mut100355 = false, _mut100356 = false, _mut100357 = false, _mut100358 = false, _mut100359 = false, _mut100360 = false, _mut100361 = false, _mut100362 = false, _mut100363 = false, _mut100364 = false, _mut100365 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public MullerSolver2() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public MullerSolver2(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public MullerSolver2(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82");
        final double min = getMin();
        final double max = getMax();
        verifyInterval(min, max);
        final double relativeAccuracy = getRelativeAccuracy();
        final double absoluteAccuracy = getAbsoluteAccuracy();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        double x0 = min;
        double y0 = computeObjectiveValue(x0);
        if (ROR_less(FastMath.abs(y0), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100142, _mut100143, _mut100144, _mut100145, _mut100146)) {
            return x0;
        }
        double x1 = max;
        double y1 = computeObjectiveValue(x1);
        if (ROR_less(FastMath.abs(y1), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100147, _mut100148, _mut100149, _mut100150, _mut100151)) {
            return x1;
        }
        if (ROR_greater(AOR_multiply(y0, y1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100152, _mut100153, _mut100154, _mut100155), 0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100156, _mut100157, _mut100158, _mut100159, _mut100160)) {
            throw new NoBracketingException(x0, x1, y0, y1);
        }
        double x2 = AOR_multiply(0.5, (AOR_plus(x0, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100161, _mut100162, _mut100163, _mut100164)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100165, _mut100166, _mut100167, _mut100168);
        double y2 = computeObjectiveValue(x2);
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82");
            // quadratic interpolation through x0, x1, x2
            final double q = AOR_divide((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100169, _mut100170, _mut100171, _mut100172)), (AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100173, _mut100174, _mut100175, _mut100176)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100177, _mut100178, _mut100179, _mut100180);
            final double a = AOR_multiply(q, (AOR_plus(AOR_minus(y2, AOR_multiply((AOR_plus(1, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100181, _mut100182, _mut100183, _mut100184)), y1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100185, _mut100186, _mut100187, _mut100188), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100189, _mut100190, _mut100191, _mut100192), AOR_multiply(q, y0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100193, _mut100194, _mut100195, _mut100196), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100197, _mut100198, _mut100199, _mut100200)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100201, _mut100202, _mut100203, _mut100204);
            final double b = AOR_plus(AOR_minus(AOR_multiply((AOR_plus(AOR_multiply(2, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100205, _mut100206, _mut100207, _mut100208), 1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100209, _mut100210, _mut100211, _mut100212)), y2, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100213, _mut100214, _mut100215, _mut100216), AOR_multiply(AOR_multiply((AOR_plus(1, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100217, _mut100218, _mut100219, _mut100220)), (AOR_plus(1, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100221, _mut100222, _mut100223, _mut100224)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100225, _mut100226, _mut100227, _mut100228), y1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100229, _mut100230, _mut100231, _mut100232), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100233, _mut100234, _mut100235, _mut100236), AOR_multiply(AOR_multiply(q, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100237, _mut100238, _mut100239, _mut100240), y0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100241, _mut100242, _mut100243, _mut100244), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100245, _mut100246, _mut100247, _mut100248);
            final double c = AOR_multiply((AOR_plus(1, q, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100249, _mut100250, _mut100251, _mut100252)), y2, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100253, _mut100254, _mut100255, _mut100256);
            final double delta = AOR_minus(AOR_multiply(b, b, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100257, _mut100258, _mut100259, _mut100260), AOR_multiply(AOR_multiply(4, a, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100261, _mut100262, _mut100263, _mut100264), c, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100265, _mut100266, _mut100267, _mut100268), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100269, _mut100270, _mut100271, _mut100272);
            double x;
            final double denominator;
            if (ROR_greater_equals(delta, 0.0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100273, _mut100274, _mut100275, _mut100276, _mut100277)) {
                // choose a denominator larger in magnitude
                double dplus = AOR_plus(b, FastMath.sqrt(delta), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100286, _mut100287, _mut100288, _mut100289);
                double dminus = AOR_minus(b, FastMath.sqrt(delta), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100290, _mut100291, _mut100292, _mut100293);
                denominator = ROR_greater(FastMath.abs(dplus), FastMath.abs(dminus), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100294, _mut100295, _mut100296, _mut100297, _mut100298) ? dplus : dminus;
            } else {
                // take the modulus of (B +/- FastMath.sqrt(delta))
                denominator = FastMath.sqrt(AOR_minus(AOR_multiply(b, b, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100278, _mut100279, _mut100280, _mut100281), delta, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100282, _mut100283, _mut100284, _mut100285));
            }
            if (ROR_not_equals(denominator, 0, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100299, _mut100300, _mut100301, _mut100302, _mut100303)) {
                x = AOR_minus(x2, AOR_divide(AOR_multiply(AOR_multiply(2.0, c, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100316, _mut100317, _mut100318, _mut100319), (AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100320, _mut100321, _mut100322, _mut100323)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100324, _mut100325, _mut100326, _mut100327), denominator, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100328, _mut100329, _mut100330, _mut100331), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100332, _mut100333, _mut100334, _mut100335);
                // the equality tests here are intentional
                while ((_mut100346 ? (ROR_equals(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100336, _mut100337, _mut100338, _mut100339, _mut100340) && ROR_equals(x, x2, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100341, _mut100342, _mut100343, _mut100344, _mut100345)) : (ROR_equals(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100336, _mut100337, _mut100338, _mut100339, _mut100340) || ROR_equals(x, x2, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100341, _mut100342, _mut100343, _mut100344, _mut100345)))) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82");
                    x += absoluteAccuracy;
                }
            } else {
                // extremely rare case, get a random number to skip it
                x = AOR_plus(min, AOR_multiply(FastMath.random(), (AOR_minus(max, min, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100304, _mut100305, _mut100306, _mut100307)), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100308, _mut100309, _mut100310, _mut100311), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100312, _mut100313, _mut100314, _mut100315);
                oldx = Double.POSITIVE_INFINITY;
            }
            final double y = computeObjectiveValue(x);
            // check for convergence
            final double tolerance = FastMath.max(AOR_multiply(relativeAccuracy, FastMath.abs(x), "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100347, _mut100348, _mut100349, _mut100350), absoluteAccuracy);
            if ((_mut100365 ? (ROR_less_equals(FastMath.abs(AOR_minus(x, oldx, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100351, _mut100352, _mut100353, _mut100354)), tolerance, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100355, _mut100356, _mut100357, _mut100358, _mut100359) && ROR_less_equals(FastMath.abs(y), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100360, _mut100361, _mut100362, _mut100363, _mut100364)) : (ROR_less_equals(FastMath.abs(AOR_minus(x, oldx, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100351, _mut100352, _mut100353, _mut100354)), tolerance, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100355, _mut100356, _mut100357, _mut100358, _mut100359) || ROR_less_equals(FastMath.abs(y), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver2.doSolve_82", _mut100360, _mut100361, _mut100362, _mut100363, _mut100364)))) {
                return x;
            }
            // prepare the next iteration
            x0 = x1;
            y0 = y1;
            x1 = x2;
            y1 = y2;
            x2 = x;
            y2 = y;
            oldx = x;
        }
    }
}
