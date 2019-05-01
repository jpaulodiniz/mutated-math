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

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Abstract class implementing the {@link  RandomGenerator} interface.
 * Default implementations for all methods other than {@link #nextDouble()} and
 * {@link #setSeed(long)} are provided.
 * <p>
 * All data generation methods are based on {@code code nextDouble()}.
 * Concrete implementations <strong>must</strong> override
 * this method and <strong>should</strong> provide better / more
 * performant implementations of the other methods if the underlying PRNG
 * supplies them.</p>
 *
 * @since 1.1
 */
public abstract class AbstractRandomGenerator implements RandomGenerator {

    @Conditional
    public static boolean _mut52477 = false, _mut52478 = false, _mut52479 = false, _mut52480 = false, _mut52481 = false, _mut52482 = false, _mut52483 = false, _mut52484 = false, _mut52485 = false, _mut52486 = false, _mut52487 = false, _mut52488 = false, _mut52489 = false, _mut52490 = false, _mut52491 = false, _mut52492 = false, _mut52493 = false, _mut52494 = false, _mut52495 = false, _mut52496 = false, _mut52497 = false, _mut52498 = false, _mut52499 = false, _mut52500 = false, _mut52501 = false, _mut52502 = false, _mut52503 = false, _mut52504 = false, _mut52505 = false, _mut52506 = false, _mut52507 = false, _mut52508 = false, _mut52509 = false, _mut52510 = false, _mut52511 = false, _mut52512 = false, _mut52513 = false, _mut52514 = false, _mut52515 = false, _mut52516 = false, _mut52517 = false, _mut52518 = false, _mut52519 = false, _mut52520 = false, _mut52521 = false, _mut52522 = false, _mut52523 = false, _mut52524 = false, _mut52525 = false, _mut52526 = false, _mut52527 = false, _mut52528 = false, _mut52529 = false, _mut52530 = false, _mut52531 = false, _mut52532 = false, _mut52533 = false, _mut52534 = false, _mut52535 = false, _mut52536 = false, _mut52537 = false, _mut52538 = false, _mut52539 = false, _mut52540 = false, _mut52541 = false, _mut52542 = false, _mut52543 = false, _mut52544 = false, _mut52545 = false, _mut52546 = false, _mut52547 = false, _mut52548 = false, _mut52549 = false, _mut52550 = false, _mut52551 = false, _mut52552 = false, _mut52553 = false, _mut52554 = false, _mut52555 = false, _mut52556 = false, _mut52557 = false, _mut52558 = false, _mut52559 = false, _mut52560 = false, _mut52561 = false, _mut52562 = false, _mut52563 = false, _mut52564 = false, _mut52565 = false, _mut52566 = false, _mut52567 = false, _mut52568 = false, _mut52569 = false, _mut52570 = false, _mut52571 = false, _mut52572 = false, _mut52573 = false, _mut52574 = false, _mut52575 = false, _mut52576 = false, _mut52577 = false, _mut52578 = false, _mut52579 = false, _mut52580 = false, _mut52581 = false, _mut52582 = false, _mut52583 = false, _mut52584 = false, _mut52585 = false, _mut52586 = false, _mut52587 = false, _mut52588 = false, _mut52589 = false, _mut52590 = false, _mut52591 = false, _mut52592 = false, _mut52593 = false, _mut52594 = false, _mut52595 = false, _mut52596 = false, _mut52597 = false, _mut52598 = false, _mut52599 = false, _mut52600 = false, _mut52601 = false, _mut52602 = false, _mut52603 = false, _mut52604 = false, _mut52605 = false;

    /**
     * Cached random normal value.  The default implementation for
     * {@link #nextGaussian} generates pairs of values and this field caches the
     * second value so that the full algorithm is not executed for every
     * activation.  The value {@code Double.NaN} signals that there is
     * no cached value.  Use {@link #clear} to clear the cached value.
     */
    private double cachedNormalDeviate = Double.NaN;

    /**
     * Construct a RandomGenerator.
     */
    public AbstractRandomGenerator() {
        super();
    }

    /**
     * Clears the cache used by the default implementation of
     * {@link #nextGaussian}. Implementations that do not override the
     * default implementation of {@code nextGaussian} should call this
     * method in the implementation of {@link #setSeed(long)}
     */
    public void clear() {
        cachedNormalDeviate = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    public void setSeed(int seed) {
        setSeed((long) seed);
    }

    /**
     * {@inheritDoc}
     */
    public void setSeed(int[] seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.setSeed_70");
        // the following number is the largest prime that fits in 32 bits (it is 2^32 - 5)
        final long prime = 4294967291l;
        long combined = 0l;
        for (int s : seed) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.setSeed_70");
            combined = AOR_plus(AOR_multiply(combined, prime, "org.apache.commons.math3.random.AbstractRandomGenerator.setSeed_70", _mut52477, _mut52478, _mut52479, _mut52480), s, "org.apache.commons.math3.random.AbstractRandomGenerator.setSeed_70", _mut52481, _mut52482, _mut52483, _mut52484);
        }
        setSeed(combined);
    }

    /**
     * Sets the seed of the underlying random number generator using a
     * {@code long} seed.  Sequences of values generated starting with the
     * same seeds should be identical.
     * <p>
     * Implementations that do not override the default implementation of
     * {@code nextGaussian} should include a call to {@link #clear} in the
     * implementation of this method.</p>
     *
     * @param seed the seed value
     */
    public abstract void setSeed(long seed);

    /**
     * Generates random bytes and places them into a user-supplied
     * byte array.  The number of random bytes produced is equal to
     * the length of the byte array.
     * <p>
     * The default implementation fills the array with bytes extracted from
     * random integers generated using {@link #nextInt}.</p>
     *
     * @param bytes the non-null byte array in which to put the
     * random bytes
     */
    public void nextBytes(byte[] bytes) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105");
        int bytesOut = 0;
        while (ROR_less(bytesOut, bytes.length, "org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105", _mut52500, _mut52501, _mut52502, _mut52503, _mut52504)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105");
            int randInt = nextInt();
            for (int i = 0; ROR_less(i, 3, "org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105", _mut52495, _mut52496, _mut52497, _mut52498, _mut52499); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105");
                if (ROR_greater(i, 0, "org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105", _mut52485, _mut52486, _mut52487, _mut52488, _mut52489)) {
                    randInt >>= 8;
                }
                bytes[bytesOut++] = (byte) randInt;
                if (ROR_equals(bytesOut, bytes.length, "org.apache.commons.math3.random.AbstractRandomGenerator.nextBytes_105", _mut52490, _mut52491, _mut52492, _mut52493, _mut52494)) {
                    return;
                }
            }
        }
    }

    /**
     * Returns the next pseudorandom, uniformly distributed {@code int}
     * value from this random number generator's sequence.
     * All 2<font size="-1"><sup>32</sup></font> possible {@code int} values
     * should be produced with  (approximately) equal probability.
     * <p>
     * The default implementation provided here returns
     * <pre>
     * <code>(int) (nextDouble() * Integer.MAX_VALUE)</code>
     * </pre></p>
     *
     * @return the next pseudorandom, uniformly distributed {@code int}
     *  value from this random number generator's sequence
     */
    public int nextInt() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_135");
        return (int) (AOR_multiply((AOR_minus(AOR_multiply(2d, nextDouble(), "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_135", _mut52505, _mut52506, _mut52507, _mut52508), 1d, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_135", _mut52509, _mut52510, _mut52511, _mut52512)), Integer.MAX_VALUE, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_135", _mut52513, _mut52514, _mut52515, _mut52516));
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * between 0 (inclusive) and the specified value (exclusive), drawn from
     * this random number generator's sequence.
     * <p>
     * The default implementation returns
     * <pre>
     * <code>(int) (nextDouble() * n</code>
     * </pre></p>
     *
     * @param n the bound on the random number to be returned.  Must be
     * positive.
     * @return  a pseudorandom, uniformly distributed {@code int}
     * value between 0 (inclusive) and n (exclusive).
     * @throws NotStrictlyPositiveException if {@code n <= 0}.
     */
    public int nextInt(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_155");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_155", _mut52517, _mut52518, _mut52519, _mut52520, _mut52521)) {
            throw new NotStrictlyPositiveException(n);
        }
        int result = (int) (AOR_multiply(nextDouble(), n, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_155", _mut52522, _mut52523, _mut52524, _mut52525));
        return ROR_less(result, n, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_155", _mut52526, _mut52527, _mut52528, _mut52529, _mut52530) ? result : AOR_minus(n, 1, "org.apache.commons.math3.random.AbstractRandomGenerator.nextInt_155", _mut52531, _mut52532, _mut52533, _mut52534);
    }

    /**
     * Returns the next pseudorandom, uniformly distributed {@code long}
     * value from this random number generator's sequence.  All
     * 2<font size="-1"><sup>64</sup></font> possible {@code long} values
     * should be produced with (approximately) equal probability.
     * <p>
     * The default implementation returns
     * <pre>
     * <code>(long) (nextDouble() * Long.MAX_VALUE)</code>
     * </pre></p>
     *
     * @return  the next pseudorandom, uniformly distributed {@code long}
     *value from this random number generator's sequence
     */
    public long nextLong() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextLong_177");
        return (long) (AOR_multiply((AOR_minus(AOR_multiply(2d, nextDouble(), "org.apache.commons.math3.random.AbstractRandomGenerator.nextLong_177", _mut52535, _mut52536, _mut52537, _mut52538), 1d, "org.apache.commons.math3.random.AbstractRandomGenerator.nextLong_177", _mut52539, _mut52540, _mut52541, _mut52542)), Long.MAX_VALUE, "org.apache.commons.math3.random.AbstractRandomGenerator.nextLong_177", _mut52543, _mut52544, _mut52545, _mut52546));
    }

    /**
     * Returns the next pseudorandom, uniformly distributed
     * {@code boolean} value from this random number generator's
     * sequence.
     * <p>
     * The default implementation returns
     * <pre>
     * <code>nextDouble() <= 0.5</code>
     * </pre></p>
     *
     * @return  the next pseudorandom, uniformly distributed
     * {@code boolean} value from this random number generator's
     * sequence
     */
    public boolean nextBoolean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextBoolean_195");
        return ROR_less_equals(nextDouble(), 0.5, "org.apache.commons.math3.random.AbstractRandomGenerator.nextBoolean_195", _mut52547, _mut52548, _mut52549, _mut52550, _mut52551);
    }

    /**
     * Returns the next pseudorandom, uniformly distributed {@code float}
     * value between {@code 0.0} and {@code 1.0} from this random
     * number generator's sequence.
     * <p>
     * The default implementation returns
     * <pre>
     * <code>(float) nextDouble() </code>
     * </pre></p>
     *
     * @return  the next pseudorandom, uniformly distributed {@code float}
     * value between {@code 0.0} and {@code 1.0} from this
     * random number generator's sequence
     */
    public float nextFloat() {
        return (float) nextDouble();
    }

    /**
     * Returns the next pseudorandom, uniformly distributed
     * {@code double} value between {@code 0.0} and
     * {@code 1.0} from this random number generator's sequence.
     * <p>
     * This method provides the underlying source of random data used by the
     * other methods.</p>
     *
     * @return  the next pseudorandom, uniformly distributed
     *  {@code double} value between {@code 0.0} and
     *  {@code 1.0} from this random number generator's sequence
     */
    public abstract double nextDouble();

    /**
     * Returns the next pseudorandom, Gaussian ("normally") distributed
     * {@code double} value with mean {@code 0.0} and standard
     * deviation {@code 1.0} from this random number generator's sequence.
     * <p>
     * The default implementation uses the <em>Polar Method</em>
     * due to G.E.P. Box, M.E. Muller and G. Marsaglia, as described in
     * D. Knuth, <u>The Art of Computer Programming</u>, 3.4.1C.</p>
     * <p>
     * The algorithm generates a pair of independent random values.  One of
     * these is cached for reuse, so the full algorithm is not executed on each
     * activation.  Implementations that do not override this method should
     * make sure to call {@link #clear} to clear the cached value in the
     * implementation of {@link #setSeed(long)}.</p>
     *
     * @return  the next pseudorandom, Gaussian ("normally") distributed
     * {@code double} value with mean {@code 0.0} and
     * standard deviation {@code 1.0} from this random number
     *  generator's sequence
     */
    public double nextGaussian() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251");
        if (!Double.isNaN(cachedNormalDeviate)) {
            double dev = cachedNormalDeviate;
            cachedNormalDeviate = Double.NaN;
            return dev;
        }
        double v1 = 0;
        double v2 = 0;
        double s = 1;
        while (ROR_greater_equals(s, 1, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52580, _mut52581, _mut52582, _mut52583, _mut52584)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251");
            v1 = AOR_minus(AOR_multiply(2, nextDouble(), "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52552, _mut52553, _mut52554, _mut52555), 1, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52556, _mut52557, _mut52558, _mut52559);
            v2 = AOR_minus(AOR_multiply(2, nextDouble(), "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52560, _mut52561, _mut52562, _mut52563), 1, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52564, _mut52565, _mut52566, _mut52567);
            s = AOR_plus(AOR_multiply(v1, v1, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52568, _mut52569, _mut52570, _mut52571), AOR_multiply(v2, v2, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52572, _mut52573, _mut52574, _mut52575), "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52576, _mut52577, _mut52578, _mut52579);
        }
        if (ROR_not_equals(s, 0, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52585, _mut52586, _mut52587, _mut52588, _mut52589)) {
            s = FastMath.sqrt(AOR_divide(AOR_multiply(-2, FastMath.log(s), "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52590, _mut52591, _mut52592, _mut52593), s, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52594, _mut52595, _mut52596, _mut52597));
        }
        cachedNormalDeviate = AOR_multiply(v2, s, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52598, _mut52599, _mut52600, _mut52601);
        return AOR_multiply(v1, s, "org.apache.commons.math3.random.AbstractRandomGenerator.nextGaussian_251", _mut52602, _mut52603, _mut52604, _mut52605);
    }
}
