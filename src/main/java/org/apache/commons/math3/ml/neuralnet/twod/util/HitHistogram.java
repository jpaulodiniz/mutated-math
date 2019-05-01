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
package org.apache.commons.math3.ml.neuralnet.twod.util;

import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes the hit histogram.
 * Each bin will contain the number of data for which the corresponding
 * neuron is the best matching unit.
 * @since 3.6
 */
public class HitHistogram implements MapDataVisualization {

    @Conditional
    public static boolean _mut103141 = false, _mut103142 = false, _mut103143 = false, _mut103144 = false, _mut103145 = false, _mut103146 = false, _mut103147 = false, _mut103148 = false, _mut103149 = false, _mut103150 = false;

    /**
     * Distance.
     */
    private final DistanceMeasure distance;

    /**
     * Whether to compute relative bin counts.
     */
    private final boolean normalizeCount;

    /**
     * @param normalizeCount Whether to compute relative bin counts.
     * If {@code true}, the data count in each bin will be divided by the total
     * number of samples.
     * @param distance Distance.
     */
    public HitHistogram(boolean normalizeCount, DistanceMeasure distance) {
        this.normalizeCount = normalizeCount;
        this.distance = distance;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50");
        final int nR = map.getNumberOfRows();
        final int nC = map.getNumberOfColumns();
        final LocationFinder finder = new LocationFinder(map);
        // Total number of samples.
        int numSamples = 0;
        // Hit bins.
        final double[][] hit = new double[nR][nC];
        for (double[] sample : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50");
            final Neuron best = MapUtils.findBest(sample, map, distance);
            final LocationFinder.Location loc = finder.getLocation(best);
            final int row = loc.getRow();
            final int col = loc.getColumn();
            hit[row][col] += 1;
            ++numSamples;
        }
        if (normalizeCount) {
            for (int r = 0; ROR_less(r, nR, "org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50", _mut103146, _mut103147, _mut103148, _mut103149, _mut103150); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50");
                for (int c = 0; ROR_less(c, nC, "org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50", _mut103141, _mut103142, _mut103143, _mut103144, _mut103145); c++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.HitHistogram.computeImage_50");
                    hit[r][c] /= numSamples;
                }
            }
        }
        return hit;
    }
}
