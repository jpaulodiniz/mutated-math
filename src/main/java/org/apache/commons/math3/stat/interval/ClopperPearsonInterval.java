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
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.FDistribution;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the Clopper-Pearson method for creating a binomial proportion confidence interval.
 *
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Clopper-Pearson_interval">
 *      Clopper-Pearson interval (Wikipedia)</a>
 * @since 3.3
 */
public class ClopperPearsonInterval implements BinomialConfidenceInterval {

    @Conditional
    public static boolean _mut5053 = false, _mut5054 = false, _mut5055 = false, _mut5056 = false, _mut5057 = false, _mut5058 = false, _mut5059 = false, _mut5060 = false, _mut5061 = false, _mut5062 = false, _mut5063 = false, _mut5064 = false, _mut5065 = false, _mut5066 = false, _mut5067 = false, _mut5068 = false, _mut5069 = false, _mut5070 = false, _mut5071 = false, _mut5072 = false, _mut5073 = false, _mut5074 = false, _mut5075 = false, _mut5076 = false, _mut5077 = false, _mut5078 = false, _mut5079 = false, _mut5080 = false, _mut5081 = false, _mut5082 = false, _mut5083 = false, _mut5084 = false, _mut5085 = false, _mut5086 = false, _mut5087 = false, _mut5088 = false, _mut5089 = false, _mut5090 = false, _mut5091 = false, _mut5092 = false, _mut5093 = false, _mut5094 = false, _mut5095 = false, _mut5096 = false, _mut5097 = false, _mut5098 = false, _mut5099 = false, _mut5100 = false, _mut5101 = false, _mut5102 = false, _mut5103 = false, _mut5104 = false, _mut5105 = false, _mut5106 = false, _mut5107 = false, _mut5108 = false, _mut5109 = false, _mut5110 = false, _mut5111 = false, _mut5112 = false, _mut5113 = false, _mut5114 = false, _mut5115 = false, _mut5116 = false, _mut5117 = false, _mut5118 = false, _mut5119 = false, _mut5120 = false, _mut5121 = false, _mut5122 = false, _mut5123 = false, _mut5124 = false, _mut5125 = false, _mut5126 = false, _mut5127 = false, _mut5128 = false, _mut5129 = false, _mut5130 = false, _mut5131 = false, _mut5132 = false, _mut5133 = false, _mut5134 = false, _mut5135 = false, _mut5136 = false, _mut5137 = false, _mut5138 = false, _mut5139 = false, _mut5140 = false, _mut5141 = false, _mut5142 = false, _mut5143 = false, _mut5144 = false, _mut5145 = false, _mut5146 = false, _mut5147 = false, _mut5148 = false, _mut5149 = false, _mut5150 = false, _mut5151 = false, _mut5152 = false, _mut5153 = false, _mut5154 = false, _mut5155 = false, _mut5156 = false, _mut5157 = false, _mut5158 = false;

    /**
     * {@inheritDoc}
     */
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32");
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double lowerBound = 0;
        double upperBound = 0;
        final double alpha = AOR_divide((AOR_minus(1.0, confidenceLevel, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5053, _mut5054, _mut5055, _mut5056)), 2.0, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5057, _mut5058, _mut5059, _mut5060);
        final FDistribution distributionLowerBound = new FDistribution(AOR_multiply(2, (AOR_plus(AOR_minus(numberOfTrials, numberOfSuccesses, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5061, _mut5062, _mut5063, _mut5064), 1, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5065, _mut5066, _mut5067, _mut5068)), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5069, _mut5070, _mut5071, _mut5072), AOR_multiply(2, numberOfSuccesses, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5073, _mut5074, _mut5075, _mut5076));
        final double fValueLowerBound = distributionLowerBound.inverseCumulativeProbability(AOR_minus(1, alpha, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5077, _mut5078, _mut5079, _mut5080));
        if (ROR_greater(numberOfSuccesses, 0, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5081, _mut5082, _mut5083, _mut5084, _mut5085)) {
            lowerBound = AOR_divide(numberOfSuccesses, (AOR_plus(numberOfSuccesses, AOR_multiply((AOR_plus(AOR_minus(numberOfTrials, numberOfSuccesses, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5086, _mut5087, _mut5088, _mut5089), 1, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5090, _mut5091, _mut5092, _mut5093)), fValueLowerBound, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5094, _mut5095, _mut5096, _mut5097), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5098, _mut5099, _mut5100, _mut5101)), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5102, _mut5103, _mut5104, _mut5105);
        }
        final FDistribution distributionUpperBound = new FDistribution(AOR_multiply(2, (AOR_plus(numberOfSuccesses, 1, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5106, _mut5107, _mut5108, _mut5109)), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5110, _mut5111, _mut5112, _mut5113), AOR_multiply(2, (AOR_minus(numberOfTrials, numberOfSuccesses, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5114, _mut5115, _mut5116, _mut5117)), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5118, _mut5119, _mut5120, _mut5121));
        final double fValueUpperBound = distributionUpperBound.inverseCumulativeProbability(AOR_minus(1, alpha, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5122, _mut5123, _mut5124, _mut5125));
        if (ROR_greater(numberOfSuccesses, 0, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5126, _mut5127, _mut5128, _mut5129, _mut5130)) {
            upperBound = AOR_divide(AOR_multiply((AOR_plus(numberOfSuccesses, 1, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5131, _mut5132, _mut5133, _mut5134)), fValueUpperBound, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5135, _mut5136, _mut5137, _mut5138), (AOR_plus(AOR_minus(numberOfTrials, numberOfSuccesses, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5139, _mut5140, _mut5141, _mut5142), AOR_multiply((AOR_plus(numberOfSuccesses, 1, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5143, _mut5144, _mut5145, _mut5146)), fValueUpperBound, "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5147, _mut5148, _mut5149, _mut5150), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5151, _mut5152, _mut5153, _mut5154)), "org.apache.commons.math3.stat.interval.ClopperPearsonInterval.createInterval_32", _mut5155, _mut5156, _mut5157, _mut5158);
        }
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}
