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
package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class solves a least-squares problem using the Levenberg-Marquardt
 * algorithm.
 * <br/>
 * Constraints are not supported: the call to
 * {@link #optimize(OptimizationData[]) optimize} will throw
 * {@link MathUnsupportedOperationException} if bounds are passed to it.
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
 * @since 2.0
 * @deprecated All classes and interfaces in this package are deprecated.
 * The optimizers that were provided here were moved to the
 * {@link org.apache.commons.math3.fitting.leastsquares} package
 * (cf. MATH-1008).
 */
@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {

    @Conditional
    public static boolean _mut65805 = false, _mut65806 = false, _mut65807 = false, _mut65808 = false, _mut65809 = false, _mut65810 = false, _mut65811 = false, _mut65812 = false, _mut65813 = false, _mut65814 = false, _mut65815 = false, _mut65816 = false, _mut65817 = false, _mut65818 = false, _mut65819 = false, _mut65820 = false, _mut65821 = false, _mut65822 = false, _mut65823 = false, _mut65824 = false, _mut65825 = false, _mut65826 = false, _mut65827 = false, _mut65828 = false, _mut65829 = false, _mut65830 = false, _mut65831 = false, _mut65832 = false, _mut65833 = false, _mut65834 = false, _mut65835 = false, _mut65836 = false, _mut65837 = false, _mut65838 = false, _mut65839 = false, _mut65840 = false, _mut65841 = false, _mut65842 = false, _mut65843 = false, _mut65844 = false, _mut65845 = false, _mut65846 = false, _mut65847 = false, _mut65848 = false, _mut65849 = false, _mut65850 = false, _mut65851 = false, _mut65852 = false, _mut65853 = false, _mut65854 = false, _mut65855 = false, _mut65856 = false, _mut65857 = false, _mut65858 = false, _mut65859 = false, _mut65860 = false, _mut65861 = false, _mut65862 = false, _mut65863 = false, _mut65864 = false, _mut65865 = false, _mut65866 = false, _mut65867 = false, _mut65868 = false, _mut65869 = false, _mut65870 = false, _mut65871 = false, _mut65872 = false, _mut65873 = false, _mut65874 = false, _mut65875 = false, _mut65876 = false, _mut65877 = false, _mut65878 = false, _mut65879 = false, _mut65880 = false, _mut65881 = false, _mut65882 = false, _mut65883 = false, _mut65884 = false, _mut65885 = false, _mut65886 = false, _mut65887 = false, _mut65888 = false, _mut65889 = false, _mut65890 = false, _mut65891 = false, _mut65892 = false, _mut65893 = false, _mut65894 = false, _mut65895 = false, _mut65896 = false, _mut65897 = false, _mut65898 = false, _mut65899 = false, _mut65900 = false, _mut65901 = false, _mut65902 = false, _mut65903 = false, _mut65904 = false, _mut65905 = false, _mut65906 = false, _mut65907 = false, _mut65908 = false, _mut65909 = false, _mut65910 = false, _mut65911 = false, _mut65912 = false, _mut65913 = false, _mut65914 = false, _mut65915 = false, _mut65916 = false, _mut65917 = false, _mut65918 = false, _mut65919 = false, _mut65920 = false, _mut65921 = false, _mut65922 = false, _mut65923 = false, _mut65924 = false, _mut65925 = false, _mut65926 = false, _mut65927 = false, _mut65928 = false, _mut65929 = false, _mut65930 = false, _mut65931 = false, _mut65932 = false, _mut65933 = false, _mut65934 = false, _mut65935 = false, _mut65936 = false, _mut65937 = false, _mut65938 = false, _mut65939 = false, _mut65940 = false, _mut65941 = false, _mut65942 = false, _mut65943 = false, _mut65944 = false, _mut65945 = false, _mut65946 = false, _mut65947 = false, _mut65948 = false, _mut65949 = false, _mut65950 = false, _mut65951 = false, _mut65952 = false, _mut65953 = false, _mut65954 = false, _mut65955 = false, _mut65956 = false, _mut65957 = false, _mut65958 = false, _mut65959 = false, _mut65960 = false, _mut65961 = false, _mut65962 = false, _mut65963 = false, _mut65964 = false, _mut65965 = false, _mut65966 = false, _mut65967 = false, _mut65968 = false, _mut65969 = false, _mut65970 = false, _mut65971 = false, _mut65972 = false, _mut65973 = false, _mut65974 = false, _mut65975 = false, _mut65976 = false, _mut65977 = false, _mut65978 = false, _mut65979 = false, _mut65980 = false, _mut65981 = false, _mut65982 = false, _mut65983 = false, _mut65984 = false, _mut65985 = false, _mut65986 = false, _mut65987 = false, _mut65988 = false, _mut65989 = false, _mut65990 = false, _mut65991 = false, _mut65992 = false, _mut65993 = false, _mut65994 = false, _mut65995 = false, _mut65996 = false, _mut65997 = false, _mut65998 = false, _mut65999 = false, _mut66000 = false, _mut66001 = false, _mut66002 = false, _mut66003 = false, _mut66004 = false, _mut66005 = false, _mut66006 = false, _mut66007 = false, _mut66008 = false, _mut66009 = false, _mut66010 = false, _mut66011 = false, _mut66012 = false, _mut66013 = false, _mut66014 = false, _mut66015 = false, _mut66016 = false, _mut66017 = false, _mut66018 = false, _mut66019 = false, _mut66020 = false, _mut66021 = false, _mut66022 = false, _mut66023 = false, _mut66024 = false, _mut66025 = false, _mut66026 = false, _mut66027 = false, _mut66028 = false, _mut66029 = false, _mut66030 = false, _mut66031 = false, _mut66032 = false, _mut66033 = false, _mut66034 = false, _mut66035 = false, _mut66036 = false, _mut66037 = false, _mut66038 = false, _mut66039 = false, _mut66040 = false, _mut66041 = false, _mut66042 = false, _mut66043 = false, _mut66044 = false, _mut66045 = false, _mut66046 = false, _mut66047 = false, _mut66048 = false, _mut66049 = false, _mut66050 = false, _mut66051 = false, _mut66052 = false, _mut66053 = false, _mut66054 = false, _mut66055 = false, _mut66056 = false, _mut66057 = false, _mut66058 = false, _mut66059 = false, _mut66060 = false, _mut66061 = false, _mut66062 = false, _mut66063 = false, _mut66064 = false, _mut66065 = false, _mut66066 = false, _mut66067 = false, _mut66068 = false, _mut66069 = false, _mut66070 = false, _mut66071 = false, _mut66072 = false, _mut66073 = false, _mut66074 = false, _mut66075 = false, _mut66076 = false, _mut66077 = false, _mut66078 = false, _mut66079 = false, _mut66080 = false, _mut66081 = false, _mut66082 = false, _mut66083 = false, _mut66084 = false, _mut66085 = false, _mut66086 = false, _mut66087 = false, _mut66088 = false, _mut66089 = false, _mut66090 = false, _mut66091 = false, _mut66092 = false, _mut66093 = false, _mut66094 = false, _mut66095 = false, _mut66096 = false, _mut66097 = false, _mut66098 = false, _mut66099 = false, _mut66100 = false, _mut66101 = false, _mut66102 = false, _mut66103 = false, _mut66104 = false, _mut66105 = false, _mut66106 = false, _mut66107 = false, _mut66108 = false, _mut66109 = false, _mut66110 = false, _mut66111 = false, _mut66112 = false, _mut66113 = false, _mut66114 = false, _mut66115 = false, _mut66116 = false, _mut66117 = false, _mut66118 = false, _mut66119 = false, _mut66120 = false, _mut66121 = false, _mut66122 = false, _mut66123 = false, _mut66124 = false, _mut66125 = false, _mut66126 = false, _mut66127 = false, _mut66128 = false, _mut66129 = false, _mut66130 = false, _mut66131 = false, _mut66132 = false, _mut66133 = false, _mut66134 = false, _mut66135 = false, _mut66136 = false, _mut66137 = false, _mut66138 = false, _mut66139 = false, _mut66140 = false, _mut66141 = false, _mut66142 = false, _mut66143 = false, _mut66144 = false, _mut66145 = false, _mut66146 = false, _mut66147 = false, _mut66148 = false, _mut66149 = false, _mut66150 = false, _mut66151 = false, _mut66152 = false, _mut66153 = false, _mut66154 = false, _mut66155 = false, _mut66156 = false, _mut66157 = false, _mut66158 = false, _mut66159 = false, _mut66160 = false, _mut66161 = false, _mut66162 = false, _mut66163 = false, _mut66164 = false, _mut66165 = false, _mut66166 = false, _mut66167 = false, _mut66168 = false, _mut66169 = false, _mut66170 = false, _mut66171 = false, _mut66172 = false, _mut66173 = false, _mut66174 = false, _mut66175 = false, _mut66176 = false, _mut66177 = false, _mut66178 = false, _mut66179 = false, _mut66180 = false, _mut66181 = false, _mut66182 = false, _mut66183 = false, _mut66184 = false, _mut66185 = false, _mut66186 = false, _mut66187 = false, _mut66188 = false, _mut66189 = false, _mut66190 = false, _mut66191 = false, _mut66192 = false, _mut66193 = false, _mut66194 = false, _mut66195 = false, _mut66196 = false, _mut66197 = false, _mut66198 = false, _mut66199 = false, _mut66200 = false, _mut66201 = false, _mut66202 = false, _mut66203 = false, _mut66204 = false, _mut66205 = false, _mut66206 = false, _mut66207 = false, _mut66208 = false, _mut66209 = false, _mut66210 = false, _mut66211 = false, _mut66212 = false, _mut66213 = false, _mut66214 = false, _mut66215 = false, _mut66216 = false, _mut66217 = false, _mut66218 = false, _mut66219 = false, _mut66220 = false, _mut66221 = false, _mut66222 = false, _mut66223 = false, _mut66224 = false, _mut66225 = false, _mut66226 = false, _mut66227 = false, _mut66228 = false, _mut66229 = false, _mut66230 = false, _mut66231 = false, _mut66232 = false, _mut66233 = false, _mut66234 = false, _mut66235 = false, _mut66236 = false, _mut66237 = false, _mut66238 = false, _mut66239 = false, _mut66240 = false, _mut66241 = false, _mut66242 = false, _mut66243 = false, _mut66244 = false, _mut66245 = false, _mut66246 = false, _mut66247 = false, _mut66248 = false, _mut66249 = false, _mut66250 = false, _mut66251 = false, _mut66252 = false, _mut66253 = false, _mut66254 = false, _mut66255 = false, _mut66256 = false, _mut66257 = false, _mut66258 = false, _mut66259 = false, _mut66260 = false, _mut66261 = false, _mut66262 = false, _mut66263 = false, _mut66264 = false, _mut66265 = false, _mut66266 = false, _mut66267 = false, _mut66268 = false, _mut66269 = false, _mut66270 = false, _mut66271 = false, _mut66272 = false, _mut66273 = false, _mut66274 = false, _mut66275 = false, _mut66276 = false, _mut66277 = false, _mut66278 = false, _mut66279 = false, _mut66280 = false, _mut66281 = false, _mut66282 = false, _mut66283 = false, _mut66284 = false, _mut66285 = false, _mut66286 = false, _mut66287 = false, _mut66288 = false, _mut66289 = false, _mut66290 = false, _mut66291 = false, _mut66292 = false, _mut66293 = false, _mut66294 = false, _mut66295 = false, _mut66296 = false, _mut66297 = false, _mut66298 = false, _mut66299 = false, _mut66300 = false, _mut66301 = false, _mut66302 = false, _mut66303 = false, _mut66304 = false, _mut66305 = false, _mut66306 = false, _mut66307 = false, _mut66308 = false, _mut66309 = false, _mut66310 = false, _mut66311 = false, _mut66312 = false, _mut66313 = false, _mut66314 = false, _mut66315 = false, _mut66316 = false, _mut66317 = false, _mut66318 = false, _mut66319 = false, _mut66320 = false, _mut66321 = false, _mut66322 = false, _mut66323 = false, _mut66324 = false, _mut66325 = false, _mut66326 = false, _mut66327 = false, _mut66328 = false, _mut66329 = false, _mut66330 = false, _mut66331 = false, _mut66332 = false, _mut66333 = false, _mut66334 = false, _mut66335 = false, _mut66336 = false, _mut66337 = false, _mut66338 = false, _mut66339 = false, _mut66340 = false, _mut66341 = false, _mut66342 = false, _mut66343 = false, _mut66344 = false, _mut66345 = false, _mut66346 = false, _mut66347 = false, _mut66348 = false, _mut66349 = false, _mut66350 = false, _mut66351 = false, _mut66352 = false, _mut66353 = false, _mut66354 = false, _mut66355 = false, _mut66356 = false, _mut66357 = false, _mut66358 = false, _mut66359 = false, _mut66360 = false, _mut66361 = false, _mut66362 = false, _mut66363 = false, _mut66364 = false, _mut66365 = false, _mut66366 = false, _mut66367 = false, _mut66368 = false, _mut66369 = false, _mut66370 = false, _mut66371 = false, _mut66372 = false, _mut66373 = false, _mut66374 = false, _mut66375 = false, _mut66376 = false, _mut66377 = false, _mut66378 = false, _mut66379 = false, _mut66380 = false, _mut66381 = false, _mut66382 = false, _mut66383 = false, _mut66384 = false, _mut66385 = false, _mut66386 = false, _mut66387 = false, _mut66388 = false, _mut66389 = false, _mut66390 = false, _mut66391 = false, _mut66392 = false, _mut66393 = false, _mut66394 = false, _mut66395 = false, _mut66396 = false, _mut66397 = false, _mut66398 = false, _mut66399 = false, _mut66400 = false, _mut66401 = false, _mut66402 = false, _mut66403 = false, _mut66404 = false, _mut66405 = false, _mut66406 = false, _mut66407 = false, _mut66408 = false, _mut66409 = false, _mut66410 = false, _mut66411 = false, _mut66412 = false, _mut66413 = false, _mut66414 = false, _mut66415 = false, _mut66416 = false, _mut66417 = false, _mut66418 = false, _mut66419 = false, _mut66420 = false, _mut66421 = false, _mut66422 = false, _mut66423 = false, _mut66424 = false, _mut66425 = false, _mut66426 = false, _mut66427 = false, _mut66428 = false, _mut66429 = false, _mut66430 = false, _mut66431 = false, _mut66432 = false, _mut66433 = false, _mut66434 = false, _mut66435 = false, _mut66436 = false, _mut66437 = false, _mut66438 = false, _mut66439 = false, _mut66440 = false, _mut66441 = false, _mut66442 = false, _mut66443 = false, _mut66444 = false, _mut66445 = false, _mut66446 = false, _mut66447 = false, _mut66448 = false, _mut66449 = false, _mut66450 = false, _mut66451 = false, _mut66452 = false, _mut66453 = false, _mut66454 = false, _mut66455 = false, _mut66456 = false, _mut66457 = false, _mut66458 = false, _mut66459 = false, _mut66460 = false, _mut66461 = false, _mut66462 = false, _mut66463 = false, _mut66464 = false, _mut66465 = false, _mut66466 = false, _mut66467 = false, _mut66468 = false, _mut66469 = false, _mut66470 = false, _mut66471 = false, _mut66472 = false, _mut66473 = false, _mut66474 = false, _mut66475 = false, _mut66476 = false, _mut66477 = false, _mut66478 = false, _mut66479 = false, _mut66480 = false, _mut66481 = false, _mut66482 = false, _mut66483 = false, _mut66484 = false, _mut66485 = false, _mut66486 = false, _mut66487 = false, _mut66488 = false, _mut66489 = false, _mut66490 = false, _mut66491 = false, _mut66492 = false, _mut66493 = false, _mut66494 = false, _mut66495 = false, _mut66496 = false, _mut66497 = false, _mut66498 = false, _mut66499 = false, _mut66500 = false, _mut66501 = false, _mut66502 = false, _mut66503 = false, _mut66504 = false, _mut66505 = false, _mut66506 = false, _mut66507 = false, _mut66508 = false, _mut66509 = false, _mut66510 = false, _mut66511 = false, _mut66512 = false, _mut66513 = false, _mut66514 = false, _mut66515 = false, _mut66516 = false, _mut66517 = false, _mut66518 = false, _mut66519 = false, _mut66520 = false, _mut66521 = false, _mut66522 = false, _mut66523 = false, _mut66524 = false, _mut66525 = false, _mut66526 = false, _mut66527 = false, _mut66528 = false, _mut66529 = false, _mut66530 = false, _mut66531 = false, _mut66532 = false, _mut66533 = false, _mut66534 = false, _mut66535 = false, _mut66536 = false, _mut66537 = false, _mut66538 = false, _mut66539 = false, _mut66540 = false, _mut66541 = false, _mut66542 = false, _mut66543 = false, _mut66544 = false, _mut66545 = false, _mut66546 = false, _mut66547 = false, _mut66548 = false, _mut66549 = false, _mut66550 = false, _mut66551 = false, _mut66552 = false, _mut66553 = false, _mut66554 = false, _mut66555 = false, _mut66556 = false, _mut66557 = false, _mut66558 = false, _mut66559 = false, _mut66560 = false, _mut66561 = false, _mut66562 = false, _mut66563 = false, _mut66564 = false, _mut66565 = false, _mut66566 = false, _mut66567 = false, _mut66568 = false, _mut66569 = false, _mut66570 = false, _mut66571 = false, _mut66572 = false, _mut66573 = false, _mut66574 = false, _mut66575 = false, _mut66576 = false, _mut66577 = false, _mut66578 = false, _mut66579 = false, _mut66580 = false, _mut66581 = false, _mut66582 = false, _mut66583 = false, _mut66584 = false, _mut66585 = false, _mut66586 = false, _mut66587 = false, _mut66588 = false, _mut66589 = false, _mut66590 = false, _mut66591 = false, _mut66592 = false, _mut66593 = false, _mut66594 = false, _mut66595 = false, _mut66596 = false, _mut66597 = false, _mut66598 = false, _mut66599 = false, _mut66600 = false, _mut66601 = false, _mut66602 = false, _mut66603 = false, _mut66604 = false, _mut66605 = false, _mut66606 = false, _mut66607 = false, _mut66608 = false, _mut66609 = false, _mut66610 = false, _mut66611 = false, _mut66612 = false, _mut66613 = false, _mut66614 = false, _mut66615 = false, _mut66616 = false, _mut66617 = false, _mut66618 = false, _mut66619 = false, _mut66620 = false, _mut66621 = false, _mut66622 = false, _mut66623 = false, _mut66624 = false, _mut66625 = false, _mut66626 = false, _mut66627 = false, _mut66628 = false, _mut66629 = false, _mut66630 = false, _mut66631 = false, _mut66632 = false, _mut66633 = false, _mut66634 = false, _mut66635 = false, _mut66636 = false, _mut66637 = false, _mut66638 = false, _mut66639 = false, _mut66640 = false, _mut66641 = false, _mut66642 = false, _mut66643 = false, _mut66644 = false, _mut66645 = false, _mut66646 = false, _mut66647 = false, _mut66648 = false, _mut66649 = false, _mut66650 = false, _mut66651 = false, _mut66652 = false, _mut66653 = false, _mut66654 = false, _mut66655 = false, _mut66656 = false, _mut66657 = false, _mut66658 = false, _mut66659 = false, _mut66660 = false, _mut66661 = false, _mut66662 = false, _mut66663 = false, _mut66664 = false, _mut66665 = false, _mut66666 = false, _mut66667 = false, _mut66668 = false, _mut66669 = false, _mut66670 = false, _mut66671 = false, _mut66672 = false, _mut66673 = false, _mut66674 = false, _mut66675 = false, _mut66676 = false, _mut66677 = false, _mut66678 = false, _mut66679 = false, _mut66680 = false, _mut66681 = false, _mut66682 = false, _mut66683 = false, _mut66684 = false, _mut66685 = false, _mut66686 = false, _mut66687 = false, _mut66688 = false, _mut66689 = false, _mut66690 = false, _mut66691 = false, _mut66692 = false, _mut66693 = false, _mut66694 = false, _mut66695 = false, _mut66696 = false, _mut66697 = false, _mut66698 = false, _mut66699 = false, _mut66700 = false, _mut66701 = false, _mut66702 = false, _mut66703 = false, _mut66704 = false, _mut66705 = false, _mut66706 = false, _mut66707 = false, _mut66708 = false, _mut66709 = false, _mut66710 = false, _mut66711 = false, _mut66712 = false, _mut66713 = false, _mut66714 = false, _mut66715 = false, _mut66716 = false, _mut66717 = false, _mut66718 = false, _mut66719 = false, _mut66720 = false, _mut66721 = false;

    /**
     * Twice the "epsilon machine".
     */
    private static final double TWO_EPS = AOR_multiply(2, Precision.EPSILON, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.squareRoot_268", _mut65805, _mut65806, _mut65807, _mut65808);

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
        checkParameters();
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
        final ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
            incrementIterationCount();
            final PointVectorValuePair previous = current;
            // QR decomposition of the jacobian matrix
            qrDecomposition(computeWeightedJacobian(currentPoint));
            weightedResidual = weightMatrixSqrt.operate(currentResiduals);
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65809, _mut65810, _mut65811, _mut65812, _mut65813); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                qtf[i] = weightedResidual[i];
            }
            // compute Qt.res
            qTy(qtf);
            // so let jacobian contain the R matrix with its diagonal elements
            for (int k = 0; ROR_less(k, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65814, _mut65815, _mut65816, _mut65817, _mut65818); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                int pk = permutation[k];
                weightedJacobian[k][pk] = diagR[pk];
            }
            if (firstIteration) {
                // of the initial jacobian
                xNorm = 0;
                for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65832, _mut65833, _mut65834, _mut65835, _mut65836); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                    double dk = jacNorm[k];
                    if (ROR_equals(dk, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65819, _mut65820, _mut65821, _mut65822, _mut65823)) {
                        dk = 1.0;
                    }
                    double xk = AOR_multiply(dk, currentPoint[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65824, _mut65825, _mut65826, _mut65827);
                    xNorm += AOR_multiply(xk, xk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65828, _mut65829, _mut65830, _mut65831);
                    diag[k] = dk;
                }
                xNorm = FastMath.sqrt(xNorm);
                // initialize the step bound delta
                delta = (ROR_equals(xNorm, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65837, _mut65838, _mut65839, _mut65840, _mut65841)) ? initialStepBoundFactor : (AOR_multiply(initialStepBoundFactor, xNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65842, _mut65843, _mut65844, _mut65845));
            }
            // check orthogonality between function vector and jacobian columns
            double maxCosine = 0;
            if (ROR_not_equals(currentCost, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65846, _mut65847, _mut65848, _mut65849, _mut65850)) {
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65873, _mut65874, _mut65875, _mut65876, _mut65877); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                    int pj = permutation[j];
                    double s = jacNorm[pj];
                    if (ROR_not_equals(s, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65851, _mut65852, _mut65853, _mut65854, _mut65855)) {
                        double sum = 0;
                        for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65860, _mut65861, _mut65862, _mut65863, _mut65864); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                            sum += AOR_multiply(weightedJacobian[i][pj], qtf[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65856, _mut65857, _mut65858, _mut65859);
                        }
                        maxCosine = FastMath.max(maxCosine, AOR_divide(FastMath.abs(sum), (AOR_multiply(s, currentCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65865, _mut65866, _mut65867, _mut65868)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65869, _mut65870, _mut65871, _mut65872));
                    }
                }
            }
            if (ROR_less_equals(maxCosine, orthoTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65878, _mut65879, _mut65880, _mut65881, _mut65882)) {
                // Convergence has been reached.
                setCost(currentCost);
                return current;
            }
            // rescale if necessary
            for (int j = 0; ROR_less(j, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65883, _mut65884, _mut65885, _mut65886, _mut65887); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                diag[j] = FastMath.max(diag[j], jacNorm[j]);
            }
            // Inner loop.
            for (double ratio = 0; ROR_less(ratio, 1.0e-4, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66137, _mut66138, _mut66139, _mut66140, _mut66141); ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                // save the state
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65888, _mut65889, _mut65890, _mut65891, _mut65892); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
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
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65905, _mut65906, _mut65907, _mut65908, _mut65909); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                    int pj = permutation[j];
                    lmDir[pj] = -lmDir[pj];
                    currentPoint[pj] = AOR_plus(oldX[pj], lmDir[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65893, _mut65894, _mut65895, _mut65896);
                    double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65897, _mut65898, _mut65899, _mut65900);
                    lmNorm += AOR_multiply(s, s, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65901, _mut65902, _mut65903, _mut65904);
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
                if (ROR_less(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65910, _mut65911, _mut65912, _mut65913), previousCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65914, _mut65915, _mut65916, _mut65917, _mut65918)) {
                    double r = AOR_divide(currentCost, previousCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65919, _mut65920, _mut65921, _mut65922);
                    actRed = AOR_minus(1.0, AOR_multiply(r, r, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65923, _mut65924, _mut65925, _mut65926), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65927, _mut65928, _mut65929, _mut65930);
                }
                // and the scaled directional derivative
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65940, _mut65941, _mut65942, _mut65943, _mut65944); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                    int pj = permutation[j];
                    double dirJ = lmDir[pj];
                    work1[j] = 0;
                    for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65935, _mut65936, _mut65937, _mut65938, _mut65939); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                        work1[i] += AOR_multiply(weightedJacobian[i][pj], dirJ, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65931, _mut65932, _mut65933, _mut65934);
                    }
                }
                double coeff1 = 0;
                for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65949, _mut65950, _mut65951, _mut65952, _mut65953); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                    coeff1 += AOR_multiply(work1[j], work1[j], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65945, _mut65946, _mut65947, _mut65948);
                }
                double pc2 = AOR_multiply(previousCost, previousCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65954, _mut65955, _mut65956, _mut65957);
                coeff1 /= pc2;
                double coeff2 = AOR_divide(AOR_multiply(AOR_multiply(lmPar, lmNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65958, _mut65959, _mut65960, _mut65961), lmNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65962, _mut65963, _mut65964, _mut65965), pc2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65966, _mut65967, _mut65968, _mut65969);
                double preRed = AOR_plus(coeff1, AOR_multiply(2, coeff2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65970, _mut65971, _mut65972, _mut65973), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65974, _mut65975, _mut65976, _mut65977);
                double dirDer = -(AOR_plus(coeff1, coeff2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65978, _mut65979, _mut65980, _mut65981));
                // ratio of the actual to the predicted reduction
                ratio = (ROR_equals(preRed, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65982, _mut65983, _mut65984, _mut65985, _mut65986)) ? 0 : (AOR_divide(actRed, preRed, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65987, _mut65988, _mut65989, _mut65990));
                // update the step bound
                if (ROR_less_equals(ratio, 0.25, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65991, _mut65992, _mut65993, _mut65994, _mut65995)) {
                    double tmp = (ROR_less(actRed, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66011, _mut66012, _mut66013, _mut66014, _mut66015)) ? (AOR_divide(AOR_multiply(0.5, dirDer, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66016, _mut66017, _mut66018, _mut66019), (AOR_plus(dirDer, AOR_multiply(0.5, actRed, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66020, _mut66021, _mut66022, _mut66023), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66024, _mut66025, _mut66026, _mut66027)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66028, _mut66029, _mut66030, _mut66031)) : 0.5;
                    if ((_mut66046 ? ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66032, _mut66033, _mut66034, _mut66035), previousCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66036, _mut66037, _mut66038, _mut66039, _mut66040)) && (ROR_less(tmp, 0.1, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66041, _mut66042, _mut66043, _mut66044, _mut66045))) : ((ROR_greater_equals(AOR_multiply(0.1, currentCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66032, _mut66033, _mut66034, _mut66035), previousCost, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66036, _mut66037, _mut66038, _mut66039, _mut66040)) || (ROR_less(tmp, 0.1, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66041, _mut66042, _mut66043, _mut66044, _mut66045))))) {
                        tmp = 0.1;
                    }
                    delta = AOR_multiply(tmp, FastMath.min(delta, AOR_multiply(10.0, lmNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66047, _mut66048, _mut66049, _mut66050)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66051, _mut66052, _mut66053, _mut66054);
                    lmPar /= tmp;
                } else if ((_mut66006 ? ((ROR_equals(lmPar, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65996, _mut65997, _mut65998, _mut65999, _mut66000)) && (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66001, _mut66002, _mut66003, _mut66004, _mut66005))) : ((ROR_equals(lmPar, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut65996, _mut65997, _mut65998, _mut65999, _mut66000)) || (ROR_greater_equals(ratio, 0.75, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66001, _mut66002, _mut66003, _mut66004, _mut66005))))) {
                    delta = AOR_multiply(2, lmNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66007, _mut66008, _mut66009, _mut66010);
                    lmPar *= 0.5;
                }
                // test for successful iteration.
                if (ROR_greater_equals(ratio, 1.0e-4, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66055, _mut66056, _mut66057, _mut66058, _mut66059)) {
                    // successful iteration, update the norm
                    firstIteration = false;
                    xNorm = 0;
                    for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66073, _mut66074, _mut66075, _mut66076, _mut66077); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
                        double xK = AOR_multiply(diag[k], currentPoint[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66065, _mut66066, _mut66067, _mut66068);
                        xNorm += AOR_multiply(xK, xK, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66069, _mut66070, _mut66071, _mut66072);
                    }
                    xNorm = FastMath.sqrt(xNorm);
                    // tests for convergence.
                    if ((_mut66078 ? (checker != null || checker.converged(getIterations(), previous, current)) : (checker != null && checker.converged(getIterations(), previous, current)))) {
                        setCost(currentCost);
                        return current;
                    }
                } else {
                    // failed iteration, reset the previous values
                    currentCost = previousCost;
                    for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66060, _mut66061, _mut66062, _mut66063, _mut66064); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289");
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
                if ((_mut66105 ? (((_mut66095 ? ((_mut66089 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66090, _mut66091, _mut66092, _mut66093, _mut66094)) : ((_mut66089 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66090, _mut66091, _mut66092, _mut66093, _mut66094)))) && ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66096, _mut66097, _mut66098, _mut66099), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66100, _mut66101, _mut66102, _mut66103, _mut66104)) : (((_mut66095 ? ((_mut66089 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66090, _mut66091, _mut66092, _mut66093, _mut66094)) : ((_mut66089 ? (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) || ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088)) : (ROR_less_equals(FastMath.abs(actRed), costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66079, _mut66080, _mut66081, _mut66082, _mut66083) && ROR_less_equals(preRed, costRelativeTolerance, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66084, _mut66085, _mut66086, _mut66087, _mut66088))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66090, _mut66091, _mut66092, _mut66093, _mut66094)))) || ROR_less_equals(delta, AOR_multiply(parRelativeTolerance, xNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66096, _mut66097, _mut66098, _mut66099), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66100, _mut66101, _mut66102, _mut66103, _mut66104)))) {
                    setCost(currentCost);
                    return current;
                }
                // tests for termination and stringent tolerances
                if ((_mut66122 ? ((_mut66116 ? (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66106, _mut66107, _mut66108, _mut66109, _mut66110) || ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66111, _mut66112, _mut66113, _mut66114, _mut66115)) : (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66106, _mut66107, _mut66108, _mut66109, _mut66110) && ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66111, _mut66112, _mut66113, _mut66114, _mut66115))) || ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66117, _mut66118, _mut66119, _mut66120, _mut66121)) : ((_mut66116 ? (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66106, _mut66107, _mut66108, _mut66109, _mut66110) || ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66111, _mut66112, _mut66113, _mut66114, _mut66115)) : (ROR_less_equals(FastMath.abs(actRed), TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66106, _mut66107, _mut66108, _mut66109, _mut66110) && ROR_less_equals(preRed, TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66111, _mut66112, _mut66113, _mut66114, _mut66115))) && ROR_less_equals(ratio, 2.0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66117, _mut66118, _mut66119, _mut66120, _mut66121)))) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, costRelativeTolerance);
                } else if (ROR_less_equals(delta, AOR_multiply(TWO_EPS, xNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66123, _mut66124, _mut66125, _mut66126), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66127, _mut66128, _mut66129, _mut66130, _mut66131)) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, parRelativeTolerance);
                } else if (ROR_less_equals(maxCosine, TWO_EPS, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize_289", _mut66132, _mut66133, _mut66134, _mut66135, _mut66136)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
        final int nC = weightedJacobian[0].length;
        // jacobian is rank-deficient, obtain a least squares solution
        for (int j = 0; ROR_less(j, rank, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66142, _mut66143, _mut66144, _mut66145, _mut66146); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            lmDir[permutation[j]] = qy[j];
        }
        for (int j = rank; ROR_less(j, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66147, _mut66148, _mut66149, _mut66150, _mut66151); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            lmDir[permutation[j]] = 0;
        }
        for (int k = rank - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66165, _mut66166, _mut66167, _mut66168, _mut66169); --k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            int pk = permutation[k];
            double ypk = AOR_divide(lmDir[pk], diagR[pk], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66152, _mut66153, _mut66154, _mut66155);
            for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66160, _mut66161, _mut66162, _mut66163, _mut66164); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                lmDir[permutation[i]] -= AOR_multiply(ypk, weightedJacobian[i][pk], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66156, _mut66157, _mut66158, _mut66159);
            }
            lmDir[pk] = ypk;
        }
        // for acceptance of the Gauss-Newton direction
        double dxNorm = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66178, _mut66179, _mut66180, _mut66181, _mut66182); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            int pj = permutation[j];
            double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66170, _mut66171, _mut66172, _mut66173);
            work1[pj] = s;
            dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66174, _mut66175, _mut66176, _mut66177);
        }
        dxNorm = FastMath.sqrt(dxNorm);
        double fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66183, _mut66184, _mut66185, _mut66186);
        if (ROR_less_equals(fp, AOR_multiply(0.1, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66187, _mut66188, _mut66189, _mut66190), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66191, _mut66192, _mut66193, _mut66194, _mut66195)) {
            lmPar = 0;
            return;
        }
        // otherwise set this bound to zero
        double sum2;
        double parl = 0;
        if (ROR_equals(rank, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66196, _mut66197, _mut66198, _mut66199, _mut66200)) {
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66205, _mut66206, _mut66207, _mut66208, _mut66209); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                work1[pj] *= AOR_divide(diag[pj], dxNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66201, _mut66202, _mut66203, _mut66204);
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66231, _mut66232, _mut66233, _mut66234, _mut66235); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                double sum = 0;
                for (int i = 0; ROR_less(i, j, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66214, _mut66215, _mut66216, _mut66217, _mut66218); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                    sum += AOR_multiply(weightedJacobian[i][pj], work1[permutation[i]], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66210, _mut66211, _mut66212, _mut66213);
                }
                double s = AOR_divide((AOR_minus(work1[pj], sum, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66219, _mut66220, _mut66221, _mut66222)), diagR[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66223, _mut66224, _mut66225, _mut66226);
                work1[pj] = s;
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66227, _mut66228, _mut66229, _mut66230);
            }
            parl = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66236, _mut66237, _mut66238, _mut66239)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66240, _mut66241, _mut66242, _mut66243);
        }
        // calculate an upper bound, paru, for the zero of the function
        sum2 = 0;
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66257, _mut66258, _mut66259, _mut66260, _mut66261); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            int pj = permutation[j];
            double sum = 0;
            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66248, _mut66249, _mut66250, _mut66251, _mut66252); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                sum += AOR_multiply(weightedJacobian[i][pj], qy[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66244, _mut66245, _mut66246, _mut66247);
            }
            sum /= diag[pj];
            sum2 += AOR_multiply(sum, sum, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66253, _mut66254, _mut66255, _mut66256);
        }
        double gNorm = FastMath.sqrt(sum2);
        double paru = AOR_divide(gNorm, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66262, _mut66263, _mut66264, _mut66265);
        if (ROR_equals(paru, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66266, _mut66267, _mut66268, _mut66269, _mut66270)) {
            paru = AOR_divide(Precision.SAFE_MIN, FastMath.min(delta, 0.1), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66271, _mut66272, _mut66273, _mut66274);
        }
        // set par to the closer endpoint
        lmPar = FastMath.min(paru, FastMath.max(lmPar, parl));
        if (ROR_equals(lmPar, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66275, _mut66276, _mut66277, _mut66278, _mut66279)) {
            lmPar = AOR_divide(gNorm, dxNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66280, _mut66281, _mut66282, _mut66283);
        }
        for (int countdown = 10; ROR_greater_equals(countdown, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66404, _mut66405, _mut66406, _mut66407, _mut66408); --countdown) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
            // evaluate the function at the current value of lmPar
            if (ROR_equals(lmPar, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66284, _mut66285, _mut66286, _mut66287, _mut66288)) {
                lmPar = FastMath.max(Precision.SAFE_MIN, AOR_multiply(0.001, paru, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66289, _mut66290, _mut66291, _mut66292));
            }
            double sPar = FastMath.sqrt(lmPar);
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66297, _mut66298, _mut66299, _mut66300, _mut66301); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                work1[pj] = AOR_multiply(sPar, diag[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66293, _mut66294, _mut66295, _mut66296);
            }
            determineLMDirection(qy, work1, work2, work3);
            dxNorm = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66310, _mut66311, _mut66312, _mut66313, _mut66314); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                double s = AOR_multiply(diag[pj], lmDir[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66302, _mut66303, _mut66304, _mut66305);
                work3[pj] = s;
                dxNorm += AOR_multiply(s, s, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66306, _mut66307, _mut66308, _mut66309);
            }
            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = AOR_minus(dxNorm, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66315, _mut66316, _mut66317, _mut66318);
            // of lmPar, also test for the exceptional cases where parl is zero
            if ((_mut66345 ? ((ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66319, _mut66320, _mut66321, _mut66322), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66323, _mut66324, _mut66325, _mut66326, _mut66327)) && ((_mut66344 ? ((_mut66338 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337)))) || (ROR_less(previousFP, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66339, _mut66340, _mut66341, _mut66342, _mut66343))) : ((_mut66338 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337)))) && (ROR_less(previousFP, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66339, _mut66340, _mut66341, _mut66342, _mut66343)))))) : ((ROR_less_equals(FastMath.abs(fp), AOR_multiply(0.1, delta, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66319, _mut66320, _mut66321, _mut66322), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66323, _mut66324, _mut66325, _mut66326, _mut66327)) || ((_mut66344 ? ((_mut66338 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337)))) || (ROR_less(previousFP, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66339, _mut66340, _mut66341, _mut66342, _mut66343))) : ((_mut66338 ? ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) || (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337))) : ((ROR_equals(parl, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66328, _mut66329, _mut66330, _mut66331, _mut66332)) && (ROR_less_equals(fp, previousFP, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66333, _mut66334, _mut66335, _mut66336, _mut66337)))) && (ROR_less(previousFP, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66339, _mut66340, _mut66341, _mut66342, _mut66343)))))))) {
                return;
            }
            // compute the Newton correction
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66354, _mut66355, _mut66356, _mut66357, _mut66358); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                work1[pj] = AOR_divide(AOR_multiply(work3[pj], diag[pj], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66346, _mut66347, _mut66348, _mut66349), dxNorm, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66350, _mut66351, _mut66352, _mut66353);
            }
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66368, _mut66369, _mut66370, _mut66371, _mut66372); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                int pj = permutation[j];
                work1[pj] /= work2[j];
                double tmp = work1[pj];
                for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66363, _mut66364, _mut66365, _mut66366, _mut66367); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                    work1[permutation[i]] -= AOR_multiply(weightedJacobian[i][pj], tmp, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66359, _mut66360, _mut66361, _mut66362);
                }
            }
            sum2 = 0;
            for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66377, _mut66378, _mut66379, _mut66380, _mut66381); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562");
                double s = work1[permutation[j]];
                sum2 += AOR_multiply(s, s, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66373, _mut66374, _mut66375, _mut66376);
            }
            double correction = AOR_divide(fp, (AOR_multiply(delta, sum2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66382, _mut66383, _mut66384, _mut66385)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66386, _mut66387, _mut66388, _mut66389);
            // depending on the sign of the function, update parl or paru.
            if (ROR_greater(fp, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66390, _mut66391, _mut66392, _mut66393, _mut66394)) {
                parl = FastMath.max(parl, lmPar);
            } else if (ROR_less(fp, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66395, _mut66396, _mut66397, _mut66398, _mut66399)) {
                paru = FastMath.min(paru, lmPar);
            }
            // compute an improved estimate for lmPar
            lmPar = FastMath.max(parl, AOR_plus(lmPar, correction, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMParameter_562", _mut66400, _mut66401, _mut66402, _mut66403));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
        // in particular, save the diagonal elements of R in lmDir
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66414, _mut66415, _mut66416, _mut66417, _mut66418); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
            int pj = permutation[j];
            for (int i = j + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66409, _mut66410, _mut66411, _mut66412, _mut66413); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
                weightedJacobian[i][pj] = weightedJacobian[j][permutation[i]];
            }
            lmDir[j] = diagR[pj];
            work[j] = qy[j];
        }
        // eliminate the diagonal matrix d using a Givens rotation
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66548, _mut66549, _mut66550, _mut66551, _mut66552); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
            // diagonal element using p from the Q.R. factorization
            int pj = permutation[j];
            double dpj = diag[pj];
            if (ROR_not_equals(dpj, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66419, _mut66420, _mut66421, _mut66422, _mut66423)) {
                Arrays.fill(lmDiag, AOR_plus(j, 1, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66424, _mut66425, _mut66426, _mut66427), lmDiag.length, 0);
            }
            lmDiag[j] = dpj;
            // beyond the first n, which is initially zero.
            double qtbpj = 0;
            for (int k = j; ROR_less(k, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66543, _mut66544, _mut66545, _mut66546, _mut66547); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
                int pk = permutation[k];
                // appropriate element in the current row of d
                if (ROR_not_equals(lmDiag[k], 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66428, _mut66429, _mut66430, _mut66431, _mut66432)) {
                    final double sin;
                    final double cos;
                    double rkk = weightedJacobian[k][pk];
                    if (ROR_less(FastMath.abs(rkk), FastMath.abs(lmDiag[k]), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66433, _mut66434, _mut66435, _mut66436, _mut66437)) {
                        final double cotan = AOR_divide(rkk, lmDiag[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66458, _mut66459, _mut66460, _mut66461);
                        sin = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(cotan, cotan, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66462, _mut66463, _mut66464, _mut66465), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66466, _mut66467, _mut66468, _mut66469)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66470, _mut66471, _mut66472, _mut66473);
                        cos = AOR_multiply(sin, cotan, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66474, _mut66475, _mut66476, _mut66477);
                    } else {
                        final double tan = AOR_divide(lmDiag[k], rkk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66438, _mut66439, _mut66440, _mut66441);
                        cos = AOR_divide(1.0, FastMath.sqrt(AOR_plus(1.0, AOR_multiply(tan, tan, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66442, _mut66443, _mut66444, _mut66445), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66446, _mut66447, _mut66448, _mut66449)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66450, _mut66451, _mut66452, _mut66453);
                        sin = AOR_multiply(cos, tan, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66454, _mut66455, _mut66456, _mut66457);
                    }
                    // the modified element of (Qty,0)
                    weightedJacobian[k][pk] = AOR_plus(AOR_multiply(cos, rkk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66478, _mut66479, _mut66480, _mut66481), AOR_multiply(sin, lmDiag[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66482, _mut66483, _mut66484, _mut66485), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66486, _mut66487, _mut66488, _mut66489);
                    final double temp = AOR_plus(AOR_multiply(cos, work[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66490, _mut66491, _mut66492, _mut66493), AOR_multiply(sin, qtbpj, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66494, _mut66495, _mut66496, _mut66497), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66498, _mut66499, _mut66500, _mut66501);
                    qtbpj = AOR_plus(AOR_multiply(-sin, work[k], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66502, _mut66503, _mut66504, _mut66505), AOR_multiply(cos, qtbpj, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66506, _mut66507, _mut66508, _mut66509), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66510, _mut66511, _mut66512, _mut66513);
                    work[k] = temp;
                    // accumulate the tranformation in the row of s
                    for (int i = k + 1; ROR_less(i, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66538, _mut66539, _mut66540, _mut66541, _mut66542); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
                        double rik = weightedJacobian[i][pk];
                        final double temp2 = AOR_plus(AOR_multiply(cos, rik, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66514, _mut66515, _mut66516, _mut66517), AOR_multiply(sin, lmDiag[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66518, _mut66519, _mut66520, _mut66521), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66522, _mut66523, _mut66524, _mut66525);
                        lmDiag[i] = AOR_plus(AOR_multiply(-sin, rik, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66526, _mut66527, _mut66528, _mut66529), AOR_multiply(cos, lmDiag[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66530, _mut66531, _mut66532, _mut66533), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66534, _mut66535, _mut66536, _mut66537);
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
        for (int j = 0; ROR_less(j, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66569, _mut66570, _mut66571, _mut66572, _mut66573); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
            if ((_mut66563 ? ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66553, _mut66554, _mut66555, _mut66556, _mut66557)) || (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66558, _mut66559, _mut66560, _mut66561, _mut66562))) : ((ROR_equals(lmDiag[j], 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66553, _mut66554, _mut66555, _mut66556, _mut66557)) && (ROR_equals(nSing, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66558, _mut66559, _mut66560, _mut66561, _mut66562))))) {
                nSing = j;
            }
            if (ROR_less(nSing, solvedCols, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66564, _mut66565, _mut66566, _mut66567, _mut66568)) {
                work[j] = 0;
            }
        }
        if (ROR_greater(nSing, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66574, _mut66575, _mut66576, _mut66577, _mut66578)) {
            for (int j = nSing - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66596, _mut66597, _mut66598, _mut66599, _mut66600); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
                int pj = permutation[j];
                double sum = 0;
                for (int i = j + 1; ROR_less(i, nSing, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66583, _mut66584, _mut66585, _mut66586, _mut66587); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
                    sum += AOR_multiply(weightedJacobian[i][pj], work[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66579, _mut66580, _mut66581, _mut66582);
                }
                work[j] = AOR_divide((AOR_minus(work[j], sum, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66588, _mut66589, _mut66590, _mut66591)), lmDiag[j], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66592, _mut66593, _mut66594, _mut66595);
            }
        }
        // permute the components of z back to components of lmDir
        for (int j = 0; ROR_less(j, lmDir.length, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731", _mut66601, _mut66602, _mut66603, _mut66604, _mut66605); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.determineLMDirection_731");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
        // hence the multiplication by -1.
        weightedJacobian = jacobian.scalarMultiply(-1).getData();
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        // initializations
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66615, _mut66616, _mut66617, _mut66618, _mut66619); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
            permutation[k] = k;
            double norm2 = 0;
            for (int i = 0; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66610, _mut66611, _mut66612, _mut66613, _mut66614); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                double akk = weightedJacobian[i][k];
                norm2 += AOR_multiply(akk, akk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66606, _mut66607, _mut66608, _mut66609);
            }
            jacNorm[k] = FastMath.sqrt(norm2);
        }
        // transform the matrix column after column
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66693, _mut66694, _mut66695, _mut66696, _mut66697); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
            // select the column with the greatest norm on active components
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i = k; ROR_less(i, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66635, _mut66636, _mut66637, _mut66638, _mut66639); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                double norm2 = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66624, _mut66625, _mut66626, _mut66627, _mut66628); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                    double aki = weightedJacobian[j][permutation[i]];
                    norm2 += AOR_multiply(aki, aki, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66620, _mut66621, _mut66622, _mut66623);
                }
                if ((_mut66629 ? (Double.isInfinite(norm2) && Double.isNaN(norm2)) : (Double.isInfinite(norm2) || Double.isNaN(norm2)))) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, nR, nC);
                }
                if (ROR_greater(norm2, ak2, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66630, _mut66631, _mut66632, _mut66633, _mut66634)) {
                    nextColumn = i;
                    ak2 = norm2;
                }
            }
            if (ROR_less_equals(ak2, qrRankingThreshold, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66640, _mut66641, _mut66642, _mut66643, _mut66644)) {
                rank = k;
                return;
            }
            int pk = permutation[nextColumn];
            permutation[nextColumn] = permutation[k];
            permutation[k] = pk;
            // choose alpha such that Hk.u = alpha ek
            double akk = weightedJacobian[k][pk];
            double alpha = (ROR_greater(akk, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66645, _mut66646, _mut66647, _mut66648, _mut66649)) ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
            double betak = AOR_divide(1.0, (AOR_minus(ak2, AOR_multiply(akk, alpha, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66650, _mut66651, _mut66652, _mut66653), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66654, _mut66655, _mut66656, _mut66657)), "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66658, _mut66659, _mut66660, _mut66661);
            beta[pk] = betak;
            // transform the current column
            diagR[pk] = alpha;
            weightedJacobian[k][pk] -= alpha;
            // transform the remaining columns
            for (int dk = nC - 1 - k; ROR_greater(dk, 0, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66688, _mut66689, _mut66690, _mut66691, _mut66692); --dk) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                double gamma = 0;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66670, _mut66671, _mut66672, _mut66673, _mut66674); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                    gamma += AOR_multiply(weightedJacobian[j][pk], weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66662, _mut66663, _mut66664, _mut66665)]], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66666, _mut66667, _mut66668, _mut66669);
                }
                gamma *= betak;
                for (int j = k; ROR_less(j, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66683, _mut66684, _mut66685, _mut66686, _mut66687); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856");
                    weightedJacobian[j][permutation[AOR_plus(k, dk, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66675, _mut66676, _mut66677, _mut66678)]] -= AOR_multiply(gamma, weightedJacobian[j][pk], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qrDecomposition_856", _mut66679, _mut66680, _mut66681, _mut66682);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934");
        final int nR = weightedJacobian.length;
        final int nC = weightedJacobian[0].length;
        for (int k = 0; ROR_less(k, nC, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934", _mut66716, _mut66717, _mut66718, _mut66719, _mut66720); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934");
            int pk = permutation[k];
            double gamma = 0;
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934", _mut66702, _mut66703, _mut66704, _mut66705, _mut66706); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934");
                gamma += AOR_multiply(weightedJacobian[i][pk], y[i], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934", _mut66698, _mut66699, _mut66700, _mut66701);
            }
            gamma *= beta[pk];
            for (int i = k; ROR_less(i, nR, "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934", _mut66711, _mut66712, _mut66713, _mut66714, _mut66715); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934");
                y[i] -= AOR_multiply(gamma, weightedJacobian[i][pk], "org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.qTy_934", _mut66707, _mut66708, _mut66709, _mut66710);
            }
        }
    }

    /**
     * @throws MathUnsupportedOperationException if bounds were passed to the
     * {@link #optimize(OptimizationData[]) optimize} method.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.checkParameters_955");
        if ((_mut66721 ? (getLowerBound() != null && getUpperBound() != null) : (getLowerBound() != null || getUpperBound() != null))) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT);
        }
    }
}
