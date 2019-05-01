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
package org.apache.commons.math3.ode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class ContinuousOutputModel implements StepHandler, Serializable {

    @Conditional
    public static boolean _mut11799 = false, _mut11800 = false, _mut11801 = false, _mut11802 = false, _mut11803 = false, _mut11804 = false, _mut11805 = false, _mut11806 = false, _mut11807 = false, _mut11808 = false, _mut11809 = false, _mut11810 = false, _mut11811 = false, _mut11812 = false, _mut11813 = false, _mut11814 = false, _mut11815 = false, _mut11816 = false, _mut11817 = false, _mut11818 = false, _mut11819 = false, _mut11820 = false, _mut11821 = false, _mut11822 = false, _mut11823 = false, _mut11824 = false, _mut11825 = false, _mut11826 = false, _mut11827 = false, _mut11828 = false, _mut11829 = false, _mut11830 = false, _mut11831 = false, _mut11832 = false, _mut11833 = false, _mut11834 = false, _mut11835 = false, _mut11836 = false, _mut11837 = false, _mut11838 = false, _mut11839 = false, _mut11840 = false, _mut11841 = false, _mut11842 = false, _mut11843 = false, _mut11844 = false, _mut11845 = false, _mut11846 = false, _mut11847 = false, _mut11848 = false, _mut11849 = false, _mut11850 = false, _mut11851 = false, _mut11852 = false, _mut11853 = false, _mut11854 = false, _mut11855 = false, _mut11856 = false, _mut11857 = false, _mut11858 = false, _mut11859 = false, _mut11860 = false, _mut11861 = false, _mut11862 = false, _mut11863 = false, _mut11864 = false, _mut11865 = false, _mut11866 = false, _mut11867 = false, _mut11868 = false, _mut11869 = false, _mut11870 = false, _mut11871 = false, _mut11872 = false, _mut11873 = false, _mut11874 = false, _mut11875 = false, _mut11876 = false, _mut11877 = false, _mut11878 = false, _mut11879 = false, _mut11880 = false, _mut11881 = false, _mut11882 = false, _mut11883 = false, _mut11884 = false, _mut11885 = false, _mut11886 = false, _mut11887 = false, _mut11888 = false, _mut11889 = false, _mut11890 = false, _mut11891 = false, _mut11892 = false, _mut11893 = false, _mut11894 = false, _mut11895 = false, _mut11896 = false, _mut11897 = false, _mut11898 = false, _mut11899 = false, _mut11900 = false, _mut11901 = false, _mut11902 = false, _mut11903 = false, _mut11904 = false, _mut11905 = false, _mut11906 = false, _mut11907 = false, _mut11908 = false, _mut11909 = false, _mut11910 = false, _mut11911 = false, _mut11912 = false, _mut11913 = false, _mut11914 = false, _mut11915 = false, _mut11916 = false, _mut11917 = false, _mut11918 = false, _mut11919 = false, _mut11920 = false, _mut11921 = false, _mut11922 = false, _mut11923 = false, _mut11924 = false, _mut11925 = false, _mut11926 = false, _mut11927 = false, _mut11928 = false, _mut11929 = false, _mut11930 = false, _mut11931 = false, _mut11932 = false, _mut11933 = false, _mut11934 = false, _mut11935 = false, _mut11936 = false, _mut11937 = false, _mut11938 = false, _mut11939 = false, _mut11940 = false, _mut11941 = false, _mut11942 = false, _mut11943 = false, _mut11944 = false, _mut11945 = false, _mut11946 = false, _mut11947 = false, _mut11948 = false, _mut11949 = false, _mut11950 = false, _mut11951 = false, _mut11952 = false, _mut11953 = false, _mut11954 = false, _mut11955 = false, _mut11956 = false, _mut11957 = false, _mut11958 = false, _mut11959 = false, _mut11960 = false, _mut11961 = false, _mut11962 = false, _mut11963 = false, _mut11964 = false, _mut11965 = false, _mut11966 = false, _mut11967 = false, _mut11968 = false, _mut11969 = false, _mut11970 = false, _mut11971 = false, _mut11972 = false, _mut11973 = false, _mut11974 = false, _mut11975 = false, _mut11976 = false, _mut11977 = false, _mut11978 = false, _mut11979 = false, _mut11980 = false, _mut11981 = false, _mut11982 = false, _mut11983 = false, _mut11984 = false, _mut11985 = false, _mut11986 = false, _mut11987 = false, _mut11988 = false, _mut11989 = false, _mut11990 = false, _mut11991 = false, _mut11992 = false, _mut11993 = false, _mut11994 = false, _mut11995 = false, _mut11996 = false, _mut11997 = false, _mut11998 = false, _mut11999 = false, _mut12000 = false, _mut12001 = false, _mut12002 = false, _mut12003 = false, _mut12004 = false, _mut12005 = false, _mut12006 = false, _mut12007 = false, _mut12008 = false, _mut12009 = false, _mut12010 = false, _mut12011 = false, _mut12012 = false, _mut12013 = false, _mut12014 = false, _mut12015 = false, _mut12016 = false, _mut12017 = false, _mut12018 = false, _mut12019 = false, _mut12020 = false, _mut12021 = false, _mut12022 = false, _mut12023 = false, _mut12024 = false, _mut12025 = false, _mut12026 = false, _mut12027 = false, _mut12028 = false, _mut12029 = false, _mut12030 = false, _mut12031 = false, _mut12032 = false, _mut12033 = false, _mut12034 = false, _mut12035 = false, _mut12036 = false, _mut12037 = false, _mut12038 = false, _mut12039 = false, _mut12040 = false, _mut12041 = false, _mut12042 = false, _mut12043 = false, _mut12044 = false, _mut12045 = false, _mut12046 = false, _mut12047 = false, _mut12048 = false, _mut12049 = false, _mut12050 = false, _mut12051 = false, _mut12052 = false, _mut12053 = false, _mut12054 = false, _mut12055 = false, _mut12056 = false, _mut12057 = false, _mut12058 = false, _mut12059 = false, _mut12060 = false, _mut12061 = false, _mut12062 = false, _mut12063 = false, _mut12064 = false, _mut12065 = false, _mut12066 = false, _mut12067 = false, _mut12068 = false, _mut12069 = false, _mut12070 = false, _mut12071 = false, _mut12072 = false, _mut12073 = false, _mut12074 = false, _mut12075 = false, _mut12076 = false, _mut12077 = false, _mut12078 = false, _mut12079 = false, _mut12080 = false, _mut12081 = false, _mut12082 = false, _mut12083 = false, _mut12084 = false, _mut12085 = false, _mut12086 = false, _mut12087 = false, _mut12088 = false, _mut12089 = false, _mut12090 = false, _mut12091 = false, _mut12092 = false, _mut12093 = false, _mut12094 = false, _mut12095 = false, _mut12096 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -1417964919405031606L;

    /**
     * Initial integration time.
     */
    private double initialTime;

    /**
     * Final integration time.
     */
    private double finalTime;

    /**
     * Integration direction indicator.
     */
    private boolean forward;

    /**
     * Current interpolator index.
     */
    private int index;

    /**
     * Steps table.
     */
    private List<StepInterpolator> steps;

    /**
     * Simple constructor.
     * Build an empty continuous output model.
     */
    public ContinuousOutputModel() {
        steps = new ArrayList<StepInterpolator>();
        initialTime = Double.NaN;
        finalTime = Double.NaN;
        forward = true;
        index = 0;
    }

    /**
     * Append another model at the end of the instance.
     * @param model model to add at the end of the instance
     * @exception MathIllegalArgumentException if the model to append is not
     * compatible with the instance (dimension of the state vector,
     * propagation direction, hole between the dates)
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void append(final ContinuousOutputModel model) throws MathIllegalArgumentException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.append_129");
        if (ROR_equals(model.steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11799, _mut11800, _mut11801, _mut11802, _mut11803)) {
            return;
        }
        if (ROR_equals(steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11804, _mut11805, _mut11806, _mut11807, _mut11808)) {
            initialTime = model.initialTime;
            forward = model.forward;
        } else {
            if (ROR_not_equals(getInterpolatedState().length, model.getInterpolatedState().length, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11809, _mut11810, _mut11811, _mut11812, _mut11813)) {
                throw new DimensionMismatchException(model.getInterpolatedState().length, getInterpolatedState().length);
            }
            if (forward ^ model.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH);
            }
            final StepInterpolator lastInterpolator = steps.get(index);
            final double current = lastInterpolator.getCurrentTime();
            final double previous = lastInterpolator.getPreviousTime();
            final double step = AOR_minus(current, previous, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11814, _mut11815, _mut11816, _mut11817);
            final double gap = AOR_minus(model.getInitialTime(), current, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11818, _mut11819, _mut11820, _mut11821);
            if (ROR_greater(FastMath.abs(gap), AOR_multiply(1.0e-3, FastMath.abs(step), "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11822, _mut11823, _mut11824, _mut11825), "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11826, _mut11827, _mut11828, _mut11829, _mut11830)) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, FastMath.abs(gap));
            }
        }
        for (StepInterpolator interpolator : model.steps) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.append_129");
            steps.add(interpolator.copy());
        }
        index = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputModel.append_129", _mut11831, _mut11832, _mut11833, _mut11834);
        finalTime = (steps.get(index)).getCurrentTime();
    }

    /**
     * {@inheritDoc}
     */
    public void init(double t0, double[] y0, double t) {
        initialTime = Double.NaN;
        finalTime = Double.NaN;
        forward = true;
        index = 0;
        steps.clear();
    }

    /**
     * Handle the last accepted step.
     * A copy of the information provided by the last step is stored in
     * the instance for later use.
     * @param interpolator interpolator for the last accepted step.
     * @param isLast true if the step is the last one
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void handleStep(final StepInterpolator interpolator, final boolean isLast) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.handleStep_188");
        if (ROR_equals(steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.handleStep_188", _mut11835, _mut11836, _mut11837, _mut11838, _mut11839)) {
            initialTime = interpolator.getPreviousTime();
            forward = interpolator.isForward();
        }
        steps.add(interpolator.copy());
        if (isLast) {
            finalTime = interpolator.getCurrentTime();
            index = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputModel.handleStep_188", _mut11840, _mut11841, _mut11842, _mut11843);
        }
    }

    /**
     * Get the initial integration time.
     * @return initial integration time
     */
    public double getInitialTime() {
        return initialTime;
    }

    /**
     * Get the final integration time.
     * @return final integration time
     */
    public double getFinalTime() {
        return finalTime;
    }

    /**
     * Get the time of the interpolated point.
     * If {@link #setInterpolatedTime} has not been called, it returns
     * the final integration time.
     * @return interpolation point time
     */
    public double getInterpolatedTime() {
        return steps.get(index).getInterpolatedTime();
    }

    /**
     * Set the time of the interpolated point.
     * <p>This method should <strong>not</strong> be called before the
     * integration is over because some internal variables are set only
     * once the last step has been handled.</p>
     * <p>Setting the time outside of the integration interval is now
     * allowed, but should be used with care since the accuracy of the
     * interpolator will probably be very poor far from this interval.
     * This allowance has been added to simplify implementation of search
     * algorithms near the interval endpoints.</p>
     * <p>Note that each time this method is called, the internal arrays
     * returned in {@link #getInterpolatedState()}, {@link
     * #getInterpolatedDerivatives()} and {@link #getInterpolatedSecondaryState(int)}
     * <em>will</em> be overwritten. So if their content must be preserved
     * across several calls, user must copy them.</p>
     * @param time time of the interpolated point
     * @see #getInterpolatedState()
     * @see #getInterpolatedDerivatives()
     * @see #getInterpolatedSecondaryState(int)
     */
    public void setInterpolatedTime(final double time) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250");
        // initialize the search with the complete steps table
        int iMin = 0;
        final StepInterpolator sMin = steps.get(iMin);
        double tMin = AOR_multiply(0.5, (AOR_plus(sMin.getPreviousTime(), sMin.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11844, _mut11845, _mut11846, _mut11847)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11848, _mut11849, _mut11850, _mut11851);
        int iMax = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11852, _mut11853, _mut11854, _mut11855);
        final StepInterpolator sMax = steps.get(iMax);
        double tMax = AOR_multiply(0.5, (AOR_plus(sMax.getPreviousTime(), sMax.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11856, _mut11857, _mut11858, _mut11859)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11860, _mut11861, _mut11862, _mut11863);
        // or in the first and last step
        if (ROR_less_equals(locatePoint(time, sMin), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11864, _mut11865, _mut11866, _mut11867, _mut11868)) {
            index = iMin;
            sMin.setInterpolatedTime(time);
            return;
        }
        if (ROR_greater_equals(locatePoint(time, sMax), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11869, _mut11870, _mut11871, _mut11872, _mut11873)) {
            index = iMax;
            sMax.setInterpolatedTime(time);
            return;
        }
        // reduction of the table slice size
        while (ROR_greater(AOR_minus(iMax, iMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12057, _mut12058, _mut12059, _mut12060), 5, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12061, _mut12062, _mut12063, _mut12064, _mut12065)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250");
            // use the last estimated index as the splitting index
            final StepInterpolator si = steps.get(index);
            final int location = locatePoint(time, si);
            if (ROR_less(location, 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11874, _mut11875, _mut11876, _mut11877, _mut11878)) {
                iMax = index;
                tMax = AOR_multiply(0.5, (AOR_plus(si.getPreviousTime(), si.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11892, _mut11893, _mut11894, _mut11895)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11896, _mut11897, _mut11898, _mut11899);
            } else if (ROR_greater(location, 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11879, _mut11880, _mut11881, _mut11882, _mut11883)) {
                iMin = index;
                tMin = AOR_multiply(0.5, (AOR_plus(si.getPreviousTime(), si.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11884, _mut11885, _mut11886, _mut11887)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11888, _mut11889, _mut11890, _mut11891);
            } else {
                // we have found the target step, no need to continue searching
                si.setInterpolatedTime(time);
                return;
            }
            // compute a new estimate of the index in the reduced table slice
            final int iMed = AOR_divide((AOR_plus(iMin, iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11900, _mut11901, _mut11902, _mut11903)), 2, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11904, _mut11905, _mut11906, _mut11907);
            final StepInterpolator sMed = steps.get(iMed);
            final double tMed = AOR_multiply(0.5, (AOR_plus(sMed.getPreviousTime(), sMed.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11908, _mut11909, _mut11910, _mut11911)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11912, _mut11913, _mut11914, _mut11915);
            if ((_mut11934 ? ((ROR_less(FastMath.abs(AOR_minus(tMed, tMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11916, _mut11917, _mut11918, _mut11919)), 1e-6, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11920, _mut11921, _mut11922, _mut11923, _mut11924)) && (ROR_less(FastMath.abs(AOR_minus(tMax, tMed, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11925, _mut11926, _mut11927, _mut11928)), 1e-6, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11929, _mut11930, _mut11931, _mut11932, _mut11933))) : ((ROR_less(FastMath.abs(AOR_minus(tMed, tMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11916, _mut11917, _mut11918, _mut11919)), 1e-6, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11920, _mut11921, _mut11922, _mut11923, _mut11924)) || (ROR_less(FastMath.abs(AOR_minus(tMax, tMed, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11925, _mut11926, _mut11927, _mut11928)), 1e-6, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11929, _mut11930, _mut11931, _mut11932, _mut11933))))) {
                // too close to the bounds, we estimate using a simple dichotomy
                index = iMed;
            } else {
                // compute index = P(time) rather than solving a quadratic equation)
                final double d12 = AOR_minus(tMax, tMed, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11935, _mut11936, _mut11937, _mut11938);
                final double d23 = AOR_minus(tMed, tMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11939, _mut11940, _mut11941, _mut11942);
                final double d13 = AOR_minus(tMax, tMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11943, _mut11944, _mut11945, _mut11946);
                final double dt1 = AOR_minus(time, tMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11947, _mut11948, _mut11949, _mut11950);
                final double dt2 = AOR_minus(time, tMed, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11951, _mut11952, _mut11953, _mut11954);
                final double dt3 = AOR_minus(time, tMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11955, _mut11956, _mut11957, _mut11958);
                final double iLagrange = AOR_divide((AOR_plus(AOR_minus(AOR_multiply((AOR_multiply(AOR_multiply(dt2, dt3, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11959, _mut11960, _mut11961, _mut11962), d23, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11963, _mut11964, _mut11965, _mut11966)), iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11967, _mut11968, _mut11969, _mut11970), AOR_multiply((AOR_multiply(AOR_multiply(dt1, dt3, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11971, _mut11972, _mut11973, _mut11974), d13, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11975, _mut11976, _mut11977, _mut11978)), iMed, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11979, _mut11980, _mut11981, _mut11982), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11983, _mut11984, _mut11985, _mut11986), AOR_multiply((AOR_multiply(AOR_multiply(dt1, dt2, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11987, _mut11988, _mut11989, _mut11990), d12, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11991, _mut11992, _mut11993, _mut11994)), iMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11995, _mut11996, _mut11997, _mut11998), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut11999, _mut12000, _mut12001, _mut12002)), (AOR_multiply(AOR_multiply(d12, d23, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12003, _mut12004, _mut12005, _mut12006), d13, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12007, _mut12008, _mut12009, _mut12010)), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12011, _mut12012, _mut12013, _mut12014);
                index = (int) FastMath.rint(iLagrange);
            }
            // force the next size reduction to be at least one tenth
            final int low = FastMath.max(AOR_plus(iMin, 1, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12015, _mut12016, _mut12017, _mut12018), AOR_divide((AOR_plus(AOR_multiply(9, iMin, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12019, _mut12020, _mut12021, _mut12022), iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12023, _mut12024, _mut12025, _mut12026)), 10, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12027, _mut12028, _mut12029, _mut12030));
            final int high = FastMath.min(AOR_minus(iMax, 1, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12031, _mut12032, _mut12033, _mut12034), AOR_divide((AOR_plus(iMin, AOR_multiply(9, iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12035, _mut12036, _mut12037, _mut12038), "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12039, _mut12040, _mut12041, _mut12042)), 10, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12043, _mut12044, _mut12045, _mut12046));
            if (ROR_less(index, low, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12047, _mut12048, _mut12049, _mut12050, _mut12051)) {
                index = low;
            } else if (ROR_greater(index, high, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12052, _mut12053, _mut12054, _mut12055, _mut12056)) {
                index = high;
            }
        }
        // now the table slice is very small, we perform an iterative search
        index = iMin;
        while ((_mut12076 ? ((ROR_less_equals(index, iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12066, _mut12067, _mut12068, _mut12069, _mut12070)) || (ROR_greater(locatePoint(time, steps.get(index)), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12071, _mut12072, _mut12073, _mut12074, _mut12075))) : ((ROR_less_equals(index, iMax, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12066, _mut12067, _mut12068, _mut12069, _mut12070)) && (ROR_greater(locatePoint(time, steps.get(index)), 0, "org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250", _mut12071, _mut12072, _mut12073, _mut12074, _mut12075))))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime_250");
            ++index;
        }
        steps.get(index).setInterpolatedTime(time);
    }

    /**
     * Get the state vector of the interpolated point.
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls to the associated
     * {@link #setInterpolatedTime(double)} method.</p>
     * @return state vector at time {@link #getInterpolatedTime}
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @see #setInterpolatedTime(double)
     * @see #getInterpolatedDerivatives()
     * @see #getInterpolatedSecondaryState(int)
     * @see #getInterpolatedSecondaryDerivatives(int)
     */
    public double[] getInterpolatedState() throws MaxCountExceededException {
        return steps.get(index).getInterpolatedState();
    }

    /**
     * Get the derivatives of the state vector of the interpolated point.
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls to the associated
     * {@link #setInterpolatedTime(double)} method.</p>
     * @return derivatives of the state vector at time {@link #getInterpolatedTime}
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @see #setInterpolatedTime(double)
     * @see #getInterpolatedState()
     * @see #getInterpolatedSecondaryState(int)
     * @see #getInterpolatedSecondaryDerivatives(int)
     * @since 3.4
     */
    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        return steps.get(index).getInterpolatedDerivatives();
    }

    /**
     * Get the interpolated secondary state corresponding to the secondary equations.
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls to the associated
     * {@link #setInterpolatedTime(double)} method.</p>
     * @param secondaryStateIndex index of the secondary set, as returned by {@link
     * org.apache.commons.math3.ode.ExpandableStatefulODE#addSecondaryEquations(
     * org.apache.commons.math3.ode.SecondaryEquations)
     * ExpandableStatefulODE.addSecondaryEquations(SecondaryEquations)}
     * @return interpolated secondary state at the current interpolation date
     * @see #setInterpolatedTime(double)
     * @see #getInterpolatedState()
     * @see #getInterpolatedDerivatives()
     * @see #getInterpolatedSecondaryDerivatives(int)
     * @since 3.2
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     */
    public double[] getInterpolatedSecondaryState(final int secondaryStateIndex) throws MaxCountExceededException {
        return steps.get(index).getInterpolatedSecondaryState(secondaryStateIndex);
    }

    /**
     * Get the interpolated secondary derivatives corresponding to the secondary equations.
     * <p>The returned vector is a reference to a reused array, so
     * it should not be modified and it should be copied if it needs
     * to be preserved across several calls to the associated
     * {@link #setInterpolatedTime(double)} method.</p>
     * @param secondaryStateIndex index of the secondary set, as returned by {@link
     * org.apache.commons.math3.ode.ExpandableStatefulODE#addSecondaryEquations(
     * org.apache.commons.math3.ode.SecondaryEquations)
     * ExpandableStatefulODE.addSecondaryEquations(SecondaryEquations)}
     * @return interpolated secondary derivatives at the current interpolation date
     * @see #setInterpolatedTime(double)
     * @see #getInterpolatedState()
     * @see #getInterpolatedDerivatives()
     * @see #getInterpolatedSecondaryState(int)
     * @since 3.4
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     */
    public double[] getInterpolatedSecondaryDerivatives(final int secondaryStateIndex) throws MaxCountExceededException {
        return steps.get(index).getInterpolatedSecondaryDerivatives(secondaryStateIndex);
    }

    /**
     * Compare a step interval and a double.
     * @param time point to locate
     * @param interval step interval
     * @return -1 if the double is before the interval, 0 if it is in
     * the interval, and +1 if it is after the interval, according to
     * the interval direction
     */
    private int locatePoint(final double time, final StepInterpolator interval) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputModel.locatePoint_424");
        if (forward) {
            if (ROR_less(time, interval.getPreviousTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.locatePoint_424", _mut12077, _mut12078, _mut12079, _mut12080, _mut12081)) {
                return -1;
            } else if (ROR_greater(time, interval.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.locatePoint_424", _mut12082, _mut12083, _mut12084, _mut12085, _mut12086)) {
                return +1;
            } else {
                return 0;
            }
        }
        if (ROR_greater(time, interval.getPreviousTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.locatePoint_424", _mut12087, _mut12088, _mut12089, _mut12090, _mut12091)) {
            return -1;
        } else if (ROR_less(time, interval.getCurrentTime(), "org.apache.commons.math3.ode.ContinuousOutputModel.locatePoint_424", _mut12092, _mut12093, _mut12094, _mut12095, _mut12096)) {
            return +1;
        } else {
            return 0;
        }
    }
}
