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
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Fuzzy K-Means clustering algorithm.
 * <p>
 * The Fuzzy K-Means algorithm is a variation of the classical K-Means algorithm, with the
 * major difference that a single data point is not uniquely assigned to a single cluster.
 * Instead, each point i has a set of weights u<sub>ij</sub> which indicate the degree of membership
 * to the cluster j.
 * <p>
 * The algorithm then tries to minimize the objective function:
 * <pre>
 * J = &#8721;<sub>i=1..C</sub>&#8721;<sub>k=1..N</sub> u<sub>ik</sub><sup>m</sup>d<sub>ik</sub><sup>2</sup>
 * </pre>
 * with d<sub>ik</sub> being the distance between data point i and the cluster center k.
 * <p>
 * The algorithm requires two parameters:
 * <ul>
 *   <li>k: the number of clusters
 *   <li>fuzziness: determines the level of cluster fuzziness, larger values lead to fuzzier clusters
 * </ul>
 * Additional, optional parameters:
 * <ul>
 *   <li>maxIterations: the maximum number of iterations
 *   <li>epsilon: the convergence criteria, default is 1e-3
 * </ul>
 * <p>
 * The fuzzy variant of the K-Means algorithm is more robust with regard to the selection
 * of the initial cluster centers.
 *
 * @param <T> type of the points to cluster
 * @since 3.3
 */
public class FuzzyKMeansClusterer<T extends Clusterable> extends Clusterer<T> {

    @Conditional
    public static boolean _mut102516 = false, _mut102517 = false, _mut102518 = false, _mut102519 = false, _mut102520 = false, _mut102521 = false, _mut102522 = false, _mut102523 = false, _mut102524 = false, _mut102525 = false, _mut102526 = false, _mut102527 = false, _mut102528 = false, _mut102529 = false, _mut102530 = false, _mut102531 = false, _mut102532 = false, _mut102533 = false, _mut102534 = false, _mut102535 = false, _mut102536 = false, _mut102537 = false, _mut102538 = false, _mut102539 = false, _mut102540 = false, _mut102541 = false, _mut102542 = false, _mut102543 = false, _mut102544 = false, _mut102545 = false, _mut102546 = false, _mut102547 = false, _mut102548 = false, _mut102549 = false, _mut102550 = false, _mut102551 = false, _mut102552 = false, _mut102553 = false, _mut102554 = false, _mut102555 = false, _mut102556 = false, _mut102557 = false, _mut102558 = false, _mut102559 = false, _mut102560 = false, _mut102561 = false, _mut102562 = false, _mut102563 = false, _mut102564 = false, _mut102565 = false, _mut102566 = false, _mut102567 = false, _mut102568 = false, _mut102569 = false, _mut102570 = false, _mut102571 = false, _mut102572 = false, _mut102573 = false, _mut102574 = false, _mut102575 = false, _mut102576 = false, _mut102577 = false, _mut102578 = false, _mut102579 = false, _mut102580 = false, _mut102581 = false, _mut102582 = false, _mut102583 = false, _mut102584 = false, _mut102585 = false, _mut102586 = false, _mut102587 = false, _mut102588 = false, _mut102589 = false, _mut102590 = false, _mut102591 = false, _mut102592 = false, _mut102593 = false, _mut102594 = false, _mut102595 = false, _mut102596 = false, _mut102597 = false, _mut102598 = false, _mut102599 = false, _mut102600 = false, _mut102601 = false, _mut102602 = false, _mut102603 = false, _mut102604 = false, _mut102605 = false, _mut102606 = false, _mut102607 = false, _mut102608 = false, _mut102609 = false, _mut102610 = false, _mut102611 = false, _mut102612 = false, _mut102613 = false, _mut102614 = false, _mut102615 = false, _mut102616 = false, _mut102617 = false, _mut102618 = false, _mut102619 = false, _mut102620 = false, _mut102621 = false, _mut102622 = false, _mut102623 = false, _mut102624 = false, _mut102625 = false, _mut102626 = false, _mut102627 = false, _mut102628 = false, _mut102629 = false, _mut102630 = false, _mut102631 = false, _mut102632 = false, _mut102633 = false, _mut102634 = false, _mut102635 = false, _mut102636 = false, _mut102637 = false, _mut102638 = false, _mut102639 = false, _mut102640 = false, _mut102641 = false, _mut102642 = false, _mut102643 = false, _mut102644 = false, _mut102645 = false, _mut102646 = false, _mut102647 = false, _mut102648 = false, _mut102649 = false, _mut102650 = false, _mut102651 = false, _mut102652 = false, _mut102653 = false;

    /**
     * The default value for the convergence criteria.
     */
    private static final double DEFAULT_EPSILON = 1e-3;

    /**
     * The number of clusters.
     */
    private final int k;

    /**
     * The maximum number of iterations.
     */
    private final int maxIterations;

    /**
     * The fuzziness factor.
     */
    private final double fuzziness;

    /**
     * The convergence criteria.
     */
    private final double epsilon;

    /**
     * Random generator for choosing initial centers.
     */
    private final RandomGenerator random;

    /**
     * The membership matrix.
     */
    private double[][] membershipMatrix;

    /**
     * The list of points used in the last call to {@link #cluster(Collection)}.
     */
    private List<T> points;

    /**
     * The list of clusters resulting from the last call to {@link #cluster(Collection)}.
     */
    private List<CentroidCluster<T>> clusters;

    /**
     * Creates a new instance of a FuzzyKMeansClusterer.
     * <p>
     * The euclidean distance will be used as default distance measure.
     *
     * @param k the number of clusters to split the data into
     * @param fuzziness the fuzziness factor, must be &gt; 1.0
     * @throws NumberIsTooSmallException if {@code fuzziness <= 1.0}
     */
    public FuzzyKMeansClusterer(final int k, final double fuzziness) throws NumberIsTooSmallException {
        this(k, fuzziness, -1, new EuclideanDistance());
    }

    /**
     * Creates a new instance of a FuzzyKMeansClusterer.
     *
     * @param k the number of clusters to split the data into
     * @param fuzziness the fuzziness factor, must be &gt; 1.0
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     * @param measure the distance measure to use
     * @throws NumberIsTooSmallException if {@code fuzziness <= 1.0}
     */
    public FuzzyKMeansClusterer(final int k, final double fuzziness, final int maxIterations, final DistanceMeasure measure) throws NumberIsTooSmallException {
        this(k, fuzziness, maxIterations, measure, DEFAULT_EPSILON, new JDKRandomGenerator());
    }

    /**
     * Creates a new instance of a FuzzyKMeansClusterer.
     *
     * @param k the number of clusters to split the data into
     * @param fuzziness the fuzziness factor, must be &gt; 1.0
     * @param maxIterations the maximum number of iterations to run the algorithm for.
     *   If negative, no maximum will be used.
     * @param measure the distance measure to use
     * @param epsilon the convergence criteria (default is 1e-3)
     * @param random random generator to use for choosing initial centers
     * @throws NumberIsTooSmallException if {@code fuzziness <= 1.0}
     */
    public FuzzyKMeansClusterer(final int k, final double fuzziness, final int maxIterations, final DistanceMeasure measure, final double epsilon, final RandomGenerator random) throws NumberIsTooSmallException {
        super(measure);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.FuzzyKMeansClusterer_138");
        if (ROR_less_equals(fuzziness, 1.0d, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.FuzzyKMeansClusterer_138", _mut102516, _mut102517, _mut102518, _mut102519, _mut102520)) {
            throw new NumberIsTooSmallException(fuzziness, 1.0, false);
        }
        this.k = k;
        this.fuzziness = fuzziness;
        this.maxIterations = maxIterations;
        this.epsilon = epsilon;
        this.random = random;
        this.membershipMatrix = null;
        this.points = null;
        this.clusters = null;
    }

    /**
     * Return the number of clusters this instance will use.
     * @return the number of clusters
     */
    public int getK() {
        return k;
    }

    /**
     * Returns the fuzziness factor used by this instance.
     * @return the fuzziness factor
     */
    public double getFuzziness() {
        return fuzziness;
    }

    /**
     * Returns the maximum number of iterations this instance will use.
     * @return the maximum number of iterations, or -1 if no maximum is set
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Returns the convergence criteria used by this instance.
     * @return the convergence criteria
     */
    public double getEpsilon() {
        return epsilon;
    }

    /**
     * Returns the random generator this instance will use.
     * @return the random generator
     */
    public RandomGenerator getRandomGenerator() {
        return random;
    }

    /**
     * Returns the {@code nxk} membership matrix, where {@code n} is the number
     * of data points and {@code k} the number of clusters.
     * <p>
     * The element U<sub>i,j</sub> represents the membership value for data point {@code i}
     * to cluster {@code j}.
     *
     * @return the membership matrix
     * @throws MathIllegalStateException if {@link #cluster(Collection)} has not been called before
     */
    public RealMatrix getMembershipMatrix() {
        if (membershipMatrix == null) {
            throw new MathIllegalStateException();
        }
        return MatrixUtils.createRealMatrix(membershipMatrix);
    }

    /**
     * Returns an unmodifiable list of the data points used in the last
     * call to {@link #cluster(Collection)}.
     * @return the list of data points, or {@code null} if {@link #cluster(Collection)} has
     *   not been called before.
     */
    public List<T> getDataPoints() {
        return points;
    }

    /**
     * Returns the list of clusters resulting from the last call to {@link #cluster(Collection)}.
     * @return the list of clusters, or {@code null} if {@link #cluster(Collection)} has
     *   not been called before.
     */
    public List<CentroidCluster<T>> getClusters() {
        return clusters;
    }

    /**
     * Get the value of the objective function.
     * @return the objective function evaluation as double value
     * @throws MathIllegalStateException if {@link #cluster(Collection)} has not been called before
     */
    public double getObjectiveFunctionValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.getObjectiveFunctionValue_240");
        if ((_mut102521 ? (points == null && clusters == null) : (points == null || clusters == null))) {
            throw new MathIllegalStateException();
        }
        int i = 0;
        double objFunction = 0.0;
        for (final T point : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.getObjectiveFunctionValue_240");
            int j = 0;
            for (final CentroidCluster<T> cluster : clusters) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.getObjectiveFunctionValue_240");
                final double dist = distance(point, cluster.getCenter());
                objFunction += AOR_multiply((AOR_multiply(dist, dist, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.getObjectiveFunctionValue_240", _mut102522, _mut102523, _mut102524, _mut102525)), FastMath.pow(membershipMatrix[i][j], fuzziness), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.getObjectiveFunctionValue_240", _mut102526, _mut102527, _mut102528, _mut102529);
                j++;
            }
            i++;
        }
        return objFunction;
    }

    /**
     * Performs Fuzzy K-Means cluster analysis.
     *
     * @param dataPoints the points to cluster
     * @return the list of clusters
     * @throws MathIllegalArgumentException if the data points are null or the number
     *     of clusters is larger than the number of data points
     */
    @Override
    public List<CentroidCluster<T>> cluster(final Collection<T> dataPoints) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267");
        // sanity checks
        MathUtils.checkNotNull(dataPoints);
        final int size = dataPoints.size();
        // number of clusters has to be smaller or equal the number of data points
        if (ROR_less(size, k, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102530, _mut102531, _mut102532, _mut102533, _mut102534)) {
            throw new NumberIsTooSmallException(size, k, false);
        }
        // copy the input collection to an unmodifiable list with indexed access
        points = Collections.unmodifiableList(new ArrayList<T>(dataPoints));
        clusters = new ArrayList<CentroidCluster<T>>();
        membershipMatrix = new double[size][k];
        final double[][] oldMatrix = new double[size][k];
        // if no points are provided, return an empty list of clusters
        if (ROR_equals(size, 0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102535, _mut102536, _mut102537, _mut102538, _mut102539)) {
            return clusters;
        }
        initializeMembershipMatrix();
        // there is at least one point
        final int pointDimension = points.get(0).getPoint().length;
        for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102540, _mut102541, _mut102542, _mut102543, _mut102544); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267");
            clusters.add(new CentroidCluster<T>(new DoublePoint(new double[pointDimension])));
        }
        int iteration = 0;
        final int max = (ROR_less(maxIterations, 0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102545, _mut102546, _mut102547, _mut102548, _mut102549)) ? Integer.MAX_VALUE : maxIterations;
        double difference = 0.0;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267");
            saveMembershipMatrix(oldMatrix);
            updateClusterCenters();
            updateMembershipMatrix();
            difference = calculateMaxMembershipChange(oldMatrix);
        } while ((_mut102560 ? (ROR_greater(difference, epsilon, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102550, _mut102551, _mut102552, _mut102553, _mut102554) || ROR_less(++iteration, max, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102555, _mut102556, _mut102557, _mut102558, _mut102559)) : (ROR_greater(difference, epsilon, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102550, _mut102551, _mut102552, _mut102553, _mut102554) && ROR_less(++iteration, max, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.cluster_267", _mut102555, _mut102556, _mut102557, _mut102558, _mut102559))));
        return clusters;
    }

    /**
     * Update the cluster centers.
     */
    private void updateClusterCenters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317");
        int j = 0;
        final List<CentroidCluster<T>> newClusters = new ArrayList<CentroidCluster<T>>(k);
        for (final CentroidCluster<T> cluster : clusters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317");
            final Clusterable center = cluster.getCenter();
            int i = 0;
            double[] arr = new double[center.getPoint().length];
            double sum = 0.0;
            for (final T point : points) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317");
                final double u = FastMath.pow(membershipMatrix[i][j], fuzziness);
                final double[] pointArr = point.getPoint();
                for (int idx = 0; ROR_less(idx, arr.length, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317", _mut102565, _mut102566, _mut102567, _mut102568, _mut102569); idx++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317");
                    arr[idx] += AOR_multiply(u, pointArr[idx], "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317", _mut102561, _mut102562, _mut102563, _mut102564);
                }
                sum += u;
                i++;
            }
            MathArrays.scaleInPlace(AOR_divide(1.0, sum, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateClusterCenters_317", _mut102570, _mut102571, _mut102572, _mut102573), arr);
            newClusters.add(new CentroidCluster<T>(new DoublePoint(arr)));
            j++;
        }
        clusters.clear();
        clusters = newClusters;
    }

    /**
     * Updates the membership matrix and assigns the points to the cluster with
     * the highest membership.
     */
    private void updateMembershipMatrix() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346");
        for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102620, _mut102621, _mut102622, _mut102623, _mut102624); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346");
            final T point = points.get(i);
            double maxMembership = Double.MIN_VALUE;
            int newCluster = -1;
            for (int j = 0; ROR_less(j, clusters.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102615, _mut102616, _mut102617, _mut102618, _mut102619); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346");
                double sum = 0.0;
                final double distA = FastMath.abs(distance(point, clusters.get(j).getCenter()));
                if (ROR_not_equals(distA, 0.0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102574, _mut102575, _mut102576, _mut102577, _mut102578)) {
                    for (final CentroidCluster<T> c : clusters) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346");
                        final double distB = FastMath.abs(distance(point, c.getCenter()));
                        if (ROR_equals(distB, 0.0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102579, _mut102580, _mut102581, _mut102582, _mut102583)) {
                            sum = Double.POSITIVE_INFINITY;
                            break;
                        }
                        sum += FastMath.pow(AOR_divide(distA, distB, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102584, _mut102585, _mut102586, _mut102587), AOR_divide(2.0, (AOR_minus(fuzziness, 1.0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102588, _mut102589, _mut102590, _mut102591)), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102592, _mut102593, _mut102594, _mut102595));
                    }
                }
                double membership;
                if (ROR_equals(sum, 0.0, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102596, _mut102597, _mut102598, _mut102599, _mut102600)) {
                    membership = 1.0;
                } else if (ROR_equals(sum, Double.POSITIVE_INFINITY, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102601, _mut102602, _mut102603, _mut102604, _mut102605)) {
                    membership = 0.0;
                } else {
                    membership = AOR_divide(1.0, sum, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102606, _mut102607, _mut102608, _mut102609);
                }
                membershipMatrix[i][j] = membership;
                if (ROR_greater(membershipMatrix[i][j], maxMembership, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.updateMembershipMatrix_346", _mut102610, _mut102611, _mut102612, _mut102613, _mut102614)) {
                    maxMembership = membershipMatrix[i][j];
                    newCluster = j;
                }
            }
            clusters.get(newCluster).addPoint(point);
        }
    }

    /**
     * Initialize the membership matrix with random values.
     */
    private void initializeMembershipMatrix() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.initializeMembershipMatrix_388");
        for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.initializeMembershipMatrix_388", _mut102630, _mut102631, _mut102632, _mut102633, _mut102634); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.initializeMembershipMatrix_388");
            for (int j = 0; ROR_less(j, k, "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.initializeMembershipMatrix_388", _mut102625, _mut102626, _mut102627, _mut102628, _mut102629); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.initializeMembershipMatrix_388");
                membershipMatrix[i][j] = random.nextDouble();
            }
            membershipMatrix[i] = MathArrays.normalizeArray(membershipMatrix[i], 1.0);
        }
    }

    /**
     * Calculate the maximum element-by-element change of the membership matrix
     * for the current iteration.
     *
     * @param matrix the membership matrix of the previous iteration
     * @return the maximum membership matrix change
     */
    private double calculateMaxMembershipChange(final double[][] matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404");
        double maxMembership = 0.0;
        for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404", _mut102644, _mut102645, _mut102646, _mut102647, _mut102648); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404");
            for (int j = 0; ROR_less(j, clusters.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404", _mut102639, _mut102640, _mut102641, _mut102642, _mut102643); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404");
                double v = FastMath.abs(AOR_minus(membershipMatrix[i][j], matrix[i][j], "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.calculateMaxMembershipChange_404", _mut102635, _mut102636, _mut102637, _mut102638));
                maxMembership = FastMath.max(v, maxMembership);
            }
        }
        return maxMembership;
    }

    /**
     * Copy the membership matrix into the provided matrix.
     *
     * @param matrix the place to store the membership matrix
     */
    private void saveMembershipMatrix(final double[][] matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.saveMembershipMatrix_420");
        for (int i = 0; ROR_less(i, points.size(), "org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.saveMembershipMatrix_420", _mut102649, _mut102650, _mut102651, _mut102652, _mut102653); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.saveMembershipMatrix_420");
            System.arraycopy(membershipMatrix[i], 0, matrix[i], 0, clusters.size());
        }
    }
}
