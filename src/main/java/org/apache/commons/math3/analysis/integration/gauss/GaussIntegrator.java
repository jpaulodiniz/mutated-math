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
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class that implements the Gaussian rule for
 * {@link #integrate(UnivariateFunction) integrating} a weighted
 * function.
 *
 * @since 3.1
 */
public class GaussIntegrator {

    @Conditional
    public static boolean _mut101661 = false, _mut101662 = false, _mut101663 = false, _mut101664 = false, _mut101665 = false, _mut101666 = false, _mut101667 = false, _mut101668 = false, _mut101669 = false, _mut101670 = false, _mut101671 = false, _mut101672 = false, _mut101673 = false, _mut101674 = false, _mut101675 = false, _mut101676 = false, _mut101677 = false, _mut101678 = false, _mut101679 = false, _mut101680 = false, _mut101681 = false, _mut101682 = false, _mut101683 = false, _mut101684 = false, _mut101685 = false, _mut101686 = false, _mut101687 = false, _mut101688 = false, _mut101689 = false, _mut101690 = false;

    /**
     * Nodes.
     */
    private final double[] points;

    /**
     * Nodes weights.
     */
    private final double[] weights;

    /**
     * Creates an integrator from the given {@code points} and {@code weights}.
     * The integration interval is defined by the first and last value of
     * {@code points} which must be sorted in increasing order.
     *
     * @param points Integration points.
     * @param weights Weights of the corresponding integration nodes.
     * @throws NonMonotonicSequenceException if the {@code points} are not
     * sorted in increasing order.
     * @throws DimensionMismatchException if points and weights don't have the same length
     */
    public GaussIntegrator(double[] points, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.GaussIntegrator_49");
        if (ROR_not_equals(points.length, weights.length, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.GaussIntegrator_49", _mut101661, _mut101662, _mut101663, _mut101664, _mut101665)) {
            throw new DimensionMismatchException(points.length, weights.length);
        }
        MathArrays.checkOrder(points, MathArrays.OrderDirection.INCREASING, true, true);
        this.points = points.clone();
        this.weights = weights.clone();
    }

    /**
     * Creates an integrator from the given pair of points (first element of
     * the pair) and weights (second element of the pair.
     *
     * @param pointsAndWeights Integration points and corresponding weights.
     * @throws NonMonotonicSequenceException if the {@code points} are not
     * sorted in increasing order.
     *
     * @see #GaussIntegrator(double[], double[])
     */
    public GaussIntegrator(Pair<double[], double[]> pointsAndWeights) throws NonMonotonicSequenceException {
        this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
    }

    /**
     * Returns an estimate of the integral of {@code f(x) * w(x)},
     * where {@code w} is a weight function that depends on the actual
     * flavor of the Gauss integration scheme.
     * The algorithm uses the points and associated weights, as passed
     * to the {@link #GaussIntegrator(double[],double[]) constructor}.
     *
     * @param f Function to integrate.
     * @return the integral of the weighted function.
     */
    public double integrate(UnivariateFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88");
        double s = 0;
        double c = 0;
        for (int i = 0; ROR_less(i, points.length, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101686, _mut101687, _mut101688, _mut101689, _mut101690); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88");
            final double x = points[i];
            final double w = weights[i];
            final double y = AOR_minus(AOR_multiply(w, f.value(x), "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101666, _mut101667, _mut101668, _mut101669), c, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101670, _mut101671, _mut101672, _mut101673);
            final double t = AOR_plus(s, y, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101674, _mut101675, _mut101676, _mut101677);
            c = AOR_minus((AOR_minus(t, s, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101678, _mut101679, _mut101680, _mut101681)), y, "org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator.integrate_88", _mut101682, _mut101683, _mut101684, _mut101685);
            s = t;
        }
        return s;
    }

    /**
     * @return the order of the integration rule (the number of integration
     * points).
     */
    public int getNumberOfPoints() {
        return points.length;
    }

    /**
     * Gets the integration point at the given index.
     * The index must be in the valid range but no check is performed.
     * @param index index of the integration point
     * @return the integration point.
     */
    public double getPoint(int index) {
        return points[index];
    }

    /**
     * Gets the weight of the integration point at the given index.
     * The index must be in the valid range but no check is performed.
     * @param index index of the integration point
     * @return the weight.
     */
    public double getWeight(int index) {
        return weights[index];
    }
}
