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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A collection of static methods that operate on or return matrices.
 */
public class MatrixUtils {

    @Conditional
    public static boolean _mut36672 = false, _mut36673 = false, _mut36674 = false, _mut36675 = false, _mut36676 = false, _mut36677 = false, _mut36678 = false, _mut36679 = false, _mut36680 = false, _mut36681 = false, _mut36682 = false, _mut36683 = false, _mut36684 = false, _mut36685 = false, _mut36686 = false, _mut36687 = false, _mut36688 = false, _mut36689 = false, _mut36690 = false, _mut36691 = false, _mut36692 = false, _mut36693 = false, _mut36694 = false, _mut36695 = false, _mut36696 = false, _mut36697 = false, _mut36698 = false, _mut36699 = false, _mut36700 = false, _mut36701 = false, _mut36702 = false, _mut36703 = false, _mut36704 = false, _mut36705 = false, _mut36706 = false, _mut36707 = false, _mut36708 = false, _mut36709 = false, _mut36710 = false, _mut36711 = false, _mut36712 = false, _mut36713 = false, _mut36714 = false, _mut36715 = false, _mut36716 = false, _mut36717 = false, _mut36718 = false, _mut36719 = false, _mut36720 = false, _mut36721 = false, _mut36722 = false, _mut36723 = false, _mut36724 = false, _mut36725 = false, _mut36726 = false, _mut36727 = false, _mut36728 = false, _mut36729 = false, _mut36730 = false, _mut36731 = false, _mut36732 = false, _mut36733 = false, _mut36734 = false, _mut36735 = false, _mut36736 = false, _mut36737 = false, _mut36738 = false, _mut36739 = false, _mut36740 = false, _mut36741 = false, _mut36742 = false, _mut36743 = false, _mut36744 = false, _mut36745 = false, _mut36746 = false, _mut36747 = false, _mut36748 = false, _mut36749 = false, _mut36750 = false, _mut36751 = false, _mut36752 = false, _mut36753 = false, _mut36754 = false, _mut36755 = false, _mut36756 = false, _mut36757 = false, _mut36758 = false, _mut36759 = false, _mut36760 = false, _mut36761 = false, _mut36762 = false, _mut36763 = false, _mut36764 = false, _mut36765 = false, _mut36766 = false, _mut36767 = false, _mut36768 = false, _mut36769 = false, _mut36770 = false, _mut36771 = false, _mut36772 = false, _mut36773 = false, _mut36774 = false, _mut36775 = false, _mut36776 = false, _mut36777 = false, _mut36778 = false, _mut36779 = false, _mut36780 = false, _mut36781 = false, _mut36782 = false, _mut36783 = false, _mut36784 = false, _mut36785 = false, _mut36786 = false, _mut36787 = false, _mut36788 = false, _mut36789 = false, _mut36790 = false, _mut36791 = false, _mut36792 = false, _mut36793 = false, _mut36794 = false, _mut36795 = false, _mut36796 = false, _mut36797 = false, _mut36798 = false, _mut36799 = false, _mut36800 = false, _mut36801 = false, _mut36802 = false, _mut36803 = false, _mut36804 = false, _mut36805 = false, _mut36806 = false, _mut36807 = false, _mut36808 = false, _mut36809 = false, _mut36810 = false, _mut36811 = false, _mut36812 = false, _mut36813 = false, _mut36814 = false, _mut36815 = false, _mut36816 = false, _mut36817 = false, _mut36818 = false, _mut36819 = false, _mut36820 = false, _mut36821 = false, _mut36822 = false, _mut36823 = false, _mut36824 = false, _mut36825 = false, _mut36826 = false, _mut36827 = false, _mut36828 = false, _mut36829 = false, _mut36830 = false, _mut36831 = false, _mut36832 = false, _mut36833 = false, _mut36834 = false, _mut36835 = false, _mut36836 = false, _mut36837 = false, _mut36838 = false, _mut36839 = false, _mut36840 = false, _mut36841 = false, _mut36842 = false, _mut36843 = false, _mut36844 = false, _mut36845 = false, _mut36846 = false, _mut36847 = false, _mut36848 = false, _mut36849 = false, _mut36850 = false, _mut36851 = false, _mut36852 = false, _mut36853 = false, _mut36854 = false, _mut36855 = false, _mut36856 = false, _mut36857 = false, _mut36858 = false, _mut36859 = false, _mut36860 = false, _mut36861 = false, _mut36862 = false, _mut36863 = false, _mut36864 = false, _mut36865 = false, _mut36866 = false, _mut36867 = false, _mut36868 = false, _mut36869 = false, _mut36870 = false, _mut36871 = false, _mut36872 = false, _mut36873 = false, _mut36874 = false, _mut36875 = false, _mut36876 = false, _mut36877 = false, _mut36878 = false, _mut36879 = false, _mut36880 = false, _mut36881 = false, _mut36882 = false, _mut36883 = false, _mut36884 = false, _mut36885 = false, _mut36886 = false, _mut36887 = false, _mut36888 = false, _mut36889 = false, _mut36890 = false, _mut36891 = false, _mut36892 = false, _mut36893 = false, _mut36894 = false, _mut36895 = false, _mut36896 = false, _mut36897 = false, _mut36898 = false, _mut36899 = false, _mut36900 = false, _mut36901 = false, _mut36902 = false, _mut36903 = false, _mut36904 = false, _mut36905 = false, _mut36906 = false, _mut36907 = false, _mut36908 = false, _mut36909 = false, _mut36910 = false, _mut36911 = false, _mut36912 = false, _mut36913 = false, _mut36914 = false, _mut36915 = false, _mut36916 = false, _mut36917 = false, _mut36918 = false, _mut36919 = false, _mut36920 = false, _mut36921 = false, _mut36922 = false, _mut36923 = false, _mut36924 = false, _mut36925 = false, _mut36926 = false, _mut36927 = false, _mut36928 = false, _mut36929 = false, _mut36930 = false, _mut36931 = false, _mut36932 = false, _mut36933 = false, _mut36934 = false, _mut36935 = false, _mut36936 = false, _mut36937 = false, _mut36938 = false, _mut36939 = false, _mut36940 = false, _mut36941 = false, _mut36942 = false, _mut36943 = false, _mut36944 = false, _mut36945 = false, _mut36946 = false, _mut36947 = false, _mut36948 = false, _mut36949 = false, _mut36950 = false, _mut36951 = false, _mut36952 = false, _mut36953 = false, _mut36954 = false, _mut36955 = false, _mut36956 = false, _mut36957 = false, _mut36958 = false, _mut36959 = false, _mut36960 = false, _mut36961 = false, _mut36962 = false, _mut36963 = false, _mut36964 = false, _mut36965 = false, _mut36966 = false, _mut36967 = false, _mut36968 = false, _mut36969 = false, _mut36970 = false, _mut36971 = false, _mut36972 = false, _mut36973 = false, _mut36974 = false, _mut36975 = false, _mut36976 = false, _mut36977 = false, _mut36978 = false, _mut36979 = false, _mut36980 = false, _mut36981 = false, _mut36982 = false, _mut36983 = false, _mut36984 = false, _mut36985 = false, _mut36986 = false, _mut36987 = false, _mut36988 = false, _mut36989 = false, _mut36990 = false, _mut36991 = false, _mut36992 = false, _mut36993 = false, _mut36994 = false, _mut36995 = false, _mut36996 = false, _mut36997 = false, _mut36998 = false, _mut36999 = false, _mut37000 = false, _mut37001 = false, _mut37002 = false;

    /**
     * The default format for {@link RealMatrix} objects.
     * @since 3.1
     */
    public static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance();

    /**
     * A format for {@link RealMatrix} objects compatible with octave.
     * @since 3.1
     */
    public static final RealMatrixFormat OCTAVE_FORMAT = new RealMatrixFormat("[", "]", "", "", "; ", ", ");

    /**
     * Private constructor.
     */
    private MatrixUtils() {
        super();
    }

    /**
     * Returns a {@link RealMatrix} with specified dimensions.
     * <p>The type of matrix returned depends on the dimension. Below
     * 2<sup>12</sup> elements (i.e. 4096 elements or 64&times;64 for a
     * square matrix) which can be stored in a 32kB array, a {@link
     * Array2DRowRealMatrix} instance is built. Above this threshold a {@link
     * BlockRealMatrix} instance is built.</p>
     * <p>The matrix elements are all set to 0.0.</p>
     * @param rows number of rows of the matrix
     * @param columns number of columns of the matrix
     * @return  RealMatrix with specified dimensions
     * @see #createRealMatrix(double[][])
     */
    public static RealMatrix createRealMatrix(final int rows, final int columns) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_80");
        return (ROR_less_equals(AOR_multiply(rows, columns, "org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_80", _mut36672, _mut36673, _mut36674, _mut36675), 4096, "org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_80", _mut36676, _mut36677, _mut36678, _mut36679, _mut36680)) ? new Array2DRowRealMatrix(rows, columns) : new BlockRealMatrix(rows, columns);
    }

    /**
     * Returns a {@link FieldMatrix} with specified dimensions.
     * <p>The type of matrix returned depends on the dimension. Below
     * 2<sup>12</sup> elements (i.e. 4096 elements or 64&times;64 for a
     * square matrix), a {@link FieldMatrix} instance is built. Above
     * this threshold a {@link BlockFieldMatrix} instance is built.</p>
     * <p>The matrix elements are all set to field.getZero().</p>
     * @param <T> the type of the field elements
     * @param field field to which the matrix elements belong
     * @param rows number of rows of the matrix
     * @param columns number of columns of the matrix
     * @return  FieldMatrix with specified dimensions
     * @see #createFieldMatrix(FieldElement[][])
     * @since 2.0
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(final Field<T> field, final int rows, final int columns) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_100");
        return (ROR_less_equals(AOR_multiply(rows, columns, "org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_100", _mut36681, _mut36682, _mut36683, _mut36684), 4096, "org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_100", _mut36685, _mut36686, _mut36687, _mut36688, _mut36689)) ? new Array2DRowFieldMatrix<T>(field, rows, columns) : new BlockFieldMatrix<T>(field, rows, columns);
    }

    /**
     * Returns a {@link RealMatrix} whose entries are the the values in the
     * the input array.
     * <p>The type of matrix returned depends on the dimension. Below
     * 2<sup>12</sup> elements (i.e. 4096 elements or 64&times;64 for a
     * square matrix) which can be stored in a 32kB array, a {@link
     * Array2DRowRealMatrix} instance is built. Above this threshold a {@link
     * BlockRealMatrix} instance is built.</p>
     * <p>The input array is copied, not referenced.</p>
     *
     * @param data input array
     * @return  RealMatrix containing the values of the array
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if {@code data} is not rectangular (not all rows have the same length).
     * @throws NoDataException if a row or column is empty.
     * @throws NullArgumentException if either {@code data} or {@code data[0]}
     * is {@code null}.
     * @throws DimensionMismatchException if {@code data} is not rectangular.
     * @see #createRealMatrix(int, int)
     */
    public static RealMatrix createRealMatrix(double[][] data) throws NullArgumentException, DimensionMismatchException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_127");
        if ((_mut36690 ? (data == null && data[0] == null) : (data == null || data[0] == null))) {
            throw new NullArgumentException();
        }
        return (ROR_less_equals(AOR_multiply(data.length, data[0].length, "org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_127", _mut36691, _mut36692, _mut36693, _mut36694), 4096, "org.apache.commons.math3.linear.MatrixUtils.createRealMatrix_127", _mut36695, _mut36696, _mut36697, _mut36698, _mut36699)) ? new Array2DRowRealMatrix(data) : new BlockRealMatrix(data);
    }

    /**
     * Returns a {@link FieldMatrix} whose entries are the the values in the
     * the input array.
     * <p>The type of matrix returned depends on the dimension. Below
     * 2<sup>12</sup> elements (i.e. 4096 elements or 64&times;64 for a
     * square matrix), a {@link FieldMatrix} instance is built. Above
     * this threshold a {@link BlockFieldMatrix} instance is built.</p>
     * <p>The input array is copied, not referenced.</p>
     * @param <T> the type of the field elements
     * @param data input array
     * @return a matrix containing the values of the array.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if {@code data} is not rectangular (not all rows have the same length).
     * @throws NoDataException if a row or column is empty.
     * @throws NullArgumentException if either {@code data} or {@code data[0]}
     * is {@code null}.
     * @see #createFieldMatrix(Field, int, int)
     * @since 2.0
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(T[][] data) throws DimensionMismatchException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_157");
        if ((_mut36700 ? (data == null && data[0] == null) : (data == null || data[0] == null))) {
            throw new NullArgumentException();
        }
        return (ROR_less_equals(AOR_multiply(data.length, data[0].length, "org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_157", _mut36701, _mut36702, _mut36703, _mut36704), 4096, "org.apache.commons.math3.linear.MatrixUtils.createFieldMatrix_157", _mut36705, _mut36706, _mut36707, _mut36708, _mut36709)) ? new Array2DRowFieldMatrix<T>(data) : new BlockFieldMatrix<T>(data);
    }

    /**
     * Returns <code>dimension x dimension</code> identity matrix.
     *
     * @param dimension dimension of identity matrix to generate
     * @return identity matrix
     * @throws IllegalArgumentException if dimension is not positive
     * @since 1.1
     */
    public static RealMatrix createRealIdentityMatrix(int dimension) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealIdentityMatrix_175");
        final RealMatrix m = createRealMatrix(dimension, dimension);
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.linear.MatrixUtils.createRealIdentityMatrix_175", _mut36710, _mut36711, _mut36712, _mut36713, _mut36714); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealIdentityMatrix_175");
            m.setEntry(i, i, 1.0);
        }
        return m;
    }

    /**
     * Returns <code>dimension x dimension</code> identity matrix.
     *
     * @param <T> the type of the field elements
     * @param field field to which the elements belong
     * @param dimension dimension of identity matrix to generate
     * @return identity matrix
     * @throws IllegalArgumentException if dimension is not positive
     * @since 2.0
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldIdentityMatrix(final Field<T> field, final int dimension) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldIdentityMatrix_193");
        final T zero = field.getZero();
        final T one = field.getOne();
        final T[][] d = MathArrays.buildArray(field, dimension, dimension);
        for (int row = 0; ROR_less(row, dimension, "org.apache.commons.math3.linear.MatrixUtils.createFieldIdentityMatrix_193", _mut36715, _mut36716, _mut36717, _mut36718, _mut36719); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldIdentityMatrix_193");
            final T[] dRow = d[row];
            Arrays.fill(dRow, zero);
            dRow[row] = one;
        }
        return new Array2DRowFieldMatrix<T>(field, d, false);
    }

    /**
     * Returns a diagonal matrix with specified elements.
     *
     * @param diagonal diagonal elements of the matrix (the array elements
     * will be copied)
     * @return diagonal matrix
     * @since 2.0
     */
    public static RealMatrix createRealDiagonalMatrix(final double[] diagonal) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealDiagonalMatrix_214");
        final RealMatrix m = createRealMatrix(diagonal.length, diagonal.length);
        for (int i = 0; ROR_less(i, diagonal.length, "org.apache.commons.math3.linear.MatrixUtils.createRealDiagonalMatrix_214", _mut36720, _mut36721, _mut36722, _mut36723, _mut36724); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRealDiagonalMatrix_214");
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    /**
     * Returns a diagonal matrix with specified elements.
     *
     * @param <T> the type of the field elements
     * @param diagonal diagonal elements of the matrix (the array elements
     * will be copied)
     * @return diagonal matrix
     * @since 2.0
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldDiagonalMatrix(final T[] diagonal) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldDiagonalMatrix_231");
        final FieldMatrix<T> m = createFieldMatrix(diagonal[0].getField(), diagonal.length, diagonal.length);
        for (int i = 0; ROR_less(i, diagonal.length, "org.apache.commons.math3.linear.MatrixUtils.createFieldDiagonalMatrix_231", _mut36725, _mut36726, _mut36727, _mut36728, _mut36729); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldDiagonalMatrix_231");
            m.setEntry(i, i, diagonal[i]);
        }
        return m;
    }

    /**
     * Creates a {@link RealVector} using the data from the input array.
     *
     * @param data the input data
     * @return a data.length RealVector
     * @throws NoDataException if {@code data} is empty.
     * @throws NullArgumentException if {@code data} is {@code null}.
     */
    public static RealVector createRealVector(double[] data) throws NoDataException, NullArgumentException {
        if (data == null) {
            throw new NullArgumentException();
        }
        return new ArrayRealVector(data, true);
    }

    /**
     * Creates a {@link FieldVector} using the data from the input array.
     *
     * @param <T> the type of the field elements
     * @param data the input data
     * @return a data.length FieldVector
     * @throws NoDataException if {@code data} is empty.
     * @throws NullArgumentException if {@code data} is {@code null}.
     * @throws ZeroException if {@code data} has 0 elements
     */
    public static <T extends FieldElement<T>> FieldVector<T> createFieldVector(final T[] data) throws NoDataException, NullArgumentException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createFieldVector_267");
        if (data == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(data.length, 0, "org.apache.commons.math3.linear.MatrixUtils.createFieldVector_267", _mut36730, _mut36731, _mut36732, _mut36733, _mut36734)) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
        }
        return new ArrayFieldVector<T>(data[0].getField(), data, true);
    }

    /**
     * Create a row {@link RealMatrix} using the data from the input
     * array.
     *
     * @param rowData the input row data
     * @return a 1 x rowData.length RealMatrix
     * @throws NoDataException if {@code rowData} is empty.
     * @throws NullArgumentException if {@code rowData} is {@code null}.
     */
    public static RealMatrix createRowRealMatrix(double[] rowData) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRowRealMatrix_287");
        if (rowData == null) {
            throw new NullArgumentException();
        }
        final int nCols = rowData.length;
        final RealMatrix m = createRealMatrix(1, nCols);
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.MatrixUtils.createRowRealMatrix_287", _mut36735, _mut36736, _mut36737, _mut36738, _mut36739); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRowRealMatrix_287");
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    /**
     * Create a row {@link FieldMatrix} using the data from the input
     * array.
     *
     * @param <T> the type of the field elements
     * @param rowData the input row data
     * @return a 1 x rowData.length FieldMatrix
     * @throws NoDataException if {@code rowData} is empty.
     * @throws NullArgumentException if {@code rowData} is {@code null}.
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createRowFieldMatrix(final T[] rowData) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRowFieldMatrix_310");
        if (rowData == null) {
            throw new NullArgumentException();
        }
        final int nCols = rowData.length;
        if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.MatrixUtils.createRowFieldMatrix_310", _mut36740, _mut36741, _mut36742, _mut36743, _mut36744)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        final FieldMatrix<T> m = createFieldMatrix(rowData[0].getField(), 1, nCols);
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.MatrixUtils.createRowFieldMatrix_310", _mut36745, _mut36746, _mut36747, _mut36748, _mut36749); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createRowFieldMatrix_310");
            m.setEntry(0, i, rowData[i]);
        }
        return m;
    }

    /**
     * Creates a column {@link RealMatrix} using the data from the input
     * array.
     *
     * @param columnData  the input column data
     * @return a columnData x 1 RealMatrix
     * @throws NoDataException if {@code columnData} is empty.
     * @throws NullArgumentException if {@code columnData} is {@code null}.
     */
    public static RealMatrix createColumnRealMatrix(double[] columnData) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createColumnRealMatrix_336");
        if (columnData == null) {
            throw new NullArgumentException();
        }
        final int nRows = columnData.length;
        final RealMatrix m = createRealMatrix(nRows, 1);
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.MatrixUtils.createColumnRealMatrix_336", _mut36750, _mut36751, _mut36752, _mut36753, _mut36754); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createColumnRealMatrix_336");
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    /**
     * Creates a column {@link FieldMatrix} using the data from the input
     * array.
     *
     * @param <T> the type of the field elements
     * @param columnData  the input column data
     * @return a columnData x 1 FieldMatrix
     * @throws NoDataException if {@code data} is empty.
     * @throws NullArgumentException if {@code columnData} is {@code null}.
     */
    public static <T extends FieldElement<T>> FieldMatrix<T> createColumnFieldMatrix(final T[] columnData) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createColumnFieldMatrix_359");
        if (columnData == null) {
            throw new NullArgumentException();
        }
        final int nRows = columnData.length;
        if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.MatrixUtils.createColumnFieldMatrix_359", _mut36755, _mut36756, _mut36757, _mut36758, _mut36759)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        final FieldMatrix<T> m = createFieldMatrix(columnData[0].getField(), nRows, 1);
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.MatrixUtils.createColumnFieldMatrix_359", _mut36760, _mut36761, _mut36762, _mut36763, _mut36764); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.createColumnFieldMatrix_359");
            m.setEntry(i, 0, columnData[i]);
        }
        return m;
    }

    /**
     * Checks whether a matrix is symmetric, within a given relative tolerance.
     *
     * @param matrix Matrix to check.
     * @param relativeTolerance Tolerance of the symmetry check.
     * @param raiseException If {@code true}, an exception will be raised if
     * the matrix is not symmetric.
     * @return {@code true} if {@code matrix} is symmetric.
     * @throws NonSquareMatrixException if the matrix is not square.
     * @throws NonSymmetricMatrixException if the matrix is not symmetric.
     */
    private static boolean isSymmetricInternal(RealMatrix matrix, double relativeTolerance, boolean raiseException) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387");
        final int rows = matrix.getRowDimension();
        if (ROR_not_equals(rows, matrix.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36765, _mut36766, _mut36767, _mut36768, _mut36769)) {
            if (raiseException) {
                throw new NonSquareMatrixException(rows, matrix.getColumnDimension());
            } else {
                return false;
            }
        }
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36788, _mut36789, _mut36790, _mut36791, _mut36792); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387");
            for (int j = i + 1; ROR_less(j, rows, "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36783, _mut36784, _mut36785, _mut36786, _mut36787); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387");
                final double mij = matrix.getEntry(i, j);
                final double mji = matrix.getEntry(j, i);
                if (ROR_greater(FastMath.abs(AOR_minus(mij, mji, "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36770, _mut36771, _mut36772, _mut36773)), AOR_multiply(FastMath.max(FastMath.abs(mij), FastMath.abs(mji)), relativeTolerance, "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36774, _mut36775, _mut36776, _mut36777), "org.apache.commons.math3.linear.MatrixUtils.isSymmetricInternal_387", _mut36778, _mut36779, _mut36780, _mut36781, _mut36782)) {
                    if (raiseException) {
                        throw new NonSymmetricMatrixException(i, j, relativeTolerance);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks whether a matrix is symmetric.
     *
     * @param matrix Matrix to check.
     * @param eps Relative tolerance.
     * @throws NonSquareMatrixException if the matrix is not square.
     * @throws NonSymmetricMatrixException if the matrix is not symmetric.
     * @since 3.1
     */
    public static void checkSymmetric(RealMatrix matrix, double eps) {
        isSymmetricInternal(matrix, eps, true);
    }

    /**
     * Checks whether a matrix is symmetric.
     *
     * @param matrix Matrix to check.
     * @param eps Relative tolerance.
     * @return {@code true} if {@code matrix} is symmetric.
     * @since 3.1
     */
    public static boolean isSymmetric(RealMatrix matrix, double eps) {
        return isSymmetricInternal(matrix, eps, false);
    }

    /**
     * Check if matrix indices are valid.
     *
     * @param m Matrix.
     * @param row Row index to check.
     * @param column Column index to check.
     * @throws OutOfRangeException if {@code row} or {@code column} is not
     * a valid index.
     */
    public static void checkMatrixIndex(final AnyMatrix m, final int row, final int column) throws OutOfRangeException {
        checkRowIndex(m, row);
        checkColumnIndex(m, column);
    }

    /**
     * Check if a row index is valid.
     *
     * @param m Matrix.
     * @param row Row index to check.
     * @throws OutOfRangeException if {@code row} is not a valid index.
     */
    public static void checkRowIndex(final AnyMatrix m, final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465");
        if ((_mut36803 ? (ROR_less(row, 0, "org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465", _mut36793, _mut36794, _mut36795, _mut36796, _mut36797) && ROR_greater_equals(row, m.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465", _mut36798, _mut36799, _mut36800, _mut36801, _mut36802)) : (ROR_less(row, 0, "org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465", _mut36793, _mut36794, _mut36795, _mut36796, _mut36797) || ROR_greater_equals(row, m.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465", _mut36798, _mut36799, _mut36800, _mut36801, _mut36802)))) {
            throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, row, 0, AOR_minus(m.getRowDimension(), 1, "org.apache.commons.math3.linear.MatrixUtils.checkRowIndex_465", _mut36804, _mut36805, _mut36806, _mut36807));
        }
    }

    /**
     * Check if a column index is valid.
     *
     * @param m Matrix.
     * @param column Column index to check.
     * @throws OutOfRangeException if {@code column} is not a valid index.
     */
    public static void checkColumnIndex(final AnyMatrix m, final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481");
        if ((_mut36818 ? (ROR_less(column, 0, "org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481", _mut36808, _mut36809, _mut36810, _mut36811, _mut36812) && ROR_greater_equals(column, m.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481", _mut36813, _mut36814, _mut36815, _mut36816, _mut36817)) : (ROR_less(column, 0, "org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481", _mut36808, _mut36809, _mut36810, _mut36811, _mut36812) || ROR_greater_equals(column, m.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481", _mut36813, _mut36814, _mut36815, _mut36816, _mut36817)))) {
            throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, column, 0, AOR_minus(m.getColumnDimension(), 1, "org.apache.commons.math3.linear.MatrixUtils.checkColumnIndex_481", _mut36819, _mut36820, _mut36821, _mut36822));
        }
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to {@code n - 1}.
     *
     * @param m Matrix.
     * @param startRow Initial row index.
     * @param endRow Final row index.
     * @param startColumn Initial column index.
     * @param endColumn Final column index.
     * @throws OutOfRangeException if the indices are invalid.
     * @throws NumberIsTooSmallException if {@code endRow < startRow} or
     * {@code endColumn < startColumn}.
     */
    public static void checkSubMatrixIndex(final AnyMatrix m, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_502");
        checkRowIndex(m, startRow);
        checkRowIndex(m, endRow);
        if (ROR_less(endRow, startRow, "org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_502", _mut36823, _mut36824, _mut36825, _mut36826, _mut36827)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, endRow, startRow, false);
        }
        checkColumnIndex(m, startColumn);
        checkColumnIndex(m, endColumn);
        if (ROR_less(endColumn, startColumn, "org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_502", _mut36828, _mut36829, _mut36830, _mut36831, _mut36832)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, endColumn, startColumn, false);
        }
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to n-1.
     *
     * @param m Matrix.
     * @param selectedRows Array of row indices.
     * @param selectedColumns Array of column indices.
     * @throws NullArgumentException if {@code selectedRows} or
     * {@code selectedColumns} are {@code null}.
     * @throws NoDataException if the row or column selections are empty (zero
     * length).
     * @throws OutOfRangeException if row or column selections are not valid.
     */
    public static void checkSubMatrixIndex(final AnyMatrix m, final int[] selectedRows, final int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_536");
        if (selectedRows == null) {
            throw new NullArgumentException();
        }
        if (selectedColumns == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(selectedRows.length, 0, "org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_536", _mut36833, _mut36834, _mut36835, _mut36836, _mut36837)) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY);
        }
        if (ROR_equals(selectedColumns.length, 0, "org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_536", _mut36838, _mut36839, _mut36840, _mut36841, _mut36842)) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY);
        }
        for (final int row : selectedRows) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_536");
            checkRowIndex(m, row);
        }
        for (final int column : selectedColumns) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkSubMatrixIndex_536");
            checkColumnIndex(m, column);
        }
    }

    /**
     * Check if matrices are addition compatible.
     *
     * @param left Left hand side matrix.
     * @param right Right hand side matrix.
     * @throws MatrixDimensionMismatchException if the matrices are not addition
     * compatible.
     */
    public static void checkAdditionCompatible(final AnyMatrix left, final AnyMatrix right) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible_569");
        if ((_mut36853 ? ((ROR_not_equals(left.getRowDimension(), right.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible_569", _mut36843, _mut36844, _mut36845, _mut36846, _mut36847)) && (ROR_not_equals(left.getColumnDimension(), right.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible_569", _mut36848, _mut36849, _mut36850, _mut36851, _mut36852))) : ((ROR_not_equals(left.getRowDimension(), right.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible_569", _mut36843, _mut36844, _mut36845, _mut36846, _mut36847)) || (ROR_not_equals(left.getColumnDimension(), right.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkAdditionCompatible_569", _mut36848, _mut36849, _mut36850, _mut36851, _mut36852))))) {
            throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension());
        }
    }

    /**
     * Check if matrices are subtraction compatible
     *
     * @param left Left hand side matrix.
     * @param right Right hand side matrix.
     * @throws MatrixDimensionMismatchException if the matrices are not addition
     * compatible.
     */
    public static void checkSubtractionCompatible(final AnyMatrix left, final AnyMatrix right) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible_586");
        if ((_mut36864 ? ((ROR_not_equals(left.getRowDimension(), right.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible_586", _mut36854, _mut36855, _mut36856, _mut36857, _mut36858)) && (ROR_not_equals(left.getColumnDimension(), right.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible_586", _mut36859, _mut36860, _mut36861, _mut36862, _mut36863))) : ((ROR_not_equals(left.getRowDimension(), right.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible_586", _mut36854, _mut36855, _mut36856, _mut36857, _mut36858)) || (ROR_not_equals(left.getColumnDimension(), right.getColumnDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkSubtractionCompatible_586", _mut36859, _mut36860, _mut36861, _mut36862, _mut36863))))) {
            throw new MatrixDimensionMismatchException(left.getRowDimension(), left.getColumnDimension(), right.getRowDimension(), right.getColumnDimension());
        }
    }

    /**
     * Check if matrices are multiplication compatible
     *
     * @param left Left hand side matrix.
     * @param right Right hand side matrix.
     * @throws DimensionMismatchException if matrices are not multiplication
     * compatible.
     */
    public static void checkMultiplicationCompatible(final AnyMatrix left, final AnyMatrix right) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible_603");
        if (ROR_not_equals(left.getColumnDimension(), right.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.checkMultiplicationCompatible_603", _mut36865, _mut36866, _mut36867, _mut36868, _mut36869)) {
            throw new DimensionMismatchException(left.getColumnDimension(), right.getRowDimension());
        }
    }

    /**
     * Convert a {@link FieldMatrix}/{@link Fraction} matrix to a {@link RealMatrix}.
     * @param m Matrix to convert.
     * @return the converted matrix.
     */
    public static Array2DRowRealMatrix fractionMatrixToRealMatrix(final FieldMatrix<Fraction> m) {
        final FractionMatrixConverter converter = new FractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    /**
     * Converter for {@link FieldMatrix}/{@link Fraction}.
     */
    private static class FractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<Fraction> {

        /**
         * Converted array.
         */
        private double[][] data;

        /**
         * Simple constructor.
         */
        FractionMatrixConverter() {
            super(Fraction.ZERO);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            data = new double[rows][columns];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void visit(int row, int column, Fraction value) {
            data[row][column] = value.doubleValue();
        }

        /**
         * Get the converted matrix.
         *
         * @return the converted matrix.
         */
        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(data, false);
        }
    }

    /**
     * Convert a {@link FieldMatrix}/{@link BigFraction} matrix to a {@link RealMatrix}.
     *
     * @param m Matrix to convert.
     * @return the converted matrix.
     */
    public static Array2DRowRealMatrix bigFractionMatrixToRealMatrix(final FieldMatrix<BigFraction> m) {
        final BigFractionMatrixConverter converter = new BigFractionMatrixConverter();
        m.walkInOptimizedOrder(converter);
        return converter.getConvertedMatrix();
    }

    /**
     * Converter for {@link FieldMatrix}/{@link BigFraction}.
     */
    private static class BigFractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<BigFraction> {

        /**
         * Converted array.
         */
        private double[][] data;

        /**
         * Simple constructor.
         */
        BigFractionMatrixConverter() {
            super(BigFraction.ZERO);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            data = new double[rows][columns];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void visit(int row, int column, BigFraction value) {
            data[row][column] = value.doubleValue();
        }

        /**
         * Get the converted matrix.
         *
         * @return the converted matrix.
         */
        Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(data, false);
        }
    }

    /**
     * Serialize a {@link RealVector}.
     * <p>
     * This method is intended to be called from within a private
     * <code>writeObject</code> method (after a call to
     * <code>oos.defaultWriteObject()</code>) in a class that has a
     * {@link RealVector} field, which should be declared <code>transient</code>.
     * This way, the default handling does not serialize the vector (the {@link
     * RealVector} interface is not serializable by default) but this method does
     * serialize it specifically.
     * </p>
     * <p>
     * The following example shows how a simple class with a name and a real vector
     * should be written:
     * <pre><code>
     * public class NamedVector implements Serializable {
     *
     *     private final String name;
     *     private final transient RealVector coefficients;
     *
     *     // omitted constructors, getters ...
     *
     *     private void writeObject(ObjectOutputStream oos) throws IOException {
     *         oos.defaultWriteObject();  // takes care of name field
     *         MatrixUtils.serializeRealVector(coefficients, oos);
     *     }
     *
     *     private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
     *         ois.defaultReadObject();  // takes care of name field
     *         MatrixUtils.deserializeRealVector(this, "coefficients", ois);
     *     }
     *
     * }
     * </code></pre>
     * </p>
     *
     * @param vector real vector to serialize
     * @param oos stream where the real vector should be written
     * @exception IOException if object cannot be written to stream
     * @see #deserializeRealVector(Object, String, ObjectInputStream)
     */
    public static void serializeRealVector(final RealVector vector, final ObjectOutputStream oos) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.serializeRealVector_740");
        final int n = vector.getDimension();
        oos.writeInt(n);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.MatrixUtils.serializeRealVector_740", _mut36870, _mut36871, _mut36872, _mut36873, _mut36874); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.serializeRealVector_740");
            oos.writeDouble(vector.getEntry(i));
        }
    }

    /**
     * Deserialize  a {@link RealVector} field in a class.
     * <p>
     * This method is intended to be called from within a private
     * <code>readObject</code> method (after a call to
     * <code>ois.defaultReadObject()</code>) in a class that has a
     * {@link RealVector} field, which should be declared <code>transient</code>.
     * This way, the default handling does not deserialize the vector (the {@link
     * RealVector} interface is not serializable by default) but this method does
     * deserialize it specifically.
     * </p>
     * @param instance instance in which the field must be set up
     * @param fieldName name of the field within the class (may be private and final)
     * @param ois stream from which the real vector should be read
     * @exception ClassNotFoundException if a class in the stream cannot be found
     * @exception IOException if object cannot be read from the stream
     * @see #serializeRealVector(RealVector, ObjectOutputStream)
     */
    public static void deserializeRealVector(final Object instance, final String fieldName, final ObjectInputStream ois) throws ClassNotFoundException, IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.deserializeRealVector_767");
        try {
            // read the vector data
            final int n = ois.readInt();
            final double[] data = new double[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.MatrixUtils.deserializeRealVector_767", _mut36875, _mut36876, _mut36877, _mut36878, _mut36879); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.deserializeRealVector_767");
                data[i] = ois.readDouble();
            }
            // create the instance
            final RealVector vector = new ArrayRealVector(data, false);
            // set up the field
            final java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, vector);
        } catch (NoSuchFieldException nsfe) {
            IOException ioe = new IOException();
            ioe.initCause(nsfe);
            throw ioe;
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        }
    }

    /**
     * Serialize a {@link RealMatrix}.
     * <p>
     * This method is intended to be called from within a private
     * <code>writeObject</code> method (after a call to
     * <code>oos.defaultWriteObject()</code>) in a class that has a
     * {@link RealMatrix} field, which should be declared <code>transient</code>.
     * This way, the default handling does not serialize the matrix (the {@link
     * RealMatrix} interface is not serializable by default) but this method does
     * serialize it specifically.
     * </p>
     * <p>
     * The following example shows how a simple class with a name and a real matrix
     * should be written:
     * <pre><code>
     * public class NamedMatrix implements Serializable {
     *
     *     private final String name;
     *     private final transient RealMatrix coefficients;
     *
     *     // omitted constructors, getters ...
     *
     *     private void writeObject(ObjectOutputStream oos) throws IOException {
     *         oos.defaultWriteObject();  // takes care of name field
     *         MatrixUtils.serializeRealMatrix(coefficients, oos);
     *     }
     *
     *     private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
     *         ois.defaultReadObject();  // takes care of name field
     *         MatrixUtils.deserializeRealMatrix(this, "coefficients", ois);
     *     }
     *
     * }
     * </code></pre>
     * </p>
     *
     * @param matrix real matrix to serialize
     * @param oos stream where the real matrix should be written
     * @exception IOException if object cannot be written to stream
     * @see #deserializeRealMatrix(Object, String, ObjectInputStream)
     */
    public static void serializeRealMatrix(final RealMatrix matrix, final ObjectOutputStream oos) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.serializeRealMatrix_841");
        final int n = matrix.getRowDimension();
        final int m = matrix.getColumnDimension();
        oos.writeInt(n);
        oos.writeInt(m);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.MatrixUtils.serializeRealMatrix_841", _mut36885, _mut36886, _mut36887, _mut36888, _mut36889); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.serializeRealMatrix_841");
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.MatrixUtils.serializeRealMatrix_841", _mut36880, _mut36881, _mut36882, _mut36883, _mut36884); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.serializeRealMatrix_841");
                oos.writeDouble(matrix.getEntry(i, j));
            }
        }
    }

    /**
     * Deserialize  a {@link RealMatrix} field in a class.
     * <p>
     * This method is intended to be called from within a private
     * <code>readObject</code> method (after a call to
     * <code>ois.defaultReadObject()</code>) in a class that has a
     * {@link RealMatrix} field, which should be declared <code>transient</code>.
     * This way, the default handling does not deserialize the matrix (the {@link
     * RealMatrix} interface is not serializable by default) but this method does
     * deserialize it specifically.
     * </p>
     * @param instance instance in which the field must be set up
     * @param fieldName name of the field within the class (may be private and final)
     * @param ois stream from which the real matrix should be read
     * @exception ClassNotFoundException if a class in the stream cannot be found
     * @exception IOException if object cannot be read from the stream
     * @see #serializeRealMatrix(RealMatrix, ObjectOutputStream)
     */
    public static void deserializeRealMatrix(final Object instance, final String fieldName, final ObjectInputStream ois) throws ClassNotFoundException, IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.deserializeRealMatrix_872");
        try {
            // read the matrix data
            final int n = ois.readInt();
            final int m = ois.readInt();
            final double[][] data = new double[n][m];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.MatrixUtils.deserializeRealMatrix_872", _mut36895, _mut36896, _mut36897, _mut36898, _mut36899); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.deserializeRealMatrix_872");
                final double[] dataI = data[i];
                for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.MatrixUtils.deserializeRealMatrix_872", _mut36890, _mut36891, _mut36892, _mut36893, _mut36894); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.deserializeRealMatrix_872");
                    dataI[j] = ois.readDouble();
                }
            }
            // create the instance
            final RealMatrix matrix = new Array2DRowRealMatrix(data, false);
            // set up the field
            final java.lang.reflect.Field f = instance.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, matrix);
        } catch (NoSuchFieldException nsfe) {
            IOException ioe = new IOException();
            ioe.initCause(nsfe);
            throw ioe;
        } catch (IllegalAccessException iae) {
            IOException ioe = new IOException();
            ioe.initCause(iae);
            throw ioe;
        }
    }

    /**
     * Solve  a  system of composed of a Lower Triangular Matrix
     *  {@link RealMatrix}.
     *  <p>
     *  This method is called to solve systems of equations which are
     *  of the lower triangular form. The matrix {@link RealMatrix}
     *  is assumed, though not checked, to be in lower triangular form.
     *  The vector {@link RealVector} is overwritten with the solution.
     *  The matrix is checked that it is square and its dimensions match
     *  the length of the vector.
     *  </p>
     *  @param rm RealMatrix which is lower triangular
     *  @param b  RealVector this is overwritten
     *  @throws DimensionMismatchException if the matrix and vector are not
     *  conformable
     *  @throws NonSquareMatrixException if the matrix {@code rm} is not square
     *  @throws MathArithmeticException if the absolute value of one of the diagonal
     *  coefficient of {@code rm} is lower than {@link Precision#SAFE_MIN}
     */
    public static void solveLowerTriangularSystem(RealMatrix rm, RealVector b) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927");
        if ((_mut36906 ? ((_mut36900 ? ((rm == null) && (b == null)) : ((rm == null) || (b == null))) && (ROR_not_equals(rm.getRowDimension(), b.getDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36901, _mut36902, _mut36903, _mut36904, _mut36905))) : ((_mut36900 ? ((rm == null) && (b == null)) : ((rm == null) || (b == null))) || (ROR_not_equals(rm.getRowDimension(), b.getDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36901, _mut36902, _mut36903, _mut36904, _mut36905))))) {
            throw new DimensionMismatchException((rm == null) ? 0 : rm.getRowDimension(), (b == null) ? 0 : b.getDimension());
        }
        if (ROR_not_equals(rm.getColumnDimension(), rm.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36907, _mut36908, _mut36909, _mut36910, _mut36911)) {
            throw new NonSquareMatrixException(rm.getRowDimension(), rm.getColumnDimension());
        }
        int rows = rm.getRowDimension();
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36934, _mut36935, _mut36936, _mut36937, _mut36938); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927");
            double diag = rm.getEntry(i, i);
            if (ROR_less(FastMath.abs(diag), Precision.SAFE_MIN, "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36912, _mut36913, _mut36914, _mut36915, _mut36916)) {
                throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
            }
            double bi = AOR_divide(b.getEntry(i), diag, "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36917, _mut36918, _mut36919, _mut36920);
            b.setEntry(i, bi);
            for (int j = i + 1; ROR_less(j, rows, "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36929, _mut36930, _mut36931, _mut36932, _mut36933); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927");
                b.setEntry(j, AOR_minus(b.getEntry(j), AOR_multiply(bi, rm.getEntry(j, i), "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36921, _mut36922, _mut36923, _mut36924), "org.apache.commons.math3.linear.MatrixUtils.solveLowerTriangularSystem_927", _mut36925, _mut36926, _mut36927, _mut36928));
            }
        }
    }

    /**
     * Solver a  system composed  of an Upper Triangular Matrix
     * {@link RealMatrix}.
     * <p>
     * This method is called to solve systems of equations which are
     * of the lower triangular form. The matrix {@link RealMatrix}
     * is assumed, though not checked, to be in upper triangular form.
     * The vector {@link RealVector} is overwritten with the solution.
     * The matrix is checked that it is square and its dimensions match
     * the length of the vector.
     * </p>
     * @param rm RealMatrix which is upper triangular
     * @param b  RealVector this is overwritten
     * @throws DimensionMismatchException if the matrix and vector are not
     * conformable
     * @throws NonSquareMatrixException if the matrix {@code rm} is not
     * square
     * @throws MathArithmeticException if the absolute value of one of the diagonal
     * coefficient of {@code rm} is lower than {@link Precision#SAFE_MIN}
     */
    public static void solveUpperTriangularSystem(RealMatrix rm, RealVector b) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972");
        if ((_mut36945 ? ((_mut36939 ? ((rm == null) && (b == null)) : ((rm == null) || (b == null))) && (ROR_not_equals(rm.getRowDimension(), b.getDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36940, _mut36941, _mut36942, _mut36943, _mut36944))) : ((_mut36939 ? ((rm == null) && (b == null)) : ((rm == null) || (b == null))) || (ROR_not_equals(rm.getRowDimension(), b.getDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36940, _mut36941, _mut36942, _mut36943, _mut36944))))) {
            throw new DimensionMismatchException((rm == null) ? 0 : rm.getRowDimension(), (b == null) ? 0 : b.getDimension());
        }
        if (ROR_not_equals(rm.getColumnDimension(), rm.getRowDimension(), "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36946, _mut36947, _mut36948, _mut36949, _mut36950)) {
            throw new NonSquareMatrixException(rm.getRowDimension(), rm.getColumnDimension());
        }
        int rows = rm.getRowDimension();
        for (int i = rows - 1; ROR_greater(i, -1, "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36973, _mut36974, _mut36975, _mut36976, _mut36977); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972");
            double diag = rm.getEntry(i, i);
            if (ROR_less(FastMath.abs(diag), Precision.SAFE_MIN, "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36951, _mut36952, _mut36953, _mut36954, _mut36955)) {
                throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
            }
            double bi = AOR_divide(b.getEntry(i), diag, "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36956, _mut36957, _mut36958, _mut36959);
            b.setEntry(i, bi);
            for (int j = i - 1; ROR_greater(j, -1, "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36968, _mut36969, _mut36970, _mut36971, _mut36972); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972");
                b.setEntry(j, AOR_minus(b.getEntry(j), AOR_multiply(bi, rm.getEntry(j, i), "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36960, _mut36961, _mut36962, _mut36963), "org.apache.commons.math3.linear.MatrixUtils.solveUpperTriangularSystem_972", _mut36964, _mut36965, _mut36966, _mut36967));
            }
        }
    }

    /**
     * Computes the inverse of the given matrix by splitting it into
     * 4 sub-matrices.
     *
     * @param m Matrix whose inverse must be computed.
     * @param splitIndex Index that determines the "split" line and
     * column.
     * The element corresponding to this index will part of the
     * upper-left sub-matrix.
     * @return the inverse of {@code m}.
     * @throws NonSquareMatrixException if {@code m} is not square.
     */
    public static RealMatrix blockInverse(RealMatrix m, int splitIndex) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010");
        final int n = m.getRowDimension();
        if (ROR_not_equals(m.getColumnDimension(), n, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36978, _mut36979, _mut36980, _mut36981, _mut36982)) {
            throw new NonSquareMatrixException(m.getRowDimension(), m.getColumnDimension());
        }
        final int splitIndex1 = AOR_plus(splitIndex, 1, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36983, _mut36984, _mut36985, _mut36986);
        final RealMatrix a = m.getSubMatrix(0, splitIndex, 0, splitIndex);
        final RealMatrix b = m.getSubMatrix(0, splitIndex, splitIndex1, AOR_minus(n, 1, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36987, _mut36988, _mut36989, _mut36990));
        final RealMatrix c = m.getSubMatrix(splitIndex1, AOR_minus(n, 1, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36991, _mut36992, _mut36993, _mut36994), 0, splitIndex);
        final RealMatrix d = m.getSubMatrix(splitIndex1, AOR_minus(n, 1, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36995, _mut36996, _mut36997, _mut36998), splitIndex1, AOR_minus(n, 1, "org.apache.commons.math3.linear.MatrixUtils.blockInverse_1010", _mut36999, _mut37000, _mut37001, _mut37002));
        final SingularValueDecomposition aDec = new SingularValueDecomposition(a);
        final DecompositionSolver aSolver = aDec.getSolver();
        if (!aSolver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        final RealMatrix aInv = aSolver.getInverse();
        final SingularValueDecomposition dDec = new SingularValueDecomposition(d);
        final DecompositionSolver dSolver = dDec.getSolver();
        if (!dSolver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        final RealMatrix dInv = dSolver.getInverse();
        final RealMatrix tmp1 = a.subtract(b.multiply(dInv).multiply(c));
        final SingularValueDecomposition tmp1Dec = new SingularValueDecomposition(tmp1);
        final DecompositionSolver tmp1Solver = tmp1Dec.getSolver();
        if (!tmp1Solver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        final RealMatrix result00 = tmp1Solver.getInverse();
        final RealMatrix tmp2 = d.subtract(c.multiply(aInv).multiply(b));
        final SingularValueDecomposition tmp2Dec = new SingularValueDecomposition(tmp2);
        final DecompositionSolver tmp2Solver = tmp2Dec.getSolver();
        if (!tmp2Solver.isNonSingular()) {
            throw new SingularMatrixException();
        }
        final RealMatrix result11 = tmp2Solver.getInverse();
        final RealMatrix result01 = aInv.multiply(b).multiply(result11).scalarMultiply(-1);
        final RealMatrix result10 = dInv.multiply(c).multiply(result00).scalarMultiply(-1);
        final RealMatrix result = new Array2DRowRealMatrix(n, n);
        result.setSubMatrix(result00.getData(), 0, 0);
        result.setSubMatrix(result01.getData(), 0, splitIndex1);
        result.setSubMatrix(result10.getData(), splitIndex1, 0);
        result.setSubMatrix(result11.getData(), splitIndex1, splitIndex1);
        return result;
    }

    /**
     * Computes the inverse of the given matrix.
     * <p>
     * By default, the inverse of the matrix is computed using the QR-decomposition,
     * unless a more efficient method can be determined for the input matrix.
     * <p>
     * Note: this method will use a singularity threshold of 0,
     * use {@link #inverse(RealMatrix, double)} if a different threshold is needed.
     *
     * @param matrix Matrix whose inverse shall be computed
     * @return the inverse of {@code matrix}
     * @throws NullArgumentException if {@code matrix} is {@code null}
     * @throws SingularMatrixException if m is singular
     * @throws NonSquareMatrixException if matrix is not square
     * @since 3.3
     */
    public static RealMatrix inverse(RealMatrix matrix) throws NullArgumentException, SingularMatrixException, NonSquareMatrixException {
        return inverse(matrix, 0);
    }

    /**
     * Computes the inverse of the given matrix.
     * <p>
     * By default, the inverse of the matrix is computed using the QR-decomposition,
     * unless a more efficient method can be determined for the input matrix.
     *
     * @param matrix Matrix whose inverse shall be computed
     * @param threshold Singularity threshold
     * @return the inverse of {@code m}
     * @throws NullArgumentException if {@code matrix} is {@code null}
     * @throws SingularMatrixException if matrix is singular
     * @throws NonSquareMatrixException if matrix is not square
     * @since 3.3
     */
    public static RealMatrix inverse(RealMatrix matrix, double threshold) throws NullArgumentException, SingularMatrixException, NonSquareMatrixException {
        MathUtils.checkNotNull(matrix);
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        if (matrix instanceof DiagonalMatrix) {
            return ((DiagonalMatrix) matrix).inverse(threshold);
        } else {
            QRDecomposition decomposition = new QRDecomposition(matrix, threshold);
            return decomposition.getSolver().getInverse();
        }
    }
}
