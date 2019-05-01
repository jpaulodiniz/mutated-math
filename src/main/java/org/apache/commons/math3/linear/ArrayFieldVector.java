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
package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the {@link FieldVector} interface with a {@link FieldElement} array.
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class ArrayFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {

    @Conditional
    public static boolean _mut32827 = false, _mut32828 = false, _mut32829 = false, _mut32830 = false, _mut32831 = false, _mut32832 = false, _mut32833 = false, _mut32834 = false, _mut32835 = false, _mut32836 = false, _mut32837 = false, _mut32838 = false, _mut32839 = false, _mut32840 = false, _mut32841 = false, _mut32842 = false, _mut32843 = false, _mut32844 = false, _mut32845 = false, _mut32846 = false, _mut32847 = false, _mut32848 = false, _mut32849 = false, _mut32850 = false, _mut32851 = false, _mut32852 = false, _mut32853 = false, _mut32854 = false, _mut32855 = false, _mut32856 = false, _mut32857 = false, _mut32858 = false, _mut32859 = false, _mut32860 = false, _mut32861 = false, _mut32862 = false, _mut32863 = false, _mut32864 = false, _mut32865 = false, _mut32866 = false, _mut32867 = false, _mut32868 = false, _mut32869 = false, _mut32870 = false, _mut32871 = false, _mut32872 = false, _mut32873 = false, _mut32874 = false, _mut32875 = false, _mut32876 = false, _mut32877 = false, _mut32878 = false, _mut32879 = false, _mut32880 = false, _mut32881 = false, _mut32882 = false, _mut32883 = false, _mut32884 = false, _mut32885 = false, _mut32886 = false, _mut32887 = false, _mut32888 = false, _mut32889 = false, _mut32890 = false, _mut32891 = false, _mut32892 = false, _mut32893 = false, _mut32894 = false, _mut32895 = false, _mut32896 = false, _mut32897 = false, _mut32898 = false, _mut32899 = false, _mut32900 = false, _mut32901 = false, _mut32902 = false, _mut32903 = false, _mut32904 = false, _mut32905 = false, _mut32906 = false, _mut32907 = false, _mut32908 = false, _mut32909 = false, _mut32910 = false, _mut32911 = false, _mut32912 = false, _mut32913 = false, _mut32914 = false, _mut32915 = false, _mut32916 = false, _mut32917 = false, _mut32918 = false, _mut32919 = false, _mut32920 = false, _mut32921 = false, _mut32922 = false, _mut32923 = false, _mut32924 = false, _mut32925 = false, _mut32926 = false, _mut32927 = false, _mut32928 = false, _mut32929 = false, _mut32930 = false, _mut32931 = false, _mut32932 = false, _mut32933 = false, _mut32934 = false, _mut32935 = false, _mut32936 = false, _mut32937 = false, _mut32938 = false, _mut32939 = false, _mut32940 = false, _mut32941 = false, _mut32942 = false, _mut32943 = false, _mut32944 = false, _mut32945 = false, _mut32946 = false, _mut32947 = false, _mut32948 = false, _mut32949 = false, _mut32950 = false, _mut32951 = false, _mut32952 = false, _mut32953 = false, _mut32954 = false, _mut32955 = false, _mut32956 = false, _mut32957 = false, _mut32958 = false, _mut32959 = false, _mut32960 = false, _mut32961 = false, _mut32962 = false, _mut32963 = false, _mut32964 = false, _mut32965 = false, _mut32966 = false, _mut32967 = false, _mut32968 = false, _mut32969 = false, _mut32970 = false, _mut32971 = false, _mut32972 = false, _mut32973 = false, _mut32974 = false, _mut32975 = false, _mut32976 = false, _mut32977 = false, _mut32978 = false, _mut32979 = false, _mut32980 = false, _mut32981 = false, _mut32982 = false, _mut32983 = false, _mut32984 = false, _mut32985 = false, _mut32986 = false, _mut32987 = false, _mut32988 = false, _mut32989 = false, _mut32990 = false, _mut32991 = false, _mut32992 = false, _mut32993 = false, _mut32994 = false, _mut32995 = false, _mut32996 = false, _mut32997 = false, _mut32998 = false, _mut32999 = false, _mut33000 = false, _mut33001 = false, _mut33002 = false, _mut33003 = false, _mut33004 = false, _mut33005 = false, _mut33006 = false, _mut33007 = false, _mut33008 = false, _mut33009 = false, _mut33010 = false, _mut33011 = false, _mut33012 = false, _mut33013 = false, _mut33014 = false, _mut33015 = false, _mut33016 = false, _mut33017 = false, _mut33018 = false, _mut33019 = false, _mut33020 = false, _mut33021 = false, _mut33022 = false, _mut33023 = false, _mut33024 = false, _mut33025 = false, _mut33026 = false, _mut33027 = false, _mut33028 = false, _mut33029 = false, _mut33030 = false, _mut33031 = false, _mut33032 = false, _mut33033 = false, _mut33034 = false, _mut33035 = false, _mut33036 = false, _mut33037 = false, _mut33038 = false, _mut33039 = false, _mut33040 = false, _mut33041 = false, _mut33042 = false, _mut33043 = false, _mut33044 = false, _mut33045 = false, _mut33046 = false, _mut33047 = false, _mut33048 = false, _mut33049 = false, _mut33050 = false, _mut33051 = false, _mut33052 = false, _mut33053 = false, _mut33054 = false, _mut33055 = false, _mut33056 = false, _mut33057 = false, _mut33058 = false, _mut33059 = false, _mut33060 = false, _mut33061 = false, _mut33062 = false, _mut33063 = false, _mut33064 = false, _mut33065 = false, _mut33066 = false, _mut33067 = false, _mut33068 = false, _mut33069 = false, _mut33070 = false, _mut33071 = false, _mut33072 = false, _mut33073 = false, _mut33074 = false, _mut33075 = false, _mut33076 = false, _mut33077 = false, _mut33078 = false, _mut33079 = false, _mut33080 = false, _mut33081 = false, _mut33082 = false, _mut33083 = false, _mut33084 = false, _mut33085 = false, _mut33086 = false, _mut33087 = false, _mut33088 = false, _mut33089 = false, _mut33090 = false, _mut33091 = false, _mut33092 = false, _mut33093 = false, _mut33094 = false, _mut33095 = false, _mut33096 = false, _mut33097 = false, _mut33098 = false, _mut33099 = false, _mut33100 = false, _mut33101 = false, _mut33102 = false, _mut33103 = false, _mut33104 = false, _mut33105 = false, _mut33106 = false, _mut33107 = false, _mut33108 = false, _mut33109 = false, _mut33110 = false, _mut33111 = false, _mut33112 = false, _mut33113 = false, _mut33114 = false, _mut33115 = false, _mut33116 = false, _mut33117 = false, _mut33118 = false, _mut33119 = false, _mut33120 = false, _mut33121 = false, _mut33122 = false, _mut33123 = false, _mut33124 = false, _mut33125 = false, _mut33126 = false, _mut33127 = false, _mut33128 = false, _mut33129 = false, _mut33130 = false, _mut33131 = false, _mut33132 = false, _mut33133 = false, _mut33134 = false, _mut33135 = false, _mut33136 = false, _mut33137 = false, _mut33138 = false, _mut33139 = false, _mut33140 = false, _mut33141 = false, _mut33142 = false, _mut33143 = false, _mut33144 = false, _mut33145 = false, _mut33146 = false, _mut33147 = false, _mut33148 = false, _mut33149 = false, _mut33150 = false, _mut33151 = false, _mut33152 = false, _mut33153 = false, _mut33154 = false, _mut33155 = false, _mut33156 = false, _mut33157 = false, _mut33158 = false, _mut33159 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 7648186910365927050L;

    /**
     * Entries of the vector.
     */
    private T[] data;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Build a 0-length vector.
     * Zero-length vectors may be used to initialize construction of vectors
     * by data gathering. We start with zero-length and use either the {@link
     * #ArrayFieldVector(ArrayFieldVector, ArrayFieldVector)} constructor
     * or one of the {@code append} methods ({@link #add(FieldVector)} or
     * {@link #append(ArrayFieldVector)}) to gather data into this vector.
     *
     * @param field field to which the elements belong
     */
    public ArrayFieldVector(final Field<T> field) {
        this(field, 0);
    }

    /**
     * Construct a vector of zeroes.
     *
     * @param field Field to which the elements belong.
     * @param size Size of the vector.
     */
    public ArrayFieldVector(Field<T> field, int size) {
        this.field = field;
        this.data = MathArrays.buildArray(field, size);
    }

    /**
     * Construct a vector with preset values.
     *
     * @param size Size of the vector.
     * @param preset All entries will be set with this value.
     */
    public ArrayFieldVector(int size, T preset) {
        this(preset.getField(), size);
        Arrays.fill(data, preset);
    }

    /**
     * Construct a vector from an array, copying the input array.
     * This constructor needs a non-empty {@code d} array to retrieve
     * the field from its first element. This implies it cannot build
     * 0 length vectors. To build vectors from any size, one should
     * use the {@link #ArrayFieldVector(Field, FieldElement[])} constructor.
     *
     * @param d Array.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws ZeroException if {@code d} is empty.
     * @see #ArrayFieldVector(Field, FieldElement[])
     */
    public ArrayFieldVector(T[] d) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(d);
        try {
            field = d[0].getField();
            data = d.clone();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
        }
    }

    /**
     * Construct a vector from an array, copying the input array.
     *
     * @param field Field to which the elements belong.
     * @param d Array.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #ArrayFieldVector(FieldElement[])
     */
    public ArrayFieldVector(Field<T> field, T[] d) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        this.field = field;
        data = d.clone();
    }

    /**
     * Create a new ArrayFieldVector using the input array as the underlying
     * data array.
     * If an array is built specially in order to be embedded in a
     * ArrayFieldVector and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.
     * This constructor needs a non-empty {@code d} array to retrieve
     * the field from its first element. This implies it cannot build
     * 0 length vectors. To build vectors from any size, one should
     * use the {@link #ArrayFieldVector(Field, FieldElement[], boolean)}
     * constructor.
     *
     * @param d Data for the new vector.
     * @param copyArray If {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws ZeroException if {@code d} is empty.
     * @see #ArrayFieldVector(FieldElement[])
     * @see #ArrayFieldVector(Field, FieldElement[], boolean)
     */
    public ArrayFieldVector(T[] d, boolean copyArray) throws NullArgumentException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_146");
        MathUtils.checkNotNull(d);
        if (ROR_equals(d.length, 0, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_146", _mut32827, _mut32828, _mut32829, _mut32830, _mut32831)) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
        }
        field = d[0].getField();
        data = copyArray ? d.clone() : d;
    }

    /**
     * Create a new ArrayFieldVector using the input array as the underlying
     * data array.
     * If an array is built specially in order to be embedded in a
     * ArrayFieldVector and not used directly, the {@code copyArray} may be
     * set to {@code false}. This will prevent the copying and improve
     * performance as no new array will be built and no data will be copied.
     *
     * @param field Field to which the elements belong.
     * @param d Data for the new vector.
     * @param copyArray If {@code true}, the input array will be copied,
     * otherwise it will be referenced.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @see #ArrayFieldVector(FieldElement[], boolean)
     */
    public ArrayFieldVector(Field<T> field, T[] d, boolean copyArray) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        this.field = field;
        data = copyArray ? d.clone() : d;
    }

    /**
     * Construct a vector from part of a array.
     *
     * @param d Array.
     * @param pos Position of the first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayFieldVector(T[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_188");
        MathUtils.checkNotNull(d);
        if (ROR_less(d.length, AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_188", _mut32832, _mut32833, _mut32834, _mut32835), "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_188", _mut32836, _mut32837, _mut32838, _mut32839, _mut32840)) {
            throw new NumberIsTooLargeException(AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_188", _mut32841, _mut32842, _mut32843, _mut32844), d.length, true);
        }
        field = d[0].getField();
        data = MathArrays.buildArray(field, size);
        System.arraycopy(d, pos, data, 0, size);
    }

    /**
     * Construct a vector from part of a array.
     *
     * @param field Field to which the elements belong.
     * @param d Array.
     * @param pos Position of the first entry.
     * @param size Number of entries to copy.
     * @throws NullArgumentException if {@code d} is {@code null}.
     * @throws NumberIsTooLargeException if the size of {@code d} is less
     * than {@code pos + size}.
     */
    public ArrayFieldVector(Field<T> field, T[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_210");
        MathUtils.checkNotNull(d);
        if (ROR_less(d.length, AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_210", _mut32845, _mut32846, _mut32847, _mut32848), "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_210", _mut32849, _mut32850, _mut32851, _mut32852, _mut32853)) {
            throw new NumberIsTooLargeException(AOR_plus(pos, size, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_210", _mut32854, _mut32855, _mut32856, _mut32857), d.length, true);
        }
        this.field = field;
        data = MathArrays.buildArray(field, size);
        System.arraycopy(d, pos, data, 0, size);
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v Vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayFieldVector(FieldVector<T> v) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_227");
        MathUtils.checkNotNull(v);
        field = v.getField();
        data = MathArrays.buildArray(field, v.getDimension());
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_227", _mut32858, _mut32859, _mut32860, _mut32861, _mut32862); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_227");
            data[i] = v.getEntry(i);
        }
    }

    /**
     * Construct a vector from another vector, using a deep copy.
     *
     * @param v Vector to copy.
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayFieldVector(ArrayFieldVector<T> v) throws NullArgumentException {
        MathUtils.checkNotNull(v);
        field = v.getField();
        data = v.data.clone();
    }

    /**
     * Construct a vector from another vector.
     *
     * @param v Vector to copy.
     * @param deep If {@code true} perform a deep copy, otherwise perform
     * a shallow copy
     * @throws NullArgumentException if {@code v} is {@code null}.
     */
    public ArrayFieldVector(ArrayFieldVector<T> v, boolean deep) throws NullArgumentException {
        MathUtils.checkNotNull(v);
        field = v.getField();
        data = deep ? v.data.clone() : v.data;
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector(FieldVector, FieldVector)}
     */
    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> v1, ArrayFieldVector<T> v2) throws NullArgumentException {
        this((FieldVector<T>) v1, (FieldVector<T>) v2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @since 3.2
     */
    public ArrayFieldVector(FieldVector<T> v1, FieldVector<T> v2) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_289");
        MathUtils.checkNotNull(v1);
        MathUtils.checkNotNull(v2);
        field = v1.getField();
        final T[] v1Data = (v1 instanceof ArrayFieldVector) ? ((ArrayFieldVector<T>) v1).data : v1.toArray();
        final T[] v2Data = (v2 instanceof ArrayFieldVector) ? ((ArrayFieldVector<T>) v2).data : v2.toArray();
        data = MathArrays.buildArray(field, AOR_plus(v1Data.length, v2Data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_289", _mut32863, _mut32864, _mut32865, _mut32866));
        System.arraycopy(v1Data, 0, data, 0, v1Data.length);
        System.arraycopy(v2Data, 0, data, v1Data.length, v2Data.length);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector(FieldVector, FieldElement[])}
     */
    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> v1, T[] v2) throws NullArgumentException {
        this((FieldVector<T>) v1, v2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @since 3.2
     */
    public ArrayFieldVector(FieldVector<T> v1, T[] v2) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_327");
        MathUtils.checkNotNull(v1);
        MathUtils.checkNotNull(v2);
        field = v1.getField();
        final T[] v1Data = (v1 instanceof ArrayFieldVector) ? ((ArrayFieldVector<T>) v1).data : v1.toArray();
        data = MathArrays.buildArray(field, AOR_plus(v1Data.length, v2.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_327", _mut32867, _mut32868, _mut32869, _mut32870));
        System.arraycopy(v1Data, 0, data, 0, v1Data.length);
        System.arraycopy(v2, 0, data, v1Data.length, v2.length);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @deprecated as of 3.2, replaced by {@link #ArrayFieldVector(FieldElement[], FieldVector)}
     */
    @Deprecated
    public ArrayFieldVector(T[] v1, ArrayFieldVector<T> v2) throws NullArgumentException {
        this(v1, (FieldVector<T>) v2);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @since 3.2
     */
    public ArrayFieldVector(T[] v1, FieldVector<T> v2) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_363");
        MathUtils.checkNotNull(v1);
        MathUtils.checkNotNull(v2);
        field = v2.getField();
        final T[] v2Data = (v2 instanceof ArrayFieldVector) ? ((ArrayFieldVector<T>) v2).data : v2.toArray();
        data = MathArrays.buildArray(field, AOR_plus(v1.length, v2Data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_363", _mut32871, _mut32872, _mut32873, _mut32874));
        System.arraycopy(v1, 0, data, 0, v1.length);
        System.arraycopy(v2Data, 0, data, v1.length, v2Data.length);
    }

    /**
     * Construct a vector by appending one vector to another vector.
     * This constructor needs at least one non-empty array to retrieve
     * the field from its first element. This implies it cannot build
     * 0 length vectors. To build vectors from any size, one should
     * use the {@link #ArrayFieldVector(Field, FieldElement[], FieldElement[])}
     * constructor.
     *
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @throws ZeroException if both arrays are empty.
     * @see #ArrayFieldVector(Field, FieldElement[], FieldElement[])
     */
    public ArrayFieldVector(T[] v1, T[] v2) throws NullArgumentException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_390");
        MathUtils.checkNotNull(v1);
        MathUtils.checkNotNull(v2);
        if (ROR_equals(AOR_plus(v1.length, v2.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_390", _mut32875, _mut32876, _mut32877, _mut32878), 0, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_390", _mut32879, _mut32880, _mut32881, _mut32882, _mut32883)) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
        }
        data = MathArrays.buildArray(v1[0].getField(), AOR_plus(v1.length, v2.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_390", _mut32884, _mut32885, _mut32886, _mut32887));
        System.arraycopy(v1, 0, data, 0, v1.length);
        System.arraycopy(v2, 0, data, v1.length, v2.length);
        field = data[0].getField();
    }

    /**
     * Construct a vector by appending one vector to another vector.
     *
     * @param field Field to which the elements belong.
     * @param v1 First vector (will be put in front of the new vector).
     * @param v2 Second vector (will be put at back of the new vector).
     * @throws NullArgumentException if {@code v1} or {@code v2} is
     * {@code null}.
     * @throws ZeroException if both arrays are empty.
     * @see #ArrayFieldVector(FieldElement[], FieldElement[])
     */
    public ArrayFieldVector(Field<T> field, T[] v1, T[] v2) throws NullArgumentException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_414");
        MathUtils.checkNotNull(v1);
        MathUtils.checkNotNull(v2);
        if (ROR_equals(AOR_plus(v1.length, v2.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_414", _mut32888, _mut32889, _mut32890, _mut32891), 0, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_414", _mut32892, _mut32893, _mut32894, _mut32895, _mut32896)) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT);
        }
        data = MathArrays.buildArray(field, AOR_plus(v1.length, v2.length, "org.apache.commons.math3.linear.ArrayFieldVector.ArrayFieldVector_414", _mut32897, _mut32898, _mut32899, _mut32900));
        System.arraycopy(v1, 0, data, 0, v1.length);
        System.arraycopy(v2, 0, data, v1.length, v2.length);
        this.field = field;
    }

    /**
     * {@inheritDoc}
     */
    public Field<T> getField() {
        return field;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> copy() {
        return new ArrayFieldVector<T>(this, true);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> add(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.add_438");
        try {
            return add((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            checkVectorDimensions(v);
            T[] out = MathArrays.buildArray(field, data.length);
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.add_438", _mut32901, _mut32902, _mut32903, _mut32904, _mut32905); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.add_438");
                out[i] = data[i].add(v.getEntry(i));
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * Compute the sum of {@code this} and {@code v}.
     * @param v vector to be added
     * @return {@code this + v}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     */
    public ArrayFieldVector<T> add(ArrayFieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.add_459");
        checkVectorDimensions(v.data.length);
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.add_459", _mut32906, _mut32907, _mut32908, _mut32909, _mut32910); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.add_459");
            out[i] = data[i].add(v.data[i]);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> subtract(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.subtract_470");
        try {
            return subtract((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            checkVectorDimensions(v);
            T[] out = MathArrays.buildArray(field, data.length);
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.subtract_470", _mut32911, _mut32912, _mut32913, _mut32914, _mut32915); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.subtract_470");
                out[i] = data[i].subtract(v.getEntry(i));
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * Compute {@code this} minus {@code v}.
     * @param v vector to be subtracted
     * @return {@code this - v}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     */
    public ArrayFieldVector<T> subtract(ArrayFieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.subtract_491");
        checkVectorDimensions(v.data.length);
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.subtract_491", _mut32916, _mut32917, _mut32918, _mut32919, _mut32920); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.subtract_491");
            out[i] = data[i].subtract(v.data[i]);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapAdd(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapAdd_502");
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapAdd_502", _mut32921, _mut32922, _mut32923, _mut32924, _mut32925); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapAdd_502");
            out[i] = data[i].add(d);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapAddToSelf(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapAddToSelf_511");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapAddToSelf_511", _mut32926, _mut32927, _mut32928, _mut32929, _mut32930); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapAddToSelf_511");
            data[i] = data[i].add(d);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapSubtract(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapSubtract_519");
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapSubtract_519", _mut32931, _mut32932, _mut32933, _mut32934, _mut32935); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapSubtract_519");
            out[i] = data[i].subtract(d);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapSubtractToSelf(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapSubtractToSelf_528");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapSubtractToSelf_528", _mut32936, _mut32937, _mut32938, _mut32939, _mut32940); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapSubtractToSelf_528");
            data[i] = data[i].subtract(d);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapMultiply(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapMultiply_536");
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapMultiply_536", _mut32941, _mut32942, _mut32943, _mut32944, _mut32945); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapMultiply_536");
            out[i] = data[i].multiply(d);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapMultiplyToSelf(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapMultiplyToSelf_545");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapMultiplyToSelf_545", _mut32946, _mut32947, _mut32948, _mut32949, _mut32950); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapMultiplyToSelf_545");
            data[i] = data[i].multiply(d);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapDivide(T d) throws NullArgumentException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapDivide_553");
        MathUtils.checkNotNull(d);
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapDivide_553", _mut32951, _mut32952, _mut32953, _mut32954, _mut32955); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapDivide_553");
            out[i] = data[i].divide(d);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapDivideToSelf(T d) throws NullArgumentException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapDivideToSelf_564");
        MathUtils.checkNotNull(d);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapDivideToSelf_564", _mut32956, _mut32957, _mut32958, _mut32959, _mut32960); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapDivideToSelf_564");
            data[i] = data[i].divide(d);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapInv() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapInv_574");
        T[] out = MathArrays.buildArray(field, data.length);
        final T one = field.getOne();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapInv_574", _mut32961, _mut32962, _mut32963, _mut32964, _mut32965); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapInv_574");
            try {
                out[i] = one.divide(data[i]);
            } catch (final MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, i);
            }
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapInvToSelf_588");
        final T one = field.getOne();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.mapInvToSelf_588", _mut32966, _mut32967, _mut32968, _mut32969, _mut32970); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.mapInvToSelf_588");
            try {
                data[i] = one.divide(data[i]);
            } catch (final MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, i);
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> ebeMultiply(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_601");
        try {
            return ebeMultiply((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            checkVectorDimensions(v);
            T[] out = MathArrays.buildArray(field, data.length);
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_601", _mut32971, _mut32972, _mut32973, _mut32974, _mut32975); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_601");
                out[i] = data[i].multiply(v.getEntry(i));
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * Element-by-element multiplication.
     * @param v vector by which instance elements must be multiplied
     * @return a vector containing {@code this[i] * v[i]} for all {@code i}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     */
    public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_622");
        checkVectorDimensions(v.data.length);
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_622", _mut32976, _mut32977, _mut32978, _mut32979, _mut32980); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeMultiply_622");
            out[i] = data[i].multiply(v.data[i]);
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> ebeDivide(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_633");
        try {
            return ebeDivide((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            checkVectorDimensions(v);
            T[] out = MathArrays.buildArray(field, data.length);
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_633", _mut32981, _mut32982, _mut32983, _mut32984, _mut32985); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_633");
                try {
                    out[i] = data[i].divide(v.getEntry(i));
                } catch (final MathArithmeticException e) {
                    throw new MathArithmeticException(LocalizedFormats.INDEX, i);
                }
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * Element-by-element division.
     * @param v vector by which instance elements must be divided
     * @return a vector containing {@code this[i] / v[i]} for all {@code i}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     * @throws MathArithmeticException if one entry of {@code v} is zero.
     */
    public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_659");
        checkVectorDimensions(v.data.length);
        T[] out = MathArrays.buildArray(field, data.length);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_659", _mut32986, _mut32987, _mut32988, _mut32989, _mut32990); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.ebeDivide_659");
            try {
                out[i] = data[i].divide(v.data[i]);
            } catch (final MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, i);
            }
        }
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public T[] getData() {
        return data.clone();
    }

    /**
     * Returns a reference to the underlying data array.
     * <p>Does not make a fresh copy of the underlying data.</p>
     * @return array of entries
     */
    public T[] getDataRef() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    public T dotProduct(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_688");
        try {
            return dotProduct((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            checkVectorDimensions(v);
            T dot = field.getZero();
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_688", _mut32991, _mut32992, _mut32993, _mut32994, _mut32995); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_688");
                dot = dot.add(data[i].multiply(v.getEntry(i)));
            }
            return dot;
        }
    }

    /**
     * Compute the dot product.
     * @param v vector with which dot product should be computed
     * @return the scalar dot product of {@code this} and {@code v}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     */
    public T dotProduct(ArrayFieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_709");
        checkVectorDimensions(v.data.length);
        T dot = field.getZero();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_709", _mut32996, _mut32997, _mut32998, _mut32999, _mut33000); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.dotProduct_709");
            dot = dot.add(data[i].multiply(v.data[i]));
        }
        return dot;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> projection(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        return v.mapMultiply(dotProduct(v).divide(v.dotProduct(v)));
    }

    /**
     * Find the orthogonal projection of this vector onto another vector.
     * @param v vector onto which {@code this} must be projected
     * @return projection of {@code this} onto {@code v}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}
     * @throws MathArithmeticException if {@code v} is the null vector.
     */
    public ArrayFieldVector<T> projection(ArrayFieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        return (ArrayFieldVector<T>) v.mapMultiply(dotProduct(v).divide(v.dotProduct(v)));
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> outerProduct(FieldVector<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_738");
        try {
            return outerProduct((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            final int m = data.length;
            final int n = v.getDimension();
            final FieldMatrix<T> out = new Array2DRowFieldMatrix<T>(field, m, n);
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_738", _mut33006, _mut33007, _mut33008, _mut33009, _mut33010); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_738");
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_738", _mut33001, _mut33002, _mut33003, _mut33004, _mut33005); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_738");
                    out.setEntry(i, j, data[i].multiply(v.getEntry(j)));
                }
            }
            return out;
        }
    }

    /**
     * Compute the outer product.
     * @param v vector with which outer product should be computed
     * @return the matrix outer product between instance and v
     */
    public FieldMatrix<T> outerProduct(ArrayFieldVector<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_759");
        final int m = data.length;
        final int n = v.data.length;
        final FieldMatrix<T> out = new Array2DRowFieldMatrix<T>(field, m, n);
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_759", _mut33016, _mut33017, _mut33018, _mut33019, _mut33020); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_759");
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_759", _mut33011, _mut33012, _mut33013, _mut33014, _mut33015); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.outerProduct_759");
                out.setEntry(i, j, data[i].multiply(v.data[j]));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public T getEntry(int index) {
        return data[index];
    }

    /**
     * {@inheritDoc}
     */
    public int getDimension() {
        return data.length;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> append(FieldVector<T> v) {
        try {
            return append((ArrayFieldVector<T>) v);
        } catch (ClassCastException cce) {
            return new ArrayFieldVector<T>(this, new ArrayFieldVector<T>(v));
        }
    }

    /**
     * Construct a vector by appending a vector to this vector.
     * @param v vector to append to this one.
     * @return a new vector
     */
    public ArrayFieldVector<T> append(ArrayFieldVector<T> v) {
        return new ArrayFieldVector<T>(this, v);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> append(T in) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.append_800");
        final T[] out = MathArrays.buildArray(field, AOR_plus(data.length, 1, "org.apache.commons.math3.linear.ArrayFieldVector.append_800", _mut33021, _mut33022, _mut33023, _mut33024));
        System.arraycopy(data, 0, out, 0, data.length);
        out[data.length] = in;
        return new ArrayFieldVector<T>(field, out, false);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.getSubVector_808");
        if (ROR_less(n, 0, "org.apache.commons.math3.linear.ArrayFieldVector.getSubVector_808", _mut33025, _mut33026, _mut33027, _mut33028, _mut33029)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        ArrayFieldVector<T> out = new ArrayFieldVector<T>(field, n);
        try {
            System.arraycopy(data, index, out.data, 0, n);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(AOR_minus(AOR_plus(index, n, "org.apache.commons.math3.linear.ArrayFieldVector.getSubVector_808", _mut33030, _mut33031, _mut33032, _mut33033), 1, "org.apache.commons.math3.linear.ArrayFieldVector.getSubVector_808", _mut33034, _mut33035, _mut33036, _mut33037));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setEntry(int index, T value) {
        try {
            data[index] = value;
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setSubVector(int index, FieldVector<T> v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833");
        try {
            try {
                set(index, (ArrayFieldVector<T>) v);
            } catch (ClassCastException cce) {
                for (int i = index; ROR_less(i, AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833", _mut33050, _mut33051, _mut33052, _mut33053), "org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833", _mut33054, _mut33055, _mut33056, _mut33057, _mut33058); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833");
                    data[i] = v.getEntry(AOR_minus(i, index, "org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833", _mut33046, _mut33047, _mut33048, _mut33049));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(AOR_minus(AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833", _mut33038, _mut33039, _mut33040, _mut33041), 1, "org.apache.commons.math3.linear.ArrayFieldVector.setSubVector_833", _mut33042, _mut33043, _mut33044, _mut33045));
        }
    }

    /**
     * Set a set of consecutive elements.
     *
     * @param index index of first element to be set.
     * @param v vector containing the values to set.
     * @throws OutOfRangeException if the index is invalid.
     */
    public void set(int index, ArrayFieldVector<T> v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.set_855");
        try {
            System.arraycopy(v.data, 0, data, index, v.data.length);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex(AOR_minus(AOR_plus(index, v.data.length, "org.apache.commons.math3.linear.ArrayFieldVector.set_855", _mut33059, _mut33060, _mut33061, _mut33062), 1, "org.apache.commons.math3.linear.ArrayFieldVector.set_855", _mut33063, _mut33064, _mut33065, _mut33066));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void set(T value) {
        Arrays.fill(data, value);
    }

    /**
     * {@inheritDoc}
     */
    public T[] toArray() {
        return data.clone();
    }

    /**
     * Check if instance and specified vectors have the same dimension.
     * @param v vector to compare instance with
     * @exception DimensionMismatchException if the vectors do not
     * have the same dimensions
     */
    protected void checkVectorDimensions(FieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n Expected dimension.
     * @throws DimensionMismatchException if the dimension is not equal to the
     * size of {@code this} vector.
     */
    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.checkVectorDimensions_892");
        if (ROR_not_equals(data.length, n, "org.apache.commons.math3.linear.ArrayFieldVector.checkVectorDimensions_892", _mut33067, _mut33068, _mut33069, _mut33070, _mut33071)) {
            throw new DimensionMismatchException(data.length, n);
        }
    }

    /**
     * Visits (but does not alter) all entries of this vector in default order
     * (increasing index).
     *
     * @param visitor the visitor to be used to process the entries of this
     * vector
     * @return the value returned by {@link FieldVectorPreservingVisitor#end()}
     * at the end of the walk
     * @since 3.3
     */
    public T walkInDefaultOrder(final FieldVectorPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_909");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_909", _mut33072, _mut33073, _mut33074, _mut33075));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_909", _mut33076, _mut33077, _mut33078, _mut33079, _mut33080); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_909");
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    /**
     * Visits (but does not alter) some entries of this vector in default order
     * (increasing index).
     *
     * @param visitor visitor to be used to process the entries of this vector
     * @param start the index of the first entry to be visited
     * @param end the index of the last entry to be visited (inclusive)
     * @return the value returned by {@link FieldVectorPreservingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.3
     */
    public T walkInDefaultOrder(final FieldVectorPreservingVisitor<T> visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_931");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_931", _mut33081, _mut33082, _mut33083, _mut33084, _mut33085); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_931");
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    /**
     * Visits (but does not alter) all entries of this vector in optimized
     * order. The order in which the entries are visited is selected so as to
     * lead to the most efficient implementation; it might depend on the
     * concrete implementation of this abstract class.
     *
     * @param visitor the visitor to be used to process the entries of this
     * vector
     * @return the value returned by {@link FieldVectorPreservingVisitor#end()}
     * at the end of the walk
     * @since 3.3
     */
    public T walkInOptimizedOrder(final FieldVectorPreservingVisitor<T> visitor) {
        return walkInDefaultOrder(visitor);
    }

    /**
     * Visits (but does not alter) some entries of this vector in optimized
     * order. The order in which the entries are visited is selected so as to
     * lead to the most efficient implementation; it might depend on the
     * concrete implementation of this abstract class.
     *
     * @param visitor visitor to be used to process the entries of this vector
     * @param start the index of the first entry to be visited
     * @param end the index of the last entry to be visited (inclusive)
     * @return the value returned by {@link FieldVectorPreservingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.3
     */
    public T walkInOptimizedOrder(final FieldVectorPreservingVisitor<T> visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /**
     * Visits (and possibly alters) all entries of this vector in default order
     * (increasing index).
     *
     * @param visitor the visitor to be used to process and modify the entries
     * of this vector
     * @return the value returned by {@link FieldVectorChangingVisitor#end()}
     * at the end of the walk
     * @since 3.3
     */
    public T walkInDefaultOrder(final FieldVectorChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_989");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_989", _mut33086, _mut33087, _mut33088, _mut33089));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_989", _mut33090, _mut33091, _mut33092, _mut33093, _mut33094); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_989");
            setEntry(i, visitor.visit(i, getEntry(i)));
        }
        return visitor.end();
    }

    /**
     * Visits (and possibly alters) some entries of this vector in default order
     * (increasing index).
     *
     * @param visitor visitor to be used to process the entries of this vector
     * @param start the index of the first entry to be visited
     * @param end the index of the last entry to be visited (inclusive)
     * @return the value returned by {@link FieldVectorChangingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.3
     */
    public T walkInDefaultOrder(final FieldVectorChangingVisitor<T> visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_1011");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_1011", _mut33095, _mut33096, _mut33097, _mut33098, _mut33099); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.walkInDefaultOrder_1011");
            setEntry(i, visitor.visit(i, getEntry(i)));
        }
        return visitor.end();
    }

    /**
     * Visits (and possibly alters) all entries of this vector in optimized
     * order. The order in which the entries are visited is selected so as to
     * lead to the most efficient implementation; it might depend on the
     * concrete implementation of this abstract class.
     *
     * @param visitor the visitor to be used to process the entries of this
     * vector
     * @return the value returned by {@link FieldVectorChangingVisitor#end()}
     * at the end of the walk
     * @since 3.3
     */
    public T walkInOptimizedOrder(final FieldVectorChangingVisitor<T> visitor) {
        return walkInDefaultOrder(visitor);
    }

    /**
     * Visits (and possibly change) some entries of this vector in optimized
     * order. The order in which the entries are visited is selected so as to
     * lead to the most efficient implementation; it might depend on the
     * concrete implementation of this abstract class.
     *
     * @param visitor visitor to be used to process the entries of this vector
     * @param start the index of the first entry to be visited
     * @param end the index of the last entry to be visited (inclusive)
     * @return the value returned by {@link FieldVectorChangingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.3
     */
    public T walkInOptimizedOrder(final FieldVectorChangingVisitor<T> visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /**
     * Test for the equality of two vectors.
     *
     * @param other Object to test for equality.
     * @return {@code true} if two vector objects are equal, {@code false}
     * otherwise.
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.equals_1066");
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        try {
            // May fail, but we ignore ClassCastException
            @SuppressWarnings("unchecked")
            FieldVector<T> rhs = (FieldVector<T>) other;
            if (ROR_not_equals(data.length, rhs.getDimension(), "org.apache.commons.math3.linear.ArrayFieldVector.equals_1066", _mut33100, _mut33101, _mut33102, _mut33103, _mut33104)) {
                return false;
            }
            for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.ArrayFieldVector.equals_1066", _mut33105, _mut33106, _mut33107, _mut33108, _mut33109); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.equals_1066");
                if (!data[i].equals(rhs.getEntry(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException ex) {
            // ignore exception
            return false;
        }
    }

    /**
     * Get a hashCode for the real vector.
     * <p>All NaN values have the same hash code.</p>
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int h = 3542;
        for (final T a : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.hashCode_1099");
            h ^= a.hashCode();
        }
        return h;
    }

    /**
     * Check if an index is valid.
     *
     * @param index Index to check.
     * @exception OutOfRangeException if the index is not valid.
     */
    private void checkIndex(final int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114");
        if ((_mut33120 ? (ROR_less(index, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114", _mut33110, _mut33111, _mut33112, _mut33113, _mut33114) && ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114", _mut33115, _mut33116, _mut33117, _mut33118, _mut33119)) : (ROR_less(index, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114", _mut33110, _mut33111, _mut33112, _mut33113, _mut33114) || ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114", _mut33115, _mut33116, _mut33117, _mut33118, _mut33119)))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, AOR_minus(getDimension(), 1, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndex_1114", _mut33121, _mut33122, _mut33123, _mut33124));
        }
    }

    /**
     * Checks that the indices of a subvector are valid.
     *
     * @param start the index of the first entry of the subvector
     * @param end the index of the last entry of the subvector (inclusive)
     * @throws OutOfRangeException if {@code start} of {@code end} are not valid
     * @throws NumberIsTooSmallException if {@code end < start}
     * @since 3.3
     */
    private void checkIndices(final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130");
        final int dim = getDimension();
        if ((_mut33135 ? ((ROR_less(start, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33125, _mut33126, _mut33127, _mut33128, _mut33129)) && (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33130, _mut33131, _mut33132, _mut33133, _mut33134))) : ((ROR_less(start, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33125, _mut33126, _mut33127, _mut33128, _mut33129)) || (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33130, _mut33131, _mut33132, _mut33133, _mut33134))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, start, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33136, _mut33137, _mut33138, _mut33139));
        }
        if ((_mut33150 ? ((ROR_less(end, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33140, _mut33141, _mut33142, _mut33143, _mut33144)) && (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33145, _mut33146, _mut33147, _mut33148, _mut33149))) : ((ROR_less(end, 0, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33140, _mut33141, _mut33142, _mut33143, _mut33144)) || (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33145, _mut33146, _mut33147, _mut33148, _mut33149))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, end, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33151, _mut33152, _mut33153, _mut33154));
        }
        if (ROR_less(end, start, "org.apache.commons.math3.linear.ArrayFieldVector.checkIndices_1130", _mut33155, _mut33156, _mut33157, _mut33158, _mut33159)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, end, start, false);
        }
    }
}
