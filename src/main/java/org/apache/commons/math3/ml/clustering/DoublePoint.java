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
package org.apache.commons.math3.ml.clustering;

import java.io.Serializable;
import java.util.Arrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A simple implementation of {@link Clusterable} for points with double coordinates.
 * @since 3.2
 */
public class DoublePoint implements Clusterable, Serializable {

    @Conditional
    public static boolean _mut102654 = false, _mut102655 = false, _mut102656 = false, _mut102657 = false, _mut102658 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 3946024775784901369L;

    /**
     * Point coordinates.
     */
    private final double[] point;

    /**
     * Build an instance wrapping an double array.
     * <p>
     * The wrapped array is referenced, it is <em>not</em> copied.
     *
     * @param point the n-dimensional point in double space
     */
    public DoublePoint(final double[] point) {
        this.point = point;
    }

    /**
     * Build an instance wrapping an integer array.
     * <p>
     * The wrapped array is copied to an internal double array.
     *
     * @param point the n-dimensional point in integer space
     */
    public DoublePoint(final int[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DoublePoint.DoublePoint_53");
        this.point = new double[point.length];
        for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.ml.clustering.DoublePoint.DoublePoint_53", _mut102654, _mut102655, _mut102656, _mut102657, _mut102658); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DoublePoint.DoublePoint_53");
            this.point[i] = point[i];
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] getPoint() {
        return point;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof DoublePoint)) {
            return false;
        }
        return Arrays.equals(point, ((DoublePoint) other).point);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(point);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(point);
    }
}
