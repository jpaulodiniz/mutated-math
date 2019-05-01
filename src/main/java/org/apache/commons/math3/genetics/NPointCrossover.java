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
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * N-point crossover policy. For each iteration a random crossover point is
 * selected and the first part from each parent is copied to the corresponding
 * child, and the second parts are copied crosswise.
 *
 * Example (2-point crossover):
 * <pre>
 * -C- denotes a crossover point
 *           -C-       -C-                         -C-        -C-
 * p1 = (1 0  | 1 0 0 1 | 0 1 1)    X    p2 = (0 1  | 1 0 1 0  | 1 1 1)
 *      \----/ \-------/ \-----/              \----/ \--------/ \-----/
 *        ||      (*)       ||                  ||      (**)       ||
 *        VV      (**)      VV                  VV      (*)        VV
 *      /----\ /--------\ /-----\             /----\ /--------\ /-----\
 * c1 = (1 0  | 1 0 1 0  | 0 1 1)    X   c2 = (0 1  | 1 0 0 1  | 0 1 1)
 * </pre>
 *
 * This policy works only on {@link AbstractListChromosome}, and therefore it
 * is parameterized by T. Moreover, the chromosomes must have same lengths.
 *
 * @param <T> generic type of the {@link AbstractListChromosome}s for crossover
 * @since 3.1
 */
public class NPointCrossover<T> implements CrossoverPolicy {

    @Conditional
    public static boolean _mut2325 = false, _mut2326 = false, _mut2327 = false, _mut2328 = false, _mut2329 = false, _mut2330 = false, _mut2331 = false, _mut2332 = false, _mut2333 = false, _mut2334 = false, _mut2335 = false, _mut2336 = false, _mut2337 = false, _mut2338 = false, _mut2339 = false, _mut2340 = false, _mut2341 = false, _mut2342 = false, _mut2343 = false, _mut2344 = false, _mut2345 = false, _mut2346 = false, _mut2347 = false, _mut2348 = false, _mut2349 = false, _mut2350 = false, _mut2351 = false, _mut2352 = false, _mut2353 = false, _mut2354 = false, _mut2355 = false, _mut2356 = false, _mut2357 = false, _mut2358 = false, _mut2359 = false, _mut2360 = false, _mut2361 = false, _mut2362 = false, _mut2363 = false, _mut2364 = false, _mut2365 = false, _mut2366 = false, _mut2367 = false, _mut2368 = false, _mut2369 = false, _mut2370 = false, _mut2371 = false;

    /**
     * The number of crossover points.
     */
    private final int crossoverPoints;

    /**
     * Creates a new {@link NPointCrossover} policy using the given number of points.
     * <p>
     * <b>Note</b>: the number of crossover points must be &lt; <code>chromosome length - 1</code>.
     * This condition can only be checked at runtime, as the chromosome length is not known in advance.
     *
     * @param crossoverPoints the number of crossover points
     * @throws NotStrictlyPositiveException if the number of {@code crossoverPoints} is not strictly positive
     */
    public NPointCrossover(final int crossoverPoints) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.NPointCrossover_66");
        if (ROR_less_equals(crossoverPoints, 0, "org.apache.commons.math3.genetics.NPointCrossover.NPointCrossover_66", _mut2325, _mut2326, _mut2327, _mut2328, _mut2329)) {
            throw new NotStrictlyPositiveException(crossoverPoints);
        }
        this.crossoverPoints = crossoverPoints;
    }

    /**
     * Returns the number of crossover points used by this {@link CrossoverPolicy}.
     *
     * @return the number of crossover points
     */
    public int getCrossoverPoints() {
        return crossoverPoints;
    }

    /**
     * Performs a N-point crossover. N random crossover points are selected and are used
     * to divide the parent chromosomes into segments. The segments are copied in alternate
     * order from the two parents to the corresponding child chromosomes.
     *
     * Example (2-point crossover):
     * <pre>
     * -C- denotes a crossover point
     *           -C-       -C-                         -C-        -C-
     * p1 = (1 0  | 1 0 0 1 | 0 1 1)    X    p2 = (0 1  | 1 0 1 0  | 1 1 1)
     *      \----/ \-------/ \-----/              \----/ \--------/ \-----/
     *        ||      (*)       ||                  ||      (**)       ||
     *        VV      (**)      VV                  VV      (*)        VV
     *      /----\ /--------\ /-----\             /----\ /--------\ /-----\
     * c1 = (1 0  | 1 0 1 0  | 0 1 1)    X   c2 = (0 1  | 1 0 0 1  | 0 1 1)
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.crossover_106");
        if (!((_mut2330 ? (first instanceof AbstractListChromosome<?> || second instanceof AbstractListChromosome<?>) : (first instanceof AbstractListChromosome<?> && second instanceof AbstractListChromosome<?>)))) {
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
     * @throws NumberIsTooLargeException if the number of crossoverPoints is too large for the actual chromosomes
     */
    private ChromosomePair mate(final AbstractListChromosome<T> first, final AbstractListChromosome<T> second) throws DimensionMismatchException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.mate_125");
        final int length = first.getLength();
        if (ROR_not_equals(length, second.getLength(), "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2331, _mut2332, _mut2333, _mut2334, _mut2335)) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        if (ROR_greater_equals(crossoverPoints, length, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2336, _mut2337, _mut2338, _mut2339, _mut2340)) {
            throw new NumberIsTooLargeException(crossoverPoints, length, false);
        }
        // array representations of the parents
        final List<T> parent1Rep = first.getRepresentation();
        final List<T> parent2Rep = second.getRepresentation();
        // and of the children
        final List<T> child1Rep = new ArrayList<T>(length);
        final List<T> child2Rep = new ArrayList<T>(length);
        final RandomGenerator random = GeneticAlgorithm.getRandomGenerator();
        List<T> c1 = child1Rep;
        List<T> c2 = child2Rep;
        int remainingPoints = crossoverPoints;
        int lastIndex = 0;
        for (int i = 0; ROR_less(i, crossoverPoints, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2362, _mut2363, _mut2364, _mut2365, _mut2366); i++, remainingPoints--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.mate_125");
            // select the next crossover point at random
            final int crossoverIndex = AOR_plus(AOR_plus(1, lastIndex, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2341, _mut2342, _mut2343, _mut2344), random.nextInt(AOR_minus(AOR_minus(length, lastIndex, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2345, _mut2346, _mut2347, _mut2348), remainingPoints, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2349, _mut2350, _mut2351, _mut2352)), "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2353, _mut2354, _mut2355, _mut2356);
            // copy the current segment
            for (int j = lastIndex; ROR_less(j, crossoverIndex, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2357, _mut2358, _mut2359, _mut2360, _mut2361); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.mate_125");
                c1.add(parent1Rep.get(j));
                c2.add(parent2Rep.get(j));
            }
            // swap the children for the next segment
            List<T> tmp = c1;
            c1 = c2;
            c2 = tmp;
            lastIndex = crossoverIndex;
        }
        // copy the last segment
        for (int j = lastIndex; ROR_less(j, length, "org.apache.commons.math3.genetics.NPointCrossover.mate_125", _mut2367, _mut2368, _mut2369, _mut2370, _mut2371); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.NPointCrossover.mate_125");
            c1.add(parent1Rep.get(j));
            c2.add(parent2Rep.get(j));
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}
