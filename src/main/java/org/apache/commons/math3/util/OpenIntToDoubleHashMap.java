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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Open addressed map from int to double.
 * <p>This class provides a dedicated map from integers to doubles with a
 * much smaller memory overhead than standard <code>java.util.Map</code>.</p>
 * <p>This class is not synchronized. The specialized iterators returned by
 * {@link #iterator()} are fail-fast: they throw a
 * <code>ConcurrentModificationException</code> when they detect the map has been
 * modified during iteration.</p>
 * @since 2.0
 */
public class OpenIntToDoubleHashMap implements Serializable {

    @Conditional
    public static boolean _mut42808 = false, _mut42809 = false, _mut42810 = false, _mut42811 = false, _mut42812 = false, _mut42813 = false, _mut42814 = false, _mut42815 = false, _mut42816 = false, _mut42817 = false, _mut42818 = false, _mut42819 = false, _mut42820 = false, _mut42821 = false, _mut42822 = false, _mut42823 = false, _mut42824 = false, _mut42825 = false, _mut42826 = false, _mut42827 = false, _mut42828 = false, _mut42829 = false, _mut42830 = false, _mut42831 = false, _mut42832 = false, _mut42833 = false, _mut42834 = false, _mut42835 = false, _mut42836 = false, _mut42837 = false, _mut42838 = false, _mut42839 = false, _mut42840 = false, _mut42841 = false, _mut42842 = false, _mut42843 = false, _mut42844 = false, _mut42845 = false, _mut42846 = false, _mut42847 = false, _mut42848 = false, _mut42849 = false, _mut42850 = false, _mut42851 = false, _mut42852 = false, _mut42853 = false, _mut42854 = false, _mut42855 = false, _mut42856 = false, _mut42857 = false, _mut42858 = false, _mut42859 = false, _mut42860 = false, _mut42861 = false, _mut42862 = false, _mut42863 = false, _mut42864 = false, _mut42865 = false, _mut42866 = false, _mut42867 = false, _mut42868 = false, _mut42869 = false, _mut42870 = false, _mut42871 = false, _mut42872 = false, _mut42873 = false, _mut42874 = false, _mut42875 = false, _mut42876 = false, _mut42877 = false, _mut42878 = false, _mut42879 = false, _mut42880 = false, _mut42881 = false, _mut42882 = false, _mut42883 = false, _mut42884 = false, _mut42885 = false, _mut42886 = false, _mut42887 = false, _mut42888 = false, _mut42889 = false, _mut42890 = false, _mut42891 = false, _mut42892 = false, _mut42893 = false, _mut42894 = false, _mut42895 = false, _mut42896 = false, _mut42897 = false, _mut42898 = false, _mut42899 = false, _mut42900 = false, _mut42901 = false, _mut42902 = false, _mut42903 = false, _mut42904 = false, _mut42905 = false, _mut42906 = false, _mut42907 = false, _mut42908 = false, _mut42909 = false, _mut42910 = false, _mut42911 = false, _mut42912 = false, _mut42913 = false, _mut42914 = false, _mut42915 = false, _mut42916 = false, _mut42917 = false, _mut42918 = false, _mut42919 = false, _mut42920 = false, _mut42921 = false, _mut42922 = false, _mut42923 = false, _mut42924 = false, _mut42925 = false, _mut42926 = false, _mut42927 = false, _mut42928 = false, _mut42929 = false, _mut42930 = false, _mut42931 = false, _mut42932 = false, _mut42933 = false, _mut42934 = false, _mut42935 = false, _mut42936 = false, _mut42937 = false, _mut42938 = false, _mut42939 = false, _mut42940 = false, _mut42941 = false, _mut42942 = false, _mut42943 = false, _mut42944 = false, _mut42945 = false, _mut42946 = false, _mut42947 = false, _mut42948 = false, _mut42949 = false, _mut42950 = false, _mut42951 = false, _mut42952 = false, _mut42953 = false, _mut42954 = false, _mut42955 = false, _mut42956 = false, _mut42957 = false, _mut42958 = false, _mut42959 = false, _mut42960 = false, _mut42961 = false, _mut42962 = false, _mut42963 = false, _mut42964 = false, _mut42965 = false, _mut42966 = false, _mut42967 = false, _mut42968 = false, _mut42969 = false, _mut42970 = false, _mut42971 = false, _mut42972 = false, _mut42973 = false, _mut42974 = false, _mut42975 = false, _mut42976 = false, _mut42977 = false, _mut42978 = false, _mut42979 = false, _mut42980 = false, _mut42981 = false, _mut42982 = false, _mut42983 = false, _mut42984 = false, _mut42985 = false, _mut42986 = false, _mut42987 = false, _mut42988 = false, _mut42989 = false, _mut42990 = false, _mut42991 = false, _mut42992 = false, _mut42993 = false, _mut42994 = false, _mut42995 = false, _mut42996 = false, _mut42997 = false, _mut42998 = false, _mut42999 = false, _mut43000 = false, _mut43001 = false, _mut43002 = false, _mut43003 = false, _mut43004 = false, _mut43005 = false, _mut43006 = false, _mut43007 = false, _mut43008 = false, _mut43009 = false, _mut43010 = false, _mut43011 = false, _mut43012 = false, _mut43013 = false, _mut43014 = false, _mut43015 = false, _mut43016 = false, _mut43017 = false, _mut43018 = false, _mut43019 = false, _mut43020 = false, _mut43021 = false, _mut43022 = false;

    /**
     * Status indicator for free table entries.
     */
    protected static final byte FREE = 0;

    /**
     * Status indicator for full table entries.
     */
    protected static final byte FULL = 1;

    /**
     * Status indicator for removed table entries.
     */
    protected static final byte REMOVED = 2;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -3646337053166149105L;

    /**
     * Load factor for the map.
     */
    private static final float LOAD_FACTOR = 0.5f;

    /**
     * Default starting size.
     * <p>This must be a power of two for bit mask to work properly. </p>
     */
    private static final int DEFAULT_EXPECTED_SIZE = 16;

    /**
     * Multiplier for size growth when map fills up.
     * <p>This must be a power of two for bit mask to work properly. </p>
     */
    private static final int RESIZE_MULTIPLIER = 2;

    /**
     * Number of bits to perturb the index when probing for collision resolution.
     */
    private static final int PERTURB_SHIFT = 5;

    /**
     * Keys table.
     */
    private int[] keys;

    /**
     * Values table.
     */
    private double[] values;

    /**
     * States table.
     */
    private byte[] states;

    /**
     * Return value for missing entries.
     */
    private final double missingEntries;

    /**
     * Current size of the map.
     */
    private int size;

    /**
     * Bit mask for hash values.
     */
    private int mask;

    /**
     * Modifications count.
     */
    private transient int count;

    /**
     * Build an empty map with default size and using NaN for missing entries.
     */
    public OpenIntToDoubleHashMap() {
        this(DEFAULT_EXPECTED_SIZE, Double.NaN);
    }

    /**
     * Build an empty map with default size
     * @param missingEntries value to return when a missing entry is fetched
     */
    public OpenIntToDoubleHashMap(final double missingEntries) {
        this(DEFAULT_EXPECTED_SIZE, missingEntries);
    }

    /**
     * Build an empty map with specified size and using NaN for missing entries.
     * @param expectedSize expected number of elements in the map
     */
    public OpenIntToDoubleHashMap(final int expectedSize) {
        this(expectedSize, Double.NaN);
    }

    /**
     * Build an empty map with specified size.
     * @param expectedSize expected number of elements in the map
     * @param missingEntries value to return when a missing entry is fetched
     */
    public OpenIntToDoubleHashMap(final int expectedSize, final double missingEntries) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.OpenIntToDoubleHashMap_115");
        final int capacity = computeCapacity(expectedSize);
        keys = new int[capacity];
        values = new double[capacity];
        states = new byte[capacity];
        this.missingEntries = missingEntries;
        mask = AOR_minus(capacity, 1, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.OpenIntToDoubleHashMap_115", _mut42808, _mut42809, _mut42810, _mut42811);
    }

    /**
     * Copy constructor.
     * @param source map to copy
     */
    public OpenIntToDoubleHashMap(final OpenIntToDoubleHashMap source) {
        final int length = source.keys.length;
        keys = new int[length];
        System.arraycopy(source.keys, 0, keys, 0, length);
        values = new double[length];
        System.arraycopy(source.values, 0, values, 0, length);
        states = new byte[length];
        System.arraycopy(source.states, 0, states, 0, length);
        missingEntries = source.missingEntries;
        size = source.size;
        mask = source.mask;
        count = source.count;
    }

    /**
     * Compute the capacity needed for a given size.
     * @param expectedSize expected size of the map
     * @return capacity to use for the specified size
     */
    private static int computeCapacity(final int expectedSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.computeCapacity_148");
        if (ROR_equals(expectedSize, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.computeCapacity_148", _mut42812, _mut42813, _mut42814, _mut42815, _mut42816)) {
            return 1;
        }
        final int capacity = (int) FastMath.ceil(AOR_divide(expectedSize, LOAD_FACTOR, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.computeCapacity_148", _mut42817, _mut42818, _mut42819, _mut42820));
        final int powerOfTwo = Integer.highestOneBit(capacity);
        if (ROR_equals(powerOfTwo, capacity, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.computeCapacity_148", _mut42821, _mut42822, _mut42823, _mut42824, _mut42825)) {
            return capacity;
        }
        return nextPowerOfTwo(capacity);
    }

    /**
     * Find the smallest power of two greater than the input value
     * @param i input value
     * @return smallest power of two greater than the input value
     */
    private static int nextPowerOfTwo(final int i) {
        return Integer.highestOneBit(i) << 1;
    }

    /**
     * Get the stored value associated with the given key
     * @param key key associated with the data
     * @return data associated with the key
     */
    public double get(final int key) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.get_174");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return values[index];
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.get_174", _mut42826, _mut42827, _mut42828, _mut42829, _mut42830)) {
            return missingEntries;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.get_174", _mut42831, _mut42832, _mut42833, _mut42834, _mut42835); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.get_174");
            j = probe(perturb, j);
            index = j & mask;
            if (containsKey(key, index)) {
                return values[index];
            }
        }
        return missingEntries;
    }

    /**
     * Check if a value is associated with a key.
     * @param key key to check
     * @return true if a value is associated with key
     */
    public boolean containsKey(final int key) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_204");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return true;
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_204", _mut42836, _mut42837, _mut42838, _mut42839, _mut42840)) {
            return false;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_204", _mut42841, _mut42842, _mut42843, _mut42844, _mut42845); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_204");
            j = probe(perturb, j);
            index = j & mask;
            if (containsKey(key, index)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an iterator over map elements.
     * <p>The specialized iterators returned are fail-fast: they throw a
     * <code>ConcurrentModificationException</code> when they detect the map
     * has been modified during iteration.</p>
     * @return iterator over the map elements
     */
    public Iterator iterator() {
        return new Iterator();
    }

    /**
     * Perturb the hash for starting probing.
     * @param hash initial hash
     * @return perturbed hash
     */
    private static int perturb(final int hash) {
        return hash & 0x7fffffff;
    }

    /**
     * Find the index at which a key should be inserted
     * @param key key to lookup
     * @return index at which key should be inserted
     */
    private int findInsertionIndex(final int key) {
        return findInsertionIndex(keys, states, key, mask);
    }

    /**
     * Find the index at which a key should be inserted
     * @param keys keys table
     * @param states states table
     * @param key key to lookup
     * @param mask bit mask for hash values
     * @return index at which key should be inserted
     */
    private static int findInsertionIndex(final int[] keys, final byte[] states, final int key, final int mask) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42846, _mut42847, _mut42848, _mut42849, _mut42850)) {
            return index;
        } else if ((_mut42861 ? (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42851, _mut42852, _mut42853, _mut42854, _mut42855) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42856, _mut42857, _mut42858, _mut42859, _mut42860)) : (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42851, _mut42852, _mut42853, _mut42854, _mut42855) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42856, _mut42857, _mut42858, _mut42859, _mut42860)))) {
            return changeIndexSign(index);
        }
        int perturb = perturb(hash);
        int j = index;
        if (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42862, _mut42863, _mut42864, _mut42865, _mut42866)) {
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266");
                j = probe(perturb, j);
                index = j & mask;
                perturb >>= PERTURB_SHIFT;
                if ((_mut42877 ? (ROR_not_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42867, _mut42868, _mut42869, _mut42870, _mut42871) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42872, _mut42873, _mut42874, _mut42875, _mut42876)) : (ROR_not_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42867, _mut42868, _mut42869, _mut42870, _mut42871) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42872, _mut42873, _mut42874, _mut42875, _mut42876)))) {
                    break;
                }
            }
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42878, _mut42879, _mut42880, _mut42881, _mut42882)) {
            return index;
        } else if (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42883, _mut42884, _mut42885, _mut42886, _mut42887)) {
            // if (states[index] == FULL) then keys[index] == key
            return changeIndexSign(index);
        }
        final int firstRemoved = index;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266");
            j = probe(perturb, j);
            index = j & mask;
            if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42888, _mut42889, _mut42890, _mut42891, _mut42892)) {
                return firstRemoved;
            } else if ((_mut42903 ? (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42893, _mut42894, _mut42895, _mut42896, _mut42897) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42898, _mut42899, _mut42900, _mut42901, _mut42902)) : (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42893, _mut42894, _mut42895, _mut42896, _mut42897) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.findInsertionIndex_266", _mut42898, _mut42899, _mut42900, _mut42901, _mut42902)))) {
                return changeIndexSign(index);
            }
            perturb >>= PERTURB_SHIFT;
        }
    }

    /**
     * Compute next probe for collision resolution
     * @param perturb perturbed hash
     * @param j previous probe
     * @return next probe
     */
    private static int probe(final int perturb, final int j) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.probe_321");
        return AOR_plus(AOR_plus(AOR_plus((j << 2), j, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.probe_321", _mut42904, _mut42905, _mut42906, _mut42907), perturb, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.probe_321", _mut42908, _mut42909, _mut42910, _mut42911), 1, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.probe_321", _mut42912, _mut42913, _mut42914, _mut42915);
    }

    /**
     * Change the index sign
     * @param index initial index
     * @return changed index
     */
    private static int changeIndexSign(final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.changeIndexSign_330");
        return AOR_minus(-index, 1, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.changeIndexSign_330", _mut42916, _mut42917, _mut42918, _mut42919);
    }

    /**
     * Get the number of elements stored in the map.
     * @return number of elements stored in the map
     */
    public int size() {
        return size;
    }

    /**
     * Remove the value associated with a key.
     * @param key key to which the value is associated
     * @return removed value
     */
    public double remove(final int key) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.remove_348");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return doRemove(index);
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.remove_348", _mut42920, _mut42921, _mut42922, _mut42923, _mut42924)) {
            return missingEntries;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.remove_348", _mut42925, _mut42926, _mut42927, _mut42928, _mut42929); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.remove_348");
            j = probe(perturb, j);
            index = j & mask;
            if (containsKey(key, index)) {
                return doRemove(index);
            }
        }
        return missingEntries;
    }

    /**
     * Check if the tables contain an element associated with specified key
     * at specified index.
     * @param key key to check
     * @param index index to check
     * @return true if an element is associated with key at index
     */
    private boolean containsKey(final int key, final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380");
        return (_mut42946 ? (((_mut42940 ? (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42930, _mut42931, _mut42932, _mut42933, _mut42934) && ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42935, _mut42936, _mut42937, _mut42938, _mut42939)) : (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42930, _mut42931, _mut42932, _mut42933, _mut42934) || ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42935, _mut42936, _mut42937, _mut42938, _mut42939)))) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42941, _mut42942, _mut42943, _mut42944, _mut42945)) : (((_mut42940 ? (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42930, _mut42931, _mut42932, _mut42933, _mut42934) && ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42935, _mut42936, _mut42937, _mut42938, _mut42939)) : (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42930, _mut42931, _mut42932, _mut42933, _mut42934) || ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42935, _mut42936, _mut42937, _mut42938, _mut42939)))) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.containsKey_380", _mut42941, _mut42942, _mut42943, _mut42944, _mut42945)));
    }

    /**
     * Remove an element at specified index.
     * @param index index of the element to remove
     * @return removed value
     */
    private double doRemove(int index) {
        keys[index] = 0;
        states[index] = REMOVED;
        final double previous = values[index];
        values[index] = missingEntries;
        --size;
        ++count;
        return previous;
    }

    /**
     * Put a value associated with a key in the map.
     * @param key key to which value is associated
     * @param value value to put in the map
     * @return previous value associated with the key
     */
    public double put(final int key, final double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.put_405");
        int index = findInsertionIndex(key);
        double previous = missingEntries;
        boolean newMapping = true;
        if (ROR_less(index, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.put_405", _mut42947, _mut42948, _mut42949, _mut42950, _mut42951)) {
            index = changeIndexSign(index);
            previous = values[index];
            newMapping = false;
        }
        keys[index] = key;
        states[index] = FULL;
        values[index] = value;
        if (newMapping) {
            ++size;
            if (shouldGrowTable()) {
                growTable();
            }
            ++count;
        }
        return previous;
    }

    /**
     * Grow the tables.
     */
    private void growTable() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431");
        final int oldLength = states.length;
        final int[] oldKeys = keys;
        final double[] oldValues = values;
        final byte[] oldStates = states;
        final int newLength = AOR_multiply(RESIZE_MULTIPLIER, oldLength, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431", _mut42952, _mut42953, _mut42954, _mut42955);
        final int[] newKeys = new int[newLength];
        final double[] newValues = new double[newLength];
        final byte[] newStates = new byte[newLength];
        final int newMask = AOR_minus(newLength, 1, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431", _mut42956, _mut42957, _mut42958, _mut42959);
        for (int i = 0; ROR_less(i, oldLength, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431", _mut42965, _mut42966, _mut42967, _mut42968, _mut42969); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431");
            if (ROR_equals(oldStates[i], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.growTable_431", _mut42960, _mut42961, _mut42962, _mut42963, _mut42964)) {
                final int key = oldKeys[i];
                final int index = findInsertionIndex(newKeys, newStates, key, newMask);
                newKeys[index] = key;
                newValues[index] = oldValues[i];
                newStates[index] = FULL;
            }
        }
        mask = newMask;
        keys = newKeys;
        values = newValues;
        states = newStates;
    }

    /**
     * Check if tables should grow due to increased size.
     * @return true if  tables should grow
     */
    private boolean shouldGrowTable() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.shouldGrowTable_464");
        return ROR_greater(size, AOR_multiply((AOR_plus(mask, 1, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.shouldGrowTable_464", _mut42970, _mut42971, _mut42972, _mut42973)), LOAD_FACTOR, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.shouldGrowTable_464", _mut42974, _mut42975, _mut42976, _mut42977), "org.apache.commons.math3.util.OpenIntToDoubleHashMap.shouldGrowTable_464", _mut42978, _mut42979, _mut42980, _mut42981, _mut42982);
    }

    /**
     * Compute the hash value of a key
     * @param key key to hash
     * @return hash value of the key
     */
    private static int hashOf(final int key) {
        final int h = key ^ ((key >>> 20) ^ (key >>> 12));
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Iterator class for the map.
     */
    public class Iterator {

        /**
         * Reference modification count.
         */
        private final int referenceCount;

        /**
         * Index of current element.
         */
        private int current;

        /**
         * Index of next element.
         */
        private int next;

        /**
         * Simple constructor.
         */
        private Iterator() {
            // preserve the modification count of the map to detect concurrent modifications later
            referenceCount = count;
            // initialize current index
            next = -1;
            try {
                advance();
            } catch (NoSuchElementException nsee) {
            }
        }

        /**
         * Check if there is a next element in the map.
         * @return true if there is a next element
         */
        public boolean hasNext() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.hasNext_513");
            return ROR_greater_equals(next, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.hasNext_513", _mut42983, _mut42984, _mut42985, _mut42986, _mut42987);
        }

        /**
         * Get the key of current entry.
         * @return key of current entry
         * @exception ConcurrentModificationException if the map is modified during iteration
         * @exception NoSuchElementException if there is no element left in the map
         */
        public int key() throws ConcurrentModificationException, NoSuchElementException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.key_523");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.key_523", _mut42988, _mut42989, _mut42990, _mut42991, _mut42992)) {
                throw new ConcurrentModificationException();
            }
            if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.key_523", _mut42993, _mut42994, _mut42995, _mut42996, _mut42997)) {
                throw new NoSuchElementException();
            }
            return keys[current];
        }

        /**
         * Get the value of current entry.
         * @return value of current entry
         * @exception ConcurrentModificationException if the map is modified during iteration
         * @exception NoSuchElementException if there is no element left in the map
         */
        public double value() throws ConcurrentModificationException, NoSuchElementException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.value_540");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.value_540", _mut42998, _mut42999, _mut43000, _mut43001, _mut43002)) {
                throw new ConcurrentModificationException();
            }
            if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.value_540", _mut43003, _mut43004, _mut43005, _mut43006, _mut43007)) {
                throw new NoSuchElementException();
            }
            return values[current];
        }

        /**
         * Advance iterator one step further.
         * @exception ConcurrentModificationException if the map is modified during iteration
         * @exception NoSuchElementException if there is no element left in the map
         */
        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.advance_556");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.advance_556", _mut43008, _mut43009, _mut43010, _mut43011, _mut43012)) {
                throw new ConcurrentModificationException();
            }
            // advance on step
            current = next;
            // prepare next step
            try {
                while (ROR_not_equals(states[++next], FULL, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.advance_556", _mut43018, _mut43019, _mut43020, _mut43021, _mut43022)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToDoubleHashMap.advance_556");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                next = -2;
                if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToDoubleHashMap.advance_556", _mut43013, _mut43014, _mut43015, _mut43016, _mut43017)) {
                    throw new NoSuchElementException();
                }
            }
        }
    }

    /**
     * Read a serialized object.
     * @param stream input stream
     * @throws IOException if object cannot be read
     * @throws ClassNotFoundException if the class corresponding
     * to the serialized object cannot be found
     */
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        count = 0;
    }
}
