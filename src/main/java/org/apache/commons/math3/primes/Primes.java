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

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import java.util.List;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Methods related to prime numbers in the range of <code>int</code>:
 * <ul>
 * <li>primality test</li>
 * <li>prime number generation</li>
 * <li>factorization</li>
 * </ul>
 *
 * @since 3.2
 */
public class Primes {

    @Conditional
    public static boolean _mut220 = false, _mut221 = false, _mut222 = false, _mut223 = false, _mut224 = false, _mut225 = false, _mut226 = false, _mut227 = false, _mut228 = false, _mut229 = false, _mut230 = false, _mut231 = false, _mut232 = false, _mut233 = false, _mut234 = false, _mut235 = false, _mut236 = false, _mut237 = false, _mut238 = false, _mut239 = false, _mut240 = false, _mut241 = false, _mut242 = false, _mut243 = false, _mut244 = false, _mut245 = false, _mut246 = false, _mut247 = false, _mut248 = false, _mut249 = false, _mut250 = false, _mut251 = false, _mut252 = false, _mut253 = false, _mut254 = false, _mut255 = false, _mut256 = false, _mut257 = false, _mut258 = false, _mut259 = false, _mut260 = false, _mut261 = false, _mut262 = false, _mut263 = false, _mut264 = false, _mut265 = false, _mut266 = false, _mut267 = false, _mut268 = false, _mut269 = false, _mut270 = false, _mut271 = false, _mut272 = false;

    /**
     * Hide utility class.
     */
    private Primes() {
    }

    /**
     * Primality test: tells if the argument is a (provable) prime or not.
     * <p>
     * It uses the Miller-Rabin probabilistic test in such a way that a result is guaranteed:
     * it uses the firsts prime numbers as successive base (see Handbook of applied cryptography
     * by Menezes, table 4.1).
     *
     * @param n number to test.
     * @return true if n is prime. (All numbers &lt; 2 return false).
     */
    public static boolean isPrime(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.Primes.isPrime_53");
        if (ROR_less(n, 2, "org.apache.commons.math3.primes.Primes.isPrime_53", _mut220, _mut221, _mut222, _mut223, _mut224)) {
            return false;
        }
        for (int p : SmallPrimes.PRIMES) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.Primes.isPrime_53");
            if (ROR_equals(0, (AOR_remainder(n, p, "org.apache.commons.math3.primes.Primes.isPrime_53", _mut225, _mut226, _mut227, _mut228)), "org.apache.commons.math3.primes.Primes.isPrime_53", _mut229, _mut230, _mut231, _mut232, _mut233)) {
                return ROR_equals(n, p, "org.apache.commons.math3.primes.Primes.isPrime_53", _mut234, _mut235, _mut236, _mut237, _mut238);
            }
        }
        return SmallPrimes.millerRabinPrimeTest(n);
    }

    /**
     * Return the smallest prime greater than or equal to n.
     *
     * @param n a positive number.
     * @return the smallest prime greater than or equal to n.
     * @throws MathIllegalArgumentException if n &lt; 0.
     */
    public static int nextPrime(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.Primes.nextPrime_73");
        if (ROR_less(n, 0, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut239, _mut240, _mut241, _mut242, _mut243)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, n, 0);
        }
        if (ROR_equals(n, 2, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut244, _mut245, _mut246, _mut247, _mut248)) {
            return 2;
        }
        // make sure n is odd
        n |= 1;
        if (ROR_equals(n, 1, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut249, _mut250, _mut251, _mut252, _mut253)) {
            return 2;
        }
        if (isPrime(n)) {
            return n;
        }
        // n should not be a multiple of 3
        final int rem = AOR_remainder(n, 3, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut254, _mut255, _mut256, _mut257);
        if (ROR_equals(0, rem, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut258, _mut259, _mut260, _mut261, _mut262)) {
            // n % 3 == 2
            n += 2;
        } else if (ROR_equals(1, rem, "org.apache.commons.math3.primes.Primes.nextPrime_73", _mut263, _mut264, _mut265, _mut266, _mut267)) {
            // n % 3 == 2
            n += 4;
        }
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.Primes.nextPrime_73");
            // this loop skips all multiple of 3
            if (isPrime(n)) {
                return n;
            }
            // n % 3 == 1
            n += 2;
            if (isPrime(n)) {
                return n;
            }
            // n % 3 == 2
            n += 4;
        }
    }

    /**
     * Prime factors decomposition
     *
     * @param n number to factorize: must be &ge; 2
     * @return list of prime factors of n
     * @throws MathIllegalArgumentException if n &lt; 2.
     */
    public static List<Integer> primeFactors(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.primes.Primes.primeFactors_117");
        if (ROR_less(n, 2, "org.apache.commons.math3.primes.Primes.primeFactors_117", _mut268, _mut269, _mut270, _mut271, _mut272)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NUMBER_TOO_SMALL, n, 2);
        }
        // List<Integer> out = PollardRho.primeFactors(n);
        return SmallPrimes.trialDivision(n);
    }
}
