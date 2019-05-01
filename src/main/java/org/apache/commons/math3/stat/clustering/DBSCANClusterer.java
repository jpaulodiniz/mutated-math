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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
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
 * <p>
 * <b>Note:</b> as DBSCAN is not a centroid-based clustering algorithm, the resulting
 * {@link Cluster} objects will have no defined center, i.e. {@link Cluster#getCenter()} will
 * return {@code null}.
 *
 * @param <T> type of the points to cluster
 * @see <a href="http://en.wikipedia.org/wiki/DBSCAN">DBSCAN (wikipedia)</a>
 * @see <a href="http://www.dbs.ifi.lmu.de/Publikationen/Papers/KDD-96.final.frame.pdf">
 * A Density-Based Algorithm for Discovering Clusters in Large Spatial Databases with Noise</a>
 * @since 3.1
 * @deprecated As of 3.2 (to be removed in 4.0),
 * use {@link org.apache.commons.math3.ml.clustering.DBSCANClusterer} instead
 */
@Deprecated
public class DBSCANClusterer<T extends Clusterable<T>> {

    @Conditional
    public static boolean _mut4842 = false, _mut4843 = false, _mut4844 = false, _mut4845 = false, _mut4846 = false, _mut4847 = false, _mut4848 = false, _mut4849 = false, _mut4850 = false, _mut4851 = false, _mut4852 = false, _mut4853 = false, _mut4854 = false, _mut4855 = false, _mut4856 = false, _mut4857 = false, _mut4858 = false, _mut4859 = false, _mut4860 = false, _mut4861 = false, _mut4862 = false, _mut4863 = false, _mut4864 = false, _mut4865 = false, _mut4866 = false, _mut4867 = false, _mut4868 = false, _mut4869 = false, _mut4870 = false, _mut4871 = false, _mut4872 = false;

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
     *
     * @param eps maximum radius of the neighborhood to be considered
     * @param minPts minimum number of points needed for a cluster
     * @throws NotPositiveException if {@code eps < 0.0} or {@code minPts < 0}
     */
    public DBSCANClusterer(final double eps, final int minPts) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.DBSCANClusterer_86");
        if (ROR_less(eps, 0.0d, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.DBSCANClusterer_86", _mut4842, _mut4843, _mut4844, _mut4845, _mut4846)) {
            throw new NotPositiveException(eps);
        }
        if (ROR_less(minPts, 0, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.DBSCANClusterer_86", _mut4847, _mut4848, _mut4849, _mut4850, _mut4851)) {
            throw new NotPositiveException(minPts);
        }
        this.eps = eps;
        this.minPts = minPts;
    }

    /**
     * Returns the maximum radius of the neighborhood to be considered.
     *
     * @return maximum radius of the neighborhood
     */
    public double getEps() {
        return eps;
    }

    /**
     * Returns the minimum number of points needed for a cluster.
     *
     * @return minimum number of points needed for a cluster
     */
    public int getMinPts() {
        return minPts;
    }

    /**
     * Performs DBSCAN cluster analysis.
     * <p>
     * <b>Note:</b> as DBSCAN is not a centroid-based clustering algorithm, the resulting
     * {@link Cluster} objects will have no defined center, i.e. {@link Cluster#getCenter()} will
     * return {@code null}.
     *
     * @param points the points to cluster
     * @return the list of clusters
     * @throws NullArgumentException if the data points are null
     */
    public List<Cluster<T>> cluster(final Collection<T> points) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.cluster_127");
        // sanity checks
        MathUtils.checkNotNull(points);
        final List<Cluster<T>> clusters = new ArrayList<Cluster<T>>();
        final Map<Clusterable<T>, PointStatus> visited = new HashMap<Clusterable<T>, PointStatus>();
        for (final T point : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.cluster_127");
            if (visited.get(point) != null) {
                continue;
            }
            final List<T> neighbors = getNeighbors(point, points);
            if (ROR_greater_equals(neighbors.size(), minPts, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.cluster_127", _mut4852, _mut4853, _mut4854, _mut4855, _mut4856)) {
                // DBSCAN does not care about center points
                final Cluster<T> cluster = new Cluster<T>(null);
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
    private Cluster<T> expandCluster(final Cluster<T> cluster, final T point, final List<T> neighbors, final Collection<T> points, final Map<Clusterable<T>, PointStatus> visited) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.expandCluster_162");
        cluster.addPoint(point);
        visited.put(point, PointStatus.PART_OF_CLUSTER);
        List<T> seeds = new ArrayList<T>(neighbors);
        int index = 0;
        while (ROR_less(index, seeds.size(), "org.apache.commons.math3.stat.clustering.DBSCANClusterer.expandCluster_162", _mut4862, _mut4863, _mut4864, _mut4865, _mut4866)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.expandCluster_162");
            final T current = seeds.get(index);
            PointStatus pStatus = visited.get(current);
            // only check non-visited points
            if (pStatus == null) {
                final List<T> currentNeighbors = getNeighbors(current, points);
                if (ROR_greater_equals(currentNeighbors.size(), minPts, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.expandCluster_162", _mut4857, _mut4858, _mut4859, _mut4860, _mut4861)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.getNeighbors_200");
        final List<T> neighbors = new ArrayList<T>();
        for (final T neighbor : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.getNeighbors_200");
            if ((_mut4872 ? (point != neighbor || ROR_less_equals(neighbor.distanceFrom(point), eps, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.getNeighbors_200", _mut4867, _mut4868, _mut4869, _mut4870, _mut4871)) : (point != neighbor && ROR_less_equals(neighbor.distanceFrom(point), eps, "org.apache.commons.math3.stat.clustering.DBSCANClusterer.getNeighbors_200", _mut4867, _mut4868, _mut4869, _mut4870, _mut4871)))) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.clustering.DBSCANClusterer.merge_217");
            if (!oneSet.contains(item)) {
                one.add(item);
            }
        }
        return one;
    }
}
