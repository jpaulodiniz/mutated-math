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
package org.apache.commons.math3.optimization.direct;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.OptimizationData;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the simplex concept.
 * It is intended to be used in conjunction with {@link SimplexOptimizer}.
 * <br/>
 * The initial configuration of the simplex is set by the constructors
 * {@link #AbstractSimplex(double[])} or {@link #AbstractSimplex(double[][])}.
 * The other {@link #AbstractSimplex(int) constructor} will set all steps
 * to 1, thus building a default configuration from a unit hypercube.
 * <br/>
 * Users <em>must</em> call the {@link #build(double[]) build} method in order
 * to create the data structure that will be acted on by the other methods of
 * this class.
 *
 * @see SimplexOptimizer
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public abstract class AbstractSimplex implements OptimizationData {

    @Conditional
    public static boolean _mut74932 = false, _mut74933 = false, _mut74934 = false, _mut74935 = false, _mut74936 = false, _mut74937 = false, _mut74938 = false, _mut74939 = false, _mut74940 = false, _mut74941 = false, _mut74942 = false, _mut74943 = false, _mut74944 = false, _mut74945 = false, _mut74946 = false, _mut74947 = false, _mut74948 = false, _mut74949 = false, _mut74950 = false, _mut74951 = false, _mut74952 = false, _mut74953 = false, _mut74954 = false, _mut74955 = false, _mut74956 = false, _mut74957 = false, _mut74958 = false, _mut74959 = false, _mut74960 = false, _mut74961 = false, _mut74962 = false, _mut74963 = false, _mut74964 = false, _mut74965 = false, _mut74966 = false, _mut74967 = false, _mut74968 = false, _mut74969 = false, _mut74970 = false, _mut74971 = false, _mut74972 = false, _mut74973 = false, _mut74974 = false, _mut74975 = false, _mut74976 = false, _mut74977 = false, _mut74978 = false, _mut74979 = false, _mut74980 = false, _mut74981 = false, _mut74982 = false, _mut74983 = false, _mut74984 = false, _mut74985 = false, _mut74986 = false, _mut74987 = false, _mut74988 = false, _mut74989 = false, _mut74990 = false, _mut74991 = false, _mut74992 = false, _mut74993 = false, _mut74994 = false, _mut74995 = false, _mut74996 = false, _mut74997 = false, _mut74998 = false, _mut74999 = false, _mut75000 = false, _mut75001 = false, _mut75002 = false, _mut75003 = false, _mut75004 = false, _mut75005 = false, _mut75006 = false, _mut75007 = false, _mut75008 = false, _mut75009 = false, _mut75010 = false, _mut75011 = false, _mut75012 = false, _mut75013 = false, _mut75014 = false, _mut75015 = false, _mut75016 = false, _mut75017 = false, _mut75018 = false, _mut75019 = false, _mut75020 = false, _mut75021 = false, _mut75022 = false, _mut75023 = false, _mut75024 = false, _mut75025 = false, _mut75026 = false, _mut75027 = false, _mut75028 = false, _mut75029 = false, _mut75030 = false, _mut75031 = false, _mut75032 = false, _mut75033 = false, _mut75034 = false, _mut75035 = false, _mut75036 = false, _mut75037 = false, _mut75038 = false, _mut75039 = false, _mut75040 = false, _mut75041 = false, _mut75042 = false, _mut75043 = false, _mut75044 = false, _mut75045 = false, _mut75046 = false, _mut75047 = false, _mut75048 = false, _mut75049 = false, _mut75050 = false, _mut75051 = false, _mut75052 = false, _mut75053 = false, _mut75054 = false, _mut75055 = false, _mut75056 = false, _mut75057 = false, _mut75058 = false, _mut75059 = false, _mut75060 = false, _mut75061 = false, _mut75062 = false, _mut75063 = false, _mut75064 = false, _mut75065 = false, _mut75066 = false, _mut75067 = false, _mut75068 = false, _mut75069 = false, _mut75070 = false, _mut75071 = false, _mut75072 = false, _mut75073 = false, _mut75074 = false, _mut75075 = false, _mut75076 = false, _mut75077 = false, _mut75078 = false, _mut75079 = false, _mut75080 = false, _mut75081 = false, _mut75082 = false, _mut75083 = false, _mut75084 = false, _mut75085 = false, _mut75086 = false, _mut75087 = false, _mut75088 = false, _mut75089 = false, _mut75090 = false, _mut75091 = false, _mut75092 = false, _mut75093 = false;

    /**
     * Simplex.
     */
    private PointValuePair[] simplex;

    /**
     * Start simplex configuration.
     */
    private double[][] startConfiguration;

    /**
     * Simplex dimension (must be equal to {@code simplex.length - 1}).
     */
    private final int dimension;

    /**
     * Build a unit hypercube simplex.
     *
     * @param n Dimension of the simplex.
     */
    protected AbstractSimplex(int n) {
        this(n, 1d);
    }

    /**
     * Build a hypercube simplex with the given side length.
     *
     * @param n Dimension of the simplex.
     * @param sideLength Length of the sides of the hypercube.
     */
    protected AbstractSimplex(int n, double sideLength) {
        this(createHypercubeSteps(n, sideLength));
    }

    /**
     * The start configuration for simplex is built from a box parallel to
     * the canonical axes of the space. The simplex is the subset of vertices
     * of a box parallel to the canonical axes. It is built as the path followed
     * while traveling from one vertex of the box to the diagonally opposite
     * vertex moving only along the box edges. The first vertex of the box will
     * be located at the start point of the optimization.
     * As an example, in dimension 3 a simplex has 4 vertices. Setting the
     * steps to (1, 10, 2) and the start point to (1, 1, 1) would imply the
     * start simplex would be: { (1, 1, 1), (2, 1, 1), (2, 11, 1), (2, 11, 3) }.
     * The first vertex would be set to the start point at (1, 1, 1) and the
     * last vertex would be set to the diagonally opposite vertex at (2, 11, 3).
     *
     * @param steps Steps along the canonical axes representing box edges. They
     * may be negative but not zero.
     * @throws NullArgumentException if {@code steps} is {@code null}.
     * @throws ZeroException if one of the steps is zero.
     */
    protected AbstractSimplex(final double[] steps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98");
        if (steps == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(steps.length, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74932, _mut74933, _mut74934, _mut74935, _mut74936)) {
            throw new ZeroException();
        }
        dimension = steps.length;
        // to the first one are stored.
        startConfiguration = new double[dimension][dimension];
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74955, _mut74956, _mut74957, _mut74958, _mut74959); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98");
            final double[] vertexI = startConfiguration[i];
            for (int j = 0; ROR_less(j, AOR_plus(i, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74946, _mut74947, _mut74948, _mut74949), "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74950, _mut74951, _mut74952, _mut74953, _mut74954); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98");
                if (ROR_equals(steps[j], 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74937, _mut74938, _mut74939, _mut74940, _mut74941)) {
                    throw new ZeroException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX);
                }
                System.arraycopy(steps, 0, vertexI, 0, AOR_plus(j, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_98", _mut74942, _mut74943, _mut74944, _mut74945));
            }
        }
    }

    /**
     * The real initial simplex will be set up by moving the reference
     * simplex such that its first point is located at the start point of the
     * optimization.
     *
     * @param referenceSimplex Reference simplex.
     * @throws NotStrictlyPositiveException if the reference simplex does not
     * contain at least one point.
     * @throws DimensionMismatchException if there is a dimension mismatch
     * in the reference simplex.
     * @throws IllegalArgumentException if one of its vertices is duplicated.
     */
    protected AbstractSimplex(final double[][] referenceSimplex) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133");
        if (ROR_less_equals(referenceSimplex.length, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74960, _mut74961, _mut74962, _mut74963, _mut74964)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SIMPLEX_NEED_ONE_POINT, referenceSimplex.length);
        }
        dimension = AOR_minus(referenceSimplex.length, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74965, _mut74966, _mut74967, _mut74968);
        // to the first one are stored.
        startConfiguration = new double[dimension][dimension];
        final double[] ref0 = referenceSimplex[0];
        // Loop over vertices.
        for (int i = 0; ROR_less(i, referenceSimplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut75007, _mut75008, _mut75009, _mut75010, _mut75011); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133");
            final double[] refI = referenceSimplex[i];
            // Safety checks.
            if (ROR_not_equals(refI.length, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74969, _mut74970, _mut74971, _mut74972, _mut74973)) {
                throw new DimensionMismatchException(refI.length, dimension);
            }
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74984, _mut74985, _mut74986, _mut74987, _mut74988); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133");
                final double[] refJ = referenceSimplex[j];
                boolean allEquals = true;
                for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74979, _mut74980, _mut74981, _mut74982, _mut74983); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133");
                    if (ROR_not_equals(refI[k], refJ[k], "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74974, _mut74975, _mut74976, _mut74977, _mut74978)) {
                        allEquals = false;
                        break;
                    }
                }
                if (allEquals) {
                    throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, i, j);
                }
            }
            // Store vertex i position relative to vertex 0 position.
            if (ROR_greater(i, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74989, _mut74990, _mut74991, _mut74992, _mut74993)) {
                final double[] confI = startConfiguration[AOR_minus(i, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74994, _mut74995, _mut74996, _mut74997)];
                for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut75002, _mut75003, _mut75004, _mut75005, _mut75006); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133");
                    confI[k] = AOR_minus(refI[k], ref0[k], "org.apache.commons.math3.optimization.direct.AbstractSimplex.AbstractSimplex_133", _mut74998, _mut74999, _mut75000, _mut75001);
                }
            }
        }
    }

    /**
     * Get simplex dimension.
     *
     * @return the dimension of the simplex.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Get simplex size.
     * After calling the {@link #build(double[]) build} method, this method will
     * will be equivalent to {@code getDimension() + 1}.
     *
     * @return the size of the simplex.
     */
    public int getSize() {
        return simplex.length;
    }

    /**
     * Compute the next simplex of the algorithm.
     *
     * @param evaluationFunction Evaluation function.
     * @param comparator Comparator to use to sort simplex vertices from best
     * to worst.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the algorithm fails to converge.
     */
    public abstract void iterate(final MultivariateFunction evaluationFunction, final Comparator<PointValuePair> comparator);

    /**
     * Build an initial simplex.
     *
     * @param startPoint First point of the simplex.
     * @throws DimensionMismatchException if the start point does not match
     * simplex dimension.
     */
    public void build(final double[] startPoint) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217");
        if (ROR_not_equals(dimension, startPoint.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75012, _mut75013, _mut75014, _mut75015, _mut75016)) {
            throw new DimensionMismatchException(dimension, startPoint.length);
        }
        // Set first vertex.
        simplex = new PointValuePair[AOR_plus(dimension, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75017, _mut75018, _mut75019, _mut75020)];
        simplex[0] = new PointValuePair(startPoint, Double.NaN);
        // Set remaining vertices.
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75034, _mut75035, _mut75036, _mut75037, _mut75038); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217");
            final double[] confI = startConfiguration[i];
            final double[] vertexI = new double[dimension];
            for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75025, _mut75026, _mut75027, _mut75028, _mut75029); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217");
                vertexI[k] = AOR_plus(startPoint[k], confI[k], "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75021, _mut75022, _mut75023, _mut75024);
            }
            simplex[AOR_plus(i, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.build_217", _mut75030, _mut75031, _mut75032, _mut75033)] = new PointValuePair(vertexI, Double.NaN);
        }
    }

    /**
     * Evaluate all the non-evaluated points of the simplex.
     *
     * @param evaluationFunction Evaluation function.
     * @param comparator Comparator to use to sort simplex vertices from best to worst.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximal number of evaluations is exceeded.
     */
    public void evaluate(final MultivariateFunction evaluationFunction, final Comparator<PointValuePair> comparator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.evaluate_245");
        // Evaluate the objective function at all non-evaluated simplex points.
        for (int i = 0; ROR_less(i, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.evaluate_245", _mut75039, _mut75040, _mut75041, _mut75042, _mut75043); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.evaluate_245");
            final PointValuePair vertex = simplex[i];
            final double[] point = vertex.getPointRef();
            if (Double.isNaN(vertex.getValue())) {
                simplex[i] = new PointValuePair(point, evaluationFunction.value(point), false);
            }
        }
        // Sort the simplex from best to worst.
        Arrays.sort(simplex, comparator);
    }

    /**
     * Replace the worst point of the simplex by a new point.
     *
     * @param pointValuePair Point to insert.
     * @param comparator Comparator to use for sorting the simplex vertices
     * from best to worst.
     */
    protected void replaceWorstPoint(PointValuePair pointValuePair, final Comparator<PointValuePair> comparator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.replaceWorstPoint_267");
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.AbstractSimplex.replaceWorstPoint_267", _mut75049, _mut75050, _mut75051, _mut75052, _mut75053); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.replaceWorstPoint_267");
            if (ROR_greater(comparator.compare(simplex[i], pointValuePair), 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.replaceWorstPoint_267", _mut75044, _mut75045, _mut75046, _mut75047, _mut75048)) {
                PointValuePair tmp = simplex[i];
                simplex[i] = pointValuePair;
                pointValuePair = tmp;
            }
        }
        simplex[dimension] = pointValuePair;
    }

    /**
     * Get the points of the simplex.
     *
     * @return all the simplex points.
     */
    public PointValuePair[] getPoints() {
        final PointValuePair[] copy = new PointValuePair[simplex.length];
        System.arraycopy(simplex, 0, copy, 0, simplex.length);
        return copy;
    }

    /**
     * Get the simplex point stored at the requested {@code index}.
     *
     * @param index Location.
     * @return the point at location {@code index}.
     */
    public PointValuePair getPoint(int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296");
        if ((_mut75064 ? (ROR_less(index, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296", _mut75054, _mut75055, _mut75056, _mut75057, _mut75058) && ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296", _mut75059, _mut75060, _mut75061, _mut75062, _mut75063)) : (ROR_less(index, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296", _mut75054, _mut75055, _mut75056, _mut75057, _mut75058) || ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296", _mut75059, _mut75060, _mut75061, _mut75062, _mut75063)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(simplex.length, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.getPoint_296", _mut75065, _mut75066, _mut75067, _mut75068));
        }
        return simplex[index];
    }

    /**
     * Store a new point at location {@code index}.
     * Note that no deep-copy of {@code point} is performed.
     *
     * @param index Location.
     * @param point New value.
     */
    protected void setPoint(int index, PointValuePair point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311");
        if ((_mut75079 ? (ROR_less(index, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311", _mut75069, _mut75070, _mut75071, _mut75072, _mut75073) && ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311", _mut75074, _mut75075, _mut75076, _mut75077, _mut75078)) : (ROR_less(index, 0, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311", _mut75069, _mut75070, _mut75071, _mut75072, _mut75073) || ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311", _mut75074, _mut75075, _mut75076, _mut75077, _mut75078)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(simplex.length, 1, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoint_311", _mut75080, _mut75081, _mut75082, _mut75083));
        }
        simplex[index] = point;
    }

    /**
     * Replace all points.
     * Note that no deep-copy of {@code points} is performed.
     *
     * @param points New Points.
     */
    protected void setPoints(PointValuePair[] points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoints_325");
        if (ROR_not_equals(points.length, simplex.length, "org.apache.commons.math3.optimization.direct.AbstractSimplex.setPoints_325", _mut75084, _mut75085, _mut75086, _mut75087, _mut75088)) {
            throw new DimensionMismatchException(points.length, simplex.length);
        }
        simplex = points;
    }

    /**
     * Create steps for a unit hypercube.
     *
     * @param n Dimension of the hypercube.
     * @param sideLength Length of the sides of the hypercube.
     * @return the steps.
     */
    private static double[] createHypercubeSteps(int n, double sideLength) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.createHypercubeSteps_339");
        final double[] steps = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.AbstractSimplex.createHypercubeSteps_339", _mut75089, _mut75090, _mut75091, _mut75092, _mut75093); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.AbstractSimplex.createHypercubeSteps_339");
            steps[i] = sideLength;
        }
        return steps;
    }
}
