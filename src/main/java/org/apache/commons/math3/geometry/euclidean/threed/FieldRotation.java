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
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FieldRotation<T extends RealFieldElement<T>> implements Serializable {

    @Conditional
    public static boolean _mut80749 = false, _mut80750 = false, _mut80751 = false, _mut80752 = false, _mut80753 = false, _mut80754 = false, _mut80755 = false, _mut80756 = false, _mut80757 = false, _mut80758 = false, _mut80759 = false, _mut80760 = false, _mut80761 = false, _mut80762 = false, _mut80763 = false, _mut80764 = false, _mut80765 = false, _mut80766 = false, _mut80767 = false, _mut80768 = false, _mut80769 = false, _mut80770 = false, _mut80771 = false, _mut80772 = false, _mut80773 = false, _mut80774 = false, _mut80775 = false, _mut80776 = false, _mut80777 = false, _mut80778 = false, _mut80779 = false, _mut80780 = false, _mut80781 = false, _mut80782 = false, _mut80783 = false, _mut80784 = false, _mut80785 = false, _mut80786 = false, _mut80787 = false, _mut80788 = false, _mut80789 = false, _mut80790 = false, _mut80791 = false, _mut80792 = false, _mut80793 = false, _mut80794 = false, _mut80795 = false, _mut80796 = false, _mut80797 = false, _mut80798 = false, _mut80799 = false, _mut80800 = false, _mut80801 = false, _mut80802 = false, _mut80803 = false, _mut80804 = false, _mut80805 = false, _mut80806 = false, _mut80807 = false, _mut80808 = false, _mut80809 = false, _mut80810 = false, _mut80811 = false, _mut80812 = false, _mut80813 = false, _mut80814 = false, _mut80815 = false, _mut80816 = false, _mut80817 = false, _mut80818 = false, _mut80819 = false, _mut80820 = false, _mut80821 = false, _mut80822 = false, _mut80823 = false, _mut80824 = false, _mut80825 = false, _mut80826 = false, _mut80827 = false, _mut80828 = false, _mut80829 = false, _mut80830 = false, _mut80831 = false, _mut80832 = false, _mut80833 = false, _mut80834 = false, _mut80835 = false, _mut80836 = false, _mut80837 = false, _mut80838 = false, _mut80839 = false, _mut80840 = false, _mut80841 = false, _mut80842 = false, _mut80843 = false, _mut80844 = false, _mut80845 = false, _mut80846 = false, _mut80847 = false, _mut80848 = false, _mut80849 = false, _mut80850 = false, _mut80851 = false, _mut80852 = false, _mut80853 = false, _mut80854 = false, _mut80855 = false, _mut80856 = false, _mut80857 = false, _mut80858 = false, _mut80859 = false, _mut80860 = false, _mut80861 = false, _mut80862 = false, _mut80863 = false, _mut80864 = false, _mut80865 = false, _mut80866 = false, _mut80867 = false, _mut80868 = false, _mut80869 = false, _mut80870 = false, _mut80871 = false, _mut80872 = false, _mut80873 = false, _mut80874 = false, _mut80875 = false, _mut80876 = false, _mut80877 = false, _mut80878 = false, _mut80879 = false, _mut80880 = false, _mut80881 = false, _mut80882 = false, _mut80883 = false, _mut80884 = false, _mut80885 = false, _mut80886 = false, _mut80887 = false, _mut80888 = false, _mut80889 = false, _mut80890 = false, _mut80891 = false, _mut80892 = false, _mut80893 = false, _mut80894 = false, _mut80895 = false, _mut80896 = false, _mut80897 = false, _mut80898 = false, _mut80899 = false, _mut80900 = false, _mut80901 = false, _mut80902 = false, _mut80903 = false, _mut80904 = false, _mut80905 = false, _mut80906 = false, _mut80907 = false, _mut80908 = false, _mut80909 = false, _mut80910 = false, _mut80911 = false, _mut80912 = false, _mut80913 = false, _mut80914 = false, _mut80915 = false, _mut80916 = false, _mut80917 = false, _mut80918 = false, _mut80919 = false, _mut80920 = false, _mut80921 = false, _mut80922 = false, _mut80923 = false, _mut80924 = false, _mut80925 = false, _mut80926 = false, _mut80927 = false, _mut80928 = false, _mut80929 = false, _mut80930 = false, _mut80931 = false, _mut80932 = false, _mut80933 = false, _mut80934 = false, _mut80935 = false, _mut80936 = false, _mut80937 = false, _mut80938 = false, _mut80939 = false, _mut80940 = false, _mut80941 = false, _mut80942 = false, _mut80943 = false, _mut80944 = false, _mut80945 = false, _mut80946 = false, _mut80947 = false, _mut80948 = false, _mut80949 = false, _mut80950 = false, _mut80951 = false, _mut80952 = false, _mut80953 = false, _mut80954 = false, _mut80955 = false, _mut80956 = false, _mut80957 = false, _mut80958 = false, _mut80959 = false, _mut80960 = false, _mut80961 = false, _mut80962 = false, _mut80963 = false, _mut80964 = false, _mut80965 = false, _mut80966 = false, _mut80967 = false, _mut80968 = false, _mut80969 = false, _mut80970 = false, _mut80971 = false, _mut80972 = false, _mut80973 = false, _mut80974 = false, _mut80975 = false, _mut80976 = false, _mut80977 = false, _mut80978 = false, _mut80979 = false, _mut80980 = false, _mut80981 = false, _mut80982 = false, _mut80983 = false, _mut80984 = false, _mut80985 = false, _mut80986 = false, _mut80987 = false, _mut80988 = false, _mut80989 = false, _mut80990 = false, _mut80991 = false, _mut80992 = false, _mut80993 = false, _mut80994 = false, _mut80995 = false, _mut80996 = false, _mut80997 = false, _mut80998 = false, _mut80999 = false, _mut81000 = false, _mut81001 = false, _mut81002 = false, _mut81003 = false, _mut81004 = false, _mut81005 = false, _mut81006 = false, _mut81007 = false, _mut81008 = false, _mut81009 = false, _mut81010 = false, _mut81011 = false, _mut81012 = false, _mut81013 = false, _mut81014 = false, _mut81015 = false, _mut81016 = false, _mut81017 = false, _mut81018 = false, _mut81019 = false, _mut81020 = false, _mut81021 = false, _mut81022 = false, _mut81023 = false, _mut81024 = false, _mut81025 = false, _mut81026 = false, _mut81027 = false, _mut81028 = false, _mut81029 = false, _mut81030 = false, _mut81031 = false, _mut81032 = false, _mut81033 = false, _mut81034 = false, _mut81035 = false, _mut81036 = false, _mut81037 = false, _mut81038 = false, _mut81039 = false, _mut81040 = false, _mut81041 = false, _mut81042 = false, _mut81043 = false, _mut81044 = false, _mut81045 = false, _mut81046 = false, _mut81047 = false, _mut81048 = false, _mut81049 = false, _mut81050 = false, _mut81051 = false, _mut81052 = false, _mut81053 = false, _mut81054 = false, _mut81055 = false, _mut81056 = false, _mut81057 = false, _mut81058 = false, _mut81059 = false, _mut81060 = false, _mut81061 = false, _mut81062 = false, _mut81063 = false, _mut81064 = false, _mut81065 = false, _mut81066 = false, _mut81067 = false, _mut81068 = false, _mut81069 = false, _mut81070 = false, _mut81071 = false, _mut81072 = false, _mut81073 = false, _mut81074 = false, _mut81075 = false, _mut81076 = false, _mut81077 = false, _mut81078 = false, _mut81079 = false, _mut81080 = false, _mut81081 = false, _mut81082 = false, _mut81083 = false, _mut81084 = false, _mut81085 = false, _mut81086 = false, _mut81087 = false, _mut81088 = false, _mut81089 = false, _mut81090 = false, _mut81091 = false, _mut81092 = false, _mut81093 = false, _mut81094 = false, _mut81095 = false, _mut81096 = false, _mut81097 = false, _mut81098 = false, _mut81099 = false, _mut81100 = false, _mut81101 = false, _mut81102 = false, _mut81103 = false, _mut81104 = false, _mut81105 = false, _mut81106 = false, _mut81107 = false, _mut81108 = false, _mut81109 = false, _mut81110 = false, _mut81111 = false, _mut81112 = false, _mut81113 = false, _mut81114 = false, _mut81115 = false, _mut81116 = false, _mut81117 = false, _mut81118 = false, _mut81119 = false, _mut81120 = false, _mut81121 = false, _mut81122 = false, _mut81123 = false, _mut81124 = false, _mut81125 = false, _mut81126 = false, _mut81127 = false, _mut81128 = false, _mut81129 = false, _mut81130 = false, _mut81131 = false, _mut81132 = false, _mut81133 = false, _mut81134 = false, _mut81135 = false, _mut81136 = false, _mut81137 = false, _mut81138 = false, _mut81139 = false, _mut81140 = false, _mut81141 = false, _mut81142 = false, _mut81143 = false, _mut81144 = false, _mut81145 = false, _mut81146 = false, _mut81147 = false, _mut81148 = false, _mut81149 = false, _mut81150 = false, _mut81151 = false, _mut81152 = false, _mut81153 = false, _mut81154 = false, _mut81155 = false, _mut81156 = false, _mut81157 = false, _mut81158 = false, _mut81159 = false, _mut81160 = false, _mut81161 = false, _mut81162 = false, _mut81163 = false, _mut81164 = false, _mut81165 = false, _mut81166 = false, _mut81167 = false, _mut81168 = false, _mut81169 = false, _mut81170 = false, _mut81171 = false, _mut81172 = false, _mut81173 = false, _mut81174 = false, _mut81175 = false, _mut81176 = false, _mut81177 = false, _mut81178 = false, _mut81179 = false, _mut81180 = false, _mut81181 = false, _mut81182 = false, _mut81183 = false, _mut81184 = false, _mut81185 = false, _mut81186 = false, _mut81187 = false, _mut81188 = false, _mut81189 = false, _mut81190 = false, _mut81191 = false, _mut81192 = false, _mut81193 = false, _mut81194 = false, _mut81195 = false, _mut81196 = false, _mut81197 = false, _mut81198 = false, _mut81199 = false, _mut81200 = false, _mut81201 = false, _mut81202 = false, _mut81203 = false, _mut81204 = false, _mut81205 = false, _mut81206 = false, _mut81207 = false, _mut81208 = false, _mut81209 = false, _mut81210 = false, _mut81211 = false, _mut81212 = false, _mut81213 = false, _mut81214 = false, _mut81215 = false, _mut81216 = false, _mut81217 = false, _mut81218 = false, _mut81219 = false, _mut81220 = false, _mut81221 = false, _mut81222 = false, _mut81223 = false, _mut81224 = false, _mut81225 = false, _mut81226 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 20130224l;

    /**
     * Scalar coordinate of the quaternion.
     */
    private final T q0;

    /**
     * First coordinate of the vectorial part of the quaternion.
     */
    private final T q1;

    /**
     * Second coordinate of the vectorial part of the quaternion.
     */
    private final T q2;

    /**
     * Third coordinate of the vectorial part of the quaternion.
     */
    private final T q3;

    /**
     * Build a rotation from the quaternion coordinates.
     * <p>A rotation can be built from a <em>normalized</em> quaternion,
     * i.e. a quaternion for which q<sub>0</sub><sup>2</sup> +
     * q<sub>1</sub><sup>2</sup> + q<sub>2</sub><sup>2</sup> +
     * q<sub>3</sub><sup>2</sup> = 1. If the quaternion is not normalized,
     * the constructor can normalize it in a preprocessing step.</p>
     * <p>Note that some conventions put the scalar part of the quaternion
     * as the 4<sup>th</sup> component and the vector part as the first three
     * components. This is <em>not</em> our convention. We put the scalar part
     * as the first component.</p>
     * @param q0 scalar part of the quaternion
     * @param q1 first coordinate of the vectorial part of the quaternion
     * @param q2 second coordinate of the vectorial part of the quaternion
     * @param q3 third coordinate of the vectorial part of the quaternion
     * @param needsNormalization if true, the coordinates are considered
     * not to be normalized, a normalization preprocessing step is performed
     * before using them
     */
    public FieldRotation(final T q0, final T q1, final T q2, final T q3, final boolean needsNormalization) {
        if (needsNormalization) {
            // normalization preprocessing
            final T inv = q0.multiply(q0).add(q1.multiply(q1)).add(q2.multiply(q2)).add(q3.multiply(q3)).sqrt().reciprocal();
            this.q0 = inv.multiply(q0);
            this.q1 = inv.multiply(q1);
            this.q2 = inv.multiply(q2);
            this.q3 = inv.multiply(q3);
        } else {
            this.q0 = q0;
            this.q1 = q1;
            this.q2 = q2;
            this.q3 = q3;
        }
    }

    /**
     * Build a rotation from an axis and an angle.
     * <p>We use the convention that angles are oriented according to
     * the effect of the rotation on vectors around the axis. That means
     * that if (i, j, k) is a direct frame and if we first provide +k as
     * the axis and &pi;/2 as the angle to this constructor, and then
     * {@link #applyTo(FieldVector3D) apply} the instance to +i, we will get
     * +j.</p>
     * <p>Another way to represent our convention is to say that a rotation
     * of angle &theta; about the unit vector (x, y, z) is the same as the
     * rotation build from quaternion components { cos(-&theta;/2),
     * x * sin(-&theta;/2), y * sin(-&theta;/2), z * sin(-&theta;/2) }.
     * Note the minus sign on the angle!</p>
     * <p>On the one hand this convention is consistent with a vectorial
     * perspective (moving vectors in fixed frames), on the other hand it
     * is different from conventions with a frame perspective (fixed vectors
     * viewed from different frames) like the ones used for example in spacecraft
     * attitude community or in the graphics community.</p>
     * @param axis axis around which to rotate
     * @param angle rotation angle.
     * @exception MathIllegalArgumentException if the axis norm is zero
     * @deprecated as of 3.6, replaced with {@link
     * #FieldRotation(FieldVector3D, RealFieldElement, RotationConvention)}
     */
    @Deprecated
    public FieldRotation(final FieldVector3D<T> axis, final T angle) throws MathIllegalArgumentException {
        this(axis, angle, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Build a rotation from an axis and an angle.
     * <p>We use the convention that angles are oriented according to
     * the effect of the rotation on vectors around the axis. That means
     * that if (i, j, k) is a direct frame and if we first provide +k as
     * the axis and &pi;/2 as the angle to this constructor, and then
     * {@link #applyTo(FieldVector3D) apply} the instance to +i, we will get
     * +j.</p>
     * <p>Another way to represent our convention is to say that a rotation
     * of angle &theta; about the unit vector (x, y, z) is the same as the
     * rotation build from quaternion components { cos(-&theta;/2),
     * x * sin(-&theta;/2), y * sin(-&theta;/2), z * sin(-&theta;/2) }.
     * Note the minus sign on the angle!</p>
     * <p>On the one hand this convention is consistent with a vectorial
     * perspective (moving vectors in fixed frames), on the other hand it
     * is different from conventions with a frame perspective (fixed vectors
     * viewed from different frames) like the ones used for example in spacecraft
     * attitude community or in the graphics community.</p>
     * @param axis axis around which to rotate
     * @param angle rotation angle.
     * @param convention convention to use for the semantics of the angle
     * @exception MathIllegalArgumentException if the axis norm is zero
     * @since 3.6
     */
    public FieldRotation(final FieldVector3D<T> axis, final T angle, final RotationConvention convention) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_146");
        final T norm = axis.getNorm();
        if (ROR_equals(norm.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_146", _mut80749, _mut80750, _mut80751, _mut80752, _mut80753)) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS);
        }
        final T halfAngle = angle.multiply(convention == RotationConvention.VECTOR_OPERATOR ? -0.5 : 0.5);
        final T coeff = halfAngle.sin().divide(norm);
        q0 = halfAngle.cos();
        q1 = coeff.multiply(axis.getX());
        q2 = coeff.multiply(axis.getY());
        q3 = coeff.multiply(axis.getZ());
    }

    /**
     * Build a rotation from a 3X3 matrix.
     *
     * <p>Rotation matrices are orthogonal matrices, i.e. unit matrices
     * (which are matrices for which m.m<sup>T</sup> = I) with real
     * coefficients. The module of the determinant of unit matrices is
     * 1, among the orthogonal 3X3 matrices, only the ones having a
     * positive determinant (+1) are rotation matrices.</p>
     *
     * <p>When a rotation is defined by a matrix with truncated values
     * (typically when it is extracted from a technical sheet where only
     * four to five significant digits are available), the matrix is not
     * orthogonal anymore. This constructor handles this case
     * transparently by using a copy of the given matrix and applying a
     * correction to the copy in order to perfect its orthogonality. If
     * the Frobenius norm of the correction needed is above the given
     * threshold, then the matrix is considered to be too far from a
     * true rotation matrix and an exception is thrown.<p>
     *
     * @param m rotation matrix
     * @param threshold convergence threshold for the iterative
     * orthogonality correction (convergence is reached when the
     * difference between two steps of the Frobenius norm of the
     * correction is below this threshold)
     *
     * @exception NotARotationMatrixException if the matrix is not a 3X3
     * matrix, or if it cannot be transformed into an orthogonal matrix
     * with the given threshold, or if the determinant of the resulting
     * orthogonal matrix is negative
     */
    public FieldRotation(final T[][] m, final double threshold) throws NotARotationMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194");
        // dimension check
        if ((_mut80776 ? ((_mut80770 ? ((_mut80764 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763)))) && (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80765, _mut80766, _mut80767, _mut80768, _mut80769))) : ((_mut80764 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763)))) || (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80765, _mut80766, _mut80767, _mut80768, _mut80769)))) && (ROR_not_equals(m[2].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80771, _mut80772, _mut80773, _mut80774, _mut80775))) : ((_mut80770 ? ((_mut80764 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763)))) && (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80765, _mut80766, _mut80767, _mut80768, _mut80769))) : ((_mut80764 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80754, _mut80755, _mut80756, _mut80757, _mut80758)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80759, _mut80760, _mut80761, _mut80762, _mut80763)))) || (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80765, _mut80766, _mut80767, _mut80768, _mut80769)))) || (ROR_not_equals(m[2].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80771, _mut80772, _mut80773, _mut80774, _mut80775))))) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, m.length, m[0].length);
        }
        // compute a "close" orthogonal matrix
        final T[][] ort = orthogonalizeMatrix(m, threshold);
        // check the sign of the determinant
        final T d0 = ort[1][1].multiply(ort[2][2]).subtract(ort[2][1].multiply(ort[1][2]));
        final T d1 = ort[0][1].multiply(ort[2][2]).subtract(ort[2][1].multiply(ort[0][2]));
        final T d2 = ort[0][1].multiply(ort[1][2]).subtract(ort[1][1].multiply(ort[0][2]));
        final T det = ort[0][0].multiply(d0).subtract(ort[1][0].multiply(d1)).add(ort[2][0].multiply(d2));
        if (ROR_less(det.getReal(), 0.0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_194", _mut80777, _mut80778, _mut80779, _mut80780, _mut80781)) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, det);
        }
        final T[] quat = mat2quat(ort);
        q0 = quat[0];
        q1 = quat[1];
        q2 = quat[2];
        q3 = quat[3];
    }

    /**
     * Build the rotation that transforms a pair of vectors into another pair.
     *
     * <p>Except for possible scale factors, if the instance were applied to
     * the pair (u<sub>1</sub>, u<sub>2</sub>) it will produce the pair
     * (v<sub>1</sub>, v<sub>2</sub>).</p>
     *
     * <p>If the angular separation between u<sub>1</sub> and u<sub>2</sub> is
     * not the same as the angular separation between v<sub>1</sub> and
     * v<sub>2</sub>, then a corrected v'<sub>2</sub> will be used rather than
     * v<sub>2</sub>, the corrected vector will be in the (&pm;v<sub>1</sub>,
     * +v<sub>2</sub>) half-plane.</p>
     *
     * @param u1 first vector of the origin pair
     * @param u2 second vector of the origin pair
     * @param v1 desired image of u1 by the rotation
     * @param v2 desired image of u2 by the rotation
     * @exception MathArithmeticException if the norm of one of the vectors is zero,
     * or if one of the pair is degenerated (i.e. the vectors of the pair are collinear)
     */
    public FieldRotation(FieldVector3D<T> u1, FieldVector3D<T> u2, FieldVector3D<T> v1, FieldVector3D<T> v2) throws MathArithmeticException {
        // this fails when vectors are null or collinear, which is forbidden to define a rotation
        final FieldVector3D<T> u3 = FieldVector3D.crossProduct(u1, u2).normalize();
        u2 = FieldVector3D.crossProduct(u3, u1).normalize();
        u1 = u1.normalize();
        // this fails when vectors are null or collinear, which is forbidden to define a rotation
        final FieldVector3D<T> v3 = FieldVector3D.crossProduct(v1, v2).normalize();
        v2 = FieldVector3D.crossProduct(v3, v1).normalize();
        v1 = v1.normalize();
        // buid a matrix transforming the first base into the second one
        final T[][] array = MathArrays.buildArray(u1.getX().getField(), 3, 3);
        array[0][0] = u1.getX().multiply(v1.getX()).add(u2.getX().multiply(v2.getX())).add(u3.getX().multiply(v3.getX()));
        array[0][1] = u1.getY().multiply(v1.getX()).add(u2.getY().multiply(v2.getX())).add(u3.getY().multiply(v3.getX()));
        array[0][2] = u1.getZ().multiply(v1.getX()).add(u2.getZ().multiply(v2.getX())).add(u3.getZ().multiply(v3.getX()));
        array[1][0] = u1.getX().multiply(v1.getY()).add(u2.getX().multiply(v2.getY())).add(u3.getX().multiply(v3.getY()));
        array[1][1] = u1.getY().multiply(v1.getY()).add(u2.getY().multiply(v2.getY())).add(u3.getY().multiply(v3.getY()));
        array[1][2] = u1.getZ().multiply(v1.getY()).add(u2.getZ().multiply(v2.getY())).add(u3.getZ().multiply(v3.getY()));
        array[2][0] = u1.getX().multiply(v1.getZ()).add(u2.getX().multiply(v2.getZ())).add(u3.getX().multiply(v3.getZ()));
        array[2][1] = u1.getY().multiply(v1.getZ()).add(u2.getY().multiply(v2.getZ())).add(u3.getY().multiply(v3.getZ()));
        array[2][2] = u1.getZ().multiply(v1.getZ()).add(u2.getZ().multiply(v2.getZ())).add(u3.getZ().multiply(v3.getZ()));
        T[] quat = mat2quat(array);
        q0 = quat[0];
        q1 = quat[1];
        q2 = quat[2];
        q3 = quat[3];
    }

    /**
     * Build one of the rotations that transform one vector into another one.
     *
     * <p>Except for a possible scale factor, if the instance were
     * applied to the vector u it will produce the vector v. There is an
     * infinite number of such rotations, this constructor choose the
     * one with the smallest associated angle (i.e. the one whose axis
     * is orthogonal to the (u, v) plane). If u and v are collinear, an
     * arbitrary rotation axis is chosen.</p>
     *
     * @param u origin vector
     * @param v desired image of u by the rotation
     * @exception MathArithmeticException if the norm of one of the vectors is zero
     */
    public FieldRotation(final FieldVector3D<T> u, final FieldVector3D<T> v) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_295");
        final T normProduct = u.getNorm().multiply(v.getNorm());
        if (ROR_equals(normProduct.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_295", _mut80782, _mut80783, _mut80784, _mut80785, _mut80786)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR);
        }
        final T dot = FieldVector3D.dotProduct(u, v);
        if (ROR_less(dot.getReal(), (AOR_multiply((AOR_minus(2.0e-15, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_295", _mut80787, _mut80788, _mut80789, _mut80790)), normProduct.getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_295", _mut80791, _mut80792, _mut80793, _mut80794)), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.FieldRotation_295", _mut80795, _mut80796, _mut80797, _mut80798, _mut80799)) {
            // an arbitrary vector orthogonal to u
            final FieldVector3D<T> w = u.orthogonal();
            q0 = normProduct.getField().getZero();
            q1 = w.getX().negate();
            q2 = w.getY().negate();
            q3 = w.getZ().negate();
        } else {
            // the shortest possible rotation: axis orthogonal to this plane
            q0 = dot.divide(normProduct).add(1.0).multiply(0.5).sqrt();
            final T coeff = q0.multiply(normProduct).multiply(2.0).reciprocal();
            final FieldVector3D<T> q = FieldVector3D.crossProduct(v, u);
            q1 = coeff.multiply(q.getX());
            q2 = coeff.multiply(q.getY());
            q3 = coeff.multiply(q.getZ());
        }
    }

    /**
     * Build a rotation from three Cardan or Euler elementary rotations.
     *
     * <p>Cardan rotations are three successive rotations around the
     * canonical axes X, Y and Z, each axis being used once. There are
     * 6 such sets of rotations (XYZ, XZY, YXZ, YZX, ZXY and ZYX). Euler
     * rotations are three successive rotations around the canonical
     * axes X, Y and Z, the first and last rotations being around the
     * same axis. There are 6 such sets of rotations (XYX, XZX, YXY,
     * YZY, ZXZ and ZYZ), the most popular one being ZXZ.</p>
     * <p>Beware that many people routinely use the term Euler angles even
     * for what really are Cardan angles (this confusion is especially
     * widespread in the aerospace business where Roll, Pitch and Yaw angles
     * are often wrongly tagged as Euler angles).</p>
     *
     * @param order order of rotations to use
     * @param alpha1 angle of the first elementary rotation
     * @param alpha2 angle of the second elementary rotation
     * @param alpha3 angle of the third elementary rotation
     * @deprecated as of 3.6, replaced with {@link
     * #FieldRotation(RotationOrder, RotationConvention,
     * RealFieldElement, RealFieldElement, RealFieldElement)}
     */
    @Deprecated
    public FieldRotation(final RotationOrder order, final T alpha1, final T alpha2, final T alpha3) {
        this(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3);
    }

    /**
     * Build a rotation from three Cardan or Euler elementary rotations.
     *
     * <p>Cardan rotations are three successive rotations around the
     * canonical axes X, Y and Z, each axis being used once. There are
     * 6 such sets of rotations (XYZ, XZY, YXZ, YZX, ZXY and ZYX). Euler
     * rotations are three successive rotations around the canonical
     * axes X, Y and Z, the first and last rotations being around the
     * same axis. There are 6 such sets of rotations (XYX, XZX, YXY,
     * YZY, ZXZ and ZYZ), the most popular one being ZXZ.</p>
     * <p>Beware that many people routinely use the term Euler angles even
     * for what really are Cardan angles (this confusion is especially
     * widespread in the aerospace business where Roll, Pitch and Yaw angles
     * are often wrongly tagged as Euler angles).</p>
     *
     * @param order order of rotations to compose, from left to right
     * (i.e. we will use {@code r1.compose(r2.compose(r3, convention), convention)})
     * @param convention convention to use for the semantics of the angle
     * @param alpha1 angle of the first elementary rotation
     * @param alpha2 angle of the second elementary rotation
     * @param alpha3 angle of the third elementary rotation
     * @since 3.6
     */
    public FieldRotation(final RotationOrder order, final RotationConvention convention, final T alpha1, final T alpha2, final T alpha3) {
        final T one = alpha1.getField().getOne();
        final FieldRotation<T> r1 = new FieldRotation<T>(new FieldVector3D<T>(one, order.getA1()), alpha1, convention);
        final FieldRotation<T> r2 = new FieldRotation<T>(new FieldVector3D<T>(one, order.getA2()), alpha2, convention);
        final FieldRotation<T> r3 = new FieldRotation<T>(new FieldVector3D<T>(one, order.getA3()), alpha3, convention);
        final FieldRotation<T> composed = r1.compose(r2.compose(r3, convention), convention);
        q0 = composed.q0;
        q1 = composed.q1;
        q2 = composed.q2;
        q3 = composed.q3;
    }

    /**
     * Convert an orthogonal rotation matrix to a quaternion.
     * @param ort orthogonal rotation matrix
     * @return quaternion corresponding to the matrix
     */
    private T[] mat2quat(final T[][] ort) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.mat2quat_391");
        final T[] quat = MathArrays.buildArray(ort[0][0].getField(), 4);
        // test since qi = 0.45 implies 4 qi^2 - 1 = -0.19)
        T s = ort[0][0].add(ort[1][1]).add(ort[2][2]);
        if (ROR_greater(s.getReal(), -0.19, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.mat2quat_391", _mut80800, _mut80801, _mut80802, _mut80803, _mut80804)) {
            // compute q0 and deduce q1, q2 and q3
            quat[0] = s.add(1.0).sqrt().multiply(0.5);
            T inv = quat[0].reciprocal().multiply(0.25);
            quat[1] = inv.multiply(ort[1][2].subtract(ort[2][1]));
            quat[2] = inv.multiply(ort[2][0].subtract(ort[0][2]));
            quat[3] = inv.multiply(ort[0][1].subtract(ort[1][0]));
        } else {
            s = ort[0][0].subtract(ort[1][1]).subtract(ort[2][2]);
            if (ROR_greater(s.getReal(), -0.19, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.mat2quat_391", _mut80805, _mut80806, _mut80807, _mut80808, _mut80809)) {
                // compute q1 and deduce q0, q2 and q3
                quat[1] = s.add(1.0).sqrt().multiply(0.5);
                T inv = quat[1].reciprocal().multiply(0.25);
                quat[0] = inv.multiply(ort[1][2].subtract(ort[2][1]));
                quat[2] = inv.multiply(ort[0][1].add(ort[1][0]));
                quat[3] = inv.multiply(ort[0][2].add(ort[2][0]));
            } else {
                s = ort[1][1].subtract(ort[0][0]).subtract(ort[2][2]);
                if (ROR_greater(s.getReal(), -0.19, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.mat2quat_391", _mut80810, _mut80811, _mut80812, _mut80813, _mut80814)) {
                    // compute q2 and deduce q0, q1 and q3
                    quat[2] = s.add(1.0).sqrt().multiply(0.5);
                    T inv = quat[2].reciprocal().multiply(0.25);
                    quat[0] = inv.multiply(ort[2][0].subtract(ort[0][2]));
                    quat[1] = inv.multiply(ort[0][1].add(ort[1][0]));
                    quat[3] = inv.multiply(ort[2][1].add(ort[1][2]));
                } else {
                    // compute q3 and deduce q0, q1 and q2
                    s = ort[2][2].subtract(ort[0][0]).subtract(ort[1][1]);
                    quat[3] = s.add(1.0).sqrt().multiply(0.5);
                    T inv = quat[3].reciprocal().multiply(0.25);
                    quat[0] = inv.multiply(ort[0][1].subtract(ort[1][0]));
                    quat[1] = inv.multiply(ort[0][2].add(ort[2][0]));
                    quat[2] = inv.multiply(ort[2][1].add(ort[1][2]));
                }
            }
        }
        return quat;
    }

    /**
     * Revert a rotation.
     * Build a rotation which reverse the effect of another
     * rotation. This means that if r(u) = v, then r.revert(v) = u. The
     * instance is not changed.
     * @return a new rotation whose effect is the reverse of the effect
     * of the instance
     */
    public FieldRotation<T> revert() {
        return new FieldRotation<T>(q0.negate(), q1, q2, q3, false);
    }

    /**
     * Get the scalar coordinate of the quaternion.
     * @return scalar coordinate of the quaternion
     */
    public T getQ0() {
        return q0;
    }

    /**
     * Get the first coordinate of the vectorial part of the quaternion.
     * @return first coordinate of the vectorial part of the quaternion
     */
    public T getQ1() {
        return q1;
    }

    /**
     * Get the second coordinate of the vectorial part of the quaternion.
     * @return second coordinate of the vectorial part of the quaternion
     */
    public T getQ2() {
        return q2;
    }

    /**
     * Get the third coordinate of the vectorial part of the quaternion.
     * @return third coordinate of the vectorial part of the quaternion
     */
    public T getQ3() {
        return q3;
    }

    /**
     * Get the normalized axis of the rotation.
     * @return normalized axis of the rotation
     * @see #FieldRotation(FieldVector3D, RealFieldElement)
     * @deprecated as of 3.6, replaced with {@link #getAxis(RotationConvention)}
     */
    @Deprecated
    public FieldVector3D<T> getAxis() {
        return getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Get the normalized axis of the rotation.
     * <p>
     * Note that as {@link #getAngle()} always returns an angle
     * between 0 and &pi;, changing the convention changes the
     * direction of the axis, not the sign of the angle.
     * </p>
     * @param convention convention to use for the semantics of the angle
     * @return normalized axis of the rotation
     * @see #FieldRotation(FieldVector3D, RealFieldElement)
     * @since 3.6
     */
    public FieldVector3D<T> getAxis(final RotationConvention convention) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAxis_508");
        final T squaredSine = q1.multiply(q1).add(q2.multiply(q2)).add(q3.multiply(q3));
        if (ROR_equals(squaredSine.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAxis_508", _mut80815, _mut80816, _mut80817, _mut80818, _mut80819)) {
            final Field<T> field = squaredSine.getField();
            return new FieldVector3D<T>(convention == RotationConvention.VECTOR_OPERATOR ? field.getOne() : field.getOne().negate(), field.getZero(), field.getZero());
        } else {
            final double sgn = convention == RotationConvention.VECTOR_OPERATOR ? +1 : -1;
            if (ROR_less(q0.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAxis_508", _mut80820, _mut80821, _mut80822, _mut80823, _mut80824)) {
                T inverse = squaredSine.sqrt().reciprocal().multiply(sgn);
                return new FieldVector3D<T>(q1.multiply(inverse), q2.multiply(inverse), q3.multiply(inverse));
            }
            final T inverse = squaredSine.sqrt().reciprocal().negate().multiply(sgn);
            return new FieldVector3D<T>(q1.multiply(inverse), q2.multiply(inverse), q3.multiply(inverse));
        }
    }

    /**
     * Get the angle of the rotation.
     * @return angle of the rotation (between 0 and &pi;)
     * @see #FieldRotation(FieldVector3D, RealFieldElement)
     */
    public T getAngle() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530");
        if ((_mut80835 ? ((ROR_less(q0.getReal(), -0.1, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530", _mut80825, _mut80826, _mut80827, _mut80828, _mut80829)) && (ROR_greater(q0.getReal(), 0.1, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530", _mut80830, _mut80831, _mut80832, _mut80833, _mut80834))) : ((ROR_less(q0.getReal(), -0.1, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530", _mut80825, _mut80826, _mut80827, _mut80828, _mut80829)) || (ROR_greater(q0.getReal(), 0.1, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530", _mut80830, _mut80831, _mut80832, _mut80833, _mut80834))))) {
            return q1.multiply(q1).add(q2.multiply(q2)).add(q3.multiply(q3)).sqrt().asin().multiply(2);
        } else if (ROR_less(q0.getReal(), 0, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngle_530", _mut80836, _mut80837, _mut80838, _mut80839, _mut80840)) {
            return q0.negate().acos().multiply(2);
        }
        return q0.acos().multiply(2);
    }

    /**
     * Get the Cardan or Euler angles corresponding to the instance.
     *
     * <p>The equations show that each rotation can be defined by two
     * different values of the Cardan or Euler angles set. For example
     * if Cardan angles are used, the rotation defined by the angles
     * a<sub>1</sub>, a<sub>2</sub> and a<sub>3</sub> is the same as
     * the rotation defined by the angles &pi; + a<sub>1</sub>, &pi;
     * - a<sub>2</sub> and &pi; + a<sub>3</sub>. This method implements
     * the following arbitrary choices:</p>
     * <ul>
     *   <li>for Cardan angles, the chosen set is the one for which the
     *   second angle is between -&pi;/2 and &pi;/2 (i.e its cosine is
     *   positive),</li>
     *   <li>for Euler angles, the chosen set is the one for which the
     *   second angle is between 0 and &pi; (i.e its sine is positive).</li>
     * </ul>
     *
     * <p>Cardan and Euler angle have a very disappointing drawback: all
     * of them have singularities. This means that if the instance is
     * too close to the singularities corresponding to the given
     * rotation order, it will be impossible to retrieve the angles. For
     * Cardan angles, this is often called gimbal lock. There is
     * <em>nothing</em> to do to prevent this, it is an intrinsic problem
     * with Cardan and Euler representation (but not a problem with the
     * rotation itself, which is perfectly well defined). For Cardan
     * angles, singularities occur when the second angle is close to
     * -&pi;/2 or +&pi;/2, for Euler angle singularities occur when the
     * second angle is close to 0 or &pi;, this implies that the identity
     * rotation is always singular for Euler angles!</p>
     *
     * @param order rotation order to use
     * @return an array of three angles, in the order specified by the set
     * @exception CardanEulerSingularityException if the rotation is
     * singular with respect to the angles set specified
     * @deprecated as of 3.6, replaced with {@link #getAngles(RotationOrder, RotationConvention)}
     */
    @Deprecated
    public T[] getAngles(final RotationOrder order) throws CardanEulerSingularityException {
        return getAngles(order, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Get the Cardan or Euler angles corresponding to the instance.
     *
     * <p>The equations show that each rotation can be defined by two
     * different values of the Cardan or Euler angles set. For example
     * if Cardan angles are used, the rotation defined by the angles
     * a<sub>1</sub>, a<sub>2</sub> and a<sub>3</sub> is the same as
     * the rotation defined by the angles &pi; + a<sub>1</sub>, &pi;
     * - a<sub>2</sub> and &pi; + a<sub>3</sub>. This method implements
     * the following arbitrary choices:</p>
     * <ul>
     *   <li>for Cardan angles, the chosen set is the one for which the
     *   second angle is between -&pi;/2 and &pi;/2 (i.e its cosine is
     *   positive),</li>
     *   <li>for Euler angles, the chosen set is the one for which the
     *   second angle is between 0 and &pi; (i.e its sine is positive).</li>
     * </ul>
     *
     * <p>Cardan and Euler angle have a very disappointing drawback: all
     * of them have singularities. This means that if the instance is
     * too close to the singularities corresponding to the given
     * rotation order, it will be impossible to retrieve the angles. For
     * Cardan angles, this is often called gimbal lock. There is
     * <em>nothing</em> to do to prevent this, it is an intrinsic problem
     * with Cardan and Euler representation (but not a problem with the
     * rotation itself, which is perfectly well defined). For Cardan
     * angles, singularities occur when the second angle is close to
     * -&pi;/2 or +&pi;/2, for Euler angle singularities occur when the
     * second angle is close to 0 or &pi;, this implies that the identity
     * rotation is always singular for Euler angles!</p>
     *
     * @param order rotation order to use
     * @param convention convention to use for the semantics of the angle
     * @return an array of three angles, in the order specified by the set
     * @exception CardanEulerSingularityException if the rotation is
     * singular with respect to the angles set specified
     * @since 3.6
     */
    public T[] getAngles(final RotationOrder order, RotationConvention convention) throws CardanEulerSingularityException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618");
        if (convention == RotationConvention.VECTOR_OPERATOR) {
            if (order == RotationOrder.XYZ) {
                // cos (psi) cos (theta), -sin (psi) cos (theta), sin (theta)
                final FieldVector3D<T> // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                v1 = applyTo(vector(0, 0, 1));
                final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
                if ((_mut81104 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81094, _mut81095, _mut81096, _mut81097, _mut81098)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81099, _mut81100, _mut81101, _mut81102, _mut81103))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81094, _mut81095, _mut81096, _mut81097, _mut81098)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81099, _mut81100, _mut81101, _mut81102, _mut81103))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getY().negate().atan2(v1.getZ()), v2.getZ().asin(), v2.getY().negate().atan2(v2.getX()));
            } else if (order == RotationOrder.XZY) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
                if ((_mut81093 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81083, _mut81084, _mut81085, _mut81086, _mut81087)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81088, _mut81089, _mut81090, _mut81091, _mut81092))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81083, _mut81084, _mut81085, _mut81086, _mut81087)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81088, _mut81089, _mut81090, _mut81091, _mut81092))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getZ().atan2(v1.getY()), v2.getY().asin().negate(), v2.getZ().atan2(v2.getX()));
            } else if (order == RotationOrder.YXZ) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
                if ((_mut81082 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81072, _mut81073, _mut81074, _mut81075, _mut81076)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81077, _mut81078, _mut81079, _mut81080, _mut81081))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81072, _mut81073, _mut81074, _mut81075, _mut81076)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81077, _mut81078, _mut81079, _mut81080, _mut81081))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getX().atan2(v1.getZ()), v2.getZ().asin().negate(), v2.getX().atan2(v2.getY()));
            } else if (order == RotationOrder.YZX) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
                if ((_mut81071 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81061, _mut81062, _mut81063, _mut81064, _mut81065)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81066, _mut81067, _mut81068, _mut81069, _mut81070))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81061, _mut81062, _mut81063, _mut81064, _mut81065)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81066, _mut81067, _mut81068, _mut81069, _mut81070))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getZ().negate().atan2(v1.getX()), v2.getX().asin(), v2.getZ().negate().atan2(v2.getY()));
            } else if (order == RotationOrder.ZXY) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
                if ((_mut81060 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81050, _mut81051, _mut81052, _mut81053, _mut81054)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81055, _mut81056, _mut81057, _mut81058, _mut81059))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81050, _mut81051, _mut81052, _mut81053, _mut81054)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81055, _mut81056, _mut81057, _mut81058, _mut81059))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getX().negate().atan2(v1.getY()), v2.getY().asin(), v2.getX().negate().atan2(v2.getZ()));
            } else if (order == RotationOrder.ZYX) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
                if ((_mut81049 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81039, _mut81040, _mut81041, _mut81042, _mut81043)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81044, _mut81045, _mut81046, _mut81047, _mut81048))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81039, _mut81040, _mut81041, _mut81042, _mut81043)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81044, _mut81045, _mut81046, _mut81047, _mut81048))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v1.getY().atan2(v1.getX()), v2.getX().asin().negate(), v2.getY().atan2(v2.getZ()));
            } else if (order == RotationOrder.XYX) {
                // and we can choose to have theta in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
                if ((_mut81038 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81028, _mut81029, _mut81030, _mut81031, _mut81032)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81033, _mut81034, _mut81035, _mut81036, _mut81037))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81028, _mut81029, _mut81030, _mut81031, _mut81032)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81033, _mut81034, _mut81035, _mut81036, _mut81037))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getY().atan2(v1.getZ().negate()), v2.getX().acos(), v2.getY().atan2(v2.getZ()));
            } else if (order == RotationOrder.XZX) {
                // and we can choose to have psi in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
                if ((_mut81027 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81017, _mut81018, _mut81019, _mut81020, _mut81021)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81022, _mut81023, _mut81024, _mut81025, _mut81026))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81017, _mut81018, _mut81019, _mut81020, _mut81021)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81022, _mut81023, _mut81024, _mut81025, _mut81026))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getZ().atan2(v1.getY()), v2.getX().acos(), v2.getZ().atan2(v2.getY().negate()));
            } else if (order == RotationOrder.YXY) {
                // and we can choose to have phi in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
                if ((_mut81016 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81006, _mut81007, _mut81008, _mut81009, _mut81010)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81011, _mut81012, _mut81013, _mut81014, _mut81015))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81006, _mut81007, _mut81008, _mut81009, _mut81010)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81011, _mut81012, _mut81013, _mut81014, _mut81015))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getX().atan2(v1.getZ()), v2.getY().acos(), v2.getX().atan2(v2.getZ().negate()));
            } else if (order == RotationOrder.YZY) {
                // and we can choose to have psi in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
                if ((_mut81005 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80995, _mut80996, _mut80997, _mut80998, _mut80999)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81000, _mut81001, _mut81002, _mut81003, _mut81004))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80995, _mut80996, _mut80997, _mut80998, _mut80999)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut81000, _mut81001, _mut81002, _mut81003, _mut81004))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getZ().atan2(v1.getX().negate()), v2.getY().acos(), v2.getZ().atan2(v2.getX()));
            } else if (order == RotationOrder.ZXZ) {
                // and we can choose to have phi in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
                if ((_mut80994 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80984, _mut80985, _mut80986, _mut80987, _mut80988)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80989, _mut80990, _mut80991, _mut80992, _mut80993))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80984, _mut80985, _mut80986, _mut80987, _mut80988)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80989, _mut80990, _mut80991, _mut80992, _mut80993))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getX().atan2(v1.getY().negate()), v2.getZ().acos(), v2.getX().atan2(v2.getY()));
            } else {
                // and we can choose to have theta in the interval [0 ; PI]
                final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
                final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
                if ((_mut80983 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80973, _mut80974, _mut80975, _mut80976, _mut80977)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80978, _mut80979, _mut80980, _mut80981, _mut80982))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80973, _mut80974, _mut80975, _mut80976, _mut80977)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80978, _mut80979, _mut80980, _mut80981, _mut80982))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v1.getY().atan2(v1.getX()), v2.getZ().acos(), v2.getY().atan2(v2.getX().negate()));
            }
        } else {
            if (order == RotationOrder.XYZ) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_I);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut80972 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80962, _mut80963, _mut80964, _mut80965, _mut80966)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80967, _mut80968, _mut80969, _mut80970, _mut80971))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80962, _mut80963, _mut80964, _mut80965, _mut80966)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80967, _mut80968, _mut80969, _mut80970, _mut80971))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getY().negate().atan2(v2.getZ()), v2.getX().asin(), v1.getY().negate().atan2(v1.getX()));
            } else if (order == RotationOrder.XZY) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_I);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut80961 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80951, _mut80952, _mut80953, _mut80954, _mut80955)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80956, _mut80957, _mut80958, _mut80959, _mut80960))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80951, _mut80952, _mut80953, _mut80954, _mut80955)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80956, _mut80957, _mut80958, _mut80959, _mut80960))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getZ().atan2(v2.getY()), v2.getX().asin().negate(), v1.getZ().atan2(v1.getX()));
            } else if (order == RotationOrder.YXZ) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_J);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut80950 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80940, _mut80941, _mut80942, _mut80943, _mut80944)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80945, _mut80946, _mut80947, _mut80948, _mut80949))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80940, _mut80941, _mut80942, _mut80943, _mut80944)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80945, _mut80946, _mut80947, _mut80948, _mut80949))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getX().atan2(v2.getZ()), v2.getY().asin().negate(), v1.getX().atan2(v1.getY()));
            } else if (order == RotationOrder.YZX) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_J);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut80939 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80929, _mut80930, _mut80931, _mut80932, _mut80933)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80934, _mut80935, _mut80936, _mut80937, _mut80938))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80929, _mut80930, _mut80931, _mut80932, _mut80933)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80934, _mut80935, _mut80936, _mut80937, _mut80938))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getZ().negate().atan2(v2.getX()), v2.getY().asin(), v1.getZ().negate().atan2(v1.getY()));
            } else if (order == RotationOrder.ZXY) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_K);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut80928 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80918, _mut80919, _mut80920, _mut80921, _mut80922)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80923, _mut80924, _mut80925, _mut80926, _mut80927))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80918, _mut80919, _mut80920, _mut80921, _mut80922)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80923, _mut80924, _mut80925, _mut80926, _mut80927))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getX().negate().atan2(v2.getY()), v2.getZ().asin(), v1.getX().negate().atan2(v1.getZ()));
            } else if (order == RotationOrder.ZYX) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_K);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut80917 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80907, _mut80908, _mut80909, _mut80910, _mut80911)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80912, _mut80913, _mut80914, _mut80915, _mut80916))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80907, _mut80908, _mut80909, _mut80910, _mut80911)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80912, _mut80913, _mut80914, _mut80915, _mut80916))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return buildArray(v2.getY().atan2(v2.getX()), v2.getZ().asin().negate(), v1.getY().atan2(v1.getZ()));
            } else if (order == RotationOrder.XYX) {
                // and we can choose to have theta in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_I);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut80906 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80896, _mut80897, _mut80898, _mut80899, _mut80900)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80901, _mut80902, _mut80903, _mut80904, _mut80905))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80896, _mut80897, _mut80898, _mut80899, _mut80900)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80901, _mut80902, _mut80903, _mut80904, _mut80905))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getY().atan2(v2.getZ().negate()), v2.getX().acos(), v1.getY().atan2(v1.getZ()));
            } else if (order == RotationOrder.XZX) {
                // and we can choose to have psi in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_I);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut80895 ? ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80885, _mut80886, _mut80887, _mut80888, _mut80889)) && (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80890, _mut80891, _mut80892, _mut80893, _mut80894))) : ((ROR_less(v2.getX().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80885, _mut80886, _mut80887, _mut80888, _mut80889)) || (ROR_greater(v2.getX().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80890, _mut80891, _mut80892, _mut80893, _mut80894))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getZ().atan2(v2.getY()), v2.getX().acos(), v1.getZ().atan2(v1.getY().negate()));
            } else if (order == RotationOrder.YXY) {
                // and we can choose to have phi in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_J);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut80884 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80874, _mut80875, _mut80876, _mut80877, _mut80878)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80879, _mut80880, _mut80881, _mut80882, _mut80883))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80874, _mut80875, _mut80876, _mut80877, _mut80878)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80879, _mut80880, _mut80881, _mut80882, _mut80883))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getX().atan2(v2.getZ()), v2.getY().acos(), v1.getX().atan2(v1.getZ().negate()));
            } else if (order == RotationOrder.YZY) {
                // and we can choose to have psi in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_J);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut80873 ? ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80863, _mut80864, _mut80865, _mut80866, _mut80867)) && (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80868, _mut80869, _mut80870, _mut80871, _mut80872))) : ((ROR_less(v2.getY().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80863, _mut80864, _mut80865, _mut80866, _mut80867)) || (ROR_greater(v2.getY().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80868, _mut80869, _mut80870, _mut80871, _mut80872))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getZ().atan2(v2.getX().negate()), v2.getY().acos(), v1.getZ().atan2(v1.getX()));
            } else if (order == RotationOrder.ZXZ) {
                // and we can choose to have phi in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_K);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut80862 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80852, _mut80853, _mut80854, _mut80855, _mut80856)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80857, _mut80858, _mut80859, _mut80860, _mut80861))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80852, _mut80853, _mut80854, _mut80855, _mut80856)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80857, _mut80858, _mut80859, _mut80860, _mut80861))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getX().atan2(v2.getY().negate()), v2.getZ().acos(), v1.getX().atan2(v1.getY()));
            } else {
                // and we can choose to have theta in the interval [0 ; PI]
                FieldVector3D<T> v1 = applyTo(Vector3D.PLUS_K);
                FieldVector3D<T> v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut80851 ? ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80841, _mut80842, _mut80843, _mut80844, _mut80845)) && (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80846, _mut80847, _mut80848, _mut80849, _mut80850))) : ((ROR_less(v2.getZ().getReal(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80841, _mut80842, _mut80843, _mut80844, _mut80845)) || (ROR_greater(v2.getZ().getReal(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.getAngles_618", _mut80846, _mut80847, _mut80848, _mut80849, _mut80850))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return buildArray(v2.getY().atan2(v2.getX()), v2.getZ().acos(), v1.getY().atan2(v1.getX().negate()));
            }
        }
    }

    /**
     * Create a dimension 3 array.
     * @param a0 first array element
     * @param a1 second array element
     * @param a2 third array element
     * @return new array
     */
    private T[] buildArray(final T a0, final T a1, final T a2) {
        final T[] array = MathArrays.buildArray(a0.getField(), 3);
        array[0] = a0;
        array[1] = a1;
        array[2] = a2;
        return array;
    }

    /**
     * Create a constant vector.
     * @param x abscissa
     * @param y ordinate
     * @param z height
     * @return a constant vector
     */
    private FieldVector3D<T> vector(final double x, final double y, final double z) {
        final T zero = q0.getField().getZero();
        return new FieldVector3D<T>(zero.add(x), zero.add(y), zero.add(z));
    }

    /**
     * Get the 3X3 matrix corresponding to the instance
     * @return the matrix corresponding to the instance
     */
    public T[][] getMatrix() {
        // products
        final T q0q0 = q0.multiply(q0);
        final T q0q1 = q0.multiply(q1);
        final T q0q2 = q0.multiply(q2);
        final T q0q3 = q0.multiply(q3);
        final T q1q1 = q1.multiply(q1);
        final T q1q2 = q1.multiply(q2);
        final T q1q3 = q1.multiply(q3);
        final T q2q2 = q2.multiply(q2);
        final T q2q3 = q2.multiply(q3);
        final T q3q3 = q3.multiply(q3);
        // create the matrix
        final T[][] m = MathArrays.buildArray(q0.getField(), 3, 3);
        m[0][0] = q0q0.add(q1q1).multiply(2).subtract(1);
        m[1][0] = q1q2.subtract(q0q3).multiply(2);
        m[2][0] = q1q3.add(q0q2).multiply(2);
        m[0][1] = q1q2.add(q0q3).multiply(2);
        m[1][1] = q0q0.add(q2q2).multiply(2).subtract(1);
        m[2][1] = q2q3.subtract(q0q1).multiply(2);
        m[0][2] = q1q3.subtract(q0q2).multiply(2);
        m[1][2] = q2q3.add(q0q1).multiply(2);
        m[2][2] = q0q0.add(q3q3).multiply(2).subtract(1);
        return m;
    }

    /**
     * Convert to a constant vector without derivatives.
     * @return a constant vector
     */
    public Rotation toRotation() {
        return new Rotation(q0.getReal(), q1.getReal(), q2.getReal(), q3.getReal(), false);
    }

    /**
     * Apply the rotation to a vector.
     * @param u vector to apply the rotation to
     * @return a new vector which is the image of u by the rotation
     */
    public FieldVector3D<T> applyTo(final FieldVector3D<T> u) {
        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        return new FieldVector3D<T>(q0.multiply(x.multiply(q0).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x), q0.multiply(y.multiply(q0).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y), q0.multiply(z.multiply(q0).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z));
    }

    /**
     * Apply the rotation to a vector.
     * @param u vector to apply the rotation to
     * @return a new vector which is the image of u by the rotation
     */
    public FieldVector3D<T> applyTo(final Vector3D u) {
        final double x = u.getX();
        final double y = u.getY();
        final double z = u.getZ();
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        return new FieldVector3D<T>(q0.multiply(q0.multiply(x).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x), q0.multiply(q0.multiply(y).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y), q0.multiply(q0.multiply(z).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z));
    }

    /**
     * Apply the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to (it can be the same
     * array as in)
     */
    public void applyTo(final T[] in, final T[] out) {
        final T x = in[0];
        final T y = in[1];
        final T z = in[2];
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        out[0] = q0.multiply(x.multiply(q0).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x);
        out[1] = q0.multiply(y.multiply(q0).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y);
        out[2] = q0.multiply(z.multiply(q0).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z);
    }

    /**
     * Apply the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to
     */
    public void applyTo(final double[] in, final T[] out) {
        final double x = in[0];
        final double y = in[1];
        final double z = in[2];
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        out[0] = q0.multiply(q0.multiply(x).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x);
        out[1] = q0.multiply(q0.multiply(y).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y);
        out[2] = q0.multiply(q0.multiply(z).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z);
    }

    /**
     * Apply a rotation to a vector.
     * @param r rotation to apply
     * @param u vector to apply the rotation to
     * @param <T> the type of the field elements
     * @return a new vector which is the image of u by the rotation
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyTo(final Rotation r, final FieldVector3D<T> u) {
        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();
        final T s = x.multiply(r.getQ1()).add(y.multiply(r.getQ2())).add(z.multiply(r.getQ3()));
        return new FieldVector3D<T>(x.multiply(r.getQ0()).subtract(z.multiply(r.getQ2()).subtract(y.multiply(r.getQ3()))).multiply(r.getQ0()).add(s.multiply(r.getQ1())).multiply(2).subtract(x), y.multiply(r.getQ0()).subtract(x.multiply(r.getQ3()).subtract(z.multiply(r.getQ1()))).multiply(r.getQ0()).add(s.multiply(r.getQ2())).multiply(2).subtract(y), z.multiply(r.getQ0()).subtract(y.multiply(r.getQ1()).subtract(x.multiply(r.getQ2()))).multiply(r.getQ0()).add(s.multiply(r.getQ3())).multiply(2).subtract(z));
    }

    /**
     * Apply the inverse of the rotation to a vector.
     * @param u vector to apply the inverse of the rotation to
     * @return a new vector which such that u is its image by the rotation
     */
    public FieldVector3D<T> applyInverseTo(final FieldVector3D<T> u) {
        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();
        return new FieldVector3D<T>(m0.multiply(x.multiply(m0).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x), m0.multiply(y.multiply(m0).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y), m0.multiply(z.multiply(m0).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z));
    }

    /**
     * Apply the inverse of the rotation to a vector.
     * @param u vector to apply the inverse of the rotation to
     * @return a new vector which such that u is its image by the rotation
     */
    public FieldVector3D<T> applyInverseTo(final Vector3D u) {
        final double x = u.getX();
        final double y = u.getY();
        final double z = u.getZ();
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();
        return new FieldVector3D<T>(m0.multiply(m0.multiply(x).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x), m0.multiply(m0.multiply(y).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y), m0.multiply(m0.multiply(z).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z));
    }

    /**
     * Apply the inverse of the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to (it can be the same
     * array as in)
     */
    public void applyInverseTo(final T[] in, final T[] out) {
        final T x = in[0];
        final T y = in[1];
        final T z = in[2];
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();
        out[0] = m0.multiply(x.multiply(m0).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x);
        out[1] = m0.multiply(y.multiply(m0).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y);
        out[2] = m0.multiply(z.multiply(m0).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z);
    }

    /**
     * Apply the inverse of the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to
     */
    public void applyInverseTo(final double[] in, final T[] out) {
        final double x = in[0];
        final double y = in[1];
        final double z = in[2];
        final T s = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();
        out[0] = m0.multiply(m0.multiply(x).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x);
        out[1] = m0.multiply(m0.multiply(y).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y);
        out[2] = m0.multiply(m0.multiply(z).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(q3)).multiply(2).subtract(z);
    }

    /**
     * Apply the inverse of a rotation to a vector.
     * @param r rotation to apply
     * @param u vector to apply the inverse of the rotation to
     * @param <T> the type of the field elements
     * @return a new vector which such that u is its image by the rotation
     */
    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(final Rotation r, final FieldVector3D<T> u) {
        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();
        final T s = x.multiply(r.getQ1()).add(y.multiply(r.getQ2())).add(z.multiply(r.getQ3()));
        final double m0 = -r.getQ0();
        return new FieldVector3D<T>(x.multiply(m0).subtract(z.multiply(r.getQ2()).subtract(y.multiply(r.getQ3()))).multiply(m0).add(s.multiply(r.getQ1())).multiply(2).subtract(x), y.multiply(m0).subtract(x.multiply(r.getQ3()).subtract(z.multiply(r.getQ1()))).multiply(m0).add(s.multiply(r.getQ2())).multiply(2).subtract(y), z.multiply(m0).subtract(y.multiply(r.getQ1()).subtract(x.multiply(r.getQ2()))).multiply(m0).add(s.multiply(r.getQ3())).multiply(2).subtract(z));
    }

    /**
     * Apply the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #compose(FieldRotation, RotationConvention)
     * compose(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     */
    public FieldRotation<T> applyTo(final FieldRotation<T> r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the instance to a rotation is computing the composition
     * in an order compliant with the following rule : let {@code u} be any
     * vector and {@code v} its image by {@code r1} (i.e.
     * {@code r1.applyTo(u) = v}). Let {@code w} be the image of {@code v} by
     * rotation {@code r2} (i.e. {@code r2.applyTo(v) = w}). Then
     * {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.compose(r1, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.compose(r2, RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the instance
     */
    public FieldRotation<T> compose(final FieldRotation<T> r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : r.composeInternal(this);
    }

    /**
     * Compose the instance with another rotation using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     * using vector operator convention
     */
    private FieldRotation<T> composeInternal(final FieldRotation<T> r) {
        return new FieldRotation<T>(r.q0.multiply(q0).subtract(r.q1.multiply(q1).add(r.q2.multiply(q2)).add(r.q3.multiply(q3))), r.q1.multiply(q0).add(r.q0.multiply(q1)).add(r.q2.multiply(q3).subtract(r.q3.multiply(q2))), r.q2.multiply(q0).add(r.q0.multiply(q2)).add(r.q3.multiply(q1).subtract(r.q1.multiply(q3))), r.q3.multiply(q0).add(r.q0.multiply(q3)).add(r.q1.multiply(q2).subtract(r.q2.multiply(q1))), false);
    }

    /**
     * Apply the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #compose(Rotation, RotationConvention)
     * compose(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     */
    public FieldRotation<T> applyTo(final Rotation r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the instance to a rotation is computing the composition
     * in an order compliant with the following rule : let {@code u} be any
     * vector and {@code v} its image by {@code r1} (i.e.
     * {@code r1.applyTo(u) = v}). Let {@code w} be the image of {@code v} by
     * rotation {@code r2} (i.e. {@code r2.applyTo(v) = w}). Then
     * {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.compose(r1, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.compose(r2, RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the instance
     */
    public FieldRotation<T> compose(final Rotation r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : applyTo(r, this);
    }

    /**
     * Compose the instance with another rotation using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     * using vector operator convention
     */
    private FieldRotation<T> composeInternal(final Rotation r) {
        return new FieldRotation<T>(q0.multiply(r.getQ0()).subtract(q1.multiply(r.getQ1()).add(q2.multiply(r.getQ2())).add(q3.multiply(r.getQ3()))), q0.multiply(r.getQ1()).add(q1.multiply(r.getQ0())).add(q3.multiply(r.getQ2()).subtract(q2.multiply(r.getQ3()))), q0.multiply(r.getQ2()).add(q2.multiply(r.getQ0())).add(q1.multiply(r.getQ3()).subtract(q3.multiply(r.getQ1()))), q0.multiply(r.getQ3()).add(q3.multiply(r.getQ0())).add(q2.multiply(r.getQ1()).subtract(q1.multiply(r.getQ2()))), false);
    }

    /**
     * Apply a rotation to another rotation.
     * Applying a rotation to another rotation is computing the composition
     * in an order compliant with the following rule : let u be any
     * vector and v its image by rInner (i.e. rInner.applyTo(u) = v), let w be the image
     * of v by rOuter (i.e. rOuter.applyTo(v) = w), then w = comp.applyTo(u),
     * where comp = applyTo(rOuter, rInner).
     * @param r1 rotation to apply
     * @param rInner rotation to apply the rotation to
     * @param <T> the type of the field elements
     * @return a new rotation which is the composition of r by the instance
     */
    public static <T extends RealFieldElement<T>> FieldRotation<T> applyTo(final Rotation r1, final FieldRotation<T> rInner) {
        return new FieldRotation<T>(rInner.q0.multiply(r1.getQ0()).subtract(rInner.q1.multiply(r1.getQ1()).add(rInner.q2.multiply(r1.getQ2())).add(rInner.q3.multiply(r1.getQ3()))), rInner.q1.multiply(r1.getQ0()).add(rInner.q0.multiply(r1.getQ1())).add(rInner.q2.multiply(r1.getQ3()).subtract(rInner.q3.multiply(r1.getQ2()))), rInner.q2.multiply(r1.getQ0()).add(rInner.q0.multiply(r1.getQ2())).add(rInner.q3.multiply(r1.getQ1()).subtract(rInner.q1.multiply(r1.getQ3()))), rInner.q3.multiply(r1.getQ0()).add(rInner.q0.multiply(r1.getQ3())).add(rInner.q1.multiply(r1.getQ2()).subtract(rInner.q2.multiply(r1.getQ1()))), false);
    }

    /**
     * Apply the inverse of the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #composeInverse(FieldRotation, RotationConvention)
     * composeInverse(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public FieldRotation<T> applyInverseTo(final FieldRotation<T> r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the inverse of the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the inverse of the instance to a rotation is computing
     * the composition in an order compliant with the following rule :
     * let {@code u} be any vector and {@code v} its image by {@code r1}
     * (i.e. {@code r1.applyTo(u) = v}). Let {@code w} be the inverse image
     * of {@code v} by {@code r2} (i.e. {@code r2.applyInverseTo(v) = w}).
     * Then {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.composeInverse(r1)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed, which means it is the
     * <em>innermost</em> rotation that will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.revert().composeInverse(r2.revert(), RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public FieldRotation<T> composeInverse(final FieldRotation<T> r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : r.composeInternal(revert());
    }

    /**
     * Compose the inverse of the instance with another rotation
     * using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance using vector operator convention
     */
    private FieldRotation<T> composeInverseInternal(FieldRotation<T> r) {
        return new FieldRotation<T>(r.q0.multiply(q0).add(r.q1.multiply(q1).add(r.q2.multiply(q2)).add(r.q3.multiply(q3))).negate(), r.q0.multiply(q1).add(r.q2.multiply(q3).subtract(r.q3.multiply(q2))).subtract(r.q1.multiply(q0)), r.q0.multiply(q2).add(r.q3.multiply(q1).subtract(r.q1.multiply(q3))).subtract(r.q2.multiply(q0)), r.q0.multiply(q3).add(r.q1.multiply(q2).subtract(r.q2.multiply(q1))).subtract(r.q3.multiply(q0)), false);
    }

    /**
     * Apply the inverse of the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #composeInverse(Rotation, RotationConvention)
     * composeInverse(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public FieldRotation<T> applyInverseTo(final Rotation r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the inverse of the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the inverse of the instance to a rotation is computing
     * the composition in an order compliant with the following rule :
     * let {@code u} be any vector and {@code v} its image by {@code r1}
     * (i.e. {@code r1.applyTo(u) = v}). Let {@code w} be the inverse image
     * of {@code v} by {@code r2} (i.e. {@code r2.applyInverseTo(v) = w}).
     * Then {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.composeInverse(r1)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed, which means it is the
     * <em>innermost</em> rotation that will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.revert().composeInverse(r2.revert(), RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public FieldRotation<T> composeInverse(final Rotation r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : applyTo(r, revert());
    }

    /**
     * Compose the inverse of the instance with another rotation
     * using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance using vector operator convention
     */
    private FieldRotation<T> composeInverseInternal(Rotation r) {
        return new FieldRotation<T>(q0.multiply(r.getQ0()).add(q1.multiply(r.getQ1()).add(q2.multiply(r.getQ2())).add(q3.multiply(r.getQ3()))).negate(), q1.multiply(r.getQ0()).add(q3.multiply(r.getQ2()).subtract(q2.multiply(r.getQ3()))).subtract(q0.multiply(r.getQ1())), q2.multiply(r.getQ0()).add(q1.multiply(r.getQ3()).subtract(q3.multiply(r.getQ1()))).subtract(q0.multiply(r.getQ2())), q3.multiply(r.getQ0()).add(q2.multiply(r.getQ1()).subtract(q1.multiply(r.getQ2()))).subtract(q0.multiply(r.getQ3())), false);
    }

    /**
     * Apply the inverse of a rotation to another rotation.
     * Applying the inverse of a rotation to another rotation is computing
     * the composition in an order compliant with the following rule :
     * let u be any vector and v its image by rInner (i.e. rInner.applyTo(u) = v),
     * let w be the inverse image of v by rOuter
     * (i.e. rOuter.applyInverseTo(v) = w), then w = comp.applyTo(u), where
     * comp = applyInverseTo(rOuter, rInner).
     * @param rOuter rotation to apply the rotation to
     * @param rInner rotation to apply the rotation to
     * @param <T> the type of the field elements
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public static <T extends RealFieldElement<T>> FieldRotation<T> applyInverseTo(final Rotation rOuter, final FieldRotation<T> rInner) {
        return new FieldRotation<T>(rInner.q0.multiply(rOuter.getQ0()).add(rInner.q1.multiply(rOuter.getQ1()).add(rInner.q2.multiply(rOuter.getQ2())).add(rInner.q3.multiply(rOuter.getQ3()))).negate(), rInner.q0.multiply(rOuter.getQ1()).add(rInner.q2.multiply(rOuter.getQ3()).subtract(rInner.q3.multiply(rOuter.getQ2()))).subtract(rInner.q1.multiply(rOuter.getQ0())), rInner.q0.multiply(rOuter.getQ2()).add(rInner.q3.multiply(rOuter.getQ1()).subtract(rInner.q1.multiply(rOuter.getQ3()))).subtract(rInner.q2.multiply(rOuter.getQ0())), rInner.q0.multiply(rOuter.getQ3()).add(rInner.q1.multiply(rOuter.getQ2()).subtract(rInner.q2.multiply(rOuter.getQ1()))).subtract(rInner.q3.multiply(rOuter.getQ0())), false);
    }

    /**
     * Perfect orthogonality on a 3X3 matrix.
     * @param m initial matrix (not exactly orthogonal)
     * @param threshold convergence threshold for the iterative
     * orthogonality correction (convergence is reached when the
     * difference between two steps of the Frobenius norm of the
     * correction is below this threshold)
     * @return an orthogonal matrix close to m
     * @exception NotARotationMatrixException if the matrix cannot be
     * orthogonalized with the given threshold after 10 iterations
     */
    private T[][] orthogonalizeMatrix(final T[][] m, final double threshold) throws NotARotationMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550");
        T x00 = m[0][0];
        T x01 = m[0][1];
        T x02 = m[0][2];
        T x10 = m[1][0];
        T x11 = m[1][1];
        T x12 = m[1][2];
        T x20 = m[2][0];
        T x21 = m[2][1];
        T x22 = m[2][2];
        double fn = 0;
        double fn1;
        final T[][] o = MathArrays.buildArray(m[0][0].getField(), 3, 3);
        // iterative correction: Xn+1 = Xn - 0.5 * (Xn.Mt.Xn - M)
        int i = 0;
        while (ROR_less(++i, 11, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81218, _mut81219, _mut81220, _mut81221, _mut81222)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550");
            // Mt.Xn
            final T mx00 = m[0][0].multiply(x00).add(m[1][0].multiply(x10)).add(m[2][0].multiply(x20));
            final T mx10 = m[0][1].multiply(x00).add(m[1][1].multiply(x10)).add(m[2][1].multiply(x20));
            final T mx20 = m[0][2].multiply(x00).add(m[1][2].multiply(x10)).add(m[2][2].multiply(x20));
            final T mx01 = m[0][0].multiply(x01).add(m[1][0].multiply(x11)).add(m[2][0].multiply(x21));
            final T mx11 = m[0][1].multiply(x01).add(m[1][1].multiply(x11)).add(m[2][1].multiply(x21));
            final T mx21 = m[0][2].multiply(x01).add(m[1][2].multiply(x11)).add(m[2][2].multiply(x21));
            final T mx02 = m[0][0].multiply(x02).add(m[1][0].multiply(x12)).add(m[2][0].multiply(x22));
            final T mx12 = m[0][1].multiply(x02).add(m[1][1].multiply(x12)).add(m[2][1].multiply(x22));
            final T mx22 = m[0][2].multiply(x02).add(m[1][2].multiply(x12)).add(m[2][2].multiply(x22));
            // Xn+1
            o[0][0] = x00.subtract(x00.multiply(mx00).add(x01.multiply(mx10)).add(x02.multiply(mx20)).subtract(m[0][0]).multiply(0.5));
            o[0][1] = x01.subtract(x00.multiply(mx01).add(x01.multiply(mx11)).add(x02.multiply(mx21)).subtract(m[0][1]).multiply(0.5));
            o[0][2] = x02.subtract(x00.multiply(mx02).add(x01.multiply(mx12)).add(x02.multiply(mx22)).subtract(m[0][2]).multiply(0.5));
            o[1][0] = x10.subtract(x10.multiply(mx00).add(x11.multiply(mx10)).add(x12.multiply(mx20)).subtract(m[1][0]).multiply(0.5));
            o[1][1] = x11.subtract(x10.multiply(mx01).add(x11.multiply(mx11)).add(x12.multiply(mx21)).subtract(m[1][1]).multiply(0.5));
            o[1][2] = x12.subtract(x10.multiply(mx02).add(x11.multiply(mx12)).add(x12.multiply(mx22)).subtract(m[1][2]).multiply(0.5));
            o[2][0] = x20.subtract(x20.multiply(mx00).add(x21.multiply(mx10)).add(x22.multiply(mx20)).subtract(m[2][0]).multiply(0.5));
            o[2][1] = x21.subtract(x20.multiply(mx01).add(x21.multiply(mx11)).add(x22.multiply(mx21)).subtract(m[2][1]).multiply(0.5));
            o[2][2] = x22.subtract(x20.multiply(mx02).add(x21.multiply(mx12)).add(x22.multiply(mx22)).subtract(m[2][2]).multiply(0.5));
            // correction on each elements
            final double corr00 = AOR_minus(o[0][0].getReal(), m[0][0].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81105, _mut81106, _mut81107, _mut81108);
            final double corr01 = AOR_minus(o[0][1].getReal(), m[0][1].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81109, _mut81110, _mut81111, _mut81112);
            final double corr02 = AOR_minus(o[0][2].getReal(), m[0][2].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81113, _mut81114, _mut81115, _mut81116);
            final double corr10 = AOR_minus(o[1][0].getReal(), m[1][0].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81117, _mut81118, _mut81119, _mut81120);
            final double corr11 = AOR_minus(o[1][1].getReal(), m[1][1].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81121, _mut81122, _mut81123, _mut81124);
            final double corr12 = AOR_minus(o[1][2].getReal(), m[1][2].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81125, _mut81126, _mut81127, _mut81128);
            final double corr20 = AOR_minus(o[2][0].getReal(), m[2][0].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81129, _mut81130, _mut81131, _mut81132);
            final double corr21 = AOR_minus(o[2][1].getReal(), m[2][1].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81133, _mut81134, _mut81135, _mut81136);
            final double corr22 = AOR_minus(o[2][2].getReal(), m[2][2].getReal(), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81137, _mut81138, _mut81139, _mut81140);
            // Frobenius norm of the correction
            fn1 = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(corr00, corr00, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81141, _mut81142, _mut81143, _mut81144), AOR_multiply(corr01, corr01, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81145, _mut81146, _mut81147, _mut81148), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81149, _mut81150, _mut81151, _mut81152), AOR_multiply(corr02, corr02, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81153, _mut81154, _mut81155, _mut81156), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81157, _mut81158, _mut81159, _mut81160), AOR_multiply(corr10, corr10, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81161, _mut81162, _mut81163, _mut81164), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81165, _mut81166, _mut81167, _mut81168), AOR_multiply(corr11, corr11, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81169, _mut81170, _mut81171, _mut81172), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81173, _mut81174, _mut81175, _mut81176), AOR_multiply(corr12, corr12, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81177, _mut81178, _mut81179, _mut81180), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81181, _mut81182, _mut81183, _mut81184), AOR_multiply(corr20, corr20, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81185, _mut81186, _mut81187, _mut81188), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81189, _mut81190, _mut81191, _mut81192), AOR_multiply(corr21, corr21, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81193, _mut81194, _mut81195, _mut81196), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81197, _mut81198, _mut81199, _mut81200), AOR_multiply(corr22, corr22, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81201, _mut81202, _mut81203, _mut81204), "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81205, _mut81206, _mut81207, _mut81208);
            // convergence test
            if (ROR_less_equals(FastMath.abs(AOR_minus(fn1, fn, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81209, _mut81210, _mut81211, _mut81212)), threshold, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81213, _mut81214, _mut81215, _mut81216, _mut81217)) {
                return o;
            }
            // prepare next iteration
            x00 = o[0][0];
            x01 = o[0][1];
            x02 = o[0][2];
            x10 = o[1][0];
            x11 = o[1][1];
            x12 = o[1][2];
            x20 = o[2][0];
            x21 = o[2][1];
            x22 = o[2][2];
            fn = fn1;
        }
        // the algorithm did not converge after 10 iterations
        throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, AOR_minus(i, 1, "org.apache.commons.math3.geometry.euclidean.threed.FieldRotation.orthogonalizeMatrix_1550", _mut81223, _mut81224, _mut81225, _mut81226));
    }

    /**
     * Compute the <i>distance</i> between two rotations.
     * <p>The <i>distance</i> is intended here as a way to check if two
     * rotations are almost similar (i.e. they transform vectors the same way)
     * or very different. It is mathematically defined as the angle of
     * the rotation r that prepended to one of the rotations gives the other
     * one:</p>
     * <pre>
     *        r<sub>1</sub>(r) = r<sub>2</sub>
     * </pre>
     * <p>This distance is an angle between 0 and &pi;. Its value is the smallest
     * possible upper bound of the angle in radians between r<sub>1</sub>(v)
     * and r<sub>2</sub>(v) for all possible vectors v. This upper bound is
     * reached for some v. The distance is equal to 0 if and only if the two
     * rotations are identical.</p>
     * <p>Comparing two rotations should always be done using this value rather
     * than for example comparing the components of the quaternions. It is much
     * more stable, and has a geometric meaning. Also comparing quaternions
     * components is error prone since for example quaternions (0.36, 0.48, -0.48, -0.64)
     * and (-0.36, -0.48, 0.48, 0.64) represent exactly the same rotation despite
     * their components are different (they are exact opposites).</p>
     * @param r1 first rotation
     * @param r2 second rotation
     * @param <T> the type of the field elements
     * @return <i>distance</i> between r1 and r2
     */
    public static <T extends RealFieldElement<T>> T distance(final FieldRotation<T> r1, final FieldRotation<T> r2) {
        return r1.composeInverseInternal(r2).getAngle();
    }
}
