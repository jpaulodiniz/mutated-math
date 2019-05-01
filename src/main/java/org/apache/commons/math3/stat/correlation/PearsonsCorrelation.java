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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes Pearson's product-moment correlation coefficients for pairs of arrays
 * or columns of a matrix.
 *
 * <p>The constructors that take <code>RealMatrix</code> or
 * <code>double[][]</code> arguments generate correlation matrices.  The
 * columns of the input matrices are assumed to represent variable values.
 * Correlations are given by the formula</p>
 *
 * <p><code>cor(X, Y) = &Sigma;[(x<sub>i</sub> - E(X))(y<sub>i</sub> - E(Y))] / [(n - 1)s(X)s(Y)]</code>
 * where <code>E(X)</code> is the mean of <code>X</code>, <code>E(Y)</code>
 * is the mean of the <code>Y</code> values and s(X), s(Y) are standard deviations.</p>
 *
 * <p>To compute the correlation coefficient for a single pair of arrays, use {@link #PearsonsCorrelation()}
 * to construct an instance with no data and then {@link #correlation(double[], double[])}.
 * Correlation matrices can also be computed directly from an instance with no data using
 * {@link #computeCorrelationMatrix(double[][])}. In order to use {@link #getCorrelationMatrix()},
 * {@link #getCorrelationPValues()},  or {@link #getCorrelationStandardErrors()}; however, one of the
 * constructors supplying data or a covariance matrix must be used to create the instance.</p>
 *
 * @since 2.0
 */
public class PearsonsCorrelation {

    @Conditional
    public static boolean _mut11068 = false, _mut11069 = false, _mut11070 = false, _mut11071 = false, _mut11072 = false, _mut11073 = false, _mut11074 = false, _mut11075 = false, _mut11076 = false, _mut11077 = false, _mut11078 = false, _mut11079 = false, _mut11080 = false, _mut11081 = false, _mut11082 = false, _mut11083 = false, _mut11084 = false, _mut11085 = false, _mut11086 = false, _mut11087 = false, _mut11088 = false, _mut11089 = false, _mut11090 = false, _mut11091 = false, _mut11092 = false, _mut11093 = false, _mut11094 = false, _mut11095 = false, _mut11096 = false, _mut11097 = false, _mut11098 = false, _mut11099 = false, _mut11100 = false, _mut11101 = false, _mut11102 = false, _mut11103 = false, _mut11104 = false, _mut11105 = false, _mut11106 = false, _mut11107 = false, _mut11108 = false, _mut11109 = false, _mut11110 = false, _mut11111 = false, _mut11112 = false, _mut11113 = false, _mut11114 = false, _mut11115 = false, _mut11116 = false, _mut11117 = false, _mut11118 = false, _mut11119 = false, _mut11120 = false, _mut11121 = false, _mut11122 = false, _mut11123 = false, _mut11124 = false, _mut11125 = false, _mut11126 = false, _mut11127 = false, _mut11128 = false, _mut11129 = false, _mut11130 = false, _mut11131 = false, _mut11132 = false, _mut11133 = false, _mut11134 = false, _mut11135 = false, _mut11136 = false, _mut11137 = false, _mut11138 = false, _mut11139 = false, _mut11140 = false, _mut11141 = false, _mut11142 = false, _mut11143 = false, _mut11144 = false, _mut11145 = false, _mut11146 = false, _mut11147 = false, _mut11148 = false, _mut11149 = false, _mut11150 = false, _mut11151 = false, _mut11152 = false, _mut11153 = false, _mut11154 = false, _mut11155 = false, _mut11156 = false, _mut11157 = false, _mut11158 = false, _mut11159 = false, _mut11160 = false, _mut11161 = false, _mut11162 = false, _mut11163 = false, _mut11164 = false, _mut11165 = false, _mut11166 = false, _mut11167 = false, _mut11168 = false, _mut11169 = false, _mut11170 = false, _mut11171 = false, _mut11172 = false, _mut11173 = false, _mut11174 = false, _mut11175 = false, _mut11176 = false, _mut11177 = false, _mut11178 = false, _mut11179 = false, _mut11180 = false, _mut11181 = false, _mut11182 = false, _mut11183 = false, _mut11184 = false, _mut11185 = false, _mut11186 = false, _mut11187 = false, _mut11188 = false, _mut11189 = false, _mut11190 = false;

    /**
     * correlation matrix
     */
    private final RealMatrix correlationMatrix;

    /**
     * number of observations
     */
    private final int nObs;

    /**
     * Create a PearsonsCorrelation instance without data.
     */
    public PearsonsCorrelation() {
        super();
        correlationMatrix = null;
        nObs = 0;
    }

    /**
     * Create a PearsonsCorrelation from a rectangular array
     * whose columns represent values of variables to be correlated.
     *
     * Throws MathIllegalArgumentException if the input array does not have at least
     * two columns and two rows.  Pairwise correlations are set to NaN if one
     * of the correlates has zero variance.
     *
     * @param data rectangular array with columns representing variables
     * @throws MathIllegalArgumentException if the input data array is not
     * rectangular with at least two rows and two columns.
     * @see #correlation(double[], double[])
     */
    public PearsonsCorrelation(double[][] data) {
        this(new BlockRealMatrix(data));
    }

    /**
     * Create a PearsonsCorrelation from a RealMatrix whose columns
     * represent variables to be correlated.
     *
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * two columns and two rows.  Pairwise correlations are set to NaN if one
     * of the correlates has zero variance.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     * @see #correlation(double[], double[])
     */
    public PearsonsCorrelation(RealMatrix matrix) {
        nObs = matrix.getRowDimension();
        correlationMatrix = computeCorrelationMatrix(matrix);
    }

    /**
     * Create a PearsonsCorrelation from a {@link Covariance}.  The correlation
     * matrix is computed by scaling the Covariance's covariance matrix.
     * The Covariance instance must have been created from a data matrix with
     * columns representing variable values.
     *
     * @param covariance Covariance instance
     */
    public PearsonsCorrelation(Covariance covariance) {
        RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
        if (covarianceMatrix == null) {
            throw new NullArgumentException(LocalizedFormats.COVARIANCE_MATRIX);
        }
        nObs = covariance.getN();
        correlationMatrix = covarianceToCorrelation(covarianceMatrix);
    }

    /**
     * Create a PearsonsCorrelation from a covariance matrix. The correlation
     * matrix is computed by scaling the covariance matrix.
     *
     * @param covarianceMatrix covariance matrix
     * @param numberOfObservations the number of observations in the dataset used to compute
     * the covariance matrix
     */
    public PearsonsCorrelation(RealMatrix covarianceMatrix, int numberOfObservations) {
        nObs = numberOfObservations;
        correlationMatrix = covarianceToCorrelation(covarianceMatrix);
    }

    /**
     * Returns the correlation matrix.
     *
     * <p>This method will return null if the argumentless constructor was used
     * to create this instance, even if {@link #computeCorrelationMatrix(double[][])}
     * has been called before it is activated.</p>
     *
     * @return correlation matrix
     */
    public RealMatrix getCorrelationMatrix() {
        return correlationMatrix;
    }

    /**
     * Returns a matrix of standard errors associated with the estimates
     * in the correlation matrix.<br/>
     * <code>getCorrelationStandardErrors().getEntry(i,j)</code> is the standard
     * error associated with <code>getCorrelationMatrix.getEntry(i,j)</code>
     *
     * <p>The formula used to compute the standard error is <br/>
     * <code>SE<sub>r</sub> = ((1 - r<sup>2</sup>) / (n - 2))<sup>1/2</sup></code>
     * where <code>r</code> is the estimated correlation coefficient and
     * <code>n</code> is the number of observations in the source dataset.</p>
     *
     * <p>To use this method, one of the constructors that supply an input
     * matrix must have been used to create this instance.</p>
     *
     * @return matrix of correlation standard errors
     * @throws NullPointerException if this instance was created with no data
     */
    public RealMatrix getCorrelationStandardErrors() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162");
        int nVars = correlationMatrix.getColumnDimension();
        double[][] out = new double[nVars][nVars];
        for (int i = 0; ROR_less(i, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11089, _mut11090, _mut11091, _mut11092, _mut11093); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162");
            for (int j = 0; ROR_less(j, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11084, _mut11085, _mut11086, _mut11087, _mut11088); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162");
                double r = correlationMatrix.getEntry(i, j);
                out[i][j] = FastMath.sqrt(AOR_divide((AOR_minus(1, AOR_multiply(r, r, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11068, _mut11069, _mut11070, _mut11071), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11072, _mut11073, _mut11074, _mut11075)), (AOR_minus(nObs, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11076, _mut11077, _mut11078, _mut11079)), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationStandardErrors_162", _mut11080, _mut11081, _mut11082, _mut11083));
            }
        }
        return new BlockRealMatrix(out);
    }

    /**
     * Returns a matrix of p-values associated with the (two-sided) null
     * hypothesis that the corresponding correlation coefficient is zero.
     *
     * <p><code>getCorrelationPValues().getEntry(i,j)</code> is the probability
     * that a random variable distributed as <code>t<sub>n-2</sub></code> takes
     * a value with absolute value greater than or equal to <br>
     * <code>|r|((n - 2) / (1 - r<sup>2</sup>))<sup>1/2</sup></code></p>
     *
     * <p>The values in the matrix are sometimes referred to as the
     * <i>significance</i> of the corresponding correlation coefficients.</p>
     *
     * <p>To use this method, one of the constructors that supply an input
     * matrix must have been used to create this instance.</p>
     *
     * @return matrix of p-values
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if an error occurs estimating probabilities
     * @throws NullPointerException if this instance was created with no data
     */
    public RealMatrix getCorrelationPValues() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194");
        TDistribution tDistribution = new TDistribution(AOR_minus(nObs, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11094, _mut11095, _mut11096, _mut11097));
        int nVars = correlationMatrix.getColumnDimension();
        double[][] out = new double[nVars][nVars];
        for (int i = 0; ROR_less(i, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11132, _mut11133, _mut11134, _mut11135, _mut11136); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194");
            for (int j = 0; ROR_less(j, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11127, _mut11128, _mut11129, _mut11130, _mut11131); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194");
                if (ROR_equals(i, j, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11098, _mut11099, _mut11100, _mut11101, _mut11102)) {
                    out[i][j] = 0d;
                } else {
                    double r = correlationMatrix.getEntry(i, j);
                    double t = FastMath.abs(AOR_multiply(r, FastMath.sqrt(AOR_divide((AOR_minus(nObs, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11103, _mut11104, _mut11105, _mut11106)), (AOR_minus(1, AOR_multiply(r, r, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11107, _mut11108, _mut11109, _mut11110), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11111, _mut11112, _mut11113, _mut11114)), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11115, _mut11116, _mut11117, _mut11118)), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11119, _mut11120, _mut11121, _mut11122));
                    out[i][j] = AOR_multiply(2, tDistribution.cumulativeProbability(-t), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.getCorrelationPValues_194", _mut11123, _mut11124, _mut11125, _mut11126);
                }
            }
        }
        return new BlockRealMatrix(out);
    }

    /**
     * Computes the correlation matrix for the columns of the
     * input matrix, using {@link #correlation(double[], double[])}.
     *
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * two columns and two rows.  Pairwise correlations are set to NaN if one
     * of the correlates has zero variance.
     *
     * @param matrix matrix with columns representing variables to correlate
     * @return correlation matrix
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     * @see #correlation(double[], double[])
     */
    public RealMatrix computeCorrelationMatrix(RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.computeCorrelationMatrix_226");
        checkSufficientData(matrix);
        int nVars = matrix.getColumnDimension();
        RealMatrix outMatrix = new BlockRealMatrix(nVars, nVars);
        for (int i = 0; ROR_less(i, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.computeCorrelationMatrix_226", _mut11142, _mut11143, _mut11144, _mut11145, _mut11146); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.computeCorrelationMatrix_226");
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.computeCorrelationMatrix_226", _mut11137, _mut11138, _mut11139, _mut11140, _mut11141); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.computeCorrelationMatrix_226");
                double corr = correlation(matrix.getColumn(i), matrix.getColumn(j));
                outMatrix.setEntry(i, j, corr);
                outMatrix.setEntry(j, i, corr);
            }
            outMatrix.setEntry(i, i, 1d);
        }
        return outMatrix;
    }

    /**
     * Computes the correlation matrix for the columns of the
     * input rectangular array.  The columns of the array represent values
     * of variables to be correlated.
     *
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * two columns and two rows or if the array is not rectangular. Pairwise
     * correlations are set to NaN if one of the correlates has zero variance.
     *
     * @param data matrix with columns representing variables to correlate
     * @return correlation matrix
     * @throws MathIllegalArgumentException if the array does not contain sufficient data
     * @see #correlation(double[], double[])
     */
    public RealMatrix computeCorrelationMatrix(double[][] data) {
        return computeCorrelationMatrix(new BlockRealMatrix(data));
    }

    /**
     * Computes the Pearson's product-moment correlation coefficient between two arrays.
     *
     * <p>Throws MathIllegalArgumentException if the arrays do not have the same length
     * or their common length is less than 2.  Returns {@code NaN} if either of the arrays
     * has zero variance (i.e., if one of the arrays does not contain at least two distinct
     * values).</p>
     *
     * @param xArray first data array
     * @param yArray second data array
     * @return Returns Pearson's correlation coefficient for the two arrays
     * @throws DimensionMismatchException if the arrays lengths do not match
     * @throws MathIllegalArgumentException if there is insufficient data
     */
    public double correlation(final double[] xArray, final double[] yArray) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.correlation_273");
        SimpleRegression regression = new SimpleRegression();
        if (ROR_not_equals(xArray.length, yArray.length, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.correlation_273", _mut11147, _mut11148, _mut11149, _mut11150, _mut11151)) {
            throw new DimensionMismatchException(xArray.length, yArray.length);
        } else if (ROR_less(xArray.length, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.correlation_273", _mut11152, _mut11153, _mut11154, _mut11155, _mut11156)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, xArray.length, 2);
        } else {
            for (int i = 0; ROR_less(i, xArray.length, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.correlation_273", _mut11157, _mut11158, _mut11159, _mut11160, _mut11161); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.correlation_273");
                regression.addData(xArray[i], yArray[i]);
            }
            return regression.getR();
        }
    }

    /**
     * Derives a correlation matrix from a covariance matrix.
     *
     * <p>Uses the formula <br/>
     * <code>r(X,Y) = cov(X,Y)/s(X)s(Y)</code> where
     * <code>r(&middot,&middot;)</code> is the correlation coefficient and
     * <code>s(&middot;)</code> means standard deviation.</p>
     *
     * @param covarianceMatrix the covariance matrix
     * @return correlation matrix
     */
    public RealMatrix covarianceToCorrelation(RealMatrix covarianceMatrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299");
        int nVars = covarianceMatrix.getColumnDimension();
        RealMatrix outMatrix = new BlockRealMatrix(nVars, nVars);
        for (int i = 0; ROR_less(i, nVars, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299", _mut11175, _mut11176, _mut11177, _mut11178, _mut11179); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299");
            double sigma = FastMath.sqrt(covarianceMatrix.getEntry(i, i));
            outMatrix.setEntry(i, i, 1d);
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299", _mut11170, _mut11171, _mut11172, _mut11173, _mut11174); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299");
                double entry = AOR_divide(covarianceMatrix.getEntry(i, j), (AOR_multiply(sigma, FastMath.sqrt(covarianceMatrix.getEntry(j, j)), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299", _mut11162, _mut11163, _mut11164, _mut11165)), "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.covarianceToCorrelation_299", _mut11166, _mut11167, _mut11168, _mut11169);
                outMatrix.setEntry(i, j, entry);
                outMatrix.setEntry(j, i, entry);
            }
        }
        return outMatrix;
    }

    /**
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * two columns and two rows.
     *
     * @param matrix matrix to check for sufficiency
     * @throws MathIllegalArgumentException if there is insufficient data
     */
    private void checkSufficientData(final RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.PearsonsCorrelation.checkSufficientData_322");
        int nRows = matrix.getRowDimension();
        int nCols = matrix.getColumnDimension();
        if ((_mut11190 ? (ROR_less(nRows, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.checkSufficientData_322", _mut11180, _mut11181, _mut11182, _mut11183, _mut11184) && ROR_less(nCols, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.checkSufficientData_322", _mut11185, _mut11186, _mut11187, _mut11188, _mut11189)) : (ROR_less(nRows, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.checkSufficientData_322", _mut11180, _mut11181, _mut11182, _mut11183, _mut11184) || ROR_less(nCols, 2, "org.apache.commons.math3.stat.correlation.PearsonsCorrelation.checkSufficientData_322", _mut11185, _mut11186, _mut11187, _mut11188, _mut11189)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, nRows, nCols);
        }
    }
}
