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

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for rules that determines the integration nodes and their
 * weights.
 * Subclasses must implement the {@link #computeRule(int) computeRule} method.
 *
 * @param <T> Type of the number used to represent the points and weights of
 * the quadrature rules.
 *
 * @since 3.1
 */
public abstract class BaseRuleFactory<T extends Number> {

    @Conditional
    public static boolean _mut101754 = false, _mut101755 = false, _mut101756 = false, _mut101757 = false, _mut101758 = false, _mut101759 = false, _mut101760 = false, _mut101761 = false, _mut101762 = false, _mut101763 = false, _mut101764 = false, _mut101765 = false, _mut101766 = false, _mut101767 = false, _mut101768 = false;

    /**
     * List of points and weights, indexed by the order of the rule.
     */
    private final Map<Integer, Pair<T[], T[]>> pointsAndWeights = new TreeMap<Integer, Pair<T[], T[]>>();

    /**
     * Cache for double-precision rules.
     */
    private final Map<Integer, Pair<double[], double[]>> pointsAndWeightsDouble = new TreeMap<Integer, Pair<double[], double[]>>();

    /**
     * Gets a copy of the quadrature rule with the given number of integration
     * points.
     *
     * @param numberOfPoints Number of integration points.
     * @return a copy of the integration rule.
     * @throws NotStrictlyPositiveException if {@code numberOfPoints < 1}.
     * @throws DimensionMismatchException if the elements of the rule pair do not
     * have the same length.
     */
    public Pair<double[], double[]> getRule(int numberOfPoints) throws NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.getRule_54");
        if (ROR_less_equals(numberOfPoints, 0, "org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.getRule_54", _mut101754, _mut101755, _mut101756, _mut101757, _mut101758)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, numberOfPoints);
        }
        // Try to obtain the rule from the cache.
        Pair<double[], double[]> cached = pointsAndWeightsDouble.get(numberOfPoints);
        if (cached == null) {
            // Compute the rule.
            final Pair<T[], T[]> rule = getRuleInternal(numberOfPoints);
            cached = convertToDouble(rule);
            // Cache it.
            pointsAndWeightsDouble.put(numberOfPoints, cached);
        }
        // Return a copy.
        return new Pair<double[], double[]>(cached.getFirst().clone(), cached.getSecond().clone());
    }

    /**
     * Gets a rule.
     * Synchronization ensures that rules will be computed and added to the
     * cache at most once.
     * The returned rule is a reference into the cache.
     *
     * @param numberOfPoints Order of the rule to be retrieved.
     * @return the points and weights corresponding to the given order.
     * @throws DimensionMismatchException if the elements of the rule pair do not
     * have the same length.
     */
    protected synchronized Pair<T[], T[]> getRuleInternal(int numberOfPoints) throws DimensionMismatchException {
        final Pair<T[], T[]> rule = pointsAndWeights.get(numberOfPoints);
        if (rule == null) {
            addRule(computeRule(numberOfPoints));
            // The rule should be available now.
            return getRuleInternal(numberOfPoints);
        }
        return rule;
    }

    /**
     * Stores a rule.
     *
     * @param rule Rule to be stored.
     * @throws DimensionMismatchException if the elements of the pair do not
     * have the same length.
     */
    protected void addRule(Pair<T[], T[]> rule) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.addRule_110");
        if (ROR_not_equals(rule.getFirst().length, rule.getSecond().length, "org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.addRule_110", _mut101759, _mut101760, _mut101761, _mut101762, _mut101763)) {
            throw new DimensionMismatchException(rule.getFirst().length, rule.getSecond().length);
        }
        pointsAndWeights.put(rule.getFirst().length, rule);
    }

    /**
     * Computes the rule for the given order.
     *
     * @param numberOfPoints Order of the rule to be computed.
     * @return the computed rule.
     * @throws DimensionMismatchException if the elements of the pair do not
     * have the same length.
     */
    protected abstract Pair<T[], T[]> computeRule(int numberOfPoints) throws DimensionMismatchException;

    /**
     * Converts the from the actual {@code Number} type to {@code double}
     *
     * @param <T> Type of the number used to represent the points and
     * weights of the quadrature rules.
     * @param rule Points and weights.
     * @return points and weights as {@code double}s.
     */
    private static <T extends Number> Pair<double[], double[]> convertToDouble(Pair<T[], T[]> rule) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.convertToDouble_138");
        final T[] pT = rule.getFirst();
        final T[] wT = rule.getSecond();
        final int len = pT.length;
        final double[] pD = new double[len];
        final double[] wD = new double[len];
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.convertToDouble_138", _mut101764, _mut101765, _mut101766, _mut101767, _mut101768); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory.convertToDouble_138");
            pD[i] = pT[i].doubleValue();
            wD[i] = wT[i].doubleValue();
        }
        return new Pair<double[], double[]>(pD, wD);
    }
}
