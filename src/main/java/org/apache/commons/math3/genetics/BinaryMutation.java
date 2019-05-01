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
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Mutation for {@link BinaryChromosome}s. Randomly changes one gene.
 *
 * @since 2.0
 */
public class BinaryMutation implements MutationPolicy {

    @Conditional
    public static boolean _mut2320 = false, _mut2321 = false, _mut2322 = false, _mut2323 = false, _mut2324 = false;

    /**
     * Mutate the given chromosome. Randomly changes one gene.
     *
     * @param original the original chromosome.
     * @return the mutated chromosome.
     * @throws MathIllegalArgumentException if <code>original</code> is not an instance of {@link BinaryChromosome}.
     */
    public Chromosome mutate(Chromosome original) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryMutation.mutate_39");
        if (!(original instanceof BinaryChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_BINARY_CHROMOSOME);
        }
        BinaryChromosome origChrom = (BinaryChromosome) original;
        List<Integer> newRepr = new ArrayList<Integer>(origChrom.getRepresentation());
        // randomly select a gene
        int geneIndex = GeneticAlgorithm.getRandomGenerator().nextInt(origChrom.getLength());
        // and change it
        newRepr.set(geneIndex, ROR_equals(origChrom.getRepresentation().get(geneIndex), 0, "org.apache.commons.math3.genetics.BinaryMutation.mutate_39", _mut2320, _mut2321, _mut2322, _mut2323, _mut2324) ? 1 : 0);
        Chromosome newChrom = origChrom.newFixedLengthChromosome(newRepr);
        return newChrom;
    }
}
