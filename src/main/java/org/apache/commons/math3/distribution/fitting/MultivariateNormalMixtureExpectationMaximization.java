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
package org.apache.commons.math3.distribution.fitting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Expectation-Maximization</a> algorithm for fitting the parameters of
 * multivariate normal mixture model distributions.
 *
 * This implementation is pure original code based on <a
 * href="https://www.ee.washington.edu/techsite/papers/documents/UWEETR-2010-0002.pdf">
 * EM Demystified: An Expectation-Maximization Tutorial</a> by Yihua Chen and Maya R. Gupta,
 * Department of Electrical Engineering, University of Washington, Seattle, WA 98195.
 * It was verified using external tools like <a
 * href="http://cran.r-project.org/web/packages/mixtools/index.html">CRAN Mixtools</a>
 * (see the JUnit test cases) but it is <strong>not</strong> based on Mixtools code at all.
 * The discussion of the origin of this class can be seen in the comments of the <a
 * href="https://issues.apache.org/jira/browse/MATH-817">MATH-817</a> JIRA issue.
 * @since 3.2
 */
public class MultivariateNormalMixtureExpectationMaximization {

    @Conditional
    public static boolean _mut54528 = false, _mut54529 = false, _mut54530 = false, _mut54531 = false, _mut54532 = false, _mut54533 = false, _mut54534 = false, _mut54535 = false, _mut54536 = false, _mut54537 = false, _mut54538 = false, _mut54539 = false, _mut54540 = false, _mut54541 = false, _mut54542 = false, _mut54543 = false, _mut54544 = false, _mut54545 = false, _mut54546 = false, _mut54547 = false, _mut54548 = false, _mut54549 = false, _mut54550 = false, _mut54551 = false, _mut54552 = false, _mut54553 = false, _mut54554 = false, _mut54555 = false, _mut54556 = false, _mut54557 = false, _mut54558 = false, _mut54559 = false, _mut54560 = false, _mut54561 = false, _mut54562 = false, _mut54563 = false, _mut54564 = false, _mut54565 = false, _mut54566 = false, _mut54567 = false, _mut54568 = false, _mut54569 = false, _mut54570 = false, _mut54571 = false, _mut54572 = false, _mut54573 = false, _mut54574 = false, _mut54575 = false, _mut54576 = false, _mut54577 = false, _mut54578 = false, _mut54579 = false, _mut54580 = false, _mut54581 = false, _mut54582 = false, _mut54583 = false, _mut54584 = false, _mut54585 = false, _mut54586 = false, _mut54587 = false, _mut54588 = false, _mut54589 = false, _mut54590 = false, _mut54591 = false, _mut54592 = false, _mut54593 = false, _mut54594 = false, _mut54595 = false, _mut54596 = false, _mut54597 = false, _mut54598 = false, _mut54599 = false, _mut54600 = false, _mut54601 = false, _mut54602 = false, _mut54603 = false, _mut54604 = false, _mut54605 = false, _mut54606 = false, _mut54607 = false, _mut54608 = false, _mut54609 = false, _mut54610 = false, _mut54611 = false, _mut54612 = false, _mut54613 = false, _mut54614 = false, _mut54615 = false, _mut54616 = false, _mut54617 = false, _mut54618 = false, _mut54619 = false, _mut54620 = false, _mut54621 = false, _mut54622 = false, _mut54623 = false, _mut54624 = false, _mut54625 = false, _mut54626 = false, _mut54627 = false, _mut54628 = false, _mut54629 = false, _mut54630 = false, _mut54631 = false, _mut54632 = false, _mut54633 = false, _mut54634 = false, _mut54635 = false, _mut54636 = false, _mut54637 = false, _mut54638 = false, _mut54639 = false, _mut54640 = false, _mut54641 = false, _mut54642 = false, _mut54643 = false, _mut54644 = false, _mut54645 = false, _mut54646 = false, _mut54647 = false, _mut54648 = false, _mut54649 = false, _mut54650 = false, _mut54651 = false, _mut54652 = false, _mut54653 = false, _mut54654 = false, _mut54655 = false, _mut54656 = false, _mut54657 = false, _mut54658 = false, _mut54659 = false, _mut54660 = false, _mut54661 = false, _mut54662 = false, _mut54663 = false, _mut54664 = false, _mut54665 = false, _mut54666 = false, _mut54667 = false, _mut54668 = false, _mut54669 = false, _mut54670 = false, _mut54671 = false, _mut54672 = false, _mut54673 = false, _mut54674 = false, _mut54675 = false, _mut54676 = false, _mut54677 = false, _mut54678 = false, _mut54679 = false, _mut54680 = false, _mut54681 = false, _mut54682 = false, _mut54683 = false, _mut54684 = false, _mut54685 = false, _mut54686 = false, _mut54687 = false, _mut54688 = false, _mut54689 = false, _mut54690 = false, _mut54691 = false, _mut54692 = false, _mut54693 = false, _mut54694 = false, _mut54695 = false, _mut54696 = false, _mut54697 = false, _mut54698 = false, _mut54699 = false, _mut54700 = false, _mut54701 = false, _mut54702 = false, _mut54703 = false, _mut54704 = false, _mut54705 = false, _mut54706 = false, _mut54707 = false, _mut54708 = false, _mut54709 = false, _mut54710 = false, _mut54711 = false, _mut54712 = false, _mut54713 = false, _mut54714 = false, _mut54715 = false, _mut54716 = false, _mut54717 = false, _mut54718 = false, _mut54719 = false, _mut54720 = false, _mut54721 = false, _mut54722 = false, _mut54723 = false, _mut54724 = false, _mut54725 = false, _mut54726 = false, _mut54727 = false, _mut54728 = false, _mut54729 = false, _mut54730 = false, _mut54731 = false, _mut54732 = false, _mut54733 = false, _mut54734 = false, _mut54735 = false, _mut54736 = false;

    /**
     * Default maximum number of iterations allowed per fitting process.
     */
    private static final int DEFAULT_MAX_ITERATIONS = 1000;

    /**
     * Default convergence threshold for fitting.
     */
    private static final double DEFAULT_THRESHOLD = 1E-5;

    /**
     * The data to fit.
     */
    private final double[][] data;

    /**
     * The model fit against the data.
     */
    private MixtureMultivariateNormalDistribution fittedModel;

    /**
     * The log likelihood of the data given the fitted model.
     */
    private double logLikelihood = 0d;

    /**
     * Creates an object to fit a multivariate normal mixture model to data.
     *
     * @param data Data to use in fitting procedure
     * @throws NotStrictlyPositiveException if data has no rows
     * @throws DimensionMismatchException if rows of data have different numbers
     *             of columns
     * @throws NumberIsTooSmallException if the number of columns in the data is
     *             less than 2
     */
    public MultivariateNormalMixtureExpectationMaximization(double[][] data) throws NotStrictlyPositiveException, DimensionMismatchException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86");
        if (ROR_less(data.length, 1, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86", _mut54528, _mut54529, _mut54530, _mut54531, _mut54532)) {
            throw new NotStrictlyPositiveException(data.length);
        }
        this.data = new double[data.length][data[0].length];
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86", _mut54543, _mut54544, _mut54545, _mut54546, _mut54547); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86");
            if (ROR_not_equals(data[i].length, data[0].length, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86", _mut54533, _mut54534, _mut54535, _mut54536, _mut54537)) {
                // Jagged arrays not allowed
                throw new DimensionMismatchException(data[i].length, data[0].length);
            }
            if (ROR_less(data[i].length, 2, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.MultivariateNormalMixtureExpectationMaximization_86", _mut54538, _mut54539, _mut54540, _mut54541, _mut54542)) {
                throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, data[i].length, 2, true);
            }
            this.data[i] = MathArrays.copyOf(data[i], data[i].length);
        }
    }

    /**
     * Fit a mixture model to the data supplied to the constructor.
     *
     * The quality of the fit depends on the concavity of the data provided to
     * the constructor and the initial mixture provided to this function. If the
     * data has many local optima, multiple runs of the fitting function with
     * different initial mixtures may be required to find the optimal solution.
     * If a SingularMatrixException is encountered, it is possible that another
     * initialization would work.
     *
     * @param initialMixture Model containing initial values of weights and
     *            multivariate normals
     * @param maxIterations Maximum iterations allowed for fit
     * @param threshold Convergence threshold computed as difference in
     *             logLikelihoods between successive iterations
     * @throws SingularMatrixException if any component's covariance matrix is
     *             singular during fitting
     * @throws NotStrictlyPositiveException if numComponents is less than one
     *             or threshold is less than Double.MIN_VALUE
     * @throws DimensionMismatchException if initialMixture mean vector and data
     *             number of columns are not equal
     */
    public void fit(final MixtureMultivariateNormalDistribution initialMixture, final int maxIterations, final double threshold) throws SingularMatrixException, NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
        if (ROR_less(maxIterations, 1, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54548, _mut54549, _mut54550, _mut54551, _mut54552)) {
            throw new NotStrictlyPositiveException(maxIterations);
        }
        if (ROR_less(threshold, Double.MIN_VALUE, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54553, _mut54554, _mut54555, _mut54556, _mut54557)) {
            throw new NotStrictlyPositiveException(threshold);
        }
        final int n = data.length;
        // so we can assume the lengths of each row are equal.
        final int numCols = data[0].length;
        final int k = initialMixture.getComponents().size();
        final int numMeanColumns = initialMixture.getComponents().get(0).getSecond().getMeans().length;
        if (ROR_not_equals(numMeanColumns, numCols, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54558, _mut54559, _mut54560, _mut54561, _mut54562)) {
            throw new DimensionMismatchException(numMeanColumns, numCols);
        }
        int numIterations = 0;
        double previousLogLikelihood = 0d;
        logLikelihood = Double.NEGATIVE_INFINITY;
        // Initialize model to fit to initial mixture.
        fittedModel = new MixtureMultivariateNormalDistribution(initialMixture.getComponents());
        while ((_mut54655 ? (ROR_less_equals(numIterations++, maxIterations, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54641, _mut54642, _mut54643, _mut54644, _mut54645) || ROR_greater(FastMath.abs(AOR_minus(previousLogLikelihood, logLikelihood, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54646, _mut54647, _mut54648, _mut54649)), threshold, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54650, _mut54651, _mut54652, _mut54653, _mut54654)) : (ROR_less_equals(numIterations++, maxIterations, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54641, _mut54642, _mut54643, _mut54644, _mut54645) && ROR_greater(FastMath.abs(AOR_minus(previousLogLikelihood, logLikelihood, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54646, _mut54647, _mut54648, _mut54649)), threshold, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54650, _mut54651, _mut54652, _mut54653, _mut54654)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
            previousLogLikelihood = logLikelihood;
            double sumLogLikelihood = 0d;
            // Mixture components
            final List<Pair<Double, MultivariateNormalDistribution>> components = fittedModel.getComponents();
            // Weight and distribution of each component
            final double[] weights = new double[k];
            final MultivariateNormalDistribution[] mvns = new MultivariateNormalDistribution[k];
            for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54563, _mut54564, _mut54565, _mut54566, _mut54567); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                weights[j] = components.get(j).getFirst();
                mvns[j] = components.get(j).getSecond();
            }
            // component
            final double[][] gamma = new double[n][k];
            // Sum of gamma for each component
            final double[] gammaSums = new double[k];
            // Sum of gamma times its row for each each component
            final double[][] gammaDataProdSums = new double[k][numCols];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54590, _mut54591, _mut54592, _mut54593, _mut54594); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                final double rowDensity = fittedModel.density(data[i]);
                sumLogLikelihood += FastMath.log(rowDensity);
                for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54585, _mut54586, _mut54587, _mut54588, _mut54589); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                    gamma[i][j] = AOR_divide(AOR_multiply(weights[j], mvns[j].density(data[i]), "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54568, _mut54569, _mut54570, _mut54571), rowDensity, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54572, _mut54573, _mut54574, _mut54575);
                    gammaSums[j] += gamma[i][j];
                    for (int col = 0; ROR_less(col, numCols, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54580, _mut54581, _mut54582, _mut54583, _mut54584); col++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                        gammaDataProdSums[j][col] += AOR_multiply(gamma[i][j], data[i][col], "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54576, _mut54577, _mut54578, _mut54579);
                    }
                }
            }
            logLikelihood = AOR_divide(sumLogLikelihood, n, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54595, _mut54596, _mut54597, _mut54598);
            // function.
            final double[] newWeights = new double[k];
            final double[][] newMeans = new double[k][numCols];
            for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54612, _mut54613, _mut54614, _mut54615, _mut54616); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                newWeights[j] = AOR_divide(gammaSums[j], n, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54599, _mut54600, _mut54601, _mut54602);
                for (int col = 0; ROR_less(col, numCols, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54607, _mut54608, _mut54609, _mut54610, _mut54611); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                    newMeans[j][col] = AOR_divide(gammaDataProdSums[j][col], gammaSums[j], "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54603, _mut54604, _mut54605, _mut54606);
                }
            }
            // Compute new covariance matrices
            final RealMatrix[] newCovMats = new RealMatrix[k];
            for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54617, _mut54618, _mut54619, _mut54620, _mut54621); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                newCovMats[j] = new Array2DRowRealMatrix(numCols, numCols);
            }
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54627, _mut54628, _mut54629, _mut54630, _mut54631); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54622, _mut54623, _mut54624, _mut54625, _mut54626); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                    final RealMatrix vec = new Array2DRowRealMatrix(MathArrays.ebeSubtract(data[i], newMeans[j]));
                    final RealMatrix dataCov = vec.multiply(vec.transpose()).scalarMultiply(gamma[i][j]);
                    newCovMats[j] = newCovMats[j].add(dataCov);
                }
            }
            // Converting to arrays for use by fitted model
            final double[][][] newCovMatArrays = new double[k][numCols][numCols];
            for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54636, _mut54637, _mut54638, _mut54639, _mut54640); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132");
                newCovMats[j] = newCovMats[j].scalarMultiply(AOR_divide(1d, gammaSums[j], "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54632, _mut54633, _mut54634, _mut54635));
                newCovMatArrays[j] = newCovMats[j].getData();
            }
            // Update current model
            fittedModel = new MixtureMultivariateNormalDistribution(newWeights, newMeans, newCovMatArrays);
        }
        if (ROR_greater(FastMath.abs(AOR_minus(previousLogLikelihood, logLikelihood, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54656, _mut54657, _mut54658, _mut54659)), threshold, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.fit_132", _mut54660, _mut54661, _mut54662, _mut54663, _mut54664)) {
            // Did not converge before the maximum number of iterations
            throw new ConvergenceException();
        }
    }

    /**
     * Fit a mixture model to the data supplied to the constructor.
     *
     * The quality of the fit depends on the concavity of the data provided to
     * the constructor and the initial mixture provided to this function. If the
     * data has many local optima, multiple runs of the fitting function with
     * different initial mixtures may be required to find the optimal solution.
     * If a SingularMatrixException is encountered, it is possible that another
     * initialization would work.
     *
     * @param initialMixture Model containing initial values of weights and
     *            multivariate normals
     * @throws SingularMatrixException if any component's covariance matrix is
     *             singular during fitting
     * @throws NotStrictlyPositiveException if numComponents is less than one or
     *             threshold is less than Double.MIN_VALUE
     */
    public void fit(MixtureMultivariateNormalDistribution initialMixture) throws SingularMatrixException, NotStrictlyPositiveException {
        fit(initialMixture, DEFAULT_MAX_ITERATIONS, DEFAULT_THRESHOLD);
    }

    /**
     * Helper method to create a multivariate normal mixture model which can be
     * used to initialize {@link #fit(MixtureMultivariateNormalDistribution)}.
     *
     * This method uses the data supplied to the constructor to try to determine
     * a good mixture model at which to start the fit, but it is not guaranteed
     * to supply a model which will find the optimal solution or even converge.
     *
     * @param data Data to estimate distribution
     * @param numComponents Number of components for estimated mixture
     * @return Multivariate normal mixture model estimated from the data
     * @throws NumberIsTooLargeException if {@code numComponents} is greater
     * than the number of data rows.
     * @throws NumberIsTooSmallException if {@code numComponents < 2}.
     * @throws NotStrictlyPositiveException if data has less than 2 rows
     * @throws DimensionMismatchException if rows of data have different numbers
     *             of columns
     */
    public static MixtureMultivariateNormalDistribution estimate(final double[][] data, final int numComponents) throws NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302");
        if (ROR_less(data.length, 2, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54665, _mut54666, _mut54667, _mut54668, _mut54669)) {
            throw new NotStrictlyPositiveException(data.length);
        }
        if (ROR_less(numComponents, 2, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54670, _mut54671, _mut54672, _mut54673, _mut54674)) {
            throw new NumberIsTooSmallException(numComponents, 2, true);
        }
        if (ROR_greater(numComponents, data.length, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54675, _mut54676, _mut54677, _mut54678, _mut54679)) {
            throw new NumberIsTooLargeException(numComponents, data.length, true);
        }
        final int numRows = data.length;
        final int numCols = data[0].length;
        // sort the data
        final DataRow[] sortedData = new DataRow[numRows];
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54680, _mut54681, _mut54682, _mut54683, _mut54684); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302");
            sortedData[i] = new DataRow(data[i]);
        }
        Arrays.sort(sortedData);
        // uniform weight for each bin
        final double weight = AOR_divide(1d, numComponents, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54685, _mut54686, _mut54687, _mut54688);
        // components of mixture model to be created
        final List<Pair<Double, MultivariateNormalDistribution>> components = new ArrayList<Pair<Double, MultivariateNormalDistribution>>(numComponents);
        // create a component based on data in each bin
        for (int binIndex = 0; ROR_less(binIndex, numComponents, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54727, _mut54728, _mut54729, _mut54730, _mut54731); binIndex++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302");
            // minimum index (inclusive) from sorted data for this bin
            final int minIndex = AOR_divide((AOR_multiply(binIndex, numRows, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54689, _mut54690, _mut54691, _mut54692)), numComponents, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54693, _mut54694, _mut54695, _mut54696);
            // maximum index (exclusive) from sorted data for this bin
            final int maxIndex = AOR_divide((AOR_multiply((AOR_plus(binIndex, 1, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54697, _mut54698, _mut54699, _mut54700)), numRows, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54701, _mut54702, _mut54703, _mut54704)), numComponents, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54705, _mut54706, _mut54707, _mut54708);
            // number of data records that will be in this bin
            final int numBinRows = AOR_minus(maxIndex, minIndex, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54709, _mut54710, _mut54711, _mut54712);
            // data for this bin
            final double[][] binData = new double[numBinRows][numCols];
            // mean of each column for the data in the this bin
            final double[] columnMeans = new double[numCols];
            // populate bin and create component
            for (int i = minIndex, iBin = 0; ROR_less(i, maxIndex, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54718, _mut54719, _mut54720, _mut54721, _mut54722); i++, iBin++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302");
                for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54713, _mut54714, _mut54715, _mut54716, _mut54717); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302");
                    final double val = sortedData[i].getRow()[j];
                    columnMeans[j] += val;
                    binData[iBin][j] = val;
                }
            }
            MathArrays.scaleInPlace(AOR_divide(1d, numBinRows, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.estimate_302", _mut54723, _mut54724, _mut54725, _mut54726), columnMeans);
            // covariance matrix for this bin
            final double[][] covMat = new Covariance(binData).getCovarianceMatrix().getData();
            final MultivariateNormalDistribution mvn = new MultivariateNormalDistribution(columnMeans, covMat);
            components.add(new Pair<Double, MultivariateNormalDistribution>(weight, mvn));
        }
        return new MixtureMultivariateNormalDistribution(components);
    }

    /**
     * Gets the log likelihood of the data under the fitted model.
     *
     * @return Log likelihood of data or zero of no data has been fit
     */
    public double getLogLikelihood() {
        return logLikelihood;
    }

    /**
     * Gets the fitted model.
     *
     * @return fitted model or {@code null} if no fit has been performed yet.
     */
    public MixtureMultivariateNormalDistribution getFittedModel() {
        return new MixtureMultivariateNormalDistribution(fittedModel.getComponents());
    }

    /**
     * Class used for sorting user-supplied data.
     */
    private static class DataRow implements Comparable<DataRow> {

        /**
         * One data row.
         */
        private final double[] row;

        /**
         * Mean of the data row.
         */
        private Double mean;

        /**
         * Create a data row.
         * @param data Data to use for the row
         */
        DataRow(final double[] data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow_404");
            // Store reference.
            row = data;
            // Compute mean.
            mean = 0d;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow_404", _mut54732, _mut54733, _mut54734, _mut54735, _mut54736); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization.DataRow_404");
                mean += data[i];
            }
            mean /= data.length;
        }

        /**
         * Compare two data rows.
         * @param other The other row
         * @return int for sorting
         */
        public int compareTo(final DataRow other) {
            return mean.compareTo(other.mean);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof DataRow) {
                return MathArrays.equals(row, ((DataRow) other).row);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Arrays.hashCode(row);
        }

        /**
         * Get a data row.
         * @return data row array
         */
        public double[] getRow() {
            return row;
        }
    }
}
