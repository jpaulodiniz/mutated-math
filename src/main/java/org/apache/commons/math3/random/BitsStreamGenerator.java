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
package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for random number generators that generates bits streams.
 *
 * @since 2.0
 */
public abstract class BitsStreamGenerator implements RandomGenerator, Serializable {

    @Conditional
    public static boolean _mut51284 = false, _mut51285 = false, _mut51286 = false, _mut51287 = false, _mut51288 = false, _mut51289 = false, _mut51290 = false, _mut51291 = false, _mut51292 = false, _mut51293 = false, _mut51294 = false, _mut51295 = false, _mut51296 = false, _mut51297 = false, _mut51298 = false, _mut51299 = false, _mut51300 = false, _mut51301 = false, _mut51302 = false, _mut51303 = false, _mut51304 = false, _mut51305 = false, _mut51306 = false, _mut51307 = false, _mut51308 = false, _mut51309 = false, _mut51310 = false, _mut51311 = false, _mut51312 = false, _mut51313 = false, _mut51314 = false, _mut51315 = false, _mut51316 = false, _mut51317 = false, _mut51318 = false, _mut51319 = false, _mut51320 = false, _mut51321 = false, _mut51322 = false, _mut51323 = false, _mut51324 = false, _mut51325 = false, _mut51326 = false, _mut51327 = false, _mut51328 = false, _mut51329 = false, _mut51330 = false, _mut51331 = false, _mut51332 = false, _mut51333 = false, _mut51334 = false, _mut51335 = false, _mut51336 = false, _mut51337 = false, _mut51338 = false, _mut51339 = false, _mut51340 = false, _mut51341 = false, _mut51342 = false, _mut51343 = false, _mut51344 = false, _mut51345 = false, _mut51346 = false, _mut51347 = false, _mut51348 = false, _mut51349 = false, _mut51350 = false, _mut51351 = false, _mut51352 = false, _mut51353 = false, _mut51354 = false, _mut51355 = false, _mut51356 = false, _mut51357 = false, _mut51358 = false, _mut51359 = false, _mut51360 = false, _mut51361 = false, _mut51362 = false, _mut51363 = false, _mut51364 = false, _mut51365 = false, _mut51366 = false, _mut51367 = false, _mut51368 = false, _mut51369 = false, _mut51370 = false, _mut51371 = false, _mut51372 = false, _mut51373 = false, _mut51374 = false, _mut51375 = false, _mut51376 = false, _mut51377 = false, _mut51378 = false, _mut51379 = false, _mut51380 = false, _mut51381 = false, _mut51382 = false, _mut51383 = false, _mut51384 = false, _mut51385 = false, _mut51386 = false, _mut51387 = false, _mut51388 = false, _mut51389 = false, _mut51390 = false, _mut51391 = false, _mut51392 = false, _mut51393 = false, _mut51394 = false, _mut51395 = false, _mut51396 = false, _mut51397 = false, _mut51398 = false, _mut51399 = false, _mut51400 = false, _mut51401 = false, _mut51402 = false, _mut51403 = false, _mut51404 = false, _mut51405 = false, _mut51406 = false, _mut51407 = false, _mut51408 = false, _mut51409 = false, _mut51410 = false, _mut51411 = false, _mut51412 = false, _mut51413 = false, _mut51414 = false, _mut51415 = false, _mut51416 = false, _mut51417 = false, _mut51418 = false, _mut51419 = false, _mut51420 = false, _mut51421 = false, _mut51422 = false, _mut51423 = false, _mut51424 = false, _mut51425 = false, _mut51426 = false, _mut51427 = false, _mut51428 = false, _mut51429 = false, _mut51430 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 20130104L;

    /**
     * Next gaussian.
     */
    private double nextGaussian;

    /**
     * Creates a new random number generator.
     */
    public BitsStreamGenerator() {
        nextGaussian = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void setSeed(int seed);

    /**
     * {@inheritDoc}
     */
    public abstract void setSeed(int[] seed);

    /**
     * {@inheritDoc}
     */
    public abstract void setSeed(long seed);

    /**
     * Generate next pseudorandom number.
     * <p>This method is the core generation algorithm. It is used by all the
     * public generation methods for the various primitive types {@link
     * #nextBoolean()}, {@link #nextBytes(byte[])}, {@link #nextDouble()},
     * {@link #nextFloat()}, {@link #nextGaussian()}, {@link #nextInt()},
     * {@link #next(int)} and {@link #nextLong()}.</p>
     * @param bits number of random bits to produce
     * @return random bits generated
     */
    protected abstract int next(int bits);

    /**
     * {@inheritDoc}
     */
    public boolean nextBoolean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextBoolean_65");
        return ROR_not_equals(next(1), 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextBoolean_65", _mut51284, _mut51285, _mut51286, _mut51287, _mut51288);
    }

    /**
     * {@inheritDoc}
     */
    public double nextDouble() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextDouble_70");
        final long high = ((long) next(26)) << 26;
        final int low = next(26);
        return AOR_multiply((high | low), 0x1.0p-52d, "org.apache.commons.math3.random.BitsStreamGenerator.nextDouble_70", _mut51289, _mut51290, _mut51291, _mut51292);
    }

    /**
     * {@inheritDoc}
     */
    public float nextFloat() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextFloat_77");
        return AOR_multiply(next(23), 0x1.0p-23f, "org.apache.commons.math3.random.BitsStreamGenerator.nextFloat_77", _mut51293, _mut51294, _mut51295, _mut51296);
    }

    /**
     * {@inheritDoc}
     */
    public double nextGaussian() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82");
        final double random;
        if (Double.isNaN(nextGaussian)) {
            // generate a new pair of gaussian numbers
            final double x = nextDouble();
            final double y = nextDouble();
            final double alpha = AOR_multiply(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82", _mut51297, _mut51298, _mut51299, _mut51300), x, "org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82", _mut51301, _mut51302, _mut51303, _mut51304);
            final double r = FastMath.sqrt(AOR_multiply(-2, FastMath.log(y), "org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82", _mut51305, _mut51306, _mut51307, _mut51308));
            random = AOR_multiply(r, FastMath.cos(alpha), "org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82", _mut51309, _mut51310, _mut51311, _mut51312);
            nextGaussian = AOR_multiply(r, FastMath.sin(alpha), "org.apache.commons.math3.random.BitsStreamGenerator.nextGaussian_82", _mut51313, _mut51314, _mut51315, _mut51316);
        } else {
            // use the second element of the pair already generated
            random = nextGaussian;
            nextGaussian = Double.NaN;
        }
        return random;
    }

    /**
     * {@inheritDoc}
     */
    public int nextInt() {
        return next(32);
    }

    /**
     * {@inheritDoc}
     * <p>This default implementation is copied from Apache Harmony
     * java.util.Random (r929253).</p>
     *
     * <p>Implementation notes: <ul>
     * <li>If n is a power of 2, this method returns
     * {@code (int) ((n * (long) next(31)) >> 31)}.</li>
     *
     * <li>If n is not a power of 2, what is returned is {@code next(31) % n}
     * with {@code next(31)} values rejected (i.e. regenerated) until a
     * value that is larger than the remainder of {@code Integer.MAX_VALUE / n}
     * is generated. Rejection of this initial segment is necessary to ensure
     * a uniform distribution.</li></ul></p>
     */
    public int nextInt(int n) throws IllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123");
        if (ROR_greater(n, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51317, _mut51318, _mut51319, _mut51320, _mut51321)) {
            if (ROR_equals((n & -n), n, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51322, _mut51323, _mut51324, _mut51325, _mut51326)) {
                return (int) ((AOR_multiply(n, (long) next(31), "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51327, _mut51328, _mut51329, _mut51330)) >> 31);
            }
            int bits;
            int val;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123");
                bits = next(31);
                val = AOR_remainder(bits, n, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51331, _mut51332, _mut51333, _mut51334);
            } while (ROR_less(AOR_plus(AOR_minus(bits, val, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51335, _mut51336, _mut51337, _mut51338), (AOR_minus(n, 1, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51339, _mut51340, _mut51341, _mut51342)), "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51343, _mut51344, _mut51345, _mut51346), 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextInt_123", _mut51347, _mut51348, _mut51349, _mut51350, _mut51351));
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    /**
     * {@inheritDoc}
     */
    public long nextLong() {
        final long high = ((long) next(32)) << 32;
        final long low = ((long) next(32)) & 0xffffffffL;
        return high | low;
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code long} value
     * between 0 (inclusive) and the specified value (exclusive), drawn from
     * this random number generator's sequence.
     *
     * @param n the bound on the random number to be returned.  Must be
     * positive.
     * @return  a pseudorandom, uniformly distributed {@code long}
     * value between 0 (inclusive) and n (exclusive).
     * @throws IllegalArgumentException  if n is not positive.
     */
    public long nextLong(long n) throws IllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157");
        if (ROR_greater(n, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51352, _mut51353, _mut51354, _mut51355, _mut51356)) {
            long bits;
            long val;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157");
                bits = ((long) next(31)) << 32;
                bits |= ((long) next(32)) & 0xffffffffL;
                val = AOR_remainder(bits, n, "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51357, _mut51358, _mut51359, _mut51360);
            } while (ROR_less(AOR_plus(AOR_minus(bits, val, "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51361, _mut51362, _mut51363, _mut51364), (AOR_minus(n, 1, "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51365, _mut51366, _mut51367, _mut51368)), "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51369, _mut51370, _mut51371, _mut51372), 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextLong_157", _mut51373, _mut51374, _mut51375, _mut51376, _mut51377));
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    /**
     * Clears the cache used by the default implementation of
     * {@link #nextGaussian}.
     */
    public void clear() {
        nextGaussian = Double.NaN;
    }

    /**
     * Generates random bytes and places them into a user-supplied array.
     *
     * <p>
     * The array is filled with bytes extracted from random integers.
     * This implies that the number of random bytes generated may be larger than
     * the length of the byte array.
     * </p>
     *
     * @param bytes Array in which to put the generated bytes. Cannot be {@code null}.
     */
    public void nextBytes(byte[] bytes) {
        nextBytesFill(bytes, 0, bytes.length);
    }

    /**
     * Generates random bytes and places them into a user-supplied array.
     *
     * <p>
     * The array is filled with bytes extracted from random integers.
     * This implies that the number of random bytes generated may be larger than
     * the length of the byte array.
     * </p>
     *
     * @param bytes Array in which to put the generated bytes. Cannot be {@code null}.
     * @param start Index at which to start inserting the generated bytes.
     * @param len Number of bytes to insert.
     * @throws OutOfRangeException if {@code start < 0} or {@code start >= bytes.length}.
     * @throws OutOfRangeException if {@code len < 0} or {@code len > bytes.length - start}.
     */
    public void nextBytes(byte[] bytes, int start, int len) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209");
        if ((_mut51388 ? (ROR_less(start, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51378, _mut51379, _mut51380, _mut51381, _mut51382) && ROR_greater_equals(start, bytes.length, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51383, _mut51384, _mut51385, _mut51386, _mut51387)) : (ROR_less(start, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51378, _mut51379, _mut51380, _mut51381, _mut51382) || ROR_greater_equals(start, bytes.length, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51383, _mut51384, _mut51385, _mut51386, _mut51387)))) {
            throw new OutOfRangeException(start, 0, bytes.length);
        }
        if ((_mut51403 ? (ROR_less(len, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51389, _mut51390, _mut51391, _mut51392, _mut51393) && ROR_greater(len, AOR_minus(bytes.length, start, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51394, _mut51395, _mut51396, _mut51397), "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51398, _mut51399, _mut51400, _mut51401, _mut51402)) : (ROR_less(len, 0, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51389, _mut51390, _mut51391, _mut51392, _mut51393) || ROR_greater(len, AOR_minus(bytes.length, start, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51394, _mut51395, _mut51396, _mut51397), "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51398, _mut51399, _mut51400, _mut51401, _mut51402)))) {
            throw new OutOfRangeException(len, 0, AOR_minus(bytes.length, start, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytes_209", _mut51404, _mut51405, _mut51406, _mut51407));
        }
        nextBytesFill(bytes, start, len);
    }

    /**
     * Generates random bytes and places them into a user-supplied array.
     *
     * <p>
     * The array is filled with bytes extracted from random integers.
     * This implies that the number of random bytes generated may be larger than
     * the length of the byte array.
     * </p>
     *
     * @param bytes Array in which to put the generated bytes. Cannot be {@code null}.
     * @param start Index at which to start inserting the generated bytes.
     * @param len Number of bytes to insert.
     */
    private void nextBytesFill(byte[] bytes, int start, int len) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237");
        // Index of first insertion.
        int index = start;
        // with two least significant bits unset).
        final int indexLoopLimit = AOR_plus(index, (len & 0x7ffffffc), "org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237", _mut51408, _mut51409, _mut51410, _mut51411);
        // Start filling in the byte array, 4 bytes at a time.
        while (ROR_less(index, indexLoopLimit, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237", _mut51412, _mut51413, _mut51414, _mut51415, _mut51416)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237");
            final int random = next(32);
            bytes[index++] = (byte) random;
            bytes[index++] = (byte) (random >>> 8);
            bytes[index++] = (byte) (random >>> 16);
            bytes[index++] = (byte) (random >>> 24);
        }
        // Index of last insertion + 1.
        final int indexLimit = AOR_plus(start, len, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237", _mut51417, _mut51418, _mut51419, _mut51420);
        // Fill in the remaining bytes.
        if (ROR_less(index, indexLimit, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237", _mut51421, _mut51422, _mut51423, _mut51424, _mut51425)) {
            int random = next(32);
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237");
                bytes[index++] = (byte) random;
                if (ROR_less(index, indexLimit, "org.apache.commons.math3.random.BitsStreamGenerator.nextBytesFill_237", _mut51426, _mut51427, _mut51428, _mut51429, _mut51430)) {
                    random >>>= 8;
                } else {
                    break;
                }
            }
        }
    }
}
