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

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes a cubic spline interpolation for the data set using the Akima
 * algorithm, as originally formulated by Hiroshi Akima in his 1970 paper
 * "A New Method of Interpolation and Smooth Curve Fitting Based on Local Procedures."
 * J. ACM 17, 4 (October 1970), 589-602. DOI=10.1145/321607.321609
 * http://doi.acm.org/10.1145/321607.321609
 * <p>
 * This implementation is based on the Akima implementation in the CubicSpline
 * class in the Math.NET Numerics library. The method referenced is
 * CubicSpline.InterpolateAkimaSorted
 * </p>
 * <p>
 * The {@link #interpolate(double[], double[]) interpolate} method returns a
 * {@link PolynomialSplineFunction} consisting of n cubic polynomials, defined
 * over the subintervals determined by the x values, {@code x[0] < x[i] ... < x[n]}.
 * The Akima algorithm requires that {@code n >= 5}.
 * </p>
 */
public class AkimaSplineInterpolator implements UnivariateInterpolator {

    @Conditional
    public static boolean _mut95447 = false, _mut95448 = false, _mut95449 = false, _mut95450 = false, _mut95451 = false, _mut95452 = false, _mut95453 = false, _mut95454 = false, _mut95455 = false, _mut95456 = false, _mut95457 = false, _mut95458 = false, _mut95459 = false, _mut95460 = false, _mut95461 = false, _mut95462 = false, _mut95463 = false, _mut95464 = false, _mut95465 = false, _mut95466 = false, _mut95467 = false, _mut95468 = false, _mut95469 = false, _mut95470 = false, _mut95471 = false, _mut95472 = false, _mut95473 = false, _mut95474 = false, _mut95475 = false, _mut95476 = false, _mut95477 = false, _mut95478 = false, _mut95479 = false, _mut95480 = false, _mut95481 = false, _mut95482 = false, _mut95483 = false, _mut95484 = false, _mut95485 = false, _mut95486 = false, _mut95487 = false, _mut95488 = false, _mut95489 = false, _mut95490 = false, _mut95491 = false, _mut95492 = false, _mut95493 = false, _mut95494 = false, _mut95495 = false, _mut95496 = false, _mut95497 = false, _mut95498 = false, _mut95499 = false, _mut95500 = false, _mut95501 = false, _mut95502 = false, _mut95503 = false, _mut95504 = false, _mut95505 = false, _mut95506 = false, _mut95507 = false, _mut95508 = false, _mut95509 = false, _mut95510 = false, _mut95511 = false, _mut95512 = false, _mut95513 = false, _mut95514 = false, _mut95515 = false, _mut95516 = false, _mut95517 = false, _mut95518 = false, _mut95519 = false, _mut95520 = false, _mut95521 = false, _mut95522 = false, _mut95523 = false, _mut95524 = false, _mut95525 = false, _mut95526 = false, _mut95527 = false, _mut95528 = false, _mut95529 = false, _mut95530 = false, _mut95531 = false, _mut95532 = false, _mut95533 = false, _mut95534 = false, _mut95535 = false, _mut95536 = false, _mut95537 = false, _mut95538 = false, _mut95539 = false, _mut95540 = false, _mut95541 = false, _mut95542 = false, _mut95543 = false, _mut95544 = false, _mut95545 = false, _mut95546 = false, _mut95547 = false, _mut95548 = false, _mut95549 = false, _mut95550 = false, _mut95551 = false, _mut95552 = false, _mut95553 = false, _mut95554 = false, _mut95555 = false, _mut95556 = false, _mut95557 = false, _mut95558 = false, _mut95559 = false, _mut95560 = false, _mut95561 = false, _mut95562 = false, _mut95563 = false, _mut95564 = false, _mut95565 = false, _mut95566 = false, _mut95567 = false, _mut95568 = false, _mut95569 = false, _mut95570 = false, _mut95571 = false, _mut95572 = false, _mut95573 = false, _mut95574 = false, _mut95575 = false, _mut95576 = false, _mut95577 = false, _mut95578 = false, _mut95579 = false, _mut95580 = false, _mut95581 = false, _mut95582 = false, _mut95583 = false, _mut95584 = false, _mut95585 = false, _mut95586 = false, _mut95587 = false, _mut95588 = false, _mut95589 = false, _mut95590 = false, _mut95591 = false, _mut95592 = false, _mut95593 = false, _mut95594 = false, _mut95595 = false, _mut95596 = false, _mut95597 = false, _mut95598 = false, _mut95599 = false, _mut95600 = false, _mut95601 = false, _mut95602 = false, _mut95603 = false, _mut95604 = false, _mut95605 = false, _mut95606 = false, _mut95607 = false, _mut95608 = false, _mut95609 = false, _mut95610 = false, _mut95611 = false, _mut95612 = false, _mut95613 = false, _mut95614 = false, _mut95615 = false, _mut95616 = false, _mut95617 = false, _mut95618 = false, _mut95619 = false, _mut95620 = false, _mut95621 = false, _mut95622 = false, _mut95623 = false, _mut95624 = false, _mut95625 = false, _mut95626 = false, _mut95627 = false, _mut95628 = false, _mut95629 = false, _mut95630 = false, _mut95631 = false, _mut95632 = false, _mut95633 = false, _mut95634 = false, _mut95635 = false, _mut95636 = false, _mut95637 = false, _mut95638 = false, _mut95639 = false, _mut95640 = false, _mut95641 = false, _mut95642 = false, _mut95643 = false, _mut95644 = false, _mut95645 = false, _mut95646 = false, _mut95647 = false, _mut95648 = false, _mut95649 = false, _mut95650 = false, _mut95651 = false, _mut95652 = false, _mut95653 = false, _mut95654 = false, _mut95655 = false, _mut95656 = false, _mut95657 = false, _mut95658 = false, _mut95659 = false, _mut95660 = false, _mut95661 = false, _mut95662 = false, _mut95663 = false, _mut95664 = false, _mut95665 = false, _mut95666 = false, _mut95667 = false, _mut95668 = false, _mut95669 = false, _mut95670 = false, _mut95671 = false, _mut95672 = false, _mut95673 = false, _mut95674 = false, _mut95675 = false, _mut95676 = false, _mut95677 = false, _mut95678 = false, _mut95679 = false, _mut95680 = false, _mut95681 = false, _mut95682 = false, _mut95683 = false, _mut95684 = false, _mut95685 = false, _mut95686 = false, _mut95687 = false, _mut95688 = false, _mut95689 = false, _mut95690 = false, _mut95691 = false, _mut95692 = false, _mut95693 = false, _mut95694 = false, _mut95695 = false, _mut95696 = false, _mut95697 = false, _mut95698 = false, _mut95699 = false, _mut95700 = false, _mut95701 = false, _mut95702 = false, _mut95703 = false, _mut95704 = false, _mut95705 = false, _mut95706 = false, _mut95707 = false, _mut95708 = false, _mut95709 = false, _mut95710 = false, _mut95711 = false, _mut95712 = false, _mut95713 = false, _mut95714 = false, _mut95715 = false, _mut95716 = false, _mut95717 = false, _mut95718 = false, _mut95719 = false, _mut95720 = false, _mut95721 = false, _mut95722 = false, _mut95723 = false, _mut95724 = false, _mut95725 = false, _mut95726 = false, _mut95727 = false, _mut95728 = false, _mut95729 = false, _mut95730 = false, _mut95731 = false, _mut95732 = false, _mut95733 = false, _mut95734 = false, _mut95735 = false, _mut95736 = false, _mut95737 = false, _mut95738 = false, _mut95739 = false, _mut95740 = false, _mut95741 = false, _mut95742 = false, _mut95743 = false, _mut95744 = false, _mut95745 = false, _mut95746 = false, _mut95747 = false, _mut95748 = false, _mut95749 = false, _mut95750 = false, _mut95751 = false, _mut95752 = false, _mut95753 = false, _mut95754 = false, _mut95755 = false, _mut95756 = false, _mut95757 = false, _mut95758 = false, _mut95759 = false, _mut95760 = false, _mut95761 = false, _mut95762 = false, _mut95763 = false, _mut95764 = false, _mut95765 = false, _mut95766 = false, _mut95767 = false, _mut95768 = false, _mut95769 = false, _mut95770 = false, _mut95771 = false, _mut95772 = false, _mut95773 = false, _mut95774 = false, _mut95775 = false, _mut95776 = false, _mut95777 = false, _mut95778 = false, _mut95779 = false, _mut95780 = false, _mut95781 = false, _mut95782 = false, _mut95783 = false, _mut95784 = false, _mut95785 = false, _mut95786 = false, _mut95787 = false, _mut95788 = false, _mut95789 = false, _mut95790 = false, _mut95791 = false, _mut95792 = false, _mut95793 = false, _mut95794 = false, _mut95795 = false, _mut95796 = false, _mut95797 = false;

    /**
     * The minimum number of points that are needed to compute the function.
     */
    private static final int MINIMUM_NUMBER_POINTS = 5;

    /**
     * Computes an interpolating function for the data set.
     *
     * @param xvals the arguments for the interpolation points
     * @param yvals the values for the interpolation points
     * @return a function which interpolates the data set
     * @throws DimensionMismatchException if {@code xvals} and {@code yvals} have
     *         different sizes.
     * @throws NonMonotonicSequenceException if {@code xvals} is not sorted in
     *         strict increasing order.
     * @throws NumberIsTooSmallException if the size of {@code xvals} is smaller
     *         than 5.
     */
    public PolynomialSplineFunction interpolate(double[] xvals, double[] yvals) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66");
        if ((_mut95447 ? (xvals == null && yvals == null) : (xvals == null || yvals == null))) {
            throw new NullArgumentException();
        }
        if (ROR_not_equals(xvals.length, yvals.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95448, _mut95449, _mut95450, _mut95451, _mut95452)) {
            throw new DimensionMismatchException(xvals.length, yvals.length);
        }
        if (ROR_less(xvals.length, MINIMUM_NUMBER_POINTS, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95453, _mut95454, _mut95455, _mut95456, _mut95457)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, xvals.length, MINIMUM_NUMBER_POINTS, true);
        }
        MathArrays.checkOrder(xvals);
        final int numberOfDiffAndWeightElements = AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95458, _mut95459, _mut95460, _mut95461);
        final double[] differences = new double[numberOfDiffAndWeightElements];
        final double[] weights = new double[numberOfDiffAndWeightElements];
        for (int i = 0; ROR_less(i, differences.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95482, _mut95483, _mut95484, _mut95485, _mut95486); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66");
            differences[i] = AOR_divide((AOR_minus(yvals[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95462, _mut95463, _mut95464, _mut95465)], yvals[i], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95466, _mut95467, _mut95468, _mut95469)), (AOR_minus(xvals[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95470, _mut95471, _mut95472, _mut95473)], xvals[i], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95474, _mut95475, _mut95476, _mut95477)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95478, _mut95479, _mut95480, _mut95481);
        }
        for (int i = 1; ROR_less(i, weights.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95495, _mut95496, _mut95497, _mut95498, _mut95499); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66");
            weights[i] = FastMath.abs(AOR_minus(differences[i], differences[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95487, _mut95488, _mut95489, _mut95490)], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95491, _mut95492, _mut95493, _mut95494));
        }
        // Prepare Hermite interpolation scheme.
        final double[] firstDerivatives = new double[xvals.length];
        for (int i = 2; ROR_less(i, AOR_minus(firstDerivatives.length, 2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95573, _mut95574, _mut95575, _mut95576), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95577, _mut95578, _mut95579, _mut95580, _mut95581); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66");
            final double wP = weights[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95500, _mut95501, _mut95502, _mut95503)];
            final double wM = weights[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95504, _mut95505, _mut95506, _mut95507)];
            if ((_mut95508 ? (Precision.equals(wP, 0.0) || Precision.equals(wM, 0.0)) : (Precision.equals(wP, 0.0) && Precision.equals(wM, 0.0)))) {
                final double xv = xvals[i];
                final double xvP = xvals[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95533, _mut95534, _mut95535, _mut95536)];
                final double xvM = xvals[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95537, _mut95538, _mut95539, _mut95540)];
                firstDerivatives[i] = AOR_divide((AOR_plus((AOR_multiply((AOR_minus(xvP, xv, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95541, _mut95542, _mut95543, _mut95544)), differences[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95545, _mut95546, _mut95547, _mut95548)], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95549, _mut95550, _mut95551, _mut95552)), (AOR_multiply((AOR_minus(xv, xvM, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95553, _mut95554, _mut95555, _mut95556)), differences[i], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95557, _mut95558, _mut95559, _mut95560)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95561, _mut95562, _mut95563, _mut95564)), (AOR_minus(xvP, xvM, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95565, _mut95566, _mut95567, _mut95568)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95569, _mut95570, _mut95571, _mut95572);
            } else {
                firstDerivatives[i] = AOR_divide((AOR_plus((AOR_multiply(wP, differences[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95509, _mut95510, _mut95511, _mut95512)], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95513, _mut95514, _mut95515, _mut95516)), (AOR_multiply(wM, differences[i], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95517, _mut95518, _mut95519, _mut95520)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95521, _mut95522, _mut95523, _mut95524)), (AOR_plus(wP, wM, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95525, _mut95526, _mut95527, _mut95528)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95529, _mut95530, _mut95531, _mut95532);
            }
        }
        firstDerivatives[0] = differentiateThreePoint(xvals, yvals, 0, 0, 1, 2);
        firstDerivatives[1] = differentiateThreePoint(xvals, yvals, 1, 0, 1, 2);
        firstDerivatives[AOR_minus(xvals.length, 2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95582, _mut95583, _mut95584, _mut95585)] = differentiateThreePoint(xvals, yvals, AOR_minus(xvals.length, 2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95586, _mut95587, _mut95588, _mut95589), AOR_minus(xvals.length, 3, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95590, _mut95591, _mut95592, _mut95593), AOR_minus(xvals.length, 2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95594, _mut95595, _mut95596, _mut95597), AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95598, _mut95599, _mut95600, _mut95601));
        firstDerivatives[AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95602, _mut95603, _mut95604, _mut95605)] = differentiateThreePoint(xvals, yvals, AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95606, _mut95607, _mut95608, _mut95609), AOR_minus(xvals.length, 3, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95610, _mut95611, _mut95612, _mut95613), AOR_minus(xvals.length, 2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95614, _mut95615, _mut95616, _mut95617), AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolate_66", _mut95618, _mut95619, _mut95620, _mut95621));
        return interpolateHermiteSorted(xvals, yvals, firstDerivatives);
    }

    /**
     * Three point differentiation helper, modeled off of the same method in the
     * Math.NET CubicSpline class. This is used by both the Apache Math and the
     * Math.NET Akima Cubic Spline algorithms
     *
     * @param xvals x values to calculate the numerical derivative with
     * @param yvals y values to calculate the numerical derivative with
     * @param indexOfDifferentiation index of the elemnt we are calculating the derivative around
     * @param indexOfFirstSample index of the first element to sample for the three point method
     * @param indexOfSecondsample index of the second element to sample for the three point method
     * @param indexOfThirdSample index of the third element to sample for the three point method
     * @return the derivative
     */
    private double differentiateThreePoint(double[] xvals, double[] yvals, int indexOfDifferentiation, int indexOfFirstSample, int indexOfSecondsample, int indexOfThirdSample) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143");
        final double x0 = yvals[indexOfFirstSample];
        final double x1 = yvals[indexOfSecondsample];
        final double x2 = yvals[indexOfThirdSample];
        final double t = AOR_minus(xvals[indexOfDifferentiation], xvals[indexOfFirstSample], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95622, _mut95623, _mut95624, _mut95625);
        final double t1 = AOR_minus(xvals[indexOfSecondsample], xvals[indexOfFirstSample], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95626, _mut95627, _mut95628, _mut95629);
        final double t2 = AOR_minus(xvals[indexOfThirdSample], xvals[indexOfFirstSample], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95630, _mut95631, _mut95632, _mut95633);
        final double a = AOR_divide((AOR_minus(AOR_minus(x2, x0, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95634, _mut95635, _mut95636, _mut95637), (AOR_multiply(AOR_divide(t2, t1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95638, _mut95639, _mut95640, _mut95641), (AOR_minus(x1, x0, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95642, _mut95643, _mut95644, _mut95645)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95646, _mut95647, _mut95648, _mut95649)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95650, _mut95651, _mut95652, _mut95653)), (AOR_minus(AOR_multiply(t2, t2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95654, _mut95655, _mut95656, _mut95657), AOR_multiply(t1, t2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95658, _mut95659, _mut95660, _mut95661), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95662, _mut95663, _mut95664, _mut95665)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95666, _mut95667, _mut95668, _mut95669);
        final double b = AOR_divide((AOR_minus(AOR_minus(x1, x0, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95670, _mut95671, _mut95672, _mut95673), AOR_multiply(AOR_multiply(a, t1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95674, _mut95675, _mut95676, _mut95677), t1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95678, _mut95679, _mut95680, _mut95681), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95682, _mut95683, _mut95684, _mut95685)), t1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95686, _mut95687, _mut95688, _mut95689);
        return AOR_plus((AOR_multiply(AOR_multiply(2, a, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95690, _mut95691, _mut95692, _mut95693), t, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95694, _mut95695, _mut95696, _mut95697)), b, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.differentiateThreePoint_143", _mut95698, _mut95699, _mut95700, _mut95701);
    }

    /**
     * Creates a Hermite cubic spline interpolation from the set of (x,y) value
     * pairs and their derivatives. This is modeled off of the
     * InterpolateHermiteSorted method in the Math.NET CubicSpline class.
     *
     * @param xvals x values for interpolation
     * @param yvals y values for interpolation
     * @param firstDerivatives first derivative values of the function
     * @return polynomial that fits the function
     */
    private PolynomialSplineFunction interpolateHermiteSorted(double[] xvals, double[] yvals, double[] firstDerivatives) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172");
        if (ROR_not_equals(xvals.length, yvals.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95702, _mut95703, _mut95704, _mut95705, _mut95706)) {
            throw new DimensionMismatchException(xvals.length, yvals.length);
        }
        if (ROR_not_equals(xvals.length, firstDerivatives.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95707, _mut95708, _mut95709, _mut95710, _mut95711)) {
            throw new DimensionMismatchException(xvals.length, firstDerivatives.length);
        }
        final int minimumLength = 2;
        if (ROR_less(xvals.length, minimumLength, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95712, _mut95713, _mut95714, _mut95715, _mut95716)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, xvals.length, minimumLength, true);
        }
        final int size = AOR_minus(xvals.length, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95717, _mut95718, _mut95719, _mut95720);
        final PolynomialFunction[] polynomials = new PolynomialFunction[size];
        final double[] coefficients = new double[4];
        for (int i = 0; ROR_less(i, polynomials.length, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95793, _mut95794, _mut95795, _mut95796, _mut95797); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172");
            final double w = AOR_minus(xvals[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95721, _mut95722, _mut95723, _mut95724)], xvals[i], "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95725, _mut95726, _mut95727, _mut95728);
            final double w2 = AOR_multiply(w, w, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95729, _mut95730, _mut95731, _mut95732);
            final double yv = yvals[i];
            final double yvP = yvals[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95733, _mut95734, _mut95735, _mut95736)];
            final double fd = firstDerivatives[i];
            final double fdP = firstDerivatives[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95737, _mut95738, _mut95739, _mut95740)];
            coefficients[0] = yv;
            coefficients[1] = firstDerivatives[i];
            coefficients[2] = AOR_divide((AOR_minus(AOR_minus(AOR_divide(AOR_multiply(3, (AOR_minus(yvP, yv, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95741, _mut95742, _mut95743, _mut95744)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95745, _mut95746, _mut95747, _mut95748), w, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95749, _mut95750, _mut95751, _mut95752), AOR_multiply(2, fd, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95753, _mut95754, _mut95755, _mut95756), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95757, _mut95758, _mut95759, _mut95760), fdP, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95761, _mut95762, _mut95763, _mut95764)), w, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95765, _mut95766, _mut95767, _mut95768);
            coefficients[3] = AOR_divide((AOR_plus(AOR_plus(AOR_divide(AOR_multiply(2, (AOR_minus(yv, yvP, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95769, _mut95770, _mut95771, _mut95772)), "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95773, _mut95774, _mut95775, _mut95776), w, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95777, _mut95778, _mut95779, _mut95780), fd, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95781, _mut95782, _mut95783, _mut95784), fdP, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95785, _mut95786, _mut95787, _mut95788)), w2, "org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator.interpolateHermiteSorted_172", _mut95789, _mut95790, _mut95791, _mut95792);
            polynomials[i] = new PolynomialFunction(coefficients);
        }
        return new PolynomialSplineFunction(xvals, polynomials);
    }
}
