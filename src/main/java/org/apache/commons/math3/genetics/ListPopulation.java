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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Population of chromosomes represented by a {@link List}.
 *
 * @since 2.0
 */
public abstract class ListPopulation implements Population {

    @Conditional
    public static boolean _mut2016 = false, _mut2017 = false, _mut2018 = false, _mut2019 = false, _mut2020 = false, _mut2021 = false, _mut2022 = false, _mut2023 = false, _mut2024 = false, _mut2025 = false, _mut2026 = false, _mut2027 = false, _mut2028 = false, _mut2029 = false, _mut2030 = false, _mut2031 = false, _mut2032 = false, _mut2033 = false, _mut2034 = false, _mut2035 = false, _mut2036 = false, _mut2037 = false, _mut2038 = false, _mut2039 = false, _mut2040 = false, _mut2041 = false, _mut2042 = false, _mut2043 = false, _mut2044 = false, _mut2045 = false, _mut2046 = false, _mut2047 = false, _mut2048 = false, _mut2049 = false, _mut2050 = false, _mut2051 = false, _mut2052 = false, _mut2053 = false, _mut2054 = false, _mut2055 = false, _mut2056 = false, _mut2057 = false, _mut2058 = false, _mut2059 = false;

    /**
     * List of chromosomes
     */
    private List<Chromosome> chromosomes;

    /**
     * maximal size of the population
     */
    private int populationLimit;

    /**
     * Creates a new ListPopulation instance and initializes its inner chromosome list.
     *
     * @param populationLimit maximal size of the population
     * @throws NotPositiveException if the population limit is not a positive number (&lt; 1)
     */
    public ListPopulation(final int populationLimit) throws NotPositiveException {
        this(Collections.<Chromosome>emptyList(), populationLimit);
    }

    /**
     * Creates a new ListPopulation instance.
     * <p>
     * Note: the chromosomes of the specified list are added to the population.
     *
     * @param chromosomes list of chromosomes to be added to the population
     * @param populationLimit maximal size of the population
     * @throws NullArgumentException if the list of chromosomes is {@code null}
     * @throws NotPositiveException if the population limit is not a positive number (&lt; 1)
     * @throws NumberIsTooLargeException if the list of chromosomes exceeds the population limit
     */
    public ListPopulation(final List<Chromosome> chromosomes, final int populationLimit) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.ListPopulation_65");
        if (chromosomes == null) {
            throw new NullArgumentException();
        }
        if (ROR_less_equals(populationLimit, 0, "org.apache.commons.math3.genetics.ListPopulation.ListPopulation_65", _mut2016, _mut2017, _mut2018, _mut2019, _mut2020)) {
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
        }
        if (ROR_greater(chromosomes.size(), populationLimit, "org.apache.commons.math3.genetics.ListPopulation.ListPopulation_65", _mut2021, _mut2022, _mut2023, _mut2024, _mut2025)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), populationLimit, false);
        }
        this.populationLimit = populationLimit;
        this.chromosomes = new ArrayList<Chromosome>(populationLimit);
        this.chromosomes.addAll(chromosomes);
    }

    /**
     * Sets the list of chromosomes.
     * <p>
     * Note: this method removes all existing chromosomes in the population and adds all chromosomes
     * of the specified list to the population.
     *
     * @param chromosomes the list of chromosomes
     * @throws NullArgumentException if the list of chromosomes is {@code null}
     * @throws NumberIsTooLargeException if the list of chromosomes exceeds the population limit
     * @deprecated use {@link #addChromosomes(Collection)} instead
     */
    @Deprecated
    public void setChromosomes(final List<Chromosome> chromosomes) throws NullArgumentException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.setChromosomes_94");
        if (chromosomes == null) {
            throw new NullArgumentException();
        }
        if (ROR_greater(chromosomes.size(), populationLimit, "org.apache.commons.math3.genetics.ListPopulation.setChromosomes_94", _mut2026, _mut2027, _mut2028, _mut2029, _mut2030)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), populationLimit, false);
        }
        this.chromosomes.clear();
        this.chromosomes.addAll(chromosomes);
    }

    /**
     * Add a {@link Collection} of chromosomes to this {@link Population}.
     * @param chromosomeColl a {@link Collection} of chromosomes
     * @throws NumberIsTooLargeException if the population would exceed the population limit when
     * adding this chromosome
     * @since 3.1
     */
    public void addChromosomes(final Collection<Chromosome> chromosomeColl) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.addChromosomes_116");
        if (ROR_greater(AOR_plus(chromosomes.size(), chromosomeColl.size(), "org.apache.commons.math3.genetics.ListPopulation.addChromosomes_116", _mut2031, _mut2032, _mut2033, _mut2034), populationLimit, "org.apache.commons.math3.genetics.ListPopulation.addChromosomes_116", _mut2035, _mut2036, _mut2037, _mut2038, _mut2039)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), populationLimit, false);
        }
        this.chromosomes.addAll(chromosomeColl);
    }

    /**
     * Returns an unmodifiable list of the chromosomes in this population.
     * @return the unmodifiable list of chromosomes
     */
    public List<Chromosome> getChromosomes() {
        return Collections.unmodifiableList(chromosomes);
    }

    /**
     * Access the list of chromosomes.
     * @return the list of chromosomes
     * @since 3.1
     */
    protected List<Chromosome> getChromosomeList() {
        return chromosomes;
    }

    /**
     * Add the given chromosome to the population.
     *
     * @param chromosome the chromosome to add.
     * @throws NumberIsTooLargeException if the population would exceed the {@code populationLimit} after
     *   adding this chromosome
     */
    public void addChromosome(final Chromosome chromosome) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.addChromosome_148");
        if (ROR_greater_equals(chromosomes.size(), populationLimit, "org.apache.commons.math3.genetics.ListPopulation.addChromosome_148", _mut2040, _mut2041, _mut2042, _mut2043, _mut2044)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), populationLimit, false);
        }
        this.chromosomes.add(chromosome);
    }

    /**
     * Access the fittest chromosome in this population.
     * @return the fittest chromosome.
     */
    public Chromosome getFittestChromosome() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.getFittestChromosome_160");
        // best so far
        Chromosome bestChromosome = this.chromosomes.get(0);
        for (Chromosome chromosome : this.chromosomes) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.getFittestChromosome_160");
            if (ROR_greater(chromosome.compareTo(bestChromosome), 0, "org.apache.commons.math3.genetics.ListPopulation.getFittestChromosome_160", _mut2045, _mut2046, _mut2047, _mut2048, _mut2049)) {
                // better chromosome found
                bestChromosome = chromosome;
            }
        }
        return bestChromosome;
    }

    /**
     * Access the maximum population size.
     * @return the maximum population size.
     */
    public int getPopulationLimit() {
        return this.populationLimit;
    }

    /**
     * Sets the maximal population size.
     * @param populationLimit maximal population size.
     * @throws NotPositiveException if the population limit is not a positive number (&lt; 1)
     * @throws NumberIsTooSmallException if the new population size is smaller than the current number
     *   of chromosomes in the population
     */
    public void setPopulationLimit(final int populationLimit) throws NotPositiveException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.ListPopulation.setPopulationLimit_187");
        if (ROR_less_equals(populationLimit, 0, "org.apache.commons.math3.genetics.ListPopulation.setPopulationLimit_187", _mut2050, _mut2051, _mut2052, _mut2053, _mut2054)) {
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
        }
        if (ROR_less(populationLimit, chromosomes.size(), "org.apache.commons.math3.genetics.ListPopulation.setPopulationLimit_187", _mut2055, _mut2056, _mut2057, _mut2058, _mut2059)) {
            throw new NumberIsTooSmallException(populationLimit, chromosomes.size(), true);
        }
        this.populationLimit = populationLimit;
    }

    /**
     * Access the current population size.
     * @return the current population size.
     */
    public int getPopulationSize() {
        return this.chromosomes.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.chromosomes.toString();
    }

    /**
     * Returns an iterator over the unmodifiable list of chromosomes.
     * <p>Any call to {@link Iterator#remove()} will result in a {@link UnsupportedOperationException}.</p>
     *
     * @return chromosome iterator
     */
    public Iterator<Chromosome> iterator() {
        return getChromosomes().iterator();
    }
}
