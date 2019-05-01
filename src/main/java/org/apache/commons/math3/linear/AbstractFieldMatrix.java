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

import java.util.ArrayList;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Basic implementation of {@link FieldMatrix} methods regardless of the underlying storage.
 * <p>All the methods implemented here use {@link #getEntry(int, int)} to access
 * matrix elements. Derived class can provide faster implementations. </p>
 *
 * @param <T> Type of the field elements.
 *
 * @since 2.0
 */
public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {

    @Conditional
    public static boolean _mut33792 = false, _mut33793 = false, _mut33794 = false, _mut33795 = false, _mut33796 = false, _mut33797 = false, _mut33798 = false, _mut33799 = false, _mut33800 = false, _mut33801 = false, _mut33802 = false, _mut33803 = false, _mut33804 = false, _mut33805 = false, _mut33806 = false, _mut33807 = false, _mut33808 = false, _mut33809 = false, _mut33810 = false, _mut33811 = false, _mut33812 = false, _mut33813 = false, _mut33814 = false, _mut33815 = false, _mut33816 = false, _mut33817 = false, _mut33818 = false, _mut33819 = false, _mut33820 = false, _mut33821 = false, _mut33822 = false, _mut33823 = false, _mut33824 = false, _mut33825 = false, _mut33826 = false, _mut33827 = false, _mut33828 = false, _mut33829 = false, _mut33830 = false, _mut33831 = false, _mut33832 = false, _mut33833 = false, _mut33834 = false, _mut33835 = false, _mut33836 = false, _mut33837 = false, _mut33838 = false, _mut33839 = false, _mut33840 = false, _mut33841 = false, _mut33842 = false, _mut33843 = false, _mut33844 = false, _mut33845 = false, _mut33846 = false, _mut33847 = false, _mut33848 = false, _mut33849 = false, _mut33850 = false, _mut33851 = false, _mut33852 = false, _mut33853 = false, _mut33854 = false, _mut33855 = false, _mut33856 = false, _mut33857 = false, _mut33858 = false, _mut33859 = false, _mut33860 = false, _mut33861 = false, _mut33862 = false, _mut33863 = false, _mut33864 = false, _mut33865 = false, _mut33866 = false, _mut33867 = false, _mut33868 = false, _mut33869 = false, _mut33870 = false, _mut33871 = false, _mut33872 = false, _mut33873 = false, _mut33874 = false, _mut33875 = false, _mut33876 = false, _mut33877 = false, _mut33878 = false, _mut33879 = false, _mut33880 = false, _mut33881 = false, _mut33882 = false, _mut33883 = false, _mut33884 = false, _mut33885 = false, _mut33886 = false, _mut33887 = false, _mut33888 = false, _mut33889 = false, _mut33890 = false, _mut33891 = false, _mut33892 = false, _mut33893 = false, _mut33894 = false, _mut33895 = false, _mut33896 = false, _mut33897 = false, _mut33898 = false, _mut33899 = false, _mut33900 = false, _mut33901 = false, _mut33902 = false, _mut33903 = false, _mut33904 = false, _mut33905 = false, _mut33906 = false, _mut33907 = false, _mut33908 = false, _mut33909 = false, _mut33910 = false, _mut33911 = false, _mut33912 = false, _mut33913 = false, _mut33914 = false, _mut33915 = false, _mut33916 = false, _mut33917 = false, _mut33918 = false, _mut33919 = false, _mut33920 = false, _mut33921 = false, _mut33922 = false, _mut33923 = false, _mut33924 = false, _mut33925 = false, _mut33926 = false, _mut33927 = false, _mut33928 = false, _mut33929 = false, _mut33930 = false, _mut33931 = false, _mut33932 = false, _mut33933 = false, _mut33934 = false, _mut33935 = false, _mut33936 = false, _mut33937 = false, _mut33938 = false, _mut33939 = false, _mut33940 = false, _mut33941 = false, _mut33942 = false, _mut33943 = false, _mut33944 = false, _mut33945 = false, _mut33946 = false, _mut33947 = false, _mut33948 = false, _mut33949 = false, _mut33950 = false, _mut33951 = false, _mut33952 = false, _mut33953 = false, _mut33954 = false, _mut33955 = false, _mut33956 = false, _mut33957 = false, _mut33958 = false, _mut33959 = false, _mut33960 = false, _mut33961 = false, _mut33962 = false, _mut33963 = false, _mut33964 = false, _mut33965 = false, _mut33966 = false, _mut33967 = false, _mut33968 = false, _mut33969 = false, _mut33970 = false, _mut33971 = false, _mut33972 = false, _mut33973 = false, _mut33974 = false, _mut33975 = false, _mut33976 = false, _mut33977 = false, _mut33978 = false, _mut33979 = false, _mut33980 = false, _mut33981 = false, _mut33982 = false, _mut33983 = false, _mut33984 = false, _mut33985 = false, _mut33986 = false, _mut33987 = false, _mut33988 = false, _mut33989 = false, _mut33990 = false, _mut33991 = false, _mut33992 = false, _mut33993 = false, _mut33994 = false, _mut33995 = false, _mut33996 = false, _mut33997 = false, _mut33998 = false, _mut33999 = false, _mut34000 = false, _mut34001 = false, _mut34002 = false, _mut34003 = false, _mut34004 = false, _mut34005 = false, _mut34006 = false, _mut34007 = false, _mut34008 = false, _mut34009 = false, _mut34010 = false, _mut34011 = false, _mut34012 = false, _mut34013 = false, _mut34014 = false, _mut34015 = false, _mut34016 = false, _mut34017 = false, _mut34018 = false, _mut34019 = false, _mut34020 = false, _mut34021 = false, _mut34022 = false, _mut34023 = false, _mut34024 = false, _mut34025 = false, _mut34026 = false, _mut34027 = false, _mut34028 = false, _mut34029 = false, _mut34030 = false, _mut34031 = false, _mut34032 = false, _mut34033 = false, _mut34034 = false, _mut34035 = false, _mut34036 = false, _mut34037 = false, _mut34038 = false, _mut34039 = false, _mut34040 = false, _mut34041 = false, _mut34042 = false, _mut34043 = false, _mut34044 = false, _mut34045 = false, _mut34046 = false, _mut34047 = false, _mut34048 = false, _mut34049 = false, _mut34050 = false, _mut34051 = false, _mut34052 = false, _mut34053 = false, _mut34054 = false, _mut34055 = false, _mut34056 = false, _mut34057 = false, _mut34058 = false, _mut34059 = false, _mut34060 = false, _mut34061 = false, _mut34062 = false, _mut34063 = false, _mut34064 = false, _mut34065 = false, _mut34066 = false, _mut34067 = false, _mut34068 = false, _mut34069 = false, _mut34070 = false, _mut34071 = false, _mut34072 = false, _mut34073 = false, _mut34074 = false, _mut34075 = false, _mut34076 = false, _mut34077 = false, _mut34078 = false, _mut34079 = false, _mut34080 = false, _mut34081 = false, _mut34082 = false, _mut34083 = false, _mut34084 = false, _mut34085 = false, _mut34086 = false, _mut34087 = false, _mut34088 = false, _mut34089 = false, _mut34090 = false, _mut34091 = false, _mut34092 = false, _mut34093 = false, _mut34094 = false, _mut34095 = false, _mut34096 = false, _mut34097 = false, _mut34098 = false, _mut34099 = false, _mut34100 = false, _mut34101 = false, _mut34102 = false, _mut34103 = false, _mut34104 = false, _mut34105 = false, _mut34106 = false, _mut34107 = false, _mut34108 = false, _mut34109 = false, _mut34110 = false, _mut34111 = false, _mut34112 = false, _mut34113 = false, _mut34114 = false, _mut34115 = false, _mut34116 = false, _mut34117 = false, _mut34118 = false, _mut34119 = false, _mut34120 = false, _mut34121 = false, _mut34122 = false, _mut34123 = false, _mut34124 = false, _mut34125 = false, _mut34126 = false, _mut34127 = false, _mut34128 = false, _mut34129 = false, _mut34130 = false, _mut34131 = false, _mut34132 = false, _mut34133 = false, _mut34134 = false, _mut34135 = false, _mut34136 = false, _mut34137 = false, _mut34138 = false, _mut34139 = false, _mut34140 = false, _mut34141 = false, _mut34142 = false, _mut34143 = false, _mut34144 = false, _mut34145 = false, _mut34146 = false, _mut34147 = false, _mut34148 = false, _mut34149 = false, _mut34150 = false, _mut34151 = false, _mut34152 = false, _mut34153 = false, _mut34154 = false, _mut34155 = false, _mut34156 = false, _mut34157 = false, _mut34158 = false, _mut34159 = false, _mut34160 = false, _mut34161 = false, _mut34162 = false, _mut34163 = false, _mut34164 = false, _mut34165 = false, _mut34166 = false, _mut34167 = false, _mut34168 = false, _mut34169 = false, _mut34170 = false, _mut34171 = false, _mut34172 = false, _mut34173 = false, _mut34174 = false, _mut34175 = false, _mut34176 = false, _mut34177 = false, _mut34178 = false, _mut34179 = false, _mut34180 = false, _mut34181 = false, _mut34182 = false, _mut34183 = false, _mut34184 = false, _mut34185 = false, _mut34186 = false, _mut34187 = false, _mut34188 = false, _mut34189 = false, _mut34190 = false, _mut34191 = false, _mut34192 = false, _mut34193 = false, _mut34194 = false, _mut34195 = false, _mut34196 = false, _mut34197 = false, _mut34198 = false, _mut34199 = false, _mut34200 = false, _mut34201 = false, _mut34202 = false, _mut34203 = false, _mut34204 = false, _mut34205 = false, _mut34206 = false, _mut34207 = false, _mut34208 = false, _mut34209 = false, _mut34210 = false, _mut34211 = false, _mut34212 = false, _mut34213 = false, _mut34214 = false, _mut34215 = false, _mut34216 = false, _mut34217 = false, _mut34218 = false, _mut34219 = false, _mut34220 = false, _mut34221 = false, _mut34222 = false, _mut34223 = false, _mut34224 = false, _mut34225 = false, _mut34226 = false, _mut34227 = false, _mut34228 = false, _mut34229 = false, _mut34230 = false, _mut34231 = false, _mut34232 = false, _mut34233 = false, _mut34234 = false, _mut34235 = false, _mut34236 = false, _mut34237 = false, _mut34238 = false, _mut34239 = false, _mut34240 = false, _mut34241 = false, _mut34242 = false, _mut34243 = false, _mut34244 = false, _mut34245 = false, _mut34246 = false, _mut34247 = false, _mut34248 = false, _mut34249 = false, _mut34250 = false, _mut34251 = false, _mut34252 = false, _mut34253 = false, _mut34254 = false, _mut34255 = false, _mut34256 = false, _mut34257 = false, _mut34258 = false, _mut34259 = false, _mut34260 = false, _mut34261 = false, _mut34262 = false, _mut34263 = false, _mut34264 = false, _mut34265 = false, _mut34266 = false, _mut34267 = false, _mut34268 = false, _mut34269 = false, _mut34270 = false, _mut34271 = false, _mut34272 = false, _mut34273 = false, _mut34274 = false, _mut34275 = false, _mut34276 = false, _mut34277 = false, _mut34278 = false, _mut34279 = false, _mut34280 = false, _mut34281 = false, _mut34282 = false, _mut34283 = false, _mut34284 = false, _mut34285 = false, _mut34286 = false, _mut34287 = false, _mut34288 = false, _mut34289 = false, _mut34290 = false, _mut34291 = false, _mut34292 = false, _mut34293 = false, _mut34294 = false, _mut34295 = false, _mut34296 = false, _mut34297 = false, _mut34298 = false, _mut34299 = false, _mut34300 = false, _mut34301 = false, _mut34302 = false, _mut34303 = false, _mut34304 = false, _mut34305 = false, _mut34306 = false, _mut34307 = false, _mut34308 = false, _mut34309 = false, _mut34310 = false, _mut34311 = false, _mut34312 = false, _mut34313 = false, _mut34314 = false, _mut34315 = false, _mut34316 = false, _mut34317 = false, _mut34318 = false, _mut34319 = false, _mut34320 = false, _mut34321 = false, _mut34322 = false, _mut34323 = false, _mut34324 = false, _mut34325 = false, _mut34326 = false, _mut34327 = false, _mut34328 = false, _mut34329 = false, _mut34330 = false, _mut34331 = false, _mut34332 = false, _mut34333 = false, _mut34334 = false, _mut34335 = false, _mut34336 = false, _mut34337 = false, _mut34338 = false, _mut34339 = false, _mut34340 = false, _mut34341 = false, _mut34342 = false, _mut34343 = false, _mut34344 = false, _mut34345 = false, _mut34346 = false, _mut34347 = false, _mut34348 = false, _mut34349 = false, _mut34350 = false, _mut34351 = false, _mut34352 = false, _mut34353 = false, _mut34354 = false, _mut34355 = false, _mut34356 = false, _mut34357 = false, _mut34358 = false, _mut34359 = false, _mut34360 = false, _mut34361 = false, _mut34362 = false, _mut34363 = false, _mut34364 = false, _mut34365 = false, _mut34366 = false, _mut34367 = false, _mut34368 = false, _mut34369 = false, _mut34370 = false, _mut34371 = false, _mut34372 = false, _mut34373 = false, _mut34374 = false, _mut34375 = false, _mut34376 = false, _mut34377 = false, _mut34378 = false, _mut34379 = false, _mut34380 = false, _mut34381 = false, _mut34382 = false, _mut34383 = false, _mut34384 = false, _mut34385 = false, _mut34386 = false, _mut34387 = false, _mut34388 = false, _mut34389 = false, _mut34390 = false, _mut34391 = false, _mut34392 = false, _mut34393 = false, _mut34394 = false, _mut34395 = false, _mut34396 = false, _mut34397 = false, _mut34398 = false, _mut34399 = false, _mut34400 = false, _mut34401 = false, _mut34402 = false, _mut34403 = false, _mut34404 = false, _mut34405 = false, _mut34406 = false, _mut34407 = false, _mut34408 = false, _mut34409 = false, _mut34410 = false, _mut34411 = false, _mut34412 = false, _mut34413 = false, _mut34414 = false, _mut34415 = false, _mut34416 = false, _mut34417 = false, _mut34418 = false, _mut34419 = false, _mut34420 = false, _mut34421 = false, _mut34422 = false, _mut34423 = false, _mut34424 = false, _mut34425 = false, _mut34426 = false, _mut34427 = false, _mut34428 = false, _mut34429 = false, _mut34430 = false, _mut34431 = false, _mut34432 = false, _mut34433 = false, _mut34434 = false, _mut34435 = false, _mut34436 = false, _mut34437 = false, _mut34438 = false, _mut34439 = false, _mut34440 = false, _mut34441 = false, _mut34442 = false, _mut34443 = false, _mut34444 = false, _mut34445 = false, _mut34446 = false, _mut34447 = false, _mut34448 = false, _mut34449 = false, _mut34450 = false, _mut34451 = false, _mut34452 = false, _mut34453 = false, _mut34454 = false, _mut34455 = false, _mut34456 = false, _mut34457 = false, _mut34458 = false, _mut34459 = false, _mut34460 = false, _mut34461 = false, _mut34462 = false, _mut34463 = false, _mut34464 = false, _mut34465 = false, _mut34466 = false, _mut34467 = false, _mut34468 = false, _mut34469 = false, _mut34470 = false, _mut34471 = false, _mut34472 = false, _mut34473 = false, _mut34474 = false, _mut34475 = false, _mut34476 = false, _mut34477 = false, _mut34478 = false, _mut34479 = false, _mut34480 = false, _mut34481 = false, _mut34482 = false, _mut34483 = false, _mut34484 = false, _mut34485 = false, _mut34486 = false, _mut34487 = false, _mut34488 = false, _mut34489 = false, _mut34490 = false, _mut34491 = false, _mut34492 = false, _mut34493 = false, _mut34494 = false, _mut34495 = false, _mut34496 = false, _mut34497 = false, _mut34498 = false, _mut34499 = false, _mut34500 = false, _mut34501 = false, _mut34502 = false, _mut34503 = false, _mut34504 = false, _mut34505 = false, _mut34506 = false, _mut34507 = false, _mut34508 = false, _mut34509 = false, _mut34510 = false, _mut34511 = false, _mut34512 = false, _mut34513 = false, _mut34514 = false, _mut34515 = false, _mut34516 = false, _mut34517 = false, _mut34518 = false, _mut34519 = false, _mut34520 = false, _mut34521 = false, _mut34522 = false, _mut34523 = false, _mut34524 = false, _mut34525 = false, _mut34526 = false, _mut34527 = false;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Constructor for use with Serializable
     */
    protected AbstractFieldMatrix() {
        field = null;
    }

    /**
     * Creates a matrix with no data
     * @param field field to which the elements belong
     */
    protected AbstractFieldMatrix(final Field<T> field) {
        this.field = field;
    }

    /**
     * Create a new FieldMatrix<T> with the supplied row and column dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rowDimension Number of rows in the new matrix.
     * @param columnDimension Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     */
    protected AbstractFieldMatrix(final Field<T> field, final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.AbstractFieldMatrix_72");
        if (ROR_less_equals(rowDimension, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.AbstractFieldMatrix_72", _mut33792, _mut33793, _mut33794, _mut33795, _mut33796)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, rowDimension);
        }
        if (ROR_less_equals(columnDimension, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.AbstractFieldMatrix_72", _mut33797, _mut33798, _mut33799, _mut33800, _mut33801)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, columnDimension);
        }
        this.field = field;
    }

    /**
     * Get the elements type from an array.
     *
     * @param <T> Type of the field elements.
     * @param d Data array.
     * @return the field to which the array elements belong.
     * @throws NullArgumentException if the array is {@code null}.
     * @throws NoDataException if the array is empty.
     */
    protected static <T extends FieldElement<T>> Field<T> extractField(final T[][] d) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.extractField_96");
        if (d == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(d.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.extractField_96", _mut33802, _mut33803, _mut33804, _mut33805, _mut33806)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        if (ROR_equals(d[0].length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.extractField_96", _mut33807, _mut33808, _mut33809, _mut33810, _mut33811)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        return d[0][0].getField();
    }

    /**
     * Get the elements type from an array.
     *
     * @param <T> Type of the field elements.
     * @param d Data array.
     * @return the field to which the array elements belong.
     * @throws NoDataException if array is empty.
     */
    protected static <T extends FieldElement<T>> Field<T> extractField(final T[] d) throws NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.extractField_118");
        if (ROR_equals(d.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.extractField_118", _mut33812, _mut33813, _mut33814, _mut33815, _mut33816)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        return d[0].getField();
    }

    /**
     * Build an array of elements.
     * <p>
     * Complete arrays are filled with field.getZero()
     * </p>
     * @param <T> Type of the field elements
     * @param field field to which array elements belong
     * @param rows number of rows
     * @param columns number of columns (may be negative to build partial
     * arrays in the same way <code>new Field[rows][]</code> works)
     * @return a new array
     * @deprecated as of 3.2, replaced by {@link MathArrays#buildArray(Field, int, int)}
     */
    @Deprecated
    protected static <T extends FieldElement<T>> T[][] buildArray(final Field<T> field, final int rows, final int columns) {
        return MathArrays.buildArray(field, rows, columns);
    }

    /**
     * Build an array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     * </p>
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param length of the array
     * @return a new array
     * @deprecated as of 3.2, replaced by {@link MathArrays#buildArray(Field, int)}
     */
    @Deprecated
    protected static <T extends FieldElement<T>> T[] buildArray(final Field<T> field, final int length) {
        return MathArrays.buildArray(field, length);
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
    public abstract FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException;

    /**
     * {@inheritDoc}
     */
    public abstract FieldMatrix<T> copy();

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> add(FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.add_175");
        // safety check
        checkAdditionCompatible(m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.add_175", _mut33822, _mut33823, _mut33824, _mut33825, _mut33826); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.add_175");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.add_175", _mut33817, _mut33818, _mut33819, _mut33820, _mut33821); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.add_175");
                out.setEntry(row, col, getEntry(row, col).add(m.getEntry(row, col)));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> subtract(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.subtract_193");
        // safety check
        checkSubtractionCompatible(m);
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.subtract_193", _mut33832, _mut33833, _mut33834, _mut33835, _mut33836); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.subtract_193");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.subtract_193", _mut33827, _mut33828, _mut33829, _mut33830, _mut33831); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.subtract_193");
                out.setEntry(row, col, getEntry(row, col).subtract(m.getEntry(row, col)));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> scalarAdd(final T d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarAdd_211");
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.scalarAdd_211", _mut33842, _mut33843, _mut33844, _mut33845, _mut33846); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarAdd_211");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.scalarAdd_211", _mut33837, _mut33838, _mut33839, _mut33840, _mut33841); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarAdd_211");
                out.setEntry(row, col, getEntry(row, col).add(d));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> scalarMultiply(final T d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarMultiply_226");
        final int rowCount = getRowDimension();
        final int columnCount = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(rowCount, columnCount);
        for (int row = 0; ROR_less(row, rowCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.scalarMultiply_226", _mut33852, _mut33853, _mut33854, _mut33855, _mut33856); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarMultiply_226");
            for (int col = 0; ROR_less(col, columnCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.scalarMultiply_226", _mut33847, _mut33848, _mut33849, _mut33850, _mut33851); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.scalarMultiply_226");
                out.setEntry(row, col, getEntry(row, col).multiply(d));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> multiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240");
        // safety check
        checkMultiplicationCompatible(m);
        final int nRows = getRowDimension();
        final int nCols = m.getColumnDimension();
        final int nSum = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(nRows, nCols);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240", _mut33867, _mut33868, _mut33869, _mut33870, _mut33871); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240", _mut33862, _mut33863, _mut33864, _mut33865, _mut33866); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240");
                T sum = field.getZero();
                for (int i = 0; ROR_less(i, nSum, "org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240", _mut33857, _mut33858, _mut33859, _mut33860, _mut33861); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.multiply_240");
                    sum = sum.add(getEntry(row, i).multiply(m.getEntry(i, col)));
                }
                out.setEntry(row, col, sum);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> preMultiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        return m.multiply(this);
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> power(final int p) throws NonSquareMatrixException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.power_269");
        if (ROR_less(p, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33872, _mut33873, _mut33874, _mut33875, _mut33876)) {
            throw new NotPositiveException(p);
        }
        if (!isSquare()) {
            throw new NonSquareMatrixException(getRowDimension(), getColumnDimension());
        }
        if (ROR_equals(p, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33877, _mut33878, _mut33879, _mut33880, _mut33881)) {
            return MatrixUtils.createFieldIdentityMatrix(this.getField(), this.getRowDimension());
        }
        if (ROR_equals(p, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33882, _mut33883, _mut33884, _mut33885, _mut33886)) {
            return this.copy();
        }
        final int power = AOR_minus(p, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33887, _mut33888, _mut33889, _mut33890);
        final char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
        final ArrayList<Integer> nonZeroPositions = new ArrayList<Integer>();
        for (int i = 0; ROR_less(i, binaryRepresentation.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33899, _mut33900, _mut33901, _mut33902, _mut33903); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.power_269");
            if (binaryRepresentation[i] == '1') {
                final int pos = AOR_minus(AOR_minus(binaryRepresentation.length, i, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33891, _mut33892, _mut33893, _mut33894), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33895, _mut33896, _mut33897, _mut33898);
                nonZeroPositions.add(pos);
            }
        }
        ArrayList<FieldMatrix<T>> results = new ArrayList<FieldMatrix<T>>(binaryRepresentation.length);
        results.add(0, this.copy());
        for (int i = 1; ROR_less(i, binaryRepresentation.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33908, _mut33909, _mut33910, _mut33911, _mut33912); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.power_269");
            final FieldMatrix<T> s = results.get(AOR_minus(i, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.power_269", _mut33904, _mut33905, _mut33906, _mut33907));
            final FieldMatrix<T> r = s.multiply(s);
            results.add(i, r);
        }
        FieldMatrix<T> result = this.copy();
        for (Integer i : nonZeroPositions) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.power_269");
            result = result.multiply(results.get(i));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public T[][] getData() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getData_328");
        final T[][] data = MathArrays.buildArray(field, getRowDimension(), getColumnDimension());
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.getData_328", _mut33918, _mut33919, _mut33920, _mut33921, _mut33922); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getData_328");
            final T[] dataI = data[i];
            for (int j = 0; ROR_less(j, dataI.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.getData_328", _mut33913, _mut33914, _mut33915, _mut33916, _mut33917); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getData_328");
                dataI[j] = getEntry(i, j);
            }
        }
        return data;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        final FieldMatrix<T> subMatrix = createMatrix(AOR_plus(AOR_minus(endRow, startRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33923, _mut33924, _mut33925, _mut33926), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33927, _mut33928, _mut33929, _mut33930), AOR_plus(AOR_minus(endColumn, startColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33931, _mut33932, _mut33933, _mut33934), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33935, _mut33936, _mut33937, _mut33938));
        for (int i = startRow; ROR_less_equals(i, endRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33952, _mut33953, _mut33954, _mut33955, _mut33956); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342");
            for (int j = startColumn; ROR_less_equals(j, endColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33947, _mut33948, _mut33949, _mut33950, _mut33951); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342");
                subMatrix.setEntry(AOR_minus(i, startRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33939, _mut33940, _mut33941, _mut33942), AOR_minus(j, startColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.getSubMatrix_342", _mut33943, _mut33944, _mut33945, _mut33946), getEntry(i, j));
            }
        }
        return subMatrix;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        // safety checks
        checkSubMatrixIndex(selectedRows, selectedColumns);
        // copy entries
        final FieldMatrix<T> subMatrix = createMatrix(selectedRows.length, selectedColumns.length);
        subMatrix.walkInOptimizedOrder(new DefaultFieldMatrixChangingVisitor<T>(field.getZero()) {

            /**
             * {@inheritDoc}
             */
            @Override
            public T visit(final int row, final int column, final T value) {
                return getEntry(selectedRows[row], selectedColumns[column]);
            }
        });
        return subMatrix;
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn, final T[][] destination) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.visit_420");
        // safety checks
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        final int rowsCount = AOR_minus(AOR_plus(endRow, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33957, _mut33958, _mut33959, _mut33960), startRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33961, _mut33962, _mut33963, _mut33964);
        final int columnsCount = AOR_minus(AOR_plus(endColumn, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33965, _mut33966, _mut33967, _mut33968), startColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33969, _mut33970, _mut33971, _mut33972);
        if ((_mut33983 ? ((ROR_less(destination.length, rowsCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33973, _mut33974, _mut33975, _mut33976, _mut33977)) && (ROR_less(destination[0].length, columnsCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33978, _mut33979, _mut33980, _mut33981, _mut33982))) : ((ROR_less(destination.length, rowsCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33973, _mut33974, _mut33975, _mut33976, _mut33977)) || (ROR_less(destination[0].length, columnsCount, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_385", _mut33978, _mut33979, _mut33980, _mut33981, _mut33982))))) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, rowsCount, columnsCount);
        }
        // copy entries
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(field.getZero()) {

            /**
             * Initial row index.
             */
            private int startRow;

            /**
             * Initial column index.
             */
            private int startColumn;

            /**
             * {@inheritDoc}
             */
            @Override
            public void start(final int rows, final int columns, final int startRow, final int endRow, final int startColumn, final int endColumn) {
                this.startRow = startRow;
                this.startColumn = startColumn;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void visit(final int row, final int column, final T value) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.visit_420");
                destination[AOR_minus(row, startRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.visit_420", _mut33988, _mut33989, _mut33990, _mut33991)][AOR_minus(column, startColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.visit_420", _mut33984, _mut33985, _mut33986, _mut33987)] = value;
            }
        }, startRow, endRow, startColumn, endColumn);
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, T[][] destination) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430");
        // safety checks
        checkSubMatrixIndex(selectedRows, selectedColumns);
        if ((_mut34002 ? ((ROR_less(destination.length, selectedRows.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut33992, _mut33993, _mut33994, _mut33995, _mut33996)) && (ROR_less(destination[0].length, selectedColumns.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut33997, _mut33998, _mut33999, _mut34000, _mut34001))) : ((ROR_less(destination.length, selectedRows.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut33992, _mut33993, _mut33994, _mut33995, _mut33996)) || (ROR_less(destination[0].length, selectedColumns.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut33997, _mut33998, _mut33999, _mut34000, _mut34001))))) {
            throw new MatrixDimensionMismatchException(destination.length, destination[0].length, selectedRows.length, selectedColumns.length);
        }
        // copy entries
        for (int i = 0; ROR_less(i, selectedRows.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut34008, _mut34009, _mut34010, _mut34011, _mut34012); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430");
            final T[] destinationI = destination[i];
            for (int j = 0; ROR_less(j, selectedColumns.length, "org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430", _mut34003, _mut34004, _mut34005, _mut34006, _mut34007); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.copySubMatrix_430");
                destinationI[j] = getEntry(selectedRows[i], selectedColumns[j]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454");
        if (subMatrix == null) {
            throw new NullArgumentException();
        }
        final int nRows = subMatrix.length;
        if (ROR_equals(nRows, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34013, _mut34014, _mut34015, _mut34016, _mut34017)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        final int nCols = subMatrix[0].length;
        if (ROR_equals(nCols, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34018, _mut34019, _mut34020, _mut34021, _mut34022)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int r = 1; ROR_less(r, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34028, _mut34029, _mut34030, _mut34031, _mut34032); ++r) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454");
            if (ROR_not_equals(subMatrix[r].length, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34023, _mut34024, _mut34025, _mut34026, _mut34027)) {
                throw new DimensionMismatchException(nCols, subMatrix[r].length);
            }
        }
        checkRowIndex(row);
        checkColumnIndex(column);
        checkRowIndex(AOR_minus(AOR_plus(nRows, row, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34033, _mut34034, _mut34035, _mut34036), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34037, _mut34038, _mut34039, _mut34040));
        checkColumnIndex(AOR_minus(AOR_plus(nCols, column, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34041, _mut34042, _mut34043, _mut34044), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34045, _mut34046, _mut34047, _mut34048));
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34062, _mut34063, _mut34064, _mut34065, _mut34066); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454");
            for (int j = 0; ROR_less(j, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34057, _mut34058, _mut34059, _mut34060, _mut34061); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454");
                setEntry(AOR_plus(row, i, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34049, _mut34050, _mut34051, _mut34052), AOR_plus(column, j, "org.apache.commons.math3.linear.AbstractFieldMatrix.setSubMatrix_454", _mut34053, _mut34054, _mut34055, _mut34056), subMatrix[i][j]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getRowMatrix(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getRowMatrix_490");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(1, nCols);
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.getRowMatrix_490", _mut34067, _mut34068, _mut34069, _mut34070, _mut34071); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getRowMatrix_490");
            out.setEntry(0, i, getEntry(row, i));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowMatrix(final int row, final FieldMatrix<T> matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        if ((_mut34082 ? ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503", _mut34072, _mut34073, _mut34074, _mut34075, _mut34076)) && (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503", _mut34077, _mut34078, _mut34079, _mut34080, _mut34081))) : ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503", _mut34072, _mut34073, _mut34074, _mut34075, _mut34076)) || (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503", _mut34077, _mut34078, _mut34079, _mut34080, _mut34081))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503", _mut34083, _mut34084, _mut34085, _mut34086, _mut34087); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRowMatrix_503");
            setEntry(row, i, matrix.getEntry(0, i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getColumnMatrix(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getColumnMatrix_520");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        final FieldMatrix<T> out = createMatrix(nRows, 1);
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.getColumnMatrix_520", _mut34088, _mut34089, _mut34090, _mut34091, _mut34092); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getColumnMatrix_520");
            out.setEntry(i, 0, getEntry(i, column));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnMatrix(final int column, final FieldMatrix<T> matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        if ((_mut34103 ? ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535", _mut34093, _mut34094, _mut34095, _mut34096, _mut34097)) && (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535", _mut34098, _mut34099, _mut34100, _mut34101, _mut34102))) : ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535", _mut34093, _mut34094, _mut34095, _mut34096, _mut34097)) || (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535", _mut34098, _mut34099, _mut34100, _mut34101, _mut34102))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535", _mut34104, _mut34105, _mut34106, _mut34107, _mut34108); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnMatrix_535");
            setEntry(i, column, matrix.getEntry(i, 0));
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> getRowVector(final int row) throws OutOfRangeException {
        return new ArrayFieldVector<T>(field, getRow(row), false);
    }

    /**
     * {@inheritDoc}
     */
    public void setRowVector(final int row, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRowVector_558");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(vector.getDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowVector_558", _mut34109, _mut34110, _mut34111, _mut34112, _mut34113)) {
            throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRowVector_558", _mut34114, _mut34115, _mut34116, _mut34117, _mut34118); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRowVector_558");
            setEntry(row, i, vector.getEntry(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> getColumnVector(final int column) throws OutOfRangeException {
        return new ArrayFieldVector<T>(field, getColumn(column), false);
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnVector(final int column, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnVector_579");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(vector.getDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnVector_579", _mut34119, _mut34120, _mut34121, _mut34122, _mut34123)) {
            throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnVector_579", _mut34124, _mut34125, _mut34126, _mut34127, _mut34128); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumnVector_579");
            setEntry(i, column, vector.getEntry(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public T[] getRow(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getRow_595");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        final T[] out = MathArrays.buildArray(field, nCols);
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.getRow_595", _mut34129, _mut34130, _mut34131, _mut34132, _mut34133); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getRow_595");
            out[i] = getEntry(row, i);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setRow(final int row, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRow_608");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(array.length, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRow_608", _mut34134, _mut34135, _mut34136, _mut34137, _mut34138)) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.setRow_608", _mut34139, _mut34140, _mut34141, _mut34142, _mut34143); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setRow_608");
            setEntry(row, i, array[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    public T[] getColumn(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getColumn_622");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        final T[] out = MathArrays.buildArray(field, nRows);
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.getColumn_622", _mut34144, _mut34145, _mut34146, _mut34147, _mut34148); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getColumn_622");
            out[i] = getEntry(i, column);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumn(final int column, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumn_635");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(array.length, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumn_635", _mut34149, _mut34150, _mut34151, _mut34152, _mut34153)) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.setColumn_635", _mut34154, _mut34155, _mut34156, _mut34157, _mut34158); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.setColumn_635");
            setEntry(i, column, array[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    public abstract T getEntry(int row, int column) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void setEntry(int row, int column, T value) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void addToEntry(int row, int column, T increment) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void multiplyEntry(int row, int column, T factor) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> transpose() {
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final FieldMatrix<T> out = createMatrix(nCols, nRows);
        walkInOptimizedOrder(new DefaultFieldMatrixPreservingVisitor<T>(field.getZero()) {

            /**
             * {@inheritDoc}
             */
            @Override
            public void visit(final int row, final int column, final T value) {
                out.setEntry(column, row, value);
            }
        });
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSquare() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.isSquare_676");
        return ROR_equals(getColumnDimension(), getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.isSquare_676", _mut34159, _mut34160, _mut34161, _mut34162, _mut34163);
    }

    /**
     * {@inheritDoc}
     */
    public abstract int getRowDimension();

    /**
     * {@inheritDoc}
     */
    public abstract int getColumnDimension();

    /**
     * {@inheritDoc}
     */
    public T getTrace() throws NonSquareMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getTrace_687");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(nRows, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.getTrace_687", _mut34164, _mut34165, _mut34166, _mut34167, _mut34168)) {
            throw new NonSquareMatrixException(nRows, nCols);
        }
        T trace = field.getZero();
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.getTrace_687", _mut34169, _mut34170, _mut34171, _mut34172, _mut34173); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.getTrace_687");
            trace = trace.add(getEntry(i, i));
        }
        return trace;
    }

    /**
     * {@inheritDoc}
     */
    public T[] operate(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701", _mut34174, _mut34175, _mut34176, _mut34177, _mut34178)) {
            throw new DimensionMismatchException(v.length, nCols);
        }
        final T[] out = MathArrays.buildArray(field, nRows);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701", _mut34184, _mut34185, _mut34186, _mut34187, _mut34188); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701");
            T sum = field.getZero();
            for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701", _mut34179, _mut34180, _mut34181, _mut34182, _mut34183); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_701");
                sum = sum.add(getEntry(row, i).multiply(v[i]));
            }
            out[row] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> operate(final FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722");
        try {
            return new ArrayFieldVector<T>(field, operate(((ArrayFieldVector<T>) v).getDataRef()), false);
        } catch (ClassCastException cce) {
            final int nRows = getRowDimension();
            final int nCols = getColumnDimension();
            if (ROR_not_equals(v.getDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722", _mut34189, _mut34190, _mut34191, _mut34192, _mut34193)) {
                throw new DimensionMismatchException(v.getDimension(), nCols);
            }
            final T[] out = MathArrays.buildArray(field, nRows);
            for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722", _mut34199, _mut34200, _mut34201, _mut34202, _mut34203); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722");
                T sum = field.getZero();
                for (int i = 0; ROR_less(i, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722", _mut34194, _mut34195, _mut34196, _mut34197, _mut34198); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.operate_722");
                    sum = sum.add(getEntry(row, i).multiply(v.getEntry(i)));
                }
                out[row] = sum;
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public T[] preMultiply(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if (ROR_not_equals(v.length, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747", _mut34204, _mut34205, _mut34206, _mut34207, _mut34208)) {
            throw new DimensionMismatchException(v.length, nRows);
        }
        final T[] out = MathArrays.buildArray(field, nCols);
        for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747", _mut34214, _mut34215, _mut34216, _mut34217, _mut34218); ++col) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747");
            T sum = field.getZero();
            for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747", _mut34209, _mut34210, _mut34211, _mut34212, _mut34213); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_747");
                sum = sum.add(getEntry(i, col).multiply(v[i]));
            }
            out[col] = sum;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> preMultiply(final FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768");
        try {
            return new ArrayFieldVector<T>(field, preMultiply(((ArrayFieldVector<T>) v).getDataRef()), false);
        } catch (ClassCastException cce) {
            final int nRows = getRowDimension();
            final int nCols = getColumnDimension();
            if (ROR_not_equals(v.getDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768", _mut34219, _mut34220, _mut34221, _mut34222, _mut34223)) {
                throw new DimensionMismatchException(v.getDimension(), nRows);
            }
            final T[] out = MathArrays.buildArray(field, nCols);
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768", _mut34229, _mut34230, _mut34231, _mut34232, _mut34233); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768");
                T sum = field.getZero();
                for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768", _mut34224, _mut34225, _mut34226, _mut34227, _mut34228); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.preMultiply_768");
                    sum = sum.add(getEntry(i, col).multiply(v.getEntry(i)));
                }
                out[col] = sum;
            }
            return new ArrayFieldVector<T>(field, out, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793", _mut34234, _mut34235, _mut34236, _mut34237), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793", _mut34238, _mut34239, _mut34240, _mut34241));
        for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793", _mut34247, _mut34248, _mut34249, _mut34250, _mut34251); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793");
            for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793", _mut34242, _mut34243, _mut34244, _mut34245, _mut34246); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_793");
                final T oldValue = getEntry(row, column);
                final T newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808", _mut34252, _mut34253, _mut34254, _mut34255), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808", _mut34256, _mut34257, _mut34258, _mut34259));
        for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808", _mut34265, _mut34266, _mut34267, _mut34268, _mut34269); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808");
            for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808", _mut34260, _mut34261, _mut34262, _mut34263, _mut34264); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_808");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_821");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_821", _mut34275, _mut34276, _mut34277, _mut34278, _mut34279); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_821");
            for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_821", _mut34270, _mut34271, _mut34272, _mut34273, _mut34274); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_821");
                final T oldValue = getEntry(row, column);
                final T newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_839");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_839", _mut34285, _mut34286, _mut34287, _mut34288, _mut34289); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_839");
            for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_839", _mut34280, _mut34281, _mut34282, _mut34283, _mut34284); ++column) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInRowOrder_839");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855", _mut34290, _mut34291, _mut34292, _mut34293), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855", _mut34294, _mut34295, _mut34296, _mut34297));
        for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855", _mut34303, _mut34304, _mut34305, _mut34306, _mut34307); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855");
            for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855", _mut34298, _mut34299, _mut34300, _mut34301, _mut34302); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_855");
                final T oldValue = getEntry(row, column);
                final T newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870");
        final int rows = getRowDimension();
        final int columns = getColumnDimension();
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870", _mut34308, _mut34309, _mut34310, _mut34311), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870", _mut34312, _mut34313, _mut34314, _mut34315));
        for (int column = 0; ROR_less(column, columns, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870", _mut34321, _mut34322, _mut34323, _mut34324, _mut34325); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870");
            for (int row = 0; ROR_less(row, rows, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870", _mut34316, _mut34317, _mut34318, _mut34319, _mut34320); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_870");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_883");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_883", _mut34331, _mut34332, _mut34333, _mut34334, _mut34335); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_883");
            for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_883", _mut34326, _mut34327, _mut34328, _mut34329, _mut34330); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_883");
                final T oldValue = getEntry(row, column);
                final T newValue = visitor.visit(row, column, oldValue);
                setEntry(row, column, newValue);
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_901");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(getRowDimension(), getColumnDimension(), startRow, endRow, startColumn, endColumn);
        for (int column = startColumn; ROR_less_equals(column, endColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_901", _mut34341, _mut34342, _mut34343, _mut34344, _mut34345); ++column) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_901");
            for (int row = startRow; ROR_less_equals(row, endRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_901", _mut34336, _mut34337, _mut34338, _mut34339, _mut34340); ++row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.walkInColumnOrder_901");
                visitor.visit(row, column, getEntry(row, column));
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor) {
        return walkInRowOrder(visitor);
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        return walkInRowOrder(visitor);
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
    }

    /**
     * Get a string representation for this matrix.
     * @return a string representation for this matrix
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final StringBuffer res = new StringBuffer();
        String fullClassName = getClass().getName();
        String shortClassName = fullClassName.substring(AOR_plus(fullClassName.lastIndexOf('.'), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946", _mut34346, _mut34347, _mut34348, _mut34349));
        res.append(shortClassName).append("{");
        for (int i = 0; ROR_less(i, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946", _mut34365, _mut34366, _mut34367, _mut34368, _mut34369); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946");
            if (ROR_greater(i, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946", _mut34350, _mut34351, _mut34352, _mut34353, _mut34354)) {
                res.append(",");
            }
            res.append("{");
            for (int j = 0; ROR_less(j, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946", _mut34360, _mut34361, _mut34362, _mut34363, _mut34364); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946");
                if (ROR_greater(j, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.toString_946", _mut34355, _mut34356, _mut34357, _mut34358, _mut34359)) {
                    res.append(",");
                }
                res.append(getEntry(i, j));
            }
            res.append("}");
        }
        res.append("}");
        return res.toString();
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>FieldMatrix</code> instance with the same dimensions as this
     * and all corresponding matrix entries are equal.
     *
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(final Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981");
        if (object == this) {
            return true;
        }
        if (object instanceof FieldMatrix<?> == false) {
            return false;
        }
        FieldMatrix<?> m = (FieldMatrix<?>) object;
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        if ((_mut34380 ? (ROR_not_equals(m.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34370, _mut34371, _mut34372, _mut34373, _mut34374) && ROR_not_equals(m.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34375, _mut34376, _mut34377, _mut34378, _mut34379)) : (ROR_not_equals(m.getColumnDimension(), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34370, _mut34371, _mut34372, _mut34373, _mut34374) || ROR_not_equals(m.getRowDimension(), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34375, _mut34376, _mut34377, _mut34378, _mut34379)))) {
            return false;
        }
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34386, _mut34387, _mut34388, _mut34389, _mut34390); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981", _mut34381, _mut34382, _mut34383, _mut34384, _mut34385); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.equals_981");
                if (!getEntry(row, col).equals(m.getEntry(row, col))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Computes a hashcode for the matrix.
     *
     * @return hashcode for matrix
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010");
        int ret = 322562;
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34391, _mut34392, _mut34393, _mut34394), nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34395, _mut34396, _mut34397, _mut34398);
        ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34399, _mut34400, _mut34401, _mut34402), nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34403, _mut34404, _mut34405, _mut34406);
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34444, _mut34445, _mut34446, _mut34447, _mut34448); ++row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34439, _mut34440, _mut34441, _mut34442, _mut34443); ++col) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010");
                ret = AOR_plus(AOR_multiply(ret, 31, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34407, _mut34408, _mut34409, _mut34410), AOR_multiply((AOR_plus(AOR_multiply(11, (AOR_plus(row, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34411, _mut34412, _mut34413, _mut34414)), "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34415, _mut34416, _mut34417, _mut34418), AOR_multiply(17, (AOR_plus(col, 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34419, _mut34420, _mut34421, _mut34422)), "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34423, _mut34424, _mut34425, _mut34426), "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34427, _mut34428, _mut34429, _mut34430)), getEntry(row, col).hashCode(), "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34431, _mut34432, _mut34433, _mut34434), "org.apache.commons.math3.linear.AbstractFieldMatrix.hashCode_1010", _mut34435, _mut34436, _mut34437, _mut34438);
            }
        }
        return ret;
    }

    /**
     * Check if a row index is valid.
     *
     * @param row Row index to check.
     * @throws OutOfRangeException if {@code index} is not valid.
     */
    protected void checkRowIndex(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031");
        if ((_mut34459 ? (ROR_less(row, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031", _mut34449, _mut34450, _mut34451, _mut34452, _mut34453) && ROR_greater_equals(row, getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031", _mut34454, _mut34455, _mut34456, _mut34457, _mut34458)) : (ROR_less(row, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031", _mut34449, _mut34450, _mut34451, _mut34452, _mut34453) || ROR_greater_equals(row, getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031", _mut34454, _mut34455, _mut34456, _mut34457, _mut34458)))) {
            throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, row, 0, AOR_minus(getRowDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkRowIndex_1031", _mut34460, _mut34461, _mut34462, _mut34463));
        }
    }

    /**
     * Check if a column index is valid.
     *
     * @param column Column index to check.
     * @throws OutOfRangeException if {@code index} is not valid.
     */
    protected void checkColumnIndex(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044");
        if ((_mut34474 ? (ROR_less(column, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044", _mut34464, _mut34465, _mut34466, _mut34467, _mut34468) && ROR_greater_equals(column, getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044", _mut34469, _mut34470, _mut34471, _mut34472, _mut34473)) : (ROR_less(column, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044", _mut34464, _mut34465, _mut34466, _mut34467, _mut34468) || ROR_greater_equals(column, getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044", _mut34469, _mut34470, _mut34471, _mut34472, _mut34473)))) {
            throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, column, 0, AOR_minus(getColumnDimension(), 1, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkColumnIndex_1044", _mut34475, _mut34476, _mut34477, _mut34478));
        }
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to n-1.
     *
     * @param startRow Initial row index.
     * @param endRow Final row index.
     * @param startColumn Initial column index.
     * @param endColumn Final column index.
     * @throws OutOfRangeException if the indices are not valid.
     * @throws NumberIsTooSmallException if {@code endRow < startRow} or
     * {@code endColumn < startColumn}.
     */
    protected void checkSubMatrixIndex(final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1064");
        checkRowIndex(startRow);
        checkRowIndex(endRow);
        if (ROR_less(endRow, startRow, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1064", _mut34479, _mut34480, _mut34481, _mut34482, _mut34483)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, endRow, startRow, true);
        }
        checkColumnIndex(startColumn);
        checkColumnIndex(endColumn);
        if (ROR_less(endColumn, startColumn, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1064", _mut34484, _mut34485, _mut34486, _mut34487, _mut34488)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, endColumn, startColumn, true);
        }
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to n-1.
     *
     * @param selectedRows Array of row indices.
     * @param selectedColumns Array of column indices.
     * @throws NullArgumentException if the arrays are {@code null}.
     * @throws NoDataException if the arrays have zero length.
     * @throws OutOfRangeException if row or column selections are not valid.
     */
    protected void checkSubMatrixIndex(final int[] selectedRows, final int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092");
        if ((_mut34489 ? (selectedRows == null && selectedColumns == null) : (selectedRows == null || selectedColumns == null))) {
            throw new NullArgumentException();
        }
        if ((_mut34500 ? (ROR_equals(selectedRows.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092", _mut34490, _mut34491, _mut34492, _mut34493, _mut34494) && ROR_equals(selectedColumns.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092", _mut34495, _mut34496, _mut34497, _mut34498, _mut34499)) : (ROR_equals(selectedRows.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092", _mut34490, _mut34491, _mut34492, _mut34493, _mut34494) || ROR_equals(selectedColumns.length, 0, "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092", _mut34495, _mut34496, _mut34497, _mut34498, _mut34499)))) {
            throw new NoDataException();
        }
        for (final int row : selectedRows) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092");
            checkRowIndex(row);
        }
        for (final int column : selectedColumns) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubMatrixIndex_1092");
            checkColumnIndex(column);
        }
    }

    /**
     * Check if a matrix is addition compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws MatrixDimensionMismatchException if the matrix is not
     * addition-compatible with instance.
     */
    protected void checkAdditionCompatible(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkAdditionCompatible_1118");
        if ((_mut34511 ? ((ROR_not_equals(getRowDimension(), m.getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkAdditionCompatible_1118", _mut34501, _mut34502, _mut34503, _mut34504, _mut34505)) && (ROR_not_equals(getColumnDimension(), m.getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkAdditionCompatible_1118", _mut34506, _mut34507, _mut34508, _mut34509, _mut34510))) : ((ROR_not_equals(getRowDimension(), m.getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkAdditionCompatible_1118", _mut34501, _mut34502, _mut34503, _mut34504, _mut34505)) || (ROR_not_equals(getColumnDimension(), m.getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkAdditionCompatible_1118", _mut34506, _mut34507, _mut34508, _mut34509, _mut34510))))) {
            throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    /**
     * Check if a matrix is subtraction compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws MatrixDimensionMismatchException if the matrix is not
     * subtraction-compatible with instance.
     */
    protected void checkSubtractionCompatible(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubtractionCompatible_1134");
        if ((_mut34522 ? ((ROR_not_equals(getRowDimension(), m.getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubtractionCompatible_1134", _mut34512, _mut34513, _mut34514, _mut34515, _mut34516)) && (ROR_not_equals(getColumnDimension(), m.getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubtractionCompatible_1134", _mut34517, _mut34518, _mut34519, _mut34520, _mut34521))) : ((ROR_not_equals(getRowDimension(), m.getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubtractionCompatible_1134", _mut34512, _mut34513, _mut34514, _mut34515, _mut34516)) || (ROR_not_equals(getColumnDimension(), m.getColumnDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkSubtractionCompatible_1134", _mut34517, _mut34518, _mut34519, _mut34520, _mut34521))))) {
            throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    /**
     * Check if a matrix is multiplication compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws DimensionMismatchException if the matrix is not
     * multiplication-compatible with instance.
     */
    protected void checkMultiplicationCompatible(final FieldMatrix<T> m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.AbstractFieldMatrix.checkMultiplicationCompatible_1150");
        if (ROR_not_equals(getColumnDimension(), m.getRowDimension(), "org.apache.commons.math3.linear.AbstractFieldMatrix.checkMultiplicationCompatible_1150", _mut34523, _mut34524, _mut34525, _mut34526, _mut34527)) {
            throw new DimensionMismatchException(m.getRowDimension(), getColumnDimension());
        }
    }
}
