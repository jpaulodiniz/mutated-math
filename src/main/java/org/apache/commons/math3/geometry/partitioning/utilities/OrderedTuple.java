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
package org.apache.commons.math3.geometry.partitioning.utilities;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements an ordering operation for T-uples.
 *
 * <p>Ordering is done by encoding all components of the T-uple into a
 * single scalar value and using this value as the sorting
 * key. Encoding is performed using the method invented by Georg
 * Cantor in 1877 when he proved it was possible to establish a
 * bijection between a line and a plane. The binary representations of
 * the components of the T-uple are mixed together to form a single
 * scalar. This means that the 2<sup>k</sup> bit of component 0 is
 * followed by the 2<sup>k</sup> bit of component 1, then by the
 * 2<sup>k</sup> bit of component 2 up to the 2<sup>k</sup> bit of
 * component {@code t}, which is followed by the 2<sup>k-1</sup>
 * bit of component 0, followed by the 2<sup>k-1</sup> bit of
 * component 1 ... The binary representations are extended as needed
 * to handle numbers with different scales and a suitable
 * 2<sup>p</sup> offset is added to the components in order to avoid
 * negative numbers (this offset is adjusted as needed during the
 * comparison operations).</p>
 *
 * <p>The more interesting property of the encoding method for our
 * purpose is that it allows to select all the points that are in a
 * given range. This is depicted in dimension 2 by the following
 * picture:</p>
 *
 * <img src="doc-files/OrderedTuple.png" />
 *
 * <p>This picture shows a set of 100000 random 2-D pairs having their
 * first component between -50 and +150 and their second component
 * between -350 and +50. We wanted to extract all pairs having their
 * first component between +30 and +70 and their second component
 * between -120 and -30. We built the lower left point at coordinates
 * (30, -120) and the upper right point at coordinates (70, -30). All
 * points smaller than the lower left point are drawn in red and all
 * points larger than the upper right point are drawn in blue. The
 * green points are between the two limits. This picture shows that
 * all the desired points are selected, along with spurious points. In
 * this case, we get 15790 points, 4420 of which really belonging to
 * the desired rectangle. It is possible to extract very small
 * subsets. As an example extracting from the same 100000 points set
 * the points having their first component between +30 and +31 and
 * their second component between -91 and -90, we get a subset of 11
 * points, 2 of which really belonging to the desired rectangle.</p>
 *
 * <p>the previous selection technique can be applied in all
 * dimensions, still using two points to define the interval. The
 * first point will have all its components set to their lower bounds
 * while the second point will have all its components set to their
 * upper bounds.</p>
 *
 * <p>T-uples with negative infinite or positive infinite components
 * are sorted logically.</p>
 *
 * <p>Since the specification of the {@code Comparator} interface
 * allows only {@code ClassCastException} errors, some arbitrary
 * choices have been made to handle specific cases. The rationale for
 * these choices is to keep <em>regular</em> and consistent T-uples
 * together.</p>
 * <ul>
 * <li>instances with different dimensions are sorted according to
 * their dimension regardless of their components values</li>
 * <li>instances with {@code Double.NaN} components are sorted
 * after all other ones (even after instances with positive infinite
 * components</li>
 * <li>instances with both positive and negative infinite components
 * are considered as if they had {@code Double.NaN}
 * components</li>
 * </ul>
 *
 * @since 3.0
 * @deprecated as of 3.4, this class is not used anymore and considered
 * to be out of scope of Apache Commons Math
 */
@Deprecated
public class OrderedTuple implements Comparable<OrderedTuple> {

    @Conditional
    public static boolean _mut86065 = false, _mut86066 = false, _mut86067 = false, _mut86068 = false, _mut86069 = false, _mut86070 = false, _mut86071 = false, _mut86072 = false, _mut86073 = false, _mut86074 = false, _mut86075 = false, _mut86076 = false, _mut86077 = false, _mut86078 = false, _mut86079 = false, _mut86080 = false, _mut86081 = false, _mut86082 = false, _mut86083 = false, _mut86084 = false, _mut86085 = false, _mut86086 = false, _mut86087 = false, _mut86088 = false, _mut86089 = false, _mut86090 = false, _mut86091 = false, _mut86092 = false, _mut86093 = false, _mut86094 = false, _mut86095 = false, _mut86096 = false, _mut86097 = false, _mut86098 = false, _mut86099 = false, _mut86100 = false, _mut86101 = false, _mut86102 = false, _mut86103 = false, _mut86104 = false, _mut86105 = false, _mut86106 = false, _mut86107 = false, _mut86108 = false, _mut86109 = false, _mut86110 = false, _mut86111 = false, _mut86112 = false, _mut86113 = false, _mut86114 = false, _mut86115 = false, _mut86116 = false, _mut86117 = false, _mut86118 = false, _mut86119 = false, _mut86120 = false, _mut86121 = false, _mut86122 = false, _mut86123 = false, _mut86124 = false, _mut86125 = false, _mut86126 = false, _mut86127 = false, _mut86128 = false, _mut86129 = false, _mut86130 = false, _mut86131 = false, _mut86132 = false, _mut86133 = false, _mut86134 = false, _mut86135 = false, _mut86136 = false, _mut86137 = false, _mut86138 = false, _mut86139 = false, _mut86140 = false, _mut86141 = false, _mut86142 = false, _mut86143 = false, _mut86144 = false, _mut86145 = false, _mut86146 = false, _mut86147 = false, _mut86148 = false, _mut86149 = false, _mut86150 = false, _mut86151 = false, _mut86152 = false, _mut86153 = false, _mut86154 = false, _mut86155 = false, _mut86156 = false, _mut86157 = false, _mut86158 = false, _mut86159 = false, _mut86160 = false, _mut86161 = false, _mut86162 = false, _mut86163 = false, _mut86164 = false, _mut86165 = false, _mut86166 = false, _mut86167 = false, _mut86168 = false, _mut86169 = false, _mut86170 = false, _mut86171 = false, _mut86172 = false, _mut86173 = false, _mut86174 = false, _mut86175 = false, _mut86176 = false, _mut86177 = false, _mut86178 = false, _mut86179 = false, _mut86180 = false, _mut86181 = false, _mut86182 = false, _mut86183 = false, _mut86184 = false, _mut86185 = false, _mut86186 = false, _mut86187 = false, _mut86188 = false, _mut86189 = false, _mut86190 = false, _mut86191 = false, _mut86192 = false, _mut86193 = false, _mut86194 = false, _mut86195 = false, _mut86196 = false, _mut86197 = false, _mut86198 = false, _mut86199 = false, _mut86200 = false, _mut86201 = false, _mut86202 = false, _mut86203 = false, _mut86204 = false, _mut86205 = false, _mut86206 = false, _mut86207 = false, _mut86208 = false, _mut86209 = false, _mut86210 = false, _mut86211 = false, _mut86212 = false, _mut86213 = false, _mut86214 = false, _mut86215 = false, _mut86216 = false, _mut86217 = false, _mut86218 = false, _mut86219 = false, _mut86220 = false, _mut86221 = false, _mut86222 = false, _mut86223 = false, _mut86224 = false, _mut86225 = false, _mut86226 = false, _mut86227 = false, _mut86228 = false, _mut86229 = false, _mut86230 = false, _mut86231 = false, _mut86232 = false, _mut86233 = false, _mut86234 = false, _mut86235 = false, _mut86236 = false, _mut86237 = false, _mut86238 = false, _mut86239 = false, _mut86240 = false, _mut86241 = false, _mut86242 = false, _mut86243 = false, _mut86244 = false, _mut86245 = false, _mut86246 = false, _mut86247 = false, _mut86248 = false, _mut86249 = false, _mut86250 = false, _mut86251 = false, _mut86252 = false, _mut86253 = false, _mut86254 = false, _mut86255 = false, _mut86256 = false, _mut86257 = false, _mut86258 = false, _mut86259 = false, _mut86260 = false, _mut86261 = false, _mut86262 = false, _mut86263 = false, _mut86264 = false, _mut86265 = false, _mut86266 = false, _mut86267 = false, _mut86268 = false, _mut86269 = false, _mut86270 = false, _mut86271 = false, _mut86272 = false, _mut86273 = false, _mut86274 = false, _mut86275 = false, _mut86276 = false, _mut86277 = false, _mut86278 = false, _mut86279 = false, _mut86280 = false, _mut86281 = false, _mut86282 = false, _mut86283 = false, _mut86284 = false, _mut86285 = false, _mut86286 = false, _mut86287 = false, _mut86288 = false, _mut86289 = false, _mut86290 = false, _mut86291 = false, _mut86292 = false, _mut86293 = false, _mut86294 = false, _mut86295 = false, _mut86296 = false, _mut86297 = false, _mut86298 = false, _mut86299 = false, _mut86300 = false, _mut86301 = false, _mut86302 = false, _mut86303 = false, _mut86304 = false, _mut86305 = false, _mut86306 = false, _mut86307 = false, _mut86308 = false, _mut86309 = false, _mut86310 = false, _mut86311 = false, _mut86312 = false, _mut86313 = false, _mut86314 = false, _mut86315 = false, _mut86316 = false, _mut86317 = false, _mut86318 = false, _mut86319 = false, _mut86320 = false, _mut86321 = false;

    /**
     * Sign bit mask.
     */
    private static final long SIGN_MASK = 0x8000000000000000L;

    /**
     * Exponent bits mask.
     */
    private static final long EXPONENT_MASK = 0x7ff0000000000000L;

    /**
     * Mantissa bits mask.
     */
    private static final long MANTISSA_MASK = 0x000fffffffffffffL;

    /**
     * Implicit MSB for normalized numbers.
     */
    private static final long IMPLICIT_ONE = 0x0010000000000000L;

    /**
     * Double components of the T-uple.
     */
    private double[] components;

    /**
     * Offset scale.
     */
    private int offset;

    /**
     * Least Significant Bit scale.
     */
    private int lsb;

    /**
     * Ordering encoding of the double components.
     */
    private long[] encoding;

    /**
     * Positive infinity marker.
     */
    private boolean posInf;

    /**
     * Negative infinity marker.
     */
    private boolean negInf;

    /**
     * Not A Number marker.
     */
    private boolean nan;

    /**
     * Build an ordered T-uple from its components.
     * @param components double components of the T-uple
     */
    public OrderedTuple(final double... components) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134");
        this.components = components.clone();
        int msb = Integer.MIN_VALUE;
        lsb = Integer.MAX_VALUE;
        posInf = false;
        negInf = false;
        nan = false;
        for (int i = 0; ROR_less(i, components.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86083, _mut86084, _mut86085, _mut86086, _mut86087); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134");
            if (Double.isInfinite(components[i])) {
                if (ROR_less(components[i], 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86078, _mut86079, _mut86080, _mut86081, _mut86082)) {
                    negInf = true;
                } else {
                    posInf = true;
                }
            } else if (Double.isNaN(components[i])) {
                nan = true;
            } else {
                final long b = Double.doubleToLongBits(components[i]);
                final long m = mantissa(b);
                if (ROR_not_equals(m, 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86065, _mut86066, _mut86067, _mut86068, _mut86069)) {
                    final int e = exponent(b);
                    msb = FastMath.max(msb, AOR_plus(e, computeMSB(m), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86070, _mut86071, _mut86072, _mut86073));
                    lsb = FastMath.min(lsb, AOR_plus(e, computeLSB(m), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86074, _mut86075, _mut86076, _mut86077));
                }
            }
        }
        if ((_mut86088 ? (posInf || negInf) : (posInf && negInf))) {
            // instance cannot be sorted logically
            posInf = false;
            negInf = false;
            nan = true;
        }
        if (ROR_less_equals(lsb, msb, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86089, _mut86090, _mut86091, _mut86092, _mut86093)) {
            // encode the T-upple with the specified offset
            encode(AOR_plus(msb, 16, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.OrderedTuple_134", _mut86094, _mut86095, _mut86096, _mut86097));
        } else {
            encoding = new long[] { 0x0L };
        }
    }

    /**
     * Encode the T-uple with a given offset.
     * @param minOffset minimal scale of the offset to add to all
     * components (must be greater than the MSBs of all components)
     */
    private void encode(final int minOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183");
        // choose an offset with some margins
        offset = AOR_plus(minOffset, 31, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86098, _mut86099, _mut86100, _mut86101);
        offset -= AOR_remainder(offset, 32, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86102, _mut86103, _mut86104, _mut86105);
        if ((_mut86117 ? ((_mut86111 ? ((encoding != null) || (ROR_equals(encoding.length, 1, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86106, _mut86107, _mut86108, _mut86109, _mut86110))) : ((encoding != null) && (ROR_equals(encoding.length, 1, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86106, _mut86107, _mut86108, _mut86109, _mut86110)))) || (ROR_equals(encoding[0], 0x0L, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86112, _mut86113, _mut86114, _mut86115, _mut86116))) : ((_mut86111 ? ((encoding != null) || (ROR_equals(encoding.length, 1, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86106, _mut86107, _mut86108, _mut86109, _mut86110))) : ((encoding != null) && (ROR_equals(encoding.length, 1, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86106, _mut86107, _mut86108, _mut86109, _mut86110)))) && (ROR_equals(encoding[0], 0x0L, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86112, _mut86113, _mut86114, _mut86115, _mut86116))))) {
            // the components are all zeroes
            return;
        }
        // 63 bits per element because there is no unsigned long in Java)
        final int neededBits = AOR_minus(AOR_plus(offset, 1, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86118, _mut86119, _mut86120, _mut86121), lsb, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86122, _mut86123, _mut86124, _mut86125);
        final int neededLongs = AOR_divide((AOR_plus(neededBits, 62, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86126, _mut86127, _mut86128, _mut86129)), 63, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86130, _mut86131, _mut86132, _mut86133);
        encoding = new long[AOR_multiply(components.length, neededLongs, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86134, _mut86135, _mut86136, _mut86137)];
        // mix the bits from all components
        int eIndex = 0;
        int shift = 62;
        long word = 0x0L;
        for (int k = offset; ROR_less(eIndex, encoding.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86153, _mut86154, _mut86155, _mut86156, _mut86157); --k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183");
            for (int vIndex = 0; ROR_less(vIndex, components.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86148, _mut86149, _mut86150, _mut86151, _mut86152); ++vIndex) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183");
                if (ROR_not_equals(getBit(vIndex, k), 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86138, _mut86139, _mut86140, _mut86141, _mut86142)) {
                    word |= 0x1L << shift;
                }
                if (ROR_equals(shift--, 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.encode_183", _mut86143, _mut86144, _mut86145, _mut86146, _mut86147)) {
                    encoding[eIndex++] = word;
                    word = 0x0L;
                    shift = 62;
                }
            }
        }
    }

    /**
     * Compares this ordered T-uple with the specified object.
     *
     * <p>The ordering method is detailed in the general description of
     * the class. Its main property is to be consistent with distance:
     * geometrically close T-uples stay close to each other when stored
     * in a sorted collection using this comparison method.</p>
     *
     * <p>T-uples with negative infinite, positive infinite are sorted
     * logically.</p>
     *
     * <p>Some arbitrary choices have been made to handle specific
     * cases. The rationale for these choices is to keep
     * <em>normal</em> and consistent T-uples together.</p>
     * <ul>
     * <li>instances with different dimensions are sorted according to
     * their dimension regardless of their components values</li>
     * <li>instances with {@code Double.NaN} components are sorted
     * after all other ones (evan after instances with positive infinite
     * components</li>
     * <li>instances with both positive and negative infinite components
     * are considered as if they had {@code Double.NaN}
     * components</li>
     * </ul>
     *
     * @param ot T-uple to compare instance with
     * @return a negative integer if the instance is less than the
     * object, zero if they are equal, or a positive integer if the
     * instance is greater than the object
     */
    public int compareTo(final OrderedTuple ot) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249");
        if (ROR_equals(components.length, ot.components.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86158, _mut86159, _mut86160, _mut86161, _mut86162)) {
            if (nan) {
                return +1;
            } else if (ot.nan) {
                return -1;
            } else if ((_mut86163 ? (negInf && ot.posInf) : (negInf || ot.posInf))) {
                return -1;
            } else if ((_mut86164 ? (posInf && ot.negInf) : (posInf || ot.negInf))) {
                return +1;
            } else {
                if (ROR_less(offset, ot.offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86165, _mut86166, _mut86167, _mut86168, _mut86169)) {
                    encode(ot.offset);
                } else if (ROR_greater(offset, ot.offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86170, _mut86171, _mut86172, _mut86173, _mut86174)) {
                    ot.encode(offset);
                }
                final int limit = FastMath.min(encoding.length, ot.encoding.length);
                for (int i = 0; ROR_less(i, limit, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86185, _mut86186, _mut86187, _mut86188, _mut86189); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249");
                    if (ROR_less(encoding[i], ot.encoding[i], "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86175, _mut86176, _mut86177, _mut86178, _mut86179)) {
                        return -1;
                    } else if (ROR_greater(encoding[i], ot.encoding[i], "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86180, _mut86181, _mut86182, _mut86183, _mut86184)) {
                        return +1;
                    }
                }
                if (ROR_less(encoding.length, ot.encoding.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86190, _mut86191, _mut86192, _mut86193, _mut86194)) {
                    return -1;
                } else if (ROR_greater(encoding.length, ot.encoding.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86195, _mut86196, _mut86197, _mut86198, _mut86199)) {
                    return +1;
                } else {
                    return 0;
                }
            }
        }
        return AOR_minus(components.length, ot.components.length, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.compareTo_249", _mut86200, _mut86201, _mut86202, _mut86203);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.equals_292");
        if (this == other) {
            return true;
        } else if (other instanceof OrderedTuple) {
            return ROR_equals(compareTo((OrderedTuple) other), 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.equals_292", _mut86204, _mut86205, _mut86206, _mut86207, _mut86208);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304");
        // the following constants are arbitrary small primes
        final int multiplier = 37;
        final int trueHash = 97;
        final int falseHash = 71;
        // for all int fields and all boolean fields)
        int hash = Arrays.hashCode(components);
        hash = AOR_plus(AOR_multiply(hash, multiplier, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86209, _mut86210, _mut86211, _mut86212), offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86213, _mut86214, _mut86215, _mut86216);
        hash = AOR_plus(AOR_multiply(hash, multiplier, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86217, _mut86218, _mut86219, _mut86220), lsb, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86221, _mut86222, _mut86223, _mut86224);
        hash = AOR_plus(AOR_multiply(hash, multiplier, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86225, _mut86226, _mut86227, _mut86228), (posInf ? trueHash : falseHash), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86229, _mut86230, _mut86231, _mut86232);
        hash = AOR_plus(AOR_multiply(hash, multiplier, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86233, _mut86234, _mut86235, _mut86236), (negInf ? trueHash : falseHash), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86237, _mut86238, _mut86239, _mut86240);
        hash = AOR_plus(AOR_multiply(hash, multiplier, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86241, _mut86242, _mut86243, _mut86244), (nan ? trueHash : falseHash), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.hashCode_304", _mut86245, _mut86246, _mut86247, _mut86248);
        return hash;
    }

    /**
     * Get the components array.
     * @return array containing the T-uple components
     */
    public double[] getComponents() {
        return components.clone();
    }

    /**
     * Extract the sign from the bits of a double.
     * @param bits binary representation of the double
     * @return sign bit (zero if positive, non zero if negative)
     */
    private static long sign(final long bits) {
        return bits & SIGN_MASK;
    }

    /**
     * Extract the exponent from the bits of a double.
     * @param bits binary representation of the double
     * @return exponent
     */
    private static int exponent(final long bits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.exponent_344");
        return AOR_minus(((int) ((bits & EXPONENT_MASK) >> 52)), 1075, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.exponent_344", _mut86249, _mut86250, _mut86251, _mut86252);
    }

    /**
     * Extract the mantissa from the bits of a double.
     * @param bits binary representation of the double
     * @return mantissa
     */
    private static long mantissa(final long bits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.mantissa_352");
        return (ROR_equals((bits & EXPONENT_MASK), 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.mantissa_352", _mut86253, _mut86254, _mut86255, _mut86256, _mut86257)) ? // subnormal number
        ((bits & MANTISSA_MASK) << 1) : // normal number
        (IMPLICIT_ONE | (bits & MANTISSA_MASK));
    }

    /**
     * Compute the most significant bit of a long.
     * @param l long from which the most significant bit is requested
     * @return scale of the most significant bit of {@code l},
     * or 0 if {@code l} is zero
     * @see #computeLSB
     */
    private static int computeMSB(final long l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeMSB_364");
        long ll = l;
        long mask = 0xffffffffL;
        int scale = 32;
        int msb = 0;
        while (ROR_not_equals(scale, 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeMSB_364", _mut86263, _mut86264, _mut86265, _mut86266, _mut86267)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeMSB_364");
            if (ROR_not_equals((ll & mask), ll, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeMSB_364", _mut86258, _mut86259, _mut86260, _mut86261, _mut86262)) {
                msb |= scale;
                ll >>= scale;
            }
            scale >>= 1;
            mask >>= scale;
        }
        return msb;
    }

    /**
     * Compute the least significant bit of a long.
     * @param l long from which the least significant bit is requested
     * @return scale of the least significant bit of {@code l},
     * or 63 if {@code l} is zero
     * @see #computeMSB
     */
    private static int computeLSB(final long l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeLSB_390");
        long ll = l;
        long mask = 0xffffffff00000000L;
        int scale = 32;
        int lsb = 0;
        while (ROR_not_equals(scale, 0, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeLSB_390", _mut86273, _mut86274, _mut86275, _mut86276, _mut86277)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeLSB_390");
            if (ROR_equals((ll & mask), ll, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.computeLSB_390", _mut86268, _mut86269, _mut86270, _mut86271, _mut86272)) {
                lsb |= scale;
                ll >>= scale;
            }
            scale >>= 1;
            mask >>= scale;
        }
        return lsb;
    }

    /**
     * Get a bit from the mantissa of a double.
     * @param i index of the component
     * @param k scale of the requested bit
     * @return the specified bit (either 0 or 1), after the offset has
     * been added to the double
     */
    private int getBit(final int i, final int k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416");
        final long bits = Double.doubleToLongBits(components[i]);
        final int e = exponent(bits);
        if ((_mut86288 ? ((ROR_less(k, e, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86278, _mut86279, _mut86280, _mut86281, _mut86282)) && (ROR_greater(k, offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86283, _mut86284, _mut86285, _mut86286, _mut86287))) : ((ROR_less(k, e, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86278, _mut86279, _mut86280, _mut86281, _mut86282)) || (ROR_greater(k, offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86283, _mut86284, _mut86285, _mut86286, _mut86287))))) {
            return 0;
        } else if (ROR_equals(k, offset, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86289, _mut86290, _mut86291, _mut86292, _mut86293)) {
            return (ROR_equals(sign(bits), 0L, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86317, _mut86318, _mut86319, _mut86320, _mut86321)) ? 1 : 0;
        } else if (ROR_greater(k, (AOR_plus(e, 52, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86294, _mut86295, _mut86296, _mut86297)), "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86298, _mut86299, _mut86300, _mut86301, _mut86302)) {
            return (ROR_equals(sign(bits), 0L, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86312, _mut86313, _mut86314, _mut86315, _mut86316)) ? 0 : 1;
        } else {
            final long m = (ROR_equals(sign(bits), 0L, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86303, _mut86304, _mut86305, _mut86306, _mut86307)) ? mantissa(bits) : -mantissa(bits);
            return (int) ((m >> (AOR_minus(k, e, "org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple.getBit_416", _mut86308, _mut86309, _mut86310, _mut86311))) & 0x1L);
        }
    }
}
