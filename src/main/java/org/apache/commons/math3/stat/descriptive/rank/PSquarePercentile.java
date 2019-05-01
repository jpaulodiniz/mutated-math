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
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A {@link StorelessUnivariateStatistic} estimating percentiles using the
 * <ahref=http://www.cs.wustl.edu/~jain/papers/ftp/psqr.pdf>P<SUP>2</SUP></a>
 * Algorithm as explained by <a href=http://www.cse.wustl.edu/~jain/>Raj
 * Jain</a> and Imrich Chlamtac in
 * <a href=http://www.cse.wustl.edu/~jain/papers/psqr.htm>P<SUP>2</SUP> Algorithm
 * for Dynamic Calculation of Quantiles and Histogram Without Storing
 * Observations</a>.
 * <p>
 * Note: This implementation is not synchronized and produces an approximate
 * result. For small samples, where data can be stored and processed in memory,
 * {@link Percentile} should be used.</p>
 */
public class PSquarePercentile extends AbstractStorelessUnivariateStatistic implements StorelessUnivariateStatistic, Serializable {

    @Conditional
    public static boolean _mut3491 = false, _mut3492 = false, _mut3493 = false, _mut3494 = false, _mut3495 = false, _mut3496 = false, _mut3497 = false, _mut3498 = false, _mut3499 = false, _mut3500 = false, _mut3501 = false, _mut3502 = false, _mut3503 = false, _mut3504 = false, _mut3505 = false, _mut3506 = false, _mut3507 = false, _mut3508 = false, _mut3509 = false, _mut3510 = false, _mut3511 = false, _mut3512 = false, _mut3513 = false, _mut3514 = false, _mut3515 = false, _mut3516 = false, _mut3517 = false, _mut3518 = false, _mut3519 = false, _mut3520 = false, _mut3521 = false, _mut3522 = false, _mut3523 = false, _mut3524 = false, _mut3525 = false, _mut3526 = false, _mut3527 = false, _mut3528 = false, _mut3529 = false, _mut3530 = false, _mut3531 = false, _mut3532 = false, _mut3533 = false, _mut3534 = false, _mut3535 = false, _mut3536 = false, _mut3537 = false, _mut3538 = false, _mut3539 = false, _mut3540 = false, _mut3541 = false, _mut3542 = false, _mut3543 = false, _mut3544 = false, _mut3545 = false, _mut3546 = false, _mut3547 = false, _mut3548 = false, _mut3549 = false, _mut3550 = false, _mut3551 = false, _mut3552 = false, _mut3553 = false, _mut3554 = false, _mut3555 = false, _mut3556 = false, _mut3557 = false, _mut3558 = false, _mut3559 = false, _mut3560 = false, _mut3561 = false, _mut3562 = false, _mut3563 = false, _mut3564 = false, _mut3565 = false, _mut3566 = false, _mut3567 = false, _mut3568 = false, _mut3569 = false, _mut3570 = false, _mut3571 = false, _mut3572 = false, _mut3573 = false, _mut3574 = false, _mut3575 = false, _mut3576 = false, _mut3577 = false, _mut3578 = false, _mut3579 = false, _mut3580 = false, _mut3581 = false, _mut3582 = false, _mut3583 = false, _mut3584 = false, _mut3585 = false, _mut3586 = false, _mut3587 = false, _mut3588 = false, _mut3589 = false, _mut3590 = false, _mut3591 = false, _mut3592 = false, _mut3593 = false, _mut3594 = false, _mut3595 = false, _mut3596 = false, _mut3597 = false, _mut3598 = false, _mut3599 = false, _mut3600 = false, _mut3601 = false, _mut3602 = false, _mut3603 = false, _mut3604 = false, _mut3605 = false, _mut3606 = false, _mut3607 = false, _mut3608 = false, _mut3609 = false, _mut3610 = false, _mut3611 = false, _mut3612 = false, _mut3613 = false, _mut3614 = false, _mut3615 = false, _mut3616 = false, _mut3617 = false, _mut3618 = false, _mut3619 = false, _mut3620 = false, _mut3621 = false, _mut3622 = false, _mut3623 = false, _mut3624 = false, _mut3625 = false, _mut3626 = false, _mut3627 = false, _mut3628 = false, _mut3629 = false, _mut3630 = false, _mut3631 = false, _mut3632 = false, _mut3633 = false, _mut3634 = false, _mut3635 = false, _mut3636 = false, _mut3637 = false, _mut3638 = false, _mut3639 = false, _mut3640 = false, _mut3641 = false, _mut3642 = false, _mut3643 = false, _mut3644 = false, _mut3645 = false, _mut3646 = false, _mut3647 = false, _mut3648 = false, _mut3649 = false, _mut3650 = false, _mut3651 = false, _mut3652 = false, _mut3653 = false, _mut3654 = false, _mut3655 = false, _mut3656 = false, _mut3657 = false, _mut3658 = false, _mut3659 = false, _mut3660 = false, _mut3661 = false, _mut3662 = false, _mut3663 = false, _mut3664 = false, _mut3665 = false, _mut3666 = false, _mut3667 = false, _mut3668 = false, _mut3669 = false, _mut3670 = false, _mut3671 = false, _mut3672 = false, _mut3673 = false, _mut3674 = false, _mut3675 = false, _mut3676 = false, _mut3677 = false, _mut3678 = false, _mut3679 = false, _mut3680 = false, _mut3681 = false, _mut3682 = false, _mut3683 = false, _mut3684 = false, _mut3685 = false, _mut3686 = false, _mut3687 = false, _mut3688 = false, _mut3689 = false, _mut3690 = false, _mut3691 = false, _mut3692 = false, _mut3693 = false, _mut3694 = false, _mut3695 = false, _mut3696 = false, _mut3697 = false, _mut3698 = false, _mut3699 = false, _mut3700 = false, _mut3701 = false, _mut3702 = false, _mut3703 = false, _mut3704 = false, _mut3705 = false, _mut3706 = false, _mut3707 = false, _mut3708 = false, _mut3709 = false, _mut3710 = false, _mut3711 = false, _mut3712 = false, _mut3713 = false, _mut3714 = false, _mut3715 = false, _mut3716 = false, _mut3717 = false, _mut3718 = false, _mut3719 = false, _mut3720 = false, _mut3721 = false, _mut3722 = false, _mut3723 = false, _mut3724 = false, _mut3725 = false, _mut3726 = false, _mut3727 = false, _mut3728 = false, _mut3729 = false, _mut3730 = false, _mut3731 = false, _mut3732 = false, _mut3733 = false, _mut3734 = false, _mut3735 = false, _mut3736 = false, _mut3737 = false, _mut3738 = false, _mut3739 = false, _mut3740 = false, _mut3741 = false, _mut3742 = false, _mut3743 = false, _mut3744 = false, _mut3745 = false, _mut3746 = false, _mut3747 = false, _mut3748 = false, _mut3749 = false, _mut3750 = false, _mut3751 = false, _mut3752 = false, _mut3753 = false, _mut3754 = false, _mut3755 = false, _mut3756 = false, _mut3757 = false, _mut3758 = false, _mut3759 = false, _mut3760 = false, _mut3761 = false, _mut3762 = false, _mut3763 = false, _mut3764 = false, _mut3765 = false, _mut3766 = false, _mut3767 = false, _mut3768 = false, _mut3769 = false, _mut3770 = false, _mut3771 = false, _mut3772 = false, _mut3773 = false, _mut3774 = false, _mut3775 = false, _mut3776 = false, _mut3777 = false, _mut3778 = false, _mut3779 = false, _mut3780 = false, _mut3781 = false, _mut3782 = false, _mut3783 = false, _mut3784 = false, _mut3785 = false, _mut3786 = false, _mut3787 = false, _mut3788 = false, _mut3789 = false, _mut3790 = false, _mut3791 = false, _mut3792 = false, _mut3793 = false, _mut3794 = false, _mut3795 = false, _mut3796 = false, _mut3797 = false;

    /**
     * The maximum array size used for psquare algorithm
     */
    private static final int PSQUARE_CONSTANT = 5;

    /**
     * A Default quantile needed in case if user prefers to use default no
     * argument constructor.
     */
    private static final double DEFAULT_QUANTILE_DESIRED = 50d;

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2283912083175715479L;

    /**
     * A decimal formatter for print convenience
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");

    /**
     * Initial list of 5 numbers corresponding to 5 markers. <b>NOTE:</b>watch
     * out for the add methods that are overloaded
     */
    private final List<Double> initialFive = new FixedCapacityList<Double>(PSQUARE_CONSTANT);

    /**
     * The quantile needed should be in range of 0-1. The constructor
     * {@link #PSquarePercentile(double)} ensures that passed in percentile is
     * divided by 100.
     */
    private final double quantile;

    /**
     * lastObservation is the last observation value/input sample. No need to
     * serialize
     */
    private transient double lastObservation;

    /**
     * Markers is the marker collection object which comes to effect
     * only after 5 values are inserted
     */
    private PSquareMarkers markers = null;

    /**
     * Computed p value (i,e percentile value of data set hither to received)
     */
    private double pValue = Double.NaN;

    /**
     * Counter to count the values/observations accepted into this data set
     */
    private long countOfObservations;

    /**
     * Constructs a PSquarePercentile with the specific percentile value.
     * @param p the percentile
     * @throws OutOfRangeException  if p is not greater than 0 and less
     * than or equal to 100
     */
    public PSquarePercentile(final double p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123");
        if ((_mut3501 ? (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123", _mut3491, _mut3492, _mut3493, _mut3494, _mut3495) && ROR_less(p, 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123", _mut3496, _mut3497, _mut3498, _mut3499, _mut3500)) : (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123", _mut3491, _mut3492, _mut3493, _mut3494, _mut3495) || ROR_less(p, 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123", _mut3496, _mut3497, _mut3498, _mut3499, _mut3500)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE, p, 0, 100);
        }
        // always set it within (0,1]
        this.quantile = AOR_divide(p, 100d, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquarePercentile_123", _mut3502, _mut3503, _mut3504, _mut3505);
    }

    /**
     * Default constructor that assumes a {@link #DEFAULT_QUANTILE_DESIRED
     * default quantile} needed
     */
    PSquarePercentile() {
        this(DEFAULT_QUANTILE_DESIRED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        double result = getResult();
        result = Double.isNaN(result) ? 37 : result;
        final double markersHash = markers == null ? 0 : markers.hashCode();
        final double[] toHash = { result, quantile, markersHash, countOfObservations };
        return Arrays.hashCode(toHash);
    }

    /**
     * Returns true iff {@code o} is a {@code PSquarePercentile} returning the
     * same values as this for {@code getResult()} and {@code getN()} and also
     * having equal markers
     *
     * @param o object to compare
     * @return true if {@code o} is a {@code PSquarePercentile} with
     * equivalent internal state
     */
    @Override
    public boolean equals(Object o) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_160");
        boolean result = false;
        if (this == o) {
            result = true;
        } else if ((_mut3506 ? (o != null || o instanceof PSquarePercentile) : (o != null && o instanceof PSquarePercentile))) {
            PSquarePercentile that = (PSquarePercentile) o;
            boolean isNotNull = (_mut3507 ? (markers != null || that.markers != null) : (markers != null && that.markers != null));
            boolean isNull = (_mut3508 ? (markers == null || that.markers == null) : (markers == null && that.markers == null));
            result = isNotNull ? markers.equals(that.markers) : isNull;
            // five observations
            result = (_mut3514 ? (result || ROR_equals(getN(), that.getN(), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_160", _mut3509, _mut3510, _mut3511, _mut3512, _mut3513)) : (result && ROR_equals(getN(), that.getN(), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_160", _mut3509, _mut3510, _mut3511, _mut3512, _mut3513)));
        }
        return result;
    }

    /**
     * {@inheritDoc}The internal state updated due to the new value in this
     * context is basically of the marker positions and computation of the
     * approximate quantile.
     *
     * @param observation the observation currently being added.
     */
    @Override
    public void increment(final double observation) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.increment_184");
        // Increment counter
        countOfObservations++;
        // Store last observation
        this.lastObservation = observation;
        // 0. Use Brute force for <5
        if (markers == null) {
            if (initialFive.add(observation)) {
                Collections.sort(initialFive);
                pValue = initialFive.get((int) (AOR_multiply(quantile, (AOR_minus(initialFive.size(), 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.increment_184", _mut3515, _mut3516, _mut3517, _mut3518)), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.increment_184", _mut3519, _mut3520, _mut3521, _mut3522)));
                return;
            }
            // 1. Initialize once after 5th observation
            markers = newMarkers(initialFive, quantile);
        }
        // 2. process a Data Point and return pValue
        pValue = markers.processDataPoint(observation);
    }

    /**
     * Returns a string containing the last observation, the current estimate
     * of the quantile and all markers.
     *
     * @return string representation of state data
     */
    @Override
    public String toString() {
        if (markers == null) {
            return String.format("obs=%s pValue=%s", DECIMAL_FORMAT.format(lastObservation), DECIMAL_FORMAT.format(pValue));
        } else {
            return String.format("obs=%s markers=%s", DECIMAL_FORMAT.format(lastObservation), markers.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        return countOfObservations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorelessUnivariateStatistic copy() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.copy_237");
        // multiply quantile by 100 now as anyway constructor divides it by 100
        PSquarePercentile copy = new PSquarePercentile(AOR_multiply(100d, quantile, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.copy_237", _mut3523, _mut3524, _mut3525, _mut3526));
        if (markers != null) {
            copy.markers = (PSquareMarkers) markers.clone();
        }
        copy.countOfObservations = countOfObservations;
        copy.pValue = pValue;
        copy.initialFive.clear();
        copy.initialFive.addAll(initialFive);
        return copy;
    }

    /**
     * Returns the quantile estimated by this statistic in the range [0.0-1.0]
     *
     * @return quantile estimated by {@link #getResult()}
     */
    public double quantile() {
        return quantile;
    }

    /**
     * {@inheritDoc}. This basically clears all the markers, the
     * initialFive list and sets countOfObservations to 0.
     */
    @Override
    public void clear() {
        markers = null;
        initialFive.clear();
        countOfObservations = 0L;
        pValue = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.getResult_276");
        if (ROR_equals(Double.compare(quantile, 1d), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.getResult_276", _mut3527, _mut3528, _mut3529, _mut3530, _mut3531)) {
            pValue = maximum();
        } else if (ROR_equals(Double.compare(quantile, 0d), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.getResult_276", _mut3532, _mut3533, _mut3534, _mut3535, _mut3536)) {
            pValue = minimum();
        }
        return pValue;
    }

    /**
     * @return maximum in the data set added to this statistic
     */
    private double maximum() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.maximum_289");
        double val = Double.NaN;
        if (markers != null) {
            val = markers.height(PSQUARE_CONSTANT);
        } else if (!initialFive.isEmpty()) {
            val = initialFive.get(AOR_minus(initialFive.size(), 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.maximum_289", _mut3537, _mut3538, _mut3539, _mut3540));
        }
        return val;
    }

    /**
     * @return minimum in the data set added to this statistic
     */
    private double minimum() {
        double val = Double.NaN;
        if (markers != null) {
            val = markers.height(1);
        } else if (!initialFive.isEmpty()) {
            val = initialFive.get(0);
        }
        return val;
    }

    /**
     * Markers is an encapsulation of the five markers/buckets as indicated in
     * the original works.
     */
    private static class Markers implements PSquareMarkers, Serializable {

        /**
         * Serial version id
         */
        private static final long serialVersionUID = 1L;

        /**
         * Low marker index
         */
        private static final int LOW = 2;

        /**
         * High marker index
         */
        private static final int HIGH = 4;

        /**
         * Array of 5+1 Markers (The first marker is dummy just so we
         * can match the rest of indexes [1-5] indicated in the original works
         * which follows unit based index)
         */
        private final Marker[] markerArray;

        /**
         * Kth cell belonging to [1-5] of the markerArray. No need for
         * this to be serialized
         */
        private transient int k = -1;

        /**
         * Constructor
         *
         * @param theMarkerArray marker array to be used
         */
        private Markers(final Marker[] theMarkerArray) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers_346");
            MathUtils.checkNotNull(theMarkerArray);
            markerArray = theMarkerArray;
            for (int i = 1; ROR_less(i, PSQUARE_CONSTANT, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers_346", _mut3549, _mut3550, _mut3551, _mut3552, _mut3553); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers_346");
                markerArray[i].previous(markerArray[AOR_minus(i, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers_346", _mut3545, _mut3546, _mut3547, _mut3548)]).next(markerArray[AOR_plus(i, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers_346", _mut3541, _mut3542, _mut3543, _mut3544)]).index(i);
            }
            markerArray[0].previous(markerArray[0]).next(markerArray[1]).index(0);
            markerArray[5].previous(markerArray[4]).next(markerArray[5]).index(5);
        }

        /**
         * Constructor
         *
         * @param initialFive elements required to build Marker
         * @param p quantile required to be computed
         */
        private Markers(final List<Double> initialFive, final double p) {
            this(createMarkerArray(initialFive, p));
        }

        /**
         * Creates a marker array using initial five elements and a quantile
         *
         * @param initialFive list of initial five elements
         * @param p the pth quantile
         * @return Marker array
         */
        private static Marker[] createMarkerArray(final List<Double> initialFive, final double p) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376");
            final int countObserved = initialFive == null ? -1 : initialFive.size();
            if (ROR_less(countObserved, PSQUARE_CONSTANT, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3554, _mut3555, _mut3556, _mut3557, _mut3558)) {
                throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, countObserved, PSQUARE_CONSTANT);
            }
            Collections.sort(initialFive);
            return new Marker[] { // Null Marker
            new Marker(), new Marker(initialFive.get(0), 1, 0, 1), new Marker(initialFive.get(1), AOR_plus(1, AOR_multiply(2, p, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3559, _mut3560, _mut3561, _mut3562), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3563, _mut3564, _mut3565, _mut3566), AOR_divide(p, 2, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3567, _mut3568, _mut3569, _mut3570), 2), new Marker(initialFive.get(2), AOR_plus(1, AOR_multiply(4, p, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3571, _mut3572, _mut3573, _mut3574), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3575, _mut3576, _mut3577, _mut3578), p, 3), new Marker(initialFive.get(3), AOR_plus(3, AOR_multiply(2, p, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3579, _mut3580, _mut3581, _mut3582), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3583, _mut3584, _mut3585, _mut3586), AOR_divide((AOR_plus(1, p, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3587, _mut3588, _mut3589, _mut3590)), 2, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.createMarkerArray_376", _mut3591, _mut3592, _mut3593, _mut3594), 4), new Marker(initialFive.get(4), 5, 1, 5) };
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Arrays.deepHashCode(markerArray);
        }

        /**
         * {@inheritDoc}.This equals method basically checks for marker array to
         * be deep equals.
         *
         * @param o is the other object
         * @return true if the object compares with this object are equivalent
         */
        @Override
        public boolean equals(Object o) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_410");
            boolean result = false;
            if (this == o) {
                result = true;
            } else if ((_mut3595 ? (o != null || o instanceof Markers) : (o != null && o instanceof Markers))) {
                Markers that = (Markers) o;
                result = Arrays.deepEquals(markerArray, that.markerArray);
            }
            return result;
        }

        /**
         * Process a data point
         *
         * @param inputDataPoint is the data point passed
         * @return computed percentile
         */
        public double processDataPoint(final double inputDataPoint) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.processDataPoint_428");
            // 1. Find cell and update minima and maxima
            final int kthCell = findCellAndUpdateMinMax(inputDataPoint);
            // 2. Increment positions
            incrementPositions(1, AOR_plus(kthCell, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.processDataPoint_428", _mut3596, _mut3597, _mut3598, _mut3599), 5);
            // 2a. Update desired position with increments
            updateDesiredPositions();
            // 3. Adjust heights of m[2-4] if necessary
            adjustHeightsOfMarkers();
            // 4. Return percentile
            return getPercentileValue();
        }

        /**
         * Returns the percentile computed thus far.
         *
         * @return height of mid point marker
         */
        public double getPercentileValue() {
            return height(3);
        }

        /**
         * Finds the cell where the input observation / value fits.
         *
         * @param observation the input value to be checked for
         * @return kth cell (of the markers ranging from 1-5) where observed
         *         sample fits
         */
        private int findCellAndUpdateMinMax(final double observation) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462");
            k = -1;
            if (ROR_less(observation, height(1), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462", _mut3600, _mut3601, _mut3602, _mut3603, _mut3604)) {
                markerArray[1].markerHeight = observation;
                k = 1;
            } else if (ROR_less(observation, height(2), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462", _mut3605, _mut3606, _mut3607, _mut3608, _mut3609)) {
                k = 1;
            } else if (ROR_less(observation, height(3), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462", _mut3610, _mut3611, _mut3612, _mut3613, _mut3614)) {
                k = 2;
            } else if (ROR_less(observation, height(4), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462", _mut3615, _mut3616, _mut3617, _mut3618, _mut3619)) {
                k = 3;
            } else if (ROR_less_equals(observation, height(5), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.findCellAndUpdateMinMax_462", _mut3620, _mut3621, _mut3622, _mut3623, _mut3624)) {
                k = 4;
            } else {
                markerArray[5].markerHeight = observation;
                k = 4;
            }
            return k;
        }

        /**
         * Adjust marker heights by setting quantile estimates to middle markers.
         */
        private void adjustHeightsOfMarkers() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.adjustHeightsOfMarkers_485");
            for (int i = LOW; ROR_less_equals(i, HIGH, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.adjustHeightsOfMarkers_485", _mut3625, _mut3626, _mut3627, _mut3628, _mut3629); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.adjustHeightsOfMarkers_485");
                estimate(i);
            }
        }

        /**
         * {@inheritDoc}
         */
        public double estimate(final int index) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_494");
            if ((_mut3640 ? (ROR_less(index, LOW, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_494", _mut3630, _mut3631, _mut3632, _mut3633, _mut3634) && ROR_greater(index, HIGH, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_494", _mut3635, _mut3636, _mut3637, _mut3638, _mut3639)) : (ROR_less(index, LOW, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_494", _mut3630, _mut3631, _mut3632, _mut3633, _mut3634) || ROR_greater(index, HIGH, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_494", _mut3635, _mut3636, _mut3637, _mut3638, _mut3639)))) {
                throw new OutOfRangeException(index, LOW, HIGH);
            }
            return markerArray[index].estimate();
        }

        /**
         * Increment positions by d. Refer to algorithm paper for the
         * definition of d.
         *
         * @param d The increment value for the position
         * @param startIndex start index of the marker array
         * @param endIndex end index of the marker array
         */
        private void incrementPositions(final int d, final int startIndex, final int endIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.incrementPositions_509");
            for (int i = startIndex; ROR_less_equals(i, endIndex, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.incrementPositions_509", _mut3641, _mut3642, _mut3643, _mut3644, _mut3645); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.incrementPositions_509");
                markerArray[i].incrementPosition(d);
            }
        }

        /**
         * Desired positions incremented by bucket width. The bucket width is
         * basically the desired increments.
         */
        private void updateDesiredPositions() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.updateDesiredPositions_520");
            for (int i = 1; ROR_less(i, markerArray.length, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.updateDesiredPositions_520", _mut3646, _mut3647, _mut3648, _mut3649, _mut3650); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.updateDesiredPositions_520");
                markerArray[i].updateDesiredPosition();
            }
        }

        /**
         * Sets previous and next markers after default read is done.
         *
         * @param anInputStream the input stream to be deserialized
         * @throws ClassNotFoundException thrown when a desired class not found
         * @throws IOException thrown due to any io errors
         */
        private void readObject(ObjectInputStream anInputStream) throws ClassNotFoundException, IOException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.readObject_533");
            // always perform the default de-serialization first
            anInputStream.defaultReadObject();
            // Build links
            for (int i = 1; ROR_less(i, PSQUARE_CONSTANT, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.readObject_533", _mut3659, _mut3660, _mut3661, _mut3662, _mut3663); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.readObject_533");
                markerArray[i].previous(markerArray[AOR_minus(i, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.readObject_533", _mut3655, _mut3656, _mut3657, _mut3658)]).next(markerArray[AOR_plus(i, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.readObject_533", _mut3651, _mut3652, _mut3653, _mut3654)]).index(i);
            }
            markerArray[0].previous(markerArray[0]).next(markerArray[1]).index(0);
            markerArray[5].previous(markerArray[4]).next(markerArray[5]).index(5);
        }

        /**
         * Return marker height given index
         *
         * @param markerIndex index of marker within (1,6)
         * @return marker height
         */
        public double height(final int markerIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.height_554");
            if ((_mut3674 ? (ROR_greater_equals(markerIndex, markerArray.length, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.height_554", _mut3664, _mut3665, _mut3666, _mut3667, _mut3668) && ROR_less_equals(markerIndex, 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.height_554", _mut3669, _mut3670, _mut3671, _mut3672, _mut3673)) : (ROR_greater_equals(markerIndex, markerArray.length, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.height_554", _mut3664, _mut3665, _mut3666, _mut3667, _mut3668) || ROR_less_equals(markerIndex, 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.height_554", _mut3669, _mut3670, _mut3671, _mut3672, _mut3673)))) {
                throw new OutOfRangeException(markerIndex, 1, markerArray.length);
            }
            return markerArray[markerIndex].markerHeight;
        }

        /**
         * {@inheritDoc}.Clone Markers
         *
         * @return cloned object
         */
        @Override
        public Object clone() {
            return new Markers(new Marker[] { new Marker(), (Marker) markerArray[1].clone(), (Marker) markerArray[2].clone(), (Marker) markerArray[3].clone(), (Marker) markerArray[4].clone(), (Marker) markerArray[5].clone() });
        }

        /**
         * Returns string representation of the Marker array.
         *
         * @return Markers as a string
         */
        @Override
        public String toString() {
            return String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", markerArray[1].toString(), markerArray[2].toString(), markerArray[3].toString(), markerArray[4].toString(), markerArray[5].toString());
        }
    }

    /**
     * The class modeling the attributes of the marker of the P-square algorithm
     */
    private static class Marker implements Serializable, Cloneable {

        /**
         * Serial Version ID
         */
        private static final long serialVersionUID = -3575879478288538431L;

        /**
         * The marker index which is just a serial number for the marker in the
         * marker array of 5+1.
         */
        private int index;

        /**
         * The integral marker position. Refer to the variable n in the original
         * works.
         */
        private double intMarkerPosition;

        /**
         * Desired marker position. Refer to the variable n' in the original
         * works.
         */
        private double desiredMarkerPosition;

        /**
         * Marker height or the quantile. Refer to the variable q in the
         * original works.
         */
        private double markerHeight;

        /**
         * Desired marker increment. Refer to the variable dn' in the original
         * works.
         */
        private double desiredMarkerIncrement;

        /**
         * Next and previous markers for easy linked navigation in loops. this
         * is not serialized as they can be rebuilt during deserialization.
         */
        private transient Marker next;

        /**
         * The previous marker links
         */
        private transient Marker previous;

        /**
         * Nonlinear interpolator
         */
        private final UnivariateInterpolator nonLinear = new NevilleInterpolator();

        /**
         * Linear interpolator which is not serializable
         */
        private transient UnivariateInterpolator linear = new LinearInterpolator();

        /**
         * Default constructor
         */
        private Marker() {
            this.next = this.previous = this;
        }

        /**
         * Constructor of the marker with parameters
         *
         * @param heightOfMarker represent the quantile value
         * @param makerPositionDesired represent the desired marker position
         * @param markerPositionIncrement represent increments for position
         * @param markerPositionNumber represent the position number of marker
         */
        private Marker(double heightOfMarker, double makerPositionDesired, double markerPositionIncrement, double markerPositionNumber) {
            this();
            this.markerHeight = heightOfMarker;
            this.desiredMarkerPosition = makerPositionDesired;
            this.desiredMarkerIncrement = markerPositionIncrement;
            this.intMarkerPosition = markerPositionNumber;
        }

        /**
         * Sets the previous marker.
         *
         * @param previousMarker the previous marker to the current marker in
         *            the array of markers
         * @return this instance
         */
        private Marker previous(final Marker previousMarker) {
            MathUtils.checkNotNull(previousMarker);
            this.previous = previousMarker;
            return this;
        }

        /**
         * Sets the next marker.
         *
         * @param nextMarker the next marker to the current marker in the array
         *            of markers
         * @return this instance
         */
        private Marker next(final Marker nextMarker) {
            MathUtils.checkNotNull(nextMarker);
            this.next = nextMarker;
            return this;
        }

        /**
         * Sets the index of the marker.
         *
         * @param indexOfMarker the array index of the marker in marker array
         * @return this instance
         */
        private Marker index(final int indexOfMarker) {
            this.index = indexOfMarker;
            return this;
        }

        /**
         * Update desired Position with increment.
         */
        private void updateDesiredPosition() {
            desiredMarkerPosition += desiredMarkerIncrement;
        }

        /**
         * Increment Position by d.
         *
         * @param d a delta value to increment
         */
        private void incrementPosition(final int d) {
            intMarkerPosition += d;
        }

        /**
         * Difference between desired and actual position
         *
         * @return difference between desired and actual position
         */
        private double difference() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.difference_738");
            return AOR_minus(desiredMarkerPosition, intMarkerPosition, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.difference_738", _mut3675, _mut3676, _mut3677, _mut3678);
        }

        /**
         * Estimate the quantile for the current marker.
         *
         * @return estimated quantile
         */
        private double estimate() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747");
            final double di = difference();
            final boolean isNextHigher = ROR_greater(AOR_minus(next.intMarkerPosition, intMarkerPosition, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3679, _mut3680, _mut3681, _mut3682), 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3683, _mut3684, _mut3685, _mut3686, _mut3687);
            final boolean isPreviousLower = ROR_less(AOR_minus(previous.intMarkerPosition, intMarkerPosition, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3688, _mut3689, _mut3690, _mut3691), -1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3692, _mut3693, _mut3694, _mut3695, _mut3696);
            if ((_mut3709 ? ((_mut3702 ? (ROR_greater_equals(di, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3697, _mut3698, _mut3699, _mut3700, _mut3701) || isNextHigher) : (ROR_greater_equals(di, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3697, _mut3698, _mut3699, _mut3700, _mut3701) && isNextHigher)) && (_mut3708 ? (ROR_less_equals(di, -1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3703, _mut3704, _mut3705, _mut3706, _mut3707) || isPreviousLower) : (ROR_less_equals(di, -1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3703, _mut3704, _mut3705, _mut3706, _mut3707) && isPreviousLower))) : ((_mut3702 ? (ROR_greater_equals(di, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3697, _mut3698, _mut3699, _mut3700, _mut3701) || isNextHigher) : (ROR_greater_equals(di, 1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3697, _mut3698, _mut3699, _mut3700, _mut3701) && isNextHigher)) || (_mut3708 ? (ROR_less_equals(di, -1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3703, _mut3704, _mut3705, _mut3706, _mut3707) || isPreviousLower) : (ROR_less_equals(di, -1, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3703, _mut3704, _mut3705, _mut3706, _mut3707) && isPreviousLower))))) {
                final int d = ROR_greater_equals(di, 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3710, _mut3711, _mut3712, _mut3713, _mut3714) ? 1 : -1;
                final double[] xval = new double[] { previous.intMarkerPosition, intMarkerPosition, next.intMarkerPosition };
                final double[] yval = new double[] { previous.markerHeight, markerHeight, next.markerHeight };
                final double xD = AOR_plus(intMarkerPosition, d, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3715, _mut3716, _mut3717, _mut3718);
                UnivariateFunction univariateFunction = nonLinear.interpolate(xval, yval);
                markerHeight = univariateFunction.value(xD);
                // If parabolic estimate is bad then turn linear
                if (isEstimateBad(yval, markerHeight)) {
                    int delta = ROR_greater(AOR_minus(xD, xval[1], "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3719, _mut3720, _mut3721, _mut3722), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3723, _mut3724, _mut3725, _mut3726, _mut3727) ? 1 : -1;
                    final double[] xBad = new double[] { xval[1], xval[AOR_plus(1, delta, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3728, _mut3729, _mut3730, _mut3731)] };
                    final double[] yBad = new double[] { yval[1], yval[AOR_plus(1, delta, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.estimate_747", _mut3732, _mut3733, _mut3734, _mut3735)] };
                    // since d can be +/- 1
                    MathArrays.sortInPlace(xBad, yBad);
                    univariateFunction = linear.interpolate(xBad, yBad);
                    markerHeight = univariateFunction.value(xD);
                }
                incrementPosition(d);
            }
            return markerHeight;
        }

        /**
         * Check if parabolic/nonlinear estimate is bad by checking if the
         * ordinate found is beyond the y[0] and y[2].
         *
         * @param y the array to get the bounds
         * @param yD the estimate
         * @return true if yD is a bad estimate
         */
        private boolean isEstimateBad(final double[] y, final double yD) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.isEstimateBad_792");
            return (_mut3746 ? (ROR_less_equals(yD, y[0], "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.isEstimateBad_792", _mut3736, _mut3737, _mut3738, _mut3739, _mut3740) && ROR_greater_equals(yD, y[2], "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.isEstimateBad_792", _mut3741, _mut3742, _mut3743, _mut3744, _mut3745)) : (ROR_less_equals(yD, y[0], "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.isEstimateBad_792", _mut3736, _mut3737, _mut3738, _mut3739, _mut3740) || ROR_greater_equals(yD, y[2], "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.isEstimateBad_792", _mut3741, _mut3742, _mut3743, _mut3744, _mut3745)));
        }

        /**
         * {@inheritDoc}<i>This equals method checks for marker attributes and
         * as well checks if navigation pointers (next and previous) are the same
         * between this and passed in object</i>
         *
         * @param o Other object
         * @return true if this equals passed in other object o
         */
        @Override
        public boolean equals(Object o) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804");
            boolean result = false;
            if (this == o) {
                result = true;
            } else if ((_mut3747 ? (o != null || o instanceof Marker) : (o != null && o instanceof Marker))) {
                Marker that = (Marker) o;
                result = ROR_equals(Double.compare(markerHeight, that.markerHeight), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3748, _mut3749, _mut3750, _mut3751, _mut3752);
                result = (_mut3758 ? (result || ROR_equals(Double.compare(intMarkerPosition, that.intMarkerPosition), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3753, _mut3754, _mut3755, _mut3756, _mut3757)) : (result && ROR_equals(Double.compare(intMarkerPosition, that.intMarkerPosition), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3753, _mut3754, _mut3755, _mut3756, _mut3757)));
                result = (_mut3764 ? (result || ROR_equals(Double.compare(desiredMarkerPosition, that.desiredMarkerPosition), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3759, _mut3760, _mut3761, _mut3762, _mut3763)) : (result && ROR_equals(Double.compare(desiredMarkerPosition, that.desiredMarkerPosition), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3759, _mut3760, _mut3761, _mut3762, _mut3763)));
                result = (_mut3770 ? (result || ROR_equals(Double.compare(desiredMarkerIncrement, that.desiredMarkerIncrement), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3765, _mut3766, _mut3767, _mut3768, _mut3769)) : (result && ROR_equals(Double.compare(desiredMarkerIncrement, that.desiredMarkerIncrement), 0, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3765, _mut3766, _mut3767, _mut3768, _mut3769)));
                result = (_mut3776 ? (result || ROR_equals(next.index, that.next.index, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3771, _mut3772, _mut3773, _mut3774, _mut3775)) : (result && ROR_equals(next.index, that.next.index, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3771, _mut3772, _mut3773, _mut3774, _mut3775)));
                result = (_mut3782 ? (result || ROR_equals(previous.index, that.previous.index, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3777, _mut3778, _mut3779, _mut3780, _mut3781)) : (result && ROR_equals(previous.index, that.previous.index, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.equals_804", _mut3777, _mut3778, _mut3779, _mut3780, _mut3781)));
            }
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Arrays.hashCode(new double[] { markerHeight, intMarkerPosition, desiredMarkerIncrement, desiredMarkerPosition, previous.index, next.index });
        }

        /**
         * Read Object to deserialize.
         *
         * @param anInstream Stream Object data
         * @throws IOException thrown for IO Errors
         * @throws ClassNotFoundException thrown for class not being found
         */
        private void readObject(ObjectInputStream anInstream) throws ClassNotFoundException, IOException {
            anInstream.defaultReadObject();
            previous = next = this;
            linear = new LinearInterpolator();
        }

        /**
         * Clone this instance.
         *
         * @return cloned marker
         */
        @Override
        public Object clone() {
            return new Marker(markerHeight, desiredMarkerPosition, desiredMarkerIncrement, intMarkerPosition);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", (double) index, Precision.round(intMarkerPosition, 0), Precision.round(desiredMarkerPosition, 2), Precision.round(markerHeight, 2), Precision.round(desiredMarkerIncrement, 2), previous.index, next.index);
        }
    }

    /**
     * A simple fixed capacity list that has an upper bound to growth.
     * Once its capacity is reached, {@code add} is a no-op, returning
     * {@code false}.
     *
     * @param <E>
     */
    private static class FixedCapacityList<E> extends ArrayList<E> implements Serializable {

        /**
         * Serialization Version Id
         */
        private static final long serialVersionUID = 2283952083075725479L;

        /**
         * Capacity of the list
         */
        private final int capacity;

        /**
         * This constructor constructs the list with given capacity and as well
         * as stores the capacity
         *
         * @param fixedCapacity the capacity to be fixed for this list
         */
        FixedCapacityList(final int fixedCapacity) {
            super(fixedCapacity);
            this.capacity = fixedCapacity;
        }

        /**
         * {@inheritDoc} In addition it checks if the {@link #size()} returns a
         * size that is within capacity and if true it adds; otherwise the list
         * contents are unchanged and {@code false} is returned.
         *
         * @return true if addition is successful and false otherwise
         */
        @Override
        public boolean add(final E e) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.add_915");
            return ROR_less(size(), capacity, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.add_915", _mut3783, _mut3784, _mut3785, _mut3786, _mut3787) ? super.add(e) : false;
        }

        /**
         * {@inheritDoc} In addition it checks if the sum of Collection size and
         * this instance's {@link #size()} returns a value that is within
         * capacity and if true it adds the collection; otherwise the list
         * contents are unchanged and {@code false} is returned.
         *
         * @return true if addition is successful and false otherwise
         */
        @Override
        public boolean addAll(Collection<? extends E> collection) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.addAll_928");
            boolean isCollectionLess = (_mut3797 ? (collection != null || ROR_less_equals(AOR_plus(collection.size(), size(), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.addAll_928", _mut3788, _mut3789, _mut3790, _mut3791), capacity, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.addAll_928", _mut3792, _mut3793, _mut3794, _mut3795, _mut3796)) : (collection != null && ROR_less_equals(AOR_plus(collection.size(), size(), "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.addAll_928", _mut3788, _mut3789, _mut3790, _mut3791), capacity, "org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.addAll_928", _mut3792, _mut3793, _mut3794, _mut3795, _mut3796)));
            return isCollectionLess ? super.addAll(collection) : false;
        }
    }

    /**
     * A creation method to build Markers
     *
     * @param initialFive list of initial five elements
     * @param p the quantile desired
     * @return an instance of PSquareMarkers
     */
    public static PSquareMarkers newMarkers(final List<Double> initialFive, final double p) {
        return new Markers(initialFive, p);
    }

    /**
     * An interface that encapsulates abstractions of the
     * P-square algorithm markers as is explained in the original works. This
     * interface is exposed with protected access to help in testability.
     */
    protected interface PSquareMarkers extends Cloneable {

        /**
         * Returns Percentile value computed thus far.
         *
         * @return percentile
         */
        double getPercentileValue();

        /**
         * A clone function to clone the current instance. It's created as an
         * interface method as well for convenience though Cloneable is just a
         * marker interface.
         *
         * @return clone of this instance
         */
        Object clone();

        /**
         * Returns the marker height (or percentile) of a given marker index.
         *
         * @param markerIndex is the index of marker in the marker array
         * @return percentile value of the marker index passed
         * @throws OutOfRangeException in case the index is not within [1-5]
         */
        double height(final int markerIndex);

        /**
         * Process a data point by moving the marker heights based on estimator.
         *
         * @param inputDataPoint is the data point passed
         * @return computed percentile
         */
        double processDataPoint(final double inputDataPoint);

        /**
         * An Estimate of the percentile value of a given Marker
         *
         * @param index the marker's index in the array of markers
         * @return percentile estimate
         * @throws OutOfRangeException in case if index is not within [1-5]
         */
        double estimate(final int index);
    }
}
