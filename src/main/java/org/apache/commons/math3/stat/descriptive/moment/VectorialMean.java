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
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Returns the arithmetic mean of the available vectors.
 * @since 1.2
 */
public class VectorialMean implements Serializable {

    @Conditional
    public static boolean _mut3160 = false, _mut3161 = false, _mut3162 = false, _mut3163 = false, _mut3164 = false, _mut3165 = false, _mut3166 = false, _mut3167 = false, _mut3168 = false, _mut3169 = false, _mut3170 = false, _mut3171 = false, _mut3172 = false, _mut3173 = false, _mut3174 = false, _mut3175 = false, _mut3176 = false, _mut3177 = false, _mut3178 = false, _mut3179 = false, _mut3180 = false, _mut3181 = false, _mut3182 = false, _mut3183 = false, _mut3184 = false, _mut3185 = false, _mut3186 = false, _mut3187 = false, _mut3188 = false, _mut3189 = false, _mut3190 = false, _mut3191 = false, _mut3192 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 8223009086481006892L;

    /**
     * Means for each component.
     */
    private final Mean[] means;

    /**
     * Constructs a VectorialMean.
     * @param dimension vectors dimension
     */
    public VectorialMean(int dimension) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.VectorialMean_39");
        means = new Mean[dimension];
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.VectorialMean_39", _mut3160, _mut3161, _mut3162, _mut3163, _mut3164); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.VectorialMean_39");
            means[i] = new Mean();
        }
    }

    /**
     * Add a new vector to the sample.
     * @param v vector to add
     * @throws DimensionMismatchException if the vector does not have the right dimension
     */
    public void increment(double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.increment_51");
        if (ROR_not_equals(v.length, means.length, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.increment_51", _mut3165, _mut3166, _mut3167, _mut3168, _mut3169)) {
            throw new DimensionMismatchException(v.length, means.length);
        }
        for (int i = 0; ROR_less(i, v.length, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.increment_51", _mut3170, _mut3171, _mut3172, _mut3173, _mut3174); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.increment_51");
            means[i].increment(v[i]);
        }
    }

    /**
     * Get the mean vector.
     * @return mean vector
     */
    public double[] getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.getResult_64");
        double[] result = new double[means.length];
        for (int i = 0; ROR_less(i, result.length, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.getResult_64", _mut3175, _mut3176, _mut3177, _mut3178, _mut3179); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.getResult_64");
            result[i] = means[i].getResult();
        }
        return result;
    }

    /**
     * Get the number of vectors in the sample.
     * @return number of vectors in the sample
     */
    public long getN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.getN_76");
        return (ROR_equals(means.length, 0, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.getN_76", _mut3180, _mut3181, _mut3182, _mut3183, _mut3184)) ? 0 : means[0].getN();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialMean.hashCode_81");
        final int prime = 31;
        int result = 1;
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.hashCode_81", _mut3185, _mut3186, _mut3187, _mut3188), Arrays.hashCode(means), "org.apache.commons.math3.stat.descriptive.moment.VectorialMean.hashCode_81", _mut3189, _mut3190, _mut3191, _mut3192);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialMean)) {
            return false;
        }
        VectorialMean other = (VectorialMean) obj;
        if (!Arrays.equals(means, other.means)) {
            return false;
        }
        return true;
    }
}
