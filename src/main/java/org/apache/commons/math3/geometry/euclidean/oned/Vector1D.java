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
package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a 1D vector.
 * <p>Instances of this class are guaranteed to be immutable.</p>
 * @since 3.0
 */
public class Vector1D implements Vector<Euclidean1D> {

    @Conditional
    public static boolean _mut84030 = false, _mut84031 = false, _mut84032 = false, _mut84033 = false, _mut84034 = false, _mut84035 = false, _mut84036 = false, _mut84037 = false, _mut84038 = false, _mut84039 = false, _mut84040 = false, _mut84041 = false, _mut84042 = false, _mut84043 = false, _mut84044 = false, _mut84045 = false, _mut84046 = false, _mut84047 = false, _mut84048 = false, _mut84049 = false, _mut84050 = false, _mut84051 = false, _mut84052 = false, _mut84053 = false, _mut84054 = false, _mut84055 = false, _mut84056 = false, _mut84057 = false, _mut84058 = false, _mut84059 = false, _mut84060 = false, _mut84061 = false, _mut84062 = false, _mut84063 = false, _mut84064 = false, _mut84065 = false, _mut84066 = false, _mut84067 = false, _mut84068 = false, _mut84069 = false, _mut84070 = false, _mut84071 = false, _mut84072 = false, _mut84073 = false, _mut84074 = false, _mut84075 = false, _mut84076 = false, _mut84077 = false, _mut84078 = false, _mut84079 = false, _mut84080 = false, _mut84081 = false, _mut84082 = false, _mut84083 = false, _mut84084 = false, _mut84085 = false, _mut84086 = false, _mut84087 = false, _mut84088 = false, _mut84089 = false, _mut84090 = false, _mut84091 = false, _mut84092 = false, _mut84093 = false, _mut84094 = false, _mut84095 = false, _mut84096 = false, _mut84097 = false, _mut84098 = false, _mut84099 = false, _mut84100 = false, _mut84101 = false, _mut84102 = false, _mut84103 = false, _mut84104 = false, _mut84105 = false, _mut84106 = false, _mut84107 = false, _mut84108 = false, _mut84109 = false, _mut84110 = false, _mut84111 = false, _mut84112 = false, _mut84113 = false, _mut84114 = false, _mut84115 = false, _mut84116 = false, _mut84117 = false, _mut84118 = false, _mut84119 = false, _mut84120 = false, _mut84121 = false, _mut84122 = false, _mut84123 = false, _mut84124 = false, _mut84125 = false, _mut84126 = false, _mut84127 = false, _mut84128 = false, _mut84129 = false, _mut84130 = false, _mut84131 = false, _mut84132 = false, _mut84133 = false, _mut84134 = false, _mut84135 = false, _mut84136 = false, _mut84137 = false, _mut84138 = false, _mut84139 = false, _mut84140 = false, _mut84141 = false, _mut84142 = false, _mut84143 = false, _mut84144 = false, _mut84145 = false, _mut84146 = false, _mut84147 = false, _mut84148 = false, _mut84149 = false, _mut84150 = false, _mut84151 = false, _mut84152 = false, _mut84153 = false, _mut84154 = false, _mut84155 = false, _mut84156 = false, _mut84157 = false, _mut84158 = false, _mut84159 = false, _mut84160 = false, _mut84161 = false, _mut84162 = false, _mut84163 = false, _mut84164 = false, _mut84165 = false, _mut84166 = false, _mut84167 = false, _mut84168 = false;

    /**
     * Origin (coordinates: 0).
     */
    public static final Vector1D ZERO = new Vector1D(0.0);

    /**
     * Unit (coordinates: 1).
     */
    public static final Vector1D ONE = new Vector1D(1.0);

    /**
     * A vector with all coordinates set to NaN.
     */
    public static final Vector1D NaN = new Vector1D(Double.NaN);

    /**
     * A vector with all coordinates set to positive infinity.
     */
    public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);

    /**
     * A vector with all coordinates set to negative infinity.
     */
    public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 7556674948671647925L;

    /**
     * Abscissa.
     */
    private final double x;

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param x abscissa
     * @see #getX()
     */
    public Vector1D(double x) {
        this.x = x;
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public Vector1D(double a, Vector1D u) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_75");
        this.x = AOR_multiply(a, u.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_75", _mut84030, _mut84031, _mut84032, _mut84033);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_87");
        this.x = AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_87", _mut84034, _mut84035, _mut84036, _mut84037), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_87", _mut84038, _mut84039, _mut84040, _mut84041), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_87", _mut84042, _mut84043, _mut84044, _mut84045);
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101");
        this.x = AOR_plus(AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101", _mut84046, _mut84047, _mut84048, _mut84049), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101", _mut84050, _mut84051, _mut84052, _mut84053), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101", _mut84054, _mut84055, _mut84056, _mut84057), AOR_multiply(a3, u3.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101", _mut84058, _mut84059, _mut84060, _mut84061), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_101", _mut84062, _mut84063, _mut84064, _mut84065);
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3, double a4, Vector1D u4) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118");
        this.x = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84066, _mut84067, _mut84068, _mut84069), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84070, _mut84071, _mut84072, _mut84073), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84074, _mut84075, _mut84076, _mut84077), AOR_multiply(a3, u3.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84078, _mut84079, _mut84080, _mut84081), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84082, _mut84083, _mut84084, _mut84085), AOR_multiply(a4, u4.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84086, _mut84087, _mut84088, _mut84089), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.Vector1D_118", _mut84090, _mut84091, _mut84092, _mut84093);
    }

    /**
     * Get the abscissa of the vector.
     * @return abscissa of the vector
     * @see #Vector1D(double)
     */
    public double getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Space getSpace() {
        return Euclidean1D.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D getZero() {
        return ZERO;
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm1() {
        return FastMath.abs(x);
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm() {
        return FastMath.abs(x);
    }

    /**
     * {@inheritDoc}
     */
    public double getNormSq() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.getNormSq_152");
        return AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.getNormSq_152", _mut84094, _mut84095, _mut84096, _mut84097);
    }

    /**
     * {@inheritDoc}
     */
    public double getNormInf() {
        return FastMath.abs(x);
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D add(Vector<Euclidean1D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.add_162");
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(AOR_plus(x, v1.getX(), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.add_162", _mut84098, _mut84099, _mut84100, _mut84101));
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D add(double factor, Vector<Euclidean1D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.add_168");
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(AOR_plus(x, AOR_multiply(factor, v1.getX(), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.add_168", _mut84102, _mut84103, _mut84104, _mut84105), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.add_168", _mut84106, _mut84107, _mut84108, _mut84109));
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D subtract(Vector<Euclidean1D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.subtract_174");
        Vector1D p3 = (Vector1D) p;
        return new Vector1D(AOR_minus(x, p3.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.subtract_174", _mut84110, _mut84111, _mut84112, _mut84113));
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D subtract(double factor, Vector<Euclidean1D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.subtract_180");
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(AOR_minus(x, AOR_multiply(factor, v1.getX(), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.subtract_180", _mut84114, _mut84115, _mut84116, _mut84117), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.subtract_180", _mut84118, _mut84119, _mut84120, _mut84121));
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D normalize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.normalize_186");
        double s = getNorm();
        if (ROR_equals(s, 0, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.normalize_186", _mut84122, _mut84123, _mut84124, _mut84125, _mut84126)) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
        }
        return scalarMultiply(AOR_divide(1, s, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.normalize_186", _mut84127, _mut84128, _mut84129, _mut84130));
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D negate() {
        return new Vector1D(-x);
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D scalarMultiply(double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.scalarMultiply_199");
        return new Vector1D(AOR_multiply(a, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.scalarMultiply_199", _mut84131, _mut84132, _mut84133, _mut84134));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNaN() {
        return Double.isNaN(x);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfinite() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.isInfinite_209");
        return (_mut84135 ? (!isNaN() || Double.isInfinite(x)) : (!isNaN() && Double.isInfinite(x)));
    }

    /**
     * {@inheritDoc}
     */
    public double distance1(Vector<Euclidean1D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distance1_214");
        Vector1D p3 = (Vector1D) p;
        final double dx = FastMath.abs(AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distance1_214", _mut84136, _mut84137, _mut84138, _mut84139));
        return dx;
    }

    /**
     * {@inheritDoc}
     * @deprecated as of 3.3, replaced with {@link #distance(Point)}
     */
    @Deprecated
    public double distance(Vector<Euclidean1D> p) {
        return distance((Point<Euclidean1D>) p);
    }

    /**
     * {@inheritDoc}
     */
    public double distance(Point<Euclidean1D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distance_229");
        Vector1D p3 = (Vector1D) p;
        final double dx = AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distance_229", _mut84140, _mut84141, _mut84142, _mut84143);
        return FastMath.abs(dx);
    }

    /**
     * {@inheritDoc}
     */
    public double distanceInf(Vector<Euclidean1D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distanceInf_236");
        Vector1D p3 = (Vector1D) p;
        final double dx = FastMath.abs(AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distanceInf_236", _mut84144, _mut84145, _mut84146, _mut84147));
        return dx;
    }

    /**
     * {@inheritDoc}
     */
    public double distanceSq(Vector<Euclidean1D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distanceSq_243");
        Vector1D p3 = (Vector1D) p;
        final double dx = AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distanceSq_243", _mut84148, _mut84149, _mut84150, _mut84151);
        return AOR_multiply(dx, dx, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.distanceSq_243", _mut84152, _mut84153, _mut84154, _mut84155);
    }

    /**
     * {@inheritDoc}
     */
    public double dotProduct(final Vector<Euclidean1D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.dotProduct_250");
        final Vector1D v1 = (Vector1D) v;
        return AOR_multiply(x, v1.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.dotProduct_250", _mut84156, _mut84157, _mut84158, _mut84159);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the distance between p1 and p2 according to the L<sub>2</sub> norm
     */
    public static double distance(Vector1D p1, Vector1D p2) {
        return p1.distance(p2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the distance between p1 and p2 according to the L<sub>&infin;</sub> norm
     */
    public static double distanceInf(Vector1D p1, Vector1D p2) {
        return p1.distanceInf(p2);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the square of the distance between p1 and p2
     */
    public static double distanceSq(Vector1D p1, Vector1D p2) {
        return p1.distanceSq(p2);
    }

    /**
     * Test for the equality of two 1D vectors.
     * <p>
     * If all coordinates of two 1D vectors are exactly the same, and none are
     * <code>Double.NaN</code>, the two 1D vectors are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) coordinates of the
     * 1D vector are equal to <code>Double.NaN</code>, the 1D vector is equal to
     * {@link #NaN}.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two 1D vector objects are equal, false if
     *         object is null, not an instance of Vector1D, or
     *         not equal to this Vector1D instance
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.equals_310");
        if (this == other) {
            return true;
        }
        if (other instanceof Vector1D) {
            final Vector1D rhs = (Vector1D) other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.equals_310", _mut84160, _mut84161, _mut84162, _mut84163, _mut84164);
        }
        return false;
    }

    /**
     * Get a hashCode for the 1D vector.
     * <p>
     * All NaN values have the same hash code.</p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Vector1D.hashCode_335");
        if (isNaN()) {
            return 7785;
        }
        return AOR_multiply(997, MathUtils.hash(x), "org.apache.commons.math3.geometry.euclidean.oned.Vector1D.hashCode_335", _mut84165, _mut84166, _mut84167, _mut84168);
    }

    /**
     * Get a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return Vector1DFormat.getInstance().format(this);
    }

    /**
     * {@inheritDoc}
     */
    public String toString(final NumberFormat format) {
        return new Vector1DFormat(format).format(this);
    }
}
