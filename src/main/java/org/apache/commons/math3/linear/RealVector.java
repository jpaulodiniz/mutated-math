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

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.analysis.function.Multiply;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class defining a real-valued vector with basic algebraic operations.
 * <p>
 * vector element indexing is 0-based -- e.g., {@code getEntry(0)}
 * returns the first element of the vector.
 * </p>
 * <p>
 * The {@code code map} and {@code mapToSelf} methods operate
 * on vectors element-wise, i.e. they perform the same operation (adding a scalar,
 * applying a function ...) on each element in turn. The {@code map}
 * versions create a new vector to hold the result and do not change the instance.
 * The {@code mapToSelf} version uses the instance itself to store the
 * results, so the instance is changed by this method. In all cases, the result
 * vector is returned by the methods, allowing the <i>fluent API</i>
 * style, like this:
 * </p>
 * <pre>
 *   RealVector result = v.mapAddToSelf(3.4).mapToSelf(new Tan()).mapToSelf(new Power(2.3));
 * </pre>
 *
 * @since 2.1
 */
public abstract class RealVector {

    @Conditional
    public static boolean _mut33457 = false, _mut33458 = false, _mut33459 = false, _mut33460 = false, _mut33461 = false, _mut33462 = false, _mut33463 = false, _mut33464 = false, _mut33465 = false, _mut33466 = false, _mut33467 = false, _mut33468 = false, _mut33469 = false, _mut33470 = false, _mut33471 = false, _mut33472 = false, _mut33473 = false, _mut33474 = false, _mut33475 = false, _mut33476 = false, _mut33477 = false, _mut33478 = false, _mut33479 = false, _mut33480 = false, _mut33481 = false, _mut33482 = false, _mut33483 = false, _mut33484 = false, _mut33485 = false, _mut33486 = false, _mut33487 = false, _mut33488 = false, _mut33489 = false, _mut33490 = false, _mut33491 = false, _mut33492 = false, _mut33493 = false, _mut33494 = false, _mut33495 = false, _mut33496 = false, _mut33497 = false, _mut33498 = false, _mut33499 = false, _mut33500 = false, _mut33501 = false, _mut33502 = false, _mut33503 = false, _mut33504 = false, _mut33505 = false, _mut33506 = false, _mut33507 = false, _mut33508 = false, _mut33509 = false, _mut33510 = false, _mut33511 = false, _mut33512 = false, _mut33513 = false, _mut33514 = false, _mut33515 = false, _mut33516 = false, _mut33517 = false, _mut33518 = false, _mut33519 = false, _mut33520 = false, _mut33521 = false, _mut33522 = false, _mut33523 = false, _mut33524 = false, _mut33525 = false, _mut33526 = false, _mut33527 = false, _mut33528 = false, _mut33529 = false, _mut33530 = false, _mut33531 = false, _mut33532 = false, _mut33533 = false, _mut33534 = false, _mut33535 = false, _mut33536 = false, _mut33537 = false, _mut33538 = false, _mut33539 = false, _mut33540 = false, _mut33541 = false, _mut33542 = false, _mut33543 = false, _mut33544 = false, _mut33545 = false, _mut33546 = false, _mut33547 = false, _mut33548 = false, _mut33549 = false, _mut33550 = false, _mut33551 = false, _mut33552 = false, _mut33553 = false, _mut33554 = false, _mut33555 = false, _mut33556 = false, _mut33557 = false, _mut33558 = false, _mut33559 = false, _mut33560 = false, _mut33561 = false, _mut33562 = false, _mut33563 = false, _mut33564 = false, _mut33565 = false, _mut33566 = false, _mut33567 = false, _mut33568 = false, _mut33569 = false, _mut33570 = false, _mut33571 = false, _mut33572 = false, _mut33573 = false, _mut33574 = false, _mut33575 = false, _mut33576 = false, _mut33577 = false, _mut33578 = false, _mut33579 = false, _mut33580 = false, _mut33581 = false, _mut33582 = false, _mut33583 = false, _mut33584 = false, _mut33585 = false, _mut33586 = false, _mut33587 = false, _mut33588 = false, _mut33589 = false, _mut33590 = false, _mut33591 = false, _mut33592 = false, _mut33593 = false, _mut33594 = false, _mut33595 = false, _mut33596 = false, _mut33597 = false, _mut33598 = false, _mut33599 = false, _mut33600 = false, _mut33601 = false, _mut33602 = false, _mut33603 = false, _mut33604 = false, _mut33605 = false, _mut33606 = false, _mut33607 = false, _mut33608 = false, _mut33609 = false, _mut33610 = false, _mut33611 = false, _mut33612 = false, _mut33613 = false, _mut33614 = false, _mut33615 = false, _mut33616 = false, _mut33617 = false, _mut33618 = false, _mut33619 = false, _mut33620 = false, _mut33621 = false, _mut33622 = false, _mut33623 = false, _mut33624 = false, _mut33625 = false, _mut33626 = false, _mut33627 = false, _mut33628 = false, _mut33629 = false, _mut33630 = false, _mut33631 = false, _mut33632 = false, _mut33633 = false, _mut33634 = false, _mut33635 = false, _mut33636 = false, _mut33637 = false, _mut33638 = false, _mut33639 = false, _mut33640 = false, _mut33641 = false, _mut33642 = false, _mut33643 = false, _mut33644 = false, _mut33645 = false, _mut33646 = false, _mut33647 = false, _mut33648 = false, _mut33649 = false, _mut33650 = false, _mut33651 = false, _mut33652 = false, _mut33653 = false, _mut33654 = false, _mut33655 = false, _mut33656 = false, _mut33657 = false, _mut33658 = false, _mut33659 = false, _mut33660 = false, _mut33661 = false, _mut33662 = false, _mut33663 = false, _mut33664 = false, _mut33665 = false, _mut33666 = false, _mut33667 = false, _mut33668 = false, _mut33669 = false, _mut33670 = false, _mut33671 = false, _mut33672 = false, _mut33673 = false, _mut33674 = false, _mut33675 = false, _mut33676 = false, _mut33677 = false, _mut33678 = false, _mut33679 = false, _mut33680 = false, _mut33681 = false, _mut33682 = false, _mut33683 = false, _mut33684 = false, _mut33685 = false, _mut33686 = false, _mut33687 = false, _mut33688 = false, _mut33689 = false, _mut33690 = false, _mut33691 = false, _mut33692 = false, _mut33693 = false, _mut33694 = false, _mut33695 = false, _mut33696 = false, _mut33697 = false, _mut33698 = false, _mut33699 = false, _mut33700 = false, _mut33701 = false, _mut33702 = false, _mut33703 = false, _mut33704 = false, _mut33705 = false, _mut33706 = false, _mut33707 = false, _mut33708 = false, _mut33709 = false, _mut33710 = false, _mut33711 = false, _mut33712 = false, _mut33713 = false, _mut33714 = false, _mut33715 = false, _mut33716 = false, _mut33717 = false, _mut33718 = false, _mut33719 = false, _mut33720 = false, _mut33721 = false, _mut33722 = false, _mut33723 = false, _mut33724 = false, _mut33725 = false;

    /**
     * Returns the size of the vector.
     *
     * @return the size of this vector.
     */
    public abstract int getDimension();

    /**
     * Return the entry at the specified index.
     *
     * @param index Index location of entry to be fetched.
     * @return the vector entry at {@code index}.
     * @throws OutOfRangeException if the index is not valid.
     * @see #setEntry(int, double)
     */
    public abstract double getEntry(int index) throws OutOfRangeException;

    /**
     * Set a single element.
     *
     * @param index element index.
     * @param value new value for the element.
     * @throws OutOfRangeException if the index is not valid.
     * @see #getEntry(int)
     */
    public abstract void setEntry(int index, double value) throws OutOfRangeException;

    /**
     * Change an entry at the specified index.
     *
     * @param index Index location of entry to be set.
     * @param increment Value to add to the vector entry.
     * @throws OutOfRangeException if the index is not valid.
     * @since 3.0
     */
    public void addToEntry(int index, double increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.addToEntry_96");
        setEntry(index, AOR_plus(getEntry(index), increment, "org.apache.commons.math3.linear.RealVector.addToEntry_96", _mut33457, _mut33458, _mut33459, _mut33460));
    }

    /**
     * Construct a new vector by appending a vector to this vector.
     *
     * @param v vector to append to this one.
     * @return a new vector.
     */
    public abstract RealVector append(RealVector v);

    /**
     * Construct a new vector by appending a double to this vector.
     *
     * @param d double to append.
     * @return a new vector.
     */
    public abstract RealVector append(double d);

    /**
     * Get a subvector from consecutive elements.
     *
     * @param index index of first element.
     * @param n number of elements to be retrieved.
     * @return a vector containing n elements.
     * @throws OutOfRangeException if the index is not valid.
     * @throws NotPositiveException if the number of elements is not positive.
     */
    public abstract RealVector getSubVector(int index, int n) throws NotPositiveException, OutOfRangeException;

    /**
     * Set a sequence of consecutive elements.
     *
     * @param index index of first element to be set.
     * @param v vector containing the values to set.
     * @throws OutOfRangeException if the index is not valid.
     */
    public abstract void setSubVector(int index, RealVector v) throws OutOfRangeException;

    /**
     * Check whether any coordinate of this vector is {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is {@code NaN},
     * {@code false} otherwise.
     */
    public abstract boolean isNaN();

    /**
     * Check whether any coordinate of this vector is infinite and none are {@code NaN}.
     *
     * @return {@code true} if any coordinate of this vector is infinite and
     * none are {@code NaN}, {@code false} otherwise.
     */
    public abstract boolean isInfinite();

    /**
     * Check if instance and specified vectors have the same dimension.
     *
     * @param v Vector to compare instance with.
     * @throws DimensionMismatchException if the vectors do not
     * have the same dimension.
     */
    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n Expected dimension.
     * @throws DimensionMismatchException if the dimension is
     * inconsistent with the vector size.
     */
    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.checkVectorDimensions_174");
        int d = getDimension();
        if (ROR_not_equals(d, n, "org.apache.commons.math3.linear.RealVector.checkVectorDimensions_174", _mut33461, _mut33462, _mut33463, _mut33464, _mut33465)) {
            throw new DimensionMismatchException(d, n);
        }
    }

    /**
     * Check if an index is valid.
     *
     * @param index Index to check.
     * @exception OutOfRangeException if {@code index} is not valid.
     */
    protected void checkIndex(final int index) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.checkIndex_188");
        if ((_mut33476 ? (ROR_less(index, 0, "org.apache.commons.math3.linear.RealVector.checkIndex_188", _mut33466, _mut33467, _mut33468, _mut33469, _mut33470) && ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.RealVector.checkIndex_188", _mut33471, _mut33472, _mut33473, _mut33474, _mut33475)) : (ROR_less(index, 0, "org.apache.commons.math3.linear.RealVector.checkIndex_188", _mut33466, _mut33467, _mut33468, _mut33469, _mut33470) || ROR_greater_equals(index, getDimension(), "org.apache.commons.math3.linear.RealVector.checkIndex_188", _mut33471, _mut33472, _mut33473, _mut33474, _mut33475)))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, AOR_minus(getDimension(), 1, "org.apache.commons.math3.linear.RealVector.checkIndex_188", _mut33477, _mut33478, _mut33479, _mut33480));
        }
    }

    /**
     * Checks that the indices of a subvector are valid.
     *
     * @param start the index of the first entry of the subvector
     * @param end the index of the last entry of the subvector (inclusive)
     * @throws OutOfRangeException if {@code start} of {@code end} are not valid
     * @throws NumberIsTooSmallException if {@code end < start}
     * @since 3.1
     */
    protected void checkIndices(final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.checkIndices_205");
        final int dim = getDimension();
        if ((_mut33491 ? ((ROR_less(start, 0, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33481, _mut33482, _mut33483, _mut33484, _mut33485)) && (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33486, _mut33487, _mut33488, _mut33489, _mut33490))) : ((ROR_less(start, 0, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33481, _mut33482, _mut33483, _mut33484, _mut33485)) || (ROR_greater_equals(start, dim, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33486, _mut33487, _mut33488, _mut33489, _mut33490))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, start, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33492, _mut33493, _mut33494, _mut33495));
        }
        if ((_mut33506 ? ((ROR_less(end, 0, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33496, _mut33497, _mut33498, _mut33499, _mut33500)) && (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33501, _mut33502, _mut33503, _mut33504, _mut33505))) : ((ROR_less(end, 0, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33496, _mut33497, _mut33498, _mut33499, _mut33500)) || (ROR_greater_equals(end, dim, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33501, _mut33502, _mut33503, _mut33504, _mut33505))))) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, end, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33507, _mut33508, _mut33509, _mut33510));
        }
        if (ROR_less(end, start, "org.apache.commons.math3.linear.RealVector.checkIndices_205", _mut33511, _mut33512, _mut33513, _mut33514, _mut33515)) {
            // TODO Use more specific error message
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, end, start, false);
        }
    }

    /**
     * Compute the sum of this vector and {@code v}.
     * Returns a new vector. Does not change instance data.
     *
     * @param v Vector to be added.
     * @return {@code this} + {@code v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public RealVector add(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.add_232");
        checkVectorDimensions(v);
        RealVector result = v.copy();
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.add_232");
            final Entry e = it.next();
            final int index = e.getIndex();
            result.setEntry(index, AOR_plus(e.getValue(), result.getEntry(index), "org.apache.commons.math3.linear.RealVector.add_232", _mut33516, _mut33517, _mut33518, _mut33519));
        }
        return result;
    }

    /**
     * Subtract {@code v} from this vector.
     * Returns a new vector. Does not change instance data.
     *
     * @param v Vector to be subtracted.
     * @return {@code this} - {@code v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public RealVector subtract(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.subtract_253");
        checkVectorDimensions(v);
        RealVector result = v.mapMultiply(-1d);
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.subtract_253");
            final Entry e = it.next();
            final int index = e.getIndex();
            result.setEntry(index, AOR_plus(e.getValue(), result.getEntry(index), "org.apache.commons.math3.linear.RealVector.subtract_253", _mut33520, _mut33521, _mut33522, _mut33523));
        }
        return result;
    }

    /**
     * Add a value to each entry.
     * Returns a new vector. Does not change instance data.
     *
     * @param d Value to be added to each entry.
     * @return {@code this} + {@code d}.
     */
    public RealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    /**
     * Add a value to each entry.
     * The instance is changed in-place.
     *
     * @param d Value to be added to each entry.
     * @return {@code this}.
     */
    public RealVector mapAddToSelf(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.mapAddToSelf_283");
        if (ROR_not_equals(d, 0, "org.apache.commons.math3.linear.RealVector.mapAddToSelf_283", _mut33524, _mut33525, _mut33526, _mut33527, _mut33528)) {
            return mapToSelf(FunctionUtils.fix2ndArgument(new Add(), d));
        }
        return this;
    }

    /**
     * Returns a (deep) copy of this vector.
     *
     * @return a vector copy.
     */
    public abstract RealVector copy();

    /**
     * Compute the dot product of this vector with {@code v}.
     *
     * @param v Vector with which dot product should be computed
     * @return the scalar dot product between this instance and {@code v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public double dotProduct(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.dotProduct_305");
        checkVectorDimensions(v);
        double d = 0;
        final int n = getDimension();
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.RealVector.dotProduct_305", _mut33533, _mut33534, _mut33535, _mut33536, _mut33537); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.dotProduct_305");
            d += AOR_multiply(getEntry(i), v.getEntry(i), "org.apache.commons.math3.linear.RealVector.dotProduct_305", _mut33529, _mut33530, _mut33531, _mut33532);
        }
        return d;
    }

    /**
     * Computes the cosine of the angle between this vector and the
     * argument.
     *
     * @param v Vector.
     * @return the cosine of the angle between this vector and {@code v}.
     * @throws MathArithmeticException if {@code this} or {@code v} is the null
     * vector
     * @throws DimensionMismatchException if the dimensions of {@code this} and
     * {@code v} do not match
     */
    public double cosine(RealVector v) throws DimensionMismatchException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.cosine_326");
        final double norm = getNorm();
        final double vNorm = v.getNorm();
        if ((_mut33548 ? (ROR_equals(norm, 0, "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33538, _mut33539, _mut33540, _mut33541, _mut33542) && ROR_equals(vNorm, 0, "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33543, _mut33544, _mut33545, _mut33546, _mut33547)) : (ROR_equals(norm, 0, "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33538, _mut33539, _mut33540, _mut33541, _mut33542) || ROR_equals(vNorm, 0, "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33543, _mut33544, _mut33545, _mut33546, _mut33547)))) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        return AOR_divide(dotProduct(v), (AOR_multiply(norm, vNorm, "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33549, _mut33550, _mut33551, _mut33552)), "org.apache.commons.math3.linear.RealVector.cosine_326", _mut33553, _mut33554, _mut33555, _mut33556);
    }

    /**
     * Element-by-element division.
     *
     * @param v Vector by which instance elements must be divided.
     * @return a vector containing this[i] / v[i] for all i.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public abstract RealVector ebeDivide(RealVector v) throws DimensionMismatchException;

    /**
     * Element-by-element multiplication.
     *
     * @param v Vector by which instance elements must be multiplied
     * @return a vector containing this[i] * v[i] for all i.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public abstract RealVector ebeMultiply(RealVector v) throws DimensionMismatchException;

    /**
     * Distance between two vectors.
     * <p>This method computes the distance consistent with the
     * L<sub>2</sub> norm, i.e. the square root of the sum of
     * element differences, or Euclidean distance.</p>
     *
     * @param v Vector to which distance is requested.
     * @return the distance between two vectors.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     * @see #getL1Distance(RealVector)
     * @see #getLInfDistance(RealVector)
     * @see #getNorm()
     */
    public double getDistance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getDistance_374");
        checkVectorDimensions(v);
        double d = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getDistance_374");
            final Entry e = it.next();
            final double diff = AOR_minus(e.getValue(), v.getEntry(e.getIndex()), "org.apache.commons.math3.linear.RealVector.getDistance_374", _mut33557, _mut33558, _mut33559, _mut33560);
            d += AOR_multiply(diff, diff, "org.apache.commons.math3.linear.RealVector.getDistance_374", _mut33561, _mut33562, _mut33563, _mut33564);
        }
        return FastMath.sqrt(d);
    }

    /**
     * Returns the L<sub>2</sub> norm of the vector.
     * <p>The L<sub>2</sub> norm is the root of the sum of
     * the squared elements.</p>
     *
     * @return the norm.
     * @see #getL1Norm()
     * @see #getLInfNorm()
     * @see #getDistance(RealVector)
     */
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getNorm_396");
        double sum = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getNorm_396");
            final Entry e = it.next();
            final double value = e.getValue();
            sum += AOR_multiply(value, value, "org.apache.commons.math3.linear.RealVector.getNorm_396", _mut33565, _mut33566, _mut33567, _mut33568);
        }
        return FastMath.sqrt(sum);
    }

    /**
     * Returns the L<sub>1</sub> norm of the vector.
     * <p>The L<sub>1</sub> norm is the sum of the absolute
     * values of the elements.</p>
     *
     * @return the norm.
     * @see #getNorm()
     * @see #getLInfNorm()
     * @see #getL1Distance(RealVector)
     */
    public double getL1Norm() {
        double norm = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getL1Norm_417");
            final Entry e = it.next();
            norm += FastMath.abs(e.getValue());
        }
        return norm;
    }

    /**
     * Returns the L<sub>&infin;</sub> norm of the vector.
     * <p>The L<sub>&infin;</sub> norm is the max of the absolute
     * values of the elements.</p>
     *
     * @return the norm.
     * @see #getNorm()
     * @see #getL1Norm()
     * @see #getLInfDistance(RealVector)
     */
    public double getLInfNorm() {
        double norm = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getLInfNorm_437");
            final Entry e = it.next();
            norm = FastMath.max(norm, FastMath.abs(e.getValue()));
        }
        return norm;
    }

    /**
     * Distance between two vectors.
     * <p>This method computes the distance consistent with
     * L<sub>1</sub> norm, i.e. the sum of the absolute values of
     * the elements differences.</p>
     *
     * @param v Vector to which distance is requested.
     * @return the distance between two vectors.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     */
    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getL1Distance_458");
        checkVectorDimensions(v);
        double d = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getL1Distance_458");
            final Entry e = it.next();
            d += FastMath.abs(AOR_minus(e.getValue(), v.getEntry(e.getIndex()), "org.apache.commons.math3.linear.RealVector.getL1Distance_458", _mut33569, _mut33570, _mut33571, _mut33572));
        }
        return d;
    }

    /**
     * Distance between two vectors.
     * <p>This method computes the distance consistent with
     * L<sub>&infin;</sub> norm, i.e. the max of the absolute values of
     * element differences.</p>
     *
     * @param v Vector to which distance is requested.
     * @return the distance between two vectors.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     * @see #getDistance(RealVector)
     * @see #getL1Distance(RealVector)
     * @see #getLInfNorm()
     */
    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getLInfDistance_484");
        checkVectorDimensions(v);
        double d = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getLInfDistance_484");
            final Entry e = it.next();
            d = FastMath.max(FastMath.abs(AOR_minus(e.getValue(), v.getEntry(e.getIndex()), "org.apache.commons.math3.linear.RealVector.getLInfDistance_484", _mut33573, _mut33574, _mut33575, _mut33576)), d);
        }
        return d;
    }

    /**
     * Get the index of the minimum entry.
     *
     * @return the index of the minimum entry or -1 if vector length is 0
     * or all entries are {@code NaN}.
     */
    public int getMinIndex() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMinIndex_502");
        int minIndex = -1;
        double minValue = Double.POSITIVE_INFINITY;
        Iterator<Entry> iterator = iterator();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMinIndex_502");
            final Entry entry = iterator.next();
            if (ROR_less_equals(entry.getValue(), minValue, "org.apache.commons.math3.linear.RealVector.getMinIndex_502", _mut33577, _mut33578, _mut33579, _mut33580, _mut33581)) {
                minIndex = entry.getIndex();
                minValue = entry.getValue();
            }
        }
        return minIndex;
    }

    /**
     * Get the value of the minimum entry.
     *
     * @return the value of the minimum entry or {@code NaN} if all
     * entries are {@code NaN}.
     */
    public double getMinValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMinValue_522");
        final int minIndex = getMinIndex();
        return ROR_less(minIndex, 0, "org.apache.commons.math3.linear.RealVector.getMinValue_522", _mut33582, _mut33583, _mut33584, _mut33585, _mut33586) ? Double.NaN : getEntry(minIndex);
    }

    /**
     * Get the index of the maximum entry.
     *
     * @return the index of the maximum entry or -1 if vector length is 0
     * or all entries are {@code NaN}
     */
    public int getMaxIndex() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMaxIndex_533");
        int maxIndex = -1;
        double maxValue = Double.NEGATIVE_INFINITY;
        Iterator<Entry> iterator = iterator();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMaxIndex_533");
            final Entry entry = iterator.next();
            if (ROR_greater_equals(entry.getValue(), maxValue, "org.apache.commons.math3.linear.RealVector.getMaxIndex_533", _mut33587, _mut33588, _mut33589, _mut33590, _mut33591)) {
                maxIndex = entry.getIndex();
                maxValue = entry.getValue();
            }
        }
        return maxIndex;
    }

    /**
     * Get the value of the maximum entry.
     *
     * @return the value of the maximum entry or {@code NaN} if all
     * entries are {@code NaN}.
     */
    public double getMaxValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.getMaxValue_553");
        final int maxIndex = getMaxIndex();
        return ROR_less(maxIndex, 0, "org.apache.commons.math3.linear.RealVector.getMaxValue_553", _mut33592, _mut33593, _mut33594, _mut33595, _mut33596) ? Double.NaN : getEntry(maxIndex);
    }

    /**
     * Multiply each entry by the argument. Returns a new vector.
     * Does not change instance data.
     *
     * @param d Multiplication factor.
     * @return {@code this} * {@code d}.
     */
    public RealVector mapMultiply(double d) {
        return copy().mapMultiplyToSelf(d);
    }

    /**
     * Multiply each entry.
     * The instance is changed in-place.
     *
     * @param d Multiplication factor.
     * @return {@code this}.
     */
    public RealVector mapMultiplyToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Multiply(), d));
    }

    /**
     * Subtract a value from each entry. Returns a new vector.
     * Does not change instance data.
     *
     * @param d Value to be subtracted.
     * @return {@code this} - {@code d}.
     */
    public RealVector mapSubtract(double d) {
        return copy().mapSubtractToSelf(d);
    }

    /**
     * Subtract a value from each entry.
     * The instance is changed in-place.
     *
     * @param d Value to be subtracted.
     * @return {@code this}.
     */
    public RealVector mapSubtractToSelf(double d) {
        return mapAddToSelf(-d);
    }

    /**
     * Divide each entry by the argument. Returns a new vector.
     * Does not change instance data.
     *
     * @param d Value to divide by.
     * @return {@code this} / {@code d}.
     */
    public RealVector mapDivide(double d) {
        return copy().mapDivideToSelf(d);
    }

    /**
     * Divide each entry by the argument.
     * The instance is changed in-place.
     *
     * @param d Value to divide by.
     * @return {@code this}.
     */
    public RealVector mapDivideToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Divide(), d));
    }

    /**
     * Compute the outer product.
     *
     * @param v Vector with which outer product should be computed.
     * @return the matrix outer product between this instance and {@code v}.
     */
    public RealMatrix outerProduct(RealVector v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.outerProduct_631");
        final int m = this.getDimension();
        final int n = v.getDimension();
        final RealMatrix product;
        if ((_mut33597 ? (v instanceof SparseRealVector && this instanceof SparseRealVector) : (v instanceof SparseRealVector || this instanceof SparseRealVector))) {
            product = new OpenMapRealMatrix(m, n);
        } else {
            product = new Array2DRowRealMatrix(m, n);
        }
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.RealVector.outerProduct_631", _mut33607, _mut33608, _mut33609, _mut33610, _mut33611); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.outerProduct_631");
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.RealVector.outerProduct_631", _mut33602, _mut33603, _mut33604, _mut33605, _mut33606); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.outerProduct_631");
                product.setEntry(i, j, AOR_multiply(this.getEntry(i), v.getEntry(j), "org.apache.commons.math3.linear.RealVector.outerProduct_631", _mut33598, _mut33599, _mut33600, _mut33601));
            }
        }
        return product;
    }

    /**
     * Find the orthogonal projection of this vector onto another vector.
     *
     * @param v vector onto which instance must be projected.
     * @return projection of the instance onto {@code v}.
     * @throws DimensionMismatchException if {@code v} is not the same size as
     * {@code this} vector.
     * @throws MathArithmeticException if {@code this} or {@code v} is the null
     * vector
     */
    public RealVector projection(final RealVector v) throws DimensionMismatchException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.projection_658");
        final double norm2 = v.dotProduct(v);
        if (ROR_equals(norm2, 0.0, "org.apache.commons.math3.linear.RealVector.projection_658", _mut33612, _mut33613, _mut33614, _mut33615, _mut33616)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        return v.mapMultiply(AOR_divide(dotProduct(v), v.dotProduct(v), "org.apache.commons.math3.linear.RealVector.projection_658", _mut33617, _mut33618, _mut33619, _mut33620));
    }

    /**
     * Set all elements to a single value.
     *
     * @param value Single value to set for all elements.
     */
    public void set(double value) {
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.set_672");
            final Entry e = it.next();
            e.setValue(value);
        }
    }

    /**
     * Convert the vector to an array of {@code double}s.
     * The array is independent from this vector data: the elements
     * are copied.
     *
     * @return an array containing a copy of the vector elements.
     */
    public double[] toArray() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.toArray_687");
        int dim = getDimension();
        double[] values = new double[dim];
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.RealVector.toArray_687", _mut33621, _mut33622, _mut33623, _mut33624, _mut33625); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.toArray_687");
            values[i] = getEntry(i);
        }
        return values;
    }

    /**
     * Creates a unit vector pointing in the direction of this vector.
     * The instance is not changed by this method.
     *
     * @return a unit vector pointing in direction of this vector.
     * @throws MathArithmeticException if the norm is zero.
     */
    public RealVector unitVector() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.unitVector_703");
        final double norm = getNorm();
        if (ROR_equals(norm, 0, "org.apache.commons.math3.linear.RealVector.unitVector_703", _mut33626, _mut33627, _mut33628, _mut33629, _mut33630)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        return mapDivide(norm);
    }

    /**
     * Converts this vector into a unit vector.
     * The instance itself is changed by this method.
     *
     * @throws MathArithmeticException if the norm is zero.
     */
    public void unitize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.unitize_717");
        final double norm = getNorm();
        if (ROR_equals(norm, 0, "org.apache.commons.math3.linear.RealVector.unitize_717", _mut33631, _mut33632, _mut33633, _mut33634, _mut33635)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        mapDivideToSelf(getNorm());
    }

    /**
     * Create a sparse iterator over the vector, which may omit some entries.
     * The ommitted entries are either exact zeroes (for dense implementations)
     * or are the entries which are not stored (for real sparse vectors).
     * No guarantees are made about order of iteration.
     *
     * <p>Note: derived classes are required to return an {@link Iterator} that
     * returns non-null {@link Entry} objects as long as {@link Iterator#hasNext()}
     * returns {@code true}.</p>
     *
     * @return a sparse iterator.
     */
    public Iterator<Entry> sparseIterator() {
        return new SparseEntryIterator();
    }

    /**
     * Generic dense iterator. Iteration is in increasing order
     * of the vector index.
     *
     * <p>Note: derived classes are required to return an {@link Iterator} that
     * returns non-null {@link Entry} objects as long as {@link Iterator#hasNext()}
     * returns {@code true}.</p>
     *
     * @return a dense iterator.
     */
    public Iterator<Entry> iterator() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.remove_781");
        final int dim = getDimension();
        return new Iterator<Entry>() {

            /**
             * Current index.
             */
            private int i = 0;

            /**
             * Current entry.
             */
            private Entry e = new Entry();

            /**
             * {@inheritDoc}
             */
            public boolean hasNext() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.hasNext_762");
                return ROR_less(i, dim, "org.apache.commons.math3.linear.RealVector.hasNext_762", _mut33636, _mut33637, _mut33638, _mut33639, _mut33640);
            }

            /**
             * {@inheritDoc}
             */
            public Entry next() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.next_767");
                if (ROR_less(i, dim, "org.apache.commons.math3.linear.RealVector.next_767", _mut33641, _mut33642, _mut33643, _mut33644, _mut33645)) {
                    e.setIndex(i++);
                    return e;
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all circumstances.
             */
            public void remove() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }
        };
    }

    /**
     * Acts as if implemented as:
     * <pre>
     *  return copy().mapToSelf(function);
     * </pre>
     * Returns a new vector. Does not change instance data.
     *
     * @param function Function to apply to each entry.
     * @return a new vector.
     */
    public RealVector map(UnivariateFunction function) {
        return copy().mapToSelf(function);
    }

    /**
     * Acts as if it is implemented as:
     * <pre>
     *  Entry e = null;
     *  for(Iterator<Entry> it = iterator(); it.hasNext(); e = it.next()) {
     *      e.setValue(function.value(e.getValue()));
     *  }
     * </pre>
     * Entries of this vector are modified in-place by this method.
     *
     * @param function Function to apply to each entry.
     * @return a reference to this vector.
     */
    public RealVector mapToSelf(UnivariateFunction function) {
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.mapToSelf_814");
            final Entry e = it.next();
            e.setValue(function.value(e.getValue()));
        }
        return this;
    }

    /**
     * Returns a new vector representing {@code a * this + b * y}, the linear
     * combination of {@code this} and {@code y}.
     * Returns a new vector. Does not change instance data.
     *
     * @param a Coefficient of {@code this}.
     * @param b Coefficient of {@code y}.
     * @param y Vector with which {@code this} is linearly combined.
     * @return a vector containing {@code a * this[i] + b * y[i]} for all
     * {@code i}.
     * @throws DimensionMismatchException if {@code y} is not the same size as
     * {@code this} vector.
     */
    public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
        return copy().combineToSelf(a, b, y);
    }

    /**
     * Updates {@code this} with the linear combination of {@code this} and
     * {@code y}.
     *
     * @param a Weight of {@code this}.
     * @param b Weight of {@code y}.
     * @param y Vector with which {@code this} is linearly combined.
     * @return {@code this}, with components equal to
     * {@code a * this[i] + b * y[i]} for all {@code i}.
     * @throws DimensionMismatchException if {@code y} is not the same size as
     * {@code this} vector.
     */
    public RealVector combineToSelf(double a, double b, RealVector y) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.combineToSelf_853");
        checkVectorDimensions(y);
        for (int i = 0; ROR_less(i, getDimension(), "org.apache.commons.math3.linear.RealVector.combineToSelf_853", _mut33658, _mut33659, _mut33660, _mut33661, _mut33662); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.combineToSelf_853");
            final double xi = getEntry(i);
            final double yi = y.getEntry(i);
            setEntry(i, AOR_plus(AOR_multiply(a, xi, "org.apache.commons.math3.linear.RealVector.combineToSelf_853", _mut33646, _mut33647, _mut33648, _mut33649), AOR_multiply(b, yi, "org.apache.commons.math3.linear.RealVector.combineToSelf_853", _mut33650, _mut33651, _mut33652, _mut33653), "org.apache.commons.math3.linear.RealVector.combineToSelf_853", _mut33654, _mut33655, _mut33656, _mut33657));
        }
        return this;
    }

    /**
     * Visits (but does not alter) all entries of this vector in default order
     * (increasing index).
     *
     * @param visitor the visitor to be used to process the entries of this
     * vector
     * @return the value returned by {@link RealVectorPreservingVisitor#end()}
     * at the end of the walk
     * @since 3.1
     */
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_874");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_874", _mut33663, _mut33664, _mut33665, _mut33666));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_874", _mut33667, _mut33668, _mut33669, _mut33670, _mut33671); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_874");
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
     * @return the value returned by {@link RealVectorPreservingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.1
     */
    public double walkInDefaultOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_896");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_896", _mut33672, _mut33673, _mut33674, _mut33675, _mut33676); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_896");
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
     * @return the value returned by {@link RealVectorPreservingVisitor#end()}
     * at the end of the walk
     * @since 3.1
     */
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor) {
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
     * @return the value returned by {@link RealVectorPreservingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.1
     */
    public double walkInOptimizedOrder(final RealVectorPreservingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /**
     * Visits (and possibly alters) all entries of this vector in default order
     * (increasing index).
     *
     * @param visitor the visitor to be used to process and modify the entries
     * of this vector
     * @return the value returned by {@link RealVectorChangingVisitor#end()}
     * at the end of the walk
     * @since 3.1
     */
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_954");
        final int dim = getDimension();
        visitor.start(dim, 0, AOR_minus(dim, 1, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_954", _mut33677, _mut33678, _mut33679, _mut33680));
        for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_954", _mut33681, _mut33682, _mut33683, _mut33684, _mut33685); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_954");
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
     * @return the value returned by {@link RealVectorChangingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.1
     */
    public double walkInDefaultOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_976");
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; ROR_less_equals(i, end, "org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_976", _mut33686, _mut33687, _mut33688, _mut33689, _mut33690); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.walkInDefaultOrder_976");
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
     * @return the value returned by {@link RealVectorChangingVisitor#end()}
     * at the end of the walk
     * @since 3.1
     */
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor) {
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
     * @return the value returned by {@link RealVectorChangingVisitor#end()}
     * at the end of the walk
     * @throws NumberIsTooSmallException if {@code end < start}.
     * @throws OutOfRangeException if the indices are not valid.
     * @since 3.1
     */
    public double walkInOptimizedOrder(final RealVectorChangingVisitor visitor, final int start, final int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /**
     * An entry in the vector.
     */
    protected class Entry {

        /**
         * Index of this entry.
         */
        private int index;

        /**
         * Simple constructor.
         */
        public Entry() {
            setIndex(0);
        }

        /**
         * Get the value of the entry.
         *
         * @return the value of the entry.
         */
        public double getValue() {
            return getEntry(getIndex());
        }

        /**
         * Set the value of the entry.
         *
         * @param value New value for the entry.
         */
        public void setValue(double value) {
            setEntry(getIndex(), value);
        }

        /**
         * Get the index of the entry.
         *
         * @return the index of the entry.
         */
        public int getIndex() {
            return index;
        }

        /**
         * Set the index of the entry.
         *
         * @param index New index for the entry.
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }

    /**
     * <p>
     * Test for the equality of two real vectors. If all coordinates of two real
     * vectors are exactly the same, and none are {@code NaN}, the two real
     * vectors are considered to be equal. {@code NaN} coordinates are
     * considered to affect globally the vector and be equals to each other -
     * i.e, if either (or all) coordinates of the real vector are equal to
     * {@code NaN}, the real vector is equal to a vector with all {@code NaN}
     * coordinates.
     * </p>
     * <p>
     * This method <em>must</em> be overriden by concrete subclasses of
     * {@link RealVector} (the current implementation throws an exception).
     * </p>
     *
     * @param other Object to test for equality.
     * @return {@code true} if two vector objects are equal, {@code false} if
     * {@code other} is null, not an instance of {@code RealVector}, or
     * not equal to this {@code RealVector} instance.
     * @throws MathUnsupportedOperationException if this method is not
     * overridden.
     */
    @Override
    public boolean equals(Object other) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    /**
     * {@inheritDoc}. This method <em>must</em> be overriden by concrete
     * subclasses of {@link RealVector} (current implementation throws an
     * exception).
     *
     * @throws MathUnsupportedOperationException if this method is not
     * overridden.
     */
    @Override
    public int hashCode() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    /**
     * This class should rarely be used, but is here to provide
     * a default implementation of sparseIterator(), which is implemented
     * by walking over the entries, skipping those that are zero.
     *
     * Concrete subclasses which are SparseVector implementations should
     * make their own sparse iterator, rather than using this one.
     *
     * This implementation might be useful for ArrayRealVector, when expensive
     * operations which preserve the default value are to be done on the entries,
     * and the fraction of non-default values is small (i.e. someone took a
     * SparseVector, and passed it into the copy-constructor of ArrayRealVector)
     */
    protected class SparseEntryIterator implements Iterator<Entry> {

        /**
         * Dimension of the vector.
         */
        private final int dim;

        /**
         * Last entry returned by {@link #next()}.
         */
        private Entry current;

        /**
         * Next entry for {@link #next()} to return.
         */
        private Entry next;

        /**
         * Simple constructor.
         */
        protected SparseEntryIterator() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.SparseEntryIterator_1135");
            dim = getDimension();
            current = new Entry();
            next = new Entry();
            if (ROR_equals(next.getValue(), 0, "org.apache.commons.math3.linear.RealVector.SparseEntryIterator_1135", _mut33691, _mut33692, _mut33693, _mut33694, _mut33695)) {
                advance(next);
            }
        }

        /**
         * Advance an entry up to the next nonzero one.
         *
         * @param e entry to advance.
         */
        protected void advance(Entry e) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.advance_1149");
            if (e == null) {
                return;
            }
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.advance_1149");
                e.setIndex(AOR_plus(e.getIndex(), 1, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33696, _mut33697, _mut33698, _mut33699));
            } while ((_mut33710 ? (ROR_less(e.getIndex(), dim, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33700, _mut33701, _mut33702, _mut33703, _mut33704) || ROR_equals(e.getValue(), 0, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33705, _mut33706, _mut33707, _mut33708, _mut33709)) : (ROR_less(e.getIndex(), dim, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33700, _mut33701, _mut33702, _mut33703, _mut33704) && ROR_equals(e.getValue(), 0, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33705, _mut33706, _mut33707, _mut33708, _mut33709))));
            if (ROR_greater_equals(e.getIndex(), dim, "org.apache.commons.math3.linear.RealVector.advance_1149", _mut33711, _mut33712, _mut33713, _mut33714, _mut33715)) {
                e.setIndex(-1);
            }
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.hasNext_1162");
            return ROR_greater_equals(next.getIndex(), 0, "org.apache.commons.math3.linear.RealVector.hasNext_1162", _mut33716, _mut33717, _mut33718, _mut33719, _mut33720);
        }

        /**
         * {@inheritDoc}
         */
        public Entry next() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealVector.next_1167");
            int index = next.getIndex();
            if (ROR_less(index, 0, "org.apache.commons.math3.linear.RealVector.next_1167", _mut33721, _mut33722, _mut33723, _mut33724, _mut33725)) {
                throw new NoSuchElementException();
            }
            current.setIndex(index);
            advance(next);
            return current;
        }

        /**
         * {@inheritDoc}
         *
         * @throws MathUnsupportedOperationException in all circumstances.
         */
        public void remove() throws MathUnsupportedOperationException {
            throw new MathUnsupportedOperationException();
        }
    }

    /**
     * Returns an unmodifiable view of the specified vector.
     * The returned vector has read-only access. An attempt to modify it will
     * result in a {@link MathUnsupportedOperationException}. However, the
     * returned vector is <em>not</em> immutable, since any modification of
     * {@code v} will also change the returned view.
     * For example, in the following piece of code
     * <pre>
     *     RealVector v = new ArrayRealVector(2);
     *     RealVector w = RealVector.unmodifiableRealVector(v);
     *     v.setEntry(0, 1.2);
     *     v.setEntry(1, -3.4);
     * </pre>
     * the changes will be seen in the {@code w} view of {@code v}.
     *
     * @param v Vector for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of {@code v}.
     */
    public static RealVector unmodifiableRealVector(final RealVector v) {
        /**
         * This anonymous class is an implementation of {@link RealVector}
         * with read-only access.
         * It wraps any {@link RealVector}, and exposes all methods which
         * do not modify it. Invoking methods which should normally result
         * in the modification of the calling {@link RealVector} results in
         * a {@link MathUnsupportedOperationException}. It should be noted
         * that {@link UnmodifiableVector} is <em>not</em> immutable.
         */
        return new RealVector() {

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all circumstances.
             */
            @Override
            public RealVector mapToSelf(UnivariateFunction function) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector map(UnivariateFunction function) {
                return v.map(function);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Iterator<Entry> iterator() {
                final Iterator<Entry> i = v.iterator();
                return new Iterator<Entry>() {

                    /**
                     * The current entry.
                     */
                    private final UnmodifiableEntry e = new UnmodifiableEntry();

                    /**
                     * {@inheritDoc}
                     */
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    /**
                     * {@inheritDoc}
                     */
                    public Entry next() {
                        e.setIndex(i.next().getIndex());
                        return e;
                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @throws MathUnsupportedOperationException in all
                     * circumstances.
                     */
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Iterator<Entry> sparseIterator() {
                final Iterator<Entry> i = v.sparseIterator();
                return new Iterator<Entry>() {

                    /**
                     * The current entry.
                     */
                    private final UnmodifiableEntry e = new UnmodifiableEntry();

                    /**
                     * {@inheritDoc}
                     */
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    /**
                     * {@inheritDoc}
                     */
                    public Entry next() {
                        e.setIndex(i.next().getIndex());
                        return e;
                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @throws MathUnsupportedOperationException in all
                     * circumstances.
                     */
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector copy() {
                return v.copy();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector add(RealVector w) throws DimensionMismatchException {
                return v.add(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector subtract(RealVector w) throws DimensionMismatchException {
                return v.subtract(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector mapAdd(double d) {
                return v.mapAdd(d);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public RealVector mapAddToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector mapSubtract(double d) {
                return v.mapSubtract(d);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public RealVector mapSubtractToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector mapMultiply(double d) {
                return v.mapMultiply(d);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public RealVector mapMultiplyToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector mapDivide(double d) {
                return v.mapDivide(d);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public RealVector mapDivideToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector ebeMultiply(RealVector w) throws DimensionMismatchException {
                return v.ebeMultiply(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector ebeDivide(RealVector w) throws DimensionMismatchException {
                return v.ebeDivide(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double dotProduct(RealVector w) throws DimensionMismatchException {
                return v.dotProduct(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double cosine(RealVector w) throws DimensionMismatchException, MathArithmeticException {
                return v.cosine(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getNorm() {
                return v.getNorm();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getL1Norm() {
                return v.getL1Norm();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getLInfNorm() {
                return v.getLInfNorm();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getDistance(RealVector w) throws DimensionMismatchException {
                return v.getDistance(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getL1Distance(RealVector w) throws DimensionMismatchException {
                return v.getL1Distance(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getLInfDistance(RealVector w) throws DimensionMismatchException {
                return v.getLInfDistance(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector unitVector() throws MathArithmeticException {
                return v.unitVector();
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public void unitize() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealMatrix outerProduct(RealVector w) {
                return v.outerProduct(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double getEntry(int index) throws OutOfRangeException {
                return v.getEntry(index);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public void setEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public void addToEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public int getDimension() {
                return v.getDimension();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector append(RealVector w) {
                return v.append(w);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector append(double d) {
                return v.append(d);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
                return v.getSubVector(index, n);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public void setSubVector(int index, RealVector w) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public void set(double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double[] toArray() {
                return v.toArray();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean isNaN() {
                return v.isNaN();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean isInfinite() {
                return v.isInfinite();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
                return v.combine(a, b, y);
            }

            /**
             * {@inheritDoc}
             *
             * @throws MathUnsupportedOperationException in all
             * circumstances.
             */
            @Override
            public RealVector combineToSelf(double a, double b, RealVector y) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /**
             * An entry in the vector.
             */
            class UnmodifiableEntry extends Entry {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public double getValue() {
                    return v.getEntry(getIndex());
                }

                /**
                 * {@inheritDoc}
                 *
                 * @throws MathUnsupportedOperationException in all
                 * circumstances.
                 */
                @Override
                public void setValue(double value) throws MathUnsupportedOperationException {
                    throw new MathUnsupportedOperationException();
                }
            }
        };
    }
}
