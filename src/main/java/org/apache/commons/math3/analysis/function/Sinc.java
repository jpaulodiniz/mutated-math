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
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Sinc_function">Sinc</a> function,
 * defined by
 * <pre><code>
 *   sinc(x) = 1            if x = 0,
 *             sin(x) / x   otherwise.
 * </code></pre>
 *
 * @since 3.0
 */
public class Sinc implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut91525 = false, _mut91526 = false, _mut91527 = false, _mut91528 = false, _mut91529 = false, _mut91530 = false, _mut91531 = false, _mut91532 = false, _mut91533 = false, _mut91534 = false, _mut91535 = false, _mut91536 = false, _mut91537 = false, _mut91538 = false, _mut91539 = false, _mut91540 = false, _mut91541 = false, _mut91542 = false, _mut91543 = false, _mut91544 = false, _mut91545 = false, _mut91546 = false, _mut91547 = false, _mut91548 = false, _mut91549 = false, _mut91550 = false, _mut91551 = false, _mut91552 = false, _mut91553 = false, _mut91554 = false, _mut91555 = false, _mut91556 = false, _mut91557 = false, _mut91558 = false, _mut91559 = false, _mut91560 = false, _mut91561 = false, _mut91562 = false, _mut91563 = false, _mut91564 = false, _mut91565 = false, _mut91566 = false, _mut91567 = false, _mut91568 = false, _mut91569 = false, _mut91570 = false, _mut91571 = false, _mut91572 = false, _mut91573 = false, _mut91574 = false, _mut91575 = false, _mut91576 = false, _mut91577 = false, _mut91578 = false, _mut91579 = false, _mut91580 = false, _mut91581 = false, _mut91582 = false, _mut91583 = false, _mut91584 = false, _mut91585 = false, _mut91586 = false, _mut91587 = false, _mut91588 = false, _mut91589 = false, _mut91590 = false, _mut91591 = false, _mut91592 = false, _mut91593 = false, _mut91594 = false, _mut91595 = false, _mut91596 = false, _mut91597 = false, _mut91598 = false, _mut91599 = false, _mut91600 = false, _mut91601 = false, _mut91602 = false, _mut91603 = false, _mut91604 = false, _mut91605 = false, _mut91606 = false, _mut91607 = false, _mut91608 = false, _mut91609 = false, _mut91610 = false, _mut91611 = false, _mut91612 = false, _mut91613 = false, _mut91614 = false, _mut91615 = false, _mut91616 = false, _mut91617 = false, _mut91618 = false, _mut91619 = false, _mut91620 = false, _mut91621 = false, _mut91622 = false, _mut91623 = false, _mut91624 = false, _mut91625 = false, _mut91626 = false, _mut91627 = false, _mut91628 = false, _mut91629 = false, _mut91630 = false, _mut91631 = false, _mut91632 = false, _mut91633 = false, _mut91634 = false, _mut91635 = false, _mut91636 = false, _mut91637 = false, _mut91638 = false, _mut91639 = false, _mut91640 = false, _mut91641 = false, _mut91642 = false, _mut91643 = false, _mut91644 = false, _mut91645 = false, _mut91646 = false, _mut91647 = false, _mut91648 = false, _mut91649 = false, _mut91650 = false, _mut91651 = false, _mut91652 = false, _mut91653 = false, _mut91654 = false, _mut91655 = false, _mut91656 = false, _mut91657 = false, _mut91658 = false, _mut91659 = false, _mut91660 = false, _mut91661 = false, _mut91662 = false, _mut91663 = false, _mut91664 = false, _mut91665 = false, _mut91666 = false, _mut91667 = false, _mut91668 = false, _mut91669 = false, _mut91670 = false, _mut91671 = false, _mut91672 = false, _mut91673 = false, _mut91674 = false, _mut91675 = false, _mut91676 = false, _mut91677 = false, _mut91678 = false, _mut91679 = false, _mut91680 = false, _mut91681 = false, _mut91682 = false, _mut91683 = false, _mut91684 = false, _mut91685 = false, _mut91686 = false, _mut91687 = false, _mut91688 = false, _mut91689 = false, _mut91690 = false, _mut91691 = false, _mut91692 = false, _mut91693 = false, _mut91694 = false, _mut91695 = false, _mut91696 = false, _mut91697 = false, _mut91698 = false, _mut91699 = false, _mut91700 = false, _mut91701 = false, _mut91702 = false, _mut91703 = false, _mut91704 = false, _mut91705 = false, _mut91706 = false, _mut91707 = false, _mut91708 = false, _mut91709 = false, _mut91710 = false, _mut91711 = false, _mut91712 = false, _mut91713 = false, _mut91714 = false, _mut91715 = false, _mut91716 = false, _mut91717 = false, _mut91718 = false, _mut91719 = false, _mut91720 = false, _mut91721 = false, _mut91722 = false, _mut91723 = false, _mut91724 = false, _mut91725 = false, _mut91726 = false, _mut91727 = false, _mut91728 = false, _mut91729 = false, _mut91730 = false, _mut91731 = false, _mut91732 = false, _mut91733 = false, _mut91734 = false, _mut91735 = false, _mut91736 = false, _mut91737 = false, _mut91738 = false, _mut91739 = false, _mut91740 = false, _mut91741 = false, _mut91742 = false, _mut91743 = false, _mut91744 = false, _mut91745 = false, _mut91746 = false, _mut91747 = false, _mut91748 = false, _mut91749 = false, _mut91750 = false, _mut91751 = false, _mut91752 = false, _mut91753 = false, _mut91754 = false, _mut91755 = false, _mut91756 = false, _mut91757 = false, _mut91758 = false, _mut91759 = false, _mut91760 = false, _mut91761 = false, _mut91762 = false, _mut91763 = false, _mut91764 = false, _mut91765 = false, _mut91766 = false, _mut91767 = false, _mut91768 = false, _mut91769 = false, _mut91770 = false, _mut91771 = false, _mut91772 = false, _mut91773 = false, _mut91774 = false, _mut91775 = false, _mut91776 = false, _mut91777 = false, _mut91778 = false, _mut91779 = false, _mut91780 = false, _mut91781 = false, _mut91782 = false, _mut91783 = false, _mut91784 = false, _mut91785 = false, _mut91786 = false, _mut91787 = false, _mut91788 = false, _mut91789 = false, _mut91790 = false, _mut91791 = false, _mut91792 = false, _mut91793 = false, _mut91794 = false, _mut91795 = false, _mut91796 = false, _mut91797 = false, _mut91798 = false, _mut91799 = false, _mut91800 = false, _mut91801 = false, _mut91802 = false, _mut91803 = false, _mut91804 = false, _mut91805 = false, _mut91806 = false, _mut91807 = false, _mut91808 = false, _mut91809 = false, _mut91810 = false, _mut91811 = false, _mut91812 = false, _mut91813 = false, _mut91814 = false, _mut91815 = false, _mut91816 = false, _mut91817 = false, _mut91818 = false, _mut91819 = false, _mut91820 = false, _mut91821 = false, _mut91822 = false;

    /**
     * Value below which the computations are done using Taylor series.
     * <p>
     * The Taylor series for sinc even order derivatives are:
     * <pre>
     * d^(2n)sinc/dx^(2n)     = Sum_(k>=0) (-1)^(n+k) / ((2k)!(2n+2k+1)) x^(2k)
     *                        = (-1)^n     [ 1/(2n+1) - x^2/(4n+6) + x^4/(48n+120) - x^6/(1440n+5040) + O(x^8) ]
     * </pre>
     * </p>
     * <p>
     * The Taylor series for sinc odd order derivatives are:
     * <pre>
     * d^(2n+1)sinc/dx^(2n+1) = Sum_(k>=0) (-1)^(n+k+1) / ((2k+1)!(2n+2k+3)) x^(2k+1)
     *                        = (-1)^(n+1) [ x/(2n+3) - x^3/(12n+30) + x^5/(240n+840) - x^7/(10080n+45360) + O(x^9) ]
     * </pre>
     * </p>
     * <p>
     * So the ratio of the fourth term with respect to the first term
     * is always smaller than x^6/720, for all derivative orders.
     * This implies that neglecting this term and using only the first three terms induces
     * a relative error bounded by x^6/720. The SHORTCUT value is chosen such that this
     * relative error is below double precision accuracy when |x| <= SHORTCUT.
     * </p>
     */
    private static final double SHORTCUT = 6.0e-3;

    /**
     * For normalized sinc function.
     */
    private final boolean normalized;

    /**
     * The sinc function, {@code sin(x) / x}.
     */
    public Sinc() {
        this(false);
    }

    /**
     * Instantiates the sinc function.
     *
     * @param normalized If {@code true}, the function is
     * <code> sin(&pi;x) / &pi;x</code>, otherwise {@code sin(x) / x}.
     */
    public Sinc(boolean normalized) {
        this.normalized = normalized;
    }

    /**
     * {@inheritDoc}
     */
    public double value(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_85");
        final double scaledX = normalized ? AOR_multiply(FastMath.PI, x, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91525, _mut91526, _mut91527, _mut91528) : x;
        if (ROR_less_equals(FastMath.abs(scaledX), SHORTCUT, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91529, _mut91530, _mut91531, _mut91532, _mut91533)) {
            // use Taylor series
            final double scaledX2 = AOR_multiply(scaledX, scaledX, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91538, _mut91539, _mut91540, _mut91541);
            return AOR_divide((AOR_plus(AOR_multiply((AOR_minus(scaledX2, 20, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91542, _mut91543, _mut91544, _mut91545)), scaledX2, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91546, _mut91547, _mut91548, _mut91549), 120, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91550, _mut91551, _mut91552, _mut91553)), 120, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91554, _mut91555, _mut91556, _mut91557);
        } else {
            // use definition expression
            return AOR_divide(FastMath.sin(scaledX), scaledX, "org.apache.commons.math3.analysis.function.Sinc.value_85", _mut91534, _mut91535, _mut91536, _mut91537);
        }
    }

    /**
     * {@inheritDoc}
     * @deprecated as of 3.1, replaced by {@link #value(DerivativeStructure)}
     */
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_108");
        final double scaledX = AOR_multiply((normalized ? FastMath.PI : 1), t.getValue(), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91558, _mut91559, _mut91560, _mut91561);
        final double scaledX2 = AOR_multiply(scaledX, scaledX, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91562, _mut91563, _mut91564, _mut91565);
        double[] f = new double[AOR_plus(t.getOrder(), 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91566, _mut91567, _mut91568, _mut91569)];
        if (ROR_less_equals(FastMath.abs(scaledX), SHORTCUT, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91570, _mut91571, _mut91572, _mut91573, _mut91574)) {
            for (int i = 0; ROR_less(i, f.length, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91813, _mut91814, _mut91815, _mut91816, _mut91817); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_108");
                final int k = AOR_divide(i, 2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91698, _mut91699, _mut91700, _mut91701);
                if (ROR_equals((i & 0x1), 0, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91702, _mut91703, _mut91704, _mut91705, _mut91706)) {
                    // even derivation order
                    f[i] = AOR_multiply(((ROR_equals((k & 0x1), 0, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91760, _mut91761, _mut91762, _mut91763, _mut91764)) ? 1 : -1), (AOR_minus(AOR_divide(1.0, (AOR_plus(i, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91765, _mut91766, _mut91767, _mut91768)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91769, _mut91770, _mut91771, _mut91772), AOR_multiply(scaledX2, (AOR_minus(AOR_divide(1.0, (AOR_plus(AOR_multiply(2, i, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91773, _mut91774, _mut91775, _mut91776), 6, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91777, _mut91778, _mut91779, _mut91780)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91781, _mut91782, _mut91783, _mut91784), AOR_divide(scaledX2, (AOR_plus(AOR_multiply(24, i, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91785, _mut91786, _mut91787, _mut91788), 120, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91789, _mut91790, _mut91791, _mut91792)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91793, _mut91794, _mut91795, _mut91796), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91797, _mut91798, _mut91799, _mut91800)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91801, _mut91802, _mut91803, _mut91804), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91805, _mut91806, _mut91807, _mut91808)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91809, _mut91810, _mut91811, _mut91812);
                } else {
                    // odd derivation order
                    f[i] = AOR_multiply(((ROR_equals((k & 0x1), 0, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91707, _mut91708, _mut91709, _mut91710, _mut91711)) ? -scaledX : scaledX), (AOR_minus(AOR_divide(1.0, (AOR_plus(i, 2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91712, _mut91713, _mut91714, _mut91715)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91716, _mut91717, _mut91718, _mut91719), AOR_multiply(scaledX2, (AOR_minus(AOR_divide(1.0, (AOR_plus(AOR_multiply(6, i, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91720, _mut91721, _mut91722, _mut91723), 24, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91724, _mut91725, _mut91726, _mut91727)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91728, _mut91729, _mut91730, _mut91731), AOR_divide(scaledX2, (AOR_plus(AOR_multiply(120, i, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91732, _mut91733, _mut91734, _mut91735), 720, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91736, _mut91737, _mut91738, _mut91739)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91740, _mut91741, _mut91742, _mut91743), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91744, _mut91745, _mut91746, _mut91747)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91748, _mut91749, _mut91750, _mut91751), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91752, _mut91753, _mut91754, _mut91755)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91756, _mut91757, _mut91758, _mut91759);
                }
            }
        } else {
            final double inv = AOR_divide(1, scaledX, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91575, _mut91576, _mut91577, _mut91578);
            final double cos = FastMath.cos(scaledX);
            final double sin = FastMath.sin(scaledX);
            f[0] = AOR_multiply(inv, sin, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91579, _mut91580, _mut91581, _mut91582);
            // as per polynomials parity, we can store both S_n and C_n in the same array
            final double[] sc = new double[f.length];
            sc[0] = 1;
            double coeff = inv;
            for (int n = 1; ROR_less(n, f.length, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91693, _mut91694, _mut91695, _mut91696, _mut91697); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_108");
                double s = 0;
                double c = 0;
                // update and evaluate polynomials S_n(x) and C_n(x)
                final int kStart;
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91583, _mut91584, _mut91585, _mut91586, _mut91587)) {
                    // even derivation order, S_n is degree n and C_n is degree n-1
                    sc[n] = 0;
                    kStart = n;
                } else {
                    // odd derivation order, S_n is degree n-1 and C_n is degree n
                    sc[n] = sc[AOR_minus(n, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91588, _mut91589, _mut91590, _mut91591)];
                    c = sc[n];
                    kStart = AOR_minus(n, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91592, _mut91593, _mut91594, _mut91595);
                }
                // in this loop, k is always even
                for (int k = kStart; ROR_greater(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91660, _mut91661, _mut91662, _mut91663, _mut91664); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_108");
                    // sine part
                    sc[k] = AOR_minus(AOR_multiply((AOR_minus(k, n, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91596, _mut91597, _mut91598, _mut91599)), sc[k], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91600, _mut91601, _mut91602, _mut91603), sc[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91604, _mut91605, _mut91606, _mut91607)], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91608, _mut91609, _mut91610, _mut91611);
                    s = AOR_plus(AOR_multiply(s, scaledX2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91612, _mut91613, _mut91614, _mut91615), sc[k], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91616, _mut91617, _mut91618, _mut91619);
                    // cosine part
                    sc[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91620, _mut91621, _mut91622, _mut91623)] = AOR_plus(AOR_multiply((AOR_minus(AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91624, _mut91625, _mut91626, _mut91627), n, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91628, _mut91629, _mut91630, _mut91631)), sc[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91632, _mut91633, _mut91634, _mut91635)], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91636, _mut91637, _mut91638, _mut91639), sc[AOR_minus(k, 2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91640, _mut91641, _mut91642, _mut91643)], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91644, _mut91645, _mut91646, _mut91647);
                    c = AOR_plus(AOR_multiply(c, scaledX2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91648, _mut91649, _mut91650, _mut91651), sc[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91652, _mut91653, _mut91654, _mut91655)], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91656, _mut91657, _mut91658, _mut91659);
                }
                sc[0] *= -n;
                s = AOR_plus(AOR_multiply(s, scaledX2, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91665, _mut91666, _mut91667, _mut91668), sc[0], "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91669, _mut91670, _mut91671, _mut91672);
                coeff *= inv;
                f[n] = AOR_multiply(coeff, (AOR_plus(AOR_multiply(s, sin, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91673, _mut91674, _mut91675, _mut91676), AOR_multiply(AOR_multiply(c, scaledX, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91677, _mut91678, _mut91679, _mut91680), cos, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91681, _mut91682, _mut91683, _mut91684), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91685, _mut91686, _mut91687, _mut91688)), "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91689, _mut91690, _mut91691, _mut91692);
            }
        }
        if (normalized) {
            double scale = FastMath.PI;
            for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.function.Sinc.value_108", _mut91818, _mut91819, _mut91820, _mut91821, _mut91822); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sinc.value_108");
                f[i] *= scale;
                scale *= FastMath.PI;
            }
        }
        return t.compose(f);
    }
}
