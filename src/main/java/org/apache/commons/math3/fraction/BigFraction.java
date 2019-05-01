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
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Representation of a rational number without any overflow. This class is
 * immutable.
 *
 * @since 2.0
 */
public class BigFraction extends Number implements FieldElement<BigFraction>, Comparable<BigFraction>, Serializable {

    @Conditional
    public static boolean _mut298 = false, _mut299 = false, _mut300 = false, _mut301 = false, _mut302 = false, _mut303 = false, _mut304 = false, _mut305 = false, _mut306 = false, _mut307 = false, _mut308 = false, _mut309 = false, _mut310 = false, _mut311 = false, _mut312 = false, _mut313 = false, _mut314 = false, _mut315 = false, _mut316 = false, _mut317 = false, _mut318 = false, _mut319 = false, _mut320 = false, _mut321 = false, _mut322 = false, _mut323 = false, _mut324 = false, _mut325 = false, _mut326 = false, _mut327 = false, _mut328 = false, _mut329 = false, _mut330 = false, _mut331 = false, _mut332 = false, _mut333 = false, _mut334 = false, _mut335 = false, _mut336 = false, _mut337 = false, _mut338 = false, _mut339 = false, _mut340 = false, _mut341 = false, _mut342 = false, _mut343 = false, _mut344 = false, _mut345 = false, _mut346 = false, _mut347 = false, _mut348 = false, _mut349 = false, _mut350 = false, _mut351 = false, _mut352 = false, _mut353 = false, _mut354 = false, _mut355 = false, _mut356 = false, _mut357 = false, _mut358 = false, _mut359 = false, _mut360 = false, _mut361 = false, _mut362 = false, _mut363 = false, _mut364 = false, _mut365 = false, _mut366 = false, _mut367 = false, _mut368 = false, _mut369 = false, _mut370 = false, _mut371 = false, _mut372 = false, _mut373 = false, _mut374 = false, _mut375 = false, _mut376 = false, _mut377 = false, _mut378 = false, _mut379 = false, _mut380 = false, _mut381 = false, _mut382 = false, _mut383 = false, _mut384 = false, _mut385 = false, _mut386 = false, _mut387 = false, _mut388 = false, _mut389 = false, _mut390 = false, _mut391 = false, _mut392 = false, _mut393 = false, _mut394 = false, _mut395 = false, _mut396 = false, _mut397 = false, _mut398 = false, _mut399 = false, _mut400 = false, _mut401 = false, _mut402 = false, _mut403 = false, _mut404 = false, _mut405 = false, _mut406 = false, _mut407 = false, _mut408 = false, _mut409 = false, _mut410 = false, _mut411 = false, _mut412 = false, _mut413 = false, _mut414 = false, _mut415 = false, _mut416 = false, _mut417 = false, _mut418 = false, _mut419 = false, _mut420 = false, _mut421 = false, _mut422 = false, _mut423 = false, _mut424 = false, _mut425 = false, _mut426 = false, _mut427 = false, _mut428 = false, _mut429 = false, _mut430 = false, _mut431 = false, _mut432 = false, _mut433 = false, _mut434 = false, _mut435 = false, _mut436 = false, _mut437 = false, _mut438 = false, _mut439 = false, _mut440 = false, _mut441 = false, _mut442 = false, _mut443 = false, _mut444 = false, _mut445 = false, _mut446 = false, _mut447 = false, _mut448 = false, _mut449 = false, _mut450 = false, _mut451 = false, _mut452 = false, _mut453 = false, _mut454 = false, _mut455 = false, _mut456 = false, _mut457 = false, _mut458 = false, _mut459 = false, _mut460 = false, _mut461 = false, _mut462 = false, _mut463 = false, _mut464 = false, _mut465 = false, _mut466 = false, _mut467 = false, _mut468 = false, _mut469 = false, _mut470 = false, _mut471 = false, _mut472 = false, _mut473 = false, _mut474 = false, _mut475 = false, _mut476 = false, _mut477 = false, _mut478 = false, _mut479 = false, _mut480 = false, _mut481 = false, _mut482 = false, _mut483 = false, _mut484 = false, _mut485 = false, _mut486 = false, _mut487 = false, _mut488 = false, _mut489 = false, _mut490 = false, _mut491 = false, _mut492 = false, _mut493 = false, _mut494 = false, _mut495 = false, _mut496 = false, _mut497 = false, _mut498 = false, _mut499 = false, _mut500 = false, _mut501 = false, _mut502 = false, _mut503 = false, _mut504 = false, _mut505 = false, _mut506 = false, _mut507 = false, _mut508 = false, _mut509 = false, _mut510 = false, _mut511 = false, _mut512 = false, _mut513 = false, _mut514 = false, _mut515 = false, _mut516 = false, _mut517 = false, _mut518 = false, _mut519 = false, _mut520 = false, _mut521 = false, _mut522 = false, _mut523 = false, _mut524 = false, _mut525 = false, _mut526 = false, _mut527 = false, _mut528 = false, _mut529 = false, _mut530 = false, _mut531 = false, _mut532 = false, _mut533 = false, _mut534 = false, _mut535 = false, _mut536 = false, _mut537 = false, _mut538 = false, _mut539 = false, _mut540 = false, _mut541 = false, _mut542 = false, _mut543 = false, _mut544 = false, _mut545 = false, _mut546 = false, _mut547 = false, _mut548 = false, _mut549 = false, _mut550 = false, _mut551 = false, _mut552 = false, _mut553 = false, _mut554 = false, _mut555 = false, _mut556 = false, _mut557 = false, _mut558 = false, _mut559 = false, _mut560 = false, _mut561 = false, _mut562 = false, _mut563 = false, _mut564 = false, _mut565 = false, _mut566 = false, _mut567 = false, _mut568 = false, _mut569 = false, _mut570 = false, _mut571 = false, _mut572 = false, _mut573 = false, _mut574 = false, _mut575 = false, _mut576 = false, _mut577 = false, _mut578 = false, _mut579 = false, _mut580 = false, _mut581 = false, _mut582 = false, _mut583 = false, _mut584 = false, _mut585 = false, _mut586 = false, _mut587 = false, _mut588 = false, _mut589 = false, _mut590 = false, _mut591 = false, _mut592 = false, _mut593 = false, _mut594 = false, _mut595 = false, _mut596 = false, _mut597 = false, _mut598 = false, _mut599 = false, _mut600 = false, _mut601 = false, _mut602 = false, _mut603 = false, _mut604 = false, _mut605 = false, _mut606 = false, _mut607 = false, _mut608 = false, _mut609 = false, _mut610 = false, _mut611 = false, _mut612 = false, _mut613 = false, _mut614 = false, _mut615 = false, _mut616 = false, _mut617 = false, _mut618 = false, _mut619 = false, _mut620 = false, _mut621 = false, _mut622 = false, _mut623 = false, _mut624 = false, _mut625 = false, _mut626 = false, _mut627 = false, _mut628 = false, _mut629 = false, _mut630 = false, _mut631 = false, _mut632 = false, _mut633 = false, _mut634 = false, _mut635 = false, _mut636 = false, _mut637 = false, _mut638 = false, _mut639 = false, _mut640 = false, _mut641 = false, _mut642 = false, _mut643 = false, _mut644 = false, _mut645 = false, _mut646 = false, _mut647 = false, _mut648 = false, _mut649 = false, _mut650 = false, _mut651 = false, _mut652 = false, _mut653 = false, _mut654 = false, _mut655 = false, _mut656 = false, _mut657 = false, _mut658 = false, _mut659 = false, _mut660 = false, _mut661 = false, _mut662 = false, _mut663 = false, _mut664 = false, _mut665 = false, _mut666 = false, _mut667 = false, _mut668 = false, _mut669 = false, _mut670 = false, _mut671 = false;

    /**
     * A fraction representing "2 / 1".
     */
    public static final BigFraction TWO = new BigFraction(2);

    /**
     * A fraction representing "1".
     */
    public static final BigFraction ONE = new BigFraction(1);

    /**
     * A fraction representing "0".
     */
    public static final BigFraction ZERO = new BigFraction(0);

    /**
     * A fraction representing "-1 / 1".
     */
    public static final BigFraction MINUS_ONE = new BigFraction(-1);

    /**
     * A fraction representing "4/5".
     */
    public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);

    /**
     * A fraction representing "1/5".
     */
    public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);

    /**
     * A fraction representing "1/2".
     */
    public static final BigFraction ONE_HALF = new BigFraction(1, 2);

    /**
     * A fraction representing "1/4".
     */
    public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);

    /**
     * A fraction representing "1/3".
     */
    public static final BigFraction ONE_THIRD = new BigFraction(1, 3);

    /**
     * A fraction representing "3/5".
     */
    public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);

    /**
     * A fraction representing "3/4".
     */
    public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);

    /**
     * A fraction representing "2/5".
     */
    public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);

    /**
     * A fraction representing "2/4".
     */
    public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);

    /**
     * A fraction representing "2/3".
     */
    public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -5630213147331578515L;

    /**
     * <code>BigInteger</code> representation of 100.
     */
    private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100);

    /**
     * The numerator.
     */
    private final BigInteger numerator;

    /**
     * The denominator.
     */
    private final BigInteger denominator;

    /**
     * <p>
     * Create a {@link BigFraction} equivalent to the passed {@code BigInteger}, ie
     * "num / 1".
     * </p>
     *
     * @param num
     *            the numerator.
     */
    public BigFraction(final BigInteger num) {
        this(num, BigInteger.ONE);
    }

    /**
     * Create a {@link BigFraction} given the numerator and denominator as
     * {@code BigInteger}. The {@link BigFraction} is reduced to lowest terms.
     *
     * @param num the numerator, must not be {@code null}.
     * @param den the denominator, must not be {@code null}.
     * @throws ZeroException if the denominator is zero.
     * @throws NullArgumentException if either of the arguments is null
     */
    public BigFraction(BigInteger num, BigInteger den) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.BigFraction_119");
        MathUtils.checkNotNull(num, LocalizedFormats.NUMERATOR);
        MathUtils.checkNotNull(den, LocalizedFormats.DENOMINATOR);
        if (ROR_equals(den.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_119", _mut298, _mut299, _mut300, _mut301, _mut302)) {
            throw new ZeroException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        if (ROR_equals(num.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_119", _mut303, _mut304, _mut305, _mut306, _mut307)) {
            numerator = BigInteger.ZERO;
            denominator = BigInteger.ONE;
        } else {
            // reduce numerator and denominator by greatest common denominator
            final BigInteger gcd = num.gcd(den);
            if (ROR_less(BigInteger.ONE.compareTo(gcd), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_119", _mut308, _mut309, _mut310, _mut311, _mut312)) {
                num = num.divide(gcd);
                den = den.divide(gcd);
            }
            // move sign to numerator
            if (ROR_equals(den.signum(), -1, "org.apache.commons.math3.fraction.BigFraction.BigFraction_119", _mut313, _mut314, _mut315, _mut316, _mut317)) {
                num = num.negate();
                den = den.negate();
            }
            // store the values in the final fields
            numerator = num;
            denominator = den;
        }
    }

    /**
     * Create a fraction given the double value.
     * <p>
     * This constructor behaves <em>differently</em> from
     * {@link #BigFraction(double, double, int)}. It converts the double value
     * exactly, considering its internal bits representation. This works for all
     * values except NaN and infinities and does not requires any loop or
     * convergence threshold.
     * </p>
     * <p>
     * Since this conversion is exact and since double numbers are sometimes
     * approximated, the fraction created may seem strange in some cases. For example,
     * calling <code>new BigFraction(1.0 / 3.0)</code> does <em>not</em> create
     * the fraction 1/3, but the fraction 6004799503160661 / 18014398509481984
     * because the double number passed to the constructor is not exactly 1/3
     * (this number cannot be stored exactly in IEEE754).
     * </p>
     * @see #BigFraction(double, double, int)
     * @param value the double value to convert to a fraction.
     * @exception MathIllegalArgumentException if value is NaN or infinite
     */
    public BigFraction(final double value) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.BigFraction_171");
        if (Double.isNaN(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION);
        }
        if (Double.isInfinite(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION);
        }
        // compute m and k such that value = m * 2^k
        final long bits = Double.doubleToLongBits(value);
        final long sign = bits & 0x8000000000000000L;
        final long exponent = bits & 0x7ff0000000000000L;
        long m = bits & 0x000fffffffffffffL;
        if (ROR_not_equals(exponent, 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut318, _mut319, _mut320, _mut321, _mut322)) {
            // this was a normalized number, add the implicit most significant bit
            m |= 0x0010000000000000L;
        }
        if (ROR_not_equals(sign, 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut323, _mut324, _mut325, _mut326, _mut327)) {
            m = -m;
        }
        int k = AOR_minus(((int) (exponent >> 52)), 1075, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut328, _mut329, _mut330, _mut331);
        while ((_mut342 ? ((ROR_not_equals((m & 0x001ffffffffffffeL), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut332, _mut333, _mut334, _mut335, _mut336)) || (ROR_equals((m & 0x1), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut337, _mut338, _mut339, _mut340, _mut341))) : ((ROR_not_equals((m & 0x001ffffffffffffeL), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut332, _mut333, _mut334, _mut335, _mut336)) && (ROR_equals((m & 0x1), 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut337, _mut338, _mut339, _mut340, _mut341))))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.BigFraction_171");
            m >>= 1;
            ++k;
        }
        if (ROR_less(k, 0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_171", _mut343, _mut344, _mut345, _mut346, _mut347)) {
            numerator = BigInteger.valueOf(m);
            denominator = BigInteger.ZERO.flipBit(-k);
        } else {
            numerator = BigInteger.valueOf(m).multiply(BigInteger.ZERO.flipBit(k));
            denominator = BigInteger.ONE;
        }
    }

    /**
     * Create a fraction given the double value and maximum error allowed.
     * <p>
     * References:
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/ContinuedFraction.html">
     * Continued Fraction</a> equations (11) and (22)-(26)</li>
     * </ul>
     * </p>
     *
     * @param value
     *            the double value to convert to a fraction.
     * @param epsilon
     *            maximum error allowed. The resulting fraction is within
     *            <code>epsilon</code> of <code>value</code>, in absolute terms.
     * @param maxIterations
     *            maximum number of convergents.
     * @throws FractionConversionException
     *             if the continued fraction failed to converge.
     * @see #BigFraction(double)
     */
    public BigFraction(final double value, final double epsilon, final int maxIterations) throws FractionConversionException {
        this(value, epsilon, Integer.MAX_VALUE, maxIterations);
    }

    /**
     * Create a fraction given the double value and either the maximum error
     * allowed or the maximum number of denominator digits.
     * <p>
     *
     * NOTE: This constructor is called with EITHER - a valid epsilon value and
     * the maxDenominator set to Integer.MAX_VALUE (that way the maxDenominator
     * has no effect). OR - a valid maxDenominator value and the epsilon value
     * set to zero (that way epsilon only has effect if there is an exact match
     * before the maxDenominator value is reached).
     * </p>
     * <p>
     *
     * It has been done this way so that the same code can be (re)used for both
     * scenarios. However this could be confusing to users if it were part of
     * the public API and this constructor should therefore remain PRIVATE.
     * </p>
     *
     * See JIRA issue ticket MATH-181 for more details:
     *
     * https://issues.apache.org/jira/browse/MATH-181
     *
     * @param value
     *            the double value to convert to a fraction.
     * @param epsilon
     *            maximum error allowed. The resulting fraction is within
     *            <code>epsilon</code> of <code>value</code>, in absolute terms.
     * @param maxDenominator
     *            maximum denominator value allowed.
     * @param maxIterations
     *            maximum number of convergents.
     * @throws FractionConversionException
     *             if the continued fraction failed to converge.
     */
    private BigFraction(final double value, final double epsilon, final int maxDenominator, int maxIterations) throws FractionConversionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.BigFraction_268");
        long overflow = Integer.MAX_VALUE;
        double r0 = value;
        long a0 = (long) FastMath.floor(r0);
        if (ROR_greater(FastMath.abs(a0), overflow, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut348, _mut349, _mut350, _mut351, _mut352)) {
            throw new FractionConversionException(value, a0, 1l);
        }
        // to iterations.
        if (ROR_less(FastMath.abs(AOR_minus(a0, value, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut353, _mut354, _mut355, _mut356)), epsilon, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut357, _mut358, _mut359, _mut360, _mut361)) {
            numerator = BigInteger.valueOf(a0);
            denominator = BigInteger.ONE;
            return;
        }
        long p0 = 1;
        long q0 = 0;
        long p1 = a0;
        long q1 = 1;
        long p2 = 0;
        long q2 = 1;
        int n = 0;
        boolean stop = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.BigFraction_268");
            ++n;
            final double r1 = AOR_divide(1.0, (AOR_minus(r0, a0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut362, _mut363, _mut364, _mut365)), "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut366, _mut367, _mut368, _mut369);
            final long a1 = (long) FastMath.floor(r1);
            p2 = AOR_plus((AOR_multiply(a1, p1, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut370, _mut371, _mut372, _mut373)), p0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut374, _mut375, _mut376, _mut377);
            q2 = AOR_plus((AOR_multiply(a1, q1, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut378, _mut379, _mut380, _mut381)), q0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut382, _mut383, _mut384, _mut385);
            if ((_mut396 ? ((ROR_greater(p2, overflow, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut386, _mut387, _mut388, _mut389, _mut390)) && (ROR_greater(q2, overflow, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut391, _mut392, _mut393, _mut394, _mut395))) : ((ROR_greater(p2, overflow, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut386, _mut387, _mut388, _mut389, _mut390)) || (ROR_greater(q2, overflow, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut391, _mut392, _mut393, _mut394, _mut395))))) {
                // q2 may overflow in the next iteration; in this case return the last one.
                if ((_mut407 ? (ROR_equals(epsilon, 0.0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut397, _mut398, _mut399, _mut400, _mut401) || ROR_less(FastMath.abs(q1), maxDenominator, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut402, _mut403, _mut404, _mut405, _mut406)) : (ROR_equals(epsilon, 0.0, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut397, _mut398, _mut399, _mut400, _mut401) && ROR_less(FastMath.abs(q1), maxDenominator, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut402, _mut403, _mut404, _mut405, _mut406)))) {
                    break;
                }
                throw new FractionConversionException(value, p2, q2);
            }
            final double convergent = AOR_divide((double) p2, (double) q2, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut408, _mut409, _mut410, _mut411);
            if ((_mut432 ? ((_mut426 ? ((ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut412, _mut413, _mut414, _mut415, _mut416)) || (ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut417, _mut418, _mut419, _mut420)), epsilon, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut421, _mut422, _mut423, _mut424, _mut425))) : ((ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut412, _mut413, _mut414, _mut415, _mut416)) && (ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut417, _mut418, _mut419, _mut420)), epsilon, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut421, _mut422, _mut423, _mut424, _mut425)))) || (ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut427, _mut428, _mut429, _mut430, _mut431))) : ((_mut426 ? ((ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut412, _mut413, _mut414, _mut415, _mut416)) || (ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut417, _mut418, _mut419, _mut420)), epsilon, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut421, _mut422, _mut423, _mut424, _mut425))) : ((ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut412, _mut413, _mut414, _mut415, _mut416)) && (ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut417, _mut418, _mut419, _mut420)), epsilon, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut421, _mut422, _mut423, _mut424, _mut425)))) && (ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut427, _mut428, _mut429, _mut430, _mut431))))) {
                p0 = p1;
                p1 = p2;
                q0 = q1;
                q1 = q2;
                a0 = a1;
                r0 = r1;
            } else {
                stop = true;
            }
        } while (!stop);
        if (ROR_greater_equals(n, maxIterations, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut433, _mut434, _mut435, _mut436, _mut437)) {
            throw new FractionConversionException(value, maxIterations);
        }
        if (ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.BigFraction.BigFraction_268", _mut438, _mut439, _mut440, _mut441, _mut442)) {
            numerator = BigInteger.valueOf(p2);
            denominator = BigInteger.valueOf(q2);
        } else {
            numerator = BigInteger.valueOf(p1);
            denominator = BigInteger.valueOf(q1);
        }
    }

    /**
     * Create a fraction given the double value and maximum denominator.
     * <p>
     * References:
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/ContinuedFraction.html">
     * Continued Fraction</a> equations (11) and (22)-(26)</li>
     * </ul>
     * </p>
     *
     * @param value
     *            the double value to convert to a fraction.
     * @param maxDenominator
     *            The maximum allowed value for denominator.
     * @throws FractionConversionException
     *             if the continued fraction failed to converge.
     */
    public BigFraction(final double value, final int maxDenominator) throws FractionConversionException {
        this(value, 0, maxDenominator, 100);
    }

    /**
     * <p>
     * Create a {@link BigFraction} equivalent to the passed {@code int}, ie
     * "num / 1".
     * </p>
     *
     * @param num
     *            the numerator.
     */
    public BigFraction(final int num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    /**
     * <p>
     * Create a {@link BigFraction} given the numerator and denominator as simple
     * {@code int}. The {@link BigFraction} is reduced to lowest terms.
     * </p>
     *
     * @param num
     *            the numerator.
     * @param den
     *            the denominator.
     */
    public BigFraction(final int num, final int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    /**
     * <p>
     * Create a {@link BigFraction} equivalent to the passed long, ie "num / 1".
     * </p>
     *
     * @param num
     *            the numerator.
     */
    public BigFraction(final long num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    /**
     * <p>
     * Create a {@link BigFraction} given the numerator and denominator as simple
     * {@code long}. The {@link BigFraction} is reduced to lowest terms.
     * </p>
     *
     * @param num
     *            the numerator.
     * @param den
     *            the denominator.
     */
    public BigFraction(final long num, final long den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    /**
     * <p>
     * Creates a <code>BigFraction</code> instance with the 2 parts of a fraction
     * Y/Z.
     * </p>
     *
     * <p>
     * Any negative signs are resolved to be on the numerator.
     * </p>
     *
     * @param numerator
     *            the numerator, for example the three in 'three sevenths'.
     * @param denominator
     *            the denominator, for example the seven in 'three sevenths'.
     * @return a new fraction instance, with the numerator and denominator
     *         reduced.
     * @throws ArithmeticException
     *             if the denominator is <code>zero</code>.
     */
    public static BigFraction getReducedFraction(final int numerator, final int denominator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.getReducedFraction_436");
        if (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.BigFraction.getReducedFraction_436", _mut443, _mut444, _mut445, _mut446, _mut447)) {
            // normalize zero.
            return ZERO;
        }
        return new BigFraction(numerator, denominator);
    }

    /**
     * <p>
     * Returns the absolute value of this {@link BigFraction}.
     * </p>
     *
     * @return the absolute value as a {@link BigFraction}.
     */
    public BigFraction abs() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.abs_452");
        return (ROR_equals(numerator.signum(), 1, "org.apache.commons.math3.fraction.BigFraction.abs_452", _mut448, _mut449, _mut450, _mut451, _mut452)) ? this : negate();
    }

    /**
     * <p>
     * Adds the value of this fraction to the passed {@link BigInteger},
     * returning the result in reduced form.
     * </p>
     *
     * @param bg
     *            the {@link BigInteger} to add, must'nt be <code>null</code>.
     * @return a <code>BigFraction</code> instance with the resulting values.
     * @throws NullArgumentException
     *             if the {@link BigInteger} is <code>null</code>.
     */
    public BigFraction add(final BigInteger bg) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.add_468");
        MathUtils.checkNotNull(bg);
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.add_468", _mut453, _mut454, _mut455, _mut456, _mut457)) {
            return new BigFraction(bg);
        }
        if (ROR_equals(bg.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.add_468", _mut458, _mut459, _mut460, _mut461, _mut462)) {
            return this;
        }
        return new BigFraction(numerator.add(denominator.multiply(bg)), denominator);
    }

    /**
     * <p>
     * Adds the value of this fraction to the passed {@code integer}, returning
     * the result in reduced form.
     * </p>
     *
     * @param i
     *            the {@code integer} to add.
     * @return a <code>BigFraction</code> instance with the resulting values.
     */
    public BigFraction add(final int i) {
        return add(BigInteger.valueOf(i));
    }

    /**
     * <p>
     * Adds the value of this fraction to the passed {@code long}, returning
     * the result in reduced form.
     * </p>
     *
     * @param l
     *            the {@code long} to add.
     * @return a <code>BigFraction</code> instance with the resulting values.
     */
    public BigFraction add(final long l) {
        return add(BigInteger.valueOf(l));
    }

    /**
     * <p>
     * Adds the value of this fraction to another, returning the result in
     * reduced form.
     * </p>
     *
     * @param fraction
     *            the {@link BigFraction} to add, must not be <code>null</code>.
     * @return a {@link BigFraction} instance with the resulting values.
     * @throws NullArgumentException if the {@link BigFraction} is {@code null}.
     */
    public BigFraction add(final BigFraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.add_520");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (ROR_equals(fraction.numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.add_520", _mut463, _mut464, _mut465, _mut466, _mut467)) {
            return this;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.add_520", _mut468, _mut469, _mut470, _mut471, _mut472)) {
            return fraction;
        }
        BigInteger num = null;
        BigInteger den = null;
        if (denominator.equals(fraction.denominator)) {
            num = numerator.add(fraction.numerator);
            den = denominator;
        } else {
            num = (numerator.multiply(fraction.denominator)).add((fraction.numerator).multiply(denominator));
            den = denominator.multiply(fraction.denominator);
        }
        if (ROR_equals(num.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.add_520", _mut473, _mut474, _mut475, _mut476, _mut477)) {
            return ZERO;
        }
        return new BigFraction(num, den);
    }

    /**
     * <p>
     * Gets the fraction as a <code>BigDecimal</code>. This calculates the
     * fraction as the numerator divided by denominator.
     * </p>
     *
     * @return the fraction as a <code>BigDecimal</code>.
     * @throws ArithmeticException
     *             if the exact quotient does not have a terminating decimal
     *             expansion.
     * @see BigDecimal
     */
    public BigDecimal bigDecimalValue() {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator));
    }

    /**
     * <p>
     * Gets the fraction as a <code>BigDecimal</code> following the passed
     * rounding mode. This calculates the fraction as the numerator divided by
     * denominator.
     * </p>
     *
     * @param roundingMode
     *            rounding mode to apply. see {@link BigDecimal} constants.
     * @return the fraction as a <code>BigDecimal</code>.
     * @throws IllegalArgumentException
     *             if {@code roundingMode} does not represent a valid rounding
     *             mode.
     * @see BigDecimal
     */
    public BigDecimal bigDecimalValue(final int roundingMode) {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), roundingMode);
    }

    /**
     * <p>
     * Gets the fraction as a <code>BigDecimal</code> following the passed scale
     * and rounding mode. This calculates the fraction as the numerator divided
     * by denominator.
     * </p>
     *
     * @param scale
     *            scale of the <code>BigDecimal</code> quotient to be returned.
     *            see {@link BigDecimal} for more information.
     * @param roundingMode
     *            rounding mode to apply. see {@link BigDecimal} constants.
     * @return the fraction as a <code>BigDecimal</code>.
     * @see BigDecimal
     */
    public BigDecimal bigDecimalValue(final int scale, final int roundingMode) {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), scale, roundingMode);
    }

    /**
     * <p>
     * Compares this object to another based on size.
     * </p>
     *
     * @param object
     *            the object to compare to, must not be <code>null</code>.
     * @return -1 if this is less than {@code object}, +1 if this is greater
     *         than {@code object}, 0 if they are equal.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final BigFraction object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.compareTo_615");
        int lhsSigNum = numerator.signum();
        int rhsSigNum = object.numerator.signum();
        if (ROR_not_equals(lhsSigNum, rhsSigNum, "org.apache.commons.math3.fraction.BigFraction.compareTo_615", _mut478, _mut479, _mut480, _mut481, _mut482)) {
            return (ROR_greater(lhsSigNum, rhsSigNum, "org.apache.commons.math3.fraction.BigFraction.compareTo_615", _mut483, _mut484, _mut485, _mut486, _mut487)) ? 1 : -1;
        }
        if (ROR_equals(lhsSigNum, 0, "org.apache.commons.math3.fraction.BigFraction.compareTo_615", _mut488, _mut489, _mut490, _mut491, _mut492)) {
            return 0;
        }
        BigInteger nOd = numerator.multiply(object.denominator);
        BigInteger dOn = denominator.multiply(object.numerator);
        return nOd.compareTo(dOn);
    }

    /**
     * <p>
     * Divide the value of this fraction by the passed {@code BigInteger},
     * ie {@code this * 1 / bg}, returning the result in reduced form.
     * </p>
     *
     * @param bg the {@code BigInteger} to divide by, must not be {@code null}
     * @return a {@link BigFraction} instance with the resulting values
     * @throws NullArgumentException if the {@code BigInteger} is {@code null}
     * @throws MathArithmeticException if the fraction to divide by is zero
     */
    public BigFraction divide(final BigInteger bg) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.divide_642");
        if (bg == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (ROR_equals(bg.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.divide_642", _mut493, _mut494, _mut495, _mut496, _mut497)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.divide_642", _mut498, _mut499, _mut500, _mut501, _mut502)) {
            return ZERO;
        }
        return new BigFraction(numerator, denominator.multiply(bg));
    }

    /**
     * <p>
     * Divide the value of this fraction by the passed {@code int}, ie
     * {@code this * 1 / i}, returning the result in reduced form.
     * </p>
     *
     * @param i the {@code int} to divide by
     * @return a {@link BigFraction} instance with the resulting values
     * @throws MathArithmeticException if the fraction to divide by is zero
     */
    public BigFraction divide(final int i) {
        return divide(BigInteger.valueOf(i));
    }

    /**
     * <p>
     * Divide the value of this fraction by the passed {@code long}, ie
     * {@code this * 1 / l}, returning the result in reduced form.
     * </p>
     *
     * @param l the {@code long} to divide by
     * @return a {@link BigFraction} instance with the resulting values
     * @throws MathArithmeticException if the fraction to divide by is zero
     */
    public BigFraction divide(final long l) {
        return divide(BigInteger.valueOf(l));
    }

    /**
     * <p>
     * Divide the value of this fraction by another, returning the result in
     * reduced form.
     * </p>
     *
     * @param fraction Fraction to divide by, must not be {@code null}.
     * @return a {@link BigFraction} instance with the resulting values.
     * @throws NullArgumentException if the {@code fraction} is {@code null}.
     * @throws MathArithmeticException if the fraction to divide by is zero
     */
    public BigFraction divide(final BigFraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.divide_694");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (ROR_equals(fraction.numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.divide_694", _mut503, _mut504, _mut505, _mut506, _mut507)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.divide_694", _mut508, _mut509, _mut510, _mut511, _mut512)) {
            return ZERO;
        }
        return multiply(fraction.reciprocal());
    }

    /**
     * <p>
     * Gets the fraction as a {@code double}. This calculates the fraction as
     * the numerator divided by denominator.
     * </p>
     *
     * @return the fraction as a {@code double}
     * @see java.lang.Number#doubleValue()
     */
    @Override
    public double doubleValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.doubleValue_717");
        double result = AOR_divide(numerator.doubleValue(), denominator.doubleValue(), "org.apache.commons.math3.fraction.BigFraction.doubleValue_717", _mut513, _mut514, _mut515, _mut516);
        if (Double.isNaN(result)) {
            // Calculate how far to shift them to put them in range.
            int shift = AOR_minus(FastMath.max(numerator.bitLength(), denominator.bitLength()), FastMath.getExponent(Double.MAX_VALUE), "org.apache.commons.math3.fraction.BigFraction.doubleValue_717", _mut517, _mut518, _mut519, _mut520);
            result = AOR_divide(numerator.shiftRight(shift).doubleValue(), denominator.shiftRight(shift).doubleValue(), "org.apache.commons.math3.fraction.BigFraction.doubleValue_717", _mut521, _mut522, _mut523, _mut524);
        }
        return result;
    }

    /**
     * <p>
     * Test for the equality of two fractions. If the lowest term numerator and
     * denominators are the same for both fractions, the two fractions are
     * considered to be equal.
     * </p>
     *
     * @param other
     *            fraction to test for equality to this fraction, can be
     *            <code>null</code>.
     * @return true if two fractions are equal, false if object is
     *         <code>null</code>, not an instance of {@link BigFraction}, or not
     *         equal to this fraction instance.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.equals_746");
        boolean ret = false;
        if (this == other) {
            ret = true;
        } else if (other instanceof BigFraction) {
            BigFraction rhs = ((BigFraction) other).reduce();
            BigFraction thisOne = this.reduce();
            ret = (_mut525 ? (thisOne.numerator.equals(rhs.numerator) || thisOne.denominator.equals(rhs.denominator)) : (thisOne.numerator.equals(rhs.numerator) && thisOne.denominator.equals(rhs.denominator)));
        }
        return ret;
    }

    /**
     * <p>
     * Gets the fraction as a {@code float}. This calculates the fraction as
     * the numerator divided by denominator.
     * </p>
     *
     * @return the fraction as a {@code float}.
     * @see java.lang.Number#floatValue()
     */
    @Override
    public float floatValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.floatValue_770");
        float result = AOR_divide(numerator.floatValue(), denominator.floatValue(), "org.apache.commons.math3.fraction.BigFraction.floatValue_770", _mut526, _mut527, _mut528, _mut529);
        if (Double.isNaN(result)) {
            // Calculate how far to shift them to put them in range.
            int shift = AOR_minus(FastMath.max(numerator.bitLength(), denominator.bitLength()), FastMath.getExponent(Float.MAX_VALUE), "org.apache.commons.math3.fraction.BigFraction.floatValue_770", _mut530, _mut531, _mut532, _mut533);
            result = AOR_divide(numerator.shiftRight(shift).floatValue(), denominator.shiftRight(shift).floatValue(), "org.apache.commons.math3.fraction.BigFraction.floatValue_770", _mut534, _mut535, _mut536, _mut537);
        }
        return result;
    }

    /**
     * <p>
     * Access the denominator as a <code>BigInteger</code>.
     * </p>
     *
     * @return the denominator as a <code>BigInteger</code>.
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * <p>
     * Access the denominator as a {@code int}.
     * </p>
     *
     * @return the denominator as a {@code int}.
     */
    public int getDenominatorAsInt() {
        return denominator.intValue();
    }

    /**
     * <p>
     * Access the denominator as a {@code long}.
     * </p>
     *
     * @return the denominator as a {@code long}.
     */
    public long getDenominatorAsLong() {
        return denominator.longValue();
    }

    /**
     * <p>
     * Access the numerator as a <code>BigInteger</code>.
     * </p>
     *
     * @return the numerator as a <code>BigInteger</code>.
     */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * <p>
     * Access the numerator as a {@code int}.
     * </p>
     *
     * @return the numerator as a {@code int}.
     */
    public int getNumeratorAsInt() {
        return numerator.intValue();
    }

    /**
     * <p>
     * Access the numerator as a {@code long}.
     * </p>
     *
     * @return the numerator as a {@code long}.
     */
    public long getNumeratorAsLong() {
        return numerator.longValue();
    }

    /**
     * <p>
     * Gets a hashCode for the fraction.
     * </p>
     *
     * @return a hash code value for this object.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.hashCode_858");
        return AOR_plus(AOR_multiply(37, (AOR_plus(AOR_multiply(37, 17, "org.apache.commons.math3.fraction.BigFraction.hashCode_858", _mut538, _mut539, _mut540, _mut541), numerator.hashCode(), "org.apache.commons.math3.fraction.BigFraction.hashCode_858", _mut542, _mut543, _mut544, _mut545)), "org.apache.commons.math3.fraction.BigFraction.hashCode_858", _mut546, _mut547, _mut548, _mut549), denominator.hashCode(), "org.apache.commons.math3.fraction.BigFraction.hashCode_858", _mut550, _mut551, _mut552, _mut553);
    }

    /**
     * <p>
     * Gets the fraction as an {@code int}. This returns the whole number part
     * of the fraction.
     * </p>
     *
     * @return the whole number fraction part.
     * @see java.lang.Number#intValue()
     */
    @Override
    public int intValue() {
        return numerator.divide(denominator).intValue();
    }

    /**
     * <p>
     * Gets the fraction as a {@code long}. This returns the whole number part
     * of the fraction.
     * </p>
     *
     * @return the whole number fraction part.
     * @see java.lang.Number#longValue()
     */
    @Override
    public long longValue() {
        return numerator.divide(denominator).longValue();
    }

    /**
     * <p>
     * Multiplies the value of this fraction by the passed
     * <code>BigInteger</code>, returning the result in reduced form.
     * </p>
     *
     * @param bg the {@code BigInteger} to multiply by.
     * @return a {@code BigFraction} instance with the resulting values.
     * @throws NullArgumentException if {@code bg} is {@code null}.
     */
    public BigFraction multiply(final BigInteger bg) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.multiply_901");
        if (bg == null) {
            throw new NullArgumentException();
        }
        if ((_mut564 ? (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_901", _mut554, _mut555, _mut556, _mut557, _mut558) && ROR_equals(bg.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_901", _mut559, _mut560, _mut561, _mut562, _mut563)) : (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_901", _mut554, _mut555, _mut556, _mut557, _mut558) || ROR_equals(bg.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_901", _mut559, _mut560, _mut561, _mut562, _mut563)))) {
            return ZERO;
        }
        return new BigFraction(bg.multiply(numerator), denominator);
    }

    /**
     * <p>
     * Multiply the value of this fraction by the passed {@code int}, returning
     * the result in reduced form.
     * </p>
     *
     * @param i
     *            the {@code int} to multiply by.
     * @return a {@link BigFraction} instance with the resulting values.
     */
    public BigFraction multiply(final int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.multiply_921");
        if ((_mut575 ? (ROR_equals(i, 0, "org.apache.commons.math3.fraction.BigFraction.multiply_921", _mut565, _mut566, _mut567, _mut568, _mut569) && ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_921", _mut570, _mut571, _mut572, _mut573, _mut574)) : (ROR_equals(i, 0, "org.apache.commons.math3.fraction.BigFraction.multiply_921", _mut565, _mut566, _mut567, _mut568, _mut569) || ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_921", _mut570, _mut571, _mut572, _mut573, _mut574)))) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf(i));
    }

    /**
     * <p>
     * Multiply the value of this fraction by the passed {@code long},
     * returning the result in reduced form.
     * </p>
     *
     * @param l
     *            the {@code long} to multiply by.
     * @return a {@link BigFraction} instance with the resulting values.
     */
    public BigFraction multiply(final long l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.multiply_939");
        if ((_mut586 ? (ROR_equals(l, 0, "org.apache.commons.math3.fraction.BigFraction.multiply_939", _mut576, _mut577, _mut578, _mut579, _mut580) && ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_939", _mut581, _mut582, _mut583, _mut584, _mut585)) : (ROR_equals(l, 0, "org.apache.commons.math3.fraction.BigFraction.multiply_939", _mut576, _mut577, _mut578, _mut579, _mut580) || ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_939", _mut581, _mut582, _mut583, _mut584, _mut585)))) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf(l));
    }

    /**
     * <p>
     * Multiplies the value of this fraction by another, returning the result in
     * reduced form.
     * </p>
     *
     * @param fraction Fraction to multiply by, must not be {@code null}.
     * @return a {@link BigFraction} instance with the resulting values.
     * @throws NullArgumentException if {@code fraction} is {@code null}.
     */
    public BigFraction multiply(final BigFraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.multiply_957");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if ((_mut597 ? (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_957", _mut587, _mut588, _mut589, _mut590, _mut591) && ROR_equals(fraction.numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_957", _mut592, _mut593, _mut594, _mut595, _mut596)) : (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_957", _mut587, _mut588, _mut589, _mut590, _mut591) || ROR_equals(fraction.numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.multiply_957", _mut592, _mut593, _mut594, _mut595, _mut596)))) {
            return ZERO;
        }
        return new BigFraction(numerator.multiply(fraction.numerator), denominator.multiply(fraction.denominator));
    }

    /**
     * <p>
     * Return the additive inverse of this fraction, returning the result in
     * reduced form.
     * </p>
     *
     * @return the negation of this fraction.
     */
    public BigFraction negate() {
        return new BigFraction(numerator.negate(), denominator);
    }

    /**
     * <p>
     * Gets the fraction percentage as a {@code double}. This calculates the
     * fraction as the numerator divided by denominator multiplied by 100.
     * </p>
     *
     * @return the fraction percentage as a {@code double}.
     */
    public double percentageValue() {
        return multiply(ONE_HUNDRED).doubleValue();
    }

    /**
     * <p>
     * Returns a {@code BigFraction} whose value is
     * {@code (this<sup>exponent</sup>)}, returning the result in reduced form.
     * </p>
     *
     * @param exponent
     *            exponent to which this {@code BigFraction} is to be
     *            raised.
     * @return <tt>this<sup>exponent</sup></tt>.
     */
    public BigFraction pow(final int exponent) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.pow_1004");
        if (ROR_equals(exponent, 0, "org.apache.commons.math3.fraction.BigFraction.pow_1004", _mut598, _mut599, _mut600, _mut601, _mut602)) {
            return ONE;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.pow_1004", _mut603, _mut604, _mut605, _mut606, _mut607)) {
            return this;
        }
        if (ROR_less(exponent, 0, "org.apache.commons.math3.fraction.BigFraction.pow_1004", _mut608, _mut609, _mut610, _mut611, _mut612)) {
            return new BigFraction(denominator.pow(-exponent), numerator.pow(-exponent));
        }
        return new BigFraction(numerator.pow(exponent), denominator.pow(exponent));
    }

    /**
     * <p>
     * Returns a <code>BigFraction</code> whose value is
     * <tt>(this<sup>exponent</sup>)</tt>, returning the result in reduced form.
     * </p>
     *
     * @param exponent
     *            exponent to which this <code>BigFraction</code> is to be raised.
     * @return <tt>this<sup>exponent</sup></tt> as a <code>BigFraction</code>.
     */
    public BigFraction pow(final long exponent) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.pow_1028");
        if (ROR_equals(exponent, 0, "org.apache.commons.math3.fraction.BigFraction.pow_1028", _mut613, _mut614, _mut615, _mut616, _mut617)) {
            return ONE;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.pow_1028", _mut618, _mut619, _mut620, _mut621, _mut622)) {
            return this;
        }
        if (ROR_less(exponent, 0, "org.apache.commons.math3.fraction.BigFraction.pow_1028", _mut623, _mut624, _mut625, _mut626, _mut627)) {
            return new BigFraction(ArithmeticUtils.pow(denominator, -exponent), ArithmeticUtils.pow(numerator, -exponent));
        }
        return new BigFraction(ArithmeticUtils.pow(numerator, exponent), ArithmeticUtils.pow(denominator, exponent));
    }

    /**
     * <p>
     * Returns a <code>BigFraction</code> whose value is
     * <tt>(this<sup>exponent</sup>)</tt>, returning the result in reduced form.
     * </p>
     *
     * @param exponent
     *            exponent to which this <code>BigFraction</code> is to be raised.
     * @return <tt>this<sup>exponent</sup></tt> as a <code>BigFraction</code>.
     */
    public BigFraction pow(final BigInteger exponent) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.pow_1054");
        if (ROR_equals(exponent.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.pow_1054", _mut628, _mut629, _mut630, _mut631, _mut632)) {
            return ONE;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.pow_1054", _mut633, _mut634, _mut635, _mut636, _mut637)) {
            return this;
        }
        if (ROR_equals(exponent.signum(), -1, "org.apache.commons.math3.fraction.BigFraction.pow_1054", _mut638, _mut639, _mut640, _mut641, _mut642)) {
            final BigInteger eNeg = exponent.negate();
            return new BigFraction(ArithmeticUtils.pow(denominator, eNeg), ArithmeticUtils.pow(numerator, eNeg));
        }
        return new BigFraction(ArithmeticUtils.pow(numerator, exponent), ArithmeticUtils.pow(denominator, exponent));
    }

    /**
     * <p>
     * Returns a <code>double</code> whose value is
     * <tt>(this<sup>exponent</sup>)</tt>, returning the result in reduced form.
     * </p>
     *
     * @param exponent
     *            exponent to which this <code>BigFraction</code> is to be raised.
     * @return <tt>this<sup>exponent</sup></tt>.
     */
    public double pow(final double exponent) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.pow_1081");
        return AOR_divide(FastMath.pow(numerator.doubleValue(), exponent), FastMath.pow(denominator.doubleValue(), exponent), "org.apache.commons.math3.fraction.BigFraction.pow_1081", _mut643, _mut644, _mut645, _mut646);
    }

    /**
     * <p>
     * Return the multiplicative inverse of this fraction.
     * </p>
     *
     * @return the reciprocal fraction.
     */
    public BigFraction reciprocal() {
        return new BigFraction(denominator, numerator);
    }

    /**
     * <p>
     * Reduce this <code>BigFraction</code> to its lowest terms.
     * </p>
     *
     * @return the reduced <code>BigFraction</code>. It doesn't change anything if
     *         the fraction can be reduced.
     */
    public BigFraction reduce() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.reduce_1105");
        final BigInteger gcd = numerator.gcd(denominator);
        if (ROR_less(BigInteger.ONE.compareTo(gcd), 0, "org.apache.commons.math3.fraction.BigFraction.reduce_1105", _mut647, _mut648, _mut649, _mut650, _mut651)) {
            return new BigFraction(numerator.divide(gcd), denominator.divide(gcd));
        } else {
            return this;
        }
    }

    /**
     * <p>
     * Subtracts the value of an {@link BigInteger} from the value of this
     * {@code BigFraction}, returning the result in reduced form.
     * </p>
     *
     * @param bg the {@link BigInteger} to subtract, cannot be {@code null}.
     * @return a {@code BigFraction} instance with the resulting values.
     * @throws NullArgumentException if the {@link BigInteger} is {@code null}.
     */
    public BigFraction subtract(final BigInteger bg) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.subtract_1125");
        if (bg == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(bg.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.subtract_1125", _mut652, _mut653, _mut654, _mut655, _mut656)) {
            return this;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.subtract_1125", _mut657, _mut658, _mut659, _mut660, _mut661)) {
            return new BigFraction(bg.negate());
        }
        return new BigFraction(numerator.subtract(denominator.multiply(bg)), denominator);
    }

    /**
     * <p>
     * Subtracts the value of an {@code integer} from the value of this
     * {@code BigFraction}, returning the result in reduced form.
     * </p>
     *
     * @param i the {@code integer} to subtract.
     * @return a {@code BigFraction} instance with the resulting values.
     */
    public BigFraction subtract(final int i) {
        return subtract(BigInteger.valueOf(i));
    }

    /**
     * <p>
     * Subtracts the value of a {@code long} from the value of this
     * {@code BigFraction}, returning the result in reduced form.
     * </p>
     *
     * @param l the {@code long} to subtract.
     * @return a {@code BigFraction} instance with the resulting values.
     */
    public BigFraction subtract(final long l) {
        return subtract(BigInteger.valueOf(l));
    }

    /**
     * <p>
     * Subtracts the value of another fraction from the value of this one,
     * returning the result in reduced form.
     * </p>
     *
     * @param fraction {@link BigFraction} to subtract, must not be {@code null}.
     * @return a {@link BigFraction} instance with the resulting values
     * @throws NullArgumentException if the {@code fraction} is {@code null}.
     */
    public BigFraction subtract(final BigFraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.BigFraction.subtract_1175");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (ROR_equals(fraction.numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.subtract_1175", _mut662, _mut663, _mut664, _mut665, _mut666)) {
            return this;
        }
        if (ROR_equals(numerator.signum(), 0, "org.apache.commons.math3.fraction.BigFraction.subtract_1175", _mut667, _mut668, _mut669, _mut670, _mut671)) {
            return fraction.negate();
        }
        BigInteger num = null;
        BigInteger den = null;
        if (denominator.equals(fraction.denominator)) {
            num = numerator.subtract(fraction.numerator);
            den = denominator;
        } else {
            num = (numerator.multiply(fraction.denominator)).subtract((fraction.numerator).multiply(denominator));
            den = denominator.multiply(fraction.denominator);
        }
        return new BigFraction(num, den);
    }

    /**
     * <p>
     * Returns the <code>String</code> representing this fraction, ie
     * "num / dem" or just "num" if the denominator is one.
     * </p>
     *
     * @return a string representation of the fraction.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str = null;
        if (BigInteger.ONE.equals(denominator)) {
            str = numerator.toString();
        } else if (BigInteger.ZERO.equals(numerator)) {
            str = "0";
        } else {
            str = numerator + " / " + denominator;
        }
        return str;
    }

    /**
     * {@inheritDoc}
     */
    public BigFractionField getField() {
        return BigFractionField.getInstance();
    }
}
