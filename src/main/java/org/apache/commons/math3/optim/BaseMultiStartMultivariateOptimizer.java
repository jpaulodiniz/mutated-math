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
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.random.RandomVectorGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class multi-start optimizer for a multivariate function.
 * <br/>
 * This class wraps an optimizer in order to use it several times in
 * turn with different starting points (trying to avoid being trapped
 * in a local extremum when looking for a global one).
 * <em>It is not a "user" class.</em>
 *
 * @param <PAIR> Type of the point/value pair returned by the optimization
 * algorithm.
 *
 * @since 3.0
 */
public abstract class BaseMultiStartMultivariateOptimizer<PAIR> extends BaseMultivariateOptimizer<PAIR> {

    @Conditional
    public static boolean _mut59452 = false, _mut59453 = false, _mut59454 = false, _mut59455 = false, _mut59456 = false, _mut59457 = false, _mut59458 = false, _mut59459 = false, _mut59460 = false, _mut59461 = false, _mut59462 = false, _mut59463 = false, _mut59464 = false, _mut59465 = false, _mut59466 = false, _mut59467 = false, _mut59468 = false, _mut59469 = false, _mut59470 = false, _mut59471 = false, _mut59472 = false, _mut59473 = false, _mut59474 = false, _mut59475 = false, _mut59476 = false, _mut59477 = false, _mut59478 = false, _mut59479 = false, _mut59480 = false, _mut59481 = false, _mut59482 = false, _mut59483 = false, _mut59484 = false, _mut59485 = false, _mut59486 = false, _mut59487 = false, _mut59488 = false, _mut59489 = false, _mut59490 = false, _mut59491 = false, _mut59492 = false, _mut59493 = false, _mut59494 = false, _mut59495 = false, _mut59496 = false, _mut59497 = false, _mut59498 = false, _mut59499 = false, _mut59500 = false, _mut59501 = false, _mut59502 = false, _mut59503 = false, _mut59504 = false, _mut59505 = false, _mut59506 = false, _mut59507 = false, _mut59508 = false, _mut59509 = false, _mut59510 = false, _mut59511 = false, _mut59512 = false, _mut59513 = false, _mut59514 = false;

    /**
     * Underlying classical optimizer.
     */
    private final BaseMultivariateOptimizer<PAIR> optimizer;

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
    private RandomVectorGenerator generator;

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
    private int initialGuessIndex = -1;

    /**
     * Create a multi-start optimizer from a single-start optimizer.
     * <p>
     * Note that if there are bounds constraints (see {@link #getLowerBound()}
     * and {@link #getUpperBound()}), then a simple rejection algorithm is used
     * at each restart. This implies that the random vector generator should have
     * a good probability to generate vectors in the bounded domain, otherwise the
     * rejection algorithm will hit the {@link #getMaxEvaluations()} count without
     * generating a proper restart point. Users must be take great care of the <a
     * href="http://en.wikipedia.org/wiki/Curse_of_dimensionality">curse of dimensionality</a>.
     * </p>
     * @param optimizer Single-start optimizer to wrap.
     * @param starts Number of starts to perform. If {@code starts == 1},
     * the {@link #optimize(OptimizationData[]) optimize} will return the
     * same solution as the given {@code optimizer} would return.
     * @param generator Random vector generator to use for restarts.
     * @throws NotStrictlyPositiveException if {@code starts < 1}.
     */
    public BaseMultiStartMultivariateOptimizer(final BaseMultivariateOptimizer<PAIR> optimizer, final int starts, final RandomVectorGenerator generator) {
        super(optimizer.getConvergenceChecker());
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.BaseMultiStartMultivariateOptimizer_78");
        if (ROR_less(starts, 1, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.BaseMultiStartMultivariateOptimizer_78", _mut59452, _mut59453, _mut59454, _mut59455, _mut59456)) {
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
     * <br/>
     * The behaviour is undefined if this method is called before
     * {@code optimize}; it will likely throw {@code NullPointerException}.
     *
     * @return an array containing the optima sorted from best to worst.
     */
    public abstract PAIR[] getOptima();

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalStateException if {@code optData} does not contain an
     * instance of {@link MaxEval} or {@link InitialGuess}.
     */
    @Override
    public PAIR optimize(OptimizationData... optData) {
        // Store arguments in order to pass them to the internal optimizer.
        optimData = optData;
        // Set up base class and perform computations.
        return super.optimize(optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PAIR doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138");
        // to impose a different start value for each start.
        for (int i = 0; ROR_less(i, optimData.length, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59457, _mut59458, _mut59459, _mut59460, _mut59461); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138");
            if (optimData[i] instanceof MaxEval) {
                optimData[i] = null;
                maxEvalIndex = i;
            }
            if (optimData[i] instanceof InitialGuess) {
                optimData[i] = null;
                initialGuessIndex = i;
                continue;
            }
        }
        if (ROR_equals(maxEvalIndex, -1, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59462, _mut59463, _mut59464, _mut59465, _mut59466)) {
            throw new MathIllegalStateException();
        }
        if (ROR_equals(initialGuessIndex, -1, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59467, _mut59468, _mut59469, _mut59470, _mut59471)) {
            throw new MathIllegalStateException();
        }
        RuntimeException lastException = null;
        totalEvaluations = 0;
        clear();
        final int maxEval = getMaxEvaluations();
        final double[] min = getLowerBound();
        final double[] max = getUpperBound();
        final double[] startPoint = getStartPoint();
        // Multi-start loop.
        for (int i = 0; ROR_less(i, starts, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59505, _mut59506, _mut59507, _mut59508, _mut59509); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138");
            // CHECKSTYLE: stop IllegalCatch
            try {
                // Decrease number of allowed evaluations.
                optimData[maxEvalIndex] = new MaxEval(AOR_minus(maxEval, totalEvaluations, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59472, _mut59473, _mut59474, _mut59475));
                // New start value.
                double[] s = null;
                if (ROR_equals(i, 0, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59476, _mut59477, _mut59478, _mut59479, _mut59480)) {
                    s = startPoint;
                } else {
                    int attempts = 0;
                    while (s == null) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138");
                        if (ROR_greater_equals(attempts++, getMaxEvaluations(), "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59481, _mut59482, _mut59483, _mut59484, _mut59485)) {
                            throw new TooManyEvaluationsException(getMaxEvaluations());
                        }
                        s = generator.nextVector();
                        for (int k = 0; (_mut59504 ? (s != null || ROR_less(k, s.length, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59499, _mut59500, _mut59501, _mut59502, _mut59503)) : (s != null && ROR_less(k, s.length, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59499, _mut59500, _mut59501, _mut59502, _mut59503))); ++k) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138");
                            if ((_mut59498 ? (((_mut59491 ? (min != null || ROR_less(s[k], min[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59486, _mut59487, _mut59488, _mut59489, _mut59490)) : (min != null && ROR_less(s[k], min[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59486, _mut59487, _mut59488, _mut59489, _mut59490)))) && ((_mut59497 ? (max != null || ROR_greater(s[k], max[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59492, _mut59493, _mut59494, _mut59495, _mut59496)) : (max != null && ROR_greater(s[k], max[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59492, _mut59493, _mut59494, _mut59495, _mut59496))))) : (((_mut59491 ? (min != null || ROR_less(s[k], min[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59486, _mut59487, _mut59488, _mut59489, _mut59490)) : (min != null && ROR_less(s[k], min[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59486, _mut59487, _mut59488, _mut59489, _mut59490)))) || ((_mut59497 ? (max != null || ROR_greater(s[k], max[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59492, _mut59493, _mut59494, _mut59495, _mut59496)) : (max != null && ROR_greater(s[k], max[k], "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59492, _mut59493, _mut59494, _mut59495, _mut59496))))))) {
                                // reject the vector
                                s = null;
                            }
                        }
                    }
                }
                optimData[initialGuessIndex] = new InitialGuess(s);
                // Optimize.
                final PAIR result = optimizer.optimize(optimData);
                store(result);
            } catch (RuntimeException mue) {
                lastException = mue;
            }
            totalEvaluations += optimizer.getEvaluations();
        }
        final PAIR[] optima = getOptima();
        if (ROR_equals(optima.length, 0, "org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer.doOptimize_138", _mut59510, _mut59511, _mut59512, _mut59513, _mut59514)) {
            // Cannot be null if starts >= 1.
            throw lastException;
        }
        // Return the best optimum.
        return optima[0];
    }

    /**
     * Method that will be called in order to store each found optimum.
     *
     * @param optimum Result of an optimization run.
     */
    protected abstract void store(PAIR optimum);

    /**
     * Method that will called in order to clear all stored optima.
     */
    protected abstract void clear();
}
