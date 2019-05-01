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
package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.optim.nonlinear.vector.JacobianMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for implementing least-squares optimizers.
 * It provides methods for error estimation.
 *
 * @since 3.1
 * @deprecated All classes and interfaces in this package are deprecated.
 * The optimizers that were provided here were moved to the
 * {@link org.apache.commons.math3.fitting.leastsquares} package
 * (cf. MATH-1008).
 */
@Deprecated
public abstract class AbstractLeastSquaresOptimizer extends JacobianMultivariateVectorOptimizer {

    @Conditional
    public static boolean _mut65773 = false, _mut65774 = false, _mut65775 = false, _mut65776 = false, _mut65777 = false, _mut65778 = false, _mut65779 = false, _mut65780 = false, _mut65781 = false, _mut65782 = false, _mut65783 = false, _mut65784 = false, _mut65785 = false, _mut65786 = false, _mut65787 = false, _mut65788 = false, _mut65789 = false, _mut65790 = false, _mut65791 = false, _mut65792 = false, _mut65793 = false, _mut65794 = false, _mut65795 = false, _mut65796 = false, _mut65797 = false, _mut65798 = false, _mut65799 = false, _mut65800 = false, _mut65801 = false, _mut65802 = false, _mut65803 = false, _mut65804 = false;

    /**
     * Square-root of the weight matrix.
     */
    private RealMatrix weightMatrixSqrt;

    /**
     * Cost value (square root of the sum of the residuals).
     */
    private double cost;

    /**
     * @param checker Convergence checker.
     */
    protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
    }

    /**
     * Computes the weighted Jacobian matrix.
     *
     * @param params Model parameters at which to compute the Jacobian.
     * @return the weighted Jacobian: W<sup>1/2</sup> J.
     * @throws DimensionMismatchException if the Jacobian dimension does not
     * match problem dimension.
     */
    protected RealMatrix computeWeightedJacobian(double[] params) {
        return weightMatrixSqrt.multiply(MatrixUtils.createRealMatrix(computeJacobian(params)));
    }

    /**
     * Computes the cost.
     *
     * @param residuals Residuals.
     * @return the cost.
     * @see #computeResiduals(double[])
     */
    protected double computeCost(double[] residuals) {
        final ArrayRealVector r = new ArrayRealVector(residuals);
        return FastMath.sqrt(r.dotProduct(getWeight().operate(r)));
    }

    /**
     * Gets the root-mean-square (RMS) value.
     *
     * The RMS the root of the arithmetic mean of the square of all weighted
     * residuals.
     * This is related to the criterion that is minimized by the optimizer
     * as follows: If <em>c</em> if the criterion, and <em>n</em> is the
     * number of measurements, then the RMS is <em>sqrt (c/n)</em>.
     *
     * @return the RMS value.
     */
    public double getRMS() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.getRMS_95");
        return FastMath.sqrt(AOR_divide(getChiSquare(), getTargetSize(), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.getRMS_95", _mut65773, _mut65774, _mut65775, _mut65776));
    }

    /**
     * Get a Chi-Square-like value assuming the N residuals follow N
     * distinct normal distributions centered on 0 and whose variances are
     * the reciprocal of the weights.
     * @return chi-square value
     */
    public double getChiSquare() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.getChiSquare_105");
        return AOR_multiply(cost, cost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.getChiSquare_105", _mut65777, _mut65778, _mut65779, _mut65780);
    }

    /**
     * Gets the square-root of the weight matrix.
     *
     * @return the square-root of the weight matrix.
     */
    public RealMatrix getWeightSquareRoot() {
        return weightMatrixSqrt.copy();
    }

    /**
     * Sets the cost.
     *
     * @param cost Cost value.
     */
    protected void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Get the covariance matrix of the optimized parameters.
     * <br/>
     * Note that this operation involves the inversion of the
     * <code>J<sup>T</sup>J</code> matrix, where {@code J} is the
     * Jacobian matrix.
     * The {@code threshold} parameter is a way for the caller to specify
     * that the result of this computation should be considered meaningless,
     * and thus trigger an exception.
     *
     * @param params Model parameters.
     * @param threshold Singularity threshold.
     * @return the covariance matrix.
     * @throws org.apache.commons.math3.linear.SingularMatrixException
     * if the covariance matrix cannot be computed (singular problem).
     */
    public double[][] computeCovariances(double[] params, double threshold) {
        // Set up the Jacobian.
        final RealMatrix j = computeWeightedJacobian(params);
        // Compute transpose(J)J.
        final RealMatrix jTj = j.transpose().multiply(j);
        // Compute the covariances matrix.
        final DecompositionSolver solver = new QRDecomposition(jTj, threshold).getSolver();
        return solver.getInverse().getData();
    }

    /**
     * Computes an estimate of the standard deviation of the parameters. The
     * returned values are the square root of the diagonal coefficients of the
     * covariance matrix, {@code sd(a[i]) ~= sqrt(C[i][i])}, where {@code a[i]}
     * is the optimized value of the {@code i}-th parameter, and {@code C} is
     * the covariance matrix.
     *
     * @param params Model parameters.
     * @param covarianceSingularityThreshold Singularity threshold (see
     * {@link #computeCovariances(double[],double) computeCovariances}).
     * @return an estimate of the standard deviation of the optimized parameters
     * @throws org.apache.commons.math3.linear.SingularMatrixException
     * if the covariance matrix cannot be computed.
     */
    public double[] computeSigma(double[] params, double covarianceSingularityThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeSigma_171");
        final int nC = params.length;
        final double[] sig = new double[nC];
        final double[][] cov = computeCovariances(params, covarianceSingularityThreshold);
        for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeSigma_171", _mut65781, _mut65782, _mut65783, _mut65784, _mut65785); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeSigma_171");
            sig[i] = FastMath.sqrt(cov[i][i]);
        }
        return sig;
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link JacobianMultivariateVectorOptimizer#parseOptimizationData(OptimizationData[])
     * JacobianMultivariateVectorOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link org.apache.commons.math3.optim.nonlinear.vector.Weight}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     * @throws DimensionMismatchException if the initial guess, target, and weight
     * arguments have inconsistent dimensions.
     */
    @Override
    public PointVectorValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
        // Set up base class and perform computation.
        return super.optimize(optData);
    }

    /**
     * Computes the residuals.
     * The residual is the difference between the observed (target)
     * values and the model (objective function) value.
     * There is one residual for each element of the vector-valued
     * function.
     *
     * @param objectiveValue Value of the the objective function. This is
     * the value returned from a call to
     * {@link #computeObjectiveValue(double[]) computeObjectiveValue}
     * (whose array argument contains the model parameters).
     * @return the residuals.
     * @throws DimensionMismatchException if {@code params} has a wrong
     * length.
     */
    protected double[] computeResiduals(double[] objectiveValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeResiduals_219");
        final double[] target = getTarget();
        if (ROR_not_equals(objectiveValue.length, target.length, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeResiduals_219", _mut65786, _mut65787, _mut65788, _mut65789, _mut65790)) {
            throw new DimensionMismatchException(target.length, objectiveValue.length);
        }
        final double[] residuals = new double[target.length];
        for (int i = 0; ROR_less(i, target.length, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeResiduals_219", _mut65795, _mut65796, _mut65797, _mut65798, _mut65799); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeResiduals_219");
            residuals[i] = AOR_minus(target[i], objectiveValue[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.computeResiduals_219", _mut65791, _mut65792, _mut65793, _mut65794);
        }
        return residuals;
    }

    /**
     * Scans the list of (required and optional) optimization data that
     * characterize the problem.
     * If the weight matrix is specified, the {@link #weightMatrixSqrt}
     * field is recomputed.
     *
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link Weight}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // Allow base class to register its own data.
        super.parseOptimizationData(optData);
        // not provided in the argument list.
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.parseOptimizationData_245");
            if (data instanceof Weight) {
                weightMatrixSqrt = squareRoot(((Weight) data).getWeight());
                // changed to "continue".
                break;
            }
        }
    }

    /**
     * Computes the square-root of the weight matrix.
     *
     * @param m Symmetric, positive-definite (weight) matrix.
     * @return the square-root of the weight matrix.
     */
    private RealMatrix squareRoot(RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.squareRoot_268");
        if (m instanceof DiagonalMatrix) {
            final int dim = m.getRowDimension();
            final RealMatrix sqrtM = new DiagonalMatrix(dim);
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.squareRoot_268", _mut65800, _mut65801, _mut65802, _mut65803, _mut65804); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer.squareRoot_268");
                sqrtM.setEntry(i, i, FastMath.sqrt(m.getEntry(i, i)));
            }
            return sqrtM;
        } else {
            final EigenDecomposition dec = new EigenDecomposition(m);
            return dec.getSquareRoot();
        }
    }
}
