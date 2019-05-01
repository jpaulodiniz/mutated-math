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
package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class solves a least-squares problem using the Levenberg-Marquardt
 * algorithm.
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
 *
 * @since 3.3
 */
public class LevenbergMarquardtOptimizer implements LeastSquaresOptimizer {

    @Conditional
    public static boolean _mut38229 = false, _mut38230 = false, _mut38231 = false, _mut38232 = false, _mut38233 = false, _mut38234 = false, _mut38235 = false, _mut38236 = false, _mut38237 = false, _mut38238 = false, _mut38239 = false, _mut38240 = false, _mut38241 = false, _mut38242 = false, _mut38243 = false, _mut38244 = false, _mut38245 = false, _mut38246 = false, _mut38247 = false, _mut38248 = false, _mut38249 = false, _mut38250 = false, _mut38251 = false, _mut38252 = false, _mut38253 = false, _mut38254 = false, _mut38255 = false, _mut38256 = false, _mut38257 = false, _mut38258 = false, _mut38259 = false, _mut38260 = false, _mut38261 = false, _mut38262 = false, _mut38263 = false, _mut38264 = false, _mut38265 = false, _mut38266 = false, _mut38267 = false, _mut38268 = false, _mut38269 = false, _mut38270 = false, _mut38271 = false, _mut38272 = false, _mut38273 = false, _mut38274 = false, _mut38275 = false, _mut38276 = false, _mut38277 = false, _mut38278 = false, _mut38279 = false, _mut38280 = false, _mut38281 = false, _mut38282 = false, _mut38283 = false, _mut38284 = false, _mut38285 = false, _mut38286 = false, _mut38287 = false, _mut38288 = false, _mut38289 = false, _mut38290 = false, _mut38291 = false, _mut38292 = false, _mut38293 = false, _mut38294 = false, _mut38295 = false, _mut38296 = false, _mut38297 = false, _mut38298 = false, _mut38299 = false, _mut38300 = false, _mut38301 = false, _mut38302 = false, _mut38303 = false, _mut38304 = false, _mut38305 = false, _mut38306 = false, _mut38307 = false, _mut38308 = false, _mut38309 = false, _mut38310 = false, _mut38311 = false, _mut38312 = false, _mut38313 = false, _mut38314 = false, _mut38315 = false, _mut38316 = false, _mut38317 = false, _mut38318 = false, _mut38319 = false, _mut38320 = false, _mut38321 = false, _mut38322 = false, _mut38323 = false, _mut38324 = false, _mut38325 = false, _mut38326 = false, _mut38327 = false, _mut38328 = false, _mut38329 = false, _mut38330 = false, _mut38331 = false, _mut38332 = false, _mut38333 = false, _mut38334 = false, _mut38335 = false, _mut38336 = false, _mut38337 = false, _mut38338 = false, _mut38339 = false, _mut38340 = false, _mut38341 = false, _mut38342 = false, _mut38343 = false, _mut38344 = false, _mut38345 = false, _mut38346 = false, _mut38347 = false, _mut38348 = false, _mut38349 = false, _mut38350 = false, _mut38351 = false, _mut38352 = false, _mut38353 = false, _mut38354 = false, _mut38355 = false, _mut38356 = false, _mut38357 = false, _mut38358 = false, _mut38359 = false, _mut38360 = false, _mut38361 = false, _mut38362 = false, _mut38363 = false, _mut38364 = false, _mut38365 = false, _mut38366 = false, _mut38367 = false, _mut38368 = false, _mut38369 = false, _mut38370 = false, _mut38371 = false, _mut38372 = false, _mut38373 = false, _mut38374 = false, _mut38375 = false, _mut38376 = false, _mut38377 = false, _mut38378 = false, _mut38379 = false, _mut38380 = false, _mut38381 = false, _mut38382 = false, _mut38383 = false, _mut38384 = false, _mut38385 = false, _mut38386 = false, _mut38387 = false, _mut38388 = false, _mut38389 = false, _mut38390 = false, _mut38391 = false, _mut38392 = false, _mut38393 = false, _mut38394 = false, _mut38395 = false, _mut38396 = false, _mut38397 = false, _mut38398 = false, _mut38399 = false, _mut38400 = false, _mut38401 = false, _mut38402 = false, _mut38403 = false, _mut38404 = false, _mut38405 = false, _mut38406 = false, _mut38407 = false, _mut38408 = false, _mut38409 = false, _mut38410 = false, _mut38411 = false, _mut38412 = false, _mut38413 = false, _mut38414 = false, _mut38415 = false, _mut38416 = false, _mut38417 = false, _mut38418 = false, _mut38419 = false, _mut38420 = false, _mut38421 = false, _mut38422 = false, _mut38423 = false, _mut38424 = false, _mut38425 = false, _mut38426 = false, _mut38427 = false, _mut38428 = false, _mut38429 = false, _mut38430 = false, _mut38431 = false, _mut38432 = false, _mut38433 = false, _mut38434 = false, _mut38435 = false, _mut38436 = false, _mut38437 = false, _mut38438 = false, _mut38439 = false, _mut38440 = false, _mut38441 = false, _mut38442 = false, _mut38443 = false, _mut38444 = false, _mut38445 = false, _mut38446 = false, _mut38447 = false, _mut38448 = false, _mut38449 = false, _mut38450 = false, _mut38451 = false, _mut38452 = false, _mut38453 = false, _mut38454 = false, _mut38455 = false, _mut38456 = false, _mut38457 = false, _mut38458 = false, _mut38459 = false, _mut38460 = false, _mut38461 = false, _mut38462 = false, _mut38463 = false, _mut38464 = false, _mut38465 = false, _mut38466 = false, _mut38467 = false, _mut38468 = false, _mut38469 = false, _mut38470 = false, _mut38471 = false, _mut38472 = false, _mut38473 = false, _mut38474 = false, _mut38475 = false, _mut38476 = false, _mut38477 = false, _mut38478 = false, _mut38479 = false, _mut38480 = false, _mut38481 = false, _mut38482 = false, _mut38483 = false, _mut38484 = false, _mut38485 = false, _mut38486 = false, _mut38487 = false, _mut38488 = false, _mut38489 = false, _mut38490 = false, _mut38491 = false, _mut38492 = false, _mut38493 = false, _mut38494 = false, _mut38495 = false, _mut38496 = false, _mut38497 = false, _mut38498 = false, _mut38499 = false, _mut38500 = false, _mut38501 = false, _mut38502 = false, _mut38503 = false, _mut38504 = false, _mut38505 = false, _mut38506 = false, _mut38507 = false, _mut38508 = false, _mut38509 = false, _mut38510 = false, _mut38511 = false, _mut38512 = false, _mut38513 = false, _mut38514 = false, _mut38515 = false, _mut38516 = false, _mut38517 = false, _mut38518 = false, _mut38519 = false, _mut38520 = false, _mut38521 = false, _mut38522 = false, _mut38523 = false, _mut38524 = false, _mut38525 = false, _mut38526 = false, _mut38527 = false, _mut38528 = false, _mut38529 = false, _mut38530 = false, _mut38531 = false, _mut38532 = false, _mut38533 = false, _mut38534 = false, _mut38535 = false, _mut38536 = false, _mut38537 = false, _mut38538 = false, _mut38539 = false, _mut38540 = false, _mut38541 = false, _mut38542 = false, _mut38543 = false, _mut38544 = false, _mut38545 = false, _mut38546 = false, _mut38547 = false, _mut38548 = false, _mut38549 = false, _mut38550 = false, _mut38551 = false, _mut38552 = false, _mut38553 = false, _mut38554 = false, _mut38555 = false, _mut38556 = false, _mut38557 = false, _mut38558 = false, _mut38559 = false, _mut38560 = false, _mut38561 = false, _mut38562 = false, _mut38563 = false, _mut38564 = false, _mut38565 = false, _mut38566 = false, _mut38567 = false, _mut38568 = false, _mut38569 = false, _mut38570 = false, _mut38571 = false, _mut38572 = false, _mut38573 = false, _mut38574 = false, _mut38575 = false, _mut38576 = false, _mut38577 = false, _mut38578 = false, _mut38579 = false, _mut38580 = false, _mut38581 = false, _mut38582 = false, _mut38583 = false, _mut38584 = false, _mut38585 = false, _mut38586 = false, _mut38587 = false, _mut38588 = false, _mut38589 = false, _mut38590 = false, _mut38591 = false, _mut38592 = false, _mut38593 = false, _mut38594 = false, _mut38595 = false, _mut38596 = false, _mut38597 = false, _mut38598 = false, _mut38599 = false, _mut38600 = false, _mut38601 = false, _mut38602 = false, _mut38603 = false, _mut38604 = false, _mut38605 = false, _mut38606 = false, _mut38607 = false, _mut38608 = false, _mut38609 = false, _mut38610 = false, _mut38611 = false, _mut38612 = false, _mut38613 = false, _mut38614 = false, _mut38615 = false, _mut38616 = false, _mut38617 = false, _mut38618 = false, _mut38619 = false, _mut38620 = false, _mut38621 = false, _mut38622 = false, _mut38623 = false, _mut38624 = false, _mut38625 = false, _mut38626 = false, _mut38627 = false, _mut38628 = false, _mut38629 = false, _mut38630 = false, _mut38631 = false, _mut38632 = false, _mut38633 = false, _mut38634 = false, _mut38635 = false, _mut38636 = false, _mut38637 = false, _mut38638 = false, _mut38639 = false, _mut38640 = false, _mut38641 = false, _mut38642 = false, _mut38643 = false, _mut38644 = false, _mut38645 = false, _mut38646 = false, _mut38647 = false, _mut38648 = false, _mut38649 = false, _mut38650 = false, _mut38651 = false, _mut38652 = false, _mut38653 = false, _mut38654 = false, _mut38655 = false, _mut38656 = false, _mut38657 = false, _mut38658 = false, _mut38659 = false, _mut38660 = false, _mut38661 = false, _mut38662 = false, _mut38663 = false, _mut38664 = false, _mut38665 = false, _mut38666 = false, _mut38667 = false, _mut38668 = false, _mut38669 = false, _mut38670 = false, _mut38671 = false, _mut38672 = false, _mut38673 = false, _mut38674 = false, _mut38675 = false, _mut38676 = false, _mut38677 = false, _mut38678 = false, _mut38679 = false, _mut38680 = false, _mut38681 = false, _mut38682 = false, _mut38683 = false, _mut38684 = false, _mut38685 = false, _mut38686 = false, _mut38687 = false, _mut38688 = false, _mut38689 = false, _mut38690 = false, _mut38691 = false, _mut38692 = false, _mut38693 = false, _mut38694 = false, _mut38695 = false, _mut38696 = false, _mut38697 = false, _mut38698 = false, _mut38699 = false, _mut38700 = false, _mut38701 = false, _mut38702 = false, _mut38703 = false, _mut38704 = false, _mut38705 = false, _mut38706 = false, _mut38707 = false, _mut38708 = false, _mut38709 = false, _mut38710 = false, _mut38711 = false, _mut38712 = false, _mut38713 = false, _mut38714 = false, _mut38715 = false, _mut38716 = false, _mut38717 = false, _mut38718 = false, _mut38719 = false, _mut38720 = false, _mut38721 = false, _mut38722 = false, _mut38723 = false, _mut38724 = false, _mut38725 = false, _mut38726 = false, _mut38727 = false, _mut38728 = false, _mut38729 = false, _mut38730 = false, _mut38731 = false, _mut38732 = false, _mut38733 = false, _mut38734 = false, _mut38735 = false, _mut38736 = false, _mut38737 = false, _mut38738 = false, _mut38739 = false, _mut38740 = false, _mut38741 = false, _mut38742 = false, _mut38743 = false, _mut38744 = false, _mut38745 = false, _mut38746 = false, _mut38747 = false, _mut38748 = false, _mut38749 = false, _mut38750 = false, _mut38751 = false, _mut38752 = false, _mut38753 = false, _mut38754 = false, _mut38755 = false, _mut38756 = false, _mut38757 = false, _mut38758 = false, _mut38759 = false, _mut38760 = false, _mut38761 = false, _mut38762 = false, _mut38763 = false, _mut38764 = false, _mut38765 = false, _mut38766 = false, _mut38767 = false, _mut38768 = false, _mut38769 = false, _mut38770 = false, _mut38771 = false, _mut38772 = false, _mut38773 = false, _mut38774 = false, _mut38775 = false, _mut38776 = false, _mut38777 = false, _mut38778 = false, _mut38779 = false, _mut38780 = false, _mut38781 = false, _mut38782 = false, _mut38783 = false, _mut38784 = false, _mut38785 = false, _mut38786 = false, _mut38787 = false, _mut38788 = false, _mut38789 = false, _mut38790 = false, _mut38791 = false, _mut38792 = false, _mut38793 = false, _mut38794 = false, _mut38795 = false, _mut38796 = false, _mut38797 = false, _mut38798 = false, _mut38799 = false, _mut38800 = false, _mut38801 = false, _mut38802 = false, _mut38803 = false, _mut38804 = false, _mut38805 = false, _mut38806 = false, _mut38807 = false, _mut38808 = false, _mut38809 = false, _mut38810 = false, _mut38811 = false, _mut38812 = false, _mut38813 = false, _mut38814 = false, _mut38815 = false, _mut38816 = false, _mut38817 = false, _mut38818 = false, _mut38819 = false, _mut38820 = false, _mut38821 = false, _mut38822 = false, _mut38823 = false, _mut38824 = false, _mut38825 = false, _mut38826 = false, _mut38827 = false, _mut38828 = false, _mut38829 = false, _mut38830 = false, _mut38831 = false, _mut38832 = false, _mut38833 = false, _mut38834 = false, _mut38835 = false, _mut38836 = false, _mut38837 = false, _mut38838 = false, _mut38839 = false, _mut38840 = false, _mut38841 = false, _mut38842 = false, _mut38843 = false, _mut38844 = false, _mut38845 = false, _mut38846 = false, _mut38847 = false, _mut38848 = false, _mut38849 = false, _mut38850 = false, _mut38851 = false, _mut38852 = false, _mut38853 = false, _mut38854 = false, _mut38855 = false, _mut38856 = false, _mut38857 = false, _mut38858 = false, _mut38859 = false, _mut38860 = false, _mut38861 = false, _mut38862 = false, _mut38863 = false, _mut38864 = false, _mut38865 = false, _mut38866 = false, _mut38867 = false, _mut38868 = false, _mut38869 = false, _mut38870 = false, _mut38871 = false, _mut38872 = false, _mut38873 = false, _mut38874 = false, _mut38875 = false, _mut38876 = false, _mut38877 = false, _mut38878 = false, _mut38879 = false, _mut38880 = false, _mut38881 = false, _mut38882 = false, _mut38883 = false, _mut38884 = false, _mut38885 = false, _mut38886 = false, _mut38887 = false, _mut38888 = false, _mut38889 = false, _mut38890 = false, _mut38891 = false, _mut38892 = false, _mut38893 = false, _mut38894 = false, _mut38895 = false, _mut38896 = false, _mut38897 = false, _mut38898 = false, _mut38899 = false, _mut38900 = false, _mut38901 = false, _mut38902 = false, _mut38903 = false, _mut38904 = false, _mut38905 = false, _mut38906 = false, _mut38907 = false, _mut38908 = false, _mut38909 = false, _mut38910 = false, _mut38911 = false, _mut38912 = false, _mut38913 = false, _mut38914 = false, _mut38915 = false, _mut38916 = false, _mut38917 = false, _mut38918 = false, _mut38919 = false, _mut38920 = false, _mut38921 = false, _mut38922 = false, _mut38923 = false, _mut38924 = false, _mut38925 = false, _mut38926 = false, _mut38927 = false, _mut38928 = false, _mut38929 = false, _mut38930 = false, _mut38931 = false, _mut38932 = false, _mut38933 = false, _mut38934 = false, _mut38935 = false, _mut38936 = false, _mut38937 = false, _mut38938 = false, _mut38939 = false, _mut38940 = false, _mut38941 = false, _mut38942 = false, _mut38943 = false, _mut38944 = false, _mut38945 = false, _mut38946 = false, _mut38947 = false, _mut38948 = false, _mut38949 = false, _mut38950 = false, _mut38951 = false, _mut38952 = false, _mut38953 = false, _mut38954 = false, _mut38955 = false, _mut38956 = false, _mut38957 = false, _mut38958 = false, _mut38959 = false, _mut38960 = false, _mut38961 = false, _mut38962 = false, _mut38963 = false, _mut38964 = false, _mut38965 = false, _mut38966 = false, _mut38967 = false, _mut38968 = false, _mut38969 = false, _mut38970 = false, _mut38971 = false, _mut38972 = false, _mut38973 = false, _mut38974 = false, _mut38975 = false, _mut38976 = false, _mut38977 = false, _mut38978 = false, _mut38979 = false, _mut38980 = false, _mut38981 = false, _mut38982 = false, _mut38983 = false, _mut38984 = false, _mut38985 = false, _mut38986 = false, _mut38987 = false, _mut38988 = false, _mut38989 = false, _mut38990 = false, _mut38991 = false, _mut38992 = false, _mut38993 = false, _mut38994 = false, _mut38995 = false, _mut38996 = false, _mut38997 = false, _mut38998 = false, _mut38999 = false, _mut39000 = false, _mut39001 = false, _mut39002 = false, _mut39003 = false, _mut39004 = false, _mut39005 = false, _mut39006 = false, _mut39007 = false, _mut39008 = false, _mut39009 = false, _mut39010 = false, _mut39011 = false, _mut39012 = false, _mut39013 = false, _mut39014 = false, _mut39015 = false, _mut39016 = false, _mut39017 = false, _mut39018 = false, _mut39019 = false, _mut39020 = false, _mut39021 = false, _mut39022 = false, _mut39023 = false, _mut39024 = false, _mut39025 = false, _mut39026 = false, _mut39027 = false, _mut39028 = false, _mut39029 = false, _mut39030 = false, _mut39031 = false, _mut39032 = false, _mut39033 = false, _mut39034 = false, _mut39035 = false, _mut39036 = false, _mut39037 = false, _mut39038 = false, _mut39039 = false, _mut39040 = false, _mut39041 = false, _mut39042 = false, _mut39043 = false, _mut39044 = false, _mut39045 = false, _mut39046 = false, _mut39047 = false, _mut39048 = false, _mut39049 = false, _mut39050 = false, _mut39051 = false, _mut39052 = false, _mut39053 = false, _mut39054 = false, _mut39055 = false, _mut39056 = false, _mut39057 = false, _mut39058 = false, _mut39059 = false, _mut39060 = false, _mut39061 = false, _mut39062 = false, _mut39063 = false, _mut39064 = false, _mut39065 = false, _mut39066 = false, _mut39067 = false, _mut39068 = false, _mut39069 = false, _mut39070 = false, _mut39071 = false, _mut39072 = false, _mut39073 = false, _mut39074 = false, _mut39075 = false, _mut39076 = false, _mut39077 = false, _mut39078 = false, _mut39079 = false, _mut39080 = false, _mut39081 = false, _mut39082 = false, _mut39083 = false, _mut39084 = false, _mut39085 = false, _mut39086 = false, _mut39087 = false, _mut39088 = false, _mut39089 = false, _mut39090 = false, _mut39091 = false, _mut39092 = false, _mut39093 = false, _mut39094 = false, _mut39095 = false, _mut39096 = false, _mut39097 = false, _mut39098 = false, _mut39099 = false, _mut39100 = false, _mut39101 = false, _mut39102 = false, _mut39103 = false, _mut39104 = false, _mut39105 = false, _mut39106 = false, _mut39107 = false, _mut39108 = false, _mut39109 = false, _mut39110 = false, _mut39111 = false, _mut39112 = false, _mut39113 = false, _mut39114 = false, _mut39115 = false, _mut39116 = false, _mut39117 = false, _mut39118 = false, _mut39119 = false, _mut39120 = false, _mut39121 = false, _mut39122 = false, _mut39123 = false, _mut39124 = false, _mut39125 = false, _mut39126 = false, _mut39127 = false, _mut39128 = false, _mut39129 = false, _mut39130 = false, _mut39131 = false, _mut39132 = false, _mut39133 = false, _mut39134 = false, _mut39135 = false, _mut39136 = false, _mut39137 = false, _mut39138 = false, _mut39139 = false, _mut39140 = false, _mut39141 = false, _mut39142 = false, _mut39143 = false, _mut39144 = false;

    /**
     * Twice the "epsilon machine".
     */
    private static final double TWO_EPS = AOR_multiply(2, Precision.EPSILON, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.getPoint_94", _mut38229, _mut38230, _mut38231, _mut38232);

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
     * Default constructor.
     * <p>
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
     * Construct an instance with all parameters specified.
     *
     * @param initialStepBoundFactor initial step bound factor
     * @param costRelativeTolerance  cost relative tolerance
     * @param parRelativeTolerance   parameters relative tolerance
     * @param orthoTolerance         orthogonality tolerance
     * @param qrRankingThreshold     threshold in the QR decomposition. Columns with a 2
     *                               norm less than this threshold are considered to be
     *                               all 0s.
     */
    public LevenbergMarquardtOptimizer(final double initialStepBoundFactor, final double costRelativeTolerance, final double parRelativeTolerance, final double orthoTolerance, final double qrRankingThreshold) {
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = qrRankingThreshold;
    }

    /**
     * @param newInitialStepBoundFactor Positive input variable used in
     * determining the initial step bound. This bound is set to the
     * product of initialStepBoundFactor and the euclidean norm of
     * {@code diag * x} if non-zero, or else to {@code newInitialStepBoundFactor}
     * itself. In most cases factor should lie in the interval
     * {@code (0.1, 100.0)}. {@code 100} is a generally recommended value.
     * of the matrix is reduced.
     * @return a new instance.
     */
    public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double newInitialStepBoundFactor) {
        return new LevenbergMarquardtOptimizer(newInitialStepBoundFactor, costRelativeTolerance, parRelativeTolerance, orthoTolerance, qrRankingThreshold);
    }

    /**
     * @param newCostRelativeTolerance Desired relative error in the sum of squares.
     * @return a new instance.
     */
    public LevenbergMarquardtOptimizer withCostRelativeTolerance(double newCostRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(initialStepBoundFactor, newCostRelativeTolerance, parRelativeTolerance, orthoTolerance, qrRankingThreshold);
    }

    /**
     * @param newParRelativeTolerance Desired relative error in the approximate solution
     * parameters.
     * @return a new instance.
     */
    public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double newParRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(initialStepBoundFactor, costRelativeTolerance, newParRelativeTolerance, orthoTolerance, qrRankingThreshold);
    }

    /**
     * Modifies the given parameter.
     *
     * @param newOrthoTolerance Desired max cosine on the orthogonality between
     * the function vector and the columns of the Jacobian.
     * @return a new instance.
     */
    public LevenbergMarquardtOptimizer withOrthoTolerance(double newOrthoTolerance) {
        return new LevenbergMarquardtOptimizer(initialStepBoundFactor, costRelativeTolerance, parRelativeTolerance, newOrthoTolerance, qrRankingThreshold);
    }

    /**
     * @param newQRRankingThreshold Desired threshold for QR ranking.
     * If the squared norm of a column vector is smaller or equal to this
     * threshold during QR decomposition, it is considered to be a zero vector
     * and hence the rank of the matrix is reduced.
     * @return a new instance.
     */
    public LevenbergMarquardtOptimizer withRankingThreshold(double newQRRankingThreshold) {
        return new LevenbergMarquardtOptimizer(initialStepBoundFactor, costRelativeTolerance, parRelativeTolerance, orthoTolerance, newQRRankingThreshold);
    }

    /**
     * Gets the value of a tuning parameter.
     * @see #withInitialStepBoundFactor(double)
     *
     * @return the parameter's value.
     */
    public double getInitialStepBoundFactor() {
        return initialStepBoundFactor;
    }

    /**
     * Gets the value of a tuning parameter.
     * @see #withCostRelativeTolerance(double)
     *
     * @return the parameter's value.
     */
    public double getCostRelativeTolerance() {
        return costRelativeTolerance;
    }

    /**
     * Gets the value of a tuning parameter.
     * @see #withParameterRelativeTolerance(double)
     *
     * @return the parameter's value.
     */
    public double getParameterRelativeTolerance() {
        return parRelativeTolerance;
    }

    /**
     * Gets the value of a tuning parameter.
     * @see #withOrthoTolerance(double)
     *
     * @return the parameter's value.
     */
    public double getOrthoTolerance() {
        return orthoTolerance;
    }

    /**
     * Gets the value of a tuning parameter.
     * @see #withRankingThreshold(double)
     *
     * @return the parameter's value.
     */
    public double getRankingThreshold() {
        return qrRankingThreshold;
    }

    /**
     * {@inheritDoc}
     */
    public Optimum optimize(final LeastSquaresProblem problem) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
        // Number of observed data.
        final int nR = problem.getObservationSize();
        // Number of parameters.
        final int nC = problem.getParameterSize();
        // Counters.
        final Incrementor iterationCounter = problem.getIterationCounter();
        final Incrementor evaluationCounter = problem.getEvaluationCounter();
        // Convergence criterion.
        final ConvergenceChecker<Evaluation> checker = problem.getConvergenceChecker();
        // arrays shared with the other private methods
        final int solvedCols = FastMath.min(nR, nC);
        /* Parameters evolution direction associated with lmPar. */
        double[] lmDir = new double[nC];
        /* Levenberg-Marquardt parameter. */
        double lmPar = 0;
        // local point
        double delta = 0;
        double xNorm = 0;
        double[] diag = new double[nC];
        double[] oldX = new double[nC];
        double[] oldRes = new double[nR];
        double[] qtf = new double[nR];
        double[] work1 = new double[nC];
        double[] work2 = new double[nC];
        double[] work3 = new double[nC];
        // Evaluate the function at the starting point and calculate its norm.
        evaluationCounter.incrementCount();
        // value will be reassigned in the loop
        Evaluation current = problem.evaluate(problem.getStart());
        double[] currentResiduals = current.getResiduals().toArray();
        double currentCost = current.getCost();
        double[] currentPoint = current.getPoint().toArray();
        // Outer loop.
        boolean firstIteration = true;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
            iterationCounter.incrementCount();
            final Evaluation previous = current;
            // QR decomposition of the jacobian matrix
            final InternalData internalData = qrDecomposition(current.getJacobian(), solvedCols);
            final double[][] weightedJacobian = internalData.weightedJacobian;
            final int[] permutation = internalData.permutation;
            final double[] diagR = internalData.diagR;
            final double[] jacNorm = internalData.jacNorm;
            // residuals already have weights applied
            double[] weightedResidual = currentResiduals;
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38233, _mut38234, _mut38235, _mut38236, _mut38237); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                qtf[i] = weightedResidual[i];
            }
            // compute Qt.res
            qTy(qtf, internalData);
            // so let jacobian contain the R matrix with its diagonal elements
            for (int k = 0; ROR_less(k, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38238, _mut38239, _mut38240, _mut38241, _mut38242); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                int pk = permutation[k];
                weightedJacobian[k][pk] = diagR[pk];
            }
            if (firstIteration) {
                // of the initial jacobian
                xNorm = 0;
                for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38256, _mut38257, _mut38258, _mut38259, _mut38260); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    double dk = jacNorm[k];
                    if (ROR_equals(dk, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38243, _mut38244, _mut38245, _mut38246, _mut38247)) {
                        dk = 1.0;
                    }
                    double xk = AOR_multiply(dk, currentPoint[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38248, _mut38249, _mut38250, _mut38251);
                    xNorm += AOR_multiply(xk, xk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38252, _mut38253, _mut38254, _mut38255);
                    diag[k] = dk;
                }
                xNorm = FastMath.sqrt(xNorm);
                // initialize the step bound delta
                delta = (ROR_equals(xNorm, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38261, _mut38262, _mut38263, _mut38264, _mut38265)) ? initialStepBoundFactor : (AOR_multiply(initialStepBoundFactor, xNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38266, _mut38267, _mut38268, _mut38269));
            }
            // check orthogonality between function vector and jacobian columns
            double maxCosine = 0;
            if (ROR_not_equals(currentCost, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38270, _mut38271, _mut38272, _mut38273, _mut38274)) {
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38297, _mut38298, _mut38299, _mut38300, _mut38301); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    int pj = permutation[j];
                    double s = jacNorm[pj];
                    if (ROR_not_equals(s, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38275, _mut38276, _mut38277, _mut38278, _mut38279)) {
                        double sum = 0;
                        for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38284, _mut38285, _mut38286, _mut38287, _mut38288); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                            sum += AOR_multiply(weightedJacobian[i][pj], qtf[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38280, _mut38281, _mut38282, _mut38283);
                        }
                        maxCosine = FastMath.max(maxCosine, AOR_divide(FastMath.abs(sum), (AOR_multiply(s, currentCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38289, _mut38290, _mut38291, _mut38292)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38293, _mut38294, _mut38295, _mut38296));
                    }
                }
            }
            if (ROR_less_equals(maxCosine, orthoTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38302, _mut38303, _mut38304, _mut38305, _mut38306)) {
                // Convergence has been reached.
                return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
            }
            // rescale if necessary
            for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38307, _mut38308, _mut38309, _mut38310, _mut38311); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                diag[j] = FastMath.max(diag[j], jacNorm[j]);
            }
            // Inner loop.
            for (double ratio = 0; ROR_less(ratio, 1.0e-4, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38561, _mut38562, _mut38563, _mut38564, _mut38565); ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                // save the state
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38312, _mut38313, _mut38314, _mut38315, _mut38316); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    int pj = permutation[j];
                    oldX[pj] = currentPoint[pj];
                }
                final double previousCost = currentCost;
                double[] tmpVec = weightedResidual;
                weightedResidual = oldRes;
                oldRes = tmpVec;
                // determine the Levenberg-Marquardt parameter
                lmPar = determineLMParameter(qtf, delta, diag, internalData, solvedCols, work1, work2, work3, lmDir, lmPar);
                // compute the new point and the norm of the evolution direction
                double lmNorm = 0;
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38329, _mut38330, _mut38331, _mut38332, _mut38333); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    int pj = permutation[j];
                    lmDir[pj] = -lmDir[pj];
                    currentPoint[pj] = AOR_plus(oldX[pj], lmDir[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38317, _mut38318, _mut38319, _mut38320);
                    double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38321, _mut38322, _mut38323, _mut38324);
                    lmNorm += AOR_multiply(s, s, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38325, _mut38326, _mut38327, _mut38328);
                }
                lmNorm = FastMath.sqrt(lmNorm);
                // on the first iteration, adjust the initial step bound.
                if (firstIteration) {
                    delta = FastMath.min(delta, lmNorm);
                }
                // Evaluate the function at x + p and calculate its norm.
                evaluationCounter.incrementCount();
                current = problem.evaluate(new ArrayRealVector(currentPoint));
                currentResiduals = current.getResiduals().toArray();
                currentCost = current.getCost();
                currentPoint = current.getPoint().toArray();
                // compute the scaled actual reduction
                double actRed = -1.0;
                if (ROR_less(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38334, _mut38335, _mut38336, _mut38337), previousCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38338, _mut38339, _mut38340, _mut38341, _mut38342)) {
                    double r = AOR_divide(currentCost, previousCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38343, _mut38344, _mut38345, _mut38346);
                    actRed = AOR_minus(1.0, AOR_multiply(r, r, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38347, _mut38348, _mut38349, _mut38350), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38351, _mut38352, _mut38353, _mut38354);
                }
                // and the scaled directional derivative
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38364, _mut38365, _mut38366, _mut38367, _mut38368); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    int pj = permutation[j];
                    double dirJ = lmDir[pj];
                    work1[j] = 0;
                    for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38359, _mut38360, _mut38361, _mut38362, _mut38363); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                        work1[i] += AOR_multiply(weightedJacobian[i][pj], dirJ, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38355, _mut38356, _mut38357, _mut38358);
                    }
                }
                double coeff1 = 0;
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38373, _mut38374, _mut38375, _mut38376, _mut38377); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                    coeff1 += AOR_multiply(work1[j], work1[j], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38369, _mut38370, _mut38371, _mut38372);
                }
                double pc2 = AOR_multiply(previousCost, previousCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38378, _mut38379, _mut38380, _mut38381);
                coeff1 /= pc2;
                double coeff2 = AOR_divide(AOR_multiply(AOR_multiply(lmPar, lmNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38382, _mut38383, _mut38384, _mut38385), lmNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38386, _mut38387, _mut38388, _mut38389), pc2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38390, _mut38391, _mut38392, _mut38393);
                double preRed = AOR_plus(coeff1, AOR_multiply(2, coeff2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38394, _mut38395, _mut38396, _mut38397), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38398, _mut38399, _mut38400, _mut38401);
                double dirDer = -(AOR_plus(coeff1, coeff2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38402, _mut38403, _mut38404, _mut38405));
                // ratio of the actual to the predicted reduction
                ratio = (ROR_equals(preRed, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38406, _mut38407, _mut38408, _mut38409, _mut38410)) ? 0 : (AOR_divide(actRed, preRed, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38411, _mut38412, _mut38413, _mut38414));
                // update the step bound
                if (ROR_less_equals(ratio, 0.25, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38415, _mut38416, _mut38417, _mut38418, _mut38419)) {
                    double tmp = (ROR_less(actRed, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38435, _mut38436, _mut38437, _mut38438, _mut38439)) ? (AOR_divide(AOR_multiply(0.5, dirDer, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38440, _mut38441, _mut38442, _mut38443), (AOR_plus(dirDer, AOR_multiply(0.5, actRed, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38444, _mut38445, _mut38446, _mut38447), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38448, _mut38449, _mut38450, _mut38451)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38452, _mut38453, _mut38454, _mut38455)) : 0.5;
                    if ((_mut38470 ? ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38456, _mut38457, _mut38458, _mut38459), previousCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38460, _mut38461, _mut38462, _mut38463, _mut38464)) && (ROR_less(tmp, 0.1, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38465, _mut38466, _mut38467, _mut38468, _mut38469))) : ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38456, _mut38457, _mut38458, _mut38459), previousCost, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38460, _mut38461, _mut38462, _mut38463, _mut38464)) || (ROR_less(tmp, 0.1, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38465, _mut38466, _mut38467, _mut38468, _mut38469))))) {
                        tmp = 0.1;
                    }
                    delta = AOR_multiply(tmp, FastMath.min(delta, AOR_multiply(10.0, lmNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38471, _mut38472, _mut38473, _mut38474)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38475, _mut38476, _mut38477, _mut38478);
                    lmPar /= tmp;
                } else if ((_mut38430 ? ((ROR_equals(lmPar, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38420, _mut38421, _mut38422, _mut38423, _mut38424)) && (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38425, _mut38426, _mut38427, _mut38428, _mut38429))) : ((ROR_equals(lmPar, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38420, _mut38421, _mut38422, _mut38423, _mut38424)) || (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38425, _mut38426, _mut38427, _mut38428, _mut38429))))) {
                    delta = AOR_multiply(2, lmNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38431, _mut38432, _mut38433, _mut38434);
                    lmPar *= 0.5;
                }
                // test for successful iteration.
                if (ROR_greater_equals(ratio, 1.0e-4, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38479, _mut38480, _mut38481, _mut38482, _mut38483)) {
                    // successful iteration, update the norm
                    firstIteration = false;
                    xNorm = 0;
                    for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38497, _mut38498, _mut38499, _mut38500, _mut38501); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                        double xK = AOR_multiply(diag[k], currentPoint[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38489, _mut38490, _mut38491, _mut38492);
                        xNorm += AOR_multiply(xK, xK, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38493, _mut38494, _mut38495, _mut38496);
                    }
                    xNorm = FastMath.sqrt(xNorm);
                    // tests for convergence.
                    if ((_mut38502 ? (checker != null || checker.converged(iterationCounter.getCount(), previous, current)) : (checker != null && checker.converged(iterationCounter.getCount(), previous, current)))) {
                        return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
                    }
                } else {
                    // failed iteration, reset the previous values
                    currentCost = previousCost;
                    for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38484, _mut38485, _mut38486, _mut38487, _mut38488); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296");
                        int pj = permutation[j];
                        currentPoint[pj] = oldX[pj];
                    }
                    tmpVec = weightedResidual;
                    weightedResidual = oldRes;
                    oldRes = tmpVec;
                    // Reset "current" to previous values.
                    current = previous;
                }
                // Default convergence criteria.
                if ((_mut38529 ? (((_mut38519 ? ((_mut38513 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38514, _mut38515, _mut38516, _mut38517, _mut38518)) : ((_mut38513 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38514, _mut38515, _mut38516, _mut38517, _mut38518)))) && ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38520, _mut38521, _mut38522, _mut38523), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38524, _mut38525, _mut38526, _mut38527, _mut38528)) : (((_mut38519 ? ((_mut38513 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38514, _mut38515, _mut38516, _mut38517, _mut38518)) : ((_mut38513 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38503, _mut38504, _mut38505, _mut38506, _mut38507) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38508, _mut38509, _mut38510, _mut38511, _mut38512))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38514, _mut38515, _mut38516, _mut38517, _mut38518)))) || ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38520, _mut38521, _mut38522, _mut38523), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38524, _mut38525, _mut38526, _mut38527, _mut38528)))) {
                    return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
                }
                // tests for termination and stringent tolerances
                if ((_mut38546 ? ((_mut38540 ? (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38530, _mut38531, _mut38532, _mut38533, _mut38534) || ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38535, _mut38536, _mut38537, _mut38538, _mut38539)) : (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38530, _mut38531, _mut38532, _mut38533, _mut38534) && ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38535, _mut38536, _mut38537, _mut38538, _mut38539))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38541, _mut38542, _mut38543, _mut38544, _mut38545)) : ((_mut38540 ? (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38530, _mut38531, _mut38532, _mut38533, _mut38534) || ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38535, _mut38536, _mut38537, _mut38538, _mut38539)) : (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38530, _mut38531, _mut38532, _mut38533, _mut38534) && ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38535, _mut38536, _mut38537, _mut38538, _mut38539))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38541, _mut38542, _mut38543, _mut38544, _mut38545)))) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, costRelativeTolerance);
                } else if (ROR_less_equals(delta, AOR_multiply(TWO_EPS, xNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38547, _mut38548, _mut38549, _mut38550), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38551, _mut38552, _mut38553, _mut38554, _mut38555)) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, parRelativeTolerance);
                } else if (ROR_less_equals(maxCosine, TWO_EPS, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize_296", _mut38556, _mut38557, _mut38558, _mut38559, _mut38560)) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, orthoTolerance);
                }
            }
        }
    }

    /**
     * Holds internal data.
     * This structure was created so that all optimizer fields can be "final".
     * Code should be further refactored in order to not pass around arguments
     * that will modified in-place (cf. "work" arrays).
     */
    private static class InternalData {

        /**
         * Weighted Jacobian.
         */
        private final double[][] weightedJacobian;

        /**
         * Columns permutation array.
         */
        private final int[] permutation;

        /**
         * Rank of the Jacobian matrix.
         */
        private final int rank;

        /**
         * Diagonal elements of the R matrix in the QR decomposition.
         */
        private final double[] diagR;

        /**
         * Norms of the columns of the jacobian matrix.
         */
        private final double[] jacNorm;

        /**
         * Coefficients of the Householder transforms vectors.
         */
        private final double[] beta;

        /**
         * @param weightedJacobian Weighted Jacobian.
         * @param permutation Columns permutation array.
         * @param rank Rank of the Jacobian matrix.
         * @param diagR Diagonal elements of the R matrix in the QR decomposition.
         * @param jacNorm Norms of the columns of the jacobian matrix.
         * @param beta Coefficients of the Householder transforms vectors.
         */
        InternalData(double[][] weightedJacobian, int[] permutation, int rank, double[] diagR, double[] jacNorm, double[] beta) {
            this.weightedJacobian = weightedJacobian;
            this.permutation = permutation;
            this.rank = rank;
            this.diagR = diagR;
            this.jacNorm = jacNorm;
            this.beta = beta;
        }
    }

    /**
     * Determines the Levenberg-Marquardt parameter.
     *
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
     * @param qy Array containing qTy.
     * @param delta Upper bound on the euclidean norm of diagR * lmDir.
     * @param diag Diagonal matrix.
     * @param internalData Data (modified in-place in this method).
     * @param solvedCols Number of solved point.
     * @param work1 work array
     * @param work2 work array
     * @param work3 work array
     * @param lmDir the "returned" LM direction will be stored in this array.
     * @param lmPar the value of the LM parameter from the previous iteration.
     * @return the new LM parameter
     */
    private double determineLMParameter(double[] qy, double delta, double[] diag, InternalData internalData, int solvedCols, double[] work1, double[] work2, double[] work3, double[] lmDir, double lmPar) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
        final double[][] weightedJacobian = internalData.weightedJacobian;
        final int[] permutation = internalData.permutation;
        final int rank = internalData.rank;
        final double[] diagR = internalData.diagR;
        final int nC = weightedJacobian[0].length;
        // jacobian is rank-deficient, obtain a least squares solution
        for (int j = 0; ROR_less(j, rank, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38566, _mut38567, _mut38568, _mut38569, _mut38570); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            lmDir[permutation[j]] = qy[j];
        }
        for (int j = rank; ROR_less(j, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38571, _mut38572, _mut38573, _mut38574, _mut38575); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            lmDir[permutation[j]] = 0;
        }
        for (int k = rank - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38589, _mut38590, _mut38591, _mut38592, _mut38593); --k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            int pk = permutation[k];
            double ypk = AOR_divide(lmDir[pk], diagR[pk], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38576, _mut38577, _mut38578, _mut38579);
            for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38584, _mut38585, _mut38586, _mut38587, _mut38588); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                lmDir[permutation[i]] -= AOR_multiply(ypk, weightedJacobian[i][pk], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38580, _mut38581, _mut38582, _mut38583);
            }
            lmDir[pk] = ypk;
        }
        // for acceptance of the Gauss-Newton direction
        double dxNorm = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38602, _mut38603, _mut38604, _mut38605, _mut38606); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            int pj = permutation[j];
            double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38594, _mut38595, _mut38596, _mut38597);
            work1[pj] = s;
            dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38598, _mut38599, _mut38600, _mut38601);
        }
        dxNorm = FastMath.sqrt(dxNorm);
        double fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38607, _mut38608, _mut38609, _mut38610);
        if (ROR_less_equals(fp, AOR_multiply(0.1, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38611, _mut38612, _mut38613, _mut38614), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38615, _mut38616, _mut38617, _mut38618, _mut38619)) {
            lmPar = 0;
            return lmPar;
        }
        // otherwise set this bound to zero
        double sum2;
        double parl = 0;
        if (ROR_equals(rank, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38620, _mut38621, _mut38622, _mut38623, _mut38624)) {
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38629, _mut38630, _mut38631, _mut38632, _mut38633); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                work1[pj] *= AOR_divide(diag[pj], dxNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38625, _mut38626, _mut38627, _mut38628);
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38655, _mut38656, _mut38657, _mut38658, _mut38659); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                double sum = 0;
                for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38638, _mut38639, _mut38640, _mut38641, _mut38642); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                    sum += AOR_multiply(weightedJacobian[i][pj], work1[permutation[i]], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38634, _mut38635, _mut38636, _mut38637);
                }
                double s = AOR_divide((AOR_minus(work1[pj], sum, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38643, _mut38644, _mut38645, _mut38646)), diagR[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38647, _mut38648, _mut38649, _mut38650);
                work1[pj] = s;
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38651, _mut38652, _mut38653, _mut38654);
            }
            parl = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38660, _mut38661, _mut38662, _mut38663)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38664, _mut38665, _mut38666, _mut38667);
        }
        // calculate an upper bound, paru, for the zero of the function
        sum2 = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38681, _mut38682, _mut38683, _mut38684, _mut38685); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            int pj = permutation[j];
            double sum = 0;
            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38672, _mut38673, _mut38674, _mut38675, _mut38676); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                sum += AOR_multiply(weightedJacobian[i][pj], qy[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38668, _mut38669, _mut38670, _mut38671);
            }
            sum /= diag[pj];
            sum2 += AOR_multiply(sum, sum, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38677, _mut38678, _mut38679, _mut38680);
        }
        double gNorm = FastMath.sqrt(sum2);
        double paru = AOR_divide(gNorm, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38686, _mut38687, _mut38688, _mut38689);
        if (ROR_equals(paru, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38690, _mut38691, _mut38692, _mut38693, _mut38694)) {
            paru = AOR_divide(Precision.SAFE_MIN, FastMath.min(delta, 0.1), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38695, _mut38696, _mut38697, _mut38698);
        }
        // set par to the closer endpoint
        lmPar = FastMath.min(paru, FastMath.max(lmPar, parl));
        if (ROR_equals(lmPar, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38699, _mut38700, _mut38701, _mut38702, _mut38703)) {
            lmPar = AOR_divide(gNorm, dxNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38704, _mut38705, _mut38706, _mut38707);
        }
        for (int countdown = 10; ROR_greater_equals(countdown, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38828, _mut38829, _mut38830, _mut38831, _mut38832); --countdown) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
            // evaluate the function at the current value of lmPar
            if (ROR_equals(lmPar, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38708, _mut38709, _mut38710, _mut38711, _mut38712)) {
                lmPar = FastMath.max(Precision.SAFE_MIN, AOR_multiply(0.001, paru, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38713, _mut38714, _mut38715, _mut38716));
            }
            double sPar = FastMath.sqrt(lmPar);
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38721, _mut38722, _mut38723, _mut38724, _mut38725); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                work1[pj] = AOR_multiply(sPar, diag[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38717, _mut38718, _mut38719, _mut38720);
            }
            determineLMDirection(qy, work1, work2, internalData, solvedCols, work3, lmDir);
            dxNorm = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38734, _mut38735, _mut38736, _mut38737, _mut38738); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38726, _mut38727, _mut38728, _mut38729);
                work3[pj] = s;
                dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38730, _mut38731, _mut38732, _mut38733);
            }
            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38739, _mut38740, _mut38741, _mut38742);
            // of lmPar, also test for the exceptional cases where parl is zero
            if ((_mut38769 ? (ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38743, _mut38744, _mut38745, _mut38746), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38747, _mut38748, _mut38749, _mut38750, _mut38751) && ((_mut38768 ? ((_mut38762 ? (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) || ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761)) : (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) && ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761))) || ROR_less(previousFP, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38763, _mut38764, _mut38765, _mut38766, _mut38767)) : ((_mut38762 ? (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) || ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761)) : (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) && ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761))) && ROR_less(previousFP, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38763, _mut38764, _mut38765, _mut38766, _mut38767))))) : (ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38743, _mut38744, _mut38745, _mut38746), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38747, _mut38748, _mut38749, _mut38750, _mut38751) || ((_mut38768 ? ((_mut38762 ? (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) || ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761)) : (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) && ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761))) || ROR_less(previousFP, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38763, _mut38764, _mut38765, _mut38766, _mut38767)) : ((_mut38762 ? (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) || ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761)) : (ROR_equals(parl, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38752, _mut38753, _mut38754, _mut38755, _mut38756) && ROR_less_equals(fp, previousFP, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38757, _mut38758, _mut38759, _mut38760, _mut38761))) && ROR_less(previousFP, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38763, _mut38764, _mut38765, _mut38766, _mut38767))))))) {
                return lmPar;
            }
            // compute the Newton correction
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38778, _mut38779, _mut38780, _mut38781, _mut38782); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                work1[pj] = AOR_divide(AOR_multiply(work3[pj], diag[pj], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38770, _mut38771, _mut38772, _mut38773), dxNorm, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38774, _mut38775, _mut38776, _mut38777);
            }
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38792, _mut38793, _mut38794, _mut38795, _mut38796); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                int pj = permutation[j];
                work1[pj] /= work2[j];
                double tmp = work1[pj];
                for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38787, _mut38788, _mut38789, _mut38790, _mut38791); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                    work1[permutation[i]] -= AOR_multiply(weightedJacobian[i][pj], tmp, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38783, _mut38784, _mut38785, _mut38786);
                }
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38801, _mut38802, _mut38803, _mut38804, _mut38805); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620");
                double s = work1[permutation[j]];
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38797, _mut38798, _mut38799, _mut38800);
            }
            double correction = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38806, _mut38807, _mut38808, _mut38809)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38810, _mut38811, _mut38812, _mut38813);
            // depending on the sign of the function, update parl or paru.
            if (ROR_greater(fp, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38814, _mut38815, _mut38816, _mut38817, _mut38818)) {
                parl = FastMath.max(parl, lmPar);
            } else if (ROR_less(fp, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38819, _mut38820, _mut38821, _mut38822, _mut38823)) {
                paru = FastMath.min(paru, lmPar);
            }
            // compute an improved estimate for lmPar
            lmPar = FastMath.max(parl, AOR_plus(lmPar, correction, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMParameter_620", _mut38824, _mut38825, _mut38826, _mut38827));
        }
        return lmPar;
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
     * @param internalData Data (modified in-place in this method).
     * @param solvedCols Number of sloved point.
     * @param work work array
     * @param lmDir the "returned" LM direction is stored in this array
     */
    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, InternalData internalData, int solvedCols, double[] work, double[] lmDir) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
        final int[] permutation = internalData.permutation;
        final double[][] weightedJacobian = internalData.weightedJacobian;
        final double[] diagR = internalData.diagR;
        // in particular, save the diagonal elements of R in lmDir
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38838, _mut38839, _mut38840, _mut38841, _mut38842); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
            int pj = permutation[j];
            for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38833, _mut38834, _mut38835, _mut38836, _mut38837); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
                weightedJacobian[i][pj] = weightedJacobian[j][permutation[i]];
            }
            lmDir[j] = diagR[pj];
            work[j] = qy[j];
        }
        // eliminate the diagonal matrix d using a Givens rotation
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38972, _mut38973, _mut38974, _mut38975, _mut38976); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
            // diagonal element using p from the Q.R. factorization
            int pj = permutation[j];
            double dpj = diag[pj];
            if (ROR_not_equals(dpj, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38843, _mut38844, _mut38845, _mut38846, _mut38847)) {
                Arrays.fill(lmDiag, AOR_plus(j, 1, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38848, _mut38849, _mut38850, _mut38851), lmDiag.length, 0);
            }
            lmDiag[j] = dpj;
            // beyond the first n, which is initially zero.
            double qtbpj = 0;
            for (int k = j; ROR_less(k, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38967, _mut38968, _mut38969, _mut38970, _mut38971); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
                int pk = permutation[k];
                // appropriate element in the current row of d
                if (ROR_not_equals(lmDiag[k], 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38852, _mut38853, _mut38854, _mut38855, _mut38856)) {
                    final double sin;
                    final double cos;
                    double rkk = weightedJacobian[k][pk];
                    if (ROR_less(FastMath.abs(rkk), FastMath.abs(lmDiag[k]), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38857, _mut38858, _mut38859, _mut38860, _mut38861)) {
                        final double cotan = AOR_divide(rkk, lmDiag[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38882, _mut38883, _mut38884, _mut38885);
                        sin = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(cotan, cotan, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38886, _mut38887, _mut38888, _mut38889), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38890, _mut38891, _mut38892, _mut38893)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38894, _mut38895, _mut38896, _mut38897);
                        cos = AOR_multiply(sin, cotan, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38898, _mut38899, _mut38900, _mut38901);
                    } else {
                        final double tan = AOR_divide(lmDiag[k], rkk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38862, _mut38863, _mut38864, _mut38865);
                        cos = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(tan, tan, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38866, _mut38867, _mut38868, _mut38869), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38870, _mut38871, _mut38872, _mut38873)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38874, _mut38875, _mut38876, _mut38877);
                        sin = AOR_multiply(cos, tan, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38878, _mut38879, _mut38880, _mut38881);
                    }
                    // the modified element of (Qty,0)
                    weightedJacobian[k][pk] = AOR_plus(AOR_multiply(cos, rkk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38902, _mut38903, _mut38904, _mut38905), AOR_multiply(sin, lmDiag[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38906, _mut38907, _mut38908, _mut38909), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38910, _mut38911, _mut38912, _mut38913);
                    final double temp = AOR_plus(AOR_multiply(cos, work[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38914, _mut38915, _mut38916, _mut38917), AOR_multiply(sin, qtbpj, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38918, _mut38919, _mut38920, _mut38921), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38922, _mut38923, _mut38924, _mut38925);
                    qtbpj = AOR_plus(AOR_multiply(-sin, work[k], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38926, _mut38927, _mut38928, _mut38929), AOR_multiply(cos, qtbpj, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38930, _mut38931, _mut38932, _mut38933), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38934, _mut38935, _mut38936, _mut38937);
                    work[k] = temp;
                    // accumulate the tranformation in the row of s
                    for (int i = k + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38962, _mut38963, _mut38964, _mut38965, _mut38966); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
                        double rik = weightedJacobian[i][pk];
                        final double temp2 = AOR_plus(AOR_multiply(cos, rik, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38938, _mut38939, _mut38940, _mut38941), AOR_multiply(sin, lmDiag[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38942, _mut38943, _mut38944, _mut38945), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38946, _mut38947, _mut38948, _mut38949);
                        lmDiag[i] = AOR_plus(AOR_multiply(-sin, rik, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38950, _mut38951, _mut38952, _mut38953), AOR_multiply(cos, lmDiag[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38954, _mut38955, _mut38956, _mut38957), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38958, _mut38959, _mut38960, _mut38961);
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
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38993, _mut38994, _mut38995, _mut38996, _mut38997); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
            if ((_mut38987 ? ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38977, _mut38978, _mut38979, _mut38980, _mut38981)) || (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38982, _mut38983, _mut38984, _mut38985, _mut38986))) : ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38977, _mut38978, _mut38979, _mut38980, _mut38981)) && (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38982, _mut38983, _mut38984, _mut38985, _mut38986))))) {
                nSing = j;
            }
            if (ROR_less(nSing, solvedCols, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38988, _mut38989, _mut38990, _mut38991, _mut38992)) {
                work[j] = 0;
            }
        }
        if (ROR_greater(nSing, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut38998, _mut38999, _mut39000, _mut39001, _mut39002)) {
            for (int j = nSing - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39020, _mut39021, _mut39022, _mut39023, _mut39024); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
                int pj = permutation[j];
                double sum = 0;
                for (int i = j + 1; ROR_less(i, nSing, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39007, _mut39008, _mut39009, _mut39010, _mut39011); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
                    sum += AOR_multiply(weightedJacobian[i][pj], work[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39003, _mut39004, _mut39005, _mut39006);
                }
                work[j] = AOR_divide((AOR_minus(work[j], sum, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39012, _mut39013, _mut39014, _mut39015)), lmDiag[j], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39016, _mut39017, _mut39018, _mut39019);
            }
        }
        // permute the components of z back to components of lmDir
        for (int j = 0; ROR_less(j, lmDir.length, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802", _mut39025, _mut39026, _mut39027, _mut39028, _mut39029); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.determineLMDirection_802");
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
     * @param solvedCols Number of solved point.
     * @return data used in other methods of this class.
     * @throws ConvergenceException if the decomposition cannot be performed.
     */
    private InternalData qrDecomposition(RealMatrix jacobian, int solvedCols) throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
        // hence the multiplication by -1.
        final double[][] weightedJacobian = jacobian.scalarMultiply(-1).getData();
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        final int[] permutation = new int[nC];
        final double[] diagR = new double[nC];
        final double[] jacNorm = new double[nC];
        final double[] beta = new double[nC];
        // initializations
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39039, _mut39040, _mut39041, _mut39042, _mut39043); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
            permutation[k] = k;
            double norm2 = 0;
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39034, _mut39035, _mut39036, _mut39037, _mut39038); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                double akk = weightedJacobian[i][k];
                norm2 += AOR_multiply(akk, akk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39030, _mut39031, _mut39032, _mut39033);
            }
            jacNorm[k] = FastMath.sqrt(norm2);
        }
        // transform the matrix column after column
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39117, _mut39118, _mut39119, _mut39120, _mut39121); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
            // select the column with the greatest norm on active components
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i = k; ROR_less(i, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39059, _mut39060, _mut39061, _mut39062, _mut39063); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                double norm2 = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39048, _mut39049, _mut39050, _mut39051, _mut39052); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                    double aki = weightedJacobian[j][permutation[i]];
                    norm2 += AOR_multiply(aki, aki, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39044, _mut39045, _mut39046, _mut39047);
                }
                if ((_mut39053 ? (Double.isInfinite(norm2) && Double.isNaN(norm2)) : (Double.isInfinite(norm2) || Double.isNaN(norm2)))) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, nR, nC);
                }
                if (ROR_greater(norm2, ak2, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39054, _mut39055, _mut39056, _mut39057, _mut39058)) {
                    nextColumn = i;
                    ak2 = norm2;
                }
            }
            if (ROR_less_equals(ak2, qrRankingThreshold, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39064, _mut39065, _mut39066, _mut39067, _mut39068)) {
                return new InternalData(weightedJacobian, permutation, k, diagR, jacNorm, beta);
            }
            int pk = permutation[nextColumn];
            permutation[nextColumn] = permutation[k];
            permutation[k] = pk;
            // choose alpha such that Hk.u = alpha ek
            double akk = weightedJacobian[k][pk];
            double alpha = (ROR_greater(akk, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39069, _mut39070, _mut39071, _mut39072, _mut39073)) ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
            double betak = AOR_divide(1.0, (AOR_minus(ak2, AOR_multiply(akk, alpha, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39074, _mut39075, _mut39076, _mut39077), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39078, _mut39079, _mut39080, _mut39081)), "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39082, _mut39083, _mut39084, _mut39085);
            beta[pk] = betak;
            // transform the current column
            diagR[pk] = alpha;
            weightedJacobian[k][pk] -= alpha;
            // transform the remaining columns
            for (int dk = nC - 1 - k; ROR_greater(dk, 0, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39112, _mut39113, _mut39114, _mut39115, _mut39116); --dk) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                double gamma = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39094, _mut39095, _mut39096, _mut39097, _mut39098); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                    gamma += AOR_multiply(weightedJacobian[j][pk], weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39086, _mut39087, _mut39088, _mut39089)]], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39090, _mut39091, _mut39092, _mut39093);
                }
                gamma *= betak;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39107, _mut39108, _mut39109, _mut39110, _mut39111); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936");
                    weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39099, _mut39100, _mut39101, _mut39102)]] -= AOR_multiply(gamma, weightedJacobian[j][pk], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qrDecomposition_936", _mut39103, _mut39104, _mut39105, _mut39106);
                }
            }
        }
        return new InternalData(weightedJacobian, permutation, solvedCols, diagR, jacNorm, beta);
    }

    /**
     * Compute the product Qt.y for some Q.R. decomposition.
     *
     * @param y vector to multiply (will be overwritten with the result)
     * @param internalData Data.
     */
    private void qTy(double[] y, InternalData internalData) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021");
        final double[][] weightedJacobian = internalData.weightedJacobian;
        final int[] permutation = internalData.permutation;
        final double[] beta = internalData.beta;
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021", _mut39140, _mut39141, _mut39142, _mut39143, _mut39144); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021");
            int pk = permutation[k];
            double gamma = 0;
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021", _mut39126, _mut39127, _mut39128, _mut39129, _mut39130); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021");
                gamma += AOR_multiply(weightedJacobian[i][pk], y[i], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021", _mut39122, _mut39123, _mut39124, _mut39125);
            }
            gamma *= beta[pk];
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021", _mut39135, _mut39136, _mut39137, _mut39138, _mut39139); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021");
                y[i] -= AOR_multiply(gamma, weightedJacobian[i][pk], "org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.qTy_1021", _mut39131, _mut39132, _mut39133, _mut39134);
            }
        }
    }
}
