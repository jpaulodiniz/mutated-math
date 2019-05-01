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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Collection;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.CauchyDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.apache.commons.math3.distribution.PascalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.distribution.ZipfDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the {@link RandomData} interface using a {@link RandomGenerator}
 * instance to generate non-secure data and a {@link java.security.SecureRandom}
 * instance to provide data for the <code>nextSecureXxx</code> methods. If no
 * <code>RandomGenerator</code> is provided in the constructor, the default is
 * to use a {@link Well19937c} generator. To plug in a different
 * implementation, either implement <code>RandomGenerator</code> directly or
 * extend {@link AbstractRandomGenerator}.
 * <p>
 * Supports reseeding the underlying pseudo-random number generator (PRNG). The
 * <code>SecurityProvider</code> and <code>Algorithm</code> used by the
 * <code>SecureRandom</code> instance can also be reset.
 * </p>
 * <p>
 * For details on the default PRNGs, see {@link java.util.Random} and
 * {@link java.security.SecureRandom}.
 * </p>
 * <p>
 * <strong>Usage Notes</strong>:
 * <ul>
 * <li>
 * Instance variables are used to maintain <code>RandomGenerator</code> and
 * <code>SecureRandom</code> instances used in data generation. Therefore, to
 * generate a random sequence of values or strings, you should use just
 * <strong>one</strong> <code>RandomDataImpl</code> instance repeatedly.</li>
 * <li>
 * The "secure" methods are *much* slower. These should be used only when a
 * cryptographically secure random sequence is required. A secure random
 * sequence is a sequence of pseudo-random values which, in addition to being
 * well-dispersed (so no subsequence of values is an any more likely than other
 * subsequence of the the same length), also has the additional property that
 * knowledge of values generated up to any point in the sequence does not make
 * it any easier to predict subsequent values.</li>
 * <li>
 * When a new <code>RandomDataImpl</code> is created, the underlying random
 * number generators are <strong>not</strong> initialized. If you do not
 * explicitly seed the default non-secure generator, it is seeded with the
 * current time in milliseconds plus the system identity hash code on first use.
 * The same holds for the secure generator. If you provide a <code>RandomGenerator</code>
 * to the constructor, however, this generator is not reseeded by the constructor
 * nor is it reseeded on first use.</li>
 * <li>
 * The <code>reSeed</code> and <code>reSeedSecure</code> methods delegate to the
 * corresponding methods on the underlying <code>RandomGenerator</code> and
 * <code>SecureRandom</code> instances. Therefore, <code>reSeed(long)</code>
 * fully resets the initial state of the non-secure random number generator (so
 * that reseeding with a specific value always results in the same subsequent
 * random sequence); whereas reSeedSecure(long) does <strong>not</strong>
 * reinitialize the secure random number generator (so secure sequences started
 * with calls to reseedSecure(long) won't be identical).</li>
 * <li>
 * This implementation is not synchronized. The underlying <code>RandomGenerator</code>
 * or <code>SecureRandom</code> instances are not protected by synchronization and
 * are not guaranteed to be thread-safe.  Therefore, if an instance of this class
 * is concurrently utilized by multiple threads, it is the responsibility of
 * client code to synchronize access to seeding and data generation methods.
 * </li>
 * </ul>
 * </p>
 * @since 3.1
 */
public class RandomDataGenerator implements RandomData, Serializable {

    @Conditional
    public static boolean _mut51650 = false, _mut51651 = false, _mut51652 = false, _mut51653 = false, _mut51654 = false, _mut51655 = false, _mut51656 = false, _mut51657 = false, _mut51658 = false, _mut51659 = false, _mut51660 = false, _mut51661 = false, _mut51662 = false, _mut51663 = false, _mut51664 = false, _mut51665 = false, _mut51666 = false, _mut51667 = false, _mut51668 = false, _mut51669 = false, _mut51670 = false, _mut51671 = false, _mut51672 = false, _mut51673 = false, _mut51674 = false, _mut51675 = false, _mut51676 = false, _mut51677 = false, _mut51678 = false, _mut51679 = false, _mut51680 = false, _mut51681 = false, _mut51682 = false, _mut51683 = false, _mut51684 = false, _mut51685 = false, _mut51686 = false, _mut51687 = false, _mut51688 = false, _mut51689 = false, _mut51690 = false, _mut51691 = false, _mut51692 = false, _mut51693 = false, _mut51694 = false, _mut51695 = false, _mut51696 = false, _mut51697 = false, _mut51698 = false, _mut51699 = false, _mut51700 = false, _mut51701 = false, _mut51702 = false, _mut51703 = false, _mut51704 = false, _mut51705 = false, _mut51706 = false, _mut51707 = false, _mut51708 = false, _mut51709 = false, _mut51710 = false, _mut51711 = false, _mut51712 = false, _mut51713 = false, _mut51714 = false, _mut51715 = false, _mut51716 = false, _mut51717 = false, _mut51718 = false, _mut51719 = false, _mut51720 = false, _mut51721 = false, _mut51722 = false, _mut51723 = false, _mut51724 = false, _mut51725 = false, _mut51726 = false, _mut51727 = false, _mut51728 = false, _mut51729 = false, _mut51730 = false, _mut51731 = false, _mut51732 = false, _mut51733 = false, _mut51734 = false, _mut51735 = false, _mut51736 = false, _mut51737 = false, _mut51738 = false, _mut51739 = false, _mut51740 = false, _mut51741 = false, _mut51742 = false, _mut51743 = false, _mut51744 = false, _mut51745 = false, _mut51746 = false, _mut51747 = false, _mut51748 = false, _mut51749 = false, _mut51750 = false, _mut51751 = false, _mut51752 = false, _mut51753 = false, _mut51754 = false, _mut51755 = false, _mut51756 = false, _mut51757 = false, _mut51758 = false, _mut51759 = false, _mut51760 = false, _mut51761 = false, _mut51762 = false, _mut51763 = false, _mut51764 = false, _mut51765 = false, _mut51766 = false, _mut51767 = false, _mut51768 = false, _mut51769 = false, _mut51770 = false, _mut51771 = false, _mut51772 = false, _mut51773 = false, _mut51774 = false, _mut51775 = false, _mut51776 = false, _mut51777 = false, _mut51778 = false, _mut51779 = false, _mut51780 = false, _mut51781 = false, _mut51782 = false, _mut51783 = false, _mut51784 = false, _mut51785 = false, _mut51786 = false, _mut51787 = false, _mut51788 = false, _mut51789 = false, _mut51790 = false, _mut51791 = false, _mut51792 = false, _mut51793 = false, _mut51794 = false, _mut51795 = false, _mut51796 = false, _mut51797 = false, _mut51798 = false, _mut51799 = false, _mut51800 = false, _mut51801 = false, _mut51802 = false, _mut51803 = false, _mut51804 = false, _mut51805 = false, _mut51806 = false, _mut51807 = false, _mut51808 = false, _mut51809 = false, _mut51810 = false, _mut51811 = false, _mut51812 = false, _mut51813 = false, _mut51814 = false, _mut51815 = false, _mut51816 = false, _mut51817 = false, _mut51818 = false, _mut51819 = false, _mut51820 = false, _mut51821 = false, _mut51822 = false, _mut51823 = false, _mut51824 = false, _mut51825 = false, _mut51826 = false, _mut51827 = false, _mut51828 = false, _mut51829 = false, _mut51830 = false, _mut51831 = false, _mut51832 = false, _mut51833 = false, _mut51834 = false, _mut51835 = false, _mut51836 = false, _mut51837 = false, _mut51838 = false, _mut51839 = false, _mut51840 = false, _mut51841 = false, _mut51842 = false, _mut51843 = false, _mut51844 = false, _mut51845 = false, _mut51846 = false, _mut51847 = false, _mut51848 = false, _mut51849 = false, _mut51850 = false, _mut51851 = false, _mut51852 = false, _mut51853 = false, _mut51854 = false, _mut51855 = false, _mut51856 = false, _mut51857 = false, _mut51858 = false, _mut51859 = false, _mut51860 = false, _mut51861 = false, _mut51862 = false, _mut51863 = false, _mut51864 = false, _mut51865 = false, _mut51866 = false, _mut51867 = false, _mut51868 = false, _mut51869 = false, _mut51870 = false, _mut51871 = false, _mut51872 = false, _mut51873 = false, _mut51874 = false, _mut51875 = false, _mut51876 = false, _mut51877 = false, _mut51878 = false, _mut51879 = false, _mut51880 = false, _mut51881 = false, _mut51882 = false, _mut51883 = false, _mut51884 = false, _mut51885 = false, _mut51886 = false, _mut51887 = false, _mut51888 = false, _mut51889 = false, _mut51890 = false, _mut51891 = false, _mut51892 = false, _mut51893 = false, _mut51894 = false, _mut51895 = false, _mut51896 = false, _mut51897 = false, _mut51898 = false, _mut51899 = false, _mut51900 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -626730818244969716L;

    /**
     * underlying random number generator
     */
    private RandomGenerator rand = null;

    /**
     * underlying secure random number generator
     */
    private RandomGenerator secRand = null;

    /**
     * Construct a RandomDataGenerator, using a default random generator as the source
     * of randomness.
     *
     * <p>The default generator is a {@link Well19937c} seeded
     * with {@code System.currentTimeMillis() + System.identityHashCode(this))}.
     * The generator is initialized and seeded on first use.</p>
     */
    public RandomDataGenerator() {
    }

    /**
     * Construct a RandomDataGenerator using the supplied {@link RandomGenerator} as
     * the source of (non-secure) random data.
     *
     * @param rand the source of (non-secure) random data
     * (may be null, resulting in the default generator)
     */
    public RandomDataGenerator(RandomGenerator rand) {
        this.rand = rand;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Algorithm Description:</strong> hex strings are generated using a
     * 2-step process.
     * <ol>
     * <li>{@code len / 2 + 1} binary bytes are generated using the underlying
     * Random</li>
     * <li>Each binary byte is translated into 2 hex digits</li>
     * </ol>
     * </p>
     *
     * @param len the desired string length.
     * @return the random string.
     * @throws NotStrictlyPositiveException if {@code len <= 0}.
     */
    public String nextHexString(int len) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161");
        if (ROR_less_equals(len, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51650, _mut51651, _mut51652, _mut51653, _mut51654)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, len);
        }
        // Get a random number generator
        RandomGenerator ran = getRandomGenerator();
        // Initialize output buffer
        StringBuilder outBuffer = new StringBuilder();
        // Get int(len/2)+1 random bytes
        byte[] randomBytes = new byte[AOR_plus((AOR_divide(len, 2, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51655, _mut51656, _mut51657, _mut51658)), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51659, _mut51660, _mut51661, _mut51662)];
        ran.nextBytes(randomBytes);
        // Convert each byte to 2 hex digits
        for (int i = 0; ROR_less(i, randomBytes.length, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51672, _mut51673, _mut51674, _mut51675, _mut51676); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161");
            Integer c = Integer.valueOf(randomBytes[i]);
            /*
             * Add 128 to byte value to make interval 0-255 before doing hex
             * conversion. This guarantees <= 2 hex digits from toHexString()
             * toHexString would otherwise add 2^32 to negative arguments.
             */
            String hex = Integer.toHexString(AOR_plus(c.intValue(), 128, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51663, _mut51664, _mut51665, _mut51666));
            // Make sure we add 2 hex digits for each byte
            if (ROR_equals(hex.length(), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextHexString_161", _mut51667, _mut51668, _mut51669, _mut51670, _mut51671)) {
                hex = "0" + hex;
            }
            outBuffer.append(hex);
        }
        return outBuffer.toString().substring(0, len);
    }

    /**
     * {@inheritDoc}
     */
    public int nextInt(final int lower, final int upper) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(getRandomGenerator(), lower, upper).sample();
    }

    /**
     * {@inheritDoc}
     */
    public long nextLong(final long lower, final long upper) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextLong_202");
        if (ROR_greater_equals(lower, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51677, _mut51678, _mut51679, _mut51680, _mut51681)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, lower, upper, false);
        }
        final long max = AOR_plus((AOR_minus(upper, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51682, _mut51683, _mut51684, _mut51685)), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51686, _mut51687, _mut51688, _mut51689);
        if (ROR_less_equals(max, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51690, _mut51691, _mut51692, _mut51693, _mut51694)) {
            // more than half the long range, we use directly a simple rejection method
            final RandomGenerator rng = getRandomGenerator();
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextLong_202");
                final long r = rng.nextLong();
                if ((_mut51718 ? (ROR_greater_equals(r, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51708, _mut51709, _mut51710, _mut51711, _mut51712) || ROR_less_equals(r, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51713, _mut51714, _mut51715, _mut51716, _mut51717)) : (ROR_greater_equals(r, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51708, _mut51709, _mut51710, _mut51711, _mut51712) && ROR_less_equals(r, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51713, _mut51714, _mut51715, _mut51716, _mut51717)))) {
                    return r;
                }
            }
        } else if (ROR_less(max, Integer.MAX_VALUE, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51695, _mut51696, _mut51697, _mut51698, _mut51699)) {
            // we can shift the range and generate directly a positive int
            return AOR_plus(lower, getRandomGenerator().nextInt((int) max), "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51704, _mut51705, _mut51706, _mut51707);
        } else {
            // we can shift the range and generate directly a positive long
            return AOR_plus(lower, nextLong(getRandomGenerator(), max), "org.apache.commons.math3.random.RandomDataGenerator.nextLong_202", _mut51700, _mut51701, _mut51702, _mut51703);
        }
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code long} value
     * between 0 (inclusive) and the specified value (exclusive), drawn from
     * this random number generator's sequence.
     *
     * @param rng random generator to use
     * @param n the bound on the random number to be returned.  Must be
     * positive.
     * @return  a pseudorandom, uniformly distributed {@code long}
     * value between 0 (inclusive) and n (exclusive).
     * @throws IllegalArgumentException  if n is not positive.
     */
    private static long nextLong(final RandomGenerator rng, final long n) throws IllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextLong_239");
        if (ROR_greater(n, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51719, _mut51720, _mut51721, _mut51722, _mut51723)) {
            final byte[] byteArray = new byte[8];
            long bits;
            long val;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextLong_239");
                rng.nextBytes(byteArray);
                bits = 0;
                for (final byte b : byteArray) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextLong_239");
                    bits = (bits << 8) | (((long) b) & 0xffL);
                }
                bits &= 0x7fffffffffffffffL;
                val = AOR_remainder(bits, n, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51724, _mut51725, _mut51726, _mut51727);
            } while (ROR_less(AOR_plus(AOR_minus(bits, val, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51728, _mut51729, _mut51730, _mut51731), (AOR_minus(n, 1, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51732, _mut51733, _mut51734, _mut51735)), "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51736, _mut51737, _mut51738, _mut51739), 0, "org.apache.commons.math3.random.RandomDataGenerator.nextLong_239", _mut51740, _mut51741, _mut51742, _mut51743, _mut51744));
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Algorithm Description:</strong> hex strings are generated in
     * 40-byte segments using a 3-step process.
     * <ol>
     * <li>
     * 20 random bytes are generated using the underlying
     * <code>SecureRandom</code>.</li>
     * <li>
     * SHA-1 hash is applied to yield a 20-byte binary digest.</li>
     * <li>
     * Each byte of the binary digest is converted to 2 hex digits.</li>
     * </ol>
     * </p>
     * @throws NotStrictlyPositiveException if {@code len <= 0}
     */
    public String nextSecureHexString(int len) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275");
        if (ROR_less_equals(len, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51745, _mut51746, _mut51747, _mut51748, _mut51749)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, len);
        }
        // Get SecureRandom and setup Digest provider
        final RandomGenerator secRan = getSecRan();
        MessageDigest alg = null;
        try {
            alg = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            // this should never happen
            throw new MathInternalError(ex);
        }
        alg.reset();
        // Compute number of iterations required (40 bytes each)
        int numIter = AOR_plus((AOR_divide(len, 40, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51750, _mut51751, _mut51752, _mut51753)), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51754, _mut51755, _mut51756, _mut51757);
        StringBuilder outBuffer = new StringBuilder();
        for (int iter = 1; ROR_less(iter, AOR_plus(numIter, 1, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51772, _mut51773, _mut51774, _mut51775), "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51776, _mut51777, _mut51778, _mut51779, _mut51780); iter++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275");
            byte[] randomBytes = new byte[40];
            secRan.nextBytes(randomBytes);
            alg.update(randomBytes);
            // Compute hash -- will create 20-byte binary hash
            byte[] hash = alg.digest();
            // Loop over the hash, converting each byte to 2 hex digits
            for (int i = 0; ROR_less(i, hash.length, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51767, _mut51768, _mut51769, _mut51770, _mut51771); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275");
                Integer c = Integer.valueOf(hash[i]);
                /*
                 * Add 128 to byte value to make interval 0-255 This guarantees
                 * <= 2 hex digits from toHexString() toHexString would
                 * otherwise add 2^32 to negative arguments
                 */
                String hex = Integer.toHexString(AOR_plus(c.intValue(), 128, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51758, _mut51759, _mut51760, _mut51761));
                // Keep strings uniform length -- guarantees 40 bytes
                if (ROR_equals(hex.length(), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureHexString_275", _mut51762, _mut51763, _mut51764, _mut51765, _mut51766)) {
                    hex = "0" + hex;
                }
                outBuffer.append(hex);
            }
        }
        return outBuffer.toString().substring(0, len);
    }

    /**
     *  {@inheritDoc}
     */
    public int nextSecureInt(final int lower, final int upper) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(getSecRan(), lower, upper).sample();
    }

    /**
     * {@inheritDoc}
     */
    public long nextSecureLong(final long lower, final long upper) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330");
        if (ROR_greater_equals(lower, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51781, _mut51782, _mut51783, _mut51784, _mut51785)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, lower, upper, false);
        }
        final RandomGenerator rng = getSecRan();
        final long max = AOR_plus((AOR_minus(upper, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51786, _mut51787, _mut51788, _mut51789)), 1, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51790, _mut51791, _mut51792, _mut51793);
        if (ROR_less_equals(max, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51794, _mut51795, _mut51796, _mut51797, _mut51798)) {
            // more than half the long range, we use directly a simple rejection method
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330");
                final long r = rng.nextLong();
                if ((_mut51822 ? (ROR_greater_equals(r, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51812, _mut51813, _mut51814, _mut51815, _mut51816) || ROR_less_equals(r, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51817, _mut51818, _mut51819, _mut51820, _mut51821)) : (ROR_greater_equals(r, lower, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51812, _mut51813, _mut51814, _mut51815, _mut51816) && ROR_less_equals(r, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51817, _mut51818, _mut51819, _mut51820, _mut51821)))) {
                    return r;
                }
            }
        } else if (ROR_less(max, Integer.MAX_VALUE, "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51799, _mut51800, _mut51801, _mut51802, _mut51803)) {
            // we can shift the range and generate directly a positive int
            return AOR_plus(lower, rng.nextInt((int) max), "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51808, _mut51809, _mut51810, _mut51811);
        } else {
            // we can shift the range and generate directly a positive long
            return AOR_plus(lower, nextLong(rng, max), "org.apache.commons.math3.random.RandomDataGenerator.nextSecureLong_330", _mut51804, _mut51805, _mut51806, _mut51807);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Algorithm Description</strong>:
     * <ul><li> For small means, uses simulation of a Poisson process
     * using Uniform deviates, as described
     * <a href="http://irmi.epfl.ch/cmos/Pmmi/interactive/rng7.htm"> here.</a>
     * The Poisson process (and hence value returned) is bounded by 1000 * mean.</li>
     *
     * <li> For large means, uses the rejection algorithm described in <br/>
     * Devroye, Luc. (1981).<i>The Computer Generation of Poisson Random Variables</i>
     * <strong>Computing</strong> vol. 26 pp. 197-207.</li></ul></p>
     * @throws NotStrictlyPositiveException if {@code len <= 0}
     */
    public long nextPoisson(double mean) throws NotStrictlyPositiveException {
        return new PoissonDistribution(getRandomGenerator(), mean, PoissonDistribution.DEFAULT_EPSILON, PoissonDistribution.DEFAULT_MAX_ITERATIONS).sample();
    }

    /**
     * {@inheritDoc}
     */
    public double nextGaussian(double mu, double sigma) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextGaussian_376");
        if (ROR_less_equals(sigma, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextGaussian_376", _mut51823, _mut51824, _mut51825, _mut51826, _mut51827)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, sigma);
        }
        return AOR_plus(AOR_multiply(sigma, getRandomGenerator().nextGaussian(), "org.apache.commons.math3.random.RandomDataGenerator.nextGaussian_376", _mut51828, _mut51829, _mut51830, _mut51831), mu, "org.apache.commons.math3.random.RandomDataGenerator.nextGaussian_376", _mut51832, _mut51833, _mut51834, _mut51835);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <strong>Algorithm Description</strong>: Uses the Algorithm SA (Ahrens)
     * from p. 876 in:
     * [1]: Ahrens, J. H. and Dieter, U. (1972). Computer methods for
     * sampling from the exponential and normal distributions.
     * Communications of the ACM, 15, 873-882.
     * </p>
     */
    public double nextExponential(double mean) throws NotStrictlyPositiveException {
        return new ExponentialDistribution(getRandomGenerator(), mean, ExponentialDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * <p>Generates a random value from the
     * {@link org.apache.commons.math3.distribution.GammaDistribution Gamma Distribution}.</p>
     *
     * <p>This implementation uses the following algorithms: </p>
     *
     * <p>For 0 < shape < 1: <br/>
     * Ahrens, J. H. and Dieter, U., <i>Computer methods for
     * sampling from gamma, beta, Poisson and binomial distributions.</i>
     * Computing, 12, 223-246, 1974.</p>
     *
     * <p>For shape >= 1: <br/>
     * Marsaglia and Tsang, <i>A Simple Method for Generating
     * Gamma Variables.</i> ACM Transactions on Mathematical Software,
     * Volume 26 Issue 3, September, 2000.</p>
     *
     * @param shape the median of the Gamma distribution
     * @param scale the scale parameter of the Gamma distribution
     * @return random value sampled from the Gamma(shape, scale) distribution
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     */
    public double nextGamma(double shape, double scale) throws NotStrictlyPositiveException {
        return new GammaDistribution(getRandomGenerator(), shape, scale, GammaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link HypergeometricDistribution Hypergeometric Distribution}.
     *
     * @param populationSize the population size of the Hypergeometric distribution
     * @param numberOfSuccesses number of successes in the population of the Hypergeometric distribution
     * @param sampleSize the sample size of the Hypergeometric distribution
     * @return random value sampled from the Hypergeometric(numberOfSuccesses, sampleSize) distribution
     * @throws NumberIsTooLargeException  if {@code numberOfSuccesses > populationSize},
     * or {@code sampleSize > populationSize}.
     * @throws NotStrictlyPositiveException if {@code populationSize <= 0}.
     * @throws NotPositiveException  if {@code numberOfSuccesses < 0}.
     */
    public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        return new HypergeometricDistribution(getRandomGenerator(), populationSize, numberOfSuccesses, sampleSize).sample();
    }

    /**
     * Generates a random value from the {@link PascalDistribution Pascal Distribution}.
     *
     * @param r the number of successes of the Pascal distribution
     * @param p the probability of success of the Pascal distribution
     * @return random value sampled from the Pascal(r, p) distribution
     * @throws NotStrictlyPositiveException if the number of successes is not positive
     * @throws OutOfRangeException if the probability of success is not in the
     * range {@code [0, 1]}.
     */
    public int nextPascal(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        return new PascalDistribution(getRandomGenerator(), r, p).sample();
    }

    /**
     * Generates a random value from the {@link TDistribution T Distribution}.
     *
     * @param df the degrees of freedom of the T distribution
     * @return random value from the T(df) distribution
     * @throws NotStrictlyPositiveException if {@code df <= 0}
     */
    public double nextT(double df) throws NotStrictlyPositiveException {
        return new TDistribution(getRandomGenerator(), df, TDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link WeibullDistribution Weibull Distribution}.
     *
     * @param shape the shape parameter of the Weibull distribution
     * @param scale the scale parameter of the Weibull distribution
     * @return random value sampled from the Weibull(shape, size) distribution
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     */
    public double nextWeibull(double shape, double scale) throws NotStrictlyPositiveException {
        return new WeibullDistribution(getRandomGenerator(), shape, scale, WeibullDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link ZipfDistribution Zipf Distribution}.
     *
     * @param numberOfElements the number of elements of the ZipfDistribution
     * @param exponent the exponent of the ZipfDistribution
     * @return random value sampled from the Zipf(numberOfElements, exponent) distribution
     * @exception NotStrictlyPositiveException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     */
    public int nextZipf(int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        return new ZipfDistribution(getRandomGenerator(), numberOfElements, exponent).sample();
    }

    /**
     * Generates a random value from the {@link BetaDistribution Beta Distribution}.
     *
     * @param alpha first distribution shape parameter
     * @param beta second distribution shape parameter
     * @return random value sampled from the beta(alpha, beta) distribution
     */
    public double nextBeta(double alpha, double beta) {
        return new BetaDistribution(getRandomGenerator(), alpha, beta, BetaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link BinomialDistribution Binomial Distribution}.
     *
     * @param numberOfTrials number of trials of the Binomial distribution
     * @param probabilityOfSuccess probability of success of the Binomial distribution
     * @return random value sampled from the Binomial(numberOfTrials, probabilityOfSuccess) distribution
     */
    public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) {
        return new BinomialDistribution(getRandomGenerator(), numberOfTrials, probabilityOfSuccess).sample();
    }

    /**
     * Generates a random value from the {@link CauchyDistribution Cauchy Distribution}.
     *
     * @param median the median of the Cauchy distribution
     * @param scale the scale parameter of the Cauchy distribution
     * @return random value sampled from the Cauchy(median, scale) distribution
     */
    public double nextCauchy(double median, double scale) {
        return new CauchyDistribution(getRandomGenerator(), median, scale, CauchyDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link ChiSquaredDistribution ChiSquare Distribution}.
     *
     * @param df the degrees of freedom of the ChiSquare distribution
     * @return random value sampled from the ChiSquare(df) distribution
     */
    public double nextChiSquare(double df) {
        return new ChiSquaredDistribution(getRandomGenerator(), df, ChiSquaredDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * Generates a random value from the {@link FDistribution F Distribution}.
     *
     * @param numeratorDf the numerator degrees of freedom of the F distribution
     * @param denominatorDf the denominator degrees of freedom of the F distribution
     * @return random value sampled from the F(numeratorDf, denominatorDf) distribution
     * @throws NotStrictlyPositiveException if
     * {@code numeratorDf <= 0} or {@code denominatorDf <= 0}.
     */
    public double nextF(double numeratorDf, double denominatorDf) throws NotStrictlyPositiveException {
        return new FDistribution(getRandomGenerator(), numeratorDf, denominatorDf, FDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY).sample();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <strong>Algorithm Description</strong>: scales the output of
     * Random.nextDouble(), but rejects 0 values (i.e., will generate another
     * random double if Random.nextDouble() returns 0). This is necessary to
     * provide a symmetric output interval (both endpoints excluded).
     * </p>
     * @throws NumberIsTooLargeException if {@code lower >= upper}
     * @throws NotFiniteNumberException if one of the bounds is infinite
     * @throws NotANumberException if one of the bounds is NaN
     */
    public double nextUniform(double lower, double upper) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return nextUniform(lower, upper, false);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <strong>Algorithm Description</strong>: if the lower bound is excluded,
     * scales the output of Random.nextDouble(), but rejects 0 values (i.e.,
     * will generate another random double if Random.nextDouble() returns 0).
     * This is necessary to provide a symmetric output interval (both
     * endpoints excluded).
     * </p>
     *
     * @throws NumberIsTooLargeException if {@code lower >= upper}
     * @throws NotFiniteNumberException if one of the bounds is infinite
     * @throws NotANumberException if one of the bounds is NaN
     */
    public double nextUniform(double lower, double upper, boolean lowerInclusive) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589");
        if (ROR_greater_equals(lower, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51836, _mut51837, _mut51838, _mut51839, _mut51840)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, lower, upper, false);
        }
        if (Double.isInfinite(lower)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, lower);
        }
        if (Double.isInfinite(upper)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, upper);
        }
        if ((_mut51841 ? (Double.isNaN(lower) && Double.isNaN(upper)) : (Double.isNaN(lower) || Double.isNaN(upper)))) {
            throw new NotANumberException();
        }
        final RandomGenerator generator = getRandomGenerator();
        // ensure nextDouble() isn't 0.0
        double u = generator.nextDouble();
        while ((_mut51847 ? (!lowerInclusive || ROR_less_equals(u, 0.0, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51842, _mut51843, _mut51844, _mut51845, _mut51846)) : (!lowerInclusive && ROR_less_equals(u, 0.0, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51842, _mut51843, _mut51844, _mut51845, _mut51846)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589");
            u = generator.nextDouble();
        }
        return AOR_plus(AOR_multiply(u, upper, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51848, _mut51849, _mut51850, _mut51851), AOR_multiply((AOR_minus(1.0, u, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51852, _mut51853, _mut51854, _mut51855)), lower, "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51856, _mut51857, _mut51858, _mut51859), "org.apache.commons.math3.random.RandomDataGenerator.nextUniform_589", _mut51860, _mut51861, _mut51862, _mut51863);
    }

    /**
     * {@inheritDoc}
     *
     * This method calls {@link MathArrays#shuffle(int[],RandomGenerator)
     * MathArrays.shuffle} in order to create a random shuffle of the set
     * of natural numbers {@code { 0, 1, ..., n - 1 }}.
     *
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws NotStrictlyPositiveException if {@code k <= 0}.
     */
    public int[] nextPermutation(int n, int k) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextPermutation_629");
        if (ROR_greater(k, n, "org.apache.commons.math3.random.RandomDataGenerator.nextPermutation_629", _mut51864, _mut51865, _mut51866, _mut51867, _mut51868)) {
            throw new NumberIsTooLargeException(LocalizedFormats.PERMUTATION_EXCEEDS_N, k, n, true);
        }
        if (ROR_less_equals(k, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextPermutation_629", _mut51869, _mut51870, _mut51871, _mut51872, _mut51873)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.PERMUTATION_SIZE, k);
        }
        int[] index = MathArrays.natural(n);
        MathArrays.shuffle(index, getRandomGenerator());
        // Return a new array containing the first "k" entries of "index".
        return MathArrays.copyOf(index, k);
    }

    /**
     * {@inheritDoc}
     *
     * This method calls {@link #nextPermutation(int,int) nextPermutation(c.size(), k)}
     * in order to sample the collection.
     */
    public Object[] nextSample(Collection<?> c, int k) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSample_653");
        int len = c.size();
        if (ROR_greater(k, len, "org.apache.commons.math3.random.RandomDataGenerator.nextSample_653", _mut51874, _mut51875, _mut51876, _mut51877, _mut51878)) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, k, len, true);
        }
        if (ROR_less_equals(k, 0, "org.apache.commons.math3.random.RandomDataGenerator.nextSample_653", _mut51879, _mut51880, _mut51881, _mut51882, _mut51883)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, k);
        }
        Object[] objects = c.toArray();
        int[] index = nextPermutation(len, k);
        Object[] result = new Object[k];
        for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.random.RandomDataGenerator.nextSample_653", _mut51884, _mut51885, _mut51886, _mut51887, _mut51888); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.nextSample_653");
            result[i] = objects[index[i]];
        }
        return result;
    }

    /**
     * Reseeds the random number generator with the supplied seed.
     * <p>
     * Will create and initialize if null.
     * </p>
     *
     * @param seed the seed value to use
     */
    public void reSeed(long seed) {
        getRandomGenerator().setSeed(seed);
    }

    /**
     * Reseeds the secure random number generator with the current time in
     * milliseconds.
     * <p>
     * Will create and initialize if null.
     * </p>
     */
    public void reSeedSecure() {
        getSecRan().setSeed(System.currentTimeMillis());
    }

    /**
     * Reseeds the secure random number generator with the supplied seed.
     * <p>
     * Will create and initialize if null.
     * </p>
     *
     * @param seed the seed value to use
     */
    public void reSeedSecure(long seed) {
        getSecRan().setSeed(seed);
    }

    /**
     * Reseeds the random number generator with
     * {@code System.currentTimeMillis() + System.identityHashCode(this))}.
     */
    public void reSeed() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.reSeed_714");
        getRandomGenerator().setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.RandomDataGenerator.reSeed_714", _mut51889, _mut51890, _mut51891, _mut51892));
    }

    /**
     * Sets the PRNG algorithm for the underlying SecureRandom instance using
     * the Security Provider API. The Security Provider API is defined in <a
     * href =
     * "http://java.sun.com/j2se/1.3/docs/guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification & Reference.</a>
     * <p>
     * <strong>USAGE NOTE:</strong> This method carries <i>significant</i>
     * overhead and may take several seconds to execute.
     * </p>
     *
     * @param algorithm the name of the PRNG algorithm
     * @param provider the name of the provider
     * @throws NoSuchAlgorithmException if the specified algorithm is not available
     * @throws NoSuchProviderException if the specified provider is not installed
     */
    public void setSecureAlgorithm(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        secRand = RandomGeneratorFactory.createRandomGenerator(SecureRandom.getInstance(algorithm, provider));
    }

    /**
     * Returns the RandomGenerator used to generate non-secure random data.
     * <p>
     * Creates and initializes a default generator if null. Uses a {@link Well19937c}
     * generator with {@code System.currentTimeMillis() + System.identityHashCode(this))}
     * as the default seed.
     * </p>
     *
     * @return the Random used to generate random data
     * @since 3.2
     */
    public RandomGenerator getRandomGenerator() {
        if (rand == null) {
            initRan();
        }
        return rand;
    }

    /**
     * Sets the default generator to a {@link Well19937c} generator seeded with
     * {@code System.currentTimeMillis() + System.identityHashCode(this))}.
     */
    private void initRan() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.initRan_761");
        rand = new Well19937c(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.RandomDataGenerator.initRan_761", _mut51893, _mut51894, _mut51895, _mut51896));
    }

    /**
     * Returns the SecureRandom used to generate secure random data.
     * <p>
     * Creates and initializes if null.  Uses
     * {@code System.currentTimeMillis() + System.identityHashCode(this)} as the default seed.
     * </p>
     *
     * @return the SecureRandom used to generate secure random data, wrapped in a
     * {@link RandomGenerator}.
     */
    private RandomGenerator getSecRan() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.RandomDataGenerator.getSecRan_775");
        if (secRand == null) {
            secRand = RandomGeneratorFactory.createRandomGenerator(new SecureRandom());
            secRand.setSeed(AOR_plus(System.currentTimeMillis(), System.identityHashCode(this), "org.apache.commons.math3.random.RandomDataGenerator.getSecRan_775", _mut51897, _mut51898, _mut51899, _mut51900));
        }
        return secRand;
    }
}
