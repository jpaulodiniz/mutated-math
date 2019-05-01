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

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the QR-decomposition of a matrix.
 * <p>The QR-decomposition of a matrix A consists of two matrices Q and R
 * that satisfy: A = QR, Q is orthogonal (Q<sup>T</sup>Q = I), and R is
 * upper triangular. If A is m&times;n, Q is m&times;m and R m&times;n.</p>
 * <p>This class compute the decomposition using Householder reflectors.</p>
 * <p>For efficiency purposes, the decomposition in packed form is transposed.
 * This allows inner loop to iterate inside rows, which is much more cache-efficient
 * in Java.</p>
 * <p>This class is based on the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library, with the
 * following changes:</p>
 * <ul>
 *   <li>a {@link #getQT() getQT} method has been added,</li>
 *   <li>the {@code solve} and {@code isFullRank} methods have been replaced
 *   by a {@link #getSolver() getSolver} method and the equivalent methods
 *   provided by the returned {@link DecompositionSolver}.</li>
 * </ul>
 *
 * @see <a href="http://mathworld.wolfram.com/QRDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/QR_decomposition">Wikipedia</a>
 *
 * @since 1.2 (changed to concrete class in 3.0)
 */
public class QRDecomposition {

    @Conditional
    public static boolean _mut22706 = false, _mut22707 = false, _mut22708 = false, _mut22709 = false, _mut22710 = false, _mut22711 = false, _mut22712 = false, _mut22713 = false, _mut22714 = false, _mut22715 = false, _mut22716 = false, _mut22717 = false, _mut22718 = false, _mut22719 = false, _mut22720 = false, _mut22721 = false, _mut22722 = false, _mut22723 = false, _mut22724 = false, _mut22725 = false, _mut22726 = false, _mut22727 = false, _mut22728 = false, _mut22729 = false, _mut22730 = false, _mut22731 = false, _mut22732 = false, _mut22733 = false, _mut22734 = false, _mut22735 = false, _mut22736 = false, _mut22737 = false, _mut22738 = false, _mut22739 = false, _mut22740 = false, _mut22741 = false, _mut22742 = false, _mut22743 = false, _mut22744 = false, _mut22745 = false, _mut22746 = false, _mut22747 = false, _mut22748 = false, _mut22749 = false, _mut22750 = false, _mut22751 = false, _mut22752 = false, _mut22753 = false, _mut22754 = false, _mut22755 = false, _mut22756 = false, _mut22757 = false, _mut22758 = false, _mut22759 = false, _mut22760 = false, _mut22761 = false, _mut22762 = false, _mut22763 = false, _mut22764 = false, _mut22765 = false, _mut22766 = false, _mut22767 = false, _mut22768 = false, _mut22769 = false, _mut22770 = false, _mut22771 = false, _mut22772 = false, _mut22773 = false, _mut22774 = false, _mut22775 = false, _mut22776 = false, _mut22777 = false, _mut22778 = false, _mut22779 = false, _mut22780 = false, _mut22781 = false, _mut22782 = false, _mut22783 = false, _mut22784 = false, _mut22785 = false, _mut22786 = false, _mut22787 = false, _mut22788 = false, _mut22789 = false, _mut22790 = false, _mut22791 = false, _mut22792 = false, _mut22793 = false, _mut22794 = false, _mut22795 = false, _mut22796 = false, _mut22797 = false, _mut22798 = false, _mut22799 = false, _mut22800 = false, _mut22801 = false, _mut22802 = false, _mut22803 = false, _mut22804 = false, _mut22805 = false, _mut22806 = false, _mut22807 = false, _mut22808 = false, _mut22809 = false, _mut22810 = false, _mut22811 = false, _mut22812 = false, _mut22813 = false, _mut22814 = false, _mut22815 = false, _mut22816 = false, _mut22817 = false, _mut22818 = false, _mut22819 = false, _mut22820 = false, _mut22821 = false, _mut22822 = false, _mut22823 = false, _mut22824 = false, _mut22825 = false, _mut22826 = false, _mut22827 = false, _mut22828 = false, _mut22829 = false, _mut22830 = false, _mut22831 = false, _mut22832 = false, _mut22833 = false, _mut22834 = false, _mut22835 = false, _mut22836 = false, _mut22837 = false, _mut22838 = false, _mut22839 = false, _mut22840 = false, _mut22841 = false, _mut22842 = false, _mut22843 = false, _mut22844 = false, _mut22845 = false, _mut22846 = false, _mut22847 = false, _mut22848 = false, _mut22849 = false, _mut22850 = false, _mut22851 = false, _mut22852 = false, _mut22853 = false, _mut22854 = false, _mut22855 = false, _mut22856 = false, _mut22857 = false, _mut22858 = false, _mut22859 = false, _mut22860 = false, _mut22861 = false, _mut22862 = false, _mut22863 = false, _mut22864 = false, _mut22865 = false, _mut22866 = false, _mut22867 = false, _mut22868 = false, _mut22869 = false, _mut22870 = false, _mut22871 = false, _mut22872 = false, _mut22873 = false, _mut22874 = false, _mut22875 = false, _mut22876 = false, _mut22877 = false, _mut22878 = false, _mut22879 = false, _mut22880 = false, _mut22881 = false, _mut22882 = false, _mut22883 = false, _mut22884 = false, _mut22885 = false, _mut22886 = false, _mut22887 = false, _mut22888 = false, _mut22889 = false, _mut22890 = false, _mut22891 = false, _mut22892 = false, _mut22893 = false, _mut22894 = false, _mut22895 = false, _mut22896 = false, _mut22897 = false, _mut22898 = false, _mut22899 = false, _mut22900 = false, _mut22901 = false, _mut22902 = false, _mut22903 = false, _mut22904 = false, _mut22905 = false, _mut22906 = false, _mut22907 = false, _mut22908 = false, _mut22909 = false, _mut22910 = false, _mut22911 = false, _mut22912 = false, _mut22913 = false, _mut22914 = false, _mut22915 = false, _mut22916 = false, _mut22917 = false, _mut22918 = false, _mut22919 = false, _mut22920 = false, _mut22921 = false, _mut22922 = false, _mut22923 = false, _mut22924 = false, _mut22925 = false, _mut22926 = false, _mut22927 = false, _mut22928 = false, _mut22929 = false, _mut22930 = false, _mut22931 = false, _mut22932 = false, _mut22933 = false, _mut22934 = false, _mut22935 = false, _mut22936 = false, _mut22937 = false, _mut22938 = false, _mut22939 = false, _mut22940 = false, _mut22941 = false, _mut22942 = false, _mut22943 = false, _mut22944 = false, _mut22945 = false, _mut22946 = false, _mut22947 = false, _mut22948 = false, _mut22949 = false, _mut22950 = false, _mut22951 = false, _mut22952 = false, _mut22953 = false, _mut22954 = false, _mut22955 = false, _mut22956 = false, _mut22957 = false, _mut22958 = false, _mut22959 = false, _mut22960 = false, _mut22961 = false, _mut22962 = false, _mut22963 = false, _mut22964 = false, _mut22965 = false, _mut22966 = false, _mut22967 = false, _mut22968 = false, _mut22969 = false, _mut22970 = false, _mut22971 = false, _mut22972 = false, _mut22973 = false, _mut22974 = false, _mut22975 = false, _mut22976 = false, _mut22977 = false, _mut22978 = false, _mut22979 = false, _mut22980 = false, _mut22981 = false, _mut22982 = false, _mut22983 = false, _mut22984 = false, _mut22985 = false, _mut22986 = false, _mut22987 = false, _mut22988 = false, _mut22989 = false, _mut22990 = false, _mut22991 = false, _mut22992 = false, _mut22993 = false, _mut22994 = false, _mut22995 = false, _mut22996 = false, _mut22997 = false, _mut22998 = false, _mut22999 = false, _mut23000 = false, _mut23001 = false, _mut23002 = false, _mut23003 = false, _mut23004 = false, _mut23005 = false, _mut23006 = false, _mut23007 = false, _mut23008 = false, _mut23009 = false, _mut23010 = false, _mut23011 = false, _mut23012 = false, _mut23013 = false, _mut23014 = false, _mut23015 = false, _mut23016 = false, _mut23017 = false;

    /**
     * A packed TRANSPOSED representation of the QR decomposition.
     * <p>The elements BELOW the diagonal are the elements of the UPPER triangular
     * matrix R, and the rows ABOVE the diagonal are the Householder reflector vectors
     * from which an explicit form of Q can be recomputed if desired.</p>
     */
    private double[][] qrt;

    /**
     * The diagonal elements of R.
     */
    private double[] rDiag;

    /**
     * Cached value of Q.
     */
    private RealMatrix cachedQ;

    /**
     * Cached value of QT.
     */
    private RealMatrix cachedQT;

    /**
     * Cached value of R.
     */
    private RealMatrix cachedR;

    /**
     * Cached value of H.
     */
    private RealMatrix cachedH;

    /**
     * Singularity threshold.
     */
    private final double threshold;

    /**
     * Calculates the QR-decomposition of the given matrix.
     * The singularity threshold defaults to zero.
     *
     * @param matrix The matrix to decompose.
     *
     * @see #QRDecomposition(RealMatrix,double)
     */
    public QRDecomposition(RealMatrix matrix) {
        this(matrix, 0d);
    }

    /**
     * Calculates the QR-decomposition of the given matrix.
     *
     * @param matrix The matrix to decompose.
     * @param threshold Singularity threshold.
     */
    public QRDecomposition(RealMatrix matrix, double threshold) {
        this.threshold = threshold;
        final int m = matrix.getRowDimension();
        final int n = matrix.getColumnDimension();
        qrt = matrix.transpose().getData();
        rDiag = new double[FastMath.min(m, n)];
        cachedQ = null;
        cachedQT = null;
        cachedR = null;
        cachedH = null;
        decompose(qrt);
    }

    /**
     * Decompose matrix.
     * @param matrix transposed matrix
     * @since 3.2
     */
    protected void decompose(double[][] matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.decompose_110");
        for (int minor = 0; ROR_less(minor, FastMath.min(matrix.length, matrix[0].length), "org.apache.commons.math3.linear.QRDecomposition.decompose_110", _mut22706, _mut22707, _mut22708, _mut22709, _mut22710); minor++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.decompose_110");
            performHouseholderReflection(minor, matrix);
        }
    }

    /**
     * Perform Householder reflection for a minor A(minor, minor) of A.
     * @param minor minor index
     * @param matrix transposed matrix
     * @since 3.2
     */
    protected void performHouseholderReflection(int minor, double[][] matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121");
        final double[] qrtMinor = matrix[minor];
        /*
         * Let x be the first column of the minor, and a^2 = |x|^2.
         * x will be in the positions qr[minor][minor] through qr[m][minor].
         * The first column of the transformed minor will be (a,0,0,..)'
         * The sign of a is chosen to be opposite to the sign of the first
         * component of x. Let's find a:
         */
        double xNormSqr = 0;
        for (int row = minor; ROR_less(row, qrtMinor.length, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22715, _mut22716, _mut22717, _mut22718, _mut22719); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121");
            final double c = qrtMinor[row];
            xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22711, _mut22712, _mut22713, _mut22714);
        }
        final double a = (ROR_greater(qrtMinor[minor], 0, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22720, _mut22721, _mut22722, _mut22723, _mut22724)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
        rDiag[minor] = a;
        if (ROR_not_equals(a, 0.0, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22725, _mut22726, _mut22727, _mut22728, _mut22729)) {
            // now |v|^2 = -2a*(qr[minor][minor])
            qrtMinor[minor] -= a;
            /*
             * Transform the rest of the columns of the minor:
             * They will be transformed by the matrix H = I-2vv'/|v|^2.
             * If x is a column vector of the minor, then
             * Hx = (I-2vv'/|v|^2)x = x-2vv'x/|v|^2 = x - 2<x,v>/|v|^2 v.
             * Therefore the transformation is easily calculated by
             * subtracting the column vector (2<x,v>/|v|^2)v from x.
             *
             * Let 2<x,v>/|v|^2 = alpha. From above we have
             * |v|^2 = -2a*(qr[minor][minor]), so
             * alpha = -<x,v>/(a*qr[minor][minor])
             */
            for (int col = minor + 1; ROR_less(col, matrix.length, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22752, _mut22753, _mut22754, _mut22755, _mut22756); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121");
                final double[] qrtCol = matrix[col];
                double alpha = 0;
                for (int row = minor; ROR_less(row, qrtCol.length, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22734, _mut22735, _mut22736, _mut22737, _mut22738); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121");
                    alpha -= AOR_multiply(qrtCol[row], qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22730, _mut22731, _mut22732, _mut22733);
                }
                alpha /= AOR_multiply(a, qrtMinor[minor], "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22739, _mut22740, _mut22741, _mut22742);
                // Subtract the column vector alpha*v from x.
                for (int row = minor; ROR_less(row, qrtCol.length, "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22747, _mut22748, _mut22749, _mut22750, _mut22751); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121");
                    qrtCol[row] -= AOR_multiply(alpha, qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.performHouseholderReflection_121", _mut22743, _mut22744, _mut22745, _mut22746);
                }
            }
        }
    }

    /**
     * Returns the matrix R of the decomposition.
     * <p>R is an upper-triangular matrix</p>
     * @return the R matrix
     */
    public RealMatrix getR() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getR_186");
        if (cachedR == null) {
            // R is supposed to be m x n
            final int n = qrt.length;
            final int m = qrt[0].length;
            double[][] ra = new double[m][n];
            // copy the diagonal from rDiag and the upper triangle of qr
            for (int row = FastMath.min(m, n) - 1; ROR_greater_equals(row, 0, "org.apache.commons.math3.linear.QRDecomposition.getR_186", _mut22762, _mut22763, _mut22764, _mut22765, _mut22766); row--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getR_186");
                ra[row][row] = rDiag[row];
                for (int col = row + 1; ROR_less(col, n, "org.apache.commons.math3.linear.QRDecomposition.getR_186", _mut22757, _mut22758, _mut22759, _mut22760, _mut22761); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getR_186");
                    ra[row][col] = qrt[col][row];
                }
            }
            cachedR = MatrixUtils.createRealMatrix(ra);
        }
        // return the cached matrix
        return cachedR;
    }

    /**
     * Returns the matrix Q of the decomposition.
     * <p>Q is an orthogonal matrix</p>
     * @return the Q matrix
     */
    public RealMatrix getQ() {
        if (cachedQ == null) {
            cachedQ = getQT().transpose();
        }
        return cachedQ;
    }

    /**
     * Returns the transpose of the matrix Q of the decomposition.
     * <p>Q is an orthogonal matrix</p>
     * @return the transpose of the Q matrix, Q<sup>T</sup>
     */
    public RealMatrix getQT() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
        if (cachedQT == null) {
            // QT is supposed to be m x m
            final int n = qrt.length;
            final int m = qrt[0].length;
            double[][] qta = new double[m][m];
            /*
             * Q = Q1 Q2 ... Q_m, so Q is formed by first constructing Q_m and then
             * applying the Householder transformations Q_(m-1),Q_(m-2),...,Q1 in
             * succession to the result
             */
            for (int minor = m - 1; ROR_greater_equals(minor, FastMath.min(m, n), "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22767, _mut22768, _mut22769, _mut22770, _mut22771); minor--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
                qta[minor][minor] = 1.0d;
            }
            for (int minor = FastMath.min(m, n) - 1; ROR_greater_equals(minor, 0, "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22804, _mut22805, _mut22806, _mut22807, _mut22808); minor--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
                final double[] qrtMinor = qrt[minor];
                qta[minor][minor] = 1.0d;
                if (ROR_not_equals(qrtMinor[minor], 0.0, "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22772, _mut22773, _mut22774, _mut22775, _mut22776)) {
                    for (int col = minor; ROR_less(col, m, "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22799, _mut22800, _mut22801, _mut22802, _mut22803); col++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
                        double alpha = 0;
                        for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22781, _mut22782, _mut22783, _mut22784, _mut22785); row++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
                            alpha -= AOR_multiply(qta[col][row], qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22777, _mut22778, _mut22779, _mut22780);
                        }
                        alpha /= AOR_multiply(rDiag[minor], qrtMinor[minor], "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22786, _mut22787, _mut22788, _mut22789);
                        for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22794, _mut22795, _mut22796, _mut22797, _mut22798); row++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getQT_225");
                            qta[col][row] += AOR_multiply(-alpha, qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.getQT_225", _mut22790, _mut22791, _mut22792, _mut22793);
                        }
                    }
                }
            }
            cachedQT = MatrixUtils.createRealMatrix(qta);
        }
        // return the cached matrix
        return cachedQT;
    }

    /**
     * Returns the Householder reflector vectors.
     * <p>H is a lower trapezoidal matrix whose columns represent
     * each successive Householder reflector vector. This matrix is used
     * to compute Q.</p>
     * @return a matrix containing the Householder reflector vectors
     */
    public RealMatrix getH() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getH_273");
        if (cachedH == null) {
            final int n = qrt.length;
            final int m = qrt[0].length;
            double[][] ha = new double[m][n];
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.QRDecomposition.getH_273", _mut22822, _mut22823, _mut22824, _mut22825, _mut22826); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getH_273");
                for (int j = 0; ROR_less(j, FastMath.min(AOR_plus(i, 1, "org.apache.commons.math3.linear.QRDecomposition.getH_273", _mut22813, _mut22814, _mut22815, _mut22816), n), "org.apache.commons.math3.linear.QRDecomposition.getH_273", _mut22817, _mut22818, _mut22819, _mut22820, _mut22821); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.getH_273");
                    ha[i][j] = AOR_divide(qrt[j][i], -rDiag[j], "org.apache.commons.math3.linear.QRDecomposition.getH_273", _mut22809, _mut22810, _mut22811, _mut22812);
                }
            }
            cachedH = MatrixUtils.createRealMatrix(ha);
        }
        // return the cached matrix
        return cachedH;
    }

    /**
     * Get a solver for finding the A &times; X = B solution in least square sense.
     * <p>
     * Least Square sense means a solver can be computed for an overdetermined system,
     * (i.e. a system with more equations than unknowns, which corresponds to a tall A
     * matrix with more rows than columns). In any case, if the matrix is singular
     * within the tolerance set at {@link QRDecomposition#QRDecomposition(RealMatrix,
     * double) construction}, an error will be triggered when
     * the {@link DecompositionSolver#solve(RealVector) solve} method will be called.
     * </p>
     * @return a solver
     */
    public DecompositionSolver getSolver() {
        return new Solver(qrt, rDiag, threshold);
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * A packed TRANSPOSED representation of the QR decomposition.
         * <p>The elements BELOW the diagonal are the elements of the UPPER triangular
         * matrix R, and the rows ABOVE the diagonal are the Householder reflector vectors
         * from which an explicit form of Q can be recomputed if desired.</p>
         */
        private final double[][] qrt;

        /**
         * The diagonal elements of R.
         */
        private final double[] rDiag;

        /**
         * Singularity threshold.
         */
        private final double threshold;

        /**
         * Build a solver from decomposed matrix.
         *
         * @param qrt Packed TRANSPOSED representation of the QR decomposition.
         * @param rDiag Diagonal elements of R.
         * @param threshold Singularity threshold.
         */
        private Solver(final double[][] qrt, final double[] rDiag, final double threshold) {
            this.qrt = qrt;
            this.rDiag = rDiag;
            this.threshold = threshold;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNonSingular() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.isNonSingular_337");
            for (double diag : rDiag) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.isNonSingular_337");
                if (ROR_less_equals(FastMath.abs(diag), threshold, "org.apache.commons.math3.linear.QRDecomposition.isNonSingular_337", _mut22827, _mut22828, _mut22829, _mut22830, _mut22831)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public RealVector solve(RealVector b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
            final int n = qrt.length;
            final int m = qrt[0].length;
            if (ROR_not_equals(b.getDimension(), m, "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22832, _mut22833, _mut22834, _mut22835, _mut22836)) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            final double[] x = new double[n];
            final double[] y = b.toArray();
            // apply Householder transforms to solve Q.y = b
            for (int minor = 0; ROR_less(minor, FastMath.min(m, n), "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22859, _mut22860, _mut22861, _mut22862, _mut22863); minor++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
                final double[] qrtMinor = qrt[minor];
                double dotProduct = 0;
                for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22841, _mut22842, _mut22843, _mut22844, _mut22845); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
                    dotProduct += AOR_multiply(y[row], qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22837, _mut22838, _mut22839, _mut22840);
                }
                dotProduct /= AOR_multiply(rDiag[minor], qrtMinor[minor], "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22846, _mut22847, _mut22848, _mut22849);
                for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22854, _mut22855, _mut22856, _mut22857, _mut22858); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
                    y[row] += AOR_multiply(dotProduct, qrtMinor[row], "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22850, _mut22851, _mut22852, _mut22853);
                }
            }
            // solve triangular system R.x = y
            for (int row = rDiag.length - 1; ROR_greater_equals(row, 0, "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22873, _mut22874, _mut22875, _mut22876, _mut22877); --row) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
                y[row] /= rDiag[row];
                final double yRow = y[row];
                final double[] qrtRow = qrt[row];
                x[row] = yRow;
                for (int i = 0; ROR_less(i, row, "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22868, _mut22869, _mut22870, _mut22871, _mut22872); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_347");
                    y[i] -= AOR_multiply(yRow, qrtRow[i], "org.apache.commons.math3.linear.QRDecomposition.solve_347", _mut22864, _mut22865, _mut22866, _mut22867);
                }
            }
            return new ArrayRealVector(x, false);
        }

        /**
         * {@inheritDoc}
         */
        public RealMatrix solve(RealMatrix b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
            final int n = qrt.length;
            final int m = qrt[0].length;
            if (ROR_not_equals(b.getRowDimension(), m, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22878, _mut22879, _mut22880, _mut22881, _mut22882)) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            final int columns = b.getColumnDimension();
            final int blockSize = BlockRealMatrix.BLOCK_SIZE;
            final int cBlocks = AOR_divide((AOR_minus(AOR_plus(columns, blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22883, _mut22884, _mut22885, _mut22886), 1, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22887, _mut22888, _mut22889, _mut22890)), blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22891, _mut22892, _mut22893, _mut22894);
            final double[][] xBlocks = BlockRealMatrix.createBlocksLayout(n, columns);
            final double[][] y = new double[b.getRowDimension()][blockSize];
            final double[] alpha = new double[blockSize];
            for (int kBlock = 0; ROR_less(kBlock, cBlocks, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut23013, _mut23014, _mut23015, _mut23016, _mut23017); ++kBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                final int kStart = AOR_multiply(kBlock, blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22895, _mut22896, _mut22897, _mut22898);
                final int kEnd = FastMath.min(AOR_plus(kStart, blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22899, _mut22900, _mut22901, _mut22902), columns);
                final int kWidth = AOR_minus(kEnd, kStart, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22903, _mut22904, _mut22905, _mut22906);
                // get the right hand side vector
                b.copySubMatrix(0, AOR_minus(m, 1, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22907, _mut22908, _mut22909, _mut22910), kStart, AOR_minus(kEnd, 1, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22911, _mut22912, _mut22913, _mut22914), y);
                // apply Householder transforms to solve Q.y = b
                for (int minor = 0; ROR_less(minor, FastMath.min(m, n), "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22956, _mut22957, _mut22958, _mut22959, _mut22960); minor++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                    final double[] qrtMinor = qrt[minor];
                    final double factor = AOR_divide(1.0, (AOR_multiply(rDiag[minor], qrtMinor[minor], "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22915, _mut22916, _mut22917, _mut22918)), "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22919, _mut22920, _mut22921, _mut22922);
                    Arrays.fill(alpha, 0, kWidth, 0.0);
                    for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22932, _mut22933, _mut22934, _mut22935, _mut22936); ++row) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                        final double d = qrtMinor[row];
                        final double[] yRow = y[row];
                        for (int k = 0; ROR_less(k, kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22927, _mut22928, _mut22929, _mut22930, _mut22931); ++k) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                            alpha[k] += AOR_multiply(d, yRow[k], "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22923, _mut22924, _mut22925, _mut22926);
                        }
                    }
                    for (int k = 0; ROR_less(k, kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22937, _mut22938, _mut22939, _mut22940, _mut22941); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                        alpha[k] *= factor;
                    }
                    for (int row = minor; ROR_less(row, m, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22951, _mut22952, _mut22953, _mut22954, _mut22955); ++row) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                        final double d = qrtMinor[row];
                        final double[] yRow = y[row];
                        for (int k = 0; ROR_less(k, kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22946, _mut22947, _mut22948, _mut22949, _mut22950); ++k) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                            yRow[k] += AOR_multiply(alpha[k], d, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22942, _mut22943, _mut22944, _mut22945);
                        }
                    }
                }
                // solve triangular system R.x = y
                for (int j = rDiag.length - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut23008, _mut23009, _mut23010, _mut23011, _mut23012); --j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                    final int jBlock = AOR_divide(j, blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22961, _mut22962, _mut22963, _mut22964);
                    final int jStart = AOR_multiply(jBlock, blockSize, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22965, _mut22966, _mut22967, _mut22968);
                    final double factor = AOR_divide(1.0, rDiag[j], "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22969, _mut22970, _mut22971, _mut22972);
                    final double[] yJ = y[j];
                    final double[] xBlock = xBlocks[AOR_plus(AOR_multiply(jBlock, cBlocks, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22973, _mut22974, _mut22975, _mut22976), kBlock, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22977, _mut22978, _mut22979, _mut22980)];
                    int index = AOR_multiply((AOR_minus(j, jStart, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22981, _mut22982, _mut22983, _mut22984)), kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22985, _mut22986, _mut22987, _mut22988);
                    for (int k = 0; ROR_less(k, kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22989, _mut22990, _mut22991, _mut22992, _mut22993); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                        yJ[k] *= factor;
                        xBlock[index++] = yJ[k];
                    }
                    final double[] qrtJ = qrt[j];
                    for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut23003, _mut23004, _mut23005, _mut23006, _mut23007); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                        final double rIJ = qrtJ[i];
                        final double[] yI = y[i];
                        for (int k = 0; ROR_less(k, kWidth, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22998, _mut22999, _mut23000, _mut23001, _mut23002); ++k) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.QRDecomposition.solve_390");
                            yI[k] -= AOR_multiply(yJ[k], rIJ, "org.apache.commons.math3.linear.QRDecomposition.solve_390", _mut22994, _mut22995, _mut22996, _mut22997);
                        }
                    }
                }
            }
            return new BlockRealMatrix(n, columns, xBlocks, false);
        }

        /**
         * {@inheritDoc}
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(qrt[0].length));
        }
    }
}
