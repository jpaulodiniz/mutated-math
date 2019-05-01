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
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements a powerful pseudo-random number generator
 * developed by Makoto Matsumoto and Takuji Nishimura during
 * 1996-1997.
 *
 * <p>This generator features an extremely long period
 * (2<sup>19937</sup>-1) and 623-dimensional equidistribution up to 32
 * bits accuracy. The home page for this generator is located at <a
 * href="http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/emt.html">
 * http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/emt.html</a>.</p>
 *
 * <p>This generator is described in a paper by Makoto Matsumoto and
 * Takuji Nishimura in 1998: <a
 * href="http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/ARTICLES/mt.pdf">Mersenne
 * Twister: A 623-Dimensionally Equidistributed Uniform Pseudo-Random
 * Number Generator</a>, ACM Transactions on Modeling and Computer
 * Simulation, Vol. 8, No. 1, January 1998, pp 3--30</p>
 *
 * <p>This class is mainly a Java port of the 2002-01-26 version of
 * the generator written in C by Makoto Matsumoto and Takuji
 * Nishimura. Here is their original copyright:</p>
 *
 * <table border="0" width="80%" cellpadding="10" align="center" bgcolor="#E0E0E0">
 * <tr><td>Copyright (C) 1997 - 2002, Makoto Matsumoto and Takuji Nishimura,
 *     All rights reserved.</td></tr>
 *
 * <tr><td>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <ol>
 *   <li>Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.</li>
 *   <li>Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.</li>
 *   <li>The names of its contributors may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission.</li>
 * </ol></td></tr>
 *
 * <tr><td><strong>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.</strong></td></tr>
 * </table>
 *
 * @since 2.0
 */
public class MersenneTwister extends BitsStreamGenerator implements Serializable {

    @Conditional
    public static boolean _mut51485 = false, _mut51486 = false, _mut51487 = false, _mut51488 = false, _mut51489 = false, _mut51490 = false, _mut51491 = false, _mut51492 = false, _mut51493 = false, _mut51494 = false, _mut51495 = false, _mut51496 = false, _mut51497 = false, _mut51498 = false, _mut51499 = false, _mut51500 = false, _mut51501 = false, _mut51502 = false, _mut51503 = false, _mut51504 = false, _mut51505 = false, _mut51506 = false, _mut51507 = false, _mut51508 = false, _mut51509 = false, _mut51510 = false, _mut51511 = false, _mut51512 = false, _mut51513 = false, _mut51514 = false, _mut51515 = false, _mut51516 = false, _mut51517 = false, _mut51518 = false, _mut51519 = false, _mut51520 = false, _mut51521 = false, _mut51522 = false, _mut51523 = false, _mut51524 = false, _mut51525 = false, _mut51526 = false, _mut51527 = false, _mut51528 = false, _mut51529 = false, _mut51530 = false, _mut51531 = false, _mut51532 = false, _mut51533 = false, _mut51534 = false, _mut51535 = false, _mut51536 = false, _mut51537 = false, _mut51538 = false, _mut51539 = false, _mut51540 = false, _mut51541 = false, _mut51542 = false, _mut51543 = false, _mut51544 = false, _mut51545 = false, _mut51546 = false, _mut51547 = false, _mut51548 = false, _mut51549 = false, _mut51550 = false, _mut51551 = false, _mut51552 = false, _mut51553 = false, _mut51554 = false, _mut51555 = false, _mut51556 = false, _mut51557 = false, _mut51558 = false, _mut51559 = false, _mut51560 = false, _mut51561 = false, _mut51562 = false, _mut51563 = false, _mut51564 = false, _mut51565 = false, _mut51566 = false, _mut51567 = false, _mut51568 = false, _mut51569 = false, _mut51570 = false, _mut51571 = false, _mut51572 = false, _mut51573 = false, _mut51574 = false, _mut51575 = false, _mut51576 = false, _mut51577 = false, _mut51578 = false, _mut51579 = false, _mut51580 = false, _mut51581 = false, _mut51582 = false, _mut51583 = false, _mut51584 = false, _mut51585 = false, _mut51586 = false, _mut51587 = false, _mut51588 = false, _mut51589 = false, _mut51590 = false, _mut51591 = false, _mut51592 = false, _mut51593 = false, _mut51594 = false, _mut51595 = false, _mut51596 = false, _mut51597 = false, _mut51598 = false, _mut51599 = false, _mut51600 = false, _mut51601 = false, _mut51602 = false, _mut51603 = false, _mut51604 = false, _mut51605 = false, _mut51606 = false, _mut51607 = false, _mut51608 = false, _mut51609 = false, _mut51610 = false, _mut51611 = false, _mut51612 = false, _mut51613 = false, _mut51614 = false, _mut51615 = false, _mut51616 = false, _mut51617 = false, _mut51618 = false, _mut51619 = false, _mut51620 = false, _mut51621 = false, _mut51622 = false, _mut51623 = false, _mut51624 = false, _mut51625 = false, _mut51626 = false, _mut51627 = false, _mut51628 = false, _mut51629 = false, _mut51630 = false, _mut51631 = false, _mut51632 = false, _mut51633 = false, _mut51634 = false, _mut51635 = false, _mut51636 = false, _mut51637 = false, _mut51638 = false, _mut51639 = false, _mut51640 = false, _mut51641 = false, _mut51642 = false, _mut51643 = false, _mut51644 = false, _mut51645 = false, _mut51646 = false, _mut51647 = false, _mut51648 = false, _mut51649 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 8661194735290153518L;

    /**
     * Size of the bytes pool.
     */
    private static final int N = 624;

    /**
     * Period second parameter.
     */
    private static final int M = 397;

    /**
     * X * MATRIX_A for X = {0, 1}.
     */
    private static final int[] MAG01 = { 0x0, 0x9908b0df };

    /**
     * Bytes pool.
     */
    private int[] mt;

    /**
     * Current index in the bytes pool.
     */
    private int mti;

    /**
     * Creates a new random number generator.
     * <p>The instance is initialized using the current time plus the
     * system identity hash code of this instance as the seed.</p>
     */
    public MersenneTwister() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.MersenneTwister_105");
        mt = new int[N];
        setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.MersenneTwister.MersenneTwister_105", _mut51485, _mut51486, _mut51487, _mut51488));
    }

    /**
     * Creates a new random number generator using a single int seed.
     * @param seed the initial seed (32 bits integer)
     */
    public MersenneTwister(int seed) {
        mt = new int[N];
        setSeed(seed);
    }

    /**
     * Creates a new random number generator using an int array seed.
     * @param seed the initial seed (32 bits integers array), if null
     * the seed of the generator will be related to the current time
     */
    public MersenneTwister(int[] seed) {
        mt = new int[N];
        setSeed(seed);
    }

    /**
     * Creates a new random number generator using a single long seed.
     * @param seed the initial seed (64 bits integer)
     */
    public MersenneTwister(long seed) {
        mt = new int[N];
        setSeed(seed);
    }

    /**
     * Reinitialize the generator as if just built with the given int seed.
     * <p>The state of the generator is exactly the same as a new
     * generator built with the same seed.</p>
     * @param seed the initial seed (32 bits integer)
     */
    @Override
    public void setSeed(int seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.setSeed_140");
        // we use a long masked by 0xffffffffL as a poor man unsigned int
        long longMT = seed;
        // NB: unlike original C code, we are working with java longs, the cast below makes masking unnecessary
        mt[0] = (int) longMT;
        for (mti = 1; ROR_less(mti, N, "org.apache.commons.math3.random.MersenneTwister.setSeed_140", _mut51497, _mut51498, _mut51499, _mut51500, _mut51501); ++mti) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.setSeed_140");
            // initializer from the 2002-01-09 C version by Makoto Matsumoto
            longMT = (AOR_plus(AOR_multiply(1812433253l, (longMT ^ (longMT >> 30)), "org.apache.commons.math3.random.MersenneTwister.setSeed_140", _mut51489, _mut51490, _mut51491, _mut51492), mti, "org.apache.commons.math3.random.MersenneTwister.setSeed_140", _mut51493, _mut51494, _mut51495, _mut51496)) & 0xffffffffL;
            mt[mti] = (int) longMT;
        }
        // Clear normal deviate cache
        clear();
    }

    /**
     * Reinitialize the generator as if just built with the given int array seed.
     * <p>The state of the generator is exactly the same as a new
     * generator built with the same seed.</p>
     * @param seed the initial seed (32 bits integers array), if null
     * the seed of the generator will be the current system time plus the
     * system identity hash code of this instance
     */
    @Override
    public void setSeed(int[] seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.setSeed_163");
        if (seed == null) {
            setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51502, _mut51503, _mut51504, _mut51505));
            return;
        }
        setSeed(19650218);
        int i = 1;
        int j = 0;
        for (int k = FastMath.max(N, seed.length); ROR_not_equals(k, 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51550, _mut51551, _mut51552, _mut51553, _mut51554); k--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.setSeed_163");
            long l0 = (mt[i] & 0x7fffffffl) | ((ROR_less(mt[i], 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51506, _mut51507, _mut51508, _mut51509, _mut51510)) ? 0x80000000l : 0x0l);
            long l1 = (mt[AOR_minus(i, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51511, _mut51512, _mut51513, _mut51514)] & 0x7fffffffl) | ((ROR_less(mt[AOR_minus(i, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51515, _mut51516, _mut51517, _mut51518)], 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51519, _mut51520, _mut51521, _mut51522, _mut51523)) ? 0x80000000l : 0x0l);
            // non linear
            long l = AOR_plus(AOR_plus((l0 ^ (AOR_multiply((l1 ^ (l1 >> 30)), 1664525l, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51524, _mut51525, _mut51526, _mut51527))), seed[j], "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51528, _mut51529, _mut51530, _mut51531), j, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51532, _mut51533, _mut51534, _mut51535);
            mt[i] = (int) (l & 0xffffffffl);
            i++;
            j++;
            if (ROR_greater_equals(i, N, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51536, _mut51537, _mut51538, _mut51539, _mut51540)) {
                mt[0] = mt[AOR_minus(N, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51541, _mut51542, _mut51543, _mut51544)];
                i = 1;
            }
            if (ROR_greater_equals(j, seed.length, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51545, _mut51546, _mut51547, _mut51548, _mut51549)) {
                j = 0;
            }
        }
        for (int k = N - 1; ROR_not_equals(k, 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51590, _mut51591, _mut51592, _mut51593, _mut51594); k--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.setSeed_163");
            long l0 = (mt[i] & 0x7fffffffl) | ((ROR_less(mt[i], 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51555, _mut51556, _mut51557, _mut51558, _mut51559)) ? 0x80000000l : 0x0l);
            long l1 = (mt[AOR_minus(i, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51560, _mut51561, _mut51562, _mut51563)] & 0x7fffffffl) | ((ROR_less(mt[AOR_minus(i, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51564, _mut51565, _mut51566, _mut51567)], 0, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51568, _mut51569, _mut51570, _mut51571, _mut51572)) ? 0x80000000l : 0x0l);
            // non linear
            long l = AOR_minus((l0 ^ (AOR_multiply((l1 ^ (l1 >> 30)), 1566083941l, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51573, _mut51574, _mut51575, _mut51576))), i, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51577, _mut51578, _mut51579, _mut51580);
            mt[i] = (int) (l & 0xffffffffL);
            i++;
            if (ROR_greater_equals(i, N, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51581, _mut51582, _mut51583, _mut51584, _mut51585)) {
                mt[0] = mt[AOR_minus(N, 1, "org.apache.commons.math3.random.MersenneTwister.setSeed_163", _mut51586, _mut51587, _mut51588, _mut51589)];
                i = 1;
            }
        }
        // MSB is 1; assuring non-zero initial array
        mt[0] = 0x80000000;
        // Clear normal deviate cache
        clear();
    }

    /**
     * Reinitialize the generator as if just built with the given long seed.
     * <p>The state of the generator is exactly the same as a new
     * generator built with the same seed.</p>
     * @param seed the initial seed (64 bits integer)
     */
    @Override
    public void setSeed(long seed) {
        setSeed(new int[] { (int) (seed >>> 32), (int) (seed & 0xffffffffl) });
    }

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
    @Override
    protected int next(int bits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.next_227");
        int y;
        if (ROR_greater_equals(mti, N, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51595, _mut51596, _mut51597, _mut51598, _mut51599)) {
            // generate N words at one time
            int mtNext = mt[0];
            for (int k = 0; ROR_less(k, AOR_minus(N, M, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51608, _mut51609, _mut51610, _mut51611), "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51612, _mut51613, _mut51614, _mut51615, _mut51616); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.next_227");
                int mtCurr = mtNext;
                mtNext = mt[AOR_plus(k, 1, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51600, _mut51601, _mut51602, _mut51603)];
                y = (mtCurr & 0x80000000) | (mtNext & 0x7fffffff);
                mt[k] = mt[AOR_plus(k, M, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51604, _mut51605, _mut51606, _mut51607)] ^ (y >>> 1) ^ MAG01[y & 0x1];
            }
            for (int k = N - M; ROR_less(k, AOR_minus(N, 1, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51629, _mut51630, _mut51631, _mut51632), "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51633, _mut51634, _mut51635, _mut51636, _mut51637); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.MersenneTwister.next_227");
                int mtCurr = mtNext;
                mtNext = mt[AOR_plus(k, 1, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51617, _mut51618, _mut51619, _mut51620)];
                y = (mtCurr & 0x80000000) | (mtNext & 0x7fffffff);
                mt[k] = mt[AOR_plus(k, (AOR_minus(M, N, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51621, _mut51622, _mut51623, _mut51624)), "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51625, _mut51626, _mut51627, _mut51628)] ^ (y >>> 1) ^ MAG01[y & 0x1];
            }
            y = (mtNext & 0x80000000) | (mt[0] & 0x7fffffff);
            mt[AOR_minus(N, 1, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51638, _mut51639, _mut51640, _mut51641)] = mt[AOR_minus(M, 1, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51642, _mut51643, _mut51644, _mut51645)] ^ (y >>> 1) ^ MAG01[y & 0x1];
            mti = 0;
        }
        y = mt[mti++];
        // tempering
        y ^= y >>> 11;
        y ^= (y << 7) & 0x9d2c5680;
        y ^= (y << 15) & 0xefc60000;
        y ^= y >>> 18;
        return y >>> (AOR_minus(32, bits, "org.apache.commons.math3.random.MersenneTwister.next_227", _mut51646, _mut51647, _mut51648, _mut51649));
    }
}
