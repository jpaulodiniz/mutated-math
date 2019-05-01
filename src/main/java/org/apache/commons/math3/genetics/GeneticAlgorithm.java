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
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of a genetic algorithm. All factors that govern the operation
 * of the algorithm can be configured for a specific problem.
 *
 * @since 2.0
 */
public class GeneticAlgorithm {

    @Conditional
    public static boolean _mut2109 = false, _mut2110 = false, _mut2111 = false, _mut2112 = false, _mut2113 = false, _mut2114 = false, _mut2115 = false, _mut2116 = false, _mut2117 = false, _mut2118 = false, _mut2119 = false, _mut2120 = false, _mut2121 = false, _mut2122 = false, _mut2123 = false, _mut2124 = false, _mut2125 = false, _mut2126 = false, _mut2127 = false, _mut2128 = false, _mut2129 = false, _mut2130 = false, _mut2131 = false, _mut2132 = false, _mut2133 = false, _mut2134 = false, _mut2135 = false, _mut2136 = false, _mut2137 = false, _mut2138 = false, _mut2139 = false, _mut2140 = false, _mut2141 = false, _mut2142 = false, _mut2143 = false, _mut2144 = false, _mut2145 = false, _mut2146 = false, _mut2147 = false, _mut2148 = false, _mut2149 = false, _mut2150 = false;

    // @GuardedBy("this")
    private static RandomGenerator randomGenerator = new JDKRandomGenerator();

    /**
     * the crossover policy used by the algorithm.
     */
    private final CrossoverPolicy crossoverPolicy;

    /**
     * the rate of crossover for the algorithm.
     */
    private final double crossoverRate;

    /**
     * the mutation policy used by the algorithm.
     */
    private final MutationPolicy mutationPolicy;

    /**
     * the rate of mutation for the algorithm.
     */
    private final double mutationRate;

    /**
     * the selection policy used by the algorithm.
     */
    private final SelectionPolicy selectionPolicy;

    /**
     * the number of generations evolved to reach {@link StoppingCondition} in the last run.
     */
    private int generationsEvolved = 0;

    /**
     * Create a new genetic algorithm.
     * @param crossoverPolicy The {@link CrossoverPolicy}
     * @param crossoverRate The crossover rate as a percentage (0-1 inclusive)
     * @param mutationPolicy The {@link MutationPolicy}
     * @param mutationRate The mutation rate as a percentage (0-1 inclusive)
     * @param selectionPolicy The {@link SelectionPolicy}
     * @throws OutOfRangeException if the crossover or mutation rate is outside the [0, 1] range
     */
    public GeneticAlgorithm(final CrossoverPolicy crossoverPolicy, final double crossoverRate, final MutationPolicy mutationPolicy, final double mutationRate, final SelectionPolicy selectionPolicy) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67");
        if ((_mut2119 ? (ROR_less(crossoverRate, 0, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2109, _mut2110, _mut2111, _mut2112, _mut2113) && ROR_greater(crossoverRate, 1, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2114, _mut2115, _mut2116, _mut2117, _mut2118)) : (ROR_less(crossoverRate, 0, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2109, _mut2110, _mut2111, _mut2112, _mut2113) || ROR_greater(crossoverRate, 1, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2114, _mut2115, _mut2116, _mut2117, _mut2118)))) {
            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, crossoverRate, 0, 1);
        }
        if ((_mut2130 ? (ROR_less(mutationRate, 0, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2120, _mut2121, _mut2122, _mut2123, _mut2124) && ROR_greater(mutationRate, 1, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2125, _mut2126, _mut2127, _mut2128, _mut2129)) : (ROR_less(mutationRate, 0, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2120, _mut2121, _mut2122, _mut2123, _mut2124) || ROR_greater(mutationRate, 1, "org.apache.commons.math3.genetics.GeneticAlgorithm.GeneticAlgorithm_67", _mut2125, _mut2126, _mut2127, _mut2128, _mut2129)))) {
            throw new OutOfRangeException(LocalizedFormats.MUTATION_RATE, mutationRate, 0, 1);
        }
        this.crossoverPolicy = crossoverPolicy;
        this.crossoverRate = crossoverRate;
        this.mutationPolicy = mutationPolicy;
        this.mutationRate = mutationRate;
        this.selectionPolicy = selectionPolicy;
    }

    /**
     * Set the (static) random generator.
     *
     * @param random random generator
     */
    public static synchronized void setRandomGenerator(final RandomGenerator random) {
        randomGenerator = random;
    }

    /**
     * Returns the (static) random generator.
     *
     * @return the static random generator shared by GA implementation classes
     */
    public static synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    /**
     * Evolve the given population. Evolution stops when the stopping condition
     * is satisfied. Updates the {@link #getGenerationsEvolved() generationsEvolved}
     * property with the number of generations evolved before the StoppingCondition
     * is satisfied.
     *
     * @param initial the initial, seed population.
     * @param condition the stopping condition used to stop evolution.
     * @return the population that satisfies the stopping condition.
     */
    public Population evolve(final Population initial, final StoppingCondition condition) {
        Population current = initial;
        generationsEvolved = 0;
        while (!condition.isSatisfied(current)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.GeneticAlgorithm.evolve_116");
            current = nextGeneration(current);
            generationsEvolved++;
        }
        return current;
    }

    /**
     * Evolve the given population into the next generation.
     * <p>
     * <ol>
     *  <li>Get nextGeneration population to fill from <code>current</code>
     *      generation, using its nextGeneration method</li>
     *  <li>Loop until new generation is filled:</li>
     *  <ul><li>Apply configured SelectionPolicy to select a pair of parents
     *          from <code>current</code></li>
     *      <li>With probability = {@link #getCrossoverRate()}, apply
     *          configured {@link CrossoverPolicy} to parents</li>
     *      <li>With probability = {@link #getMutationRate()}, apply
     *          configured {@link MutationPolicy} to each of the offspring</li>
     *      <li>Add offspring individually to nextGeneration,
     *          space permitting</li>
     *  </ul>
     *  <li>Return nextGeneration</li>
     * </ol>
     *
     * @param current the current population.
     * @return the population for the next generation.
     */
    public Population nextGeneration(final Population current) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148");
        Population nextGeneration = current.nextGeneration();
        RandomGenerator randGen = getRandomGenerator();
        while (ROR_less(nextGeneration.getPopulationSize(), nextGeneration.getPopulationLimit(), "org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148", _mut2146, _mut2147, _mut2148, _mut2149, _mut2150)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148");
            // select parent chromosomes
            ChromosomePair pair = getSelectionPolicy().select(current);
            // crossover?
            if (ROR_less(randGen.nextDouble(), getCrossoverRate(), "org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148", _mut2131, _mut2132, _mut2133, _mut2134, _mut2135)) {
                // apply crossover policy to create two offspring
                pair = getCrossoverPolicy().crossover(pair.getFirst(), pair.getSecond());
            }
            // mutation?
            if (ROR_less(randGen.nextDouble(), getMutationRate(), "org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148", _mut2136, _mut2137, _mut2138, _mut2139, _mut2140)) {
                // apply mutation policy to the chromosomes
                pair = new ChromosomePair(getMutationPolicy().mutate(pair.getFirst()), getMutationPolicy().mutate(pair.getSecond()));
            }
            // add the first chromosome to the population
            nextGeneration.addChromosome(pair.getFirst());
            // is there still a place for the second chromosome?
            if (ROR_less(nextGeneration.getPopulationSize(), nextGeneration.getPopulationLimit(), "org.apache.commons.math3.genetics.GeneticAlgorithm.nextGeneration_148", _mut2141, _mut2142, _mut2143, _mut2144, _mut2145)) {
                // add the second chromosome to the population
                nextGeneration.addChromosome(pair.getSecond());
            }
        }
        return nextGeneration;
    }

    /**
     * Returns the crossover policy.
     * @return crossover policy
     */
    public CrossoverPolicy getCrossoverPolicy() {
        return crossoverPolicy;
    }

    /**
     * Returns the crossover rate.
     * @return crossover rate
     */
    public double getCrossoverRate() {
        return crossoverRate;
    }

    /**
     * Returns the mutation policy.
     * @return mutation policy
     */
    public MutationPolicy getMutationPolicy() {
        return mutationPolicy;
    }

    /**
     * Returns the mutation rate.
     * @return mutation rate
     */
    public double getMutationRate() {
        return mutationRate;
    }

    /**
     * Returns the selection policy.
     * @return selection policy
     */
    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    /**
     * Returns the number of generations evolved to reach {@link StoppingCondition} in the last run.
     *
     * @return number of generations evolved
     * @since 2.1
     */
    public int getGenerationsEvolved() {
        return generationsEvolved;
    }
}
