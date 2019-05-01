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

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * An implementation of {@link Evaluation} that is designed for extension. All of the
 * methods implemented here use the methods that are left unimplemented.
 * <p/>
 * TODO cache results?
 *
 * @since 3.3
 */
public abstract class AbstractEvaluation implements Evaluation {

    @Conditional
    public static boolean _mut38162 = false, _mut38163 = false, _mut38164 = false, _mut38165 = false, _mut38166 = false, _mut38167 = false, _mut38168 = false, _mut38169 = false, _mut38170 = false, _mut38171 = false, _mut38172 = false, _mut38173 = false, _mut38174 = false;

    /**
     * number of observations
     */
    private final int observationSize;

    /**
     * Constructor.
     *
     * @param observationSize the number of observation. Needed for {@link
     *                        #getRMS()}.
     */
    AbstractEvaluation(final int observationSize) {
        this.observationSize = observationSize;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getCovariances(double threshold) {
        // Set up the Jacobian.
        final RealMatrix j = this.getJacobian();
        // Compute transpose(J)J.
        final RealMatrix jTj = j.transpose().multiply(j);
        // Compute the covariances matrix.
        final DecompositionSolver solver = new QRDecomposition(jTj, threshold).getSolver();
        return solver.getInverse();
    }

    /**
     * {@inheritDoc}
     */
    public RealVector getSigma(double covarianceSingularityThreshold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getSigma_65");
        final RealMatrix cov = this.getCovariances(covarianceSingularityThreshold);
        final int nC = cov.getColumnDimension();
        final RealVector sig = new ArrayRealVector(nC);
        for (int i = 0; ROR_less(i, nC, "org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getSigma_65", _mut38162, _mut38163, _mut38164, _mut38165, _mut38166); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getSigma_65");
            sig.setEntry(i, FastMath.sqrt(cov.getEntry(i, i)));
        }
        return sig;
    }

    /**
     * {@inheritDoc}
     */
    public double getRMS() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getRMS_76");
        final double cost = this.getCost();
        return FastMath.sqrt(AOR_divide(AOR_multiply(cost, cost, "org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getRMS_76", _mut38167, _mut38168, _mut38169, _mut38170), this.observationSize, "org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation.getRMS_76", _mut38171, _mut38172, _mut38173, _mut38174));
    }

    /**
     * {@inheritDoc}
     */
    public double getCost() {
        final ArrayRealVector r = new ArrayRealVector(this.getResiduals());
        return FastMath.sqrt(r.dotProduct(r));
    }
}
