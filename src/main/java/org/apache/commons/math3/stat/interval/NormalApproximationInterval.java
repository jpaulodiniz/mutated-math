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
 * Implements the normal approximation method for creating a binomial proportion confidence interval.
 *
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Normal_approximation_interval">
 *      Normal approximation interval (Wikipedia)</a>
 * @since 3.3
 */
public class NormalApproximationInterval implements BinomialConfidenceInterval {

    @Conditional
    public static boolean _mut4909 = false, _mut4910 = false, _mut4911 = false, _mut4912 = false, _mut4913 = false, _mut4914 = false, _mut4915 = false, _mut4916 = false, _mut4917 = false, _mut4918 = false, _mut4919 = false, _mut4920 = false, _mut4921 = false, _mut4922 = false, _mut4923 = false, _mut4924 = false, _mut4925 = false, _mut4926 = false, _mut4927 = false, _mut4928 = false, _mut4929 = false, _mut4930 = false, _mut4931 = false, _mut4932 = false, _mut4933 = false, _mut4934 = false, _mut4935 = false, _mut4936 = false, _mut4937 = false, _mut4938 = false, _mut4939 = false, _mut4940 = false, _mut4941 = false, _mut4942 = false, _mut4943 = false, _mut4944 = false, _mut4945 = false, _mut4946 = false, _mut4947 = false, _mut4948 = false, _mut4949 = false, _mut4950 = false, _mut4951 = false, _mut4952 = false;

    /**
     * {@inheritDoc}
     */
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33");
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        final double mean = AOR_divide((double) numberOfSuccesses, (double) numberOfTrials, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4909, _mut4910, _mut4911, _mut4912);
        final double alpha = AOR_divide((AOR_minus(1.0, confidenceLevel, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4913, _mut4914, _mut4915, _mut4916)), 2, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4917, _mut4918, _mut4919, _mut4920);
        final NormalDistribution normalDistribution = new NormalDistribution();
        final double difference = AOR_multiply(normalDistribution.inverseCumulativeProbability(AOR_minus(1, alpha, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4921, _mut4922, _mut4923, _mut4924)), FastMath.sqrt(AOR_multiply(AOR_multiply(AOR_divide(1.0, numberOfTrials, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4925, _mut4926, _mut4927, _mut4928), mean, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4929, _mut4930, _mut4931, _mut4932), (AOR_minus(1, mean, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4933, _mut4934, _mut4935, _mut4936)), "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4937, _mut4938, _mut4939, _mut4940)), "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4941, _mut4942, _mut4943, _mut4944);
        return new ConfidenceInterval(AOR_minus(mean, difference, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4945, _mut4946, _mut4947, _mut4948), AOR_plus(mean, difference, "org.apache.commons.math3.stat.interval.NormalApproximationInterval.createInterval_33", _mut4949, _mut4950, _mut4951, _mut4952), confidenceLevel);
    }
}
