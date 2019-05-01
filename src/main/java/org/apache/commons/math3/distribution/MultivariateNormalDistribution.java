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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the multivariate normal (Gaussian) distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Multivariate_normal_distribution">
 * Multivariate normal distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/MultivariateNormalDistribution.html">
 * Multivariate normal distribution (MathWorld)</a>
 *
 * @since 3.1
 */
public class MultivariateNormalDistribution extends AbstractMultivariateRealDistribution {

    @Conditional
    public static boolean _mut55788 = false, _mut55789 = false, _mut55790 = false, _mut55791 = false, _mut55792 = false, _mut55793 = false, _mut55794 = false, _mut55795 = false, _mut55796 = false, _mut55797 = false, _mut55798 = false, _mut55799 = false, _mut55800 = false, _mut55801 = false, _mut55802 = false, _mut55803 = false, _mut55804 = false, _mut55805 = false, _mut55806 = false, _mut55807 = false, _mut55808 = false, _mut55809 = false, _mut55810 = false, _mut55811 = false, _mut55812 = false, _mut55813 = false, _mut55814 = false, _mut55815 = false, _mut55816 = false, _mut55817 = false, _mut55818 = false, _mut55819 = false, _mut55820 = false, _mut55821 = false, _mut55822 = false, _mut55823 = false, _mut55824 = false, _mut55825 = false, _mut55826 = false, _mut55827 = false, _mut55828 = false, _mut55829 = false, _mut55830 = false, _mut55831 = false, _mut55832 = false, _mut55833 = false, _mut55834 = false, _mut55835 = false, _mut55836 = false, _mut55837 = false, _mut55838 = false, _mut55839 = false, _mut55840 = false, _mut55841 = false, _mut55842 = false, _mut55843 = false, _mut55844 = false, _mut55845 = false, _mut55846 = false, _mut55847 = false, _mut55848 = false, _mut55849 = false, _mut55850 = false, _mut55851 = false, _mut55852 = false, _mut55853 = false, _mut55854 = false, _mut55855 = false, _mut55856 = false, _mut55857 = false, _mut55858 = false, _mut55859 = false, _mut55860 = false, _mut55861 = false, _mut55862 = false, _mut55863 = false, _mut55864 = false, _mut55865 = false, _mut55866 = false, _mut55867 = false, _mut55868 = false, _mut55869 = false, _mut55870 = false, _mut55871 = false, _mut55872 = false, _mut55873 = false, _mut55874 = false, _mut55875 = false, _mut55876 = false, _mut55877 = false, _mut55878 = false, _mut55879 = false, _mut55880 = false, _mut55881 = false, _mut55882 = false, _mut55883 = false, _mut55884 = false, _mut55885 = false;

    /**
     * Vector of means.
     */
    private final double[] means;

    /**
     * Covariance matrix.
     */
    private final RealMatrix covarianceMatrix;

    /**
     * The matrix inverse of the covariance matrix.
     */
    private final RealMatrix covarianceMatrixInverse;

    /**
     * The determinant of the covariance matrix.
     */
    private final double covarianceMatrixDeterminant;

    /**
     * Matrix used in computation of samples.
     */
    private final RealMatrix samplingMatrix;

    /**
     * Creates a multivariate normal distribution with the given mean vector and
     * covariance matrix.
     * <br/>
     * The number of dimensions is equal to the length of the mean vector
     * and to the number of rows and columns of the covariance matrix.
     * It is frequently written as "p" in formulae.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param means Vector of means.
     * @param covariances Covariance matrix.
     * @throws DimensionMismatchException if the arrays length are
     * inconsistent.
     * @throws SingularMatrixException if the eigenvalue decomposition cannot
     * be performed on the provided covariance matrix.
     * @throws NonPositiveDefiniteMatrixException if any of the eigenvalues is
     * negative.
     */
    public MultivariateNormalDistribution(final double[] means, final double[][] covariances) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        this(new Well19937c(), means, covariances);
    }

    /**
     * Creates a multivariate normal distribution with the given mean vector and
     * covariance matrix.
     * <br/>
     * The number of dimensions is equal to the length of the mean vector
     * and to the number of rows and columns of the covariance matrix.
     * It is frequently written as "p" in formulae.
     *
     * @param rng Random Number Generator.
     * @param means Vector of means.
     * @param covariances Covariance matrix.
     * @throws DimensionMismatchException if the arrays length are
     * inconsistent.
     * @throws SingularMatrixException if the eigenvalue decomposition cannot
     * be performed on the provided covariance matrix.
     * @throws NonPositiveDefiniteMatrixException if any of the eigenvalues is
     * negative.
     */
    public MultivariateNormalDistribution(RandomGenerator rng, final double[] means, final double[][] covariances) throws SingularMatrixException, DimensionMismatchException, NonPositiveDefiniteMatrixException {
        super(rng, means.length);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
        final int dim = means.length;
        if (ROR_not_equals(covariances.length, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55788, _mut55789, _mut55790, _mut55791, _mut55792)) {
            throw new DimensionMismatchException(covariances.length, dim);
        }
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55798, _mut55799, _mut55800, _mut55801, _mut55802); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
            if (ROR_not_equals(dim, covariances[i].length, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55793, _mut55794, _mut55795, _mut55796, _mut55797)) {
                throw new DimensionMismatchException(covariances[i].length, dim);
            }
        }
        this.means = MathArrays.copyOf(means);
        covarianceMatrix = new Array2DRowRealMatrix(covariances);
        // Covariance matrix eigen decomposition.
        final EigenDecomposition covMatDec = new EigenDecomposition(covarianceMatrix);
        // Compute and store the inverse.
        covarianceMatrixInverse = covMatDec.getSolver().getInverse();
        // Compute and store the determinant.
        covarianceMatrixDeterminant = covMatDec.getDeterminant();
        // Eigenvalues of the covariance matrix.
        final double[] covMatEigenvalues = covMatDec.getRealEigenvalues();
        for (int i = 0; ROR_less(i, covMatEigenvalues.length, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55808, _mut55809, _mut55810, _mut55811, _mut55812); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
            if (ROR_less(covMatEigenvalues[i], 0, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55803, _mut55804, _mut55805, _mut55806, _mut55807)) {
                throw new NonPositiveDefiniteMatrixException(covMatEigenvalues[i], i, 0);
            }
        }
        // Matrix where each column is an eigenvector of the covariance matrix.
        final Array2DRowRealMatrix covMatEigenvectors = new Array2DRowRealMatrix(dim, dim);
        for (int v = 0; ROR_less(v, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55813, _mut55814, _mut55815, _mut55816, _mut55817); v++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
            final double[] evec = covMatDec.getEigenvector(v).toArray();
            covMatEigenvectors.setColumn(v, evec);
        }
        final RealMatrix tmpMatrix = covMatEigenvectors.transpose();
        // Scale each eigenvector by the square root of its eigenvalue.
        for (int row = 0; ROR_less(row, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55823, _mut55824, _mut55825, _mut55826, _mut55827); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
            final double factor = FastMath.sqrt(covMatEigenvalues[row]);
            for (int col = 0; ROR_less(col, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103", _mut55818, _mut55819, _mut55820, _mut55821, _mut55822); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.MultivariateNormalDistribution_103");
                tmpMatrix.multiplyEntry(row, col, factor);
            }
        }
        samplingMatrix = covMatEigenvectors.multiply(tmpMatrix);
    }

    /**
     * Gets the mean vector.
     *
     * @return the mean vector.
     */
    public double[] getMeans() {
        return MathArrays.copyOf(means);
    }

    /**
     * Gets the covariance matrix.
     *
     * @return the covariance matrix.
     */
    public RealMatrix getCovariances() {
        return covarianceMatrix.copy();
    }

    /**
     * {@inheritDoc}
     */
    public double density(final double[] vals) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183");
        final int dim = getDimension();
        if (ROR_not_equals(vals.length, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183", _mut55828, _mut55829, _mut55830, _mut55831, _mut55832)) {
            throw new DimensionMismatchException(vals.length, dim);
        }
        return AOR_multiply(AOR_multiply(FastMath.pow(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183", _mut55833, _mut55834, _mut55835, _mut55836), AOR_multiply(-0.5, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183", _mut55837, _mut55838, _mut55839, _mut55840)), FastMath.pow(covarianceMatrixDeterminant, -0.5), "org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183", _mut55841, _mut55842, _mut55843, _mut55844), getExponentTerm(vals), "org.apache.commons.math3.distribution.MultivariateNormalDistribution.density_183", _mut55845, _mut55846, _mut55847, _mut55848);
    }

    /**
     * Gets the square root of each element on the diagonal of the covariance
     * matrix.
     *
     * @return the standard deviations.
     */
    public double[] getStandardDeviations() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.getStandardDeviations_200");
        final int dim = getDimension();
        final double[] std = new double[dim];
        final double[][] s = covarianceMatrix.getData();
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getStandardDeviations_200", _mut55849, _mut55850, _mut55851, _mut55852, _mut55853); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.getStandardDeviations_200");
            std[i] = FastMath.sqrt(s[i][i]);
        }
        return std;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.sample_211");
        final int dim = getDimension();
        final double[] normalVals = new double[dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.sample_211", _mut55854, _mut55855, _mut55856, _mut55857, _mut55858); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.sample_211");
            normalVals[i] = random.nextGaussian();
        }
        final double[] vals = samplingMatrix.operate(normalVals);
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.sample_211", _mut55859, _mut55860, _mut55861, _mut55862, _mut55863); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.sample_211");
            vals[i] += means[i];
        }
        return vals;
    }

    /**
     * Computes the term used in the exponent (see definition of the distribution).
     *
     * @param values Values at which to compute density.
     * @return the multiplication factor of density calculations.
     */
    private double getExponentTerm(final double[] values) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235");
        final double[] centered = new double[values.length];
        for (int i = 0; ROR_less(i, centered.length, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235", _mut55868, _mut55869, _mut55870, _mut55871, _mut55872); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235");
            centered[i] = AOR_minus(values[i], getMeans()[i], "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235", _mut55864, _mut55865, _mut55866, _mut55867);
        }
        final double[] preMultiplied = covarianceMatrixInverse.preMultiply(centered);
        double sum = 0;
        for (int i = 0; ROR_less(i, preMultiplied.length, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235", _mut55877, _mut55878, _mut55879, _mut55880, _mut55881); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235");
            sum += AOR_multiply(preMultiplied[i], centered[i], "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235", _mut55873, _mut55874, _mut55875, _mut55876);
        }
        return FastMath.exp(AOR_multiply(-0.5, sum, "org.apache.commons.math3.distribution.MultivariateNormalDistribution.getExponentTerm_235", _mut55882, _mut55883, _mut55884, _mut55885));
    }
}
