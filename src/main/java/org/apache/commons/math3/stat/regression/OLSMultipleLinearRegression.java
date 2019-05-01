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

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Implements ordinary least squares (OLS) to estimate the parameters of a
 * multiple linear regression model.</p>
 *
 * <p>The regression coefficients, <code>b</code>, satisfy the normal equations:
 * <pre><code> X<sup>T</sup> X b = X<sup>T</sup> y </code></pre></p>
 *
 * <p>To solve the normal equations, this implementation uses QR decomposition
 * of the <code>X</code> matrix. (See {@link QRDecomposition} for details on the
 * decomposition algorithm.) The <code>X</code> matrix, also known as the <i>design matrix,</i>
 * has rows corresponding to sample observations and columns corresponding to independent
 * variables.  When the model is estimated using an intercept term (i.e. when
 * {@link #isNoIntercept() isNoIntercept} is false as it is by default), the <code>X</code>
 * matrix includes an initial column identically equal to 1.  We solve the normal equations
 * as follows:
 * <pre><code> X<sup>T</sup>X b = X<sup>T</sup> y
 * (QR)<sup>T</sup> (QR) b = (QR)<sup>T</sup>y
 * R<sup>T</sup> (Q<sup>T</sup>Q) R b = R<sup>T</sup> Q<sup>T</sup> y
 * R<sup>T</sup> R b = R<sup>T</sup> Q<sup>T</sup> y
 * (R<sup>T</sup>)<sup>-1</sup> R<sup>T</sup> R b = (R<sup>T</sup>)<sup>-1</sup> R<sup>T</sup> Q<sup>T</sup> y
 * R b = Q<sup>T</sup> y </code></pre></p>
 *
 * <p>Given <code>Q</code> and <code>R</code>, the last equation is solved by back-substitution.</p>
 *
 * @since 2.0
 */
public class OLSMultipleLinearRegression extends AbstractMultipleLinearRegression {

    @Conditional
    public static boolean _mut7830 = false, _mut7831 = false, _mut7832 = false, _mut7833 = false, _mut7834 = false, _mut7835 = false, _mut7836 = false, _mut7837 = false, _mut7838 = false, _mut7839 = false, _mut7840 = false, _mut7841 = false, _mut7842 = false, _mut7843 = false, _mut7844 = false, _mut7845 = false, _mut7846 = false, _mut7847 = false, _mut7848 = false, _mut7849 = false, _mut7850 = false, _mut7851 = false, _mut7852 = false, _mut7853 = false, _mut7854 = false, _mut7855 = false, _mut7856 = false, _mut7857 = false, _mut7858 = false, _mut7859 = false, _mut7860 = false, _mut7861 = false, _mut7862 = false, _mut7863 = false, _mut7864 = false, _mut7865 = false, _mut7866 = false, _mut7867 = false, _mut7868 = false, _mut7869 = false, _mut7870 = false, _mut7871 = false, _mut7872 = false, _mut7873 = false, _mut7874 = false, _mut7875 = false, _mut7876 = false, _mut7877 = false, _mut7878 = false, _mut7879 = false, _mut7880 = false, _mut7881 = false, _mut7882 = false, _mut7883 = false, _mut7884 = false, _mut7885 = false, _mut7886 = false, _mut7887 = false, _mut7888 = false, _mut7889 = false, _mut7890 = false, _mut7891 = false, _mut7892 = false, _mut7893 = false, _mut7894 = false, _mut7895 = false, _mut7896 = false, _mut7897 = false, _mut7898 = false, _mut7899 = false, _mut7900 = false, _mut7901 = false, _mut7902 = false, _mut7903 = false, _mut7904 = false, _mut7905 = false, _mut7906 = false, _mut7907 = false, _mut7908 = false, _mut7909 = false, _mut7910 = false;

    /**
     * Cached QR decomposition of X matrix
     */
    private QRDecomposition qr = null;

    /**
     * Singularity threshold for QR decomposition
     */
    private final double threshold;

    /**
     * Create an empty OLSMultipleLinearRegression instance.
     */
    public OLSMultipleLinearRegression() {
        this(0d);
    }

    /**
     * Create an empty OLSMultipleLinearRegression instance, using the given
     * singularity threshold for the QR decomposition.
     *
     * @param threshold the singularity threshold
     * @since 3.3
     */
    public OLSMultipleLinearRegression(final double threshold) {
        this.threshold = threshold;
    }

    /**
     * Loads model x and y sample data, overriding any previous sample.
     *
     * Computes and caches QR decomposition of the X matrix.
     * @param y the [n,1] array representing the y sample
     * @param x the [n,k] array representing the x sample
     * @throws MathIllegalArgumentException if the x and y array data are not
     *             compatible for the regression
     */
    public void newSampleData(double[] y, double[][] x) throws MathIllegalArgumentException {
        validateSampleData(x, y);
        newYSampleData(y);
        newXSampleData(x);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation computes and caches the QR decomposition of the X matrix.</p>
     */
    @Override
    public void newSampleData(double[] data, int nobs, int nvars) {
        super.newSampleData(data, nobs, nvars);
        qr = new QRDecomposition(getX(), threshold);
    }

    /**
     * <p>Compute the "hat" matrix.
     * </p>
     * <p>The hat matrix is defined in terms of the design matrix X
     *  by X(X<sup>T</sup>X)<sup>-1</sup>X<sup>T</sup>
     * </p>
     * <p>The implementation here uses the QR decomposition to compute the
     * hat matrix as Q I<sub>p</sub>Q<sup>T</sup> where I<sub>p</sub> is the
     * p-dimensional identity matrix augmented by 0's.  This computational
     * formula is from "The Hat Matrix in Regression and ANOVA",
     * David C. Hoaglin and Roy E. Welsch,
     * <i>The American Statistician</i>, Vol. 32, No. 1 (Feb., 1978), pp. 17-22.
     * </p>
     * <p>Data for the model must have been successfully loaded using one of
     * the {@code newSampleData} methods before invoking this method; otherwise
     * a {@code NullPointerException} will be thrown.</p>
     *
     * @return the hat matrix
     * @throws NullPointerException unless method {@code newSampleData} has been
     * called beforehand.
     */
    public RealMatrix calculateHat() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126");
        // Create augmented identity matrix
        RealMatrix Q = qr.getQ();
        final int p = qr.getR().getColumnDimension();
        final int n = Q.getColumnDimension();
        // No try-catch or advertised NotStrictlyPositiveException - NPE above if n < 3
        Array2DRowRealMatrix augI = new Array2DRowRealMatrix(n, n);
        double[][] augIData = augI.getDataRef();
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7846, _mut7847, _mut7848, _mut7849, _mut7850); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126");
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7841, _mut7842, _mut7843, _mut7844, _mut7845); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126");
                if ((_mut7840 ? (ROR_equals(i, j, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7830, _mut7831, _mut7832, _mut7833, _mut7834) || ROR_less(i, p, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7835, _mut7836, _mut7837, _mut7838, _mut7839)) : (ROR_equals(i, j, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7830, _mut7831, _mut7832, _mut7833, _mut7834) && ROR_less(i, p, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateHat_126", _mut7835, _mut7836, _mut7837, _mut7838, _mut7839)))) {
                    augIData[i][j] = 1d;
                } else {
                    augIData[i][j] = 0d;
                }
            }
        }
        // No DME advertised - args valid if we get here
        return Q.multiply(augI).multiply(Q.transpose());
    }

    /**
     * <p>Returns the sum of squared deviations of Y from its mean.</p>
     *
     * <p>If the model has no intercept term, <code>0</code> is used for the
     * mean of Y - i.e., what is returned is the sum of the squared Y values.</p>
     *
     * <p>The value returned by this method is the SSTO value used in
     * the {@link #calculateRSquared() R-squared} computation.</p>
     *
     * @return SSTO - the total sum of squares
     * @throws NullPointerException if the sample has not been set
     * @see #isNoIntercept()
     * @since 2.2
     */
    public double calculateTotalSumOfSquares() {
        if (isNoIntercept()) {
            return StatUtils.sumSq(getY().toArray());
        } else {
            return new SecondMoment().evaluate(getY().toArray());
        }
    }

    /**
     * Returns the sum of squared residuals.
     *
     * @return residual sum of squares
     * @since 2.2
     * @throws org.apache.commons.math3.linear.SingularMatrixException if the design matrix is singular
     * @throws NullPointerException if the data for the model have not been loaded
     */
    public double calculateResidualSumOfSquares() {
        final RealVector residuals = calculateResiduals();
        // No advertised DME, args are valid
        return residuals.dotProduct(residuals);
    }

    /**
     * Returns the R-Squared statistic, defined by the formula <pre>
     * R<sup>2</sup> = 1 - SSR / SSTO
     * </pre>
     * where SSR is the {@link #calculateResidualSumOfSquares() sum of squared residuals}
     * and SSTO is the {@link #calculateTotalSumOfSquares() total sum of squares}
     *
     * <p>If there is no variance in y, i.e., SSTO = 0, NaN is returned.</p>
     *
     * @return R-square statistic
     * @throws NullPointerException if the sample has not been set
     * @throws org.apache.commons.math3.linear.SingularMatrixException if the design matrix is singular
     * @since 2.2
     */
    public double calculateRSquared() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateRSquared_199");
        return AOR_minus(1, AOR_divide(calculateResidualSumOfSquares(), calculateTotalSumOfSquares(), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateRSquared_199", _mut7851, _mut7852, _mut7853, _mut7854), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateRSquared_199", _mut7855, _mut7856, _mut7857, _mut7858);
    }

    /**
     * <p>Returns the adjusted R-squared statistic, defined by the formula <pre>
     * R<sup>2</sup><sub>adj</sub> = 1 - [SSR (n - 1)] / [SSTO (n - p)]
     * </pre>
     * where SSR is the {@link #calculateResidualSumOfSquares() sum of squared residuals},
     * SSTO is the {@link #calculateTotalSumOfSquares() total sum of squares}, n is the number
     * of observations and p is the number of parameters estimated (including the intercept).</p>
     *
     * <p>If the regression is estimated without an intercept term, what is returned is <pre>
     * <code> 1 - (1 - {@link #calculateRSquared()}) * (n / (n - p)) </code>
     * </pre></p>
     *
     * <p>If there is no variance in y, i.e., SSTO = 0, NaN is returned.</p>
     *
     * @return adjusted R-Squared statistic
     * @throws NullPointerException if the sample has not been set
     * @throws org.apache.commons.math3.linear.SingularMatrixException if the design matrix is singular
     * @see #isNoIntercept()
     * @since 2.2
     */
    public double calculateAdjustedRSquared() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223");
        final double n = getX().getRowDimension();
        if (isNoIntercept()) {
            return AOR_minus(1, AOR_multiply((AOR_minus(1, calculateRSquared(), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7883, _mut7884, _mut7885, _mut7886)), (AOR_divide(n, (AOR_minus(n, getX().getColumnDimension(), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7887, _mut7888, _mut7889, _mut7890)), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7891, _mut7892, _mut7893, _mut7894)), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7895, _mut7896, _mut7897, _mut7898), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7899, _mut7900, _mut7901, _mut7902);
        } else {
            return AOR_minus(1, AOR_divide((AOR_multiply(calculateResidualSumOfSquares(), (AOR_minus(n, 1, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7859, _mut7860, _mut7861, _mut7862)), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7863, _mut7864, _mut7865, _mut7866)), (AOR_multiply(calculateTotalSumOfSquares(), (AOR_minus(n, getX().getColumnDimension(), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7867, _mut7868, _mut7869, _mut7870)), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7871, _mut7872, _mut7873, _mut7874)), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7875, _mut7876, _mut7877, _mut7878), "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateAdjustedRSquared_223", _mut7879, _mut7880, _mut7881, _mut7882);
        }
    }

    /**
     * {@inheritDoc}
     * <p>This implementation computes and caches the QR decomposition of the X matrix
     * once it is successfully loaded.</p>
     */
    @Override
    protected void newXSampleData(double[][] x) {
        super.newXSampleData(x);
        qr = new QRDecomposition(getX(), threshold);
    }

    /**
     * Calculates the regression coefficients using OLS.
     *
     * <p>Data for the model must have been successfully loaded using one of
     * the {@code newSampleData} methods before invoking this method; otherwise
     * a {@code NullPointerException} will be thrown.</p>
     *
     * @return beta
     * @throws org.apache.commons.math3.linear.SingularMatrixException if the design matrix is singular
     * @throws NullPointerException if the data for the model have not been loaded
     */
    @Override
    protected RealVector calculateBeta() {
        return qr.getSolver().solve(getY());
    }

    /**
     * <p>Calculates the variance-covariance matrix of the regression parameters.
     * </p>
     * <p>Var(b) = (X<sup>T</sup>X)<sup>-1</sup>
     * </p>
     * <p>Uses QR decomposition to reduce (X<sup>T</sup>X)<sup>-1</sup>
     * to (R<sup>T</sup>R)<sup>-1</sup>, with only the top p rows of
     * R included, where p = the length of the beta vector.</p>
     *
     * <p>Data for the model must have been successfully loaded using one of
     * the {@code newSampleData} methods before invoking this method; otherwise
     * a {@code NullPointerException} will be thrown.</p>
     *
     * @return The beta variance-covariance matrix
     * @throws org.apache.commons.math3.linear.SingularMatrixException if the design matrix is singular
     * @throws NullPointerException if the data for the model have not been loaded
     */
    @Override
    protected RealMatrix calculateBetaVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateBetaVariance_277");
        int p = getX().getColumnDimension();
        RealMatrix Raug = qr.getR().getSubMatrix(0, AOR_minus(p, 1, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateBetaVariance_277", _mut7903, _mut7904, _mut7905, _mut7906), 0, AOR_minus(p, 1, "org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression.calculateBetaVariance_277", _mut7907, _mut7908, _mut7909, _mut7910));
        RealMatrix Rinv = new LUDecomposition(Raug).getSolver().getInverse();
        return Rinv.multiply(Rinv.transpose());
    }
}
