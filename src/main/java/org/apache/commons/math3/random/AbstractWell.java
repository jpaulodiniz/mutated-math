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
 * This abstract class implements the WELL class of pseudo-random number generator
 * from Fran&ccedil;ois Panneton, Pierre L'Ecuyer and Makoto Matsumoto.
 *
 * <p>This generator is described in a paper by Fran&ccedil;ois Panneton,
 * Pierre L'Ecuyer and Makoto Matsumoto <a
 * href="http://www.iro.umontreal.ca/~lecuyer/myftp/papers/wellrng.pdf">Improved
 * Long-Period Generators Based on Linear Recurrences Modulo 2</a> ACM
 * Transactions on Mathematical Software, 32, 1 (2006). The errata for the paper
 * are in <a href="http://www.iro.umontreal.ca/~lecuyer/myftp/papers/wellrng-errata.txt">wellrng-errata.txt</a>.</p>
 *
 * @see <a href="http://www.iro.umontreal.ca/~panneton/WELLRNG.html">WELL Random number generator</a>
 * @since 2.2
 */
public abstract class AbstractWell extends BitsStreamGenerator implements Serializable {

    @Conditional
    public static boolean _mut52998 = false, _mut52999 = false, _mut53000 = false, _mut53001 = false, _mut53002 = false, _mut53003 = false, _mut53004 = false, _mut53005 = false, _mut53006 = false, _mut53007 = false, _mut53008 = false, _mut53009 = false, _mut53010 = false, _mut53011 = false, _mut53012 = false, _mut53013 = false, _mut53014 = false, _mut53015 = false, _mut53016 = false, _mut53017 = false, _mut53018 = false, _mut53019 = false, _mut53020 = false, _mut53021 = false, _mut53022 = false, _mut53023 = false, _mut53024 = false, _mut53025 = false, _mut53026 = false, _mut53027 = false, _mut53028 = false, _mut53029 = false, _mut53030 = false, _mut53031 = false, _mut53032 = false, _mut53033 = false, _mut53034 = false, _mut53035 = false, _mut53036 = false, _mut53037 = false, _mut53038 = false, _mut53039 = false, _mut53040 = false, _mut53041 = false, _mut53042 = false, _mut53043 = false, _mut53044 = false, _mut53045 = false, _mut53046 = false, _mut53047 = false, _mut53048 = false, _mut53049 = false, _mut53050 = false, _mut53051 = false, _mut53052 = false, _mut53053 = false, _mut53054 = false, _mut53055 = false, _mut53056 = false, _mut53057 = false, _mut53058 = false, _mut53059 = false, _mut53060 = false, _mut53061 = false, _mut53062 = false, _mut53063 = false, _mut53064 = false, _mut53065 = false, _mut53066 = false, _mut53067 = false, _mut53068 = false, _mut53069 = false, _mut53070 = false, _mut53071 = false, _mut53072 = false, _mut53073 = false, _mut53074 = false, _mut53075 = false, _mut53076 = false, _mut53077 = false, _mut53078 = false, _mut53079 = false, _mut53080 = false, _mut53081 = false, _mut53082 = false, _mut53083 = false, _mut53084 = false, _mut53085 = false, _mut53086 = false, _mut53087 = false, _mut53088 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -817701723016583596L;

    /**
     * Current index in the bytes pool.
     */
    protected int index;

    /**
     * Bytes pool.
     */
    protected final int[] v;

    /**
     * Index indirection table giving for each index its predecessor taking table size into account.
     */
    protected final int[] iRm1;

    /**
     * Index indirection table giving for each index its second predecessor taking table size into account.
     */
    protected final int[] iRm2;

    /**
     * Index indirection table giving for each index the value index + m1 taking table size into account.
     */
    protected final int[] i1;

    /**
     * Index indirection table giving for each index the value index + m2 taking table size into account.
     */
    protected final int[] i2;

    /**
     * Index indirection table giving for each index the value index + m3 taking table size into account.
     */
    protected final int[] i3;

    /**
     * Creates a new random number generator.
     * <p>The instance is initialized using the current time plus the
     * system identity hash code of this instance as the seed.</p>
     * @param k number of bits in the pool (not necessarily a multiple of 32)
     * @param m1 first parameter of the algorithm
     * @param m2 second parameter of the algorithm
     * @param m3 third parameter of the algorithm
     */
    protected AbstractWell(final int k, final int m1, final int m2, final int m3) {
        this(k, m1, m2, m3, null);
    }

    /**
     * Creates a new random number generator using a single int seed.
     * @param k number of bits in the pool (not necessarily a multiple of 32)
     * @param m1 first parameter of the algorithm
     * @param m2 second parameter of the algorithm
     * @param m3 third parameter of the algorithm
     * @param seed the initial seed (32 bits integer)
     */
    protected AbstractWell(final int k, final int m1, final int m2, final int m3, final int seed) {
        this(k, m1, m2, m3, new int[] { seed });
    }

    /**
     * Creates a new random number generator using an int array seed.
     * @param k number of bits in the pool (not necessarily a multiple of 32)
     * @param m1 first parameter of the algorithm
     * @param m2 second parameter of the algorithm
     * @param m3 third parameter of the algorithm
     * @param seed the initial seed (32 bits integers array), if null
     * the seed of the generator will be related to the current time
     */
    protected AbstractWell(final int k, final int m1, final int m2, final int m3, final int[] seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractWell.AbstractWell_95");
        // and p is the number of unused bits in the last block
        final int w = 32;
        final int r = AOR_divide((AOR_minus(AOR_plus(k, w, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut52998, _mut52999, _mut53000, _mut53001), 1, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53002, _mut53003, _mut53004, _mut53005)), w, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53006, _mut53007, _mut53008, _mut53009);
        this.v = new int[r];
        this.index = 0;
        // they allow saving computations like "(j + r - 2) % r" with costly modulo operations
        iRm1 = new int[r];
        iRm2 = new int[r];
        i1 = new int[r];
        i2 = new int[r];
        i3 = new int[r];
        for (int j = 0; ROR_less(j, r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53058, _mut53059, _mut53060, _mut53061, _mut53062); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractWell.AbstractWell_95");
            iRm1[j] = AOR_remainder((AOR_minus(AOR_plus(j, r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53010, _mut53011, _mut53012, _mut53013), 1, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53014, _mut53015, _mut53016, _mut53017)), r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53018, _mut53019, _mut53020, _mut53021);
            iRm2[j] = AOR_remainder((AOR_minus(AOR_plus(j, r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53022, _mut53023, _mut53024, _mut53025), 2, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53026, _mut53027, _mut53028, _mut53029)), r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53030, _mut53031, _mut53032, _mut53033);
            i1[j] = AOR_remainder((AOR_plus(j, m1, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53034, _mut53035, _mut53036, _mut53037)), r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53038, _mut53039, _mut53040, _mut53041);
            i2[j] = AOR_remainder((AOR_plus(j, m2, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53042, _mut53043, _mut53044, _mut53045)), r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53046, _mut53047, _mut53048, _mut53049);
            i3[j] = AOR_remainder((AOR_plus(j, m3, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53050, _mut53051, _mut53052, _mut53053)), r, "org.apache.commons.math3.random.AbstractWell.AbstractWell_95", _mut53054, _mut53055, _mut53056, _mut53057);
        }
        // initialize the pool content
        setSeed(seed);
    }

    /**
     * Creates a new random number generator using a single long seed.
     * @param k number of bits in the pool (not necessarily a multiple of 32)
     * @param m1 first parameter of the algorithm
     * @param m2 second parameter of the algorithm
     * @param m3 third parameter of the algorithm
     * @param seed the initial seed (64 bits integer)
     */
    protected AbstractWell(final int k, final int m1, final int m2, final int m3, final long seed) {
        this(k, m1, m2, m3, new int[] { (int) (seed >>> 32), (int) (seed & 0xffffffffl) });
    }

    /**
     * Reinitialize the generator as if just built with the given int seed.
     * <p>The state of the generator is exactly the same as a new
     * generator built with the same seed.</p>
     * @param seed the initial seed (32 bits integer)
     */
    @Override
    public void setSeed(final int seed) {
        setSeed(new int[] { seed });
    }

    /**
     * Reinitialize the generator as if just built with the given int array seed.
     * <p>The state of the generator is exactly the same as a new
     * generator built with the same seed.</p>
     * @param seed the initial seed (32 bits integers array). If null
     * the seed of the generator will be the system time plus the system identity
     * hash code of the instance.
     */
    @Override
    public void setSeed(final int[] seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractWell.setSeed_153");
        if (seed == null) {
            setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53063, _mut53064, _mut53065, _mut53066));
            return;
        }
        System.arraycopy(seed, 0, v, 0, FastMath.min(seed.length, v.length));
        if (ROR_less(seed.length, v.length, "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53067, _mut53068, _mut53069, _mut53070, _mut53071)) {
            for (int i = seed.length; ROR_less(i, v.length, "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53084, _mut53085, _mut53086, _mut53087, _mut53088); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.AbstractWell.setSeed_153");
                final long l = v[AOR_minus(i, seed.length, "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53072, _mut53073, _mut53074, _mut53075)];
                v[i] = (int) ((AOR_plus(AOR_multiply(1812433253l, (l ^ (l >> 30)), "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53076, _mut53077, _mut53078, _mut53079), i, "org.apache.commons.math3.random.AbstractWell.setSeed_153", _mut53080, _mut53081, _mut53082, _mut53083)) & 0xffffffffL);
            }
        }
        index = 0;
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
    public void setSeed(final long seed) {
        setSeed(new int[] { (int) (seed >>> 32), (int) (seed & 0xffffffffl) });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract int next(final int bits);
}
