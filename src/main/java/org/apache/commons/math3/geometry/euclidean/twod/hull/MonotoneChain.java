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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements Andrew's monotone chain method to generate the convex hull of a finite set of
 * points in the two-dimensional euclidean space.
 * <p>
 * The runtime complexity is O(n log n), with n being the number of input points. If the
 * point set is already sorted (by x-coordinate), the runtime complexity is O(n).
 * <p>
 * The implementation is not sensitive to collinear points on the hull. The parameter
 * {@code includeCollinearPoints} allows to control the behavior with regard to collinear points.
 * If {@code true}, all points on the boundary of the hull will be added to the hull vertices,
 * otherwise only the extreme points will be present. By default, collinear points are not added
 * as hull vertices.
 * <p>
 * The {@code tolerance} parameter (default: 1e-10) is used as epsilon criteria to determine
 * identical and collinear points.
 *
 * @see <a href="http://en.wikibooks.org/wiki/Algorithm_Implementation/Geometry/Convex_hull/Monotone_chain">
 * Andrew's monotone chain algorithm (Wikibooks)</a>
 * @since 3.3
 */
public class MonotoneChain extends AbstractConvexHullGenerator2D {

    @Conditional
    public static boolean _mut84921 = false, _mut84922 = false, _mut84923 = false, _mut84924 = false, _mut84925 = false, _mut84926 = false, _mut84927 = false, _mut84928 = false, _mut84929 = false, _mut84930 = false, _mut84931 = false, _mut84932 = false, _mut84933 = false, _mut84934 = false, _mut84935 = false, _mut84936 = false, _mut84937 = false, _mut84938 = false, _mut84939 = false, _mut84940 = false, _mut84941 = false, _mut84942 = false, _mut84943 = false, _mut84944 = false, _mut84945 = false, _mut84946 = false, _mut84947 = false, _mut84948 = false, _mut84949 = false, _mut84950 = false, _mut84951 = false, _mut84952 = false, _mut84953 = false, _mut84954 = false, _mut84955 = false, _mut84956 = false, _mut84957 = false, _mut84958 = false, _mut84959 = false, _mut84960 = false, _mut84961 = false, _mut84962 = false, _mut84963 = false, _mut84964 = false, _mut84965 = false, _mut84966 = false, _mut84967 = false, _mut84968 = false, _mut84969 = false, _mut84970 = false, _mut84971 = false, _mut84972 = false, _mut84973 = false, _mut84974 = false, _mut84975 = false, _mut84976 = false, _mut84977 = false, _mut84978 = false, _mut84979 = false, _mut84980 = false, _mut84981 = false, _mut84982 = false, _mut84983 = false, _mut84984 = false, _mut84985 = false, _mut84986 = false, _mut84987 = false, _mut84988 = false, _mut84989 = false, _mut84990 = false, _mut84991 = false, _mut84992 = false, _mut84993 = false, _mut84994 = false, _mut84995 = false, _mut84996 = false, _mut84997 = false, _mut84998 = false, _mut84999 = false, _mut85000 = false, _mut85001 = false, _mut85002 = false, _mut85003 = false, _mut85004 = false, _mut85005 = false, _mut85006 = false, _mut85007 = false, _mut85008 = false, _mut85009 = false, _mut85010 = false, _mut85011 = false, _mut85012 = false, _mut85013 = false, _mut85014 = false, _mut85015 = false, _mut85016 = false, _mut85017 = false, _mut85018 = false, _mut85019 = false, _mut85020 = false, _mut85021 = false, _mut85022 = false, _mut85023 = false;

    /**
     * Create a new MonotoneChain instance.
     */
    public MonotoneChain() {
        this(false);
    }

    /**
     * Create a new MonotoneChain instance.
     * @param includeCollinearPoints whether collinear points shall be added as hull vertices
     */
    public MonotoneChain(final boolean includeCollinearPoints) {
        super(includeCollinearPoints);
    }

    /**
     * Create a new MonotoneChain instance.
     * @param includeCollinearPoints whether collinear points shall be added as hull vertices
     * @param tolerance tolerance below which points are considered identical
     */
    public MonotoneChain(final boolean includeCollinearPoints, final double tolerance) {
        super(includeCollinearPoints, tolerance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Vector2D> findHullVertices(final Collection<Vector2D> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
        final List<Vector2D> pointsSortedByXAxis = new ArrayList<Vector2D>(points);
        // sort the points in increasing order on the x-axis
        Collections.sort(pointsSortedByXAxis, new Comparator<Vector2D>() {

            /**
             * {@inheritDoc}
             */
            public int compare(final Vector2D o1, final Vector2D o2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
                final double tolerance = getTolerance();
                // will not be handled correctly when building the upper/lower hull
                final int diff = Precision.compareTo(o1.getX(), o2.getX(), tolerance);
                if (ROR_equals(diff, 0, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84921, _mut84922, _mut84923, _mut84924, _mut84925)) {
                    return Precision.compareTo(o1.getY(), o2.getY(), tolerance);
                } else {
                    return diff;
                }
            }
        });
        // build lower hull
        final List<Vector2D> lowerHull = new ArrayList<Vector2D>();
        for (Vector2D p : pointsSortedByXAxis) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
            updateHull(p, lowerHull);
        }
        // build upper hull
        final List<Vector2D> upperHull = new ArrayList<Vector2D>();
        for (int idx = pointsSortedByXAxis.size() - 1; ROR_greater_equals(idx, 0, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84926, _mut84927, _mut84928, _mut84929, _mut84930); idx--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
            final Vector2D p = pointsSortedByXAxis.get(idx);
            updateHull(p, upperHull);
        }
        // the last point of each list is omitted as it is repeated at the beginning of the other list
        final List<Vector2D> hullVertices = new ArrayList<Vector2D>(AOR_minus(AOR_plus(lowerHull.size(), upperHull.size(), "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84931, _mut84932, _mut84933, _mut84934), 2, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84935, _mut84936, _mut84937, _mut84938));
        for (int idx = 0; ROR_less(idx, AOR_minus(lowerHull.size(), 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84939, _mut84940, _mut84941, _mut84942), "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84943, _mut84944, _mut84945, _mut84946, _mut84947); idx++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
            hullVertices.add(lowerHull.get(idx));
        }
        for (int idx = 0; ROR_less(idx, AOR_minus(upperHull.size(), 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84948, _mut84949, _mut84950, _mut84951), "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85", _mut84952, _mut84953, _mut84954, _mut84955, _mut84956); idx++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.compare_85");
            hullVertices.add(upperHull.get(idx));
        }
        // special case: if the lower and upper hull may contain only 1 point if all are identical
        if ((_mut84957 ? (hullVertices.isEmpty() || !lowerHull.isEmpty()) : (hullVertices.isEmpty() && !lowerHull.isEmpty()))) {
            hullVertices.add(lowerHull.get(0));
        }
        return hullVertices;
    }

    /**
     * Update the partial hull with the current point.
     *
     * @param point the current point
     * @param hull the partial hull
     */
    private void updateHull(final Vector2D point, final List<Vector2D> hull) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135");
        final double tolerance = getTolerance();
        if (ROR_equals(hull.size(), 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84958, _mut84959, _mut84960, _mut84961, _mut84962)) {
            // ensure that we do not add an identical point
            final Vector2D p1 = hull.get(0);
            if (ROR_less(p1.distance(point), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84963, _mut84964, _mut84965, _mut84966, _mut84967)) {
                return;
            }
        }
        while (ROR_greater_equals(hull.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut85019, _mut85020, _mut85021, _mut85022, _mut85023)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135");
            final int size = hull.size();
            final Vector2D p1 = hull.get(AOR_minus(size, 2, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84968, _mut84969, _mut84970, _mut84971));
            final Vector2D p2 = hull.get(AOR_minus(size, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84972, _mut84973, _mut84974, _mut84975));
            final double offset = new Line(p1, p2, tolerance).getOffset(point);
            if (ROR_less(FastMath.abs(offset), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84976, _mut84977, _mut84978, _mut84979, _mut84980)) {
                final double distanceToCurrent = p1.distance(point);
                if ((_mut85000 ? (ROR_less(distanceToCurrent, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84990, _mut84991, _mut84992, _mut84993, _mut84994) && ROR_less(p2.distance(point), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84995, _mut84996, _mut84997, _mut84998, _mut84999)) : (ROR_less(distanceToCurrent, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84990, _mut84991, _mut84992, _mut84993, _mut84994) || ROR_less(p2.distance(point), tolerance, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84995, _mut84996, _mut84997, _mut84998, _mut84999)))) {
                    // the point is assumed to be identical to either p1 or p2
                    return;
                }
                final double distanceToLast = p1.distance(p2);
                if (isIncludeCollinearPoints()) {
                    final int index = ROR_less(distanceToCurrent, distanceToLast, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut85010, _mut85011, _mut85012, _mut85013, _mut85014) ? AOR_minus(size, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut85015, _mut85016, _mut85017, _mut85018) : size;
                    hull.add(index, point);
                } else {
                    if (ROR_greater(distanceToCurrent, distanceToLast, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut85001, _mut85002, _mut85003, _mut85004, _mut85005)) {
                        hull.remove(AOR_minus(size, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut85006, _mut85007, _mut85008, _mut85009));
                        hull.add(point);
                    }
                }
                return;
            } else if (ROR_greater(offset, 0, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84981, _mut84982, _mut84983, _mut84984, _mut84985)) {
                hull.remove(AOR_minus(size, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.MonotoneChain.updateHull_135", _mut84986, _mut84987, _mut84988, _mut84989));
            } else {
                break;
            }
        }
        hull.add(point);
    }
}
