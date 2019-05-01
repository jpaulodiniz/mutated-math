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
package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of a Kalman filter to estimate the state <i>x<sub>k</sub></i>
 * of a discrete-time controlled process that is governed by the linear
 * stochastic difference equation:
 *
 * <pre>
 * <i>x<sub>k</sub></i> = <b>A</b><i>x<sub>k-1</sub></i> + <b>B</b><i>u<sub>k-1</sub></i> + <i>w<sub>k-1</sub></i>
 * </pre>
 *
 * with a measurement <i>x<sub>k</sub></i> that is
 *
 * <pre>
 * <i>z<sub>k</sub></i> = <b>H</b><i>x<sub>k</sub></i> + <i>v<sub>k</sub></i>.
 * </pre>
 *
 * <p>
 * The random variables <i>w<sub>k</sub></i> and <i>v<sub>k</sub></i> represent
 * the process and measurement noise and are assumed to be independent of each
 * other and distributed with normal probability (white noise).
 * <p>
 * The Kalman filter cycle involves the following steps:
 * <ol>
 * <li>predict: project the current state estimate ahead in time</li>
 * <li>correct: adjust the projected estimate by an actual measurement</li>
 * </ol>
 * <p>
 * The Kalman filter is initialized with a {@link ProcessModel} and a
 * {@link MeasurementModel}, which contain the corresponding transformation and
 * noise covariance matrices. The parameter names used in the respective models
 * correspond to the following names commonly used in the mathematical
 * literature:
 * <ul>
 * <li>A - state transition matrix</li>
 * <li>B - control input matrix</li>
 * <li>H - measurement matrix</li>
 * <li>Q - process noise covariance matrix</li>
 * <li>R - measurement noise covariance matrix</li>
 * <li>P - error covariance matrix</li>
 * </ul>
 *
 * @see <a href="http://www.cs.unc.edu/~welch/kalman/">Kalman filter
 *      resources</a>
 * @see <a href="http://www.cs.unc.edu/~welch/media/pdf/kalman_intro.pdf">An
 *      introduction to the Kalman filter by Greg Welch and Gary Bishop</a>
 * @see <a href="http://academic.csuohio.edu/simond/courses/eec644/kalman.pdf">
 *      Kalman filter example by Dan Simon</a>
 * @see ProcessModel
 * @see MeasurementModel
 * @since 3.0
 */
public class KalmanFilter {

    @Conditional
    public static boolean _mut70352 = false, _mut70353 = false, _mut70354 = false, _mut70355 = false, _mut70356 = false, _mut70357 = false, _mut70358 = false, _mut70359 = false, _mut70360 = false, _mut70361 = false, _mut70362 = false, _mut70363 = false, _mut70364 = false, _mut70365 = false, _mut70366 = false, _mut70367 = false, _mut70368 = false, _mut70369 = false, _mut70370 = false, _mut70371 = false, _mut70372 = false, _mut70373 = false, _mut70374 = false, _mut70375 = false, _mut70376 = false, _mut70377 = false, _mut70378 = false, _mut70379 = false, _mut70380 = false, _mut70381 = false, _mut70382 = false, _mut70383 = false, _mut70384 = false, _mut70385 = false, _mut70386 = false, _mut70387 = false, _mut70388 = false, _mut70389 = false, _mut70390 = false, _mut70391 = false, _mut70392 = false, _mut70393 = false, _mut70394 = false, _mut70395 = false;

    /**
     * The process model used by this filter instance.
     */
    private final ProcessModel processModel;

    /**
     * The measurement model used by this filter instance.
     */
    private final MeasurementModel measurementModel;

    /**
     * The transition matrix, equivalent to A.
     */
    private RealMatrix transitionMatrix;

    /**
     * The transposed transition matrix.
     */
    private RealMatrix transitionMatrixT;

    /**
     * The control matrix, equivalent to B.
     */
    private RealMatrix controlMatrix;

    /**
     * The measurement matrix, equivalent to H.
     */
    private RealMatrix measurementMatrix;

    /**
     * The transposed measurement matrix.
     */
    private RealMatrix measurementMatrixT;

    /**
     * The internal state estimation vector, equivalent to x hat.
     */
    private RealVector stateEstimation;

    /**
     * The error covariance matrix, equivalent to P.
     */
    private RealMatrix errorCovariance;

    /**
     * Creates a new Kalman filter with the given process and measurement models.
     *
     * @param process
     *            the model defining the underlying process dynamics
     * @param measurement
     *            the model defining the given measurement characteristics
     * @throws NullArgumentException
     *             if any of the given inputs is null (except for the control matrix)
     * @throws NonSquareMatrixException
     *             if the transition matrix is non square
     * @throws DimensionMismatchException
     *             if the column dimension of the transition matrix does not match the dimension of the
     *             initial state estimation vector
     * @throws MatrixDimensionMismatchException
     *             if the matrix dimensions do not fit together
     */
    public KalmanFilter(final ProcessModel process, final MeasurementModel measurement) throws NullArgumentException, NonSquareMatrixException, DimensionMismatchException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119");
        MathUtils.checkNotNull(process);
        MathUtils.checkNotNull(measurement);
        this.processModel = process;
        this.measurementModel = measurement;
        transitionMatrix = processModel.getStateTransitionMatrix();
        MathUtils.checkNotNull(transitionMatrix);
        transitionMatrixT = transitionMatrix.transpose();
        // create an empty matrix if no control matrix was given
        if (processModel.getControlMatrix() == null) {
            controlMatrix = new Array2DRowRealMatrix();
        } else {
            controlMatrix = processModel.getControlMatrix();
        }
        measurementMatrix = measurementModel.getMeasurementMatrix();
        MathUtils.checkNotNull(measurementMatrix);
        measurementMatrixT = measurementMatrix.transpose();
        // over time
        RealMatrix processNoise = processModel.getProcessNoise();
        MathUtils.checkNotNull(processNoise);
        RealMatrix measNoise = measurementModel.getMeasurementNoise();
        MathUtils.checkNotNull(measNoise);
        // available from the process model
        if (processModel.getInitialStateEstimate() == null) {
            stateEstimation = new ArrayRealVector(transitionMatrix.getColumnDimension());
        } else {
            stateEstimation = processModel.getInitialStateEstimate();
        }
        if (ROR_not_equals(transitionMatrix.getColumnDimension(), stateEstimation.getDimension(), "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70352, _mut70353, _mut70354, _mut70355, _mut70356)) {
            throw new DimensionMismatchException(transitionMatrix.getColumnDimension(), stateEstimation.getDimension());
        }
        // available from the process model
        if (processModel.getInitialErrorCovariance() == null) {
            errorCovariance = processNoise.copy();
        } else {
            errorCovariance = processModel.getInitialErrorCovariance();
        }
        // A must be a square matrix
        if (!transitionMatrix.isSquare()) {
            throw new NonSquareMatrixException(transitionMatrix.getRowDimension(), transitionMatrix.getColumnDimension());
        }
        // if no control matrix is available, the row and column dimension will be 0
        if ((_mut70374 ? ((_mut70368 ? ((_mut70362 ? (controlMatrix != null || ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361)) : (controlMatrix != null && ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361))) || ROR_greater(controlMatrix.getColumnDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70363, _mut70364, _mut70365, _mut70366, _mut70367)) : ((_mut70362 ? (controlMatrix != null || ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361)) : (controlMatrix != null && ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361))) && ROR_greater(controlMatrix.getColumnDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70363, _mut70364, _mut70365, _mut70366, _mut70367))) || ROR_not_equals(controlMatrix.getRowDimension(), transitionMatrix.getRowDimension(), "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70369, _mut70370, _mut70371, _mut70372, _mut70373)) : ((_mut70368 ? ((_mut70362 ? (controlMatrix != null || ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361)) : (controlMatrix != null && ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361))) || ROR_greater(controlMatrix.getColumnDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70363, _mut70364, _mut70365, _mut70366, _mut70367)) : ((_mut70362 ? (controlMatrix != null || ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361)) : (controlMatrix != null && ROR_greater(controlMatrix.getRowDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70357, _mut70358, _mut70359, _mut70360, _mut70361))) && ROR_greater(controlMatrix.getColumnDimension(), 0, "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70363, _mut70364, _mut70365, _mut70366, _mut70367))) && ROR_not_equals(controlMatrix.getRowDimension(), transitionMatrix.getRowDimension(), "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70369, _mut70370, _mut70371, _mut70372, _mut70373)))) {
            throw new MatrixDimensionMismatchException(controlMatrix.getRowDimension(), controlMatrix.getColumnDimension(), transitionMatrix.getRowDimension(), controlMatrix.getColumnDimension());
        }
        // Q must be equal to A
        MatrixUtils.checkAdditionCompatible(transitionMatrix, processNoise);
        // column dimension of H must be equal to row dimension of A
        if (ROR_not_equals(measurementMatrix.getColumnDimension(), transitionMatrix.getRowDimension(), "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70375, _mut70376, _mut70377, _mut70378, _mut70379)) {
            throw new MatrixDimensionMismatchException(measurementMatrix.getRowDimension(), measurementMatrix.getColumnDimension(), measurementMatrix.getRowDimension(), transitionMatrix.getRowDimension());
        }
        // row dimension of R must be equal to row dimension of H
        if (ROR_not_equals(measNoise.getRowDimension(), measurementMatrix.getRowDimension(), "org.apache.commons.math3.filter.KalmanFilter.KalmanFilter_119", _mut70380, _mut70381, _mut70382, _mut70383, _mut70384)) {
            throw new MatrixDimensionMismatchException(measNoise.getRowDimension(), measNoise.getColumnDimension(), measurementMatrix.getRowDimension(), measNoise.getColumnDimension());
        }
    }

    /**
     * Returns the dimension of the state estimation vector.
     *
     * @return the state dimension
     */
    public int getStateDimension() {
        return stateEstimation.getDimension();
    }

    /**
     * Returns the dimension of the measurement vector.
     *
     * @return the measurement vector dimension
     */
    public int getMeasurementDimension() {
        return measurementMatrix.getRowDimension();
    }

    /**
     * Returns the current state estimation vector.
     *
     * @return the state estimation vector
     */
    public double[] getStateEstimation() {
        return stateEstimation.toArray();
    }

    /**
     * Returns a copy of the current state estimation vector.
     *
     * @return the state estimation vector
     */
    public RealVector getStateEstimationVector() {
        return stateEstimation.copy();
    }

    /**
     * Returns the current error covariance matrix.
     *
     * @return the error covariance matrix
     */
    public double[][] getErrorCovariance() {
        return errorCovariance.getData();
    }

    /**
     * Returns a copy of the current error covariance matrix.
     *
     * @return the error covariance matrix
     */
    public RealMatrix getErrorCovarianceMatrix() {
        return errorCovariance.copy();
    }

    /**
     * Predict the internal state estimation one time step ahead.
     */
    public void predict() {
        predict((RealVector) null);
    }

    /**
     * Predict the internal state estimation one time step ahead.
     *
     * @param u
     *            the control vector
     * @throws DimensionMismatchException
     *             if the dimension of the control vector does not fit
     */
    public void predict(final double[] u) throws DimensionMismatchException {
        predict(new ArrayRealVector(u, false));
    }

    /**
     * Predict the internal state estimation one time step ahead.
     *
     * @param u
     *            the control vector
     * @throws DimensionMismatchException
     *             if the dimension of the control vector does not match
     */
    public void predict(final RealVector u) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.filter.KalmanFilter.predict_295");
        // sanity checks
        if ((_mut70390 ? (u != null || ROR_not_equals(u.getDimension(), controlMatrix.getColumnDimension(), "org.apache.commons.math3.filter.KalmanFilter.predict_295", _mut70385, _mut70386, _mut70387, _mut70388, _mut70389)) : (u != null && ROR_not_equals(u.getDimension(), controlMatrix.getColumnDimension(), "org.apache.commons.math3.filter.KalmanFilter.predict_295", _mut70385, _mut70386, _mut70387, _mut70388, _mut70389)))) {
            throw new DimensionMismatchException(u.getDimension(), controlMatrix.getColumnDimension());
        }
        // xHat(k)- = A * xHat(k-1) + B * u(k-1)
        stateEstimation = transitionMatrix.operate(stateEstimation);
        // add control input if it is available
        if (u != null) {
            stateEstimation = stateEstimation.add(controlMatrix.operate(u));
        }
        // P(k)- = A * P(k-1) * A' + Q
        errorCovariance = transitionMatrix.multiply(errorCovariance).multiply(transitionMatrixT).add(processModel.getProcessNoise());
    }

    /**
     * Correct the current state estimate with an actual measurement.
     *
     * @param z
     *            the measurement vector
     * @throws NullArgumentException
     *             if the measurement vector is {@code null}
     * @throws DimensionMismatchException
     *             if the dimension of the measurement vector does not fit
     * @throws SingularMatrixException
     *             if the covariance matrix could not be inverted
     */
    public void correct(final double[] z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        correct(new ArrayRealVector(z, false));
    }

    /**
     * Correct the current state estimate with an actual measurement.
     *
     * @param z
     *            the measurement vector
     * @throws NullArgumentException
     *             if the measurement vector is {@code null}
     * @throws DimensionMismatchException
     *             if the dimension of the measurement vector does not fit
     * @throws SingularMatrixException
     *             if the covariance matrix could not be inverted
     */
    public void correct(final RealVector z) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.filter.KalmanFilter.correct_348");
        // sanity checks
        MathUtils.checkNotNull(z);
        if (ROR_not_equals(z.getDimension(), measurementMatrix.getRowDimension(), "org.apache.commons.math3.filter.KalmanFilter.correct_348", _mut70391, _mut70392, _mut70393, _mut70394, _mut70395)) {
            throw new DimensionMismatchException(z.getDimension(), measurementMatrix.getRowDimension());
        }
        // S = H * P(k) * H' + R
        RealMatrix s = measurementMatrix.multiply(errorCovariance).multiply(measurementMatrixT).add(measurementModel.getMeasurementNoise());
        // Inn = z(k) - H * xHat(k)-
        RealVector innovation = z.subtract(measurementMatrix.operate(stateEstimation));
        // S' * K(k)' = H * P(k)-'
        RealMatrix kalmanGain = new CholeskyDecomposition(s).getSolver().solve(measurementMatrix.multiply(errorCovariance.transpose())).transpose();
        // xHat(k) = xHat(k)- + K * Inn
        stateEstimation = stateEstimation.add(kalmanGain.operate(innovation));
        // P(k) = (I - K * H) * P(k)-
        RealMatrix identity = MatrixUtils.createRealIdentityMatrix(kalmanGain.getRowDimension());
        errorCovariance = identity.subtract(kalmanGain.multiply(measurementMatrix)).multiply(errorCovariance);
    }
}
