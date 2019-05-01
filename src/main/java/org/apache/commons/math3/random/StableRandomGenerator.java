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
package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>This class provides a stable normalized random generator. It samples from a stable
 * distribution with location parameter 0 and scale 1.</p>
 *
 * <p>The implementation uses the Chambers-Mallows-Stuck method as described in
 * <i>Handbook of computational statistics: concepts and methods</i> by
 * James E. Gentle, Wolfgang H&auml;rdle, Yuichi Mori.</p>
 *
 * @since 3.0
 */
public class StableRandomGenerator implements NormalizedRandomGenerator {

    @Conditional
    public static boolean _mut52243 = false, _mut52244 = false, _mut52245 = false, _mut52246 = false, _mut52247 = false, _mut52248 = false, _mut52249 = false, _mut52250 = false, _mut52251 = false, _mut52252 = false, _mut52253 = false, _mut52254 = false, _mut52255 = false, _mut52256 = false, _mut52257 = false, _mut52258 = false, _mut52259 = false, _mut52260 = false, _mut52261 = false, _mut52262 = false, _mut52263 = false, _mut52264 = false, _mut52265 = false, _mut52266 = false, _mut52267 = false, _mut52268 = false, _mut52269 = false, _mut52270 = false, _mut52271 = false, _mut52272 = false, _mut52273 = false, _mut52274 = false, _mut52275 = false, _mut52276 = false, _mut52277 = false, _mut52278 = false, _mut52279 = false, _mut52280 = false, _mut52281 = false, _mut52282 = false, _mut52283 = false, _mut52284 = false, _mut52285 = false, _mut52286 = false, _mut52287 = false, _mut52288 = false, _mut52289 = false, _mut52290 = false, _mut52291 = false, _mut52292 = false, _mut52293 = false, _mut52294 = false, _mut52295 = false, _mut52296 = false, _mut52297 = false, _mut52298 = false, _mut52299 = false, _mut52300 = false, _mut52301 = false, _mut52302 = false, _mut52303 = false, _mut52304 = false, _mut52305 = false, _mut52306 = false, _mut52307 = false, _mut52308 = false, _mut52309 = false, _mut52310 = false, _mut52311 = false, _mut52312 = false, _mut52313 = false, _mut52314 = false, _mut52315 = false, _mut52316 = false, _mut52317 = false, _mut52318 = false, _mut52319 = false, _mut52320 = false, _mut52321 = false, _mut52322 = false, _mut52323 = false, _mut52324 = false, _mut52325 = false, _mut52326 = false, _mut52327 = false, _mut52328 = false, _mut52329 = false, _mut52330 = false, _mut52331 = false, _mut52332 = false, _mut52333 = false, _mut52334 = false, _mut52335 = false, _mut52336 = false, _mut52337 = false, _mut52338 = false, _mut52339 = false, _mut52340 = false, _mut52341 = false, _mut52342 = false, _mut52343 = false, _mut52344 = false, _mut52345 = false, _mut52346 = false, _mut52347 = false, _mut52348 = false, _mut52349 = false, _mut52350 = false, _mut52351 = false, _mut52352 = false, _mut52353 = false, _mut52354 = false, _mut52355 = false, _mut52356 = false, _mut52357 = false, _mut52358 = false, _mut52359 = false, _mut52360 = false, _mut52361 = false, _mut52362 = false, _mut52363 = false, _mut52364 = false, _mut52365 = false, _mut52366 = false, _mut52367 = false, _mut52368 = false, _mut52369 = false, _mut52370 = false, _mut52371 = false, _mut52372 = false, _mut52373 = false, _mut52374 = false, _mut52375 = false, _mut52376 = false, _mut52377 = false, _mut52378 = false, _mut52379 = false, _mut52380 = false, _mut52381 = false, _mut52382 = false, _mut52383 = false, _mut52384 = false, _mut52385 = false, _mut52386 = false, _mut52387 = false, _mut52388 = false, _mut52389 = false, _mut52390 = false, _mut52391 = false, _mut52392 = false, _mut52393 = false, _mut52394 = false, _mut52395 = false, _mut52396 = false, _mut52397 = false, _mut52398 = false, _mut52399 = false, _mut52400 = false, _mut52401 = false, _mut52402 = false, _mut52403 = false, _mut52404 = false, _mut52405 = false, _mut52406 = false, _mut52407 = false, _mut52408 = false, _mut52409 = false, _mut52410 = false, _mut52411 = false, _mut52412 = false, _mut52413 = false, _mut52414 = false, _mut52415 = false, _mut52416 = false, _mut52417 = false, _mut52418 = false, _mut52419 = false, _mut52420 = false, _mut52421 = false, _mut52422 = false, _mut52423 = false, _mut52424 = false, _mut52425 = false, _mut52426 = false, _mut52427 = false, _mut52428 = false, _mut52429 = false, _mut52430 = false, _mut52431 = false, _mut52432 = false, _mut52433 = false, _mut52434 = false, _mut52435 = false, _mut52436 = false, _mut52437 = false, _mut52438 = false, _mut52439 = false, _mut52440 = false, _mut52441 = false, _mut52442 = false, _mut52443 = false, _mut52444 = false, _mut52445 = false, _mut52446 = false, _mut52447 = false, _mut52448 = false, _mut52449 = false, _mut52450 = false, _mut52451 = false, _mut52452 = false, _mut52453 = false, _mut52454 = false, _mut52455 = false, _mut52456 = false, _mut52457 = false, _mut52458 = false, _mut52459 = false, _mut52460 = false, _mut52461 = false, _mut52462 = false, _mut52463 = false, _mut52464 = false, _mut52465 = false, _mut52466 = false, _mut52467 = false, _mut52468 = false, _mut52469 = false, _mut52470 = false, _mut52471 = false, _mut52472 = false, _mut52473 = false, _mut52474 = false, _mut52475 = false, _mut52476 = false;

    /**
     * Underlying generator.
     */
    private final RandomGenerator generator;

    /**
     * stability parameter
     */
    private final double alpha;

    /**
     * skewness parameter
     */
    private final double beta;

    /**
     * cache of expression value used in generation
     */
    private final double zeta;

    /**
     * Create a new generator.
     *
     * @param generator underlying random generator to use
     * @param alpha Stability parameter. Must be in range (0, 2]
     * @param beta Skewness parameter. Must be in range [-1, 1]
     * @throws NullArgumentException if generator is null
     * @throws OutOfRangeException if {@code alpha <= 0} or {@code alpha > 2}
     * or {@code beta < -1} or {@code beta > 1}
     */
    public StableRandomGenerator(final RandomGenerator generator, final double alpha, final double beta) throws NullArgumentException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57");
        if (generator == null) {
            throw new NullArgumentException();
        }
        if (!((_mut52253 ? (ROR_greater(alpha, 0d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52243, _mut52244, _mut52245, _mut52246, _mut52247) || ROR_less_equals(alpha, 2d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52248, _mut52249, _mut52250, _mut52251, _mut52252)) : (ROR_greater(alpha, 0d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52243, _mut52244, _mut52245, _mut52246, _mut52247) && ROR_less_equals(alpha, 2d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52248, _mut52249, _mut52250, _mut52251, _mut52252))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, alpha, 0, 2);
        }
        if (!((_mut52264 ? (ROR_greater_equals(beta, -1d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52254, _mut52255, _mut52256, _mut52257, _mut52258) || ROR_less_equals(beta, 1d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52259, _mut52260, _mut52261, _mut52262, _mut52263)) : (ROR_greater_equals(beta, -1d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52254, _mut52255, _mut52256, _mut52257, _mut52258) && ROR_less_equals(beta, 1d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52259, _mut52260, _mut52261, _mut52262, _mut52263))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, beta, -1, 1);
        }
        this.generator = generator;
        this.alpha = alpha;
        this.beta = beta;
        if ((_mut52275 ? (ROR_less(alpha, 2d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52265, _mut52266, _mut52267, _mut52268, _mut52269) || ROR_not_equals(beta, 0d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52270, _mut52271, _mut52272, _mut52273, _mut52274)) : (ROR_less(alpha, 2d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52265, _mut52266, _mut52267, _mut52268, _mut52269) && ROR_not_equals(beta, 0d, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52270, _mut52271, _mut52272, _mut52273, _mut52274)))) {
            zeta = AOR_multiply(beta, FastMath.tan(AOR_divide(AOR_multiply(FastMath.PI, alpha, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52276, _mut52277, _mut52278, _mut52279), 2, "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52280, _mut52281, _mut52282, _mut52283)), "org.apache.commons.math3.random.StableRandomGenerator.StableRandomGenerator_57", _mut52284, _mut52285, _mut52286, _mut52287);
        } else {
            zeta = 0d;
        }
    }

    /**
     * Generate a random scalar with zero location and unit scale.
     *
     * @return a random scalar with zero location and unit scale
     */
    public double nextNormalizedDouble() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89");
        // we need 2 uniform random numbers to calculate omega and phi
        double omega = -FastMath.log(generator.nextDouble());
        double phi = AOR_multiply(FastMath.PI, (AOR_minus(generator.nextDouble(), 0.5, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52288, _mut52289, _mut52290, _mut52291)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52292, _mut52293, _mut52294, _mut52295);
        // Normal distribution case (Box-Muller algorithm)
        if (ROR_equals(alpha, 2d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52296, _mut52297, _mut52298, _mut52299, _mut52300)) {
            return AOR_multiply(FastMath.sqrt(AOR_multiply(2d, omega, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52301, _mut52302, _mut52303, _mut52304)), FastMath.sin(phi), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52305, _mut52306, _mut52307, _mut52308);
        }
        double x;
        // Thus we can exclude it from the formula
        if (ROR_equals(beta, 0d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52309, _mut52310, _mut52311, _mut52312, _mut52313)) {
            // Cauchy distribution case
            if (ROR_equals(alpha, 1d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52436, _mut52437, _mut52438, _mut52439, _mut52440)) {
                x = FastMath.tan(phi);
            } else {
                x = AOR_divide(AOR_multiply(FastMath.pow(AOR_multiply(omega, FastMath.cos(AOR_multiply((AOR_minus(1, alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52441, _mut52442, _mut52443, _mut52444)), phi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52445, _mut52446, _mut52447, _mut52448)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52449, _mut52450, _mut52451, _mut52452), AOR_minus(AOR_divide(1d, alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52453, _mut52454, _mut52455, _mut52456), 1d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52457, _mut52458, _mut52459, _mut52460)), FastMath.sin(AOR_multiply(alpha, phi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52461, _mut52462, _mut52463, _mut52464)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52465, _mut52466, _mut52467, _mut52468), FastMath.pow(FastMath.cos(phi), AOR_divide(1d, alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52469, _mut52470, _mut52471, _mut52472)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52473, _mut52474, _mut52475, _mut52476);
            }
        } else {
            // Generic stable distribution
            double cosPhi = FastMath.cos(phi);
            // to avoid rounding errors around alpha = 1
            if (ROR_greater(FastMath.abs(AOR_minus(alpha, 1d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52314, _mut52315, _mut52316, _mut52317)), 1e-8, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52318, _mut52319, _mut52320, _mut52321, _mut52322)) {
                double alphaPhi = AOR_multiply(alpha, phi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52388, _mut52389, _mut52390, _mut52391);
                double invAlphaPhi = AOR_minus(phi, alphaPhi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52392, _mut52393, _mut52394, _mut52395);
                x = AOR_divide(AOR_multiply(AOR_divide((AOR_plus(FastMath.sin(alphaPhi), AOR_multiply(zeta, FastMath.cos(alphaPhi), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52396, _mut52397, _mut52398, _mut52399), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52400, _mut52401, _mut52402, _mut52403)), cosPhi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52404, _mut52405, _mut52406, _mut52407), (AOR_plus(FastMath.cos(invAlphaPhi), AOR_multiply(zeta, FastMath.sin(invAlphaPhi), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52408, _mut52409, _mut52410, _mut52411), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52412, _mut52413, _mut52414, _mut52415)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52416, _mut52417, _mut52418, _mut52419), FastMath.pow(AOR_multiply(omega, cosPhi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52420, _mut52421, _mut52422, _mut52423), AOR_divide((AOR_minus(1, alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52424, _mut52425, _mut52426, _mut52427)), alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52428, _mut52429, _mut52430, _mut52431)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52432, _mut52433, _mut52434, _mut52435);
            } else {
                double betaPhi = AOR_plus(AOR_divide(FastMath.PI, 2, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52323, _mut52324, _mut52325, _mut52326), AOR_multiply(beta, phi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52327, _mut52328, _mut52329, _mut52330), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52331, _mut52332, _mut52333, _mut52334);
                x = AOR_multiply(AOR_divide(2d, FastMath.PI, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52335, _mut52336, _mut52337, _mut52338), (AOR_minus(AOR_multiply(betaPhi, FastMath.tan(phi), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52339, _mut52340, _mut52341, _mut52342), AOR_multiply(beta, FastMath.log(AOR_divide(AOR_multiply(AOR_multiply(AOR_divide(FastMath.PI, 2d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52343, _mut52344, _mut52345, _mut52346), omega, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52347, _mut52348, _mut52349, _mut52350), cosPhi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52351, _mut52352, _mut52353, _mut52354), betaPhi, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52355, _mut52356, _mut52357, _mut52358)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52359, _mut52360, _mut52361, _mut52362), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52363, _mut52364, _mut52365, _mut52366)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52367, _mut52368, _mut52369, _mut52370);
                if (ROR_not_equals(alpha, 1d, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52371, _mut52372, _mut52373, _mut52374, _mut52375)) {
                    x += AOR_multiply(beta, FastMath.tan(AOR_divide(AOR_multiply(FastMath.PI, alpha, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52376, _mut52377, _mut52378, _mut52379), 2, "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52380, _mut52381, _mut52382, _mut52383)), "org.apache.commons.math3.random.StableRandomGenerator.nextNormalizedDouble_89", _mut52384, _mut52385, _mut52386, _mut52387);
                }
            }
        }
        return x;
    }
}
