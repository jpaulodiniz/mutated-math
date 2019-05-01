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

import java.util.Collection;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/U-Matrix">U-Matrix</a>
 * visualization of high-dimensional data projection.
 * @since 3.6
 */
public class UnifiedDistanceMatrix implements MapVisualization {

    @Conditional
    public static boolean _mut102951 = false, _mut102952 = false, _mut102953 = false, _mut102954 = false, _mut102955 = false, _mut102956 = false, _mut102957 = false, _mut102958 = false, _mut102959 = false, _mut102960 = false, _mut102961 = false, _mut102962 = false, _mut102963 = false, _mut102964 = false, _mut102965 = false, _mut102966 = false, _mut102967 = false, _mut102968 = false, _mut102969 = false, _mut102970 = false, _mut102971 = false, _mut102972 = false, _mut102973 = false, _mut102974 = false, _mut102975 = false, _mut102976 = false, _mut102977 = false, _mut102978 = false, _mut102979 = false, _mut102980 = false, _mut102981 = false, _mut102982 = false, _mut102983 = false, _mut102984 = false, _mut102985 = false, _mut102986 = false, _mut102987 = false, _mut102988 = false, _mut102989 = false, _mut102990 = false, _mut102991 = false, _mut102992 = false, _mut102993 = false, _mut102994 = false, _mut102995 = false, _mut102996 = false, _mut102997 = false, _mut102998 = false, _mut102999 = false, _mut103000 = false, _mut103001 = false, _mut103002 = false, _mut103003 = false, _mut103004 = false, _mut103005 = false, _mut103006 = false, _mut103007 = false, _mut103008 = false, _mut103009 = false, _mut103010 = false, _mut103011 = false, _mut103012 = false, _mut103013 = false, _mut103014 = false, _mut103015 = false, _mut103016 = false, _mut103017 = false, _mut103018 = false, _mut103019 = false, _mut103020 = false, _mut103021 = false, _mut103022 = false, _mut103023 = false, _mut103024 = false, _mut103025 = false, _mut103026 = false, _mut103027 = false, _mut103028 = false, _mut103029 = false, _mut103030 = false, _mut103031 = false, _mut103032 = false, _mut103033 = false, _mut103034 = false, _mut103035 = false, _mut103036 = false, _mut103037 = false, _mut103038 = false, _mut103039 = false, _mut103040 = false, _mut103041 = false, _mut103042 = false, _mut103043 = false, _mut103044 = false, _mut103045 = false, _mut103046 = false, _mut103047 = false, _mut103048 = false, _mut103049 = false, _mut103050 = false, _mut103051 = false, _mut103052 = false, _mut103053 = false, _mut103054 = false, _mut103055 = false, _mut103056 = false, _mut103057 = false, _mut103058 = false, _mut103059 = false, _mut103060 = false, _mut103061 = false, _mut103062 = false, _mut103063 = false, _mut103064 = false, _mut103065 = false, _mut103066 = false, _mut103067 = false, _mut103068 = false, _mut103069 = false, _mut103070 = false;

    /**
     * Whether to show distance between each pair of neighbouring units.
     */
    private final boolean individualDistances;

    /**
     * Distance.
     */
    private final DistanceMeasure distance;

    /**
     * Simple constructor.
     *
     * @param individualDistances If {@code true}, the 8 individual
     * inter-units distances will be {@link #computeImage(NeuronSquareMesh2D)
     * computed}.  They will be stored in additional pixels around each of
     * the original units of the 2D-map.  The additional pixels that lie
     * along a "diagonal" are shared by <em>two</em> pairs of units: their
     * value will be set to the average distance between the units belonging
     * to each of the pairs.  The value zero will be stored in the pixel
     * corresponding to the location of a unit of the 2D-map.
     * <br>
     * If {@code false}, only the average distance between a unit and all its
     * neighbours will be computed (and stored in the pixel corresponding to
     * that unit of the 2D-map).  In that case, the number of neighbours taken
     * into account depends on the network's
     * {@link org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood
     * neighbourhood type}.
     * @param distance Distance.
     */
    public UnifiedDistanceMatrix(boolean individualDistances, DistanceMeasure distance) {
        this.individualDistances = individualDistances;
        this.distance = distance;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] computeImage(NeuronSquareMesh2D map) {
        if (individualDistances) {
            return individualDistances(map);
        } else {
            return averageDistances(map);
        }
    }

    /**
     * Computes the distances between a unit of the map and its
     * neighbours.
     * The image will contain more pixels than the number of neurons
     * in the given {@code map} because each neuron has 8 neighbours.
     * The value zero will be stored in the pixels corresponding to
     * the location of a map unit.
     *
     * @param map Map.
     * @return an image representing the individual distances.
     */
    private double[][] individualDistances(NeuronSquareMesh2D map) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
        final int numRows = map.getNumberOfRows();
        final int numCols = map.getNumberOfColumns();
        final double[][] uMatrix = new double[AOR_plus(AOR_multiply(numRows, 2, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102951, _mut102952, _mut102953, _mut102954), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102955, _mut102956, _mut102957, _mut102958)][AOR_plus(AOR_multiply(numCols, 2, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102959, _mut102960, _mut102961, _mut102962), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102963, _mut102964, _mut102965, _mut102966)];
        // respectively.
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102996, _mut102997, _mut102998, _mut102999, _mut103000); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
            // Current unit's row index in result image.
            final int iR = AOR_plus(AOR_multiply(2, i, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102967, _mut102968, _mut102969, _mut102970), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102971, _mut102972, _mut102973, _mut102974);
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102991, _mut102992, _mut102993, _mut102994, _mut102995); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
                // Current unit's column index in result image.
                final int jR = AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102975, _mut102976, _mut102977, _mut102978), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102979, _mut102980, _mut102981, _mut102982);
                final double[] current = map.getNeuron(i, j).getFeatures();
                Neuron neighbour;
                // Right neighbour.
                neighbour = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                if (neighbour != null) {
                    uMatrix[iR][AOR_plus(jR, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102983, _mut102984, _mut102985, _mut102986)] = distance.compute(current, neighbour.getFeatures());
                }
                // Bottom-center neighbour.
                neighbour = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                if (neighbour != null) {
                    uMatrix[AOR_plus(iR, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut102987, _mut102988, _mut102989, _mut102990)][jR] = distance.compute(current, neighbour.getFeatures());
                }
            }
        }
        // * the bottom-center neighbour and the right neighbour.
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103039, _mut103040, _mut103041, _mut103042, _mut103043); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
            // Current unit's row index in result image.
            final int iR = AOR_plus(AOR_multiply(2, i, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103001, _mut103002, _mut103003, _mut103004), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103005, _mut103006, _mut103007, _mut103008);
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103034, _mut103035, _mut103036, _mut103037, _mut103038); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
                // Current unit's column index in result image.
                final int jR = AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103009, _mut103010, _mut103011, _mut103012), 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103013, _mut103014, _mut103015, _mut103016);
                final Neuron current = map.getNeuron(i, j);
                final Neuron right = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                final Neuron bottom = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                final Neuron bottomRight = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.DOWN);
                final double current2BottomRight = bottomRight == null ? 0 : distance.compute(current.getFeatures(), bottomRight.getFeatures());
                final double right2Bottom = ((_mut103017 ? (right == null && bottom == null) : (right == null || bottom == null))) ? 0 : distance.compute(right.getFeatures(), bottom.getFeatures());
                // Bottom-right slot.
                uMatrix[AOR_plus(iR, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103022, _mut103023, _mut103024, _mut103025)][AOR_plus(jR, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103018, _mut103019, _mut103020, _mut103021)] = AOR_multiply(0.5, (AOR_plus(current2BottomRight, right2Bottom, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103026, _mut103027, _mut103028, _mut103029)), "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103030, _mut103031, _mut103032, _mut103033);
            }
        }
        // 3. Copy last row into first row.
        final int lastRow = AOR_minus(uMatrix.length, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103044, _mut103045, _mut103046, _mut103047);
        uMatrix[0] = uMatrix[lastRow];
        // Copy last column into first column.
        final int lastCol = AOR_minus(uMatrix[0].length, 1, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103048, _mut103049, _mut103050, _mut103051);
        for (int r = 0; ROR_less(r, lastRow, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83", _mut103052, _mut103053, _mut103054, _mut103055, _mut103056); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.individualDistances_83");
            uMatrix[r][0] = uMatrix[r][lastCol];
        }
        return uMatrix;
    }

    /**
     * Computes the distances between a unit of the map and its neighbours.
     *
     * @param map Map.
     * @return an image representing the average distances.
     */
    private double[][] averageDistances(NeuronSquareMesh2D map) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183");
        final int numRows = map.getNumberOfRows();
        final int numCols = map.getNumberOfColumns();
        final double[][] uMatrix = new double[numRows][numCols];
        final Network net = map.getNetwork();
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183", _mut103066, _mut103067, _mut103068, _mut103069, _mut103070); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183");
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183", _mut103061, _mut103062, _mut103063, _mut103064, _mut103065); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183");
                final Neuron neuron = map.getNeuron(i, j);
                final Collection<Neuron> neighbours = net.getNeighbours(neuron);
                final double[] features = neuron.getFeatures();
                double d = 0;
                int count = 0;
                for (Neuron n : neighbours) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183");
                    ++count;
                    d += distance.compute(features, n.getFeatures());
                }
                uMatrix[i][j] = AOR_divide(d, count, "org.apache.commons.math3.ml.neuralnet.twod.util.UnifiedDistanceMatrix.averageDistances_183", _mut103057, _mut103058, _mut103059, _mut103060);
            }
        }
        return uMatrix;
    }
}
