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
 * Calculates the rank-revealing QR-decomposition of a matrix, with column pivoting.
 * <p>The rank-revealing QR-decomposition of a matrix A consists of three matrices Q,
 * R and P such that AP=QR.  Q is orthogonal (Q<sup>T</sup>Q = I), and R is upper triangular.
 * If A is m&times;n, Q is m&times;m and R is m&times;n and P is n&times;n.</p>
 * <p>QR decomposition with column pivoting produces a rank-revealing QR
 * decomposition and the {@link #getRank(double)} method may be used to return the rank of the
 * input matrix A.</p>
 * <p>This class compute the decomposition using Householder reflectors.</p>
 * <p>For efficiency purposes, the decomposition in packed form is transposed.
 * This allows inner loop to iterate inside rows, which is much more cache-efficient
 * in Java.</p>
 * <p>This class is based on the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library, with the
 * following changes:</p>
 * <ul>
 *   <li>a {@link #getQT() getQT} method has been added,</li>
 *   <li>the {@code solve} and {@code isFullRank} methods have been replaced
 *   by a {@link #getSolver() getSolver} method and the equivalent methods
 *   provided by the returned {@link DecompositionSolver}.</li>
 * </ul>
 *
 * @see <a href="http://mathworld.wolfram.com/QRDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/QR_decomposition">Wikipedia</a>
 *
 * @since 3.2
 */
public class RRQRDecomposition extends QRDecomposition {

    @Conditional
    public static boolean _mut33726 = false, _mut33727 = false, _mut33728 = false, _mut33729 = false, _mut33730 = false, _mut33731 = false, _mut33732 = false, _mut33733 = false, _mut33734 = false, _mut33735 = false, _mut33736 = false, _mut33737 = false, _mut33738 = false, _mut33739 = false, _mut33740 = false, _mut33741 = false, _mut33742 = false, _mut33743 = false, _mut33744 = false, _mut33745 = false, _mut33746 = false, _mut33747 = false, _mut33748 = false, _mut33749 = false, _mut33750 = false, _mut33751 = false, _mut33752 = false, _mut33753 = false, _mut33754 = false, _mut33755 = false, _mut33756 = false, _mut33757 = false, _mut33758 = false, _mut33759 = false, _mut33760 = false, _mut33761 = false, _mut33762 = false, _mut33763 = false, _mut33764 = false, _mut33765 = false, _mut33766 = false, _mut33767 = false, _mut33768 = false, _mut33769 = false, _mut33770 = false, _mut33771 = false, _mut33772 = false, _mut33773 = false, _mut33774 = false, _mut33775 = false, _mut33776 = false, _mut33777 = false, _mut33778 = false, _mut33779 = false, _mut33780 = false, _mut33781 = false, _mut33782 = false, _mut33783 = false, _mut33784 = false, _mut33785 = false, _mut33786 = false, _mut33787 = false, _mut33788 = false, _mut33789 = false, _mut33790 = false, _mut33791 = false;

    /**
     * An array to record the column pivoting for later creation of P.
     */
    private int[] p;

    /**
     * Cached value of P.
     */
    private RealMatrix cachedP;

    /**
     * Calculates the QR-decomposition of the given matrix.
     * The singularity threshold defaults to zero.
     *
     * @param matrix The matrix to decompose.
     *
     * @see #RRQRDecomposition(RealMatrix, double)
     */
    public RRQRDecomposition(RealMatrix matrix) {
        this(matrix, 0d);
    }

    /**
     * Calculates the QR-decomposition of the given matrix.
     *
     * @param matrix The matrix to decompose.
     * @param threshold Singularity threshold.
     * @see #RRQRDecomposition(RealMatrix)
     */
    public RRQRDecomposition(RealMatrix matrix, double threshold) {
        super(matrix, threshold);
    }

    /**
     * Decompose matrix.
     * @param qrt transposed matrix
     */
    @Override
    protected void decompose(double[][] qrt) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.decompose_85");
        p = new int[qrt.length];
        for (int i = 0; ROR_less(i, p.length, "org.apache.commons.math3.linear.RRQRDecomposition.decompose_85", _mut33726, _mut33727, _mut33728, _mut33729, _mut33730); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.decompose_85");
            p[i] = i;
        }
        super.decompose(qrt);
    }

    /**
     * Perform Householder reflection for a minor A(minor, minor) of A.
     * @param minor minor index
     * @param qrt transposed matrix
     */
    @Override
    protected void performHouseholderReflection(int minor, double[][] qrt) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98");
        double l2NormSquaredMax = 0;
        // Find the unreduced column with the greatest L2-Norm
        int l2NormSquaredMaxIndex = minor;
        for (int i = minor; ROR_less(i, qrt.length, "org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98", _mut33745, _mut33746, _mut33747, _mut33748, _mut33749); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98");
            double l2NormSquared = 0;
            for (int j = 0; ROR_less(j, qrt[i].length, "org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98", _mut33735, _mut33736, _mut33737, _mut33738, _mut33739); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98");
                l2NormSquared += AOR_multiply(qrt[i][j], qrt[i][j], "org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98", _mut33731, _mut33732, _mut33733, _mut33734);
            }
            if (ROR_greater(l2NormSquared, l2NormSquaredMax, "org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98", _mut33740, _mut33741, _mut33742, _mut33743, _mut33744)) {
                l2NormSquaredMax = l2NormSquared;
                l2NormSquaredMaxIndex = i;
            }
        }
        // swap the current column with that with the greated L2-Norm and record in p
        if (ROR_not_equals(l2NormSquaredMaxIndex, minor, "org.apache.commons.math3.linear.RRQRDecomposition.performHouseholderReflection_98", _mut33750, _mut33751, _mut33752, _mut33753, _mut33754)) {
            double[] tmp1 = qrt[minor];
            qrt[minor] = qrt[l2NormSquaredMaxIndex];
            qrt[l2NormSquaredMaxIndex] = tmp1;
            int tmp2 = p[minor];
            p[minor] = p[l2NormSquaredMaxIndex];
            p[l2NormSquaredMaxIndex] = tmp2;
        }
        super.performHouseholderReflection(minor, qrt);
    }

    /**
     * Returns the pivot matrix, P, used in the QR Decomposition of matrix A such that AP = QR.
     *
     * If no pivoting is used in this decomposition then P is equal to the identity matrix.
     *
     * @return a permutation matrix.
     */
    public RealMatrix getP() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.getP_136");
        if (cachedP == null) {
            int n = p.length;
            cachedP = MatrixUtils.createRealMatrix(n, n);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.RRQRDecomposition.getP_136", _mut33755, _mut33756, _mut33757, _mut33758, _mut33759); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.getP_136");
                cachedP.setEntry(p[i], i, 1);
            }
        }
        return cachedP;
    }

    /**
     * Return the effective numerical matrix rank.
     * <p>The effective numerical rank is the number of non-negligible
     * singular values.</p>
     * <p>This implementation looks at Frobenius norms of the sequence of
     * bottom right submatrices.  When a large fall in norm is seen,
     * the rank is returned. The drop is computed as:</p>
     * <pre>
     *   (thisNorm/lastNorm) * rNorm < dropThreshold
     * </pre>
     * <p>
     * where thisNorm is the Frobenius norm of the current submatrix,
     * lastNorm is the Frobenius norm of the previous submatrix,
     * rNorm is is the Frobenius norm of the complete matrix
     * </p>
     *
     * @param dropThreshold threshold triggering rank computation
     * @return effective numerical matrix rank
     */
    public int getRank(final double dropThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.getRank_166");
        RealMatrix r = getR();
        int rows = r.getRowDimension();
        int columns = r.getColumnDimension();
        int rank = 1;
        double lastNorm = r.getFrobeniusNorm();
        double rNorm = lastNorm;
        while (ROR_less(rank, FastMath.min(rows, columns), "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33787, _mut33788, _mut33789, _mut33790, _mut33791)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RRQRDecomposition.getRank_166");
            double thisNorm = r.getSubMatrix(rank, AOR_minus(rows, 1, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33760, _mut33761, _mut33762, _mut33763), rank, AOR_minus(columns, 1, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33764, _mut33765, _mut33766, _mut33767)).getFrobeniusNorm();
            if ((_mut33786 ? (ROR_equals(thisNorm, 0, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33768, _mut33769, _mut33770, _mut33771, _mut33772) && ROR_less(AOR_multiply((AOR_divide(thisNorm, lastNorm, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33773, _mut33774, _mut33775, _mut33776)), rNorm, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33777, _mut33778, _mut33779, _mut33780), dropThreshold, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33781, _mut33782, _mut33783, _mut33784, _mut33785)) : (ROR_equals(thisNorm, 0, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33768, _mut33769, _mut33770, _mut33771, _mut33772) || ROR_less(AOR_multiply((AOR_divide(thisNorm, lastNorm, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33773, _mut33774, _mut33775, _mut33776)), rNorm, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33777, _mut33778, _mut33779, _mut33780), dropThreshold, "org.apache.commons.math3.linear.RRQRDecomposition.getRank_166", _mut33781, _mut33782, _mut33783, _mut33784, _mut33785)))) {
                break;
            }
            lastNorm = thisNorm;
            rank++;
        }
        return rank;
    }

    /**
     * Get a solver for finding the A &times; X = B solution in least square sense.
     * <p>
     * Least Square sense means a solver can be computed for an overdetermined system,
     * (i.e. a system with more equations than unknowns, which corresponds to a tall A
     * matrix with more rows than columns). In any case, if the matrix is singular
     * within the tolerance set at {@link RRQRDecomposition#RRQRDecomposition(RealMatrix,
     * double) construction}, an error will be triggered when
     * the {@link DecompositionSolver#solve(RealVector) solve} method will be called.
     * </p>
     * @return a solver
     */
    @Override
    public DecompositionSolver getSolver() {
        return new Solver(super.getSolver(), this.getP());
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * Upper level solver.
         */
        private final DecompositionSolver upper;

        /**
         * A permutation matrix for the pivots used in the QR decomposition
         */
        private RealMatrix p;

        /**
         * Build a solver from decomposed matrix.
         *
         * @param upper upper level solver.
         * @param p permutation matrix
         */
        private Solver(final DecompositionSolver upper, final RealMatrix p) {
            this.upper = upper;
            this.p = p;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNonSingular() {
            return upper.isNonSingular();
        }

        /**
         * {@inheritDoc}
         */
        public RealVector solve(RealVector b) {
            return p.operate(upper.solve(b));
        }

        /**
         * {@inheritDoc}
         */
        public RealMatrix solve(RealMatrix b) {
            return p.multiply(upper.solve(b));
        }

        /**
         * {@inheritDoc}
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(p.getRowDimension()));
        }
    }
}
