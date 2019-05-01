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
package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a point on the 2-sphere.
 * <p>
 * We use the mathematical convention to use the azimuthal angle \( \theta \)
 * in the x-y plane as the first coordinate, and the polar angle \( \varphi \)
 * as the second coordinate (see <a
 * href="http://mathworld.wolfram.com/SphericalCoordinates.html">Spherical
 * Coordinates</a> in MathWorld).
 * </p>
 * <p>Instances of this class are guaranteed to be immutable.</p>
 * @since 3.3
 */
public class S2Point implements Point<Sphere2D> {

    @Conditional
    public static boolean _mut85871 = false, _mut85872 = false, _mut85873 = false, _mut85874 = false, _mut85875 = false, _mut85876 = false, _mut85877 = false, _mut85878 = false, _mut85879 = false, _mut85880 = false, _mut85881 = false, _mut85882 = false, _mut85883 = false, _mut85884 = false, _mut85885 = false, _mut85886 = false, _mut85887 = false, _mut85888 = false, _mut85889 = false, _mut85890 = false, _mut85891 = false, _mut85892 = false, _mut85893 = false, _mut85894 = false, _mut85895 = false, _mut85896 = false, _mut85897 = false, _mut85898 = false, _mut85899 = false, _mut85900 = false, _mut85901 = false, _mut85902 = false, _mut85903 = false, _mut85904 = false, _mut85905 = false, _mut85906 = false, _mut85907 = false, _mut85908 = false, _mut85909 = false, _mut85910 = false, _mut85911 = false, _mut85912 = false, _mut85913 = false, _mut85914 = false, _mut85915 = false, _mut85916 = false, _mut85917 = false, _mut85918 = false, _mut85919 = false, _mut85920 = false, _mut85921 = false, _mut85922 = false, _mut85923 = false, _mut85924 = false, _mut85925 = false, _mut85926 = false, _mut85927 = false, _mut85928 = false, _mut85929 = false, _mut85930 = false, _mut85931 = false, _mut85932 = false, _mut85933 = false, _mut85934 = false, _mut85935 = false, _mut85936 = false, _mut85937 = false, _mut85938 = false, _mut85939 = false, _mut85940 = false, _mut85941 = false;

    /**
     * +I (coordinates: \( \theta = 0, \varphi = \pi/2 \)).
     */
    public static final S2Point PLUS_I = new S2Point(0, AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85871, _mut85872, _mut85873, _mut85874), Vector3D.PLUS_I);

    /**
     * +J (coordinates: \( \theta = \pi/2, \varphi = \pi/2 \))).
     */
    public static final S2Point PLUS_J = new S2Point(AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85875, _mut85876, _mut85877, _mut85878), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85879, _mut85880, _mut85881, _mut85882), Vector3D.PLUS_J);

    /**
     * +K (coordinates: \( \theta = any angle, \varphi = 0 \)).
     */
    public static final S2Point PLUS_K = new S2Point(0, 0, Vector3D.PLUS_K);

    /**
     * -I (coordinates: \( \theta = \pi, \varphi = \pi/2 \)).
     */
    public static final S2Point MINUS_I = new S2Point(FastMath.PI, AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85883, _mut85884, _mut85885, _mut85886), Vector3D.MINUS_I);

    /**
     * -J (coordinates: \( \theta = 3\pi/2, \varphi = \pi/2 \)).
     */
    public static final S2Point MINUS_J = new S2Point(AOR_multiply(1.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85887, _mut85888, _mut85889, _mut85890), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.apply_317", _mut85891, _mut85892, _mut85893, _mut85894), Vector3D.MINUS_J);

    /**
     * -K (coordinates: \( \theta = any angle, \varphi = \pi \)).
     */
    public static final S2Point MINUS_K = new S2Point(0, FastMath.PI, Vector3D.MINUS_K);

    /**
     * A vector with all coordinates set to NaN.
     */
    public static final S2Point NaN = new S2Point(Double.NaN, Double.NaN, Vector3D.NaN);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20131218L;

    /**
     * Azimuthal angle \( \theta \) in the x-y plane.
     */
    private final double theta;

    /**
     * Polar angle \( \varphi \).
     */
    private final double phi;

    /**
     * Corresponding 3D normalized vector.
     */
    private final Vector3D vector;

    /**
     * Simple constructor.
     * Build a vector from its spherical coordinates
     * @param theta azimuthal angle \( \theta \) in the x-y plane
     * @param phi polar angle \( \varphi \)
     * @see #getTheta()
     * @see #getPhi()
     * @exception OutOfRangeException if \( \varphi \) is not in the [\( 0; \pi \)] range
     */
    public S2Point(final double theta, final double phi) throws OutOfRangeException {
        this(theta, phi, vector(theta, phi));
    }

    /**
     * Simple constructor.
     * Build a vector from its underlying 3D vector
     * @param vector 3D vector
     * @exception MathArithmeticException if vector norm is zero
     */
    public S2Point(final Vector3D vector) throws MathArithmeticException {
        this(FastMath.atan2(vector.getY(), vector.getX()), Vector3D.angle(Vector3D.PLUS_K, vector), vector.normalize());
    }

    /**
     * Build a point from its internal components.
     * @param theta azimuthal angle \( \theta \) in the x-y plane
     * @param phi polar angle \( \varphi \)
     * @param vector corresponding vector
     */
    private S2Point(final double theta, final double phi, final Vector3D vector) {
        this.theta = theta;
        this.phi = phi;
        this.vector = vector;
    }

    /**
     * Build the normalized vector corresponding to spherical coordinates.
     * @param theta azimuthal angle \( \theta \) in the x-y plane
     * @param phi polar angle \( \varphi \)
     * @return normalized vector
     * @exception OutOfRangeException if \( \varphi \) is not in the [\( 0; \pi \)] range
     */
    private static Vector3D vector(final double theta, final double phi) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115");
        if ((_mut85905 ? (ROR_less(phi, 0, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85895, _mut85896, _mut85897, _mut85898, _mut85899) && ROR_greater(phi, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85900, _mut85901, _mut85902, _mut85903, _mut85904)) : (ROR_less(phi, 0, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85895, _mut85896, _mut85897, _mut85898, _mut85899) || ROR_greater(phi, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85900, _mut85901, _mut85902, _mut85903, _mut85904)))) {
            throw new OutOfRangeException(phi, 0, FastMath.PI);
        }
        final double cosTheta = FastMath.cos(theta);
        final double sinTheta = FastMath.sin(theta);
        final double cosPhi = FastMath.cos(phi);
        final double sinPhi = FastMath.sin(phi);
        return new Vector3D(AOR_multiply(cosTheta, sinPhi, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85906, _mut85907, _mut85908, _mut85909), AOR_multiply(sinTheta, sinPhi, "org.apache.commons.math3.geometry.spherical.twod.S2Point.vector_115", _mut85910, _mut85911, _mut85912, _mut85913), cosPhi);
    }

    /**
     * Get the azimuthal angle \( \theta \) in the x-y plane.
     * @return azimuthal angle \( \theta \) in the x-y plane
     * @see #S2Point(double, double)
     */
    public double getTheta() {
        return theta;
    }

    /**
     * Get the polar angle \( \varphi \).
     * @return polar angle \( \varphi \)
     * @see #S2Point(double, double)
     */
    public double getPhi() {
        return phi;
    }

    /**
     * Get the corresponding normalized vector in the 3D euclidean space.
     * @return normalized vector
     */
    public Vector3D getVector() {
        return vector;
    }

    /**
     * {@inheritDoc}
     */
    public Space getSpace() {
        return Sphere2D.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNaN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.S2Point.isNaN_160");
        return (_mut85914 ? (Double.isNaN(theta) && Double.isNaN(phi)) : (Double.isNaN(theta) || Double.isNaN(phi)));
    }

    /**
     * Get the opposite of the instance.
     * @return a new vector which is opposite to the instance
     */
    public S2Point negate() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.S2Point.negate_167");
        return new S2Point(-theta, AOR_minus(FastMath.PI, phi, "org.apache.commons.math3.geometry.spherical.twod.S2Point.negate_167", _mut85915, _mut85916, _mut85917, _mut85918), vector.negate());
    }

    /**
     * {@inheritDoc}
     */
    public double distance(final Point<Sphere2D> point) {
        return distance(this, (S2Point) point);
    }

    /**
     * Compute the distance (angular separation) between two points.
     * @param p1 first vector
     * @param p2 second vector
     * @return the angular separation between p1 and p2
     */
    public static double distance(S2Point p1, S2Point p2) {
        return Vector3D.angle(p1.vector, p2.vector);
    }

    /**
     * Test for the equality of two points on the 2-sphere.
     * <p>
     * If all coordinates of two points are exactly the same, and none are
     * <code>Double.NaN</code>, the two points are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) coordinates of the
     * 2D vector are equal to <code>Double.NaN</code>, the 2D vector is equal to
     * {@link #NaN}.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two points on the 2-sphere objects are equal, false if
     *         object is null, not an instance of S2Point, or
     *         not equal to this S2Point instance
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.S2Point.equals_204");
        if (this == other) {
            return true;
        }
        if (other instanceof S2Point) {
            final S2Point rhs = (S2Point) other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return (_mut85929 ? ((ROR_equals(theta, rhs.theta, "org.apache.commons.math3.geometry.spherical.twod.S2Point.equals_204", _mut85919, _mut85920, _mut85921, _mut85922, _mut85923)) || (ROR_equals(phi, rhs.phi, "org.apache.commons.math3.geometry.spherical.twod.S2Point.equals_204", _mut85924, _mut85925, _mut85926, _mut85927, _mut85928))) : ((ROR_equals(theta, rhs.theta, "org.apache.commons.math3.geometry.spherical.twod.S2Point.equals_204", _mut85919, _mut85920, _mut85921, _mut85922, _mut85923)) && (ROR_equals(phi, rhs.phi, "org.apache.commons.math3.geometry.spherical.twod.S2Point.equals_204", _mut85924, _mut85925, _mut85926, _mut85927, _mut85928))));
        }
        return false;
    }

    /**
     * Get a hashCode for the 2D vector.
     * <p>
     * All NaN values have the same hash code.</p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.S2Point.hashCode_229");
        if (isNaN()) {
            return 542;
        }
        return AOR_multiply(134, (AOR_plus(AOR_multiply(37, MathUtils.hash(theta), "org.apache.commons.math3.geometry.spherical.twod.S2Point.hashCode_229", _mut85930, _mut85931, _mut85932, _mut85933), MathUtils.hash(phi), "org.apache.commons.math3.geometry.spherical.twod.S2Point.hashCode_229", _mut85934, _mut85935, _mut85936, _mut85937)), "org.apache.commons.math3.geometry.spherical.twod.S2Point.hashCode_229", _mut85938, _mut85939, _mut85940, _mut85941);
    }
}
