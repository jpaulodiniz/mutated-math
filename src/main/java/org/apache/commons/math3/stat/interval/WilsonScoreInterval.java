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
 * Implements the Wilson score method for creating a binomial proportion confidence interval.
 *
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Wilson_score_interval">
 *      Wilson score interval (Wikipedia)</a>
 * @since 3.3
 */
public class WilsonScoreInterval implements BinomialConfidenceInterval {

    @Conditional
    public static boolean _mut4953 = false, _mut4954 = false, _mut4955 = false, _mut4956 = false, _mut4957 = false, _mut4958 = false, _mut4959 = false, _mut4960 = false, _mut4961 = false, _mut4962 = false, _mut4963 = false, _mut4964 = false, _mut4965 = false, _mut4966 = false, _mut4967 = false, _mut4968 = false, _mut4969 = false, _mut4970 = false, _mut4971 = false, _mut4972 = false, _mut4973 = false, _mut4974 = false, _mut4975 = false, _mut4976 = false, _mut4977 = false, _mut4978 = false, _mut4979 = false, _mut4980 = false, _mut4981 = false, _mut4982 = false, _mut4983 = false, _mut4984 = false, _mut4985 = false, _mut4986 = false, _mut4987 = false, _mut4988 = false, _mut4989 = false, _mut4990 = false, _mut4991 = false, _mut4992 = false, _mut4993 = false, _mut4994 = false, _mut4995 = false, _mut4996 = false, _mut4997 = false, _mut4998 = false, _mut4999 = false, _mut5000 = false, _mut5001 = false, _mut5002 = false, _mut5003 = false, _mut5004 = false, _mut5005 = false, _mut5006 = false, _mut5007 = false, _mut5008 = false, _mut5009 = false, _mut5010 = false, _mut5011 = false, _mut5012 = false, _mut5013 = false, _mut5014 = false, _mut5015 = false, _mut5016 = false, _mut5017 = false, _mut5018 = false, _mut5019 = false, _mut5020 = false, _mut5021 = false, _mut5022 = false, _mut5023 = false, _mut5024 = false, _mut5025 = false, _mut5026 = false, _mut5027 = false, _mut5028 = false, _mut5029 = false, _mut5030 = false, _mut5031 = false, _mut5032 = false, _mut5033 = false, _mut5034 = false, _mut5035 = false, _mut5036 = false, _mut5037 = false, _mut5038 = false, _mut5039 = false, _mut5040 = false, _mut5041 = false, _mut5042 = false, _mut5043 = false, _mut5044 = false, _mut5045 = false, _mut5046 = false, _mut5047 = false, _mut5048 = false, _mut5049 = false, _mut5050 = false, _mut5051 = false, _mut5052 = false;

    /**
     * {@inheritDoc}
     */
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33");
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        final double alpha = AOR_divide((AOR_minus(1.0, confidenceLevel, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4953, _mut4954, _mut4955, _mut4956)), 2, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4957, _mut4958, _mut4959, _mut4960);
        final NormalDistribution normalDistribution = new NormalDistribution();
        final double z = normalDistribution.inverseCumulativeProbability(AOR_minus(1, alpha, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4961, _mut4962, _mut4963, _mut4964));
        final double zSquared = FastMath.pow(z, 2);
        final double mean = AOR_divide((double) numberOfSuccesses, (double) numberOfTrials, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4965, _mut4966, _mut4967, _mut4968);
        final double factor = AOR_divide(1.0, (AOR_plus(1, AOR_multiply((AOR_divide(1.0, numberOfTrials, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4969, _mut4970, _mut4971, _mut4972)), zSquared, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4973, _mut4974, _mut4975, _mut4976), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4977, _mut4978, _mut4979, _mut4980)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4981, _mut4982, _mut4983, _mut4984);
        final double modifiedSuccessRatio = AOR_plus(mean, AOR_multiply((AOR_divide(1.0, (AOR_multiply(2, numberOfTrials, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4985, _mut4986, _mut4987, _mut4988)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4989, _mut4990, _mut4991, _mut4992)), zSquared, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4993, _mut4994, _mut4995, _mut4996), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut4997, _mut4998, _mut4999, _mut5000);
        final double difference = AOR_multiply(z, FastMath.sqrt(AOR_plus(AOR_multiply(AOR_multiply(AOR_divide(1.0, numberOfTrials, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5001, _mut5002, _mut5003, _mut5004), mean, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5005, _mut5006, _mut5007, _mut5008), (AOR_minus(1, mean, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5009, _mut5010, _mut5011, _mut5012)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5013, _mut5014, _mut5015, _mut5016), (AOR_multiply(AOR_divide(1.0, (AOR_multiply(4, FastMath.pow(numberOfTrials, 2), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5017, _mut5018, _mut5019, _mut5020)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5021, _mut5022, _mut5023, _mut5024), zSquared, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5025, _mut5026, _mut5027, _mut5028)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5029, _mut5030, _mut5031, _mut5032)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5033, _mut5034, _mut5035, _mut5036);
        final double lowerBound = AOR_multiply(factor, (AOR_minus(modifiedSuccessRatio, difference, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5037, _mut5038, _mut5039, _mut5040)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5041, _mut5042, _mut5043, _mut5044);
        final double upperBound = AOR_multiply(factor, (AOR_plus(modifiedSuccessRatio, difference, "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5045, _mut5046, _mut5047, _mut5048)), "org.apache.commons.math3.stat.interval.WilsonScoreInterval.createInterval_33", _mut5049, _mut5050, _mut5051, _mut5052);
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}
