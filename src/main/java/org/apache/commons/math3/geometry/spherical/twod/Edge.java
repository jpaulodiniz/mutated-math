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

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Spherical polygons boundary edge.
 * @see SphericalPolygonsSet#getBoundaryLoops()
 * @see Vertex
 * @since 3.3
 */
public class Edge {

    @Conditional
    public static boolean _mut85942 = false, _mut85943 = false, _mut85944 = false, _mut85945 = false, _mut85946 = false, _mut85947 = false, _mut85948 = false, _mut85949 = false, _mut85950 = false, _mut85951 = false, _mut85952 = false, _mut85953 = false, _mut85954 = false, _mut85955 = false, _mut85956 = false, _mut85957 = false, _mut85958 = false, _mut85959 = false, _mut85960 = false, _mut85961 = false, _mut85962 = false, _mut85963 = false, _mut85964 = false, _mut85965 = false, _mut85966 = false, _mut85967 = false, _mut85968 = false, _mut85969 = false, _mut85970 = false, _mut85971 = false, _mut85972 = false, _mut85973 = false, _mut85974 = false, _mut85975 = false, _mut85976 = false, _mut85977 = false, _mut85978 = false, _mut85979 = false, _mut85980 = false, _mut85981 = false, _mut85982 = false, _mut85983 = false, _mut85984 = false, _mut85985 = false, _mut85986 = false, _mut85987 = false, _mut85988 = false, _mut85989 = false, _mut85990 = false, _mut85991 = false, _mut85992 = false, _mut85993 = false, _mut85994 = false, _mut85995 = false, _mut85996 = false, _mut85997 = false, _mut85998 = false, _mut85999 = false, _mut86000 = false, _mut86001 = false, _mut86002 = false, _mut86003 = false, _mut86004 = false, _mut86005 = false, _mut86006 = false, _mut86007 = false, _mut86008 = false, _mut86009 = false, _mut86010 = false, _mut86011 = false, _mut86012 = false, _mut86013 = false, _mut86014 = false, _mut86015 = false, _mut86016 = false, _mut86017 = false, _mut86018 = false, _mut86019 = false, _mut86020 = false, _mut86021 = false, _mut86022 = false, _mut86023 = false, _mut86024 = false, _mut86025 = false, _mut86026 = false, _mut86027 = false, _mut86028 = false, _mut86029 = false, _mut86030 = false, _mut86031 = false, _mut86032 = false, _mut86033 = false, _mut86034 = false, _mut86035 = false;

    /**
     * Start vertex.
     */
    private final Vertex start;

    /**
     * End vertex.
     */
    private Vertex end;

    /**
     * Length of the arc.
     */
    private final double length;

    /**
     * Circle supporting the edge.
     */
    private final Circle circle;

    /**
     * Build an edge not contained in any node yet.
     * @param start start vertex
     * @param end end vertex
     * @param length length of the arc (it can be greater than \( \pi \))
     * @param circle circle supporting the edge
     */
    Edge(final Vertex start, final Vertex end, final double length, final Circle circle) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.circle = circle;
        // connect the vertices back to the edge
        start.setOutgoing(this);
        end.setIncoming(this);
    }

    /**
     * Get start vertex.
     * @return start vertex
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Get end vertex.
     * @return end vertex
     */
    public Vertex getEnd() {
        return end;
    }

    /**
     * Get the length of the arc.
     * @return length of the arc (can be greater than \( \pi \))
     */
    public double getLength() {
        return length;
    }

    /**
     * Get the circle supporting this edge.
     * @return circle supporting this edge
     */
    public Circle getCircle() {
        return circle;
    }

    /**
     * Get an intermediate point.
     * <p>
     * The angle along the edge should normally be between 0 and {@link #getLength()}
     * in order to remain within edge limits. However, there are no checks on the
     * value of the angle, so user can rebuild the full circle on which an edge is
     * defined if they want.
     * </p>
     * @param alpha angle along the edge, counted from {@link #getStart()}
     * @return an intermediate point
     */
    public Vector3D getPointAt(final double alpha) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.Edge.getPointAt_102");
        return circle.getPointAt(AOR_plus(alpha, circle.getPhase(start.getLocation().getVector()), "org.apache.commons.math3.geometry.spherical.twod.Edge.getPointAt_102", _mut85942, _mut85943, _mut85944, _mut85945));
    }

    /**
     * Connect the instance with a following edge.
     * @param next edge following the instance
     */
    void setNextEdge(final Edge next) {
        end = next.getStart();
        end.setIncoming(this);
        end.bindWith(getCircle());
    }

    /**
     * Split the edge.
     * <p>
     * Once split, this edge is not referenced anymore by the vertices,
     * it is replaced by the two or three sub-edges and intermediate splitting
     * vertices are introduced to connect these sub-edges together.
     * </p>
     * @param splitCircle circle splitting the edge in several parts
     * @param outsideList list where to put parts that are outside of the split circle
     * @param insideList list where to put parts that are inside the split circle
     */
    void split(final Circle splitCircle, final List<Edge> outsideList, final List<Edge> insideList) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.Edge.split_125");
        // get the inside arc, synchronizing its phase with the edge itself
        final double edgeStart = circle.getPhase(start.getLocation().getVector());
        final Arc arc = circle.getInsideArc(splitCircle);
        final double arcRelativeStart = AOR_minus(MathUtils.normalizeAngle(arc.getInf(), AOR_plus(edgeStart, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85946, _mut85947, _mut85948, _mut85949)), edgeStart, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85950, _mut85951, _mut85952, _mut85953);
        final double arcRelativeEnd = AOR_plus(arcRelativeStart, arc.getSize(), "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85954, _mut85955, _mut85956, _mut85957);
        final double unwrappedEnd = AOR_minus(arcRelativeEnd, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85958, _mut85959, _mut85960, _mut85961);
        // build the sub-edges
        final double tolerance = circle.getTolerance();
        Vertex previousVertex = start;
        if (ROR_greater_equals(unwrappedEnd, AOR_minus(length, tolerance, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85962, _mut85963, _mut85964, _mut85965), "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85966, _mut85967, _mut85968, _mut85969, _mut85970)) {
            // we don't split anything
            insideList.add(this);
        } else {
            // (even is they are later be filtered out as being too small)
            double alreadyManagedLength = 0;
            if (ROR_greater_equals(unwrappedEnd, 0, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85971, _mut85972, _mut85973, _mut85974, _mut85975)) {
                // the start of the edge is inside the circle
                previousVertex = addSubEdge(previousVertex, new Vertex(new S2Point(circle.getPointAt(AOR_plus(edgeStart, unwrappedEnd, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85976, _mut85977, _mut85978, _mut85979)))), unwrappedEnd, insideList, splitCircle);
                alreadyManagedLength = unwrappedEnd;
            }
            if (ROR_greater_equals(arcRelativeStart, AOR_minus(length, tolerance, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85980, _mut85981, _mut85982, _mut85983), "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85984, _mut85985, _mut85986, _mut85987, _mut85988)) {
                // the edge ends while still outside of the circle
                if (ROR_greater_equals(unwrappedEnd, 0, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86022, _mut86023, _mut86024, _mut86025, _mut86026)) {
                    previousVertex = addSubEdge(previousVertex, end, AOR_minus(length, alreadyManagedLength, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86027, _mut86028, _mut86029, _mut86030), outsideList, splitCircle);
                } else {
                    // we don't split anything
                    outsideList.add(this);
                }
            } else {
                // the edge is long enough to enter inside the circle
                previousVertex = addSubEdge(previousVertex, new Vertex(new S2Point(circle.getPointAt(AOR_plus(edgeStart, arcRelativeStart, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85989, _mut85990, _mut85991, _mut85992)))), AOR_minus(arcRelativeStart, alreadyManagedLength, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85993, _mut85994, _mut85995, _mut85996), outsideList, splitCircle);
                alreadyManagedLength = arcRelativeStart;
                if (ROR_greater_equals(arcRelativeEnd, AOR_minus(length, tolerance, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut85997, _mut85998, _mut85999, _mut86000), "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86001, _mut86002, _mut86003, _mut86004, _mut86005)) {
                    // the edge ends while still inside of the circle
                    previousVertex = addSubEdge(previousVertex, end, AOR_minus(length, alreadyManagedLength, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86018, _mut86019, _mut86020, _mut86021), insideList, splitCircle);
                } else {
                    // the edge is long enough to exit outside of the circle
                    previousVertex = addSubEdge(previousVertex, new Vertex(new S2Point(circle.getPointAt(AOR_plus(edgeStart, arcRelativeStart, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86006, _mut86007, _mut86008, _mut86009)))), AOR_minus(arcRelativeStart, alreadyManagedLength, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86010, _mut86011, _mut86012, _mut86013), insideList, splitCircle);
                    alreadyManagedLength = arcRelativeStart;
                    previousVertex = addSubEdge(previousVertex, end, AOR_minus(length, alreadyManagedLength, "org.apache.commons.math3.geometry.spherical.twod.Edge.split_125", _mut86014, _mut86015, _mut86016, _mut86017), outsideList, splitCircle);
                }
            }
        }
    }

    /**
     * Add a sub-edge to a list if long enough.
     * <p>
     * If the length of the sub-edge to add is smaller than the {@link Circle#getTolerance()}
     * tolerance of the support circle, it will be ignored.
     * </p>
     * @param subStart start of the sub-edge
     * @param subEnd end of the sub-edge
     * @param subLength length of the sub-edge
     * @param splitCircle circle splitting the edge in several parts
     * @param list list where to put the sub-edge
     * @return end vertex of the edge ({@code subEnd} if the edge was long enough and really
     * added, {@code subStart} if the edge was too small and therefore ignored)
     */
    private Vertex addSubEdge(final Vertex subStart, final Vertex subEnd, final double subLength, final List<Edge> list, final Circle splitCircle) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.Edge.addSubEdge_206");
        if (ROR_less_equals(subLength, circle.getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.Edge.addSubEdge_206", _mut86031, _mut86032, _mut86033, _mut86034, _mut86035)) {
            // the edge is too short, we ignore it
            return subStart;
        }
        // really add the edge
        subEnd.bindWith(splitCircle);
        final Edge edge = new Edge(subStart, subEnd, subLength, circle);
        list.add(edge);
        return subEnd;
    }
}
