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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;
import java.util.Arrays;
import java.util.Comparator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of Kendall's Tau-b rank correlation</a>.
 * <p>
 * A pair of observations (x<sub>1</sub>, y<sub>1</sub>) and
 * (x<sub>2</sub>, y<sub>2</sub>) are considered <i>concordant</i> if
 * x<sub>1</sub> &lt; x<sub>2</sub> and y<sub>1</sub> &lt; y<sub>2</sub>
 * or x<sub>2</sub> &lt; x<sub>1</sub> and y<sub>2</sub> &lt; y<sub>1</sub>.
 * The pair is <i>discordant</i> if x<sub>1</sub> &lt; x<sub>2</sub> and
 * y<sub>2</sub> &lt; y<sub>1</sub> or x<sub>2</sub> &lt; x<sub>1</sub> and
 * y<sub>1</sub> &lt; y<sub>2</sub>.  If either x<sub>1</sub> = x<sub>2</sub>
 * or y<sub>1</sub> = y<sub>2</sub>, the pair is neither concordant nor
 * discordant.
 * <p>
 * Kendall's Tau-b is defined as:
 * <pre>
 * tau<sub>b</sub> = (n<sub>c</sub> - n<sub>d</sub>) / sqrt((n<sub>0</sub> - n<sub>1</sub>) * (n<sub>0</sub> - n<sub>2</sub>))
 * </pre>
 * <p>
 * where:
 * <ul>
 *     <li>n<sub>0</sub> = n * (n - 1) / 2</li>
 *     <li>n<sub>c</sub> = Number of concordant pairs</li>
 *     <li>n<sub>d</sub> = Number of discordant pairs</li>
 *     <li>n<sub>1</sub> = sum of t<sub>i</sub> * (t<sub>i</sub> - 1) / 2 for all i</li>
 *     <li>n<sub>2</sub> = sum of u<sub>j</sub> * (u<sub>j</sub> - 1) / 2 for all j</li>
 *     <li>t<sub>i</sub> = Number of tied values in the i<sup>th</sup> group of ties in x</li>
 *     <li>u<sub>j</sub> = Number of tied values in the j<sup>th</sup> group of ties in y</li>
 * </ul>
 * <p>
 * This implementation uses the O(n log n) algorithm described in
 * William R. Knight's 1966 paper "A Computer Method for Calculating
 * Kendall's Tau with Ungrouped Data" in the Journal of the American
 * Statistical Association.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient">
 * Kendall tau rank correlation coefficient (Wikipedia)</a>
 * @see <a href="http://www.jstor.org/stable/2282833">A Computer
 * Method for Calculating Kendall's Tau with Ungrouped Data</a>
 *
 * @since 3.3
 */
public class KendallsCorrelation {

    @Conditional
    public static boolean _mut10695 = false, _mut10696 = false, _mut10697 = false, _mut10698 = false, _mut10699 = false, _mut10700 = false, _mut10701 = false, _mut10702 = false, _mut10703 = false, _mut10704 = false, _mut10705 = false, _mut10706 = false, _mut10707 = false, _mut10708 = false, _mut10709 = false, _mut10710 = false, _mut10711 = false, _mut10712 = false, _mut10713 = false, _mut10714 = false, _mut10715 = false, _mut10716 = false, _mut10717 = false, _mut10718 = false, _mut10719 = false, _mut10720 = false, _mut10721 = false, _mut10722 = false, _mut10723 = false, _mut10724 = false, _mut10725 = false, _mut10726 = false, _mut10727 = false, _mut10728 = false, _mut10729 = false, _mut10730 = false, _mut10731 = false, _mut10732 = false, _mut10733 = false, _mut10734 = false, _mut10735 = false, _mut10736 = false, _mut10737 = false, _mut10738 = false, _mut10739 = false, _mut10740 = false, _mut10741 = false, _mut10742 = false, _mut10743 = false, _mut10744 = false, _mut10745 = false, _mut10746 = false, _mut10747 = false, _mut10748 = false, _mut10749 = false, _mut10750 = false, _mut10751 = false, _mut10752 = false, _mut10753 = false, _mut10754 = false, _mut10755 = false, _mut10756 = false, _mut10757 = false, _mut10758 = false, _mut10759 = false, _mut10760 = false, _mut10761 = false, _mut10762 = false, _mut10763 = false, _mut10764 = false, _mut10765 = false, _mut10766 = false, _mut10767 = false, _mut10768 = false, _mut10769 = false, _mut10770 = false, _mut10771 = false, _mut10772 = false, _mut10773 = false, _mut10774 = false, _mut10775 = false, _mut10776 = false, _mut10777 = false, _mut10778 = false, _mut10779 = false, _mut10780 = false, _mut10781 = false, _mut10782 = false, _mut10783 = false, _mut10784 = false, _mut10785 = false, _mut10786 = false, _mut10787 = false, _mut10788 = false, _mut10789 = false, _mut10790 = false, _mut10791 = false, _mut10792 = false, _mut10793 = false, _mut10794 = false, _mut10795 = false, _mut10796 = false, _mut10797 = false, _mut10798 = false, _mut10799 = false, _mut10800 = false, _mut10801 = false, _mut10802 = false, _mut10803 = false, _mut10804 = false, _mut10805 = false, _mut10806 = false, _mut10807 = false, _mut10808 = false, _mut10809 = false, _mut10810 = false, _mut10811 = false, _mut10812 = false, _mut10813 = false, _mut10814 = false, _mut10815 = false, _mut10816 = false, _mut10817 = false, _mut10818 = false, _mut10819 = false, _mut10820 = false, _mut10821 = false, _mut10822 = false, _mut10823 = false, _mut10824 = false, _mut10825 = false, _mut10826 = false, _mut10827 = false, _mut10828 = false, _mut10829 = false, _mut10830 = false, _mut10831 = false, _mut10832 = false, _mut10833 = false, _mut10834 = false, _mut10835 = false, _mut10836 = false, _mut10837 = false, _mut10838 = false, _mut10839 = false, _mut10840 = false, _mut10841 = false, _mut10842 = false, _mut10843 = false, _mut10844 = false, _mut10845 = false, _mut10846 = false, _mut10847 = false, _mut10848 = false, _mut10849 = false, _mut10850 = false, _mut10851 = false, _mut10852 = false, _mut10853 = false, _mut10854 = false, _mut10855 = false, _mut10856 = false, _mut10857 = false;

    /**
     * correlation matrix
     */
    private final RealMatrix correlationMatrix;

    /**
     * Create a KendallsCorrelation instance without data.
     */
    public KendallsCorrelation() {
        correlationMatrix = null;
    }

    /**
     * Create a KendallsCorrelation from a rectangular array
     * whose columns represent values of variables to be correlated.
     *
     * @param data rectangular array with columns representing variables
     * @throws IllegalArgumentException if the input data array is not
     * rectangular with at least two rows and two columns.
     */
    public KendallsCorrelation(double[][] data) {
        this(MatrixUtils.createRealMatrix(data));
    }

    /**
     * Create a KendallsCorrelation from a RealMatrix whose columns
     * represent variables to be correlated.
     *
     * @param matrix matrix with columns representing variables to correlate
     */
    public KendallsCorrelation(RealMatrix matrix) {
        correlationMatrix = computeCorrelationMatrix(matrix);
    }

    /**
     * Returns the correlation matrix.
     *
     * @return correlation matrix
     */
    public RealMatrix getCorrelationMatrix() {
        return correlationMatrix;
    }

    /**
     * Computes the Kendall's Tau rank correlation matrix for the columns of
     * the input matrix.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @return correlation matrix
     */
    public RealMatrix computeCorrelationMatrix(final RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.computeCorrelationMatrix_120");
        int nVars = matrix.getColumnDimension();
        RealMatrix outMatrix = new BlockRealMatrix(nVars, nVars);
        for (int i = 0; ROR_less(i, nVars, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.computeCorrelationMatrix_120", _mut10700, _mut10701, _mut10702, _mut10703, _mut10704); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.computeCorrelationMatrix_120");
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.computeCorrelationMatrix_120", _mut10695, _mut10696, _mut10697, _mut10698, _mut10699); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.computeCorrelationMatrix_120");
                double corr = correlation(matrix.getColumn(i), matrix.getColumn(j));
                outMatrix.setEntry(i, j, corr);
                outMatrix.setEntry(j, i, corr);
            }
            outMatrix.setEntry(i, i, 1d);
        }
        return outMatrix;
    }

    /**
     * Computes the Kendall's Tau rank correlation matrix for the columns of
     * the input rectangular array.  The columns of the array represent values
     * of variables to be correlated.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @return correlation matrix
     */
    public RealMatrix computeCorrelationMatrix(final double[][] matrix) {
        return computeCorrelationMatrix(new BlockRealMatrix(matrix));
    }

    /**
     * Computes the Kendall's Tau rank correlation coefficient between the two arrays.
     *
     * @param xArray first data array
     * @param yArray second data array
     * @return Returns Kendall's Tau rank correlation coefficient for the two arrays
     * @throws DimensionMismatchException if the arrays lengths do not match
     */
    public double correlation(final double[] xArray, final double[] yArray) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
        if (ROR_not_equals(xArray.length, yArray.length, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.correlation_154", _mut10705, _mut10706, _mut10707, _mut10708, _mut10709)) {
            throw new DimensionMismatchException(xArray.length, yArray.length);
        }
        final int n = xArray.length;
        final long numPairs = sum(AOR_minus(n, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.correlation_154", _mut10710, _mut10711, _mut10712, _mut10713));
        @SuppressWarnings("unchecked")
        Pair<Double, Double>[] pairs = new Pair[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.correlation_154", _mut10714, _mut10715, _mut10716, _mut10717, _mut10718); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.correlation_154");
            pairs[i] = new Pair<Double, Double>(xArray[i], yArray[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair<Double, Double>>() {

            /**
             * {@inheritDoc}
             */
            public int compare(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
                int compareFirst = pair1.getFirst().compareTo(pair2.getFirst());
                return ROR_not_equals(compareFirst, 0, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10719, _mut10720, _mut10721, _mut10722, _mut10723) ? compareFirst : pair1.getSecond().compareTo(pair2.getSecond());
            }
        });
        long tiedXPairs = 0;
        long tiedXYPairs = 0;
        long consecutiveXTies = 1;
        long consecutiveXYTies = 1;
        Pair<Double, Double> prev = pairs[0];
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10736, _mut10737, _mut10738, _mut10739, _mut10740); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
            final Pair<Double, Double> curr = pairs[i];
            if (curr.getFirst().equals(prev.getFirst())) {
                consecutiveXTies++;
                if (curr.getSecond().equals(prev.getSecond())) {
                    consecutiveXYTies++;
                } else {
                    tiedXYPairs += sum(AOR_minus(consecutiveXYTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10732, _mut10733, _mut10734, _mut10735));
                    consecutiveXYTies = 1;
                }
            } else {
                tiedXPairs += sum(AOR_minus(consecutiveXTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10724, _mut10725, _mut10726, _mut10727));
                consecutiveXTies = 1;
                tiedXYPairs += sum(AOR_minus(consecutiveXYTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10728, _mut10729, _mut10730, _mut10731));
                consecutiveXYTies = 1;
            }
            prev = curr;
        }
        tiedXPairs += sum(AOR_minus(consecutiveXTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10741, _mut10742, _mut10743, _mut10744));
        tiedXYPairs += sum(AOR_minus(consecutiveXYTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10745, _mut10746, _mut10747, _mut10748));
        long swaps = 0;
        @SuppressWarnings("unchecked")
        Pair<Double, Double>[] pairsDestination = new Pair[n];
        for (int segmentSize = 1; ROR_less(segmentSize, n, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10792, _mut10793, _mut10794, _mut10795, _mut10796); segmentSize <<= 1) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
            for (int offset = 0; ROR_less(offset, n, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10787, _mut10788, _mut10789, _mut10790, _mut10791); offset += 2 * segmentSize) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
                int i = offset;
                final int iEnd = FastMath.min(AOR_plus(i, segmentSize, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10749, _mut10750, _mut10751, _mut10752), n);
                int j = iEnd;
                final int jEnd = FastMath.min(AOR_plus(j, segmentSize, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10753, _mut10754, _mut10755, _mut10756), n);
                int copyLocation = offset;
                while ((_mut10786 ? (ROR_less(i, iEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10776, _mut10777, _mut10778, _mut10779, _mut10780) && ROR_less(j, jEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10781, _mut10782, _mut10783, _mut10784, _mut10785)) : (ROR_less(i, iEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10776, _mut10777, _mut10778, _mut10779, _mut10780) || ROR_less(j, jEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10781, _mut10782, _mut10783, _mut10784, _mut10785)))) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
                    if (ROR_less(i, iEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10757, _mut10758, _mut10759, _mut10760, _mut10761)) {
                        if (ROR_less(j, jEnd, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10762, _mut10763, _mut10764, _mut10765, _mut10766)) {
                            if (ROR_less_equals(pairs[i].getSecond().compareTo(pairs[j].getSecond()), 0, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10767, _mut10768, _mut10769, _mut10770, _mut10771)) {
                                pairsDestination[copyLocation] = pairs[i];
                                i++;
                            } else {
                                pairsDestination[copyLocation] = pairs[j];
                                j++;
                                swaps += AOR_minus(iEnd, i, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10772, _mut10773, _mut10774, _mut10775);
                            }
                        } else {
                            pairsDestination[copyLocation] = pairs[i];
                            i++;
                        }
                    } else {
                        pairsDestination[copyLocation] = pairs[j];
                        j++;
                    }
                    copyLocation++;
                }
            }
            final Pair<Double, Double>[] pairsTemp = pairs;
            pairs = pairsDestination;
            pairsDestination = pairsTemp;
        }
        long tiedYPairs = 0;
        long consecutiveYTies = 1;
        prev = pairs[0];
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10801, _mut10802, _mut10803, _mut10804, _mut10805); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172");
            final Pair<Double, Double> curr = pairs[i];
            if (curr.getSecond().equals(prev.getSecond())) {
                consecutiveYTies++;
            } else {
                tiedYPairs += sum(AOR_minus(consecutiveYTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10797, _mut10798, _mut10799, _mut10800));
                consecutiveYTies = 1;
            }
            prev = curr;
        }
        tiedYPairs += sum(AOR_minus(consecutiveYTies, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10806, _mut10807, _mut10808, _mut10809));
        final long concordantMinusDiscordant = AOR_minus(AOR_plus(AOR_minus(AOR_minus(numPairs, tiedXPairs, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10810, _mut10811, _mut10812, _mut10813), tiedYPairs, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10814, _mut10815, _mut10816, _mut10817), tiedXYPairs, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10818, _mut10819, _mut10820, _mut10821), AOR_multiply(2, swaps, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10822, _mut10823, _mut10824, _mut10825), "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10826, _mut10827, _mut10828, _mut10829);
        final double nonTiedPairsMultiplied = AOR_multiply((AOR_minus(numPairs, tiedXPairs, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10830, _mut10831, _mut10832, _mut10833)), (double) (AOR_minus(numPairs, tiedYPairs, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10834, _mut10835, _mut10836, _mut10837)), "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10838, _mut10839, _mut10840, _mut10841);
        return AOR_divide(concordantMinusDiscordant, FastMath.sqrt(nonTiedPairsMultiplied), "org.apache.commons.math3.stat.correlation.KendallsCorrelation.compare_172", _mut10842, _mut10843, _mut10844, _mut10845);
    }

    /**
     * Returns the sum of the number from 1 .. n according to Gauss' summation formula:
     * \[ \sum\limits_{k=1}^n k = \frac{n(n + 1)}{2} \]
     *
     * @param n the summation end
     * @return the sum of the number from 1 to n
     */
    private static long sum(long n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum_269");
        return AOR_divide(AOR_multiply(n, (AOR_plus(n, 1, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum_269", _mut10846, _mut10847, _mut10848, _mut10849)), "org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum_269", _mut10850, _mut10851, _mut10852, _mut10853), 2l, "org.apache.commons.math3.stat.correlation.KendallsCorrelation.sum_269", _mut10854, _mut10855, _mut10856, _mut10857);
    }
}
