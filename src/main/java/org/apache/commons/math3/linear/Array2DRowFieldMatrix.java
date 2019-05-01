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

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of FieldMatrix<T> using a {@link FieldElement}[][] array to store entries.
 * <p>
 * As specified in the {@link FieldMatrix} interface, matrix element indexing
 * is 0-based -- e.g., <code>getEntry(0, 0)</code>
 * returns the element in the first row, first column of the matrix.</li></ul>
 * </p>
 *
 * @param <T> the type of the field elements
 */
public class Array2DRowFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {

    @Conditional
    public static boolean _mut25119 = false, _mut25120 = false, _mut25121 = false, _mut25122 = false, _mut25123 = false, _mut25124 = false, _mut25125 = false, _mut25126 = false, _mut25127 = false, _mut25128 = false, _mut25129 = false, _mut25130 = false, _mut25131 = false, _mut25132 = false, _mut25133 = false, _mut25134 = false, _mut25135 = false, _mut25136 = false, _mut25137 = false, _mut25138 = false, _mut25139 = false, _mut25140 = false, _mut25141 = false, _mut25142 = false, _mut25143 = false, _mut25144 = false, _mut25145 = false, _mut25146 = false, _mut25147 = false, _mut25148 = false, _mut25149 = false, _mut25150 = false, _mut25151 = false, _mut25152 = false, _mut25153 = false, _mut25154 = false, _mut25155 = false, _mut25156 = false, _mut25157 = false, _mut25158 = false, _mut25159 = false, _mut25160 = false, _mut25161 = false, _mut25162 = false, _mut25163 = false, _mut25164 = false, _mut25165 = false, _mut25166 = false, _mut25167 = false, _mut25168 = false, _mut25169 = false, _mut25170 = false, _mut25171 = false, _mut25172 = false, _mut25173 = false, _mut25174 = false, _mut25175 = false, _mut25176 = false, _mut25177 = false, _mut25178 = false, _mut25179 = false, _mut25180 = false, _mut25181 = false, _mut25182 = false, _mut25183 = false, _mut25184 = false, _mut25185 = false, _mut25186 = false, _mut25187 = false, _mut25188 = false, _mut25189 = false, _mut25190 = false, _mut25191 = false, _mut25192 = false, _mut25193 = false, _mut25194 = false, _mut25195 = false, _mut25196 = false, _mut25197 = false, _mut25198 = false, _mut25199 = false, _mut25200 = false, _mut25201 = false, _mut25202 = false, _mut25203 = false, _mut25204 = false, _mut25205 = false, _mut25206 = false, _mut25207 = false, _mut25208 = false, _mut25209 = false, _mut25210 = false, _mut25211 = false, _mut25212 = false, _mut25213 = false, _mut25214 = false, _mut25215 = false, _mut25216 = false, _mut25217 = false, _mut25218 = false, _mut25219 = false, _mut25220 = false, _mut25221 = false, _mut25222 = false, _mut25223 = false, _mut25224 = false, _mut25225 = false, _mut25226 = false, _mut25227 = false, _mut25228 = false, _mut25229 = false, _mut25230 = false, _mut25231 = false, _mut25232 = false, _mut25233 = false, _mut25234 = false, _mut25235 = false, _mut25236 = false, _mut25237 = false, _mut25238 = false, _mut25239 = false, _mut25240 = false, _mut25241 = false, _mut25242 = false, _mut25243 = false, _mut25244 = false, _mut25245 = false, _mut25246 = false, _mut25247 = false, _mut25248 = false, _mut25249 = false, _mut25250 = false, _mut25251 = false, _mut25252 = false, _mut25253 = false, _mut25254 = false, _mut25255 = false, _mut25256 = false, _mut25257 = false, _mut25258 = false, _mut25259 = false, _mut25260 = false, _mut25261 = false, _mut25262 = false, _mut25263 = false, _mut25264 = false, _mut25265 = false, _mut25266 = false, _mut25267 = false, _mut25268 = false, _mut25269 = false, _mut25270 = false, _mut25271 = false, _mut25272 = false, _mut25273 = false, _mut25274 = false, _mut25275 = false, _mut25276 = false, _mut25277 = false, _mut25278 = false, _mut25279 = false, _mut25280 = false, _mut25281 = false, _mut25282 = false, _mut25283 = false, _mut25284 = false, _mut25285 = false, _mut25286 = false, _mut25287 = false, _mut25288 = false, _mut25289 = false, _mut25290 = false, _mut25291 = false, _mut25292 = false, _mut25293 = false, _mut25294 = false, _mut25295 = false, _mut25296 = false, _mut25297 = false, _mut25298 = false, _mut25299 = false, _mut25300 = false, _mut25301 = false, _mut25302 = false, _mut25303 = false, _mut25304 = false, _mut25305 = false, _mut25306 = false, _mut25307 = false, _mut25308 = false, _mut25309 = false, _mut25310 = false, _mut25311 = false, _mut25312 = false, _mut25313 = false, _mut25314 = false, _mut25315 = false, _mut25316 = false, _mut25317 = false, _mut25318 = false, _mut25319 = false, _mut25320 = false, _mut25321 = false, _mut25322 = false, _mut25323 = false, _mut25324 = false, _mut25325 = false, _mut25326 = false, _mut25327 = false, _mut25328 = false, _mut25329 = false, _mut25330 = false, _mut25331 = false, _mut25332 = false, _mut25333 = false, _mut25334 = false, _mut25335 = false, _mut25336 = false, _mut25337 = false, _mut25338 = false, _mut25339 = false, _mut25340 = false, _mut25341 = false, _mut25342 = false, _mut25343 = false, _mut25344 = false, _mut25345 = false, _mut25346 = false, _mut25347 = false, _mut25348 = false, _mut25349 = false, _mut25350 = false, _mut25351 = false, _mut25352 = false, _mut25353 = false, _mut25354 = false, _mut25355 = false, _mut25356 = false, _mut25357 = false, _mut25358 = false, _mut25359 = false, _mut25360 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 7260756672015356458L;

    /**
     * Entries of the matrix
     */
    private T[][] data;

    /**
     * Creates a matrix with no data
     * @param field field to which the elements belong
     */
    public Array2DRowFieldMatrix(final Field<T> field) {
        super(field);
    }

    /**
     * Create a new {@code FieldMatrix<T>} with the supplied row and column dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rowDimension Number of rows in the new matrix.
     * @param columnDimension Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not positive.
     */
    public Array2DRowFieldMatrix(final Field<T> field, final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        super(field, rowDimension, columnDimension);
        data = MathArrays.buildArray(field, rowDimension, columnDimension);
    }

    /**
     * Create a new {@code FieldMatrix<T>} using the input array as the underlying
     * data array.
     * <p>The input array is copied, not referenced. This constructor has
     * the same effect as calling {@link #Array2DRowFieldMatrix(FieldElement[][], boolean)}
     * with the second argument set to {@code true}.</p>
     *
     * @param d Data for the new matrix.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NoDataException if there are not at least one row and one column.
     * @see #Array2DRowFieldMatrix(FieldElement[][], boolean)
     */
    public Array2DRowFieldMatrix(final T[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
        this(extractField(d), d);
    }

    /**
     * Create a new {@code FieldMatrix<T>} using the input array as the underlying
     * data array.
     * <p>The input array is copied, not referenced. This constructor has
     * the same effect as calling {@link #Array2DRowFieldMatrix(FieldElement[][], boolean)}
     * with the second argument set to {@code true}.</p>
     *
     * @param field Field to which the elements belong.
     * @param d Data for the new matrix.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NoDataException if there are not at least one row and one column.
     * @see #Array2DRowFieldMatrix(FieldElement[][], boolean)
     */
    public Array2DRowFieldMatrix(final Field<T> field, final T[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
        super(field);
        copyIn(d);
    }

    /**
     * Create a new {@code FieldMatrix<T>} using the input array as the underlying
     * data array.
     * <p>If an array is built specially in order to be embedded in a
     * {@code FieldMatrix<T>} and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.</p>
     *
     * @param d Data for the new matrix.
     * @param copyArray Whether to copy or reference the input array.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NoDataException if there are not at least one row and one column.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #Array2DRowFieldMatrix(FieldElement[][])
     */
    public Array2DRowFieldMatrix(final T[][] d, final boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        this(extractField(d), d, copyArray);
    }

    /**
     * Create a new {@code FieldMatrix<T>} using the input array as the underlying
     * data array.
     * <p>If an array is built specially in order to be embedded in a
     * {@code FieldMatrix<T>} and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.</p>
     *
     * @param field Field to which the elements belong.
     * @param d Data for the new matrix.
     * @param copyArray Whether to copy or reference the input array.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NoDataException if there are not at least one row and one column.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #Array2DRowFieldMatrix(FieldElement[][])
     */
    public Array2DRowFieldMatrix(final Field<T> field, final T[][] d, final boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        super(field);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153");
        if (copyArray) {
            copyIn(d);
        } else {
            MathUtils.checkNotNull(d);
            final int nRows = d.length;
            if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153", _mut25119, _mut25120, _mut25121, _mut25122, _mut25123)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            final int nCols = d[0].length;
            if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153", _mut25124, _mut25125, _mut25126, _mut25127, _mut25128)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            for (int r = 1; ROR_less(r, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153", _mut25134, _mut25135, _mut25136, _mut25137, _mut25138); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153");
                if (ROR_not_equals(d[r].length, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_153", _mut25129, _mut25130, _mut25131, _mut25132, _mut25133)) {
                    throw new DimensionMismatchException(nCols, d[r].length);
                }
            }
            data = d;
        }
    }

    /**
     * Create a new (column) {@code FieldMatrix<T>} using {@code v} as the
     * data for the unique column of the created matrix.
     * The input array is copied.
     *
     * @param v Column vector holding data for new matrix.
     * @throws NoDataException if v is empty
     */
    public Array2DRowFieldMatrix(final T[] v) throws NoDataException {
        this(extractField(v), v);
    }

    /**
     * Create a new (column) {@code FieldMatrix<T>} using {@code v} as the
     * data for the unique column of the created matrix.
     * The input array is copied.
     *
     * @param field Field to which the elements belong.
     * @param v Column vector holding data for new matrix.
     */
    public Array2DRowFieldMatrix(final Field<T> field, final T[] v) {
        super(field);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_197");
        final int nRows = v.length;
        data = MathArrays.buildArray(getField(), nRows, 1);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_197", _mut25139, _mut25140, _mut25141, _mut25142, _mut25143); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.Array2DRowFieldMatrix_197");
            data[row][0] = v[row];
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        return new Array2DRowFieldMatrix<T>(getField(), rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> copy() {
        return new Array2DRowFieldMatrix<T>(getField(), copyOut(), false);
    }

    /**
     * Add {@code m} to this matrix.
     *
     * @param m Matrix to be added.
     * @return {@code this} + m.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as this matrix.
     */
    public Array2DRowFieldMatrix<T> add(final Array2DRowFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.add_228");
        // safety check
        checkAdditionCompatible(m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final T[][] outData = MathArrays.buildArray(getField(), rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.add_228", _mut25149, _mut25150, _mut25151, _mut25152, _mut25153); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.add_228");
            final T[] dataRow = data[row];
            final T[] mRow = m.data[row];
            final T[] outDataRow = outData[row];
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.add_228", _mut25144, _mut25145, _mut25146, _mut25147, _mut25148); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.add_228");
                outDataRow[col] = dataRow[col].add(mRow[col]);
            }
        }
        return new Array2DRowFieldMatrix<T>(getField(), outData, false);
    }

    /**
     * Subtract {@code m} from this matrix.
     *
     * @param m Matrix to be subtracted.
     * @return {@code this} + m.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as this matrix.
     */
    public Array2DRowFieldMatrix<T> subtract(final Array2DRowFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.subtract_256");
        // safety check
        checkSubtractionCompatible(m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final T[][] outData = MathArrays.buildArray(getField(), rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.subtract_256", _mut25159, _mut25160, _mut25161, _mut25162, _mut25163); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.subtract_256");
            final T[] dataRow = data[row];
            final T[] mRow = m.data[row];
            final T[] outDataRow = outData[row];
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.subtract_256", _mut25154, _mut25155, _mut25156, _mut25157, _mut25158); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.subtract_256");
                outDataRow[col] = dataRow[col].subtract(mRow[col]);
            }
        }
        return new Array2DRowFieldMatrix<T>(getField(), outData, false);
    }

    /**
     * Postmultiplying this matrix by {@code m}.
     *
     * @param m Matrix to postmultiply by.
     * @return {@code this} * m.
     * @throws DimensionMismatchException if the number of columns of this
     * matrix is not equal to the number of rows of {@code m}.
     */
    public Array2DRowFieldMatrix<T> multiply(final Array2DRowFieldMatrix<T> m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285");
        // safety check
        checkMultiplicationCompatible(m);
        final int nRows = this.getRowDimension();
        final int nCols = m.getColumnDimension();
        final int nSum = this.getColumnDimension();
        final T[][] outData = MathArrays.buildArray(getField(), nRows, nCols);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285", _mut25174, _mut25175, _mut25176, _mut25177, _mut25178); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285");
            final T[] dataRow = data[row];
            final T[] outDataRow = outData[row];
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285", _mut25169, _mut25170, _mut25171, _mut25172, _mut25173); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285");
                T sum = getField().getZero();
                for (int i = 0; ROR_less(i, nSum, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285", _mut25164, _mut25165, _mut25166, _mut25167, _mut25168); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.multiply_285");
                    sum = sum.add(dataRow[i].multiply(m.data[i][col]));
                }
                outDataRow[col] = sum;
            }
        }
        return new Array2DRowFieldMatrix<T>(getField(), outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[][] getData() {
        return copyOut();
    }

    /**
     * Get a reference to the underlying data array.
     * This methods returns internal data, <strong>not</strong> fresh copy of it.
     *
     * @return the 2-dimensional array of entries.
     */
    public T[][] getDataRef() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327");
        if (data == null) {
            if (ROR_greater(row, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25179, _mut25180, _mut25181, _mut25182, _mut25183)) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, row);
            }
            if (ROR_greater(column, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25184, _mut25185, _mut25186, _mut25187, _mut25188)) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, column);
            }
            final int nRows = subMatrix.length;
            if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25189, _mut25190, _mut25191, _mut25192, _mut25193)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            final int nCols = subMatrix[0].length;
            if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25194, _mut25195, _mut25196, _mut25197, _mut25198)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            data = MathArrays.buildArray(getField(), subMatrix.length, nCols);
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25208, _mut25209, _mut25210, _mut25211, _mut25212); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327");
                if (ROR_not_equals(subMatrix[i].length, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25199, _mut25200, _mut25201, _mut25202, _mut25203)) {
                    throw new DimensionMismatchException(nCols, subMatrix[i].length);
                }
                System.arraycopy(subMatrix[i], 0, data[AOR_plus(i, row, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.setSubMatrix_327", _mut25204, _mut25205, _mut25206, _mut25207)], column, nCols);
            }
        } else {
            super.setSubMatrix(subMatrix, row, column);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntry(final int row, final int column) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        return data[row][column];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(final int row, final int column, final T value) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        data[row][column] = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(final int row, final int column, final T increment) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        data[row][column] = data[row][column].add(increment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final T factor) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        data[row][column] = data[row][column].multiply(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        return (data == null) ? 0 : data.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.getColumnDimension_408");
        return ((_mut25213 ? ((data == null) && (data[0] == null)) : ((data == null) || (data[0] == null)))) ? 0 : data[0].length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] operate(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414");
        final int nRows = this.getRowDimension();
        final int nCols = this.getColumnDimension();
        if (ROR_not_equals(v.length, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414", _mut25214, _mut25215, _mut25216, _mut25217, _mut25218)) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        final T[] out = MathArrays.buildArray(getField(), nRows);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414", _mut25224, _mut25225, _mut25226, _mut25227, _mut25228); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414");
            final T[] dataRow = data[row];
            T sum = getField().getZero();
            for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414", _mut25219, _mut25220, _mut25221, _mut25222, _mut25223); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.operate_414");
                sum = sum.add(dataRow[i].multiply(v[i]));
            }
            out[row] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] preMultiply(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434", _mut25229, _mut25230, _mut25231, _mut25232, _mut25233)) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        final T[] out = MathArrays.buildArray(getField(), nCols);
        for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434", _mut25239, _mut25240, _mut25241, _mut25242, _mut25243); ++col) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434");
            T sum = getField().getZero();
            for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434", _mut25234, _mut25235, _mut25236, _mut25237, _mut25238); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.preMultiply_434");
                sum = sum.add(data[i][col].multiply(v[i]));
            }
            out[col] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455", _mut25244, _mut25245, _mut25246, _mut25247), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455", _mut25248, _mut25249, _mut25250, _mut25251));
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455", _mut25257, _mut25258, _mut25259, _mut25260, _mut25261); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455");
            final T[] rowI = data[i];
            for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455", _mut25252, _mut25253, _mut25254, _mut25255, _mut25256); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_455");
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470", _mut25262, _mut25263, _mut25264, _mut25265), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470", _mut25266, _mut25267, _mut25268, _mut25269));
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470", _mut25275, _mut25276, _mut25277, _mut25278, _mut25279); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470");
            final T[] rowI = data[i];
            for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470", _mut25270, _mut25271, _mut25272, _mut25273, _mut25274); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_470");
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_485");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_485", _mut25285, _mut25286, _mut25287, _mut25288, _mut25289); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_485");
            final T[] rowI = data[i];
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_485", _mut25280, _mut25281, _mut25282, _mut25283, _mut25284); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_485");
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_503");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_503", _mut25295, _mut25296, _mut25297, _mut25298, _mut25299); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_503");
            final T[] rowI = data[i];
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_503", _mut25290, _mut25291, _mut25292, _mut25293, _mut25294); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInRowOrder_503");
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521", _mut25300, _mut25301, _mut25302, _mut25303), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521", _mut25304, _mut25305, _mut25306, _mut25307));
        for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521", _mut25313, _mut25314, _mut25315, _mut25316, _mut25317); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521");
            for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521", _mut25308, _mut25309, _mut25310, _mut25311, _mut25312); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_521");
                final T[] rowI = data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536", _mut25318, _mut25319, _mut25320, _mut25321), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536", _mut25322, _mut25323, _mut25324, _mut25325));
        for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536", _mut25331, _mut25332, _mut25333, _mut25334, _mut25335); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536");
            for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536", _mut25326, _mut25327, _mut25328, _mut25329, _mut25330); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_536");
                visitor.visit(i, j, data[i][j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_550");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_550", _mut25341, _mut25342, _mut25343, _mut25344, _mut25345); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_550");
            for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_550", _mut25336, _mut25337, _mut25338, _mut25339, _mut25340); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_550");
                final T[] rowI = data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_568");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_568", _mut25351, _mut25352, _mut25353, _mut25354, _mut25355); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_568");
            for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_568", _mut25346, _mut25347, _mut25348, _mut25349, _mut25350); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.walkInColumnOrder_568");
                visitor.visit(i, j, data[i][j]);
            }
        }
        return visitor.end();
    }

    /**
     * Get a fresh copy of the underlying data array.
     *
     * @return a copy of the underlying data array.
     */
    private T[][] copyOut() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.copyOut_589");
        final int nRows = this.getRowDimension();
        final T[][] out = MathArrays.buildArray(getField(), nRows, getColumnDimension());
        // can't copy 2-d array in one shot, otherwise get row references
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.Array2DRowFieldMatrix.copyOut_589", _mut25356, _mut25357, _mut25358, _mut25359, _mut25360); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowFieldMatrix.copyOut_589");
            System.arraycopy(data[i], 0, out[i], 0, data[i].length);
        }
        return out;
    }

    /**
     * Replace data with a fresh copy of the input array.
     *
     * @param in Data to copy.
     * @throws NoDataException if the input array is empty.
     * @throws DimensionMismatchException if the input array is not rectangular.
     * @throws NullArgumentException if the input array is {@code null}.
     */
    private void copyIn(final T[][] in) throws NullArgumentException, NoDataException, DimensionMismatchException {
        setSubMatrix(in, 0, 0);
    }
}
