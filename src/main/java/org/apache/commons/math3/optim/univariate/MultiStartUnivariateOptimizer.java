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
package org.apache.commons.math3.optim.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.OptimizationData;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Special implementation of the {@link UnivariateOptimizer} interface
 * adding multi-start features to an existing optimizer.
 * <br/>
 * This class wraps an optimizer in order to use it several times in
 * turn with different starting points (trying to avoid being trapped
 * in a local extremum when looking for a global one).
 *
 * @since 3.0
 */
public class MultiStartUnivariateOptimizer extends UnivariateOptimizer {

    @Conditional
    public static boolean _mut58839 = false, _mut58840 = false, _mut58841 = false, _mut58842 = false, _mut58843 = false, _mut58844 = false, _mut58845 = false, _mut58846 = false, _mut58847 = false, _mut58848 = false, _mut58849 = false, _mut58850 = false, _mut58851 = false, _mut58852 = false, _mut58853 = false, _mut58854 = false, _mut58855 = false, _mut58856 = false, _mut58857 = false, _mut58858 = false, _mut58859 = false, _mut58860 = false, _mut58861 = false, _mut58862 = false, _mut58863 = false, _mut58864 = false, _mut58865 = false, _mut58866 = false, _mut58867 = false, _mut58868 = false, _mut58869 = false, _mut58870 = false, _mut58871 = false, _mut58872 = false, _mut58873 = false, _mut58874 = false, _mut58875 = false, _mut58876 = false, _mut58877 = false, _mut58878 = false, _mut58879 = false, _mut58880 = false, _mut58881 = false, _mut58882 = false, _mut58883 = false, _mut58884 = false;

    /**
     * Underlying classical optimizer.
     */
    private final UnivariateOptimizer optimizer;

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
     * Optimization data.
     */
    private OptimizationData[] optimData;

    /**
     * Location in {@link #optimData} where the updated maximum
     * number of evaluations will be stored.
     */
    private int maxEvalIndex = -1;

    /**
     * Location in {@link #optimData} where the updated start value
     * will be stored.
     */
    private int searchIntervalIndex = -1;

    /**
     * Create a multi-start optimizer from a single-start optimizer.
     *
     * @param optimizer Single-start optimizer to wrap.
     * @param starts Number of starts to perform. If {@code starts == 1},
     * the {@code optimize} methods will return the same solution as
     * {@code optimizer} would.
     * @param generator Random generator to use for restarts.
     * @throws NotStrictlyPositiveException if {@code starts < 1}.
     */
    public MultiStartUnivariateOptimizer(final UnivariateOptimizer optimizer, final int starts, final RandomGenerator generator) {
        super(optimizer.getConvergenceChecker());
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.MultiStartUnivariateOptimizer_75");
        if (ROR_less(starts, 1, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.MultiStartUnivariateOptimizer_75", _mut58839, _mut58840, _mut58841, _mut58842, _mut58843)) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEvaluations() {
        return totalEvaluations;
    }

    /**
     * Gets all the optima found during the last call to {@code optimize}.
     * The optimizer stores all the optima found during a set of
     * restarts. The {@code optimize} method returns the best point only.
     * This method returns all the points found at the end of each starts,
     * including the best one already returned by the {@code optimize} method.
     * <br/>
     * The returned array as one element for each start as specified
     * in the constructor. It is ordered with the results from the
     * runs that did converge first, sorted from best to worst
     * objective value (i.e in ascending order if minimizing and in
     * descending order if maximizing), followed by {@code null} elements
     * corresponding to the runs that did not converge. This means all
     * elements will be {@code null} if the {@code optimize} method did throw
     * an exception.
     * This also means that if the first element is not {@code null}, it is
     * the best point found across all starts.
     *
     * @return an array containing the optima.
     * @throws MathIllegalStateException if {@link #optimize(OptimizationData[])
     * optimize} has not been called.
     */
    public UnivariatePointValuePair[] getOptima() {
        if (optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET);
        }
        return optima.clone();
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalStateException if {@code optData} does not contain an
     * instance of {@link MaxEval} or {@link SearchInterval}.
     */
    @Override
    public UnivariatePointValuePair optimize(OptimizationData... optData) {
        // Store arguments in order to pass them to the internal optimizer.
        optimData = optData;
        // Set up base class and perform computations.
        return super.optimize(optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UnivariatePointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139");
        // to impose a different start value for each start.
        for (int i = 0; ROR_less(i, optimData.length, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58844, _mut58845, _mut58846, _mut58847, _mut58848); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139");
            if (optimData[i] instanceof MaxEval) {
                optimData[i] = null;
                maxEvalIndex = i;
                continue;
            }
            if (optimData[i] instanceof SearchInterval) {
                optimData[i] = null;
                searchIntervalIndex = i;
                continue;
            }
        }
        if (ROR_equals(maxEvalIndex, -1, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58849, _mut58850, _mut58851, _mut58852, _mut58853)) {
            throw new MathIllegalStateException();
        }
        if (ROR_equals(searchIntervalIndex, -1, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58854, _mut58855, _mut58856, _mut58857, _mut58858)) {
            throw new MathIllegalStateException();
        }
        RuntimeException lastException = null;
        optima = new UnivariatePointValuePair[starts];
        totalEvaluations = 0;
        final int maxEval = getMaxEvaluations();
        final double min = getMin();
        final double max = getMax();
        final double startValue = getStartValue();
        // Multi-start loop.
        for (int i = 0; ROR_less(i, starts, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58880, _mut58881, _mut58882, _mut58883, _mut58884); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139");
            // CHECKSTYLE: stop IllegalCatch
            try {
                // Decrease number of allowed evaluations.
                optimData[maxEvalIndex] = new MaxEval(AOR_minus(maxEval, totalEvaluations, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58859, _mut58860, _mut58861, _mut58862));
                // New start value.
                final double s = (ROR_equals(i, 0, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58863, _mut58864, _mut58865, _mut58866, _mut58867)) ? startValue : AOR_plus(min, AOR_multiply(generator.nextDouble(), (AOR_minus(max, min, "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58868, _mut58869, _mut58870, _mut58871)), "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58872, _mut58873, _mut58874, _mut58875), "org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.doOptimize_139", _mut58876, _mut58877, _mut58878, _mut58879);
                optimData[searchIntervalIndex] = new SearchInterval(min, max, s);
                // Optimize.
                optima[i] = optimizer.optimize(optimData);
            } catch (RuntimeException mue) {
                lastException = mue;
                optima[i] = null;
            }
            totalEvaluations += optimizer.getEvaluations();
        }
        sortPairs(getGoalType());
        if (optima[0] == null) {
            // Cannot be null if starts >= 1.
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
