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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a 2D region: a set of polygons.
 * @since 3.0
 */
public class PolygonsSet extends AbstractRegion<Euclidean2D, Euclidean1D> {

    @Conditional
    public static boolean _mut85024 = false, _mut85025 = false, _mut85026 = false, _mut85027 = false, _mut85028 = false, _mut85029 = false, _mut85030 = false, _mut85031 = false, _mut85032 = false, _mut85033 = false, _mut85034 = false, _mut85035 = false, _mut85036 = false, _mut85037 = false, _mut85038 = false, _mut85039 = false, _mut85040 = false, _mut85041 = false, _mut85042 = false, _mut85043 = false, _mut85044 = false, _mut85045 = false, _mut85046 = false, _mut85047 = false, _mut85048 = false, _mut85049 = false, _mut85050 = false, _mut85051 = false, _mut85052 = false, _mut85053 = false, _mut85054 = false, _mut85055 = false, _mut85056 = false, _mut85057 = false, _mut85058 = false, _mut85059 = false, _mut85060 = false, _mut85061 = false, _mut85062 = false, _mut85063 = false, _mut85064 = false, _mut85065 = false, _mut85066 = false, _mut85067 = false, _mut85068 = false, _mut85069 = false, _mut85070 = false, _mut85071 = false, _mut85072 = false, _mut85073 = false, _mut85074 = false, _mut85075 = false, _mut85076 = false, _mut85077 = false, _mut85078 = false, _mut85079 = false, _mut85080 = false, _mut85081 = false, _mut85082 = false, _mut85083 = false, _mut85084 = false, _mut85085 = false, _mut85086 = false, _mut85087 = false, _mut85088 = false, _mut85089 = false, _mut85090 = false, _mut85091 = false, _mut85092 = false, _mut85093 = false, _mut85094 = false, _mut85095 = false, _mut85096 = false, _mut85097 = false, _mut85098 = false, _mut85099 = false, _mut85100 = false, _mut85101 = false, _mut85102 = false, _mut85103 = false, _mut85104 = false, _mut85105 = false, _mut85106 = false, _mut85107 = false, _mut85108 = false, _mut85109 = false, _mut85110 = false, _mut85111 = false, _mut85112 = false, _mut85113 = false, _mut85114 = false, _mut85115 = false, _mut85116 = false, _mut85117 = false, _mut85118 = false, _mut85119 = false, _mut85120 = false, _mut85121 = false, _mut85122 = false, _mut85123 = false, _mut85124 = false, _mut85125 = false, _mut85126 = false, _mut85127 = false, _mut85128 = false, _mut85129 = false, _mut85130 = false, _mut85131 = false, _mut85132 = false, _mut85133 = false, _mut85134 = false, _mut85135 = false, _mut85136 = false, _mut85137 = false, _mut85138 = false, _mut85139 = false, _mut85140 = false, _mut85141 = false, _mut85142 = false, _mut85143 = false, _mut85144 = false, _mut85145 = false, _mut85146 = false, _mut85147 = false, _mut85148 = false, _mut85149 = false, _mut85150 = false, _mut85151 = false, _mut85152 = false, _mut85153 = false, _mut85154 = false, _mut85155 = false, _mut85156 = false, _mut85157 = false, _mut85158 = false, _mut85159 = false, _mut85160 = false, _mut85161 = false, _mut85162 = false, _mut85163 = false, _mut85164 = false, _mut85165 = false, _mut85166 = false, _mut85167 = false, _mut85168 = false, _mut85169 = false, _mut85170 = false, _mut85171 = false, _mut85172 = false, _mut85173 = false, _mut85174 = false, _mut85175 = false, _mut85176 = false, _mut85177 = false, _mut85178 = false, _mut85179 = false, _mut85180 = false, _mut85181 = false, _mut85182 = false, _mut85183 = false, _mut85184 = false, _mut85185 = false, _mut85186 = false, _mut85187 = false, _mut85188 = false, _mut85189 = false, _mut85190 = false, _mut85191 = false, _mut85192 = false, _mut85193 = false, _mut85194 = false, _mut85195 = false, _mut85196 = false, _mut85197 = false, _mut85198 = false, _mut85199 = false, _mut85200 = false, _mut85201 = false, _mut85202 = false, _mut85203 = false, _mut85204 = false, _mut85205 = false, _mut85206 = false, _mut85207 = false, _mut85208 = false, _mut85209 = false, _mut85210 = false, _mut85211 = false, _mut85212 = false, _mut85213 = false, _mut85214 = false, _mut85215 = false, _mut85216 = false, _mut85217 = false, _mut85218 = false, _mut85219 = false, _mut85220 = false, _mut85221 = false, _mut85222 = false, _mut85223 = false, _mut85224 = false, _mut85225 = false, _mut85226 = false, _mut85227 = false, _mut85228 = false, _mut85229 = false, _mut85230 = false, _mut85231 = false, _mut85232 = false, _mut85233 = false, _mut85234 = false, _mut85235 = false, _mut85236 = false, _mut85237 = false, _mut85238 = false, _mut85239 = false, _mut85240 = false, _mut85241 = false, _mut85242 = false, _mut85243 = false, _mut85244 = false, _mut85245 = false, _mut85246 = false, _mut85247 = false, _mut85248 = false, _mut85249 = false, _mut85250 = false, _mut85251 = false, _mut85252 = false, _mut85253 = false, _mut85254 = false, _mut85255 = false, _mut85256 = false, _mut85257 = false, _mut85258 = false, _mut85259 = false, _mut85260 = false, _mut85261 = false, _mut85262 = false, _mut85263 = false, _mut85264 = false, _mut85265 = false, _mut85266 = false, _mut85267 = false, _mut85268 = false, _mut85269 = false, _mut85270 = false, _mut85271 = false;

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Vertices organized as boundary loops.
     */
    private Vector2D[][] vertices;

    /**
     * Build a polygons set representing the whole plane.
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public PolygonsSet(final double tolerance) {
        super(tolerance);
    }

    /**
     * Build a polygons set from a BSP tree.
     * <p>The leaf nodes of the BSP tree <em>must</em> have a
     * {@code Boolean} attribute representing the inside status of
     * the corresponding cell (true for inside cells, false for outside
     * cells). In order to avoid building too many small objects, it is
     * recommended to use the predefined constants
     * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
     * <p>
     * This constructor is aimed at expert use, as building the tree may
     * be a difficult task. It is not intended for general use and for
     * performances reasons does not check thoroughly its input, as this would
     * require walking the full tree each time. Failing to provide a tree with
     * the proper attributes, <em>will</em> therefore generate problems like
     * {@link NullPointerException} or {@link ClassCastException} only later on.
     * This limitation is known and explains why this constructor is for expert
     * use only. The caller does have the responsibility to provided correct arguments.
     * </p>
     * @param tree inside/outside BSP tree representing the region
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public PolygonsSet(final BSPTree<Euclidean2D> tree, final double tolerance) {
        super(tree, tolerance);
    }

    /**
     * Build a polygons set from a Boundary REPresentation (B-rep).
     * <p>The boundary is provided as a collection of {@link
     * SubHyperplane sub-hyperplanes}. Each sub-hyperplane has the
     * interior part of the region on its minus side and the exterior on
     * its plus side.</p>
     * <p>The boundary elements can be in any order, and can form
     * several non-connected sets (like for example polygons with holes
     * or a set of disjoint polygons considered as a whole). In
     * fact, the elements do not even need to be connected together
     * (their topological connections are not used here). However, if the
     * boundary does not really separate an inside open from an outside
     * open (open having here its topological meaning), then subsequent
     * calls to the {@link
     * org.apache.commons.math3.geometry.partitioning.Region#checkPoint(org.apache.commons.math3.geometry.Point)
     * checkPoint} method will not be meaningful anymore.</p>
     * <p>If the boundary is empty, the region will represent the whole
     * space.</p>
     * @param boundary collection of boundary elements, as a
     * collection of {@link SubHyperplane SubHyperplane} objects
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public PolygonsSet(final Collection<SubHyperplane<Euclidean2D>> boundary, final double tolerance) {
        super(boundary, tolerance);
    }

    /**
     * Build a parallellepipedic box.
     * @param xMin low bound along the x direction
     * @param xMax high bound along the x direction
     * @param yMin low bound along the y direction
     * @param yMax high bound along the y direction
     * @param tolerance tolerance below which points are considered identical
     * @since 3.3
     */
    public PolygonsSet(final double xMin, final double xMax, final double yMin, final double yMax, final double tolerance) {
        super(boxBoundary(xMin, xMax, yMin, yMax, tolerance), tolerance);
    }

    /**
     * Build a polygon from a simple list of vertices.
     * <p>The boundary is provided as a list of points considering to
     * represent the vertices of a simple loop. The interior part of the
     * region is on the left side of this path and the exterior is on its
     * right side.</p>
     * <p>This constructor does not handle polygons with a boundary
     * forming several disconnected paths (such as polygons with holes).</p>
     * <p>For cases where this simple constructor applies, it is expected to
     * be numerically more robust than the {@link #PolygonsSet(Collection) general
     * constructor} using {@link SubHyperplane subhyperplanes}.</p>
     * <p>If the list is empty, the region will represent the whole
     * space.</p>
     * <p>
     * Polygons with thin pikes or dents are inherently difficult to handle because
     * they involve lines with almost opposite directions at some vertices. Polygons
     * whose vertices come from some physical measurement with noise are also
     * difficult because an edge that should be straight may be broken in lots of
     * different pieces with almost equal directions. In both cases, computing the
     * lines intersections is not numerically robust due to the almost 0 or almost
     * &pi; angle. Such cases need to carefully adjust the {@code hyperplaneThickness}
     * parameter. A too small value would often lead to completely wrong polygons
     * with large area wrongly identified as inside or outside. Large values are
     * often much safer. As a rule of thumb, a value slightly below the size of the
     * most accurate detail needed is a good value for the {@code hyperplaneThickness}
     * parameter.
     * </p>
     * @param hyperplaneThickness tolerance below which points are considered to
     * belong to the hyperplane (which is therefore more a slab)
     * @param vertices vertices of the simple loop boundary
     */
    public PolygonsSet(final double hyperplaneThickness, final Vector2D... vertices) {
        super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
    }

    /**
     * Build a polygons set representing the whole real line.
     * @deprecated as of 3.3, replaced with {@link #PolygonsSet(double)}
     */
    @Deprecated
    public PolygonsSet() {
        this(DEFAULT_TOLERANCE);
    }

    /**
     * Build a polygons set from a BSP tree.
     * <p>The leaf nodes of the BSP tree <em>must</em> have a
     * {@code Boolean} attribute representing the inside status of
     * the corresponding cell (true for inside cells, false for outside
     * cells). In order to avoid building too many small objects, it is
     * recommended to use the predefined constants
     * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
     * @param tree inside/outside BSP tree representing the region
     * @deprecated as of 3.3, replaced with {@link #PolygonsSet(BSPTree, double)}
     */
    @Deprecated
    public PolygonsSet(final BSPTree<Euclidean2D> tree) {
        this(tree, DEFAULT_TOLERANCE);
    }

    /**
     * Build a polygons set from a Boundary REPresentation (B-rep).
     * <p>The boundary is provided as a collection of {@link
     * SubHyperplane sub-hyperplanes}. Each sub-hyperplane has the
     * interior part of the region on its minus side and the exterior on
     * its plus side.</p>
     * <p>The boundary elements can be in any order, and can form
     * several non-connected sets (like for example polygons with holes
     * or a set of disjoint polygons considered as a whole). In
     * fact, the elements do not even need to be connected together
     * (their topological connections are not used here). However, if the
     * boundary does not really separate an inside open from an outside
     * open (open having here its topological meaning), then subsequent
     * calls to the {@link
     * org.apache.commons.math3.geometry.partitioning.Region#checkPoint(org.apache.commons.math3.geometry.Point)
     * checkPoint} method will not be meaningful anymore.</p>
     * <p>If the boundary is empty, the region will represent the whole
     * space.</p>
     * @param boundary collection of boundary elements, as a
     * collection of {@link SubHyperplane SubHyperplane} objects
     * @deprecated as of 3.3, replaced with {@link #PolygonsSet(Collection, double)}
     */
    @Deprecated
    public PolygonsSet(final Collection<SubHyperplane<Euclidean2D>> boundary) {
        this(boundary, DEFAULT_TOLERANCE);
    }

    /**
     * Build a parallellepipedic box.
     * @param xMin low bound along the x direction
     * @param xMax high bound along the x direction
     * @param yMin low bound along the y direction
     * @param yMax high bound along the y direction
     * @deprecated as of 3.3, replaced with {@link #PolygonsSet(double, double, double, double, double)}
     */
    @Deprecated
    public PolygonsSet(final double xMin, final double xMax, final double yMin, final double yMax) {
        this(xMin, xMax, yMin, yMax, DEFAULT_TOLERANCE);
    }

    /**
     * Create a list of hyperplanes representing the boundary of a box.
     * @param xMin low bound along the x direction
     * @param xMax high bound along the x direction
     * @param yMin low bound along the y direction
     * @param yMax high bound along the y direction
     * @param tolerance tolerance below which points are considered identical
     * @return boundary of the box
     */
    private static Line[] boxBoundary(final double xMin, final double xMax, final double yMin, final double yMax, final double tolerance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227");
        if ((_mut85042 ? ((ROR_greater_equals(xMin, AOR_minus(xMax, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85024, _mut85025, _mut85026, _mut85027), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85028, _mut85029, _mut85030, _mut85031, _mut85032)) && (ROR_greater_equals(yMin, AOR_minus(yMax, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85033, _mut85034, _mut85035, _mut85036), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85037, _mut85038, _mut85039, _mut85040, _mut85041))) : ((ROR_greater_equals(xMin, AOR_minus(xMax, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85024, _mut85025, _mut85026, _mut85027), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85028, _mut85029, _mut85030, _mut85031, _mut85032)) || (ROR_greater_equals(yMin, AOR_minus(yMax, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85033, _mut85034, _mut85035, _mut85036), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary_227", _mut85037, _mut85038, _mut85039, _mut85040, _mut85041))))) {
            // too thin box, build an empty polygons set
            return null;
        }
        final Vector2D minMin = new Vector2D(xMin, yMin);
        final Vector2D minMax = new Vector2D(xMin, yMax);
        final Vector2D maxMin = new Vector2D(xMax, yMin);
        final Vector2D maxMax = new Vector2D(xMax, yMax);
        return new Line[] { new Line(minMin, maxMin, tolerance), new Line(maxMin, maxMax, tolerance), new Line(maxMax, minMax, tolerance), new Line(minMax, minMin, tolerance) };
    }

    /**
     * Build the BSP tree of a polygons set from a simple list of vertices.
     * <p>The boundary is provided as a list of points considering to
     * represent the vertices of a simple loop. The interior part of the
     * region is on the left side of this path and the exterior is on its
     * right side.</p>
     * <p>This constructor does not handle polygons with a boundary
     * forming several disconnected paths (such as polygons with holes).</p>
     * <p>For cases where this simple constructor applies, it is expected to
     * be numerically more robust than the {@link #PolygonsSet(Collection) general
     * constructor} using {@link SubHyperplane subhyperplanes}.</p>
     * @param hyperplaneThickness tolerance below which points are consider to
     * belong to the hyperplane (which is therefore more a slab)
     * @param vertices vertices of the simple loop boundary
     * @return the BSP tree of the input vertices
     */
    private static BSPTree<Euclidean2D> verticesToTree(final double hyperplaneThickness, final Vector2D... vertices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261");
        final int n = vertices.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85043, _mut85044, _mut85045, _mut85046, _mut85047)) {
            // the tree represents the whole space
            return new BSPTree<Euclidean2D>(Boolean.TRUE);
        }
        // build the vertices
        final Vertex[] vArray = new Vertex[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85048, _mut85049, _mut85050, _mut85051, _mut85052); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261");
            vArray[i] = new Vertex(vertices[i]);
        }
        // build the edges
        List<Edge> edges = new ArrayList<Edge>(n);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85068, _mut85069, _mut85070, _mut85071, _mut85072); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261");
            // get the endpoints of the edge
            final Vertex start = vArray[i];
            final Vertex end = vArray[AOR_remainder((AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85053, _mut85054, _mut85055, _mut85056)), n, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85057, _mut85058, _mut85059, _mut85060)];
            // with the current one
            Line line = start.sharedLineWith(end);
            if (line == null) {
                line = new Line(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }
            // create the edge and store it
            edges.add(new Edge(start, end, line));
            // check if another vertex also happens to be on this line
            for (final Vertex vertex : vArray) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261");
                if ((_mut85067 ? ((_mut85061 ? (vertex != start || vertex != end) : (vertex != start && vertex != end)) || ROR_less_equals(FastMath.abs(line.getOffset((Point<Euclidean2D>) vertex.getLocation())), hyperplaneThickness, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85062, _mut85063, _mut85064, _mut85065, _mut85066)) : ((_mut85061 ? (vertex != start || vertex != end) : (vertex != start && vertex != end)) && ROR_less_equals(FastMath.abs(line.getOffset((Point<Euclidean2D>) vertex.getLocation())), hyperplaneThickness, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree_261", _mut85062, _mut85063, _mut85064, _mut85065, _mut85066)))) {
                    vertex.bindWith(line);
                }
            }
        }
        // build the tree top-down
        final BSPTree<Euclidean2D> tree = new BSPTree<Euclidean2D>();
        insertEdges(hyperplaneThickness, tree, edges);
        return tree;
    }

    /**
     * Recursively build a tree by inserting cut sub-hyperplanes.
     * @param hyperplaneThickness tolerance below which points are consider to
     * belong to the hyperplane (which is therefore more a slab)
     * @param node current tree node (it is a leaf node at the beginning
     * of the call)
     * @param edges list of edges to insert in the cell defined by this node
     * (excluding edges not belonging to the cell defined by this node)
     */
    private static void insertEdges(final double hyperplaneThickness, final BSPTree<Euclidean2D> node, final List<Edge> edges) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321");
        // find an edge with an hyperplane that can be inserted in the node
        int index = 0;
        Edge inserted = null;
        while ((_mut85078 ? (inserted == null || ROR_less(index, edges.size(), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85073, _mut85074, _mut85075, _mut85076, _mut85077)) : (inserted == null && ROR_less(index, edges.size(), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85073, _mut85074, _mut85075, _mut85076, _mut85077)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321");
            inserted = edges.get(index++);
            if (inserted.getNode() == null) {
                if (node.insertCut(inserted.getLine())) {
                    inserted.setNode(node);
                } else {
                    inserted = null;
                }
            } else {
                inserted = null;
            }
        }
        if (inserted == null) {
            // we need to set its inside/outside boolean indicator
            final BSPTree<Euclidean2D> parent = node.getParent();
            if ((_mut85079 ? (parent == null && node == parent.getMinus()) : (parent == null || node == parent.getMinus()))) {
                node.setAttribute(Boolean.TRUE);
            } else {
                node.setAttribute(Boolean.FALSE);
            }
            return;
        }
        // distribute the remaining edges in the two sub-trees
        final List<Edge> plusList = new ArrayList<Edge>();
        final List<Edge> minusList = new ArrayList<Edge>();
        for (final Edge edge : edges) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321");
            if (edge != inserted) {
                final double startOffset = inserted.getLine().getOffset((Point<Euclidean2D>) edge.getStart().getLocation());
                final double endOffset = inserted.getLine().getOffset((Point<Euclidean2D>) edge.getEnd().getLocation());
                Side startSide = (ROR_less_equals(FastMath.abs(startOffset), hyperplaneThickness, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85080, _mut85081, _mut85082, _mut85083, _mut85084)) ? Side.HYPER : ((ROR_less(startOffset, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85085, _mut85086, _mut85087, _mut85088, _mut85089)) ? Side.MINUS : Side.PLUS);
                Side endSide = (ROR_less_equals(FastMath.abs(endOffset), hyperplaneThickness, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85090, _mut85091, _mut85092, _mut85093, _mut85094)) ? Side.HYPER : ((ROR_less(endOffset, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges_321", _mut85095, _mut85096, _mut85097, _mut85098, _mut85099)) ? Side.MINUS : Side.PLUS);
                switch(startSide) {
                    case PLUS:
                        if (endSide == Side.MINUS) {
                            // we need to insert a split point on the hyperplane
                            final Vertex splitPoint = edge.split(inserted.getLine());
                            minusList.add(splitPoint.getOutgoing());
                            plusList.add(splitPoint.getIncoming());
                        } else {
                            plusList.add(edge);
                        }
                        break;
                    case MINUS:
                        if (endSide == Side.PLUS) {
                            // we need to insert a split point on the hyperplane
                            final Vertex splitPoint = edge.split(inserted.getLine());
                            minusList.add(splitPoint.getIncoming());
                            plusList.add(splitPoint.getOutgoing());
                        } else {
                            minusList.add(edge);
                        }
                        break;
                    default:
                        if (endSide == Side.PLUS) {
                            plusList.add(edge);
                        } else if (endSide == Side.MINUS) {
                            minusList.add(edge);
                        }
                        break;
                }
            }
        }
        // recurse through lower levels
        if (!plusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), plusList);
        } else {
            node.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!minusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), minusList);
        } else {
            node.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    /**
     * Internal class for holding vertices while they are processed to build a BSP tree.
     */
    private static class Vertex {

        /**
         * Vertex location.
         */
        private final Vector2D location;

        /**
         * Incoming edge.
         */
        private Edge incoming;

        /**
         * Outgoing edge.
         */
        private Edge outgoing;

        /**
         * Lines bound with this vertex.
         */
        private final List<Line> lines;

        /**
         * Build a non-processed vertex not owned by any node yet.
         * @param location vertex location
         */
        Vertex(final Vector2D location) {
            this.location = location;
            this.incoming = null;
            this.outgoing = null;
            this.lines = new ArrayList<Line>();
        }

        /**
         * Get Vertex location.
         * @return vertex location
         */
        public Vector2D getLocation() {
            return location;
        }

        /**
         * Bind a line considered to contain this vertex.
         * @param line line to bind with this vertex
         */
        public void bindWith(final Line line) {
            lines.add(line);
        }

        /**
         * Get the common line bound with both the instance and another vertex, if any.
         * <p>
         * When two vertices are both bound to the same line, this means they are
         * already handled by node associated with this line, so there is no need
         * to create a cut hyperplane for them.
         * </p>
         * @param vertex other vertex to check instance against
         * @return line bound with both the instance and another vertex, or null if the
         * two vertices do not share a line yet
         */
        public Line sharedLineWith(final Vertex vertex) {
            for (final Line line1 : lines) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.sharedLineWith_460");
                for (final Line line2 : vertex.lines) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.sharedLineWith_460");
                    if (line1 == line2) {
                        return line1;
                    }
                }
            }
            return null;
        }

        /**
         * Set incoming edge.
         * <p>
         * The line supporting the incoming edge is automatically bound
         * with the instance.
         * </p>
         * @param incoming incoming edge
         */
        public void setIncoming(final Edge incoming) {
            this.incoming = incoming;
            bindWith(incoming.getLine());
        }

        /**
         * Get incoming edge.
         * @return incoming edge
         */
        public Edge getIncoming() {
            return incoming;
        }

        /**
         * Set outgoing edge.
         * <p>
         * The line supporting the outgoing edge is automatically bound
         * with the instance.
         * </p>
         * @param outgoing outgoing edge
         */
        public void setOutgoing(final Edge outgoing) {
            this.outgoing = outgoing;
            bindWith(outgoing.getLine());
        }

        /**
         * Get outgoing edge.
         * @return outgoing edge
         */
        public Edge getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Internal class for holding edges while they are processed to build a BSP tree.
     */
    private static class Edge {

        /**
         * Start vertex.
         */
        private final Vertex start;

        /**
         * End vertex.
         */
        private final Vertex end;

        /**
         * Line supporting the edge.
         */
        private final Line line;

        /**
         * Node whose cut hyperplane contains this edge.
         */
        private BSPTree<Euclidean2D> node;

        /**
         * Build an edge not contained in any node yet.
         * @param start start vertex
         * @param end end vertex
         * @param line line supporting the edge
         */
        Edge(final Vertex start, final Vertex end, final Line line) {
            this.start = start;
            this.end = end;
            this.line = line;
            this.node = null;
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
         * Get the line supporting this edge.
         * @return line supporting this edge
         */
        public Line getLine() {
            return line;
        }

        /**
         * Set the node whose cut hyperplane contains this edge.
         * @param node node whose cut hyperplane contains this edge
         */
        public void setNode(final BSPTree<Euclidean2D> node) {
            this.node = node;
        }

        /**
         * Get the node whose cut hyperplane contains this edge.
         * @return node whose cut hyperplane contains this edge
         * (null if edge has not yet been inserted into the BSP tree)
         */
        public BSPTree<Euclidean2D> getNode() {
            return node;
        }

        /**
         * Split the edge.
         * <p>
         * Once split, this edge is not referenced anymore by the vertices,
         * it is replaced by the two half-edges and an intermediate splitting
         * vertex is introduced to connect these two halves.
         * </p>
         * @param splitLine line splitting the edge in two halves
         * @return split vertex (its incoming and outgoing edges are the two halves)
         */
        public Vertex split(final Line splitLine) {
            final Vertex splitVertex = new Vertex(line.intersection(splitLine));
            splitVertex.bindWith(splitLine);
            final Edge startHalf = new Edge(start, splitVertex, line);
            final Edge endHalf = new Edge(splitVertex, end, line);
            startHalf.node = node;
            endHalf.node = node;
            return splitVertex;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolygonsSet buildNew(final BSPTree<Euclidean2D> tree) {
        return new PolygonsSet(tree, getTolerance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeGeometricalProperties() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608");
        final Vector2D[][] v = getVertices();
        if (ROR_equals(v.length, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85100, _mut85101, _mut85102, _mut85103, _mut85104)) {
            final BSPTree<Euclidean2D> tree = getTree(false);
            if ((_mut85166 ? (tree.getCut() == null || (Boolean) tree.getAttribute()) : (tree.getCut() == null && (Boolean) tree.getAttribute()))) {
                // the instance covers the whole space
                setSize(Double.POSITIVE_INFINITY);
                setBarycenter((Point<Euclidean2D>) Vector2D.NaN);
            } else {
                setSize(0);
                setBarycenter((Point<Euclidean2D>) new Vector2D(0, 0));
            }
        } else if (v[0][0] == null) {
            // there is at least one open-loop: the polygon is infinite
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter((Point<Euclidean2D>) Vector2D.NaN);
        } else {
            double sum = 0;
            double sumX = 0;
            double sumY = 0;
            for (Vector2D[] loop : v) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608");
                double x1 = loop[AOR_minus(loop.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85105, _mut85106, _mut85107, _mut85108)].getX();
                double y1 = loop[AOR_minus(loop.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85109, _mut85110, _mut85111, _mut85112)].getY();
                for (final Vector2D point : loop) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608");
                    final double x0 = x1;
                    final double y0 = y1;
                    x1 = point.getX();
                    y1 = point.getY();
                    final double factor = AOR_minus(AOR_multiply(x0, y1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85113, _mut85114, _mut85115, _mut85116), AOR_multiply(y0, x1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85117, _mut85118, _mut85119, _mut85120), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85121, _mut85122, _mut85123, _mut85124);
                    sum += factor;
                    sumX += AOR_multiply(factor, (AOR_plus(x0, x1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85125, _mut85126, _mut85127, _mut85128)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85129, _mut85130, _mut85131, _mut85132);
                    sumY += AOR_multiply(factor, (AOR_plus(y0, y1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85133, _mut85134, _mut85135, _mut85136)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85137, _mut85138, _mut85139, _mut85140);
                }
            }
            if (ROR_less(sum, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85141, _mut85142, _mut85143, _mut85144, _mut85145)) {
                // the polygon as a finite outside surrounded by an infinite inside
                setSize(Double.POSITIVE_INFINITY);
                setBarycenter((Point<Euclidean2D>) Vector2D.NaN);
            } else {
                setSize(AOR_divide(sum, 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85146, _mut85147, _mut85148, _mut85149));
                setBarycenter((Point<Euclidean2D>) new Vector2D(AOR_divide(sumX, (AOR_multiply(3, sum, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85150, _mut85151, _mut85152, _mut85153)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85154, _mut85155, _mut85156, _mut85157), AOR_divide(sumY, (AOR_multiply(3, sum, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85158, _mut85159, _mut85160, _mut85161)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.computeGeometricalProperties_608", _mut85162, _mut85163, _mut85164, _mut85165)));
            }
        }
    }

    /**
     * Get the vertices of the polygon.
     * <p>The polygon boundary can be represented as an array of loops,
     * each loop being itself an array of vertices.</p>
     * <p>In order to identify open loops which start and end by
     * infinite edges, the open loops arrays start with a null point. In
     * this case, the first non null point and the last point of the
     * array do not represent real vertices, they are dummy points
     * intended only to get the direction of the first and last edge. An
     * open loop consisting of a single infinite line will therefore be
     * represented by a three elements array with one null point
     * followed by two dummy points. The open loops are always the first
     * ones in the loops array.</p>
     * <p>If the polygon has no boundary at all, a zero length loop
     * array will be returned.</p>
     * <p>All line segments in the various loops have the inside of the
     * region on their left side and the outside on their right side
     * when moving in the underlying line direction. This means that
     * closed loops surrounding finite areas obey the direct
     * trigonometric orientation.</p>
     * @return vertices of the polygon, organized as oriented boundary
     * loops with the open loops first (the returned value is guaranteed
     * to be non-null)
     */
    public Vector2D[][] getVertices() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685");
        if (vertices == null) {
            if (getTree(false).getCut() == null) {
                vertices = new Vector2D[0][];
            } else {
                // build the unconnected segments
                final SegmentsBuilder visitor = new SegmentsBuilder(getTolerance());
                getTree(true).visit(visitor);
                final List<ConnectableSegment> segments = visitor.getSegments();
                // and using Euclidean distance only as a last resort
                int pending = segments.size();
                pending -= naturalFollowerConnections(segments);
                if (ROR_greater(pending, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85167, _mut85168, _mut85169, _mut85170, _mut85171)) {
                    pending -= splitEdgeConnections(segments);
                }
                if (ROR_greater(pending, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85172, _mut85173, _mut85174, _mut85175, _mut85176)) {
                    pending -= closeVerticesConnections(segments);
                }
                // create the segment loops
                final ArrayList<List<Segment>> loops = new ArrayList<List<Segment>>();
                for (ConnectableSegment s = getUnprocessed(segments); s != null; s = getUnprocessed(segments)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685");
                    final List<Segment> loop = followLoop(s);
                    if (loop != null) {
                        if (loop.get(0).getStart() == null) {
                            // this is an open loop, we put it on the front
                            loops.add(0, loop);
                        } else {
                            // this is a closed loop, we put it on the back
                            loops.add(loop);
                        }
                    }
                }
                // transform the loops in an array of arrays of points
                vertices = new Vector2D[loops.size()][];
                int i = 0;
                for (final List<Segment> loop : loops) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685");
                    if ((_mut85189 ? (ROR_less(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85177, _mut85178, _mut85179, _mut85180, _mut85181) && ((_mut85188 ? ((_mut85187 ? (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) || loop.get(0).getStart() == null) : (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) && loop.get(0).getStart() == null)) || loop.get(1).getEnd() == null) : ((_mut85187 ? (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) || loop.get(0).getStart() == null) : (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) && loop.get(0).getStart() == null)) && loop.get(1).getEnd() == null)))) : (ROR_less(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85177, _mut85178, _mut85179, _mut85180, _mut85181) || ((_mut85188 ? ((_mut85187 ? (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) || loop.get(0).getStart() == null) : (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) && loop.get(0).getStart() == null)) || loop.get(1).getEnd() == null) : ((_mut85187 ? (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) || loop.get(0).getStart() == null) : (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85182, _mut85183, _mut85184, _mut85185, _mut85186) && loop.get(0).getStart() == null)) && loop.get(1).getEnd() == null)))))) {
                        // single infinite line
                        final Line line = loop.get(0).getLine();
                        vertices[i++] = new Vector2D[] { null, line.toSpace((Point<Euclidean1D>) new Vector1D(-Float.MAX_VALUE)), line.toSpace((Point<Euclidean1D>) new Vector1D(+Float.MAX_VALUE)) };
                    } else if (loop.get(0).getStart() == null) {
                        // open loop with at least one real point
                        final Vector2D[] array = new Vector2D[AOR_plus(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85190, _mut85191, _mut85192, _mut85193)];
                        int j = 0;
                        for (Segment segment : loop) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685");
                            if (ROR_equals(j, 0, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85194, _mut85195, _mut85196, _mut85197, _mut85198)) {
                                // null point and first dummy point
                                double x = segment.getLine().toSubSpace((Point<Euclidean2D>) segment.getEnd()).getX();
                                x -= FastMath.max(1.0, FastMath.abs(AOR_divide(x, 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85199, _mut85200, _mut85201, _mut85202)));
                                array[j++] = null;
                                array[j++] = segment.getLine().toSpace((Point<Euclidean1D>) new Vector1D(x));
                            }
                            if (ROR_less(j, (AOR_minus(array.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85203, _mut85204, _mut85205, _mut85206)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85207, _mut85208, _mut85209, _mut85210, _mut85211)) {
                                // current point
                                array[j++] = segment.getEnd();
                            }
                            if (ROR_equals(j, (AOR_minus(array.length, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85212, _mut85213, _mut85214, _mut85215)), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85216, _mut85217, _mut85218, _mut85219, _mut85220)) {
                                // last dummy point
                                double x = segment.getLine().toSubSpace((Point<Euclidean2D>) segment.getStart()).getX();
                                x += FastMath.max(1.0, FastMath.abs(AOR_divide(x, 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685", _mut85221, _mut85222, _mut85223, _mut85224)));
                                array[j++] = segment.getLine().toSpace((Point<Euclidean1D>) new Vector1D(x));
                            }
                        }
                        vertices[i++] = array;
                    } else {
                        final Vector2D[] array = new Vector2D[loop.size()];
                        int j = 0;
                        for (Segment segment : loop) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getVertices_685");
                            array[j++] = segment.getStart();
                        }
                        vertices[i++] = array;
                    }
                }
            }
        }
        return vertices.clone();
    }

    /**
     * Connect the segments using only natural follower information.
     * @param segments segments complete segments list
     * @return number of connections performed
     */
    private int naturalFollowerConnections(final List<ConnectableSegment> segments) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.naturalFollowerConnections_785");
        int connected = 0;
        for (final ConnectableSegment segment : segments) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.naturalFollowerConnections_785");
            if (segment.getNext() == null) {
                final BSPTree<Euclidean2D> node = segment.getNode();
                final BSPTree<Euclidean2D> end = segment.getEndNode();
                for (final ConnectableSegment candidateNext : segments) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.naturalFollowerConnections_785");
                    if ((_mut85226 ? ((_mut85225 ? (candidateNext.getPrevious() == null || candidateNext.getNode() == end) : (candidateNext.getPrevious() == null && candidateNext.getNode() == end)) || candidateNext.getStartNode() == node) : ((_mut85225 ? (candidateNext.getPrevious() == null || candidateNext.getNode() == end) : (candidateNext.getPrevious() == null && candidateNext.getNode() == end)) && candidateNext.getStartNode() == node))) {
                        // connect the two segments
                        segment.setNext(candidateNext);
                        candidateNext.setPrevious(segment);
                        ++connected;
                        break;
                    }
                }
            }
        }
        return connected;
    }

    /**
     * Connect the segments resulting from a line splitting a straight edge.
     * @param segments segments complete segments list
     * @return number of connections performed
     */
    private int splitEdgeConnections(final List<ConnectableSegment> segments) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.splitEdgeConnections_811");
        int connected = 0;
        for (final ConnectableSegment segment : segments) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.splitEdgeConnections_811");
            if (segment.getNext() == null) {
                final Hyperplane<Euclidean2D> hyperplane = segment.getNode().getCut().getHyperplane();
                final BSPTree<Euclidean2D> end = segment.getEndNode();
                for (final ConnectableSegment candidateNext : segments) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.splitEdgeConnections_811");
                    if ((_mut85228 ? ((_mut85227 ? (candidateNext.getPrevious() == null || candidateNext.getNode().getCut().getHyperplane() == hyperplane) : (candidateNext.getPrevious() == null && candidateNext.getNode().getCut().getHyperplane() == hyperplane)) || candidateNext.getStartNode() == end) : ((_mut85227 ? (candidateNext.getPrevious() == null || candidateNext.getNode().getCut().getHyperplane() == hyperplane) : (candidateNext.getPrevious() == null && candidateNext.getNode().getCut().getHyperplane() == hyperplane)) && candidateNext.getStartNode() == end))) {
                        // connect the two segments
                        segment.setNext(candidateNext);
                        candidateNext.setPrevious(segment);
                        ++connected;
                        break;
                    }
                }
            }
        }
        return connected;
    }

    /**
     * Connect the segments using Euclidean distance.
     * <p>
     * This connection heuristic should be used last, as it relies
     * only on a fuzzy distance criterion.
     * </p>
     * @param segments segments complete segments list
     * @return number of connections performed
     */
    private int closeVerticesConnections(final List<ConnectableSegment> segments) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.closeVerticesConnections_841");
        int connected = 0;
        for (final ConnectableSegment segment : segments) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.closeVerticesConnections_841");
            if ((_mut85229 ? (segment.getNext() == null || segment.getEnd() != null) : (segment.getNext() == null && segment.getEnd() != null))) {
                final Vector2D end = segment.getEnd();
                ConnectableSegment selectedNext = null;
                double min = Double.POSITIVE_INFINITY;
                for (final ConnectableSegment candidateNext : segments) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.closeVerticesConnections_841");
                    if ((_mut85230 ? (candidateNext.getPrevious() == null || candidateNext.getStart() != null) : (candidateNext.getPrevious() == null && candidateNext.getStart() != null))) {
                        final double distance = Vector2D.distance(end, candidateNext.getStart());
                        if (ROR_less(distance, min, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.closeVerticesConnections_841", _mut85231, _mut85232, _mut85233, _mut85234, _mut85235)) {
                            selectedNext = candidateNext;
                            min = distance;
                        }
                    }
                }
                if (ROR_less_equals(min, getTolerance(), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.closeVerticesConnections_841", _mut85236, _mut85237, _mut85238, _mut85239, _mut85240)) {
                    // connect the two segments
                    segment.setNext(selectedNext);
                    selectedNext.setPrevious(segment);
                    ++connected;
                }
            }
        }
        return connected;
    }

    /**
     * Get first unprocessed segment from a list.
     * @param segments segments list
     * @return first segment that has not been processed yet
     * or null if all segments have been processed
     */
    private ConnectableSegment getUnprocessed(final List<ConnectableSegment> segments) {
        for (final ConnectableSegment segment : segments) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.getUnprocessed_873");
            if (!segment.isProcessed()) {
                return segment;
            }
        }
        return null;
    }

    /**
     * Build the loop containing a segment.
     * <p>
     * The segment put in the loop will be marked as processed.
     * </p>
     * @param defining segment used to define the loop
     * @return loop containing the segment (may be null if the loop is a
     * degenerated infinitely thin 2 points loop
     */
    private List<Segment> followLoop(final ConnectableSegment defining) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.followLoop_890");
        final List<Segment> loop = new ArrayList<Segment>();
        loop.add(defining);
        defining.setProcessed(true);
        // add segments in connection order
        ConnectableSegment next = defining.getNext();
        while ((_mut85241 ? (next != defining || next != null) : (next != defining && next != null))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.followLoop_890");
            loop.add(next);
            next.setProcessed(true);
            next = next.getNext();
        }
        if (next == null) {
            // we need to find its start too
            ConnectableSegment previous = defining.getPrevious();
            while (previous != null) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.followLoop_890");
                loop.add(0, previous);
                previous.setProcessed(true);
                previous = previous.getPrevious();
            }
        }
        // filter out spurious vertices
        filterSpuriousVertices(loop);
        if ((_mut85247 ? (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.followLoop_890", _mut85242, _mut85243, _mut85244, _mut85245, _mut85246) || loop.get(0).getStart() != null) : (ROR_equals(loop.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.followLoop_890", _mut85242, _mut85243, _mut85244, _mut85245, _mut85246) && loop.get(0).getStart() != null))) {
            // this is a degenerated infinitely thin closed loop, we simply ignore it
            return null;
        } else {
            return loop;
        }
    }

    /**
     * Filter out spurious vertices on straight lines (at machine precision).
     * @param loop segments loop to filter (will be modified in-place)
     */
    private void filterSpuriousVertices(final List<Segment> loop) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.filterSpuriousVertices_930");
        for (int i = 0; ROR_less(i, loop.size(), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.filterSpuriousVertices_930", _mut85257, _mut85258, _mut85259, _mut85260, _mut85261); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.filterSpuriousVertices_930");
            final Segment previous = loop.get(i);
            int j = AOR_remainder((AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.filterSpuriousVertices_930", _mut85248, _mut85249, _mut85250, _mut85251)), loop.size(), "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.filterSpuriousVertices_930", _mut85252, _mut85253, _mut85254, _mut85255);
            final Segment next = loop.get(j);
            if ((_mut85256 ? (next != null || Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), Precision.EPSILON)) : (next != null && Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), Precision.EPSILON)))) {
                // replace the two segments by a single one
                loop.set(j, new Segment(previous.getStart(), next.getEnd(), previous.getLine()));
                loop.remove(i--);
            }
        }
    }

    /**
     * Private extension of Segment allowing connection.
     */
    private static class ConnectableSegment extends Segment {

        /**
         * Node containing segment.
         */
        private final BSPTree<Euclidean2D> node;

        /**
         * Node whose intersection with current node defines start point.
         */
        private final BSPTree<Euclidean2D> startNode;

        /**
         * Node whose intersection with current node defines end point.
         */
        private final BSPTree<Euclidean2D> endNode;

        /**
         * Previous segment.
         */
        private ConnectableSegment previous;

        /**
         * Next segment.
         */
        private ConnectableSegment next;

        /**
         * Indicator for completely processed segments.
         */
        private boolean processed;

        /**
         * Build a segment.
         * @param start start point of the segment
         * @param end end point of the segment
         * @param line line containing the segment
         * @param node node containing the segment
         * @param startNode node whose intersection with current node defines start point
         * @param endNode node whose intersection with current node defines end point
         */
        ConnectableSegment(final Vector2D start, final Vector2D end, final Line line, final BSPTree<Euclidean2D> node, final BSPTree<Euclidean2D> startNode, final BSPTree<Euclidean2D> endNode) {
            super(start, end, line);
            this.node = node;
            this.startNode = startNode;
            this.endNode = endNode;
            this.previous = null;
            this.next = null;
            this.processed = false;
        }

        /**
         * Get the node containing segment.
         * @return node containing segment
         */
        public BSPTree<Euclidean2D> getNode() {
            return node;
        }

        /**
         * Get the node whose intersection with current node defines start point.
         * @return node whose intersection with current node defines start point
         */
        public BSPTree<Euclidean2D> getStartNode() {
            return startNode;
        }

        /**
         * Get the node whose intersection with current node defines end point.
         * @return node whose intersection with current node defines end point
         */
        public BSPTree<Euclidean2D> getEndNode() {
            return endNode;
        }

        /**
         * Get the previous segment.
         * @return previous segment
         */
        public ConnectableSegment getPrevious() {
            return previous;
        }

        /**
         * Set the previous segment.
         * @param previous previous segment
         */
        public void setPrevious(final ConnectableSegment previous) {
            this.previous = previous;
        }

        /**
         * Get the next segment.
         * @return next segment
         */
        public ConnectableSegment getNext() {
            return next;
        }

        /**
         * Set the next segment.
         * @param next previous segment
         */
        public void setNext(final ConnectableSegment next) {
            this.next = next;
        }

        /**
         * Set the processed flag.
         * @param processed processed flag to set
         */
        public void setProcessed(final boolean processed) {
            this.processed = processed;
        }

        /**
         * Check if the segment has been processed.
         * @return true if the segment has been processed
         */
        public boolean isProcessed() {
            return processed;
        }
    }

    /**
     * Visitor building segments.
     */
    private static class SegmentsBuilder implements BSPTreeVisitor<Euclidean2D> {

        /**
         * Tolerance for close nodes connection.
         */
        private final double tolerance;

        /**
         * Built segments.
         */
        private final List<ConnectableSegment> segments;

        /**
         * Simple constructor.
         * @param tolerance tolerance for close nodes connection
         */
        SegmentsBuilder(final double tolerance) {
            this.tolerance = tolerance;
            this.segments = new ArrayList<ConnectableSegment>();
        }

        /**
         * {@inheritDoc}
         */
        public Order visitOrder(final BSPTree<Euclidean2D> node) {
            return Order.MINUS_SUB_PLUS;
        }

        /**
         * {@inheritDoc}
         */
        public void visitInternalNode(final BSPTree<Euclidean2D> node) {
            @SuppressWarnings("unchecked")
            final BoundaryAttribute<Euclidean2D> attribute = (BoundaryAttribute<Euclidean2D>) node.getAttribute();
            final Iterable<BSPTree<Euclidean2D>> splitters = attribute.getSplitters();
            if (attribute.getPlusOutside() != null) {
                addContribution(attribute.getPlusOutside(), node, splitters, false);
            }
            if (attribute.getPlusInside() != null) {
                addContribution(attribute.getPlusInside(), node, splitters, true);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void visitLeafNode(final BSPTree<Euclidean2D> node) {
        }

        /**
         * Add the contribution of a boundary facet.
         * @param sub boundary facet
         * @param node node containing segment
         * @param splitters splitters for the boundary facet
         * @param reversed if true, the facet has the inside on its plus side
         */
        private void addContribution(final SubHyperplane<Euclidean2D> sub, final BSPTree<Euclidean2D> node, final Iterable<BSPTree<Euclidean2D>> splitters, final boolean reversed) {
            @SuppressWarnings("unchecked")
            final AbstractSubHyperplane<Euclidean2D, Euclidean1D> absSub = (AbstractSubHyperplane<Euclidean2D, Euclidean1D>) sub;
            final Line line = (Line) sub.getHyperplane();
            final List<Interval> intervals = ((IntervalsSet) absSub.getRemainingRegion()).asList();
            for (final Interval i : intervals) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.addContribution_1097");
                // find the 2D points
                final Vector2D startV = Double.isInfinite(i.getInf()) ? null : (Vector2D) line.toSpace((Point<Euclidean1D>) new Vector1D(i.getInf()));
                final Vector2D endV = Double.isInfinite(i.getSup()) ? null : (Vector2D) line.toSpace((Point<Euclidean1D>) new Vector1D(i.getSup()));
                // recover the connectivity information
                final BSPTree<Euclidean2D> startN = selectClosest(startV, splitters);
                final BSPTree<Euclidean2D> endN = selectClosest(endV, splitters);
                if (reversed) {
                    segments.add(new ConnectableSegment(endV, startV, line.getReverse(), node, endN, startN));
                } else {
                    segments.add(new ConnectableSegment(startV, endV, line, node, startN, endN));
                }
            }
        }

        /**
         * Select the node whose cut sub-hyperplane is closest to specified point.
         * @param point reference point
         * @param candidates candidate nodes
         * @return node closest to point, or null if no node is closer than tolerance
         */
        private BSPTree<Euclidean2D> selectClosest(final Vector2D point, final Iterable<BSPTree<Euclidean2D>> candidates) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.selectClosest_1134");
            BSPTree<Euclidean2D> selected = null;
            double min = Double.POSITIVE_INFINITY;
            for (final BSPTree<Euclidean2D> node : candidates) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.selectClosest_1134");
                final double distance = FastMath.abs(node.getCut().getHyperplane().getOffset(point));
                if (ROR_less(distance, min, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.selectClosest_1134", _mut85262, _mut85263, _mut85264, _mut85265, _mut85266)) {
                    selected = node;
                    min = distance;
                }
            }
            return ROR_less_equals(min, tolerance, "org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.selectClosest_1134", _mut85267, _mut85268, _mut85269, _mut85270, _mut85271) ? selected : null;
        }

        /**
         * Get the segments.
         * @return built segments
         */
        public List<ConnectableSegment> getSegments() {
            return segments;
        }
    }
}
