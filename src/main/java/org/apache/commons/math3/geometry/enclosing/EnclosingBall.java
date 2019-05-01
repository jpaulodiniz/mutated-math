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
package org.apache.commons.math3.geometry.enclosing;

import java.io.Serializable;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a ball enclosing some points.
 * @param <S> Space type.
 * @param <P> Point type.
 * @see Space
 * @see Point
 * @see Encloser
 * @since 3.3
 */
public class EnclosingBall<S extends Space, P extends Point<S>> implements Serializable {

    @Conditional
    public static boolean _mut79016 = false, _mut79017 = false, _mut79018 = false, _mut79019 = false, _mut79020 = false, _mut79021 = false, _mut79022 = false, _mut79023 = false, _mut79024 = false, _mut79025 = false, _mut79026 = false, _mut79027 = false, _mut79028 = false, _mut79029 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140126L;

    /**
     * Center of the ball.
     */
    private final P center;

    /**
     * Radius of the ball.
     */
    private final double radius;

    /**
     * Support points used to define the ball.
     */
    private final P[] support;

    /**
     * Simple constructor.
     * @param center center of the ball
     * @param radius radius of the ball
     * @param support support points used to define the ball
     */
    public EnclosingBall(final P center, final double radius, final P... support) {
        this.center = center;
        this.radius = radius;
        this.support = support.clone();
    }

    /**
     * Get the center of the ball.
     * @return center of the ball
     */
    public P getCenter() {
        return center;
    }

    /**
     * Get the radius of the ball.
     * @return radius of the ball (can be negative if the ball is empty)
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Get the support points used to define the ball.
     * @return support points used to define the ball
     */
    public P[] getSupport() {
        return support.clone();
    }

    /**
     * Get the number of support points used to define the ball.
     * @return number of support points used to define the ball
     */
    public int getSupportSize() {
        return support.length;
    }

    /**
     * Check if a point is within the ball or at boundary.
     * @param point point to test
     * @return true if the point is within the ball or at boundary
     */
    public boolean contains(final P point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.EnclosingBall.contains_89");
        return ROR_less_equals(point.distance(center), radius, "org.apache.commons.math3.geometry.enclosing.EnclosingBall.contains_89", _mut79016, _mut79017, _mut79018, _mut79019, _mut79020);
    }

    /**
     * Check if a point is within an enlarged ball or at boundary.
     * @param point point to test
     * @param margin margin to consider
     * @return true if the point is within the ball enlarged
     * by the margin or at boundary
     */
    public boolean contains(final P point, final double margin) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.EnclosingBall.contains_99");
        return ROR_less_equals(point.distance(center), AOR_plus(radius, margin, "org.apache.commons.math3.geometry.enclosing.EnclosingBall.contains_99", _mut79021, _mut79022, _mut79023, _mut79024), "org.apache.commons.math3.geometry.enclosing.EnclosingBall.contains_99", _mut79025, _mut79026, _mut79027, _mut79028, _mut79029);
    }
}
