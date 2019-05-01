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
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Comparator;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.analysis.MultivariateFunction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the Nelder-Mead simplex algorithm.
 *
 * @since 3.0
 */
public class NelderMeadSimplex extends AbstractSimplex {

    @Conditional
    public static boolean _mut61401 = false, _mut61402 = false, _mut61403 = false, _mut61404 = false, _mut61405 = false, _mut61406 = false, _mut61407 = false, _mut61408 = false, _mut61409 = false, _mut61410 = false, _mut61411 = false, _mut61412 = false, _mut61413 = false, _mut61414 = false, _mut61415 = false, _mut61416 = false, _mut61417 = false, _mut61418 = false, _mut61419 = false, _mut61420 = false, _mut61421 = false, _mut61422 = false, _mut61423 = false, _mut61424 = false, _mut61425 = false, _mut61426 = false, _mut61427 = false, _mut61428 = false, _mut61429 = false, _mut61430 = false, _mut61431 = false, _mut61432 = false, _mut61433 = false, _mut61434 = false, _mut61435 = false, _mut61436 = false, _mut61437 = false, _mut61438 = false, _mut61439 = false, _mut61440 = false, _mut61441 = false, _mut61442 = false, _mut61443 = false, _mut61444 = false, _mut61445 = false, _mut61446 = false, _mut61447 = false, _mut61448 = false, _mut61449 = false, _mut61450 = false, _mut61451 = false, _mut61452 = false, _mut61453 = false, _mut61454 = false, _mut61455 = false, _mut61456 = false, _mut61457 = false, _mut61458 = false, _mut61459 = false, _mut61460 = false, _mut61461 = false, _mut61462 = false, _mut61463 = false, _mut61464 = false, _mut61465 = false, _mut61466 = false, _mut61467 = false, _mut61468 = false, _mut61469 = false, _mut61470 = false, _mut61471 = false, _mut61472 = false, _mut61473 = false, _mut61474 = false, _mut61475 = false, _mut61476 = false, _mut61477 = false, _mut61478 = false, _mut61479 = false, _mut61480 = false, _mut61481 = false, _mut61482 = false, _mut61483 = false, _mut61484 = false, _mut61485 = false, _mut61486 = false, _mut61487 = false, _mut61488 = false, _mut61489 = false, _mut61490 = false, _mut61491 = false, _mut61492 = false, _mut61493 = false, _mut61494 = false, _mut61495 = false, _mut61496 = false, _mut61497 = false, _mut61498 = false, _mut61499 = false, _mut61500 = false, _mut61501 = false, _mut61502 = false, _mut61503 = false, _mut61504 = false, _mut61505 = false, _mut61506 = false, _mut61507 = false, _mut61508 = false, _mut61509 = false, _mut61510 = false, _mut61511 = false, _mut61512 = false, _mut61513 = false, _mut61514 = false, _mut61515 = false, _mut61516 = false, _mut61517 = false, _mut61518 = false, _mut61519 = false, _mut61520 = false, _mut61521 = false, _mut61522 = false, _mut61523 = false, _mut61524 = false, _mut61525 = false, _mut61526 = false, _mut61527 = false, _mut61528 = false, _mut61529 = false, _mut61530 = false, _mut61531 = false, _mut61532 = false, _mut61533 = false, _mut61534 = false, _mut61535 = false, _mut61536 = false, _mut61537 = false, _mut61538 = false, _mut61539 = false, _mut61540 = false, _mut61541 = false, _mut61542 = false, _mut61543 = false, _mut61544 = false, _mut61545 = false, _mut61546 = false, _mut61547 = false, _mut61548 = false, _mut61549 = false;

    /**
     * Default value for {@link #rho}: {@value}.
     */
    private static final double DEFAULT_RHO = 1;

    /**
     * Default value for {@link #khi}: {@value}.
     */
    private static final double DEFAULT_KHI = 2;

    /**
     * Default value for {@link #gamma}: {@value}.
     */
    private static final double DEFAULT_GAMMA = 0.5;

    /**
     * Default value for {@link #sigma}: {@value}.
     */
    private static final double DEFAULT_SIGMA = 0.5;

    /**
     * Reflection coefficient.
     */
    private final double rho;

    /**
     * Expansion coefficient.
     */
    private final double khi;

    /**
     * Contraction coefficient.
     */
    private final double gamma;

    /**
     * Shrinkage coefficient.
     */
    private final double sigma;

    /**
     * Build a Nelder-Mead simplex with default coefficients.
     * The default coefficients are 1.0 for rho, 2.0 for khi and 0.5
     * for both gamma and sigma.
     *
     * @param n Dimension of the simplex.
     */
    public NelderMeadSimplex(final int n) {
        this(n, 1d);
    }

    /**
     * Build a Nelder-Mead simplex with default coefficients.
     * The default coefficients are 1.0 for rho, 2.0 for khi and 0.5
     * for both gamma and sigma.
     *
     * @param n Dimension of the simplex.
     * @param sideLength Length of the sides of the default (hypercube)
     * simplex. See {@link AbstractSimplex#AbstractSimplex(int,double)}.
     */
    public NelderMeadSimplex(final int n, double sideLength) {
        this(n, sideLength, DEFAULT_RHO, DEFAULT_KHI, DEFAULT_GAMMA, DEFAULT_SIGMA);
    }

    /**
     * Build a Nelder-Mead simplex with specified coefficients.
     *
     * @param n Dimension of the simplex. See
     * {@link AbstractSimplex#AbstractSimplex(int,double)}.
     * @param sideLength Length of the sides of the default (hypercube)
     * simplex. See {@link AbstractSimplex#AbstractSimplex(int,double)}.
     * @param rho Reflection coefficient.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     * @param sigma Shrinkage coefficient.
     */
    public NelderMeadSimplex(final int n, double sideLength, final double rho, final double khi, final double gamma, final double sigma) {
        super(n, sideLength);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    /**
     * Build a Nelder-Mead simplex with specified coefficients.
     *
     * @param n Dimension of the simplex. See
     * {@link AbstractSimplex#AbstractSimplex(int)}.
     * @param rho Reflection coefficient.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     * @param sigma Shrinkage coefficient.
     */
    public NelderMeadSimplex(final int n, final double rho, final double khi, final double gamma, final double sigma) {
        this(n, 1d, rho, khi, gamma, sigma);
    }

    /**
     * Build a Nelder-Mead simplex with default coefficients.
     * The default coefficients are 1.0 for rho, 2.0 for khi and 0.5
     * for both gamma and sigma.
     *
     * @param steps Steps along the canonical axes representing box edges.
     * They may be negative but not zero. See
     */
    public NelderMeadSimplex(final double[] steps) {
        this(steps, DEFAULT_RHO, DEFAULT_KHI, DEFAULT_GAMMA, DEFAULT_SIGMA);
    }

    /**
     * Build a Nelder-Mead simplex with specified coefficients.
     *
     * @param steps Steps along the canonical axes representing box edges.
     * They may be negative but not zero. See
     * {@link AbstractSimplex#AbstractSimplex(double[])}.
     * @param rho Reflection coefficient.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     * @param sigma Shrinkage coefficient.
     * @throws IllegalArgumentException if one of the steps is zero.
     */
    public NelderMeadSimplex(final double[] steps, final double rho, final double khi, final double gamma, final double sigma) {
        super(steps);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    /**
     * Build a Nelder-Mead simplex with default coefficients.
     * The default coefficients are 1.0 for rho, 2.0 for khi and 0.5
     * for both gamma and sigma.
     *
     * @param referenceSimplex Reference simplex. See
     * {@link AbstractSimplex#AbstractSimplex(double[][])}.
     */
    public NelderMeadSimplex(final double[][] referenceSimplex) {
        this(referenceSimplex, DEFAULT_RHO, DEFAULT_KHI, DEFAULT_GAMMA, DEFAULT_SIGMA);
    }

    /**
     * Build a Nelder-Mead simplex with specified coefficients.
     *
     * @param referenceSimplex Reference simplex. See
     * {@link AbstractSimplex#AbstractSimplex(double[][])}.
     * @param rho Reflection coefficient.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     * @param sigma Shrinkage coefficient.
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if the reference simplex does not contain at least one point.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if there is a dimension mismatch in the reference simplex.
     */
    public NelderMeadSimplex(final double[][] referenceSimplex, final double rho, final double khi, final double gamma, final double sigma) {
        super(referenceSimplex);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void iterate(final MultivariateFunction evaluationFunction, final Comparator<PointValuePair> comparator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
        // The simplex has n + 1 points if dimension is n.
        final int n = getDimension();
        // Interesting values.
        final PointValuePair best = getPoint(0);
        final PointValuePair secondBest = getPoint(AOR_minus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61401, _mut61402, _mut61403, _mut61404));
        final PointValuePair worst = getPoint(n);
        final double[] xWorst = worst.getPointRef();
        // point at index n).
        final double[] centroid = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61410, _mut61411, _mut61412, _mut61413, _mut61414); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
            final double[] x = getPoint(i).getPointRef();
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61405, _mut61406, _mut61407, _mut61408, _mut61409); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                centroid[j] += x[j];
            }
        }
        final double scaling = AOR_divide(1.0, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61415, _mut61416, _mut61417, _mut61418);
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61419, _mut61420, _mut61421, _mut61422, _mut61423); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
            centroid[j] *= scaling;
        }
        // compute the reflection point
        final double[] xR = new double[n];
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61436, _mut61437, _mut61438, _mut61439, _mut61440); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
            xR[j] = AOR_plus(centroid[j], AOR_multiply(rho, (AOR_minus(centroid[j], xWorst[j], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61424, _mut61425, _mut61426, _mut61427)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61428, _mut61429, _mut61430, _mut61431), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61432, _mut61433, _mut61434, _mut61435);
        }
        final PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
        if ((_mut61451 ? (ROR_less_equals(comparator.compare(best, reflected), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61441, _mut61442, _mut61443, _mut61444, _mut61445) || ROR_less(comparator.compare(reflected, secondBest), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61446, _mut61447, _mut61448, _mut61449, _mut61450)) : (ROR_less_equals(comparator.compare(best, reflected), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61441, _mut61442, _mut61443, _mut61444, _mut61445) && ROR_less(comparator.compare(reflected, secondBest), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61446, _mut61447, _mut61448, _mut61449, _mut61450)))) {
            // Accept the reflected point.
            replaceWorstPoint(reflected, comparator);
        } else if (ROR_less(comparator.compare(reflected, best), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61452, _mut61453, _mut61454, _mut61455, _mut61456)) {
            // Compute the expansion point.
            final double[] xE = new double[n];
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61540, _mut61541, _mut61542, _mut61543, _mut61544); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                xE[j] = AOR_plus(centroid[j], AOR_multiply(khi, (AOR_minus(xR[j], centroid[j], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61528, _mut61529, _mut61530, _mut61531)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61532, _mut61533, _mut61534, _mut61535), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61536, _mut61537, _mut61538, _mut61539);
            }
            final PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
            if (ROR_less(comparator.compare(expanded, reflected), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61545, _mut61546, _mut61547, _mut61548, _mut61549)) {
                // Accept the expansion point.
                replaceWorstPoint(expanded, comparator);
            } else {
                // Accept the reflected point.
                replaceWorstPoint(reflected, comparator);
            }
        } else {
            if (ROR_less(comparator.compare(reflected, worst), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61457, _mut61458, _mut61459, _mut61460, _mut61461)) {
                // Perform an outside contraction.
                final double[] xC = new double[n];
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61496, _mut61497, _mut61498, _mut61499, _mut61500); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                    xC[j] = AOR_plus(centroid[j], AOR_multiply(gamma, (AOR_minus(xR[j], centroid[j], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61484, _mut61485, _mut61486, _mut61487)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61488, _mut61489, _mut61490, _mut61491), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61492, _mut61493, _mut61494, _mut61495);
                }
                final PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (ROR_less_equals(comparator.compare(outContracted, reflected), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61501, _mut61502, _mut61503, _mut61504, _mut61505)) {
                    // Accept the contraction point.
                    replaceWorstPoint(outContracted, comparator);
                    return;
                }
            } else {
                // Perform an inside contraction.
                final double[] xC = new double[n];
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61474, _mut61475, _mut61476, _mut61477, _mut61478); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                    xC[j] = AOR_minus(centroid[j], AOR_multiply(gamma, (AOR_minus(centroid[j], xWorst[j], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61462, _mut61463, _mut61464, _mut61465)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61466, _mut61467, _mut61468, _mut61469), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61470, _mut61471, _mut61472, _mut61473);
                }
                final PointValuePair inContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (ROR_less(comparator.compare(inContracted, worst), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61479, _mut61480, _mut61481, _mut61482, _mut61483)) {
                    // Accept the contraction point.
                    replaceWorstPoint(inContracted, comparator);
                    return;
                }
            }
            // Perform a shrink.
            final double[] xSmallest = getPoint(0).getPointRef();
            for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61523, _mut61524, _mut61525, _mut61526, _mut61527); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                final double[] x = getPoint(i).getPoint();
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61518, _mut61519, _mut61520, _mut61521, _mut61522); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184");
                    x[j] = AOR_plus(xSmallest[j], AOR_multiply(sigma, (AOR_minus(x[j], xSmallest[j], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61506, _mut61507, _mut61508, _mut61509)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61510, _mut61511, _mut61512, _mut61513), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex.iterate_184", _mut61514, _mut61515, _mut61516, _mut61517);
                }
                setPoint(i, new PointValuePair(x, Double.NaN, false));
            }
            evaluate(evaluationFunction, comparator);
        }
    }
}
