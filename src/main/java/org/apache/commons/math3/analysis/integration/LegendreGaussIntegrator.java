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
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://mathworld.wolfram.com/Legendre-GaussQuadrature.html">
 * Legendre-Gauss</a> quadrature formula.
 * <p>
 * Legendre-Gauss integrators are efficient integrators that can
 * accurately integrate functions with few function evaluations. A
 * Legendre-Gauss integrator using an n-points quadrature formula can
 * integrate 2n-1 degree polynomials exactly.
 * </p>
 * <p>
 * These integrators evaluate the function on n carefully chosen
 * abscissas in each step interval (mapped to the canonical [-1,1] interval).
 * The evaluation abscissas are not evenly spaced and none of them are
 * at the interval endpoints. This implies the function integrated can be
 * undefined at integration interval endpoints.
 * </p>
 * <p>
 * The evaluation abscissas x<sub>i</sub> are the roots of the degree n
 * Legendre polynomial. The weights a<sub>i</sub> of the quadrature formula
 * integrals from -1 to +1 &int; Li<sup>2</sup> where Li (x) =
 * &prod; (x-x<sub>k</sub>)/(x<sub>i</sub>-x<sub>k</sub>) for k != i.
 * </p>
 * <p>
 * @since 1.2
 * @deprecated As of 3.1 (to be removed in 4.0). Please use
 * {@link IterativeLegendreGaussIntegrator} instead.
 */
@Deprecated
public class LegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut101024 = false, _mut101025 = false, _mut101026 = false, _mut101027 = false, _mut101028 = false, _mut101029 = false, _mut101030 = false, _mut101031 = false, _mut101032 = false, _mut101033 = false, _mut101034 = false, _mut101035 = false, _mut101036 = false, _mut101037 = false, _mut101038 = false, _mut101039 = false, _mut101040 = false, _mut101041 = false, _mut101042 = false, _mut101043 = false, _mut101044 = false, _mut101045 = false, _mut101046 = false, _mut101047 = false, _mut101048 = false, _mut101049 = false, _mut101050 = false, _mut101051 = false, _mut101052 = false, _mut101053 = false, _mut101054 = false, _mut101055 = false, _mut101056 = false, _mut101057 = false, _mut101058 = false, _mut101059 = false, _mut101060 = false, _mut101061 = false, _mut101062 = false, _mut101063 = false, _mut101064 = false, _mut101065 = false, _mut101066 = false, _mut101067 = false, _mut101068 = false, _mut101069 = false, _mut101070 = false, _mut101071 = false, _mut101072 = false, _mut101073 = false, _mut101074 = false, _mut101075 = false, _mut101076 = false, _mut101077 = false, _mut101078 = false, _mut101079 = false, _mut101080 = false, _mut101081 = false, _mut101082 = false, _mut101083 = false, _mut101084 = false, _mut101085 = false, _mut101086 = false, _mut101087 = false, _mut101088 = false, _mut101089 = false, _mut101090 = false, _mut101091 = false, _mut101092 = false, _mut101093 = false, _mut101094 = false, _mut101095 = false, _mut101096 = false, _mut101097 = false, _mut101098 = false, _mut101099 = false, _mut101100 = false, _mut101101 = false, _mut101102 = false, _mut101103 = false, _mut101104 = false, _mut101105 = false, _mut101106 = false, _mut101107 = false, _mut101108 = false, _mut101109 = false, _mut101110 = false, _mut101111 = false, _mut101112 = false, _mut101113 = false, _mut101114 = false, _mut101115 = false, _mut101116 = false, _mut101117 = false, _mut101118 = false, _mut101119 = false, _mut101120 = false, _mut101121 = false, _mut101122 = false, _mut101123 = false, _mut101124 = false, _mut101125 = false, _mut101126 = false, _mut101127 = false, _mut101128 = false, _mut101129 = false, _mut101130 = false, _mut101131 = false, _mut101132 = false, _mut101133 = false, _mut101134 = false, _mut101135 = false, _mut101136 = false, _mut101137 = false, _mut101138 = false, _mut101139 = false, _mut101140 = false, _mut101141 = false, _mut101142 = false, _mut101143 = false, _mut101144 = false, _mut101145 = false, _mut101146 = false, _mut101147 = false, _mut101148 = false, _mut101149 = false, _mut101150 = false, _mut101151 = false, _mut101152 = false, _mut101153 = false, _mut101154 = false, _mut101155 = false, _mut101156 = false, _mut101157 = false, _mut101158 = false, _mut101159 = false, _mut101160 = false, _mut101161 = false, _mut101162 = false, _mut101163 = false, _mut101164 = false, _mut101165 = false, _mut101166 = false, _mut101167 = false, _mut101168 = false, _mut101169 = false, _mut101170 = false, _mut101171 = false, _mut101172 = false, _mut101173 = false, _mut101174 = false, _mut101175 = false, _mut101176 = false, _mut101177 = false, _mut101178 = false, _mut101179 = false, _mut101180 = false, _mut101181 = false, _mut101182 = false, _mut101183 = false, _mut101184 = false, _mut101185 = false, _mut101186 = false, _mut101187 = false, _mut101188 = false, _mut101189 = false, _mut101190 = false, _mut101191 = false, _mut101192 = false, _mut101193 = false, _mut101194 = false, _mut101195 = false, _mut101196 = false, _mut101197 = false, _mut101198 = false, _mut101199 = false, _mut101200 = false, _mut101201 = false, _mut101202 = false, _mut101203 = false, _mut101204 = false, _mut101205 = false, _mut101206 = false, _mut101207 = false, _mut101208 = false, _mut101209 = false, _mut101210 = false, _mut101211 = false, _mut101212 = false, _mut101213 = false, _mut101214 = false, _mut101215 = false, _mut101216 = false, _mut101217 = false, _mut101218 = false, _mut101219 = false, _mut101220 = false, _mut101221 = false, _mut101222 = false, _mut101223 = false, _mut101224 = false, _mut101225 = false, _mut101226 = false, _mut101227 = false, _mut101228 = false, _mut101229 = false, _mut101230 = false, _mut101231 = false, _mut101232 = false, _mut101233 = false, _mut101234 = false, _mut101235 = false, _mut101236 = false, _mut101237 = false, _mut101238 = false, _mut101239 = false, _mut101240 = false, _mut101241 = false, _mut101242 = false, _mut101243 = false, _mut101244 = false, _mut101245 = false, _mut101246 = false, _mut101247 = false, _mut101248 = false, _mut101249 = false, _mut101250 = false, _mut101251 = false, _mut101252 = false, _mut101253 = false, _mut101254 = false, _mut101255 = false, _mut101256 = false, _mut101257 = false, _mut101258 = false, _mut101259 = false, _mut101260 = false, _mut101261 = false, _mut101262 = false, _mut101263 = false, _mut101264 = false, _mut101265 = false, _mut101266 = false, _mut101267 = false, _mut101268 = false, _mut101269 = false, _mut101270 = false, _mut101271 = false, _mut101272 = false, _mut101273 = false, _mut101274 = false, _mut101275 = false, _mut101276 = false, _mut101277 = false, _mut101278 = false, _mut101279 = false, _mut101280 = false, _mut101281 = false, _mut101282 = false, _mut101283 = false, _mut101284 = false, _mut101285 = false, _mut101286 = false, _mut101287 = false, _mut101288 = false, _mut101289 = false, _mut101290 = false, _mut101291 = false, _mut101292 = false, _mut101293 = false, _mut101294 = false, _mut101295 = false, _mut101296 = false, _mut101297 = false, _mut101298 = false, _mut101299 = false, _mut101300 = false, _mut101301 = false, _mut101302 = false, _mut101303 = false, _mut101304 = false, _mut101305 = false, _mut101306 = false, _mut101307 = false, _mut101308 = false, _mut101309 = false, _mut101310 = false, _mut101311 = false, _mut101312 = false, _mut101313 = false, _mut101314 = false, _mut101315 = false, _mut101316 = false, _mut101317 = false, _mut101318 = false, _mut101319 = false, _mut101320 = false, _mut101321 = false, _mut101322 = false, _mut101323 = false, _mut101324 = false, _mut101325 = false, _mut101326 = false, _mut101327 = false, _mut101328 = false;

    /**
     * Abscissas for the 2 points method.
     */
    private static final double[] ABSCISSAS_2 = { AOR_divide(-1.0, FastMath.sqrt(3.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101024, _mut101025, _mut101026, _mut101027), AOR_divide(1.0, FastMath.sqrt(3.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101028, _mut101029, _mut101030, _mut101031) };

    /**
     * Weights for the 2 points method.
     */
    private static final double[] WEIGHTS_2 = { 1.0, 1.0 };

    /**
     * Abscissas for the 3 points method.
     */
    private static final double[] ABSCISSAS_3 = { -FastMath.sqrt(0.6), 0.0, FastMath.sqrt(0.6) };

    /**
     * Weights for the 3 points method.
     */
    private static final double[] WEIGHTS_3 = { AOR_divide(5.0, 9.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101032, _mut101033, _mut101034, _mut101035), AOR_divide(8.0, 9.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101036, _mut101037, _mut101038, _mut101039), AOR_divide(5.0, 9.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101040, _mut101041, _mut101042, _mut101043) };

    /**
     * Abscissas for the 4 points method.
     */
    private static final double[] ABSCISSAS_4 = { -FastMath.sqrt(AOR_divide((AOR_plus(15.0, AOR_multiply(2.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101044, _mut101045, _mut101046, _mut101047), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101048, _mut101049, _mut101050, _mut101051)), 35.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101052, _mut101053, _mut101054, _mut101055)), -FastMath.sqrt(AOR_divide((AOR_minus(15.0, AOR_multiply(2.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101056, _mut101057, _mut101058, _mut101059), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101060, _mut101061, _mut101062, _mut101063)), 35.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101064, _mut101065, _mut101066, _mut101067)), FastMath.sqrt(AOR_divide((AOR_minus(15.0, AOR_multiply(2.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101068, _mut101069, _mut101070, _mut101071), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101072, _mut101073, _mut101074, _mut101075)), 35.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101076, _mut101077, _mut101078, _mut101079)), FastMath.sqrt(AOR_divide((AOR_plus(15.0, AOR_multiply(2.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101080, _mut101081, _mut101082, _mut101083), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101084, _mut101085, _mut101086, _mut101087)), 35.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101088, _mut101089, _mut101090, _mut101091)) };

    /**
     * Weights for the 4 points method.
     */
    private static final double[] WEIGHTS_4 = { AOR_divide((AOR_minus(90.0, AOR_multiply(5.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101092, _mut101093, _mut101094, _mut101095), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101096, _mut101097, _mut101098, _mut101099)), 180.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101100, _mut101101, _mut101102, _mut101103), AOR_divide((AOR_plus(90.0, AOR_multiply(5.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101104, _mut101105, _mut101106, _mut101107), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101108, _mut101109, _mut101110, _mut101111)), 180.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101112, _mut101113, _mut101114, _mut101115), AOR_divide((AOR_plus(90.0, AOR_multiply(5.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101116, _mut101117, _mut101118, _mut101119), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101120, _mut101121, _mut101122, _mut101123)), 180.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101124, _mut101125, _mut101126, _mut101127), AOR_divide((AOR_minus(90.0, AOR_multiply(5.0, FastMath.sqrt(30.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101128, _mut101129, _mut101130, _mut101131), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101132, _mut101133, _mut101134, _mut101135)), 180.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101136, _mut101137, _mut101138, _mut101139) };

    /**
     * Abscissas for the 5 points method.
     */
    private static final double[] ABSCISSAS_5 = { -FastMath.sqrt(AOR_divide((AOR_plus(35.0, AOR_multiply(2.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101140, _mut101141, _mut101142, _mut101143), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101144, _mut101145, _mut101146, _mut101147)), 63.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101148, _mut101149, _mut101150, _mut101151)), -FastMath.sqrt(AOR_divide((AOR_minus(35.0, AOR_multiply(2.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101152, _mut101153, _mut101154, _mut101155), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101156, _mut101157, _mut101158, _mut101159)), 63.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101160, _mut101161, _mut101162, _mut101163)), 0.0, FastMath.sqrt(AOR_divide((AOR_minus(35.0, AOR_multiply(2.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101164, _mut101165, _mut101166, _mut101167), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101168, _mut101169, _mut101170, _mut101171)), 63.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101172, _mut101173, _mut101174, _mut101175)), FastMath.sqrt(AOR_divide((AOR_plus(35.0, AOR_multiply(2.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101176, _mut101177, _mut101178, _mut101179), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101180, _mut101181, _mut101182, _mut101183)), 63.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101184, _mut101185, _mut101186, _mut101187)) };

    /**
     * Weights for the 5 points method.
     */
    private static final double[] WEIGHTS_5 = { AOR_divide((AOR_minus(322.0, AOR_multiply(13.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101188, _mut101189, _mut101190, _mut101191), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101192, _mut101193, _mut101194, _mut101195)), 900.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101196, _mut101197, _mut101198, _mut101199), AOR_divide((AOR_plus(322.0, AOR_multiply(13.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101200, _mut101201, _mut101202, _mut101203), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101204, _mut101205, _mut101206, _mut101207)), 900.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101208, _mut101209, _mut101210, _mut101211), AOR_divide(128.0, 225.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101212, _mut101213, _mut101214, _mut101215), AOR_divide((AOR_plus(322.0, AOR_multiply(13.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101216, _mut101217, _mut101218, _mut101219), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101220, _mut101221, _mut101222, _mut101223)), 900.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101224, _mut101225, _mut101226, _mut101227), AOR_divide((AOR_minus(322.0, AOR_multiply(13.0, FastMath.sqrt(70.0), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101228, _mut101229, _mut101230, _mut101231), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101232, _mut101233, _mut101234, _mut101235)), 900.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.value_162", _mut101236, _mut101237, _mut101238, _mut101239) };

    /**
     * Abscissas for the current method.
     */
    private final double[] abscissas;

    /**
     * Weights for the current method.
     */
    private final double[] weights;

    /**
     * Build a Legendre-Gauss integrator with given accuracies and iterations counts.
     * @param n number of points desired (must be between 2 and 5 inclusive)
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * @exception MathIllegalArgumentException if number of points is out of [2; 5]
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     */
    public LegendreGaussIntegrator(final int n, final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws MathIllegalArgumentException, NotStrictlyPositiveException, NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        switch(n) {
            case 2:
                abscissas = ABSCISSAS_2;
                weights = WEIGHTS_2;
                break;
            case 3:
                abscissas = ABSCISSAS_3;
                weights = WEIGHTS_3;
                break;
            case 4:
                abscissas = ABSCISSAS_4;
                weights = WEIGHTS_4;
                break;
            case 5:
                abscissas = ABSCISSAS_5;
                weights = WEIGHTS_5;
                break;
            default:
                throw new MathIllegalArgumentException(LocalizedFormats.N_POINTS_GAUSS_LEGENDRE_INTEGRATOR_NOT_SUPPORTED, n, 2, 5);
        }
    }

    /**
     * Build a Legendre-Gauss integrator with given accuracies.
     * @param n number of points desired (must be between 2 and 5 inclusive)
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @exception MathIllegalArgumentException if number of points is out of [2; 5]
     */
    public LegendreGaussIntegrator(final int n, final double relativeAccuracy, final double absoluteAccuracy) throws MathIllegalArgumentException {
        this(n, relativeAccuracy, absoluteAccuracy, DEFAULT_MIN_ITERATIONS_COUNT, DEFAULT_MAX_ITERATIONS_COUNT);
    }

    /**
     * Build a Legendre-Gauss integrator with given iteration counts.
     * @param n number of points desired (must be between 2 and 5 inclusive)
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * @exception MathIllegalArgumentException if number of points is out of [2; 5]
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     */
    public LegendreGaussIntegrator(final int n, final int minimalIterationCount, final int maximalIterationCount) throws MathIllegalArgumentException {
        this(n, DEFAULT_RELATIVE_ACCURACY, DEFAULT_ABSOLUTE_ACCURACY, minimalIterationCount, maximalIterationCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203");
        // compute first estimate with a single step
        double oldt = stage(1);
        int n = 2;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203");
            // improve integral with a larger number of steps
            final double t = stage(n);
            // estimate error
            final double delta = FastMath.abs(AOR_minus(t, oldt, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101240, _mut101241, _mut101242, _mut101243));
            final double limit = FastMath.max(getAbsoluteAccuracy(), AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(oldt), FastMath.abs(t), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101244, _mut101245, _mut101246, _mut101247)), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101248, _mut101249, _mut101250, _mut101251), 0.5, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101252, _mut101253, _mut101254, _mut101255));
            // check convergence
            if ((_mut101270 ? ((ROR_greater_equals(AOR_plus(getIterations(), 1, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101256, _mut101257, _mut101258, _mut101259), getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101260, _mut101261, _mut101262, _mut101263, _mut101264)) || (ROR_less_equals(delta, limit, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101265, _mut101266, _mut101267, _mut101268, _mut101269))) : ((ROR_greater_equals(AOR_plus(getIterations(), 1, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101256, _mut101257, _mut101258, _mut101259), getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101260, _mut101261, _mut101262, _mut101263, _mut101264)) && (ROR_less_equals(delta, limit, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101265, _mut101266, _mut101267, _mut101268, _mut101269))))) {
                return t;
            }
            // prepare next iteration
            double ratio = FastMath.min(4, FastMath.pow(AOR_divide(delta, limit, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101271, _mut101272, _mut101273, _mut101274), AOR_divide(0.5, abscissas.length, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101275, _mut101276, _mut101277, _mut101278)));
            n = FastMath.max((int) (AOR_multiply(ratio, n, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101279, _mut101280, _mut101281, _mut101282)), AOR_plus(n, 1, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.doIntegrate_203", _mut101283, _mut101284, _mut101285, _mut101286));
            oldt = t;
            incrementCount();
        }
    }

    /**
     * Compute the n-th stage integral.
     * @param n number of steps
     * @return the value of n-th stage integral
     * @throws TooManyEvaluationsException if the maximum number of evaluations
     * is exceeded.
     */
    private double stage(final int n) throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244");
        // set up the step for the current stage
        final double step = AOR_divide((AOR_minus(getMax(), getMin(), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101287, _mut101288, _mut101289, _mut101290)), n, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101291, _mut101292, _mut101293, _mut101294);
        final double halfStep = AOR_divide(step, 2.0, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101295, _mut101296, _mut101297, _mut101298);
        // integrate over all elementary steps
        double midPoint = AOR_plus(getMin(), halfStep, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101299, _mut101300, _mut101301, _mut101302);
        double sum = 0.0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101320, _mut101321, _mut101322, _mut101323, _mut101324); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244");
            for (int j = 0; ROR_less(j, abscissas.length, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101315, _mut101316, _mut101317, _mut101318, _mut101319); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244");
                sum += AOR_multiply(weights[j], computeObjectiveValue(AOR_plus(midPoint, AOR_multiply(halfStep, abscissas[j], "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101303, _mut101304, _mut101305, _mut101306), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101307, _mut101308, _mut101309, _mut101310)), "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101311, _mut101312, _mut101313, _mut101314);
            }
            midPoint += step;
        }
        return AOR_multiply(halfStep, sum, "org.apache.commons.math3.analysis.integration.LegendreGaussIntegrator.stage_244", _mut101325, _mut101326, _mut101327, _mut101328);
    }
}
