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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Generates a bicubic interpolating function. Due to numerical accuracy issues this should not
 * be used.
 *
 * @since 2.2
 * @deprecated as of 3.4 replaced by {@link org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator}
 */
@Deprecated
public class BicubicSplineInterpolator implements BivariateGridInterpolator {

    @Conditional
    public static boolean _mut93834 = false, _mut93835 = false, _mut93836 = false, _mut93837 = false, _mut93838 = false, _mut93839 = false, _mut93840 = false, _mut93841 = false, _mut93842 = false, _mut93843 = false, _mut93844 = false, _mut93845 = false, _mut93846 = false, _mut93847 = false, _mut93848 = false, _mut93849 = false, _mut93850 = false, _mut93851 = false, _mut93852 = false, _mut93853 = false, _mut93854 = false, _mut93855 = false, _mut93856 = false, _mut93857 = false, _mut93858 = false, _mut93859 = false, _mut93860 = false, _mut93861 = false, _mut93862 = false, _mut93863 = false, _mut93864 = false, _mut93865 = false, _mut93866 = false, _mut93867 = false, _mut93868 = false, _mut93869 = false, _mut93870 = false, _mut93871 = false, _mut93872 = false, _mut93873 = false, _mut93874 = false, _mut93875 = false, _mut93876 = false, _mut93877 = false, _mut93878 = false, _mut93879 = false, _mut93880 = false, _mut93881 = false, _mut93882 = false, _mut93883 = false, _mut93884 = false, _mut93885 = false, _mut93886 = false, _mut93887 = false, _mut93888 = false, _mut93889 = false, _mut93890 = false, _mut93891 = false, _mut93892 = false, _mut93893 = false, _mut93894 = false, _mut93895 = false, _mut93896 = false, _mut93897 = false, _mut93898 = false, _mut93899 = false, _mut93900 = false, _mut93901 = false, _mut93902 = false, _mut93903 = false, _mut93904 = false, _mut93905 = false, _mut93906 = false, _mut93907 = false, _mut93908 = false, _mut93909 = false, _mut93910 = false, _mut93911 = false, _mut93912 = false, _mut93913 = false, _mut93914 = false, _mut93915 = false, _mut93916 = false, _mut93917 = false, _mut93918 = false, _mut93919 = false, _mut93920 = false, _mut93921 = false, _mut93922 = false, _mut93923 = false, _mut93924 = false, _mut93925 = false, _mut93926 = false, _mut93927 = false, _mut93928 = false, _mut93929 = false, _mut93930 = false, _mut93931 = false, _mut93932 = false, _mut93933 = false, _mut93934 = false, _mut93935 = false, _mut93936 = false, _mut93937 = false, _mut93938 = false, _mut93939 = false, _mut93940 = false, _mut93941 = false, _mut93942 = false, _mut93943 = false, _mut93944 = false, _mut93945 = false, _mut93946 = false, _mut93947 = false, _mut93948 = false, _mut93949 = false, _mut93950 = false, _mut93951 = false, _mut93952 = false, _mut93953 = false, _mut93954 = false, _mut93955 = false, _mut93956 = false, _mut93957 = false, _mut93958 = false, _mut93959 = false, _mut93960 = false;

    /**
     * Whether to initialize internal data used to compute the analytical
     *        derivatives of the splines.
     */
    private final boolean initializeDerivatives;

    /**
     * Default constructor.
     * The argument {@link #BicubicSplineInterpolator(boolean) initializeDerivatives}
     * is set to {@code false}.
     */
    public BicubicSplineInterpolator() {
        this(false);
    }

    /**
     * Creates an interpolator.
     *
     * @param initializeDerivatives Whether to initialize the internal data
     * needed for calling any of the methods that compute the partial derivatives
     * of the {@link BicubicSplineInterpolatingFunction function} returned from
     * the call to {@link #interpolate(double[],double[],double[][]) interpolate}.
     */
    public BicubicSplineInterpolator(boolean initializeDerivatives) {
        this.initializeDerivatives = initializeDerivatives;
    }

    /**
     * {@inheritDoc}
     */
    public BicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
        if ((_mut93850 ? ((_mut93844 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93834, _mut93835, _mut93836, _mut93837, _mut93838) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93839, _mut93840, _mut93841, _mut93842, _mut93843)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93834, _mut93835, _mut93836, _mut93837, _mut93838) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93839, _mut93840, _mut93841, _mut93842, _mut93843))) && ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93845, _mut93846, _mut93847, _mut93848, _mut93849)) : ((_mut93844 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93834, _mut93835, _mut93836, _mut93837, _mut93838) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93839, _mut93840, _mut93841, _mut93842, _mut93843)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93834, _mut93835, _mut93836, _mut93837, _mut93838) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93839, _mut93840, _mut93841, _mut93842, _mut93843))) || ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93845, _mut93846, _mut93847, _mut93848, _mut93849)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, fval.length, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93851, _mut93852, _mut93853, _mut93854, _mut93855)) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        final int xLen = xval.length;
        final int yLen = yval.length;
        // fX[j][i] = f(xval[i], yval[j])
        final double[][] fX = new double[yLen][xLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93866, _mut93867, _mut93868, _mut93869, _mut93870); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            if (ROR_not_equals(fval[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93856, _mut93857, _mut93858, _mut93859, _mut93860)) {
                throw new DimensionMismatchException(fval[i].length, yLen);
            }
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93861, _mut93862, _mut93863, _mut93864, _mut93865); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
                fX[j][i] = fval[i][j];
            }
        }
        final SplineInterpolator spInterpolator = new SplineInterpolator();
        // respect to variable x
        final PolynomialSplineFunction[] ySplineX = new PolynomialSplineFunction[yLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93871, _mut93872, _mut93873, _mut93874, _mut93875); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            ySplineX[j] = spInterpolator.interpolate(xval, fX[j]);
        }
        // respect to variable y generated by array fY_1[i]
        final PolynomialSplineFunction[] xSplineY = new PolynomialSplineFunction[xLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93876, _mut93877, _mut93878, _mut93879, _mut93880); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            xSplineY[i] = spInterpolator.interpolate(yval, fval[i]);
        }
        // Partial derivatives with respect to x at the grid knots
        final double[][] dFdX = new double[xLen][yLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93886, _mut93887, _mut93888, _mut93889, _mut93890); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            final UnivariateFunction f = ySplineX[j].derivative();
            for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93881, _mut93882, _mut93883, _mut93884, _mut93885); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
                dFdX[i][j] = f.value(xval[i]);
            }
        }
        // Partial derivatives with respect to y at the grid knots
        final double[][] dFdY = new double[xLen][yLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93896, _mut93897, _mut93898, _mut93899, _mut93900); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            final UnivariateFunction f = xSplineY[i].derivative();
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93891, _mut93892, _mut93893, _mut93894, _mut93895); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
                dFdY[i][j] = f.value(yval[j]);
            }
        }
        // Cross partial derivatives
        final double[][] d2FdXdY = new double[xLen][yLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93934, _mut93935, _mut93936, _mut93937, _mut93938); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
            final int nI = nextIndex(i, xLen);
            final int pI = previousIndex(i);
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93929, _mut93930, _mut93931, _mut93932, _mut93933); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65");
                final int nJ = nextIndex(j, yLen);
                final int pJ = previousIndex(j);
                d2FdXdY[i][j] = AOR_divide((AOR_plus(AOR_minus(AOR_minus(fval[nI][nJ], fval[nI][pJ], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93901, _mut93902, _mut93903, _mut93904), fval[pI][nJ], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93905, _mut93906, _mut93907, _mut93908), fval[pI][pJ], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93909, _mut93910, _mut93911, _mut93912)), (AOR_multiply((AOR_minus(xval[nI], xval[pI], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93913, _mut93914, _mut93915, _mut93916)), (AOR_minus(yval[nJ], yval[pJ], "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93917, _mut93918, _mut93919, _mut93920)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93921, _mut93922, _mut93923, _mut93924)), "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.interpolate_65", _mut93925, _mut93926, _mut93927, _mut93928);
            }
        }
        // Create the interpolating splines
        return new BicubicSplineInterpolatingFunction(xval, yval, fval, dFdX, dFdY, d2FdXdY, initializeDerivatives);
    }

    /**
     * Computes the next index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i >= 0}.
     *
     * @param i Index.
     * @param max Upper limit of the array.
     * @return the next index.
     */
    private int nextIndex(int i, int max) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.nextIndex_160");
        final int index = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.nextIndex_160", _mut93939, _mut93940, _mut93941, _mut93942);
        return ROR_less(index, max, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.nextIndex_160", _mut93943, _mut93944, _mut93945, _mut93946, _mut93947) ? index : AOR_minus(index, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.nextIndex_160", _mut93948, _mut93949, _mut93950, _mut93951);
    }

    /**
     * Computes the previous index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is smaller than the size
     * of the array.
     *
     * @param i Index.
     * @return the previous index.
     */
    private int previousIndex(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.previousIndex_172");
        final int index = AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.previousIndex_172", _mut93952, _mut93953, _mut93954, _mut93955);
        return ROR_greater_equals(index, 0, "org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator.previousIndex_172", _mut93956, _mut93957, _mut93958, _mut93959, _mut93960) ? index : 0;
    }
}
