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
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * One point crossover policy. A random crossover point is selected and the
 * first part from each parent is copied to the corresponding child, and the
 * second parts are copied crosswise.
 *
 * Example:
 * <pre>
 * -C- denotes a crossover point
 *                   -C-                                 -C-
 * p1 = (1 0 1 0 0 1  | 0 1 1)    X    p2 = (0 1 1 0 1 0  | 1 1 1)
 *      \------------/ \-----/              \------------/ \-----/
 *            ||         (*)                       ||        (**)
 *            VV         (**)                      VV        (*)
 *      /------------\ /-----\              /------------\ /-----\
 * c1 = (1 0 1 0 0 1  | 1 1 1)    X    c2 = (0 1 1 0 1 0  | 0 1 1)
 * </pre>
 *
 * This policy works only on {@link AbstractListChromosome}, and therefore it
 * is parameterized by T. Moreover, the chromosomes must have same lengths.
 *
 * @param <T> generic type of the {@link AbstractListChromosome}s for crossover
 * @since 2.0
 */
public class OnePointCrossover<T> implements CrossoverPolicy {

    @Conditional
    public static boolean _mut2217 = false, _mut2218 = false, _mut2219 = false, _mut2220 = false, _mut2221 = false, _mut2222 = false, _mut2223 = false, _mut2224 = false, _mut2225 = false, _mut2226 = false, _mut2227 = false, _mut2228 = false, _mut2229 = false, _mut2230 = false, _mut2231 = false, _mut2232 = false, _mut2233 = false, _mut2234 = false, _mut2235 = false, _mut2236 = false, _mut2237 = false, _mut2238 = false, _mut2239 = false, _mut2240 = false;

    /**
     * Performs one point crossover. A random crossover point is selected and the
     * first part from each parent is copied to the corresponding child, and the
     * second parts are copied crosswise.
     *
     * Example:
     * <pre>
     * -C- denotes a crossover point
     *                   -C-                                 -C-
     * p1 = (1 0 1 0 0 1  | 0 1 1)    X    p2 = (0 1 1 0 1 0  | 1 1 1)
     *      \------------/ \-----/              \------------/ \-----/
     *            ||         (*)                       ||        (**)
     *            VV         (**)                      VV        (*)
     *      /------------\ /-----\              /------------\ /-----\
     * c1 = (1 0 1 0 0 1  | 1 1 1)    X    c2 = (0 1 1 0 1 0  | 0 1 1)
     * </pre>
     *
     * @param first first parent (p1)
     * @param second second parent (p2)
     * @return pair of two children (c1,c2)
     * @throws MathIllegalArgumentException iff one of the chromosomes is
     *   not an instance of {@link AbstractListChromosome}
     * @throws DimensionMismatchException if the length of the two chromosomes is different
     */
    // OK because of instanceof checks
    @SuppressWarnings("unchecked")
    public ChromosomePair crossover(final Chromosome first, final Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.OnePointCrossover.crossover_77");
        if (!((_mut2217 ? (first instanceof AbstractListChromosome<?> || second instanceof AbstractListChromosome<?>) : (first instanceof AbstractListChromosome<?> && second instanceof AbstractListChromosome<?>)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME);
        }
        return crossover((AbstractListChromosome<T>) first, (AbstractListChromosome<T>) second);
    }

    /**
     * Helper for {@link #crossover(Chromosome, Chromosome)}. Performs the actual crossover.
     *
     * @param first the first chromosome.
     * @param second the second chromosome.
     * @return the pair of new chromosomes that resulted from the crossover.
     * @throws DimensionMismatchException if the length of the two chromosomes is different
     */
    private ChromosomePair crossover(final AbstractListChromosome<T> first, final AbstractListChromosome<T> second) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.OnePointCrossover.crossover_96");
        final int length = first.getLength();
        if (ROR_not_equals(length, second.getLength(), "org.apache.commons.math3.genetics.OnePointCrossover.crossover_96", _mut2218, _mut2219, _mut2220, _mut2221, _mut2222)) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        // array representations of the parents
        final List<T> parent1Rep = first.getRepresentation();
        final List<T> parent2Rep = second.getRepresentation();
        // and of the children
        final List<T> child1Rep = new ArrayList<T>(length);
        final List<T> child2Rep = new ArrayList<T>(length);
        // select a crossover point at random (0 and length makes no sense)
        final int crossoverIndex = AOR_plus(1, (GeneticAlgorithm.getRandomGenerator().nextInt(AOR_minus(length, 2, "org.apache.commons.math3.genetics.OnePointCrossover.crossover_96", _mut2223, _mut2224, _mut2225, _mut2226))), "org.apache.commons.math3.genetics.OnePointCrossover.crossover_96", _mut2227, _mut2228, _mut2229, _mut2230);
        // copy the first part
        for (int i = 0; ROR_less(i, crossoverIndex, "org.apache.commons.math3.genetics.OnePointCrossover.crossover_96", _mut2231, _mut2232, _mut2233, _mut2234, _mut2235); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.OnePointCrossover.crossover_96");
            child1Rep.add(parent1Rep.get(i));
            child2Rep.add(parent2Rep.get(i));
        }
        // and switch the second part
        for (int i = crossoverIndex; ROR_less(i, length, "org.apache.commons.math3.genetics.OnePointCrossover.crossover_96", _mut2236, _mut2237, _mut2238, _mut2239, _mut2240); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.OnePointCrossover.crossover_96");
            child1Rep.add(parent2Rep.get(i));
            child2Rep.add(parent1Rep.get(i));
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}
