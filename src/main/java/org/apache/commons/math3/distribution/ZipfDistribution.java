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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Zipf distribution.
 * <p>
 * <strong>Parameters:</strong>
 * For a random variable {@code X} whose values are distributed according to this
 * distribution, the probability mass function is given by
 * <pre>
 *   P(X = k) = H(N,s) * 1 / k^s    for {@code k = 1,2,...,N}.
 * </pre>
 * {@code H(N,s)} is the normalizing constant
 * which corresponds to the generalized harmonic number of order N of s.
 * <p>
 * <ul>
 * <li>{@code N} is the number of elements</li>
 * <li>{@code s} is the exponent</li>
 * </ul>
 * @see <a href="https://en.wikipedia.org/wiki/Zipf's_law">Zipf's law (Wikipedia)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Harmonic_number#Generalized_harmonic_numbers">Generalized harmonic numbers</a>
 */
public class ZipfDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut54737 = false, _mut54738 = false, _mut54739 = false, _mut54740 = false, _mut54741 = false, _mut54742 = false, _mut54743 = false, _mut54744 = false, _mut54745 = false, _mut54746 = false, _mut54747 = false, _mut54748 = false, _mut54749 = false, _mut54750 = false, _mut54751 = false, _mut54752 = false, _mut54753 = false, _mut54754 = false, _mut54755 = false, _mut54756 = false, _mut54757 = false, _mut54758 = false, _mut54759 = false, _mut54760 = false, _mut54761 = false, _mut54762 = false, _mut54763 = false, _mut54764 = false, _mut54765 = false, _mut54766 = false, _mut54767 = false, _mut54768 = false, _mut54769 = false, _mut54770 = false, _mut54771 = false, _mut54772 = false, _mut54773 = false, _mut54774 = false, _mut54775 = false, _mut54776 = false, _mut54777 = false, _mut54778 = false, _mut54779 = false, _mut54780 = false, _mut54781 = false, _mut54782 = false, _mut54783 = false, _mut54784 = false, _mut54785 = false, _mut54786 = false, _mut54787 = false, _mut54788 = false, _mut54789 = false, _mut54790 = false, _mut54791 = false, _mut54792 = false, _mut54793 = false, _mut54794 = false, _mut54795 = false, _mut54796 = false, _mut54797 = false, _mut54798 = false, _mut54799 = false, _mut54800 = false, _mut54801 = false, _mut54802 = false, _mut54803 = false, _mut54804 = false, _mut54805 = false, _mut54806 = false, _mut54807 = false, _mut54808 = false, _mut54809 = false, _mut54810 = false, _mut54811 = false, _mut54812 = false, _mut54813 = false, _mut54814 = false, _mut54815 = false, _mut54816 = false, _mut54817 = false, _mut54818 = false, _mut54819 = false, _mut54820 = false, _mut54821 = false, _mut54822 = false, _mut54823 = false, _mut54824 = false, _mut54825 = false, _mut54826 = false, _mut54827 = false, _mut54828 = false, _mut54829 = false, _mut54830 = false, _mut54831 = false, _mut54832 = false, _mut54833 = false, _mut54834 = false, _mut54835 = false, _mut54836 = false, _mut54837 = false, _mut54838 = false, _mut54839 = false, _mut54840 = false, _mut54841 = false, _mut54842 = false, _mut54843 = false, _mut54844 = false, _mut54845 = false, _mut54846 = false, _mut54847 = false, _mut54848 = false, _mut54849 = false, _mut54850 = false, _mut54851 = false, _mut54852 = false, _mut54853 = false, _mut54854 = false, _mut54855 = false, _mut54856 = false, _mut54857 = false, _mut54858 = false, _mut54859 = false, _mut54860 = false, _mut54861 = false, _mut54862 = false, _mut54863 = false, _mut54864 = false, _mut54865 = false, _mut54866 = false, _mut54867 = false, _mut54868 = false, _mut54869 = false, _mut54870 = false, _mut54871 = false, _mut54872 = false, _mut54873 = false, _mut54874 = false, _mut54875 = false, _mut54876 = false, _mut54877 = false, _mut54878 = false, _mut54879 = false, _mut54880 = false, _mut54881 = false, _mut54882 = false, _mut54883 = false, _mut54884 = false, _mut54885 = false, _mut54886 = false, _mut54887 = false, _mut54888 = false, _mut54889 = false, _mut54890 = false, _mut54891 = false, _mut54892 = false, _mut54893 = false, _mut54894 = false, _mut54895 = false, _mut54896 = false, _mut54897 = false, _mut54898 = false, _mut54899 = false, _mut54900 = false, _mut54901 = false, _mut54902 = false, _mut54903 = false, _mut54904 = false, _mut54905 = false, _mut54906 = false, _mut54907 = false, _mut54908 = false, _mut54909 = false, _mut54910 = false, _mut54911 = false, _mut54912 = false, _mut54913 = false, _mut54914 = false, _mut54915 = false, _mut54916 = false, _mut54917 = false, _mut54918 = false, _mut54919 = false, _mut54920 = false, _mut54921 = false, _mut54922 = false, _mut54923 = false, _mut54924 = false, _mut54925 = false, _mut54926 = false, _mut54927 = false, _mut54928 = false, _mut54929 = false, _mut54930 = false, _mut54931 = false, _mut54932 = false, _mut54933 = false, _mut54934 = false, _mut54935 = false, _mut54936 = false, _mut54937 = false, _mut54938 = false, _mut54939 = false, _mut54940 = false, _mut54941 = false, _mut54942 = false, _mut54943 = false, _mut54944 = false, _mut54945 = false, _mut54946 = false, _mut54947 = false, _mut54948 = false, _mut54949 = false, _mut54950 = false, _mut54951 = false, _mut54952 = false, _mut54953 = false, _mut54954 = false, _mut54955 = false, _mut54956 = false, _mut54957 = false, _mut54958 = false, _mut54959 = false, _mut54960 = false, _mut54961 = false, _mut54962 = false, _mut54963 = false, _mut54964 = false, _mut54965 = false, _mut54966 = false, _mut54967 = false, _mut54968 = false, _mut54969 = false, _mut54970 = false, _mut54971 = false, _mut54972 = false, _mut54973 = false, _mut54974 = false, _mut54975 = false, _mut54976 = false, _mut54977 = false, _mut54978 = false, _mut54979 = false, _mut54980 = false, _mut54981 = false, _mut54982 = false, _mut54983 = false, _mut54984 = false, _mut54985 = false, _mut54986 = false, _mut54987 = false, _mut54988 = false, _mut54989 = false, _mut54990 = false, _mut54991 = false, _mut54992 = false, _mut54993 = false, _mut54994 = false, _mut54995 = false, _mut54996 = false, _mut54997 = false, _mut54998 = false, _mut54999 = false, _mut55000 = false, _mut55001 = false, _mut55002 = false, _mut55003 = false, _mut55004 = false, _mut55005 = false, _mut55006 = false, _mut55007 = false, _mut55008 = false, _mut55009 = false, _mut55010 = false, _mut55011 = false, _mut55012 = false, _mut55013 = false, _mut55014 = false, _mut55015 = false, _mut55016 = false, _mut55017 = false, _mut55018 = false, _mut55019 = false, _mut55020 = false, _mut55021 = false, _mut55022 = false, _mut55023 = false, _mut55024 = false, _mut55025 = false, _mut55026 = false, _mut55027 = false, _mut55028 = false, _mut55029 = false, _mut55030 = false, _mut55031 = false, _mut55032 = false, _mut55033 = false, _mut55034 = false, _mut55035 = false, _mut55036 = false, _mut55037 = false, _mut55038 = false, _mut55039 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -140627372283420404L;

    /**
     * Number of elements.
     */
    private final int numberOfElements;

    /**
     * Exponent parameter of the distribution.
     */
    private final double exponent;

    /**
     * Cached numerical mean
     */
    private double numericalMean = Double.NaN;

    /**
     * Whether or not the numerical mean has been calculated
     */
    private boolean numericalMeanIsCalculated = false;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * The sampler to be used for the sample() method
     */
    private transient ZipfRejectionInversionSampler sampler;

    /**
     * Create a new Zipf distribution with the given number of elements and
     * exponent.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     * @exception NotStrictlyPositiveException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     */
    public ZipfDistribution(final int numberOfElements, final double exponent) {
        this(new Well19937c(), numberOfElements, exponent);
    }

    /**
     * Creates a Zipf distribution.
     *
     * @param rng Random number generator.
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     * @exception NotStrictlyPositiveException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     * @since 3.1
     */
    public ZipfDistribution(RandomGenerator rng, int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.ZipfDistribution_93");
        if (ROR_less_equals(numberOfElements, 0, "org.apache.commons.math3.distribution.ZipfDistribution.ZipfDistribution_93", _mut54737, _mut54738, _mut54739, _mut54740, _mut54741)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, numberOfElements);
        }
        if (ROR_less_equals(exponent, 0, "org.apache.commons.math3.distribution.ZipfDistribution.ZipfDistribution_93", _mut54742, _mut54743, _mut54744, _mut54745, _mut54746)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, exponent);
        }
        this.numberOfElements = numberOfElements;
        this.exponent = exponent;
    }

    /**
     * Get the number of elements (e.g. corpus size) for the distribution.
     *
     * @return the number of elements
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * Get the exponent characterizing the distribution.
     *
     * @return the exponent
     */
    public double getExponent() {
        return exponent;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.probability_131");
        if ((_mut54757 ? (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54747, _mut54748, _mut54749, _mut54750, _mut54751) && ROR_greater(x, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54752, _mut54753, _mut54754, _mut54755, _mut54756)) : (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54747, _mut54748, _mut54749, _mut54750, _mut54751) || ROR_greater(x, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54752, _mut54753, _mut54754, _mut54755, _mut54756)))) {
            return 0.0;
        }
        return AOR_divide((AOR_divide(1.0, FastMath.pow(x, exponent), "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54758, _mut54759, _mut54760, _mut54761)), generalizedHarmonic(numberOfElements, exponent), "org.apache.commons.math3.distribution.ZipfDistribution.probability_131", _mut54762, _mut54763, _mut54764, _mut54765);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140");
        if ((_mut54776 ? (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54766, _mut54767, _mut54768, _mut54769, _mut54770) && ROR_greater(x, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54771, _mut54772, _mut54773, _mut54774, _mut54775)) : (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54766, _mut54767, _mut54768, _mut54769, _mut54770) || ROR_greater(x, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54771, _mut54772, _mut54773, _mut54774, _mut54775)))) {
            return Double.NEGATIVE_INFINITY;
        }
        return AOR_minus(AOR_multiply(-FastMath.log(x), exponent, "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54777, _mut54778, _mut54779, _mut54780), FastMath.log(generalizedHarmonic(numberOfElements, exponent)), "org.apache.commons.math3.distribution.ZipfDistribution.logProbability_140", _mut54781, _mut54782, _mut54783, _mut54784);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.cumulativeProbability_150");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.ZipfDistribution.cumulativeProbability_150", _mut54785, _mut54786, _mut54787, _mut54788, _mut54789)) {
            return 0.0;
        } else if (ROR_greater_equals(x, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.cumulativeProbability_150", _mut54790, _mut54791, _mut54792, _mut54793, _mut54794)) {
            return 1.0;
        }
        return AOR_divide(generalizedHarmonic(x, exponent), generalizedHarmonic(numberOfElements, exponent), "org.apache.commons.math3.distribution.ZipfDistribution.cumulativeProbability_150", _mut54795, _mut54796, _mut54797, _mut54798);
    }

    /**
     * {@inheritDoc}
     *
     * For number of elements {@code N} and exponent {@code s}, the mean is
     * {@code Hs1 / Hs}, where
     * <ul>
     *  <li>{@code Hs1 = generalizedHarmonic(N, s - 1)},</li>
     *  <li>{@code Hs = generalizedHarmonic(N, s)}.</li>
     * </ul>
     */
    public double getNumericalMean() {
        if (!numericalMeanIsCalculated) {
            numericalMean = calculateNumericalMean();
            numericalMeanIsCalculated = true;
        }
        return numericalMean;
    }

    /**
     * Used by {@link #getNumericalMean()}.
     *
     * @return the mean of this distribution
     */
    protected double calculateNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalMean_183");
        final int N = getNumberOfElements();
        final double s = getExponent();
        final double Hs1 = generalizedHarmonic(N, AOR_minus(s, 1, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalMean_183", _mut54799, _mut54800, _mut54801, _mut54802));
        final double Hs = generalizedHarmonic(N, s);
        return AOR_divide(Hs1, Hs, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalMean_183", _mut54803, _mut54804, _mut54805, _mut54806);
    }

    /**
     * {@inheritDoc}
     *
     * For number of elements {@code N} and exponent {@code s}, the mean is
     * {@code (Hs2 / Hs) - (Hs1^2 / Hs^2)}, where
     * <ul>
     *  <li>{@code Hs2 = generalizedHarmonic(N, s - 2)},</li>
     *  <li>{@code Hs1 = generalizedHarmonic(N, s - 1)},</li>
     *  <li>{@code Hs = generalizedHarmonic(N, s)}.</li>
     * </ul>
     */
    public double getNumericalVariance() {
        if (!numericalVarianceIsCalculated) {
            numericalVariance = calculateNumericalVariance();
            numericalVarianceIsCalculated = true;
        }
        return numericalVariance;
    }

    /**
     * Used by {@link #getNumericalVariance()}.
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217");
        final int N = getNumberOfElements();
        final double s = getExponent();
        final double Hs2 = generalizedHarmonic(N, AOR_minus(s, 2, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54807, _mut54808, _mut54809, _mut54810));
        final double Hs1 = generalizedHarmonic(N, AOR_minus(s, 1, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54811, _mut54812, _mut54813, _mut54814));
        final double Hs = generalizedHarmonic(N, s);
        return AOR_minus((AOR_divide(Hs2, Hs, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54815, _mut54816, _mut54817, _mut54818)), (AOR_divide((AOR_multiply(Hs1, Hs1, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54819, _mut54820, _mut54821, _mut54822)), (AOR_multiply(Hs, Hs, "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54823, _mut54824, _mut54825, _mut54826)), "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54827, _mut54828, _mut54829, _mut54830)), "org.apache.commons.math3.distribution.ZipfDistribution.calculateNumericalVariance_217", _mut54831, _mut54832, _mut54833, _mut54834);
    }

    /**
     * Calculates the Nth generalized harmonic number. See
     * <a href="http://mathworld.wolfram.com/HarmonicSeries.html">Harmonic
     * Series</a>.
     *
     * @param n Term in the series to calculate (must be larger than 1)
     * @param m Exponent (special case {@code m = 1} is the harmonic series).
     * @return the n<sup>th</sup> generalized harmonic number.
     */
    private double generalizedHarmonic(final int n, final double m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.generalizedHarmonic_237");
        double value = 0;
        for (int k = n; ROR_greater(k, 0, "org.apache.commons.math3.distribution.ZipfDistribution.generalizedHarmonic_237", _mut54839, _mut54840, _mut54841, _mut54842, _mut54843); --k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.generalizedHarmonic_237");
            value += AOR_divide(1.0, FastMath.pow(k, m), "org.apache.commons.math3.distribution.ZipfDistribution.generalizedHarmonic_237", _mut54835, _mut54836, _mut54837, _mut54838);
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 1 no matter the parameters.
     *
     * @return lower bound of the support (always 1)
     */
    public int getSupportLowerBound() {
        return 1;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is the number of elements.
     *
     * @return upper bound of the support
     */
    public int getSupportUpperBound() {
        return getNumberOfElements();
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sample() {
        if (sampler == null) {
            sampler = new ZipfRejectionInversionSampler(numberOfElements, exponent);
        }
        return sampler.sample(random);
    }

    /**
     * Utility class implementing a rejection inversion sampling method for a discrete,
     * bounded Zipf distribution that is based on the method described in
     * <p>
     * Wolfgang Hörmann and Gerhard Derflinger
     * "Rejection-inversion to generate variates from monotone discrete distributions."
     * ACM Transactions on Modeling and Computer Simulation (TOMACS) 6.3 (1996): 169-184.
     * <p>
     * The paper describes an algorithm for exponents larger than 1 (Algorithm ZRI).
     * The original method uses {@code H(x) := (v + x)^(1 - q) / (1 - q)}
     * as the integral of the hat function. This function is undefined for
     * q = 1, which is the reason for the limitation of the exponent.
     * If instead the integral function
     * {@code H(x) := ((v + x)^(1 - q) - 1) / (1 - q)} is used,
     * for which a meaningful limit exists for q = 1,
     * the method works for all positive exponents.
     * <p>
     * The following implementation uses v := 0 and generates integral numbers
     * in the range [1, numberOfElements]. This is different to the original method
     * where v is defined to be positive and numbers are taken from [0, i_max].
     * This explains why the implementation looks slightly different.
     *
     * @since 3.6
     */
    static final class ZipfRejectionInversionSampler {

        /**
         * Exponent parameter of the distribution.
         */
        private final double exponent;

        /**
         * Number of elements.
         */
        private final int numberOfElements;

        /**
         * Constant equal to {@code hIntegral(1.5) - 1}.
         */
        private final double hIntegralX1;

        /**
         * Constant equal to {@code hIntegral(numberOfElements + 0.5)}.
         */
        private final double hIntegralNumberOfElements;

        /**
         * Constant equal to {@code 2 - hIntegralInverse(hIntegral(2.5) - h(2)}.
         */
        private final double s;

        /**
         * Simple constructor.
         * @param numberOfElements number of elements
         * @param exponent exponent parameter of the distribution
         */
        ZipfRejectionInversionSampler(final int numberOfElements, final double exponent) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler_330");
            this.exponent = exponent;
            this.numberOfElements = numberOfElements;
            this.hIntegralX1 = AOR_minus(hIntegral(1.5), 1d, "org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler_330", _mut54844, _mut54845, _mut54846, _mut54847);
            this.hIntegralNumberOfElements = hIntegral(AOR_plus(numberOfElements, 0.5, "org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler_330", _mut54848, _mut54849, _mut54850, _mut54851));
            this.s = AOR_minus(2d, hIntegralInverse(AOR_minus(hIntegral(2.5), h(2), "org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler_330", _mut54852, _mut54853, _mut54854, _mut54855)), "org.apache.commons.math3.distribution.ZipfDistribution.ZipfRejectionInversionSampler_330", _mut54856, _mut54857, _mut54858, _mut54859);
        }

        /**
         * Generate one integral number in the range [1, numberOfElements].
         * @param random random generator to use
         * @return generated integral number in the range [1, numberOfElements]
         */
        int sample(final RandomGenerator random) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.sample_342");
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.sample_342");
                final double u = AOR_plus(hIntegralNumberOfElements, AOR_multiply(random.nextDouble(), (AOR_minus(hIntegralX1, hIntegralNumberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54860, _mut54861, _mut54862, _mut54863)), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54864, _mut54865, _mut54866, _mut54867), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54868, _mut54869, _mut54870, _mut54871);
                double x = hIntegralInverse(u);
                int k = (int) (AOR_plus(x, 0.5, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54872, _mut54873, _mut54874, _mut54875));
                // (k could be outside due to numerical inaccuracies)
                if (ROR_less(k, 1, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54876, _mut54877, _mut54878, _mut54879, _mut54880)) {
                    k = 1;
                } else if (ROR_greater(k, numberOfElements, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54881, _mut54882, _mut54883, _mut54884, _mut54885)) {
                    k = numberOfElements;
                }
                if ((_mut54908 ? (ROR_less_equals(AOR_minus(k, x, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54886, _mut54887, _mut54888, _mut54889), s, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54890, _mut54891, _mut54892, _mut54893, _mut54894) && ROR_greater_equals(u, AOR_minus(hIntegral(AOR_plus(k, 0.5, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54895, _mut54896, _mut54897, _mut54898)), h(k), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54899, _mut54900, _mut54901, _mut54902), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54903, _mut54904, _mut54905, _mut54906, _mut54907)) : (ROR_less_equals(AOR_minus(k, x, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54886, _mut54887, _mut54888, _mut54889), s, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54890, _mut54891, _mut54892, _mut54893, _mut54894) || ROR_greater_equals(u, AOR_minus(hIntegral(AOR_plus(k, 0.5, "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54895, _mut54896, _mut54897, _mut54898)), h(k), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54899, _mut54900, _mut54901, _mut54902), "org.apache.commons.math3.distribution.ZipfDistribution.sample_342", _mut54903, _mut54904, _mut54905, _mut54906, _mut54907)))) {
                    return k;
                }
            }
        }

        /**
         * {@code H(x) :=}
         * <ul>
         * <li>{@code (x^(1-exponent) - 1)/(1 - exponent)}, if {@code exponent != 1}</li>
         * <li>{@code log(x)}, if {@code exponent == 1}</li>
         * </ul>
         * H(x) is an integral function of h(x),
         * the derivative of H(x) is h(x).
         *
         * @param x free parameter
         * @return {@code H(x)}
         */
        private double hIntegral(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.hIntegral_423");
            final double logX = FastMath.log(x);
            return AOR_multiply(helper2(AOR_multiply((AOR_minus(1d, exponent, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegral_423", _mut54909, _mut54910, _mut54911, _mut54912)), logX, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegral_423", _mut54913, _mut54914, _mut54915, _mut54916)), logX, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegral_423", _mut54917, _mut54918, _mut54919, _mut54920);
        }

        /**
         * {@code h(x) := 1/x^exponent}
         *
         * @param x free parameter
         * @return h(x)
         */
        private double h(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.h_434");
            return FastMath.exp(AOR_multiply(-exponent, FastMath.log(x), "org.apache.commons.math3.distribution.ZipfDistribution.h_434", _mut54921, _mut54922, _mut54923, _mut54924));
        }

        /**
         * The inverse function of H(x).
         *
         * @param x free parameter
         * @return y for which {@code H(y) = x}
         */
        private double hIntegralInverse(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.hIntegralInverse_444");
            double t = AOR_multiply(x, (AOR_minus(1d, exponent, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegralInverse_444", _mut54925, _mut54926, _mut54927, _mut54928)), "org.apache.commons.math3.distribution.ZipfDistribution.hIntegralInverse_444", _mut54929, _mut54930, _mut54931, _mut54932);
            if (ROR_less(t, -1d, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegralInverse_444", _mut54933, _mut54934, _mut54935, _mut54936, _mut54937)) {
                // t could be smaller than -1 in some rare cases due to numerical errors.
                t = -1;
            }
            return FastMath.exp(AOR_multiply(helper1(t), x, "org.apache.commons.math3.distribution.ZipfDistribution.hIntegralInverse_444", _mut54938, _mut54939, _mut54940, _mut54941));
        }

        /**
         * Helper function that calculates {@code log(1+x)/x}.
         * <p>
         * A Taylor series expansion is used, if x is close to 0.
         *
         * @param x a value larger than or equal to -1
         * @return {@code log(1+x)/x}
         */
        static double helper1(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.helper1_462");
            if (ROR_greater(FastMath.abs(x), 1e-8, "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54942, _mut54943, _mut54944, _mut54945, _mut54946)) {
                return AOR_divide(FastMath.log1p(x), x, "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54983, _mut54984, _mut54985, _mut54986);
            } else {
                return AOR_minus(1., AOR_multiply(x, (AOR_minus((AOR_divide(1., 2., "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54947, _mut54948, _mut54949, _mut54950)), AOR_multiply(x, (AOR_minus((AOR_divide(1., 3., "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54951, _mut54952, _mut54953, _mut54954)), AOR_multiply(x, (AOR_divide(1., 4., "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54955, _mut54956, _mut54957, _mut54958)), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54959, _mut54960, _mut54961, _mut54962), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54963, _mut54964, _mut54965, _mut54966)), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54967, _mut54968, _mut54969, _mut54970), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54971, _mut54972, _mut54973, _mut54974)), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54975, _mut54976, _mut54977, _mut54978), "org.apache.commons.math3.distribution.ZipfDistribution.helper1_462", _mut54979, _mut54980, _mut54981, _mut54982);
            }
        }

        /**
         * Helper function to calculate {@code (exp(x)-1)/x}.
         * <p>
         * A Taylor series expansion is used, if x is close to 0.
         *
         * @param x free parameter
         * @return {@code (exp(x)-1)/x} if x is non-zero, or 1 if x=0
         */
        static double helper2(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ZipfDistribution.helper2_479");
            if (ROR_greater(FastMath.abs(x), 1e-8, "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut54987, _mut54988, _mut54989, _mut54990, _mut54991)) {
                return AOR_divide(FastMath.expm1(x), x, "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55036, _mut55037, _mut55038, _mut55039);
            } else {
                return AOR_plus(1., AOR_multiply(AOR_multiply(x, (AOR_divide(1., 2., "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut54992, _mut54993, _mut54994, _mut54995)), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut54996, _mut54997, _mut54998, _mut54999), (AOR_plus(1., AOR_multiply(AOR_multiply(x, (AOR_divide(1., 3., "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55000, _mut55001, _mut55002, _mut55003)), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55004, _mut55005, _mut55006, _mut55007), (AOR_plus(1., AOR_multiply(x, (AOR_divide(1., 4., "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55008, _mut55009, _mut55010, _mut55011)), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55012, _mut55013, _mut55014, _mut55015), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55016, _mut55017, _mut55018, _mut55019)), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55020, _mut55021, _mut55022, _mut55023), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55024, _mut55025, _mut55026, _mut55027)), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55028, _mut55029, _mut55030, _mut55031), "org.apache.commons.math3.distribution.ZipfDistribution.helper2_479", _mut55032, _mut55033, _mut55034, _mut55035);
            }
        }
    }
}
