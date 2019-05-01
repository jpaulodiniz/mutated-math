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
package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Weight matrix of the residuals between model and observations.
 * <br/>
 * Immutable class.
 *
 * @since 3.1
 * @deprecated All classes and interfaces in this package are deprecated.
 * The optimizers that were provided here were moved to the
 * {@link org.apache.commons.math3.fitting.leastsquares} package
 * (cf. MATH-1008).
 */
@Deprecated
public class Weight implements OptimizationData {

    @Conditional
    public static boolean _mut65721 = false, _mut65722 = false, _mut65723 = false, _mut65724 = false, _mut65725 = false;

    /**
     * Weight matrix.
     */
    private final RealMatrix weightMatrix;

    /**
     * Creates a diagonal weight matrix.
     *
     * @param weight List of the values of the diagonal.
     */
    public Weight(double[] weight) {
        weightMatrix = new DiagonalMatrix(weight);
    }

    /**
     * @param weight Weight matrix.
     * @throws NonSquareMatrixException if the argument is not
     * a square matrix.
     */
    public Weight(RealMatrix weight) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.Weight.Weight_54");
        if (ROR_not_equals(weight.getColumnDimension(), weight.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.vector.Weight.Weight_54", _mut65721, _mut65722, _mut65723, _mut65724, _mut65725)) {
            throw new NonSquareMatrixException(weight.getColumnDimension(), weight.getRowDimension());
        }
        weightMatrix = weight.copy();
    }

    /**
     * Gets the initial guess.
     *
     * @return the initial guess.
     */
    public RealMatrix getWeight() {
        return weightMatrix.copy();
    }
}
