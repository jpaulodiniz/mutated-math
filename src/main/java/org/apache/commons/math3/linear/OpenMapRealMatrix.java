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
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Sparse matrix implementation based on an open addressed map.
 *
 * <p>
 *  Caveat: This implementation assumes that, for any {@code x},
 *  the equality {@code x * 0d == 0d} holds. But it is is not true for
 *  {@code NaN}. Moreover, zero entries will lose their sign.
 *  Some operations (that involve {@code NaN} and/or infinities) may
 *  thus give incorrect results.
 * </p>
 * @since 2.0
 */
public class OpenMapRealMatrix extends AbstractRealMatrix implements SparseRealMatrix, Serializable {

    @Conditional
    public static boolean _mut34528 = false, _mut34529 = false, _mut34530 = false, _mut34531 = false, _mut34532 = false, _mut34533 = false, _mut34534 = false, _mut34535 = false, _mut34536 = false, _mut34537 = false, _mut34538 = false, _mut34539 = false, _mut34540 = false, _mut34541 = false, _mut34542 = false, _mut34543 = false, _mut34544 = false, _mut34545 = false, _mut34546 = false, _mut34547 = false, _mut34548 = false, _mut34549 = false, _mut34550 = false, _mut34551 = false, _mut34552 = false, _mut34553 = false, _mut34554 = false, _mut34555 = false, _mut34556 = false, _mut34557 = false, _mut34558 = false, _mut34559 = false, _mut34560 = false, _mut34561 = false, _mut34562 = false, _mut34563 = false, _mut34564 = false, _mut34565 = false, _mut34566 = false, _mut34567 = false, _mut34568 = false, _mut34569 = false, _mut34570 = false, _mut34571 = false, _mut34572 = false, _mut34573 = false, _mut34574 = false, _mut34575 = false, _mut34576 = false, _mut34577 = false, _mut34578 = false, _mut34579 = false, _mut34580 = false, _mut34581 = false, _mut34582 = false, _mut34583 = false, _mut34584 = false, _mut34585 = false, _mut34586 = false, _mut34587 = false, _mut34588 = false, _mut34589 = false, _mut34590 = false, _mut34591 = false, _mut34592 = false, _mut34593 = false, _mut34594 = false, _mut34595 = false, _mut34596 = false, _mut34597 = false, _mut34598 = false, _mut34599 = false, _mut34600 = false, _mut34601 = false, _mut34602 = false, _mut34603 = false, _mut34604 = false, _mut34605 = false, _mut34606 = false, _mut34607 = false, _mut34608 = false, _mut34609 = false, _mut34610 = false, _mut34611 = false, _mut34612 = false, _mut34613 = false, _mut34614 = false, _mut34615 = false, _mut34616 = false, _mut34617 = false, _mut34618 = false, _mut34619 = false, _mut34620 = false, _mut34621 = false, _mut34622 = false, _mut34623 = false, _mut34624 = false, _mut34625 = false, _mut34626 = false, _mut34627 = false, _mut34628 = false, _mut34629 = false, _mut34630 = false, _mut34631 = false, _mut34632 = false, _mut34633 = false, _mut34634 = false, _mut34635 = false, _mut34636 = false, _mut34637 = false, _mut34638 = false, _mut34639 = false, _mut34640 = false, _mut34641 = false, _mut34642 = false, _mut34643 = false, _mut34644 = false, _mut34645 = false, _mut34646 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -5962461716457143437L;

    /**
     * Number of rows of the matrix.
     */
    private final int rows;

    /**
     * Number of columns of the matrix.
     */
    private final int columns;

    /**
     * Storage for (sparse) matrix elements.
     */
    private final OpenIntToDoubleHashMap entries;

    /**
     * Build a sparse matrix with the supplied row and column dimensions.
     *
     * @param rowDimension Number of rows of the matrix.
     * @param columnDimension Number of columns of the matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     * @throws NumberIsTooLargeException if the total number of entries of the
     * matrix is larger than {@code Integer.MAX_VALUE}.
     */
    public OpenMapRealMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        super(rowDimension, columnDimension);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.OpenMapRealMatrix_61");
        long lRow = rowDimension;
        long lCol = columnDimension;
        if (ROR_greater_equals(AOR_multiply(lRow, lCol, "org.apache.commons.math3.linear.OpenMapRealMatrix.OpenMapRealMatrix_61", _mut34528, _mut34529, _mut34530, _mut34531), Integer.MAX_VALUE, "org.apache.commons.math3.linear.OpenMapRealMatrix.OpenMapRealMatrix_61", _mut34532, _mut34533, _mut34534, _mut34535, _mut34536)) {
            throw new NumberIsTooLargeException(AOR_multiply(lRow, lCol, "org.apache.commons.math3.linear.OpenMapRealMatrix.OpenMapRealMatrix_61", _mut34537, _mut34538, _mut34539, _mut34540), Integer.MAX_VALUE, false);
        }
        this.rows = rowDimension;
        this.columns = columnDimension;
        this.entries = new OpenIntToDoubleHashMap(0.0);
    }

    /**
     * Build a matrix by copying another one.
     *
     * @param matrix matrix to copy.
     */
    public OpenMapRealMatrix(OpenMapRealMatrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.entries = new OpenIntToDoubleHashMap(matrix.entries);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealMatrix copy() {
        return new OpenMapRealMatrix(this);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NumberIsTooLargeException if the total number of entries of the
     * matrix is larger than {@code Integer.MAX_VALUE}.
     */
    @Override
    public OpenMapRealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return new OpenMapRealMatrix(rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        return columns;
    }

    /**
     * Compute the sum of this matrix and {@code m}.
     *
     * @param m Matrix to be added.
     * @return {@code this} + {@code m}.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public OpenMapRealMatrix add(OpenMapRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.add_117");
        MatrixUtils.checkAdditionCompatible(this, m);
        final OpenMapRealMatrix out = new OpenMapRealMatrix(this);
        for (OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator(); iterator.hasNext(); ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.add_117");
            iterator.advance();
            final int row = AOR_divide(iterator.key(), columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.add_117", _mut34541, _mut34542, _mut34543, _mut34544);
            final int col = AOR_minus(iterator.key(), AOR_multiply(row, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.add_117", _mut34545, _mut34546, _mut34547, _mut34548), "org.apache.commons.math3.linear.OpenMapRealMatrix.add_117", _mut34549, _mut34550, _mut34551, _mut34552);
            out.setEntry(row, col, AOR_plus(getEntry(row, col), iterator.value(), "org.apache.commons.math3.linear.OpenMapRealMatrix.add_117", _mut34553, _mut34554, _mut34555, _mut34556));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealMatrix subtract(final RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return subtract((OpenMapRealMatrix) m);
        } catch (ClassCastException cce) {
            return (OpenMapRealMatrix) super.subtract(m);
        }
    }

    /**
     * Subtract {@code m} from this matrix.
     *
     * @param m Matrix to be subtracted.
     * @return {@code this} - {@code m}.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public OpenMapRealMatrix subtract(OpenMapRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153");
        MatrixUtils.checkAdditionCompatible(this, m);
        final OpenMapRealMatrix out = new OpenMapRealMatrix(this);
        for (OpenIntToDoubleHashMap.Iterator iterator = m.entries.iterator(); iterator.hasNext(); ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153");
            iterator.advance();
            final int row = AOR_divide(iterator.key(), columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153", _mut34557, _mut34558, _mut34559, _mut34560);
            final int col = AOR_minus(iterator.key(), AOR_multiply(row, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153", _mut34561, _mut34562, _mut34563, _mut34564), "org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153", _mut34565, _mut34566, _mut34567, _mut34568);
            out.setEntry(row, col, AOR_minus(getEntry(row, col), iterator.value(), "org.apache.commons.math3.linear.OpenMapRealMatrix.subtract_153", _mut34569, _mut34570, _mut34571, _mut34572));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NumberIsTooLargeException if {@code m} is an
     * {@code OpenMapRealMatrix}, and the total number of entries of the product
     * is larger than {@code Integer.MAX_VALUE}.
     */
    @Override
    public RealMatrix multiply(final RealMatrix m) throws DimensionMismatchException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175");
        try {
            return multiply((OpenMapRealMatrix) m);
        } catch (ClassCastException cce) {
            MatrixUtils.checkMultiplicationCompatible(this, m);
            final int outCols = m.getColumnDimension();
            final BlockRealMatrix out = new BlockRealMatrix(rows, outCols);
            for (OpenIntToDoubleHashMap.Iterator iterator = entries.iterator(); iterator.hasNext(); ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175");
                iterator.advance();
                final double value = iterator.value();
                final int key = iterator.key();
                final int i = AOR_divide(key, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175", _mut34573, _mut34574, _mut34575, _mut34576);
                final int k = AOR_remainder(key, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175", _mut34577, _mut34578, _mut34579, _mut34580);
                for (int j = 0; ROR_less(j, outCols, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175", _mut34585, _mut34586, _mut34587, _mut34588, _mut34589); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175");
                    out.addToEntry(i, j, AOR_multiply(value, m.getEntry(k, j), "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_175", _mut34581, _mut34582, _mut34583, _mut34584));
                }
            }
            return out;
        }
    }

    /**
     * Postmultiply this matrix by {@code m}.
     *
     * @param m Matrix to postmultiply by.
     * @return {@code this} * {@code m}.
     * @throws DimensionMismatchException if the number of rows of {@code m}
     * differ from the number of columns of {@code this} matrix.
     * @throws NumberIsTooLargeException if the total number of entries of the
     * product is larger than {@code Integer.MAX_VALUE}.
     */
    public OpenMapRealMatrix multiply(OpenMapRealMatrix m) throws DimensionMismatchException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211");
        // Safety check.
        MatrixUtils.checkMultiplicationCompatible(this, m);
        final int outCols = m.getColumnDimension();
        OpenMapRealMatrix out = new OpenMapRealMatrix(rows, outCols);
        for (OpenIntToDoubleHashMap.Iterator iterator = entries.iterator(); iterator.hasNext(); ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211");
            iterator.advance();
            final double value = iterator.value();
            final int key = iterator.key();
            final int i = AOR_divide(key, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34590, _mut34591, _mut34592, _mut34593);
            final int k = AOR_remainder(key, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34594, _mut34595, _mut34596, _mut34597);
            for (int j = 0; ROR_less(j, outCols, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34611, _mut34612, _mut34613, _mut34614, _mut34615); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211");
                final int rightKey = m.computeKey(k, j);
                if (m.entries.containsKey(rightKey)) {
                    final int outKey = out.computeKey(i, j);
                    final double outValue = AOR_plus(out.entries.get(outKey), AOR_multiply(value, m.entries.get(rightKey), "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34598, _mut34599, _mut34600, _mut34601), "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34602, _mut34603, _mut34604, _mut34605);
                    if (ROR_equals(outValue, 0.0, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiply_211", _mut34606, _mut34607, _mut34608, _mut34609, _mut34610)) {
                        out.entries.remove(outKey);
                    } else {
                        out.entries.put(outKey, outValue);
                    }
                }
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        return entries.get(computeKey(row, column));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        return rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(int row, int column, double value) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.setEntry_257");
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        if (ROR_equals(value, 0.0, "org.apache.commons.math3.linear.OpenMapRealMatrix.setEntry_257", _mut34616, _mut34617, _mut34618, _mut34619, _mut34620)) {
            entries.remove(computeKey(row, column));
        } else {
            entries.put(computeKey(row, column), value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.addToEntry_270");
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        final int key = computeKey(row, column);
        final double value = AOR_plus(entries.get(key), increment, "org.apache.commons.math3.linear.OpenMapRealMatrix.addToEntry_270", _mut34621, _mut34622, _mut34623, _mut34624);
        if (ROR_equals(value, 0.0, "org.apache.commons.math3.linear.OpenMapRealMatrix.addToEntry_270", _mut34625, _mut34626, _mut34627, _mut34628, _mut34629)) {
            entries.remove(key);
        } else {
            entries.put(key, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.multiplyEntry_285");
        MatrixUtils.checkRowIndex(this, row);
        MatrixUtils.checkColumnIndex(this, column);
        final int key = computeKey(row, column);
        final double value = AOR_multiply(entries.get(key), factor, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiplyEntry_285", _mut34630, _mut34631, _mut34632, _mut34633);
        if (ROR_equals(value, 0.0, "org.apache.commons.math3.linear.OpenMapRealMatrix.multiplyEntry_285", _mut34634, _mut34635, _mut34636, _mut34637, _mut34638)) {
            entries.remove(key);
        } else {
            entries.put(key, value);
        }
    }

    /**
     * Compute the key to access a matrix element
     * @param row row index of the matrix element
     * @param column column index of the matrix element
     * @return key within the map to access the matrix element
     */
    private int computeKey(int row, int column) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealMatrix.computeKey_305");
        return AOR_plus(AOR_multiply(row, columns, "org.apache.commons.math3.linear.OpenMapRealMatrix.computeKey_305", _mut34639, _mut34640, _mut34641, _mut34642), column, "org.apache.commons.math3.linear.OpenMapRealMatrix.computeKey_305", _mut34643, _mut34644, _mut34645, _mut34646);
    }
}
