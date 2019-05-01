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

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the Agresti-Coull method for creating a binomial proportion confidence interval.
 *
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Agresti-Coull_Interval">
 *      Agresti-Coull interval (Wikipedia)</a>
 * @since 3.3
 */
public class AgrestiCoullInterval implements BinomialConfidenceInterval {

    @Conditional
    public static boolean _mut5185 = false, _mut5186 = false, _mut5187 = false, _mut5188 = false, _mut5189 = false, _mut5190 = false, _mut5191 = false, _mut5192 = false, _mut5193 = false, _mut5194 = false, _mut5195 = false, _mut5196 = false, _mut5197 = false, _mut5198 = false, _mut5199 = false, _mut5200 = false, _mut5201 = false, _mut5202 = false, _mut5203 = false, _mut5204 = false, _mut5205 = false, _mut5206 = false, _mut5207 = false, _mut5208 = false, _mut5209 = false, _mut5210 = false, _mut5211 = false, _mut5212 = false, _mut5213 = false, _mut5214 = false, _mut5215 = false, _mut5216 = false, _mut5217 = false, _mut5218 = false, _mut5219 = false, _mut5220 = false, _mut5221 = false, _mut5222 = false, _mut5223 = false, _mut5224 = false, _mut5225 = false, _mut5226 = false, _mut5227 = false, _mut5228 = false, _mut5229 = false, _mut5230 = false, _mut5231 = false, _mut5232 = false, _mut5233 = false, _mut5234 = false, _mut5235 = false, _mut5236 = false, _mut5237 = false, _mut5238 = false, _mut5239 = false, _mut5240 = false, _mut5241 = false, _mut5242 = false, _mut5243 = false, _mut5244 = false;

    /**
     * {@inheritDoc}
     */
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33");
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        final double alpha = AOR_divide((AOR_minus(1.0, confidenceLevel, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5185, _mut5186, _mut5187, _mut5188)), 2, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5189, _mut5190, _mut5191, _mut5192);
        final NormalDistribution normalDistribution = new NormalDistribution();
        final double z = normalDistribution.inverseCumulativeProbability(AOR_minus(1, alpha, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5193, _mut5194, _mut5195, _mut5196));
        final double zSquared = FastMath.pow(z, 2);
        final double modifiedNumberOfTrials = AOR_plus(numberOfTrials, zSquared, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5197, _mut5198, _mut5199, _mut5200);
        final double modifiedSuccessesRatio = AOR_multiply((AOR_divide(1.0, modifiedNumberOfTrials, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5201, _mut5202, _mut5203, _mut5204)), (AOR_plus(numberOfSuccesses, AOR_multiply(0.5, zSquared, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5205, _mut5206, _mut5207, _mut5208), "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5209, _mut5210, _mut5211, _mut5212)), "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5213, _mut5214, _mut5215, _mut5216);
        final double difference = AOR_multiply(z, FastMath.sqrt(AOR_multiply(AOR_multiply(AOR_divide(1.0, modifiedNumberOfTrials, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5217, _mut5218, _mut5219, _mut5220), modifiedSuccessesRatio, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5221, _mut5222, _mut5223, _mut5224), (AOR_minus(1, modifiedSuccessesRatio, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5225, _mut5226, _mut5227, _mut5228)), "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5229, _mut5230, _mut5231, _mut5232)), "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5233, _mut5234, _mut5235, _mut5236);
        return new ConfidenceInterval(AOR_minus(modifiedSuccessesRatio, difference, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5237, _mut5238, _mut5239, _mut5240), AOR_plus(modifiedSuccessesRatio, difference, "org.apache.commons.math3.stat.interval.AgrestiCoullInterval.createInterval_33", _mut5241, _mut5242, _mut5243, _mut5244), confidenceLevel);
    }
}
