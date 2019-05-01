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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Representation of a Complex number, i.e. a number which has both a
 * real and imaginary part.
 * <p>
 * Implementations of arithmetic operations handle {@code NaN} and
 * infinite values according to the rules for {@link java.lang.Double}, i.e.
 * {@link #equals} is an equivalence relation for all instances that have
 * a {@code NaN} in either real or imaginary part, e.g. the following are
 * considered equal:
 * <ul>
 *  <li>{@code 1 + NaNi}</li>
 *  <li>{@code NaN + i}</li>
 *  <li>{@code NaN + NaNi}</li>
 * </ul><p>
 * Note that this contradicts the IEEE-754 standard for floating
 * point numbers (according to which the test {@code x == x} must fail if
 * {@code x} is {@code NaN}). The method
 * {@link org.apache.commons.math3.util.Precision#equals(double,double,int)
 * equals for primitive double} in {@link org.apache.commons.math3.util.Precision}
 * conforms with IEEE-754 while this class conforms with the standard behavior
 * for Java object types.</p>
 */
public class Complex implements FieldElement<Complex>, Serializable {

    @Conditional
    public static boolean _mut37003 = false, _mut37004 = false, _mut37005 = false, _mut37006 = false, _mut37007 = false, _mut37008 = false, _mut37009 = false, _mut37010 = false, _mut37011 = false, _mut37012 = false, _mut37013 = false, _mut37014 = false, _mut37015 = false, _mut37016 = false, _mut37017 = false, _mut37018 = false, _mut37019 = false, _mut37020 = false, _mut37021 = false, _mut37022 = false, _mut37023 = false, _mut37024 = false, _mut37025 = false, _mut37026 = false, _mut37027 = false, _mut37028 = false, _mut37029 = false, _mut37030 = false, _mut37031 = false, _mut37032 = false, _mut37033 = false, _mut37034 = false, _mut37035 = false, _mut37036 = false, _mut37037 = false, _mut37038 = false, _mut37039 = false, _mut37040 = false, _mut37041 = false, _mut37042 = false, _mut37043 = false, _mut37044 = false, _mut37045 = false, _mut37046 = false, _mut37047 = false, _mut37048 = false, _mut37049 = false, _mut37050 = false, _mut37051 = false, _mut37052 = false, _mut37053 = false, _mut37054 = false, _mut37055 = false, _mut37056 = false, _mut37057 = false, _mut37058 = false, _mut37059 = false, _mut37060 = false, _mut37061 = false, _mut37062 = false, _mut37063 = false, _mut37064 = false, _mut37065 = false, _mut37066 = false, _mut37067 = false, _mut37068 = false, _mut37069 = false, _mut37070 = false, _mut37071 = false, _mut37072 = false, _mut37073 = false, _mut37074 = false, _mut37075 = false, _mut37076 = false, _mut37077 = false, _mut37078 = false, _mut37079 = false, _mut37080 = false, _mut37081 = false, _mut37082 = false, _mut37083 = false, _mut37084 = false, _mut37085 = false, _mut37086 = false, _mut37087 = false, _mut37088 = false, _mut37089 = false, _mut37090 = false, _mut37091 = false, _mut37092 = false, _mut37093 = false, _mut37094 = false, _mut37095 = false, _mut37096 = false, _mut37097 = false, _mut37098 = false, _mut37099 = false, _mut37100 = false, _mut37101 = false, _mut37102 = false, _mut37103 = false, _mut37104 = false, _mut37105 = false, _mut37106 = false, _mut37107 = false, _mut37108 = false, _mut37109 = false, _mut37110 = false, _mut37111 = false, _mut37112 = false, _mut37113 = false, _mut37114 = false, _mut37115 = false, _mut37116 = false, _mut37117 = false, _mut37118 = false, _mut37119 = false, _mut37120 = false, _mut37121 = false, _mut37122 = false, _mut37123 = false, _mut37124 = false, _mut37125 = false, _mut37126 = false, _mut37127 = false, _mut37128 = false, _mut37129 = false, _mut37130 = false, _mut37131 = false, _mut37132 = false, _mut37133 = false, _mut37134 = false, _mut37135 = false, _mut37136 = false, _mut37137 = false, _mut37138 = false, _mut37139 = false, _mut37140 = false, _mut37141 = false, _mut37142 = false, _mut37143 = false, _mut37144 = false, _mut37145 = false, _mut37146 = false, _mut37147 = false, _mut37148 = false, _mut37149 = false, _mut37150 = false, _mut37151 = false, _mut37152 = false, _mut37153 = false, _mut37154 = false, _mut37155 = false, _mut37156 = false, _mut37157 = false, _mut37158 = false, _mut37159 = false, _mut37160 = false, _mut37161 = false, _mut37162 = false, _mut37163 = false, _mut37164 = false, _mut37165 = false, _mut37166 = false, _mut37167 = false, _mut37168 = false, _mut37169 = false, _mut37170 = false, _mut37171 = false, _mut37172 = false, _mut37173 = false, _mut37174 = false, _mut37175 = false, _mut37176 = false, _mut37177 = false, _mut37178 = false, _mut37179 = false, _mut37180 = false, _mut37181 = false, _mut37182 = false, _mut37183 = false, _mut37184 = false, _mut37185 = false, _mut37186 = false, _mut37187 = false, _mut37188 = false, _mut37189 = false, _mut37190 = false, _mut37191 = false, _mut37192 = false, _mut37193 = false, _mut37194 = false, _mut37195 = false, _mut37196 = false, _mut37197 = false, _mut37198 = false, _mut37199 = false, _mut37200 = false, _mut37201 = false, _mut37202 = false, _mut37203 = false, _mut37204 = false, _mut37205 = false, _mut37206 = false, _mut37207 = false, _mut37208 = false, _mut37209 = false, _mut37210 = false, _mut37211 = false, _mut37212 = false, _mut37213 = false, _mut37214 = false, _mut37215 = false, _mut37216 = false, _mut37217 = false, _mut37218 = false, _mut37219 = false, _mut37220 = false, _mut37221 = false, _mut37222 = false, _mut37223 = false, _mut37224 = false, _mut37225 = false, _mut37226 = false, _mut37227 = false, _mut37228 = false, _mut37229 = false, _mut37230 = false, _mut37231 = false, _mut37232 = false, _mut37233 = false, _mut37234 = false, _mut37235 = false, _mut37236 = false, _mut37237 = false, _mut37238 = false, _mut37239 = false, _mut37240 = false, _mut37241 = false, _mut37242 = false, _mut37243 = false, _mut37244 = false, _mut37245 = false, _mut37246 = false, _mut37247 = false, _mut37248 = false, _mut37249 = false, _mut37250 = false, _mut37251 = false, _mut37252 = false, _mut37253 = false, _mut37254 = false, _mut37255 = false, _mut37256 = false, _mut37257 = false, _mut37258 = false, _mut37259 = false, _mut37260 = false, _mut37261 = false, _mut37262 = false, _mut37263 = false, _mut37264 = false, _mut37265 = false, _mut37266 = false, _mut37267 = false, _mut37268 = false, _mut37269 = false, _mut37270 = false, _mut37271 = false, _mut37272 = false, _mut37273 = false, _mut37274 = false, _mut37275 = false, _mut37276 = false, _mut37277 = false, _mut37278 = false, _mut37279 = false, _mut37280 = false, _mut37281 = false, _mut37282 = false, _mut37283 = false, _mut37284 = false, _mut37285 = false, _mut37286 = false, _mut37287 = false, _mut37288 = false, _mut37289 = false, _mut37290 = false, _mut37291 = false, _mut37292 = false, _mut37293 = false, _mut37294 = false, _mut37295 = false, _mut37296 = false, _mut37297 = false, _mut37298 = false, _mut37299 = false, _mut37300 = false, _mut37301 = false, _mut37302 = false, _mut37303 = false, _mut37304 = false, _mut37305 = false, _mut37306 = false, _mut37307 = false, _mut37308 = false, _mut37309 = false, _mut37310 = false, _mut37311 = false, _mut37312 = false, _mut37313 = false, _mut37314 = false, _mut37315 = false, _mut37316 = false, _mut37317 = false, _mut37318 = false, _mut37319 = false, _mut37320 = false, _mut37321 = false, _mut37322 = false, _mut37323 = false, _mut37324 = false, _mut37325 = false, _mut37326 = false, _mut37327 = false, _mut37328 = false, _mut37329 = false, _mut37330 = false, _mut37331 = false, _mut37332 = false, _mut37333 = false, _mut37334 = false, _mut37335 = false, _mut37336 = false, _mut37337 = false, _mut37338 = false, _mut37339 = false, _mut37340 = false, _mut37341 = false, _mut37342 = false, _mut37343 = false, _mut37344 = false, _mut37345 = false, _mut37346 = false, _mut37347 = false, _mut37348 = false, _mut37349 = false, _mut37350 = false, _mut37351 = false, _mut37352 = false, _mut37353 = false, _mut37354 = false, _mut37355 = false, _mut37356 = false, _mut37357 = false, _mut37358 = false, _mut37359 = false, _mut37360 = false, _mut37361 = false, _mut37362 = false, _mut37363 = false, _mut37364 = false, _mut37365 = false, _mut37366 = false, _mut37367 = false, _mut37368 = false, _mut37369 = false, _mut37370 = false, _mut37371 = false, _mut37372 = false, _mut37373 = false, _mut37374 = false, _mut37375 = false, _mut37376 = false, _mut37377 = false, _mut37378 = false, _mut37379 = false, _mut37380 = false, _mut37381 = false, _mut37382 = false, _mut37383 = false, _mut37384 = false, _mut37385 = false, _mut37386 = false, _mut37387 = false, _mut37388 = false, _mut37389 = false, _mut37390 = false, _mut37391 = false, _mut37392 = false, _mut37393 = false, _mut37394 = false, _mut37395 = false, _mut37396 = false, _mut37397 = false, _mut37398 = false, _mut37399 = false, _mut37400 = false, _mut37401 = false, _mut37402 = false, _mut37403 = false, _mut37404 = false, _mut37405 = false, _mut37406 = false, _mut37407 = false, _mut37408 = false, _mut37409 = false, _mut37410 = false, _mut37411 = false, _mut37412 = false, _mut37413 = false, _mut37414 = false, _mut37415 = false, _mut37416 = false, _mut37417 = false, _mut37418 = false, _mut37419 = false, _mut37420 = false, _mut37421 = false, _mut37422 = false, _mut37423 = false, _mut37424 = false, _mut37425 = false, _mut37426 = false, _mut37427 = false, _mut37428 = false, _mut37429 = false, _mut37430 = false, _mut37431 = false, _mut37432 = false, _mut37433 = false, _mut37434 = false, _mut37435 = false, _mut37436 = false, _mut37437 = false, _mut37438 = false, _mut37439 = false, _mut37440 = false, _mut37441 = false, _mut37442 = false, _mut37443 = false, _mut37444 = false, _mut37445 = false, _mut37446 = false, _mut37447 = false, _mut37448 = false, _mut37449 = false, _mut37450 = false, _mut37451 = false, _mut37452 = false, _mut37453 = false, _mut37454 = false, _mut37455 = false, _mut37456 = false, _mut37457 = false, _mut37458 = false, _mut37459 = false, _mut37460 = false, _mut37461 = false, _mut37462 = false, _mut37463 = false, _mut37464 = false, _mut37465 = false, _mut37466 = false, _mut37467 = false, _mut37468 = false, _mut37469 = false, _mut37470 = false, _mut37471 = false, _mut37472 = false, _mut37473 = false, _mut37474 = false, _mut37475 = false, _mut37476 = false, _mut37477 = false, _mut37478 = false, _mut37479 = false, _mut37480 = false, _mut37481 = false, _mut37482 = false, _mut37483 = false, _mut37484 = false, _mut37485 = false;

    /**
     * The square root of -1. A number representing "0.0 + 1.0i"
     */
    public static final Complex I = new Complex(0.0, 1.0);

    /**
     * A complex number representing "NaN + NaNi"
     */
    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);

    /**
     * A complex number representing "+INF + INFi"
     */
    public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * A complex number representing "1.0 + 0.0i"
     */
    public static final Complex ONE = new Complex(1.0, 0.0);

    /**
     * A complex number representing "0.0 + 0.0i"
     */
    public static final Complex ZERO = new Complex(0.0, 0.0);

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -6195664516687396620L;

    /**
     * The imaginary part.
     */
    private final double imaginary;

    /**
     * The real part.
     */
    private final double real;

    /**
     * Record whether this complex number is equal to NaN.
     */
    private final transient boolean isNaN;

    /**
     * Record whether this complex number is infinite.
     */
    private final transient boolean isInfinite;

    /**
     * Create a complex number given only the real part.
     *
     * @param real Real part.
     */
    public Complex(double real) {
        this(real, 0.0);
    }

    /**
     * Create a complex number given the real and imaginary parts.
     *
     * @param real Real part.
     * @param imaginary Imaginary part.
     */
    public Complex(double real, double imaginary) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.Complex_96");
        this.real = real;
        this.imaginary = imaginary;
        isNaN = (_mut37003 ? (Double.isNaN(real) && Double.isNaN(imaginary)) : (Double.isNaN(real) || Double.isNaN(imaginary)));
        isInfinite = (_mut37005 ? (!isNaN || ((_mut37004 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))))) : (!isNaN && ((_mut37004 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))))));
    }

    /**
     * Return the absolute value of this complex number.
     * Returns {@code NaN} if either real or imaginary part is {@code NaN}
     * and {@code Double.POSITIVE_INFINITY} if neither part is {@code NaN},
     * but at least one part is infinite.
     *
     * @return the absolute value.
     */
    public double abs() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.abs_113");
        if (isNaN) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return Double.POSITIVE_INFINITY;
        }
        if (ROR_less(FastMath.abs(real), FastMath.abs(imaginary), "org.apache.commons.math3.complex.Complex.abs_113", _mut37006, _mut37007, _mut37008, _mut37009, _mut37010)) {
            if (ROR_equals(imaginary, 0.0, "org.apache.commons.math3.complex.Complex.abs_113", _mut37032, _mut37033, _mut37034, _mut37035, _mut37036)) {
                return FastMath.abs(real);
            }
            double q = AOR_divide(real, imaginary, "org.apache.commons.math3.complex.Complex.abs_113", _mut37037, _mut37038, _mut37039, _mut37040);
            return AOR_multiply(FastMath.abs(imaginary), FastMath.sqrt(AOR_plus(1, AOR_multiply(q, q, "org.apache.commons.math3.complex.Complex.abs_113", _mut37041, _mut37042, _mut37043, _mut37044), "org.apache.commons.math3.complex.Complex.abs_113", _mut37045, _mut37046, _mut37047, _mut37048)), "org.apache.commons.math3.complex.Complex.abs_113", _mut37049, _mut37050, _mut37051, _mut37052);
        } else {
            if (ROR_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.abs_113", _mut37011, _mut37012, _mut37013, _mut37014, _mut37015)) {
                return FastMath.abs(imaginary);
            }
            double q = AOR_divide(imaginary, real, "org.apache.commons.math3.complex.Complex.abs_113", _mut37016, _mut37017, _mut37018, _mut37019);
            return AOR_multiply(FastMath.abs(real), FastMath.sqrt(AOR_plus(1, AOR_multiply(q, q, "org.apache.commons.math3.complex.Complex.abs_113", _mut37020, _mut37021, _mut37022, _mut37023), "org.apache.commons.math3.complex.Complex.abs_113", _mut37024, _mut37025, _mut37026, _mut37027)), "org.apache.commons.math3.complex.Complex.abs_113", _mut37028, _mut37029, _mut37030, _mut37031);
        }
    }

    /**
     * Returns a {@code Complex} whose value is
     * {@code (this + addend)}.
     * Uses the definitional formula
     * <p>
     *   {@code (a + bi) + (c + di) = (a+c) + (b+d)i}
     * </p>
     * If either {@code this} or {@code addend} has a {@code NaN} value in
     * either part, {@link #NaN} is returned; otherwise {@code Infinite}
     * and {@code NaN} values are returned in the parts of the result
     * according to the rules for {@link java.lang.Double} arithmetic.
     *
     * @param  addend Value to be added to this {@code Complex}.
     * @return {@code this + addend}.
     * @throws NullArgumentException if {@code addend} is {@code null}.
     */
    public Complex add(Complex addend) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.add_151");
        MathUtils.checkNotNull(addend);
        if ((_mut37053 ? (isNaN && addend.isNaN) : (isNaN || addend.isNaN))) {
            return NaN;
        }
        return createComplex(AOR_plus(real, addend.getReal(), "org.apache.commons.math3.complex.Complex.add_151", _mut37054, _mut37055, _mut37056, _mut37057), AOR_plus(imaginary, addend.getImaginary(), "org.apache.commons.math3.complex.Complex.add_151", _mut37058, _mut37059, _mut37060, _mut37061));
    }

    /**
     * Returns a {@code Complex} whose value is {@code (this + addend)},
     * with {@code addend} interpreted as a real number.
     *
     * @param addend Value to be added to this {@code Complex}.
     * @return {@code this + addend}.
     * @see #add(Complex)
     */
    public Complex add(double addend) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.add_169");
        if ((_mut37062 ? (isNaN && Double.isNaN(addend)) : (isNaN || Double.isNaN(addend)))) {
            return NaN;
        }
        return createComplex(AOR_plus(real, addend, "org.apache.commons.math3.complex.Complex.add_169", _mut37063, _mut37064, _mut37065, _mut37066), imaginary);
    }

    /**
     * Returns the conjugate of this complex number.
     * The conjugate of {@code a + bi} is {@code a - bi}.
     * <p>
     * {@link #NaN} is returned if either the real or imaginary
     * part of this Complex number equals {@code Double.NaN}.
     * </p><p>
     * If the imaginary part is infinite, and the real part is not
     * {@code NaN}, the returned value has infinite imaginary part
     * of the opposite sign, e.g. the conjugate of
     * {@code 1 + POSITIVE_INFINITY i} is {@code 1 - NEGATIVE_INFINITY i}.
     * </p>
     * @return the conjugate of this Complex object.
     */
    public Complex conjugate() {
        if (isNaN) {
            return NaN;
        }
        return createComplex(real, -imaginary);
    }

    /**
     * Returns a {@code Complex} whose value is
     * {@code (this / divisor)}.
     * Implements the definitional formula
     * <pre>
     *  <code>
     *    a + bi          ac + bd + (bc - ad)i
     *    ----------- = -------------------------
     *    c + di         c<sup>2</sup> + d<sup>2</sup>
     *  </code>
     * </pre>
     * but uses
     * <a href="http://doi.acm.org/10.1145/1039813.1039814">
     * prescaling of operands</a> to limit the effects of overflows and
     * underflows in the computation.
     * <p>
     * {@code Infinite} and {@code NaN} values are handled according to the
     * following rules, applied in the order presented:
     * <ul>
     *  <li>If either {@code this} or {@code divisor} has a {@code NaN} value
     *   in either part, {@link #NaN} is returned.
     *  </li>
     *  <li>If {@code divisor} equals {@link #ZERO}, {@link #NaN} is returned.
     *  </li>
     *  <li>If {@code this} and {@code divisor} are both infinite,
     *   {@link #NaN} is returned.
     *  </li>
     *  <li>If {@code this} is finite (i.e., has no {@code Infinite} or
     *   {@code NaN} parts) and {@code divisor} is infinite (one or both parts
     *   infinite), {@link #ZERO} is returned.
     *  </li>
     *  <li>If {@code this} is infinite and {@code divisor} is finite,
     *   {@code NaN} values are returned in the parts of the result if the
     *   {@link java.lang.Double} rules applied to the definitional formula
     *   force {@code NaN} results.
     *  </li>
     * </ul>
     *
     * @param divisor Value by which this {@code Complex} is to be divided.
     * @return {@code this / divisor}.
     * @throws NullArgumentException if {@code divisor} is {@code null}.
     */
    public Complex divide(Complex divisor) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.divide_241");
        MathUtils.checkNotNull(divisor);
        if ((_mut37067 ? (isNaN && divisor.isNaN) : (isNaN || divisor.isNaN))) {
            return NaN;
        }
        final double c = divisor.getReal();
        final double d = divisor.getImaginary();
        if ((_mut37078 ? (ROR_equals(c, 0.0, "org.apache.commons.math3.complex.Complex.divide_241", _mut37068, _mut37069, _mut37070, _mut37071, _mut37072) || ROR_equals(d, 0.0, "org.apache.commons.math3.complex.Complex.divide_241", _mut37073, _mut37074, _mut37075, _mut37076, _mut37077)) : (ROR_equals(c, 0.0, "org.apache.commons.math3.complex.Complex.divide_241", _mut37068, _mut37069, _mut37070, _mut37071, _mut37072) && ROR_equals(d, 0.0, "org.apache.commons.math3.complex.Complex.divide_241", _mut37073, _mut37074, _mut37075, _mut37076, _mut37077)))) {
            return NaN;
        }
        if ((_mut37079 ? (divisor.isInfinite() || !isInfinite()) : (divisor.isInfinite() && !isInfinite()))) {
            return ZERO;
        }
        if (ROR_less(FastMath.abs(c), FastMath.abs(d), "org.apache.commons.math3.complex.Complex.divide_241", _mut37080, _mut37081, _mut37082, _mut37083, _mut37084)) {
            double q = AOR_divide(c, d, "org.apache.commons.math3.complex.Complex.divide_241", _mut37121, _mut37122, _mut37123, _mut37124);
            double denominator = AOR_plus(AOR_multiply(c, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37125, _mut37126, _mut37127, _mut37128), d, "org.apache.commons.math3.complex.Complex.divide_241", _mut37129, _mut37130, _mut37131, _mut37132);
            return createComplex(AOR_divide((AOR_plus(AOR_multiply(real, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37133, _mut37134, _mut37135, _mut37136), imaginary, "org.apache.commons.math3.complex.Complex.divide_241", _mut37137, _mut37138, _mut37139, _mut37140)), denominator, "org.apache.commons.math3.complex.Complex.divide_241", _mut37141, _mut37142, _mut37143, _mut37144), AOR_divide((AOR_minus(AOR_multiply(imaginary, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37145, _mut37146, _mut37147, _mut37148), real, "org.apache.commons.math3.complex.Complex.divide_241", _mut37149, _mut37150, _mut37151, _mut37152)), denominator, "org.apache.commons.math3.complex.Complex.divide_241", _mut37153, _mut37154, _mut37155, _mut37156));
        } else {
            double q = AOR_divide(d, c, "org.apache.commons.math3.complex.Complex.divide_241", _mut37085, _mut37086, _mut37087, _mut37088);
            double denominator = AOR_plus(AOR_multiply(d, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37089, _mut37090, _mut37091, _mut37092), c, "org.apache.commons.math3.complex.Complex.divide_241", _mut37093, _mut37094, _mut37095, _mut37096);
            return createComplex(AOR_divide((AOR_plus(AOR_multiply(imaginary, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37097, _mut37098, _mut37099, _mut37100), real, "org.apache.commons.math3.complex.Complex.divide_241", _mut37101, _mut37102, _mut37103, _mut37104)), denominator, "org.apache.commons.math3.complex.Complex.divide_241", _mut37105, _mut37106, _mut37107, _mut37108), AOR_divide((AOR_minus(imaginary, AOR_multiply(real, q, "org.apache.commons.math3.complex.Complex.divide_241", _mut37109, _mut37110, _mut37111, _mut37112), "org.apache.commons.math3.complex.Complex.divide_241", _mut37113, _mut37114, _mut37115, _mut37116)), denominator, "org.apache.commons.math3.complex.Complex.divide_241", _mut37117, _mut37118, _mut37119, _mut37120));
        }
    }

    /**
     * Returns a {@code Complex} whose value is {@code (this / divisor)},
     * with {@code divisor} interpreted as a real number.
     *
     * @param  divisor Value by which this {@code Complex} is to be divided.
     * @return {@code this / divisor}.
     * @see #divide(Complex)
     */
    public Complex divide(double divisor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.divide_279");
        if ((_mut37157 ? (isNaN && Double.isNaN(divisor)) : (isNaN || Double.isNaN(divisor)))) {
            return NaN;
        }
        if (ROR_equals(divisor, 0d, "org.apache.commons.math3.complex.Complex.divide_279", _mut37158, _mut37159, _mut37160, _mut37161, _mut37162)) {
            return NaN;
        }
        if (Double.isInfinite(divisor)) {
            return !isInfinite() ? ZERO : NaN;
        }
        return createComplex(AOR_divide(real, divisor, "org.apache.commons.math3.complex.Complex.divide_279", _mut37163, _mut37164, _mut37165, _mut37166), AOR_divide(imaginary, divisor, "org.apache.commons.math3.complex.Complex.divide_279", _mut37167, _mut37168, _mut37169, _mut37170));
    }

    /**
     * {@inheritDoc}
     */
    public Complex reciprocal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.reciprocal_294");
        if (isNaN) {
            return NaN;
        }
        if ((_mut37181 ? (ROR_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37171, _mut37172, _mut37173, _mut37174, _mut37175) || ROR_equals(imaginary, 0.0, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37176, _mut37177, _mut37178, _mut37179, _mut37180)) : (ROR_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37171, _mut37172, _mut37173, _mut37174, _mut37175) && ROR_equals(imaginary, 0.0, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37176, _mut37177, _mut37178, _mut37179, _mut37180)))) {
            return INF;
        }
        if (isInfinite) {
            return ZERO;
        }
        if (ROR_less(FastMath.abs(real), FastMath.abs(imaginary), "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37182, _mut37183, _mut37184, _mut37185, _mut37186)) {
            double q = AOR_divide(real, imaginary, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37207, _mut37208, _mut37209, _mut37210);
            double scale = AOR_divide(1., (AOR_plus(AOR_multiply(real, q, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37211, _mut37212, _mut37213, _mut37214), imaginary, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37215, _mut37216, _mut37217, _mut37218)), "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37219, _mut37220, _mut37221, _mut37222);
            return createComplex(AOR_multiply(scale, q, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37223, _mut37224, _mut37225, _mut37226), -scale);
        } else {
            double q = AOR_divide(imaginary, real, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37187, _mut37188, _mut37189, _mut37190);
            double scale = AOR_divide(1., (AOR_plus(AOR_multiply(imaginary, q, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37191, _mut37192, _mut37193, _mut37194), real, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37195, _mut37196, _mut37197, _mut37198)), "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37199, _mut37200, _mut37201, _mut37202);
            return createComplex(scale, AOR_multiply(-scale, q, "org.apache.commons.math3.complex.Complex.reciprocal_294", _mut37203, _mut37204, _mut37205, _mut37206));
        }
    }

    /**
     * Test for equality with another object.
     * If both the real and imaginary parts of two complex numbers
     * are exactly the same, and neither is {@code Double.NaN}, the two
     * Complex objects are considered to be equal.
     * The behavior is the same as for JDK's {@link Double#equals(Object)
     * Double}:
     * <ul>
     *  <li>All {@code NaN} values are considered to be equal,
     *   i.e, if either (or both) real and imaginary parts of the complex
     *   number are equal to {@code Double.NaN}, the complex number is equal
     *   to {@code NaN}.
     *  </li>
     *  <li>
     *   Instances constructed with different representations of zero (i.e.
     *   either "0" or "-0") are <em>not</em> considered to be equal.
     *  </li>
     * </ul>
     *
     * @param other Object to test for equality with this instance.
     * @return {@code true} if the objects are equal, {@code false} if object
     * is {@code null}, not an instance of {@code Complex}, or not equal to
     * this instance.
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.equals_342");
        if (this == other) {
            return true;
        }
        if (other instanceof Complex) {
            Complex c = (Complex) other;
            if (c.isNaN) {
                return isNaN;
            } else {
                return (_mut37227 ? (MathUtils.equals(real, c.real) || MathUtils.equals(imaginary, c.imaginary)) : (MathUtils.equals(real, c.real) && MathUtils.equals(imaginary, c.imaginary)));
            }
        }
        return false;
    }

    /**
     * Test for the floating-point equality between Complex objects.
     * It returns {@code true} if both arguments are equal or within the
     * range of allowed error (inclusive).
     *
     * @param x First value (cannot be {@code null}).
     * @param y Second value (cannot be {@code null}).
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between the real (resp. imaginary) parts of {@code x} and
     * {@code y}.
     * @return {@code true} if there are fewer than {@code maxUlps} floating
     * point values between the real (resp. imaginary) parts of {@code x}
     * and {@code y}.
     *
     * @see Precision#equals(double,double,int)
     * @since 3.3
     */
    public static boolean equals(Complex x, Complex y, int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.equals_376");
        return (_mut37228 ? (Precision.equals(x.real, y.real, maxUlps) || Precision.equals(x.imaginary, y.imaginary, maxUlps)) : (Precision.equals(x.real, y.real, maxUlps) && Precision.equals(x.imaginary, y.imaginary, maxUlps)));
    }

    /**
     * Returns {@code true} iff the values are equal as defined by
     * {@link #equals(Complex,Complex,int) equals(x, y, 1)}.
     *
     * @param x First value (cannot be {@code null}).
     * @param y Second value (cannot be {@code null}).
     * @return {@code true} if the values are equal.
     *
     * @since 3.3
     */
    public static boolean equals(Complex x, Complex y) {
        return equals(x, y, 1);
    }

    /**
     * Returns {@code true} if, both for the real part and for the imaginary
     * part, there is no double value strictly between the arguments or the
     * difference between them is within the range of allowed error
     * (inclusive).  Returns {@code false} if either of the arguments is NaN.
     *
     * @param x First value (cannot be {@code null}).
     * @param y Second value (cannot be {@code null}).
     * @param eps Amount of allowed absolute error.
     * @return {@code true} if the values are two adjacent floating point
     * numbers or they are within range of each other.
     *
     * @see Precision#equals(double,double,double)
     * @since 3.3
     */
    public static boolean equals(Complex x, Complex y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.equals_410");
        return (_mut37229 ? (Precision.equals(x.real, y.real, eps) || Precision.equals(x.imaginary, y.imaginary, eps)) : (Precision.equals(x.real, y.real, eps) && Precision.equals(x.imaginary, y.imaginary, eps)));
    }

    /**
     * Returns {@code true} if, both for the real part and for the imaginary
     * part, there is no double value strictly between the arguments or the
     * relative difference between them is smaller or equal to the given
     * tolerance. Returns {@code false} if either of the arguments is NaN.
     *
     * @param x First value (cannot be {@code null}).
     * @param y Second value (cannot be {@code null}).
     * @param eps Amount of allowed relative error.
     * @return {@code true} if the values are two adjacent floating point
     * numbers or they are within range of each other.
     *
     * @see Precision#equalsWithRelativeTolerance(double,double,double)
     * @since 3.3
     */
    public static boolean equalsWithRelativeTolerance(Complex x, Complex y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.equalsWithRelativeTolerance_430");
        return (_mut37230 ? (Precision.equalsWithRelativeTolerance(x.real, y.real, eps) || Precision.equalsWithRelativeTolerance(x.imaginary, y.imaginary, eps)) : (Precision.equalsWithRelativeTolerance(x.real, y.real, eps) && Precision.equalsWithRelativeTolerance(x.imaginary, y.imaginary, eps)));
    }

    /**
     * Get a hashCode for the complex number.
     * Any {@code Double.NaN} value in real or imaginary part produces
     * the same hash code {@code 7}.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.hashCode_443");
        if (isNaN) {
            return 7;
        }
        return AOR_multiply(37, (AOR_plus(AOR_multiply(17, MathUtils.hash(imaginary), "org.apache.commons.math3.complex.Complex.hashCode_443", _mut37231, _mut37232, _mut37233, _mut37234), MathUtils.hash(real), "org.apache.commons.math3.complex.Complex.hashCode_443", _mut37235, _mut37236, _mut37237, _mut37238)), "org.apache.commons.math3.complex.Complex.hashCode_443", _mut37239, _mut37240, _mut37241, _mut37242);
    }

    /**
     * Access the imaginary part.
     *
     * @return the imaginary part.
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Access the real part.
     *
     * @return the real part.
     */
    public double getReal() {
        return real;
    }

    /**
     * Checks whether either or both parts of this complex number is
     * {@code NaN}.
     *
     * @return true if either or both parts of this complex number is
     * {@code NaN}; false otherwise.
     */
    public boolean isNaN() {
        return isNaN;
    }

    /**
     * Checks whether either the real or imaginary part of this complex number
     * takes an infinite value (either {@code Double.POSITIVE_INFINITY} or
     * {@code Double.NEGATIVE_INFINITY}) and neither part
     * is {@code NaN}.
     *
     * @return true if one or both parts of this complex number are infinite
     * and neither part is {@code NaN}.
     */
    public boolean isInfinite() {
        return isInfinite;
    }

    /**
     * Returns a {@code Complex} whose value is {@code this * factor}.
     * Implements preliminary checks for {@code NaN} and infinity followed by
     * the definitional formula:
     * <p>
     *   {@code (a + bi)(c + di) = (ac - bd) + (ad + bc)i}
     * </p>
     * Returns {@link #NaN} if either {@code this} or {@code factor} has one or
     * more {@code NaN} parts.
     * <p>
     * Returns {@link #INF} if neither {@code this} nor {@code factor} has one
     * or more {@code NaN} parts and if either {@code this} or {@code factor}
     * has one or more infinite parts (same result is returned regardless of
     * the sign of the components).
     * </p><p>
     * Returns finite values in components of the result per the definitional
     * formula in all remaining cases.</p>
     *
     * @param  factor value to be multiplied by this {@code Complex}.
     * @return {@code this * factor}.
     * @throws NullArgumentException if {@code factor} is {@code null}.
     */
    public Complex multiply(Complex factor) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.multiply_516");
        MathUtils.checkNotNull(factor);
        if ((_mut37243 ? (isNaN && factor.isNaN) : (isNaN || factor.isNaN))) {
            return NaN;
        }
        if ((_mut37246 ? ((_mut37245 ? ((_mut37244 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) && Double.isInfinite(factor.real)) : ((_mut37244 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) || Double.isInfinite(factor.real))) && Double.isInfinite(factor.imaginary)) : ((_mut37245 ? ((_mut37244 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) && Double.isInfinite(factor.real)) : ((_mut37244 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) || Double.isInfinite(factor.real))) || Double.isInfinite(factor.imaginary)))) {
            // we don't use isInfinite() to avoid testing for NaN again
            return INF;
        }
        return createComplex(AOR_minus(AOR_multiply(real, factor.real, "org.apache.commons.math3.complex.Complex.multiply_516", _mut37247, _mut37248, _mut37249, _mut37250), AOR_multiply(imaginary, factor.imaginary, "org.apache.commons.math3.complex.Complex.multiply_516", _mut37251, _mut37252, _mut37253, _mut37254), "org.apache.commons.math3.complex.Complex.multiply_516", _mut37255, _mut37256, _mut37257, _mut37258), AOR_plus(AOR_multiply(real, factor.imaginary, "org.apache.commons.math3.complex.Complex.multiply_516", _mut37259, _mut37260, _mut37261, _mut37262), AOR_multiply(imaginary, factor.real, "org.apache.commons.math3.complex.Complex.multiply_516", _mut37263, _mut37264, _mut37265, _mut37266), "org.apache.commons.math3.complex.Complex.multiply_516", _mut37267, _mut37268, _mut37269, _mut37270));
    }

    /**
     * Returns a {@code Complex} whose value is {@code this * factor}, with {@code factor}
     * interpreted as a integer number.
     *
     * @param  factor value to be multiplied by this {@code Complex}.
     * @return {@code this * factor}.
     * @see #multiply(Complex)
     */
    public Complex multiply(final int factor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.multiply_541");
        if (isNaN) {
            return NaN;
        }
        if ((_mut37271 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary)))) {
            return INF;
        }
        return createComplex(AOR_multiply(real, factor, "org.apache.commons.math3.complex.Complex.multiply_541", _mut37272, _mut37273, _mut37274, _mut37275), AOR_multiply(imaginary, factor, "org.apache.commons.math3.complex.Complex.multiply_541", _mut37276, _mut37277, _mut37278, _mut37279));
    }

    /**
     * Returns a {@code Complex} whose value is {@code this * factor}, with {@code factor}
     * interpreted as a real number.
     *
     * @param  factor value to be multiplied by this {@code Complex}.
     * @return {@code this * factor}.
     * @see #multiply(Complex)
     */
    public Complex multiply(double factor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.multiply_560");
        if ((_mut37280 ? (isNaN && Double.isNaN(factor)) : (isNaN || Double.isNaN(factor)))) {
            return NaN;
        }
        if ((_mut37282 ? ((_mut37281 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) && Double.isInfinite(factor)) : ((_mut37281 ? (Double.isInfinite(real) && Double.isInfinite(imaginary)) : (Double.isInfinite(real) || Double.isInfinite(imaginary))) || Double.isInfinite(factor)))) {
            // we don't use isInfinite() to avoid testing for NaN again
            return INF;
        }
        return createComplex(AOR_multiply(real, factor, "org.apache.commons.math3.complex.Complex.multiply_560", _mut37283, _mut37284, _mut37285, _mut37286), AOR_multiply(imaginary, factor, "org.apache.commons.math3.complex.Complex.multiply_560", _mut37287, _mut37288, _mut37289, _mut37290));
    }

    /**
     * Returns a {@code Complex} whose value is {@code (-this)}.
     * Returns {@code NaN} if either real or imaginary
     * part of this Complex number is {@code Double.NaN}.
     *
     * @return {@code -this}.
     */
    public Complex negate() {
        if (isNaN) {
            return NaN;
        }
        return createComplex(-real, -imaginary);
    }

    /**
     * Returns a {@code Complex} whose value is
     * {@code (this - subtrahend)}.
     * Uses the definitional formula
     * <p>
     *  {@code (a + bi) - (c + di) = (a-c) + (b-d)i}
     * </p>
     * If either {@code this} or {@code subtrahend} has a {@code NaN]} value in either part,
     * {@link #NaN} is returned; otherwise infinite and {@code NaN} values are
     * returned in the parts of the result according to the rules for
     * {@link java.lang.Double} arithmetic.
     *
     * @param  subtrahend value to be subtracted from this {@code Complex}.
     * @return {@code this - subtrahend}.
     * @throws NullArgumentException if {@code subtrahend} is {@code null}.
     */
    public Complex subtract(Complex subtrahend) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.subtract_604");
        MathUtils.checkNotNull(subtrahend);
        if ((_mut37291 ? (isNaN && subtrahend.isNaN) : (isNaN || subtrahend.isNaN))) {
            return NaN;
        }
        return createComplex(AOR_minus(real, subtrahend.getReal(), "org.apache.commons.math3.complex.Complex.subtract_604", _mut37292, _mut37293, _mut37294, _mut37295), AOR_minus(imaginary, subtrahend.getImaginary(), "org.apache.commons.math3.complex.Complex.subtract_604", _mut37296, _mut37297, _mut37298, _mut37299));
    }

    /**
     * Returns a {@code Complex} whose value is
     * {@code (this - subtrahend)}.
     *
     * @param  subtrahend value to be subtracted from this {@code Complex}.
     * @return {@code this - subtrahend}.
     * @see #subtract(Complex)
     */
    public Complex subtract(double subtrahend) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.subtract_623");
        if ((_mut37300 ? (isNaN && Double.isNaN(subtrahend)) : (isNaN || Double.isNaN(subtrahend)))) {
            return NaN;
        }
        return createComplex(AOR_minus(real, subtrahend, "org.apache.commons.math3.complex.Complex.subtract_623", _mut37301, _mut37302, _mut37303, _mut37304), imaginary);
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/InverseCosine.html" TARGET="_top">
     * inverse cosine</a> of this complex number.
     * Implements the formula:
     * <p>
     *  {@code acos(z) = -i (log(z + i (sqrt(1 - z<sup>2</sup>))))}
     * </p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN} or infinite.
     *
     * @return the inverse cosine of this complex number.
     * @since 1.2
     */
    public Complex acos() {
        if (isNaN) {
            return NaN;
        }
        return this.add(this.sqrt1z().multiply(I)).log().multiply(I.negate());
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/InverseSine.html" TARGET="_top">
     * inverse sine</a> of this complex number.
     * Implements the formula:
     * <p>
     *  {@code asin(z) = -i (log(sqrt(1 - z<sup>2</sup>) + iz))}
     * </p><p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN} or infinite.</p>
     *
     * @return the inverse sine of this complex number.
     * @since 1.2
     */
    public Complex asin() {
        if (isNaN) {
            return NaN;
        }
        return sqrt1z().add(this.multiply(I)).log().multiply(I.negate());
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/InverseTangent.html" TARGET="_top">
     * inverse tangent</a> of this complex number.
     * Implements the formula:
     * <p>
     * {@code atan(z) = (i/2) log((i + z)/(i - z))}
     * </p><p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN} or infinite.</p>
     *
     * @return the inverse tangent of this complex number
     * @since 1.2
     */
    public Complex atan() {
        if (isNaN) {
            return NaN;
        }
        return this.add(I).divide(I.subtract(this)).log().multiply(I.divide(createComplex(2.0, 0.0)));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/Cosine.html" TARGET="_top">
     * cosine</a> of this complex number.
     * Implements the formula:
     * <p>
     *  {@code cos(a + bi) = cos(a)cosh(b) - sin(a)sinh(b)i}
     * </p><p>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos},
     * {@link FastMath#cosh} and {@link FastMath#sinh}.
     * </p><p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p><p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.</p>
     * <pre>
     *  Examples:
     *  <code>
     *   cos(1 &plusmn; INFINITY i) = 1 \u2213 INFINITY i
     *   cos(&plusmn;INFINITY + i) = NaN + NaN i
     *   cos(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *  </code>
     * </pre>
     *
     * @return the cosine of this complex number.
     * @since 1.2
     */
    public Complex cos() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.cos_726");
        if (isNaN) {
            return NaN;
        }
        return createComplex(AOR_multiply(FastMath.cos(real), FastMath.cosh(imaginary), "org.apache.commons.math3.complex.Complex.cos_726", _mut37305, _mut37306, _mut37307, _mut37308), AOR_multiply(-FastMath.sin(real), FastMath.sinh(imaginary), "org.apache.commons.math3.complex.Complex.cos_726", _mut37309, _mut37310, _mut37311, _mut37312));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/HyperbolicCosine.html" TARGET="_top">
     * hyperbolic cosine</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   cosh(a + bi) = cosh(a)cos(b) + sinh(a)sin(b)i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos},
     * {@link FastMath#cosh} and {@link FastMath#sinh}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   cosh(1 &plusmn; INFINITY i) = NaN + NaN i
     *   cosh(&plusmn;INFINITY + i) = INFINITY &plusmn; INFINITY i
     *   cosh(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *  </code>
     * </pre>
     *
     * @return the hyperbolic cosine of this complex number.
     * @since 1.2
     */
    public Complex cosh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.cosh_766");
        if (isNaN) {
            return NaN;
        }
        return createComplex(AOR_multiply(FastMath.cosh(real), FastMath.cos(imaginary), "org.apache.commons.math3.complex.Complex.cosh_766", _mut37313, _mut37314, _mut37315, _mut37316), AOR_multiply(FastMath.sinh(real), FastMath.sin(imaginary), "org.apache.commons.math3.complex.Complex.cosh_766", _mut37317, _mut37318, _mut37319, _mut37320));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/ExponentialFunction.html" TARGET="_top">
     * exponential function</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   exp(a + bi) = exp(a)cos(b) + exp(a)sin(b)i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#exp}, {@link FastMath#cos}, and
     * {@link FastMath#sin}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   exp(1 &plusmn; INFINITY i) = NaN + NaN i
     *   exp(INFINITY + i) = INFINITY + INFINITY i
     *   exp(-INFINITY + i) = 0 + 0i
     *   exp(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *  </code>
     * </pre>
     *
     * @return <code><i>e</i><sup>this</sup></code>.
     * @since 1.2
     */
    public Complex exp() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.exp_807");
        if (isNaN) {
            return NaN;
        }
        double expReal = FastMath.exp(real);
        return createComplex(AOR_multiply(expReal, FastMath.cos(imaginary), "org.apache.commons.math3.complex.Complex.exp_807", _mut37321, _mut37322, _mut37323, _mut37324), AOR_multiply(expReal, FastMath.sin(imaginary), "org.apache.commons.math3.complex.Complex.exp_807", _mut37325, _mut37326, _mut37327, _mut37328));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/NaturalLogarithm.html" TARGET="_top">
     * natural logarithm</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   log(a + bi) = ln(|a + bi|) + arg(a + bi)i
     *  </code>
     * </pre>
     * where ln on the right hand side is {@link FastMath#log},
     * {@code |a + bi|} is the modulus, {@link Complex#abs},  and
     * {@code arg(a + bi) = }{@link FastMath#atan2}(b, a).
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite (or critical) values in real or imaginary parts of the input may
     * result in infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   log(1 &plusmn; INFINITY i) = INFINITY &plusmn; (&pi;/2)i
     *   log(INFINITY + i) = INFINITY + 0i
     *   log(-INFINITY + i) = INFINITY + &pi;i
     *   log(INFINITY &plusmn; INFINITY i) = INFINITY &plusmn; (&pi;/4)i
     *   log(-INFINITY &plusmn; INFINITY i) = INFINITY &plusmn; (3&pi;/4)i
     *   log(0 + 0i) = -INFINITY + 0i
     *  </code>
     * </pre>
     *
     * @return the value <code>ln &nbsp; this</code>, the natural logarithm
     * of {@code this}.
     * @since 1.2
     */
    public Complex log() {
        if (isNaN) {
            return NaN;
        }
        return createComplex(FastMath.log(abs()), FastMath.atan2(imaginary, real));
    }

    /**
     * Returns of value of this complex number raised to the power of {@code x}.
     * Implements the formula:
     * <pre>
     *  <code>
     *   y<sup>x</sup> = exp(x&middot;log(y))
     *  </code>
     * </pre>
     * where {@code exp} and {@code log} are {@link #exp} and
     * {@link #log}, respectively.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN} or infinite, or if {@code y}
     * equals {@link Complex#ZERO}.</p>
     *
     * @param  x exponent to which this {@code Complex} is to be raised.
     * @return <code> this<sup>x</sup></code>.
     * @throws NullArgumentException if x is {@code null}.
     * @since 1.2
     */
    public Complex pow(Complex x) throws NullArgumentException {
        MathUtils.checkNotNull(x);
        return this.log().multiply(x).exp();
    }

    /**
     * Returns of value of this complex number raised to the power of {@code x}.
     *
     * @param  x exponent to which this {@code Complex} is to be raised.
     * @return <code>this<sup>x</sup></code>.
     * @see #pow(Complex)
     */
    public Complex pow(double x) {
        return this.log().multiply(x).exp();
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/Sine.html" TARGET="_top">
     * sine</a>
     * of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   sin(a + bi) = sin(a)cosh(b) - cos(a)sinh(b)i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos},
     * {@link FastMath#cosh} and {@link FastMath#sinh}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p><p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or {@code NaN} values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   sin(1 &plusmn; INFINITY i) = 1 &plusmn; INFINITY i
     *   sin(&plusmn;INFINITY + i) = NaN + NaN i
     *   sin(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *  </code>
     * </pre>
     *
     * @return the sine of this complex number.
     * @since 1.2
     */
    public Complex sin() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.sin_930");
        if (isNaN) {
            return NaN;
        }
        return createComplex(AOR_multiply(FastMath.sin(real), FastMath.cosh(imaginary), "org.apache.commons.math3.complex.Complex.sin_930", _mut37329, _mut37330, _mut37331, _mut37332), AOR_multiply(FastMath.cos(real), FastMath.sinh(imaginary), "org.apache.commons.math3.complex.Complex.sin_930", _mut37333, _mut37334, _mut37335, _mut37336));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/HyperbolicSine.html" TARGET="_top">
     * hyperbolic sine</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   sinh(a + bi) = sinh(a)cos(b)) + cosh(a)sin(b)i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos},
     * {@link FastMath#cosh} and {@link FastMath#sinh}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p><p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   sinh(1 &plusmn; INFINITY i) = NaN + NaN i
     *   sinh(&plusmn;INFINITY + i) = &plusmn; INFINITY + INFINITY i
     *   sinh(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *  </code>
     * </pre>
     *
     * @return the hyperbolic sine of {@code this}.
     * @since 1.2
     */
    public Complex sinh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.sinh_970");
        if (isNaN) {
            return NaN;
        }
        return createComplex(AOR_multiply(FastMath.sinh(real), FastMath.cos(imaginary), "org.apache.commons.math3.complex.Complex.sinh_970", _mut37337, _mut37338, _mut37339, _mut37340), AOR_multiply(FastMath.cosh(real), FastMath.sin(imaginary), "org.apache.commons.math3.complex.Complex.sinh_970", _mut37341, _mut37342, _mut37343, _mut37344));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/SquareRoot.html" TARGET="_top">
     * square root</a> of this complex number.
     * Implements the following algorithm to compute {@code sqrt(a + bi)}:
     * <ol><li>Let {@code t = sqrt((|a| + |a + bi|) / 2)}</li>
     * <li><pre>if {@code  a &#8805; 0} return {@code t + (b/2t)i}
     *  else return {@code |b|/2t + sign(b)t i }</pre></li>
     * </ol>
     * where <ul>
     * <li>{@code |a| = }{@link FastMath#abs}(a)</li>
     * <li>{@code |a + bi| = }{@link Complex#abs}(a + bi)</li>
     * <li>{@code sign(b) =  }{@link FastMath#copySign(double,double) copySign(1d, b)}
     * </ul>
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   sqrt(1 &plusmn; INFINITY i) = INFINITY + NaN i
     *   sqrt(INFINITY + i) = INFINITY + 0i
     *   sqrt(-INFINITY + i) = 0 + INFINITY i
     *   sqrt(INFINITY &plusmn; INFINITY i) = INFINITY + NaN i
     *   sqrt(-INFINITY &plusmn; INFINITY i) = NaN &plusmn; INFINITY i
     *  </code>
     * </pre>
     *
     * @return the square root of {@code this}.
     * @since 1.2
     */
    public Complex sqrt() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.sqrt_1013");
        if (isNaN) {
            return NaN;
        }
        if ((_mut37355 ? (ROR_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37345, _mut37346, _mut37347, _mut37348, _mut37349) || ROR_equals(imaginary, 0.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37350, _mut37351, _mut37352, _mut37353, _mut37354)) : (ROR_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37345, _mut37346, _mut37347, _mut37348, _mut37349) && ROR_equals(imaginary, 0.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37350, _mut37351, _mut37352, _mut37353, _mut37354)))) {
            return createComplex(0.0, 0.0);
        }
        double t = FastMath.sqrt(AOR_divide((AOR_plus(FastMath.abs(real), abs(), "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37356, _mut37357, _mut37358, _mut37359)), 2.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37360, _mut37361, _mut37362, _mut37363));
        if (ROR_greater_equals(real, 0.0, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37364, _mut37365, _mut37366, _mut37367, _mut37368)) {
            return createComplex(t, AOR_divide(imaginary, (AOR_multiply(2.0, t, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37381, _mut37382, _mut37383, _mut37384)), "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37385, _mut37386, _mut37387, _mut37388));
        } else {
            return createComplex(AOR_divide(FastMath.abs(imaginary), (AOR_multiply(2.0, t, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37369, _mut37370, _mut37371, _mut37372)), "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37373, _mut37374, _mut37375, _mut37376), AOR_multiply(FastMath.copySign(1d, imaginary), t, "org.apache.commons.math3.complex.Complex.sqrt_1013", _mut37377, _mut37378, _mut37379, _mut37380));
        }
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/SquareRoot.html" TARGET="_top">
     * square root</a> of <code>1 - this<sup>2</sup></code> for this complex
     * number.
     * Computes the result directly as
     * {@code sqrt(ONE.subtract(z.multiply(z)))}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     *
     * @return the square root of <code>1 - this<sup>2</sup></code>.
     * @since 1.2
     */
    public Complex sqrt1z() {
        return createComplex(1.0, 0.0).subtract(this.multiply(this)).sqrt();
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/Tangent.html" TARGET="_top">
     * tangent</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   tan(a + bi) = sin(2a)/(cos(2a)+cosh(2b)) + [sinh(2b)/(cos(2a)+cosh(2b))]i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos}, {@link FastMath#cosh} and
     * {@link FastMath#sinh}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite (or critical) values in real or imaginary parts of the input may
     * result in infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   tan(a &plusmn; INFINITY i) = 0 &plusmn; i
     *   tan(&plusmn;INFINITY + bi) = NaN + NaN i
     *   tan(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *   tan(&plusmn;&pi;/2 + 0 i) = &plusmn;INFINITY + NaN i
     *  </code>
     * </pre>
     *
     * @return the tangent of {@code this}.
     * @since 1.2
     */
    public Complex tan() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.tan_1084");
        if ((_mut37389 ? (isNaN && Double.isInfinite(real)) : (isNaN || Double.isInfinite(real)))) {
            return NaN;
        }
        if (ROR_greater(imaginary, 20.0, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37390, _mut37391, _mut37392, _mut37393, _mut37394)) {
            return createComplex(0.0, 1.0);
        }
        if (ROR_less(imaginary, -20.0, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37395, _mut37396, _mut37397, _mut37398, _mut37399)) {
            return createComplex(0.0, -1.0);
        }
        double real2 = AOR_multiply(2.0, real, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37400, _mut37401, _mut37402, _mut37403);
        double imaginary2 = AOR_multiply(2.0, imaginary, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37404, _mut37405, _mut37406, _mut37407);
        double d = AOR_plus(FastMath.cos(real2), FastMath.cosh(imaginary2), "org.apache.commons.math3.complex.Complex.tan_1084", _mut37408, _mut37409, _mut37410, _mut37411);
        return createComplex(AOR_divide(FastMath.sin(real2), d, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37412, _mut37413, _mut37414, _mut37415), AOR_divide(FastMath.sinh(imaginary2), d, "org.apache.commons.math3.complex.Complex.tan_1084", _mut37416, _mut37417, _mut37418, _mut37419));
    }

    /**
     * Compute the
     * <a href="http://mathworld.wolfram.com/HyperbolicTangent.html" TARGET="_top">
     * hyperbolic tangent</a> of this complex number.
     * Implements the formula:
     * <pre>
     *  <code>
     *   tan(a + bi) = sinh(2a)/(cosh(2a)+cos(2b)) + [sin(2b)/(cosh(2a)+cos(2b))]i
     *  </code>
     * </pre>
     * where the (real) functions on the right-hand side are
     * {@link FastMath#sin}, {@link FastMath#cos}, {@link FastMath#cosh} and
     * {@link FastMath#sinh}.
     * <p>
     * Returns {@link Complex#NaN} if either real or imaginary part of the
     * input argument is {@code NaN}.
     * </p>
     * Infinite values in real or imaginary parts of the input may result in
     * infinite or NaN values returned in parts of the result.
     * <pre>
     *  Examples:
     *  <code>
     *   tanh(a &plusmn; INFINITY i) = NaN + NaN i
     *   tanh(&plusmn;INFINITY + bi) = &plusmn;1 + 0 i
     *   tanh(&plusmn;INFINITY &plusmn; INFINITY i) = NaN + NaN i
     *   tanh(0 + (&pi;/2)i) = NaN + INFINITY i
     *  </code>
     * </pre>
     *
     * @return the hyperbolic tangent of {@code this}.
     * @since 1.2
     */
    public Complex tanh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.tanh_1135");
        if ((_mut37420 ? (isNaN && Double.isInfinite(imaginary)) : (isNaN || Double.isInfinite(imaginary)))) {
            return NaN;
        }
        if (ROR_greater(real, 20.0, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37421, _mut37422, _mut37423, _mut37424, _mut37425)) {
            return createComplex(1.0, 0.0);
        }
        if (ROR_less(real, -20.0, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37426, _mut37427, _mut37428, _mut37429, _mut37430)) {
            return createComplex(-1.0, 0.0);
        }
        double real2 = AOR_multiply(2.0, real, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37431, _mut37432, _mut37433, _mut37434);
        double imaginary2 = AOR_multiply(2.0, imaginary, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37435, _mut37436, _mut37437, _mut37438);
        double d = AOR_plus(FastMath.cosh(real2), FastMath.cos(imaginary2), "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37439, _mut37440, _mut37441, _mut37442);
        return createComplex(AOR_divide(FastMath.sinh(real2), d, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37443, _mut37444, _mut37445, _mut37446), AOR_divide(FastMath.sin(imaginary2), d, "org.apache.commons.math3.complex.Complex.tanh_1135", _mut37447, _mut37448, _mut37449, _mut37450));
    }

    /**
     * Compute the argument of this complex number.
     * The argument is the angle phi between the positive real axis and
     * the point representing this number in the complex plane.
     * The value returned is between -PI (not inclusive)
     * and PI (inclusive), with negative values returned for numbers with
     * negative imaginary parts.
     * <p>
     * If either real or imaginary part (or both) is NaN, NaN is returned.
     * Infinite parts are handled as {@code Math.atan2} handles them,
     * essentially treating finite parts as zero in the presence of an
     * infinite coordinate and returning a multiple of pi/4 depending on
     * the signs of the infinite parts.
     * See the javadoc for {@code Math.atan2} for full details.
     *
     * @return the argument of {@code this}.
     */
    public double getArgument() {
        return FastMath.atan2(getImaginary(), getReal());
    }

    /**
     * Computes the n-th roots of this complex number.
     * The nth roots are defined by the formula:
     * <pre>
     *  <code>
     *   z<sub>k</sub> = abs<sup>1/n</sup> (cos(phi + 2&pi;k/n) + i (sin(phi + 2&pi;k/n))
     *  </code>
     * </pre>
     * for <i>{@code k=0, 1, ..., n-1}</i>, where {@code abs} and {@code phi}
     * are respectively the {@link #abs() modulus} and
     * {@link #getArgument() argument} of this complex number.
     * <p>
     * If one or both parts of this complex number is NaN, a list with just
     * one element, {@link #NaN} is returned.
     * if neither part is NaN, but at least one part is infinite, the result
     * is a one-element list containing {@link #INF}.
     *
     * @param n Degree of root.
     * @return a List of all {@code n}-th roots of {@code this}.
     * @throws NotPositiveException if {@code n <= 0}.
     * @since 2.0
     */
    public List<Complex> nthRoot(int n) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.nthRoot_1198");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37451, _mut37452, _mut37453, _mut37454, _mut37455)) {
            throw new NotPositiveException(LocalizedFormats.CANNOT_COMPUTE_NTH_ROOT_FOR_NEGATIVE_N, n);
        }
        final List<Complex> result = new ArrayList<Complex>();
        if (isNaN) {
            result.add(NaN);
            return result;
        }
        if (isInfinite()) {
            result.add(INF);
            return result;
        }
        // nth root of abs -- faster / more accurate to use a solver here?
        final double nthRootOfAbs = FastMath.pow(abs(), AOR_divide(1.0, n, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37456, _mut37457, _mut37458, _mut37459));
        // Compute nth roots of complex number with k = 0, 1, ... n-1
        final double nthPhi = AOR_divide(getArgument(), n, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37460, _mut37461, _mut37462, _mut37463);
        final double slice = AOR_divide(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37464, _mut37465, _mut37466, _mut37467), n, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37468, _mut37469, _mut37470, _mut37471);
        double innerPart = nthPhi;
        for (int k = 0; ROR_less(k, n, "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37480, _mut37481, _mut37482, _mut37483, _mut37484); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.nthRoot_1198");
            // inner part
            final double realPart = AOR_multiply(nthRootOfAbs, FastMath.cos(innerPart), "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37472, _mut37473, _mut37474, _mut37475);
            final double imaginaryPart = AOR_multiply(nthRootOfAbs, FastMath.sin(innerPart), "org.apache.commons.math3.complex.Complex.nthRoot_1198", _mut37476, _mut37477, _mut37478, _mut37479);
            result.add(createComplex(realPart, imaginaryPart));
            innerPart += slice;
        }
        return result;
    }

    /**
     * Create a complex number given the real and imaginary parts.
     *
     * @param realPart Real part.
     * @param imaginaryPart Imaginary part.
     * @return a new complex number instance.
     * @since 1.2
     * @see #valueOf(double, double)
     */
    protected Complex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Create a complex number given the real and imaginary parts.
     *
     * @param realPart Real part.
     * @param imaginaryPart Imaginary part.
     * @return a Complex instance.
     */
    public static Complex valueOf(double realPart, double imaginaryPart) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Complex.valueOf_1255");
        if ((_mut37485 ? (Double.isNaN(realPart) && Double.isNaN(imaginaryPart)) : (Double.isNaN(realPart) || Double.isNaN(imaginaryPart)))) {
            return NaN;
        }
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Create a complex number given only the real part.
     *
     * @param realPart Real part.
     * @return a Complex instance.
     */
    public static Complex valueOf(double realPart) {
        if (Double.isNaN(realPart)) {
            return NaN;
        }
        return new Complex(realPart);
    }

    /**
     * Resolve the transient fields in a deserialized Complex Object.
     * Subclasses will need to override {@link #createComplex} to
     * deserialize properly.
     *
     * @return A Complex instance with all fields resolved.
     * @since 2.0
     */
    protected final Object readResolve() {
        return createComplex(real, imaginary);
    }

    /**
     * {@inheritDoc}
     */
    public ComplexField getField() {
        return ComplexField.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + real + ", " + imaginary + ")";
    }
}
