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
package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Factory that creates Gauss-type quadrature rule using Legendre polynomials.
 * In this implementation, the lower and upper bounds of the natural interval
 * of integration are -1 and 1, respectively.
 * The Legendre polynomials are evaluated using the recurrence relation
 * presented in <a href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">
 * Abramowitz and Stegun, 1964</a>.
 *
 * @since 3.1
 */
public class LegendreHighPrecisionRuleFactory extends BaseRuleFactory<BigDecimal> {

    @Conditional
    public static boolean _mut101555 = false, _mut101556 = false, _mut101557 = false, _mut101558 = false, _mut101559 = false, _mut101560 = false, _mut101561 = false, _mut101562 = false, _mut101563 = false, _mut101564 = false, _mut101565 = false, _mut101566 = false, _mut101567 = false, _mut101568 = false, _mut101569 = false, _mut101570 = false, _mut101571 = false, _mut101572 = false, _mut101573 = false, _mut101574 = false, _mut101575 = false, _mut101576 = false, _mut101577 = false, _mut101578 = false, _mut101579 = false, _mut101580 = false, _mut101581 = false, _mut101582 = false, _mut101583 = false, _mut101584 = false, _mut101585 = false, _mut101586 = false, _mut101587 = false, _mut101588 = false, _mut101589 = false, _mut101590 = false, _mut101591 = false, _mut101592 = false, _mut101593 = false, _mut101594 = false, _mut101595 = false, _mut101596 = false, _mut101597 = false, _mut101598 = false, _mut101599 = false, _mut101600 = false, _mut101601 = false, _mut101602 = false, _mut101603 = false, _mut101604 = false, _mut101605 = false, _mut101606 = false, _mut101607 = false, _mut101608 = false, _mut101609 = false, _mut101610 = false, _mut101611 = false, _mut101612 = false, _mut101613 = false, _mut101614 = false, _mut101615 = false, _mut101616 = false, _mut101617 = false, _mut101618 = false, _mut101619 = false, _mut101620 = false, _mut101621 = false, _mut101622 = false, _mut101623 = false, _mut101624 = false, _mut101625 = false, _mut101626 = false, _mut101627 = false, _mut101628 = false, _mut101629 = false, _mut101630 = false, _mut101631 = false, _mut101632 = false, _mut101633 = false, _mut101634 = false, _mut101635 = false, _mut101636 = false, _mut101637 = false, _mut101638 = false, _mut101639 = false, _mut101640 = false, _mut101641 = false, _mut101642 = false, _mut101643 = false, _mut101644 = false, _mut101645 = false, _mut101646 = false, _mut101647 = false, _mut101648 = false, _mut101649 = false, _mut101650 = false, _mut101651 = false, _mut101652 = false, _mut101653 = false, _mut101654 = false, _mut101655 = false, _mut101656 = false, _mut101657 = false, _mut101658 = false, _mut101659 = false, _mut101660 = false;

    /**
     * Settings for enhanced precision computations.
     */
    private final MathContext mContext;

    /**
     * The number {@code 2}.
     */
    private final BigDecimal two;

    /**
     * The number {@code -1}.
     */
    private final BigDecimal minusOne;

    /**
     * The number {@code 0.5}.
     */
    private final BigDecimal oneHalf;

    /**
     * Default precision is {@link MathContext#DECIMAL128 DECIMAL128}.
     */
    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    /**
     * @param mContext Precision setting for computing the quadrature rules.
     */
    public LegendreHighPrecisionRuleFactory(MathContext mContext) {
        this.mContext = mContext;
        two = new BigDecimal("2", mContext);
        minusOne = new BigDecimal("-1", mContext);
        oneHalf = new BigDecimal("0.5", mContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pair<BigDecimal[], BigDecimal[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
        if (ROR_equals(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101555, _mut101556, _mut101557, _mut101558, _mut101559)) {
            // Break recursion.
            return new Pair<BigDecimal[], BigDecimal[]>(new BigDecimal[] { BigDecimal.ZERO }, new BigDecimal[] { two });
        }
        // to this method.
        final BigDecimal[] previousPoints = getRuleInternal(AOR_minus(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101560, _mut101561, _mut101562, _mut101563)).getFirst();
        // Compute next rule.
        final BigDecimal[] points = new BigDecimal[numberOfPoints];
        final BigDecimal[] weights = new BigDecimal[numberOfPoints];
        // Find i-th root of P[n+1] by bracketing.
        final int iMax = AOR_divide(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101564, _mut101565, _mut101566, _mut101567);
        for (int i = 0; ROR_less(i, iMax, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101638, _mut101639, _mut101640, _mut101641, _mut101642); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
            // Lower-bound of the interval.
            BigDecimal a = (ROR_equals(i, 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101568, _mut101569, _mut101570, _mut101571, _mut101572)) ? minusOne : previousPoints[AOR_minus(i, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101573, _mut101574, _mut101575, _mut101576)];
            // Upper-bound of the interval.
            BigDecimal b = (ROR_equals(iMax, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101577, _mut101578, _mut101579, _mut101580, _mut101581)) ? BigDecimal.ONE : previousPoints[i];
            // P[j-1](a)
            BigDecimal pma = BigDecimal.ONE;
            // P[j](a)
            BigDecimal pa = a;
            // P[j-1](b)
            BigDecimal pmb = BigDecimal.ONE;
            // P[j](b)
            BigDecimal pb = b;
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101594, _mut101595, _mut101596, _mut101597, _mut101598); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
                final BigDecimal b_two_j_p_1 = new BigDecimal(AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101582, _mut101583, _mut101584, _mut101585), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101586, _mut101587, _mut101588, _mut101589), mContext);
                final BigDecimal b_j = new BigDecimal(j, mContext);
                final BigDecimal b_j_p_1 = new BigDecimal(AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101590, _mut101591, _mut101592, _mut101593), mContext);
                BigDecimal tmp1 = a.multiply(b_two_j_p_1, mContext);
                tmp1 = pa.multiply(tmp1, mContext);
                BigDecimal tmp2 = pma.multiply(b_j, mContext);
                // P[j+1](a)
                BigDecimal ppa = tmp1.subtract(tmp2, mContext);
                ppa = ppa.divide(b_j_p_1, mContext);
                tmp1 = b.multiply(b_two_j_p_1, mContext);
                tmp1 = pb.multiply(tmp1, mContext);
                tmp2 = pmb.multiply(b_j, mContext);
                // P[j+1](b)
                BigDecimal ppb = tmp1.subtract(tmp2, mContext);
                ppb = ppb.divide(b_j_p_1, mContext);
                pma = pa;
                pa = ppa;
                pmb = pb;
                pb = ppb;
            }
            // Middle of the interval.
            BigDecimal c = a.add(b, mContext).multiply(oneHalf, mContext);
            // P[j-1](c)
            BigDecimal pmc = BigDecimal.ONE;
            // P[j](c)
            BigDecimal pc = c;
            boolean done = false;
            while (!done) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
                BigDecimal tmp1 = b.subtract(a, mContext);
                BigDecimal tmp2 = c.ulp().multiply(BigDecimal.TEN, mContext);
                done = ROR_less_equals(tmp1.compareTo(tmp2), 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101599, _mut101600, _mut101601, _mut101602, _mut101603);
                pmc = BigDecimal.ONE;
                pc = c;
                for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101616, _mut101617, _mut101618, _mut101619, _mut101620); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
                    final BigDecimal b_two_j_p_1 = new BigDecimal(AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101604, _mut101605, _mut101606, _mut101607), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101608, _mut101609, _mut101610, _mut101611), mContext);
                    final BigDecimal b_j = new BigDecimal(j, mContext);
                    final BigDecimal b_j_p_1 = new BigDecimal(AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101612, _mut101613, _mut101614, _mut101615), mContext);
                    // Compute P[j+1](c)
                    tmp1 = c.multiply(b_two_j_p_1, mContext);
                    tmp1 = pc.multiply(tmp1, mContext);
                    tmp2 = pmc.multiply(b_j, mContext);
                    // P[j+1](c)
                    BigDecimal ppc = tmp1.subtract(tmp2, mContext);
                    ppc = ppc.divide(b_j_p_1, mContext);
                    pmc = pc;
                    pc = ppc;
                }
                // Now pc = P[n+1](c) and pmc = P[n](c).
                if (!done) {
                    if (ROR_less_equals(AOR_multiply(pa.signum(), pc.signum(), "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101621, _mut101622, _mut101623, _mut101624), 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101625, _mut101626, _mut101627, _mut101628, _mut101629)) {
                        b = c;
                        pmb = pmc;
                        pb = pc;
                    } else {
                        a = c;
                        pma = pmc;
                        pa = pc;
                    }
                    c = a.add(b, mContext).multiply(oneHalf, mContext);
                }
            }
            final BigDecimal nP = new BigDecimal(numberOfPoints, mContext);
            BigDecimal tmp1 = pmc.subtract(c.multiply(pc, mContext), mContext);
            tmp1 = tmp1.multiply(nP);
            tmp1 = tmp1.pow(2, mContext);
            BigDecimal tmp2 = c.pow(2, mContext);
            tmp2 = BigDecimal.ONE.subtract(tmp2, mContext);
            tmp2 = tmp2.multiply(two, mContext);
            tmp2 = tmp2.divide(tmp1, mContext);
            points[i] = c;
            weights[i] = tmp2;
            final int idx = AOR_minus(AOR_minus(numberOfPoints, i, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101630, _mut101631, _mut101632, _mut101633), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101634, _mut101635, _mut101636, _mut101637);
            points[idx] = c.negate(mContext);
            weights[idx] = tmp2;
        }
        // a FindBugs warning.
        if (ROR_not_equals(AOR_remainder(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101643, _mut101644, _mut101645, _mut101646), 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101647, _mut101648, _mut101649, _mut101650, _mut101651)) {
            BigDecimal pmc = BigDecimal.ONE;
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101656, _mut101657, _mut101658, _mut101659, _mut101660); j += 2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63");
                final BigDecimal b_j = new BigDecimal(j, mContext);
                final BigDecimal b_j_p_1 = new BigDecimal(AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory.computeRule_63", _mut101652, _mut101653, _mut101654, _mut101655), mContext);
                // pmc = -j * pmc / (j + 1);
                pmc = pmc.multiply(b_j, mContext);
                pmc = pmc.divide(b_j_p_1, mContext);
                pmc = pmc.negate(mContext);
            }
            // 2 / pow(numberOfPoints * pmc, 2);
            final BigDecimal nP = new BigDecimal(numberOfPoints, mContext);
            BigDecimal tmp1 = pmc.multiply(nP, mContext);
            tmp1 = tmp1.pow(2, mContext);
            BigDecimal tmp2 = two.divide(tmp1, mContext);
            points[iMax] = BigDecimal.ZERO;
            weights[iMax] = tmp2;
        }
        return new Pair<BigDecimal[], BigDecimal[]>(points, weights);
    }
}
