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
package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a 1D region: a set of intervals.
 * @since 3.0
 */
public class IntervalsSet extends AbstractRegion<Euclidean1D, Euclidean1D> implements Iterable<double[]> {

    @Conditional
    public static boolean _mut83891 = false, _mut83892 = false, _mut83893 = false, _mut83894 = false, _mut83895 = false, _mut83896 = false, _mut83897 = false, _mut83898 = false, _mut83899 = false, _mut83900 = false, _mut83901 = false, _mut83902 = false, _mut83903 = false, _mut83904 = false, _mut83905 = false, _mut83906 = false, _mut83907 = false, _mut83908 = false, _mut83909 = false, _mut83910 = false, _mut83911 = false, _mut83912 = false, _mut83913 = false, _mut83914 = false, _mut83915 = false, _mut83916 = false, _mut83917 = false, _mut83918 = false, _mut83919 = false, _mut83920 = false, _mut83921 = false, _mut83922 = false, _mut83923 = false, _mut83924 = false, _mut83925 = false, _mut83926 = false, _mut83927 = false, _mut83928 = false, _mut83929 = false, _mut83930 = false, _mut83931 = false, _mut83932 = false, _mut83933 = false, _mut83934 = false, _mut83935 = false, _mut83936 = false, _mut83937 = false, _mut83938 = false, _mut83939 = false, _mut83940 = false, _mut83941 = false, _mut83942 = false, _mut83943 = false, _mut83944 = false, _mut83945 = false, _mut83946 = false, _mut83947 = false, _mut83948 = false, _mut83949 = false, _mut83950 = false, _mut83951 = false, _mut83952 = false, _mut83953 = false, _mut83954 = false, _mut83955 = false, _mut83956 = false, _mut83957 = false, _mut83958 = false, _mut83959 = false, _mut83960 = false, _mut83961 = false, _mut83962 = false, _mut83963 = false, _mut83964 = false, _mut83965 = false;

    /**
     * Default value for tolerance.
     */
    private static final double DEFAULT_TOLERANCE = 1.0e-10;

    /**
     * Build an intervals set representing the whole real line.
     * @param tolerance tolerance below which points are considered identical.
     * @since 3.3
     */
    public IntervalsSet(final double tolerance) {
        super(tolerance);
    }

    /**
     * Build an intervals set corresponding to a single interval.
     * @param lower lower bound of the interval, must be lesser or equal
     * to {@code upper} (may be {@code Double.NEGATIVE_INFINITY})
     * @param upper upper bound of the interval, must be greater or equal
     * to {@code lower} (may be {@code Double.POSITIVE_INFINITY})
     * @param tolerance tolerance below which points are considered identical.
     * @since 3.3
     */
    public IntervalsSet(final double lower, final double upper, final double tolerance) {
        super(buildTree(lower, upper, tolerance), tolerance);
    }

    /**
     * Build an intervals set from an inside/outside BSP tree.
     * <p>The leaf nodes of the BSP tree <em>must</em> have a
     * {@code Boolean} attribute representing the inside status of
     * the corresponding cell (true for inside cells, false for outside
     * cells). In order to avoid building too many small objects, it is
     * recommended to use the predefined constants
     * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
     * @param tree inside/outside BSP tree representing the intervals set
     * @param tolerance tolerance below which points are considered identical.
     * @since 3.3
     */
    public IntervalsSet(final BSPTree<Euclidean1D> tree, final double tolerance) {
        super(tree, tolerance);
    }

    /**
     * Build an intervals set from a Boundary REPresentation (B-rep).
     * <p>The boundary is provided as a collection of {@link
     * SubHyperplane sub-hyperplanes}. Each sub-hyperplane has the
     * interior part of the region on its minus side and the exterior on
     * its plus side.</p>
     * <p>The boundary elements can be in any order, and can form
     * several non-connected sets (like for example polygons with holes
     * or a set of disjoints polyhedrons considered as a whole). In
     * fact, the elements do not even need to be connected together
     * (their topological connections are not used here). However, if the
     * boundary does not really separate an inside open from an outside
     * open (open having here its topological meaning), then subsequent
     * calls to the {@link
     * org.apache.commons.math3.geometry.partitioning.Region#checkPoint(org.apache.commons.math3.geometry.Point)
     * checkPoint} method will not be meaningful anymore.</p>
     * <p>If the boundary is empty, the region will represent the whole
     * space.</p>
     * @param boundary collection of boundary elements
     * @param tolerance tolerance below which points are considered identical.
     * @since 3.3
     */
    public IntervalsSet(final Collection<SubHyperplane<Euclidean1D>> boundary, final double tolerance) {
        super(boundary, tolerance);
    }

    /**
     * Build an intervals set representing the whole real line.
     * @deprecated as of 3.1 replaced with {@link #IntervalsSet(double)}
     */
    @Deprecated
    public IntervalsSet() {
        this(DEFAULT_TOLERANCE);
    }

    /**
     * Build an intervals set corresponding to a single interval.
     * @param lower lower bound of the interval, must be lesser or equal
     * to {@code upper} (may be {@code Double.NEGATIVE_INFINITY})
     * @param upper upper bound of the interval, must be greater or equal
     * to {@code lower} (may be {@code Double.POSITIVE_INFINITY})
     * @deprecated as of 3.3 replaced with {@link #IntervalsSet(double, double, double)}
     */
    @Deprecated
    public IntervalsSet(final double lower, final double upper) {
        this(lower, upper, DEFAULT_TOLERANCE);
    }

    /**
     * Build an intervals set from an inside/outside BSP tree.
     * <p>The leaf nodes of the BSP tree <em>must</em> have a
     * {@code Boolean} attribute representing the inside status of
     * the corresponding cell (true for inside cells, false for outside
     * cells). In order to avoid building too many small objects, it is
     * recommended to use the predefined constants
     * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
     * @param tree inside/outside BSP tree representing the intervals set
     * @deprecated as of 3.3, replaced with {@link #IntervalsSet(BSPTree, double)}
     */
    @Deprecated
    public IntervalsSet(final BSPTree<Euclidean1D> tree) {
        this(tree, DEFAULT_TOLERANCE);
    }

    /**
     * Build an intervals set from a Boundary REPresentation (B-rep).
     * <p>The boundary is provided as a collection of {@link
     * SubHyperplane sub-hyperplanes}. Each sub-hyperplane has the
     * interior part of the region on its minus side and the exterior on
     * its plus side.</p>
     * <p>The boundary elements can be in any order, and can form
     * several non-connected sets (like for example polygons with holes
     * or a set of disjoints polyhedrons considered as a whole). In
     * fact, the elements do not even need to be connected together
     * (their topological connections are not used here). However, if the
     * boundary does not really separate an inside open from an outside
     * open (open having here its topological meaning), then subsequent
     * calls to the {@link
     * org.apache.commons.math3.geometry.partitioning.Region#checkPoint(org.apache.commons.math3.geometry.Point)
     * checkPoint} method will not be meaningful anymore.</p>
     * <p>If the boundary is empty, the region will represent the whole
     * space.</p>
     * @param boundary collection of boundary elements
     * @deprecated as of 3.3, replaced with {@link #IntervalsSet(Collection, double)}
     */
    @Deprecated
    public IntervalsSet(final Collection<SubHyperplane<Euclidean1D>> boundary) {
        this(boundary, DEFAULT_TOLERANCE);
    }

    /**
     * Build an inside/outside tree representing a single interval.
     * @param lower lower bound of the interval, must be lesser or equal
     * to {@code upper} (may be {@code Double.NEGATIVE_INFINITY})
     * @param upper upper bound of the interval, must be greater or equal
     * to {@code lower} (may be {@code Double.POSITIVE_INFINITY})
     * @param tolerance tolerance below which points are considered identical.
     * @return the built tree
     */
    private static BSPTree<Euclidean1D> buildTree(final double lower, final double upper, final double tolerance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169");
        if ((_mut83896 ? (Double.isInfinite(lower) || (ROR_less(lower, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83891, _mut83892, _mut83893, _mut83894, _mut83895))) : (Double.isInfinite(lower) && (ROR_less(lower, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83891, _mut83892, _mut83893, _mut83894, _mut83895))))) {
            if ((_mut83902 ? (Double.isInfinite(upper) || (ROR_greater(upper, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83897, _mut83898, _mut83899, _mut83900, _mut83901))) : (Double.isInfinite(upper) && (ROR_greater(upper, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83897, _mut83898, _mut83899, _mut83900, _mut83901))))) {
                // the tree must cover the whole real line
                return new BSPTree<Euclidean1D>(Boolean.TRUE);
            }
            // the tree must be open on the negative infinity side
            final SubHyperplane<Euclidean1D> upperCut = new OrientedPoint(new Vector1D(upper), true, tolerance).wholeHyperplane();
            return new BSPTree<Euclidean1D>(upperCut, new BSPTree<Euclidean1D>(Boolean.FALSE), new BSPTree<Euclidean1D>(Boolean.TRUE), null);
        }
        final SubHyperplane<Euclidean1D> lowerCut = new OrientedPoint(new Vector1D(lower), false, tolerance).wholeHyperplane();
        if ((_mut83908 ? (Double.isInfinite(upper) || (ROR_greater(upper, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83903, _mut83904, _mut83905, _mut83906, _mut83907))) : (Double.isInfinite(upper) && (ROR_greater(upper, 0, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.buildTree_169", _mut83903, _mut83904, _mut83905, _mut83906, _mut83907))))) {
            // the tree must be open on the positive infinity side
            return new BSPTree<Euclidean1D>(lowerCut, new BSPTree<Euclidean1D>(Boolean.FALSE), new BSPTree<Euclidean1D>(Boolean.TRUE), null);
        }
        // the tree must be bounded on the two sides
        final SubHyperplane<Euclidean1D> upperCut = new OrientedPoint(new Vector1D(upper), true, tolerance).wholeHyperplane();
        return new BSPTree<Euclidean1D>(lowerCut, new BSPTree<Euclidean1D>(Boolean.FALSE), new BSPTree<Euclidean1D>(upperCut, new BSPTree<Euclidean1D>(Boolean.FALSE), new BSPTree<Euclidean1D>(Boolean.TRUE), null), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntervalsSet buildNew(final BSPTree<Euclidean1D> tree) {
        return new IntervalsSet(tree, getTolerance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeGeometricalProperties() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.computeGeometricalProperties_214");
        if (getTree(false).getCut() == null) {
            setBarycenter((Point<Euclidean1D>) Vector1D.NaN);
            setSize(((Boolean) getTree(false).getAttribute()) ? Double.POSITIVE_INFINITY : 0);
        } else {
            double size = 0.0;
            double sum = 0.0;
            for (final Interval interval : asList()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.computeGeometricalProperties_214");
                size += interval.getSize();
                sum += AOR_multiply(interval.getSize(), interval.getBarycenter(), "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.computeGeometricalProperties_214", _mut83909, _mut83910, _mut83911, _mut83912);
            }
            setSize(size);
            if (Double.isInfinite(size)) {
                setBarycenter((Point<Euclidean1D>) Vector1D.NaN);
            } else if (ROR_greater_equals(size, Precision.SAFE_MIN, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.computeGeometricalProperties_214", _mut83913, _mut83914, _mut83915, _mut83916, _mut83917)) {
                setBarycenter((Point<Euclidean1D>) new Vector1D(AOR_divide(sum, size, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.computeGeometricalProperties_214", _mut83918, _mut83919, _mut83920, _mut83921)));
            } else {
                setBarycenter((Point<Euclidean1D>) ((OrientedPoint) getTree(false).getCut().getHyperplane()).getLocation());
            }
        }
    }

    /**
     * Get the lowest value belonging to the instance.
     * @return lowest value belonging to the instance
     * ({@code Double.NEGATIVE_INFINITY} if the instance doesn't
     * have any low bound, {@code Double.POSITIVE_INFINITY} if the
     * instance is empty)
     */
    public double getInf() {
        BSPTree<Euclidean1D> node = getTree(false);
        double inf = Double.POSITIVE_INFINITY;
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.getInf_243");
            final OrientedPoint op = (OrientedPoint) node.getCut().getHyperplane();
            inf = op.getLocation().getX();
            node = op.isDirect() ? node.getMinus() : node.getPlus();
        }
        return ((Boolean) node.getAttribute()) ? Double.NEGATIVE_INFINITY : inf;
    }

    /**
     * Get the highest value belonging to the instance.
     * @return highest value belonging to the instance
     * ({@code Double.POSITIVE_INFINITY} if the instance doesn't
     * have any high bound, {@code Double.NEGATIVE_INFINITY} if the
     * instance is empty)
     */
    public double getSup() {
        BSPTree<Euclidean1D> node = getTree(false);
        double sup = Double.NEGATIVE_INFINITY;
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.getSup_260");
            final OrientedPoint op = (OrientedPoint) node.getCut().getHyperplane();
            sup = op.getLocation().getX();
            node = op.isDirect() ? node.getPlus() : node.getMinus();
        }
        return ((Boolean) node.getAttribute()) ? Double.POSITIVE_INFINITY : sup;
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    @Override
    public BoundaryProjection<Euclidean1D> projectToBoundary(final Point<Euclidean1D> point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274");
        // get position of test point
        final double x = ((Vector1D) point).getX();
        double previous = Double.NEGATIVE_INFINITY;
        for (final double[] a : this) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274");
            if (ROR_less(x, a[0], "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83922, _mut83923, _mut83924, _mut83925, _mut83926)) {
                // offset will be positive
                final double previousOffset = AOR_minus(x, previous, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83945, _mut83946, _mut83947, _mut83948);
                final double currentOffset = AOR_minus(a[0], x, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83949, _mut83950, _mut83951, _mut83952);
                if (ROR_less(previousOffset, currentOffset, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83953, _mut83954, _mut83955, _mut83956, _mut83957)) {
                    return new BoundaryProjection<Euclidean1D>(point, finiteOrNullPoint(previous), previousOffset);
                } else {
                    return new BoundaryProjection<Euclidean1D>(point, finiteOrNullPoint(a[0]), currentOffset);
                }
            } else if (ROR_less_equals(x, a[1], "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83927, _mut83928, _mut83929, _mut83930, _mut83931)) {
                // offset will be negative
                final double offset0 = AOR_minus(a[0], x, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83932, _mut83933, _mut83934, _mut83935);
                final double offset1 = AOR_minus(x, a[1], "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83936, _mut83937, _mut83938, _mut83939);
                if (ROR_less(offset0, offset1, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83940, _mut83941, _mut83942, _mut83943, _mut83944)) {
                    return new BoundaryProjection<Euclidean1D>(point, finiteOrNullPoint(a[1]), offset1);
                } else {
                    return new BoundaryProjection<Euclidean1D>(point, finiteOrNullPoint(a[0]), offset0);
                }
            }
            previous = a[1];
        }
        // the test point if past the last sub-interval
        return new BoundaryProjection<Euclidean1D>(point, finiteOrNullPoint(previous), AOR_minus(x, previous, "org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.projectToBoundary_274", _mut83958, _mut83959, _mut83960, _mut83961));
    }

    /**
     * Build a finite point.
     * @param x abscissa of the point
     * @return a new point for finite abscissa, null otherwise
     */
    private Vector1D finiteOrNullPoint(final double x) {
        return Double.isInfinite(x) ? null : new Vector1D(x);
    }

    /**
     * Build an ordered list of intervals representing the instance.
     * <p>This method builds this intervals set as an ordered list of
     * {@link Interval Interval} elements. If the intervals set has no
     * lower limit, the first interval will have its low bound equal to
     * {@code Double.NEGATIVE_INFINITY}. If the intervals set has
     * no upper limit, the last interval will have its upper bound equal
     * to {@code Double.POSITIVE_INFINITY}. An empty tree will
     * build an empty list while a tree representing the whole real line
     * will build a one element list with both bounds being
     * infinite.</p>
     * @return a new ordered list containing {@link Interval Interval}
     * elements
     */
    public List<Interval> asList() {
        final List<Interval> list = new ArrayList<Interval>();
        for (final double[] a : this) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.asList_332");
            list.add(new Interval(a[0], a[1]));
        }
        return list;
    }

    /**
     * Get the first leaf node of a tree.
     * @param root tree root
     * @return first leaf node
     */
    private BSPTree<Euclidean1D> getFirstLeaf(final BSPTree<Euclidean1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        // find the smallest internal node
        BSPTree<Euclidean1D> smallest = null;
        for (BSPTree<Euclidean1D> n = root; n != null; n = previousInternalNode(n)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.getFirstLeaf_344");
            smallest = n;
        }
        return leafBefore(smallest);
    }

    /**
     * Get the node corresponding to the first interval boundary.
     * @return smallest internal node,
     * or null if there are no internal nodes (i.e. the set is either empty or covers the real line)
     */
    private BSPTree<Euclidean1D> getFirstIntervalBoundary() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.getFirstIntervalBoundary_364");
        // start search at the tree root
        BSPTree<Euclidean1D> node = getTree(false);
        if (node.getCut() == null) {
            return null;
        }
        // walk tree until we find the smallest internal node
        node = getFirstLeaf(node).getParent();
        // walk tree until we find an interval boundary
        while ((_mut83963 ? (node != null || !((_mut83962 ? (isIntervalStart(node) && isIntervalEnd(node)) : (isIntervalStart(node) || isIntervalEnd(node))))) : (node != null && !((_mut83962 ? (isIntervalStart(node) && isIntervalEnd(node)) : (isIntervalStart(node) || isIntervalEnd(node))))))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.getFirstIntervalBoundary_364");
            node = nextInternalNode(node);
        }
        return node;
    }

    /**
     * Check if an internal node corresponds to the start abscissa of an interval.
     * @param node internal node to check
     * @return true if the node corresponds to the start abscissa of an interval
     */
    private boolean isIntervalStart(final BSPTree<Euclidean1D> node) {
        if ((Boolean) leafBefore(node).getAttribute()) {
            // it has an inside cell before it, it may end an interval but not start it
            return false;
        }
        if (!(Boolean) leafAfter(node).getAttribute()) {
            // it has an outside cell after it, it is a dummy cut away from real intervals
            return false;
        }
        // it is the start of an interval
        return true;
    }

    /**
     * Check if an internal node corresponds to the end abscissa of an interval.
     * @param node internal node to check
     * @return true if the node corresponds to the end abscissa of an interval
     */
    private boolean isIntervalEnd(final BSPTree<Euclidean1D> node) {
        if (!(Boolean) leafBefore(node).getAttribute()) {
            // it has an outside cell before it, it may start an interval but not end it
            return false;
        }
        if ((Boolean) leafAfter(node).getAttribute()) {
            // it has an inside cell after it, it is a dummy cut in the middle of an interval
            return false;
        }
        // it is the end of an interval
        return true;
    }

    /**
     * Get the next internal node.
     * @param node current internal node
     * @return next internal node in ascending order, or null
     * if this is the last internal node
     */
    private BSPTree<Euclidean1D> nextInternalNode(BSPTree<Euclidean1D> node) {
        if (childAfter(node).getCut() != null) {
            // the next node is in the sub-tree
            return leafAfter(node).getParent();
        }
        // there is nothing left deeper in the tree, we backtrack
        while (isAfterParent(node)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.nextInternalNode_433");
            node = node.getParent();
        }
        return node.getParent();
    }

    /**
     * Get the previous internal node.
     * @param node current internal node
     * @return previous internal node in ascending order, or null
     * if this is the first internal node
     */
    private BSPTree<Euclidean1D> previousInternalNode(BSPTree<Euclidean1D> node) {
        if (childBefore(node).getCut() != null) {
            // the next node is in the sub-tree
            return leafBefore(node).getParent();
        }
        // there is nothing left deeper in the tree, we backtrack
        while (isBeforeParent(node)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.previousInternalNode_453");
            node = node.getParent();
        }
        return node.getParent();
    }

    /**
     * Find the leaf node just before an internal node.
     * @param node internal node at which the sub-tree starts
     * @return leaf node just before the internal node
     */
    private BSPTree<Euclidean1D> leafBefore(BSPTree<Euclidean1D> node) {
        node = childBefore(node);
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.leafBefore_472");
            node = childAfter(node);
        }
        return node;
    }

    /**
     * Find the leaf node just after an internal node.
     * @param node internal node at which the sub-tree starts
     * @return leaf node just after the internal node
     */
    private BSPTree<Euclidean1D> leafAfter(BSPTree<Euclidean1D> node) {
        node = childAfter(node);
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.leafAfter_487");
            node = childBefore(node);
        }
        return node;
    }

    /**
     * Check if a node is the child before its parent in ascending order.
     * @param node child node considered
     * @return true is the node has a parent end is before it in ascending order
     */
    private boolean isBeforeParent(final BSPTree<Euclidean1D> node) {
        final BSPTree<Euclidean1D> parent = node.getParent();
        if (parent == null) {
            return false;
        } else {
            return node == childBefore(parent);
        }
    }

    /**
     * Check if a node is the child after its parent in ascending order.
     * @param node child node considered
     * @return true is the node has a parent end is after it in ascending order
     */
    private boolean isAfterParent(final BSPTree<Euclidean1D> node) {
        final BSPTree<Euclidean1D> parent = node.getParent();
        if (parent == null) {
            return false;
        } else {
            return node == childAfter(parent);
        }
    }

    /**
     * Find the child node just before an internal node.
     * @param node internal node at which the sub-tree starts
     * @return child node just before the internal node
     */
    private BSPTree<Euclidean1D> childBefore(BSPTree<Euclidean1D> node) {
        if (isDirect(node)) {
            // smaller abscissas are on minus side, larger abscissas are on plus side
            return node.getMinus();
        } else {
            // smaller abscissas are on plus side, larger abscissas are on minus side
            return node.getPlus();
        }
    }

    /**
     * Find the child node just after an internal node.
     * @param node internal node at which the sub-tree starts
     * @return child node just after the internal node
     */
    private BSPTree<Euclidean1D> childAfter(BSPTree<Euclidean1D> node) {
        if (isDirect(node)) {
            // smaller abscissas are on minus side, larger abscissas are on plus side
            return node.getPlus();
        } else {
            // smaller abscissas are on plus side, larger abscissas are on minus side
            return node.getMinus();
        }
    }

    /**
     * Check if an internal node has a direct oriented point.
     * @param node internal node to check
     * @return true if the oriented point is direct
     */
    private boolean isDirect(final BSPTree<Euclidean1D> node) {
        return ((OrientedPoint) node.getCut().getHyperplane()).isDirect();
    }

    /**
     * Get the abscissa of an internal node.
     * @param node internal node to check
     * @return abscissa
     */
    private double getAngle(final BSPTree<Euclidean1D> node) {
        return ((OrientedPoint) node.getCut().getHyperplane()).getLocation().getX();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The iterator returns the limit values of sub-intervals in ascending order.
     * </p>
     * <p>
     * The iterator does <em>not</em> support the optional {@code remove} operation.
     * </p>
     * @since 3.3
     */
    public Iterator<double[]> iterator() {
        return new SubIntervalsIterator();
    }

    /**
     * Local iterator for sub-intervals.
     */
    private class SubIntervalsIterator implements Iterator<double[]> {

        /**
         * Current node.
         */
        private BSPTree<Euclidean1D> current;

        /**
         * Sub-interval no yet returned.
         */
        private double[] pending;

        /**
         * Simple constructor.
         */
        SubIntervalsIterator() {
            current = getFirstIntervalBoundary();
            if (current == null) {
                // all the leaf tree nodes share the same inside/outside status
                if ((Boolean) getFirstLeaf(getTree(false)).getAttribute()) {
                    // it is an inside node, it represents the full real line
                    pending = new double[] { Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY };
                } else {
                    pending = null;
                }
            } else if (isIntervalEnd(current)) {
                // so the first interval starts at infinity
                pending = new double[] { Double.NEGATIVE_INFINITY, getAngle(current) };
            } else {
                selectPending();
            }
        }

        /**
         * Walk the tree to select the pending sub-interval.
         */
        private void selectPending() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.selectPending_619");
            // look for the start of the interval
            BSPTree<Euclidean1D> start = current;
            while ((_mut83964 ? (start != null || !isIntervalStart(start)) : (start != null && !isIntervalStart(start)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.selectPending_619");
                start = nextInternalNode(start);
            }
            if (start == null) {
                // we have exhausted the iterator
                current = null;
                pending = null;
                return;
            }
            // look for the end of the interval
            BSPTree<Euclidean1D> end = start;
            while ((_mut83965 ? (end != null || !isIntervalEnd(end)) : (end != null && !isIntervalEnd(end)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet.selectPending_619");
                end = nextInternalNode(end);
            }
            if (end != null) {
                // we have identified the interval
                pending = new double[] { getAngle(start), getAngle(end) };
                // prepare search for next interval
                current = end;
            } else {
                // the final interval is open toward infinity
                pending = new double[] { getAngle(start), Double.POSITIVE_INFINITY };
                // there won't be any other intervals
                current = null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return pending != null;
        }

        /**
         * {@inheritDoc}
         */
        public double[] next() {
            if (pending == null) {
                throw new NoSuchElementException();
            }
            final double[] next = pending;
            selectPending();
            return next;
        }

        /**
         * {@inheritDoc}
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
