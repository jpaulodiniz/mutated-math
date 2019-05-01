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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Covariance implementation that does not require input data to be
 * stored in memory. The size of the covariance matrix is specified in the
 * constructor. Specific elements of the matrix are incrementally updated with
 * calls to incrementRow() or increment Covariance().
 *
 * <p>This class is based on a paper written by Philippe P&eacute;bay:
 * <a href="http://prod.sandia.gov/techlib/access-control.cgi/2008/086212.pdf">
 * Formulas for Robust, One-Pass Parallel Computation of Covariances and
 * Arbitrary-Order Statistical Moments</a>, 2008, Technical Report SAND2008-6212,
 * Sandia National Laboratories.</p>
 *
 * <p>Note: the underlying covariance matrix is symmetric, thus only the
 * upper triangular part of the matrix is stored and updated each increment.</p>
 *
 * @since 3.0
 */
public class StorelessCovariance extends Covariance {

    @Conditional
    public static boolean _mut10596 = false, _mut10597 = false, _mut10598 = false, _mut10599 = false, _mut10600 = false, _mut10601 = false, _mut10602 = false, _mut10603 = false, _mut10604 = false, _mut10605 = false, _mut10606 = false, _mut10607 = false, _mut10608 = false, _mut10609 = false, _mut10610 = false, _mut10611 = false, _mut10612 = false, _mut10613 = false, _mut10614 = false, _mut10615 = false, _mut10616 = false, _mut10617 = false, _mut10618 = false, _mut10619 = false, _mut10620 = false, _mut10621 = false, _mut10622 = false, _mut10623 = false, _mut10624 = false, _mut10625 = false, _mut10626 = false, _mut10627 = false, _mut10628 = false, _mut10629 = false, _mut10630 = false, _mut10631 = false, _mut10632 = false, _mut10633 = false, _mut10634 = false, _mut10635 = false, _mut10636 = false, _mut10637 = false, _mut10638 = false, _mut10639 = false, _mut10640 = false, _mut10641 = false, _mut10642 = false, _mut10643 = false, _mut10644 = false, _mut10645 = false, _mut10646 = false, _mut10647 = false, _mut10648 = false, _mut10649 = false, _mut10650 = false, _mut10651 = false, _mut10652 = false, _mut10653 = false, _mut10654 = false, _mut10655 = false, _mut10656 = false, _mut10657 = false, _mut10658 = false, _mut10659 = false, _mut10660 = false, _mut10661 = false, _mut10662 = false, _mut10663 = false, _mut10664 = false, _mut10665 = false, _mut10666 = false, _mut10667 = false, _mut10668 = false, _mut10669 = false, _mut10670 = false, _mut10671 = false, _mut10672 = false, _mut10673 = false, _mut10674 = false, _mut10675 = false, _mut10676 = false, _mut10677 = false, _mut10678 = false, _mut10679 = false, _mut10680 = false, _mut10681 = false, _mut10682 = false, _mut10683 = false, _mut10684 = false, _mut10685 = false, _mut10686 = false, _mut10687 = false, _mut10688 = false, _mut10689 = false, _mut10690 = false, _mut10691 = false, _mut10692 = false, _mut10693 = false, _mut10694 = false;

    /**
     * the square covariance matrix (upper triangular part)
     */
    private StorelessBivariateCovariance[] covMatrix;

    /**
     * dimension of the square covariance matrix
     */
    private int dimension;

    /**
     * Create a bias corrected covariance matrix with a given dimension.
     *
     * @param dim the dimension of the square covariance matrix
     */
    public StorelessCovariance(final int dim) {
        this(dim, true);
    }

    /**
     * Create a covariance matrix with a given number of rows and columns and the
     * indicated bias correction.
     *
     * @param dim the dimension of the covariance matrix
     * @param biasCorrected if <code>true</code> the covariance estimate is corrected
     * for bias, i.e. n-1 in the denominator, otherwise there is no bias correction,
     * i.e. n in the denominator.
     */
    public StorelessCovariance(final int dim, final boolean biasCorrected) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.StorelessCovariance_68");
        dimension = dim;
        covMatrix = new StorelessBivariateCovariance[AOR_divide(AOR_multiply(dimension, (AOR_plus(dimension, 1, "org.apache.commons.math3.stat.correlation.StorelessCovariance.StorelessCovariance_68", _mut10596, _mut10597, _mut10598, _mut10599)), "org.apache.commons.math3.stat.correlation.StorelessCovariance.StorelessCovariance_68", _mut10600, _mut10601, _mut10602, _mut10603), 2, "org.apache.commons.math3.stat.correlation.StorelessCovariance.StorelessCovariance_68", _mut10604, _mut10605, _mut10606, _mut10607)];
        initializeMatrix(biasCorrected);
    }

    /**
     * Initialize the internal two-dimensional array of
     * {@link StorelessBivariateCovariance} instances.
     *
     * @param biasCorrected if the covariance estimate shall be corrected for bias
     */
    private void initializeMatrix(final boolean biasCorrected) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.initializeMatrix_80");
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.initializeMatrix_80", _mut10613, _mut10614, _mut10615, _mut10616, _mut10617); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.initializeMatrix_80");
            for (int j = 0; ROR_less(j, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.initializeMatrix_80", _mut10608, _mut10609, _mut10610, _mut10611, _mut10612); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.initializeMatrix_80");
                setElement(i, j, new StorelessBivariateCovariance(biasCorrected));
            }
        }
    }

    /**
     * Returns the index (i, j) translated into the one-dimensional
     * array used to store the upper triangular part of the symmetric
     * covariance matrix.
     *
     * @param i the row index
     * @param j the column index
     * @return the corresponding index in the matrix array
     */
    private int indexOf(final int i, final int j) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97");
        return ROR_less(j, i, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10618, _mut10619, _mut10620, _mut10621, _mut10622) ? AOR_plus(AOR_divide(AOR_multiply(i, (AOR_plus(i, 1, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10639, _mut10640, _mut10641, _mut10642)), "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10643, _mut10644, _mut10645, _mut10646), 2, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10647, _mut10648, _mut10649, _mut10650), j, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10651, _mut10652, _mut10653, _mut10654) : AOR_plus(AOR_divide(AOR_multiply(j, (AOR_plus(j, 1, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10623, _mut10624, _mut10625, _mut10626)), "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10627, _mut10628, _mut10629, _mut10630), 2, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10631, _mut10632, _mut10633, _mut10634), i, "org.apache.commons.math3.stat.correlation.StorelessCovariance.indexOf_97", _mut10635, _mut10636, _mut10637, _mut10638);
    }

    /**
     * Gets the element at index (i, j) from the covariance matrix
     * @param i the row index
     * @param j the column index
     * @return the {@link StorelessBivariateCovariance} element at the given index
     */
    private StorelessBivariateCovariance getElement(final int i, final int j) {
        return covMatrix[indexOf(i, j)];
    }

    /**
     * Sets the covariance element at index (i, j) in the covariance matrix
     * @param i the row index
     * @param j the column index
     * @param cov the {@link StorelessBivariateCovariance} element to be set
     */
    private void setElement(final int i, final int j, final StorelessBivariateCovariance cov) {
        covMatrix[indexOf(i, j)] = cov;
    }

    /**
     * Get the covariance for an individual element of the covariance matrix.
     *
     * @param xIndex row index in the covariance matrix
     * @param yIndex column index in the covariance matrix
     * @return the covariance of the given element
     * @throws NumberIsTooSmallException if the number of observations
     * in the cell is &lt; 2
     */
    public double getCovariance(final int xIndex, final int yIndex) throws NumberIsTooSmallException {
        return getElement(xIndex, yIndex).getResult();
    }

    /**
     * Increment the covariance matrix with one row of data.
     *
     * @param data array representing one row of data.
     * @throws DimensionMismatchException if the length of <code>rowData</code>
     * does not match with the covariance matrix
     */
    public void increment(final double[] data) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146");
        int length = data.length;
        if (ROR_not_equals(length, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146", _mut10655, _mut10656, _mut10657, _mut10658, _mut10659)) {
            throw new DimensionMismatchException(length, dimension);
        }
        // as only these parts are actually stored
        for (int i = 0; ROR_less(i, length, "org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146", _mut10665, _mut10666, _mut10667, _mut10668, _mut10669); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146");
            for (int j = i; ROR_less(j, length, "org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146", _mut10660, _mut10661, _mut10662, _mut10663, _mut10664); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.increment_146");
                getElement(i, j).increment(data[i], data[j]);
            }
        }
    }

    /**
     * Appends {@code sc} to this, effectively aggregating the computations in {@code sc}
     * with this.  After invoking this method, covariances returned should be close
     * to what would have been obtained by performing all of the {@link #increment(double[])}
     * operations in {@code sc} directly on this.
     *
     * @param sc externally computed StorelessCovariance to add to this
     * @throws DimensionMismatchException if the dimension of sc does not match this
     * @since 3.3
     */
    public void append(StorelessCovariance sc) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174");
        if (ROR_not_equals(sc.dimension, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174", _mut10670, _mut10671, _mut10672, _mut10673, _mut10674)) {
            throw new DimensionMismatchException(sc.dimension, dimension);
        }
        // as only these parts are actually stored
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174", _mut10680, _mut10681, _mut10682, _mut10683, _mut10684); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174");
            for (int j = i; ROR_less(j, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174", _mut10675, _mut10676, _mut10677, _mut10678, _mut10679); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.append_174");
                getElement(i, j).append(sc.getElement(i, j));
            }
        }
    }

    /**
     * {@inheritDoc}
     * @throws NumberIsTooSmallException if the number of observations
     * in a cell is &lt; 2
     */
    @Override
    public RealMatrix getCovarianceMatrix() throws NumberIsTooSmallException {
        return MatrixUtils.createRealMatrix(getData());
    }

    /**
     * Return the covariance matrix as two-dimensional array.
     *
     * @return a two-dimensional double array of covariance values
     * @throws NumberIsTooSmallException if the number of observations
     * for a cell is &lt; 2
     */
    public double[][] getData() throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.getData_205");
        final double[][] data = new double[dimension][dimension];
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.getData_205", _mut10690, _mut10691, _mut10692, _mut10693, _mut10694); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.getData_205");
            for (int j = 0; ROR_less(j, dimension, "org.apache.commons.math3.stat.correlation.StorelessCovariance.getData_205", _mut10685, _mut10686, _mut10687, _mut10688, _mut10689); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessCovariance.getData_205");
                data[i][j] = getElement(i, j).getResult();
            }
        }
        return data;
    }

    /**
     * This {@link Covariance} method is not supported by a {@link StorelessCovariance},
     * since the number of bivariate observations does not have to be the same for different
     * pairs of covariates - i.e., N as defined in {@link Covariance#getN()} is undefined.
     *
     * @return nothing as this implementation always throws a
     * {@link MathUnsupportedOperationException}
     * @throws MathUnsupportedOperationException in all cases
     */
    @Override
    public int getN() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }
}
