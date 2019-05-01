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

import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Population of chromosomes which uses elitism (certain percentage of the best
 * chromosomes is directly copied to the next generation).
 *
 * @since 2.0
 */
public class ElitisticListPopulation extends ListPopulation {

    @Conditional
    public static boolean _mut2161 = false, _mut2162 = false, _mut2163 = false, _mut2164 = false, _mut2165 = false, _mut2166 = false, _mut2167 = false, _mut2168 = false, _mut2169 = false, _mut2170 = false, _mut2171 = false, _mut2172 = false, _mut2173 = false, _mut2174 = false, _mut2175 = false, _mut2176 = false, _mut2177 = false, _mut2178 = false, _mut2179 = false, _mut2180 = false, _mut2181 = false, _mut2182 = false, _mut2183 = false, _mut2184 = false;

    /**
     * percentage of chromosomes copied to the next generation
     */
    private double elitismRate = 0.9;

    /**
     * Creates a new {@link ElitisticListPopulation} instance.
     *
     * @param chromosomes list of chromosomes in the population
     * @param populationLimit maximal size of the population
     * @param elitismRate how many best chromosomes will be directly transferred to the next generation [in %]
     * @throws NullArgumentException if the list of chromosomes is {@code null}
     * @throws NotPositiveException if the population limit is not a positive number (&lt; 1)
     * @throws NumberIsTooLargeException if the list of chromosomes exceeds the population limit
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public ElitisticListPopulation(final List<Chromosome> chromosomes, final int populationLimit, final double elitismRate) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException, OutOfRangeException {
        super(chromosomes, populationLimit);
        setElitismRate(elitismRate);
    }

    /**
     * Creates a new {@link ElitisticListPopulation} instance and initializes its inner chromosome list.
     *
     * @param populationLimit maximal size of the population
     * @param elitismRate how many best chromosomes will be directly transferred to the next generation [in %]
     * @throws NotPositiveException if the population limit is not a positive number (&lt; 1)
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public ElitisticListPopulation(final int populationLimit, final double elitismRate) throws NotPositiveException, OutOfRangeException {
        super(populationLimit);
        setElitismRate(elitismRate);
    }

    /**
     * Start the population for the next generation. The <code>{@link #elitismRate}</code>
     * percents of the best chromosomes are directly copied to the next generation.
     *
     * @return the beginnings of the next generation.
     */
    public Population nextGeneration() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ElitisticListPopulation.nextGeneration_80");
        // initialize a new generation with the same parameters
        ElitisticListPopulation nextGeneration = new ElitisticListPopulation(getPopulationLimit(), getElitismRate());
        final List<Chromosome> oldChromosomes = getChromosomeList();
        Collections.sort(oldChromosomes);
        // index of the last "not good enough" chromosome
        int boundIndex = (int) FastMath.ceil(AOR_multiply((AOR_minus(1.0, getElitismRate(), "org.apache.commons.math3.genetics.ElitisticListPopulation.nextGeneration_80", _mut2161, _mut2162, _mut2163, _mut2164)), oldChromosomes.size(), "org.apache.commons.math3.genetics.ElitisticListPopulation.nextGeneration_80", _mut2165, _mut2166, _mut2167, _mut2168));
        for (int i = boundIndex; ROR_less(i, oldChromosomes.size(), "org.apache.commons.math3.genetics.ElitisticListPopulation.nextGeneration_80", _mut2169, _mut2170, _mut2171, _mut2172, _mut2173); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ElitisticListPopulation.nextGeneration_80");
            nextGeneration.addChromosome(oldChromosomes.get(i));
        }
        return nextGeneration;
    }

    /**
     * Sets the elitism rate, i.e. how many best chromosomes will be directly transferred to the next generation [in %].
     *
     * @param elitismRate how many best chromosomes will be directly transferred to the next generation [in %]
     * @throws OutOfRangeException if the elitism rate is outside the [0, 1] range
     */
    public void setElitismRate(final double elitismRate) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ElitisticListPopulation.setElitismRate_102");
        if ((_mut2184 ? (ROR_less(elitismRate, 0, "org.apache.commons.math3.genetics.ElitisticListPopulation.setElitismRate_102", _mut2174, _mut2175, _mut2176, _mut2177, _mut2178) && ROR_greater(elitismRate, 1, "org.apache.commons.math3.genetics.ElitisticListPopulation.setElitismRate_102", _mut2179, _mut2180, _mut2181, _mut2182, _mut2183)) : (ROR_less(elitismRate, 0, "org.apache.commons.math3.genetics.ElitisticListPopulation.setElitismRate_102", _mut2174, _mut2175, _mut2176, _mut2177, _mut2178) || ROR_greater(elitismRate, 1, "org.apache.commons.math3.genetics.ElitisticListPopulation.setElitismRate_102", _mut2179, _mut2180, _mut2181, _mut2182, _mut2183)))) {
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, elitismRate, 0, 1);
        }
        this.elitismRate = elitismRate;
    }

    /**
     * Access the elitism rate.
     * @return the elitism rate
     */
    public double getElitismRate() {
        return this.elitismRate;
    }
}
