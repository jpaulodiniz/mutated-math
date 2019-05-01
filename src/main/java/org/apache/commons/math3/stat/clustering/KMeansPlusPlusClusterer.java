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
package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Clustering algorithm based on David Arthur and Sergei Vassilvitski k-means++ algorithm.
 * @param <T> type of the points to cluster
 * @see <a href="http://en.wikipedia.org/wiki/K-means%2B%2B">K-means++ (wikipedia)</a>
 * @since 2.0
 * @deprecated As of 3.2 (to be removed in 4.0),
 * use {@link org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer} instead
 */
@Deprecated
public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {

    @Conditional
    public static boolean _mut4709 = false, _mut4710 = false, _mut4711 = false, _mut4712 = false, _mut4713 = false, _mut4714 = false, _mut4715 = false, _mut4716 = false, _mut4717 = false, _mut4718 = false, _mut4719 = false, _mut4720 = false, _mut4721 = false, _mut4722 = false, _mut4723 = false, _mut4724 = false, _mut4725 = false, _mut4726 = false, _mut4727 = false, _mut4728 = false, _mut4729 = false, _mut4730 = false, _mut4731 = false, _mut4732 = false, _mut4733 = false, _mut4734 = false, _mut4735 = false, _mut4736 = false, _mut4737 = false, _mut4738 = false, _mut4739 = false, _mut4740 = false, _mut4741 = false, _mut4742 = false, _mut4743 = false, _mut4744 = false, _mut4745 = false, _mut4746 = false, _mut4747 = false, _mut4748 = false, _mut4749 = false, _mut4750 = false, _mut4751 = false, _mut4752 = false, _mut4753 = false, _mut4754 = false, _mut4755 = false, _mut4756 = false, _mut4757 = false, _mut4758 = false, _mut4759 = false, _mut4760 = false, _mut4761 = false, _mut4762 = false, _mut4763 = false, _mut4764 = false, _mut4765 = false, _mut4766 = false, _mut4767 = false, _mut4768 = false, _mut4769 = false, _mut4770 = false, _mut4771 = false, _mut4772 = false, _mut4773 = false, _mut4774 = false, _mut4775 = false, _mut4776 = false, _mut4777 = false, _mut4778 = false, _mut4779 = false, _mut4780 = false, _mut4781 = false, _mut4782 = false, _mut4783 = false, _mut4784 = false, _mut4785 = false, _mut4786 = false, _mut4787 = false, _mut4788 = false, _mut4789 = false, _mut4790 = false, _mut4791 = false, _mut4792 = false, _mut4793 = false, _mut4794 = false, _mut4795 = false, _mut4796 = false, _mut4797 = false, _mut4798 = false, _mut4799 = false, _mut4800 = false, _mut4801 = false, _mut4802 = false, _mut4803 = false, _mut4804 = false, _mut4805 = false, _mut4806 = false, _mut4807 = false, _mut4808 = false, _mut4809 = false, _mut4810 = false, _mut4811 = false, _mut4812 = false, _mut4813 = false, _mut4814 = false, _mut4815 = false, _mut4816 = false, _mut4817 = false, _mut4818 = false, _mut4819 = false, _mut4820 = false, _mut4821 = false, _mut4822 = false, _mut4823 = false, _mut4824 = false, _mut4825 = false, _mut4826 = false, _mut4827 = false, _mut4828 = false, _mut4829 = false, _mut4830 = false, _mut4831 = false, _mut4832 = false, _mut4833 = false, _mut4834 = false, _mut4835 = false, _mut4836 = false, _mut4837 = false, _mut4838 = false, _mut4839 = false, _mut4840 = false, _mut4841 = false;

    /**
     * Strategies to use for replacing an empty cluster.
     */
    public enum EmptyClusterStrategy {

        /**
         * Split the cluster with largest distance variance.
         */
        LARGEST_VARIANCE,
        /**
         * Split the cluster with largest number of points.
         */
        LARGEST_POINTS_NUMBER,
        /**
         * Create a cluster around the point farthest from its centroid.
         */
        FARTHEST_POINT,
        /**
         * Generate an error.
         */
        ERROR
    }

    /**
     * Random generator for choosing initial centers.
     */
    private final Random random;

    /**
     * Selected strategy for empty clusters.
     */
    private final EmptyClusterStrategy emptyStrategy;

    /**
     * Build a clusterer.
     * <p>
     * The default strategy for handling empty clusters that may appear during
     * algorithm iterations is to split the cluster with largest distance variance.
     * </p>
     * @param random random generator to use for choosing initial centers
     */
    public KMeansPlusPlusClusterer(final Random random) {
        this(random, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    /**
     * Build a clusterer.
     * @param random random generator to use for choosing initial centers
     * @param emptyStrategy strategy to use for handling empty clusters that
     * may appear during algorithm iterations
     * @since 2.2
     */
    public KMeansPlusPlusClusterer(final Random random, final EmptyClusterStrategy emptyStrategy) {
        this.random = random;
        this.emptyStrategy = emptyStrategy;
    }

    /**
     * Runs the K-means++ clustering algorithm.
     *
     * @param points the points to cluster
     * @param k the number of clusters to split the data into
     * @param numTrials number of trial runs
     * @param maxIterationsPerTrial the maximum number of iterations to run the algorithm
     *     for at each trial run.  If negative, no maximum will be used
     * @return a list of clusters containing the points
     * @throws MathIllegalArgumentException if the data points are null or the number
     *     of clusters is larger than the number of data points
     * @throws ConvergenceException if an empty cluster is encountered and the
     * {@link #emptyStrategy} is set to {@code ERROR}
     */
    public List<Cluster<T>> cluster(final Collection<T> points, final int k, int numTrials, int maxIterationsPerTrial) throws MathIllegalArgumentException, ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103");
        // at first, we have not found any clusters list yet
        List<Cluster<T>> best = null;
        double bestVarianceSum = Double.POSITIVE_INFINITY;
        // do several clustering trials
        for (int i = 0; ROR_less(i, numTrials, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103", _mut4714, _mut4715, _mut4716, _mut4717, _mut4718); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103");
            // compute a clusters list
            List<Cluster<T>> clusters = cluster(points, k, maxIterationsPerTrial);
            // compute the variance of the current list
            double varianceSum = 0.0;
            for (final Cluster<T> cluster : clusters) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103");
                if (!cluster.getPoints().isEmpty()) {
                    // compute the distance variance of the current cluster
                    final T center = cluster.getCenter();
                    final Variance stat = new Variance();
                    for (final T point : cluster.getPoints()) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103");
                        stat.increment(point.distanceFrom(center));
                    }
                    varianceSum += stat.getResult();
                }
            }
            if (ROR_less_equals(varianceSum, bestVarianceSum, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_103", _mut4709, _mut4710, _mut4711, _mut4712, _mut4713)) {
                // this one is the best we have found so far, remember it
                best = clusters;
                bestVarianceSum = varianceSum;
            }
        }
        // return the best clusters list found
        return best;
    }

    /**
     * Runs the K-means++ clustering algorithm.
     *
     * @param points the points to cluster
     * @param k the number of clusters to split the data into
     * @param maxIterations the maximum number of iterations to run the algorithm
     *     for.  If negative, no maximum will be used
     * @return a list of clusters containing the points
     * @throws MathIllegalArgumentException if the data points are null or the number
     *     of clusters is larger than the number of data points
     * @throws ConvergenceException if an empty cluster is encountered and the
     * {@link #emptyStrategy} is set to {@code ERROR}
     */
    public List<Cluster<T>> cluster(final Collection<T> points, final int k, final int maxIterations) throws MathIllegalArgumentException, ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159");
        // sanity checks
        MathUtils.checkNotNull(points);
        // number of clusters has to be smaller or equal the number of data points
        if (ROR_less(points.size(), k, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159", _mut4719, _mut4720, _mut4721, _mut4722, _mut4723)) {
            throw new NumberIsTooSmallException(points.size(), k, false);
        }
        // create the initial clusters
        List<Cluster<T>> clusters = chooseInitialCenters(points, k, random);
        // no need to initialize the array, as it will be filled with the first assignment
        int[] assignments = new int[points.size()];
        assignPointsToClusters(clusters, points, assignments);
        // iterate through updating the centers until we're done
        final int max = (ROR_less(maxIterations, 0, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159", _mut4724, _mut4725, _mut4726, _mut4727, _mut4728)) ? Integer.MAX_VALUE : maxIterations;
        for (int count = 0; ROR_less(count, max, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159", _mut4735, _mut4736, _mut4737, _mut4738, _mut4739); count++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159");
            boolean emptyCluster = false;
            List<Cluster<T>> newClusters = new ArrayList<Cluster<T>>();
            for (final Cluster<T> cluster : clusters) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159");
                final T newCenter;
                if (cluster.getPoints().isEmpty()) {
                    switch(emptyStrategy) {
                        case LARGEST_VARIANCE:
                            newCenter = getPointFromLargestVarianceCluster(clusters);
                            break;
                        case LARGEST_POINTS_NUMBER:
                            newCenter = getPointFromLargestNumberCluster(clusters);
                            break;
                        case FARTHEST_POINT:
                            newCenter = getFarthestPoint(clusters);
                            break;
                        default:
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
                    }
                    emptyCluster = true;
                } else {
                    newCenter = cluster.getCenter().centroidOf(cluster.getPoints());
                }
                newClusters.add(new Cluster<T>(newCenter));
            }
            int changes = assignPointsToClusters(newClusters, points, assignments);
            clusters = newClusters;
            // and there are no empty clusters left, return the current clusters
            if ((_mut4734 ? (ROR_equals(changes, 0, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159", _mut4729, _mut4730, _mut4731, _mut4732, _mut4733) || !emptyCluster) : (ROR_equals(changes, 0, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.cluster_159", _mut4729, _mut4730, _mut4731, _mut4732, _mut4733) && !emptyCluster))) {
                return clusters;
            }
        }
        return clusters;
    }

    /**
     * Adds the given points to the closest {@link Cluster}.
     *
     * @param <T> type of the points to cluster
     * @param clusters the {@link Cluster}s to add the points to
     * @param points the points to add to the given {@link Cluster}s
     * @param assignments points assignments to clusters
     * @return the number of points assigned to different clusters as the iteration before
     */
    private static <T extends Clusterable<T>> int assignPointsToClusters(final List<Cluster<T>> clusters, final Collection<T> points, final int[] assignments) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_227");
        int assignedDifferently = 0;
        int pointIndex = 0;
        for (final T p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_227");
            int clusterIndex = getNearestCluster(clusters, p);
            if (ROR_not_equals(clusterIndex, assignments[pointIndex], "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_227", _mut4740, _mut4741, _mut4742, _mut4743, _mut4744)) {
                assignedDifferently++;
            }
            Cluster<T> cluster = clusters.get(clusterIndex);
            cluster.addPoint(p);
            assignments[pointIndex++] = clusterIndex;
        }
        return assignedDifferently;
    }

    /**
     * Use K-means++ to choose the initial centers.
     *
     * @param <T> type of the points to cluster
     * @param points the points to choose the initial centers from
     * @param k the number of centers to choose
     * @param random random generator to use
     * @return the initial centers
     */
    private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(final Collection<T> points, final int k, final Random random) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
        // would screw up the logic of this method.
        final List<T> pointList = Collections.unmodifiableList(new ArrayList<T>(points));
        // The number of points in the list.
        final int numPoints = pointList.size();
        // elements of pointList are no longer available.
        final boolean[] taken = new boolean[numPoints];
        // The resulting list of initial centers.
        final List<Cluster<T>> resultSet = new ArrayList<Cluster<T>>();
        // Choose one center uniformly at random from among the data points.
        final int firstPointIndex = random.nextInt(numPoints);
        final T firstPoint = pointList.get(firstPointIndex);
        resultSet.add(new Cluster<T>(firstPoint));
        // Must mark it as taken
        taken[firstPointIndex] = true;
        // pointList to elements of resultSet.
        final double[] minDistSquared = new double[numPoints];
        // this is very easy.
        for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4754, _mut4755, _mut4756, _mut4757, _mut4758); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
            if (ROR_not_equals(i, firstPointIndex, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4745, _mut4746, _mut4747, _mut4748, _mut4749)) {
                // That point isn't considered
                double d = firstPoint.distanceFrom(pointList.get(i));
                minDistSquared[i] = AOR_multiply(d, d, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4750, _mut4751, _mut4752, _mut4753);
            }
        }
        while (ROR_less(resultSet.size(), k, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4812, _mut4813, _mut4814, _mut4815, _mut4816)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
            // already taken.
            double distSqSum = 0.0;
            for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4759, _mut4760, _mut4761, _mut4762, _mut4763); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
                if (!taken[i]) {
                    distSqSum += minDistSquared[i];
                }
            }
            // probability proportional to D(x)2
            final double r = AOR_multiply(random.nextDouble(), distSqSum, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4764, _mut4765, _mut4766, _mut4767);
            // The index of the next point to be added to the resultSet.
            int nextPointIndex = -1;
            // sum >= r.
            double sum = 0.0;
            for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4773, _mut4774, _mut4775, _mut4776, _mut4777); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
                if (!taken[i]) {
                    sum += minDistSquared[i];
                    if (ROR_greater_equals(sum, r, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4768, _mut4769, _mut4770, _mut4771, _mut4772)) {
                        nextPointIndex = i;
                        break;
                    }
                }
            }
            // the last available point.
            if (ROR_equals(nextPointIndex, -1, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4778, _mut4779, _mut4780, _mut4781, _mut4782)) {
                for (int i = numPoints - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4783, _mut4784, _mut4785, _mut4786, _mut4787); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
                    if (!taken[i]) {
                        nextPointIndex = i;
                        break;
                    }
                }
            }
            // We found one.
            if (ROR_greater_equals(nextPointIndex, 0, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4788, _mut4789, _mut4790, _mut4791, _mut4792)) {
                final T p = pointList.get(nextPointIndex);
                resultSet.add(new Cluster<T>(p));
                // Mark it as taken.
                taken[nextPointIndex] = true;
                if (ROR_less(resultSet.size(), k, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4793, _mut4794, _mut4795, _mut4796, _mut4797)) {
                    // the distance to the new center to do this.
                    for (int j = 0; ROR_less(j, numPoints, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4807, _mut4808, _mut4809, _mut4810, _mut4811); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255");
                        // Only have to worry about the points still not taken.
                        if (!taken[j]) {
                            double d = p.distanceFrom(pointList.get(j));
                            double d2 = AOR_multiply(d, d, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4798, _mut4799, _mut4800, _mut4801);
                            if (ROR_less(d2, minDistSquared[j], "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_255", _mut4802, _mut4803, _mut4804, _mut4805, _mut4806)) {
                                minDistSquared[j] = d2;
                            }
                        }
                    }
                }
            } else {
                // an infinite loop.
                break;
            }
        }
        return resultSet;
    }

    /**
     * Get a random point from the {@link Cluster} with the largest distance variance.
     *
     * @param clusters the {@link Cluster}s to search
     * @return a random point from the selected cluster
     * @throws ConvergenceException if clusters are all empty
     */
    private T getPointFromLargestVarianceCluster(final Collection<Cluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_382");
        double maxVariance = Double.NEGATIVE_INFINITY;
        Cluster<T> selected = null;
        for (final Cluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_382");
            if (!cluster.getPoints().isEmpty()) {
                // compute the distance variance of the current cluster
                final T center = cluster.getCenter();
                final Variance stat = new Variance();
                for (final T point : cluster.getPoints()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_382");
                    stat.increment(point.distanceFrom(center));
                }
                final double variance = stat.getResult();
                // select the cluster with the largest variance
                if (ROR_greater(variance, maxVariance, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_382", _mut4817, _mut4818, _mut4819, _mut4820, _mut4821)) {
                    maxVariance = variance;
                    selected = cluster;
                }
            }
        }
        // did we find at least one non-empty cluster ?
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
        }
        // extract a random point from the cluster
        final List<T> selectedPoints = selected.getPoints();
        return selectedPoints.remove(random.nextInt(selectedPoints.size()));
    }

    /**
     * Get a random point from the {@link Cluster} with the largest number of points
     *
     * @param clusters the {@link Cluster}s to search
     * @return a random point from the selected cluster
     * @throws ConvergenceException if clusters are all empty
     */
    private T getPointFromLargestNumberCluster(final Collection<Cluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_425");
        int maxNumber = 0;
        Cluster<T> selected = null;
        for (final Cluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_425");
            // get the number of points of the current cluster
            final int number = cluster.getPoints().size();
            // select the cluster with the largest number of points
            if (ROR_greater(number, maxNumber, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_425", _mut4822, _mut4823, _mut4824, _mut4825, _mut4826)) {
                maxNumber = number;
                selected = cluster;
            }
        }
        // did we find at least one non-empty cluster ?
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
        }
        // extract a random point from the cluster
        final List<T> selectedPoints = selected.getPoints();
        return selectedPoints.remove(random.nextInt(selectedPoints.size()));
    }

    /**
     * Get the point farthest to its cluster center
     *
     * @param clusters the {@link Cluster}s to search
     * @return point farthest to its cluster center
     * @throws ConvergenceException if clusters are all empty
     */
    private T getFarthestPoint(final Collection<Cluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getFarthestPoint_460");
        double maxDistance = Double.NEGATIVE_INFINITY;
        Cluster<T> selectedCluster = null;
        int selectedPoint = -1;
        for (final Cluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getFarthestPoint_460");
            // get the farthest point
            final T center = cluster.getCenter();
            final List<T> points = cluster.getPoints();
            for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getFarthestPoint_460", _mut4832, _mut4833, _mut4834, _mut4835, _mut4836); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getFarthestPoint_460");
                final double distance = points.get(i).distanceFrom(center);
                if (ROR_greater(distance, maxDistance, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getFarthestPoint_460", _mut4827, _mut4828, _mut4829, _mut4830, _mut4831)) {
                    maxDistance = distance;
                    selectedCluster = cluster;
                    selectedPoint = i;
                }
            }
        }
        // did we find at least one non-empty cluster ?
        if (selectedCluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
        }
        return selectedCluster.getPoints().remove(selectedPoint);
    }

    /**
     * Returns the nearest {@link Cluster} to the given point
     *
     * @param <T> type of the points to cluster
     * @param clusters the {@link Cluster}s to search
     * @param point the point to find the nearest {@link Cluster} for
     * @return the index of the nearest {@link Cluster} to the given point
     */
    private static <T extends Clusterable<T>> int getNearestCluster(final Collection<Cluster<T>> clusters, final T point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getNearestCluster_498");
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        int minCluster = 0;
        for (final Cluster<T> c : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getNearestCluster_498");
            final double distance = point.distanceFrom(c.getCenter());
            if (ROR_less(distance, minDistance, "org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.getNearestCluster_498", _mut4837, _mut4838, _mut4839, _mut4840, _mut4841)) {
                minDistance = distance;
                minCluster = clusterIndex;
            }
            clusterIndex++;
        }
        return minCluster;
    }
}
