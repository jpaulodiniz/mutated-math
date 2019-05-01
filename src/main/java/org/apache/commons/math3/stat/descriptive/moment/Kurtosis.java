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
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes the Kurtosis of the available values.
 * <p>
 * We use the following (unbiased) formula to define kurtosis:</p>
 *  <p>
 *  kurtosis = { [n(n+1) / (n -1)(n - 2)(n-3)] sum[(x_i - mean)^4] / std^4 } - [3(n-1)^2 / (n-2)(n-3)]
 *  </p><p>
 *  where n is the number of values, mean is the {@link Mean} and std is the
 * {@link StandardDeviation}</p>
 * <p>
 *  Note that this statistic is undefined for n < 4.  <code>Double.Nan</code>
 *  is returned when there is not sufficient data to compute the statistic.
 *  Note that Double.NaN may also be returned if the input includes NaN
 *  and / or infinite values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut2586 = false, _mut2587 = false, _mut2588 = false, _mut2589 = false, _mut2590 = false, _mut2591 = false, _mut2592 = false, _mut2593 = false, _mut2594 = false, _mut2595 = false, _mut2596 = false, _mut2597 = false, _mut2598 = false, _mut2599 = false, _mut2600 = false, _mut2601 = false, _mut2602 = false, _mut2603 = false, _mut2604 = false, _mut2605 = false, _mut2606 = false, _mut2607 = false, _mut2608 = false, _mut2609 = false, _mut2610 = false, _mut2611 = false, _mut2612 = false, _mut2613 = false, _mut2614 = false, _mut2615 = false, _mut2616 = false, _mut2617 = false, _mut2618 = false, _mut2619 = false, _mut2620 = false, _mut2621 = false, _mut2622 = false, _mut2623 = false, _mut2624 = false, _mut2625 = false, _mut2626 = false, _mut2627 = false, _mut2628 = false, _mut2629 = false, _mut2630 = false, _mut2631 = false, _mut2632 = false, _mut2633 = false, _mut2634 = false, _mut2635 = false, _mut2636 = false, _mut2637 = false, _mut2638 = false, _mut2639 = false, _mut2640 = false, _mut2641 = false, _mut2642 = false, _mut2643 = false, _mut2644 = false, _mut2645 = false, _mut2646 = false, _mut2647 = false, _mut2648 = false, _mut2649 = false, _mut2650 = false, _mut2651 = false, _mut2652 = false, _mut2653 = false, _mut2654 = false, _mut2655 = false, _mut2656 = false, _mut2657 = false, _mut2658 = false, _mut2659 = false, _mut2660 = false, _mut2661 = false, _mut2662 = false, _mut2663 = false, _mut2664 = false, _mut2665 = false, _mut2666 = false, _mut2667 = false, _mut2668 = false, _mut2669 = false, _mut2670 = false, _mut2671 = false, _mut2672 = false, _mut2673 = false, _mut2674 = false, _mut2675 = false, _mut2676 = false, _mut2677 = false, _mut2678 = false, _mut2679 = false, _mut2680 = false, _mut2681 = false, _mut2682 = false, _mut2683 = false, _mut2684 = false, _mut2685 = false, _mut2686 = false, _mut2687 = false, _mut2688 = false, _mut2689 = false, _mut2690 = false, _mut2691 = false, _mut2692 = false, _mut2693 = false, _mut2694 = false, _mut2695 = false, _mut2696 = false, _mut2697 = false, _mut2698 = false, _mut2699 = false, _mut2700 = false, _mut2701 = false, _mut2702 = false, _mut2703 = false, _mut2704 = false, _mut2705 = false, _mut2706 = false, _mut2707 = false, _mut2708 = false, _mut2709 = false, _mut2710 = false, _mut2711 = false, _mut2712 = false, _mut2713 = false, _mut2714 = false, _mut2715 = false, _mut2716 = false, _mut2717 = false, _mut2718 = false, _mut2719 = false, _mut2720 = false, _mut2721 = false, _mut2722 = false, _mut2723 = false, _mut2724 = false, _mut2725 = false, _mut2726 = false, _mut2727 = false, _mut2728 = false, _mut2729 = false, _mut2730 = false, _mut2731 = false, _mut2732 = false, _mut2733 = false, _mut2734 = false, _mut2735 = false, _mut2736 = false, _mut2737 = false, _mut2738 = false, _mut2739 = false, _mut2740 = false, _mut2741 = false, _mut2742 = false, _mut2743 = false, _mut2744 = false, _mut2745 = false, _mut2746 = false, _mut2747 = false, _mut2748 = false, _mut2749 = false, _mut2750 = false, _mut2751 = false, _mut2752 = false, _mut2753 = false, _mut2754 = false, _mut2755 = false, _mut2756 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 2784465764798260919L;

    /**
     * Fourth Moment on which this statistic is based
     */
    protected FourthMoment moment;

    /**
     * Determines whether or not this statistic can be incremented or cleared.
     * <p>
     * Statistics based on (constructed from) external moments cannot
     * be incremented or cleared.</p>
     */
    protected boolean incMoment;

    /**
     * Construct a Kurtosis
     */
    public Kurtosis() {
        incMoment = true;
        moment = new FourthMoment();
    }

    /**
     * Construct a Kurtosis from an external moment
     *
     * @param m4 external Moment
     */
    public Kurtosis(final FourthMoment m4) {
        incMoment = false;
        this.moment = m4;
    }

    /**
     * Copy constructor, creates a new {@code Kurtosis} identical
     * to the {@code original}
     *
     * @param original the {@code Kurtosis} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Kurtosis(Kurtosis original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>Note that when {@link #Kurtosis(FourthMoment)} is used to
     * create a Variance, this method does nothing. In that case, the
     * FourthMoment should be incremented directly.</p>
     */
    @Override
    public void increment(final double d) {
        if (incMoment) {
            moment.increment(d);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110");
        double kurtosis = Double.NaN;
        if (ROR_greater(moment.getN(), 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2586, _mut2587, _mut2588, _mut2589, _mut2590)) {
            double variance = AOR_divide(moment.m2, (AOR_minus(moment.n, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2591, _mut2592, _mut2593, _mut2594)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2595, _mut2596, _mut2597, _mut2598);
            if ((_mut2609 ? (ROR_less_equals(moment.n, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2599, _mut2600, _mut2601, _mut2602, _mut2603) && ROR_less(variance, 10E-20, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2604, _mut2605, _mut2606, _mut2607, _mut2608)) : (ROR_less_equals(moment.n, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2599, _mut2600, _mut2601, _mut2602, _mut2603) || ROR_less(variance, 10E-20, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2604, _mut2605, _mut2606, _mut2607, _mut2608)))) {
                kurtosis = 0.0;
            } else {
                double n = moment.n;
                kurtosis = AOR_divide((AOR_minus(AOR_multiply(AOR_multiply(n, (AOR_plus(n, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2610, _mut2611, _mut2612, _mut2613)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2614, _mut2615, _mut2616, _mut2617), moment.getResult(), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2618, _mut2619, _mut2620, _mut2621), AOR_multiply(AOR_multiply(AOR_multiply(3, moment.m2, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2622, _mut2623, _mut2624, _mut2625), moment.m2, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2626, _mut2627, _mut2628, _mut2629), (AOR_minus(n, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2630, _mut2631, _mut2632, _mut2633)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2634, _mut2635, _mut2636, _mut2637), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2638, _mut2639, _mut2640, _mut2641)), (AOR_multiply(AOR_multiply(AOR_multiply(AOR_multiply((AOR_minus(n, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2642, _mut2643, _mut2644, _mut2645)), (AOR_minus(n, 2, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2646, _mut2647, _mut2648, _mut2649)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2650, _mut2651, _mut2652, _mut2653), (AOR_minus(n, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2654, _mut2655, _mut2656, _mut2657)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2658, _mut2659, _mut2660, _mut2661), variance, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2662, _mut2663, _mut2664, _mut2665), variance, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2666, _mut2667, _mut2668, _mut2669)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.getResult_110", _mut2670, _mut2671, _mut2672, _mut2673);
            }
        }
        return kurtosis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        if (incMoment) {
            moment.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        return moment.getN();
    }

    /**
     * Returns the kurtosis of the entries in the specified portion of the
     * input array.
     * <p>
     * See {@link Kurtosis} for details on the computing algorithm.</p>
     * <p>
     * Throws <code>IllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the kurtosis of the values or Double.NaN if length is less than 4
     * @throws MathIllegalArgumentException if the input array is null or the array
     * index parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162");
        // Initialize the kurtosis
        double kurt = Double.NaN;
        if ((_mut2679 ? (test(values, begin, length) || ROR_greater(length, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2674, _mut2675, _mut2676, _mut2677, _mut2678)) : (test(values, begin, length) && ROR_greater(length, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2674, _mut2675, _mut2676, _mut2677, _mut2678)))) {
            // Compute the mean and standard deviation
            Variance variance = new Variance();
            variance.incrementAll(values, begin, length);
            double mean = variance.moment.m1;
            double stdDev = FastMath.sqrt(variance.getResult());
            // standard deviation
            double accum3 = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2684, _mut2685, _mut2686, _mut2687), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2688, _mut2689, _mut2690, _mut2691, _mut2692); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162");
                accum3 += FastMath.pow(AOR_minus(values[i], mean, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2680, _mut2681, _mut2682, _mut2683), 4.0);
            }
            accum3 /= FastMath.pow(stdDev, 4.0d);
            // Get N
            double n0 = length;
            double coefficientOne = AOR_divide((AOR_multiply(n0, (AOR_plus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2693, _mut2694, _mut2695, _mut2696)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2697, _mut2698, _mut2699, _mut2700)), (AOR_multiply(AOR_multiply((AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2701, _mut2702, _mut2703, _mut2704)), (AOR_minus(n0, 2, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2705, _mut2706, _mut2707, _mut2708)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2709, _mut2710, _mut2711, _mut2712), (AOR_minus(n0, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2713, _mut2714, _mut2715, _mut2716)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2717, _mut2718, _mut2719, _mut2720)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2721, _mut2722, _mut2723, _mut2724);
            double termTwo = AOR_divide((AOR_multiply(3, FastMath.pow(AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2725, _mut2726, _mut2727, _mut2728), 2.0), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2729, _mut2730, _mut2731, _mut2732)), (AOR_multiply((AOR_minus(n0, 2, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2733, _mut2734, _mut2735, _mut2736)), (AOR_minus(n0, 3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2737, _mut2738, _mut2739, _mut2740)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2741, _mut2742, _mut2743, _mut2744)), "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2745, _mut2746, _mut2747, _mut2748);
            // Calculate kurtosis
            kurt = AOR_minus((AOR_multiply(coefficientOne, accum3, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2749, _mut2750, _mut2751, _mut2752)), termTwo, "org.apache.commons.math3.stat.descriptive.moment.Kurtosis.evaluate_162", _mut2753, _mut2754, _mut2755, _mut2756);
        }
        return kurt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kurtosis copy() {
        Kurtosis result = new Kurtosis();
        // No try-catch because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Kurtosis to copy
     * @param dest Kurtosis to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Kurtosis source, Kurtosis dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.moment = source.moment.copy();
        dest.incMoment = source.incMoment;
    }
}
