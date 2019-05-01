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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Generates a {@link BicubicInterpolatingFunction bicubic interpolating
 * function}.
 * <p>
 *  Caveat: Because the interpolation scheme requires that derivatives be
 *  specified at the sample points, those are approximated with finite
 *  differences (using the 2-points symmetric formulae).
 *  Since their values are undefined at the borders of the provided
 *  interpolation ranges, the interpolated values will be wrong at the
 *  edges of the patch.
 *  The {@code interpolate} method will return a function that overrides
 *  {@link BicubicInterpolatingFunction#isValidPoint(double,double)} to
 *  indicate points where the interpolation will be inaccurate.
 * </p>
 *
 * @since 3.4
 */
public class BicubicInterpolator implements BivariateGridInterpolator {

    @Conditional
    public static boolean _mut93703 = false, _mut93704 = false, _mut93705 = false, _mut93706 = false, _mut93707 = false, _mut93708 = false, _mut93709 = false, _mut93710 = false, _mut93711 = false, _mut93712 = false, _mut93713 = false, _mut93714 = false, _mut93715 = false, _mut93716 = false, _mut93717 = false, _mut93718 = false, _mut93719 = false, _mut93720 = false, _mut93721 = false, _mut93722 = false, _mut93723 = false, _mut93724 = false, _mut93725 = false, _mut93726 = false, _mut93727 = false, _mut93728 = false, _mut93729 = false, _mut93730 = false, _mut93731 = false, _mut93732 = false, _mut93733 = false, _mut93734 = false, _mut93735 = false, _mut93736 = false, _mut93737 = false, _mut93738 = false, _mut93739 = false, _mut93740 = false, _mut93741 = false, _mut93742 = false, _mut93743 = false, _mut93744 = false, _mut93745 = false, _mut93746 = false, _mut93747 = false, _mut93748 = false, _mut93749 = false, _mut93750 = false, _mut93751 = false, _mut93752 = false, _mut93753 = false, _mut93754 = false, _mut93755 = false, _mut93756 = false, _mut93757 = false, _mut93758 = false, _mut93759 = false, _mut93760 = false, _mut93761 = false, _mut93762 = false, _mut93763 = false, _mut93764 = false, _mut93765 = false, _mut93766 = false, _mut93767 = false, _mut93768 = false, _mut93769 = false, _mut93770 = false, _mut93771 = false, _mut93772 = false, _mut93773 = false, _mut93774 = false, _mut93775 = false, _mut93776 = false, _mut93777 = false, _mut93778 = false, _mut93779 = false, _mut93780 = false, _mut93781 = false, _mut93782 = false, _mut93783 = false, _mut93784 = false, _mut93785 = false, _mut93786 = false, _mut93787 = false, _mut93788 = false, _mut93789 = false, _mut93790 = false, _mut93791 = false, _mut93792 = false, _mut93793 = false, _mut93794 = false, _mut93795 = false, _mut93796 = false, _mut93797 = false, _mut93798 = false, _mut93799 = false, _mut93800 = false, _mut93801 = false, _mut93802 = false, _mut93803 = false, _mut93804 = false, _mut93805 = false, _mut93806 = false, _mut93807 = false, _mut93808 = false, _mut93809 = false, _mut93810 = false, _mut93811 = false, _mut93812 = false, _mut93813 = false, _mut93814 = false, _mut93815 = false, _mut93816 = false, _mut93817 = false, _mut93818 = false, _mut93819 = false, _mut93820 = false, _mut93821 = false, _mut93822 = false, _mut93823 = false, _mut93824 = false, _mut93825 = false, _mut93826 = false, _mut93827 = false, _mut93828 = false, _mut93829 = false, _mut93830 = false, _mut93831 = false, _mut93832 = false, _mut93833 = false;

    /**
     * {@inheritDoc}
     */
    public BicubicInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100");
        if ((_mut93719 ? ((_mut93713 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93703, _mut93704, _mut93705, _mut93706, _mut93707) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93708, _mut93709, _mut93710, _mut93711, _mut93712)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93703, _mut93704, _mut93705, _mut93706, _mut93707) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93708, _mut93709, _mut93710, _mut93711, _mut93712))) && ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93714, _mut93715, _mut93716, _mut93717, _mut93718)) : ((_mut93713 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93703, _mut93704, _mut93705, _mut93706, _mut93707) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93708, _mut93709, _mut93710, _mut93711, _mut93712)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93703, _mut93704, _mut93705, _mut93706, _mut93707) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93708, _mut93709, _mut93710, _mut93711, _mut93712))) || ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93714, _mut93715, _mut93716, _mut93717, _mut93718)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, fval.length, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93720, _mut93721, _mut93722, _mut93723, _mut93724)) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        final int xLen = xval.length;
        final int yLen = yval.length;
        // Approximation to the partial derivatives using finite differences.
        final double[][] dFdX = new double[xLen][yLen];
        final double[][] dFdY = new double[xLen][yLen];
        final double[][] d2FdXdY = new double[xLen][yLen];
        for (int i = 1; ROR_less(i, AOR_minus(xLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93794, _mut93795, _mut93796, _mut93797), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93798, _mut93799, _mut93800, _mut93801, _mut93802); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47");
            final int nI = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93725, _mut93726, _mut93727, _mut93728);
            final int pI = AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93729, _mut93730, _mut93731, _mut93732);
            final double nX = xval[nI];
            final double pX = xval[pI];
            final double deltaX = AOR_minus(nX, pX, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93733, _mut93734, _mut93735, _mut93736);
            for (int j = 1; ROR_less(j, AOR_minus(yLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93785, _mut93786, _mut93787, _mut93788), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93789, _mut93790, _mut93791, _mut93792, _mut93793); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47");
                final int nJ = AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93737, _mut93738, _mut93739, _mut93740);
                final int pJ = AOR_minus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93741, _mut93742, _mut93743, _mut93744);
                final double nY = yval[nJ];
                final double pY = yval[pJ];
                final double deltaY = AOR_minus(nY, pY, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93745, _mut93746, _mut93747, _mut93748);
                dFdX[i][j] = AOR_divide((AOR_minus(fval[nI][j], fval[pI][j], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93749, _mut93750, _mut93751, _mut93752)), deltaX, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93753, _mut93754, _mut93755, _mut93756);
                dFdY[i][j] = AOR_divide((AOR_minus(fval[i][nJ], fval[i][pJ], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93757, _mut93758, _mut93759, _mut93760)), deltaY, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93761, _mut93762, _mut93763, _mut93764);
                final double deltaXY = AOR_multiply(deltaX, deltaY, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93765, _mut93766, _mut93767, _mut93768);
                d2FdXdY[i][j] = AOR_divide((AOR_plus(AOR_minus(AOR_minus(fval[nI][nJ], fval[nI][pJ], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93769, _mut93770, _mut93771, _mut93772), fval[pI][nJ], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93773, _mut93774, _mut93775, _mut93776), fval[pI][pJ], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93777, _mut93778, _mut93779, _mut93780)), deltaXY, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.interpolate_47", _mut93781, _mut93782, _mut93783, _mut93784);
            }
        }
        // Create the interpolating function.
        return new BicubicInterpolatingFunction(xval, yval, fval, dFdX, dFdY, d2FdXdY) {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean isValidPoint(double x, double y) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100");
                if ((_mut93833 ? ((_mut93823 ? ((_mut93817 ? (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) && ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816)) : (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) || ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816))) && ROR_less(y, yval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93818, _mut93819, _mut93820, _mut93821, _mut93822)) : ((_mut93817 ? (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) && ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816)) : (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) || ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816))) || ROR_less(y, yval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93818, _mut93819, _mut93820, _mut93821, _mut93822))) && ROR_greater(y, yval[AOR_minus(yval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93824, _mut93825, _mut93826, _mut93827)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93828, _mut93829, _mut93830, _mut93831, _mut93832)) : ((_mut93823 ? ((_mut93817 ? (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) && ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816)) : (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) || ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816))) && ROR_less(y, yval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93818, _mut93819, _mut93820, _mut93821, _mut93822)) : ((_mut93817 ? (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) && ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816)) : (ROR_less(x, xval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93803, _mut93804, _mut93805, _mut93806, _mut93807) || ROR_greater(x, xval[AOR_minus(xval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93808, _mut93809, _mut93810, _mut93811)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93812, _mut93813, _mut93814, _mut93815, _mut93816))) || ROR_less(y, yval[1], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93818, _mut93819, _mut93820, _mut93821, _mut93822))) || ROR_greater(y, yval[AOR_minus(yval.length, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93824, _mut93825, _mut93826, _mut93827)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.isValidPoint_100", _mut93828, _mut93829, _mut93830, _mut93831, _mut93832)))) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }
}
