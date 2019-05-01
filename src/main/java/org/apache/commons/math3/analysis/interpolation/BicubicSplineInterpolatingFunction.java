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

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Function that implements the
 * <a href="http://en.wikipedia.org/wiki/Bicubic_interpolation">
 * bicubic spline interpolation</a>. Due to numerical accuracy issues this should not
 * be used.
 *
 * @since 2.1
 * @deprecated as of 3.4 replaced by
 * {@link org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction}
 */
@Deprecated
public class BicubicSplineInterpolatingFunction implements BivariateFunction {

    @Conditional
    public static boolean _mut94574 = false, _mut94575 = false, _mut94576 = false, _mut94577 = false, _mut94578 = false, _mut94579 = false, _mut94580 = false, _mut94581 = false, _mut94582 = false, _mut94583 = false, _mut94584 = false, _mut94585 = false, _mut94586 = false, _mut94587 = false, _mut94588 = false, _mut94589 = false, _mut94590 = false, _mut94591 = false, _mut94592 = false, _mut94593 = false, _mut94594 = false, _mut94595 = false, _mut94596 = false, _mut94597 = false, _mut94598 = false, _mut94599 = false, _mut94600 = false, _mut94601 = false, _mut94602 = false, _mut94603 = false, _mut94604 = false, _mut94605 = false, _mut94606 = false, _mut94607 = false, _mut94608 = false, _mut94609 = false, _mut94610 = false, _mut94611 = false, _mut94612 = false, _mut94613 = false, _mut94614 = false, _mut94615 = false, _mut94616 = false, _mut94617 = false, _mut94618 = false, _mut94619 = false, _mut94620 = false, _mut94621 = false, _mut94622 = false, _mut94623 = false, _mut94624 = false, _mut94625 = false, _mut94626 = false, _mut94627 = false, _mut94628 = false, _mut94629 = false, _mut94630 = false, _mut94631 = false, _mut94632 = false, _mut94633 = false, _mut94634 = false, _mut94635 = false, _mut94636 = false, _mut94637 = false, _mut94638 = false, _mut94639 = false, _mut94640 = false, _mut94641 = false, _mut94642 = false, _mut94643 = false, _mut94644 = false, _mut94645 = false, _mut94646 = false, _mut94647 = false, _mut94648 = false, _mut94649 = false, _mut94650 = false, _mut94651 = false, _mut94652 = false, _mut94653 = false, _mut94654 = false, _mut94655 = false, _mut94656 = false, _mut94657 = false, _mut94658 = false, _mut94659 = false, _mut94660 = false, _mut94661 = false, _mut94662 = false, _mut94663 = false, _mut94664 = false, _mut94665 = false, _mut94666 = false, _mut94667 = false, _mut94668 = false, _mut94669 = false, _mut94670 = false, _mut94671 = false, _mut94672 = false, _mut94673 = false, _mut94674 = false, _mut94675 = false, _mut94676 = false, _mut94677 = false, _mut94678 = false, _mut94679 = false, _mut94680 = false, _mut94681 = false, _mut94682 = false, _mut94683 = false, _mut94684 = false, _mut94685 = false, _mut94686 = false, _mut94687 = false, _mut94688 = false, _mut94689 = false, _mut94690 = false, _mut94691 = false, _mut94692 = false, _mut94693 = false, _mut94694 = false, _mut94695 = false, _mut94696 = false, _mut94697 = false, _mut94698 = false, _mut94699 = false, _mut94700 = false, _mut94701 = false, _mut94702 = false, _mut94703 = false, _mut94704 = false, _mut94705 = false, _mut94706 = false, _mut94707 = false, _mut94708 = false, _mut94709 = false, _mut94710 = false, _mut94711 = false, _mut94712 = false, _mut94713 = false, _mut94714 = false, _mut94715 = false, _mut94716 = false, _mut94717 = false, _mut94718 = false, _mut94719 = false, _mut94720 = false, _mut94721 = false, _mut94722 = false, _mut94723 = false, _mut94724 = false, _mut94725 = false, _mut94726 = false, _mut94727 = false, _mut94728 = false, _mut94729 = false, _mut94730 = false, _mut94731 = false, _mut94732 = false, _mut94733 = false, _mut94734 = false, _mut94735 = false, _mut94736 = false, _mut94737 = false, _mut94738 = false, _mut94739 = false, _mut94740 = false, _mut94741 = false, _mut94742 = false, _mut94743 = false, _mut94744 = false, _mut94745 = false, _mut94746 = false, _mut94747 = false, _mut94748 = false, _mut94749 = false, _mut94750 = false, _mut94751 = false, _mut94752 = false, _mut94753 = false, _mut94754 = false, _mut94755 = false, _mut94756 = false, _mut94757 = false, _mut94758 = false, _mut94759 = false, _mut94760 = false, _mut94761 = false, _mut94762 = false, _mut94763 = false, _mut94764 = false, _mut94765 = false, _mut94766 = false, _mut94767 = false, _mut94768 = false, _mut94769 = false, _mut94770 = false, _mut94771 = false, _mut94772 = false, _mut94773 = false, _mut94774 = false, _mut94775 = false, _mut94776 = false, _mut94777 = false, _mut94778 = false, _mut94779 = false, _mut94780 = false, _mut94781 = false, _mut94782 = false, _mut94783 = false, _mut94784 = false, _mut94785 = false, _mut94786 = false, _mut94787 = false, _mut94788 = false, _mut94789 = false, _mut94790 = false, _mut94791 = false, _mut94792 = false, _mut94793 = false, _mut94794 = false, _mut94795 = false, _mut94796 = false, _mut94797 = false, _mut94798 = false, _mut94799 = false, _mut94800 = false, _mut94801 = false, _mut94802 = false, _mut94803 = false, _mut94804 = false, _mut94805 = false, _mut94806 = false, _mut94807 = false, _mut94808 = false, _mut94809 = false, _mut94810 = false, _mut94811 = false, _mut94812 = false, _mut94813 = false, _mut94814 = false, _mut94815 = false, _mut94816 = false, _mut94817 = false, _mut94818 = false, _mut94819 = false, _mut94820 = false, _mut94821 = false, _mut94822 = false;

    /**
     * Number of coefficients.
     */
    private static final int NUM_COEFF = 16;

    /**
     * Matrix to compute the spline coefficients from the function values
     * and function derivatives values
     */
    private static final double[][] AINV = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -3, 3, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, -2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, -2, -1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 1, 1, 0, 0 }, { -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0 }, { 9, -9, -9, 9, 6, 3, -6, -3, 6, -6, 3, -3, 4, 2, 2, 1 }, { -6, 6, 6, -6, -3, -3, 3, 3, -4, 4, -2, 2, -2, -2, -1, -1 }, { 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0 }, { -6, 6, 6, -6, -4, -2, 4, 2, -3, 3, -3, 3, -2, -1, -2, -1 }, { 4, -4, -4, 4, 2, 2, -2, -2, 2, -2, 2, -2, 1, 1, 1, 1 } };

    /**
     * Samples x-coordinates
     */
    private final double[] xval;

    /**
     * Samples y-coordinates
     */
    private final double[] yval;

    /**
     * Set of cubic splines patching the whole data grid
     */
    private final BicubicSplineFunction[][] splines;

    /**
     * Partial derivatives.
     * The value of the first index determines the kind of derivatives:
     * 0 = first partial derivatives wrt x
     * 1 = first partial derivatives wrt y
     * 2 = second partial derivatives wrt x
     * 3 = second partial derivatives wrt y
     * 4 = cross partial derivatives
     */
    private final BivariateFunction[][][] partialDerivatives;

    /**
     * @param x Sample values of the x-coordinate, in increasing order.
     * @param y Sample values of the y-coordinate, in increasing order.
     * @param f Values of the function on every grid point.
     * @param dFdX Values of the partial derivative of function with respect
     * to x on every grid point.
     * @param dFdY Values of the partial derivative of function with respect
     * to y on every grid point.
     * @param d2FdXdY Values of the cross partial derivative of function on
     * every grid point.
     * @throws DimensionMismatchException if the various arrays do not contain
     * the expected number of elements.
     * @throws NonMonotonicSequenceException if {@code x} or {@code y} are
     * not strictly increasing.
     * @throws NoDataException if any of the arrays has zero length.
     */
    public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        this(x, y, f, dFdX, dFdY, d2FdXdY, false);
    }

    /**
     * @param x Sample values of the x-coordinate, in increasing order.
     * @param y Sample values of the y-coordinate, in increasing order.
     * @param f Values of the function on every grid point.
     * @param dFdX Values of the partial derivative of function with respect
     * to x on every grid point.
     * @param dFdY Values of the partial derivative of function with respect
     * to y on every grid point.
     * @param d2FdXdY Values of the cross partial derivative of function on
     * every grid point.
     * @param initializeDerivatives Whether to initialize the internal data
     * needed for calling any of the methods that compute the partial derivatives
     * this function.
     * @throws DimensionMismatchException if the various arrays do not contain
     * the expected number of elements.
     * @throws NonMonotonicSequenceException if {@code x} or {@code y} are
     * not strictly increasing.
     * @throws NoDataException if any of the arrays has zero length.
     *
     * @see #partialDerivativeX(double,double)
     * @see #partialDerivativeY(double,double)
     * @see #partialDerivativeXX(double,double)
     * @see #partialDerivativeYY(double,double)
     * @see #partialDerivativeXY(double,double)
     */
    public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY, boolean initializeDerivatives) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135");
        final int xLen = x.length;
        final int yLen = y.length;
        if ((_mut94596 ? ((_mut94590 ? ((_mut94584 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94585, _mut94586, _mut94587, _mut94588, _mut94589)) : ((_mut94584 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94585, _mut94586, _mut94587, _mut94588, _mut94589))) && ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94591, _mut94592, _mut94593, _mut94594, _mut94595)) : ((_mut94590 ? ((_mut94584 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94585, _mut94586, _mut94587, _mut94588, _mut94589)) : ((_mut94584 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94574, _mut94575, _mut94576, _mut94577, _mut94578) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94579, _mut94580, _mut94581, _mut94582, _mut94583))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94585, _mut94586, _mut94587, _mut94588, _mut94589))) || ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94591, _mut94592, _mut94593, _mut94594, _mut94595)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xLen, f.length, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94597, _mut94598, _mut94599, _mut94600, _mut94601)) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (ROR_not_equals(xLen, dFdX.length, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94602, _mut94603, _mut94604, _mut94605, _mut94606)) {
            throw new DimensionMismatchException(xLen, dFdX.length);
        }
        if (ROR_not_equals(xLen, dFdY.length, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94607, _mut94608, _mut94609, _mut94610, _mut94611)) {
            throw new DimensionMismatchException(xLen, dFdY.length);
        }
        if (ROR_not_equals(xLen, d2FdXdY.length, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94612, _mut94613, _mut94614, _mut94615, _mut94616)) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        xval = x.clone();
        yval = y.clone();
        final int lastI = AOR_minus(xLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94617, _mut94618, _mut94619, _mut94620);
        final int lastJ = AOR_minus(yLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94621, _mut94622, _mut94623, _mut94624);
        splines = new BicubicSplineFunction[lastI][lastJ];
        for (int i = 0; ROR_less(i, lastI, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94658, _mut94659, _mut94660, _mut94661, _mut94662); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135");
            if (ROR_not_equals(f[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94625, _mut94626, _mut94627, _mut94628, _mut94629)) {
                throw new DimensionMismatchException(f[i].length, yLen);
            }
            if (ROR_not_equals(dFdX[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94630, _mut94631, _mut94632, _mut94633, _mut94634)) {
                throw new DimensionMismatchException(dFdX[i].length, yLen);
            }
            if (ROR_not_equals(dFdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94635, _mut94636, _mut94637, _mut94638, _mut94639)) {
                throw new DimensionMismatchException(dFdY[i].length, yLen);
            }
            if (ROR_not_equals(d2FdXdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94640, _mut94641, _mut94642, _mut94643, _mut94644)) {
                throw new DimensionMismatchException(d2FdXdY[i].length, yLen);
            }
            final int ip1 = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94645, _mut94646, _mut94647, _mut94648);
            for (int j = 0; ROR_less(j, lastJ, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94653, _mut94654, _mut94655, _mut94656, _mut94657); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135");
                final int jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94649, _mut94650, _mut94651, _mut94652);
                final double[] beta = new double[] { f[i][j], f[ip1][j], f[i][jp1], f[ip1][jp1], dFdX[i][j], dFdX[ip1][j], dFdX[i][jp1], dFdX[ip1][jp1], dFdY[i][j], dFdY[ip1][j], dFdY[i][jp1], dFdY[ip1][jp1], d2FdXdY[i][j], d2FdXdY[ip1][j], d2FdXdY[i][jp1], d2FdXdY[ip1][jp1] };
                splines[i][j] = new BicubicSplineFunction(computeSplineCoefficients(beta), initializeDerivatives);
            }
        }
        if (initializeDerivatives) {
            // Compute all partial derivatives.
            partialDerivatives = new BivariateFunction[5][lastI][lastJ];
            for (int i = 0; ROR_less(i, lastI, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94668, _mut94669, _mut94670, _mut94671, _mut94672); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135");
                for (int j = 0; ROR_less(j, lastJ, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135", _mut94663, _mut94664, _mut94665, _mut94666, _mut94667); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.BicubicSplineInterpolatingFunction_135");
                    final BicubicSplineFunction bcs = splines[i][j];
                    partialDerivatives[0][i][j] = bcs.partialDerivativeX();
                    partialDerivatives[1][i][j] = bcs.partialDerivativeY();
                    partialDerivatives[2][i][j] = bcs.partialDerivativeXX();
                    partialDerivatives[3][i][j] = bcs.partialDerivativeYY();
                    partialDerivatives[4][i][j] = bcs.partialDerivativeXY();
                }
            }
        } else {
            // Partial derivative methods cannot be used.
            partialDerivatives = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225");
        final int i = searchIndex(x, xval);
        final int j = searchIndex(y, yval);
        final double xN = AOR_divide((AOR_minus(x, xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94673, _mut94674, _mut94675, _mut94676)), (AOR_minus(xval[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94677, _mut94678, _mut94679, _mut94680)], xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94681, _mut94682, _mut94683, _mut94684)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94685, _mut94686, _mut94687, _mut94688);
        final double yN = AOR_divide((AOR_minus(y, yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94689, _mut94690, _mut94691, _mut94692)), (AOR_minus(yval[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94693, _mut94694, _mut94695, _mut94696)], yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94697, _mut94698, _mut94699, _mut94700)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.value_225", _mut94701, _mut94702, _mut94703, _mut94704);
        return splines[i][j].value(xN, yN);
    }

    /**
     * Indicates whether a point is within the interpolation range.
     *
     * @param x First coordinate.
     * @param y Second coordinate.
     * @return {@code true} if (x, y) is a valid point.
     * @since 3.3
     */
    public boolean isValidPoint(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244");
        if ((_mut94735 ? ((_mut94725 ? ((_mut94719 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94720, _mut94721, _mut94722, _mut94723, _mut94724)) : ((_mut94719 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94720, _mut94721, _mut94722, _mut94723, _mut94724))) && ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94726, _mut94727, _mut94728, _mut94729)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94730, _mut94731, _mut94732, _mut94733, _mut94734)) : ((_mut94725 ? ((_mut94719 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94720, _mut94721, _mut94722, _mut94723, _mut94724)) : ((_mut94719 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94705, _mut94706, _mut94707, _mut94708, _mut94709) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94710, _mut94711, _mut94712, _mut94713)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94714, _mut94715, _mut94716, _mut94717, _mut94718))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94720, _mut94721, _mut94722, _mut94723, _mut94724))) || ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94726, _mut94727, _mut94728, _mut94729)], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.isValidPoint_244", _mut94730, _mut94731, _mut94732, _mut94733, _mut94734)))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the first partial derivative with
     * respect to x.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    public double partialDerivativeX(double x, double y) throws OutOfRangeException {
        return partialDerivative(0, x, y);
    }

    /**
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the first partial derivative with
     * respect to y.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    public double partialDerivativeY(double x, double y) throws OutOfRangeException {
        return partialDerivative(1, x, y);
    }

    /**
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the second partial derivative with
     * respect to x.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    public double partialDerivativeXX(double x, double y) throws OutOfRangeException {
        return partialDerivative(2, x, y);
    }

    /**
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the second partial derivative with
     * respect to y.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    public double partialDerivativeYY(double x, double y) throws OutOfRangeException {
        return partialDerivative(3, x, y);
    }

    /**
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the second partial cross-derivative.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    public double partialDerivativeXY(double x, double y) throws OutOfRangeException {
        return partialDerivative(4, x, y);
    }

    /**
     * @param which First index in {@link #partialDerivatives}.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return the value at point (x, y) of the selected partial derivative.
     * @throws OutOfRangeException if {@code x} (resp. {@code y}) is outside
     * the range defined by the boundary values of {@code xval} (resp.
     * {@code yval}).
     * @throws NullPointerException if the internal data were not initialized
     * (cf. {@link #BicubicSplineInterpolatingFunction(double[],double[],double[][],
     *             double[][],double[][],double[][],boolean) constructor}).
     */
    private double partialDerivative(int which, double x, double y) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347");
        final int i = searchIndex(x, xval);
        final int j = searchIndex(y, yval);
        final double xN = AOR_divide((AOR_minus(x, xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94736, _mut94737, _mut94738, _mut94739)), (AOR_minus(xval[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94740, _mut94741, _mut94742, _mut94743)], xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94744, _mut94745, _mut94746, _mut94747)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94748, _mut94749, _mut94750, _mut94751);
        final double yN = AOR_divide((AOR_minus(y, yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94752, _mut94753, _mut94754, _mut94755)), (AOR_minus(yval[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94756, _mut94757, _mut94758, _mut94759)], yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94760, _mut94761, _mut94762, _mut94763)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.partialDerivative_347", _mut94764, _mut94765, _mut94766, _mut94767);
        return partialDerivatives[which][i][j].value(xN, yN);
    }

    /**
     * @param c Coordinate.
     * @param val Coordinate samples.
     * @return the index in {@code val} corresponding to the interval
     * containing {@code c}.
     * @throws OutOfRangeException if {@code c} is out of the
     * range defined by the boundary values of {@code val}.
     */
    private int searchIndex(double c, double[] val) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366");
        final int r = Arrays.binarySearch(val, c);
        if ((_mut94782 ? (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94768, _mut94769, _mut94770, _mut94771, _mut94772) && ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94773, _mut94774, _mut94775, _mut94776), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94777, _mut94778, _mut94779, _mut94780, _mut94781)) : (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94768, _mut94769, _mut94770, _mut94771, _mut94772) || ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94773, _mut94774, _mut94775, _mut94776), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94777, _mut94778, _mut94779, _mut94780, _mut94781)))) {
            throw new OutOfRangeException(c, val[0], val[AOR_minus(val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94783, _mut94784, _mut94785, _mut94786)]);
        }
        if (ROR_less(r, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94787, _mut94788, _mut94789, _mut94790, _mut94791)) {
            // index of the sample at the lower end of the sub-interval.
            return AOR_minus(-r, 2, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94792, _mut94793, _mut94794, _mut94795);
        }
        final int last = AOR_minus(val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94796, _mut94797, _mut94798, _mut94799);
        if (ROR_equals(r, last, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94800, _mut94801, _mut94802, _mut94803, _mut94804)) {
            // of the sample at the lower end of the last sub-interval.
            return AOR_minus(last, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.searchIndex_366", _mut94805, _mut94806, _mut94807, _mut94808);
        }
        // "c" is another sample point.
        return r;
    }

    /**
     * Compute the spline coefficients from the list of function values and
     * function partial derivatives values at the four corners of a grid
     * element. They must be specified in the following order:
     * <ul>
     *  <li>f(0,0)</li>
     *  <li>f(1,0)</li>
     *  <li>f(0,1)</li>
     *  <li>f(1,1)</li>
     *  <li>f<sub>x</sub>(0,0)</li>
     *  <li>f<sub>x</sub>(1,0)</li>
     *  <li>f<sub>x</sub>(0,1)</li>
     *  <li>f<sub>x</sub>(1,1)</li>
     *  <li>f<sub>y</sub>(0,0)</li>
     *  <li>f<sub>y</sub>(1,0)</li>
     *  <li>f<sub>y</sub>(0,1)</li>
     *  <li>f<sub>y</sub>(1,1)</li>
     *  <li>f<sub>xy</sub>(0,0)</li>
     *  <li>f<sub>xy</sub>(1,0)</li>
     *  <li>f<sub>xy</sub>(0,1)</li>
     *  <li>f<sub>xy</sub>(1,1)</li>
     * </ul>
     * where the subscripts indicate the partial derivative with respect to
     * the corresponding variable(s).
     *
     * @param beta List of function values and function partial derivatives
     * values.
     * @return the spline coefficients.
     */
    private double[] computeSplineCoefficients(double[] beta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419");
        final double[] a = new double[NUM_COEFF];
        for (int i = 0; ROR_less(i, NUM_COEFF, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419", _mut94818, _mut94819, _mut94820, _mut94821, _mut94822); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419");
            double result = 0;
            final double[] row = AINV[i];
            for (int j = 0; ROR_less(j, NUM_COEFF, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419", _mut94813, _mut94814, _mut94815, _mut94816, _mut94817); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419");
                result += AOR_multiply(row[j], beta[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction.computeSplineCoefficients_419", _mut94809, _mut94810, _mut94811, _mut94812);
            }
            a[i] = result;
        }
        return a;
    }
}

/**
 * 2D-spline function.
 */
class BicubicSplineFunction implements BivariateFunction {

    @Conditional
    public static boolean _mut94823 = false, _mut94824 = false, _mut94825 = false, _mut94826 = false, _mut94827 = false, _mut94828 = false, _mut94829 = false, _mut94830 = false, _mut94831 = false, _mut94832 = false, _mut94833 = false, _mut94834 = false, _mut94835 = false, _mut94836 = false, _mut94837 = false, _mut94838 = false, _mut94839 = false, _mut94840 = false, _mut94841 = false, _mut94842 = false, _mut94843 = false, _mut94844 = false, _mut94845 = false, _mut94846 = false, _mut94847 = false, _mut94848 = false, _mut94849 = false, _mut94850 = false, _mut94851 = false, _mut94852 = false, _mut94853 = false, _mut94854 = false, _mut94855 = false, _mut94856 = false, _mut94857 = false, _mut94858 = false, _mut94859 = false, _mut94860 = false, _mut94861 = false, _mut94862 = false, _mut94863 = false, _mut94864 = false, _mut94865 = false, _mut94866 = false, _mut94867 = false, _mut94868 = false, _mut94869 = false, _mut94870 = false, _mut94871 = false, _mut94872 = false, _mut94873 = false, _mut94874 = false, _mut94875 = false, _mut94876 = false, _mut94877 = false, _mut94878 = false, _mut94879 = false, _mut94880 = false, _mut94881 = false, _mut94882 = false, _mut94883 = false, _mut94884 = false, _mut94885 = false, _mut94886 = false, _mut94887 = false, _mut94888 = false, _mut94889 = false, _mut94890 = false, _mut94891 = false, _mut94892 = false, _mut94893 = false, _mut94894 = false, _mut94895 = false, _mut94896 = false, _mut94897 = false, _mut94898 = false, _mut94899 = false, _mut94900 = false, _mut94901 = false, _mut94902 = false, _mut94903 = false, _mut94904 = false, _mut94905 = false, _mut94906 = false, _mut94907 = false, _mut94908 = false, _mut94909 = false, _mut94910 = false, _mut94911 = false, _mut94912 = false, _mut94913 = false, _mut94914 = false, _mut94915 = false, _mut94916 = false, _mut94917 = false, _mut94918 = false, _mut94919 = false, _mut94920 = false, _mut94921 = false, _mut94922 = false, _mut94923 = false, _mut94924 = false, _mut94925 = false, _mut94926 = false, _mut94927 = false, _mut94928 = false, _mut94929 = false, _mut94930 = false, _mut94931 = false, _mut94932 = false, _mut94933 = false, _mut94934 = false, _mut94935 = false, _mut94936 = false, _mut94937 = false, _mut94938 = false, _mut94939 = false, _mut94940 = false, _mut94941 = false, _mut94942 = false, _mut94943 = false, _mut94944 = false, _mut94945 = false, _mut94946 = false, _mut94947 = false, _mut94948 = false, _mut94949 = false, _mut94950 = false, _mut94951 = false, _mut94952 = false, _mut94953 = false, _mut94954 = false, _mut94955 = false, _mut94956 = false, _mut94957 = false, _mut94958 = false, _mut94959 = false, _mut94960 = false, _mut94961 = false, _mut94962 = false, _mut94963 = false, _mut94964 = false, _mut94965 = false, _mut94966 = false, _mut94967 = false, _mut94968 = false, _mut94969 = false, _mut94970 = false, _mut94971 = false, _mut94972 = false, _mut94973 = false, _mut94974 = false, _mut94975 = false, _mut94976 = false, _mut94977 = false, _mut94978 = false, _mut94979 = false, _mut94980 = false, _mut94981 = false, _mut94982 = false;

    /**
     * Number of points.
     */
    private static final short N = 4;

    /**
     * Coefficients
     */
    private final double[][] a;

    /**
     * First partial derivative along x.
     */
    private final BivariateFunction partialDerivativeX;

    /**
     * First partial derivative along y.
     */
    private final BivariateFunction partialDerivativeY;

    /**
     * Second partial derivative along x.
     */
    private final BivariateFunction partialDerivativeXX;

    /**
     * Second partial derivative along y.
     */
    private final BivariateFunction partialDerivativeYY;

    /**
     * Second crossed partial derivative.
     */
    private final BivariateFunction partialDerivativeXY;

    /**
     * Simple constructor.
     *
     * @param coeff Spline coefficients.
     */
    BicubicSplineFunction(double[] coeff) {
        this(coeff, false);
    }

    /**
     * Simple constructor.
     *
     * @param coeff Spline coefficients.
     * @param initializeDerivatives Whether to initialize the internal data
     * needed for calling any of the methods that compute the partial derivatives
     * this function.
     */
    BicubicSplineFunction(double[] coeff, boolean initializeDerivatives) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_551");
        a = new double[N][N];
        for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94836, _mut94837, _mut94838, _mut94839, _mut94840); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472");
            for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94831, _mut94832, _mut94833, _mut94834, _mut94835); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472");
                a[i][j] = coeff[AOR_plus(AOR_multiply(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94823, _mut94824, _mut94825, _mut94826), j, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94827, _mut94828, _mut94829, _mut94830)];
            }
        }
        if (initializeDerivatives) {
            // Compute all partial derivatives functions.
            final double[][] aX = new double[N][N];
            final double[][] aY = new double[N][N];
            final double[][] aXX = new double[N][N];
            final double[][] aYY = new double[N][N];
            final double[][] aXY = new double[N][N];
            for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94874, _mut94875, _mut94876, _mut94877, _mut94878); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472");
                for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94869, _mut94870, _mut94871, _mut94872, _mut94873); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472");
                    final double c = a[i][j];
                    aX[i][j] = AOR_multiply(i, c, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94841, _mut94842, _mut94843, _mut94844);
                    aY[i][j] = AOR_multiply(j, c, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94845, _mut94846, _mut94847, _mut94848);
                    aXX[i][j] = AOR_multiply((AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94849, _mut94850, _mut94851, _mut94852)), aX[i][j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94853, _mut94854, _mut94855, _mut94856);
                    aYY[i][j] = AOR_multiply((AOR_minus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94857, _mut94858, _mut94859, _mut94860)), aY[i][j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94861, _mut94862, _mut94863, _mut94864);
                    aXY[i][j] = AOR_multiply(j, aX[i][j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.BicubicSplineFunction_472", _mut94865, _mut94866, _mut94867, _mut94868);
                }
            }
            partialDerivativeX = new BivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double x, double y) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_501");
                    final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_501", _mut94879, _mut94880, _mut94881, _mut94882);
                    final double[] pX = { 0, 1, x, x2 };
                    final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_501", _mut94883, _mut94884, _mut94885, _mut94886);
                    final double y3 = AOR_multiply(y2, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_501", _mut94887, _mut94888, _mut94889, _mut94890);
                    final double[] pY = { 1, y, y2, y3 };
                    return apply(pX, pY, aX);
                }
            };
            partialDerivativeY = new BivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double x, double y) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_514");
                    final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_514", _mut94891, _mut94892, _mut94893, _mut94894);
                    final double x3 = AOR_multiply(x2, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_514", _mut94895, _mut94896, _mut94897, _mut94898);
                    final double[] pX = { 1, x, x2, x3 };
                    final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_514", _mut94899, _mut94900, _mut94901, _mut94902);
                    final double[] pY = { 0, 1, y, y2 };
                    return apply(pX, pY, aY);
                }
            };
            partialDerivativeXX = new BivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double x, double y) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_527");
                    final double[] pX = { 0, 0, 1, x };
                    final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_527", _mut94903, _mut94904, _mut94905, _mut94906);
                    final double y3 = AOR_multiply(y2, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_527", _mut94907, _mut94908, _mut94909, _mut94910);
                    final double[] pY = { 1, y, y2, y3 };
                    return apply(pX, pY, aXX);
                }
            };
            partialDerivativeYY = new BivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double x, double y) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_539");
                    final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_539", _mut94911, _mut94912, _mut94913, _mut94914);
                    final double x3 = AOR_multiply(x2, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_539", _mut94915, _mut94916, _mut94917, _mut94918);
                    final double[] pX = { 1, x, x2, x3 };
                    final double[] pY = { 0, 0, 1, y };
                    return apply(pX, pY, aYY);
                }
            };
            partialDerivativeXY = new BivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double x, double y) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_551");
                    final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_551", _mut94919, _mut94920, _mut94921, _mut94922);
                    final double[] pX = { 0, 1, x, x2 };
                    final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_551", _mut94923, _mut94924, _mut94925, _mut94926);
                    final double[] pY = { 0, 1, y, y2 };
                    return apply(pX, pY, aXY);
                }
            };
        } else {
            partialDerivativeX = null;
            partialDerivativeY = null;
            partialDerivativeXX = null;
            partialDerivativeYY = null;
            partialDerivativeXY = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573");
        if ((_mut94937 ? (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94927, _mut94928, _mut94929, _mut94930, _mut94931) && ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94932, _mut94933, _mut94934, _mut94935, _mut94936)) : (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94927, _mut94928, _mut94929, _mut94930, _mut94931) || ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94932, _mut94933, _mut94934, _mut94935, _mut94936)))) {
            throw new OutOfRangeException(x, 0, 1);
        }
        if ((_mut94948 ? (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94938, _mut94939, _mut94940, _mut94941, _mut94942) && ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94943, _mut94944, _mut94945, _mut94946, _mut94947)) : (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94938, _mut94939, _mut94940, _mut94941, _mut94942) || ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94943, _mut94944, _mut94945, _mut94946, _mut94947)))) {
            throw new OutOfRangeException(y, 0, 1);
        }
        final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94949, _mut94950, _mut94951, _mut94952);
        final double x3 = AOR_multiply(x2, x, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94953, _mut94954, _mut94955, _mut94956);
        final double[] pX = { 1, x, x2, x3 };
        final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94957, _mut94958, _mut94959, _mut94960);
        final double y3 = AOR_multiply(y2, y, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.value_573", _mut94961, _mut94962, _mut94963, _mut94964);
        final double[] pY = { 1, y, y2, y3 };
        return apply(pX, pY, a);
    }

    /**
     * Compute the value of the bicubic polynomial.
     *
     * @param pX Powers of the x-coordinate.
     * @param pY Powers of the y-coordinate.
     * @param coeff Spline coefficients.
     * @return the interpolated value.
     */
    private double apply(double[] pX, double[] pY, double[][] coeff) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600");
        double result = 0;
        for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600", _mut94978, _mut94979, _mut94980, _mut94981, _mut94982); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600");
            for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600", _mut94973, _mut94974, _mut94975, _mut94976, _mut94977); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600");
                result += AOR_multiply(AOR_multiply(coeff[i][j], pX[i], "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600", _mut94965, _mut94966, _mut94967, _mut94968), pY[j], "org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.apply_600", _mut94969, _mut94970, _mut94971, _mut94972);
            }
        }
        return result;
    }

    /**
     * @return the partial derivative wrt {@code x}.
     */
    public BivariateFunction partialDerivativeX() {
        return partialDerivativeX;
    }

    /**
     * @return the partial derivative wrt {@code y}.
     */
    public BivariateFunction partialDerivativeY() {
        return partialDerivativeY;
    }

    /**
     * @return the second partial derivative wrt {@code x}.
     */
    public BivariateFunction partialDerivativeXX() {
        return partialDerivativeXX;
    }

    /**
     * @return the second partial derivative wrt {@code y}.
     */
    public BivariateFunction partialDerivativeYY() {
        return partialDerivativeYY;
    }

    /**
     * @return the second partial cross-derivative.
     */
    public BivariateFunction partialDerivativeXY() {
        return partialDerivativeXY;
    }
}
