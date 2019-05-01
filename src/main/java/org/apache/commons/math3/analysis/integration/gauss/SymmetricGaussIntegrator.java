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
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class's implements {@link #integrate(UnivariateFunction) integrate}
 * method assuming that the integral is symmetric about 0.
 * This allows to reduce numerical errors.
 *
 * @since 3.3
 */
public class SymmetricGaussIntegrator extends GaussIntegrator {

    @Conditional
    public static boolean _mut101691 = false, _mut101692 = false, _mut101693 = false, _mut101694 = false, _mut101695 = false, _mut101696 = false, _mut101697 = false, _mut101698 = false, _mut101699 = false, _mut101700 = false, _mut101701 = false, _mut101702 = false, _mut101703 = false, _mut101704 = false, _mut101705 = false, _mut101706 = false, _mut101707 = false, _mut101708 = false, _mut101709 = false, _mut101710 = false, _mut101711 = false, _mut101712 = false, _mut101713 = false, _mut101714 = false, _mut101715 = false, _mut101716 = false, _mut101717 = false, _mut101718 = false, _mut101719 = false, _mut101720 = false, _mut101721 = false, _mut101722 = false, _mut101723 = false, _mut101724 = false, _mut101725 = false, _mut101726 = false, _mut101727 = false, _mut101728 = false, _mut101729 = false, _mut101730 = false, _mut101731 = false, _mut101732 = false, _mut101733 = false, _mut101734 = false, _mut101735 = false, _mut101736 = false, _mut101737 = false, _mut101738 = false, _mut101739 = false, _mut101740 = false, _mut101741 = false, _mut101742 = false, _mut101743 = false, _mut101744 = false, _mut101745 = false, _mut101746 = false, _mut101747 = false, _mut101748 = false, _mut101749 = false, _mut101750 = false, _mut101751 = false, _mut101752 = false, _mut101753 = false;

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
    public SymmetricGaussIntegrator(double[] points, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException {
        super(points, weights);
    }

    /**
     * Creates an integrator from the given pair of points (first element of
     * the pair) and weights (second element of the pair.
     *
     * @param pointsAndWeights Integration points and corresponding weights.
     * @throws NonMonotonicSequenceException if the {@code points} are not
     * sorted in increasing order.
     *
     * @see #SymmetricGaussIntegrator(double[], double[])
     */
    public SymmetricGaussIntegrator(Pair<double[], double[]> pointsAndWeights) throws NonMonotonicSequenceException {
        this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double integrate(UnivariateFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67");
        final int ruleLength = getNumberOfPoints();
        if (ROR_equals(ruleLength, 1, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101691, _mut101692, _mut101693, _mut101694, _mut101695)) {
            return AOR_multiply(getWeight(0), f.value(0d), "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101696, _mut101697, _mut101698, _mut101699);
        }
        final int iMax = AOR_divide(ruleLength, 2, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101700, _mut101701, _mut101702, _mut101703);
        double s = 0;
        double c = 0;
        for (int i = 0; ROR_less(i, iMax, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101728, _mut101729, _mut101730, _mut101731, _mut101732); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67");
            final double p = getPoint(i);
            final double w = getWeight(i);
            final double f1 = f.value(p);
            final double f2 = f.value(-p);
            final double y = AOR_minus(AOR_multiply(w, (AOR_plus(f1, f2, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101704, _mut101705, _mut101706, _mut101707)), "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101708, _mut101709, _mut101710, _mut101711), c, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101712, _mut101713, _mut101714, _mut101715);
            final double t = AOR_plus(s, y, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101716, _mut101717, _mut101718, _mut101719);
            c = AOR_minus((AOR_minus(t, s, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101720, _mut101721, _mut101722, _mut101723)), y, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101724, _mut101725, _mut101726, _mut101727);
            s = t;
        }
        if (ROR_not_equals(AOR_remainder(ruleLength, 2, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101733, _mut101734, _mut101735, _mut101736), 0, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101737, _mut101738, _mut101739, _mut101740, _mut101741)) {
            final double w = getWeight(iMax);
            final double y = AOR_minus(AOR_multiply(w, f.value(0d), "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101742, _mut101743, _mut101744, _mut101745), c, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101746, _mut101747, _mut101748, _mut101749);
            final double t = AOR_plus(s, y, "org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator.integrate_67", _mut101750, _mut101751, _mut101752, _mut101753);
            s = t;
        }
        return s;
    }
}
