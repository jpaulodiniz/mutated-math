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
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the compact Singular Value Decomposition of a matrix.
 * <p>
 * The Singular Value Decomposition of matrix A is a set of three matrices: U,
 * &Sigma; and V such that A = U &times; &Sigma; &times; V<sup>T</sup>. Let A be
 * a m &times; n matrix, then U is a m &times; p orthogonal matrix, &Sigma; is a
 * p &times; p diagonal matrix with positive or null elements, V is a p &times;
 * n orthogonal matrix (hence V<sup>T</sup> is also orthogonal) where
 * p=min(m,n).
 * </p>
 * <p>This class is similar to the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library, with the
 * following changes:</p>
 * <ul>
 *   <li>the {@code norm2} method which has been renamed as {@link #getNorm()
 *   getNorm},</li>
 *   <li>the {@code cond} method which has been renamed as {@link
 *   #getConditionNumber() getConditionNumber},</li>
 *   <li>the {@code rank} method which has been renamed as {@link #getRank()
 *   getRank},</li>
 *   <li>a {@link #getUT() getUT} method has been added,</li>
 *   <li>a {@link #getVT() getVT} method has been added,</li>
 *   <li>a {@link #getSolver() getSolver} method has been added,</li>
 *   <li>a {@link #getCovariance(double) getCovariance} method has been added.</li>
 * </ul>
 * @see <a href="http://mathworld.wolfram.com/SingularValueDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Singular_value_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class SingularValueDecomposition {

    @Conditional
    public static boolean _mut23285 = false, _mut23286 = false, _mut23287 = false, _mut23288 = false, _mut23289 = false, _mut23290 = false, _mut23291 = false, _mut23292 = false, _mut23293 = false, _mut23294 = false, _mut23295 = false, _mut23296 = false, _mut23297 = false, _mut23298 = false, _mut23299 = false, _mut23300 = false, _mut23301 = false, _mut23302 = false, _mut23303 = false, _mut23304 = false, _mut23305 = false, _mut23306 = false, _mut23307 = false, _mut23308 = false, _mut23309 = false, _mut23310 = false, _mut23311 = false, _mut23312 = false, _mut23313 = false, _mut23314 = false, _mut23315 = false, _mut23316 = false, _mut23317 = false, _mut23318 = false, _mut23319 = false, _mut23320 = false, _mut23321 = false, _mut23322 = false, _mut23323 = false, _mut23324 = false, _mut23325 = false, _mut23326 = false, _mut23327 = false, _mut23328 = false, _mut23329 = false, _mut23330 = false, _mut23331 = false, _mut23332 = false, _mut23333 = false, _mut23334 = false, _mut23335 = false, _mut23336 = false, _mut23337 = false, _mut23338 = false, _mut23339 = false, _mut23340 = false, _mut23341 = false, _mut23342 = false, _mut23343 = false, _mut23344 = false, _mut23345 = false, _mut23346 = false, _mut23347 = false, _mut23348 = false, _mut23349 = false, _mut23350 = false, _mut23351 = false, _mut23352 = false, _mut23353 = false, _mut23354 = false, _mut23355 = false, _mut23356 = false, _mut23357 = false, _mut23358 = false, _mut23359 = false, _mut23360 = false, _mut23361 = false, _mut23362 = false, _mut23363 = false, _mut23364 = false, _mut23365 = false, _mut23366 = false, _mut23367 = false, _mut23368 = false, _mut23369 = false, _mut23370 = false, _mut23371 = false, _mut23372 = false, _mut23373 = false, _mut23374 = false, _mut23375 = false, _mut23376 = false, _mut23377 = false, _mut23378 = false, _mut23379 = false, _mut23380 = false, _mut23381 = false, _mut23382 = false, _mut23383 = false, _mut23384 = false, _mut23385 = false, _mut23386 = false, _mut23387 = false, _mut23388 = false, _mut23389 = false, _mut23390 = false, _mut23391 = false, _mut23392 = false, _mut23393 = false, _mut23394 = false, _mut23395 = false, _mut23396 = false, _mut23397 = false, _mut23398 = false, _mut23399 = false, _mut23400 = false, _mut23401 = false, _mut23402 = false, _mut23403 = false, _mut23404 = false, _mut23405 = false, _mut23406 = false, _mut23407 = false, _mut23408 = false, _mut23409 = false, _mut23410 = false, _mut23411 = false, _mut23412 = false, _mut23413 = false, _mut23414 = false, _mut23415 = false, _mut23416 = false, _mut23417 = false, _mut23418 = false, _mut23419 = false, _mut23420 = false, _mut23421 = false, _mut23422 = false, _mut23423 = false, _mut23424 = false, _mut23425 = false, _mut23426 = false, _mut23427 = false, _mut23428 = false, _mut23429 = false, _mut23430 = false, _mut23431 = false, _mut23432 = false, _mut23433 = false, _mut23434 = false, _mut23435 = false, _mut23436 = false, _mut23437 = false, _mut23438 = false, _mut23439 = false, _mut23440 = false, _mut23441 = false, _mut23442 = false, _mut23443 = false, _mut23444 = false, _mut23445 = false, _mut23446 = false, _mut23447 = false, _mut23448 = false, _mut23449 = false, _mut23450 = false, _mut23451 = false, _mut23452 = false, _mut23453 = false, _mut23454 = false, _mut23455 = false, _mut23456 = false, _mut23457 = false, _mut23458 = false, _mut23459 = false, _mut23460 = false, _mut23461 = false, _mut23462 = false, _mut23463 = false, _mut23464 = false, _mut23465 = false, _mut23466 = false, _mut23467 = false, _mut23468 = false, _mut23469 = false, _mut23470 = false, _mut23471 = false, _mut23472 = false, _mut23473 = false, _mut23474 = false, _mut23475 = false, _mut23476 = false, _mut23477 = false, _mut23478 = false, _mut23479 = false, _mut23480 = false, _mut23481 = false, _mut23482 = false, _mut23483 = false, _mut23484 = false, _mut23485 = false, _mut23486 = false, _mut23487 = false, _mut23488 = false, _mut23489 = false, _mut23490 = false, _mut23491 = false, _mut23492 = false, _mut23493 = false, _mut23494 = false, _mut23495 = false, _mut23496 = false, _mut23497 = false, _mut23498 = false, _mut23499 = false, _mut23500 = false, _mut23501 = false, _mut23502 = false, _mut23503 = false, _mut23504 = false, _mut23505 = false, _mut23506 = false, _mut23507 = false, _mut23508 = false, _mut23509 = false, _mut23510 = false, _mut23511 = false, _mut23512 = false, _mut23513 = false, _mut23514 = false, _mut23515 = false, _mut23516 = false, _mut23517 = false, _mut23518 = false, _mut23519 = false, _mut23520 = false, _mut23521 = false, _mut23522 = false, _mut23523 = false, _mut23524 = false, _mut23525 = false, _mut23526 = false, _mut23527 = false, _mut23528 = false, _mut23529 = false, _mut23530 = false, _mut23531 = false, _mut23532 = false, _mut23533 = false, _mut23534 = false, _mut23535 = false, _mut23536 = false, _mut23537 = false, _mut23538 = false, _mut23539 = false, _mut23540 = false, _mut23541 = false, _mut23542 = false, _mut23543 = false, _mut23544 = false, _mut23545 = false, _mut23546 = false, _mut23547 = false, _mut23548 = false, _mut23549 = false, _mut23550 = false, _mut23551 = false, _mut23552 = false, _mut23553 = false, _mut23554 = false, _mut23555 = false, _mut23556 = false, _mut23557 = false, _mut23558 = false, _mut23559 = false, _mut23560 = false, _mut23561 = false, _mut23562 = false, _mut23563 = false, _mut23564 = false, _mut23565 = false, _mut23566 = false, _mut23567 = false, _mut23568 = false, _mut23569 = false, _mut23570 = false, _mut23571 = false, _mut23572 = false, _mut23573 = false, _mut23574 = false, _mut23575 = false, _mut23576 = false, _mut23577 = false, _mut23578 = false, _mut23579 = false, _mut23580 = false, _mut23581 = false, _mut23582 = false, _mut23583 = false, _mut23584 = false, _mut23585 = false, _mut23586 = false, _mut23587 = false, _mut23588 = false, _mut23589 = false, _mut23590 = false, _mut23591 = false, _mut23592 = false, _mut23593 = false, _mut23594 = false, _mut23595 = false, _mut23596 = false, _mut23597 = false, _mut23598 = false, _mut23599 = false, _mut23600 = false, _mut23601 = false, _mut23602 = false, _mut23603 = false, _mut23604 = false, _mut23605 = false, _mut23606 = false, _mut23607 = false, _mut23608 = false, _mut23609 = false, _mut23610 = false, _mut23611 = false, _mut23612 = false, _mut23613 = false, _mut23614 = false, _mut23615 = false, _mut23616 = false, _mut23617 = false, _mut23618 = false, _mut23619 = false, _mut23620 = false, _mut23621 = false, _mut23622 = false, _mut23623 = false, _mut23624 = false, _mut23625 = false, _mut23626 = false, _mut23627 = false, _mut23628 = false, _mut23629 = false, _mut23630 = false, _mut23631 = false, _mut23632 = false, _mut23633 = false, _mut23634 = false, _mut23635 = false, _mut23636 = false, _mut23637 = false, _mut23638 = false, _mut23639 = false, _mut23640 = false, _mut23641 = false, _mut23642 = false, _mut23643 = false, _mut23644 = false, _mut23645 = false, _mut23646 = false, _mut23647 = false, _mut23648 = false, _mut23649 = false, _mut23650 = false, _mut23651 = false, _mut23652 = false, _mut23653 = false, _mut23654 = false, _mut23655 = false, _mut23656 = false, _mut23657 = false, _mut23658 = false, _mut23659 = false, _mut23660 = false, _mut23661 = false, _mut23662 = false, _mut23663 = false, _mut23664 = false, _mut23665 = false, _mut23666 = false, _mut23667 = false, _mut23668 = false, _mut23669 = false, _mut23670 = false, _mut23671 = false, _mut23672 = false, _mut23673 = false, _mut23674 = false, _mut23675 = false, _mut23676 = false, _mut23677 = false, _mut23678 = false, _mut23679 = false, _mut23680 = false, _mut23681 = false, _mut23682 = false, _mut23683 = false, _mut23684 = false, _mut23685 = false, _mut23686 = false, _mut23687 = false, _mut23688 = false, _mut23689 = false, _mut23690 = false, _mut23691 = false, _mut23692 = false, _mut23693 = false, _mut23694 = false, _mut23695 = false, _mut23696 = false, _mut23697 = false, _mut23698 = false, _mut23699 = false, _mut23700 = false, _mut23701 = false, _mut23702 = false, _mut23703 = false, _mut23704 = false, _mut23705 = false, _mut23706 = false, _mut23707 = false, _mut23708 = false, _mut23709 = false, _mut23710 = false, _mut23711 = false, _mut23712 = false, _mut23713 = false, _mut23714 = false, _mut23715 = false, _mut23716 = false, _mut23717 = false, _mut23718 = false, _mut23719 = false, _mut23720 = false, _mut23721 = false, _mut23722 = false, _mut23723 = false, _mut23724 = false, _mut23725 = false, _mut23726 = false, _mut23727 = false, _mut23728 = false, _mut23729 = false, _mut23730 = false, _mut23731 = false, _mut23732 = false, _mut23733 = false, _mut23734 = false, _mut23735 = false, _mut23736 = false, _mut23737 = false, _mut23738 = false, _mut23739 = false, _mut23740 = false, _mut23741 = false, _mut23742 = false, _mut23743 = false, _mut23744 = false, _mut23745 = false, _mut23746 = false, _mut23747 = false, _mut23748 = false, _mut23749 = false, _mut23750 = false, _mut23751 = false, _mut23752 = false, _mut23753 = false, _mut23754 = false, _mut23755 = false, _mut23756 = false, _mut23757 = false, _mut23758 = false, _mut23759 = false, _mut23760 = false, _mut23761 = false, _mut23762 = false, _mut23763 = false, _mut23764 = false, _mut23765 = false, _mut23766 = false, _mut23767 = false, _mut23768 = false, _mut23769 = false, _mut23770 = false, _mut23771 = false, _mut23772 = false, _mut23773 = false, _mut23774 = false, _mut23775 = false, _mut23776 = false, _mut23777 = false, _mut23778 = false, _mut23779 = false, _mut23780 = false, _mut23781 = false, _mut23782 = false, _mut23783 = false, _mut23784 = false, _mut23785 = false, _mut23786 = false, _mut23787 = false, _mut23788 = false, _mut23789 = false, _mut23790 = false, _mut23791 = false, _mut23792 = false, _mut23793 = false, _mut23794 = false, _mut23795 = false, _mut23796 = false, _mut23797 = false, _mut23798 = false, _mut23799 = false, _mut23800 = false, _mut23801 = false, _mut23802 = false, _mut23803 = false, _mut23804 = false, _mut23805 = false, _mut23806 = false, _mut23807 = false, _mut23808 = false, _mut23809 = false, _mut23810 = false, _mut23811 = false, _mut23812 = false, _mut23813 = false, _mut23814 = false, _mut23815 = false, _mut23816 = false, _mut23817 = false, _mut23818 = false, _mut23819 = false, _mut23820 = false, _mut23821 = false, _mut23822 = false, _mut23823 = false, _mut23824 = false, _mut23825 = false, _mut23826 = false, _mut23827 = false, _mut23828 = false, _mut23829 = false, _mut23830 = false, _mut23831 = false, _mut23832 = false, _mut23833 = false, _mut23834 = false, _mut23835 = false, _mut23836 = false, _mut23837 = false, _mut23838 = false, _mut23839 = false, _mut23840 = false, _mut23841 = false, _mut23842 = false, _mut23843 = false, _mut23844 = false, _mut23845 = false, _mut23846 = false, _mut23847 = false, _mut23848 = false, _mut23849 = false, _mut23850 = false, _mut23851 = false, _mut23852 = false, _mut23853 = false, _mut23854 = false, _mut23855 = false, _mut23856 = false, _mut23857 = false, _mut23858 = false, _mut23859 = false, _mut23860 = false, _mut23861 = false, _mut23862 = false, _mut23863 = false, _mut23864 = false, _mut23865 = false, _mut23866 = false, _mut23867 = false, _mut23868 = false, _mut23869 = false, _mut23870 = false, _mut23871 = false, _mut23872 = false, _mut23873 = false, _mut23874 = false, _mut23875 = false, _mut23876 = false, _mut23877 = false, _mut23878 = false, _mut23879 = false, _mut23880 = false, _mut23881 = false, _mut23882 = false, _mut23883 = false, _mut23884 = false, _mut23885 = false, _mut23886 = false, _mut23887 = false, _mut23888 = false, _mut23889 = false, _mut23890 = false, _mut23891 = false, _mut23892 = false, _mut23893 = false, _mut23894 = false, _mut23895 = false, _mut23896 = false, _mut23897 = false, _mut23898 = false, _mut23899 = false, _mut23900 = false, _mut23901 = false, _mut23902 = false, _mut23903 = false, _mut23904 = false, _mut23905 = false, _mut23906 = false, _mut23907 = false, _mut23908 = false, _mut23909 = false, _mut23910 = false, _mut23911 = false, _mut23912 = false, _mut23913 = false, _mut23914 = false, _mut23915 = false, _mut23916 = false, _mut23917 = false, _mut23918 = false, _mut23919 = false, _mut23920 = false, _mut23921 = false, _mut23922 = false, _mut23923 = false, _mut23924 = false, _mut23925 = false, _mut23926 = false, _mut23927 = false, _mut23928 = false, _mut23929 = false, _mut23930 = false, _mut23931 = false, _mut23932 = false, _mut23933 = false, _mut23934 = false, _mut23935 = false, _mut23936 = false, _mut23937 = false, _mut23938 = false, _mut23939 = false, _mut23940 = false, _mut23941 = false, _mut23942 = false, _mut23943 = false, _mut23944 = false, _mut23945 = false, _mut23946 = false, _mut23947 = false, _mut23948 = false, _mut23949 = false, _mut23950 = false, _mut23951 = false, _mut23952 = false, _mut23953 = false, _mut23954 = false, _mut23955 = false, _mut23956 = false, _mut23957 = false, _mut23958 = false, _mut23959 = false, _mut23960 = false, _mut23961 = false, _mut23962 = false, _mut23963 = false, _mut23964 = false, _mut23965 = false, _mut23966 = false, _mut23967 = false, _mut23968 = false, _mut23969 = false, _mut23970 = false, _mut23971 = false, _mut23972 = false, _mut23973 = false, _mut23974 = false, _mut23975 = false, _mut23976 = false, _mut23977 = false, _mut23978 = false, _mut23979 = false, _mut23980 = false, _mut23981 = false, _mut23982 = false, _mut23983 = false, _mut23984 = false, _mut23985 = false, _mut23986 = false, _mut23987 = false, _mut23988 = false, _mut23989 = false, _mut23990 = false, _mut23991 = false, _mut23992 = false, _mut23993 = false, _mut23994 = false, _mut23995 = false, _mut23996 = false, _mut23997 = false, _mut23998 = false, _mut23999 = false, _mut24000 = false, _mut24001 = false, _mut24002 = false, _mut24003 = false, _mut24004 = false, _mut24005 = false, _mut24006 = false, _mut24007 = false, _mut24008 = false, _mut24009 = false, _mut24010 = false, _mut24011 = false, _mut24012 = false, _mut24013 = false, _mut24014 = false, _mut24015 = false, _mut24016 = false, _mut24017 = false, _mut24018 = false, _mut24019 = false, _mut24020 = false, _mut24021 = false, _mut24022 = false, _mut24023 = false, _mut24024 = false, _mut24025 = false, _mut24026 = false, _mut24027 = false, _mut24028 = false, _mut24029 = false, _mut24030 = false, _mut24031 = false, _mut24032 = false, _mut24033 = false, _mut24034 = false, _mut24035 = false, _mut24036 = false, _mut24037 = false, _mut24038 = false, _mut24039 = false, _mut24040 = false, _mut24041 = false, _mut24042 = false, _mut24043 = false, _mut24044 = false, _mut24045 = false, _mut24046 = false, _mut24047 = false, _mut24048 = false, _mut24049 = false, _mut24050 = false, _mut24051 = false, _mut24052 = false, _mut24053 = false, _mut24054 = false, _mut24055 = false, _mut24056 = false, _mut24057 = false, _mut24058 = false, _mut24059 = false, _mut24060 = false, _mut24061 = false, _mut24062 = false, _mut24063 = false, _mut24064 = false, _mut24065 = false, _mut24066 = false, _mut24067 = false, _mut24068 = false, _mut24069 = false, _mut24070 = false, _mut24071 = false, _mut24072 = false, _mut24073 = false, _mut24074 = false, _mut24075 = false, _mut24076 = false, _mut24077 = false, _mut24078 = false, _mut24079 = false, _mut24080 = false, _mut24081 = false, _mut24082 = false, _mut24083 = false, _mut24084 = false, _mut24085 = false, _mut24086 = false, _mut24087 = false, _mut24088 = false, _mut24089 = false, _mut24090 = false, _mut24091 = false, _mut24092 = false, _mut24093 = false, _mut24094 = false, _mut24095 = false, _mut24096 = false, _mut24097 = false, _mut24098 = false, _mut24099 = false, _mut24100 = false, _mut24101 = false, _mut24102 = false, _mut24103 = false, _mut24104 = false, _mut24105 = false, _mut24106 = false, _mut24107 = false, _mut24108 = false, _mut24109 = false, _mut24110 = false, _mut24111 = false, _mut24112 = false, _mut24113 = false, _mut24114 = false, _mut24115 = false, _mut24116 = false, _mut24117 = false, _mut24118 = false, _mut24119 = false, _mut24120 = false, _mut24121 = false, _mut24122 = false, _mut24123 = false, _mut24124 = false, _mut24125 = false, _mut24126 = false, _mut24127 = false, _mut24128 = false, _mut24129 = false, _mut24130 = false, _mut24131 = false, _mut24132 = false, _mut24133 = false, _mut24134 = false, _mut24135 = false, _mut24136 = false, _mut24137 = false, _mut24138 = false, _mut24139 = false, _mut24140 = false, _mut24141 = false, _mut24142 = false, _mut24143 = false, _mut24144 = false, _mut24145 = false, _mut24146 = false, _mut24147 = false, _mut24148 = false, _mut24149 = false, _mut24150 = false, _mut24151 = false, _mut24152 = false, _mut24153 = false, _mut24154 = false, _mut24155 = false, _mut24156 = false, _mut24157 = false, _mut24158 = false, _mut24159 = false, _mut24160 = false, _mut24161 = false, _mut24162 = false, _mut24163 = false, _mut24164 = false, _mut24165 = false, _mut24166 = false, _mut24167 = false, _mut24168 = false, _mut24169 = false, _mut24170 = false, _mut24171 = false, _mut24172 = false, _mut24173 = false, _mut24174 = false, _mut24175 = false, _mut24176 = false, _mut24177 = false, _mut24178 = false, _mut24179 = false, _mut24180 = false, _mut24181 = false, _mut24182 = false, _mut24183 = false, _mut24184 = false, _mut24185 = false, _mut24186 = false, _mut24187 = false, _mut24188 = false, _mut24189 = false, _mut24190 = false, _mut24191 = false, _mut24192 = false, _mut24193 = false, _mut24194 = false, _mut24195 = false, _mut24196 = false, _mut24197 = false, _mut24198 = false, _mut24199 = false, _mut24200 = false, _mut24201 = false, _mut24202 = false, _mut24203 = false, _mut24204 = false, _mut24205 = false, _mut24206 = false, _mut24207 = false, _mut24208 = false, _mut24209 = false, _mut24210 = false, _mut24211 = false, _mut24212 = false, _mut24213 = false, _mut24214 = false, _mut24215 = false, _mut24216 = false, _mut24217 = false, _mut24218 = false, _mut24219 = false, _mut24220 = false, _mut24221 = false, _mut24222 = false, _mut24223 = false, _mut24224 = false, _mut24225 = false, _mut24226 = false, _mut24227 = false, _mut24228 = false, _mut24229 = false, _mut24230 = false, _mut24231 = false, _mut24232 = false, _mut24233 = false, _mut24234 = false, _mut24235 = false, _mut24236 = false, _mut24237 = false, _mut24238 = false, _mut24239 = false, _mut24240 = false, _mut24241 = false, _mut24242 = false, _mut24243 = false, _mut24244 = false, _mut24245 = false, _mut24246 = false, _mut24247 = false, _mut24248 = false, _mut24249 = false, _mut24250 = false, _mut24251 = false, _mut24252 = false, _mut24253 = false, _mut24254 = false, _mut24255 = false, _mut24256 = false, _mut24257 = false, _mut24258 = false, _mut24259 = false, _mut24260 = false, _mut24261 = false, _mut24262 = false, _mut24263 = false, _mut24264 = false, _mut24265 = false, _mut24266 = false, _mut24267 = false, _mut24268 = false, _mut24269 = false, _mut24270 = false, _mut24271 = false, _mut24272 = false, _mut24273 = false, _mut24274 = false, _mut24275 = false, _mut24276 = false, _mut24277 = false, _mut24278 = false, _mut24279 = false, _mut24280 = false, _mut24281 = false, _mut24282 = false, _mut24283 = false, _mut24284 = false, _mut24285 = false, _mut24286 = false, _mut24287 = false, _mut24288 = false, _mut24289 = false, _mut24290 = false, _mut24291 = false, _mut24292 = false, _mut24293 = false, _mut24294 = false, _mut24295 = false, _mut24296 = false, _mut24297 = false, _mut24298 = false, _mut24299 = false, _mut24300 = false, _mut24301 = false, _mut24302 = false, _mut24303 = false, _mut24304 = false, _mut24305 = false, _mut24306 = false, _mut24307 = false, _mut24308 = false, _mut24309 = false, _mut24310 = false, _mut24311 = false, _mut24312 = false, _mut24313 = false, _mut24314 = false, _mut24315 = false, _mut24316 = false, _mut24317 = false, _mut24318 = false, _mut24319 = false, _mut24320 = false, _mut24321 = false, _mut24322 = false, _mut24323 = false, _mut24324 = false, _mut24325 = false, _mut24326 = false, _mut24327 = false, _mut24328 = false, _mut24329 = false, _mut24330 = false, _mut24331 = false, _mut24332 = false, _mut24333 = false, _mut24334 = false, _mut24335 = false, _mut24336 = false, _mut24337 = false, _mut24338 = false, _mut24339 = false, _mut24340 = false, _mut24341 = false, _mut24342 = false, _mut24343 = false, _mut24344 = false, _mut24345 = false, _mut24346 = false, _mut24347 = false, _mut24348 = false, _mut24349 = false, _mut24350 = false, _mut24351 = false, _mut24352 = false, _mut24353 = false, _mut24354 = false, _mut24355 = false, _mut24356 = false, _mut24357 = false, _mut24358 = false, _mut24359 = false, _mut24360 = false, _mut24361 = false, _mut24362 = false, _mut24363 = false, _mut24364 = false, _mut24365 = false, _mut24366 = false, _mut24367 = false, _mut24368 = false, _mut24369 = false, _mut24370 = false, _mut24371 = false, _mut24372 = false, _mut24373 = false, _mut24374 = false, _mut24375 = false, _mut24376 = false, _mut24377 = false, _mut24378 = false, _mut24379 = false, _mut24380 = false, _mut24381 = false, _mut24382 = false, _mut24383 = false, _mut24384 = false, _mut24385 = false, _mut24386 = false, _mut24387 = false, _mut24388 = false, _mut24389 = false, _mut24390 = false, _mut24391 = false, _mut24392 = false, _mut24393 = false, _mut24394 = false, _mut24395 = false, _mut24396 = false, _mut24397 = false, _mut24398 = false, _mut24399 = false, _mut24400 = false, _mut24401 = false, _mut24402 = false, _mut24403 = false, _mut24404 = false, _mut24405 = false, _mut24406 = false, _mut24407 = false, _mut24408 = false, _mut24409 = false, _mut24410 = false;

    /**
     * Relative threshold for small singular values.
     */
    private static final double EPS = 0x1.0p-52;

    /**
     * Absolute threshold for small singular values.
     */
    private static final double TINY = 0x1.0p-966;

    /**
     * Computed singular values.
     */
    private final double[] singularValues;

    /**
     * max(row dimension, column dimension).
     */
    private final int m;

    /**
     * min(row dimension, column dimension).
     */
    private final int n;

    /**
     * Indicator for transposed matrix.
     */
    private final boolean transposed;

    /**
     * Cached value of U matrix.
     */
    private final RealMatrix cachedU;

    /**
     * Cached value of transposed U matrix.
     */
    private RealMatrix cachedUt;

    /**
     * Cached value of S (diagonal) matrix.
     */
    private RealMatrix cachedS;

    /**
     * Cached value of V matrix.
     */
    private final RealMatrix cachedV;

    /**
     * Cached value of transposed V matrix.
     */
    private RealMatrix cachedVt;

    /**
     * Tolerance value for small singular values, calculated once we have
     * populated "singularValues".
     */
    private final double tol;

    /**
     * Calculates the compact Singular Value Decomposition of the given matrix.
     *
     * @param matrix Matrix to decompose.
     */
    public SingularValueDecomposition(final RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
        final double[][] A;
        // "m" is always the largest dimension.
        if (ROR_less(matrix.getRowDimension(), matrix.getColumnDimension(), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23285, _mut23286, _mut23287, _mut23288, _mut23289)) {
            transposed = true;
            A = matrix.transpose().getData();
            m = matrix.getColumnDimension();
            n = matrix.getRowDimension();
        } else {
            transposed = false;
            A = matrix.getData();
            m = matrix.getRowDimension();
            n = matrix.getColumnDimension();
        }
        singularValues = new double[n];
        final double[][] U = new double[m][n];
        final double[][] V = new double[n][n];
        final double[] e = new double[n];
        final double[] work = new double[m];
        // in s and the super-diagonal elements in e.
        final int nct = FastMath.min(AOR_minus(m, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23290, _mut23291, _mut23292, _mut23293), n);
        final int nrt = FastMath.max(0, AOR_minus(n, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23294, _mut23295, _mut23296, _mut23297));
        for (int k = 0; ROR_less(k, FastMath.max(nct, nrt), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23465, _mut23466, _mut23467, _mut23468, _mut23469); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
            if (ROR_less(k, nct, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23298, _mut23299, _mut23300, _mut23301, _mut23302)) {
                // Compute 2-norm of k-th column without under/overflow.
                singularValues[k] = 0;
                for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23303, _mut23304, _mut23305, _mut23306, _mut23307); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    singularValues[k] = FastMath.hypot(singularValues[k], A[i][k]);
                }
                if (ROR_not_equals(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23308, _mut23309, _mut23310, _mut23311, _mut23312)) {
                    if (ROR_less(A[k][k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23313, _mut23314, _mut23315, _mut23316, _mut23317)) {
                        singularValues[k] = -singularValues[k];
                    }
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23318, _mut23319, _mut23320, _mut23321, _mut23322); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        A[i][k] /= singularValues[k];
                    }
                    A[k][k] += 1;
                }
                singularValues[k] = -singularValues[k];
            }
            for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23356, _mut23357, _mut23358, _mut23359, _mut23360); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                if ((_mut23333 ? (ROR_less(k, nct, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23323, _mut23324, _mut23325, _mut23326, _mut23327) || ROR_not_equals(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23328, _mut23329, _mut23330, _mut23331, _mut23332)) : (ROR_less(k, nct, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23323, _mut23324, _mut23325, _mut23326, _mut23327) && ROR_not_equals(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23328, _mut23329, _mut23330, _mut23331, _mut23332)))) {
                    // Apply the transformation.
                    double t = 0;
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23338, _mut23339, _mut23340, _mut23341, _mut23342); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        t += AOR_multiply(A[i][k], A[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23334, _mut23335, _mut23336, _mut23337);
                    }
                    t = AOR_divide(-t, A[k][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23343, _mut23344, _mut23345, _mut23346);
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23351, _mut23352, _mut23353, _mut23354, _mut23355); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        A[i][j] += AOR_multiply(t, A[i][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23347, _mut23348, _mut23349, _mut23350);
                    }
                }
                // subsequent calculation of the row transformation.
                e[j] = A[k][j];
            }
            if (ROR_less(k, nct, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23361, _mut23362, _mut23363, _mut23364, _mut23365)) {
                // multiplication.
                for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23366, _mut23367, _mut23368, _mut23369, _mut23370); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    U[i][k] = A[i][k];
                }
            }
            if (ROR_less(k, nrt, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23371, _mut23372, _mut23373, _mut23374, _mut23375)) {
                // Compute 2-norm without under/overflow.
                e[k] = 0;
                for (int i = k + 1; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23376, _mut23377, _mut23378, _mut23379, _mut23380); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    e[k] = FastMath.hypot(e[k], e[i]);
                }
                if (ROR_not_equals(e[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23381, _mut23382, _mut23383, _mut23384, _mut23385)) {
                    if (ROR_less(e[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23386, _mut23387, _mut23388, _mut23389)], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23390, _mut23391, _mut23392, _mut23393, _mut23394)) {
                        e[k] = -e[k];
                    }
                    for (int i = k + 1; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23395, _mut23396, _mut23397, _mut23398, _mut23399); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        e[i] /= e[k];
                    }
                    e[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23400, _mut23401, _mut23402, _mut23403)] += 1;
                }
                e[k] = -e[k];
                if ((_mut23418 ? (ROR_less(AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23404, _mut23405, _mut23406, _mut23407), m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23408, _mut23409, _mut23410, _mut23411, _mut23412) || ROR_not_equals(e[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23413, _mut23414, _mut23415, _mut23416, _mut23417)) : (ROR_less(AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23404, _mut23405, _mut23406, _mut23407), m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23408, _mut23409, _mut23410, _mut23411, _mut23412) && ROR_not_equals(e[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23413, _mut23414, _mut23415, _mut23416, _mut23417)))) {
                    // Apply the transformation.
                    for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23419, _mut23420, _mut23421, _mut23422, _mut23423); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        work[i] = 0;
                    }
                    for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23433, _mut23434, _mut23435, _mut23436, _mut23437); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23428, _mut23429, _mut23430, _mut23431, _mut23432); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            work[i] += AOR_multiply(e[j], A[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23424, _mut23425, _mut23426, _mut23427);
                        }
                    }
                    for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23455, _mut23456, _mut23457, _mut23458, _mut23459); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        final double t = AOR_divide(-e[j], e[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23438, _mut23439, _mut23440, _mut23441)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23442, _mut23443, _mut23444, _mut23445);
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23450, _mut23451, _mut23452, _mut23453, _mut23454); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            A[i][j] += AOR_multiply(t, work[i], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23446, _mut23447, _mut23448, _mut23449);
                        }
                    }
                }
                // back multiplication.
                for (int i = k + 1; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23460, _mut23461, _mut23462, _mut23463, _mut23464); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    V[i][k] = e[i];
                }
            }
        }
        // Set up the final bidiagonal matrix or order p.
        int p = n;
        if (ROR_less(nct, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23470, _mut23471, _mut23472, _mut23473, _mut23474)) {
            singularValues[nct] = A[nct][nct];
        }
        if (ROR_less(m, p, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23475, _mut23476, _mut23477, _mut23478, _mut23479)) {
            singularValues[AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23480, _mut23481, _mut23482, _mut23483)] = 0;
        }
        if (ROR_less(AOR_plus(nrt, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23484, _mut23485, _mut23486, _mut23487), p, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23488, _mut23489, _mut23490, _mut23491, _mut23492)) {
            e[nrt] = A[nrt][AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23493, _mut23494, _mut23495, _mut23496)];
        }
        e[AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23497, _mut23498, _mut23499, _mut23500)] = 0;
        // Generate U.
        for (int j = nct; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23506, _mut23507, _mut23508, _mut23509, _mut23510); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23501, _mut23502, _mut23503, _mut23504, _mut23505); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                U[i][j] = 0;
            }
            U[j][j] = 1;
        }
        for (int k = nct - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23566, _mut23567, _mut23568, _mut23569, _mut23570); k--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
            if (ROR_not_equals(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23511, _mut23512, _mut23513, _mut23514, _mut23515)) {
                for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23543, _mut23544, _mut23545, _mut23546, _mut23547); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    double t = 0;
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23525, _mut23526, _mut23527, _mut23528, _mut23529); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        t += AOR_multiply(U[i][k], U[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23521, _mut23522, _mut23523, _mut23524);
                    }
                    t = AOR_divide(-t, U[k][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23530, _mut23531, _mut23532, _mut23533);
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23538, _mut23539, _mut23540, _mut23541, _mut23542); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        U[i][j] += AOR_multiply(t, U[i][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23534, _mut23535, _mut23536, _mut23537);
                    }
                }
                for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23548, _mut23549, _mut23550, _mut23551, _mut23552); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    U[i][k] = -U[i][k];
                }
                U[k][k] = AOR_plus(1, U[k][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23553, _mut23554, _mut23555, _mut23556);
                for (int i = 0; ROR_less(i, AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23557, _mut23558, _mut23559, _mut23560), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23561, _mut23562, _mut23563, _mut23564, _mut23565); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    U[i][k] = 0;
                }
            } else {
                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23516, _mut23517, _mut23518, _mut23519, _mut23520); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    U[i][k] = 0;
                }
                U[k][k] = 1;
            }
        }
        // Generate V.
        for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23618, _mut23619, _mut23620, _mut23621, _mut23622); k--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
            if ((_mut23581 ? (ROR_less(k, nrt, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23571, _mut23572, _mut23573, _mut23574, _mut23575) || ROR_not_equals(e[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23576, _mut23577, _mut23578, _mut23579, _mut23580)) : (ROR_less(k, nrt, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23571, _mut23572, _mut23573, _mut23574, _mut23575) && ROR_not_equals(e[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23576, _mut23577, _mut23578, _mut23579, _mut23580)))) {
                for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23608, _mut23609, _mut23610, _mut23611, _mut23612); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    double t = 0;
                    for (int i = k + 1; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23586, _mut23587, _mut23588, _mut23589, _mut23590); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        t += AOR_multiply(V[i][k], V[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23582, _mut23583, _mut23584, _mut23585);
                    }
                    t = AOR_divide(-t, V[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23591, _mut23592, _mut23593, _mut23594)][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23595, _mut23596, _mut23597, _mut23598);
                    for (int i = k + 1; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23603, _mut23604, _mut23605, _mut23606, _mut23607); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                        V[i][j] += AOR_multiply(t, V[i][k], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23599, _mut23600, _mut23601, _mut23602);
                    }
                }
            }
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23613, _mut23614, _mut23615, _mut23616, _mut23617); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                V[i][k] = 0;
            }
            V[k][k] = 1;
        }
        // Main iteration loop for the singular values.
        final int pp = AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23623, _mut23624, _mut23625, _mut23626);
        while (ROR_greater(p, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24320, _mut24321, _mut24322, _mut24323, _mut24324)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
            int k;
            int kase;
            // kase = 4     if e(p-1) is negligible (convergence).
            for (k = p - 2; ROR_greater_equals(k, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23648, _mut23649, _mut23650, _mut23651, _mut23652); k--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                final double threshold = AOR_plus(TINY, AOR_multiply(EPS, (AOR_plus(FastMath.abs(singularValues[k]), FastMath.abs(singularValues[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23627, _mut23628, _mut23629, _mut23630)]), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23631, _mut23632, _mut23633, _mut23634)), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23635, _mut23636, _mut23637, _mut23638), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23639, _mut23640, _mut23641, _mut23642);
                // see issue MATH-947
                if (!(ROR_greater(FastMath.abs(e[k]), threshold, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23643, _mut23644, _mut23645, _mut23646, _mut23647))) {
                    e[k] = 0;
                    break;
                }
            }
            if (ROR_equals(k, AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23653, _mut23654, _mut23655, _mut23656), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23657, _mut23658, _mut23659, _mut23660, _mut23661)) {
                kase = 4;
            } else {
                int ks;
                for (ks = p - 1; ROR_greater_equals(ks, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23702, _mut23703, _mut23704, _mut23705, _mut23706); ks--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                    if (ROR_equals(ks, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23662, _mut23663, _mut23664, _mut23665, _mut23666)) {
                        break;
                    }
                    final double t = AOR_plus((ROR_not_equals(ks, p, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23667, _mut23668, _mut23669, _mut23670, _mut23671) ? FastMath.abs(e[ks]) : 0), (ROR_not_equals(ks, AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23672, _mut23673, _mut23674, _mut23675), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23676, _mut23677, _mut23678, _mut23679, _mut23680) ? FastMath.abs(e[AOR_minus(ks, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23681, _mut23682, _mut23683, _mut23684)]) : 0), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23685, _mut23686, _mut23687, _mut23688);
                    if (ROR_less_equals(FastMath.abs(singularValues[ks]), AOR_plus(TINY, AOR_multiply(EPS, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23689, _mut23690, _mut23691, _mut23692), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23693, _mut23694, _mut23695, _mut23696), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23697, _mut23698, _mut23699, _mut23700, _mut23701)) {
                        singularValues[ks] = 0;
                        break;
                    }
                }
                if (ROR_equals(ks, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23707, _mut23708, _mut23709, _mut23710, _mut23711)) {
                    kase = 3;
                } else if (ROR_equals(ks, AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23712, _mut23713, _mut23714, _mut23715), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23716, _mut23717, _mut23718, _mut23719, _mut23720)) {
                    kase = 1;
                } else {
                    kase = 2;
                    k = ks;
                }
            }
            k++;
            // Perform the task indicated by kase.
            switch(kase) {
                // Deflate negligible s(p).
                case 1:
                    {
                        double f = e[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23721, _mut23722, _mut23723, _mut23724)];
                        e[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23725, _mut23726, _mut23727, _mut23728)] = 0;
                        for (int j = p - 2; ROR_greater_equals(j, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23803, _mut23804, _mut23805, _mut23806, _mut23807); j--) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            double t = FastMath.hypot(singularValues[j], f);
                            final double cs = AOR_divide(singularValues[j], t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23729, _mut23730, _mut23731, _mut23732);
                            final double sn = AOR_divide(f, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23733, _mut23734, _mut23735, _mut23736);
                            singularValues[j] = t;
                            if (ROR_not_equals(j, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23737, _mut23738, _mut23739, _mut23740, _mut23741)) {
                                f = AOR_multiply(-sn, e[AOR_minus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23742, _mut23743, _mut23744, _mut23745)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23746, _mut23747, _mut23748, _mut23749);
                                e[AOR_minus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23750, _mut23751, _mut23752, _mut23753)] = AOR_multiply(cs, e[AOR_minus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23754, _mut23755, _mut23756, _mut23757)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23758, _mut23759, _mut23760, _mut23761);
                            }
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23798, _mut23799, _mut23800, _mut23801, _mut23802); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                t = AOR_plus(AOR_multiply(cs, V[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23762, _mut23763, _mut23764, _mut23765), AOR_multiply(sn, V[i][AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23766, _mut23767, _mut23768, _mut23769)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23770, _mut23771, _mut23772, _mut23773), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23774, _mut23775, _mut23776, _mut23777);
                                V[i][AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23778, _mut23779, _mut23780, _mut23781)] = AOR_plus(AOR_multiply(-sn, V[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23782, _mut23783, _mut23784, _mut23785), AOR_multiply(cs, V[i][AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23786, _mut23787, _mut23788, _mut23789)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23790, _mut23791, _mut23792, _mut23793), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23794, _mut23795, _mut23796, _mut23797);
                                V[i][j] = t;
                            }
                        }
                    }
                    break;
                // Split at negligible s(k).
                case 2:
                    {
                        double f = e[AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23808, _mut23809, _mut23810, _mut23811)];
                        e[AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23812, _mut23813, _mut23814, _mut23815)] = 0;
                        for (int j = k; ROR_less(j, p, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23873, _mut23874, _mut23875, _mut23876, _mut23877); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            double t = FastMath.hypot(singularValues[j], f);
                            final double cs = AOR_divide(singularValues[j], t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23816, _mut23817, _mut23818, _mut23819);
                            final double sn = AOR_divide(f, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23820, _mut23821, _mut23822, _mut23823);
                            singularValues[j] = t;
                            f = AOR_multiply(-sn, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23824, _mut23825, _mut23826, _mut23827);
                            e[j] = AOR_multiply(cs, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23828, _mut23829, _mut23830, _mut23831);
                            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23868, _mut23869, _mut23870, _mut23871, _mut23872); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                t = AOR_plus(AOR_multiply(cs, U[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23832, _mut23833, _mut23834, _mut23835), AOR_multiply(sn, U[i][AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23836, _mut23837, _mut23838, _mut23839)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23840, _mut23841, _mut23842, _mut23843), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23844, _mut23845, _mut23846, _mut23847);
                                U[i][AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23848, _mut23849, _mut23850, _mut23851)] = AOR_plus(AOR_multiply(-sn, U[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23852, _mut23853, _mut23854, _mut23855), AOR_multiply(cs, U[i][AOR_minus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23856, _mut23857, _mut23858, _mut23859)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23860, _mut23861, _mut23862, _mut23863), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23864, _mut23865, _mut23866, _mut23867);
                                U[i][j] = t;
                            }
                        }
                    }
                    break;
                // Perform one qr step.
                case 3:
                    {
                        // Calculate the shift.
                        final double maxPm1Pm2 = FastMath.max(FastMath.abs(singularValues[AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23878, _mut23879, _mut23880, _mut23881)]), FastMath.abs(singularValues[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23882, _mut23883, _mut23884, _mut23885)]));
                        final double scale = FastMath.max(FastMath.max(FastMath.max(maxPm1Pm2, FastMath.abs(e[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23886, _mut23887, _mut23888, _mut23889)])), FastMath.abs(singularValues[k])), FastMath.abs(e[k]));
                        final double sp = AOR_divide(singularValues[AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23890, _mut23891, _mut23892, _mut23893)], scale, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23894, _mut23895, _mut23896, _mut23897);
                        final double spm1 = AOR_divide(singularValues[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23898, _mut23899, _mut23900, _mut23901)], scale, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23902, _mut23903, _mut23904, _mut23905);
                        final double epm1 = AOR_divide(e[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23906, _mut23907, _mut23908, _mut23909)], scale, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23910, _mut23911, _mut23912, _mut23913);
                        final double sk = AOR_divide(singularValues[k], scale, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23914, _mut23915, _mut23916, _mut23917);
                        final double ek = AOR_divide(e[k], scale, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23918, _mut23919, _mut23920, _mut23921);
                        final double b = AOR_divide((AOR_plus(AOR_multiply((AOR_plus(spm1, sp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23922, _mut23923, _mut23924, _mut23925)), (AOR_minus(spm1, sp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23926, _mut23927, _mut23928, _mut23929)), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23930, _mut23931, _mut23932, _mut23933), AOR_multiply(epm1, epm1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23934, _mut23935, _mut23936, _mut23937), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23938, _mut23939, _mut23940, _mut23941)), 2.0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23942, _mut23943, _mut23944, _mut23945);
                        final double c = AOR_multiply((AOR_multiply(sp, epm1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23946, _mut23947, _mut23948, _mut23949)), (AOR_multiply(sp, epm1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23950, _mut23951, _mut23952, _mut23953)), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23954, _mut23955, _mut23956, _mut23957);
                        double shift = 0;
                        if ((_mut23968 ? (ROR_not_equals(b, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23958, _mut23959, _mut23960, _mut23961, _mut23962) && ROR_not_equals(c, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23963, _mut23964, _mut23965, _mut23966, _mut23967)) : (ROR_not_equals(b, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23958, _mut23959, _mut23960, _mut23961, _mut23962) || ROR_not_equals(c, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23963, _mut23964, _mut23965, _mut23966, _mut23967)))) {
                            shift = FastMath.sqrt(AOR_plus(AOR_multiply(b, b, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23969, _mut23970, _mut23971, _mut23972), c, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23973, _mut23974, _mut23975, _mut23976));
                            if (ROR_less(b, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23977, _mut23978, _mut23979, _mut23980, _mut23981)) {
                                shift = -shift;
                            }
                            shift = AOR_divide(c, (AOR_plus(b, shift, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23982, _mut23983, _mut23984, _mut23985)), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23986, _mut23987, _mut23988, _mut23989);
                        }
                        double f = AOR_plus(AOR_multiply((AOR_plus(sk, sp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23990, _mut23991, _mut23992, _mut23993)), (AOR_minus(sk, sp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23994, _mut23995, _mut23996, _mut23997)), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut23998, _mut23999, _mut24000, _mut24001), shift, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24002, _mut24003, _mut24004, _mut24005);
                        double g = AOR_multiply(sk, ek, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24006, _mut24007, _mut24008, _mut24009);
                        // Chase zeros.
                        for (int j = k; ROR_less(j, AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24226, _mut24227, _mut24228, _mut24229), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24230, _mut24231, _mut24232, _mut24233, _mut24234); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            double t = FastMath.hypot(f, g);
                            double cs = AOR_divide(f, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24010, _mut24011, _mut24012, _mut24013);
                            double sn = AOR_divide(g, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24014, _mut24015, _mut24016, _mut24017);
                            if (ROR_not_equals(j, k, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24018, _mut24019, _mut24020, _mut24021, _mut24022)) {
                                e[AOR_minus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24023, _mut24024, _mut24025, _mut24026)] = t;
                            }
                            f = AOR_plus(AOR_multiply(cs, singularValues[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24027, _mut24028, _mut24029, _mut24030), AOR_multiply(sn, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24031, _mut24032, _mut24033, _mut24034), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24035, _mut24036, _mut24037, _mut24038);
                            e[j] = AOR_minus(AOR_multiply(cs, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24039, _mut24040, _mut24041, _mut24042), AOR_multiply(sn, singularValues[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24043, _mut24044, _mut24045, _mut24046), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24047, _mut24048, _mut24049, _mut24050);
                            g = AOR_multiply(sn, singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24051, _mut24052, _mut24053, _mut24054)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24055, _mut24056, _mut24057, _mut24058);
                            singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24059, _mut24060, _mut24061, _mut24062)] = AOR_multiply(cs, singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24063, _mut24064, _mut24065, _mut24066)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24067, _mut24068, _mut24069, _mut24070);
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24107, _mut24108, _mut24109, _mut24110, _mut24111); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                t = AOR_plus(AOR_multiply(cs, V[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24071, _mut24072, _mut24073, _mut24074), AOR_multiply(sn, V[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24075, _mut24076, _mut24077, _mut24078)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24079, _mut24080, _mut24081, _mut24082), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24083, _mut24084, _mut24085, _mut24086);
                                V[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24087, _mut24088, _mut24089, _mut24090)] = AOR_plus(AOR_multiply(-sn, V[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24091, _mut24092, _mut24093, _mut24094), AOR_multiply(cs, V[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24095, _mut24096, _mut24097, _mut24098)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24099, _mut24100, _mut24101, _mut24102), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24103, _mut24104, _mut24105, _mut24106);
                                V[i][j] = t;
                            }
                            t = FastMath.hypot(f, g);
                            cs = AOR_divide(f, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24112, _mut24113, _mut24114, _mut24115);
                            sn = AOR_divide(g, t, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24116, _mut24117, _mut24118, _mut24119);
                            singularValues[j] = t;
                            f = AOR_plus(AOR_multiply(cs, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24120, _mut24121, _mut24122, _mut24123), AOR_multiply(sn, singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24124, _mut24125, _mut24126, _mut24127)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24128, _mut24129, _mut24130, _mut24131), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24132, _mut24133, _mut24134, _mut24135);
                            singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24136, _mut24137, _mut24138, _mut24139)] = AOR_plus(AOR_multiply(-sn, e[j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24140, _mut24141, _mut24142, _mut24143), AOR_multiply(cs, singularValues[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24144, _mut24145, _mut24146, _mut24147)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24148, _mut24149, _mut24150, _mut24151), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24152, _mut24153, _mut24154, _mut24155);
                            g = AOR_multiply(sn, e[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24156, _mut24157, _mut24158, _mut24159)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24160, _mut24161, _mut24162, _mut24163);
                            e[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24164, _mut24165, _mut24166, _mut24167)] = AOR_multiply(cs, e[AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24168, _mut24169, _mut24170, _mut24171)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24172, _mut24173, _mut24174, _mut24175);
                            if (ROR_less(j, AOR_minus(m, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24176, _mut24177, _mut24178, _mut24179), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24180, _mut24181, _mut24182, _mut24183, _mut24184)) {
                                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24221, _mut24222, _mut24223, _mut24224, _mut24225); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                    t = AOR_plus(AOR_multiply(cs, U[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24185, _mut24186, _mut24187, _mut24188), AOR_multiply(sn, U[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24189, _mut24190, _mut24191, _mut24192)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24193, _mut24194, _mut24195, _mut24196), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24197, _mut24198, _mut24199, _mut24200);
                                    U[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24201, _mut24202, _mut24203, _mut24204)] = AOR_plus(AOR_multiply(-sn, U[i][j], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24205, _mut24206, _mut24207, _mut24208), AOR_multiply(cs, U[i][AOR_plus(j, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24209, _mut24210, _mut24211, _mut24212)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24213, _mut24214, _mut24215, _mut24216), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24217, _mut24218, _mut24219, _mut24220);
                                    U[i][j] = t;
                                }
                            }
                        }
                        e[AOR_minus(p, 2, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24235, _mut24236, _mut24237, _mut24238)] = f;
                    }
                    break;
                // Convergence.
                default:
                    {
                        // Make the singular values positive.
                        if (ROR_less_equals(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24239, _mut24240, _mut24241, _mut24242, _mut24243)) {
                            singularValues[k] = ROR_less(singularValues[k], 0, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24244, _mut24245, _mut24246, _mut24247, _mut24248) ? -singularValues[k] : 0;
                            for (int i = 0; ROR_less_equals(i, pp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24249, _mut24250, _mut24251, _mut24252, _mut24253); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                V[i][k] = -V[i][k];
                            }
                        }
                        // Order the singular values.
                        while (ROR_less(k, pp, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24315, _mut24316, _mut24317, _mut24318, _mut24319)) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                            if (ROR_greater_equals(singularValues[k], singularValues[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24254, _mut24255, _mut24256, _mut24257)], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24258, _mut24259, _mut24260, _mut24261, _mut24262)) {
                                break;
                            }
                            double t = singularValues[k];
                            singularValues[k] = singularValues[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24263, _mut24264, _mut24265, _mut24266)];
                            singularValues[AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24267, _mut24268, _mut24269, _mut24270)] = t;
                            if (ROR_less(k, AOR_minus(n, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24271, _mut24272, _mut24273, _mut24274), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24275, _mut24276, _mut24277, _mut24278, _mut24279)) {
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24288, _mut24289, _mut24290, _mut24291, _mut24292); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                    t = V[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24280, _mut24281, _mut24282, _mut24283)];
                                    V[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24284, _mut24285, _mut24286, _mut24287)] = V[i][k];
                                    V[i][k] = t;
                                }
                            }
                            if (ROR_less(k, AOR_minus(m, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24293, _mut24294, _mut24295, _mut24296), "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24297, _mut24298, _mut24299, _mut24300, _mut24301)) {
                                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24310, _mut24311, _mut24312, _mut24313, _mut24314); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87");
                                    t = U[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24302, _mut24303, _mut24304, _mut24305)];
                                    U[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24306, _mut24307, _mut24308, _mut24309)] = U[i][k];
                                    U[i][k] = t;
                                }
                            }
                            k++;
                        }
                        p--;
                    }
                    break;
            }
        }
        // Set the small value tolerance used to calculate rank and pseudo-inverse
        tol = FastMath.max(AOR_multiply(AOR_multiply(m, singularValues[0], "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24325, _mut24326, _mut24327, _mut24328), EPS, "org.apache.commons.math3.linear.SingularValueDecomposition.SingularValueDecomposition_87", _mut24329, _mut24330, _mut24331, _mut24332), FastMath.sqrt(Precision.SAFE_MIN));
        if (!transposed) {
            cachedU = MatrixUtils.createRealMatrix(U);
            cachedV = MatrixUtils.createRealMatrix(V);
        } else {
            cachedU = MatrixUtils.createRealMatrix(V);
            cachedV = MatrixUtils.createRealMatrix(U);
        }
    }

    /**
     * Returns the matrix U of the decomposition.
     * <p>U is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the U matrix
     * @see #getUT()
     */
    public RealMatrix getU() {
        // return the cached matrix
        return cachedU;
    }

    /**
     * Returns the transpose of the matrix U of the decomposition.
     * <p>U is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the U matrix (or null if decomposed matrix is singular)
     * @see #getU()
     */
    public RealMatrix getUT() {
        if (cachedUt == null) {
            cachedUt = getU().transpose();
        }
        // return the cached matrix
        return cachedUt;
    }

    /**
     * Returns the diagonal matrix &Sigma; of the decomposition.
     * <p>&Sigma; is a diagonal matrix. The singular values are provided in
     * non-increasing order, for compatibility with Jama.</p>
     * @return the &Sigma; matrix
     */
    public RealMatrix getS() {
        if (cachedS == null) {
            // cache the matrix for subsequent calls
            cachedS = MatrixUtils.createRealDiagonalMatrix(singularValues);
        }
        return cachedS;
    }

    /**
     * Returns the diagonal elements of the matrix &Sigma; of the decomposition.
     * <p>The singular values are provided in non-increasing order, for
     * compatibility with Jama.</p>
     * @return the diagonal elements of the &Sigma; matrix
     */
    public double[] getSingularValues() {
        return singularValues.clone();
    }

    /**
     * Returns the matrix V of the decomposition.
     * <p>V is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the V matrix (or null if decomposed matrix is singular)
     * @see #getVT()
     */
    public RealMatrix getV() {
        // return the cached matrix
        return cachedV;
    }

    /**
     * Returns the transpose of the matrix V of the decomposition.
     * <p>V is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the V matrix (or null if decomposed matrix is singular)
     * @see #getV()
     */
    public RealMatrix getVT() {
        if (cachedVt == null) {
            cachedVt = getV().transpose();
        }
        // return the cached matrix
        return cachedVt;
    }

    /**
     * Returns the n &times; n covariance matrix.
     * <p>The covariance matrix is V &times; J &times; V<sup>T</sup>
     * where J is the diagonal matrix of the inverse of the squares of
     * the singular values.</p>
     * @param minSingularValue value below which singular values are ignored
     * (a 0 or negative value implies all singular value will be used)
     * @return covariance matrix
     * @exception IllegalArgumentException if minSingularValue is larger than
     * the largest singular value, meaning all singular values are ignored
     */
    public RealMatrix getCovariance(final double minSingularValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.visit_589");
        // get the number of singular values to consider
        final int p = singularValues.length;
        int dimension = 0;
        while ((_mut24343 ? (ROR_less(dimension, p, "org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572", _mut24333, _mut24334, _mut24335, _mut24336, _mut24337) || ROR_greater_equals(singularValues[dimension], minSingularValue, "org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572", _mut24338, _mut24339, _mut24340, _mut24341, _mut24342)) : (ROR_less(dimension, p, "org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572", _mut24333, _mut24334, _mut24335, _mut24336, _mut24337) && ROR_greater_equals(singularValues[dimension], minSingularValue, "org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572", _mut24338, _mut24339, _mut24340, _mut24341, _mut24342)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572");
            ++dimension;
        }
        if (ROR_equals(dimension, 0, "org.apache.commons.math3.linear.SingularValueDecomposition.getCovariance_572", _mut24344, _mut24345, _mut24346, _mut24347, _mut24348)) {
            throw new NumberIsTooLargeException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, minSingularValue, singularValues[0], true);
        }
        final double[][] data = new double[dimension][p];
        getVT().walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void visit(final int row, final int column, final double value) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.visit_589");
                data[row][column] = AOR_divide(value, singularValues[row], "org.apache.commons.math3.linear.SingularValueDecomposition.visit_589", _mut24349, _mut24350, _mut24351, _mut24352);
            }
        }, 0, AOR_minus(dimension, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.visit_589", _mut24353, _mut24354, _mut24355, _mut24356), 0, AOR_minus(p, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.visit_589", _mut24357, _mut24358, _mut24359, _mut24360));
        RealMatrix jv = new Array2DRowRealMatrix(data, false);
        return jv.transpose().multiply(jv);
    }

    /**
     * Returns the L<sub>2</sub> norm of the matrix.
     * <p>The L<sub>2</sub> norm is max(|A &times; u|<sub>2</sub> /
     * |u|<sub>2</sub>), where |.|<sub>2</sub> denotes the vectorial 2-norm
     * (i.e. the traditional euclidian norm).</p>
     * @return norm
     */
    public double getNorm() {
        return singularValues[0];
    }

    /**
     * Return the condition number of the matrix.
     * @return condition number of the matrix
     */
    public double getConditionNumber() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getConditionNumber_615");
        return AOR_divide(singularValues[0], singularValues[AOR_minus(n, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.getConditionNumber_615", _mut24361, _mut24362, _mut24363, _mut24364)], "org.apache.commons.math3.linear.SingularValueDecomposition.getConditionNumber_615", _mut24365, _mut24366, _mut24367, _mut24368);
    }

    /**
     * Computes the inverse of the condition number.
     * In cases of rank deficiency, the {@link #getConditionNumber() condition
     * number} will become undefined.
     *
     * @return the inverse of the condition number.
     */
    public double getInverseConditionNumber() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getInverseConditionNumber_626");
        return AOR_divide(singularValues[AOR_minus(n, 1, "org.apache.commons.math3.linear.SingularValueDecomposition.getInverseConditionNumber_626", _mut24369, _mut24370, _mut24371, _mut24372)], singularValues[0], "org.apache.commons.math3.linear.SingularValueDecomposition.getInverseConditionNumber_626", _mut24373, _mut24374, _mut24375, _mut24376);
    }

    /**
     * Return the effective numerical matrix rank.
     * <p>The effective numerical rank is the number of non-negligible
     * singular values. The threshold used to identify non-negligible
     * terms is max(m,n) &times; ulp(s<sub>1</sub>) where ulp(s<sub>1</sub>)
     * is the least significant bit of the largest singular value.</p>
     * @return effective numerical matrix rank
     */
    public int getRank() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getRank_638");
        int r = 0;
        for (int i = 0; ROR_less(i, singularValues.length, "org.apache.commons.math3.linear.SingularValueDecomposition.getRank_638", _mut24382, _mut24383, _mut24384, _mut24385, _mut24386); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getRank_638");
            if (ROR_greater(singularValues[i], tol, "org.apache.commons.math3.linear.SingularValueDecomposition.getRank_638", _mut24377, _mut24378, _mut24379, _mut24380, _mut24381)) {
                r++;
            }
        }
        return r;
    }

    /**
     * Get a solver for finding the A &times; X = B solution in least square sense.
     * @return a solver
     */
    public DecompositionSolver getSolver() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.getSolver_652");
        return new Solver(singularValues, getUT(), getV(), ROR_equals(getRank(), m, "org.apache.commons.math3.linear.SingularValueDecomposition.getSolver_652", _mut24387, _mut24388, _mut24389, _mut24390, _mut24391), tol);
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * Pseudo-inverse of the initial matrix.
         */
        private final RealMatrix pseudoInverse;

        /**
         * Singularity indicator.
         */
        private boolean nonSingular;

        /**
         * Build a solver from decomposed matrix.
         *
         * @param singularValues Singular values.
         * @param uT U<sup>T</sup> matrix of the decomposition.
         * @param v V matrix of the decomposition.
         * @param nonSingular Singularity indicator.
         * @param tol tolerance for singular values
         */
        private Solver(final double[] singularValues, final RealMatrix uT, final RealMatrix v, final boolean nonSingular, final double tol) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672");
            final double[][] suT = uT.getData();
            for (int i = 0; ROR_less(i, singularValues.length, "org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672", _mut24406, _mut24407, _mut24408, _mut24409, _mut24410); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672");
                final double a;
                if (ROR_greater(singularValues[i], tol, "org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672", _mut24392, _mut24393, _mut24394, _mut24395, _mut24396)) {
                    a = AOR_divide(1, singularValues[i], "org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672", _mut24397, _mut24398, _mut24399, _mut24400);
                } else {
                    a = 0;
                }
                final double[] suTi = suT[i];
                for (int j = 0; ROR_less(j, suTi.length, "org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672", _mut24401, _mut24402, _mut24403, _mut24404, _mut24405); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SingularValueDecomposition.Solver_672");
                    suTi[j] *= a;
                }
            }
            pseudoInverse = v.multiply(new Array2DRowRealMatrix(suT, false));
            this.nonSingular = nonSingular;
        }

        /**
         * Solve the linear equation A &times; X = B in least square sense.
         * <p>
         * The m&times;n matrix A may not be square, the solution X is such that
         * ||A &times; X - B|| is minimal.
         * </p>
         * @param b Right-hand side of the equation A &times; X = B
         * @return a vector X that minimizes the two norm of A &times; X - B
         * @throws org.apache.commons.math3.exception.DimensionMismatchException
         * if the matrices dimensions do not match.
         */
        public RealVector solve(final RealVector b) {
            return pseudoInverse.operate(b);
        }

        /**
         * Solve the linear equation A &times; X = B in least square sense.
         * <p>
         * The m&times;n matrix A may not be square, the solution X is such that
         * ||A &times; X - B|| is minimal.
         * </p>
         *
         * @param b Right-hand side of the equation A &times; X = B
         * @return a matrix X that minimizes the two norm of A &times; X - B
         * @throws org.apache.commons.math3.exception.DimensionMismatchException
         * if the matrices dimensions do not match.
         */
        public RealMatrix solve(final RealMatrix b) {
            return pseudoInverse.multiply(b);
        }

        /**
         * Check if the decomposed matrix is non-singular.
         *
         * @return {@code true} if the decomposed matrix is non-singular.
         */
        public boolean isNonSingular() {
            return nonSingular;
        }

        /**
         * Get the pseudo-inverse of the decomposed matrix.
         *
         * @return the inverse matrix.
         */
        public RealMatrix getInverse() {
            return pseudoInverse;
        }
    }
}
