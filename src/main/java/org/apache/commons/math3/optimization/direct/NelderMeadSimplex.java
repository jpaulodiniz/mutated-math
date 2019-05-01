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
package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.analysis.MultivariateFunction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the Nelder-Mead simplex algorithm.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class NelderMeadSimplex extends AbstractSimplex {

    @Conditional
    public static boolean _mut74449 = false, _mut74450 = false, _mut74451 = false, _mut74452 = false, _mut74453 = false, _mut74454 = false, _mut74455 = false, _mut74456 = false, _mut74457 = false, _mut74458 = false, _mut74459 = false, _mut74460 = false, _mut74461 = false, _mut74462 = false, _mut74463 = false, _mut74464 = false, _mut74465 = false, _mut74466 = false, _mut74467 = false, _mut74468 = false, _mut74469 = false, _mut74470 = false, _mut74471 = false, _mut74472 = false, _mut74473 = false, _mut74474 = false, _mut74475 = false, _mut74476 = false, _mut74477 = false, _mut74478 = false, _mut74479 = false, _mut74480 = false, _mut74481 = false, _mut74482 = false, _mut74483 = false, _mut74484 = false, _mut74485 = false, _mut74486 = false, _mut74487 = false, _mut74488 = false, _mut74489 = false, _mut74490 = false, _mut74491 = false, _mut74492 = false, _mut74493 = false, _mut74494 = false, _mut74495 = false, _mut74496 = false, _mut74497 = false, _mut74498 = false, _mut74499 = false, _mut74500 = false, _mut74501 = false, _mut74502 = false, _mut74503 = false, _mut74504 = false, _mut74505 = false, _mut74506 = false, _mut74507 = false, _mut74508 = false, _mut74509 = false, _mut74510 = false, _mut74511 = false, _mut74512 = false, _mut74513 = false, _mut74514 = false, _mut74515 = false, _mut74516 = false, _mut74517 = false, _mut74518 = false, _mut74519 = false, _mut74520 = false, _mut74521 = false, _mut74522 = false, _mut74523 = false, _mut74524 = false, _mut74525 = false, _mut74526 = false, _mut74527 = false, _mut74528 = false, _mut74529 = false, _mut74530 = false, _mut74531 = false, _mut74532 = false, _mut74533 = false, _mut74534 = false, _mut74535 = false, _mut74536 = false, _mut74537 = false, _mut74538 = false, _mut74539 = false, _mut74540 = false, _mut74541 = false, _mut74542 = false, _mut74543 = false, _mut74544 = false, _mut74545 = false, _mut74546 = false, _mut74547 = false, _mut74548 = false, _mut74549 = false, _mut74550 = false, _mut74551 = false, _mut74552 = false, _mut74553 = false, _mut74554 = false, _mut74555 = false, _mut74556 = false, _mut74557 = false, _mut74558 = false, _mut74559 = false, _mut74560 = false, _mut74561 = false, _mut74562 = false, _mut74563 = false, _mut74564 = false, _mut74565 = false, _mut74566 = false, _mut74567 = false, _mut74568 = false, _mut74569 = false, _mut74570 = false, _mut74571 = false, _mut74572 = false, _mut74573 = false, _mut74574 = false, _mut74575 = false, _mut74576 = false, _mut74577 = false, _mut74578 = false, _mut74579 = false, _mut74580 = false, _mut74581 = false, _mut74582 = false, _mut74583 = false, _mut74584 = false, _mut74585 = false, _mut74586 = false, _mut74587 = false, _mut74588 = false, _mut74589 = false, _mut74590 = false, _mut74591 = false, _mut74592 = false, _mut74593 = false, _mut74594 = false, _mut74595 = false, _mut74596 = false, _mut74597 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
        // The simplex has n + 1 points if dimension is n.
        final int n = getDimension();
        // Interesting values.
        final PointValuePair best = getPoint(0);
        final PointValuePair secondBest = getPoint(AOR_minus(n, 1, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74449, _mut74450, _mut74451, _mut74452));
        final PointValuePair worst = getPoint(n);
        final double[] xWorst = worst.getPointRef();
        // point at index n).
        final double[] centroid = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74458, _mut74459, _mut74460, _mut74461, _mut74462); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
            final double[] x = getPoint(i).getPointRef();
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74453, _mut74454, _mut74455, _mut74456, _mut74457); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                centroid[j] += x[j];
            }
        }
        final double scaling = AOR_divide(1.0, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74463, _mut74464, _mut74465, _mut74466);
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74467, _mut74468, _mut74469, _mut74470, _mut74471); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
            centroid[j] *= scaling;
        }
        // compute the reflection point
        final double[] xR = new double[n];
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74484, _mut74485, _mut74486, _mut74487, _mut74488); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
            xR[j] = AOR_plus(centroid[j], AOR_multiply(rho, (AOR_minus(centroid[j], xWorst[j], "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74472, _mut74473, _mut74474, _mut74475)), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74476, _mut74477, _mut74478, _mut74479), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74480, _mut74481, _mut74482, _mut74483);
        }
        final PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
        if ((_mut74499 ? (ROR_less_equals(comparator.compare(best, reflected), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74489, _mut74490, _mut74491, _mut74492, _mut74493) || ROR_less(comparator.compare(reflected, secondBest), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74494, _mut74495, _mut74496, _mut74497, _mut74498)) : (ROR_less_equals(comparator.compare(best, reflected), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74489, _mut74490, _mut74491, _mut74492, _mut74493) && ROR_less(comparator.compare(reflected, secondBest), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74494, _mut74495, _mut74496, _mut74497, _mut74498)))) {
            // Accept the reflected point.
            replaceWorstPoint(reflected, comparator);
        } else if (ROR_less(comparator.compare(reflected, best), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74500, _mut74501, _mut74502, _mut74503, _mut74504)) {
            // Compute the expansion point.
            final double[] xE = new double[n];
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74588, _mut74589, _mut74590, _mut74591, _mut74592); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                xE[j] = AOR_plus(centroid[j], AOR_multiply(khi, (AOR_minus(xR[j], centroid[j], "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74576, _mut74577, _mut74578, _mut74579)), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74580, _mut74581, _mut74582, _mut74583), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74584, _mut74585, _mut74586, _mut74587);
            }
            final PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
            if (ROR_less(comparator.compare(expanded, reflected), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74593, _mut74594, _mut74595, _mut74596, _mut74597)) {
                // Accept the expansion point.
                replaceWorstPoint(expanded, comparator);
            } else {
                // Accept the reflected point.
                replaceWorstPoint(reflected, comparator);
            }
        } else {
            if (ROR_less(comparator.compare(reflected, worst), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74505, _mut74506, _mut74507, _mut74508, _mut74509)) {
                // Perform an outside contraction.
                final double[] xC = new double[n];
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74544, _mut74545, _mut74546, _mut74547, _mut74548); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                    xC[j] = AOR_plus(centroid[j], AOR_multiply(gamma, (AOR_minus(xR[j], centroid[j], "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74532, _mut74533, _mut74534, _mut74535)), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74536, _mut74537, _mut74538, _mut74539), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74540, _mut74541, _mut74542, _mut74543);
                }
                final PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (ROR_less_equals(comparator.compare(outContracted, reflected), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74549, _mut74550, _mut74551, _mut74552, _mut74553)) {
                    // Accept the contraction point.
                    replaceWorstPoint(outContracted, comparator);
                    return;
                }
            } else {
                // Perform an inside contraction.
                final double[] xC = new double[n];
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74522, _mut74523, _mut74524, _mut74525, _mut74526); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                    xC[j] = AOR_minus(centroid[j], AOR_multiply(gamma, (AOR_minus(centroid[j], xWorst[j], "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74510, _mut74511, _mut74512, _mut74513)), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74514, _mut74515, _mut74516, _mut74517), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74518, _mut74519, _mut74520, _mut74521);
                }
                final PointValuePair inContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (ROR_less(comparator.compare(inContracted, worst), 0, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74527, _mut74528, _mut74529, _mut74530, _mut74531)) {
                    // Accept the contraction point.
                    replaceWorstPoint(inContracted, comparator);
                    return;
                }
            }
            // Perform a shrink.
            final double[] xSmallest = getPoint(0).getPointRef();
            for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74571, _mut74572, _mut74573, _mut74574, _mut74575); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                final double[] x = getPoint(i).getPoint();
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74566, _mut74567, _mut74568, _mut74569, _mut74570); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187");
                    x[j] = AOR_plus(xSmallest[j], AOR_multiply(sigma, (AOR_minus(x[j], xSmallest[j], "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74554, _mut74555, _mut74556, _mut74557)), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74558, _mut74559, _mut74560, _mut74561), "org.apache.commons.math3.optimization.direct.NelderMeadSimplex.iterate_187", _mut74562, _mut74563, _mut74564, _mut74565);
                }
                setPoint(i, new PointValuePair(x, Double.NaN, false));
            }
            evaluate(evaluationFunction, comparator);
        }
    }
}
