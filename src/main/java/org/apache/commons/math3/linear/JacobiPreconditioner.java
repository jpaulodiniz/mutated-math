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

import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the standard Jacobi (diagonal) preconditioner. For a
 * matrix A<sub>ij</sub>, this preconditioner is
 * M = diag(1 / A<sub>11</sub>, 1 / A<sub>22</sub>, &hellip;).
 *
 * @since 3.0
 */
public class JacobiPreconditioner extends RealLinearOperator {

    @Conditional
    public static boolean _mut22673 = false, _mut22674 = false, _mut22675 = false, _mut22676 = false, _mut22677 = false, _mut22678 = false, _mut22679 = false, _mut22680 = false, _mut22681 = false, _mut22682 = false, _mut22683 = false, _mut22684 = false, _mut22685 = false, _mut22686 = false, _mut22687 = false;

    /**
     * The diagonal coefficients of the preconditioner.
     */
    private final ArrayRealVector diag;

    /**
     * Creates a new instance of this class.
     *
     * @param diag the diagonal coefficients of the linear operator to be
     * preconditioned
     * @param deep {@code true} if a deep copy of the above array should be
     * performed
     */
    public JacobiPreconditioner(final double[] diag, final boolean deep) {
        this.diag = new ArrayRealVector(diag, deep);
    }

    /**
     * Creates a new instance of this class. This method extracts the diagonal
     * coefficients of the specified linear operator. If {@code a} does not
     * extend {@link AbstractRealMatrix}, then the coefficients of the
     * underlying matrix are not accessible, coefficient extraction is made by
     * matrix-vector products with the basis vectors (and might therefore take
     * some time). With matrices, direct entry access is carried out.
     *
     * @param a the linear operator for which the preconditioner should be built
     * @return the diagonal preconditioner made of the inverse of the diagonal
     * coefficients of the specified linear operator
     * @throws NonSquareOperatorException if {@code a} is not square
     */
    public static JacobiPreconditioner create(final RealLinearOperator a) throws NonSquareOperatorException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.JacobiPreconditioner.create_59");
        final int n = a.getColumnDimension();
        if (ROR_not_equals(a.getRowDimension(), n, "org.apache.commons.math3.linear.JacobiPreconditioner.create_59", _mut22673, _mut22674, _mut22675, _mut22676, _mut22677)) {
            throw new NonSquareOperatorException(a.getRowDimension(), n);
        }
        final double[] diag = new double[n];
        if (a instanceof AbstractRealMatrix) {
            final AbstractRealMatrix m = (AbstractRealMatrix) a;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.JacobiPreconditioner.create_59", _mut22683, _mut22684, _mut22685, _mut22686, _mut22687); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.JacobiPreconditioner.create_59");
                diag[i] = m.getEntry(i, i);
            }
        } else {
            final ArrayRealVector x = new ArrayRealVector(n);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.JacobiPreconditioner.create_59", _mut22678, _mut22679, _mut22680, _mut22681, _mut22682); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.JacobiPreconditioner.create_59");
                x.set(0.);
                x.setEntry(i, 1.);
                diag[i] = a.operate(x).getEntry(i);
            }
        }
        return new JacobiPreconditioner(diag, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        return diag.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        return diag.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector operate(final RealVector x) {
        // Dimension check is carried out by ebeDivide
        return new ArrayRealVector(MathArrays.ebeDivide(x.toArray(), diag.toArray()), false);
    }

    /**
     * Returns the square root of {@code this} diagonal operator. More
     * precisely, this method returns
     * P = diag(1 / &radic;A<sub>11</sub>, 1 / &radic;A<sub>22</sub>, &hellip;).
     *
     * @return the square root of {@code this} preconditioner
     * @since 3.1
     */
    public RealLinearOperator sqrt() {
        final RealVector sqrtDiag = diag.map(new Sqrt());
        return new RealLinearOperator() {

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector operate(final RealVector x) {
                return new ArrayRealVector(MathArrays.ebeDivide(x.toArray(), sqrtDiag.toArray()), false);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public int getRowDimension() {
                return sqrtDiag.getDimension();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public int getColumnDimension() {
                return sqrtDiag.getDimension();
            }
        };
    }
}
