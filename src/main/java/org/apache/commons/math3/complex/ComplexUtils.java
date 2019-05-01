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
package org.apache.commons.math3.complex;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Static implementations of common
 * {@link org.apache.commons.math3.complex.Complex} utilities functions.
 */
public class ComplexUtils {

    @Conditional
    public static boolean _mut37486 = false, _mut37487 = false, _mut37488 = false, _mut37489 = false, _mut37490 = false, _mut37491 = false, _mut37492 = false, _mut37493 = false, _mut37494 = false, _mut37495 = false, _mut37496 = false, _mut37497 = false, _mut37498 = false, _mut37499 = false, _mut37500 = false, _mut37501 = false, _mut37502 = false, _mut37503 = false;

    /**
     * Default constructor.
     */
    private ComplexUtils() {
    }

    /**
     * Creates a complex number from the given polar representation.
     * <p>
     * The value returned is <code>r&middot;e<sup>i&middot;theta</sup></code>,
     * computed as <code>r&middot;cos(theta) + r&middot;sin(theta)i</code></p>
     * <p>
     * If either <code>r</code> or <code>theta</code> is NaN, or
     * <code>theta</code> is infinite, {@link Complex#NaN} is returned.</p>
     * <p>
     * If <code>r</code> is infinite and <code>theta</code> is finite,
     * infinite or NaN values may be returned in parts of the result, following
     * the rules for double arithmetic.<pre>
     * Examples:
     * <code>
     * polar2Complex(INFINITY, &pi;/4) = INFINITY + INFINITY i
     * polar2Complex(INFINITY, 0) = INFINITY + NaN i
     * polar2Complex(INFINITY, -&pi;/4) = INFINITY - INFINITY i
     * polar2Complex(INFINITY, 5&pi;/4) = -INFINITY - INFINITY i </code></pre></p>
     *
     * @param r the modulus of the complex number to create
     * @param theta  the argument of the complex number to create
     * @return <code>r&middot;e<sup>i&middot;theta</sup></code>
     * @throws MathIllegalArgumentException if {@code r} is negative.
     * @since 1.1
     */
    public static Complex polar2Complex(double r, double theta) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.ComplexUtils.polar2Complex_61");
        if (ROR_less(r, 0, "org.apache.commons.math3.complex.ComplexUtils.polar2Complex_61", _mut37486, _mut37487, _mut37488, _mut37489, _mut37490)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_COMPLEX_MODULE, r);
        }
        return new Complex(AOR_multiply(r, FastMath.cos(theta), "org.apache.commons.math3.complex.ComplexUtils.polar2Complex_61", _mut37491, _mut37492, _mut37493, _mut37494), AOR_multiply(r, FastMath.sin(theta), "org.apache.commons.math3.complex.ComplexUtils.polar2Complex_61", _mut37495, _mut37496, _mut37497, _mut37498));
    }

    /**
     * Convert an array of primitive doubles to an array of {@code Complex} objects.
     *
     * @param real Array of numbers to be converted to their {@code Complex}
     * equivalent.
     * @return an array of {@code Complex} objects.
     *
     * @since 3.1
     */
    public static Complex[] convertToComplex(double[] real) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.ComplexUtils.convertToComplex_78");
        final Complex[] c = new Complex[real.length];
        for (int i = 0; ROR_less(i, real.length, "org.apache.commons.math3.complex.ComplexUtils.convertToComplex_78", _mut37499, _mut37500, _mut37501, _mut37502, _mut37503); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.ComplexUtils.convertToComplex_78");
            c[i] = new Complex(real[i], 0);
        }
        return c;
    }
}
