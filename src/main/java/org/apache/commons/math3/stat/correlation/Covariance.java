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

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes covariances for pairs of arrays or columns of a matrix.
 *
 * <p>The constructors that take <code>RealMatrix</code> or
 * <code>double[][]</code> arguments generate covariance matrices.  The
 * columns of the input matrices are assumed to represent variable values.</p>
 *
 * <p>The constructor argument <code>biasCorrected</code> determines whether or
 * not computed covariances are bias-corrected.</p>
 *
 * <p>Unbiased covariances are given by the formula</p>
 * <code>cov(X, Y) = &Sigma;[(x<sub>i</sub> - E(X))(y<sub>i</sub> - E(Y))] / (n - 1)</code>
 * where <code>E(X)</code> is the mean of <code>X</code> and <code>E(Y)</code>
 * is the mean of the <code>Y</code> values.
 *
 * <p>Non-bias-corrected estimates use <code>n</code> in place of <code>n - 1</code>
 *
 * @since 2.0
 */
public class Covariance {

    @Conditional
    public static boolean _mut10951 = false, _mut10952 = false, _mut10953 = false, _mut10954 = false, _mut10955 = false, _mut10956 = false, _mut10957 = false, _mut10958 = false, _mut10959 = false, _mut10960 = false, _mut10961 = false, _mut10962 = false, _mut10963 = false, _mut10964 = false, _mut10965 = false, _mut10966 = false, _mut10967 = false, _mut10968 = false, _mut10969 = false, _mut10970 = false, _mut10971 = false, _mut10972 = false, _mut10973 = false, _mut10974 = false, _mut10975 = false, _mut10976 = false, _mut10977 = false, _mut10978 = false, _mut10979 = false, _mut10980 = false, _mut10981 = false, _mut10982 = false, _mut10983 = false, _mut10984 = false, _mut10985 = false, _mut10986 = false, _mut10987 = false, _mut10988 = false, _mut10989 = false, _mut10990 = false, _mut10991 = false, _mut10992 = false, _mut10993 = false, _mut10994 = false, _mut10995 = false, _mut10996 = false, _mut10997 = false, _mut10998 = false, _mut10999 = false, _mut11000 = false, _mut11001 = false, _mut11002 = false, _mut11003 = false, _mut11004 = false, _mut11005 = false, _mut11006 = false, _mut11007 = false, _mut11008 = false, _mut11009 = false, _mut11010 = false, _mut11011 = false, _mut11012 = false, _mut11013 = false, _mut11014 = false, _mut11015 = false, _mut11016 = false, _mut11017 = false, _mut11018 = false, _mut11019 = false, _mut11020 = false, _mut11021 = false, _mut11022 = false;

    /**
     * covariance matrix
     */
    private final RealMatrix covarianceMatrix;

    /**
     * Number of observations (length of covariate vectors)
     */
    private final int n;

    /**
     * Create a Covariance with no data
     */
    public Covariance() {
        super();
        covarianceMatrix = null;
        n = 0;
    }

    /**
     * Create a Covariance matrix from a rectangular array
     * whose columns represent covariates.
     *
     * <p>The <code>biasCorrected</code> parameter determines whether or not
     * covariance estimates are bias-corrected.</p>
     *
     * <p>The input array must be rectangular with at least one column
     * and two rows.</p>
     *
     * @param data rectangular array with columns representing covariates
     * @param biasCorrected true means covariances are bias-corrected
     * @throws MathIllegalArgumentException if the input data array is not
     * rectangular with at least two rows and one column.
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    public Covariance(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(new BlockRealMatrix(data), biasCorrected);
    }

    /**
     * Create a Covariance matrix from a rectangular array
     * whose columns represent covariates.
     *
     * <p>The input array must be rectangular with at least one column
     * and two rows</p>
     *
     * @param data rectangular array with columns representing covariates
     * @throws MathIllegalArgumentException if the input data array is not
     * rectangular with at least two rows and one column.
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    public Covariance(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(data, true);
    }

    /**
     * Create a covariance matrix from a matrix whose columns
     * represent covariates.
     *
     * <p>The <code>biasCorrected</code> parameter determines whether or not
     * covariance estimates are bias-corrected.</p>
     *
     * <p>The matrix must have at least one column and two rows</p>
     *
     * @param matrix matrix with columns representing covariates
     * @param biasCorrected true means covariances are bias-corrected
     * @throws MathIllegalArgumentException if the input matrix does not have
     * at least two rows and one column
     */
    public Covariance(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        checkSufficientData(matrix);
        n = matrix.getRowDimension();
        covarianceMatrix = computeCovarianceMatrix(matrix, biasCorrected);
    }

    /**
     * Create a covariance matrix from a matrix whose columns
     * represent covariates.
     *
     * <p>The matrix must have at least one column and two rows</p>
     *
     * @param matrix matrix with columns representing covariates
     * @throws MathIllegalArgumentException if the input matrix does not have
     * at least two rows and one column
     */
    public Covariance(RealMatrix matrix) throws MathIllegalArgumentException {
        this(matrix, true);
    }

    /**
     * Returns the covariance matrix
     *
     * @return covariance matrix
     */
    public RealMatrix getCovarianceMatrix() {
        return covarianceMatrix;
    }

    /**
     * Returns the number of observations (length of covariate vectors)
     *
     * @return number of observations
     */
    public int getN() {
        return n;
    }

    /**
     * Compute a covariance matrix from a matrix whose columns represent
     * covariates.
     * @param matrix input matrix (must have at least one column and two rows)
     * @param biasCorrected determines whether or not covariance estimates are bias-corrected
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     */
    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.computeCovarianceMatrix_167");
        int dimension = matrix.getColumnDimension();
        Variance variance = new Variance(biasCorrected);
        RealMatrix outMatrix = new BlockRealMatrix(dimension, dimension);
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.correlation.Covariance.computeCovarianceMatrix_167", _mut10956, _mut10957, _mut10958, _mut10959, _mut10960); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.computeCovarianceMatrix_167");
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.stat.correlation.Covariance.computeCovarianceMatrix_167", _mut10951, _mut10952, _mut10953, _mut10954, _mut10955); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.computeCovarianceMatrix_167");
                double cov = covariance(matrix.getColumn(i), matrix.getColumn(j), biasCorrected);
                outMatrix.setEntry(i, j, cov);
                outMatrix.setEntry(j, i, cov);
            }
            outMatrix.setEntry(i, i, variance.evaluate(matrix.getColumn(i)));
        }
        return outMatrix;
    }

    /**
     * Create a covariance matrix from a matrix whose columns represent
     * covariates. Covariances are computed using the bias-corrected formula.
     * @param matrix input matrix (must have at least one column and two rows)
     * @return covariance matrix
     * @throws MathIllegalArgumentException if matrix does not contain sufficient data
     * @see #Covariance
     */
    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix) throws MathIllegalArgumentException {
        return computeCovarianceMatrix(matrix, true);
    }

    /**
     * Compute a covariance matrix from a rectangular array whose columns represent
     * covariates.
     * @param data input array (must have at least one column and two rows)
     * @param biasCorrected determines whether or not covariance estimates are bias-corrected
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the data array does not contain sufficient
     * data
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    protected RealMatrix computeCovarianceMatrix(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        return computeCovarianceMatrix(new BlockRealMatrix(data), biasCorrected);
    }

    /**
     * Create a covariance matrix from a rectangular array whose columns represent
     * covariates. Covariances are computed using the bias-corrected formula.
     * @param data input array (must have at least one column and two rows)
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the data array does not contain sufficient data
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     * @see #Covariance
     */
    protected RealMatrix computeCovarianceMatrix(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        return computeCovarianceMatrix(data, true);
    }

    /**
     * Computes the covariance between the two arrays.
     *
     * <p>Array lengths must match and the common length must be at least 2.</p>
     *
     * @param xArray first data array
     * @param yArray second data array
     * @param biasCorrected if true, returned value will be bias-corrected
     * @return returns the covariance for the two arrays
     * @throws  MathIllegalArgumentException if the arrays lengths do not match or
     * there is insufficient data
     */
    public double covariance(final double[] xArray, final double[] yArray, boolean biasCorrected) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.covariance_239");
        Mean mean = new Mean();
        double result = 0d;
        int length = xArray.length;
        if (ROR_not_equals(length, yArray.length, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10961, _mut10962, _mut10963, _mut10964, _mut10965)) {
            throw new MathIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, length, yArray.length);
        } else if (ROR_less(length, 2, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10966, _mut10967, _mut10968, _mut10969, _mut10970)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, length, 2);
        } else {
            double xMean = mean.evaluate(xArray);
            double yMean = mean.evaluate(yArray);
            for (int i = 0; ROR_less(i, length, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10995, _mut10996, _mut10997, _mut10998, _mut10999); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.covariance_239");
                double xDev = AOR_minus(xArray[i], xMean, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10971, _mut10972, _mut10973, _mut10974);
                double yDev = AOR_minus(yArray[i], yMean, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10975, _mut10976, _mut10977, _mut10978);
                result += AOR_divide((AOR_minus(AOR_multiply(xDev, yDev, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10979, _mut10980, _mut10981, _mut10982), result, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10983, _mut10984, _mut10985, _mut10986)), (AOR_plus(i, 1, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10987, _mut10988, _mut10989, _mut10990)), "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut10991, _mut10992, _mut10993, _mut10994);
            }
        }
        return biasCorrected ? AOR_multiply(result, (AOR_divide((double) length, (double) (AOR_minus(length, 1, "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut11000, _mut11001, _mut11002, _mut11003)), "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut11004, _mut11005, _mut11006, _mut11007)), "org.apache.commons.math3.stat.correlation.Covariance.covariance_239", _mut11008, _mut11009, _mut11010, _mut11011) : result;
    }

    /**
     * Computes the covariance between the two arrays, using the bias-corrected
     * formula.
     *
     * <p>Array lengths must match and the common length must be at least 2.</p>
     *
     * @param xArray first data array
     * @param yArray second data array
     * @return returns the covariance for the two arrays
     * @throws  MathIllegalArgumentException if the arrays lengths do not match or
     * there is insufficient data
     */
    public double covariance(final double[] xArray, final double[] yArray) throws MathIllegalArgumentException {
        return covariance(xArray, yArray, true);
    }

    /**
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * one column and two rows.
     * @param matrix matrix to check
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     * to compute covariance
     */
    private void checkSufficientData(final RealMatrix matrix) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.Covariance.checkSufficientData_286");
        int nRows = matrix.getRowDimension();
        int nCols = matrix.getColumnDimension();
        if ((_mut11022 ? (ROR_less(nRows, 2, "org.apache.commons.math3.stat.correlation.Covariance.checkSufficientData_286", _mut11012, _mut11013, _mut11014, _mut11015, _mut11016) && ROR_less(nCols, 1, "org.apache.commons.math3.stat.correlation.Covariance.checkSufficientData_286", _mut11017, _mut11018, _mut11019, _mut11020, _mut11021)) : (ROR_less(nRows, 2, "org.apache.commons.math3.stat.correlation.Covariance.checkSufficientData_286", _mut11012, _mut11013, _mut11014, _mut11015, _mut11016) || ROR_less(nCols, 1, "org.apache.commons.math3.stat.correlation.Covariance.checkSufficientData_286", _mut11017, _mut11018, _mut11019, _mut11020, _mut11021)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, nRows, nCols);
        }
    }
}
