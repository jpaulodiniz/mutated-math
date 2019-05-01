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
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Returns the covariance matrix of the available vectors.
 * @since 1.2
 */
public class VectorialCovariance implements Serializable {

    @Conditional
    public static boolean _mut3262 = false, _mut3263 = false, _mut3264 = false, _mut3265 = false, _mut3266 = false, _mut3267 = false, _mut3268 = false, _mut3269 = false, _mut3270 = false, _mut3271 = false, _mut3272 = false, _mut3273 = false, _mut3274 = false, _mut3275 = false, _mut3276 = false, _mut3277 = false, _mut3278 = false, _mut3279 = false, _mut3280 = false, _mut3281 = false, _mut3282 = false, _mut3283 = false, _mut3284 = false, _mut3285 = false, _mut3286 = false, _mut3287 = false, _mut3288 = false, _mut3289 = false, _mut3290 = false, _mut3291 = false, _mut3292 = false, _mut3293 = false, _mut3294 = false, _mut3295 = false, _mut3296 = false, _mut3297 = false, _mut3298 = false, _mut3299 = false, _mut3300 = false, _mut3301 = false, _mut3302 = false, _mut3303 = false, _mut3304 = false, _mut3305 = false, _mut3306 = false, _mut3307 = false, _mut3308 = false, _mut3309 = false, _mut3310 = false, _mut3311 = false, _mut3312 = false, _mut3313 = false, _mut3314 = false, _mut3315 = false, _mut3316 = false, _mut3317 = false, _mut3318 = false, _mut3319 = false, _mut3320 = false, _mut3321 = false, _mut3322 = false, _mut3323 = false, _mut3324 = false, _mut3325 = false, _mut3326 = false, _mut3327 = false, _mut3328 = false, _mut3329 = false, _mut3330 = false, _mut3331 = false, _mut3332 = false, _mut3333 = false, _mut3334 = false, _mut3335 = false, _mut3336 = false, _mut3337 = false, _mut3338 = false, _mut3339 = false, _mut3340 = false, _mut3341 = false, _mut3342 = false, _mut3343 = false, _mut3344 = false, _mut3345 = false, _mut3346 = false, _mut3347 = false, _mut3348 = false, _mut3349 = false, _mut3350 = false, _mut3351 = false, _mut3352 = false, _mut3353 = false, _mut3354 = false, _mut3355 = false, _mut3356 = false, _mut3357 = false, _mut3358 = false, _mut3359 = false, _mut3360 = false, _mut3361 = false, _mut3362 = false, _mut3363 = false, _mut3364 = false, _mut3365 = false, _mut3366 = false, _mut3367 = false, _mut3368 = false, _mut3369 = false, _mut3370 = false, _mut3371 = false, _mut3372 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 4118372414238930270L;

    /**
     * Sums for each component.
     */
    private final double[] sums;

    /**
     * Sums of products for each component.
     */
    private final double[] productsSums;

    /**
     * Indicator for bias correction.
     */
    private final boolean isBiasCorrected;

    /**
     * Number of vectors in the sample.
     */
    private long n;

    /**
     * Constructs a VectorialCovariance.
     * @param dimension vectors dimension
     * @param isBiasCorrected if true, computed the unbiased sample covariance,
     * otherwise computes the biased population covariance
     */
    public VectorialCovariance(int dimension, boolean isBiasCorrected) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.VectorialCovariance_52");
        sums = new double[dimension];
        productsSums = new double[AOR_divide(AOR_multiply(dimension, (AOR_plus(dimension, 1, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.VectorialCovariance_52", _mut3262, _mut3263, _mut3264, _mut3265)), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.VectorialCovariance_52", _mut3266, _mut3267, _mut3268, _mut3269), 2, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.VectorialCovariance_52", _mut3270, _mut3271, _mut3272, _mut3273)];
        n = 0;
        this.isBiasCorrected = isBiasCorrected;
    }

    /**
     * Add a new vector to the sample.
     * @param v vector to add
     * @throws DimensionMismatchException if the vector does not have the right dimension
     */
    public void increment(double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64");
        if (ROR_not_equals(v.length, sums.length, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64", _mut3274, _mut3275, _mut3276, _mut3277, _mut3278)) {
            throw new DimensionMismatchException(v.length, sums.length);
        }
        int k = 0;
        for (int i = 0; ROR_less(i, v.length, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64", _mut3288, _mut3289, _mut3290, _mut3291, _mut3292); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64");
            sums[i] += v[i];
            for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64", _mut3283, _mut3284, _mut3285, _mut3286, _mut3287); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64");
                productsSums[k++] += AOR_multiply(v[i], v[j], "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.increment_64", _mut3279, _mut3280, _mut3281, _mut3282);
            }
        }
        n++;
    }

    /**
     * Get the covariance matrix.
     * @return covariance matrix
     */
    public RealMatrix getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82");
        int dimension = sums.length;
        RealMatrix result = MatrixUtils.createRealMatrix(dimension, dimension);
        if (ROR_greater(n, 1, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3293, _mut3294, _mut3295, _mut3296, _mut3297)) {
            double c = AOR_divide(1.0, (AOR_multiply(n, (isBiasCorrected ? (AOR_minus(n, 1, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3298, _mut3299, _mut3300, _mut3301)) : n), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3302, _mut3303, _mut3304, _mut3305)), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3306, _mut3307, _mut3308, _mut3309);
            int k = 0;
            for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3331, _mut3332, _mut3333, _mut3334, _mut3335); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82");
                for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3326, _mut3327, _mut3328, _mut3329, _mut3330); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82");
                    double e = AOR_multiply(c, (AOR_minus(AOR_multiply(n, productsSums[k++], "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3310, _mut3311, _mut3312, _mut3313), AOR_multiply(sums[i], sums[j], "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3314, _mut3315, _mut3316, _mut3317), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3318, _mut3319, _mut3320, _mut3321)), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.getResult_82", _mut3322, _mut3323, _mut3324, _mut3325);
                    result.setEntry(i, j, e);
                    result.setEntry(j, i, e);
                }
            }
        }
        return result;
    }

    /**
     * Get the number of vectors in the sample.
     * @return number of vectors in the sample
     */
    public long getN() {
        return n;
    }

    /**
     * Clears the internal state of the Statistic
     */
    public void clear() {
        n = 0;
        Arrays.fill(sums, 0.0);
        Arrays.fill(productsSums, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121");
        final int prime = 31;
        int result = 1;
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3336, _mut3337, _mut3338, _mut3339), (isBiasCorrected ? 1231 : 1237), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3340, _mut3341, _mut3342, _mut3343);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3344, _mut3345, _mut3346, _mut3347), (int) (n ^ (n >>> 32)), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3348, _mut3349, _mut3350, _mut3351);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3352, _mut3353, _mut3354, _mut3355), Arrays.hashCode(productsSums), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3356, _mut3357, _mut3358, _mut3359);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3360, _mut3361, _mut3362, _mut3363), Arrays.hashCode(sums), "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.hashCode_121", _mut3364, _mut3365, _mut3366, _mut3367);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.equals_133");
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance other = (VectorialCovariance) obj;
        if (isBiasCorrected != other.isBiasCorrected) {
            return false;
        }
        if (ROR_not_equals(n, other.n, "org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance.equals_133", _mut3368, _mut3369, _mut3370, _mut3371, _mut3372)) {
            return false;
        }
        if (!Arrays.equals(productsSums, other.productsSums)) {
            return false;
        }
        if (!Arrays.equals(sums, other.sums)) {
            return false;
        }
        return true;
    }
}
