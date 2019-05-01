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

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Results of a Multiple Linear Regression model fit.
 *
 * @since 3.0
 */
public class RegressionResults implements Serializable {

    @Conditional
    public static boolean _mut8040 = false, _mut8041 = false, _mut8042 = false, _mut8043 = false, _mut8044 = false, _mut8045 = false, _mut8046 = false, _mut8047 = false, _mut8048 = false, _mut8049 = false, _mut8050 = false, _mut8051 = false, _mut8052 = false, _mut8053 = false, _mut8054 = false, _mut8055 = false, _mut8056 = false, _mut8057 = false, _mut8058 = false, _mut8059 = false, _mut8060 = false, _mut8061 = false, _mut8062 = false, _mut8063 = false, _mut8064 = false, _mut8065 = false, _mut8066 = false, _mut8067 = false, _mut8068 = false, _mut8069 = false, _mut8070 = false, _mut8071 = false, _mut8072 = false, _mut8073 = false, _mut8074 = false, _mut8075 = false, _mut8076 = false, _mut8077 = false, _mut8078 = false, _mut8079 = false, _mut8080 = false, _mut8081 = false, _mut8082 = false, _mut8083 = false, _mut8084 = false, _mut8085 = false, _mut8086 = false, _mut8087 = false, _mut8088 = false, _mut8089 = false, _mut8090 = false, _mut8091 = false, _mut8092 = false, _mut8093 = false, _mut8094 = false, _mut8095 = false, _mut8096 = false, _mut8097 = false, _mut8098 = false, _mut8099 = false, _mut8100 = false, _mut8101 = false, _mut8102 = false, _mut8103 = false, _mut8104 = false, _mut8105 = false, _mut8106 = false, _mut8107 = false, _mut8108 = false, _mut8109 = false, _mut8110 = false, _mut8111 = false, _mut8112 = false, _mut8113 = false, _mut8114 = false, _mut8115 = false, _mut8116 = false, _mut8117 = false, _mut8118 = false, _mut8119 = false, _mut8120 = false, _mut8121 = false, _mut8122 = false, _mut8123 = false, _mut8124 = false, _mut8125 = false, _mut8126 = false, _mut8127 = false, _mut8128 = false, _mut8129 = false, _mut8130 = false, _mut8131 = false, _mut8132 = false, _mut8133 = false, _mut8134 = false, _mut8135 = false, _mut8136 = false, _mut8137 = false, _mut8138 = false, _mut8139 = false, _mut8140 = false, _mut8141 = false, _mut8142 = false, _mut8143 = false, _mut8144 = false, _mut8145 = false, _mut8146 = false, _mut8147 = false, _mut8148 = false, _mut8149 = false, _mut8150 = false, _mut8151 = false, _mut8152 = false, _mut8153 = false, _mut8154 = false, _mut8155 = false, _mut8156 = false, _mut8157 = false, _mut8158 = false, _mut8159 = false, _mut8160 = false, _mut8161 = false, _mut8162 = false, _mut8163 = false, _mut8164 = false, _mut8165 = false, _mut8166 = false, _mut8167 = false, _mut8168 = false, _mut8169 = false, _mut8170 = false, _mut8171 = false, _mut8172 = false, _mut8173 = false, _mut8174 = false, _mut8175 = false, _mut8176 = false, _mut8177 = false, _mut8178 = false, _mut8179 = false, _mut8180 = false, _mut8181 = false, _mut8182 = false, _mut8183 = false, _mut8184 = false, _mut8185 = false, _mut8186 = false, _mut8187 = false, _mut8188 = false, _mut8189 = false, _mut8190 = false, _mut8191 = false, _mut8192 = false, _mut8193 = false, _mut8194 = false, _mut8195 = false, _mut8196 = false, _mut8197 = false, _mut8198 = false, _mut8199 = false, _mut8200 = false, _mut8201 = false, _mut8202 = false, _mut8203 = false, _mut8204 = false, _mut8205 = false, _mut8206 = false, _mut8207 = false, _mut8208 = false, _mut8209 = false, _mut8210 = false, _mut8211 = false, _mut8212 = false, _mut8213 = false, _mut8214 = false, _mut8215 = false, _mut8216 = false, _mut8217 = false, _mut8218 = false, _mut8219 = false, _mut8220 = false, _mut8221 = false, _mut8222 = false, _mut8223 = false, _mut8224 = false, _mut8225 = false, _mut8226 = false, _mut8227 = false, _mut8228 = false, _mut8229 = false, _mut8230 = false, _mut8231 = false, _mut8232 = false, _mut8233 = false, _mut8234 = false, _mut8235 = false, _mut8236 = false, _mut8237 = false, _mut8238 = false, _mut8239 = false, _mut8240 = false, _mut8241 = false, _mut8242 = false, _mut8243 = false, _mut8244 = false, _mut8245 = false, _mut8246 = false, _mut8247 = false, _mut8248 = false, _mut8249 = false, _mut8250 = false, _mut8251 = false, _mut8252 = false, _mut8253 = false, _mut8254 = false;

    /**
     * INDEX of Sum of Squared Errors
     */
    private static final int SSE_IDX = 0;

    /**
     * INDEX of Sum of Squares of Model
     */
    private static final int SST_IDX = 1;

    /**
     * INDEX of R-Squared of regression
     */
    private static final int RSQ_IDX = 2;

    /**
     * INDEX of Mean Squared Error
     */
    private static final int MSE_IDX = 3;

    /**
     * INDEX of Adjusted R Squared
     */
    private static final int ADJRSQ_IDX = 4;

    /**
     * UID
     */
    private static final long serialVersionUID = 1l;

    /**
     * regression slope parameters
     */
    private final double[] parameters;

    /**
     * variance covariance matrix of parameters
     */
    private final double[][] varCovData;

    /**
     * boolean flag for variance covariance matrix in symm compressed storage
     */
    private final boolean isSymmetricVCD;

    /**
     * rank of the solution
     */
    @SuppressWarnings("unused")
    private final int rank;

    /**
     * number of observations on which results are based
     */
    private final long nobs;

    /**
     * boolean flag indicator of whether a constant was included
     */
    private final boolean containsConstant;

    /**
     * array storing global results, SSE, MSE, RSQ, adjRSQ
     */
    private final double[] globalFitInfo;

    /**
     *  Set the default constructor to private access
     *  to prevent inadvertent instantiation
     */
    @SuppressWarnings("unused")
    private RegressionResults() {
        this.parameters = null;
        this.varCovData = null;
        this.rank = -1;
        this.nobs = -1;
        this.containsConstant = false;
        this.isSymmetricVCD = false;
        this.globalFitInfo = null;
    }

    /**
     * Constructor for Regression Results.
     *
     * @param parameters a double array with the regression slope estimates
     * @param varcov the variance covariance matrix, stored either in a square matrix
     * or as a compressed
     * @param isSymmetricCompressed a flag which denotes that the variance covariance
     * matrix is in symmetric compressed format
     * @param nobs the number of observations of the regression estimation
     * @param rank the number of independent variables in the regression
     * @param sumy the sum of the independent variable
     * @param sumysq the sum of the squared independent variable
     * @param sse sum of squared errors
     * @param containsConstant true model has constant,  false model does not have constant
     * @param copyData if true a deep copy of all input data is made, if false only references
     * are copied and the RegressionResults become mutable
     */
    public RegressionResults(final double[] parameters, final double[][] varcov, final boolean isSymmetricCompressed, final long nobs, final int rank, final double sumy, final double sumysq, final double sse, final boolean containsConstant, final boolean copyData) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92");
        if (copyData) {
            this.parameters = MathArrays.copyOf(parameters);
            this.varCovData = new double[varcov.length][];
            for (int i = 0; ROR_less(i, varcov.length, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8040, _mut8041, _mut8042, _mut8043, _mut8044); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92");
                this.varCovData[i] = MathArrays.copyOf(varcov[i]);
            }
        } else {
            this.parameters = parameters;
            this.varCovData = varcov;
        }
        this.isSymmetricVCD = isSymmetricCompressed;
        this.nobs = nobs;
        this.rank = rank;
        this.containsConstant = containsConstant;
        this.globalFitInfo = new double[5];
        Arrays.fill(this.globalFitInfo, Double.NaN);
        if (ROR_greater(rank, 0, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8045, _mut8046, _mut8047, _mut8048, _mut8049)) {
            this.globalFitInfo[SST_IDX] = containsConstant ? (AOR_minus(sumysq, AOR_divide(AOR_multiply(sumy, sumy, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8050, _mut8051, _mut8052, _mut8053), nobs, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8054, _mut8055, _mut8056, _mut8057), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8058, _mut8059, _mut8060, _mut8061)) : sumysq;
        }
        this.globalFitInfo[SSE_IDX] = sse;
        this.globalFitInfo[MSE_IDX] = AOR_divide(this.globalFitInfo[SSE_IDX], (AOR_minus(nobs, rank, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8062, _mut8063, _mut8064, _mut8065)), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8066, _mut8067, _mut8068, _mut8069);
        this.globalFitInfo[RSQ_IDX] = AOR_minus(1.0, AOR_divide(this.globalFitInfo[SSE_IDX], this.globalFitInfo[SST_IDX], "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8070, _mut8071, _mut8072, _mut8073), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8074, _mut8075, _mut8076, _mut8077);
        if (!containsConstant) {
            this.globalFitInfo[ADJRSQ_IDX] = AOR_minus(1.0, AOR_multiply((AOR_minus(1.0, this.globalFitInfo[RSQ_IDX], "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8102, _mut8103, _mut8104, _mut8105)), (AOR_divide((double) nobs, ((double) (AOR_minus(nobs, rank, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8106, _mut8107, _mut8108, _mut8109))), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8110, _mut8111, _mut8112, _mut8113)), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8114, _mut8115, _mut8116, _mut8117), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8118, _mut8119, _mut8120, _mut8121);
        } else {
            this.globalFitInfo[ADJRSQ_IDX] = AOR_minus(1.0, AOR_divide((AOR_multiply(sse, (AOR_minus(nobs, 1.0, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8078, _mut8079, _mut8080, _mut8081)), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8082, _mut8083, _mut8084, _mut8085)), (AOR_multiply(globalFitInfo[SST_IDX], (AOR_minus(nobs, rank, "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8086, _mut8087, _mut8088, _mut8089)), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8090, _mut8091, _mut8092, _mut8093)), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8094, _mut8095, _mut8096, _mut8097), "org.apache.commons.math3.stat.regression.RegressionResults.RegressionResults_92", _mut8098, _mut8099, _mut8100, _mut8101);
        }
    }

    /**
     * <p>Returns the parameter estimate for the regressor at the given index.</p>
     *
     * <p>A redundant regressor will have its redundancy flag set, as well as
     *  a parameters estimated equal to {@code Double.NaN}</p>
     *
     * @param index Index.
     * @return the parameters estimated for regressor at index.
     * @throws OutOfRangeException if {@code index} is not in the interval
     * {@code [0, number of parameters)}.
     */
    public double getParameterEstimate(int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149");
        if (parameters == null) {
            return Double.NaN;
        }
        if ((_mut8132 ? (ROR_less(index, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149", _mut8122, _mut8123, _mut8124, _mut8125, _mut8126) && ROR_greater_equals(index, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149", _mut8127, _mut8128, _mut8129, _mut8130, _mut8131)) : (ROR_less(index, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149", _mut8122, _mut8123, _mut8124, _mut8125, _mut8126) || ROR_greater_equals(index, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149", _mut8127, _mut8128, _mut8129, _mut8130, _mut8131)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(this.parameters.length, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getParameterEstimate_149", _mut8133, _mut8134, _mut8135, _mut8136));
        }
        return this.parameters[index];
    }

    /**
     * <p>Returns a copy of the regression parameters estimates.</p>
     *
     * <p>The parameter estimates are returned in the natural order of the data.</p>
     *
     * <p>A redundant regressor will have its redundancy flag set, as will
     *  a parameter estimate equal to {@code Double.NaN}.</p>
     *
     * @return array of parameter estimates, null if no estimation occurred
     */
    public double[] getParameterEstimates() {
        if (this.parameters == null) {
            return null;
        }
        return MathArrays.copyOf(parameters);
    }

    /**
     * Returns the <a href="http://www.xycoon.com/standerrorb(1).htm">standard
     * error of the parameter estimate at index</a>,
     * usually denoted s(b<sub>index</sub>).
     *
     * @param index Index.
     * @return the standard errors associated with parameters estimated at index.
     * @throws OutOfRangeException if {@code index} is not in the interval
     * {@code [0, number of parameters)}.
     */
    public double getStdErrorOfEstimate(int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186");
        if (parameters == null) {
            return Double.NaN;
        }
        if ((_mut8147 ? (ROR_less(index, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8137, _mut8138, _mut8139, _mut8140, _mut8141) && ROR_greater_equals(index, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8142, _mut8143, _mut8144, _mut8145, _mut8146)) : (ROR_less(index, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8137, _mut8138, _mut8139, _mut8140, _mut8141) || ROR_greater_equals(index, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8142, _mut8143, _mut8144, _mut8145, _mut8146)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(this.parameters.length, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8148, _mut8149, _mut8150, _mut8151));
        }
        double var = this.getVcvElement(index, index);
        if ((_mut8157 ? (!Double.isNaN(var) || ROR_greater(var, Double.MIN_VALUE, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8152, _mut8153, _mut8154, _mut8155, _mut8156)) : (!Double.isNaN(var) && ROR_greater(var, Double.MIN_VALUE, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimate_186", _mut8152, _mut8153, _mut8154, _mut8155, _mut8156)))) {
            return FastMath.sqrt(var);
        }
        return Double.NaN;
    }

    /**
     * <p>Returns the <a href="http://www.xycoon.com/standerrorb(1).htm">standard
     * error of the parameter estimates</a>,
     * usually denoted s(b<sub>i</sub>).</p>
     *
     * <p>If there are problems with an ill conditioned design matrix then the regressor
     * which is redundant will be assigned <code>Double.NaN</code>. </p>
     *
     * @return an array standard errors associated with parameters estimates,
     *  null if no estimation occurred
     */
    public double[] getStdErrorOfEstimates() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimates_211");
        if (parameters == null) {
            return null;
        }
        double[] se = new double[this.parameters.length];
        for (int i = 0; ROR_less(i, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimates_211", _mut8164, _mut8165, _mut8166, _mut8167, _mut8168); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimates_211");
            double var = this.getVcvElement(i, i);
            if ((_mut8163 ? (!Double.isNaN(var) || ROR_greater(var, Double.MIN_VALUE, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimates_211", _mut8158, _mut8159, _mut8160, _mut8161, _mut8162)) : (!Double.isNaN(var) && ROR_greater(var, Double.MIN_VALUE, "org.apache.commons.math3.stat.regression.RegressionResults.getStdErrorOfEstimates_211", _mut8158, _mut8159, _mut8160, _mut8161, _mut8162)))) {
                se[i] = FastMath.sqrt(var);
                continue;
            }
            se[i] = Double.NaN;
        }
        return se;
    }

    /**
     * <p>Returns the covariance between regression parameters i and j.</p>
     *
     * <p>If there are problems with an ill conditioned design matrix then the covariance
     * which involves redundant columns will be assigned {@code Double.NaN}. </p>
     *
     * @param i {@code i}th regression parameter.
     * @param j {@code j}th regression parameter.
     * @return the covariance of the parameter estimates.
     * @throws OutOfRangeException if {@code i} or {@code j} is not in the
     * interval {@code [0, number of parameters)}.
     */
    public double getCovarianceOfParameters(int i, int j) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239");
        if (parameters == null) {
            return Double.NaN;
        }
        if ((_mut8179 ? (ROR_less(i, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8169, _mut8170, _mut8171, _mut8172, _mut8173) && ROR_greater_equals(i, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8174, _mut8175, _mut8176, _mut8177, _mut8178)) : (ROR_less(i, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8169, _mut8170, _mut8171, _mut8172, _mut8173) || ROR_greater_equals(i, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8174, _mut8175, _mut8176, _mut8177, _mut8178)))) {
            throw new OutOfRangeException(i, 0, AOR_minus(this.parameters.length, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8180, _mut8181, _mut8182, _mut8183));
        }
        if ((_mut8194 ? (ROR_less(j, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8184, _mut8185, _mut8186, _mut8187, _mut8188) && ROR_greater_equals(j, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8189, _mut8190, _mut8191, _mut8192, _mut8193)) : (ROR_less(j, 0, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8184, _mut8185, _mut8186, _mut8187, _mut8188) || ROR_greater_equals(j, this.parameters.length, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8189, _mut8190, _mut8191, _mut8192, _mut8193)))) {
            throw new OutOfRangeException(j, 0, AOR_minus(this.parameters.length, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getCovarianceOfParameters_239", _mut8195, _mut8196, _mut8197, _mut8198));
        }
        return this.getVcvElement(i, j);
    }

    /**
     * <p>Returns the number of parameters estimated in the model.</p>
     *
     * <p>This is the maximum number of regressors, some techniques may drop
     * redundant parameters</p>
     *
     * @return number of regressors, -1 if not estimated
     */
    public int getNumberOfParameters() {
        if (this.parameters == null) {
            return -1;
        }
        return this.parameters.length;
    }

    /**
     * Returns the number of observations added to the regression model.
     *
     * @return Number of observations, -1 if an error condition prevents estimation
     */
    public long getN() {
        return this.nobs;
    }

    /**
     * <p>Returns the sum of squared deviations of the y values about their mean.</p>
     *
     * <p>This is defined as SSTO
     * <a href="http://www.xycoon.com/SumOfSquares.htm">here</a>.</p>
     *
     * <p>If {@code n < 2}, this returns {@code Double.NaN}.</p>
     *
     * @return sum of squared deviations of y values
     */
    public double getTotalSumSquares() {
        return this.globalFitInfo[SST_IDX];
    }

    /**
     * <p>Returns the sum of squared deviations of the predicted y values about
     * their mean (which equals the mean of y).</p>
     *
     * <p>This is usually abbreviated SSR or SSM.  It is defined as SSM
     * <a href="http://www.xycoon.com/SumOfSquares.htm">here</a></p>
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double.NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return sum of squared deviations of predicted y values
     */
    public double getRegressionSumSquares() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getRegressionSumSquares_306");
        return AOR_minus(this.globalFitInfo[SST_IDX], this.globalFitInfo[SSE_IDX], "org.apache.commons.math3.stat.regression.RegressionResults.getRegressionSumSquares_306", _mut8199, _mut8200, _mut8201, _mut8202);
    }

    /**
     * <p>Returns the <a href="http://www.xycoon.com/SumOfSquares.htm">
     * sum of squared errors</a> (SSE) associated with the regression
     * model.</p>
     *
     * <p>The return value is constrained to be non-negative - i.e., if due to
     * rounding errors the computational formula returns a negative result,
     * 0 is returned.</p>
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>numberOfParameters data pairs
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return sum of squared errors associated with the regression model
     */
    public double getErrorSumSquares() {
        return this.globalFitInfo[SSE_IDX];
    }

    /**
     * <p>Returns the sum of squared errors divided by the degrees of freedom,
     * usually abbreviated MSE.</p>
     *
     * <p>If there are fewer than <strong>numberOfParameters + 1</strong> data pairs in the model,
     * or if there is no variation in <code>x</code>, this returns
     * <code>Double.NaN</code>.</p>
     *
     * @return sum of squared deviations of y values
     */
    public double getMeanSquareError() {
        return this.globalFitInfo[MSE_IDX];
    }

    /**
     * <p>Returns the <a href="http://www.xycoon.com/coefficient1.htm">
     * coefficient of multiple determination</a>,
     * usually denoted r-square.</p>
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>At least numberOfParameters observations (with at least numberOfParameters different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, {@code Double,NaN} is
     * returned.
     * </li></ul></p>
     *
     * @return r-square, a double in the interval [0, 1]
     */
    public double getRSquared() {
        return this.globalFitInfo[RSQ_IDX];
    }

    /**
     * <p>Returns the adjusted R-squared statistic, defined by the formula <pre>
     * R<sup>2</sup><sub>adj</sub> = 1 - [SSR (n - 1)] / [SSTO (n - p)]
     * </pre>
     * where SSR is the sum of squared residuals},
     * SSTO is the total sum of squares}, n is the number
     * of observations and p is the number of parameters estimated (including the intercept).</p>
     *
     * <p>If the regression is estimated without an intercept term, what is returned is <pre>
     * <code> 1 - (1 - {@link #getRSquared()} ) * (n / (n - p)) </code>
     * </pre></p>
     *
     * @return adjusted R-Squared statistic
     */
    public double getAdjustedRSquared() {
        return this.globalFitInfo[ADJRSQ_IDX];
    }

    /**
     * Returns true if the regression model has been computed including an intercept.
     * In this case, the coefficient of the intercept is the first element of the
     * {@link #getParameterEstimates() parameter estimates}.
     * @return true if the model has an intercept term
     */
    public boolean hasIntercept() {
        return this.containsConstant;
    }

    /**
     * Gets the i-jth element of the variance-covariance matrix.
     *
     * @param i first variable index
     * @param j second variable index
     * @return the requested variance-covariance matrix entry
     */
    private double getVcvElement(int i, int j) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399");
        if (this.isSymmetricVCD) {
            if (ROR_greater(this.varCovData.length, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8203, _mut8204, _mut8205, _mut8206, _mut8207)) {
                // could be stored in upper or lower triangular
                if (ROR_equals(i, j, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8245, _mut8246, _mut8247, _mut8248, _mut8249)) {
                    return varCovData[i][i];
                } else if (ROR_greater_equals(i, varCovData[j].length, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8250, _mut8251, _mut8252, _mut8253, _mut8254)) {
                    return varCovData[i][j];
                } else {
                    return varCovData[j][i];
                }
            } else {
                // could be in single array
                if (ROR_greater(i, j, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8208, _mut8209, _mut8210, _mut8211, _mut8212)) {
                    return varCovData[0][AOR_plus(AOR_divide(AOR_multiply((AOR_plus(i, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8229, _mut8230, _mut8231, _mut8232)), i, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8233, _mut8234, _mut8235, _mut8236), 2, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8237, _mut8238, _mut8239, _mut8240), j, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8241, _mut8242, _mut8243, _mut8244)];
                } else {
                    return varCovData[0][AOR_plus(AOR_divide(AOR_multiply((AOR_plus(j, 1, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8213, _mut8214, _mut8215, _mut8216)), j, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8217, _mut8218, _mut8219, _mut8220), 2, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8221, _mut8222, _mut8223, _mut8224), i, "org.apache.commons.math3.stat.regression.RegressionResults.getVcvElement_399", _mut8225, _mut8226, _mut8227, _mut8228)];
                }
            }
        } else {
            return this.varCovData[i][j];
        }
    }
}
