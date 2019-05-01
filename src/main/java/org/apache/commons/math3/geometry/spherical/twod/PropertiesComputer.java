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
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Visitor computing geometrical properties.
 * @since 3.3
 */
class PropertiesComputer implements BSPTreeVisitor<Sphere2D> {

    @Conditional
    public static boolean _mut85674 = false, _mut85675 = false, _mut85676 = false, _mut85677 = false, _mut85678 = false, _mut85679 = false, _mut85680 = false, _mut85681 = false, _mut85682 = false, _mut85683 = false, _mut85684 = false, _mut85685 = false, _mut85686 = false, _mut85687 = false, _mut85688 = false, _mut85689 = false, _mut85690 = false, _mut85691 = false, _mut85692 = false, _mut85693 = false, _mut85694 = false, _mut85695 = false, _mut85696 = false, _mut85697 = false, _mut85698 = false, _mut85699 = false, _mut85700 = false, _mut85701 = false, _mut85702 = false, _mut85703 = false, _mut85704 = false, _mut85705 = false, _mut85706 = false, _mut85707 = false, _mut85708 = false, _mut85709 = false, _mut85710 = false, _mut85711 = false, _mut85712 = false;

    /**
     * Tolerance below which points are consider to be identical.
     */
    private final double tolerance;

    /**
     * Summed area.
     */
    private double summedArea;

    /**
     * Summed barycenter.
     */
    private Vector3D summedBarycenter;

    /**
     * List of points strictly inside convex cells.
     */
    private final List<Vector3D> convexCellsInsidePoints;

    /**
     * Simple constructor.
     * @param tolerance below which points are consider to be identical
     */
    PropertiesComputer(final double tolerance) {
        this.tolerance = tolerance;
        this.summedArea = 0;
        this.summedBarycenter = Vector3D.ZERO;
        this.convexCellsInsidePoints = new ArrayList<Vector3D>();
    }

    /**
     * {@inheritDoc}
     */
    public Order visitOrder(final BSPTree<Sphere2D> node) {
        return Order.MINUS_SUB_PLUS;
    }

    /**
     * {@inheritDoc}
     */
    public void visitInternalNode(final BSPTree<Sphere2D> node) {
    }

    /**
     * {@inheritDoc}
     */
    public void visitLeafNode(final BSPTree<Sphere2D> node) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.visitLeafNode_67");
        if ((Boolean) node.getAttribute()) {
            // transform this inside leaf cell into a simple convex polygon
            final SphericalPolygonsSet convex = new SphericalPolygonsSet(node.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, null), tolerance);
            // extract the start of the single loop boundary of the convex cell
            final List<Vertex> boundary = convex.getBoundaryLoops();
            if (ROR_not_equals(boundary.size(), 1, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.visitLeafNode_67", _mut85674, _mut85675, _mut85676, _mut85677, _mut85678)) {
                // this should never happen
                throw new MathInternalError();
            }
            // compute the geometrical properties of the convex cell
            final double area = convexCellArea(boundary.get(0));
            final Vector3D barycenter = convexCellBarycenter(boundary.get(0));
            convexCellsInsidePoints.add(barycenter);
            // add the cell contribution to the global properties
            summedArea += area;
            summedBarycenter = new Vector3D(1, summedBarycenter, area, barycenter);
        }
    }

    /**
     * Compute convex cell area.
     * @param start start vertex of the convex cell boundary
     * @return area
     */
    private double convexCellArea(final Vertex start) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100");
        int n = 0;
        double sum = 0;
        // loop around the cell
        for (Edge e = start.getOutgoing(); (_mut85689 ? (ROR_equals(n, 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85684, _mut85685, _mut85686, _mut85687, _mut85688) && e.getStart() != start) : (ROR_equals(n, 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85684, _mut85685, _mut85686, _mut85687, _mut85688) || e.getStart() != start)); e = e.getEnd().getOutgoing()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100");
            // find path interior angle at vertex
            final Vector3D previousPole = e.getCircle().getPole();
            final Vector3D nextPole = e.getEnd().getOutgoing().getCircle().getPole();
            final Vector3D point = e.getEnd().getLocation().getVector();
            double alpha = FastMath.atan2(Vector3D.dotProduct(nextPole, Vector3D.crossProduct(point, previousPole)), -Vector3D.dotProduct(nextPole, previousPole));
            if (ROR_less(alpha, 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85679, _mut85680, _mut85681, _mut85682, _mut85683)) {
                alpha += MathUtils.TWO_PI;
            }
            sum += alpha;
            n++;
        }
        // book available from project Gutenberg at http://www.gutenberg.org/ebooks/19770
        return AOR_minus(sum, AOR_multiply((AOR_minus(n, 2, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85690, _mut85691, _mut85692, _mut85693)), FastMath.PI, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85694, _mut85695, _mut85696, _mut85697), "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellArea_100", _mut85698, _mut85699, _mut85700, _mut85701);
    }

    /**
     * Compute convex cell barycenter.
     * @param start start vertex of the convex cell boundary
     * @return barycenter
     */
    private Vector3D convexCellBarycenter(final Vertex start) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellBarycenter_133");
        int n = 0;
        Vector3D sumB = Vector3D.ZERO;
        // loop around the cell
        for (Edge e = start.getOutgoing(); (_mut85707 ? (ROR_equals(n, 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellBarycenter_133", _mut85702, _mut85703, _mut85704, _mut85705, _mut85706) && e.getStart() != start) : (ROR_equals(n, 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellBarycenter_133", _mut85702, _mut85703, _mut85704, _mut85705, _mut85706) || e.getStart() != start)); e = e.getEnd().getOutgoing()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.convexCellBarycenter_133");
            sumB = new Vector3D(1, sumB, e.getLength(), e.getCircle().getPole());
            n++;
        }
        return sumB.normalize();
    }

    /**
     * Get the area.
     * @return area
     */
    public double getArea() {
        return summedArea;
    }

    /**
     * Get the barycenter.
     * @return barycenter
     */
    public S2Point getBarycenter() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.getBarycenter_158");
        if (ROR_equals(summedBarycenter.getNormSq(), 0, "org.apache.commons.math3.geometry.spherical.twod.PropertiesComputer.getBarycenter_158", _mut85708, _mut85709, _mut85710, _mut85711, _mut85712)) {
            return S2Point.NaN;
        } else {
            return new S2Point(summedBarycenter);
        }
    }

    /**
     * Get the points strictly inside convex cells.
     * @return points strictly inside convex cells
     */
    public List<Vector3D> getConvexCellsInsidePoints() {
        return convexCellsInsidePoints;
    }
}
