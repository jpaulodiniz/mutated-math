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
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Visualization of high-dimensional data projection on a 2D-map.
 * The method is described in
 * <quote>
 *  <em>Using Smoothed Data Histograms for Cluster Visualization in Self-Organizing Maps</em>
 *  <br>
 *  by Elias Pampalk, Andreas Rauber and Dieter Merkl.
 * </quote>
 * @since 3.6
 */
public class SmoothedDataHistogram implements MapDataVisualization {

    @Conditional
    public static boolean _mut103071 = false, _mut103072 = false, _mut103073 = false, _mut103074 = false, _mut103075 = false, _mut103076 = false, _mut103077 = false, _mut103078 = false, _mut103079 = false, _mut103080 = false, _mut103081 = false, _mut103082 = false, _mut103083 = false, _mut103084 = false, _mut103085 = false, _mut103086 = false, _mut103087 = false, _mut103088 = false, _mut103089 = false, _mut103090 = false, _mut103091 = false, _mut103092 = false, _mut103093 = false, _mut103094 = false, _mut103095 = false, _mut103096 = false, _mut103097 = false, _mut103098 = false, _mut103099 = false, _mut103100 = false, _mut103101 = false, _mut103102 = false, _mut103103 = false, _mut103104 = false, _mut103105 = false;

    /**
     * Smoothing parameter.
     */
    private final int smoothingBins;

    /**
     * Distance.
     */
    private final DistanceMeasure distance;

    /**
     * Normalization factor.
     */
    private final double membershipNormalization;

    /**
     * @param smoothingBins Number of bins.
     * @param distance Distance.
     */
    public SmoothedDataHistogram(int smoothingBins, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.SmoothedDataHistogram_48");
        this.smoothingBins = smoothingBins;
        this.distance = distance;
        double sum = 0;
        for (int i = 0; ROR_less(i, smoothingBins, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.SmoothedDataHistogram_48", _mut103075, _mut103076, _mut103077, _mut103078, _mut103079); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.SmoothedDataHistogram_48");
            sum += AOR_minus(smoothingBins, i, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.SmoothedDataHistogram_48", _mut103071, _mut103072, _mut103073, _mut103074);
        }
        this.membershipNormalization = AOR_divide(1d, sum, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.SmoothedDataHistogram_48", _mut103080, _mut103081, _mut103082, _mut103083);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NumberIsTooSmallException if the size of the {@code map}
     * is smaller than the number of {@link #SmoothedDataHistogram(int,DistanceMeasure)
     * smoothing bins}.
     */
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68");
        final int nR = map.getNumberOfRows();
        final int nC = map.getNumberOfColumns();
        final int mapSize = AOR_multiply(nR, nC, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68", _mut103084, _mut103085, _mut103086, _mut103087);
        if (ROR_less(mapSize, smoothingBins, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68", _mut103088, _mut103089, _mut103090, _mut103091, _mut103092)) {
            throw new NumberIsTooSmallException(mapSize, smoothingBins, true);
        }
        final LocationFinder finder = new LocationFinder(map);
        // Histogram bins.
        final double[][] histo = new double[nR][nC];
        for (double[] sample : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68");
            final Neuron[] sorted = MapUtils.sort(sample, map.getNetwork(), distance);
            for (int i = 0; ROR_less(i, smoothingBins, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68", _mut103101, _mut103102, _mut103103, _mut103104, _mut103105); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68");
                final LocationFinder.Location loc = finder.getLocation(sorted[i]);
                final int row = loc.getRow();
                final int col = loc.getColumn();
                histo[row][col] += AOR_multiply((AOR_minus(smoothingBins, i, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68", _mut103093, _mut103094, _mut103095, _mut103096)), membershipNormalization, "org.apache.commons.math3.ml.neuralnet.twod.util.SmoothedDataHistogram.computeImage_68", _mut103097, _mut103098, _mut103099, _mut103100);
            }
        }
        return histo;
    }
}
