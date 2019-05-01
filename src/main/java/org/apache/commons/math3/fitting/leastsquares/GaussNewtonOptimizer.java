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
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Gauss-Newton least-squares solver.
 * <p> This class solve a least-square problem by
 * solving the normal equations of the linearized problem at each iteration. Either LU
 * decomposition or Cholesky decomposition can be used to solve the normal equations,
 * or QR decomposition or SVD decomposition can be used to solve the linear system. LU
 * decomposition is faster but QR decomposition is more robust for difficult problems,
 * and SVD can compute a solution for rank-deficient problems.
 * </p>
 *
 * @since 3.3
 */
public class GaussNewtonOptimizer implements LeastSquaresOptimizer {

    @Conditional
    public static boolean _mut38181 = false, _mut38182 = false, _mut38183 = false, _mut38184 = false, _mut38185 = false, _mut38186 = false, _mut38187 = false, _mut38188 = false, _mut38189 = false, _mut38190 = false, _mut38191 = false, _mut38192 = false, _mut38193 = false, _mut38194 = false, _mut38195 = false, _mut38196 = false, _mut38197 = false, _mut38198 = false, _mut38199 = false, _mut38200 = false, _mut38201 = false, _mut38202 = false, _mut38203 = false, _mut38204 = false, _mut38205 = false, _mut38206 = false, _mut38207 = false, _mut38208 = false, _mut38209 = false, _mut38210 = false, _mut38211 = false, _mut38212 = false, _mut38213 = false, _mut38214 = false, _mut38215 = false, _mut38216 = false, _mut38217 = false, _mut38218 = false, _mut38219 = false, _mut38220 = false, _mut38221 = false, _mut38222 = false, _mut38223 = false, _mut38224 = false, _mut38225 = false, _mut38226 = false, _mut38227 = false;

    // TODO move to linear package and expand options?
    public enum Decomposition {

        /**
         * Solve by forming the normal equations (J<sup>T</sup>Jx=J<sup>T</sup>r) and
         * using the {@link LUDecomposition}.
         *
         * <p> Theoretically this method takes mn<sup>2</sup>/2 operations to compute the
         * normal matrix and n<sup>3</sup>/3 operations (m > n) to solve the system using
         * the LU decomposition. </p>
         */
        LU {

            @Override
            protected RealVector solve(final RealMatrix jacobian, final RealVector residuals) {
                try {
                    final Pair<RealMatrix, RealVector> normalEquation = computeNormalMatrix(jacobian, residuals);
                    final RealMatrix normal = normalEquation.getFirst();
                    final RealVector jTr = normalEquation.getSecond();
                    return new LUDecomposition(normal, SINGULARITY_THRESHOLD).getSolver().solve(jTr);
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        }
        ,
        /**
         * Solve the linear least squares problem (Jx=r) using the {@link
         * QRDecomposition}.
         *
         * <p> Theoretically this method takes mn<sup>2</sup> - n<sup>3</sup>/3 operations
         * (m > n) and has better numerical accuracy than any method that forms the normal
         * equations. </p>
         */
        QR {

            @Override
            protected RealVector solve(final RealMatrix jacobian, final RealVector residuals) {
                try {
                    return new QRDecomposition(jacobian, SINGULARITY_THRESHOLD).getSolver().solve(residuals);
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        }
        ,
        /**
         * Solve by forming the normal equations (J<sup>T</sup>Jx=J<sup>T</sup>r) and
         * using the {@link CholeskyDecomposition}.
         *
         * <p> Theoretically this method takes mn<sup>2</sup>/2 operations to compute the
         * normal matrix and n<sup>3</sup>/6 operations (m > n) to solve the system using
         * the Cholesky decomposition. </p>
         */
        CHOLESKY {

            @Override
            protected RealVector solve(final RealMatrix jacobian, final RealVector residuals) {
                try {
                    final Pair<RealMatrix, RealVector> normalEquation = computeNormalMatrix(jacobian, residuals);
                    final RealMatrix normal = normalEquation.getFirst();
                    final RealVector jTr = normalEquation.getSecond();
                    return new CholeskyDecomposition(normal, SINGULARITY_THRESHOLD, SINGULARITY_THRESHOLD).getSolver().solve(jTr);
                } catch (NonPositiveDefiniteMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        }
        ,
        /**
         * Solve the linear least squares problem using the {@link
         * SingularValueDecomposition}.
         *
         * <p> This method is slower, but can provide a solution for rank deficient and
         * nearly singular systems.
         */
        SVD {

            @Override
            protected RealVector solve(final RealMatrix jacobian, final RealVector residuals) {
                return new SingularValueDecomposition(jacobian).getSolver().solve(residuals);
            }
        }
        ;

        /**
         * Solve the linear least squares problem Jx=r.
         *
         * @param jacobian  the Jacobian matrix, J. the number of rows >= the number or
         *                  columns.
         * @param residuals the computed residuals, r.
         * @return the solution x, to the linear least squares problem Jx=r.
         * @throws ConvergenceException if the matrix properties (e.g. singular) do not
         *                              permit a solution.
         */
        protected abstract RealVector solve(RealMatrix jacobian, RealVector residuals);
    }

    /**
     * The singularity threshold for matrix decompositions. Determines when a {@link
     * ConvergenceException} is thrown. The current value was the default value for {@link
     * LUDecomposition}.
     */
    private static final double SINGULARITY_THRESHOLD = 1e-11;

    /**
     * Indicator for using LU decomposition.
     */
    private final Decomposition decomposition;

    /**
     * Creates a Gauss Newton optimizer.
     * <p/>
     * The default for the algorithm is to solve the normal equations using QR
     * decomposition.
     */
    public GaussNewtonOptimizer() {
        this(Decomposition.QR);
    }

    /**
     * Create a Gauss Newton optimizer that uses the given decomposition algorithm to
     * solve the normal equations.
     *
     * @param decomposition the {@link Decomposition} algorithm.
     */
    public GaussNewtonOptimizer(final Decomposition decomposition) {
        this.decomposition = decomposition;
    }

    /**
     * Get the matrix decomposition algorithm used to solve the normal equations.
     *
     * @return the matrix {@link Decomposition} algoritm.
     */
    public Decomposition getDecomposition() {
        return this.decomposition;
    }

    /**
     * Configure the decomposition algorithm.
     *
     * @param newDecomposition the {@link Decomposition} algorithm to use.
     * @return a new instance.
     */
    public GaussNewtonOptimizer withDecomposition(final Decomposition newDecomposition) {
        return new GaussNewtonOptimizer(newDecomposition);
    }

    /**
     * {@inheritDoc}
     */
    public Optimum optimize(final LeastSquaresProblem lsp) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.optimize_207");
        // create local evaluation and iteration counts
        final Incrementor evaluationCounter = lsp.getEvaluationCounter();
        final Incrementor iterationCounter = lsp.getIterationCounter();
        final ConvergenceChecker<Evaluation> checker = lsp.getConvergenceChecker();
        // Computation will be useless without a checker (see "for-loop").
        if (checker == null) {
            throw new NullArgumentException();
        }
        RealVector currentPoint = lsp.getStart();
        // iterate until convergence is reached
        Evaluation current = null;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.optimize_207");
            iterationCounter.incrementCount();
            // evaluate the objective function and its jacobian
            Evaluation previous = current;
            // Value of the objective function at "currentPoint".
            evaluationCounter.incrementCount();
            current = lsp.evaluate(currentPoint);
            final RealVector currentResiduals = current.getResiduals();
            final RealMatrix weightedJacobian = current.getJacobian();
            currentPoint = current.getPoint();
            // Check convergence.
            if ((_mut38181 ? (previous != null || checker.converged(iterationCounter.getCount(), previous, current)) : (previous != null && checker.converged(iterationCounter.getCount(), previous, current)))) {
                return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
            }
            // solve the linearized least squares problem
            final RealVector dX = this.decomposition.solve(weightedJacobian, currentResiduals);
            // update the estimated parameters
            currentPoint = currentPoint.add(dX);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GaussNewtonOptimizer{" + "decomposition=" + decomposition + '}';
    }

    /**
     * Compute the normal matrix, J<sup>T</sup>J.
     *
     * @param jacobian  the m by n jacobian matrix, J. Input.
     * @param residuals the m by 1 residual vector, r. Input.
     * @return  the n by n normal matrix and  the n by 1 J<sup>Tr vector.
     */
    private static Pair<RealMatrix, RealVector> computeNormalMatrix(final RealMatrix jacobian, final RealVector residuals) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
        // since the normal matrix is symmetric, we only need to compute half of it.
        final int nR = jacobian.getRowDimension();
        final int nC = jacobian.getColumnDimension();
        // allocate space for return values
        final RealMatrix normal = MatrixUtils.createRealMatrix(nC, nC);
        final RealVector jTr = new ArrayRealVector(nC);
        // for each measurement
        for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38213, _mut38214, _mut38215, _mut38216, _mut38217); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
            // compute JTr for measurement i
            for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38190, _mut38191, _mut38192, _mut38193, _mut38194); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
                jTr.setEntry(j, AOR_plus(jTr.getEntry(j), AOR_multiply(residuals.getEntry(i), jacobian.getEntry(i, j), "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38182, _mut38183, _mut38184, _mut38185), "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38186, _mut38187, _mut38188, _mut38189));
            }
            // add the the contribution to the normal matrix for measurement i
            for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38208, _mut38209, _mut38210, _mut38211, _mut38212); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
                // only compute the upper triangular part
                for (int l = k; ROR_less(l, nC, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38203, _mut38204, _mut38205, _mut38206, _mut38207); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
                    normal.setEntry(k, l, AOR_plus(normal.getEntry(k, l), AOR_multiply(jacobian.getEntry(i, k), jacobian.getEntry(i, l), "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38195, _mut38196, _mut38197, _mut38198), "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38199, _mut38200, _mut38201, _mut38202));
                }
            }
        }
        // copy the upper triangular part to the lower triangular part.
        for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38223, _mut38224, _mut38225, _mut38226, _mut38227); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265", _mut38218, _mut38219, _mut38220, _mut38221, _mut38222); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.computeNormalMatrix_265");
                normal.setEntry(i, j, normal.getEntry(j, i));
            }
        }
        return new Pair<RealMatrix, RealVector>(normal, jTr);
    }
}
