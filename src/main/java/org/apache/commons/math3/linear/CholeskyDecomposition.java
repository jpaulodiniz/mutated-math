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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the Cholesky decomposition of a matrix.
 * <p>The Cholesky decomposition of a real symmetric positive-definite
 * matrix A consists of a lower triangular matrix L with same size such
 * that: A = LL<sup>T</sup>. In a sense, this is the square root of A.</p>
 * <p>This class is based on the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library, with the
 * following changes:</p>
 * <ul>
 *   <li>a {@link #getLT() getLT} method has been added,</li>
 *   <li>the {@code isspd} method has been removed, since the constructor of
 *   this class throws a {@link NonPositiveDefiniteMatrixException} when a
 *   matrix cannot be decomposed,</li>
 *   <li>a {@link #getDeterminant() getDeterminant} method has been added,</li>
 *   <li>the {@code solve} method has been replaced by a {@link #getSolver()
 *   getSolver} method and the equivalent method provided by the returned
 *   {@link DecompositionSolver}.</li>
 * </ul>
 *
 * @see <a href="http://mathworld.wolfram.com/CholeskyDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Cholesky_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class CholeskyDecomposition {

    @Conditional
    public static boolean _mut25788 = false, _mut25789 = false, _mut25790 = false, _mut25791 = false, _mut25792 = false, _mut25793 = false, _mut25794 = false, _mut25795 = false, _mut25796 = false, _mut25797 = false, _mut25798 = false, _mut25799 = false, _mut25800 = false, _mut25801 = false, _mut25802 = false, _mut25803 = false, _mut25804 = false, _mut25805 = false, _mut25806 = false, _mut25807 = false, _mut25808 = false, _mut25809 = false, _mut25810 = false, _mut25811 = false, _mut25812 = false, _mut25813 = false, _mut25814 = false, _mut25815 = false, _mut25816 = false, _mut25817 = false, _mut25818 = false, _mut25819 = false, _mut25820 = false, _mut25821 = false, _mut25822 = false, _mut25823 = false, _mut25824 = false, _mut25825 = false, _mut25826 = false, _mut25827 = false, _mut25828 = false, _mut25829 = false, _mut25830 = false, _mut25831 = false, _mut25832 = false, _mut25833 = false, _mut25834 = false, _mut25835 = false, _mut25836 = false, _mut25837 = false, _mut25838 = false, _mut25839 = false, _mut25840 = false, _mut25841 = false, _mut25842 = false, _mut25843 = false, _mut25844 = false, _mut25845 = false, _mut25846 = false, _mut25847 = false, _mut25848 = false, _mut25849 = false, _mut25850 = false, _mut25851 = false, _mut25852 = false, _mut25853 = false, _mut25854 = false, _mut25855 = false, _mut25856 = false, _mut25857 = false, _mut25858 = false, _mut25859 = false, _mut25860 = false, _mut25861 = false, _mut25862 = false, _mut25863 = false, _mut25864 = false, _mut25865 = false, _mut25866 = false, _mut25867 = false, _mut25868 = false, _mut25869 = false, _mut25870 = false, _mut25871 = false, _mut25872 = false, _mut25873 = false, _mut25874 = false, _mut25875 = false, _mut25876 = false, _mut25877 = false, _mut25878 = false, _mut25879 = false, _mut25880 = false, _mut25881 = false, _mut25882 = false, _mut25883 = false, _mut25884 = false, _mut25885 = false, _mut25886 = false, _mut25887 = false, _mut25888 = false, _mut25889 = false, _mut25890 = false, _mut25891 = false, _mut25892 = false, _mut25893 = false, _mut25894 = false, _mut25895 = false, _mut25896 = false, _mut25897 = false, _mut25898 = false, _mut25899 = false, _mut25900 = false, _mut25901 = false, _mut25902 = false, _mut25903 = false, _mut25904 = false, _mut25905 = false, _mut25906 = false, _mut25907 = false, _mut25908 = false, _mut25909 = false, _mut25910 = false, _mut25911 = false, _mut25912 = false, _mut25913 = false, _mut25914 = false, _mut25915 = false, _mut25916 = false, _mut25917 = false, _mut25918 = false, _mut25919 = false, _mut25920 = false, _mut25921 = false, _mut25922 = false, _mut25923 = false, _mut25924 = false, _mut25925 = false, _mut25926 = false, _mut25927 = false, _mut25928 = false, _mut25929 = false, _mut25930 = false, _mut25931 = false, _mut25932 = false, _mut25933 = false;

    /**
     * Default threshold above which off-diagonal elements are considered too different
     * and matrix not symmetric.
     */
    public static final double DEFAULT_RELATIVE_SYMMETRY_THRESHOLD = 1.0e-15;

    /**
     * Default threshold below which diagonal elements are considered null
     * and matrix not positive definite.
     */
    public static final double DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD = 1.0e-10;

    /**
     * Row-oriented storage for L<sup>T</sup> matrix data.
     */
    private double[][] lTData;

    /**
     * Cached value of L.
     */
    private RealMatrix cachedL;

    /**
     * Cached value of LT.
     */
    private RealMatrix cachedLT;

    /**
     * Calculates the Cholesky decomposition of the given matrix.
     * <p>
     * Calling this constructor is equivalent to call {@link
     * #CholeskyDecomposition(RealMatrix, double, double)} with the
     * thresholds set to the default values {@link
     * #DEFAULT_RELATIVE_SYMMETRY_THRESHOLD} and {@link
     * #DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD}
     * </p>
     * @param matrix the matrix to decompose
     * @throws NonSquareMatrixException if the matrix is not square.
     * @throws NonSymmetricMatrixException if the matrix is not symmetric.
     * @throws NonPositiveDefiniteMatrixException if the matrix is not
     * strictly positive definite.
     * @see #CholeskyDecomposition(RealMatrix, double, double)
     * @see #DEFAULT_RELATIVE_SYMMETRY_THRESHOLD
     * @see #DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD
     */
    public CholeskyDecomposition(final RealMatrix matrix) {
        this(matrix, DEFAULT_RELATIVE_SYMMETRY_THRESHOLD, DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD);
    }

    /**
     * Calculates the Cholesky decomposition of the given matrix.
     * @param matrix the matrix to decompose
     * @param relativeSymmetryThreshold threshold above which off-diagonal
     * elements are considered too different and matrix not symmetric
     * @param absolutePositivityThreshold threshold below which diagonal
     * elements are considered null and matrix not positive definite
     * @throws NonSquareMatrixException if the matrix is not square.
     * @throws NonSymmetricMatrixException if the matrix is not symmetric.
     * @throws NonPositiveDefiniteMatrixException if the matrix is not
     * strictly positive definite.
     * @see #CholeskyDecomposition(RealMatrix)
     * @see #DEFAULT_RELATIVE_SYMMETRY_THRESHOLD
     * @see #DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD
     */
    public CholeskyDecomposition(final RealMatrix matrix, final double relativeSymmetryThreshold, final double absolutePositivityThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int order = matrix.getRowDimension();
        lTData = matrix.getData();
        cachedL = null;
        cachedLT = null;
        // check the matrix before transformation
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25806, _mut25807, _mut25808, _mut25809, _mut25810); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
            final double[] lI = lTData[i];
            // check off-diagonal elements (and reset them to 0)
            for (int j = i + 1; ROR_less(j, order, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25801, _mut25802, _mut25803, _mut25804, _mut25805); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
                final double[] lJ = lTData[j];
                final double lIJ = lI[j];
                final double lJI = lJ[i];
                final double maxDelta = AOR_multiply(relativeSymmetryThreshold, FastMath.max(FastMath.abs(lIJ), FastMath.abs(lJI)), "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25788, _mut25789, _mut25790, _mut25791);
                if (ROR_greater(FastMath.abs(AOR_minus(lIJ, lJI, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25792, _mut25793, _mut25794, _mut25795)), maxDelta, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25796, _mut25797, _mut25798, _mut25799, _mut25800)) {
                    throw new NonSymmetricMatrixException(i, j, relativeSymmetryThreshold);
                }
                lJ[i] = 0;
            }
        }
        // transform the matrix
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25834, _mut25835, _mut25836, _mut25837, _mut25838); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
            final double[] ltI = lTData[i];
            // check diagonal element
            if (ROR_less_equals(ltI[i], absolutePositivityThreshold, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25811, _mut25812, _mut25813, _mut25814, _mut25815)) {
                throw new NonPositiveDefiniteMatrixException(ltI[i], i, absolutePositivityThreshold);
            }
            ltI[i] = FastMath.sqrt(ltI[i]);
            final double inverse = AOR_divide(1.0, ltI[i], "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25816, _mut25817, _mut25818, _mut25819);
            for (int q = order - 1; ROR_greater(q, i, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25829, _mut25830, _mut25831, _mut25832, _mut25833); --q) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
                ltI[q] *= inverse;
                final double[] ltQ = lTData[q];
                for (int p = q; ROR_less(p, order, "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25824, _mut25825, _mut25826, _mut25827, _mut25828); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103");
                    ltQ[p] -= AOR_multiply(ltI[q], ltI[p], "org.apache.commons.math3.linear.CholeskyDecomposition.CholeskyDecomposition_103", _mut25820, _mut25821, _mut25822, _mut25823);
                }
            }
        }
    }

    /**
     * Returns the matrix L of the decomposition.
     * <p>L is an lower-triangular matrix</p>
     * @return the L matrix
     */
    public RealMatrix getL() {
        if (cachedL == null) {
            cachedL = getLT().transpose();
        }
        return cachedL;
    }

    /**
     * Returns the transpose of the matrix L of the decomposition.
     * <p>L<sup>T</sup> is an upper-triangular matrix</p>
     * @return the transpose of the matrix L of the decomposition
     */
    public RealMatrix getLT() {
        if (cachedLT == null) {
            cachedLT = MatrixUtils.createRealMatrix(lTData);
        }
        // return the cached matrix
        return cachedLT;
    }

    /**
     * Return the determinant of the matrix
     * @return determinant of the matrix
     */
    public double getDeterminant() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.getDeterminant_188");
        double determinant = 1.0;
        for (int i = 0; ROR_less(i, lTData.length, "org.apache.commons.math3.linear.CholeskyDecomposition.getDeterminant_188", _mut25843, _mut25844, _mut25845, _mut25846, _mut25847); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.getDeterminant_188");
            double lTii = lTData[i][i];
            determinant *= AOR_multiply(lTii, lTii, "org.apache.commons.math3.linear.CholeskyDecomposition.getDeterminant_188", _mut25839, _mut25840, _mut25841, _mut25842);
        }
        return determinant;
    }

    /**
     * Get a solver for finding the A &times; X = B solution in least square sense.
     * @return a solver
     */
    public DecompositionSolver getSolver() {
        return new Solver(lTData);
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * Row-oriented storage for L<sup>T</sup> matrix data.
         */
        private final double[][] lTData;

        /**
         * Build a solver from decomposed matrix.
         * @param lTData row-oriented storage for L<sup>T</sup> matrix data
         */
        private Solver(final double[][] lTData) {
            this.lTData = lTData;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNonSingular() {
            // if we get this far, the matrix was positive definite, hence non-singular
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public RealVector solve(final RealVector b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_225");
            final int m = lTData.length;
            if (ROR_not_equals(b.getDimension(), m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25848, _mut25849, _mut25850, _mut25851, _mut25852)) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            final double[] x = b.toArray();
            // Solve LY = b
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25862, _mut25863, _mut25864, _mut25865, _mut25866); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_225");
                final double[] lJ = lTData[j];
                x[j] /= lJ[j];
                final double xJ = x[j];
                for (int i = j + 1; ROR_less(i, m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25857, _mut25858, _mut25859, _mut25860, _mut25861); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_225");
                    x[i] -= AOR_multiply(xJ, lJ[i], "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25853, _mut25854, _mut25855, _mut25856);
                }
            }
            // Solve LTX = Y
            for (int j = m - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25876, _mut25877, _mut25878, _mut25879, _mut25880); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_225");
                x[j] /= lTData[j][j];
                final double xJ = x[j];
                for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25871, _mut25872, _mut25873, _mut25874, _mut25875); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_225");
                    x[i] -= AOR_multiply(xJ, lTData[i][j], "org.apache.commons.math3.linear.CholeskyDecomposition.solve_225", _mut25867, _mut25868, _mut25869, _mut25870);
                }
            }
            return new ArrayRealVector(x, false);
        }

        /**
         * {@inheritDoc}
         */
        public RealMatrix solve(RealMatrix b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
            final int m = lTData.length;
            if (ROR_not_equals(b.getRowDimension(), m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25881, _mut25882, _mut25883, _mut25884, _mut25885)) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            final int nColB = b.getColumnDimension();
            final double[][] x = b.getData();
            // Solve LY = b
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25905, _mut25906, _mut25907, _mut25908, _mut25909); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                final double[] lJ = lTData[j];
                final double lJJ = lJ[j];
                final double[] xJ = x[j];
                for (int k = 0; ROR_less(k, nColB, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25886, _mut25887, _mut25888, _mut25889, _mut25890); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                    xJ[k] /= lJJ;
                }
                for (int i = j + 1; ROR_less(i, m, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25900, _mut25901, _mut25902, _mut25903, _mut25904); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                    final double[] xI = x[i];
                    final double lJI = lJ[i];
                    for (int k = 0; ROR_less(k, nColB, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25895, _mut25896, _mut25897, _mut25898, _mut25899); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                        xI[k] -= AOR_multiply(xJ[k], lJI, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25891, _mut25892, _mut25893, _mut25894);
                    }
                }
            }
            // Solve LTX = Y
            for (int j = m - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25929, _mut25930, _mut25931, _mut25932, _mut25933); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                final double lJJ = lTData[j][j];
                final double[] xJ = x[j];
                for (int k = 0; ROR_less(k, nColB, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25910, _mut25911, _mut25912, _mut25913, _mut25914); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                    xJ[k] /= lJJ;
                }
                for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25924, _mut25925, _mut25926, _mut25927, _mut25928); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                    final double[] xI = x[i];
                    final double lIJ = lTData[i][j];
                    for (int k = 0; ROR_less(k, nColB, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25919, _mut25920, _mut25921, _mut25922, _mut25923); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.CholeskyDecomposition.solve_256");
                        xI[k] -= AOR_multiply(xJ[k], lIJ, "org.apache.commons.math3.linear.CholeskyDecomposition.solve_256", _mut25915, _mut25916, _mut25917, _mut25918);
                    }
                }
            }
            return new Array2DRowRealMatrix(x);
        }

        /**
         * Get the inverse of the decomposed matrix.
         *
         * @return the inverse matrix.
         */
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(lTData.length));
        }
    }
}
