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
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class DormandPrince54Integrator extends EmbeddedRungeKuttaIntegrator {

    @Conditional
    public static boolean _mut21060 = false, _mut21061 = false, _mut21062 = false, _mut21063 = false, _mut21064 = false, _mut21065 = false, _mut21066 = false, _mut21067 = false, _mut21068 = false, _mut21069 = false, _mut21070 = false, _mut21071 = false, _mut21072 = false, _mut21073 = false, _mut21074 = false, _mut21075 = false, _mut21076 = false, _mut21077 = false, _mut21078 = false, _mut21079 = false, _mut21080 = false, _mut21081 = false, _mut21082 = false, _mut21083 = false, _mut21084 = false, _mut21085 = false, _mut21086 = false, _mut21087 = false, _mut21088 = false, _mut21089 = false, _mut21090 = false, _mut21091 = false, _mut21092 = false, _mut21093 = false, _mut21094 = false, _mut21095 = false, _mut21096 = false, _mut21097 = false, _mut21098 = false, _mut21099 = false, _mut21100 = false, _mut21101 = false, _mut21102 = false, _mut21103 = false, _mut21104 = false, _mut21105 = false, _mut21106 = false, _mut21107 = false, _mut21108 = false, _mut21109 = false, _mut21110 = false, _mut21111 = false, _mut21112 = false, _mut21113 = false, _mut21114 = false, _mut21115 = false, _mut21116 = false, _mut21117 = false, _mut21118 = false, _mut21119 = false, _mut21120 = false, _mut21121 = false, _mut21122 = false, _mut21123 = false, _mut21124 = false, _mut21125 = false, _mut21126 = false, _mut21127 = false, _mut21128 = false, _mut21129 = false, _mut21130 = false, _mut21131 = false, _mut21132 = false, _mut21133 = false, _mut21134 = false, _mut21135 = false, _mut21136 = false, _mut21137 = false, _mut21138 = false, _mut21139 = false, _mut21140 = false, _mut21141 = false, _mut21142 = false, _mut21143 = false, _mut21144 = false, _mut21145 = false, _mut21146 = false, _mut21147 = false, _mut21148 = false, _mut21149 = false, _mut21150 = false, _mut21151 = false, _mut21152 = false, _mut21153 = false, _mut21154 = false, _mut21155 = false, _mut21156 = false, _mut21157 = false, _mut21158 = false, _mut21159 = false, _mut21160 = false, _mut21161 = false, _mut21162 = false, _mut21163 = false, _mut21164 = false, _mut21165 = false, _mut21166 = false, _mut21167 = false, _mut21168 = false, _mut21169 = false, _mut21170 = false, _mut21171 = false, _mut21172 = false, _mut21173 = false, _mut21174 = false, _mut21175 = false, _mut21176 = false, _mut21177 = false, _mut21178 = false, _mut21179 = false, _mut21180 = false, _mut21181 = false, _mut21182 = false, _mut21183 = false, _mut21184 = false, _mut21185 = false, _mut21186 = false, _mut21187 = false, _mut21188 = false, _mut21189 = false, _mut21190 = false, _mut21191 = false, _mut21192 = false, _mut21193 = false, _mut21194 = false, _mut21195 = false, _mut21196 = false, _mut21197 = false, _mut21198 = false, _mut21199 = false, _mut21200 = false, _mut21201 = false, _mut21202 = false, _mut21203 = false, _mut21204 = false, _mut21205 = false, _mut21206 = false, _mut21207 = false, _mut21208 = false, _mut21209 = false, _mut21210 = false, _mut21211 = false, _mut21212 = false, _mut21213 = false, _mut21214 = false, _mut21215 = false, _mut21216 = false, _mut21217 = false, _mut21218 = false, _mut21219 = false, _mut21220 = false, _mut21221 = false, _mut21222 = false, _mut21223 = false, _mut21224 = false, _mut21225 = false, _mut21226 = false, _mut21227 = false, _mut21228 = false, _mut21229 = false, _mut21230 = false, _mut21231 = false, _mut21232 = false, _mut21233 = false, _mut21234 = false, _mut21235 = false, _mut21236 = false, _mut21237 = false, _mut21238 = false, _mut21239 = false, _mut21240 = false, _mut21241 = false, _mut21242 = false, _mut21243 = false, _mut21244 = false, _mut21245 = false, _mut21246 = false, _mut21247 = false, _mut21248 = false, _mut21249 = false, _mut21250 = false, _mut21251 = false, _mut21252 = false, _mut21253 = false, _mut21254 = false, _mut21255 = false, _mut21256 = false, _mut21257 = false, _mut21258 = false, _mut21259 = false, _mut21260 = false, _mut21261 = false, _mut21262 = false, _mut21263 = false, _mut21264 = false, _mut21265 = false, _mut21266 = false, _mut21267 = false, _mut21268 = false, _mut21269 = false, _mut21270 = false, _mut21271 = false, _mut21272 = false, _mut21273 = false, _mut21274 = false, _mut21275 = false, _mut21276 = false, _mut21277 = false, _mut21278 = false, _mut21279 = false, _mut21280 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Dormand-Prince 5(4)";

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(1.0, 5.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21060, _mut21061, _mut21062, _mut21063), AOR_divide(3.0, 10.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21064, _mut21065, _mut21066, _mut21067), AOR_divide(4.0, 5.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21068, _mut21069, _mut21070, _mut21071), AOR_divide(8.0, 9.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21072, _mut21073, _mut21074, _mut21075), 1.0, 1.0 };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(1.0, 5.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21076, _mut21077, _mut21078, _mut21079) }, { AOR_divide(3.0, 40.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21080, _mut21081, _mut21082, _mut21083), AOR_divide(9.0, 40.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21084, _mut21085, _mut21086, _mut21087) }, { AOR_divide(44.0, 45.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21088, _mut21089, _mut21090, _mut21091), AOR_divide(-56.0, 15.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21092, _mut21093, _mut21094, _mut21095), AOR_divide(32.0, 9.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21096, _mut21097, _mut21098, _mut21099) }, { AOR_divide(19372.0, 6561.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21100, _mut21101, _mut21102, _mut21103), AOR_divide(-25360.0, 2187.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21104, _mut21105, _mut21106, _mut21107), AOR_divide(64448.0, 6561.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21108, _mut21109, _mut21110, _mut21111), AOR_divide(-212.0, 729.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21112, _mut21113, _mut21114, _mut21115) }, { AOR_divide(9017.0, 3168.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21116, _mut21117, _mut21118, _mut21119), AOR_divide(-355.0, 33.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21120, _mut21121, _mut21122, _mut21123), AOR_divide(46732.0, 5247.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21124, _mut21125, _mut21126, _mut21127), AOR_divide(49.0, 176.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21128, _mut21129, _mut21130, _mut21131), AOR_divide(-5103.0, 18656.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21132, _mut21133, _mut21134, _mut21135) }, { AOR_divide(35.0, 384.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21136, _mut21137, _mut21138, _mut21139), 0.0, AOR_divide(500.0, 1113.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21140, _mut21141, _mut21142, _mut21143), AOR_divide(125.0, 192.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21144, _mut21145, _mut21146, _mut21147), AOR_divide(-2187.0, 6784.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21148, _mut21149, _mut21150, _mut21151), AOR_divide(11.0, 84.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21152, _mut21153, _mut21154, _mut21155) } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { AOR_divide(35.0, 384.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21156, _mut21157, _mut21158, _mut21159), 0.0, AOR_divide(500.0, 1113.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21160, _mut21161, _mut21162, _mut21163), AOR_divide(125.0, 192.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21164, _mut21165, _mut21166, _mut21167), AOR_divide(-2187.0, 6784.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21168, _mut21169, _mut21170, _mut21171), AOR_divide(11.0, 84.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21172, _mut21173, _mut21174, _mut21175), 0.0 };

    /**
     * Error array, element 1.
     */
    private static final double E1 = AOR_divide(71.0, 57600.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21176, _mut21177, _mut21178, _mut21179);

    /**
     * Error array, element 3.
     */
    private static final double E3 = AOR_divide(-71.0, 16695.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21180, _mut21181, _mut21182, _mut21183);

    /**
     * Error array, element 4.
     */
    private static final double E4 = AOR_divide(71.0, 1920.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21184, _mut21185, _mut21186, _mut21187);

    /**
     * Error array, element 5.
     */
    private static final double E5 = AOR_divide(-17253.0, 339200.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21188, _mut21189, _mut21190, _mut21191);

    /**
     * Error array, element 6.
     */
    private static final double E6 = AOR_divide(22.0, 525.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21192, _mut21193, _mut21194, _mut21195);

    /**
     * Error array, element 7.
     */
    private static final double E7 = AOR_divide(-1.0, 40.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.readExternal_479", _mut21196, _mut21197, _mut21198, _mut21199);

    /**
     * Simple constructor.
     * Build a fifth order Dormand-Prince integrator with the given step bounds
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public DormandPrince54Integrator(final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    /**
     * Simple constructor.
     * Build a fifth order Dormand-Prince integrator with the given step bounds
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public DormandPrince54Integrator(final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double estimateError(final double[][] yDotK, final double[] y0, final double[] y1, final double h) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136");
        double error = 0;
        for (int j = 0; ROR_less(j, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21272, _mut21273, _mut21274, _mut21275, _mut21276); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136");
            final double errSum = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(E1, yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21200, _mut21201, _mut21202, _mut21203), AOR_multiply(E3, yDotK[2][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21204, _mut21205, _mut21206, _mut21207), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21208, _mut21209, _mut21210, _mut21211), AOR_multiply(E4, yDotK[3][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21212, _mut21213, _mut21214, _mut21215), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21216, _mut21217, _mut21218, _mut21219), AOR_multiply(E5, yDotK[4][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21220, _mut21221, _mut21222, _mut21223), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21224, _mut21225, _mut21226, _mut21227), AOR_multiply(E6, yDotK[5][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21228, _mut21229, _mut21230, _mut21231), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21232, _mut21233, _mut21234, _mut21235), AOR_multiply(E7, yDotK[6][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21236, _mut21237, _mut21238, _mut21239), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21240, _mut21241, _mut21242, _mut21243);
            final double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
            final double tol = (vecAbsoluteTolerance == null) ? (AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, yScale, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21252, _mut21253, _mut21254, _mut21255), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21256, _mut21257, _mut21258, _mut21259)) : (AOR_plus(vecAbsoluteTolerance[j], AOR_multiply(vecRelativeTolerance[j], yScale, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21244, _mut21245, _mut21246, _mut21247), "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21248, _mut21249, _mut21250, _mut21251));
            final double ratio = AOR_divide(AOR_multiply(h, errSum, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21260, _mut21261, _mut21262, _mut21263), tol, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21264, _mut21265, _mut21266, _mut21267);
            error += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21268, _mut21269, _mut21270, _mut21271);
        }
        return FastMath.sqrt(AOR_divide(error, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator.estimateError_136", _mut21277, _mut21278, _mut21279, _mut21280));
    }
}
