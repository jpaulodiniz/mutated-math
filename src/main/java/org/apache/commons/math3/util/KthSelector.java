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
package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.NullArgumentException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A Simple K<sup>th</sup> selector implementation to pick up the
 * K<sup>th</sup> ordered element from a work array containing the input
 * numbers.
 * @since 3.4
 */
public class KthSelector implements Serializable {

    @Conditional
    public static boolean _mut40177 = false, _mut40178 = false, _mut40179 = false, _mut40180 = false, _mut40181 = false, _mut40182 = false, _mut40183 = false, _mut40184 = false, _mut40185 = false, _mut40186 = false, _mut40187 = false, _mut40188 = false, _mut40189 = false, _mut40190 = false, _mut40191 = false, _mut40192 = false, _mut40193 = false, _mut40194 = false, _mut40195 = false, _mut40196 = false, _mut40197 = false, _mut40198 = false, _mut40199 = false, _mut40200 = false, _mut40201 = false, _mut40202 = false, _mut40203 = false, _mut40204 = false, _mut40205 = false, _mut40206 = false, _mut40207 = false, _mut40208 = false, _mut40209 = false, _mut40210 = false, _mut40211 = false, _mut40212 = false, _mut40213 = false, _mut40214 = false, _mut40215 = false, _mut40216 = false, _mut40217 = false, _mut40218 = false, _mut40219 = false, _mut40220 = false, _mut40221 = false, _mut40222 = false, _mut40223 = false, _mut40224 = false, _mut40225 = false, _mut40226 = false, _mut40227 = false, _mut40228 = false, _mut40229 = false, _mut40230 = false, _mut40231 = false, _mut40232 = false, _mut40233 = false, _mut40234 = false, _mut40235 = false, _mut40236 = false, _mut40237 = false, _mut40238 = false, _mut40239 = false, _mut40240 = false, _mut40241 = false, _mut40242 = false, _mut40243 = false, _mut40244 = false, _mut40245 = false, _mut40246 = false, _mut40247 = false, _mut40248 = false, _mut40249 = false, _mut40250 = false, _mut40251 = false, _mut40252 = false, _mut40253 = false, _mut40254 = false, _mut40255 = false, _mut40256 = false, _mut40257 = false, _mut40258 = false, _mut40259 = false, _mut40260 = false, _mut40261 = false, _mut40262 = false, _mut40263 = false, _mut40264 = false, _mut40265 = false, _mut40266 = false, _mut40267 = false, _mut40268 = false, _mut40269 = false, _mut40270 = false, _mut40271 = false, _mut40272 = false, _mut40273 = false, _mut40274 = false, _mut40275 = false, _mut40276 = false, _mut40277 = false, _mut40278 = false, _mut40279 = false, _mut40280 = false, _mut40281 = false, _mut40282 = false, _mut40283 = false, _mut40284 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140713L;

    /**
     * Minimum selection size for insertion sort rather than selection.
     */
    private static final int MIN_SELECT_SIZE = 15;

    /**
     * A {@link PivotingStrategyInterface} used for pivoting
     */
    private final PivotingStrategyInterface pivotingStrategy;

    /**
     * Constructor with default {@link MedianOf3PivotingStrategy median of 3} pivoting strategy
     */
    public KthSelector() {
        this.pivotingStrategy = new MedianOf3PivotingStrategy();
    }

    /**
     * Constructor with specified pivoting strategy
     *
     * @param pivotingStrategy pivoting strategy to use
     * @throws NullArgumentException when pivotingStrategy is null
     * @see MedianOf3PivotingStrategy
     * @see RandomPivotingStrategy
     * @see CentralPivotingStrategy
     */
    public KthSelector(final PivotingStrategyInterface pivotingStrategy) throws NullArgumentException {
        MathUtils.checkNotNull(pivotingStrategy);
        this.pivotingStrategy = pivotingStrategy;
    }

    /**
     * Get the pivotin strategy.
     * @return pivoting strategy
     */
    public PivotingStrategyInterface getPivotingStrategy() {
        return pivotingStrategy;
    }

    /**
     * Select K<sup>th</sup> value in the array.
     *
     * @param work work array to use to find out the K<sup>th</sup> value
     * @param pivotsHeap cached pivots heap that can be used for efficient estimation
     * @param k the index whose value in the array is of interest
     * @return K<sup>th</sup> value
     */
    public double select(final double[] work, final int[] pivotsHeap, final int k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.select_79");
        int begin = 0;
        int end = work.length;
        int node = 0;
        final boolean usePivotsHeap = pivotsHeap != null;
        while (ROR_greater(AOR_minus(end, begin, "org.apache.commons.math3.util.KthSelector.select_79", _mut40225, _mut40226, _mut40227, _mut40228), MIN_SELECT_SIZE, "org.apache.commons.math3.util.KthSelector.select_79", _mut40229, _mut40230, _mut40231, _mut40232, _mut40233)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.select_79");
            final int pivot;
            if ((_mut40188 ? ((_mut40182 ? (usePivotsHeap || ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40177, _mut40178, _mut40179, _mut40180, _mut40181)) : (usePivotsHeap && ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40177, _mut40178, _mut40179, _mut40180, _mut40181))) || ROR_greater_equals(pivotsHeap[node], 0, "org.apache.commons.math3.util.KthSelector.select_79", _mut40183, _mut40184, _mut40185, _mut40186, _mut40187)) : ((_mut40182 ? (usePivotsHeap || ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40177, _mut40178, _mut40179, _mut40180, _mut40181)) : (usePivotsHeap && ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40177, _mut40178, _mut40179, _mut40180, _mut40181))) && ROR_greater_equals(pivotsHeap[node], 0, "org.apache.commons.math3.util.KthSelector.select_79", _mut40183, _mut40184, _mut40185, _mut40186, _mut40187)))) {
                // and the array has already been partitioned around it
                pivot = pivotsHeap[node];
            } else {
                // select a pivot and partition work array around it
                pivot = partition(work, begin, end, pivotingStrategy.pivotIndex(work, begin, end));
                if ((_mut40194 ? (usePivotsHeap || ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40189, _mut40190, _mut40191, _mut40192, _mut40193)) : (usePivotsHeap && ROR_less(node, pivotsHeap.length, "org.apache.commons.math3.util.KthSelector.select_79", _mut40189, _mut40190, _mut40191, _mut40192, _mut40193)))) {
                    pivotsHeap[node] = pivot;
                }
            }
            if (ROR_equals(k, pivot, "org.apache.commons.math3.util.KthSelector.select_79", _mut40195, _mut40196, _mut40197, _mut40198, _mut40199)) {
                // the pivot was exactly the element we wanted
                return work[k];
            } else if (ROR_less(k, pivot, "org.apache.commons.math3.util.KthSelector.select_79", _mut40200, _mut40201, _mut40202, _mut40203, _mut40204)) {
                // the element is in the left partition
                end = pivot;
                node = FastMath.min(AOR_plus(AOR_multiply(2, node, "org.apache.commons.math3.util.KthSelector.select_79", _mut40217, _mut40218, _mut40219, _mut40220), 1, "org.apache.commons.math3.util.KthSelector.select_79", _mut40221, _mut40222, _mut40223, _mut40224), usePivotsHeap ? pivotsHeap.length : end);
            } else {
                // the element is in the right partition
                begin = AOR_plus(pivot, 1, "org.apache.commons.math3.util.KthSelector.select_79", _mut40205, _mut40206, _mut40207, _mut40208);
                node = FastMath.min(AOR_plus(AOR_multiply(2, node, "org.apache.commons.math3.util.KthSelector.select_79", _mut40209, _mut40210, _mut40211, _mut40212), 2, "org.apache.commons.math3.util.KthSelector.select_79", _mut40213, _mut40214, _mut40215, _mut40216), usePivotsHeap ? pivotsHeap.length : end);
            }
        }
        Arrays.sort(work, begin, end);
        return work[k];
    }

    /**
     * Partition an array slice around a pivot.Partitioning exchanges array
     * elements such that all elements smaller than pivot are before it and
     * all elements larger than pivot are after it.
     *
     * @param work work array
     * @param begin index of the first element of the slice of work array
     * @param end index after the last element of the slice of work array
     * @param pivot initial index of the pivot
     * @return index of the pivot after partition
     */
    private int partition(final double[] work, final int begin, final int end, final int pivot) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.partition_128");
        final double value = work[pivot];
        work[pivot] = work[begin];
        int i = AOR_plus(begin, 1, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40234, _mut40235, _mut40236, _mut40237);
        int j = AOR_minus(end, 1, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40238, _mut40239, _mut40240, _mut40241);
        while (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40269, _mut40270, _mut40271, _mut40272, _mut40273)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.partition_128");
            while ((_mut40252 ? (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40242, _mut40243, _mut40244, _mut40245, _mut40246) || ROR_greater(work[j], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40247, _mut40248, _mut40249, _mut40250, _mut40251)) : (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40242, _mut40243, _mut40244, _mut40245, _mut40246) && ROR_greater(work[j], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40247, _mut40248, _mut40249, _mut40250, _mut40251)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.partition_128");
                --j;
            }
            while ((_mut40263 ? (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40253, _mut40254, _mut40255, _mut40256, _mut40257) || ROR_less(work[i], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40258, _mut40259, _mut40260, _mut40261, _mut40262)) : (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40253, _mut40254, _mut40255, _mut40256, _mut40257) && ROR_less(work[i], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40258, _mut40259, _mut40260, _mut40261, _mut40262)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.KthSelector.partition_128");
                ++i;
            }
            if (ROR_less(i, j, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40264, _mut40265, _mut40266, _mut40267, _mut40268)) {
                final double tmp = work[i];
                work[i++] = work[j];
                work[j--] = tmp;
            }
        }
        if ((_mut40284 ? (ROR_greater_equals(i, end, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40274, _mut40275, _mut40276, _mut40277, _mut40278) && ROR_greater(work[i], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40279, _mut40280, _mut40281, _mut40282, _mut40283)) : (ROR_greater_equals(i, end, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40274, _mut40275, _mut40276, _mut40277, _mut40278) || ROR_greater(work[i], value, "org.apache.commons.math3.util.KthSelector.partition_128", _mut40279, _mut40280, _mut40281, _mut40282, _mut40283)))) {
            --i;
        }
        work[begin] = work[i];
        work[i] = value;
        return i;
    }
}
