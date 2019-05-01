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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of {@link RealMatrix} using a {@code double[][]} array to
 * store entries.
 */
public class Array2DRowRealMatrix extends AbstractRealMatrix implements Serializable {

    @Conditional
    public static boolean _mut23018 = false, _mut23019 = false, _mut23020 = false, _mut23021 = false, _mut23022 = false, _mut23023 = false, _mut23024 = false, _mut23025 = false, _mut23026 = false, _mut23027 = false, _mut23028 = false, _mut23029 = false, _mut23030 = false, _mut23031 = false, _mut23032 = false, _mut23033 = false, _mut23034 = false, _mut23035 = false, _mut23036 = false, _mut23037 = false, _mut23038 = false, _mut23039 = false, _mut23040 = false, _mut23041 = false, _mut23042 = false, _mut23043 = false, _mut23044 = false, _mut23045 = false, _mut23046 = false, _mut23047 = false, _mut23048 = false, _mut23049 = false, _mut23050 = false, _mut23051 = false, _mut23052 = false, _mut23053 = false, _mut23054 = false, _mut23055 = false, _mut23056 = false, _mut23057 = false, _mut23058 = false, _mut23059 = false, _mut23060 = false, _mut23061 = false, _mut23062 = false, _mut23063 = false, _mut23064 = false, _mut23065 = false, _mut23066 = false, _mut23067 = false, _mut23068 = false, _mut23069 = false, _mut23070 = false, _mut23071 = false, _mut23072 = false, _mut23073 = false, _mut23074 = false, _mut23075 = false, _mut23076 = false, _mut23077 = false, _mut23078 = false, _mut23079 = false, _mut23080 = false, _mut23081 = false, _mut23082 = false, _mut23083 = false, _mut23084 = false, _mut23085 = false, _mut23086 = false, _mut23087 = false, _mut23088 = false, _mut23089 = false, _mut23090 = false, _mut23091 = false, _mut23092 = false, _mut23093 = false, _mut23094 = false, _mut23095 = false, _mut23096 = false, _mut23097 = false, _mut23098 = false, _mut23099 = false, _mut23100 = false, _mut23101 = false, _mut23102 = false, _mut23103 = false, _mut23104 = false, _mut23105 = false, _mut23106 = false, _mut23107 = false, _mut23108 = false, _mut23109 = false, _mut23110 = false, _mut23111 = false, _mut23112 = false, _mut23113 = false, _mut23114 = false, _mut23115 = false, _mut23116 = false, _mut23117 = false, _mut23118 = false, _mut23119 = false, _mut23120 = false, _mut23121 = false, _mut23122 = false, _mut23123 = false, _mut23124 = false, _mut23125 = false, _mut23126 = false, _mut23127 = false, _mut23128 = false, _mut23129 = false, _mut23130 = false, _mut23131 = false, _mut23132 = false, _mut23133 = false, _mut23134 = false, _mut23135 = false, _mut23136 = false, _mut23137 = false, _mut23138 = false, _mut23139 = false, _mut23140 = false, _mut23141 = false, _mut23142 = false, _mut23143 = false, _mut23144 = false, _mut23145 = false, _mut23146 = false, _mut23147 = false, _mut23148 = false, _mut23149 = false, _mut23150 = false, _mut23151 = false, _mut23152 = false, _mut23153 = false, _mut23154 = false, _mut23155 = false, _mut23156 = false, _mut23157 = false, _mut23158 = false, _mut23159 = false, _mut23160 = false, _mut23161 = false, _mut23162 = false, _mut23163 = false, _mut23164 = false, _mut23165 = false, _mut23166 = false, _mut23167 = false, _mut23168 = false, _mut23169 = false, _mut23170 = false, _mut23171 = false, _mut23172 = false, _mut23173 = false, _mut23174 = false, _mut23175 = false, _mut23176 = false, _mut23177 = false, _mut23178 = false, _mut23179 = false, _mut23180 = false, _mut23181 = false, _mut23182 = false, _mut23183 = false, _mut23184 = false, _mut23185 = false, _mut23186 = false, _mut23187 = false, _mut23188 = false, _mut23189 = false, _mut23190 = false, _mut23191 = false, _mut23192 = false, _mut23193 = false, _mut23194 = false, _mut23195 = false, _mut23196 = false, _mut23197 = false, _mut23198 = false, _mut23199 = false, _mut23200 = false, _mut23201 = false, _mut23202 = false, _mut23203 = false, _mut23204 = false, _mut23205 = false, _mut23206 = false, _mut23207 = false, _mut23208 = false, _mut23209 = false, _mut23210 = false, _mut23211 = false, _mut23212 = false, _mut23213 = false, _mut23214 = false, _mut23215 = false, _mut23216 = false, _mut23217 = false, _mut23218 = false, _mut23219 = false, _mut23220 = false, _mut23221 = false, _mut23222 = false, _mut23223 = false, _mut23224 = false, _mut23225 = false, _mut23226 = false, _mut23227 = false, _mut23228 = false, _mut23229 = false, _mut23230 = false, _mut23231 = false, _mut23232 = false, _mut23233 = false, _mut23234 = false, _mut23235 = false, _mut23236 = false, _mut23237 = false, _mut23238 = false, _mut23239 = false, _mut23240 = false, _mut23241 = false, _mut23242 = false, _mut23243 = false, _mut23244 = false, _mut23245 = false, _mut23246 = false, _mut23247 = false, _mut23248 = false, _mut23249 = false, _mut23250 = false, _mut23251 = false, _mut23252 = false, _mut23253 = false, _mut23254 = false, _mut23255 = false, _mut23256 = false, _mut23257 = false, _mut23258 = false, _mut23259 = false, _mut23260 = false, _mut23261 = false, _mut23262 = false, _mut23263 = false, _mut23264 = false, _mut23265 = false, _mut23266 = false, _mut23267 = false, _mut23268 = false, _mut23269 = false, _mut23270 = false, _mut23271 = false, _mut23272 = false, _mut23273 = false, _mut23274 = false, _mut23275 = false, _mut23276 = false, _mut23277 = false, _mut23278 = false, _mut23279 = false, _mut23280 = false, _mut23281 = false, _mut23282 = false, _mut23283 = false, _mut23284 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -1067294169172445528L;

    /**
     * Entries of the matrix.
     */
    private double[][] data;

    /**
     * Creates a matrix with no data
     */
    public Array2DRowRealMatrix() {
    }

    /**
     * Create a new RealMatrix with the supplied row and column dimensions.
     *
     * @param rowDimension Number of rows in the new matrix.
     * @param columnDimension Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if the row or column dimension is
     * not positive.
     */
    public Array2DRowRealMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        super(rowDimension, columnDimension);
        data = new double[rowDimension][columnDimension];
    }

    /**
     * Create a new {@code RealMatrix} using the input array as the underlying
     * data array.
     * <p>The input array is copied, not referenced. This constructor has
     * the same effect as calling {@link #Array2DRowRealMatrix(double[][], boolean)}
     * with the second argument set to {@code true}.</p>
     *
     * @param d Data for the new matrix.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NoDataException if {@code d} row or column dimension is zero.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #Array2DRowRealMatrix(double[][], boolean)
     */
    public Array2DRowRealMatrix(final double[][] d) throws DimensionMismatchException, NoDataException, NullArgumentException {
        copyIn(d);
    }

    /**
     * Create a new RealMatrix using the input array as the underlying
     * data array.
     * If an array is built specially in order to be embedded in a
     * RealMatrix and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.
     *
     * @param d Data for new matrix.
     * @param copyArray if {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @throws DimensionMismatchException if {@code d} is not rectangular.
     * @throws NoDataException if {@code d} row or column dimension is zero.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #Array2DRowRealMatrix(double[][])
     */
    public Array2DRowRealMatrix(final double[][] d, final boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98");
        if (copyArray) {
            copyIn(d);
        } else {
            if (d == null) {
                throw new NullArgumentException();
            }
            final int nRows = d.length;
            if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98", _mut23018, _mut23019, _mut23020, _mut23021, _mut23022)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            final int nCols = d[0].length;
            if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98", _mut23023, _mut23024, _mut23025, _mut23026, _mut23027)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            for (int r = 1; ROR_less(r, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98", _mut23033, _mut23034, _mut23035, _mut23036, _mut23037); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98");
                if (ROR_not_equals(d[r].length, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_98", _mut23028, _mut23029, _mut23030, _mut23031, _mut23032)) {
                    throw new DimensionMismatchException(d[r].length, nCols);
                }
            }
            data = d;
        }
    }

    /**
     * Create a new (column) RealMatrix using {@code v} as the
     * data for the unique column of the created matrix.
     * The input array is copied.
     *
     * @param v Column vector holding data for new matrix.
     */
    public Array2DRowRealMatrix(final double[] v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_131");
        final int nRows = v.length;
        data = new double[nRows][1];
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_131", _mut23038, _mut23039, _mut23040, _mut23041, _mut23042); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.Array2DRowRealMatrix_131");
            data[row][0] = v[row];
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        return new Array2DRowRealMatrix(rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix copy() {
        return new Array2DRowRealMatrix(copyOut(), false);
    }

    /**
     * Compute the sum of {@code this} and {@code m}.
     *
     * @param m Matrix to be added.
     * @return {@code this + m}.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public Array2DRowRealMatrix add(final Array2DRowRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161");
        // Safety check.
        MatrixUtils.checkAdditionCompatible(this, m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final double[][] outData = new double[rowCount][columnCount];
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161", _mut23052, _mut23053, _mut23054, _mut23055, _mut23056); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161");
            final double[] dataRow = data[row];
            final double[] mRow = m.data[row];
            final double[] outDataRow = outData[row];
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161", _mut23047, _mut23048, _mut23049, _mut23050, _mut23051); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161");
                outDataRow[col] = AOR_plus(dataRow[col], mRow[col], "org.apache.commons.math3.linear.Array2DRowRealMatrix.add_161", _mut23043, _mut23044, _mut23045, _mut23046);
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    /**
     * Returns {@code this} minus {@code m}.
     *
     * @param m Matrix to be subtracted.
     * @return {@code this - m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public Array2DRowRealMatrix subtract(final Array2DRowRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189");
        MatrixUtils.checkSubtractionCompatible(this, m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final double[][] outData = new double[rowCount][columnCount];
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189", _mut23066, _mut23067, _mut23068, _mut23069, _mut23070); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189");
            final double[] dataRow = data[row];
            final double[] mRow = m.data[row];
            final double[] outDataRow = outData[row];
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189", _mut23061, _mut23062, _mut23063, _mut23064, _mut23065); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189");
                outDataRow[col] = AOR_minus(dataRow[col], mRow[col], "org.apache.commons.math3.linear.Array2DRowRealMatrix.subtract_189", _mut23057, _mut23058, _mut23059, _mut23060);
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    /**
     * Returns the result of postmultiplying {@code this} by {@code m}.
     *
     * @param m matrix to postmultiply by
     * @return {@code this * m}
     * @throws DimensionMismatchException if
     * {@code columnDimension(this) != rowDimension(m)}
     */
    public Array2DRowRealMatrix multiply(final Array2DRowRealMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216");
        MatrixUtils.checkMultiplicationCompatible(this, m);
        final int nRows = this.getRowDimension();
        final int nCols = m.getColumnDimension();
        final int nSum = this.getColumnDimension();
        final double[][] outData = new double[nRows][nCols];
        // Will hold a column of "m".
        final double[] mCol = new double[nSum];
        final double[][] mData = m.data;
        // Multiply.
        for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216", _mut23090, _mut23091, _mut23092, _mut23093, _mut23094); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216");
            // will be in contiguous memory.
            for (int mRow = 0; ROR_less(mRow, nSum, "org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216", _mut23071, _mut23072, _mut23073, _mut23074, _mut23075); mRow++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216");
                mCol[mRow] = mData[mRow][col];
            }
            for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216", _mut23085, _mut23086, _mut23087, _mut23088, _mut23089); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216");
                final double[] dataRow = data[row];
                double sum = 0;
                for (int i = 0; ROR_less(i, nSum, "org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216", _mut23080, _mut23081, _mut23082, _mut23083, _mut23084); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216");
                    sum += AOR_multiply(dataRow[i], mCol[i], "org.apache.commons.math3.linear.Array2DRowRealMatrix.multiply_216", _mut23076, _mut23077, _mut23078, _mut23079);
                }
                outData[row][col] = sum;
            }
        }
        return new Array2DRowRealMatrix(outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[][] getData() {
        return copyOut();
    }

    /**
     * Get a reference to the underlying data array.
     *
     * @return 2-dimensional array of entries.
     */
    public double[][] getDataRef() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubMatrix(final double[][] subMatrix, final int row, final int column) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266");
        if (data == null) {
            if (ROR_greater(row, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23095, _mut23096, _mut23097, _mut23098, _mut23099)) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, row);
            }
            if (ROR_greater(column, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23100, _mut23101, _mut23102, _mut23103, _mut23104)) {
                throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, column);
            }
            MathUtils.checkNotNull(subMatrix);
            final int nRows = subMatrix.length;
            if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23105, _mut23106, _mut23107, _mut23108, _mut23109)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
            final int nCols = subMatrix[0].length;
            if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23110, _mut23111, _mut23112, _mut23113, _mut23114)) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            data = new double[subMatrix.length][nCols];
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23124, _mut23125, _mut23126, _mut23127, _mut23128); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266");
                if (ROR_not_equals(subMatrix[i].length, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23115, _mut23116, _mut23117, _mut23118, _mut23119)) {
                    throw new DimensionMismatchException(subMatrix[i].length, nCols);
                }
                System.arraycopy(subMatrix[i], 0, data[AOR_plus(i, row, "org.apache.commons.math3.linear.Array2DRowRealMatrix.setSubMatrix_266", _mut23120, _mut23121, _mut23122, _mut23123)], column, nCols);
            }
        } else {
            super.setSubMatrix(subMatrix, row, column);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(final int row, final int column) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        return data[row][column];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(final int row, final int column, final double value) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        data[row][column] = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(final int row, final int column, final double increment) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        data[row][column] += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final double factor) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        data[row][column] *= factor;
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.getColumnDimension_342");
        return ((_mut23129 ? ((data == null) && (data[0] == null)) : ((data == null) || (data[0] == null)))) ? 0 : data[0].length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] operate(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348");
        final int nRows = this.getRowDimension();
        final int nCols = this.getColumnDimension();
        if (ROR_not_equals(v.length, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348", _mut23130, _mut23131, _mut23132, _mut23133, _mut23134)) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        final double[] out = new double[nRows];
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348", _mut23144, _mut23145, _mut23146, _mut23147, _mut23148); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348");
            final double[] dataRow = data[row];
            double sum = 0;
            for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348", _mut23139, _mut23140, _mut23141, _mut23142, _mut23143); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348");
                sum += AOR_multiply(dataRow[i], v[i], "org.apache.commons.math3.linear.Array2DRowRealMatrix.operate_348", _mut23135, _mut23136, _mut23137, _mut23138);
            }
            out[row] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] preMultiply(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369", _mut23149, _mut23150, _mut23151, _mut23152, _mut23153)) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        final double[] out = new double[nCols];
        for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369", _mut23163, _mut23164, _mut23165, _mut23166, _mut23167); ++col) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369");
            double sum = 0;
            for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369", _mut23158, _mut23159, _mut23160, _mut23161, _mut23162); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369");
                sum += AOR_multiply(data[i][col], v[i], "org.apache.commons.math3.linear.Array2DRowRealMatrix.preMultiply_369", _mut23154, _mut23155, _mut23156, _mut23157);
            }
            out[col] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392", _mut23168, _mut23169, _mut23170, _mut23171), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392", _mut23172, _mut23173, _mut23174, _mut23175));
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392", _mut23181, _mut23182, _mut23183, _mut23184, _mut23185); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392");
            final double[] rowI = data[i];
            for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392", _mut23176, _mut23177, _mut23178, _mut23179, _mut23180); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_392");
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407", _mut23186, _mut23187, _mut23188, _mut23189), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407", _mut23190, _mut23191, _mut23192, _mut23193));
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407", _mut23199, _mut23200, _mut23201, _mut23202, _mut23203); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407");
            final double[] rowI = data[i];
            for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407", _mut23194, _mut23195, _mut23196, _mut23197, _mut23198); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_407");
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_422");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_422", _mut23209, _mut23210, _mut23211, _mut23212, _mut23213); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_422");
            final double[] rowI = data[i];
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_422", _mut23204, _mut23205, _mut23206, _mut23207, _mut23208); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_422");
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_440");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_440", _mut23219, _mut23220, _mut23221, _mut23222, _mut23223); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_440");
            final double[] rowI = data[i];
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_440", _mut23214, _mut23215, _mut23216, _mut23217, _mut23218); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInRowOrder_440");
                visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458", _mut23224, _mut23225, _mut23226, _mut23227), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458", _mut23228, _mut23229, _mut23230, _mut23231));
        for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458", _mut23237, _mut23238, _mut23239, _mut23240, _mut23241); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458");
            for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458", _mut23232, _mut23233, _mut23234, _mut23235, _mut23236); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_458");
                final double[] rowI = data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473", _mut23242, _mut23243, _mut23244, _mut23245), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473", _mut23246, _mut23247, _mut23248, _mut23249));
        for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473", _mut23255, _mut23256, _mut23257, _mut23258, _mut23259); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473");
            for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473", _mut23250, _mut23251, _mut23252, _mut23253, _mut23254); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_473");
                visitor.visit(i, j, data[i][j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_487");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_487", _mut23265, _mut23266, _mut23267, _mut23268, _mut23269); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_487");
            for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_487", _mut23260, _mut23261, _mut23262, _mut23263, _mut23264); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_487");
                final double[] rowI = data[i];
                rowI[j] = visitor.visit(i, j, rowI[j]);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_505");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_505", _mut23275, _mut23276, _mut23277, _mut23278, _mut23279); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_505");
            for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_505", _mut23270, _mut23271, _mut23272, _mut23273, _mut23274); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.walkInColumnOrder_505");
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
    private double[][] copyOut() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.copyOut_526");
        final int nRows = this.getRowDimension();
        final double[][] out = new double[nRows][this.getColumnDimension()];
        // can't copy 2-d array in one shot, otherwise get row references
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.Array2DRowRealMatrix.copyOut_526", _mut23280, _mut23281, _mut23282, _mut23283, _mut23284); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.Array2DRowRealMatrix.copyOut_526");
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
    private void copyIn(final double[][] in) throws DimensionMismatchException, NoDataException, NullArgumentException {
        setSubMatrix(in, 0, 0);
    }
}
