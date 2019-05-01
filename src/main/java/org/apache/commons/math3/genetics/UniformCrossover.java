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
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Perform Uniform Crossover [UX] on the specified chromosomes. A fixed mixing
 * ratio is used to combine genes from the first and second parents, e.g. using a
 * ratio of 0.5 would result in approximately 50% of genes coming from each
 * parent. This is typically a poor method of crossover, but empirical evidence
 * suggests that it is more exploratory and results in a larger part of the
 * problem space being searched.
 * <p>
 * This crossover policy evaluates each gene of the parent chromosomes by chosing a
 * uniform random number {@code p} in the range [0, 1]. If {@code p} &lt; {@code ratio},
 * the parent genes are swapped. This means with a ratio of 0.7, 30% of the genes from the
 * first parent and 70% from the second parent will be selected for the first offspring (and
 * vice versa for the second offspring).
 * <p>
 * This policy works only on {@link AbstractListChromosome}, and therefore it
 * is parameterized by T. Moreover, the chromosomes must have same lengths.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Crossover_%28genetic_algorithm%29">Crossover techniques (Wikipedia)</a>
 * @see <a href="http://www.obitko.com/tutorials/genetic-algorithms/crossover-mutation.php">Crossover (Obitko.com)</a>
 * @see <a href="http://www.tomaszgwiazda.com/uniformX.htm">Uniform crossover</a>
 * @param <T> generic type of the {@link AbstractListChromosome}s for crossover
 * @since 3.1
 */
public class UniformCrossover<T> implements CrossoverPolicy {

    @Conditional
    public static boolean _mut2391 = false, _mut2392 = false, _mut2393 = false, _mut2394 = false, _mut2395 = false, _mut2396 = false, _mut2397 = false, _mut2398 = false, _mut2399 = false, _mut2400 = false, _mut2401 = false, _mut2402 = false, _mut2403 = false, _mut2404 = false, _mut2405 = false, _mut2406 = false, _mut2407 = false, _mut2408 = false, _mut2409 = false, _mut2410 = false, _mut2411 = false, _mut2412 = false, _mut2413 = false, _mut2414 = false, _mut2415 = false, _mut2416 = false, _mut2417 = false;

    /**
     * The mixing ratio.
     */
    private final double ratio;

    /**
     * Creates a new {@link UniformCrossover} policy using the given mixing ratio.
     *
     * @param ratio the mixing ratio
     * @throws OutOfRangeException if the mixing ratio is outside the [0, 1] range
     */
    public UniformCrossover(final double ratio) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.UniformCrossover.UniformCrossover_62");
        if ((_mut2401 ? (ROR_less(ratio, 0.0d, "org.apache.commons.math3.genetics.UniformCrossover.UniformCrossover_62", _mut2391, _mut2392, _mut2393, _mut2394, _mut2395) && ROR_greater(ratio, 1.0d, "org.apache.commons.math3.genetics.UniformCrossover.UniformCrossover_62", _mut2396, _mut2397, _mut2398, _mut2399, _mut2400)) : (ROR_less(ratio, 0.0d, "org.apache.commons.math3.genetics.UniformCrossover.UniformCrossover_62", _mut2391, _mut2392, _mut2393, _mut2394, _mut2395) || ROR_greater(ratio, 1.0d, "org.apache.commons.math3.genetics.UniformCrossover.UniformCrossover_62", _mut2396, _mut2397, _mut2398, _mut2399, _mut2400)))) {
            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, ratio, 0.0d, 1.0d);
        }
        this.ratio = ratio;
    }

    /**
     * Returns the mixing ratio used by this {@link CrossoverPolicy}.
     *
     * @return the mixing ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalArgumentException iff one of the chromosomes is
     *   not an instance of {@link AbstractListChromosome}
     * @throws DimensionMismatchException if the length of the two chromosomes is different
     */
    @SuppressWarnings("unchecked")
    public ChromosomePair crossover(final Chromosome first, final Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.UniformCrossover.crossover_85");
        if (!((_mut2402 ? (first instanceof AbstractListChromosome<?> || second instanceof AbstractListChromosome<?>) : (first instanceof AbstractListChromosome<?> && second instanceof AbstractListChromosome<?>)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME);
        }
        return mate((AbstractListChromosome<T>) first, (AbstractListChromosome<T>) second);
    }

    /**
     * Helper for {@link #crossover(Chromosome, Chromosome)}. Performs the actual crossover.
     *
     * @param first the first chromosome
     * @param second the second chromosome
     * @return the pair of new chromosomes that resulted from the crossover
     * @throws DimensionMismatchException if the length of the two chromosomes is different
     */
    private ChromosomePair mate(final AbstractListChromosome<T> first, final AbstractListChromosome<T> second) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.UniformCrossover.mate_103");
        final int length = first.getLength();
        if (ROR_not_equals(length, second.getLength(), "org.apache.commons.math3.genetics.UniformCrossover.mate_103", _mut2403, _mut2404, _mut2405, _mut2406, _mut2407)) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        // array representations of the parents
        final List<T> parent1Rep = first.getRepresentation();
        final List<T> parent2Rep = second.getRepresentation();
        // and of the children
        final List<T> child1Rep = new ArrayList<T>(length);
        final List<T> child2Rep = new ArrayList<T>(length);
        final RandomGenerator random = GeneticAlgorithm.getRandomGenerator();
        for (int index = 0; ROR_less(index, length, "org.apache.commons.math3.genetics.UniformCrossover.mate_103", _mut2413, _mut2414, _mut2415, _mut2416, _mut2417); index++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.UniformCrossover.mate_103");
            if (ROR_less(random.nextDouble(), ratio, "org.apache.commons.math3.genetics.UniformCrossover.mate_103", _mut2408, _mut2409, _mut2410, _mut2411, _mut2412)) {
                // swap the bits -> take other parent
                child1Rep.add(parent2Rep.get(index));
                child2Rep.add(parent1Rep.get(index));
            } else {
                child1Rep.add(parent1Rep.get(index));
                child2Rep.add(parent2Rep.get(index));
            }
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}
