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
package org.apache.commons.math3.ml.neuralnet.oned;

import java.io.Serializable;
import java.io.ObjectInputStream;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Neural network with the topology of a one-dimensional line.
 * Each neuron defines one point on the line.
 *
 * @since 3.3
 */
public class NeuronString implements Serializable {

    @Conditional
    public static boolean _mut102876 = false, _mut102877 = false, _mut102878 = false, _mut102879 = false, _mut102880 = false, _mut102881 = false, _mut102882 = false, _mut102883 = false, _mut102884 = false, _mut102885 = false, _mut102886 = false, _mut102887 = false, _mut102888 = false, _mut102889 = false, _mut102890 = false, _mut102891 = false, _mut102892 = false, _mut102893 = false, _mut102894 = false, _mut102895 = false, _mut102896 = false, _mut102897 = false, _mut102898 = false, _mut102899 = false, _mut102900 = false, _mut102901 = false, _mut102902 = false, _mut102903 = false, _mut102904 = false, _mut102905 = false, _mut102906 = false, _mut102907 = false, _mut102908 = false, _mut102909 = false, _mut102910 = false, _mut102911 = false, _mut102912 = false, _mut102913 = false, _mut102914 = false, _mut102915 = false, _mut102916 = false, _mut102917 = false, _mut102918 = false, _mut102919 = false, _mut102920 = false, _mut102921 = false, _mut102922 = false, _mut102923 = false, _mut102924 = false, _mut102925 = false, _mut102926 = false, _mut102927 = false, _mut102928 = false, _mut102929 = false, _mut102930 = false, _mut102931 = false, _mut102932 = false, _mut102933 = false, _mut102934 = false, _mut102935 = false, _mut102936 = false, _mut102937 = false, _mut102938 = false, _mut102939 = false, _mut102940 = false, _mut102941 = false, _mut102942 = false, _mut102943 = false, _mut102944 = false, _mut102945 = false, _mut102946 = false, _mut102947 = false, _mut102948 = false, _mut102949 = false, _mut102950 = false;

    /**
     * Serial version ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Underlying network.
     */
    private final Network network;

    /**
     * Number of neurons.
     */
    private final int size;

    /**
     * Wrap.
     */
    private final boolean wrap;

    /**
     * Mapping of the 1D coordinate to the neuron identifiers
     * (attributed by the {@link #network} instance).
     */
    private final long[] identifiers;

    /**
     * Constructor with restricted access, solely used for deserialization.
     *
     * @param wrap Whether to wrap the dimension (i.e the first and last
     * neurons will be linked together).
     * @param featuresList Arrays that will initialize the features sets of
     * the network's neurons.
     * @throws NumberIsTooSmallException if {@code num < 2}.
     */
    NeuronString(boolean wrap, double[][] featuresList) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_58");
        size = featuresList.length;
        if (ROR_less(size, 2, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_58", _mut102876, _mut102877, _mut102878, _mut102879, _mut102880)) {
            throw new NumberIsTooSmallException(size, 2, true);
        }
        this.wrap = wrap;
        final int fLen = featuresList[0].length;
        network = new Network(0, fLen);
        identifiers = new long[size];
        // Add neurons.
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_58", _mut102881, _mut102882, _mut102883, _mut102884, _mut102885); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_58");
            identifiers[i] = network.createNeuron(featuresList[i]);
        }
        // Add links.
        createLinks();
    }

    /**
     * Creates a one-dimensional network:
     * Each neuron not located on the border of the mesh has two
     * neurons linked to it.
     * <br/>
     * The links are bi-directional.
     * Neurons created successively are neighbours (i.e. there are
     * links between them).
     * <br/>
     * The topology of the network can also be a circle (if the
     * dimension is wrapped).
     *
     * @param num Number of neurons.
     * @param wrap Whether to wrap the dimension (i.e the first and last
     * neurons will be linked together).
     * @param featureInit Arrays that will initialize the features sets of
     * the network's neurons.
     * @throws NumberIsTooSmallException if {@code num < 2}.
     */
    public NeuronString(int num, boolean wrap, FeatureInitializer[] featureInit) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100");
        if (ROR_less(num, 2, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100", _mut102886, _mut102887, _mut102888, _mut102889, _mut102890)) {
            throw new NumberIsTooSmallException(num, 2, true);
        }
        size = num;
        this.wrap = wrap;
        identifiers = new long[num];
        final int fLen = featureInit.length;
        network = new Network(0, fLen);
        // Add neurons.
        for (int i = 0; ROR_less(i, num, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100", _mut102896, _mut102897, _mut102898, _mut102899, _mut102900); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100");
            final double[] features = new double[fLen];
            for (int fIndex = 0; ROR_less(fIndex, fLen, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100", _mut102891, _mut102892, _mut102893, _mut102894, _mut102895); fIndex++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.NeuronString_100");
                features[fIndex] = featureInit[fIndex].value();
            }
            identifiers[i] = network.createNeuron(features);
        }
        // Add links.
        createLinks();
    }

    /**
     * Retrieves the underlying network.
     * A reference is returned (enabling, for example, the network to be
     * trained).
     * This also implies that calling methods that modify the {@link Network}
     * topology may cause this class to become inconsistent.
     *
     * @return the network.
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Gets the number of neurons.
     *
     * @return the number of neurons.
     */
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the features set from the neuron at location
     * {@code i} in the map.
     *
     * @param i Neuron index.
     * @return the features of the neuron at index {@code i}.
     * @throws OutOfRangeException if {@code i} is out of range.
     */
    public double[] getFeatures(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157");
        if ((_mut102911 ? (ROR_less(i, 0, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157", _mut102901, _mut102902, _mut102903, _mut102904, _mut102905) && ROR_greater_equals(i, size, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157", _mut102906, _mut102907, _mut102908, _mut102909, _mut102910)) : (ROR_less(i, 0, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157", _mut102901, _mut102902, _mut102903, _mut102904, _mut102905) || ROR_greater_equals(i, size, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157", _mut102906, _mut102907, _mut102908, _mut102909, _mut102910)))) {
            throw new OutOfRangeException(i, 0, AOR_minus(size, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.getFeatures_157", _mut102912, _mut102913, _mut102914, _mut102915));
        }
        return network.getNeuron(identifiers[i]).getFeatures();
    }

    /**
     * Creates the neighbour relationships between neurons.
     */
    private void createLinks() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169");
        for (int i = 0; ROR_less(i, AOR_minus(size, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102920, _mut102921, _mut102922, _mut102923), "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102924, _mut102925, _mut102926, _mut102927, _mut102928); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169");
            network.addLink(network.getNeuron(i), network.getNeuron(AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102916, _mut102917, _mut102918, _mut102919)));
        }
        for (int i = size - 1; ROR_greater(i, 0, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102933, _mut102934, _mut102935, _mut102936, _mut102937); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169");
            network.addLink(network.getNeuron(i), network.getNeuron(AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102929, _mut102930, _mut102931, _mut102932)));
        }
        if (wrap) {
            network.addLink(network.getNeuron(0), network.getNeuron(AOR_minus(size, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102938, _mut102939, _mut102940, _mut102941)));
            network.addLink(network.getNeuron(AOR_minus(size, 1, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.createLinks_169", _mut102942, _mut102943, _mut102944, _mut102945)), network.getNeuron(0));
        }
    }

    /**
     * Prevents proxy bypass.
     *
     * @param in Input stream.
     */
    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    /**
     * Custom serialization.
     *
     * @return the proxy instance that will be actually serialized.
     */
    private Object writeReplace() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.writeReplace_196");
        final double[][] featuresList = new double[size][];
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.ml.neuralnet.oned.NeuronString.writeReplace_196", _mut102946, _mut102947, _mut102948, _mut102949, _mut102950); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.oned.NeuronString.writeReplace_196");
            featuresList[i] = getFeatures(i);
        }
        return new SerializationProxy(wrap, featuresList);
    }

    /**
     * Serialization.
     */
    private static class SerializationProxy implements Serializable {

        /**
         * Serializable.
         */
        private static final long serialVersionUID = 20130226L;

        /**
         * Wrap.
         */
        private final boolean wrap;

        /**
         * Neurons' features.
         */
        private final double[][] featuresList;

        /**
         * @param wrap Whether the dimension is wrapped.
         * @param featuresList List of neurons features.
         * {@code neuronList}.
         */
        SerializationProxy(boolean wrap, double[][] featuresList) {
            this.wrap = wrap;
            this.featuresList = featuresList;
        }

        /**
         * Custom serialization.
         *
         * @return the {@link Neuron} for which this instance is the proxy.
         */
        private Object readResolve() {
            return new NeuronString(wrap, featuresList);
        }
    }
}
