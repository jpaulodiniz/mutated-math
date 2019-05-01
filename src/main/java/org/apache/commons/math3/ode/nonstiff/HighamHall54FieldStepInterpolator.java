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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class HighamHall54FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {

    @Conditional
    public static boolean _mut13257 = false, _mut13258 = false, _mut13259 = false, _mut13260 = false, _mut13261 = false, _mut13262 = false, _mut13263 = false, _mut13264 = false, _mut13265 = false, _mut13266 = false, _mut13267 = false, _mut13268 = false, _mut13269 = false, _mut13270 = false, _mut13271 = false, _mut13272 = false, _mut13273 = false, _mut13274 = false, _mut13275 = false, _mut13276 = false, _mut13277 = false, _mut13278 = false, _mut13279 = false, _mut13280 = false, _mut13281 = false, _mut13282 = false, _mut13283 = false, _mut13284 = false, _mut13285 = false, _mut13286 = false, _mut13287 = false, _mut13288 = false, _mut13289 = false, _mut13290 = false, _mut13291 = false, _mut13292 = false, _mut13293 = false, _mut13294 = false, _mut13295 = false, _mut13296 = false, _mut13297 = false, _mut13298 = false, _mut13299 = false, _mut13300 = false, _mut13301 = false, _mut13302 = false, _mut13303 = false, _mut13304 = false, _mut13305 = false, _mut13306 = false, _mut13307 = false, _mut13308 = false, _mut13309 = false, _mut13310 = false, _mut13311 = false, _mut13312 = false, _mut13313 = false, _mut13314 = false, _mut13315 = false, _mut13316 = false, _mut13317 = false, _mut13318 = false, _mut13319 = false, _mut13320 = false, _mut13321 = false, _mut13322 = false, _mut13323 = false, _mut13324 = false, _mut13325 = false, _mut13326 = false, _mut13327 = false, _mut13328 = false, _mut13329 = false, _mut13330 = false, _mut13331 = false, _mut13332 = false, _mut13333 = false, _mut13334 = false, _mut13335 = false, _mut13336 = false, _mut13337 = false, _mut13338 = false, _mut13339 = false, _mut13340 = false, _mut13341 = false, _mut13342 = false, _mut13343 = false, _mut13344 = false, _mut13345 = false, _mut13346 = false, _mut13347 = false, _mut13348 = false, _mut13349 = false, _mut13350 = false, _mut13351 = false, _mut13352 = false, _mut13353 = false, _mut13354 = false, _mut13355 = false, _mut13356 = false, _mut13357 = false, _mut13358 = false, _mut13359 = false, _mut13360 = false, _mut13361 = false, _mut13362 = false, _mut13363 = false, _mut13364 = false, _mut13365 = false, _mut13366 = false, _mut13367 = false, _mut13368 = false, _mut13369 = false, _mut13370 = false, _mut13371 = false, _mut13372 = false, _mut13373 = false, _mut13374 = false, _mut13375 = false, _mut13376 = false, _mut13377 = false, _mut13378 = false, _mut13379 = false, _mut13380 = false, _mut13381 = false, _mut13382 = false, _mut13383 = false, _mut13384 = false, _mut13385 = false, _mut13386 = false, _mut13387 = false, _mut13388 = false, _mut13389 = false, _mut13390 = false, _mut13391 = false, _mut13392 = false, _mut13393 = false, _mut13394 = false, _mut13395 = false, _mut13396 = false, _mut13397 = false, _mut13398 = false, _mut13399 = false, _mut13400 = false, _mut13401 = false, _mut13402 = false, _mut13403 = false, _mut13404 = false, _mut13405 = false, _mut13406 = false, _mut13407 = false, _mut13408 = false, _mut13409 = false, _mut13410 = false;

    /**
     * Simple constructor.
     * @param field field to which the time and state vector elements belong
     * @param forward integration direction indicator
     * @param yDotK slopes at the intermediate points
     * @param globalPreviousState start of the global step
     * @param globalCurrentState end of the global step
     * @param softPreviousState start of the restricted step
     * @param softCurrentState end of the restricted step
     * @param mapper equations mapper for the all equations
     */
    HighamHall54FieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HighamHall54FieldStepInterpolator<T> create(final Field<T> newField, final boolean newForward, final T[][] newYDotK, final FieldODEStateAndDerivative<T> newGlobalPreviousState, final FieldODEStateAndDerivative<T> newGlobalCurrentState, final FieldODEStateAndDerivative<T> newSoftPreviousState, final FieldODEStateAndDerivative<T> newSoftCurrentState, final FieldEquationsMapper<T> newMapper) {
        return new HighamHall54FieldStepInterpolator<T>(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(final FieldEquationsMapper<T> mapper, final T time, final T theta, final T thetaH, final T oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75");
        final T bDot0 = theta.multiply(theta.multiply(theta.multiply(-10.0).add(16.0)).add(AOR_divide(-15.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13257, _mut13258, _mut13259, _mut13260))).add(1);
        final T bDot1 = time.getField().getZero();
        final T bDot2 = theta.multiply(theta.multiply(theta.multiply(AOR_divide(135.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13269, _mut13270, _mut13271, _mut13272)).add(AOR_divide(-729.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13265, _mut13266, _mut13267, _mut13268))).add(AOR_divide(459.0, 16.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13261, _mut13262, _mut13263, _mut13264)));
        final T bDot3 = theta.multiply(theta.multiply(theta.multiply(-120.0).add(152.0)).add(-44.0));
        final T bDot4 = theta.multiply(theta.multiply(theta.multiply(AOR_divide(125.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13281, _mut13282, _mut13283, _mut13284)).add(AOR_divide(-625.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13277, _mut13278, _mut13279, _mut13280))).add(AOR_divide(375.0, 16.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13273, _mut13274, _mut13275, _mut13276)));
        final T bDot5 = theta.multiply(AOR_divide(5.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13285, _mut13286, _mut13287, _mut13288)).multiply(theta.multiply(2).subtract(1));
        final T[] interpolatedState;
        final T[] interpolatedDerivatives;
        if ((_mut13294 ? (getGlobalPreviousState() != null || ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13289, _mut13290, _mut13291, _mut13292, _mut13293)) : (getGlobalPreviousState() != null && ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13289, _mut13290, _mut13291, _mut13292, _mut13293)))) {
            final T b0 = thetaH.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-5.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13371, _mut13372, _mut13373, _mut13374)).add(AOR_divide(16.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13367, _mut13368, _mut13369, _mut13370))).add(AOR_divide(-15.0, 4.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13363, _mut13364, _mut13365, _mut13366))).add(1));
            final T b1 = time.getField().getZero();
            final T b2 = thetaH.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(135.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13383, _mut13384, _mut13385, _mut13386)).add(AOR_divide(-243.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13379, _mut13380, _mut13381, _mut13382))).add(AOR_divide(459.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13375, _mut13376, _mut13377, _mut13378))));
            final T b3 = thetaH.multiply(theta.multiply(theta.multiply(theta.multiply(-30.0).add(AOR_divide(152.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13387, _mut13388, _mut13389, _mut13390))).add(-22.0)));
            final T b4 = thetaH.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(125.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13399, _mut13400, _mut13401, _mut13402)).add(AOR_divide(-625.0, 24.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13395, _mut13396, _mut13397, _mut13398))).add(AOR_divide(375.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13391, _mut13392, _mut13393, _mut13394))));
            final T b5 = thetaH.multiply(theta.multiply(theta.multiply(AOR_divide(5.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13407, _mut13408, _mut13409, _mut13410)).add(AOR_divide(-5.0, 16.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13403, _mut13404, _mut13405, _mut13406))));
            interpolatedState = previousStateLinearCombination(b0, b1, b2, b3, b4, b5);
            interpolatedDerivatives = derivativeLinearCombination(bDot0, bDot1, bDot2, bDot3, bDot4, bDot5);
        } else {
            final T theta2 = theta.multiply(theta);
            final T h = thetaH.divide(theta);
            final T b0 = h.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-5.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13307, _mut13308, _mut13309, _mut13310)).add(AOR_divide(16.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13303, _mut13304, _mut13305, _mut13306))).add(AOR_divide(-15.0, 4.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13299, _mut13300, _mut13301, _mut13302))).add(1.0)).add(AOR_divide(-1.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13295, _mut13296, _mut13297, _mut13298)));
            final T b1 = time.getField().getZero();
            final T b2 = h.multiply(theta2.multiply(theta.multiply(theta.multiply(AOR_divide(135.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13323, _mut13324, _mut13325, _mut13326)).add(AOR_divide(-243.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13319, _mut13320, _mut13321, _mut13322))).add(AOR_divide(459.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13315, _mut13316, _mut13317, _mut13318))).add(AOR_divide(-27.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13311, _mut13312, _mut13313, _mut13314)));
            final T b3 = h.multiply(theta2.multiply(theta.multiply(theta.multiply(-30.0).add(AOR_divide(152.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13331, _mut13332, _mut13333, _mut13334))).add(-22.0)).add(AOR_divide(4.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13327, _mut13328, _mut13329, _mut13330)));
            final T b4 = h.multiply(theta2.multiply(theta.multiply(theta.multiply(AOR_divide(125.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13347, _mut13348, _mut13349, _mut13350)).add(AOR_divide(-625.0, 24.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13343, _mut13344, _mut13345, _mut13346))).add(AOR_divide(375.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13339, _mut13340, _mut13341, _mut13342))).add(AOR_divide(-125.0, 96.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13335, _mut13336, _mut13337, _mut13338)));
            final T b5 = h.multiply(theta2.multiply(theta.multiply(AOR_divide(5.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13359, _mut13360, _mut13361, _mut13362)).add(AOR_divide(-5.0, 16.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13355, _mut13356, _mut13357, _mut13358))).add(AOR_divide(-5.0, 48.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54FieldStepInterpolator.computeInterpolatedStateAndDerivatives_75", _mut13351, _mut13352, _mut13353, _mut13354)));
            interpolatedState = currentStateLinearCombination(b0, b1, b2, b3, b4, b5);
            interpolatedDerivatives = derivativeLinearCombination(bDot0, bDot1, bDot2, bDot3, bDot4, bDot5);
        }
        return new FieldODEStateAndDerivative<T>(time, interpolatedState, interpolatedDerivatives);
    }
}
