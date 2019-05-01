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

import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class generating an enclosing ball from its support points.
 * @since 3.3
 */
public class SphereGenerator implements SupportBallGenerator<Euclidean3D, Vector3D> {

    @Conditional
    public static boolean _mut79030 = false, _mut79031 = false, _mut79032 = false, _mut79033 = false, _mut79034 = false, _mut79035 = false, _mut79036 = false, _mut79037 = false, _mut79038 = false, _mut79039 = false, _mut79040 = false, _mut79041 = false, _mut79042 = false, _mut79043 = false, _mut79044 = false, _mut79045 = false, _mut79046 = false, _mut79047 = false, _mut79048 = false, _mut79049 = false, _mut79050 = false, _mut79051 = false, _mut79052 = false, _mut79053 = false, _mut79054 = false, _mut79055 = false, _mut79056 = false, _mut79057 = false, _mut79058 = false, _mut79059 = false, _mut79060 = false, _mut79061 = false, _mut79062 = false, _mut79063 = false, _mut79064 = false, _mut79065 = false;

    /**
     * {@inheritDoc}
     */
    public EnclosingBall<Euclidean3D, Vector3D> ballOnSupport(final List<Vector3D> support) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36");
        if (ROR_less(support.size(), 1, "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79030, _mut79031, _mut79032, _mut79033, _mut79034)) {
            return new EnclosingBall<Euclidean3D, Vector3D>(Vector3D.ZERO, Double.NEGATIVE_INFINITY);
        } else {
            final Vector3D vA = support.get(0);
            if (ROR_less(support.size(), 2, "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79035, _mut79036, _mut79037, _mut79038, _mut79039)) {
                return new EnclosingBall<Euclidean3D, Vector3D>(vA, 0, vA);
            } else {
                final Vector3D vB = support.get(1);
                if (ROR_less(support.size(), 3, "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79040, _mut79041, _mut79042, _mut79043, _mut79044)) {
                    return new EnclosingBall<Euclidean3D, Vector3D>(new Vector3D(0.5, vA, 0.5, vB), AOR_multiply(0.5, vA.distance(vB), "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79062, _mut79063, _mut79064, _mut79065), vA, vB);
                } else {
                    final Vector3D vC = support.get(2);
                    if (ROR_less(support.size(), 4, "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79045, _mut79046, _mut79047, _mut79048, _mut79049)) {
                        // delegate to 2D disk generator
                        final Plane p = new Plane(vA, vB, vC, AOR_multiply(1.0e-10, (AOR_plus(AOR_plus(vA.getNorm1(), vB.getNorm1(), "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79050, _mut79051, _mut79052, _mut79053), vC.getNorm1(), "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79054, _mut79055, _mut79056, _mut79057)), "org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator.ballOnSupport_36", _mut79058, _mut79059, _mut79060, _mut79061));
                        final EnclosingBall<Euclidean2D, Vector2D> disk = new DiskGenerator().ballOnSupport(Arrays.asList(p.toSubSpace(vA), p.toSubSpace(vB), p.toSubSpace(vC)));
                        // convert back to 3D
                        return new EnclosingBall<Euclidean3D, Vector3D>(p.toSpace(disk.getCenter()), disk.getRadius(), vA, vB, vC);
                    } else {
                        final Vector3D vD = support.get(3);
                        // filled with 1.0, hence simplifying the computation
                        final BigFraction[] c2 = new BigFraction[] { new BigFraction(vA.getX()), new BigFraction(vB.getX()), new BigFraction(vC.getX()), new BigFraction(vD.getX()) };
                        final BigFraction[] c3 = new BigFraction[] { new BigFraction(vA.getY()), new BigFraction(vB.getY()), new BigFraction(vC.getY()), new BigFraction(vD.getY()) };
                        final BigFraction[] c4 = new BigFraction[] { new BigFraction(vA.getZ()), new BigFraction(vB.getZ()), new BigFraction(vC.getZ()), new BigFraction(vD.getZ()) };
                        final BigFraction[] c1 = new BigFraction[] { c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])).add(c4[0].multiply(c4[0])), c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])).add(c4[1].multiply(c4[1])), c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])).add(c4[2].multiply(c4[2])), c2[3].multiply(c2[3]).add(c3[3].multiply(c3[3])).add(c4[3].multiply(c4[3])) };
                        final BigFraction twoM11 = minor(c2, c3, c4).multiply(2);
                        final BigFraction m12 = minor(c1, c3, c4);
                        final BigFraction m13 = minor(c1, c2, c4);
                        final BigFraction m14 = minor(c1, c2, c3);
                        final BigFraction centerX = m12.divide(twoM11);
                        final BigFraction centerY = m13.divide(twoM11).negate();
                        final BigFraction centerZ = m14.divide(twoM11);
                        final BigFraction dx = c2[0].subtract(centerX);
                        final BigFraction dy = c3[0].subtract(centerY);
                        final BigFraction dz = c4[0].subtract(centerZ);
                        final BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
                        return new EnclosingBall<Euclidean3D, Vector3D>(new Vector3D(centerX.doubleValue(), centerY.doubleValue(), centerZ.doubleValue()), FastMath.sqrt(r2.doubleValue()), vA, vB, vC, vD);
                    }
                }
            }
        }
    }

    /**
     * Compute a dimension 4 minor, when 4<sup>th</sup> column is known to be filled with 1.0.
     * @param c1 first column
     * @param c2 second column
     * @param c3 third column
     * @return value of the minor computed has an exact fraction
     */
    private BigFraction minor(final BigFraction[] c1, final BigFraction[] c2, final BigFraction[] c3) {
        return c2[0].multiply(c3[1]).multiply(c1[2].subtract(c1[3])).add(c2[0].multiply(c3[2]).multiply(c1[3].subtract(c1[1]))).add(c2[0].multiply(c3[3]).multiply(c1[1].subtract(c1[2]))).add(c2[1].multiply(c3[0]).multiply(c1[3].subtract(c1[2]))).add(c2[1].multiply(c3[2]).multiply(c1[0].subtract(c1[3]))).add(c2[1].multiply(c3[3]).multiply(c1[2].subtract(c1[0]))).add(c2[2].multiply(c3[0]).multiply(c1[1].subtract(c1[3]))).add(c2[2].multiply(c3[1]).multiply(c1[3].subtract(c1[0]))).add(c2[2].multiply(c3[3]).multiply(c1[0].subtract(c1[1]))).add(c2[3].multiply(c3[0]).multiply(c1[2].subtract(c1[1]))).add(c2[3].multiply(c3[1]).multiply(c1[0].subtract(c1[2]))).add(c2[3].multiply(c3[2]).multiply(c1[1].subtract(c1[0])));
    }
}
