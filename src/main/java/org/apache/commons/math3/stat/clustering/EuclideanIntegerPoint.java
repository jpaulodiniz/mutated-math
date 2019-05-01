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
package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A simple implementation of {@link Clusterable} for points with integer coordinates.
 * @since 2.0
 * @deprecated As of 3.2 (to be removed in 4.0),
 * use {@link org.apache.commons.math3.ml.clustering.DoublePoint} instead
 */
@Deprecated
public class EuclideanIntegerPoint implements Clusterable<EuclideanIntegerPoint>, Serializable {

    @Conditional
    public static boolean _mut4873 = false, _mut4874 = false, _mut4875 = false, _mut4876 = false, _mut4877 = false, _mut4878 = false, _mut4879 = false, _mut4880 = false, _mut4881 = false, _mut4882 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 3946024775784901369L;

    /**
     * Point coordinates.
     */
    private final int[] point;

    /**
     * Build an instance wrapping an integer array.
     * <p>The wrapped array is referenced, it is <em>not</em> copied.</p>
     * @param point the n-dimensional point in integer space
     */
    public EuclideanIntegerPoint(final int[] point) {
        this.point = point;
    }

    /**
     * Get the n-dimensional point in integer space.
     * @return a reference (not a copy!) to the wrapped array
     */
    public int[] getPoint() {
        return point;
    }

    /**
     * {@inheritDoc}
     */
    public double distanceFrom(final EuclideanIntegerPoint p) {
        return MathArrays.distance(point, p.getPoint());
    }

    /**
     * {@inheritDoc}
     */
    public EuclideanIntegerPoint centroidOf(final Collection<EuclideanIntegerPoint> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64");
        int[] centroid = new int[getPoint().length];
        for (EuclideanIntegerPoint p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64");
            for (int i = 0; ROR_less(i, centroid.length, "org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64", _mut4873, _mut4874, _mut4875, _mut4876, _mut4877); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64");
                centroid[i] += p.getPoint()[i];
            }
        }
        for (int i = 0; ROR_less(i, centroid.length, "org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64", _mut4878, _mut4879, _mut4880, _mut4881, _mut4882); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.EuclideanIntegerPoint.centroidOf_64");
            centroid[i] /= points.size();
        }
        return new EuclideanIntegerPoint(centroid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EuclideanIntegerPoint)) {
            return false;
        }
        return Arrays.equals(point, ((EuclideanIntegerPoint) other).point);
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
     * @since 2.1
     */
    @Override
    public String toString() {
        return Arrays.toString(point);
    }
}
