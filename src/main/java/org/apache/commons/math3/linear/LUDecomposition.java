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
 * Calculates the LUP-decomposition of a square matrix.
 * <p>The LUP-decomposition of a matrix A consists of three matrices L, U and
 * P that satisfy: P&times;A = L&times;U. L is lower triangular (with unit
 * diagonal terms), U is upper triangular and P is a permutation matrix. All
 * matrices are m&times;m.</p>
 * <p>As shown by the presence of the P matrix, this decomposition is
 * implemented using partial pivoting.</p>
 * <p>This class is based on the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library.</p>
 * <ul>
 *   <li>a {@link #getP() getP} method has been added,</li>
 *   <li>the {@code det} method has been renamed as {@link #getDeterminant()
 *   getDeterminant},</li>
 *   <li>the {@code getDoublePivot} method has been removed (but the int based
 *   {@link #getPivot() getPivot} method has been kept),</li>
 *   <li>the {@code solve} and {@code isNonSingular} methods have been replaced
 *   by a {@link #getSolver() getSolver} method and the equivalent methods
 *   provided by the returned {@link DecompositionSolver}.</li>
 * </ul>
 *
 * @see <a href="http://mathworld.wolfram.com/LUDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/LU_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class LUDecomposition {

    @Conditional
    public static boolean _mut28888 = false, _mut28889 = false, _mut28890 = false, _mut28891 = false, _mut28892 = false, _mut28893 = false, _mut28894 = false, _mut28895 = false, _mut28896 = false, _mut28897 = false, _mut28898 = false, _mut28899 = false, _mut28900 = false, _mut28901 = false, _mut28902 = false, _mut28903 = false, _mut28904 = false, _mut28905 = false, _mut28906 = false, _mut28907 = false, _mut28908 = false, _mut28909 = false, _mut28910 = false, _mut28911 = false, _mut28912 = false, _mut28913 = false, _mut28914 = false, _mut28915 = false, _mut28916 = false, _mut28917 = false, _mut28918 = false, _mut28919 = false, _mut28920 = false, _mut28921 = false, _mut28922 = false, _mut28923 = false, _mut28924 = false, _mut28925 = false, _mut28926 = false, _mut28927 = false, _mut28928 = false, _mut28929 = false, _mut28930 = false, _mut28931 = false, _mut28932 = false, _mut28933 = false, _mut28934 = false, _mut28935 = false, _mut28936 = false, _mut28937 = false, _mut28938 = false, _mut28939 = false, _mut28940 = false, _mut28941 = false, _mut28942 = false, _mut28943 = false, _mut28944 = false, _mut28945 = false, _mut28946 = false, _mut28947 = false, _mut28948 = false, _mut28949 = false, _mut28950 = false, _mut28951 = false, _mut28952 = false, _mut28953 = false, _mut28954 = false, _mut28955 = false, _mut28956 = false, _mut28957 = false, _mut28958 = false, _mut28959 = false, _mut28960 = false, _mut28961 = false, _mut28962 = false, _mut28963 = false, _mut28964 = false, _mut28965 = false, _mut28966 = false, _mut28967 = false, _mut28968 = false, _mut28969 = false, _mut28970 = false, _mut28971 = false, _mut28972 = false, _mut28973 = false, _mut28974 = false, _mut28975 = false, _mut28976 = false, _mut28977 = false, _mut28978 = false, _mut28979 = false, _mut28980 = false, _mut28981 = false, _mut28982 = false, _mut28983 = false, _mut28984 = false, _mut28985 = false, _mut28986 = false, _mut28987 = false, _mut28988 = false, _mut28989 = false, _mut28990 = false, _mut28991 = false, _mut28992 = false, _mut28993 = false, _mut28994 = false, _mut28995 = false, _mut28996 = false, _mut28997 = false, _mut28998 = false, _mut28999 = false, _mut29000 = false, _mut29001 = false, _mut29002 = false, _mut29003 = false, _mut29004 = false, _mut29005 = false, _mut29006 = false, _mut29007 = false, _mut29008 = false, _mut29009 = false, _mut29010 = false, _mut29011 = false, _mut29012 = false, _mut29013 = false, _mut29014 = false, _mut29015 = false, _mut29016 = false, _mut29017 = false, _mut29018 = false, _mut29019 = false, _mut29020 = false, _mut29021 = false, _mut29022 = false, _mut29023 = false, _mut29024 = false, _mut29025 = false, _mut29026 = false, _mut29027 = false, _mut29028 = false, _mut29029 = false, _mut29030 = false, _mut29031 = false, _mut29032 = false, _mut29033 = false, _mut29034 = false, _mut29035 = false, _mut29036 = false, _mut29037 = false, _mut29038 = false, _mut29039 = false, _mut29040 = false, _mut29041 = false, _mut29042 = false, _mut29043 = false, _mut29044 = false, _mut29045 = false, _mut29046 = false, _mut29047 = false, _mut29048 = false, _mut29049 = false, _mut29050 = false, _mut29051 = false, _mut29052 = false, _mut29053 = false, _mut29054 = false, _mut29055 = false, _mut29056 = false, _mut29057 = false, _mut29058 = false, _mut29059 = false, _mut29060 = false, _mut29061 = false, _mut29062 = false, _mut29063 = false, _mut29064 = false, _mut29065 = false, _mut29066 = false, _mut29067 = false, _mut29068 = false, _mut29069 = false, _mut29070 = false, _mut29071 = false, _mut29072 = false, _mut29073 = false, _mut29074 = false, _mut29075 = false, _mut29076 = false, _mut29077 = false, _mut29078 = false, _mut29079 = false;

    /**
     * Default bound to determine effective singularity in LU decomposition.
     */
    private static final double DEFAULT_TOO_SMALL = 1e-11;

    /**
     * Entries of LU decomposition.
     */
    private final double[][] lu;

    /**
     * Pivot permutation associated with LU decomposition.
     */
    private final int[] pivot;

    /**
     * Parity of the permutation associated with the LU decomposition.
     */
    private boolean even;

    /**
     * Singularity indicator.
     */
    private boolean singular;

    /**
     * Cached value of L.
     */
    private RealMatrix cachedL;

    /**
     * Cached value of U.
     */
    private RealMatrix cachedU;

    /**
     * Cached value of P.
     */
    private RealMatrix cachedP;

    /**
     * Calculates the LU-decomposition of the given matrix.
     * This constructor uses 1e-11 as default value for the singularity
     * threshold.
     *
     * @param matrix Matrix to decompose.
     * @throws NonSquareMatrixException if matrix is not square.
     */
    public LUDecomposition(RealMatrix matrix) {
        this(matrix, DEFAULT_TOO_SMALL);
    }

    /**
     * Calculates the LU-decomposition of the given matrix.
     * @param matrix The matrix to decompose.
     * @param singularityThreshold threshold (based on partial row norm)
     * under which a matrix is considered singular
     * @throws NonSquareMatrixException if matrix is not square
     */
    public LUDecomposition(RealMatrix matrix, double singularityThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int m = matrix.getColumnDimension();
        lu = matrix.getData();
        pivot = new int[m];
        cachedL = null;
        cachedU = null;
        cachedP = null;
        // Initialize permutation array and parity
        for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28888, _mut28889, _mut28890, _mut28891, _mut28892); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
            pivot[row] = row;
        }
        even = true;
        singular = false;
        // Loop over columns
        for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28946, _mut28947, _mut28948, _mut28949, _mut28950); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
            // upper
            for (int row = 0; ROR_less(row, col, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28902, _mut28903, _mut28904, _mut28905, _mut28906); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                final double[] luRow = lu[row];
                double sum = luRow[col];
                for (int i = 0; ROR_less(i, row, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28897, _mut28898, _mut28899, _mut28900, _mut28901); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                    sum -= AOR_multiply(luRow[i], lu[i][col], "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28893, _mut28894, _mut28895, _mut28896);
                }
                luRow[col] = sum;
            }
            // permutation row
            int max = col;
            double largest = Double.NEGATIVE_INFINITY;
            for (int row = col; ROR_less(row, m, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28921, _mut28922, _mut28923, _mut28924, _mut28925); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                final double[] luRow = lu[row];
                double sum = luRow[col];
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28911, _mut28912, _mut28913, _mut28914, _mut28915); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                    sum -= AOR_multiply(luRow[i], lu[i][col], "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28907, _mut28908, _mut28909, _mut28910);
                }
                luRow[col] = sum;
                // maintain best permutation choice
                if (ROR_greater(FastMath.abs(sum), largest, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28916, _mut28917, _mut28918, _mut28919, _mut28920)) {
                    largest = FastMath.abs(sum);
                    max = row;
                }
            }
            // Singularity check
            if (ROR_less(FastMath.abs(lu[max][col]), singularityThreshold, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28926, _mut28927, _mut28928, _mut28929, _mut28930)) {
                singular = true;
                return;
            }
            // Pivot if necessary
            if (ROR_not_equals(max, col, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28931, _mut28932, _mut28933, _mut28934, _mut28935)) {
                double tmp = 0;
                final double[] luMax = lu[max];
                final double[] luCol = lu[col];
                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28936, _mut28937, _mut28938, _mut28939, _mut28940); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                    tmp = luMax[i];
                    luMax[i] = luCol[i];
                    luCol[i] = tmp;
                }
                int temp = pivot[max];
                pivot[max] = pivot[col];
                pivot[col] = temp;
                even = !even;
            }
            // Divide the lower elements by the "winning" diagonal elt.
            final double luDiag = lu[col][col];
            for (int row = col + 1; ROR_less(row, m, "org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85", _mut28941, _mut28942, _mut28943, _mut28944, _mut28945); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.LUDecomposition_85");
                lu[row][col] /= luDiag;
            }
        }
    }

    /**
     * Returns the matrix L of the decomposition.
     * <p>L is a lower-triangular matrix</p>
     * @return the L matrix (or null if decomposed matrix is singular)
     */
    public RealMatrix getL() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getL_171");
        if ((_mut28951 ? ((cachedL == null) || !singular) : ((cachedL == null) && !singular))) {
            final int m = pivot.length;
            cachedL = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.getL_171", _mut28957, _mut28958, _mut28959, _mut28960, _mut28961); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getL_171");
                final double[] luI = lu[i];
                for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.linear.LUDecomposition.getL_171", _mut28952, _mut28953, _mut28954, _mut28955, _mut28956); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getL_171");
                    cachedL.setEntry(i, j, luI[j]);
                }
                cachedL.setEntry(i, i, 1.0);
            }
        }
        return cachedL;
    }

    /**
     * Returns the matrix U of the decomposition.
     * <p>U is an upper-triangular matrix</p>
     * @return the U matrix (or null if decomposed matrix is singular)
     */
    public RealMatrix getU() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getU_191");
        if ((_mut28962 ? ((cachedU == null) || !singular) : ((cachedU == null) && !singular))) {
            final int m = pivot.length;
            cachedU = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.getU_191", _mut28968, _mut28969, _mut28970, _mut28971, _mut28972); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getU_191");
                final double[] luI = lu[i];
                for (int j = i; ROR_less(j, m, "org.apache.commons.math3.linear.LUDecomposition.getU_191", _mut28963, _mut28964, _mut28965, _mut28966, _mut28967); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getU_191");
                    cachedU.setEntry(i, j, luI[j]);
                }
            }
        }
        return cachedU;
    }

    /**
     * Returns the P rows permutation matrix.
     * <p>P is a sparse matrix with exactly one element set to 1.0 in
     * each row and each column, all other elements being set to 0.0.</p>
     * <p>The positions of the 1 elements are given by the {@link #getPivot()
     * pivot permutation vector}.</p>
     * @return the P rows permutation matrix (or null if decomposed matrix is singular)
     * @see #getPivot()
     */
    public RealMatrix getP() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getP_214");
        if ((_mut28973 ? ((cachedP == null) || !singular) : ((cachedP == null) && !singular))) {
            final int m = pivot.length;
            cachedP = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.getP_214", _mut28974, _mut28975, _mut28976, _mut28977, _mut28978); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getP_214");
                cachedP.setEntry(i, pivot[i], 1.0);
            }
        }
        return cachedP;
    }

    /**
     * Returns the pivot permutation vector.
     * @return the pivot permutation vector
     * @see #getP()
     */
    public int[] getPivot() {
        return pivot.clone();
    }

    /**
     * Return the determinant of the matrix
     * @return determinant of the matrix
     */
    public double getDeterminant() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getDeterminant_238");
        if (singular) {
            return 0;
        } else {
            final int m = pivot.length;
            double determinant = even ? 1 : -1;
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.getDeterminant_238", _mut28979, _mut28980, _mut28981, _mut28982, _mut28983); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.getDeterminant_238");
                determinant *= lu[i][i];
            }
            return determinant;
        }
    }

    /**
     * Get a solver for finding the A &times; X = B solution in exact linear
     * sense.
     * @return a solver
     */
    public DecompositionSolver getSolver() {
        return new Solver(lu, pivot, singular);
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * Entries of LU decomposition.
         */
        private final double[][] lu;

        /**
         * Pivot permutation associated with LU decomposition.
         */
        private final int[] pivot;

        /**
         * Singularity indicator.
         */
        private final boolean singular;

        /**
         * Build a solver from decomposed matrix.
         * @param lu entries of LU decomposition
         * @param pivot pivot permutation associated with LU decomposition
         * @param singular singularity indicator
         */
        private Solver(final double[][] lu, final int[] pivot, final boolean singular) {
            this.lu = lu;
            this.pivot = pivot;
            this.singular = singular;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNonSingular() {
            return !singular;
        }

        /**
         * {@inheritDoc}
         */
        public RealVector solve(RealVector b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
            final int m = pivot.length;
            if (ROR_not_equals(b.getDimension(), m, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut28984, _mut28985, _mut28986, _mut28987, _mut28988)) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            if (singular) {
                throw new SingularMatrixException();
            }
            final double[] bp = new double[m];
            // Apply permutations to b
            for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut28989, _mut28990, _mut28991, _mut28992, _mut28993); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
                bp[row] = b.getEntry(pivot[row]);
            }
            // Solve LY = b
            for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut29003, _mut29004, _mut29005, _mut29006, _mut29007); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
                final double bpCol = bp[col];
                for (int i = col + 1; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut28998, _mut28999, _mut29000, _mut29001, _mut29002); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
                    bp[i] -= AOR_multiply(bpCol, lu[i][col], "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut28994, _mut28995, _mut28996, _mut28997);
                }
            }
            // Solve UX = Y
            for (int col = m - 1; ROR_greater_equals(col, 0, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut29017, _mut29018, _mut29019, _mut29020, _mut29021); col--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
                bp[col] /= lu[col][col];
                final double bpCol = bp[col];
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut29012, _mut29013, _mut29014, _mut29015, _mut29016); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_290");
                    bp[i] -= AOR_multiply(bpCol, lu[i][col], "org.apache.commons.math3.linear.LUDecomposition.solve_290", _mut29008, _mut29009, _mut29010, _mut29011);
                }
            }
            return new ArrayRealVector(bp, false);
        }

        /**
         * {@inheritDoc}
         */
        public RealMatrix solve(RealMatrix b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
            final int m = pivot.length;
            if (ROR_not_equals(b.getRowDimension(), m, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29022, _mut29023, _mut29024, _mut29025, _mut29026)) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            if (singular) {
                throw new SingularMatrixException();
            }
            final int nColB = b.getColumnDimension();
            // Apply permutations to b
            final double[][] bp = new double[m][nColB];
            for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29032, _mut29033, _mut29034, _mut29035, _mut29036); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                final double[] bpRow = bp[row];
                final int pRow = pivot[row];
                for (int col = 0; ROR_less(col, nColB, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29027, _mut29028, _mut29029, _mut29030, _mut29031); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                    bpRow[col] = b.getEntry(pRow, col);
                }
            }
            // Solve LY = b
            for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29051, _mut29052, _mut29053, _mut29054, _mut29055); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                final double[] bpCol = bp[col];
                for (int i = col + 1; ROR_less(i, m, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29046, _mut29047, _mut29048, _mut29049, _mut29050); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                    final double[] bpI = bp[i];
                    final double luICol = lu[i][col];
                    for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29041, _mut29042, _mut29043, _mut29044, _mut29045); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                        bpI[j] -= AOR_multiply(bpCol[j], luICol, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29037, _mut29038, _mut29039, _mut29040);
                    }
                }
            }
            // Solve UX = Y
            for (int col = m - 1; ROR_greater_equals(col, 0, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29075, _mut29076, _mut29077, _mut29078, _mut29079); col--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                final double[] bpCol = bp[col];
                final double luDiag = lu[col][col];
                for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29056, _mut29057, _mut29058, _mut29059, _mut29060); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                    bpCol[j] /= luDiag;
                }
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29070, _mut29071, _mut29072, _mut29073, _mut29074); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                    final double[] bpI = bp[i];
                    final double luICol = lu[i][col];
                    for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29065, _mut29066, _mut29067, _mut29068, _mut29069); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.LUDecomposition.solve_327");
                        bpI[j] -= AOR_multiply(bpCol[j], luICol, "org.apache.commons.math3.linear.LUDecomposition.solve_327", _mut29061, _mut29062, _mut29063, _mut29064);
                    }
                }
            }
            return new Array2DRowRealMatrix(bp, false);
        }

        /**
         * Get the inverse of the decomposed matrix.
         *
         * @return the inverse matrix.
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(pivot.length));
        }
    }
}
