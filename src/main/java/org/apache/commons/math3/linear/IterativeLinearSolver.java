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
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This abstract class defines an iterative solver for the linear system A
 * &middot; x = b. In what follows, the <em>residual</em> r is defined as r = b
 * - A &middot; x, where A is the linear operator of the linear system, b is the
 * right-hand side vector, and x the current estimate of the solution.
 *
 * @since 3.0
 */
public abstract class IterativeLinearSolver {

    @Conditional
    public static boolean _mut26562 = false, _mut26563 = false, _mut26564 = false, _mut26565 = false, _mut26566 = false, _mut26567 = false, _mut26568 = false, _mut26569 = false, _mut26570 = false, _mut26571 = false, _mut26572 = false, _mut26573 = false, _mut26574 = false, _mut26575 = false, _mut26576 = false;

    /**
     * The object in charge of managing the iterations.
     */
    private final IterationManager manager;

    /**
     * Creates a new instance of this class, with default iteration manager.
     *
     * @param maxIterations the maximum number of iterations
     */
    public IterativeLinearSolver(final int maxIterations) {
        this.manager = new IterationManager(maxIterations);
    }

    /**
     * Creates a new instance of this class, with custom iteration manager.
     *
     * @param manager the custom iteration manager
     * @throws NullArgumentException if {@code manager} is {@code null}
     */
    public IterativeLinearSolver(final IterationManager manager) throws NullArgumentException {
        MathUtils.checkNotNull(manager);
        this.manager = manager;
    }

    /**
     * Performs all dimension checks on the parameters of
     * {@link #solve(RealLinearOperator, RealVector, RealVector) solve} and
     * {@link #solveInPlace(RealLinearOperator, RealVector, RealVector) solveInPlace},
     * and throws an exception if one of the checks fails.
     *
     * @param a the linear operator A of the system
     * @param b the right-hand side vector
     * @param x0 the initial guess of the solution
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} is not square
     * @throws DimensionMismatchException if {@code b} or {@code x0} have
     * dimensions inconsistent with {@code a}
     */
    protected static void checkParameters(final RealLinearOperator a, final RealVector b, final RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.IterativeLinearSolver.checkParameters_73");
        MathUtils.checkNotNull(a);
        MathUtils.checkNotNull(b);
        MathUtils.checkNotNull(x0);
        if (ROR_not_equals(a.getRowDimension(), a.getColumnDimension(), "org.apache.commons.math3.linear.IterativeLinearSolver.checkParameters_73", _mut26562, _mut26563, _mut26564, _mut26565, _mut26566)) {
            throw new NonSquareOperatorException(a.getRowDimension(), a.getColumnDimension());
        }
        if (ROR_not_equals(b.getDimension(), a.getRowDimension(), "org.apache.commons.math3.linear.IterativeLinearSolver.checkParameters_73", _mut26567, _mut26568, _mut26569, _mut26570, _mut26571)) {
            throw new DimensionMismatchException(b.getDimension(), a.getRowDimension());
        }
        if (ROR_not_equals(x0.getDimension(), a.getColumnDimension(), "org.apache.commons.math3.linear.IterativeLinearSolver.checkParameters_73", _mut26572, _mut26573, _mut26574, _mut26575, _mut26576)) {
            throw new DimensionMismatchException(x0.getDimension(), a.getColumnDimension());
        }
    }

    /**
     * Returns the iteration manager attached to this solver.
     *
     * @return the manager
     */
    public IterationManager getIterationManager() {
        return manager;
    }

    /**
     * Returns an estimate of the solution to the linear system A &middot; x =
     * b.
     *
     * @param a the linear operator A of the system
     * @param b the right-hand side vector
     * @return a new vector containing the solution
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} is not square
     * @throws DimensionMismatchException if {@code b} has dimensions
     * inconsistent with {@code a}
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     */
    public RealVector solve(final RealLinearOperator a, final RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        final RealVector x = new ArrayRealVector(a.getColumnDimension());
        x.set(0.);
        return solveInPlace(a, b, x);
    }

    /**
     * Returns an estimate of the solution to the linear system A &middot; x =
     * b.
     *
     * @param a the linear operator A of the system
     * @param b the right-hand side vector
     * @param x0 the initial guess of the solution
     * @return a new vector containing the solution
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} is not square
     * @throws DimensionMismatchException if {@code b} or {@code x0} have
     * dimensions inconsistent with {@code a}
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     */
    public RealVector solve(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(x0);
        return solveInPlace(a, b, x0.copy());
    }

    /**
     * Returns an estimate of the solution to the linear system A &middot; x =
     * b. The solution is computed in-place (initial guess is modified).
     *
     * @param a the linear operator A of the system
     * @param b the right-hand side vector
     * @param x0 initial guess of the solution
     * @return a reference to {@code x0} (shallow copy) updated with the
     * solution
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} is not square
     * @throws DimensionMismatchException if {@code b} or {@code x0} have
     * dimensions inconsistent with {@code a}
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     */
    public abstract RealVector solveInPlace(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;
}
