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

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a sub-hyperplane for {@link Plane}.
 * @since 3.0
 */
public class SubPlane extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {

    @Conditional
    public static boolean _mut80734 = false, _mut80735 = false, _mut80736 = false, _mut80737 = false, _mut80738 = false, _mut80739 = false, _mut80740 = false, _mut80741 = false, _mut80742 = false, _mut80743 = false, _mut80744 = false, _mut80745 = false, _mut80746 = false, _mut80747 = false, _mut80748 = false;

    /**
     * Simple constructor.
     * @param hyperplane underlying hyperplane
     * @param remainingRegion remaining region of the hyperplane
     */
    public SubPlane(final Hyperplane<Euclidean3D> hyperplane, final Region<Euclidean2D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(final Hyperplane<Euclidean3D> hyperplane, final Region<Euclidean2D> remainingRegion) {
        return new SubPlane(hyperplane, remainingRegion);
    }

    /**
     * Split the instance in two parts by an hyperplane.
     * @param hyperplane splitting hyperplane
     * @return an object containing both the part of the instance
     * on the plus side of the instance and the part of the
     * instance on the minus side of the instance
     */
    @Override
    public SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SubPlane.split_58");
        final Plane otherPlane = (Plane) hyperplane;
        final Plane thisPlane = (Plane) getHyperplane();
        final Line inter = otherPlane.intersection(thisPlane);
        final double tolerance = thisPlane.getTolerance();
        if (inter == null) {
            // the hyperplanes are parallel
            final double global = otherPlane.getOffset(thisPlane);
            if (ROR_less(global, -tolerance, "org.apache.commons.math3.geometry.euclidean.threed.SubPlane.split_58", _mut80734, _mut80735, _mut80736, _mut80737, _mut80738)) {
                return new SplitSubHyperplane<Euclidean3D>(null, this);
            } else if (ROR_greater(global, tolerance, "org.apache.commons.math3.geometry.euclidean.threed.SubPlane.split_58", _mut80739, _mut80740, _mut80741, _mut80742, _mut80743)) {
                return new SplitSubHyperplane<Euclidean3D>(this, null);
            } else {
                return new SplitSubHyperplane<Euclidean3D>(null, null);
            }
        }
        // the hyperplanes do intersect
        Vector2D p = thisPlane.toSubSpace((Point<Euclidean3D>) inter.toSpace((Point<Euclidean1D>) Vector1D.ZERO));
        Vector2D q = thisPlane.toSubSpace((Point<Euclidean3D>) inter.toSpace((Point<Euclidean1D>) Vector1D.ONE));
        Vector3D crossP = Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
        if (ROR_less(crossP.dotProduct(otherPlane.getNormal()), 0, "org.apache.commons.math3.geometry.euclidean.threed.SubPlane.split_58", _mut80744, _mut80745, _mut80746, _mut80747, _mut80748)) {
            final Vector2D tmp = p;
            p = q;
            q = tmp;
        }
        final SubHyperplane<Euclidean2D> l2DMinus = new org.apache.commons.math3.geometry.euclidean.twod.Line(p, q, tolerance).wholeHyperplane();
        final SubHyperplane<Euclidean2D> l2DPlus = new org.apache.commons.math3.geometry.euclidean.twod.Line(q, p, tolerance).wholeHyperplane();
        final BSPTree<Euclidean2D> splitTree = getRemainingRegion().getTree(false).split(l2DMinus);
        final BSPTree<Euclidean2D> plusTree = getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree<Euclidean2D>(Boolean.FALSE) : new BSPTree<Euclidean2D>(l2DPlus, new BSPTree<Euclidean2D>(Boolean.FALSE), splitTree.getPlus(), null);
        final BSPTree<Euclidean2D> minusTree = getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree<Euclidean2D>(Boolean.FALSE) : new BSPTree<Euclidean2D>(l2DMinus, new BSPTree<Euclidean2D>(Boolean.FALSE), splitTree.getMinus(), null);
        return new SplitSubHyperplane<Euclidean3D>(new SubPlane(thisPlane.copySelf(), new PolygonsSet(plusTree, tolerance)), new SubPlane(thisPlane.copySelf(), new PolygonsSet(minusTree, tolerance)));
    }
}
