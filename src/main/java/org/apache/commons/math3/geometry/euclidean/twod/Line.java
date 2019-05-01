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
package org.apache.commons.math3.geometry.euclidean.twod;

import java.awt.geom.AffineTransform;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents an oriented line in the 2D plane.
 *
 * <p>An oriented line can be defined either by prolongating a line
 * segment between two points past these points, or by one point and
 * an angular direction (in trigonometric orientation).</p>
 *
 * <p>Since it is oriented the two half planes at its two sides are
 * unambiguously identified as a left half plane and a right half
 * plane. This can be used to identify the interior and the exterior
 * in a simple way by local properties only when part of a line is
 * used to define part of a polygon boundary.</p>
 *
 * <p>A line can also be used to completely define a reference frame
 * in the plane. It is sufficient to select one specific point in the
 * line (the orthogonal projection of the original reference frame on
 * the line) and to use the unit vector in the line direction and the
 * orthogonal vector oriented from left half plane to right half
 * plane. We define two coordinates by the process, the
 * <em>abscissa</em> along the line, and the <em>offset</em> across
 * the line. All points of the plane are uniquely identified by these
 * two coordinates. The line is the set of points at zero offset, the
 * left half plane is the set of points with negative offsets and the
 * right half plane is the set of points with positive offsets.</p>
 *
 * @since 3.0
 */
public class Line implements Hyperplane<Euclidean2D>, Embedding<Euclidean2D, Euclidean1D> {

    @Conditional
    public static boolean _mut84566 = false, _mut84567 = false, _mut84568 = false, _mut84569 = false, _mut84570 = false, _mut84571 = false, _mut84572 = false, _mut84573 = false, _mut84574 = false, _mut84575 = false, _mut84576 = false, _mut84577 = false, _mut84578 = false, _mut84579 = false, _mut84580 = false, _mut84581 = false, _mut84582 = false, _mut84583 = false, _mut84584 = false, _mut84585 = false, _mut84586 = false, _mut84587 = false, _mut84588 = false, _mut84589 = false, _mut84590 = false, _mut84591 = false, _mut84592 = false, _mut84593 = false, _mut84594 = false, _mut84595 = false, _mut84596 = false, _mut84597 = false, _mut84598 = false, _mut84599 = false, _mut84600 = false, _mut84601 = false, _mut84602 = false, _mut84603 = false, _mut84604 = false, _mut84605 = false, _mut84606 = false, _mut84607 = false, _mut84608 = false, _mut84609 = false, _mut84610 = false, _mut84611 = false, _mut84612 = false, _mut84613 = false, _mut84614 = false, _mut84615 = false, _mut84616 = false, _mut84617 = false, _mut84618 = false, _mut84619 = false, _mut84620 = false, _mut84621 = false, _mut84622 = false, _mut84623 = false, _mut84624 = false, _mut84625 = false, _mut84626 = false, _mut84627 = false, _mut84628 = false, _mut84629 = false, _mut84630 = false, _mut84631 = false, _mut84632 = false, _mut84633 = false, _mut84634 = false, _mut84635 = false, _mut84636 = false, _mut84637 = false, _mut84638 = false, _mut84639 = false, _mut84640 = false, _mut84641 = false, _mut84642 = false, _mut84643 = false, _mut84644 = false, _mut84645 = false, _mut84646 = false, _mut84647 = false, _mut84648 = false, _mut84649 = false, _mut84650 = false, _mut84651 = false, _mut84652 = false, _mut84653 = false, _mut84654 = false, _mut84655 = false, _mut84656 = false, _mut84657 = false, _mut84658 = false, _mut84659 = false, _mut84660 = false, _mut84661 = false, _mut84662 = false, _mut84663 = false, _mut84664 = false, _mut84665 = false, _mut84666 = false, _mut84667 = false, _mut84668 = false, _mut84669 = false, _mut84670 = false, _mut84671 = false, _mut84672 = false, _mut84673 = false, _mut84674 = false, _mut84675 = false, _mut84676 = false, _mut84677 = false, _mut84678 = false, _mut84679 = false, _mut84680 = false, _mut84681 = false, _mut84682 = false, _mut84683 = false, _mut84684 = false, _mut84685 = false, _mut84686 = false, _mut84687 = false, _mut84688 = false, _mut84689 = false, _mut84690 = false;

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Angle with respect to the abscissa axis.
     */
    private double angle;

    /**
     * Cosine of the line angle.
     */
    private double cos;

    /**
     * Sine of the line angle.
     */
    private double sin;

    /**
     * Offset of the frame origin.
     */
    private double originOffset;

    /**
     * Tolerance below which points are considered identical.
     */
    private final double tolerance;

    /**
     * Reverse line.
     */
    private Line reverse;

    /**
     * Build a line from two points.
     * <p>The line is oriented from p1 to p2</p>
     * @param p1 first point
     * @param p2 second point
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public Line(final Vector2D p1, final Vector2D p2, final double tolerance) {
        reset(p1, p2);
        this.tolerance = tolerance;
    }

    /**
     * Build a line from a point and an angle.
     * @param p point belonging to the line
     * @param angle angle of the line with respect to abscissa axis
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public Line(final Vector2D p, final double angle, final double tolerance) {
        reset(p, angle);
        this.tolerance = tolerance;
    }

    /**
     * Build a line from its internal characteristics.
     * @param angle angle of the line with respect to abscissa axis
     * @param cos cosine of the angle
     * @param sin sine of the angle
     * @param originOffset offset of the origin
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    private Line(final double angle, final double cos, final double sin, final double originOffset, final double tolerance) {
        this.angle = angle;
        this.cos = cos;
        this.sin = sin;
        this.originOffset = originOffset;
        this.tolerance = tolerance;
        this.reverse = null;
    }

    /**
     * Build a line from two points.
     * <p>The line is oriented from p1 to p2</p>
     * @param p1 first point
     * @param p2 second point
     * @deprecated as of 3.3, replaced with {@link #Line(Vector2D, Vector2D, double)}
     */
    @Deprecated
    public Line(final Vector2D p1, final Vector2D p2) {
        this(p1, p2, DEFAULT_TOLERANCE);
    }

    /**
     * Build a line from a point and an angle.
     * @param p point belonging to the line
     * @param angle angle of the line with respect to abscissa axis
     * @deprecated as of 3.3, replaced with {@link #Line(Vector2D, double, double)}
     */
    @Deprecated
    public Line(final Vector2D p, final double angle) {
        this(p, angle, DEFAULT_TOLERANCE);
    }

    /**
     * Copy constructor.
     * <p>The created instance is completely independent from the
     * original instance, it is a deep copy.</p>
     * @param line line to copy
     */
    public Line(final Line line) {
        angle = MathUtils.normalizeAngle(line.angle, FastMath.PI);
        cos = line.cos;
        sin = line.sin;
        originOffset = line.originOffset;
        tolerance = line.tolerance;
        reverse = null;
    }

    /**
     * {@inheritDoc}
     */
    public Line copySelf() {
        return new Line(this);
    }

    /**
     * Reset the instance as if built from two points.
     * <p>The line is oriented from p1 to p2</p>
     * @param p1 first point
     * @param p2 second point
     */
    public void reset(final Vector2D p1, final Vector2D p2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172");
        unlinkReverse();
        final double dx = AOR_minus(p2.getX(), p1.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84566, _mut84567, _mut84568, _mut84569);
        final double dy = AOR_minus(p2.getY(), p1.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84570, _mut84571, _mut84572, _mut84573);
        final double d = FastMath.hypot(dx, dy);
        if (ROR_equals(d, 0.0, "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84574, _mut84575, _mut84576, _mut84577, _mut84578)) {
            angle = 0.0;
            cos = 1.0;
            sin = 0.0;
            originOffset = p1.getY();
        } else {
            angle = AOR_plus(FastMath.PI, FastMath.atan2(-dy, -dx), "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84579, _mut84580, _mut84581, _mut84582);
            cos = AOR_divide(dx, d, "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84583, _mut84584, _mut84585, _mut84586);
            sin = AOR_divide(dy, d, "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84587, _mut84588, _mut84589, _mut84590);
            originOffset = AOR_divide(MathArrays.linearCombination(p2.getX(), p1.getY(), -p1.getX(), p2.getY()), d, "org.apache.commons.math3.geometry.euclidean.twod.Line.reset_172", _mut84591, _mut84592, _mut84593, _mut84594);
        }
    }

    /**
     * Reset the instance as if built from a line and an angle.
     * @param p point belonging to the line
     * @param alpha angle of the line with respect to abscissa axis
     */
    public void reset(final Vector2D p, final double alpha) {
        unlinkReverse();
        this.angle = MathUtils.normalizeAngle(alpha, FastMath.PI);
        cos = FastMath.cos(this.angle);
        sin = FastMath.sin(this.angle);
        originOffset = MathArrays.linearCombination(cos, p.getY(), -sin, p.getX());
    }

    /**
     * Revert the instance.
     */
    public void revertSelf() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.revertSelf_204");
        unlinkReverse();
        if (ROR_less(angle, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.twod.Line.revertSelf_204", _mut84595, _mut84596, _mut84597, _mut84598, _mut84599)) {
            angle += FastMath.PI;
        } else {
            angle -= FastMath.PI;
        }
        cos = -cos;
        sin = -sin;
        originOffset = -originOffset;
    }

    /**
     * Unset the link between an instance and its reverse.
     */
    private void unlinkReverse() {
        if (reverse != null) {
            reverse.reverse = null;
        }
        reverse = null;
    }

    /**
     * Get the reverse of the instance.
     * <p>Get a line with reversed orientation with respect to the
     * instance.</p>
     * <p>
     * As long as neither the instance nor its reverse are modified
     * (i.e. as long as none of the {@link #reset(Vector2D, Vector2D)},
     * {@link #reset(Vector2D, double)}, {@link #revertSelf()},
     * {@link #setAngle(double)} or {@link #setOriginOffset(double)}
     * methods are called), then the line and its reverse remain linked
     * together so that {@code line.getReverse().getReverse() == line}.
     * When one of the line is modified, the link is deleted as both
     * instance becomes independent.
     * </p>
     * @return a new line, with orientation opposite to the instance orientation
     */
    public Line getReverse() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.getReverse_240");
        if (reverse == null) {
            reverse = new Line((ROR_less(angle, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.twod.Line.getReverse_240", _mut84600, _mut84601, _mut84602, _mut84603, _mut84604)) ? (AOR_plus(angle, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.twod.Line.getReverse_240", _mut84609, _mut84610, _mut84611, _mut84612)) : (AOR_minus(angle, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.twod.Line.getReverse_240", _mut84605, _mut84606, _mut84607, _mut84608)), -cos, -sin, -originOffset, tolerance);
            reverse.reverse = this;
        }
        return reverse;
    }

    /**
     * Transform a space point into a sub-space point.
     * @param vector n-dimension point of the space
     * @return (n-1)-dimension point of the sub-space corresponding to
     * the specified space point
     */
    public Vector1D toSubSpace(Vector<Euclidean2D> vector) {
        return toSubSpace((Point<Euclidean2D>) vector);
    }

    /**
     * Transform a sub-space point into a space point.
     * @param vector (n-1)-dimension point of the sub-space
     * @return n-dimension point of the space corresponding to the
     * specified sub-space point
     */
    public Vector2D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point<Euclidean1D>) vector);
    }

    /**
     * {@inheritDoc}
     */
    public Vector1D toSubSpace(final Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
        return new Vector1D(MathArrays.linearCombination(cos, p2.getX(), sin, p2.getY()));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D toSpace(final Point<Euclidean1D> point) {
        final double abscissa = ((Vector1D) point).getX();
        return new Vector2D(MathArrays.linearCombination(abscissa, cos, -originOffset, sin), MathArrays.linearCombination(abscissa, sin, originOffset, cos));
    }

    /**
     * Get the intersection point of the instance and another line.
     * @param other other line
     * @return intersection point of the instance and the other line
     * or null if there are no intersection points
     */
    public Vector2D intersection(final Line other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.intersection_285");
        final double d = MathArrays.linearCombination(sin, other.cos, -other.sin, cos);
        if (ROR_less(FastMath.abs(d), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.Line.intersection_285", _mut84613, _mut84614, _mut84615, _mut84616, _mut84617)) {
            return null;
        }
        return new Vector2D(AOR_divide(MathArrays.linearCombination(cos, other.originOffset, -other.cos, originOffset), d, "org.apache.commons.math3.geometry.euclidean.twod.Line.intersection_285", _mut84618, _mut84619, _mut84620, _mut84621), AOR_divide(MathArrays.linearCombination(sin, other.originOffset, -other.sin, originOffset), d, "org.apache.commons.math3.geometry.euclidean.twod.Line.intersection_285", _mut84622, _mut84623, _mut84624, _mut84625));
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    public Point<Euclidean2D> project(Point<Euclidean2D> point) {
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
     * {@inheritDoc}
     */
    public SubLine wholeHyperplane() {
        return new SubLine(this, new IntervalsSet(tolerance));
    }

    /**
     * Build a region covering the whole space.
     * @return a region containing the instance (really a {@link
     * PolygonsSet PolygonsSet} instance)
     */
    public PolygonsSet wholeSpace() {
        return new PolygonsSet(tolerance);
    }

    /**
     * Get the offset (oriented distance) of a parallel line.
     * <p>This method should be called only for parallel lines otherwise
     * the result is not meaningful.</p>
     * <p>The offset is 0 if both lines are the same, it is
     * positive if the line is on the right side of the instance and
     * negative if it is on the left side, according to its natural
     * orientation.</p>
     * @param line line to check
     * @return offset of the line
     */
    public double getOffset(final Line line) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.getOffset_331");
        return AOR_plus(originOffset, (ROR_greater(MathArrays.linearCombination(cos, line.cos, sin, line.sin), 0, "org.apache.commons.math3.geometry.euclidean.twod.Line.getOffset_331", _mut84626, _mut84627, _mut84628, _mut84629, _mut84630) ? -line.originOffset : line.originOffset), "org.apache.commons.math3.geometry.euclidean.twod.Line.getOffset_331", _mut84631, _mut84632, _mut84633, _mut84634);
    }

    /**
     * Get the offset (oriented distance) of a vector.
     * @param vector vector to check
     * @return offset of the vector
     */
    public double getOffset(Vector<Euclidean2D> vector) {
        return getOffset((Point<Euclidean2D>) vector);
    }

    /**
     * {@inheritDoc}
     */
    public double getOffset(final Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
        return MathArrays.linearCombination(sin, p2.getX(), -cos, p2.getY(), 1.0, originOffset);
    }

    /**
     * {@inheritDoc}
     */
    public boolean sameOrientationAs(final Hyperplane<Euclidean2D> other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.sameOrientationAs_351");
        final Line otherL = (Line) other;
        return ROR_greater_equals(MathArrays.linearCombination(sin, otherL.sin, cos, otherL.cos), 0.0, "org.apache.commons.math3.geometry.euclidean.twod.Line.sameOrientationAs_351", _mut84635, _mut84636, _mut84637, _mut84638, _mut84639);
    }

    /**
     * Get one point from the plane.
     * @param abscissa desired abscissa for the point
     * @param offset desired offset for the point
     * @return one point in the plane, with given abscissa and offset
     * relative to the line
     */
    public Vector2D getPointAt(final Vector1D abscissa, final double offset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.getPointAt_362");
        final double x = abscissa.getX();
        final double dOffset = AOR_minus(offset, originOffset, "org.apache.commons.math3.geometry.euclidean.twod.Line.getPointAt_362", _mut84640, _mut84641, _mut84642, _mut84643);
        return new Vector2D(MathArrays.linearCombination(x, cos, dOffset, sin), MathArrays.linearCombination(x, sin, -dOffset, cos));
    }

    /**
     * Check if the line contains a point.
     * @param p point to check
     * @return true if p belongs to the line
     */
    public boolean contains(final Vector2D p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.contains_373");
        return ROR_less(FastMath.abs(getOffset(p)), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.Line.contains_373", _mut84644, _mut84645, _mut84646, _mut84647, _mut84648);
    }

    /**
     * Compute the distance between the instance and a point.
     * <p>This is a shortcut for invoking FastMath.abs(getOffset(p)),
     * and provides consistency with what is in the
     * org.apache.commons.math3.geometry.euclidean.threed.Line class.</p>
     *
     * @param p to check
     * @return distance between the instance and the point
     * @since 3.1
     */
    public double distance(final Vector2D p) {
        return FastMath.abs(getOffset(p));
    }

    /**
     * Check the instance is parallel to another line.
     * @param line other line to check
     * @return true if the instance is parallel to the other line
     * (they can have either the same or opposite orientations)
     */
    public boolean isParallelTo(final Line line) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.isParallelTo_395");
        return ROR_less(FastMath.abs(MathArrays.linearCombination(sin, line.cos, -cos, line.sin)), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.Line.isParallelTo_395", _mut84649, _mut84650, _mut84651, _mut84652, _mut84653);
    }

    /**
     * Translate the line to force it passing by a point.
     * @param p point by which the line should pass
     */
    public void translateToPoint(final Vector2D p) {
        originOffset = MathArrays.linearCombination(cos, p.getY(), -sin, p.getX());
    }

    /**
     * Get the angle of the line.
     * @return the angle of the line with respect to the abscissa axis
     */
    public double getAngle() {
        return MathUtils.normalizeAngle(angle, FastMath.PI);
    }

    /**
     * Set the angle of the line.
     * @param angle new angle of the line with respect to the abscissa axis
     */
    public void setAngle(final double angle) {
        unlinkReverse();
        this.angle = MathUtils.normalizeAngle(angle, FastMath.PI);
        cos = FastMath.cos(this.angle);
        sin = FastMath.sin(this.angle);
    }

    /**
     * Get the offset of the origin.
     * @return the offset of the origin
     */
    public double getOriginOffset() {
        return originOffset;
    }

    /**
     * Set the offset of the origin.
     * @param offset offset of the origin
     */
    public void setOriginOffset(final double offset) {
        unlinkReverse();
        originOffset = offset;
    }

    /**
     * Get a {@link org.apache.commons.math3.geometry.partitioning.Transform
     * Transform} embedding an affine transform.
     * @param transform affine transform to embed (must be inversible
     * otherwise the {@link
     * org.apache.commons.math3.geometry.partitioning.Transform#apply(Hyperplane)
     * apply(Hyperplane)} method would work only for some lines, and
     * fail for other ones)
     * @return a new transform that can be applied to either {@link
     * Vector2D Vector2D}, {@link Line Line} or {@link
     * org.apache.commons.math3.geometry.partitioning.SubHyperplane
     * SubHyperplane} instances
     * @exception MathIllegalArgumentException if the transform is non invertible
     * @deprecated as of 3.6, replaced with {@link #getTransform(double, double, double, double, double, double)}
     */
    @Deprecated
    public static Transform<Euclidean2D, Euclidean1D> getTransform(final AffineTransform transform) throws MathIllegalArgumentException {
        final double[] m = new double[6];
        transform.getMatrix(m);
        return new LineTransform(m[0], m[1], m[2], m[3], m[4], m[5]);
    }

    /**
     * Get a {@link org.apache.commons.math3.geometry.partitioning.Transform
     * Transform} embedding an affine transform.
     * @param cXX transform factor between input abscissa and output abscissa
     * @param cYX transform factor between input abscissa and output ordinate
     * @param cXY transform factor between input ordinate and output abscissa
     * @param cYY transform factor between input ordinate and output ordinate
     * @param cX1 transform addendum for output abscissa
     * @param cY1 transform addendum for output ordinate
     * @return a new transform that can be applied to either {@link
     * Vector2D Vector2D}, {@link Line Line} or {@link
     * org.apache.commons.math3.geometry.partitioning.SubHyperplane
     * SubHyperplane} instances
     * @exception MathIllegalArgumentException if the transform is non invertible
     * @since 3.6
     */
    public static Transform<Euclidean2D, Euclidean1D> getTransform(final double cXX, final double cYX, final double cXY, final double cYY, final double cX1, final double cY1) throws MathIllegalArgumentException {
        return new LineTransform(cXX, cYX, cXY, cYY, cX1, cY1);
    }

    /**
     * Class embedding an affine transform.
     * <p>This class is used in order to apply an affine transform to a
     * line. Using a specific object allow to perform some computations
     * on the transform only once even if the same transform is to be
     * applied to a large number of lines (for example to a large
     * polygon)./<p>
     */
    private static class LineTransform implements Transform<Euclidean2D, Euclidean1D> {

        /**
         * Transform factor between input abscissa and output abscissa.
         */
        private double cXX;

        /**
         * Transform factor between input abscissa and output ordinate.
         */
        private double cYX;

        /**
         * Transform factor between input ordinate and output abscissa.
         */
        private double cXY;

        /**
         * Transform factor between input ordinate and output ordinate.
         */
        private double cYY;

        /**
         * Transform addendum for output abscissa.
         */
        private double cX1;

        /**
         * Transform addendum for output ordinate.
         */
        private double cY1;

        /**
         * cXY * cY1 - cYY * cX1.
         */
        private double c1Y;

        /**
         * cXX * cY1 - cYX * cX1.
         */
        private double c1X;

        /**
         * cXX * cYY - cYX * cXY.
         */
        private double c11;

        /**
         * Build an affine line transform from a n {@code AffineTransform}.
         * @param cXX transform factor between input abscissa and output abscissa
         * @param cYX transform factor between input abscissa and output ordinate
         * @param cXY transform factor between input ordinate and output abscissa
         * @param cYY transform factor between input ordinate and output ordinate
         * @param cX1 transform addendum for output abscissa
         * @param cY1 transform addendum for output ordinate
         * @exception MathIllegalArgumentException if the transform is non invertible
         * @since 3.6
         */
        LineTransform(final double cXX, final double cYX, final double cXY, final double cYY, final double cX1, final double cY1) throws MathIllegalArgumentException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.LineTransform_531");
            this.cXX = cXX;
            this.cYX = cYX;
            this.cXY = cXY;
            this.cYY = cYY;
            this.cX1 = cX1;
            this.cY1 = cY1;
            c1Y = MathArrays.linearCombination(cXY, cY1, -cYY, cX1);
            c1X = MathArrays.linearCombination(cXX, cY1, -cYX, cX1);
            c11 = MathArrays.linearCombination(cXX, cYY, -cYX, cXY);
            if (ROR_less(FastMath.abs(c11), 1.0e-20, "org.apache.commons.math3.geometry.euclidean.twod.Line.LineTransform_531", _mut84654, _mut84655, _mut84656, _mut84657, _mut84658)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM);
            }
        }

        /**
         * {@inheritDoc}
         */
        public Vector2D apply(final Point<Euclidean2D> point) {
            final Vector2D p2D = (Vector2D) point;
            final double x = p2D.getX();
            final double y = p2D.getY();
            return new Vector2D(MathArrays.linearCombination(cXX, x, cXY, y, cX1, 1), MathArrays.linearCombination(cYX, x, cYY, y, cY1, 1));
        }

        /**
         * {@inheritDoc}
         */
        public Line apply(final Hyperplane<Euclidean2D> hyperplane) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562");
            final Line line = (Line) hyperplane;
            final double rOffset = MathArrays.linearCombination(c1X, line.cos, c1Y, line.sin, c11, line.originOffset);
            final double rCos = MathArrays.linearCombination(cXX, line.cos, cXY, line.sin);
            final double rSin = MathArrays.linearCombination(cYX, line.cos, cYY, line.sin);
            final double inv = AOR_divide(1.0, FastMath.sqrt(AOR_plus(AOR_multiply(rSin, rSin, "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84659, _mut84660, _mut84661, _mut84662), AOR_multiply(rCos, rCos, "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84663, _mut84664, _mut84665, _mut84666), "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84667, _mut84668, _mut84669, _mut84670)), "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84671, _mut84672, _mut84673, _mut84674);
            return new Line(AOR_plus(FastMath.PI, FastMath.atan2(-rSin, -rCos), "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84675, _mut84676, _mut84677, _mut84678), AOR_multiply(inv, rCos, "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84679, _mut84680, _mut84681, _mut84682), AOR_multiply(inv, rSin, "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84683, _mut84684, _mut84685, _mut84686), AOR_multiply(inv, rOffset, "org.apache.commons.math3.geometry.euclidean.twod.Line.apply_562", _mut84687, _mut84688, _mut84689, _mut84690), line.tolerance);
        }

        /**
         * {@inheritDoc}
         */
        public SubHyperplane<Euclidean1D> apply(final SubHyperplane<Euclidean1D> sub, final Hyperplane<Euclidean2D> original, final Hyperplane<Euclidean2D> transformed) {
            final OrientedPoint op = (OrientedPoint) sub.getHyperplane();
            final Line originalLine = (Line) original;
            final Line transformedLine = (Line) transformed;
            final Vector1D newLoc = transformedLine.toSubSpace(apply(originalLine.toSpace(op.getLocation())));
            return new OrientedPoint(newLoc, op.isDirect(), originalLine.tolerance).wholeHyperplane();
        }
    }
}
