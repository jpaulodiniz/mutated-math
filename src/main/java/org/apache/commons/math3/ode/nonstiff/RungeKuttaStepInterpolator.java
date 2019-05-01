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
package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

abstract class RungeKuttaStepInterpolator extends AbstractStepInterpolator {

    @Conditional
    public static boolean _mut13499 = false, _mut13500 = false, _mut13501 = false, _mut13502 = false, _mut13503 = false, _mut13504 = false, _mut13505 = false, _mut13506 = false, _mut13507 = false, _mut13508 = false, _mut13509 = false, _mut13510 = false, _mut13511 = false, _mut13512 = false, _mut13513 = false, _mut13514 = false, _mut13515 = false, _mut13516 = false, _mut13517 = false, _mut13518 = false, _mut13519 = false, _mut13520 = false, _mut13521 = false, _mut13522 = false, _mut13523 = false, _mut13524 = false, _mut13525 = false, _mut13526 = false, _mut13527 = false, _mut13528 = false, _mut13529 = false, _mut13530 = false, _mut13531 = false, _mut13532 = false, _mut13533 = false, _mut13534 = false, _mut13535 = false, _mut13536 = false, _mut13537 = false, _mut13538 = false, _mut13539 = false, _mut13540 = false, _mut13541 = false, _mut13542 = false, _mut13543 = false, _mut13544 = false, _mut13545 = false, _mut13546 = false, _mut13547 = false, _mut13548 = false;

    /**
     * Previous state.
     */
    protected double[] previousState;

    /**
     * Slopes at the intermediate points
     */
    protected double[][] yDotK;

    /**
     * Reference to the integrator.
     */
    protected AbstractIntegrator integrator;

    /**
     * Simple constructor.
     * This constructor builds an instance that is not usable yet, the
     * {@link #reinitialize} method should be called before using the
     * instance in order to initialize the internal arrays. This
     * constructor is used only in order to delay the initialization in
     * some cases. The {@link RungeKuttaIntegrator} and {@link
     * EmbeddedRungeKuttaIntegrator} classes use the prototyping design
     * pattern to create the step interpolators by cloning an
     * uninitialized model and latter initializing the copy.
     */
    protected RungeKuttaStepInterpolator() {
        previousState = null;
        yDotK = null;
        integrator = null;
    }

    /**
     * Copy constructor.
     *
     * <p>The copied interpolator should have been finalized before the
     * copy, otherwise the copy will not be able to perform correctly any
     * interpolation and will throw a {@link NullPointerException}
     * later. Since we don't want this constructor to throw the
     * exceptions finalization may involve and since we don't want this
     * method to modify the state of the copied interpolator,
     * finalization is <strong>not</strong> done automatically, it
     * remains under user control.</p>
     *
     * <p>The copy is a deep copy: its arrays are separated from the
     * original arrays of the instance.</p>
     *
     * @param interpolator interpolator to copy from.
     */
    RungeKuttaStepInterpolator(final RungeKuttaStepInterpolator interpolator) {
        super(interpolator);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.RungeKuttaStepInterpolator_82");
        if (interpolator.currentState != null) {
            previousState = interpolator.previousState.clone();
            yDotK = new double[interpolator.yDotK.length][];
            for (int k = 0; ROR_less(k, interpolator.yDotK.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.RungeKuttaStepInterpolator_82", _mut13499, _mut13500, _mut13501, _mut13502, _mut13503); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.RungeKuttaStepInterpolator_82");
                yDotK[k] = interpolator.yDotK[k].clone();
            }
        } else {
            previousState = null;
            yDotK = null;
        }
        // the interpolator should have been finalized before
        integrator = null;
    }

    /**
     * Reinitialize the instance
     * <p>Some Runge-Kutta integrators need fewer functions evaluations
     * than their counterpart step interpolators. So the interpolator
     * should perform the last evaluations they need by themselves. The
     * {@link RungeKuttaIntegrator RungeKuttaIntegrator} and {@link
     * EmbeddedRungeKuttaIntegrator EmbeddedRungeKuttaIntegrator}
     * abstract classes call this method in order to let the step
     * interpolator perform the evaluations it needs. These evaluations
     * will be performed during the call to <code>doFinalize</code> if
     * any, i.e. only if the step handler either calls the {@link
     * AbstractStepInterpolator#finalizeStep finalizeStep} method or the
     * {@link AbstractStepInterpolator#getInterpolatedState
     * getInterpolatedState} method (for an interpolator which needs a
     * finalization) or if it clones the step interpolator.</p>
     * @param rkIntegrator integrator being used
     * @param y reference to the integrator array holding the state at
     * the end of the step
     * @param yDotArray reference to the integrator array holding all the
     * intermediate slopes
     * @param forward integration direction indicator
     * @param primaryMapper equations mapper for the primary equations set
     * @param secondaryMappers equations mappers for the secondary equations sets
     */
    public void reinitialize(final AbstractIntegrator rkIntegrator, final double[] y, final double[][] yDotArray, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        reinitialize(y, forward, primaryMapper, secondaryMappers);
        this.previousState = null;
        this.yDotK = yDotArray;
        this.integrator = rkIntegrator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shift() {
        previousState = currentState.clone();
        super.shift();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147");
        // save the state of the base class
        writeBaseExternal(out);
        // save the local attributes
        final int n = (currentState == null) ? -1 : currentState.length;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147", _mut13504, _mut13505, _mut13506, _mut13507, _mut13508); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147");
            out.writeDouble(previousState[i]);
        }
        final int kMax = (yDotK == null) ? -1 : yDotK.length;
        out.writeInt(kMax);
        for (int k = 0; ROR_less(k, kMax, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147", _mut13514, _mut13515, _mut13516, _mut13517, _mut13518); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147");
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147", _mut13509, _mut13510, _mut13511, _mut13512, _mut13513); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.writeExternal_147");
                out.writeDouble(yDotK[k][i]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173");
        // read the base class
        final double t = readBaseExternal(in);
        // read the local attributes
        final int n = (currentState == null) ? -1 : currentState.length;
        if (ROR_less(n, 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13519, _mut13520, _mut13521, _mut13522, _mut13523)) {
            previousState = null;
        } else {
            previousState = new double[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13524, _mut13525, _mut13526, _mut13527, _mut13528); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173");
                previousState[i] = in.readDouble();
            }
        }
        final int kMax = in.readInt();
        yDotK = (ROR_less(kMax, 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13529, _mut13530, _mut13531, _mut13532, _mut13533)) ? null : new double[kMax][];
        for (int k = 0; ROR_less(k, kMax, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13544, _mut13545, _mut13546, _mut13547, _mut13548); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173");
            yDotK[k] = (ROR_less(n, 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13534, _mut13535, _mut13536, _mut13537, _mut13538)) ? null : new double[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173", _mut13539, _mut13540, _mut13541, _mut13542, _mut13543); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator.readExternal_173");
                yDotK[k][i] = in.readDouble();
            }
        }
        integrator = null;
        if (currentState != null) {
            // we can now set the interpolated time and state
            setInterpolatedTime(t);
        } else {
            interpolatedTime = t;
        }
    }
}
