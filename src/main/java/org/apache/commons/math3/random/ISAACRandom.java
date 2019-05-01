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
 * <a href="http://burtleburtle.net/bob/rand/isaacafa.html">
 *  ISAAC: a fast cryptographic pseudo-random number generator</a>
 * <br/>
 * ISAAC (Indirection, Shift, Accumulate, Add, and Count) generates 32-bit
 * random numbers.
 * ISAAC has been designed to be cryptographically secure and is inspired
 * by RC4.
 * Cycles are guaranteed to be at least 2<sup>40</sup> values long, and they
 * are 2<sup>8295</sup> values long on average.
 * The results are uniformly distributed, unbiased, and unpredictable unless
 * you know the seed.
 * <br/>
 * This code is based (with minor changes and improvements) on the original
 * implementation of the algorithm by Bob Jenkins.
 * <br/>
 *
 * @since 3.0
 */
public class ISAACRandom extends BitsStreamGenerator implements Serializable {

    @Conditional
    public static boolean _mut52606 = false, _mut52607 = false, _mut52608 = false, _mut52609 = false, _mut52610 = false, _mut52611 = false, _mut52612 = false, _mut52613 = false, _mut52614 = false, _mut52615 = false, _mut52616 = false, _mut52617 = false, _mut52618 = false, _mut52619 = false, _mut52620 = false, _mut52621 = false, _mut52622 = false, _mut52623 = false, _mut52624 = false, _mut52625 = false, _mut52626 = false, _mut52627 = false, _mut52628 = false, _mut52629 = false, _mut52630 = false, _mut52631 = false, _mut52632 = false, _mut52633 = false, _mut52634 = false, _mut52635 = false, _mut52636 = false, _mut52637 = false, _mut52638 = false, _mut52639 = false, _mut52640 = false, _mut52641 = false, _mut52642 = false, _mut52643 = false, _mut52644 = false, _mut52645 = false, _mut52646 = false, _mut52647 = false, _mut52648 = false, _mut52649 = false, _mut52650 = false, _mut52651 = false, _mut52652 = false, _mut52653 = false, _mut52654 = false, _mut52655 = false, _mut52656 = false, _mut52657 = false, _mut52658 = false, _mut52659 = false, _mut52660 = false, _mut52661 = false, _mut52662 = false, _mut52663 = false, _mut52664 = false, _mut52665 = false, _mut52666 = false, _mut52667 = false, _mut52668 = false, _mut52669 = false, _mut52670 = false, _mut52671 = false, _mut52672 = false, _mut52673 = false, _mut52674 = false, _mut52675 = false, _mut52676 = false, _mut52677 = false, _mut52678 = false, _mut52679 = false, _mut52680 = false, _mut52681 = false, _mut52682 = false, _mut52683 = false, _mut52684 = false, _mut52685 = false, _mut52686 = false, _mut52687 = false, _mut52688 = false, _mut52689 = false, _mut52690 = false, _mut52691 = false, _mut52692 = false, _mut52693 = false, _mut52694 = false, _mut52695 = false, _mut52696 = false, _mut52697 = false, _mut52698 = false, _mut52699 = false, _mut52700 = false, _mut52701 = false, _mut52702 = false, _mut52703 = false, _mut52704 = false, _mut52705 = false, _mut52706 = false, _mut52707 = false, _mut52708 = false, _mut52709 = false, _mut52710 = false, _mut52711 = false, _mut52712 = false, _mut52713 = false, _mut52714 = false, _mut52715 = false, _mut52716 = false, _mut52717 = false, _mut52718 = false, _mut52719 = false, _mut52720 = false, _mut52721 = false, _mut52722 = false, _mut52723 = false, _mut52724 = false, _mut52725 = false, _mut52726 = false, _mut52727 = false, _mut52728 = false, _mut52729 = false, _mut52730 = false, _mut52731 = false, _mut52732 = false, _mut52733 = false, _mut52734 = false, _mut52735 = false, _mut52736 = false, _mut52737 = false, _mut52738 = false, _mut52739 = false, _mut52740 = false, _mut52741 = false, _mut52742 = false, _mut52743 = false, _mut52744 = false, _mut52745 = false, _mut52746 = false, _mut52747 = false, _mut52748 = false, _mut52749 = false, _mut52750 = false, _mut52751 = false, _mut52752 = false, _mut52753 = false, _mut52754 = false, _mut52755 = false, _mut52756 = false, _mut52757 = false, _mut52758 = false, _mut52759 = false, _mut52760 = false, _mut52761 = false, _mut52762 = false, _mut52763 = false, _mut52764 = false, _mut52765 = false, _mut52766 = false, _mut52767 = false, _mut52768 = false, _mut52769 = false, _mut52770 = false, _mut52771 = false, _mut52772 = false, _mut52773 = false, _mut52774 = false, _mut52775 = false, _mut52776 = false, _mut52777 = false, _mut52778 = false, _mut52779 = false, _mut52780 = false, _mut52781 = false, _mut52782 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 7288197941165002400L;

    /**
     * Log of size of rsl[] and mem[]
     */
    private static final int SIZE_L = 8;

    /**
     * Size of rsl[] and mem[]
     */
    private static final int SIZE = 1 << SIZE_L;

    /**
     * Half-size of rsl[] and mem[]
     */
    private static final int H_SIZE = SIZE >> 1;

    /**
     * For pseudo-random lookup
     */
    private static final int MASK = AOR_minus(SIZE, 1, "org.apache.commons.math3.random.ISAACRandom.nextNormalizedDouble_42", _mut52606, _mut52607, _mut52608, _mut52609) << 2;

    /**
     * The golden ratio
     */
    private static final int GLD_RATIO = 0x9e3779b9;

    /**
     * The results given to the user
     */
    private final int[] rsl = new int[SIZE];

    /**
     * The internal state
     */
    private final int[] mem = new int[SIZE];

    /**
     * Count through the results in rsl[]
     */
    private int count;

    /**
     * Accumulator
     */
    private int isaacA;

    /**
     * The last result
     */
    private int isaacB;

    /**
     * Counter, guarantees cycle is at least 2^40
     */
    private int isaacC;

    /**
     * Service variable.
     */
    private final int[] arr = new int[8];

    /**
     * Service variable.
     */
    private int isaacX;

    /**
     * Service variable.
     */
    private int isaacI;

    /**
     * Service variable.
     */
    private int isaacJ;

    /**
     * Creates a new ISAAC random number generator.
     * <br/>
     * The instance is initialized using a combination of the
     * current time and system hash code of the instance as the seed.
     */
    public ISAACRandom() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.ISAACRandom_84");
        setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.ISAACRandom.ISAACRandom_84", _mut52610, _mut52611, _mut52612, _mut52613));
    }

    /**
     * Creates a new ISAAC random number generator using a single long seed.
     *
     * @param seed Initial seed.
     */
    public ISAACRandom(long seed) {
        setSeed(seed);
    }

    /**
     * Creates a new ISAAC random number generator using an int array seed.
     *
     * @param seed Initial seed. If {@code null}, the seed will be related
     * to the current time.
     */
    public ISAACRandom(int[] seed) {
        setSeed(seed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeed(int seed) {
        setSeed(new int[] { seed });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeed(long seed) {
        setSeed(new int[] { (int) (seed >>> 32), (int) (seed & 0xffffffffL) });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeed(int[] seed) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.setSeed_120");
        if (seed == null) {
            setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52614, _mut52615, _mut52616, _mut52617));
            return;
        }
        final int seedLen = seed.length;
        final int rslLen = rsl.length;
        System.arraycopy(seed, 0, rsl, 0, FastMath.min(seedLen, rslLen));
        if (ROR_less(seedLen, rslLen, "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52618, _mut52619, _mut52620, _mut52621, _mut52622)) {
            for (int j = seedLen; ROR_less(j, rslLen, "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52635, _mut52636, _mut52637, _mut52638, _mut52639); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.setSeed_120");
                long k = rsl[AOR_minus(j, seedLen, "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52623, _mut52624, _mut52625, _mut52626)];
                rsl[j] = (int) (AOR_plus(AOR_multiply(0x6c078965L, (k ^ k >> 30), "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52627, _mut52628, _mut52629, _mut52630), j, "org.apache.commons.math3.random.ISAACRandom.setSeed_120", _mut52631, _mut52632, _mut52633, _mut52634) & 0xffffffffL);
            }
        }
        initState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int next(int bits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.next_139");
        if (ROR_less(count, 0, "org.apache.commons.math3.random.ISAACRandom.next_139", _mut52640, _mut52641, _mut52642, _mut52643, _mut52644)) {
            isaac();
            count = AOR_minus(SIZE, 1, "org.apache.commons.math3.random.ISAACRandom.next_139", _mut52645, _mut52646, _mut52647, _mut52648);
        }
        return rsl[count--] >>> AOR_minus(32, bits, "org.apache.commons.math3.random.ISAACRandom.next_139", _mut52649, _mut52650, _mut52651, _mut52652);
    }

    /**
     * Generate 256 results
     */
    private void isaac() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.isaac_149");
        isaacI = 0;
        isaacJ = H_SIZE;
        isaacB += ++isaacC;
        while (ROR_less(isaacI, H_SIZE, "org.apache.commons.math3.random.ISAACRandom.isaac_149", _mut52653, _mut52654, _mut52655, _mut52656, _mut52657)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.isaac_149");
            isaac2();
        }
        isaacJ = 0;
        while (ROR_less(isaacJ, H_SIZE, "org.apache.commons.math3.random.ISAACRandom.isaac_149", _mut52658, _mut52659, _mut52660, _mut52661, _mut52662)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.isaac_149");
            isaac2();
        }
    }

    /**
     * Intermediate internal loop.
     */
    private void isaac2() {
        isaacX = mem[isaacI];
        isaacA ^= isaacA << 13;
        isaacA += mem[isaacJ++];
        isaac3();
        isaacX = mem[isaacI];
        isaacA ^= isaacA >>> 6;
        isaacA += mem[isaacJ++];
        isaac3();
        isaacX = mem[isaacI];
        isaacA ^= isaacA << 2;
        isaacA += mem[isaacJ++];
        isaac3();
        isaacX = mem[isaacI];
        isaacA ^= isaacA >>> 16;
        isaacA += mem[isaacJ++];
        isaac3();
    }

    /**
     * Lowest level internal loop.
     */
    private void isaac3() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.isaac3_183");
        mem[isaacI] = AOR_plus(AOR_plus(mem[(isaacX & MASK) >> 2], isaacA, "org.apache.commons.math3.random.ISAACRandom.isaac3_183", _mut52663, _mut52664, _mut52665, _mut52666), isaacB, "org.apache.commons.math3.random.ISAACRandom.isaac3_183", _mut52667, _mut52668, _mut52669, _mut52670);
        isaacB = AOR_plus(mem[(mem[isaacI] >> SIZE_L & MASK) >> 2], isaacX, "org.apache.commons.math3.random.ISAACRandom.isaac3_183", _mut52671, _mut52672, _mut52673, _mut52674);
        rsl[isaacI++] = isaacB;
    }

    /**
     * Initialize, or reinitialize, this instance of rand.
     */
    private void initState() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.initState_190");
        isaacA = 0;
        isaacB = 0;
        isaacC = 0;
        for (int j = 0; ROR_less(j, arr.length, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52675, _mut52676, _mut52677, _mut52678, _mut52679); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.initState_190");
            arr[j] = GLD_RATIO;
        }
        for (int j = 0; ROR_less(j, 4, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52680, _mut52681, _mut52682, _mut52683, _mut52684); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.initState_190");
            shuffle();
        }
        // fill in mem[] with messy stuff
        for (int j = 0; ROR_less(j, SIZE, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52713, _mut52714, _mut52715, _mut52716, _mut52717); j += 8) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.initState_190");
            arr[0] += rsl[j];
            arr[1] += rsl[AOR_plus(j, 1, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52685, _mut52686, _mut52687, _mut52688)];
            arr[2] += rsl[AOR_plus(j, 2, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52689, _mut52690, _mut52691, _mut52692)];
            arr[3] += rsl[AOR_plus(j, 3, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52693, _mut52694, _mut52695, _mut52696)];
            arr[4] += rsl[AOR_plus(j, 4, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52697, _mut52698, _mut52699, _mut52700)];
            arr[5] += rsl[AOR_plus(j, 5, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52701, _mut52702, _mut52703, _mut52704)];
            arr[6] += rsl[AOR_plus(j, 6, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52705, _mut52706, _mut52707, _mut52708)];
            arr[7] += rsl[AOR_plus(j, 7, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52709, _mut52710, _mut52711, _mut52712)];
            shuffle();
            setState(j);
        }
        // second pass makes all of seed affect all of mem
        for (int j = 0; ROR_less(j, SIZE, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52746, _mut52747, _mut52748, _mut52749, _mut52750); j += 8) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.initState_190");
            arr[0] += mem[j];
            arr[1] += mem[AOR_plus(j, 1, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52718, _mut52719, _mut52720, _mut52721)];
            arr[2] += mem[AOR_plus(j, 2, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52722, _mut52723, _mut52724, _mut52725)];
            arr[3] += mem[AOR_plus(j, 3, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52726, _mut52727, _mut52728, _mut52729)];
            arr[4] += mem[AOR_plus(j, 4, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52730, _mut52731, _mut52732, _mut52733)];
            arr[5] += mem[AOR_plus(j, 5, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52734, _mut52735, _mut52736, _mut52737)];
            arr[6] += mem[AOR_plus(j, 6, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52738, _mut52739, _mut52740, _mut52741)];
            arr[7] += mem[AOR_plus(j, 7, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52742, _mut52743, _mut52744, _mut52745)];
            shuffle();
            setState(j);
        }
        isaac();
        count = AOR_minus(SIZE, 1, "org.apache.commons.math3.random.ISAACRandom.initState_190", _mut52751, _mut52752, _mut52753, _mut52754);
        clear();
    }

    /**
     * Shuffle array.
     */
    private void shuffle() {
        arr[0] ^= arr[1] << 11;
        arr[3] += arr[0];
        arr[1] += arr[2];
        arr[1] ^= arr[2] >>> 2;
        arr[4] += arr[1];
        arr[2] += arr[3];
        arr[2] ^= arr[3] << 8;
        arr[5] += arr[2];
        arr[3] += arr[4];
        arr[3] ^= arr[4] >>> 16;
        arr[6] += arr[3];
        arr[4] += arr[5];
        arr[4] ^= arr[5] << 10;
        arr[7] += arr[4];
        arr[5] += arr[6];
        arr[5] ^= arr[6] >>> 4;
        arr[0] += arr[5];
        arr[6] += arr[7];
        arr[6] ^= arr[7] << 8;
        arr[1] += arr[6];
        arr[7] += arr[0];
        arr[7] ^= arr[0] >>> 9;
        arr[2] += arr[7];
        arr[0] += arr[1];
    }

    /**
     * Set the state by copying the internal arrays.
     *
     * @param start First index into {@link #mem} array.
     */
    private void setState(int start) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.ISAACRandom.setState_263");
        mem[start] = arr[0];
        mem[AOR_plus(start, 1, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52755, _mut52756, _mut52757, _mut52758)] = arr[1];
        mem[AOR_plus(start, 2, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52759, _mut52760, _mut52761, _mut52762)] = arr[2];
        mem[AOR_plus(start, 3, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52763, _mut52764, _mut52765, _mut52766)] = arr[3];
        mem[AOR_plus(start, 4, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52767, _mut52768, _mut52769, _mut52770)] = arr[4];
        mem[AOR_plus(start, 5, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52771, _mut52772, _mut52773, _mut52774)] = arr[5];
        mem[AOR_plus(start, 6, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52775, _mut52776, _mut52777, _mut52778)] = arr[6];
        mem[AOR_plus(start, 7, "org.apache.commons.math3.random.ISAACRandom.setState_263", _mut52779, _mut52780, _mut52781, _mut52782)] = arr[7];
    }
}
