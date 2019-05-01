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
package org.apache.commons.math3.analysis.polynomials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A collection of static methods that operate on or return polynomials.
 *
 * @since 2.0
 */
public class PolynomialsUtils {

    @Conditional
    public static boolean _mut90412 = false, _mut90413 = false, _mut90414 = false, _mut90415 = false, _mut90416 = false, _mut90417 = false, _mut90418 = false, _mut90419 = false, _mut90420 = false, _mut90421 = false, _mut90422 = false, _mut90423 = false, _mut90424 = false, _mut90425 = false, _mut90426 = false, _mut90427 = false, _mut90428 = false, _mut90429 = false, _mut90430 = false, _mut90431 = false, _mut90432 = false, _mut90433 = false, _mut90434 = false, _mut90435 = false, _mut90436 = false, _mut90437 = false, _mut90438 = false, _mut90439 = false, _mut90440 = false, _mut90441 = false, _mut90442 = false, _mut90443 = false, _mut90444 = false, _mut90445 = false, _mut90446 = false, _mut90447 = false, _mut90448 = false, _mut90449 = false, _mut90450 = false, _mut90451 = false, _mut90452 = false, _mut90453 = false, _mut90454 = false, _mut90455 = false, _mut90456 = false, _mut90457 = false, _mut90458 = false, _mut90459 = false, _mut90460 = false, _mut90461 = false, _mut90462 = false, _mut90463 = false, _mut90464 = false, _mut90465 = false, _mut90466 = false, _mut90467 = false, _mut90468 = false, _mut90469 = false, _mut90470 = false, _mut90471 = false, _mut90472 = false, _mut90473 = false, _mut90474 = false, _mut90475 = false, _mut90476 = false, _mut90477 = false, _mut90478 = false, _mut90479 = false, _mut90480 = false, _mut90481 = false, _mut90482 = false, _mut90483 = false, _mut90484 = false, _mut90485 = false, _mut90486 = false, _mut90487 = false, _mut90488 = false, _mut90489 = false, _mut90490 = false, _mut90491 = false, _mut90492 = false, _mut90493 = false, _mut90494 = false, _mut90495 = false, _mut90496 = false, _mut90497 = false, _mut90498 = false, _mut90499 = false, _mut90500 = false, _mut90501 = false, _mut90502 = false, _mut90503 = false, _mut90504 = false, _mut90505 = false, _mut90506 = false, _mut90507 = false, _mut90508 = false, _mut90509 = false, _mut90510 = false, _mut90511 = false, _mut90512 = false, _mut90513 = false, _mut90514 = false, _mut90515 = false, _mut90516 = false, _mut90517 = false, _mut90518 = false, _mut90519 = false, _mut90520 = false, _mut90521 = false, _mut90522 = false, _mut90523 = false, _mut90524 = false, _mut90525 = false, _mut90526 = false, _mut90527 = false, _mut90528 = false, _mut90529 = false, _mut90530 = false, _mut90531 = false, _mut90532 = false, _mut90533 = false, _mut90534 = false, _mut90535 = false, _mut90536 = false, _mut90537 = false, _mut90538 = false, _mut90539 = false, _mut90540 = false, _mut90541 = false, _mut90542 = false, _mut90543 = false, _mut90544 = false, _mut90545 = false, _mut90546 = false, _mut90547 = false, _mut90548 = false, _mut90549 = false, _mut90550 = false, _mut90551 = false, _mut90552 = false, _mut90553 = false, _mut90554 = false, _mut90555 = false, _mut90556 = false, _mut90557 = false, _mut90558 = false, _mut90559 = false, _mut90560 = false, _mut90561 = false, _mut90562 = false, _mut90563 = false, _mut90564 = false, _mut90565 = false, _mut90566 = false, _mut90567 = false, _mut90568 = false, _mut90569 = false, _mut90570 = false, _mut90571 = false, _mut90572 = false, _mut90573 = false, _mut90574 = false, _mut90575 = false, _mut90576 = false, _mut90577 = false, _mut90578 = false, _mut90579 = false, _mut90580 = false, _mut90581 = false, _mut90582 = false, _mut90583 = false, _mut90584 = false, _mut90585 = false, _mut90586 = false, _mut90587 = false, _mut90588 = false, _mut90589 = false, _mut90590 = false, _mut90591 = false, _mut90592 = false, _mut90593 = false, _mut90594 = false, _mut90595 = false, _mut90596 = false, _mut90597 = false, _mut90598 = false, _mut90599 = false, _mut90600 = false, _mut90601 = false, _mut90602 = false, _mut90603 = false, _mut90604 = false, _mut90605 = false, _mut90606 = false, _mut90607 = false, _mut90608 = false, _mut90609 = false, _mut90610 = false, _mut90611 = false, _mut90612 = false, _mut90613 = false, _mut90614 = false, _mut90615 = false, _mut90616 = false, _mut90617 = false, _mut90618 = false, _mut90619 = false, _mut90620 = false, _mut90621 = false, _mut90622 = false, _mut90623 = false, _mut90624 = false, _mut90625 = false, _mut90626 = false, _mut90627 = false, _mut90628 = false, _mut90629 = false, _mut90630 = false, _mut90631 = false, _mut90632 = false, _mut90633 = false, _mut90634 = false, _mut90635 = false, _mut90636 = false, _mut90637 = false, _mut90638 = false, _mut90639 = false, _mut90640 = false, _mut90641 = false, _mut90642 = false, _mut90643 = false, _mut90644 = false, _mut90645 = false, _mut90646 = false, _mut90647 = false, _mut90648 = false, _mut90649 = false, _mut90650 = false, _mut90651 = false, _mut90652 = false, _mut90653 = false, _mut90654 = false, _mut90655 = false, _mut90656 = false, _mut90657 = false, _mut90658 = false, _mut90659 = false, _mut90660 = false, _mut90661 = false, _mut90662 = false, _mut90663 = false, _mut90664 = false, _mut90665 = false, _mut90666 = false, _mut90667 = false, _mut90668 = false, _mut90669 = false, _mut90670 = false, _mut90671 = false, _mut90672 = false, _mut90673 = false, _mut90674 = false, _mut90675 = false, _mut90676 = false;

    /**
     * Coefficients for Chebyshev polynomials.
     */
    private static final List<BigFraction> CHEBYSHEV_COEFFICIENTS;

    /**
     * Coefficients for Hermite polynomials.
     */
    private static final List<BigFraction> HERMITE_COEFFICIENTS;

    /**
     * Coefficients for Laguerre polynomials.
     */
    private static final List<BigFraction> LAGUERRE_COEFFICIENTS;

    /**
     * Coefficients for Legendre polynomials.
     */
    private static final List<BigFraction> LEGENDRE_COEFFICIENTS;

    /**
     * Coefficients for Jacobi polynomials.
     */
    private static final Map<JacobiKey, List<BigFraction>> JACOBI_COEFFICIENTS;

    static {
        // T0(X) = 1, T1(X) = 0 + 1 * X
        CHEBYSHEV_COEFFICIENTS = new ArrayList<BigFraction>();
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ZERO);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        // H0(X) = 1, H1(X) = 0 + 2 * X
        HERMITE_COEFFICIENTS = new ArrayList<BigFraction>();
        HERMITE_COEFFICIENTS.add(BigFraction.ONE);
        HERMITE_COEFFICIENTS.add(BigFraction.ZERO);
        HERMITE_COEFFICIENTS.add(BigFraction.TWO);
        // L0(X) = 1, L1(X) = 1 - 1 * X
        LAGUERRE_COEFFICIENTS = new ArrayList<BigFraction>();
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.MINUS_ONE);
        // P0(X) = 1, P1(X) = 0 + 1 * X
        LEGENDRE_COEFFICIENTS = new ArrayList<BigFraction>();
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ZERO);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
        // initialize map for Jacobi polynomials
        JACOBI_COEFFICIENTS = new HashMap<JacobiKey, List<BigFraction>>();
    }

    /**
     * Private constructor, to prevent instantiation.
     */
    private PolynomialsUtils() {
    }

    /**
     * Create a Chebyshev polynomial of the first kind.
     * <p><a href="https://en.wikipedia.org/wiki/Chebyshev_polynomials">Chebyshev
     * polynomials of the first kind</a> are orthogonal polynomials.
     * They can be defined by the following recurrence relations:</p><p>
     * \(
     *    T_0(x) = 1 \\
     *    T_1(x) = x \\
     *    T_{k+1}(x) = 2x T_k(x) - T_{k-1}(x)
     * \)
     * </p>
     * @param degree degree of the polynomial
     * @return Chebyshev polynomial of specified degree
     */
    public static PolynomialFunction createChebyshevPolynomial(final int degree) {
        return buildPolynomial(degree, CHEBYSHEV_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {

            /**
             * Fixed recurrence coefficients.
             */
            private final BigFraction[] coeffs = { BigFraction.ZERO, BigFraction.TWO, BigFraction.ONE };

            /**
             * {@inheritDoc}
             */
            public BigFraction[] generate(int k) {
                return coeffs;
            }
        });
    }

    /**
     * Create a Hermite polynomial.
     * <p><a href="http://mathworld.wolfram.com/HermitePolynomial.html">Hermite
     * polynomials</a> are orthogonal polynomials.
     * They can be defined by the following recurrence relations:</p><p>
     * \(
     *  H_0(x) = 1 \\
     *  H_1(x) = 2x \\
     *  H_{k+1}(x) = 2x H_k(X) - 2k H_{k-1}(x)
     * \)
     * </p>
     *
     * @param degree degree of the polynomial
     * @return Hermite polynomial of specified degree
     */
    public static PolynomialFunction createHermitePolynomial(final int degree) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_136");
        return buildPolynomial(degree, HERMITE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {

            /**
             * {@inheritDoc}
             */
            public BigFraction[] generate(int k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_136");
                return new BigFraction[] { BigFraction.ZERO, BigFraction.TWO, new BigFraction(AOR_multiply(2, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_136", _mut90412, _mut90413, _mut90414, _mut90415)) };
            }
        });
    }

    /**
     * Create a Laguerre polynomial.
     * <p><a href="http://mathworld.wolfram.com/LaguerrePolynomial.html">Laguerre
     * polynomials</a> are orthogonal polynomials.
     * They can be defined by the following recurrence relations:</p><p>
     * \(
     *   L_0(x) = 1 \\
     *   L_1(x) = 1 - x \\
     *   (k+1) L_{k+1}(x) = (2k + 1 - x) L_k(x) - k L_{k-1}(x)
     * \)
     * </p>
     * @param degree degree of the polynomial
     * @return Laguerre polynomial of specified degree
     */
    public static PolynomialFunction createLaguerrePolynomial(final int degree) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_163");
        return buildPolynomial(degree, LAGUERRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {

            /**
             * {@inheritDoc}
             */
            public BigFraction[] generate(int k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_163");
                final int kP1 = AOR_plus(k, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_163", _mut90416, _mut90417, _mut90418, _mut90419);
                return new BigFraction[] { new BigFraction(AOR_plus(AOR_multiply(2, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_163", _mut90420, _mut90421, _mut90422, _mut90423), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_163", _mut90424, _mut90425, _mut90426, _mut90427), kP1), new BigFraction(-1, kP1), new BigFraction(k, kP1) };
            }
        });
    }

    /**
     * Create a Legendre polynomial.
     * <p><a href="http://mathworld.wolfram.com/LegendrePolynomial.html">Legendre
     * polynomials</a> are orthogonal polynomials.
     * They can be defined by the following recurrence relations:</p><p>
     * \(
     *   P_0(x) = 1 \\
     *   P_1(x) = x \\
     *   (k+1) P_{k+1}(x) = (2k+1) x P_k(x) - k P_{k-1}(x)
     * \)
     * </p>
     * @param degree degree of the polynomial
     * @return Legendre polynomial of specified degree
     */
    public static PolynomialFunction createLegendrePolynomial(final int degree) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_191");
        return buildPolynomial(degree, LEGENDRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {

            /**
             * {@inheritDoc}
             */
            public BigFraction[] generate(int k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_191");
                final int kP1 = AOR_plus(k, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_191", _mut90428, _mut90429, _mut90430, _mut90431);
                return new BigFraction[] { BigFraction.ZERO, new BigFraction(AOR_plus(k, kP1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_191", _mut90432, _mut90433, _mut90434, _mut90435), kP1), new BigFraction(k, kP1) };
            }
        });
    }

    /**
     * Create a Jacobi polynomial.
     * <p><a href="http://mathworld.wolfram.com/JacobiPolynomial.html">Jacobi
     * polynomials</a> are orthogonal polynomials.
     * They can be defined by the following recurrence relations:</p><p>
     * \(
     *    P_0^{vw}(x) = 1 \\
     *    P_{-1}^{vw}(x) = 0 \\
     *    2k(k + v + w)(2k + v + w - 2) P_k^{vw}(x) = \\
     *    (2k + v + w - 1)[(2k + v + w)(2k + v + w - 2) x + v^2 - w^2] P_{k-1}^{vw}(x) \\
     *  - 2(k + v - 1)(k + w - 1)(2k + v + w) P_{k-2}^{vw}(x)
     * \)
     * </p>
     * @param degree degree of the polynomial
     * @param v first exponent
     * @param w second exponent
     * @return Jacobi polynomial of specified degree
     */
    public static PolynomialFunction createJacobiPolynomial(final int degree, final int v, final int w) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242");
        // select the appropriate list
        final JacobiKey key = new JacobiKey(v, w);
        if (!JACOBI_COEFFICIENTS.containsKey(key)) {
            // allocate a new list for v, w
            final List<BigFraction> list = new ArrayList<BigFraction>();
            JACOBI_COEFFICIENTS.put(key, list);
            // Pv,w,0(x) = 1;
            list.add(BigFraction.ONE);
            // P1(x) = (v - w) / 2 + (2 + v + w) * X / 2
            list.add(new BigFraction(AOR_minus(v, w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.createJacobiPolynomial_219", _mut90436, _mut90437, _mut90438, _mut90439), 2));
            list.add(new BigFraction(AOR_plus(AOR_plus(2, v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.createJacobiPolynomial_219", _mut90440, _mut90441, _mut90442, _mut90443), w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.createJacobiPolynomial_219", _mut90444, _mut90445, _mut90446, _mut90447), 2));
        }
        return buildPolynomial(degree, JACOBI_COEFFICIENTS.get(key), new RecurrenceCoefficientsGenerator() {

            /**
             * {@inheritDoc}
             */
            public BigFraction[] generate(int k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242");
                k++;
                final int kvw = AOR_plus(AOR_plus(k, v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90448, _mut90449, _mut90450, _mut90451), w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90452, _mut90453, _mut90454, _mut90455);
                final int twoKvw = AOR_plus(kvw, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90456, _mut90457, _mut90458, _mut90459);
                final int twoKvwM1 = AOR_minus(twoKvw, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90460, _mut90461, _mut90462, _mut90463);
                final int twoKvwM2 = AOR_minus(twoKvw, 2, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90464, _mut90465, _mut90466, _mut90467);
                final int den = AOR_multiply(AOR_multiply(AOR_multiply(2, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90468, _mut90469, _mut90470, _mut90471), kvw, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90472, _mut90473, _mut90474, _mut90475), twoKvwM2, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90476, _mut90477, _mut90478, _mut90479);
                return new BigFraction[] { new BigFraction(AOR_multiply(twoKvwM1, (AOR_minus(AOR_multiply(v, v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90480, _mut90481, _mut90482, _mut90483), AOR_multiply(w, w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90484, _mut90485, _mut90486, _mut90487), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90488, _mut90489, _mut90490, _mut90491)), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90492, _mut90493, _mut90494, _mut90495), den), new BigFraction(AOR_multiply(AOR_multiply(twoKvwM1, twoKvw, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90496, _mut90497, _mut90498, _mut90499), twoKvwM2, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90500, _mut90501, _mut90502, _mut90503), den), new BigFraction(AOR_multiply(AOR_multiply(AOR_multiply(2, (AOR_minus(AOR_plus(k, v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90504, _mut90505, _mut90506, _mut90507), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90508, _mut90509, _mut90510, _mut90511)), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90512, _mut90513, _mut90514, _mut90515), (AOR_minus(AOR_plus(k, w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90516, _mut90517, _mut90518, _mut90519), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90520, _mut90521, _mut90522, _mut90523)), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90524, _mut90525, _mut90526, _mut90527), twoKvw, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.generate_242", _mut90528, _mut90529, _mut90530, _mut90531), den) };
            }
        });
    }

    /**
     * Inner class for Jacobi polynomials keys.
     */
    private static class JacobiKey {

        /**
         * First exponent.
         */
        private final int v;

        /**
         * Second exponent.
         */
        private final int w;

        /**
         * Simple constructor.
         * @param v first exponent
         * @param w second exponent
         */
        JacobiKey(final int v, final int w) {
            this.v = v;
            this.w = w;
        }

        /**
         * Get hash code.
         * @return hash code
         */
        @Override
        public int hashCode() {
            return (v << 16) ^ w;
        }

        /**
         * Check if the instance represent the same key as another instance.
         * @param key other key
         * @return true if the instance and the other key refer to the same polynomial
         */
        @Override
        public boolean equals(final Object key) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.equals_290");
            if ((_mut90532 ? ((key == null) && !(key instanceof JacobiKey)) : ((key == null) || !(key instanceof JacobiKey)))) {
                return false;
            }
            final JacobiKey otherK = (JacobiKey) key;
            return (_mut90543 ? ((ROR_equals(v, otherK.v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.equals_290", _mut90533, _mut90534, _mut90535, _mut90536, _mut90537)) || (ROR_equals(w, otherK.w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.equals_290", _mut90538, _mut90539, _mut90540, _mut90541, _mut90542))) : ((ROR_equals(v, otherK.v, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.equals_290", _mut90533, _mut90534, _mut90535, _mut90536, _mut90537)) && (ROR_equals(w, otherK.w, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.equals_290", _mut90538, _mut90539, _mut90540, _mut90541, _mut90542))));
        }
    }

    /**
     * Compute the coefficients of the polynomial \(P_s(x)\)
     * whose values at point {@code x} will be the same as the those from the
     * original polynomial \(P(x)\) when computed at {@code x + shift}.
     * <p>
     * More precisely, let \(\Delta = \) {@code shift} and let
     * \(P_s(x) = P(x + \Delta)\).  The returned array
     * consists of the coefficients of \(P_s\).  So if \(a_0, ..., a_{n-1}\)
     * are the coefficients of \(P\), then the returned array
     * \(b_0, ..., b_{n-1}\) satisfies the identity
     * \(\sum_{i=0}^{n-1} b_i x^i = \sum_{i=0}^{n-1} a_i (x + \Delta)^i\) for all \(x\).
     *
     * @param coefficients Coefficients of the original polynomial.
     * @param shift Shift value.
     * @return the coefficients \(b_i\) of the shifted
     * polynomial.
     */
    public static double[] shift(final double[] coefficients, final double shift) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
        final int dp1 = coefficients.length;
        final double[] newCoefficients = new double[dp1];
        // Pascal triangle.
        final int[][] coeff = new int[dp1][dp1];
        for (int i = 0; ROR_less(i, dp1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90549, _mut90550, _mut90551, _mut90552, _mut90553); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
            for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90544, _mut90545, _mut90546, _mut90547, _mut90548); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
                coeff[i][j] = (int) CombinatoricsUtils.binomialCoefficient(i, j);
            }
        }
        // First polynomial coefficient.
        for (int i = 0; ROR_less(i, dp1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90558, _mut90559, _mut90560, _mut90561, _mut90562); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
            newCoefficients[0] += AOR_multiply(coefficients[i], FastMath.pow(shift, i), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90554, _mut90555, _mut90556, _mut90557);
        }
        // Superior order.
        final int d = AOR_minus(dp1, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90563, _mut90564, _mut90565, _mut90566);
        for (int i = 0; ROR_less(i, d, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90600, _mut90601, _mut90602, _mut90603, _mut90604); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
            for (int j = i; ROR_less(j, d, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90595, _mut90596, _mut90597, _mut90598, _mut90599); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320");
                newCoefficients[AOR_plus(i, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90567, _mut90568, _mut90569, _mut90570)] += AOR_multiply(AOR_multiply(coeff[AOR_plus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90575, _mut90576, _mut90577, _mut90578)][AOR_minus(j, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90571, _mut90572, _mut90573, _mut90574)], coefficients[AOR_plus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90579, _mut90580, _mut90581, _mut90582)], "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90583, _mut90584, _mut90585, _mut90586), FastMath.pow(shift, AOR_minus(j, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90587, _mut90588, _mut90589, _mut90590)), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.shift_320", _mut90591, _mut90592, _mut90593, _mut90594);
            }
        }
        return newCoefficients;
    }

    /**
     * Get the coefficients array for a given degree.
     * @param degree degree of the polynomial
     * @param coefficients list where the computed coefficients are stored
     * @param generator recurrence coefficients generator
     * @return coefficients array
     */
    private static PolynomialFunction buildPolynomial(final int degree, final List<BigFraction> coefficients, final RecurrenceCoefficientsGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357");
        synchronized (coefficients) {
            final int maxDegree = AOR_minus((int) FastMath.floor(FastMath.sqrt(AOR_multiply(2, coefficients.size(), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90605, _mut90606, _mut90607, _mut90608))), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90609, _mut90610, _mut90611, _mut90612);
            if (ROR_greater(degree, maxDegree, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90613, _mut90614, _mut90615, _mut90616, _mut90617)) {
                computeUpToDegree(degree, maxDegree, generator, coefficients);
            }
        }
        // ...
        final int start = AOR_divide(AOR_multiply(degree, (AOR_plus(degree, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90618, _mut90619, _mut90620, _mut90621)), "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90622, _mut90623, _mut90624, _mut90625), 2, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90626, _mut90627, _mut90628, _mut90629);
        final double[] a = new double[AOR_plus(degree, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90630, _mut90631, _mut90632, _mut90633)];
        for (int i = 0; ROR_less_equals(i, degree, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90638, _mut90639, _mut90640, _mut90641, _mut90642); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357");
            a[i] = coefficients.get(AOR_plus(start, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.buildPolynomial_357", _mut90634, _mut90635, _mut90636, _mut90637)).doubleValue();
        }
        // build the polynomial
        return new PolynomialFunction(a);
    }

    /**
     * Compute polynomial coefficients up to a given degree.
     * @param degree maximal degree
     * @param maxDegree current maximal degree
     * @param generator recurrence coefficients generator
     * @param coefficients list where the computed coefficients should be appended
     */
    private static void computeUpToDegree(final int degree, final int maxDegree, final RecurrenceCoefficientsGenerator generator, final List<BigFraction> coefficients) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393");
        int startK = AOR_divide(AOR_multiply((AOR_minus(maxDegree, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90643, _mut90644, _mut90645, _mut90646)), maxDegree, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90647, _mut90648, _mut90649, _mut90650), 2, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90651, _mut90652, _mut90653, _mut90654);
        for (int k = maxDegree; ROR_less(k, degree, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90672, _mut90673, _mut90674, _mut90675, _mut90676); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393");
            // start indices of two previous polynomials Pk(X) and Pk-1(X)
            int startKm1 = startK;
            startK += k;
            // Pk+1(X) = (a[0] + a[1] X) Pk(X) - a[2] Pk-1(X)
            BigFraction[] ai = generator.generate(k);
            BigFraction ck = coefficients.get(startK);
            BigFraction ckm1 = coefficients.get(startKm1);
            // degree 0 coefficient
            coefficients.add(ck.multiply(ai[0]).subtract(ckm1.multiply(ai[2])));
            // degree 1 to degree k-1 coefficients
            for (int i = 1; ROR_less(i, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90663, _mut90664, _mut90665, _mut90666, _mut90667); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393");
                final BigFraction ckPrev = ck;
                ck = coefficients.get(AOR_plus(startK, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90655, _mut90656, _mut90657, _mut90658));
                ckm1 = coefficients.get(AOR_plus(startKm1, i, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90659, _mut90660, _mut90661, _mut90662));
                coefficients.add(ck.multiply(ai[0]).add(ckPrev.multiply(ai[1])).subtract(ckm1.multiply(ai[2])));
            }
            // degree k coefficient
            final BigFraction ckPrev = ck;
            ck = coefficients.get(AOR_plus(startK, k, "org.apache.commons.math3.analysis.polynomials.PolynomialsUtils.computeUpToDegree_393", _mut90668, _mut90669, _mut90670, _mut90671));
            coefficients.add(ck.multiply(ai[0]).add(ckPrev.multiply(ai[1])));
            // degree k+1 coefficient
            coefficients.add(ck.multiply(ai[1]));
        }
    }

    /**
     * Interface for recurrence coefficients generation.
     */
    private interface RecurrenceCoefficientsGenerator {

        /**
         * Generate recurrence coefficients.
         * @param k highest degree of the polynomials used in the recurrence
         * @return an array of three coefficients such that
         * \( P_{k+1}(x) = (a[0] + a[1] x) P_k(x) - a[2] P_{k-1}(x) \)
         */
        BigFraction[] generate(int k);
    }
}
