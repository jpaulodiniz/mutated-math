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
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class is a re-implementation of {@link Vector3D} using {@link RealFieldElement}.
 * <p>Instance of this class are guaranteed to be immutable.</p>
 * @param <T> the type of the field elements
 * @since 3.2
 */
public class FieldVector3D<T extends RealFieldElement<T>> implements Serializable {

    @Conditional
    public static boolean _mut79621 = false, _mut79622 = false, _mut79623 = false, _mut79624 = false, _mut79625 = false, _mut79626 = false, _mut79627 = false, _mut79628 = false, _mut79629 = false, _mut79630 = false, _mut79631 = false, _mut79632 = false, _mut79633 = false, _mut79634 = false, _mut79635 = false, _mut79636 = false, _mut79637 = false, _mut79638 = false, _mut79639 = false, _mut79640 = false, _mut79641 = false, _mut79642 = false, _mut79643 = false, _mut79644 = false, _mut79645 = false, _mut79646 = false, _mut79647 = false, _mut79648 = false, _mut79649 = false, _mut79650 = false, _mut79651 = false, _mut79652 = false, _mut79653 = false, _mut79654 = false, _mut79655 = false, _mut79656 = false, _mut79657 = false, _mut79658 = false, _mut79659 = false, _mut79660 = false, _mut79661 = false, _mut79662 = false, _mut79663 = false, _mut79664 = false, _mut79665 = false, _mut79666 = false, _mut79667 = false, _mut79668 = false, _mut79669 = false, _mut79670 = false, _mut79671 = false, _mut79672 = false, _mut79673 = false, _mut79674 = false, _mut79675 = false, _mut79676 = false, _mut79677 = false, _mut79678 = false, _mut79679 = false, _mut79680 = false, _mut79681 = false, _mut79682 = false, _mut79683 = false, _mut79684 = false, _mut79685 = false, _mut79686 = false, _mut79687 = false, _mut79688 = false, _mut79689 = false, _mut79690 = false, _mut79691 = false, _mut79692 = false, _mut79693 = false, _mut79694 = false, _mut79695 = false, _mut79696 = false, _mut79697 = false, _mut79698 = false, _mut79699 = false, _mut79700 = false, _mut79701 = false, _mut79702 = false, _mut79703 = false, _mut79704 = false, _mut79705 = false, _mut79706 = false, _mut79707 = false, _mut79708 = false, _mut79709 = false, _mut79710 = false, _mut79711 = false, _mut79712 = false, _mut79713 = false, _mut79714 = false, _mut79715 = false, _mut79716 = false, _mut79717 = false, _mut79718 = false, _mut79719 = false, _mut79720 = false, _mut79721 = false, _mut79722 = false, _mut79723 = false, _mut79724 = false, _mut79725 = false, _mut79726 = false, _mut79727 = false, _mut79728 = false, _mut79729 = false, _mut79730 = false, _mut79731 = false, _mut79732 = false, _mut79733 = false, _mut79734 = false, _mut79735 = false, _mut79736 = false, _mut79737 = false, _mut79738 = false, _mut79739 = false, _mut79740 = false, _mut79741 = false, _mut79742 = false, _mut79743 = false, _mut79744 = false, _mut79745 = false, _mut79746 = false, _mut79747 = false, _mut79748 = false, _mut79749 = false, _mut79750 = false, _mut79751 = false, _mut79752 = false, _mut79753 = false, _mut79754 = false, _mut79755 = false, _mut79756 = false, _mut79757 = false, _mut79758 = false, _mut79759 = false, _mut79760 = false, _mut79761 = false, _mut79762 = false, _mut79763 = false, _mut79764 = false, _mut79765 = false, _mut79766 = false, _mut79767 = false, _mut79768 = false, _mut79769 = false, _mut79770 = false, _mut79771 = false, _mut79772 = false, _mut79773 = false, _mut79774 = false, _mut79775 = false, _mut79776 = false, _mut79777 = false, _mut79778 = false, _mut79779 = false, _mut79780 = false, _mut79781 = false, _mut79782 = false, _mut79783 = false, _mut79784 = false, _mut79785 = false, _mut79786 = false, _mut79787 = false, _mut79788 = false, _mut79789 = false, _mut79790 = false, _mut79791 = false, _mut79792 = false, _mut79793 = false, _mut79794 = false, _mut79795 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20130224L;

    /**
     * Abscissa.
     */
    private final T x;

    /**
     * Ordinate.
     */
    private final T y;

    /**
     * Height.
     */
    private final T z;

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param x abscissa
     * @param y ordinate
     * @param z height
     * @see #getX()
     * @see #getY()
     * @see #getZ()
     */
    public FieldVector3D(final T x, final T y, final T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param v coordinates array
     * @exception DimensionMismatchException if array does not have 3 elements
     * @see #toArray()
     */
    public FieldVector3D(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.FieldVector3D_71");
        if (ROR_not_equals(v.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.FieldVector3D_71", _mut79621, _mut79622, _mut79623, _mut79624, _mut79625)) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    /**
     * Simple constructor.
     * Build a vector from its azimuthal coordinates
     * @param alpha azimuth (&alpha;) around Z
     *              (0 is +X, &pi;/2 is +Y, &pi; is -X and 3&pi;/2 is -Y)
     * @param delta elevation (&delta;) above (XY) plane, from -&pi;/2 to +&pi;/2
     * @see #getAlpha()
     * @see #getDelta()
     */
    public FieldVector3D(final T alpha, final T delta) {
        T cosDelta = delta.cos();
        this.x = alpha.cos().multiply(cosDelta);
        this.y = alpha.sin().multiply(cosDelta);
        this.z = delta.sin();
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final T a, final FieldVector3D<T> u) {
        this.x = a.multiply(u.x);
        this.y = a.multiply(u.y);
        this.z = a.multiply(u.z);
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final T a, final Vector3D u) {
        this.x = a.multiply(u.getX());
        this.y = a.multiply(u.getY());
        this.z = a.multiply(u.getZ());
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public FieldVector3D(final double a, final FieldVector3D<T> u) {
        this.x = u.x.multiply(a);
        this.y = u.y.multiply(a);
        this.z = u.z.multiply(a);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2, final T a3, final FieldVector3D<T> u3) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2, final T a3, final Vector3D u3) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2, final double a3, final FieldVector3D<T> u3) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final T a1, final FieldVector3D<T> u1, final T a2, final FieldVector3D<T> u2, final T a3, final FieldVector3D<T> u3, final T a4, final FieldVector3D<T> u4) {
        final T prototype = a1;
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final T a1, final Vector3D u1, final T a2, final Vector3D u2, final T a3, final Vector3D u3, final T a4, final Vector3D u4) {
        final T prototype = a1;
        this.x = prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
        this.y = prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
        this.z = prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public FieldVector3D(final double a1, final FieldVector3D<T> u1, final double a2, final FieldVector3D<T> u2, final double a3, final FieldVector3D<T> u3, final double a4, final FieldVector3D<T> u4) {
        final T prototype = u1.getX();
        this.x = prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    /**
     * Get the abscissa of the vector.
     * @return abscissa of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getX() {
        return x;
    }

    /**
     * Get the ordinate of the vector.
     * @return ordinate of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getY() {
        return y;
    }

    /**
     * Get the height of the vector.
     * @return height of the vector
     * @see #FieldVector3D(RealFieldElement, RealFieldElement, RealFieldElement)
     */
    public T getZ() {
        return z;
    }

    /**
     * Get the vector coordinates as a dimension 3 array.
     * @return vector coordinates
     * @see #FieldVector3D(RealFieldElement[])
     */
    public T[] toArray() {
        final T[] array = MathArrays.buildArray(x.getField(), 3);
        array[0] = x;
        array[1] = y;
        array[2] = z;
        return array;
    }

    /**
     * Convert to a constant vector without derivatives.
     * @return a constant vector
     */
    public Vector3D toVector3D() {
        return new Vector3D(x.getReal(), y.getReal(), z.getReal());
    }

    /**
     * Get the L<sub>1</sub> norm for the vector.
     * @return L<sub>1</sub> norm for the vector
     */
    public T getNorm1() {
        return x.abs().add(y.abs()).add(z.abs());
    }

    /**
     * Get the L<sub>2</sub> norm for the vector.
     * @return Euclidean norm for the vector
     */
    public T getNorm() {
        // there are no cancellation problems here, so we use the straightforward formula
        return x.multiply(x).add(y.multiply(y)).add(z.multiply(z)).sqrt();
    }

    /**
     * Get the square of the norm for the vector.
     * @return square of the Euclidean norm for the vector
     */
    public T getNormSq() {
        // there are no cancellation problems here, so we use the straightforward formula
        return x.multiply(x).add(y.multiply(y)).add(z.multiply(z));
    }

    /**
     * Get the L<sub>&infin;</sub> norm for the vector.
     * @return L<sub>&infin;</sub> norm for the vector
     */
    public T getNormInf() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.getNormInf_371");
        final T xAbs = x.abs();
        final T yAbs = y.abs();
        final T zAbs = z.abs();
        if (ROR_less_equals(xAbs.getReal(), yAbs.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.getNormInf_371", _mut79626, _mut79627, _mut79628, _mut79629, _mut79630)) {
            if (ROR_less_equals(yAbs.getReal(), zAbs.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.getNormInf_371", _mut79636, _mut79637, _mut79638, _mut79639, _mut79640)) {
                return zAbs;
            } else {
                return yAbs;
            }
        } else {
            if (ROR_less_equals(xAbs.getReal(), zAbs.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.getNormInf_371", _mut79631, _mut79632, _mut79633, _mut79634, _mut79635)) {
                return zAbs;
            } else {
                return xAbs;
            }
        }
    }

    /**
     * Get the azimuth of the vector.
     * @return azimuth (&alpha;) of the vector, between -&pi; and +&pi;
     * @see #FieldVector3D(RealFieldElement, RealFieldElement)
     */
    public T getAlpha() {
        return y.atan2(x);
    }

    /**
     * Get the elevation of the vector.
     * @return elevation (&delta;) of the vector, between -&pi;/2 and +&pi;/2
     * @see #FieldVector3D(RealFieldElement, RealFieldElement)
     */
    public T getDelta() {
        return z.divide(getNorm()).asin();
    }

    /**
     * Add a vector to the instance.
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final FieldVector3D<T> v) {
        return new FieldVector3D<T>(x.add(v.x), y.add(v.y), z.add(v.z));
    }

    /**
     * Add a vector to the instance.
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final Vector3D v) {
        return new FieldVector3D<T>(x.add(v.getX()), y.add(v.getY()), z.add(v.getZ()));
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final T factor, final FieldVector3D<T> v) {
        return new FieldVector3D<T>(x.getField().getOne(), this, factor, v);
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final T factor, final Vector3D v) {
        return new FieldVector3D<T>(x.add(factor.multiply(v.getX())), y.add(factor.multiply(v.getY())), z.add(factor.multiply(v.getZ())));
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final double factor, final FieldVector3D<T> v) {
        return new FieldVector3D<T>(1.0, this, factor, v);
    }

    /**
     * Add a scaled vector to the instance.
     * @param factor scale factor to apply to v before adding it
     * @param v vector to add
     * @return a new vector
     */
    public FieldVector3D<T> add(final double factor, final Vector3D v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.add_456");
        return new FieldVector3D<T>(x.add(AOR_multiply(factor, v.getX(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.add_456", _mut79641, _mut79642, _mut79643, _mut79644)), y.add(AOR_multiply(factor, v.getY(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.add_456", _mut79645, _mut79646, _mut79647, _mut79648)), z.add(AOR_multiply(factor, v.getZ(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.add_456", _mut79649, _mut79650, _mut79651, _mut79652)));
    }

    /**
     * Subtract a vector from the instance.
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final FieldVector3D<T> v) {
        return new FieldVector3D<T>(x.subtract(v.x), y.subtract(v.y), z.subtract(v.z));
    }

    /**
     * Subtract a vector from the instance.
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final Vector3D v) {
        return new FieldVector3D<T>(x.subtract(v.getX()), y.subtract(v.getY()), z.subtract(v.getZ()));
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final T factor, final FieldVector3D<T> v) {
        return new FieldVector3D<T>(x.getField().getOne(), this, factor.negate(), v);
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final T factor, final Vector3D v) {
        return new FieldVector3D<T>(x.subtract(factor.multiply(v.getX())), y.subtract(factor.multiply(v.getY())), z.subtract(factor.multiply(v.getZ())));
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final double factor, final FieldVector3D<T> v) {
        return new FieldVector3D<T>(1.0, this, -factor, v);
    }

    /**
     * Subtract a scaled vector from the instance.
     * @param factor scale factor to apply to v before subtracting it
     * @param v vector to subtract
     * @return a new vector
     */
    public FieldVector3D<T> subtract(final double factor, final Vector3D v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.subtract_512");
        return new FieldVector3D<T>(x.subtract(AOR_multiply(factor, v.getX(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.subtract_512", _mut79653, _mut79654, _mut79655, _mut79656)), y.subtract(AOR_multiply(factor, v.getY(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.subtract_512", _mut79657, _mut79658, _mut79659, _mut79660)), z.subtract(AOR_multiply(factor, v.getZ(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.subtract_512", _mut79661, _mut79662, _mut79663, _mut79664)));
    }

    /**
     * Get a normalized vector aligned with the instance.
     * @return a new normalized vector
     * @exception MathArithmeticException if the norm is zero
     */
    public FieldVector3D<T> normalize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.normalize_522");
        final T s = getNorm();
        if (ROR_equals(s.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.normalize_522", _mut79665, _mut79666, _mut79667, _mut79668, _mut79669)) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
        }
        return scalarMultiply(s.reciprocal());
    }

    /**
     * Get a vector orthogonal to the instance.
     * <p>There are an infinite number of normalized vectors orthogonal
     * to the instance. This method picks up one of them almost
     * arbitrarily. It is useful when one needs to compute a reference
     * frame with one of the axes in a predefined direction. The
     * following example shows how to build a frame having the k axis
     * aligned with the known vector u :
     * <pre><code>
     *   Vector3D k = u.normalize();
     *   Vector3D i = k.orthogonal();
     *   Vector3D j = Vector3D.crossProduct(k, i);
     * </code></pre></p>
     * @return a new normalized vector orthogonal to the instance
     * @exception MathArithmeticException if the norm of the instance is null
     */
    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.orthogonal_545");
        final double threshold = AOR_multiply(0.6, getNorm().getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.orthogonal_545", _mut79670, _mut79671, _mut79672, _mut79673);
        if (ROR_equals(threshold, 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.orthogonal_545", _mut79674, _mut79675, _mut79676, _mut79677, _mut79678)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        if (ROR_less_equals(FastMath.abs(x.getReal()), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.orthogonal_545", _mut79679, _mut79680, _mut79681, _mut79682, _mut79683)) {
            final T inverse = y.multiply(y).add(z.multiply(z)).sqrt().reciprocal();
            return new FieldVector3D<T>(inverse.getField().getZero(), inverse.multiply(z), inverse.multiply(y).negate());
        } else if (ROR_less_equals(FastMath.abs(y.getReal()), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.orthogonal_545", _mut79684, _mut79685, _mut79686, _mut79687, _mut79688)) {
            final T inverse = x.multiply(x).add(z.multiply(z)).sqrt().reciprocal();
            return new FieldVector3D<T>(inverse.multiply(z).negate(), inverse.getField().getZero(), inverse.multiply(x));
        } else {
            final T inverse = x.multiply(x).add(y.multiply(y)).sqrt().reciprocal();
            return new FieldVector3D<T>(inverse.multiply(y), inverse.multiply(x).negate(), inverse.getField().getZero());
        }
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final FieldVector3D<T> v1, final FieldVector3D<T> v2) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577");
        final T normProduct = v1.getNorm().multiply(v2.getNorm());
        if (ROR_equals(normProduct.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79689, _mut79690, _mut79691, _mut79692, _mut79693)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        final T dot = dotProduct(v1, v2);
        final double threshold = AOR_multiply(normProduct.getReal(), 0.9999, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79694, _mut79695, _mut79696, _mut79697);
        if ((_mut79708 ? ((ROR_less(dot.getReal(), -threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79698, _mut79699, _mut79700, _mut79701, _mut79702)) && (ROR_greater(dot.getReal(), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79703, _mut79704, _mut79705, _mut79706, _mut79707))) : ((ROR_less(dot.getReal(), -threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79698, _mut79699, _mut79700, _mut79701, _mut79702)) || (ROR_greater(dot.getReal(), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79703, _mut79704, _mut79705, _mut79706, _mut79707))))) {
            // the vectors are almost aligned, compute using the sine
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            if (ROR_greater_equals(dot.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_577", _mut79709, _mut79710, _mut79711, _mut79712, _mut79713)) {
                return v3.getNorm().divide(normProduct).asin();
            }
            return v3.getNorm().divide(normProduct).asin().subtract(FastMath.PI).negate();
        }
        // the vectors are sufficiently separated to use the cosine
        return dot.divide(normProduct).acos();
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final FieldVector3D<T> v1, final Vector3D v2) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613");
        final T normProduct = v1.getNorm().multiply(v2.getNorm());
        if (ROR_equals(normProduct.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79714, _mut79715, _mut79716, _mut79717, _mut79718)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        final T dot = dotProduct(v1, v2);
        final double threshold = AOR_multiply(normProduct.getReal(), 0.9999, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79719, _mut79720, _mut79721, _mut79722);
        if ((_mut79733 ? ((ROR_less(dot.getReal(), -threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79723, _mut79724, _mut79725, _mut79726, _mut79727)) && (ROR_greater(dot.getReal(), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79728, _mut79729, _mut79730, _mut79731, _mut79732))) : ((ROR_less(dot.getReal(), -threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79723, _mut79724, _mut79725, _mut79726, _mut79727)) || (ROR_greater(dot.getReal(), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79728, _mut79729, _mut79730, _mut79731, _mut79732))))) {
            // the vectors are almost aligned, compute using the sine
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            if (ROR_greater_equals(dot.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.angle_613", _mut79734, _mut79735, _mut79736, _mut79737, _mut79738)) {
                return v3.getNorm().divide(normProduct).asin();
            }
            return v3.getNorm().divide(normProduct).asin().subtract(FastMath.PI).negate();
        }
        // the vectors are sufficiently separated to use the cosine
        return dot.divide(normProduct).acos();
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static <T extends RealFieldElement<T>> T angle(final Vector3D v1, final FieldVector3D<T> v2) throws MathArithmeticException {
        return angle(v2, v1);
    }

    /**
     * Get the opposite of the instance.
     * @return a new vector which is opposite to the instance
     */
    public FieldVector3D<T> negate() {
        return new FieldVector3D<T>(x.negate(), y.negate(), z.negate());
    }

    /**
     * Multiply the instance by a scalar.
     * @param a scalar
     * @return a new vector
     */
    public FieldVector3D<T> scalarMultiply(final T a) {
        return new FieldVector3D<T>(x.multiply(a), y.multiply(a), z.multiply(a));
    }

    /**
     * Multiply the instance by a scalar.
     * @param a scalar
     * @return a new vector
     */
    public FieldVector3D<T> scalarMultiply(final double a) {
        return new FieldVector3D<T>(x.multiply(a), y.multiply(a), z.multiply(a));
    }

    /**
     * Returns true if any coordinate of this vector is NaN; false otherwise
     * @return  true if any coordinate of this vector is NaN; false otherwise
     */
    public boolean isNaN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.isNaN_681");
        return (_mut79740 ? ((_mut79739 ? (Double.isNaN(x.getReal()) && Double.isNaN(y.getReal())) : (Double.isNaN(x.getReal()) || Double.isNaN(y.getReal()))) && Double.isNaN(z.getReal())) : ((_mut79739 ? (Double.isNaN(x.getReal()) && Double.isNaN(y.getReal())) : (Double.isNaN(x.getReal()) || Double.isNaN(y.getReal()))) || Double.isNaN(z.getReal())));
    }

    /**
     * Returns true if any coordinate of this vector is infinite and none are NaN;
     * false otherwise
     * @return  true if any coordinate of this vector is infinite and none are NaN;
     * false otherwise
     */
    public boolean isInfinite() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.isInfinite_691");
        return (_mut79743 ? (!isNaN() || ((_mut79742 ? ((_mut79741 ? (Double.isInfinite(x.getReal()) && Double.isInfinite(y.getReal())) : (Double.isInfinite(x.getReal()) || Double.isInfinite(y.getReal()))) && Double.isInfinite(z.getReal())) : ((_mut79741 ? (Double.isInfinite(x.getReal()) && Double.isInfinite(y.getReal())) : (Double.isInfinite(x.getReal()) || Double.isInfinite(y.getReal()))) || Double.isInfinite(z.getReal()))))) : (!isNaN() && ((_mut79742 ? ((_mut79741 ? (Double.isInfinite(x.getReal()) && Double.isInfinite(y.getReal())) : (Double.isInfinite(x.getReal()) || Double.isInfinite(y.getReal()))) && Double.isInfinite(z.getReal())) : ((_mut79741 ? (Double.isInfinite(x.getReal()) && Double.isInfinite(y.getReal())) : (Double.isInfinite(x.getReal()) || Double.isInfinite(y.getReal()))) || Double.isInfinite(z.getReal()))))));
    }

    /**
     * Test for the equality of two 3D vectors.
     * <p>
     * If all coordinates of two 3D vectors are exactly the same, and none of their
     * {@link RealFieldElement#getReal() real part} are <code>NaN</code>, the
     * two 3D vectors are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) real part of the
     * coordinates of the 3D vector are <code>NaN</code>, the 3D vector is <code>NaN</code>.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two 3D vector objects are equal, false if
     *         object is null, not an instance of Vector3D, or
     *         not equal to this Vector3D instance
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.equals_714");
        if (this == other) {
            return true;
        }
        if (other instanceof FieldVector3D) {
            @SuppressWarnings("unchecked")
            final FieldVector3D<T> rhs = (FieldVector3D<T>) other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return (_mut79745 ? ((_mut79744 ? (x.equals(rhs.x) || y.equals(rhs.y)) : (x.equals(rhs.x) && y.equals(rhs.y))) || z.equals(rhs.z)) : ((_mut79744 ? (x.equals(rhs.x) || y.equals(rhs.y)) : (x.equals(rhs.x) && y.equals(rhs.y))) && z.equals(rhs.z)));
        }
        return false;
    }

    /**
     * Get a hashCode for the 3D vector.
     * <p>
     * All NaN values have the same hash code.</p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741");
        if (isNaN()) {
            return 409;
        }
        return AOR_multiply(311, (AOR_plus(AOR_plus(AOR_multiply(107, x.hashCode(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741", _mut79746, _mut79747, _mut79748, _mut79749), AOR_multiply(83, y.hashCode(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741", _mut79750, _mut79751, _mut79752, _mut79753), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741", _mut79754, _mut79755, _mut79756, _mut79757), z.hashCode(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741", _mut79758, _mut79759, _mut79760, _mut79761)), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.hashCode_741", _mut79762, _mut79763, _mut79764, _mut79765);
    }

    /**
     * Compute the dot-product of the instance and another vector.
     * <p>
     * The implementation uses specific multiplication and addition
     * algorithms to preserve accuracy and reduce cancellation effects.
     * It should be very accurate even for nearly orthogonal vectors.
     * </p>
     * @see MathArrays#linearCombination(double, double, double, double, double, double)
     * @param v second vector
     * @return the dot product this.v
     */
    public T dotProduct(final FieldVector3D<T> v) {
        return x.linearCombination(x, v.x, y, v.y, z, v.z);
    }

    /**
     * Compute the dot-product of the instance and another vector.
     * <p>
     * The implementation uses specific multiplication and addition
     * algorithms to preserve accuracy and reduce cancellation effects.
     * It should be very accurate even for nearly orthogonal vectors.
     * </p>
     * @see MathArrays#linearCombination(double, double, double, double, double, double)
     * @param v second vector
     * @return the dot product this.v
     */
    public T dotProduct(final Vector3D v) {
        return x.linearCombination(v.getX(), x, v.getY(), y, v.getZ(), z);
    }

    /**
     * Compute the cross-product of the instance with another vector.
     * @param v other vector
     * @return the cross product this ^ v as a new Vector3D
     */
    public FieldVector3D<T> crossProduct(final FieldVector3D<T> v) {
        return new FieldVector3D<T>(x.linearCombination(y, v.z, z.negate(), v.y), y.linearCombination(z, v.x, x.negate(), v.z), z.linearCombination(x, v.y, y.negate(), v.x));
    }

    /**
     * Compute the cross-product of the instance with another vector.
     * @param v other vector
     * @return the cross product this ^ v as a new Vector3D
     */
    public FieldVector3D<T> crossProduct(final Vector3D v) {
        return new FieldVector3D<T>(x.linearCombination(v.getZ(), y, -v.getY(), z), y.linearCombination(v.getX(), z, -v.getZ(), x), z.linearCombination(v.getY(), x, -v.getX(), y));
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>1</sub> norm
     */
    public T distance1(final FieldVector3D<T> v) {
        final T dx = v.x.subtract(x).abs();
        final T dy = v.y.subtract(y).abs();
        final T dz = v.z.subtract(z).abs();
        return dx.add(dy).add(dz);
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>1</sub> norm
     */
    public T distance1(final Vector3D v) {
        final T dx = x.subtract(v.getX()).abs();
        final T dy = y.subtract(v.getY()).abs();
        final T dz = z.subtract(v.getZ()).abs();
        return dx.add(dy).add(dz);
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>2</sub> norm
     */
    public T distance(final FieldVector3D<T> v) {
        final T dx = v.x.subtract(x);
        final T dy = v.y.subtract(y);
        final T dz = v.z.subtract(z);
        return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt();
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>2</sub> norm
     */
    public T distance(final Vector3D v) {
        final T dx = x.subtract(v.getX());
        final T dy = y.subtract(v.getY());
        final T dz = z.subtract(v.getZ());
        return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt();
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>&infin;</sub> norm
     */
    public T distanceInf(final FieldVector3D<T> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_860");
        final T dx = v.x.subtract(x).abs();
        final T dy = v.y.subtract(y).abs();
        final T dz = v.z.subtract(z).abs();
        if (ROR_less_equals(dx.getReal(), dy.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_860", _mut79766, _mut79767, _mut79768, _mut79769, _mut79770)) {
            if (ROR_less_equals(dy.getReal(), dz.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_860", _mut79776, _mut79777, _mut79778, _mut79779, _mut79780)) {
                return dz;
            } else {
                return dy;
            }
        } else {
            if (ROR_less_equals(dx.getReal(), dz.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_860", _mut79771, _mut79772, _mut79773, _mut79774, _mut79775)) {
                return dz;
            } else {
                return dx;
            }
        }
    }

    /**
     * Compute the distance between the instance and another vector according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the distance between the instance and p according to the L<sub>&infin;</sub> norm
     */
    public T distanceInf(final Vector3D v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_886");
        final T dx = x.subtract(v.getX()).abs();
        final T dy = y.subtract(v.getY()).abs();
        final T dz = z.subtract(v.getZ()).abs();
        if (ROR_less_equals(dx.getReal(), dy.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_886", _mut79781, _mut79782, _mut79783, _mut79784, _mut79785)) {
            if (ROR_less_equals(dy.getReal(), dz.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_886", _mut79791, _mut79792, _mut79793, _mut79794, _mut79795)) {
                return dz;
            } else {
                return dy;
            }
        } else {
            if (ROR_less_equals(dx.getReal(), dz.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D.distanceInf_886", _mut79786, _mut79787, _mut79788, _mut79789, _mut79790)) {
                return dz;
            } else {
                return dx;
            }
        }
    }

    /**
     * Compute the square of the distance between the instance and another vector.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the square of the distance between the instance and p
     */
    public T distanceSq(final FieldVector3D<T> v) {
        final T dx = v.x.subtract(x);
        final T dy = v.y.subtract(y);
        final T dz = v.z.subtract(z);
        return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
    }

    /**
     * Compute the square of the distance between the instance and another vector.
     * <p>Calling this method is equivalent to calling:
     * <code>q.subtract(p).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v second vector
     * @return the square of the distance between the instance and p
     */
    public T distanceSq(final Vector3D v) {
        final T dx = x.subtract(v.getX());
        final T dy = y.subtract(v.getY());
        final T dz = z.subtract(v.getZ());
        return dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.dotProduct(v2);
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.dotProduct(v2);
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the dot product v1.v2
     */
    public static <T extends RealFieldElement<T>> T dotProduct(final Vector3D v1, final FieldVector3D<T> v2) {
        return v2.dotProduct(v1);
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.crossProduct(v2);
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.crossProduct(v2);
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final Vector3D v1, final FieldVector3D<T> v2) {
        return new FieldVector3D<T>(v2.x.linearCombination(v1.getY(), v2.z, -v1.getZ(), v2.y), v2.y.linearCombination(v1.getZ(), v2.x, -v1.getX(), v2.z), v2.z.linearCombination(v1.getX(), v2.y, -v1.getY(), v2.x));
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.distance1(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.distance1(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance1(final Vector3D v1, final FieldVector3D<T> v2) {
        return v2.distance1(v1);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.distance(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.distance(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distance(final Vector3D v1, final FieldVector3D<T> v2) {
        return v2.distance(v1);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.distanceInf(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.distanceInf(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static <T extends RealFieldElement<T>> T distanceInf(final Vector3D v1, final FieldVector3D<T> v2) {
        return v2.distanceInf(v1);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final FieldVector3D<T> v1, final FieldVector3D<T> v2) {
        return v1.distanceSq(v2);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final FieldVector3D<T> v1, final Vector3D v2) {
        return v1.distanceSq(v2);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @param <T> the type of the field elements
     * @return the square of the distance between v1 and v2
     */
    public static <T extends RealFieldElement<T>> T distanceSq(final Vector3D v1, final FieldVector3D<T> v2) {
        return v2.distanceSq(v1);
    }

    /**
     * Get a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return Vector3DFormat.getInstance().format(toVector3D());
    }

    /**
     * Get a string representation of this vector.
     * @param format the custom format for components
     * @return a string representation of this vector
     */
    public String toString(final NumberFormat format) {
        return new Vector3DFormat(format).format(toVector3D());
    }
}
