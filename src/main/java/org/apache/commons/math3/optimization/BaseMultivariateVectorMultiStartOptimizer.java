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
package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomVectorGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for all implementations of a multi-start optimizer.
 *
 * This interface is mainly intended to enforce the internal coherence of
 * Commons-Math. Users of the API are advised to base their code on
 * {@link DifferentiableMultivariateVectorMultiStartOptimizer}.
 *
 * @param <FUNC> Type of the objective function to be optimized.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class BaseMultivariateVectorMultiStartOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {

    @Conditional
    public static boolean _mut71038 = false, _mut71039 = false, _mut71040 = false, _mut71041 = false, _mut71042 = false, _mut71043 = false, _mut71044 = false, _mut71045 = false, _mut71046 = false, _mut71047 = false, _mut71048 = false, _mut71049 = false, _mut71050 = false, _mut71051 = false, _mut71052 = false, _mut71053 = false, _mut71054 = false, _mut71055 = false, _mut71056 = false, _mut71057 = false, _mut71058 = false, _mut71059 = false, _mut71060 = false, _mut71061 = false, _mut71062 = false, _mut71063 = false, _mut71064 = false, _mut71065 = false, _mut71066 = false, _mut71067 = false, _mut71068 = false, _mut71069 = false, _mut71070 = false, _mut71071 = false, _mut71072 = false, _mut71073 = false, _mut71074 = false;

    /**
     * Underlying classical optimizer.
     */
    private final BaseMultivariateVectorOptimizer<FUNC> optimizer;

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
    private RandomVectorGenerator generator;

    /**
     * Found optima.
     */
    private PointVectorValuePair[] optima;

    /**
     * Create a multi-start optimizer from a single-start optimizer.
     *
     * @param optimizer Single-start optimizer to wrap.
     * @param starts Number of starts to perform. If {@code starts == 1},
     * the {@link #optimize(int,MultivariateVectorFunction,double[],double[],double[])
     * optimize} will return the same solution as {@code optimizer} would.
     * @param generator Random vector generator to use for restarts.
     * @throws NullArgumentException if {@code optimizer} or {@code generator}
     * is {@code null}.
     * @throws NotStrictlyPositiveException if {@code starts < 1}.
     */
    protected BaseMultivariateVectorMultiStartOptimizer(final BaseMultivariateVectorOptimizer<FUNC> optimizer, final int starts, final RandomVectorGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.BaseMultivariateVectorMultiStartOptimizer_71");
        if ((_mut71038 ? (optimizer == null && generator == null) : (optimizer == null || generator == null))) {
            throw new NullArgumentException();
        }
        if (ROR_less(starts, 1, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.BaseMultivariateVectorMultiStartOptimizer_71", _mut71039, _mut71040, _mut71041, _mut71042, _mut71043)) {
            throw new NotStrictlyPositiveException(starts);
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    /**
     * Get all the optima found during the last call to {@link
     * #optimize(int,MultivariateVectorFunction,double[],double[],double[]) optimize}.
     * The optimizer stores all the optima found during a set of
     * restarts. The {@link #optimize(int,MultivariateVectorFunction,double[],double[],double[])
     * optimize} method returns the best point only. This method
     * returns all the points found at the end of each starts, including
     * the best one already returned by the {@link
     * #optimize(int,MultivariateVectorFunction,double[],double[],double[]) optimize} method.
     * <br/>
     * The returned array as one element for each start as specified
     * in the constructor. It is ordered with the results from the
     * runs that did converge first, sorted from best to worst
     * objective value (i.e. in ascending order if minimizing and in
     * descending order if maximizing), followed by and null elements
     * corresponding to the runs that did not converge. This means all
     * elements will be null if the {@link
     * #optimize(int,MultivariateVectorFunction,double[],double[],double[]) optimize} method did
     * throw a {@link ConvergenceException}). This also means that if
     * the first element is not {@code null}, it is the best point found
     * across all starts.
     *
     * @return array containing the optima
     * @throws MathIllegalStateException if {@link
     * #optimize(int,MultivariateVectorFunction,double[],double[],double[]) optimize} has not been
     * called.
     */
    public PointVectorValuePair[] getOptima() {
        if (optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET);
        }
        return optima.clone();
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
     * {@inheritDoc}
     */
    public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
        return optimizer.getConvergenceChecker();
    }

    /**
     * {@inheritDoc}
     */
    public PointVectorValuePair optimize(int maxEval, final FUNC f, double[] target, double[] weights, double[] startPoint) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.optimize_139");
        maxEvaluations = maxEval;
        RuntimeException lastException = null;
        optima = new PointVectorValuePair[starts];
        totalEvaluations = 0;
        // Multi-start loop.
        for (int i = 0; ROR_less(i, starts, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.optimize_139", _mut71053, _mut71054, _mut71055, _mut71056, _mut71057); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.optimize_139");
            // CHECKSTYLE: stop IllegalCatch
            try {
                optima[i] = optimizer.optimize(AOR_minus(maxEval, totalEvaluations, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.optimize_139", _mut71044, _mut71045, _mut71046, _mut71047), f, target, weights, ROR_equals(i, 0, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.optimize_139", _mut71048, _mut71049, _mut71050, _mut71051, _mut71052) ? startPoint : generator.nextVector());
            } catch (ConvergenceException oe) {
                optima[i] = null;
            } catch (RuntimeException mue) {
                lastException = mue;
                optima[i] = null;
            }
            totalEvaluations += optimizer.getEvaluations();
        }
        sortPairs(target, weights);
        if (optima[0] == null) {
            // cannot be null if starts >=1
            throw lastException;
        }
        // Return the found point given the best objective function value.
        return optima[0];
    }

    /**
     * Sort the optima from best to worst, followed by {@code null} elements.
     *
     * @param target Target value for the objective functions at optimum.
     * @param weights Weights for the least-squares cost computation.
     */
    private void sortPairs(final double[] target, final double[] weights) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194");
        Arrays.sort(optima, new Comparator<PointVectorValuePair>() {

            /**
             * {@inheritDoc}
             */
            public int compare(final PointVectorValuePair o1, final PointVectorValuePair o2) {
                if (o1 == null) {
                    return (o2 == null) ? 0 : 1;
                } else if (o2 == null) {
                    return -1;
                }
                return Double.compare(weightedResidual(o1), weightedResidual(o2));
            }

            private double weightedResidual(final PointVectorValuePair pv) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194");
                final double[] value = pv.getValueRef();
                double sum = 0;
                for (int i = 0; ROR_less(i, value.length, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194", _mut71070, _mut71071, _mut71072, _mut71073, _mut71074); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194");
                    final double ri = AOR_minus(value[i], target[i], "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194", _mut71058, _mut71059, _mut71060, _mut71061);
                    sum += AOR_multiply(AOR_multiply(weights[i], ri, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194", _mut71062, _mut71063, _mut71064, _mut71065), ri, "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer.weightedResidual_194", _mut71066, _mut71067, _mut71068, _mut71069);
                }
                return sum;
            }
        });
    }
}
