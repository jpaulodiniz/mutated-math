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
package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Estimates an ordinary least squares regression model
 * with one independent variable.
 * <p>
 * <code> y = intercept + slope * x  </code></p>
 * <p>
 * Standard errors for <code>intercept</code> and <code>slope</code> are
 * available as well as ANOVA, r-square and Pearson's r statistics.</p>
 * <p>
 * Observations (x,y pairs) can be added to the model one at a time or they
 * can be provided in a 2-dimensional array.  The observations are not stored
 * in memory, so there is no limit to the number of observations that can be
 * added to the model.</p>
 * <p>
 * <strong>Usage Notes</strong>: <ul>
 * <li> When there are fewer than two observations in the model, or when
 * there is no variation in the x values (i.e. all x values are the same)
 * all statistics return <code>NaN</code>. At least two observations with
 * different x coordinates are required to estimate a bivariate regression
 * model.
 * </li>
 * <li> Getters for the statistics always compute values based on the current
 * set of observations -- i.e., you can get statistics, then add more data
 * and get updated statistics without using a new instance.  There is no
 * "compute" method that updates all statistics.  Each of the getters performs
 * the necessary computations to return the requested statistic.
 * </li>
 * <li> The intercept term may be suppressed by passing {@code false} to
 * the {@link #SimpleRegression(boolean)} constructor.  When the
 * {@code hasIntercept} property is false, the model is estimated without a
 * constant term and {@link #getIntercept()} returns {@code 0}.</li>
 * </ul></p>
 */
public class SimpleRegression implements Serializable, UpdatingMultipleLinearRegression {

    @Conditional
    public static boolean _mut9799 = false, _mut9800 = false, _mut9801 = false, _mut9802 = false, _mut9803 = false, _mut9804 = false, _mut9805 = false, _mut9806 = false, _mut9807 = false, _mut9808 = false, _mut9809 = false, _mut9810 = false, _mut9811 = false, _mut9812 = false, _mut9813 = false, _mut9814 = false, _mut9815 = false, _mut9816 = false, _mut9817 = false, _mut9818 = false, _mut9819 = false, _mut9820 = false, _mut9821 = false, _mut9822 = false, _mut9823 = false, _mut9824 = false, _mut9825 = false, _mut9826 = false, _mut9827 = false, _mut9828 = false, _mut9829 = false, _mut9830 = false, _mut9831 = false, _mut9832 = false, _mut9833 = false, _mut9834 = false, _mut9835 = false, _mut9836 = false, _mut9837 = false, _mut9838 = false, _mut9839 = false, _mut9840 = false, _mut9841 = false, _mut9842 = false, _mut9843 = false, _mut9844 = false, _mut9845 = false, _mut9846 = false, _mut9847 = false, _mut9848 = false, _mut9849 = false, _mut9850 = false, _mut9851 = false, _mut9852 = false, _mut9853 = false, _mut9854 = false, _mut9855 = false, _mut9856 = false, _mut9857 = false, _mut9858 = false, _mut9859 = false, _mut9860 = false, _mut9861 = false, _mut9862 = false, _mut9863 = false, _mut9864 = false, _mut9865 = false, _mut9866 = false, _mut9867 = false, _mut9868 = false, _mut9869 = false, _mut9870 = false, _mut9871 = false, _mut9872 = false, _mut9873 = false, _mut9874 = false, _mut9875 = false, _mut9876 = false, _mut9877 = false, _mut9878 = false, _mut9879 = false, _mut9880 = false, _mut9881 = false, _mut9882 = false, _mut9883 = false, _mut9884 = false, _mut9885 = false, _mut9886 = false, _mut9887 = false, _mut9888 = false, _mut9889 = false, _mut9890 = false, _mut9891 = false, _mut9892 = false, _mut9893 = false, _mut9894 = false, _mut9895 = false, _mut9896 = false, _mut9897 = false, _mut9898 = false, _mut9899 = false, _mut9900 = false, _mut9901 = false, _mut9902 = false, _mut9903 = false, _mut9904 = false, _mut9905 = false, _mut9906 = false, _mut9907 = false, _mut9908 = false, _mut9909 = false, _mut9910 = false, _mut9911 = false, _mut9912 = false, _mut9913 = false, _mut9914 = false, _mut9915 = false, _mut9916 = false, _mut9917 = false, _mut9918 = false, _mut9919 = false, _mut9920 = false, _mut9921 = false, _mut9922 = false, _mut9923 = false, _mut9924 = false, _mut9925 = false, _mut9926 = false, _mut9927 = false, _mut9928 = false, _mut9929 = false, _mut9930 = false, _mut9931 = false, _mut9932 = false, _mut9933 = false, _mut9934 = false, _mut9935 = false, _mut9936 = false, _mut9937 = false, _mut9938 = false, _mut9939 = false, _mut9940 = false, _mut9941 = false, _mut9942 = false, _mut9943 = false, _mut9944 = false, _mut9945 = false, _mut9946 = false, _mut9947 = false, _mut9948 = false, _mut9949 = false, _mut9950 = false, _mut9951 = false, _mut9952 = false, _mut9953 = false, _mut9954 = false, _mut9955 = false, _mut9956 = false, _mut9957 = false, _mut9958 = false, _mut9959 = false, _mut9960 = false, _mut9961 = false, _mut9962 = false, _mut9963 = false, _mut9964 = false, _mut9965 = false, _mut9966 = false, _mut9967 = false, _mut9968 = false, _mut9969 = false, _mut9970 = false, _mut9971 = false, _mut9972 = false, _mut9973 = false, _mut9974 = false, _mut9975 = false, _mut9976 = false, _mut9977 = false, _mut9978 = false, _mut9979 = false, _mut9980 = false, _mut9981 = false, _mut9982 = false, _mut9983 = false, _mut9984 = false, _mut9985 = false, _mut9986 = false, _mut9987 = false, _mut9988 = false, _mut9989 = false, _mut9990 = false, _mut9991 = false, _mut9992 = false, _mut9993 = false, _mut9994 = false, _mut9995 = false, _mut9996 = false, _mut9997 = false, _mut9998 = false, _mut9999 = false, _mut10000 = false, _mut10001 = false, _mut10002 = false, _mut10003 = false, _mut10004 = false, _mut10005 = false, _mut10006 = false, _mut10007 = false, _mut10008 = false, _mut10009 = false, _mut10010 = false, _mut10011 = false, _mut10012 = false, _mut10013 = false, _mut10014 = false, _mut10015 = false, _mut10016 = false, _mut10017 = false, _mut10018 = false, _mut10019 = false, _mut10020 = false, _mut10021 = false, _mut10022 = false, _mut10023 = false, _mut10024 = false, _mut10025 = false, _mut10026 = false, _mut10027 = false, _mut10028 = false, _mut10029 = false, _mut10030 = false, _mut10031 = false, _mut10032 = false, _mut10033 = false, _mut10034 = false, _mut10035 = false, _mut10036 = false, _mut10037 = false, _mut10038 = false, _mut10039 = false, _mut10040 = false, _mut10041 = false, _mut10042 = false, _mut10043 = false, _mut10044 = false, _mut10045 = false, _mut10046 = false, _mut10047 = false, _mut10048 = false, _mut10049 = false, _mut10050 = false, _mut10051 = false, _mut10052 = false, _mut10053 = false, _mut10054 = false, _mut10055 = false, _mut10056 = false, _mut10057 = false, _mut10058 = false, _mut10059 = false, _mut10060 = false, _mut10061 = false, _mut10062 = false, _mut10063 = false, _mut10064 = false, _mut10065 = false, _mut10066 = false, _mut10067 = false, _mut10068 = false, _mut10069 = false, _mut10070 = false, _mut10071 = false, _mut10072 = false, _mut10073 = false, _mut10074 = false, _mut10075 = false, _mut10076 = false, _mut10077 = false, _mut10078 = false, _mut10079 = false, _mut10080 = false, _mut10081 = false, _mut10082 = false, _mut10083 = false, _mut10084 = false, _mut10085 = false, _mut10086 = false, _mut10087 = false, _mut10088 = false, _mut10089 = false, _mut10090 = false, _mut10091 = false, _mut10092 = false, _mut10093 = false, _mut10094 = false, _mut10095 = false, _mut10096 = false, _mut10097 = false, _mut10098 = false, _mut10099 = false, _mut10100 = false, _mut10101 = false, _mut10102 = false, _mut10103 = false, _mut10104 = false, _mut10105 = false, _mut10106 = false, _mut10107 = false, _mut10108 = false, _mut10109 = false, _mut10110 = false, _mut10111 = false, _mut10112 = false, _mut10113 = false, _mut10114 = false, _mut10115 = false, _mut10116 = false, _mut10117 = false, _mut10118 = false, _mut10119 = false, _mut10120 = false, _mut10121 = false, _mut10122 = false, _mut10123 = false, _mut10124 = false, _mut10125 = false, _mut10126 = false, _mut10127 = false, _mut10128 = false, _mut10129 = false, _mut10130 = false, _mut10131 = false, _mut10132 = false, _mut10133 = false, _mut10134 = false, _mut10135 = false, _mut10136 = false, _mut10137 = false, _mut10138 = false, _mut10139 = false, _mut10140 = false, _mut10141 = false, _mut10142 = false, _mut10143 = false, _mut10144 = false, _mut10145 = false, _mut10146 = false, _mut10147 = false, _mut10148 = false, _mut10149 = false, _mut10150 = false, _mut10151 = false, _mut10152 = false, _mut10153 = false, _mut10154 = false, _mut10155 = false, _mut10156 = false, _mut10157 = false, _mut10158 = false, _mut10159 = false, _mut10160 = false, _mut10161 = false, _mut10162 = false, _mut10163 = false, _mut10164 = false, _mut10165 = false, _mut10166 = false, _mut10167 = false, _mut10168 = false, _mut10169 = false, _mut10170 = false, _mut10171 = false, _mut10172 = false, _mut10173 = false, _mut10174 = false, _mut10175 = false, _mut10176 = false, _mut10177 = false, _mut10178 = false, _mut10179 = false, _mut10180 = false, _mut10181 = false, _mut10182 = false, _mut10183 = false, _mut10184 = false, _mut10185 = false, _mut10186 = false, _mut10187 = false, _mut10188 = false, _mut10189 = false, _mut10190 = false, _mut10191 = false, _mut10192 = false, _mut10193 = false, _mut10194 = false, _mut10195 = false, _mut10196 = false, _mut10197 = false, _mut10198 = false, _mut10199 = false, _mut10200 = false, _mut10201 = false, _mut10202 = false, _mut10203 = false, _mut10204 = false, _mut10205 = false, _mut10206 = false, _mut10207 = false, _mut10208 = false, _mut10209 = false, _mut10210 = false, _mut10211 = false, _mut10212 = false, _mut10213 = false, _mut10214 = false, _mut10215 = false, _mut10216 = false, _mut10217 = false, _mut10218 = false, _mut10219 = false, _mut10220 = false, _mut10221 = false, _mut10222 = false, _mut10223 = false, _mut10224 = false, _mut10225 = false, _mut10226 = false, _mut10227 = false, _mut10228 = false, _mut10229 = false, _mut10230 = false, _mut10231 = false, _mut10232 = false, _mut10233 = false, _mut10234 = false, _mut10235 = false, _mut10236 = false, _mut10237 = false, _mut10238 = false, _mut10239 = false, _mut10240 = false, _mut10241 = false, _mut10242 = false, _mut10243 = false, _mut10244 = false, _mut10245 = false, _mut10246 = false, _mut10247 = false, _mut10248 = false, _mut10249 = false, _mut10250 = false, _mut10251 = false, _mut10252 = false, _mut10253 = false, _mut10254 = false, _mut10255 = false, _mut10256 = false, _mut10257 = false, _mut10258 = false, _mut10259 = false, _mut10260 = false, _mut10261 = false, _mut10262 = false, _mut10263 = false, _mut10264 = false, _mut10265 = false, _mut10266 = false, _mut10267 = false, _mut10268 = false, _mut10269 = false, _mut10270 = false, _mut10271 = false, _mut10272 = false, _mut10273 = false, _mut10274 = false, _mut10275 = false, _mut10276 = false, _mut10277 = false, _mut10278 = false, _mut10279 = false, _mut10280 = false, _mut10281 = false, _mut10282 = false, _mut10283 = false, _mut10284 = false, _mut10285 = false, _mut10286 = false, _mut10287 = false, _mut10288 = false, _mut10289 = false, _mut10290 = false, _mut10291 = false, _mut10292 = false, _mut10293 = false, _mut10294 = false, _mut10295 = false, _mut10296 = false, _mut10297 = false, _mut10298 = false, _mut10299 = false, _mut10300 = false, _mut10301 = false, _mut10302 = false, _mut10303 = false, _mut10304 = false, _mut10305 = false, _mut10306 = false, _mut10307 = false, _mut10308 = false, _mut10309 = false, _mut10310 = false, _mut10311 = false, _mut10312 = false, _mut10313 = false, _mut10314 = false, _mut10315 = false, _mut10316 = false, _mut10317 = false, _mut10318 = false, _mut10319 = false, _mut10320 = false, _mut10321 = false, _mut10322 = false, _mut10323 = false, _mut10324 = false, _mut10325 = false, _mut10326 = false, _mut10327 = false, _mut10328 = false, _mut10329 = false, _mut10330 = false, _mut10331 = false, _mut10332 = false, _mut10333 = false, _mut10334 = false, _mut10335 = false, _mut10336 = false, _mut10337 = false, _mut10338 = false, _mut10339 = false, _mut10340 = false, _mut10341 = false, _mut10342 = false, _mut10343 = false, _mut10344 = false, _mut10345 = false, _mut10346 = false, _mut10347 = false, _mut10348 = false, _mut10349 = false, _mut10350 = false, _mut10351 = false, _mut10352 = false, _mut10353 = false, _mut10354 = false, _mut10355 = false, _mut10356 = false, _mut10357 = false, _mut10358 = false, _mut10359 = false, _mut10360 = false, _mut10361 = false, _mut10362 = false, _mut10363 = false, _mut10364 = false, _mut10365 = false, _mut10366 = false, _mut10367 = false, _mut10368 = false, _mut10369 = false, _mut10370 = false, _mut10371 = false, _mut10372 = false, _mut10373 = false, _mut10374 = false, _mut10375 = false, _mut10376 = false, _mut10377 = false, _mut10378 = false, _mut10379 = false, _mut10380 = false, _mut10381 = false, _mut10382 = false, _mut10383 = false, _mut10384 = false, _mut10385 = false, _mut10386 = false, _mut10387 = false, _mut10388 = false, _mut10389 = false, _mut10390 = false, _mut10391 = false, _mut10392 = false, _mut10393 = false, _mut10394 = false, _mut10395 = false, _mut10396 = false, _mut10397 = false, _mut10398 = false, _mut10399 = false, _mut10400 = false, _mut10401 = false, _mut10402 = false, _mut10403 = false, _mut10404 = false, _mut10405 = false, _mut10406 = false, _mut10407 = false, _mut10408 = false, _mut10409 = false, _mut10410 = false, _mut10411 = false, _mut10412 = false, _mut10413 = false, _mut10414 = false, _mut10415 = false, _mut10416 = false, _mut10417 = false, _mut10418 = false, _mut10419 = false, _mut10420 = false, _mut10421 = false, _mut10422 = false, _mut10423 = false, _mut10424 = false, _mut10425 = false, _mut10426 = false, _mut10427 = false, _mut10428 = false, _mut10429 = false, _mut10430 = false, _mut10431 = false, _mut10432 = false, _mut10433 = false, _mut10434 = false, _mut10435 = false, _mut10436 = false, _mut10437 = false, _mut10438 = false, _mut10439 = false, _mut10440 = false, _mut10441 = false, _mut10442 = false, _mut10443 = false, _mut10444 = false, _mut10445 = false, _mut10446 = false, _mut10447 = false, _mut10448 = false, _mut10449 = false, _mut10450 = false, _mut10451 = false, _mut10452 = false, _mut10453 = false, _mut10454 = false, _mut10455 = false, _mut10456 = false, _mut10457 = false, _mut10458 = false, _mut10459 = false, _mut10460 = false, _mut10461 = false, _mut10462 = false, _mut10463 = false, _mut10464 = false, _mut10465 = false, _mut10466 = false, _mut10467 = false, _mut10468 = false, _mut10469 = false, _mut10470 = false, _mut10471 = false, _mut10472 = false, _mut10473 = false, _mut10474 = false, _mut10475 = false, _mut10476 = false, _mut10477 = false, _mut10478 = false, _mut10479 = false, _mut10480 = false, _mut10481 = false, _mut10482 = false, _mut10483 = false, _mut10484 = false, _mut10485 = false, _mut10486 = false, _mut10487 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -3004689053607543335L;

    /**
     * sum of x values
     */
    private double sumX = 0d;

    /**
     * total variation in x (sum of squared deviations from xbar)
     */
    private double sumXX = 0d;

    /**
     * sum of y values
     */
    private double sumY = 0d;

    /**
     * total variation in y (sum of squared deviations from ybar)
     */
    private double sumYY = 0d;

    /**
     * sum of products
     */
    private double sumXY = 0d;

    /**
     * number of observations
     */
    private long n = 0;

    /**
     * mean of accumulated x values, used in updating formulas
     */
    private double xbar = 0;

    /**
     * mean of accumulated y values, used in updating formulas
     */
    private double ybar = 0;

    /**
     * include an intercept or not
     */
    private final boolean hasIntercept;

    /**
     * Create an empty SimpleRegression instance
     */
    public SimpleRegression() {
        this(true);
    }

    /**
     * Create a SimpleRegression instance, specifying whether or not to estimate
     * an intercept.
     *
     * <p>Use {@code false} to estimate a model with no intercept.  When the
     * {@code hasIntercept} property is false, the model is estimated without a
     * constant term and {@link #getIntercept()} returns {@code 0}.</p>
     *
     * @param includeIntercept whether or not to include an intercept term in
     * the regression model
     */
    public SimpleRegression(boolean includeIntercept) {
        super();
        hasIntercept = includeIntercept;
    }

    /**
     * Adds the observation (x,y) to the regression data set.
     * <p>
     * Uses updating formulas for means and sums of squares defined in
     * "Algorithms for Computing the Sample Variance: Analysis and
     * Recommendations", Chan, T.F., Golub, G.H., and LeVeque, R.J.
     * 1983, American Statistician, vol. 37, pp. 242-247, referenced in
     * Weisberg, S. "Applied Linear Regression". 2nd Ed. 1985.</p>
     *
     * @param x independent variable value
     * @param y dependent variable value
     */
    public void addData(final double x, final double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addData_131");
        if (ROR_equals(n, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9799, _mut9800, _mut9801, _mut9802, _mut9803)) {
            xbar = x;
            ybar = y;
        } else {
            if (hasIntercept) {
                final double fact1 = AOR_plus(1.0, n, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9804, _mut9805, _mut9806, _mut9807);
                final double fact2 = AOR_divide(n, (AOR_plus(1.0, n, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9808, _mut9809, _mut9810, _mut9811)), "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9812, _mut9813, _mut9814, _mut9815);
                final double dx = AOR_minus(x, xbar, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9816, _mut9817, _mut9818, _mut9819);
                final double dy = AOR_minus(y, ybar, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9820, _mut9821, _mut9822, _mut9823);
                sumXX += AOR_multiply(AOR_multiply(dx, dx, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9824, _mut9825, _mut9826, _mut9827), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9828, _mut9829, _mut9830, _mut9831);
                sumYY += AOR_multiply(AOR_multiply(dy, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9832, _mut9833, _mut9834, _mut9835), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9836, _mut9837, _mut9838, _mut9839);
                sumXY += AOR_multiply(AOR_multiply(dx, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9840, _mut9841, _mut9842, _mut9843), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9844, _mut9845, _mut9846, _mut9847);
                xbar += AOR_divide(dx, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9848, _mut9849, _mut9850, _mut9851);
                ybar += AOR_divide(dy, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9852, _mut9853, _mut9854, _mut9855);
            }
        }
        if (!hasIntercept) {
            sumXX += AOR_multiply(x, x, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9856, _mut9857, _mut9858, _mut9859);
            sumYY += AOR_multiply(y, y, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9860, _mut9861, _mut9862, _mut9863);
            sumXY += AOR_multiply(x, y, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_131", _mut9864, _mut9865, _mut9866, _mut9867);
        }
        sumX += x;
        sumY += y;
        n++;
    }

    /**
     * Appends data from another regression calculation to this one.
     *
     * <p>The mean update formulae are based on a paper written by Philippe
     * P&eacute;bay:
     * <a
     * href="http://prod.sandia.gov/techlib/access-control.cgi/2008/086212.pdf">
     * Formulas for Robust, One-Pass Parallel Computation of Covariances and
     * Arbitrary-Order Statistical Moments</a>, 2008, Technical Report
     * SAND2008-6212, Sandia National Laboratories.</p>
     *
     * @param reg model to append data from
     * @since 3.3
     */
    public void append(SimpleRegression reg) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.append_172");
        if (ROR_equals(n, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9868, _mut9869, _mut9870, _mut9871, _mut9872)) {
            xbar = reg.xbar;
            ybar = reg.ybar;
            sumXX = reg.sumXX;
            sumYY = reg.sumYY;
            sumXY = reg.sumXY;
        } else {
            if (hasIntercept) {
                final double fact1 = AOR_divide(reg.n, (double) (AOR_plus(reg.n, n, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9873, _mut9874, _mut9875, _mut9876)), "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9877, _mut9878, _mut9879, _mut9880);
                final double fact2 = AOR_divide(AOR_multiply(n, reg.n, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9881, _mut9882, _mut9883, _mut9884), (double) (AOR_plus(reg.n, n, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9885, _mut9886, _mut9887, _mut9888)), "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9889, _mut9890, _mut9891, _mut9892);
                final double dx = AOR_minus(reg.xbar, xbar, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9893, _mut9894, _mut9895, _mut9896);
                final double dy = AOR_minus(reg.ybar, ybar, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9897, _mut9898, _mut9899, _mut9900);
                sumXX += AOR_plus(reg.sumXX, AOR_multiply(AOR_multiply(dx, dx, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9901, _mut9902, _mut9903, _mut9904), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9905, _mut9906, _mut9907, _mut9908), "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9909, _mut9910, _mut9911, _mut9912);
                sumYY += AOR_plus(reg.sumYY, AOR_multiply(AOR_multiply(dy, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9913, _mut9914, _mut9915, _mut9916), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9917, _mut9918, _mut9919, _mut9920), "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9921, _mut9922, _mut9923, _mut9924);
                sumXY += AOR_plus(reg.sumXY, AOR_multiply(AOR_multiply(dx, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9925, _mut9926, _mut9927, _mut9928), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9929, _mut9930, _mut9931, _mut9932), "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9933, _mut9934, _mut9935, _mut9936);
                xbar += AOR_multiply(dx, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9937, _mut9938, _mut9939, _mut9940);
                ybar += AOR_multiply(dy, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.append_172", _mut9941, _mut9942, _mut9943, _mut9944);
            } else {
                sumXX += reg.sumXX;
                sumYY += reg.sumYY;
                sumXY += reg.sumXY;
            }
        }
        sumX += reg.sumX;
        sumY += reg.sumY;
        n += reg.n;
    }

    /**
     * Removes the observation (x,y) from the regression data set.
     * <p>
     * Mirrors the addData method.  This method permits the use of
     * SimpleRegression instances in streaming mode where the regression
     * is applied to a sliding "window" of observations, however the caller is
     * responsible for maintaining the set of observations in the window.</p>
     *
     * The method has no effect if there are no points of data (i.e. n=0)
     *
     * @param x independent variable value
     * @param y dependent variable value
     */
    public void removeData(final double x, final double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214");
        if (ROR_greater(n, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9945, _mut9946, _mut9947, _mut9948, _mut9949)) {
            if (hasIntercept) {
                final double fact1 = AOR_minus(n, 1.0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9974, _mut9975, _mut9976, _mut9977);
                final double fact2 = AOR_divide(n, (AOR_minus(n, 1.0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9978, _mut9979, _mut9980, _mut9981)), "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9982, _mut9983, _mut9984, _mut9985);
                final double dx = AOR_minus(x, xbar, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9986, _mut9987, _mut9988, _mut9989);
                final double dy = AOR_minus(y, ybar, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9990, _mut9991, _mut9992, _mut9993);
                sumXX -= AOR_multiply(AOR_multiply(dx, dx, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9994, _mut9995, _mut9996, _mut9997), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9998, _mut9999, _mut10000, _mut10001);
                sumYY -= AOR_multiply(AOR_multiply(dy, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10002, _mut10003, _mut10004, _mut10005), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10006, _mut10007, _mut10008, _mut10009);
                sumXY -= AOR_multiply(AOR_multiply(dx, dy, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10010, _mut10011, _mut10012, _mut10013), fact2, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10014, _mut10015, _mut10016, _mut10017);
                xbar -= AOR_divide(dx, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10018, _mut10019, _mut10020, _mut10021);
                ybar -= AOR_divide(dy, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut10022, _mut10023, _mut10024, _mut10025);
            } else {
                final double fact1 = AOR_minus(n, 1.0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9950, _mut9951, _mut9952, _mut9953);
                sumXX -= AOR_multiply(x, x, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9954, _mut9955, _mut9956, _mut9957);
                sumYY -= AOR_multiply(y, y, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9958, _mut9959, _mut9960, _mut9961);
                sumXY -= AOR_multiply(x, y, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9962, _mut9963, _mut9964, _mut9965);
                xbar -= AOR_divide(x, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9966, _mut9967, _mut9968, _mut9969);
                ybar -= AOR_divide(y, fact1, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_214", _mut9970, _mut9971, _mut9972, _mut9973);
            }
            sumX -= x;
            sumY -= y;
            n--;
        }
    }

    /**
     * Adds the observations represented by the elements in
     * <code>data</code>.
     * <p>
     * <code>(data[0][0],data[0][1])</code> will be the first observation, then
     * <code>(data[1][0],data[1][1])</code>, etc.</p>
     * <p>
     * This method does not replace data that has already been added.  The
     * observations represented by <code>data</code> are added to the existing
     * dataset.</p>
     * <p>
     * To replace all data, use <code>clear()</code> before adding the new
     * data.</p>
     *
     * @param data array of observations to be added
     * @throws ModelSpecificationException if the length of {@code data[i]} is not
     * greater than or equal to 2
     */
    public void addData(final double[][] data) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addData_258");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_258", _mut10031, _mut10032, _mut10033, _mut10034, _mut10035); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addData_258");
            if (ROR_less(data[i].length, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.addData_258", _mut10026, _mut10027, _mut10028, _mut10029, _mut10030)) {
                throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, data[i].length, 2);
            }
            addData(data[i][0], data[i][1]);
        }
    }

    /**
     * Adds one observation to the regression model.
     *
     * @param x the independent variables which form the design matrix
     * @param y the dependent or response variable
     * @throws ModelSpecificationException if the length of {@code x} does not equal
     * the number of independent variables in the model
     */
    public void addObservation(final double[] x, final double y) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addObservation_276");
        if ((_mut10041 ? (x == null && ROR_equals(x.length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservation_276", _mut10036, _mut10037, _mut10038, _mut10039, _mut10040)) : (x == null || ROR_equals(x.length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservation_276", _mut10036, _mut10037, _mut10038, _mut10039, _mut10040)))) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, x != null ? x.length : 0, 1);
        }
        addData(x[0], y);
    }

    /**
     * Adds a series of observations to the regression model. The lengths of
     * x and y must be the same and x must be rectangular.
     *
     * @param x a series of observations on the independent variables
     * @param y a series of observations on the dependent variable
     * The length of x and y must be the same
     * @throws ModelSpecificationException if {@code x} is not rectangular, does not match
     * the length of {@code y} or does not contain sufficient data to estimate the model
     */
    public void addObservations(final double[][] x, final double[] y) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294");
        if ((_mut10048 ? ((_mut10042 ? ((x == null) && (y == null)) : ((x == null) || (y == null))) && (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10043, _mut10044, _mut10045, _mut10046, _mut10047))) : ((_mut10042 ? ((x == null) && (y == null)) : ((x == null) || (y == null))) || (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10043, _mut10044, _mut10045, _mut10046, _mut10047))))) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, (x == null) ? 0 : x.length, (y == null) ? 0 : y.length);
        }
        boolean obsOk = true;
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10055, _mut10056, _mut10057, _mut10058, _mut10059); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294");
            if ((_mut10054 ? (x[i] == null && ROR_equals(x[i].length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10049, _mut10050, _mut10051, _mut10052, _mut10053)) : (x[i] == null || ROR_equals(x[i].length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10049, _mut10050, _mut10051, _mut10052, _mut10053)))) {
                obsOk = false;
            }
        }
        if (!obsOk) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, 0, 1);
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294", _mut10060, _mut10061, _mut10062, _mut10063, _mut10064); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.addObservations_294");
            addData(x[i][0], y[i]);
        }
    }

    /**
     * Removes observations represented by the elements in <code>data</code>.
     * <p>
     * If the array is larger than the current n, only the first n elements are
     * processed.  This method permits the use of SimpleRegression instances in
     * streaming mode where the regression is applied to a sliding "window" of
     * observations, however the caller is responsible for maintaining the set
     * of observations in the window.</p>
     * <p>
     * To remove all data, use <code>clear()</code>.</p>
     *
     * @param data array of observations to be removed
     */
    public void removeData(double[][] data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330");
        for (int i = 0; (_mut10075 ? (ROR_less(i, data.length, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330", _mut10065, _mut10066, _mut10067, _mut10068, _mut10069) || ROR_greater(n, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330", _mut10070, _mut10071, _mut10072, _mut10073, _mut10074)) : (ROR_less(i, data.length, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330", _mut10065, _mut10066, _mut10067, _mut10068, _mut10069) && ROR_greater(n, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330", _mut10070, _mut10071, _mut10072, _mut10073, _mut10074))); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.removeData_330");
            removeData(data[i][0], data[i][1]);
        }
    }

    /**
     * Clears all data from the model.
     */
    public void clear() {
        sumX = 0d;
        sumXX = 0d;
        sumY = 0d;
        sumYY = 0d;
        sumXY = 0d;
        n = 0;
    }

    /**
     * Returns the number of observations that have been added to the model.
     *
     * @return n number of observations that have been added.
     */
    public long getN() {
        return n;
    }

    /**
     * Returns the "predicted" <code>y</code> value associated with the
     * supplied <code>x</code> value,  based on the data that has been
     * added to the model when this method is activated.
     * <p>
     * <code> predict(x) = intercept + slope * x </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @param x input <code>x</code> value
     * @return predicted <code>y</code> value
     */
    public double predict(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.predict_374");
        final double b1 = getSlope();
        if (hasIntercept) {
            return AOR_plus(getIntercept(b1), AOR_multiply(b1, x, "org.apache.commons.math3.stat.regression.SimpleRegression.predict_374", _mut10076, _mut10077, _mut10078, _mut10079), "org.apache.commons.math3.stat.regression.SimpleRegression.predict_374", _mut10080, _mut10081, _mut10082, _mut10083);
        }
        return AOR_multiply(b1, x, "org.apache.commons.math3.stat.regression.SimpleRegression.predict_374", _mut10084, _mut10085, _mut10086, _mut10087);
    }

    /**
     * Returns the intercept of the estimated regression line, if
     * {@link #hasIntercept()} is true; otherwise 0.
     * <p>
     * The least squares estimate of the intercept is computed using the
     * <a href="http://www.xycoon.com/estimation4.htm">normal equations</a>.
     * The intercept is sometimes denoted b0.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return the intercept of the regression line if the model includes an
     * intercept; 0 otherwise
     * @see #SimpleRegression(boolean)
     */
    public double getIntercept() {
        return hasIntercept ? getIntercept(getSlope()) : 0.0;
    }

    /**
     * Returns true if the model includes an intercept term.
     *
     * @return true if the regression includes an intercept; false otherwise
     * @see #SimpleRegression(boolean)
     */
    public boolean hasIntercept() {
        return hasIntercept;
    }

    /**
     * Returns the slope of the estimated regression line.
     * <p>
     * The least squares estimate of the slope is computed using the
     * <a href="http://www.xycoon.com/estimation4.htm">normal equations</a>.
     * The slope is sometimes denoted b1.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double.NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return the slope of the regression line
     */
    public double getSlope() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getSlope_431");
        if (ROR_less(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlope_431", _mut10088, _mut10089, _mut10090, _mut10091, _mut10092)) {
            // not enough data
            return Double.NaN;
        }
        if (ROR_less(FastMath.abs(sumXX), AOR_multiply(10, Double.MIN_VALUE, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlope_431", _mut10093, _mut10094, _mut10095, _mut10096), "org.apache.commons.math3.stat.regression.SimpleRegression.getSlope_431", _mut10097, _mut10098, _mut10099, _mut10100, _mut10101)) {
            // not enough variation in x
            return Double.NaN;
        }
        return AOR_divide(sumXY, sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlope_431", _mut10102, _mut10103, _mut10104, _mut10105);
    }

    /**
     * Returns the <a href="http://www.xycoon.com/SumOfSquares.htm">
     * sum of squared errors</a> (SSE) associated with the regression
     * model.
     * <p>
     * The sum is computed using the computational formula</p>
     * <p>
     * <code>SSE = SYY - (SXY * SXY / SXX)</code></p>
     * <p>
     * where <code>SYY</code> is the sum of the squared deviations of the y
     * values about their mean, <code>SXX</code> is similarly defined and
     * <code>SXY</code> is the sum of the products of x and y mean deviations.
     * </p><p>
     * The sums are accumulated using the updating algorithm referenced in
     * {@link #addData}.</p>
     * <p>
     * The return value is constrained to be non-negative - i.e., if due to
     * rounding errors the computational formula returns a negative result,
     * 0 is returned.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return sum of squared errors associated with the regression model
     */
    public double getSumSquaredErrors() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getSumSquaredErrors_470");
        return FastMath.max(0d, AOR_minus(sumYY, AOR_divide(AOR_multiply(sumXY, sumXY, "org.apache.commons.math3.stat.regression.SimpleRegression.getSumSquaredErrors_470", _mut10106, _mut10107, _mut10108, _mut10109), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.getSumSquaredErrors_470", _mut10110, _mut10111, _mut10112, _mut10113), "org.apache.commons.math3.stat.regression.SimpleRegression.getSumSquaredErrors_470", _mut10114, _mut10115, _mut10116, _mut10117));
    }

    /**
     * Returns the sum of squared deviations of the y values about their mean.
     * <p>
     * This is defined as SSTO
     * <a href="http://www.xycoon.com/SumOfSquares.htm">here</a>.</p>
     * <p>
     * If <code>n < 2</code>, this returns <code>Double.NaN</code>.</p>
     *
     * @return sum of squared deviations of y values
     */
    public double getTotalSumSquares() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getTotalSumSquares_484");
        if (ROR_less(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getTotalSumSquares_484", _mut10118, _mut10119, _mut10120, _mut10121, _mut10122)) {
            return Double.NaN;
        }
        return sumYY;
    }

    /**
     * Returns the sum of squared deviations of the x values about their mean.
     *
     * If <code>n < 2</code>, this returns <code>Double.NaN</code>.</p>
     *
     * @return sum of squared deviations of x values
     */
    public double getXSumSquares() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getXSumSquares_498");
        if (ROR_less(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getXSumSquares_498", _mut10123, _mut10124, _mut10125, _mut10126, _mut10127)) {
            return Double.NaN;
        }
        return sumXX;
    }

    /**
     * Returns the sum of crossproducts, x<sub>i</sub>*y<sub>i</sub>.
     *
     * @return sum of cross products
     */
    public double getSumOfCrossProducts() {
        return sumXY;
    }

    /**
     * Returns the sum of squared deviations of the predicted y values about
     * their mean (which equals the mean of y).
     * <p>
     * This is usually abbreviated SSR or SSM.  It is defined as SSM
     * <a href="http://www.xycoon.com/SumOfSquares.htm">here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double.NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return sum of squared deviations of predicted y values
     */
    public double getRegressionSumSquares() {
        return getRegressionSumSquares(getSlope());
    }

    /**
     * Returns the sum of squared errors divided by the degrees of freedom,
     * usually abbreviated MSE.
     * <p>
     * If there are fewer than <strong>three</strong> data pairs in the model,
     * or if there is no variation in <code>x</code>, this returns
     * <code>Double.NaN</code>.</p>
     *
     * @return sum of squared deviations of y values
     */
    public double getMeanSquareError() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544");
        if (ROR_less(n, 3, "org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544", _mut10128, _mut10129, _mut10130, _mut10131, _mut10132)) {
            return Double.NaN;
        }
        return hasIntercept ? (AOR_divide(getSumSquaredErrors(), (AOR_minus(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544", _mut10141, _mut10142, _mut10143, _mut10144)), "org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544", _mut10145, _mut10146, _mut10147, _mut10148)) : (AOR_divide(getSumSquaredErrors(), (AOR_minus(n, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544", _mut10133, _mut10134, _mut10135, _mut10136)), "org.apache.commons.math3.stat.regression.SimpleRegression.getMeanSquareError_544", _mut10137, _mut10138, _mut10139, _mut10140));
    }

    /**
     * Returns <a href="http://mathworld.wolfram.com/CorrelationCoefficient.html">
     * Pearson's product moment correlation coefficient</a>,
     * usually denoted r.
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return Pearson's r
     */
    public double getR() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getR_565");
        double b1 = getSlope();
        double result = FastMath.sqrt(getRSquare());
        if (ROR_less(b1, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.getR_565", _mut10149, _mut10150, _mut10151, _mut10152, _mut10153)) {
            result = -result;
        }
        return result;
    }

    /**
     * Returns the <a href="http://www.xycoon.com/coefficient1.htm">
     * coefficient of determination</a>,
     * usually denoted r-square.
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>At least two observations (with at least two different x values)
     * must have been added before invoking this method. If this method is
     * invoked before a model can be estimated, <code>Double,NaN</code> is
     * returned.
     * </li></ul></p>
     *
     * @return r-square
     */
    public double getRSquare() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getRSquare_588");
        double ssto = getTotalSumSquares();
        return AOR_divide((AOR_minus(ssto, getSumSquaredErrors(), "org.apache.commons.math3.stat.regression.SimpleRegression.getRSquare_588", _mut10154, _mut10155, _mut10156, _mut10157)), ssto, "org.apache.commons.math3.stat.regression.SimpleRegression.getRSquare_588", _mut10158, _mut10159, _mut10160, _mut10161);
    }

    /**
     * Returns the <a href="http://www.xycoon.com/standarderrorb0.htm">
     * standard error of the intercept estimate</a>,
     * usually denoted s(b0).
     * <p>
     * If there are fewer that <strong>three</strong> observations in the
     * model, or if there is no variation in x, this returns
     * <code>Double.NaN</code>.</p> Additionally, a <code>Double.NaN</code> is
     * returned when the intercept is constrained to be zero
     *
     * @return standard error associated with intercept estimate
     */
    public double getInterceptStdErr() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605");
        if (!hasIntercept) {
            return Double.NaN;
        }
        return FastMath.sqrt(AOR_multiply(getMeanSquareError(), (AOR_plus((AOR_divide(1d, n, "org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605", _mut10162, _mut10163, _mut10164, _mut10165)), AOR_divide((AOR_multiply(xbar, xbar, "org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605", _mut10166, _mut10167, _mut10168, _mut10169)), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605", _mut10170, _mut10171, _mut10172, _mut10173), "org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605", _mut10174, _mut10175, _mut10176, _mut10177)), "org.apache.commons.math3.stat.regression.SimpleRegression.getInterceptStdErr_605", _mut10178, _mut10179, _mut10180, _mut10181));
    }

    /**
     * Returns the <a href="http://www.xycoon.com/standerrorb(1).htm">standard
     * error of the slope estimate</a>,
     * usually denoted s(b1).
     * <p>
     * If there are fewer that <strong>three</strong> data pairs in the model,
     * or if there is no variation in x, this returns <code>Double.NaN</code>.
     * </p>
     *
     * @return standard error associated with slope estimate
     */
    public double getSlopeStdErr() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeStdErr_624");
        return FastMath.sqrt(AOR_divide(getMeanSquareError(), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeStdErr_624", _mut10182, _mut10183, _mut10184, _mut10185));
    }

    /**
     * Returns the half-width of a 95% confidence interval for the slope
     * estimate.
     * <p>
     * The 95% confidence interval is</p>
     * <p>
     * <code>(getSlope() - getSlopeConfidenceInterval(),
     * getSlope() + getSlopeConfidenceInterval())</code></p>
     * <p>
     * If there are fewer that <strong>three</strong> observations in the
     * model, or if there is no variation in x, this returns
     * <code>Double.NaN</code>.</p>
     * <p>
     * <strong>Usage Note</strong>:<br>
     * The validity of this statistic depends on the assumption that the
     * observations included in the model are drawn from a
     * <a href="http://mathworld.wolfram.com/BivariateNormalDistribution.html">
     * Bivariate Normal Distribution</a>.</p>
     *
     * @return half-width of 95% confidence interval for the slope estimate
     * @throws OutOfRangeException if the confidence interval can not be computed.
     */
    public double getSlopeConfidenceInterval() throws OutOfRangeException {
        return getSlopeConfidenceInterval(0.05d);
    }

    /**
     * Returns the half-width of a (100-100*alpha)% confidence interval for
     * the slope estimate.
     * <p>
     * The (100-100*alpha)% confidence interval is </p>
     * <p>
     * <code>(getSlope() - getSlopeConfidenceInterval(),
     * getSlope() + getSlopeConfidenceInterval())</code></p>
     * <p>
     * To request, for example, a 99% confidence interval, use
     * <code>alpha = .01</code></p>
     * <p>
     * <strong>Usage Note</strong>:<br>
     * The validity of this statistic depends on the assumption that the
     * observations included in the model are drawn from a
     * <a href="http://mathworld.wolfram.com/BivariateNormalDistribution.html">
     * Bivariate Normal Distribution</a>.</p>
     * <p>
     * <strong> Preconditions:</strong><ul>
     * <li>If there are fewer that <strong>three</strong> observations in the
     * model, or if there is no variation in x, this returns
     * <code>Double.NaN</code>.
     * </li>
     * <li><code>(0 < alpha < 1)</code>; otherwise an
     * <code>OutOfRangeException</code> is thrown.
     * </li></ul></p>
     *
     * @param alpha the desired significance level
     * @return half-width of 95% confidence interval for the slope estimate
     * @throws OutOfRangeException if the confidence interval can not be computed.
     */
    public double getSlopeConfidenceInterval(final double alpha) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685");
        if (ROR_less(n, 3, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10186, _mut10187, _mut10188, _mut10189, _mut10190)) {
            return Double.NaN;
        }
        if ((_mut10201 ? (ROR_greater_equals(alpha, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10191, _mut10192, _mut10193, _mut10194, _mut10195) && ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10196, _mut10197, _mut10198, _mut10199, _mut10200)) : (ROR_greater_equals(alpha, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10191, _mut10192, _mut10193, _mut10194, _mut10195) || ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10196, _mut10197, _mut10198, _mut10199, _mut10200)))) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, alpha, 0, 1);
        }
        // No advertised NotStrictlyPositiveException here - will return NaN above
        TDistribution distribution = new TDistribution(AOR_minus(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10202, _mut10203, _mut10204, _mut10205));
        return AOR_multiply(getSlopeStdErr(), distribution.inverseCumulativeProbability(AOR_minus(1d, AOR_divide(alpha, 2d, "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10206, _mut10207, _mut10208, _mut10209), "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10210, _mut10211, _mut10212, _mut10213)), "org.apache.commons.math3.stat.regression.SimpleRegression.getSlopeConfidenceInterval_685", _mut10214, _mut10215, _mut10216, _mut10217);
    }

    /**
     * Returns the significance level of the slope (equiv) correlation.
     * <p>
     * Specifically, the returned value is the smallest <code>alpha</code>
     * such that the slope confidence interval with significance level
     * equal to <code>alpha</code> does not include <code>0</code>.
     * On regression output, this is often denoted <code>Prob(|t| > 0)</code>
     * </p><p>
     * <strong>Usage Note</strong>:<br>
     * The validity of this statistic depends on the assumption that the
     * observations included in the model are drawn from a
     * <a href="http://mathworld.wolfram.com/BivariateNormalDistribution.html">
     * Bivariate Normal Distribution</a>.</p>
     * <p>
     * If there are fewer that <strong>three</strong> observations in the
     * model, or if there is no variation in x, this returns
     * <code>Double.NaN</code>.</p>
     *
     * @return significance level for slope/correlation
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the significance level can not be computed.
     */
    public double getSignificance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722");
        if (ROR_less(n, 3, "org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722", _mut10218, _mut10219, _mut10220, _mut10221, _mut10222)) {
            return Double.NaN;
        }
        // No advertised NotStrictlyPositiveException here - will return NaN above
        TDistribution distribution = new TDistribution(AOR_minus(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722", _mut10223, _mut10224, _mut10225, _mut10226));
        return AOR_multiply(2d, (AOR_minus(1.0, distribution.cumulativeProbability(AOR_divide(FastMath.abs(getSlope()), getSlopeStdErr(), "org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722", _mut10227, _mut10228, _mut10229, _mut10230)), "org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722", _mut10231, _mut10232, _mut10233, _mut10234)), "org.apache.commons.math3.stat.regression.SimpleRegression.getSignificance_722", _mut10235, _mut10236, _mut10237, _mut10238);
    }

    /**
     * Returns the intercept of the estimated regression line, given the slope.
     * <p>
     * Will return <code>NaN</code> if slope is <code>NaN</code>.</p>
     *
     * @param slope current slope
     * @return the intercept of the regression line
     */
    private double getIntercept(final double slope) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getIntercept_742");
        if (hasIntercept) {
            return AOR_divide((AOR_minus(sumY, AOR_multiply(slope, sumX, "org.apache.commons.math3.stat.regression.SimpleRegression.getIntercept_742", _mut10239, _mut10240, _mut10241, _mut10242), "org.apache.commons.math3.stat.regression.SimpleRegression.getIntercept_742", _mut10243, _mut10244, _mut10245, _mut10246)), n, "org.apache.commons.math3.stat.regression.SimpleRegression.getIntercept_742", _mut10247, _mut10248, _mut10249, _mut10250);
        }
        return 0.0;
    }

    /**
     * Computes SSR from b1.
     *
     * @param slope regression slope estimate
     * @return sum of squared deviations of predicted y values
     */
    private double getRegressionSumSquares(final double slope) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.getRegressionSumSquares_755");
        return AOR_multiply(AOR_multiply(slope, slope, "org.apache.commons.math3.stat.regression.SimpleRegression.getRegressionSumSquares_755", _mut10251, _mut10252, _mut10253, _mut10254), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.getRegressionSumSquares_755", _mut10255, _mut10256, _mut10257, _mut10258);
    }

    /**
     * Performs a regression on data present in buffers and outputs a RegressionResults object.
     *
     * <p>If there are fewer than 3 observations in the model and {@code hasIntercept} is true
     * a {@code NoDataException} is thrown.  If there is no intercept term, the model must
     * contain at least 2 observations.</p>
     *
     * @return RegressionResults acts as a container of regression output
     * @throws ModelSpecificationException if the model is not correctly specified
     * @throws NoDataException if there is not sufficient data in the model to
     * estimate the regression parameters
     */
    public RegressionResults regress() throws ModelSpecificationException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.regress_771");
        if (hasIntercept) {
            if (ROR_less(n, 3, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10272, _mut10273, _mut10274, _mut10275, _mut10276)) {
                throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
            }
            if (ROR_greater(FastMath.abs(sumXX), Precision.SAFE_MIN, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10277, _mut10278, _mut10279, _mut10280, _mut10281)) {
                final double[] params = new double[] { getIntercept(), getSlope() };
                final double mse = getMeanSquareError();
                final double _syy = AOR_plus(sumYY, AOR_divide(AOR_multiply(sumY, sumY, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10294, _mut10295, _mut10296, _mut10297), n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10298, _mut10299, _mut10300, _mut10301), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10302, _mut10303, _mut10304, _mut10305);
                final double[] vcv = new double[] { AOR_multiply(mse, (AOR_plus(AOR_divide(AOR_multiply(xbar, xbar, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10306, _mut10307, _mut10308, _mut10309), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10310, _mut10311, _mut10312, _mut10313), AOR_divide(1.0, n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10314, _mut10315, _mut10316, _mut10317), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10318, _mut10319, _mut10320, _mut10321)), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10322, _mut10323, _mut10324, _mut10325), AOR_divide(AOR_multiply(-xbar, mse, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10326, _mut10327, _mut10328, _mut10329), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10330, _mut10331, _mut10332, _mut10333), AOR_divide(mse, sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10334, _mut10335, _mut10336, _mut10337) };
                return new RegressionResults(params, new double[][] { vcv }, true, n, 2, sumY, _syy, getSumSquaredErrors(), true, false);
            } else {
                final double[] params = new double[] { AOR_divide(sumY, n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10282, _mut10283, _mut10284, _mut10285), Double.NaN };
                // final double mse = getMeanSquareError();
                final double[] vcv = new double[] { AOR_divide(ybar, (AOR_minus(n, 1.0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10286, _mut10287, _mut10288, _mut10289)), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10290, _mut10291, _mut10292, _mut10293), Double.NaN, Double.NaN };
                return new RegressionResults(params, new double[][] { vcv }, true, n, 1, sumY, sumYY, getSumSquaredErrors(), true, false);
            }
        } else {
            if (ROR_less(n, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10259, _mut10260, _mut10261, _mut10262, _mut10263)) {
                throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
            }
            if (!Double.isNaN(sumXX)) {
                final double[] vcv = new double[] { AOR_divide(getMeanSquareError(), sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10264, _mut10265, _mut10266, _mut10267) };
                final double[] params = new double[] { AOR_divide(sumXY, sumXX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_771", _mut10268, _mut10269, _mut10270, _mut10271) };
                return new RegressionResults(params, new double[][] { vcv }, true, n, 1, sumY, sumYY, getSumSquaredErrors(), false, false);
            } else {
                final double[] vcv = new double[] { Double.NaN };
                final double[] params = new double[] { Double.NaN };
                return new RegressionResults(params, new double[][] { vcv }, true, n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
            }
        }
    }

    /**
     * Performs a regression on data present in buffers including only regressors
     * indexed in variablesToInclude and outputs a RegressionResults object
     * @param variablesToInclude an array of indices of regressors to include
     * @return RegressionResults acts as a container of regression output
     * @throws MathIllegalArgumentException if the variablesToInclude array is null or zero length
     * @throws OutOfRangeException if a requested variable is not present in model
     */
    public RegressionResults regress(int[] variablesToInclude) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.SimpleRegression.regress_816");
        if ((_mut10343 ? (variablesToInclude == null && ROR_equals(variablesToInclude.length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10338, _mut10339, _mut10340, _mut10341, _mut10342)) : (variablesToInclude == null || ROR_equals(variablesToInclude.length, 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10338, _mut10339, _mut10340, _mut10341, _mut10342)))) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARRAY_ZERO_LENGTH_OR_NULL_NOT_ALLOWED);
        }
        if ((_mut10355 ? (ROR_greater(variablesToInclude.length, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10344, _mut10345, _mut10346, _mut10347, _mut10348) && ((_mut10354 ? (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10349, _mut10350, _mut10351, _mut10352, _mut10353) || !hasIntercept) : (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10349, _mut10350, _mut10351, _mut10352, _mut10353) && !hasIntercept)))) : (ROR_greater(variablesToInclude.length, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10344, _mut10345, _mut10346, _mut10347, _mut10348) || ((_mut10354 ? (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10349, _mut10350, _mut10351, _mut10352, _mut10353) || !hasIntercept) : (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10349, _mut10350, _mut10351, _mut10352, _mut10353) && !hasIntercept)))))) {
            throw new ModelSpecificationException(LocalizedFormats.ARRAY_SIZE_EXCEEDS_MAX_VARIABLES, ((_mut10361 ? (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10356, _mut10357, _mut10358, _mut10359, _mut10360) || !hasIntercept) : (ROR_greater(variablesToInclude.length, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10356, _mut10357, _mut10358, _mut10359, _mut10360) && !hasIntercept))) ? 1 : 2);
        }
        if (hasIntercept) {
            if (ROR_equals(variablesToInclude.length, 2, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10367, _mut10368, _mut10369, _mut10370, _mut10371)) {
                if (ROR_equals(variablesToInclude[0], 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10473, _mut10474, _mut10475, _mut10476, _mut10477)) {
                    throw new ModelSpecificationException(LocalizedFormats.NOT_INCREASING_SEQUENCE);
                } else if (ROR_not_equals(variablesToInclude[0], 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10478, _mut10479, _mut10480, _mut10481, _mut10482)) {
                    throw new OutOfRangeException(variablesToInclude[0], 0, 1);
                }
                if (ROR_not_equals(variablesToInclude[1], 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10483, _mut10484, _mut10485, _mut10486, _mut10487)) {
                    throw new OutOfRangeException(variablesToInclude[0], 0, 1);
                }
                return regress();
            } else {
                if ((_mut10382 ? (ROR_not_equals(variablesToInclude[0], 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10372, _mut10373, _mut10374, _mut10375, _mut10376) || ROR_not_equals(variablesToInclude[0], 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10377, _mut10378, _mut10379, _mut10380, _mut10381)) : (ROR_not_equals(variablesToInclude[0], 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10372, _mut10373, _mut10374, _mut10375, _mut10376) && ROR_not_equals(variablesToInclude[0], 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10377, _mut10378, _mut10379, _mut10380, _mut10381)))) {
                    throw new OutOfRangeException(variablesToInclude[0], 0, 1);
                }
                final double _mean = AOR_divide(AOR_multiply(sumY, sumY, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10383, _mut10384, _mut10385, _mut10386), n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10387, _mut10388, _mut10389, _mut10390);
                final double _syy = AOR_plus(sumYY, _mean, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10391, _mut10392, _mut10393, _mut10394);
                if (ROR_equals(variablesToInclude[0], 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10395, _mut10396, _mut10397, _mut10398, _mut10399)) {
                    // just the mean
                    final double[] vcv = new double[] { AOR_divide(sumYY, ((AOR_multiply((AOR_minus(n, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10457, _mut10458, _mut10459, _mut10460)), n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10461, _mut10462, _mut10463, _mut10464))), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10465, _mut10466, _mut10467, _mut10468) };
                    final double[] params = new double[] { ybar };
                    return new RegressionResults(params, new double[][] { vcv }, true, n, 1, sumY, AOR_plus(_syy, _mean, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10469, _mut10470, _mut10471, _mut10472), sumYY, true, false);
                } else if (ROR_equals(variablesToInclude[0], 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10400, _mut10401, _mut10402, _mut10403, _mut10404)) {
                    // final double _syy = sumYY + sumY * sumY / ((double) n);
                    final double _sxx = AOR_plus(sumXX, AOR_divide(AOR_multiply(sumX, sumX, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10405, _mut10406, _mut10407, _mut10408), n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10409, _mut10410, _mut10411, _mut10412), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10413, _mut10414, _mut10415, _mut10416);
                    final double _sxy = AOR_plus(sumXY, AOR_divide(AOR_multiply(sumX, sumY, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10417, _mut10418, _mut10419, _mut10420), n, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10421, _mut10422, _mut10423, _mut10424), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10425, _mut10426, _mut10427, _mut10428);
                    final double _sse = FastMath.max(0d, AOR_minus(_syy, AOR_divide(AOR_multiply(_sxy, _sxy, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10429, _mut10430, _mut10431, _mut10432), _sxx, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10433, _mut10434, _mut10435, _mut10436), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10437, _mut10438, _mut10439, _mut10440));
                    final double _mse = AOR_divide(_sse, ((AOR_minus(n, 1, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10441, _mut10442, _mut10443, _mut10444))), "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10445, _mut10446, _mut10447, _mut10448);
                    if (!Double.isNaN(_sxx)) {
                        final double[] vcv = new double[] { AOR_divide(_mse, _sxx, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10449, _mut10450, _mut10451, _mut10452) };
                        final double[] params = new double[] { AOR_divide(_sxy, _sxx, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10453, _mut10454, _mut10455, _mut10456) };
                        return new RegressionResults(params, new double[][] { vcv }, true, n, 1, sumY, _syy, _sse, false, false);
                    } else {
                        final double[] vcv = new double[] { Double.NaN };
                        final double[] params = new double[] { Double.NaN };
                        return new RegressionResults(params, new double[][] { vcv }, true, n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
                    }
                }
            }
        } else {
            if (ROR_not_equals(variablesToInclude[0], 0, "org.apache.commons.math3.stat.regression.SimpleRegression.regress_816", _mut10362, _mut10363, _mut10364, _mut10365, _mut10366)) {
                throw new OutOfRangeException(variablesToInclude[0], 0, 0);
            }
            return regress();
        }
        return null;
    }
}
