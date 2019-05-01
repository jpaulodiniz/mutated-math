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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.WelzlEncloser;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a region on the 2-sphere: a set of spherical polygons.
 * @since 3.3
 */
public class SphericalPolygonsSet extends AbstractRegion<Sphere2D, Sphere1D> {

    @Conditional
    public static boolean _mut85728 = false, _mut85729 = false, _mut85730 = false, _mut85731 = false, _mut85732 = false, _mut85733 = false, _mut85734 = false, _mut85735 = false, _mut85736 = false, _mut85737 = false, _mut85738 = false, _mut85739 = false, _mut85740 = false, _mut85741 = false, _mut85742 = false, _mut85743 = false, _mut85744 = false, _mut85745 = false, _mut85746 = false, _mut85747 = false, _mut85748 = false, _mut85749 = false, _mut85750 = false, _mut85751 = false, _mut85752 = false, _mut85753 = false, _mut85754 = false, _mut85755 = false, _mut85756 = false, _mut85757 = false, _mut85758 = false, _mut85759 = false, _mut85760 = false, _mut85761 = false, _mut85762 = false, _mut85763 = false, _mut85764 = false, _mut85765 = false, _mut85766 = false, _mut85767 = false, _mut85768 = false, _mut85769 = false, _mut85770 = false, _mut85771 = false, _mut85772 = false, _mut85773 = false, _mut85774 = false, _mut85775 = false, _mut85776 = false, _mut85777 = false, _mut85778 = false, _mut85779 = false, _mut85780 = false, _mut85781 = false, _mut85782 = false, _mut85783 = false, _mut85784 = false, _mut85785 = false, _mut85786 = false, _mut85787 = false, _mut85788 = false, _mut85789 = false, _mut85790 = false, _mut85791 = false, _mut85792 = false, _mut85793 = false, _mut85794 = false, _mut85795 = false, _mut85796 = false, _mut85797 = false, _mut85798 = false, _mut85799 = false, _mut85800 = false, _mut85801 = false, _mut85802 = false, _mut85803 = false, _mut85804 = false, _mut85805 = false, _mut85806 = false, _mut85807 = false, _mut85808 = false, _mut85809 = false, _mut85810 = false, _mut85811 = false, _mut85812 = false, _mut85813 = false, _mut85814 = false, _mut85815 = false, _mut85816 = false, _mut85817 = false, _mut85818 = false, _mut85819 = false, _mut85820 = false, _mut85821 = false, _mut85822 = false, _mut85823 = false, _mut85824 = false, _mut85825 = false, _mut85826 = false, _mut85827 = false, _mut85828 = false, _mut85829 = false, _mut85830 = false, _mut85831 = false, _mut85832 = false, _mut85833 = false, _mut85834 = false, _mut85835 = false, _mut85836 = false, _mut85837 = false, _mut85838 = false, _mut85839 = false, _mut85840 = false, _mut85841 = false;

    /**
     * Boundary defined as an array of closed loops start vertices.
     */
    private List<Vertex> loops;

    /**
     * Build a polygons set representing the whole real 2-sphere.
     * @param tolerance below which points are consider to be identical
     */
    public SphericalPolygonsSet(final double tolerance) {
        super(tolerance);
    }

    /**
     * Build a polygons set representing a hemisphere.
     * @param pole pole of the hemisphere (the pole is in the inside half)
     * @param tolerance below which points are consider to be identical
     */
    public SphericalPolygonsSet(final Vector3D pole, final double tolerance) {
        super(new BSPTree<Sphere2D>(new Circle(pole, tolerance).wholeHyperplane(), new BSPTree<Sphere2D>(Boolean.FALSE), new BSPTree<Sphere2D>(Boolean.TRUE), null), tolerance);
    }

    /**
     * Build a polygons set representing a regular polygon.
     * @param center center of the polygon (the center is in the inside half)
     * @param meridian point defining the reference meridian for first polygon vertex
     * @param outsideRadius distance of the vertices to the center
     * @param n number of sides of the polygon
     * @param tolerance below which points are consider to be identical
     */
    public SphericalPolygonsSet(final Vector3D center, final Vector3D meridian, final double outsideRadius, final int n, final double tolerance) {
        this(tolerance, createRegularPolygonVertices(center, meridian, outsideRadius, n));
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
     * @param tolerance below which points are consider to be identical
     */
    public SphericalPolygonsSet(final BSPTree<Sphere2D> tree, final double tolerance) {
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
     * @param tolerance below which points are consider to be identical
     */
    public SphericalPolygonsSet(final Collection<SubHyperplane<Sphere2D>> boundary, final double tolerance) {
        super(boundary, tolerance);
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
     * be numerically more robust than the {@link #SphericalPolygonsSet(Collection,
     * double) general constructor} using {@link SubHyperplane subhyperplanes}.</p>
     * <p>If the list is empty, the region will represent the whole
     * space.</p>
     * <p>
     * Polygons with thin pikes or dents are inherently difficult to handle because
     * they involve circles with almost opposite directions at some vertices. Polygons
     * whose vertices come from some physical measurement with noise are also
     * difficult because an edge that should be straight may be broken in lots of
     * different pieces with almost equal directions. In both cases, computing the
     * circles intersections is not numerically robust due to the almost 0 or almost
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
    public SphericalPolygonsSet(final double hyperplaneThickness, final S2Point... vertices) {
        super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
    }

    /**
     * Build the vertices representing a regular polygon.
     * @param center center of the polygon (the center is in the inside half)
     * @param meridian point defining the reference meridian for first polygon vertex
     * @param outsideRadius distance of the vertices to the center
     * @param n number of sides of the polygon
     * @return vertices array
     */
    private static S2Point[] createRegularPolygonVertices(final Vector3D center, final Vector3D meridian, final double outsideRadius, final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices_162");
        final S2Point[] array = new S2Point[n];
        final Rotation r0 = new Rotation(Vector3D.crossProduct(center, meridian), outsideRadius, RotationConvention.VECTOR_OPERATOR);
        array[0] = new S2Point(r0.applyTo(center));
        final Rotation r = new Rotation(center, AOR_divide(MathUtils.TWO_PI, n, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices_162", _mut85728, _mut85729, _mut85730, _mut85731), RotationConvention.VECTOR_OPERATOR);
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices_162", _mut85736, _mut85737, _mut85738, _mut85739, _mut85740); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices_162");
            array[i] = new S2Point(r.applyTo(array[AOR_minus(i, 1, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.createRegularPolygonVertices_162", _mut85732, _mut85733, _mut85734, _mut85735)].getVector()));
        }
        return array;
    }

    /**
     * Build the BSP tree of a polygons set from a simple list of vertices.
     * <p>The boundary is provided as a list of points considering to
     * represent the vertices of a simple loop. The interior part of the
     * region is on the left side of this path and the exterior is on its
     * right side.</p>
     * <p>This constructor does not handle polygons with a boundary
     * forming several disconnected paths (such as polygons with holes).</p>
     * <p>This constructor handles only polygons with edges strictly shorter
     * than \( \pi \). If longer edges are needed, they need to be broken up
     * in smaller sub-edges so this constraint holds.</p>
     * <p>For cases where this simple constructor applies, it is expected to
     * be numerically more robust than the {@link #PolygonsSet(Collection) general
     * constructor} using {@link SubHyperplane subhyperplanes}.</p>
     * @param hyperplaneThickness tolerance below which points are consider to
     * belong to the hyperplane (which is therefore more a slab)
     * @param vertices vertices of the simple loop boundary
     * @return the BSP tree of the input vertices
     */
    private static BSPTree<Sphere2D> verticesToTree(final double hyperplaneThickness, final S2Point... vertices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195");
        final int n = vertices.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85741, _mut85742, _mut85743, _mut85744, _mut85745)) {
            // the tree represents the whole space
            return new BSPTree<Sphere2D>(Boolean.TRUE);
        }
        // build the vertices
        final Vertex[] vArray = new Vertex[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85746, _mut85747, _mut85748, _mut85749, _mut85750); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195");
            vArray[i] = new Vertex(vertices[i]);
        }
        // build the edges
        List<Edge> edges = new ArrayList<Edge>(n);
        Vertex end = vArray[AOR_minus(n, 1, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85751, _mut85752, _mut85753, _mut85754)];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85762, _mut85763, _mut85764, _mut85765, _mut85766); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195");
            // get the endpoints of the edge
            final Vertex start = end;
            end = vArray[i];
            // with the current one
            Circle circle = start.sharedCircleWith(end);
            if (circle == null) {
                circle = new Circle(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }
            // create the edge and store it
            edges.add(new Edge(start, end, Vector3D.angle(start.getLocation().getVector(), end.getLocation().getVector()), circle));
            // check if another vertex also happens to be on this circle
            for (final Vertex vertex : vArray) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195");
                if ((_mut85761 ? ((_mut85755 ? (vertex != start || vertex != end) : (vertex != start && vertex != end)) || ROR_less_equals(FastMath.abs(circle.getOffset(vertex.getLocation())), hyperplaneThickness, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85756, _mut85757, _mut85758, _mut85759, _mut85760)) : ((_mut85755 ? (vertex != start || vertex != end) : (vertex != start && vertex != end)) && ROR_less_equals(FastMath.abs(circle.getOffset(vertex.getLocation())), hyperplaneThickness, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.verticesToTree_195", _mut85756, _mut85757, _mut85758, _mut85759, _mut85760)))) {
                    vertex.bindWith(circle);
                }
            }
        }
        // build the tree top-down
        final BSPTree<Sphere2D> tree = new BSPTree<Sphere2D>();
        insertEdges(hyperplaneThickness, tree, edges);
        return tree;
    }

    /**
     * Recursively build a tree by inserting cut sub-hyperplanes.
     * @param hyperplaneThickness tolerance below which points are considered to
     * belong to the hyperplane (which is therefore more a slab)
     * @param node current tree node (it is a leaf node at the beginning
     * of the call)
     * @param edges list of edges to insert in the cell defined by this node
     * (excluding edges not belonging to the cell defined by this node)
     */
    private static void insertEdges(final double hyperplaneThickness, final BSPTree<Sphere2D> node, final List<Edge> edges) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges_259");
        // find an edge with an hyperplane that can be inserted in the node
        int index = 0;
        Edge inserted = null;
        while ((_mut85772 ? (inserted == null || ROR_less(index, edges.size(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges_259", _mut85767, _mut85768, _mut85769, _mut85770, _mut85771)) : (inserted == null && ROR_less(index, edges.size(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges_259", _mut85767, _mut85768, _mut85769, _mut85770, _mut85771)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges_259");
            inserted = edges.get(index++);
            if (!node.insertCut(inserted.getCircle())) {
                inserted = null;
            }
        }
        if (inserted == null) {
            // we need to set its inside/outside boolean indicator
            final BSPTree<Sphere2D> parent = node.getParent();
            if ((_mut85773 ? (parent == null && node == parent.getMinus()) : (parent == null || node == parent.getMinus()))) {
                node.setAttribute(Boolean.TRUE);
            } else {
                node.setAttribute(Boolean.FALSE);
            }
            return;
        }
        // distribute the remaining edges in the two sub-trees
        final List<Edge> outsideList = new ArrayList<Edge>();
        final List<Edge> insideList = new ArrayList<Edge>();
        for (final Edge edge : edges) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.insertEdges_259");
            if (edge != inserted) {
                edge.split(inserted.getCircle(), outsideList, insideList);
            }
        }
        // recurse through lower levels
        if (!outsideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), outsideList);
        } else {
            node.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!insideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), insideList);
        } else {
            node.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SphericalPolygonsSet buildNew(final BSPTree<Sphere2D> tree) {
        return new SphericalPolygonsSet(tree, getTolerance());
    }

    /**
     * {@inheritDoc}
     * @exception MathIllegalStateException if the tolerance setting does not allow to build
     * a clean non-ambiguous boundary
     */
    @Override
    protected void computeGeometricalProperties() throws MathIllegalStateException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.computeGeometricalProperties_319");
        final BSPTree<Sphere2D> tree = getTree(true);
        if (tree.getCut() == null) {
            if ((_mut85774 ? (tree.getCut() == null || (Boolean) tree.getAttribute()) : (tree.getCut() == null && (Boolean) tree.getAttribute()))) {
                // the instance covers the whole space
                setSize(AOR_multiply(4, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.computeGeometricalProperties_319", _mut85775, _mut85776, _mut85777, _mut85778));
                setBarycenter(new S2Point(0, 0));
            } else {
                setSize(0);
                setBarycenter(S2Point.NaN);
            }
        } else {
            // the instance has a boundary
            final PropertiesComputer pc = new PropertiesComputer(getTolerance());
            tree.visit(pc);
            setSize(pc.getArea());
            setBarycenter(pc.getBarycenter());
        }
    }

    /**
     * Get the boundary loops of the polygon.
     * <p>The polygon boundary can be represented as a list of closed loops,
     * each loop being given by exactly one of its vertices. From each loop
     * start vertex, one can follow the loop by finding the outgoing edge,
     * then the end vertex, then the next outgoing edge ... until the start
     * vertex of the loop (exactly the same instance) is found again once
     * the full loop has been visited.</p>
     * <p>If the polygon has no boundary at all, a zero length loop
     * array will be returned.</p>
     * <p>If the polygon is a simple one-piece polygon, then the returned
     * array will contain a single vertex.
     * </p>
     * <p>All edges in the various loops have the inside of the region on
     * their left side (i.e. toward their pole) and the outside on their
     * right side (i.e. away from their pole) when moving in the underlying
     * circle direction. This means that the closed loops obey the direct
     * trigonometric orientation.</p>
     * @return boundary of the polygon, organized as an unmodifiable list of loops start vertices.
     * @exception MathIllegalStateException if the tolerance setting does not allow to build
     * a clean non-ambiguous boundary
     * @see Vertex
     * @see Edge
     */
    public List<Vertex> getBoundaryLoops() throws MathIllegalStateException {
        if (loops == null) {
            if (getTree(false).getCut() == null) {
                loops = Collections.emptyList();
            } else {
                // sort the arcs according to their start point
                final BSPTree<Sphere2D> root = getTree(true);
                final EdgesBuilder visitor = new EdgesBuilder(root, getTolerance());
                root.visit(visitor);
                final List<Edge> edges = visitor.getEdges();
                // convert the list of all edges into a list of start vertices
                loops = new ArrayList<Vertex>();
                while (!edges.isEmpty()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getBoundaryLoops_372");
                    // this is an edge belonging to a new loop, store it
                    Edge edge = edges.get(0);
                    final Vertex startVertex = edge.getStart();
                    loops.add(startVertex);
                    // remove all remaining edges in the same loop
                    do {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getBoundaryLoops_372");
                        // remove one edge
                        for (final Iterator<Edge> iterator = edges.iterator(); iterator.hasNext(); ) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getBoundaryLoops_372");
                            if (iterator.next() == edge) {
                                iterator.remove();
                                break;
                            }
                        }
                        // go to next edge following the boundary loop
                        edge = edge.getEnd().getOutgoing();
                    } while (edge.getStart() != startVertex);
                }
            }
        }
        return Collections.unmodifiableList(loops);
    }

    /**
     * Get a spherical cap enclosing the polygon.
     * <p>
     * This method is intended as a first test to quickly identify points
     * that are guaranteed to be outside of the region, hence performing a full
     * {@link #checkPoint(org.apache.commons.math3.geometry.Vector) checkPoint}
     * only if the point status remains undecided after the quick check. It is
     * is therefore mostly useful to speed up computation for small polygons with
     * complex shapes (say a country boundary on Earth), as the spherical cap will
     * be small and hence will reliably identify a large part of the sphere as outside,
     * whereas the full check can be more computing intensive. A typical use case is
     * therefore:
     * </p>
     * <pre>
     *   // compute region, plus an enclosing spherical cap
     *   SphericalPolygonsSet complexShape = ...;
     *   EnclosingBall<Sphere2D, S2Point> cap = complexShape.getEnclosingCap();
     *
     *   // check lots of points
     *   for (Vector3D p : points) {
     *
     *     final Location l;
     *     if (cap.contains(p)) {
     *       // we cannot be sure where the point is
     *       // we need to perform the full computation
     *       l = complexShape.checkPoint(v);
     *     } else {
     *       // no need to do further computation,
     *       // we already know the point is outside
     *       l = Location.OUTSIDE;
     *     }
     *
     *     // use l ...
     *
     *   }
     * </pre>
     * <p>
     * In the special cases of empty or whole sphere polygons, special
     * spherical caps are returned, with angular radius set to negative
     * or positive infinity so the {@link
     * EnclosingBall#contains(org.apache.commons.math3.geometry.Point) ball.contains(point)}
     * method return always false or true.
     * </p>
     * <p>
     * This method is <em>not</em> guaranteed to return the smallest enclosing cap.
     * </p>
     * @return a spherical cap enclosing the polygon
     */
    public EnclosingBall<Sphere2D, S2Point> getEnclosingCap() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467");
        // handle special cases first
        if (isEmpty()) {
            return new EnclosingBall<Sphere2D, S2Point>(S2Point.PLUS_K, Double.NEGATIVE_INFINITY);
        }
        if (isFull()) {
            return new EnclosingBall<Sphere2D, S2Point>(S2Point.PLUS_K, Double.POSITIVE_INFINITY);
        }
        // as the polygons is neither empty nor full, it has some boundaries and cut hyperplanes
        final BSPTree<Sphere2D> root = getTree(false);
        if ((_mut85779 ? (isEmpty(root.getMinus()) || isFull(root.getPlus())) : (isEmpty(root.getMinus()) && isFull(root.getPlus())))) {
            // the polygon covers an hemisphere, and its boundary is one 2π long edge
            final Circle circle = (Circle) root.getCut().getHyperplane();
            return new EnclosingBall<Sphere2D, S2Point>(new S2Point(circle.getPole()).negate(), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85780, _mut85781, _mut85782, _mut85783));
        }
        if ((_mut85784 ? (isFull(root.getMinus()) || isEmpty(root.getPlus())) : (isFull(root.getMinus()) && isEmpty(root.getPlus())))) {
            // the polygon covers an hemisphere, and its boundary is one 2π long edge
            final Circle circle = (Circle) root.getCut().getHyperplane();
            return new EnclosingBall<Sphere2D, S2Point>(new S2Point(circle.getPole()), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85785, _mut85786, _mut85787, _mut85788));
        }
        // gather some inside points, to be used by the encloser
        final List<Vector3D> points = getInsidePoints();
        // extract points from the boundary loops, to be used by the encloser as well
        final List<Vertex> boundary = getBoundaryLoops();
        for (final Vertex loopStart : boundary) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467");
            int count = 0;
            for (Vertex v = loopStart; (_mut85794 ? (ROR_equals(count, 0, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85789, _mut85790, _mut85791, _mut85792, _mut85793) && v != loopStart) : (ROR_equals(count, 0, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85789, _mut85790, _mut85791, _mut85792, _mut85793) || v != loopStart)); v = v.getOutgoing().getEnd()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467");
                ++count;
                points.add(v.getLocation().getVector());
            }
        }
        // find the smallest enclosing 3D sphere
        final SphereGenerator generator = new SphereGenerator();
        final WelzlEncloser<Euclidean3D, Vector3D> encloser = new WelzlEncloser<Euclidean3D, Vector3D>(getTolerance(), generator);
        EnclosingBall<Euclidean3D, Vector3D> enclosing3D = encloser.enclose(points);
        final Vector3D[] support3D = enclosing3D.getSupport();
        // convert to 3D sphere to spherical cap
        final double r = enclosing3D.getRadius();
        final double h = enclosing3D.getCenter().getNorm();
        if (ROR_less(h, getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85795, _mut85796, _mut85797, _mut85798, _mut85799)) {
            // fall back to a crude approximation, based only on outside convex cells
            EnclosingBall<Sphere2D, S2Point> enclosingS2 = new EnclosingBall<Sphere2D, S2Point>(S2Point.PLUS_K, Double.POSITIVE_INFINITY);
            for (Vector3D outsidePoint : getOutsidePoints()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467");
                final S2Point outsideS2 = new S2Point(outsidePoint);
                final BoundaryProjection<Sphere2D> projection = projectToBoundary(outsideS2);
                if (ROR_less(AOR_minus(FastMath.PI, projection.getOffset(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85800, _mut85801, _mut85802, _mut85803), enclosingS2.getRadius(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85804, _mut85805, _mut85806, _mut85807, _mut85808)) {
                    enclosingS2 = new EnclosingBall<Sphere2D, S2Point>(outsideS2.negate(), AOR_minus(FastMath.PI, projection.getOffset(), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85809, _mut85810, _mut85811, _mut85812), (S2Point) projection.getProjected());
                }
            }
            return enclosingS2;
        }
        final S2Point[] support = new S2Point[support3D.length];
        for (int i = 0; ROR_less(i, support3D.length, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85813, _mut85814, _mut85815, _mut85816, _mut85817); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467");
            support[i] = new S2Point(support3D[i]);
        }
        final EnclosingBall<Sphere2D, S2Point> enclosingS2 = new EnclosingBall<Sphere2D, S2Point>(new S2Point(enclosing3D.getCenter()), FastMath.acos(AOR_divide((AOR_minus(AOR_plus(1, AOR_multiply(h, h, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85818, _mut85819, _mut85820, _mut85821), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85822, _mut85823, _mut85824, _mut85825), AOR_multiply(r, r, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85826, _mut85827, _mut85828, _mut85829), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85830, _mut85831, _mut85832, _mut85833)), (AOR_multiply(2, h, "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85834, _mut85835, _mut85836, _mut85837)), "org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet.getEnclosingCap_467", _mut85838, _mut85839, _mut85840, _mut85841)), support);
        return enclosingS2;
    }

    /**
     * Gather some inside points.
     * @return list of points known to be strictly in all inside convex cells
     */
    private List<Vector3D> getInsidePoints() {
        final PropertiesComputer pc = new PropertiesComputer(getTolerance());
        getTree(true).visit(pc);
        return pc.getConvexCellsInsidePoints();
    }

    /**
     * Gather some outside points.
     * @return list of points known to be strictly in all outside convex cells
     */
    private List<Vector3D> getOutsidePoints() {
        final SphericalPolygonsSet complement = (SphericalPolygonsSet) new RegionFactory<Sphere2D>().getComplement(this);
        final PropertiesComputer pc = new PropertiesComputer(getTolerance());
        complement.getTree(true).visit(pc);
        return pc.getConvexCellsInsidePoints();
    }
}
