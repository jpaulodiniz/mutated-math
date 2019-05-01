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

import java.io.Serializable;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class mapping the part of a complete state or derivative that pertains
 * to a set of differential equations.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 * </p>
 * @see FieldExpandableODE
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldEquationsMapper<T extends RealFieldElement<T>> implements Serializable {

    @Conditional
    public static boolean _mut12177 = false, _mut12178 = false, _mut12179 = false, _mut12180 = false, _mut12181 = false, _mut12182 = false, _mut12183 = false, _mut12184 = false, _mut12185 = false, _mut12186 = false, _mut12187 = false, _mut12188 = false, _mut12189 = false, _mut12190 = false, _mut12191 = false, _mut12192 = false, _mut12193 = false, _mut12194 = false, _mut12195 = false, _mut12196 = false, _mut12197 = false, _mut12198 = false, _mut12199 = false, _mut12200 = false, _mut12201 = false, _mut12202 = false, _mut12203 = false, _mut12204 = false, _mut12205 = false, _mut12206 = false, _mut12207 = false, _mut12208 = false, _mut12209 = false, _mut12210 = false, _mut12211 = false, _mut12212 = false, _mut12213 = false, _mut12214 = false, _mut12215 = false, _mut12216 = false, _mut12217 = false, _mut12218 = false, _mut12219 = false, _mut12220 = false, _mut12221 = false, _mut12222 = false, _mut12223 = false, _mut12224 = false, _mut12225 = false, _mut12226 = false, _mut12227 = false, _mut12228 = false, _mut12229 = false, _mut12230 = false, _mut12231 = false, _mut12232 = false, _mut12233 = false, _mut12234 = false, _mut12235 = false, _mut12236 = false, _mut12237 = false, _mut12238 = false, _mut12239 = false, _mut12240 = false, _mut12241 = false, _mut12242 = false, _mut12243 = false, _mut12244 = false, _mut12245 = false, _mut12246 = false, _mut12247 = false, _mut12248 = false, _mut12249 = false, _mut12250 = false, _mut12251 = false, _mut12252 = false, _mut12253 = false, _mut12254 = false, _mut12255 = false, _mut12256 = false, _mut12257 = false, _mut12258 = false, _mut12259 = false, _mut12260 = false, _mut12261 = false, _mut12262 = false, _mut12263 = false, _mut12264 = false, _mut12265 = false, _mut12266 = false, _mut12267 = false, _mut12268 = false, _mut12269 = false, _mut12270 = false, _mut12271 = false, _mut12272 = false, _mut12273 = false, _mut12274 = false, _mut12275 = false, _mut12276 = false, _mut12277 = false, _mut12278 = false, _mut12279 = false, _mut12280 = false, _mut12281 = false, _mut12282 = false, _mut12283 = false, _mut12284 = false, _mut12285 = false, _mut12286 = false, _mut12287 = false, _mut12288 = false, _mut12289 = false, _mut12290 = false, _mut12291 = false, _mut12292 = false, _mut12293 = false, _mut12294 = false, _mut12295 = false, _mut12296 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20151114L;

    /**
     * Start indices of the components.
     */
    private final int[] start;

    /**
     * Create a mapper by adding a new equation to another mapper.
     * <p>
     * The new equation will have index {@code mapper.}{@link #getNumberOfEquations()},
     * or 0 if {@code mapper} is null.
     * </p>
     * @param mapper former mapper, with one equation less (null for first equation)
     * @param dimension dimension of the equation state vector
     */
    FieldEquationsMapper(final FieldEquationsMapper<T> mapper, final int dimension) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.FieldEquationsMapper_54");
        final int index = (mapper == null) ? 0 : mapper.getNumberOfEquations();
        this.start = new int[AOR_plus(index, 2, "org.apache.commons.math3.ode.FieldEquationsMapper.FieldEquationsMapper_54", _mut12177, _mut12178, _mut12179, _mut12180)];
        if (mapper == null) {
            start[0] = 0;
        } else {
            System.arraycopy(mapper.start, 0, start, 0, AOR_plus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.FieldEquationsMapper_54", _mut12181, _mut12182, _mut12183, _mut12184));
        }
        start[AOR_plus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.FieldEquationsMapper_54", _mut12185, _mut12186, _mut12187, _mut12188)] = AOR_plus(start[index], dimension, "org.apache.commons.math3.ode.FieldEquationsMapper.FieldEquationsMapper_54", _mut12189, _mut12190, _mut12191, _mut12192);
    }

    /**
     * Get the number of equations mapped.
     * @return number of equations mapped
     */
    public int getNumberOfEquations() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.getNumberOfEquations_68");
        return AOR_minus(start.length, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.getNumberOfEquations_68", _mut12193, _mut12194, _mut12195, _mut12196);
    }

    /**
     * Return the dimension of the complete set of equations.
     * <p>
     * The complete set of equations correspond to the primary set plus all secondary sets.
     * </p>
     * @return dimension of the complete set of equations
     */
    public int getTotalDimension() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.getTotalDimension_78");
        return start[AOR_minus(start.length, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.getTotalDimension_78", _mut12197, _mut12198, _mut12199, _mut12200)];
    }

    /**
     * Map a state to a complete flat array.
     * @param state state to map
     * @return flat array containing the mapped state, including primary and secondary components
     */
    public T[] mapState(final FieldODEState<T> state) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapState_86");
        final T[] y = MathArrays.buildArray(state.getTime().getField(), getTotalDimension());
        int index = 0;
        insertEquationData(index, state.getState(), y);
        while (ROR_less(++index, getNumberOfEquations(), "org.apache.commons.math3.ode.FieldEquationsMapper.mapState_86", _mut12201, _mut12202, _mut12203, _mut12204, _mut12205)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapState_86");
            insertEquationData(index, state.getSecondaryState(index), y);
        }
        return y;
    }

    /**
     * Map a state derivative to a complete flat array.
     * @param state state to map
     * @return flat array containing the mapped state derivative, including primary and secondary components
     */
    public T[] mapDerivative(final FieldODEStateAndDerivative<T> state) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapDerivative_100");
        final T[] yDot = MathArrays.buildArray(state.getTime().getField(), getTotalDimension());
        int index = 0;
        insertEquationData(index, state.getDerivative(), yDot);
        while (ROR_less(++index, getNumberOfEquations(), "org.apache.commons.math3.ode.FieldEquationsMapper.mapDerivative_100", _mut12206, _mut12207, _mut12208, _mut12209, _mut12210)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapDerivative_100");
            insertEquationData(index, state.getSecondaryDerivative(index), yDot);
        }
        return yDot;
    }

    /**
     * Map flat arrays to a state and derivative.
     * @param t time
     * @param y state array to map, including primary and secondary components
     * @param yDot state derivative array to map, including primary and secondary components
     * @return mapped state
     * @exception DimensionMismatchException if an array does not match total dimension
     */
    public FieldODEStateAndDerivative<T> mapStateAndDerivative(final T t, final T[] y, final T[] yDot) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117");
        if (ROR_not_equals(y.length, getTotalDimension(), "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12211, _mut12212, _mut12213, _mut12214, _mut12215)) {
            throw new DimensionMismatchException(y.length, getTotalDimension());
        }
        if (ROR_not_equals(yDot.length, getTotalDimension(), "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12216, _mut12217, _mut12218, _mut12219, _mut12220)) {
            throw new DimensionMismatchException(yDot.length, getTotalDimension());
        }
        final int n = getNumberOfEquations();
        int index = 0;
        final T[] state = extractEquationData(index, y);
        final T[] derivative = extractEquationData(index, yDot);
        if (ROR_less(n, 2, "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12221, _mut12222, _mut12223, _mut12224, _mut12225)) {
            return new FieldODEStateAndDerivative<T>(t, state, derivative);
        } else {
            final T[][] secondaryState = MathArrays.buildArray(t.getField(), AOR_minus(n, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12226, _mut12227, _mut12228, _mut12229), -1);
            final T[][] secondaryDerivative = MathArrays.buildArray(t.getField(), AOR_minus(n, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12230, _mut12231, _mut12232, _mut12233), -1);
            while (ROR_less(++index, getNumberOfEquations(), "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12242, _mut12243, _mut12244, _mut12245, _mut12246)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117");
                secondaryState[AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12234, _mut12235, _mut12236, _mut12237)] = extractEquationData(index, y);
                secondaryDerivative[AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.mapStateAndDerivative_117", _mut12238, _mut12239, _mut12240, _mut12241)] = extractEquationData(index, yDot);
            }
            return new FieldODEStateAndDerivative<T>(t, state, derivative, secondaryState, secondaryDerivative);
        }
    }

    /**
     * Extract equation data from a complete state or derivative array.
     * @param index index of the equation, must be between 0 included and
     * {@link #getNumberOfEquations()} (excluded)
     * @param complete complete state or derivative array from which
     * equation data should be retrieved
     * @return equation data
     * @exception MathIllegalArgumentException if index is out of range
     * @exception DimensionMismatchException if complete state has not enough elements
     */
    public T[] extractEquationData(final int index, final T[] complete) throws MathIllegalArgumentException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.extractEquationData_154");
        checkIndex(index);
        final int begin = start[index];
        final int end = start[AOR_plus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.extractEquationData_154", _mut12247, _mut12248, _mut12249, _mut12250)];
        if (ROR_less(complete.length, end, "org.apache.commons.math3.ode.FieldEquationsMapper.extractEquationData_154", _mut12251, _mut12252, _mut12253, _mut12254, _mut12255)) {
            throw new DimensionMismatchException(complete.length, end);
        }
        final int dimension = AOR_minus(end, begin, "org.apache.commons.math3.ode.FieldEquationsMapper.extractEquationData_154", _mut12256, _mut12257, _mut12258, _mut12259);
        final T[] equationData = MathArrays.buildArray(complete[0].getField(), dimension);
        System.arraycopy(complete, begin, equationData, 0, dimension);
        return equationData;
    }

    /**
     * Insert equation data into a complete state or derivative array.
     * @param index index of the equation, must be between 0 included and
     * {@link #getNumberOfEquations()} (excluded)
     * @param equationData equation data to be inserted into the complete array
     * @param complete placeholder where to put equation data (only the
     * part corresponding to the equation will be overwritten)
     * @exception DimensionMismatchException if either array has not enough elements
     */
    public void insertEquationData(final int index, T[] equationData, T[] complete) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.insertEquationData_176");
        checkIndex(index);
        final int begin = start[index];
        final int end = start[AOR_plus(index, 1, "org.apache.commons.math3.ode.FieldEquationsMapper.insertEquationData_176", _mut12260, _mut12261, _mut12262, _mut12263)];
        final int dimension = AOR_minus(end, begin, "org.apache.commons.math3.ode.FieldEquationsMapper.insertEquationData_176", _mut12264, _mut12265, _mut12266, _mut12267);
        if (ROR_less(complete.length, end, "org.apache.commons.math3.ode.FieldEquationsMapper.insertEquationData_176", _mut12268, _mut12269, _mut12270, _mut12271, _mut12272)) {
            throw new DimensionMismatchException(complete.length, end);
        }
        if (ROR_not_equals(equationData.length, dimension, "org.apache.commons.math3.ode.FieldEquationsMapper.insertEquationData_176", _mut12273, _mut12274, _mut12275, _mut12276, _mut12277)) {
            throw new DimensionMismatchException(equationData.length, dimension);
        }
        System.arraycopy(equationData, 0, complete, begin, dimension);
    }

    /**
     * Check equation index.
     * @param index index of the equation, must be between 0 included and
     * {@link #getNumberOfEquations()} (excluded)
     * @exception MathIllegalArgumentException if index is out of range
     */
    private void checkIndex(final int index) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196");
        if ((_mut12292 ? (ROR_less(index, 0, "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12278, _mut12279, _mut12280, _mut12281, _mut12282) && ROR_greater(index, AOR_minus(start.length, 2, "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12283, _mut12284, _mut12285, _mut12286), "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12287, _mut12288, _mut12289, _mut12290, _mut12291)) : (ROR_less(index, 0, "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12278, _mut12279, _mut12280, _mut12281, _mut12282) || ROR_greater(index, AOR_minus(start.length, 2, "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12283, _mut12284, _mut12285, _mut12286), "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12287, _mut12288, _mut12289, _mut12290, _mut12291)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, index, 0, AOR_minus(start.length, 2, "org.apache.commons.math3.ode.FieldEquationsMapper.checkIndex_196", _mut12293, _mut12294, _mut12295, _mut12296));
        }
    }
}
