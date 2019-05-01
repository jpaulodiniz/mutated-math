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
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements binomial test statistics.
 * <p>
 * Exact test for the statistical significance of deviations from a
 * theoretically expected distribution of observations into two categories.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Binomial_test">Binomial test (Wikipedia)</a>
 * @since 3.3
 */
public class BinomialTest {

    @Conditional
    public static boolean _mut7606 = false, _mut7607 = false, _mut7608 = false, _mut7609 = false, _mut7610 = false, _mut7611 = false, _mut7612 = false, _mut7613 = false, _mut7614 = false, _mut7615 = false, _mut7616 = false, _mut7617 = false, _mut7618 = false, _mut7619 = false, _mut7620 = false, _mut7621 = false, _mut7622 = false, _mut7623 = false, _mut7624 = false, _mut7625 = false, _mut7626 = false, _mut7627 = false, _mut7628 = false, _mut7629 = false, _mut7630 = false, _mut7631 = false, _mut7632 = false, _mut7633 = false, _mut7634 = false, _mut7635 = false, _mut7636 = false, _mut7637 = false, _mut7638 = false, _mut7639 = false, _mut7640 = false, _mut7641 = false, _mut7642 = false, _mut7643 = false, _mut7644 = false, _mut7645 = false, _mut7646 = false, _mut7647 = false, _mut7648 = false, _mut7649 = false, _mut7650 = false, _mut7651 = false, _mut7652 = false, _mut7653 = false, _mut7654 = false, _mut7655 = false, _mut7656 = false, _mut7657 = false, _mut7658 = false, _mut7659 = false, _mut7660 = false, _mut7661 = false, _mut7662 = false, _mut7663 = false, _mut7664 = false, _mut7665 = false, _mut7666 = false, _mut7667 = false, _mut7668 = false, _mut7669 = false;

    /**
     * Returns whether the null hypothesis can be rejected with the given confidence level.
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>Number of trials must be &ge; 0.</li>
     * <li>Number of successes must be &ge; 0.</li>
     * <li>Number of successes must be &le; number of trials.</li>
     * <li>Probability must be &ge; 0 and &le; 1.</li>
     * </ul>
     *
     * @param numberOfTrials number of trials performed
     * @param numberOfSuccesses number of successes observed
     * @param probability assumed probability of a single trial under the null hypothesis
     * @param alternativeHypothesis type of hypothesis being evaluated (one- or two-sided)
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with confidence {@code 1 - alpha}
     * @throws NotPositiveException if {@code numberOfTrials} or {@code numberOfSuccesses} is negative
     * @throws OutOfRangeException if {@code probability} is not between 0 and 1
     * @throws MathIllegalArgumentException if {@code numberOfTrials} &lt; {@code numberOfSuccesses} or
     * if {@code alternateHypothesis} is null.
     * @see AlternativeHypothesis
     */
    public boolean binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis, double alpha) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_61");
        double pValue = binomialTest(numberOfTrials, numberOfSuccesses, probability, alternativeHypothesis);
        return ROR_less(pValue, alpha, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_61", _mut7606, _mut7607, _mut7608, _mut7609, _mut7610);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <a href="http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">p-value</a>,
     * associated with a <a href="http://en.wikipedia.org/wiki/Binomial_test"> Binomial test</a>.
     * <p>
     * The number returned is the smallest significance level at which one can reject the null hypothesis.
     * The form of the hypothesis depends on {@code alternativeHypothesis}.</p>
     * <p>
     * The p-Value represents the likelihood of getting a result at least as extreme as the sample,
     * given the provided {@code probability} of success on a single trial. For single-sided tests,
     * this value can be directly derived from the Binomial distribution. For the two-sided test,
     * the implementation works as follows: we start by looking at the most extreme cases
     * (0 success and n success where n is the number of trials from the sample) and determine their likelihood.
     * The lower value is added to the p-Value (if both values are equal, both are added). Then we continue with
     * the next extreme value, until we added the value for the actual observed sample.</p>
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>Number of trials must be &ge; 0.</li>
     * <li>Number of successes must be &ge; 0.</li>
     * <li>Number of successes must be &le; number of trials.</li>
     * <li>Probability must be &ge; 0 and &le; 1.</li>
     * </ul></p>
     *
     * @param numberOfTrials number of trials performed
     * @param numberOfSuccesses number of successes observed
     * @param probability assumed probability of a single trial under the null hypothesis
     * @param alternativeHypothesis type of hypothesis being evaluated (one- or two-sided)
     * @return p-value
     * @throws NotPositiveException if {@code numberOfTrials} or {@code numberOfSuccesses} is negative
     * @throws OutOfRangeException if {@code probability} is not between 0 and 1
     * @throws MathIllegalArgumentException if {@code numberOfTrials} &lt; {@code numberOfSuccesses} or
     * if {@code alternateHypothesis} is null.
     * @see AlternativeHypothesis
     */
    public double binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, AlternativeHypothesis alternativeHypothesis) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102");
        if (ROR_less(numberOfTrials, 0, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7611, _mut7612, _mut7613, _mut7614, _mut7615)) {
            throw new NotPositiveException(numberOfTrials);
        }
        if (ROR_less(numberOfSuccesses, 0, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7616, _mut7617, _mut7618, _mut7619, _mut7620)) {
            throw new NotPositiveException(numberOfSuccesses);
        }
        if ((_mut7631 ? (ROR_less(probability, 0, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7621, _mut7622, _mut7623, _mut7624, _mut7625) && ROR_greater(probability, 1, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7626, _mut7627, _mut7628, _mut7629, _mut7630)) : (ROR_less(probability, 0, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7621, _mut7622, _mut7623, _mut7624, _mut7625) || ROR_greater(probability, 1, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7626, _mut7627, _mut7628, _mut7629, _mut7630)))) {
            throw new OutOfRangeException(probability, 0, 1);
        }
        if (ROR_less(numberOfTrials, numberOfSuccesses, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7632, _mut7633, _mut7634, _mut7635, _mut7636)) {
            throw new MathIllegalArgumentException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, numberOfTrials, numberOfSuccesses);
        }
        if (alternativeHypothesis == null) {
            throw new NullArgumentException();
        }
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final BinomialDistribution distribution = new BinomialDistribution(null, numberOfTrials, probability);
        switch(alternativeHypothesis) {
            case GREATER_THAN:
                return AOR_minus(1, distribution.cumulativeProbability(AOR_minus(numberOfSuccesses, 1, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7637, _mut7638, _mut7639, _mut7640)), "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7641, _mut7642, _mut7643, _mut7644);
            case LESS_THAN:
                return distribution.cumulativeProbability(numberOfSuccesses);
            case TWO_SIDED:
                int criticalValueLow = 0;
                int criticalValueHigh = numberOfTrials;
                double pTotal = 0;
                while (true) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102");
                    double pLow = distribution.probability(criticalValueLow);
                    double pHigh = distribution.probability(criticalValueHigh);
                    if (ROR_equals(pLow, pHigh, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7645, _mut7646, _mut7647, _mut7648, _mut7649)) {
                        pTotal += AOR_multiply(2, pLow, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7655, _mut7656, _mut7657, _mut7658);
                        criticalValueLow++;
                        criticalValueHigh--;
                    } else if (ROR_less(pLow, pHigh, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7650, _mut7651, _mut7652, _mut7653, _mut7654)) {
                        pTotal += pLow;
                        criticalValueLow++;
                    } else {
                        pTotal += pHigh;
                        criticalValueHigh--;
                    }
                    if ((_mut7669 ? (ROR_greater(criticalValueLow, numberOfSuccesses, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7659, _mut7660, _mut7661, _mut7662, _mut7663) && ROR_less(criticalValueHigh, numberOfSuccesses, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7664, _mut7665, _mut7666, _mut7667, _mut7668)) : (ROR_greater(criticalValueLow, numberOfSuccesses, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7659, _mut7660, _mut7661, _mut7662, _mut7663) || ROR_less(criticalValueHigh, numberOfSuccesses, "org.apache.commons.math3.stat.inference.BinomialTest.binomialTest_102", _mut7664, _mut7665, _mut7666, _mut7667, _mut7668)))) {
                        break;
                    }
                }
                return pTotal;
            default:
                throw new MathInternalError(LocalizedFormats.OUT_OF_RANGE_SIMPLE, alternativeHypothesis, AlternativeHypothesis.TWO_SIDED, AlternativeHypothesis.LESS_THAN);
        }
    }
}
