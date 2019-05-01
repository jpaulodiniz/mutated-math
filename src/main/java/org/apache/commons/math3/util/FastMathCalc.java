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

import java.io.PrintStream;
import org.apache.commons.math3.exception.DimensionMismatchException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class used to compute the classical functions tables.
 * @since 3.0
 */
class FastMathCalc {

    @Conditional
    public static boolean _mut40414 = false, _mut40415 = false, _mut40416 = false, _mut40417 = false, _mut40418 = false, _mut40419 = false, _mut40420 = false, _mut40421 = false, _mut40422 = false, _mut40423 = false, _mut40424 = false, _mut40425 = false, _mut40426 = false, _mut40427 = false, _mut40428 = false, _mut40429 = false, _mut40430 = false, _mut40431 = false, _mut40432 = false, _mut40433 = false, _mut40434 = false, _mut40435 = false, _mut40436 = false, _mut40437 = false, _mut40438 = false, _mut40439 = false, _mut40440 = false, _mut40441 = false, _mut40442 = false, _mut40443 = false, _mut40444 = false, _mut40445 = false, _mut40446 = false, _mut40447 = false, _mut40448 = false, _mut40449 = false, _mut40450 = false, _mut40451 = false, _mut40452 = false, _mut40453 = false, _mut40454 = false, _mut40455 = false, _mut40456 = false, _mut40457 = false, _mut40458 = false, _mut40459 = false, _mut40460 = false, _mut40461 = false, _mut40462 = false, _mut40463 = false, _mut40464 = false, _mut40465 = false, _mut40466 = false, _mut40467 = false, _mut40468 = false, _mut40469 = false, _mut40470 = false, _mut40471 = false, _mut40472 = false, _mut40473 = false, _mut40474 = false, _mut40475 = false, _mut40476 = false, _mut40477 = false, _mut40478 = false, _mut40479 = false, _mut40480 = false, _mut40481 = false, _mut40482 = false, _mut40483 = false, _mut40484 = false, _mut40485 = false, _mut40486 = false, _mut40487 = false, _mut40488 = false, _mut40489 = false, _mut40490 = false, _mut40491 = false, _mut40492 = false, _mut40493 = false, _mut40494 = false, _mut40495 = false, _mut40496 = false, _mut40497 = false, _mut40498 = false, _mut40499 = false, _mut40500 = false, _mut40501 = false, _mut40502 = false, _mut40503 = false, _mut40504 = false, _mut40505 = false, _mut40506 = false, _mut40507 = false, _mut40508 = false, _mut40509 = false, _mut40510 = false, _mut40511 = false, _mut40512 = false, _mut40513 = false, _mut40514 = false, _mut40515 = false, _mut40516 = false, _mut40517 = false, _mut40518 = false, _mut40519 = false, _mut40520 = false, _mut40521 = false, _mut40522 = false, _mut40523 = false, _mut40524 = false, _mut40525 = false, _mut40526 = false, _mut40527 = false, _mut40528 = false, _mut40529 = false, _mut40530 = false, _mut40531 = false, _mut40532 = false, _mut40533 = false, _mut40534 = false, _mut40535 = false, _mut40536 = false, _mut40537 = false, _mut40538 = false, _mut40539 = false, _mut40540 = false, _mut40541 = false, _mut40542 = false, _mut40543 = false, _mut40544 = false, _mut40545 = false, _mut40546 = false, _mut40547 = false, _mut40548 = false, _mut40549 = false, _mut40550 = false, _mut40551 = false, _mut40552 = false, _mut40553 = false, _mut40554 = false, _mut40555 = false, _mut40556 = false, _mut40557 = false, _mut40558 = false, _mut40559 = false, _mut40560 = false, _mut40561 = false, _mut40562 = false, _mut40563 = false, _mut40564 = false, _mut40565 = false, _mut40566 = false, _mut40567 = false, _mut40568 = false, _mut40569 = false, _mut40570 = false, _mut40571 = false, _mut40572 = false, _mut40573 = false, _mut40574 = false, _mut40575 = false, _mut40576 = false, _mut40577 = false, _mut40578 = false, _mut40579 = false, _mut40580 = false, _mut40581 = false, _mut40582 = false, _mut40583 = false, _mut40584 = false, _mut40585 = false, _mut40586 = false, _mut40587 = false, _mut40588 = false, _mut40589 = false, _mut40590 = false, _mut40591 = false, _mut40592 = false, _mut40593 = false, _mut40594 = false, _mut40595 = false, _mut40596 = false, _mut40597 = false, _mut40598 = false, _mut40599 = false, _mut40600 = false, _mut40601 = false, _mut40602 = false, _mut40603 = false, _mut40604 = false, _mut40605 = false, _mut40606 = false, _mut40607 = false, _mut40608 = false, _mut40609 = false, _mut40610 = false, _mut40611 = false, _mut40612 = false, _mut40613 = false, _mut40614 = false, _mut40615 = false, _mut40616 = false, _mut40617 = false, _mut40618 = false, _mut40619 = false, _mut40620 = false, _mut40621 = false, _mut40622 = false, _mut40623 = false, _mut40624 = false, _mut40625 = false, _mut40626 = false, _mut40627 = false, _mut40628 = false, _mut40629 = false, _mut40630 = false, _mut40631 = false, _mut40632 = false, _mut40633 = false, _mut40634 = false, _mut40635 = false, _mut40636 = false, _mut40637 = false, _mut40638 = false, _mut40639 = false, _mut40640 = false, _mut40641 = false, _mut40642 = false, _mut40643 = false, _mut40644 = false, _mut40645 = false, _mut40646 = false, _mut40647 = false, _mut40648 = false, _mut40649 = false, _mut40650 = false, _mut40651 = false, _mut40652 = false, _mut40653 = false, _mut40654 = false, _mut40655 = false, _mut40656 = false, _mut40657 = false, _mut40658 = false, _mut40659 = false, _mut40660 = false, _mut40661 = false, _mut40662 = false, _mut40663 = false, _mut40664 = false, _mut40665 = false, _mut40666 = false, _mut40667 = false, _mut40668 = false, _mut40669 = false, _mut40670 = false, _mut40671 = false, _mut40672 = false, _mut40673 = false, _mut40674 = false, _mut40675 = false, _mut40676 = false, _mut40677 = false, _mut40678 = false, _mut40679 = false, _mut40680 = false, _mut40681 = false, _mut40682 = false, _mut40683 = false, _mut40684 = false, _mut40685 = false, _mut40686 = false, _mut40687 = false, _mut40688 = false, _mut40689 = false, _mut40690 = false, _mut40691 = false, _mut40692 = false, _mut40693 = false, _mut40694 = false, _mut40695 = false, _mut40696 = false, _mut40697 = false, _mut40698 = false, _mut40699 = false, _mut40700 = false, _mut40701 = false, _mut40702 = false, _mut40703 = false, _mut40704 = false, _mut40705 = false, _mut40706 = false, _mut40707 = false, _mut40708 = false, _mut40709 = false, _mut40710 = false, _mut40711 = false, _mut40712 = false, _mut40713 = false, _mut40714 = false, _mut40715 = false, _mut40716 = false, _mut40717 = false, _mut40718 = false, _mut40719 = false, _mut40720 = false, _mut40721 = false, _mut40722 = false, _mut40723 = false, _mut40724 = false, _mut40725 = false, _mut40726 = false, _mut40727 = false, _mut40728 = false, _mut40729 = false, _mut40730 = false, _mut40731 = false, _mut40732 = false, _mut40733 = false, _mut40734 = false, _mut40735 = false, _mut40736 = false, _mut40737 = false, _mut40738 = false, _mut40739 = false, _mut40740 = false, _mut40741 = false, _mut40742 = false, _mut40743 = false, _mut40744 = false, _mut40745 = false, _mut40746 = false, _mut40747 = false, _mut40748 = false, _mut40749 = false, _mut40750 = false, _mut40751 = false, _mut40752 = false, _mut40753 = false, _mut40754 = false, _mut40755 = false, _mut40756 = false, _mut40757 = false, _mut40758 = false, _mut40759 = false, _mut40760 = false, _mut40761 = false, _mut40762 = false, _mut40763 = false, _mut40764 = false, _mut40765 = false, _mut40766 = false, _mut40767 = false, _mut40768 = false, _mut40769 = false, _mut40770 = false, _mut40771 = false, _mut40772 = false, _mut40773 = false, _mut40774 = false, _mut40775 = false, _mut40776 = false, _mut40777 = false, _mut40778 = false, _mut40779 = false, _mut40780 = false, _mut40781 = false, _mut40782 = false, _mut40783 = false, _mut40784 = false, _mut40785 = false, _mut40786 = false, _mut40787 = false, _mut40788 = false, _mut40789 = false, _mut40790 = false, _mut40791 = false, _mut40792 = false, _mut40793 = false, _mut40794 = false, _mut40795 = false, _mut40796 = false, _mut40797 = false, _mut40798 = false, _mut40799 = false, _mut40800 = false, _mut40801 = false, _mut40802 = false, _mut40803 = false, _mut40804 = false, _mut40805 = false, _mut40806 = false, _mut40807 = false, _mut40808 = false, _mut40809 = false, _mut40810 = false, _mut40811 = false, _mut40812 = false, _mut40813 = false, _mut40814 = false, _mut40815 = false, _mut40816 = false, _mut40817 = false, _mut40818 = false, _mut40819 = false, _mut40820 = false, _mut40821 = false, _mut40822 = false, _mut40823 = false, _mut40824 = false, _mut40825 = false, _mut40826 = false, _mut40827 = false, _mut40828 = false, _mut40829 = false, _mut40830 = false, _mut40831 = false, _mut40832 = false, _mut40833 = false, _mut40834 = false, _mut40835 = false, _mut40836 = false, _mut40837 = false, _mut40838 = false, _mut40839 = false, _mut40840 = false, _mut40841 = false, _mut40842 = false, _mut40843 = false, _mut40844 = false, _mut40845 = false, _mut40846 = false, _mut40847 = false, _mut40848 = false, _mut40849 = false, _mut40850 = false, _mut40851 = false, _mut40852 = false, _mut40853 = false, _mut40854 = false, _mut40855 = false, _mut40856 = false, _mut40857 = false, _mut40858 = false, _mut40859 = false, _mut40860 = false, _mut40861 = false, _mut40862 = false, _mut40863 = false, _mut40864 = false, _mut40865 = false, _mut40866 = false, _mut40867 = false, _mut40868 = false, _mut40869 = false, _mut40870 = false, _mut40871 = false, _mut40872 = false, _mut40873 = false, _mut40874 = false, _mut40875 = false, _mut40876 = false, _mut40877 = false, _mut40878 = false, _mut40879 = false, _mut40880 = false, _mut40881 = false, _mut40882 = false, _mut40883 = false, _mut40884 = false, _mut40885 = false, _mut40886 = false, _mut40887 = false, _mut40888 = false, _mut40889 = false, _mut40890 = false, _mut40891 = false, _mut40892 = false, _mut40893 = false, _mut40894 = false, _mut40895 = false, _mut40896 = false, _mut40897 = false, _mut40898 = false, _mut40899 = false, _mut40900 = false, _mut40901 = false, _mut40902 = false, _mut40903 = false, _mut40904 = false, _mut40905 = false, _mut40906 = false, _mut40907 = false;

    // 1073741824L
    private static final long HEX_40000000 = 0x40000000L;

    /**
     * Factorial table, for Taylor series expansions. 0!, 1!, 2!, ... 19!
     */
    private static final double[] FACT = new double[] { // 0
    +1.0d, // 1
    +1.0d, // 2
    +2.0d, // 3
    +6.0d, // 4
    +24.0d, // 5
    +120.0d, // 6
    +720.0d, // 7
    +5040.0d, // 8
    +40320.0d, // 9
    +362880.0d, // 10
    +3628800.0d, // 11
    +39916800.0d, // 12
    +479001600.0d, // 13
    +6227020800.0d, // 14
    +87178291200.0d, // 15
    +1307674368000.0d, // 16
    +20922789888000.0d, // 17
    +355687428096000.0d, // 18
    +6402373705728000.0d, // 19
    +121645100408832000.0d };

    /**
     * Coefficients for slowLog.
     */
    private static final double[][] LN_SPLIT_COEF = { { 2.0, 0.0 }, { 0.6666666269302368, 3.9736429850260626E-8 }, { 0.3999999761581421, 2.3841857910019882E-8 }, { 0.2857142686843872, 1.7029898543501842E-8 }, { 0.2222222089767456, 1.3245471311735498E-8 }, { 0.1818181574344635, 2.4384203044354907E-8 }, { 0.1538461446762085, 9.140260083262505E-9 }, { 0.13333332538604736, 9.220590270857665E-9 }, { 0.11764700710773468, 1.2393345855018391E-8 }, { 0.10526403784751892, 8.251545029714408E-9 }, { 0.0952233225107193, 1.2675934823758863E-8 }, { 0.08713622391223907, 1.1430250008909141E-8 }, { 0.07842259109020233, 2.404307984052299E-9 }, { 0.08371849358081818, 1.176342548272881E-8 }, { 0.030589580535888672, 1.2958646899018938E-9 }, { 0.14982303977012634, 1.225743062930824E-8 } };

    /**
     * Table start declaration.
     */
    private static final String TABLE_START_DECL = "    {";

    /**
     * Table end declaration.
     */
    private static final String TABLE_END_DECL = "    };";

    /**
     * Private Constructor.
     */
    private FastMathCalc() {
    }

    /**
     * Build the sine and cosine tables.
     * @param SINE_TABLE_A table of the most significant part of the sines
     * @param SINE_TABLE_B table of the least significant part of the sines
     * @param COSINE_TABLE_A table of the most significant part of the cosines
     * @param COSINE_TABLE_B table of the most significant part of the cosines
     * @param SINE_TABLE_LEN length of the tables
     * @param TANGENT_TABLE_A table of the most significant part of the tangents
     * @param TANGENT_TABLE_B table of the most significant part of the tangents
     */
    @SuppressWarnings("unused")
    private static void buildSinCosTables(double[] SINE_TABLE_A, double[] SINE_TABLE_B, double[] COSINE_TABLE_A, double[] COSINE_TABLE_B, int SINE_TABLE_LEN, double[] TANGENT_TABLE_A, double[] TANGENT_TABLE_B) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100");
        final double[] result = new double[2];
        /* Use taylor series for 0 <= x <= 6/8 */
        for (int i = 0; ROR_less(i, 7, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40418, _mut40419, _mut40420, _mut40421, _mut40422); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100");
            double x = AOR_divide(i, 8.0, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40414, _mut40415, _mut40416, _mut40417);
            slowSin(x, result);
            SINE_TABLE_A[i] = result[0];
            SINE_TABLE_B[i] = result[1];
            slowCos(x, result);
            COSINE_TABLE_A[i] = result[0];
            COSINE_TABLE_B[i] = result[1];
        }
        /* Use angle addition formula to complete table to 13/8, just beyond pi/2 */
        for (int i = 7; ROR_less(i, SINE_TABLE_LEN, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40500, _mut40501, _mut40502, _mut40503, _mut40504); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100");
            double[] xs = new double[2];
            double[] ys = new double[2];
            double[] as = new double[2];
            double[] bs = new double[2];
            double[] temps = new double[2];
            if (ROR_equals((i & 1), 0, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40423, _mut40424, _mut40425, _mut40426, _mut40427)) {
                // Even, use double angle
                xs[0] = SINE_TABLE_A[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40476, _mut40477, _mut40478, _mut40479)];
                xs[1] = SINE_TABLE_B[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40480, _mut40481, _mut40482, _mut40483)];
                ys[0] = COSINE_TABLE_A[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40484, _mut40485, _mut40486, _mut40487)];
                ys[1] = COSINE_TABLE_B[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40488, _mut40489, _mut40490, _mut40491)];
                /* compute sine */
                splitMult(xs, ys, result);
                SINE_TABLE_A[i] = AOR_multiply(result[0], 2.0, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40492, _mut40493, _mut40494, _mut40495);
                SINE_TABLE_B[i] = AOR_multiply(result[1], 2.0, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40496, _mut40497, _mut40498, _mut40499);
                /* Compute cosine */
                splitMult(ys, ys, as);
                splitMult(xs, xs, temps);
                temps[0] = -temps[0];
                temps[1] = -temps[1];
                splitAdd(as, temps, result);
                COSINE_TABLE_A[i] = result[0];
                COSINE_TABLE_B[i] = result[1];
            } else {
                xs[0] = SINE_TABLE_A[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40428, _mut40429, _mut40430, _mut40431)];
                xs[1] = SINE_TABLE_B[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40432, _mut40433, _mut40434, _mut40435)];
                ys[0] = COSINE_TABLE_A[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40436, _mut40437, _mut40438, _mut40439)];
                ys[1] = COSINE_TABLE_B[AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40440, _mut40441, _mut40442, _mut40443)];
                as[0] = SINE_TABLE_A[AOR_plus(AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40444, _mut40445, _mut40446, _mut40447), 1, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40448, _mut40449, _mut40450, _mut40451)];
                as[1] = SINE_TABLE_B[AOR_plus(AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40452, _mut40453, _mut40454, _mut40455), 1, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40456, _mut40457, _mut40458, _mut40459)];
                bs[0] = COSINE_TABLE_A[AOR_plus(AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40460, _mut40461, _mut40462, _mut40463), 1, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40464, _mut40465, _mut40466, _mut40467)];
                bs[1] = COSINE_TABLE_B[AOR_plus(AOR_divide(i, 2, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40468, _mut40469, _mut40470, _mut40471), 1, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40472, _mut40473, _mut40474, _mut40475)];
                /* compute sine */
                splitMult(xs, bs, temps);
                splitMult(ys, as, result);
                splitAdd(result, temps, result);
                SINE_TABLE_A[i] = result[0];
                SINE_TABLE_B[i] = result[1];
                /* Compute cosine */
                splitMult(ys, bs, result);
                splitMult(xs, as, temps);
                temps[0] = -temps[0];
                temps[1] = -temps[1];
                splitAdd(result, temps, result);
                COSINE_TABLE_A[i] = result[0];
                COSINE_TABLE_B[i] = result[1];
            }
        }
        /* Compute tangent = sine/cosine */
        for (int i = 0; ROR_less(i, SINE_TABLE_LEN, "org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100", _mut40505, _mut40506, _mut40507, _mut40508, _mut40509); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.buildSinCosTables_100");
            double[] xs = new double[2];
            double[] ys = new double[2];
            double[] as = new double[2];
            as[0] = COSINE_TABLE_A[i];
            as[1] = COSINE_TABLE_B[i];
            splitReciprocal(as, ys);
            xs[0] = SINE_TABLE_A[i];
            xs[1] = SINE_TABLE_B[i];
            splitMult(xs, ys, as);
            TANGENT_TABLE_A[i] = as[0];
            TANGENT_TABLE_B[i] = as[1];
        }
    }

    /**
     *  For x between 0 and pi/4 compute cosine using Talor series
     *  cos(x) = 1 - x^2/2! + x^4/4! ...
     * @param x number from which cosine is requested
     * @param result placeholder where to put the result in extended precision
     * (may be null)
     * @return cos(x)
     */
    static double slowCos(final double x, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowCos_205");
        final double[] xs = new double[2];
        final double[] ys = new double[2];
        final double[] facts = new double[2];
        final double[] as = new double[2];
        split(x, xs);
        ys[0] = ys[1] = 0.0;
        for (int i = FACT.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMathCalc.slowCos_205", _mut40520, _mut40521, _mut40522, _mut40523, _mut40524); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowCos_205");
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if (ROR_not_equals((i & 1), 0, "org.apache.commons.math3.util.FastMathCalc.slowCos_205", _mut40510, _mut40511, _mut40512, _mut40513, _mut40514)) {
                // skip odd entries
                continue;
            }
            split(FACT[i], as);
            splitReciprocal(as, facts);
            if (ROR_not_equals((i & 2), 0, "org.apache.commons.math3.util.FastMathCalc.slowCos_205", _mut40515, _mut40516, _mut40517, _mut40518, _mut40519)) {
                // alternate terms are negative
                facts[0] = -facts[0];
                facts[1] = -facts[1];
            }
            splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return AOR_plus(ys[0], ys[1], "org.apache.commons.math3.util.FastMathCalc.slowCos_205", _mut40525, _mut40526, _mut40527, _mut40528);
    }

    /**
     * For x between 0 and pi/4 compute sine using Taylor expansion:
     * sin(x) = x - x^3/3! + x^5/5! - x^7/7! ...
     * @param x number from which sine is requested
     * @param result placeholder where to put the result in extended precision
     * (may be null)
     * @return sin(x)
     */
    static double slowSin(final double x, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowSin_250");
        final double[] xs = new double[2];
        final double[] ys = new double[2];
        final double[] facts = new double[2];
        final double[] as = new double[2];
        split(x, xs);
        ys[0] = ys[1] = 0.0;
        for (int i = FACT.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMathCalc.slowSin_250", _mut40539, _mut40540, _mut40541, _mut40542, _mut40543); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowSin_250");
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            if (ROR_equals((i & 1), 0, "org.apache.commons.math3.util.FastMathCalc.slowSin_250", _mut40529, _mut40530, _mut40531, _mut40532, _mut40533)) {
                // Ignore even numbers
                continue;
            }
            split(FACT[i], as);
            splitReciprocal(as, facts);
            if (ROR_not_equals((i & 2), 0, "org.apache.commons.math3.util.FastMathCalc.slowSin_250", _mut40534, _mut40535, _mut40536, _mut40537, _mut40538)) {
                // alternate terms are negative
                facts[0] = -facts[0];
                facts[1] = -facts[1];
            }
            splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return AOR_plus(ys[0], ys[1], "org.apache.commons.math3.util.FastMathCalc.slowSin_250", _mut40544, _mut40545, _mut40546, _mut40547);
    }

    /**
     *  For x between 0 and 1, returns exp(x), uses extended precision
     *  @param x argument of exponential
     *  @param result placeholder where to place exp(x) split in two terms
     *  for extra precision (i.e. exp(x) = result[0] + result[1]
     *  @return exp(x)
     */
    static double slowexp(final double x, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowexp_294");
        final double[] xs = new double[2];
        final double[] ys = new double[2];
        final double[] facts = new double[2];
        final double[] as = new double[2];
        split(x, xs);
        ys[0] = ys[1] = 0.0;
        for (int i = FACT.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMathCalc.slowexp_294", _mut40548, _mut40549, _mut40550, _mut40551, _mut40552); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowexp_294");
            splitMult(xs, ys, as);
            ys[0] = as[0];
            ys[1] = as[1];
            split(FACT[i], as);
            splitReciprocal(as, facts);
            splitAdd(ys, facts, as);
            ys[0] = as[0];
            ys[1] = as[1];
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
        }
        return AOR_plus(ys[0], ys[1], "org.apache.commons.math3.util.FastMathCalc.slowexp_294", _mut40553, _mut40554, _mut40555, _mut40556);
    }

    /**
     * Compute split[0], split[1] such that their sum is equal to d,
     * and split[0] has its 30 least significant bits as zero.
     * @param d number to split
     * @param split placeholder where to place the result
     */
    private static void split(final double d, final double[] split) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.split_328");
        if ((_mut40567 ? (ROR_less(d, 8e298, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40557, _mut40558, _mut40559, _mut40560, _mut40561) || ROR_greater(d, -8e298, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40562, _mut40563, _mut40564, _mut40565, _mut40566)) : (ROR_less(d, 8e298, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40557, _mut40558, _mut40559, _mut40560, _mut40561) && ROR_greater(d, -8e298, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40562, _mut40563, _mut40564, _mut40565, _mut40566)))) {
            final double a = AOR_multiply(d, HEX_40000000, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40588, _mut40589, _mut40590, _mut40591);
            split[0] = AOR_minus((AOR_plus(d, a, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40592, _mut40593, _mut40594, _mut40595)), a, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40596, _mut40597, _mut40598, _mut40599);
            split[1] = AOR_minus(d, split[0], "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40600, _mut40601, _mut40602, _mut40603);
        } else {
            final double a = AOR_multiply(d, 9.31322574615478515625E-10, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40568, _mut40569, _mut40570, _mut40571);
            split[0] = AOR_multiply((AOR_minus(AOR_plus(d, a, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40572, _mut40573, _mut40574, _mut40575), d, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40576, _mut40577, _mut40578, _mut40579)), HEX_40000000, "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40580, _mut40581, _mut40582, _mut40583);
            split[1] = AOR_minus(d, split[0], "org.apache.commons.math3.util.FastMathCalc.split_328", _mut40584, _mut40585, _mut40586, _mut40587);
        }
    }

    /**
     * Recompute a split.
     * @param a input/out array containing the split, changed
     * on output
     */
    private static void resplit(final double[] a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.resplit_344");
        final double c = AOR_plus(a[0], a[1], "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40604, _mut40605, _mut40606, _mut40607);
        final double d = -(AOR_minus(AOR_minus(c, a[0], "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40608, _mut40609, _mut40610, _mut40611), a[1], "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40612, _mut40613, _mut40614, _mut40615));
        if ((_mut40626 ? (ROR_less(c, 8e298, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40616, _mut40617, _mut40618, _mut40619, _mut40620) || ROR_greater(c, -8e298, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40621, _mut40622, _mut40623, _mut40624, _mut40625)) : (ROR_less(c, 8e298, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40616, _mut40617, _mut40618, _mut40619, _mut40620) && ROR_greater(c, -8e298, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40621, _mut40622, _mut40623, _mut40624, _mut40625)))) {
            // MAGIC NUMBER
            double z = AOR_multiply(c, HEX_40000000, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40651, _mut40652, _mut40653, _mut40654);
            a[0] = AOR_minus((AOR_plus(c, z, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40655, _mut40656, _mut40657, _mut40658)), z, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40659, _mut40660, _mut40661, _mut40662);
            a[1] = AOR_plus(AOR_minus(c, a[0], "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40663, _mut40664, _mut40665, _mut40666), d, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40667, _mut40668, _mut40669, _mut40670);
        } else {
            double z = AOR_multiply(c, 9.31322574615478515625E-10, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40627, _mut40628, _mut40629, _mut40630);
            a[0] = AOR_multiply((AOR_minus(AOR_plus(c, z, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40631, _mut40632, _mut40633, _mut40634), c, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40635, _mut40636, _mut40637, _mut40638)), HEX_40000000, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40639, _mut40640, _mut40641, _mut40642);
            a[1] = AOR_plus(AOR_minus(c, a[0], "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40643, _mut40644, _mut40645, _mut40646), d, "org.apache.commons.math3.util.FastMathCalc.resplit_344", _mut40647, _mut40648, _mut40649, _mut40650);
        }
    }

    /**
     * Multiply two numbers in split form.
     * @param a first term of multiplication
     * @param b second term of multiplication
     * @param ans placeholder where to put the result
     */
    private static void splitMult(double[] a, double[] b, double[] ans) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.splitMult_364");
        ans[0] = AOR_multiply(a[0], b[0], "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40671, _mut40672, _mut40673, _mut40674);
        ans[1] = AOR_plus(AOR_plus(AOR_multiply(a[0], b[1], "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40675, _mut40676, _mut40677, _mut40678), AOR_multiply(a[1], b[0], "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40679, _mut40680, _mut40681, _mut40682), "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40683, _mut40684, _mut40685, _mut40686), AOR_multiply(a[1], b[1], "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40687, _mut40688, _mut40689, _mut40690), "org.apache.commons.math3.util.FastMathCalc.splitMult_364", _mut40691, _mut40692, _mut40693, _mut40694);
        /* Resplit */
        resplit(ans);
    }

    /**
     * Add two numbers in split form.
     * @param a first term of addition
     * @param b second term of addition
     * @param ans placeholder where to put the result
     */
    private static void splitAdd(final double[] a, final double[] b, final double[] ans) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.splitAdd_377");
        ans[0] = AOR_plus(a[0], b[0], "org.apache.commons.math3.util.FastMathCalc.splitAdd_377", _mut40695, _mut40696, _mut40697, _mut40698);
        ans[1] = AOR_plus(a[1], b[1], "org.apache.commons.math3.util.FastMathCalc.splitAdd_377", _mut40699, _mut40700, _mut40701, _mut40702);
        resplit(ans);
    }

    /**
     * Compute the reciprocal of in.  Use the following algorithm.
     *  in = c + d.
     *  want to find x + y such that x+y = 1/(c+d) and x is much
     *  larger than y and x has several zero bits on the right.
     *
     *  Set b = 1/(2^22),  a = 1 - b.  Thus (a+b) = 1.
     *  Use following identity to compute (a+b)/(c+d)
     *
     *  (a+b)/(c+d)  =   a/c   +    (bc - ad) / (c^2 + cd)
     *  set x = a/c  and y = (bc - ad) / (c^2 + cd)
     *  This will be close to the right answer, but there will be
     *  some rounding in the calculation of X.  So by carefully
     *  computing 1 - (c+d)(x+y) we can compute an error and
     *  add that back in.   This is done carefully so that terms
     *  of similar size are subtracted first.
     *  @param in initial number, in split form
     *  @param result placeholder where to put the result
     */
    static void splitReciprocal(final double[] in, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402");
        final double b = AOR_divide(1.0, 4194304.0, "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40703, _mut40704, _mut40705, _mut40706);
        final double a = AOR_minus(1.0, b, "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40707, _mut40708, _mut40709, _mut40710);
        if (ROR_equals(in[0], 0.0, "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40711, _mut40712, _mut40713, _mut40714, _mut40715)) {
            in[0] = in[1];
            in[1] = 0.0;
        }
        result[0] = AOR_divide(a, in[0], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40716, _mut40717, _mut40718, _mut40719);
        result[1] = AOR_divide((AOR_minus(AOR_multiply(b, in[0], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40720, _mut40721, _mut40722, _mut40723), AOR_multiply(a, in[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40724, _mut40725, _mut40726, _mut40727), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40728, _mut40729, _mut40730, _mut40731)), (AOR_plus(AOR_multiply(in[0], in[0], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40732, _mut40733, _mut40734, _mut40735), AOR_multiply(in[0], in[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40736, _mut40737, _mut40738, _mut40739), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40740, _mut40741, _mut40742, _mut40743)), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40744, _mut40745, _mut40746, _mut40747);
        if (ROR_not_equals(result[1], result[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40748, _mut40749, _mut40750, _mut40751, _mut40752)) {
            // can happen if result[1] is NAN
            result[1] = 0.0;
        }
        /* Resplit */
        resplit(result);
        for (int i = 0; ROR_less(i, 2, "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40789, _mut40790, _mut40791, _mut40792, _mut40793); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402");
            /* this may be overkill, probably once is enough */
            double err = AOR_minus(AOR_minus(AOR_minus(AOR_minus(1.0, AOR_multiply(result[0], in[0], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40753, _mut40754, _mut40755, _mut40756), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40757, _mut40758, _mut40759, _mut40760), AOR_multiply(result[0], in[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40761, _mut40762, _mut40763, _mut40764), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40765, _mut40766, _mut40767, _mut40768), AOR_multiply(result[1], in[0], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40769, _mut40770, _mut40771, _mut40772), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40773, _mut40774, _mut40775, _mut40776), AOR_multiply(result[1], in[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40777, _mut40778, _mut40779, _mut40780), "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40781, _mut40782, _mut40783, _mut40784);
            /*err = 1.0 - err; */
            err *= AOR_plus(result[0], result[1], "org.apache.commons.math3.util.FastMathCalc.splitReciprocal_402", _mut40785, _mut40786, _mut40787, _mut40788);
            /*printf("err = %16e\n", err); */
            result[1] += err;
        }
    }

    /**
     * Compute (a[0] + a[1]) * (b[0] + b[1]) in extended precision.
     * @param a first term of the multiplication
     * @param b second term of the multiplication
     * @param result placeholder where to put the result
     */
    private static void quadMult(final double[] a, final double[] b, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.quadMult_437");
        final double[] xs = new double[2];
        final double[] ys = new double[2];
        final double[] zs = new double[2];
        /* a[0] * b[0] */
        split(a[0], xs);
        split(b[0], ys);
        splitMult(xs, ys, zs);
        result[0] = zs[0];
        result[1] = zs[1];
        /* a[0] * b[1] */
        split(b[1], ys);
        splitMult(xs, ys, zs);
        double tmp = AOR_plus(result[0], zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40794, _mut40795, _mut40796, _mut40797);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40798, _mut40799, _mut40800, _mut40801), zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40802, _mut40803, _mut40804, _mut40805);
        result[0] = tmp;
        tmp = AOR_plus(result[0], zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40806, _mut40807, _mut40808, _mut40809);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40810, _mut40811, _mut40812, _mut40813), zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40814, _mut40815, _mut40816, _mut40817);
        result[0] = tmp;
        /* a[1] * b[0] */
        split(a[1], xs);
        split(b[0], ys);
        splitMult(xs, ys, zs);
        tmp = AOR_plus(result[0], zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40818, _mut40819, _mut40820, _mut40821);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40822, _mut40823, _mut40824, _mut40825), zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40826, _mut40827, _mut40828, _mut40829);
        result[0] = tmp;
        tmp = AOR_plus(result[0], zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40830, _mut40831, _mut40832, _mut40833);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40834, _mut40835, _mut40836, _mut40837), zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40838, _mut40839, _mut40840, _mut40841);
        result[0] = tmp;
        /* a[1] * b[0] */
        split(a[1], xs);
        split(b[1], ys);
        splitMult(xs, ys, zs);
        tmp = AOR_plus(result[0], zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40842, _mut40843, _mut40844, _mut40845);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40846, _mut40847, _mut40848, _mut40849), zs[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40850, _mut40851, _mut40852, _mut40853);
        result[0] = tmp;
        tmp = AOR_plus(result[0], zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40854, _mut40855, _mut40856, _mut40857);
        result[1] -= AOR_minus(AOR_minus(tmp, result[0], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40858, _mut40859, _mut40860, _mut40861), zs[1], "org.apache.commons.math3.util.FastMathCalc.quadMult_437", _mut40862, _mut40863, _mut40864, _mut40865);
        result[0] = tmp;
    }

    /**
     * Compute exp(p) for a integer p in extended precision.
     * @param p integer whose exponential is requested
     * @param result placeholder where to put the result in extended precision
     * @return exp(p) in standard precision (equal to result[0] + result[1])
     */
    static double expint(int p, final double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.expint_491");
        // double x = M_E;
        final double[] xs = new double[2];
        final double[] as = new double[2];
        final double[] ys = new double[2];
        /* E */
        xs[0] = 2.718281828459045;
        xs[1] = 1.4456468917292502E-16;
        split(1.0, ys);
        while (ROR_greater(p, 0, "org.apache.commons.math3.util.FastMathCalc.expint_491", _mut40871, _mut40872, _mut40873, _mut40874, _mut40875)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.expint_491");
            if (ROR_not_equals((p & 1), 0, "org.apache.commons.math3.util.FastMathCalc.expint_491", _mut40866, _mut40867, _mut40868, _mut40869, _mut40870)) {
                quadMult(ys, xs, as);
                ys[0] = as[0];
                ys[1] = as[1];
            }
            quadMult(xs, xs, as);
            xs[0] = as[0];
            xs[1] = as[1];
            p >>= 1;
        }
        if (result != null) {
            result[0] = ys[0];
            result[1] = ys[1];
            resplit(result);
        }
        return AOR_plus(ys[0], ys[1], "org.apache.commons.math3.util.FastMathCalc.expint_491", _mut40876, _mut40877, _mut40878, _mut40879);
    }

    /**
     * xi in the range of [1, 2].
     *                                3        5        7
     *      x+1           /          x        x        x          \
     *  ln ----- =   2 *  |  x  +   ----  +  ----  +  ---- + ...  |
     *      1-x           \          3        5        7          /
     *
     * So, compute a Remez approximation of the following function
     *
     *  ln ((sqrt(x)+1)/(1-sqrt(x)))  /  x
     *
     * This will be an even function with only positive coefficents.
     * x is in the range [0 - 1/3].
     *
     * Transform xi for input to the above function by setting
     * x = (xi-1)/(xi+1).   Input to the polynomial is x^2, then
     * the result is multiplied by x.
     * @param xi number from which log is requested
     * @return log(xi)
     */
    static double[] slowLog(double xi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowLog_549");
        double[] x = new double[2];
        double[] x2 = new double[2];
        double[] y = new double[2];
        double[] a = new double[2];
        split(xi, x);
        /* Set X = (x-1)/(x+1) */
        x[0] += 1.0;
        resplit(x);
        splitReciprocal(x, a);
        x[0] -= 2.0;
        resplit(x);
        splitMult(x, a, y);
        x[0] = y[0];
        x[1] = y[1];
        /* Square X -> X2*/
        splitMult(x, x, x2);
        y[0] = LN_SPLIT_COEF[AOR_minus(LN_SPLIT_COEF.length, 1, "org.apache.commons.math3.util.FastMathCalc.slowLog_549", _mut40880, _mut40881, _mut40882, _mut40883)][0];
        y[1] = LN_SPLIT_COEF[AOR_minus(LN_SPLIT_COEF.length, 1, "org.apache.commons.math3.util.FastMathCalc.slowLog_549", _mut40884, _mut40885, _mut40886, _mut40887)][1];
        for (int i = LN_SPLIT_COEF.length - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMathCalc.slowLog_549", _mut40888, _mut40889, _mut40890, _mut40891, _mut40892); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.slowLog_549");
            splitMult(y, x2, a);
            y[0] = a[0];
            y[1] = a[1];
            splitAdd(y, LN_SPLIT_COEF[i], a);
            y[0] = a[0];
            y[1] = a[1];
        }
        splitMult(y, x, a);
        y[0] = a[0];
        y[1] = a[1];
        return y;
    }

    /**
     * Print an array.
     * @param out text output stream where output should be printed
     * @param name array name
     * @param expectedLen expected length of the array
     * @param array2d array data
     */
    static void printarray(PrintStream out, String name, int expectedLen, double[][] array2d) {
        out.println(name);
        checkLen(expectedLen, array2d.length);
        out.println(TABLE_START_DECL + " ");
        int i = 0;
        for (double[] array : array2d) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.printarray_601");
            // "double array[]" causes PMD parsing error
            out.print("        {");
            for (double d : array) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.printarray_601");
                // multiple entries per line
                out.printf("%-25.25s", format(d));
            }
            out.println("}, // " + i++);
        }
        out.println(TABLE_END_DECL);
    }

    /**
     * Print an array.
     * @param out text output stream where output should be printed
     * @param name array name
     * @param expectedLen expected length of the array
     * @param array array data
     */
    static void printarray(PrintStream out, String name, int expectedLen, double[] array) {
        out.println(name + "=");
        checkLen(expectedLen, array.length);
        out.println(TABLE_START_DECL);
        for (double d : array) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.printarray_623");
            // one entry per line
            out.printf("        %s%n", format(d));
        }
        out.println(TABLE_END_DECL);
    }

    /**
     * Format a double.
     * @param d double number to format
     * @return formatted number
     */
    static String format(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.format_637");
        if (ROR_not_equals(d, d, "org.apache.commons.math3.util.FastMathCalc.format_637", _mut40893, _mut40894, _mut40895, _mut40896, _mut40897)) {
            return "Double.NaN,";
        } else {
            return ((ROR_greater_equals(d, 0, "org.apache.commons.math3.util.FastMathCalc.format_637", _mut40898, _mut40899, _mut40900, _mut40901, _mut40902)) ? "+" : "") + Double.toString(d) + "d,";
        }
    }

    /**
     * Check two lengths are equal.
     * @param expectedLen expected length
     * @param actual actual length
     * @exception DimensionMismatchException if the two lengths are not equal
     */
    private static void checkLen(int expectedLen, int actual) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMathCalc.checkLen_651");
        if (ROR_not_equals(expectedLen, actual, "org.apache.commons.math3.util.FastMathCalc.checkLen_651", _mut40903, _mut40904, _mut40905, _mut40906, _mut40907)) {
            throw new DimensionMismatchException(actual, expectedLen);
        }
    }
}
