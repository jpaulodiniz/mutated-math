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
package org.apache.commons.math3.linear;

import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Basic implementation of RealMatrix methods regardless of the underlying storage.
 * <p>All the methods implemented here use {@link #getEntry(int, int)} to access
 * matrix elements. Derived class can provide faster implementations.</p>
 *
 * @since 2.0
 */
public abstract class AbstractRealMatrix extends RealLinearOperator implements RealMatrix {

    @Conditional
    public static boolean _mut24411 = false, _mut24412 = false, _mut24413 = false, _mut24414 = false, _mut24415 = false, _mut24416 = false, _mut24417 = false, _mut24418 = false, _mut24419 = false, _mut24420 = false, _mut24421 = false, _mut24422 = false, _mut24423 = false, _mut24424 = false, _mut24425 = false, _mut24426 = false, _mut24427 = false, _mut24428 = false, _mut24429 = false, _mut24430 = false, _mut24431 = false, _mut24432 = false, _mut24433 = false, _mut24434 = false, _mut24435 = false, _mut24436 = false, _mut24437 = false, _mut24438 = false, _mut24439 = false, _mut24440 = false, _mut24441 = false, _mut24442 = false, _mut24443 = false, _mut24444 = false, _mut24445 = false, _mut24446 = false, _mut24447 = false, _mut24448 = false, _mut24449 = false, _mut24450 = false, _mut24451 = false, _mut24452 = false, _mut24453 = false, _mut24454 = false, _mut24455 = false, _mut24456 = false, _mut24457 = false, _mut24458 = false, _mut24459 = false, _mut24460 = false, _mut24461 = false, _mut24462 = false, _mut24463 = false, _mut24464 = false, _mut24465 = false, _mut24466 = false, _mut24467 = false, _mut24468 = false, _mut24469 = false, _mut24470 = false, _mut24471 = false, _mut24472 = false, _mut24473 = false, _mut24474 = false, _mut24475 = false, _mut24476 = false, _mut24477 = false, _mut24478 = false, _mut24479 = false, _mut24480 = false, _mut24481 = false, _mut24482 = false, _mut24483 = false, _mut24484 = false, _mut24485 = false, _mut24486 = false, _mut24487 = false, _mut24488 = false, _mut24489 = false, _mut24490 = false, _mut24491 = false, _mut24492 = false, _mut24493 = false, _mut24494 = false, _mut24495 = false, _mut24496 = false, _mut24497 = false, _mut24498 = false, _mut24499 = false, _mut24500 = false, _mut24501 = false, _mut24502 = false, _mut24503 = false, _mut24504 = false, _mut24505 = false, _mut24506 = false, _mut24507 = false, _mut24508 = false, _mut24509 = false, _mut24510 = false, _mut24511 = false, _mut24512 = false, _mut24513 = false, _mut24514 = false, _mut24515 = false, _mut24516 = false, _mut24517 = false, _mut24518 = false, _mut24519 = false, _mut24520 = false, _mut24521 = false, _mut24522 = false, _mut24523 = false, _mut24524 = false, _mut24525 = false, _mut24526 = false, _mut24527 = false, _mut24528 = false, _mut24529 = false, _mut24530 = false, _mut24531 = false, _mut24532 = false, _mut24533 = false, _mut24534 = false, _mut24535 = false, _mut24536 = false, _mut24537 = false, _mut24538 = false, _mut24539 = false, _mut24540 = false, _mut24541 = false, _mut24542 = false, _mut24543 = false, _mut24544 = false, _mut24545 = false, _mut24546 = false, _mut24547 = false, _mut24548 = false, _mut24549 = false, _mut24550 = false, _mut24551 = false, _mut24552 = false, _mut24553 = false, _mut24554 = false, _mut24555 = false, _mut24556 = false, _mut24557 = false, _mut24558 = false, _mut24559 = false, _mut24560 = false, _mut24561 = false, _mut24562 = false, _mut24563 = false, _mut24564 = false, _mut24565 = false, _mut24566 = false, _mut24567 = false, _mut24568 = false, _mut24569 = false, _mut24570 = false, _mut24571 = false, _mut24572 = false, _mut24573 = false, _mut24574 = false, _mut24575 = false, _mut24576 = false, _mut24577 = false, _mut24578 = false, _mut24579 = false, _mut24580 = false, _mut24581 = false, _mut24582 = false, _mut24583 = false, _mut24584 = false, _mut24585 = false, _mut24586 = false, _mut24587 = false, _mut24588 = false, _mut24589 = false, _mut24590 = false, _mut24591 = false, _mut24592 = false, _mut24593 = false, _mut24594 = false, _mut24595 = false, _mut24596 = false, _mut24597 = false, _mut24598 = false, _mut24599 = false, _mut24600 = false, _mut24601 = false, _mut24602 = false, _mut24603 = false, _mut24604 = false, _mut24605 = false, _mut24606 = false, _mut24607 = false, _mut24608 = false, _mut24609 = false, _mut24610 = false, _mut24611 = false, _mut24612 = false, _mut24613 = false, _mut24614 = false, _mut24615 = false, _mut24616 = false, _mut24617 = false, _mut24618 = false, _mut24619 = false, _mut24620 = false, _mut24621 = false, _mut24622 = false, _mut24623 = false, _mut24624 = false, _mut24625 = false, _mut24626 = false, _mut24627 = false, _mut24628 = false, _mut24629 = false, _mut24630 = false, _mut24631 = false, _mut24632 = false, _mut24633 = false, _mut24634 = false, _mut24635 = false, _mut24636 = false, _mut24637 = false, _mut24638 = false, _mut24639 = false, _mut24640 = false, _mut24641 = false, _mut24642 = false, _mut24643 = false, _mut24644 = false, _mut24645 = false, _mut24646 = false, _mut24647 = false, _mut24648 = false, _mut24649 = false, _mut24650 = false, _mut24651 = false, _mut24652 = false, _mut24653 = false, _mut24654 = false, _mut24655 = false, _mut24656 = false, _mut24657 = false, _mut24658 = false, _mut24659 = false, _mut24660 = false, _mut24661 = false, _mut24662 = false, _mut24663 = false, _mut24664 = false, _mut24665 = false, _mut24666 = false, _mut24667 = false, _mut24668 = false, _mut24669 = false, _mut24670 = false, _mut24671 = false, _mut24672 = false, _mut24673 = false, _mut24674 = false, _mut24675 = false, _mut24676 = false, _mut24677 = false, _mut24678 = false, _mut24679 = false, _mut24680 = false, _mut24681 = false, _mut24682 = false, _mut24683 = false, _mut24684 = false, _mut24685 = false, _mut24686 = false, _mut24687 = false, _mut24688 = false, _mut24689 = false, _mut24690 = false, _mut24691 = false, _mut24692 = false, _mut24693 = false, _mut24694 = false, _mut24695 = false, _mut24696 = false, _mut24697 = false, _mut24698 = false, _mut24699 = false, _mut24700 = false, _mut24701 = false, _mut24702 = false, _mut24703 = false, _mut24704 = false, _mut24705 = false, _mut24706 = false, _mut24707 = false, _mut24708 = false, _mut24709 = false, _mut24710 = false, _mut24711 = false, _mut24712 = false, _mut24713 = false, _mut24714 = false, _mut24715 = false, _mut24716 = false, _mut24717 = false, _mut24718 = false, _mut24719 = false, _mut24720 = false, _mut24721 = false, _mut24722 = false, _mut24723 = false, _mut24724 = false, _mut24725 = false, _mut24726 = false, _mut24727 = false, _mut24728 = false, _mut24729 = false, _mut24730 = false, _mut24731 = false, _mut24732 = false, _mut24733 = false, _mut24734 = false, _mut24735 = false, _mut24736 = false, _mut24737 = false, _mut24738 = false, _mut24739 = false, _mut24740 = false, _mut24741 = false, _mut24742 = false, _mut24743 = false, _mut24744 = false, _mut24745 = false, _mut24746 = false, _mut24747 = false, _mut24748 = false, _mut24749 = false, _mut24750 = false, _mut24751 = false, _mut24752 = false, _mut24753 = false, _mut24754 = false, _mut24755 = false, _mut24756 = false, _mut24757 = false, _mut24758 = false, _mut24759 = false, _mut24760 = false, _mut24761 = false, _mut24762 = false, _mut24763 = false, _mut24764 = false, _mut24765 = false, _mut24766 = false, _mut24767 = false, _mut24768 = false, _mut24769 = false, _mut24770 = false, _mut24771 = false, _mut24772 = false, _mut24773 = false, _mut24774 = false, _mut24775 = false, _mut24776 = false, _mut24777 = false, _mut24778 = false, _mut24779 = false, _mut24780 = false, _mut24781 = false, _mut24782 = false, _mut24783 = false, _mut24784 = false, _mut24785 = false, _mut24786 = false, _mut24787 = false, _mut24788 = false, _mut24789 = false, _mut24790 = false, _mut24791 = false, _mut24792 = false, _mut24793 = false, _mut24794 = false, _mut24795 = false, _mut24796 = false, _mut24797 = false, _mut24798 = false, _mut24799 = false, _mut24800 = false, _mut24801 = false, _mut24802 = false, _mut24803 = false, _mut24804 = false, _mut24805 = false, _mut24806 = false, _mut24807 = false, _mut24808 = false, _mut24809 = false, _mut24810 = false, _mut24811 = false, _mut24812 = false, _mut24813 = false, _mut24814 = false, _mut24815 = false, _mut24816 = false, _mut24817 = false, _mut24818 = false, _mut24819 = false, _mut24820 = false, _mut24821 = false, _mut24822 = false, _mut24823 = false, _mut24824 = false, _mut24825 = false, _mut24826 = false, _mut24827 = false, _mut24828 = false, _mut24829 = false, _mut24830 = false, _mut24831 = false, _mut24832 = false, _mut24833 = false, _mut24834 = false, _mut24835 = false, _mut24836 = false, _mut24837 = false, _mut24838 = false, _mut24839 = false, _mut24840 = false, _mut24841 = false, _mut24842 = false, _mut24843 = false, _mut24844 = false, _mut24845 = false, _mut24846 = false, _mut24847 = false, _mut24848 = false, _mut24849 = false, _mut24850 = false, _mut24851 = false, _mut24852 = false, _mut24853 = false, _mut24854 = false, _mut24855 = false, _mut24856 = false, _mut24857 = false, _mut24858 = false, _mut24859 = false, _mut24860 = false, _mut24861 = false, _mut24862 = false, _mut24863 = false, _mut24864 = false, _mut24865 = false, _mut24866 = false, _mut24867 = false, _mut24868 = false, _mut24869 = false, _mut24870 = false, _mut24871 = false, _mut24872 = false, _mut24873 = false, _mut24874 = false, _mut24875 = false, _mut24876 = false, _mut24877 = false, _mut24878 = false, _mut24879 = false, _mut24880 = false, _mut24881 = false, _mut24882 = false, _mut24883 = false, _mut24884 = false, _mut24885 = false, _mut24886 = false, _mut24887 = false, _mut24888 = false, _mut24889 = false, _mut24890 = false, _mut24891 = false, _mut24892 = false, _mut24893 = false, _mut24894 = false, _mut24895 = false, _mut24896 = false, _mut24897 = false, _mut24898 = false, _mut24899 = false, _mut24900 = false, _mut24901 = false, _mut24902 = false, _mut24903 = false, _mut24904 = false, _mut24905 = false, _mut24906 = false, _mut24907 = false, _mut24908 = false, _mut24909 = false, _mut24910 = false, _mut24911 = false, _mut24912 = false, _mut24913 = false, _mut24914 = false, _mut24915 = false, _mut24916 = false, _mut24917 = false, _mut24918 = false, _mut24919 = false, _mut24920 = false, _mut24921 = false, _mut24922 = false, _mut24923 = false, _mut24924 = false, _mut24925 = false, _mut24926 = false, _mut24927 = false, _mut24928 = false, _mut24929 = false, _mut24930 = false, _mut24931 = false, _mut24932 = false, _mut24933 = false, _mut24934 = false, _mut24935 = false, _mut24936 = false, _mut24937 = false, _mut24938 = false, _mut24939 = false, _mut24940 = false, _mut24941 = false, _mut24942 = false, _mut24943 = false, _mut24944 = false, _mut24945 = false, _mut24946 = false, _mut24947 = false, _mut24948 = false, _mut24949 = false, _mut24950 = false, _mut24951 = false, _mut24952 = false, _mut24953 = false, _mut24954 = false, _mut24955 = false, _mut24956 = false, _mut24957 = false, _mut24958 = false, _mut24959 = false, _mut24960 = false, _mut24961 = false, _mut24962 = false, _mut24963 = false, _mut24964 = false, _mut24965 = false, _mut24966 = false, _mut24967 = false, _mut24968 = false, _mut24969 = false, _mut24970 = false, _mut24971 = false, _mut24972 = false, _mut24973 = false, _mut24974 = false, _mut24975 = false, _mut24976 = false, _mut24977 = false, _mut24978 = false, _mut24979 = false, _mut24980 = false, _mut24981 = false, _mut24982 = false, _mut24983 = false, _mut24984 = false, _mut24985 = false, _mut24986 = false, _mut24987 = false, _mut24988 = false, _mut24989 = false, _mut24990 = false, _mut24991 = false, _mut24992 = false, _mut24993 = false, _mut24994 = false, _mut24995 = false, _mut24996 = false, _mut24997 = false, _mut24998 = false, _mut24999 = false, _mut25000 = false, _mut25001 = false, _mut25002 = false, _mut25003 = false, _mut25004 = false, _mut25005 = false, _mut25006 = false, _mut25007 = false, _mut25008 = false, _mut25009 = false, _mut25010 = false, _mut25011 = false, _mut25012 = false, _mut25013 = false, _mut25014 = false, _mut25015 = false, _mut25016 = false, _mut25017 = false, _mut25018 = false, _mut25019 = false, _mut25020 = false, _mut25021 = false, _mut25022 = false, _mut25023 = false, _mut25024 = false, _mut25025 = false, _mut25026 = false, _mut25027 = false, _mut25028 = false, _mut25029 = false, _mut25030 = false, _mut25031 = false, _mut25032 = false, _mut25033 = false, _mut25034 = false, _mut25035 = false, _mut25036 = false, _mut25037 = false, _mut25038 = false, _mut25039 = false, _mut25040 = false, _mut25041 = false, _mut25042 = false, _mut25043 = false, _mut25044 = false, _mut25045 = false, _mut25046 = false, _mut25047 = false, _mut25048 = false, _mut25049 = false, _mut25050 = false, _mut25051 = false, _mut25052 = false, _mut25053 = false, _mut25054 = false, _mut25055 = false, _mut25056 = false, _mut25057 = false, _mut25058 = false, _mut25059 = false, _mut25060 = false, _mut25061 = false, _mut25062 = false, _mut25063 = false, _mut25064 = false, _mut25065 = false, _mut25066 = false, _mut25067 = false, _mut25068 = false, _mut25069 = false, _mut25070 = false, _mut25071 = false, _mut25072 = false, _mut25073 = false, _mut25074 = false, _mut25075 = false, _mut25076 = false, _mut25077 = false, _mut25078 = false, _mut25079 = false, _mut25080 = false, _mut25081 = false, _mut25082 = false, _mut25083 = false, _mut25084 = false, _mut25085 = false, _mut25086 = false, _mut25087 = false, _mut25088 = false, _mut25089 = false, _mut25090 = false, _mut25091 = false, _mut25092 = false, _mut25093 = false, _mut25094 = false, _mut25095 = false, _mut25096 = false, _mut25097 = false, _mut25098 = false, _mut25099 = false, _mut25100 = false, _mut25101 = false, _mut25102 = false, _mut25103 = false, _mut25104 = false, _mut25105 = false, _mut25106 = false, _mut25107 = false, _mut25108 = false, _mut25109 = false, _mut25110 = false, _mut25111 = false, _mut25112 = false, _mut25113 = false, _mut25114 = false, _mut25115 = false, _mut25116 = false, _mut25117 = false, _mut25118 = false;

    /**
     * Default format.
     */
    private static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance(Locale.US);

    static {
        // set the minimum fraction digits to 1 to keep compatibility
        DEFAULT_FORMAT.getFormat().setMinimumFractionDigits(1);
    }

    /**
     * Creates a matrix with no data
     */
    protected AbstractRealMatrix() {
    }

    /**
     * Create a new RealMatrix with the supplied row and column dimensions.
     *
     * @param rowDimension  the number of rows in the new matrix
     * @param columnDimension  the number of columns in the new matrix
     * @throws NotStrictlyPositiveException if row or column dimension is not positive
     */
    protected AbstractRealMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.AbstractRealMatrix_64");
        if (ROR_less(rowDimension, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.AbstractRealMatrix_64", _mut24411, _mut24412, _mut24413, _mut24414, _mut24415)) {
            throw new NotStrictlyPositiveException(rowDimension);
        }
        if (ROR_less(columnDimension, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.AbstractRealMatrix_64", _mut24416, _mut24417, _mut24418, _mut24419, _mut24420)) {
            throw new NotStrictlyPositiveException(columnDimension);
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix add(RealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.add_76");
        MatrixUtils.checkAdditionCompatible(this, m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractRealMatrix.add_76", _mut24430, _mut24431, _mut24432, _mut24433, _mut24434); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.add_76");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractRealMatrix.add_76", _mut24425, _mut24426, _mut24427, _mut24428, _mut24429); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.add_76");
                out.setEntry(row, col, AOR_plus(getEntry(row, col), m.getEntry(row, col), "org.apache.commons.math3.linear.AbstractRealMatrix.add_76", _mut24421, _mut24422, _mut24423, _mut24424));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix subtract(final RealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93");
        MatrixUtils.checkSubtractionCompatible(this, m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93", _mut24444, _mut24445, _mut24446, _mut24447, _mut24448); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93", _mut24439, _mut24440, _mut24441, _mut24442, _mut24443); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93");
                out.setEntry(row, col, AOR_minus(getEntry(row, col), m.getEntry(row, col), "org.apache.commons.math3.linear.AbstractRealMatrix.subtract_93", _mut24435, _mut24436, _mut24437, _mut24438));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix scalarAdd(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110");
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110", _mut24458, _mut24459, _mut24460, _mut24461, _mut24462); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110", _mut24453, _mut24454, _mut24455, _mut24456, _mut24457); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110");
                out.setEntry(row, col, AOR_plus(getEntry(row, col), d, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarAdd_110", _mut24449, _mut24450, _mut24451, _mut24452));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix scalarMultiply(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124");
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124", _mut24472, _mut24473, _mut24474, _mut24475, _mut24476); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124", _mut24467, _mut24468, _mut24469, _mut24470, _mut24471); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124");
                out.setEntry(row, col, AOR_multiply(getEntry(row, col), d, "org.apache.commons.math3.linear.AbstractRealMatrix.scalarMultiply_124", _mut24463, _mut24464, _mut24465, _mut24466));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix multiply(final RealMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138");
        MatrixUtils.checkMultiplicationCompatible(this, m);
        final int nRows = getRowDimension();
        final int nCols = m.getColumnDimension();
        final int nSum = getColumnDimension();
        final RealMatrix out = createMatrix(nRows, nCols);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138", _mut24491, _mut24492, _mut24493, _mut24494, _mut24495); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138", _mut24486, _mut24487, _mut24488, _mut24489, _mut24490); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138");
                double sum = 0;
                for (int i = 0; ROR_less(i, nSum, "org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138", _mut24481, _mut24482, _mut24483, _mut24484, _mut24485); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138");
                    sum += AOR_multiply(getEntry(row, i), m.getEntry(i, col), "org.apache.commons.math3.linear.AbstractRealMatrix.multiply_138", _mut24477, _mut24478, _mut24479, _mut24480);
                }
                out.setEntry(row, col, sum);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix preMultiply(final RealMatrix m) throws DimensionMismatchException {
        return m.multiply(this);
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix power(final int p) throws NotPositiveException, NonSquareMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.power_166");
        if (ROR_less(p, 0, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24496, _mut24497, _mut24498, _mut24499, _mut24500)) {
            throw new NotPositiveException(LocalizedFormats.NOT_POSITIVE_EXPONENT, p);
        }
        if (!isSquare()) {
            throw new NonSquareMatrixException(getRowDimension(), getColumnDimension());
        }
        if (ROR_equals(p, 0, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24501, _mut24502, _mut24503, _mut24504, _mut24505)) {
            return MatrixUtils.createRealIdentityMatrix(this.getRowDimension());
        }
        if (ROR_equals(p, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24506, _mut24507, _mut24508, _mut24509, _mut24510)) {
            return this.copy();
        }
        final int power = AOR_minus(p, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24511, _mut24512, _mut24513, _mut24514);
        final char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
        final ArrayList<Integer> nonZeroPositions = new ArrayList<Integer>();
        int maxI = -1;
        for (int i = 0; ROR_less(i, binaryRepresentation.length, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24528, _mut24529, _mut24530, _mut24531, _mut24532); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.power_166");
            if (binaryRepresentation[i] == '1') {
                final int pos = AOR_minus(AOR_minus(binaryRepresentation.length, i, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24515, _mut24516, _mut24517, _mut24518), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24519, _mut24520, _mut24521, _mut24522);
                nonZeroPositions.add(pos);
                // The positions are taken in turn, so maxI is only changed once
                if (ROR_equals(maxI, -1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24523, _mut24524, _mut24525, _mut24526, _mut24527)) {
                    maxI = pos;
                }
            }
        }
        RealMatrix[] results = new RealMatrix[AOR_plus(maxI, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24533, _mut24534, _mut24535, _mut24536)];
        results[0] = this.copy();
        for (int i = 1; ROR_less_equals(i, maxI, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24545, _mut24546, _mut24547, _mut24548, _mut24549); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.power_166");
            results[i] = results[AOR_minus(i, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24541, _mut24542, _mut24543, _mut24544)].multiply(results[AOR_minus(i, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.power_166", _mut24537, _mut24538, _mut24539, _mut24540)]);
        }
        RealMatrix result = this.copy();
        for (Integer i : nonZeroPositions) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.power_166");
            result = result.multiply(results[i]);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] getData() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getData_226");
        final double[][] data = new double[getRowDimension()][getColumnDimension()];
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.AbstractRealMatrix.getData_226", _mut24555, _mut24556, _mut24557, _mut24558, _mut24559); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getData_226");
            final double[] dataI = data[i];
            for (int j = 0; ROR_less(j, dataI.length, "org.apache.commons.math3.linear.AbstractRealMatrix.getData_226", _mut24550, _mut24551, _mut24552, _mut24553, _mut24554); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getData_226");
                dataI[j] = getEntry(i, j);
            }
        }
        return data;
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.end_271");
        return walkInColumnOrder(new RealMatrixPreservingVisitor() {

            /**
             * Last row index.
             */
            private double endRow;

            /**
             * Sum of absolute values on one column.
             */
            private double columnSum;

            /**
             * Maximal sum across all columns.
             */
            private double maxColSum;

            /**
             * {@inheritDoc}
             */
            public void start(final int rows, final int columns, final int startRow, final int endRow, final int startColumn, final int endColumn) {
                this.endRow = endRow;
                columnSum = 0;
                maxColSum = 0;
            }

            /**
             * {@inheritDoc}
             */
            public void visit(final int row, final int column, final double value) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.visit_262");
                columnSum += FastMath.abs(value);
                if (ROR_equals(row, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.visit_262", _mut24560, _mut24561, _mut24562, _mut24563, _mut24564)) {
                    maxColSum = FastMath.max(maxColSum, columnSum);
                    columnSum = 0;
                }
            }

            /**
             * {@inheritDoc}
             */
            public double end() {
                return maxColSum;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public double getFrobeniusNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.end_297");
        return walkInOptimizedOrder(new RealMatrixPreservingVisitor() {

            /**
             * Sum of squared entries.
             */
            private double sum;

            /**
             * {@inheritDoc}
             */
            public void start(final int rows, final int columns, final int startRow, final int endRow, final int startColumn, final int endColumn) {
                sum = 0;
            }

            /**
             * {@inheritDoc}
             */
            public void visit(final int row, final int column, final double value) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.visit_292");
                sum += AOR_multiply(value, value, "org.apache.commons.math3.linear.AbstractRealMatrix.visit_292", _mut24565, _mut24566, _mut24567, _mut24568);
            }

            /**
             * {@inheritDoc}
             */
            public double end() {
                return FastMath.sqrt(sum);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        final RealMatrix subMatrix = createMatrix(AOR_plus(AOR_minus(endRow, startRow, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24569, _mut24570, _mut24571, _mut24572), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24573, _mut24574, _mut24575, _mut24576), AOR_plus(AOR_minus(endColumn, startColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24577, _mut24578, _mut24579, _mut24580), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24581, _mut24582, _mut24583, _mut24584));
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24598, _mut24599, _mut24600, _mut24601, _mut24602); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304");
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24593, _mut24594, _mut24595, _mut24596, _mut24597); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304");
                subMatrix.setEntry(AOR_minus(i, startRow, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24585, _mut24586, _mut24587, _mut24588), AOR_minus(j, startColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.getSubMatrix_304", _mut24589, _mut24590, _mut24591, _mut24592), getEntry(i, j));
            }
        }
        return subMatrix;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws NullArgumentException, NoDataException, OutOfRangeException {
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        final RealMatrix subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
        subMatrix.walkInOptimizedOrder(new DefaultRealMatrixChangingVisitor() {

            /**
             * {@inheritDoc}
             */
            @Override
            public double visit(final int row, final int column, final double value) {
                return getEntry(selectedRows[row], selectedColumns[column]);
            }
        });
        return subMatrix;
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn, final double[][] destination) throws OutOfRangeException, NumberIsTooSmallException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.visit_380");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        final int rowsCount = AOR_minus(AOR_plus(endRow, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24603, _mut24604, _mut24605, _mut24606), startRow, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24607, _mut24608, _mut24609, _mut24610);
        final int columnsCount = AOR_minus(AOR_plus(endColumn, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24611, _mut24612, _mut24613, _mut24614), startColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24615, _mut24616, _mut24617, _mut24618);
        if ((_mut24629 ? ((ROR_less(destination.length, rowsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24619, _mut24620, _mut24621, _mut24622, _mut24623)) && (ROR_less(destination[0].length, columnsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24624, _mut24625, _mut24626, _mut24627, _mut24628))) : ((ROR_less(destination.length, rowsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24619, _mut24620, _mut24621, _mut24622, _mut24623)) || (ROR_less(destination[0].length, columnsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24624, _mut24625, _mut24626, _mut24627, _mut24628))))) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, rowsCount, columnsCount);
        }
        for (int i = 1; ROR_less(i, rowsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24635, _mut24636, _mut24637, _mut24638, _mut24639); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342");
            if (ROR_less(destination[i].length, columnsCount, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_342", _mut24630, _mut24631, _mut24632, _mut24633, _mut24634)) {
                throw new MatrixDimensionMismatchException(destination.length, destination[i].length, rowsCount, columnsCount);
            }
        }
        walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {

            /**
             * Initial row index.
             */
            private int startRow;

            /**
             * Initial column index.
             */
            private int startColumn;

            /**
             * {@inheritDoc}
             */
            @Override
            public void start(final int rows, final int columns, final int startRow, final int endRow, final int startColumn, final int endColumn) {
                this.startRow = startRow;
                this.startColumn = startColumn;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void visit(final int row, final int column, final double value) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.visit_380");
                destination[AOR_minus(row, startRow, "org.apache.commons.math3.linear.AbstractRealMatrix.visit_380", _mut24644, _mut24645, _mut24646, _mut24647)][AOR_minus(column, startColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.visit_380", _mut24640, _mut24641, _mut24642, _mut24643)] = value;
            }
        }, startRow, endRow, startColumn, endColumn);
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, double[][] destination) throws OutOfRangeException, NullArgumentException, NoDataException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389");
        MatrixUtils.checkSubMatrixIndex(this, selectedRows, selectedColumns);
        final int nCols = selectedColumns.length;
        if ((_mut24658 ? ((ROR_less(destination.length, selectedRows.length, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24648, _mut24649, _mut24650, _mut24651, _mut24652)) && (ROR_less(destination[0].length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24653, _mut24654, _mut24655, _mut24656, _mut24657))) : ((ROR_less(destination.length, selectedRows.length, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24648, _mut24649, _mut24650, _mut24651, _mut24652)) || (ROR_less(destination[0].length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24653, _mut24654, _mut24655, _mut24656, _mut24657))))) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, selectedRows.length, selectedColumns.length);
        }
        for (int i = 0; ROR_less(i, selectedRows.length, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24669, _mut24670, _mut24671, _mut24672, _mut24673); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389");
            final double[] destinationI = destination[i];
            if (ROR_less(destinationI.length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24659, _mut24660, _mut24661, _mut24662, _mut24663)) {
                throw new MatrixDimensionMismatchException(destination.length, destinationI.length, selectedRows.length, selectedColumns.length);
            }
            for (int j = 0; ROR_less(j, selectedColumns.length, "org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389", _mut24664, _mut24665, _mut24666, _mut24667, _mut24668); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.copySubMatrix_389");
                destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setSubMatrix(final double[][] subMatrix, final int row, final int column) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414");
        MathUtils.checkNotNull(subMatrix);
        final int nRows = subMatrix.length;
        if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24674, _mut24675, _mut24676, _mut24677, _mut24678)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        final int nCols = subMatrix[0].length;
        if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24679, _mut24680, _mut24681, _mut24682, _mut24683)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int r = 1; ROR_less(r, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24689, _mut24690, _mut24691, _mut24692, _mut24693); ++r) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414");
            if (ROR_not_equals(subMatrix[r].length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24684, _mut24685, _mut24686, _mut24687, _mut24688)) {
                throw new DimensionMismatchException(nCols, subMatrix[r].length);
            }
        }
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        MatrixUtils.checkRowIndex(this, AOR_minus(AOR_plus(nRows, row, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24694, _mut24695, _mut24696, _mut24697), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24698, _mut24699, _mut24700, _mut24701));
        MatrixUtils.checkColumnIndex(this, AOR_minus(AOR_plus(nCols, column, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24702, _mut24703, _mut24704, _mut24705), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24706, _mut24707, _mut24708, _mut24709));
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24723, _mut24724, _mut24725, _mut24726, _mut24727); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414");
            for (int j = 0; ROR_less(j, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24718, _mut24719, _mut24720, _mut24721, _mut24722); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414");
                setEntry(AOR_plus(row, i, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24710, _mut24711, _mut24712, _mut24713), AOR_plus(column, j, "org.apache.commons.math3.linear.AbstractRealMatrix.setSubMatrix_414", _mut24714, _mut24715, _mut24716, _mut24717), subMatrix[i][j]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getRowMatrix(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getRowMatrix_447");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        final RealMatrix out = createMatrix(1, nCols);
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.getRowMatrix_447", _mut24728, _mut24729, _mut24730, _mut24731, _mut24732); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getRowMatrix_447");
            out.setEntry(0, i, getEntry(row, i));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowMatrix(final int row, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        if ((_mut24743 ? ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459", _mut24733, _mut24734, _mut24735, _mut24736, _mut24737)) && (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459", _mut24738, _mut24739, _mut24740, _mut24741, _mut24742))) : ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459", _mut24733, _mut24734, _mut24735, _mut24736, _mut24737)) || (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459", _mut24738, _mut24739, _mut24740, _mut24741, _mut24742))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459", _mut24744, _mut24745, _mut24746, _mut24747, _mut24748); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRowMatrix_459");
            setEntry(row, i, matrix.getEntry(0, i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getColumnMatrix(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getColumnMatrix_475");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        final RealMatrix out = createMatrix(nRows, 1);
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.getColumnMatrix_475", _mut24749, _mut24750, _mut24751, _mut24752, _mut24753); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getColumnMatrix_475");
            out.setEntry(i, 0, getEntry(i, column));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnMatrix(final int column, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        if ((_mut24764 ? ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488", _mut24754, _mut24755, _mut24756, _mut24757, _mut24758)) && (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488", _mut24759, _mut24760, _mut24761, _mut24762, _mut24763))) : ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488", _mut24754, _mut24755, _mut24756, _mut24757, _mut24758)) || (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488", _mut24759, _mut24760, _mut24761, _mut24762, _mut24763))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488", _mut24765, _mut24766, _mut24767, _mut24768, _mut24769); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumnMatrix_488");
            setEntry(i, column, matrix.getEntry(i, 0));
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealVector getRowVector(final int row) throws OutOfRangeException {
        return new ArrayRealVector(getRow(row), false);
    }

    /**
     * {@inheritDoc}
     */
    public void setRowVector(final int row, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRowVector_510");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(vector.getDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowVector_510", _mut24770, _mut24771, _mut24772, _mut24773, _mut24774)) {
            throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRowVector_510", _mut24775, _mut24776, _mut24777, _mut24778, _mut24779); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRowVector_510");
            setEntry(row, i, vector.getEntry(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealVector getColumnVector(final int column) throws OutOfRangeException {
        return new ArrayRealVector(getColumn(column), false);
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnVector(final int column, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumnVector_530");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(vector.getDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnVector_530", _mut24780, _mut24781, _mut24782, _mut24783, _mut24784)) {
            throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumnVector_530", _mut24785, _mut24786, _mut24787, _mut24788, _mut24789); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumnVector_530");
            setEntry(i, column, vector.getEntry(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] getRow(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getRow_544");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        final double[] out = new double[nCols];
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.getRow_544", _mut24790, _mut24791, _mut24792, _mut24793, _mut24794); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getRow_544");
            out[i] = getEntry(row, i);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setRow(final int row, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRow_556");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(array.length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRow_556", _mut24795, _mut24796, _mut24797, _mut24798, _mut24799)) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.setRow_556", _mut24800, _mut24801, _mut24802, _mut24803, _mut24804); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setRow_556");
            setEntry(row, i, array[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] getColumn(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getColumn_569");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        final double[] out = new double[nRows];
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.getColumn_569", _mut24805, _mut24806, _mut24807, _mut24808, _mut24809); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getColumn_569");
            out[i] = getEntry(i, column);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumn(final int column, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumn_581");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(array.length, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumn_581", _mut24810, _mut24811, _mut24812, _mut24813, _mut24814)) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.setColumn_581", _mut24815, _mut24816, _mut24817, _mut24818, _mut24819); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.setColumn_581");
            setEntry(i, column, array[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.addToEntry_594");
        MatrixUtils.checkMatrixIndex(this, row, column);
        setEntry(row, column, AOR_plus(getEntry(row, column), increment, "org.apache.commons.math3.linear.AbstractRealMatrix.addToEntry_594", _mut24820, _mut24821, _mut24822, _mut24823));
    }

    /**
     * {@inheritDoc}
     */
    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.multiplyEntry_601");
        MatrixUtils.checkMatrixIndex(this, row, column);
        setEntry(row, column, AOR_multiply(getEntry(row, column), factor, "org.apache.commons.math3.linear.AbstractRealMatrix.multiplyEntry_601", _mut24824, _mut24825, _mut24826, _mut24827));
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix transpose() {
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final RealMatrix out = createMatrix(nCols, nRows);
        walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void visit(final int row, final int column, final double value) {
                out.setEntry(column, row, value);
            }
        });
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSquare() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.isSquare_626");
        return ROR_equals(getColumnDimension(), getRowDimension(), "org.apache.commons.math3.linear.AbstractRealMatrix.isSquare_626", _mut24828, _mut24829, _mut24830, _mut24831, _mut24832);
    }

    /**
     * Returns the number of rows of this matrix.
     *
     * @return the number of rows.
     */
    @Override
    public abstract int getRowDimension();

    /**
     * Returns the number of columns of this matrix.
     *
     * @return the number of columns.
     */
    @Override
    public abstract int getColumnDimension();

    /**
     * {@inheritDoc}
     */
    public double getTrace() throws NonSquareMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getTrace_647");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(nRows, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.getTrace_647", _mut24833, _mut24834, _mut24835, _mut24836, _mut24837)) {
            throw new NonSquareMatrixException(nRows, nCols);
        }
        double trace = 0;
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.getTrace_647", _mut24838, _mut24839, _mut24840, _mut24841, _mut24842); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.getTrace_647");
            trace += getEntry(i, i);
        }
        return trace;
    }

    /**
     * {@inheritDoc}
     */
    public double[] operate(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_661");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_661", _mut24843, _mut24844, _mut24845, _mut24846, _mut24847)) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        final double[] out = new double[nRows];
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_661", _mut24857, _mut24858, _mut24859, _mut24860, _mut24861); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_661");
            double sum = 0;
            for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_661", _mut24852, _mut24853, _mut24854, _mut24855, _mut24856); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_661");
                sum += AOR_multiply(getEntry(row, i), v[i], "org.apache.commons.math3.linear.AbstractRealMatrix.operate_661", _mut24848, _mut24849, _mut24850, _mut24851);
            }
            out[row] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector operate(final RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_682");
        try {
            return new ArrayRealVector(operate(((ArrayRealVector) v).getDataRef()), false);
        } catch (ClassCastException cce) {
            final int nRows = getRowDimension();
            final int nCols = getColumnDimension();
            if (ROR_not_equals(v.getDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_682", _mut24862, _mut24863, _mut24864, _mut24865, _mut24866)) {
                throw new DimensionMismatchException(v.getDimension(), nCols);
            }
            final double[] out = new double[nRows];
            for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_682", _mut24876, _mut24877, _mut24878, _mut24879, _mut24880); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_682");
                double sum = 0;
                for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.operate_682", _mut24871, _mut24872, _mut24873, _mut24874, _mut24875); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.operate_682");
                    sum += AOR_multiply(getEntry(row, i), v.getEntry(i), "org.apache.commons.math3.linear.AbstractRealMatrix.operate_682", _mut24867, _mut24868, _mut24869, _mut24870);
                }
                out[row] = sum;
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] preMultiply(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708", _mut24881, _mut24882, _mut24883, _mut24884, _mut24885)) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        final double[] out = new double[nCols];
        for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708", _mut24895, _mut24896, _mut24897, _mut24898, _mut24899); ++col) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708");
            double sum = 0;
            for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708", _mut24890, _mut24891, _mut24892, _mut24893, _mut24894); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708");
                sum += AOR_multiply(getEntry(i, col), v[i], "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_708", _mut24886, _mut24887, _mut24888, _mut24889);
            }
            out[col] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public RealVector preMultiply(final RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729");
        try {
            return new ArrayRealVector(preMultiply(((ArrayRealVector) v).getDataRef()), false);
        } catch (ClassCastException cce) {
            final int nRows = getRowDimension();
            final int nCols = getColumnDimension();
            if (ROR_not_equals(v.getDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729", _mut24900, _mut24901, _mut24902, _mut24903, _mut24904)) {
                throw new DimensionMismatchException(v.getDimension(), nRows);
            }
            final double[] out = new double[nCols];
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729", _mut24914, _mut24915, _mut24916, _mut24917, _mut24918); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729");
                double sum = 0;
                for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729", _mut24909, _mut24910, _mut24911, _mut24912, _mut24913); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729");
                    sum += AOR_multiply(getEntry(i, col), v.getEntry(i), "org.apache.commons.math3.linear.AbstractRealMatrix.preMultiply_729", _mut24905, _mut24906, _mut24907, _mut24908);
                }
                out[col] = sum;
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754", _mut24919, _mut24920, _mut24921, _mut24922), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754", _mut24923, _mut24924, _mut24925, _mut24926));
        for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754", _mut24932, _mut24933, _mut24934, _mut24935, _mut24936); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754");
            for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754", _mut24927, _mut24928, _mut24929, _mut24930, _mut24931); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_754");
                final double oldValue = getEntry(row, column);
                final double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769", _mut24937, _mut24938, _mut24939, _mut24940), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769", _mut24941, _mut24942, _mut24943, _mut24944));
        for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769", _mut24950, _mut24951, _mut24952, _mut24953, _mut24954); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769");
            for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769", _mut24945, _mut24946, _mut24947, _mut24948, _mut24949); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_769");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_782");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_782", _mut24960, _mut24961, _mut24962, _mut24963, _mut24964); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_782");
            for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_782", _mut24955, _mut24956, _mut24957, _mut24958, _mut24959); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_782");
                final double oldValue = getEntry(row, column);
                final double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_800");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_800", _mut24970, _mut24971, _mut24972, _mut24973, _mut24974); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_800");
            for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_800", _mut24965, _mut24966, _mut24967, _mut24968, _mut24969); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInRowOrder_800");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816", _mut24975, _mut24976, _mut24977, _mut24978), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816", _mut24979, _mut24980, _mut24981, _mut24982));
        for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816", _mut24988, _mut24989, _mut24990, _mut24991, _mut24992); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816");
            for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816", _mut24983, _mut24984, _mut24985, _mut24986, _mut24987); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_816");
                final double oldValue = getEntry(row, column);
                final double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831", _mut24993, _mut24994, _mut24995, _mut24996), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831", _mut24997, _mut24998, _mut24999, _mut25000));
        for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831", _mut25006, _mut25007, _mut25008, _mut25009, _mut25010); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831");
            for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831", _mut25001, _mut25002, _mut25003, _mut25004, _mut25005); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_831");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_844");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_844", _mut25016, _mut25017, _mut25018, _mut25019, _mut25020); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_844");
            for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_844", _mut25011, _mut25012, _mut25013, _mut25014, _mut25015); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_844");
                final double oldValue = getEntry(row, column);
                final double newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_862");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_862", _mut25026, _mut25027, _mut25028, _mut25029, _mut25030); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_862");
            for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_862", _mut25021, _mut25022, _mut25023, _mut25024, _mut25025); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.walkInColumnOrder_862");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor) {
        return walkInRowOrder(visitor);
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor) {
        return walkInRowOrder(visitor);
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    /**
     * Get a string representation for this matrix.
     * @return a string representation for this matrix
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.toString_909");
        final StringBuilder res = new StringBuilder();
        String fullClassName = getClass().getName();
        String shortClassName = fullClassName.substring(AOR_plus(fullClassName.lastIndexOf('.'), 1, "org.apache.commons.math3.linear.AbstractRealMatrix.toString_909", _mut25031, _mut25032, _mut25033, _mut25034));
        res.append(shortClassName);
        res.append(DEFAULT_FORMAT.format(this));
        return res.toString();
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>RealMatrix</code> instance with the same dimensions as this
     * and all corresponding matrix entries are equal.
     *
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(final Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.equals_927");
        if (object == this) {
            return true;
        }
        if (object instanceof RealMatrix == false) {
            return false;
        }
        RealMatrix m = (RealMatrix) object;
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if ((_mut25045 ? (ROR_not_equals(m.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25035, _mut25036, _mut25037, _mut25038, _mut25039) && ROR_not_equals(m.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25040, _mut25041, _mut25042, _mut25043, _mut25044)) : (ROR_not_equals(m.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25035, _mut25036, _mut25037, _mut25038, _mut25039) || ROR_not_equals(m.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25040, _mut25041, _mut25042, _mut25043, _mut25044)))) {
            return false;
        }
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25056, _mut25057, _mut25058, _mut25059, _mut25060); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.equals_927");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25051, _mut25052, _mut25053, _mut25054, _mut25055); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.equals_927");
                if (ROR_not_equals(getEntry(row, col), m.getEntry(row, col), "org.apache.commons.math3.linear.AbstractRealMatrix.equals_927", _mut25046, _mut25047, _mut25048, _mut25049, _mut25050)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Computes a hashcode for the matrix.
     *
     * @return hashcode for matrix
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956");
        int ret = 7;
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25061, _mut25062, _mut25063, _mut25064), nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25065, _mut25066, _mut25067, _mut25068);
        ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25069, _mut25070, _mut25071, _mut25072), nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25073, _mut25074, _mut25075, _mut25076);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25114, _mut25115, _mut25116, _mut25117, _mut25118); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25109, _mut25110, _mut25111, _mut25112, _mut25113); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956");
                ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25077, _mut25078, _mut25079, _mut25080), AOR_multiply((AOR_plus(AOR_multiply(11, (AOR_plus(row, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25081, _mut25082, _mut25083, _mut25084)), "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25085, _mut25086, _mut25087, _mut25088), AOR_multiply(17, (AOR_plus(col, 1, "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25089, _mut25090, _mut25091, _mut25092)), "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25093, _mut25094, _mut25095, _mut25096), "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25097, _mut25098, _mut25099, _mut25100)), MathUtils.hash(getEntry(row, col)), "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25101, _mut25102, _mut25103, _mut25104), "org.apache.commons.math3.linear.AbstractRealMatrix.hashCode_956", _mut25105, _mut25106, _mut25107, _mut25108);
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public abstract RealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException;

    /**
     * {@inheritDoc}
     */
    public abstract RealMatrix copy();

    /**
     * {@inheritDoc}
     */
    public abstract double getEntry(int row, int column) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void setEntry(int row, int column, double value) throws OutOfRangeException;
}
