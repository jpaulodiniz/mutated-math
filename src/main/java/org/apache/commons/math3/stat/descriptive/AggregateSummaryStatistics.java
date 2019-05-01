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
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.NullArgumentException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * An aggregator for {@code SummaryStatistics} from several data sets or
 * data set partitions.  In its simplest usage mode, the client creates an
 * instance via the zero-argument constructor, then uses
 * {@link #createContributingStatistics()} to obtain a {@code SummaryStatistics}
 * for each individual data set / partition.  The per-set statistics objects
 * are used as normal, and at any time the aggregate statistics for all the
 * contributors can be obtained from this object.
 * </p><p>
 * Clients with specialized requirements can use alternative constructors to
 * control the statistics implementations and initial values used by the
 * contributing and the internal aggregate {@code SummaryStatistics} objects.
 * </p><p>
 * A static {@link #aggregate(Collection)} method is also included that computes
 * aggregate statistics directly from a Collection of SummaryStatistics instances.
 * </p><p>
 * When {@link #createContributingStatistics()} is used to create SummaryStatistics
 * instances to be aggregated concurrently, the created instances'
 * {@link SummaryStatistics#addValue(double)} methods must synchronize on the aggregating
 * instance maintained by this class.  In multithreaded environments, if the functionality
 * provided by {@link #aggregate(Collection)} is adequate, that method should be used
 * to avoid unnecessary computation and synchronization delays.</p>
 *
 * @since 2.0
 */
public class AggregateSummaryStatistics implements StatisticalSummary, Serializable {

    @Conditional
    public static boolean _mut2454 = false, _mut2455 = false, _mut2456 = false, _mut2457 = false, _mut2458 = false, _mut2459 = false, _mut2460 = false, _mut2461 = false, _mut2462 = false, _mut2463 = false, _mut2464 = false, _mut2465 = false, _mut2466 = false, _mut2467 = false, _mut2468 = false, _mut2469 = false, _mut2470 = false, _mut2471 = false, _mut2472 = false, _mut2473 = false, _mut2474 = false, _mut2475 = false, _mut2476 = false, _mut2477 = false, _mut2478 = false, _mut2479 = false, _mut2480 = false, _mut2481 = false, _mut2482 = false, _mut2483 = false, _mut2484 = false, _mut2485 = false, _mut2486 = false, _mut2487 = false, _mut2488 = false, _mut2489 = false, _mut2490 = false, _mut2491 = false, _mut2492 = false, _mut2493 = false, _mut2494 = false, _mut2495 = false, _mut2496 = false, _mut2497 = false, _mut2498 = false, _mut2499 = false, _mut2500 = false, _mut2501 = false, _mut2502 = false, _mut2503 = false, _mut2504 = false, _mut2505 = false, _mut2506 = false, _mut2507 = false, _mut2508 = false, _mut2509 = false, _mut2510 = false, _mut2511 = false, _mut2512 = false, _mut2513 = false, _mut2514 = false, _mut2515 = false, _mut2516 = false, _mut2517 = false, _mut2518 = false, _mut2519 = false, _mut2520 = false, _mut2521 = false, _mut2522 = false, _mut2523 = false, _mut2524 = false, _mut2525 = false, _mut2526 = false, _mut2527 = false, _mut2528 = false, _mut2529 = false, _mut2530 = false, _mut2531 = false, _mut2532 = false, _mut2533 = false, _mut2534 = false, _mut2535 = false, _mut2536 = false, _mut2537 = false, _mut2538 = false, _mut2539 = false, _mut2540 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -8207112444016386906L;

    /**
     * A SummaryStatistics serving as a prototype for creating SummaryStatistics
     * contributing to this aggregate
     */
    private final SummaryStatistics statisticsPrototype;

    /**
     * The SummaryStatistics in which aggregate statistics are accumulated.
     */
    private final SummaryStatistics statistics;

    /**
     * Initializes a new AggregateSummaryStatistics with default statistics
     * implementations.
     */
    public AggregateSummaryStatistics() {
        // No try-catch or throws NAE because arg is guaranteed non-null
        this(new SummaryStatistics());
    }

    /**
     * Initializes a new AggregateSummaryStatistics with the specified statistics
     * object as a prototype for contributing statistics and for the internal
     * aggregate statistics.  This provides for customized statistics implementations
     * to be used by contributing and aggregate statistics.
     *
     * @param prototypeStatistics a {@code SummaryStatistics} serving as a
     *      prototype both for the internal aggregate statistics and for
     *      contributing statistics obtained via the
     *      {@code createContributingStatistics()} method.  Being a prototype
     *      means that other objects are initialized by copying this object's state.
     *      If {@code null}, a new, default statistics object is used.  Any statistic
     *      values in the prototype are propagated to contributing statistics
     *      objects and (once) into these aggregate statistics.
     * @throws NullArgumentException if prototypeStatistics is null
     * @see #createContributingStatistics()
     */
    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics) throws NullArgumentException {
        this(prototypeStatistics, prototypeStatistics == null ? null : new SummaryStatistics(prototypeStatistics));
    }

    /**
     * Initializes a new AggregateSummaryStatistics with the specified statistics
     * object as a prototype for contributing statistics and for the internal
     * aggregate statistics.  This provides for different statistics implementations
     * to be used by contributing and aggregate statistics and for an initial
     * state to be supplied for the aggregate statistics.
     *
     * @param prototypeStatistics a {@code SummaryStatistics} serving as a
     *      prototype both for the internal aggregate statistics and for
     *      contributing statistics obtained via the
     *      {@code createContributingStatistics()} method.  Being a prototype
     *      means that other objects are initialized by copying this object's state.
     *      If {@code null}, a new, default statistics object is used.  Any statistic
     *      values in the prototype are propagated to contributing statistics
     *      objects, but not into these aggregate statistics.
     * @param initialStatistics a {@code SummaryStatistics} to serve as the
     *      internal aggregate statistics object.  If {@code null}, a new, default
     *      statistics object is used.
     * @see #createContributingStatistics()
     */
    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics, SummaryStatistics initialStatistics) {
        this.statisticsPrototype = (prototypeStatistics == null) ? new SummaryStatistics() : prototypeStatistics;
        this.statistics = (initialStatistics == null) ? new SummaryStatistics() : initialStatistics;
    }

    /**
     * {@inheritDoc}.  This version returns the maximum over all the aggregated
     * data.
     *
     * @see StatisticalSummary#getMax()
     */
    public double getMax() {
        synchronized (statistics) {
            return statistics.getMax();
        }
    }

    /**
     * {@inheritDoc}.  This version returns the mean of all the aggregated data.
     *
     * @see StatisticalSummary#getMean()
     */
    public double getMean() {
        synchronized (statistics) {
            return statistics.getMean();
        }
    }

    /**
     * {@inheritDoc}.  This version returns the minimum over all the aggregated
     * data.
     *
     * @see StatisticalSummary#getMin()
     */
    public double getMin() {
        synchronized (statistics) {
            return statistics.getMin();
        }
    }

    /**
     * {@inheritDoc}.  This version returns a count of all the aggregated data.
     *
     * @see StatisticalSummary#getN()
     */
    public long getN() {
        synchronized (statistics) {
            return statistics.getN();
        }
    }

    /**
     * {@inheritDoc}.  This version returns the standard deviation of all the
     * aggregated data.
     *
     * @see StatisticalSummary#getStandardDeviation()
     */
    public double getStandardDeviation() {
        synchronized (statistics) {
            return statistics.getStandardDeviation();
        }
    }

    /**
     * {@inheritDoc}.  This version returns a sum of all the aggregated data.
     *
     * @see StatisticalSummary#getSum()
     */
    public double getSum() {
        synchronized (statistics) {
            return statistics.getSum();
        }
    }

    /**
     * {@inheritDoc}.  This version returns the variance of all the aggregated
     * data.
     *
     * @see StatisticalSummary#getVariance()
     */
    public double getVariance() {
        synchronized (statistics) {
            return statistics.getVariance();
        }
    }

    /**
     * Returns the sum of the logs of all the aggregated data.
     *
     * @return the sum of logs
     * @see SummaryStatistics#getSumOfLogs()
     */
    public double getSumOfLogs() {
        synchronized (statistics) {
            return statistics.getSumOfLogs();
        }
    }

    /**
     * Returns the geometric mean of all the aggregated data.
     *
     * @return the geometric mean
     * @see SummaryStatistics#getGeometricMean()
     */
    public double getGeometricMean() {
        synchronized (statistics) {
            return statistics.getGeometricMean();
        }
    }

    /**
     * Returns the sum of the squares of all the aggregated data.
     *
     * @return The sum of squares
     * @see SummaryStatistics#getSumsq()
     */
    public double getSumsq() {
        synchronized (statistics) {
            return statistics.getSumsq();
        }
    }

    /**
     * Returns a statistic related to the Second Central Moment.  Specifically,
     * what is returned is the sum of squared deviations from the sample mean
     * among the all of the aggregated data.
     *
     * @return second central moment statistic
     * @see SummaryStatistics#getSecondMoment()
     */
    public double getSecondMoment() {
        synchronized (statistics) {
            return statistics.getSecondMoment();
        }
    }

    /**
     * Return a {@link StatisticalSummaryValues} instance reporting current
     * aggregate statistics.
     *
     * @return Current values of aggregate statistics
     */
    public StatisticalSummary getSummary() {
        synchronized (statistics) {
            return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
        }
    }

    /**
     * Creates and returns a {@code SummaryStatistics} whose data will be
     * aggregated with those of this {@code AggregateSummaryStatistics}.
     *
     * @return a {@code SummaryStatistics} whose data will be aggregated with
     *      those of this {@code AggregateSummaryStatistics}.  The initial state
     *      is a copy of the configured prototype statistics.
     */
    public SummaryStatistics createContributingStatistics() {
        SummaryStatistics contributingStatistics = new AggregatingSummaryStatistics(statistics);
        // No try - catch or advertising NAE because neither argument will ever be null
        SummaryStatistics.copy(statisticsPrototype, contributingStatistics);
        return contributingStatistics;
    }

    /**
     * Computes aggregate summary statistics. This method can be used to combine statistics
     * computed over partitions or subsamples - i.e., the StatisticalSummaryValues returned
     * should contain the same values that would have been obtained by computing a single
     * StatisticalSummary over the combined dataset.
     * <p>
     * Returns null if the collection is empty or null.
     * </p>
     *
     * @param statistics collection of SummaryStatistics to aggregate
     * @return summary statistics for the combined dataset
     */
    public static StatisticalSummaryValues aggregate(Collection<? extends StatisticalSummary> statistics) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305");
        if (statistics == null) {
            return null;
        }
        Iterator<? extends StatisticalSummary> iterator = statistics.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        StatisticalSummary current = iterator.next();
        long n = current.getN();
        double min = current.getMin();
        double sum = current.getSum();
        double max = current.getMax();
        double var = current.getVariance();
        double m2 = AOR_multiply(var, (AOR_minus(n, 1d, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2454, _mut2455, _mut2456, _mut2457)), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2458, _mut2459, _mut2460, _mut2461);
        double mean = current.getMean();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305");
            current = iterator.next();
            if ((_mut2467 ? (ROR_less(current.getMin(), min, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2462, _mut2463, _mut2464, _mut2465, _mut2466) && Double.isNaN(min)) : (ROR_less(current.getMin(), min, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2462, _mut2463, _mut2464, _mut2465, _mut2466) || Double.isNaN(min)))) {
                min = current.getMin();
            }
            if ((_mut2473 ? (ROR_greater(current.getMax(), max, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2468, _mut2469, _mut2470, _mut2471, _mut2472) && Double.isNaN(max)) : (ROR_greater(current.getMax(), max, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2468, _mut2469, _mut2470, _mut2471, _mut2472) || Double.isNaN(max)))) {
                max = current.getMax();
            }
            sum += current.getSum();
            final double oldN = n;
            final double curN = current.getN();
            n += curN;
            final double meanDiff = AOR_minus(current.getMean(), mean, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2474, _mut2475, _mut2476, _mut2477);
            mean = AOR_divide(sum, n, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2478, _mut2479, _mut2480, _mut2481);
            final double curM2 = AOR_multiply(current.getVariance(), (AOR_minus(curN, 1d, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2482, _mut2483, _mut2484, _mut2485)), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2486, _mut2487, _mut2488, _mut2489);
            m2 = AOR_plus(AOR_plus(m2, curM2, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2490, _mut2491, _mut2492, _mut2493), AOR_divide(AOR_multiply(AOR_multiply(AOR_multiply(meanDiff, meanDiff, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2494, _mut2495, _mut2496, _mut2497), oldN, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2498, _mut2499, _mut2500, _mut2501), curN, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2502, _mut2503, _mut2504, _mut2505), n, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2506, _mut2507, _mut2508, _mut2509), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2510, _mut2511, _mut2512, _mut2513);
        }
        final double variance;
        if (ROR_equals(n, 0, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2514, _mut2515, _mut2516, _mut2517, _mut2518)) {
            variance = Double.NaN;
        } else if (ROR_equals(n, 1, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2519, _mut2520, _mut2521, _mut2522, _mut2523)) {
            variance = 0d;
        } else {
            variance = AOR_divide(m2, (AOR_minus(n, 1, "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2524, _mut2525, _mut2526, _mut2527)), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.aggregate_305", _mut2528, _mut2529, _mut2530, _mut2531);
        }
        return new StatisticalSummaryValues(mean, variance, n, max, min, sum);
    }

    /**
     * A SummaryStatistics that also forwards all values added to it to a second
     * {@code SummaryStatistics} for aggregation.
     *
     * @since 2.0
     */
    private static class AggregatingSummaryStatistics extends SummaryStatistics {

        /**
         * The serialization version of this class
         */
        private static final long serialVersionUID = 1L;

        /**
         * An additional SummaryStatistics into which values added to these
         * statistics (and possibly others) are aggregated
         */
        private final SummaryStatistics aggregateStatistics;

        /**
         * Initializes a new AggregatingSummaryStatistics with the specified
         * aggregate statistics object
         *
         * @param aggregateStatistics a {@code SummaryStatistics} into which
         *      values added to this statistics object should be aggregated
         */
        AggregatingSummaryStatistics(SummaryStatistics aggregateStatistics) {
            this.aggregateStatistics = aggregateStatistics;
        }

        /**
         * {@inheritDoc}.  This version adds the provided value to the configured
         * aggregate after adding it to these statistics.
         *
         * @see SummaryStatistics#addValue(double)
         */
        @Override
        public void addValue(double value) {
            super.addValue(value);
            synchronized (aggregateStatistics) {
                aggregateStatistics.addValue(value);
            }
        }

        /**
         * Returns true iff <code>object</code> is a
         * <code>SummaryStatistics</code> instance and all statistics have the
         * same values as this.
         * @param object the object to test equality against.
         * @return true if object equals this
         */
        @Override
        public boolean equals(Object object) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.equals_400");
            if (object == this) {
                return true;
            }
            if (object instanceof AggregatingSummaryStatistics == false) {
                return false;
            }
            AggregatingSummaryStatistics stat = (AggregatingSummaryStatistics) object;
            return (_mut2532 ? (super.equals(stat) || aggregateStatistics.equals(stat.aggregateStatistics)) : (super.equals(stat) && aggregateStatistics.equals(stat.aggregateStatistics)));
        }

        /**
         * Returns hash code based on values of statistics
         * @return hash code
         */
        @Override
        public int hashCode() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.hashCode_417");
            return AOR_plus(AOR_plus(123, super.hashCode(), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.hashCode_417", _mut2533, _mut2534, _mut2535, _mut2536), aggregateStatistics.hashCode(), "org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics.hashCode_417", _mut2537, _mut2538, _mut2539, _mut2540);
        }
    }
}
