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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * DBSCAN (density-based spatial clustering of applications with noise) algorithm.
 * <p>
 * The DBSCAN algorithm forms clusters based on the idea of density connectivity, i.e.
 * a point p is density connected to another point q, if there exists a chain of
 * points p<sub>i</sub>, with i = 1 .. n and p<sub>1</sub> = p and p<sub>n</sub> = q,
 * such that each pair &lt;p<sub>i</sub>, p<sub>i+1</sub>&gt; is directly density-reachable.
 * A point q is directly density-reachable from point p if it is in the &epsilon;-neighborhood
 * of this point.
 * <p>
 * Any point that is not density-reachable from a formed cluster is treated as noise, and
 * will thus not be present in the result.
 * <p>
 * The algorithm requires two parameters:
 * <ul>
 *   <li>eps: the distance that defines the &epsilon;-neighborhood of a point
 *   <li>minPoints: the minimum number of density-connected points required to form a cluster
 * </ul>
 *
 * @param <T> type of the points to cluster
 * @see <a href="http://en.wikipedia.org/wiki/DBSCAN">DBSCAN (wikipedia)</a>
 * @see <a href="http://www.dbs.ifi.lmu.de/Publikationen/Papers/KDD-96.final.frame.pdf">
 * A Density-Based Algorithm for Discovering Clusters in Large Spatial Databases with Noise</a>
 * @since 3.2
 */
public class DBSCANClusterer<T extends Clusterable> extends Clusterer<T> {

    @Conditional
    public static boolean _mut102679 = false, _mut102680 = false, _mut102681 = false, _mut102682 = false, _mut102683 = false, _mut102684 = false, _mut102685 = false, _mut102686 = false, _mut102687 = false, _mut102688 = false, _mut102689 = false, _mut102690 = false, _mut102691 = false, _mut102692 = false, _mut102693 = false, _mut102694 = false, _mut102695 = false, _mut102696 = false, _mut102697 = false, _mut102698 = false, _mut102699 = false, _mut102700 = false, _mut102701 = false, _mut102702 = false, _mut102703 = false, _mut102704 = false, _mut102705 = false, _mut102706 = false, _mut102707 = false, _mut102708 = false, _mut102709 = false;

    /**
     * Maximum radius of the neighborhood to be considered.
     */
    private final double eps;

    /**
     * Minimum number of points needed for a cluster.
     */
    private final int minPts;

    /**
     * Status of a point during the clustering process.
     */
    private enum PointStatus {

        /**
         * The point has is considered to be noise.
         */
        NOISE,
        /**
         * The point is already part of a cluster.
         */
        PART_OF_CLUSTER
    }

    /**
     * Creates a new instance of a DBSCANClusterer.
     * <p>
     * The euclidean distance will be used as default distance measure.
     *
     * @param eps maximum radius of the neighborhood to be considered
     * @param minPts minimum number of points needed for a cluster
     * @throws NotPositiveException if {@code eps < 0.0} or {@code minPts < 0}
     */
    public DBSCANClusterer(final double eps, final int minPts) throws NotPositiveException {
        this(eps, minPts, new EuclideanDistance());
    }

    /**
     * Creates a new instance of a DBSCANClusterer.
     *
     * @param eps maximum radius of the neighborhood to be considered
     * @param minPts minimum number of points needed for a cluster
     * @param measure the distance measure to use
     * @throws NotPositiveException if {@code eps < 0.0} or {@code minPts < 0}
     */
    public DBSCANClusterer(final double eps, final int minPts, final DistanceMeasure measure) throws NotPositiveException {
        super(measure);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.DBSCANClusterer_96");
        if (ROR_less(eps, 0.0d, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.DBSCANClusterer_96", _mut102679, _mut102680, _mut102681, _mut102682, _mut102683)) {
            throw new NotPositiveException(eps);
        }
        if (ROR_less(minPts, 0, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.DBSCANClusterer_96", _mut102684, _mut102685, _mut102686, _mut102687, _mut102688)) {
            throw new NotPositiveException(minPts);
        }
        this.eps = eps;
        this.minPts = minPts;
    }

    /**
     * Returns the maximum radius of the neighborhood to be considered.
     * @return maximum radius of the neighborhood
     */
    public double getEps() {
        return eps;
    }

    /**
     * Returns the minimum number of points needed for a cluster.
     * @return minimum number of points needed for a cluster
     */
    public int getMinPts() {
        return minPts;
    }

    /**
     * Performs DBSCAN cluster analysis.
     *
     * @param points the points to cluster
     * @return the list of clusters
     * @throws NullArgumentException if the data points are null
     */
    @Override
    public List<Cluster<T>> cluster(final Collection<T> points) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.cluster_133");
        // sanity checks
        MathUtils.checkNotNull(points);
        final List<Cluster<T>> clusters = new ArrayList<Cluster<T>>();
        final Map<Clusterable, PointStatus> visited = new HashMap<Clusterable, PointStatus>();
        for (final T point : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.cluster_133");
            if (visited.get(point) != null) {
                continue;
            }
            final List<T> neighbors = getNeighbors(point, points);
            if (ROR_greater_equals(neighbors.size(), minPts, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.cluster_133", _mut102689, _mut102690, _mut102691, _mut102692, _mut102693)) {
                // DBSCAN does not care about center points
                final Cluster<T> cluster = new Cluster<T>();
                clusters.add(expandCluster(cluster, point, neighbors, points, visited));
            } else {
                visited.put(point, PointStatus.NOISE);
            }
        }
        return clusters;
    }

    /**
     * Expands the cluster to include density-reachable items.
     *
     * @param cluster Cluster to expand
     * @param point Point to add to cluster
     * @param neighbors List of neighbors
     * @param points the data set
     * @param visited the set of already visited points
     * @return the expanded cluster
     */
    private Cluster<T> expandCluster(final Cluster<T> cluster, final T point, final List<T> neighbors, final Collection<T> points, final Map<Clusterable, PointStatus> visited) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.expandCluster_169");
        cluster.addPoint(point);
        visited.put(point, PointStatus.PART_OF_CLUSTER);
        List<T> seeds = new ArrayList<T>(neighbors);
        int index = 0;
        while (ROR_less(index, seeds.size(), "org.apache.commons.math3.ml.clustering.DBSCANClusterer.expandCluster_169", _mut102699, _mut102700, _mut102701, _mut102702, _mut102703)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.expandCluster_169");
            final T current = seeds.get(index);
            PointStatus pStatus = visited.get(current);
            // only check non-visited points
            if (pStatus == null) {
                final List<T> currentNeighbors = getNeighbors(current, points);
                if (ROR_greater_equals(currentNeighbors.size(), minPts, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.expandCluster_169", _mut102694, _mut102695, _mut102696, _mut102697, _mut102698)) {
                    seeds = merge(seeds, currentNeighbors);
                }
            }
            if (pStatus != PointStatus.PART_OF_CLUSTER) {
                visited.put(current, PointStatus.PART_OF_CLUSTER);
                cluster.addPoint(current);
            }
            index++;
        }
        return cluster;
    }

    /**
     * Returns a list of density-reachable neighbors of a {@code point}.
     *
     * @param point the point to look for
     * @param points possible neighbors
     * @return the List of neighbors
     */
    private List<T> getNeighbors(final T point, final Collection<T> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.getNeighbors_207");
        final List<T> neighbors = new ArrayList<T>();
        for (final T neighbor : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.getNeighbors_207");
            if ((_mut102709 ? (point != neighbor || ROR_less_equals(distance(neighbor, point), eps, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.getNeighbors_207", _mut102704, _mut102705, _mut102706, _mut102707, _mut102708)) : (point != neighbor && ROR_less_equals(distance(neighbor, point), eps, "org.apache.commons.math3.ml.clustering.DBSCANClusterer.getNeighbors_207", _mut102704, _mut102705, _mut102706, _mut102707, _mut102708)))) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    /**
     * Merges two lists together.
     *
     * @param one first list
     * @param two second list
     * @return merged lists
     */
    private List<T> merge(final List<T> one, final List<T> two) {
        final Set<T> oneSet = new HashSet<T>(one);
        for (T item : two) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.clustering.DBSCANClusterer.merge_224");
            if (!oneSet.contains(item)) {
                one.add(item);
            }
        }
        return one;
    }
}
