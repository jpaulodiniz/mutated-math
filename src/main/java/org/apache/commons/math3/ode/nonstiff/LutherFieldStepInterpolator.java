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
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class LutherFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {

    @Conditional
    public static boolean _mut12613 = false, _mut12614 = false, _mut12615 = false, _mut12616 = false, _mut12617 = false, _mut12618 = false, _mut12619 = false, _mut12620 = false, _mut12621 = false, _mut12622 = false, _mut12623 = false, _mut12624 = false, _mut12625 = false, _mut12626 = false, _mut12627 = false, _mut12628 = false, _mut12629 = false, _mut12630 = false, _mut12631 = false, _mut12632 = false, _mut12633 = false, _mut12634 = false, _mut12635 = false, _mut12636 = false, _mut12637 = false, _mut12638 = false, _mut12639 = false, _mut12640 = false, _mut12641 = false, _mut12642 = false, _mut12643 = false, _mut12644 = false, _mut12645 = false, _mut12646 = false, _mut12647 = false, _mut12648 = false, _mut12649 = false, _mut12650 = false, _mut12651 = false, _mut12652 = false, _mut12653 = false, _mut12654 = false, _mut12655 = false, _mut12656 = false, _mut12657 = false, _mut12658 = false, _mut12659 = false, _mut12660 = false, _mut12661 = false, _mut12662 = false, _mut12663 = false, _mut12664 = false, _mut12665 = false, _mut12666 = false, _mut12667 = false, _mut12668 = false, _mut12669 = false, _mut12670 = false, _mut12671 = false, _mut12672 = false, _mut12673 = false, _mut12674 = false, _mut12675 = false, _mut12676 = false, _mut12677 = false, _mut12678 = false, _mut12679 = false, _mut12680 = false, _mut12681 = false, _mut12682 = false, _mut12683 = false, _mut12684 = false, _mut12685 = false, _mut12686 = false, _mut12687 = false, _mut12688 = false, _mut12689 = false, _mut12690 = false, _mut12691 = false, _mut12692 = false, _mut12693 = false, _mut12694 = false, _mut12695 = false, _mut12696 = false, _mut12697 = false, _mut12698 = false, _mut12699 = false, _mut12700 = false, _mut12701 = false, _mut12702 = false, _mut12703 = false, _mut12704 = false, _mut12705 = false, _mut12706 = false, _mut12707 = false, _mut12708 = false, _mut12709 = false, _mut12710 = false, _mut12711 = false, _mut12712 = false, _mut12713 = false, _mut12714 = false, _mut12715 = false, _mut12716 = false, _mut12717 = false, _mut12718 = false, _mut12719 = false, _mut12720 = false, _mut12721 = false, _mut12722 = false, _mut12723 = false, _mut12724 = false, _mut12725 = false, _mut12726 = false, _mut12727 = false, _mut12728 = false, _mut12729 = false, _mut12730 = false, _mut12731 = false, _mut12732 = false, _mut12733 = false, _mut12734 = false, _mut12735 = false, _mut12736 = false, _mut12737 = false, _mut12738 = false, _mut12739 = false, _mut12740 = false, _mut12741 = false, _mut12742 = false, _mut12743 = false, _mut12744 = false, _mut12745 = false, _mut12746 = false, _mut12747 = false, _mut12748 = false, _mut12749 = false, _mut12750 = false, _mut12751 = false, _mut12752 = false, _mut12753 = false, _mut12754 = false, _mut12755 = false, _mut12756 = false, _mut12757 = false, _mut12758 = false, _mut12759 = false, _mut12760 = false, _mut12761 = false, _mut12762 = false, _mut12763 = false, _mut12764 = false, _mut12765 = false, _mut12766 = false, _mut12767 = false, _mut12768 = false, _mut12769 = false, _mut12770 = false, _mut12771 = false, _mut12772 = false, _mut12773 = false, _mut12774 = false, _mut12775 = false, _mut12776 = false, _mut12777 = false, _mut12778 = false, _mut12779 = false, _mut12780 = false, _mut12781 = false, _mut12782 = false, _mut12783 = false, _mut12784 = false, _mut12785 = false, _mut12786 = false, _mut12787 = false, _mut12788 = false, _mut12789 = false, _mut12790 = false;

    /**
     * -49 - 49 q.
     */
    private final T c5a;

    /**
     * 392 + 287 q.
     */
    private final T c5b;

    /**
     * -637 - 357 q.
     */
    private final T c5c;

    /**
     * 833 + 343 q.
     */
    private final T c5d;

    /**
     * -49 + 49 q.
     */
    private final T c6a;

    /**
     * -392 - 287 q.
     */
    private final T c6b;

    /**
     * -637 + 357 q.
     */
    private final T c6c;

    /**
     * 833 - 343 q.
     */
    private final T c6d;

    /**
     * 49 + 49 q.
     */
    private final T d5a;

    /**
     * -1372 - 847 q.
     */
    private final T d5b;

    /**
     * 2254 + 1029 q
     */
    private final T d5c;

    /**
     * 49 - 49 q.
     */
    private final T d6a;

    /**
     * -1372 + 847 q.
     */
    private final T d6b;

    /**
     * 2254 - 1029 q
     */
    private final T d6c;

    /**
     * Simple constructor.
     * @param field field to which the time and state vector elements belong
     * @param forward integration direction indicator
     * @param yDotK slopes at the intermediate points
     * @param globalPreviousState start of the global step
     * @param globalCurrentState end of the global step
     * @param softPreviousState start of the restricted step
     * @param softCurrentState end of the restricted step
     * @param mapper equations mapper for the all equations
     */
    LutherFieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        final T q = field.getZero().add(21).sqrt();
        c5a = q.multiply(-49).add(-49);
        c5b = q.multiply(287).add(392);
        c5c = q.multiply(-357).add(-637);
        c5d = q.multiply(343).add(833);
        c6a = q.multiply(49).add(-49);
        c6b = q.multiply(-287).add(392);
        c6c = q.multiply(357).add(-637);
        c6d = q.multiply(-343).add(833);
        d5a = q.multiply(49).add(49);
        d5b = q.multiply(-847).add(-1372);
        d5c = q.multiply(1029).add(2254);
        d6a = q.multiply(-49).add(49);
        d6b = q.multiply(847).add(-1372);
        d6c = q.multiply(-1029).add(2254);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LutherFieldStepInterpolator<T> create(final Field<T> newField, final boolean newForward, final T[][] newYDotK, final FieldODEStateAndDerivative<T> newGlobalPreviousState, final FieldODEStateAndDerivative<T> newGlobalCurrentState, final FieldODEStateAndDerivative<T> newSoftPreviousState, final FieldODEStateAndDerivative<T> newSoftCurrentState, final FieldEquationsMapper<T> newMapper) {
        return new LutherFieldStepInterpolator<T>(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(final FieldEquationsMapper<T> mapper, final T time, final T theta, final T thetaH, final T oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135");
        final T coeffDot1 = theta.multiply(theta.multiply(theta.multiply(theta.multiply(21).add(-47)).add(36)).add(AOR_divide(-54, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12613, _mut12614, _mut12615, _mut12616))).add(1);
        final T coeffDot2 = time.getField().getZero();
        final T coeffDot3 = theta.multiply(theta.multiply(theta.multiply(theta.multiply(112).add(AOR_divide(-608, 3.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12625, _mut12626, _mut12627, _mut12628))).add(AOR_divide(320, 3.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12621, _mut12622, _mut12623, _mut12624))).add(AOR_divide(-208, 15.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12617, _mut12618, _mut12619, _mut12620)));
        final T coeffDot4 = theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-567, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12641, _mut12642, _mut12643, _mut12644)).add(AOR_divide(972, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12637, _mut12638, _mut12639, _mut12640))).add(AOR_divide(-486, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12633, _mut12634, _mut12635, _mut12636))).add(AOR_divide(324, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12629, _mut12630, _mut12631, _mut12632)));
        final T coeffDot5 = theta.multiply(theta.multiply(theta.multiply(theta.multiply(c5a.divide(5)).add(c5b.divide(15))).add(c5c.divide(30))).add(c5d.divide(150)));
        final T coeffDot6 = theta.multiply(theta.multiply(theta.multiply(theta.multiply(c6a.divide(5)).add(c6b.divide(15))).add(c6c.divide(30))).add(c6d.divide(150)));
        final T coeffDot7 = theta.multiply(theta.multiply(theta.multiply(3.0).add(-3)).add(AOR_divide(3, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12645, _mut12646, _mut12647, _mut12648)));
        final T[] interpolatedState;
        final T[] interpolatedDerivatives;
        if ((_mut12654 ? (getGlobalPreviousState() != null || ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12649, _mut12650, _mut12651, _mut12652, _mut12653)) : (getGlobalPreviousState() != null && ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12649, _mut12650, _mut12651, _mut12652, _mut12653)))) {
            final T s = thetaH;
            final T coeff1 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(21, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12747, _mut12748, _mut12749, _mut12750)).add(AOR_divide(-47, 4.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12743, _mut12744, _mut12745, _mut12746))).add(12)).add(AOR_divide(-27, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12739, _mut12740, _mut12741, _mut12742))).add(1));
            final T coeff2 = time.getField().getZero();
            final T coeff3 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(112, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12763, _mut12764, _mut12765, _mut12766)).add(AOR_divide(-152, 3.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12759, _mut12760, _mut12761, _mut12762))).add(AOR_divide(320, 9.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12755, _mut12756, _mut12757, _mut12758))).add(AOR_divide(-104, 15.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12751, _mut12752, _mut12753, _mut12754))));
            final T coeff4 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-567, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12779, _mut12780, _mut12781, _mut12782)).add(AOR_divide(243, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12775, _mut12776, _mut12777, _mut12778))).add(AOR_divide(-162, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12771, _mut12772, _mut12773, _mut12774))).add(AOR_divide(162, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12767, _mut12768, _mut12769, _mut12770))));
            final T coeff5 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(c5a.divide(25)).add(c5b.divide(60))).add(c5c.divide(90))).add(c5d.divide(300))));
            final T coeff6 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(c6a.divide(25)).add(c6b.divide(60))).add(c6c.divide(90))).add(c6d.divide(300))));
            final T coeff7 = s.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(3, 4.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12787, _mut12788, _mut12789, _mut12790)).add(-1)).add(AOR_divide(3, 10.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12783, _mut12784, _mut12785, _mut12786))));
            interpolatedState = previousStateLinearCombination(coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7);
        } else {
            final T s = oneMinusThetaH;
            final T coeff1 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-21, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12671, _mut12672, _mut12673, _mut12674)).add(AOR_divide(151, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12667, _mut12668, _mut12669, _mut12670))).add(AOR_divide(-89, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12663, _mut12664, _mut12665, _mut12666))).add(AOR_divide(19, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12659, _mut12660, _mut12661, _mut12662))).add(AOR_divide(-1, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12655, _mut12656, _mut12657, _mut12658)));
            final T coeff2 = time.getField().getZero();
            final T coeff3 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-112, 5.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12691, _mut12692, _mut12693, _mut12694)).add(AOR_divide(424, 15.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12687, _mut12688, _mut12689, _mut12690))).add(AOR_divide(-328, 45.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12683, _mut12684, _mut12685, _mut12686))).add(AOR_divide(-16, 45.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12679, _mut12680, _mut12681, _mut12682))).add(AOR_divide(-16, 45.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12675, _mut12676, _mut12677, _mut12678)));
            final T coeff4 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(567, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12703, _mut12704, _mut12705, _mut12706)).add(AOR_divide(-648, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12699, _mut12700, _mut12701, _mut12702))).add(AOR_divide(162, 25.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12695, _mut12696, _mut12697, _mut12698)))));
            final T coeff5 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(d5a.divide(25)).add(d5b.divide(300))).add(d5c.divide(900))).add(AOR_divide(-49, 180.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12711, _mut12712, _mut12713, _mut12714))).add(AOR_divide(-49, 180.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12707, _mut12708, _mut12709, _mut12710)));
            final T coeff6 = s.multiply(theta.multiply(theta.multiply(theta.multiply(theta.multiply(d6a.divide(25)).add(d6b.divide(300))).add(d6c.divide(900))).add(AOR_divide(-49, 180.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12719, _mut12720, _mut12721, _mut12722))).add(AOR_divide(-49, 180.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12715, _mut12716, _mut12717, _mut12718)));
            final T coeff7 = s.multiply(theta.multiply(theta.multiply(theta.multiply(AOR_divide(-3, 4.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12735, _mut12736, _mut12737, _mut12738)).add(AOR_divide(1, 4.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12731, _mut12732, _mut12733, _mut12734))).add(AOR_divide(-1, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12727, _mut12728, _mut12729, _mut12730))).add(AOR_divide(-1, 20.0, "org.apache.commons.math3.ode.nonstiff.LutherFieldStepInterpolator.computeInterpolatedStateAndDerivatives_135", _mut12723, _mut12724, _mut12725, _mut12726)));
            interpolatedState = currentStateLinearCombination(coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7);
        }
        return new FieldODEStateAndDerivative<T>(time, interpolatedState, interpolatedDerivatives);
    }
}
