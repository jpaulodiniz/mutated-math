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
package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FieldODEState<T extends RealFieldElement<T>> {

    @Conditional
    public static boolean _mut11740 = false, _mut11741 = false, _mut11742 = false, _mut11743 = false, _mut11744 = false, _mut11745 = false, _mut11746 = false, _mut11747 = false, _mut11748 = false, _mut11749 = false, _mut11750 = false, _mut11751 = false, _mut11752 = false, _mut11753 = false, _mut11754 = false, _mut11755 = false, _mut11756 = false, _mut11757 = false, _mut11758 = false, _mut11759 = false, _mut11760 = false, _mut11761 = false, _mut11762 = false;

    /**
     * Time.
     */
    private final T time;

    /**
     * Main state at time.
     */
    private final T[] state;

    /**
     * Secondary state at time.
     */
    private final T[][] secondaryState;

    /**
     * Simple constructor.
     * <p>Calling this constructor is equivalent to call {@link
     * #FieldODEState(RealFieldElement, RealFieldElement[], RealFieldElement[][])
     * FieldODEState(time, state, null)}.</p>
     * @param time time
     * @param state state at time
     */
    public FieldODEState(T time, T[] state) {
        this(time, state, null);
    }

    /**
     * Simple constructor.
     * @param time time
     * @param state state at time
     * @param secondaryState state at time (may be null)
     */
    public FieldODEState(T time, T[] state, T[][] secondaryState) {
        this.time = time;
        this.state = state.clone();
        this.secondaryState = copy(time.getField(), secondaryState);
    }

    /**
     * Copy a two-dimensions array.
     * @param field field to which elements belong
     * @param original original array (may be null)
     * @return copied array or null if original array was null
     */
    protected T[][] copy(final Field<T> field, final T[][] original) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldODEState.copy_72");
        // special handling of null arrays
        if (original == null) {
            return null;
        }
        // allocate the array
        final T[][] copied = MathArrays.buildArray(field, original.length, -1);
        // copy content
        for (int i = 0; ROR_less(i, original.length, "org.apache.commons.math3.ode.FieldODEState.copy_72", _mut11740, _mut11741, _mut11742, _mut11743, _mut11744); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldODEState.copy_72");
            copied[i] = original[i].clone();
        }
        return copied;
    }

    /**
     * Get time.
     * @return time
     */
    public T getTime() {
        return time;
    }

    /**
     * Get main state dimension.
     * @return main state dimension
     */
    public int getStateDimension() {
        return state.length;
    }

    /**
     * Get main state at time.
     * @return main state at time
     */
    public T[] getState() {
        return state.clone();
    }

    /**
     * Get the number of secondary states.
     * @return number of secondary states.
     */
    public int getNumberOfSecondaryStates() {
        return secondaryState == null ? 0 : secondaryState.length;
    }

    /**
     * Get secondary state dimension.
     * @param index index of the secondary set as returned
     * by {@link FieldExpandableODE#addSecondaryEquations(FieldSecondaryEquations)}
     * (beware index 0 corresponds to main state, additional states start at 1)
     * @return secondary state dimension
     */
    public int getSecondaryStateDimension(final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldODEState.getSecondaryStateDimension_125");
        return ROR_equals(index, 0, "org.apache.commons.math3.ode.FieldODEState.getSecondaryStateDimension_125", _mut11745, _mut11746, _mut11747, _mut11748, _mut11749) ? state.length : secondaryState[AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldODEState.getSecondaryStateDimension_125", _mut11750, _mut11751, _mut11752, _mut11753)].length;
    }

    /**
     * Get secondary state at time.
     * @param index index of the secondary set as returned
     * by {@link FieldExpandableODE#addSecondaryEquations(FieldSecondaryEquations)}
     * (beware index 0 corresponds to main state, additional states start at 1)
     * @return secondary state at time
     */
    public T[] getSecondaryState(final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldODEState.getSecondaryState_135");
        return ROR_equals(index, 0, "org.apache.commons.math3.ode.FieldODEState.getSecondaryState_135", _mut11754, _mut11755, _mut11756, _mut11757, _mut11758) ? state.clone() : secondaryState[AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldODEState.getSecondaryState_135", _mut11759, _mut11760, _mut11761, _mut11762)].clone();
    }
}
