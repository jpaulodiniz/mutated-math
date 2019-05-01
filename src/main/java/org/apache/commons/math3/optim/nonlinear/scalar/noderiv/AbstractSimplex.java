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
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

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
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.OptimizationData;
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
 * @since 3.0
 */
public abstract class AbstractSimplex implements OptimizationData {

    @Conditional
    public static boolean _mut61740 = false, _mut61741 = false, _mut61742 = false, _mut61743 = false, _mut61744 = false, _mut61745 = false, _mut61746 = false, _mut61747 = false, _mut61748 = false, _mut61749 = false, _mut61750 = false, _mut61751 = false, _mut61752 = false, _mut61753 = false, _mut61754 = false, _mut61755 = false, _mut61756 = false, _mut61757 = false, _mut61758 = false, _mut61759 = false, _mut61760 = false, _mut61761 = false, _mut61762 = false, _mut61763 = false, _mut61764 = false, _mut61765 = false, _mut61766 = false, _mut61767 = false, _mut61768 = false, _mut61769 = false, _mut61770 = false, _mut61771 = false, _mut61772 = false, _mut61773 = false, _mut61774 = false, _mut61775 = false, _mut61776 = false, _mut61777 = false, _mut61778 = false, _mut61779 = false, _mut61780 = false, _mut61781 = false, _mut61782 = false, _mut61783 = false, _mut61784 = false, _mut61785 = false, _mut61786 = false, _mut61787 = false, _mut61788 = false, _mut61789 = false, _mut61790 = false, _mut61791 = false, _mut61792 = false, _mut61793 = false, _mut61794 = false, _mut61795 = false, _mut61796 = false, _mut61797 = false, _mut61798 = false, _mut61799 = false, _mut61800 = false, _mut61801 = false, _mut61802 = false, _mut61803 = false, _mut61804 = false, _mut61805 = false, _mut61806 = false, _mut61807 = false, _mut61808 = false, _mut61809 = false, _mut61810 = false, _mut61811 = false, _mut61812 = false, _mut61813 = false, _mut61814 = false, _mut61815 = false, _mut61816 = false, _mut61817 = false, _mut61818 = false, _mut61819 = false, _mut61820 = false, _mut61821 = false, _mut61822 = false, _mut61823 = false, _mut61824 = false, _mut61825 = false, _mut61826 = false, _mut61827 = false, _mut61828 = false, _mut61829 = false, _mut61830 = false, _mut61831 = false, _mut61832 = false, _mut61833 = false, _mut61834 = false, _mut61835 = false, _mut61836 = false, _mut61837 = false, _mut61838 = false, _mut61839 = false, _mut61840 = false, _mut61841 = false, _mut61842 = false, _mut61843 = false, _mut61844 = false, _mut61845 = false, _mut61846 = false, _mut61847 = false, _mut61848 = false, _mut61849 = false, _mut61850 = false, _mut61851 = false, _mut61852 = false, _mut61853 = false, _mut61854 = false, _mut61855 = false, _mut61856 = false, _mut61857 = false, _mut61858 = false, _mut61859 = false, _mut61860 = false, _mut61861 = false, _mut61862 = false, _mut61863 = false, _mut61864 = false, _mut61865 = false, _mut61866 = false, _mut61867 = false, _mut61868 = false, _mut61869 = false, _mut61870 = false, _mut61871 = false, _mut61872 = false, _mut61873 = false, _mut61874 = false, _mut61875 = false, _mut61876 = false, _mut61877 = false, _mut61878 = false, _mut61879 = false, _mut61880 = false, _mut61881 = false, _mut61882 = false, _mut61883 = false, _mut61884 = false, _mut61885 = false, _mut61886 = false, _mut61887 = false, _mut61888 = false, _mut61889 = false, _mut61890 = false, _mut61891 = false, _mut61892 = false, _mut61893 = false, _mut61894 = false, _mut61895 = false, _mut61896 = false, _mut61897 = false, _mut61898 = false, _mut61899 = false, _mut61900 = false, _mut61901 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96");
        if (steps == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(steps.length, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61740, _mut61741, _mut61742, _mut61743, _mut61744)) {
            throw new ZeroException();
        }
        dimension = steps.length;
        // to the first one are stored.
        startConfiguration = new double[dimension][dimension];
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61763, _mut61764, _mut61765, _mut61766, _mut61767); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96");
            final double[] vertexI = startConfiguration[i];
            for (int j = 0; ROR_less(j, AOR_plus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61754, _mut61755, _mut61756, _mut61757), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61758, _mut61759, _mut61760, _mut61761, _mut61762); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96");
                if (ROR_equals(steps[j], 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61745, _mut61746, _mut61747, _mut61748, _mut61749)) {
                    throw new ZeroException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX);
                }
                System.arraycopy(steps, 0, vertexI, 0, AOR_plus(j, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_96", _mut61750, _mut61751, _mut61752, _mut61753));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131");
        if (ROR_less_equals(referenceSimplex.length, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61768, _mut61769, _mut61770, _mut61771, _mut61772)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SIMPLEX_NEED_ONE_POINT, referenceSimplex.length);
        }
        dimension = AOR_minus(referenceSimplex.length, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61773, _mut61774, _mut61775, _mut61776);
        // to the first one are stored.
        startConfiguration = new double[dimension][dimension];
        final double[] ref0 = referenceSimplex[0];
        // Loop over vertices.
        for (int i = 0; ROR_less(i, referenceSimplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61815, _mut61816, _mut61817, _mut61818, _mut61819); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131");
            final double[] refI = referenceSimplex[i];
            // Safety checks.
            if (ROR_not_equals(refI.length, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61777, _mut61778, _mut61779, _mut61780, _mut61781)) {
                throw new DimensionMismatchException(refI.length, dimension);
            }
            for (int j = 0; ROR_less(j, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61792, _mut61793, _mut61794, _mut61795, _mut61796); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131");
                final double[] refJ = referenceSimplex[j];
                boolean allEquals = true;
                for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61787, _mut61788, _mut61789, _mut61790, _mut61791); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131");
                    if (ROR_not_equals(refI[k], refJ[k], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61782, _mut61783, _mut61784, _mut61785, _mut61786)) {
                        allEquals = false;
                        break;
                    }
                }
                if (allEquals) {
                    throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, i, j);
                }
            }
            // Store vertex i position relative to vertex 0 position.
            if (ROR_greater(i, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61797, _mut61798, _mut61799, _mut61800, _mut61801)) {
                final double[] confI = startConfiguration[AOR_minus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61802, _mut61803, _mut61804, _mut61805)];
                for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61810, _mut61811, _mut61812, _mut61813, _mut61814); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131");
                    confI[k] = AOR_minus(refI[k], ref0[k], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.AbstractSimplex_131", _mut61806, _mut61807, _mut61808, _mut61809);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215");
        if (ROR_not_equals(dimension, startPoint.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61820, _mut61821, _mut61822, _mut61823, _mut61824)) {
            throw new DimensionMismatchException(dimension, startPoint.length);
        }
        // Set first vertex.
        simplex = new PointValuePair[AOR_plus(dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61825, _mut61826, _mut61827, _mut61828)];
        simplex[0] = new PointValuePair(startPoint, Double.NaN);
        // Set remaining vertices.
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61842, _mut61843, _mut61844, _mut61845, _mut61846); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215");
            final double[] confI = startConfiguration[i];
            final double[] vertexI = new double[dimension];
            for (int k = 0; ROR_less(k, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61833, _mut61834, _mut61835, _mut61836, _mut61837); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215");
                vertexI[k] = AOR_plus(startPoint[k], confI[k], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61829, _mut61830, _mut61831, _mut61832);
            }
            simplex[AOR_plus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.build_215", _mut61838, _mut61839, _mut61840, _mut61841)] = new PointValuePair(vertexI, Double.NaN);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.evaluate_243");
        // Evaluate the objective function at all non-evaluated simplex points.
        for (int i = 0; ROR_less(i, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.evaluate_243", _mut61847, _mut61848, _mut61849, _mut61850, _mut61851); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.evaluate_243");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.replaceWorstPoint_265");
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.replaceWorstPoint_265", _mut61857, _mut61858, _mut61859, _mut61860, _mut61861); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.replaceWorstPoint_265");
            if (ROR_greater(comparator.compare(simplex[i], pointValuePair), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.replaceWorstPoint_265", _mut61852, _mut61853, _mut61854, _mut61855, _mut61856)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294");
        if ((_mut61872 ? (ROR_less(index, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294", _mut61862, _mut61863, _mut61864, _mut61865, _mut61866) && ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294", _mut61867, _mut61868, _mut61869, _mut61870, _mut61871)) : (ROR_less(index, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294", _mut61862, _mut61863, _mut61864, _mut61865, _mut61866) || ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294", _mut61867, _mut61868, _mut61869, _mut61870, _mut61871)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(simplex.length, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.getPoint_294", _mut61873, _mut61874, _mut61875, _mut61876));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309");
        if ((_mut61887 ? (ROR_less(index, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309", _mut61877, _mut61878, _mut61879, _mut61880, _mut61881) && ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309", _mut61882, _mut61883, _mut61884, _mut61885, _mut61886)) : (ROR_less(index, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309", _mut61877, _mut61878, _mut61879, _mut61880, _mut61881) || ROR_greater_equals(index, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309", _mut61882, _mut61883, _mut61884, _mut61885, _mut61886)))) {
            throw new OutOfRangeException(index, 0, AOR_minus(simplex.length, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoint_309", _mut61888, _mut61889, _mut61890, _mut61891));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoints_323");
        if (ROR_not_equals(points.length, simplex.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.setPoints_323", _mut61892, _mut61893, _mut61894, _mut61895, _mut61896)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.createHypercubeSteps_337");
        final double[] steps = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.createHypercubeSteps_337", _mut61897, _mut61898, _mut61899, _mut61900, _mut61901); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.createHypercubeSteps_337");
            steps[i] = sideLength;
        }
        return steps;
    }
}
