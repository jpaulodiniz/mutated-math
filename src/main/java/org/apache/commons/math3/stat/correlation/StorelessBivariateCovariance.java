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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Bivariate Covariance implementation that does not require input data to be
 * stored in memory.
 *
 * <p>This class is based on a paper written by Philippe P&eacute;bay:
 * <a href="http://prod.sandia.gov/techlib/access-control.cgi/2008/086212.pdf">
 * Formulas for Robust, One-Pass Parallel Computation of Covariances and
 * Arbitrary-Order Statistical Moments</a>, 2008, Technical Report SAND2008-6212,
 * Sandia National Laboratories. It computes the covariance for a pair of variables.
 * Use {@link StorelessCovariance} to estimate an entire covariance matrix.</p>
 *
 * <p>Note: This class is package private as it is only used internally in
 * the {@link StorelessCovariance} class.</p>
 *
 * @since 3.0
 */
class StorelessBivariateCovariance {

    @Conditional
    public static boolean _mut10858 = false, _mut10859 = false, _mut10860 = false, _mut10861 = false, _mut10862 = false, _mut10863 = false, _mut10864 = false, _mut10865 = false, _mut10866 = false, _mut10867 = false, _mut10868 = false, _mut10869 = false, _mut10870 = false, _mut10871 = false, _mut10872 = false, _mut10873 = false, _mut10874 = false, _mut10875 = false, _mut10876 = false, _mut10877 = false, _mut10878 = false, _mut10879 = false, _mut10880 = false, _mut10881 = false, _mut10882 = false, _mut10883 = false, _mut10884 = false, _mut10885 = false, _mut10886 = false, _mut10887 = false, _mut10888 = false, _mut10889 = false, _mut10890 = false, _mut10891 = false, _mut10892 = false, _mut10893 = false, _mut10894 = false, _mut10895 = false, _mut10896 = false, _mut10897 = false, _mut10898 = false, _mut10899 = false, _mut10900 = false, _mut10901 = false, _mut10902 = false, _mut10903 = false, _mut10904 = false, _mut10905 = false, _mut10906 = false, _mut10907 = false, _mut10908 = false, _mut10909 = false, _mut10910 = false, _mut10911 = false, _mut10912 = false, _mut10913 = false, _mut10914 = false, _mut10915 = false, _mut10916 = false, _mut10917 = false, _mut10918 = false, _mut10919 = false, _mut10920 = false, _mut10921 = false, _mut10922 = false, _mut10923 = false, _mut10924 = false, _mut10925 = false, _mut10926 = false, _mut10927 = false, _mut10928 = false, _mut10929 = false, _mut10930 = false, _mut10931 = false, _mut10932 = false, _mut10933 = false, _mut10934 = false, _mut10935 = false, _mut10936 = false, _mut10937 = false, _mut10938 = false, _mut10939 = false, _mut10940 = false, _mut10941 = false, _mut10942 = false, _mut10943 = false, _mut10944 = false, _mut10945 = false, _mut10946 = false, _mut10947 = false, _mut10948 = false, _mut10949 = false, _mut10950 = false;

    /**
     * the mean of variable x
     */
    private double meanX;

    /**
     * the mean of variable y
     */
    private double meanY;

    /**
     * number of observations
     */
    private double n;

    /**
     * the running covariance estimate
     */
    private double covarianceNumerator;

    /**
     * flag for bias correction
     */
    private boolean biasCorrected;

    /**
     * Create an empty {@link StorelessBivariateCovariance} instance with
     * bias correction.
     */
    StorelessBivariateCovariance() {
        this(true);
    }

    /**
     * Create an empty {@link StorelessBivariateCovariance} instance.
     *
     * @param biasCorrection if <code>true</code> the covariance estimate is corrected
     * for bias, i.e. n-1 in the denominator, otherwise there is no bias correction,
     * i.e. n in the denominator.
     */
    StorelessBivariateCovariance(final boolean biasCorrection) {
        meanX = meanY = 0.0;
        n = 0;
        covarianceNumerator = 0.0;
        biasCorrected = biasCorrection;
    }

    /**
     * Update the covariance estimation with a pair of variables (x, y).
     *
     * @param x the x value
     * @param y the y value
     */
    public void increment(final double x, final double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83");
        n++;
        final double deltaX = AOR_minus(x, meanX, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10858, _mut10859, _mut10860, _mut10861);
        final double deltaY = AOR_minus(y, meanY, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10862, _mut10863, _mut10864, _mut10865);
        meanX += AOR_divide(deltaX, n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10866, _mut10867, _mut10868, _mut10869);
        meanY += AOR_divide(deltaY, n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10870, _mut10871, _mut10872, _mut10873);
        covarianceNumerator += AOR_multiply(AOR_multiply((AOR_divide((AOR_minus(n, 1.0, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10874, _mut10875, _mut10876, _mut10877)), n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10878, _mut10879, _mut10880, _mut10881)), deltaX, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10882, _mut10883, _mut10884, _mut10885), deltaY, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.increment_83", _mut10886, _mut10887, _mut10888, _mut10889);
    }

    /**
     * Appends another bivariate covariance calculation to this.
     * After this operation, statistics returned should be close to what would
     * have been obtained by by performing all of the {@link #increment(double, double)}
     * operations in {@code cov} directly on this.
     *
     * @param cov StorelessBivariateCovariance instance to append.
     */
    public void append(StorelessBivariateCovariance cov) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100");
        double oldN = n;
        n += cov.n;
        final double deltaX = AOR_minus(cov.meanX, meanX, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10890, _mut10891, _mut10892, _mut10893);
        final double deltaY = AOR_minus(cov.meanY, meanY, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10894, _mut10895, _mut10896, _mut10897);
        meanX += AOR_divide(AOR_multiply(deltaX, cov.n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10898, _mut10899, _mut10900, _mut10901), n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10902, _mut10903, _mut10904, _mut10905);
        meanY += AOR_divide(AOR_multiply(deltaY, cov.n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10906, _mut10907, _mut10908, _mut10909), n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10910, _mut10911, _mut10912, _mut10913);
        covarianceNumerator += AOR_plus(cov.covarianceNumerator, AOR_multiply(AOR_multiply(AOR_divide(AOR_multiply(oldN, cov.n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10914, _mut10915, _mut10916, _mut10917), n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10918, _mut10919, _mut10920, _mut10921), deltaX, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10922, _mut10923, _mut10924, _mut10925), deltaY, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10926, _mut10927, _mut10928, _mut10929), "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.append_100", _mut10930, _mut10931, _mut10932, _mut10933);
    }

    /**
     * Returns the number of observations.
     *
     * @return number of observations
     */
    public double getN() {
        return n;
    }

    /**
     * Return the current covariance estimate.
     *
     * @return the current covariance
     * @throws NumberIsTooSmallException if the number of observations
     * is &lt; 2
     */
    public double getResult() throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.getResult_126");
        if (ROR_less(n, 2, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.getResult_126", _mut10934, _mut10935, _mut10936, _mut10937, _mut10938)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DIMENSION, n, 2, true);
        }
        if (biasCorrected) {
            return AOR_divide(covarianceNumerator, (AOR_minus(n, 1d, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.getResult_126", _mut10943, _mut10944, _mut10945, _mut10946)), "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.getResult_126", _mut10947, _mut10948, _mut10949, _mut10950);
        } else {
            return AOR_divide(covarianceNumerator, n, "org.apache.commons.math3.stat.correlation.StorelessBivariateCovariance.getResult_126", _mut10939, _mut10940, _mut10941, _mut10942);
        }
    }
}
