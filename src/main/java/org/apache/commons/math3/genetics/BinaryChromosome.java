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
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Chromosome represented by a vector of 0s and 1s.
 *
 * @since 2.0
 */
public abstract class BinaryChromosome extends AbstractListChromosome<Integer> {

    @Conditional
    public static boolean _mut2418 = false, _mut2419 = false, _mut2420 = false, _mut2421 = false, _mut2422 = false, _mut2423 = false, _mut2424 = false, _mut2425 = false, _mut2426 = false, _mut2427 = false, _mut2428 = false, _mut2429 = false, _mut2430 = false, _mut2431 = false, _mut2432 = false, _mut2433 = false, _mut2434 = false, _mut2435 = false, _mut2436 = false, _mut2437 = false, _mut2438 = false, _mut2439 = false, _mut2440 = false, _mut2441 = false, _mut2442 = false, _mut2443 = false;

    /**
     * Constructor.
     * @param representation list of {0,1} values representing the chromosome
     * @throws InvalidRepresentationException iff the <code>representation</code> can not represent a valid chromosome
     */
    public BinaryChromosome(List<Integer> representation) throws InvalidRepresentationException {
        super(representation);
    }

    /**
     * Constructor.
     * @param representation array of {0,1} values representing the chromosome
     * @throws InvalidRepresentationException iff the <code>representation</code> can not represent a valid chromosome
     */
    public BinaryChromosome(Integer[] representation) throws InvalidRepresentationException {
        super(representation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52");
        for (int i : chromosomeRepresentation) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52");
            if ((_mut2428 ? (ROR_less(i, 0, "org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52", _mut2418, _mut2419, _mut2420, _mut2421, _mut2422) && ROR_greater(i, 1, "org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52", _mut2423, _mut2424, _mut2425, _mut2426, _mut2427)) : (ROR_less(i, 0, "org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52", _mut2418, _mut2419, _mut2420, _mut2421, _mut2422) || ROR_greater(i, 1, "org.apache.commons.math3.genetics.BinaryChromosome.checkValidity_52", _mut2423, _mut2424, _mut2425, _mut2426, _mut2427)))) {
                throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, i);
            }
        }
    }

    /**
     * Returns a representation of a random binary array of length <code>length</code>.
     * @param length length of the array
     * @return a random binary array of length <code>length</code>
     */
    public static List<Integer> randomBinaryRepresentation(int length) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.randomBinaryRepresentation_67");
        // random binary list
        List<Integer> rList = new ArrayList<Integer>(length);
        for (int j = 0; ROR_less(j, length, "org.apache.commons.math3.genetics.BinaryChromosome.randomBinaryRepresentation_67", _mut2429, _mut2430, _mut2431, _mut2432, _mut2433); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.randomBinaryRepresentation_67");
            rList.add(GeneticAlgorithm.getRandomGenerator().nextInt(2));
        }
        return rList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSame(Chromosome another) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.isSame_77");
        // type check
        if (!(another instanceof BinaryChromosome)) {
            return false;
        }
        BinaryChromosome anotherBc = (BinaryChromosome) another;
        // size check
        if (ROR_not_equals(getLength(), anotherBc.getLength(), "org.apache.commons.math3.genetics.BinaryChromosome.isSame_77", _mut2434, _mut2435, _mut2436, _mut2437, _mut2438)) {
            return false;
        }
        for (int i = 0; ROR_less(i, getRepresentation().size(), "org.apache.commons.math3.genetics.BinaryChromosome.isSame_77", _mut2439, _mut2440, _mut2441, _mut2442, _mut2443); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.BinaryChromosome.isSame_77");
            if (!(getRepresentation().get(i).equals(anotherBc.getRepresentation().get(i)))) {
                return false;
            }
        }
        // all is ok
        return true;
    }
}
