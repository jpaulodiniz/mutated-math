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
package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Abstract base class for implementations of MultipleLinearRegression.
 * @since 2.0
 */
public abstract class AbstractMultipleLinearRegression implements MultipleLinearRegression {

    @Conditional
    public static boolean _mut7919 = false, _mut7920 = false, _mut7921 = false, _mut7922 = false, _mut7923 = false, _mut7924 = false, _mut7925 = false, _mut7926 = false, _mut7927 = false, _mut7928 = false, _mut7929 = false, _mut7930 = false, _mut7931 = false, _mut7932 = false, _mut7933 = false, _mut7934 = false, _mut7935 = false, _mut7936 = false, _mut7937 = false, _mut7938 = false, _mut7939 = false, _mut7940 = false, _mut7941 = false, _mut7942 = false, _mut7943 = false, _mut7944 = false, _mut7945 = false, _mut7946 = false, _mut7947 = false, _mut7948 = false, _mut7949 = false, _mut7950 = false, _mut7951 = false, _mut7952 = false, _mut7953 = false, _mut7954 = false, _mut7955 = false, _mut7956 = false, _mut7957 = false, _mut7958 = false, _mut7959 = false, _mut7960 = false, _mut7961 = false, _mut7962 = false, _mut7963 = false, _mut7964 = false, _mut7965 = false, _mut7966 = false, _mut7967 = false, _mut7968 = false, _mut7969 = false, _mut7970 = false, _mut7971 = false, _mut7972 = false, _mut7973 = false, _mut7974 = false, _mut7975 = false, _mut7976 = false, _mut7977 = false, _mut7978 = false, _mut7979 = false, _mut7980 = false, _mut7981 = false, _mut7982 = false, _mut7983 = false, _mut7984 = false, _mut7985 = false, _mut7986 = false, _mut7987 = false, _mut7988 = false, _mut7989 = false, _mut7990 = false, _mut7991 = false, _mut7992 = false, _mut7993 = false, _mut7994 = false, _mut7995 = false, _mut7996 = false, _mut7997 = false, _mut7998 = false, _mut7999 = false, _mut8000 = false, _mut8001 = false, _mut8002 = false, _mut8003 = false, _mut8004 = false, _mut8005 = false, _mut8006 = false, _mut8007 = false, _mut8008 = false, _mut8009 = false, _mut8010 = false, _mut8011 = false, _mut8012 = false, _mut8013 = false, _mut8014 = false, _mut8015 = false, _mut8016 = false, _mut8017 = false, _mut8018 = false, _mut8019 = false, _mut8020 = false, _mut8021 = false, _mut8022 = false, _mut8023 = false, _mut8024 = false, _mut8025 = false, _mut8026 = false, _mut8027 = false, _mut8028 = false, _mut8029 = false, _mut8030 = false, _mut8031 = false, _mut8032 = false, _mut8033 = false, _mut8034 = false, _mut8035 = false, _mut8036 = false, _mut8037 = false, _mut8038 = false, _mut8039 = false;

    /**
     * X sample data.
     */
    private RealMatrix xMatrix;

    /**
     * Y sample data.
     */
    private RealVector yVector;

    /**
     * Whether or not the regression model includes an intercept.  True means no intercept.
     */
    private boolean noIntercept = false;

    /**
     * @return the X sample data.
     */
    protected RealMatrix getX() {
        return xMatrix;
    }

    /**
     * @return the Y sample data.
     */
    protected RealVector getY() {
        return yVector;
    }

    /**
     * @return true if the model has no intercept term; false otherwise
     * @since 2.2
     */
    public boolean isNoIntercept() {
        return noIntercept;
    }

    /**
     * @param noIntercept true means the model is to be estimated without an intercept term
     * @since 2.2
     */
    public void setNoIntercept(boolean noIntercept) {
        this.noIntercept = noIntercept;
    }

    /**
     * <p>Loads model x and y sample data from a flat input array, overriding any previous sample.
     * </p>
     * <p>Assumes that rows are concatenated with y values first in each row.  For example, an input
     * <code>data</code> array containing the sequence of values (1, 2, 3, 4, 5, 6, 7, 8, 9) with
     * <code>nobs = 3</code> and <code>nvars = 2</code> creates a regression dataset with two
     * independent variables, as below:
     * <pre>
     *   y   x[0]  x[1]
     *   --------------
     *   1     2     3
     *   4     5     6
     *   7     8     9
     * </pre>
     * </p>
     * <p>Note that there is no need to add an initial unitary column (column of 1's) when
     * specifying a model including an intercept term.  If {@link #isNoIntercept()} is <code>true</code>,
     * the X matrix will be created without an initial column of "1"s; otherwise this column will
     * be added.
     * </p>
     * <p>Throws IllegalArgumentException if any of the following preconditions fail:
     * <ul><li><code>data</code> cannot be null</li>
     * <li><code>data.length = nobs * (nvars + 1)</li>
     * <li><code>nobs > nvars</code></li></ul>
     * </p>
     *
     * @param data input data array
     * @param nobs number of observations (rows)
     * @param nvars number of independent variables (columns, not counting y)
     * @throws NullArgumentException if the data array is null
     * @throws DimensionMismatchException if the length of the data array is not equal
     * to <code>nobs * (nvars + 1)</code>
     * @throws InsufficientDataException if <code>nobs</code> is less than
     * <code>nvars + 1</code>
     */
    public void newSampleData(double[] data, int nobs, int nvars) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114");
        if (data == null) {
            throw new NullArgumentException();
        }
        if (ROR_not_equals(data.length, AOR_multiply(nobs, (AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7919, _mut7920, _mut7921, _mut7922)), "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7923, _mut7924, _mut7925, _mut7926), "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7927, _mut7928, _mut7929, _mut7930, _mut7931)) {
            throw new DimensionMismatchException(data.length, AOR_multiply(nobs, (AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7932, _mut7933, _mut7934, _mut7935)), "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7936, _mut7937, _mut7938, _mut7939));
        }
        if (ROR_less_equals(nobs, nvars, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7940, _mut7941, _mut7942, _mut7943, _mut7944)) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, nobs, AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7945, _mut7946, _mut7947, _mut7948));
        }
        double[] y = new double[nobs];
        final int cols = noIntercept ? nvars : AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7949, _mut7950, _mut7951, _mut7952);
        double[][] x = new double[nobs][cols];
        int pointer = 0;
        for (int i = 0; ROR_less(i, nobs, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7958, _mut7959, _mut7960, _mut7961, _mut7962); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114");
            y[i] = data[pointer++];
            if (!noIntercept) {
                x[i][0] = 1.0d;
            }
            for (int j = noIntercept ? 0 : 1; ROR_less(j, cols, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114", _mut7953, _mut7954, _mut7955, _mut7956, _mut7957); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newSampleData_114");
                x[i][j] = data[pointer++];
            }
        }
        this.xMatrix = new Array2DRowRealMatrix(x);
        this.yVector = new ArrayRealVector(y);
    }

    /**
     * Loads new y sample data, overriding any previous data.
     *
     * @param y the array representing the y sample
     * @throws NullArgumentException if y is null
     * @throws NoDataException if y is empty
     */
    protected void newYSampleData(double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newYSampleData_148");
        if (y == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(y.length, 0, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newYSampleData_148", _mut7963, _mut7964, _mut7965, _mut7966, _mut7967)) {
            throw new NoDataException();
        }
        this.yVector = new ArrayRealVector(y);
    }

    /**
     * <p>Loads new x sample data, overriding any previous data.
     * </p>
     * The input <code>x</code> array should have one row for each sample
     * observation, with columns corresponding to independent variables.
     * For example, if <pre>
     * <code> x = new double[][] {{1, 2}, {3, 4}, {5, 6}} </code></pre>
     * then <code>setXSampleData(x) </code> results in a model with two independent
     * variables and 3 observations:
     * <pre>
     *   x[0]  x[1]
     *   ----------
     *     1    2
     *     3    4
     *     5    6
     * </pre>
     * </p>
     * <p>Note that there is no need to add an initial unitary column (column of 1's) when
     * specifying a model including an intercept term.
     * </p>
     * @param x the rectangular array representing the x sample
     * @throws NullArgumentException if x is null
     * @throws NoDataException if x is empty
     * @throws DimensionMismatchException if x is not rectangular
     */
    protected void newXSampleData(double[][] x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183");
        if (x == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183", _mut7968, _mut7969, _mut7970, _mut7971, _mut7972)) {
            throw new NoDataException();
        }
        if (noIntercept) {
            this.xMatrix = new Array2DRowRealMatrix(x, true);
        } else {
            // Augment design matrix with initial unitary column
            final int nVars = x[0].length;
            final double[][] xAug = new double[x.length][AOR_plus(nVars, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183", _mut7973, _mut7974, _mut7975, _mut7976)];
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183", _mut7982, _mut7983, _mut7984, _mut7985, _mut7986); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183");
                if (ROR_not_equals(x[i].length, nVars, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.newXSampleData_183", _mut7977, _mut7978, _mut7979, _mut7980, _mut7981)) {
                    throw new DimensionMismatchException(x[i].length, nVars);
                }
                xAug[i][0] = 1.0d;
                System.arraycopy(x[i], 0, xAug[i], 1, nVars);
            }
            this.xMatrix = new Array2DRowRealMatrix(xAug, false);
        }
    }

    /**
     * Validates sample data.  Checks that
     * <ul><li>Neither x nor y is null or empty;</li>
     * <li>The length (i.e. number of rows) of x equals the length of y</li>
     * <li>x has at least one more row than it has columns (i.e. there is
     * sufficient data to estimate regression coefficients for each of the
     * columns in x plus an intercept.</li>
     * </ul>
     *
     * @param x the [n,k] array representing the x data
     * @param y the [n,1] array representing the y data
     * @throws NullArgumentException if {@code x} or {@code y} is null
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length
     * @throws NoDataException if {@code x} or {@code y} are zero-length
     * @throws MathIllegalArgumentException if the number of rows of {@code x}
     * is not larger than the number of columns + 1
     */
    protected void validateSampleData(double[][] x, double[] y) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateSampleData_224");
        if ((_mut7987 ? ((x == null) && (y == null)) : ((x == null) || (y == null)))) {
            throw new NullArgumentException();
        }
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateSampleData_224", _mut7988, _mut7989, _mut7990, _mut7991, _mut7992)) {
            throw new DimensionMismatchException(y.length, x.length);
        }
        if (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateSampleData_224", _mut7993, _mut7994, _mut7995, _mut7996, _mut7997)) {
            // Must be no y data either
            throw new NoDataException();
        }
        if (ROR_greater(AOR_plus(x[0].length, 1, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateSampleData_224", _mut7998, _mut7999, _mut8000, _mut8001), x.length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateSampleData_224", _mut8002, _mut8003, _mut8004, _mut8005, _mut8006)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, x.length, x[0].length);
        }
    }

    /**
     * Validates that the x data and covariance matrix have the same
     * number of rows and that the covariance matrix is square.
     *
     * @param x the [n,k] array representing the x sample
     * @param covariance the [n,n] array representing the covariance matrix
     * @throws DimensionMismatchException if the number of rows in x is not equal
     * to the number of rows in covariance
     * @throws NonSquareMatrixException if the covariance matrix is not square
     */
    protected void validateCovarianceData(double[][] x, double[][] covariance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251");
        if (ROR_not_equals(x.length, covariance.length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251", _mut8007, _mut8008, _mut8009, _mut8010, _mut8011)) {
            throw new DimensionMismatchException(x.length, covariance.length);
        }
        if ((_mut8022 ? (ROR_greater(covariance.length, 0, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251", _mut8012, _mut8013, _mut8014, _mut8015, _mut8016) || ROR_not_equals(covariance.length, covariance[0].length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251", _mut8017, _mut8018, _mut8019, _mut8020, _mut8021)) : (ROR_greater(covariance.length, 0, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251", _mut8012, _mut8013, _mut8014, _mut8015, _mut8016) && ROR_not_equals(covariance.length, covariance[0].length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.validateCovarianceData_251", _mut8017, _mut8018, _mut8019, _mut8020, _mut8021)))) {
            throw new NonSquareMatrixException(covariance.length, covariance[0].length);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateRegressionParameters() {
        RealVector b = calculateBeta();
        return b.toArray();
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateResiduals() {
        RealVector b = calculateBeta();
        RealVector e = yVector.subtract(xMatrix.operate(b));
        return e.toArray();
    }

    /**
     * {@inheritDoc}
     */
    public double[][] estimateRegressionParametersVariance() {
        return calculateBetaVariance().getData();
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateRegressionParametersStandardErrors() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.estimateRegressionParametersStandardErrors_287");
        double[][] betaVariance = estimateRegressionParametersVariance();
        double sigma = calculateErrorVariance();
        int length = betaVariance[0].length;
        double[] result = new double[length];
        for (int i = 0; ROR_less(i, length, "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.estimateRegressionParametersStandardErrors_287", _mut8027, _mut8028, _mut8029, _mut8030, _mut8031); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.estimateRegressionParametersStandardErrors_287");
            result[i] = FastMath.sqrt(AOR_multiply(sigma, betaVariance[i][i], "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.estimateRegressionParametersStandardErrors_287", _mut8023, _mut8024, _mut8025, _mut8026));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public double estimateRegressandVariance() {
        return calculateYVariance();
    }

    /**
     * Estimates the variance of the error.
     *
     * @return estimate of the error variance
     * @since 2.2
     */
    public double estimateErrorVariance() {
        return calculateErrorVariance();
    }

    /**
     * Estimates the standard error of the regression.
     *
     * @return regression standard error
     * @since 2.2
     */
    public double estimateRegressionStandardError() {
        return FastMath.sqrt(estimateErrorVariance());
    }

    /**
     * Calculates the beta of multiple linear regression in matrix notation.
     *
     * @return beta
     */
    protected abstract RealVector calculateBeta();

    /**
     * Calculates the beta variance of multiple linear regression in matrix
     * notation.
     *
     * @return beta variance
     */
    protected abstract RealMatrix calculateBetaVariance();

    /**
     * Calculates the variance of the y values.
     *
     * @return Y variance
     */
    protected double calculateYVariance() {
        return new Variance().evaluate(yVector.toArray());
    }

    /**
     * <p>Calculates the variance of the error term.</p>
     * Uses the formula <pre>
     * var(u) = u &middot; u / (n - k)
     * </pre>
     * where n and k are the row and column dimensions of the design
     * matrix X.
     *
     * @return error variance estimate
     * @since 2.2
     */
    protected double calculateErrorVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.calculateErrorVariance_362");
        RealVector residuals = calculateResiduals();
        return AOR_divide(residuals.dotProduct(residuals), (AOR_minus(xMatrix.getRowDimension(), xMatrix.getColumnDimension(), "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.calculateErrorVariance_362", _mut8032, _mut8033, _mut8034, _mut8035)), "org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression.calculateErrorVariance_362", _mut8036, _mut8037, _mut8038, _mut8039);
    }

    /**
     * Calculates the residuals of multiple linear regression in matrix
     * notation.
     *
     * <pre>
     * u = y - X * b
     * </pre>
     *
     * @return The residuals [n,1] matrix
     */
    protected RealVector calculateResiduals() {
        RealVector b = calculateBeta();
        return yVector.subtract(xMatrix.operate(b));
    }
}
