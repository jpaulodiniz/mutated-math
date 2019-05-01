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

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class MidpointStepInterpolator extends RungeKuttaStepInterpolator {

    @Conditional
    public static boolean _mut18956 = false, _mut18957 = false, _mut18958 = false, _mut18959 = false, _mut18960 = false, _mut18961 = false, _mut18962 = false, _mut18963 = false, _mut18964 = false, _mut18965 = false, _mut18966 = false, _mut18967 = false, _mut18968 = false, _mut18969 = false, _mut18970 = false, _mut18971 = false, _mut18972 = false, _mut18973 = false, _mut18974 = false, _mut18975 = false, _mut18976 = false, _mut18977 = false, _mut18978 = false, _mut18979 = false, _mut18980 = false, _mut18981 = false, _mut18982 = false, _mut18983 = false, _mut18984 = false, _mut18985 = false, _mut18986 = false, _mut18987 = false, _mut18988 = false, _mut18989 = false, _mut18990 = false, _mut18991 = false, _mut18992 = false, _mut18993 = false, _mut18994 = false, _mut18995 = false, _mut18996 = false, _mut18997 = false, _mut18998 = false, _mut18999 = false, _mut19000 = false, _mut19001 = false, _mut19002 = false, _mut19003 = false, _mut19004 = false, _mut19005 = false, _mut19006 = false, _mut19007 = false, _mut19008 = false, _mut19009 = false, _mut19010 = false, _mut19011 = false, _mut19012 = false, _mut19013 = false, _mut19014 = false, _mut19015 = false, _mut19016 = false, _mut19017 = false, _mut19018 = false, _mut19019 = false, _mut19020 = false, _mut19021 = false, _mut19022 = false, _mut19023 = false, _mut19024 = false, _mut19025 = false, _mut19026 = false, _mut19027 = false, _mut19028 = false, _mut19029 = false, _mut19030 = false, _mut19031 = false, _mut19032 = false, _mut19033 = false, _mut19034 = false, _mut19035 = false, _mut19036 = false, _mut19037 = false, _mut19038 = false, _mut19039 = false, _mut19040 = false, _mut19041 = false, _mut19042 = false, _mut19043 = false, _mut19044 = false, _mut19045 = false, _mut19046 = false, _mut19047 = false, _mut19048 = false, _mut19049 = false, _mut19050 = false, _mut19051 = false, _mut19052 = false, _mut19053 = false, _mut19054 = false, _mut19055 = false, _mut19056 = false, _mut19057 = false, _mut19058 = false, _mut19059 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 20111120L;

    // the public modifier here is needed for serialization
    public MidpointStepInterpolator() {
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    MidpointStepInterpolator(final MidpointStepInterpolator interpolator) {
        super(interpolator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new MidpointStepInterpolator(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87");
        final double coeffDot2 = AOR_multiply(2, theta, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18956, _mut18957, _mut18958, _mut18959);
        final double coeffDot1 = AOR_minus(1, coeffDot2, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18960, _mut18961, _mut18962, _mut18963);
        if ((_mut18969 ? ((previousState != null) || (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18964, _mut18965, _mut18966, _mut18967, _mut18968))) : ((previousState != null) && (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18964, _mut18965, _mut18966, _mut18967, _mut18968))))) {
            final double coeff1 = AOR_multiply(theta, oneMinusThetaH, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19015, _mut19016, _mut19017, _mut19018);
            final double coeff2 = AOR_multiply(AOR_multiply(theta, theta, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19019, _mut19020, _mut19021, _mut19022), h, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19023, _mut19024, _mut19025, _mut19026);
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19055, _mut19056, _mut19057, _mut19058, _mut19059); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87");
                final double yDot1 = yDotK[0][i];
                final double yDot2 = yDotK[1][i];
                interpolatedState[i] = AOR_plus(AOR_plus(previousState[i], AOR_multiply(coeff1, yDot1, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19027, _mut19028, _mut19029, _mut19030), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19031, _mut19032, _mut19033, _mut19034), AOR_multiply(coeff2, yDot2, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19035, _mut19036, _mut19037, _mut19038), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19039, _mut19040, _mut19041, _mut19042);
                interpolatedDerivatives[i] = AOR_plus(AOR_multiply(coeffDot1, yDot1, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19043, _mut19044, _mut19045, _mut19046), AOR_multiply(coeffDot2, yDot2, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19047, _mut19048, _mut19049, _mut19050), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19051, _mut19052, _mut19053, _mut19054);
            }
        } else {
            final double coeff1 = AOR_multiply(oneMinusThetaH, theta, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18970, _mut18971, _mut18972, _mut18973);
            final double coeff2 = AOR_multiply(oneMinusThetaH, (AOR_plus(1.0, theta, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18974, _mut18975, _mut18976, _mut18977)), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18978, _mut18979, _mut18980, _mut18981);
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19010, _mut19011, _mut19012, _mut19013, _mut19014); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87");
                final double yDot1 = yDotK[0][i];
                final double yDot2 = yDotK[1][i];
                interpolatedState[i] = AOR_minus(AOR_plus(currentState[i], AOR_multiply(coeff1, yDot1, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18982, _mut18983, _mut18984, _mut18985), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18986, _mut18987, _mut18988, _mut18989), AOR_multiply(coeff2, yDot2, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18990, _mut18991, _mut18992, _mut18993), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18994, _mut18995, _mut18996, _mut18997);
                interpolatedDerivatives[i] = AOR_plus(AOR_multiply(coeffDot1, yDot1, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut18998, _mut18999, _mut19000, _mut19001), AOR_multiply(coeffDot2, yDot2, "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19002, _mut19003, _mut19004, _mut19005), "org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator.computeInterpolatedStateAndDerivatives_87", _mut19006, _mut19007, _mut19008, _mut19009);
            }
        }
    }
}
