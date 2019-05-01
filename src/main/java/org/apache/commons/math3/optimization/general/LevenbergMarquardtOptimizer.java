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
package org.apache.commons.math3.optimization.general;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class solves a least squares problem using the Levenberg-Marquardt algorithm.
 *
 * <p>This implementation <em>should</em> work even for over-determined systems
 * (i.e. systems having more point than equations). Over-determined systems
 * are solved by ignoring the point which have the smallest impact according
 * to their jacobian column norm. Only the rank of the matrix and some loop bounds
 * are changed to implement this.</p>
 *
 * <p>The resolution engine is a simple translation of the MINPACK <a
 * href="http://www.netlib.org/minpack/lmder.f">lmder</a> routine with minor
 * changes. The changes include the over-determined resolution, the use of
 * inherited convergence checker and the Q.R. decomposition which has been
 * rewritten following the algorithm described in the
 * P. Lascaux and R. Theodor book <i>Analyse num&eacute;rique matricielle
 * appliqu&eacute;e &agrave; l'art de l'ing&eacute;nieur</i>, Masson 1986.</p>
 * <p>The authors of the original fortran version are:
 * <ul>
 * <li>Argonne National Laboratory. MINPACK project. March 1980</li>
 * <li>Burton S. Garbow</li>
 * <li>Kenneth E. Hillstrom</li>
 * <li>Jorge J. More</li>
 * </ul>
 * The redistribution policy for MINPACK is available <a
 * href="http://www.netlib.org/minpack/disclaimer">here</a>, for convenience, it
 * is reproduced below.</p>
 *
 * <table border="0" width="80%" cellpadding="10" align="center" bgcolor="#E0E0E0">
 * <tr><td>
 *    Minpack Copyright Notice (1999) University of Chicago.
 *    All rights reserved
 * </td></tr>
 * <tr><td>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <ol>
 *  <li>Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.</li>
 * <li>Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.</li>
 * <li>The end-user documentation included with the redistribution, if any,
 *     must include the following acknowledgment:
 *     <code>This product includes software developed by the University of
 *           Chicago, as Operator of Argonne National Laboratory.</code>
 *     Alternately, this acknowledgment may appear in the software itself,
 *     if and wherever such third-party acknowledgments normally appear.</li>
 * <li><strong>WARRANTY DISCLAIMER. THE SOFTWARE IS SUPPLIED "AS IS"
 *     WITHOUT WARRANTY OF ANY KIND. THE COPYRIGHT HOLDER, THE
 *     UNITED STATES, THE UNITED STATES DEPARTMENT OF ENERGY, AND
 *     THEIR EMPLOYEES: (1) DISCLAIM ANY WARRANTIES, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO ANY IMPLIED WARRANTIES
 *     OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE
 *     OR NON-INFRINGEMENT, (2) DO NOT ASSUME ANY LEGAL LIABILITY
 *     OR RESPONSIBILITY FOR THE ACCURACY, COMPLETENESS, OR
 *     USEFULNESS OF THE SOFTWARE, (3) DO NOT REPRESENT THAT USE OF
 *     THE SOFTWARE WOULD NOT INFRINGE PRIVATELY OWNED RIGHTS, (4)
 *     DO NOT WARRANT THAT THE SOFTWARE WILL FUNCTION
 *     UNINTERRUPTED, THAT IT IS ERROR-FREE OR THAT ANY ERRORS WILL
 *     BE CORRECTED.</strong></li>
 * <li><strong>LIMITATION OF LIABILITY. IN NO EVENT WILL THE COPYRIGHT
 *     HOLDER, THE UNITED STATES, THE UNITED STATES DEPARTMENT OF
 *     ENERGY, OR THEIR EMPLOYEES: BE LIABLE FOR ANY INDIRECT,
 *     INCIDENTAL, CONSEQUENTIAL, SPECIAL OR PUNITIVE DAMAGES OF
 *     ANY KIND OR NATURE, INCLUDING BUT NOT LIMITED TO LOSS OF
 *     PROFITS OR LOSS OF DATA, FOR ANY REASON WHATSOEVER, WHETHER
 *     SUCH LIABILITY IS ASSERTED ON THE BASIS OF CONTRACT, TORT
 *     (INCLUDING NEGLIGENCE OR STRICT LIABILITY), OR OTHERWISE,
 *     EVEN IF ANY OF SAID PARTIES HAS BEEN WARNED OF THE
 *     POSSIBILITY OF SUCH LOSS OR DAMAGES.</strong></li>
 * <ol></td></tr>
 * </table>
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {

    @Conditional
    public static boolean _mut72374 = false, _mut72375 = false, _mut72376 = false, _mut72377 = false, _mut72378 = false, _mut72379 = false, _mut72380 = false, _mut72381 = false, _mut72382 = false, _mut72383 = false, _mut72384 = false, _mut72385 = false, _mut72386 = false, _mut72387 = false, _mut72388 = false, _mut72389 = false, _mut72390 = false, _mut72391 = false, _mut72392 = false, _mut72393 = false, _mut72394 = false, _mut72395 = false, _mut72396 = false, _mut72397 = false, _mut72398 = false, _mut72399 = false, _mut72400 = false, _mut72401 = false, _mut72402 = false, _mut72403 = false, _mut72404 = false, _mut72405 = false, _mut72406 = false, _mut72407 = false, _mut72408 = false, _mut72409 = false, _mut72410 = false, _mut72411 = false, _mut72412 = false, _mut72413 = false, _mut72414 = false, _mut72415 = false, _mut72416 = false, _mut72417 = false, _mut72418 = false, _mut72419 = false, _mut72420 = false, _mut72421 = false, _mut72422 = false, _mut72423 = false, _mut72424 = false, _mut72425 = false, _mut72426 = false, _mut72427 = false, _mut72428 = false, _mut72429 = false, _mut72430 = false, _mut72431 = false, _mut72432 = false, _mut72433 = false, _mut72434 = false, _mut72435 = false, _mut72436 = false, _mut72437 = false, _mut72438 = false, _mut72439 = false, _mut72440 = false, _mut72441 = false, _mut72442 = false, _mut72443 = false, _mut72444 = false, _mut72445 = false, _mut72446 = false, _mut72447 = false, _mut72448 = false, _mut72449 = false, _mut72450 = false, _mut72451 = false, _mut72452 = false, _mut72453 = false, _mut72454 = false, _mut72455 = false, _mut72456 = false, _mut72457 = false, _mut72458 = false, _mut72459 = false, _mut72460 = false, _mut72461 = false, _mut72462 = false, _mut72463 = false, _mut72464 = false, _mut72465 = false, _mut72466 = false, _mut72467 = false, _mut72468 = false, _mut72469 = false, _mut72470 = false, _mut72471 = false, _mut72472 = false, _mut72473 = false, _mut72474 = false, _mut72475 = false, _mut72476 = false, _mut72477 = false, _mut72478 = false, _mut72479 = false, _mut72480 = false, _mut72481 = false, _mut72482 = false, _mut72483 = false, _mut72484 = false, _mut72485 = false, _mut72486 = false, _mut72487 = false, _mut72488 = false, _mut72489 = false, _mut72490 = false, _mut72491 = false, _mut72492 = false, _mut72493 = false, _mut72494 = false, _mut72495 = false, _mut72496 = false, _mut72497 = false, _mut72498 = false, _mut72499 = false, _mut72500 = false, _mut72501 = false, _mut72502 = false, _mut72503 = false, _mut72504 = false, _mut72505 = false, _mut72506 = false, _mut72507 = false, _mut72508 = false, _mut72509 = false, _mut72510 = false, _mut72511 = false, _mut72512 = false, _mut72513 = false, _mut72514 = false, _mut72515 = false, _mut72516 = false, _mut72517 = false, _mut72518 = false, _mut72519 = false, _mut72520 = false, _mut72521 = false, _mut72522 = false, _mut72523 = false, _mut72524 = false, _mut72525 = false, _mut72526 = false, _mut72527 = false, _mut72528 = false, _mut72529 = false, _mut72530 = false, _mut72531 = false, _mut72532 = false, _mut72533 = false, _mut72534 = false, _mut72535 = false, _mut72536 = false, _mut72537 = false, _mut72538 = false, _mut72539 = false, _mut72540 = false, _mut72541 = false, _mut72542 = false, _mut72543 = false, _mut72544 = false, _mut72545 = false, _mut72546 = false, _mut72547 = false, _mut72548 = false, _mut72549 = false, _mut72550 = false, _mut72551 = false, _mut72552 = false, _mut72553 = false, _mut72554 = false, _mut72555 = false, _mut72556 = false, _mut72557 = false, _mut72558 = false, _mut72559 = false, _mut72560 = false, _mut72561 = false, _mut72562 = false, _mut72563 = false, _mut72564 = false, _mut72565 = false, _mut72566 = false, _mut72567 = false, _mut72568 = false, _mut72569 = false, _mut72570 = false, _mut72571 = false, _mut72572 = false, _mut72573 = false, _mut72574 = false, _mut72575 = false, _mut72576 = false, _mut72577 = false, _mut72578 = false, _mut72579 = false, _mut72580 = false, _mut72581 = false, _mut72582 = false, _mut72583 = false, _mut72584 = false, _mut72585 = false, _mut72586 = false, _mut72587 = false, _mut72588 = false, _mut72589 = false, _mut72590 = false, _mut72591 = false, _mut72592 = false, _mut72593 = false, _mut72594 = false, _mut72595 = false, _mut72596 = false, _mut72597 = false, _mut72598 = false, _mut72599 = false, _mut72600 = false, _mut72601 = false, _mut72602 = false, _mut72603 = false, _mut72604 = false, _mut72605 = false, _mut72606 = false, _mut72607 = false, _mut72608 = false, _mut72609 = false, _mut72610 = false, _mut72611 = false, _mut72612 = false, _mut72613 = false, _mut72614 = false, _mut72615 = false, _mut72616 = false, _mut72617 = false, _mut72618 = false, _mut72619 = false, _mut72620 = false, _mut72621 = false, _mut72622 = false, _mut72623 = false, _mut72624 = false, _mut72625 = false, _mut72626 = false, _mut72627 = false, _mut72628 = false, _mut72629 = false, _mut72630 = false, _mut72631 = false, _mut72632 = false, _mut72633 = false, _mut72634 = false, _mut72635 = false, _mut72636 = false, _mut72637 = false, _mut72638 = false, _mut72639 = false, _mut72640 = false, _mut72641 = false, _mut72642 = false, _mut72643 = false, _mut72644 = false, _mut72645 = false, _mut72646 = false, _mut72647 = false, _mut72648 = false, _mut72649 = false, _mut72650 = false, _mut72651 = false, _mut72652 = false, _mut72653 = false, _mut72654 = false, _mut72655 = false, _mut72656 = false, _mut72657 = false, _mut72658 = false, _mut72659 = false, _mut72660 = false, _mut72661 = false, _mut72662 = false, _mut72663 = false, _mut72664 = false, _mut72665 = false, _mut72666 = false, _mut72667 = false, _mut72668 = false, _mut72669 = false, _mut72670 = false, _mut72671 = false, _mut72672 = false, _mut72673 = false, _mut72674 = false, _mut72675 = false, _mut72676 = false, _mut72677 = false, _mut72678 = false, _mut72679 = false, _mut72680 = false, _mut72681 = false, _mut72682 = false, _mut72683 = false, _mut72684 = false, _mut72685 = false, _mut72686 = false, _mut72687 = false, _mut72688 = false, _mut72689 = false, _mut72690 = false, _mut72691 = false, _mut72692 = false, _mut72693 = false, _mut72694 = false, _mut72695 = false, _mut72696 = false, _mut72697 = false, _mut72698 = false, _mut72699 = false, _mut72700 = false, _mut72701 = false, _mut72702 = false, _mut72703 = false, _mut72704 = false, _mut72705 = false, _mut72706 = false, _mut72707 = false, _mut72708 = false, _mut72709 = false, _mut72710 = false, _mut72711 = false, _mut72712 = false, _mut72713 = false, _mut72714 = false, _mut72715 = false, _mut72716 = false, _mut72717 = false, _mut72718 = false, _mut72719 = false, _mut72720 = false, _mut72721 = false, _mut72722 = false, _mut72723 = false, _mut72724 = false, _mut72725 = false, _mut72726 = false, _mut72727 = false, _mut72728 = false, _mut72729 = false, _mut72730 = false, _mut72731 = false, _mut72732 = false, _mut72733 = false, _mut72734 = false, _mut72735 = false, _mut72736 = false, _mut72737 = false, _mut72738 = false, _mut72739 = false, _mut72740 = false, _mut72741 = false, _mut72742 = false, _mut72743 = false, _mut72744 = false, _mut72745 = false, _mut72746 = false, _mut72747 = false, _mut72748 = false, _mut72749 = false, _mut72750 = false, _mut72751 = false, _mut72752 = false, _mut72753 = false, _mut72754 = false, _mut72755 = false, _mut72756 = false, _mut72757 = false, _mut72758 = false, _mut72759 = false, _mut72760 = false, _mut72761 = false, _mut72762 = false, _mut72763 = false, _mut72764 = false, _mut72765 = false, _mut72766 = false, _mut72767 = false, _mut72768 = false, _mut72769 = false, _mut72770 = false, _mut72771 = false, _mut72772 = false, _mut72773 = false, _mut72774 = false, _mut72775 = false, _mut72776 = false, _mut72777 = false, _mut72778 = false, _mut72779 = false, _mut72780 = false, _mut72781 = false, _mut72782 = false, _mut72783 = false, _mut72784 = false, _mut72785 = false, _mut72786 = false, _mut72787 = false, _mut72788 = false, _mut72789 = false, _mut72790 = false, _mut72791 = false, _mut72792 = false, _mut72793 = false, _mut72794 = false, _mut72795 = false, _mut72796 = false, _mut72797 = false, _mut72798 = false, _mut72799 = false, _mut72800 = false, _mut72801 = false, _mut72802 = false, _mut72803 = false, _mut72804 = false, _mut72805 = false, _mut72806 = false, _mut72807 = false, _mut72808 = false, _mut72809 = false, _mut72810 = false, _mut72811 = false, _mut72812 = false, _mut72813 = false, _mut72814 = false, _mut72815 = false, _mut72816 = false, _mut72817 = false, _mut72818 = false, _mut72819 = false, _mut72820 = false, _mut72821 = false, _mut72822 = false, _mut72823 = false, _mut72824 = false, _mut72825 = false, _mut72826 = false, _mut72827 = false, _mut72828 = false, _mut72829 = false, _mut72830 = false, _mut72831 = false, _mut72832 = false, _mut72833 = false, _mut72834 = false, _mut72835 = false, _mut72836 = false, _mut72837 = false, _mut72838 = false, _mut72839 = false, _mut72840 = false, _mut72841 = false, _mut72842 = false, _mut72843 = false, _mut72844 = false, _mut72845 = false, _mut72846 = false, _mut72847 = false, _mut72848 = false, _mut72849 = false, _mut72850 = false, _mut72851 = false, _mut72852 = false, _mut72853 = false, _mut72854 = false, _mut72855 = false, _mut72856 = false, _mut72857 = false, _mut72858 = false, _mut72859 = false, _mut72860 = false, _mut72861 = false, _mut72862 = false, _mut72863 = false, _mut72864 = false, _mut72865 = false, _mut72866 = false, _mut72867 = false, _mut72868 = false, _mut72869 = false, _mut72870 = false, _mut72871 = false, _mut72872 = false, _mut72873 = false, _mut72874 = false, _mut72875 = false, _mut72876 = false, _mut72877 = false, _mut72878 = false, _mut72879 = false, _mut72880 = false, _mut72881 = false, _mut72882 = false, _mut72883 = false, _mut72884 = false, _mut72885 = false, _mut72886 = false, _mut72887 = false, _mut72888 = false, _mut72889 = false, _mut72890 = false, _mut72891 = false, _mut72892 = false, _mut72893 = false, _mut72894 = false, _mut72895 = false, _mut72896 = false, _mut72897 = false, _mut72898 = false, _mut72899 = false, _mut72900 = false, _mut72901 = false, _mut72902 = false, _mut72903 = false, _mut72904 = false, _mut72905 = false, _mut72906 = false, _mut72907 = false, _mut72908 = false, _mut72909 = false, _mut72910 = false, _mut72911 = false, _mut72912 = false, _mut72913 = false, _mut72914 = false, _mut72915 = false, _mut72916 = false, _mut72917 = false, _mut72918 = false, _mut72919 = false, _mut72920 = false, _mut72921 = false, _mut72922 = false, _mut72923 = false, _mut72924 = false, _mut72925 = false, _mut72926 = false, _mut72927 = false, _mut72928 = false, _mut72929 = false, _mut72930 = false, _mut72931 = false, _mut72932 = false, _mut72933 = false, _mut72934 = false, _mut72935 = false, _mut72936 = false, _mut72937 = false, _mut72938 = false, _mut72939 = false, _mut72940 = false, _mut72941 = false, _mut72942 = false, _mut72943 = false, _mut72944 = false, _mut72945 = false, _mut72946 = false, _mut72947 = false, _mut72948 = false, _mut72949 = false, _mut72950 = false, _mut72951 = false, _mut72952 = false, _mut72953 = false, _mut72954 = false, _mut72955 = false, _mut72956 = false, _mut72957 = false, _mut72958 = false, _mut72959 = false, _mut72960 = false, _mut72961 = false, _mut72962 = false, _mut72963 = false, _mut72964 = false, _mut72965 = false, _mut72966 = false, _mut72967 = false, _mut72968 = false, _mut72969 = false, _mut72970 = false, _mut72971 = false, _mut72972 = false, _mut72973 = false, _mut72974 = false, _mut72975 = false, _mut72976 = false, _mut72977 = false, _mut72978 = false, _mut72979 = false, _mut72980 = false, _mut72981 = false, _mut72982 = false, _mut72983 = false, _mut72984 = false, _mut72985 = false, _mut72986 = false, _mut72987 = false, _mut72988 = false, _mut72989 = false, _mut72990 = false, _mut72991 = false, _mut72992 = false, _mut72993 = false, _mut72994 = false, _mut72995 = false, _mut72996 = false, _mut72997 = false, _mut72998 = false, _mut72999 = false, _mut73000 = false, _mut73001 = false, _mut73002 = false, _mut73003 = false, _mut73004 = false, _mut73005 = false, _mut73006 = false, _mut73007 = false, _mut73008 = false, _mut73009 = false, _mut73010 = false, _mut73011 = false, _mut73012 = false, _mut73013 = false, _mut73014 = false, _mut73015 = false, _mut73016 = false, _mut73017 = false, _mut73018 = false, _mut73019 = false, _mut73020 = false, _mut73021 = false, _mut73022 = false, _mut73023 = false, _mut73024 = false, _mut73025 = false, _mut73026 = false, _mut73027 = false, _mut73028 = false, _mut73029 = false, _mut73030 = false, _mut73031 = false, _mut73032 = false, _mut73033 = false, _mut73034 = false, _mut73035 = false, _mut73036 = false, _mut73037 = false, _mut73038 = false, _mut73039 = false, _mut73040 = false, _mut73041 = false, _mut73042 = false, _mut73043 = false, _mut73044 = false, _mut73045 = false, _mut73046 = false, _mut73047 = false, _mut73048 = false, _mut73049 = false, _mut73050 = false, _mut73051 = false, _mut73052 = false, _mut73053 = false, _mut73054 = false, _mut73055 = false, _mut73056 = false, _mut73057 = false, _mut73058 = false, _mut73059 = false, _mut73060 = false, _mut73061 = false, _mut73062 = false, _mut73063 = false, _mut73064 = false, _mut73065 = false, _mut73066 = false, _mut73067 = false, _mut73068 = false, _mut73069 = false, _mut73070 = false, _mut73071 = false, _mut73072 = false, _mut73073 = false, _mut73074 = false, _mut73075 = false, _mut73076 = false, _mut73077 = false, _mut73078 = false, _mut73079 = false, _mut73080 = false, _mut73081 = false, _mut73082 = false, _mut73083 = false, _mut73084 = false, _mut73085 = false, _mut73086 = false, _mut73087 = false, _mut73088 = false, _mut73089 = false, _mut73090 = false, _mut73091 = false, _mut73092 = false, _mut73093 = false, _mut73094 = false, _mut73095 = false, _mut73096 = false, _mut73097 = false, _mut73098 = false, _mut73099 = false, _mut73100 = false, _mut73101 = false, _mut73102 = false, _mut73103 = false, _mut73104 = false, _mut73105 = false, _mut73106 = false, _mut73107 = false, _mut73108 = false, _mut73109 = false, _mut73110 = false, _mut73111 = false, _mut73112 = false, _mut73113 = false, _mut73114 = false, _mut73115 = false, _mut73116 = false, _mut73117 = false, _mut73118 = false, _mut73119 = false, _mut73120 = false, _mut73121 = false, _mut73122 = false, _mut73123 = false, _mut73124 = false, _mut73125 = false, _mut73126 = false, _mut73127 = false, _mut73128 = false, _mut73129 = false, _mut73130 = false, _mut73131 = false, _mut73132 = false, _mut73133 = false, _mut73134 = false, _mut73135 = false, _mut73136 = false, _mut73137 = false, _mut73138 = false, _mut73139 = false, _mut73140 = false, _mut73141 = false, _mut73142 = false, _mut73143 = false, _mut73144 = false, _mut73145 = false, _mut73146 = false, _mut73147 = false, _mut73148 = false, _mut73149 = false, _mut73150 = false, _mut73151 = false, _mut73152 = false, _mut73153 = false, _mut73154 = false, _mut73155 = false, _mut73156 = false, _mut73157 = false, _mut73158 = false, _mut73159 = false, _mut73160 = false, _mut73161 = false, _mut73162 = false, _mut73163 = false, _mut73164 = false, _mut73165 = false, _mut73166 = false, _mut73167 = false, _mut73168 = false, _mut73169 = false, _mut73170 = false, _mut73171 = false, _mut73172 = false, _mut73173 = false, _mut73174 = false, _mut73175 = false, _mut73176 = false, _mut73177 = false, _mut73178 = false, _mut73179 = false, _mut73180 = false, _mut73181 = false, _mut73182 = false, _mut73183 = false, _mut73184 = false, _mut73185 = false, _mut73186 = false, _mut73187 = false, _mut73188 = false, _mut73189 = false, _mut73190 = false, _mut73191 = false, _mut73192 = false, _mut73193 = false, _mut73194 = false, _mut73195 = false, _mut73196 = false, _mut73197 = false, _mut73198 = false, _mut73199 = false, _mut73200 = false, _mut73201 = false, _mut73202 = false, _mut73203 = false, _mut73204 = false, _mut73205 = false, _mut73206 = false, _mut73207 = false, _mut73208 = false, _mut73209 = false, _mut73210 = false, _mut73211 = false, _mut73212 = false, _mut73213 = false, _mut73214 = false, _mut73215 = false, _mut73216 = false, _mut73217 = false, _mut73218 = false, _mut73219 = false, _mut73220 = false, _mut73221 = false, _mut73222 = false, _mut73223 = false, _mut73224 = false, _mut73225 = false, _mut73226 = false, _mut73227 = false, _mut73228 = false, _mut73229 = false, _mut73230 = false, _mut73231 = false, _mut73232 = false, _mut73233 = false, _mut73234 = false, _mut73235 = false, _mut73236 = false, _mut73237 = false, _mut73238 = false, _mut73239 = false, _mut73240 = false, _mut73241 = false, _mut73242 = false, _mut73243 = false, _mut73244 = false, _mut73245 = false, _mut73246 = false, _mut73247 = false, _mut73248 = false, _mut73249 = false, _mut73250 = false, _mut73251 = false, _mut73252 = false, _mut73253 = false, _mut73254 = false, _mut73255 = false, _mut73256 = false, _mut73257 = false, _mut73258 = false, _mut73259 = false, _mut73260 = false, _mut73261 = false, _mut73262 = false, _mut73263 = false, _mut73264 = false, _mut73265 = false, _mut73266 = false, _mut73267 = false, _mut73268 = false, _mut73269 = false, _mut73270 = false, _mut73271 = false, _mut73272 = false, _mut73273 = false, _mut73274 = false, _mut73275 = false, _mut73276 = false, _mut73277 = false, _mut73278 = false, _mut73279 = false, _mut73280 = false, _mut73281 = false, _mut73282 = false, _mut73283 = false, _mut73284 = false, _mut73285 = false;

    /**
     * Number of solved point.
     */
    private int solvedCols;

    /**
     * Diagonal elements of the R matrix in the Q.R. decomposition.
     */
    private double[] diagR;

    /**
     * Norms of the columns of the jacobian matrix.
     */
    private double[] jacNorm;

    /**
     * Coefficients of the Householder transforms vectors.
     */
    private double[] beta;

    /**
     * Columns permutation array.
     */
    private int[] permutation;

    /**
     * Rank of the jacobian matrix.
     */
    private int rank;

    /**
     * Levenberg-Marquardt parameter.
     */
    private double lmPar;

    /**
     * Parameters evolution direction associated with lmPar.
     */
    private double[] lmDir;

    /**
     * Positive input variable used in determining the initial step bound.
     */
    private final double initialStepBoundFactor;

    /**
     * Desired relative error in the sum of squares.
     */
    private final double costRelativeTolerance;

    /**
     *  Desired relative error in the approximate solution parameters.
     */
    private final double parRelativeTolerance;

    /**
     * Desired max cosine on the orthogonality between the function vector
     * and the columns of the jacobian.
     */
    private final double orthoTolerance;

    /**
     * Threshold for QR ranking.
     */
    private final double qrRankingThreshold;

    /**
     * Weighted residuals.
     */
    private double[] weightedResidual;

    /**
     * Weighted Jacobian.
     */
    private double[][] weightedJacobian;

    /**
     * Build an optimizer for least squares problems with default values
     * for all the tuning parameters (see the {@link
     * #LevenbergMarquardtOptimizer(double,double,double,double,double)
     * other contructor}.
     * The default values for the algorithm settings are:
     * <ul>
     *  <li>Initial step bound factor: 100</li>
     *  <li>Cost relative tolerance: 1e-10</li>
     *  <li>Parameters relative tolerance: 1e-10</li>
     *  <li>Orthogonality tolerance: 1e-10</li>
     *  <li>QR ranking threshold: {@link Precision#SAFE_MIN}</li>
     * </ul>
     */
    public LevenbergMarquardtOptimizer() {
        this(100, 1e-10, 1e-10, 1e-10, Precision.SAFE_MIN);
    }

    /**
     * Constructor that allows the specification of a custom convergence
     * checker.
     * Note that all the usual convergence checks will be <em>disabled</em>.
     * The default values for the algorithm settings are:
     * <ul>
     *  <li>Initial step bound factor: 100</li>
     *  <li>Cost relative tolerance: 1e-10</li>
     *  <li>Parameters relative tolerance: 1e-10</li>
     *  <li>Orthogonality tolerance: 1e-10</li>
     *  <li>QR ranking threshold: {@link Precision#SAFE_MIN}</li>
     * </ul>
     *
     * @param checker Convergence checker.
     */
    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(100, checker, 1e-10, 1e-10, 1e-10, Precision.SAFE_MIN);
    }

    /**
     * Constructor that allows the specification of a custom convergence
     * checker, in addition to the standard ones.
     *
     * @param initialStepBoundFactor Positive input variable used in
     * determining the initial step bound. This bound is set to the
     * product of initialStepBoundFactor and the euclidean norm of
     * {@code diag * x} if non-zero, or else to {@code initialStepBoundFactor}
     * itself. In most cases factor should lie in the interval
     * {@code (0.1, 100.0)}. {@code 100} is a generally recommended value.
     * @param checker Convergence checker.
     * @param costRelativeTolerance Desired relative error in the sum of
     * squares.
     * @param parRelativeTolerance Desired relative error in the approximate
     * solution parameters.
     * @param orthoTolerance Desired max cosine on the orthogonality between
     * the function vector and the columns of the Jacobian.
     * @param threshold Desired threshold for QR ranking. If the squared norm
     * of a column vector is smaller or equal to this threshold during QR
     * decomposition, it is considered to be a zero vector and hence the rank
     * of the matrix is reduced.
     */
    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, ConvergenceChecker<PointVectorValuePair> checker, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        super(checker);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    /**
     * Build an optimizer for least squares problems with default values
     * for some of the tuning parameters (see the {@link
     * #LevenbergMarquardtOptimizer(double,double,double,double,double)
     * other contructor}.
     * The default values for the algorithm settings are:
     * <ul>
     *  <li>Initial step bound factor}: 100</li>
     *  <li>QR ranking threshold}: {@link Precision#SAFE_MIN}</li>
     * </ul>
     *
     * @param costRelativeTolerance Desired relative error in the sum of
     * squares.
     * @param parRelativeTolerance Desired relative error in the approximate
     * solution parameters.
     * @param orthoTolerance Desired max cosine on the orthogonality between
     * the function vector and the columns of the Jacobian.
     */
    public LevenbergMarquardtOptimizer(double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance) {
        this(100, costRelativeTolerance, parRelativeTolerance, orthoTolerance, Precision.SAFE_MIN);
    }

    /**
     * The arguments control the behaviour of the default convergence checking
     * procedure.
     * Additional criteria can defined through the setting of a {@link
     * ConvergenceChecker}.
     *
     * @param initialStepBoundFactor Positive input variable used in
     * determining the initial step bound. This bound is set to the
     * product of initialStepBoundFactor and the euclidean norm of
     * {@code diag * x} if non-zero, or else to {@code initialStepBoundFactor}
     * itself. In most cases factor should lie in the interval
     * {@code (0.1, 100.0)}. {@code 100} is a generally recommended value.
     * @param costRelativeTolerance Desired relative error in the sum of
     * squares.
     * @param parRelativeTolerance Desired relative error in the approximate
     * solution parameters.
     * @param orthoTolerance Desired max cosine on the orthogonality between
     * the function vector and the columns of the Jacobian.
     * @param threshold Desired threshold for QR ranking. If the squared norm
     * of a column vector is smaller or equal to this threshold during QR
     * decomposition, it is considered to be a zero vector and hence the rank
     * of the matrix is reduced.
     */
    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        // No custom convergence criterion.
        super(null);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointVectorValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
        // Number of observed data.
        final int nR = getTarget().length;
        final double[] currentPoint = getStartPoint();
        // Number of parameters.
        final int nC = currentPoint.length;
        // arrays shared with the other private methods
        solvedCols = FastMath.min(nR, nC);
        diagR = new double[nC];
        jacNorm = new double[nC];
        beta = new double[nC];
        permutation = new int[nC];
        lmDir = new double[nC];
        // local point
        double delta = 0;
        double xNorm = 0;
        double[] diag = new double[nC];
        double[] oldX = new double[nC];
        double[] oldRes = new double[nR];
        double[] oldObj = new double[nR];
        double[] qtf = new double[nR];
        double[] work1 = new double[nC];
        double[] work2 = new double[nC];
        double[] work3 = new double[nC];
        final RealMatrix weightMatrixSqrt = getWeightSquareRoot();
        // Evaluate the function at the starting point and calculate its norm.
        double[] currentObjective = computeObjectiveValue(currentPoint);
        double[] currentResiduals = computeResiduals(currentObjective);
        PointVectorValuePair current = new PointVectorValuePair(currentPoint, currentObjective);
        double currentCost = computeCost(currentResiduals);
        // Outer loop.
        lmPar = 0;
        boolean firstIteration = true;
        int iter = 0;
        final ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
            ++iter;
            final PointVectorValuePair previous = current;
            // QR decomposition of the jacobian matrix
            qrDecomposition(computeWeightedJacobian(currentPoint));
            weightedResidual = weightMatrixSqrt.operate(currentResiduals);
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72374, _mut72375, _mut72376, _mut72377, _mut72378); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                qtf[i] = weightedResidual[i];
            }
            // compute Qt.res
            qTy(qtf);
            // so let jacobian contain the R matrix with its diagonal elements
            for (int k = 0; ROR_less(k, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72379, _mut72380, _mut72381, _mut72382, _mut72383); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                int pk = permutation[k];
                weightedJacobian[k][pk] = diagR[pk];
            }
            if (firstIteration) {
                // of the initial jacobian
                xNorm = 0;
                for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72397, _mut72398, _mut72399, _mut72400, _mut72401); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    double dk = jacNorm[k];
                    if (ROR_equals(dk, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72384, _mut72385, _mut72386, _mut72387, _mut72388)) {
                        dk = 1.0;
                    }
                    double xk = AOR_multiply(dk, currentPoint[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72389, _mut72390, _mut72391, _mut72392);
                    xNorm += AOR_multiply(xk, xk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72393, _mut72394, _mut72395, _mut72396);
                    diag[k] = dk;
                }
                xNorm = FastMath.sqrt(xNorm);
                // initialize the step bound delta
                delta = (ROR_equals(xNorm, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72402, _mut72403, _mut72404, _mut72405, _mut72406)) ? initialStepBoundFactor : (AOR_multiply(initialStepBoundFactor, xNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72407, _mut72408, _mut72409, _mut72410));
            }
            // check orthogonality between function vector and jacobian columns
            double maxCosine = 0;
            if (ROR_not_equals(currentCost, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72411, _mut72412, _mut72413, _mut72414, _mut72415)) {
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72438, _mut72439, _mut72440, _mut72441, _mut72442); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    int pj = permutation[j];
                    double s = jacNorm[pj];
                    if (ROR_not_equals(s, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72416, _mut72417, _mut72418, _mut72419, _mut72420)) {
                        double sum = 0;
                        for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72425, _mut72426, _mut72427, _mut72428, _mut72429); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                            sum += AOR_multiply(weightedJacobian[i][pj], qtf[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72421, _mut72422, _mut72423, _mut72424);
                        }
                        maxCosine = FastMath.max(maxCosine, AOR_divide(FastMath.abs(sum), (AOR_multiply(s, currentCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72430, _mut72431, _mut72432, _mut72433)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72434, _mut72435, _mut72436, _mut72437));
                    }
                }
            }
            if (ROR_less_equals(maxCosine, orthoTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72443, _mut72444, _mut72445, _mut72446, _mut72447)) {
                // Convergence has been reached.
                setCost(currentCost);
                // Update (deprecated) "point" field.
                point = current.getPoint();
                return current;
            }
            // rescale if necessary
            for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72448, _mut72449, _mut72450, _mut72451, _mut72452); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                diag[j] = FastMath.max(diag[j], jacNorm[j]);
            }
            // Inner loop.
            for (double ratio = 0; ROR_less(ratio, 1.0e-4, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72702, _mut72703, _mut72704, _mut72705, _mut72706); ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                // save the state
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72453, _mut72454, _mut72455, _mut72456, _mut72457); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    int pj = permutation[j];
                    oldX[pj] = currentPoint[pj];
                }
                final double previousCost = currentCost;
                double[] tmpVec = weightedResidual;
                weightedResidual = oldRes;
                oldRes = tmpVec;
                tmpVec = currentObjective;
                currentObjective = oldObj;
                oldObj = tmpVec;
                // determine the Levenberg-Marquardt parameter
                determineLMParameter(qtf, delta, diag, work1, work2, work3);
                // compute the new point and the norm of the evolution direction
                double lmNorm = 0;
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72470, _mut72471, _mut72472, _mut72473, _mut72474); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    int pj = permutation[j];
                    lmDir[pj] = -lmDir[pj];
                    currentPoint[pj] = AOR_plus(oldX[pj], lmDir[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72458, _mut72459, _mut72460, _mut72461);
                    double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72462, _mut72463, _mut72464, _mut72465);
                    lmNorm += AOR_multiply(s, s, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72466, _mut72467, _mut72468, _mut72469);
                }
                lmNorm = FastMath.sqrt(lmNorm);
                // on the first iteration, adjust the initial step bound.
                if (firstIteration) {
                    delta = FastMath.min(delta, lmNorm);
                }
                // Evaluate the function at x + p and calculate its norm.
                currentObjective = computeObjectiveValue(currentPoint);
                currentResiduals = computeResiduals(currentObjective);
                current = new PointVectorValuePair(currentPoint, currentObjective);
                currentCost = computeCost(currentResiduals);
                // compute the scaled actual reduction
                double actRed = -1.0;
                if (ROR_less(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72475, _mut72476, _mut72477, _mut72478), previousCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72479, _mut72480, _mut72481, _mut72482, _mut72483)) {
                    double r = AOR_divide(currentCost, previousCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72484, _mut72485, _mut72486, _mut72487);
                    actRed = AOR_minus(1.0, AOR_multiply(r, r, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72488, _mut72489, _mut72490, _mut72491), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72492, _mut72493, _mut72494, _mut72495);
                }
                // and the scaled directional derivative
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72505, _mut72506, _mut72507, _mut72508, _mut72509); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    int pj = permutation[j];
                    double dirJ = lmDir[pj];
                    work1[j] = 0;
                    for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72500, _mut72501, _mut72502, _mut72503, _mut72504); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                        work1[i] += AOR_multiply(weightedJacobian[i][pj], dirJ, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72496, _mut72497, _mut72498, _mut72499);
                    }
                }
                double coeff1 = 0;
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72514, _mut72515, _mut72516, _mut72517, _mut72518); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                    coeff1 += AOR_multiply(work1[j], work1[j], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72510, _mut72511, _mut72512, _mut72513);
                }
                double pc2 = AOR_multiply(previousCost, previousCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72519, _mut72520, _mut72521, _mut72522);
                coeff1 /= pc2;
                double coeff2 = AOR_divide(AOR_multiply(AOR_multiply(lmPar, lmNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72523, _mut72524, _mut72525, _mut72526), lmNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72527, _mut72528, _mut72529, _mut72530), pc2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72531, _mut72532, _mut72533, _mut72534);
                double preRed = AOR_plus(coeff1, AOR_multiply(2, coeff2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72535, _mut72536, _mut72537, _mut72538), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72539, _mut72540, _mut72541, _mut72542);
                double dirDer = -(AOR_plus(coeff1, coeff2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72543, _mut72544, _mut72545, _mut72546));
                // ratio of the actual to the predicted reduction
                ratio = (ROR_equals(preRed, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72547, _mut72548, _mut72549, _mut72550, _mut72551)) ? 0 : (AOR_divide(actRed, preRed, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72552, _mut72553, _mut72554, _mut72555));
                // update the step bound
                if (ROR_less_equals(ratio, 0.25, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72556, _mut72557, _mut72558, _mut72559, _mut72560)) {
                    double tmp = (ROR_less(actRed, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72576, _mut72577, _mut72578, _mut72579, _mut72580)) ? (AOR_divide(AOR_multiply(0.5, dirDer, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72581, _mut72582, _mut72583, _mut72584), (AOR_plus(dirDer, AOR_multiply(0.5, actRed, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72585, _mut72586, _mut72587, _mut72588), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72589, _mut72590, _mut72591, _mut72592)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72593, _mut72594, _mut72595, _mut72596)) : 0.5;
                    if ((_mut72611 ? ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72597, _mut72598, _mut72599, _mut72600), previousCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72601, _mut72602, _mut72603, _mut72604, _mut72605)) && (ROR_less(tmp, 0.1, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72606, _mut72607, _mut72608, _mut72609, _mut72610))) : ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72597, _mut72598, _mut72599, _mut72600), previousCost, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72601, _mut72602, _mut72603, _mut72604, _mut72605)) || (ROR_less(tmp, 0.1, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72606, _mut72607, _mut72608, _mut72609, _mut72610))))) {
                        tmp = 0.1;
                    }
                    delta = AOR_multiply(tmp, FastMath.min(delta, AOR_multiply(10.0, lmNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72612, _mut72613, _mut72614, _mut72615)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72616, _mut72617, _mut72618, _mut72619);
                    lmPar /= tmp;
                } else if ((_mut72571 ? ((ROR_equals(lmPar, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72561, _mut72562, _mut72563, _mut72564, _mut72565)) && (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72566, _mut72567, _mut72568, _mut72569, _mut72570))) : ((ROR_equals(lmPar, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72561, _mut72562, _mut72563, _mut72564, _mut72565)) || (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72566, _mut72567, _mut72568, _mut72569, _mut72570))))) {
                    delta = AOR_multiply(2, lmNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72572, _mut72573, _mut72574, _mut72575);
                    lmPar *= 0.5;
                }
                // test for successful iteration.
                if (ROR_greater_equals(ratio, 1.0e-4, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72620, _mut72621, _mut72622, _mut72623, _mut72624)) {
                    // successful iteration, update the norm
                    firstIteration = false;
                    xNorm = 0;
                    for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72638, _mut72639, _mut72640, _mut72641, _mut72642); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                        double xK = AOR_multiply(diag[k], currentPoint[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72630, _mut72631, _mut72632, _mut72633);
                        xNorm += AOR_multiply(xK, xK, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72634, _mut72635, _mut72636, _mut72637);
                    }
                    xNorm = FastMath.sqrt(xNorm);
                    // tests for convergence.
                    if ((_mut72643 ? (checker != null || checker.converged(iter, previous, current)) : (checker != null && checker.converged(iter, previous, current)))) {
                        setCost(currentCost);
                        // Update (deprecated) "point" field.
                        point = current.getPoint();
                        return current;
                    }
                } else {
                    // failed iteration, reset the previous values
                    currentCost = previousCost;
                    for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72625, _mut72626, _mut72627, _mut72628, _mut72629); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278");
                        int pj = permutation[j];
                        currentPoint[pj] = oldX[pj];
                    }
                    tmpVec = weightedResidual;
                    weightedResidual = oldRes;
                    oldRes = tmpVec;
                    tmpVec = currentObjective;
                    currentObjective = oldObj;
                    oldObj = tmpVec;
                    // Reset "current" to previous values.
                    current = new PointVectorValuePair(currentPoint, currentObjective);
                }
                // Default convergence criteria.
                if ((_mut72670 ? (((_mut72660 ? ((_mut72654 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72655, _mut72656, _mut72657, _mut72658, _mut72659)) : ((_mut72654 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72655, _mut72656, _mut72657, _mut72658, _mut72659)))) && ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72661, _mut72662, _mut72663, _mut72664), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72665, _mut72666, _mut72667, _mut72668, _mut72669)) : (((_mut72660 ? ((_mut72654 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72655, _mut72656, _mut72657, _mut72658, _mut72659)) : ((_mut72654 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72644, _mut72645, _mut72646, _mut72647, _mut72648) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72649, _mut72650, _mut72651, _mut72652, _mut72653))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72655, _mut72656, _mut72657, _mut72658, _mut72659)))) || ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72661, _mut72662, _mut72663, _mut72664), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72665, _mut72666, _mut72667, _mut72668, _mut72669)))) {
                    setCost(currentCost);
                    // Update (deprecated) "point" field.
                    point = current.getPoint();
                    return current;
                }
                // (2.2204e-16 is the machine epsilon for IEEE754)
                if ((_mut72687 ? ((_mut72681 ? ((ROR_less_equals(FastMath.abs(actRed), 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72671, _mut72672, _mut72673, _mut72674, _mut72675)) || (ROR_less_equals(preRed, 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72676, _mut72677, _mut72678, _mut72679, _mut72680))) : ((ROR_less_equals(FastMath.abs(actRed), 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72671, _mut72672, _mut72673, _mut72674, _mut72675)) && (ROR_less_equals(preRed, 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72676, _mut72677, _mut72678, _mut72679, _mut72680)))) || (ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72682, _mut72683, _mut72684, _mut72685, _mut72686))) : ((_mut72681 ? ((ROR_less_equals(FastMath.abs(actRed), 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72671, _mut72672, _mut72673, _mut72674, _mut72675)) || (ROR_less_equals(preRed, 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72676, _mut72677, _mut72678, _mut72679, _mut72680))) : ((ROR_less_equals(FastMath.abs(actRed), 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72671, _mut72672, _mut72673, _mut72674, _mut72675)) && (ROR_less_equals(preRed, 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72676, _mut72677, _mut72678, _mut72679, _mut72680)))) && (ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72682, _mut72683, _mut72684, _mut72685, _mut72686))))) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, costRelativeTolerance);
                } else if (ROR_less_equals(delta, AOR_multiply(2.2204e-16, xNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72688, _mut72689, _mut72690, _mut72691), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72692, _mut72693, _mut72694, _mut72695, _mut72696)) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, parRelativeTolerance);
                } else if (ROR_less_equals(maxCosine, 2.2204e-16, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.doOptimize_278", _mut72697, _mut72698, _mut72699, _mut72700, _mut72701)) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, orthoTolerance);
                }
            }
        }
    }

    /**
     * Determine the Levenberg-Marquardt parameter.
     * <p>This implementation is a translation in Java of the MINPACK
     * <a href="http://www.netlib.org/minpack/lmpar.f">lmpar</a>
     * routine.</p>
     * <p>This method sets the lmPar and lmDir attributes.</p>
     * <p>The authors of the original fortran function are:</p>
     * <ul>
     *   <li>Argonne National Laboratory. MINPACK project. March 1980</li>
     *   <li>Burton  S. Garbow</li>
     *   <li>Kenneth E. Hillstrom</li>
     *   <li>Jorge   J. More</li>
     * </ul>
     * <p>Luc Maisonobe did the Java translation.</p>
     *
     * @param qy array containing qTy
     * @param delta upper bound on the euclidean norm of diagR * lmDir
     * @param diag diagonal matrix
     * @param work1 work array
     * @param work2 work array
     * @param work3 work array
     */
    private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
        final int nC = weightedJacobian[0].length;
        // jacobian is rank-deficient, obtain a least squares solution
        for (int j = 0; ROR_less(j, rank, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72707, _mut72708, _mut72709, _mut72710, _mut72711); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            lmDir[permutation[j]] = qy[j];
        }
        for (int j = rank; ROR_less(j, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72712, _mut72713, _mut72714, _mut72715, _mut72716); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            lmDir[permutation[j]] = 0;
        }
        for (int k = rank - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72730, _mut72731, _mut72732, _mut72733, _mut72734); --k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            int pk = permutation[k];
            double ypk = AOR_divide(lmDir[pk], diagR[pk], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72717, _mut72718, _mut72719, _mut72720);
            for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72725, _mut72726, _mut72727, _mut72728, _mut72729); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                lmDir[permutation[i]] -= AOR_multiply(ypk, weightedJacobian[i][pk], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72721, _mut72722, _mut72723, _mut72724);
            }
            lmDir[pk] = ypk;
        }
        // for acceptance of the Gauss-Newton direction
        double dxNorm = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72743, _mut72744, _mut72745, _mut72746, _mut72747); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            int pj = permutation[j];
            double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72735, _mut72736, _mut72737, _mut72738);
            work1[pj] = s;
            dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72739, _mut72740, _mut72741, _mut72742);
        }
        dxNorm = FastMath.sqrt(dxNorm);
        double fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72748, _mut72749, _mut72750, _mut72751);
        if (ROR_less_equals(fp, AOR_multiply(0.1, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72752, _mut72753, _mut72754, _mut72755), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72756, _mut72757, _mut72758, _mut72759, _mut72760)) {
            lmPar = 0;
            return;
        }
        // otherwise set this bound to zero
        double sum2;
        double parl = 0;
        if (ROR_equals(rank, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72761, _mut72762, _mut72763, _mut72764, _mut72765)) {
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72770, _mut72771, _mut72772, _mut72773, _mut72774); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                work1[pj] *= AOR_divide(diag[pj], dxNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72766, _mut72767, _mut72768, _mut72769);
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72796, _mut72797, _mut72798, _mut72799, _mut72800); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                double sum = 0;
                for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72779, _mut72780, _mut72781, _mut72782, _mut72783); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                    sum += AOR_multiply(weightedJacobian[i][pj], work1[permutation[i]], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72775, _mut72776, _mut72777, _mut72778);
                }
                double s = AOR_divide((AOR_minus(work1[pj], sum, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72784, _mut72785, _mut72786, _mut72787)), diagR[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72788, _mut72789, _mut72790, _mut72791);
                work1[pj] = s;
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72792, _mut72793, _mut72794, _mut72795);
            }
            parl = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72801, _mut72802, _mut72803, _mut72804)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72805, _mut72806, _mut72807, _mut72808);
        }
        // calculate an upper bound, paru, for the zero of the function
        sum2 = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72822, _mut72823, _mut72824, _mut72825, _mut72826); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            int pj = permutation[j];
            double sum = 0;
            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72813, _mut72814, _mut72815, _mut72816, _mut72817); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                sum += AOR_multiply(weightedJacobian[i][pj], qy[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72809, _mut72810, _mut72811, _mut72812);
            }
            sum /= diag[pj];
            sum2 += AOR_multiply(sum, sum, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72818, _mut72819, _mut72820, _mut72821);
        }
        double gNorm = FastMath.sqrt(sum2);
        double paru = AOR_divide(gNorm, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72827, _mut72828, _mut72829, _mut72830);
        if (ROR_equals(paru, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72831, _mut72832, _mut72833, _mut72834, _mut72835)) {
            // 2.2251e-308 is the smallest positive real for IEE754
            paru = AOR_divide(2.2251e-308, FastMath.min(delta, 0.1), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72836, _mut72837, _mut72838, _mut72839);
        }
        // set par to the closer endpoint
        lmPar = FastMath.min(paru, FastMath.max(lmPar, parl));
        if (ROR_equals(lmPar, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72840, _mut72841, _mut72842, _mut72843, _mut72844)) {
            lmPar = AOR_divide(gNorm, dxNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72845, _mut72846, _mut72847, _mut72848);
        }
        for (int countdown = 10; ROR_greater_equals(countdown, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72969, _mut72970, _mut72971, _mut72972, _mut72973); --countdown) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
            // evaluate the function at the current value of lmPar
            if (ROR_equals(lmPar, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72849, _mut72850, _mut72851, _mut72852, _mut72853)) {
                lmPar = FastMath.max(2.2251e-308, AOR_multiply(0.001, paru, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72854, _mut72855, _mut72856, _mut72857));
            }
            double sPar = FastMath.sqrt(lmPar);
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72862, _mut72863, _mut72864, _mut72865, _mut72866); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                work1[pj] = AOR_multiply(sPar, diag[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72858, _mut72859, _mut72860, _mut72861);
            }
            determineLMDirection(qy, work1, work2, work3);
            dxNorm = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72875, _mut72876, _mut72877, _mut72878, _mut72879); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72867, _mut72868, _mut72869, _mut72870);
                work3[pj] = s;
                dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72871, _mut72872, _mut72873, _mut72874);
            }
            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72880, _mut72881, _mut72882, _mut72883);
            // of lmPar, also test for the exceptional cases where parl is zero
            if ((_mut72910 ? ((ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72884, _mut72885, _mut72886, _mut72887), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72888, _mut72889, _mut72890, _mut72891, _mut72892)) && ((_mut72909 ? ((_mut72903 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902)))) || (ROR_less(previousFP, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72904, _mut72905, _mut72906, _mut72907, _mut72908))) : ((_mut72903 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902)))) && (ROR_less(previousFP, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72904, _mut72905, _mut72906, _mut72907, _mut72908)))))) : ((ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72884, _mut72885, _mut72886, _mut72887), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72888, _mut72889, _mut72890, _mut72891, _mut72892)) || ((_mut72909 ? ((_mut72903 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902)))) || (ROR_less(previousFP, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72904, _mut72905, _mut72906, _mut72907, _mut72908))) : ((_mut72903 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72893, _mut72894, _mut72895, _mut72896, _mut72897)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72898, _mut72899, _mut72900, _mut72901, _mut72902)))) && (ROR_less(previousFP, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72904, _mut72905, _mut72906, _mut72907, _mut72908)))))))) {
                return;
            }
            // compute the Newton correction
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72919, _mut72920, _mut72921, _mut72922, _mut72923); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                work1[pj] = AOR_divide(AOR_multiply(work3[pj], diag[pj], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72911, _mut72912, _mut72913, _mut72914), dxNorm, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72915, _mut72916, _mut72917, _mut72918);
            }
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72933, _mut72934, _mut72935, _mut72936, _mut72937); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                int pj = permutation[j];
                work1[pj] /= work2[j];
                double tmp = work1[pj];
                for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72928, _mut72929, _mut72930, _mut72931, _mut72932); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                    work1[permutation[i]] -= AOR_multiply(weightedJacobian[i][pj], tmp, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72924, _mut72925, _mut72926, _mut72927);
                }
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72942, _mut72943, _mut72944, _mut72945, _mut72946); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554");
                double s = work1[permutation[j]];
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72938, _mut72939, _mut72940, _mut72941);
            }
            double correction = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72947, _mut72948, _mut72949, _mut72950)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72951, _mut72952, _mut72953, _mut72954);
            // depending on the sign of the function, update parl or paru.
            if (ROR_greater(fp, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72955, _mut72956, _mut72957, _mut72958, _mut72959)) {
                parl = FastMath.max(parl, lmPar);
            } else if (ROR_less(fp, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72960, _mut72961, _mut72962, _mut72963, _mut72964)) {
                paru = FastMath.min(paru, lmPar);
            }
            // compute an improved estimate for lmPar
            lmPar = FastMath.max(parl, AOR_plus(lmPar, correction, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMParameter_554", _mut72965, _mut72966, _mut72967, _mut72968));
        }
    }

    /**
     * Solve a*x = b and d*x = 0 in the least squares sense.
     * <p>This implementation is a translation in Java of the MINPACK
     * <a href="http://www.netlib.org/minpack/qrsolv.f">qrsolv</a>
     * routine.</p>
     * <p>This method sets the lmDir and lmDiag attributes.</p>
     * <p>The authors of the original fortran function are:</p>
     * <ul>
     *   <li>Argonne National Laboratory. MINPACK project. March 1980</li>
     *   <li>Burton  S. Garbow</li>
     *   <li>Kenneth E. Hillstrom</li>
     *   <li>Jorge   J. More</li>
     * </ul>
     * <p>Luc Maisonobe did the Java translation.</p>
     *
     * @param qy array containing qTy
     * @param diag diagonal matrix
     * @param lmDiag diagonal elements associated with lmDir
     * @param work work array
     */
    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
        // in particular, save the diagonal elements of R in lmDir
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72979, _mut72980, _mut72981, _mut72982, _mut72983); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
            int pj = permutation[j];
            for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72974, _mut72975, _mut72976, _mut72977, _mut72978); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
                weightedJacobian[i][pj] = weightedJacobian[j][permutation[i]];
            }
            lmDir[j] = diagR[pj];
            work[j] = qy[j];
        }
        // eliminate the diagonal matrix d using a Givens rotation
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73113, _mut73114, _mut73115, _mut73116, _mut73117); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
            // diagonal element using p from the Q.R. factorization
            int pj = permutation[j];
            double dpj = diag[pj];
            if (ROR_not_equals(dpj, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72984, _mut72985, _mut72986, _mut72987, _mut72988)) {
                Arrays.fill(lmDiag, AOR_plus(j, 1, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72989, _mut72990, _mut72991, _mut72992), lmDiag.length, 0);
            }
            lmDiag[j] = dpj;
            // beyond the first n, which is initially zero.
            double qtbpj = 0;
            for (int k = j; ROR_less(k, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73108, _mut73109, _mut73110, _mut73111, _mut73112); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
                int pk = permutation[k];
                // appropriate element in the current row of d
                if (ROR_not_equals(lmDiag[k], 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72993, _mut72994, _mut72995, _mut72996, _mut72997)) {
                    final double sin;
                    final double cos;
                    double rkk = weightedJacobian[k][pk];
                    if (ROR_less(FastMath.abs(rkk), FastMath.abs(lmDiag[k]), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut72998, _mut72999, _mut73000, _mut73001, _mut73002)) {
                        final double cotan = AOR_divide(rkk, lmDiag[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73023, _mut73024, _mut73025, _mut73026);
                        sin = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(cotan, cotan, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73027, _mut73028, _mut73029, _mut73030), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73031, _mut73032, _mut73033, _mut73034)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73035, _mut73036, _mut73037, _mut73038);
                        cos = AOR_multiply(sin, cotan, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73039, _mut73040, _mut73041, _mut73042);
                    } else {
                        final double tan = AOR_divide(lmDiag[k], rkk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73003, _mut73004, _mut73005, _mut73006);
                        cos = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(tan, tan, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73007, _mut73008, _mut73009, _mut73010), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73011, _mut73012, _mut73013, _mut73014)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73015, _mut73016, _mut73017, _mut73018);
                        sin = AOR_multiply(cos, tan, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73019, _mut73020, _mut73021, _mut73022);
                    }
                    // the modified element of (Qty,0)
                    weightedJacobian[k][pk] = AOR_plus(AOR_multiply(cos, rkk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73043, _mut73044, _mut73045, _mut73046), AOR_multiply(sin, lmDiag[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73047, _mut73048, _mut73049, _mut73050), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73051, _mut73052, _mut73053, _mut73054);
                    final double temp = AOR_plus(AOR_multiply(cos, work[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73055, _mut73056, _mut73057, _mut73058), AOR_multiply(sin, qtbpj, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73059, _mut73060, _mut73061, _mut73062), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73063, _mut73064, _mut73065, _mut73066);
                    qtbpj = AOR_plus(AOR_multiply(-sin, work[k], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73067, _mut73068, _mut73069, _mut73070), AOR_multiply(cos, qtbpj, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73071, _mut73072, _mut73073, _mut73074), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73075, _mut73076, _mut73077, _mut73078);
                    work[k] = temp;
                    // accumulate the tranformation in the row of s
                    for (int i = k + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73103, _mut73104, _mut73105, _mut73106, _mut73107); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
                        double rik = weightedJacobian[i][pk];
                        final double temp2 = AOR_plus(AOR_multiply(cos, rik, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73079, _mut73080, _mut73081, _mut73082), AOR_multiply(sin, lmDiag[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73083, _mut73084, _mut73085, _mut73086), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73087, _mut73088, _mut73089, _mut73090);
                        lmDiag[i] = AOR_plus(AOR_multiply(-sin, rik, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73091, _mut73092, _mut73093, _mut73094), AOR_multiply(cos, lmDiag[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73095, _mut73096, _mut73097, _mut73098), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73099, _mut73100, _mut73101, _mut73102);
                        weightedJacobian[i][pk] = temp2;
                    }
                }
            }
            // the corresponding diagonal element of R
            lmDiag[j] = weightedJacobian[j][permutation[j]];
            weightedJacobian[j][permutation[j]] = lmDir[j];
        }
        // singular, then obtain a least squares solution
        int nSing = solvedCols;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73134, _mut73135, _mut73136, _mut73137, _mut73138); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
            if ((_mut73128 ? ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73118, _mut73119, _mut73120, _mut73121, _mut73122)) || (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73123, _mut73124, _mut73125, _mut73126, _mut73127))) : ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73118, _mut73119, _mut73120, _mut73121, _mut73122)) && (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73123, _mut73124, _mut73125, _mut73126, _mut73127))))) {
                nSing = j;
            }
            if (ROR_less(nSing, solvedCols, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73129, _mut73130, _mut73131, _mut73132, _mut73133)) {
                work[j] = 0;
            }
        }
        if (ROR_greater(nSing, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73139, _mut73140, _mut73141, _mut73142, _mut73143)) {
            for (int j = nSing - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73161, _mut73162, _mut73163, _mut73164, _mut73165); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
                int pj = permutation[j];
                double sum = 0;
                for (int i = j + 1; ROR_less(i, nSing, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73148, _mut73149, _mut73150, _mut73151, _mut73152); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
                    sum += AOR_multiply(weightedJacobian[i][pj], work[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73144, _mut73145, _mut73146, _mut73147);
                }
                work[j] = AOR_divide((AOR_minus(work[j], sum, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73153, _mut73154, _mut73155, _mut73156)), lmDiag[j], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73157, _mut73158, _mut73159, _mut73160);
            }
        }
        // permute the components of z back to components of lmDir
        for (int j = 0; ROR_less(j, lmDir.length, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724", _mut73166, _mut73167, _mut73168, _mut73169, _mut73170); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.determineLMDirection_724");
            lmDir[permutation[j]] = work[j];
        }
    }

    /**
     * Decompose a matrix A as A.P = Q.R using Householder transforms.
     * <p>As suggested in the P. Lascaux and R. Theodor book
     * <i>Analyse num&eacute;rique matricielle appliqu&eacute;e &agrave;
     * l'art de l'ing&eacute;nieur</i> (Masson, 1986), instead of representing
     * the Householder transforms with u<sub>k</sub> unit vectors such that:
     * <pre>
     * H<sub>k</sub> = I - 2u<sub>k</sub>.u<sub>k</sub><sup>t</sup>
     * </pre>
     * we use <sub>k</sub> non-unit vectors such that:
     * <pre>
     * H<sub>k</sub> = I - beta<sub>k</sub>v<sub>k</sub>.v<sub>k</sub><sup>t</sup>
     * </pre>
     * where v<sub>k</sub> = a<sub>k</sub> - alpha<sub>k</sub> e<sub>k</sub>.
     * The beta<sub>k</sub> coefficients are provided upon exit as recomputing
     * them from the v<sub>k</sub> vectors would be costly.</p>
     * <p>This decomposition handles rank deficient cases since the tranformations
     * are performed in non-increasing columns norms order thanks to columns
     * pivoting. The diagonal elements of the R matrix are therefore also in
     * non-increasing absolute values order.</p>
     *
     * @param jacobian Weighted Jacobian matrix at the current point.
     * @exception ConvergenceException if the decomposition cannot be performed
     */
    private void qrDecomposition(RealMatrix jacobian) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
        // hence the multiplication by -1.
        weightedJacobian = jacobian.scalarMultiply(-1).getData();
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        // initializations
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73180, _mut73181, _mut73182, _mut73183, _mut73184); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
            permutation[k] = k;
            double norm2 = 0;
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73175, _mut73176, _mut73177, _mut73178, _mut73179); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                double akk = weightedJacobian[i][k];
                norm2 += AOR_multiply(akk, akk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73171, _mut73172, _mut73173, _mut73174);
            }
            jacNorm[k] = FastMath.sqrt(norm2);
        }
        // transform the matrix column after column
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73258, _mut73259, _mut73260, _mut73261, _mut73262); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
            // select the column with the greatest norm on active components
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i = k; ROR_less(i, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73200, _mut73201, _mut73202, _mut73203, _mut73204); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                double norm2 = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73189, _mut73190, _mut73191, _mut73192, _mut73193); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                    double aki = weightedJacobian[j][permutation[i]];
                    norm2 += AOR_multiply(aki, aki, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73185, _mut73186, _mut73187, _mut73188);
                }
                if ((_mut73194 ? (Double.isInfinite(norm2) && Double.isNaN(norm2)) : (Double.isInfinite(norm2) || Double.isNaN(norm2)))) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, nR, nC);
                }
                if (ROR_greater(norm2, ak2, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73195, _mut73196, _mut73197, _mut73198, _mut73199)) {
                    nextColumn = i;
                    ak2 = norm2;
                }
            }
            if (ROR_less_equals(ak2, qrRankingThreshold, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73205, _mut73206, _mut73207, _mut73208, _mut73209)) {
                rank = k;
                return;
            }
            int pk = permutation[nextColumn];
            permutation[nextColumn] = permutation[k];
            permutation[k] = pk;
            // choose alpha such that Hk.u = alpha ek
            double akk = weightedJacobian[k][pk];
            double alpha = (ROR_greater(akk, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73210, _mut73211, _mut73212, _mut73213, _mut73214)) ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
            double betak = AOR_divide(1.0, (AOR_minus(ak2, AOR_multiply(akk, alpha, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73215, _mut73216, _mut73217, _mut73218), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73219, _mut73220, _mut73221, _mut73222)), "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73223, _mut73224, _mut73225, _mut73226);
            beta[pk] = betak;
            // transform the current column
            diagR[pk] = alpha;
            weightedJacobian[k][pk] -= alpha;
            // transform the remaining columns
            for (int dk = nC - 1 - k; ROR_greater(dk, 0, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73253, _mut73254, _mut73255, _mut73256, _mut73257); --dk) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                double gamma = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73235, _mut73236, _mut73237, _mut73238, _mut73239); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                    gamma += AOR_multiply(weightedJacobian[j][pk], weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73227, _mut73228, _mut73229, _mut73230)]], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73231, _mut73232, _mut73233, _mut73234);
                }
                gamma *= betak;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73248, _mut73249, _mut73250, _mut73251, _mut73252); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849");
                    weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73240, _mut73241, _mut73242, _mut73243)]] -= AOR_multiply(gamma, weightedJacobian[j][pk], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qrDecomposition_849", _mut73244, _mut73245, _mut73246, _mut73247);
                }
            }
        }
        rank = solvedCols;
    }

    /**
     * Compute the product Qt.y for some Q.R. decomposition.
     *
     * @param y vector to multiply (will be overwritten with the result)
     */
    private void qTy(double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927");
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927", _mut73281, _mut73282, _mut73283, _mut73284, _mut73285); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927");
            int pk = permutation[k];
            double gamma = 0;
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927", _mut73267, _mut73268, _mut73269, _mut73270, _mut73271); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927");
                gamma += AOR_multiply(weightedJacobian[i][pk], y[i], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927", _mut73263, _mut73264, _mut73265, _mut73266);
            }
            gamma *= beta[pk];
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927", _mut73276, _mut73277, _mut73278, _mut73279, _mut73280); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927");
                y[i] -= AOR_multiply(gamma, weightedJacobian[i][pk], "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer.qTy_927", _mut73272, _mut73273, _mut73274, _mut73275);
            }
        }
    }
}
