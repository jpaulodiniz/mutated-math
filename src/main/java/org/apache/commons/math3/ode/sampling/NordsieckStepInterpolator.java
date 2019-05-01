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
package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class NordsieckStepInterpolator extends AbstractStepInterpolator {

    @Conditional
    public static boolean _mut11306 = false, _mut11307 = false, _mut11308 = false, _mut11309 = false, _mut11310 = false, _mut11311 = false, _mut11312 = false, _mut11313 = false, _mut11314 = false, _mut11315 = false, _mut11316 = false, _mut11317 = false, _mut11318 = false, _mut11319 = false, _mut11320 = false, _mut11321 = false, _mut11322 = false, _mut11323 = false, _mut11324 = false, _mut11325 = false, _mut11326 = false, _mut11327 = false, _mut11328 = false, _mut11329 = false, _mut11330 = false, _mut11331 = false, _mut11332 = false, _mut11333 = false, _mut11334 = false, _mut11335 = false, _mut11336 = false, _mut11337 = false, _mut11338 = false, _mut11339 = false, _mut11340 = false, _mut11341 = false, _mut11342 = false, _mut11343 = false, _mut11344 = false, _mut11345 = false, _mut11346 = false, _mut11347 = false, _mut11348 = false, _mut11349 = false, _mut11350 = false, _mut11351 = false, _mut11352 = false, _mut11353 = false, _mut11354 = false, _mut11355 = false, _mut11356 = false, _mut11357 = false, _mut11358 = false, _mut11359 = false, _mut11360 = false, _mut11361 = false, _mut11362 = false, _mut11363 = false, _mut11364 = false, _mut11365 = false, _mut11366 = false, _mut11367 = false, _mut11368 = false, _mut11369 = false, _mut11370 = false, _mut11371 = false, _mut11372 = false, _mut11373 = false, _mut11374 = false, _mut11375 = false, _mut11376 = false, _mut11377 = false, _mut11378 = false, _mut11379 = false, _mut11380 = false, _mut11381 = false, _mut11382 = false, _mut11383 = false, _mut11384 = false, _mut11385 = false, _mut11386 = false, _mut11387 = false, _mut11388 = false, _mut11389 = false, _mut11390 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -7179861704951334960L;

    /**
     * State variation.
     */
    protected double[] stateVariation;

    /**
     * Step size used in the first scaled derivative and Nordsieck vector.
     */
    private double scalingH;

    /**
     * Reference time for all arrays.
     * <p>Sometimes, the reference time is the same as previousTime,
     * sometimes it is the same as currentTime, so we use a separate
     * field to avoid any confusion.
     * </p>
     */
    private double referenceTime;

    /**
     * First scaled derivative.
     */
    private double[] scaled;

    /**
     * Nordsieck vector.
     */
    private Array2DRowRealMatrix nordsieck;

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link AbstractStepInterpolator#reinitialize} method should be called
     * before using the instance in order to initialize the internal arrays. This
     * constructor is used only in order to delay the initialization in
     * some cases.
     */
    public NordsieckStepInterpolator() {
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    public NordsieckStepInterpolator(final NordsieckStepInterpolator interpolator) {
        super(interpolator);
        scalingH = interpolator.scalingH;
        referenceTime = interpolator.referenceTime;
        if (interpolator.scaled != null) {
            scaled = interpolator.scaled.clone();
        }
        if (interpolator.nordsieck != null) {
            nordsieck = new Array2DRowRealMatrix(interpolator.nordsieck.getDataRef(), true);
        }
        if (interpolator.stateVariation != null) {
            stateVariation = interpolator.stateVariation.clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new NordsieckStepInterpolator(this);
    }

    /**
     * Reinitialize the instance.
     * <p>Beware that all arrays <em>must</em> be references to integrator
     * arrays, in order to ensure proper update without copy.</p>
     * @param y reference to the integrator array holding the state at
     * the end of the step
     * @param forward integration direction indicator
     * @param primaryMapper equations mapper for the primary equations set
     * @param secondaryMappers equations mappers for the secondary equations sets
     */
    @Override
    public void reinitialize(final double[] y, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        super.reinitialize(y, forward, primaryMapper, secondaryMappers);
        stateVariation = new double[y.length];
    }

    /**
     * Reinitialize the instance.
     * <p>Beware that all arrays <em>must</em> be references to integrator
     * arrays, in order to ensure proper update without copy.</p>
     * @param time time at which all arrays are defined
     * @param stepSize step size used in the scaled and Nordsieck arrays
     * @param scaledDerivative reference to the integrator array holding the first
     * scaled derivative
     * @param nordsieckVector reference to the integrator matrix holding the
     * Nordsieck vector
     */
    public void reinitialize(final double time, final double stepSize, final double[] scaledDerivative, final Array2DRowRealMatrix nordsieckVector) {
        this.referenceTime = time;
        this.scalingH = stepSize;
        this.scaled = scaledDerivative;
        this.nordsieck = nordsieckVector;
        // make sure the state and derivatives will depend on the new arrays
        setInterpolatedTime(getInterpolatedTime());
    }

    /**
     * Rescale the instance.
     * <p>Since the scaled and Nordsieck arrays are shared with the caller,
     * this method has the side effect of rescaling this arrays in the caller too.</p>
     * @param stepSize new step size to use in the scaled and Nordsieck arrays
     */
    public void rescale(final double stepSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147");
        final double ratio = AOR_divide(stepSize, scalingH, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147", _mut11306, _mut11307, _mut11308, _mut11309);
        for (int i = 0; ROR_less(i, scaled.length, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147", _mut11310, _mut11311, _mut11312, _mut11313, _mut11314); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147");
            scaled[i] *= ratio;
        }
        final double[][] nData = nordsieck.getDataRef();
        double power = ratio;
        for (int i = 0; ROR_less(i, nData.length, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147", _mut11320, _mut11321, _mut11322, _mut11323, _mut11324); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147");
            power *= ratio;
            final double[] nDataI = nData[i];
            for (int j = 0; ROR_less(j, nDataI.length, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147", _mut11315, _mut11316, _mut11317, _mut11318, _mut11319); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.rescale_147");
                nDataI[j] *= power;
            }
        }
        scalingH = stepSize;
    }

    /**
     * Get the state vector variation from current to interpolated state.
     * <p>This method is aimed at computing y(t<sub>interpolation</sub>)
     * -y(t<sub>current</sub>) accurately by avoiding the cancellation errors
     * that would occur if the subtraction were performed explicitly.</p>
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls.</p>
     * @return state vector at time {@link #getInterpolatedTime}
     * @see #getInterpolatedDerivatives()
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     */
    public double[] getInterpolatedStateVariation() throws MaxCountExceededException {
        // to make sure state variation is computed as a side effect
        getInterpolatedState();
        return stateVariation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188");
        final double x = AOR_minus(interpolatedTime, referenceTime, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11325, _mut11326, _mut11327, _mut11328);
        final double normalizedAbscissa = AOR_divide(x, scalingH, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11329, _mut11330, _mut11331, _mut11332);
        Arrays.fill(stateVariation, 0.0);
        Arrays.fill(interpolatedDerivatives, 0.0);
        // for the sake of numerical accuracy
        final double[][] nData = nordsieck.getDataRef();
        for (int i = nData.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11350, _mut11351, _mut11352, _mut11353, _mut11354); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188");
            final int order = AOR_plus(i, 2, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11333, _mut11334, _mut11335, _mut11336);
            final double[] nDataI = nData[i];
            final double power = FastMath.pow(normalizedAbscissa, order);
            for (int j = 0; ROR_less(j, nDataI.length, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11345, _mut11346, _mut11347, _mut11348, _mut11349); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188");
                final double d = AOR_multiply(nDataI[j], power, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11337, _mut11338, _mut11339, _mut11340);
                stateVariation[j] += d;
                interpolatedDerivatives[j] += AOR_multiply(order, d, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11341, _mut11342, _mut11343, _mut11344);
            }
        }
        for (int j = 0; ROR_less(j, currentState.length, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11375, _mut11376, _mut11377, _mut11378, _mut11379); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188");
            stateVariation[j] += AOR_multiply(scaled[j], normalizedAbscissa, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11355, _mut11356, _mut11357, _mut11358);
            interpolatedState[j] = AOR_plus(currentState[j], stateVariation[j], "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11359, _mut11360, _mut11361, _mut11362);
            interpolatedDerivatives[j] = AOR_divide((AOR_plus(interpolatedDerivatives[j], AOR_multiply(scaled[j], normalizedAbscissa, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11363, _mut11364, _mut11365, _mut11366), "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11367, _mut11368, _mut11369, _mut11370)), x, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.computeInterpolatedStateAndDerivatives_188", _mut11371, _mut11372, _mut11373, _mut11374);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.writeExternal_221");
        // save the state of the base class
        writeBaseExternal(out);
        // save the local attributes
        out.writeDouble(scalingH);
        out.writeDouble(referenceTime);
        final int n = (currentState == null) ? -1 : currentState.length;
        if (scaled == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.writeExternal_221", _mut11380, _mut11381, _mut11382, _mut11383, _mut11384); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.writeExternal_221");
                out.writeDouble(scaled[j]);
            }
        }
        if (nordsieck == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            out.writeObject(nordsieck);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.readExternal_254");
        // read the base class
        final double t = readBaseExternal(in);
        // read the local attributes
        scalingH = in.readDouble();
        referenceTime = in.readDouble();
        final int n = (currentState == null) ? -1 : currentState.length;
        final boolean hasScaled = in.readBoolean();
        if (hasScaled) {
            scaled = new double[n];
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.readExternal_254", _mut11385, _mut11386, _mut11387, _mut11388, _mut11389); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator.readExternal_254");
                scaled[j] = in.readDouble();
            }
        } else {
            scaled = null;
        }
        final boolean hasNordsieck = in.readBoolean();
        if (hasNordsieck) {
            nordsieck = (Array2DRowRealMatrix) in.readObject();
        } else {
            nordsieck = null;
        }
        if ((_mut11390 ? (hasScaled || hasNordsieck) : (hasScaled && hasNordsieck))) {
            // we can now set the interpolated time and state
            stateVariation = new double[n];
            setInterpolatedTime(t);
        } else {
            stateVariation = null;
        }
    }
}
