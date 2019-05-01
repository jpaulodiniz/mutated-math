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
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A simple heuristic to improve the performance of convex hull algorithms.
 * <p>
 * The heuristic is based on the idea of a convex quadrilateral, which is formed by
 * four points with the lowest and highest x / y coordinates. Any point that lies inside
 * this quadrilateral can not be part of the convex hull and can thus be safely discarded
 * before generating the convex hull itself.
 * <p>
 * The complexity of the operation is O(n), and may greatly improve the time it takes to
 * construct the convex hull afterwards, depending on the point distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Convex_hull_algorithms#Akl-Toussaint_heuristic">
 * Akl-Toussaint heuristic (Wikipedia)</a>
 * @since 3.3
 */
public final class AklToussaintHeuristic {

    @Conditional
    public static boolean _mut84858 = false, _mut84859 = false, _mut84860 = false, _mut84861 = false, _mut84862 = false, _mut84863 = false, _mut84864 = false, _mut84865 = false, _mut84866 = false, _mut84867 = false, _mut84868 = false, _mut84869 = false, _mut84870 = false, _mut84871 = false, _mut84872 = false, _mut84873 = false, _mut84874 = false, _mut84875 = false, _mut84876 = false, _mut84877 = false, _mut84878 = false, _mut84879 = false, _mut84880 = false, _mut84881 = false, _mut84882 = false, _mut84883 = false, _mut84884 = false, _mut84885 = false, _mut84886 = false, _mut84887 = false, _mut84888 = false, _mut84889 = false, _mut84890 = false, _mut84891 = false, _mut84892 = false, _mut84893 = false, _mut84894 = false, _mut84895 = false, _mut84896 = false, _mut84897 = false, _mut84898 = false, _mut84899 = false, _mut84900 = false, _mut84901 = false, _mut84902 = false, _mut84903 = false, _mut84904 = false, _mut84905 = false, _mut84906 = false, _mut84907 = false, _mut84908 = false, _mut84909 = false, _mut84910 = false, _mut84911 = false, _mut84912 = false, _mut84913 = false, _mut84914 = false, _mut84915 = false, _mut84916 = false, _mut84917 = false, _mut84918 = false, _mut84919 = false, _mut84920 = false;

    /**
     * Hide utility constructor.
     */
    private AklToussaintHeuristic() {
    }

    /**
     * Returns a point set that is reduced by all points for which it is safe to assume
     * that they are not part of the convex hull.
     *
     * @param points the original point set
     * @return a reduced point set, useful as input for convex hull algorithms
     */
    public static Collection<Vector2D> reducePoints(final Collection<Vector2D> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53");
        // find the leftmost point
        int size = 0;
        Vector2D minX = null;
        Vector2D maxX = null;
        Vector2D minY = null;
        Vector2D maxY = null;
        for (Vector2D p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53");
            if ((_mut84863 ? (minX == null && ROR_less(p.getX(), minX.getX(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84858, _mut84859, _mut84860, _mut84861, _mut84862)) : (minX == null || ROR_less(p.getX(), minX.getX(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84858, _mut84859, _mut84860, _mut84861, _mut84862)))) {
                minX = p;
            }
            if ((_mut84869 ? (maxX == null && ROR_greater(p.getX(), maxX.getX(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84864, _mut84865, _mut84866, _mut84867, _mut84868)) : (maxX == null || ROR_greater(p.getX(), maxX.getX(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84864, _mut84865, _mut84866, _mut84867, _mut84868)))) {
                maxX = p;
            }
            if ((_mut84875 ? (minY == null && ROR_less(p.getY(), minY.getY(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84870, _mut84871, _mut84872, _mut84873, _mut84874)) : (minY == null || ROR_less(p.getY(), minY.getY(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84870, _mut84871, _mut84872, _mut84873, _mut84874)))) {
                minY = p;
            }
            if ((_mut84881 ? (maxY == null && ROR_greater(p.getY(), maxY.getY(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84876, _mut84877, _mut84878, _mut84879, _mut84880)) : (maxY == null || ROR_greater(p.getY(), maxY.getY(), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84876, _mut84877, _mut84878, _mut84879, _mut84880)))) {
                maxY = p;
            }
            size++;
        }
        if (ROR_less(size, 4, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84882, _mut84883, _mut84884, _mut84885, _mut84886)) {
            return points;
        }
        final List<Vector2D> quadrilateral = buildQuadrilateral(minY, maxX, maxY, minX);
        // if the quadrilateral is not well formed, e.g. only 2 points, do not attempt to reduce
        if (ROR_less(quadrilateral.size(), 3, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53", _mut84887, _mut84888, _mut84889, _mut84890, _mut84891)) {
            return points;
        }
        final List<Vector2D> reducedPoints = new ArrayList<Vector2D>(quadrilateral);
        for (final Vector2D p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.reducePoints_53");
            // in which case they can not be part of the convex hull
            if (!insideQuadrilateral(p, quadrilateral)) {
                reducedPoints.add(p);
            }
        }
        return reducedPoints;
    }

    /**
     * Build the convex quadrilateral with the found corner points (with min/max x/y coordinates).
     *
     * @param points the respective points with min/max x/y coordinate
     * @return the quadrilateral
     */
    private static List<Vector2D> buildQuadrilateral(final Vector2D... points) {
        List<Vector2D> quadrilateral = new ArrayList<Vector2D>();
        for (Vector2D p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.buildQuadrilateral_105");
            if (!quadrilateral.contains(p)) {
                quadrilateral.add(p);
            }
        }
        return quadrilateral;
    }

    /**
     * Checks if the given point is located within the convex quadrilateral.
     * @param point the point to check
     * @param quadrilateralPoints the convex quadrilateral, represented by 4 points
     * @return {@code true} if the point is inside the quadrilateral, {@code false} otherwise
     */
    private static boolean insideQuadrilateral(final Vector2D point, final List<Vector2D> quadrilateralPoints) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121");
        Vector2D p1 = quadrilateralPoints.get(0);
        Vector2D p2 = quadrilateralPoints.get(1);
        if ((_mut84892 ? (point.equals(p1) && point.equals(p2)) : (point.equals(p1) || point.equals(p2)))) {
            return true;
        }
        // get the location of the point relative to the first two vertices
        final double last = point.crossProduct(p1, p2);
        final int size = quadrilateralPoints.size();
        // loop through the rest of the vertices
        for (int i = 1; ROR_less(i, size, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84916, _mut84917, _mut84918, _mut84919, _mut84920); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121");
            p1 = p2;
            p2 = quadrilateralPoints.get(ROR_equals((AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84893, _mut84894, _mut84895, _mut84896)), size, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84897, _mut84898, _mut84899, _mut84900, _mut84901) ? 0 : AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84902, _mut84903, _mut84904, _mut84905));
            if ((_mut84906 ? (point.equals(p1) && point.equals(p2)) : (point.equals(p1) || point.equals(p2)))) {
                return true;
            }
            // -x * -y = +xy, x * y = +xy, -x * y = -xy, x * -y = -xy
            if (ROR_less(AOR_multiply(last, point.crossProduct(p1, p2), "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84907, _mut84908, _mut84909, _mut84910), 0, "org.apache.commons.math3.geometry.euclidean.twod.hull.AklToussaintHeuristic.insideQuadrilateral_121", _mut84911, _mut84912, _mut84913, _mut84914, _mut84915)) {
                return false;
            }
        }
        return true;
    }
}
