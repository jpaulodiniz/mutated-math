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
package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.Target;
import org.apache.commons.math3.optimization.Weight;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for implementing least squares optimizers.
 * It handles the boilerplate methods associated to thresholds settings,
 * Jacobian and error estimation.
 * <br/>
 * This class constructs the Jacobian matrix of the function argument in method
 * {@link BaseAbstractMultivariateVectorOptimizer#optimize(int,
 * org.apache.commons.math3.analysis.MultivariateVectorFunction,OptimizationData[])
 * optimize} and assumes that the rows of that matrix iterate on the model
 * functions while the columns iterate on the parameters; thus, the numbers
 * of rows is equal to the dimension of the
 * {@link org.apache.commons.math3.optimization.Target Target} while
 * the number of columns is equal to the dimension of the
 * {@link org.apache.commons.math3.optimization.InitialGuess InitialGuess}.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 1.2
 */
@Deprecated
public abstract class AbstractLeastSquaresOptimizer extends BaseAbstractMultivariateVectorOptimizer<DifferentiableMultivariateVectorFunction> implements DifferentiableMultivariateVectorOptimizer {

    @Conditional
    public static boolean _mut72300 = false, _mut72301 = false, _mut72302 = false, _mut72303 = false, _mut72304 = false, _mut72305 = false, _mut72306 = false, _mut72307 = false, _mut72308 = false, _mut72309 = false, _mut72310 = false, _mut72311 = false, _mut72312 = false, _mut72313 = false, _mut72314 = false, _mut72315 = false, _mut72316 = false, _mut72317 = false, _mut72318 = false, _mut72319 = false, _mut72320 = false, _mut72321 = false, _mut72322 = false, _mut72323 = false, _mut72324 = false, _mut72325 = false, _mut72326 = false, _mut72327 = false, _mut72328 = false, _mut72329 = false, _mut72330 = false, _mut72331 = false, _mut72332 = false, _mut72333 = false, _mut72334 = false, _mut72335 = false, _mut72336 = false, _mut72337 = false, _mut72338 = false, _mut72339 = false, _mut72340 = false, _mut72341 = false, _mut72342 = false, _mut72343 = false, _mut72344 = false, _mut72345 = false, _mut72346 = false, _mut72347 = false, _mut72348 = false, _mut72349 = false, _mut72350 = false, _mut72351 = false, _mut72352 = false, _mut72353 = false, _mut72354 = false, _mut72355 = false, _mut72356 = false, _mut72357 = false, _mut72358 = false, _mut72359 = false, _mut72360 = false, _mut72361 = false, _mut72362 = false, _mut72363 = false, _mut72364 = false, _mut72365 = false, _mut72366 = false, _mut72367 = false, _mut72368 = false, _mut72369 = false, _mut72370 = false, _mut72371 = false, _mut72372 = false, _mut72373 = false;

    /**
     * Singularity threshold (cf. {@link #getCovariances(double)}).
     * @deprecated As of 3.1.
     */
    @Deprecated
    private static final double DEFAULT_SINGULARITY_THRESHOLD = 1e-14;

    /**
     * Jacobian matrix of the weighted residuals.
     * This matrix is in canonical form just after the calls to
     * {@link #updateJacobian()}, but may be modified by the solver
     * in the derived class (the {@link LevenbergMarquardtOptimizer
     * Levenberg-Marquardt optimizer} does this).
     * @deprecated As of 3.1. To be removed in 4.0. Please use
     * {@link #computeWeightedJacobian(double[])} instead.
     */
    @Deprecated
    protected double[][] weightedResidualJacobian;

    /**
     * Number of columns of the jacobian matrix.
     * @deprecated As of 3.1.
     */
    @Deprecated
    protected int cols;

    /**
     * Number of rows of the jacobian matrix.
     * @deprecated As of 3.1.
     */
    @Deprecated
    protected int rows;

    /**
     * Current point.
     * @deprecated As of 3.1.
     */
    @Deprecated
    protected double[] point;

    /**
     * Current objective function value.
     * @deprecated As of 3.1.
     */
    @Deprecated
    protected double[] objective;

    /**
     * Weighted residuals
     * @deprecated As of 3.1.
     */
    @Deprecated
    protected double[] weightedResiduals;

    /**
     * Cost value (square root of the sum of the residuals).
     * @deprecated As of 3.1. Field to become "private" in 4.0.
     * Please use {@link #setCost(double)}.
     */
    @Deprecated
    protected double cost;

    /**
     * Objective function derivatives.
     */
    private MultivariateDifferentiableVectorFunction jF;

    /**
     * Number of evaluations of the Jacobian.
     */
    private int jacobianEvaluations;

    /**
     * Square-root of the weight matrix.
     */
    private RealMatrix weightMatrixSqrt;

    /**
     * Simple constructor with default settings.
     * The convergence check is set to a {@link
     * org.apache.commons.math3.optimization.SimpleVectorValueChecker}.
     * @deprecated See {@link org.apache.commons.math3.optimization.SimpleValueChecker#SimpleValueChecker()}
     */
    @Deprecated
    protected AbstractLeastSquaresOptimizer() {
    }

    /**
     * @param checker Convergence checker.
     */
    protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
    }

    /**
     * @return the number of evaluations of the Jacobian function.
     */
    public int getJacobianEvaluations() {
        return jacobianEvaluations;
    }

    /**
     * Update the jacobian matrix.
     *
     * @throws DimensionMismatchException if the Jacobian dimension does not
     * match problem dimension.
     * @deprecated As of 3.1. Please use {@link #computeWeightedJacobian(double[])}
     * instead.
     */
    @Deprecated
    protected void updateJacobian() {
        final RealMatrix weightedJacobian = computeWeightedJacobian(point);
        weightedResidualJacobian = weightedJacobian.scalarMultiply(-1).getData();
    }

    /**
     * Computes the Jacobian matrix.
     *
     * @param params Model parameters at which to compute the Jacobian.
     * @return the weighted Jacobian: W<sup>1/2</sup> J.
     * @throws DimensionMismatchException if the Jacobian dimension does not
     * match problem dimension.
     * @since 3.1
     */
    protected RealMatrix computeWeightedJacobian(double[] params) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167");
        ++jacobianEvaluations;
        final DerivativeStructure[] dsPoint = new DerivativeStructure[params.length];
        final int nC = params.length;
        for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167", _mut72300, _mut72301, _mut72302, _mut72303, _mut72304); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167");
            dsPoint[i] = new DerivativeStructure(nC, 1, i, params[i]);
        }
        final DerivativeStructure[] dsValue = jF.value(dsPoint);
        final int nR = getTarget().length;
        if (ROR_not_equals(dsValue.length, nR, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167", _mut72305, _mut72306, _mut72307, _mut72308, _mut72309)) {
            throw new DimensionMismatchException(dsValue.length, nR);
        }
        final double[][] jacobianData = new double[nR][nC];
        for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167", _mut72315, _mut72316, _mut72317, _mut72318, _mut72319); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167");
            int[] orders = new int[nC];
            for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167", _mut72310, _mut72311, _mut72312, _mut72313, _mut72314); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeWeightedJacobian_167");
                orders[j] = 1;
                jacobianData[i][j] = dsValue[i].getPartialDerivative(orders);
                orders[j] = 0;
            }
        }
        return weightMatrixSqrt.multiply(MatrixUtils.createRealMatrix(jacobianData));
    }

    /**
     * Update the residuals array and cost function value.
     * @throws DimensionMismatchException if the dimension does not match the
     * problem dimension.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximal number of evaluations is exceeded.
     * @deprecated As of 3.1. Please use {@link #computeResiduals(double[])},
     * {@link #computeObjectiveValue(double[])}, {@link #computeCost(double[])}
     * and {@link #setCost(double)} instead.
     */
    @Deprecated
    protected void updateResidualsAndCost() {
        objective = computeObjectiveValue(point);
        final double[] res = computeResiduals(objective);
        // Compute cost.
        cost = computeCost(res);
        // Compute weighted residuals.
        final ArrayRealVector residuals = new ArrayRealVector(res);
        weightedResiduals = weightMatrixSqrt.operate(residuals).toArray();
    }

    /**
     * Computes the cost.
     *
     * @param residuals Residuals.
     * @return the cost.
     * @see #computeResiduals(double[])
     * @since 3.1
     */
    protected double computeCost(double[] residuals) {
        final ArrayRealVector r = new ArrayRealVector(residuals);
        return FastMath.sqrt(r.dotProduct(getWeight().operate(r)));
    }

    /**
     * Get the Root Mean Square value.
     * Get the Root Mean Square value, i.e. the root of the arithmetic
     * mean of the square of all weighted residuals. This is related to the
     * criterion that is minimized by the optimizer as follows: if
     * <em>c</em> if the criterion, and <em>n</em> is the number of
     * measurements, then the RMS is <em>sqrt (c/n)</em>.
     *
     * @return RMS value
     */
    public double getRMS() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.getRMS_239");
        return FastMath.sqrt(AOR_divide(getChiSquare(), rows, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.getRMS_239", _mut72320, _mut72321, _mut72322, _mut72323));
    }

    /**
     * Get a Chi-Square-like value assuming the N residuals follow N
     * distinct normal distributions centered on 0 and whose variances are
     * the reciprocal of the weights.
     * @return chi-square value
     */
    public double getChiSquare() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.getChiSquare_249");
        return AOR_multiply(cost, cost, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.getChiSquare_249", _mut72324, _mut72325, _mut72326, _mut72327);
    }

    /**
     * Gets the square-root of the weight matrix.
     *
     * @return the square-root of the weight matrix.
     * @since 3.1
     */
    public RealMatrix getWeightSquareRoot() {
        return weightMatrixSqrt.copy();
    }

    /**
     * Sets the cost.
     *
     * @param cost Cost value.
     * @since 3.1
     */
    protected void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Get the covariance matrix of the optimized parameters.
     *
     * @return the covariance matrix.
     * @throws org.apache.commons.math3.linear.SingularMatrixException
     * if the covariance matrix cannot be computed (singular problem).
     * @see #getCovariances(double)
     * @deprecated As of 3.1. Please use {@link #computeCovariances(double[],double)}
     * instead.
     */
    @Deprecated
    public double[][] getCovariances() {
        return getCovariances(DEFAULT_SINGULARITY_THRESHOLD);
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
     * @param threshold Singularity threshold.
     * @return the covariance matrix.
     * @throws org.apache.commons.math3.linear.SingularMatrixException
     * if the covariance matrix cannot be computed (singular problem).
     * @deprecated As of 3.1. Please use {@link #computeCovariances(double[],double)}
     * instead.
     */
    @Deprecated
    public double[][] getCovariances(double threshold) {
        return computeCovariances(point, threshold);
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
     * @since 3.1
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
     * <p>
     * Returns an estimate of the standard deviation of each parameter. The
     * returned values are the so-called (asymptotic) standard errors on the
     * parameters, defined as {@code sd(a[i]) = sqrt(S / (n - m) * C[i][i])},
     * where {@code a[i]} is the optimized value of the {@code i}-th parameter,
     * {@code S} is the minimized value of the sum of squares objective function
     * (as returned by {@link #getChiSquare()}), {@code n} is the number of
     * observations, {@code m} is the number of parameters and {@code C} is the
     * covariance matrix.
     * </p>
     * <p>
     * See also
     * <a href="http://en.wikipedia.org/wiki/Least_squares">Wikipedia</a>,
     * or
     * <a href="http://mathworld.wolfram.com/LeastSquaresFitting.html">MathWorld</a>,
     * equations (34) and (35) for a particular case.
     * </p>
     *
     * @return an estimate of the standard deviation of the optimized parameters
     * @throws org.apache.commons.math3.linear.SingularMatrixException
     * if the covariance matrix cannot be computed.
     * @throws NumberIsTooSmallException if the number of degrees of freedom is not
     * positive, i.e. the number of measurements is less or equal to the number of
     * parameters.
     * @deprecated as of version 3.1, {@link #computeSigma(double[],double)} should be used
     * instead. It should be emphasized that {@code guessParametersErrors} and
     * {@code computeSigma} are <em>not</em> strictly equivalent.
     */
    @Deprecated
    public double[] guessParametersErrors() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370");
        if (ROR_less_equals(rows, cols, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370", _mut72328, _mut72329, _mut72330, _mut72331, _mut72332)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NO_DEGREES_OF_FREEDOM, rows, cols, false);
        }
        double[] errors = new double[cols];
        final double c = FastMath.sqrt(AOR_divide(getChiSquare(), (AOR_minus(rows, cols, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370", _mut72333, _mut72334, _mut72335, _mut72336)), "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370", _mut72337, _mut72338, _mut72339, _mut72340));
        double[][] covar = computeCovariances(point, 1e-14);
        for (int i = 0; ROR_less(i, errors.length, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370", _mut72345, _mut72346, _mut72347, _mut72348, _mut72349); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370");
            errors[i] = AOR_multiply(FastMath.sqrt(covar[i][i]), c, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.guessParametersErrors_370", _mut72341, _mut72342, _mut72343, _mut72344);
        }
        return errors;
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
     * @since 3.1
     */
    public double[] computeSigma(double[] params, double covarianceSingularityThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeSigma_400");
        final int nC = params.length;
        final double[] sig = new double[nC];
        final double[][] cov = computeCovariances(params, covarianceSingularityThreshold);
        for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeSigma_400", _mut72350, _mut72351, _mut72352, _mut72353, _mut72354); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeSigma_400");
            sig[i] = FastMath.sqrt(cov[i][i]);
        }
        return sig;
    }

    /**
     * {@inheritDoc}
     * @deprecated As of 3.1. Please use
     * {@link BaseAbstractMultivariateVectorOptimizer#optimize(int,
     * org.apache.commons.math3.analysis.MultivariateVectorFunction,OptimizationData[])
     * optimize(int,MultivariateDifferentiableVectorFunction,OptimizationData...)}
     * instead.
     */
    @Override
    @Deprecated
    public PointVectorValuePair optimize(int maxEval, final DifferentiableMultivariateVectorFunction f, final double[] target, final double[] weights, final double[] startPoint) {
        return optimizeInternal(maxEval, FunctionUtils.toMultivariateDifferentiableVectorFunction(f), new Target(target), new Weight(weights), new InitialGuess(startPoint));
    }

    /**
     * Optimize an objective function.
     * Optimization is considered to be a weighted least-squares minimization.
     * The cost function to be minimized is
     * <code>&sum;weight<sub>i</sub>(objective<sub>i</sub> - target<sub>i</sub>)<sup>2</sup></code>
     *
     * @param f Objective function.
     * @param target Target value for the objective functions at optimum.
     * @param weights Weights for the least squares cost computation.
     * @param startPoint Start point for optimization.
     * @return the point/value pair giving the optimal value for objective
     * function.
     * @param maxEval Maximum number of function evaluations.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the start point dimension is wrong.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximal number of evaluations is exceeded.
     * @throws org.apache.commons.math3.exception.NullArgumentException if
     * any argument is {@code null}.
     * @deprecated As of 3.1. Please use
     * {@link BaseAbstractMultivariateVectorOptimizer#optimize(int,
     * org.apache.commons.math3.analysis.MultivariateVectorFunction,OptimizationData[])
     * optimize(int,MultivariateDifferentiableVectorFunction,OptimizationData...)}
     * instead.
     */
    @Deprecated
    public PointVectorValuePair optimize(final int maxEval, final MultivariateDifferentiableVectorFunction f, final double[] target, final double[] weights, final double[] startPoint) {
        return optimizeInternal(maxEval, f, new Target(target), new Weight(weights), new InitialGuess(startPoint));
    }

    /**
     * Optimize an objective function.
     * Optimization is considered to be a weighted least-squares minimization.
     * The cost function to be minimized is
     * <code>&sum;weight<sub>i</sub>(objective<sub>i</sub> - target<sub>i</sub>)<sup>2</sup></code>
     *
     * @param maxEval Allowed number of evaluations of the objective function.
     * @param f Objective function.
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link Target}</li>
     *  <li>{@link Weight}</li>
     *  <li>{@link InitialGuess}</li>
     * </ul>
     * @return the point/value pair giving the optimal value of the objective
     * function.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException if
     * the maximal number of evaluations is exceeded.
     * @throws DimensionMismatchException if the target, and weight arguments
     * have inconsistent dimensions.
     * @see BaseAbstractMultivariateVectorOptimizer#optimizeInternal(int,
     * org.apache.commons.math3.analysis.MultivariateVectorFunction,OptimizationData[])
     * @since 3.1
     * @deprecated As of 3.1. Override is necessary only until this class's generic
     * argument is changed to {@code MultivariateDifferentiableVectorFunction}.
     */
    @Deprecated
    protected PointVectorValuePair optimizeInternal(final int maxEval, final MultivariateDifferentiableVectorFunction f, OptimizationData... optData) {
        // base class becomes "MultivariateDifferentiableVectorFunction".
        return super.optimizeInternal(maxEval, FunctionUtils.toDifferentiableMultivariateVectorFunction(f), optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() {
        super.setUp();
        // Reset counter.
        jacobianEvaluations = 0;
        // Square-root of the weight matrix.
        weightMatrixSqrt = squareRoot(getWeight());
        // every time it is used.
        jF = FunctionUtils.toMultivariateDifferentiableVectorFunction((DifferentiableMultivariateVectorFunction) getObjectiveFunction());
        // Arrays shared with "private" and "protected" methods.
        point = getStartPoint();
        rows = getTarget().length;
        cols = point.length;
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
     * @since 3.1
     */
    protected double[] computeResiduals(double[] objectiveValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeResiduals_543");
        final double[] target = getTarget();
        if (ROR_not_equals(objectiveValue.length, target.length, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeResiduals_543", _mut72355, _mut72356, _mut72357, _mut72358, _mut72359)) {
            throw new DimensionMismatchException(target.length, objectiveValue.length);
        }
        final double[] residuals = new double[target.length];
        for (int i = 0; ROR_less(i, target.length, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeResiduals_543", _mut72364, _mut72365, _mut72366, _mut72367, _mut72368); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeResiduals_543");
            residuals[i] = AOR_minus(target[i], objectiveValue[i], "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.computeResiduals_543", _mut72360, _mut72361, _mut72362, _mut72363);
        }
        return residuals;
    }

    /**
     * Computes the square-root of the weight matrix.
     *
     * @param m Symmetric, positive-definite (weight) matrix.
     * @return the square-root of the weight matrix.
     */
    private RealMatrix squareRoot(RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.squareRoot_564");
        if (m instanceof DiagonalMatrix) {
            final int dim = m.getRowDimension();
            final RealMatrix sqrtM = new DiagonalMatrix(dim);
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.squareRoot_564", _mut72369, _mut72370, _mut72371, _mut72372, _mut72373); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer.squareRoot_564");
                sqrtM.setEntry(i, i, FastMath.sqrt(m.getEntry(i, i)));
            }
            return sqrtM;
        } else {
            final EigenDecomposition dec = new EigenDecomposition(m);
            return dec.getSquareRoot();
        }
    }
}
