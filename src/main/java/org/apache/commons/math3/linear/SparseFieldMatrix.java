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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;
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
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class SparseFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> {

    @Conditional
    public static boolean _mut22688 = false, _mut22689 = false, _mut22690 = false, _mut22691 = false, _mut22692 = false, _mut22693 = false, _mut22694 = false, _mut22695 = false, _mut22696 = false, _mut22697 = false, _mut22698 = false, _mut22699 = false, _mut22700 = false, _mut22701 = false, _mut22702 = false, _mut22703 = false, _mut22704 = false, _mut22705 = false;

    /**
     * Storage for (sparse) matrix elements.
     */
    private final OpenIntToFieldHashMap<T> entries;

    /**
     * Row dimension.
     */
    private final int rows;

    /**
     * Column dimension.
     */
    private final int columns;

    /**
     * Create a matrix with no data.
     *
     * @param field Field to which the elements belong.
     */
    public SparseFieldMatrix(final Field<T> field) {
        super(field);
        rows = 0;
        columns = 0;
        entries = new OpenIntToFieldHashMap<T>(field);
    }

    /**
     * Create a new SparseFieldMatrix<T> with the supplied row and column
     * dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rowDimension Number of rows in the new matrix.
     * @param columnDimension Number of columns in the new matrix.
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if row or column dimension is not positive.
     */
    public SparseFieldMatrix(final Field<T> field, final int rowDimension, final int columnDimension) {
        super(field, rowDimension, columnDimension);
        this.rows = rowDimension;
        this.columns = columnDimension;
        entries = new OpenIntToFieldHashMap<T>(field);
    }

    /**
     * Copy constructor.
     *
     * @param other Instance to copy.
     */
    public SparseFieldMatrix(SparseFieldMatrix<T> other) {
        super(other.getField(), other.getRowDimension(), other.getColumnDimension());
        rows = other.getRowDimension();
        columns = other.getColumnDimension();
        entries = new OpenIntToFieldHashMap<T>(other.entries);
    }

    /**
     * Generic copy constructor.
     *
     * @param other Instance to copy.
     */
    public SparseFieldMatrix(FieldMatrix<T> other) {
        super(other.getField(), other.getRowDimension(), other.getColumnDimension());
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldMatrix.SparseFieldMatrix_92");
        rows = other.getRowDimension();
        columns = other.getColumnDimension();
        entries = new OpenIntToFieldHashMap<T>(getField());
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.SparseFieldMatrix.SparseFieldMatrix_92", _mut22693, _mut22694, _mut22695, _mut22696, _mut22697); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldMatrix.SparseFieldMatrix_92");
            for (int j = 0; ROR_less(j, columns, "org.apache.commons.math3.linear.SparseFieldMatrix.SparseFieldMatrix_92", _mut22688, _mut22689, _mut22690, _mut22691, _mut22692); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldMatrix.SparseFieldMatrix_92");
                setEntry(i, j, other.getEntry(i, j));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(int row, int column, T increment) {
        checkRowIndex(row);
        checkColumnIndex(column);
        final int key = computeKey(row, column);
        final T value = entries.get(key).add(increment);
        if (getField().getZero().equals(value)) {
            entries.remove(key);
        } else {
            entries.put(key, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> copy() {
        return new SparseFieldMatrix<T>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) {
        return new SparseFieldMatrix<T>(getField(), rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        return columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntry(int row, int column) {
        checkRowIndex(row);
        checkColumnIndex(column);
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
    public void multiplyEntry(int row, int column, T factor) {
        checkRowIndex(row);
        checkColumnIndex(column);
        final int key = computeKey(row, column);
        final T value = entries.get(key).multiply(factor);
        if (getField().getZero().equals(value)) {
            entries.remove(key);
        } else {
            entries.put(key, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(int row, int column, T value) {
        checkRowIndex(row);
        checkColumnIndex(column);
        if (getField().getZero().equals(value)) {
            entries.remove(computeKey(row, column));
        } else {
            entries.put(computeKey(row, column), value);
        }
    }

    /**
     * Compute the key to access a matrix element.
     *
     * @param row Row index of the matrix element.
     * @param column Column index of the matrix element.
     * @return the key within the map to access the matrix element.
     */
    private int computeKey(int row, int column) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldMatrix.computeKey_184");
        return AOR_plus(AOR_multiply(row, columns, "org.apache.commons.math3.linear.SparseFieldMatrix.computeKey_184", _mut22698, _mut22699, _mut22700, _mut22701), column, "org.apache.commons.math3.linear.SparseFieldMatrix.computeKey_184", _mut22702, _mut22703, _mut22704, _mut22705);
    }
}
