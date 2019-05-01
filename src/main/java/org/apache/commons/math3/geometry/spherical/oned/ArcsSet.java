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
package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a region of a circle: a set of arcs.
 * <p>
 * Note that due to the wrapping around \(2 \pi\), barycenter is
 * ill-defined here. It was defined only in order to fulfill
 * the requirements of the {@link
 * org.apache.commons.math3.geometry.partitioning.Region Region}
 * interface, but its use is discouraged.
 * </p>
 * @since 3.3
 */
public class ArcsSet extends AbstractRegion<Sphere1D, Sphere1D> implements Iterable<double[]> {

    @Conditional
    public static boolean _mut85380 = false, _mut85381 = false, _mut85382 = false, _mut85383 = false, _mut85384 = false, _mut85385 = false, _mut85386 = false, _mut85387 = false, _mut85388 = false, _mut85389 = false, _mut85390 = false, _mut85391 = false, _mut85392 = false, _mut85393 = false, _mut85394 = false, _mut85395 = false, _mut85396 = false, _mut85397 = false, _mut85398 = false, _mut85399 = false, _mut85400 = false, _mut85401 = false, _mut85402 = false, _mut85403 = false, _mut85404 = false, _mut85405 = false, _mut85406 = false, _mut85407 = false, _mut85408 = false, _mut85409 = false, _mut85410 = false, _mut85411 = false, _mut85412 = false, _mut85413 = false, _mut85414 = false, _mut85415 = false, _mut85416 = false, _mut85417 = false, _mut85418 = false, _mut85419 = false, _mut85420 = false, _mut85421 = false, _mut85422 = false, _mut85423 = false, _mut85424 = false, _mut85425 = false, _mut85426 = false, _mut85427 = false, _mut85428 = false, _mut85429 = false, _mut85430 = false, _mut85431 = false, _mut85432 = false, _mut85433 = false, _mut85434 = false, _mut85435 = false, _mut85436 = false, _mut85437 = false, _mut85438 = false, _mut85439 = false, _mut85440 = false, _mut85441 = false, _mut85442 = false, _mut85443 = false, _mut85444 = false, _mut85445 = false, _mut85446 = false, _mut85447 = false, _mut85448 = false, _mut85449 = false, _mut85450 = false, _mut85451 = false, _mut85452 = false, _mut85453 = false, _mut85454 = false, _mut85455 = false, _mut85456 = false, _mut85457 = false, _mut85458 = false, _mut85459 = false, _mut85460 = false, _mut85461 = false, _mut85462 = false, _mut85463 = false, _mut85464 = false, _mut85465 = false, _mut85466 = false, _mut85467 = false, _mut85468 = false, _mut85469 = false, _mut85470 = false, _mut85471 = false, _mut85472 = false, _mut85473 = false, _mut85474 = false, _mut85475 = false, _mut85476 = false, _mut85477 = false, _mut85478 = false, _mut85479 = false, _mut85480 = false, _mut85481 = false, _mut85482 = false, _mut85483 = false, _mut85484 = false, _mut85485 = false, _mut85486 = false, _mut85487 = false, _mut85488 = false, _mut85489 = false, _mut85490 = false, _mut85491 = false, _mut85492 = false, _mut85493 = false, _mut85494 = false, _mut85495 = false, _mut85496 = false, _mut85497 = false, _mut85498 = false, _mut85499 = false, _mut85500 = false, _mut85501 = false, _mut85502 = false, _mut85503 = false, _mut85504 = false, _mut85505 = false, _mut85506 = false, _mut85507 = false, _mut85508 = false, _mut85509 = false, _mut85510 = false, _mut85511 = false, _mut85512 = false, _mut85513 = false, _mut85514 = false, _mut85515 = false, _mut85516 = false, _mut85517 = false, _mut85518 = false, _mut85519 = false, _mut85520 = false, _mut85521 = false, _mut85522 = false, _mut85523 = false, _mut85524 = false, _mut85525 = false, _mut85526 = false, _mut85527 = false, _mut85528 = false, _mut85529 = false, _mut85530 = false, _mut85531 = false, _mut85532 = false, _mut85533 = false, _mut85534 = false, _mut85535 = false, _mut85536 = false, _mut85537 = false, _mut85538 = false, _mut85539 = false, _mut85540 = false, _mut85541 = false, _mut85542 = false, _mut85543 = false, _mut85544 = false, _mut85545 = false, _mut85546 = false, _mut85547 = false, _mut85548 = false, _mut85549 = false, _mut85550 = false, _mut85551 = false, _mut85552 = false, _mut85553 = false, _mut85554 = false, _mut85555 = false, _mut85556 = false, _mut85557 = false, _mut85558 = false, _mut85559 = false, _mut85560 = false, _mut85561 = false, _mut85562 = false, _mut85563 = false, _mut85564 = false, _mut85565 = false, _mut85566 = false, _mut85567 = false, _mut85568 = false, _mut85569 = false, _mut85570 = false, _mut85571 = false, _mut85572 = false, _mut85573 = false, _mut85574 = false, _mut85575 = false, _mut85576 = false, _mut85577 = false, _mut85578 = false, _mut85579 = false, _mut85580 = false, _mut85581 = false, _mut85582 = false, _mut85583 = false, _mut85584 = false, _mut85585 = false, _mut85586 = false, _mut85587 = false, _mut85588 = false, _mut85589 = false, _mut85590 = false, _mut85591 = false, _mut85592 = false, _mut85593 = false, _mut85594 = false, _mut85595 = false, _mut85596 = false, _mut85597 = false, _mut85598 = false, _mut85599 = false, _mut85600 = false, _mut85601 = false, _mut85602 = false, _mut85603 = false, _mut85604 = false, _mut85605 = false, _mut85606 = false, _mut85607 = false, _mut85608 = false, _mut85609 = false, _mut85610 = false, _mut85611 = false, _mut85612 = false, _mut85613 = false, _mut85614 = false, _mut85615 = false, _mut85616 = false, _mut85617 = false, _mut85618 = false, _mut85619 = false, _mut85620 = false, _mut85621 = false, _mut85622 = false, _mut85623 = false, _mut85624 = false, _mut85625 = false, _mut85626 = false, _mut85627 = false, _mut85628 = false, _mut85629 = false, _mut85630 = false, _mut85631 = false, _mut85632 = false, _mut85633 = false, _mut85634 = false, _mut85635 = false, _mut85636 = false, _mut85637 = false, _mut85638 = false, _mut85639 = false, _mut85640 = false, _mut85641 = false, _mut85642 = false, _mut85643 = false, _mut85644 = false;

    /**
     * Build an arcs set representing the whole circle.
     * @param tolerance tolerance below which close sub-arcs are merged together
     */
    public ArcsSet(final double tolerance) {
        super(tolerance);
    }

    /**
     * Build an arcs set corresponding to a single arc.
     * <p>
     * If either {@code lower} is equals to {@code upper} or
     * the interval exceeds \( 2 \pi \), the arc is considered
     * to be the full circle and its initial defining boundaries
     * will be forgotten. {@code lower} is not allowed to be greater
     * than {@code upper} (an exception is thrown in this case).
     * </p>
     * @param lower lower bound of the arc
     * @param upper upper bound of the arc
     * @param tolerance tolerance below which close sub-arcs are merged together
     * @exception NumberIsTooLargeException if lower is greater than upper
     */
    public ArcsSet(final double lower, final double upper, final double tolerance) throws NumberIsTooLargeException {
        super(buildTree(lower, upper, tolerance), tolerance);
    }

    /**
     * Build an arcs set from an inside/outside BSP tree.
     * <p>The leaf nodes of the BSP tree <em>must</em> have a
     * {@code Boolean} attribute representing the inside status of
     * the corresponding cell (true for inside cells, false for outside
     * cells). In order to avoid building too many small objects, it is
     * recommended to use the predefined constants
     * {@code Boolean.TRUE} and {@code Boolean.FALSE}</p>
     * @param tree inside/outside BSP tree representing the arcs set
     * @param tolerance tolerance below which close sub-arcs are merged together
     * @exception InconsistentStateAt2PiWrapping if the tree leaf nodes are not
     * consistent across the \( 0, 2 \pi \) crossing
     */
    public ArcsSet(final BSPTree<Sphere1D> tree, final double tolerance) throws InconsistentStateAt2PiWrapping {
        super(tree, tolerance);
        check2PiConsistency();
    }

    /**
     * Build an arcs set from a Boundary REPresentation (B-rep).
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
     * @param tolerance tolerance below which close sub-arcs are merged together
     * @exception InconsistentStateAt2PiWrapping if the tree leaf nodes are not
     * consistent across the \( 0, 2 \pi \) crossing
     */
    public ArcsSet(final Collection<SubHyperplane<Sphere1D>> boundary, final double tolerance) throws InconsistentStateAt2PiWrapping {
        super(boundary, tolerance);
        check2PiConsistency();
    }

    /**
     * Build an inside/outside tree representing a single arc.
     * @param lower lower angular bound of the arc
     * @param upper upper angular bound of the arc
     * @param tolerance tolerance below which close sub-arcs are merged together
     * @return the built tree
     * @exception NumberIsTooLargeException if lower is greater than upper
     */
    private static BSPTree<Sphere1D> buildTree(final double lower, final double upper, final double tolerance) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129");
        if ((_mut85389 ? (Precision.equals(lower, upper, 0) && ROR_greater_equals((AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85380, _mut85381, _mut85382, _mut85383)), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85384, _mut85385, _mut85386, _mut85387, _mut85388)) : (Precision.equals(lower, upper, 0) || ROR_greater_equals((AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85380, _mut85381, _mut85382, _mut85383)), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85384, _mut85385, _mut85386, _mut85387, _mut85388)))) {
            // the tree must cover the whole circle
            return new BSPTree<Sphere1D>(Boolean.TRUE);
        } else if (ROR_greater(lower, upper, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85390, _mut85391, _mut85392, _mut85393, _mut85394)) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, lower, upper, true);
        }
        // this is a regular arc, covering only part of the circle
        final double normalizedLower = MathUtils.normalizeAngle(lower, FastMath.PI);
        final double normalizedUpper = AOR_plus(normalizedLower, (AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85395, _mut85396, _mut85397, _mut85398)), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85399, _mut85400, _mut85401, _mut85402);
        final SubHyperplane<Sphere1D> lowerCut = new LimitAngle(new S1Point(normalizedLower), false, tolerance).wholeHyperplane();
        if (ROR_less_equals(normalizedUpper, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85403, _mut85404, _mut85405, _mut85406, _mut85407)) {
            // simple arc starting after 0 and ending before 2 \pi
            final SubHyperplane<Sphere1D> upperCut = new LimitAngle(new S1Point(normalizedUpper), true, tolerance).wholeHyperplane();
            return new BSPTree<Sphere1D>(lowerCut, new BSPTree<Sphere1D>(Boolean.FALSE), new BSPTree<Sphere1D>(upperCut, new BSPTree<Sphere1D>(Boolean.FALSE), new BSPTree<Sphere1D>(Boolean.TRUE), null), null);
        } else {
            // arc wrapping around 2 \pi
            final SubHyperplane<Sphere1D> upperCut = new LimitAngle(new S1Point(AOR_minus(normalizedUpper, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.buildTree_129", _mut85408, _mut85409, _mut85410, _mut85411)), true, tolerance).wholeHyperplane();
            return new BSPTree<Sphere1D>(lowerCut, new BSPTree<Sphere1D>(upperCut, new BSPTree<Sphere1D>(Boolean.FALSE), new BSPTree<Sphere1D>(Boolean.TRUE), null), new BSPTree<Sphere1D>(Boolean.TRUE), null);
        }
    }

    /**
     * Check consistency.
     * @exception InconsistentStateAt2PiWrapping if the tree leaf nodes are not
     * consistent across the \( 0, 2 \pi \) crossing
     */
    private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
        // start search at the tree root
        BSPTree<Sphere1D> root = getTree(false);
        if (root.getCut() == null) {
            return;
        }
        // find the inside/outside state before the smallest internal node
        final Boolean stateBefore = (Boolean) getFirstLeaf(root).getAttribute();
        // find the inside/outside state after the largest internal node
        final Boolean stateAfter = (Boolean) getLastLeaf(root).getAttribute();
        if (stateBefore ^ stateAfter) {
            throw new InconsistentStateAt2PiWrapping();
        }
    }

    /**
     * Get the first leaf node of a tree.
     * @param root tree root
     * @return first leaf node (i.e. node corresponding to the region just after 0.0 radians)
     */
    private BSPTree<Sphere1D> getFirstLeaf(final BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        // find the smallest internal node
        BSPTree<Sphere1D> smallest = null;
        for (BSPTree<Sphere1D> n = root; n != null; n = previousInternalNode(n)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.getFirstLeaf_201");
            smallest = n;
        }
        return leafBefore(smallest);
    }

    /**
     * Get the last leaf node of a tree.
     * @param root tree root
     * @return last leaf node (i.e. node corresponding to the region just before \( 2 \pi \) radians)
     */
    private BSPTree<Sphere1D> getLastLeaf(final BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        // find the largest internal node
        BSPTree<Sphere1D> largest = null;
        for (BSPTree<Sphere1D> n = root; n != null; n = nextInternalNode(n)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.getLastLeaf_221");
            largest = n;
        }
        return leafAfter(largest);
    }

    /**
     * Get the node corresponding to the first arc start.
     * @return smallest internal node (i.e. first after 0.0 radians, in trigonometric direction),
     * or null if there are no internal nodes (i.e. the set is either empty or covers the full circle)
     */
    private BSPTree<Sphere1D> getFirstArcStart() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.getFirstArcStart_241");
        // start search at the tree root
        BSPTree<Sphere1D> node = getTree(false);
        if (node.getCut() == null) {
            return null;
        }
        // walk tree until we find the smallest internal node
        node = getFirstLeaf(node).getParent();
        // walk tree until we find an arc start
        while ((_mut85412 ? (node != null || !isArcStart(node)) : (node != null && !isArcStart(node)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.getFirstArcStart_241");
            node = nextInternalNode(node);
        }
        return node;
    }

    /**
     * Check if an internal node corresponds to the start angle of an arc.
     * @param node internal node to check
     * @return true if the node corresponds to the start angle of an arc
     */
    private boolean isArcStart(final BSPTree<Sphere1D> node) {
        if ((Boolean) leafBefore(node).getAttribute()) {
            // it has an inside cell before it, it may end an arc but not start it
            return false;
        }
        if (!(Boolean) leafAfter(node).getAttribute()) {
            // it has an outside cell after it, it is a dummy cut away from real arcs
            return false;
        }
        // it is the start of an arc
        return true;
    }

    /**
     * Check if an internal node corresponds to the end angle of an arc.
     * @param node internal node to check
     * @return true if the node corresponds to the end angle of an arc
     */
    private boolean isArcEnd(final BSPTree<Sphere1D> node) {
        if (!(Boolean) leafBefore(node).getAttribute()) {
            // it has an outside cell before it, it may start an arc but not end it
            return false;
        }
        if ((Boolean) leafAfter(node).getAttribute()) {
            // it has an inside cell after it, it is a dummy cut in the middle of an arc
            return false;
        }
        // it is the end of an arc
        return true;
    }

    /**
     * Get the next internal node.
     * @param node current internal node
     * @return next internal node in trigonometric order, or null
     * if this is the last internal node
     */
    private BSPTree<Sphere1D> nextInternalNode(BSPTree<Sphere1D> node) {
        if (childAfter(node).getCut() != null) {
            // the next node is in the sub-tree
            return leafAfter(node).getParent();
        }
        // there is nothing left deeper in the tree, we backtrack
        while (isAfterParent(node)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.nextInternalNode_310");
            node = node.getParent();
        }
        return node.getParent();
    }

    /**
     * Get the previous internal node.
     * @param node current internal node
     * @return previous internal node in trigonometric order, or null
     * if this is the first internal node
     */
    private BSPTree<Sphere1D> previousInternalNode(BSPTree<Sphere1D> node) {
        if (childBefore(node).getCut() != null) {
            // the next node is in the sub-tree
            return leafBefore(node).getParent();
        }
        // there is nothing left deeper in the tree, we backtrack
        while (isBeforeParent(node)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.previousInternalNode_330");
            node = node.getParent();
        }
        return node.getParent();
    }

    /**
     * Find the leaf node just before an internal node.
     * @param node internal node at which the sub-tree starts
     * @return leaf node just before the internal node
     */
    private BSPTree<Sphere1D> leafBefore(BSPTree<Sphere1D> node) {
        node = childBefore(node);
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.leafBefore_349");
            node = childAfter(node);
        }
        return node;
    }

    /**
     * Find the leaf node just after an internal node.
     * @param node internal node at which the sub-tree starts
     * @return leaf node just after the internal node
     */
    private BSPTree<Sphere1D> leafAfter(BSPTree<Sphere1D> node) {
        node = childAfter(node);
        while (node.getCut() != null) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.leafAfter_364");
            node = childBefore(node);
        }
        return node;
    }

    /**
     * Check if a node is the child before its parent in trigonometric order.
     * @param node child node considered
     * @return true is the node has a parent end is before it in trigonometric order
     */
    private boolean isBeforeParent(final BSPTree<Sphere1D> node) {
        final BSPTree<Sphere1D> parent = node.getParent();
        if (parent == null) {
            return false;
        } else {
            return node == childBefore(parent);
        }
    }

    /**
     * Check if a node is the child after its parent in trigonometric order.
     * @param node child node considered
     * @return true is the node has a parent end is after it in trigonometric order
     */
    private boolean isAfterParent(final BSPTree<Sphere1D> node) {
        final BSPTree<Sphere1D> parent = node.getParent();
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
    private BSPTree<Sphere1D> childBefore(BSPTree<Sphere1D> node) {
        if (isDirect(node)) {
            // smaller angles are on minus side, larger angles are on plus side
            return node.getMinus();
        } else {
            // smaller angles are on plus side, larger angles are on minus side
            return node.getPlus();
        }
    }

    /**
     * Find the child node just after an internal node.
     * @param node internal node at which the sub-tree starts
     * @return child node just after the internal node
     */
    private BSPTree<Sphere1D> childAfter(BSPTree<Sphere1D> node) {
        if (isDirect(node)) {
            // smaller angles are on minus side, larger angles are on plus side
            return node.getPlus();
        } else {
            // smaller angles are on plus side, larger angles are on minus side
            return node.getMinus();
        }
    }

    /**
     * Check if an internal node has a direct limit angle.
     * @param node internal node to check
     * @return true if the limit angle is direct
     */
    private boolean isDirect(final BSPTree<Sphere1D> node) {
        return ((LimitAngle) node.getCut().getHyperplane()).isDirect();
    }

    /**
     * Get the limit angle of an internal node.
     * @param node internal node to check
     * @return limit angle
     */
    private double getAngle(final BSPTree<Sphere1D> node) {
        return ((LimitAngle) node.getCut().getHyperplane()).getLocation().getAlpha();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArcsSet buildNew(final BSPTree<Sphere1D> tree) {
        return new ArcsSet(tree, getTolerance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeGeometricalProperties() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452");
        if (getTree(false).getCut() == null) {
            setBarycenter(S1Point.NaN);
            setSize(((Boolean) getTree(false).getAttribute()) ? MathUtils.TWO_PI : 0);
        } else {
            double size = 0.0;
            double sum = 0.0;
            for (final double[] a : this) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452");
                final double length = AOR_minus(a[1], a[0], "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85413, _mut85414, _mut85415, _mut85416);
                size += length;
                sum += AOR_multiply(length, (AOR_plus(a[0], a[1], "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85417, _mut85418, _mut85419, _mut85420)), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85421, _mut85422, _mut85423, _mut85424);
            }
            setSize(size);
            if (Precision.equals(size, MathUtils.TWO_PI, 0)) {
                setBarycenter(S1Point.NaN);
            } else if (ROR_greater_equals(size, Precision.SAFE_MIN, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85425, _mut85426, _mut85427, _mut85428, _mut85429)) {
                setBarycenter(new S1Point(AOR_divide(sum, (AOR_multiply(2, size, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85430, _mut85431, _mut85432, _mut85433)), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.computeGeometricalProperties_452", _mut85434, _mut85435, _mut85436, _mut85437)));
            } else {
                final LimitAngle limit = (LimitAngle) getTree(false).getCut().getHyperplane();
                setBarycenter(limit.getLocation());
            }
        }
    }

    /**
     * {@inheritDoc}
     * @since 3.3
     */
    @Override
    public BoundaryProjection<Sphere1D> projectToBoundary(final Point<Sphere1D> point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480");
        // get position of test point
        final double alpha = ((S1Point) point).getAlpha();
        boolean wrapFirst = false;
        double first = Double.NaN;
        double previous = Double.NaN;
        for (final double[] a : this) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480");
            if (Double.isNaN(first)) {
                // remember the first angle in case we need it later
                first = a[0];
            }
            if (!wrapFirst) {
                if (ROR_less(alpha, a[0], "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85438, _mut85439, _mut85440, _mut85441, _mut85442)) {
                    // offset will be positive
                    if (Double.isNaN(previous)) {
                        // we need to wrap around the circle
                        wrapFirst = true;
                    } else {
                        final double previousOffset = AOR_minus(alpha, previous, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85461, _mut85462, _mut85463, _mut85464);
                        final double currentOffset = AOR_minus(a[0], alpha, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85465, _mut85466, _mut85467, _mut85468);
                        if (ROR_less(previousOffset, currentOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85469, _mut85470, _mut85471, _mut85472, _mut85473)) {
                            return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
                        } else {
                            return new BoundaryProjection<Sphere1D>(point, new S1Point(a[0]), currentOffset);
                        }
                    }
                } else if (ROR_less_equals(alpha, a[1], "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85443, _mut85444, _mut85445, _mut85446, _mut85447)) {
                    // offset will be negative
                    final double offset0 = AOR_minus(a[0], alpha, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85448, _mut85449, _mut85450, _mut85451);
                    final double offset1 = AOR_minus(alpha, a[1], "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85452, _mut85453, _mut85454, _mut85455);
                    if (ROR_less(offset0, offset1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85456, _mut85457, _mut85458, _mut85459, _mut85460)) {
                        return new BoundaryProjection<Sphere1D>(point, new S1Point(a[1]), offset1);
                    } else {
                        return new BoundaryProjection<Sphere1D>(point, new S1Point(a[0]), offset0);
                    }
                }
            }
            previous = a[1];
        }
        if (Double.isNaN(previous)) {
            // there are no points at all in the arcs set
            return new BoundaryProjection<Sphere1D>(point, null, MathUtils.TWO_PI);
        } else {
            // somewhere around the 0/2 \pi crossing
            if (wrapFirst) {
                // the test point is between 0 and first
                final double previousOffset = AOR_minus(alpha, (AOR_minus(previous, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85491, _mut85492, _mut85493, _mut85494)), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85495, _mut85496, _mut85497, _mut85498);
                final double currentOffset = AOR_minus(first, alpha, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85499, _mut85500, _mut85501, _mut85502);
                if (ROR_less(previousOffset, currentOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85503, _mut85504, _mut85505, _mut85506, _mut85507)) {
                    return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
                } else {
                    return new BoundaryProjection<Sphere1D>(point, new S1Point(first), currentOffset);
                }
            } else {
                // the test point is between last and 2\pi
                final double previousOffset = AOR_minus(alpha, previous, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85474, _mut85475, _mut85476, _mut85477);
                final double currentOffset = AOR_minus(AOR_plus(first, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85478, _mut85479, _mut85480, _mut85481), alpha, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85482, _mut85483, _mut85484, _mut85485);
                if (ROR_less(previousOffset, currentOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.projectToBoundary_480", _mut85486, _mut85487, _mut85488, _mut85489, _mut85490)) {
                    return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
                } else {
                    return new BoundaryProjection<Sphere1D>(point, new S1Point(first), currentOffset);
                }
            }
        }
    }

    /**
     * Build an ordered list of arcs representing the instance.
     * <p>This method builds this arcs set as an ordered list of
     * {@link Arc Arc} elements. An empty tree will build an empty list
     * while a tree representing the whole circle will build a one
     * element list with bounds set to \( 0 and 2 \pi \).</p>
     * @return a new ordered list containing {@link Arc Arc} elements
     */
    public List<Arc> asList() {
        final List<Arc> list = new ArrayList<Arc>();
        for (final double[] a : this) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.asList_567");
            list.add(new Arc(a[0], a[1], getTolerance()));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The iterator returns the limit angles pairs of sub-arcs in trigonometric order.
     * </p>
     * <p>
     * The iterator does <em>not</em> support the optional {@code remove} operation.
     * </p>
     */
    public Iterator<double[]> iterator() {
        return new SubArcsIterator();
    }

    /**
     * Local iterator for sub-arcs.
     */
    private class SubArcsIterator implements Iterator<double[]> {

        /**
         * Start of the first arc.
         */
        private final BSPTree<Sphere1D> firstStart;

        /**
         * Current node.
         */
        private BSPTree<Sphere1D> current;

        /**
         * Sub-arc no yet returned.
         */
        private double[] pending;

        /**
         * Simple constructor.
         */
        SubArcsIterator() {
            firstStart = getFirstArcStart();
            current = firstStart;
            if (firstStart == null) {
                // all the leaf tree nodes share the same inside/outside status
                if ((Boolean) getFirstLeaf(getTree(false)).getAttribute()) {
                    // it is an inside node, it represents the full circle
                    pending = new double[] { 0, MathUtils.TWO_PI };
                } else {
                    pending = null;
                }
            } else {
                selectPending();
            }
        }

        /**
         * Walk the tree to select the pending sub-arc.
         */
        private void selectPending() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.selectPending_623");
            // look for the start of the arc
            BSPTree<Sphere1D> start = current;
            while ((_mut85508 ? (start != null || !isArcStart(start)) : (start != null && !isArcStart(start)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.selectPending_623");
                start = nextInternalNode(start);
            }
            if (start == null) {
                // we have exhausted the iterator
                current = null;
                pending = null;
                return;
            }
            // look for the end of the arc
            BSPTree<Sphere1D> end = start;
            while ((_mut85509 ? (end != null || !isArcEnd(end)) : (end != null && !isArcEnd(end)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.selectPending_623");
                end = nextInternalNode(end);
            }
            if (end != null) {
                // we have identified the arc
                pending = new double[] { getAngle(start), getAngle(end) };
                // prepare search for next arc
                current = end;
            } else {
                // the final arc wraps around 2\pi, its end is before the first start
                end = firstStart;
                while ((_mut85510 ? (end != null || !isArcEnd(end)) : (end != null && !isArcEnd(end)))) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.selectPending_623");
                    end = previousInternalNode(end);
                }
                if (end == null) {
                    // this should never happen
                    throw new MathInternalError();
                }
                // we have identified the last arc
                pending = new double[] { getAngle(start), AOR_plus(getAngle(end), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.selectPending_623", _mut85511, _mut85512, _mut85513, _mut85514) };
                // there won't be any other arcs
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

    /**
     * Compute the relative position of the instance with respect
     * to an arc.
     * <p>
     * The {@link Side#MINUS} side of the arc is the one covered by the arc.
     * </p>
     * @param arc arc to check instance against
     * @return one of {@link Side#PLUS}, {@link Side#MINUS}, {@link Side#BOTH}
     * or {@link Side#HYPER}
     * @deprecated as of 3.6, replaced with {@link #split(Arc)}.{@link Split#getSide()}
     */
    @Deprecated
    public Side side(final Arc arc) {
        return split(arc).getSide();
    }

    /**
     * Split the instance in two parts by an arc.
     * @param arc splitting arc
     * @return an object containing both the part of the instance
     * on the plus side of the arc and the part of the
     * instance on the minus side of the arc
     */
    public Split split(final Arc arc) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721");
        final List<Double> minus = new ArrayList<Double>();
        final List<Double> plus = new ArrayList<Double>();
        final double reference = AOR_plus(FastMath.PI, arc.getInf(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85515, _mut85516, _mut85517, _mut85518);
        final double arcLength = AOR_minus(arc.getSup(), arc.getInf(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85519, _mut85520, _mut85521, _mut85522);
        for (final double[] a : this) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721");
            final double syncedStart = AOR_minus(MathUtils.normalizeAngle(a[0], reference), arc.getInf(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85523, _mut85524, _mut85525, _mut85526);
            final double arcOffset = AOR_minus(a[0], syncedStart, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85527, _mut85528, _mut85529, _mut85530);
            final double syncedEnd = AOR_minus(a[1], arcOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85531, _mut85532, _mut85533, _mut85534);
            if (ROR_less(syncedStart, arcLength, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85535, _mut85536, _mut85537, _mut85538, _mut85539)) {
                // the start point a[0] is in the minus part of the arc
                minus.add(a[0]);
                if (ROR_greater(syncedEnd, arcLength, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85566, _mut85567, _mut85568, _mut85569, _mut85570)) {
                    // so we leave the minus part and enter the plus part
                    final double minusToPlus = AOR_plus(arcLength, arcOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85571, _mut85572, _mut85573, _mut85574);
                    minus.add(minusToPlus);
                    plus.add(minusToPlus);
                    if (ROR_greater(syncedEnd, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85575, _mut85576, _mut85577, _mut85578, _mut85579)) {
                        // leave the plus part of the arc and enter the minus part again
                        final double plusToMinus = AOR_plus(MathUtils.TWO_PI, arcOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85580, _mut85581, _mut85582, _mut85583);
                        plus.add(plusToMinus);
                        minus.add(plusToMinus);
                        minus.add(a[1]);
                    } else {
                        // the end point a[1] is in the plus part of the arc
                        plus.add(a[1]);
                    }
                } else {
                    // the end point a[1] is in the minus part of the arc
                    minus.add(a[1]);
                }
            } else {
                // the start point a[0] is in the plus part of the arc
                plus.add(a[0]);
                if (ROR_greater(syncedEnd, MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85540, _mut85541, _mut85542, _mut85543, _mut85544)) {
                    // so we leave the plus part and enter the minus part
                    final double plusToMinus = AOR_plus(MathUtils.TWO_PI, arcOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85545, _mut85546, _mut85547, _mut85548);
                    plus.add(plusToMinus);
                    minus.add(plusToMinus);
                    if (ROR_greater(syncedEnd, AOR_plus(MathUtils.TWO_PI, arcLength, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85549, _mut85550, _mut85551, _mut85552), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85553, _mut85554, _mut85555, _mut85556, _mut85557)) {
                        // leave the minus part of the arc and enter the plus part again
                        final double minusToPlus = AOR_plus(AOR_plus(MathUtils.TWO_PI, arcLength, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85558, _mut85559, _mut85560, _mut85561), arcOffset, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.split_721", _mut85562, _mut85563, _mut85564, _mut85565);
                        minus.add(minusToPlus);
                        plus.add(minusToPlus);
                        plus.add(a[1]);
                    } else {
                        // the end point a[1] is in the minus part of the arc
                        minus.add(a[1]);
                    }
                } else {
                    // the end point a[1] is in the plus part of the arc
                    plus.add(a[1]);
                }
            }
        }
        return new Split(createSplitPart(plus), createSplitPart(minus));
    }

    /**
     * Add an arc limit to a BSP tree under construction.
     * @param tree BSP tree under construction
     * @param alpha arc limit
     * @param isStart if true, the limit is the start of an arc
     */
    private void addArcLimit(final BSPTree<Sphere1D> tree, final double alpha, final boolean isStart) {
        final LimitAngle limit = new LimitAngle(new S1Point(alpha), !isStart, getTolerance());
        final BSPTree<Sphere1D> node = tree.getCell(limit.getLocation(), getTolerance());
        if (node.getCut() != null) {
            // this should never happen
            throw new MathInternalError();
        }
        node.insertCut(limit);
        node.setAttribute(null);
        node.getPlus().setAttribute(Boolean.FALSE);
        node.getMinus().setAttribute(Boolean.TRUE);
    }

    /**
     * Create a split part.
     * <p>
     * As per construction, the list of limit angles is known to have
     * an even number of entries, with start angles at even indices and
     * end angles at odd indices.
     * </p>
     * @param limits limit angles of the split part
     * @return split part (may be null)
     */
    private ArcsSet createSplitPart(final List<Double> limits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818");
        if (limits.isEmpty()) {
            return null;
        } else {
            // collapse close limit angles
            for (int i = 0; ROR_less(i, limits.size(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85627, _mut85628, _mut85629, _mut85630, _mut85631); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818");
                final int j = AOR_remainder((AOR_plus(i, 1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85584, _mut85585, _mut85586, _mut85587)), limits.size(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85588, _mut85589, _mut85590, _mut85591);
                final double lA = limits.get(i);
                final double lB = MathUtils.normalizeAngle(limits.get(j), lA);
                if (ROR_less_equals(FastMath.abs(AOR_minus(lB, lA, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85592, _mut85593, _mut85594, _mut85595)), getTolerance(), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85596, _mut85597, _mut85598, _mut85599, _mut85600)) {
                    // the two limits are too close to each other, we remove both of them
                    if (ROR_greater(j, 0, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85601, _mut85602, _mut85603, _mut85604, _mut85605)) {
                        // regular case, the two entries are consecutive ones
                        limits.remove(j);
                        limits.remove(i);
                        i = AOR_minus(i, 1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85623, _mut85624, _mut85625, _mut85626);
                    } else {
                        // we have wrapped around list end
                        final double lEnd = limits.remove(AOR_minus(limits.size(), 1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85606, _mut85607, _mut85608, _mut85609));
                        final double lStart = limits.remove(0);
                        if (limits.isEmpty()) {
                            // the ends were the only limits, is it a full circle or an empty circle?
                            if (ROR_greater(AOR_minus(lEnd, lStart, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85614, _mut85615, _mut85616, _mut85617), FastMath.PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85618, _mut85619, _mut85620, _mut85621, _mut85622)) {
                                // it was full circle
                                return new ArcsSet(new BSPTree<Sphere1D>(Boolean.TRUE), getTolerance());
                            } else {
                                // it was an empty circle
                                return null;
                            }
                        } else {
                            // we need to move this interval end to the end of the list
                            limits.add(AOR_plus(limits.remove(0), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85610, _mut85611, _mut85612, _mut85613));
                        }
                    }
                }
            }
            // build the tree by adding all angular sectors
            BSPTree<Sphere1D> tree = new BSPTree<Sphere1D>(Boolean.FALSE);
            for (int i = 0; ROR_less(i, AOR_minus(limits.size(), 1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85636, _mut85637, _mut85638, _mut85639), "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85640, _mut85641, _mut85642, _mut85643, _mut85644); i += 2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818");
                addArcLimit(tree, limits.get(i), true);
                addArcLimit(tree, limits.get(AOR_plus(i, 1, "org.apache.commons.math3.geometry.spherical.oned.ArcsSet.createSplitPart_818", _mut85632, _mut85633, _mut85634, _mut85635)), false);
            }
            if (tree.getCut() == null) {
                // we did not insert anything
                return null;
            }
            return new ArcsSet(tree, getTolerance());
        }
    }

    /**
     * Class holding the results of the {@link #split split} method.
     */
    public static class Split {

        /**
         * Part of the arcs set on the plus side of the splitting arc.
         */
        private final ArcsSet plus;

        /**
         * Part of the arcs set on the minus side of the splitting arc.
         */
        private final ArcsSet minus;

        /**
         * Build a Split from its parts.
         * @param plus part of the arcs set on the plus side of the
         * splitting arc
         * @param minus part of the arcs set on the minus side of the
         * splitting arc
         */
        private Split(final ArcsSet plus, final ArcsSet minus) {
            this.plus = plus;
            this.minus = minus;
        }

        /**
         * Get the part of the arcs set on the plus side of the splitting arc.
         * @return part of the arcs set on the plus side of the splitting arc
         */
        public ArcsSet getPlus() {
            return plus;
        }

        /**
         * Get the part of the arcs set on the minus side of the splitting arc.
         * @return part of the arcs set on the minus side of the splitting arc
         */
        public ArcsSet getMinus() {
            return minus;
        }

        /**
         * Get the side of the split arc with respect to its splitter.
         * @return {@link Side#PLUS} if only {@link #getPlus()} returns non-null,
         * {@link Side#MINUS} if only {@link #getMinus()} returns non-null,
         * {@link Side#BOTH} if both {@link #getPlus()} and {@link #getMinus()}
         * return non-null or {@link Side#HYPER} if both {@link #getPlus()} and
         * {@link #getMinus()} return null
         * @since 3.6
         */
        public Side getSide() {
            if (plus != null) {
                if (minus != null) {
                    return Side.BOTH;
                } else {
                    return Side.PLUS;
                }
            } else if (minus != null) {
                return Side.MINUS;
            } else {
                return Side.HYPER;
            }
        }
    }

    /**
     * Specialized exception for inconsistent BSP tree state inconsistency.
     * <p>
     * This exception is thrown at {@link ArcsSet} construction time when the
     * {@link org.apache.commons.math3.geometry.partitioning.Region.Location inside/outside}
     * state is not consistent at the 0, \(2 \pi \) crossing.
     * </p>
     */
    public static class InconsistentStateAt2PiWrapping extends MathIllegalArgumentException {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 20140107L;

        /**
         * Simple constructor.
         */
        public InconsistentStateAt2PiWrapping() {
            super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING);
        }
    }
}
