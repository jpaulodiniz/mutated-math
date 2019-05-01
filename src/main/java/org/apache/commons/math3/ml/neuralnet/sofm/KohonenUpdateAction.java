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
package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.UpdateAction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Update formula for <a href="http://en.wikipedia.org/wiki/Kohonen">
 * Kohonen's Self-Organizing Map</a>.
 * <br/>
 * The {@link #update(Network,double[]) update} method modifies the
 * features {@code w} of the "winning" neuron and its neighbours
 * according to the following rule:
 * <code>
 *  w<sub>new</sub> = w<sub>old</sub> + &alpha; e<sup>(-d / &sigma;)</sup> * (sample - w<sub>old</sub>)
 * </code>
 * where
 * <ul>
 *  <li>&alpha; is the current <em>learning rate</em>, </li>
 *  <li>&sigma; is the current <em>neighbourhood size</em>, and</li>
 *  <li>{@code d} is the number of links to traverse in order to reach
 *   the neuron from the winning neuron.</li>
 * </ul>
 * <br/>
 * This class is thread-safe as long as the arguments passed to the
 * {@link #KohonenUpdateAction(DistanceMeasure,LearningFactorFunction,
 * NeighbourhoodSizeFunction) constructor} are instances of thread-safe
 * classes.
 * <br/>
 * Each call to the {@link #update(Network,double[]) update} method
 * will increment the internal counter used to compute the current
 * values for
 * <ul>
 *  <li>the <em>learning rate</em>, and</li>
 *  <li>the <em>neighbourhood size</em>.</li>
 * </ul>
 * Consequently, the function instances that compute those values (passed
 * to the constructor of this class) must take into account whether this
 * class's instance will be shared by multiple threads, as this will impact
 * the training process.
 *
 * @since 3.3
 */
public class KohonenUpdateAction implements UpdateAction {

    @Conditional
    public static boolean _mut103649 = false, _mut103650 = false, _mut103651 = false, _mut103652 = false, _mut103653 = false, _mut103654 = false, _mut103655 = false, _mut103656 = false, _mut103657 = false, _mut103658 = false, _mut103659 = false, _mut103660 = false, _mut103661 = false, _mut103662 = false;

    /**
     * Distance function.
     */
    private final DistanceMeasure distance;

    /**
     * Learning factor update function.
     */
    private final LearningFactorFunction learningFactor;

    /**
     * Neighbourhood size update function.
     */
    private final NeighbourhoodSizeFunction neighbourhoodSize;

    /**
     * Number of calls to {@link #update(Network,double[])}.
     */
    private final AtomicLong numberOfCalls = new AtomicLong(0);

    /**
     * @param distance Distance function.
     * @param learningFactor Learning factor update function.
     * @param neighbourhoodSize Neighbourhood size update function.
     */
    public KohonenUpdateAction(DistanceMeasure distance, LearningFactorFunction learningFactor, NeighbourhoodSizeFunction neighbourhoodSize) {
        this.distance = distance;
        this.learningFactor = learningFactor;
        this.neighbourhoodSize = neighbourhoodSize;
    }

    /**
     * {@inheritDoc}
     */
    public void update(Network net, double[] features) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95");
        final long numCalls = AOR_minus(numberOfCalls.incrementAndGet(), 1, "org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95", _mut103649, _mut103650, _mut103651, _mut103652);
        final double currentLearning = learningFactor.value(numCalls);
        final Neuron best = findAndUpdateBestNeuron(net, features, currentLearning);
        final int currentNeighbourhood = neighbourhoodSize.value(numCalls);
        // smaller the learning rate will become.
        final Gaussian neighbourhoodDecay = new Gaussian(currentLearning, 0, currentNeighbourhood);
        if (ROR_greater(currentNeighbourhood, 0, "org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95", _mut103653, _mut103654, _mut103655, _mut103656, _mut103657)) {
            // Initial set of neurons only contains the winning neuron.
            Collection<Neuron> neighbours = new HashSet<Neuron>();
            neighbours.add(best);
            // Winning neuron must be excluded from the neighbours.
            final HashSet<Neuron> exclude = new HashSet<Neuron>();
            exclude.add(best);
            int radius = 1;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95");
                // Retrieve immediate neighbours of the current set of neurons.
                neighbours = net.getNeighbours(neighbours, exclude);
                // Update all the neighbours.
                for (Neuron n : neighbours) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95");
                    updateNeighbouringNeuron(n, features, neighbourhoodDecay.value(radius));
                }
                // not be update more than once per training step.
                exclude.addAll(neighbours);
                ++radius;
            } while (ROR_less_equals(radius, currentNeighbourhood, "org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.update_95", _mut103658, _mut103659, _mut103660, _mut103661, _mut103662));
        }
    }

    /**
     * Retrieves the number of calls to the {@link #update(Network,double[]) update}
     * method.
     *
     * @return the current number of calls.
     */
    public long getNumberOfCalls() {
        return numberOfCalls.get();
    }

    /**
     * Tries to update a neuron.
     *
     * @param n Neuron to be updated.
     * @param features Training data.
     * @param learningRate Learning factor.
     * @return {@code true} if the update succeeded, {@code true} if a
     * concurrent update has been detected.
     */
    private boolean attemptNeuronUpdate(Neuron n, double[] features, double learningRate) {
        final double[] expect = n.getFeatures();
        final double[] update = computeFeatures(expect, features, learningRate);
        return n.compareAndSetFeatures(expect, update);
    }

    /**
     * Atomically updates the given neuron.
     *
     * @param n Neuron to be updated.
     * @param features Training data.
     * @param learningRate Learning factor.
     */
    private void updateNeighbouringNeuron(Neuron n, double[] features, double learningRate) {
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.updateNeighbouringNeuron_174");
            if (attemptNeuronUpdate(n, features, learningRate)) {
                break;
            }
        }
    }

    /**
     * Searches for the neuron whose features are closest to the given
     * sample, and atomically updates its features.
     *
     * @param net Network.
     * @param features Sample data.
     * @param learningRate Current learning factor.
     * @return the winning neuron.
     */
    private Neuron findAndUpdateBestNeuron(Network net, double[] features, double learningRate) {
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction.findAndUpdateBestNeuron_193");
            final Neuron best = MapUtils.findBest(features, net, distance);
            if (attemptNeuronUpdate(best, features, learningRate)) {
                return best;
            }
        }
    }

    /**
     * Computes the new value of the features set.
     *
     * @param current Current values of the features.
     * @param sample Training data.
     * @param learningRate Learning factor.
     * @return the new values for the features.
     */
    private double[] computeFeatures(double[] current, double[] sample, double learningRate) {
        final ArrayRealVector c = new ArrayRealVector(current, false);
        final ArrayRealVector s = new ArrayRealVector(sample, false);
        // c + learningRate * (s - c)
        return s.subtract(c).mapMultiplyToSelf(learningRate).add(c).toArray();
    }
}
