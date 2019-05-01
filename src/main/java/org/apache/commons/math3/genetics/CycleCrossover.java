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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Cycle Crossover [CX] builds offspring from <b>ordered</b> chromosomes by identifying cycles
 * between two parent chromosomes. To form the children, the cycles are copied from the
 * respective parents.
 * <p>
 * To form a cycle the following procedure is applied:
 * <ol>
 *   <li>start with the first gene of parent 1</li>
 *   <li>look at the gene at the same position of parent 2</li>
 *   <li>go to the position with the same gene in parent 1</li>
 *   <li>add this gene index to the cycle</li>
 *   <li>repeat the steps 2-5 until we arrive at the starting gene of this cycle</li>
 * </ol>
 * The indices that form a cycle are then used to form the children in alternating order, i.e.
 * in cycle 1, the genes of parent 1 are copied to child 1, while in cycle 2 the genes of parent 1
 * are copied to child 2, and so forth ...
 * </p>
 *
 * Example (zero-start cycle):
 * <pre>
 * p1 = (8 4 7 3 6 2 5 1 9 0)    X   c1 = (8 1 2 3 4 5 6 7 9 0)
 * p2 = (0 1 2 3 4 5 6 7 8 9)    X   c2 = (0 4 7 3 6 2 5 1 8 9)
 *
 * cycle 1: 8 0 9
 * cycle 2: 4 1 7 2 5 6
 * cycle 3: 3
 * </pre>
 *
 * This policy works only on {@link AbstractListChromosome}, and therefore it
 * is parameterized by T. Moreover, the chromosomes must have same lengths.
 *
 * @see <a href="http://www.rubicite.com/Tutorials/GeneticAlgorithms/CrossoverOperators/CycleCrossoverOperator.aspx">
 * Cycle Crossover Operator</a>
 *
 * @param <T> generic type of the {@link AbstractListChromosome}s for crossover
 * @since 3.1
 */
public class CycleCrossover<T> implements CrossoverPolicy {

    @Conditional
    public static boolean _mut2065 = false, _mut2066 = false, _mut2067 = false, _mut2068 = false, _mut2069 = false, _mut2070 = false, _mut2071 = false, _mut2072 = false, _mut2073 = false, _mut2074 = false, _mut2075 = false, _mut2076 = false, _mut2077 = false, _mut2078 = false, _mut2079 = false, _mut2080 = false, _mut2081 = false, _mut2082 = false, _mut2083 = false, _mut2084 = false, _mut2085 = false, _mut2086 = false, _mut2087 = false, _mut2088 = false, _mut2089 = false, _mut2090 = false, _mut2091 = false, _mut2092 = false, _mut2093 = false, _mut2094 = false, _mut2095 = false, _mut2096 = false, _mut2097 = false, _mut2098 = false, _mut2099 = false, _mut2100 = false, _mut2101 = false, _mut2102 = false, _mut2103 = false, _mut2104 = false, _mut2105 = false, _mut2106 = false, _mut2107 = false, _mut2108 = false;

    /**
     * If the start index shall be chosen randomly.
     */
    private final boolean randomStart;

    /**
     * Creates a new {@link CycleCrossover} policy.
     */
    public CycleCrossover() {
        this(false);
    }

    /**
     * Creates a new {@link CycleCrossover} policy using the given {@code randomStart} behavior.
     *
     * @param randomStart whether the start index shall be chosen randomly or be set to 0
     */
    public CycleCrossover(final boolean randomStart) {
        this.randomStart = randomStart;
    }

    /**
     * Returns whether the starting index is chosen randomly or set to zero.
     *
     * @return {@code true} if the starting index is chosen randomly, {@code false} otherwise
     */
    public boolean isRandomStart() {
        return randomStart;
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalArgumentException if the chromosomes are not an instance of {@link AbstractListChromosome}
     * @throws DimensionMismatchException if the length of the two chromosomes is different
     */
    @SuppressWarnings("unchecked")
    public ChromosomePair crossover(final Chromosome first, final Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.crossover_101");
        if (!((_mut2065 ? (first instanceof AbstractListChromosome<?> || second instanceof AbstractListChromosome<?>) : (first instanceof AbstractListChromosome<?> && second instanceof AbstractListChromosome<?>)))) {
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
    protected ChromosomePair mate(final AbstractListChromosome<T> first, final AbstractListChromosome<T> second) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.mate_119");
        final int length = first.getLength();
        if (ROR_not_equals(length, second.getLength(), "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2066, _mut2067, _mut2068, _mut2069, _mut2070)) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        // array representations of the parents
        final List<T> parent1Rep = first.getRepresentation();
        final List<T> parent2Rep = second.getRepresentation();
        // and of the children: do a crossover copy to simplify the later processing
        final List<T> child1Rep = new ArrayList<T>(second.getRepresentation());
        final List<T> child2Rep = new ArrayList<T>(first.getRepresentation());
        // the set of all visited indices so far
        final Set<Integer> visitedIndices = new HashSet<Integer>(length);
        // the indices of the current cycle
        final List<Integer> indices = new ArrayList<Integer>(length);
        // determine the starting index
        int idx = randomStart ? GeneticAlgorithm.getRandomGenerator().nextInt(length) : 0;
        int cycle = 1;
        while (ROR_less(visitedIndices.size(), length, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2104, _mut2105, _mut2106, _mut2107, _mut2108)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.mate_119");
            indices.add(idx);
            T item = parent2Rep.get(idx);
            idx = parent1Rep.indexOf(item);
            while (ROR_not_equals(idx, indices.get(0), "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2071, _mut2072, _mut2073, _mut2074, _mut2075)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.mate_119");
                // add that index to the cycle indices
                indices.add(idx);
                // get the item in the second parent at that index
                item = parent2Rep.get(idx);
                // get the index of that item in the first parent
                idx = parent1Rep.indexOf(item);
            }
            // for even cycles: swap the child elements on the indices found in this cycle
            if (ROR_not_equals(AOR_remainder(cycle++, 2, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2076, _mut2077, _mut2078, _mut2079), 0, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2080, _mut2081, _mut2082, _mut2083, _mut2084)) {
                for (int i : indices) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.mate_119");
                    T tmp = child1Rep.get(i);
                    child1Rep.set(i, child2Rep.get(i));
                    child2Rep.set(i, tmp);
                }
            }
            visitedIndices.addAll(indices);
            // find next starting index: last one + 1 until we find an unvisited index
            idx = AOR_remainder((AOR_plus(indices.get(0), 1, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2085, _mut2086, _mut2087, _mut2088)), length, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2089, _mut2090, _mut2091, _mut2092);
            while ((_mut2103 ? (visitedIndices.contains(idx) || ROR_less(visitedIndices.size(), length, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2098, _mut2099, _mut2100, _mut2101, _mut2102)) : (visitedIndices.contains(idx) && ROR_less(visitedIndices.size(), length, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2098, _mut2099, _mut2100, _mut2101, _mut2102)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.CycleCrossover.mate_119");
                idx++;
                if (ROR_greater_equals(idx, length, "org.apache.commons.math3.genetics.CycleCrossover.mate_119", _mut2093, _mut2094, _mut2095, _mut2096, _mut2097)) {
                    idx = 0;
                }
            }
            indices.clear();
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}
