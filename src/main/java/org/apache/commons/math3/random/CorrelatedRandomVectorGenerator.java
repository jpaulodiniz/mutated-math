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
package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RectangularCholeskyDecomposition;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class CorrelatedRandomVectorGenerator implements RandomVectorGenerator {

    @Conditional
    public static boolean _mut52783 = false, _mut52784 = false, _mut52785 = false, _mut52786 = false, _mut52787 = false, _mut52788 = false, _mut52789 = false, _mut52790 = false, _mut52791 = false, _mut52792 = false, _mut52793 = false, _mut52794 = false, _mut52795 = false, _mut52796 = false, _mut52797 = false, _mut52798 = false, _mut52799 = false, _mut52800 = false, _mut52801 = false, _mut52802 = false, _mut52803 = false, _mut52804 = false, _mut52805 = false, _mut52806 = false, _mut52807 = false, _mut52808 = false, _mut52809 = false, _mut52810 = false, _mut52811 = false;

    /**
     * Mean vector.
     */
    private final double[] mean;

    /**
     * Underlying generator.
     */
    private final NormalizedRandomGenerator generator;

    /**
     * Storage for the normalized vector.
     */
    private final double[] normalized;

    /**
     * Root of the covariance matrix.
     */
    private final RealMatrix root;

    /**
     * Builds a correlated random vector generator from its mean
     * vector and covariance matrix.
     *
     * @param mean Expected mean values for all components.
     * @param covariance Covariance matrix.
     * @param small Diagonal elements threshold under which  column are
     * considered to be dependent on previous ones and are discarded
     * @param generator underlying generator for uncorrelated normalized
     * components.
     * @throws org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException
     * if the covariance matrix is not strictly positive definite.
     * @throws DimensionMismatchException if the mean and covariance
     * arrays dimensions do not match.
     */
    public CorrelatedRandomVectorGenerator(double[] mean, RealMatrix covariance, double small, NormalizedRandomGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.CorrelatedRandomVectorGenerator_86");
        int order = covariance.getRowDimension();
        if (ROR_not_equals(mean.length, order, "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.CorrelatedRandomVectorGenerator_86", _mut52783, _mut52784, _mut52785, _mut52786, _mut52787)) {
            throw new DimensionMismatchException(mean.length, order);
        }
        this.mean = mean.clone();
        final RectangularCholeskyDecomposition decomposition = new RectangularCholeskyDecomposition(covariance, small);
        root = decomposition.getRootMatrix();
        this.generator = generator;
        normalized = new double[decomposition.getRank()];
    }

    /**
     * Builds a null mean random correlated vector generator from its
     * covariance matrix.
     *
     * @param covariance Covariance matrix.
     * @param small Diagonal elements threshold under which  column are
     * considered to be dependent on previous ones and are discarded.
     * @param generator Underlying generator for uncorrelated normalized
     * components.
     * @throws org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException
     * if the covariance matrix is not strictly positive definite.
     */
    public CorrelatedRandomVectorGenerator(RealMatrix covariance, double small, NormalizedRandomGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.CorrelatedRandomVectorGenerator_116");
        int order = covariance.getRowDimension();
        mean = new double[order];
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.CorrelatedRandomVectorGenerator_116", _mut52788, _mut52789, _mut52790, _mut52791, _mut52792); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.CorrelatedRandomVectorGenerator_116");
            mean[i] = 0;
        }
        final RectangularCholeskyDecomposition decomposition = new RectangularCholeskyDecomposition(covariance, small);
        root = decomposition.getRootMatrix();
        this.generator = generator;
        normalized = new double[decomposition.getRank()];
    }

    /**
     * Get the underlying normalized components generator.
     * @return underlying uncorrelated components generator
     */
    public NormalizedRandomGenerator getGenerator() {
        return generator;
    }

    /**
     * Get the rank of the covariance matrix.
     * The rank is the number of independent rows in the covariance
     * matrix, it is also the number of columns of the root matrix.
     * @return rank of the square matrix.
     * @see #getRootMatrix()
     */
    public int getRank() {
        return normalized.length;
    }

    /**
     * Get the root of the covariance matrix.
     * The root is the rectangular matrix <code>B</code> such that
     * the covariance matrix is equal to <code>B.B<sup>T</sup></code>
     * @return root of the square matrix
     * @see #getRank()
     */
    public RealMatrix getRootMatrix() {
        return root;
    }

    /**
     * Generate a correlated random vector.
     * @return a random vector as an array of double. The returned array
     * is created at each call, the caller can do what it wants with it.
     */
    public double[] nextVector() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164");
        // generate uncorrelated vector
        for (int i = 0; ROR_less(i, normalized.length, "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164", _mut52793, _mut52794, _mut52795, _mut52796, _mut52797); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164");
            normalized[i] = generator.nextNormalizedDouble();
        }
        // compute correlated vector
        double[] correlated = new double[mean.length];
        for (int i = 0; ROR_less(i, correlated.length, "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164", _mut52807, _mut52808, _mut52809, _mut52810, _mut52811); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164");
            correlated[i] = mean[i];
            for (int j = 0; ROR_less(j, root.getColumnDimension(), "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164", _mut52802, _mut52803, _mut52804, _mut52805, _mut52806); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164");
                correlated[i] += AOR_multiply(root.getEntry(i, j), normalized[j], "org.apache.commons.math3.random.CorrelatedRandomVectorGenerator.nextVector_164", _mut52798, _mut52799, _mut52800, _mut52801);
            }
        }
        return correlated;
    }
}
