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
package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Extractor for {@link PolygonsSet polyhedrons sets} outlines.
 * <p>This class extracts the 2D outlines from {{@link PolygonsSet
 * polyhedrons sets} in a specified projection plane.</p>
 * @since 3.0
 */
public class OutlineExtractor {

    @Conditional
    public static boolean _mut79801 = false, _mut79802 = false, _mut79803 = false, _mut79804 = false, _mut79805 = false, _mut79806 = false, _mut79807 = false, _mut79808 = false, _mut79809 = false, _mut79810 = false, _mut79811 = false, _mut79812 = false, _mut79813 = false, _mut79814 = false, _mut79815 = false, _mut79816 = false, _mut79817 = false, _mut79818 = false, _mut79819 = false, _mut79820 = false, _mut79821 = false, _mut79822 = false, _mut79823 = false, _mut79824 = false, _mut79825 = false, _mut79826 = false, _mut79827 = false, _mut79828 = false, _mut79829 = false, _mut79830 = false, _mut79831 = false, _mut79832 = false, _mut79833 = false, _mut79834 = false, _mut79835 = false, _mut79836 = false, _mut79837 = false, _mut79838 = false, _mut79839 = false, _mut79840 = false, _mut79841 = false, _mut79842 = false, _mut79843 = false, _mut79844 = false, _mut79845 = false, _mut79846 = false, _mut79847 = false, _mut79848 = false, _mut79849 = false, _mut79850 = false, _mut79851 = false, _mut79852 = false, _mut79853 = false, _mut79854 = false, _mut79855 = false, _mut79856 = false, _mut79857 = false, _mut79858 = false, _mut79859 = false, _mut79860 = false, _mut79861 = false, _mut79862 = false, _mut79863 = false, _mut79864 = false, _mut79865 = false, _mut79866 = false, _mut79867 = false, _mut79868 = false, _mut79869 = false, _mut79870 = false, _mut79871 = false, _mut79872 = false, _mut79873 = false, _mut79874 = false, _mut79875 = false, _mut79876 = false, _mut79877 = false, _mut79878 = false, _mut79879 = false, _mut79880 = false, _mut79881 = false, _mut79882 = false, _mut79883 = false, _mut79884 = false, _mut79885 = false, _mut79886 = false, _mut79887 = false, _mut79888 = false, _mut79889 = false, _mut79890 = false, _mut79891 = false, _mut79892 = false, _mut79893 = false, _mut79894 = false, _mut79895 = false, _mut79896 = false, _mut79897 = false, _mut79898 = false, _mut79899 = false, _mut79900 = false, _mut79901 = false, _mut79902 = false, _mut79903 = false, _mut79904 = false, _mut79905 = false, _mut79906 = false, _mut79907 = false, _mut79908 = false, _mut79909 = false, _mut79910 = false, _mut79911 = false, _mut79912 = false, _mut79913 = false, _mut79914 = false, _mut79915 = false, _mut79916 = false, _mut79917 = false, _mut79918 = false, _mut79919 = false, _mut79920 = false, _mut79921 = false, _mut79922 = false, _mut79923 = false, _mut79924 = false, _mut79925 = false, _mut79926 = false, _mut79927 = false, _mut79928 = false, _mut79929 = false, _mut79930 = false, _mut79931 = false, _mut79932 = false, _mut79933 = false, _mut79934 = false, _mut79935 = false, _mut79936 = false, _mut79937 = false, _mut79938 = false, _mut79939 = false, _mut79940 = false, _mut79941 = false, _mut79942 = false, _mut79943 = false, _mut79944 = false, _mut79945 = false, _mut79946 = false, _mut79947 = false, _mut79948 = false, _mut79949 = false, _mut79950 = false, _mut79951 = false, _mut79952 = false, _mut79953 = false, _mut79954 = false, _mut79955 = false, _mut79956 = false, _mut79957 = false, _mut79958 = false, _mut79959 = false, _mut79960 = false, _mut79961 = false, _mut79962 = false, _mut79963 = false, _mut79964 = false, _mut79965 = false, _mut79966 = false, _mut79967 = false, _mut79968 = false, _mut79969 = false, _mut79970 = false, _mut79971 = false, _mut79972 = false, _mut79973 = false, _mut79974 = false, _mut79975 = false, _mut79976 = false, _mut79977 = false, _mut79978 = false, _mut79979 = false, _mut79980 = false, _mut79981 = false, _mut79982 = false, _mut79983 = false, _mut79984 = false, _mut79985 = false, _mut79986 = false, _mut79987 = false, _mut79988 = false, _mut79989 = false, _mut79990 = false, _mut79991 = false, _mut79992 = false, _mut79993 = false, _mut79994 = false, _mut79995 = false, _mut79996 = false, _mut79997 = false, _mut79998 = false, _mut79999 = false, _mut80000 = false, _mut80001 = false, _mut80002 = false, _mut80003 = false, _mut80004 = false, _mut80005 = false, _mut80006 = false, _mut80007 = false, _mut80008 = false, _mut80009 = false, _mut80010 = false, _mut80011 = false, _mut80012 = false, _mut80013 = false, _mut80014 = false, _mut80015 = false, _mut80016 = false, _mut80017 = false;

    /**
     * Abscissa axis of the projection plane.
     */
    private Vector3D u;

    /**
     * Ordinate axis of the projection plane.
     */
    private Vector3D v;

    /**
     * Normal of the projection plane (viewing direction).
     */
    private Vector3D w;

    /**
     * Build an extractor for a specific projection plane.
     * @param u abscissa axis of the projection point
     * @param v ordinate axis of the projection point
     */
    public OutlineExtractor(final Vector3D u, final Vector3D v) {
        this.u = u;
        this.v = v;
        w = Vector3D.crossProduct(u, v);
    }

    /**
     * Extract the outline of a polyhedrons set.
     * @param polyhedronsSet polyhedrons set whose outline must be extracted
     * @return an outline, as an array of loops.
     */
    public Vector2D[][] getOutline(final PolyhedronsSet polyhedronsSet) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63");
        // project all boundary facets into one polygons set
        final BoundaryProjector projector = new BoundaryProjector(polyhedronsSet.getTolerance());
        polyhedronsSet.getTree(true).visit(projector);
        final PolygonsSet projected = projector.getProjected();
        // Remove the spurious intermediate vertices from the outline
        final Vector2D[][] outline = projected.getVertices();
        for (int i = 0; ROR_less(i, outline.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79824, _mut79825, _mut79826, _mut79827, _mut79828); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63");
            final Vector2D[] rawLoop = outline[i];
            int end = rawLoop.length;
            int j = 0;
            while (ROR_less(j, end, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79814, _mut79815, _mut79816, _mut79817, _mut79818)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63");
                if (pointIsBetween(rawLoop, end, j)) {
                    // the point should be removed
                    for (int k = j; ROR_less(k, (AOR_minus(end, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79805, _mut79806, _mut79807, _mut79808)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79809, _mut79810, _mut79811, _mut79812, _mut79813); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63");
                        rawLoop[k] = rawLoop[AOR_plus(k, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79801, _mut79802, _mut79803, _mut79804)];
                    }
                    --end;
                } else {
                    // the point remains in the loop
                    ++j;
                }
            }
            if (ROR_not_equals(end, rawLoop.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.getOutline_63", _mut79819, _mut79820, _mut79821, _mut79822, _mut79823)) {
                // resize the array
                outline[i] = new Vector2D[end];
                System.arraycopy(rawLoop, 0, outline[i], 0, end);
            }
        }
        return outline;
    }

    /**
     * Check if a point is geometrically between its neighbor in an array.
     * <p>The neighbors are computed considering the array is a loop
     * (i.e. point at index (n-1) is before point at index 0)</p>
     * @param loop points array
     * @param n number of points to consider in the array
     * @param i index of the point to check (must be between 0 and n-1)
     * @return true if the point is exactly between its neighbors
     */
    private boolean pointIsBetween(final Vector2D[] loop, final int n, final int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107");
        final Vector2D previous = loop[AOR_remainder((AOR_minus(AOR_plus(i, n, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79829, _mut79830, _mut79831, _mut79832), 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79833, _mut79834, _mut79835, _mut79836)), n, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79837, _mut79838, _mut79839, _mut79840)];
        final Vector2D current = loop[i];
        final Vector2D next = loop[AOR_remainder((AOR_plus(i, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79841, _mut79842, _mut79843, _mut79844)), n, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79845, _mut79846, _mut79847, _mut79848)];
        final double dx1 = AOR_minus(current.getX(), previous.getX(), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79849, _mut79850, _mut79851, _mut79852);
        final double dy1 = AOR_minus(current.getY(), previous.getY(), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79853, _mut79854, _mut79855, _mut79856);
        final double dx2 = AOR_minus(next.getX(), current.getX(), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79857, _mut79858, _mut79859, _mut79860);
        final double dy2 = AOR_minus(next.getY(), current.getY(), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79861, _mut79862, _mut79863, _mut79864);
        final double cross = AOR_minus(AOR_multiply(dx1, dy2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79865, _mut79866, _mut79867, _mut79868), AOR_multiply(dx2, dy1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79869, _mut79870, _mut79871, _mut79872), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79873, _mut79874, _mut79875, _mut79876);
        final double dot = AOR_plus(AOR_multiply(dx1, dx2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79877, _mut79878, _mut79879, _mut79880), AOR_multiply(dy1, dy2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79881, _mut79882, _mut79883, _mut79884), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79885, _mut79886, _mut79887, _mut79888);
        final double d1d2 = FastMath.sqrt(AOR_multiply((AOR_plus(AOR_multiply(dx1, dx1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79889, _mut79890, _mut79891, _mut79892), AOR_multiply(dy1, dy1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79893, _mut79894, _mut79895, _mut79896), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79897, _mut79898, _mut79899, _mut79900)), (AOR_plus(AOR_multiply(dx2, dx2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79901, _mut79902, _mut79903, _mut79904), AOR_multiply(dy2, dy2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79905, _mut79906, _mut79907, _mut79908), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79909, _mut79910, _mut79911, _mut79912)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79913, _mut79914, _mut79915, _mut79916));
        return (_mut79931 ? ((ROR_less_equals(FastMath.abs(cross), (AOR_multiply(1.0e-6, d1d2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79917, _mut79918, _mut79919, _mut79920)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79921, _mut79922, _mut79923, _mut79924, _mut79925)) || (ROR_greater_equals(dot, 0.0, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79926, _mut79927, _mut79928, _mut79929, _mut79930))) : ((ROR_less_equals(FastMath.abs(cross), (AOR_multiply(1.0e-6, d1d2, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79917, _mut79918, _mut79919, _mut79920)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79921, _mut79922, _mut79923, _mut79924, _mut79925)) && (ROR_greater_equals(dot, 0.0, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.pointIsBetween_107", _mut79926, _mut79927, _mut79928, _mut79929, _mut79930))));
    }

    /**
     * Visitor projecting the boundary facets on a plane.
     */
    private class BoundaryProjector implements BSPTreeVisitor<Euclidean3D> {

        /**
         * Projection of the polyhedrons set on the plane.
         */
        private PolygonsSet projected;

        /**
         * Tolerance below which points are considered identical.
         */
        private final double tolerance;

        /**
         * Simple constructor.
         * @param tolerance tolerance below which points are considered identical
         */
        BoundaryProjector(final double tolerance) {
            this.projected = new PolygonsSet(new BSPTree<Euclidean2D>(Boolean.FALSE), tolerance);
            this.tolerance = tolerance;
        }

        /**
         * {@inheritDoc}
         */
        public Order visitOrder(final BSPTree<Euclidean3D> node) {
            return Order.MINUS_SUB_PLUS;
        }

        /**
         * {@inheritDoc}
         */
        public void visitInternalNode(final BSPTree<Euclidean3D> node) {
            @SuppressWarnings("unchecked")
            final BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute<Euclidean3D>) node.getAttribute();
            if (attribute.getPlusOutside() != null) {
                addContribution(attribute.getPlusOutside(), false);
            }
            if (attribute.getPlusInside() != null) {
                addContribution(attribute.getPlusInside(), true);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void visitLeafNode(final BSPTree<Euclidean3D> node) {
        }

        /**
         * Add he contribution of a boundary facet.
         * @param facet boundary facet
         * @param reversed if true, the facet has the inside on its plus side
         */
        private void addContribution(final SubHyperplane<Euclidean3D> facet, final boolean reversed) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
            // extract the vertices of the facet
            @SuppressWarnings("unchecked")
            final AbstractSubHyperplane<Euclidean3D, Euclidean2D> absFacet = (AbstractSubHyperplane<Euclidean3D, Euclidean2D>) facet;
            final Plane plane = (Plane) facet.getHyperplane();
            final double scal = plane.getNormal().dotProduct(w);
            if (ROR_greater(FastMath.abs(scal), 1.0e-3, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79932, _mut79933, _mut79934, _mut79935, _mut79936)) {
                Vector2D[][] vertices = ((PolygonsSet) absFacet.getRemainingRegion()).getVertices();
                if ((ROR_less(scal, 0, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79937, _mut79938, _mut79939, _mut79940, _mut79941)) ^ reversed) {
                    // we need to invert its boundary orientation
                    final Vector2D[][] newVertices = new Vector2D[vertices.length][];
                    for (int i = 0; ROR_less(i, vertices.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79964, _mut79965, _mut79966, _mut79967, _mut79968); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
                        final Vector2D[] loop = vertices[i];
                        final Vector2D[] newLoop = new Vector2D[loop.length];
                        if (loop[0] == null) {
                            newLoop[0] = null;
                            for (int j = 1; ROR_less(j, loop.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79959, _mut79960, _mut79961, _mut79962, _mut79963); ++j) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
                                newLoop[j] = loop[AOR_minus(loop.length, j, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79955, _mut79956, _mut79957, _mut79958)];
                            }
                        } else {
                            for (int j = 0; ROR_less(j, loop.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79950, _mut79951, _mut79952, _mut79953, _mut79954); ++j) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
                                newLoop[j] = loop[AOR_minus(loop.length, (AOR_plus(j, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79942, _mut79943, _mut79944, _mut79945)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79946, _mut79947, _mut79948, _mut79949)];
                            }
                        }
                        newVertices[i] = newLoop;
                    }
                    // use the reverted vertices
                    vertices = newVertices;
                }
                // compute the projection of the facet in the outline plane
                final ArrayList<SubHyperplane<Euclidean2D>> edges = new ArrayList<SubHyperplane<Euclidean2D>>();
                for (Vector2D[] loop : vertices) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
                    final boolean closed = loop[0] != null;
                    int previous = closed ? (AOR_minus(loop.length, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79969, _mut79970, _mut79971, _mut79972)) : 1;
                    Vector3D previous3D = plane.toSpace((Point<Euclidean2D>) loop[previous]);
                    int current = AOR_remainder((AOR_plus(previous, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79973, _mut79974, _mut79975, _mut79976)), loop.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79977, _mut79978, _mut79979, _mut79980);
                    Vector2D pPoint = new Vector2D(previous3D.dotProduct(u), previous3D.dotProduct(v));
                    while (ROR_less(current, loop.length, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut80013, _mut80014, _mut80015, _mut80016, _mut80017)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164");
                        final Vector3D current3D = plane.toSpace((Point<Euclidean2D>) loop[current]);
                        final Vector2D cPoint = new Vector2D(current3D.dotProduct(u), current3D.dotProduct(v));
                        final org.apache.commons.math3.geometry.euclidean.twod.Line line = new org.apache.commons.math3.geometry.euclidean.twod.Line(pPoint, cPoint, tolerance);
                        SubHyperplane<Euclidean2D> edge = line.wholeHyperplane();
                        if ((_mut79986 ? (closed && (ROR_not_equals(previous, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79981, _mut79982, _mut79983, _mut79984, _mut79985))) : (closed || (ROR_not_equals(previous, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79981, _mut79982, _mut79983, _mut79984, _mut79985))))) {
                            // it defines one bounding point of the edge
                            final double angle = AOR_plus(line.getAngle(), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79987, _mut79988, _mut79989, _mut79990), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79991, _mut79992, _mut79993, _mut79994);
                            final org.apache.commons.math3.geometry.euclidean.twod.Line l = new org.apache.commons.math3.geometry.euclidean.twod.Line(pPoint, angle, tolerance);
                            edge = edge.split(l).getPlus();
                        }
                        if ((_mut80004 ? (closed && (ROR_not_equals(current, (AOR_minus(loop.length, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79995, _mut79996, _mut79997, _mut79998)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79999, _mut80000, _mut80001, _mut80002, _mut80003))) : (closed || (ROR_not_equals(current, (AOR_minus(loop.length, 1, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79995, _mut79996, _mut79997, _mut79998)), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut79999, _mut80000, _mut80001, _mut80002, _mut80003))))) {
                            // it defines one bounding point of the edge
                            final double angle = AOR_plus(line.getAngle(), AOR_multiply(0.5, FastMath.PI, "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut80005, _mut80006, _mut80007, _mut80008), "org.apache.commons.math3.geometry.euclidean.threed.OutlineExtractor.addContribution_164", _mut80009, _mut80010, _mut80011, _mut80012);
                            final org.apache.commons.math3.geometry.euclidean.twod.Line l = new org.apache.commons.math3.geometry.euclidean.twod.Line(cPoint, angle, tolerance);
                            edge = edge.split(l).getMinus();
                        }
                        edges.add(edge);
                        previous = current++;
                        previous3D = current3D;
                        pPoint = cPoint;
                    }
                }
                final PolygonsSet projectedFacet = new PolygonsSet(edges, tolerance);
                // add the contribution of the facet to the global outline
                projected = (PolygonsSet) new RegionFactory<Euclidean2D>().union(projected, projectedFacet);
            }
        }

        /**
         * Get the projection of the polyhedrons set on the plane.
         * @return projection of the polyhedrons set on the plane
         */
        public PolygonsSet getProjected() {
            return projected;
        }
    }
}
