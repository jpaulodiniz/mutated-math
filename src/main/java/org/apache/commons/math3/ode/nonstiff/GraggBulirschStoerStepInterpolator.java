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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {

    @Conditional
    public static boolean _mut16607 = false, _mut16608 = false, _mut16609 = false, _mut16610 = false, _mut16611 = false, _mut16612 = false, _mut16613 = false, _mut16614 = false, _mut16615 = false, _mut16616 = false, _mut16617 = false, _mut16618 = false, _mut16619 = false, _mut16620 = false, _mut16621 = false, _mut16622 = false, _mut16623 = false, _mut16624 = false, _mut16625 = false, _mut16626 = false, _mut16627 = false, _mut16628 = false, _mut16629 = false, _mut16630 = false, _mut16631 = false, _mut16632 = false, _mut16633 = false, _mut16634 = false, _mut16635 = false, _mut16636 = false, _mut16637 = false, _mut16638 = false, _mut16639 = false, _mut16640 = false, _mut16641 = false, _mut16642 = false, _mut16643 = false, _mut16644 = false, _mut16645 = false, _mut16646 = false, _mut16647 = false, _mut16648 = false, _mut16649 = false, _mut16650 = false, _mut16651 = false, _mut16652 = false, _mut16653 = false, _mut16654 = false, _mut16655 = false, _mut16656 = false, _mut16657 = false, _mut16658 = false, _mut16659 = false, _mut16660 = false, _mut16661 = false, _mut16662 = false, _mut16663 = false, _mut16664 = false, _mut16665 = false, _mut16666 = false, _mut16667 = false, _mut16668 = false, _mut16669 = false, _mut16670 = false, _mut16671 = false, _mut16672 = false, _mut16673 = false, _mut16674 = false, _mut16675 = false, _mut16676 = false, _mut16677 = false, _mut16678 = false, _mut16679 = false, _mut16680 = false, _mut16681 = false, _mut16682 = false, _mut16683 = false, _mut16684 = false, _mut16685 = false, _mut16686 = false, _mut16687 = false, _mut16688 = false, _mut16689 = false, _mut16690 = false, _mut16691 = false, _mut16692 = false, _mut16693 = false, _mut16694 = false, _mut16695 = false, _mut16696 = false, _mut16697 = false, _mut16698 = false, _mut16699 = false, _mut16700 = false, _mut16701 = false, _mut16702 = false, _mut16703 = false, _mut16704 = false, _mut16705 = false, _mut16706 = false, _mut16707 = false, _mut16708 = false, _mut16709 = false, _mut16710 = false, _mut16711 = false, _mut16712 = false, _mut16713 = false, _mut16714 = false, _mut16715 = false, _mut16716 = false, _mut16717 = false, _mut16718 = false, _mut16719 = false, _mut16720 = false, _mut16721 = false, _mut16722 = false, _mut16723 = false, _mut16724 = false, _mut16725 = false, _mut16726 = false, _mut16727 = false, _mut16728 = false, _mut16729 = false, _mut16730 = false, _mut16731 = false, _mut16732 = false, _mut16733 = false, _mut16734 = false, _mut16735 = false, _mut16736 = false, _mut16737 = false, _mut16738 = false, _mut16739 = false, _mut16740 = false, _mut16741 = false, _mut16742 = false, _mut16743 = false, _mut16744 = false, _mut16745 = false, _mut16746 = false, _mut16747 = false, _mut16748 = false, _mut16749 = false, _mut16750 = false, _mut16751 = false, _mut16752 = false, _mut16753 = false, _mut16754 = false, _mut16755 = false, _mut16756 = false, _mut16757 = false, _mut16758 = false, _mut16759 = false, _mut16760 = false, _mut16761 = false, _mut16762 = false, _mut16763 = false, _mut16764 = false, _mut16765 = false, _mut16766 = false, _mut16767 = false, _mut16768 = false, _mut16769 = false, _mut16770 = false, _mut16771 = false, _mut16772 = false, _mut16773 = false, _mut16774 = false, _mut16775 = false, _mut16776 = false, _mut16777 = false, _mut16778 = false, _mut16779 = false, _mut16780 = false, _mut16781 = false, _mut16782 = false, _mut16783 = false, _mut16784 = false, _mut16785 = false, _mut16786 = false, _mut16787 = false, _mut16788 = false, _mut16789 = false, _mut16790 = false, _mut16791 = false, _mut16792 = false, _mut16793 = false, _mut16794 = false, _mut16795 = false, _mut16796 = false, _mut16797 = false, _mut16798 = false, _mut16799 = false, _mut16800 = false, _mut16801 = false, _mut16802 = false, _mut16803 = false, _mut16804 = false, _mut16805 = false, _mut16806 = false, _mut16807 = false, _mut16808 = false, _mut16809 = false, _mut16810 = false, _mut16811 = false, _mut16812 = false, _mut16813 = false, _mut16814 = false, _mut16815 = false, _mut16816 = false, _mut16817 = false, _mut16818 = false, _mut16819 = false, _mut16820 = false, _mut16821 = false, _mut16822 = false, _mut16823 = false, _mut16824 = false, _mut16825 = false, _mut16826 = false, _mut16827 = false, _mut16828 = false, _mut16829 = false, _mut16830 = false, _mut16831 = false, _mut16832 = false, _mut16833 = false, _mut16834 = false, _mut16835 = false, _mut16836 = false, _mut16837 = false, _mut16838 = false, _mut16839 = false, _mut16840 = false, _mut16841 = false, _mut16842 = false, _mut16843 = false, _mut16844 = false, _mut16845 = false, _mut16846 = false, _mut16847 = false, _mut16848 = false, _mut16849 = false, _mut16850 = false, _mut16851 = false, _mut16852 = false, _mut16853 = false, _mut16854 = false, _mut16855 = false, _mut16856 = false, _mut16857 = false, _mut16858 = false, _mut16859 = false, _mut16860 = false, _mut16861 = false, _mut16862 = false, _mut16863 = false, _mut16864 = false, _mut16865 = false, _mut16866 = false, _mut16867 = false, _mut16868 = false, _mut16869 = false, _mut16870 = false, _mut16871 = false, _mut16872 = false, _mut16873 = false, _mut16874 = false, _mut16875 = false, _mut16876 = false, _mut16877 = false, _mut16878 = false, _mut16879 = false, _mut16880 = false, _mut16881 = false, _mut16882 = false, _mut16883 = false, _mut16884 = false, _mut16885 = false, _mut16886 = false, _mut16887 = false, _mut16888 = false, _mut16889 = false, _mut16890 = false, _mut16891 = false, _mut16892 = false, _mut16893 = false, _mut16894 = false, _mut16895 = false, _mut16896 = false, _mut16897 = false, _mut16898 = false, _mut16899 = false, _mut16900 = false, _mut16901 = false, _mut16902 = false, _mut16903 = false, _mut16904 = false, _mut16905 = false, _mut16906 = false, _mut16907 = false, _mut16908 = false, _mut16909 = false, _mut16910 = false, _mut16911 = false, _mut16912 = false, _mut16913 = false, _mut16914 = false, _mut16915 = false, _mut16916 = false, _mut16917 = false, _mut16918 = false, _mut16919 = false, _mut16920 = false, _mut16921 = false, _mut16922 = false, _mut16923 = false, _mut16924 = false, _mut16925 = false, _mut16926 = false, _mut16927 = false, _mut16928 = false, _mut16929 = false, _mut16930 = false, _mut16931 = false, _mut16932 = false, _mut16933 = false, _mut16934 = false, _mut16935 = false, _mut16936 = false, _mut16937 = false, _mut16938 = false, _mut16939 = false, _mut16940 = false, _mut16941 = false, _mut16942 = false, _mut16943 = false, _mut16944 = false, _mut16945 = false, _mut16946 = false, _mut16947 = false, _mut16948 = false, _mut16949 = false, _mut16950 = false, _mut16951 = false, _mut16952 = false, _mut16953 = false, _mut16954 = false, _mut16955 = false, _mut16956 = false, _mut16957 = false, _mut16958 = false, _mut16959 = false, _mut16960 = false, _mut16961 = false, _mut16962 = false, _mut16963 = false, _mut16964 = false, _mut16965 = false, _mut16966 = false, _mut16967 = false, _mut16968 = false, _mut16969 = false, _mut16970 = false, _mut16971 = false, _mut16972 = false, _mut16973 = false, _mut16974 = false, _mut16975 = false, _mut16976 = false, _mut16977 = false, _mut16978 = false, _mut16979 = false, _mut16980 = false, _mut16981 = false, _mut16982 = false, _mut16983 = false, _mut16984 = false, _mut16985 = false, _mut16986 = false, _mut16987 = false, _mut16988 = false, _mut16989 = false, _mut16990 = false, _mut16991 = false, _mut16992 = false, _mut16993 = false, _mut16994 = false, _mut16995 = false, _mut16996 = false, _mut16997 = false, _mut16998 = false, _mut16999 = false, _mut17000 = false, _mut17001 = false, _mut17002 = false, _mut17003 = false, _mut17004 = false, _mut17005 = false, _mut17006 = false, _mut17007 = false, _mut17008 = false, _mut17009 = false, _mut17010 = false, _mut17011 = false, _mut17012 = false, _mut17013 = false, _mut17014 = false, _mut17015 = false, _mut17016 = false, _mut17017 = false, _mut17018 = false, _mut17019 = false, _mut17020 = false, _mut17021 = false, _mut17022 = false, _mut17023 = false, _mut17024 = false, _mut17025 = false, _mut17026 = false, _mut17027 = false, _mut17028 = false, _mut17029 = false, _mut17030 = false, _mut17031 = false, _mut17032 = false, _mut17033 = false, _mut17034 = false, _mut17035 = false, _mut17036 = false, _mut17037 = false, _mut17038 = false, _mut17039 = false, _mut17040 = false, _mut17041 = false, _mut17042 = false, _mut17043 = false, _mut17044 = false, _mut17045 = false, _mut17046 = false, _mut17047 = false, _mut17048 = false, _mut17049 = false, _mut17050 = false, _mut17051 = false, _mut17052 = false, _mut17053 = false, _mut17054 = false, _mut17055 = false, _mut17056 = false, _mut17057 = false, _mut17058 = false, _mut17059 = false, _mut17060 = false, _mut17061 = false, _mut17062 = false, _mut17063 = false, _mut17064 = false, _mut17065 = false, _mut17066 = false, _mut17067 = false, _mut17068 = false, _mut17069 = false, _mut17070 = false, _mut17071 = false, _mut17072 = false, _mut17073 = false, _mut17074 = false, _mut17075 = false, _mut17076 = false, _mut17077 = false, _mut17078 = false, _mut17079 = false, _mut17080 = false, _mut17081 = false, _mut17082 = false, _mut17083 = false, _mut17084 = false, _mut17085 = false, _mut17086 = false, _mut17087 = false, _mut17088 = false, _mut17089 = false, _mut17090 = false, _mut17091 = false, _mut17092 = false, _mut17093 = false, _mut17094 = false, _mut17095 = false, _mut17096 = false, _mut17097 = false, _mut17098 = false, _mut17099 = false, _mut17100 = false, _mut17101 = false, _mut17102 = false, _mut17103 = false, _mut17104 = false, _mut17105 = false, _mut17106 = false, _mut17107 = false, _mut17108 = false, _mut17109 = false, _mut17110 = false, _mut17111 = false, _mut17112 = false, _mut17113 = false, _mut17114 = false, _mut17115 = false, _mut17116 = false, _mut17117 = false, _mut17118 = false, _mut17119 = false, _mut17120 = false, _mut17121 = false, _mut17122 = false, _mut17123 = false, _mut17124 = false, _mut17125 = false, _mut17126 = false, _mut17127 = false, _mut17128 = false, _mut17129 = false, _mut17130 = false, _mut17131 = false, _mut17132 = false, _mut17133 = false, _mut17134 = false, _mut17135 = false, _mut17136 = false, _mut17137 = false, _mut17138 = false, _mut17139 = false, _mut17140 = false, _mut17141 = false, _mut17142 = false, _mut17143 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20110928L;

    /**
     * Slope at the beginning of the step.
     */
    private double[] y0Dot;

    /**
     * State at the end of the step.
     */
    private double[] y1;

    /**
     * Slope at the end of the step.
     */
    private double[] y1Dot;

    /**
     * Derivatives at the middle of the step.
     * element 0 is state at midpoint, element 1 is first derivative ...
     */
    private double[][] yMidDots;

    /**
     * Interpolation polynomials.
     */
    private double[][] polynomials;

    /**
     * Error coefficients for the interpolation.
     */
    private double[] errfac;

    /**
     * Degree of the interpolation polynomials.
     */
    private int currentDegree;

    // the public modifier here is needed for serialization
    public GraggBulirschStoerStepInterpolator() {
        y0Dot = null;
        y1 = null;
        y1Dot = null;
        yMidDots = null;
        resetTables(-1);
    }

    /**
     * Simple constructor.
     * @param y reference to the integrator array holding the current state
     * @param y0Dot reference to the integrator array holding the slope
     * at the beginning of the step
     * @param y1 reference to the integrator array holding the state at
     * the end of the step
     * @param y1Dot reference to the integrator array holding the slope
     * at the end of the step
     * @param yMidDots reference to the integrator array holding the
     * derivatives at the middle point of the step
     * @param forward integration direction indicator
     * @param primaryMapper equations mapper for the primary equations set
     * @param secondaryMappers equations mappers for the secondary equations sets
     */
    GraggBulirschStoerStepInterpolator(final double[] y, final double[] y0Dot, final double[] y1, final double[] y1Dot, final double[][] yMidDots, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        super(y, forward, primaryMapper, secondaryMappers);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.GraggBulirschStoerStepInterpolator_135");
        this.y0Dot = y0Dot;
        this.y1 = y1;
        this.y1Dot = y1Dot;
        this.yMidDots = yMidDots;
        resetTables(AOR_plus(yMidDots.length, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.GraggBulirschStoerStepInterpolator_135", _mut16607, _mut16608, _mut16609, _mut16610));
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    GraggBulirschStoerStepInterpolator(final GraggBulirschStoerStepInterpolator interpolator) {
        super(interpolator);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.GraggBulirschStoerStepInterpolator_157");
        final int dimension = currentState.length;
        // the following arrays are not needed anymore
        y0Dot = null;
        y1 = null;
        y1Dot = null;
        yMidDots = null;
        // copy the interpolation polynomials (up to the current degree only)
        if (interpolator.polynomials == null) {
            polynomials = null;
            currentDegree = -1;
        } else {
            resetTables(interpolator.currentDegree);
            for (int i = 0; ROR_less(i, polynomials.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.GraggBulirschStoerStepInterpolator_157", _mut16611, _mut16612, _mut16613, _mut16614, _mut16615); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.GraggBulirschStoerStepInterpolator_157");
                polynomials[i] = new double[dimension];
                System.arraycopy(interpolator.polynomials[i], 0, polynomials[i], 0, dimension);
            }
            currentDegree = interpolator.currentDegree;
        }
    }

    /**
     * Reallocate the internal tables.
     * Reallocate the internal tables in order to be able to handle
     * interpolation polynomials up to the given degree
     * @param maxDegree maximal degree to handle
     */
    private void resetTables(final int maxDegree) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191");
        if (ROR_less(maxDegree, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16616, _mut16617, _mut16618, _mut16619, _mut16620)) {
            polynomials = null;
            errfac = null;
            currentDegree = -1;
        } else {
            final double[][] newPols = new double[AOR_plus(maxDegree, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16621, _mut16622, _mut16623, _mut16624)][];
            if (polynomials != null) {
                System.arraycopy(polynomials, 0, newPols, 0, polynomials.length);
                for (int i = polynomials.length; ROR_less(i, newPols.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16630, _mut16631, _mut16632, _mut16633, _mut16634); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191");
                    newPols[i] = new double[currentState.length];
                }
            } else {
                for (int i = 0; ROR_less(i, newPols.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16625, _mut16626, _mut16627, _mut16628, _mut16629); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191");
                    newPols[i] = new double[currentState.length];
                }
            }
            polynomials = newPols;
            // initialize the error factors array for interpolation
            if (ROR_less_equals(maxDegree, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16635, _mut16636, _mut16637, _mut16638, _mut16639)) {
                errfac = null;
            } else {
                errfac = new double[AOR_minus(maxDegree, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16640, _mut16641, _mut16642, _mut16643)];
                for (int i = 0; ROR_less(i, errfac.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16681, _mut16682, _mut16683, _mut16684, _mut16685); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191");
                    final int ip5 = AOR_plus(i, 5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16644, _mut16645, _mut16646, _mut16647);
                    errfac[i] = AOR_divide(1.0, (AOR_multiply(ip5, ip5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16648, _mut16649, _mut16650, _mut16651)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16652, _mut16653, _mut16654, _mut16655);
                    final double e = AOR_multiply(0.5, FastMath.sqrt(AOR_divide(((double) (AOR_plus(i, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16656, _mut16657, _mut16658, _mut16659))), ip5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16660, _mut16661, _mut16662, _mut16663)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16664, _mut16665, _mut16666, _mut16667);
                    for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16676, _mut16677, _mut16678, _mut16679, _mut16680); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191");
                        errfac[i] *= AOR_divide(e, (AOR_plus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16668, _mut16669, _mut16670, _mut16671)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.resetTables_191", _mut16672, _mut16673, _mut16674, _mut16675);
                    }
                }
            }
            currentDegree = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new GraggBulirschStoerStepInterpolator(this);
    }

    /**
     * Compute the interpolation coefficients for dense output.
     * @param mu degree of the interpolation polynomial
     * @param h current step
     */
    public void computeCoefficients(final int mu, final double h) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244");
        if ((_mut16695 ? ((polynomials == null) && (ROR_less_equals(polynomials.length, (AOR_plus(mu, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16686, _mut16687, _mut16688, _mut16689)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16690, _mut16691, _mut16692, _mut16693, _mut16694))) : ((polynomials == null) || (ROR_less_equals(polynomials.length, (AOR_plus(mu, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16686, _mut16687, _mut16688, _mut16689)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16690, _mut16691, _mut16692, _mut16693, _mut16694))))) {
            resetTables(AOR_plus(mu, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16696, _mut16697, _mut16698, _mut16699));
        }
        currentDegree = AOR_plus(mu, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16700, _mut16701, _mut16702, _mut16703);
        for (int i = 0; ROR_less(i, currentState.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16897, _mut16898, _mut16899, _mut16900, _mut16901); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244");
            final double yp0 = AOR_multiply(h, y0Dot[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16704, _mut16705, _mut16706, _mut16707);
            final double yp1 = AOR_multiply(h, y1Dot[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16708, _mut16709, _mut16710, _mut16711);
            final double ydiff = AOR_minus(y1[i], currentState[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16712, _mut16713, _mut16714, _mut16715);
            final double aspl = AOR_minus(ydiff, yp1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16716, _mut16717, _mut16718, _mut16719);
            final double bspl = AOR_minus(yp0, ydiff, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16720, _mut16721, _mut16722, _mut16723);
            polynomials[0][i] = currentState[i];
            polynomials[1][i] = ydiff;
            polynomials[2][i] = aspl;
            polynomials[3][i] = bspl;
            if (ROR_less(mu, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16724, _mut16725, _mut16726, _mut16727, _mut16728)) {
                return;
            }
            // compute the remaining coefficients
            final double ph0 = AOR_plus(AOR_multiply(0.5, (AOR_plus(currentState[i], y1[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16729, _mut16730, _mut16731, _mut16732)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16733, _mut16734, _mut16735, _mut16736), AOR_multiply(0.125, (AOR_plus(aspl, bspl, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16737, _mut16738, _mut16739, _mut16740)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16741, _mut16742, _mut16743, _mut16744), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16745, _mut16746, _mut16747, _mut16748);
            polynomials[4][i] = AOR_multiply(16, (AOR_minus(yMidDots[0][i], ph0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16749, _mut16750, _mut16751, _mut16752)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16753, _mut16754, _mut16755, _mut16756);
            if (ROR_greater(mu, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16757, _mut16758, _mut16759, _mut16760, _mut16761)) {
                final double ph1 = AOR_plus(ydiff, AOR_multiply(0.25, (AOR_minus(aspl, bspl, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16762, _mut16763, _mut16764, _mut16765)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16766, _mut16767, _mut16768, _mut16769), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16770, _mut16771, _mut16772, _mut16773);
                polynomials[5][i] = AOR_multiply(16, (AOR_minus(yMidDots[1][i], ph1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16774, _mut16775, _mut16776, _mut16777)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16778, _mut16779, _mut16780, _mut16781);
                if (ROR_greater(mu, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16782, _mut16783, _mut16784, _mut16785, _mut16786)) {
                    final double ph2 = AOR_minus(yp1, yp0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16787, _mut16788, _mut16789, _mut16790);
                    polynomials[6][i] = AOR_multiply(16, (AOR_plus(AOR_minus(yMidDots[2][i], ph2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16791, _mut16792, _mut16793, _mut16794), polynomials[4][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16795, _mut16796, _mut16797, _mut16798)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16799, _mut16800, _mut16801, _mut16802);
                    if (ROR_greater(mu, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16803, _mut16804, _mut16805, _mut16806, _mut16807)) {
                        final double ph3 = AOR_multiply(6, (AOR_minus(bspl, aspl, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16808, _mut16809, _mut16810, _mut16811)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16812, _mut16813, _mut16814, _mut16815);
                        polynomials[7][i] = AOR_multiply(16, (AOR_plus(AOR_minus(yMidDots[3][i], ph3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16816, _mut16817, _mut16818, _mut16819), AOR_multiply(3, polynomials[5][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16820, _mut16821, _mut16822, _mut16823), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16824, _mut16825, _mut16826, _mut16827)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16828, _mut16829, _mut16830, _mut16831);
                        for (int j = 4; ROR_less_equals(j, mu, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16892, _mut16893, _mut16894, _mut16895, _mut16896); ++j) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244");
                            final double fac1 = AOR_multiply(AOR_multiply(0.5, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16832, _mut16833, _mut16834, _mut16835), (AOR_minus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16836, _mut16837, _mut16838, _mut16839)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16840, _mut16841, _mut16842, _mut16843);
                            final double fac2 = AOR_multiply(AOR_multiply(AOR_multiply(2, fac1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16844, _mut16845, _mut16846, _mut16847), (AOR_minus(j, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16848, _mut16849, _mut16850, _mut16851)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16852, _mut16853, _mut16854, _mut16855), (AOR_minus(j, 3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16856, _mut16857, _mut16858, _mut16859)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16860, _mut16861, _mut16862, _mut16863);
                            polynomials[AOR_plus(j, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16864, _mut16865, _mut16866, _mut16867)][i] = AOR_multiply(16, (AOR_minus(AOR_plus(yMidDots[j][i], AOR_multiply(fac1, polynomials[AOR_plus(j, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16868, _mut16869, _mut16870, _mut16871)][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16872, _mut16873, _mut16874, _mut16875), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16876, _mut16877, _mut16878, _mut16879), AOR_multiply(fac2, polynomials[j][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16880, _mut16881, _mut16882, _mut16883), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16884, _mut16885, _mut16886, _mut16887)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeCoefficients_244", _mut16888, _mut16889, _mut16890, _mut16891);
                        }
                    }
                }
            }
        }
    }

    /**
     * Estimate interpolation error.
     * @param scale scaling array
     * @return estimate of the interpolation error
     */
    public double estimateError(final double[] scale) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303");
        double error = 0;
        if (ROR_greater_equals(currentDegree, 5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16902, _mut16903, _mut16904, _mut16905, _mut16906)) {
            for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16915, _mut16916, _mut16917, _mut16918, _mut16919); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303");
                final double e = AOR_divide(polynomials[currentDegree][i], scale[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16907, _mut16908, _mut16909, _mut16910);
                error += AOR_multiply(e, e, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16911, _mut16912, _mut16913, _mut16914);
            }
            error = AOR_multiply(FastMath.sqrt(AOR_divide(error, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16920, _mut16921, _mut16922, _mut16923)), errfac[AOR_minus(currentDegree, 5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16924, _mut16925, _mut16926, _mut16927)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.estimateError_303", _mut16928, _mut16929, _mut16930, _mut16931);
        }
        return error;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316");
        final int dimension = currentState.length;
        final double oneMinusTheta = AOR_minus(1.0, theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16932, _mut16933, _mut16934, _mut16935);
        final double theta05 = AOR_minus(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16936, _mut16937, _mut16938, _mut16939);
        final double tOmT = AOR_multiply(theta, oneMinusTheta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16940, _mut16941, _mut16942, _mut16943);
        final double t4 = AOR_multiply(tOmT, tOmT, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16944, _mut16945, _mut16946, _mut16947);
        final double t4Dot = AOR_multiply(AOR_multiply(2, tOmT, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16948, _mut16949, _mut16950, _mut16951), (AOR_minus(1, AOR_multiply(2, theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16952, _mut16953, _mut16954, _mut16955), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16956, _mut16957, _mut16958, _mut16959)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16960, _mut16961, _mut16962, _mut16963);
        final double dot1 = AOR_divide(1.0, h, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16964, _mut16965, _mut16966, _mut16967);
        final double dot2 = AOR_divide(AOR_multiply(theta, (AOR_minus(2, AOR_multiply(3, theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16968, _mut16969, _mut16970, _mut16971), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16972, _mut16973, _mut16974, _mut16975)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16976, _mut16977, _mut16978, _mut16979), h, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16980, _mut16981, _mut16982, _mut16983);
        final double dot3 = AOR_divide((AOR_plus(AOR_multiply((AOR_minus(AOR_multiply(3, theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16984, _mut16985, _mut16986, _mut16987), 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16988, _mut16989, _mut16990, _mut16991)), theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16992, _mut16993, _mut16994, _mut16995), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut16996, _mut16997, _mut16998, _mut16999)), h, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17000, _mut17001, _mut17002, _mut17003);
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17114, _mut17115, _mut17116, _mut17117, _mut17118); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316");
            final double p0 = polynomials[0][i];
            final double p1 = polynomials[1][i];
            final double p2 = polynomials[2][i];
            final double p3 = polynomials[3][i];
            interpolatedState[i] = AOR_plus(p0, AOR_multiply(theta, (AOR_plus(p1, AOR_multiply(oneMinusTheta, (AOR_plus(AOR_multiply(p2, theta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17004, _mut17005, _mut17006, _mut17007), AOR_multiply(p3, oneMinusTheta, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17008, _mut17009, _mut17010, _mut17011), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17012, _mut17013, _mut17014, _mut17015)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17016, _mut17017, _mut17018, _mut17019), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17020, _mut17021, _mut17022, _mut17023)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17024, _mut17025, _mut17026, _mut17027), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17028, _mut17029, _mut17030, _mut17031);
            interpolatedDerivatives[i] = AOR_plus(AOR_plus(AOR_multiply(dot1, p1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17032, _mut17033, _mut17034, _mut17035), AOR_multiply(dot2, p2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17036, _mut17037, _mut17038, _mut17039), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17040, _mut17041, _mut17042, _mut17043), AOR_multiply(dot3, p3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17044, _mut17045, _mut17046, _mut17047), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17048, _mut17049, _mut17050, _mut17051);
            if (ROR_greater(currentDegree, 3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17052, _mut17053, _mut17054, _mut17055, _mut17056)) {
                double cDot = 0;
                double c = polynomials[currentDegree][i];
                for (int j = currentDegree - 1; ROR_greater(j, 3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17089, _mut17090, _mut17091, _mut17092, _mut17093); --j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316");
                    final double d = AOR_divide(1.0, (AOR_minus(j, 3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17057, _mut17058, _mut17059, _mut17060)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17061, _mut17062, _mut17063, _mut17064);
                    cDot = AOR_multiply(d, (AOR_plus(AOR_multiply(theta05, cDot, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17065, _mut17066, _mut17067, _mut17068), c, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17069, _mut17070, _mut17071, _mut17072)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17073, _mut17074, _mut17075, _mut17076);
                    c = AOR_plus(polynomials[j][i], AOR_multiply(AOR_multiply(c, d, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17077, _mut17078, _mut17079, _mut17080), theta05, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17081, _mut17082, _mut17083, _mut17084), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17085, _mut17086, _mut17087, _mut17088);
                }
                interpolatedState[i] += AOR_multiply(t4, c, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17094, _mut17095, _mut17096, _mut17097);
                interpolatedDerivatives[i] += AOR_divide((AOR_plus(AOR_multiply(t4, cDot, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17098, _mut17099, _mut17100, _mut17101), AOR_multiply(t4Dot, c, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17102, _mut17103, _mut17104, _mut17105), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17106, _mut17107, _mut17108, _mut17109)), h, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17110, _mut17111, _mut17112, _mut17113);
            }
        }
        if (ROR_equals(h, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.computeInterpolatedStateAndDerivatives_316", _mut17119, _mut17120, _mut17121, _mut17122, _mut17123)) {
            // we fix this by using the derivatives at midpoint
            System.arraycopy(yMidDots[1], 0, interpolatedDerivatives, 0, dimension);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.writeExternal_363");
        final int dimension = (currentState == null) ? -1 : currentState.length;
        // save the state of the base class
        writeBaseExternal(out);
        // save the local attributes (but not the temporary vectors)
        out.writeInt(currentDegree);
        for (int k = 0; ROR_less_equals(k, currentDegree, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.writeExternal_363", _mut17129, _mut17130, _mut17131, _mut17132, _mut17133); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.writeExternal_363");
            for (int l = 0; ROR_less(l, dimension, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.writeExternal_363", _mut17124, _mut17125, _mut17126, _mut17127, _mut17128); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.writeExternal_363");
                out.writeDouble(polynomials[k][l]);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.readExternal_383");
        // read the base class
        final double t = readBaseExternal(in);
        final int dimension = (currentState == null) ? -1 : currentState.length;
        // read the local attributes
        final int degree = in.readInt();
        resetTables(degree);
        currentDegree = degree;
        for (int k = 0; ROR_less_equals(k, currentDegree, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.readExternal_383", _mut17139, _mut17140, _mut17141, _mut17142, _mut17143); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.readExternal_383");
            for (int l = 0; ROR_less(l, dimension, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.readExternal_383", _mut17134, _mut17135, _mut17136, _mut17137, _mut17138); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator.readExternal_383");
                polynomials[k][l] = in.readDouble();
            }
        }
        // we can now set the interpolated time and state
        setInterpolatedTime(t);
    }
}
