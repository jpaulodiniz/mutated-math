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
package org.apache.commons.math3.ml.neuralnet.twod;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.io.ObjectInputStream;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.MathInternalError;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Neural network with the topology of a two-dimensional surface.
 * Each neuron defines one surface element.
 * <br/>
 * This network is primarily intended to represent a
 * <a href="http://en.wikipedia.org/wiki/Kohonen">
 *  Self Organizing Feature Map</a>.
 *
 * @see org.apache.commons.math3.ml.neuralnet.sofm
 * @since 3.3
 */
public class NeuronSquareMesh2D implements Iterable<Neuron>, Serializable {

    @Conditional
    public static boolean _mut103151 = false, _mut103152 = false, _mut103153 = false, _mut103154 = false, _mut103155 = false, _mut103156 = false, _mut103157 = false, _mut103158 = false, _mut103159 = false, _mut103160 = false, _mut103161 = false, _mut103162 = false, _mut103163 = false, _mut103164 = false, _mut103165 = false, _mut103166 = false, _mut103167 = false, _mut103168 = false, _mut103169 = false, _mut103170 = false, _mut103171 = false, _mut103172 = false, _mut103173 = false, _mut103174 = false, _mut103175 = false, _mut103176 = false, _mut103177 = false, _mut103178 = false, _mut103179 = false, _mut103180 = false, _mut103181 = false, _mut103182 = false, _mut103183 = false, _mut103184 = false, _mut103185 = false, _mut103186 = false, _mut103187 = false, _mut103188 = false, _mut103189 = false, _mut103190 = false, _mut103191 = false, _mut103192 = false, _mut103193 = false, _mut103194 = false, _mut103195 = false, _mut103196 = false, _mut103197 = false, _mut103198 = false, _mut103199 = false, _mut103200 = false, _mut103201 = false, _mut103202 = false, _mut103203 = false, _mut103204 = false, _mut103205 = false, _mut103206 = false, _mut103207 = false, _mut103208 = false, _mut103209 = false, _mut103210 = false, _mut103211 = false, _mut103212 = false, _mut103213 = false, _mut103214 = false, _mut103215 = false, _mut103216 = false, _mut103217 = false, _mut103218 = false, _mut103219 = false, _mut103220 = false, _mut103221 = false, _mut103222 = false, _mut103223 = false, _mut103224 = false, _mut103225 = false, _mut103226 = false, _mut103227 = false, _mut103228 = false, _mut103229 = false, _mut103230 = false, _mut103231 = false, _mut103232 = false, _mut103233 = false, _mut103234 = false, _mut103235 = false, _mut103236 = false, _mut103237 = false, _mut103238 = false, _mut103239 = false, _mut103240 = false, _mut103241 = false, _mut103242 = false, _mut103243 = false, _mut103244 = false, _mut103245 = false, _mut103246 = false, _mut103247 = false, _mut103248 = false, _mut103249 = false, _mut103250 = false, _mut103251 = false, _mut103252 = false, _mut103253 = false, _mut103254 = false, _mut103255 = false, _mut103256 = false, _mut103257 = false, _mut103258 = false, _mut103259 = false, _mut103260 = false, _mut103261 = false, _mut103262 = false, _mut103263 = false, _mut103264 = false, _mut103265 = false, _mut103266 = false, _mut103267 = false, _mut103268 = false, _mut103269 = false, _mut103270 = false, _mut103271 = false, _mut103272 = false, _mut103273 = false, _mut103274 = false, _mut103275 = false, _mut103276 = false, _mut103277 = false, _mut103278 = false, _mut103279 = false, _mut103280 = false, _mut103281 = false, _mut103282 = false, _mut103283 = false, _mut103284 = false, _mut103285 = false, _mut103286 = false, _mut103287 = false, _mut103288 = false, _mut103289 = false, _mut103290 = false, _mut103291 = false, _mut103292 = false, _mut103293 = false, _mut103294 = false, _mut103295 = false, _mut103296 = false, _mut103297 = false, _mut103298 = false, _mut103299 = false, _mut103300 = false, _mut103301 = false, _mut103302 = false, _mut103303 = false, _mut103304 = false, _mut103305 = false, _mut103306 = false, _mut103307 = false, _mut103308 = false, _mut103309 = false, _mut103310 = false, _mut103311 = false, _mut103312 = false, _mut103313 = false, _mut103314 = false, _mut103315 = false, _mut103316 = false, _mut103317 = false, _mut103318 = false, _mut103319 = false, _mut103320 = false, _mut103321 = false, _mut103322 = false, _mut103323 = false, _mut103324 = false, _mut103325 = false, _mut103326 = false, _mut103327 = false, _mut103328 = false, _mut103329 = false, _mut103330 = false, _mut103331 = false, _mut103332 = false, _mut103333 = false, _mut103334 = false, _mut103335 = false, _mut103336 = false, _mut103337 = false, _mut103338 = false, _mut103339 = false, _mut103340 = false, _mut103341 = false, _mut103342 = false, _mut103343 = false, _mut103344 = false, _mut103345 = false, _mut103346 = false, _mut103347 = false, _mut103348 = false, _mut103349 = false, _mut103350 = false, _mut103351 = false, _mut103352 = false, _mut103353 = false, _mut103354 = false, _mut103355 = false, _mut103356 = false, _mut103357 = false, _mut103358 = false, _mut103359 = false, _mut103360 = false, _mut103361 = false, _mut103362 = false, _mut103363 = false, _mut103364 = false, _mut103365 = false, _mut103366 = false, _mut103367 = false, _mut103368 = false, _mut103369 = false, _mut103370 = false, _mut103371 = false, _mut103372 = false, _mut103373 = false, _mut103374 = false, _mut103375 = false, _mut103376 = false, _mut103377 = false, _mut103378 = false, _mut103379 = false, _mut103380 = false, _mut103381 = false, _mut103382 = false, _mut103383 = false, _mut103384 = false, _mut103385 = false, _mut103386 = false, _mut103387 = false, _mut103388 = false, _mut103389 = false, _mut103390 = false, _mut103391 = false, _mut103392 = false, _mut103393 = false, _mut103394 = false, _mut103395 = false, _mut103396 = false, _mut103397 = false, _mut103398 = false, _mut103399 = false, _mut103400 = false, _mut103401 = false, _mut103402 = false, _mut103403 = false, _mut103404 = false, _mut103405 = false, _mut103406 = false, _mut103407 = false, _mut103408 = false, _mut103409 = false, _mut103410 = false, _mut103411 = false, _mut103412 = false, _mut103413 = false, _mut103414 = false, _mut103415 = false, _mut103416 = false, _mut103417 = false, _mut103418 = false, _mut103419 = false, _mut103420 = false, _mut103421 = false, _mut103422 = false, _mut103423 = false, _mut103424 = false, _mut103425 = false, _mut103426 = false, _mut103427 = false, _mut103428 = false, _mut103429 = false, _mut103430 = false, _mut103431 = false, _mut103432 = false, _mut103433 = false, _mut103434 = false, _mut103435 = false, _mut103436 = false, _mut103437 = false, _mut103438 = false, _mut103439 = false, _mut103440 = false, _mut103441 = false, _mut103442 = false, _mut103443 = false, _mut103444 = false, _mut103445 = false, _mut103446 = false, _mut103447 = false, _mut103448 = false, _mut103449 = false, _mut103450 = false, _mut103451 = false, _mut103452 = false, _mut103453 = false, _mut103454 = false, _mut103455 = false, _mut103456 = false, _mut103457 = false, _mut103458 = false, _mut103459 = false, _mut103460 = false, _mut103461 = false, _mut103462 = false, _mut103463 = false, _mut103464 = false, _mut103465 = false, _mut103466 = false, _mut103467 = false, _mut103468 = false, _mut103469 = false, _mut103470 = false, _mut103471 = false, _mut103472 = false, _mut103473 = false, _mut103474 = false, _mut103475 = false, _mut103476 = false, _mut103477 = false, _mut103478 = false, _mut103479 = false, _mut103480 = false, _mut103481 = false, _mut103482 = false, _mut103483 = false, _mut103484 = false, _mut103485 = false, _mut103486 = false, _mut103487 = false, _mut103488 = false, _mut103489 = false, _mut103490 = false, _mut103491 = false, _mut103492 = false, _mut103493 = false, _mut103494 = false, _mut103495 = false, _mut103496 = false, _mut103497 = false, _mut103498 = false, _mut103499 = false, _mut103500 = false, _mut103501 = false, _mut103502 = false, _mut103503 = false, _mut103504 = false, _mut103505 = false, _mut103506 = false, _mut103507 = false, _mut103508 = false, _mut103509 = false, _mut103510 = false, _mut103511 = false, _mut103512 = false, _mut103513 = false, _mut103514 = false, _mut103515 = false, _mut103516 = false, _mut103517 = false, _mut103518 = false, _mut103519 = false, _mut103520 = false, _mut103521 = false, _mut103522 = false, _mut103523 = false, _mut103524 = false, _mut103525 = false, _mut103526 = false, _mut103527 = false, _mut103528 = false, _mut103529 = false, _mut103530 = false, _mut103531 = false, _mut103532 = false, _mut103533 = false, _mut103534 = false, _mut103535 = false, _mut103536 = false, _mut103537 = false, _mut103538 = false, _mut103539 = false, _mut103540 = false, _mut103541 = false, _mut103542 = false, _mut103543 = false, _mut103544 = false, _mut103545 = false, _mut103546 = false, _mut103547 = false, _mut103548 = false, _mut103549 = false, _mut103550 = false, _mut103551 = false, _mut103552 = false, _mut103553 = false, _mut103554 = false, _mut103555 = false, _mut103556 = false, _mut103557 = false, _mut103558 = false, _mut103559 = false;

    /**
     * Serial version ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Underlying network.
     */
    private final Network network;

    /**
     * Number of rows.
     */
    private final int numberOfRows;

    /**
     * Number of columns.
     */
    private final int numberOfColumns;

    /**
     * Wrap.
     */
    private final boolean wrapRows;

    /**
     * Wrap.
     */
    private final boolean wrapColumns;

    /**
     * Neighbourhood type.
     */
    private final SquareNeighbourhood neighbourhood;

    /**
     * Mapping of the 2D coordinates (in the rectangular mesh) to
     * the neuron identifiers (attributed by the {@link #network}
     * instance).
     */
    private final long[][] identifiers;

    /**
     * Horizontal (along row) direction.
     * @since 3.6
     */
    public enum HorizontalDirection {

        /**
         * Column at the right of the current column.
         */
        RIGHT,
        /**
         * Current column.
         */
        CENTER,
        /**
         * Column at the left of the current column.
         */
        LEFT
    }

    /**
     * Vertical (along column) direction.
     * @since 3.6
     */
    public enum VerticalDirection {

        /**
         * Row above the current row.
         */
        UP,
        /**
         * Current row.
         */
        CENTER,
        /**
         * Row below the current row.
         */
        DOWN
    }

    /**
     * Constructor with restricted access, solely used for deserialization.
     *
     * @param wrapRowDim Whether to wrap the first dimension (i.e the first
     * and last neurons will be linked together).
     * @param wrapColDim Whether to wrap the second dimension (i.e the first
     * and last neurons will be linked together).
     * @param neighbourhoodType Neighbourhood type.
     * @param featuresList Arrays that will initialize the features sets of
     * the network's neurons.
     * @throws NumberIsTooSmallException if {@code numRows < 2} or
     * {@code numCols < 2}.
     */
    NeuronSquareMesh2D(boolean wrapRowDim, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, double[][][] featuresList) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106");
        numberOfRows = featuresList.length;
        numberOfColumns = featuresList[0].length;
        if (ROR_less(numberOfRows, 2, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106", _mut103151, _mut103152, _mut103153, _mut103154, _mut103155)) {
            throw new NumberIsTooSmallException(numberOfRows, 2, true);
        }
        if (ROR_less(numberOfColumns, 2, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106", _mut103156, _mut103157, _mut103158, _mut103159, _mut103160)) {
            throw new NumberIsTooSmallException(numberOfColumns, 2, true);
        }
        wrapRows = wrapRowDim;
        wrapColumns = wrapColDim;
        neighbourhood = neighbourhoodType;
        final int fLen = featuresList[0][0].length;
        network = new Network(0, fLen);
        identifiers = new long[numberOfRows][numberOfColumns];
        // Add neurons.
        for (int i = 0; ROR_less(i, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106", _mut103166, _mut103167, _mut103168, _mut103169, _mut103170); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106");
            for (int j = 0; ROR_less(j, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106", _mut103161, _mut103162, _mut103163, _mut103164, _mut103165); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_106");
                identifiers[i][j] = network.createNeuron(featuresList[i][j]);
            }
        }
        // Add links.
        createLinks();
    }

    /**
     * Creates a two-dimensional network composed of square cells:
     * Each neuron not located on the border of the mesh has four
     * neurons linked to it.
     * <br/>
     * The links are bi-directional.
     * <br/>
     * The topology of the network can also be a cylinder (if one
     * of the dimensions is wrapped) or a torus (if both dimensions
     * are wrapped).
     *
     * @param numRows Number of neurons in the first dimension.
     * @param wrapRowDim Whether to wrap the first dimension (i.e the first
     * and last neurons will be linked together).
     * @param numCols Number of neurons in the second dimension.
     * @param wrapColDim Whether to wrap the second dimension (i.e the first
     * and last neurons will be linked together).
     * @param neighbourhoodType Neighbourhood type.
     * @param featureInit Array of functions that will initialize the
     * corresponding element of the features set of each newly created
     * neuron. In particular, the size of this array defines the size of
     * feature set.
     * @throws NumberIsTooSmallException if {@code numRows < 2} or
     * {@code numCols < 2}.
     */
    public NeuronSquareMesh2D(int numRows, boolean wrapRowDim, int numCols, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, FeatureInitializer[] featureInit) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164");
        if (ROR_less(numRows, 2, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164", _mut103171, _mut103172, _mut103173, _mut103174, _mut103175)) {
            throw new NumberIsTooSmallException(numRows, 2, true);
        }
        if (ROR_less(numCols, 2, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164", _mut103176, _mut103177, _mut103178, _mut103179, _mut103180)) {
            throw new NumberIsTooSmallException(numCols, 2, true);
        }
        numberOfRows = numRows;
        wrapRows = wrapRowDim;
        numberOfColumns = numCols;
        wrapColumns = wrapColDim;
        neighbourhood = neighbourhoodType;
        identifiers = new long[numberOfRows][numberOfColumns];
        final int fLen = featureInit.length;
        network = new Network(0, fLen);
        // Add neurons.
        for (int i = 0; ROR_less(i, numRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164", _mut103191, _mut103192, _mut103193, _mut103194, _mut103195); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164");
            for (int j = 0; ROR_less(j, numCols, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164", _mut103186, _mut103187, _mut103188, _mut103189, _mut103190); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164");
                final double[] features = new double[fLen];
                for (int fIndex = 0; ROR_less(fIndex, fLen, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164", _mut103181, _mut103182, _mut103183, _mut103184, _mut103185); fIndex++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.NeuronSquareMesh2D_164");
                    features[fIndex] = featureInit[fIndex].value();
                }
                identifiers[i][j] = network.createNeuron(features);
            }
        }
        // Add links.
        createLinks();
    }

    /**
     * Constructor with restricted access, solely used for making a
     * {@link #copy() deep copy}.
     *
     * @param wrapRowDim Whether to wrap the first dimension (i.e the first
     * and last neurons will be linked together).
     * @param wrapColDim Whether to wrap the second dimension (i.e the first
     * and last neurons will be linked together).
     * @param neighbourhoodType Neighbourhood type.
     * @param net Underlying network.
     * @param idGrid Neuron identifiers.
     */
    private NeuronSquareMesh2D(boolean wrapRowDim, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, Network net, long[][] idGrid) {
        numberOfRows = idGrid.length;
        numberOfColumns = idGrid[0].length;
        wrapRows = wrapRowDim;
        wrapColumns = wrapColDim;
        neighbourhood = neighbourhoodType;
        network = net;
        identifiers = idGrid;
    }

    /**
     * Performs a deep copy of this instance.
     * Upon return, the copied and original instances will be independent:
     * Updating one will not affect the other.
     *
     * @return a new instance with the same state as this instance.
     * @since 3.6
     */
    public synchronized NeuronSquareMesh2D copy() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.copy_236");
        final long[][] idGrid = new long[numberOfRows][numberOfColumns];
        for (int r = 0; ROR_less(r, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.copy_236", _mut103201, _mut103202, _mut103203, _mut103204, _mut103205); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.copy_236");
            for (int c = 0; ROR_less(c, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.copy_236", _mut103196, _mut103197, _mut103198, _mut103199, _mut103200); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.copy_236");
                idGrid[r][c] = identifiers[r][c];
            }
        }
        return new NeuronSquareMesh2D(wrapRows, wrapColumns, neighbourhood, network.copy(), idGrid);
    }

    /**
     * {@inheritDoc}
     *  @since 3.6
     */
    public Iterator<Neuron> iterator() {
        return network.iterator();
    }

    /**
     * Retrieves the underlying network.
     * A reference is returned (enabling, for example, the network to be
     * trained).
     * This also implies that calling methods that modify the {@link Network}
     * topology may cause this class to become inconsistent.
     *
     * @return the network.
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Gets the number of neurons in each row of this map.
     *
     * @return the number of rows.
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Gets the number of neurons in each column of this map.
     *
     * @return the number of column.
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Retrieves the neuron at location {@code (i, j)} in the map.
     * The neuron at position {@code (0, 0)} is located at the upper-left
     * corner of the map.
     *
     * @param i Row index.
     * @param j Column index.
     * @return the neuron at {@code (i, j)}.
     * @throws OutOfRangeException if {@code i} or {@code j} is
     * out of range.
     *
     * @see #getNeuron(int,int,HorizontalDirection,VerticalDirection)
     */
    public Neuron getNeuron(int i, int j) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303");
        if ((_mut103216 ? (ROR_less(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103206, _mut103207, _mut103208, _mut103209, _mut103210) && ROR_greater_equals(i, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103211, _mut103212, _mut103213, _mut103214, _mut103215)) : (ROR_less(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103206, _mut103207, _mut103208, _mut103209, _mut103210) || ROR_greater_equals(i, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103211, _mut103212, _mut103213, _mut103214, _mut103215)))) {
            throw new OutOfRangeException(i, 0, AOR_minus(numberOfRows, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103217, _mut103218, _mut103219, _mut103220));
        }
        if ((_mut103231 ? (ROR_less(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103221, _mut103222, _mut103223, _mut103224, _mut103225) && ROR_greater_equals(j, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103226, _mut103227, _mut103228, _mut103229, _mut103230)) : (ROR_less(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103221, _mut103222, _mut103223, _mut103224, _mut103225) || ROR_greater_equals(j, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103226, _mut103227, _mut103228, _mut103229, _mut103230)))) {
            throw new OutOfRangeException(j, 0, AOR_minus(numberOfColumns, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getNeuron_303", _mut103232, _mut103233, _mut103234, _mut103235));
        }
        return network.getNeuron(identifiers[i][j]);
    }

    /**
     * Retrieves the neuron at {@code (location[0], location[1])} in the map.
     * The neuron at position {@code (0, 0)} is located at the upper-left
     * corner of the map.
     *
     * @param row Row index.
     * @param col Column index.
     * @param alongRowDir Direction along the given {@code row} (i.e. an
     * offset will be added to the given <em>column</em> index.
     * @param alongColDir Direction along the given {@code col} (i.e. an
     * offset will be added to the given <em>row</em> index.
     * @return the neuron at the requested location, or {@code null} if
     * the location is not on the map.
     *
     * @see #getNeuron(int,int)
     */
    public Neuron getNeuron(int row, int col, HorizontalDirection alongRowDir, VerticalDirection alongColDir) {
        final int[] location = getLocation(row, col, alongRowDir, alongColDir);
        return location == null ? null : getNeuron(location[0], location[1]);
    }

    /**
     * Computes the location of a neighbouring neuron.
     * It will return {@code null} if the resulting location is not part
     * of the map.
     * Position {@code (0, 0)} is at the upper-left corner of the map.
     *
     * @param row Row index.
     * @param col Column index.
     * @param alongRowDir Direction along the given {@code row} (i.e. an
     * offset will be added to the given <em>column</em> index.
     * @param alongColDir Direction along the given {@code col} (i.e. an
     * offset will be added to the given <em>row</em> index.
     * @return an array of length 2 containing the indices of the requested
     * location, or {@code null} if that location is not part of the map.
     *
     * @see #getNeuron(int,int)
     */
    private int[] getLocation(int row, int col, HorizontalDirection alongRowDir, VerticalDirection alongColDir) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359");
        final int colOffset;
        switch(alongRowDir) {
            case LEFT:
                colOffset = -1;
                break;
            case RIGHT:
                colOffset = 1;
                break;
            case CENTER:
                colOffset = 0;
                break;
            default:
                // Should never happen.
                throw new MathInternalError();
        }
        int colIndex = AOR_plus(col, colOffset, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103236, _mut103237, _mut103238, _mut103239);
        if (wrapColumns) {
            if (ROR_less(colIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103240, _mut103241, _mut103242, _mut103243, _mut103244)) {
                colIndex += numberOfColumns;
            } else {
                colIndex %= numberOfColumns;
            }
        }
        final int rowOffset;
        switch(alongColDir) {
            case UP:
                rowOffset = -1;
                break;
            case DOWN:
                rowOffset = 1;
                break;
            case CENTER:
                rowOffset = 0;
                break;
            default:
                // Should never happen.
                throw new MathInternalError();
        }
        int rowIndex = AOR_plus(row, rowOffset, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103245, _mut103246, _mut103247, _mut103248);
        if (wrapRows) {
            if (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103249, _mut103250, _mut103251, _mut103252, _mut103253)) {
                rowIndex += numberOfRows;
            } else {
                rowIndex %= numberOfRows;
            }
        }
        if ((_mut103276 ? ((_mut103270 ? ((_mut103264 ? (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) && ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263)) : (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) || ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263))) && ROR_less(colIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103265, _mut103266, _mut103267, _mut103268, _mut103269)) : ((_mut103264 ? (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) && ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263)) : (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) || ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263))) || ROR_less(colIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103265, _mut103266, _mut103267, _mut103268, _mut103269))) && ROR_greater_equals(colIndex, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103271, _mut103272, _mut103273, _mut103274, _mut103275)) : ((_mut103270 ? ((_mut103264 ? (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) && ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263)) : (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) || ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263))) && ROR_less(colIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103265, _mut103266, _mut103267, _mut103268, _mut103269)) : ((_mut103264 ? (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) && ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263)) : (ROR_less(rowIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103254, _mut103255, _mut103256, _mut103257, _mut103258) || ROR_greater_equals(rowIndex, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103259, _mut103260, _mut103261, _mut103262, _mut103263))) || ROR_less(colIndex, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103265, _mut103266, _mut103267, _mut103268, _mut103269))) || ROR_greater_equals(colIndex, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.getLocation_359", _mut103271, _mut103272, _mut103273, _mut103274, _mut103275)))) {
            return null;
        } else {
            return new int[] { rowIndex, colIndex };
        }
    }

    /**
     * Creates the neighbour relationships between neurons.
     */
    private void createLinks() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424");
        // "linkEnd" will store the identifiers of the "neighbours".
        final List<Long> linkEnd = new ArrayList<Long>();
        final int iLast = AOR_minus(numberOfRows, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103277, _mut103278, _mut103279, _mut103280);
        final int jLast = AOR_minus(numberOfColumns, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103281, _mut103282, _mut103283, _mut103284);
        for (int i = 0; ROR_less(i, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103545, _mut103546, _mut103547, _mut103548, _mut103549); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424");
            for (int j = 0; ROR_less(j, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103540, _mut103541, _mut103542, _mut103543, _mut103544); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424");
                linkEnd.clear();
                switch(neighbourhood) {
                    case MOORE:
                        // Add links to "diagonal" neighbours.
                        if (ROR_greater(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103285, _mut103286, _mut103287, _mut103288, _mut103289)) {
                            if (ROR_greater(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103290, _mut103291, _mut103292, _mut103293, _mut103294)) {
                                linkEnd.add(identifiers[AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103299, _mut103300, _mut103301, _mut103302)][AOR_minus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103295, _mut103296, _mut103297, _mut103298)]);
                            }
                            if (ROR_less(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103303, _mut103304, _mut103305, _mut103306, _mut103307)) {
                                linkEnd.add(identifiers[AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103312, _mut103313, _mut103314, _mut103315)][AOR_plus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103308, _mut103309, _mut103310, _mut103311)]);
                            }
                        }
                        if (ROR_less(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103316, _mut103317, _mut103318, _mut103319, _mut103320)) {
                            if (ROR_greater(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103321, _mut103322, _mut103323, _mut103324, _mut103325)) {
                                linkEnd.add(identifiers[AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103330, _mut103331, _mut103332, _mut103333)][AOR_minus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103326, _mut103327, _mut103328, _mut103329)]);
                            }
                            if (ROR_less(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103334, _mut103335, _mut103336, _mut103337, _mut103338)) {
                                linkEnd.add(identifiers[AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103343, _mut103344, _mut103345, _mut103346)][AOR_plus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103339, _mut103340, _mut103341, _mut103342)]);
                            }
                        }
                        if (wrapRows) {
                            if (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103347, _mut103348, _mut103349, _mut103350, _mut103351)) {
                                if (ROR_greater(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103375, _mut103376, _mut103377, _mut103378, _mut103379)) {
                                    linkEnd.add(identifiers[iLast][AOR_minus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103380, _mut103381, _mut103382, _mut103383)]);
                                }
                                if (ROR_less(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103384, _mut103385, _mut103386, _mut103387, _mut103388)) {
                                    linkEnd.add(identifiers[iLast][AOR_plus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103389, _mut103390, _mut103391, _mut103392)]);
                                }
                            } else if (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103352, _mut103353, _mut103354, _mut103355, _mut103356)) {
                                if (ROR_greater(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103357, _mut103358, _mut103359, _mut103360, _mut103361)) {
                                    linkEnd.add(identifiers[0][AOR_minus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103362, _mut103363, _mut103364, _mut103365)]);
                                }
                                if (ROR_less(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103366, _mut103367, _mut103368, _mut103369, _mut103370)) {
                                    linkEnd.add(identifiers[0][AOR_plus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103371, _mut103372, _mut103373, _mut103374)]);
                                }
                            }
                        }
                        if (wrapColumns) {
                            if (ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103393, _mut103394, _mut103395, _mut103396, _mut103397)) {
                                if (ROR_greater(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103421, _mut103422, _mut103423, _mut103424, _mut103425)) {
                                    linkEnd.add(identifiers[AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103426, _mut103427, _mut103428, _mut103429)][jLast]);
                                }
                                if (ROR_less(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103430, _mut103431, _mut103432, _mut103433, _mut103434)) {
                                    linkEnd.add(identifiers[AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103435, _mut103436, _mut103437, _mut103438)][jLast]);
                                }
                            } else if (ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103398, _mut103399, _mut103400, _mut103401, _mut103402)) {
                                if (ROR_greater(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103403, _mut103404, _mut103405, _mut103406, _mut103407)) {
                                    linkEnd.add(identifiers[AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103408, _mut103409, _mut103410, _mut103411)][0]);
                                }
                                if (ROR_less(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103412, _mut103413, _mut103414, _mut103415, _mut103416)) {
                                    linkEnd.add(identifiers[AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103417, _mut103418, _mut103419, _mut103420)][0]);
                                }
                            }
                        }
                        if ((_mut103439 ? (wrapRows || wrapColumns) : (wrapRows && wrapColumns))) {
                            if ((_mut103450 ? (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103440, _mut103441, _mut103442, _mut103443, _mut103444) || ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103445, _mut103446, _mut103447, _mut103448, _mut103449)) : (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103440, _mut103441, _mut103442, _mut103443, _mut103444) && ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103445, _mut103446, _mut103447, _mut103448, _mut103449)))) {
                                linkEnd.add(identifiers[iLast][jLast]);
                            } else if ((_mut103461 ? (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103451, _mut103452, _mut103453, _mut103454, _mut103455) || ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103456, _mut103457, _mut103458, _mut103459, _mut103460)) : (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103451, _mut103452, _mut103453, _mut103454, _mut103455) && ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103456, _mut103457, _mut103458, _mut103459, _mut103460)))) {
                                linkEnd.add(identifiers[iLast][0]);
                            } else if ((_mut103472 ? (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103462, _mut103463, _mut103464, _mut103465, _mut103466) || ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103467, _mut103468, _mut103469, _mut103470, _mut103471)) : (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103462, _mut103463, _mut103464, _mut103465, _mut103466) && ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103467, _mut103468, _mut103469, _mut103470, _mut103471)))) {
                                linkEnd.add(identifiers[0][jLast]);
                            } else if ((_mut103483 ? (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103473, _mut103474, _mut103475, _mut103476, _mut103477) || ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103478, _mut103479, _mut103480, _mut103481, _mut103482)) : (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103473, _mut103474, _mut103475, _mut103476, _mut103477) && ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103478, _mut103479, _mut103480, _mut103481, _mut103482)))) {
                                linkEnd.add(identifiers[0][0]);
                            }
                        }
                    // fallthru (CheckStyle)
                    case VON_NEUMANN:
                        // Links to preceding and following "row".
                        if (ROR_greater(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103484, _mut103485, _mut103486, _mut103487, _mut103488)) {
                            linkEnd.add(identifiers[AOR_minus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103489, _mut103490, _mut103491, _mut103492)][j]);
                        }
                        if (ROR_less(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103493, _mut103494, _mut103495, _mut103496, _mut103497)) {
                            linkEnd.add(identifiers[AOR_plus(i, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103498, _mut103499, _mut103500, _mut103501)][j]);
                        }
                        if (wrapRows) {
                            if (ROR_equals(i, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103502, _mut103503, _mut103504, _mut103505, _mut103506)) {
                                linkEnd.add(identifiers[iLast][j]);
                            } else if (ROR_equals(i, iLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103507, _mut103508, _mut103509, _mut103510, _mut103511)) {
                                linkEnd.add(identifiers[0][j]);
                            }
                        }
                        // Links to preceding and following "column".
                        if (ROR_greater(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103512, _mut103513, _mut103514, _mut103515, _mut103516)) {
                            linkEnd.add(identifiers[i][AOR_minus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103517, _mut103518, _mut103519, _mut103520)]);
                        }
                        if (ROR_less(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103521, _mut103522, _mut103523, _mut103524, _mut103525)) {
                            linkEnd.add(identifiers[i][AOR_plus(j, 1, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103526, _mut103527, _mut103528, _mut103529)]);
                        }
                        if (wrapColumns) {
                            if (ROR_equals(j, 0, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103530, _mut103531, _mut103532, _mut103533, _mut103534)) {
                                linkEnd.add(identifiers[i][jLast]);
                            } else if (ROR_equals(j, jLast, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424", _mut103535, _mut103536, _mut103537, _mut103538, _mut103539)) {
                                linkEnd.add(identifiers[i][0]);
                            }
                        }
                        break;
                    default:
                        // Cannot happen.
                        throw new MathInternalError();
                }
                final Neuron aNeuron = network.getNeuron(identifiers[i][j]);
                for (long b : linkEnd) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.createLinks_424");
                    final Neuron bNeuron = network.getNeuron(b);
                    // The reverse links will be added as the loop proceeds.
                    network.addLink(aNeuron, bNeuron);
                }
            }
        }
    }

    /**
     * Prevents proxy bypass.
     *
     * @param in Input stream.
     */
    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    /**
     * Custom serialization.
     *
     * @return the proxy instance that will be actually serialized.
     */
    private Object writeReplace() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.writeReplace_570");
        final double[][][] featuresList = new double[numberOfRows][numberOfColumns][];
        for (int i = 0; ROR_less(i, numberOfRows, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.writeReplace_570", _mut103555, _mut103556, _mut103557, _mut103558, _mut103559); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.writeReplace_570");
            for (int j = 0; ROR_less(j, numberOfColumns, "org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.writeReplace_570", _mut103550, _mut103551, _mut103552, _mut103553, _mut103554); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.writeReplace_570");
                featuresList[i][j] = getNeuron(i, j).getFeatures();
            }
        }
        return new SerializationProxy(wrapRows, wrapColumns, neighbourhood, featuresList);
    }

    /**
     * Serialization.
     */
    private static class SerializationProxy implements Serializable {

        /**
         * Serializable.
         */
        private static final long serialVersionUID = 20130226L;

        /**
         * Wrap.
         */
        private final boolean wrapRows;

        /**
         * Wrap.
         */
        private final boolean wrapColumns;

        /**
         * Neighbourhood type.
         */
        private final SquareNeighbourhood neighbourhood;

        /**
         * Neurons' features.
         */
        private final double[][][] featuresList;

        /**
         * @param wrapRows Whether the row dimension is wrapped.
         * @param wrapColumns Whether the column dimension is wrapped.
         * @param neighbourhood Neighbourhood type.
         * @param featuresList List of neurons features.
         * {@code neuronList}.
         */
        SerializationProxy(boolean wrapRows, boolean wrapColumns, SquareNeighbourhood neighbourhood, double[][][] featuresList) {
            this.wrapRows = wrapRows;
            this.wrapColumns = wrapColumns;
            this.neighbourhood = neighbourhood;
            this.featuresList = featuresList;
        }

        /**
         * Custom serialization.
         *
         * @return the {@link Neuron} for which this instance is the proxy.
         */
        private Object readResolve() {
            return new NeuronSquareMesh2D(wrapRows, wrapColumns, neighbourhood, featuresList);
        }
    }
}
