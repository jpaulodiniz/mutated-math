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
package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the {@link RealVector} interface with a double array.
 * @since 2.0
 */
public class ArrayRealVector extends RealVector implements Serializable {

    @Conditional
    public static boolean _mut29080 = false, _mut29081 = false, _mut29082 = false, _mut29083 = false, _mut29084 = false, _mut29085 = false, _mut29086 = false, _mut29087 = false, _mut29088 = false, _mut29089 = false, _mut29090 = false, _mut29091 = false, _mut29092 = false, _mut29093 = false, _mut29094 = false, _mut29095 = false, _mut29096 = false, _mut29097 = false, _mut29098 = false, _mut29099 = false, _mut29100 = false, _mut29101 = false, _mut29102 = false, _mut29103 = false, _mut29104 = false, _mut29105 = false, _mut29106 = false, _mut29107 = false, _mut29108 = false, _mut29109 = false, _mut29110 = false, _mut29111 = false, _mut29112 = false, _mut29113 = false, _mut29114 = false, _mut29115 = false, _mut29116 = false, _mut29117 = false, _mut29118 = false, _mut29119 = false, _mut29120 = false, _mut29121 = false, _mut29122 = false, _mut29123 = false, _mut29124 = false, _mut29125 = false, _mut29126 = false, _mut29127 = false, _mut29128 = false, _mut29129 = false, _mut29130 = false, _mut29131 = false, _mut29132 = false, _mut29133 = false, _mut29134 = false, _mut29135 = false, _mut29136 = false, _mut29137 = false, _mut29138 = false, _mut29139 = false, _mut29140 = false, _mut29141 = false, _mut29142 = false, _mut29143 = false, _mut29144 = false, _mut29145 = false, _mut29146 = false, _mut29147 = false, _mut29148 = false, _mut29149 = false, _mut29150 = false, _mut29151 = false, _mut29152 = false, _mut29153 = false, _mut29154 = false, _mut29155 = false, _mut29156 = false, _mut29157 = false, _mut29158 = false, _mut29159 = false, _mut29160 = false, _mut29161 = false, _mut29162 = false, _mut29163 = false, _mut29164 = false, _mut29165 = false, _mut29166 = false, _mut29167 = false, _mut29168 = false, _mut29169 = false, _mut29170 = false, _mut29171 = false, _mut29172 = false, _mut29173 = false, _mut29174 = false, _mut29175 = false, _mut29176 = false, _mut29177 = false, _mut29178 = false, _mut29179 = false, _mut29180 = false, _mut29181 = false, _mut29182 = false, _mut29183 = false, _mut29184 = false, _mut29185 = false, _mut29186 = false, _mut29187 = false, _mut29188 = false, _mut29189 = false, _mut29190 = false, _mut29191 = false, _mut29192 = false, _mut29193 = false, _mut29194 = false, _mut29195 = false, _mut29196 = false, _mut29197 = false, _mut29198 = false, _mut29199 = false, _mut29200 = false, _mut29201 = false, _mut29202 = false, _mut29203 = false, _mut29204 = false, _mut29205 = false, _mut29206 = false, _mut29207 = false, _mut29208 = false, _mut29209 = false, _mut29210 = false, _mut29211 = false, _mut29212 = false, _mut29213 = false, _mut29214 = false, _mut29215 = false, _mut29216 = false, _mut29217 = false, _mut29218 = false, _mut29219 = false, _mut29220 = false, _mut29221 = false, _mut29222 = false, _mut29223 = false, _mut29224 = false, _mut29225 = false, _mut29226 = false, _mut29227 = false, _mut29228 = false, _mut29229 = false, _mut29230 = false, _mut29231 = false, _mut29232 = false, _mut29233 = false, _mut29234 = false, _mut29235 = false, _mut29236 = false, _mut29237 = false, _mut29238 = false, _mut29239 = false, _mut29240 = false, _mut29241 = false, _mut29242 = false, _mut29243 = false, _mut29244 = false, _mut29245 = false, _mut29246 = false, _mut29247 = false, _mut29248 = false, _mut29249 = false, _mut29250 = false, _mut29251 = false, _mut29252 = false, _mut29253 = false, _mut29254 = false, _mut29255 = false, _mut29256 = false, _mut29257 = false, _mut29258 = false, _mut29259 = false, _mut29260 = false, _mut29261 = false, _mut29262 = false, _mut29263 = false, _mut29264 = false, _mut29265 = false, _mut29266 = false, _mut29267 = false, _mut29268 = false, _mut29269 = false, _mut29270 = false, _mut29271 = false, _mut29272 = false, _mut29273 = false, _mut29274 = false, _mut29275 = false, _mut29276 = false, _mut29277 = false, _mut29278 = false, _mut29279 = false, _mut29280 = false, _mut29281 = false, _mut29282 = false, _mut29283 = false, _mut29284 = false, _mut29285 = false, _mut29286 = false, _mut29287 = false, _mut29288 = false, _mut29289 = false, _mut29290 = false, _mut29291 = false, _mut29292 = false, _mut29293 = false, _mut29294 = false, _mut29295 = false, _mut29296 = false, _mut29297 = false, _mut29298 = false, _mut29299 = false, _mut29300 = false, _mut29301 = false, _mut29302 = false, _mut29303 = false, _mut29304 = false, _mut29305 = false, _mut29306 = false, _mut29307 = false, _mut29308 = false, _mut29309 = false, _mut29310 = false, _mut29311 = false, _mut29312 = false, _mut29313 = false, _mut29314 = false, _mut29315 = false, _mut29316 = false, _mut29317 = false, _mut29318 = false, _mut29319 = false, _mut29320 = false, _mut29321 = false, _mut29322 = false, _mut29323 = false, _mut29324 = false, _mut29325 = false, _mut29326 = false, _mut29327 = false, _mut29328 = false, _mut29329 = false, _mut29330 = false, _mut29331 = false, _mut29332 = false, _mut29333 = false, _mut29334 = false, _mut29335 = false, _mut29336 = false, _mut29337 = false, _mut29338 = false, _mut29339 = false, _mut29340 = false, _mut29341 = false, _mut29342 = false, _mut29343 = false, _mut29344 = false, _mut29345 = false, _mut29346 = false, _mut29347 = false, _mut29348 = false, _mut29349 = false, _mut29350 = false, _mut29351 = false, _mut29352 = false, _mut29353 = false, _mut29354 = false, _mut29355 = false, _mut29356 = false, _mut29357 = false, _mut29358 = false, _mut29359 = false, _mut29360 = false, _mut29361 = false, _mut29362 = false, _mut29363 = false, _mut29364 = false, _mut29365 = false, _mut29366 = false, _mut29367 = false, _mut29368 = false, _mut29369 = false, _mut29370 = false, _mut29371 = false, _mut29372 = false, _mut29373 = false, _mut29374 = false, _mut29375 = false, _mut29376 = false, _mut29377 = false, _mut29378 = false, _mut29379 = false, _mut29380 = false, _mut29381 = false, _mut29382 = false, _mut29383 = false, _mut29384 = false, _mut29385 = false, _mut29386 = false, _mut29387 = false, _mut29388 = false, _mut29389 = false, _mut29390 = false, _mut29391 = false, _mut29392 = false, _mut29393 = false, _mut29394 = false, _mut29395 = false, _mut29396 = false, _mut29397 = false, _mut29398 = false, _mut29399 = false, _mut29400 = false, _mut29401 = false, _mut29402 = false, _mut29403 = false, _mut29404 = false, _mut29405 = false, _mut29406 = false, _mut29407 = false, _mut29408 = false, _mut29409 = false, _mut29410 = false, _mut29411 = false, _mut29412 = false, _mut29413 = false, _mut29414 = false, _mut29415 = false, _mut29416 = false, _mut29417 = false, _mut29418 = false, _mut29419 = false, _mut29420 = false, _mut29421 = false, _mut29422 = false, _mut29423 = false, _mut29424 = false, _mut29425 = false, _mut29426 = false, _mut29427 = false, _mut29428 = false, _mut29429 = false, _mut29430 = false, _mut29431 = false, _mut29432 = false, _mut29433 = false, _mut29434 = false, _mut29435 = false, _mut29436 = false, _mut29437 = false, _mut29438 = false, _mut29439 = false, _mut29440 = false, _mut29441 = false, _mut29442 = false, _mut29443 = false, _mut29444 = false, _mut29445 = false, _mut29446 = false, _mut29447 = false, _mut29448 = false, _mut29449 = false, _mut29450 = false, _mut29451 = false, _mut29452 = false, _mut29453 = false, _mut29454 = false, _mut29455 = false, _mut29456 = false, _mut29457 = false, _mut29458 = false, _mut29459 = false, _mut29460 = false, _mut29461 = false, _mut29462 = false, _mut29463 = false, _mut29464 = false, _mut29465 = false, _mut29466 = false, _mut29467 = false, _mut29468 = false, _mut29469 = false, _mut29470 = false, _mut29471 = false, _mut29472 = false, _mut29473 = false, _mut29474 = false, _mut29475 = false, _mut29476 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -1097961340710804027L;

    /**
     * Default format.
     */
    private static final RealVectorFormat DEFAULT_FORMAT = RealVectorFormat.getInstance();

    /**
     * Entries of the vector.
     */
    private double[] data;

    /**
     * Build a 0-length vector.
     * Zero-length vectors may be used to initialized construction of vectors
     * by data gathering. We start with zero-length and use either the {@link
     * #ArrayRealVector(ArrayRealVector, ArrayRealVector)} constructor
     * or one of the {@code append} method ({@link #append(double)},
     * {@link #append(ArrayRealVector)}) to gather data into this vector.
     */
    public ArrayRealVector() {
        data = new double[0];
    }

    /**
     * Construct a vector of zeroes.
     *
     * @param size Size of the vector.
     */
    public ArrayRealVector(int size) {
        data = new double[size];
    }

    /**
     * Construct a vector with preset values.
     *
     * @param size Size of the vector
     * @param preset All entries will be set with this value.
     */
    public ArrayRealVector(int size, double preset) {
        data = new double[size];
        Arrays.fill(data, preset);
    }

    /**
     * Construct a vector from an array, copying the input array.
     *
     * @param d Array.
     */
    public ArrayRealVector(double[] d) {
        data = d.clone();
    }

    /**
     * Create a new ArrayRealVector using the input array as the underlying
     * data array.
     * If an array is built specially in order to be embedded in a
     * ArrayRealVector and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.
     *
     * @param d Data for the new vector.
     * @param copyArray if {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #ArrayRealVector(double[])
     */
    public ArrayRealVector(double[] d, boolean copyArray) throws NullArgumentException {
        if (d == null) {
            throw new NullArgumentException();
        }
        data = copyArray ? d.clone() : d;
    }

    /**
     * Construct a vector from part of a array.
     *
     * @param d Array.
     * @param pos Position of first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayRealVector(double[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_120");
        if (d == null) {
            throw new NullArgumentException();
        }
        if (ROR_less(d.length, AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_120", _mut29080, _mut29081, _mut29082, _mut29083), "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_120", _mut29084, _mut29085, _mut29086, _mut29087, _mut29088)) {
            throw new NumberIsTooLargeException(AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_120", _mut29089, _mut29090, _mut29091, _mut29092), d.length, true);
        }
        data = new double[size];
        System.arraycopy(d, pos, data, 0, size);
    }

    /**
     * Construct a vector from an array.
     *
     * @param d Array of {@code Double}s.
     */
    public ArrayRealVector(Double[] d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_137");
        data = new double[d.length];
        for (int i = 0; ROR_less(i, d.length, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_137", _mut29093, _mut29094, _mut29095, _mut29096, _mut29097); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_137");
            data[i] = d[i].doubleValue();
        }
    }

    /**
     * Construct a vector from part of an array.
     *
     * @param d Array.
     * @param pos Position of first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayRealVector(Double[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154");
        if (d == null) {
            throw new NullArgumentException();
        }
        if (ROR_less(d.length, AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29098, _mut29099, _mut29100, _mut29101), "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29102, _mut29103, _mut29104, _mut29105, _mut29106)) {
            throw new NumberIsTooLargeException(AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29107, _mut29108, _mut29109, _mut29110), d.length, true);
        }
        data = new double[size];
        for (int i = pos; ROR_less(i, AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29115, _mut29116, _mut29117, _mut29118), "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29119, _mut29120, _mut29121, _mut29122, _mut29123); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154");
            data[AOR_minus(i, pos, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_154", _mut29111, _mut29112, _mut29113, _mut29114)] = d[i].doubleValue();
        }
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayRealVector(RealVector v) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_174");
        if (v == null) {
            throw new NullArgumentException();
        }
        data = new double[v.getDimension()];
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_174", _mut29124, _mut29125, _mut29126, _mut29127, _mut29128); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_174");
            data[i] = v.getEntry(i);
        }
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v Vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayRealVector(ArrayRealVector v) throws NullArgumentException {
        this(v, true);
    }

    /**
     * Construct a vector from another vector.
     *
     * @param v Vector to copy.
     * @param deep If {@code true} perform a deep copy, otherwise perform a
     * shallow copy.
     */
    public ArrayRealVector(ArrayRealVector v, boolean deep) {
        data = deep ? v.data.clone() : v.data;
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, ArrayRealVector v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_210");
        data = new double[AOR_plus(v1.data.length, v2.data.length, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_210", _mut29129, _mut29130, _mut29131, _mut29132)];
        System.arraycopy(v1.data, 0, data, 0, v1.data.length);
        System.arraycopy(v2.data, 0, data, v1.data.length, v2.data.length);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, RealVector v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_221");
        final int l1 = v1.data.length;
        final int l2 = v2.getDimension();
        data = new double[AOR_plus(l1, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_221", _mut29133, _mut29134, _mut29135, _mut29136)];
        System.arraycopy(v1.data, 0, data, 0, l1);
        for (int i = 0; ROR_less(i, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_221", _mut29141, _mut29142, _mut29143, _mut29144, _mut29145); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_221");
            data[AOR_plus(l1, i, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_221", _mut29137, _mut29138, _mut29139, _mut29140)] = v2.getEntry(i);
        }
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(RealVector v1, ArrayRealVector v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_236");
        final int l1 = v1.getDimension();
        final int l2 = v2.data.length;
        data = new double[AOR_plus(l1, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_236", _mut29146, _mut29147, _mut29148, _mut29149)];
        for (int i = 0; ROR_less(i, l1, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_236", _mut29150, _mut29151, _mut29152, _mut29153, _mut29154); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_236");
            data[i] = v1.getEntry(i);
        }
        System.arraycopy(v2.data, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(ArrayRealVector v1, double[] v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_251");
        final int l1 = v1.getDimension();
        final int l2 = v2.length;
        data = new double[AOR_plus(l1, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_251", _mut29155, _mut29156, _mut29157, _mut29158)];
        System.arraycopy(v1.data, 0, data, 0, l1);
        System.arraycopy(v2, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     */
    public ArrayRealVector(double[] v1, ArrayRealVector v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_264");
        final int l1 = v1.length;
        final int l2 = v2.getDimension();
        data = new double[AOR_plus(l1, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_264", _mut29159, _mut29160, _mut29161, _mut29162)];
        System.arraycopy(v1, 0, data, 0, l1);
        System.arraycopy(v2.data, 0, data, l1, l2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * @param v1 first vector (will be put in front of the new vector)
     * @param v2 second vector (will be put at back of the new vector)
     */
    public ArrayRealVector(double[] v1, double[] v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_277");
        final int l1 = v1.length;
        final int l2 = v2.length;
        data = new double[AOR_plus(l1, l2, "org.apache.commons.math3.linear.ArrayRealVector.ArrayRealVector_277", _mut29163, _mut29164, _mut29165, _mut29166)];
        System.arraycopy(v1, 0, data, 0, l1);
        System.arraycopy(v2, 0, data, l1, l2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector copy() {
        return new ArrayRealVector(this, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector add(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.add_292");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            final int dim = vData.length;
            checkVectorDimensions(dim);
            ArrayRealVector result = new ArrayRealVector(dim);
            double[] resultData = result.data;
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayRealVector.add_292", _mut29171, _mut29172, _mut29173, _mut29174, _mut29175); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.add_292");
                resultData[i] = AOR_plus(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.add_292", _mut29167, _mut29168, _mut29169, _mut29170);
            }
            return result;
        } else {
            checkVectorDimensions(v);
            double[] out = data.clone();
            Iterator<Entry> it = v.iterator();
            while (it.hasNext()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.add_292");
                final Entry e = it.next();
                out[e.getIndex()] += e.getValue();
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector subtract(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.subtract_318");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            final int dim = vData.length;
            checkVectorDimensions(dim);
            ArrayRealVector result = new ArrayRealVector(dim);
            double[] resultData = result.data;
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayRealVector.subtract_318", _mut29180, _mut29181, _mut29182, _mut29183, _mut29184); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.subtract_318");
                resultData[i] = AOR_minus(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.subtract_318", _mut29176, _mut29177, _mut29178, _mut29179);
            }
            return result;
        } else {
            checkVectorDimensions(v);
            double[] out = data.clone();
            Iterator<Entry> it = v.iterator();
            while (it.hasNext()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.subtract_318");
                final Entry e = it.next();
                out[e.getIndex()] -= e.getValue();
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector map(UnivariateFunction function) {
        return copy().mapToSelf(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector mapToSelf(UnivariateFunction function) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapToSelf_350");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.mapToSelf_350", _mut29185, _mut29186, _mut29187, _mut29188, _mut29189); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapToSelf_350");
            data[i] = function.value(data[i]);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapAddToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapAddToSelf_359");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.mapAddToSelf_359", _mut29190, _mut29191, _mut29192, _mut29193, _mut29194); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapAddToSelf_359");
            data[i] += d;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapSubtractToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapSubtractToSelf_368");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.mapSubtractToSelf_368", _mut29195, _mut29196, _mut29197, _mut29198, _mut29199); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapSubtractToSelf_368");
            data[i] -= d;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapMultiplyToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapMultiplyToSelf_377");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.mapMultiplyToSelf_377", _mut29200, _mut29201, _mut29202, _mut29203, _mut29204); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapMultiplyToSelf_377");
            data[i] *= d;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector mapDivideToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapDivideToSelf_386");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.mapDivideToSelf_386", _mut29205, _mut29206, _mut29207, _mut29208, _mut29209); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.mapDivideToSelf_386");
            data[i] /= d;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector ebeMultiply(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            final int dim = vData.length;
            checkVectorDimensions(dim);
            ArrayRealVector result = new ArrayRealVector(dim);
            double[] resultData = result.data;
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395", _mut29219, _mut29220, _mut29221, _mut29222, _mut29223); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395");
                resultData[i] = AOR_multiply(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395", _mut29215, _mut29216, _mut29217, _mut29218);
            }
            return result;
        } else {
            checkVectorDimensions(v);
            double[] out = data.clone();
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395", _mut29210, _mut29211, _mut29212, _mut29213, _mut29214); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeMultiply_395");
                out[i] *= v.getEntry(i);
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector ebeDivide(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            final int dim = vData.length;
            checkVectorDimensions(dim);
            ArrayRealVector result = new ArrayRealVector(dim);
            double[] resultData = result.data;
            for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419", _mut29233, _mut29234, _mut29235, _mut29236, _mut29237); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419");
                resultData[i] = AOR_divide(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419", _mut29229, _mut29230, _mut29231, _mut29232);
            }
            return result;
        } else {
            checkVectorDimensions(v);
            double[] out = data.clone();
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419", _mut29224, _mut29225, _mut29226, _mut29227, _mut29228); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.ebeDivide_419");
                out[i] /= v.getEntry(i);
            }
            return new ArrayRealVector(out, false);
        }
    }

    /**
     * Get a reference to the underlying data array.
     * This method does not make a fresh copy of the underlying data.
     *
     * @return the array of entries.
     */
    public double[] getDataRef() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dotProduct(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.dotProduct_453");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            checkVectorDimensions(vData.length);
            double dot = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.dotProduct_453", _mut29242, _mut29243, _mut29244, _mut29245, _mut29246); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.dotProduct_453");
                dot += AOR_multiply(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.dotProduct_453", _mut29238, _mut29239, _mut29240, _mut29241);
            }
            return dot;
        }
        return super.dotProduct(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getNorm_468");
        double sum = 0;
        for (double a : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getNorm_468");
            sum += AOR_multiply(a, a, "org.apache.commons.math3.linear.ArrayRealVector.getNorm_468", _mut29247, _mut29248, _mut29249, _mut29250);
        }
        return FastMath.sqrt(sum);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getL1Norm() {
        double sum = 0;
        for (double a : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getL1Norm_478");
            sum += FastMath.abs(a);
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLInfNorm() {
        double max = 0;
        for (double a : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getLInfNorm_488");
            max = FastMath.max(max, FastMath.abs(a));
        }
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDistance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getDistance_498");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            checkVectorDimensions(vData.length);
            double sum = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29272, _mut29273, _mut29274, _mut29275, _mut29276); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getDistance_498");
                final double delta = AOR_minus(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29264, _mut29265, _mut29266, _mut29267);
                sum += AOR_multiply(delta, delta, "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29268, _mut29269, _mut29270, _mut29271);
            }
            return FastMath.sqrt(sum);
        } else {
            checkVectorDimensions(v);
            double sum = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29259, _mut29260, _mut29261, _mut29262, _mut29263); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getDistance_498");
                final double delta = AOR_minus(data[i], v.getEntry(i), "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29251, _mut29252, _mut29253, _mut29254);
                sum += AOR_multiply(delta, delta, "org.apache.commons.math3.linear.ArrayRealVector.getDistance_498", _mut29255, _mut29256, _mut29257, _mut29258);
            }
            return FastMath.sqrt(sum);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            checkVectorDimensions(vData.length);
            double sum = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521", _mut29290, _mut29291, _mut29292, _mut29293, _mut29294); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521");
                final double delta = AOR_minus(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521", _mut29286, _mut29287, _mut29288, _mut29289);
                sum += FastMath.abs(delta);
            }
            return sum;
        } else {
            checkVectorDimensions(v);
            double sum = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521", _mut29281, _mut29282, _mut29283, _mut29284, _mut29285); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521");
                final double delta = AOR_minus(data[i], v.getEntry(i), "org.apache.commons.math3.linear.ArrayRealVector.getL1Distance_521", _mut29277, _mut29278, _mut29279, _mut29280);
                sum += FastMath.abs(delta);
            }
            return sum;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            checkVectorDimensions(vData.length);
            double max = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545", _mut29308, _mut29309, _mut29310, _mut29311, _mut29312); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545");
                final double delta = AOR_minus(data[i], vData[i], "org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545", _mut29304, _mut29305, _mut29306, _mut29307);
                max = FastMath.max(max, FastMath.abs(delta));
            }
            return max;
        } else {
            checkVectorDimensions(v);
            double max = 0;
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545", _mut29299, _mut29300, _mut29301, _mut29302, _mut29303); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545");
                final double delta = AOR_minus(data[i], v.getEntry(i), "org.apache.commons.math3.linear.ArrayRealVector.getLInfDistance_545", _mut29295, _mut29296, _mut29297, _mut29298);
                max = FastMath.max(max, FastMath.abs(delta));
            }
            return max;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix outerProduct(RealVector v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569");
        if (v instanceof ArrayRealVector) {
            final double[] vData = ((ArrayRealVector) v).data;
            final int m = data.length;
            final int n = vData.length;
            final RealMatrix out = MatrixUtils.createRealMatrix(m, n);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29336, _mut29337, _mut29338, _mut29339, _mut29340); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569");
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29331, _mut29332, _mut29333, _mut29334, _mut29335); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569");
                    out.setEntry(i, j, AOR_multiply(data[i], vData[j], "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29327, _mut29328, _mut29329, _mut29330));
                }
            }
            return out;
        } else {
            final int m = data.length;
            final int n = v.getDimension();
            final RealMatrix out = MatrixUtils.createRealMatrix(m, n);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29322, _mut29323, _mut29324, _mut29325, _mut29326); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569");
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29317, _mut29318, _mut29319, _mut29320, _mut29321); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569");
                    out.setEntry(i, j, AOR_multiply(data[i], v.getEntry(j), "org.apache.commons.math3.linear.ArrayRealVector.outerProduct_569", _mut29313, _mut29314, _mut29315, _mut29316));
                }
            }
            return out;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getEntry_596");
        try {
            return data[index];
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, AOR_minus(getDimension(), 1, "org.apache.commons.math3.linear.ArrayRealVector.getEntry_596", _mut29341, _mut29342, _mut29343, _mut29344));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return data.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector append(RealVector v) {
        try {
            return new ArrayRealVector(this, (ArrayRealVector) v);
        } catch (ClassCastException cce) {
            return new ArrayRealVector(this, v);
        }
    }

    /**
     * Construct a vector by appending a vector to this vector.
     *
     * @param v Vector to append to this one.
     * @return a new vector.
     */
    public ArrayRealVector append(ArrayRealVector v) {
        return new ArrayRealVector(this, v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector append(double in) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.append_633");
        final double[] out = new double[AOR_plus(data.length, 1, "org.apache.commons.math3.linear.ArrayRealVector.append_633", _mut29345, _mut29346, _mut29347, _mut29348)];
        System.arraycopy(data, 0, out, 0, data.length);
        out[data.length] = in;
        return new ArrayRealVector(out, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.getSubVector_642");
        if (ROR_less(n, 0, "org.apache.commons.math3.linear.ArrayRealVector.getSubVector_642", _mut29349, _mut29350, _mut29351, _mut29352, _mut29353)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        ArrayRealVector out = new ArrayRealVector(n);
        try {
            System.arraycopy(data, index, out.data, 0, n);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(AOR_minus(AOR_plus(index, n, "org.apache.commons.math3.linear.ArrayRealVector.getSubVector_642", _mut29354, _mut29355, _mut29356, _mut29357), 1, "org.apache.commons.math3.linear.ArrayRealVector.getSubVector_642", _mut29358, _mut29359, _mut29360, _mut29361));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(int index, double value) throws OutOfRangeException {
        try {
            data[index] = value;
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(int index, double increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.addToEntry_669");
        try {
            data[index] += increment;
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, AOR_minus(data.length, 1, "org.apache.commons.math3.linear.ArrayRealVector.addToEntry_669", _mut29362, _mut29363, _mut29364, _mut29365));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubVector(int index, RealVector v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681");
        if (v instanceof ArrayRealVector) {
            setSubVector(index, ((ArrayRealVector) v).data);
        } else {
            try {
                for (int i = index; ROR_less(i, AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681", _mut29378, _mut29379, _mut29380, _mut29381), "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681", _mut29382, _mut29383, _mut29384, _mut29385, _mut29386); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681");
                    data[i] = v.getEntry(AOR_minus(i, index, "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681", _mut29374, _mut29375, _mut29376, _mut29377));
                }
            } catch (IndexOutOfBoundsException e) {
                checkIndex(index);
                checkIndex(AOR_minus(AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681", _mut29366, _mut29367, _mut29368, _mut29369), 1, "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_681", _mut29370, _mut29371, _mut29372, _mut29373));
            }
        }
    }

    /**
     * Set a set of consecutive elements.
     *
     * @param index Index of first element to be set.
     * @param v Vector containing the values to set.
     * @throws OutOfRangeException if the index is inconsistent with the vector
     * size.
     */
    public void setSubVector(int index, double[] v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.setSubVector_706");
        try {
            System.arraycopy(v, 0, data, index, v.length);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(AOR_minus(AOR_plus(index, v.length, "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_706", _mut29387, _mut29388, _mut29389, _mut29390), 1, "org.apache.commons.math3.linear.ArrayRealVector.setSubVector_706", _mut29391, _mut29392, _mut29393, _mut29394));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(double value) {
        Arrays.fill(data, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return data.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return DEFAULT_FORMAT.format(this);
    }

    /**
     * Check if instance and specified vectors have the same dimension.
     *
     * @param v Vector to compare instance with.
     * @throws DimensionMismatchException if the vectors do not
     * have the same dimension.
     */
    @Override
    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n Expected dimension.
     * @throws DimensionMismatchException if the dimension is
     * inconsistent with vector size.
     */
    @Override
    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.checkVectorDimensions_754");
        if (ROR_not_equals(data.length, n, "org.apache.commons.math3.linear.ArrayRealVector.checkVectorDimensions_754", _mut29395, _mut29396, _mut29397, _mut29398, _mut29399)) {
            throw new DimensionMismatchException(data.length, n);
        }
    }

    /**
     * Check if any coordinate of this vector is {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is {@code NaN},
     * {@code false} otherwise.
     */
    @Override
    public boolean isNaN() {
        for (double v : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.isNaN_768");
            if (Double.isNaN(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether any coordinate of this vector is infinite and none
     * are {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is infinite and
     * none are {@code NaN}, {@code false} otherwise.
     */
    @Override
    public boolean isInfinite() {
        if (isNaN()) {
            return false;
        }
        for (double v : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.isInfinite_785");
            if (Double.isInfinite(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.equals_801");
        if (this == other) {
            return true;
        }
        if (!(other instanceof RealVector)) {
            return false;
        }
        RealVector rhs = (RealVector) other;
        if (ROR_not_equals(data.length, rhs.getDimension(), "org.apache.commons.math3.linear.ArrayRealVector.equals_801", _mut29400, _mut29401, _mut29402, _mut29403, _mut29404)) {
            return false;
        }
        if (rhs.isNaN()) {
            return this.isNaN();
        }
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.equals_801", _mut29410, _mut29411, _mut29412, _mut29413, _mut29414); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.equals_801");
            if (ROR_not_equals(data[i], rhs.getEntry(i), "org.apache.commons.math3.linear.ArrayRealVector.equals_801", _mut29405, _mut29406, _mut29407, _mut29408, _mut29409)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc} All {@code NaN} values have the same hash code.
     */
    @Override
    public int hashCode() {
        if (isNaN()) {
            return 9;
        }
        return MathUtils.hash(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
        return copy().combineToSelf(a, b, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayRealVector combineToSelf(double a, double b, RealVector y) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847");
        if (y instanceof ArrayRealVector) {
            final double[] yData = ((ArrayRealVector) y).data;
            checkVectorDimensions(yData.length);
            for (int i = 0; ROR_less(i, this.data.length, "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29444, _mut29445, _mut29446, _mut29447, _mut29448); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847");
                data[i] = AOR_plus(AOR_multiply(a, data[i], "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29432, _mut29433, _mut29434, _mut29435), AOR_multiply(b, yData[i], "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29436, _mut29437, _mut29438, _mut29439), "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29440, _mut29441, _mut29442, _mut29443);
            }
        } else {
            checkVectorDimensions(y);
            for (int i = 0; ROR_less(i, this.data.length, "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29427, _mut29428, _mut29429, _mut29430, _mut29431); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847");
                data[i] = AOR_plus(AOR_multiply(a, data[i], "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29415, _mut29416, _mut29417, _mut29418), AOR_multiply(b, y.getEntry(i), "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29419, _mut29420, _mut29421, _mut29422), "org.apache.commons.math3.linear.ArrayRealVector.combineToSelf_847", _mut29423, _mut29424, _mut29425, _mut29426);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_866");
        visitor.start(data.length, 0, AOR_minus(data.length, 1, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_866", _mut29449, _mut29450, _mut29451, _mut29452));
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_866", _mut29453, _mut29454, _mut29455, _mut29456, _mut29457); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_866");
            visitor.visit(i, data[i]);
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_876");
        checkIndices(start, end);
        visitor.start(data.length, start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_876", _mut29458, _mut29459, _mut29460, _mut29461, _mut29462); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_876");
            visitor.visit(i, data[i]);
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor) {
        return walkInDefaultOrder(visitor);
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_911");
        visitor.start(data.length, 0, AOR_minus(data.length, 1, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_911", _mut29463, _mut29464, _mut29465, _mut29466));
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_911", _mut29467, _mut29468, _mut29469, _mut29470, _mut29471); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_911");
            data[i] = visitor.visit(i, data[i]);
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_921");
        checkIndices(start, end);
        visitor.start(data.length, start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_921", _mut29472, _mut29473, _mut29474, _mut29475, _mut29476); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayRealVector.walkInDefaultOrder_921");
            data[i] = visitor.visit(i, data[i]);
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor) {
        return walkInDefaultOrder(visitor);
    }

    /**
     * {@inheritDoc}
     *
     * In this implementation, the optimized order is the default order.
     */
    @Override
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }
}
