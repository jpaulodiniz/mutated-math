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

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.exception.MathIllegalStateException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Helper class to find the grid coordinates of a neuron.
 * @since 3.6
 */
public class LocationFinder {

    @Conditional
    public static boolean _mut103116 = false, _mut103117 = false, _mut103118 = false, _mut103119 = false, _mut103120 = false, _mut103121 = false, _mut103122 = false, _mut103123 = false, _mut103124 = false, _mut103125 = false;

    /**
     * Identifier to location mapping.
     */
    private final Map<Long, Location> locations = new HashMap<Long, Location>();

    /**
     * Container holding a (row, column) pair.
     */
    public static class Location {

        /**
         * Row index.
         */
        private final int row;

        /**
         * Column index.
         */
        private final int column;

        /**
         * @param row Row index.
         * @param column Column index.
         */
        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

        /**
         * @return the row index.
         */
        public int getRow() {
            return row;
        }

        /**
         * @return the column index.
         */
        public int getColumn() {
            return column;
        }
    }

    /**
     * Builds a finder to retrieve the locations of neurons that
     * belong to the given {@code map}.
     *
     * @param map Map.
     *
     * @throws MathIllegalStateException if the network contains non-unique
     * identifiers.  This indicates an inconsistent state due to a bug in
     * the construction code of the underlying
     * {@link org.apache.commons.math3.ml.neuralnet.Network network}.
     */
    public LocationFinder(NeuronSquareMesh2D map) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder.LocationFinder_79");
        final int nR = map.getNumberOfRows();
        final int nC = map.getNumberOfColumns();
        for (int r = 0; ROR_less(r, nR, "org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder.LocationFinder_79", _mut103121, _mut103122, _mut103123, _mut103124, _mut103125); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder.LocationFinder_79");
            for (int c = 0; ROR_less(c, nC, "org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder.LocationFinder_79", _mut103116, _mut103117, _mut103118, _mut103119, _mut103120); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder.LocationFinder_79");
                final Long id = map.getNeuron(r, c).getIdentifier();
                if (locations.get(id) != null) {
                    throw new MathIllegalStateException();
                }
                locations.put(id, new Location(r, c));
            }
        }
    }

    /**
     * Retrieves a neuron's grid coordinates.
     *
     * @param n Neuron.
     * @return the (row, column) coordinates of {@code n}, or {@code null}
     * if no such neuron belongs to the {@link #LocationFinder(NeuronSquareMesh2D)
     * map used to build this instance}.
     */
    public Location getLocation(Neuron n) {
        return locations.get(n.getIdentifier());
    }
}
