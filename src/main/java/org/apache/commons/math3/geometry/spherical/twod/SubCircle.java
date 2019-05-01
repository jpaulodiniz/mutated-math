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

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a sub-hyperplane for {@link Circle}.
 * @since 3.3
 */
public class SubCircle extends AbstractSubHyperplane<Sphere2D, Sphere1D> {

    @Conditional
    public static boolean _mut85713 = false, _mut85714 = false, _mut85715 = false, _mut85716 = false, _mut85717 = false, _mut85718 = false, _mut85719 = false, _mut85720 = false, _mut85721 = false, _mut85722 = false, _mut85723 = false, _mut85724 = false, _mut85725 = false, _mut85726 = false, _mut85727 = false;

    /**
     * Simple constructor.
     * @param hyperplane underlying hyperplane
     * @param remainingRegion remaining region of the hyperplane
     */
    public SubCircle(final Hyperplane<Sphere2D> hyperplane, final Region<Sphere1D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractSubHyperplane<Sphere2D, Sphere1D> buildNew(final Hyperplane<Sphere2D> hyperplane, final Region<Sphere1D> remainingRegion) {
        return new SubCircle(hyperplane, remainingRegion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SplitSubHyperplane<Sphere2D> split(final Hyperplane<Sphere2D> hyperplane) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50");
        final Circle thisCircle = (Circle) getHyperplane();
        final Circle otherCircle = (Circle) hyperplane;
        final double angle = Vector3D.angle(thisCircle.getPole(), otherCircle.getPole());
        if ((_mut85727 ? (ROR_less(angle, thisCircle.getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85713, _mut85714, _mut85715, _mut85716, _mut85717) && ROR_greater(angle, AOR_minus(FastMath.PI, thisCircle.getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85718, _mut85719, _mut85720, _mut85721), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85722, _mut85723, _mut85724, _mut85725, _mut85726)) : (ROR_less(angle, thisCircle.getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85713, _mut85714, _mut85715, _mut85716, _mut85717) || ROR_greater(angle, AOR_minus(FastMath.PI, thisCircle.getTolerance(), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85718, _mut85719, _mut85720, _mut85721), "org.apache.commons.math3.geometry.spherical.twod.SubCircle.split_50", _mut85722, _mut85723, _mut85724, _mut85725, _mut85726)))) {
            // the two circles are aligned or opposite
            return new SplitSubHyperplane<Sphere2D>(null, null);
        } else {
            // the two circles intersect each other
            final Arc arc = thisCircle.getInsideArc(otherCircle);
            final ArcsSet.Split split = ((ArcsSet) getRemainingRegion()).split(arc);
            final ArcsSet plus = split.getPlus();
            final ArcsSet minus = split.getMinus();
            return new SplitSubHyperplane<Sphere2D>(plus == null ? null : new SubCircle(thisCircle.copySelf(), plus), minus == null ? null : new SubCircle(thisCircle.copySelf(), minus));
        }
    }
}
