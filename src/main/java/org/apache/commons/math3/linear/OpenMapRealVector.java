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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the {@link RealVector} interface with a
 * {@link OpenIntToDoubleHashMap} backing store.
 * <p>
 *  Caveat: This implementation assumes that, for any {@code x},
 *  the equality {@code x * 0d == 0d} holds. But it is is not true for
 *  {@code NaN}. Moreover, zero entries will lose their sign.
 *  Some operations (that involve {@code NaN} and/or infinities) may
 *  thus give incorrect results, like multiplications, divisions or
 *  functions mapping.
 * </p>
 * @since 2.0
 */
public class OpenMapRealVector extends SparseRealVector implements Serializable {

    @Conditional
    public static boolean _mut32021 = false, _mut32022 = false, _mut32023 = false, _mut32024 = false, _mut32025 = false, _mut32026 = false, _mut32027 = false, _mut32028 = false, _mut32029 = false, _mut32030 = false, _mut32031 = false, _mut32032 = false, _mut32033 = false, _mut32034 = false, _mut32035 = false, _mut32036 = false, _mut32037 = false, _mut32038 = false, _mut32039 = false, _mut32040 = false, _mut32041 = false, _mut32042 = false, _mut32043 = false, _mut32044 = false, _mut32045 = false, _mut32046 = false, _mut32047 = false, _mut32048 = false, _mut32049 = false, _mut32050 = false, _mut32051 = false, _mut32052 = false, _mut32053 = false, _mut32054 = false, _mut32055 = false, _mut32056 = false, _mut32057 = false, _mut32058 = false, _mut32059 = false, _mut32060 = false, _mut32061 = false, _mut32062 = false, _mut32063 = false, _mut32064 = false, _mut32065 = false, _mut32066 = false, _mut32067 = false, _mut32068 = false, _mut32069 = false, _mut32070 = false, _mut32071 = false, _mut32072 = false, _mut32073 = false, _mut32074 = false, _mut32075 = false, _mut32076 = false, _mut32077 = false, _mut32078 = false, _mut32079 = false, _mut32080 = false, _mut32081 = false, _mut32082 = false, _mut32083 = false, _mut32084 = false, _mut32085 = false, _mut32086 = false, _mut32087 = false, _mut32088 = false, _mut32089 = false, _mut32090 = false, _mut32091 = false, _mut32092 = false, _mut32093 = false, _mut32094 = false, _mut32095 = false, _mut32096 = false, _mut32097 = false, _mut32098 = false, _mut32099 = false, _mut32100 = false, _mut32101 = false, _mut32102 = false, _mut32103 = false, _mut32104 = false, _mut32105 = false, _mut32106 = false, _mut32107 = false, _mut32108 = false, _mut32109 = false, _mut32110 = false, _mut32111 = false, _mut32112 = false, _mut32113 = false, _mut32114 = false, _mut32115 = false, _mut32116 = false, _mut32117 = false, _mut32118 = false, _mut32119 = false, _mut32120 = false, _mut32121 = false, _mut32122 = false, _mut32123 = false, _mut32124 = false, _mut32125 = false, _mut32126 = false, _mut32127 = false, _mut32128 = false, _mut32129 = false, _mut32130 = false, _mut32131 = false, _mut32132 = false, _mut32133 = false, _mut32134 = false, _mut32135 = false, _mut32136 = false, _mut32137 = false, _mut32138 = false, _mut32139 = false, _mut32140 = false, _mut32141 = false, _mut32142 = false, _mut32143 = false, _mut32144 = false, _mut32145 = false, _mut32146 = false, _mut32147 = false, _mut32148 = false, _mut32149 = false, _mut32150 = false, _mut32151 = false, _mut32152 = false, _mut32153 = false, _mut32154 = false, _mut32155 = false, _mut32156 = false, _mut32157 = false, _mut32158 = false, _mut32159 = false, _mut32160 = false, _mut32161 = false, _mut32162 = false, _mut32163 = false, _mut32164 = false, _mut32165 = false, _mut32166 = false, _mut32167 = false, _mut32168 = false, _mut32169 = false, _mut32170 = false, _mut32171 = false, _mut32172 = false, _mut32173 = false, _mut32174 = false, _mut32175 = false, _mut32176 = false, _mut32177 = false, _mut32178 = false, _mut32179 = false, _mut32180 = false, _mut32181 = false, _mut32182 = false, _mut32183 = false, _mut32184 = false, _mut32185 = false, _mut32186 = false, _mut32187 = false, _mut32188 = false, _mut32189 = false, _mut32190 = false, _mut32191 = false, _mut32192 = false, _mut32193 = false, _mut32194 = false, _mut32195 = false, _mut32196 = false, _mut32197 = false, _mut32198 = false, _mut32199 = false, _mut32200 = false, _mut32201 = false, _mut32202 = false, _mut32203 = false, _mut32204 = false, _mut32205 = false, _mut32206 = false, _mut32207 = false, _mut32208 = false, _mut32209 = false, _mut32210 = false, _mut32211 = false, _mut32212 = false, _mut32213 = false, _mut32214 = false, _mut32215 = false, _mut32216 = false, _mut32217 = false, _mut32218 = false, _mut32219 = false, _mut32220 = false, _mut32221 = false, _mut32222 = false, _mut32223 = false, _mut32224 = false, _mut32225 = false, _mut32226 = false, _mut32227 = false, _mut32228 = false, _mut32229 = false;

    /**
     * Default Tolerance for having a value considered zero.
     */
    public static final double DEFAULT_ZERO_TOLERANCE = 1.0e-12;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 8772222695580707260L;

    /**
     * Entries of the vector.
     */
    private final OpenIntToDoubleHashMap entries;

    /**
     * Dimension of the vector.
     */
    private final int virtualSize;

    /**
     * Tolerance for having a value considered zero.
     */
    private final double epsilon;

    /**
     * Build a 0-length vector.
     * Zero-length vectors may be used to initialized construction of vectors
     * by data gathering. We start with zero-length and use either the {@link
     * #OpenMapRealVector(OpenMapRealVector, int)} constructor
     * or one of the {@code append} method ({@link #append(double)},
     * {@link #append(RealVector)}) to gather data into this vector.
     */
    public OpenMapRealVector() {
        this(0, DEFAULT_ZERO_TOLERANCE);
    }

    /**
     * Construct a vector of zeroes.
     *
     * @param dimension Size of the vector.
     */
    public OpenMapRealVector(int dimension) {
        this(dimension, DEFAULT_ZERO_TOLERANCE);
    }

    /**
     * Construct a vector of zeroes, specifying zero tolerance.
     *
     * @param dimension Size of the vector.
     * @param epsilon Tolerance below which a value considered zero.
     */
    public OpenMapRealVector(int dimension, double epsilon) {
        virtualSize = dimension;
        entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
    }

    /**
     * Build a resized vector, for use with append.
     *
     * @param v Original vector.
     * @param resize Amount to add.
     */
    protected OpenMapRealVector(OpenMapRealVector v, int resize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_95");
        virtualSize = AOR_plus(v.getDimension(), resize, "org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_95", _mut32021, _mut32022, _mut32023, _mut32024);
        entries = new OpenIntToDoubleHashMap(v.entries);
        epsilon = v.epsilon;
    }

    /**
     * Build a vector with known the sparseness (for advanced use only).
     *
     * @param dimension Size of the vector.
     * @param expectedSize The expected number of non-zero entries.
     */
    public OpenMapRealVector(int dimension, int expectedSize) {
        this(dimension, expectedSize, DEFAULT_ZERO_TOLERANCE);
    }

    /**
     * Build a vector with known the sparseness and zero tolerance
     * setting (for advanced use only).
     *
     * @param dimension Size of the vector.
     * @param expectedSize Expected number of non-zero entries.
     * @param epsilon Tolerance below which a value is considered zero.
     */
    public OpenMapRealVector(int dimension, int expectedSize, double epsilon) {
        virtualSize = dimension;
        entries = new OpenIntToDoubleHashMap(expectedSize, 0.0);
        this.epsilon = epsilon;
    }

    /**
     * Create from an array.
     * Only non-zero entries will be stored.
     *
     * @param values Set of values to create from.
     */
    public OpenMapRealVector(double[] values) {
        this(values, DEFAULT_ZERO_TOLERANCE);
    }

    /**
     * Create from an array, specifying zero tolerance.
     * Only non-zero entries will be stored.
     *
     * @param values Set of values to create from.
     * @param epsilon Tolerance below which a value is considered zero.
     */
    public OpenMapRealVector(double[] values, double epsilon) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_142");
        virtualSize = values.length;
        entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
        for (int key = 0; ROR_less(key, values.length, "org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_142", _mut32025, _mut32026, _mut32027, _mut32028, _mut32029); key++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_142");
            double value = values[key];
            if (!isDefaultValue(value)) {
                entries.put(key, value);
            }
        }
    }

    /**
     * Create from an array.
     * Only non-zero entries will be stored.
     *
     * @param values The set of values to create from
     */
    public OpenMapRealVector(Double[] values) {
        this(values, DEFAULT_ZERO_TOLERANCE);
    }

    /**
     * Create from an array.
     * Only non-zero entries will be stored.
     *
     * @param values Set of values to create from.
     * @param epsilon Tolerance below which a value is considered zero.
     */
    public OpenMapRealVector(Double[] values, double epsilon) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_171");
        virtualSize = values.length;
        entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
        for (int key = 0; ROR_less(key, values.length, "org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_171", _mut32030, _mut32031, _mut32032, _mut32033, _mut32034); key++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_171");
            double value = values[key].doubleValue();
            if (!isDefaultValue(value)) {
                entries.put(key, value);
            }
        }
    }

    /**
     * Copy constructor.
     *
     * @param v Instance to copy from.
     */
    public OpenMapRealVector(OpenMapRealVector v) {
        virtualSize = v.getDimension();
        entries = new OpenIntToDoubleHashMap(v.getEntries());
        epsilon = v.epsilon;
    }

    /**
     * Generic copy constructor.
     *
     * @param v Instance to copy from.
     */
    public OpenMapRealVector(RealVector v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_199");
        virtualSize = v.getDimension();
        entries = new OpenIntToDoubleHashMap(0.0);
        epsilon = DEFAULT_ZERO_TOLERANCE;
        for (int key = 0; ROR_less(key, virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_199", _mut32035, _mut32036, _mut32037, _mut32038, _mut32039); key++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.OpenMapRealVector_199");
            double value = v.getEntry(key);
            if (!isDefaultValue(value)) {
                entries.put(key, value);
            }
        }
    }

    /**
     * Get the entries of this instance.
     *
     * @return the entries of this instance.
     */
    private OpenIntToDoubleHashMap getEntries() {
        return entries;
    }

    /**
     * Determine if this value is within epsilon of zero.
     *
     * @param value Value to test
     * @return {@code true} if this value is within epsilon to zero,
     * {@code false} otherwise.
     * @since 2.1
     */
    protected boolean isDefaultValue(double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.isDefaultValue_228");
        return ROR_less(FastMath.abs(value), epsilon, "org.apache.commons.math3.linear.OpenMapRealVector.isDefaultValue_228", _mut32040, _mut32041, _mut32042, _mut32043, _mut32044);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector add(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return add((OpenMapRealVector) v);
        } else {
            return super.add(v);
        }
    }

    /**
     * Optimized method to add two OpenMapRealVectors.
     * It copies the larger vector, then iterates over the smaller.
     *
     * @param v Vector to add.
     * @return the sum of {@code this} and {@code v}.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    public OpenMapRealVector add(OpenMapRealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.add_252");
        checkVectorDimensions(v.getDimension());
        boolean copyThis = ROR_greater(entries.size(), v.entries.size(), "org.apache.commons.math3.linear.OpenMapRealVector.add_252", _mut32045, _mut32046, _mut32047, _mut32048, _mut32049);
        OpenMapRealVector res = copyThis ? this.copy() : v.copy();
        Iterator iter = copyThis ? v.entries.iterator() : entries.iterator();
        OpenIntToDoubleHashMap randomAccess = copyThis ? entries : v.entries;
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.add_252");
            iter.advance();
            int key = iter.key();
            if (randomAccess.containsKey(key)) {
                res.setEntry(key, AOR_plus(randomAccess.get(key), iter.value(), "org.apache.commons.math3.linear.OpenMapRealVector.add_252", _mut32050, _mut32051, _mut32052, _mut32053));
            } else {
                res.setEntry(key, iter.value());
            }
        }
        return res;
    }

    /**
     * Optimized method to append a OpenMapRealVector.
     * @param v vector to append
     * @return The result of appending {@code v} to self
     */
    public OpenMapRealVector append(OpenMapRealVector v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.append_276");
        OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
        Iterator iter = v.entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.append_276");
            iter.advance();
            res.setEntry(AOR_plus(iter.key(), virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.append_276", _mut32054, _mut32055, _mut32056, _mut32057), iter.value());
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector append(RealVector v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.append_287");
        if (v instanceof OpenMapRealVector) {
            return append((OpenMapRealVector) v);
        } else {
            final OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
            for (int i = 0; ROR_less(i, v.getDimension(), "org.apache.commons.math3.linear.OpenMapRealVector.append_287", _mut32062, _mut32063, _mut32064, _mut32065, _mut32066); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.append_287");
                res.setEntry(AOR_plus(i, virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.append_287", _mut32058, _mut32059, _mut32060, _mut32061), v.getEntry(i));
            }
            return res;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector append(double d) {
        OpenMapRealVector res = new OpenMapRealVector(this, 1);
        res.setEntry(virtualSize, d);
        return res;
    }

    /**
     * {@inheritDoc}
     * @since 2.1
     */
    @Override
    public OpenMapRealVector copy() {
        return new OpenMapRealVector(this);
    }

    /**
     * Computes the dot product.
     * Note that the computation is now performed in the parent class: no
     * performance improvement is to be expected from this overloaded
     * method.
     * The previous implementation was buggy and cannot be easily fixed
     * (see MATH-795).
     *
     * @param v Vector.
     * @return the dot product of this vector with {@code v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     *
     * @deprecated as of 3.1 (to be removed in 4.0). The computation is
     * performed by the parent class. The method must be kept to maintain
     * backwards compatibility.
     */
    @Deprecated
    public double dotProduct(OpenMapRealVector v) throws DimensionMismatchException {
        return dotProduct((RealVector) v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector ebeDivide(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.ebeDivide_341");
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        /*
         * MATH-803: it is not sufficient to loop through non zero entries of
         * this only. Indeed, if this[i] = 0d and v[i] = 0d, then
         * this[i] / v[i] = NaN, and not 0d.
         */
        final int n = getDimension();
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.OpenMapRealVector.ebeDivide_341", _mut32071, _mut32072, _mut32073, _mut32074, _mut32075); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.ebeDivide_341");
            res.setEntry(i, AOR_divide(this.getEntry(i), v.getEntry(i), "org.apache.commons.math3.linear.OpenMapRealVector.ebeDivide_341", _mut32067, _mut32068, _mut32069, _mut32070));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector ebeMultiply(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.ebeMultiply_359");
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.ebeMultiply_359");
            iter.advance();
            res.setEntry(iter.key(), AOR_multiply(iter.value(), v.getEntry(iter.key()), "org.apache.commons.math3.linear.OpenMapRealVector.ebeMultiply_359", _mut32076, _mut32077, _mut32078, _mut32079));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector getSubVector(int index, int n) throws NotPositiveException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373");
        checkIndex(index);
        if (ROR_less(n, 0, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32080, _mut32081, _mut32082, _mut32083, _mut32084)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        checkIndex(AOR_minus(AOR_plus(index, n, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32085, _mut32086, _mut32087, _mut32088), 1, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32089, _mut32090, _mut32091, _mut32092));
        OpenMapRealVector res = new OpenMapRealVector(n);
        int end = AOR_plus(index, n, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32093, _mut32094, _mut32095, _mut32096);
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373");
            iter.advance();
            int key = iter.key();
            if ((_mut32107 ? (ROR_greater_equals(key, index, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32097, _mut32098, _mut32099, _mut32100, _mut32101) || ROR_less(key, end, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32102, _mut32103, _mut32104, _mut32105, _mut32106)) : (ROR_greater_equals(key, index, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32097, _mut32098, _mut32099, _mut32100, _mut32101) && ROR_less(key, end, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32102, _mut32103, _mut32104, _mut32105, _mut32106)))) {
                res.setEntry(AOR_minus(key, index, "org.apache.commons.math3.linear.OpenMapRealVector.getSubVector_373", _mut32108, _mut32109, _mut32110, _mut32111), iter.value());
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return virtualSize;
    }

    /**
     * Optimized method to compute distance.
     *
     * @param v Vector to compute distance to.
     * @return the distance from {@code this} and {@code v}.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    public double getDistance(OpenMapRealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407");
        checkVectorDimensions(v.getDimension());
        Iterator iter = entries.iterator();
        double res = 0;
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407");
            iter.advance();
            int key = iter.key();
            double delta;
            delta = AOR_minus(iter.value(), v.getEntry(key), "org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407", _mut32112, _mut32113, _mut32114, _mut32115);
            res += AOR_multiply(delta, delta, "org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407", _mut32116, _mut32117, _mut32118, _mut32119);
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407");
            iter.advance();
            int key = iter.key();
            if (!entries.containsKey(key)) {
                final double value = iter.value();
                res += AOR_multiply(value, value, "org.apache.commons.math3.linear.OpenMapRealVector.getDistance_407", _mut32120, _mut32121, _mut32122, _mut32123);
            }
        }
        return FastMath.sqrt(res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getDistance((OpenMapRealVector) v);
        } else {
            return super.getDistance(v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(int index) throws OutOfRangeException {
        checkIndex(index);
        return entries.get(index);
    }

    /**
     * Distance between two vectors.
     * This method computes the distance consistent with
     * L<sub>1</sub> norm, i.e. the sum of the absolute values of
     * elements differences.
     *
     * @param v Vector to which distance is requested.
     * @return distance between this vector and {@code v}.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    public double getL1Distance(OpenMapRealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getL1Distance_459");
        checkVectorDimensions(v.getDimension());
        double max = 0;
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getL1Distance_459");
            iter.advance();
            double delta = FastMath.abs(AOR_minus(iter.value(), v.getEntry(iter.key()), "org.apache.commons.math3.linear.OpenMapRealVector.getL1Distance_459", _mut32124, _mut32125, _mut32126, _mut32127));
            max += delta;
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getL1Distance_459");
            iter.advance();
            int key = iter.key();
            if (!entries.containsKey(key)) {
                double delta = FastMath.abs(iter.value());
                max += FastMath.abs(delta);
            }
        }
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getL1Distance((OpenMapRealVector) v);
        } else {
            return super.getL1Distance(v);
        }
    }

    /**
     * Optimized method to compute LInfDistance.
     *
     * @param v Vector to compute distance from.
     * @return the LInfDistance.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    private double getLInfDistance(OpenMapRealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500");
        checkVectorDimensions(v.getDimension());
        double max = 0;
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500");
            iter.advance();
            double delta = FastMath.abs(AOR_minus(iter.value(), v.getEntry(iter.key()), "org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500", _mut32128, _mut32129, _mut32130, _mut32131));
            if (ROR_greater(delta, max, "org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500", _mut32132, _mut32133, _mut32134, _mut32135, _mut32136)) {
                max = delta;
            }
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500");
            iter.advance();
            int key = iter.key();
            if ((_mut32142 ? (!entries.containsKey(key) || ROR_greater(iter.value(), max, "org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500", _mut32137, _mut32138, _mut32139, _mut32140, _mut32141)) : (!entries.containsKey(key) && ROR_greater(iter.value(), max, "org.apache.commons.math3.linear.OpenMapRealVector.getLInfDistance_500", _mut32137, _mut32138, _mut32139, _mut32140, _mut32141)))) {
                max = iter.value();
            }
        }
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return getLInfDistance((OpenMapRealVector) v);
        } else {
            return super.getLInfDistance(v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        boolean infiniteFound = false;
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.isInfinite_536");
            iter.advance();
            final double value = iter.value();
            if (Double.isNaN(value)) {
                return false;
            }
            if (Double.isInfinite(value)) {
                infiniteFound = true;
            }
        }
        return infiniteFound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.isNaN_554");
            iter.advance();
            if (Double.isNaN(iter.value())) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector mapAddToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.mapAddToSelf_573");
        for (int i = 0; ROR_less(i, virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.mapAddToSelf_573", _mut32147, _mut32148, _mut32149, _mut32150, _mut32151); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.mapAddToSelf_573");
            setEntry(i, AOR_plus(getEntry(i), d, "org.apache.commons.math3.linear.OpenMapRealVector.mapAddToSelf_573", _mut32143, _mut32144, _mut32145, _mut32146));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(int index, double value) throws OutOfRangeException {
        checkIndex(index);
        if (!isDefaultValue(value)) {
            entries.put(index, value);
        } else if (entries.containsKey(index)) {
            entries.remove(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubVector(int index, RealVector v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594");
        checkIndex(index);
        checkIndex(AOR_minus(AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594", _mut32152, _mut32153, _mut32154, _mut32155), 1, "org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594", _mut32156, _mut32157, _mut32158, _mut32159));
        for (int i = 0; ROR_less(i, v.getDimension(), "org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594", _mut32164, _mut32165, _mut32166, _mut32167, _mut32168); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594");
            setEntry(AOR_plus(i, index, "org.apache.commons.math3.linear.OpenMapRealVector.setSubVector_594", _mut32160, _mut32161, _mut32162, _mut32163), v.getEntry(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.set_605");
        for (int i = 0; ROR_less(i, virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.set_605", _mut32169, _mut32170, _mut32171, _mut32172, _mut32173); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.set_605");
            setEntry(i, value);
        }
    }

    /**
     * Optimized method to subtract OpenMapRealVectors.
     *
     * @param v Vector to subtract from {@code this}.
     * @return the difference of {@code this} and {@code v}.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    public OpenMapRealVector subtract(OpenMapRealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.subtract_619");
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = copy();
        Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.subtract_619");
            iter.advance();
            int key = iter.key();
            if (entries.containsKey(key)) {
                res.setEntry(key, AOR_minus(entries.get(key), iter.value(), "org.apache.commons.math3.linear.OpenMapRealVector.subtract_619", _mut32174, _mut32175, _mut32176, _mut32177));
            } else {
                res.setEntry(key, -iter.value());
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector subtract(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return subtract((OpenMapRealVector) v);
        } else {
            return super.subtract(v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpenMapRealVector unitVector() throws MathArithmeticException {
        OpenMapRealVector res = copy();
        res.unitize();
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unitize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.unitize_657");
        double norm = getNorm();
        if (isDefaultValue(norm)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.unitize_657");
            iter.advance();
            entries.put(iter.key(), AOR_divide(iter.value(), norm, "org.apache.commons.math3.linear.OpenMapRealVector.unitize_657", _mut32178, _mut32179, _mut32180, _mut32181));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        double[] res = new double[virtualSize];
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.toArray_671");
            iter.advance();
            res[iter.key()] = iter.value();
        }
        return res;
    }

    /**
     * {@inheritDoc}
     * Implementation Note: This works on exact values, and as a result
     * it is possible for {@code a.subtract(b)} to be the zero vector, while
     * {@code a.hashCode() != b.hashCode()}.
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688");
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(epsilon);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32182, _mut32183, _mut32184, _mut32185), (int) (temp ^ (temp >>> 32)), "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32186, _mut32187, _mut32188, _mut32189);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32190, _mut32191, _mut32192, _mut32193), virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32194, _mut32195, _mut32196, _mut32197);
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688");
            iter.advance();
            temp = Double.doubleToLongBits(iter.value());
            result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32198, _mut32199, _mut32200, _mut32201), (int) (temp ^ (temp >> 32)), "org.apache.commons.math3.linear.OpenMapRealVector.hashCode_688", _mut32202, _mut32203, _mut32204, _mut32205);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * Implementation Note: This performs an exact comparison, and as a result
     * it is possible for {@code a.subtract(b}} to be the zero vector, while
     * {@code  a.equals(b) == false}.
     */
    @Override
    public boolean equals(Object obj) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.equals_711");
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenMapRealVector)) {
            return false;
        }
        OpenMapRealVector other = (OpenMapRealVector) obj;
        if (ROR_not_equals(virtualSize, other.virtualSize, "org.apache.commons.math3.linear.OpenMapRealVector.equals_711", _mut32206, _mut32207, _mut32208, _mut32209, _mut32210)) {
            return false;
        }
        if (ROR_not_equals(Double.doubleToLongBits(epsilon), Double.doubleToLongBits(other.epsilon), "org.apache.commons.math3.linear.OpenMapRealVector.equals_711", _mut32211, _mut32212, _mut32213, _mut32214, _mut32215)) {
            return false;
        }
        Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.equals_711");
            iter.advance();
            double test = other.getEntry(iter.key());
            if (ROR_not_equals(Double.doubleToLongBits(test), Double.doubleToLongBits(iter.value()), "org.apache.commons.math3.linear.OpenMapRealVector.equals_711", _mut32216, _mut32217, _mut32218, _mut32219, _mut32220)) {
                return false;
            }
        }
        iter = other.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.equals_711");
            iter.advance();
            double test = iter.value();
            if (ROR_not_equals(Double.doubleToLongBits(test), Double.doubleToLongBits(getEntry(iter.key())), "org.apache.commons.math3.linear.OpenMapRealVector.equals_711", _mut32221, _mut32222, _mut32223, _mut32224, _mut32225)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the percentage of none zero elements as a decimal percent.
     * @since 2.2
     */
    public double getSparsity() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.OpenMapRealVector.getSparsity_751");
        return AOR_divide((double) entries.size(), (double) getDimension(), "org.apache.commons.math3.linear.OpenMapRealVector.getSparsity_751", _mut32226, _mut32227, _mut32228, _mut32229);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Iterator<Entry> sparseIterator() {
        return new OpenMapSparseIterator();
    }

    /**
     * Implementation of {@code Entry} optimized for OpenMap.
     * This implementation does not allow arbitrary calls to {@code setIndex}
     * since the order in which entries are returned is undefined.
     */
    protected class OpenMapEntry extends Entry {

        /**
         * Iterator pointing to the entry.
         */
        private final Iterator iter;

        /**
         * Build an entry from an iterator point to an element.
         *
         * @param iter Iterator pointing to the entry.
         */
        protected OpenMapEntry(Iterator iter) {
            this.iter = iter;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double getValue() {
            return iter.value();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setValue(double value) {
            entries.put(iter.key(), value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getIndex() {
            return iter.key();
        }
    }

    /**
     * Iterator class to do iteration over just the non-zero elements.
     * This implementation is fail-fast, so cannot be used to modify
     * any zero element.
     */
    protected class OpenMapSparseIterator implements java.util.Iterator<Entry> {

        /**
         * Underlying iterator.
         */
        private final Iterator iter;

        /**
         * Current entry.
         */
        private final Entry current;

        /**
         * Simple constructor.
         */
        protected OpenMapSparseIterator() {
            iter = entries.iterator();
            current = new OpenMapEntry(iter);
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return iter.hasNext();
        }

        /**
         * {@inheritDoc}
         */
        public Entry next() {
            iter.advance();
            return current;
        }

        /**
         * {@inheritDoc}
         */
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
