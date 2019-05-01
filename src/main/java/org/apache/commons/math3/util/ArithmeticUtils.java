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
package org.apache.commons.math3.util;

import java.math.BigInteger;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Some useful, arithmetics related, additions to the built-in functions in
 * {@link Math}.
 */
public final class ArithmeticUtils {

    @Conditional
    public static boolean _mut42349 = false, _mut42350 = false, _mut42351 = false, _mut42352 = false, _mut42353 = false, _mut42354 = false, _mut42355 = false, _mut42356 = false, _mut42357 = false, _mut42358 = false, _mut42359 = false, _mut42360 = false, _mut42361 = false, _mut42362 = false, _mut42363 = false, _mut42364 = false, _mut42365 = false, _mut42366 = false, _mut42367 = false, _mut42368 = false, _mut42369 = false, _mut42370 = false, _mut42371 = false, _mut42372 = false, _mut42373 = false, _mut42374 = false, _mut42375 = false, _mut42376 = false, _mut42377 = false, _mut42378 = false, _mut42379 = false, _mut42380 = false, _mut42381 = false, _mut42382 = false, _mut42383 = false, _mut42384 = false, _mut42385 = false, _mut42386 = false, _mut42387 = false, _mut42388 = false, _mut42389 = false, _mut42390 = false, _mut42391 = false, _mut42392 = false, _mut42393 = false, _mut42394 = false, _mut42395 = false, _mut42396 = false, _mut42397 = false, _mut42398 = false, _mut42399 = false, _mut42400 = false, _mut42401 = false, _mut42402 = false, _mut42403 = false, _mut42404 = false, _mut42405 = false, _mut42406 = false, _mut42407 = false, _mut42408 = false, _mut42409 = false, _mut42410 = false, _mut42411 = false, _mut42412 = false, _mut42413 = false, _mut42414 = false, _mut42415 = false, _mut42416 = false, _mut42417 = false, _mut42418 = false, _mut42419 = false, _mut42420 = false, _mut42421 = false, _mut42422 = false, _mut42423 = false, _mut42424 = false, _mut42425 = false, _mut42426 = false, _mut42427 = false, _mut42428 = false, _mut42429 = false, _mut42430 = false, _mut42431 = false, _mut42432 = false, _mut42433 = false, _mut42434 = false, _mut42435 = false, _mut42436 = false, _mut42437 = false, _mut42438 = false, _mut42439 = false, _mut42440 = false, _mut42441 = false, _mut42442 = false, _mut42443 = false, _mut42444 = false, _mut42445 = false, _mut42446 = false, _mut42447 = false, _mut42448 = false, _mut42449 = false, _mut42450 = false, _mut42451 = false, _mut42452 = false, _mut42453 = false, _mut42454 = false, _mut42455 = false, _mut42456 = false, _mut42457 = false, _mut42458 = false, _mut42459 = false, _mut42460 = false, _mut42461 = false, _mut42462 = false, _mut42463 = false, _mut42464 = false, _mut42465 = false, _mut42466 = false, _mut42467 = false, _mut42468 = false, _mut42469 = false, _mut42470 = false, _mut42471 = false, _mut42472 = false, _mut42473 = false, _mut42474 = false, _mut42475 = false, _mut42476 = false, _mut42477 = false, _mut42478 = false, _mut42479 = false, _mut42480 = false, _mut42481 = false, _mut42482 = false, _mut42483 = false, _mut42484 = false, _mut42485 = false, _mut42486 = false, _mut42487 = false, _mut42488 = false, _mut42489 = false, _mut42490 = false, _mut42491 = false, _mut42492 = false, _mut42493 = false, _mut42494 = false, _mut42495 = false, _mut42496 = false, _mut42497 = false, _mut42498 = false, _mut42499 = false, _mut42500 = false, _mut42501 = false, _mut42502 = false, _mut42503 = false, _mut42504 = false, _mut42505 = false, _mut42506 = false, _mut42507 = false, _mut42508 = false, _mut42509 = false, _mut42510 = false, _mut42511 = false, _mut42512 = false, _mut42513 = false, _mut42514 = false, _mut42515 = false, _mut42516 = false, _mut42517 = false, _mut42518 = false, _mut42519 = false, _mut42520 = false, _mut42521 = false, _mut42522 = false, _mut42523 = false, _mut42524 = false, _mut42525 = false, _mut42526 = false, _mut42527 = false, _mut42528 = false, _mut42529 = false, _mut42530 = false, _mut42531 = false, _mut42532 = false, _mut42533 = false, _mut42534 = false, _mut42535 = false, _mut42536 = false, _mut42537 = false, _mut42538 = false, _mut42539 = false, _mut42540 = false, _mut42541 = false, _mut42542 = false, _mut42543 = false, _mut42544 = false, _mut42545 = false, _mut42546 = false, _mut42547 = false, _mut42548 = false, _mut42549 = false, _mut42550 = false, _mut42551 = false, _mut42552 = false, _mut42553 = false, _mut42554 = false, _mut42555 = false, _mut42556 = false, _mut42557 = false, _mut42558 = false, _mut42559 = false, _mut42560 = false, _mut42561 = false, _mut42562 = false, _mut42563 = false, _mut42564 = false, _mut42565 = false, _mut42566 = false, _mut42567 = false, _mut42568 = false, _mut42569 = false, _mut42570 = false, _mut42571 = false, _mut42572 = false, _mut42573 = false, _mut42574 = false, _mut42575 = false, _mut42576 = false, _mut42577 = false, _mut42578 = false, _mut42579 = false, _mut42580 = false, _mut42581 = false, _mut42582 = false, _mut42583 = false, _mut42584 = false, _mut42585 = false, _mut42586 = false, _mut42587 = false, _mut42588 = false, _mut42589 = false, _mut42590 = false, _mut42591 = false, _mut42592 = false, _mut42593 = false, _mut42594 = false, _mut42595 = false, _mut42596 = false, _mut42597 = false, _mut42598 = false, _mut42599 = false, _mut42600 = false, _mut42601 = false, _mut42602 = false, _mut42603 = false, _mut42604 = false, _mut42605 = false, _mut42606 = false, _mut42607 = false, _mut42608 = false, _mut42609 = false, _mut42610 = false, _mut42611 = false, _mut42612 = false, _mut42613 = false, _mut42614 = false, _mut42615 = false, _mut42616 = false, _mut42617 = false, _mut42618 = false, _mut42619 = false, _mut42620 = false, _mut42621 = false, _mut42622 = false, _mut42623 = false, _mut42624 = false, _mut42625 = false, _mut42626 = false, _mut42627 = false, _mut42628 = false, _mut42629 = false, _mut42630 = false, _mut42631 = false, _mut42632 = false, _mut42633 = false, _mut42634 = false, _mut42635 = false, _mut42636 = false, _mut42637 = false, _mut42638 = false, _mut42639 = false, _mut42640 = false, _mut42641 = false, _mut42642 = false, _mut42643 = false, _mut42644 = false, _mut42645 = false, _mut42646 = false, _mut42647 = false, _mut42648 = false, _mut42649 = false, _mut42650 = false, _mut42651 = false, _mut42652 = false, _mut42653 = false, _mut42654 = false, _mut42655 = false, _mut42656 = false, _mut42657 = false, _mut42658 = false, _mut42659 = false, _mut42660 = false, _mut42661 = false, _mut42662 = false, _mut42663 = false, _mut42664 = false, _mut42665 = false, _mut42666 = false, _mut42667 = false, _mut42668 = false, _mut42669 = false, _mut42670 = false, _mut42671 = false, _mut42672 = false, _mut42673 = false, _mut42674 = false, _mut42675 = false, _mut42676 = false, _mut42677 = false, _mut42678 = false, _mut42679 = false, _mut42680 = false, _mut42681 = false, _mut42682 = false, _mut42683 = false, _mut42684 = false, _mut42685 = false, _mut42686 = false, _mut42687 = false, _mut42688 = false, _mut42689 = false, _mut42690 = false, _mut42691 = false, _mut42692 = false, _mut42693 = false, _mut42694 = false, _mut42695 = false, _mut42696 = false, _mut42697 = false, _mut42698 = false, _mut42699 = false, _mut42700 = false, _mut42701 = false, _mut42702 = false, _mut42703 = false, _mut42704 = false, _mut42705 = false, _mut42706 = false, _mut42707 = false, _mut42708 = false, _mut42709 = false, _mut42710 = false, _mut42711 = false, _mut42712 = false, _mut42713 = false, _mut42714 = false, _mut42715 = false, _mut42716 = false, _mut42717 = false, _mut42718 = false, _mut42719 = false, _mut42720 = false, _mut42721 = false, _mut42722 = false, _mut42723 = false, _mut42724 = false, _mut42725 = false, _mut42726 = false, _mut42727 = false, _mut42728 = false, _mut42729 = false, _mut42730 = false, _mut42731 = false, _mut42732 = false, _mut42733 = false, _mut42734 = false, _mut42735 = false, _mut42736 = false, _mut42737 = false, _mut42738 = false, _mut42739 = false, _mut42740 = false, _mut42741 = false, _mut42742 = false, _mut42743 = false, _mut42744 = false, _mut42745 = false, _mut42746 = false, _mut42747 = false, _mut42748 = false, _mut42749 = false, _mut42750 = false, _mut42751 = false, _mut42752 = false, _mut42753 = false, _mut42754 = false, _mut42755 = false, _mut42756 = false, _mut42757 = false, _mut42758 = false, _mut42759 = false, _mut42760 = false, _mut42761 = false, _mut42762 = false, _mut42763 = false, _mut42764 = false, _mut42765 = false, _mut42766 = false, _mut42767 = false, _mut42768 = false, _mut42769 = false, _mut42770 = false, _mut42771 = false, _mut42772 = false, _mut42773 = false, _mut42774 = false, _mut42775 = false, _mut42776 = false, _mut42777 = false, _mut42778 = false, _mut42779 = false, _mut42780 = false, _mut42781 = false, _mut42782 = false, _mut42783 = false, _mut42784 = false, _mut42785 = false, _mut42786 = false, _mut42787 = false, _mut42788 = false, _mut42789 = false, _mut42790 = false, _mut42791 = false, _mut42792 = false, _mut42793 = false, _mut42794 = false, _mut42795 = false, _mut42796 = false, _mut42797 = false, _mut42798 = false, _mut42799 = false, _mut42800 = false, _mut42801 = false, _mut42802 = false, _mut42803 = false, _mut42804 = false, _mut42805 = false, _mut42806 = false, _mut42807 = false;

    /**
     * Private constructor.
     */
    private ArithmeticUtils() {
        super();
    }

    /**
     * Add two integers, checking for overflow.
     *
     * @param x an addend
     * @param y an addend
     * @return the sum {@code x+y}
     * @throws MathArithmeticException if the result can not be represented
     * as an {@code int}.
     * @since 1.1
     */
    public static int addAndCheck(int x, int y) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49");
        long s = AOR_plus((long) x, (long) y, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49", _mut42349, _mut42350, _mut42351, _mut42352);
        if ((_mut42363 ? (ROR_less(s, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49", _mut42353, _mut42354, _mut42355, _mut42356, _mut42357) && ROR_greater(s, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49", _mut42358, _mut42359, _mut42360, _mut42361, _mut42362)) : (ROR_less(s, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49", _mut42353, _mut42354, _mut42355, _mut42356, _mut42357) || ROR_greater(s, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_49", _mut42358, _mut42359, _mut42360, _mut42361, _mut42362)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, x, y);
        }
        return (int) s;
    }

    /**
     * Add two long integers, checking for overflow.
     *
     * @param a an addend
     * @param b an addend
     * @return the sum {@code a+b}
     * @throws MathArithmeticException if the result can not be represented as an long
     * @since 1.2
     */
    public static long addAndCheck(long a, long b) throws MathArithmeticException {
        return addAndCheck(a, b, LocalizedFormats.OVERFLOW_IN_ADDITION);
    }

    /**
     * Returns an exact representation of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code IllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code long}. The
     * largest value of {@code n} for which all coefficients are
     * {@code  < Long.MAX_VALUE} is 66. If the computed value exceeds
     * {@code Long.MAX_VALUE} an {@code ArithMeticException} is
     * thrown.</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     * @deprecated use {@link CombinatoricsUtils#binomialCoefficient(int, int)}
     */
    @Deprecated
    public static long binomialCoefficient(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficient(n, k);
    }

    /**
     * Returns a {@code double} representation of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code IllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code double}. The
     * largest value of {@code n} for which all coefficients are <
     * Double.MAX_VALUE is 1029. If the computed value exceeds Double.MAX_VALUE,
     * Double.POSITIVE_INFINITY is returned</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     * @deprecated use {@link CombinatoricsUtils#binomialCoefficientDouble(int, int)}
     */
    @Deprecated
    public static double binomialCoefficientDouble(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientDouble(n, k);
    }

    /**
     * Returns the natural {@code log} of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code IllegalArgumentException} is thrown)</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     * @deprecated use {@link CombinatoricsUtils#binomialCoefficientLog(int, int)}
     */
    @Deprecated
    public static double binomialCoefficientLog(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientLog(n, k);
    }

    /**
     * Returns n!. Shorthand for {@code n} <a
     * href="http://mathworld.wolfram.com/Factorial.html"> Factorial</a>, the
     * product of the numbers {@code 1,...,n}.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code n >= 0} (otherwise
     * {@code IllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code long}. The
     * largest value of {@code n} for which {@code n!} <
     * Long.MAX_VALUE} is 20. If the computed value exceeds {@code Long.MAX_VALUE}
     * an {@code ArithMeticException } is thrown.</li>
     * </ul>
     * </p>
     *
     * @param n argument
     * @return {@code n!}
     * @throws MathArithmeticException if the result is too large to be represented
     * by a {@code long}.
     * @throws NotPositiveException if {@code n < 0}.
     * @throws MathArithmeticException if {@code n > 20}: The factorial value is too
     * large to fit in a {@code long}.
     * @deprecated use {@link CombinatoricsUtils#factorial(int)}
     */
    @Deprecated
    public static long factorial(final int n) throws NotPositiveException, MathArithmeticException {
        return CombinatoricsUtils.factorial(n);
    }

    /**
     * Compute n!, the<a href="http://mathworld.wolfram.com/Factorial.html">
     * factorial</a> of {@code n} (the product of the numbers 1 to n), as a
     * {@code double}.
     * The result should be small enough to fit into a {@code double}: The
     * largest {@code n} for which {@code n! < Double.MAX_VALUE} is 170.
     * If the computed value exceeds {@code Double.MAX_VALUE},
     * {@code Double.POSITIVE_INFINITY} is returned.
     *
     * @param n Argument.
     * @return {@code n!}
     * @throws NotPositiveException if {@code n < 0}.
     * @deprecated use {@link CombinatoricsUtils#factorialDouble(int)}
     */
    @Deprecated
    public static double factorialDouble(final int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialDouble(n);
    }

    /**
     * Compute the natural logarithm of the factorial of {@code n}.
     *
     * @param n Argument.
     * @return {@code n!}
     * @throws NotPositiveException if {@code n < 0}.
     * @deprecated use {@link CombinatoricsUtils#factorialLog(int)}
     */
    @Deprecated
    public static double factorialLog(final int n) throws NotPositiveException {
        return CombinatoricsUtils.factorialLog(n);
    }

    /**
     * Computes the greatest common divisor of the absolute value of two
     * numbers, using a modified version of the "binary gcd" method.
     * See Knuth 4.5.2 algorithm B.
     * The algorithm is due to Josef Stein (1961).
     * <br/>
     * Special cases:
     * <ul>
     *  <li>The invocations
     *   {@code gcd(Integer.MIN_VALUE, Integer.MIN_VALUE)},
     *   {@code gcd(Integer.MIN_VALUE, 0)} and
     *   {@code gcd(0, Integer.MIN_VALUE)} throw an
     *   {@code ArithmeticException}, because the result would be 2^31, which
     *   is too large for an int value.</li>
     *  <li>The result of {@code gcd(x, x)}, {@code gcd(0, x)} and
     *   {@code gcd(x, 0)} is the absolute value of {@code x}, except
     *   for the special cases above.</li>
     *  <li>The invocation {@code gcd(0, 0)} is the only one which returns
     *   {@code 0}.</li>
     * </ul>
     *
     * @param p Number.
     * @param q Number.
     * @return the greatest common divisor (never negative).
     * @throws MathArithmeticException if the result cannot be represented as
     * a non-negative {@code int} value.
     * @since 1.1
     */
    public static int gcd(int p, int q) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcd_254");
        int a = p;
        int b = q;
        if ((_mut42374 ? (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42364, _mut42365, _mut42366, _mut42367, _mut42368) && ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42369, _mut42370, _mut42371, _mut42372, _mut42373)) : (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42364, _mut42365, _mut42366, _mut42367, _mut42368) || ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42369, _mut42370, _mut42371, _mut42372, _mut42373)))) {
            if ((_mut42385 ? (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42375, _mut42376, _mut42377, _mut42378, _mut42379) && ROR_equals(b, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42380, _mut42381, _mut42382, _mut42383, _mut42384)) : (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42375, _mut42376, _mut42377, _mut42378, _mut42379) || ROR_equals(b, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42380, _mut42381, _mut42382, _mut42383, _mut42384)))) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
            }
            return FastMath.abs(AOR_plus(a, b, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42386, _mut42387, _mut42388, _mut42389));
        }
        long al = a;
        long bl = b;
        boolean useLong = false;
        if (ROR_less(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42390, _mut42391, _mut42392, _mut42393, _mut42394)) {
            if (ROR_equals(Integer.MIN_VALUE, a, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42395, _mut42396, _mut42397, _mut42398, _mut42399)) {
                useLong = true;
            } else {
                a = -a;
            }
            al = -al;
        }
        if (ROR_less(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42400, _mut42401, _mut42402, _mut42403, _mut42404)) {
            if (ROR_equals(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42405, _mut42406, _mut42407, _mut42408, _mut42409)) {
                useLong = true;
            } else {
                b = -b;
            }
            bl = -bl;
        }
        if (useLong) {
            if (ROR_equals(al, bl, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42410, _mut42411, _mut42412, _mut42413, _mut42414)) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
            }
            long blbu = bl;
            bl = al;
            al = AOR_remainder(blbu, al, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42415, _mut42416, _mut42417, _mut42418);
            if (ROR_equals(al, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42419, _mut42420, _mut42421, _mut42422, _mut42423)) {
                if (ROR_greater(bl, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42424, _mut42425, _mut42426, _mut42427, _mut42428)) {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, p, q);
                }
                return (int) bl;
            }
            blbu = bl;
            // Now "al" and "bl" fit in an "int".
            b = (int) al;
            a = (int) (AOR_remainder(blbu, al, "org.apache.commons.math3.util.ArithmeticUtils.gcd_254", _mut42429, _mut42430, _mut42431, _mut42432));
        }
        return gcdPositive(a, b);
    }

    /**
     * Computes the greatest common divisor of two <em>positive</em> numbers
     * (this precondition is <em>not</em> checked and the result is undefined
     * if not fulfilled) using the "binary gcd" method which avoids division
     * and modulo operations.
     * See Knuth 4.5.2 algorithm B.
     * The algorithm is due to Josef Stein (1961).
     * <br/>
     * Special cases:
     * <ul>
     *  <li>The result of {@code gcd(x, x)}, {@code gcd(0, x)} and
     *   {@code gcd(x, 0)} is the value of {@code x}.</li>
     *  <li>The invocation {@code gcd(0, 0)} is the only one which returns
     *   {@code 0}.</li>
     * </ul>
     *
     * @param a Positive number.
     * @param b Positive number.
     * @return the greatest common divisor.
     */
    private static int gcdPositive(int a, int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331");
        if (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331", _mut42433, _mut42434, _mut42435, _mut42436, _mut42437)) {
            return b;
        } else if (ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331", _mut42438, _mut42439, _mut42440, _mut42441, _mut42442)) {
            return a;
        }
        // Make "a" and "b" odd, keeping track of common power of 2.
        final int aTwos = Integer.numberOfTrailingZeros(a);
        a >>= aTwos;
        final int bTwos = Integer.numberOfTrailingZeros(b);
        b >>= bTwos;
        final int shift = FastMath.min(aTwos, bTwos);
        // "b" becomes the minimum of the current values.
        while (ROR_not_equals(a, b, "org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331", _mut42447, _mut42448, _mut42449, _mut42450, _mut42451)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331");
            final int delta = AOR_minus(a, b, "org.apache.commons.math3.util.ArithmeticUtils.gcdPositive_331", _mut42443, _mut42444, _mut42445, _mut42446);
            b = Math.min(a, b);
            a = Math.abs(delta);
            // Remove any power of 2 in "a" ("b" is guaranteed to be odd).
            a >>= Integer.numberOfTrailingZeros(a);
        }
        // Recover the common power of 2.
        return a << shift;
    }

    /**
     * <p>
     * Gets the greatest common divisor of the absolute value of two numbers,
     * using the "binary gcd" method which avoids division and modulo
     * operations. See Knuth 4.5.2 algorithm B. This algorithm is due to Josef
     * Stein (1961).
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations
     * {@code gcd(Long.MIN_VALUE, Long.MIN_VALUE)},
     * {@code gcd(Long.MIN_VALUE, 0L)} and
     * {@code gcd(0L, Long.MIN_VALUE)} throw an
     * {@code ArithmeticException}, because the result would be 2^63, which
     * is too large for a long value.</li>
     * <li>The result of {@code gcd(x, x)}, {@code gcd(0L, x)} and
     * {@code gcd(x, 0L)} is the absolute value of {@code x}, except
     * for the special cases above.
     * <li>The invocation {@code gcd(0L, 0L)} is the only one which returns
     * {@code 0L}.</li>
     * </ul>
     *
     * @param p Number.
     * @param q Number.
     * @return the greatest common divisor, never negative.
     * @throws MathArithmeticException if the result cannot be represented as
     * a non-negative {@code long} value.
     * @since 2.1
     */
    public static long gcd(final long p, final long q) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcd_394");
        long u = p;
        long v = q;
        if ((_mut42462 ? ((ROR_equals(u, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42452, _mut42453, _mut42454, _mut42455, _mut42456)) && (ROR_equals(v, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42457, _mut42458, _mut42459, _mut42460, _mut42461))) : ((ROR_equals(u, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42452, _mut42453, _mut42454, _mut42455, _mut42456)) || (ROR_equals(v, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42457, _mut42458, _mut42459, _mut42460, _mut42461))))) {
            if ((_mut42473 ? ((ROR_equals(u, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42463, _mut42464, _mut42465, _mut42466, _mut42467)) && (ROR_equals(v, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42468, _mut42469, _mut42470, _mut42471, _mut42472))) : ((ROR_equals(u, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42463, _mut42464, _mut42465, _mut42466, _mut42467)) || (ROR_equals(v, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42468, _mut42469, _mut42470, _mut42471, _mut42472))))) {
                throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, p, q);
            }
            return AOR_plus(FastMath.abs(u), FastMath.abs(v), "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42474, _mut42475, _mut42476, _mut42477);
        }
        /* assert u!=0 && v!=0; */
        if (ROR_greater(u, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42478, _mut42479, _mut42480, _mut42481, _mut42482)) {
            u = -u;
        }
        // make u negative
        if (ROR_greater(v, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42483, _mut42484, _mut42485, _mut42486, _mut42487)) {
            v = -v;
        }
        // B1. [Find power of 2]
        int k = 0;
        while ((_mut42504 ? ((_mut42498 ? (ROR_equals((u & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42488, _mut42489, _mut42490, _mut42491, _mut42492) || ROR_equals((v & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42493, _mut42494, _mut42495, _mut42496, _mut42497)) : (ROR_equals((u & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42488, _mut42489, _mut42490, _mut42491, _mut42492) && ROR_equals((v & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42493, _mut42494, _mut42495, _mut42496, _mut42497))) || ROR_less(k, 63, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42499, _mut42500, _mut42501, _mut42502, _mut42503)) : ((_mut42498 ? (ROR_equals((u & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42488, _mut42489, _mut42490, _mut42491, _mut42492) || ROR_equals((v & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42493, _mut42494, _mut42495, _mut42496, _mut42497)) : (ROR_equals((u & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42488, _mut42489, _mut42490, _mut42491, _mut42492) && ROR_equals((v & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42493, _mut42494, _mut42495, _mut42496, _mut42497))) && ROR_less(k, 63, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42499, _mut42500, _mut42501, _mut42502, _mut42503)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcd_394");
            // both even...
            u /= 2;
            v /= 2;
            // cast out twos.
            k++;
        }
        if (ROR_equals(k, 63, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42505, _mut42506, _mut42507, _mut42508, _mut42509)) {
            throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_64_BITS, p, q);
        }
        // one is odd.
        long t = (ROR_equals((u & 1), 1, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42510, _mut42511, _mut42512, _mut42513, _mut42514)) ? v : -(AOR_divide(u, 2, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42515, _mut42516, _mut42517, _mut42518));
        // t positive: u was even, v is odd (t replaces u)
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcd_394");
            // B4/B3: cast out twos from t.
            while (ROR_equals((t & 1), 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42519, _mut42520, _mut42521, _mut42522, _mut42523)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.gcd_394");
                // cast out twos
                t /= 2;
            }
            // B5 [reset max(u,v)]
            if (ROR_greater(t, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42524, _mut42525, _mut42526, _mut42527, _mut42528)) {
                u = -t;
            } else {
                v = t;
            }
            // B6/B3. at this point both u and v should be odd.
            t = AOR_divide((AOR_minus(v, u, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42529, _mut42530, _mut42531, _mut42532)), 2, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42533, _mut42534, _mut42535, _mut42536);
        } while (ROR_not_equals(t, 0, "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42537, _mut42538, _mut42539, _mut42540, _mut42541));
        // gcd is u*2^k
        return AOR_multiply(-u, (1L << k), "org.apache.commons.math3.util.ArithmeticUtils.gcd_394", _mut42542, _mut42543, _mut42544, _mut42545);
    }

    /**
     * <p>
     * Returns the least common multiple of the absolute value of two numbers,
     * using the formula {@code lcm(a,b) = (a / gcd(a,b)) * b}.
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations {@code lcm(Integer.MIN_VALUE, n)} and
     * {@code lcm(n, Integer.MIN_VALUE)}, where {@code abs(n)} is a
     * power of 2, throw an {@code ArithmeticException}, because the result
     * would be 2^31, which is too large for an int value.</li>
     * <li>The result of {@code lcm(0, x)} and {@code lcm(x, 0)} is
     * {@code 0} for any {@code x}.
     * </ul>
     *
     * @param a Number.
     * @param b Number.
     * @return the least common multiple, never negative.
     * @throws MathArithmeticException if the result cannot be represented as
     * a non-negative {@code int} value.
     * @since 1.1
     */
    public static int lcm(int a, int b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.lcm_474");
        if ((_mut42556 ? (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42546, _mut42547, _mut42548, _mut42549, _mut42550) && ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42551, _mut42552, _mut42553, _mut42554, _mut42555)) : (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42546, _mut42547, _mut42548, _mut42549, _mut42550) || ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42551, _mut42552, _mut42553, _mut42554, _mut42555)))) {
            return 0;
        }
        int lcm = FastMath.abs(ArithmeticUtils.mulAndCheck(AOR_divide(a, gcd(a, b), "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42557, _mut42558, _mut42559, _mut42560), b));
        if (ROR_equals(lcm, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.lcm_474", _mut42561, _mut42562, _mut42563, _mut42564, _mut42565)) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, a, b);
        }
        return lcm;
    }

    /**
     * <p>
     * Returns the least common multiple of the absolute value of two numbers,
     * using the formula {@code lcm(a,b) = (a / gcd(a,b)) * b}.
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations {@code lcm(Long.MIN_VALUE, n)} and
     * {@code lcm(n, Long.MIN_VALUE)}, where {@code abs(n)} is a
     * power of 2, throw an {@code ArithmeticException}, because the result
     * would be 2^63, which is too large for an int value.</li>
     * <li>The result of {@code lcm(0L, x)} and {@code lcm(x, 0L)} is
     * {@code 0L} for any {@code x}.
     * </ul>
     *
     * @param a Number.
     * @param b Number.
     * @return the least common multiple, never negative.
     * @throws MathArithmeticException if the result cannot be represented
     * as a non-negative {@code long} value.
     * @since 2.1
     */
    public static long lcm(long a, long b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.lcm_508");
        if ((_mut42576 ? (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42566, _mut42567, _mut42568, _mut42569, _mut42570) && ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42571, _mut42572, _mut42573, _mut42574, _mut42575)) : (ROR_equals(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42566, _mut42567, _mut42568, _mut42569, _mut42570) || ROR_equals(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42571, _mut42572, _mut42573, _mut42574, _mut42575)))) {
            return 0;
        }
        long lcm = FastMath.abs(ArithmeticUtils.mulAndCheck(AOR_divide(a, gcd(a, b), "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42577, _mut42578, _mut42579, _mut42580), b));
        if (ROR_equals(lcm, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.lcm_508", _mut42581, _mut42582, _mut42583, _mut42584, _mut42585)) {
            throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, a, b);
        }
        return lcm;
    }

    /**
     * Multiply two integers, checking for overflow.
     *
     * @param x Factor.
     * @param y Factor.
     * @return the product {@code x * y}.
     * @throws MathArithmeticException if the result can not be
     * represented as an {@code int}.
     * @since 1.1
     */
    public static int mulAndCheck(int x, int y) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530");
        long m = AOR_multiply(((long) x), ((long) y), "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530", _mut42586, _mut42587, _mut42588, _mut42589);
        if ((_mut42600 ? (ROR_less(m, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530", _mut42590, _mut42591, _mut42592, _mut42593, _mut42594) && ROR_greater(m, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530", _mut42595, _mut42596, _mut42597, _mut42598, _mut42599)) : (ROR_less(m, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530", _mut42590, _mut42591, _mut42592, _mut42593, _mut42594) || ROR_greater(m, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_530", _mut42595, _mut42596, _mut42597, _mut42598, _mut42599)))) {
            throw new MathArithmeticException();
        }
        return (int) m;
    }

    /**
     * Multiply two long integers, checking for overflow.
     *
     * @param a Factor.
     * @param b Factor.
     * @return the product {@code a * b}.
     * @throws MathArithmeticException if the result can not be represented
     * as a {@code long}.
     * @since 1.2
     */
    public static long mulAndCheck(long a, long b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548");
        long ret;
        if (ROR_greater(a, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42601, _mut42602, _mut42603, _mut42604, _mut42605)) {
            // use symmetry to reduce boundary cases
            ret = mulAndCheck(b, a);
        } else {
            if (ROR_less(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42606, _mut42607, _mut42608, _mut42609, _mut42610)) {
                if (ROR_less(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42629, _mut42630, _mut42631, _mut42632, _mut42633)) {
                    // check for positive overflow with negative a, negative b
                    if (ROR_greater_equals(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42652, _mut42653, _mut42654, _mut42655), "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42656, _mut42657, _mut42658, _mut42659, _mut42660)) {
                        ret = AOR_multiply(a, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42661, _mut42662, _mut42663, _mut42664);
                    } else {
                        throw new MathArithmeticException();
                    }
                } else if (ROR_greater(b, 0, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42634, _mut42635, _mut42636, _mut42637, _mut42638)) {
                    // check for negative overflow with negative a, positive b
                    if (ROR_less_equals(AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42639, _mut42640, _mut42641, _mut42642), a, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42643, _mut42644, _mut42645, _mut42646, _mut42647)) {
                        ret = AOR_multiply(a, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42648, _mut42649, _mut42650, _mut42651);
                    } else {
                        throw new MathArithmeticException();
                    }
                } else {
                    // assert b == 0
                    ret = 0;
                }
            } else if (ROR_greater(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42611, _mut42612, _mut42613, _mut42614, _mut42615)) {
                // check for positive overflow with positive a, positive b
                if (ROR_less_equals(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42616, _mut42617, _mut42618, _mut42619), "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42620, _mut42621, _mut42622, _mut42623, _mut42624)) {
                    ret = AOR_multiply(a, b, "org.apache.commons.math3.util.ArithmeticUtils.mulAndCheck_548", _mut42625, _mut42626, _mut42627, _mut42628);
                } else {
                    throw new MathArithmeticException();
                }
            } else {
                // assert a == 0
                ret = 0;
            }
        }
        return ret;
    }

    /**
     * Subtract two integers, checking for overflow.
     *
     * @param x Minuend.
     * @param y Subtrahend.
     * @return the difference {@code x - y}.
     * @throws MathArithmeticException if the result can not be represented
     * as an {@code int}.
     * @since 1.1
     */
    public static int subAndCheck(int x, int y) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602");
        long s = AOR_minus((long) x, (long) y, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602", _mut42665, _mut42666, _mut42667, _mut42668);
        if ((_mut42679 ? (ROR_less(s, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602", _mut42669, _mut42670, _mut42671, _mut42672, _mut42673) && ROR_greater(s, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602", _mut42674, _mut42675, _mut42676, _mut42677, _mut42678)) : (ROR_less(s, Integer.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602", _mut42669, _mut42670, _mut42671, _mut42672, _mut42673) || ROR_greater(s, Integer.MAX_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_602", _mut42674, _mut42675, _mut42676, _mut42677, _mut42678)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, x, y);
        }
        return (int) s;
    }

    /**
     * Subtract two long integers, checking for overflow.
     *
     * @param a Value.
     * @param b Value.
     * @return the difference {@code a - b}.
     * @throws MathArithmeticException if the result can not be represented as a
     * {@code long}.
     * @since 1.2
     */
    public static long subAndCheck(long a, long b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_620");
        long ret;
        if (ROR_equals(b, Long.MIN_VALUE, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_620", _mut42680, _mut42681, _mut42682, _mut42683, _mut42684)) {
            if (ROR_less(a, 0, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_620", _mut42685, _mut42686, _mut42687, _mut42688, _mut42689)) {
                ret = AOR_minus(a, b, "org.apache.commons.math3.util.ArithmeticUtils.subAndCheck_620", _mut42690, _mut42691, _mut42692, _mut42693);
            } else {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, a, -b);
            }
        } else {
            // use additive inverse
            ret = addAndCheck(a, -b, LocalizedFormats.OVERFLOW_IN_ADDITION);
        }
        return ret;
    }

    /**
     * Raise an int to an int power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return \( k^e \)
     * @throws NotPositiveException if {@code e < 0}.
     * @throws MathArithmeticException if the result would overflow.
     */
    public static int pow(final int k, final int e) throws NotPositiveException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_644");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_644", _mut42694, _mut42695, _mut42696, _mut42697, _mut42698)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        try {
            int exp = e;
            int result = 1;
            int k2p = k;
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_644");
                if (ROR_not_equals((exp & 0x1), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_644", _mut42699, _mut42700, _mut42701, _mut42702, _mut42703)) {
                    result = mulAndCheck(result, k2p);
                }
                exp >>= 1;
                if (ROR_equals(exp, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_644", _mut42704, _mut42705, _mut42706, _mut42707, _mut42708)) {
                    break;
                }
                k2p = mulAndCheck(k2p, k2p);
            }
            return result;
        } catch (MathArithmeticException mae) {
            // Add context information.
            mae.getContext().addMessage(LocalizedFormats.OVERFLOW);
            mae.getContext().addMessage(LocalizedFormats.BASE, k);
            mae.getContext().addMessage(LocalizedFormats.EXPONENT, e);
            // Rethrow.
            throw mae;
        }
    }

    /**
     * Raise an int to a long power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws NotPositiveException if {@code e < 0}.
     * @deprecated As of 3.3. Please use {@link #pow(int,int)} instead.
     */
    @Deprecated
    public static int pow(final int k, long e) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_690");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_690", _mut42709, _mut42710, _mut42711, _mut42712, _mut42713)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        int result = 1;
        int k2p = k;
        while (ROR_not_equals(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_690", _mut42719, _mut42720, _mut42721, _mut42722, _mut42723)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_690");
            if (ROR_not_equals((e & 0x1), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_690", _mut42714, _mut42715, _mut42716, _mut42717, _mut42718)) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    /**
     * Raise a long to an int power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return \( k^e \)
     * @throws NotPositiveException if {@code e < 0}.
     * @throws MathArithmeticException if the result would overflow.
     */
    public static long pow(final long k, final int e) throws NotPositiveException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_718");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_718", _mut42724, _mut42725, _mut42726, _mut42727, _mut42728)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        try {
            int exp = e;
            long result = 1;
            long k2p = k;
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_718");
                if (ROR_not_equals((exp & 0x1), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_718", _mut42729, _mut42730, _mut42731, _mut42732, _mut42733)) {
                    result = mulAndCheck(result, k2p);
                }
                exp >>= 1;
                if (ROR_equals(exp, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_718", _mut42734, _mut42735, _mut42736, _mut42737, _mut42738)) {
                    break;
                }
                k2p = mulAndCheck(k2p, k2p);
            }
            return result;
        } catch (MathArithmeticException mae) {
            // Add context information.
            mae.getContext().addMessage(LocalizedFormats.OVERFLOW);
            mae.getContext().addMessage(LocalizedFormats.BASE, k);
            mae.getContext().addMessage(LocalizedFormats.EXPONENT, e);
            // Rethrow.
            throw mae;
        }
    }

    /**
     * Raise a long to a long power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws NotPositiveException if {@code e < 0}.
     * @deprecated As of 3.3. Please use {@link #pow(long,int)} instead.
     */
    @Deprecated
    public static long pow(final long k, long e) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_764");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_764", _mut42739, _mut42740, _mut42741, _mut42742, _mut42743)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        long result = 1l;
        long k2p = k;
        while (ROR_not_equals(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_764", _mut42749, _mut42750, _mut42751, _mut42752, _mut42753)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_764");
            if (ROR_not_equals((e & 0x1), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_764", _mut42744, _mut42745, _mut42746, _mut42747, _mut42748)) {
                result *= k2p;
            }
            k2p *= k2p;
            e >>= 1;
        }
        return result;
    }

    /**
     * Raise a BigInteger to an int power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws NotPositiveException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, int e) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_791");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_791", _mut42754, _mut42755, _mut42756, _mut42757, _mut42758)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        return k.pow(e);
    }

    /**
     * Raise a BigInteger to a long power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws NotPositiveException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, long e) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_807");
        if (ROR_less(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_807", _mut42759, _mut42760, _mut42761, _mut42762, _mut42763)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (ROR_not_equals(e, 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_807", _mut42769, _mut42770, _mut42771, _mut42772, _mut42773)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_807");
            if (ROR_not_equals((e & 0x1), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_807", _mut42764, _mut42765, _mut42766, _mut42767, _mut42768)) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e >>= 1;
        }
        return result;
    }

    /**
     * Raise a BigInteger to a BigInteger power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws NotPositiveException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, BigInteger e) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_834");
        if (ROR_less(e.compareTo(BigInteger.ZERO), 0, "org.apache.commons.math3.util.ArithmeticUtils.pow_834", _mut42774, _mut42775, _mut42776, _mut42777, _mut42778)) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }
        BigInteger result = BigInteger.ONE;
        BigInteger k2p = k;
        while (!BigInteger.ZERO.equals(e)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.pow_834");
            if (e.testBit(0)) {
                result = result.multiply(k2p);
            }
            k2p = k2p.multiply(k2p);
            e = e.shiftRight(1);
        }
        return result;
    }

    /**
     * Returns the <a
     * href="http://mathworld.wolfram.com/StirlingNumberoftheSecondKind.html">
     * Stirling number of the second kind</a>, "{@code S(n,k)}", the number of
     * ways of partitioning an {@code n}-element set into {@code k} non-empty
     * subsets.
     * <p>
     * The preconditions are {@code 0 <= k <= n } (otherwise
     * {@code NotPositiveException} is thrown)
     * </p>
     * @param n the size of the set
     * @param k the number of non-empty subsets
     * @return {@code S(n,k)}
     * @throws NotPositiveException if {@code k < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if some overflow happens, typically for n exceeding 25 and
     * k between 20 and n-2 (S(n,n-1) is handled specifically and does not overflow)
     * @since 3.1
     * @deprecated use {@link CombinatoricsUtils#stirlingS2(int, int)}
     */
    @Deprecated
    public static long stirlingS2(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.stirlingS2(n, k);
    }

    /**
     * Add two long integers, checking for overflow.
     *
     * @param a Addend.
     * @param b Addend.
     * @param pattern Pattern to use for any thrown exception.
     * @return the sum {@code a + b}.
     * @throws MathArithmeticException if the result cannot be represented
     * as a {@code long}.
     * @since 1.2
     */
    private static long addAndCheck(long a, long b, Localizable pattern) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_890");
        final long result = AOR_plus(a, b, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_890", _mut42779, _mut42780, _mut42781, _mut42782);
        if (!(ROR_less((a ^ b), 0, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_890", _mut42783, _mut42784, _mut42785, _mut42786, _mut42787) | ROR_greater_equals((a ^ result), 0, "org.apache.commons.math3.util.ArithmeticUtils.addAndCheck_890", _mut42788, _mut42789, _mut42790, _mut42791, _mut42792))) {
            throw new MathArithmeticException(pattern, a, b);
        }
        return result;
    }

    /**
     * Returns true if the argument is a power of two.
     *
     * @param n the number to test
     * @return true if the argument is a power of two
     */
    public static boolean isPowerOfTwo(long n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904");
        return (_mut42807 ? ((ROR_greater(n, 0, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42793, _mut42794, _mut42795, _mut42796, _mut42797)) || (ROR_equals((n & (AOR_minus(n, 1, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42798, _mut42799, _mut42800, _mut42801))), 0, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42802, _mut42803, _mut42804, _mut42805, _mut42806))) : ((ROR_greater(n, 0, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42793, _mut42794, _mut42795, _mut42796, _mut42797)) && (ROR_equals((n & (AOR_minus(n, 1, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42798, _mut42799, _mut42800, _mut42801))), 0, "org.apache.commons.math3.util.ArithmeticUtils.isPowerOfTwo_904", _mut42802, _mut42803, _mut42804, _mut42805, _mut42806))));
    }
}
