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
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Provide an interval that brackets a local optimum of a function.
 * This code is based on a Python implementation (from <em>SciPy</em>,
 * module {@code optimize.py} v0.5).
 *
 * @since 2.2
 */
public class BracketFinder {

    @Conditional
    public static boolean _mut58909 = false, _mut58910 = false, _mut58911 = false, _mut58912 = false, _mut58913 = false, _mut58914 = false, _mut58915 = false, _mut58916 = false, _mut58917 = false, _mut58918 = false, _mut58919 = false, _mut58920 = false, _mut58921 = false, _mut58922 = false, _mut58923 = false, _mut58924 = false, _mut58925 = false, _mut58926 = false, _mut58927 = false, _mut58928 = false, _mut58929 = false, _mut58930 = false, _mut58931 = false, _mut58932 = false, _mut58933 = false, _mut58934 = false, _mut58935 = false, _mut58936 = false, _mut58937 = false, _mut58938 = false, _mut58939 = false, _mut58940 = false, _mut58941 = false, _mut58942 = false, _mut58943 = false, _mut58944 = false, _mut58945 = false, _mut58946 = false, _mut58947 = false, _mut58948 = false, _mut58949 = false, _mut58950 = false, _mut58951 = false, _mut58952 = false, _mut58953 = false, _mut58954 = false, _mut58955 = false, _mut58956 = false, _mut58957 = false, _mut58958 = false, _mut58959 = false, _mut58960 = false, _mut58961 = false, _mut58962 = false, _mut58963 = false, _mut58964 = false, _mut58965 = false, _mut58966 = false, _mut58967 = false, _mut58968 = false, _mut58969 = false, _mut58970 = false, _mut58971 = false, _mut58972 = false, _mut58973 = false, _mut58974 = false, _mut58975 = false, _mut58976 = false, _mut58977 = false, _mut58978 = false, _mut58979 = false, _mut58980 = false, _mut58981 = false, _mut58982 = false, _mut58983 = false, _mut58984 = false, _mut58985 = false, _mut58986 = false, _mut58987 = false, _mut58988 = false, _mut58989 = false, _mut58990 = false, _mut58991 = false, _mut58992 = false, _mut58993 = false, _mut58994 = false, _mut58995 = false, _mut58996 = false, _mut58997 = false, _mut58998 = false, _mut58999 = false, _mut59000 = false, _mut59001 = false, _mut59002 = false, _mut59003 = false, _mut59004 = false, _mut59005 = false, _mut59006 = false, _mut59007 = false, _mut59008 = false, _mut59009 = false, _mut59010 = false, _mut59011 = false, _mut59012 = false, _mut59013 = false, _mut59014 = false, _mut59015 = false, _mut59016 = false, _mut59017 = false, _mut59018 = false, _mut59019 = false, _mut59020 = false, _mut59021 = false, _mut59022 = false, _mut59023 = false, _mut59024 = false, _mut59025 = false, _mut59026 = false, _mut59027 = false, _mut59028 = false, _mut59029 = false, _mut59030 = false, _mut59031 = false, _mut59032 = false, _mut59033 = false, _mut59034 = false, _mut59035 = false, _mut59036 = false, _mut59037 = false, _mut59038 = false, _mut59039 = false, _mut59040 = false, _mut59041 = false, _mut59042 = false, _mut59043 = false, _mut59044 = false, _mut59045 = false, _mut59046 = false, _mut59047 = false, _mut59048 = false, _mut59049 = false, _mut59050 = false, _mut59051 = false, _mut59052 = false, _mut59053 = false, _mut59054 = false, _mut59055 = false, _mut59056 = false, _mut59057 = false, _mut59058 = false, _mut59059 = false, _mut59060 = false, _mut59061 = false, _mut59062 = false, _mut59063 = false, _mut59064 = false, _mut59065 = false, _mut59066 = false, _mut59067 = false, _mut59068 = false, _mut59069 = false, _mut59070 = false, _mut59071 = false, _mut59072 = false, _mut59073 = false, _mut59074 = false, _mut59075 = false, _mut59076 = false, _mut59077 = false, _mut59078 = false, _mut59079 = false, _mut59080 = false, _mut59081 = false, _mut59082 = false, _mut59083 = false, _mut59084 = false, _mut59085 = false, _mut59086 = false, _mut59087 = false, _mut59088 = false, _mut59089 = false, _mut59090 = false, _mut59091 = false, _mut59092 = false, _mut59093 = false, _mut59094 = false, _mut59095 = false, _mut59096 = false, _mut59097 = false, _mut59098 = false, _mut59099 = false, _mut59100 = false, _mut59101 = false, _mut59102 = false, _mut59103 = false, _mut59104 = false, _mut59105 = false, _mut59106 = false, _mut59107 = false, _mut59108 = false, _mut59109 = false, _mut59110 = false, _mut59111 = false, _mut59112 = false, _mut59113 = false, _mut59114 = false, _mut59115 = false, _mut59116 = false, _mut59117 = false, _mut59118 = false, _mut59119 = false, _mut59120 = false, _mut59121 = false, _mut59122 = false, _mut59123 = false, _mut59124 = false, _mut59125 = false, _mut59126 = false, _mut59127 = false, _mut59128 = false, _mut59129 = false, _mut59130 = false, _mut59131 = false, _mut59132 = false, _mut59133 = false, _mut59134 = false, _mut59135 = false, _mut59136 = false, _mut59137 = false, _mut59138 = false, _mut59139 = false, _mut59140 = false, _mut59141 = false, _mut59142 = false, _mut59143 = false, _mut59144 = false, _mut59145 = false, _mut59146 = false, _mut59147 = false, _mut59148 = false, _mut59149 = false, _mut59150 = false, _mut59151 = false, _mut59152 = false, _mut59153 = false;

    /**
     * Tolerance to avoid division by zero.
     */
    private static final double EPS_MIN = 1e-21;

    /**
     * Golden section.
     */
    private static final double GOLD = 1.618034;

    /**
     * Factor for expanding the interval.
     */
    private final double growLimit;

    /**
     * Counter for function evaluations.
     */
    private IntegerSequence.Incrementor evaluations;

    /**
     * Lower bound of the bracket.
     */
    private double lo;

    /**
     * Higher bound of the bracket.
     */
    private double hi;

    /**
     * Point inside the bracket.
     */
    private double mid;

    /**
     * Function value at {@link #lo}.
     */
    private double fLo;

    /**
     * Function value at {@link #hi}.
     */
    private double fHi;

    /**
     * Function value at {@link #mid}.
     */
    private double fMid;

    /**
     * Constructor with default values {@code 100, 500} (see the
     * {@link #BracketFinder(double,int) other constructor}).
     */
    public BracketFinder() {
        this(100, 500);
    }

    /**
     * Create a bracketing interval finder.
     *
     * @param growLimit Expanding factor.
     * @param maxEvaluations Maximum number of evaluations allowed for finding
     * a bracketing interval.
     */
    public BracketFinder(double growLimit, int maxEvaluations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BracketFinder.BracketFinder_89");
        if (ROR_less_equals(growLimit, 0, "org.apache.commons.math3.optim.univariate.BracketFinder.BracketFinder_89", _mut58909, _mut58910, _mut58911, _mut58912, _mut58913)) {
            throw new NotStrictlyPositiveException(growLimit);
        }
        if (ROR_less_equals(maxEvaluations, 0, "org.apache.commons.math3.optim.univariate.BracketFinder.BracketFinder_89", _mut58914, _mut58915, _mut58916, _mut58917, _mut58918)) {
            throw new NotStrictlyPositiveException(maxEvaluations);
        }
        this.growLimit = growLimit;
        evaluations = IntegerSequence.Incrementor.create().withMaximalCount(maxEvaluations);
    }

    /**
     * Search new points that bracket a local optimum of the function.
     *
     * @param func Function whose optimum should be bracketed.
     * @param goal {@link GoalType Goal type}.
     * @param xA Initial point.
     * @param xB Initial point.
     * @throws TooManyEvaluationsException if the maximum number of evaluations
     * is exceeded.
     */
    public void search(UnivariateFunction func, GoalType goal, double xA, double xB) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BracketFinder.search_112");
        evaluations = evaluations.withStart(0);
        final boolean isMinim = goal == GoalType.MINIMIZE;
        double fA = eval(func, xA);
        double fB = eval(func, xB);
        if (isMinim ? ROR_less(fA, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58924, _mut58925, _mut58926, _mut58927, _mut58928) : ROR_greater(fA, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58919, _mut58920, _mut58921, _mut58922, _mut58923)) {
            double tmp = xA;
            xA = xB;
            xB = tmp;
            tmp = fA;
            fA = fB;
            fB = tmp;
        }
        double xC = AOR_plus(xB, AOR_multiply(GOLD, (AOR_minus(xB, xA, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58929, _mut58930, _mut58931, _mut58932)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58933, _mut58934, _mut58935, _mut58936), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58937, _mut58938, _mut58939, _mut58940);
        double fC = eval(func, xC);
        while (isMinim ? ROR_less(fC, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59144, _mut59145, _mut59146, _mut59147, _mut59148) : ROR_greater(fC, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59139, _mut59140, _mut59141, _mut59142, _mut59143)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.BracketFinder.search_112");
            double tmp1 = AOR_multiply((AOR_minus(xB, xA, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58941, _mut58942, _mut58943, _mut58944)), (AOR_minus(fB, fC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58945, _mut58946, _mut58947, _mut58948)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58949, _mut58950, _mut58951, _mut58952);
            double tmp2 = AOR_multiply((AOR_minus(xB, xC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58953, _mut58954, _mut58955, _mut58956)), (AOR_minus(fB, fA, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58957, _mut58958, _mut58959, _mut58960)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58961, _mut58962, _mut58963, _mut58964);
            double val = AOR_minus(tmp2, tmp1, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58965, _mut58966, _mut58967, _mut58968);
            double denom = ROR_less(FastMath.abs(val), EPS_MIN, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58969, _mut58970, _mut58971, _mut58972, _mut58973) ? AOR_multiply(2, EPS_MIN, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58978, _mut58979, _mut58980, _mut58981) : AOR_multiply(2, val, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58974, _mut58975, _mut58976, _mut58977);
            double w = AOR_minus(xB, AOR_divide((AOR_minus(AOR_multiply((AOR_minus(xB, xC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58982, _mut58983, _mut58984, _mut58985)), tmp2, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58986, _mut58987, _mut58988, _mut58989), AOR_multiply((AOR_minus(xB, xA, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58990, _mut58991, _mut58992, _mut58993)), tmp1, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58994, _mut58995, _mut58996, _mut58997), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut58998, _mut58999, _mut59000, _mut59001)), denom, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59002, _mut59003, _mut59004, _mut59005), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59006, _mut59007, _mut59008, _mut59009);
            double wLim = AOR_plus(xB, AOR_multiply(growLimit, (AOR_minus(xC, xB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59010, _mut59011, _mut59012, _mut59013)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59014, _mut59015, _mut59016, _mut59017), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59018, _mut59019, _mut59020, _mut59021);
            double fW;
            if (ROR_greater(AOR_multiply((AOR_minus(w, xC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59022, _mut59023, _mut59024, _mut59025)), (AOR_minus(xB, w, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59026, _mut59027, _mut59028, _mut59029)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59030, _mut59031, _mut59032, _mut59033), 0, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59034, _mut59035, _mut59036, _mut59037, _mut59038)) {
                fW = eval(func, w);
                if (isMinim ? ROR_less(fW, fC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59112, _mut59113, _mut59114, _mut59115, _mut59116) : ROR_greater(fW, fC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59107, _mut59108, _mut59109, _mut59110, _mut59111)) {
                    xA = xB;
                    xB = w;
                    fA = fB;
                    fB = fW;
                    break;
                } else if (isMinim ? ROR_greater(fW, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59122, _mut59123, _mut59124, _mut59125, _mut59126) : ROR_less(fW, fB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59117, _mut59118, _mut59119, _mut59120, _mut59121)) {
                    xC = w;
                    fC = fW;
                    break;
                }
                w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59127, _mut59128, _mut59129, _mut59130)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59131, _mut59132, _mut59133, _mut59134), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59135, _mut59136, _mut59137, _mut59138);
                fW = eval(func, w);
            } else if (ROR_greater_equals(AOR_multiply((AOR_minus(w, wLim, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59039, _mut59040, _mut59041, _mut59042)), (AOR_minus(wLim, xC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59043, _mut59044, _mut59045, _mut59046)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59047, _mut59048, _mut59049, _mut59050), 0, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59051, _mut59052, _mut59053, _mut59054, _mut59055)) {
                w = wLim;
                fW = eval(func, w);
            } else if (ROR_greater(AOR_multiply((AOR_minus(w, wLim, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59056, _mut59057, _mut59058, _mut59059)), (AOR_minus(xC, w, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59060, _mut59061, _mut59062, _mut59063)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59064, _mut59065, _mut59066, _mut59067), 0, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59068, _mut59069, _mut59070, _mut59071, _mut59072)) {
                fW = eval(func, w);
                if (isMinim ? ROR_less(fW, fC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59090, _mut59091, _mut59092, _mut59093, _mut59094) : ROR_greater(fW, fC, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59085, _mut59086, _mut59087, _mut59088, _mut59089)) {
                    xB = xC;
                    xC = w;
                    w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59095, _mut59096, _mut59097, _mut59098)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59099, _mut59100, _mut59101, _mut59102), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59103, _mut59104, _mut59105, _mut59106);
                    fB = fC;
                    fC = fW;
                    fW = eval(func, w);
                }
            } else {
                w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59073, _mut59074, _mut59075, _mut59076)), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59077, _mut59078, _mut59079, _mut59080), "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59081, _mut59082, _mut59083, _mut59084);
                fW = eval(func, w);
            }
            xA = xB;
            fA = fB;
            xB = xC;
            fB = fC;
            xC = w;
            fC = fW;
        }
        lo = xA;
        fLo = fA;
        mid = xB;
        fMid = fB;
        hi = xC;
        fHi = fC;
        if (ROR_greater(lo, hi, "org.apache.commons.math3.optim.univariate.BracketFinder.search_112", _mut59149, _mut59150, _mut59151, _mut59152, _mut59153)) {
            double tmp = lo;
            lo = hi;
            hi = tmp;
            tmp = fLo;
            fLo = fHi;
            fHi = tmp;
        }
    }

    /**
     * @return the number of evalutations.
     */
    public int getMaxEvaluations() {
        return evaluations.getMaximalCount();
    }

    /**
     * @return the number of evalutations.
     */
    public int getEvaluations() {
        return evaluations.getCount();
    }

    /**
     * @return the lower bound of the bracket.
     * @see #getFLo()
     */
    public double getLo() {
        return lo;
    }

    /**
     * Get function value at {@link #getLo()}.
     * @return function value at {@link #getLo()}
     */
    public double getFLo() {
        return fLo;
    }

    /**
     * @return the higher bound of the bracket.
     * @see #getFHi()
     */
    public double getHi() {
        return hi;
    }

    /**
     * Get function value at {@link #getHi()}.
     * @return function value at {@link #getHi()}
     */
    public double getFHi() {
        return fHi;
    }

    /**
     * @return a point in the middle of the bracket.
     * @see #getFMid()
     */
    public double getMid() {
        return mid;
    }

    /**
     * Get function value at {@link #getMid()}.
     * @return function value at {@link #getMid()}
     */
    public double getFMid() {
        return fMid;
    }

    /**
     * @param f Function.
     * @param x Argument.
     * @return {@code f(x)}
     * @throws TooManyEvaluationsException if the maximal number of evaluations is
     * exceeded.
     */
    private double eval(UnivariateFunction f, double x) {
        try {
            evaluations.increment();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return f.value(x);
    }
}
