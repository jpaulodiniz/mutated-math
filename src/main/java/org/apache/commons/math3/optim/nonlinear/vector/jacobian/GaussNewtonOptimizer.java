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

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Gauss-Newton least-squares solver.
 * <br/>
 * Constraints are not supported: the call to
 * {@link #optimize(OptimizationData[]) optimize} will throw
 * {@link MathUnsupportedOperationException} if bounds are passed to it.
 *
 * <p>
 * This class solve a least-square problem by solving the normal equations
 * of the linearized problem at each iteration. Either LU decomposition or
 * QR decomposition can be used to solve the normal equations. LU decomposition
 * is faster but QR decomposition is more robust for difficult problems.
 * </p>
 *
 * @since 2.0
 * @deprecated All classes and interfaces in this package are deprecated.
 * The optimizers that were provided here were moved to the
 * {@link org.apache.commons.math3.fitting.leastsquares} package
 * (cf. MATH-1008).
 */
@Deprecated
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {

    @Conditional
    public static boolean _mut65726 = false, _mut65727 = false, _mut65728 = false, _mut65729 = false, _mut65730 = false, _mut65731 = false, _mut65732 = false, _mut65733 = false, _mut65734 = false, _mut65735 = false, _mut65736 = false, _mut65737 = false, _mut65738 = false, _mut65739 = false, _mut65740 = false, _mut65741 = false, _mut65742 = false, _mut65743 = false, _mut65744 = false, _mut65745 = false, _mut65746 = false, _mut65747 = false, _mut65748 = false, _mut65749 = false, _mut65750 = false, _mut65751 = false, _mut65752 = false, _mut65753 = false, _mut65754 = false, _mut65755 = false, _mut65756 = false, _mut65757 = false, _mut65758 = false, _mut65759 = false, _mut65760 = false, _mut65761 = false, _mut65762 = false, _mut65763 = false, _mut65764 = false, _mut65765 = false, _mut65766 = false, _mut65767 = false, _mut65768 = false, _mut65769 = false, _mut65770 = false, _mut65771 = false, _mut65772 = false;

    /**
     * Indicator for using LU decomposition.
     */
    private final boolean useLU;

    /**
     * Simple constructor with default settings.
     * The normal equations will be solved using LU decomposition.
     *
     * @param checker Convergence checker.
     */
    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(true, checker);
    }

    /**
     * @param useLU If {@code true}, the normal equations will be solved
     * using LU decomposition, otherwise they will be solved using QR
     * decomposition.
     * @param checker Convergence checker.
     */
    public GaussNewtonOptimizer(final boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
        this.useLU = useLU;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PointVectorValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
        checkParameters();
        final ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
        // Computation will be useless without a checker (see "for-loop").
        if (checker == null) {
            throw new NullArgumentException();
        }
        final double[] targetValues = getTarget();
        // Number of observed data.
        final int nR = targetValues.length;
        final RealMatrix weightMatrix = getWeight();
        // Diagonal of the weight matrix.
        final double[] residualsWeights = new double[nR];
        for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65726, _mut65727, _mut65728, _mut65729, _mut65730); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
            residualsWeights[i] = weightMatrix.getEntry(i, i);
        }
        final double[] currentPoint = getStartPoint();
        final int nC = currentPoint.length;
        // iterate until convergence is reached
        PointVectorValuePair current = null;
        for (boolean converged = false; !converged; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
            incrementIterationCount();
            // evaluate the objective function and its jacobian
            PointVectorValuePair previous = current;
            // Value of the objective function at "currentPoint".
            final double[] currentObjective = computeObjectiveValue(currentPoint);
            final double[] currentResiduals = computeResiduals(currentObjective);
            final RealMatrix weightedJacobian = computeWeightedJacobian(currentPoint);
            current = new PointVectorValuePair(currentPoint, currentObjective);
            // build the linear problem
            final double[] b = new double[nC];
            final double[][] a = new double[nC][nC];
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65762, _mut65763, _mut65764, _mut65765, _mut65766); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
                final double[] grad = weightedJacobian.getRow(i);
                final double weight = residualsWeights[i];
                final double residual = currentResiduals[i];
                // compute the normal equation
                final double wr = AOR_multiply(weight, residual, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65731, _mut65732, _mut65733, _mut65734);
                for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65739, _mut65740, _mut65741, _mut65742, _mut65743); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
                    b[j] += AOR_multiply(wr, grad[j], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65735, _mut65736, _mut65737, _mut65738);
                }
                // build the contribution matrix for measurement i
                for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65757, _mut65758, _mut65759, _mut65760, _mut65761); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
                    double[] ak = a[k];
                    double wgk = AOR_multiply(weight, grad[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65744, _mut65745, _mut65746, _mut65747);
                    for (int l = 0; ROR_less(l, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65752, _mut65753, _mut65754, _mut65755, _mut65756); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
                        ak[l] += AOR_multiply(wgk, grad[l], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65748, _mut65749, _mut65750, _mut65751);
                    }
                }
            }
            // Check convergence.
            if (previous != null) {
                converged = checker.converged(getIterations(), previous, current);
                if (converged) {
                    setCost(computeCost(currentResiduals));
                    return current;
                }
            }
            try {
                // solve the linearized least squares problem
                RealMatrix mA = new BlockRealMatrix(a);
                DecompositionSolver solver = useLU ? new LUDecomposition(mA).getSolver() : new QRDecomposition(mA).getSolver();
                final double[] dX = solver.solve(new ArrayRealVector(b, false)).toArray();
                // update the estimated parameters
                for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82", _mut65767, _mut65768, _mut65769, _mut65770, _mut65771); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.doOptimize_82");
                    currentPoint[i] += dX[i];
                }
            } catch (SingularMatrixException e) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM);
            }
        }
        // Must never happen.
        throw new MathInternalError();
    }

    /**
     * @throws MathUnsupportedOperationException if bounds were passed to the
     * {@link #optimize(OptimizationData[]) optimize} method.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer.checkParameters_177");
        if ((_mut65772 ? (getLowerBound() != null && getUpperBound() != null) : (getLowerBound() != null || getUpperBound() != null))) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT);
        }
    }
}
