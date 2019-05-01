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

import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class generating an enclosing ball from its support points.
 * @since 3.3
 */
public class DiskGenerator implements SupportBallGenerator<Euclidean2D, Vector2D> {

    @Conditional
    public static boolean _mut84691 = false, _mut84692 = false, _mut84693 = false, _mut84694 = false, _mut84695 = false, _mut84696 = false, _mut84697 = false, _mut84698 = false, _mut84699 = false, _mut84700 = false, _mut84701 = false, _mut84702 = false, _mut84703 = false, _mut84704 = false, _mut84705 = false, _mut84706 = false, _mut84707 = false, _mut84708 = false, _mut84709 = false;

    /**
     * {@inheritDoc}
     */
    public EnclosingBall<Euclidean2D, Vector2D> ballOnSupport(final List<Vector2D> support) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator.ballOnSupport_32");
        if (ROR_less(support.size(), 1, "org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator.ballOnSupport_32", _mut84691, _mut84692, _mut84693, _mut84694, _mut84695)) {
            return new EnclosingBall<Euclidean2D, Vector2D>(Vector2D.ZERO, Double.NEGATIVE_INFINITY);
        } else {
            final Vector2D vA = support.get(0);
            if (ROR_less(support.size(), 2, "org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator.ballOnSupport_32", _mut84696, _mut84697, _mut84698, _mut84699, _mut84700)) {
                return new EnclosingBall<Euclidean2D, Vector2D>(vA, 0, vA);
            } else {
                final Vector2D vB = support.get(1);
                if (ROR_less(support.size(), 3, "org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator.ballOnSupport_32", _mut84701, _mut84702, _mut84703, _mut84704, _mut84705)) {
                    return new EnclosingBall<Euclidean2D, Vector2D>(new Vector2D(0.5, vA, 0.5, vB), AOR_multiply(0.5, vA.distance(vB), "org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator.ballOnSupport_32", _mut84706, _mut84707, _mut84708, _mut84709), vA, vB);
                } else {
                    final Vector2D vC = support.get(2);
                    // filled with 1.0, hence simplifying the computation
                    final BigFraction[] c2 = new BigFraction[] { new BigFraction(vA.getX()), new BigFraction(vB.getX()), new BigFraction(vC.getX()) };
                    final BigFraction[] c3 = new BigFraction[] { new BigFraction(vA.getY()), new BigFraction(vB.getY()), new BigFraction(vC.getY()) };
                    final BigFraction[] c1 = new BigFraction[] { c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])), c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])), c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])) };
                    final BigFraction twoM11 = minor(c2, c3).multiply(2);
                    final BigFraction m12 = minor(c1, c3);
                    final BigFraction m13 = minor(c1, c2);
                    final BigFraction centerX = m12.divide(twoM11);
                    final BigFraction centerY = m13.divide(twoM11).negate();
                    final BigFraction dx = c2[0].subtract(centerX);
                    final BigFraction dy = c3[0].subtract(centerY);
                    final BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy));
                    return new EnclosingBall<Euclidean2D, Vector2D>(new Vector2D(centerX.doubleValue(), centerY.doubleValue()), FastMath.sqrt(r2.doubleValue()), vA, vB, vC);
                }
            }
        }
    }

    /**
     * Compute a dimension 3 minor, when 3<sup>d</sup> column is known to be filled with 1.0.
     * @param c1 first column
     * @param c2 second column
     * @return value of the minor computed has an exact fraction
     */
    private BigFraction minor(final BigFraction[] c1, final BigFraction[] c2) {
        return c2[0].multiply(c1[2].subtract(c1[1])).add(c2[1].multiply(c1[0].subtract(c1[2]))).add(c2[2].multiply(c1[1].subtract(c1[0])));
    }
}
