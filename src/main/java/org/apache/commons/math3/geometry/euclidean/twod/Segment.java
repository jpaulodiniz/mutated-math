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

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Simple container for a two-points segment.
 * @since 3.0
 */
public class Segment {

    @Conditional
    public static boolean _mut84710 = false, _mut84711 = false, _mut84712 = false, _mut84713 = false, _mut84714 = false, _mut84715 = false, _mut84716 = false, _mut84717 = false, _mut84718 = false, _mut84719 = false, _mut84720 = false, _mut84721 = false, _mut84722 = false, _mut84723 = false, _mut84724 = false, _mut84725 = false, _mut84726 = false, _mut84727 = false, _mut84728 = false, _mut84729 = false, _mut84730 = false, _mut84731 = false, _mut84732 = false, _mut84733 = false, _mut84734 = false, _mut84735 = false, _mut84736 = false, _mut84737 = false, _mut84738 = false, _mut84739 = false, _mut84740 = false, _mut84741 = false, _mut84742 = false, _mut84743 = false, _mut84744 = false, _mut84745 = false, _mut84746 = false, _mut84747 = false, _mut84748 = false, _mut84749 = false, _mut84750 = false, _mut84751 = false, _mut84752 = false, _mut84753 = false, _mut84754 = false, _mut84755 = false, _mut84756 = false, _mut84757 = false, _mut84758 = false, _mut84759 = false, _mut84760 = false, _mut84761 = false, _mut84762 = false, _mut84763 = false, _mut84764 = false, _mut84765 = false, _mut84766 = false, _mut84767 = false, _mut84768 = false, _mut84769 = false, _mut84770 = false, _mut84771 = false, _mut84772 = false, _mut84773 = false, _mut84774 = false, _mut84775 = false, _mut84776 = false, _mut84777 = false, _mut84778 = false, _mut84779 = false, _mut84780 = false;

    /**
     * Start point of the segment.
     */
    private final Vector2D start;

    /**
     * End point of the segment.
     */
    private final Vector2D end;

    /**
     * Line containing the segment.
     */
    private final Line line;

    /**
     * Build a segment.
     * @param start start point of the segment
     * @param end end point of the segment
     * @param line line containing the segment
     */
    public Segment(final Vector2D start, final Vector2D end, final Line line) {
        this.start = start;
        this.end = end;
        this.line = line;
    }

    /**
     * Get the start point of the segment.
     * @return start point of the segment
     */
    public Vector2D getStart() {
        return start;
    }

    /**
     * Get the end point of the segment.
     * @return end point of the segment
     */
    public Vector2D getEnd() {
        return end;
    }

    /**
     * Get the line containing the segment.
     * @return line containing the segment
     */
    public Line getLine() {
        return line;
    }

    /**
     * Calculates the shortest distance from a point to this line segment.
     * <p>
     * If the perpendicular extension from the point to the line does not
     * cross in the bounds of the line segment, the shortest distance to
     * the two end points will be returned.
     * </p>
     *
     * Algorithm adapted from:
     * <a href="http://www.codeguru.com/forum/printthread.php?s=cc8cf0596231f9a7dba4da6e77c29db3&t=194400&pp=15&page=1">
     * Thread @ Codeguru</a>
     *
     * @param p to check
     * @return distance between the instance and the point
     * @since 3.1
     */
    public double distance(final Vector2D p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83");
        final double deltaX = AOR_minus(end.getX(), start.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84710, _mut84711, _mut84712, _mut84713);
        final double deltaY = AOR_minus(end.getY(), start.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84714, _mut84715, _mut84716, _mut84717);
        final double r = AOR_divide((AOR_plus(AOR_multiply((AOR_minus(p.getX(), start.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84718, _mut84719, _mut84720, _mut84721)), deltaX, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84722, _mut84723, _mut84724, _mut84725), AOR_multiply((AOR_minus(p.getY(), start.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84726, _mut84727, _mut84728, _mut84729)), deltaY, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84730, _mut84731, _mut84732, _mut84733), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84734, _mut84735, _mut84736, _mut84737)), (AOR_plus(AOR_multiply(deltaX, deltaX, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84738, _mut84739, _mut84740, _mut84741), AOR_multiply(deltaY, deltaY, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84742, _mut84743, _mut84744, _mut84745), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84746, _mut84747, _mut84748, _mut84749)), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84750, _mut84751, _mut84752, _mut84753);
        // if point isn't on the line segment, just return the shortest distance to the end points
        if ((_mut84764 ? (ROR_less(r, 0, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84754, _mut84755, _mut84756, _mut84757, _mut84758) && ROR_greater(r, 1, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84759, _mut84760, _mut84761, _mut84762, _mut84763)) : (ROR_less(r, 0, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84754, _mut84755, _mut84756, _mut84757, _mut84758) || ROR_greater(r, 1, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84759, _mut84760, _mut84761, _mut84762, _mut84763)))) {
            final double dist1 = getStart().distance((Point<Euclidean2D>) p);
            final double dist2 = getEnd().distance((Point<Euclidean2D>) p);
            return FastMath.min(dist1, dist2);
        } else {
            // find point on line and see if it is in the line segment
            final double px = AOR_plus(start.getX(), AOR_multiply(r, deltaX, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84765, _mut84766, _mut84767, _mut84768), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84769, _mut84770, _mut84771, _mut84772);
            final double py = AOR_plus(start.getY(), AOR_multiply(r, deltaY, "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84773, _mut84774, _mut84775, _mut84776), "org.apache.commons.math3.geometry.euclidean.twod.Segment.distance_83", _mut84777, _mut84778, _mut84779, _mut84780);
            final Vector2D interPt = new Vector2D(px, py);
            return interPt.distance((Point<Euclidean2D>) p);
        }
    }
}
