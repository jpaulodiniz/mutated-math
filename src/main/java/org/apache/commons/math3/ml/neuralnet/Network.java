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

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Neural network, composed of {@link Neuron} instances and the links
 * between them.
 *
 * Although updating a neuron's state is thread-safe, modifying the
 * network's topology (adding or removing links) is not.
 *
 * @since 3.3
 */
public class Network implements Iterable<Neuron>, Serializable {

    @Conditional
    public static boolean _mut102755 = false, _mut102756 = false, _mut102757 = false, _mut102758 = false, _mut102759 = false, _mut102760 = false, _mut102761 = false, _mut102762 = false, _mut102763 = false, _mut102764 = false, _mut102765 = false, _mut102766 = false, _mut102767 = false, _mut102768 = false, _mut102769 = false, _mut102770 = false, _mut102771 = false, _mut102772 = false, _mut102773 = false, _mut102774 = false, _mut102775 = false, _mut102776 = false, _mut102777 = false, _mut102778 = false, _mut102779 = false, _mut102780 = false, _mut102781 = false, _mut102782 = false, _mut102783 = false, _mut102784 = false, _mut102785 = false, _mut102786 = false, _mut102787 = false, _mut102788 = false, _mut102789 = false, _mut102790 = false, _mut102791 = false, _mut102792 = false, _mut102793 = false, _mut102794 = false;

    /**
     * Serializable.
     */
    private static final long serialVersionUID = 20130207L;

    /**
     * Neurons.
     */
    private final ConcurrentHashMap<Long, Neuron> neuronMap = new ConcurrentHashMap<Long, Neuron>();

    /**
     * Next available neuron identifier.
     */
    private final AtomicLong nextId;

    /**
     * Neuron's features set size.
     */
    private final int featureSize;

    /**
     * Links.
     */
    private final ConcurrentHashMap<Long, Set<Long>> linkMap = new ConcurrentHashMap<Long, Set<Long>>();

    /**
     * Comparator that prescribes an order of the neurons according
     * to the increasing order of their identifier.
     */
    public static class NeuronIdentifierComparator implements Comparator<Neuron>, Serializable {

        /**
         * Version identifier.
         */
        private static final long serialVersionUID = 20130207L;

        /**
         * {@inheritDoc}
         */
        public int compare(Neuron a, Neuron b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.compare_73");
            final long aId = a.getIdentifier();
            final long bId = b.getIdentifier();
            return ROR_less(aId, bId, "org.apache.commons.math3.ml.neuralnet.Network.compare_73", _mut102755, _mut102756, _mut102757, _mut102758, _mut102759) ? -1 : ROR_greater(aId, bId, "org.apache.commons.math3.ml.neuralnet.Network.compare_73", _mut102760, _mut102761, _mut102762, _mut102763, _mut102764) ? 1 : 0;
        }
    }

    /**
     * Constructor with restricted access, solely used for deserialization.
     *
     * @param nextId Next available identifier.
     * @param featureSize Number of features.
     * @param neuronList Neurons.
     * @param neighbourIdList Links associated to each of the neurons in
     * {@code neuronList}.
     * @throws MathIllegalStateException if an inconsistency is detected
     * (which probably means that the serialized form has been corrupted).
     */
    Network(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.Network_93");
        final int numNeurons = neuronList.length;
        if (ROR_not_equals(numNeurons, neighbourIdList.length, "org.apache.commons.math3.ml.neuralnet.Network.Network_93", _mut102765, _mut102766, _mut102767, _mut102768, _mut102769)) {
            throw new MathIllegalStateException();
        }
        for (int i = 0; ROR_less(i, numNeurons, "org.apache.commons.math3.ml.neuralnet.Network.Network_93", _mut102775, _mut102776, _mut102777, _mut102778, _mut102779); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.Network_93");
            final Neuron n = neuronList[i];
            final long id = n.getIdentifier();
            if (ROR_greater_equals(id, nextId, "org.apache.commons.math3.ml.neuralnet.Network.Network_93", _mut102770, _mut102771, _mut102772, _mut102773, _mut102774)) {
                throw new MathIllegalStateException();
            }
            neuronMap.put(id, n);
            linkMap.put(id, new HashSet<Long>());
        }
        for (int i = 0; ROR_less(i, numNeurons, "org.apache.commons.math3.ml.neuralnet.Network.Network_93", _mut102780, _mut102781, _mut102782, _mut102783, _mut102784); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.Network_93");
            final long aId = neuronList[i].getIdentifier();
            final Set<Long> aLinks = linkMap.get(aId);
            for (Long bId : neighbourIdList[i]) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.Network_93");
                if (neuronMap.get(bId) == null) {
                    throw new MathIllegalStateException();
                }
                addLinkToLinkSet(aLinks, bId);
            }
        }
        this.nextId = new AtomicLong(nextId);
        this.featureSize = featureSize;
    }

    /**
     * @param initialIdentifier Identifier for the first neuron that
     * will be added to this network.
     * @param featureSize Size of the neuron's features.
     */
    public Network(long initialIdentifier, int featureSize) {
        nextId = new AtomicLong(initialIdentifier);
        this.featureSize = featureSize;
    }

    /**
     * Performs a deep copy of this instance.
     * Upon return, the copied and original instances will be independent:
     * Updating one will not affect the other.
     *
     * @return a new instance with the same state as this instance.
     * @since 3.6
     */
    public synchronized Network copy() {
        final Network copy = new Network(nextId.get(), featureSize);
        for (Map.Entry<Long, Neuron> e : neuronMap.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.copy_146");
            copy.neuronMap.put(e.getKey(), e.getValue().copy());
        }
        for (Map.Entry<Long, Set<Long>> e : linkMap.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.copy_146");
            copy.linkMap.put(e.getKey(), new HashSet<Long>(e.getValue()));
        }
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Neuron> iterator() {
        return neuronMap.values().iterator();
    }

    /**
     * Creates a list of the neurons, sorted in a custom order.
     *
     * @param comparator {@link Comparator} used for sorting the neurons.
     * @return a list of neurons, sorted in the order prescribed by the
     * given {@code comparator}.
     * @see NeuronIdentifierComparator
     */
    public Collection<Neuron> getNeurons(Comparator<Neuron> comparator) {
        final List<Neuron> neurons = new ArrayList<Neuron>();
        neurons.addAll(neuronMap.values());
        Collections.sort(neurons, comparator);
        return neurons;
    }

    /**
     * Creates a neuron and assigns it a unique identifier.
     *
     * @param features Initial values for the neuron's features.
     * @return the neuron's identifier.
     * @throws DimensionMismatchException if the length of {@code features}
     * is different from the expected size (as set by the
     * {@link #Network(long,int) constructor}).
     */
    public long createNeuron(double[] features) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.createNeuron_195");
        if (ROR_not_equals(features.length, featureSize, "org.apache.commons.math3.ml.neuralnet.Network.createNeuron_195", _mut102785, _mut102786, _mut102787, _mut102788, _mut102789)) {
            throw new DimensionMismatchException(features.length, featureSize);
        }
        final long id = createNextId();
        neuronMap.put(id, new Neuron(id, features));
        linkMap.put(id, new HashSet<Long>());
        return id;
    }

    /**
     * Deletes a neuron.
     * Links from all neighbours to the removed neuron will also be
     * {@link #deleteLink(Neuron,Neuron) deleted}.
     *
     * @param neuron Neuron to be removed from this network.
     * @throws NoSuchElementException if {@code n} does not belong to
     * this network.
     */
    public void deleteNeuron(Neuron neuron) {
        final Collection<Neuron> neighbours = getNeighbours(neuron);
        // Delete links to from neighbours.
        for (Neuron n : neighbours) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.deleteNeuron_215");
            deleteLink(n, neuron);
        }
        // Remove neuron.
        neuronMap.remove(neuron.getIdentifier());
    }

    /**
     * Gets the size of the neurons' features set.
     *
     * @return the size of the features set.
     */
    public int getFeaturesSize() {
        return featureSize;
    }

    /**
     * Adds a link from neuron {@code a} to neuron {@code b}.
     * Note: the link is not bi-directional; if a bi-directional link is
     * required, an additional call must be made with {@code a} and
     * {@code b} exchanged in the argument list.
     *
     * @param a Neuron.
     * @param b Neuron.
     * @throws NoSuchElementException if the neurons do not exist in the
     * network.
     */
    public void addLink(Neuron a, Neuron b) {
        final long aId = a.getIdentifier();
        final long bId = b.getIdentifier();
        // Check that the neurons belong to this network.
        if (a != getNeuron(aId)) {
            throw new NoSuchElementException(Long.toString(aId));
        }
        if (b != getNeuron(bId)) {
            throw new NoSuchElementException(Long.toString(bId));
        }
        // Add link from "a" to "b".
        addLinkToLinkSet(linkMap.get(aId), bId);
    }

    /**
     * Adds a link to neuron {@code id} in given {@code linkSet}.
     * Note: no check verifies that the identifier indeed belongs
     * to this network.
     *
     * @param linkSet Neuron identifier.
     * @param id Neuron identifier.
     */
    private void addLinkToLinkSet(Set<Long> linkSet, long id) {
        linkSet.add(id);
    }

    /**
     * Deletes the link between neurons {@code a} and {@code b}.
     *
     * @param a Neuron.
     * @param b Neuron.
     * @throws NoSuchElementException if the neurons do not exist in the
     * network.
     */
    public void deleteLink(Neuron a, Neuron b) {
        final long aId = a.getIdentifier();
        final long bId = b.getIdentifier();
        // Check that the neurons belong to this network.
        if (a != getNeuron(aId)) {
            throw new NoSuchElementException(Long.toString(aId));
        }
        if (b != getNeuron(bId)) {
            throw new NoSuchElementException(Long.toString(bId));
        }
        // Delete link from "a" to "b".
        deleteLinkFromLinkSet(linkMap.get(aId), bId);
    }

    /**
     * Deletes a link to neuron {@code id} in given {@code linkSet}.
     * Note: no check verifies that the identifier indeed belongs
     * to this network.
     *
     * @param linkSet Neuron identifier.
     * @param id Neuron identifier.
     */
    private void deleteLinkFromLinkSet(Set<Long> linkSet, long id) {
        linkSet.remove(id);
    }

    /**
     * Retrieves the neuron with the given (unique) {@code id}.
     *
     * @param id Identifier.
     * @return the neuron associated with the given {@code id}.
     * @throws NoSuchElementException if the neuron does not exist in the
     * network.
     */
    public Neuron getNeuron(long id) {
        final Neuron n = neuronMap.get(id);
        if (n == null) {
            throw new NoSuchElementException(Long.toString(id));
        }
        return n;
    }

    /**
     * Retrieves the neurons in the neighbourhood of any neuron in the
     * {@code neurons} list.
     * @param neurons Neurons for which to retrieve the neighbours.
     * @return the list of neighbours.
     * @see #getNeighbours(Iterable,Iterable)
     */
    public Collection<Neuron> getNeighbours(Iterable<Neuron> neurons) {
        return getNeighbours(neurons, null);
    }

    /**
     * Retrieves the neurons in the neighbourhood of any neuron in the
     * {@code neurons} list.
     * The {@code exclude} list allows to retrieve the "concentric"
     * neighbourhoods by removing the neurons that belong to the inner
     * "circles".
     *
     * @param neurons Neurons for which to retrieve the neighbours.
     * @param exclude Neurons to exclude from the returned list.
     * Can be {@code null}.
     * @return the list of neighbours.
     */
    public Collection<Neuron> getNeighbours(Iterable<Neuron> neurons, Iterable<Neuron> exclude) {
        final Set<Long> idList = new HashSet<Long>();
        for (Neuron n : neurons) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.getNeighbours_354");
            idList.addAll(linkMap.get(n.getIdentifier()));
        }
        if (exclude != null) {
            for (Neuron n : exclude) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.getNeighbours_354");
                idList.remove(n.getIdentifier());
            }
        }
        final List<Neuron> neuronList = new ArrayList<Neuron>();
        for (Long id : idList) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.getNeighbours_354");
            neuronList.add(getNeuron(id));
        }
        return neuronList;
    }

    /**
     * Retrieves the neighbours of the given neuron.
     *
     * @param neuron Neuron for which to retrieve the neighbours.
     * @return the list of neighbours.
     * @see #getNeighbours(Neuron,Iterable)
     */
    public Collection<Neuron> getNeighbours(Neuron neuron) {
        return getNeighbours(neuron, null);
    }

    /**
     * Retrieves the neighbours of the given neuron.
     *
     * @param neuron Neuron for which to retrieve the neighbours.
     * @param exclude Neurons to exclude from the returned list.
     * Can be {@code null}.
     * @return the list of neighbours.
     */
    public Collection<Neuron> getNeighbours(Neuron neuron, Iterable<Neuron> exclude) {
        final Set<Long> idList = linkMap.get(neuron.getIdentifier());
        if (exclude != null) {
            for (Neuron n : exclude) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.getNeighbours_394");
                idList.remove(n.getIdentifier());
            }
        }
        final List<Neuron> neuronList = new ArrayList<Neuron>();
        for (Long id : idList) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.getNeighbours_394");
            neuronList.add(getNeuron(id));
        }
        return neuronList;
    }

    /**
     * Creates a neuron identifier.
     *
     * @return a value that will serve as a unique identifier.
     */
    private Long createNextId() {
        return nextId.getAndIncrement();
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.writeReplace_434");
        final Neuron[] neuronList = neuronMap.values().toArray(new Neuron[0]);
        final long[][] neighbourIdList = new long[neuronList.length][];
        for (int i = 0; ROR_less(i, neuronList.length, "org.apache.commons.math3.ml.neuralnet.Network.writeReplace_434", _mut102790, _mut102791, _mut102792, _mut102793, _mut102794); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.writeReplace_434");
            final Collection<Neuron> neighbours = getNeighbours(neuronList[i]);
            final long[] neighboursId = new long[neighbours.size()];
            int count = 0;
            for (Neuron n : neighbours) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.Network.writeReplace_434");
                neighboursId[count] = n.getIdentifier();
                ++count;
            }
            neighbourIdList[i] = neighboursId;
        }
        return new SerializationProxy(nextId.get(), featureSize, neuronList, neighbourIdList);
    }

    /**
     * Serialization.
     */
    private static class SerializationProxy implements Serializable {

        /**
         * Serializable.
         */
        private static final long serialVersionUID = 20130207L;

        /**
         * Next identifier.
         */
        private final long nextId;

        /**
         * Number of features.
         */
        private final int featureSize;

        /**
         * Neurons.
         */
        private final Neuron[] neuronList;

        /**
         * Links.
         */
        private final long[][] neighbourIdList;

        /**
         * @param nextId Next available identifier.
         * @param featureSize Number of features.
         * @param neuronList Neurons.
         * @param neighbourIdList Links associated to each of the neurons in
         * {@code neuronList}.
         */
        SerializationProxy(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
            this.nextId = nextId;
            this.featureSize = featureSize;
            this.neuronList = neuronList;
            this.neighbourIdList = neighbourIdList;
        }

        /**
         * Custom serialization.
         *
         * @return the {@link Network} for which this instance is the proxy.
         */
        private Object readResolve() {
            return new Network(nextId, featureSize, neuronList, neighbourIdList);
        }
    }
}
