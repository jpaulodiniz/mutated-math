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
package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.io.Serializable;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.hull.ConvexHull;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a convex hull in an two-dimensional euclidean space.
 *
 * @since 3.3
 */
public class ConvexHull2D implements ConvexHull<Euclidean2D, Vector2D>, Serializable {

    @Conditional
    public static boolean _mut84786 = false, _mut84787 = false, _mut84788 = false, _mut84789 = false, _mut84790 = false, _mut84791 = false, _mut84792 = false, _mut84793 = false, _mut84794 = false, _mut84795 = false, _mut84796 = false, _mut84797 = false, _mut84798 = false, _mut84799 = false, _mut84800 = false, _mut84801 = false, _mut84802 = false, _mut84803 = false, _mut84804 = false, _mut84805 = false, _mut84806 = false, _mut84807 = false, _mut84808 = false, _mut84809 = false, _mut84810 = false, _mut84811 = false, _mut84812 = false, _mut84813 = false, _mut84814 = false, _mut84815 = false, _mut84816 = false, _mut84817 = false, _mut84818 = false, _mut84819 = false, _mut84820 = false, _mut84821 = false, _mut84822 = false, _mut84823 = false, _mut84824 = false, _mut84825 = false, _mut84826 = false, _mut84827 = false, _mut84828 = false, _mut84829 = false, _mut84830 = false, _mut84831 = false, _mut84832 = false, _mut84833 = false, _mut84834 = false, _mut84835 = false, _mut84836 = false, _mut84837 = false, _mut84838 = false, _mut84839 = false, _mut84840 = false, _mut84841 = false, _mut84842 = false, _mut84843 = false, _mut84844 = false, _mut84845 = false, _mut84846 = false, _mut84847 = false, _mut84848 = false, _mut84849 = false, _mut84850 = false, _mut84851 = false, _mut84852 = false, _mut84853 = false, _mut84854 = false, _mut84855 = false, _mut84856 = false, _mut84857 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140129L;

    /**
     * Vertices of the hull.
     */
    private final Vector2D[] vertices;

    /**
     * Tolerance threshold used during creation of the hull vertices.
     */
    private final double tolerance;

    /**
     * Line segments of the hull.
     * The array is not serialized and will be created from the vertices on first access.
     */
    private transient Segment[] lineSegments;

    /**
     * Simple constructor.
     * @param vertices the vertices of the convex hull, must be ordered
     * @param tolerance tolerance below which points are considered identical
     * @throws MathIllegalArgumentException if the vertices do not form a convex hull
     */
    public ConvexHull2D(final Vector2D[] vertices, final double tolerance) throws MathIllegalArgumentException {
        // assign tolerance as it will be used by the isConvex method
        this.tolerance = tolerance;
        if (!isConvex(vertices)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX);
        }
        this.vertices = vertices.clone();
    }

    /**
     * Checks whether the given hull vertices form a convex hull.
     * @param hullVertices the hull vertices
     * @return {@code true} if the vertices form a convex hull, {@code false} otherwise
     */
    private boolean isConvex(final Vector2D[] hullVertices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80");
        if (ROR_less(hullVertices.length, 3, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84786, _mut84787, _mut84788, _mut84789, _mut84790)) {
            return true;
        }
        int sign = 0;
        for (int i = 0; ROR_less(i, hullVertices.length, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84833, _mut84834, _mut84835, _mut84836, _mut84837); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80");
            final Vector2D p1 = hullVertices[ROR_equals(i, 0, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84791, _mut84792, _mut84793, _mut84794, _mut84795) ? AOR_minus(hullVertices.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84800, _mut84801, _mut84802, _mut84803) : AOR_minus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84796, _mut84797, _mut84798, _mut84799)];
            final Vector2D p2 = hullVertices[i];
            final Vector2D p3 = hullVertices[ROR_equals(i, AOR_minus(hullVertices.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84804, _mut84805, _mut84806, _mut84807), "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84808, _mut84809, _mut84810, _mut84811, _mut84812) ? 0 : AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84813, _mut84814, _mut84815, _mut84816)];
            final Vector2D d1 = p2.subtract(p1);
            final Vector2D d2 = p3.subtract(p2);
            final double crossProduct = MathArrays.linearCombination(d1.getX(), d2.getY(), -d1.getY(), d2.getX());
            final int cmp = Precision.compareTo(crossProduct, 0.0, tolerance);
            // in case of collinear points the cross product will be zero
            if (ROR_not_equals(cmp, 0.0, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84817, _mut84818, _mut84819, _mut84820, _mut84821)) {
                if ((_mut84832 ? (ROR_not_equals(sign, 0.0, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84822, _mut84823, _mut84824, _mut84825, _mut84826) || ROR_not_equals(cmp, sign, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84827, _mut84828, _mut84829, _mut84830, _mut84831)) : (ROR_not_equals(sign, 0.0, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84822, _mut84823, _mut84824, _mut84825, _mut84826) && ROR_not_equals(cmp, sign, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.isConvex_80", _mut84827, _mut84828, _mut84829, _mut84830, _mut84831)))) {
                    return false;
                }
                sign = cmp;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D[] getVertices() {
        return vertices.clone();
    }

    /**
     * Get the line segments of the convex hull, ordered.
     * @return the line segments of the convex hull
     */
    public Segment[] getLineSegments() {
        return retrieveLineSegments().clone();
    }

    /**
     * Retrieve the line segments from the cached array or create them if needed.
     *
     * @return the array of line segments
     */
    private Segment[] retrieveLineSegments() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.retrieveLineSegments_126");
        if (lineSegments == null) {
            // construct the line segments - handle special cases of 1 or 2 points
            final int size = vertices.length;
            if (ROR_less_equals(size, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.retrieveLineSegments_126", _mut84838, _mut84839, _mut84840, _mut84841, _mut84842)) {
                this.lineSegments = new Segment[0];
            } else if (ROR_equals(size, 2, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.retrieveLineSegments_126", _mut84843, _mut84844, _mut84845, _mut84846, _mut84847)) {
                this.lineSegments = new Segment[1];
                final Vector2D p1 = vertices[0];
                final Vector2D p2 = vertices[1];
                this.lineSegments[0] = new Segment(p1, p2, new Line(p1, p2, tolerance));
            } else {
                this.lineSegments = new Segment[size];
                Vector2D firstPoint = null;
                Vector2D lastPoint = null;
                int index = 0;
                for (Vector2D point : vertices) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.retrieveLineSegments_126");
                    if (lastPoint == null) {
                        firstPoint = point;
                        lastPoint = point;
                    } else {
                        this.lineSegments[index++] = new Segment(lastPoint, point, new Line(lastPoint, point, tolerance));
                        lastPoint = point;
                    }
                }
                this.lineSegments[index] = new Segment(lastPoint, firstPoint, new Line(lastPoint, firstPoint, tolerance));
            }
        }
        return lineSegments;
    }

    /**
     * {@inheritDoc}
     */
    public Region<Euclidean2D> createRegion() throws InsufficientDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.createRegion_160");
        if (ROR_less(vertices.length, 3, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.createRegion_160", _mut84848, _mut84849, _mut84850, _mut84851, _mut84852)) {
            throw new InsufficientDataException();
        }
        final RegionFactory<Euclidean2D> factory = new RegionFactory<Euclidean2D>();
        final Segment[] segments = retrieveLineSegments();
        final Line[] lineArray = new Line[segments.length];
        for (int i = 0; ROR_less(i, segments.length, "org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.createRegion_160", _mut84853, _mut84854, _mut84855, _mut84856, _mut84857); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D.createRegion_160");
            lineArray[i] = segments[i].getLine();
        }
        return factory.buildConvex(lineArray);
    }
}
