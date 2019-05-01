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
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.KthSelector;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Provides percentile computation.
 * <p>
 * There are several commonly used methods for estimating percentiles (a.k.a.
 * quantiles) based on sample data.  For large samples, the different methods
 * agree closely, but when sample sizes are small, different methods will give
 * significantly different results.  The algorithm implemented here works as follows:
 * <ol>
 * <li>Let <code>n</code> be the length of the (sorted) array and
 * <code>0 < p <= 100</code> be the desired percentile.</li>
 * <li>If <code> n = 1 </code> return the unique array element (regardless of
 * the value of <code>p</code>); otherwise </li>
 * <li>Compute the estimated percentile position
 * <code> pos = p * (n + 1) / 100</code> and the difference, <code>d</code>
 * between <code>pos</code> and <code>floor(pos)</code> (i.e. the fractional
 * part of <code>pos</code>).</li>
 * <li> If <code>pos < 1</code> return the smallest element in the array.</li>
 * <li> Else if <code>pos >= n</code> return the largest element in the array.</li>
 * <li> Else let <code>lower</code> be the element in position
 * <code>floor(pos)</code> in the array and let <code>upper</code> be the
 * next element in the array.  Return <code>lower + d * (upper - lower)</code>
 * </li>
 * </ol></p>
 * <p>
 * To compute percentiles, the data must be at least partially ordered.  Input
 * arrays are copied and recursively partitioned using an ordering definition.
 * The ordering used by <code>Arrays.sort(double[])</code> is the one determined
 * by {@link java.lang.Double#compareTo(Double)}.  This ordering makes
 * <code>Double.NaN</code> larger than any other value (including
 * <code>Double.POSITIVE_INFINITY</code>).  Therefore, for example, the median
 * (50th percentile) of
 * <code>{0, 1, 2, 3, 4, Double.NaN}</code> evaluates to <code>2.5.</code></p>
 * <p>
 * Since percentile estimation usually involves interpolation between array
 * elements, arrays containing  <code>NaN</code> or infinite values will often
 * result in <code>NaN</code> or infinite values returned.</p>
 * <p>
 * Further, to include different estimation types such as R1, R2 as mentioned in
 * <a href="http://en.wikipedia.org/wiki/Quantile">Quantile page(wikipedia)</a>,
 * a type specific NaN handling strategy is used to closely match with the
 * typically observed results from popular tools like R(R1-R9), Excel(R7).</p>
 * <p>
 * Since 2.2, Percentile uses only selection instead of complete sorting
 * and caches selection algorithm state between calls to the various
 * {@code evaluate} methods. This greatly improves efficiency, both for a single
 * percentile and multiple percentile computations. To maximize performance when
 * multiple percentiles are computed based on the same data, users should set the
 * data array once using either one of the {@link #evaluate(double[], double)} or
 * {@link #setData(double[])} methods and thereafter {@link #evaluate(double)}
 * with just the percentile provided.
 * </p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Percentile extends AbstractUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut3818 = false, _mut3819 = false, _mut3820 = false, _mut3821 = false, _mut3822 = false, _mut3823 = false, _mut3824 = false, _mut3825 = false, _mut3826 = false, _mut3827 = false, _mut3828 = false, _mut3829 = false, _mut3830 = false, _mut3831 = false, _mut3832 = false, _mut3833 = false, _mut3834 = false, _mut3835 = false, _mut3836 = false, _mut3837 = false, _mut3838 = false, _mut3839 = false, _mut3840 = false, _mut3841 = false, _mut3842 = false, _mut3843 = false, _mut3844 = false, _mut3845 = false, _mut3846 = false, _mut3847 = false, _mut3848 = false, _mut3849 = false, _mut3850 = false, _mut3851 = false, _mut3852 = false, _mut3853 = false, _mut3854 = false, _mut3855 = false, _mut3856 = false, _mut3857 = false, _mut3858 = false, _mut3859 = false, _mut3860 = false, _mut3861 = false, _mut3862 = false, _mut3863 = false, _mut3864 = false, _mut3865 = false, _mut3866 = false, _mut3867 = false, _mut3868 = false, _mut3869 = false, _mut3870 = false, _mut3871 = false, _mut3872 = false, _mut3873 = false, _mut3874 = false, _mut3875 = false, _mut3876 = false, _mut3877 = false, _mut3878 = false, _mut3879 = false, _mut3880 = false, _mut3881 = false, _mut3882 = false, _mut3883 = false, _mut3884 = false, _mut3885 = false, _mut3886 = false, _mut3887 = false, _mut3888 = false, _mut3889 = false, _mut3890 = false, _mut3891 = false, _mut3892 = false, _mut3893 = false, _mut3894 = false, _mut3895 = false, _mut3896 = false, _mut3897 = false, _mut3898 = false, _mut3899 = false, _mut3900 = false, _mut3901 = false, _mut3902 = false, _mut3903 = false, _mut3904 = false, _mut3905 = false, _mut3906 = false, _mut3907 = false, _mut3908 = false, _mut3909 = false, _mut3910 = false, _mut3911 = false, _mut3912 = false, _mut3913 = false, _mut3914 = false, _mut3915 = false, _mut3916 = false, _mut3917 = false, _mut3918 = false, _mut3919 = false, _mut3920 = false, _mut3921 = false, _mut3922 = false, _mut3923 = false, _mut3924 = false, _mut3925 = false, _mut3926 = false, _mut3927 = false, _mut3928 = false, _mut3929 = false, _mut3930 = false, _mut3931 = false, _mut3932 = false, _mut3933 = false, _mut3934 = false, _mut3935 = false, _mut3936 = false, _mut3937 = false, _mut3938 = false, _mut3939 = false, _mut3940 = false, _mut3941 = false, _mut3942 = false, _mut3943 = false, _mut3944 = false, _mut3945 = false, _mut3946 = false, _mut3947 = false, _mut3948 = false, _mut3949 = false, _mut3950 = false, _mut3951 = false, _mut3952 = false, _mut3953 = false, _mut3954 = false, _mut3955 = false, _mut3956 = false, _mut3957 = false, _mut3958 = false, _mut3959 = false, _mut3960 = false, _mut3961 = false, _mut3962 = false, _mut3963 = false, _mut3964 = false, _mut3965 = false, _mut3966 = false, _mut3967 = false, _mut3968 = false, _mut3969 = false, _mut3970 = false, _mut3971 = false, _mut3972 = false, _mut3973 = false, _mut3974 = false, _mut3975 = false, _mut3976 = false, _mut3977 = false, _mut3978 = false, _mut3979 = false, _mut3980 = false, _mut3981 = false, _mut3982 = false, _mut3983 = false, _mut3984 = false, _mut3985 = false, _mut3986 = false, _mut3987 = false, _mut3988 = false, _mut3989 = false, _mut3990 = false, _mut3991 = false, _mut3992 = false, _mut3993 = false, _mut3994 = false, _mut3995 = false, _mut3996 = false, _mut3997 = false, _mut3998 = false, _mut3999 = false, _mut4000 = false, _mut4001 = false, _mut4002 = false, _mut4003 = false, _mut4004 = false, _mut4005 = false, _mut4006 = false, _mut4007 = false, _mut4008 = false, _mut4009 = false, _mut4010 = false, _mut4011 = false, _mut4012 = false, _mut4013 = false, _mut4014 = false, _mut4015 = false, _mut4016 = false, _mut4017 = false, _mut4018 = false, _mut4019 = false, _mut4020 = false, _mut4021 = false, _mut4022 = false, _mut4023 = false, _mut4024 = false, _mut4025 = false, _mut4026 = false, _mut4027 = false, _mut4028 = false, _mut4029 = false, _mut4030 = false, _mut4031 = false, _mut4032 = false, _mut4033 = false, _mut4034 = false, _mut4035 = false, _mut4036 = false, _mut4037 = false, _mut4038 = false, _mut4039 = false, _mut4040 = false, _mut4041 = false, _mut4042 = false, _mut4043 = false, _mut4044 = false, _mut4045 = false, _mut4046 = false, _mut4047 = false, _mut4048 = false, _mut4049 = false, _mut4050 = false, _mut4051 = false, _mut4052 = false, _mut4053 = false, _mut4054 = false, _mut4055 = false, _mut4056 = false, _mut4057 = false, _mut4058 = false, _mut4059 = false, _mut4060 = false, _mut4061 = false, _mut4062 = false, _mut4063 = false, _mut4064 = false, _mut4065 = false, _mut4066 = false, _mut4067 = false, _mut4068 = false, _mut4069 = false, _mut4070 = false, _mut4071 = false, _mut4072 = false, _mut4073 = false, _mut4074 = false, _mut4075 = false, _mut4076 = false, _mut4077 = false, _mut4078 = false, _mut4079 = false, _mut4080 = false, _mut4081 = false, _mut4082 = false, _mut4083 = false, _mut4084 = false, _mut4085 = false, _mut4086 = false, _mut4087 = false, _mut4088 = false, _mut4089 = false, _mut4090 = false, _mut4091 = false, _mut4092 = false, _mut4093 = false, _mut4094 = false, _mut4095 = false, _mut4096 = false, _mut4097 = false, _mut4098 = false, _mut4099 = false, _mut4100 = false, _mut4101 = false, _mut4102 = false, _mut4103 = false, _mut4104 = false, _mut4105 = false, _mut4106 = false, _mut4107 = false, _mut4108 = false, _mut4109 = false, _mut4110 = false, _mut4111 = false, _mut4112 = false, _mut4113 = false, _mut4114 = false, _mut4115 = false, _mut4116 = false, _mut4117 = false, _mut4118 = false, _mut4119 = false, _mut4120 = false, _mut4121 = false, _mut4122 = false, _mut4123 = false, _mut4124 = false, _mut4125 = false, _mut4126 = false, _mut4127 = false, _mut4128 = false, _mut4129 = false, _mut4130 = false, _mut4131 = false, _mut4132 = false, _mut4133 = false, _mut4134 = false, _mut4135 = false, _mut4136 = false, _mut4137 = false, _mut4138 = false, _mut4139 = false, _mut4140 = false, _mut4141 = false, _mut4142 = false, _mut4143 = false, _mut4144 = false, _mut4145 = false, _mut4146 = false, _mut4147 = false, _mut4148 = false, _mut4149 = false, _mut4150 = false, _mut4151 = false, _mut4152 = false, _mut4153 = false, _mut4154 = false, _mut4155 = false, _mut4156 = false, _mut4157 = false, _mut4158 = false, _mut4159 = false, _mut4160 = false, _mut4161 = false, _mut4162 = false, _mut4163 = false, _mut4164 = false, _mut4165 = false, _mut4166 = false, _mut4167 = false, _mut4168 = false, _mut4169 = false, _mut4170 = false, _mut4171 = false, _mut4172 = false, _mut4173 = false, _mut4174 = false, _mut4175 = false, _mut4176 = false, _mut4177 = false, _mut4178 = false, _mut4179 = false, _mut4180 = false, _mut4181 = false, _mut4182 = false, _mut4183 = false, _mut4184 = false, _mut4185 = false, _mut4186 = false, _mut4187 = false, _mut4188 = false, _mut4189 = false, _mut4190 = false, _mut4191 = false, _mut4192 = false, _mut4193 = false, _mut4194 = false, _mut4195 = false, _mut4196 = false, _mut4197 = false, _mut4198 = false, _mut4199 = false, _mut4200 = false, _mut4201 = false, _mut4202 = false, _mut4203 = false, _mut4204 = false, _mut4205 = false, _mut4206 = false, _mut4207 = false, _mut4208 = false, _mut4209 = false, _mut4210 = false, _mut4211 = false, _mut4212 = false, _mut4213 = false, _mut4214 = false, _mut4215 = false, _mut4216 = false, _mut4217 = false, _mut4218 = false, _mut4219 = false, _mut4220 = false, _mut4221 = false, _mut4222 = false, _mut4223 = false, _mut4224 = false, _mut4225 = false, _mut4226 = false, _mut4227 = false, _mut4228 = false, _mut4229 = false, _mut4230 = false, _mut4231 = false, _mut4232 = false, _mut4233 = false, _mut4234 = false, _mut4235 = false, _mut4236 = false, _mut4237 = false, _mut4238 = false, _mut4239 = false, _mut4240 = false, _mut4241 = false, _mut4242 = false, _mut4243 = false, _mut4244 = false, _mut4245 = false, _mut4246 = false, _mut4247 = false, _mut4248 = false, _mut4249 = false, _mut4250 = false, _mut4251 = false, _mut4252 = false, _mut4253 = false, _mut4254 = false, _mut4255 = false, _mut4256 = false, _mut4257 = false, _mut4258 = false, _mut4259 = false, _mut4260 = false, _mut4261 = false, _mut4262 = false, _mut4263 = false, _mut4264 = false, _mut4265 = false, _mut4266 = false, _mut4267 = false, _mut4268 = false, _mut4269 = false, _mut4270 = false, _mut4271 = false, _mut4272 = false, _mut4273 = false, _mut4274 = false, _mut4275 = false, _mut4276 = false, _mut4277 = false, _mut4278 = false, _mut4279 = false, _mut4280 = false, _mut4281 = false, _mut4282 = false, _mut4283 = false, _mut4284 = false, _mut4285 = false, _mut4286 = false, _mut4287 = false, _mut4288 = false, _mut4289 = false, _mut4290 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -8091216485095130416L;

    /**
     * Maximum number of partitioning pivots cached (each level double the number of pivots).
     */
    private static final int MAX_CACHED_LEVELS = 10;

    /**
     * Maximum number of cached pivots in the pivots cached array
     */
    private static final int PIVOTS_HEAP_LENGTH = 0x1 << AOR_minus(MAX_CACHED_LEVELS, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.copy_163", _mut3818, _mut3819, _mut3820, _mut3821);

    /**
     * Default KthSelector used with default pivoting strategy
     */
    private final KthSelector kthSelector;

    /**
     * Any of the {@link EstimationType}s such as {@link EstimationType#LEGACY CM} can be used.
     */
    private final EstimationType estimationType;

    /**
     * NaN Handling of the input as defined by {@link NaNStrategy}
     */
    private final NaNStrategy nanStrategy;

    /**
     * Determines what percentile is computed when evaluate() is activated
     * with no quantile argument
     */
    private double quantile;

    /**
     * Cached pivots.
     */
    private int[] cachedPivots;

    /**
     * Constructs a Percentile with the following defaults.
     * <ul>
     *   <li>default quantile: 50.0, can be reset with {@link #setQuantile(double)}</li>
     *   <li>default estimation type: {@link EstimationType#LEGACY},
     *   can be reset with {@link #withEstimationType(EstimationType)}</li>
     *   <li>default NaN strategy: {@link NaNStrategy#REMOVED},
     *   can be reset with {@link #withNaNStrategy(NaNStrategy)}</li>
     *   <li>a KthSelector that makes use of {@link MedianOf3PivotingStrategy},
     *   can be reset with {@link #withKthSelector(KthSelector)}</li>
     * </ul>
     */
    public Percentile() {
        // No try-catch or advertised exception here - arg is valid
        this(50.0);
    }

    /**
     * Constructs a Percentile with the specific quantile value and the following
     * <ul>
     *   <li>default method type: {@link EstimationType#LEGACY}</li>
     *   <li>default NaN strategy: {@link NaNStrategy#REMOVED}</li>
     *   <li>a Kth Selector : {@link KthSelector}</li>
     * </ul>
     * @param quantile the quantile
     * @throws MathIllegalArgumentException  if p is not greater than 0 and less
     * than or equal to 100
     */
    public Percentile(final double quantile) throws MathIllegalArgumentException {
        this(quantile, EstimationType.LEGACY, NaNStrategy.REMOVED, new KthSelector(new MedianOf3PivotingStrategy()));
    }

    /**
     * Copy constructor, creates a new {@code Percentile} identical
     * to the {@code original}
     *
     * @param original the {@code Percentile} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Percentile(final Percentile original) throws NullArgumentException {
        MathUtils.checkNotNull(original);
        estimationType = original.getEstimationType();
        nanStrategy = original.getNaNStrategy();
        kthSelector = original.getKthSelector();
        setData(original.getDataRef());
        if (original.cachedPivots != null) {
            System.arraycopy(original.cachedPivots, 0, cachedPivots, 0, original.cachedPivots.length);
        }
        setQuantile(original.quantile);
    }

    /**
     * Constructs a Percentile with the specific quantile value,
     * {@link EstimationType}, {@link NaNStrategy} and {@link KthSelector}.
     *
     * @param quantile the quantile to be computed
     * @param estimationType one of the percentile {@link EstimationType  estimation types}
     * @param nanStrategy one of {@link NaNStrategy} to handle with NaNs
     * @param kthSelector a {@link KthSelector} to use for pivoting during search
     * @throws MathIllegalArgumentException if p is not within (0,100]
     * @throws NullArgumentException if type or NaNStrategy passed is null
     */
    protected Percentile(final double quantile, final EstimationType estimationType, final NaNStrategy nanStrategy, final KthSelector kthSelector) throws MathIllegalArgumentException {
        setQuantile(quantile);
        cachedPivots = null;
        MathUtils.checkNotNull(estimationType);
        MathUtils.checkNotNull(nanStrategy);
        MathUtils.checkNotNull(kthSelector);
        this.estimationType = estimationType;
        this.nanStrategy = nanStrategy;
        this.kthSelector = kthSelector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(final double[] values) {
        if (values == null) {
            cachedPivots = null;
        } else {
            cachedPivots = new int[PIVOTS_HEAP_LENGTH];
            Arrays.fill(cachedPivots, -1);
        }
        super.setData(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        if (values == null) {
            cachedPivots = null;
        } else {
            cachedPivots = new int[PIVOTS_HEAP_LENGTH];
            Arrays.fill(cachedPivots, -1);
        }
        super.setData(values, begin, length);
    }

    /**
     * Returns the result of evaluating the statistic over the stored data.
     * <p>
     * The stored array is the one which was set by previous calls to
     * {@link #setData(double[])}
     * </p>
     * @param p the percentile value to compute
     * @return the value of the statistic applied to the stored data
     * @throws MathIllegalArgumentException if p is not a valid quantile value
     * (p must be greater than 0 and less than or equal to 100)
     */
    public double evaluate(final double p) throws MathIllegalArgumentException {
        return evaluate(getDataRef(), p);
    }

    /**
     * Returns an estimate of the <code>p</code>th percentile of the values
     * in the <code>values</code> array.
     * <p>
     * Calls to this method do not modify the internal <code>quantile</code>
     * state of this statistic.</p>
     * <p>
     * <ul>
     * <li>Returns <code>Double.NaN</code> if <code>values</code> has length
     * <code>0</code></li>
     * <li>Returns (for any value of <code>p</code>) <code>values[0]</code>
     *  if <code>values</code> has length <code>1</code></li>
     * <li>Throws <code>MathIllegalArgumentException</code> if <code>values</code>
     * is null or p is not a valid quantile value (p must be greater than 0
     * and less than or equal to 100) </li>
     * </ul></p>
     * <p>
     * See {@link Percentile} for a description of the percentile estimation
     * algorithm used.</p>
     *
     * @param values input array of values
     * @param p the percentile value to compute
     * @return the percentile value or Double.NaN if the array is empty
     * @throws MathIllegalArgumentException if <code>values</code> is null
     *     or p is invalid
     */
    public double evaluate(final double[] values, final double p) throws MathIllegalArgumentException {
        test(values, 0, 0);
        return evaluate(values, 0, values.length, p);
    }

    /**
     * Returns an estimate of the <code>quantile</code>th percentile of the
     * designated values in the <code>values</code> array.  The quantile
     * estimated is determined by the <code>quantile</code> property.
     * <p>
     * <ul>
     * <li>Returns <code>Double.NaN</code> if <code>length = 0</code></li>
     * <li>Returns (for any value of <code>quantile</code>)
     * <code>values[begin]</code> if <code>length = 1 </code></li>
     * <li>Throws <code>MathIllegalArgumentException</code> if <code>values</code>
     * is null, or <code>start</code> or <code>length</code> is invalid</li>
     * </ul></p>
     * <p>
     * See {@link Percentile} for a description of the percentile estimation
     * algorithm used.</p>
     *
     * @param values the input array
     * @param start index of the first array element to include
     * @param length the number of elements to include
     * @return the percentile value
     * @throws MathIllegalArgumentException if the parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int start, final int length) throws MathIllegalArgumentException {
        return evaluate(values, start, length, quantile);
    }

    /**
     * Returns an estimate of the <code>p</code>th percentile of the values
     * in the <code>values</code> array, starting with the element in (0-based)
     * position <code>begin</code> in the array and including <code>length</code>
     * values.
     * <p>
     * Calls to this method do not modify the internal <code>quantile</code>
     * state of this statistic.</p>
     * <p>
     * <ul>
     * <li>Returns <code>Double.NaN</code> if <code>length = 0</code></li>
     * <li>Returns (for any value of <code>p</code>) <code>values[begin]</code>
     *  if <code>length = 1 </code></li>
     * <li>Throws <code>MathIllegalArgumentException</code> if <code>values</code>
     *  is null , <code>begin</code> or <code>length</code> is invalid, or
     * <code>p</code> is not a valid quantile value (p must be greater than 0
     * and less than or equal to 100)</li>
     * </ul></p>
     * <p>
     * See {@link Percentile} for a description of the percentile estimation
     * algorithm used.</p>
     *
     * @param values array of input values
     * @param p  the percentile to compute
     * @param begin  the first (0-based) element to include in the computation
     * @param length  the number of array elements to include
     * @return  the percentile value
     * @throws MathIllegalArgumentException if the parameters are not valid or the
     * input array is null
     */
    public double evaluate(final double[] values, final int begin, final int length, final double p) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335");
        test(values, begin, length);
        if ((_mut3832 ? (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3822, _mut3823, _mut3824, _mut3825, _mut3826) && ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3827, _mut3828, _mut3829, _mut3830, _mut3831)) : (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3822, _mut3823, _mut3824, _mut3825, _mut3826) || ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3827, _mut3828, _mut3829, _mut3830, _mut3831)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
        }
        if (ROR_equals(length, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3833, _mut3834, _mut3835, _mut3836, _mut3837)) {
            return Double.NaN;
        }
        if (ROR_equals(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3838, _mut3839, _mut3840, _mut3841, _mut3842)) {
            // always return single value for n = 1
            return values[begin];
        }
        final double[] work = getWorkArray(values, begin, length);
        final int[] pivotsHeap = getPivots(values);
        return ROR_equals(work.length, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_335", _mut3843, _mut3844, _mut3845, _mut3846, _mut3847) ? Double.NaN : estimationType.evaluate(work, pivotsHeap, p, kthSelector);
    }

    /**
     * Select a pivot index as the median of three
     * <p>
     * <b>Note:</b> With the effect of allowing {@link KthSelector} to be set on
     * {@link Percentile} instances(thus indirectly {@link PivotingStrategy})
     * this method wont take effect any more and hence is unsupported.
     * @param work data array
     * @param begin index of the first element of the slice
     * @param end index after the last element of the slice
     * @return the index of the median element chosen between the
     * first, the middle and the last element of the array slice
     * @deprecated Please refrain from using this method (as it wont take effect)
     * and instead use {@link Percentile#withKthSelector(newKthSelector)} if
     * required.
     */
    @Deprecated
    int medianOf3(final double[] work, final int begin, final int end) {
        return new MedianOf3PivotingStrategy().pivotIndex(work, begin, end);
    }

    /**
     * Returns the value of the quantile field (determines what percentile is
     * computed when evaluate() is called with no quantile argument).
     *
     * @return quantile set while construction or {@link #setQuantile(double)}
     */
    public double getQuantile() {
        return quantile;
    }

    /**
     * Sets the value of the quantile field (determines what percentile is
     * computed when evaluate() is called with no quantile argument).
     *
     * @param p a value between 0 < p <= 100
     * @throws MathIllegalArgumentException  if p is not greater than 0 and less
     * than or equal to 100
     */
    public void setQuantile(final double p) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.setQuantile_396");
        if ((_mut3858 ? (ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.setQuantile_396", _mut3848, _mut3849, _mut3850, _mut3851, _mut3852) && ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.setQuantile_396", _mut3853, _mut3854, _mut3855, _mut3856, _mut3857)) : (ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.setQuantile_396", _mut3848, _mut3849, _mut3850, _mut3851, _mut3852) || ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.setQuantile_396", _mut3853, _mut3854, _mut3855, _mut3856, _mut3857)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
        }
        quantile = p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Percentile copy() {
        return new Percentile(this);
    }

    /**
     * Copies source to dest.
     * @param source Percentile to copy
     * @param dest Percentile to copy to
     * @exception MathUnsupportedOperationException always thrown since 3.4
     * @deprecated as of 3.4 this method does not work anymore, as it fails to
     * copy internal states between instances configured with different
     * {@link EstimationType estimation type}, {@link NaNStrategy NaN handling strategies}
     * and {@link KthSelector kthSelector}, it therefore always
     * throw {@link MathUnsupportedOperationException}
     */
    @Deprecated
    public static void copy(final Percentile source, final Percentile dest) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    /**
     * Get the work array to operate. Makes use of prior {@code storedData} if
     * it exists or else do a check on NaNs and copy a subset of the array
     * defined by begin and length parameters. The set {@link #nanStrategy} will
     * be used to either retain/remove/replace any NaNs present before returning
     * the resultant array.
     *
     * @param values the array of numbers
     * @param begin index to start reading the array
     * @param length the length of array to be read from the begin index
     * @return work array sliced from values in the range [begin,begin+length)
     * @throws MathIllegalArgumentException if values or indices are invalid
     */
    protected double[] getWorkArray(final double[] values, final int begin, final int length) {
        final double[] work;
        if (values == getDataRef()) {
            work = getDataRef();
        } else {
            switch(nanStrategy) {
                case // Replace NaNs with +INFs
                MAXIMAL:
                    work = replaceAndSlice(values, begin, length, Double.NaN, Double.POSITIVE_INFINITY);
                    break;
                case // Replace NaNs with -INFs
                MINIMAL:
                    work = replaceAndSlice(values, begin, length, Double.NaN, Double.NEGATIVE_INFINITY);
                    break;
                case // Drop NaNs from data
                REMOVED:
                    work = removeAndSlice(values, begin, length, Double.NaN);
                    break;
                case // just throw exception as NaN is un-acceptable
                FAILED:
                    work = copyOf(values, begin, length);
                    MathArrays.checkNotNaN(work);
                    break;
                default:
                    // FIXED
                    work = copyOf(values, begin, length);
                    break;
            }
        }
        return work;
    }

    /**
     * Make a copy of the array for the slice defined by array part from
     * [begin, begin+length)
     * @param values the input array
     * @param begin start index of the array to include
     * @param length number of elements to include from begin
     * @return copy of a slice of the original array
     */
    private static double[] copyOf(final double[] values, final int begin, final int length) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf_477");
        MathArrays.verifyValues(values, begin, length);
        return MathArrays.copyOfRange(values, begin, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.copyOf_477", _mut3859, _mut3860, _mut3861, _mut3862));
    }

    /**
     * Replace every occurrence of a given value with a replacement value in a
     * copied slice of array defined by array part from [begin, begin+length).
     * @param values the input array
     * @param begin start index of the array to include
     * @param length number of elements to include from begin
     * @param original the value to be replaced with
     * @param replacement the value to be used for replacement
     * @return the copy of sliced array with replaced values
     */
    private static double[] replaceAndSlice(final double[] values, final int begin, final int length, final double original, final double replacement) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.replaceAndSlice_492");
        final double[] temp = copyOf(values, begin, length);
        for (int i = 0; ROR_less(i, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.replaceAndSlice_492", _mut3863, _mut3864, _mut3865, _mut3866, _mut3867); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.replaceAndSlice_492");
            temp[i] = Precision.equalsIncludingNaN(original, temp[i]) ? replacement : temp[i];
        }
        return temp;
    }

    /**
     * Remove the occurrence of a given value in a copied slice of array
     * defined by the array part from [begin, begin+length).
     * @param values the input array
     * @param begin start index of the array to include
     * @param length number of elements to include from begin
     * @param removedValue the value to be removed from the sliced array
     * @return the copy of the sliced array after removing the removedValue
     */
    private static double[] removeAndSlice(final double[] values, final int begin, final int length, final double removedValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513");
        MathArrays.verifyValues(values, begin, length);
        final double[] temp;
        // BitSet(length) to indicate where the removedValue is located
        final BitSet bits = new BitSet(length);
        for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3872, _mut3873, _mut3874, _mut3875), "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3876, _mut3877, _mut3878, _mut3879, _mut3880); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513");
            if (Precision.equalsIncludingNaN(removedValue, values[i])) {
                bits.set(AOR_minus(i, begin, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3868, _mut3869, _mut3870, _mut3871));
            }
        }
        // Check if empty then create a new copy
        if (bits.isEmpty()) {
            // Nothing removed, just copy
            temp = copyOf(values, begin, length);
        } else if (ROR_equals(bits.cardinality(), length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3881, _mut3882, _mut3883, _mut3884, _mut3885)) {
            // All removed, just empty
            temp = new double[0];
        } else {
            // Some removable, so new
            temp = new double[AOR_minus(length, bits.cardinality(), "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3886, _mut3887, _mut3888, _mut3889)];
            // start index from source array (i.e values)
            int start = begin;
            // dest index in destination array(i.e temp)
            int dest = 0;
            // nextOne is the index of bit set of next one
            int nextOne = -1;
            // bitSetPtr is start index pointer of bitset
            int bitSetPtr = 0;
            while (ROR_not_equals((nextOne = bits.nextSetBit(bitSetPtr)), -1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3898, _mut3899, _mut3900, _mut3901, _mut3902)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513");
                final int lengthToCopy = AOR_minus(nextOne, bitSetPtr, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3890, _mut3891, _mut3892, _mut3893);
                System.arraycopy(values, start, temp, dest, lengthToCopy);
                dest += lengthToCopy;
                start = AOR_plus(begin, (bitSetPtr = bits.nextClearBit(nextOne)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3894, _mut3895, _mut3896, _mut3897);
            }
            // Copy any residue past start index till begin+length
            if (ROR_less(start, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3903, _mut3904, _mut3905, _mut3906), "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3907, _mut3908, _mut3909, _mut3910, _mut3911)) {
                System.arraycopy(values, start, temp, dest, AOR_minus(AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3912, _mut3913, _mut3914, _mut3915), start, "org.apache.commons.math3.stat.descriptive.rank.Percentile.removeAndSlice_513", _mut3916, _mut3917, _mut3918, _mut3919));
            }
        }
        return temp;
    }

    /**
     * Get pivots which is either cached or a newly created one
     *
     * @param values array containing the input numbers
     * @return cached pivots or a newly created one
     */
    private int[] getPivots(final double[] values) {
        final int[] pivotsHeap;
        if (values == getDataRef()) {
            pivotsHeap = cachedPivots;
        } else {
            pivotsHeap = new int[PIVOTS_HEAP_LENGTH];
            Arrays.fill(pivotsHeap, -1);
        }
        return pivotsHeap;
    }

    /**
     * Get the estimation {@link EstimationType type} used for computation.
     *
     * @return the {@code estimationType} set
     */
    public EstimationType getEstimationType() {
        return estimationType;
    }

    /**
     * Build a new instance similar to the current one except for the
     * {@link EstimationType estimation type}.
     * <p>
     * This method is intended to be used as part of a fluent-type builder
     * pattern. Building finely tune instances should be done as follows:
     * </p>
     * <pre>
     *   Percentile customized = new Percentile(quantile).
     *                           withEstimationType(estimationType).
     *                           withNaNStrategy(nanStrategy).
     *                           withKthSelector(kthSelector);
     * </pre>
     * <p>
     * If any of the {@code withXxx} method is omitted, the default value for
     * the corresponding customization parameter will be used.
     * </p>
     * @param newEstimationType estimation type for the new instance
     * @return a new instance, with changed estimation type
     * @throws NullArgumentException when newEstimationType is null
     */
    public Percentile withEstimationType(final EstimationType newEstimationType) {
        return new Percentile(quantile, newEstimationType, nanStrategy, kthSelector);
    }

    /**
     * Get the {@link NaNStrategy NaN Handling} strategy used for computation.
     * @return {@code NaN Handling} strategy set during construction
     */
    public NaNStrategy getNaNStrategy() {
        return nanStrategy;
    }

    /**
     * Build a new instance similar to the current one except for the
     * {@link NaNStrategy NaN handling} strategy.
     * <p>
     * This method is intended to be used as part of a fluent-type builder
     * pattern. Building finely tune instances should be done as follows:
     * </p>
     * <pre>
     *   Percentile customized = new Percentile(quantile).
     *                           withEstimationType(estimationType).
     *                           withNaNStrategy(nanStrategy).
     *                           withKthSelector(kthSelector);
     * </pre>
     * <p>
     * If any of the {@code withXxx} method is omitted, the default value for
     * the corresponding customization parameter will be used.
     * </p>
     * @param newNaNStrategy NaN strategy for the new instance
     * @return a new instance, with changed NaN handling strategy
     * @throws NullArgumentException when newNaNStrategy is null
     */
    public Percentile withNaNStrategy(final NaNStrategy newNaNStrategy) {
        return new Percentile(quantile, estimationType, newNaNStrategy, kthSelector);
    }

    /**
     * Get the {@link KthSelector kthSelector} used for computation.
     * @return the {@code kthSelector} set
     */
    public KthSelector getKthSelector() {
        return kthSelector;
    }

    /**
     * Get the {@link PivotingStrategyInterface} used in KthSelector for computation.
     * @return the pivoting strategy set
     */
    public PivotingStrategyInterface getPivotingStrategy() {
        return kthSelector.getPivotingStrategy();
    }

    /**
     * Build a new instance similar to the current one except for the
     * {@link KthSelector kthSelector} instance specifically set.
     * <p>
     * This method is intended to be used as part of a fluent-type builder
     * pattern. Building finely tune instances should be done as follows:
     * </p>
     * <pre>
     *   Percentile customized = new Percentile(quantile).
     *                           withEstimationType(estimationType).
     *                           withNaNStrategy(nanStrategy).
     *                           withKthSelector(newKthSelector);
     * </pre>
     * <p>
     * If any of the {@code withXxx} method is omitted, the default value for
     * the corresponding customization parameter will be used.
     * </p>
     * @param newKthSelector KthSelector for the new instance
     * @return a new instance, with changed KthSelector
     * @throws NullArgumentException when newKthSelector is null
     */
    public Percentile withKthSelector(final KthSelector newKthSelector) {
        return new Percentile(quantile, estimationType, nanStrategy, newKthSelector);
    }

    /**
     * An enum for various estimation strategies of a percentile referred in
     * <a href="http://en.wikipedia.org/wiki/Quantile">wikipedia on quantile</a>
     * with the names of enum matching those of types mentioned in
     * wikipedia.
     * <p>
     * Each enum corresponding to the specific type of estimation in wikipedia
     * implements  the respective formulae that specializes in the below aspects
     * <ul>
     * <li>An <b>index method</b> to calculate approximate index of the
     * estimate</li>
     * <li>An <b>estimate method</b> to estimate a value found at the earlier
     * computed index</li>
     * <li>A <b> minLimit</b> on the quantile for which first element of sorted
     * input is returned as an estimate </li>
     * <li>A <b> maxLimit</b> on the quantile for which last element of sorted
     * input is returned as an estimate </li>
     * </ul>
     * <p>
     * Users can now create {@link Percentile} by explicitly passing this enum;
     * such as by invoking {@link Percentile#withEstimationType(EstimationType)}
     * <p>
     * References:
     * <ol>
     * <li>
     * <a href="http://en.wikipedia.org/wiki/Quantile">Wikipedia on quantile</a>
     * </li>
     * <li>
     * <a href="https://www.amherst.edu/media/view/129116/.../Sample+Quantiles.pdf">
     * Hyndman, R. J. and Fan, Y. (1996) Sample quantiles in statistical
     * packages, American Statistician 50, 361–365</a> </li>
     * <li>
     * <a href="http://stat.ethz.ch/R-manual/R-devel/library/stats/html/quantile.html">
     * R-Manual </a></li>
     * </ol>
     */
    public enum EstimationType {

        /**
         * This is the default type used in the {@link Percentile}.This method
         * has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index    = (N+1)p\ \\
         * &amp;estimate = x_{\lceil h\,-\,1/2 \rceil} \\
         * &amp;minLimit = 0 \\
         * &amp;maxLimit = 1 \\
         * \end{align}\)
         */
        LEGACY("Legacy Apache Commons Math") {

            /**
             * {@inheritDoc}.This method in particular makes use of existing
             * Apache Commons Math style of picking up the index.
             */
            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_729");
                final double minLimit = 0d;
                final double maxLimit = 1d;
                return ROR_equals(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_729", _mut3920, _mut3921, _mut3922, _mut3923, _mut3924) ? 0 : ROR_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_729", _mut3925, _mut3926, _mut3927, _mut3928, _mut3929) ? length : AOR_multiply(p, (AOR_plus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_729", _mut3930, _mut3931, _mut3932, _mut3933)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_729", _mut3934, _mut3935, _mut3936, _mut3937);
            }
        }
        ,
        /**
         * The method R_1 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= Np + 1/2\,  \\
         * &amp;estimate= x_{\lceil h\,-\,1/2 \rceil} \\
         * &amp;minLimit = 0 \\
         * \end{align}\)
         */
        R_1("R-1") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_748");
                final double minLimit = 0d;
                return ROR_equals(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_748", _mut3938, _mut3939, _mut3940, _mut3941, _mut3942) ? 0 : AOR_plus(AOR_multiply(length, p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_748", _mut3943, _mut3944, _mut3945, _mut3946), 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_748", _mut3947, _mut3948, _mut3949, _mut3950);
            }

            /**
             * {@inheritDoc}This method in particular for R_1 uses ceil(pos-0.5)
             */
            @Override
            protected double estimate(final double[] values, final int[] pivotsHeap, final double pos, final int length, final KthSelector selector) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_757");
                return super.estimate(values, pivotsHeap, FastMath.ceil(AOR_minus(pos, 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_757", _mut3951, _mut3952, _mut3953, _mut3954)), length, selector);
            }
        }
        ,
        /**
         * The method R_2 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= Np + 1/2\, \\
         * &amp;estimate=\frac{x_{\lceil h\,-\,1/2 \rceil} +
         * x_{\lfloor h\,+\,1/2 \rfloor}}{2} \\
         * &amp;minLimit = 0 \\
         * &amp;maxLimit = 1 \\
         * \end{align}\)
         */
        R_2("R-2") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_777");
                final double minLimit = 0d;
                final double maxLimit = 1d;
                return ROR_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_777", _mut3955, _mut3956, _mut3957, _mut3958, _mut3959) ? length : ROR_equals(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_777", _mut3960, _mut3961, _mut3962, _mut3963, _mut3964) ? 0 : AOR_plus(AOR_multiply(length, p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_777", _mut3965, _mut3966, _mut3967, _mut3968), 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_777", _mut3969, _mut3970, _mut3971, _mut3972);
            }

            /**
             * {@inheritDoc}This method in particular for R_2 averages the
             * values at ceil(p+0.5) and floor(p-0.5).
             */
            @Override
            protected double estimate(final double[] values, final int[] pivotsHeap, final double pos, final int length, final KthSelector selector) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_789");
                final double low = super.estimate(values, pivotsHeap, FastMath.ceil(AOR_minus(pos, 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_789", _mut3973, _mut3974, _mut3975, _mut3976)), length, selector);
                final double high = super.estimate(values, pivotsHeap, FastMath.floor(AOR_plus(pos, 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_789", _mut3977, _mut3978, _mut3979, _mut3980)), length, selector);
                return AOR_divide((AOR_plus(low, high, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_789", _mut3981, _mut3982, _mut3983, _mut3984)), 2, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_789", _mut3985, _mut3986, _mut3987, _mut3988);
            }
        }
        ,
        /**
         * The method R_3 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= Np \\
         * &amp;estimate= x_{\lfloor h \rceil}\, \\
         * &amp;minLimit = 0.5/N \\
         * \end{align}\)
         */
        R_3("R-3") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_810");
                final double minLimit = AOR_divide(AOR_divide(1d, 2, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_810", _mut3989, _mut3990, _mut3991, _mut3992), length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_810", _mut3993, _mut3994, _mut3995, _mut3996);
                return ROR_less_equals(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_810", _mut3997, _mut3998, _mut3999, _mut4000, _mut4001) ? 0 : FastMath.rint(AOR_multiply(length, p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_810", _mut4002, _mut4003, _mut4004, _mut4005));
            }
        }
        ,
        /**
         * The method R_4 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= Np\, \\
         * &amp;estimate= x_{\lfloor h \rfloor} + (h -
         * \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = 1/N \\
         * &amp;maxLimit = 1 \\
         * \end{align}\)
         */
        R_4("R-4") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_830");
                final double minLimit = AOR_divide(1d, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_830", _mut4006, _mut4007, _mut4008, _mut4009);
                final double maxLimit = 1d;
                return ROR_less(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_830", _mut4010, _mut4011, _mut4012, _mut4013, _mut4014) ? 0 : ROR_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_830", _mut4015, _mut4016, _mut4017, _mut4018, _mut4019) ? length : AOR_multiply(length, p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_830", _mut4020, _mut4021, _mut4022, _mut4023);
            }
        }
        ,
        /**
         * The method R_5 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= Np + 1/2\\
         * &amp;estimate= x_{\lfloor h \rfloor} + (h -
         * \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = 0.5/N \\
         * &amp;maxLimit = (N-0.5)/N
         * \end{align}\)
         */
        R_5("R-5") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852");
                final double minLimit = AOR_divide(AOR_divide(1d, 2, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4024, _mut4025, _mut4026, _mut4027), length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4028, _mut4029, _mut4030, _mut4031);
                final double maxLimit = AOR_divide((AOR_minus(length, 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4032, _mut4033, _mut4034, _mut4035)), length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4036, _mut4037, _mut4038, _mut4039);
                return ROR_less(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4040, _mut4041, _mut4042, _mut4043, _mut4044) ? 0 : ROR_greater_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4045, _mut4046, _mut4047, _mut4048, _mut4049) ? length : AOR_plus(AOR_multiply(length, p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4050, _mut4051, _mut4052, _mut4053), 0.5, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_852", _mut4054, _mut4055, _mut4056, _mut4057);
            }
        }
        ,
        /**
         * The method R_6 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index= (N + 1)p \\
         * &amp;estimate= x_{\lfloor h \rfloor} + (h -
         * \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = 1/(N+1) \\
         * &amp;maxLimit = N/(N+1) \\
         * \end{align}\)
         * <p>
         * <b>Note:</b> This method computes the index in a manner very close to
         * the default Commons Math Percentile existing implementation. However
         * the difference to be noted is in picking up the limits with which
         * first element (p&lt;1(N+1)) and last elements (p&gt;N/(N+1))are done.
         * While in default case; these are done with p=0 and p=1 respectively.
         */
        R_6("R-6") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880");
                final double minLimit = AOR_divide(1d, (AOR_plus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4058, _mut4059, _mut4060, _mut4061)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4062, _mut4063, _mut4064, _mut4065);
                final double maxLimit = AOR_divide(AOR_multiply(1d, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4066, _mut4067, _mut4068, _mut4069), (AOR_plus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4070, _mut4071, _mut4072, _mut4073)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4074, _mut4075, _mut4076, _mut4077);
                return ROR_less(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4078, _mut4079, _mut4080, _mut4081, _mut4082) ? 0 : ROR_greater_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4083, _mut4084, _mut4085, _mut4086, _mut4087) ? length : AOR_multiply((AOR_plus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4088, _mut4089, _mut4090, _mut4091)), p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_880", _mut4092, _mut4093, _mut4094, _mut4095);
            }
        }
        ,
        /**
         * The method R_7 implements Microsoft Excel style computation has the
         * following formulae for index and estimates.<br>
         * \( \begin{align}
         * &amp;index = (N-1)p + 1 \\
         * &amp;estimate = x_{\lfloor h \rfloor} + (h -
         * \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = 0 \\
         * &amp;maxLimit = 1 \\
         * \end{align}\)
         */
        R_7("R-7") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903");
                final double minLimit = 0d;
                final double maxLimit = 1d;
                return ROR_equals(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903", _mut4096, _mut4097, _mut4098, _mut4099, _mut4100) ? 0 : ROR_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903", _mut4101, _mut4102, _mut4103, _mut4104, _mut4105) ? length : AOR_plus(1, AOR_multiply((AOR_minus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903", _mut4106, _mut4107, _mut4108, _mut4109)), p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903", _mut4110, _mut4111, _mut4112, _mut4113), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_903", _mut4114, _mut4115, _mut4116, _mut4117);
            }
        }
        ,
        /**
         * The method R_8 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index = (N + 1/3)p + 1/3  \\
         * &amp;estimate = x_{\lfloor h \rfloor} + (h -
         *           \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = (2/3)/(N+1/3) \\
         * &amp;maxLimit = (N-1/3)/(N+1/3) \\
         * \end{align}\)
         * <p>
         * As per Ref [2,3] this approach is most recommended as it provides
         * an approximate median-unbiased estimate regardless of distribution.
         */
        R_8("R-8") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929");
                final double minLimit = AOR_divide(AOR_multiply(2, (AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4118, _mut4119, _mut4120, _mut4121)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4122, _mut4123, _mut4124, _mut4125), (AOR_plus(length, AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4126, _mut4127, _mut4128, _mut4129), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4130, _mut4131, _mut4132, _mut4133)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4134, _mut4135, _mut4136, _mut4137);
                final double maxLimit = AOR_divide((AOR_minus(length, AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4138, _mut4139, _mut4140, _mut4141), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4142, _mut4143, _mut4144, _mut4145)), (AOR_plus(length, AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4146, _mut4147, _mut4148, _mut4149), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4150, _mut4151, _mut4152, _mut4153)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4154, _mut4155, _mut4156, _mut4157);
                return ROR_less(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4158, _mut4159, _mut4160, _mut4161, _mut4162) ? 0 : ROR_greater_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4163, _mut4164, _mut4165, _mut4166, _mut4167) ? length : AOR_plus(AOR_multiply((AOR_plus(length, AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4168, _mut4169, _mut4170, _mut4171), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4172, _mut4173, _mut4174, _mut4175)), p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4176, _mut4177, _mut4178, _mut4179), AOR_divide(1d, 3, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4180, _mut4181, _mut4182, _mut4183), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_929", _mut4184, _mut4185, _mut4186, _mut4187);
            }
        }
        ,
        /**
         * The method R_9 has the following formulae for index and estimates<br>
         * \( \begin{align}
         * &amp;index = (N + 1/4)p + 3/8\\
         * &amp;estimate = x_{\lfloor h \rfloor} + (h -
         *           \lfloor h \rfloor) (x_{\lfloor h \rfloor + 1} - x_{\lfloor h
         * \rfloor}) \\
         * &amp;minLimit = (5/8)/(N+1/4) \\
         * &amp;maxLimit = (N-3/8)/(N+1/4) \\
         * \end{align}\)
         */
        R_9("R-9") {

            @Override
            protected double index(final double p, final int length) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952");
                final double minLimit = AOR_divide(AOR_divide(5d, 8, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4188, _mut4189, _mut4190, _mut4191), (AOR_plus(length, 0.25, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4192, _mut4193, _mut4194, _mut4195)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4196, _mut4197, _mut4198, _mut4199);
                final double maxLimit = AOR_divide((AOR_minus(length, AOR_divide(3d, 8, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4200, _mut4201, _mut4202, _mut4203), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4204, _mut4205, _mut4206, _mut4207)), (AOR_plus(length, 0.25, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4208, _mut4209, _mut4210, _mut4211)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4212, _mut4213, _mut4214, _mut4215);
                return ROR_less(Double.compare(p, minLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4216, _mut4217, _mut4218, _mut4219, _mut4220) ? 0 : ROR_greater_equals(Double.compare(p, maxLimit), 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4221, _mut4222, _mut4223, _mut4224, _mut4225) ? length : AOR_plus(AOR_multiply((AOR_plus(length, 0.25, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4226, _mut4227, _mut4228, _mut4229)), p, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4230, _mut4231, _mut4232, _mut4233), AOR_divide(3d, 8, "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4234, _mut4235, _mut4236, _mut4237), "org.apache.commons.math3.stat.descriptive.rank.Percentile.index_952", _mut4238, _mut4239, _mut4240, _mut4241);
            }
        }
        ;

        /**
         * Simple name such as R-1, R-2 corresponding to those in wikipedia.
         */
        private final String name;

        /**
         * Constructor
         *
         * @param type name of estimation type as per wikipedia
         */
        EstimationType(final String type) {
            this.name = type;
        }

        /**
         * Finds the index of array that can be used as starting index to
         * {@link #estimate(double[], int[], double, int, KthSelector) estimate}
         * percentile. The calculation of index calculation is specific to each
         * {@link EstimationType}.
         *
         * @param p the p<sup>th</sup> quantile
         * @param length the total number of array elements in the work array
         * @return a computed real valued index as explained in the wikipedia
         */
        protected abstract double index(final double p, final int length);

        /**
         * Estimation based on K<sup>th</sup> selection. This may be overridden
         * in specific enums to compute slightly different estimations.
         *
         * @param work array of numbers to be used for finding the percentile
         * @param pos indicated positional index prior computed from calling
         *            {@link #index(double, int)}
         * @param pivotsHeap an earlier populated cache if exists; will be used
         * @param length size of array considered
         * @param selector a {@link KthSelector} used for pivoting during search
         * @return estimated percentile
         */
        protected double estimate(final double[] work, final int[] pivotsHeap, final double pos, final int length, final KthSelector selector) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000");
            final double fpos = FastMath.floor(pos);
            final int intPos = (int) fpos;
            final double dif = AOR_minus(pos, fpos, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4242, _mut4243, _mut4244, _mut4245);
            if (ROR_less(pos, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4246, _mut4247, _mut4248, _mut4249, _mut4250)) {
                return selector.select(work, pivotsHeap, 0);
            }
            if (ROR_greater_equals(pos, length, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4251, _mut4252, _mut4253, _mut4254, _mut4255)) {
                return selector.select(work, pivotsHeap, AOR_minus(length, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4256, _mut4257, _mut4258, _mut4259));
            }
            final double lower = selector.select(work, pivotsHeap, AOR_minus(intPos, 1, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4260, _mut4261, _mut4262, _mut4263));
            final double upper = selector.select(work, pivotsHeap, intPos);
            return AOR_plus(lower, AOR_multiply(dif, (AOR_minus(upper, lower, "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4264, _mut4265, _mut4266, _mut4267)), "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4268, _mut4269, _mut4270, _mut4271), "org.apache.commons.math3.stat.descriptive.rank.Percentile.estimate_1000", _mut4272, _mut4273, _mut4274, _mut4275);
        }

        /**
         * Evaluate method to compute the percentile for a given bounded array
         * using earlier computed pivots heap.<br>
         * This basically calls the {@link #index(double, int) index} and then
         * {@link #estimate(double[], int[], double, int, KthSelector) estimate}
         * functions to return the estimated percentile value.
         *
         * @param work array of numbers to be used for finding the percentile
         * @param pivotsHeap a prior cached heap which can speed up estimation
         * @param p the p<sup>th</sup> quantile to be computed
         * @param selector a {@link KthSelector} used for pivoting during search
         * @return estimated percentile
         * @throws OutOfRangeException if p is out of range
         * @throws NullArgumentException if work array is null
         */
        protected double evaluate(final double[] work, final int[] pivotsHeap, final double p, final KthSelector selector) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035");
            MathUtils.checkNotNull(work);
            if ((_mut4286 ? (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035", _mut4276, _mut4277, _mut4278, _mut4279, _mut4280) && ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035", _mut4281, _mut4282, _mut4283, _mut4284, _mut4285)) : (ROR_greater(p, 100, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035", _mut4276, _mut4277, _mut4278, _mut4279, _mut4280) || ROR_less_equals(p, 0, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035", _mut4281, _mut4282, _mut4283, _mut4284, _mut4285)))) {
                throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, p, 0, 100);
            }
            return estimate(work, pivotsHeap, index(AOR_divide(p, 100d, "org.apache.commons.math3.stat.descriptive.rank.Percentile.evaluate_1035", _mut4287, _mut4288, _mut4289, _mut4290), work.length), work.length, selector);
        }

        /**
         * Evaluate method to compute the percentile for a given bounded array.
         * This basically calls the {@link #index(double, int) index} and then
         * {@link #estimate(double[], int[], double, int, KthSelector) estimate}
         * functions to return the estimated percentile value. Please
         * note that this method does not make use of cached pivots.
         *
         * @param work array of numbers to be used for finding the percentile
         * @param p the p<sup>th</sup> quantile to be computed
         * @return estimated percentile
         * @param selector a {@link KthSelector} used for pivoting during search
         * @throws OutOfRangeException if length or p is out of range
         * @throws NullArgumentException if work array is null
         */
        public double evaluate(final double[] work, final double p, final KthSelector selector) {
            return this.evaluate(work, null, p, selector);
        }

        /**
         * Gets the name of the enum
         *
         * @return the name
         */
        String getName() {
            return name;
        }
    }
}
