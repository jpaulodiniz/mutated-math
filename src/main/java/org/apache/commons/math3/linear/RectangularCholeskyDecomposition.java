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

import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the rectangular Cholesky decomposition of a matrix.
 * <p>The rectangular Cholesky decomposition of a real symmetric positive
 * semidefinite matrix A consists of a rectangular matrix B with the same
 * number of rows such that: A is almost equal to BB<sup>T</sup>, depending
 * on a user-defined tolerance. In a sense, this is the square root of A.</p>
 * <p>The difference with respect to the regular {@link CholeskyDecomposition}
 * is that rows/columns may be permuted (hence the rectangular shape instead
 * of the traditional triangular shape) and there is a threshold to ignore
 * small diagonal elements. This is used for example to generate {@link
 * org.apache.commons.math3.random.CorrelatedRandomVectorGenerator correlated
 * random n-dimensions vectors} in a p-dimension subspace (p < n).
 * In other words, it allows generating random vectors from a covariance
 * matrix that is only positive semidefinite, and not positive definite.</p>
 * <p>Rectangular Cholesky decomposition is <em>not</em> suited for solving
 * linear systems, so it does not provide any {@link DecompositionSolver
 * decomposition solver}.</p>
 *
 * @see <a href="http://mathworld.wolfram.com/CholeskyDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Cholesky_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class RectangularCholeskyDecomposition {

    @Conditional
    public static boolean _mut32521 = false, _mut32522 = false, _mut32523 = false, _mut32524 = false, _mut32525 = false, _mut32526 = false, _mut32527 = false, _mut32528 = false, _mut32529 = false, _mut32530 = false, _mut32531 = false, _mut32532 = false, _mut32533 = false, _mut32534 = false, _mut32535 = false, _mut32536 = false, _mut32537 = false, _mut32538 = false, _mut32539 = false, _mut32540 = false, _mut32541 = false, _mut32542 = false, _mut32543 = false, _mut32544 = false, _mut32545 = false, _mut32546 = false, _mut32547 = false, _mut32548 = false, _mut32549 = false, _mut32550 = false, _mut32551 = false, _mut32552 = false, _mut32553 = false, _mut32554 = false, _mut32555 = false, _mut32556 = false, _mut32557 = false, _mut32558 = false, _mut32559 = false, _mut32560 = false, _mut32561 = false, _mut32562 = false, _mut32563 = false, _mut32564 = false, _mut32565 = false, _mut32566 = false, _mut32567 = false, _mut32568 = false, _mut32569 = false, _mut32570 = false, _mut32571 = false, _mut32572 = false, _mut32573 = false, _mut32574 = false, _mut32575 = false, _mut32576 = false, _mut32577 = false, _mut32578 = false, _mut32579 = false, _mut32580 = false, _mut32581 = false, _mut32582 = false, _mut32583 = false, _mut32584 = false, _mut32585 = false, _mut32586 = false, _mut32587 = false, _mut32588 = false, _mut32589 = false, _mut32590 = false, _mut32591 = false, _mut32592 = false, _mut32593 = false, _mut32594 = false, _mut32595 = false, _mut32596 = false, _mut32597 = false, _mut32598 = false, _mut32599 = false, _mut32600 = false, _mut32601 = false, _mut32602 = false, _mut32603 = false, _mut32604 = false, _mut32605 = false, _mut32606 = false, _mut32607 = false, _mut32608 = false, _mut32609 = false, _mut32610 = false, _mut32611 = false, _mut32612 = false, _mut32613 = false;

    /**
     * Permutated Cholesky root of the symmetric positive semidefinite matrix.
     */
    private final RealMatrix root;

    /**
     * Rank of the symmetric positive semidefinite matrix.
     */
    private int rank;

    /**
     * Decompose a symmetric positive semidefinite matrix.
     * <p>
     * <b>Note:</b> this constructor follows the linpack method to detect dependent
     * columns by proceeding with the Cholesky algorithm until a nonpositive diagonal
     * element is encountered.
     *
     * @see <a href="http://eprints.ma.man.ac.uk/1193/01/covered/MIMS_ep2008_56.pdf">
     * Analysis of the Cholesky Decomposition of a Semi-definite Matrix</a>
     *
     * @param matrix Symmetric positive semidefinite matrix.
     * @exception NonPositiveDefiniteMatrixException if the matrix is not
     * positive semidefinite.
     * @since 3.1
     */
    public RectangularCholeskyDecomposition(RealMatrix matrix) throws NonPositiveDefiniteMatrixException {
        this(matrix, 0);
    }

    /**
     * Decompose a symmetric positive semidefinite matrix.
     *
     * @param matrix Symmetric positive semidefinite matrix.
     * @param small Diagonal elements threshold under which columns are
     * considered to be dependent on previous ones and are discarded.
     * @exception NonPositiveDefiniteMatrixException if the matrix is not
     * positive semidefinite.
     */
    public RectangularCholeskyDecomposition(RealMatrix matrix, double small) throws NonPositiveDefiniteMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
        final int order = matrix.getRowDimension();
        final double[][] c = matrix.getData();
        final double[][] b = new double[order][order];
        int[] index = new int[order];
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32521, _mut32522, _mut32523, _mut32524, _mut32525); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
            index[i] = i;
        }
        int r = 0;
        for (boolean loop = true; loop; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
            // find maximal diagonal element
            int swapR = r;
            for (int i = r + 1; ROR_less(i, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32531, _mut32532, _mut32533, _mut32534, _mut32535); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
                int ii = index[i];
                int isr = index[swapR];
                if (ROR_greater(c[ii][ii], c[isr][isr], "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32526, _mut32527, _mut32528, _mut32529, _mut32530)) {
                    swapR = i;
                }
            }
            // swap elements
            if (ROR_not_equals(swapR, r, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32536, _mut32537, _mut32538, _mut32539, _mut32540)) {
                final int tmpIndex = index[r];
                index[r] = index[swapR];
                index[swapR] = tmpIndex;
                final double[] tmpRow = b[r];
                b[r] = b[swapR];
                b[swapR] = tmpRow;
            }
            // check diagonal element
            int ir = index[r];
            if (ROR_less_equals(c[ir][ir], small, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32541, _mut32542, _mut32543, _mut32544, _mut32545)) {
                if (ROR_equals(r, 0, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32589, _mut32590, _mut32591, _mut32592, _mut32593)) {
                    throw new NonPositiveDefiniteMatrixException(c[ir][ir], ir, small);
                }
                // check remaining diagonal elements
                for (int i = r; ROR_less(i, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32599, _mut32600, _mut32601, _mut32602, _mut32603); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
                    if (ROR_less(c[index[i]][index[i]], -small, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32594, _mut32595, _mut32596, _mut32597, _mut32598)) {
                        // the symmetric positive semidefinite matrix is wrong
                        throw new NonPositiveDefiniteMatrixException(c[index[i]][index[i]], i, small);
                    }
                }
                // found the rank of the symmetric positive semidefinite matrix
                loop = false;
            } else {
                // transform the matrix
                final double sqrt = FastMath.sqrt(c[ir][ir]);
                b[r][r] = sqrt;
                final double inverse = AOR_divide(1, sqrt, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32546, _mut32547, _mut32548, _mut32549);
                final double inverse2 = AOR_divide(1, c[ir][ir], "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32550, _mut32551, _mut32552, _mut32553);
                for (int i = r + 1; ROR_less(i, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32579, _mut32580, _mut32581, _mut32582, _mut32583); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
                    final int ii = index[i];
                    final double e = AOR_multiply(inverse, c[ii][ir], "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32554, _mut32555, _mut32556, _mut32557);
                    b[i][r] = e;
                    c[ii][ii] -= AOR_multiply(AOR_multiply(c[ii][ir], c[ii][ir], "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32558, _mut32559, _mut32560, _mut32561), inverse2, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32562, _mut32563, _mut32564, _mut32565);
                    for (int j = r + 1; ROR_less(j, i, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32574, _mut32575, _mut32576, _mut32577, _mut32578); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
                        final int ij = index[j];
                        final double f = AOR_minus(c[ii][ij], AOR_multiply(e, b[j][r], "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32566, _mut32567, _mut32568, _mut32569), "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32570, _mut32571, _mut32572, _mut32573);
                        c[ii][ij] = f;
                        c[ij][ii] = f;
                    }
                }
                // prepare next iteration
                loop = ROR_less(++r, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32584, _mut32585, _mut32586, _mut32587, _mut32588);
            }
        }
        // build the root matrix
        rank = r;
        root = MatrixUtils.createRealMatrix(order, r);
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32609, _mut32610, _mut32611, _mut32612, _mut32613); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
            for (int j = 0; ROR_less(j, r, "org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81", _mut32604, _mut32605, _mut32606, _mut32607, _mut32608); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RectangularCholeskyDecomposition.RectangularCholeskyDecomposition_81");
                root.setEntry(index[i], j, b[i][j]);
            }
        }
    }

    /**
     * Get the root of the covariance matrix.
     * The root is the rectangular matrix <code>B</code> such that
     * the covariance matrix is equal to <code>B.B<sup>T</sup></code>
     * @return root of the square matrix
     * @see #getRank()
     */
    public RealMatrix getRootMatrix() {
        return root;
    }

    /**
     * Get the rank of the symmetric positive semidefinite matrix.
     * The r is the number of independent rows in the symmetric positive semidefinite
     * matrix, it is also the number of columns of the rectangular
     * matrix of the decomposition.
     * @return r of the square matrix.
     * @see #getRootMatrix()
     */
    public int getRank() {
        return rank;
    }
}
