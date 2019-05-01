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

import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Provide a default implementation for several functions useful to generic
 * optimizers.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public abstract class BaseAbstractUnivariateOptimizer implements UnivariateOptimizer {

    @Conditional
    public static boolean _mut70720 = false, _mut70721 = false, _mut70722 = false, _mut70723 = false, _mut70724 = false, _mut70725 = false, _mut70726 = false, _mut70727 = false, _mut70728 = false, _mut70729 = false, _mut70730 = false, _mut70731 = false;

    /**
     * Convergence checker.
     */
    private final ConvergenceChecker<UnivariatePointValuePair> checker;

    /**
     * Evaluations counter.
     */
    private final Incrementor evaluations = new Incrementor();

    /**
     * Optimization type
     */
    private GoalType goal;

    /**
     * Lower end of search interval.
     */
    private double searchMin;

    /**
     * Higher end of search interval.
     */
    private double searchMax;

    /**
     * Initial guess .
     */
    private double searchStart;

    /**
     * Function to optimize.
     */
    private UnivariateFunction function;

    /**
     * @param checker Convergence checking procedure.
     */
    protected BaseAbstractUnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> checker) {
        this.checker = checker;
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxEvaluations() {
        return evaluations.getMaximalCount();
    }

    /**
     * {@inheritDoc}
     */
    public int getEvaluations() {
        return evaluations.getCount();
    }

    /**
     * @return the optimization type.
     */
    public GoalType getGoalType() {
        return goal;
    }

    /**
     * @return the lower end of the search interval.
     */
    public double getMin() {
        return searchMin;
    }

    /**
     * @return the higher end of the search interval.
     */
    public double getMax() {
        return searchMax;
    }

    /**
     * @return the initial guess.
     */
    public double getStartValue() {
        return searchStart;
    }

    /**
     * Compute the objective function value.
     *
     * @param point Point at which the objective function must be evaluated.
     * @return the objective function value at specified point.
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     */
    protected double computeObjectiveValue(double point) {
        try {
            evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return function.value(point);
    }

    /**
     * {@inheritDoc}
     */
    public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max, double startValue) {
        // Checks.
        if (f == null) {
            throw new NullArgumentException();
        }
        if (goalType == null) {
            throw new NullArgumentException();
        }
        // Reset.
        searchMin = min;
        searchMax = max;
        searchStart = startValue;
        goal = goalType;
        function = f;
        evaluations.setMaximalCount(maxEval);
        evaluations.resetCount();
        // Perform computation.
        return doOptimize();
    }

    /**
     * {@inheritDoc}
     */
    public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer.optimize_139");
        return optimize(maxEval, f, goalType, min, max, AOR_plus(min, AOR_multiply(0.5, (AOR_minus(max, min, "org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer.optimize_139", _mut70720, _mut70721, _mut70722, _mut70723)), "org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer.optimize_139", _mut70724, _mut70725, _mut70726, _mut70727), "org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer.optimize_139", _mut70728, _mut70729, _mut70730, _mut70731));
    }

    /**
     * {@inheritDoc}
     */
    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return checker;
    }

    /**
     * Method for implementing actual optimization algorithms in derived
     * classes.
     *
     * @return the optimum and its corresponding function value.
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     */
    protected abstract UnivariatePointValuePair doOptimize();
}
