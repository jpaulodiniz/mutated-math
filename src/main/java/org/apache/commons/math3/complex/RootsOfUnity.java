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
package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A helper class for the computation and caching of the {@code n}-th roots of
 * unity.
 *
 * @since 3.0
 */
public class RootsOfUnity implements Serializable {

    @Conditional
    public static boolean _mut37839 = false, _mut37840 = false, _mut37841 = false, _mut37842 = false, _mut37843 = false, _mut37844 = false, _mut37845 = false, _mut37846 = false, _mut37847 = false, _mut37848 = false, _mut37849 = false, _mut37850 = false, _mut37851 = false, _mut37852 = false, _mut37853 = false, _mut37854 = false, _mut37855 = false, _mut37856 = false, _mut37857 = false, _mut37858 = false, _mut37859 = false, _mut37860 = false, _mut37861 = false, _mut37862 = false, _mut37863 = false, _mut37864 = false, _mut37865 = false, _mut37866 = false, _mut37867 = false, _mut37868 = false, _mut37869 = false, _mut37870 = false, _mut37871 = false, _mut37872 = false, _mut37873 = false, _mut37874 = false, _mut37875 = false, _mut37876 = false, _mut37877 = false, _mut37878 = false, _mut37879 = false, _mut37880 = false, _mut37881 = false, _mut37882 = false, _mut37883 = false, _mut37884 = false, _mut37885 = false, _mut37886 = false, _mut37887 = false, _mut37888 = false, _mut37889 = false, _mut37890 = false, _mut37891 = false, _mut37892 = false, _mut37893 = false, _mut37894 = false, _mut37895 = false, _mut37896 = false, _mut37897 = false, _mut37898 = false, _mut37899 = false, _mut37900 = false, _mut37901 = false, _mut37902 = false, _mut37903 = false, _mut37904 = false, _mut37905 = false, _mut37906 = false, _mut37907 = false, _mut37908 = false, _mut37909 = false, _mut37910 = false, _mut37911 = false, _mut37912 = false, _mut37913 = false, _mut37914 = false, _mut37915 = false, _mut37916 = false, _mut37917 = false, _mut37918 = false, _mut37919 = false, _mut37920 = false, _mut37921 = false, _mut37922 = false, _mut37923 = false, _mut37924 = false, _mut37925 = false, _mut37926 = false, _mut37927 = false, _mut37928 = false, _mut37929 = false, _mut37930 = false, _mut37931 = false, _mut37932 = false, _mut37933 = false, _mut37934 = false, _mut37935 = false, _mut37936 = false, _mut37937 = false, _mut37938 = false, _mut37939 = false, _mut37940 = false, _mut37941 = false, _mut37942 = false, _mut37943 = false, _mut37944 = false, _mut37945 = false, _mut37946 = false, _mut37947 = false, _mut37948 = false, _mut37949 = false, _mut37950 = false, _mut37951 = false;

    /**
     * Serializable version id.
     */
    private static final long serialVersionUID = 20120201L;

    /**
     * Number of roots of unity.
     */
    private int omegaCount;

    /**
     * Real part of the roots.
     */
    private double[] omegaReal;

    /**
     * Imaginary part of the {@code n}-th roots of unity, for positive values
     * of {@code n}. In this array, the roots are stored in counter-clockwise
     * order.
     */
    private double[] omegaImaginaryCounterClockwise;

    /**
     * Imaginary part of the {@code n}-th roots of unity, for negative values
     * of {@code n}. In this array, the roots are stored in clockwise order.
     */
    private double[] omegaImaginaryClockwise;

    /**
     * {@code true} if {@link #computeRoots(int)} was called with a positive
     * value of its argument {@code n}. In this case, counter-clockwise ordering
     * of the roots of unity should be used.
     */
    private boolean isCounterClockWise;

    /**
     * Build an engine for computing the {@code n}-th roots of unity.
     */
    public RootsOfUnity() {
        omegaCount = 0;
        omegaReal = null;
        omegaImaginaryCounterClockwise = null;
        omegaImaginaryClockwise = null;
        isCounterClockWise = true;
    }

    /**
     * Returns {@code true} if {@link #computeRoots(int)} was called with a
     * positive value of its argument {@code n}. If {@code true}, then
     * counter-clockwise ordering of the roots of unity should be used.
     *
     * @return {@code true} if the roots of unity are stored in
     * counter-clockwise order
     * @throws MathIllegalStateException if no roots of unity have been computed
     * yet
     */
    public synchronized boolean isCounterClockWise() throws MathIllegalStateException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.RootsOfUnity.isCounterClockWise_87");
        if (ROR_equals(omegaCount, 0, "org.apache.commons.math3.complex.RootsOfUnity.isCounterClockWise_87", _mut37839, _mut37840, _mut37841, _mut37842, _mut37843)) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET);
        }
        return isCounterClockWise;
    }

    /**
     * <p>
     * Computes the {@code n}-th roots of unity. The roots are stored in
     * {@code omega[]}, such that {@code omega[k] = w ^ k}, where
     * {@code k = 0, ..., n - 1}, {@code w = exp(2 * pi * i / n)} and
     * {@code i = sqrt(-1)}.
     * </p>
     * <p>
     * Note that {@code n} can be positive of negative
     * </p>
     * <ul>
     * <li>{@code abs(n)} is always the number of roots of unity.</li>
     * <li>If {@code n > 0}, then the roots are stored in counter-clockwise order.</li>
     * <li>If {@code n < 0}, then the roots are stored in clockwise order.</p>
     * </ul>
     *
     * @param n the (signed) number of roots of unity to be computed
     * @throws ZeroException if {@code n = 0}
     */
    public synchronized void computeRoots(int n) throws ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116");
        if (ROR_equals(n, 0, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37844, _mut37845, _mut37846, _mut37847, _mut37848)) {
            throw new ZeroException(LocalizedFormats.CANNOT_COMPUTE_0TH_ROOT_OF_UNITY);
        }
        isCounterClockWise = ROR_greater(n, 0, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37849, _mut37850, _mut37851, _mut37852, _mut37853);
        // avoid repetitive calculations
        final int absN = FastMath.abs(n);
        if (ROR_equals(absN, omegaCount, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37854, _mut37855, _mut37856, _mut37857, _mut37858)) {
            return;
        }
        // calculate everything from scratch
        final double t = AOR_divide(AOR_multiply(2.0, FastMath.PI, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37859, _mut37860, _mut37861, _mut37862), absN, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37863, _mut37864, _mut37865, _mut37866);
        final double cosT = FastMath.cos(t);
        final double sinT = FastMath.sin(t);
        omegaReal = new double[absN];
        omegaImaginaryCounterClockwise = new double[absN];
        omegaImaginaryClockwise = new double[absN];
        omegaReal[0] = 1.0;
        omegaImaginaryCounterClockwise[0] = 0.0;
        omegaImaginaryClockwise[0] = 0.0;
        for (int i = 1; ROR_less(i, absN, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37907, _mut37908, _mut37909, _mut37910, _mut37911); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116");
            omegaReal[i] = AOR_minus(AOR_multiply(omegaReal[AOR_minus(i, 1, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37867, _mut37868, _mut37869, _mut37870)], cosT, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37871, _mut37872, _mut37873, _mut37874), AOR_multiply(omegaImaginaryCounterClockwise[AOR_minus(i, 1, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37875, _mut37876, _mut37877, _mut37878)], sinT, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37879, _mut37880, _mut37881, _mut37882), "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37883, _mut37884, _mut37885, _mut37886);
            omegaImaginaryCounterClockwise[i] = AOR_plus(AOR_multiply(omegaReal[AOR_minus(i, 1, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37887, _mut37888, _mut37889, _mut37890)], sinT, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37891, _mut37892, _mut37893, _mut37894), AOR_multiply(omegaImaginaryCounterClockwise[AOR_minus(i, 1, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37895, _mut37896, _mut37897, _mut37898)], cosT, "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37899, _mut37900, _mut37901, _mut37902), "org.apache.commons.math3.complex.RootsOfUnity.computeRoots_116", _mut37903, _mut37904, _mut37905, _mut37906);
            omegaImaginaryClockwise[i] = -omegaImaginaryCounterClockwise[i];
        }
        omegaCount = absN;
    }

    /**
     * Get the real part of the {@code k}-th {@code n}-th root of unity.
     *
     * @param k index of the {@code n}-th root of unity
     * @return real part of the {@code k}-th {@code n}-th root of unity
     * @throws MathIllegalStateException if no roots of unity have been
     * computed yet
     * @throws MathIllegalArgumentException if {@code k} is out of range
     */
    public synchronized double getReal(int k) throws MathIllegalStateException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.RootsOfUnity.getReal_161");
        if (ROR_equals(omegaCount, 0, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37912, _mut37913, _mut37914, _mut37915, _mut37916)) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET);
        }
        if ((_mut37927 ? ((ROR_less(k, 0, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37917, _mut37918, _mut37919, _mut37920, _mut37921)) && (ROR_greater_equals(k, omegaCount, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37922, _mut37923, _mut37924, _mut37925, _mut37926))) : ((ROR_less(k, 0, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37917, _mut37918, _mut37919, _mut37920, _mut37921)) || (ROR_greater_equals(k, omegaCount, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37922, _mut37923, _mut37924, _mut37925, _mut37926))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(k), Integer.valueOf(0), Integer.valueOf(AOR_minus(omegaCount, 1, "org.apache.commons.math3.complex.RootsOfUnity.getReal_161", _mut37928, _mut37929, _mut37930, _mut37931)));
        }
        return omegaReal[k];
    }

    /**
     * Get the imaginary part of the {@code k}-th {@code n}-th root of unity.
     *
     * @param k index of the {@code n}-th root of unity
     * @return imaginary part of the {@code k}-th {@code n}-th root of unity
     * @throws MathIllegalStateException if no roots of unity have been
     * computed yet
     * @throws OutOfRangeException if {@code k} is out of range
     */
    public synchronized double getImaginary(int k) throws MathIllegalStateException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188");
        if (ROR_equals(omegaCount, 0, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37932, _mut37933, _mut37934, _mut37935, _mut37936)) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET);
        }
        if ((_mut37947 ? ((ROR_less(k, 0, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37937, _mut37938, _mut37939, _mut37940, _mut37941)) && (ROR_greater_equals(k, omegaCount, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37942, _mut37943, _mut37944, _mut37945, _mut37946))) : ((ROR_less(k, 0, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37937, _mut37938, _mut37939, _mut37940, _mut37941)) || (ROR_greater_equals(k, omegaCount, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37942, _mut37943, _mut37944, _mut37945, _mut37946))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(k), Integer.valueOf(0), Integer.valueOf(AOR_minus(omegaCount, 1, "org.apache.commons.math3.complex.RootsOfUnity.getImaginary_188", _mut37948, _mut37949, _mut37950, _mut37951)));
        }
        return isCounterClockWise ? omegaImaginaryCounterClockwise[k] : omegaImaginaryClockwise[k];
    }

    /**
     * Returns the number of roots of unity currently stored. If
     * {@link #computeRoots(int)} was called with {@code n}, then this method
     * returns {@code abs(n)}. If no roots of unity have been computed yet, this
     * method returns 0.
     *
     * @return the number of roots of unity currently stored
     */
    public synchronized int getNumberOfRoots() {
        return omegaCount;
    }
}
