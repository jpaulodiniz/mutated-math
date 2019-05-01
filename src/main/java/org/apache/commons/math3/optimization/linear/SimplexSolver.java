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
package org.apache.commons.math3.optimization.linear;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Solves a linear problem using the Two-Phase Simplex Method.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class SimplexSolver extends AbstractLinearOptimizer {

    @Conditional
    public static boolean _mut71397 = false, _mut71398 = false, _mut71399 = false, _mut71400 = false, _mut71401 = false, _mut71402 = false, _mut71403 = false, _mut71404 = false, _mut71405 = false, _mut71406 = false, _mut71407 = false, _mut71408 = false, _mut71409 = false, _mut71410 = false, _mut71411 = false, _mut71412 = false, _mut71413 = false, _mut71414 = false, _mut71415 = false, _mut71416 = false, _mut71417 = false, _mut71418 = false, _mut71419 = false, _mut71420 = false, _mut71421 = false, _mut71422 = false, _mut71423 = false, _mut71424 = false, _mut71425 = false, _mut71426 = false, _mut71427 = false, _mut71428 = false, _mut71429 = false, _mut71430 = false, _mut71431 = false, _mut71432 = false, _mut71433 = false, _mut71434 = false, _mut71435 = false, _mut71436 = false, _mut71437 = false, _mut71438 = false, _mut71439 = false, _mut71440 = false, _mut71441 = false, _mut71442 = false, _mut71443 = false, _mut71444 = false, _mut71445 = false, _mut71446 = false, _mut71447 = false, _mut71448 = false, _mut71449 = false, _mut71450 = false, _mut71451 = false, _mut71452 = false, _mut71453 = false, _mut71454 = false, _mut71455 = false, _mut71456 = false, _mut71457 = false, _mut71458 = false, _mut71459 = false, _mut71460 = false, _mut71461 = false, _mut71462 = false, _mut71463 = false, _mut71464 = false, _mut71465 = false, _mut71466 = false, _mut71467 = false, _mut71468 = false, _mut71469 = false, _mut71470 = false, _mut71471 = false, _mut71472 = false, _mut71473 = false, _mut71474 = false, _mut71475 = false, _mut71476 = false, _mut71477 = false, _mut71478 = false, _mut71479 = false, _mut71480 = false, _mut71481 = false, _mut71482 = false, _mut71483 = false, _mut71484 = false, _mut71485 = false, _mut71486 = false, _mut71487 = false, _mut71488 = false, _mut71489 = false, _mut71490 = false, _mut71491 = false, _mut71492 = false, _mut71493 = false, _mut71494 = false, _mut71495 = false, _mut71496 = false, _mut71497 = false, _mut71498 = false, _mut71499 = false, _mut71500 = false, _mut71501 = false, _mut71502 = false, _mut71503 = false, _mut71504 = false;

    /**
     * Default amount of error to accept for algorithm convergence.
     */
    private static final double DEFAULT_EPSILON = 1.0e-6;

    /**
     * Default amount of error to accept in floating point comparisons (as ulps).
     */
    private static final int DEFAULT_ULPS = 10;

    /**
     * Amount of error to accept for algorithm convergence.
     */
    private final double epsilon;

    /**
     * Amount of error to accept in floating point comparisons (as ulps).
     */
    private final int maxUlps;

    /**
     * Build a simplex solver with default settings.
     */
    public SimplexSolver() {
        this(DEFAULT_EPSILON, DEFAULT_ULPS);
    }

    /**
     * Build a simplex solver with a specified accepted amount of error
     * @param epsilon the amount of error to accept for algorithm convergence
     * @param maxUlps amount of error to accept in floating point comparisons
     */
    public SimplexSolver(final double epsilon, final int maxUlps) {
        this.epsilon = epsilon;
        this.maxUlps = maxUlps;
    }

    /**
     * Returns the column with the most negative coefficient in the objective function row.
     * @param tableau simple tableau for the problem
     * @return column with the most negative coefficient
     */
    private Integer getPivotColumn(SimplexTableau tableau) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotColumn_71");
        double minValue = 0;
        Integer minPos = null;
        for (int i = tableau.getNumObjectiveFunctions(); ROR_less(i, AOR_minus(tableau.getWidth(), 1, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotColumn_71", _mut71402, _mut71403, _mut71404, _mut71405), "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotColumn_71", _mut71406, _mut71407, _mut71408, _mut71409, _mut71410); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotColumn_71");
            final double entry = tableau.getEntry(0, i);
            // do not use a ulp/epsilon check
            if (ROR_less(entry, minValue, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotColumn_71", _mut71397, _mut71398, _mut71399, _mut71400, _mut71401)) {
                minValue = entry;
                minPos = i;
            }
        }
        return minPos;
    }

    /**
     * Returns the row with the minimum ratio as given by the minimum ratio test (MRT).
     * @param tableau simple tableau for the problem
     * @param col the column to test the ratio of.  See {@link #getPivotColumn(SimplexTableau)}
     * @return row with the minimum ratio
     */
    private Integer getPivotRow(SimplexTableau tableau, final int col) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
        // create a list of all the rows that tie for the lowest score in the minimum ratio test
        List<Integer> minRatioPositions = new ArrayList<Integer>();
        double minRatio = Double.MAX_VALUE;
        for (int i = tableau.getNumObjectiveFunctions(); ROR_less(i, tableau.getHeight(), "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71434, _mut71435, _mut71436, _mut71437, _mut71438); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
            final double rhs = tableau.getEntry(i, AOR_minus(tableau.getWidth(), 1, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71411, _mut71412, _mut71413, _mut71414));
            final double entry = tableau.getEntry(i, col);
            if (ROR_greater(Precision.compareTo(entry, 0d, maxUlps), 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71415, _mut71416, _mut71417, _mut71418, _mut71419)) {
                final double ratio = AOR_divide(rhs, entry, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71420, _mut71421, _mut71422, _mut71423);
                // do not use a ulp/epsilon check
                final int cmp = Double.compare(ratio, minRatio);
                if (ROR_equals(cmp, 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71424, _mut71425, _mut71426, _mut71427, _mut71428)) {
                    minRatioPositions.add(i);
                } else if (ROR_less(cmp, 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71429, _mut71430, _mut71431, _mut71432, _mut71433)) {
                    minRatio = ratio;
                    minRatioPositions = new ArrayList<Integer>();
                    minRatioPositions.add(i);
                }
            }
        }
        if (ROR_equals(minRatioPositions.size(), 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71439, _mut71440, _mut71441, _mut71442, _mut71443)) {
            return null;
        } else if (ROR_greater(minRatioPositions.size(), 1, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71444, _mut71445, _mut71446, _mut71447, _mut71448)) {
            // 1. check if there's an artificial variable that can be forced out of the basis
            if (ROR_greater(tableau.getNumArtificialVariables(), 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71449, _mut71450, _mut71451, _mut71452, _mut71453)) {
                for (Integer row : minRatioPositions) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
                    for (int i = 0; ROR_less(i, tableau.getNumArtificialVariables(), "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71459, _mut71460, _mut71461, _mut71462, _mut71463); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
                        int column = AOR_plus(i, tableau.getArtificialVariableOffset(), "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71454, _mut71455, _mut71456, _mut71457);
                        final double entry = tableau.getEntry(row, column);
                        if ((_mut71458 ? (Precision.equals(entry, 1d, maxUlps) || row.equals(tableau.getBasicRow(column))) : (Precision.equals(entry, 1d, maxUlps) && row.equals(tableau.getBasicRow(column))))) {
                            return row;
                        }
                    }
                }
            }
            // This heuristic is based on empirical data gathered while investigating MATH-828.
            if (ROR_less(getIterations(), AOR_divide(getMaxIterations(), 2, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71464, _mut71465, _mut71466, _mut71467), "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71468, _mut71469, _mut71470, _mut71471, _mut71472)) {
                Integer minRow = null;
                int minIndex = tableau.getWidth();
                final int varStart = tableau.getNumObjectiveFunctions();
                final int varEnd = AOR_minus(tableau.getWidth(), 1, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71473, _mut71474, _mut71475, _mut71476);
                for (Integer row : minRatioPositions) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
                    for (int i = varStart; (_mut71489 ? (ROR_less(i, varEnd, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71484, _mut71485, _mut71486, _mut71487, _mut71488) || !row.equals(minRow)) : (ROR_less(i, varEnd, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71484, _mut71485, _mut71486, _mut71487, _mut71488) && !row.equals(minRow))); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92");
                        final Integer basicRow = tableau.getBasicRow(i);
                        if ((_mut71483 ? ((_mut71477 ? (basicRow != null || basicRow.equals(row)) : (basicRow != null && basicRow.equals(row))) || ROR_less(i, minIndex, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71478, _mut71479, _mut71480, _mut71481, _mut71482)) : ((_mut71477 ? (basicRow != null || basicRow.equals(row)) : (basicRow != null && basicRow.equals(row))) && ROR_less(i, minIndex, "org.apache.commons.math3.optimization.linear.SimplexSolver.getPivotRow_92", _mut71478, _mut71479, _mut71480, _mut71481, _mut71482)))) {
                            minIndex = i;
                            minRow = row;
                        }
                    }
                }
                return minRow;
            }
        }
        return minRatioPositions.get(0);
    }

    /**
     * Runs one iteration of the Simplex method on the given model.
     * @param tableau simple tableau for the problem
     * @throws MaxCountExceededException if the maximal iteration count has been exceeded
     * @throws UnboundedSolutionException if the model is found not to have a bounded solution
     */
    protected void doIteration(final SimplexTableau tableau) throws MaxCountExceededException, UnboundedSolutionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.doIteration_168");
        incrementIterationsCounter();
        Integer pivotCol = getPivotColumn(tableau);
        Integer pivotRow = getPivotRow(tableau, pivotCol);
        if (pivotRow == null) {
            throw new UnboundedSolutionException();
        }
        // set the pivot element to 1
        double pivotVal = tableau.getEntry(pivotRow, pivotCol);
        tableau.divideRow(pivotRow, pivotVal);
        // set the rest of the pivot column to 0
        for (int i = 0; ROR_less(i, tableau.getHeight(), "org.apache.commons.math3.optimization.linear.SimplexSolver.doIteration_168", _mut71495, _mut71496, _mut71497, _mut71498, _mut71499); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.doIteration_168");
            if (ROR_not_equals(i, pivotRow, "org.apache.commons.math3.optimization.linear.SimplexSolver.doIteration_168", _mut71490, _mut71491, _mut71492, _mut71493, _mut71494)) {
                final double multiplier = tableau.getEntry(i, pivotCol);
                tableau.subtractRow(i, pivotRow, multiplier);
            }
        }
    }

    /**
     * Solves Phase 1 of the Simplex method.
     * @param tableau simple tableau for the problem
     * @throws MaxCountExceededException if the maximal iteration count has been exceeded
     * @throws UnboundedSolutionException if the model is found not to have a bounded solution
     * @throws NoFeasibleSolutionException if there is no feasible solution
     */
    protected void solvePhase1(final SimplexTableau tableau) throws MaxCountExceededException, UnboundedSolutionException, NoFeasibleSolutionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.solvePhase1_199");
        // make sure we're in Phase 1
        if (ROR_equals(tableau.getNumArtificialVariables(), 0, "org.apache.commons.math3.optimization.linear.SimplexSolver.solvePhase1_199", _mut71500, _mut71501, _mut71502, _mut71503, _mut71504)) {
            return;
        }
        while (!tableau.isOptimal()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.solvePhase1_199");
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
    public PointValuePair doOptimize() throws MaxCountExceededException, UnboundedSolutionException, NoFeasibleSolutionException {
        final SimplexTableau tableau = new SimplexTableau(getFunction(), getConstraints(), getGoalType(), restrictToNonNegative(), epsilon, maxUlps);
        solvePhase1(tableau);
        tableau.dropPhase1Objective();
        while (!tableau.isOptimal()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.SimplexSolver.doOptimize_218");
            doIteration(tableau);
        }
        return tableau.getSolution();
    }
}
