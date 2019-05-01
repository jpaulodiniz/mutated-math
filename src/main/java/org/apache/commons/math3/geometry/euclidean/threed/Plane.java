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
package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * The class represent planes in a three dimensional space.
 * @since 3.0
 */
public class Plane implements Hyperplane<Euclidean3D>, Embedding<Euclidean3D, Euclidean2D> {

    @Conditional
    public static boolean _mut79328 = false, _mut79329 = false, _mut79330 = false, _mut79331 = false, _mut79332 = false, _mut79333 = false, _mut79334 = false, _mut79335 = false, _mut79336 = false, _mut79337 = false, _mut79338 = false, _mut79339 = false, _mut79340 = false, _mut79341 = false, _mut79342 = false, _mut79343 = false, _mut79344 = false, _mut79345 = false, _mut79346 = false, _mut79347 = false, _mut79348 = false, _mut79349 = false, _mut79350 = false, _mut79351 = false, _mut79352 = false, _mut79353 = false, _mut79354 = false, _mut79355 = false, _mut79356 = false, _mut79357 = false, _mut79358 = false, _mut79359 = false, _mut79360 = false, _mut79361 = false, _mut79362 = false, _mut79363 = false, _mut79364 = false, _mut79365 = false, _mut79366 = false, _mut79367 = false, _mut79368 = false, _mut79369 = false, _mut79370 = false, _mut79371 = false, _mut79372 = false, _mut79373 = false, _mut79374 = false, _mut79375 = false, _mut79376 = false, _mut79377 = false, _mut79378 = false, _mut79379 = false, _mut79380 = false, _mut79381 = false, _mut79382 = false, _mut79383 = false, _mut79384 = false, _mut79385 = false, _mut79386 = false, _mut79387 = false, _mut79388 = false, _mut79389 = false, _mut79390 = false, _mut79391 = false, _mut79392 = false, _mut79393 = false, _mut79394 = false, _mut79395 = false, _mut79396 = false, _mut79397 = false, _mut79398 = false, _mut79399 = false, _mut79400 = false, _mut79401 = false, _mut79402 = false, _mut79403 = false, _mut79404 = false, _mut79405 = false, _mut79406 = false, _mut79407 = false, _mut79408 = false, _mut79409 = false, _mut79410 = false, _mut79411 = false, _mut79412 = false, _mut79413 = false, _mut79414 = false, _mut79415 = false, _mut79416 = false, _mut79417 = false, _mut79418 = false, _mut79419 = false, _mut79420 = false, _mut79421 = false, _mut79422 = false, _mut79423 = false, _mut79424 = false, _mut79425 = false, _mut79426 = false, _mut79427 = false, _mut79428 = false, _mut79429 = false, _mut79430 = false, _mut79431 = false, _mut79432 = false, _mut79433 = false, _mut79434 = false, _mut79435 = false, _mut79436 = false, _mut79437 = false, _mut79438 = false, _mut79439 = false, _mut79440 = false, _mut79441 = false, _mut79442 = false, _mut79443 = false, _mut79444 = false, _mut79445 = false, _mut79446 = false, _mut79447 = false, _mut79448 = false, _mut79449 = false, _mut79450 = false, _mut79451 = false, _mut79452 = false, _mut79453 = false, _mut79454 = false, _mut79455 = false, _mut79456 = false, _mut79457 = false, _mut79458 = false, _mut79459 = false, _mut79460 = false, _mut79461 = false, _mut79462 = false, _mut79463 = false, _mut79464 = false, _mut79465 = false, _mut79466 = false, _mut79467 = false, _mut79468 = false, _mut79469 = false, _mut79470 = false, _mut79471 = false, _mut79472 = false, _mut79473 = false, _mut79474 = false, _mut79475 = false, _mut79476 = false, _mut79477 = false, _mut79478 = false, _mut79479 = false, _mut79480 = false, _mut79481 = false, _mut79482 = false, _mut79483 = false, _mut79484 = false, _mut79485 = false, _mut79486 = false, _mut79487 = false, _mut79488 = false, _mut79489 = false, _mut79490 = false, _mut79491 = false, _mut79492 = false, _mut79493 = false, _mut79494 = false, _mut79495 = false, _mut79496 = false, _mut79497 = false, _mut79498 = false, _mut79499 = false, _mut79500 = false, _mut79501 = false, _mut79502 = false, _mut79503 = false, _mut79504 = false, _mut79505 = false, _mut79506 = false, _mut79507 = false, _mut79508 = false, _mut79509 = false, _mut79510 = false, _mut79511 = false, _mut79512 = false, _mut79513 = false, _mut79514 = false, _mut79515 = false, _mut79516 = false, _mut79517 = false, _mut79518 = false, _mut79519 = false, _mut79520 = false, _mut79521 = false, _mut79522 = false, _mut79523 = false, _mut79524 = false, _mut79525 = false, _mut79526 = false, _mut79527 = false, _mut79528 = false, _mut79529 = false, _mut79530 = false, _mut79531 = false, _mut79532 = false, _mut79533 = false, _mut79534 = false, _mut79535 = false, _mut79536 = false, _mut79537 = false, _mut79538 = false, _mut79539 = false, _mut79540 = false, _mut79541 = false, _mut79542 = false, _mut79543 = false, _mut79544 = false, _mut79545 = false, _mut79546 = false, _mut79547 = false, _mut79548 = false, _mut79549 = false, _mut79550 = false, _mut79551 = false, _mut79552 = false, _mut79553 = false, _mut79554 = false, _mut79555 = false, _mut79556 = false, _mut79557 = false, _mut79558 = false, _mut79559 = false, _mut79560 = false, _mut79561 = false, _mut79562 = false, _mut79563 = false, _mut79564 = false, _mut79565 = false, _mut79566 = false, _mut79567 = false, _mut79568 = false, _mut79569 = false, _mut79570 = false, _mut79571 = false, _mut79572 = false, _mut79573 = false, _mut79574 = false, _mut79575 = false, _mut79576 = false, _mut79577 = false, _mut79578 = false, _mut79579 = false, _mut79580 = false, _mut79581 = false, _mut79582 = false, _mut79583 = false, _mut79584 = false, _mut79585 = false, _mut79586 = false, _mut79587 = false, _mut79588 = false, _mut79589 = false, _mut79590 = false, _mut79591 = false, _mut79592 = false, _mut79593 = false, _mut79594 = false, _mut79595 = false, _mut79596 = false, _mut79597 = false, _mut79598 = false, _mut79599 = false, _mut79600 = false, _mut79601 = false, _mut79602 = false, _mut79603 = false, _mut79604 = false, _mut79605 = false, _mut79606 = false, _mut79607 = false, _mut79608 = false, _mut79609 = false, _mut79610 = false, _mut79611 = false, _mut79612 = false, _mut79613 = false, _mut79614 = false, _mut79615 = false, _mut79616 = false, _mut79617 = false, _mut79618 = false, _mut79619 = false, _mut79620 = false;

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Offset of the origin with respect to the plane.
     */
    private double originOffset;

    /**
     * Origin of the plane frame.
     */
    private Vector3D origin;

    /**
     * First vector of the plane frame (in plane).
     */
    private Vector3D u;

    /**
     * Second vector of the plane frame (in plane).
     */
    private Vector3D v;

    /**
     * Third vector of the plane frame (plane normal).
     */
    private Vector3D w;

    /**
     * Tolerance below which points are considered identical.
     */
    private final double tolerance;

    /**
     * Build a plane normal to a given direction and containing the origin.
     * @param normal normal direction to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the normal norm is too small
     * @since 3.3
     */
    public Plane(final Vector3D normal, final double tolerance) throws MathArithmeticException {
        setNormal(normal);
        this.tolerance = tolerance;
        originOffset = 0;
        setFrame();
    }

    /**
     * Build a plane from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the normal norm is too small
     * @since 3.3
     */
    public Plane(final Vector3D p, final Vector3D normal, final double tolerance) throws MathArithmeticException {
        setNormal(normal);
        this.tolerance = tolerance;
        originOffset = -p.dotProduct(w);
        setFrame();
    }

    /**
     * Build a plane from three points.
     * <p>The plane is oriented in the direction of
     * {@code (p2-p1) ^ (p3-p1)}</p>
     * @param p1 first point belonging to the plane
     * @param p2 second point belonging to the plane
     * @param p3 third point belonging to the plane
     * @param tolerance tolerance below which points are considered identical
     * @exception MathArithmeticException if the points do not constitute a plane
     * @since 3.3
     */
    public Plane(final Vector3D p1, final Vector3D p2, final Vector3D p3, final double tolerance) throws MathArithmeticException {
        this(p1, p2.subtract(p1).crossProduct(p3.subtract(p1)), tolerance);
    }

    /**
     * Build a plane normal to a given direction and containing the origin.
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D normal) throws MathArithmeticException {
        this(normal, DEFAULT_TOLERANCE);
    }

    /**
     * Build a plane from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D p, final Vector3D normal) throws MathArithmeticException {
        this(p, normal, DEFAULT_TOLERANCE);
    }

    /**
     * Build a plane from three points.
     * <p>The plane is oriented in the direction of
     * {@code (p2-p1) ^ (p3-p1)}</p>
     * @param p1 first point belonging to the plane
     * @param p2 second point belonging to the plane
     * @param p3 third point belonging to the plane
     * @exception MathArithmeticException if the points do not constitute a plane
     * @deprecated as of 3.3, replaced with {@link #Plane(Vector3D, Vector3D, Vector3D, double)}
     */
    @Deprecated
    public Plane(final Vector3D p1, final Vector3D p2, final Vector3D p3) throws MathArithmeticException {
        this(p1, p2, p3, DEFAULT_TOLERANCE);
    }

    /**
     * Copy constructor.
     * <p>The instance created is completely independant of the original
     * one. A deep copy is used, none of the underlying object are
     * shared.</p>
     * @param plane plane to copy
     */
    public Plane(final Plane plane) {
        originOffset = plane.originOffset;
        origin = plane.origin;
        u = plane.u;
        v = plane.v;
        w = plane.w;
        tolerance = plane.tolerance;
    }

    /**
     * Copy the instance.
     * <p>The instance created is completely independant of the original
     * one. A deep copy is used, none of the underlying objects are
     * shared (except for immutable objects).</p>
     * @return a new hyperplane, copy of the instance
     */
    public Plane copySelf() {
        return new Plane(this);
    }

    /**
     * Reset the instance as if built from a point and a normal.
     * @param p point belonging to the plane
     * @param normal normal direction to the plane
     * @exception MathArithmeticException if the normal norm is too small
     */
    public void reset(final Vector3D p, final Vector3D normal) throws MathArithmeticException {
        setNormal(normal);
        originOffset = -p.dotProduct(w);
        setFrame();
    }

    /**
     * Reset the instance from another one.
     * <p>The updated instance is completely independant of the original
     * one. A deep reset is used none of the underlying object is
     * shared.</p>
     * @param original plane to reset from
     */
    public void reset(final Plane original) {
        originOffset = original.originOffset;
        origin = original.origin;
        u = original.u;
        v = original.v;
        w = original.w;
    }

    /**
     * Set the normal vactor.
     * @param normal normal direction to the plane (will be copied)
     * @exception MathArithmeticException if the normal norm is too small
     */
    private void setNormal(final Vector3D normal) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.setNormal_192");
        final double norm = normal.getNorm();
        if (ROR_less(norm, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.setNormal_192", _mut79328, _mut79329, _mut79330, _mut79331, _mut79332)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        w = new Vector3D(AOR_divide(1.0, norm, "org.apache.commons.math3.geometry.euclidean.threed.Plane.setNormal_192", _mut79333, _mut79334, _mut79335, _mut79336), normal);
    }

    /**
     * Reset the plane frame.
     */
    private void setFrame() {
        origin = new Vector3D(-originOffset, w);
        u = w.orthogonal();
        v = Vector3D.crossProduct(w, u);
    }

    /**
     * Get the origin point of the plane frame.
     * <p>The point returned is the orthogonal projection of the
     * 3D-space origin in the plane.</p>
     * @return the origin point of the plane frame (point closest to the
     * 3D-space origin)
     */
    public Vector3D getOrigin() {
        return origin;
    }

    /**
     * Get the normalized normal vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized normal vector
     * @see #getU
     * @see #getV
     */
    public Vector3D getNormal() {
        return w;
    }

    /**
     * Get the plane first canonical vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized first canonical vector
     * @see #getV
     * @see #getNormal
     */
    public Vector3D getU() {
        return u;
    }

    /**
     * Get the plane second canonical vector.
     * <p>The frame defined by ({@link #getU getU}, {@link #getV getV},
     * {@link #getNormal getNormal}) is a rigth-handed orthonormalized
     * frame).</p>
     * @return normalized second canonical vector
     * @see #getU
     * @see #getNormal
     */
    public Vector3D getV() {
        return v;
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    public Point<Euclidean3D> project(Point<Euclidean3D> point) {
        return toSpace(toSubSpace(point));
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * Revert the plane.
     * <p>Replace the instance by a similar plane with opposite orientation.</p>
     * <p>The new plane frame is chosen in such a way that a 3D point that had
     * {@code (x, y)} in-plane coordinates and {@code z} offset with
     * respect to the plane and is unaffected by the change will have
     * {@code (y, x)} in-plane coordinates and {@code -z} offset with
     * respect to the new plane. This means that the {@code u} and {@code v}
     * vectors returned by the {@link #getU} and {@link #getV} methods are exchanged,
     * and the {@code w} vector returned by the {@link #getNormal} method is
     * reversed.</p>
     */
    public void revertSelf() {
        final Vector3D tmp = u;
        u = v;
        v = tmp;
        w = w.negate();
        originOffset = -originOffset;
    }

    /**
     * Transform a space point into a sub-space point.
     * @param vector n-dimension point of the space
     * @return (n-1)-dimension point of the sub-space corresponding to
     * the specified space point
     */
    public Vector2D toSubSpace(Vector<Euclidean3D> vector) {
        return toSubSpace((Point<Euclidean3D>) vector);
    }

    /**
     * Transform a sub-space point into a space point.
     * @param vector (n-1)-dimension point of the sub-space
     * @return n-dimension point of the space corresponding to the
     * specified sub-space point
     */
    public Vector3D toSpace(Vector<Euclidean2D> vector) {
        return toSpace((Point<Euclidean2D>) vector);
    }

    /**
     * Transform a 3D space point into an in-plane point.
     * @param point point of the space (must be a {@link Vector3D
     * Vector3D} instance)
     * @return in-plane point (really a {@link
     * org.apache.commons.math3.geometry.euclidean.twod.Vector2D Vector2D} instance)
     * @see #toSpace
     */
    public Vector2D toSubSpace(final Point<Euclidean3D> point) {
        final Vector3D p3D = (Vector3D) point;
        return new Vector2D(p3D.dotProduct(u), p3D.dotProduct(v));
    }

    /**
     * Transform an in-plane point into a 3D space point.
     * @param point in-plane point (must be a {@link
     * org.apache.commons.math3.geometry.euclidean.twod.Vector2D Vector2D} instance)
     * @return 3D space point (really a {@link Vector3D Vector3D} instance)
     * @see #toSubSpace
     */
    public Vector3D toSpace(final Point<Euclidean2D> point) {
        final Vector2D p2D = (Vector2D) point;
        return new Vector3D(p2D.getX(), u, p2D.getY(), v, -originOffset, w);
    }

    /**
     * Get one point from the 3D-space.
     * @param inPlane desired in-plane coordinates for the point in the
     * plane
     * @param offset desired offset for the point
     * @return one point in the 3D-space, with given coordinates and offset
     * relative to the plane
     */
    public Vector3D getPointAt(final Vector2D inPlane, final double offset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.getPointAt_335");
        return new Vector3D(inPlane.getX(), u, inPlane.getY(), v, AOR_minus(offset, originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.getPointAt_335", _mut79337, _mut79338, _mut79339, _mut79340), w);
    }

    /**
     * Check if the instance is similar to another plane.
     * <p>Planes are considered similar if they contain the same
     * points. This does not mean they are equal since they can have
     * opposite normals.</p>
     * @param plane plane to which the instance is compared
     * @return true if the planes are similar
     */
    public boolean isSimilarTo(final Plane plane) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346");
        final double angle = Vector3D.angle(w, plane.w);
        return (_mut79375 ? (((_mut79355 ? ((ROR_less(angle, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79341, _mut79342, _mut79343, _mut79344, _mut79345)) || (ROR_less(FastMath.abs(AOR_minus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79346, _mut79347, _mut79348, _mut79349)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79350, _mut79351, _mut79352, _mut79353, _mut79354))) : ((ROR_less(angle, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79341, _mut79342, _mut79343, _mut79344, _mut79345)) && (ROR_less(FastMath.abs(AOR_minus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79346, _mut79347, _mut79348, _mut79349)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79350, _mut79351, _mut79352, _mut79353, _mut79354))))) && ((_mut79374 ? ((ROR_greater(angle, (AOR_minus(FastMath.PI, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79356, _mut79357, _mut79358, _mut79359)), "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79360, _mut79361, _mut79362, _mut79363, _mut79364)) || (ROR_less(FastMath.abs(AOR_plus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79365, _mut79366, _mut79367, _mut79368)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79369, _mut79370, _mut79371, _mut79372, _mut79373))) : ((ROR_greater(angle, (AOR_minus(FastMath.PI, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79356, _mut79357, _mut79358, _mut79359)), "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79360, _mut79361, _mut79362, _mut79363, _mut79364)) && (ROR_less(FastMath.abs(AOR_plus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79365, _mut79366, _mut79367, _mut79368)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79369, _mut79370, _mut79371, _mut79372, _mut79373)))))) : (((_mut79355 ? ((ROR_less(angle, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79341, _mut79342, _mut79343, _mut79344, _mut79345)) || (ROR_less(FastMath.abs(AOR_minus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79346, _mut79347, _mut79348, _mut79349)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79350, _mut79351, _mut79352, _mut79353, _mut79354))) : ((ROR_less(angle, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79341, _mut79342, _mut79343, _mut79344, _mut79345)) && (ROR_less(FastMath.abs(AOR_minus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79346, _mut79347, _mut79348, _mut79349)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79350, _mut79351, _mut79352, _mut79353, _mut79354))))) || ((_mut79374 ? ((ROR_greater(angle, (AOR_minus(FastMath.PI, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79356, _mut79357, _mut79358, _mut79359)), "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79360, _mut79361, _mut79362, _mut79363, _mut79364)) || (ROR_less(FastMath.abs(AOR_plus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79365, _mut79366, _mut79367, _mut79368)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79369, _mut79370, _mut79371, _mut79372, _mut79373))) : ((ROR_greater(angle, (AOR_minus(FastMath.PI, 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79356, _mut79357, _mut79358, _mut79359)), "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79360, _mut79361, _mut79362, _mut79363, _mut79364)) && (ROR_less(FastMath.abs(AOR_plus(originOffset, plane.originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79365, _mut79366, _mut79367, _mut79368)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.isSimilarTo_346", _mut79369, _mut79370, _mut79371, _mut79372, _mut79373)))))));
    }

    /**
     * Rotate the plane around the specified point.
     * <p>The instance is not modified, a new instance is created.</p>
     * @param center rotation center
     * @param rotation vectorial rotation operator
     * @return a new plane
     */
    public Plane rotate(final Vector3D center, final Rotation rotation) {
        final Vector3D delta = origin.subtract(center);
        final Plane plane = new Plane(center.add(rotation.applyTo(delta)), rotation.applyTo(w), tolerance);
        // make sure the frame is transformed as desired
        plane.u = rotation.applyTo(u);
        plane.v = rotation.applyTo(v);
        return plane;
    }

    /**
     * Translate the plane by the specified amount.
     * <p>The instance is not modified, a new instance is created.</p>
     * @param translation translation to apply
     * @return a new plane
     */
    public Plane translate(final Vector3D translation) {
        final Plane plane = new Plane(origin.add(translation), w, tolerance);
        // make sure the frame is transformed as desired
        plane.u = u;
        plane.v = v;
        return plane;
    }

    /**
     * Get the intersection of a line with the instance.
     * @param line line intersecting the instance
     * @return intersection point between between the line and the
     * instance (null if the line is parallel to the instance)
     */
    public Vector3D intersection(final Line line) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_394");
        final Vector3D direction = line.getDirection();
        final double dot = w.dotProduct(direction);
        if (ROR_less(FastMath.abs(dot), 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_394", _mut79376, _mut79377, _mut79378, _mut79379, _mut79380)) {
            return null;
        }
        final Vector3D point = line.toSpace((Point<Euclidean1D>) Vector1D.ZERO);
        final double k = AOR_divide(-(AOR_plus(originOffset, w.dotProduct(point), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_394", _mut79381, _mut79382, _mut79383, _mut79384)), dot, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_394", _mut79385, _mut79386, _mut79387, _mut79388);
        return new Vector3D(1.0, point, k, direction);
    }

    /**
     * Build the line shared by the instance and another plane.
     * @param other other plane
     * @return line at the intersection of the instance and the
     * other plane (really a {@link Line Line} instance)
     */
    public Line intersection(final Plane other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_410");
        final Vector3D direction = Vector3D.crossProduct(w, other.w);
        if (ROR_less(direction.getNorm(), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_410", _mut79389, _mut79390, _mut79391, _mut79392, _mut79393)) {
            return null;
        }
        final Vector3D point = intersection(this, other, new Plane(direction, tolerance));
        return new Line(point, point.add(direction), tolerance);
    }

    /**
     * Get the intersection point of three planes.
     * @param plane1 first plane1
     * @param plane2 second plane2
     * @param plane3 third plane2
     * @return intersection point of three planes, null if some planes are parallel
     */
    public static Vector3D intersection(final Plane plane1, final Plane plane2, final Plane plane3) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425");
        // coefficients of the three planes linear equations
        final double a1 = plane1.w.getX();
        final double b1 = plane1.w.getY();
        final double c1 = plane1.w.getZ();
        final double d1 = plane1.originOffset;
        final double a2 = plane2.w.getX();
        final double b2 = plane2.w.getY();
        final double c2 = plane2.w.getZ();
        final double d2 = plane2.originOffset;
        final double a3 = plane3.w.getX();
        final double b3 = plane3.w.getY();
        final double c3 = plane3.w.getZ();
        final double d3 = plane3.originOffset;
        // (this is still feasible for a 3x3 system)
        final double a23 = AOR_minus(AOR_multiply(b2, c3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79394, _mut79395, _mut79396, _mut79397), AOR_multiply(b3, c2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79398, _mut79399, _mut79400, _mut79401), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79402, _mut79403, _mut79404, _mut79405);
        final double b23 = AOR_minus(AOR_multiply(c2, a3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79406, _mut79407, _mut79408, _mut79409), AOR_multiply(c3, a2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79410, _mut79411, _mut79412, _mut79413), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79414, _mut79415, _mut79416, _mut79417);
        final double c23 = AOR_minus(AOR_multiply(a2, b3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79418, _mut79419, _mut79420, _mut79421), AOR_multiply(a3, b2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79422, _mut79423, _mut79424, _mut79425), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79426, _mut79427, _mut79428, _mut79429);
        final double determinant = AOR_plus(AOR_plus(AOR_multiply(a1, a23, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79430, _mut79431, _mut79432, _mut79433), AOR_multiply(b1, b23, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79434, _mut79435, _mut79436, _mut79437), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79438, _mut79439, _mut79440, _mut79441), AOR_multiply(c1, c23, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79442, _mut79443, _mut79444, _mut79445), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79446, _mut79447, _mut79448, _mut79449);
        if (ROR_less(FastMath.abs(determinant), 1.0e-10, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79450, _mut79451, _mut79452, _mut79453, _mut79454)) {
            return null;
        }
        final double r = AOR_divide(1.0, determinant, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79455, _mut79456, _mut79457, _mut79458);
        return new Vector3D(AOR_multiply((AOR_minus(AOR_minus(AOR_multiply(-a23, d1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79459, _mut79460, _mut79461, _mut79462), AOR_multiply((AOR_minus(AOR_multiply(c1, b3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79463, _mut79464, _mut79465, _mut79466), AOR_multiply(c3, b1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79467, _mut79468, _mut79469, _mut79470), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79471, _mut79472, _mut79473, _mut79474)), d2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79475, _mut79476, _mut79477, _mut79478), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79479, _mut79480, _mut79481, _mut79482), AOR_multiply((AOR_minus(AOR_multiply(c2, b1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79483, _mut79484, _mut79485, _mut79486), AOR_multiply(c1, b2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79487, _mut79488, _mut79489, _mut79490), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79491, _mut79492, _mut79493, _mut79494)), d3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79495, _mut79496, _mut79497, _mut79498), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79499, _mut79500, _mut79501, _mut79502)), r, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79503, _mut79504, _mut79505, _mut79506), AOR_multiply((AOR_minus(AOR_minus(AOR_multiply(-b23, d1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79507, _mut79508, _mut79509, _mut79510), AOR_multiply((AOR_minus(AOR_multiply(c3, a1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79511, _mut79512, _mut79513, _mut79514), AOR_multiply(c1, a3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79515, _mut79516, _mut79517, _mut79518), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79519, _mut79520, _mut79521, _mut79522)), d2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79523, _mut79524, _mut79525, _mut79526), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79527, _mut79528, _mut79529, _mut79530), AOR_multiply((AOR_minus(AOR_multiply(c1, a2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79531, _mut79532, _mut79533, _mut79534), AOR_multiply(c2, a1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79535, _mut79536, _mut79537, _mut79538), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79539, _mut79540, _mut79541, _mut79542)), d3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79543, _mut79544, _mut79545, _mut79546), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79547, _mut79548, _mut79549, _mut79550)), r, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79551, _mut79552, _mut79553, _mut79554), AOR_multiply((AOR_minus(AOR_minus(AOR_multiply(-c23, d1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79555, _mut79556, _mut79557, _mut79558), AOR_multiply((AOR_minus(AOR_multiply(b1, a3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79559, _mut79560, _mut79561, _mut79562), AOR_multiply(b3, a1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79563, _mut79564, _mut79565, _mut79566), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79567, _mut79568, _mut79569, _mut79570)), d2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79571, _mut79572, _mut79573, _mut79574), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79575, _mut79576, _mut79577, _mut79578), AOR_multiply((AOR_minus(AOR_multiply(b2, a1, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79579, _mut79580, _mut79581, _mut79582), AOR_multiply(b1, a2, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79583, _mut79584, _mut79585, _mut79586), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79587, _mut79588, _mut79589, _mut79590)), d3, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79591, _mut79592, _mut79593, _mut79594), "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79595, _mut79596, _mut79597, _mut79598)), r, "org.apache.commons.math3.geometry.euclidean.threed.Plane.intersection_425", _mut79599, _mut79600, _mut79601, _mut79602));
    }

    /**
     * Build a region covering the whole hyperplane.
     * @return a region covering the whole hyperplane
     */
    public SubPlane wholeHyperplane() {
        return new SubPlane(this, new PolygonsSet(tolerance));
    }

    /**
     * Build a region covering the whole space.
     * @return a region containing the instance (really a {@link
     * PolyhedronsSet PolyhedronsSet} instance)
     */
    public PolyhedronsSet wholeSpace() {
        return new PolyhedronsSet(tolerance);
    }

    /**
     * Check if the instance contains a point.
     * @param p point to check
     * @return true if p belongs to the plane
     */
    public boolean contains(final Vector3D p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.contains_480");
        return ROR_less(FastMath.abs(getOffset(p)), tolerance, "org.apache.commons.math3.geometry.euclidean.threed.Plane.contains_480", _mut79603, _mut79604, _mut79605, _mut79606, _mut79607);
    }

    /**
     * Get the offset (oriented distance) of a parallel plane.
     * <p>This method should be called only for parallel planes otherwise
     * the result is not meaningful.</p>
     * <p>The offset is 0 if both planes are the same, it is
     * positive if the plane is on the plus side of the instance and
     * negative if it is on the minus side, according to its natural
     * orientation.</p>
     * @param plane plane to check
     * @return offset of the plane
     */
    public double getOffset(final Plane plane) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.getOffset_494");
        return AOR_plus(originOffset, (sameOrientationAs(plane) ? -plane.originOffset : plane.originOffset), "org.apache.commons.math3.geometry.euclidean.threed.Plane.getOffset_494", _mut79608, _mut79609, _mut79610, _mut79611);
    }

    /**
     * Get the offset (oriented distance) of a vector.
     * @param vector vector to check
     * @return offset of the vector
     */
    public double getOffset(Vector<Euclidean3D> vector) {
        return getOffset((Point<Euclidean3D>) vector);
    }

    /**
     * Get the offset (oriented distance) of a point.
     * <p>The offset is 0 if the point is on the underlying hyperplane,
     * it is positive if the point is on one particular side of the
     * hyperplane, and it is negative if the point is on the other side,
     * according to the hyperplane natural orientation.</p>
     * @param point point to check
     * @return offset of the point
     */
    public double getOffset(final Point<Euclidean3D> point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.getOffset_514");
        return AOR_plus(((Vector3D) point).dotProduct(w), originOffset, "org.apache.commons.math3.geometry.euclidean.threed.Plane.getOffset_514", _mut79612, _mut79613, _mut79614, _mut79615);
    }

    /**
     * Check if the instance has the same orientation as another hyperplane.
     * @param other other hyperplane to check against the instance
     * @return true if the instance and the other hyperplane have
     * the same orientation
     */
    public boolean sameOrientationAs(final Hyperplane<Euclidean3D> other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Plane.sameOrientationAs_523");
        return ROR_greater((((Plane) other).w).dotProduct(w), 0.0, "org.apache.commons.math3.geometry.euclidean.threed.Plane.sameOrientationAs_523", _mut79616, _mut79617, _mut79618, _mut79619, _mut79620);
    }
}
