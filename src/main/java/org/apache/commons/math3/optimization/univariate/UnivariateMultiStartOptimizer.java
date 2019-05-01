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
package org.apache.commons.math3.optimization.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Special implementation of the {@link UnivariateOptimizer} interface
 * adding multi-start features to an existing optimizer.
 *
 * This class wraps a classical optimizer to use it several times in
 * turn with different starting points in order to avoid being trapped
 * into a local extremum when looking for a global one.
 *
 * @param <FUNC> Type of the objective function to be optimized.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class UnivariateMultiStartOptimizer<FUNC extends UnivariateFunction> implements BaseUnivariateOptimizer<FUNC> {

    @Conditional
    public static boolean _mut70676 = false, _mut70677 = false, _mut70678 = false, _mut70679 = false, _mut70680 = false, _mut70681 = false, _mut70682 = false, _mut70683 = false, _mut70684 = false, _mut70685 = false, _mut70686 = false, _mut70687 = false, _mut70688 = false, _mut70689 = false, _mut70690 = false, _mut70691 = false, _mut70692 = false, _mut70693 = false, _mut70694 = false, _mut70695 = false, _mut70696 = false, _mut70697 = false, _mut70698 = false, _mut70699 = false, _mut70700 = false, _mut70701 = false, _mut70702 = false, _mut70703 = false, _mut70704 = false, _mut70705 = false, _mut70706 = false, _mut70707 = false, _mut70708 = false, _mut70709 = false, _mut70710 = false, _mut70711 = false, _mut70712 = false, _mut70713 = false, _mut70714 = false, _mut70715 = false, _mut70716 = false, _mut70717 = false, _mut70718 = false, _mut70719 = false;

    /**
     * Underlying classical optimizer.
     */
    private final BaseUnivariateOptimizer<FUNC> optimizer;

    /**
     * Maximal number of evaluations allowed.
     */
    private int maxEvaluations;

    /**
     * Number of evaluations already performed for all starts.
     */
    private int totalEvaluations;

    /**
     * Number of starts to go.
     */
    private int starts;

    /**
     * Random generator for multi-start.
     */
    private RandomGenerator generator;

    /**
     * Found optima.
     */
    private UnivariatePointValuePair[] optima;

    /**
     * Create a multi-start optimizer from a single-start optimizer.
     *
     * @param optimizer Single-start optimizer to wrap.
     * @param starts Number of starts to perform. If {@code starts == 1},
     * the {@code optimize} methods will return the same solution as
     * {@code optimizer} would.
     * @param generator Random generator to use for restarts.
     * @throws NullArgumentException if {@code optimizer} or {@code generator}
     * is {@code null}.
     * @throws NotStrictlyPositiveException if {@code starts < 1}.
     */
    public UnivariateMultiStartOptimizer(final BaseUnivariateOptimizer<FUNC> optimizer, final int starts, final RandomGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.UnivariateMultiStartOptimizer_73");
        if ((_mut70676 ? (optimizer == null && generator == null) : (optimizer == null || generator == null))) {
            throw new NullArgumentException();
        }
        if (ROR_less(starts, 1, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.UnivariateMultiStartOptimizer_73", _mut70677, _mut70678, _mut70679, _mut70680, _mut70681)) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    /**
     * {@inheritDoc}
     */
    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return optimizer.getConvergenceChecker();
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxEvaluations() {
        return maxEvaluations;
    }

    /**
     * {@inheritDoc}
     */
    public int getEvaluations() {
        return totalEvaluations;
    }

    /**
     * Get all the optima found during the last call to {@link
     * #optimize(int,UnivariateFunction,GoalType,double,double) optimize}.
     * The optimizer stores all the optima found during a set of
     * restarts. The {@link #optimize(int,UnivariateFunction,GoalType,double,double) optimize}
     * method returns the best point only. This method returns all the points
     * found at the end of each starts, including the best one already
     * returned by the {@link #optimize(int,UnivariateFunction,GoalType,double,double) optimize}
     * method.
     * <br/>
     * The returned array as one element for each start as specified
     * in the constructor. It is ordered with the results from the
     * runs that did converge first, sorted from best to worst
     * objective value (i.e in ascending order if minimizing and in
     * descending order if maximizing), followed by {@code null} elements
     * corresponding to the runs that did not converge. This means all
     * elements will be {@code null} if the {@link
     * #optimize(int,UnivariateFunction,GoalType,double,double) optimize}
     * method did throw an exception.
     * This also means that if the first element is not {@code null}, it is
     * the best point found across all starts.
     *
     * @return an array containing the optima.
     * @throws MathIllegalStateException if {@link
     * #optimize(int,UnivariateFunction,GoalType,double,double) optimize}
     * has not been called.
     */
    public UnivariatePointValuePair[] getOptima() {
        if (optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET);
        }
        return optima.clone();
    }

    /**
     * {@inheritDoc}
     */
    public UnivariatePointValuePair optimize(int maxEval, final FUNC f, final GoalType goal, final double min, final double max) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_141");
        return optimize(maxEval, f, goal, min, max, AOR_plus(min, AOR_multiply(0.5, (AOR_minus(max, min, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_141", _mut70682, _mut70683, _mut70684, _mut70685)), "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_141", _mut70686, _mut70687, _mut70688, _mut70689), "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_141", _mut70690, _mut70691, _mut70692, _mut70693));
    }

    /**
     * {@inheritDoc}
     */
    public UnivariatePointValuePair optimize(int maxEval, final FUNC f, final GoalType goal, final double min, final double max, final double startValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148");
        RuntimeException lastException = null;
        optima = new UnivariatePointValuePair[starts];
        totalEvaluations = 0;
        // Multi-start loop.
        for (int i = 0; ROR_less(i, starts, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70715, _mut70716, _mut70717, _mut70718, _mut70719); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148");
            // CHECKSTYLE: stop IllegalCatch
            try {
                final double s = (ROR_equals(i, 0, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70694, _mut70695, _mut70696, _mut70697, _mut70698)) ? startValue : AOR_plus(min, AOR_multiply(generator.nextDouble(), (AOR_minus(max, min, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70699, _mut70700, _mut70701, _mut70702)), "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70703, _mut70704, _mut70705, _mut70706), "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70707, _mut70708, _mut70709, _mut70710);
                optima[i] = optimizer.optimize(AOR_minus(maxEval, totalEvaluations, "org.apache.commons.math3.optimization.univariate.UnivariateMultiStartOptimizer.optimize_148", _mut70711, _mut70712, _mut70713, _mut70714), f, goal, min, max, s);
            } catch (RuntimeException mue) {
                lastException = mue;
                optima[i] = null;
            }
            totalEvaluations += optimizer.getEvaluations();
        }
        sortPairs(goal);
        if (optima[0] == null) {
            // cannot be null if starts >=1
            throw lastException;
        }
        // Return the point with the best objective function value.
        return optima[0];
    }

    /**
     * Sort the optima from best to worst, followed by {@code null} elements.
     *
     * @param goal Goal type.
     */
    private void sortPairs(final GoalType goal) {
        Arrays.sort(optima, new Comparator<UnivariatePointValuePair>() {

            /**
             * {@inheritDoc}
             */
            public int compare(final UnivariatePointValuePair o1, final UnivariatePointValuePair o2) {
                if (o1 == null) {
                    return (o2 == null) ? 0 : 1;
                } else if (o2 == null) {
                    return -1;
                }
                final double v1 = o1.getValue();
                final double v2 = o2.getValue();
                return (goal == GoalType.MINIMIZE) ? Double.compare(v1, v2) : Double.compare(v2, v1);
            }
        });
    }
}
