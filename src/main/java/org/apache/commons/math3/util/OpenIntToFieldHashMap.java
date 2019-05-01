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
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Open addressed map from int to FieldElement.
 * <p>This class provides a dedicated map from integers to FieldElements with a
 * much smaller memory overhead than standard <code>java.util.Map</code>.</p>
 * <p>This class is not synchronized. The specialized iterators returned by
 * {@link #iterator()} are fail-fast: they throw a
 * <code>ConcurrentModificationException</code> when they detect the map has been
 * modified during iteration.</p>
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class OpenIntToFieldHashMap<T extends FieldElement<T>> implements Serializable {

    @Conditional
    public static boolean _mut50868 = false, _mut50869 = false, _mut50870 = false, _mut50871 = false, _mut50872 = false, _mut50873 = false, _mut50874 = false, _mut50875 = false, _mut50876 = false, _mut50877 = false, _mut50878 = false, _mut50879 = false, _mut50880 = false, _mut50881 = false, _mut50882 = false, _mut50883 = false, _mut50884 = false, _mut50885 = false, _mut50886 = false, _mut50887 = false, _mut50888 = false, _mut50889 = false, _mut50890 = false, _mut50891 = false, _mut50892 = false, _mut50893 = false, _mut50894 = false, _mut50895 = false, _mut50896 = false, _mut50897 = false, _mut50898 = false, _mut50899 = false, _mut50900 = false, _mut50901 = false, _mut50902 = false, _mut50903 = false, _mut50904 = false, _mut50905 = false, _mut50906 = false, _mut50907 = false, _mut50908 = false, _mut50909 = false, _mut50910 = false, _mut50911 = false, _mut50912 = false, _mut50913 = false, _mut50914 = false, _mut50915 = false, _mut50916 = false, _mut50917 = false, _mut50918 = false, _mut50919 = false, _mut50920 = false, _mut50921 = false, _mut50922 = false, _mut50923 = false, _mut50924 = false, _mut50925 = false, _mut50926 = false, _mut50927 = false, _mut50928 = false, _mut50929 = false, _mut50930 = false, _mut50931 = false, _mut50932 = false, _mut50933 = false, _mut50934 = false, _mut50935 = false, _mut50936 = false, _mut50937 = false, _mut50938 = false, _mut50939 = false, _mut50940 = false, _mut50941 = false, _mut50942 = false, _mut50943 = false, _mut50944 = false, _mut50945 = false, _mut50946 = false, _mut50947 = false, _mut50948 = false, _mut50949 = false, _mut50950 = false, _mut50951 = false, _mut50952 = false, _mut50953 = false, _mut50954 = false, _mut50955 = false, _mut50956 = false, _mut50957 = false, _mut50958 = false, _mut50959 = false, _mut50960 = false, _mut50961 = false, _mut50962 = false, _mut50963 = false, _mut50964 = false, _mut50965 = false, _mut50966 = false, _mut50967 = false, _mut50968 = false, _mut50969 = false, _mut50970 = false, _mut50971 = false, _mut50972 = false, _mut50973 = false, _mut50974 = false, _mut50975 = false, _mut50976 = false, _mut50977 = false, _mut50978 = false, _mut50979 = false, _mut50980 = false, _mut50981 = false, _mut50982 = false, _mut50983 = false, _mut50984 = false, _mut50985 = false, _mut50986 = false, _mut50987 = false, _mut50988 = false, _mut50989 = false, _mut50990 = false, _mut50991 = false, _mut50992 = false, _mut50993 = false, _mut50994 = false, _mut50995 = false, _mut50996 = false, _mut50997 = false, _mut50998 = false, _mut50999 = false, _mut51000 = false, _mut51001 = false, _mut51002 = false, _mut51003 = false, _mut51004 = false, _mut51005 = false, _mut51006 = false, _mut51007 = false, _mut51008 = false, _mut51009 = false, _mut51010 = false, _mut51011 = false, _mut51012 = false, _mut51013 = false, _mut51014 = false, _mut51015 = false, _mut51016 = false, _mut51017 = false, _mut51018 = false, _mut51019 = false, _mut51020 = false, _mut51021 = false, _mut51022 = false, _mut51023 = false, _mut51024 = false, _mut51025 = false, _mut51026 = false, _mut51027 = false, _mut51028 = false, _mut51029 = false, _mut51030 = false, _mut51031 = false, _mut51032 = false, _mut51033 = false, _mut51034 = false, _mut51035 = false, _mut51036 = false, _mut51037 = false, _mut51038 = false, _mut51039 = false, _mut51040 = false, _mut51041 = false, _mut51042 = false, _mut51043 = false, _mut51044 = false, _mut51045 = false, _mut51046 = false, _mut51047 = false, _mut51048 = false, _mut51049 = false, _mut51050 = false, _mut51051 = false, _mut51052 = false, _mut51053 = false, _mut51054 = false, _mut51055 = false, _mut51056 = false, _mut51057 = false, _mut51058 = false, _mut51059 = false, _mut51060 = false, _mut51061 = false, _mut51062 = false, _mut51063 = false, _mut51064 = false, _mut51065 = false, _mut51066 = false, _mut51067 = false, _mut51068 = false, _mut51069 = false, _mut51070 = false, _mut51071 = false, _mut51072 = false, _mut51073 = false, _mut51074 = false, _mut51075 = false, _mut51076 = false, _mut51077 = false, _mut51078 = false, _mut51079 = false, _mut51080 = false, _mut51081 = false, _mut51082 = false;

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
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -9179080286849120720L;

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
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Keys table.
     */
    private int[] keys;

    /**
     * Values table.
     */
    private T[] values;

    /**
     * States table.
     */
    private byte[] states;

    /**
     * Return value for missing entries.
     */
    private final T missingEntries;

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
     * Build an empty map with default size and using zero for missing entries.
     * @param field field to which the elements belong
     */
    public OpenIntToFieldHashMap(final Field<T> field) {
        this(field, DEFAULT_EXPECTED_SIZE, field.getZero());
    }

    /**
     * Build an empty map with default size
     * @param field field to which the elements belong
     * @param missingEntries value to return when a missing entry is fetched
     */
    public OpenIntToFieldHashMap(final Field<T> field, final T missingEntries) {
        this(field, DEFAULT_EXPECTED_SIZE, missingEntries);
    }

    /**
     * Build an empty map with specified size and using zero for missing entries.
     * @param field field to which the elements belong
     * @param expectedSize expected number of elements in the map
     */
    public OpenIntToFieldHashMap(final Field<T> field, final int expectedSize) {
        this(field, expectedSize, field.getZero());
    }

    /**
     * Build an empty map with specified size.
     * @param field field to which the elements belong
     * @param expectedSize expected number of elements in the map
     * @param missingEntries value to return when a missing entry is fetched
     */
    public OpenIntToFieldHashMap(final Field<T> field, final int expectedSize, final T missingEntries) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.OpenIntToFieldHashMap_126");
        this.field = field;
        final int capacity = computeCapacity(expectedSize);
        keys = new int[capacity];
        values = buildArray(capacity);
        states = new byte[capacity];
        this.missingEntries = missingEntries;
        mask = AOR_minus(capacity, 1, "org.apache.commons.math3.util.OpenIntToFieldHashMap.OpenIntToFieldHashMap_126", _mut50868, _mut50869, _mut50870, _mut50871);
    }

    /**
     * Copy constructor.
     * @param source map to copy
     */
    public OpenIntToFieldHashMap(final OpenIntToFieldHashMap<T> source) {
        field = source.field;
        final int length = source.keys.length;
        keys = new int[length];
        System.arraycopy(source.keys, 0, keys, 0, length);
        values = buildArray(length);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.computeCapacity_161");
        if (ROR_equals(expectedSize, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.computeCapacity_161", _mut50872, _mut50873, _mut50874, _mut50875, _mut50876)) {
            return 1;
        }
        final int capacity = (int) FastMath.ceil(AOR_divide(expectedSize, LOAD_FACTOR, "org.apache.commons.math3.util.OpenIntToFieldHashMap.computeCapacity_161", _mut50877, _mut50878, _mut50879, _mut50880));
        final int powerOfTwo = Integer.highestOneBit(capacity);
        if (ROR_equals(powerOfTwo, capacity, "org.apache.commons.math3.util.OpenIntToFieldHashMap.computeCapacity_161", _mut50881, _mut50882, _mut50883, _mut50884, _mut50885)) {
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
    public T get(final int key) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.get_187");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return values[index];
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.get_187", _mut50886, _mut50887, _mut50888, _mut50889, _mut50890)) {
            return missingEntries;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.get_187", _mut50891, _mut50892, _mut50893, _mut50894, _mut50895); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.get_187");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_217");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return true;
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_217", _mut50896, _mut50897, _mut50898, _mut50899, _mut50900)) {
            return false;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_217", _mut50901, _mut50902, _mut50903, _mut50904, _mut50905); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_217");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50906, _mut50907, _mut50908, _mut50909, _mut50910)) {
            return index;
        } else if ((_mut50921 ? (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50911, _mut50912, _mut50913, _mut50914, _mut50915) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50916, _mut50917, _mut50918, _mut50919, _mut50920)) : (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50911, _mut50912, _mut50913, _mut50914, _mut50915) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50916, _mut50917, _mut50918, _mut50919, _mut50920)))) {
            return changeIndexSign(index);
        }
        int perturb = perturb(hash);
        int j = index;
        if (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50922, _mut50923, _mut50924, _mut50925, _mut50926)) {
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279");
                j = probe(perturb, j);
                index = j & mask;
                perturb >>= PERTURB_SHIFT;
                if ((_mut50937 ? (ROR_not_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50927, _mut50928, _mut50929, _mut50930, _mut50931) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50932, _mut50933, _mut50934, _mut50935, _mut50936)) : (ROR_not_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50927, _mut50928, _mut50929, _mut50930, _mut50931) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50932, _mut50933, _mut50934, _mut50935, _mut50936)))) {
                    break;
                }
            }
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50938, _mut50939, _mut50940, _mut50941, _mut50942)) {
            return index;
        } else if (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50943, _mut50944, _mut50945, _mut50946, _mut50947)) {
            // if (states[index] == FULL) then keys[index] == key
            return changeIndexSign(index);
        }
        final int firstRemoved = index;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279");
            j = probe(perturb, j);
            index = j & mask;
            if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50948, _mut50949, _mut50950, _mut50951, _mut50952)) {
                return firstRemoved;
            } else if ((_mut50963 ? (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50953, _mut50954, _mut50955, _mut50956, _mut50957) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50958, _mut50959, _mut50960, _mut50961, _mut50962)) : (ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50953, _mut50954, _mut50955, _mut50956, _mut50957) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.findInsertionIndex_279", _mut50958, _mut50959, _mut50960, _mut50961, _mut50962)))) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.probe_334");
        return AOR_plus(AOR_plus(AOR_plus((j << 2), j, "org.apache.commons.math3.util.OpenIntToFieldHashMap.probe_334", _mut50964, _mut50965, _mut50966, _mut50967), perturb, "org.apache.commons.math3.util.OpenIntToFieldHashMap.probe_334", _mut50968, _mut50969, _mut50970, _mut50971), 1, "org.apache.commons.math3.util.OpenIntToFieldHashMap.probe_334", _mut50972, _mut50973, _mut50974, _mut50975);
    }

    /**
     * Change the index sign
     * @param index initial index
     * @return changed index
     */
    private static int changeIndexSign(final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.changeIndexSign_343");
        return AOR_minus(-index, 1, "org.apache.commons.math3.util.OpenIntToFieldHashMap.changeIndexSign_343", _mut50976, _mut50977, _mut50978, _mut50979);
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
    public T remove(final int key) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.remove_361");
        final int hash = hashOf(key);
        int index = hash & mask;
        if (containsKey(key, index)) {
            return doRemove(index);
        }
        if (ROR_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.remove_361", _mut50980, _mut50981, _mut50982, _mut50983, _mut50984)) {
            return missingEntries;
        }
        int j = index;
        for (int perturb = perturb(hash); ROR_not_equals(states[index], FREE, "org.apache.commons.math3.util.OpenIntToFieldHashMap.remove_361", _mut50985, _mut50986, _mut50987, _mut50988, _mut50989); perturb >>= PERTURB_SHIFT) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.remove_361");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393");
        return (_mut51006 ? (((_mut51000 ? (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50990, _mut50991, _mut50992, _mut50993, _mut50994) && ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50995, _mut50996, _mut50997, _mut50998, _mut50999)) : (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50990, _mut50991, _mut50992, _mut50993, _mut50994) || ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50995, _mut50996, _mut50997, _mut50998, _mut50999)))) || ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut51001, _mut51002, _mut51003, _mut51004, _mut51005)) : (((_mut51000 ? (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50990, _mut50991, _mut50992, _mut50993, _mut50994) && ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50995, _mut50996, _mut50997, _mut50998, _mut50999)) : (ROR_not_equals(key, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50990, _mut50991, _mut50992, _mut50993, _mut50994) || ROR_equals(states[index], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut50995, _mut50996, _mut50997, _mut50998, _mut50999)))) && ROR_equals(keys[index], key, "org.apache.commons.math3.util.OpenIntToFieldHashMap.containsKey_393", _mut51001, _mut51002, _mut51003, _mut51004, _mut51005)));
    }

    /**
     * Remove an element at specified index.
     * @param index index of the element to remove
     * @return removed value
     */
    private T doRemove(int index) {
        keys[index] = 0;
        states[index] = REMOVED;
        final T previous = values[index];
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
    public T put(final int key, final T value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.put_418");
        int index = findInsertionIndex(key);
        T previous = missingEntries;
        boolean newMapping = true;
        if (ROR_less(index, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.put_418", _mut51007, _mut51008, _mut51009, _mut51010, _mut51011)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444");
        final int oldLength = states.length;
        final int[] oldKeys = keys;
        final T[] oldValues = values;
        final byte[] oldStates = states;
        final int newLength = AOR_multiply(RESIZE_MULTIPLIER, oldLength, "org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444", _mut51012, _mut51013, _mut51014, _mut51015);
        final int[] newKeys = new int[newLength];
        final T[] newValues = buildArray(newLength);
        final byte[] newStates = new byte[newLength];
        final int newMask = AOR_minus(newLength, 1, "org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444", _mut51016, _mut51017, _mut51018, _mut51019);
        for (int i = 0; ROR_less(i, oldLength, "org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444", _mut51025, _mut51026, _mut51027, _mut51028, _mut51029); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444");
            if (ROR_equals(oldStates[i], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.growTable_444", _mut51020, _mut51021, _mut51022, _mut51023, _mut51024)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.shouldGrowTable_477");
        return ROR_greater(size, AOR_multiply((AOR_plus(mask, 1, "org.apache.commons.math3.util.OpenIntToFieldHashMap.shouldGrowTable_477", _mut51030, _mut51031, _mut51032, _mut51033)), LOAD_FACTOR, "org.apache.commons.math3.util.OpenIntToFieldHashMap.shouldGrowTable_477", _mut51034, _mut51035, _mut51036, _mut51037), "org.apache.commons.math3.util.OpenIntToFieldHashMap.shouldGrowTable_477", _mut51038, _mut51039, _mut51040, _mut51041, _mut51042);
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.hasNext_526");
            return ROR_greater_equals(next, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.hasNext_526", _mut51043, _mut51044, _mut51045, _mut51046, _mut51047);
        }

        /**
         * Get the key of current entry.
         * @return key of current entry
         * @exception ConcurrentModificationException if the map is modified during iteration
         * @exception NoSuchElementException if there is no element left in the map
         */
        public int key() throws ConcurrentModificationException, NoSuchElementException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.key_536");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToFieldHashMap.key_536", _mut51048, _mut51049, _mut51050, _mut51051, _mut51052)) {
                throw new ConcurrentModificationException();
            }
            if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.key_536", _mut51053, _mut51054, _mut51055, _mut51056, _mut51057)) {
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
        public T value() throws ConcurrentModificationException, NoSuchElementException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.value_553");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToFieldHashMap.value_553", _mut51058, _mut51059, _mut51060, _mut51061, _mut51062)) {
                throw new ConcurrentModificationException();
            }
            if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.value_553", _mut51063, _mut51064, _mut51065, _mut51066, _mut51067)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.advance_569");
            if (ROR_not_equals(referenceCount, count, "org.apache.commons.math3.util.OpenIntToFieldHashMap.advance_569", _mut51068, _mut51069, _mut51070, _mut51071, _mut51072)) {
                throw new ConcurrentModificationException();
            }
            // advance on step
            current = next;
            // prepare next step
            try {
                while (ROR_not_equals(states[++next], FULL, "org.apache.commons.math3.util.OpenIntToFieldHashMap.advance_569", _mut51078, _mut51079, _mut51080, _mut51081, _mut51082)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.OpenIntToFieldHashMap.advance_569");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                next = -2;
                if (ROR_less(current, 0, "org.apache.commons.math3.util.OpenIntToFieldHashMap.advance_569", _mut51073, _mut51074, _mut51075, _mut51076, _mut51077)) {
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

    /**
     * Build an array of elements.
     * @param length size of the array to build
     * @return a new array
     */
    // field is of type T
    @SuppressWarnings("unchecked")
    private T[] buildArray(final int length) {
        return (T[]) Array.newInstance(field.getRuntimeClass(), length);
    }
}
