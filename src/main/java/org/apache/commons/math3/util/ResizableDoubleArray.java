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
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * A variable length {@link DoubleArray} implementation that automatically
 * handles expanding and contracting its internal storage array as elements
 * are added and removed.
 * </p>
 * <h3>Important note: Usage should not assume that this class is thread-safe
 * even though some of the methods are {@code synchronized}.
 * This qualifier will be dropped in the next major release (4.0).</h3>
 * <p>
 * The internal storage array starts with capacity determined by the
 * {@code initialCapacity} property, which can be set by the constructor.
 * The default initial capacity is 16.  Adding elements using
 * {@link #addElement(double)} appends elements to the end of the array.
 * When there are no open entries at the end of the internal storage array,
 * the array is expanded.  The size of the expanded array depends on the
 * {@code expansionMode} and {@code expansionFactor} properties.
 * The {@code expansionMode} determines whether the size of the array is
 * multiplied by the {@code expansionFactor}
 * ({@link ExpansionMode#MULTIPLICATIVE}) or if the expansion is additive
 * ({@link ExpansionMode#ADDITIVE} -- {@code expansionFactor} storage
 * locations added).
 * The default {@code expansionMode} is {@code MULTIPLICATIVE} and the default
 * {@code expansionFactor} is 2.
 * </p>
 * <p>
 * The {@link #addElementRolling(double)} method adds a new element to the end
 * of the internal storage array and adjusts the "usable window" of the
 * internal array forward by one position (effectively making what was the
 * second element the first, and so on).  Repeated activations of this method
 * (or activation of {@link #discardFrontElements(int)}) will effectively orphan
 * the storage locations at the beginning of the internal storage array.  To
 * reclaim this storage, each time one of these methods is activated, the size
 * of the internal storage array is compared to the number of addressable
 * elements (the {@code numElements} property) and if the difference
 * is too large, the internal array is contracted to size
 * {@code numElements + 1}.  The determination of when the internal
 * storage array is "too large" depends on the {@code expansionMode} and
 * {@code contractionFactor} properties.  If  the {@code expansionMode}
 * is {@code MULTIPLICATIVE}, contraction is triggered when the
 * ratio between storage array length and {@code numElements} exceeds
 * {@code contractionFactor.}  If the {@code expansionMode}
 * is {@code ADDITIVE}, the number of excess storage locations
 * is compared to {@code contractionFactor}.
 * </p>
 * <p>
 * To avoid cycles of expansions and contractions, the
 * {@code expansionFactor} must not exceed the {@code contractionFactor}.
 * Constructors and mutators for both of these properties enforce this
 * requirement, throwing a {@code MathIllegalArgumentException} if it is
 * violated.
 * </p>
 */
public class ResizableDoubleArray implements DoubleArray, Serializable {

    @Conditional
    public static boolean _mut39914 = false, _mut39915 = false, _mut39916 = false, _mut39917 = false, _mut39918 = false, _mut39919 = false, _mut39920 = false, _mut39921 = false, _mut39922 = false, _mut39923 = false, _mut39924 = false, _mut39925 = false, _mut39926 = false, _mut39927 = false, _mut39928 = false, _mut39929 = false, _mut39930 = false, _mut39931 = false, _mut39932 = false, _mut39933 = false, _mut39934 = false, _mut39935 = false, _mut39936 = false, _mut39937 = false, _mut39938 = false, _mut39939 = false, _mut39940 = false, _mut39941 = false, _mut39942 = false, _mut39943 = false, _mut39944 = false, _mut39945 = false, _mut39946 = false, _mut39947 = false, _mut39948 = false, _mut39949 = false, _mut39950 = false, _mut39951 = false, _mut39952 = false, _mut39953 = false, _mut39954 = false, _mut39955 = false, _mut39956 = false, _mut39957 = false, _mut39958 = false, _mut39959 = false, _mut39960 = false, _mut39961 = false, _mut39962 = false, _mut39963 = false, _mut39964 = false, _mut39965 = false, _mut39966 = false, _mut39967 = false, _mut39968 = false, _mut39969 = false, _mut39970 = false, _mut39971 = false, _mut39972 = false, _mut39973 = false, _mut39974 = false, _mut39975 = false, _mut39976 = false, _mut39977 = false, _mut39978 = false, _mut39979 = false, _mut39980 = false, _mut39981 = false, _mut39982 = false, _mut39983 = false, _mut39984 = false, _mut39985 = false, _mut39986 = false, _mut39987 = false, _mut39988 = false, _mut39989 = false, _mut39990 = false, _mut39991 = false, _mut39992 = false, _mut39993 = false, _mut39994 = false, _mut39995 = false, _mut39996 = false, _mut39997 = false, _mut39998 = false, _mut39999 = false, _mut40000 = false, _mut40001 = false, _mut40002 = false, _mut40003 = false, _mut40004 = false, _mut40005 = false, _mut40006 = false, _mut40007 = false, _mut40008 = false, _mut40009 = false, _mut40010 = false, _mut40011 = false, _mut40012 = false, _mut40013 = false, _mut40014 = false, _mut40015 = false, _mut40016 = false, _mut40017 = false, _mut40018 = false, _mut40019 = false, _mut40020 = false, _mut40021 = false, _mut40022 = false, _mut40023 = false, _mut40024 = false, _mut40025 = false, _mut40026 = false, _mut40027 = false, _mut40028 = false, _mut40029 = false, _mut40030 = false, _mut40031 = false, _mut40032 = false, _mut40033 = false, _mut40034 = false, _mut40035 = false, _mut40036 = false, _mut40037 = false, _mut40038 = false, _mut40039 = false, _mut40040 = false, _mut40041 = false, _mut40042 = false, _mut40043 = false, _mut40044 = false, _mut40045 = false, _mut40046 = false, _mut40047 = false, _mut40048 = false, _mut40049 = false, _mut40050 = false, _mut40051 = false, _mut40052 = false, _mut40053 = false, _mut40054 = false, _mut40055 = false, _mut40056 = false, _mut40057 = false, _mut40058 = false, _mut40059 = false, _mut40060 = false, _mut40061 = false, _mut40062 = false, _mut40063 = false, _mut40064 = false, _mut40065 = false, _mut40066 = false, _mut40067 = false, _mut40068 = false, _mut40069 = false, _mut40070 = false, _mut40071 = false, _mut40072 = false, _mut40073 = false, _mut40074 = false, _mut40075 = false, _mut40076 = false, _mut40077 = false, _mut40078 = false, _mut40079 = false, _mut40080 = false, _mut40081 = false, _mut40082 = false, _mut40083 = false, _mut40084 = false, _mut40085 = false, _mut40086 = false, _mut40087 = false, _mut40088 = false, _mut40089 = false, _mut40090 = false, _mut40091 = false, _mut40092 = false, _mut40093 = false, _mut40094 = false, _mut40095 = false, _mut40096 = false, _mut40097 = false, _mut40098 = false, _mut40099 = false, _mut40100 = false, _mut40101 = false, _mut40102 = false, _mut40103 = false, _mut40104 = false, _mut40105 = false, _mut40106 = false, _mut40107 = false, _mut40108 = false, _mut40109 = false, _mut40110 = false, _mut40111 = false, _mut40112 = false, _mut40113 = false, _mut40114 = false, _mut40115 = false, _mut40116 = false, _mut40117 = false, _mut40118 = false, _mut40119 = false, _mut40120 = false, _mut40121 = false, _mut40122 = false, _mut40123 = false, _mut40124 = false, _mut40125 = false, _mut40126 = false, _mut40127 = false, _mut40128 = false, _mut40129 = false, _mut40130 = false, _mut40131 = false, _mut40132 = false, _mut40133 = false, _mut40134 = false, _mut40135 = false, _mut40136 = false, _mut40137 = false, _mut40138 = false, _mut40139 = false, _mut40140 = false, _mut40141 = false, _mut40142 = false, _mut40143 = false, _mut40144 = false, _mut40145 = false, _mut40146 = false, _mut40147 = false, _mut40148 = false, _mut40149 = false, _mut40150 = false, _mut40151 = false, _mut40152 = false, _mut40153 = false, _mut40154 = false, _mut40155 = false, _mut40156 = false, _mut40157 = false, _mut40158 = false, _mut40159 = false, _mut40160 = false;

    /**
     * Additive expansion mode.
     * @deprecated As of 3.1. Please use {@link ExpansionMode#ADDITIVE} instead.
     */
    @Deprecated
    public static final int ADDITIVE_MODE = 1;

    /**
     * Multiplicative expansion mode.
     * @deprecated As of 3.1. Please use {@link ExpansionMode#MULTIPLICATIVE} instead.
     */
    @Deprecated
    public static final int MULTIPLICATIVE_MODE = 0;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -3485529955529426875L;

    /**
     * Default value for initial capacity.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Default value for array size modifier.
     */
    private static final double DEFAULT_EXPANSION_FACTOR = 2.0;

    /**
     * Default value for the difference between {@link #contractionCriterion}
     * and {@link #expansionFactor}.
     */
    private static final double DEFAULT_CONTRACTION_DELTA = 0.5;

    /**
     * The contraction criteria determines when the internal array will be
     * contracted to fit the number of elements contained in the element
     *  array + 1.
     */
    private double contractionCriterion = 2.5;

    /**
     * The expansion factor of the array.  When the array needs to be expanded,
     * the new array size will be
     * {@code internalArray.length * expansionFactor}
     * if {@code expansionMode} is set to MULTIPLICATIVE_MODE, or
     * {@code internalArray.length + expansionFactor} if
     * {@code expansionMode} is set to ADDITIVE_MODE.
     */
    private double expansionFactor = 2.0;

    /**
     * Determines whether array expansion by {@code expansionFactor}
     * is additive or multiplicative.
     */
    private ExpansionMode expansionMode = ExpansionMode.MULTIPLICATIVE;

    /**
     * The internal storage array.
     */
    private double[] internalArray;

    /**
     * The number of addressable elements in the array.  Note that this
     * has nothing to do with the length of the internal storage array.
     */
    private int numElements = 0;

    /**
     * The position of the first addressable element in the internal storage
     * array.  The addressable elements in the array are
     * {@code internalArray[startIndex],...,internalArray[startIndex + numElements - 1]}.
     */
    private int startIndex = 0;

    /**
     * Specification of expansion algorithm.
     * @since 3.1
     */
    public enum ExpansionMode {

        /**
         * Multiplicative expansion mode.
         */
        MULTIPLICATIVE,
        /**
         * Additive expansion mode.
         */
        ADDITIVE
    }

    /**
     * Creates an instance with default properties.
     * <ul>
     *  <li>{@code initialCapacity = 16}</li>
     *  <li>{@code expansionMode = MULTIPLICATIVE}</li>
     *  <li>{@code expansionFactor = 2.0}</li>
     *  <li>{@code contractionCriterion = 2.5}</li>
     * </ul>
     */
    public ResizableDoubleArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Creates an instance with the specified initial capacity.
     * Other properties take default values:
     * <ul>
     *  <li>{@code expansionMode = MULTIPLICATIVE}</li>
     *  <li>{@code expansionFactor = 2.0}</li>
     *  <li>{@code contractionCriterion = 2.5}</li>
     * </ul>
     * @param initialCapacity Initial size of the internal storage array.
     * @throws MathIllegalArgumentException if {@code initialCapacity <= 0}.
     */
    public ResizableDoubleArray(int initialCapacity) throws MathIllegalArgumentException {
        this(initialCapacity, DEFAULT_EXPANSION_FACTOR);
    }

    /**
     * Creates an instance from an existing {@code double[]} with the
     * initial capacity and numElements corresponding to the size of
     * the supplied {@code double[]} array.
     * If the supplied array is null, a new empty array with the default
     * initial capacity will be created.
     * The input array is copied, not referenced.
     * Other properties take default values:
     * <ul>
     *  <li>{@code initialCapacity = 16}</li>
     *  <li>{@code expansionMode = MULTIPLICATIVE}</li>
     *  <li>{@code expansionFactor = 2.0}</li>
     *  <li>{@code contractionCriterion = 2.5}</li>
     * </ul>
     *
     * @param initialArray initial array
     * @since 2.2
     */
    public ResizableDoubleArray(double[] initialArray) {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_EXPANSION_FACTOR, AOR_plus(DEFAULT_CONTRACTION_DELTA, DEFAULT_EXPANSION_FACTOR, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_206", _mut39914, _mut39915, _mut39916, _mut39917), ExpansionMode.MULTIPLICATIVE, initialArray);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_206");
    }

    /**
     * Creates an instance with the specified initial capacity
     * and expansion factor.
     * The remaining properties take default values:
     * <ul>
     *  <li>{@code expansionMode = MULTIPLICATIVE}</li>
     *  <li>{@code contractionCriterion = 0.5 + expansionFactor}</li>
     * </ul>
     * <br/>
     * Throws IllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     *  <li>{@code initialCapacity > 0}</li>
     *  <li>{@code expansionFactor > 1}</li>
     * </ul>
     *
     * @param initialCapacity Initial size of the internal storage array.
     * @param expansionFactor The array will be expanded based on this
     * parameter.
     * @throws MathIllegalArgumentException if parameters are not valid.
     * @deprecated As of 3.1. Please use
     * {@link #ResizableDoubleArray(int,double)} instead.
     */
    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor) throws MathIllegalArgumentException {
        this(initialCapacity, (double) expansionFactor);
    }

    /**
     * Creates an instance with the specified initial capacity
     * and expansion factor.
     * The remaining properties take default values:
     * <ul>
     *  <li>{@code expansionMode = MULTIPLICATIVE}</li>
     *  <li>{@code contractionCriterion = 0.5 + expansionFactor}</li>
     * </ul>
     * <br/>
     * Throws IllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     *  <li>{@code initialCapacity > 0}</li>
     *  <li>{@code expansionFactor > 1}</li>
     * </ul>
     *
     * @param initialCapacity Initial size of the internal storage array.
     * @param expansionFactor The array will be expanded based on this
     * parameter.
     * @throws MathIllegalArgumentException if parameters are not valid.
     * @since 3.1
     */
    public ResizableDoubleArray(int initialCapacity, double expansionFactor) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, AOR_plus(DEFAULT_CONTRACTION_DELTA, expansionFactor, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_267", _mut39918, _mut39919, _mut39920, _mut39921));
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_267");
    }

    /**
     * Creates an instance with the specified initialCapacity,
     * expansionFactor, and contractionCriterion.
     * The expansion mode will default to {@code MULTIPLICATIVE}.
     * <br/>
     * Throws IllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     *  <li>{@code initialCapacity > 0}</li>
     *  <li>{@code expansionFactor > 1}</li>
     *  <li>{@code contractionCriterion >= expansionFactor}</li>
     * </ul>
     *
     * @param initialCapacity Initial size of the internal storage array..
     * @param expansionFactor The array will be expanded based on this
     * parameter.
     * @param contractionCriteria Contraction criteria.
     * @throws MathIllegalArgumentException if parameters are not valid.
     * @deprecated As of 3.1. Please use
     * {@link #ResizableDoubleArray(int,double,double)} instead.
     */
    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria) throws MathIllegalArgumentException {
        this(initialCapacity, (double) expansionFactor, (double) contractionCriteria);
    }

    /**
     * Creates an instance with the specified initial capacity,
     * expansion factor, and contraction criteria.
     * The expansion mode will default to {@code MULTIPLICATIVE}.
     * <br/>
     * Throws IllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     *  <li>{@code initialCapacity > 0}</li>
     *  <li>{@code expansionFactor > 1}</li>
     *  <li>{@code contractionCriterion >= expansionFactor}</li>
     * </ul>
     *
     * @param initialCapacity Initial size of the internal storage array..
     * @param expansionFactor The array will be expanded based on this
     * parameter.
     * @param contractionCriterion Contraction criterion.
     * @throws MathIllegalArgumentException if the parameters are not valid.
     * @since 3.1
     */
    public ResizableDoubleArray(int initialCapacity, double expansionFactor, double contractionCriterion) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, contractionCriterion, ExpansionMode.MULTIPLICATIVE, null);
    }

    /**
     * <p>
     * Create a ResizableArray with the specified properties.</p>
     * <p>
     * Throws IllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     * <li><code>initialCapacity > 0</code></li>
     * <li><code>expansionFactor > 1</code></li>
     * <li><code>contractionFactor >= expansionFactor</code></li>
     * <li><code>expansionMode in {MULTIPLICATIVE_MODE, ADDITIVE_MODE}</code>
     * </li>
     * </ul></p>
     *
     * @param initialCapacity the initial size of the internal storage array
     * @param expansionFactor the array will be expanded based on this
     *                        parameter
     * @param contractionCriteria the contraction Criteria
     * @param expansionMode  the expansion mode
     * @throws MathIllegalArgumentException if parameters are not valid
     * @deprecated As of 3.1. Please use
     * {@link #ResizableDoubleArray(int,double,double,ExpansionMode,double[])}
     * instead.
     */
    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria, int expansionMode) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, contractionCriteria, ROR_equals(expansionMode, ADDITIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_361", _mut39922, _mut39923, _mut39924, _mut39925, _mut39926) ? ExpansionMode.ADDITIVE : ExpansionMode.MULTIPLICATIVE, null);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_361");
        // With the new "enum", that test will become obsolete.
        setExpansionMode(expansionMode);
    }

    /**
     * Creates an instance with the specified properties.
     * <br/>
     * Throws MathIllegalArgumentException if the following conditions are
     * not met:
     * <ul>
     *  <li>{@code initialCapacity > 0}</li>
     *  <li>{@code expansionFactor > 1}</li>
     *  <li>{@code contractionCriterion >= expansionFactor}</li>
     * </ul>
     *
     * @param initialCapacity Initial size of the internal storage array.
     * @param expansionFactor The array will be expanded based on this
     * parameter.
     * @param contractionCriterion Contraction criteria.
     * @param expansionMode Expansion mode.
     * @param data Initial contents of the array.
     * @throws MathIllegalArgumentException if the parameters are not valid.
     */
    public ResizableDoubleArray(int initialCapacity, double expansionFactor, double contractionCriterion, ExpansionMode expansionMode, double... data) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_395");
        if (ROR_less_equals(initialCapacity, 0, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_395", _mut39927, _mut39928, _mut39929, _mut39930, _mut39931)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, initialCapacity);
        }
        checkContractExpand(contractionCriterion, expansionFactor);
        this.expansionFactor = expansionFactor;
        this.contractionCriterion = contractionCriterion;
        this.expansionMode = expansionMode;
        internalArray = new double[initialCapacity];
        numElements = 0;
        startIndex = 0;
        if ((_mut39937 ? (data != null || ROR_greater(data.length, 0, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_395", _mut39932, _mut39933, _mut39934, _mut39935, _mut39936)) : (data != null && ROR_greater(data.length, 0, "org.apache.commons.math3.util.ResizableDoubleArray.ResizableDoubleArray_395", _mut39932, _mut39933, _mut39934, _mut39935, _mut39936)))) {
            addElements(data);
        }
    }

    /**
     * Copy constructor.  Creates a new ResizableDoubleArray that is a deep,
     * fresh copy of the original. Needs to acquire synchronization lock
     * on original.  Original may not be null; otherwise a {@link NullArgumentException}
     * is thrown.
     *
     * @param original array to copy
     * @exception NullArgumentException if original is null
     * @since 2.0
     */
    public ResizableDoubleArray(ResizableDoubleArray original) throws NullArgumentException {
        MathUtils.checkNotNull(original);
        copy(original, this);
    }

    /**
     * Adds an element to the end of this expandable array.
     *
     * @param value Value to be added to end of array.
     */
    public synchronized void addElement(double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.addElement_440");
        if (ROR_less_equals(internalArray.length, AOR_plus(startIndex, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.addElement_440", _mut39938, _mut39939, _mut39940, _mut39941), "org.apache.commons.math3.util.ResizableDoubleArray.addElement_440", _mut39942, _mut39943, _mut39944, _mut39945, _mut39946)) {
            expand();
        }
        internalArray[AOR_plus(startIndex, numElements++, "org.apache.commons.math3.util.ResizableDoubleArray.addElement_440", _mut39947, _mut39948, _mut39949, _mut39950)] = value;
    }

    /**
     * Adds several element to the end of this expandable array.
     *
     * @param values Values to be added to end of array.
     * @since 2.2
     */
    public synchronized void addElements(double[] values) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.addElements_453");
        final double[] tempArray = new double[AOR_plus(AOR_plus(numElements, values.length, "org.apache.commons.math3.util.ResizableDoubleArray.addElements_453", _mut39951, _mut39952, _mut39953, _mut39954), 1, "org.apache.commons.math3.util.ResizableDoubleArray.addElements_453", _mut39955, _mut39956, _mut39957, _mut39958)];
        System.arraycopy(internalArray, startIndex, tempArray, 0, numElements);
        System.arraycopy(values, 0, tempArray, numElements, values.length);
        internalArray = tempArray;
        startIndex = 0;
        numElements += values.length;
    }

    /**
     * <p>
     * Adds an element to the end of the array and removes the first
     * element in the array.  Returns the discarded first element.
     * The effect is similar to a push operation in a FIFO queue.
     * </p>
     * <p>
     * Example: If the array contains the elements 1, 2, 3, 4 (in that order)
     * and addElementRolling(5) is invoked, the result is an array containing
     * the entries 2, 3, 4, 5 and the value returned is 1.
     * </p>
     *
     * @param value Value to be added to the array.
     * @return the value which has been discarded or "pushed" out of the array
     * by this rolling insert.
     */
    public synchronized double addElementRolling(double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478");
        double discarded = internalArray[startIndex];
        if (ROR_greater((AOR_plus(startIndex, (AOR_plus(numElements, 1, "org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478", _mut39959, _mut39960, _mut39961, _mut39962)), "org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478", _mut39963, _mut39964, _mut39965, _mut39966)), internalArray.length, "org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478", _mut39967, _mut39968, _mut39969, _mut39970, _mut39971)) {
            expand();
        }
        // Increment the start index
        startIndex += 1;
        // Add the new value
        internalArray[AOR_plus(startIndex, (AOR_minus(numElements, 1, "org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478", _mut39972, _mut39973, _mut39974, _mut39975)), "org.apache.commons.math3.util.ResizableDoubleArray.addElementRolling_478", _mut39976, _mut39977, _mut39978, _mut39979)] = value;
        // Check the contraction criterion.
        if (shouldContract()) {
            contract();
        }
        return discarded;
    }

    /**
     * Substitutes <code>value</code> for the most recently added value.
     * Returns the value that has been replaced. If the array is empty (i.e.
     * if {@link #numElements} is zero), an IllegalStateException is thrown.
     *
     * @param value New value to substitute for the most recently added value
     * @return the value that has been replaced in the array.
     * @throws MathIllegalStateException if the array is empty
     * @since 2.0
     */
    public synchronized double substituteMostRecentElement(double value) throws MathIllegalStateException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.substituteMostRecentElement_507");
        if (ROR_less(numElements, 1, "org.apache.commons.math3.util.ResizableDoubleArray.substituteMostRecentElement_507", _mut39980, _mut39981, _mut39982, _mut39983, _mut39984)) {
            throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY);
        }
        final int substIndex = AOR_plus(startIndex, (AOR_minus(numElements, 1, "org.apache.commons.math3.util.ResizableDoubleArray.substituteMostRecentElement_507", _mut39985, _mut39986, _mut39987, _mut39988)), "org.apache.commons.math3.util.ResizableDoubleArray.substituteMostRecentElement_507", _mut39989, _mut39990, _mut39991, _mut39992);
        final double discarded = internalArray[substIndex];
        internalArray[substIndex] = value;
        return discarded;
    }

    /**
     * Checks the expansion factor and the contraction criterion and throws an
     * IllegalArgumentException if the contractionCriteria is less than the
     * expansionCriteria
     *
     * @param expansion factor to be checked
     * @param contraction criteria to be checked
     * @throws MathIllegalArgumentException if the contractionCriteria is less than
     * the expansionCriteria.
     * @deprecated As of 3.1. Please use
     * {@link #checkContractExpand(double,double)} instead.
     */
    @Deprecated
    protected void checkContractExpand(float contraction, float expansion) throws MathIllegalArgumentException {
        checkContractExpand((double) contraction, (double) expansion);
    }

    /**
     * Checks the expansion factor and the contraction criterion and raises
     * an exception if the contraction criterion is smaller than the
     * expansion criterion.
     *
     * @param contraction Criterion to be checked.
     * @param expansion Factor to be checked.
     * @throws NumberIsTooSmallException if {@code contraction < expansion}.
     * @throws NumberIsTooSmallException if {@code contraction <= 1}.
     * @throws NumberIsTooSmallException if {@code expansion <= 1 }.
     * @since 3.1
     */
    protected void checkContractExpand(double contraction, double expansion) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.checkContractExpand_553");
        if (ROR_less(contraction, expansion, "org.apache.commons.math3.util.ResizableDoubleArray.checkContractExpand_553", _mut39993, _mut39994, _mut39995, _mut39996, _mut39997)) {
            final NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, 1, true);
            e.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, contraction, expansion);
            throw e;
        }
        if (ROR_less_equals(contraction, 1, "org.apache.commons.math3.util.ResizableDoubleArray.checkContractExpand_553", _mut39998, _mut39999, _mut40000, _mut40001, _mut40002)) {
            final NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, 1, false);
            e.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, contraction);
            throw e;
        }
        if (ROR_less_equals(expansion, 1, "org.apache.commons.math3.util.ResizableDoubleArray.checkContractExpand_553", _mut40003, _mut40004, _mut40005, _mut40006, _mut40007)) {
            final NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, 1, false);
            e.getContext().addMessage(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, expansion);
            throw e;
        }
    }

    /**
     * Clear the array contents, resetting the number of elements to zero.
     */
    public synchronized void clear() {
        numElements = 0;
        startIndex = 0;
    }

    /**
     * Contracts the storage array to the (size of the element set) + 1 - to
     * avoid a zero length array. This function also resets the startIndex to
     * zero.
     */
    public synchronized void contract() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.contract_591");
        final double[] tempArray = new double[AOR_plus(numElements, 1, "org.apache.commons.math3.util.ResizableDoubleArray.contract_591", _mut40008, _mut40009, _mut40010, _mut40011)];
        // Copy and swap - copy only the element array from the src array.
        System.arraycopy(internalArray, startIndex, tempArray, 0, numElements);
        internalArray = tempArray;
        // Reset the start index to zero
        startIndex = 0;
    }

    /**
     * Discards the <code>i</code> initial elements of the array.  For example,
     * if the array contains the elements 1,2,3,4, invoking
     * <code>discardFrontElements(2)</code> will cause the first two elements
     * to be discarded, leaving 3,4 in the array.  Throws illegalArgumentException
     * if i exceeds numElements.
     *
     * @param i  the number of elements to discard from the front of the array
     * @throws MathIllegalArgumentException if i is greater than numElements.
     * @since 2.0
     */
    public synchronized void discardFrontElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, true);
    }

    /**
     * Discards the <code>i</code> last elements of the array.  For example,
     * if the array contains the elements 1,2,3,4, invoking
     * <code>discardMostRecentElements(2)</code> will cause the last two elements
     * to be discarded, leaving 1,2 in the array.  Throws illegalArgumentException
     * if i exceeds numElements.
     *
     * @param i  the number of elements to discard from the end of the array
     * @throws MathIllegalArgumentException if i is greater than numElements.
     * @since 2.0
     */
    public synchronized void discardMostRecentElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, false);
    }

    /**
     * Discards the <code>i</code> first or last elements of the array,
     * depending on the value of <code>front</code>.
     * For example, if the array contains the elements 1,2,3,4, invoking
     * <code>discardExtremeElements(2,false)</code> will cause the last two elements
     * to be discarded, leaving 1,2 in the array.
     * For example, if the array contains the elements 1,2,3,4, invoking
     * <code>discardExtremeElements(2,true)</code> will cause the first two elements
     * to be discarded, leaving 3,4 in the array.
     * Throws illegalArgumentException
     * if i exceeds numElements.
     *
     * @param i  the number of elements to discard from the front/end of the array
     * @param front true if elements are to be discarded from the front
     * of the array, false if elements are to be discarded from the end
     * of the array
     * @throws MathIllegalArgumentException if i is greater than numElements.
     * @since 2.0
     */
    private synchronized void discardExtremeElements(int i, boolean front) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.discardExtremeElements_653");
        if (ROR_greater(i, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.discardExtremeElements_653", _mut40012, _mut40013, _mut40014, _mut40015, _mut40016)) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, i, numElements);
        } else if (ROR_less(i, 0, "org.apache.commons.math3.util.ResizableDoubleArray.discardExtremeElements_653", _mut40017, _mut40018, _mut40019, _mut40020, _mut40021)) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, i);
        } else {
            // "Subtract" this number of discarded from numElements
            numElements -= i;
            if (front) {
                startIndex += i;
            }
        }
        if (shouldContract()) {
            contract();
        }
    }

    /**
     * Expands the internal storage array using the expansion factor.
     * <p>
     * if <code>expansionMode</code> is set to MULTIPLICATIVE_MODE,
     * the new array size will be <code>internalArray.length * expansionFactor.</code>
     * If <code>expansionMode</code> is set to ADDITIVE_MODE,  the length
     * after expansion will be <code>internalArray.length + expansionFactor</code>
     * </p>
     */
    protected synchronized void expand() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.expand_685");
        // rounded up to 2 after the multiplication is performed.
        int newSize = 0;
        if (expansionMode == ExpansionMode.MULTIPLICATIVE) {
            newSize = (int) FastMath.ceil(AOR_multiply(internalArray.length, expansionFactor, "org.apache.commons.math3.util.ResizableDoubleArray.expand_685", _mut40026, _mut40027, _mut40028, _mut40029));
        } else {
            newSize = (int) (AOR_plus(internalArray.length, FastMath.round(expansionFactor), "org.apache.commons.math3.util.ResizableDoubleArray.expand_685", _mut40022, _mut40023, _mut40024, _mut40025));
        }
        final double[] tempArray = new double[newSize];
        // Copy and swap
        System.arraycopy(internalArray, 0, tempArray, 0, internalArray.length);
        internalArray = tempArray;
    }

    /**
     * Expands the internal storage array to the specified size.
     *
     * @param size Size of the new internal storage array.
     */
    private synchronized void expandTo(int size) {
        final double[] tempArray = new double[size];
        // Copy and swap
        System.arraycopy(internalArray, 0, tempArray, 0, internalArray.length);
        internalArray = tempArray;
    }

    /**
     * The contraction criteria defines when the internal array will contract
     * to store only the number of elements in the element array.
     * If  the <code>expansionMode</code> is <code>MULTIPLICATIVE_MODE</code>,
     * contraction is triggered when the ratio between storage array length
     * and <code>numElements</code> exceeds <code>contractionFactor</code>.
     * If the <code>expansionMode</code> is <code>ADDITIVE_MODE</code>, the
     * number of excess storage locations is compared to
     * <code>contractionFactor.</code>
     *
     * @return the contraction criteria used to reclaim memory.
     * @deprecated As of 3.1. Please use {@link #getContractionCriterion()}
     * instead.
     */
    @Deprecated
    public float getContractionCriteria() {
        return (float) getContractionCriterion();
    }

    /**
     * The contraction criterion defines when the internal array will contract
     * to store only the number of elements in the element array.
     * If  the <code>expansionMode</code> is <code>MULTIPLICATIVE_MODE</code>,
     * contraction is triggered when the ratio between storage array length
     * and <code>numElements</code> exceeds <code>contractionFactor</code>.
     * If the <code>expansionMode</code> is <code>ADDITIVE_MODE</code>, the
     * number of excess storage locations is compared to
     * <code>contractionFactor.</code>
     *
     * @return the contraction criterion used to reclaim memory.
     * @since 3.1
     */
    public double getContractionCriterion() {
        return contractionCriterion;
    }

    /**
     * Returns the element at the specified index
     *
     * @param index index to fetch a value from
     * @return value stored at the specified index
     * @throws ArrayIndexOutOfBoundsException if <code>index</code> is less than
     * zero or is greater than <code>getNumElements() - 1</code>.
     */
    public synchronized double getElement(int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.getElement_760");
        if (ROR_greater_equals(index, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.getElement_760", _mut40030, _mut40031, _mut40032, _mut40033, _mut40034)) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else if (ROR_greater_equals(index, 0, "org.apache.commons.math3.util.ResizableDoubleArray.getElement_760", _mut40035, _mut40036, _mut40037, _mut40038, _mut40039)) {
            return internalArray[AOR_plus(startIndex, index, "org.apache.commons.math3.util.ResizableDoubleArray.getElement_760", _mut40040, _mut40041, _mut40042, _mut40043)];
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    /**
     * Returns a double array containing the elements of this
     * <code>ResizableArray</code>.  This method returns a copy, not a
     * reference to the underlying array, so that changes made to the returned
     *  array have no effect on this <code>ResizableArray.</code>
     * @return the double array.
     */
    public synchronized double[] getElements() {
        final double[] elementArray = new double[numElements];
        System.arraycopy(internalArray, startIndex, elementArray, 0, numElements);
        return elementArray;
    }

    /**
     * The expansion factor controls the size of a new array when an array
     * needs to be expanded.  The <code>expansionMode</code>
     * determines whether the size of the array is multiplied by the
     * <code>expansionFactor</code> (MULTIPLICATIVE_MODE) or if
     * the expansion is additive (ADDITIVE_MODE -- <code>expansionFactor</code>
     * storage locations added).  The default <code>expansionMode</code> is
     * MULTIPLICATIVE_MODE and the default <code>expansionFactor</code>
     * is 2.0.
     *
     * @return the expansion factor of this expandable double array
     * @deprecated As of 3.1. Return type will be changed to "double" in 4.0.
     */
    @Deprecated
    public float getExpansionFactor() {
        return (float) expansionFactor;
    }

    /**
     * The expansion mode determines whether the internal storage
     * array grows additively or multiplicatively when it is expanded.
     *
     * @return the expansion mode.
     * @deprecated As of 3.1. Return value to be changed to
     * {@link ExpansionMode} in 4.0.
     */
    @Deprecated
    public int getExpansionMode() {
        synchronized (this) {
            switch(expansionMode) {
                case MULTIPLICATIVE:
                    return MULTIPLICATIVE_MODE;
                case ADDITIVE:
                    return ADDITIVE_MODE;
                default:
                    // Should never happen.
                    throw new MathInternalError();
            }
        }
    }

    /**
     * Notice the package scope on this method.   This method is simply here
     * for the JUnit test, it allows us check if the expansion is working
     * properly after a number of expansions.  This is not meant to be a part
     * of the public interface of this class.
     *
     * @return the length of the internal storage array.
     * @deprecated As of 3.1. Please use {@link #getCapacity()} instead.
     */
    @Deprecated
    synchronized int getInternalLength() {
        return internalArray.length;
    }

    /**
     * Gets the currently allocated size of the internal data structure used
     * for storing elements.
     * This is not to be confused with {@link #getNumElements() the number of
     * elements actually stored}.
     *
     * @return the length of the internal array.
     * @since 3.1
     */
    public int getCapacity() {
        return internalArray.length;
    }

    /**
     * Returns the number of elements currently in the array.  Please note
     * that this is different from the length of the internal storage array.
     *
     * @return the number of elements.
     */
    public synchronized int getNumElements() {
        return numElements;
    }

    /**
     * Returns the internal storage array.  Note that this method returns
     * a reference to the internal storage array, not a copy, and to correctly
     * address elements of the array, the <code>startIndex</code> is
     * required (available via the {@link #start} method).  This method should
     * only be used in cases where copying the internal array is not practical.
     * The {@link #getElements} method should be used in all other cases.
     *
     * @return the internal storage array used by this object
     * @since 2.0
     * @deprecated As of 3.1.
     */
    @Deprecated
    public synchronized double[] getInternalValues() {
        return internalArray;
    }

    /**
     * Provides <em>direct</em> access to the internal storage array.
     * Please note that this method returns a reference to this object's
     * storage array, not a copy.
     * <br/>
     * To correctly address elements of the array, the "start index" is
     * required (available via the {@link #getStartIndex() getStartIndex}
     * method.
     * <br/>
     * This method should only be used to avoid copying the internal array.
     * The returned value <em>must</em> be used for reading only; other
     * uses could lead to this object becoming inconsistent.
     * <br/>
     * The {@link #getElements} method has no such limitation since it
     * returns a copy of this array's addressable elements.
     *
     * @return the internal storage array used by this object.
     * @since 3.1
     */
    protected double[] getArrayRef() {
        return internalArray;
    }

    /**
     * Returns the "start index" of the internal array.
     * This index is the position of the first addressable element in the
     * internal storage array.
     * The addressable elements in the array are at indices contained in
     * the interval [{@link #getStartIndex()},
     *               {@link #getStartIndex()} + {@link #getNumElements()} - 1].
     *
     * @return the start index.
     * @since 3.1
     */
    protected int getStartIndex() {
        return startIndex;
    }

    /**
     * Sets the contraction criteria.
     *
     * @param contractionCriteria contraction criteria
     * @throws MathIllegalArgumentException if the contractionCriteria is less than
     *         the expansionCriteria.
     * @deprecated As of 3.1 (to be removed in 4.0 as field will become "final").
     */
    @Deprecated
    public void setContractionCriteria(float contractionCriteria) throws MathIllegalArgumentException {
        checkContractExpand(contractionCriteria, getExpansionFactor());
        synchronized (this) {
            this.contractionCriterion = contractionCriteria;
        }
    }

    /**
     * Performs an operation on the addressable elements of the array.
     *
     * @param f Function to be applied on this array.
     * @return the result.
     * @since 3.1
     */
    public double compute(MathArrays.Function f) {
        final double[] array;
        final int start;
        final int num;
        synchronized (this) {
            array = internalArray;
            start = startIndex;
            num = numElements;
        }
        return f.evaluate(array, start, num);
    }

    /**
     * Sets the element at the specified index.  If the specified index is greater than
     * <code>getNumElements() - 1</code>, the <code>numElements</code> property
     * is increased to <code>index +1</code> and additional storage is allocated
     * (if necessary) for the new element and all  (uninitialized) elements
     * between the new element and the previous end of the array).
     *
     * @param index index to store a value in
     * @param value value to store at the specified index
     * @throws ArrayIndexOutOfBoundsException if {@code index < 0}.
     */
    public synchronized void setElement(int index, double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.setElement_963");
        if (ROR_less(index, 0, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40044, _mut40045, _mut40046, _mut40047, _mut40048)) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (ROR_greater(AOR_plus(index, 1, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40049, _mut40050, _mut40051, _mut40052), numElements, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40053, _mut40054, _mut40055, _mut40056, _mut40057)) {
            numElements = AOR_plus(index, 1, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40058, _mut40059, _mut40060, _mut40061);
        }
        if (ROR_greater_equals((AOR_plus(startIndex, index, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40062, _mut40063, _mut40064, _mut40065)), internalArray.length, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40066, _mut40067, _mut40068, _mut40069, _mut40070)) {
            expandTo(AOR_plus(startIndex, (AOR_plus(index, 1, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40071, _mut40072, _mut40073, _mut40074)), "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40075, _mut40076, _mut40077, _mut40078));
        }
        internalArray[AOR_plus(startIndex, index, "org.apache.commons.math3.util.ResizableDoubleArray.setElement_963", _mut40079, _mut40080, _mut40081, _mut40082)] = value;
    }

    /**
     * Sets the expansionFactor.  Throws IllegalArgumentException if the
     * the following conditions are not met:
     * <ul>
     * <li><code>expansionFactor > 1</code></li>
     * <li><code>contractionFactor >= expansionFactor</code></li>
     * </ul>
     * @param expansionFactor the new expansion factor value.
     * @throws MathIllegalArgumentException if expansionFactor is <= 1 or greater
     * than contractionFactor
     * @deprecated As of 3.1 (to be removed in 4.0 as field will become "final").
     */
    @Deprecated
    public void setExpansionFactor(float expansionFactor) throws MathIllegalArgumentException {
        checkContractExpand(getContractionCriterion(), expansionFactor);
        // The check above verifies that the expansion factor is > 1.0;
        synchronized (this) {
            this.expansionFactor = expansionFactor;
        }
    }

    /**
     * Sets the <code>expansionMode</code>. The specified value must be one of
     * ADDITIVE_MODE, MULTIPLICATIVE_MODE.
     *
     * @param expansionMode The expansionMode to set.
     * @throws MathIllegalArgumentException if the specified mode value is not valid.
     * @deprecated As of 3.1. Please use {@link #setExpansionMode(ExpansionMode)} instead.
     */
    @Deprecated
    public void setExpansionMode(int expansionMode) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005");
        if ((_mut40093 ? (ROR_not_equals(expansionMode, MULTIPLICATIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40083, _mut40084, _mut40085, _mut40086, _mut40087) || ROR_not_equals(expansionMode, ADDITIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40088, _mut40089, _mut40090, _mut40091, _mut40092)) : (ROR_not_equals(expansionMode, MULTIPLICATIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40083, _mut40084, _mut40085, _mut40086, _mut40087) && ROR_not_equals(expansionMode, ADDITIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40088, _mut40089, _mut40090, _mut40091, _mut40092)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, expansionMode, MULTIPLICATIVE_MODE, "MULTIPLICATIVE_MODE", ADDITIVE_MODE, "ADDITIVE_MODE");
        }
        synchronized (this) {
            if (ROR_equals(expansionMode, MULTIPLICATIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40094, _mut40095, _mut40096, _mut40097, _mut40098)) {
                setExpansionMode(ExpansionMode.MULTIPLICATIVE);
            } else if (ROR_equals(expansionMode, ADDITIVE_MODE, "org.apache.commons.math3.util.ResizableDoubleArray.setExpansionMode_1005", _mut40099, _mut40100, _mut40101, _mut40102, _mut40103)) {
                setExpansionMode(ExpansionMode.ADDITIVE);
            }
        }
    }

    /**
     * Sets the {@link ExpansionMode expansion mode}.
     *
     * @param expansionMode Expansion mode to use for resizing the array.
     * @deprecated As of 3.1 (to be removed in 4.0 as field will become "final").
     */
    @Deprecated
    public void setExpansionMode(ExpansionMode expansionMode) {
        synchronized (this) {
            this.expansionMode = expansionMode;
        }
    }

    /**
     * Sets the initial capacity.  Should only be invoked by constructors.
     *
     * @param initialCapacity of the array
     * @throws MathIllegalArgumentException if <code>initialCapacity</code> is not
     * positive.
     * @deprecated As of 3.1, this is a no-op.
     */
    @Deprecated
    protected void setInitialCapacity(int initialCapacity) throws MathIllegalArgumentException {
    }

    /**
     * This function allows you to control the number of elements contained
     * in this array, and can be used to "throw out" the last n values in an
     * array. This function will also expand the internal array as needed.
     *
     * @param i a new number of elements
     * @throws MathIllegalArgumentException if <code>i</code> is negative.
     */
    public synchronized void setNumElements(int i) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.setNumElements_1058");
        // If index is negative thrown an error.
        if (ROR_less(i, 0, "org.apache.commons.math3.util.ResizableDoubleArray.setNumElements_1058", _mut40104, _mut40105, _mut40106, _mut40107, _mut40108)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, i);
        }
        // expanded to accommodate this new number of elements.
        final int newSize = AOR_plus(startIndex, i, "org.apache.commons.math3.util.ResizableDoubleArray.setNumElements_1058", _mut40109, _mut40110, _mut40111, _mut40112);
        if (ROR_greater(newSize, internalArray.length, "org.apache.commons.math3.util.ResizableDoubleArray.setNumElements_1058", _mut40113, _mut40114, _mut40115, _mut40116, _mut40117)) {
            expandTo(newSize);
        }
        // Set the new number of elements to new value.
        numElements = i;
    }

    /**
     * Returns true if the internal storage array has too many unused
     * storage positions.
     *
     * @return true if array satisfies the contraction criteria
     */
    private synchronized boolean shouldContract() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.shouldContract_1084");
        if (expansionMode == ExpansionMode.MULTIPLICATIVE) {
            return ROR_greater((AOR_divide(internalArray.length, ((float) numElements), "org.apache.commons.math3.util.ResizableDoubleArray.shouldContract_1084", _mut40127, _mut40128, _mut40129, _mut40130)), contractionCriterion, "org.apache.commons.math3.util.ResizableDoubleArray.shouldContract_1084", _mut40131, _mut40132, _mut40133, _mut40134, _mut40135);
        } else {
            return ROR_greater((AOR_minus(internalArray.length, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.shouldContract_1084", _mut40118, _mut40119, _mut40120, _mut40121)), contractionCriterion, "org.apache.commons.math3.util.ResizableDoubleArray.shouldContract_1084", _mut40122, _mut40123, _mut40124, _mut40125, _mut40126);
        }
    }

    /**
     * Returns the starting index of the internal array.  The starting index is
     * the position of the first addressable element in the internal storage
     * array.  The addressable elements in the array are <code>
     * internalArray[startIndex],...,internalArray[startIndex + numElements -1]
     * </code>
     *
     * @return the starting index.
     * @deprecated As of 3.1.
     */
    @Deprecated
    public synchronized int start() {
        return startIndex;
    }

    /**
     * <p>Copies source to dest, copying the underlying data, so dest is
     * a new, independent copy of source.  Does not contract before
     * the copy.</p>
     *
     * <p>Obtains synchronization locks on both source and dest
     * (in that order) before performing the copy.</p>
     *
     * <p>Neither source nor dest may be null; otherwise a {@link NullArgumentException}
     * is thrown</p>
     *
     * @param source ResizableDoubleArray to copy
     * @param dest ResizableArray to replace with a copy of the source array
     * @exception NullArgumentException if either source or dest is null
     * @since 2.0
     */
    public static void copy(ResizableDoubleArray source, ResizableDoubleArray dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        synchronized (source) {
            synchronized (dest) {
                dest.contractionCriterion = source.contractionCriterion;
                dest.expansionFactor = source.expansionFactor;
                dest.expansionMode = source.expansionMode;
                dest.internalArray = new double[source.internalArray.length];
                System.arraycopy(source.internalArray, 0, dest.internalArray, 0, dest.internalArray.length);
                dest.numElements = source.numElements;
                dest.startIndex = source.startIndex;
            }
        }
    }

    /**
     * Returns a copy of the ResizableDoubleArray.  Does not contract before
     * the copy, so the returned object is an exact copy of this.
     *
     * @return a new ResizableDoubleArray with the same data and configuration
     * properties as this
     * @since 2.0
     */
    public synchronized ResizableDoubleArray copy() {
        final ResizableDoubleArray result = new ResizableDoubleArray();
        copy(this, result);
        return result;
    }

    /**
     * Returns true iff object is a ResizableDoubleArray with the same properties
     * as this and an identical internal storage array.
     *
     * @param object object to be compared for equality with this
     * @return true iff object is a ResizableDoubleArray with the same data and
     * properties as this
     * @since 2.0
     */
    @Override
    public boolean equals(Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ResizableDoubleArray.equals_1166");
        if (object == this) {
            return true;
        }
        if (object instanceof ResizableDoubleArray == false) {
            return false;
        }
        synchronized (this) {
            synchronized (object) {
                boolean result = true;
                final ResizableDoubleArray other = (ResizableDoubleArray) object;
                result = (_mut40141 ? (result || (ROR_equals(other.contractionCriterion, contractionCriterion, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40136, _mut40137, _mut40138, _mut40139, _mut40140))) : (result && (ROR_equals(other.contractionCriterion, contractionCriterion, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40136, _mut40137, _mut40138, _mut40139, _mut40140))));
                result = (_mut40147 ? (result || (ROR_equals(other.expansionFactor, expansionFactor, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40142, _mut40143, _mut40144, _mut40145, _mut40146))) : (result && (ROR_equals(other.expansionFactor, expansionFactor, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40142, _mut40143, _mut40144, _mut40145, _mut40146))));
                result = (_mut40148 ? (result || (other.expansionMode == expansionMode)) : (result && (other.expansionMode == expansionMode)));
                result = (_mut40154 ? (result || (ROR_equals(other.numElements, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40149, _mut40150, _mut40151, _mut40152, _mut40153))) : (result && (ROR_equals(other.numElements, numElements, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40149, _mut40150, _mut40151, _mut40152, _mut40153))));
                result = (_mut40160 ? (result || (ROR_equals(other.startIndex, startIndex, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40155, _mut40156, _mut40157, _mut40158, _mut40159))) : (result && (ROR_equals(other.startIndex, startIndex, "org.apache.commons.math3.util.ResizableDoubleArray.equals_1166", _mut40155, _mut40156, _mut40157, _mut40158, _mut40159))));
                if (!result) {
                    return false;
                } else {
                    return Arrays.equals(internalArray, other.internalArray);
                }
            }
        }
    }

    /**
     * Returns a hash code consistent with equals.
     *
     * @return the hash code representing this {@code ResizableDoubleArray}.
     * @since 2.0
     */
    @Override
    public synchronized int hashCode() {
        final int[] hashData = new int[6];
        hashData[0] = Double.valueOf(expansionFactor).hashCode();
        hashData[1] = Double.valueOf(contractionCriterion).hashCode();
        hashData[2] = expansionMode.hashCode();
        hashData[3] = Arrays.hashCode(internalArray);
        hashData[4] = numElements;
        hashData[5] = startIndex;
        return Arrays.hashCode(hashData);
    }
}
