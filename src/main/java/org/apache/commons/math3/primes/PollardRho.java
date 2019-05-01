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
package org.apache.commons.math3.primes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Pollard's rho factorization algorithm.
 * @since 3.2
 */
class PollardRho {

    @Conditional
    public static boolean _mut118 = false, _mut119 = false, _mut120 = false, _mut121 = false, _mut122 = false, _mut123 = false, _mut124 = false, _mut125 = false, _mut126 = false, _mut127 = false, _mut128 = false, _mut129 = false, _mut130 = false, _mut131 = false, _mut132 = false, _mut133 = false, _mut134 = false, _mut135 = false, _mut136 = false, _mut137 = false, _mut138 = false, _mut139 = false, _mut140 = false, _mut141 = false, _mut142 = false, _mut143 = false, _mut144 = false, _mut145 = false, _mut146 = false, _mut147 = false, _mut148 = false, _mut149 = false, _mut150 = false, _mut151 = false, _mut152 = false, _mut153 = false, _mut154 = false, _mut155 = false, _mut156 = false, _mut157 = false, _mut158 = false, _mut159 = false, _mut160 = false, _mut161 = false, _mut162 = false, _mut163 = false, _mut164 = false, _mut165 = false, _mut166 = false, _mut167 = false, _mut168 = false, _mut169 = false, _mut170 = false, _mut171 = false, _mut172 = false, _mut173 = false, _mut174 = false, _mut175 = false, _mut176 = false, _mut177 = false, _mut178 = false, _mut179 = false, _mut180 = false, _mut181 = false, _mut182 = false, _mut183 = false, _mut184 = false, _mut185 = false, _mut186 = false, _mut187 = false, _mut188 = false, _mut189 = false, _mut190 = false, _mut191 = false, _mut192 = false, _mut193 = false, _mut194 = false, _mut195 = false, _mut196 = false, _mut197 = false, _mut198 = false, _mut199 = false, _mut200 = false, _mut201 = false, _mut202 = false, _mut203 = false, _mut204 = false, _mut205 = false, _mut206 = false, _mut207 = false, _mut208 = false, _mut209 = false, _mut210 = false, _mut211 = false, _mut212 = false, _mut213 = false, _mut214 = false, _mut215 = false, _mut216 = false, _mut217 = false, _mut218 = false, _mut219 = false;

    /**
     * Hide utility class.
     */
    private PollardRho() {
    }

    /**
     * Factorization using Pollard's rho algorithm.
     * @param n number to factors, must be &gt; 0
     * @return the list of prime factors of n.
     */
    public static List<Integer> primeFactors(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.primeFactors_41");
        final List<Integer> factors = new ArrayList<Integer>();
        n = SmallPrimes.smallTrialDivision(n, factors);
        if (ROR_equals(1, n, "org.apache.commons.math3.primes.PollardRho.primeFactors_41", _mut118, _mut119, _mut120, _mut121, _mut122)) {
            return factors;
        }
        if (SmallPrimes.millerRabinPrimeTest(n)) {
            factors.add(n);
            return factors;
        }
        int divisor = rhoBrent(n);
        factors.add(divisor);
        factors.add(AOR_divide(n, divisor, "org.apache.commons.math3.primes.PollardRho.primeFactors_41", _mut123, _mut124, _mut125, _mut126));
        return factors;
    }

    /**
     * Implementation of the Pollard's rho factorization algorithm.
     * <p>
     * This implementation follows the paper "An improved Monte Carlo factorization algorithm"
     * by Richard P. Brent. This avoids the triple computation of f(x) typically found in Pollard's
     * rho implementations. It also batches several gcd computation into 1.
     * <p>
     * The backtracking is not implemented as we deal only with semi-primes.
     *
     * @param n number to factor, must be semi-prime.
     * @return a prime factor of n.
     */
    static int rhoBrent(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.rhoBrent_72");
        final int x0 = 2;
        final int m = 25;
        int cst = SmallPrimes.PRIMES_LAST;
        int y = x0;
        int r = 1;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.rhoBrent_72");
            int x = y;
            for (int i = 0; ROR_less(i, r, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut139, _mut140, _mut141, _mut142, _mut143); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.rhoBrent_72");
                final long y2 = AOR_multiply(((long) y), y, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut127, _mut128, _mut129, _mut130);
                y = (int) (AOR_remainder((AOR_plus(y2, cst, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut131, _mut132, _mut133, _mut134)), n, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut135, _mut136, _mut137, _mut138));
            }
            int k = 0;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.rhoBrent_72");
                final int bound = FastMath.min(m, AOR_minus(r, k, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut144, _mut145, _mut146, _mut147));
                int q = 1;
                for (int i = -3; ROR_less(i, bound, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut182, _mut183, _mut184, _mut185, _mut186); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.rhoBrent_72");
                    // start at -3 to ensure we enter this loop at least 3 times
                    final long y2 = AOR_multiply(((long) y), y, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut148, _mut149, _mut150, _mut151);
                    y = (int) (AOR_remainder((AOR_plus(y2, cst, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut152, _mut153, _mut154, _mut155)), n, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut156, _mut157, _mut158, _mut159));
                    final long divisor = FastMath.abs(AOR_minus(x, y, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut160, _mut161, _mut162, _mut163));
                    if (ROR_equals(0, divisor, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut164, _mut165, _mut166, _mut167, _mut168)) {
                        cst += SmallPrimes.PRIMES_LAST;
                        k = -m;
                        y = x0;
                        r = 1;
                        break;
                    }
                    final long prod = AOR_multiply(divisor, q, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut169, _mut170, _mut171, _mut172);
                    q = (int) (AOR_remainder(prod, n, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut173, _mut174, _mut175, _mut176));
                    if (ROR_equals(0, q, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut177, _mut178, _mut179, _mut180, _mut181)) {
                        return gcdPositive(FastMath.abs((int) divisor), n);
                    }
                }
                final int out = gcdPositive(FastMath.abs(q), n);
                if (ROR_not_equals(1, out, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut187, _mut188, _mut189, _mut190, _mut191)) {
                    return out;
                }
                k += m;
            } while (ROR_less(k, r, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut192, _mut193, _mut194, _mut195, _mut196));
            r = AOR_multiply(2, r, "org.apache.commons.math3.primes.PollardRho.rhoBrent_72", _mut197, _mut198, _mut199, _mut200);
        } while (true);
    }

    /**
     * Gcd between two positive numbers.
     * <p>
     * Gets the greatest common divisor of two numbers, using the "binary gcd" method,
     * which avoids division and modulo operations. See Knuth 4.5.2 algorithm B.
     * This algorithm is due to Josef Stein (1961).
     * </p>
     * Special cases:
     * <ul>
     * <li>The result of {@code gcd(x, x)}, {@code gcd(0, x)} and {@code gcd(x, 0)} is the value of {@code x}.</li>
     * <li>The invocation {@code gcd(0, 0)} is the only one which returns {@code 0}.</li>
     * </ul>
     *
     * @param a first number, must be &ge; 0
     * @param b second number, must be &ge; 0
     * @return gcd(a,b)
     */
    static int gcdPositive(int a, int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.gcdPositive_132");
        // gdc(a,0) = a
        if (ROR_equals(a, 0, "org.apache.commons.math3.primes.PollardRho.gcdPositive_132", _mut201, _mut202, _mut203, _mut204, _mut205)) {
            return b;
        } else if (ROR_equals(b, 0, "org.apache.commons.math3.primes.PollardRho.gcdPositive_132", _mut206, _mut207, _mut208, _mut209, _mut210)) {
            return a;
        }
        // make a and b odd, keep in mind the common power of twos
        final int aTwos = Integer.numberOfTrailingZeros(a);
        a >>= aTwos;
        final int bTwos = Integer.numberOfTrailingZeros(b);
        b >>= bTwos;
        final int shift = FastMath.min(aTwos, bTwos);
        // so next a is the absolute difference and next b is the minimum of current values
        while (ROR_not_equals(a, b, "org.apache.commons.math3.primes.PollardRho.gcdPositive_132", _mut215, _mut216, _mut217, _mut218, _mut219)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.PollardRho.gcdPositive_132");
            final int delta = AOR_minus(a, b, "org.apache.commons.math3.primes.PollardRho.gcdPositive_132", _mut211, _mut212, _mut213, _mut214);
            b = FastMath.min(a, b);
            a = FastMath.abs(delta);
            // remove any power of two in a as b is guaranteed to be odd throughout all iterations
            a >>= Integer.numberOfTrailingZeros(a);
        }
        // gcd(a,a) = a, just "add" the common power of twos
        return a << shift;
    }
}
