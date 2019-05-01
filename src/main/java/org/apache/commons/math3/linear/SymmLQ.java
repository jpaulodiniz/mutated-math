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
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * Implementation of the SYMMLQ iterative linear solver proposed by <a
 * href="#PAIG1975">Paige and Saunders (1975)</a>. This implementation is
 * largely based on the FORTRAN code by Pr. Michael A. Saunders, available <a
 * href="http://www.stanford.edu/group/SOL/software/symmlq/f77/">here</a>.
 * </p>
 * <p>
 * SYMMLQ is designed to solve the system of linear equations A &middot; x = b
 * where A is an n &times; n self-adjoint linear operator (defined as a
 * {@link RealLinearOperator}), and b is a given vector. The operator A is not
 * required to be positive definite. If A is known to be definite, the method of
 * conjugate gradients might be preferred, since it will require about the same
 * number of iterations as SYMMLQ but slightly less work per iteration.
 * </p>
 * <p>
 * SYMMLQ is designed to solve the system (A - shift &middot; I) &middot; x = b,
 * where shift is a specified scalar value. If shift and b are suitably chosen,
 * the computed vector x may approximate an (unnormalized) eigenvector of A, as
 * in the methods of inverse iteration and/or Rayleigh-quotient iteration.
 * Again, the linear operator (A - shift &middot; I) need not be positive
 * definite (but <em>must</em> be self-adjoint). The work per iteration is very
 * slightly less if shift = 0.
 * </p>
 * <h3>Preconditioning</h3>
 * <p>
 * Preconditioning may reduce the number of iterations required. The solver may
 * be provided with a positive definite preconditioner
 * M = P<sup>T</sup> &middot; P
 * that is known to approximate
 * (A - shift &middot; I)<sup>-1</sup> in some sense, where matrix-vector
 * products of the form M &middot; y = x can be computed efficiently. Then
 * SYMMLQ will implicitly solve the system of equations
 * P &middot; (A - shift &middot; I) &middot; P<sup>T</sup> &middot;
 * x<sub>hat</sub> = P &middot; b, i.e.
 * A<sub>hat</sub> &middot; x<sub>hat</sub> = b<sub>hat</sub>,
 * where
 * A<sub>hat</sub> = P &middot; (A - shift &middot; I) &middot; P<sup>T</sup>,
 * b<sub>hat</sub> = P &middot; b,
 * and return the solution
 * x = P<sup>T</sup> &middot; x<sub>hat</sub>.
 * The associated residual is
 * r<sub>hat</sub> = b<sub>hat</sub> - A<sub>hat</sub> &middot; x<sub>hat</sub>
 *                 = P &middot; [b - (A - shift &middot; I) &middot; x]
 *                 = P &middot; r.
 * </p>
 * <p>
 * In the case of preconditioning, the {@link IterativeLinearSolverEvent}s that
 * this solver fires are such that
 * {@link IterativeLinearSolverEvent#getNormOfResidual()} returns the norm of
 * the <em>preconditioned</em>, updated residual, ||P &middot; r||, not the norm
 * of the <em>true</em> residual ||r||.
 * </p>
 * <h3><a id="stopcrit">Default stopping criterion</a></h3>
 * <p>
 * A default stopping criterion is implemented. The iterations stop when || rhat
 * || &le; &delta; || Ahat || || xhat ||, where xhat is the current estimate of
 * the solution of the transformed system, rhat the current estimate of the
 * corresponding residual, and &delta; a user-specified tolerance.
 * </p>
 * <h3>Iteration count</h3>
 * <p>
 * In the present context, an iteration should be understood as one evaluation
 * of the matrix-vector product A &middot; x. The initialization phase therefore
 * counts as one iteration. If the user requires checks on the symmetry of A,
 * this entails one further matrix-vector product in the initial phase. This
 * further product is <em>not</em> accounted for in the iteration count. In
 * other words, the number of iterations required to reach convergence will be
 * identical, whether checks have been required or not.
 * </p>
 * <p>
 * The present definition of the iteration count differs from that adopted in
 * the original FOTRAN code, where the initialization phase was <em>not</em>
 * taken into account.
 * </p>
 * <h3><a id="initguess">Initial guess of the solution</a></h3>
 * <p>
 * The {@code x} parameter in
 * <ul>
 * <li>{@link #solve(RealLinearOperator, RealVector, RealVector)},</li>
 * <li>{@link #solve(RealLinearOperator, RealLinearOperator, RealVector, RealVector)}},</li>
 * <li>{@link #solveInPlace(RealLinearOperator, RealVector, RealVector)},</li>
 * <li>{@link #solveInPlace(RealLinearOperator, RealLinearOperator, RealVector, RealVector)},</li>
 * <li>{@link #solveInPlace(RealLinearOperator, RealLinearOperator, RealVector, RealVector, boolean, double)},</li>
 * </ul>
 * should not be considered as an initial guess, as it is set to zero in the
 * initial phase. If x<sub>0</sub> is known to be a good approximation to x, one
 * should compute r<sub>0</sub> = b - A &middot; x, solve A &middot; dx = r0,
 * and set x = x<sub>0</sub> + dx.
 * </p>
 * <h3><a id="context">Exception context</a></h3>
 * <p>
 * Besides standard {@link DimensionMismatchException}, this class might throw
 * {@link NonSelfAdjointOperatorException} if the linear operator or the
 * preconditioner are not symmetric. In this case, the {@link ExceptionContext}
 * provides more information
 * <ul>
 * <li>key {@code "operator"} points to the offending linear operator, say L,</li>
 * <li>key {@code "vector1"} points to the first offending vector, say x,
 * <li>key {@code "vector2"} points to the second offending vector, say y, such
 * that x<sup>T</sup> &middot; L &middot; y &ne; y<sup>T</sup> &middot; L
 * &middot; x (within a certain accuracy).</li>
 * </ul>
 * </p>
 * <p>
 * {@link NonPositiveDefiniteOperatorException} might also be thrown in case the
 * preconditioner is not positive definite. The relevant keys to the
 * {@link ExceptionContext} are
 * <ul>
 * <li>key {@code "operator"}, which points to the offending linear operator,
 * say L,</li>
 * <li>key {@code "vector"}, which points to the offending vector, say x, such
 * that x<sup>T</sup> &middot; L &middot; x < 0.</li>
 * </ul>
 * </p>
 * <h3>References</h3>
 * <dl>
 * <dt><a id="PAIG1975">Paige and Saunders (1975)</a></dt>
 * <dd>C. C. Paige and M. A. Saunders, <a
 * href="http://www.stanford.edu/group/SOL/software/symmlq/PS75.pdf"><em>
 * Solution of Sparse Indefinite Systems of Linear Equations</em></a>, SIAM
 * Journal on Numerical Analysis 12(4): 617-629, 1975</dd>
 * </dl>
 *
 * @since 3.0
 */
public class SymmLQ extends PreconditionedIterativeLinearSolver {

    @Conditional
    public static boolean _mut25361 = false, _mut25362 = false, _mut25363 = false, _mut25364 = false, _mut25365 = false, _mut25366 = false, _mut25367 = false, _mut25368 = false, _mut25369 = false, _mut25370 = false, _mut25371 = false, _mut25372 = false, _mut25373 = false, _mut25374 = false, _mut25375 = false, _mut25376 = false, _mut25377 = false, _mut25378 = false, _mut25379 = false, _mut25380 = false, _mut25381 = false, _mut25382 = false, _mut25383 = false, _mut25384 = false, _mut25385 = false, _mut25386 = false, _mut25387 = false, _mut25388 = false, _mut25389 = false, _mut25390 = false, _mut25391 = false, _mut25392 = false, _mut25393 = false, _mut25394 = false, _mut25395 = false, _mut25396 = false, _mut25397 = false, _mut25398 = false, _mut25399 = false, _mut25400 = false, _mut25401 = false, _mut25402 = false, _mut25403 = false, _mut25404 = false, _mut25405 = false, _mut25406 = false, _mut25407 = false, _mut25408 = false, _mut25409 = false, _mut25410 = false, _mut25411 = false, _mut25412 = false, _mut25413 = false, _mut25414 = false, _mut25415 = false, _mut25416 = false, _mut25417 = false, _mut25418 = false, _mut25419 = false, _mut25420 = false, _mut25421 = false, _mut25422 = false, _mut25423 = false, _mut25424 = false, _mut25425 = false, _mut25426 = false, _mut25427 = false, _mut25428 = false, _mut25429 = false, _mut25430 = false, _mut25431 = false, _mut25432 = false, _mut25433 = false, _mut25434 = false, _mut25435 = false, _mut25436 = false, _mut25437 = false, _mut25438 = false, _mut25439 = false, _mut25440 = false, _mut25441 = false, _mut25442 = false, _mut25443 = false, _mut25444 = false, _mut25445 = false, _mut25446 = false, _mut25447 = false, _mut25448 = false, _mut25449 = false, _mut25450 = false, _mut25451 = false, _mut25452 = false, _mut25453 = false, _mut25454 = false, _mut25455 = false, _mut25456 = false, _mut25457 = false, _mut25458 = false, _mut25459 = false, _mut25460 = false, _mut25461 = false, _mut25462 = false, _mut25463 = false, _mut25464 = false, _mut25465 = false, _mut25466 = false, _mut25467 = false, _mut25468 = false, _mut25469 = false, _mut25470 = false, _mut25471 = false, _mut25472 = false, _mut25473 = false, _mut25474 = false, _mut25475 = false, _mut25476 = false, _mut25477 = false, _mut25478 = false, _mut25479 = false, _mut25480 = false, _mut25481 = false, _mut25482 = false, _mut25483 = false, _mut25484 = false, _mut25485 = false, _mut25486 = false, _mut25487 = false, _mut25488 = false, _mut25489 = false, _mut25490 = false, _mut25491 = false, _mut25492 = false, _mut25493 = false, _mut25494 = false, _mut25495 = false, _mut25496 = false, _mut25497 = false, _mut25498 = false, _mut25499 = false, _mut25500 = false, _mut25501 = false, _mut25502 = false, _mut25503 = false, _mut25504 = false, _mut25505 = false, _mut25506 = false, _mut25507 = false, _mut25508 = false, _mut25509 = false, _mut25510 = false, _mut25511 = false, _mut25512 = false, _mut25513 = false, _mut25514 = false, _mut25515 = false, _mut25516 = false, _mut25517 = false, _mut25518 = false, _mut25519 = false, _mut25520 = false, _mut25521 = false, _mut25522 = false, _mut25523 = false, _mut25524 = false, _mut25525 = false, _mut25526 = false, _mut25527 = false, _mut25528 = false, _mut25529 = false, _mut25530 = false, _mut25531 = false, _mut25532 = false, _mut25533 = false, _mut25534 = false, _mut25535 = false, _mut25536 = false, _mut25537 = false, _mut25538 = false, _mut25539 = false, _mut25540 = false, _mut25541 = false, _mut25542 = false, _mut25543 = false, _mut25544 = false, _mut25545 = false, _mut25546 = false, _mut25547 = false, _mut25548 = false, _mut25549 = false, _mut25550 = false, _mut25551 = false, _mut25552 = false, _mut25553 = false, _mut25554 = false, _mut25555 = false, _mut25556 = false, _mut25557 = false, _mut25558 = false, _mut25559 = false, _mut25560 = false, _mut25561 = false, _mut25562 = false, _mut25563 = false, _mut25564 = false, _mut25565 = false, _mut25566 = false, _mut25567 = false, _mut25568 = false, _mut25569 = false, _mut25570 = false, _mut25571 = false, _mut25572 = false, _mut25573 = false, _mut25574 = false, _mut25575 = false, _mut25576 = false, _mut25577 = false, _mut25578 = false, _mut25579 = false, _mut25580 = false, _mut25581 = false, _mut25582 = false, _mut25583 = false, _mut25584 = false, _mut25585 = false, _mut25586 = false, _mut25587 = false, _mut25588 = false, _mut25589 = false, _mut25590 = false, _mut25591 = false, _mut25592 = false, _mut25593 = false, _mut25594 = false, _mut25595 = false, _mut25596 = false, _mut25597 = false, _mut25598 = false, _mut25599 = false, _mut25600 = false, _mut25601 = false, _mut25602 = false, _mut25603 = false, _mut25604 = false, _mut25605 = false, _mut25606 = false, _mut25607 = false, _mut25608 = false, _mut25609 = false, _mut25610 = false, _mut25611 = false, _mut25612 = false, _mut25613 = false, _mut25614 = false, _mut25615 = false, _mut25616 = false, _mut25617 = false, _mut25618 = false, _mut25619 = false, _mut25620 = false, _mut25621 = false, _mut25622 = false, _mut25623 = false, _mut25624 = false, _mut25625 = false, _mut25626 = false, _mut25627 = false, _mut25628 = false, _mut25629 = false, _mut25630 = false, _mut25631 = false, _mut25632 = false, _mut25633 = false, _mut25634 = false, _mut25635 = false, _mut25636 = false, _mut25637 = false, _mut25638 = false, _mut25639 = false, _mut25640 = false, _mut25641 = false, _mut25642 = false, _mut25643 = false, _mut25644 = false, _mut25645 = false, _mut25646 = false, _mut25647 = false, _mut25648 = false, _mut25649 = false, _mut25650 = false, _mut25651 = false, _mut25652 = false, _mut25653 = false, _mut25654 = false, _mut25655 = false, _mut25656 = false, _mut25657 = false, _mut25658 = false, _mut25659 = false, _mut25660 = false, _mut25661 = false, _mut25662 = false, _mut25663 = false, _mut25664 = false, _mut25665 = false, _mut25666 = false, _mut25667 = false, _mut25668 = false, _mut25669 = false, _mut25670 = false, _mut25671 = false, _mut25672 = false, _mut25673 = false, _mut25674 = false, _mut25675 = false, _mut25676 = false, _mut25677 = false, _mut25678 = false, _mut25679 = false, _mut25680 = false, _mut25681 = false, _mut25682 = false, _mut25683 = false, _mut25684 = false, _mut25685 = false, _mut25686 = false, _mut25687 = false, _mut25688 = false, _mut25689 = false, _mut25690 = false, _mut25691 = false, _mut25692 = false, _mut25693 = false, _mut25694 = false, _mut25695 = false, _mut25696 = false, _mut25697 = false, _mut25698 = false, _mut25699 = false, _mut25700 = false, _mut25701 = false, _mut25702 = false, _mut25703 = false, _mut25704 = false, _mut25705 = false, _mut25706 = false, _mut25707 = false, _mut25708 = false, _mut25709 = false, _mut25710 = false, _mut25711 = false, _mut25712 = false, _mut25713 = false, _mut25714 = false, _mut25715 = false, _mut25716 = false, _mut25717 = false, _mut25718 = false, _mut25719 = false, _mut25720 = false, _mut25721 = false, _mut25722 = false, _mut25723 = false, _mut25724 = false, _mut25725 = false, _mut25726 = false, _mut25727 = false, _mut25728 = false, _mut25729 = false, _mut25730 = false, _mut25731 = false, _mut25732 = false, _mut25733 = false, _mut25734 = false, _mut25735 = false, _mut25736 = false, _mut25737 = false, _mut25738 = false, _mut25739 = false, _mut25740 = false, _mut25741 = false, _mut25742 = false, _mut25743 = false, _mut25744 = false, _mut25745 = false, _mut25746 = false, _mut25747 = false, _mut25748 = false, _mut25749 = false, _mut25750 = false, _mut25751 = false, _mut25752 = false, _mut25753 = false, _mut25754 = false, _mut25755 = false, _mut25756 = false, _mut25757 = false, _mut25758 = false, _mut25759 = false, _mut25760 = false, _mut25761 = false, _mut25762 = false, _mut25763 = false, _mut25764 = false, _mut25765 = false, _mut25766 = false, _mut25767 = false, _mut25768 = false, _mut25769 = false, _mut25770 = false, _mut25771 = false, _mut25772 = false, _mut25773 = false, _mut25774 = false, _mut25775 = false, _mut25776 = false, _mut25777 = false, _mut25778 = false, _mut25779 = false, _mut25780 = false, _mut25781 = false, _mut25782 = false, _mut25783 = false, _mut25784 = false, _mut25785 = false, _mut25786 = false, _mut25787 = false;

    /**
     * <p>
     * A simple container holding the non-final variables used in the
     * iterations. Making the current state of the solver visible from the
     * outside is necessary, because during the iterations, {@code x} does not
     * <em>exactly</em> hold the current estimate of the solution. Indeed,
     * {@code x} needs in general to be moved from the LQ point to the CG point.
     * Besides, additional upudates must be carried out in case {@code goodb} is
     * set to {@code true}.
     * </p>
     * <p>
     * In all subsequent comments, the description of the state variables refer
     * to their value after a call to {@link #update()}. In these comments, k is
     * the current number of evaluations of matrix-vector products.
     * </p>
     */
    private static class State {

        /**
         * The cubic root of {@link #MACH_PREC}.
         */
        static final double CBRT_MACH_PREC;

        /**
         * The machine precision.
         */
        static final double MACH_PREC;

        /**
         * Reference to the linear operator.
         */
        private final RealLinearOperator a;

        /**
         * Reference to the right-hand side vector.
         */
        private final RealVector b;

        /**
         * {@code true} if symmetry of matrix and conditioner must be checked.
         */
        private final boolean check;

        /**
         * The value of the custom tolerance &delta; for the default stopping
         * criterion.
         */
        private final double delta;

        /**
         * The value of beta[k+1].
         */
        private double beta;

        /**
         * The value of beta[1].
         */
        private double beta1;

        /**
         * The value of bstep[k-1].
         */
        private double bstep;

        /**
         * The estimate of the norm of P * rC[k].
         */
        private double cgnorm;

        /**
         * The value of dbar[k+1] = -beta[k+1] * c[k-1].
         */
        private double dbar;

        /**
         * The value of gamma[k] * zeta[k]. Was called {@code rhs1} in the
         * initial code.
         */
        private double gammaZeta;

        /**
         * The value of gbar[k].
         */
        private double gbar;

        /**
         * The value of max(|alpha[1]|, gamma[1], ..., gamma[k-1]).
         */
        private double gmax;

        /**
         * The value of min(|alpha[1]|, gamma[1], ..., gamma[k-1]).
         */
        private double gmin;

        /**
         * Copy of the {@code goodb} parameter.
         */
        private final boolean goodb;

        /**
         * {@code true} if the default convergence criterion is verified.
         */
        private boolean hasConverged;

        /**
         * The estimate of the norm of P * rL[k-1].
         */
        private double lqnorm;

        /**
         * Reference to the preconditioner, M.
         */
        private final RealLinearOperator m;

        /**
         * The value of (-eps[k+1] * zeta[k-1]). Was called {@code rhs2} in the
         * initial code.
         */
        private double minusEpsZeta;

        /**
         * The value of M * b.
         */
        private final RealVector mb;

        /**
         * The value of beta[k].
         */
        private double oldb;

        /**
         * The value of beta[k] * M^(-1) * P' * v[k].
         */
        private RealVector r1;

        /**
         * The value of beta[k+1] * M^(-1) * P' * v[k+1].
         */
        private RealVector r2;

        /**
         * The value of the updated, preconditioned residual P * r. This value is
         * given by {@code min(}{@link #cgnorm}{@code , }{@link #lqnorm}{@code )}.
         */
        private double rnorm;

        /**
         * Copy of the {@code shift} parameter.
         */
        private final double shift;

        /**
         * The value of s[1] * ... * s[k-1].
         */
        private double snprod;

        /**
         * An estimate of the square of the norm of A * V[k], based on Paige and
         * Saunders (1975), equation (3.3).
         */
        private double tnorm;

        /**
         * The value of P' * wbar[k] or P' * (wbar[k] - s[1] * ... * s[k-1] *
         * v[1]) if {@code goodb} is {@code true}. Was called {@code w} in the
         * initial code.
         */
        private RealVector wbar;

        /**
         * A reference to the vector to be updated with the solution. Contains
         * the value of xL[k-1] if {@code goodb} is {@code false}, (xL[k-1] -
         * bstep[k-1] * v[1]) otherwise.
         */
        private final RealVector xL;

        /**
         * The value of beta[k+1] * P' * v[k+1].
         */
        private RealVector y;

        /**
         * The value of zeta[1]^2 + ... + zeta[k-1]^2.
         */
        private double ynorm2;

        /**
         * The value of {@code b == 0} (exact floating-point equality).
         */
        private boolean bIsNull;

        static {
            MACH_PREC = FastMath.ulp(1.);
            CBRT_MACH_PREC = FastMath.cbrt(MACH_PREC);
        }

        /**
         * Creates and inits to k = 1 a new instance of this class.
         *
         * @param a the linear operator A of the system
         * @param m the preconditioner, M (can be {@code null})
         * @param b the right-hand side vector
         * @param goodb usually {@code false}, except if {@code x} is expected
         * to contain a large multiple of {@code b}
         * @param shift the amount to be subtracted to all diagonal elements of
         * A
         * @param delta the &delta; parameter for the default stopping criterion
         * @param check {@code true} if self-adjointedness of both matrix and
         * preconditioner should be checked
         */
        State(final RealLinearOperator a, final RealLinearOperator m, final RealVector b, final boolean goodb, final double shift, final double delta, final boolean check) {
            this.a = a;
            this.m = m;
            this.b = b;
            this.xL = new ArrayRealVector(b.getDimension());
            this.goodb = goodb;
            this.shift = shift;
            this.mb = m == null ? b : m.operate(b);
            this.hasConverged = false;
            this.check = check;
            this.delta = delta;
        }

        /**
         * Performs a symmetry check on the specified linear operator, and throws an
         * exception in case this check fails. Given a linear operator L, and a
         * vector x, this method checks that
         * x' &middot; L &middot; y = y' &middot; L &middot; x
         * (within a given accuracy), where y = L &middot; x.
         *
         * @param l the linear operator L
         * @param x the candidate vector x
         * @param y the candidate vector y = L &middot; x
         * @param z the vector z = L &middot; y
         * @throws NonSelfAdjointOperatorException when the test fails
         */
        private static void checkSymmetry(final RealLinearOperator l, final RealVector x, final RealVector y, final RealVector z) throws NonSelfAdjointOperatorException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.checkSymmetry_415");
            final double s = y.dotProduct(y);
            final double t = x.dotProduct(z);
            final double epsa = AOR_multiply((AOR_plus(s, MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.checkSymmetry_415", _mut25361, _mut25362, _mut25363, _mut25364)), CBRT_MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.checkSymmetry_415", _mut25365, _mut25366, _mut25367, _mut25368);
            if (ROR_greater(FastMath.abs(AOR_minus(s, t, "org.apache.commons.math3.linear.SymmLQ.checkSymmetry_415", _mut25369, _mut25370, _mut25371, _mut25372)), epsa, "org.apache.commons.math3.linear.SymmLQ.checkSymmetry_415", _mut25373, _mut25374, _mut25375, _mut25376, _mut25377)) {
                final NonSelfAdjointOperatorException e;
                e = new NonSelfAdjointOperatorException();
                final ExceptionContext context = e.getContext();
                context.setValue(SymmLQ.OPERATOR, l);
                context.setValue(SymmLQ.VECTOR1, x);
                context.setValue(SymmLQ.VECTOR2, y);
                context.setValue(SymmLQ.THRESHOLD, Double.valueOf(epsa));
                throw e;
            }
        }

        /**
         * Throws a new {@link NonPositiveDefiniteOperatorException} with
         * appropriate context.
         *
         * @param l the offending linear operator
         * @param v the offending vector
         * @throws NonPositiveDefiniteOperatorException in any circumstances
         */
        private static void throwNPDLOException(final RealLinearOperator l, final RealVector v) throws NonPositiveDefiniteOperatorException {
            final NonPositiveDefiniteOperatorException e;
            e = new NonPositiveDefiniteOperatorException();
            final ExceptionContext context = e.getContext();
            context.setValue(OPERATOR, l);
            context.setValue(VECTOR, v);
            throw e;
        }

        /**
         * A clone of the BLAS {@code DAXPY} function, which carries out the
         * operation y &larr; a &middot; x + y. This is for internal use only: no
         * dimension checks are provided.
         *
         * @param a the scalar by which {@code x} is to be multiplied
         * @param x the vector to be added to {@code y}
         * @param y the vector to be incremented
         */
        private static void daxpy(final double a, final RealVector x, final RealVector y) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.daxpy_460");
            final int n = x.getDimension();
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.daxpy_460", _mut25386, _mut25387, _mut25388, _mut25389, _mut25390); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.daxpy_460");
                y.setEntry(i, AOR_plus(AOR_multiply(a, x.getEntry(i), "org.apache.commons.math3.linear.SymmLQ.daxpy_460", _mut25378, _mut25379, _mut25380, _mut25381), y.getEntry(i), "org.apache.commons.math3.linear.SymmLQ.daxpy_460", _mut25382, _mut25383, _mut25384, _mut25385));
            }
        }

        /**
         * A BLAS-like function, for the operation z &larr; a &middot; x + b
         * &middot; y + z. This is for internal use only: no dimension checks are
         * provided.
         *
         * @param a the scalar by which {@code x} is to be multiplied
         * @param x the first vector to be added to {@code z}
         * @param b the scalar by which {@code y} is to be multiplied
         * @param y the second vector to be added to {@code z}
         * @param z the vector to be incremented
         */
        private static void daxpbypz(final double a, final RealVector x, final double b, final RealVector y, final RealVector z) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.daxpbypz_479");
            final int n = z.getDimension();
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.daxpbypz_479", _mut25407, _mut25408, _mut25409, _mut25410, _mut25411); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.daxpbypz_479");
                final double zi;
                zi = AOR_plus(AOR_plus(AOR_multiply(a, x.getEntry(i), "org.apache.commons.math3.linear.SymmLQ.daxpbypz_479", _mut25391, _mut25392, _mut25393, _mut25394), AOR_multiply(b, y.getEntry(i), "org.apache.commons.math3.linear.SymmLQ.daxpbypz_479", _mut25395, _mut25396, _mut25397, _mut25398), "org.apache.commons.math3.linear.SymmLQ.daxpbypz_479", _mut25399, _mut25400, _mut25401, _mut25402), z.getEntry(i), "org.apache.commons.math3.linear.SymmLQ.daxpbypz_479", _mut25403, _mut25404, _mut25405, _mut25406);
                z.setEntry(i, zi);
            }
        }

        /**
         * <p>
         * Move to the CG point if it seems better. In this version of SYMMLQ,
         * the convergence tests involve only cgnorm, so we're unlikely to stop
         * at an LQ point, except if the iteration limit interferes.
         * </p>
         * <p>
         * Additional upudates are also carried out in case {@code goodb} is set
         * to {@code true}.
         * </p>
         *
         * @param x the vector to be updated with the refined value of xL
         */
        void refineSolution(final RealVector x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.refineSolution_502");
            final int n = this.xL.getDimension();
            if (ROR_less(lqnorm, cgnorm, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25412, _mut25413, _mut25414, _mut25415, _mut25416)) {
                if (!goodb) {
                    x.setSubVector(0, this.xL);
                } else {
                    final double step = AOR_divide(bstep, beta1, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25476, _mut25477, _mut25478, _mut25479);
                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25488, _mut25489, _mut25490, _mut25491, _mut25492); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.refineSolution_502");
                        final double bi = mb.getEntry(i);
                        final double xi = this.xL.getEntry(i);
                        x.setEntry(i, AOR_plus(xi, AOR_multiply(step, bi, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25480, _mut25481, _mut25482, _mut25483), "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25484, _mut25485, _mut25486, _mut25487));
                    }
                }
            } else {
                final double anorm = FastMath.sqrt(tnorm);
                final double diag = ROR_equals(gbar, 0., "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25417, _mut25418, _mut25419, _mut25420, _mut25421) ? AOR_multiply(anorm, MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25422, _mut25423, _mut25424, _mut25425) : gbar;
                final double zbar = AOR_divide(gammaZeta, diag, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25426, _mut25427, _mut25428, _mut25429);
                final double step = AOR_divide((AOR_plus(bstep, AOR_multiply(snprod, zbar, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25430, _mut25431, _mut25432, _mut25433), "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25434, _mut25435, _mut25436, _mut25437)), beta1, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25438, _mut25439, _mut25440, _mut25441);
                // ynorm = FastMath.sqrt(ynorm2 + zbar * zbar);
                if (!goodb) {
                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25471, _mut25472, _mut25473, _mut25474, _mut25475); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.refineSolution_502");
                        final double xi = this.xL.getEntry(i);
                        final double wi = wbar.getEntry(i);
                        x.setEntry(i, AOR_plus(xi, AOR_multiply(zbar, wi, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25463, _mut25464, _mut25465, _mut25466), "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25467, _mut25468, _mut25469, _mut25470));
                    }
                } else {
                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25458, _mut25459, _mut25460, _mut25461, _mut25462); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.refineSolution_502");
                        final double xi = this.xL.getEntry(i);
                        final double wi = wbar.getEntry(i);
                        final double bi = mb.getEntry(i);
                        x.setEntry(i, AOR_plus(AOR_plus(xi, AOR_multiply(zbar, wi, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25442, _mut25443, _mut25444, _mut25445), "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25446, _mut25447, _mut25448, _mut25449), AOR_multiply(step, bi, "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25450, _mut25451, _mut25452, _mut25453), "org.apache.commons.math3.linear.SymmLQ.refineSolution_502", _mut25454, _mut25455, _mut25456, _mut25457));
                    }
                }
            }
        }

        /**
         * Performs the initial phase of the SYMMLQ algorithm. On return, the
         * value of the state variables of {@code this} object correspond to k =
         * 1.
         */
        void init() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.init_543");
            this.xL.set(0.);
            /*
             * Set up y for the first Lanczos vector. y and beta1 will be zero
             * if b = 0.
             */
            this.r1 = this.b.copy();
            this.y = this.m == null ? this.b.copy() : this.m.operate(this.r1);
            if ((_mut25493 ? ((this.m != null) || this.check) : ((this.m != null) && this.check))) {
                checkSymmetry(this.m, this.r1, this.y, this.m.operate(this.y));
            }
            this.beta1 = this.r1.dotProduct(this.y);
            if (ROR_less(this.beta1, 0., "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25494, _mut25495, _mut25496, _mut25497, _mut25498)) {
                throwNPDLOException(this.m, this.y);
            }
            if (ROR_equals(this.beta1, 0., "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25499, _mut25500, _mut25501, _mut25502, _mut25503)) {
                /* If b = 0 exactly, stop with x = 0. */
                this.bIsNull = true;
                return;
            }
            this.bIsNull = false;
            this.beta1 = FastMath.sqrt(this.beta1);
            /* At this point
             *   r1 = b,
             *   y = M * b,
             *   beta1 = beta[1].
             */
            final RealVector v = this.y.mapMultiply(AOR_divide(1., this.beta1, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25504, _mut25505, _mut25506, _mut25507));
            this.y = this.a.operate(v);
            if (this.check) {
                checkSymmetry(this.a, v, this.y, this.a.operate(this.y));
            }
            /*
             * Set up y for the second Lanczos vector. y and beta will be zero
             * or very small if b is an eigenvector.
             */
            daxpy(-this.shift, v, this.y);
            final double alpha = v.dotProduct(this.y);
            daxpy(AOR_divide(-alpha, this.beta1, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25508, _mut25509, _mut25510, _mut25511), this.r1, this.y);
            /* Make sure r2 will be orthogonal to the first v. */
            final double vty = v.dotProduct(this.y);
            final double vtv = v.dotProduct(v);
            daxpy(AOR_divide(-vty, vtv, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25512, _mut25513, _mut25514, _mut25515), v, this.y);
            this.r2 = this.y.copy();
            if (this.m != null) {
                this.y = this.m.operate(this.r2);
            }
            this.oldb = this.beta1;
            this.beta = this.r2.dotProduct(this.y);
            if (ROR_less(this.beta, 0., "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25516, _mut25517, _mut25518, _mut25519, _mut25520)) {
                throwNPDLOException(this.m, this.y);
            }
            this.beta = FastMath.sqrt(this.beta);
            /*
             * At this point
             *   oldb = beta[1]
             *   beta = beta[2]
             *   y  = beta[2] * P' * v[2]
             *   r2 = beta[2] * M^(-1) * P' * v[2]
             */
            this.cgnorm = this.beta1;
            this.gbar = alpha;
            this.dbar = this.beta;
            this.gammaZeta = this.beta1;
            this.minusEpsZeta = 0.;
            this.bstep = 0.;
            this.snprod = 1.;
            this.tnorm = AOR_plus(AOR_multiply(alpha, alpha, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25521, _mut25522, _mut25523, _mut25524), AOR_multiply(this.beta, this.beta, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25525, _mut25526, _mut25527, _mut25528), "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25529, _mut25530, _mut25531, _mut25532);
            this.ynorm2 = 0.;
            this.gmax = AOR_plus(FastMath.abs(alpha), MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.init_543", _mut25533, _mut25534, _mut25535, _mut25536);
            this.gmin = this.gmax;
            if (this.goodb) {
                this.wbar = new ArrayRealVector(this.a.getRowDimension());
                this.wbar.set(0.);
            } else {
                this.wbar = v;
            }
            updateNorms();
        }

        /**
         * Performs the next iteration of the algorithm. The iteration count
         * should be incremented prior to calling this method. On return, the
         * value of the state variables of {@code this} object correspond to the
         * current iteration count {@code k}.
         */
        void update() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.update_636");
            final RealVector v = y.mapMultiply(AOR_divide(1., beta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25537, _mut25538, _mut25539, _mut25540));
            y = a.operate(v);
            daxpbypz(-shift, v, AOR_divide(-beta, oldb, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25541, _mut25542, _mut25543, _mut25544), r1, y);
            final double alpha = v.dotProduct(y);
            /*
             * At this point
             *   v     = P' * v[k],
             *   y     = (A - shift * I) * P' * v[k] - beta[k] * M^(-1) * P' * v[k-1],
             *   alpha = v'[k] * P * (A - shift * I) * P' * v[k]
             *           - beta[k] * v[k]' * P * M^(-1) * P' * v[k-1]
             *         = v'[k] * P * (A - shift * I) * P' * v[k]
             *           - beta[k] * v[k]' * v[k-1]
             *         = alpha[k].
             */
            daxpy(AOR_divide(-alpha, beta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25545, _mut25546, _mut25547, _mut25548), r2, y);
            /*
             * At this point
             *   y = (A - shift * I) * P' * v[k] - alpha[k] * M^(-1) * P' * v[k]
             *       - beta[k] * M^(-1) * P' * v[k-1]
             *     = M^(-1) * P' * (P * (A - shift * I) * P' * v[k] -alpha[k] * v[k]
             *       - beta[k] * v[k-1])
             *     = beta[k+1] * M^(-1) * P' * v[k+1],
             * from Paige and Saunders (1975), equation (3.2).
             *
             * WATCH-IT: the two following lines work only because y is no longer
             * updated up to the end of the present iteration, and is
             * reinitialized at the beginning of the next iteration.
             */
            r1 = r2;
            r2 = y;
            if (m != null) {
                y = m.operate(r2);
            }
            oldb = beta;
            beta = r2.dotProduct(y);
            if (ROR_less(beta, 0., "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25549, _mut25550, _mut25551, _mut25552, _mut25553)) {
                throwNPDLOException(m, y);
            }
            beta = FastMath.sqrt(beta);
            /*
             * At this point
             *   r1 = beta[k] * M^(-1) * P' * v[k],
             *   r2 = beta[k+1] * M^(-1) * P' * v[k+1],
             *   y  = beta[k+1] * P' * v[k+1],
             *   oldb = beta[k],
             *   beta = beta[k+1].
             */
            tnorm += AOR_plus(AOR_plus(AOR_multiply(alpha, alpha, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25554, _mut25555, _mut25556, _mut25557), AOR_multiply(oldb, oldb, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25558, _mut25559, _mut25560, _mut25561), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25562, _mut25563, _mut25564, _mut25565), AOR_multiply(beta, beta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25566, _mut25567, _mut25568, _mut25569), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25570, _mut25571, _mut25572, _mut25573);
            /*
             * Compute the next plane rotation for Q. See Paige and Saunders
             * (1975), equation (5.6), with
             *   gamma = gamma[k-1],
             *   c     = c[k-1],
             *   s     = s[k-1].
             */
            final double gamma = FastMath.sqrt(AOR_plus(AOR_multiply(gbar, gbar, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25574, _mut25575, _mut25576, _mut25577), AOR_multiply(oldb, oldb, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25578, _mut25579, _mut25580, _mut25581), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25582, _mut25583, _mut25584, _mut25585));
            final double c = AOR_divide(gbar, gamma, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25586, _mut25587, _mut25588, _mut25589);
            final double s = AOR_divide(oldb, gamma, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25590, _mut25591, _mut25592, _mut25593);
            /*
             * The relations
             *   gbar[k] = s[k-1] * (-c[k-2] * beta[k]) - c[k-1] * alpha[k]
             *           = s[k-1] * dbar[k] - c[k-1] * alpha[k],
             *   delta[k] = c[k-1] * dbar[k] + s[k-1] * alpha[k],
             * are not stated in Paige and Saunders (1975), but can be retrieved
             * by expanding the (k, k-1) and (k, k) coefficients of the matrix in
             * equation (5.5).
             */
            final double deltak = AOR_plus(AOR_multiply(c, dbar, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25594, _mut25595, _mut25596, _mut25597), AOR_multiply(s, alpha, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25598, _mut25599, _mut25600, _mut25601), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25602, _mut25603, _mut25604, _mut25605);
            gbar = AOR_minus(AOR_multiply(s, dbar, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25606, _mut25607, _mut25608, _mut25609), AOR_multiply(c, alpha, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25610, _mut25611, _mut25612, _mut25613), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25614, _mut25615, _mut25616, _mut25617);
            final double eps = AOR_multiply(s, beta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25618, _mut25619, _mut25620, _mut25621);
            dbar = AOR_multiply(-c, beta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25622, _mut25623, _mut25624, _mut25625);
            final double zeta = AOR_divide(gammaZeta, gamma, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25626, _mut25627, _mut25628, _mut25629);
            /*
             * At this point
             *   gbar   = gbar[k]
             *   deltak = delta[k]
             *   eps    = eps[k+1]
             *   dbar   = dbar[k+1]
             *   zeta   = zeta[k-1]
             */
            final double zetaC = AOR_multiply(zeta, c, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25630, _mut25631, _mut25632, _mut25633);
            final double zetaS = AOR_multiply(zeta, s, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25634, _mut25635, _mut25636, _mut25637);
            final int n = xL.getDimension();
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25666, _mut25667, _mut25668, _mut25669, _mut25670); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.update_636");
                final double xi = xL.getEntry(i);
                final double vi = v.getEntry(i);
                final double wi = wbar.getEntry(i);
                xL.setEntry(i, AOR_plus(AOR_plus(xi, AOR_multiply(wi, zetaC, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25638, _mut25639, _mut25640, _mut25641), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25642, _mut25643, _mut25644, _mut25645), AOR_multiply(vi, zetaS, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25646, _mut25647, _mut25648, _mut25649), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25650, _mut25651, _mut25652, _mut25653));
                wbar.setEntry(i, AOR_minus(AOR_multiply(wi, s, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25654, _mut25655, _mut25656, _mut25657), AOR_multiply(vi, c, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25658, _mut25659, _mut25660, _mut25661), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25662, _mut25663, _mut25664, _mut25665));
            }
            /*
             * At this point
             *   x = xL[k-1],
             *   ptwbar = P' wbar[k],
             * see Paige and Saunders (1975), equations (5.9) and (5.10).
             */
            bstep += AOR_multiply(AOR_multiply(snprod, c, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25671, _mut25672, _mut25673, _mut25674), zeta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25675, _mut25676, _mut25677, _mut25678);
            snprod *= s;
            gmax = FastMath.max(gmax, gamma);
            gmin = FastMath.min(gmin, gamma);
            ynorm2 += AOR_multiply(zeta, zeta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25679, _mut25680, _mut25681, _mut25682);
            gammaZeta = AOR_minus(minusEpsZeta, AOR_multiply(deltak, zeta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25683, _mut25684, _mut25685, _mut25686), "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25687, _mut25688, _mut25689, _mut25690);
            minusEpsZeta = AOR_multiply(-eps, zeta, "org.apache.commons.math3.linear.SymmLQ.update_636", _mut25691, _mut25692, _mut25693, _mut25694);
            /*
             * At this point
             *   snprod       = s[1] * ... * s[k-1],
             *   gmax         = max(|alpha[1]|, gamma[1], ..., gamma[k-1]),
             *   gmin         = min(|alpha[1]|, gamma[1], ..., gamma[k-1]),
             *   ynorm2       = zeta[1]^2 + ... + zeta[k-1]^2,
             *   gammaZeta    = gamma[k] * zeta[k],
             *   minusEpsZeta = -eps[k+1] * zeta[k-1].
             * The relation for gammaZeta can be retrieved from Paige and
             * Saunders (1975), equation (5.4a), last line of the vector
             * gbar[k] * zbar[k] = -eps[k] * zeta[k-2] - delta[k] * zeta[k-1].
             */
            updateNorms();
        }

        /**
         * Computes the norms of the residuals, and checks for convergence.
         * Updates {@link #lqnorm} and {@link #cgnorm}.
         */
        private void updateNorms() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.updateNorms_759");
            final double anorm = FastMath.sqrt(tnorm);
            final double ynorm = FastMath.sqrt(ynorm2);
            final double epsa = AOR_multiply(anorm, MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25695, _mut25696, _mut25697, _mut25698);
            final double epsx = AOR_multiply(AOR_multiply(anorm, ynorm, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25699, _mut25700, _mut25701, _mut25702), MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25703, _mut25704, _mut25705, _mut25706);
            final double epsr = AOR_multiply(AOR_multiply(anorm, ynorm, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25707, _mut25708, _mut25709, _mut25710), delta, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25711, _mut25712, _mut25713, _mut25714);
            final double diag = ROR_equals(gbar, 0., "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25715, _mut25716, _mut25717, _mut25718, _mut25719) ? epsa : gbar;
            lqnorm = FastMath.sqrt(AOR_plus(AOR_multiply(gammaZeta, gammaZeta, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25720, _mut25721, _mut25722, _mut25723), AOR_multiply(minusEpsZeta, minusEpsZeta, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25724, _mut25725, _mut25726, _mut25727), "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25728, _mut25729, _mut25730, _mut25731));
            final double qrnorm = AOR_multiply(snprod, beta1, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25732, _mut25733, _mut25734, _mut25735);
            cgnorm = AOR_divide(AOR_multiply(qrnorm, beta, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25736, _mut25737, _mut25738, _mut25739), FastMath.abs(diag), "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25740, _mut25741, _mut25742, _mut25743);
            /*
             * Estimate cond(A). In this version we look at the diagonals of L
             * in the factorization of the tridiagonal matrix, T = L * Q.
             * Sometimes, T[k] can be misleadingly ill-conditioned when T[k+1]
             * is not, so we must be careful not to overestimate acond.
             */
            final double acond;
            if (ROR_less_equals(lqnorm, cgnorm, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25744, _mut25745, _mut25746, _mut25747, _mut25748)) {
                acond = AOR_divide(gmax, gmin, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25753, _mut25754, _mut25755, _mut25756);
            } else {
                acond = AOR_divide(gmax, FastMath.min(gmin, FastMath.abs(diag)), "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25749, _mut25750, _mut25751, _mut25752);
            }
            if (ROR_greater_equals(AOR_multiply(acond, MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25757, _mut25758, _mut25759, _mut25760), 0.1, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25761, _mut25762, _mut25763, _mut25764, _mut25765)) {
                throw new IllConditionedOperatorException(acond);
            }
            if (ROR_less_equals(beta1, epsx, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25766, _mut25767, _mut25768, _mut25769, _mut25770)) {
                /*
                 * x has converged to an eigenvector of A corresponding to the
                 * eigenvalue shift.
                 */
                throw new SingularOperatorException();
            }
            rnorm = FastMath.min(cgnorm, lqnorm);
            hasConverged = (_mut25781 ? ((ROR_less_equals(cgnorm, epsx, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25771, _mut25772, _mut25773, _mut25774, _mut25775)) && (ROR_less_equals(cgnorm, epsr, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25776, _mut25777, _mut25778, _mut25779, _mut25780))) : ((ROR_less_equals(cgnorm, epsx, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25771, _mut25772, _mut25773, _mut25774, _mut25775)) || (ROR_less_equals(cgnorm, epsr, "org.apache.commons.math3.linear.SymmLQ.updateNorms_759", _mut25776, _mut25777, _mut25778, _mut25779, _mut25780))));
        }

        /**
         * Returns {@code true} if the default stopping criterion is fulfilled.
         *
         * @return {@code true} if convergence of the iterations has occurred
         */
        boolean hasConverged() {
            return hasConverged;
        }

        /**
         * Returns {@code true} if the right-hand side vector is zero exactly.
         *
         * @return the boolean value of {@code b == 0}
         */
        boolean bEqualsNullVector() {
            return bIsNull;
        }

        /**
         * Returns {@code true} if {@code beta} is essentially zero. This method
         * is used to check for early stop of the iterations.
         *
         * @return {@code true} if {@code beta < }{@link #MACH_PREC}
         */
        boolean betaEqualsZero() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.betaEqualsZero_821");
            return ROR_less(beta, MACH_PREC, "org.apache.commons.math3.linear.SymmLQ.betaEqualsZero_821", _mut25782, _mut25783, _mut25784, _mut25785, _mut25786);
        }

        /**
         * Returns the norm of the updated, preconditioned residual.
         *
         * @return the norm of the residual, ||P * r||
         */
        double getNormOfResidual() {
            return rnorm;
        }
    }

    /**
     * Key for the exception context.
     */
    private static final String OPERATOR = "operator";

    /**
     * Key for the exception context.
     */
    private static final String THRESHOLD = "threshold";

    /**
     * Key for the exception context.
     */
    private static final String VECTOR = "vector";

    /**
     * Key for the exception context.
     */
    private static final String VECTOR1 = "vector1";

    /**
     * Key for the exception context.
     */
    private static final String VECTOR2 = "vector2";

    /**
     * {@code true} if symmetry of matrix and conditioner must be checked.
     */
    private final boolean check;

    /**
     * The value of the custom tolerance &delta; for the default stopping
     * criterion.
     */
    private final double delta;

    /**
     * Creates a new instance of this class, with <a href="#stopcrit">default
     * stopping criterion</a>. Note that setting {@code check} to {@code true}
     * entails an extra matrix-vector product in the initial phase.
     *
     * @param maxIterations the maximum number of iterations
     * @param delta the &delta; parameter for the default stopping criterion
     * @param check {@code true} if self-adjointedness of both matrix and
     * preconditioner should be checked
     */
    public SymmLQ(final int maxIterations, final double delta, final boolean check) {
        super(maxIterations);
        this.delta = delta;
        this.check = check;
    }

    /**
     * Creates a new instance of this class, with <a href="#stopcrit">default
     * stopping criterion</a> and custom iteration manager. Note that setting
     * {@code check} to {@code true} entails an extra matrix-vector product in
     * the initial phase.
     *
     * @param manager the custom iteration manager
     * @param delta the &delta; parameter for the default stopping criterion
     * @param check {@code true} if self-adjointedness of both matrix and
     * preconditioner should be checked
     */
    public SymmLQ(final IterationManager manager, final double delta, final boolean check) {
        super(manager);
        this.delta = delta;
        this.check = check;
    }

    /**
     * Returns {@code true} if symmetry of the matrix, and symmetry as well as
     * positive definiteness of the preconditioner should be checked.
     *
     * @return {@code true} if the tests are to be performed
     */
    public final boolean getCheck() {
        return check;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} or {@code m} is not self-adjoint
     * @throws NonPositiveDefiniteOperatorException if {@code m} is not
     * positive definite
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solve(final RealLinearOperator a, final RealLinearOperator m, final RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(a);
        final RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, m, b, x, false, 0.);
    }

    /**
     * Returns an estimate of the solution to the linear system (A - shift
     * &middot; I) &middot; x = b.
     * <p>
     * If the solution x is expected to contain a large multiple of {@code b}
     * (as in Rayleigh-quotient iteration), then better precision may be
     * achieved with {@code goodb} set to {@code true}; this however requires an
     * extra call to the preconditioner.
     * </p>
     * <p>
     * {@code shift} should be zero if the system A &middot; x = b is to be
     * solved. Otherwise, it could be an approximation to an eigenvalue of A,
     * such as the Rayleigh quotient b<sup>T</sup> &middot; A &middot; b /
     * (b<sup>T</sup> &middot; b) corresponding to the vector b. If b is
     * sufficiently like an eigenvector corresponding to an eigenvalue near
     * shift, then the computed x may have very large components. When
     * normalized, x may be closer to an eigenvector than b.
     * </p>
     *
     * @param a the linear operator A of the system
     * @param m the preconditioner, M (can be {@code null})
     * @param b the right-hand side vector
     * @param goodb usually {@code false}, except if {@code x} is expected to
     * contain a large multiple of {@code b}
     * @param shift the amount to be subtracted to all diagonal elements of A
     * @return a reference to {@code x} (shallow copy)
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} or {@code m} is not square
     * @throws DimensionMismatchException if {@code m} or {@code b} have dimensions
     * inconsistent with {@code a}
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} or {@code m} is not self-adjoint
     * @throws NonPositiveDefiniteOperatorException if {@code m} is not
     * positive definite
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    public RealVector solve(final RealLinearOperator a, final RealLinearOperator m, final RealVector b, final boolean goodb, final double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(a);
        final RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, m, b, x, goodb, shift);
    }

    /**
     * {@inheritDoc}
     *
     * @param x not meaningful in this implementation; should not be considered
     * as an initial guess (<a href="#initguess">more</a>)
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} or {@code m} is not self-adjoint
     * @throws NonPositiveDefiniteOperatorException if {@code m} is not positive
     * definite
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solve(final RealLinearOperator a, final RealLinearOperator m, final RealVector b, final RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(x);
        return solveInPlace(a, m, b, x.copy(), false, 0.);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} is not self-adjoint
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solve(final RealLinearOperator a, final RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        final RealVector x = new ArrayRealVector(a.getColumnDimension());
        x.set(0.);
        return solveInPlace(a, null, b, x, false, 0.);
    }

    /**
     * Returns the solution to the system (A - shift &middot; I) &middot; x = b.
     * <p>
     * If the solution x is expected to contain a large multiple of {@code b}
     * (as in Rayleigh-quotient iteration), then better precision may be
     * achieved with {@code goodb} set to {@code true}.
     * </p>
     * <p>
     * {@code shift} should be zero if the system A &middot; x = b is to be
     * solved. Otherwise, it could be an approximation to an eigenvalue of A,
     * such as the Rayleigh quotient b<sup>T</sup> &middot; A &middot; b /
     * (b<sup>T</sup> &middot; b) corresponding to the vector b. If b is
     * sufficiently like an eigenvector corresponding to an eigenvalue near
     * shift, then the computed x may have very large components. When
     * normalized, x may be closer to an eigenvector than b.
     * </p>
     *
     * @param a the linear operator A of the system
     * @param b the right-hand side vector
     * @param goodb usually {@code false}, except if {@code x} is expected to
     * contain a large multiple of {@code b}
     * @param shift the amount to be subtracted to all diagonal elements of A
     * @return a reference to {@code x}
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} is not square
     * @throws DimensionMismatchException if {@code b} has dimensions
     * inconsistent with {@code a}
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} is not self-adjoint
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    public RealVector solve(final RealLinearOperator a, final RealVector b, final boolean goodb, final double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        final RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, null, b, x, goodb, shift);
    }

    /**
     * {@inheritDoc}
     *
     * @param x not meaningful in this implementation; should not be considered
     * as an initial guess (<a href="#initguess">more</a>)
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} is not self-adjoint
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solve(final RealLinearOperator a, final RealVector b, final RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(x);
        return solveInPlace(a, null, b, x.copy(), false, 0.);
    }

    /**
     * {@inheritDoc}
     *
     * @param x the vector to be updated with the solution; {@code x} should
     * not be considered as an initial guess (<a href="#initguess">more</a>)
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} or {@code m} is not self-adjoint
     * @throws NonPositiveDefiniteOperatorException if {@code m} is not
     * positive definite
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solveInPlace(final RealLinearOperator a, final RealLinearOperator m, final RealVector b, final RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(a, m, b, x, false, 0.);
    }

    /**
     * Returns an estimate of the solution to the linear system (A - shift
     * &middot; I) &middot; x = b. The solution is computed in-place.
     * <p>
     * If the solution x is expected to contain a large multiple of {@code b}
     * (as in Rayleigh-quotient iteration), then better precision may be
     * achieved with {@code goodb} set to {@code true}; this however requires an
     * extra call to the preconditioner.
     * </p>
     * <p>
     * {@code shift} should be zero if the system A &middot; x = b is to be
     * solved. Otherwise, it could be an approximation to an eigenvalue of A,
     * such as the Rayleigh quotient b<sup>T</sup> &middot; A &middot; b /
     * (b<sup>T</sup> &middot; b) corresponding to the vector b. If b is
     * sufficiently like an eigenvector corresponding to an eigenvalue near
     * shift, then the computed x may have very large components. When
     * normalized, x may be closer to an eigenvector than b.
     * </p>
     *
     * @param a the linear operator A of the system
     * @param m the preconditioner, M (can be {@code null})
     * @param b the right-hand side vector
     * @param x the vector to be updated with the solution; {@code x} should
     * not be considered as an initial guess (<a href="#initguess">more</a>)
     * @param goodb usually {@code false}, except if {@code x} is expected to
     * contain a large multiple of {@code b}
     * @param shift the amount to be subtracted to all diagonal elements of A
     * @return a reference to {@code x} (shallow copy).
     * @throws NullArgumentException if one of the parameters is {@code null}
     * @throws NonSquareOperatorException if {@code a} or {@code m} is not square
     * @throws DimensionMismatchException if {@code m}, {@code b} or {@code x}
     * have dimensions inconsistent with {@code a}.
     * @throws MaxCountExceededException at exhaustion of the iteration count,
     * unless a custom
     * {@link org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback callback}
     * has been set at construction of the {@link IterationManager}
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} or {@code m} is not self-adjoint
     * @throws NonPositiveDefiniteOperatorException if {@code m} is not positive
     * definite
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    public RealVector solveInPlace(final RealLinearOperator a, final RealLinearOperator m, final RealVector b, final RealVector x, final boolean goodb, final double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.solveInPlace_1143");
        checkParameters(a, m, b, x);
        final IterationManager manager = getIterationManager();
        /* Initialization counts as an iteration. */
        manager.resetIterationCount();
        manager.incrementIterationCount();
        final State state;
        state = new State(a, m, b, goodb, shift, delta, check);
        state.init();
        state.refineSolution(x);
        IterativeLinearSolverEvent event;
        event = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), x, b, state.getNormOfResidual());
        if (state.bEqualsNullVector()) {
            /* If b = 0 exactly, stop with x = 0. */
            manager.fireTerminationEvent(event);
            return x;
        }
        /* Cause termination if beta is essentially zero. */
        final boolean earlyStop;
        earlyStop = (_mut25787 ? (state.betaEqualsZero() && state.hasConverged()) : (state.betaEqualsZero() || state.hasConverged()));
        manager.fireInitializationEvent(event);
        if (!earlyStop) {
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SymmLQ.solveInPlace_1143");
                manager.incrementIterationCount();
                event = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), x, b, state.getNormOfResidual());
                manager.fireIterationStartedEvent(event);
                state.update();
                state.refineSolution(x);
                event = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), x, b, state.getNormOfResidual());
                manager.fireIterationPerformedEvent(event);
            } while (!state.hasConverged());
        }
        event = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), x, b, state.getNormOfResidual());
        manager.fireTerminationEvent(event);
        return x;
    }

    /**
     * {@inheritDoc}
     *
     * @param x the vector to be updated with the solution; {@code x} should
     * not be considered as an initial guess (<a href="#initguess">more</a>)
     * @throws NonSelfAdjointOperatorException if {@link #getCheck()} is
     * {@code true}, and {@code a} is not self-adjoint
     * @throws IllConditionedOperatorException if {@code a} is ill-conditioned
     */
    @Override
    public RealVector solveInPlace(final RealLinearOperator a, final RealVector b, final RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(a, null, b, x, false, 0.);
    }
}
