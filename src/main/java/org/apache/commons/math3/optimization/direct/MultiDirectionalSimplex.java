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
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.PointValuePair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the multi-directional direct search method.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class MultiDirectionalSimplex extends AbstractSimplex {

    @Conditional
    public static boolean _mut74659 = false, _mut74660 = false, _mut74661 = false, _mut74662 = false, _mut74663 = false, _mut74664 = false, _mut74665 = false, _mut74666 = false, _mut74667 = false, _mut74668 = false, _mut74669 = false, _mut74670 = false, _mut74671 = false, _mut74672 = false, _mut74673 = false, _mut74674 = false, _mut74675 = false, _mut74676 = false, _mut74677 = false, _mut74678 = false, _mut74679 = false, _mut74680 = false, _mut74681 = false, _mut74682 = false, _mut74683 = false, _mut74684 = false, _mut74685 = false, _mut74686 = false, _mut74687 = false, _mut74688 = false, _mut74689 = false, _mut74690 = false;

    /**
     * Default value for {@link #khi}: {@value}.
     */
    private static final double DEFAULT_KHI = 2;

    /**
     * Default value for {@link #gamma}: {@value}.
     */
    private static final double DEFAULT_GAMMA = 0.5;

    /**
     * Expansion coefficient.
     */
    private final double khi;

    /**
     * Contraction coefficient.
     */
    private final double gamma;

    /**
     * Build a multi-directional simplex with default coefficients.
     * The default values are 2.0 for khi and 0.5 for gamma.
     *
     * @param n Dimension of the simplex.
     */
    public MultiDirectionalSimplex(final int n) {
        this(n, 1d);
    }

    /**
     * Build a multi-directional simplex with default coefficients.
     * The default values are 2.0 for khi and 0.5 for gamma.
     *
     * @param n Dimension of the simplex.
     * @param sideLength Length of the sides of the default (hypercube)
     * simplex. See {@link AbstractSimplex#AbstractSimplex(int,double)}.
     */
    public MultiDirectionalSimplex(final int n, double sideLength) {
        this(n, sideLength, DEFAULT_KHI, DEFAULT_GAMMA);
    }

    /**
     * Build a multi-directional simplex with specified coefficients.
     *
     * @param n Dimension of the simplex. See
     * {@link AbstractSimplex#AbstractSimplex(int,double)}.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     */
    public MultiDirectionalSimplex(final int n, final double khi, final double gamma) {
        this(n, 1d, khi, gamma);
    }

    /**
     * Build a multi-directional simplex with specified coefficients.
     *
     * @param n Dimension of the simplex. See
     * {@link AbstractSimplex#AbstractSimplex(int,double)}.
     * @param sideLength Length of the sides of the default (hypercube)
     * simplex. See {@link AbstractSimplex#AbstractSimplex(int,double)}.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     */
    public MultiDirectionalSimplex(final int n, double sideLength, final double khi, final double gamma) {
        super(n, sideLength);
        this.khi = khi;
        this.gamma = gamma;
    }

    /**
     * Build a multi-directional simplex with default coefficients.
     * The default values are 2.0 for khi and 0.5 for gamma.
     *
     * @param steps Steps along the canonical axes representing box edges.
     * They may be negative but not zero. See
     */
    public MultiDirectionalSimplex(final double[] steps) {
        this(steps, DEFAULT_KHI, DEFAULT_GAMMA);
    }

    /**
     * Build a multi-directional simplex with specified coefficients.
     *
     * @param steps Steps along the canonical axes representing box edges.
     * They may be negative but not zero. See
     * {@link AbstractSimplex#AbstractSimplex(double[])}.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     */
    public MultiDirectionalSimplex(final double[] steps, final double khi, final double gamma) {
        super(steps);
        this.khi = khi;
        this.gamma = gamma;
    }

    /**
     * Build a multi-directional simplex with default coefficients.
     * The default values are 2.0 for khi and 0.5 for gamma.
     *
     * @param referenceSimplex Reference simplex. See
     * {@link AbstractSimplex#AbstractSimplex(double[][])}.
     */
    public MultiDirectionalSimplex(final double[][] referenceSimplex) {
        this(referenceSimplex, DEFAULT_KHI, DEFAULT_GAMMA);
    }

    /**
     * Build a multi-directional simplex with specified coefficients.
     *
     * @param referenceSimplex Reference simplex. See
     * {@link AbstractSimplex#AbstractSimplex(double[][])}.
     * @param khi Expansion coefficient.
     * @param gamma Contraction coefficient.
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if the reference simplex does not contain at least one point.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if there is a dimension mismatch in the reference simplex.
     */
    public MultiDirectionalSimplex(final double[][] referenceSimplex, final double khi, final double gamma) {
        super(referenceSimplex);
        this.khi = khi;
        this.gamma = gamma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void iterate(final MultivariateFunction evaluationFunction, final Comparator<PointValuePair> comparator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.iterate_155");
        // Save the original simplex.
        final PointValuePair[] original = getPoints();
        final PointValuePair best = original[0];
        // Perform a reflection step.
        final PointValuePair reflected = evaluateNewSimplex(evaluationFunction, original, 1, comparator);
        if (ROR_less(comparator.compare(reflected, best), 0, "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.iterate_155", _mut74659, _mut74660, _mut74661, _mut74662, _mut74663)) {
            // Compute the expanded simplex.
            final PointValuePair[] reflectedSimplex = getPoints();
            final PointValuePair expanded = evaluateNewSimplex(evaluationFunction, original, khi, comparator);
            if (ROR_less_equals(comparator.compare(reflected, expanded), 0, "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.iterate_155", _mut74664, _mut74665, _mut74666, _mut74667, _mut74668)) {
                // Keep the reflected simplex.
                setPoints(reflectedSimplex);
            }
            // Keep the expanded simplex.
            return;
        }
        // Compute the contracted simplex.
        evaluateNewSimplex(evaluationFunction, original, gamma, comparator);
    }

    /**
     * Compute and evaluate a new simplex.
     *
     * @param evaluationFunction Evaluation function.
     * @param original Original simplex (to be preserved).
     * @param coeff Linear coefficient.
     * @param comparator Comparator to use to sort simplex vertices from best
     * to poorest.
     * @return the best point in the transformed simplex.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximal number of evaluations is exceeded.
     */
    private PointValuePair evaluateNewSimplex(final MultivariateFunction evaluationFunction, final PointValuePair[] original, final double coeff, final Comparator<PointValuePair> comparator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195");
        final double[] xSmallest = original[0].getPointRef();
        // except the first one.
        setPoint(0, original[0]);
        final int dim = getDimension();
        for (int i = 1; ROR_less(i, getSize(), "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195", _mut74686, _mut74687, _mut74688, _mut74689, _mut74690); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195");
            final double[] xOriginal = original[i].getPointRef();
            final double[] xTransformed = new double[dim];
            for (int j = 0; ROR_less(j, dim, "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195", _mut74681, _mut74682, _mut74683, _mut74684, _mut74685); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195");
                xTransformed[j] = AOR_plus(xSmallest[j], AOR_multiply(coeff, (AOR_minus(xSmallest[j], xOriginal[j], "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195", _mut74669, _mut74670, _mut74671, _mut74672)), "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195", _mut74673, _mut74674, _mut74675, _mut74676), "org.apache.commons.math3.optimization.direct.MultiDirectionalSimplex.evaluateNewSimplex_195", _mut74677, _mut74678, _mut74679, _mut74680);
            }
            setPoint(i, new PointValuePair(xTransformed, Double.NaN, false));
        }
        // Evaluate the simplex.
        evaluate(evaluationFunction, comparator);
        return getPoint(0);
    }
}
