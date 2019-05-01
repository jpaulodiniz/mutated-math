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
package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * This is a utility class that provides computation methods related to the
 * &Gamma; (Gamma) family of functions.
 * </p>
 * <p>
 * Implementation of {@link #invGamma1pm1(double)} and
 * {@link #logGamma1p(double)} is based on the algorithms described in
 * <ul>
 * <li><a href="http://dx.doi.org/10.1145/22721.23109">Didonato and Morris
 * (1986)</a>, <em>Computation of the Incomplete Gamma Function Ratios and
 *     their Inverse</em>, TOMS 12(4), 377-393,</li>
 * <li><a href="http://dx.doi.org/10.1145/131766.131776">Didonato and Morris
 * (1992)</a>, <em>Algorithm 708: Significant Digit Computation of the
 *     Incomplete Beta Function Ratios</em>, TOMS 18(3), 360-373,</li>
 * </ul>
 * and implemented in the
 * <a href="http://www.dtic.mil/docs/citations/ADA476840">NSWC Library of Mathematical Functions</a>,
 * available
 * <a href="http://www.ualberta.ca/CNS/RESEARCH/Software/NumericalNSWC/site.html">here</a>.
 * This library is "approved for public release", and the
 * <a href="http://www.dtic.mil/dtic/pdf/announcements/CopyrightGuidance.pdf">Copyright guidance</a>
 * indicates that unless otherwise stated in the code, all FORTRAN functions in
 * this library are license free. Since no such notice appears in the code these
 * functions can safely be ported to Commons-Math.
 * </p>
 */
public class Gamma {

    @Conditional
    public static boolean _mut66832 = false, _mut66833 = false, _mut66834 = false, _mut66835 = false, _mut66836 = false, _mut66837 = false, _mut66838 = false, _mut66839 = false, _mut66840 = false, _mut66841 = false, _mut66842 = false, _mut66843 = false, _mut66844 = false, _mut66845 = false, _mut66846 = false, _mut66847 = false, _mut66848 = false, _mut66849 = false, _mut66850 = false, _mut66851 = false, _mut66852 = false, _mut66853 = false, _mut66854 = false, _mut66855 = false, _mut66856 = false, _mut66857 = false, _mut66858 = false, _mut66859 = false, _mut66860 = false, _mut66861 = false, _mut66862 = false, _mut66863 = false, _mut66864 = false, _mut66865 = false, _mut66866 = false, _mut66867 = false, _mut66868 = false, _mut66869 = false, _mut66870 = false, _mut66871 = false, _mut66872 = false, _mut66873 = false, _mut66874 = false, _mut66875 = false, _mut66876 = false, _mut66877 = false, _mut66878 = false, _mut66879 = false, _mut66880 = false, _mut66881 = false, _mut66882 = false, _mut66883 = false, _mut66884 = false, _mut66885 = false, _mut66886 = false, _mut66887 = false, _mut66888 = false, _mut66889 = false, _mut66890 = false, _mut66891 = false, _mut66892 = false, _mut66893 = false, _mut66894 = false, _mut66895 = false, _mut66896 = false, _mut66897 = false, _mut66898 = false, _mut66899 = false, _mut66900 = false, _mut66901 = false, _mut66902 = false, _mut66903 = false, _mut66904 = false, _mut66905 = false, _mut66906 = false, _mut66907 = false, _mut66908 = false, _mut66909 = false, _mut66910 = false, _mut66911 = false, _mut66912 = false, _mut66913 = false, _mut66914 = false, _mut66915 = false, _mut66916 = false, _mut66917 = false, _mut66918 = false, _mut66919 = false, _mut66920 = false, _mut66921 = false, _mut66922 = false, _mut66923 = false, _mut66924 = false, _mut66925 = false, _mut66926 = false, _mut66927 = false, _mut66928 = false, _mut66929 = false, _mut66930 = false, _mut66931 = false, _mut66932 = false, _mut66933 = false, _mut66934 = false, _mut66935 = false, _mut66936 = false, _mut66937 = false, _mut66938 = false, _mut66939 = false, _mut66940 = false, _mut66941 = false, _mut66942 = false, _mut66943 = false, _mut66944 = false, _mut66945 = false, _mut66946 = false, _mut66947 = false, _mut66948 = false, _mut66949 = false, _mut66950 = false, _mut66951 = false, _mut66952 = false, _mut66953 = false, _mut66954 = false, _mut66955 = false, _mut66956 = false, _mut66957 = false, _mut66958 = false, _mut66959 = false, _mut66960 = false, _mut66961 = false, _mut66962 = false, _mut66963 = false, _mut66964 = false, _mut66965 = false, _mut66966 = false, _mut66967 = false, _mut66968 = false, _mut66969 = false, _mut66970 = false, _mut66971 = false, _mut66972 = false, _mut66973 = false, _mut66974 = false, _mut66975 = false, _mut66976 = false, _mut66977 = false, _mut66978 = false, _mut66979 = false, _mut66980 = false, _mut66981 = false, _mut66982 = false, _mut66983 = false, _mut66984 = false, _mut66985 = false, _mut66986 = false, _mut66987 = false, _mut66988 = false, _mut66989 = false, _mut66990 = false, _mut66991 = false, _mut66992 = false, _mut66993 = false, _mut66994 = false, _mut66995 = false, _mut66996 = false, _mut66997 = false, _mut66998 = false, _mut66999 = false, _mut67000 = false, _mut67001 = false, _mut67002 = false, _mut67003 = false, _mut67004 = false, _mut67005 = false, _mut67006 = false, _mut67007 = false, _mut67008 = false, _mut67009 = false, _mut67010 = false, _mut67011 = false, _mut67012 = false, _mut67013 = false, _mut67014 = false, _mut67015 = false, _mut67016 = false, _mut67017 = false, _mut67018 = false, _mut67019 = false, _mut67020 = false, _mut67021 = false, _mut67022 = false, _mut67023 = false, _mut67024 = false, _mut67025 = false, _mut67026 = false, _mut67027 = false, _mut67028 = false, _mut67029 = false, _mut67030 = false, _mut67031 = false, _mut67032 = false, _mut67033 = false, _mut67034 = false, _mut67035 = false, _mut67036 = false, _mut67037 = false, _mut67038 = false, _mut67039 = false, _mut67040 = false, _mut67041 = false, _mut67042 = false, _mut67043 = false, _mut67044 = false, _mut67045 = false, _mut67046 = false, _mut67047 = false, _mut67048 = false, _mut67049 = false, _mut67050 = false, _mut67051 = false, _mut67052 = false, _mut67053 = false, _mut67054 = false, _mut67055 = false, _mut67056 = false, _mut67057 = false, _mut67058 = false, _mut67059 = false, _mut67060 = false, _mut67061 = false, _mut67062 = false, _mut67063 = false, _mut67064 = false, _mut67065 = false, _mut67066 = false, _mut67067 = false, _mut67068 = false, _mut67069 = false, _mut67070 = false, _mut67071 = false, _mut67072 = false, _mut67073 = false, _mut67074 = false, _mut67075 = false, _mut67076 = false, _mut67077 = false, _mut67078 = false, _mut67079 = false, _mut67080 = false, _mut67081 = false, _mut67082 = false, _mut67083 = false, _mut67084 = false, _mut67085 = false, _mut67086 = false, _mut67087 = false, _mut67088 = false, _mut67089 = false, _mut67090 = false, _mut67091 = false, _mut67092 = false, _mut67093 = false, _mut67094 = false, _mut67095 = false, _mut67096 = false, _mut67097 = false, _mut67098 = false, _mut67099 = false, _mut67100 = false, _mut67101 = false, _mut67102 = false, _mut67103 = false, _mut67104 = false, _mut67105 = false, _mut67106 = false, _mut67107 = false, _mut67108 = false, _mut67109 = false, _mut67110 = false, _mut67111 = false, _mut67112 = false, _mut67113 = false, _mut67114 = false, _mut67115 = false, _mut67116 = false, _mut67117 = false, _mut67118 = false, _mut67119 = false, _mut67120 = false, _mut67121 = false, _mut67122 = false, _mut67123 = false, _mut67124 = false, _mut67125 = false, _mut67126 = false, _mut67127 = false, _mut67128 = false, _mut67129 = false, _mut67130 = false, _mut67131 = false, _mut67132 = false, _mut67133 = false, _mut67134 = false, _mut67135 = false, _mut67136 = false, _mut67137 = false, _mut67138 = false, _mut67139 = false, _mut67140 = false, _mut67141 = false, _mut67142 = false, _mut67143 = false, _mut67144 = false, _mut67145 = false, _mut67146 = false, _mut67147 = false, _mut67148 = false, _mut67149 = false, _mut67150 = false, _mut67151 = false, _mut67152 = false, _mut67153 = false, _mut67154 = false, _mut67155 = false, _mut67156 = false, _mut67157 = false, _mut67158 = false, _mut67159 = false, _mut67160 = false, _mut67161 = false, _mut67162 = false, _mut67163 = false, _mut67164 = false, _mut67165 = false, _mut67166 = false, _mut67167 = false, _mut67168 = false, _mut67169 = false, _mut67170 = false, _mut67171 = false, _mut67172 = false, _mut67173 = false, _mut67174 = false, _mut67175 = false, _mut67176 = false, _mut67177 = false, _mut67178 = false, _mut67179 = false, _mut67180 = false, _mut67181 = false, _mut67182 = false, _mut67183 = false, _mut67184 = false, _mut67185 = false, _mut67186 = false, _mut67187 = false, _mut67188 = false, _mut67189 = false, _mut67190 = false, _mut67191 = false, _mut67192 = false, _mut67193 = false, _mut67194 = false, _mut67195 = false, _mut67196 = false, _mut67197 = false, _mut67198 = false, _mut67199 = false, _mut67200 = false, _mut67201 = false, _mut67202 = false, _mut67203 = false, _mut67204 = false, _mut67205 = false, _mut67206 = false, _mut67207 = false, _mut67208 = false, _mut67209 = false, _mut67210 = false, _mut67211 = false, _mut67212 = false, _mut67213 = false, _mut67214 = false, _mut67215 = false, _mut67216 = false, _mut67217 = false, _mut67218 = false, _mut67219 = false, _mut67220 = false, _mut67221 = false, _mut67222 = false, _mut67223 = false, _mut67224 = false, _mut67225 = false, _mut67226 = false, _mut67227 = false, _mut67228 = false, _mut67229 = false, _mut67230 = false, _mut67231 = false, _mut67232 = false, _mut67233 = false, _mut67234 = false, _mut67235 = false, _mut67236 = false, _mut67237 = false, _mut67238 = false, _mut67239 = false, _mut67240 = false, _mut67241 = false, _mut67242 = false, _mut67243 = false, _mut67244 = false, _mut67245 = false, _mut67246 = false, _mut67247 = false, _mut67248 = false, _mut67249 = false, _mut67250 = false, _mut67251 = false, _mut67252 = false, _mut67253 = false, _mut67254 = false, _mut67255 = false, _mut67256 = false, _mut67257 = false, _mut67258 = false, _mut67259 = false, _mut67260 = false, _mut67261 = false, _mut67262 = false, _mut67263 = false, _mut67264 = false, _mut67265 = false, _mut67266 = false, _mut67267 = false, _mut67268 = false, _mut67269 = false, _mut67270 = false, _mut67271 = false, _mut67272 = false, _mut67273 = false, _mut67274 = false, _mut67275 = false, _mut67276 = false, _mut67277 = false, _mut67278 = false, _mut67279 = false, _mut67280 = false, _mut67281 = false, _mut67282 = false, _mut67283 = false, _mut67284 = false, _mut67285 = false, _mut67286 = false, _mut67287 = false, _mut67288 = false, _mut67289 = false, _mut67290 = false, _mut67291 = false, _mut67292 = false, _mut67293 = false, _mut67294 = false, _mut67295 = false, _mut67296 = false, _mut67297 = false, _mut67298 = false, _mut67299 = false, _mut67300 = false, _mut67301 = false, _mut67302 = false, _mut67303 = false, _mut67304 = false, _mut67305 = false, _mut67306 = false, _mut67307 = false, _mut67308 = false, _mut67309 = false, _mut67310 = false, _mut67311 = false, _mut67312 = false, _mut67313 = false, _mut67314 = false, _mut67315 = false, _mut67316 = false, _mut67317 = false, _mut67318 = false, _mut67319 = false, _mut67320 = false, _mut67321 = false, _mut67322 = false, _mut67323 = false, _mut67324 = false, _mut67325 = false, _mut67326 = false, _mut67327 = false, _mut67328 = false, _mut67329 = false, _mut67330 = false, _mut67331 = false, _mut67332 = false, _mut67333 = false, _mut67334 = false, _mut67335 = false, _mut67336 = false, _mut67337 = false, _mut67338 = false, _mut67339 = false, _mut67340 = false, _mut67341 = false, _mut67342 = false, _mut67343 = false, _mut67344 = false, _mut67345 = false, _mut67346 = false, _mut67347 = false, _mut67348 = false, _mut67349 = false, _mut67350 = false, _mut67351 = false, _mut67352 = false, _mut67353 = false, _mut67354 = false, _mut67355 = false, _mut67356 = false, _mut67357 = false, _mut67358 = false, _mut67359 = false, _mut67360 = false, _mut67361 = false, _mut67362 = false, _mut67363 = false, _mut67364 = false, _mut67365 = false, _mut67366 = false, _mut67367 = false, _mut67368 = false, _mut67369 = false, _mut67370 = false, _mut67371 = false, _mut67372 = false, _mut67373 = false, _mut67374 = false, _mut67375 = false, _mut67376 = false, _mut67377 = false, _mut67378 = false, _mut67379 = false, _mut67380 = false, _mut67381 = false, _mut67382 = false, _mut67383 = false, _mut67384 = false, _mut67385 = false, _mut67386 = false, _mut67387 = false, _mut67388 = false, _mut67389 = false, _mut67390 = false, _mut67391 = false, _mut67392 = false, _mut67393 = false, _mut67394 = false, _mut67395 = false, _mut67396 = false, _mut67397 = false, _mut67398 = false, _mut67399 = false, _mut67400 = false, _mut67401 = false, _mut67402 = false, _mut67403 = false, _mut67404 = false, _mut67405 = false, _mut67406 = false, _mut67407 = false, _mut67408 = false, _mut67409 = false, _mut67410 = false, _mut67411 = false, _mut67412 = false, _mut67413 = false, _mut67414 = false, _mut67415 = false, _mut67416 = false, _mut67417 = false, _mut67418 = false, _mut67419 = false, _mut67420 = false, _mut67421 = false, _mut67422 = false, _mut67423 = false, _mut67424 = false, _mut67425 = false, _mut67426 = false, _mut67427 = false, _mut67428 = false, _mut67429 = false, _mut67430 = false, _mut67431 = false, _mut67432 = false, _mut67433 = false, _mut67434 = false, _mut67435 = false, _mut67436 = false, _mut67437 = false, _mut67438 = false, _mut67439 = false, _mut67440 = false, _mut67441 = false, _mut67442 = false, _mut67443 = false, _mut67444 = false, _mut67445 = false, _mut67446 = false, _mut67447 = false, _mut67448 = false, _mut67449 = false, _mut67450 = false, _mut67451 = false, _mut67452 = false, _mut67453 = false, _mut67454 = false, _mut67455 = false, _mut67456 = false, _mut67457 = false, _mut67458 = false, _mut67459 = false, _mut67460 = false, _mut67461 = false, _mut67462 = false, _mut67463 = false, _mut67464 = false, _mut67465 = false, _mut67466 = false, _mut67467 = false, _mut67468 = false, _mut67469 = false, _mut67470 = false, _mut67471 = false, _mut67472 = false, _mut67473 = false, _mut67474 = false, _mut67475 = false, _mut67476 = false, _mut67477 = false, _mut67478 = false, _mut67479 = false, _mut67480 = false, _mut67481 = false, _mut67482 = false, _mut67483 = false, _mut67484 = false, _mut67485 = false, _mut67486 = false, _mut67487 = false, _mut67488 = false, _mut67489 = false, _mut67490 = false, _mut67491 = false, _mut67492 = false, _mut67493 = false, _mut67494 = false, _mut67495 = false, _mut67496 = false, _mut67497 = false, _mut67498 = false, _mut67499 = false, _mut67500 = false, _mut67501 = false, _mut67502 = false, _mut67503 = false, _mut67504 = false, _mut67505 = false, _mut67506 = false, _mut67507 = false, _mut67508 = false, _mut67509 = false, _mut67510 = false, _mut67511 = false, _mut67512 = false, _mut67513 = false, _mut67514 = false, _mut67515 = false, _mut67516 = false, _mut67517 = false, _mut67518 = false, _mut67519 = false, _mut67520 = false, _mut67521 = false, _mut67522 = false, _mut67523 = false, _mut67524 = false, _mut67525 = false, _mut67526 = false, _mut67527 = false, _mut67528 = false, _mut67529 = false, _mut67530 = false, _mut67531 = false, _mut67532 = false, _mut67533 = false, _mut67534 = false, _mut67535 = false, _mut67536 = false, _mut67537 = false, _mut67538 = false, _mut67539 = false, _mut67540 = false, _mut67541 = false, _mut67542 = false, _mut67543 = false, _mut67544 = false, _mut67545 = false, _mut67546 = false, _mut67547 = false, _mut67548 = false, _mut67549 = false, _mut67550 = false, _mut67551 = false, _mut67552 = false, _mut67553 = false, _mut67554 = false, _mut67555 = false, _mut67556 = false, _mut67557 = false, _mut67558 = false, _mut67559 = false, _mut67560 = false, _mut67561 = false, _mut67562 = false, _mut67563 = false, _mut67564 = false, _mut67565 = false, _mut67566 = false, _mut67567 = false, _mut67568 = false, _mut67569 = false, _mut67570 = false, _mut67571 = false, _mut67572 = false, _mut67573 = false, _mut67574 = false, _mut67575 = false, _mut67576 = false, _mut67577 = false, _mut67578 = false, _mut67579 = false, _mut67580 = false, _mut67581 = false, _mut67582 = false, _mut67583 = false, _mut67584 = false, _mut67585 = false, _mut67586 = false, _mut67587 = false, _mut67588 = false, _mut67589 = false, _mut67590 = false, _mut67591 = false, _mut67592 = false, _mut67593 = false, _mut67594 = false, _mut67595 = false, _mut67596 = false, _mut67597 = false, _mut67598 = false, _mut67599 = false, _mut67600 = false, _mut67601 = false, _mut67602 = false, _mut67603 = false, _mut67604 = false, _mut67605 = false, _mut67606 = false, _mut67607 = false, _mut67608 = false, _mut67609 = false, _mut67610 = false, _mut67611 = false, _mut67612 = false, _mut67613 = false, _mut67614 = false, _mut67615 = false, _mut67616 = false, _mut67617 = false, _mut67618 = false, _mut67619 = false, _mut67620 = false, _mut67621 = false, _mut67622 = false, _mut67623 = false, _mut67624 = false, _mut67625 = false, _mut67626 = false, _mut67627 = false, _mut67628 = false, _mut67629 = false, _mut67630 = false, _mut67631 = false, _mut67632 = false, _mut67633 = false, _mut67634 = false, _mut67635 = false, _mut67636 = false, _mut67637 = false, _mut67638 = false, _mut67639 = false, _mut67640 = false, _mut67641 = false, _mut67642 = false, _mut67643 = false, _mut67644 = false, _mut67645 = false, _mut67646 = false, _mut67647 = false, _mut67648 = false, _mut67649 = false, _mut67650 = false, _mut67651 = false, _mut67652 = false, _mut67653 = false, _mut67654 = false, _mut67655 = false, _mut67656 = false, _mut67657 = false, _mut67658 = false, _mut67659 = false, _mut67660 = false, _mut67661 = false, _mut67662 = false, _mut67663 = false, _mut67664 = false, _mut67665 = false, _mut67666 = false, _mut67667 = false, _mut67668 = false, _mut67669 = false, _mut67670 = false, _mut67671 = false, _mut67672 = false, _mut67673 = false, _mut67674 = false, _mut67675 = false, _mut67676 = false, _mut67677 = false, _mut67678 = false, _mut67679 = false, _mut67680 = false, _mut67681 = false, _mut67682 = false, _mut67683 = false, _mut67684 = false, _mut67685 = false, _mut67686 = false, _mut67687 = false, _mut67688 = false, _mut67689 = false, _mut67690 = false, _mut67691 = false, _mut67692 = false, _mut67693 = false, _mut67694 = false, _mut67695 = false, _mut67696 = false, _mut67697 = false, _mut67698 = false, _mut67699 = false, _mut67700 = false, _mut67701 = false, _mut67702 = false, _mut67703 = false, _mut67704 = false, _mut67705 = false, _mut67706 = false, _mut67707 = false, _mut67708 = false, _mut67709 = false, _mut67710 = false, _mut67711 = false, _mut67712 = false, _mut67713 = false, _mut67714 = false, _mut67715 = false, _mut67716 = false, _mut67717 = false, _mut67718 = false, _mut67719 = false, _mut67720 = false, _mut67721 = false, _mut67722 = false, _mut67723 = false, _mut67724 = false, _mut67725 = false, _mut67726 = false, _mut67727 = false, _mut67728 = false, _mut67729 = false, _mut67730 = false, _mut67731 = false, _mut67732 = false, _mut67733 = false, _mut67734 = false, _mut67735 = false, _mut67736 = false, _mut67737 = false, _mut67738 = false, _mut67739 = false, _mut67740 = false, _mut67741 = false, _mut67742 = false, _mut67743 = false, _mut67744 = false, _mut67745 = false, _mut67746 = false, _mut67747 = false, _mut67748 = false, _mut67749 = false, _mut67750 = false, _mut67751 = false, _mut67752 = false, _mut67753 = false, _mut67754 = false, _mut67755 = false, _mut67756 = false, _mut67757 = false, _mut67758 = false, _mut67759 = false, _mut67760 = false, _mut67761 = false, _mut67762 = false, _mut67763 = false, _mut67764 = false, _mut67765 = false, _mut67766 = false, _mut67767 = false, _mut67768 = false, _mut67769 = false, _mut67770 = false, _mut67771 = false, _mut67772 = false, _mut67773 = false, _mut67774 = false, _mut67775 = false, _mut67776 = false, _mut67777 = false, _mut67778 = false, _mut67779 = false, _mut67780 = false, _mut67781 = false, _mut67782 = false, _mut67783 = false, _mut67784 = false, _mut67785 = false, _mut67786 = false, _mut67787 = false, _mut67788 = false, _mut67789 = false, _mut67790 = false, _mut67791 = false, _mut67792 = false, _mut67793 = false, _mut67794 = false, _mut67795 = false, _mut67796 = false, _mut67797 = false, _mut67798 = false, _mut67799 = false, _mut67800 = false, _mut67801 = false, _mut67802 = false, _mut67803 = false, _mut67804 = false, _mut67805 = false, _mut67806 = false, _mut67807 = false, _mut67808 = false, _mut67809 = false, _mut67810 = false, _mut67811 = false, _mut67812 = false, _mut67813 = false, _mut67814 = false, _mut67815 = false, _mut67816 = false, _mut67817 = false, _mut67818 = false, _mut67819 = false, _mut67820 = false, _mut67821 = false, _mut67822 = false, _mut67823 = false, _mut67824 = false, _mut67825 = false, _mut67826 = false, _mut67827 = false, _mut67828 = false, _mut67829 = false, _mut67830 = false, _mut67831 = false, _mut67832 = false, _mut67833 = false, _mut67834 = false, _mut67835 = false, _mut67836 = false, _mut67837 = false, _mut67838 = false, _mut67839 = false, _mut67840 = false, _mut67841 = false, _mut67842 = false, _mut67843 = false, _mut67844 = false, _mut67845 = false, _mut67846 = false, _mut67847 = false, _mut67848 = false, _mut67849 = false, _mut67850 = false, _mut67851 = false, _mut67852 = false, _mut67853 = false, _mut67854 = false, _mut67855 = false, _mut67856 = false, _mut67857 = false, _mut67858 = false, _mut67859 = false, _mut67860 = false, _mut67861 = false, _mut67862 = false, _mut67863 = false, _mut67864 = false, _mut67865 = false, _mut67866 = false, _mut67867 = false, _mut67868 = false;

    /**
     * <a href="http://en.wikipedia.org/wiki/Euler-Mascheroni_constant">Euler-Mascheroni constant</a>
     * @since 2.0
     */
    public static final double GAMMA = 0.577215664901532860606512090082;

    /**
     * The value of the {@code g} constant in the Lanczos approximation, see
     * {@link #lanczos(double)}.
     * @since 3.1
     */
    public static final double LANCZOS_G = AOR_divide(607.0, 128.0, "org.apache.commons.math3.special.Gamma.converged_110", _mut66832, _mut66833, _mut66834, _mut66835);

    /**
     * Maximum allowed numerical error.
     */
    private static final double DEFAULT_EPSILON = 10e-15;

    /**
     * Lanczos coefficients
     */
    private static final double[] LANCZOS = { 0.99999999999999709182, 57.156235665862923517, -59.597960355475491248, 14.136097974741747174, -0.49191381609762019978, .33994649984811888699e-4, .46523628927048575665e-4, -.98374475304879564677e-4, .15808870322491248884e-3, -.21026444172410488319e-3, .21743961811521264320e-3, -.16431810653676389022e-3, .84418223983852743293e-4, -.26190838401581408670e-4, .36899182659531622704e-5 };

    /**
     * Avoid repeated computation of log of 2 PI in logGamma
     */
    private static final double HALF_LOG_2_PI = AOR_multiply(0.5, FastMath.log(AOR_multiply(2.0, FastMath.PI, "org.apache.commons.math3.special.Gamma.converged_110", _mut66836, _mut66837, _mut66838, _mut66839)), "org.apache.commons.math3.special.Gamma.converged_110", _mut66840, _mut66841, _mut66842, _mut66843);

    /**
     * The constant value of &radic;(2&pi;).
     */
    private static final double SQRT_TWO_PI = 2.506628274631000502;

    /**
     * C limit.
     */
    private static final double C_LIMIT = 49;

    /**
     * S limit.
     */
    private static final double S_LIMIT = 1e-5;

    /**
     * The constant {@code A0} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_A0 = .611609510448141581788E-08;

    /**
     * The constant {@code A1} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_A1 = .624730830116465516210E-08;

    /**
     * The constant {@code B1} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B1 = .203610414066806987300E+00;

    /**
     * The constant {@code B2} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B2 = .266205348428949217746E-01;

    /**
     * The constant {@code B3} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B3 = .493944979382446875238E-03;

    /**
     * The constant {@code B4} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B4 = -.851419432440314906588E-05;

    /**
     * The constant {@code B5} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B5 = -.643045481779353022248E-05;

    /**
     * The constant {@code B6} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B6 = .992641840672773722196E-06;

    /**
     * The constant {@code B7} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B7 = -.607761895722825260739E-07;

    /**
     * The constant {@code B8} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_B8 = .195755836614639731882E-09;

    /**
     * The constant {@code P0} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P0 = .6116095104481415817861E-08;

    /**
     * The constant {@code P1} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P1 = .6871674113067198736152E-08;

    /**
     * The constant {@code P2} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P2 = .6820161668496170657918E-09;

    /**
     * The constant {@code P3} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P3 = .4686843322948848031080E-10;

    /**
     * The constant {@code P4} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P4 = .1572833027710446286995E-11;

    /**
     * The constant {@code P5} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P5 = -.1249441572276366213222E-12;

    /**
     * The constant {@code P6} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_P6 = .4343529937408594255178E-14;

    /**
     * The constant {@code Q1} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_Q1 = .3056961078365221025009E+00;

    /**
     * The constant {@code Q2} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_Q2 = .5464213086042296536016E-01;

    /**
     * The constant {@code Q3} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_Q3 = .4956830093825887312020E-02;

    /**
     * The constant {@code Q4} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_Q4 = .2692369466186361192876E-03;

    /**
     * The constant {@code C} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C = -.422784335098467139393487909917598E+00;

    /**
     * The constant {@code C0} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C0 = .577215664901532860606512090082402E+00;

    /**
     * The constant {@code C1} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C1 = -.655878071520253881077019515145390E+00;

    /**
     * The constant {@code C2} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C2 = -.420026350340952355290039348754298E-01;

    /**
     * The constant {@code C3} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C3 = .166538611382291489501700795102105E+00;

    /**
     * The constant {@code C4} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C4 = -.421977345555443367482083012891874E-01;

    /**
     * The constant {@code C5} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C5 = -.962197152787697356211492167234820E-02;

    /**
     * The constant {@code C6} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C6 = .721894324666309954239501034044657E-02;

    /**
     * The constant {@code C7} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C7 = -.116516759185906511211397108401839E-02;

    /**
     * The constant {@code C8} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C8 = -.215241674114950972815729963053648E-03;

    /**
     * The constant {@code C9} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C9 = .128050282388116186153198626328164E-03;

    /**
     * The constant {@code C10} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C10 = -.201348547807882386556893914210218E-04;

    /**
     * The constant {@code C11} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C11 = -.125049348214267065734535947383309E-05;

    /**
     * The constant {@code C12} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C12 = .113302723198169588237412962033074E-05;

    /**
     * The constant {@code C13} defined in {@code DGAM1}.
     */
    private static final double INV_GAMMA1P_M1_C13 = -.205633841697760710345015413002057E-06;

    /**
     * Default constructor.  Prohibit instantiation.
     */
    private Gamma() {
    }

    /**
     * <p>
     * Returns the value of log&nbsp;&Gamma;(x) for x&nbsp;&gt;&nbsp;0.
     * </p>
     * <p>
     * For x &le; 8, the implementation is based on the double precision
     * implementation in the <em>NSWC Library of Mathematics Subroutines</em>,
     * {@code DGAMLN}. For x &gt; 8, the implementation is based on
     * </p>
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/GammaFunction.html">Gamma
     *     Function</a>, equation (28).</li>
     * <li><a href="http://mathworld.wolfram.com/LanczosApproximation.html">
     *     Lanczos Approximation</a>, equations (1) through (5).</li>
     * <li><a href="http://my.fit.edu/~gabdo/gamma.txt">Paul Godfrey, A note on
     *     the computation of the convergent Lanczos complex Gamma
     *     approximation</a></li>
     * </ul>
     *
     * @param x Argument.
     * @return the value of {@code log(Gamma(x))}, {@code Double.NaN} if
     * {@code x <= 0.0}.
     */
    public static double logGamma(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.logGamma_243");
        double ret;
        if ((_mut66849 ? (Double.isNaN(x) && (ROR_less_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66844, _mut66845, _mut66846, _mut66847, _mut66848))) : (Double.isNaN(x) || (ROR_less_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66844, _mut66845, _mut66846, _mut66847, _mut66848))))) {
            ret = Double.NaN;
        } else if (ROR_less(x, 0.5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66850, _mut66851, _mut66852, _mut66853, _mut66854)) {
            return AOR_minus(logGamma1p(x), FastMath.log(x), "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66930, _mut66931, _mut66932, _mut66933);
        } else if (ROR_less_equals(x, 2.5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66855, _mut66856, _mut66857, _mut66858, _mut66859)) {
            return logGamma1p(AOR_minus((AOR_minus(x, 0.5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66922, _mut66923, _mut66924, _mut66925)), 0.5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66926, _mut66927, _mut66928, _mut66929));
        } else if (ROR_less_equals(x, 8.0, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66860, _mut66861, _mut66862, _mut66863, _mut66864)) {
            final int n = (int) FastMath.floor(AOR_minus(x, 1.5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66897, _mut66898, _mut66899, _mut66900));
            double prod = 1.0;
            for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66905, _mut66906, _mut66907, _mut66908, _mut66909); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.logGamma_243");
                prod *= AOR_minus(x, i, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66901, _mut66902, _mut66903, _mut66904);
            }
            return AOR_plus(logGamma1p(AOR_minus(x, (AOR_plus(n, 1, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66910, _mut66911, _mut66912, _mut66913)), "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66914, _mut66915, _mut66916, _mut66917)), FastMath.log(prod), "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66918, _mut66919, _mut66920, _mut66921);
        } else {
            double sum = lanczos(x);
            double tmp = AOR_plus(AOR_plus(x, LANCZOS_G, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66865, _mut66866, _mut66867, _mut66868), .5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66869, _mut66870, _mut66871, _mut66872);
            ret = AOR_plus(AOR_plus(AOR_minus((AOR_multiply((AOR_plus(x, .5, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66873, _mut66874, _mut66875, _mut66876)), FastMath.log(tmp), "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66877, _mut66878, _mut66879, _mut66880)), tmp, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66881, _mut66882, _mut66883, _mut66884), HALF_LOG_2_PI, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66885, _mut66886, _mut66887, _mut66888), FastMath.log(AOR_divide(sum, x, "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66889, _mut66890, _mut66891, _mut66892)), "org.apache.commons.math3.special.Gamma.logGamma_243", _mut66893, _mut66894, _mut66895, _mut66896);
        }
        return ret;
    }

    /**
     * Returns the regularized gamma function P(a, x).
     *
     * @param a Parameter.
     * @param x Value.
     * @return the regularized gamma function P(a, x).
     * @throws MaxCountExceededException if the algorithm fails to converge.
     */
    public static double regularizedGammaP(double a, double x) {
        return regularizedGammaP(a, x, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    /**
     * Returns the regularized gamma function P(a, x).
     *
     * The implementation of this method is based on:
     * <ul>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/RegularizedGammaFunction.html">
     *   Regularized Gamma Function</a>, equation (1)
     *  </li>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/IncompleteGammaFunction.html">
     *   Incomplete Gamma Function</a>, equation (4).
     *  </li>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/ConfluentHypergeometricFunctionoftheFirstKind.html">
     *   Confluent Hypergeometric Function of the First Kind</a>, equation (1).
     *  </li>
     * </ul>
     *
     * @param a the a parameter.
     * @param x the value.
     * @param epsilon When the absolute value of the nth item in the
     * series is less than epsilon the approximation ceases to calculate
     * further elements in the series.
     * @param maxIterations Maximum number of "iterations" to complete.
     * @return the regularized gamma function P(a, x)
     * @throws MaxCountExceededException if the algorithm fails to converge.
     */
    public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.regularizedGammaP_309");
        double ret;
        if ((_mut66946 ? ((_mut66940 ? ((_mut66934 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) && (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66935, _mut66936, _mut66937, _mut66938, _mut66939))) : ((_mut66934 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) || (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66935, _mut66936, _mut66937, _mut66938, _mut66939)))) && (ROR_less(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66941, _mut66942, _mut66943, _mut66944, _mut66945))) : ((_mut66940 ? ((_mut66934 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) && (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66935, _mut66936, _mut66937, _mut66938, _mut66939))) : ((_mut66934 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) || (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66935, _mut66936, _mut66937, _mut66938, _mut66939)))) || (ROR_less(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66941, _mut66942, _mut66943, _mut66944, _mut66945))))) {
            ret = Double.NaN;
        } else if (ROR_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66947, _mut66948, _mut66949, _mut66950, _mut66951)) {
            ret = 0.0;
        } else if (ROR_greater_equals(x, AOR_plus(a, 1, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66952, _mut66953, _mut66954, _mut66955), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66956, _mut66957, _mut66958, _mut66959, _mut66960)) {
            // case.
            ret = AOR_minus(1.0, regularizedGammaQ(a, x, epsilon, maxIterations), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut67015, _mut67016, _mut67017, _mut67018);
        } else {
            // current element index
            double n = 0.0;
            // n-th element in the series
            double an = AOR_divide(1.0, a, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66961, _mut66962, _mut66963, _mut66964);
            // partial sum
            double sum = an;
            while ((_mut66993 ? ((_mut66987 ? (ROR_greater(FastMath.abs(AOR_divide(an, sum, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66973, _mut66974, _mut66975, _mut66976)), epsilon, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66977, _mut66978, _mut66979, _mut66980, _mut66981) || ROR_less(n, maxIterations, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66982, _mut66983, _mut66984, _mut66985, _mut66986)) : (ROR_greater(FastMath.abs(AOR_divide(an, sum, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66973, _mut66974, _mut66975, _mut66976)), epsilon, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66977, _mut66978, _mut66979, _mut66980, _mut66981) && ROR_less(n, maxIterations, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66982, _mut66983, _mut66984, _mut66985, _mut66986))) || ROR_less(sum, Double.POSITIVE_INFINITY, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66988, _mut66989, _mut66990, _mut66991, _mut66992)) : ((_mut66987 ? (ROR_greater(FastMath.abs(AOR_divide(an, sum, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66973, _mut66974, _mut66975, _mut66976)), epsilon, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66977, _mut66978, _mut66979, _mut66980, _mut66981) || ROR_less(n, maxIterations, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66982, _mut66983, _mut66984, _mut66985, _mut66986)) : (ROR_greater(FastMath.abs(AOR_divide(an, sum, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66973, _mut66974, _mut66975, _mut66976)), epsilon, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66977, _mut66978, _mut66979, _mut66980, _mut66981) && ROR_less(n, maxIterations, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66982, _mut66983, _mut66984, _mut66985, _mut66986))) && ROR_less(sum, Double.POSITIVE_INFINITY, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66988, _mut66989, _mut66990, _mut66991, _mut66992)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.regularizedGammaP_309");
                // compute next element in the series
                n += 1.0;
                an *= AOR_divide(x, (AOR_plus(a, n, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66965, _mut66966, _mut66967, _mut66968)), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66969, _mut66970, _mut66971, _mut66972);
                // update partial sum
                sum += an;
            }
            if (ROR_greater_equals(n, maxIterations, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66994, _mut66995, _mut66996, _mut66997, _mut66998)) {
                throw new MaxCountExceededException(maxIterations);
            } else if (Double.isInfinite(sum)) {
                ret = 1.0;
            } else {
                ret = AOR_multiply(FastMath.exp(AOR_minus(AOR_plus(-x, (AOR_multiply(a, FastMath.log(x), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut66999, _mut67000, _mut67001, _mut67002)), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut67003, _mut67004, _mut67005, _mut67006), logGamma(a), "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut67007, _mut67008, _mut67009, _mut67010)), sum, "org.apache.commons.math3.special.Gamma.regularizedGammaP_309", _mut67011, _mut67012, _mut67013, _mut67014);
            }
        }
        return ret;
    }

    /**
     * Returns the regularized gamma function Q(a, x) = 1 - P(a, x).
     *
     * @param a the a parameter.
     * @param x the value.
     * @return the regularized gamma function Q(a, x)
     * @throws MaxCountExceededException if the algorithm fails to converge.
     */
    public static double regularizedGammaQ(double a, double x) {
        return regularizedGammaQ(a, x, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    /**
     * Returns the regularized gamma function Q(a, x) = 1 - P(a, x).
     *
     * The implementation of this method is based on:
     * <ul>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/RegularizedGammaFunction.html">
     *   Regularized Gamma Function</a>, equation (1).
     *  </li>
     *  <li>
     *   <a href="http://functions.wolfram.com/GammaBetaErf/GammaRegularized/10/0003/">
     *   Regularized incomplete gamma function: Continued fraction representations
     *   (formula 06.08.10.0003)</a>
     *  </li>
     * </ul>
     *
     * @param a the a parameter.
     * @param x the value.
     * @param epsilon When the absolute value of the nth item in the
     * series is less than epsilon the approximation ceases to calculate
     * further elements in the series.
     * @param maxIterations Maximum number of "iterations" to complete.
     * @return the regularized gamma function P(a, x)
     * @throws MaxCountExceededException if the algorithm fails to converge.
     */
    public static double regularizedGammaQ(final double a, double x, double epsilon, int maxIterations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.getB_412");
        double ret;
        if ((_mut67031 ? ((_mut67025 ? ((_mut67019 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) && (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67020, _mut67021, _mut67022, _mut67023, _mut67024))) : ((_mut67019 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) || (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67020, _mut67021, _mut67022, _mut67023, _mut67024)))) && (ROR_less(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67026, _mut67027, _mut67028, _mut67029, _mut67030))) : ((_mut67025 ? ((_mut67019 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) && (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67020, _mut67021, _mut67022, _mut67023, _mut67024))) : ((_mut67019 ? (Double.isNaN(a) && Double.isNaN(x)) : (Double.isNaN(a) || Double.isNaN(x))) || (ROR_less_equals(a, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67020, _mut67021, _mut67022, _mut67023, _mut67024)))) || (ROR_less(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67026, _mut67027, _mut67028, _mut67029, _mut67030))))) {
            ret = Double.NaN;
        } else if (ROR_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67032, _mut67033, _mut67034, _mut67035, _mut67036)) {
            ret = 1.0;
        } else if (ROR_less(x, AOR_plus(a, 1.0, "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67037, _mut67038, _mut67039, _mut67040), "org.apache.commons.math3.special.Gamma.regularizedGammaQ_387", _mut67041, _mut67042, _mut67043, _mut67044, _mut67045)) {
            // case.
            ret = AOR_minus(1.0, regularizedGammaP(a, x, epsilon, maxIterations), "org.apache.commons.math3.special.Gamma.getB_412", _mut67090, _mut67091, _mut67092, _mut67093);
        } else {
            // create continued fraction
            ContinuedFraction cf = new ContinuedFraction() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected double getA(int n, double x) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.getA_406");
                    return AOR_plus(AOR_minus((AOR_plus((AOR_multiply(2.0, n, "org.apache.commons.math3.special.Gamma.getA_406", _mut67046, _mut67047, _mut67048, _mut67049)), 1.0, "org.apache.commons.math3.special.Gamma.getA_406", _mut67050, _mut67051, _mut67052, _mut67053)), a, "org.apache.commons.math3.special.Gamma.getA_406", _mut67054, _mut67055, _mut67056, _mut67057), x, "org.apache.commons.math3.special.Gamma.getA_406", _mut67058, _mut67059, _mut67060, _mut67061);
                }

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected double getB(int n, double x) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.getB_412");
                    return AOR_multiply(n, (AOR_minus(a, n, "org.apache.commons.math3.special.Gamma.getB_412", _mut67062, _mut67063, _mut67064, _mut67065)), "org.apache.commons.math3.special.Gamma.getB_412", _mut67066, _mut67067, _mut67068, _mut67069);
                }
            };
            ret = AOR_divide(1.0, cf.evaluate(x, epsilon, maxIterations), "org.apache.commons.math3.special.Gamma.getB_412", _mut67070, _mut67071, _mut67072, _mut67073);
            ret = AOR_multiply(FastMath.exp(AOR_minus(AOR_plus(-x, (AOR_multiply(a, FastMath.log(x), "org.apache.commons.math3.special.Gamma.getB_412", _mut67074, _mut67075, _mut67076, _mut67077)), "org.apache.commons.math3.special.Gamma.getB_412", _mut67078, _mut67079, _mut67080, _mut67081), logGamma(a), "org.apache.commons.math3.special.Gamma.getB_412", _mut67082, _mut67083, _mut67084, _mut67085)), ret, "org.apache.commons.math3.special.Gamma.getB_412", _mut67086, _mut67087, _mut67088, _mut67089);
        }
        return ret;
    }

    /**
     * <p>Computes the digamma function of x.</p>
     *
     * <p>This is an independently written implementation of the algorithm described in
     * Jose Bernardo, Algorithm AS 103: Psi (Digamma) Function, Applied Statistics, 1976.</p>
     *
     * <p>Some of the constants have been changed to increase accuracy at the moderate expense
     * of run-time.  The result should be accurate to within 10^-8 absolute tolerance for
     * x >= 10^-5 and within 10^-8 relative tolerance for x > 0.</p>
     *
     * <p>Performance for large negative values of x will be quite expensive (proportional to
     * |x|).  Accuracy for negative values of x should be about 10^-8 absolute for results
     * less than 10^5 and 10^-8 relative for results larger than that.</p>
     *
     * @param x Argument.
     * @return digamma(x) to within 10-8 relative or absolute error whichever is smaller.
     * @see <a href="http://en.wikipedia.org/wiki/Digamma_function">Digamma</a>
     * @see <a href="http://www.uv.es/~bernardo/1976AppStatist.pdf">Bernardo&apos;s original article </a>
     * @since 2.0
     */
    public static double digamma(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.digamma_446");
        if ((_mut67094 ? (Double.isNaN(x) && Double.isInfinite(x)) : (Double.isNaN(x) || Double.isInfinite(x)))) {
            return x;
        }
        if ((_mut67105 ? (ROR_greater(x, 0, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67095, _mut67096, _mut67097, _mut67098, _mut67099) || ROR_less_equals(x, S_LIMIT, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67100, _mut67101, _mut67102, _mut67103, _mut67104)) : (ROR_greater(x, 0, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67095, _mut67096, _mut67097, _mut67098, _mut67099) && ROR_less_equals(x, S_LIMIT, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67100, _mut67101, _mut67102, _mut67103, _mut67104)))) {
            // accurate to O(x)
            return AOR_minus(-GAMMA, AOR_divide(1, x, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67106, _mut67107, _mut67108, _mut67109), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67110, _mut67111, _mut67112, _mut67113);
        }
        if (ROR_greater_equals(x, C_LIMIT, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67114, _mut67115, _mut67116, _mut67117, _mut67118)) {
            // use method 4 (accurate to O(1/x^8)
            double inv = AOR_divide(1, (AOR_multiply(x, x, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67119, _mut67120, _mut67121, _mut67122)), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67123, _mut67124, _mut67125, _mut67126);
            // 2 x   12 x^2   120 x^4   252 x^6
            return AOR_minus(AOR_minus(FastMath.log(x), AOR_divide(0.5, x, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67127, _mut67128, _mut67129, _mut67130), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67131, _mut67132, _mut67133, _mut67134), AOR_multiply(inv, (AOR_plus((AOR_divide(1.0, 12, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67135, _mut67136, _mut67137, _mut67138)), AOR_multiply(inv, (AOR_minus(AOR_divide(1.0, 120, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67139, _mut67140, _mut67141, _mut67142), AOR_divide(inv, 252, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67143, _mut67144, _mut67145, _mut67146), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67147, _mut67148, _mut67149, _mut67150)), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67151, _mut67152, _mut67153, _mut67154), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67155, _mut67156, _mut67157, _mut67158)), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67159, _mut67160, _mut67161, _mut67162), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67163, _mut67164, _mut67165, _mut67166);
        }
        return AOR_minus(digamma(AOR_plus(x, 1, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67167, _mut67168, _mut67169, _mut67170)), AOR_divide(1, x, "org.apache.commons.math3.special.Gamma.digamma_446", _mut67171, _mut67172, _mut67173, _mut67174), "org.apache.commons.math3.special.Gamma.digamma_446", _mut67175, _mut67176, _mut67177, _mut67178);
    }

    /**
     * Computes the trigamma function of x.
     * This function is derived by taking the derivative of the implementation
     * of digamma.
     *
     * @param x Argument.
     * @return trigamma(x) to within 10-8 relative or absolute error whichever is smaller
     * @see <a href="http://en.wikipedia.org/wiki/Trigamma_function">Trigamma</a>
     * @see Gamma#digamma(double)
     * @since 2.0
     */
    public static double trigamma(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.trigamma_480");
        if ((_mut67179 ? (Double.isNaN(x) && Double.isInfinite(x)) : (Double.isNaN(x) || Double.isInfinite(x)))) {
            return x;
        }
        if ((_mut67190 ? (ROR_greater(x, 0, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67180, _mut67181, _mut67182, _mut67183, _mut67184) || ROR_less_equals(x, S_LIMIT, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67185, _mut67186, _mut67187, _mut67188, _mut67189)) : (ROR_greater(x, 0, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67180, _mut67181, _mut67182, _mut67183, _mut67184) && ROR_less_equals(x, S_LIMIT, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67185, _mut67186, _mut67187, _mut67188, _mut67189)))) {
            return AOR_divide(1, (AOR_multiply(x, x, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67191, _mut67192, _mut67193, _mut67194)), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67195, _mut67196, _mut67197, _mut67198);
        }
        if (ROR_greater_equals(x, C_LIMIT, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67199, _mut67200, _mut67201, _mut67202, _mut67203)) {
            double inv = AOR_divide(1, (AOR_multiply(x, x, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67204, _mut67205, _mut67206, _mut67207)), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67208, _mut67209, _mut67210, _mut67211);
            // 2 x    6 x    30 x    42 x
            return AOR_plus(AOR_plus(AOR_divide(1, x, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67212, _mut67213, _mut67214, _mut67215), AOR_divide(inv, 2, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67216, _mut67217, _mut67218, _mut67219), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67220, _mut67221, _mut67222, _mut67223), AOR_multiply(AOR_divide(inv, x, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67224, _mut67225, _mut67226, _mut67227), (AOR_minus(AOR_divide(1.0, 6, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67228, _mut67229, _mut67230, _mut67231), AOR_multiply(inv, (AOR_plus(AOR_divide(1.0, 30, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67232, _mut67233, _mut67234, _mut67235), AOR_divide(inv, 42, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67236, _mut67237, _mut67238, _mut67239), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67240, _mut67241, _mut67242, _mut67243)), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67244, _mut67245, _mut67246, _mut67247), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67248, _mut67249, _mut67250, _mut67251)), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67252, _mut67253, _mut67254, _mut67255), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67256, _mut67257, _mut67258, _mut67259);
        }
        return AOR_plus(trigamma(AOR_plus(x, 1, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67260, _mut67261, _mut67262, _mut67263)), AOR_divide(1, (AOR_multiply(x, x, "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67264, _mut67265, _mut67266, _mut67267)), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67268, _mut67269, _mut67270, _mut67271), "org.apache.commons.math3.special.Gamma.trigamma_480", _mut67272, _mut67273, _mut67274, _mut67275);
    }

    /**
     * <p>
     * Returns the Lanczos approximation used to compute the gamma function.
     * The Lanczos approximation is related to the Gamma function by the
     * following equation
     * <center>
     * {@code gamma(x) = sqrt(2 * pi) / x * (x + g + 0.5) ^ (x + 0.5)
     *                   * exp(-x - g - 0.5) * lanczos(x)},
     * </center>
     * where {@code g} is the Lanczos constant.
     * </p>
     *
     * @param x Argument.
     * @return The Lanczos approximation.
     * @see <a href="http://mathworld.wolfram.com/LanczosApproximation.html">Lanczos Approximation</a>
     * equations (1) through (5), and Paul Godfrey's
     * <a href="http://my.fit.edu/~gabdo/gamma.txt">Note on the computation
     * of the convergent Lanczos complex Gamma approximation</a>
     * @since 3.1
     */
    public static double lanczos(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.lanczos_521");
        double sum = 0.0;
        for (int i = LANCZOS.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.special.Gamma.lanczos_521", _mut67284, _mut67285, _mut67286, _mut67287, _mut67288); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.lanczos_521");
            sum += AOR_divide(LANCZOS[i], (AOR_plus(x, i, "org.apache.commons.math3.special.Gamma.lanczos_521", _mut67276, _mut67277, _mut67278, _mut67279)), "org.apache.commons.math3.special.Gamma.lanczos_521", _mut67280, _mut67281, _mut67282, _mut67283);
        }
        return AOR_plus(sum, LANCZOS[0], "org.apache.commons.math3.special.Gamma.lanczos_521", _mut67289, _mut67290, _mut67291, _mut67292);
    }

    /**
     * Returns the value of 1 / &Gamma;(1 + x) - 1 for -0&#46;5 &le; x &le;
     * 1&#46;5. This implementation is based on the double precision
     * implementation in the <em>NSWC Library of Mathematics Subroutines</em>,
     * {@code DGAM1}.
     *
     * @param x Argument.
     * @return The value of {@code 1.0 / Gamma(1.0 + x) - 1.0}.
     * @throws NumberIsTooSmallException if {@code x < -0.5}
     * @throws NumberIsTooLargeException if {@code x > 1.5}
     * @since 3.1
     */
    public static double invGamma1pm1(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.invGamma1pm1_541");
        if (ROR_less(x, -0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67293, _mut67294, _mut67295, _mut67296, _mut67297)) {
            throw new NumberIsTooSmallException(x, -0.5, true);
        }
        if (ROR_greater(x, 1.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67298, _mut67299, _mut67300, _mut67301, _mut67302)) {
            throw new NumberIsTooLargeException(x, 1.5, true);
        }
        final double ret;
        final double t = ROR_less_equals(x, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67303, _mut67304, _mut67305, _mut67306, _mut67307) ? x : AOR_minus((AOR_minus(x, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67308, _mut67309, _mut67310, _mut67311)), 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67312, _mut67313, _mut67314, _mut67315);
        if (ROR_less(t, 0.0, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67316, _mut67317, _mut67318, _mut67319, _mut67320)) {
            final double a = AOR_plus(INV_GAMMA1P_M1_A0, AOR_multiply(t, INV_GAMMA1P_M1_A1, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67542, _mut67543, _mut67544, _mut67545), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67546, _mut67547, _mut67548, _mut67549);
            double b = INV_GAMMA1P_M1_B8;
            b = AOR_plus(INV_GAMMA1P_M1_B7, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67550, _mut67551, _mut67552, _mut67553), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67554, _mut67555, _mut67556, _mut67557);
            b = AOR_plus(INV_GAMMA1P_M1_B6, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67558, _mut67559, _mut67560, _mut67561), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67562, _mut67563, _mut67564, _mut67565);
            b = AOR_plus(INV_GAMMA1P_M1_B5, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67566, _mut67567, _mut67568, _mut67569), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67570, _mut67571, _mut67572, _mut67573);
            b = AOR_plus(INV_GAMMA1P_M1_B4, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67574, _mut67575, _mut67576, _mut67577), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67578, _mut67579, _mut67580, _mut67581);
            b = AOR_plus(INV_GAMMA1P_M1_B3, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67582, _mut67583, _mut67584, _mut67585), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67586, _mut67587, _mut67588, _mut67589);
            b = AOR_plus(INV_GAMMA1P_M1_B2, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67590, _mut67591, _mut67592, _mut67593), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67594, _mut67595, _mut67596, _mut67597);
            b = AOR_plus(INV_GAMMA1P_M1_B1, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67598, _mut67599, _mut67600, _mut67601), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67602, _mut67603, _mut67604, _mut67605);
            b = AOR_plus(1.0, AOR_multiply(t, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67606, _mut67607, _mut67608, _mut67609), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67610, _mut67611, _mut67612, _mut67613);
            double c = AOR_plus(INV_GAMMA1P_M1_C13, AOR_multiply(t, (AOR_divide(a, b, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67614, _mut67615, _mut67616, _mut67617)), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67618, _mut67619, _mut67620, _mut67621), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67622, _mut67623, _mut67624, _mut67625);
            c = AOR_plus(INV_GAMMA1P_M1_C12, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67626, _mut67627, _mut67628, _mut67629), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67630, _mut67631, _mut67632, _mut67633);
            c = AOR_plus(INV_GAMMA1P_M1_C11, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67634, _mut67635, _mut67636, _mut67637), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67638, _mut67639, _mut67640, _mut67641);
            c = AOR_plus(INV_GAMMA1P_M1_C10, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67642, _mut67643, _mut67644, _mut67645), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67646, _mut67647, _mut67648, _mut67649);
            c = AOR_plus(INV_GAMMA1P_M1_C9, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67650, _mut67651, _mut67652, _mut67653), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67654, _mut67655, _mut67656, _mut67657);
            c = AOR_plus(INV_GAMMA1P_M1_C8, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67658, _mut67659, _mut67660, _mut67661), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67662, _mut67663, _mut67664, _mut67665);
            c = AOR_plus(INV_GAMMA1P_M1_C7, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67666, _mut67667, _mut67668, _mut67669), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67670, _mut67671, _mut67672, _mut67673);
            c = AOR_plus(INV_GAMMA1P_M1_C6, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67674, _mut67675, _mut67676, _mut67677), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67678, _mut67679, _mut67680, _mut67681);
            c = AOR_plus(INV_GAMMA1P_M1_C5, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67682, _mut67683, _mut67684, _mut67685), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67686, _mut67687, _mut67688, _mut67689);
            c = AOR_plus(INV_GAMMA1P_M1_C4, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67690, _mut67691, _mut67692, _mut67693), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67694, _mut67695, _mut67696, _mut67697);
            c = AOR_plus(INV_GAMMA1P_M1_C3, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67698, _mut67699, _mut67700, _mut67701), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67702, _mut67703, _mut67704, _mut67705);
            c = AOR_plus(INV_GAMMA1P_M1_C2, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67706, _mut67707, _mut67708, _mut67709), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67710, _mut67711, _mut67712, _mut67713);
            c = AOR_plus(INV_GAMMA1P_M1_C1, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67714, _mut67715, _mut67716, _mut67717), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67718, _mut67719, _mut67720, _mut67721);
            c = AOR_plus(INV_GAMMA1P_M1_C, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67722, _mut67723, _mut67724, _mut67725), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67726, _mut67727, _mut67728, _mut67729);
            if (ROR_greater(x, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67730, _mut67731, _mut67732, _mut67733, _mut67734)) {
                ret = AOR_divide(AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67747, _mut67748, _mut67749, _mut67750), x, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67751, _mut67752, _mut67753, _mut67754);
            } else {
                ret = AOR_multiply(x, (AOR_plus((AOR_plus(c, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67735, _mut67736, _mut67737, _mut67738)), 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67739, _mut67740, _mut67741, _mut67742)), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67743, _mut67744, _mut67745, _mut67746);
            }
        } else {
            double p = INV_GAMMA1P_M1_P6;
            p = AOR_plus(INV_GAMMA1P_M1_P5, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67321, _mut67322, _mut67323, _mut67324), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67325, _mut67326, _mut67327, _mut67328);
            p = AOR_plus(INV_GAMMA1P_M1_P4, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67329, _mut67330, _mut67331, _mut67332), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67333, _mut67334, _mut67335, _mut67336);
            p = AOR_plus(INV_GAMMA1P_M1_P3, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67337, _mut67338, _mut67339, _mut67340), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67341, _mut67342, _mut67343, _mut67344);
            p = AOR_plus(INV_GAMMA1P_M1_P2, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67345, _mut67346, _mut67347, _mut67348), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67349, _mut67350, _mut67351, _mut67352);
            p = AOR_plus(INV_GAMMA1P_M1_P1, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67353, _mut67354, _mut67355, _mut67356), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67357, _mut67358, _mut67359, _mut67360);
            p = AOR_plus(INV_GAMMA1P_M1_P0, AOR_multiply(t, p, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67361, _mut67362, _mut67363, _mut67364), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67365, _mut67366, _mut67367, _mut67368);
            double q = INV_GAMMA1P_M1_Q4;
            q = AOR_plus(INV_GAMMA1P_M1_Q3, AOR_multiply(t, q, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67369, _mut67370, _mut67371, _mut67372), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67373, _mut67374, _mut67375, _mut67376);
            q = AOR_plus(INV_GAMMA1P_M1_Q2, AOR_multiply(t, q, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67377, _mut67378, _mut67379, _mut67380), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67381, _mut67382, _mut67383, _mut67384);
            q = AOR_plus(INV_GAMMA1P_M1_Q1, AOR_multiply(t, q, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67385, _mut67386, _mut67387, _mut67388), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67389, _mut67390, _mut67391, _mut67392);
            q = AOR_plus(1.0, AOR_multiply(t, q, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67393, _mut67394, _mut67395, _mut67396), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67397, _mut67398, _mut67399, _mut67400);
            double c = AOR_plus(INV_GAMMA1P_M1_C13, AOR_multiply((AOR_divide(p, q, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67401, _mut67402, _mut67403, _mut67404)), t, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67405, _mut67406, _mut67407, _mut67408), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67409, _mut67410, _mut67411, _mut67412);
            c = AOR_plus(INV_GAMMA1P_M1_C12, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67413, _mut67414, _mut67415, _mut67416), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67417, _mut67418, _mut67419, _mut67420);
            c = AOR_plus(INV_GAMMA1P_M1_C11, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67421, _mut67422, _mut67423, _mut67424), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67425, _mut67426, _mut67427, _mut67428);
            c = AOR_plus(INV_GAMMA1P_M1_C10, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67429, _mut67430, _mut67431, _mut67432), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67433, _mut67434, _mut67435, _mut67436);
            c = AOR_plus(INV_GAMMA1P_M1_C9, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67437, _mut67438, _mut67439, _mut67440), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67441, _mut67442, _mut67443, _mut67444);
            c = AOR_plus(INV_GAMMA1P_M1_C8, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67445, _mut67446, _mut67447, _mut67448), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67449, _mut67450, _mut67451, _mut67452);
            c = AOR_plus(INV_GAMMA1P_M1_C7, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67453, _mut67454, _mut67455, _mut67456), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67457, _mut67458, _mut67459, _mut67460);
            c = AOR_plus(INV_GAMMA1P_M1_C6, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67461, _mut67462, _mut67463, _mut67464), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67465, _mut67466, _mut67467, _mut67468);
            c = AOR_plus(INV_GAMMA1P_M1_C5, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67469, _mut67470, _mut67471, _mut67472), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67473, _mut67474, _mut67475, _mut67476);
            c = AOR_plus(INV_GAMMA1P_M1_C4, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67477, _mut67478, _mut67479, _mut67480), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67481, _mut67482, _mut67483, _mut67484);
            c = AOR_plus(INV_GAMMA1P_M1_C3, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67485, _mut67486, _mut67487, _mut67488), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67489, _mut67490, _mut67491, _mut67492);
            c = AOR_plus(INV_GAMMA1P_M1_C2, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67493, _mut67494, _mut67495, _mut67496), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67497, _mut67498, _mut67499, _mut67500);
            c = AOR_plus(INV_GAMMA1P_M1_C1, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67501, _mut67502, _mut67503, _mut67504), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67505, _mut67506, _mut67507, _mut67508);
            c = AOR_plus(INV_GAMMA1P_M1_C0, AOR_multiply(t, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67509, _mut67510, _mut67511, _mut67512), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67513, _mut67514, _mut67515, _mut67516);
            if (ROR_greater(x, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67517, _mut67518, _mut67519, _mut67520, _mut67521)) {
                ret = AOR_multiply((AOR_divide(t, x, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67526, _mut67527, _mut67528, _mut67529)), (AOR_minus((AOR_minus(c, 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67530, _mut67531, _mut67532, _mut67533)), 0.5, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67534, _mut67535, _mut67536, _mut67537)), "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67538, _mut67539, _mut67540, _mut67541);
            } else {
                ret = AOR_multiply(x, c, "org.apache.commons.math3.special.Gamma.invGamma1pm1_541", _mut67522, _mut67523, _mut67524, _mut67525);
            }
        }
        return ret;
    }

    /**
     * Returns the value of log &Gamma;(1 + x) for -0&#46;5 &le; x &le; 1&#46;5.
     * This implementation is based on the double precision implementation in
     * the <em>NSWC Library of Mathematics Subroutines</em>, {@code DGMLN1}.
     *
     * @param x Argument.
     * @return The value of {@code log(Gamma(1 + x))}.
     * @throws NumberIsTooSmallException if {@code x < -0.5}.
     * @throws NumberIsTooLargeException if {@code x > 1.5}.
     * @since 3.1
     */
    public static double logGamma1p(final double x) throws NumberIsTooSmallException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.logGamma1p_634");
        if (ROR_less(x, -0.5, "org.apache.commons.math3.special.Gamma.logGamma1p_634", _mut67755, _mut67756, _mut67757, _mut67758, _mut67759)) {
            throw new NumberIsTooSmallException(x, -0.5, true);
        }
        if (ROR_greater(x, 1.5, "org.apache.commons.math3.special.Gamma.logGamma1p_634", _mut67760, _mut67761, _mut67762, _mut67763, _mut67764)) {
            throw new NumberIsTooLargeException(x, 1.5, true);
        }
        return -FastMath.log1p(invGamma1pm1(x));
    }

    /**
     * Returns the value of Γ(x). Based on the <em>NSWC Library of
     * Mathematics Subroutines</em> double precision implementation,
     * {@code DGAMMA}.
     *
     * @param x Argument.
     * @return the value of {@code Gamma(x)}.
     * @since 3.1
     */
    public static double gamma(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.gamma_657");
        if ((_mut67775 ? ((ROR_equals(x, FastMath.rint(x), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67765, _mut67766, _mut67767, _mut67768, _mut67769)) || (ROR_less_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67770, _mut67771, _mut67772, _mut67773, _mut67774))) : ((ROR_equals(x, FastMath.rint(x), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67765, _mut67766, _mut67767, _mut67768, _mut67769)) && (ROR_less_equals(x, 0.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67770, _mut67771, _mut67772, _mut67773, _mut67774))))) {
            return Double.NaN;
        }
        final double ret;
        final double absX = FastMath.abs(x);
        if (ROR_less_equals(absX, 20.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67776, _mut67777, _mut67778, _mut67779, _mut67780)) {
            if (ROR_greater_equals(x, 1.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67830, _mut67831, _mut67832, _mut67833, _mut67834)) {
                /*
                 * From the recurrence relation
                 * Gamma(x) = (x - 1) * ... * (x - n) * Gamma(x - n),
                 * then
                 * Gamma(t) = 1 / [1 + invGamma1pm1(t - 1)],
                 * where t = x - n. This means that t must satisfy
                 * -0.5 <= t - 1 <= 1.5.
                 */
                double prod = 1.0;
                double t = x;
                while (ROR_greater(t, 2.5, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67852, _mut67853, _mut67854, _mut67855, _mut67856)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.gamma_657");
                    t -= 1.0;
                    prod *= t;
                }
                ret = AOR_divide(prod, (AOR_plus(1.0, invGamma1pm1(AOR_minus(t, 1.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67857, _mut67858, _mut67859, _mut67860)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67861, _mut67862, _mut67863, _mut67864)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67865, _mut67866, _mut67867, _mut67868);
            } else {
                /*
                 * From the recurrence relation
                 * Gamma(x) = Gamma(x + n + 1) / [x * (x + 1) * ... * (x + n)]
                 * then
                 * Gamma(x + n + 1) = 1 / [1 + invGamma1pm1(x + n)],
                 * which requires -0.5 <= x + n <= 1.5.
                 */
                double prod = x;
                double t = x;
                while (ROR_less(t, -0.5, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67835, _mut67836, _mut67837, _mut67838, _mut67839)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Gamma.gamma_657");
                    t += 1.0;
                    prod *= t;
                }
                ret = AOR_divide(1.0, (AOR_multiply(prod, (AOR_plus(1.0, invGamma1pm1(t), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67840, _mut67841, _mut67842, _mut67843)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67844, _mut67845, _mut67846, _mut67847)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67848, _mut67849, _mut67850, _mut67851);
            }
        } else {
            final double y = AOR_plus(AOR_plus(absX, LANCZOS_G, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67781, _mut67782, _mut67783, _mut67784), 0.5, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67785, _mut67786, _mut67787, _mut67788);
            final double gammaAbs = AOR_multiply(AOR_multiply(AOR_multiply(AOR_divide(SQRT_TWO_PI, absX, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67789, _mut67790, _mut67791, _mut67792), FastMath.pow(y, AOR_plus(absX, 0.5, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67793, _mut67794, _mut67795, _mut67796)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67797, _mut67798, _mut67799, _mut67800), FastMath.exp(-y), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67801, _mut67802, _mut67803, _mut67804), lanczos(absX), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67805, _mut67806, _mut67807, _mut67808);
            if (ROR_greater(x, 0.0, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67809, _mut67810, _mut67811, _mut67812, _mut67813)) {
                ret = gammaAbs;
            } else {
                /*
                 * From the reflection formula
                 * Gamma(x) * Gamma(1 - x) * sin(pi * x) = pi,
                 * and the recurrence relation
                 * Gamma(1 - x) = -x * Gamma(-x),
                 * it is found
                 * Gamma(x) = -pi / [x * sin(pi * x) * Gamma(-x)].
                 */
                ret = AOR_divide(-FastMath.PI, (AOR_multiply(AOR_multiply(x, FastMath.sin(AOR_multiply(FastMath.PI, x, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67814, _mut67815, _mut67816, _mut67817)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67818, _mut67819, _mut67820, _mut67821), gammaAbs, "org.apache.commons.math3.special.Gamma.gamma_657", _mut67822, _mut67823, _mut67824, _mut67825)), "org.apache.commons.math3.special.Gamma.gamma_657", _mut67826, _mut67827, _mut67828, _mut67829);
            }
        }
        return ret;
    }
}
