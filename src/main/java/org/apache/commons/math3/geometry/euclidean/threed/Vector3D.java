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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements vectors in a three-dimensional space.
 * <p>Instance of this class are guaranteed to be immutable.</p>
 * @since 1.2
 */
public class Vector3D implements Serializable, Vector<Euclidean3D> {

    @Conditional
    public static boolean _mut83495 = false, _mut83496 = false, _mut83497 = false, _mut83498 = false, _mut83499 = false, _mut83500 = false, _mut83501 = false, _mut83502 = false, _mut83503 = false, _mut83504 = false, _mut83505 = false, _mut83506 = false, _mut83507 = false, _mut83508 = false, _mut83509 = false, _mut83510 = false, _mut83511 = false, _mut83512 = false, _mut83513 = false, _mut83514 = false, _mut83515 = false, _mut83516 = false, _mut83517 = false, _mut83518 = false, _mut83519 = false, _mut83520 = false, _mut83521 = false, _mut83522 = false, _mut83523 = false, _mut83524 = false, _mut83525 = false, _mut83526 = false, _mut83527 = false, _mut83528 = false, _mut83529 = false, _mut83530 = false, _mut83531 = false, _mut83532 = false, _mut83533 = false, _mut83534 = false, _mut83535 = false, _mut83536 = false, _mut83537 = false, _mut83538 = false, _mut83539 = false, _mut83540 = false, _mut83541 = false, _mut83542 = false, _mut83543 = false, _mut83544 = false, _mut83545 = false, _mut83546 = false, _mut83547 = false, _mut83548 = false, _mut83549 = false, _mut83550 = false, _mut83551 = false, _mut83552 = false, _mut83553 = false, _mut83554 = false, _mut83555 = false, _mut83556 = false, _mut83557 = false, _mut83558 = false, _mut83559 = false, _mut83560 = false, _mut83561 = false, _mut83562 = false, _mut83563 = false, _mut83564 = false, _mut83565 = false, _mut83566 = false, _mut83567 = false, _mut83568 = false, _mut83569 = false, _mut83570 = false, _mut83571 = false, _mut83572 = false, _mut83573 = false, _mut83574 = false, _mut83575 = false, _mut83576 = false, _mut83577 = false, _mut83578 = false, _mut83579 = false, _mut83580 = false, _mut83581 = false, _mut83582 = false, _mut83583 = false, _mut83584 = false, _mut83585 = false, _mut83586 = false, _mut83587 = false, _mut83588 = false, _mut83589 = false, _mut83590 = false, _mut83591 = false, _mut83592 = false, _mut83593 = false, _mut83594 = false, _mut83595 = false, _mut83596 = false, _mut83597 = false, _mut83598 = false, _mut83599 = false, _mut83600 = false, _mut83601 = false, _mut83602 = false, _mut83603 = false, _mut83604 = false, _mut83605 = false, _mut83606 = false, _mut83607 = false, _mut83608 = false, _mut83609 = false, _mut83610 = false, _mut83611 = false, _mut83612 = false, _mut83613 = false, _mut83614 = false, _mut83615 = false, _mut83616 = false, _mut83617 = false, _mut83618 = false, _mut83619 = false, _mut83620 = false, _mut83621 = false, _mut83622 = false, _mut83623 = false, _mut83624 = false, _mut83625 = false, _mut83626 = false, _mut83627 = false, _mut83628 = false, _mut83629 = false, _mut83630 = false, _mut83631 = false, _mut83632 = false, _mut83633 = false, _mut83634 = false, _mut83635 = false, _mut83636 = false, _mut83637 = false, _mut83638 = false, _mut83639 = false, _mut83640 = false, _mut83641 = false, _mut83642 = false, _mut83643 = false, _mut83644 = false, _mut83645 = false, _mut83646 = false, _mut83647 = false, _mut83648 = false, _mut83649 = false, _mut83650 = false, _mut83651 = false, _mut83652 = false, _mut83653 = false, _mut83654 = false, _mut83655 = false, _mut83656 = false, _mut83657 = false, _mut83658 = false, _mut83659 = false, _mut83660 = false, _mut83661 = false, _mut83662 = false, _mut83663 = false, _mut83664 = false, _mut83665 = false, _mut83666 = false, _mut83667 = false, _mut83668 = false, _mut83669 = false, _mut83670 = false, _mut83671 = false, _mut83672 = false, _mut83673 = false, _mut83674 = false, _mut83675 = false, _mut83676 = false, _mut83677 = false, _mut83678 = false, _mut83679 = false, _mut83680 = false, _mut83681 = false, _mut83682 = false, _mut83683 = false, _mut83684 = false, _mut83685 = false, _mut83686 = false, _mut83687 = false, _mut83688 = false, _mut83689 = false, _mut83690 = false, _mut83691 = false, _mut83692 = false, _mut83693 = false, _mut83694 = false, _mut83695 = false, _mut83696 = false, _mut83697 = false, _mut83698 = false, _mut83699 = false, _mut83700 = false, _mut83701 = false, _mut83702 = false, _mut83703 = false, _mut83704 = false, _mut83705 = false, _mut83706 = false, _mut83707 = false, _mut83708 = false, _mut83709 = false, _mut83710 = false, _mut83711 = false, _mut83712 = false, _mut83713 = false, _mut83714 = false, _mut83715 = false, _mut83716 = false, _mut83717 = false, _mut83718 = false, _mut83719 = false, _mut83720 = false, _mut83721 = false, _mut83722 = false, _mut83723 = false, _mut83724 = false, _mut83725 = false, _mut83726 = false, _mut83727 = false, _mut83728 = false, _mut83729 = false, _mut83730 = false, _mut83731 = false, _mut83732 = false, _mut83733 = false, _mut83734 = false, _mut83735 = false, _mut83736 = false, _mut83737 = false, _mut83738 = false, _mut83739 = false, _mut83740 = false, _mut83741 = false, _mut83742 = false, _mut83743 = false, _mut83744 = false, _mut83745 = false, _mut83746 = false, _mut83747 = false, _mut83748 = false, _mut83749 = false, _mut83750 = false, _mut83751 = false, _mut83752 = false, _mut83753 = false, _mut83754 = false, _mut83755 = false, _mut83756 = false, _mut83757 = false, _mut83758 = false, _mut83759 = false, _mut83760 = false, _mut83761 = false, _mut83762 = false, _mut83763 = false, _mut83764 = false, _mut83765 = false, _mut83766 = false, _mut83767 = false, _mut83768 = false, _mut83769 = false, _mut83770 = false, _mut83771 = false, _mut83772 = false, _mut83773 = false, _mut83774 = false, _mut83775 = false, _mut83776 = false, _mut83777 = false, _mut83778 = false, _mut83779 = false, _mut83780 = false, _mut83781 = false, _mut83782 = false, _mut83783 = false, _mut83784 = false, _mut83785 = false, _mut83786 = false, _mut83787 = false, _mut83788 = false, _mut83789 = false, _mut83790 = false, _mut83791 = false, _mut83792 = false, _mut83793 = false, _mut83794 = false, _mut83795 = false, _mut83796 = false, _mut83797 = false, _mut83798 = false, _mut83799 = false, _mut83800 = false, _mut83801 = false, _mut83802 = false, _mut83803 = false, _mut83804 = false, _mut83805 = false, _mut83806 = false, _mut83807 = false, _mut83808 = false, _mut83809 = false, _mut83810 = false, _mut83811 = false, _mut83812 = false, _mut83813 = false, _mut83814 = false, _mut83815 = false, _mut83816 = false, _mut83817 = false, _mut83818 = false, _mut83819 = false, _mut83820 = false, _mut83821 = false, _mut83822 = false, _mut83823 = false, _mut83824 = false, _mut83825 = false, _mut83826 = false, _mut83827 = false, _mut83828 = false, _mut83829 = false, _mut83830 = false, _mut83831 = false, _mut83832 = false, _mut83833 = false, _mut83834 = false, _mut83835 = false, _mut83836 = false, _mut83837 = false, _mut83838 = false, _mut83839 = false, _mut83840 = false, _mut83841 = false, _mut83842 = false, _mut83843 = false, _mut83844 = false, _mut83845 = false, _mut83846 = false, _mut83847 = false, _mut83848 = false, _mut83849 = false, _mut83850 = false, _mut83851 = false, _mut83852 = false, _mut83853 = false, _mut83854 = false, _mut83855 = false, _mut83856 = false, _mut83857 = false, _mut83858 = false, _mut83859 = false, _mut83860 = false, _mut83861 = false, _mut83862 = false, _mut83863 = false, _mut83864 = false, _mut83865 = false, _mut83866 = false, _mut83867 = false, _mut83868 = false, _mut83869 = false, _mut83870 = false, _mut83871 = false, _mut83872 = false, _mut83873 = false, _mut83874 = false, _mut83875 = false, _mut83876 = false, _mut83877 = false, _mut83878 = false, _mut83879 = false, _mut83880 = false, _mut83881 = false, _mut83882 = false, _mut83883 = false, _mut83884 = false, _mut83885 = false, _mut83886 = false, _mut83887 = false, _mut83888 = false, _mut83889 = false, _mut83890 = false;

    /**
     * Null vector (coordinates: 0, 0, 0).
     */
    public static final Vector3D ZERO = new Vector3D(0, 0, 0);

    /**
     * First canonical vector (coordinates: 1, 0, 0).
     */
    public static final Vector3D PLUS_I = new Vector3D(1, 0, 0);

    /**
     * Opposite of the first canonical vector (coordinates: -1, 0, 0).
     */
    public static final Vector3D MINUS_I = new Vector3D(-1, 0, 0);

    /**
     * Second canonical vector (coordinates: 0, 1, 0).
     */
    public static final Vector3D PLUS_J = new Vector3D(0, 1, 0);

    /**
     * Opposite of the second canonical vector (coordinates: 0, -1, 0).
     */
    public static final Vector3D MINUS_J = new Vector3D(0, -1, 0);

    /**
     * Third canonical vector (coordinates: 0, 0, 1).
     */
    public static final Vector3D PLUS_K = new Vector3D(0, 0, 1);

    /**
     * Opposite of the third canonical vector (coordinates: 0, 0, -1).
     */
    public static final Vector3D MINUS_K = new Vector3D(0, 0, -1);

    /**
     * A vector with all coordinates set to NaN.
     */
    public static final Vector3D NaN = new Vector3D(Double.NaN, Double.NaN, Double.NaN);

    /**
     * A vector with all coordinates set to positive infinity.
     */
    public static final Vector3D POSITIVE_INFINITY = new Vector3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * A vector with all coordinates set to negative infinity.
     */
    public static final Vector3D NEGATIVE_INFINITY = new Vector3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 1313493323784566947L;

    /**
     * Abscissa.
     */
    private final double x;

    /**
     * Ordinate.
     */
    private final double y;

    /**
     * Height.
     */
    private final double z;

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
    public Vector3D(double x, double y, double z) {
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
    public Vector3D(double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_107");
        if (ROR_not_equals(v.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_107", _mut83495, _mut83496, _mut83497, _mut83498, _mut83499)) {
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
    public Vector3D(double alpha, double delta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_124");
        double cosDelta = FastMath.cos(delta);
        this.x = AOR_multiply(FastMath.cos(alpha), cosDelta, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_124", _mut83500, _mut83501, _mut83502, _mut83503);
        this.y = AOR_multiply(FastMath.sin(alpha), cosDelta, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_124", _mut83504, _mut83505, _mut83506, _mut83507);
        this.z = FastMath.sin(delta);
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public Vector3D(double a, Vector3D u) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_137");
        this.x = AOR_multiply(a, u.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_137", _mut83508, _mut83509, _mut83510, _mut83511);
        this.y = AOR_multiply(a, u.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_137", _mut83512, _mut83513, _mut83514, _mut83515);
        this.z = AOR_multiply(a, u.z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.Vector3D_137", _mut83516, _mut83517, _mut83518, _mut83519);
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
    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z);
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
    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z);
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
    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3, double a4, Vector3D u4) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x, a4, u4.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y, a4, u4.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z, a4, u4.z);
    }

    /**
     * Get the abscissa of the vector.
     * @return abscissa of the vector
     * @see #Vector3D(double, double, double)
     */
    public double getX() {
        return x;
    }

    /**
     * Get the ordinate of the vector.
     * @return ordinate of the vector
     * @see #Vector3D(double, double, double)
     */
    public double getY() {
        return y;
    }

    /**
     * Get the height of the vector.
     * @return height of the vector
     * @see #Vector3D(double, double, double)
     */
    public double getZ() {
        return z;
    }

    /**
     * Get the vector coordinates as a dimension 3 array.
     * @return vector coordinates
     * @see #Vector3D(double[])
     */
    public double[] toArray() {
        return new double[] { x, y, z };
    }

    /**
     * {@inheritDoc}
     */
    public Space getSpace() {
        return Euclidean3D.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D getZero() {
        return ZERO;
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm1() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm1_236");
        return AOR_plus(AOR_plus(FastMath.abs(x), FastMath.abs(y), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm1_236", _mut83520, _mut83521, _mut83522, _mut83523), FastMath.abs(z), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm1_236", _mut83524, _mut83525, _mut83526, _mut83527);
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241");
        // there are no cancellation problems here, so we use the straightforward formula
        return FastMath.sqrt(AOR_plus(AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241", _mut83528, _mut83529, _mut83530, _mut83531), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241", _mut83532, _mut83533, _mut83534, _mut83535), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241", _mut83536, _mut83537, _mut83538, _mut83539), AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241", _mut83540, _mut83541, _mut83542, _mut83543), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNorm_241", _mut83544, _mut83545, _mut83546, _mut83547));
    }

    /**
     * {@inheritDoc}
     */
    public double getNormSq() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247");
        // there are no cancellation problems here, so we use the straightforward formula
        return AOR_plus(AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247", _mut83548, _mut83549, _mut83550, _mut83551), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247", _mut83552, _mut83553, _mut83554, _mut83555), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247", _mut83556, _mut83557, _mut83558, _mut83559), AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247", _mut83560, _mut83561, _mut83562, _mut83563), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getNormSq_247", _mut83564, _mut83565, _mut83566, _mut83567);
    }

    /**
     * {@inheritDoc}
     */
    public double getNormInf() {
        return FastMath.max(FastMath.max(FastMath.abs(x), FastMath.abs(y)), FastMath.abs(z));
    }

    /**
     * Get the azimuth of the vector.
     * @return azimuth (&alpha;) of the vector, between -&pi; and +&pi;
     * @see #Vector3D(double, double)
     */
    public double getAlpha() {
        return FastMath.atan2(y, x);
    }

    /**
     * Get the elevation of the vector.
     * @return elevation (&delta;) of the vector, between -&pi;/2 and +&pi;/2
     * @see #Vector3D(double, double)
     */
    public double getDelta() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getDelta_269");
        return FastMath.asin(AOR_divide(z, getNorm(), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.getDelta_269", _mut83568, _mut83569, _mut83570, _mut83571));
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D add(final Vector<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.add_274");
        final Vector3D v3 = (Vector3D) v;
        return new Vector3D(AOR_plus(x, v3.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.add_274", _mut83572, _mut83573, _mut83574, _mut83575), AOR_plus(y, v3.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.add_274", _mut83576, _mut83577, _mut83578, _mut83579), AOR_plus(z, v3.z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.add_274", _mut83580, _mut83581, _mut83582, _mut83583));
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D add(double factor, final Vector<Euclidean3D> v) {
        return new Vector3D(1, this, factor, (Vector3D) v);
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D subtract(final Vector<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.subtract_285");
        final Vector3D v3 = (Vector3D) v;
        return new Vector3D(AOR_minus(x, v3.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.subtract_285", _mut83584, _mut83585, _mut83586, _mut83587), AOR_minus(y, v3.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.subtract_285", _mut83588, _mut83589, _mut83590, _mut83591), AOR_minus(z, v3.z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.subtract_285", _mut83592, _mut83593, _mut83594, _mut83595));
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D subtract(final double factor, final Vector<Euclidean3D> v) {
        return new Vector3D(1, this, -factor, (Vector3D) v);
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D normalize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.normalize_296");
        double s = getNorm();
        if (ROR_equals(s, 0, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.normalize_296", _mut83596, _mut83597, _mut83598, _mut83599, _mut83600)) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
        }
        return scalarMultiply(AOR_divide(1, s, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.normalize_296", _mut83601, _mut83602, _mut83603, _mut83604));
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
    public Vector3D orthogonal() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319");
        double threshold = AOR_multiply(0.6, getNorm(), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83605, _mut83606, _mut83607, _mut83608);
        if (ROR_equals(threshold, 0, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83609, _mut83610, _mut83611, _mut83612, _mut83613)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        if (ROR_less_equals(FastMath.abs(x), threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83614, _mut83615, _mut83616, _mut83617, _mut83618)) {
            double inverse = AOR_divide(1, FastMath.sqrt(AOR_plus(AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83648, _mut83649, _mut83650, _mut83651), AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83652, _mut83653, _mut83654, _mut83655), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83656, _mut83657, _mut83658, _mut83659)), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83660, _mut83661, _mut83662, _mut83663);
            return new Vector3D(0, AOR_multiply(inverse, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83664, _mut83665, _mut83666, _mut83667), AOR_multiply(-inverse, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83668, _mut83669, _mut83670, _mut83671));
        } else if (ROR_less_equals(FastMath.abs(y), threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83619, _mut83620, _mut83621, _mut83622, _mut83623)) {
            double inverse = AOR_divide(1, FastMath.sqrt(AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83624, _mut83625, _mut83626, _mut83627), AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83628, _mut83629, _mut83630, _mut83631), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83632, _mut83633, _mut83634, _mut83635)), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83636, _mut83637, _mut83638, _mut83639);
            return new Vector3D(AOR_multiply(-inverse, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83640, _mut83641, _mut83642, _mut83643), 0, AOR_multiply(inverse, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83644, _mut83645, _mut83646, _mut83647));
        }
        double inverse = AOR_divide(1, FastMath.sqrt(AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83672, _mut83673, _mut83674, _mut83675), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83676, _mut83677, _mut83678, _mut83679), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83680, _mut83681, _mut83682, _mut83683)), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83684, _mut83685, _mut83686, _mut83687);
        return new Vector3D(AOR_multiply(inverse, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83688, _mut83689, _mut83690, _mut83691), AOR_multiply(-inverse, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.orthogonal_319", _mut83692, _mut83693, _mut83694, _mut83695), 0);
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
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static double angle(Vector3D v1, Vector3D v2) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349");
        double normProduct = AOR_multiply(v1.getNorm(), v2.getNorm(), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83696, _mut83697, _mut83698, _mut83699);
        if (ROR_equals(normProduct, 0, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83700, _mut83701, _mut83702, _mut83703, _mut83704)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        double dot = v1.dotProduct(v2);
        double threshold = AOR_multiply(normProduct, 0.9999, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83705, _mut83706, _mut83707, _mut83708);
        if ((_mut83719 ? ((ROR_less(dot, -threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83709, _mut83710, _mut83711, _mut83712, _mut83713)) && (ROR_greater(dot, threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83714, _mut83715, _mut83716, _mut83717, _mut83718))) : ((ROR_less(dot, -threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83709, _mut83710, _mut83711, _mut83712, _mut83713)) || (ROR_greater(dot, threshold, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83714, _mut83715, _mut83716, _mut83717, _mut83718))))) {
            // the vectors are almost aligned, compute using the sine
            Vector3D v3 = crossProduct(v1, v2);
            if (ROR_greater_equals(dot, 0, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83720, _mut83721, _mut83722, _mut83723, _mut83724)) {
                return FastMath.asin(AOR_divide(v3.getNorm(), normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83725, _mut83726, _mut83727, _mut83728));
            }
            return AOR_minus(FastMath.PI, FastMath.asin(AOR_divide(v3.getNorm(), normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83729, _mut83730, _mut83731, _mut83732)), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83733, _mut83734, _mut83735, _mut83736);
        }
        // the vectors are sufficiently separated to use the cosine
        return FastMath.acos(AOR_divide(dot, normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.angle_349", _mut83737, _mut83738, _mut83739, _mut83740));
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D negate() {
        return new Vector3D(-x, -y, -z);
    }

    /**
     * {@inheritDoc}
     */
    public Vector3D scalarMultiply(double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.scalarMultiply_378");
        return new Vector3D(AOR_multiply(a, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.scalarMultiply_378", _mut83741, _mut83742, _mut83743, _mut83744), AOR_multiply(a, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.scalarMultiply_378", _mut83745, _mut83746, _mut83747, _mut83748), AOR_multiply(a, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.scalarMultiply_378", _mut83749, _mut83750, _mut83751, _mut83752));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNaN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.isNaN_383");
        return (_mut83754 ? ((_mut83753 ? (Double.isNaN(x) && Double.isNaN(y)) : (Double.isNaN(x) || Double.isNaN(y))) && Double.isNaN(z)) : ((_mut83753 ? (Double.isNaN(x) && Double.isNaN(y)) : (Double.isNaN(x) || Double.isNaN(y))) || Double.isNaN(z)));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfinite() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.isInfinite_388");
        return (_mut83757 ? (!isNaN() || ((_mut83756 ? ((_mut83755 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))) && Double.isInfinite(z)) : ((_mut83755 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))) || Double.isInfinite(z))))) : (!isNaN() && ((_mut83756 ? ((_mut83755 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))) && Double.isInfinite(z)) : ((_mut83755 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))) || Double.isInfinite(z))))));
    }

    /**
     * Test for the equality of two 3D vectors.
     * <p>
     * If all coordinates of two 3D vectors are exactly the same, and none are
     * <code>Double.NaN</code>, the two 3D vectors are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) coordinates of the
     * 3D vector are equal to <code>Double.NaN</code>, the 3D vector is equal to
     * {@link #NaN}.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two 3D vector objects are equal, false if
     *         object is null, not an instance of Vector3D, or
     *         not equal to this Vector3D instance
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411");
        if (this == other) {
            return true;
        }
        if (other instanceof Vector3D) {
            final Vector3D rhs = (Vector3D) other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return (_mut83774 ? ((_mut83768 ? ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83758, _mut83759, _mut83760, _mut83761, _mut83762)) || (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83763, _mut83764, _mut83765, _mut83766, _mut83767))) : ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83758, _mut83759, _mut83760, _mut83761, _mut83762)) && (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83763, _mut83764, _mut83765, _mut83766, _mut83767)))) || (ROR_equals(z, rhs.z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83769, _mut83770, _mut83771, _mut83772, _mut83773))) : ((_mut83768 ? ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83758, _mut83759, _mut83760, _mut83761, _mut83762)) || (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83763, _mut83764, _mut83765, _mut83766, _mut83767))) : ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83758, _mut83759, _mut83760, _mut83761, _mut83762)) && (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83763, _mut83764, _mut83765, _mut83766, _mut83767)))) && (ROR_equals(z, rhs.z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.equals_411", _mut83769, _mut83770, _mut83771, _mut83772, _mut83773))));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436");
        if (isNaN()) {
            return 642;
        }
        return AOR_multiply(643, (AOR_plus(AOR_plus(AOR_multiply(164, MathUtils.hash(x), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436", _mut83775, _mut83776, _mut83777, _mut83778), AOR_multiply(3, MathUtils.hash(y), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436", _mut83779, _mut83780, _mut83781, _mut83782), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436", _mut83783, _mut83784, _mut83785, _mut83786), MathUtils.hash(z), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436", _mut83787, _mut83788, _mut83789, _mut83790)), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.hashCode_436", _mut83791, _mut83792, _mut83793, _mut83794);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation uses specific multiplication and addition
     * algorithms to preserve accuracy and reduce cancellation effects.
     * It should be very accurate even for nearly orthogonal vectors.
     * </p>
     * @see MathArrays#linearCombination(double, double, double, double, double, double)
     */
    public double dotProduct(final Vector<Euclidean3D> v) {
        final Vector3D v3 = (Vector3D) v;
        return MathArrays.linearCombination(x, v3.x, y, v3.y, z, v3.z);
    }

    /**
     * Compute the cross-product of the instance with another vector.
     * @param v other vector
     * @return the cross product this ^ v as a new Vector3D
     */
    public Vector3D crossProduct(final Vector<Euclidean3D> v) {
        final Vector3D v3 = (Vector3D) v;
        return new Vector3D(MathArrays.linearCombination(y, v3.z, -z, v3.y), MathArrays.linearCombination(z, v3.x, -x, v3.z), MathArrays.linearCombination(x, v3.y, -y, v3.x));
    }

    /**
     * {@inheritDoc}
     */
    public double distance1(Vector<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469");
        final Vector3D v3 = (Vector3D) v;
        final double dx = FastMath.abs(AOR_minus(v3.x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469", _mut83795, _mut83796, _mut83797, _mut83798));
        final double dy = FastMath.abs(AOR_minus(v3.y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469", _mut83799, _mut83800, _mut83801, _mut83802));
        final double dz = FastMath.abs(AOR_minus(v3.z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469", _mut83803, _mut83804, _mut83805, _mut83806));
        return AOR_plus(AOR_plus(dx, dy, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469", _mut83807, _mut83808, _mut83809, _mut83810), dz, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance1_469", _mut83811, _mut83812, _mut83813, _mut83814);
    }

    /**
     * {@inheritDoc}
     */
    public double distance(Vector<Euclidean3D> v) {
        return distance((Point<Euclidean3D>) v);
    }

    /**
     * {@inheritDoc}
     */
    public double distance(Point<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483");
        final Vector3D v3 = (Vector3D) v;
        final double dx = AOR_minus(v3.x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83815, _mut83816, _mut83817, _mut83818);
        final double dy = AOR_minus(v3.y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83819, _mut83820, _mut83821, _mut83822);
        final double dz = AOR_minus(v3.z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83823, _mut83824, _mut83825, _mut83826);
        return FastMath.sqrt(AOR_plus(AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83827, _mut83828, _mut83829, _mut83830), AOR_multiply(dy, dy, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83831, _mut83832, _mut83833, _mut83834), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83835, _mut83836, _mut83837, _mut83838), AOR_multiply(dz, dz, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83839, _mut83840, _mut83841, _mut83842), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distance_483", _mut83843, _mut83844, _mut83845, _mut83846));
    }

    /**
     * {@inheritDoc}
     */
    public double distanceInf(Vector<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceInf_492");
        final Vector3D v3 = (Vector3D) v;
        final double dx = FastMath.abs(AOR_minus(v3.x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceInf_492", _mut83847, _mut83848, _mut83849, _mut83850));
        final double dy = FastMath.abs(AOR_minus(v3.y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceInf_492", _mut83851, _mut83852, _mut83853, _mut83854));
        final double dz = FastMath.abs(AOR_minus(v3.z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceInf_492", _mut83855, _mut83856, _mut83857, _mut83858));
        return FastMath.max(FastMath.max(dx, dy), dz);
    }

    /**
     * {@inheritDoc}
     */
    public double distanceSq(Vector<Euclidean3D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501");
        final Vector3D v3 = (Vector3D) v;
        final double dx = AOR_minus(v3.x, x, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83859, _mut83860, _mut83861, _mut83862);
        final double dy = AOR_minus(v3.y, y, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83863, _mut83864, _mut83865, _mut83866);
        final double dz = AOR_minus(v3.z, z, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83867, _mut83868, _mut83869, _mut83870);
        return AOR_plus(AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83871, _mut83872, _mut83873, _mut83874), AOR_multiply(dy, dy, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83875, _mut83876, _mut83877, _mut83878), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83879, _mut83880, _mut83881, _mut83882), AOR_multiply(dz, dz, "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83883, _mut83884, _mut83885, _mut83886), "org.apache.commons.math3.geometry.euclidean.threed.Vector3D.distanceSq_501", _mut83887, _mut83888, _mut83889, _mut83890);
    }

    /**
     * Compute the dot-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @return the dot product v1.v2
     */
    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    /**
     * Compute the cross-product of two vectors.
     * @param v1 first vector
     * @param v2 second vector
     * @return the cross product v1 ^ v2 as a new Vector
     */
    public static Vector3D crossProduct(final Vector3D v1, final Vector3D v2) {
        return v1.crossProduct(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>1</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm1()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @return the distance between v1 and v2 according to the L<sub>1</sub> norm
     */
    public static double distance1(Vector3D v1, Vector3D v2) {
        return v1.distance1(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @return the distance between v1 and v2 according to the L<sub>2</sub> norm
     */
    public static double distance(Vector3D v1, Vector3D v2) {
        return v1.distance(v2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @return the distance between v1 and v2 according to the L<sub>&infin;</sub> norm
     */
    public static double distanceInf(Vector3D v1, Vector3D v2) {
        return v1.distanceInf(v2);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>v1.subtract(v2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param v1 first vector
     * @param v2 second vector
     * @return the square of the distance between v1 and v2
     */
    public static double distanceSq(Vector3D v1, Vector3D v2) {
        return v1.distanceSq(v2);
    }

    /**
     * Get a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return Vector3DFormat.getInstance().format(this);
    }

    /**
     * {@inheritDoc}
     */
    public String toString(final NumberFormat format) {
        return new Vector3DFormat(format).format(this);
    }
}
