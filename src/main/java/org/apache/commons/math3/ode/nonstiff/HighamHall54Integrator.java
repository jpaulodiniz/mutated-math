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

public class HighamHall54Integrator extends EmbeddedRungeKuttaIntegrator {

    @Conditional
    public static boolean _mut12791 = false, _mut12792 = false, _mut12793 = false, _mut12794 = false, _mut12795 = false, _mut12796 = false, _mut12797 = false, _mut12798 = false, _mut12799 = false, _mut12800 = false, _mut12801 = false, _mut12802 = false, _mut12803 = false, _mut12804 = false, _mut12805 = false, _mut12806 = false, _mut12807 = false, _mut12808 = false, _mut12809 = false, _mut12810 = false, _mut12811 = false, _mut12812 = false, _mut12813 = false, _mut12814 = false, _mut12815 = false, _mut12816 = false, _mut12817 = false, _mut12818 = false, _mut12819 = false, _mut12820 = false, _mut12821 = false, _mut12822 = false, _mut12823 = false, _mut12824 = false, _mut12825 = false, _mut12826 = false, _mut12827 = false, _mut12828 = false, _mut12829 = false, _mut12830 = false, _mut12831 = false, _mut12832 = false, _mut12833 = false, _mut12834 = false, _mut12835 = false, _mut12836 = false, _mut12837 = false, _mut12838 = false, _mut12839 = false, _mut12840 = false, _mut12841 = false, _mut12842 = false, _mut12843 = false, _mut12844 = false, _mut12845 = false, _mut12846 = false, _mut12847 = false, _mut12848 = false, _mut12849 = false, _mut12850 = false, _mut12851 = false, _mut12852 = false, _mut12853 = false, _mut12854 = false, _mut12855 = false, _mut12856 = false, _mut12857 = false, _mut12858 = false, _mut12859 = false, _mut12860 = false, _mut12861 = false, _mut12862 = false, _mut12863 = false, _mut12864 = false, _mut12865 = false, _mut12866 = false, _mut12867 = false, _mut12868 = false, _mut12869 = false, _mut12870 = false, _mut12871 = false, _mut12872 = false, _mut12873 = false, _mut12874 = false, _mut12875 = false, _mut12876 = false, _mut12877 = false, _mut12878 = false, _mut12879 = false, _mut12880 = false, _mut12881 = false, _mut12882 = false, _mut12883 = false, _mut12884 = false, _mut12885 = false, _mut12886 = false, _mut12887 = false, _mut12888 = false, _mut12889 = false, _mut12890 = false, _mut12891 = false, _mut12892 = false, _mut12893 = false, _mut12894 = false, _mut12895 = false, _mut12896 = false, _mut12897 = false, _mut12898 = false, _mut12899 = false, _mut12900 = false, _mut12901 = false, _mut12902 = false, _mut12903 = false, _mut12904 = false, _mut12905 = false, _mut12906 = false, _mut12907 = false, _mut12908 = false, _mut12909 = false, _mut12910 = false, _mut12911 = false, _mut12912 = false, _mut12913 = false, _mut12914 = false, _mut12915 = false, _mut12916 = false, _mut12917 = false, _mut12918 = false, _mut12919 = false, _mut12920 = false, _mut12921 = false, _mut12922 = false, _mut12923 = false, _mut12924 = false, _mut12925 = false, _mut12926 = false, _mut12927 = false, _mut12928 = false, _mut12929 = false, _mut12930 = false, _mut12931 = false, _mut12932 = false, _mut12933 = false, _mut12934 = false, _mut12935 = false, _mut12936 = false, _mut12937 = false, _mut12938 = false, _mut12939 = false, _mut12940 = false, _mut12941 = false, _mut12942 = false, _mut12943 = false, _mut12944 = false, _mut12945 = false, _mut12946 = false, _mut12947 = false, _mut12948 = false, _mut12949 = false, _mut12950 = false, _mut12951 = false, _mut12952 = false, _mut12953 = false, _mut12954 = false, _mut12955 = false, _mut12956 = false, _mut12957 = false, _mut12958 = false, _mut12959 = false, _mut12960 = false, _mut12961 = false, _mut12962 = false, _mut12963 = false, _mut12964 = false, _mut12965 = false, _mut12966 = false, _mut12967 = false, _mut12968 = false, _mut12969 = false, _mut12970 = false, _mut12971 = false, _mut12972 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Higham-Hall 5(4)";

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(2.0, 9.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12791, _mut12792, _mut12793, _mut12794), AOR_divide(1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12795, _mut12796, _mut12797, _mut12798), AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12799, _mut12800, _mut12801, _mut12802), AOR_divide(3.0, 5.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12803, _mut12804, _mut12805, _mut12806), 1.0, 1.0 };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(2.0, 9.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12807, _mut12808, _mut12809, _mut12810) }, { AOR_divide(1.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12811, _mut12812, _mut12813, _mut12814), AOR_divide(1.0, 4.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12815, _mut12816, _mut12817, _mut12818) }, { AOR_divide(1.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12819, _mut12820, _mut12821, _mut12822), 0.0, AOR_divide(3.0, 8.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12823, _mut12824, _mut12825, _mut12826) }, { AOR_divide(91.0, 500.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12827, _mut12828, _mut12829, _mut12830), AOR_divide(-27.0, 100.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12831, _mut12832, _mut12833, _mut12834), AOR_divide(78.0, 125.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12835, _mut12836, _mut12837, _mut12838), AOR_divide(8.0, 125.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12839, _mut12840, _mut12841, _mut12842) }, { AOR_divide(-11.0, 20.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12843, _mut12844, _mut12845, _mut12846), AOR_divide(27.0, 20.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12847, _mut12848, _mut12849, _mut12850), AOR_divide(12.0, 5.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12851, _mut12852, _mut12853, _mut12854), AOR_divide(-36.0, 5.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12855, _mut12856, _mut12857, _mut12858), 5.0 }, { AOR_divide(1.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12859, _mut12860, _mut12861, _mut12862), 0.0, AOR_divide(27.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12863, _mut12864, _mut12865, _mut12866), AOR_divide(-4.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12867, _mut12868, _mut12869, _mut12870), AOR_divide(125.0, 96.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12871, _mut12872, _mut12873, _mut12874), AOR_divide(5.0, 48.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12875, _mut12876, _mut12877, _mut12878) } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { AOR_divide(1.0, 12.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12879, _mut12880, _mut12881, _mut12882), 0.0, AOR_divide(27.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12883, _mut12884, _mut12885, _mut12886), AOR_divide(-4.0, 3.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12887, _mut12888, _mut12889, _mut12890), AOR_divide(125.0, 96.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12891, _mut12892, _mut12893, _mut12894), AOR_divide(5.0, 48.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12895, _mut12896, _mut12897, _mut12898), 0.0 };

    /**
     * Error weights Butcher array.
     */
    private static final double[] STATIC_E = { AOR_divide(-1.0, 20.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12899, _mut12900, _mut12901, _mut12902), 0.0, AOR_divide(81.0, 160.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12903, _mut12904, _mut12905, _mut12906), AOR_divide(-6.0, 5.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12907, _mut12908, _mut12909, _mut12910), AOR_divide(25.0, 32.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12911, _mut12912, _mut12913, _mut12914), AOR_divide(1.0, 16.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12915, _mut12916, _mut12917, _mut12918), AOR_divide(-1.0, 10.0, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.computeInterpolatedStateAndDerivatives_135", _mut12919, _mut12920, _mut12921, _mut12922) };

    /**
     * Simple constructor.
     * Build a fifth order Higham and Hall integrator with the given step bounds
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public HighamHall54Integrator(final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(METHOD_NAME, false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    /**
     * Simple constructor.
     * Build a fifth order Higham and Hall integrator with the given step bounds
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public HighamHall54Integrator(final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(METHOD_NAME, false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109");
        double error = 0;
        for (int j = 0; ROR_less(j, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12964, _mut12965, _mut12966, _mut12967, _mut12968); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109");
            double errSum = AOR_multiply(STATIC_E[0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12923, _mut12924, _mut12925, _mut12926);
            for (int l = 1; ROR_less(l, STATIC_E.length, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12931, _mut12932, _mut12933, _mut12934, _mut12935); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109");
                errSum += AOR_multiply(STATIC_E[l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12927, _mut12928, _mut12929, _mut12930);
            }
            final double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
            final double tol = (vecAbsoluteTolerance == null) ? (AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, yScale, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12944, _mut12945, _mut12946, _mut12947), "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12948, _mut12949, _mut12950, _mut12951)) : (AOR_plus(vecAbsoluteTolerance[j], AOR_multiply(vecRelativeTolerance[j], yScale, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12936, _mut12937, _mut12938, _mut12939), "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12940, _mut12941, _mut12942, _mut12943));
            final double ratio = AOR_divide(AOR_multiply(h, errSum, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12952, _mut12953, _mut12954, _mut12955), tol, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12956, _mut12957, _mut12958, _mut12959);
            error += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12960, _mut12961, _mut12962, _mut12963);
        }
        return FastMath.sqrt(AOR_divide(error, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator.estimateError_109", _mut12969, _mut12970, _mut12971, _mut12972));
    }
}
