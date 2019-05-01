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
package org.apache.commons.math3.transform;

import java.util.Arrays;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Useful functions for the implementation of various transforms.
 *
 * @since 3.0
 */
public class TransformUtils {

    @Conditional
    public static boolean _mut1712 = false, _mut1713 = false, _mut1714 = false, _mut1715 = false, _mut1716 = false, _mut1717 = false, _mut1718 = false, _mut1719 = false, _mut1720 = false, _mut1721 = false, _mut1722 = false, _mut1723 = false, _mut1724 = false, _mut1725 = false, _mut1726 = false, _mut1727 = false, _mut1728 = false, _mut1729 = false, _mut1730 = false, _mut1731 = false, _mut1732 = false, _mut1733 = false, _mut1734 = false, _mut1735 = false, _mut1736 = false, _mut1737 = false, _mut1738 = false, _mut1739 = false, _mut1740 = false, _mut1741 = false, _mut1742 = false, _mut1743 = false, _mut1744 = false, _mut1745 = false, _mut1746 = false, _mut1747 = false, _mut1748 = false, _mut1749 = false, _mut1750 = false, _mut1751 = false, _mut1752 = false, _mut1753 = false, _mut1754 = false;

    /**
     * Table of the powers of 2 to facilitate binary search lookup.
     *
     * @see #exactLog2(int)
     */
    private static final int[] POWERS_OF_TWO = { 0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020, 0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000, 0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000, 0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000 };

    /**
     * Private constructor.
     */
    private TransformUtils() {
        super();
    }

    /**
     * Multiply every component in the given real array by the
     * given real number. The change is made in place.
     *
     * @param f the real array to be scaled
     * @param d the real scaling coefficient
     * @return a reference to the scaled array
     */
    public static double[] scaleArray(double[] f, double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.scaleArray_59");
        for (int i = 0; ROR_less(i, f.length, "org.apache.commons.math3.transform.TransformUtils.scaleArray_59", _mut1712, _mut1713, _mut1714, _mut1715, _mut1716); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.scaleArray_59");
            f[i] *= d;
        }
        return f;
    }

    /**
     * Multiply every component in the given complex array by the
     * given real number. The change is made in place.
     *
     * @param f the complex array to be scaled
     * @param d the real scaling coefficient
     * @return a reference to the scaled array
     */
    public static Complex[] scaleArray(Complex[] f, double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.scaleArray_75");
        for (int i = 0; ROR_less(i, f.length, "org.apache.commons.math3.transform.TransformUtils.scaleArray_75", _mut1725, _mut1726, _mut1727, _mut1728, _mut1729); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.scaleArray_75");
            f[i] = new Complex(AOR_multiply(d, f[i].getReal(), "org.apache.commons.math3.transform.TransformUtils.scaleArray_75", _mut1717, _mut1718, _mut1719, _mut1720), AOR_multiply(d, f[i].getImaginary(), "org.apache.commons.math3.transform.TransformUtils.scaleArray_75", _mut1721, _mut1722, _mut1723, _mut1724));
        }
        return f;
    }

    /**
     * Builds a new two dimensional array of {@code double} filled with the real
     * and imaginary parts of the specified {@link Complex} numbers. In the
     * returned array {@code dataRI}, the data is laid out as follows
     * <ul>
     * <li>{@code dataRI[0][i] = dataC[i].getReal()},</li>
     * <li>{@code dataRI[1][i] = dataC[i].getImaginary()}.</li>
     * </ul>
     *
     * @param dataC the array of {@link Complex} data to be transformed
     * @return a two dimensional array filled with the real and imaginary parts
     *   of the specified complex input
     */
    public static double[][] createRealImaginaryArray(final Complex[] dataC) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.createRealImaginaryArray_97");
        final double[][] dataRI = new double[2][dataC.length];
        final double[] dataR = dataRI[0];
        final double[] dataI = dataRI[1];
        for (int i = 0; ROR_less(i, dataC.length, "org.apache.commons.math3.transform.TransformUtils.createRealImaginaryArray_97", _mut1730, _mut1731, _mut1732, _mut1733, _mut1734); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.createRealImaginaryArray_97");
            final Complex c = dataC[i];
            dataR[i] = c.getReal();
            dataI[i] = c.getImaginary();
        }
        return dataRI;
    }

    /**
     * Builds a new array of {@link Complex} from the specified two dimensional
     * array of real and imaginary parts. In the returned array {@code dataC},
     * the data is laid out as follows
     * <ul>
     * <li>{@code dataC[i].getReal() = dataRI[0][i]},</li>
     * <li>{@code dataC[i].getImaginary() = dataRI[1][i]}.</li>
     * </ul>
     *
     * @param dataRI the array of real and imaginary parts to be transformed
     * @return an array of {@link Complex} with specified real and imaginary parts.
     * @throws DimensionMismatchException if the number of rows of the specified
     *   array is not two, or the array is not rectangular
     */
    public static Complex[] createComplexArray(final double[][] dataRI) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.createComplexArray_123");
        if (ROR_not_equals(dataRI.length, 2, "org.apache.commons.math3.transform.TransformUtils.createComplexArray_123", _mut1735, _mut1736, _mut1737, _mut1738, _mut1739)) {
            throw new DimensionMismatchException(dataRI.length, 2);
        }
        final double[] dataR = dataRI[0];
        final double[] dataI = dataRI[1];
        if (ROR_not_equals(dataR.length, dataI.length, "org.apache.commons.math3.transform.TransformUtils.createComplexArray_123", _mut1740, _mut1741, _mut1742, _mut1743, _mut1744)) {
            throw new DimensionMismatchException(dataI.length, dataR.length);
        }
        final int n = dataR.length;
        final Complex[] c = new Complex[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.transform.TransformUtils.createComplexArray_123", _mut1745, _mut1746, _mut1747, _mut1748, _mut1749); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.createComplexArray_123");
            c[i] = new Complex(dataR[i], dataI[i]);
        }
        return c;
    }

    /**
     * Returns the base-2 logarithm of the specified {@code int}. Throws an
     * exception if {@code n} is not a power of two.
     *
     * @param n the {@code int} whose base-2 logarithm is to be evaluated
     * @return the base-2 logarithm of {@code n}
     * @throws MathIllegalArgumentException if {@code n} is not a power of two
     */
    public static int exactLog2(final int n) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.TransformUtils.exactLog2_152");
        int index = Arrays.binarySearch(TransformUtils.POWERS_OF_TWO, n);
        if (ROR_less(index, 0, "org.apache.commons.math3.transform.TransformUtils.exactLog2_152", _mut1750, _mut1751, _mut1752, _mut1753, _mut1754)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(n));
        }
        return index;
    }
}
