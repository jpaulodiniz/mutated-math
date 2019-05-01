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
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Factory that creates a
 * <a href="http://en.wikipedia.org/wiki/Gauss-Hermite_quadrature">
 * Gauss-type quadrature rule using Hermite polynomials</a>
 * of the first kind.
 * Such a quadrature rule allows the calculation of improper integrals
 * of a function
 * <p>
 *  \(f(x) e^{-x^2}\)
 * </p><p>
 * Recurrence relation and weights computation follow
 * <a href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">
 * Abramowitz and Stegun, 1964</a>.
 * </p><p>
 * The coefficients of the standard Hermite polynomials grow very rapidly.
 * In order to avoid overflows, each Hermite polynomial is normalized with
 * respect to the underlying scalar product.
 * The initial interval for the application of the bisection method is
 * based on the roots of the previous Hermite polynomial (interlacing).
 * Upper and lower bounds of these roots are provided by </p>
 * <blockquote>
 *  I. Krasikov,
 *  <em>Nonnegative quadratic forms and bounds on orthogonal polynomials</em>,
 *  Journal of Approximation theory <b>111</b>, 31-49
 * </blockquote>
 *
 * @since 3.3
 */
public class HermiteRuleFactory extends BaseRuleFactory<Double> {

    @Conditional
    public static boolean _mut101329 = false, _mut101330 = false, _mut101331 = false, _mut101332 = false, _mut101333 = false, _mut101334 = false, _mut101335 = false, _mut101336 = false, _mut101337 = false, _mut101338 = false, _mut101339 = false, _mut101340 = false, _mut101341 = false, _mut101342 = false, _mut101343 = false, _mut101344 = false, _mut101345 = false, _mut101346 = false, _mut101347 = false, _mut101348 = false, _mut101349 = false, _mut101350 = false, _mut101351 = false, _mut101352 = false, _mut101353 = false, _mut101354 = false, _mut101355 = false, _mut101356 = false, _mut101357 = false, _mut101358 = false, _mut101359 = false, _mut101360 = false, _mut101361 = false, _mut101362 = false, _mut101363 = false, _mut101364 = false, _mut101365 = false, _mut101366 = false, _mut101367 = false, _mut101368 = false, _mut101369 = false, _mut101370 = false, _mut101371 = false, _mut101372 = false, _mut101373 = false, _mut101374 = false, _mut101375 = false, _mut101376 = false, _mut101377 = false, _mut101378 = false, _mut101379 = false, _mut101380 = false, _mut101381 = false, _mut101382 = false, _mut101383 = false, _mut101384 = false, _mut101385 = false, _mut101386 = false, _mut101387 = false, _mut101388 = false, _mut101389 = false, _mut101390 = false, _mut101391 = false, _mut101392 = false, _mut101393 = false, _mut101394 = false, _mut101395 = false, _mut101396 = false, _mut101397 = false, _mut101398 = false, _mut101399 = false, _mut101400 = false, _mut101401 = false, _mut101402 = false, _mut101403 = false, _mut101404 = false, _mut101405 = false, _mut101406 = false, _mut101407 = false, _mut101408 = false, _mut101409 = false, _mut101410 = false, _mut101411 = false, _mut101412 = false, _mut101413 = false, _mut101414 = false, _mut101415 = false, _mut101416 = false, _mut101417 = false, _mut101418 = false, _mut101419 = false, _mut101420 = false, _mut101421 = false, _mut101422 = false, _mut101423 = false, _mut101424 = false, _mut101425 = false, _mut101426 = false, _mut101427 = false, _mut101428 = false, _mut101429 = false, _mut101430 = false, _mut101431 = false, _mut101432 = false, _mut101433 = false, _mut101434 = false, _mut101435 = false, _mut101436 = false, _mut101437 = false, _mut101438 = false, _mut101439 = false, _mut101440 = false, _mut101441 = false, _mut101442 = false, _mut101443 = false, _mut101444 = false, _mut101445 = false, _mut101446 = false, _mut101447 = false, _mut101448 = false, _mut101449 = false, _mut101450 = false, _mut101451 = false, _mut101452 = false, _mut101453 = false, _mut101454 = false, _mut101455 = false, _mut101456 = false, _mut101457 = false, _mut101458 = false, _mut101459 = false, _mut101460 = false, _mut101461 = false, _mut101462 = false, _mut101463 = false, _mut101464 = false, _mut101465 = false, _mut101466 = false, _mut101467 = false, _mut101468 = false, _mut101469 = false, _mut101470 = false, _mut101471 = false, _mut101472 = false, _mut101473 = false, _mut101474 = false, _mut101475 = false, _mut101476 = false, _mut101477 = false, _mut101478 = false, _mut101479 = false, _mut101480 = false, _mut101481 = false, _mut101482 = false, _mut101483 = false, _mut101484 = false, _mut101485 = false, _mut101486 = false, _mut101487 = false, _mut101488 = false, _mut101489 = false, _mut101490 = false, _mut101491 = false, _mut101492 = false, _mut101493 = false, _mut101494 = false, _mut101495 = false, _mut101496 = false, _mut101497 = false, _mut101498 = false, _mut101499 = false, _mut101500 = false, _mut101501 = false, _mut101502 = false, _mut101503 = false, _mut101504 = false, _mut101505 = false, _mut101506 = false, _mut101507 = false, _mut101508 = false, _mut101509 = false, _mut101510 = false, _mut101511 = false, _mut101512 = false, _mut101513 = false, _mut101514 = false, _mut101515 = false, _mut101516 = false, _mut101517 = false, _mut101518 = false, _mut101519 = false, _mut101520 = false, _mut101521 = false, _mut101522 = false, _mut101523 = false, _mut101524 = false, _mut101525 = false, _mut101526 = false, _mut101527 = false, _mut101528 = false, _mut101529 = false, _mut101530 = false, _mut101531 = false, _mut101532 = false, _mut101533 = false, _mut101534 = false, _mut101535 = false, _mut101536 = false, _mut101537 = false, _mut101538 = false, _mut101539 = false, _mut101540 = false, _mut101541 = false, _mut101542 = false, _mut101543 = false, _mut101544 = false, _mut101545 = false, _mut101546 = false, _mut101547 = false, _mut101548 = false, _mut101549 = false, _mut101550 = false, _mut101551 = false, _mut101552 = false, _mut101553 = false, _mut101554 = false;

    /**
     * &pi;<sup>1/2</sup>
     */
    private static final double SQRT_PI = 1.77245385090551602729;

    /**
     * &pi;<sup>-1/4</sup>
     */
    private static final double H0 = 7.5112554446494248286e-1;

    /**
     * &pi;<sup>-1/4</sup> &radic;2
     */
    private static final double H1 = 1.0622519320271969145;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
        if (ROR_equals(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101329, _mut101330, _mut101331, _mut101332, _mut101333)) {
            // Break recursion.
            return new Pair<Double[], Double[]>(new Double[] { 0d }, new Double[] { SQRT_PI });
        }
        // to this method.
        final int lastNumPoints = AOR_minus(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101334, _mut101335, _mut101336, _mut101337);
        final Double[] previousPoints = getRuleInternal(lastNumPoints).getFirst();
        // Compute next rule.
        final Double[] points = new Double[numberOfPoints];
        final Double[] weights = new Double[numberOfPoints];
        final double sqrtTwoTimesLastNumPoints = FastMath.sqrt(AOR_multiply(2, lastNumPoints, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101338, _mut101339, _mut101340, _mut101341));
        final double sqrtTwoTimesNumPoints = FastMath.sqrt(AOR_multiply(2, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101342, _mut101343, _mut101344, _mut101345));
        // Find i-th root of H[n+1] by bracketing.
        final int iMax = AOR_divide(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101346, _mut101347, _mut101348, _mut101349);
        for (int i = 0; ROR_less(i, iMax, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101512, _mut101513, _mut101514, _mut101515, _mut101516); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
            // Lower-bound of the interval.
            double a = (ROR_equals(i, 0, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101350, _mut101351, _mut101352, _mut101353, _mut101354)) ? -sqrtTwoTimesLastNumPoints : previousPoints[AOR_minus(i, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101355, _mut101356, _mut101357, _mut101358)].doubleValue();
            // Upper-bound of the interval.
            double b = (ROR_equals(iMax, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101359, _mut101360, _mut101361, _mut101362, _mut101363)) ? -0.5 : previousPoints[i].doubleValue();
            // H[j-1](a)
            double hma = H0;
            // H[j](a)
            double ha = AOR_multiply(H1, a, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101364, _mut101365, _mut101366, _mut101367);
            // H[j-1](b)
            double hmb = H0;
            // H[j](b)
            double hb = AOR_multiply(H1, b, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101368, _mut101369, _mut101370, _mut101371);
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101416, _mut101417, _mut101418, _mut101419, _mut101420); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
                // Compute H[j+1](a) and H[j+1](b)
                final double jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101372, _mut101373, _mut101374, _mut101375);
                final double s = FastMath.sqrt(AOR_divide(2, jp1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101376, _mut101377, _mut101378, _mut101379));
                final double sm = FastMath.sqrt(AOR_divide(j, jp1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101380, _mut101381, _mut101382, _mut101383));
                final double hpa = AOR_minus(AOR_multiply(AOR_multiply(s, a, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101384, _mut101385, _mut101386, _mut101387), ha, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101388, _mut101389, _mut101390, _mut101391), AOR_multiply(sm, hma, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101392, _mut101393, _mut101394, _mut101395), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101396, _mut101397, _mut101398, _mut101399);
                final double hpb = AOR_minus(AOR_multiply(AOR_multiply(s, b, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101400, _mut101401, _mut101402, _mut101403), hb, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101404, _mut101405, _mut101406, _mut101407), AOR_multiply(sm, hmb, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101408, _mut101409, _mut101410, _mut101411), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101412, _mut101413, _mut101414, _mut101415);
                hma = ha;
                ha = hpa;
                hmb = hb;
                hb = hpb;
            }
            // Middle of the interval.
            double c = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101421, _mut101422, _mut101423, _mut101424)), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101425, _mut101426, _mut101427, _mut101428);
            // P[j-1](c)
            double hmc = H0;
            // P[j](c)
            double hc = AOR_multiply(H1, c, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101429, _mut101430, _mut101431, _mut101432);
            boolean done = false;
            while (!done) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
                done = ROR_less_equals(AOR_minus(b, a, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101433, _mut101434, _mut101435, _mut101436), Math.ulp(c), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101437, _mut101438, _mut101439, _mut101440, _mut101441);
                hmc = H0;
                hc = AOR_multiply(H1, c, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101442, _mut101443, _mut101444, _mut101445);
                for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101474, _mut101475, _mut101476, _mut101477, _mut101478); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
                    // Compute H[j+1](c)
                    final double jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101446, _mut101447, _mut101448, _mut101449);
                    final double s = FastMath.sqrt(AOR_divide(2, jp1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101450, _mut101451, _mut101452, _mut101453));
                    final double sm = FastMath.sqrt(AOR_divide(j, jp1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101454, _mut101455, _mut101456, _mut101457));
                    final double hpc = AOR_minus(AOR_multiply(AOR_multiply(s, c, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101458, _mut101459, _mut101460, _mut101461), hc, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101462, _mut101463, _mut101464, _mut101465), AOR_multiply(sm, hmc, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101466, _mut101467, _mut101468, _mut101469), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101470, _mut101471, _mut101472, _mut101473);
                    hmc = hc;
                    hc = hpc;
                }
                // Now h = H[n+1](c) and hm = H[n](c).
                if (!done) {
                    if (ROR_less(AOR_multiply(ha, hc, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101479, _mut101480, _mut101481, _mut101482), 0, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101483, _mut101484, _mut101485, _mut101486, _mut101487)) {
                        b = c;
                        hmb = hmc;
                        hb = hc;
                    } else {
                        a = c;
                        hma = hmc;
                        ha = hc;
                    }
                    c = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101488, _mut101489, _mut101490, _mut101491)), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101492, _mut101493, _mut101494, _mut101495);
                }
            }
            final double d = AOR_multiply(sqrtTwoTimesNumPoints, hmc, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101496, _mut101497, _mut101498, _mut101499);
            final double w = AOR_divide(2, (AOR_multiply(d, d, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101500, _mut101501, _mut101502, _mut101503)), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101504, _mut101505, _mut101506, _mut101507);
            points[i] = c;
            weights[i] = w;
            final int idx = AOR_minus(lastNumPoints, i, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101508, _mut101509, _mut101510, _mut101511);
            points[idx] = -c;
            weights[idx] = w;
        }
        // a FindBugs warning.
        if (ROR_not_equals(AOR_remainder(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101517, _mut101518, _mut101519, _mut101520), 0, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101521, _mut101522, _mut101523, _mut101524, _mut101525)) {
            double hm = H0;
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101538, _mut101539, _mut101540, _mut101541, _mut101542); j += 2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60");
                final double jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101526, _mut101527, _mut101528, _mut101529);
                hm = AOR_multiply(-FastMath.sqrt(AOR_divide(j, jp1, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101530, _mut101531, _mut101532, _mut101533)), hm, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101534, _mut101535, _mut101536, _mut101537);
            }
            final double d = AOR_multiply(sqrtTwoTimesNumPoints, hm, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101543, _mut101544, _mut101545, _mut101546);
            final double w = AOR_divide(2, (AOR_multiply(d, d, "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101547, _mut101548, _mut101549, _mut101550)), "org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.computeRule_60", _mut101551, _mut101552, _mut101553, _mut101554);
            points[iMax] = 0d;
            weights[iMax] = w;
        }
        return new Pair<Double[], Double[]>(points, weights);
    }
}
