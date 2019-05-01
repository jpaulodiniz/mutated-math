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
package org.apache.commons.math3.ml.neuralnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utilities for network maps.
 *
 * @since 3.3
 */
public class MapUtils {

    @Conditional
    public static boolean _mut102795 = false, _mut102796 = false, _mut102797 = false, _mut102798 = false, _mut102799 = false, _mut102800 = false, _mut102801 = false, _mut102802 = false, _mut102803 = false, _mut102804 = false, _mut102805 = false, _mut102806 = false, _mut102807 = false, _mut102808 = false, _mut102809 = false, _mut102810 = false, _mut102811 = false, _mut102812 = false, _mut102813 = false, _mut102814 = false, _mut102815 = false, _mut102816 = false, _mut102817 = false, _mut102818 = false, _mut102819 = false, _mut102820 = false, _mut102821 = false, _mut102822 = false, _mut102823 = false, _mut102824 = false, _mut102825 = false, _mut102826 = false, _mut102827 = false, _mut102828 = false, _mut102829 = false, _mut102830 = false, _mut102831 = false, _mut102832 = false, _mut102833 = false, _mut102834 = false, _mut102835 = false, _mut102836 = false, _mut102837 = false, _mut102838 = false, _mut102839 = false, _mut102840 = false, _mut102841 = false, _mut102842 = false, _mut102843 = false, _mut102844 = false, _mut102845 = false, _mut102846 = false, _mut102847 = false, _mut102848 = false, _mut102849 = false, _mut102850 = false, _mut102851 = false, _mut102852 = false, _mut102853 = false, _mut102854 = false, _mut102855 = false, _mut102856 = false, _mut102857 = false, _mut102858 = false, _mut102859 = false, _mut102860 = false;

    /**
     * Class contains only static methods.
     */
    private MapUtils() {
    }

    /**
     * Finds the neuron that best matches the given features.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If the list is empty
     * {@code null} will be returned.
     * @param distance Distance function. The neuron's features are
     * passed as the first argument to {@link DistanceMeasure#compute(double[],double[])}.
     * @return the neuron whose features are closest to the given data.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     */
    public static Neuron findBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.findBest_56");
        Neuron best = null;
        double min = Double.POSITIVE_INFINITY;
        for (final Neuron n : neurons) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.findBest_56");
            final double d = distance.compute(n.getFeatures(), features);
            if (ROR_less(d, min, "org.apache.commons.math3.ml.neuralnet.MapUtils.findBest_56", _mut102795, _mut102796, _mut102797, _mut102798, _mut102799)) {
                min = d;
                best = n;
            }
        }
        return best;
    }

    /**
     * Finds the two neurons that best match the given features.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If the list is empty
     * {@code null} will be returned.
     * @param distance Distance function. The neuron's features are
     * passed as the first argument to {@link DistanceMeasure#compute(double[],double[])}.
     * @return the two neurons whose features are closest to the given data.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     */
    public static Pair<Neuron, Neuron> findBestAndSecondBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.findBestAndSecondBest_85");
        Neuron[] best = { null, null };
        double[] min = { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
        for (final Neuron n : neurons) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.findBestAndSecondBest_85");
            final double d = distance.compute(n.getFeatures(), features);
            if (ROR_less(d, min[0], "org.apache.commons.math3.ml.neuralnet.MapUtils.findBestAndSecondBest_85", _mut102800, _mut102801, _mut102802, _mut102803, _mut102804)) {
                // Replace second best with old best.
                min[1] = min[0];
                best[1] = best[0];
                // Store current as new best.
                min[0] = d;
                best[0] = n;
            } else if (ROR_less(d, min[1], "org.apache.commons.math3.ml.neuralnet.MapUtils.findBestAndSecondBest_85", _mut102805, _mut102806, _mut102807, _mut102808, _mut102809)) {
                // Replace old second best with current.
                min[1] = d;
                best[1] = n;
            }
        }
        return new Pair<Neuron, Neuron>(best[0], best[1]);
    }

    /**
     * Creates a list of neurons sorted in increased order of the distance
     * to the given {@code features}.
     *
     * @param features Data.
     * @param neurons List of neurons to scan. If it is empty, an empty array
     * will be returned.
     * @param distance Distance function.
     * @return the neurons, sorted in increasing order of distance in data
     * space.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the size of the input is not compatible with the neurons features
     * size.
     *
     * @see #findBest(double[],Iterable,DistanceMeasure)
     * @see #findBestAndSecondBest(double[],Iterable,DistanceMeasure)
     *
     * @since 3.6
     */
    public static Neuron[] sort(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.sort_130");
        final List<PairNeuronDouble> list = new ArrayList<PairNeuronDouble>();
        for (final Neuron n : neurons) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.sort_130");
            final double d = distance.compute(n.getFeatures(), features);
            list.add(new PairNeuronDouble(n, d));
        }
        Collections.sort(list, PairNeuronDouble.COMPARATOR);
        final int len = list.size();
        final Neuron[] sorted = new Neuron[len];
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.ml.neuralnet.MapUtils.sort_130", _mut102810, _mut102811, _mut102812, _mut102813, _mut102814); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.sort_130");
            sorted[i] = list.get(i).getNeuron();
        }
        return sorted;
    }

    /**
     * Computes the <a href="http://en.wikipedia.org/wiki/U-Matrix">
     *  U-matrix</a> of a two-dimensional map.
     *
     * @param map Network.
     * @param distance Function to use for computing the average
     * distance from a neuron to its neighbours.
     * @return the matrix of average distances.
     */
    public static double[][] computeU(NeuronSquareMesh2D map, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160");
        final int numRows = map.getNumberOfRows();
        final int numCols = map.getNumberOfColumns();
        final double[][] uMatrix = new double[numRows][numCols];
        final Network net = map.getNetwork();
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160", _mut102824, _mut102825, _mut102826, _mut102827, _mut102828); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160");
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160", _mut102819, _mut102820, _mut102821, _mut102822, _mut102823); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160");
                final Neuron neuron = map.getNeuron(i, j);
                final Collection<Neuron> neighbours = net.getNeighbours(neuron);
                final double[] features = neuron.getFeatures();
                double d = 0;
                int count = 0;
                for (Neuron n : neighbours) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160");
                    ++count;
                    d += distance.compute(features, n.getFeatures());
                }
                uMatrix[i][j] = AOR_divide(d, count, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeU_160", _mut102815, _mut102816, _mut102817, _mut102818);
            }
        }
        return uMatrix;
    }

    /**
     * Computes the "hit" histogram of a two-dimensional map.
     *
     * @param data Feature vectors.
     * @param map Network.
     * @param distance Function to use for determining the best matching unit.
     * @return the number of hits for each neuron in the map.
     */
    public static int[][] computeHitHistogram(Iterable<double[]> data, NeuronSquareMesh2D map, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196");
        final HashMap<Neuron, Integer> hit = new HashMap<Neuron, Integer>();
        final Network net = map.getNetwork();
        for (double[] f : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196");
            final Neuron best = findBest(f, net, distance);
            final Integer count = hit.get(best);
            if (count == null) {
                hit.put(best, 1);
            } else {
                hit.put(best, AOR_plus(count, 1, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196", _mut102829, _mut102830, _mut102831, _mut102832));
            }
        }
        // Copy the histogram data into a 2D map.
        final int numRows = map.getNumberOfRows();
        final int numCols = map.getNumberOfColumns();
        final int[][] histo = new int[numRows][numCols];
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196", _mut102838, _mut102839, _mut102840, _mut102841, _mut102842); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196");
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196", _mut102833, _mut102834, _mut102835, _mut102836, _mut102837); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeHitHistogram_196");
                final Neuron neuron = map.getNeuron(i, j);
                final Integer count = hit.get(neuron);
                if (count == null) {
                    histo[i][j] = 0;
                } else {
                    histo[i][j] = count;
                }
            }
        }
        return histo;
    }

    /**
     * Computes the quantization error.
     * The quantization error is the average distance between a feature vector
     * and its "best matching unit" (closest neuron).
     *
     * @param data Feature vectors.
     * @param neurons List of neurons to scan.
     * @param distance Distance function.
     * @return the error.
     * @throws NoDataException if {@code data} is empty.
     */
    public static double computeQuantizationError(Iterable<double[]> data, Iterable<Neuron> neurons, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeQuantizationError_243");
        double d = 0;
        int count = 0;
        for (double[] f : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeQuantizationError_243");
            ++count;
            d += distance.compute(f, findBest(f, neurons, distance).getFeatures());
        }
        if (ROR_equals(count, 0, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeQuantizationError_243", _mut102843, _mut102844, _mut102845, _mut102846, _mut102847)) {
            throw new NoDataException();
        }
        return AOR_divide(d, count, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeQuantizationError_243", _mut102848, _mut102849, _mut102850, _mut102851);
    }

    /**
     * Computes the topographic error.
     * The topographic error is the proportion of data for which first and
     * second best matching units are not adjacent in the map.
     *
     * @param data Feature vectors.
     * @param net Network.
     * @param distance Distance function.
     * @return the error.
     * @throws NoDataException if {@code data} is empty.
     */
    public static double computeTopographicError(Iterable<double[]> data, Network net, DistanceMeasure distance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeTopographicError_271");
        int notAdjacentCount = 0;
        int count = 0;
        for (double[] f : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.MapUtils.computeTopographicError_271");
            ++count;
            final Pair<Neuron, Neuron> p = findBestAndSecondBest(f, net, distance);
            if (!net.getNeighbours(p.getFirst()).contains(p.getSecond())) {
                // are not neighbours.
                ++notAdjacentCount;
            }
        }
        if (ROR_equals(count, 0, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeTopographicError_271", _mut102852, _mut102853, _mut102854, _mut102855, _mut102856)) {
            throw new NoDataException();
        }
        return AOR_divide(((double) notAdjacentCount), count, "org.apache.commons.math3.ml.neuralnet.MapUtils.computeTopographicError_271", _mut102857, _mut102858, _mut102859, _mut102860);
    }

    /**
     * Helper data structure holding a (Neuron, double) pair.
     */
    private static class PairNeuronDouble {

        /**
         * Comparator.
         */
        static final Comparator<PairNeuronDouble> COMPARATOR = new Comparator<PairNeuronDouble>() {

            /**
             * {@inheritDoc}
             */
            public int compare(PairNeuronDouble o1, PairNeuronDouble o2) {
                return Double.compare(o1.value, o2.value);
            }
        };

        /**
         * Key.
         */
        private final Neuron neuron;

        /**
         * Value.
         */
        private final double value;

        /**
         * @param neuron Neuron.
         * @param value Value.
         */
        PairNeuronDouble(Neuron neuron, double value) {
            this.neuron = neuron;
            this.value = value;
        }

        /**
         * @return the neuron.
         */
        public Neuron getNeuron() {
            return neuron;
        }
    }
}
