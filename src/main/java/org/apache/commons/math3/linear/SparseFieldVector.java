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
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the {@link FieldVector} interface with a {@link OpenIntToFieldHashMap} backing store.
 * <p>
 *  Caveat: This implementation assumes that, for any {@code x},
 *  the equality {@code x * 0d == 0d} holds. But it is is not true for
 *  {@code NaN}. Moreover, zero entries will lose their sign.
 *  Some operations (that involve {@code NaN} and/or infinities) may
 *  thus give incorrect results.
 * </p>
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class SparseFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {

    @Conditional
    public static boolean _mut32614 = false, _mut32615 = false, _mut32616 = false, _mut32617 = false, _mut32618 = false, _mut32619 = false, _mut32620 = false, _mut32621 = false, _mut32622 = false, _mut32623 = false, _mut32624 = false, _mut32625 = false, _mut32626 = false, _mut32627 = false, _mut32628 = false, _mut32629 = false, _mut32630 = false, _mut32631 = false, _mut32632 = false, _mut32633 = false, _mut32634 = false, _mut32635 = false, _mut32636 = false, _mut32637 = false, _mut32638 = false, _mut32639 = false, _mut32640 = false, _mut32641 = false, _mut32642 = false, _mut32643 = false, _mut32644 = false, _mut32645 = false, _mut32646 = false, _mut32647 = false, _mut32648 = false, _mut32649 = false, _mut32650 = false, _mut32651 = false, _mut32652 = false, _mut32653 = false, _mut32654 = false, _mut32655 = false, _mut32656 = false, _mut32657 = false, _mut32658 = false, _mut32659 = false, _mut32660 = false, _mut32661 = false, _mut32662 = false, _mut32663 = false, _mut32664 = false, _mut32665 = false, _mut32666 = false, _mut32667 = false, _mut32668 = false, _mut32669 = false, _mut32670 = false, _mut32671 = false, _mut32672 = false, _mut32673 = false, _mut32674 = false, _mut32675 = false, _mut32676 = false, _mut32677 = false, _mut32678 = false, _mut32679 = false, _mut32680 = false, _mut32681 = false, _mut32682 = false, _mut32683 = false, _mut32684 = false, _mut32685 = false, _mut32686 = false, _mut32687 = false, _mut32688 = false, _mut32689 = false, _mut32690 = false, _mut32691 = false, _mut32692 = false, _mut32693 = false, _mut32694 = false, _mut32695 = false, _mut32696 = false, _mut32697 = false, _mut32698 = false, _mut32699 = false, _mut32700 = false, _mut32701 = false, _mut32702 = false, _mut32703 = false, _mut32704 = false, _mut32705 = false, _mut32706 = false, _mut32707 = false, _mut32708 = false, _mut32709 = false, _mut32710 = false, _mut32711 = false, _mut32712 = false, _mut32713 = false, _mut32714 = false, _mut32715 = false, _mut32716 = false, _mut32717 = false, _mut32718 = false, _mut32719 = false, _mut32720 = false, _mut32721 = false, _mut32722 = false, _mut32723 = false, _mut32724 = false, _mut32725 = false, _mut32726 = false, _mut32727 = false, _mut32728 = false, _mut32729 = false, _mut32730 = false, _mut32731 = false, _mut32732 = false, _mut32733 = false, _mut32734 = false, _mut32735 = false, _mut32736 = false, _mut32737 = false, _mut32738 = false, _mut32739 = false, _mut32740 = false, _mut32741 = false, _mut32742 = false, _mut32743 = false, _mut32744 = false, _mut32745 = false, _mut32746 = false, _mut32747 = false, _mut32748 = false, _mut32749 = false, _mut32750 = false, _mut32751 = false, _mut32752 = false, _mut32753 = false, _mut32754 = false, _mut32755 = false, _mut32756 = false, _mut32757 = false, _mut32758 = false, _mut32759 = false, _mut32760 = false, _mut32761 = false, _mut32762 = false, _mut32763 = false, _mut32764 = false, _mut32765 = false, _mut32766 = false, _mut32767 = false, _mut32768 = false, _mut32769 = false, _mut32770 = false, _mut32771 = false, _mut32772 = false, _mut32773 = false, _mut32774 = false, _mut32775 = false, _mut32776 = false, _mut32777 = false, _mut32778 = false, _mut32779 = false, _mut32780 = false, _mut32781 = false, _mut32782 = false, _mut32783 = false, _mut32784 = false, _mut32785 = false, _mut32786 = false, _mut32787 = false, _mut32788 = false, _mut32789 = false, _mut32790 = false, _mut32791 = false, _mut32792 = false, _mut32793 = false, _mut32794 = false, _mut32795 = false, _mut32796 = false, _mut32797 = false, _mut32798 = false, _mut32799 = false, _mut32800 = false, _mut32801 = false, _mut32802 = false, _mut32803 = false, _mut32804 = false, _mut32805 = false, _mut32806 = false, _mut32807 = false, _mut32808 = false, _mut32809 = false, _mut32810 = false, _mut32811 = false, _mut32812 = false, _mut32813 = false, _mut32814 = false, _mut32815 = false, _mut32816 = false, _mut32817 = false, _mut32818 = false, _mut32819 = false, _mut32820 = false, _mut32821 = false, _mut32822 = false, _mut32823 = false, _mut32824 = false, _mut32825 = false, _mut32826 = false;

    /**
     *  Serialization identifier.
     */
    private static final long serialVersionUID = 7841233292190413362L;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Entries of the vector.
     */
    private final OpenIntToFieldHashMap<T> entries;

    /**
     * Dimension of the vector.
     */
    private final int virtualSize;

    /**
     * Build a 0-length vector.
     * Zero-length vectors may be used to initialize construction of vectors
     * by data gathering. We start with zero-length and use either the {@link
     * #SparseFieldVector(SparseFieldVector, int)} constructor
     * or one of the {@code append} method ({@link #append(FieldVector)} or
     * {@link #append(SparseFieldVector)}) to gather data into this vector.
     *
     * @param field Field to which the elements belong.
     */
    public SparseFieldVector(Field<T> field) {
        this(field, 0);
    }

    /**
     * Construct a vector of zeroes.
     *
     * @param field Field to which the elements belong.
     * @param dimension Size of the vector.
     */
    public SparseFieldVector(Field<T> field, int dimension) {
        this.field = field;
        virtualSize = dimension;
        entries = new OpenIntToFieldHashMap<T>(field);
    }

    /**
     * Build a resized vector, for use with append.
     *
     * @param v Original vector
     * @param resize Amount to add.
     */
    protected SparseFieldVector(SparseFieldVector<T> v, int resize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.SparseFieldVector_89");
        field = v.field;
        virtualSize = AOR_plus(v.getDimension(), resize, "org.apache.commons.math3.linear.SparseFieldVector.SparseFieldVector_89", _mut32614, _mut32615, _mut32616, _mut32617);
        entries = new OpenIntToFieldHashMap<T>(v.entries);
    }

    /**
     * Build a vector with known the sparseness (for advanced use only).
     *
     * @param field Field to which the elements belong.
     * @param dimension Size of the vector.
     * @param expectedSize Expected number of non-zero entries.
     */
    public SparseFieldVector(Field<T> field, int dimension, int expectedSize) {
        this.field = field;
        virtualSize = dimension;
        entries = new OpenIntToFieldHashMap<T>(field, expectedSize);
    }

    /**
     * Create from a Field array.
     * Only non-zero entries will be stored.
     *
     * @param field Field to which the elements belong.
     * @param values Set of values to create from.
     * @exception NullArgumentException if values is null
     */
    public SparseFieldVector(Field<T> field, T[] values) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.SparseFieldVector_117");
        MathUtils.checkNotNull(values);
        this.field = field;
        virtualSize = values.length;
        entries = new OpenIntToFieldHashMap<T>(field);
        for (int key = 0; ROR_less(key, values.length, "org.apache.commons.math3.linear.SparseFieldVector.SparseFieldVector_117", _mut32618, _mut32619, _mut32620, _mut32621, _mut32622); key++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.SparseFieldVector_117");
            T value = values[key];
            entries.put(key, value);
        }
    }

    /**
     * Copy constructor.
     *
     * @param v Instance to copy.
     */
    public SparseFieldVector(SparseFieldVector<T> v) {
        field = v.field;
        virtualSize = v.getDimension();
        entries = new OpenIntToFieldHashMap<T>(v.getEntries());
    }

    /**
     * Get the entries of this instance.
     *
     * @return the entries of this instance
     */
    private OpenIntToFieldHashMap<T> getEntries() {
        return entries;
    }

    /**
     * Optimized method to add sparse vectors.
     *
     * @param v Vector to add.
     * @return {@code this + v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}.
     */
    public FieldVector<T> add(SparseFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        SparseFieldVector<T> res = (SparseFieldVector<T>) copy();
        OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.add_156");
            iter.advance();
            int key = iter.key();
            T value = iter.value();
            if (entries.containsKey(key)) {
                res.setEntry(key, entries.get(key).add(value));
            } else {
                res.setEntry(key, value);
            }
        }
        return res;
    }

    /**
     * Construct a vector by appending a vector to this vector.
     *
     * @param v Vector to append to this one.
     * @return a new vector.
     */
    public FieldVector<T> append(SparseFieldVector<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.append_181");
        SparseFieldVector<T> res = new SparseFieldVector<T>(this, v.getDimension());
        OpenIntToFieldHashMap<T>.Iterator iter = v.entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.append_181");
            iter.advance();
            res.setEntry(AOR_plus(iter.key(), virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.append_181", _mut32623, _mut32624, _mut32625, _mut32626), iter.value());
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> append(FieldVector<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.append_192");
        if (v instanceof SparseFieldVector<?>) {
            return append((SparseFieldVector<T>) v);
        } else {
            final int n = v.getDimension();
            FieldVector<T> res = new SparseFieldVector<T>(this, n);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SparseFieldVector.append_192", _mut32631, _mut32632, _mut32633, _mut32634, _mut32635); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.append_192");
                res.setEntry(AOR_plus(i, virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.append_192", _mut32627, _mut32628, _mut32629, _mut32630), v.getEntry(i));
            }
            return res;
        }
    }

    /**
     * {@inheritDoc}
     * @exception NullArgumentException if d is null
     */
    public FieldVector<T> append(T d) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        FieldVector<T> res = new SparseFieldVector<T>(this, 1);
        res.setEntry(virtualSize, d);
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> copy() {
        return new SparseFieldVector<T>(this);
    }

    /**
     * {@inheritDoc}
     */
    public T dotProduct(FieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        T res = field.getZero();
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.dotProduct_221");
            iter.advance();
            res = res.add(v.getEntry(iter.key()).multiply(iter.value()));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> ebeDivide(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(v.getDimension());
        SparseFieldVector<T> res = new SparseFieldVector<T>(this);
        OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.ebeDivide_233");
            iter.advance();
            res.setEntry(iter.key(), iter.value().divide(v.getEntry(iter.key())));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> ebeMultiply(FieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        SparseFieldVector<T> res = new SparseFieldVector<T>(this);
        OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.ebeMultiply_246");
            iter.advance();
            res.setEntry(iter.key(), iter.value().multiply(v.getEntry(iter.key())));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated as of 3.1, to be removed in 4.0. Please use the {@link #toArray()} method instead.
     */
    @Deprecated
    public T[] getData() {
        return toArray();
    }

    /**
     * {@inheritDoc}
     */
    public int getDimension() {
        return virtualSize;
    }

    /**
     * {@inheritDoc}
     */
    public T getEntry(int index) throws OutOfRangeException {
        checkIndex(index);
        return entries.get(index);
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
    public FieldVector<T> getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285");
        if (ROR_less(n, 0, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32636, _mut32637, _mut32638, _mut32639, _mut32640)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        checkIndex(index);
        checkIndex(AOR_minus(AOR_plus(index, n, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32641, _mut32642, _mut32643, _mut32644), 1, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32645, _mut32646, _mut32647, _mut32648));
        SparseFieldVector<T> res = new SparseFieldVector<T>(field, n);
        int end = AOR_plus(index, n, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32649, _mut32650, _mut32651, _mut32652);
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285");
            iter.advance();
            int key = iter.key();
            if ((_mut32663 ? (ROR_greater_equals(key, index, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32653, _mut32654, _mut32655, _mut32656, _mut32657) || ROR_less(key, end, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32658, _mut32659, _mut32660, _mut32661, _mut32662)) : (ROR_greater_equals(key, index, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32653, _mut32654, _mut32655, _mut32656, _mut32657) && ROR_less(key, end, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32658, _mut32659, _mut32660, _mut32661, _mut32662)))) {
                res.setEntry(AOR_minus(key, index, "org.apache.commons.math3.linear.SparseFieldVector.getSubVector_285", _mut32664, _mut32665, _mut32666, _mut32667), iter.value());
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapAdd(T d) throws NullArgumentException {
        return copy().mapAddToSelf(d);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapAddToSelf(T d) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapAddToSelf_311");
        for (int i = 0; ROR_less(i, virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.mapAddToSelf_311", _mut32668, _mut32669, _mut32670, _mut32671, _mut32672); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapAddToSelf_311");
            setEntry(i, getEntry(i).add(d));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapDivide(T d) throws NullArgumentException, MathArithmeticException {
        return copy().mapDivideToSelf(d);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapDivideToSelf(T d) throws NullArgumentException, MathArithmeticException {
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapDivideToSelf_325");
            iter.advance();
            entries.put(iter.key(), iter.value().divide(d));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapInv() throws MathArithmeticException {
        return copy().mapInvToSelf();
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapInvToSelf_341");
        for (int i = 0; ROR_less(i, virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.mapInvToSelf_341", _mut32673, _mut32674, _mut32675, _mut32676, _mut32677); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapInvToSelf_341");
            setEntry(i, field.getOne().divide(getEntry(i)));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapMultiply(T d) throws NullArgumentException {
        return copy().mapMultiplyToSelf(d);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapMultiplyToSelf(T d) throws NullArgumentException {
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.mapMultiplyToSelf_354");
            iter.advance();
            entries.put(iter.key(), iter.value().multiply(d));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapSubtract(T d) throws NullArgumentException {
        return copy().mapSubtractToSelf(d);
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> mapSubtractToSelf(T d) throws NullArgumentException {
        return mapAddToSelf(field.getZero().subtract(d));
    }

    /**
     * Optimized method to compute outer product when both vectors are sparse.
     * @param v vector with which outer product should be computed
     * @return the matrix outer product between instance and v
     */
    public FieldMatrix<T> outerProduct(SparseFieldVector<T> v) {
        final int n = v.getDimension();
        SparseFieldMatrix<T> res = new SparseFieldMatrix<T>(field, virtualSize, n);
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.outerProduct_378");
            iter.advance();
            OpenIntToFieldHashMap<T>.Iterator iter2 = v.entries.iterator();
            while (iter2.hasNext()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.outerProduct_378");
                iter2.advance();
                res.setEntry(iter.key(), iter2.key(), iter.value().multiply(iter2.value()));
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> outerProduct(FieldVector<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.outerProduct_394");
        if (v instanceof SparseFieldVector<?>) {
            return outerProduct((SparseFieldVector<T>) v);
        } else {
            final int n = v.getDimension();
            FieldMatrix<T> res = new SparseFieldMatrix<T>(field, virtualSize, n);
            OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
            while (iter.hasNext()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.outerProduct_394");
                iter.advance();
                int row = iter.key();
                FieldElement<T> value = iter.value();
                for (int col = 0; ROR_less(col, n, "org.apache.commons.math3.linear.SparseFieldVector.outerProduct_394", _mut32678, _mut32679, _mut32680, _mut32681, _mut32682); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.outerProduct_394");
                    res.setEntry(row, col, value.multiply(v.getEntry(col)));
                }
            }
            return res;
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> projection(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(v.getDimension());
        return v.mapMultiply(dotProduct(v).divide(v.dotProduct(v)));
    }

    /**
     * {@inheritDoc}
     * @exception NullArgumentException if value is null
     */
    public void set(T value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.set_423");
        MathUtils.checkNotNull(value);
        for (int i = 0; ROR_less(i, virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.set_423", _mut32683, _mut32684, _mut32685, _mut32686, _mut32687); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.set_423");
            setEntry(i, value);
        }
    }

    /**
     * {@inheritDoc}
     * @exception NullArgumentException if value is null
     */
    public void setEntry(int index, T value) throws NullArgumentException, OutOfRangeException {
        MathUtils.checkNotNull(value);
        checkIndex(index);
        entries.put(index, value);
    }

    /**
     * {@inheritDoc}
     */
    public void setSubVector(int index, FieldVector<T> v) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440");
        checkIndex(index);
        checkIndex(AOR_minus(AOR_plus(index, v.getDimension(), "org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440", _mut32688, _mut32689, _mut32690, _mut32691), 1, "org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440", _mut32692, _mut32693, _mut32694, _mut32695));
        final int n = v.getDimension();
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440", _mut32700, _mut32701, _mut32702, _mut32703, _mut32704); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440");
            setEntry(AOR_plus(i, index, "org.apache.commons.math3.linear.SparseFieldVector.setSubVector_440", _mut32696, _mut32697, _mut32698, _mut32699), v.getEntry(i));
        }
    }

    /**
     * Optimized method to compute {@code this} minus {@code v}.
     * @param v vector to be subtracted
     * @return {@code this - v}
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this}.
     */
    public SparseFieldVector<T> subtract(SparseFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        SparseFieldVector<T> res = (SparseFieldVector<T>) copy();
        OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.subtract_457");
            iter.advance();
            int key = iter.key();
            if (entries.containsKey(key)) {
                res.setEntry(key, entries.get(key).subtract(iter.value()));
            } else {
                res.setEntry(key, field.getZero().subtract(iter.value()));
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> subtract(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.subtract_475");
        if (v instanceof SparseFieldVector<?>) {
            return subtract((SparseFieldVector<T>) v);
        } else {
            final int n = v.getDimension();
            checkVectorDimensions(n);
            SparseFieldVector<T> res = new SparseFieldVector<T>(this);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SparseFieldVector.subtract_475", _mut32705, _mut32706, _mut32707, _mut32708, _mut32709); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.subtract_475");
                if (entries.containsKey(i)) {
                    res.setEntry(i, entries.get(i).subtract(v.getEntry(i)));
                } else {
                    res.setEntry(i, field.getZero().subtract(v.getEntry(i)));
                }
            }
            return res;
        }
    }

    /**
     * {@inheritDoc}
     */
    public T[] toArray() {
        T[] res = MathArrays.buildArray(field, virtualSize);
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.toArray_495");
            iter.advance();
            res[iter.key()] = iter.value();
        }
        return res;
    }

    /**
     * Check whether an index is valid.
     *
     * @param index Index to check.
     * @throws OutOfRangeException if the index is not valid.
     */
    private void checkIndex(final int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511");
        if ((_mut32720 ? (ROR_less(index, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511", _mut32710, _mut32711, _mut32712, _mut32713, _mut32714) && ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511", _mut32715, _mut32716, _mut32717, _mut32718, _mut32719)) : (ROR_less(index, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511", _mut32710, _mut32711, _mut32712, _mut32713, _mut32714) || ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511", _mut32715, _mut32716, _mut32717, _mut32718, _mut32719)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(getDimension(), 1, "org.apache.commons.math3.linear.SparseFieldVector.checkIndex_511", _mut32721, _mut32722, _mut32723, _mut32724));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526");
        final int dim = getDimension();
        if ((_mut32735 ? ((ROR_less(start, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32725, _mut32726, _mut32727, _mut32728, _mut32729)) && (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32730, _mut32731, _mut32732, _mut32733, _mut32734))) : ((ROR_less(start, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32725, _mut32726, _mut32727, _mut32728, _mut32729)) || (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32730, _mut32731, _mut32732, _mut32733, _mut32734))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, start, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32736, _mut32737, _mut32738, _mut32739));
        }
        if ((_mut32750 ? ((ROR_less(end, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32740, _mut32741, _mut32742, _mut32743, _mut32744)) && (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32745, _mut32746, _mut32747, _mut32748, _mut32749))) : ((ROR_less(end, 0, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32740, _mut32741, _mut32742, _mut32743, _mut32744)) || (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32745, _mut32746, _mut32747, _mut32748, _mut32749))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, end, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32751, _mut32752, _mut32753, _mut32754));
        }
        if (ROR_less(end, start, "org.apache.commons.math3.linear.SparseFieldVector.checkIndices_526", _mut32755, _mut32756, _mut32757, _mut32758, _mut32759)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, end, start, false);
        }
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n Expected dimension.
     * @throws DimensionMismatchException if the dimensions do not match.
     */
    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.checkVectorDimensions_549");
        if (ROR_not_equals(getDimension(), n, "org.apache.commons.math3.linear.SparseFieldVector.checkVectorDimensions_549", _mut32760, _mut32761, _mut32762, _mut32763, _mut32764)) {
            throw new DimensionMismatchException(getDimension(), n);
        }
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> add(FieldVector<T> v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.add_557");
        if (v instanceof SparseFieldVector<?>) {
            return add((SparseFieldVector<T>) v);
        } else {
            final int n = v.getDimension();
            checkVectorDimensions(n);
            SparseFieldVector<T> res = new SparseFieldVector<T>(field, getDimension());
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SparseFieldVector.add_557", _mut32765, _mut32766, _mut32767, _mut32768, _mut32769); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.add_557");
                res.setEntry(i, v.getEntry(i).add(getEntry(i)));
            }
            return res;
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_582");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_582", _mut32770, _mut32771, _mut32772, _mut32773));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_582", _mut32774, _mut32775, _mut32776, _mut32777, _mut32778); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_582");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_604");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_604", _mut32779, _mut32780, _mut32781, _mut32782, _mut32783); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_604");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_662");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_662", _mut32784, _mut32785, _mut32786, _mut32787));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_662", _mut32788, _mut32789, _mut32790, _mut32791, _mut32792); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_662");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_684");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_684", _mut32793, _mut32794, _mut32795, _mut32796, _mut32797); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.walkInDefaultOrder_684");
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.hashCode_733");
        final int prime = 31;
        int result = 1;
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32798, _mut32799, _mut32800, _mut32801), ((field == null) ? 0 : field.hashCode()), "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32802, _mut32803, _mut32804, _mut32805);
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32806, _mut32807, _mut32808, _mut32809), virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32810, _mut32811, _mut32812, _mut32813);
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.hashCode_733");
            iter.advance();
            int temp = iter.value().hashCode();
            result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32814, _mut32815, _mut32816, _mut32817), temp, "org.apache.commons.math3.linear.SparseFieldVector.hashCode_733", _mut32818, _mut32819, _mut32820, _mut32821);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.equals_750");
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SparseFieldVector<?>)) {
            return false;
        }
        // OK, because "else if" check below ensures that
        @SuppressWarnings("unchecked")
        SparseFieldVector<T> // other must be the same type as this
        other = (SparseFieldVector<T>) obj;
        if (field == null) {
            if (other.field != null) {
                return false;
            }
        } else if (!field.equals(other.field)) {
            return false;
        }
        if (ROR_not_equals(virtualSize, other.virtualSize, "org.apache.commons.math3.linear.SparseFieldVector.equals_750", _mut32822, _mut32823, _mut32824, _mut32825, _mut32826)) {
            return false;
        }
        OpenIntToFieldHashMap<T>.Iterator iter = entries.iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.equals_750");
            iter.advance();
            T test = other.getEntry(iter.key());
            if (!test.equals(iter.value())) {
                return false;
            }
        }
        iter = other.getEntries().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SparseFieldVector.equals_750");
            iter.advance();
            T test = iter.value();
            if (!test.equals(getEntry(iter.key()))) {
                return false;
            }
        }
        return true;
    }
}
