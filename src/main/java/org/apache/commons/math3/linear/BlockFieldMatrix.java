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

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Cache-friendly implementation of FieldMatrix using a flat arrays to store
 * square blocks of the matrix.
 * <p>
 * This implementation is specially designed to be cache-friendly. Square blocks are
 * stored as small arrays and allow efficient traversal of data both in row major direction
 * and columns major direction, one block at a time. This greatly increases performances
 * for algorithms that use crossed directions loops like multiplication or transposition.
 * </p>
 * <p>
 * The size of square blocks is a static parameter. It may be tuned according to the cache
 * size of the target computer processor. As a rule of thumbs, it should be the largest
 * value that allows three blocks to be simultaneously cached (this is necessary for example
 * for matrix multiplication). The default value is to use 36x36 blocks.
 * </p>
 * <p>
 * The regular blocks represent {@link #BLOCK_SIZE} x {@link #BLOCK_SIZE} squares. Blocks
 * at right hand side and bottom side which may be smaller to fit matrix dimensions. The square
 * blocks are flattened in row major order in single dimension arrays which are therefore
 * {@link #BLOCK_SIZE}<sup>2</sup> elements long for regular blocks. The blocks are themselves
 * organized in row major order.
 * </p>
 * <p>
 * As an example, for a block size of 36x36, a 100x60 matrix would be stored in 6 blocks.
 * Block 0 would be a Field[1296] array holding the upper left 36x36 square, block 1 would be
 * a Field[1296] array holding the upper center 36x36 square, block 2 would be a Field[1008]
 * array holding the upper right 36x28 rectangle, block 3 would be a Field[864] array holding
 * the lower left 24x36 rectangle, block 4 would be a Field[864] array holding the lower center
 * 24x36 rectangle and block 5 would be a Field[672] array holding the lower right 24x28
 * rectangle.
 * </p>
 * <p>
 * The layout complexity overhead versus simple mapping of matrices to java
 * arrays is negligible for small matrices (about 1%). The gain from cache efficiency leads
 * to up to 3-fold improvements for matrices of moderate to large size.
 * </p>
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {

    @Conditional
    public static boolean _mut26577 = false, _mut26578 = false, _mut26579 = false, _mut26580 = false, _mut26581 = false, _mut26582 = false, _mut26583 = false, _mut26584 = false, _mut26585 = false, _mut26586 = false, _mut26587 = false, _mut26588 = false, _mut26589 = false, _mut26590 = false, _mut26591 = false, _mut26592 = false, _mut26593 = false, _mut26594 = false, _mut26595 = false, _mut26596 = false, _mut26597 = false, _mut26598 = false, _mut26599 = false, _mut26600 = false, _mut26601 = false, _mut26602 = false, _mut26603 = false, _mut26604 = false, _mut26605 = false, _mut26606 = false, _mut26607 = false, _mut26608 = false, _mut26609 = false, _mut26610 = false, _mut26611 = false, _mut26612 = false, _mut26613 = false, _mut26614 = false, _mut26615 = false, _mut26616 = false, _mut26617 = false, _mut26618 = false, _mut26619 = false, _mut26620 = false, _mut26621 = false, _mut26622 = false, _mut26623 = false, _mut26624 = false, _mut26625 = false, _mut26626 = false, _mut26627 = false, _mut26628 = false, _mut26629 = false, _mut26630 = false, _mut26631 = false, _mut26632 = false, _mut26633 = false, _mut26634 = false, _mut26635 = false, _mut26636 = false, _mut26637 = false, _mut26638 = false, _mut26639 = false, _mut26640 = false, _mut26641 = false, _mut26642 = false, _mut26643 = false, _mut26644 = false, _mut26645 = false, _mut26646 = false, _mut26647 = false, _mut26648 = false, _mut26649 = false, _mut26650 = false, _mut26651 = false, _mut26652 = false, _mut26653 = false, _mut26654 = false, _mut26655 = false, _mut26656 = false, _mut26657 = false, _mut26658 = false, _mut26659 = false, _mut26660 = false, _mut26661 = false, _mut26662 = false, _mut26663 = false, _mut26664 = false, _mut26665 = false, _mut26666 = false, _mut26667 = false, _mut26668 = false, _mut26669 = false, _mut26670 = false, _mut26671 = false, _mut26672 = false, _mut26673 = false, _mut26674 = false, _mut26675 = false, _mut26676 = false, _mut26677 = false, _mut26678 = false, _mut26679 = false, _mut26680 = false, _mut26681 = false, _mut26682 = false, _mut26683 = false, _mut26684 = false, _mut26685 = false, _mut26686 = false, _mut26687 = false, _mut26688 = false, _mut26689 = false, _mut26690 = false, _mut26691 = false, _mut26692 = false, _mut26693 = false, _mut26694 = false, _mut26695 = false, _mut26696 = false, _mut26697 = false, _mut26698 = false, _mut26699 = false, _mut26700 = false, _mut26701 = false, _mut26702 = false, _mut26703 = false, _mut26704 = false, _mut26705 = false, _mut26706 = false, _mut26707 = false, _mut26708 = false, _mut26709 = false, _mut26710 = false, _mut26711 = false, _mut26712 = false, _mut26713 = false, _mut26714 = false, _mut26715 = false, _mut26716 = false, _mut26717 = false, _mut26718 = false, _mut26719 = false, _mut26720 = false, _mut26721 = false, _mut26722 = false, _mut26723 = false, _mut26724 = false, _mut26725 = false, _mut26726 = false, _mut26727 = false, _mut26728 = false, _mut26729 = false, _mut26730 = false, _mut26731 = false, _mut26732 = false, _mut26733 = false, _mut26734 = false, _mut26735 = false, _mut26736 = false, _mut26737 = false, _mut26738 = false, _mut26739 = false, _mut26740 = false, _mut26741 = false, _mut26742 = false, _mut26743 = false, _mut26744 = false, _mut26745 = false, _mut26746 = false, _mut26747 = false, _mut26748 = false, _mut26749 = false, _mut26750 = false, _mut26751 = false, _mut26752 = false, _mut26753 = false, _mut26754 = false, _mut26755 = false, _mut26756 = false, _mut26757 = false, _mut26758 = false, _mut26759 = false, _mut26760 = false, _mut26761 = false, _mut26762 = false, _mut26763 = false, _mut26764 = false, _mut26765 = false, _mut26766 = false, _mut26767 = false, _mut26768 = false, _mut26769 = false, _mut26770 = false, _mut26771 = false, _mut26772 = false, _mut26773 = false, _mut26774 = false, _mut26775 = false, _mut26776 = false, _mut26777 = false, _mut26778 = false, _mut26779 = false, _mut26780 = false, _mut26781 = false, _mut26782 = false, _mut26783 = false, _mut26784 = false, _mut26785 = false, _mut26786 = false, _mut26787 = false, _mut26788 = false, _mut26789 = false, _mut26790 = false, _mut26791 = false, _mut26792 = false, _mut26793 = false, _mut26794 = false, _mut26795 = false, _mut26796 = false, _mut26797 = false, _mut26798 = false, _mut26799 = false, _mut26800 = false, _mut26801 = false, _mut26802 = false, _mut26803 = false, _mut26804 = false, _mut26805 = false, _mut26806 = false, _mut26807 = false, _mut26808 = false, _mut26809 = false, _mut26810 = false, _mut26811 = false, _mut26812 = false, _mut26813 = false, _mut26814 = false, _mut26815 = false, _mut26816 = false, _mut26817 = false, _mut26818 = false, _mut26819 = false, _mut26820 = false, _mut26821 = false, _mut26822 = false, _mut26823 = false, _mut26824 = false, _mut26825 = false, _mut26826 = false, _mut26827 = false, _mut26828 = false, _mut26829 = false, _mut26830 = false, _mut26831 = false, _mut26832 = false, _mut26833 = false, _mut26834 = false, _mut26835 = false, _mut26836 = false, _mut26837 = false, _mut26838 = false, _mut26839 = false, _mut26840 = false, _mut26841 = false, _mut26842 = false, _mut26843 = false, _mut26844 = false, _mut26845 = false, _mut26846 = false, _mut26847 = false, _mut26848 = false, _mut26849 = false, _mut26850 = false, _mut26851 = false, _mut26852 = false, _mut26853 = false, _mut26854 = false, _mut26855 = false, _mut26856 = false, _mut26857 = false, _mut26858 = false, _mut26859 = false, _mut26860 = false, _mut26861 = false, _mut26862 = false, _mut26863 = false, _mut26864 = false, _mut26865 = false, _mut26866 = false, _mut26867 = false, _mut26868 = false, _mut26869 = false, _mut26870 = false, _mut26871 = false, _mut26872 = false, _mut26873 = false, _mut26874 = false, _mut26875 = false, _mut26876 = false, _mut26877 = false, _mut26878 = false, _mut26879 = false, _mut26880 = false, _mut26881 = false, _mut26882 = false, _mut26883 = false, _mut26884 = false, _mut26885 = false, _mut26886 = false, _mut26887 = false, _mut26888 = false, _mut26889 = false, _mut26890 = false, _mut26891 = false, _mut26892 = false, _mut26893 = false, _mut26894 = false, _mut26895 = false, _mut26896 = false, _mut26897 = false, _mut26898 = false, _mut26899 = false, _mut26900 = false, _mut26901 = false, _mut26902 = false, _mut26903 = false, _mut26904 = false, _mut26905 = false, _mut26906 = false, _mut26907 = false, _mut26908 = false, _mut26909 = false, _mut26910 = false, _mut26911 = false, _mut26912 = false, _mut26913 = false, _mut26914 = false, _mut26915 = false, _mut26916 = false, _mut26917 = false, _mut26918 = false, _mut26919 = false, _mut26920 = false, _mut26921 = false, _mut26922 = false, _mut26923 = false, _mut26924 = false, _mut26925 = false, _mut26926 = false, _mut26927 = false, _mut26928 = false, _mut26929 = false, _mut26930 = false, _mut26931 = false, _mut26932 = false, _mut26933 = false, _mut26934 = false, _mut26935 = false, _mut26936 = false, _mut26937 = false, _mut26938 = false, _mut26939 = false, _mut26940 = false, _mut26941 = false, _mut26942 = false, _mut26943 = false, _mut26944 = false, _mut26945 = false, _mut26946 = false, _mut26947 = false, _mut26948 = false, _mut26949 = false, _mut26950 = false, _mut26951 = false, _mut26952 = false, _mut26953 = false, _mut26954 = false, _mut26955 = false, _mut26956 = false, _mut26957 = false, _mut26958 = false, _mut26959 = false, _mut26960 = false, _mut26961 = false, _mut26962 = false, _mut26963 = false, _mut26964 = false, _mut26965 = false, _mut26966 = false, _mut26967 = false, _mut26968 = false, _mut26969 = false, _mut26970 = false, _mut26971 = false, _mut26972 = false, _mut26973 = false, _mut26974 = false, _mut26975 = false, _mut26976 = false, _mut26977 = false, _mut26978 = false, _mut26979 = false, _mut26980 = false, _mut26981 = false, _mut26982 = false, _mut26983 = false, _mut26984 = false, _mut26985 = false, _mut26986 = false, _mut26987 = false, _mut26988 = false, _mut26989 = false, _mut26990 = false, _mut26991 = false, _mut26992 = false, _mut26993 = false, _mut26994 = false, _mut26995 = false, _mut26996 = false, _mut26997 = false, _mut26998 = false, _mut26999 = false, _mut27000 = false, _mut27001 = false, _mut27002 = false, _mut27003 = false, _mut27004 = false, _mut27005 = false, _mut27006 = false, _mut27007 = false, _mut27008 = false, _mut27009 = false, _mut27010 = false, _mut27011 = false, _mut27012 = false, _mut27013 = false, _mut27014 = false, _mut27015 = false, _mut27016 = false, _mut27017 = false, _mut27018 = false, _mut27019 = false, _mut27020 = false, _mut27021 = false, _mut27022 = false, _mut27023 = false, _mut27024 = false, _mut27025 = false, _mut27026 = false, _mut27027 = false, _mut27028 = false, _mut27029 = false, _mut27030 = false, _mut27031 = false, _mut27032 = false, _mut27033 = false, _mut27034 = false, _mut27035 = false, _mut27036 = false, _mut27037 = false, _mut27038 = false, _mut27039 = false, _mut27040 = false, _mut27041 = false, _mut27042 = false, _mut27043 = false, _mut27044 = false, _mut27045 = false, _mut27046 = false, _mut27047 = false, _mut27048 = false, _mut27049 = false, _mut27050 = false, _mut27051 = false, _mut27052 = false, _mut27053 = false, _mut27054 = false, _mut27055 = false, _mut27056 = false, _mut27057 = false, _mut27058 = false, _mut27059 = false, _mut27060 = false, _mut27061 = false, _mut27062 = false, _mut27063 = false, _mut27064 = false, _mut27065 = false, _mut27066 = false, _mut27067 = false, _mut27068 = false, _mut27069 = false, _mut27070 = false, _mut27071 = false, _mut27072 = false, _mut27073 = false, _mut27074 = false, _mut27075 = false, _mut27076 = false, _mut27077 = false, _mut27078 = false, _mut27079 = false, _mut27080 = false, _mut27081 = false, _mut27082 = false, _mut27083 = false, _mut27084 = false, _mut27085 = false, _mut27086 = false, _mut27087 = false, _mut27088 = false, _mut27089 = false, _mut27090 = false, _mut27091 = false, _mut27092 = false, _mut27093 = false, _mut27094 = false, _mut27095 = false, _mut27096 = false, _mut27097 = false, _mut27098 = false, _mut27099 = false, _mut27100 = false, _mut27101 = false, _mut27102 = false, _mut27103 = false, _mut27104 = false, _mut27105 = false, _mut27106 = false, _mut27107 = false, _mut27108 = false, _mut27109 = false, _mut27110 = false, _mut27111 = false, _mut27112 = false, _mut27113 = false, _mut27114 = false, _mut27115 = false, _mut27116 = false, _mut27117 = false, _mut27118 = false, _mut27119 = false, _mut27120 = false, _mut27121 = false, _mut27122 = false, _mut27123 = false, _mut27124 = false, _mut27125 = false, _mut27126 = false, _mut27127 = false, _mut27128 = false, _mut27129 = false, _mut27130 = false, _mut27131 = false, _mut27132 = false, _mut27133 = false, _mut27134 = false, _mut27135 = false, _mut27136 = false, _mut27137 = false, _mut27138 = false, _mut27139 = false, _mut27140 = false, _mut27141 = false, _mut27142 = false, _mut27143 = false, _mut27144 = false, _mut27145 = false, _mut27146 = false, _mut27147 = false, _mut27148 = false, _mut27149 = false, _mut27150 = false, _mut27151 = false, _mut27152 = false, _mut27153 = false, _mut27154 = false, _mut27155 = false, _mut27156 = false, _mut27157 = false, _mut27158 = false, _mut27159 = false, _mut27160 = false, _mut27161 = false, _mut27162 = false, _mut27163 = false, _mut27164 = false, _mut27165 = false, _mut27166 = false, _mut27167 = false, _mut27168 = false, _mut27169 = false, _mut27170 = false, _mut27171 = false, _mut27172 = false, _mut27173 = false, _mut27174 = false, _mut27175 = false, _mut27176 = false, _mut27177 = false, _mut27178 = false, _mut27179 = false, _mut27180 = false, _mut27181 = false, _mut27182 = false, _mut27183 = false, _mut27184 = false, _mut27185 = false, _mut27186 = false, _mut27187 = false, _mut27188 = false, _mut27189 = false, _mut27190 = false, _mut27191 = false, _mut27192 = false, _mut27193 = false, _mut27194 = false, _mut27195 = false, _mut27196 = false, _mut27197 = false, _mut27198 = false, _mut27199 = false, _mut27200 = false, _mut27201 = false, _mut27202 = false, _mut27203 = false, _mut27204 = false, _mut27205 = false, _mut27206 = false, _mut27207 = false, _mut27208 = false, _mut27209 = false, _mut27210 = false, _mut27211 = false, _mut27212 = false, _mut27213 = false, _mut27214 = false, _mut27215 = false, _mut27216 = false, _mut27217 = false, _mut27218 = false, _mut27219 = false, _mut27220 = false, _mut27221 = false, _mut27222 = false, _mut27223 = false, _mut27224 = false, _mut27225 = false, _mut27226 = false, _mut27227 = false, _mut27228 = false, _mut27229 = false, _mut27230 = false, _mut27231 = false, _mut27232 = false, _mut27233 = false, _mut27234 = false, _mut27235 = false, _mut27236 = false, _mut27237 = false, _mut27238 = false, _mut27239 = false, _mut27240 = false, _mut27241 = false, _mut27242 = false, _mut27243 = false, _mut27244 = false, _mut27245 = false, _mut27246 = false, _mut27247 = false, _mut27248 = false, _mut27249 = false, _mut27250 = false, _mut27251 = false, _mut27252 = false, _mut27253 = false, _mut27254 = false, _mut27255 = false, _mut27256 = false, _mut27257 = false, _mut27258 = false, _mut27259 = false, _mut27260 = false, _mut27261 = false, _mut27262 = false, _mut27263 = false, _mut27264 = false, _mut27265 = false, _mut27266 = false, _mut27267 = false, _mut27268 = false, _mut27269 = false, _mut27270 = false, _mut27271 = false, _mut27272 = false, _mut27273 = false, _mut27274 = false, _mut27275 = false, _mut27276 = false, _mut27277 = false, _mut27278 = false, _mut27279 = false, _mut27280 = false, _mut27281 = false, _mut27282 = false, _mut27283 = false, _mut27284 = false, _mut27285 = false, _mut27286 = false, _mut27287 = false, _mut27288 = false, _mut27289 = false, _mut27290 = false, _mut27291 = false, _mut27292 = false, _mut27293 = false, _mut27294 = false, _mut27295 = false, _mut27296 = false, _mut27297 = false, _mut27298 = false, _mut27299 = false, _mut27300 = false, _mut27301 = false, _mut27302 = false, _mut27303 = false, _mut27304 = false, _mut27305 = false, _mut27306 = false, _mut27307 = false, _mut27308 = false, _mut27309 = false, _mut27310 = false, _mut27311 = false, _mut27312 = false, _mut27313 = false, _mut27314 = false, _mut27315 = false, _mut27316 = false, _mut27317 = false, _mut27318 = false, _mut27319 = false, _mut27320 = false, _mut27321 = false, _mut27322 = false, _mut27323 = false, _mut27324 = false, _mut27325 = false, _mut27326 = false, _mut27327 = false, _mut27328 = false, _mut27329 = false, _mut27330 = false, _mut27331 = false, _mut27332 = false, _mut27333 = false, _mut27334 = false, _mut27335 = false, _mut27336 = false, _mut27337 = false, _mut27338 = false, _mut27339 = false, _mut27340 = false, _mut27341 = false, _mut27342 = false, _mut27343 = false, _mut27344 = false, _mut27345 = false, _mut27346 = false, _mut27347 = false, _mut27348 = false, _mut27349 = false, _mut27350 = false, _mut27351 = false, _mut27352 = false, _mut27353 = false, _mut27354 = false, _mut27355 = false, _mut27356 = false, _mut27357 = false, _mut27358 = false, _mut27359 = false, _mut27360 = false, _mut27361 = false, _mut27362 = false, _mut27363 = false, _mut27364 = false, _mut27365 = false, _mut27366 = false, _mut27367 = false, _mut27368 = false, _mut27369 = false, _mut27370 = false, _mut27371 = false, _mut27372 = false, _mut27373 = false, _mut27374 = false, _mut27375 = false, _mut27376 = false, _mut27377 = false, _mut27378 = false, _mut27379 = false, _mut27380 = false, _mut27381 = false, _mut27382 = false, _mut27383 = false, _mut27384 = false, _mut27385 = false, _mut27386 = false, _mut27387 = false, _mut27388 = false, _mut27389 = false, _mut27390 = false, _mut27391 = false, _mut27392 = false, _mut27393 = false, _mut27394 = false, _mut27395 = false, _mut27396 = false, _mut27397 = false, _mut27398 = false, _mut27399 = false, _mut27400 = false, _mut27401 = false, _mut27402 = false, _mut27403 = false, _mut27404 = false, _mut27405 = false, _mut27406 = false, _mut27407 = false, _mut27408 = false, _mut27409 = false, _mut27410 = false, _mut27411 = false, _mut27412 = false, _mut27413 = false, _mut27414 = false, _mut27415 = false, _mut27416 = false, _mut27417 = false, _mut27418 = false, _mut27419 = false, _mut27420 = false, _mut27421 = false, _mut27422 = false, _mut27423 = false, _mut27424 = false, _mut27425 = false, _mut27426 = false, _mut27427 = false, _mut27428 = false, _mut27429 = false, _mut27430 = false, _mut27431 = false, _mut27432 = false, _mut27433 = false, _mut27434 = false, _mut27435 = false, _mut27436 = false, _mut27437 = false, _mut27438 = false, _mut27439 = false, _mut27440 = false, _mut27441 = false, _mut27442 = false, _mut27443 = false, _mut27444 = false, _mut27445 = false, _mut27446 = false, _mut27447 = false, _mut27448 = false, _mut27449 = false, _mut27450 = false, _mut27451 = false, _mut27452 = false, _mut27453 = false, _mut27454 = false, _mut27455 = false, _mut27456 = false, _mut27457 = false, _mut27458 = false, _mut27459 = false, _mut27460 = false, _mut27461 = false, _mut27462 = false, _mut27463 = false, _mut27464 = false, _mut27465 = false, _mut27466 = false, _mut27467 = false, _mut27468 = false, _mut27469 = false, _mut27470 = false, _mut27471 = false, _mut27472 = false, _mut27473 = false, _mut27474 = false, _mut27475 = false, _mut27476 = false, _mut27477 = false, _mut27478 = false, _mut27479 = false, _mut27480 = false, _mut27481 = false, _mut27482 = false, _mut27483 = false, _mut27484 = false, _mut27485 = false, _mut27486 = false, _mut27487 = false, _mut27488 = false, _mut27489 = false, _mut27490 = false, _mut27491 = false, _mut27492 = false, _mut27493 = false, _mut27494 = false, _mut27495 = false, _mut27496 = false, _mut27497 = false, _mut27498 = false, _mut27499 = false, _mut27500 = false, _mut27501 = false, _mut27502 = false, _mut27503 = false, _mut27504 = false, _mut27505 = false, _mut27506 = false, _mut27507 = false, _mut27508 = false, _mut27509 = false, _mut27510 = false, _mut27511 = false, _mut27512 = false, _mut27513 = false, _mut27514 = false, _mut27515 = false, _mut27516 = false, _mut27517 = false, _mut27518 = false, _mut27519 = false, _mut27520 = false, _mut27521 = false, _mut27522 = false, _mut27523 = false, _mut27524 = false, _mut27525 = false, _mut27526 = false, _mut27527 = false, _mut27528 = false, _mut27529 = false, _mut27530 = false, _mut27531 = false, _mut27532 = false, _mut27533 = false, _mut27534 = false, _mut27535 = false, _mut27536 = false, _mut27537 = false, _mut27538 = false, _mut27539 = false, _mut27540 = false, _mut27541 = false, _mut27542 = false, _mut27543 = false, _mut27544 = false, _mut27545 = false, _mut27546 = false, _mut27547 = false, _mut27548 = false, _mut27549 = false, _mut27550 = false, _mut27551 = false, _mut27552 = false, _mut27553 = false, _mut27554 = false, _mut27555 = false, _mut27556 = false, _mut27557 = false, _mut27558 = false, _mut27559 = false, _mut27560 = false, _mut27561 = false, _mut27562 = false, _mut27563 = false, _mut27564 = false, _mut27565 = false, _mut27566 = false, _mut27567 = false, _mut27568 = false, _mut27569 = false, _mut27570 = false, _mut27571 = false, _mut27572 = false, _mut27573 = false, _mut27574 = false, _mut27575 = false, _mut27576 = false, _mut27577 = false, _mut27578 = false, _mut27579 = false, _mut27580 = false, _mut27581 = false, _mut27582 = false, _mut27583 = false, _mut27584 = false, _mut27585 = false, _mut27586 = false, _mut27587 = false, _mut27588 = false, _mut27589 = false, _mut27590 = false, _mut27591 = false, _mut27592 = false, _mut27593 = false, _mut27594 = false, _mut27595 = false, _mut27596 = false, _mut27597 = false, _mut27598 = false, _mut27599 = false, _mut27600 = false, _mut27601 = false, _mut27602 = false, _mut27603 = false, _mut27604 = false, _mut27605 = false, _mut27606 = false, _mut27607 = false, _mut27608 = false, _mut27609 = false, _mut27610 = false, _mut27611 = false, _mut27612 = false, _mut27613 = false, _mut27614 = false, _mut27615 = false, _mut27616 = false, _mut27617 = false, _mut27618 = false, _mut27619 = false, _mut27620 = false, _mut27621 = false, _mut27622 = false, _mut27623 = false, _mut27624 = false, _mut27625 = false, _mut27626 = false, _mut27627 = false, _mut27628 = false, _mut27629 = false, _mut27630 = false, _mut27631 = false, _mut27632 = false, _mut27633 = false, _mut27634 = false, _mut27635 = false, _mut27636 = false, _mut27637 = false, _mut27638 = false, _mut27639 = false, _mut27640 = false, _mut27641 = false, _mut27642 = false, _mut27643 = false, _mut27644 = false, _mut27645 = false, _mut27646 = false, _mut27647 = false, _mut27648 = false, _mut27649 = false, _mut27650 = false, _mut27651 = false, _mut27652 = false, _mut27653 = false, _mut27654 = false, _mut27655 = false, _mut27656 = false, _mut27657 = false, _mut27658 = false, _mut27659 = false, _mut27660 = false, _mut27661 = false, _mut27662 = false, _mut27663 = false, _mut27664 = false, _mut27665 = false, _mut27666 = false, _mut27667 = false, _mut27668 = false, _mut27669 = false, _mut27670 = false, _mut27671 = false, _mut27672 = false, _mut27673 = false, _mut27674 = false, _mut27675 = false, _mut27676 = false, _mut27677 = false, _mut27678 = false, _mut27679 = false, _mut27680 = false, _mut27681 = false, _mut27682 = false, _mut27683 = false, _mut27684 = false, _mut27685 = false, _mut27686 = false, _mut27687 = false, _mut27688 = false, _mut27689 = false, _mut27690 = false, _mut27691 = false, _mut27692 = false, _mut27693 = false, _mut27694 = false, _mut27695 = false, _mut27696 = false, _mut27697 = false, _mut27698 = false, _mut27699 = false, _mut27700 = false, _mut27701 = false, _mut27702 = false, _mut27703 = false, _mut27704 = false, _mut27705 = false, _mut27706 = false, _mut27707 = false, _mut27708 = false, _mut27709 = false, _mut27710 = false, _mut27711 = false, _mut27712 = false, _mut27713 = false, _mut27714 = false, _mut27715 = false, _mut27716 = false, _mut27717 = false, _mut27718 = false, _mut27719 = false, _mut27720 = false, _mut27721 = false, _mut27722 = false, _mut27723 = false, _mut27724 = false, _mut27725 = false, _mut27726 = false, _mut27727 = false, _mut27728 = false, _mut27729 = false, _mut27730 = false, _mut27731 = false, _mut27732 = false, _mut27733 = false, _mut27734 = false, _mut27735 = false, _mut27736 = false, _mut27737 = false, _mut27738 = false, _mut27739 = false, _mut27740 = false, _mut27741 = false, _mut27742 = false, _mut27743 = false, _mut27744 = false, _mut27745 = false, _mut27746 = false, _mut27747 = false, _mut27748 = false, _mut27749 = false, _mut27750 = false, _mut27751 = false, _mut27752 = false, _mut27753 = false, _mut27754 = false, _mut27755 = false, _mut27756 = false, _mut27757 = false, _mut27758 = false, _mut27759 = false, _mut27760 = false, _mut27761 = false, _mut27762 = false, _mut27763 = false, _mut27764 = false, _mut27765 = false, _mut27766 = false, _mut27767 = false, _mut27768 = false, _mut27769 = false, _mut27770 = false, _mut27771 = false, _mut27772 = false, _mut27773 = false, _mut27774 = false, _mut27775 = false, _mut27776 = false, _mut27777 = false, _mut27778 = false, _mut27779 = false, _mut27780 = false, _mut27781 = false, _mut27782 = false, _mut27783 = false, _mut27784 = false, _mut27785 = false, _mut27786 = false, _mut27787 = false, _mut27788 = false, _mut27789 = false, _mut27790 = false, _mut27791 = false, _mut27792 = false, _mut27793 = false, _mut27794 = false, _mut27795 = false, _mut27796 = false, _mut27797 = false, _mut27798 = false, _mut27799 = false, _mut27800 = false, _mut27801 = false, _mut27802 = false, _mut27803 = false, _mut27804 = false, _mut27805 = false, _mut27806 = false, _mut27807 = false, _mut27808 = false, _mut27809 = false, _mut27810 = false, _mut27811 = false, _mut27812 = false, _mut27813 = false, _mut27814 = false, _mut27815 = false, _mut27816 = false, _mut27817 = false, _mut27818 = false, _mut27819 = false, _mut27820 = false, _mut27821 = false, _mut27822 = false, _mut27823 = false, _mut27824 = false, _mut27825 = false, _mut27826 = false, _mut27827 = false, _mut27828 = false, _mut27829 = false, _mut27830 = false, _mut27831 = false, _mut27832 = false, _mut27833 = false, _mut27834 = false, _mut27835 = false, _mut27836 = false, _mut27837 = false, _mut27838 = false, _mut27839 = false, _mut27840 = false, _mut27841 = false, _mut27842 = false, _mut27843 = false, _mut27844 = false, _mut27845 = false, _mut27846 = false, _mut27847 = false, _mut27848 = false, _mut27849 = false, _mut27850 = false, _mut27851 = false, _mut27852 = false, _mut27853 = false, _mut27854 = false, _mut27855 = false, _mut27856 = false, _mut27857 = false, _mut27858 = false, _mut27859 = false, _mut27860 = false, _mut27861 = false, _mut27862 = false, _mut27863 = false, _mut27864 = false, _mut27865 = false, _mut27866 = false, _mut27867 = false, _mut27868 = false, _mut27869 = false, _mut27870 = false, _mut27871 = false, _mut27872 = false, _mut27873 = false, _mut27874 = false, _mut27875 = false, _mut27876 = false, _mut27877 = false, _mut27878 = false, _mut27879 = false, _mut27880 = false, _mut27881 = false, _mut27882 = false, _mut27883 = false, _mut27884 = false, _mut27885 = false, _mut27886 = false, _mut27887 = false, _mut27888 = false, _mut27889 = false, _mut27890 = false, _mut27891 = false, _mut27892 = false, _mut27893 = false, _mut27894 = false, _mut27895 = false, _mut27896 = false, _mut27897 = false, _mut27898 = false, _mut27899 = false, _mut27900 = false, _mut27901 = false, _mut27902 = false, _mut27903 = false, _mut27904 = false, _mut27905 = false, _mut27906 = false, _mut27907 = false, _mut27908 = false, _mut27909 = false, _mut27910 = false, _mut27911 = false, _mut27912 = false, _mut27913 = false, _mut27914 = false, _mut27915 = false, _mut27916 = false, _mut27917 = false, _mut27918 = false, _mut27919 = false, _mut27920 = false, _mut27921 = false, _mut27922 = false, _mut27923 = false, _mut27924 = false, _mut27925 = false, _mut27926 = false, _mut27927 = false, _mut27928 = false, _mut27929 = false, _mut27930 = false, _mut27931 = false, _mut27932 = false, _mut27933 = false, _mut27934 = false, _mut27935 = false, _mut27936 = false, _mut27937 = false, _mut27938 = false, _mut27939 = false, _mut27940 = false, _mut27941 = false, _mut27942 = false, _mut27943 = false, _mut27944 = false, _mut27945 = false, _mut27946 = false, _mut27947 = false, _mut27948 = false, _mut27949 = false, _mut27950 = false, _mut27951 = false, _mut27952 = false, _mut27953 = false, _mut27954 = false, _mut27955 = false, _mut27956 = false, _mut27957 = false, _mut27958 = false, _mut27959 = false, _mut27960 = false, _mut27961 = false, _mut27962 = false, _mut27963 = false, _mut27964 = false, _mut27965 = false, _mut27966 = false, _mut27967 = false, _mut27968 = false, _mut27969 = false, _mut27970 = false, _mut27971 = false, _mut27972 = false, _mut27973 = false, _mut27974 = false, _mut27975 = false, _mut27976 = false, _mut27977 = false, _mut27978 = false, _mut27979 = false, _mut27980 = false, _mut27981 = false, _mut27982 = false, _mut27983 = false, _mut27984 = false, _mut27985 = false, _mut27986 = false, _mut27987 = false, _mut27988 = false, _mut27989 = false, _mut27990 = false, _mut27991 = false, _mut27992 = false, _mut27993 = false, _mut27994 = false, _mut27995 = false, _mut27996 = false, _mut27997 = false, _mut27998 = false, _mut27999 = false, _mut28000 = false, _mut28001 = false, _mut28002 = false, _mut28003 = false, _mut28004 = false, _mut28005 = false, _mut28006 = false, _mut28007 = false, _mut28008 = false, _mut28009 = false, _mut28010 = false, _mut28011 = false, _mut28012 = false, _mut28013 = false, _mut28014 = false, _mut28015 = false, _mut28016 = false, _mut28017 = false, _mut28018 = false, _mut28019 = false, _mut28020 = false, _mut28021 = false, _mut28022 = false, _mut28023 = false, _mut28024 = false, _mut28025 = false, _mut28026 = false, _mut28027 = false, _mut28028 = false, _mut28029 = false, _mut28030 = false, _mut28031 = false, _mut28032 = false, _mut28033 = false, _mut28034 = false, _mut28035 = false, _mut28036 = false, _mut28037 = false, _mut28038 = false, _mut28039 = false, _mut28040 = false, _mut28041 = false, _mut28042 = false, _mut28043 = false, _mut28044 = false, _mut28045 = false, _mut28046 = false, _mut28047 = false, _mut28048 = false, _mut28049 = false, _mut28050 = false, _mut28051 = false, _mut28052 = false, _mut28053 = false, _mut28054 = false, _mut28055 = false, _mut28056 = false, _mut28057 = false, _mut28058 = false, _mut28059 = false, _mut28060 = false, _mut28061 = false, _mut28062 = false, _mut28063 = false, _mut28064 = false, _mut28065 = false, _mut28066 = false, _mut28067 = false, _mut28068 = false, _mut28069 = false, _mut28070 = false, _mut28071 = false, _mut28072 = false, _mut28073 = false, _mut28074 = false, _mut28075 = false, _mut28076 = false, _mut28077 = false, _mut28078 = false, _mut28079 = false, _mut28080 = false, _mut28081 = false, _mut28082 = false, _mut28083 = false, _mut28084 = false, _mut28085 = false, _mut28086 = false, _mut28087 = false, _mut28088 = false, _mut28089 = false, _mut28090 = false, _mut28091 = false, _mut28092 = false, _mut28093 = false, _mut28094 = false, _mut28095 = false, _mut28096 = false, _mut28097 = false, _mut28098 = false, _mut28099 = false, _mut28100 = false, _mut28101 = false, _mut28102 = false, _mut28103 = false, _mut28104 = false, _mut28105 = false, _mut28106 = false, _mut28107 = false, _mut28108 = false, _mut28109 = false, _mut28110 = false, _mut28111 = false, _mut28112 = false, _mut28113 = false, _mut28114 = false, _mut28115 = false, _mut28116 = false, _mut28117 = false, _mut28118 = false, _mut28119 = false, _mut28120 = false, _mut28121 = false, _mut28122 = false, _mut28123 = false, _mut28124 = false, _mut28125 = false, _mut28126 = false, _mut28127 = false, _mut28128 = false, _mut28129 = false, _mut28130 = false, _mut28131 = false, _mut28132 = false, _mut28133 = false, _mut28134 = false, _mut28135 = false, _mut28136 = false, _mut28137 = false, _mut28138 = false, _mut28139 = false, _mut28140 = false, _mut28141 = false, _mut28142 = false, _mut28143 = false, _mut28144 = false, _mut28145 = false, _mut28146 = false, _mut28147 = false, _mut28148 = false, _mut28149 = false, _mut28150 = false, _mut28151 = false, _mut28152 = false, _mut28153 = false, _mut28154 = false, _mut28155 = false, _mut28156 = false, _mut28157 = false, _mut28158 = false, _mut28159 = false, _mut28160 = false, _mut28161 = false, _mut28162 = false, _mut28163 = false, _mut28164 = false, _mut28165 = false, _mut28166 = false, _mut28167 = false, _mut28168 = false, _mut28169 = false, _mut28170 = false, _mut28171 = false, _mut28172 = false, _mut28173 = false, _mut28174 = false, _mut28175 = false, _mut28176 = false, _mut28177 = false, _mut28178 = false, _mut28179 = false, _mut28180 = false, _mut28181 = false, _mut28182 = false, _mut28183 = false, _mut28184 = false, _mut28185 = false, _mut28186 = false, _mut28187 = false, _mut28188 = false, _mut28189 = false, _mut28190 = false, _mut28191 = false, _mut28192 = false, _mut28193 = false, _mut28194 = false, _mut28195 = false, _mut28196 = false, _mut28197 = false, _mut28198 = false, _mut28199 = false, _mut28200 = false, _mut28201 = false, _mut28202 = false, _mut28203 = false, _mut28204 = false, _mut28205 = false, _mut28206 = false, _mut28207 = false, _mut28208 = false, _mut28209 = false, _mut28210 = false, _mut28211 = false, _mut28212 = false, _mut28213 = false, _mut28214 = false, _mut28215 = false, _mut28216 = false, _mut28217 = false, _mut28218 = false, _mut28219 = false, _mut28220 = false, _mut28221 = false, _mut28222 = false, _mut28223 = false, _mut28224 = false, _mut28225 = false, _mut28226 = false, _mut28227 = false, _mut28228 = false, _mut28229 = false, _mut28230 = false, _mut28231 = false, _mut28232 = false, _mut28233 = false, _mut28234 = false, _mut28235 = false, _mut28236 = false, _mut28237 = false, _mut28238 = false, _mut28239 = false, _mut28240 = false, _mut28241 = false, _mut28242 = false, _mut28243 = false, _mut28244 = false, _mut28245 = false, _mut28246 = false, _mut28247 = false, _mut28248 = false, _mut28249 = false, _mut28250 = false, _mut28251 = false, _mut28252 = false, _mut28253 = false, _mut28254 = false, _mut28255 = false, _mut28256 = false, _mut28257 = false, _mut28258 = false, _mut28259 = false, _mut28260 = false, _mut28261 = false, _mut28262 = false, _mut28263 = false, _mut28264 = false, _mut28265 = false, _mut28266 = false, _mut28267 = false, _mut28268 = false, _mut28269 = false, _mut28270 = false, _mut28271 = false, _mut28272 = false, _mut28273 = false, _mut28274 = false, _mut28275 = false, _mut28276 = false, _mut28277 = false, _mut28278 = false, _mut28279 = false, _mut28280 = false, _mut28281 = false, _mut28282 = false, _mut28283 = false, _mut28284 = false, _mut28285 = false, _mut28286 = false, _mut28287 = false, _mut28288 = false, _mut28289 = false, _mut28290 = false, _mut28291 = false, _mut28292 = false, _mut28293 = false, _mut28294 = false, _mut28295 = false, _mut28296 = false, _mut28297 = false, _mut28298 = false, _mut28299 = false, _mut28300 = false, _mut28301 = false, _mut28302 = false, _mut28303 = false, _mut28304 = false, _mut28305 = false, _mut28306 = false, _mut28307 = false, _mut28308 = false, _mut28309 = false, _mut28310 = false, _mut28311 = false, _mut28312 = false, _mut28313 = false, _mut28314 = false, _mut28315 = false, _mut28316 = false, _mut28317 = false, _mut28318 = false, _mut28319 = false, _mut28320 = false, _mut28321 = false, _mut28322 = false, _mut28323 = false, _mut28324 = false, _mut28325 = false, _mut28326 = false, _mut28327 = false, _mut28328 = false, _mut28329 = false, _mut28330 = false, _mut28331 = false, _mut28332 = false, _mut28333 = false, _mut28334 = false, _mut28335 = false, _mut28336 = false, _mut28337 = false, _mut28338 = false, _mut28339 = false, _mut28340 = false, _mut28341 = false, _mut28342 = false, _mut28343 = false, _mut28344 = false, _mut28345 = false, _mut28346 = false, _mut28347 = false, _mut28348 = false, _mut28349 = false, _mut28350 = false, _mut28351 = false, _mut28352 = false, _mut28353 = false, _mut28354 = false, _mut28355 = false, _mut28356 = false, _mut28357 = false, _mut28358 = false, _mut28359 = false, _mut28360 = false, _mut28361 = false, _mut28362 = false, _mut28363 = false, _mut28364 = false, _mut28365 = false, _mut28366 = false, _mut28367 = false, _mut28368 = false, _mut28369 = false, _mut28370 = false, _mut28371 = false, _mut28372 = false, _mut28373 = false, _mut28374 = false, _mut28375 = false, _mut28376 = false, _mut28377 = false, _mut28378 = false, _mut28379 = false, _mut28380 = false, _mut28381 = false, _mut28382 = false, _mut28383 = false, _mut28384 = false, _mut28385 = false, _mut28386 = false, _mut28387 = false, _mut28388 = false, _mut28389 = false, _mut28390 = false, _mut28391 = false, _mut28392 = false, _mut28393 = false, _mut28394 = false, _mut28395 = false, _mut28396 = false, _mut28397 = false, _mut28398 = false, _mut28399 = false, _mut28400 = false, _mut28401 = false, _mut28402 = false, _mut28403 = false, _mut28404 = false, _mut28405 = false, _mut28406 = false, _mut28407 = false, _mut28408 = false, _mut28409 = false, _mut28410 = false, _mut28411 = false, _mut28412 = false, _mut28413 = false, _mut28414 = false, _mut28415 = false, _mut28416 = false, _mut28417 = false, _mut28418 = false, _mut28419 = false, _mut28420 = false, _mut28421 = false, _mut28422 = false, _mut28423 = false, _mut28424 = false, _mut28425 = false, _mut28426 = false, _mut28427 = false, _mut28428 = false, _mut28429 = false, _mut28430 = false, _mut28431 = false, _mut28432 = false, _mut28433 = false, _mut28434 = false, _mut28435 = false, _mut28436 = false, _mut28437 = false, _mut28438 = false, _mut28439 = false, _mut28440 = false, _mut28441 = false, _mut28442 = false, _mut28443 = false, _mut28444 = false, _mut28445 = false, _mut28446 = false, _mut28447 = false, _mut28448 = false, _mut28449 = false, _mut28450 = false, _mut28451 = false, _mut28452 = false, _mut28453 = false, _mut28454 = false, _mut28455 = false, _mut28456 = false, _mut28457 = false, _mut28458 = false, _mut28459 = false, _mut28460 = false, _mut28461 = false, _mut28462 = false, _mut28463 = false, _mut28464 = false, _mut28465 = false, _mut28466 = false, _mut28467 = false, _mut28468 = false, _mut28469 = false, _mut28470 = false, _mut28471 = false, _mut28472 = false, _mut28473 = false, _mut28474 = false, _mut28475 = false, _mut28476 = false, _mut28477 = false, _mut28478 = false, _mut28479 = false, _mut28480 = false, _mut28481 = false, _mut28482 = false, _mut28483 = false, _mut28484 = false, _mut28485 = false, _mut28486 = false, _mut28487 = false, _mut28488 = false, _mut28489 = false, _mut28490 = false, _mut28491 = false, _mut28492 = false, _mut28493 = false, _mut28494 = false, _mut28495 = false, _mut28496 = false, _mut28497 = false, _mut28498 = false, _mut28499 = false, _mut28500 = false, _mut28501 = false, _mut28502 = false, _mut28503 = false, _mut28504 = false, _mut28505 = false, _mut28506 = false, _mut28507 = false, _mut28508 = false, _mut28509 = false, _mut28510 = false, _mut28511 = false, _mut28512 = false, _mut28513 = false, _mut28514 = false, _mut28515 = false, _mut28516 = false, _mut28517 = false, _mut28518 = false, _mut28519 = false, _mut28520 = false, _mut28521 = false, _mut28522 = false, _mut28523 = false, _mut28524 = false, _mut28525 = false, _mut28526 = false, _mut28527 = false, _mut28528 = false, _mut28529 = false, _mut28530 = false, _mut28531 = false, _mut28532 = false, _mut28533 = false, _mut28534 = false, _mut28535 = false, _mut28536 = false, _mut28537 = false, _mut28538 = false, _mut28539 = false, _mut28540 = false, _mut28541 = false, _mut28542 = false, _mut28543 = false, _mut28544 = false, _mut28545 = false, _mut28546 = false, _mut28547 = false, _mut28548 = false, _mut28549 = false, _mut28550 = false, _mut28551 = false, _mut28552 = false, _mut28553 = false, _mut28554 = false, _mut28555 = false, _mut28556 = false, _mut28557 = false, _mut28558 = false, _mut28559 = false, _mut28560 = false, _mut28561 = false, _mut28562 = false, _mut28563 = false, _mut28564 = false, _mut28565 = false, _mut28566 = false, _mut28567 = false, _mut28568 = false, _mut28569 = false, _mut28570 = false, _mut28571 = false, _mut28572 = false, _mut28573 = false, _mut28574 = false, _mut28575 = false, _mut28576 = false, _mut28577 = false, _mut28578 = false, _mut28579 = false, _mut28580 = false, _mut28581 = false, _mut28582 = false, _mut28583 = false, _mut28584 = false, _mut28585 = false, _mut28586 = false, _mut28587 = false, _mut28588 = false, _mut28589 = false, _mut28590 = false, _mut28591 = false, _mut28592 = false, _mut28593 = false, _mut28594 = false, _mut28595 = false, _mut28596 = false, _mut28597 = false, _mut28598 = false, _mut28599 = false, _mut28600 = false, _mut28601 = false, _mut28602 = false, _mut28603 = false, _mut28604 = false, _mut28605 = false, _mut28606 = false, _mut28607 = false, _mut28608 = false, _mut28609 = false, _mut28610 = false, _mut28611 = false, _mut28612 = false, _mut28613 = false, _mut28614 = false, _mut28615 = false, _mut28616 = false, _mut28617 = false, _mut28618 = false, _mut28619 = false, _mut28620 = false, _mut28621 = false, _mut28622 = false, _mut28623 = false, _mut28624 = false, _mut28625 = false, _mut28626 = false, _mut28627 = false, _mut28628 = false, _mut28629 = false, _mut28630 = false, _mut28631 = false, _mut28632 = false, _mut28633 = false, _mut28634 = false, _mut28635 = false, _mut28636 = false, _mut28637 = false, _mut28638 = false, _mut28639 = false, _mut28640 = false, _mut28641 = false, _mut28642 = false, _mut28643 = false, _mut28644 = false, _mut28645 = false, _mut28646 = false, _mut28647 = false, _mut28648 = false, _mut28649 = false, _mut28650 = false, _mut28651 = false, _mut28652 = false, _mut28653 = false, _mut28654 = false, _mut28655 = false, _mut28656 = false, _mut28657 = false, _mut28658 = false, _mut28659 = false, _mut28660 = false, _mut28661 = false, _mut28662 = false, _mut28663 = false, _mut28664 = false, _mut28665 = false, _mut28666 = false, _mut28667 = false, _mut28668 = false, _mut28669 = false, _mut28670 = false, _mut28671 = false, _mut28672 = false, _mut28673 = false, _mut28674 = false, _mut28675 = false, _mut28676 = false, _mut28677 = false, _mut28678 = false, _mut28679 = false, _mut28680 = false, _mut28681 = false, _mut28682 = false, _mut28683 = false, _mut28684 = false, _mut28685 = false, _mut28686 = false, _mut28687 = false, _mut28688 = false, _mut28689 = false, _mut28690 = false, _mut28691 = false, _mut28692 = false, _mut28693 = false, _mut28694 = false, _mut28695 = false, _mut28696 = false, _mut28697 = false, _mut28698 = false, _mut28699 = false, _mut28700 = false, _mut28701 = false, _mut28702 = false, _mut28703 = false, _mut28704 = false, _mut28705 = false, _mut28706 = false, _mut28707 = false, _mut28708 = false, _mut28709 = false, _mut28710 = false, _mut28711 = false, _mut28712 = false, _mut28713 = false, _mut28714 = false, _mut28715 = false, _mut28716 = false, _mut28717 = false, _mut28718 = false, _mut28719 = false, _mut28720 = false, _mut28721 = false, _mut28722 = false, _mut28723 = false, _mut28724 = false, _mut28725 = false, _mut28726 = false, _mut28727 = false, _mut28728 = false, _mut28729 = false, _mut28730 = false, _mut28731 = false, _mut28732 = false, _mut28733 = false, _mut28734 = false, _mut28735 = false, _mut28736 = false, _mut28737 = false, _mut28738 = false, _mut28739 = false, _mut28740 = false, _mut28741 = false, _mut28742 = false, _mut28743 = false, _mut28744 = false, _mut28745 = false, _mut28746 = false, _mut28747 = false, _mut28748 = false, _mut28749 = false, _mut28750 = false, _mut28751 = false, _mut28752 = false, _mut28753 = false, _mut28754 = false, _mut28755 = false, _mut28756 = false, _mut28757 = false, _mut28758 = false, _mut28759 = false, _mut28760 = false, _mut28761 = false, _mut28762 = false, _mut28763 = false, _mut28764 = false, _mut28765 = false, _mut28766 = false, _mut28767 = false, _mut28768 = false, _mut28769 = false, _mut28770 = false, _mut28771 = false, _mut28772 = false, _mut28773 = false, _mut28774 = false, _mut28775 = false, _mut28776 = false, _mut28777 = false, _mut28778 = false, _mut28779 = false, _mut28780 = false, _mut28781 = false, _mut28782 = false, _mut28783 = false, _mut28784 = false, _mut28785 = false, _mut28786 = false, _mut28787 = false, _mut28788 = false, _mut28789 = false, _mut28790 = false, _mut28791 = false, _mut28792 = false, _mut28793 = false, _mut28794 = false, _mut28795 = false, _mut28796 = false, _mut28797 = false, _mut28798 = false, _mut28799 = false, _mut28800 = false, _mut28801 = false, _mut28802 = false, _mut28803 = false, _mut28804 = false, _mut28805 = false, _mut28806 = false, _mut28807 = false, _mut28808 = false, _mut28809 = false, _mut28810 = false, _mut28811 = false, _mut28812 = false, _mut28813 = false, _mut28814 = false, _mut28815 = false, _mut28816 = false, _mut28817 = false, _mut28818 = false, _mut28819 = false, _mut28820 = false, _mut28821 = false, _mut28822 = false, _mut28823 = false, _mut28824 = false, _mut28825 = false, _mut28826 = false, _mut28827 = false, _mut28828 = false, _mut28829 = false, _mut28830 = false, _mut28831 = false, _mut28832 = false, _mut28833 = false, _mut28834 = false, _mut28835 = false, _mut28836 = false, _mut28837 = false, _mut28838 = false, _mut28839 = false, _mut28840 = false, _mut28841 = false, _mut28842 = false, _mut28843 = false, _mut28844 = false, _mut28845 = false, _mut28846 = false, _mut28847 = false, _mut28848 = false, _mut28849 = false, _mut28850 = false, _mut28851 = false, _mut28852 = false, _mut28853 = false, _mut28854 = false, _mut28855 = false, _mut28856 = false, _mut28857 = false, _mut28858 = false, _mut28859 = false, _mut28860 = false, _mut28861 = false, _mut28862 = false, _mut28863 = false, _mut28864 = false, _mut28865 = false, _mut28866 = false, _mut28867 = false, _mut28868 = false, _mut28869 = false, _mut28870 = false, _mut28871 = false, _mut28872 = false, _mut28873 = false, _mut28874 = false, _mut28875 = false, _mut28876 = false, _mut28877 = false, _mut28878 = false, _mut28879 = false, _mut28880 = false, _mut28881 = false, _mut28882 = false, _mut28883 = false, _mut28884 = false, _mut28885 = false, _mut28886 = false, _mut28887 = false;

    /**
     * Block size.
     */
    public static final int BLOCK_SIZE = 36;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -4602336630143123183L;

    /**
     * Blocks of matrix entries.
     */
    private final T[][] blocks;

    /**
     * Number of rows of the matrix.
     */
    private final int rows;

    /**
     * Number of columns of the matrix.
     */
    private final int columns;

    /**
     * Number of block rows of the matrix.
     */
    private final int blockRows;

    /**
     * Number of block columns of the matrix.
     */
    private final int blockColumns;

    /**
     * Create a new matrix with the supplied row and column dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     */
    public BlockFieldMatrix(final Field<T> field, final int rows, final int columns) throws NotStrictlyPositiveException {
        super(field, rows, columns);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99");
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26577, _mut26578, _mut26579, _mut26580), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26581, _mut26582, _mut26583, _mut26584)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26585, _mut26586, _mut26587, _mut26588);
        blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26589, _mut26590, _mut26591, _mut26592), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26593, _mut26594, _mut26595, _mut26596)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_99", _mut26597, _mut26598, _mut26599, _mut26600);
        // allocate storage blocks, taking care of smaller ones at right and bottom
        blocks = createBlocksLayout(field, rows, columns);
    }

    /**
     * Create a new dense matrix copying entries from raw layout data.
     * <p>The input array <em>must</em> already be in raw layout.</p>
     * <p>Calling this constructor is equivalent to call:
     * <pre>matrix = new BlockFieldMatrix<T>(getField(), rawData.length, rawData[0].length,
     *                                   toBlocksLayout(rawData), false);</pre>
     * </p>
     *
     * @param rawData Data for the new matrix, in raw layout.
     * @throws DimensionMismatchException if the {@code blockData} shape is
     * inconsistent with block layout.
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public BlockFieldMatrix(final T[][] rawData) throws DimensionMismatchException {
        this(rawData.length, rawData[0].length, toBlocksLayout(rawData), false);
    }

    /**
     * Create a new dense matrix copying entries from block layout data.
     * <p>The input array <em>must</em> already be in blocks layout.</p>
     * @param rows  the number of rows in the new matrix
     * @param columns  the number of columns in the new matrix
     * @param blockData data for new matrix
     * @param copyArray if true, the input array will be copied, otherwise
     * it will be referenced
     *
     * @throws DimensionMismatchException if the {@code blockData} shape is
     * inconsistent with block layout.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     * @see #createBlocksLayout(Field, int, int)
     * @see #toBlocksLayout(FieldElement[][])
     * @see #BlockFieldMatrix(FieldElement[][])
     */
    public BlockFieldMatrix(final int rows, final int columns, final T[][] blockData, final boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(extractField(blockData), rows, columns);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149");
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26601, _mut26602, _mut26603, _mut26604), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26605, _mut26606, _mut26607, _mut26608)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26609, _mut26610, _mut26611, _mut26612);
        blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26613, _mut26614, _mut26615, _mut26616), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26617, _mut26618, _mut26619, _mut26620)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26621, _mut26622, _mut26623, _mut26624);
        if (copyArray) {
            // allocate storage blocks, taking care of smaller ones at right and bottom
            blocks = MathArrays.buildArray(getField(), AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26625, _mut26626, _mut26627, _mut26628), -1);
        } else {
            // reference existing array
            blocks = blockData;
        }
        int index = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26647, _mut26648, _mut26649, _mut26650, _mut26651); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149");
            final int iHeight = blockHeight(iBlock);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26642, _mut26643, _mut26644, _mut26645, _mut26646); ++jBlock, ++index) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149");
                if (ROR_not_equals(blockData[index].length, AOR_multiply(iHeight, blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26629, _mut26630, _mut26631, _mut26632), "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26633, _mut26634, _mut26635, _mut26636, _mut26637)) {
                    throw new DimensionMismatchException(blockData[index].length, AOR_multiply(iHeight, blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.BlockFieldMatrix_149", _mut26638, _mut26639, _mut26640, _mut26641));
                }
                if (copyArray) {
                    blocks[index] = blockData[index].clone();
                }
            }
        }
    }

    /**
     * Convert a data array from raw layout to blocks layout.
     * <p>
     * Raw layout is the straightforward layout where element at row i and
     * column j is in array element <code>rawData[i][j]</code>. Blocks layout
     * is the layout used in {@link BlockFieldMatrix} instances, where the matrix
     * is split in square blocks (except at right and bottom side where blocks may
     * be rectangular to fit matrix size) and each block is stored in a flattened
     * one-dimensional array.
     * </p>
     * <p>
     * This method creates an array in blocks layout from an input array in raw layout.
     * It can be used to provide the array argument of the {@link
     * #BlockFieldMatrix(int, int, FieldElement[][], boolean)}
     * constructor.
     * </p>
     * @param <T> Type of the field elements.
     * @param rawData Data array in raw layout.
     * @return a new data array containing the same entries but in blocks layout
     * @throws DimensionMismatchException if {@code rawData} is not rectangular
     *  (not all rows have the same length).
     * @see #createBlocksLayout(Field, int, int)
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public static <T extends FieldElement<T>> T[][] toBlocksLayout(final T[][] rawData) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207");
        final int rows = rawData.length;
        final int columns = rawData[0].length;
        final int blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26652, _mut26653, _mut26654, _mut26655), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26656, _mut26657, _mut26658, _mut26659)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26660, _mut26661, _mut26662, _mut26663);
        final int blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26664, _mut26665, _mut26666, _mut26667), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26668, _mut26669, _mut26670, _mut26671)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26672, _mut26673, _mut26674, _mut26675);
        // safety checks
        for (int i = 0; ROR_less(i, rawData.length, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26681, _mut26682, _mut26683, _mut26684, _mut26685); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207");
            final int length = rawData[i].length;
            if (ROR_not_equals(length, columns, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26676, _mut26677, _mut26678, _mut26679, _mut26680)) {
                throw new DimensionMismatchException(columns, length);
            }
        }
        // convert array
        final Field<T> field = extractField(rawData);
        final T[][] blocks = MathArrays.buildArray(field, AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26686, _mut26687, _mut26688, _mut26689), -1);
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26728, _mut26729, _mut26730, _mut26731, _mut26732); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26690, _mut26691, _mut26692, _mut26693);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26694, _mut26695, _mut26696, _mut26697), rows);
            final int iHeight = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26698, _mut26699, _mut26700, _mut26701);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26723, _mut26724, _mut26725, _mut26726, _mut26727); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26702, _mut26703, _mut26704, _mut26705);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26706, _mut26707, _mut26708, _mut26709), columns);
                final int jWidth = AOR_minus(qEnd, qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26710, _mut26711, _mut26712, _mut26713);
                // allocate new block
                final T[] block = MathArrays.buildArray(field, AOR_multiply(iHeight, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26714, _mut26715, _mut26716, _mut26717));
                blocks[blockIndex] = block;
                // copy data
                int index = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207", _mut26718, _mut26719, _mut26720, _mut26721, _mut26722); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.toBlocksLayout_207");
                    System.arraycopy(rawData[p], qStart, block, index, jWidth);
                    index += jWidth;
                }
                ++blockIndex;
            }
        }
        return blocks;
    }

    /**
     * Create a data array in blocks layout.
     * <p>
     * This method can be used to create the array argument of the {@link
     * #BlockFieldMatrix(int, int, FieldElement[][], boolean)}
     * constructor.
     * </p>
     * @param <T> Type of the field elements.
     * @param field Field to which the elements belong.
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @return a new data array in blocks layout.
     * @see #toBlocksLayout(FieldElement[][])
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public static <T extends FieldElement<T>> T[][] createBlocksLayout(final Field<T> field, final int rows, final int columns) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269");
        final int blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26733, _mut26734, _mut26735, _mut26736), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26737, _mut26738, _mut26739, _mut26740)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26741, _mut26742, _mut26743, _mut26744);
        final int blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26745, _mut26746, _mut26747, _mut26748), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26749, _mut26750, _mut26751, _mut26752)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26753, _mut26754, _mut26755, _mut26756);
        final T[][] blocks = MathArrays.buildArray(field, AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26757, _mut26758, _mut26759, _mut26760), -1);
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26794, _mut26795, _mut26796, _mut26797, _mut26798); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26761, _mut26762, _mut26763, _mut26764);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26765, _mut26766, _mut26767, _mut26768), rows);
            final int iHeight = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26769, _mut26770, _mut26771, _mut26772);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26789, _mut26790, _mut26791, _mut26792, _mut26793); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26773, _mut26774, _mut26775, _mut26776);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26777, _mut26778, _mut26779, _mut26780), columns);
                final int jWidth = AOR_minus(qEnd, qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26781, _mut26782, _mut26783, _mut26784);
                blocks[blockIndex] = MathArrays.buildArray(field, AOR_multiply(iHeight, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.createBlocksLayout_269", _mut26785, _mut26786, _mut26787, _mut26788));
                ++blockIndex;
            }
        }
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix<T>(getField(), rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> copy() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.copy_302");
        // create an empty matrix
        BlockFieldMatrix<T> copied = new BlockFieldMatrix<T>(getField(), rows, columns);
        // copy the blocks
        for (int i = 0; ROR_less(i, blocks.length, "org.apache.commons.math3.linear.BlockFieldMatrix.copy_302", _mut26799, _mut26800, _mut26801, _mut26802, _mut26803); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.copy_302");
            System.arraycopy(blocks[i], 0, copied.blocks[i], 0, blocks[i].length);
        }
        return copied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> add(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_317");
        try {
            return add((BlockFieldMatrix<T>) m);
        } catch (ClassCastException cce) {
            // safety check
            checkAdditionCompatible(m);
            final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
            // perform addition block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26835, _mut26836, _mut26837, _mut26838, _mut26839); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_317");
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26830, _mut26831, _mut26832, _mut26833, _mut26834); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_317");
                    // perform addition on the current block
                    final T[] outBlock = out.blocks[blockIndex];
                    final T[] tBlock = blocks[blockIndex];
                    final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26804, _mut26805, _mut26806, _mut26807);
                    final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26808, _mut26809, _mut26810, _mut26811), rows);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26812, _mut26813, _mut26814, _mut26815);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26816, _mut26817, _mut26818, _mut26819), columns);
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26825, _mut26826, _mut26827, _mut26828, _mut26829); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_317");
                        for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.add_317", _mut26820, _mut26821, _mut26822, _mut26823, _mut26824); ++q) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_317");
                            outBlock[k] = tBlock[k].add(m.getEntry(p, q));
                            ++k;
                        }
                    }
                    // go to next block
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    /**
     * Compute the sum of {@code this} and {@code m}.
     *
     * @param m matrix to be added
     * @return {@code this + m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}
     */
    public BlockFieldMatrix<T> add(final BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_367");
        // safety check
        checkAdditionCompatible(m);
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
        // perform addition block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockFieldMatrix.add_367", _mut26845, _mut26846, _mut26847, _mut26848, _mut26849); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_367");
            final T[] outBlock = out.blocks[blockIndex];
            final T[] tBlock = blocks[blockIndex];
            final T[] mBlock = m.blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.add_367", _mut26840, _mut26841, _mut26842, _mut26843, _mut26844); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.add_367");
                outBlock[k] = tBlock[k].add(mBlock[k]);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> subtract(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389");
        try {
            return subtract((BlockFieldMatrix<T>) m);
        } catch (ClassCastException cce) {
            // safety check
            checkSubtractionCompatible(m);
            final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
            // perform subtraction block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26881, _mut26882, _mut26883, _mut26884, _mut26885); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389");
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26876, _mut26877, _mut26878, _mut26879, _mut26880); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389");
                    // perform subtraction on the current block
                    final T[] outBlock = out.blocks[blockIndex];
                    final T[] tBlock = blocks[blockIndex];
                    final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26850, _mut26851, _mut26852, _mut26853);
                    final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26854, _mut26855, _mut26856, _mut26857), rows);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26858, _mut26859, _mut26860, _mut26861);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26862, _mut26863, _mut26864, _mut26865), columns);
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26871, _mut26872, _mut26873, _mut26874, _mut26875); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389");
                        for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389", _mut26866, _mut26867, _mut26868, _mut26869, _mut26870); ++q) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_389");
                            outBlock[k] = tBlock[k].subtract(m.getEntry(p, q));
                            ++k;
                        }
                    }
                    // go to next block
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    /**
     * Compute {@code this - m}.
     *
     * @param m matrix to be subtracted
     * @return {@code this - m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}
     */
    public BlockFieldMatrix<T> subtract(final BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_439");
        // safety check
        checkSubtractionCompatible(m);
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_439", _mut26891, _mut26892, _mut26893, _mut26894, _mut26895); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_439");
            final T[] outBlock = out.blocks[blockIndex];
            final T[] tBlock = blocks[blockIndex];
            final T[] mBlock = m.blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.subtract_439", _mut26886, _mut26887, _mut26888, _mut26889, _mut26890); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.subtract_439");
                outBlock[k] = tBlock[k].subtract(mBlock[k]);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> scalarAdd(final T d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarAdd_459");
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockFieldMatrix.scalarAdd_459", _mut26901, _mut26902, _mut26903, _mut26904, _mut26905); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarAdd_459");
            final T[] outBlock = out.blocks[blockIndex];
            final T[] tBlock = blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.scalarAdd_459", _mut26896, _mut26897, _mut26898, _mut26899, _mut26900); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarAdd_459");
                outBlock[k] = tBlock[k].add(d);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> scalarMultiply(final T d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarMultiply_476");
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockFieldMatrix.scalarMultiply_476", _mut26911, _mut26912, _mut26913, _mut26914, _mut26915); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarMultiply_476");
            final T[] outBlock = out.blocks[blockIndex];
            final T[] tBlock = blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.scalarMultiply_476", _mut26906, _mut26907, _mut26908, _mut26909, _mut26910); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.scalarMultiply_476");
                outBlock[k] = tBlock[k].multiply(d);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> multiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
        try {
            return multiply((BlockFieldMatrix<T>) m);
        } catch (ClassCastException cce) {
            // safety check
            checkMultiplicationCompatible(m);
            final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, m.getColumnDimension());
            final T zero = getField().getZero();
            // perform multiplication block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26981, _mut26982, _mut26983, _mut26984, _mut26985); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26916, _mut26917, _mut26918, _mut26919);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26920, _mut26921, _mut26922, _mut26923), rows);
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26976, _mut26977, _mut26978, _mut26979, _mut26980); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26924, _mut26925, _mut26926, _mut26927);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26928, _mut26929, _mut26930, _mut26931), m.getColumnDimension());
                    // select current block
                    final T[] outBlock = out.blocks[blockIndex];
                    // perform multiplication on current block
                    for (int kBlock = 0; ROR_less(kBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26971, _mut26972, _mut26973, _mut26974, _mut26975); ++kBlock) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                        final int kWidth = blockWidth(kBlock);
                        final T[] tBlock = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26932, _mut26933, _mut26934, _mut26935), kBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26936, _mut26937, _mut26938, _mut26939)];
                        final int rStart = AOR_multiply(kBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26940, _mut26941, _mut26942, _mut26943);
                        int k = 0;
                        for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26966, _mut26967, _mut26968, _mut26969, _mut26970); ++p) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                            final int lStart = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26944, _mut26945, _mut26946, _mut26947)), kWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26948, _mut26949, _mut26950, _mut26951);
                            final int lEnd = AOR_plus(lStart, kWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26952, _mut26953, _mut26954, _mut26955);
                            for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26961, _mut26962, _mut26963, _mut26964, _mut26965); ++q) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                                T sum = zero;
                                int r = rStart;
                                for (int l = lStart; ROR_less(l, lEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494", _mut26956, _mut26957, _mut26958, _mut26959, _mut26960); ++l) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_494");
                                    sum = sum.add(tBlock[l].multiply(m.getEntry(r, q)));
                                    ++r;
                                }
                                outBlock[k] = outBlock[k].add(sum);
                                ++k;
                            }
                        }
                    }
                    // go to next block
                    ++blockIndex;
                }
            }
            return out;
        }
    }

    /**
     * Returns the result of postmultiplying {@code this} by {@code m}.
     *
     * @param m matrix to postmultiply by
     * @return {@code this * m}
     * @throws DimensionMismatchException if the matrices are not compatible.
     */
    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
        // safety check
        checkMultiplicationCompatible(m);
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, m.columns);
        final T zero = getField().getZero();
        // perform multiplication block-wise, to ensure good cache behavior
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27092, _mut27093, _mut27094, _mut27095, _mut27096); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut26986, _mut26987, _mut26988, _mut26989);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut26990, _mut26991, _mut26992, _mut26993), rows);
            for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27087, _mut27088, _mut27089, _mut27090, _mut27091); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                final int jWidth = out.blockWidth(jBlock);
                final int jWidth2 = AOR_plus(jWidth, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut26994, _mut26995, _mut26996, _mut26997);
                final int jWidth3 = AOR_plus(jWidth2, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut26998, _mut26999, _mut27000, _mut27001);
                final int jWidth4 = AOR_plus(jWidth3, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27002, _mut27003, _mut27004, _mut27005);
                // select current block
                final T[] outBlock = out.blocks[blockIndex];
                // perform multiplication on current block
                for (int kBlock = 0; ROR_less(kBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27082, _mut27083, _mut27084, _mut27085, _mut27086); ++kBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                    final int kWidth = blockWidth(kBlock);
                    final T[] tBlock = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27006, _mut27007, _mut27008, _mut27009), kBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27010, _mut27011, _mut27012, _mut27013)];
                    final T[] mBlock = m.blocks[AOR_plus(AOR_multiply(kBlock, m.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27014, _mut27015, _mut27016, _mut27017), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27018, _mut27019, _mut27020, _mut27021)];
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27077, _mut27078, _mut27079, _mut27080, _mut27081); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                        final int lStart = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27022, _mut27023, _mut27024, _mut27025)), kWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27026, _mut27027, _mut27028, _mut27029);
                        final int lEnd = AOR_plus(lStart, kWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27030, _mut27031, _mut27032, _mut27033);
                        for (int nStart = 0; ROR_less(nStart, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27072, _mut27073, _mut27074, _mut27075, _mut27076); ++nStart) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                            T sum = zero;
                            int l = lStart;
                            int n = nStart;
                            while (ROR_less(l, AOR_minus(lEnd, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27058, _mut27059, _mut27060, _mut27061), "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27062, _mut27063, _mut27064, _mut27065, _mut27066)) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                                sum = sum.add(tBlock[l].multiply(mBlock[n])).add(tBlock[AOR_plus(l, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27054, _mut27055, _mut27056, _mut27057)].multiply(mBlock[AOR_plus(n, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27050, _mut27051, _mut27052, _mut27053)])).add(tBlock[AOR_plus(l, 2, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27046, _mut27047, _mut27048, _mut27049)].multiply(mBlock[AOR_plus(n, jWidth2, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27042, _mut27043, _mut27044, _mut27045)])).add(tBlock[AOR_plus(l, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27038, _mut27039, _mut27040, _mut27041)].multiply(mBlock[AOR_plus(n, jWidth3, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27034, _mut27035, _mut27036, _mut27037)]));
                                l += 4;
                                n += jWidth4;
                            }
                            while (ROR_less(l, lEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561", _mut27067, _mut27068, _mut27069, _mut27070, _mut27071)) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiply_561");
                                sum = sum.add(tBlock[l++].multiply(mBlock[n]));
                                n += jWidth;
                            }
                            outBlock[k] = outBlock[k].add(sum);
                            ++k;
                        }
                    }
                }
                // go to next block
                ++blockIndex;
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[][] getData() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getData_627");
        final T[][] data = MathArrays.buildArray(getField(), getRowDimension(), getColumnDimension());
        final int lastColumns = AOR_minus(columns, AOR_multiply((AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27097, _mut27098, _mut27099, _mut27100)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27101, _mut27102, _mut27103, _mut27104), "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27105, _mut27106, _mut27107, _mut27108);
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27135, _mut27136, _mut27137, _mut27138, _mut27139); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getData_627");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27109, _mut27110, _mut27111, _mut27112);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27113, _mut27114, _mut27115, _mut27116), rows);
            int regularPos = 0;
            int lastPos = 0;
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27130, _mut27131, _mut27132, _mut27133, _mut27134); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getData_627");
                final T[] dataP = data[p];
                int blockIndex = AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27117, _mut27118, _mut27119, _mut27120);
                int dataPos = 0;
                for (int jBlock = 0; ROR_less(jBlock, AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27121, _mut27122, _mut27123, _mut27124), "org.apache.commons.math3.linear.BlockFieldMatrix.getData_627", _mut27125, _mut27126, _mut27127, _mut27128, _mut27129); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getData_627");
                    System.arraycopy(blocks[blockIndex++], regularPos, dataP, dataPos, BLOCK_SIZE);
                    dataPos += BLOCK_SIZE;
                }
                System.arraycopy(blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
                regularPos += BLOCK_SIZE;
                lastPos += lastColumns;
            }
        }
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656");
        // safety checks
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        // create the output matrix
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), AOR_plus(AOR_minus(endRow, startRow, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27140, _mut27141, _mut27142, _mut27143), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27144, _mut27145, _mut27146, _mut27147), AOR_plus(AOR_minus(endColumn, startColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27148, _mut27149, _mut27150, _mut27151), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27152, _mut27153, _mut27154, _mut27155));
        // compute blocks shifts
        final int blockStartRow = AOR_divide(startRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27156, _mut27157, _mut27158, _mut27159);
        final int rowsShift = AOR_remainder(startRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27160, _mut27161, _mut27162, _mut27163);
        final int blockStartColumn = AOR_divide(startColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27164, _mut27165, _mut27166, _mut27167);
        final int columnsShift = AOR_remainder(startColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27168, _mut27169, _mut27170, _mut27171);
        // perform extraction block-wise, to ensure good cache behavior
        int pBlock = blockStartRow;
        for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27304, _mut27305, _mut27306, _mut27307, _mut27308); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656");
            final int iHeight = out.blockHeight(iBlock);
            int qBlock = blockStartColumn;
            for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27299, _mut27300, _mut27301, _mut27302, _mut27303); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656");
                final int jWidth = out.blockWidth(jBlock);
                // handle one block of the output matrix
                final int outIndex = AOR_plus(AOR_multiply(iBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27172, _mut27173, _mut27174, _mut27175), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27176, _mut27177, _mut27178, _mut27179);
                final T[] outBlock = out.blocks[outIndex];
                final int index = AOR_plus(AOR_multiply(pBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27180, _mut27181, _mut27182, _mut27183), qBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27184, _mut27185, _mut27186, _mut27187);
                final int width = blockWidth(qBlock);
                final int heightExcess = AOR_minus(AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27188, _mut27189, _mut27190, _mut27191), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27192, _mut27193, _mut27194, _mut27195);
                final int widthExcess = AOR_minus(AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27196, _mut27197, _mut27198, _mut27199), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27200, _mut27201, _mut27202, _mut27203);
                if (ROR_greater(heightExcess, 0, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27204, _mut27205, _mut27206, _mut27207, _mut27208)) {
                    // the submatrix block spans on two blocks rows from the original matrix
                    if (ROR_greater(widthExcess, 0, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27242, _mut27243, _mut27244, _mut27245, _mut27246)) {
                        // the submatrix block spans on two blocks columns from the original matrix
                        final int width2 = blockWidth(AOR_plus(qBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27263, _mut27264, _mut27265, _mut27266));
                        copyBlockPart(blocks[index], width, rowsShift, BLOCK_SIZE, columnsShift, BLOCK_SIZE, outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27267, _mut27268, _mut27269, _mut27270)], width2, rowsShift, BLOCK_SIZE, 0, widthExcess, outBlock, jWidth, 0, AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27271, _mut27272, _mut27273, _mut27274));
                        copyBlockPart(blocks[AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27275, _mut27276, _mut27277, _mut27278)], width, 0, heightExcess, columnsShift, BLOCK_SIZE, outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27279, _mut27280, _mut27281, _mut27282), 0);
                        copyBlockPart(blocks[AOR_plus(AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27283, _mut27284, _mut27285, _mut27286), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27287, _mut27288, _mut27289, _mut27290)], width2, 0, heightExcess, 0, widthExcess, outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27291, _mut27292, _mut27293, _mut27294), AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27295, _mut27296, _mut27297, _mut27298));
                    } else {
                        // the submatrix block spans on one block column from the original matrix
                        copyBlockPart(blocks[index], width, rowsShift, BLOCK_SIZE, columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27247, _mut27248, _mut27249, _mut27250), outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27251, _mut27252, _mut27253, _mut27254)], width, 0, heightExcess, columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27255, _mut27256, _mut27257, _mut27258), outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27259, _mut27260, _mut27261, _mut27262), 0);
                    }
                } else {
                    // the submatrix block spans on one block row from the original matrix
                    if (ROR_greater(widthExcess, 0, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27209, _mut27210, _mut27211, _mut27212, _mut27213)) {
                        // the submatrix block spans on two blocks columns from the original matrix
                        final int width2 = blockWidth(AOR_plus(qBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27222, _mut27223, _mut27224, _mut27225));
                        copyBlockPart(blocks[index], width, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27226, _mut27227, _mut27228, _mut27229), columnsShift, BLOCK_SIZE, outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27230, _mut27231, _mut27232, _mut27233)], width2, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27234, _mut27235, _mut27236, _mut27237), 0, widthExcess, outBlock, jWidth, 0, AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27238, _mut27239, _mut27240, _mut27241));
                    } else {
                        // the submatrix block spans on one block column from the original matrix
                        copyBlockPart(blocks[index], width, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27214, _mut27215, _mut27216, _mut27217), columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix_656", _mut27218, _mut27219, _mut27220, _mut27221), outBlock, jWidth, 0, 0);
                    }
                }
                ++qBlock;
            }
            ++pBlock;
        }
        return out;
    }

    /**
     * Copy a part of a block into another one
     * <p>This method can be called only when the specified part fits in both
     * blocks, no verification is done here.</p>
     * @param srcBlock source block
     * @param srcWidth source block width ({@link #BLOCK_SIZE} or smaller)
     * @param srcStartRow start row in the source block
     * @param srcEndRow end row (exclusive) in the source block
     * @param srcStartColumn start column in the source block
     * @param srcEndColumn end column (exclusive) in the source block
     * @param dstBlock destination block
     * @param dstWidth destination block width ({@link #BLOCK_SIZE} or smaller)
     * @param dstStartRow start row in the destination block
     * @param dstStartColumn start column in the destination block
     */
    private void copyBlockPart(final T[] srcBlock, final int srcWidth, final int srcStartRow, final int srcEndRow, final int srcStartColumn, final int srcEndColumn, final T[] dstBlock, final int dstWidth, final int dstStartRow, final int dstStartColumn) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766");
        final int length = AOR_minus(srcEndColumn, srcStartColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27309, _mut27310, _mut27311, _mut27312);
        int srcPos = AOR_plus(AOR_multiply(srcStartRow, srcWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27313, _mut27314, _mut27315, _mut27316), srcStartColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27317, _mut27318, _mut27319, _mut27320);
        int dstPos = AOR_plus(AOR_multiply(dstStartRow, dstWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27321, _mut27322, _mut27323, _mut27324), dstStartColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27325, _mut27326, _mut27327, _mut27328);
        for (int srcRow = srcStartRow; ROR_less(srcRow, srcEndRow, "org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766", _mut27329, _mut27330, _mut27331, _mut27332, _mut27333); ++srcRow) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.copyBlockPart_766");
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782");
        // safety checks
        MathUtils.checkNotNull(subMatrix);
        final int refLength = subMatrix[0].length;
        if (ROR_equals(refLength, 0, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27334, _mut27335, _mut27336, _mut27337, _mut27338)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        final int endRow = AOR_minus(AOR_plus(row, subMatrix.length, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27339, _mut27340, _mut27341, _mut27342), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27343, _mut27344, _mut27345, _mut27346);
        final int endColumn = AOR_minus(AOR_plus(column, refLength, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27347, _mut27348, _mut27349, _mut27350), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27351, _mut27352, _mut27353, _mut27354);
        checkSubMatrixIndex(row, endRow, column, endColumn);
        for (final T[] subRow : subMatrix) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782");
            if (ROR_not_equals(subRow.length, refLength, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27355, _mut27356, _mut27357, _mut27358, _mut27359)) {
                throw new DimensionMismatchException(refLength, subRow.length);
            }
        }
        // compute blocks bounds
        final int blockStartRow = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27360, _mut27361, _mut27362, _mut27363);
        final int blockEndRow = AOR_divide((AOR_plus(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27364, _mut27365, _mut27366, _mut27367)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27368, _mut27369, _mut27370, _mut27371);
        final int blockStartColumn = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27372, _mut27373, _mut27374, _mut27375);
        final int blockEndColumn = AOR_divide((AOR_plus(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27376, _mut27377, _mut27378, _mut27379)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27380, _mut27381, _mut27382, _mut27383);
        // perform copy block-wise, to ensure good cache behavior
        for (int iBlock = blockStartRow; ROR_less(iBlock, blockEndRow, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27454, _mut27455, _mut27456, _mut27457, _mut27458); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782");
            final int iHeight = blockHeight(iBlock);
            final int firstRow = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27384, _mut27385, _mut27386, _mut27387);
            final int iStart = FastMath.max(row, firstRow);
            final int iEnd = FastMath.min(AOR_plus(endRow, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27388, _mut27389, _mut27390, _mut27391), AOR_plus(firstRow, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27392, _mut27393, _mut27394, _mut27395));
            for (int jBlock = blockStartColumn; ROR_less(jBlock, blockEndColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27449, _mut27450, _mut27451, _mut27452, _mut27453); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782");
                final int jWidth = blockWidth(jBlock);
                final int firstColumn = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27396, _mut27397, _mut27398, _mut27399);
                final int jStart = FastMath.max(column, firstColumn);
                final int jEnd = FastMath.min(AOR_plus(endColumn, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27400, _mut27401, _mut27402, _mut27403), AOR_plus(firstColumn, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27404, _mut27405, _mut27406, _mut27407));
                final int jLength = AOR_minus(jEnd, jStart, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27408, _mut27409, _mut27410, _mut27411);
                // handle one block, row by row
                final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27412, _mut27413, _mut27414, _mut27415), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27416, _mut27417, _mut27418, _mut27419)];
                for (int i = iStart; ROR_less(i, iEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27444, _mut27445, _mut27446, _mut27447, _mut27448); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782");
                    System.arraycopy(subMatrix[AOR_minus(i, row, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27420, _mut27421, _mut27422, _mut27423)], AOR_minus(jStart, column, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27424, _mut27425, _mut27426, _mut27427), block, AOR_plus(AOR_multiply((AOR_minus(i, firstRow, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27428, _mut27429, _mut27430, _mut27431)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27432, _mut27433, _mut27434, _mut27435), (AOR_minus(jStart, firstColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27436, _mut27437, _mut27438, _mut27439)), "org.apache.commons.math3.linear.BlockFieldMatrix.setSubMatrix_782", _mut27440, _mut27441, _mut27442, _mut27443), jLength);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getRowMatrix(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835");
        checkRowIndex(row);
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), 1, columns);
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27459, _mut27460, _mut27461, _mut27462);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27463, _mut27464, _mut27465, _mut27466), "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27467, _mut27468, _mut27469, _mut27470);
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[outBlockIndex];
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27508, _mut27509, _mut27510, _mut27511, _mut27512); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835");
            final int jWidth = blockWidth(jBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27471, _mut27472, _mut27473, _mut27474), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27475, _mut27476, _mut27477, _mut27478)];
            final int available = AOR_minus(outBlock.length, outIndex, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27479, _mut27480, _mut27481, _mut27482);
            if (ROR_greater(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27483, _mut27484, _mut27485, _mut27486, _mut27487)) {
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27492, _mut27493, _mut27494, _mut27495), outBlock, outIndex, available);
                outBlock = out.blocks[++outBlockIndex];
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27496, _mut27497, _mut27498, _mut27499), outBlock, 0, AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27500, _mut27501, _mut27502, _mut27503));
                outIndex = AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27504, _mut27505, _mut27506, _mut27507);
            } else {
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowMatrix_835", _mut27488, _mut27489, _mut27490, _mut27491), outBlock, outIndex, jWidth);
                outIndex += jWidth;
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowMatrix(final int row, final FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRowMatrix(row, (BlockFieldMatrix<T>) matrix);
        } catch (ClassCastException cce) {
            super.setRowMatrix(row, matrix);
        }
    }

    /**
     * Sets the entries in row number <code>row</code>
     * as a row matrix.  Row indices start at 0.
     *
     * @param row the row to be set
     * @param matrix row matrix (must have one row and the same number of columns
     * as the instance)
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance row.
     * @throws OutOfRangeException if the specified row index is invalid.
     */
    public void setRowMatrix(final int row, final BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        if ((_mut27523 ? ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27513, _mut27514, _mut27515, _mut27516, _mut27517)) && (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27518, _mut27519, _mut27520, _mut27521, _mut27522))) : ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27513, _mut27514, _mut27515, _mut27516, _mut27517)) || (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27518, _mut27519, _mut27520, _mut27521, _mut27522))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27524, _mut27525, _mut27526, _mut27527);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27528, _mut27529, _mut27530, _mut27531), "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27532, _mut27533, _mut27534, _mut27535);
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[mBlockIndex];
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27573, _mut27574, _mut27575, _mut27576, _mut27577); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887");
            final int jWidth = blockWidth(jBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27536, _mut27537, _mut27538, _mut27539), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27540, _mut27541, _mut27542, _mut27543)];
            final int available = AOR_minus(mBlock.length, mIndex, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27544, _mut27545, _mut27546, _mut27547);
            if (ROR_greater(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27548, _mut27549, _mut27550, _mut27551, _mut27552)) {
                System.arraycopy(mBlock, mIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27557, _mut27558, _mut27559, _mut27560), available);
                mBlock = matrix.blocks[++mBlockIndex];
                System.arraycopy(mBlock, 0, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27561, _mut27562, _mut27563, _mut27564), AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27565, _mut27566, _mut27567, _mut27568));
                mIndex = AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27569, _mut27570, _mut27571, _mut27572);
            } else {
                System.arraycopy(mBlock, mIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setRowMatrix_887", _mut27553, _mut27554, _mut27555, _mut27556), jWidth);
                mIndex += jWidth;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getColumnMatrix(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921");
        checkColumnIndex(column);
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), rows, 1);
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27578, _mut27579, _mut27580, _mut27581);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27582, _mut27583, _mut27584, _mut27585), "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27586, _mut27587, _mut27588, _mut27589);
        final int jWidth = blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[outBlockIndex];
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27616, _mut27617, _mut27618, _mut27619, _mut27620); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921");
            final int iHeight = blockHeight(iBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27590, _mut27591, _mut27592, _mut27593), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27594, _mut27595, _mut27596, _mut27597)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27611, _mut27612, _mut27613, _mut27614, _mut27615); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921");
                if (ROR_greater_equals(outIndex, outBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27598, _mut27599, _mut27600, _mut27601, _mut27602)) {
                    outBlock = out.blocks[++outBlockIndex];
                    outIndex = 0;
                }
                outBlock[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27603, _mut27604, _mut27605, _mut27606), jColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnMatrix_921", _mut27607, _mut27608, _mut27609, _mut27610)];
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnMatrix(final int column, final FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setColumnMatrix(column, (BlockFieldMatrix<T>) matrix);
        } catch (ClassCastException cce) {
            super.setColumnMatrix(column, matrix);
        }
    }

    /**
     * Sets the entries in column number {@code column}
     * as a column matrix.  Column indices start at 0.
     *
     * @param column Column to be set.
     * @param matrix Column matrix (must have one column and the same number of rows
     * as the instance).
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance column.
     * @throws OutOfRangeException if the specified column index is invalid.
     */
    void setColumnMatrix(final int column, final BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        if ((_mut27631 ? ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27621, _mut27622, _mut27623, _mut27624, _mut27625)) && (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27626, _mut27627, _mut27628, _mut27629, _mut27630))) : ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27621, _mut27622, _mut27623, _mut27624, _mut27625)) || (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27626, _mut27627, _mut27628, _mut27629, _mut27630))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27632, _mut27633, _mut27634, _mut27635);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27636, _mut27637, _mut27638, _mut27639), "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27640, _mut27641, _mut27642, _mut27643);
        final int jWidth = blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[mBlockIndex];
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27670, _mut27671, _mut27672, _mut27673, _mut27674); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971");
            final int iHeight = blockHeight(iBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27644, _mut27645, _mut27646, _mut27647), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27648, _mut27649, _mut27650, _mut27651)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27665, _mut27666, _mut27667, _mut27668, _mut27669); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971");
                if (ROR_greater_equals(mIndex, mBlock.length, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27652, _mut27653, _mut27654, _mut27655, _mut27656)) {
                    mBlock = matrix.blocks[++mBlockIndex];
                    mIndex = 0;
                }
                block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27657, _mut27658, _mut27659, _mut27660), jColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumnMatrix_971", _mut27661, _mut27662, _mut27663, _mut27664)] = mBlock[mIndex++];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldVector<T> getRowVector(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003");
        checkRowIndex(row);
        final T[] outData = MathArrays.buildArray(getField(), columns);
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27675, _mut27676, _mut27677, _mut27678);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27679, _mut27680, _mut27681, _mut27682), "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27683, _mut27684, _mut27685, _mut27686);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27699, _mut27700, _mut27701, _mut27702, _mut27703); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003");
            final int jWidth = blockWidth(jBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27687, _mut27688, _mut27689, _mut27690), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27691, _mut27692, _mut27693, _mut27694)];
            System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getRowVector_1003", _mut27695, _mut27696, _mut27697, _mut27698), outData, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayFieldVector<T>(getField(), outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowVector(final int row, final FieldVector<T> vector) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRow(row, ((ArrayFieldVector<T>) vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setRowVector(row, vector);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldVector<T> getColumnVector(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035");
        checkColumnIndex(column);
        final T[] outData = MathArrays.buildArray(getField(), rows);
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27704, _mut27705, _mut27706, _mut27707);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27708, _mut27709, _mut27710, _mut27711), "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27712, _mut27713, _mut27714, _mut27715);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27737, _mut27738, _mut27739, _mut27740, _mut27741); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035");
            final int iHeight = blockHeight(iBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27716, _mut27717, _mut27718, _mut27719), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27720, _mut27721, _mut27722, _mut27723)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27732, _mut27733, _mut27734, _mut27735, _mut27736); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035");
                outData[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27724, _mut27725, _mut27726, _mut27727), jColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumnVector_1035", _mut27728, _mut27729, _mut27730, _mut27731)];
            }
        }
        return new ArrayFieldVector<T>(getField(), outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnVector(final int column, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(column, ((ArrayFieldVector<T>) vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setColumnVector(column, vector);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] getRow(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069");
        checkRowIndex(row);
        final T[] out = MathArrays.buildArray(getField(), columns);
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27742, _mut27743, _mut27744, _mut27745);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27746, _mut27747, _mut27748, _mut27749), "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27750, _mut27751, _mut27752, _mut27753);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27766, _mut27767, _mut27768, _mut27769, _mut27770); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069");
            final int jWidth = blockWidth(jBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27754, _mut27755, _mut27756, _mut27757), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27758, _mut27759, _mut27760, _mut27761)];
            System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getRow_1069", _mut27762, _mut27763, _mut27764, _mut27765), out, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRow(final int row, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089");
        checkRowIndex(row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(array.length, nCols, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27771, _mut27772, _mut27773, _mut27774, _mut27775)) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27776, _mut27777, _mut27778, _mut27779);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27780, _mut27781, _mut27782, _mut27783), "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27784, _mut27785, _mut27786, _mut27787);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27800, _mut27801, _mut27802, _mut27803, _mut27804); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089");
            final int jWidth = blockWidth(jBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27788, _mut27789, _mut27790, _mut27791), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27792, _mut27793, _mut27794, _mut27795)];
            System.arraycopy(array, outIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setRow_1089", _mut27796, _mut27797, _mut27798, _mut27799), jWidth);
            outIndex += jWidth;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] getColumn(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111");
        checkColumnIndex(column);
        final T[] out = MathArrays.buildArray(getField(), rows);
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27805, _mut27806, _mut27807, _mut27808);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27809, _mut27810, _mut27811, _mut27812), "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27813, _mut27814, _mut27815, _mut27816);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27838, _mut27839, _mut27840, _mut27841, _mut27842); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111");
            final int iHeight = blockHeight(iBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27817, _mut27818, _mut27819, _mut27820), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27821, _mut27822, _mut27823, _mut27824)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27833, _mut27834, _mut27835, _mut27836, _mut27837); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111");
                out[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27825, _mut27826, _mut27827, _mut27828), jColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.getColumn_1111", _mut27829, _mut27830, _mut27831, _mut27832)];
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumn(final int column, final T[] array) throws MatrixDimensionMismatchException, OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133");
        checkColumnIndex(column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(array.length, nRows, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27843, _mut27844, _mut27845, _mut27846, _mut27847)) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27848, _mut27849, _mut27850, _mut27851);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27852, _mut27853, _mut27854, _mut27855), "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27856, _mut27857, _mut27858, _mut27859);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27881, _mut27882, _mut27883, _mut27884, _mut27885); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133");
            final int iHeight = blockHeight(iBlock);
            final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27860, _mut27861, _mut27862, _mut27863), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27864, _mut27865, _mut27866, _mut27867)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27876, _mut27877, _mut27878, _mut27879, _mut27880); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133");
                block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27868, _mut27869, _mut27870, _mut27871), jColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.setColumn_1133", _mut27872, _mut27873, _mut27874, _mut27875)] = array[outIndex++];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntry(final int row, final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157");
        checkRowIndex(row);
        checkColumnIndex(column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27886, _mut27887, _mut27888, _mut27889);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27890, _mut27891, _mut27892, _mut27893);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27894, _mut27895, _mut27896, _mut27897), "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27898, _mut27899, _mut27900, _mut27901)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27902, _mut27903, _mut27904, _mut27905), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27906, _mut27907, _mut27908, _mut27909), "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27910, _mut27911, _mut27912, _mut27913)), "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27914, _mut27915, _mut27916, _mut27917);
        return blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27918, _mut27919, _mut27920, _mut27921), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.getEntry_1157", _mut27922, _mut27923, _mut27924, _mut27925)][k];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(final int row, final int column, final T value) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172");
        checkRowIndex(row);
        checkColumnIndex(column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27926, _mut27927, _mut27928, _mut27929);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27930, _mut27931, _mut27932, _mut27933);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27934, _mut27935, _mut27936, _mut27937), "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27938, _mut27939, _mut27940, _mut27941)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27942, _mut27943, _mut27944, _mut27945), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27946, _mut27947, _mut27948, _mut27949), "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27950, _mut27951, _mut27952, _mut27953)), "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27954, _mut27955, _mut27956, _mut27957);
        blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27958, _mut27959, _mut27960, _mut27961), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.setEntry_1172", _mut27962, _mut27963, _mut27964, _mut27965)][k] = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(final int row, final int column, final T increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187");
        checkRowIndex(row);
        checkColumnIndex(column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27966, _mut27967, _mut27968, _mut27969);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27970, _mut27971, _mut27972, _mut27973);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27974, _mut27975, _mut27976, _mut27977), "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27978, _mut27979, _mut27980, _mut27981)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27982, _mut27983, _mut27984, _mut27985), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27986, _mut27987, _mut27988, _mut27989), "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27990, _mut27991, _mut27992, _mut27993)), "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27994, _mut27995, _mut27996, _mut27997);
        final T[] blockIJ = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut27998, _mut27999, _mut28000, _mut28001), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.addToEntry_1187", _mut28002, _mut28003, _mut28004, _mut28005)];
        blockIJ[k] = blockIJ[k].add(increment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final T factor) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203");
        checkRowIndex(row);
        checkColumnIndex(column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28006, _mut28007, _mut28008, _mut28009);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28010, _mut28011, _mut28012, _mut28013);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28014, _mut28015, _mut28016, _mut28017), "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28018, _mut28019, _mut28020, _mut28021)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28022, _mut28023, _mut28024, _mut28025), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28026, _mut28027, _mut28028, _mut28029), "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28030, _mut28031, _mut28032, _mut28033)), "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28034, _mut28035, _mut28036, _mut28037);
        final T[] blockIJ = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28038, _mut28039, _mut28040, _mut28041), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.multiplyEntry_1203", _mut28042, _mut28043, _mut28044, _mut28045)];
        blockIJ[k] = blockIJ[k].multiply(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> transpose() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), nCols, nRows);
        // perform transpose block-wise, to ensure good cache behavior
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28093, _mut28094, _mut28095, _mut28096, _mut28097); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219");
            for (int jBlock = 0; ROR_less(jBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28088, _mut28089, _mut28090, _mut28091, _mut28092); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219");
                // transpose current block
                final T[] outBlock = out.blocks[blockIndex];
                final T[] tBlock = blocks[AOR_plus(AOR_multiply(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28046, _mut28047, _mut28048, _mut28049), iBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28050, _mut28051, _mut28052, _mut28053)];
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28054, _mut28055, _mut28056, _mut28057);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28058, _mut28059, _mut28060, _mut28061), columns);
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28062, _mut28063, _mut28064, _mut28065);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28066, _mut28067, _mut28068, _mut28069), rows);
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28083, _mut28084, _mut28085, _mut28086, _mut28087); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219");
                    final int lInc = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28070, _mut28071, _mut28072, _mut28073);
                    int l = AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28074, _mut28075, _mut28076, _mut28077);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219", _mut28078, _mut28079, _mut28080, _mut28081, _mut28082); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.transpose_1219");
                        outBlock[k] = tBlock[l];
                        ++k;
                        l += lInc;
                    }
                }
                // go to next block
                ++blockIndex;
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        return rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        return columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] operate(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
        if (ROR_not_equals(v.length, columns, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28098, _mut28099, _mut28100, _mut28101, _mut28102)) {
            throw new DimensionMismatchException(v.length, columns);
        }
        final T[] out = MathArrays.buildArray(getField(), rows);
        final T zero = getField().getZero();
        // perform multiplication block-wise, to ensure good cache behavior
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28175, _mut28176, _mut28177, _mut28178, _mut28179); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28103, _mut28104, _mut28105, _mut28106);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28107, _mut28108, _mut28109, _mut28110), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28170, _mut28171, _mut28172, _mut28173, _mut28174); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
                final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28111, _mut28112, _mut28113, _mut28114), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28115, _mut28116, _mut28117, _mut28118)];
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28119, _mut28120, _mut28121, _mut28122);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28123, _mut28124, _mut28125, _mut28126), columns);
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28165, _mut28166, _mut28167, _mut28168, _mut28169); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
                    T sum = zero;
                    int q = qStart;
                    while (ROR_less(q, AOR_minus(qEnd, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28151, _mut28152, _mut28153, _mut28154), "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28155, _mut28156, _mut28157, _mut28158, _mut28159)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
                        sum = sum.add(block[k].multiply(v[q])).add(block[AOR_plus(k, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28147, _mut28148, _mut28149, _mut28150)].multiply(v[AOR_plus(q, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28143, _mut28144, _mut28145, _mut28146)])).add(block[AOR_plus(k, 2, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28139, _mut28140, _mut28141, _mut28142)].multiply(v[AOR_plus(q, 2, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28135, _mut28136, _mut28137, _mut28138)])).add(block[AOR_plus(k, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28131, _mut28132, _mut28133, _mut28134)].multiply(v[AOR_plus(q, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28127, _mut28128, _mut28129, _mut28130)]));
                        k += 4;
                        q += 4;
                    }
                    while (ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270", _mut28160, _mut28161, _mut28162, _mut28163, _mut28164)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.operate_1270");
                        sum = sum.add(block[k++].multiply(v[q++]));
                    }
                    out[p] = out[p].add(sum);
                }
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] preMultiply(final T[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
        if (ROR_not_equals(v.length, rows, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28180, _mut28181, _mut28182, _mut28183, _mut28184)) {
            throw new DimensionMismatchException(v.length, rows);
        }
        final T[] out = MathArrays.buildArray(getField(), columns);
        final T zero = getField().getZero();
        // perform multiplication block-wise, to ensure good cache behavior
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28273, _mut28274, _mut28275, _mut28276, _mut28277); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
            final int jWidth = blockWidth(jBlock);
            final int jWidth2 = AOR_plus(jWidth, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28185, _mut28186, _mut28187, _mut28188);
            final int jWidth3 = AOR_plus(jWidth2, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28189, _mut28190, _mut28191, _mut28192);
            final int jWidth4 = AOR_plus(jWidth3, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28193, _mut28194, _mut28195, _mut28196);
            final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28197, _mut28198, _mut28199, _mut28200);
            final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28201, _mut28202, _mut28203, _mut28204), columns);
            for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28268, _mut28269, _mut28270, _mut28271, _mut28272); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
                final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28205, _mut28206, _mut28207, _mut28208), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28209, _mut28210, _mut28211, _mut28212)];
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28213, _mut28214, _mut28215, _mut28216);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28217, _mut28218, _mut28219, _mut28220), rows);
                for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28263, _mut28264, _mut28265, _mut28266, _mut28267); ++q) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
                    int k = AOR_minus(q, qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28221, _mut28222, _mut28223, _mut28224);
                    T sum = zero;
                    int p = pStart;
                    while (ROR_less(p, AOR_minus(pEnd, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28249, _mut28250, _mut28251, _mut28252), "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28253, _mut28254, _mut28255, _mut28256, _mut28257)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
                        sum = sum.add(block[k].multiply(v[p])).add(block[AOR_plus(k, jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28245, _mut28246, _mut28247, _mut28248)].multiply(v[AOR_plus(p, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28241, _mut28242, _mut28243, _mut28244)])).add(block[AOR_plus(k, jWidth2, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28237, _mut28238, _mut28239, _mut28240)].multiply(v[AOR_plus(p, 2, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28233, _mut28234, _mut28235, _mut28236)])).add(block[AOR_plus(k, jWidth3, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28229, _mut28230, _mut28231, _mut28232)].multiply(v[AOR_plus(p, 3, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28225, _mut28226, _mut28227, _mut28228)]));
                        k += jWidth4;
                        p += 4;
                    }
                    while (ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311", _mut28258, _mut28259, _mut28260, _mut28261, _mut28262)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.preMultiply_1311");
                        sum = sum.add(block[k].multiply(v[p++]));
                        k += jWidth;
                    }
                    out[q] = out[q].add(sum);
                }
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28278, _mut28279, _mut28280, _mut28281), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28282, _mut28283, _mut28284, _mut28285));
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28333, _mut28334, _mut28335, _mut28336, _mut28337); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28286, _mut28287, _mut28288, _mut28289);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28290, _mut28291, _mut28292, _mut28293), rows);
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28328, _mut28329, _mut28330, _mut28331, _mut28332); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358");
                for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28323, _mut28324, _mut28325, _mut28326, _mut28327); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358");
                    final int jWidth = blockWidth(jBlock);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28294, _mut28295, _mut28296, _mut28297);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28298, _mut28299, _mut28300, _mut28301), columns);
                    final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28302, _mut28303, _mut28304, _mut28305), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28306, _mut28307, _mut28308, _mut28309)];
                    int k = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28310, _mut28311, _mut28312, _mut28313)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28314, _mut28315, _mut28316, _mut28317);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358", _mut28318, _mut28319, _mut28320, _mut28321, _mut28322); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1358");
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28338, _mut28339, _mut28340, _mut28341), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28342, _mut28343, _mut28344, _mut28345));
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28393, _mut28394, _mut28395, _mut28396, _mut28397); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28346, _mut28347, _mut28348, _mut28349);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28350, _mut28351, _mut28352, _mut28353), rows);
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28388, _mut28389, _mut28390, _mut28391, _mut28392); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382");
                for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28383, _mut28384, _mut28385, _mut28386, _mut28387); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382");
                    final int jWidth = blockWidth(jBlock);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28354, _mut28355, _mut28356, _mut28357);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28358, _mut28359, _mut28360, _mut28361), columns);
                    final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28362, _mut28363, _mut28364, _mut28365), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28366, _mut28367, _mut28368, _mut28369)];
                    int k = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28370, _mut28371, _mut28372, _mut28373)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28374, _mut28375, _mut28376, _mut28377);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382", _mut28378, _mut28379, _mut28380, _mut28381, _mut28382); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1382");
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28477, _mut28478, _mut28479, _mut28480), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28481, _mut28482, _mut28483, _mut28484), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28485, _mut28486, _mut28487, _mut28488, _mut28489); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28398, _mut28399, _mut28400, _mut28401);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28402, _mut28403, _mut28404, _mut28405)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28406, _mut28407, _mut28408, _mut28409), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28410, _mut28411, _mut28412, _mut28413));
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28472, _mut28473, _mut28474, _mut28475, _mut28476); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406");
                for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28459, _mut28460, _mut28461, _mut28462), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28463, _mut28464, _mut28465, _mut28466), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28467, _mut28468, _mut28469, _mut28470, _mut28471); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406");
                    final int jWidth = blockWidth(jBlock);
                    final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28414, _mut28415, _mut28416, _mut28417);
                    final int qStart = FastMath.max(startColumn, q0);
                    final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28418, _mut28419, _mut28420, _mut28421)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28422, _mut28423, _mut28424, _mut28425), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28426, _mut28427, _mut28428, _mut28429));
                    final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28430, _mut28431, _mut28432, _mut28433), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28434, _mut28435, _mut28436, _mut28437)];
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28438, _mut28439, _mut28440, _mut28441)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28442, _mut28443, _mut28444, _mut28445), qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28446, _mut28447, _mut28448, _mut28449), q0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28450, _mut28451, _mut28452, _mut28453);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406", _mut28454, _mut28455, _mut28456, _mut28457, _mut28458); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1406");
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28569, _mut28570, _mut28571, _mut28572), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28573, _mut28574, _mut28575, _mut28576), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28577, _mut28578, _mut28579, _mut28580, _mut28581); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28490, _mut28491, _mut28492, _mut28493);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28494, _mut28495, _mut28496, _mut28497)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28498, _mut28499, _mut28500, _mut28501), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28502, _mut28503, _mut28504, _mut28505));
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28564, _mut28565, _mut28566, _mut28567, _mut28568); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436");
                for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28551, _mut28552, _mut28553, _mut28554), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28555, _mut28556, _mut28557, _mut28558), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28559, _mut28560, _mut28561, _mut28562, _mut28563); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436");
                    final int jWidth = blockWidth(jBlock);
                    final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28506, _mut28507, _mut28508, _mut28509);
                    final int qStart = FastMath.max(startColumn, q0);
                    final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28510, _mut28511, _mut28512, _mut28513)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28514, _mut28515, _mut28516, _mut28517), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28518, _mut28519, _mut28520, _mut28521));
                    final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28522, _mut28523, _mut28524, _mut28525), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28526, _mut28527, _mut28528, _mut28529)];
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28530, _mut28531, _mut28532, _mut28533)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28534, _mut28535, _mut28536, _mut28537), qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28538, _mut28539, _mut28540, _mut28541), q0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28542, _mut28543, _mut28544, _mut28545);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436", _mut28546, _mut28547, _mut28548, _mut28549, _mut28550); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInRowOrder_1436");
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28582, _mut28583, _mut28584, _mut28585), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28586, _mut28587, _mut28588, _mut28589));
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28621, _mut28622, _mut28623, _mut28624, _mut28625); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28590, _mut28591, _mut28592, _mut28593);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28594, _mut28595, _mut28596, _mut28597), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28616, _mut28617, _mut28618, _mut28619, _mut28620); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28598, _mut28599, _mut28600, _mut28601);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28602, _mut28603, _mut28604, _mut28605), columns);
                final T[] block = blocks[blockIndex];
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28611, _mut28612, _mut28613, _mut28614, _mut28615); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466");
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466", _mut28606, _mut28607, _mut28608, _mut28609, _mut28610); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1466");
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
                ++blockIndex;
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28626, _mut28627, _mut28628, _mut28629), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28630, _mut28631, _mut28632, _mut28633));
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28665, _mut28666, _mut28667, _mut28668, _mut28669); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28634, _mut28635, _mut28636, _mut28637);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28638, _mut28639, _mut28640, _mut28641), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28660, _mut28661, _mut28662, _mut28663, _mut28664); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28642, _mut28643, _mut28644, _mut28645);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28646, _mut28647, _mut28648, _mut28649), columns);
                final T[] block = blocks[blockIndex];
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28655, _mut28656, _mut28657, _mut28658, _mut28659); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491");
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491", _mut28650, _mut28651, _mut28652, _mut28653, _mut28654); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1491");
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
                ++blockIndex;
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28749, _mut28750, _mut28751, _mut28752), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28753, _mut28754, _mut28755, _mut28756), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28757, _mut28758, _mut28759, _mut28760, _mut28761); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28670, _mut28671, _mut28672, _mut28673);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28674, _mut28675, _mut28676, _mut28677)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28678, _mut28679, _mut28680, _mut28681), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28682, _mut28683, _mut28684, _mut28685));
            for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28736, _mut28737, _mut28738, _mut28739), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28740, _mut28741, _mut28742, _mut28743), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28744, _mut28745, _mut28746, _mut28747, _mut28748); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516");
                final int jWidth = blockWidth(jBlock);
                final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28686, _mut28687, _mut28688, _mut28689);
                final int qStart = FastMath.max(startColumn, q0);
                final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28690, _mut28691, _mut28692, _mut28693)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28694, _mut28695, _mut28696, _mut28697), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28698, _mut28699, _mut28700, _mut28701));
                final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28702, _mut28703, _mut28704, _mut28705), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28706, _mut28707, _mut28708, _mut28709)];
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28731, _mut28732, _mut28733, _mut28734, _mut28735); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516");
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28710, _mut28711, _mut28712, _mut28713)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28714, _mut28715, _mut28716, _mut28717), qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28718, _mut28719, _mut28720, _mut28721), q0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28722, _mut28723, _mut28724, _mut28725);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516", _mut28726, _mut28727, _mut28728, _mut28729, _mut28730); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1516");
                        block[k] = visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546");
        checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28841, _mut28842, _mut28843, _mut28844), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28845, _mut28846, _mut28847, _mut28848), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28849, _mut28850, _mut28851, _mut28852, _mut28853); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28762, _mut28763, _mut28764, _mut28765);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28766, _mut28767, _mut28768, _mut28769)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28770, _mut28771, _mut28772, _mut28773), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28774, _mut28775, _mut28776, _mut28777));
            for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28828, _mut28829, _mut28830, _mut28831), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28832, _mut28833, _mut28834, _mut28835), "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28836, _mut28837, _mut28838, _mut28839, _mut28840); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546");
                final int jWidth = blockWidth(jBlock);
                final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28778, _mut28779, _mut28780, _mut28781);
                final int qStart = FastMath.max(startColumn, q0);
                final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28782, _mut28783, _mut28784, _mut28785)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28786, _mut28787, _mut28788, _mut28789), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28790, _mut28791, _mut28792, _mut28793));
                final T[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28794, _mut28795, _mut28796, _mut28797), jBlock, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28798, _mut28799, _mut28800, _mut28801)];
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28823, _mut28824, _mut28825, _mut28826, _mut28827); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546");
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28802, _mut28803, _mut28804, _mut28805)), jWidth, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28806, _mut28807, _mut28808, _mut28809), qStart, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28810, _mut28811, _mut28812, _mut28813), q0, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28814, _mut28815, _mut28816, _mut28817);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546", _mut28818, _mut28819, _mut28820, _mut28821, _mut28822); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.walkInOptimizedOrder_1546");
                        visitor.visit(p, q, block[k]);
                        ++k;
                    }
                }
            }
        }
        return visitor.end();
    }

    /**
     * Get the height of a block.
     * @param blockRow row index (in block sense) of the block
     * @return height (number of rows) of the block
     */
    private int blockHeight(final int blockRow) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.blockHeight_1580");
        return (ROR_equals(blockRow, AOR_minus(blockRows, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.blockHeight_1580", _mut28854, _mut28855, _mut28856, _mut28857), "org.apache.commons.math3.linear.BlockFieldMatrix.blockHeight_1580", _mut28858, _mut28859, _mut28860, _mut28861, _mut28862)) ? AOR_minus(rows, AOR_multiply(blockRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.blockHeight_1580", _mut28863, _mut28864, _mut28865, _mut28866), "org.apache.commons.math3.linear.BlockFieldMatrix.blockHeight_1580", _mut28867, _mut28868, _mut28869, _mut28870) : BLOCK_SIZE;
    }

    /**
     * Get the width of a block.
     * @param blockColumn column index (in block sense) of the block
     * @return width (number of columns) of the block
     */
    private int blockWidth(final int blockColumn) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockFieldMatrix.blockWidth_1589");
        return (ROR_equals(blockColumn, AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockFieldMatrix.blockWidth_1589", _mut28871, _mut28872, _mut28873, _mut28874), "org.apache.commons.math3.linear.BlockFieldMatrix.blockWidth_1589", _mut28875, _mut28876, _mut28877, _mut28878, _mut28879)) ? AOR_minus(columns, AOR_multiply(blockColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockFieldMatrix.blockWidth_1589", _mut28880, _mut28881, _mut28882, _mut28883), "org.apache.commons.math3.linear.BlockFieldMatrix.blockWidth_1589", _mut28884, _mut28885, _mut28886, _mut28887) : BLOCK_SIZE;
    }
}
