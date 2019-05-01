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
package org.apache.commons.math3.optim.linear;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Solves a linear problem using the "Two-Phase Simplex" method.
 * <p>
 * The {@link SimplexSolver} supports the following {@link OptimizationData} data provided
 * as arguments to {@link #optimize(OptimizationData...)}:
 * <ul>
 *   <li>objective function: {@link LinearObjectiveFunction} - mandatory</li>
 *   <li>linear constraints {@link LinearConstraintSet} - mandatory</li>
 *   <li>type of optimization: {@link org.apache.commons.math3.optim.nonlinear.scalar.GoalType GoalType}
 *    - optional, default: {@link org.apache.commons.math3.optim.nonlinear.scalar.GoalType#MINIMIZE MINIMIZE}</li>
 *   <li>whether to allow negative values as solution: {@link NonNegativeConstraint} - optional, default: true</li>
 *   <li>pivot selection rule: {@link PivotSelectionRule} - optional, default {@link PivotSelectionRule#DANTZIG}</li>
 *   <li>callback for the best solution: {@link SolutionCallback} - optional</li>
 *   <li>maximum number of iterations: {@link org.apache.commons.math3.optim.MaxIter} - optional, default: {@link Integer#MAX_VALUE}</li>
 * </ul>
 * <p>
 * <b>Note:</b> Depending on the problem definition, the default convergence criteria
 * may be too strict, resulting in {@link NoFeasibleSolutionException} or
 * {@link TooManyIterationsException}. In such a case it is advised to adjust these
 * criteria with more appropriate values, e.g. relaxing the epsilon value.
 * <p>
 * Default convergence criteria:
 * <ul>
 *   <li>Algorithm convergence: 1e-6</li>
 *   <li>Floating-point comparisons: 10 ulp</li>
 *   <li>Cut-Off value: 1e-10</li>
 * </ul>
 * <p>
 * The cut-off value has been introduced to handle the case of very small pivot elements
 * in the Simplex tableau, as these may lead to numerical instabilities and degeneracy.
 * Potential pivot elements smaller than this value will be treated as if they were zero
 * and are thus not considered by the pivot selection mechanism. The default value is safe
 * for many problems, but may need to be adjusted in case of very small coefficients
 * used in either the {@link LinearConstraint} or {@link LinearObjectiveFunction}.
 *
 * @since 2.0
 */
public class SimplexSolver extends LinearOptimizer {

    @Conditional
    public static boolean _mut59858 = false, _mut59859 = false, _mut59860 = false, _mut59861 = false, _mut59862 = false, _mut59863 = false, _mut59864 = false, _mut59865 = false, _mut59866 = false, _mut59867 = false, _mut59868 = false, _mut59869 = false, _mut59870 = false, _mut59871 = false, _mut59872 = false, _mut59873 = false, _mut59874 = false, _mut59875 = false, _mut59876 = false, _mut59877 = false, _mut59878 = false, _mut59879 = false, _mut59880 = false, _mut59881 = false, _mut59882 = false, _mut59883 = false, _mut59884 = false, _mut59885 = false, _mut59886 = false, _mut59887 = false, _mut59888 = false, _mut59889 = false, _mut59890 = false, _mut59891 = false, _mut59892 = false, _mut59893 = false, _mut59894 = false, _mut59895 = false, _mut59896 = false, _mut59897 = false, _mut59898 = false, _mut59899 = false, _mut59900 = false, _mut59901 = false, _mut59902 = false, _mut59903 = false, _mut59904 = false, _mut59905 = false, _mut59906 = false, _mut59907 = false, _mut59908 = false, _mut59909 = false, _mut59910 = false, _mut59911 = false, _mut59912 = false, _mut59913 = false, _mut59914 = false, _mut59915 = false, _mut59916 = false, _mut59917 = false, _mut59918 = false, _mut59919 = false, _mut59920 = false, _mut59921 = false, _mut59922 = false, _mut59923 = false, _mut59924 = false, _mut59925 = false, _mut59926 = false, _mut59927 = false, _mut59928 = false, _mut59929 = false, _mut59930 = false, _mut59931 = false, _mut59932 = false, _mut59933 = false, _mut59934 = false, _mut59935 = false, _mut59936 = false, _mut59937 = false, _mut59938 = false, _mut59939 = false, _mut59940 = false, _mut59941 = false, _mut59942 = false, _mut59943 = false, _mut59944 = false, _mut59945 = false, _mut59946 = false, _mut59947 = false, _mut59948 = false, _mut59949 = false, _mut59950 = false, _mut59951 = false, _mut59952 = false, _mut59953 = false, _mut59954 = false, _mut59955 = false;

    /**
     * Default amount of error to accept in floating point comparisons (as ulps).
     */
    static final int DEFAULT_ULPS = 10;

    /**
     * Default cut-off value.
     */
    static final double DEFAULT_CUT_OFF = 1e-10;

    /**
     * Default amount of error to accept for algorithm convergence.
     */
    private static final double DEFAULT_EPSILON = 1.0e-6;

    /**
     * Amount of error to accept for algorithm convergence.
     */
    private final double epsilon;

    /**
     * Amount of error to accept in floating point comparisons (as ulps).
     */
    private final int maxUlps;

    /**
     * Cut-off value for entries in the tableau: values smaller than the cut-off
     * are treated as zero to improve numerical stability.
     */
    private final double cutOff;

    /**
     * The pivot selection method to use.
     */
    private PivotSelectionRule pivotSelection;

    /**
     * The solution callback to access the best solution found so far in case
     * the optimizer fails to find an optimal solution within the iteration limits.
     */
    private SolutionCallback solutionCallback;

    /**
     * Builds a simplex solver with default settings.
     */
    public SimplexSolver() {
        this(DEFAULT_EPSILON, DEFAULT_ULPS, DEFAULT_CUT_OFF);
    }

    /**
     * Builds a simplex solver with a specified accepted amount of error.
     *
     * @param epsilon Amount of error to accept for algorithm convergence.
     */
    public SimplexSolver(final double epsilon) {
        this(epsilon, DEFAULT_ULPS, DEFAULT_CUT_OFF);
    }

    /**
     * Builds a simplex solver with a specified accepted amount of error.
     *
     * @param epsilon Amount of error to accept for algorithm convergence.
     * @param maxUlps Amount of error to accept in floating point comparisons.
     */
    public SimplexSolver(final double epsilon, final int maxUlps) {
        this(epsilon, maxUlps, DEFAULT_CUT_OFF);
    }

    /**
     * Builds a simplex solver with a specified accepted amount of error.
     *
     * @param epsilon Amount of error to accept for algorithm convergence.
     * @param maxUlps Amount of error to accept in floating point comparisons.
     * @param cutOff Values smaller than the cutOff are treated as zero.
     */
    public SimplexSolver(final double epsilon, final int maxUlps, final double cutOff) {
        this.epsilon = epsilon;
        this.maxUlps = maxUlps;
        this.cutOff = cutOff;
        this.pivotSelection = PivotSelectionRule.DANTZIG;
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link LinearOptimizer#optimize(OptimizationData...)
     * LinearOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link SolutionCallback}</li>
     *  <li>{@link PivotSelectionRule}</li>
     * </ul>
     *
     * @return {@inheritDoc}
     * @throws TooManyIterationsException if the maximal number of iterations is exceeded.
     */
    @Override
    public PointValuePair optimize(OptimizationData... optData) throws TooManyIterationsException {
        // Set up base class and perform computation.
        return super.optimize(optData);
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data.
     * In addition to those documented in
     * {@link LinearOptimizer#parseOptimizationData(OptimizationData[])
     * LinearOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link SolutionCallback}</li>
     *  <li>{@link PivotSelectionRule}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // Allow base class to register its own data.
        super.parseOptimizationData(optData);
        // reset the callback before parsing
        solutionCallback = null;
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.parseOptimizationData_169");
            if (data instanceof SolutionCallback) {
                solutionCallback = (SolutionCallback) data;
                continue;
            }
            if (data instanceof PivotSelectionRule) {
                pivotSelection = (PivotSelectionRule) data;
                continue;
            }
        }
    }

    /**
     * Returns the column with the most negative coefficient in the objective function row.
     *
     * @param tableau Simple tableau for the problem.
     * @return the column with the most negative coefficient.
     */
    private Integer getPivotColumn(SimplexTableau tableau) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotColumn_195");
        double minValue = 0;
        Integer minPos = null;
        for (int i = tableau.getNumObjectiveFunctions(); ROR_less(i, AOR_minus(tableau.getWidth(), 1, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotColumn_195", _mut59864, _mut59865, _mut59866, _mut59867), "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotColumn_195", _mut59868, _mut59869, _mut59870, _mut59871, _mut59872); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotColumn_195");
            final double entry = tableau.getEntry(0, i);
            // do not use a ulp/epsilon check
            if (ROR_less(entry, minValue, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotColumn_195", _mut59858, _mut59859, _mut59860, _mut59861, _mut59862)) {
                minValue = entry;
                minPos = i;
                // Bland's rule: chose the entering column with the lowest index
                if ((_mut59863 ? (pivotSelection == PivotSelectionRule.BLAND || isValidPivotColumn(tableau, i)) : (pivotSelection == PivotSelectionRule.BLAND && isValidPivotColumn(tableau, i)))) {
                    break;
                }
            }
        }
        return minPos;
    }

    /**
     * Checks whether the given column is valid pivot column, i.e. will result
     * in a valid pivot row.
     * <p>
     * When applying Bland's rule to select the pivot column, it may happen that
     * there is no corresponding pivot row. This method will check if the selected
     * pivot column will return a valid pivot row.
     *
     * @param tableau simplex tableau for the problem
     * @param col the column to test
     * @return {@code true} if the pivot column is valid, {@code false} otherwise
     */
    private boolean isValidPivotColumn(SimplexTableau tableau, int col) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.isValidPivotColumn_227");
        for (int i = tableau.getNumObjectiveFunctions(); ROR_less(i, tableau.getHeight(), "org.apache.commons.math3.optim.linear.SimplexSolver.isValidPivotColumn_227", _mut59878, _mut59879, _mut59880, _mut59881, _mut59882); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.isValidPivotColumn_227");
            final double entry = tableau.getEntry(i, col);
            // do the same check as in getPivotRow
            if (ROR_greater(Precision.compareTo(entry, 0d, cutOff), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.isValidPivotColumn_227", _mut59873, _mut59874, _mut59875, _mut59876, _mut59877)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the row with the minimum ratio as given by the minimum ratio test (MRT).
     *
     * @param tableau Simplex tableau for the problem.
     * @param col Column to test the ratio of (see {@link #getPivotColumn(SimplexTableau)}).
     * @return the row with the minimum ratio.
     */
    private Integer getPivotRow(SimplexTableau tableau, final int col) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246");
        // create a list of all the rows that tie for the lowest score in the minimum ratio test
        List<Integer> minRatioPositions = new ArrayList<Integer>();
        double minRatio = Double.MAX_VALUE;
        for (int i = tableau.getNumObjectiveFunctions(); ROR_less(i, tableau.getHeight(), "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59906, _mut59907, _mut59908, _mut59909, _mut59910); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246");
            final double rhs = tableau.getEntry(i, AOR_minus(tableau.getWidth(), 1, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59883, _mut59884, _mut59885, _mut59886));
            final double entry = tableau.getEntry(i, col);
            // selecting others may lead to degeneracy or numerical instabilities
            if (ROR_greater(Precision.compareTo(entry, 0d, cutOff), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59887, _mut59888, _mut59889, _mut59890, _mut59891)) {
                final double ratio = FastMath.abs(AOR_divide(rhs, entry, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59892, _mut59893, _mut59894, _mut59895));
                // do not use a ulp/epsilon check
                final int cmp = Double.compare(ratio, minRatio);
                if (ROR_equals(cmp, 0, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59896, _mut59897, _mut59898, _mut59899, _mut59900)) {
                    minRatioPositions.add(i);
                } else if (ROR_less(cmp, 0, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59901, _mut59902, _mut59903, _mut59904, _mut59905)) {
                    minRatio = ratio;
                    minRatioPositions.clear();
                    minRatioPositions.add(i);
                }
            }
        }
        if (ROR_equals(minRatioPositions.size(), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59911, _mut59912, _mut59913, _mut59914, _mut59915)) {
            return null;
        } else if (ROR_greater(minRatioPositions.size(), 1, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59916, _mut59917, _mut59918, _mut59919, _mut59920)) {
            // 1. check if there's an artificial variable that can be forced out of the basis
            if (ROR_greater(tableau.getNumArtificialVariables(), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59921, _mut59922, _mut59923, _mut59924, _mut59925)) {
                for (Integer row : minRatioPositions) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246");
                    for (int i = 0; ROR_less(i, tableau.getNumArtificialVariables(), "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59931, _mut59932, _mut59933, _mut59934, _mut59935); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246");
                        int column = AOR_plus(i, tableau.getArtificialVariableOffset(), "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59926, _mut59927, _mut59928, _mut59929);
                        final double entry = tableau.getEntry(row, column);
                        if ((_mut59930 ? (Precision.equals(entry, 1d, maxUlps) || row.equals(tableau.getBasicRow(column))) : (Precision.equals(entry, 1d, maxUlps) && row.equals(tableau.getBasicRow(column))))) {
                            return row;
                        }
                    }
                }
            }
            Integer minRow = null;
            int minIndex = tableau.getWidth();
            for (Integer row : minRatioPositions) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246");
                final int basicVar = tableau.getBasicVariable(row);
                if (ROR_less(basicVar, minIndex, "org.apache.commons.math3.optim.linear.SimplexSolver.getPivotRow_246", _mut59936, _mut59937, _mut59938, _mut59939, _mut59940)) {
                    minIndex = basicVar;
                    minRow = row;
                }
            }
            return minRow;
        }
        return minRatioPositions.get(0);
    }

    /**
     * Runs one iteration of the Simplex method on the given model.
     *
     * @param tableau Simple tableau for the problem.
     * @throws TooManyIterationsException if the allowed number of iterations has been exhausted.
     * @throws UnboundedSolutionException if the model is found not to have a bounded solution.
     */
    protected void doIteration(final SimplexTableau tableau) throws TooManyIterationsException, UnboundedSolutionException {
        incrementIterationCount();
        Integer pivotCol = getPivotColumn(tableau);
        Integer pivotRow = getPivotRow(tableau, pivotCol);
        if (pivotRow == null) {
            throw new UnboundedSolutionException();
        }
        tableau.performRowOperations(pivotCol, pivotRow);
    }

    /**
     * Solves Phase 1 of the Simplex method.
     *
     * @param tableau Simple tableau for the problem.
     * @throws TooManyIterationsException if the allowed number of iterations has been exhausted.
     * @throws UnboundedSolutionException if the model is found not to have a bounded solution.
     * @throws NoFeasibleSolutionException if there is no feasible solution?
     */
    protected void solvePhase1(final SimplexTableau tableau) throws TooManyIterationsException, UnboundedSolutionException, NoFeasibleSolutionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.solvePhase1_339");
        // make sure we're in Phase 1
        if (ROR_equals(tableau.getNumArtificialVariables(), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.solvePhase1_339", _mut59941, _mut59942, _mut59943, _mut59944, _mut59945)) {
            return;
        }
        while (!tableau.isOptimal()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.solvePhase1_339");
            doIteration(tableau);
        }
        // if W is not zero then we have no feasible solution
        if (!Precision.equals(tableau.getEntry(0, tableau.getRhsOffset()), 0d, epsilon)) {
            throw new NoFeasibleSolutionException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PointValuePair doOptimize() throws TooManyIterationsException, UnboundedSolutionException, NoFeasibleSolutionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.doOptimize_360");
        // we do not pass phase 1 successfully
        if (solutionCallback != null) {
            solutionCallback.setTableau(null);
        }
        final SimplexTableau tableau = new SimplexTableau(getFunction(), getConstraints(), getGoalType(), isRestrictedToNonNegative(), epsilon, maxUlps);
        solvePhase1(tableau);
        tableau.dropPhase1Objective();
        // after phase 1, we are sure to have a feasible solution
        if (solutionCallback != null) {
            solutionCallback.setTableau(tableau);
        }
        while (!tableau.isOptimal()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.doOptimize_360");
            doIteration(tableau);
        }
        // find a non-valid solution (with negative coefficients).
        final PointValuePair solution = tableau.getSolution();
        if (isRestrictedToNonNegative()) {
            final double[] coeff = solution.getPoint();
            for (int i = 0; ROR_less(i, coeff.length, "org.apache.commons.math3.optim.linear.SimplexSolver.doOptimize_360", _mut59951, _mut59952, _mut59953, _mut59954, _mut59955); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.SimplexSolver.doOptimize_360");
                if (ROR_less(Precision.compareTo(coeff[i], 0, epsilon), 0, "org.apache.commons.math3.optim.linear.SimplexSolver.doOptimize_360", _mut59946, _mut59947, _mut59948, _mut59949, _mut59950)) {
                    throw new NoFeasibleSolutionException();
                }
            }
        }
        return solution;
    }
}
