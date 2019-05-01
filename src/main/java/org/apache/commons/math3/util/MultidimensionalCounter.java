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

import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Converter between unidimensional storage structure and multidimensional
 * conceptual structure.
 * This utility will convert from indices in a multidimensional structure
 * to the corresponding index in a one-dimensional array. For example,
 * assuming that the ranges (in 3 dimensions) of indices are 2, 4 and 3,
 * the following correspondences, between 3-tuples indices and unidimensional
 * indices, will hold:
 * <ul>
 *  <li>(0, 0, 0) corresponds to 0</li>
 *  <li>(0, 0, 1) corresponds to 1</li>
 *  <li>(0, 0, 2) corresponds to 2</li>
 *  <li>(0, 1, 0) corresponds to 3</li>
 *  <li>...</li>
 *  <li>(1, 0, 0) corresponds to 12</li>
 *  <li>...</li>
 *  <li>(1, 3, 2) corresponds to 23</li>
 * </ul>
 *
 * @since 2.2
 */
public class MultidimensionalCounter implements Iterable<Integer> {

    @Conditional
    public static boolean _mut50763 = false, _mut50764 = false, _mut50765 = false, _mut50766 = false, _mut50767 = false, _mut50768 = false, _mut50769 = false, _mut50770 = false, _mut50771 = false, _mut50772 = false, _mut50773 = false, _mut50774 = false, _mut50775 = false, _mut50776 = false, _mut50777 = false, _mut50778 = false, _mut50779 = false, _mut50780 = false, _mut50781 = false, _mut50782 = false, _mut50783 = false, _mut50784 = false, _mut50785 = false, _mut50786 = false, _mut50787 = false, _mut50788 = false, _mut50789 = false, _mut50790 = false, _mut50791 = false, _mut50792 = false, _mut50793 = false, _mut50794 = false, _mut50795 = false, _mut50796 = false, _mut50797 = false, _mut50798 = false, _mut50799 = false, _mut50800 = false, _mut50801 = false, _mut50802 = false, _mut50803 = false, _mut50804 = false, _mut50805 = false, _mut50806 = false, _mut50807 = false, _mut50808 = false, _mut50809 = false, _mut50810 = false, _mut50811 = false, _mut50812 = false, _mut50813 = false, _mut50814 = false, _mut50815 = false, _mut50816 = false, _mut50817 = false, _mut50818 = false, _mut50819 = false, _mut50820 = false, _mut50821 = false, _mut50822 = false, _mut50823 = false, _mut50824 = false, _mut50825 = false, _mut50826 = false, _mut50827 = false, _mut50828 = false, _mut50829 = false, _mut50830 = false, _mut50831 = false, _mut50832 = false, _mut50833 = false, _mut50834 = false, _mut50835 = false, _mut50836 = false, _mut50837 = false, _mut50838 = false, _mut50839 = false, _mut50840 = false, _mut50841 = false, _mut50842 = false, _mut50843 = false, _mut50844 = false, _mut50845 = false, _mut50846 = false, _mut50847 = false, _mut50848 = false, _mut50849 = false, _mut50850 = false, _mut50851 = false, _mut50852 = false, _mut50853 = false, _mut50854 = false, _mut50855 = false, _mut50856 = false, _mut50857 = false, _mut50858 = false, _mut50859 = false, _mut50860 = false, _mut50861 = false, _mut50862 = false, _mut50863 = false, _mut50864 = false, _mut50865 = false, _mut50866 = false, _mut50867 = false;

    /**
     * Number of dimensions.
     */
    private final int dimension;

    /**
     * Offset for each dimension.
     */
    private final int[] uniCounterOffset;

    /**
     * Counter sizes.
     */
    private final int[] size;

    /**
     * Total number of (one-dimensional) slots.
     */
    private final int totalSize;

    /**
     * Index of last dimension.
     */
    private final int last;

    /**
     * Perform iteration over the multidimensional counter.
     */
    public class Iterator implements java.util.Iterator<Integer> {

        /**
         * Multidimensional counter.
         */
        private final int[] counter = new int[dimension];

        /**
         * Unidimensional counter.
         */
        private int count = -1;

        /**
         * Maximum value for {@link #count}.
         */
        private final int maxCount = AOR_minus(totalSize, 1, "org.apache.commons.math3.util.MultidimensionalCounter.representableDelta_604", _mut50763, _mut50764, _mut50765, _mut50766);

        /**
         * Create an iterator
         * @see #iterator()
         */
        Iterator() {
            counter[last] = -1;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.hasNext_96");
            return ROR_less(count, maxCount, "org.apache.commons.math3.util.MultidimensionalCounter.hasNext_96", _mut50767, _mut50768, _mut50769, _mut50770, _mut50771);
        }

        /**
         * @return the unidimensional count after the counter has been
         * incremented by {@code 1}.
         * @throws NoSuchElementException if {@link #hasNext()} would have
         * returned {@code false}.
         */
        public Integer next() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.next_106");
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            for (int i = last; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.MultidimensionalCounter.next_106", _mut50781, _mut50782, _mut50783, _mut50784, _mut50785); i--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.next_106");
                if (ROR_equals(counter[i], AOR_minus(size[i], 1, "org.apache.commons.math3.util.MultidimensionalCounter.next_106", _mut50772, _mut50773, _mut50774, _mut50775), "org.apache.commons.math3.util.MultidimensionalCounter.next_106", _mut50776, _mut50777, _mut50778, _mut50779, _mut50780)) {
                    counter[i] = 0;
                } else {
                    ++counter[i];
                    break;
                }
            }
            return ++count;
        }

        /**
         * Get the current unidimensional counter slot.
         *
         * @return the index within the unidimensionl counter.
         */
        public int getCount() {
            return count;
        }

        /**
         * Get the current multidimensional counter slots.
         *
         * @return the indices within the multidimensional counter.
         */
        public int[] getCounts() {
            return MathArrays.copyOf(counter);
        }

        /**
         * Get the current count in the selected dimension.
         *
         * @param dim Dimension index.
         * @return the count at the corresponding index for the current state
         * of the iterator.
         * @throws IndexOutOfBoundsException if {@code index} is not in the
         * correct interval (as defined by the length of the argument in the
         * {@link MultidimensionalCounter#MultidimensionalCounter(int[])
         * constructor of the enclosing class}).
         */
        public int getCount(int dim) {
            return counter[dim];
        }

        /**
         * @throws UnsupportedOperationException
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Create a counter.
     *
     * @param size Counter sizes (number of slots in each dimension).
     * @throws NotStrictlyPositiveException if one of the sizes is
     * negative or zero.
     */
    public MultidimensionalCounter(int... size) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170");
        dimension = size.length;
        this.size = MathArrays.copyOf(size);
        uniCounterOffset = new int[dimension];
        last = AOR_minus(dimension, 1, "org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170", _mut50786, _mut50787, _mut50788, _mut50789);
        int tS = size[last];
        for (int i = 0; ROR_less(i, last, "org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170", _mut50795, _mut50796, _mut50797, _mut50798, _mut50799); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170");
            int count = 1;
            for (int j = i + 1; ROR_less(j, dimension, "org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170", _mut50790, _mut50791, _mut50792, _mut50793, _mut50794); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170");
                count *= size[j];
            }
            uniCounterOffset[i] = count;
            tS *= size[i];
        }
        uniCounterOffset[last] = 0;
        if (ROR_less_equals(tS, 0, "org.apache.commons.math3.util.MultidimensionalCounter.MultidimensionalCounter_170", _mut50800, _mut50801, _mut50802, _mut50803, _mut50804)) {
            throw new NotStrictlyPositiveException(tS);
        }
        totalSize = tS;
    }

    /**
     * Create an iterator over this counter.
     *
     * @return the iterator.
     */
    public Iterator iterator() {
        return new Iterator();
    }

    /**
     * Get the number of dimensions of the multidimensional counter.
     *
     * @return the number of dimensions.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Convert to multidimensional counter.
     *
     * @param index Index in unidimensional counter.
     * @return the multidimensional counts.
     * @throws OutOfRangeException if {@code index} is not between
     * {@code 0} and the value returned by {@link #getSize()} (excluded).
     */
    public int[] getCounts(int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221");
        if ((_mut50815 ? (ROR_less(index, 0, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50805, _mut50806, _mut50807, _mut50808, _mut50809) && ROR_greater_equals(index, totalSize, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50810, _mut50811, _mut50812, _mut50813, _mut50814)) : (ROR_less(index, 0, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50805, _mut50806, _mut50807, _mut50808, _mut50809) || ROR_greater_equals(index, totalSize, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50810, _mut50811, _mut50812, _mut50813, _mut50814)))) {
            throw new OutOfRangeException(index, 0, totalSize);
        }
        final int[] indices = new int[dimension];
        int count = 0;
        for (int i = 0; ROR_less(i, last, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50821, _mut50822, _mut50823, _mut50824, _mut50825); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221");
            int idx = 0;
            final int offset = uniCounterOffset[i];
            while (ROR_less_equals(count, index, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50816, _mut50817, _mut50818, _mut50819, _mut50820)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221");
                count += offset;
                ++idx;
            }
            --idx;
            count -= offset;
            indices[i] = idx;
        }
        indices[last] = AOR_minus(index, count, "org.apache.commons.math3.util.MultidimensionalCounter.getCounts_221", _mut50826, _mut50827, _mut50828, _mut50829);
        return indices;
    }

    /**
     * Convert to unidimensional counter.
     *
     * @param c Indices in multidimensional counter.
     * @return the index within the unidimensionl counter.
     * @throws DimensionMismatchException if the size of {@code c}
     * does not match the size of the array given in the constructor.
     * @throws OutOfRangeException if a value of {@code c} is not in
     * the range of the corresponding dimension, as defined in the
     * {@link MultidimensionalCounter#MultidimensionalCounter(int...) constructor}.
     */
    public int getCount(int... c) throws OutOfRangeException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.getCount_258");
        if (ROR_not_equals(c.length, dimension, "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50830, _mut50831, _mut50832, _mut50833, _mut50834)) {
            throw new DimensionMismatchException(c.length, dimension);
        }
        int count = 0;
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50854, _mut50855, _mut50856, _mut50857, _mut50858); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.getCount_258");
            final int index = c[i];
            if ((_mut50845 ? (ROR_less(index, 0, "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50835, _mut50836, _mut50837, _mut50838, _mut50839) && ROR_greater_equals(index, size[i], "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50840, _mut50841, _mut50842, _mut50843, _mut50844)) : (ROR_less(index, 0, "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50835, _mut50836, _mut50837, _mut50838, _mut50839) || ROR_greater_equals(index, size[i], "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50840, _mut50841, _mut50842, _mut50843, _mut50844)))) {
                throw new OutOfRangeException(index, 0, AOR_minus(size[i], 1, "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50846, _mut50847, _mut50848, _mut50849));
            }
            count += AOR_multiply(uniCounterOffset[i], c[i], "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50850, _mut50851, _mut50852, _mut50853);
        }
        return AOR_plus(count, c[last], "org.apache.commons.math3.util.MultidimensionalCounter.getCount_258", _mut50859, _mut50860, _mut50861, _mut50862);
    }

    /**
     * Get the total number of elements.
     *
     * @return the total size of the unidimensional counter.
     */
    public int getSize() {
        return totalSize;
    }

    /**
     * Get the number of multidimensional counter slots in each dimension.
     *
     * @return the sizes of the multidimensional counter in each dimension.
     */
    public int[] getSizes() {
        return MathArrays.copyOf(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.toString_295");
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.util.MultidimensionalCounter.toString_295", _mut50863, _mut50864, _mut50865, _mut50866, _mut50867); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MultidimensionalCounter.toString_295");
            sb.append("[").append(getCount(i)).append("]");
        }
        return sb.toString();
    }
}
