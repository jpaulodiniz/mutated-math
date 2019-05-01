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
package org.apache.commons.math3.util;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Miscellaneous utility functions.
 *
 * @see ArithmeticUtils
 * @see Precision
 * @see MathArrays
 */
public final class MathUtils {

    @Conditional
    public static boolean _mut51083 = false, _mut51084 = false, _mut51085 = false, _mut51086 = false, _mut51087 = false, _mut51088 = false, _mut51089 = false, _mut51090 = false, _mut51091 = false, _mut51092 = false, _mut51093 = false, _mut51094 = false, _mut51095 = false, _mut51096 = false, _mut51097 = false, _mut51098 = false, _mut51099 = false, _mut51100 = false, _mut51101 = false, _mut51102 = false, _mut51103 = false, _mut51104 = false, _mut51105 = false, _mut51106 = false, _mut51107 = false, _mut51108 = false, _mut51109 = false, _mut51110 = false, _mut51111 = false, _mut51112 = false, _mut51113 = false, _mut51114 = false, _mut51115 = false, _mut51116 = false, _mut51117 = false, _mut51118 = false, _mut51119 = false, _mut51120 = false, _mut51121 = false, _mut51122 = false, _mut51123 = false, _mut51124 = false, _mut51125 = false, _mut51126 = false, _mut51127 = false, _mut51128 = false, _mut51129 = false, _mut51130 = false, _mut51131 = false, _mut51132 = false, _mut51133 = false, _mut51134 = false, _mut51135 = false, _mut51136 = false, _mut51137 = false, _mut51138 = false, _mut51139 = false, _mut51140 = false, _mut51141 = false, _mut51142 = false, _mut51143 = false, _mut51144 = false, _mut51145 = false, _mut51146 = false, _mut51147 = false, _mut51148 = false, _mut51149 = false, _mut51150 = false, _mut51151 = false, _mut51152 = false, _mut51153 = false, _mut51154 = false, _mut51155 = false, _mut51156 = false, _mut51157 = false, _mut51158 = false, _mut51159 = false, _mut51160 = false, _mut51161 = false, _mut51162 = false, _mut51163 = false, _mut51164 = false, _mut51165 = false, _mut51166 = false, _mut51167 = false, _mut51168 = false, _mut51169 = false, _mut51170 = false, _mut51171 = false, _mut51172 = false, _mut51173 = false, _mut51174 = false, _mut51175 = false, _mut51176 = false, _mut51177 = false, _mut51178 = false, _mut51179 = false, _mut51180 = false, _mut51181 = false, _mut51182 = false, _mut51183 = false, _mut51184 = false, _mut51185 = false, _mut51186 = false, _mut51187 = false, _mut51188 = false, _mut51189 = false, _mut51190 = false, _mut51191 = false, _mut51192 = false, _mut51193 = false, _mut51194 = false, _mut51195 = false, _mut51196 = false, _mut51197 = false, _mut51198 = false, _mut51199 = false, _mut51200 = false, _mut51201 = false, _mut51202 = false, _mut51203 = false, _mut51204 = false, _mut51205 = false, _mut51206 = false, _mut51207 = false, _mut51208 = false, _mut51209 = false, _mut51210 = false, _mut51211 = false, _mut51212 = false, _mut51213 = false, _mut51214 = false, _mut51215 = false, _mut51216 = false, _mut51217 = false, _mut51218 = false, _mut51219 = false, _mut51220 = false, _mut51221 = false, _mut51222 = false, _mut51223 = false, _mut51224 = false, _mut51225 = false, _mut51226 = false, _mut51227 = false, _mut51228 = false, _mut51229 = false, _mut51230 = false, _mut51231 = false, _mut51232 = false, _mut51233 = false, _mut51234 = false, _mut51235 = false, _mut51236 = false, _mut51237 = false, _mut51238 = false, _mut51239 = false, _mut51240 = false, _mut51241 = false, _mut51242 = false, _mut51243 = false, _mut51244 = false, _mut51245 = false, _mut51246 = false, _mut51247 = false, _mut51248 = false, _mut51249 = false, _mut51250 = false, _mut51251 = false, _mut51252 = false, _mut51253 = false, _mut51254 = false, _mut51255 = false, _mut51256 = false, _mut51257 = false, _mut51258 = false, _mut51259 = false, _mut51260 = false, _mut51261 = false, _mut51262 = false, _mut51263 = false, _mut51264 = false, _mut51265 = false, _mut51266 = false, _mut51267 = false, _mut51268 = false, _mut51269 = false, _mut51270 = false, _mut51271 = false, _mut51272 = false, _mut51273 = false, _mut51274 = false, _mut51275 = false, _mut51276 = false, _mut51277 = false, _mut51278 = false, _mut51279 = false, _mut51280 = false, _mut51281 = false, _mut51282 = false, _mut51283 = false;

    /**
     * \(2\pi\)
     * @since 2.1
     */
    public static final double TWO_PI = AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.util.MathUtils.buildArray_612", _mut51083, _mut51084, _mut51085, _mut51086);

    /**
     * \(\pi^2\)
     * @since 3.4
     */
    public static final double PI_SQUARED = AOR_multiply(FastMath.PI, FastMath.PI, "org.apache.commons.math3.util.MathUtils.buildArray_612", _mut51087, _mut51088, _mut51089, _mut51090);

    /**
     * Class contains only static methods.
     */
    private MathUtils() {
    }

    /**
     * Returns an integer hash code representing the given double value.
     *
     * @param value the value to be hashed
     * @return the hash code
     */
    public static int hash(double value) {
        return new Double(value).hashCode();
    }

    /**
     * Returns {@code true} if the values are equal according to semantics of
     * {@link Double#equals(Object)}.
     *
     * @param x Value
     * @param y Value
     * @return {@code new Double(x).equals(new Double(y))}
     */
    public static boolean equals(double x, double y) {
        return new Double(x).equals(new Double(y));
    }

    /**
     * Returns an integer hash code representing the given double array.
     *
     * @param value the value to be hashed (may be null)
     * @return the hash code
     * @since 1.2
     */
    public static int hash(double[] value) {
        return Arrays.hashCode(value);
    }

    /**
     * Normalize an angle in a 2&pi; wide interval around a center value.
     * <p>This method has three main uses:</p>
     * <ul>
     *   <li>normalize an angle between 0 and 2&pi;:<br/>
     *       {@code a = MathUtils.normalizeAngle(a, FastMath.PI);}</li>
     *   <li>normalize an angle between -&pi; and +&pi;<br/>
     *       {@code a = MathUtils.normalizeAngle(a, 0.0);}</li>
     *   <li>compute the angle between two defining angular positions:<br>
     *       {@code angle = MathUtils.normalizeAngle(end, start) - start;}</li>
     * </ul>
     * <p>Note that due to numerical accuracy and since &pi; cannot be represented
     * exactly, the result interval is <em>closed</em>, it cannot be half-closed
     * as would be more satisfactory in a purely mathematical view.</p>
     * @param a angle to normalize
     * @param center center of the desired 2&pi; interval for the result
     * @return a-2k&pi; with integer k and center-&pi; &lt;= a-2k&pi; &lt;= center+&pi;
     * @since 1.2
     */
    public static double normalizeAngle(double a, double center) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.normalizeAngle_109");
        return AOR_minus(a, AOR_multiply(TWO_PI, FastMath.floor(AOR_divide((AOR_minus(AOR_plus(a, FastMath.PI, "org.apache.commons.math3.util.MathUtils.normalizeAngle_109", _mut51091, _mut51092, _mut51093, _mut51094), center, "org.apache.commons.math3.util.MathUtils.normalizeAngle_109", _mut51095, _mut51096, _mut51097, _mut51098)), TWO_PI, "org.apache.commons.math3.util.MathUtils.normalizeAngle_109", _mut51099, _mut51100, _mut51101, _mut51102)), "org.apache.commons.math3.util.MathUtils.normalizeAngle_109", _mut51103, _mut51104, _mut51105, _mut51106), "org.apache.commons.math3.util.MathUtils.normalizeAngle_109", _mut51107, _mut51108, _mut51109, _mut51110);
    }

    /**
     * Find the maximum of two field elements.
     * @param <T> the type of the field elements
     * @param e1 first element
     * @param e2 second element
     * @return max(a1, e2)
     * @since 3.6
     */
    public static <T extends RealFieldElement<T>> T max(final T e1, final T e2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.max_120");
        return ROR_greater_equals(e1.subtract(e2).getReal(), 0, "org.apache.commons.math3.util.MathUtils.max_120", _mut51111, _mut51112, _mut51113, _mut51114, _mut51115) ? e1 : e2;
    }

    /**
     * Find the minimum of two field elements.
     * @param <T> the type of the field elements
     * @param e1 first element
     * @param e2 second element
     * @return min(a1, e2)
     * @since 3.6
     */
    public static <T extends RealFieldElement<T>> T min(final T e1, final T e2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.min_131");
        return ROR_greater_equals(e1.subtract(e2).getReal(), 0, "org.apache.commons.math3.util.MathUtils.min_131", _mut51116, _mut51117, _mut51118, _mut51119, _mut51120) ? e2 : e1;
    }

    /**
     * <p>Reduce {@code |a - offset|} to the primary interval
     * {@code [0, |period|)}.</p>
     *
     * <p>Specifically, the value returned is <br/>
     * {@code a - |period| * floor((a - offset) / |period|) - offset}.</p>
     *
     * <p>If any of the parameters are {@code NaN} or infinite, the result is
     * {@code NaN}.</p>
     *
     * @param a Value to reduce.
     * @param period Period.
     * @param offset Value that will be mapped to {@code 0}.
     * @return the value, within the interval {@code [0 |period|)},
     * that corresponds to {@code a}.
     */
    public static double reduce(double a, double period, double offset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.reduce_151");
        final double p = FastMath.abs(period);
        return AOR_minus(AOR_minus(a, AOR_multiply(p, FastMath.floor(AOR_divide((AOR_minus(a, offset, "org.apache.commons.math3.util.MathUtils.reduce_151", _mut51121, _mut51122, _mut51123, _mut51124)), p, "org.apache.commons.math3.util.MathUtils.reduce_151", _mut51125, _mut51126, _mut51127, _mut51128)), "org.apache.commons.math3.util.MathUtils.reduce_151", _mut51129, _mut51130, _mut51131, _mut51132), "org.apache.commons.math3.util.MathUtils.reduce_151", _mut51133, _mut51134, _mut51135, _mut51136), offset, "org.apache.commons.math3.util.MathUtils.reduce_151", _mut51137, _mut51138, _mut51139, _mut51140);
    }

    /**
     * Returns the first argument with the sign of the second argument.
     *
     * @param magnitude Magnitude of the returned value.
     * @param sign Sign of the returned value.
     * @return a value with magnitude equal to {@code magnitude} and with the
     * same sign as the {@code sign} argument.
     * @throws MathArithmeticException if {@code magnitude == Byte.MIN_VALUE}
     * and {@code sign >= 0}.
     */
    public static byte copySign(byte magnitude, byte sign) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.copySign_168");
        if ((_mut51163 ? (((_mut51151 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51141, _mut51142, _mut51143, _mut51144, _mut51145) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51146, _mut51147, _mut51148, _mut51149, _mut51150)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51141, _mut51142, _mut51143, _mut51144, _mut51145) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51146, _mut51147, _mut51148, _mut51149, _mut51150)))) && ((_mut51162 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51152, _mut51153, _mut51154, _mut51155, _mut51156) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51157, _mut51158, _mut51159, _mut51160, _mut51161)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51152, _mut51153, _mut51154, _mut51155, _mut51156) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51157, _mut51158, _mut51159, _mut51160, _mut51161))))) : (((_mut51151 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51141, _mut51142, _mut51143, _mut51144, _mut51145) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51146, _mut51147, _mut51148, _mut51149, _mut51150)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51141, _mut51142, _mut51143, _mut51144, _mut51145) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51146, _mut51147, _mut51148, _mut51149, _mut51150)))) || ((_mut51162 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51152, _mut51153, _mut51154, _mut51155, _mut51156) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51157, _mut51158, _mut51159, _mut51160, _mut51161)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51152, _mut51153, _mut51154, _mut51155, _mut51156) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51157, _mut51158, _mut51159, _mut51160, _mut51161))))))) {
            // Sign is OK.
            return magnitude;
        } else if ((_mut51174 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51164, _mut51165, _mut51166, _mut51167, _mut51168) || ROR_equals(magnitude, Byte.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51169, _mut51170, _mut51171, _mut51172, _mut51173)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51164, _mut51165, _mut51166, _mut51167, _mut51168) && ROR_equals(magnitude, Byte.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_168", _mut51169, _mut51170, _mut51171, _mut51172, _mut51173)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        } else {
            // Flip sign.
            return (byte) -magnitude;
        }
    }

    /**
     * Returns the first argument with the sign of the second argument.
     *
     * @param magnitude Magnitude of the returned value.
     * @param sign Sign of the returned value.
     * @return a value with magnitude equal to {@code magnitude} and with the
     * same sign as the {@code sign} argument.
     * @throws MathArithmeticException if {@code magnitude == Short.MIN_VALUE}
     * and {@code sign >= 0}.
     */
    public static short copySign(short magnitude, short sign) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.copySign_191");
        if ((_mut51197 ? (((_mut51185 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51175, _mut51176, _mut51177, _mut51178, _mut51179) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51180, _mut51181, _mut51182, _mut51183, _mut51184)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51175, _mut51176, _mut51177, _mut51178, _mut51179) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51180, _mut51181, _mut51182, _mut51183, _mut51184)))) && ((_mut51196 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51186, _mut51187, _mut51188, _mut51189, _mut51190) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51191, _mut51192, _mut51193, _mut51194, _mut51195)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51186, _mut51187, _mut51188, _mut51189, _mut51190) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51191, _mut51192, _mut51193, _mut51194, _mut51195))))) : (((_mut51185 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51175, _mut51176, _mut51177, _mut51178, _mut51179) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51180, _mut51181, _mut51182, _mut51183, _mut51184)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51175, _mut51176, _mut51177, _mut51178, _mut51179) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51180, _mut51181, _mut51182, _mut51183, _mut51184)))) || ((_mut51196 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51186, _mut51187, _mut51188, _mut51189, _mut51190) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51191, _mut51192, _mut51193, _mut51194, _mut51195)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51186, _mut51187, _mut51188, _mut51189, _mut51190) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51191, _mut51192, _mut51193, _mut51194, _mut51195))))))) {
            // Sign is OK.
            return magnitude;
        } else if ((_mut51208 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51198, _mut51199, _mut51200, _mut51201, _mut51202) || ROR_equals(magnitude, Short.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51203, _mut51204, _mut51205, _mut51206, _mut51207)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51198, _mut51199, _mut51200, _mut51201, _mut51202) && ROR_equals(magnitude, Short.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_191", _mut51203, _mut51204, _mut51205, _mut51206, _mut51207)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        } else {
            // Flip sign.
            return (short) -magnitude;
        }
    }

    /**
     * Returns the first argument with the sign of the second argument.
     *
     * @param magnitude Magnitude of the returned value.
     * @param sign Sign of the returned value.
     * @return a value with magnitude equal to {@code magnitude} and with the
     * same sign as the {@code sign} argument.
     * @throws MathArithmeticException if {@code magnitude == Integer.MIN_VALUE}
     * and {@code sign >= 0}.
     */
    public static int copySign(int magnitude, int sign) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.copySign_214");
        if ((_mut51231 ? (((_mut51219 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51209, _mut51210, _mut51211, _mut51212, _mut51213) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51214, _mut51215, _mut51216, _mut51217, _mut51218)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51209, _mut51210, _mut51211, _mut51212, _mut51213) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51214, _mut51215, _mut51216, _mut51217, _mut51218)))) && ((_mut51230 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51220, _mut51221, _mut51222, _mut51223, _mut51224) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51225, _mut51226, _mut51227, _mut51228, _mut51229)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51220, _mut51221, _mut51222, _mut51223, _mut51224) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51225, _mut51226, _mut51227, _mut51228, _mut51229))))) : (((_mut51219 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51209, _mut51210, _mut51211, _mut51212, _mut51213) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51214, _mut51215, _mut51216, _mut51217, _mut51218)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51209, _mut51210, _mut51211, _mut51212, _mut51213) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51214, _mut51215, _mut51216, _mut51217, _mut51218)))) || ((_mut51230 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51220, _mut51221, _mut51222, _mut51223, _mut51224) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51225, _mut51226, _mut51227, _mut51228, _mut51229)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51220, _mut51221, _mut51222, _mut51223, _mut51224) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51225, _mut51226, _mut51227, _mut51228, _mut51229))))))) {
            // Sign is OK.
            return magnitude;
        } else if ((_mut51242 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51232, _mut51233, _mut51234, _mut51235, _mut51236) || ROR_equals(magnitude, Integer.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51237, _mut51238, _mut51239, _mut51240, _mut51241)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51232, _mut51233, _mut51234, _mut51235, _mut51236) && ROR_equals(magnitude, Integer.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_214", _mut51237, _mut51238, _mut51239, _mut51240, _mut51241)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        } else {
            // Flip sign.
            return -magnitude;
        }
    }

    /**
     * Returns the first argument with the sign of the second argument.
     *
     * @param magnitude Magnitude of the returned value.
     * @param sign Sign of the returned value.
     * @return a value with magnitude equal to {@code magnitude} and with the
     * same sign as the {@code sign} argument.
     * @throws MathArithmeticException if {@code magnitude == Long.MIN_VALUE}
     * and {@code sign >= 0}.
     */
    public static long copySign(long magnitude, long sign) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.copySign_237");
        if ((_mut51265 ? (((_mut51253 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51243, _mut51244, _mut51245, _mut51246, _mut51247) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51248, _mut51249, _mut51250, _mut51251, _mut51252)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51243, _mut51244, _mut51245, _mut51246, _mut51247) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51248, _mut51249, _mut51250, _mut51251, _mut51252)))) && ((_mut51264 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51254, _mut51255, _mut51256, _mut51257, _mut51258) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51259, _mut51260, _mut51261, _mut51262, _mut51263)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51254, _mut51255, _mut51256, _mut51257, _mut51258) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51259, _mut51260, _mut51261, _mut51262, _mut51263))))) : (((_mut51253 ? (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51243, _mut51244, _mut51245, _mut51246, _mut51247) || ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51248, _mut51249, _mut51250, _mut51251, _mut51252)) : (ROR_greater_equals(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51243, _mut51244, _mut51245, _mut51246, _mut51247) && ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51248, _mut51249, _mut51250, _mut51251, _mut51252)))) || ((_mut51264 ? (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51254, _mut51255, _mut51256, _mut51257, _mut51258) || ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51259, _mut51260, _mut51261, _mut51262, _mut51263)) : (ROR_less(magnitude, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51254, _mut51255, _mut51256, _mut51257, _mut51258) && ROR_less(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51259, _mut51260, _mut51261, _mut51262, _mut51263))))))) {
            // Sign is OK.
            return magnitude;
        } else if ((_mut51276 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51266, _mut51267, _mut51268, _mut51269, _mut51270) || ROR_equals(magnitude, Long.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51271, _mut51272, _mut51273, _mut51274, _mut51275)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51266, _mut51267, _mut51268, _mut51269, _mut51270) && ROR_equals(magnitude, Long.MIN_VALUE, "org.apache.commons.math3.util.MathUtils.copySign_237", _mut51271, _mut51272, _mut51273, _mut51274, _mut51275)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        } else {
            // Flip sign.
            return -magnitude;
        }
    }

    /**
     * Check that the argument is a real number.
     *
     * @param x Argument.
     * @throws NotFiniteNumberException if {@code x} is not a
     * finite real number.
     */
    public static void checkFinite(final double x) throws NotFiniteNumberException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.checkFinite_256");
        if ((_mut51277 ? (Double.isInfinite(x) && Double.isNaN(x)) : (Double.isInfinite(x) || Double.isNaN(x)))) {
            throw new NotFiniteNumberException(x);
        }
    }

    /**
     * Check that all the elements are real numbers.
     *
     * @param val Arguments.
     * @throws NotFiniteNumberException if any values of the array is not a
     * finite real number.
     */
    public static void checkFinite(final double[] val) throws NotFiniteNumberException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.checkFinite_270");
        for (int i = 0; ROR_less(i, val.length, "org.apache.commons.math3.util.MathUtils.checkFinite_270", _mut51279, _mut51280, _mut51281, _mut51282, _mut51283); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathUtils.checkFinite_270");
            final double x = val[i];
            if ((_mut51278 ? (Double.isInfinite(x) && Double.isNaN(x)) : (Double.isInfinite(x) || Double.isNaN(x)))) {
                throw new NotFiniteNumberException(LocalizedFormats.ARRAY_ELEMENT, x, i);
            }
        }
    }

    /**
     * Checks that an object is not null.
     *
     * @param o Object to be checked.
     * @param pattern Message pattern.
     * @param args Arguments to replace the placeholders in {@code pattern}.
     * @throws NullArgumentException if {@code o} is {@code null}.
     */
    public static void checkNotNull(Object o, Localizable pattern, Object... args) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException(pattern, args);
        }
    }

    /**
     * Checks that an object is not null.
     *
     * @param o Object to be checked.
     * @throws NullArgumentException if {@code o} is {@code null}.
     */
    public static void checkNotNull(Object o) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException();
        }
    }
}
