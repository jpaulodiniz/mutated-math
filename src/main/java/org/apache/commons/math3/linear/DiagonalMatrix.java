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
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of a diagonal matrix.
 *
 * @since 3.1.1
 */
public class DiagonalMatrix extends AbstractRealMatrix implements Serializable {

    @Conditional
    public static boolean _mut32230 = false, _mut32231 = false, _mut32232 = false, _mut32233 = false, _mut32234 = false, _mut32235 = false, _mut32236 = false, _mut32237 = false, _mut32238 = false, _mut32239 = false, _mut32240 = false, _mut32241 = false, _mut32242 = false, _mut32243 = false, _mut32244 = false, _mut32245 = false, _mut32246 = false, _mut32247 = false, _mut32248 = false, _mut32249 = false, _mut32250 = false, _mut32251 = false, _mut32252 = false, _mut32253 = false, _mut32254 = false, _mut32255 = false, _mut32256 = false, _mut32257 = false, _mut32258 = false, _mut32259 = false, _mut32260 = false, _mut32261 = false, _mut32262 = false, _mut32263 = false, _mut32264 = false, _mut32265 = false, _mut32266 = false, _mut32267 = false, _mut32268 = false, _mut32269 = false, _mut32270 = false, _mut32271 = false, _mut32272 = false, _mut32273 = false, _mut32274 = false, _mut32275 = false, _mut32276 = false, _mut32277 = false, _mut32278 = false, _mut32279 = false, _mut32280 = false, _mut32281 = false, _mut32282 = false, _mut32283 = false, _mut32284 = false, _mut32285 = false, _mut32286 = false, _mut32287 = false, _mut32288 = false, _mut32289 = false, _mut32290 = false, _mut32291 = false, _mut32292 = false, _mut32293 = false, _mut32294 = false, _mut32295 = false, _mut32296 = false, _mut32297 = false, _mut32298 = false, _mut32299 = false, _mut32300 = false, _mut32301 = false, _mut32302 = false, _mut32303 = false, _mut32304 = false, _mut32305 = false, _mut32306 = false, _mut32307 = false, _mut32308 = false, _mut32309 = false, _mut32310 = false, _mut32311 = false, _mut32312 = false, _mut32313 = false, _mut32314 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20121229L;

    /**
     * Entries of the diagonal.
     */
    private final double[] data;

    /**
     * Creates a matrix with the supplied dimension.
     *
     * @param dimension Number of rows and columns in the new matrix.
     * @throws NotStrictlyPositiveException if the dimension is
     * not positive.
     */
    public DiagonalMatrix(final int dimension) throws NotStrictlyPositiveException {
        super(dimension, dimension);
        data = new double[dimension];
    }

    /**
     * Creates a matrix using the input array as the underlying data.
     * <br/>
     * The input array is copied, not referenced.
     *
     * @param d Data for the new matrix.
     */
    public DiagonalMatrix(final double[] d) {
        this(d, true);
    }

    /**
     * Creates a matrix using the input array as the underlying data.
     * <br/>
     * If an array is created specially in order to be embedded in a
     * this instance and not used directly, the {@code copyArray} may be
     * set to {@code false}.
     * This will prevent the copying and improve performance as no new
     * array will be built and no data will be copied.
     *
     * @param d Data for new matrix.
     * @param copyArray if {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @exception NullArgumentException if d is null
     */
    public DiagonalMatrix(final double[] d, final boolean copyArray) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        data = copyArray ? d.clone() : d;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DimensionMismatchException if the requested dimensions are not equal.
     */
    @Override
    public RealMatrix createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.createMatrix_91");
        if (ROR_not_equals(rowDimension, columnDimension, "org.apache.commons.math3.linear.DiagonalMatrix.createMatrix_91", _mut32230, _mut32231, _mut32232, _mut32233, _mut32234)) {
            throw new DimensionMismatchException(rowDimension, columnDimension);
        }
        return new DiagonalMatrix(rowDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix copy() {
        return new DiagonalMatrix(data);
    }

    /**
     * Compute the sum of {@code this} and {@code m}.
     *
     * @param m Matrix to be added.
     * @return {@code this + m}.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public DiagonalMatrix add(final DiagonalMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.add_117");
        // Safety check.
        MatrixUtils.checkAdditionCompatible(this, m);
        final int dim = getRowDimension();
        final double[] outData = new double[dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.DiagonalMatrix.add_117", _mut32239, _mut32240, _mut32241, _mut32242, _mut32243); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.add_117");
            outData[i] = AOR_plus(data[i], m.data[i], "org.apache.commons.math3.linear.DiagonalMatrix.add_117", _mut32235, _mut32236, _mut32237, _mut32238);
        }
        return new DiagonalMatrix(outData, false);
    }

    /**
     * Returns {@code this} minus {@code m}.
     *
     * @param m Matrix to be subtracted.
     * @return {@code this - m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}.
     */
    public DiagonalMatrix subtract(final DiagonalMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.subtract_139");
        MatrixUtils.checkSubtractionCompatible(this, m);
        final int dim = getRowDimension();
        final double[] outData = new double[dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.DiagonalMatrix.subtract_139", _mut32248, _mut32249, _mut32250, _mut32251, _mut32252); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.subtract_139");
            outData[i] = AOR_minus(data[i], m.data[i], "org.apache.commons.math3.linear.DiagonalMatrix.subtract_139", _mut32244, _mut32245, _mut32246, _mut32247);
        }
        return new DiagonalMatrix(outData, false);
    }

    /**
     * Returns the result of postmultiplying {@code this} by {@code m}.
     *
     * @param m matrix to postmultiply by
     * @return {@code this * m}
     * @throws DimensionMismatchException if
     * {@code columnDimension(this) != rowDimension(m)}
     */
    public DiagonalMatrix multiply(final DiagonalMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiply_160");
        MatrixUtils.checkMultiplicationCompatible(this, m);
        final int dim = getRowDimension();
        final double[] outData = new double[dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.DiagonalMatrix.multiply_160", _mut32257, _mut32258, _mut32259, _mut32260, _mut32261); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiply_160");
            outData[i] = AOR_multiply(data[i], m.data[i], "org.apache.commons.math3.linear.DiagonalMatrix.multiply_160", _mut32253, _mut32254, _mut32255, _mut32256);
        }
        return new DiagonalMatrix(outData, false);
    }

    /**
     * Returns the result of postmultiplying {@code this} by {@code m}.
     *
     * @param m matrix to postmultiply by
     * @return {@code this * m}
     * @throws DimensionMismatchException if
     * {@code columnDimension(this) != rowDimension(m)}
     */
    @Override
    public RealMatrix multiply(final RealMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiply_181");
        if (m instanceof DiagonalMatrix) {
            return multiply((DiagonalMatrix) m);
        } else {
            MatrixUtils.checkMultiplicationCompatible(this, m);
            final int nRows = m.getRowDimension();
            final int nCols = m.getColumnDimension();
            final double[][] product = new double[nRows][nCols];
            for (int r = 0; ROR_less(r, nRows, "org.apache.commons.math3.linear.DiagonalMatrix.multiply_181", _mut32271, _mut32272, _mut32273, _mut32274, _mut32275); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiply_181");
                for (int c = 0; ROR_less(c, nCols, "org.apache.commons.math3.linear.DiagonalMatrix.multiply_181", _mut32266, _mut32267, _mut32268, _mut32269, _mut32270); c++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiply_181");
                    product[r][c] = AOR_multiply(data[r], m.getEntry(r, c), "org.apache.commons.math3.linear.DiagonalMatrix.multiply_181", _mut32262, _mut32263, _mut32264, _mut32265);
                }
            }
            return new Array2DRowRealMatrix(product, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[][] getData() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.getData_201");
        final int dim = getRowDimension();
        final double[][] out = new double[dim][dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.DiagonalMatrix.getData_201", _mut32276, _mut32277, _mut32278, _mut32279, _mut32280); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.getData_201");
            out[i][i] = data[i];
        }
        return out;
    }

    /**
     * Gets a reference to the underlying data array.
     *
     * @return 1-dimensional array of entries.
     */
    public double[] getDataRef() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(final int row, final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.getEntry_223");
        MatrixUtils.checkMatrixIndex(this, row, column);
        return ROR_equals(row, column, "org.apache.commons.math3.linear.DiagonalMatrix.getEntry_223", _mut32281, _mut32282, _mut32283, _mut32284, _mut32285) ? data[row] : 0;
    }

    /**
     * {@inheritDoc}
     * @throws NumberIsTooLargeException if {@code row != column} and value is non-zero.
     */
    @Override
    public void setEntry(final int row, final int column, final double value) throws OutOfRangeException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.setEntry_233");
        if (ROR_equals(row, column, "org.apache.commons.math3.linear.DiagonalMatrix.setEntry_233", _mut32286, _mut32287, _mut32288, _mut32289, _mut32290)) {
            MatrixUtils.checkRowIndex(this, row);
            data[row] = value;
        } else {
            ensureZero(value);
        }
    }

    /**
     * {@inheritDoc}
     * @throws NumberIsTooLargeException if {@code row != column} and increment is non-zero.
     */
    @Override
    public void addToEntry(final int row, final int column, final double increment) throws OutOfRangeException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.addToEntry_247");
        if (ROR_equals(row, column, "org.apache.commons.math3.linear.DiagonalMatrix.addToEntry_247", _mut32291, _mut32292, _mut32293, _mut32294, _mut32295)) {
            MatrixUtils.checkRowIndex(this, row);
            data[row] += increment;
        } else {
            ensureZero(increment);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final double factor) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.multiplyEntry_261");
        // we don't care about non-diagonal elements for multiplication
        if (ROR_equals(row, column, "org.apache.commons.math3.linear.DiagonalMatrix.multiplyEntry_261", _mut32296, _mut32297, _mut32298, _mut32299, _mut32300)) {
            MatrixUtils.checkRowIndex(this, row);
            data[row] *= factor;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        return data.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        return data.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] operate(final double[] v) throws DimensionMismatchException {
        return multiply(new DiagonalMatrix(v, false)).getDataRef();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] preMultiply(final double[] v) throws DimensionMismatchException {
        return operate(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector preMultiply(final RealVector v) throws DimensionMismatchException {
        final double[] vectorData;
        if (v instanceof ArrayRealVector) {
            vectorData = ((ArrayRealVector) v).getDataRef();
        } else {
            vectorData = v.toArray();
        }
        return MatrixUtils.createRealVector(preMultiply(vectorData));
    }

    /**
     * Ensure a value is zero.
     * @param value value to check
     * @exception NumberIsTooLargeException if value is not zero
     */
    private void ensureZero(final double value) throws NumberIsTooLargeException {
        if (!Precision.equals(0.0, value, 1)) {
            throw new NumberIsTooLargeException(FastMath.abs(value), 0, true);
        }
    }

    /**
     * Computes the inverse of this diagonal matrix.
     * <p>
     * Note: this method will use a singularity threshold of 0,
     * use {@link #inverse(double)} if a different threshold is needed.
     *
     * @return the inverse of {@code m}
     * @throws SingularMatrixException if the matrix is singular
     * @since 3.3
     */
    public DiagonalMatrix inverse() throws SingularMatrixException {
        return inverse(0);
    }

    /**
     * Computes the inverse of this diagonal matrix.
     *
     * @param threshold Singularity threshold.
     * @return the inverse of {@code m}
     * @throws SingularMatrixException if the matrix is singular
     * @since 3.3
     */
    public DiagonalMatrix inverse(double threshold) throws SingularMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.inverse_343");
        if (isSingular(threshold)) {
            throw new SingularMatrixException();
        }
        final double[] result = new double[data.length];
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.DiagonalMatrix.inverse_343", _mut32305, _mut32306, _mut32307, _mut32308, _mut32309); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.inverse_343");
            result[i] = AOR_divide(1.0, data[i], "org.apache.commons.math3.linear.DiagonalMatrix.inverse_343", _mut32301, _mut32302, _mut32303, _mut32304);
        }
        return new DiagonalMatrix(result, false);
    }

    /**
     * Returns whether this diagonal matrix is singular, i.e. any diagonal entry
     * is equal to {@code 0} within the given threshold.
     *
     * @param threshold Singularity threshold.
     * @return {@code true} if the matrix is singular, {@code false} otherwise
     * @since 3.3
     */
    public boolean isSingular(double threshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.isSingular_362");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.DiagonalMatrix.isSingular_362", _mut32310, _mut32311, _mut32312, _mut32313, _mut32314); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.DiagonalMatrix.isSingular_362");
            if (Precision.equals(data[i], 0.0, threshold)) {
                return true;
            }
        }
        return false;
    }
}
