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
package org.apache.commons.math3.ml.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Clustering algorithm based on David Arthur and Sergei Vassilvitski k-means++ algorithm.
 * @param <T> type of the points to cluster
 * @see <a href="http://en.wikipedia.org/wiki/K-means%2B%2B">K-means++ (wikipedia)</a>
 * @since 3.2
 */
public class KMeansPlusPlusClusterer<T extends Clusterable> extends Clusterer<T> {

    @Conditional
    public static boolean _mut102383 = false, _mut102384 = false, _mut102385 = false, _mut102386 = false, _mut102387 = false, _mut102388 = false, _mut102389 = false, _mut102390 = false, _mut102391 = false, _mut102392 = false, _mut102393 = false, _mut102394 = false, _mut102395 = false, _mut102396 = false, _mut102397 = false, _mut102398 = false, _mut102399 = false, _mut102400 = false, _mut102401 = false, _mut102402 = false, _mut102403 = false, _mut102404 = false, _mut102405 = false, _mut102406 = false, _mut102407 = false, _mut102408 = false, _mut102409 = false, _mut102410 = false, _mut102411 = false, _mut102412 = false, _mut102413 = false, _mut102414 = false, _mut102415 = false, _mut102416 = false, _mut102417 = false, _mut102418 = false, _mut102419 = false, _mut102420 = false, _mut102421 = false, _mut102422 = false, _mut102423 = false, _mut102424 = false, _mut102425 = false, _mut102426 = false, _mut102427 = false, _mut102428 = false, _mut102429 = false, _mut102430 = false, _mut102431 = false, _mut102432 = false, _mut102433 = false, _mut102434 = false, _mut102435 = false, _mut102436 = false, _mut102437 = false, _mut102438 = false, _mut102439 = false, _mut102440 = false, _mut102441 = false, _mut102442 = false, _mut102443 = false, _mut102444 = false, _mut102445 = false, _mut102446 = false, _mut102447 = false, _mut102448 = false, _mut102449 = false, _mut102450 = false, _mut102451 = false, _mut102452 = false, _mut102453 = false, _mut102454 = false, _mut102455 = false, _mut102456 = false, _mut102457 = false, _mut102458 = false, _mut102459 = false, _mut102460 = false, _mut102461 = false, _mut102462 = false, _mut102463 = false, _mut102464 = false, _mut102465 = false, _mut102466 = false, _mut102467 = false, _mut102468 = false, _mut102469 = false, _mut102470 = false, _mut102471 = false, _mut102472 = false, _mut102473 = false, _mut102474 = false, _mut102475 = false, _mut102476 = false, _mut102477 = false, _mut102478 = false, _mut102479 = false, _mut102480 = false, _mut102481 = false, _mut102482 = false, _mut102483 = false, _mut102484 = false, _mut102485 = false, _mut102486 = false, _mut102487 = false, _mut102488 = false, _mut102489 = false, _mut102490 = false, _mut102491 = false, _mut102492 = false, _mut102493 = false, _mut102494 = false, _mut102495 = false, _mut102496 = false, _mut102497 = false, _mut102498 = false, _mut102499 = false, _mut102500 = false, _mut102501 = false, _mut102502 = false, _mut102503 = false, _mut102504 = false, _mut102505 = false, _mut102506 = false, _mut102507 = false, _mut102508 = false, _mut102509 = false, _mut102510 = false, _mut102511 = false, _mut102512 = false, _mut102513 = false, _mut102514 = false, _mut102515 = false;

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
     * The number of clusters.
     */
    private final int k;

    /**
     * The maximum number of iterations.
     */
    private final int maxIterations;

    /**
     * Random generator for choosing initial centers.
     */
    private final RandomGenerator random;

    /**
     * Selected strategy for empty clusters.
     */
    private final EmptyClusterStrategy emptyStrategy;

    /**
     * Build a clusterer.
     * <p>
     * The default strategy for handling empty clusters that may appear during
     * algorithm iterations is to split the cluster with largest distance variance.
     * <p>
     * The euclidean distance will be used as default distance measure.
     *
     * @param k the number of clusters to split the data into
     */
    public KMeansPlusPlusClusterer(final int k) {
        this(k, -1);
    }

    /**
     * Build a clusterer.
     * <p>
     * The default strategy for handling empty clusters that may appear during
     * algorithm iterations is to split the cluster with largest distance variance.
     * <p>
     * The euclidean distance will be used as default distance measure.
     *
     * @param k the number of clusters to split the data into
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     */
    public KMeansPlusPlusClusterer(final int k, final int maxIterations) {
        this(k, maxIterations, new EuclideanDistance());
    }

    /**
     * Build a clusterer.
     * <p>
     * The default strategy for handling empty clusters that may appear during
     * algorithm iterations is to split the cluster with largest distance variance.
     *
     * @param k the number of clusters to split the data into
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     * @param measure the distance measure to use
     */
    public KMeansPlusPlusClusterer(final int k, final int maxIterations, final DistanceMeasure measure) {
        this(k, maxIterations, measure, new JDKRandomGenerator());
    }

    /**
     * Build a clusterer.
     * <p>
     * The default strategy for handling empty clusters that may appear during
     * algorithm iterations is to split the cluster with largest distance variance.
     *
     * @param k the number of clusters to split the data into
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     * @param measure the distance measure to use
     * @param random random generator to use for choosing initial centers
     */
    public KMeansPlusPlusClusterer(final int k, final int maxIterations, final DistanceMeasure measure, final RandomGenerator random) {
        this(k, maxIterations, measure, random, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    /**
     * Build a clusterer.
     *
     * @param k the number of clusters to split the data into
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     * @param measure the distance measure to use
     * @param random random generator to use for choosing initial centers
     * @param emptyStrategy strategy to use for handling empty clusters that
     * may appear during algorithm iterations
     */
    public KMeansPlusPlusClusterer(final int k, final int maxIterations, final DistanceMeasure measure, final RandomGenerator random, final EmptyClusterStrategy emptyStrategy) {
        super(measure);
        this.k = k;
        this.maxIterations = maxIterations;
        this.random = random;
        this.emptyStrategy = emptyStrategy;
    }

    /**
     * Return the number of clusters this instance will use.
     * @return the number of clusters
     */
    public int getK() {
        return k;
    }

    /**
     * Returns the maximum number of iterations this instance will use.
     * @return the maximum number of iterations, or -1 if no maximum is set
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Returns the random generator this instance will use.
     * @return the random generator
     */
    public RandomGenerator getRandomGenerator() {
        return random;
    }

    /**
     * Returns the {@link EmptyClusterStrategy} used by this instance.
     * @return the {@link EmptyClusterStrategy}
     */
    public EmptyClusterStrategy getEmptyClusterStrategy() {
        return emptyStrategy;
    }

    /**
     * Runs the K-means++ clustering algorithm.
     *
     * @param points the points to cluster
     * @return a list of clusters containing the points
     * @throws MathIllegalArgumentException if the data points are null or the number
     *     of clusters is larger than the number of data points
     * @throws ConvergenceException if an empty cluster is encountered and the
     * {@link #emptyStrategy} is set to {@code ERROR}
     */
    @Override
    public List<CentroidCluster<T>> cluster(final Collection<T> points) throws MathIllegalArgumentException, ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195");
        // sanity checks
        MathUtils.checkNotNull(points);
        // number of clusters has to be smaller or equal the number of data points
        if (ROR_less(points.size(), k, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195", _mut102383, _mut102384, _mut102385, _mut102386, _mut102387)) {
            throw new NumberIsTooSmallException(points.size(), k, false);
        }
        // create the initial clusters
        List<CentroidCluster<T>> clusters = chooseInitialCenters(points);
        // no need to initialize the array, as it will be filled with the first assignment
        int[] assignments = new int[points.size()];
        assignPointsToClusters(clusters, points, assignments);
        // iterate through updating the centers until we're done
        final int max = (ROR_less(maxIterations, 0, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195", _mut102388, _mut102389, _mut102390, _mut102391, _mut102392)) ? Integer.MAX_VALUE : maxIterations;
        for (int count = 0; ROR_less(count, max, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195", _mut102399, _mut102400, _mut102401, _mut102402, _mut102403); count++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195");
            boolean emptyCluster = false;
            List<CentroidCluster<T>> newClusters = new ArrayList<CentroidCluster<T>>();
            for (final CentroidCluster<T> cluster : clusters) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195");
                final Clusterable newCenter;
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
                    newCenter = centroidOf(cluster.getPoints(), cluster.getCenter().getPoint().length);
                }
                newClusters.add(new CentroidCluster<T>(newCenter));
            }
            int changes = assignPointsToClusters(newClusters, points, assignments);
            clusters = newClusters;
            // and there are no empty clusters left, return the current clusters
            if ((_mut102398 ? (ROR_equals(changes, 0, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195", _mut102393, _mut102394, _mut102395, _mut102396, _mut102397) || !emptyCluster) : (ROR_equals(changes, 0, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.cluster_195", _mut102393, _mut102394, _mut102395, _mut102396, _mut102397) && !emptyCluster))) {
                return clusters;
            }
        }
        return clusters;
    }

    /**
     * Adds the given points to the closest {@link Cluster}.
     *
     * @param clusters the {@link Cluster}s to add the points to
     * @param points the points to add to the given {@link Cluster}s
     * @param assignments points assignments to clusters
     * @return the number of points assigned to different clusters as the iteration before
     */
    private int assignPointsToClusters(final List<CentroidCluster<T>> clusters, final Collection<T> points, final int[] assignments) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_262");
        int assignedDifferently = 0;
        int pointIndex = 0;
        for (final T p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_262");
            int clusterIndex = getNearestCluster(clusters, p);
            if (ROR_not_equals(clusterIndex, assignments[pointIndex], "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.assignPointsToClusters_262", _mut102404, _mut102405, _mut102406, _mut102407, _mut102408)) {
                assignedDifferently++;
            }
            CentroidCluster<T> cluster = clusters.get(clusterIndex);
            cluster.addPoint(p);
            assignments[pointIndex++] = clusterIndex;
        }
        return assignedDifferently;
    }

    /**
     * Use K-means++ to choose the initial centers.
     *
     * @param points the points to choose the initial centers from
     * @return the initial centers
     */
    private List<CentroidCluster<T>> chooseInitialCenters(final Collection<T> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
        // would screw up the logic of this method.
        final List<T> pointList = Collections.unmodifiableList(new ArrayList<T>(points));
        // The number of points in the list.
        final int numPoints = pointList.size();
        // elements of pointList are no longer available.
        final boolean[] taken = new boolean[numPoints];
        // The resulting list of initial centers.
        final List<CentroidCluster<T>> resultSet = new ArrayList<CentroidCluster<T>>();
        // Choose one center uniformly at random from among the data points.
        final int firstPointIndex = random.nextInt(numPoints);
        final T firstPoint = pointList.get(firstPointIndex);
        resultSet.add(new CentroidCluster<T>(firstPoint));
        // Must mark it as taken
        taken[firstPointIndex] = true;
        // pointList to elements of resultSet.
        final double[] minDistSquared = new double[numPoints];
        // this is very easy.
        for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102418, _mut102419, _mut102420, _mut102421, _mut102422); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
            if (ROR_not_equals(i, firstPointIndex, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102409, _mut102410, _mut102411, _mut102412, _mut102413)) {
                // That point isn't considered
                double d = distance(firstPoint, pointList.get(i));
                minDistSquared[i] = AOR_multiply(d, d, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102414, _mut102415, _mut102416, _mut102417);
            }
        }
        while (ROR_less(resultSet.size(), k, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102476, _mut102477, _mut102478, _mut102479, _mut102480)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
            // already taken.
            double distSqSum = 0.0;
            for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102423, _mut102424, _mut102425, _mut102426, _mut102427); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
                if (!taken[i]) {
                    distSqSum += minDistSquared[i];
                }
            }
            // probability proportional to D(x)2
            final double r = AOR_multiply(random.nextDouble(), distSqSum, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102428, _mut102429, _mut102430, _mut102431);
            // The index of the next point to be added to the resultSet.
            int nextPointIndex = -1;
            // sum >= r.
            double sum = 0.0;
            for (int i = 0; ROR_less(i, numPoints, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102437, _mut102438, _mut102439, _mut102440, _mut102441); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
                if (!taken[i]) {
                    sum += minDistSquared[i];
                    if (ROR_greater_equals(sum, r, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102432, _mut102433, _mut102434, _mut102435, _mut102436)) {
                        nextPointIndex = i;
                        break;
                    }
                }
            }
            // the last available point.
            if (ROR_equals(nextPointIndex, -1, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102442, _mut102443, _mut102444, _mut102445, _mut102446)) {
                for (int i = numPoints - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102447, _mut102448, _mut102449, _mut102450, _mut102451); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
                    if (!taken[i]) {
                        nextPointIndex = i;
                        break;
                    }
                }
            }
            // We found one.
            if (ROR_greater_equals(nextPointIndex, 0, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102452, _mut102453, _mut102454, _mut102455, _mut102456)) {
                final T p = pointList.get(nextPointIndex);
                resultSet.add(new CentroidCluster<T>(p));
                // Mark it as taken.
                taken[nextPointIndex] = true;
                if (ROR_less(resultSet.size(), k, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102457, _mut102458, _mut102459, _mut102460, _mut102461)) {
                    // the distance to the new center to do this.
                    for (int j = 0; ROR_less(j, numPoints, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102471, _mut102472, _mut102473, _mut102474, _mut102475); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287");
                        // Only have to worry about the points still not taken.
                        if (!taken[j]) {
                            double d = distance(p, pointList.get(j));
                            double d2 = AOR_multiply(d, d, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102462, _mut102463, _mut102464, _mut102465);
                            if (ROR_less(d2, minDistSquared[j], "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.chooseInitialCenters_287", _mut102466, _mut102467, _mut102468, _mut102469, _mut102470)) {
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
    private T getPointFromLargestVarianceCluster(final Collection<CentroidCluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_413");
        double maxVariance = Double.NEGATIVE_INFINITY;
        Cluster<T> selected = null;
        for (final CentroidCluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_413");
            if (!cluster.getPoints().isEmpty()) {
                // compute the distance variance of the current cluster
                final Clusterable center = cluster.getCenter();
                final Variance stat = new Variance();
                for (final T point : cluster.getPoints()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_413");
                    stat.increment(distance(point, center));
                }
                final double variance = stat.getResult();
                // select the cluster with the largest variance
                if (ROR_greater(variance, maxVariance, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestVarianceCluster_413", _mut102481, _mut102482, _mut102483, _mut102484, _mut102485)) {
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
    private T getPointFromLargestNumberCluster(final Collection<? extends Cluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_456");
        int maxNumber = 0;
        Cluster<T> selected = null;
        for (final Cluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_456");
            // get the number of points of the current cluster
            final int number = cluster.getPoints().size();
            // select the cluster with the largest number of points
            if (ROR_greater(number, maxNumber, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getPointFromLargestNumberCluster_456", _mut102486, _mut102487, _mut102488, _mut102489, _mut102490)) {
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
    private T getFarthestPoint(final Collection<CentroidCluster<T>> clusters) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getFarthestPoint_492");
        double maxDistance = Double.NEGATIVE_INFINITY;
        Cluster<T> selectedCluster = null;
        int selectedPoint = -1;
        for (final CentroidCluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getFarthestPoint_492");
            // get the farthest point
            final Clusterable center = cluster.getCenter();
            final List<T> points = cluster.getPoints();
            for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getFarthestPoint_492", _mut102496, _mut102497, _mut102498, _mut102499, _mut102500); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getFarthestPoint_492");
                final double distance = distance(points.get(i), center);
                if (ROR_greater(distance, maxDistance, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getFarthestPoint_492", _mut102491, _mut102492, _mut102493, _mut102494, _mut102495)) {
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
     * @param clusters the {@link Cluster}s to search
     * @param point the point to find the nearest {@link Cluster} for
     * @return the index of the nearest {@link Cluster} to the given point
     */
    private int getNearestCluster(final Collection<CentroidCluster<T>> clusters, final T point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getNearestCluster_529");
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        int minCluster = 0;
        for (final CentroidCluster<T> c : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getNearestCluster_529");
            final double distance = distance(point, c.getCenter());
            if (ROR_less(distance, minDistance, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.getNearestCluster_529", _mut102501, _mut102502, _mut102503, _mut102504, _mut102505)) {
                minDistance = distance;
                minCluster = clusterIndex;
            }
            clusterIndex++;
        }
        return minCluster;
    }

    /**
     * Computes the centroid for a set of points.
     *
     * @param points the set of points
     * @param dimension the point dimension
     * @return the computed centroid for the set of points
     */
    private Clusterable centroidOf(final Collection<T> points, final int dimension) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551");
        final double[] centroid = new double[dimension];
        for (final T p : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551");
            final double[] point = p.getPoint();
            for (int i = 0; ROR_less(i, centroid.length, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551", _mut102506, _mut102507, _mut102508, _mut102509, _mut102510); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551");
                centroid[i] += point[i];
            }
        }
        for (int i = 0; ROR_less(i, centroid.length, "org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551", _mut102511, _mut102512, _mut102513, _mut102514, _mut102515); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.centroidOf_551");
            centroid[i] /= points.size();
        }
        return new DoublePoint(centroid);
    }
}
