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
package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * First derivative computation with large number of variables.
 * <p>
 * This class plays a similar role to {@link DerivativeStructure}, with
 * a focus on efficiency when dealing with large number of independent variables
 * and most computation depend only on a few of them, and when only first derivative
 * is desired. When these conditions are met, this class should be much faster than
 * {@link DerivativeStructure} and use less memory.
 * </p>
 *
 * @since 3.3
 */
public class SparseGradient implements RealFieldElement<SparseGradient>, Serializable {

    @Conditional
    public static boolean _mut96288 = false, _mut96289 = false, _mut96290 = false, _mut96291 = false, _mut96292 = false, _mut96293 = false, _mut96294 = false, _mut96295 = false, _mut96296 = false, _mut96297 = false, _mut96298 = false, _mut96299 = false, _mut96300 = false, _mut96301 = false, _mut96302 = false, _mut96303 = false, _mut96304 = false, _mut96305 = false, _mut96306 = false, _mut96307 = false, _mut96308 = false, _mut96309 = false, _mut96310 = false, _mut96311 = false, _mut96312 = false, _mut96313 = false, _mut96314 = false, _mut96315 = false, _mut96316 = false, _mut96317 = false, _mut96318 = false, _mut96319 = false, _mut96320 = false, _mut96321 = false, _mut96322 = false, _mut96323 = false, _mut96324 = false, _mut96325 = false, _mut96326 = false, _mut96327 = false, _mut96328 = false, _mut96329 = false, _mut96330 = false, _mut96331 = false, _mut96332 = false, _mut96333 = false, _mut96334 = false, _mut96335 = false, _mut96336 = false, _mut96337 = false, _mut96338 = false, _mut96339 = false, _mut96340 = false, _mut96341 = false, _mut96342 = false, _mut96343 = false, _mut96344 = false, _mut96345 = false, _mut96346 = false, _mut96347 = false, _mut96348 = false, _mut96349 = false, _mut96350 = false, _mut96351 = false, _mut96352 = false, _mut96353 = false, _mut96354 = false, _mut96355 = false, _mut96356 = false, _mut96357 = false, _mut96358 = false, _mut96359 = false, _mut96360 = false, _mut96361 = false, _mut96362 = false, _mut96363 = false, _mut96364 = false, _mut96365 = false, _mut96366 = false, _mut96367 = false, _mut96368 = false, _mut96369 = false, _mut96370 = false, _mut96371 = false, _mut96372 = false, _mut96373 = false, _mut96374 = false, _mut96375 = false, _mut96376 = false, _mut96377 = false, _mut96378 = false, _mut96379 = false, _mut96380 = false, _mut96381 = false, _mut96382 = false, _mut96383 = false, _mut96384 = false, _mut96385 = false, _mut96386 = false, _mut96387 = false, _mut96388 = false, _mut96389 = false, _mut96390 = false, _mut96391 = false, _mut96392 = false, _mut96393 = false, _mut96394 = false, _mut96395 = false, _mut96396 = false, _mut96397 = false, _mut96398 = false, _mut96399 = false, _mut96400 = false, _mut96401 = false, _mut96402 = false, _mut96403 = false, _mut96404 = false, _mut96405 = false, _mut96406 = false, _mut96407 = false, _mut96408 = false, _mut96409 = false, _mut96410 = false, _mut96411 = false, _mut96412 = false, _mut96413 = false, _mut96414 = false, _mut96415 = false, _mut96416 = false, _mut96417 = false, _mut96418 = false, _mut96419 = false, _mut96420 = false, _mut96421 = false, _mut96422 = false, _mut96423 = false, _mut96424 = false, _mut96425 = false, _mut96426 = false, _mut96427 = false, _mut96428 = false, _mut96429 = false, _mut96430 = false, _mut96431 = false, _mut96432 = false, _mut96433 = false, _mut96434 = false, _mut96435 = false, _mut96436 = false, _mut96437 = false, _mut96438 = false, _mut96439 = false, _mut96440 = false, _mut96441 = false, _mut96442 = false, _mut96443 = false, _mut96444 = false, _mut96445 = false, _mut96446 = false, _mut96447 = false, _mut96448 = false, _mut96449 = false, _mut96450 = false, _mut96451 = false, _mut96452 = false, _mut96453 = false, _mut96454 = false, _mut96455 = false, _mut96456 = false, _mut96457 = false, _mut96458 = false, _mut96459 = false, _mut96460 = false, _mut96461 = false, _mut96462 = false, _mut96463 = false, _mut96464 = false, _mut96465 = false, _mut96466 = false, _mut96467 = false, _mut96468 = false, _mut96469 = false, _mut96470 = false, _mut96471 = false, _mut96472 = false, _mut96473 = false, _mut96474 = false, _mut96475 = false, _mut96476 = false, _mut96477 = false, _mut96478 = false, _mut96479 = false, _mut96480 = false, _mut96481 = false, _mut96482 = false, _mut96483 = false, _mut96484 = false, _mut96485 = false, _mut96486 = false, _mut96487 = false, _mut96488 = false, _mut96489 = false, _mut96490 = false, _mut96491 = false, _mut96492 = false, _mut96493 = false, _mut96494 = false, _mut96495 = false, _mut96496 = false, _mut96497 = false, _mut96498 = false, _mut96499 = false, _mut96500 = false, _mut96501 = false, _mut96502 = false, _mut96503 = false, _mut96504 = false, _mut96505 = false, _mut96506 = false, _mut96507 = false, _mut96508 = false, _mut96509 = false, _mut96510 = false, _mut96511 = false, _mut96512 = false, _mut96513 = false, _mut96514 = false, _mut96515 = false, _mut96516 = false, _mut96517 = false, _mut96518 = false, _mut96519 = false, _mut96520 = false, _mut96521 = false, _mut96522 = false, _mut96523 = false, _mut96524 = false, _mut96525 = false, _mut96526 = false, _mut96527 = false, _mut96528 = false, _mut96529 = false, _mut96530 = false, _mut96531 = false, _mut96532 = false, _mut96533 = false, _mut96534 = false, _mut96535 = false, _mut96536 = false, _mut96537 = false, _mut96538 = false, _mut96539 = false, _mut96540 = false, _mut96541 = false, _mut96542 = false, _mut96543 = false, _mut96544 = false, _mut96545 = false, _mut96546 = false, _mut96547 = false, _mut96548 = false, _mut96549 = false, _mut96550 = false, _mut96551 = false, _mut96552 = false, _mut96553 = false, _mut96554 = false, _mut96555 = false, _mut96556 = false, _mut96557 = false, _mut96558 = false, _mut96559 = false, _mut96560 = false, _mut96561 = false, _mut96562 = false, _mut96563 = false, _mut96564 = false, _mut96565 = false, _mut96566 = false, _mut96567 = false, _mut96568 = false, _mut96569 = false, _mut96570 = false, _mut96571 = false, _mut96572 = false, _mut96573 = false, _mut96574 = false, _mut96575 = false, _mut96576 = false, _mut96577 = false, _mut96578 = false, _mut96579 = false, _mut96580 = false, _mut96581 = false, _mut96582 = false, _mut96583 = false, _mut96584 = false, _mut96585 = false, _mut96586 = false, _mut96587 = false, _mut96588 = false, _mut96589 = false, _mut96590 = false, _mut96591 = false, _mut96592 = false, _mut96593 = false, _mut96594 = false, _mut96595 = false, _mut96596 = false, _mut96597 = false, _mut96598 = false, _mut96599 = false, _mut96600 = false, _mut96601 = false, _mut96602 = false, _mut96603 = false, _mut96604 = false, _mut96605 = false, _mut96606 = false, _mut96607 = false, _mut96608 = false, _mut96609 = false, _mut96610 = false, _mut96611 = false, _mut96612 = false, _mut96613 = false, _mut96614 = false, _mut96615 = false, _mut96616 = false, _mut96617 = false, _mut96618 = false, _mut96619 = false, _mut96620 = false, _mut96621 = false, _mut96622 = false, _mut96623 = false, _mut96624 = false, _mut96625 = false, _mut96626 = false, _mut96627 = false, _mut96628 = false, _mut96629 = false, _mut96630 = false, _mut96631 = false, _mut96632 = false, _mut96633 = false, _mut96634 = false, _mut96635 = false, _mut96636 = false, _mut96637 = false, _mut96638 = false, _mut96639 = false, _mut96640 = false, _mut96641 = false, _mut96642 = false, _mut96643 = false, _mut96644 = false, _mut96645 = false, _mut96646 = false, _mut96647 = false, _mut96648 = false, _mut96649 = false, _mut96650 = false, _mut96651 = false, _mut96652 = false, _mut96653 = false, _mut96654 = false, _mut96655 = false, _mut96656 = false, _mut96657 = false, _mut96658 = false, _mut96659 = false, _mut96660 = false, _mut96661 = false, _mut96662 = false, _mut96663 = false, _mut96664 = false, _mut96665 = false, _mut96666 = false, _mut96667 = false, _mut96668 = false, _mut96669 = false, _mut96670 = false, _mut96671 = false, _mut96672 = false, _mut96673 = false, _mut96674 = false, _mut96675 = false, _mut96676 = false, _mut96677 = false, _mut96678 = false, _mut96679 = false, _mut96680 = false, _mut96681 = false, _mut96682 = false, _mut96683 = false, _mut96684 = false, _mut96685 = false, _mut96686 = false, _mut96687 = false, _mut96688 = false, _mut96689 = false, _mut96690 = false, _mut96691 = false, _mut96692 = false, _mut96693 = false, _mut96694 = false, _mut96695 = false, _mut96696 = false, _mut96697 = false, _mut96698 = false, _mut96699 = false, _mut96700 = false, _mut96701 = false, _mut96702 = false, _mut96703 = false, _mut96704 = false, _mut96705 = false, _mut96706 = false, _mut96707 = false, _mut96708 = false, _mut96709 = false, _mut96710 = false, _mut96711 = false, _mut96712 = false, _mut96713 = false, _mut96714 = false, _mut96715 = false, _mut96716 = false, _mut96717 = false, _mut96718 = false, _mut96719 = false, _mut96720 = false, _mut96721 = false, _mut96722 = false, _mut96723 = false, _mut96724 = false, _mut96725 = false, _mut96726 = false, _mut96727 = false, _mut96728 = false, _mut96729 = false, _mut96730 = false, _mut96731 = false, _mut96732 = false, _mut96733 = false, _mut96734 = false, _mut96735 = false, _mut96736 = false, _mut96737 = false, _mut96738 = false, _mut96739 = false, _mut96740 = false, _mut96741 = false, _mut96742 = false, _mut96743 = false, _mut96744 = false, _mut96745 = false, _mut96746 = false, _mut96747 = false, _mut96748 = false, _mut96749 = false, _mut96750 = false, _mut96751 = false, _mut96752 = false, _mut96753 = false, _mut96754 = false, _mut96755 = false, _mut96756 = false, _mut96757 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20131025L;

    /**
     * Value of the calculation.
     */
    private double value;

    /**
     * Stored derivative, each key representing a different independent variable.
     */
    private final Map<Integer, Double> derivatives;

    /**
     * Internal constructor.
     * @param value value of the function
     * @param derivatives derivatives map, a deep copy will be performed,
     * so the map given here will remain safe from changes in the new instance,
     * may be null to create an empty derivatives map, i.e. a constant value
     */
    private SparseGradient(final double value, final Map<Integer, Double> derivatives) {
        this.value = value;
        this.derivatives = new HashMap<Integer, Double>();
        if (derivatives != null) {
            this.derivatives.putAll(derivatives);
        }
    }

    /**
     * Internal constructor.
     * @param value value of the function
     * @param scale scaling factor to apply to all derivatives
     * @param derivatives derivatives map, a deep copy will be performed,
     * so the map given here will remain safe from changes in the new instance,
     * may be null to create an empty derivatives map, i.e. a constant value
     */
    private SparseGradient(final double value, final double scale, final Map<Integer, Double> derivatives) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.SparseGradient_77");
        this.value = value;
        this.derivatives = new HashMap<Integer, Double>();
        if (derivatives != null) {
            for (final Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.SparseGradient_77");
                this.derivatives.put(entry.getKey(), AOR_multiply(scale, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.SparseGradient_77", _mut96288, _mut96289, _mut96290, _mut96291));
            }
        }
    }

    /**
     * Factory method creating a constant.
     * @param value value of the constant
     * @return a new instance
     */
    public static SparseGradient createConstant(final double value) {
        return new SparseGradient(value, Collections.<Integer, Double>emptyMap());
    }

    /**
     * Factory method creating an independent variable.
     * @param idx index of the variable
     * @param value value of the variable
     * @return a new instance
     */
    public static SparseGradient createVariable(final int idx, final double value) {
        return new SparseGradient(value, Collections.singletonMap(idx, 1.0));
    }

    /**
     * Find the number of variables.
     * @return number of variables
     */
    public int numVars() {
        return derivatives.size();
    }

    /**
     * Get the derivative with respect to a particular index variable.
     *
     * @param index index to differentiate with.
     * @return derivative with respect to a particular index variable
     */
    public double getDerivative(final int index) {
        final Double out = derivatives.get(index);
        return (out == null) ? 0.0 : out;
    }

    /**
     * Get the value of the function.
     * @return value of the function.
     */
    public double getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public double getReal() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient add(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.add_138");
        final SparseGradient out = new SparseGradient(AOR_plus(value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.add_138", _mut96292, _mut96293, _mut96294, _mut96295), derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.add_138");
            final int id = entry.getKey();
            final Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, entry.getValue());
            } else {
                out.derivatives.put(id, AOR_plus(old, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.add_138", _mut96296, _mut96297, _mut96298, _mut96299));
            }
        }
        return out;
    }

    /**
     * Add in place.
     * <p>
     * This method is designed to be faster when used multiple times in a loop.
     * </p>
     * <p>
     * The instance is changed here, in order to not change the
     * instance the {@link #add(SparseGradient)} method should
     * be used.
     * </p>
     * @param a instance to add
     */
    public void addInPlace(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.addInPlace_165");
        value += a.value;
        for (final Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.addInPlace_165");
            final int id = entry.getKey();
            final Double old = derivatives.get(id);
            if (old == null) {
                derivatives.put(id, entry.getValue());
            } else {
                derivatives.put(id, AOR_plus(old, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.addInPlace_165", _mut96300, _mut96301, _mut96302, _mut96303));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient add(final double c) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.add_179");
        final SparseGradient out = new SparseGradient(AOR_plus(value, c, "org.apache.commons.math3.analysis.differentiation.SparseGradient.add_179", _mut96304, _mut96305, _mut96306, _mut96307), derivatives);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient subtract(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_185");
        final SparseGradient out = new SparseGradient(AOR_minus(value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_185", _mut96308, _mut96309, _mut96310, _mut96311), derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_185");
            final int id = entry.getKey();
            final Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, -entry.getValue());
            } else {
                out.derivatives.put(id, AOR_minus(old, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_185", _mut96312, _mut96313, _mut96314, _mut96315));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient subtract(double c) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_200");
        return new SparseGradient(AOR_minus(value, c, "org.apache.commons.math3.analysis.differentiation.SparseGradient.subtract_200", _mut96316, _mut96317, _mut96318, _mut96319), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient multiply(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205");
        final SparseGradient out = new SparseGradient(AOR_multiply(value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205", _mut96320, _mut96321, _mut96322, _mut96323), Collections.<Integer, Double>emptyMap());
        // Derivatives.
        for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205");
            out.derivatives.put(entry.getKey(), AOR_multiply(a.value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205", _mut96324, _mut96325, _mut96326, _mut96327));
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205");
            final int id = entry.getKey();
            final Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, AOR_multiply(value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205", _mut96336, _mut96337, _mut96338, _mut96339));
            } else {
                out.derivatives.put(id, AOR_plus(old, AOR_multiply(value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205", _mut96328, _mut96329, _mut96330, _mut96331), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_205", _mut96332, _mut96333, _mut96334, _mut96335));
            }
        }
        return out;
    }

    /**
     * Multiply in place.
     * <p>
     * This method is designed to be faster when used multiple times in a loop.
     * </p>
     * <p>
     * The instance is changed here, in order to not change the
     * instance the {@link #add(SparseGradient)} method should
     * be used.
     * </p>
     * @param a instance to multiply
     */
    public void multiplyInPlace(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237");
        // Derivatives.
        for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237");
            derivatives.put(entry.getKey(), AOR_multiply(a.value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237", _mut96340, _mut96341, _mut96342, _mut96343));
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237");
            final int id = entry.getKey();
            final Double old = derivatives.get(id);
            if (old == null) {
                derivatives.put(id, AOR_multiply(value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237", _mut96352, _mut96353, _mut96354, _mut96355));
            } else {
                derivatives.put(id, AOR_plus(old, AOR_multiply(value, entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237", _mut96344, _mut96345, _mut96346, _mut96347), "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiplyInPlace_237", _mut96348, _mut96349, _mut96350, _mut96351));
            }
        }
        value *= a.value;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient multiply(final double c) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_255");
        return new SparseGradient(AOR_multiply(value, c, "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_255", _mut96356, _mut96357, _mut96358, _mut96359), c, derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient multiply(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_260");
        return new SparseGradient(AOR_multiply(value, n, "org.apache.commons.math3.analysis.differentiation.SparseGradient.multiply_260", _mut96360, _mut96361, _mut96362, _mut96363), n, derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient divide(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265");
        final SparseGradient out = new SparseGradient(AOR_divide(value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96364, _mut96365, _mut96366, _mut96367), Collections.<Integer, Double>emptyMap());
        // Derivatives.
        for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265");
            out.derivatives.put(entry.getKey(), AOR_divide(entry.getValue(), a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96368, _mut96369, _mut96370, _mut96371));
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265");
            final int id = entry.getKey();
            final Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, AOR_multiply(AOR_divide(-out.value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96384, _mut96385, _mut96386, _mut96387), entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96388, _mut96389, _mut96390, _mut96391));
            } else {
                out.derivatives.put(id, AOR_minus(old, AOR_multiply(AOR_divide(out.value, a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96372, _mut96373, _mut96374, _mut96375), entry.getValue(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96376, _mut96377, _mut96378, _mut96379), "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_265", _mut96380, _mut96381, _mut96382, _mut96383));
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient divide(final double c) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_285");
        return new SparseGradient(AOR_divide(value, c, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_285", _mut96392, _mut96393, _mut96394, _mut96395), AOR_divide(1.0, c, "org.apache.commons.math3.analysis.differentiation.SparseGradient.divide_285", _mut96396, _mut96397, _mut96398, _mut96399), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient negate() {
        return new SparseGradient(-value, -1.0, derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public Field<SparseGradient> getField() {
        return new Field<SparseGradient>() {

            /**
             * {@inheritDoc}
             */
            public SparseGradient getZero() {
                return createConstant(0);
            }

            /**
             * {@inheritDoc}
             */
            public SparseGradient getOne() {
                return createConstant(1);
            }

            /**
             * {@inheritDoc}
             */
            public Class<? extends FieldElement<SparseGradient>> getRuntimeClass() {
                return SparseGradient.class;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient remainder(final double a) {
        return new SparseGradient(FastMath.IEEEremainder(value, a), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient remainder(final SparseGradient a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.remainder_322");
        // compute k such that lhs % rhs = lhs - k rhs
        final double rem = FastMath.IEEEremainder(value, a.value);
        final double k = FastMath.rint(AOR_divide((AOR_minus(value, rem, "org.apache.commons.math3.analysis.differentiation.SparseGradient.remainder_322", _mut96400, _mut96401, _mut96402, _mut96403)), a.value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.remainder_322", _mut96404, _mut96405, _mut96406, _mut96407));
        return subtract(a.multiply(k));
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient abs() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.abs_333");
        if (ROR_less(Double.doubleToLongBits(value), 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.abs_333", _mut96408, _mut96409, _mut96410, _mut96411, _mut96412)) {
            // we use the bits representation to also handle -0.0
            return negate();
        } else {
            return this;
        }
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient ceil() {
        return createConstant(FastMath.ceil(value));
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient floor() {
        return createConstant(FastMath.floor(value));
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient rint() {
        return createConstant(FastMath.rint(value));
    }

    /**
     * {@inheritDoc}
     */
    public long round() {
        return FastMath.round(value);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient signum() {
        return createConstant(FastMath.signum(value));
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient copySign(final SparseGradient sign) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368");
        final long m = Double.doubleToLongBits(value);
        final long s = Double.doubleToLongBits(sign.value);
        if ((_mut96435 ? (((_mut96423 ? (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96413, _mut96414, _mut96415, _mut96416, _mut96417) || ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96418, _mut96419, _mut96420, _mut96421, _mut96422)) : (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96413, _mut96414, _mut96415, _mut96416, _mut96417) && ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96418, _mut96419, _mut96420, _mut96421, _mut96422)))) && ((_mut96434 ? (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96424, _mut96425, _mut96426, _mut96427, _mut96428) || ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96429, _mut96430, _mut96431, _mut96432, _mut96433)) : (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96424, _mut96425, _mut96426, _mut96427, _mut96428) && ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96429, _mut96430, _mut96431, _mut96432, _mut96433))))) : (((_mut96423 ? (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96413, _mut96414, _mut96415, _mut96416, _mut96417) || ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96418, _mut96419, _mut96420, _mut96421, _mut96422)) : (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96413, _mut96414, _mut96415, _mut96416, _mut96417) && ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96418, _mut96419, _mut96420, _mut96421, _mut96422)))) || ((_mut96434 ? (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96424, _mut96425, _mut96426, _mut96427, _mut96428) || ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96429, _mut96430, _mut96431, _mut96432, _mut96433)) : (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96424, _mut96425, _mut96426, _mut96427, _mut96428) && ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_368", _mut96429, _mut96430, _mut96431, _mut96432, _mut96433))))))) {
            // Sign is currently OK
            return this;
        }
        // flip sign
        return negate();
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient copySign(final double sign) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378");
        final long m = Double.doubleToLongBits(value);
        final long s = Double.doubleToLongBits(sign);
        if ((_mut96458 ? (((_mut96446 ? (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96436, _mut96437, _mut96438, _mut96439, _mut96440) || ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96441, _mut96442, _mut96443, _mut96444, _mut96445)) : (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96436, _mut96437, _mut96438, _mut96439, _mut96440) && ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96441, _mut96442, _mut96443, _mut96444, _mut96445)))) && ((_mut96457 ? (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96447, _mut96448, _mut96449, _mut96450, _mut96451) || ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96452, _mut96453, _mut96454, _mut96455, _mut96456)) : (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96447, _mut96448, _mut96449, _mut96450, _mut96451) && ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96452, _mut96453, _mut96454, _mut96455, _mut96456))))) : (((_mut96446 ? (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96436, _mut96437, _mut96438, _mut96439, _mut96440) || ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96441, _mut96442, _mut96443, _mut96444, _mut96445)) : (ROR_greater_equals(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96436, _mut96437, _mut96438, _mut96439, _mut96440) && ROR_greater_equals(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96441, _mut96442, _mut96443, _mut96444, _mut96445)))) || ((_mut96457 ? (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96447, _mut96448, _mut96449, _mut96450, _mut96451) || ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96452, _mut96453, _mut96454, _mut96455, _mut96456)) : (ROR_less(m, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96447, _mut96448, _mut96449, _mut96450, _mut96451) && ROR_less(s, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.copySign_378", _mut96452, _mut96453, _mut96454, _mut96455, _mut96456))))))) {
            // Sign is currently OK
            return this;
        }
        // flip sign
        return negate();
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient scalb(final int n) {
        final SparseGradient out = new SparseGradient(FastMath.scalb(value, n), Collections.<Integer, Double>emptyMap());
        for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.scalb_388");
            out.derivatives.put(entry.getKey(), FastMath.scalb(entry.getValue(), n));
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient hypot(final SparseGradient y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397");
        if ((_mut96459 ? (Double.isInfinite(value) && Double.isInfinite(y.value)) : (Double.isInfinite(value) || Double.isInfinite(y.value)))) {
            return createConstant(Double.POSITIVE_INFINITY);
        } else if ((_mut96460 ? (Double.isNaN(value) && Double.isNaN(y.value)) : (Double.isNaN(value) || Double.isNaN(y.value)))) {
            return createConstant(Double.NaN);
        } else {
            final int expX = FastMath.getExponent(value);
            final int expY = FastMath.getExponent(y.value);
            if (ROR_greater(expX, AOR_plus(expY, 27, "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96461, _mut96462, _mut96463, _mut96464), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96465, _mut96466, _mut96467, _mut96468, _mut96469)) {
                // y is negligible with respect to x
                return abs();
            } else if (ROR_greater(expY, AOR_plus(expX, 27, "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96470, _mut96471, _mut96472, _mut96473), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96474, _mut96475, _mut96476, _mut96477, _mut96478)) {
                // x is negligible with respect to y
                return y.abs();
            } else {
                // find an intermediate scale to avoid both overflow and underflow
                final int middleExp = AOR_divide((AOR_plus(expX, expY, "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96479, _mut96480, _mut96481, _mut96482)), 2, "org.apache.commons.math3.analysis.differentiation.SparseGradient.hypot_397", _mut96483, _mut96484, _mut96485, _mut96486);
                // scale parameters without losing precision
                final SparseGradient scaledX = scalb(-middleExp);
                final SparseGradient scaledY = y.scalb(-middleExp);
                // compute scaled hypotenuse
                final SparseGradient scaledH = scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).sqrt();
                // remove scaling
                return scaledH.scalb(middleExp);
            }
        }
    }

    /**
     * Returns the hypotenuse of a triangle with sides {@code x} and {@code y}
     * - sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * avoiding intermediate overflow or underflow.
     *
     * <ul>
     * <li> If either argument is infinite, then the result is positive infinity.</li>
     * <li> else, if either argument is NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x a value
     * @param y a value
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     */
    public static SparseGradient hypot(final SparseGradient x, final SparseGradient y) {
        return x.hypot(y);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient reciprocal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.reciprocal_452");
        return new SparseGradient(AOR_divide(1.0, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.reciprocal_452", _mut96487, _mut96488, _mut96489, _mut96490), AOR_divide(-1.0, (AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.reciprocal_452", _mut96491, _mut96492, _mut96493, _mut96494)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.reciprocal_452", _mut96495, _mut96496, _mut96497, _mut96498), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient sqrt() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.sqrt_457");
        final double sqrt = FastMath.sqrt(value);
        return new SparseGradient(sqrt, AOR_divide(0.5, sqrt, "org.apache.commons.math3.analysis.differentiation.SparseGradient.sqrt_457", _mut96499, _mut96500, _mut96501, _mut96502), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient cbrt() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.cbrt_463");
        final double cbrt = FastMath.cbrt(value);
        return new SparseGradient(cbrt, AOR_divide(1.0, (AOR_multiply(AOR_multiply(3, cbrt, "org.apache.commons.math3.analysis.differentiation.SparseGradient.cbrt_463", _mut96503, _mut96504, _mut96505, _mut96506), cbrt, "org.apache.commons.math3.analysis.differentiation.SparseGradient.cbrt_463", _mut96507, _mut96508, _mut96509, _mut96510)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.cbrt_463", _mut96511, _mut96512, _mut96513, _mut96514), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient rootN(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469");
        if (ROR_equals(n, 2, "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96515, _mut96516, _mut96517, _mut96518, _mut96519)) {
            return sqrt();
        } else if (ROR_equals(n, 3, "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96520, _mut96521, _mut96522, _mut96523, _mut96524)) {
            return cbrt();
        } else {
            final double root = FastMath.pow(value, AOR_divide(1.0, n, "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96525, _mut96526, _mut96527, _mut96528));
            return new SparseGradient(root, AOR_divide(1.0, (AOR_multiply(n, FastMath.pow(root, AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96529, _mut96530, _mut96531, _mut96532)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96533, _mut96534, _mut96535, _mut96536)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.rootN_469", _mut96537, _mut96538, _mut96539, _mut96540), derivatives);
        }
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient pow(final double p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_481");
        return new SparseGradient(FastMath.pow(value, p), AOR_multiply(p, FastMath.pow(value, AOR_minus(p, 1, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_481", _mut96541, _mut96542, _mut96543, _mut96544)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_481", _mut96545, _mut96546, _mut96547, _mut96548), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient pow(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_486");
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_486", _mut96549, _mut96550, _mut96551, _mut96552, _mut96553)) {
            return getField().getOne();
        } else {
            final double valueNm1 = FastMath.pow(value, AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_486", _mut96554, _mut96555, _mut96556, _mut96557));
            return new SparseGradient(AOR_multiply(value, valueNm1, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_486", _mut96558, _mut96559, _mut96560, _mut96561), AOR_multiply(n, valueNm1, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_486", _mut96562, _mut96563, _mut96564, _mut96565), derivatives);
        }
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient pow(final SparseGradient e) {
        return log().multiply(e).exp();
    }

    /**
     * Compute a<sup>x</sup> where a is a double and x a {@link SparseGradient}
     * @param a number to exponentiate
     * @param x power to apply
     * @return a<sup>x</sup>
     */
    public static SparseGradient pow(final double a, final SparseGradient x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_505");
        if (ROR_equals(a, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_505", _mut96566, _mut96567, _mut96568, _mut96569, _mut96570)) {
            if (ROR_equals(x.value, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_505", _mut96575, _mut96576, _mut96577, _mut96578, _mut96579)) {
                return x.compose(1.0, Double.NEGATIVE_INFINITY);
            } else if (ROR_less(x.value, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_505", _mut96580, _mut96581, _mut96582, _mut96583, _mut96584)) {
                return x.compose(Double.NaN, Double.NaN);
            } else {
                return x.getField().getZero();
            }
        } else {
            final double ax = FastMath.pow(a, x.value);
            return new SparseGradient(ax, AOR_multiply(ax, FastMath.log(a), "org.apache.commons.math3.analysis.differentiation.SparseGradient.pow_505", _mut96571, _mut96572, _mut96573, _mut96574), x.derivatives);
        }
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient exp() {
        final double e = FastMath.exp(value);
        return new SparseGradient(e, e, derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient expm1() {
        return new SparseGradient(FastMath.expm1(value), FastMath.exp(value), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient log() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.log_532");
        return new SparseGradient(FastMath.log(value), AOR_divide(1.0, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.log_532", _mut96585, _mut96586, _mut96587, _mut96588), derivatives);
    }

    /**
     * Base 10 logarithm.
     * @return base 10 logarithm of the instance
     */
    public SparseGradient log10() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.log10_539");
        return new SparseGradient(FastMath.log10(value), AOR_divide(1.0, (AOR_multiply(FastMath.log(10.0), value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.log10_539", _mut96589, _mut96590, _mut96591, _mut96592)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.log10_539", _mut96593, _mut96594, _mut96595, _mut96596), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient log1p() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.log1p_544");
        return new SparseGradient(FastMath.log1p(value), AOR_divide(1.0, (AOR_plus(1.0, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.log1p_544", _mut96597, _mut96598, _mut96599, _mut96600)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.log1p_544", _mut96601, _mut96602, _mut96603, _mut96604), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient cos() {
        return new SparseGradient(FastMath.cos(value), -FastMath.sin(value), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient sin() {
        return new SparseGradient(FastMath.sin(value), FastMath.cos(value), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient tan() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.tan_559");
        final double t = FastMath.tan(value);
        return new SparseGradient(t, AOR_plus(1, AOR_multiply(t, t, "org.apache.commons.math3.analysis.differentiation.SparseGradient.tan_559", _mut96605, _mut96606, _mut96607, _mut96608), "org.apache.commons.math3.analysis.differentiation.SparseGradient.tan_559", _mut96609, _mut96610, _mut96611, _mut96612), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient acos() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.acos_565");
        return new SparseGradient(FastMath.acos(value), AOR_divide(-1.0, FastMath.sqrt(AOR_minus(1, AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.acos_565", _mut96613, _mut96614, _mut96615, _mut96616), "org.apache.commons.math3.analysis.differentiation.SparseGradient.acos_565", _mut96617, _mut96618, _mut96619, _mut96620)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.acos_565", _mut96621, _mut96622, _mut96623, _mut96624), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient asin() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.asin_570");
        return new SparseGradient(FastMath.asin(value), AOR_divide(1.0, FastMath.sqrt(AOR_minus(1, AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.asin_570", _mut96625, _mut96626, _mut96627, _mut96628), "org.apache.commons.math3.analysis.differentiation.SparseGradient.asin_570", _mut96629, _mut96630, _mut96631, _mut96632)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.asin_570", _mut96633, _mut96634, _mut96635, _mut96636), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient atan() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.atan_575");
        return new SparseGradient(FastMath.atan(value), AOR_divide(1.0, (AOR_plus(1, AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.atan_575", _mut96637, _mut96638, _mut96639, _mut96640), "org.apache.commons.math3.analysis.differentiation.SparseGradient.atan_575", _mut96641, _mut96642, _mut96643, _mut96644)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.atan_575", _mut96645, _mut96646, _mut96647, _mut96648), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient atan2(final SparseGradient x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.atan2_580");
        // compute r = sqrt(x^2+y^2)
        final SparseGradient r = multiply(this).add(x.multiply(x)).sqrt();
        final SparseGradient a;
        if (ROR_greater_equals(x.value, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.atan2_580", _mut96649, _mut96650, _mut96651, _mut96652, _mut96653)) {
            // compute atan2(y, x) = 2 atan(y / (r + x))
            a = divide(r.add(x)).atan().multiply(2);
        } else {
            // compute atan2(y, x) = +/- pi - 2 atan(y / (r - x))
            final SparseGradient tmp = divide(r.subtract(x)).atan().multiply(-2);
            a = tmp.add(ROR_less_equals(tmp.value, 0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.atan2_580", _mut96654, _mut96655, _mut96656, _mut96657, _mut96658) ? -FastMath.PI : FastMath.PI);
        }
        // fix value to take special cases (+0/+0, +0/-0, -0/+0, -0/-0, +/-infinity) correctly
        a.value = FastMath.atan2(value, x.value);
        return a;
    }

    /**
     * Two arguments arc tangent operation.
     * @param y first argument of the arc tangent
     * @param x second argument of the arc tangent
     * @return atan2(y, x)
     */
    public static SparseGradient atan2(final SparseGradient y, final SparseGradient x) {
        return y.atan2(x);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient cosh() {
        return new SparseGradient(FastMath.cosh(value), FastMath.sinh(value), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient sinh() {
        return new SparseGradient(FastMath.sinh(value), FastMath.cosh(value), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient tanh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.tanh_626");
        final double t = FastMath.tanh(value);
        return new SparseGradient(t, AOR_minus(1, AOR_multiply(t, t, "org.apache.commons.math3.analysis.differentiation.SparseGradient.tanh_626", _mut96659, _mut96660, _mut96661, _mut96662), "org.apache.commons.math3.analysis.differentiation.SparseGradient.tanh_626", _mut96663, _mut96664, _mut96665, _mut96666), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient acosh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.acosh_632");
        return new SparseGradient(FastMath.acosh(value), AOR_divide(1.0, FastMath.sqrt(AOR_minus(AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.acosh_632", _mut96667, _mut96668, _mut96669, _mut96670), 1.0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.acosh_632", _mut96671, _mut96672, _mut96673, _mut96674)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.acosh_632", _mut96675, _mut96676, _mut96677, _mut96678), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient asinh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.asinh_637");
        return new SparseGradient(FastMath.asinh(value), AOR_divide(1.0, FastMath.sqrt(AOR_plus(AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.asinh_637", _mut96679, _mut96680, _mut96681, _mut96682), 1.0, "org.apache.commons.math3.analysis.differentiation.SparseGradient.asinh_637", _mut96683, _mut96684, _mut96685, _mut96686)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.asinh_637", _mut96687, _mut96688, _mut96689, _mut96690), derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient atanh() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.atanh_642");
        return new SparseGradient(FastMath.atanh(value), AOR_divide(1.0, (AOR_minus(1.0, AOR_multiply(value, value, "org.apache.commons.math3.analysis.differentiation.SparseGradient.atanh_642", _mut96691, _mut96692, _mut96693, _mut96694), "org.apache.commons.math3.analysis.differentiation.SparseGradient.atanh_642", _mut96695, _mut96696, _mut96697, _mut96698)), "org.apache.commons.math3.analysis.differentiation.SparseGradient.atanh_642", _mut96699, _mut96700, _mut96701, _mut96702), derivatives);
    }

    /**
     * Convert radians to degrees, with error of less than 0.5 ULP
     *  @return instance converted into degrees
     */
    public SparseGradient toDegrees() {
        return new SparseGradient(FastMath.toDegrees(value), FastMath.toDegrees(1.0), derivatives);
    }

    /**
     * Convert degrees to radians, with error of less than 0.5 ULP
     *  @return instance converted into radians
     */
    public SparseGradient toRadians() {
        return new SparseGradient(FastMath.toRadians(value), FastMath.toRadians(1.0), derivatives);
    }

    /**
     * Evaluate Taylor expansion of a sparse gradient.
     * @param delta parameters offsets (&Delta;x, &Delta;y, ...)
     * @return value of the Taylor expansion at x + &Delta;x, y + &Delta;y, ...
     */
    public double taylor(final double... delta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.taylor_664");
        double y = value;
        for (int i = 0; ROR_less(i, delta.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.taylor_664", _mut96707, _mut96708, _mut96709, _mut96710, _mut96711); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.taylor_664");
            y += AOR_multiply(delta[i], getDerivative(i), "org.apache.commons.math3.analysis.differentiation.SparseGradient.taylor_664", _mut96703, _mut96704, _mut96705, _mut96706);
        }
        return y;
    }

    /**
     * Compute composition of the instance by a univariate function.
     * @param f0 value of the function at (i.e. f({@link #getValue()}))
     * @param f1 first derivative of the function at
     * the current point (i.e. f'({@link #getValue()}))
     * @return f(this)
     */
    public SparseGradient compose(final double f0, final double f1) {
        return new SparseGradient(f0, f1, derivatives);
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final SparseGradient[] a, final SparseGradient[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683");
        // compute a simple value, with all partial derivatives
        SparseGradient out = a[0].getField().getZero();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683", _mut96712, _mut96713, _mut96714, _mut96715, _mut96716); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683");
            out = out.add(a[i].multiply(b[i]));
        }
        // recompute an accurate value, taking care of cancellations
        final double[] aDouble = new double[a.length];
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683", _mut96717, _mut96718, _mut96719, _mut96720, _mut96721); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683");
            aDouble[i] = a[i].getValue();
        }
        final double[] bDouble = new double[b.length];
        for (int i = 0; ROR_less(i, b.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683", _mut96722, _mut96723, _mut96724, _mut96725, _mut96726); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_683");
            bDouble[i] = b[i].getValue();
        }
        out.value = MathArrays.linearCombination(aDouble, bDouble);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final double[] a, final SparseGradient[] b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_709");
        // compute a simple value, with all partial derivatives
        SparseGradient out = b[0].getField().getZero();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_709", _mut96727, _mut96728, _mut96729, _mut96730, _mut96731); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_709");
            out = out.add(b[i].multiply(a[i]));
        }
        // recompute an accurate value, taking care of cancellations
        final double[] bDouble = new double[b.length];
        for (int i = 0; ROR_less(i, b.length, "org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_709", _mut96732, _mut96733, _mut96734, _mut96735, _mut96736); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.linearCombination_709");
            bDouble[i] = b[i].getValue();
        }
        out.value = MathArrays.linearCombination(a, bDouble);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final SparseGradient a1, final SparseGradient b1, final SparseGradient a2, final SparseGradient b2) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final double a1, final SparseGradient b1, final double a2, final SparseGradient b2) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final SparseGradient a1, final SparseGradient b1, final SparseGradient a2, final SparseGradient b2, final SparseGradient a3, final SparseGradient b3) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final double a1, final SparseGradient b1, final double a2, final SparseGradient b2, final double a3, final SparseGradient b3) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final SparseGradient a1, final SparseGradient b1, final SparseGradient a2, final SparseGradient b2, final SparseGradient a3, final SparseGradient b3, final SparseGradient a4, final SparseGradient b4) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value);
        return out;
    }

    /**
     * {@inheritDoc}
     */
    public SparseGradient linearCombination(final double a1, final SparseGradient b1, final double a2, final SparseGradient b2, final double a3, final SparseGradient b3, final double a4, final SparseGradient b4) {
        // compute a simple value, with all partial derivatives
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value);
        return out;
    }

    /**
     * Test for the equality of two sparse gradients.
     * <p>
     * Sparse gradients are considered equal if they have the same value
     * and the same derivatives.
     * </p>
     * @param other Object to test for equality to this
     * @return true if two sparse gradients are equal
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.equals_837");
        if (this == other) {
            return true;
        }
        if (other instanceof SparseGradient) {
            final SparseGradient rhs = (SparseGradient) other;
            if (!Precision.equals(value, rhs.value, 1)) {
                return false;
            }
            if (ROR_not_equals(derivatives.size(), rhs.derivatives.size(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.equals_837", _mut96737, _mut96738, _mut96739, _mut96740, _mut96741)) {
                return false;
            }
            for (final Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.equals_837");
                if (!rhs.derivatives.containsKey(entry.getKey())) {
                    return false;
                }
                if (!Precision.equals(entry.getValue(), rhs.derivatives.get(entry.getKey()), 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Get a hashCode for the derivative structure.
     * @return a hash code value for this object
     * @since 3.2
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.SparseGradient.hashCode_872");
        return AOR_plus(AOR_plus(743, AOR_multiply(809, MathUtils.hash(value), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hashCode_872", _mut96742, _mut96743, _mut96744, _mut96745), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hashCode_872", _mut96746, _mut96747, _mut96748, _mut96749), AOR_multiply(167, derivatives.hashCode(), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hashCode_872", _mut96750, _mut96751, _mut96752, _mut96753), "org.apache.commons.math3.analysis.differentiation.SparseGradient.hashCode_872", _mut96754, _mut96755, _mut96756, _mut96757);
    }
}
