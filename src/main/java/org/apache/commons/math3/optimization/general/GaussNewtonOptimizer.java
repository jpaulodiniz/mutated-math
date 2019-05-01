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

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Gauss-Newton least-squares solver.
 * <p>
 * This class solve a least-square problem by solving the normal equations
 * of the linearized problem at each iteration. Either LU decomposition or
 * QR decomposition can be used to solve the normal equations. LU decomposition
 * is faster but QR decomposition is more robust for difficult problems.
 * </p>
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {

    @Conditional
    public static boolean _mut72254 = false, _mut72255 = false, _mut72256 = false, _mut72257 = false, _mut72258 = false, _mut72259 = false, _mut72260 = false, _mut72261 = false, _mut72262 = false, _mut72263 = false, _mut72264 = false, _mut72265 = false, _mut72266 = false, _mut72267 = false, _mut72268 = false, _mut72269 = false, _mut72270 = false, _mut72271 = false, _mut72272 = false, _mut72273 = false, _mut72274 = false, _mut72275 = false, _mut72276 = false, _mut72277 = false, _mut72278 = false, _mut72279 = false, _mut72280 = false, _mut72281 = false, _mut72282 = false, _mut72283 = false, _mut72284 = false, _mut72285 = false, _mut72286 = false, _mut72287 = false, _mut72288 = false, _mut72289 = false, _mut72290 = false, _mut72291 = false, _mut72292 = false, _mut72293 = false, _mut72294 = false, _mut72295 = false, _mut72296 = false, _mut72297 = false, _mut72298 = false, _mut72299 = false;

    /**
     * Indicator for using LU decomposition.
     */
    private final boolean useLU;

    /**
     * Simple constructor with default settings.
     * The normal equations will be solved using LU decomposition and the
     * convergence check is set to a {@link SimpleVectorValueChecker}
     * with default tolerances.
     * @deprecated See {@link SimpleVectorValueChecker#SimpleVectorValueChecker()}
     */
    @Deprecated
    public GaussNewtonOptimizer() {
        this(true);
    }

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
     * Simple constructor with default settings.
     * The convergence check is set to a {@link SimpleVectorValueChecker}
     * with default tolerances.
     *
     * @param useLU If {@code true}, the normal equations will be solved
     * using LU decomposition, otherwise they will be solved using QR
     * decomposition.
     * @deprecated See {@link SimpleVectorValueChecker#SimpleVectorValueChecker()}
     */
    @Deprecated
    public GaussNewtonOptimizer(final boolean useLU) {
        this(useLU, new SimpleVectorValueChecker());
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
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
        for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72254, _mut72255, _mut72256, _mut72257, _mut72258); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
            residualsWeights[i] = weightMatrix.getEntry(i, i);
        }
        final double[] currentPoint = getStartPoint();
        final int nC = currentPoint.length;
        // iterate until convergence is reached
        PointVectorValuePair current = null;
        int iter = 0;
        for (boolean converged = false; !converged; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
            ++iter;
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
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72290, _mut72291, _mut72292, _mut72293, _mut72294); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
                final double[] grad = weightedJacobian.getRow(i);
                final double weight = residualsWeights[i];
                final double residual = currentResiduals[i];
                // compute the normal equation
                final double wr = AOR_multiply(weight, residual, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72259, _mut72260, _mut72261, _mut72262);
                for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72267, _mut72268, _mut72269, _mut72270, _mut72271); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
                    b[j] += AOR_multiply(wr, grad[j], "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72263, _mut72264, _mut72265, _mut72266);
                }
                // build the contribution matrix for measurement i
                for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72285, _mut72286, _mut72287, _mut72288, _mut72289); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
                    double[] ak = a[k];
                    double wgk = AOR_multiply(weight, grad[k], "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72272, _mut72273, _mut72274, _mut72275);
                    for (int l = 0; ROR_less(l, nC, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72280, _mut72281, _mut72282, _mut72283, _mut72284); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
                        ak[l] += AOR_multiply(wgk, grad[l], "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72276, _mut72277, _mut72278, _mut72279);
                    }
                }
            }
            try {
                // solve the linearized least squares problem
                RealMatrix mA = new BlockRealMatrix(a);
                DecompositionSolver solver = useLU ? new LUDecomposition(mA).getSolver() : new QRDecomposition(mA).getSolver();
                final double[] dX = solver.solve(new ArrayRealVector(b, false)).toArray();
                // update the estimated parameters
                for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103", _mut72295, _mut72296, _mut72297, _mut72298, _mut72299); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.GaussNewtonOptimizer.doOptimize_103");
                    currentPoint[i] += dX[i];
                }
            } catch (SingularMatrixException e) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM);
            }
            // Check convergence.
            if (previous != null) {
                converged = checker.converged(iter, previous, current);
                if (converged) {
                    cost = computeCost(currentResiduals);
                    // Update (deprecated) "point" field.
                    point = current.getPoint();
                    return current;
                }
            }
        }
        // Must never happen.
        throw new MathInternalError();
    }
}
