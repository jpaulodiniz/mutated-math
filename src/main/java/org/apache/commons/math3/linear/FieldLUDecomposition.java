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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the LUP-decomposition of a square matrix.
 * <p>The LUP-decomposition of a matrix A consists of three matrices
 * L, U and P that satisfy: PA = LU, L is lower triangular, and U is
 * upper triangular and P is a permutation matrix. All matrices are
 * m&times;m.</p>
 * <p>Since {@link FieldElement field elements} do not provide an ordering
 * operator, the permutation matrix is computed here only in order to avoid
 * a zero pivot element, no attempt is done to get the largest pivot
 * element.</p>
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
 * @param <T> the type of the field elements
 * @see <a href="http://mathworld.wolfram.com/LUDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/LU_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class FieldLUDecomposition<T extends FieldElement<T>> {

    @Conditional
    public static boolean _mut25934 = false, _mut25935 = false, _mut25936 = false, _mut25937 = false, _mut25938 = false, _mut25939 = false, _mut25940 = false, _mut25941 = false, _mut25942 = false, _mut25943 = false, _mut25944 = false, _mut25945 = false, _mut25946 = false, _mut25947 = false, _mut25948 = false, _mut25949 = false, _mut25950 = false, _mut25951 = false, _mut25952 = false, _mut25953 = false, _mut25954 = false, _mut25955 = false, _mut25956 = false, _mut25957 = false, _mut25958 = false, _mut25959 = false, _mut25960 = false, _mut25961 = false, _mut25962 = false, _mut25963 = false, _mut25964 = false, _mut25965 = false, _mut25966 = false, _mut25967 = false, _mut25968 = false, _mut25969 = false, _mut25970 = false, _mut25971 = false, _mut25972 = false, _mut25973 = false, _mut25974 = false, _mut25975 = false, _mut25976 = false, _mut25977 = false, _mut25978 = false, _mut25979 = false, _mut25980 = false, _mut25981 = false, _mut25982 = false, _mut25983 = false, _mut25984 = false, _mut25985 = false, _mut25986 = false, _mut25987 = false, _mut25988 = false, _mut25989 = false, _mut25990 = false, _mut25991 = false, _mut25992 = false, _mut25993 = false, _mut25994 = false, _mut25995 = false, _mut25996 = false, _mut25997 = false, _mut25998 = false, _mut25999 = false, _mut26000 = false, _mut26001 = false, _mut26002 = false, _mut26003 = false, _mut26004 = false, _mut26005 = false, _mut26006 = false, _mut26007 = false, _mut26008 = false, _mut26009 = false, _mut26010 = false, _mut26011 = false, _mut26012 = false, _mut26013 = false, _mut26014 = false, _mut26015 = false, _mut26016 = false, _mut26017 = false, _mut26018 = false, _mut26019 = false, _mut26020 = false, _mut26021 = false, _mut26022 = false, _mut26023 = false, _mut26024 = false, _mut26025 = false, _mut26026 = false, _mut26027 = false, _mut26028 = false, _mut26029 = false, _mut26030 = false, _mut26031 = false, _mut26032 = false, _mut26033 = false, _mut26034 = false, _mut26035 = false, _mut26036 = false, _mut26037 = false, _mut26038 = false, _mut26039 = false, _mut26040 = false, _mut26041 = false, _mut26042 = false, _mut26043 = false, _mut26044 = false, _mut26045 = false, _mut26046 = false, _mut26047 = false, _mut26048 = false, _mut26049 = false, _mut26050 = false, _mut26051 = false, _mut26052 = false, _mut26053 = false, _mut26054 = false, _mut26055 = false, _mut26056 = false, _mut26057 = false, _mut26058 = false, _mut26059 = false, _mut26060 = false, _mut26061 = false, _mut26062 = false, _mut26063 = false, _mut26064 = false, _mut26065 = false, _mut26066 = false, _mut26067 = false, _mut26068 = false, _mut26069 = false, _mut26070 = false, _mut26071 = false, _mut26072 = false, _mut26073 = false, _mut26074 = false, _mut26075 = false, _mut26076 = false, _mut26077 = false, _mut26078 = false, _mut26079 = false, _mut26080 = false, _mut26081 = false, _mut26082 = false, _mut26083 = false, _mut26084 = false, _mut26085 = false, _mut26086 = false, _mut26087 = false, _mut26088 = false, _mut26089 = false, _mut26090 = false, _mut26091 = false, _mut26092 = false, _mut26093 = false, _mut26094 = false, _mut26095 = false, _mut26096 = false, _mut26097 = false, _mut26098 = false, _mut26099 = false, _mut26100 = false, _mut26101 = false, _mut26102 = false, _mut26103 = false, _mut26104 = false, _mut26105 = false, _mut26106 = false, _mut26107 = false, _mut26108 = false, _mut26109 = false, _mut26110 = false, _mut26111 = false, _mut26112 = false, _mut26113 = false, _mut26114 = false, _mut26115 = false, _mut26116 = false, _mut26117 = false, _mut26118 = false, _mut26119 = false, _mut26120 = false, _mut26121 = false, _mut26122 = false, _mut26123 = false, _mut26124 = false, _mut26125 = false, _mut26126 = false, _mut26127 = false, _mut26128 = false, _mut26129 = false, _mut26130 = false, _mut26131 = false;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Entries of LU decomposition.
     */
    private T[][] lu;

    /**
     * Pivot permutation associated with LU decomposition.
     */
    private int[] pivot;

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
    private FieldMatrix<T> cachedL;

    /**
     * Cached value of U.
     */
    private FieldMatrix<T> cachedU;

    /**
     * Cached value of P.
     */
    private FieldMatrix<T> cachedP;

    /**
     * Calculates the LU-decomposition of the given matrix.
     * @param matrix The matrix to decompose.
     * @throws NonSquareMatrixException if matrix is not square
     */
    public FieldLUDecomposition(FieldMatrix<T> matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int m = matrix.getColumnDimension();
        field = matrix.getField();
        lu = matrix.getData();
        pivot = new int[m];
        cachedL = null;
        cachedU = null;
        cachedP = null;
        // Initialize permutation array and parity
        for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25934, _mut25935, _mut25936, _mut25937, _mut25938); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
            pivot[row] = row;
        }
        even = true;
        singular = false;
        // Loop over columns
        for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25979, _mut25980, _mut25981, _mut25982, _mut25983); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
            T sum = field.getZero();
            // upper
            for (int row = 0; ROR_less(row, col, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25944, _mut25945, _mut25946, _mut25947, _mut25948); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                final T[] luRow = lu[row];
                sum = luRow[col];
                for (int i = 0; ROR_less(i, row, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25939, _mut25940, _mut25941, _mut25942, _mut25943); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                    sum = sum.subtract(luRow[i].multiply(lu[i][col]));
                }
                luRow[col] = sum;
            }
            // permutation row
            int nonZero = col;
            for (int row = col; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25954, _mut25955, _mut25956, _mut25957, _mut25958); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                final T[] luRow = lu[row];
                sum = luRow[col];
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25949, _mut25950, _mut25951, _mut25952, _mut25953); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                    sum = sum.subtract(luRow[i].multiply(lu[i][col]));
                }
                luRow[col] = sum;
                if (lu[nonZero][col].equals(field.getZero())) {
                    // try to select a better permutation choice
                    ++nonZero;
                }
            }
            // Singularity check
            if (ROR_greater_equals(nonZero, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25959, _mut25960, _mut25961, _mut25962, _mut25963)) {
                singular = true;
                return;
            }
            // Pivot if necessary
            if (ROR_not_equals(nonZero, col, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25964, _mut25965, _mut25966, _mut25967, _mut25968)) {
                T tmp = field.getZero();
                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25969, _mut25970, _mut25971, _mut25972, _mut25973); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                    tmp = lu[nonZero][i];
                    lu[nonZero][i] = lu[col][i];
                    lu[col][i] = tmp;
                }
                int temp = pivot[nonZero];
                pivot[nonZero] = pivot[col];
                pivot[col] = temp;
                even = !even;
            }
            // Divide the lower elements by the "winning" diagonal elt.
            final T luDiag = lu[col][col];
            for (int row = col + 1; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84", _mut25974, _mut25975, _mut25976, _mut25977, _mut25978); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.FieldLUDecomposition_84");
                final T[] luRow = lu[row];
                luRow[col] = luRow[col].divide(luDiag);
            }
        }
    }

    /**
     * Returns the matrix L of the decomposition.
     * <p>L is a lower-triangular matrix</p>
     * @return the L matrix (or null if decomposed matrix is singular)
     */
    public FieldMatrix<T> getL() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getL_171");
        if ((_mut25984 ? ((cachedL == null) || !singular) : ((cachedL == null) && !singular))) {
            final int m = pivot.length;
            cachedL = new Array2DRowFieldMatrix<T>(field, m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getL_171", _mut25990, _mut25991, _mut25992, _mut25993, _mut25994); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getL_171");
                final T[] luI = lu[i];
                for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.linear.FieldLUDecomposition.getL_171", _mut25985, _mut25986, _mut25987, _mut25988, _mut25989); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getL_171");
                    cachedL.setEntry(i, j, luI[j]);
                }
                cachedL.setEntry(i, i, field.getOne());
            }
        }
        return cachedL;
    }

    /**
     * Returns the matrix U of the decomposition.
     * <p>U is an upper-triangular matrix</p>
     * @return the U matrix (or null if decomposed matrix is singular)
     */
    public FieldMatrix<T> getU() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getU_191");
        if ((_mut25995 ? ((cachedU == null) || !singular) : ((cachedU == null) && !singular))) {
            final int m = pivot.length;
            cachedU = new Array2DRowFieldMatrix<T>(field, m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getU_191", _mut26001, _mut26002, _mut26003, _mut26004, _mut26005); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getU_191");
                final T[] luI = lu[i];
                for (int j = i; ROR_less(j, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getU_191", _mut25996, _mut25997, _mut25998, _mut25999, _mut26000); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getU_191");
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
    public FieldMatrix<T> getP() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getP_214");
        if ((_mut26006 ? ((cachedP == null) || !singular) : ((cachedP == null) && !singular))) {
            final int m = pivot.length;
            cachedP = new Array2DRowFieldMatrix<T>(field, m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getP_214", _mut26007, _mut26008, _mut26009, _mut26010, _mut26011); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getP_214");
                cachedP.setEntry(i, pivot[i], field.getOne());
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
     * Return the determinant of the matrix.
     * @return determinant of the matrix
     */
    public T getDeterminant() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getDeterminant_238");
        if (singular) {
            return field.getZero();
        } else {
            final int m = pivot.length;
            T determinant = even ? field.getOne() : field.getZero().subtract(field.getOne());
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getDeterminant_238", _mut26012, _mut26013, _mut26014, _mut26015, _mut26016); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getDeterminant_238");
                determinant = determinant.multiply(lu[i][i]);
            }
            return determinant;
        }
    }

    /**
     * Get a solver for finding the A &times; X = B solution in exact linear sense.
     * @return a solver
     */
    public FieldDecompositionSolver<T> getSolver() {
        return new Solver<T>(field, lu, pivot, singular);
    }

    /**
     * Specialized solver.
     * @param <T> the type of the field elements
     */
    private static class Solver<T extends FieldElement<T>> implements FieldDecompositionSolver<T> {

        /**
         * Field to which the elements belong.
         */
        private final Field<T> field;

        /**
         * Entries of LU decomposition.
         */
        private final T[][] lu;

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
         * @param field field to which the matrix elements belong
         * @param lu entries of LU decomposition
         * @param pivot pivot permutation associated with LU decomposition
         * @param singular singularity indicator
         */
        private Solver(final Field<T> field, final T[][] lu, final int[] pivot, final boolean singular) {
            this.field = field;
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
        public FieldVector<T> solve(FieldVector<T> b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
            try {
                return solve((ArrayFieldVector<T>) b);
            } catch (ClassCastException cce) {
                final int m = pivot.length;
                if (ROR_not_equals(b.getDimension(), m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26017, _mut26018, _mut26019, _mut26020, _mut26021)) {
                    throw new DimensionMismatchException(b.getDimension(), m);
                }
                if (singular) {
                    throw new SingularMatrixException();
                }
                // Apply permutations to b
                final T[] bp = MathArrays.buildArray(field, m);
                for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26022, _mut26023, _mut26024, _mut26025, _mut26026); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
                    bp[row] = b.getEntry(pivot[row]);
                }
                // Solve LY = b
                for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26032, _mut26033, _mut26034, _mut26035, _mut26036); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
                    final T bpCol = bp[col];
                    for (int i = col + 1; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26027, _mut26028, _mut26029, _mut26030, _mut26031); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
                        bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                    }
                }
                // Solve UX = Y
                for (int col = m - 1; ROR_greater_equals(col, 0, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26042, _mut26043, _mut26044, _mut26045, _mut26046); col--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
                    bp[col] = bp[col].divide(lu[col][col]);
                    final T bpCol = bp[col];
                    for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_297", _mut26037, _mut26038, _mut26039, _mut26040, _mut26041); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_297");
                        bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                    }
                }
                return new ArrayFieldVector<T>(field, bp, false);
            }
        }

        /**
         * Solve the linear equation A &times; X = B.
         * <p>The A matrix is implicit here. It is </p>
         * @param b right-hand side of the equation A &times; X = B
         * @return a vector X such that A &times; X = B
         * @throws DimensionMismatchException if the matrices dimensions do not match.
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public ArrayFieldVector<T> solve(ArrayFieldVector<T> b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
            final int m = pivot.length;
            final int length = b.getDimension();
            if (ROR_not_equals(length, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26047, _mut26048, _mut26049, _mut26050, _mut26051)) {
                throw new DimensionMismatchException(length, m);
            }
            if (singular) {
                throw new SingularMatrixException();
            }
            // Apply permutations to b
            final T[] bp = MathArrays.buildArray(field, m);
            for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26052, _mut26053, _mut26054, _mut26055, _mut26056); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
                bp[row] = b.getEntry(pivot[row]);
            }
            // Solve LY = b
            for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26062, _mut26063, _mut26064, _mut26065, _mut26066); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
                final T bpCol = bp[col];
                for (int i = col + 1; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26057, _mut26058, _mut26059, _mut26060, _mut26061); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
                    bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                }
            }
            // Solve UX = Y
            for (int col = m - 1; ROR_greater_equals(col, 0, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26072, _mut26073, _mut26074, _mut26075, _mut26076); col--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
                bp[col] = bp[col].divide(lu[col][col]);
                final T bpCol = bp[col];
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_345", _mut26067, _mut26068, _mut26069, _mut26070, _mut26071); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_345");
                    bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                }
            }
            return new ArrayFieldVector<T>(bp, false);
        }

        /**
         * {@inheritDoc}
         */
        public FieldMatrix<T> solve(FieldMatrix<T> b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
            final int m = pivot.length;
            if (ROR_not_equals(b.getRowDimension(), m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26077, _mut26078, _mut26079, _mut26080, _mut26081)) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            if (singular) {
                throw new SingularMatrixException();
            }
            final int nColB = b.getColumnDimension();
            // Apply permutations to b
            final T[][] bp = MathArrays.buildArray(field, m, nColB);
            for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26087, _mut26088, _mut26089, _mut26090, _mut26091); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                final T[] bpRow = bp[row];
                final int pRow = pivot[row];
                for (int col = 0; ROR_less(col, nColB, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26082, _mut26083, _mut26084, _mut26085, _mut26086); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                    bpRow[col] = b.getEntry(pRow, col);
                }
            }
            // Solve LY = b
            for (int col = 0; ROR_less(col, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26102, _mut26103, _mut26104, _mut26105, _mut26106); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                final T[] bpCol = bp[col];
                for (int i = col + 1; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26097, _mut26098, _mut26099, _mut26100, _mut26101); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                    final T[] bpI = bp[i];
                    final T luICol = lu[i][col];
                    for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26092, _mut26093, _mut26094, _mut26095, _mut26096); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                        bpI[j] = bpI[j].subtract(bpCol[j].multiply(luICol));
                    }
                }
            }
            // Solve UX = Y
            for (int col = m - 1; ROR_greater_equals(col, 0, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26122, _mut26123, _mut26124, _mut26125, _mut26126); col--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                final T[] bpCol = bp[col];
                final T luDiag = lu[col][col];
                for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26107, _mut26108, _mut26109, _mut26110, _mut26111); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                    bpCol[j] = bpCol[j].divide(luDiag);
                }
                for (int i = 0; ROR_less(i, col, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26117, _mut26118, _mut26119, _mut26120, _mut26121); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                    final T[] bpI = bp[i];
                    final T luICol = lu[i][col];
                    for (int j = 0; ROR_less(j, nColB, "org.apache.commons.math3.linear.FieldLUDecomposition.solve_382", _mut26112, _mut26113, _mut26114, _mut26115, _mut26116); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.solve_382");
                        bpI[j] = bpI[j].subtract(bpCol[j].multiply(luICol));
                    }
                }
            }
            return new Array2DRowFieldMatrix<T>(field, bp, false);
        }

        /**
         * {@inheritDoc}
         */
        public FieldMatrix<T> getInverse() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getInverse_436");
            final int m = pivot.length;
            final T one = field.getOne();
            FieldMatrix<T> identity = new Array2DRowFieldMatrix<T>(field, m, m);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.FieldLUDecomposition.getInverse_436", _mut26127, _mut26128, _mut26129, _mut26130, _mut26131); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.FieldLUDecomposition.getInverse_436");
                identity.setEntry(i, i, one);
            }
            return solve(identity);
        }
    }
}
