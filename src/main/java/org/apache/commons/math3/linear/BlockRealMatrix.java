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
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Cache-friendly implementation of RealMatrix using a flat arrays to store
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
 * for matrix multiplication). The default value is to use 52x52 blocks which is well suited
 * for processors with 64k L1 cache (one block holds 2704 values or 21632 bytes). This value
 * could be lowered to 36x36 for processors with 32k L1 cache.
 * </p>
 * <p>
 * The regular blocks represent {@link #BLOCK_SIZE} x {@link #BLOCK_SIZE} squares. Blocks
 * at right hand side and bottom side which may be smaller to fit matrix dimensions. The square
 * blocks are flattened in row major order in single dimension arrays which are therefore
 * {@link #BLOCK_SIZE}<sup>2</sup> elements long for regular blocks. The blocks are themselves
 * organized in row major order.
 * </p>
 * <p>
 * As an example, for a block size of 52x52, a 100x60 matrix would be stored in 4 blocks.
 * Block 0 would be a double[2704] array holding the upper left 52x52 square, block 1 would be
 * a double[416] array holding the upper right 52x8 rectangle, block 2 would be a double[2496]
 * array holding the lower left 48x52 rectangle and block 3 would be a double[384] array
 * holding the lower right 48x8 rectangle.
 * </p>
 * <p>
 * The layout complexity overhead versus simple mapping of matrices to java
 * arrays is negligible for small matrices (about 1%). The gain from cache efficiency leads
 * to up to 3-fold improvements for matrices of moderate to large size.
 * </p>
 * @since 2.0
 */
public class BlockRealMatrix extends AbstractRealMatrix implements Serializable {

    @Conditional
    public static boolean _mut29536 = false, _mut29537 = false, _mut29538 = false, _mut29539 = false, _mut29540 = false, _mut29541 = false, _mut29542 = false, _mut29543 = false, _mut29544 = false, _mut29545 = false, _mut29546 = false, _mut29547 = false, _mut29548 = false, _mut29549 = false, _mut29550 = false, _mut29551 = false, _mut29552 = false, _mut29553 = false, _mut29554 = false, _mut29555 = false, _mut29556 = false, _mut29557 = false, _mut29558 = false, _mut29559 = false, _mut29560 = false, _mut29561 = false, _mut29562 = false, _mut29563 = false, _mut29564 = false, _mut29565 = false, _mut29566 = false, _mut29567 = false, _mut29568 = false, _mut29569 = false, _mut29570 = false, _mut29571 = false, _mut29572 = false, _mut29573 = false, _mut29574 = false, _mut29575 = false, _mut29576 = false, _mut29577 = false, _mut29578 = false, _mut29579 = false, _mut29580 = false, _mut29581 = false, _mut29582 = false, _mut29583 = false, _mut29584 = false, _mut29585 = false, _mut29586 = false, _mut29587 = false, _mut29588 = false, _mut29589 = false, _mut29590 = false, _mut29591 = false, _mut29592 = false, _mut29593 = false, _mut29594 = false, _mut29595 = false, _mut29596 = false, _mut29597 = false, _mut29598 = false, _mut29599 = false, _mut29600 = false, _mut29601 = false, _mut29602 = false, _mut29603 = false, _mut29604 = false, _mut29605 = false, _mut29606 = false, _mut29607 = false, _mut29608 = false, _mut29609 = false, _mut29610 = false, _mut29611 = false, _mut29612 = false, _mut29613 = false, _mut29614 = false, _mut29615 = false, _mut29616 = false, _mut29617 = false, _mut29618 = false, _mut29619 = false, _mut29620 = false, _mut29621 = false, _mut29622 = false, _mut29623 = false, _mut29624 = false, _mut29625 = false, _mut29626 = false, _mut29627 = false, _mut29628 = false, _mut29629 = false, _mut29630 = false, _mut29631 = false, _mut29632 = false, _mut29633 = false, _mut29634 = false, _mut29635 = false, _mut29636 = false, _mut29637 = false, _mut29638 = false, _mut29639 = false, _mut29640 = false, _mut29641 = false, _mut29642 = false, _mut29643 = false, _mut29644 = false, _mut29645 = false, _mut29646 = false, _mut29647 = false, _mut29648 = false, _mut29649 = false, _mut29650 = false, _mut29651 = false, _mut29652 = false, _mut29653 = false, _mut29654 = false, _mut29655 = false, _mut29656 = false, _mut29657 = false, _mut29658 = false, _mut29659 = false, _mut29660 = false, _mut29661 = false, _mut29662 = false, _mut29663 = false, _mut29664 = false, _mut29665 = false, _mut29666 = false, _mut29667 = false, _mut29668 = false, _mut29669 = false, _mut29670 = false, _mut29671 = false, _mut29672 = false, _mut29673 = false, _mut29674 = false, _mut29675 = false, _mut29676 = false, _mut29677 = false, _mut29678 = false, _mut29679 = false, _mut29680 = false, _mut29681 = false, _mut29682 = false, _mut29683 = false, _mut29684 = false, _mut29685 = false, _mut29686 = false, _mut29687 = false, _mut29688 = false, _mut29689 = false, _mut29690 = false, _mut29691 = false, _mut29692 = false, _mut29693 = false, _mut29694 = false, _mut29695 = false, _mut29696 = false, _mut29697 = false, _mut29698 = false, _mut29699 = false, _mut29700 = false, _mut29701 = false, _mut29702 = false, _mut29703 = false, _mut29704 = false, _mut29705 = false, _mut29706 = false, _mut29707 = false, _mut29708 = false, _mut29709 = false, _mut29710 = false, _mut29711 = false, _mut29712 = false, _mut29713 = false, _mut29714 = false, _mut29715 = false, _mut29716 = false, _mut29717 = false, _mut29718 = false, _mut29719 = false, _mut29720 = false, _mut29721 = false, _mut29722 = false, _mut29723 = false, _mut29724 = false, _mut29725 = false, _mut29726 = false, _mut29727 = false, _mut29728 = false, _mut29729 = false, _mut29730 = false, _mut29731 = false, _mut29732 = false, _mut29733 = false, _mut29734 = false, _mut29735 = false, _mut29736 = false, _mut29737 = false, _mut29738 = false, _mut29739 = false, _mut29740 = false, _mut29741 = false, _mut29742 = false, _mut29743 = false, _mut29744 = false, _mut29745 = false, _mut29746 = false, _mut29747 = false, _mut29748 = false, _mut29749 = false, _mut29750 = false, _mut29751 = false, _mut29752 = false, _mut29753 = false, _mut29754 = false, _mut29755 = false, _mut29756 = false, _mut29757 = false, _mut29758 = false, _mut29759 = false, _mut29760 = false, _mut29761 = false, _mut29762 = false, _mut29763 = false, _mut29764 = false, _mut29765 = false, _mut29766 = false, _mut29767 = false, _mut29768 = false, _mut29769 = false, _mut29770 = false, _mut29771 = false, _mut29772 = false, _mut29773 = false, _mut29774 = false, _mut29775 = false, _mut29776 = false, _mut29777 = false, _mut29778 = false, _mut29779 = false, _mut29780 = false, _mut29781 = false, _mut29782 = false, _mut29783 = false, _mut29784 = false, _mut29785 = false, _mut29786 = false, _mut29787 = false, _mut29788 = false, _mut29789 = false, _mut29790 = false, _mut29791 = false, _mut29792 = false, _mut29793 = false, _mut29794 = false, _mut29795 = false, _mut29796 = false, _mut29797 = false, _mut29798 = false, _mut29799 = false, _mut29800 = false, _mut29801 = false, _mut29802 = false, _mut29803 = false, _mut29804 = false, _mut29805 = false, _mut29806 = false, _mut29807 = false, _mut29808 = false, _mut29809 = false, _mut29810 = false, _mut29811 = false, _mut29812 = false, _mut29813 = false, _mut29814 = false, _mut29815 = false, _mut29816 = false, _mut29817 = false, _mut29818 = false, _mut29819 = false, _mut29820 = false, _mut29821 = false, _mut29822 = false, _mut29823 = false, _mut29824 = false, _mut29825 = false, _mut29826 = false, _mut29827 = false, _mut29828 = false, _mut29829 = false, _mut29830 = false, _mut29831 = false, _mut29832 = false, _mut29833 = false, _mut29834 = false, _mut29835 = false, _mut29836 = false, _mut29837 = false, _mut29838 = false, _mut29839 = false, _mut29840 = false, _mut29841 = false, _mut29842 = false, _mut29843 = false, _mut29844 = false, _mut29845 = false, _mut29846 = false, _mut29847 = false, _mut29848 = false, _mut29849 = false, _mut29850 = false, _mut29851 = false, _mut29852 = false, _mut29853 = false, _mut29854 = false, _mut29855 = false, _mut29856 = false, _mut29857 = false, _mut29858 = false, _mut29859 = false, _mut29860 = false, _mut29861 = false, _mut29862 = false, _mut29863 = false, _mut29864 = false, _mut29865 = false, _mut29866 = false, _mut29867 = false, _mut29868 = false, _mut29869 = false, _mut29870 = false, _mut29871 = false, _mut29872 = false, _mut29873 = false, _mut29874 = false, _mut29875 = false, _mut29876 = false, _mut29877 = false, _mut29878 = false, _mut29879 = false, _mut29880 = false, _mut29881 = false, _mut29882 = false, _mut29883 = false, _mut29884 = false, _mut29885 = false, _mut29886 = false, _mut29887 = false, _mut29888 = false, _mut29889 = false, _mut29890 = false, _mut29891 = false, _mut29892 = false, _mut29893 = false, _mut29894 = false, _mut29895 = false, _mut29896 = false, _mut29897 = false, _mut29898 = false, _mut29899 = false, _mut29900 = false, _mut29901 = false, _mut29902 = false, _mut29903 = false, _mut29904 = false, _mut29905 = false, _mut29906 = false, _mut29907 = false, _mut29908 = false, _mut29909 = false, _mut29910 = false, _mut29911 = false, _mut29912 = false, _mut29913 = false, _mut29914 = false, _mut29915 = false, _mut29916 = false, _mut29917 = false, _mut29918 = false, _mut29919 = false, _mut29920 = false, _mut29921 = false, _mut29922 = false, _mut29923 = false, _mut29924 = false, _mut29925 = false, _mut29926 = false, _mut29927 = false, _mut29928 = false, _mut29929 = false, _mut29930 = false, _mut29931 = false, _mut29932 = false, _mut29933 = false, _mut29934 = false, _mut29935 = false, _mut29936 = false, _mut29937 = false, _mut29938 = false, _mut29939 = false, _mut29940 = false, _mut29941 = false, _mut29942 = false, _mut29943 = false, _mut29944 = false, _mut29945 = false, _mut29946 = false, _mut29947 = false, _mut29948 = false, _mut29949 = false, _mut29950 = false, _mut29951 = false, _mut29952 = false, _mut29953 = false, _mut29954 = false, _mut29955 = false, _mut29956 = false, _mut29957 = false, _mut29958 = false, _mut29959 = false, _mut29960 = false, _mut29961 = false, _mut29962 = false, _mut29963 = false, _mut29964 = false, _mut29965 = false, _mut29966 = false, _mut29967 = false, _mut29968 = false, _mut29969 = false, _mut29970 = false, _mut29971 = false, _mut29972 = false, _mut29973 = false, _mut29974 = false, _mut29975 = false, _mut29976 = false, _mut29977 = false, _mut29978 = false, _mut29979 = false, _mut29980 = false, _mut29981 = false, _mut29982 = false, _mut29983 = false, _mut29984 = false, _mut29985 = false, _mut29986 = false, _mut29987 = false, _mut29988 = false, _mut29989 = false, _mut29990 = false, _mut29991 = false, _mut29992 = false, _mut29993 = false, _mut29994 = false, _mut29995 = false, _mut29996 = false, _mut29997 = false, _mut29998 = false, _mut29999 = false, _mut30000 = false, _mut30001 = false, _mut30002 = false, _mut30003 = false, _mut30004 = false, _mut30005 = false, _mut30006 = false, _mut30007 = false, _mut30008 = false, _mut30009 = false, _mut30010 = false, _mut30011 = false, _mut30012 = false, _mut30013 = false, _mut30014 = false, _mut30015 = false, _mut30016 = false, _mut30017 = false, _mut30018 = false, _mut30019 = false, _mut30020 = false, _mut30021 = false, _mut30022 = false, _mut30023 = false, _mut30024 = false, _mut30025 = false, _mut30026 = false, _mut30027 = false, _mut30028 = false, _mut30029 = false, _mut30030 = false, _mut30031 = false, _mut30032 = false, _mut30033 = false, _mut30034 = false, _mut30035 = false, _mut30036 = false, _mut30037 = false, _mut30038 = false, _mut30039 = false, _mut30040 = false, _mut30041 = false, _mut30042 = false, _mut30043 = false, _mut30044 = false, _mut30045 = false, _mut30046 = false, _mut30047 = false, _mut30048 = false, _mut30049 = false, _mut30050 = false, _mut30051 = false, _mut30052 = false, _mut30053 = false, _mut30054 = false, _mut30055 = false, _mut30056 = false, _mut30057 = false, _mut30058 = false, _mut30059 = false, _mut30060 = false, _mut30061 = false, _mut30062 = false, _mut30063 = false, _mut30064 = false, _mut30065 = false, _mut30066 = false, _mut30067 = false, _mut30068 = false, _mut30069 = false, _mut30070 = false, _mut30071 = false, _mut30072 = false, _mut30073 = false, _mut30074 = false, _mut30075 = false, _mut30076 = false, _mut30077 = false, _mut30078 = false, _mut30079 = false, _mut30080 = false, _mut30081 = false, _mut30082 = false, _mut30083 = false, _mut30084 = false, _mut30085 = false, _mut30086 = false, _mut30087 = false, _mut30088 = false, _mut30089 = false, _mut30090 = false, _mut30091 = false, _mut30092 = false, _mut30093 = false, _mut30094 = false, _mut30095 = false, _mut30096 = false, _mut30097 = false, _mut30098 = false, _mut30099 = false, _mut30100 = false, _mut30101 = false, _mut30102 = false, _mut30103 = false, _mut30104 = false, _mut30105 = false, _mut30106 = false, _mut30107 = false, _mut30108 = false, _mut30109 = false, _mut30110 = false, _mut30111 = false, _mut30112 = false, _mut30113 = false, _mut30114 = false, _mut30115 = false, _mut30116 = false, _mut30117 = false, _mut30118 = false, _mut30119 = false, _mut30120 = false, _mut30121 = false, _mut30122 = false, _mut30123 = false, _mut30124 = false, _mut30125 = false, _mut30126 = false, _mut30127 = false, _mut30128 = false, _mut30129 = false, _mut30130 = false, _mut30131 = false, _mut30132 = false, _mut30133 = false, _mut30134 = false, _mut30135 = false, _mut30136 = false, _mut30137 = false, _mut30138 = false, _mut30139 = false, _mut30140 = false, _mut30141 = false, _mut30142 = false, _mut30143 = false, _mut30144 = false, _mut30145 = false, _mut30146 = false, _mut30147 = false, _mut30148 = false, _mut30149 = false, _mut30150 = false, _mut30151 = false, _mut30152 = false, _mut30153 = false, _mut30154 = false, _mut30155 = false, _mut30156 = false, _mut30157 = false, _mut30158 = false, _mut30159 = false, _mut30160 = false, _mut30161 = false, _mut30162 = false, _mut30163 = false, _mut30164 = false, _mut30165 = false, _mut30166 = false, _mut30167 = false, _mut30168 = false, _mut30169 = false, _mut30170 = false, _mut30171 = false, _mut30172 = false, _mut30173 = false, _mut30174 = false, _mut30175 = false, _mut30176 = false, _mut30177 = false, _mut30178 = false, _mut30179 = false, _mut30180 = false, _mut30181 = false, _mut30182 = false, _mut30183 = false, _mut30184 = false, _mut30185 = false, _mut30186 = false, _mut30187 = false, _mut30188 = false, _mut30189 = false, _mut30190 = false, _mut30191 = false, _mut30192 = false, _mut30193 = false, _mut30194 = false, _mut30195 = false, _mut30196 = false, _mut30197 = false, _mut30198 = false, _mut30199 = false, _mut30200 = false, _mut30201 = false, _mut30202 = false, _mut30203 = false, _mut30204 = false, _mut30205 = false, _mut30206 = false, _mut30207 = false, _mut30208 = false, _mut30209 = false, _mut30210 = false, _mut30211 = false, _mut30212 = false, _mut30213 = false, _mut30214 = false, _mut30215 = false, _mut30216 = false, _mut30217 = false, _mut30218 = false, _mut30219 = false, _mut30220 = false, _mut30221 = false, _mut30222 = false, _mut30223 = false, _mut30224 = false, _mut30225 = false, _mut30226 = false, _mut30227 = false, _mut30228 = false, _mut30229 = false, _mut30230 = false, _mut30231 = false, _mut30232 = false, _mut30233 = false, _mut30234 = false, _mut30235 = false, _mut30236 = false, _mut30237 = false, _mut30238 = false, _mut30239 = false, _mut30240 = false, _mut30241 = false, _mut30242 = false, _mut30243 = false, _mut30244 = false, _mut30245 = false, _mut30246 = false, _mut30247 = false, _mut30248 = false, _mut30249 = false, _mut30250 = false, _mut30251 = false, _mut30252 = false, _mut30253 = false, _mut30254 = false, _mut30255 = false, _mut30256 = false, _mut30257 = false, _mut30258 = false, _mut30259 = false, _mut30260 = false, _mut30261 = false, _mut30262 = false, _mut30263 = false, _mut30264 = false, _mut30265 = false, _mut30266 = false, _mut30267 = false, _mut30268 = false, _mut30269 = false, _mut30270 = false, _mut30271 = false, _mut30272 = false, _mut30273 = false, _mut30274 = false, _mut30275 = false, _mut30276 = false, _mut30277 = false, _mut30278 = false, _mut30279 = false, _mut30280 = false, _mut30281 = false, _mut30282 = false, _mut30283 = false, _mut30284 = false, _mut30285 = false, _mut30286 = false, _mut30287 = false, _mut30288 = false, _mut30289 = false, _mut30290 = false, _mut30291 = false, _mut30292 = false, _mut30293 = false, _mut30294 = false, _mut30295 = false, _mut30296 = false, _mut30297 = false, _mut30298 = false, _mut30299 = false, _mut30300 = false, _mut30301 = false, _mut30302 = false, _mut30303 = false, _mut30304 = false, _mut30305 = false, _mut30306 = false, _mut30307 = false, _mut30308 = false, _mut30309 = false, _mut30310 = false, _mut30311 = false, _mut30312 = false, _mut30313 = false, _mut30314 = false, _mut30315 = false, _mut30316 = false, _mut30317 = false, _mut30318 = false, _mut30319 = false, _mut30320 = false, _mut30321 = false, _mut30322 = false, _mut30323 = false, _mut30324 = false, _mut30325 = false, _mut30326 = false, _mut30327 = false, _mut30328 = false, _mut30329 = false, _mut30330 = false, _mut30331 = false, _mut30332 = false, _mut30333 = false, _mut30334 = false, _mut30335 = false, _mut30336 = false, _mut30337 = false, _mut30338 = false, _mut30339 = false, _mut30340 = false, _mut30341 = false, _mut30342 = false, _mut30343 = false, _mut30344 = false, _mut30345 = false, _mut30346 = false, _mut30347 = false, _mut30348 = false, _mut30349 = false, _mut30350 = false, _mut30351 = false, _mut30352 = false, _mut30353 = false, _mut30354 = false, _mut30355 = false, _mut30356 = false, _mut30357 = false, _mut30358 = false, _mut30359 = false, _mut30360 = false, _mut30361 = false, _mut30362 = false, _mut30363 = false, _mut30364 = false, _mut30365 = false, _mut30366 = false, _mut30367 = false, _mut30368 = false, _mut30369 = false, _mut30370 = false, _mut30371 = false, _mut30372 = false, _mut30373 = false, _mut30374 = false, _mut30375 = false, _mut30376 = false, _mut30377 = false, _mut30378 = false, _mut30379 = false, _mut30380 = false, _mut30381 = false, _mut30382 = false, _mut30383 = false, _mut30384 = false, _mut30385 = false, _mut30386 = false, _mut30387 = false, _mut30388 = false, _mut30389 = false, _mut30390 = false, _mut30391 = false, _mut30392 = false, _mut30393 = false, _mut30394 = false, _mut30395 = false, _mut30396 = false, _mut30397 = false, _mut30398 = false, _mut30399 = false, _mut30400 = false, _mut30401 = false, _mut30402 = false, _mut30403 = false, _mut30404 = false, _mut30405 = false, _mut30406 = false, _mut30407 = false, _mut30408 = false, _mut30409 = false, _mut30410 = false, _mut30411 = false, _mut30412 = false, _mut30413 = false, _mut30414 = false, _mut30415 = false, _mut30416 = false, _mut30417 = false, _mut30418 = false, _mut30419 = false, _mut30420 = false, _mut30421 = false, _mut30422 = false, _mut30423 = false, _mut30424 = false, _mut30425 = false, _mut30426 = false, _mut30427 = false, _mut30428 = false, _mut30429 = false, _mut30430 = false, _mut30431 = false, _mut30432 = false, _mut30433 = false, _mut30434 = false, _mut30435 = false, _mut30436 = false, _mut30437 = false, _mut30438 = false, _mut30439 = false, _mut30440 = false, _mut30441 = false, _mut30442 = false, _mut30443 = false, _mut30444 = false, _mut30445 = false, _mut30446 = false, _mut30447 = false, _mut30448 = false, _mut30449 = false, _mut30450 = false, _mut30451 = false, _mut30452 = false, _mut30453 = false, _mut30454 = false, _mut30455 = false, _mut30456 = false, _mut30457 = false, _mut30458 = false, _mut30459 = false, _mut30460 = false, _mut30461 = false, _mut30462 = false, _mut30463 = false, _mut30464 = false, _mut30465 = false, _mut30466 = false, _mut30467 = false, _mut30468 = false, _mut30469 = false, _mut30470 = false, _mut30471 = false, _mut30472 = false, _mut30473 = false, _mut30474 = false, _mut30475 = false, _mut30476 = false, _mut30477 = false, _mut30478 = false, _mut30479 = false, _mut30480 = false, _mut30481 = false, _mut30482 = false, _mut30483 = false, _mut30484 = false, _mut30485 = false, _mut30486 = false, _mut30487 = false, _mut30488 = false, _mut30489 = false, _mut30490 = false, _mut30491 = false, _mut30492 = false, _mut30493 = false, _mut30494 = false, _mut30495 = false, _mut30496 = false, _mut30497 = false, _mut30498 = false, _mut30499 = false, _mut30500 = false, _mut30501 = false, _mut30502 = false, _mut30503 = false, _mut30504 = false, _mut30505 = false, _mut30506 = false, _mut30507 = false, _mut30508 = false, _mut30509 = false, _mut30510 = false, _mut30511 = false, _mut30512 = false, _mut30513 = false, _mut30514 = false, _mut30515 = false, _mut30516 = false, _mut30517 = false, _mut30518 = false, _mut30519 = false, _mut30520 = false, _mut30521 = false, _mut30522 = false, _mut30523 = false, _mut30524 = false, _mut30525 = false, _mut30526 = false, _mut30527 = false, _mut30528 = false, _mut30529 = false, _mut30530 = false, _mut30531 = false, _mut30532 = false, _mut30533 = false, _mut30534 = false, _mut30535 = false, _mut30536 = false, _mut30537 = false, _mut30538 = false, _mut30539 = false, _mut30540 = false, _mut30541 = false, _mut30542 = false, _mut30543 = false, _mut30544 = false, _mut30545 = false, _mut30546 = false, _mut30547 = false, _mut30548 = false, _mut30549 = false, _mut30550 = false, _mut30551 = false, _mut30552 = false, _mut30553 = false, _mut30554 = false, _mut30555 = false, _mut30556 = false, _mut30557 = false, _mut30558 = false, _mut30559 = false, _mut30560 = false, _mut30561 = false, _mut30562 = false, _mut30563 = false, _mut30564 = false, _mut30565 = false, _mut30566 = false, _mut30567 = false, _mut30568 = false, _mut30569 = false, _mut30570 = false, _mut30571 = false, _mut30572 = false, _mut30573 = false, _mut30574 = false, _mut30575 = false, _mut30576 = false, _mut30577 = false, _mut30578 = false, _mut30579 = false, _mut30580 = false, _mut30581 = false, _mut30582 = false, _mut30583 = false, _mut30584 = false, _mut30585 = false, _mut30586 = false, _mut30587 = false, _mut30588 = false, _mut30589 = false, _mut30590 = false, _mut30591 = false, _mut30592 = false, _mut30593 = false, _mut30594 = false, _mut30595 = false, _mut30596 = false, _mut30597 = false, _mut30598 = false, _mut30599 = false, _mut30600 = false, _mut30601 = false, _mut30602 = false, _mut30603 = false, _mut30604 = false, _mut30605 = false, _mut30606 = false, _mut30607 = false, _mut30608 = false, _mut30609 = false, _mut30610 = false, _mut30611 = false, _mut30612 = false, _mut30613 = false, _mut30614 = false, _mut30615 = false, _mut30616 = false, _mut30617 = false, _mut30618 = false, _mut30619 = false, _mut30620 = false, _mut30621 = false, _mut30622 = false, _mut30623 = false, _mut30624 = false, _mut30625 = false, _mut30626 = false, _mut30627 = false, _mut30628 = false, _mut30629 = false, _mut30630 = false, _mut30631 = false, _mut30632 = false, _mut30633 = false, _mut30634 = false, _mut30635 = false, _mut30636 = false, _mut30637 = false, _mut30638 = false, _mut30639 = false, _mut30640 = false, _mut30641 = false, _mut30642 = false, _mut30643 = false, _mut30644 = false, _mut30645 = false, _mut30646 = false, _mut30647 = false, _mut30648 = false, _mut30649 = false, _mut30650 = false, _mut30651 = false, _mut30652 = false, _mut30653 = false, _mut30654 = false, _mut30655 = false, _mut30656 = false, _mut30657 = false, _mut30658 = false, _mut30659 = false, _mut30660 = false, _mut30661 = false, _mut30662 = false, _mut30663 = false, _mut30664 = false, _mut30665 = false, _mut30666 = false, _mut30667 = false, _mut30668 = false, _mut30669 = false, _mut30670 = false, _mut30671 = false, _mut30672 = false, _mut30673 = false, _mut30674 = false, _mut30675 = false, _mut30676 = false, _mut30677 = false, _mut30678 = false, _mut30679 = false, _mut30680 = false, _mut30681 = false, _mut30682 = false, _mut30683 = false, _mut30684 = false, _mut30685 = false, _mut30686 = false, _mut30687 = false, _mut30688 = false, _mut30689 = false, _mut30690 = false, _mut30691 = false, _mut30692 = false, _mut30693 = false, _mut30694 = false, _mut30695 = false, _mut30696 = false, _mut30697 = false, _mut30698 = false, _mut30699 = false, _mut30700 = false, _mut30701 = false, _mut30702 = false, _mut30703 = false, _mut30704 = false, _mut30705 = false, _mut30706 = false, _mut30707 = false, _mut30708 = false, _mut30709 = false, _mut30710 = false, _mut30711 = false, _mut30712 = false, _mut30713 = false, _mut30714 = false, _mut30715 = false, _mut30716 = false, _mut30717 = false, _mut30718 = false, _mut30719 = false, _mut30720 = false, _mut30721 = false, _mut30722 = false, _mut30723 = false, _mut30724 = false, _mut30725 = false, _mut30726 = false, _mut30727 = false, _mut30728 = false, _mut30729 = false, _mut30730 = false, _mut30731 = false, _mut30732 = false, _mut30733 = false, _mut30734 = false, _mut30735 = false, _mut30736 = false, _mut30737 = false, _mut30738 = false, _mut30739 = false, _mut30740 = false, _mut30741 = false, _mut30742 = false, _mut30743 = false, _mut30744 = false, _mut30745 = false, _mut30746 = false, _mut30747 = false, _mut30748 = false, _mut30749 = false, _mut30750 = false, _mut30751 = false, _mut30752 = false, _mut30753 = false, _mut30754 = false, _mut30755 = false, _mut30756 = false, _mut30757 = false, _mut30758 = false, _mut30759 = false, _mut30760 = false, _mut30761 = false, _mut30762 = false, _mut30763 = false, _mut30764 = false, _mut30765 = false, _mut30766 = false, _mut30767 = false, _mut30768 = false, _mut30769 = false, _mut30770 = false, _mut30771 = false, _mut30772 = false, _mut30773 = false, _mut30774 = false, _mut30775 = false, _mut30776 = false, _mut30777 = false, _mut30778 = false, _mut30779 = false, _mut30780 = false, _mut30781 = false, _mut30782 = false, _mut30783 = false, _mut30784 = false, _mut30785 = false, _mut30786 = false, _mut30787 = false, _mut30788 = false, _mut30789 = false, _mut30790 = false, _mut30791 = false, _mut30792 = false, _mut30793 = false, _mut30794 = false, _mut30795 = false, _mut30796 = false, _mut30797 = false, _mut30798 = false, _mut30799 = false, _mut30800 = false, _mut30801 = false, _mut30802 = false, _mut30803 = false, _mut30804 = false, _mut30805 = false, _mut30806 = false, _mut30807 = false, _mut30808 = false, _mut30809 = false, _mut30810 = false, _mut30811 = false, _mut30812 = false, _mut30813 = false, _mut30814 = false, _mut30815 = false, _mut30816 = false, _mut30817 = false, _mut30818 = false, _mut30819 = false, _mut30820 = false, _mut30821 = false, _mut30822 = false, _mut30823 = false, _mut30824 = false, _mut30825 = false, _mut30826 = false, _mut30827 = false, _mut30828 = false, _mut30829 = false, _mut30830 = false, _mut30831 = false, _mut30832 = false, _mut30833 = false, _mut30834 = false, _mut30835 = false, _mut30836 = false, _mut30837 = false, _mut30838 = false, _mut30839 = false, _mut30840 = false, _mut30841 = false, _mut30842 = false, _mut30843 = false, _mut30844 = false, _mut30845 = false, _mut30846 = false, _mut30847 = false, _mut30848 = false, _mut30849 = false, _mut30850 = false, _mut30851 = false, _mut30852 = false, _mut30853 = false, _mut30854 = false, _mut30855 = false, _mut30856 = false, _mut30857 = false, _mut30858 = false, _mut30859 = false, _mut30860 = false, _mut30861 = false, _mut30862 = false, _mut30863 = false, _mut30864 = false, _mut30865 = false, _mut30866 = false, _mut30867 = false, _mut30868 = false, _mut30869 = false, _mut30870 = false, _mut30871 = false, _mut30872 = false, _mut30873 = false, _mut30874 = false, _mut30875 = false, _mut30876 = false, _mut30877 = false, _mut30878 = false, _mut30879 = false, _mut30880 = false, _mut30881 = false, _mut30882 = false, _mut30883 = false, _mut30884 = false, _mut30885 = false, _mut30886 = false, _mut30887 = false, _mut30888 = false, _mut30889 = false, _mut30890 = false, _mut30891 = false, _mut30892 = false, _mut30893 = false, _mut30894 = false, _mut30895 = false, _mut30896 = false, _mut30897 = false, _mut30898 = false, _mut30899 = false, _mut30900 = false, _mut30901 = false, _mut30902 = false, _mut30903 = false, _mut30904 = false, _mut30905 = false, _mut30906 = false, _mut30907 = false, _mut30908 = false, _mut30909 = false, _mut30910 = false, _mut30911 = false, _mut30912 = false, _mut30913 = false, _mut30914 = false, _mut30915 = false, _mut30916 = false, _mut30917 = false, _mut30918 = false, _mut30919 = false, _mut30920 = false, _mut30921 = false, _mut30922 = false, _mut30923 = false, _mut30924 = false, _mut30925 = false, _mut30926 = false, _mut30927 = false, _mut30928 = false, _mut30929 = false, _mut30930 = false, _mut30931 = false, _mut30932 = false, _mut30933 = false, _mut30934 = false, _mut30935 = false, _mut30936 = false, _mut30937 = false, _mut30938 = false, _mut30939 = false, _mut30940 = false, _mut30941 = false, _mut30942 = false, _mut30943 = false, _mut30944 = false, _mut30945 = false, _mut30946 = false, _mut30947 = false, _mut30948 = false, _mut30949 = false, _mut30950 = false, _mut30951 = false, _mut30952 = false, _mut30953 = false, _mut30954 = false, _mut30955 = false, _mut30956 = false, _mut30957 = false, _mut30958 = false, _mut30959 = false, _mut30960 = false, _mut30961 = false, _mut30962 = false, _mut30963 = false, _mut30964 = false, _mut30965 = false, _mut30966 = false, _mut30967 = false, _mut30968 = false, _mut30969 = false, _mut30970 = false, _mut30971 = false, _mut30972 = false, _mut30973 = false, _mut30974 = false, _mut30975 = false, _mut30976 = false, _mut30977 = false, _mut30978 = false, _mut30979 = false, _mut30980 = false, _mut30981 = false, _mut30982 = false, _mut30983 = false, _mut30984 = false, _mut30985 = false, _mut30986 = false, _mut30987 = false, _mut30988 = false, _mut30989 = false, _mut30990 = false, _mut30991 = false, _mut30992 = false, _mut30993 = false, _mut30994 = false, _mut30995 = false, _mut30996 = false, _mut30997 = false, _mut30998 = false, _mut30999 = false, _mut31000 = false, _mut31001 = false, _mut31002 = false, _mut31003 = false, _mut31004 = false, _mut31005 = false, _mut31006 = false, _mut31007 = false, _mut31008 = false, _mut31009 = false, _mut31010 = false, _mut31011 = false, _mut31012 = false, _mut31013 = false, _mut31014 = false, _mut31015 = false, _mut31016 = false, _mut31017 = false, _mut31018 = false, _mut31019 = false, _mut31020 = false, _mut31021 = false, _mut31022 = false, _mut31023 = false, _mut31024 = false, _mut31025 = false, _mut31026 = false, _mut31027 = false, _mut31028 = false, _mut31029 = false, _mut31030 = false, _mut31031 = false, _mut31032 = false, _mut31033 = false, _mut31034 = false, _mut31035 = false, _mut31036 = false, _mut31037 = false, _mut31038 = false, _mut31039 = false, _mut31040 = false, _mut31041 = false, _mut31042 = false, _mut31043 = false, _mut31044 = false, _mut31045 = false, _mut31046 = false, _mut31047 = false, _mut31048 = false, _mut31049 = false, _mut31050 = false, _mut31051 = false, _mut31052 = false, _mut31053 = false, _mut31054 = false, _mut31055 = false, _mut31056 = false, _mut31057 = false, _mut31058 = false, _mut31059 = false, _mut31060 = false, _mut31061 = false, _mut31062 = false, _mut31063 = false, _mut31064 = false, _mut31065 = false, _mut31066 = false, _mut31067 = false, _mut31068 = false, _mut31069 = false, _mut31070 = false, _mut31071 = false, _mut31072 = false, _mut31073 = false, _mut31074 = false, _mut31075 = false, _mut31076 = false, _mut31077 = false, _mut31078 = false, _mut31079 = false, _mut31080 = false, _mut31081 = false, _mut31082 = false, _mut31083 = false, _mut31084 = false, _mut31085 = false, _mut31086 = false, _mut31087 = false, _mut31088 = false, _mut31089 = false, _mut31090 = false, _mut31091 = false, _mut31092 = false, _mut31093 = false, _mut31094 = false, _mut31095 = false, _mut31096 = false, _mut31097 = false, _mut31098 = false, _mut31099 = false, _mut31100 = false, _mut31101 = false, _mut31102 = false, _mut31103 = false, _mut31104 = false, _mut31105 = false, _mut31106 = false, _mut31107 = false, _mut31108 = false, _mut31109 = false, _mut31110 = false, _mut31111 = false, _mut31112 = false, _mut31113 = false, _mut31114 = false, _mut31115 = false, _mut31116 = false, _mut31117 = false, _mut31118 = false, _mut31119 = false, _mut31120 = false, _mut31121 = false, _mut31122 = false, _mut31123 = false, _mut31124 = false, _mut31125 = false, _mut31126 = false, _mut31127 = false, _mut31128 = false, _mut31129 = false, _mut31130 = false, _mut31131 = false, _mut31132 = false, _mut31133 = false, _mut31134 = false, _mut31135 = false, _mut31136 = false, _mut31137 = false, _mut31138 = false, _mut31139 = false, _mut31140 = false, _mut31141 = false, _mut31142 = false, _mut31143 = false, _mut31144 = false, _mut31145 = false, _mut31146 = false, _mut31147 = false, _mut31148 = false, _mut31149 = false, _mut31150 = false, _mut31151 = false, _mut31152 = false, _mut31153 = false, _mut31154 = false, _mut31155 = false, _mut31156 = false, _mut31157 = false, _mut31158 = false, _mut31159 = false, _mut31160 = false, _mut31161 = false, _mut31162 = false, _mut31163 = false, _mut31164 = false, _mut31165 = false, _mut31166 = false, _mut31167 = false, _mut31168 = false, _mut31169 = false, _mut31170 = false, _mut31171 = false, _mut31172 = false, _mut31173 = false, _mut31174 = false, _mut31175 = false, _mut31176 = false, _mut31177 = false, _mut31178 = false, _mut31179 = false, _mut31180 = false, _mut31181 = false, _mut31182 = false, _mut31183 = false, _mut31184 = false, _mut31185 = false, _mut31186 = false, _mut31187 = false, _mut31188 = false, _mut31189 = false, _mut31190 = false, _mut31191 = false, _mut31192 = false, _mut31193 = false, _mut31194 = false, _mut31195 = false, _mut31196 = false, _mut31197 = false, _mut31198 = false, _mut31199 = false, _mut31200 = false, _mut31201 = false, _mut31202 = false, _mut31203 = false, _mut31204 = false, _mut31205 = false, _mut31206 = false, _mut31207 = false, _mut31208 = false, _mut31209 = false, _mut31210 = false, _mut31211 = false, _mut31212 = false, _mut31213 = false, _mut31214 = false, _mut31215 = false, _mut31216 = false, _mut31217 = false, _mut31218 = false, _mut31219 = false, _mut31220 = false, _mut31221 = false, _mut31222 = false, _mut31223 = false, _mut31224 = false, _mut31225 = false, _mut31226 = false, _mut31227 = false, _mut31228 = false, _mut31229 = false, _mut31230 = false, _mut31231 = false, _mut31232 = false, _mut31233 = false, _mut31234 = false, _mut31235 = false, _mut31236 = false, _mut31237 = false, _mut31238 = false, _mut31239 = false, _mut31240 = false, _mut31241 = false, _mut31242 = false, _mut31243 = false, _mut31244 = false, _mut31245 = false, _mut31246 = false, _mut31247 = false, _mut31248 = false, _mut31249 = false, _mut31250 = false, _mut31251 = false, _mut31252 = false, _mut31253 = false, _mut31254 = false, _mut31255 = false, _mut31256 = false, _mut31257 = false, _mut31258 = false, _mut31259 = false, _mut31260 = false, _mut31261 = false, _mut31262 = false, _mut31263 = false, _mut31264 = false, _mut31265 = false, _mut31266 = false, _mut31267 = false, _mut31268 = false, _mut31269 = false, _mut31270 = false, _mut31271 = false, _mut31272 = false, _mut31273 = false, _mut31274 = false, _mut31275 = false, _mut31276 = false, _mut31277 = false, _mut31278 = false, _mut31279 = false, _mut31280 = false, _mut31281 = false, _mut31282 = false, _mut31283 = false, _mut31284 = false, _mut31285 = false, _mut31286 = false, _mut31287 = false, _mut31288 = false, _mut31289 = false, _mut31290 = false, _mut31291 = false, _mut31292 = false, _mut31293 = false, _mut31294 = false, _mut31295 = false, _mut31296 = false, _mut31297 = false, _mut31298 = false, _mut31299 = false, _mut31300 = false, _mut31301 = false, _mut31302 = false, _mut31303 = false, _mut31304 = false, _mut31305 = false, _mut31306 = false, _mut31307 = false, _mut31308 = false, _mut31309 = false, _mut31310 = false, _mut31311 = false, _mut31312 = false, _mut31313 = false, _mut31314 = false, _mut31315 = false, _mut31316 = false, _mut31317 = false, _mut31318 = false, _mut31319 = false, _mut31320 = false, _mut31321 = false, _mut31322 = false, _mut31323 = false, _mut31324 = false, _mut31325 = false, _mut31326 = false, _mut31327 = false, _mut31328 = false, _mut31329 = false, _mut31330 = false, _mut31331 = false, _mut31332 = false, _mut31333 = false, _mut31334 = false, _mut31335 = false, _mut31336 = false, _mut31337 = false, _mut31338 = false, _mut31339 = false, _mut31340 = false, _mut31341 = false, _mut31342 = false, _mut31343 = false, _mut31344 = false, _mut31345 = false, _mut31346 = false, _mut31347 = false, _mut31348 = false, _mut31349 = false, _mut31350 = false, _mut31351 = false, _mut31352 = false, _mut31353 = false, _mut31354 = false, _mut31355 = false, _mut31356 = false, _mut31357 = false, _mut31358 = false, _mut31359 = false, _mut31360 = false, _mut31361 = false, _mut31362 = false, _mut31363 = false, _mut31364 = false, _mut31365 = false, _mut31366 = false, _mut31367 = false, _mut31368 = false, _mut31369 = false, _mut31370 = false, _mut31371 = false, _mut31372 = false, _mut31373 = false, _mut31374 = false, _mut31375 = false, _mut31376 = false, _mut31377 = false, _mut31378 = false, _mut31379 = false, _mut31380 = false, _mut31381 = false, _mut31382 = false, _mut31383 = false, _mut31384 = false, _mut31385 = false, _mut31386 = false, _mut31387 = false, _mut31388 = false, _mut31389 = false, _mut31390 = false, _mut31391 = false, _mut31392 = false, _mut31393 = false, _mut31394 = false, _mut31395 = false, _mut31396 = false, _mut31397 = false, _mut31398 = false, _mut31399 = false, _mut31400 = false, _mut31401 = false, _mut31402 = false, _mut31403 = false, _mut31404 = false, _mut31405 = false, _mut31406 = false, _mut31407 = false, _mut31408 = false, _mut31409 = false, _mut31410 = false, _mut31411 = false, _mut31412 = false, _mut31413 = false, _mut31414 = false, _mut31415 = false, _mut31416 = false, _mut31417 = false, _mut31418 = false, _mut31419 = false, _mut31420 = false, _mut31421 = false, _mut31422 = false, _mut31423 = false, _mut31424 = false, _mut31425 = false, _mut31426 = false, _mut31427 = false, _mut31428 = false, _mut31429 = false, _mut31430 = false, _mut31431 = false, _mut31432 = false, _mut31433 = false, _mut31434 = false, _mut31435 = false, _mut31436 = false, _mut31437 = false, _mut31438 = false, _mut31439 = false, _mut31440 = false, _mut31441 = false, _mut31442 = false, _mut31443 = false, _mut31444 = false, _mut31445 = false, _mut31446 = false, _mut31447 = false, _mut31448 = false, _mut31449 = false, _mut31450 = false, _mut31451 = false, _mut31452 = false, _mut31453 = false, _mut31454 = false, _mut31455 = false, _mut31456 = false, _mut31457 = false, _mut31458 = false, _mut31459 = false, _mut31460 = false, _mut31461 = false, _mut31462 = false, _mut31463 = false, _mut31464 = false, _mut31465 = false, _mut31466 = false, _mut31467 = false, _mut31468 = false, _mut31469 = false, _mut31470 = false, _mut31471 = false, _mut31472 = false, _mut31473 = false, _mut31474 = false, _mut31475 = false, _mut31476 = false, _mut31477 = false, _mut31478 = false, _mut31479 = false, _mut31480 = false, _mut31481 = false, _mut31482 = false, _mut31483 = false, _mut31484 = false, _mut31485 = false, _mut31486 = false, _mut31487 = false, _mut31488 = false, _mut31489 = false, _mut31490 = false, _mut31491 = false, _mut31492 = false, _mut31493 = false, _mut31494 = false, _mut31495 = false, _mut31496 = false, _mut31497 = false, _mut31498 = false, _mut31499 = false, _mut31500 = false, _mut31501 = false, _mut31502 = false, _mut31503 = false, _mut31504 = false, _mut31505 = false, _mut31506 = false, _mut31507 = false, _mut31508 = false, _mut31509 = false, _mut31510 = false, _mut31511 = false, _mut31512 = false, _mut31513 = false, _mut31514 = false, _mut31515 = false, _mut31516 = false, _mut31517 = false, _mut31518 = false, _mut31519 = false, _mut31520 = false, _mut31521 = false, _mut31522 = false, _mut31523 = false, _mut31524 = false, _mut31525 = false, _mut31526 = false, _mut31527 = false, _mut31528 = false, _mut31529 = false, _mut31530 = false, _mut31531 = false, _mut31532 = false, _mut31533 = false, _mut31534 = false, _mut31535 = false, _mut31536 = false, _mut31537 = false, _mut31538 = false, _mut31539 = false, _mut31540 = false, _mut31541 = false, _mut31542 = false, _mut31543 = false, _mut31544 = false, _mut31545 = false, _mut31546 = false, _mut31547 = false, _mut31548 = false, _mut31549 = false, _mut31550 = false, _mut31551 = false, _mut31552 = false, _mut31553 = false, _mut31554 = false, _mut31555 = false, _mut31556 = false, _mut31557 = false, _mut31558 = false, _mut31559 = false, _mut31560 = false, _mut31561 = false, _mut31562 = false, _mut31563 = false, _mut31564 = false, _mut31565 = false, _mut31566 = false, _mut31567 = false, _mut31568 = false, _mut31569 = false, _mut31570 = false, _mut31571 = false, _mut31572 = false, _mut31573 = false, _mut31574 = false, _mut31575 = false, _mut31576 = false, _mut31577 = false, _mut31578 = false, _mut31579 = false, _mut31580 = false, _mut31581 = false, _mut31582 = false, _mut31583 = false, _mut31584 = false, _mut31585 = false, _mut31586 = false, _mut31587 = false, _mut31588 = false, _mut31589 = false, _mut31590 = false, _mut31591 = false, _mut31592 = false, _mut31593 = false, _mut31594 = false, _mut31595 = false, _mut31596 = false, _mut31597 = false, _mut31598 = false, _mut31599 = false, _mut31600 = false, _mut31601 = false, _mut31602 = false, _mut31603 = false, _mut31604 = false, _mut31605 = false, _mut31606 = false, _mut31607 = false, _mut31608 = false, _mut31609 = false, _mut31610 = false, _mut31611 = false, _mut31612 = false, _mut31613 = false, _mut31614 = false, _mut31615 = false, _mut31616 = false, _mut31617 = false, _mut31618 = false, _mut31619 = false, _mut31620 = false, _mut31621 = false, _mut31622 = false, _mut31623 = false, _mut31624 = false, _mut31625 = false, _mut31626 = false, _mut31627 = false, _mut31628 = false, _mut31629 = false, _mut31630 = false, _mut31631 = false, _mut31632 = false, _mut31633 = false, _mut31634 = false, _mut31635 = false, _mut31636 = false, _mut31637 = false, _mut31638 = false, _mut31639 = false, _mut31640 = false, _mut31641 = false, _mut31642 = false, _mut31643 = false, _mut31644 = false, _mut31645 = false, _mut31646 = false, _mut31647 = false, _mut31648 = false, _mut31649 = false, _mut31650 = false, _mut31651 = false, _mut31652 = false, _mut31653 = false, _mut31654 = false, _mut31655 = false, _mut31656 = false, _mut31657 = false, _mut31658 = false, _mut31659 = false, _mut31660 = false, _mut31661 = false, _mut31662 = false, _mut31663 = false, _mut31664 = false, _mut31665 = false, _mut31666 = false, _mut31667 = false, _mut31668 = false, _mut31669 = false, _mut31670 = false, _mut31671 = false, _mut31672 = false, _mut31673 = false, _mut31674 = false, _mut31675 = false, _mut31676 = false, _mut31677 = false, _mut31678 = false, _mut31679 = false, _mut31680 = false, _mut31681 = false, _mut31682 = false, _mut31683 = false, _mut31684 = false, _mut31685 = false, _mut31686 = false, _mut31687 = false, _mut31688 = false, _mut31689 = false, _mut31690 = false, _mut31691 = false, _mut31692 = false, _mut31693 = false, _mut31694 = false, _mut31695 = false, _mut31696 = false, _mut31697 = false, _mut31698 = false, _mut31699 = false, _mut31700 = false, _mut31701 = false, _mut31702 = false, _mut31703 = false, _mut31704 = false, _mut31705 = false, _mut31706 = false, _mut31707 = false, _mut31708 = false, _mut31709 = false, _mut31710 = false, _mut31711 = false, _mut31712 = false, _mut31713 = false, _mut31714 = false, _mut31715 = false, _mut31716 = false, _mut31717 = false, _mut31718 = false, _mut31719 = false, _mut31720 = false, _mut31721 = false, _mut31722 = false, _mut31723 = false, _mut31724 = false, _mut31725 = false, _mut31726 = false, _mut31727 = false, _mut31728 = false, _mut31729 = false, _mut31730 = false, _mut31731 = false, _mut31732 = false, _mut31733 = false, _mut31734 = false, _mut31735 = false, _mut31736 = false, _mut31737 = false, _mut31738 = false, _mut31739 = false, _mut31740 = false, _mut31741 = false, _mut31742 = false, _mut31743 = false, _mut31744 = false, _mut31745 = false, _mut31746 = false, _mut31747 = false, _mut31748 = false, _mut31749 = false, _mut31750 = false, _mut31751 = false, _mut31752 = false, _mut31753 = false, _mut31754 = false, _mut31755 = false, _mut31756 = false, _mut31757 = false, _mut31758 = false, _mut31759 = false, _mut31760 = false, _mut31761 = false, _mut31762 = false, _mut31763 = false, _mut31764 = false, _mut31765 = false, _mut31766 = false, _mut31767 = false, _mut31768 = false, _mut31769 = false, _mut31770 = false, _mut31771 = false, _mut31772 = false, _mut31773 = false, _mut31774 = false, _mut31775 = false, _mut31776 = false, _mut31777 = false, _mut31778 = false, _mut31779 = false, _mut31780 = false, _mut31781 = false, _mut31782 = false, _mut31783 = false, _mut31784 = false, _mut31785 = false, _mut31786 = false, _mut31787 = false, _mut31788 = false, _mut31789 = false, _mut31790 = false, _mut31791 = false, _mut31792 = false, _mut31793 = false, _mut31794 = false, _mut31795 = false, _mut31796 = false, _mut31797 = false, _mut31798 = false, _mut31799 = false, _mut31800 = false, _mut31801 = false, _mut31802 = false, _mut31803 = false, _mut31804 = false, _mut31805 = false, _mut31806 = false, _mut31807 = false, _mut31808 = false, _mut31809 = false, _mut31810 = false, _mut31811 = false, _mut31812 = false, _mut31813 = false, _mut31814 = false, _mut31815 = false, _mut31816 = false, _mut31817 = false, _mut31818 = false, _mut31819 = false, _mut31820 = false, _mut31821 = false, _mut31822 = false, _mut31823 = false, _mut31824 = false, _mut31825 = false, _mut31826 = false, _mut31827 = false, _mut31828 = false, _mut31829 = false, _mut31830 = false, _mut31831 = false, _mut31832 = false, _mut31833 = false, _mut31834 = false, _mut31835 = false, _mut31836 = false, _mut31837 = false, _mut31838 = false, _mut31839 = false, _mut31840 = false, _mut31841 = false, _mut31842 = false, _mut31843 = false, _mut31844 = false, _mut31845 = false, _mut31846 = false, _mut31847 = false, _mut31848 = false, _mut31849 = false, _mut31850 = false, _mut31851 = false, _mut31852 = false, _mut31853 = false, _mut31854 = false, _mut31855 = false, _mut31856 = false, _mut31857 = false, _mut31858 = false, _mut31859 = false, _mut31860 = false, _mut31861 = false, _mut31862 = false, _mut31863 = false, _mut31864 = false, _mut31865 = false, _mut31866 = false, _mut31867 = false, _mut31868 = false, _mut31869 = false, _mut31870 = false, _mut31871 = false, _mut31872 = false, _mut31873 = false, _mut31874 = false, _mut31875 = false, _mut31876 = false, _mut31877 = false, _mut31878 = false, _mut31879 = false, _mut31880 = false, _mut31881 = false, _mut31882 = false, _mut31883 = false, _mut31884 = false, _mut31885 = false, _mut31886 = false, _mut31887 = false, _mut31888 = false, _mut31889 = false, _mut31890 = false, _mut31891 = false, _mut31892 = false, _mut31893 = false, _mut31894 = false, _mut31895 = false, _mut31896 = false, _mut31897 = false, _mut31898 = false, _mut31899 = false, _mut31900 = false, _mut31901 = false, _mut31902 = false, _mut31903 = false, _mut31904 = false, _mut31905 = false, _mut31906 = false, _mut31907 = false, _mut31908 = false, _mut31909 = false, _mut31910 = false, _mut31911 = false, _mut31912 = false, _mut31913 = false, _mut31914 = false, _mut31915 = false, _mut31916 = false, _mut31917 = false, _mut31918 = false, _mut31919 = false, _mut31920 = false, _mut31921 = false, _mut31922 = false, _mut31923 = false, _mut31924 = false, _mut31925 = false, _mut31926 = false, _mut31927 = false, _mut31928 = false, _mut31929 = false, _mut31930 = false, _mut31931 = false, _mut31932 = false, _mut31933 = false, _mut31934 = false, _mut31935 = false, _mut31936 = false, _mut31937 = false, _mut31938 = false, _mut31939 = false, _mut31940 = false, _mut31941 = false, _mut31942 = false, _mut31943 = false, _mut31944 = false, _mut31945 = false, _mut31946 = false, _mut31947 = false, _mut31948 = false, _mut31949 = false, _mut31950 = false, _mut31951 = false, _mut31952 = false, _mut31953 = false, _mut31954 = false, _mut31955 = false, _mut31956 = false, _mut31957 = false, _mut31958 = false, _mut31959 = false, _mut31960 = false, _mut31961 = false, _mut31962 = false, _mut31963 = false, _mut31964 = false, _mut31965 = false, _mut31966 = false, _mut31967 = false, _mut31968 = false, _mut31969 = false, _mut31970 = false, _mut31971 = false, _mut31972 = false, _mut31973 = false, _mut31974 = false, _mut31975 = false, _mut31976 = false, _mut31977 = false, _mut31978 = false, _mut31979 = false, _mut31980 = false, _mut31981 = false, _mut31982 = false, _mut31983 = false, _mut31984 = false, _mut31985 = false, _mut31986 = false, _mut31987 = false, _mut31988 = false, _mut31989 = false, _mut31990 = false, _mut31991 = false, _mut31992 = false, _mut31993 = false, _mut31994 = false, _mut31995 = false, _mut31996 = false, _mut31997 = false, _mut31998 = false, _mut31999 = false, _mut32000 = false, _mut32001 = false, _mut32002 = false, _mut32003 = false, _mut32004 = false, _mut32005 = false, _mut32006 = false, _mut32007 = false, _mut32008 = false, _mut32009 = false, _mut32010 = false, _mut32011 = false, _mut32012 = false, _mut32013 = false, _mut32014 = false, _mut32015 = false, _mut32016 = false, _mut32017 = false, _mut32018 = false, _mut32019 = false, _mut32020 = false;

    /**
     * Block size.
     */
    public static final int BLOCK_SIZE = 52;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 4991895511313664478L;

    /**
     * Blocks of matrix entries.
     */
    private final double[][] blocks;

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
     * @param rows  the number of rows in the new matrix
     * @param columns  the number of columns in the new matrix
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     */
    public BlockRealMatrix(final int rows, final int columns) throws NotStrictlyPositiveException {
        super(rows, columns);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95");
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29536, _mut29537, _mut29538, _mut29539), 1, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29540, _mut29541, _mut29542, _mut29543)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29544, _mut29545, _mut29546, _mut29547);
        blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29548, _mut29549, _mut29550, _mut29551), 1, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29552, _mut29553, _mut29554, _mut29555)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_95", _mut29556, _mut29557, _mut29558, _mut29559);
        // allocate storage blocks, taking care of smaller ones at right and bottom
        blocks = createBlocksLayout(rows, columns);
    }

    /**
     * Create a new dense matrix copying entries from raw layout data.
     * <p>The input array <em>must</em> already be in raw layout.</p>
     * <p>Calling this constructor is equivalent to call:
     * <pre>matrix = new BlockRealMatrix(rawData.length, rawData[0].length,
     *                                   toBlocksLayout(rawData), false);</pre>
     * </p>
     *
     * @param rawData data for new matrix, in raw layout
     * @throws DimensionMismatchException if the shape of {@code blockData} is
     * inconsistent with block layout.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     * @see #BlockRealMatrix(int, int, double[][], boolean)
     */
    public BlockRealMatrix(final double[][] rawData) throws DimensionMismatchException, NotStrictlyPositiveException {
        this(rawData.length, rawData[0].length, toBlocksLayout(rawData), false);
    }

    /**
     * Create a new dense matrix copying entries from block layout data.
     * <p>The input array <em>must</em> already be in blocks layout.</p>
     *
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @param blockData data for new matrix
     * @param copyArray Whether the input array will be copied or referenced.
     * @throws DimensionMismatchException if the shape of {@code blockData} is
     * inconsistent with block layout.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     * @see #createBlocksLayout(int, int)
     * @see #toBlocksLayout(double[][])
     * @see #BlockRealMatrix(double[][])
     */
    public BlockRealMatrix(final int rows, final int columns, final double[][] blockData, final boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(rows, columns);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145");
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29560, _mut29561, _mut29562, _mut29563), 1, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29564, _mut29565, _mut29566, _mut29567)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29568, _mut29569, _mut29570, _mut29571);
        blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29572, _mut29573, _mut29574, _mut29575), 1, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29576, _mut29577, _mut29578, _mut29579)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29580, _mut29581, _mut29582, _mut29583);
        if (copyArray) {
            // allocate storage blocks, taking care of smaller ones at right and bottom
            blocks = new double[AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29584, _mut29585, _mut29586, _mut29587)][];
        } else {
            // reference existing array
            blocks = blockData;
        }
        int index = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29606, _mut29607, _mut29608, _mut29609, _mut29610); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145");
            final int iHeight = blockHeight(iBlock);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29601, _mut29602, _mut29603, _mut29604, _mut29605); ++jBlock, ++index) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145");
                if (ROR_not_equals(blockData[index].length, AOR_multiply(iHeight, blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29588, _mut29589, _mut29590, _mut29591), "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29592, _mut29593, _mut29594, _mut29595, _mut29596)) {
                    throw new DimensionMismatchException(blockData[index].length, AOR_multiply(iHeight, blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.BlockRealMatrix_145", _mut29597, _mut29598, _mut29599, _mut29600));
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
     * is the layout used in {@link BlockRealMatrix} instances, where the matrix
     * is split in square blocks (except at right and bottom side where blocks may
     * be rectangular to fit matrix size) and each block is stored in a flattened
     * one-dimensional array.
     * </p>
     * <p>
     * This method creates an array in blocks layout from an input array in raw layout.
     * It can be used to provide the array argument of the {@link
     * #BlockRealMatrix(int, int, double[][], boolean)} constructor.
     * </p>
     * @param rawData Data array in raw layout.
     * @return a new data array containing the same entries but in blocks layout.
     * @throws DimensionMismatchException if {@code rawData} is not rectangular.
     * @see #createBlocksLayout(int, int)
     * @see #BlockRealMatrix(int, int, double[][], boolean)
     */
    public static double[][] toBlocksLayout(final double[][] rawData) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200");
        final int rows = rawData.length;
        final int columns = rawData[0].length;
        final int blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29611, _mut29612, _mut29613, _mut29614), 1, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29615, _mut29616, _mut29617, _mut29618)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29619, _mut29620, _mut29621, _mut29622);
        final int blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29623, _mut29624, _mut29625, _mut29626), 1, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29627, _mut29628, _mut29629, _mut29630)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29631, _mut29632, _mut29633, _mut29634);
        // safety checks
        for (int i = 0; ROR_less(i, rawData.length, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29640, _mut29641, _mut29642, _mut29643, _mut29644); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200");
            final int length = rawData[i].length;
            if (ROR_not_equals(length, columns, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29635, _mut29636, _mut29637, _mut29638, _mut29639)) {
                throw new DimensionMismatchException(columns, length);
            }
        }
        // convert array
        final double[][] blocks = new double[AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29645, _mut29646, _mut29647, _mut29648)][];
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29687, _mut29688, _mut29689, _mut29690, _mut29691); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29649, _mut29650, _mut29651, _mut29652);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29653, _mut29654, _mut29655, _mut29656), rows);
            final int iHeight = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29657, _mut29658, _mut29659, _mut29660);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29682, _mut29683, _mut29684, _mut29685, _mut29686); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29661, _mut29662, _mut29663, _mut29664);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29665, _mut29666, _mut29667, _mut29668), columns);
                final int jWidth = AOR_minus(qEnd, qStart, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29669, _mut29670, _mut29671, _mut29672);
                // allocate new block
                final double[] block = new double[AOR_multiply(iHeight, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29673, _mut29674, _mut29675, _mut29676)];
                blocks[blockIndex] = block;
                // copy data
                int index = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200", _mut29677, _mut29678, _mut29679, _mut29680, _mut29681); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.toBlocksLayout_200");
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
     * #BlockRealMatrix(int, int, double[][], boolean)} constructor.
     * </p>
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @return a new data array in blocks layout.
     * @see #toBlocksLayout(double[][])
     * @see #BlockRealMatrix(int, int, double[][], boolean)
     */
    public static double[][] createBlocksLayout(final int rows, final int columns) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256");
        final int blockRows = AOR_divide((AOR_minus(AOR_plus(rows, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29692, _mut29693, _mut29694, _mut29695), 1, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29696, _mut29697, _mut29698, _mut29699)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29700, _mut29701, _mut29702, _mut29703);
        final int blockColumns = AOR_divide((AOR_minus(AOR_plus(columns, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29704, _mut29705, _mut29706, _mut29707), 1, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29708, _mut29709, _mut29710, _mut29711)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29712, _mut29713, _mut29714, _mut29715);
        final double[][] blocks = new double[AOR_multiply(blockRows, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29716, _mut29717, _mut29718, _mut29719)][];
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29753, _mut29754, _mut29755, _mut29756, _mut29757); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29720, _mut29721, _mut29722, _mut29723);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29724, _mut29725, _mut29726, _mut29727), rows);
            final int iHeight = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29728, _mut29729, _mut29730, _mut29731);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29748, _mut29749, _mut29750, _mut29751, _mut29752); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29732, _mut29733, _mut29734, _mut29735);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29736, _mut29737, _mut29738, _mut29739), columns);
                final int jWidth = AOR_minus(qEnd, qStart, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29740, _mut29741, _mut29742, _mut29743);
                blocks[blockIndex] = new double[AOR_multiply(iHeight, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.createBlocksLayout_256", _mut29744, _mut29745, _mut29746, _mut29747)];
                ++blockIndex;
            }
        }
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        return new BlockRealMatrix(rowDimension, columnDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix copy() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.copy_287");
        // create an empty matrix
        BlockRealMatrix copied = new BlockRealMatrix(rows, columns);
        // copy the blocks
        for (int i = 0; ROR_less(i, blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.copy_287", _mut29758, _mut29759, _mut29760, _mut29761, _mut29762); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.copy_287");
            System.arraycopy(blocks[i], 0, copied.blocks[i], 0, blocks[i].length);
        }
        return copied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix add(final RealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_301");
        try {
            return add((BlockRealMatrix) m);
        } catch (ClassCastException cce) {
            // safety check
            MatrixUtils.checkAdditionCompatible(this, m);
            final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
            // perform addition block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29798, _mut29799, _mut29800, _mut29801, _mut29802); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_301");
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29793, _mut29794, _mut29795, _mut29796, _mut29797); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_301");
                    // perform addition on the current block
                    final double[] outBlock = out.blocks[blockIndex];
                    final double[] tBlock = blocks[blockIndex];
                    final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29763, _mut29764, _mut29765, _mut29766);
                    final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29767, _mut29768, _mut29769, _mut29770), rows);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29771, _mut29772, _mut29773, _mut29774);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29775, _mut29776, _mut29777, _mut29778), columns);
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29788, _mut29789, _mut29790, _mut29791, _mut29792); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_301");
                        for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29783, _mut29784, _mut29785, _mut29786, _mut29787); ++q) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_301");
                            outBlock[k] = AOR_plus(tBlock[k], m.getEntry(p, q), "org.apache.commons.math3.linear.BlockRealMatrix.add_301", _mut29779, _mut29780, _mut29781, _mut29782);
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
     * Compute the sum of this matrix and {@code m}.
     *
     * @param m Matrix to be added.
     * @return {@code this} + m.
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as this matrix.
     */
    public BlockRealMatrix add(final BlockRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_348");
        // safety check
        MatrixUtils.checkAdditionCompatible(this, m);
        final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
        // perform addition block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.add_348", _mut29812, _mut29813, _mut29814, _mut29815, _mut29816); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_348");
            final double[] outBlock = out.blocks[blockIndex];
            final double[] tBlock = blocks[blockIndex];
            final double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.add_348", _mut29807, _mut29808, _mut29809, _mut29810, _mut29811); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.add_348");
                outBlock[k] = AOR_plus(tBlock[k], mBlock[k], "org.apache.commons.math3.linear.BlockRealMatrix.add_348", _mut29803, _mut29804, _mut29805, _mut29806);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix subtract(final RealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_369");
        try {
            return subtract((BlockRealMatrix) m);
        } catch (ClassCastException cce) {
            // safety check
            MatrixUtils.checkSubtractionCompatible(this, m);
            final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
            // perform subtraction block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29852, _mut29853, _mut29854, _mut29855, _mut29856); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_369");
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29847, _mut29848, _mut29849, _mut29850, _mut29851); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_369");
                    // perform subtraction on the current block
                    final double[] outBlock = out.blocks[blockIndex];
                    final double[] tBlock = blocks[blockIndex];
                    final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29817, _mut29818, _mut29819, _mut29820);
                    final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29821, _mut29822, _mut29823, _mut29824), rows);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29825, _mut29826, _mut29827, _mut29828);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29829, _mut29830, _mut29831, _mut29832), columns);
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29842, _mut29843, _mut29844, _mut29845, _mut29846); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_369");
                        for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29837, _mut29838, _mut29839, _mut29840, _mut29841); ++q) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_369");
                            outBlock[k] = AOR_minus(tBlock[k], m.getEntry(p, q), "org.apache.commons.math3.linear.BlockRealMatrix.subtract_369", _mut29833, _mut29834, _mut29835, _mut29836);
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
     * Subtract {@code m} from this matrix.
     *
     * @param m Matrix to be subtracted.
     * @return {@code this} - m.
     * @throws MatrixDimensionMismatchException if {@code m} is not the
     * same size as this matrix.
     */
    public BlockRealMatrix subtract(final BlockRealMatrix m) throws MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_416");
        // safety check
        MatrixUtils.checkSubtractionCompatible(this, m);
        final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_416", _mut29866, _mut29867, _mut29868, _mut29869, _mut29870); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_416");
            final double[] outBlock = out.blocks[blockIndex];
            final double[] tBlock = blocks[blockIndex];
            final double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.subtract_416", _mut29861, _mut29862, _mut29863, _mut29864, _mut29865); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.subtract_416");
                outBlock[k] = AOR_minus(tBlock[k], mBlock[k], "org.apache.commons.math3.linear.BlockRealMatrix.subtract_416", _mut29857, _mut29858, _mut29859, _mut29860);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix scalarAdd(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437");
        final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437", _mut29880, _mut29881, _mut29882, _mut29883, _mut29884); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437");
            final double[] outBlock = out.blocks[blockIndex];
            final double[] tBlock = blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437", _mut29875, _mut29876, _mut29877, _mut29878, _mut29879); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437");
                outBlock[k] = AOR_plus(tBlock[k], d, "org.apache.commons.math3.linear.BlockRealMatrix.scalarAdd_437", _mut29871, _mut29872, _mut29873, _mut29874);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealMatrix scalarMultiply(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455");
        final BlockRealMatrix out = new BlockRealMatrix(rows, columns);
        // perform subtraction block-wise, to ensure good cache behavior
        for (int blockIndex = 0; ROR_less(blockIndex, out.blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455", _mut29894, _mut29895, _mut29896, _mut29897, _mut29898); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455");
            final double[] outBlock = out.blocks[blockIndex];
            final double[] tBlock = blocks[blockIndex];
            for (int k = 0; ROR_less(k, outBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455", _mut29889, _mut29890, _mut29891, _mut29892, _mut29893); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455");
                outBlock[k] = AOR_multiply(tBlock[k], d, "org.apache.commons.math3.linear.BlockRealMatrix.scalarMultiply_455", _mut29885, _mut29886, _mut29887, _mut29888);
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix multiply(final RealMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
        try {
            return multiply((BlockRealMatrix) m);
        } catch (ClassCastException cce) {
            // safety check
            MatrixUtils.checkMultiplicationCompatible(this, m);
            final BlockRealMatrix out = new BlockRealMatrix(rows, m.getColumnDimension());
            // perform multiplication block-wise, to ensure good cache behavior
            int blockIndex = 0;
            for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29968, _mut29969, _mut29970, _mut29971, _mut29972); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29899, _mut29900, _mut29901, _mut29902);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29903, _mut29904, _mut29905, _mut29906), rows);
                for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29963, _mut29964, _mut29965, _mut29966, _mut29967); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29907, _mut29908, _mut29909, _mut29910);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29911, _mut29912, _mut29913, _mut29914), m.getColumnDimension());
                    // select current block
                    final double[] outBlock = out.blocks[blockIndex];
                    // perform multiplication on current block
                    for (int kBlock = 0; ROR_less(kBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29958, _mut29959, _mut29960, _mut29961, _mut29962); ++kBlock) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                        final int kWidth = blockWidth(kBlock);
                        final double[] tBlock = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29915, _mut29916, _mut29917, _mut29918), kBlock, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29919, _mut29920, _mut29921, _mut29922)];
                        final int rStart = AOR_multiply(kBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29923, _mut29924, _mut29925, _mut29926);
                        int k = 0;
                        for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29953, _mut29954, _mut29955, _mut29956, _mut29957); ++p) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                            final int lStart = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29927, _mut29928, _mut29929, _mut29930)), kWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29931, _mut29932, _mut29933, _mut29934);
                            final int lEnd = AOR_plus(lStart, kWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29935, _mut29936, _mut29937, _mut29938);
                            for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29948, _mut29949, _mut29950, _mut29951, _mut29952); ++q) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                                double sum = 0;
                                int r = rStart;
                                for (int l = lStart; ROR_less(l, lEnd, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29943, _mut29944, _mut29945, _mut29946, _mut29947); ++l) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_472");
                                    sum += AOR_multiply(tBlock[l], m.getEntry(r, q), "org.apache.commons.math3.linear.BlockRealMatrix.multiply_472", _mut29939, _mut29940, _mut29941, _mut29942);
                                    ++r;
                                }
                                outBlock[k] += sum;
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
     * Returns the result of postmultiplying this by {@code m}.
     *
     * @param m Matrix to postmultiply by.
     * @return {@code this} * m.
     * @throws DimensionMismatchException if the matrices are not compatible.
     */
    public BlockRealMatrix multiply(BlockRealMatrix m) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
        // safety check
        MatrixUtils.checkMultiplicationCompatible(this, m);
        final BlockRealMatrix out = new BlockRealMatrix(rows, m.columns);
        // perform multiplication block-wise, to ensure good cache behavior
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30111, _mut30112, _mut30113, _mut30114, _mut30115); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29973, _mut29974, _mut29975, _mut29976);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29977, _mut29978, _mut29979, _mut29980), rows);
            for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30106, _mut30107, _mut30108, _mut30109, _mut30110); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                final int jWidth = out.blockWidth(jBlock);
                final int jWidth2 = AOR_plus(jWidth, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29981, _mut29982, _mut29983, _mut29984);
                final int jWidth3 = AOR_plus(jWidth2, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29985, _mut29986, _mut29987, _mut29988);
                final int jWidth4 = AOR_plus(jWidth3, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29989, _mut29990, _mut29991, _mut29992);
                // select current block
                final double[] outBlock = out.blocks[blockIndex];
                // perform multiplication on current block
                for (int kBlock = 0; ROR_less(kBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30101, _mut30102, _mut30103, _mut30104, _mut30105); ++kBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                    final int kWidth = blockWidth(kBlock);
                    final double[] tBlock = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29993, _mut29994, _mut29995, _mut29996), kBlock, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut29997, _mut29998, _mut29999, _mut30000)];
                    final double[] mBlock = m.blocks[AOR_plus(AOR_multiply(kBlock, m.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30001, _mut30002, _mut30003, _mut30004), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30005, _mut30006, _mut30007, _mut30008)];
                    int k = 0;
                    for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30096, _mut30097, _mut30098, _mut30099, _mut30100); ++p) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                        final int lStart = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30009, _mut30010, _mut30011, _mut30012)), kWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30013, _mut30014, _mut30015, _mut30016);
                        final int lEnd = AOR_plus(lStart, kWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30017, _mut30018, _mut30019, _mut30020);
                        for (int nStart = 0; ROR_less(nStart, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30091, _mut30092, _mut30093, _mut30094, _mut30095); ++nStart) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                            double sum = 0;
                            int l = lStart;
                            int n = nStart;
                            while (ROR_less(l, AOR_minus(lEnd, 3, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30073, _mut30074, _mut30075, _mut30076), "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30077, _mut30078, _mut30079, _mut30080, _mut30081)) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                                sum += AOR_plus(AOR_plus(AOR_plus(AOR_multiply(tBlock[l], mBlock[n], "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30021, _mut30022, _mut30023, _mut30024), AOR_multiply(tBlock[AOR_plus(l, 1, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30025, _mut30026, _mut30027, _mut30028)], mBlock[AOR_plus(n, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30029, _mut30030, _mut30031, _mut30032)], "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30033, _mut30034, _mut30035, _mut30036), "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30037, _mut30038, _mut30039, _mut30040), AOR_multiply(tBlock[AOR_plus(l, 2, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30041, _mut30042, _mut30043, _mut30044)], mBlock[AOR_plus(n, jWidth2, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30045, _mut30046, _mut30047, _mut30048)], "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30049, _mut30050, _mut30051, _mut30052), "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30053, _mut30054, _mut30055, _mut30056), AOR_multiply(tBlock[AOR_plus(l, 3, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30057, _mut30058, _mut30059, _mut30060)], mBlock[AOR_plus(n, jWidth3, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30061, _mut30062, _mut30063, _mut30064)], "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30065, _mut30066, _mut30067, _mut30068), "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30069, _mut30070, _mut30071, _mut30072);
                                l += 4;
                                n += jWidth4;
                            }
                            while (ROR_less(l, lEnd, "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30086, _mut30087, _mut30088, _mut30089, _mut30090)) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiply_533");
                                sum += AOR_multiply(tBlock[l++], mBlock[n], "org.apache.commons.math3.linear.BlockRealMatrix.multiply_533", _mut30082, _mut30083, _mut30084, _mut30085);
                                n += jWidth;
                            }
                            outBlock[k] += sum;
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
    public double[][] getData() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getData_595");
        final double[][] data = new double[getRowDimension()][getColumnDimension()];
        final int lastColumns = AOR_minus(columns, AOR_multiply((AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30116, _mut30117, _mut30118, _mut30119)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30120, _mut30121, _mut30122, _mut30123), "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30124, _mut30125, _mut30126, _mut30127);
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30154, _mut30155, _mut30156, _mut30157, _mut30158); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getData_595");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30128, _mut30129, _mut30130, _mut30131);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30132, _mut30133, _mut30134, _mut30135), rows);
            int regularPos = 0;
            int lastPos = 0;
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30149, _mut30150, _mut30151, _mut30152, _mut30153); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getData_595");
                final double[] dataP = data[p];
                int blockIndex = AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30136, _mut30137, _mut30138, _mut30139);
                int dataPos = 0;
                for (int jBlock = 0; ROR_less(jBlock, AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30140, _mut30141, _mut30142, _mut30143), "org.apache.commons.math3.linear.BlockRealMatrix.getData_595", _mut30144, _mut30145, _mut30146, _mut30147, _mut30148); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getData_595");
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
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
        final double[] colSums = new double[BLOCK_SIZE];
        double maxColSum = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30195, _mut30196, _mut30197, _mut30198, _mut30199); jBlock++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
            final int jWidth = blockWidth(jBlock);
            Arrays.fill(colSums, 0, jWidth, 0.0);
            for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30185, _mut30186, _mut30187, _mut30188, _mut30189); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
                final int iHeight = blockHeight(iBlock);
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30159, _mut30160, _mut30161, _mut30162), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30163, _mut30164, _mut30165, _mut30166)];
                for (int j = 0; ROR_less(j, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30180, _mut30181, _mut30182, _mut30183, _mut30184); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
                    double sum = 0;
                    for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30175, _mut30176, _mut30177, _mut30178, _mut30179); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
                        sum += FastMath.abs(block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30167, _mut30168, _mut30169, _mut30170), j, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30171, _mut30172, _mut30173, _mut30174)]);
                    }
                    colSums[j] += sum;
                }
            }
            for (int j = 0; ROR_less(j, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623", _mut30190, _mut30191, _mut30192, _mut30193, _mut30194); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getNorm_623");
                maxColSum = FastMath.max(maxColSum, colSums[j]);
            }
        }
        return maxColSum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getFrobeniusNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getFrobeniusNorm_649");
        double sum2 = 0;
        for (int blockIndex = 0; ROR_less(blockIndex, blocks.length, "org.apache.commons.math3.linear.BlockRealMatrix.getFrobeniusNorm_649", _mut30204, _mut30205, _mut30206, _mut30207, _mut30208); ++blockIndex) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getFrobeniusNorm_649");
            for (final double entry : blocks[blockIndex]) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getFrobeniusNorm_649");
                sum2 += AOR_multiply(entry, entry, "org.apache.commons.math3.linear.BlockRealMatrix.getFrobeniusNorm_649", _mut30200, _mut30201, _mut30202, _mut30203);
            }
        }
        return FastMath.sqrt(sum2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661");
        // safety checks
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        // create the output matrix
        final BlockRealMatrix out = new BlockRealMatrix(AOR_plus(AOR_minus(endRow, startRow, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30209, _mut30210, _mut30211, _mut30212), 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30213, _mut30214, _mut30215, _mut30216), AOR_plus(AOR_minus(endColumn, startColumn, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30217, _mut30218, _mut30219, _mut30220), 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30221, _mut30222, _mut30223, _mut30224));
        // compute blocks shifts
        final int blockStartRow = AOR_divide(startRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30225, _mut30226, _mut30227, _mut30228);
        final int rowsShift = AOR_remainder(startRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30229, _mut30230, _mut30231, _mut30232);
        final int blockStartColumn = AOR_divide(startColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30233, _mut30234, _mut30235, _mut30236);
        final int columnsShift = AOR_remainder(startColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30237, _mut30238, _mut30239, _mut30240);
        // perform extraction block-wise, to ensure good cache behavior
        int pBlock = blockStartRow;
        for (int iBlock = 0; ROR_less(iBlock, out.blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30373, _mut30374, _mut30375, _mut30376, _mut30377); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661");
            final int iHeight = out.blockHeight(iBlock);
            int qBlock = blockStartColumn;
            for (int jBlock = 0; ROR_less(jBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30368, _mut30369, _mut30370, _mut30371, _mut30372); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661");
                final int jWidth = out.blockWidth(jBlock);
                // handle one block of the output matrix
                final int outIndex = AOR_plus(AOR_multiply(iBlock, out.blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30241, _mut30242, _mut30243, _mut30244), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30245, _mut30246, _mut30247, _mut30248);
                final double[] outBlock = out.blocks[outIndex];
                final int index = AOR_plus(AOR_multiply(pBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30249, _mut30250, _mut30251, _mut30252), qBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30253, _mut30254, _mut30255, _mut30256);
                final int width = blockWidth(qBlock);
                final int heightExcess = AOR_minus(AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30257, _mut30258, _mut30259, _mut30260), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30261, _mut30262, _mut30263, _mut30264);
                final int widthExcess = AOR_minus(AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30265, _mut30266, _mut30267, _mut30268), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30269, _mut30270, _mut30271, _mut30272);
                if (ROR_greater(heightExcess, 0, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30273, _mut30274, _mut30275, _mut30276, _mut30277)) {
                    // the submatrix block spans on two blocks rows from the original matrix
                    if (ROR_greater(widthExcess, 0, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30311, _mut30312, _mut30313, _mut30314, _mut30315)) {
                        // the submatrix block spans on two blocks columns from the original matrix
                        final int width2 = blockWidth(AOR_plus(qBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30332, _mut30333, _mut30334, _mut30335));
                        copyBlockPart(blocks[index], width, rowsShift, BLOCK_SIZE, columnsShift, BLOCK_SIZE, outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30336, _mut30337, _mut30338, _mut30339)], width2, rowsShift, BLOCK_SIZE, 0, widthExcess, outBlock, jWidth, 0, AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30340, _mut30341, _mut30342, _mut30343));
                        copyBlockPart(blocks[AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30344, _mut30345, _mut30346, _mut30347)], width, 0, heightExcess, columnsShift, BLOCK_SIZE, outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30348, _mut30349, _mut30350, _mut30351), 0);
                        copyBlockPart(blocks[AOR_plus(AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30352, _mut30353, _mut30354, _mut30355), 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30356, _mut30357, _mut30358, _mut30359)], width2, 0, heightExcess, 0, widthExcess, outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30360, _mut30361, _mut30362, _mut30363), AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30364, _mut30365, _mut30366, _mut30367));
                    } else {
                        // the submatrix block spans on one block column from the original matrix
                        copyBlockPart(blocks[index], width, rowsShift, BLOCK_SIZE, columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30316, _mut30317, _mut30318, _mut30319), outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30320, _mut30321, _mut30322, _mut30323)], width, 0, heightExcess, columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30324, _mut30325, _mut30326, _mut30327), outBlock, jWidth, AOR_minus(iHeight, heightExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30328, _mut30329, _mut30330, _mut30331), 0);
                    }
                } else {
                    // the submatrix block spans on one block row from the original matrix
                    if (ROR_greater(widthExcess, 0, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30278, _mut30279, _mut30280, _mut30281, _mut30282)) {
                        // the submatrix block spans on two blocks columns from the original matrix
                        final int width2 = blockWidth(AOR_plus(qBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30291, _mut30292, _mut30293, _mut30294));
                        copyBlockPart(blocks[index], width, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30295, _mut30296, _mut30297, _mut30298), columnsShift, BLOCK_SIZE, outBlock, jWidth, 0, 0);
                        copyBlockPart(blocks[AOR_plus(index, 1, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30299, _mut30300, _mut30301, _mut30302)], width2, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30303, _mut30304, _mut30305, _mut30306), 0, widthExcess, outBlock, jWidth, 0, AOR_minus(jWidth, widthExcess, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30307, _mut30308, _mut30309, _mut30310));
                    } else {
                        // the submatrix block spans on one block column from the original matrix
                        copyBlockPart(blocks[index], width, rowsShift, AOR_plus(iHeight, rowsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30283, _mut30284, _mut30285, _mut30286), columnsShift, AOR_plus(jWidth, columnsShift, "org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix_661", _mut30287, _mut30288, _mut30289, _mut30290), outBlock, jWidth, 0, 0);
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
    private void copyBlockPart(final double[] srcBlock, final int srcWidth, final int srcStartRow, final int srcEndRow, final int srcStartColumn, final int srcEndColumn, final double[] dstBlock, final int dstWidth, final int dstStartRow, final int dstStartColumn) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771");
        final int length = AOR_minus(srcEndColumn, srcStartColumn, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30378, _mut30379, _mut30380, _mut30381);
        int srcPos = AOR_plus(AOR_multiply(srcStartRow, srcWidth, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30382, _mut30383, _mut30384, _mut30385), srcStartColumn, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30386, _mut30387, _mut30388, _mut30389);
        int dstPos = AOR_plus(AOR_multiply(dstStartRow, dstWidth, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30390, _mut30391, _mut30392, _mut30393), dstStartColumn, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30394, _mut30395, _mut30396, _mut30397);
        for (int srcRow = srcStartRow; ROR_less(srcRow, srcEndRow, "org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771", _mut30398, _mut30399, _mut30400, _mut30401, _mut30402); ++srcRow) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.copyBlockPart_771");
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubMatrix(final double[][] subMatrix, final int row, final int column) throws OutOfRangeException, NoDataException, NullArgumentException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787");
        // safety checks
        MathUtils.checkNotNull(subMatrix);
        final int refLength = subMatrix[0].length;
        if (ROR_equals(refLength, 0, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30403, _mut30404, _mut30405, _mut30406, _mut30407)) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        final int endRow = AOR_minus(AOR_plus(row, subMatrix.length, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30408, _mut30409, _mut30410, _mut30411), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30412, _mut30413, _mut30414, _mut30415);
        final int endColumn = AOR_minus(AOR_plus(column, refLength, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30416, _mut30417, _mut30418, _mut30419), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30420, _mut30421, _mut30422, _mut30423);
        MatrixUtils.checkSubMatrixIndex(this, row, endRow, column, endColumn);
        for (final double[] subRow : subMatrix) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787");
            if (ROR_not_equals(subRow.length, refLength, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30424, _mut30425, _mut30426, _mut30427, _mut30428)) {
                throw new DimensionMismatchException(refLength, subRow.length);
            }
        }
        // compute blocks bounds
        final int blockStartRow = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30429, _mut30430, _mut30431, _mut30432);
        final int blockEndRow = AOR_divide((AOR_plus(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30433, _mut30434, _mut30435, _mut30436)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30437, _mut30438, _mut30439, _mut30440);
        final int blockStartColumn = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30441, _mut30442, _mut30443, _mut30444);
        final int blockEndColumn = AOR_divide((AOR_plus(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30445, _mut30446, _mut30447, _mut30448)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30449, _mut30450, _mut30451, _mut30452);
        // perform copy block-wise, to ensure good cache behavior
        for (int iBlock = blockStartRow; ROR_less(iBlock, blockEndRow, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30523, _mut30524, _mut30525, _mut30526, _mut30527); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787");
            final int iHeight = blockHeight(iBlock);
            final int firstRow = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30453, _mut30454, _mut30455, _mut30456);
            final int iStart = FastMath.max(row, firstRow);
            final int iEnd = FastMath.min(AOR_plus(endRow, 1, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30457, _mut30458, _mut30459, _mut30460), AOR_plus(firstRow, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30461, _mut30462, _mut30463, _mut30464));
            for (int jBlock = blockStartColumn; ROR_less(jBlock, blockEndColumn, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30518, _mut30519, _mut30520, _mut30521, _mut30522); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787");
                final int jWidth = blockWidth(jBlock);
                final int firstColumn = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30465, _mut30466, _mut30467, _mut30468);
                final int jStart = FastMath.max(column, firstColumn);
                final int jEnd = FastMath.min(AOR_plus(endColumn, 1, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30469, _mut30470, _mut30471, _mut30472), AOR_plus(firstColumn, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30473, _mut30474, _mut30475, _mut30476));
                final int jLength = AOR_minus(jEnd, jStart, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30477, _mut30478, _mut30479, _mut30480);
                // handle one block, row by row
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30481, _mut30482, _mut30483, _mut30484), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30485, _mut30486, _mut30487, _mut30488)];
                for (int i = iStart; ROR_less(i, iEnd, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30513, _mut30514, _mut30515, _mut30516, _mut30517); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787");
                    System.arraycopy(subMatrix[AOR_minus(i, row, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30489, _mut30490, _mut30491, _mut30492)], AOR_minus(jStart, column, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30493, _mut30494, _mut30495, _mut30496), block, AOR_plus(AOR_multiply((AOR_minus(i, firstRow, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30497, _mut30498, _mut30499, _mut30500)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30501, _mut30502, _mut30503, _mut30504), (AOR_minus(jStart, firstColumn, "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30505, _mut30506, _mut30507, _mut30508)), "org.apache.commons.math3.linear.BlockRealMatrix.setSubMatrix_787", _mut30509, _mut30510, _mut30511, _mut30512), jLength);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix getRowMatrix(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840");
        MatrixUtils.checkRowIndex(this, row);
        final BlockRealMatrix out = new BlockRealMatrix(1, columns);
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30528, _mut30529, _mut30530, _mut30531);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30532, _mut30533, _mut30534, _mut30535), "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30536, _mut30537, _mut30538, _mut30539);
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[outBlockIndex];
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30577, _mut30578, _mut30579, _mut30580, _mut30581); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840");
            final int jWidth = blockWidth(jBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30540, _mut30541, _mut30542, _mut30543), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30544, _mut30545, _mut30546, _mut30547)];
            final int available = AOR_minus(outBlock.length, outIndex, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30548, _mut30549, _mut30550, _mut30551);
            if (ROR_greater(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30552, _mut30553, _mut30554, _mut30555, _mut30556)) {
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30561, _mut30562, _mut30563, _mut30564), outBlock, outIndex, available);
                outBlock = out.blocks[++outBlockIndex];
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30565, _mut30566, _mut30567, _mut30568), outBlock, 0, AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30569, _mut30570, _mut30571, _mut30572));
                outIndex = AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30573, _mut30574, _mut30575, _mut30576);
            } else {
                System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getRowMatrix_840", _mut30557, _mut30558, _mut30559, _mut30560), outBlock, outIndex, jWidth);
                outIndex += jWidth;
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowMatrix(final int row, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRowMatrix(row, (BlockRealMatrix) matrix);
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
     * @throws OutOfRangeException if the specified row index is invalid.
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance row.
     */
    public void setRowMatrix(final int row, final BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        if ((_mut30592 ? ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30582, _mut30583, _mut30584, _mut30585, _mut30586)) && (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30587, _mut30588, _mut30589, _mut30590, _mut30591))) : ((ROR_not_equals(matrix.getRowDimension(), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30582, _mut30583, _mut30584, _mut30585, _mut30586)) || (ROR_not_equals(matrix.getColumnDimension(), nCols, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30587, _mut30588, _mut30589, _mut30590, _mut30591))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30593, _mut30594, _mut30595, _mut30596);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30597, _mut30598, _mut30599, _mut30600), "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30601, _mut30602, _mut30603, _mut30604);
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[mBlockIndex];
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30642, _mut30643, _mut30644, _mut30645, _mut30646); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892");
            final int jWidth = blockWidth(jBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30605, _mut30606, _mut30607, _mut30608), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30609, _mut30610, _mut30611, _mut30612)];
            final int available = AOR_minus(mBlock.length, mIndex, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30613, _mut30614, _mut30615, _mut30616);
            if (ROR_greater(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30617, _mut30618, _mut30619, _mut30620, _mut30621)) {
                System.arraycopy(mBlock, mIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30626, _mut30627, _mut30628, _mut30629), available);
                mBlock = matrix.blocks[++mBlockIndex];
                System.arraycopy(mBlock, 0, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30630, _mut30631, _mut30632, _mut30633), AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30634, _mut30635, _mut30636, _mut30637));
                mIndex = AOR_minus(jWidth, available, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30638, _mut30639, _mut30640, _mut30641);
            } else {
                System.arraycopy(mBlock, mIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setRowMatrix_892", _mut30622, _mut30623, _mut30624, _mut30625), jWidth);
                mIndex += jWidth;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix getColumnMatrix(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926");
        MatrixUtils.checkColumnIndex(this, column);
        final BlockRealMatrix out = new BlockRealMatrix(rows, 1);
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30647, _mut30648, _mut30649, _mut30650);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30651, _mut30652, _mut30653, _mut30654), "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30655, _mut30656, _mut30657, _mut30658);
        final int jWidth = blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[outBlockIndex];
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30685, _mut30686, _mut30687, _mut30688, _mut30689); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926");
            final int iHeight = blockHeight(iBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30659, _mut30660, _mut30661, _mut30662), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30663, _mut30664, _mut30665, _mut30666)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30680, _mut30681, _mut30682, _mut30683, _mut30684); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926");
                if (ROR_greater_equals(outIndex, outBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30667, _mut30668, _mut30669, _mut30670, _mut30671)) {
                    outBlock = out.blocks[++outBlockIndex];
                    outIndex = 0;
                }
                outBlock[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30672, _mut30673, _mut30674, _mut30675), jColumn, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnMatrix_926", _mut30676, _mut30677, _mut30678, _mut30679)];
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnMatrix(final int column, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumnMatrix(column, (BlockRealMatrix) matrix);
        } catch (ClassCastException cce) {
            super.setColumnMatrix(column, matrix);
        }
    }

    /**
     * Sets the entries in column number <code>column</code>
     * as a column matrix.  Column indices start at 0.
     *
     * @param column the column to be set
     * @param matrix column matrix (must have one column and the same number of rows
     * as the instance)
     * @throws OutOfRangeException if the specified column index is invalid.
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance column.
     */
    void setColumnMatrix(final int column, final BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        if ((_mut30700 ? ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30690, _mut30691, _mut30692, _mut30693, _mut30694)) && (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30695, _mut30696, _mut30697, _mut30698, _mut30699))) : ((ROR_not_equals(matrix.getRowDimension(), nRows, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30690, _mut30691, _mut30692, _mut30693, _mut30694)) || (ROR_not_equals(matrix.getColumnDimension(), 1, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30695, _mut30696, _mut30697, _mut30698, _mut30699))))) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30701, _mut30702, _mut30703, _mut30704);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30705, _mut30706, _mut30707, _mut30708), "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30709, _mut30710, _mut30711, _mut30712);
        final int jWidth = blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[mBlockIndex];
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30739, _mut30740, _mut30741, _mut30742, _mut30743); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976");
            final int iHeight = blockHeight(iBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30713, _mut30714, _mut30715, _mut30716), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30717, _mut30718, _mut30719, _mut30720)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30734, _mut30735, _mut30736, _mut30737, _mut30738); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976");
                if (ROR_greater_equals(mIndex, mBlock.length, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30721, _mut30722, _mut30723, _mut30724, _mut30725)) {
                    mBlock = matrix.blocks[++mBlockIndex];
                    mIndex = 0;
                }
                block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30726, _mut30727, _mut30728, _mut30729), jColumn, "org.apache.commons.math3.linear.BlockRealMatrix.setColumnMatrix_976", _mut30730, _mut30731, _mut30732, _mut30733)] = mBlock[mIndex++];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector getRowVector(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008");
        MatrixUtils.checkRowIndex(this, row);
        final double[] outData = new double[columns];
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30744, _mut30745, _mut30746, _mut30747);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30748, _mut30749, _mut30750, _mut30751), "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30752, _mut30753, _mut30754, _mut30755);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30768, _mut30769, _mut30770, _mut30771, _mut30772); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008");
            final int jWidth = blockWidth(jBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30756, _mut30757, _mut30758, _mut30759), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30760, _mut30761, _mut30762, _mut30763)];
            System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getRowVector_1008", _mut30764, _mut30765, _mut30766, _mut30767), outData, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayRealVector(outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowVector(final int row, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRow(row, ((ArrayRealVector) vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setRowVector(row, vector);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector getColumnVector(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040");
        MatrixUtils.checkColumnIndex(this, column);
        final double[] outData = new double[rows];
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30773, _mut30774, _mut30775, _mut30776);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30777, _mut30778, _mut30779, _mut30780), "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30781, _mut30782, _mut30783, _mut30784);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30806, _mut30807, _mut30808, _mut30809, _mut30810); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040");
            final int iHeight = blockHeight(iBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30785, _mut30786, _mut30787, _mut30788), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30789, _mut30790, _mut30791, _mut30792)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30801, _mut30802, _mut30803, _mut30804, _mut30805); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040");
                outData[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30793, _mut30794, _mut30795, _mut30796), jColumn, "org.apache.commons.math3.linear.BlockRealMatrix.getColumnVector_1040", _mut30797, _mut30798, _mut30799, _mut30800)];
            }
        }
        return new ArrayRealVector(outData, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnVector(final int column, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(column, ((ArrayRealVector) vector).getDataRef());
        } catch (ClassCastException cce) {
            super.setColumnVector(column, vector);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getRow(final int row) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074");
        MatrixUtils.checkRowIndex(this, row);
        final double[] out = new double[columns];
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30811, _mut30812, _mut30813, _mut30814);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30815, _mut30816, _mut30817, _mut30818), "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30819, _mut30820, _mut30821, _mut30822);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30835, _mut30836, _mut30837, _mut30838, _mut30839); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074");
            final int jWidth = blockWidth(jBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30823, _mut30824, _mut30825, _mut30826), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30827, _mut30828, _mut30829, _mut30830)];
            System.arraycopy(block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getRow_1074", _mut30831, _mut30832, _mut30833, _mut30834), out, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRow(final int row, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094");
        MatrixUtils.checkRowIndex(this, row);
        final int nCols = getColumnDimension();
        if (ROR_not_equals(array.length, nCols, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30840, _mut30841, _mut30842, _mut30843, _mut30844)) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30845, _mut30846, _mut30847, _mut30848);
        final int iRow = AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30849, _mut30850, _mut30851, _mut30852), "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30853, _mut30854, _mut30855, _mut30856);
        int outIndex = 0;
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30869, _mut30870, _mut30871, _mut30872, _mut30873); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094");
            final int jWidth = blockWidth(jBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30857, _mut30858, _mut30859, _mut30860), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30861, _mut30862, _mut30863, _mut30864)];
            System.arraycopy(array, outIndex, block, AOR_multiply(iRow, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setRow_1094", _mut30865, _mut30866, _mut30867, _mut30868), jWidth);
            outIndex += jWidth;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getColumn(final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116");
        MatrixUtils.checkColumnIndex(this, column);
        final double[] out = new double[rows];
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30874, _mut30875, _mut30876, _mut30877);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30878, _mut30879, _mut30880, _mut30881), "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30882, _mut30883, _mut30884, _mut30885);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30907, _mut30908, _mut30909, _mut30910, _mut30911); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116");
            final int iHeight = blockHeight(iBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30886, _mut30887, _mut30888, _mut30889), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30890, _mut30891, _mut30892, _mut30893)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30902, _mut30903, _mut30904, _mut30905, _mut30906); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116");
                out[outIndex++] = block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30894, _mut30895, _mut30896, _mut30897), jColumn, "org.apache.commons.math3.linear.BlockRealMatrix.getColumn_1116", _mut30898, _mut30899, _mut30900, _mut30901)];
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumn(final int column, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138");
        MatrixUtils.checkColumnIndex(this, column);
        final int nRows = getRowDimension();
        if (ROR_not_equals(array.length, nRows, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30912, _mut30913, _mut30914, _mut30915, _mut30916)) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        // perform copy block-wise, to ensure good cache behavior
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30917, _mut30918, _mut30919, _mut30920);
        final int jColumn = AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30921, _mut30922, _mut30923, _mut30924), "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30925, _mut30926, _mut30927, _mut30928);
        final int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30950, _mut30951, _mut30952, _mut30953, _mut30954); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138");
            final int iHeight = blockHeight(iBlock);
            final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30929, _mut30930, _mut30931, _mut30932), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30933, _mut30934, _mut30935, _mut30936)];
            for (int i = 0; ROR_less(i, iHeight, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30945, _mut30946, _mut30947, _mut30948, _mut30949); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138");
                block[AOR_plus(AOR_multiply(i, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30937, _mut30938, _mut30939, _mut30940), jColumn, "org.apache.commons.math3.linear.BlockRealMatrix.setColumn_1138", _mut30941, _mut30942, _mut30943, _mut30944)] = array[outIndex++];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEntry(final int row, final int column) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162");
        MatrixUtils.checkMatrixIndex(this, row, column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30955, _mut30956, _mut30957, _mut30958);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30959, _mut30960, _mut30961, _mut30962);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30963, _mut30964, _mut30965, _mut30966), "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30967, _mut30968, _mut30969, _mut30970)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30971, _mut30972, _mut30973, _mut30974), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30975, _mut30976, _mut30977, _mut30978), "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30979, _mut30980, _mut30981, _mut30982)), "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30983, _mut30984, _mut30985, _mut30986);
        return blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30987, _mut30988, _mut30989, _mut30990), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.getEntry_1162", _mut30991, _mut30992, _mut30993, _mut30994)][k];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(final int row, final int column, final double value) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174");
        MatrixUtils.checkMatrixIndex(this, row, column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut30995, _mut30996, _mut30997, _mut30998);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut30999, _mut31000, _mut31001, _mut31002);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31003, _mut31004, _mut31005, _mut31006), "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31007, _mut31008, _mut31009, _mut31010)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31011, _mut31012, _mut31013, _mut31014), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31015, _mut31016, _mut31017, _mut31018), "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31019, _mut31020, _mut31021, _mut31022)), "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31023, _mut31024, _mut31025, _mut31026);
        blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31027, _mut31028, _mut31029, _mut31030), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.setEntry_1174", _mut31031, _mut31032, _mut31033, _mut31034)][k] = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(final int row, final int column, final double increment) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186");
        MatrixUtils.checkMatrixIndex(this, row, column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31035, _mut31036, _mut31037, _mut31038);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31039, _mut31040, _mut31041, _mut31042);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31043, _mut31044, _mut31045, _mut31046), "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31047, _mut31048, _mut31049, _mut31050)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31051, _mut31052, _mut31053, _mut31054), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31055, _mut31056, _mut31057, _mut31058), "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31059, _mut31060, _mut31061, _mut31062)), "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31063, _mut31064, _mut31065, _mut31066);
        blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31067, _mut31068, _mut31069, _mut31070), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.addToEntry_1186", _mut31071, _mut31072, _mut31073, _mut31074)][k] += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final double factor) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199");
        MatrixUtils.checkMatrixIndex(this, row, column);
        final int iBlock = AOR_divide(row, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31075, _mut31076, _mut31077, _mut31078);
        final int jBlock = AOR_divide(column, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31079, _mut31080, _mut31081, _mut31082);
        final int k = AOR_plus(AOR_multiply((AOR_minus(row, AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31083, _mut31084, _mut31085, _mut31086), "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31087, _mut31088, _mut31089, _mut31090)), blockWidth(jBlock), "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31091, _mut31092, _mut31093, _mut31094), (AOR_minus(column, AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31095, _mut31096, _mut31097, _mut31098), "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31099, _mut31100, _mut31101, _mut31102)), "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31103, _mut31104, _mut31105, _mut31106);
        blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31107, _mut31108, _mut31109, _mut31110), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.multiplyEntry_1199", _mut31111, _mut31112, _mut31113, _mut31114)][k] *= factor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRealMatrix transpose() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212");
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final BlockRealMatrix out = new BlockRealMatrix(nCols, nRows);
        // perform transpose block-wise, to ensure good cache behavior
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31162, _mut31163, _mut31164, _mut31165, _mut31166); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212");
            for (int jBlock = 0; ROR_less(jBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31157, _mut31158, _mut31159, _mut31160, _mut31161); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212");
                // transpose current block
                final double[] outBlock = out.blocks[blockIndex];
                final double[] tBlock = blocks[AOR_plus(AOR_multiply(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31115, _mut31116, _mut31117, _mut31118), iBlock, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31119, _mut31120, _mut31121, _mut31122)];
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31123, _mut31124, _mut31125, _mut31126);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31127, _mut31128, _mut31129, _mut31130), columns);
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31131, _mut31132, _mut31133, _mut31134);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31135, _mut31136, _mut31137, _mut31138), rows);
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31152, _mut31153, _mut31154, _mut31155, _mut31156); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212");
                    final int lInc = AOR_minus(pEnd, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31139, _mut31140, _mut31141, _mut31142);
                    int l = AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31143, _mut31144, _mut31145, _mut31146);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212", _mut31147, _mut31148, _mut31149, _mut31150, _mut31151); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.transpose_1212");
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
    public double[] operate(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
        if (ROR_not_equals(v.length, columns, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31167, _mut31168, _mut31169, _mut31170, _mut31171)) {
            throw new DimensionMismatchException(v.length, columns);
        }
        final double[] out = new double[rows];
        // perform multiplication block-wise, to ensure good cache behavior
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31276, _mut31277, _mut31278, _mut31279, _mut31280); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31172, _mut31173, _mut31174, _mut31175);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31176, _mut31177, _mut31178, _mut31179), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31271, _mut31272, _mut31273, _mut31274, _mut31275); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31180, _mut31181, _mut31182, _mut31183), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31184, _mut31185, _mut31186, _mut31187)];
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31188, _mut31189, _mut31190, _mut31191);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31192, _mut31193, _mut31194, _mut31195), columns);
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31266, _mut31267, _mut31268, _mut31269, _mut31270); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
                    double sum = 0;
                    int q = qStart;
                    while (ROR_less(q, AOR_minus(qEnd, 3, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31248, _mut31249, _mut31250, _mut31251), "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31252, _mut31253, _mut31254, _mut31255, _mut31256)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
                        sum += AOR_plus(AOR_plus(AOR_plus(AOR_multiply(block[k], v[q], "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31196, _mut31197, _mut31198, _mut31199), AOR_multiply(block[AOR_plus(k, 1, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31200, _mut31201, _mut31202, _mut31203)], v[AOR_plus(q, 1, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31204, _mut31205, _mut31206, _mut31207)], "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31208, _mut31209, _mut31210, _mut31211), "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31212, _mut31213, _mut31214, _mut31215), AOR_multiply(block[AOR_plus(k, 2, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31216, _mut31217, _mut31218, _mut31219)], v[AOR_plus(q, 2, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31220, _mut31221, _mut31222, _mut31223)], "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31224, _mut31225, _mut31226, _mut31227), "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31228, _mut31229, _mut31230, _mut31231), AOR_multiply(block[AOR_plus(k, 3, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31232, _mut31233, _mut31234, _mut31235)], v[AOR_plus(q, 3, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31236, _mut31237, _mut31238, _mut31239)], "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31240, _mut31241, _mut31242, _mut31243), "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31244, _mut31245, _mut31246, _mut31247);
                        k += 4;
                        q += 4;
                    }
                    while (ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31261, _mut31262, _mut31263, _mut31264, _mut31265)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.operate_1260");
                        sum += AOR_multiply(block[k++], v[q++], "org.apache.commons.math3.linear.BlockRealMatrix.operate_1260", _mut31257, _mut31258, _mut31259, _mut31260);
                    }
                    out[p] += sum;
                }
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] preMultiply(final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
        if (ROR_not_equals(v.length, rows, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31281, _mut31282, _mut31283, _mut31284, _mut31285)) {
            throw new DimensionMismatchException(v.length, rows);
        }
        final double[] out = new double[columns];
        // perform multiplication block-wise, to ensure good cache behavior
        for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31406, _mut31407, _mut31408, _mut31409, _mut31410); ++jBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
            final int jWidth = blockWidth(jBlock);
            final int jWidth2 = AOR_plus(jWidth, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31286, _mut31287, _mut31288, _mut31289);
            final int jWidth3 = AOR_plus(jWidth2, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31290, _mut31291, _mut31292, _mut31293);
            final int jWidth4 = AOR_plus(jWidth3, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31294, _mut31295, _mut31296, _mut31297);
            final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31298, _mut31299, _mut31300, _mut31301);
            final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31302, _mut31303, _mut31304, _mut31305), columns);
            for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31401, _mut31402, _mut31403, _mut31404, _mut31405); ++iBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31306, _mut31307, _mut31308, _mut31309), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31310, _mut31311, _mut31312, _mut31313)];
                final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31314, _mut31315, _mut31316, _mut31317);
                final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31318, _mut31319, _mut31320, _mut31321), rows);
                for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31396, _mut31397, _mut31398, _mut31399, _mut31400); ++q) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
                    int k = AOR_minus(q, qStart, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31322, _mut31323, _mut31324, _mut31325);
                    double sum = 0;
                    int p = pStart;
                    while (ROR_less(p, AOR_minus(pEnd, 3, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31378, _mut31379, _mut31380, _mut31381), "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31382, _mut31383, _mut31384, _mut31385, _mut31386)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
                        sum += AOR_plus(AOR_plus(AOR_plus(AOR_multiply(block[k], v[p], "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31326, _mut31327, _mut31328, _mut31329), AOR_multiply(block[AOR_plus(k, jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31330, _mut31331, _mut31332, _mut31333)], v[AOR_plus(p, 1, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31334, _mut31335, _mut31336, _mut31337)], "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31338, _mut31339, _mut31340, _mut31341), "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31342, _mut31343, _mut31344, _mut31345), AOR_multiply(block[AOR_plus(k, jWidth2, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31346, _mut31347, _mut31348, _mut31349)], v[AOR_plus(p, 2, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31350, _mut31351, _mut31352, _mut31353)], "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31354, _mut31355, _mut31356, _mut31357), "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31358, _mut31359, _mut31360, _mut31361), AOR_multiply(block[AOR_plus(k, jWidth3, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31362, _mut31363, _mut31364, _mut31365)], v[AOR_plus(p, 3, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31366, _mut31367, _mut31368, _mut31369)], "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31370, _mut31371, _mut31372, _mut31373), "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31374, _mut31375, _mut31376, _mut31377);
                        k += jWidth4;
                        p += 4;
                    }
                    while (ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31391, _mut31392, _mut31393, _mut31394, _mut31395)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300");
                        sum += AOR_multiply(block[k], v[p++], "org.apache.commons.math3.linear.BlockRealMatrix.preMultiply_1300", _mut31387, _mut31388, _mut31389, _mut31390);
                        k += jWidth;
                    }
                    out[q] += sum;
                }
            }
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31411, _mut31412, _mut31413, _mut31414), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31415, _mut31416, _mut31417, _mut31418));
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31466, _mut31467, _mut31468, _mut31469, _mut31470); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31419, _mut31420, _mut31421, _mut31422);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31423, _mut31424, _mut31425, _mut31426), rows);
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31461, _mut31462, _mut31463, _mut31464, _mut31465); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345");
                for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31456, _mut31457, _mut31458, _mut31459, _mut31460); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345");
                    final int jWidth = blockWidth(jBlock);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31427, _mut31428, _mut31429, _mut31430);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31431, _mut31432, _mut31433, _mut31434), columns);
                    final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31435, _mut31436, _mut31437, _mut31438), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31439, _mut31440, _mut31441, _mut31442)];
                    int k = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31443, _mut31444, _mut31445, _mut31446)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31447, _mut31448, _mut31449, _mut31450);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345", _mut31451, _mut31452, _mut31453, _mut31454, _mut31455); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1345");
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
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31471, _mut31472, _mut31473, _mut31474), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31475, _mut31476, _mut31477, _mut31478));
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31526, _mut31527, _mut31528, _mut31529, _mut31530); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31479, _mut31480, _mut31481, _mut31482);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31483, _mut31484, _mut31485, _mut31486), rows);
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31521, _mut31522, _mut31523, _mut31524, _mut31525); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369");
                for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31516, _mut31517, _mut31518, _mut31519, _mut31520); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369");
                    final int jWidth = blockWidth(jBlock);
                    final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31487, _mut31488, _mut31489, _mut31490);
                    final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31491, _mut31492, _mut31493, _mut31494), columns);
                    final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31495, _mut31496, _mut31497, _mut31498), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31499, _mut31500, _mut31501, _mut31502)];
                    int k = AOR_multiply((AOR_minus(p, pStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31503, _mut31504, _mut31505, _mut31506)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31507, _mut31508, _mut31509, _mut31510);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369", _mut31511, _mut31512, _mut31513, _mut31514, _mut31515); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1369");
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
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31610, _mut31611, _mut31612, _mut31613), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31614, _mut31615, _mut31616, _mut31617), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31618, _mut31619, _mut31620, _mut31621, _mut31622); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31531, _mut31532, _mut31533, _mut31534);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31535, _mut31536, _mut31537, _mut31538)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31539, _mut31540, _mut31541, _mut31542), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31543, _mut31544, _mut31545, _mut31546));
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31605, _mut31606, _mut31607, _mut31608, _mut31609); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393");
                for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31592, _mut31593, _mut31594, _mut31595), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31596, _mut31597, _mut31598, _mut31599), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31600, _mut31601, _mut31602, _mut31603, _mut31604); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393");
                    final int jWidth = blockWidth(jBlock);
                    final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31547, _mut31548, _mut31549, _mut31550);
                    final int qStart = FastMath.max(startColumn, q0);
                    final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31551, _mut31552, _mut31553, _mut31554)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31555, _mut31556, _mut31557, _mut31558), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31559, _mut31560, _mut31561, _mut31562));
                    final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31563, _mut31564, _mut31565, _mut31566), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31567, _mut31568, _mut31569, _mut31570)];
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31571, _mut31572, _mut31573, _mut31574)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31575, _mut31576, _mut31577, _mut31578), qStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31579, _mut31580, _mut31581, _mut31582), q0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31583, _mut31584, _mut31585, _mut31586);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393", _mut31587, _mut31588, _mut31589, _mut31590, _mut31591); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1393");
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
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31702, _mut31703, _mut31704, _mut31705), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31706, _mut31707, _mut31708, _mut31709), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31710, _mut31711, _mut31712, _mut31713, _mut31714); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31623, _mut31624, _mut31625, _mut31626);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31627, _mut31628, _mut31629, _mut31630)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31631, _mut31632, _mut31633, _mut31634), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31635, _mut31636, _mut31637, _mut31638));
            for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31697, _mut31698, _mut31699, _mut31700, _mut31701); ++p) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423");
                for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31684, _mut31685, _mut31686, _mut31687), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31688, _mut31689, _mut31690, _mut31691), "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31692, _mut31693, _mut31694, _mut31695, _mut31696); ++jBlock) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423");
                    final int jWidth = blockWidth(jBlock);
                    final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31639, _mut31640, _mut31641, _mut31642);
                    final int qStart = FastMath.max(startColumn, q0);
                    final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31643, _mut31644, _mut31645, _mut31646)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31647, _mut31648, _mut31649, _mut31650), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31651, _mut31652, _mut31653, _mut31654));
                    final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31655, _mut31656, _mut31657, _mut31658), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31659, _mut31660, _mut31661, _mut31662)];
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31663, _mut31664, _mut31665, _mut31666)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31667, _mut31668, _mut31669, _mut31670), qStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31671, _mut31672, _mut31673, _mut31674), q0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31675, _mut31676, _mut31677, _mut31678);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423", _mut31679, _mut31680, _mut31681, _mut31682, _mut31683); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInRowOrder_1423");
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
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31715, _mut31716, _mut31717, _mut31718), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31719, _mut31720, _mut31721, _mut31722));
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31754, _mut31755, _mut31756, _mut31757, _mut31758); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31723, _mut31724, _mut31725, _mut31726);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31727, _mut31728, _mut31729, _mut31730), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31749, _mut31750, _mut31751, _mut31752, _mut31753); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31731, _mut31732, _mut31733, _mut31734);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31735, _mut31736, _mut31737, _mut31738), columns);
                final double[] block = blocks[blockIndex];
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31744, _mut31745, _mut31746, _mut31747, _mut31748); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453");
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453", _mut31739, _mut31740, _mut31741, _mut31742, _mut31743); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1453");
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
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478");
        visitor.start(rows, columns, 0, AOR_minus(rows, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31759, _mut31760, _mut31761, _mut31762), 0, AOR_minus(columns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31763, _mut31764, _mut31765, _mut31766));
        int blockIndex = 0;
        for (int iBlock = 0; ROR_less(iBlock, blockRows, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31798, _mut31799, _mut31800, _mut31801, _mut31802); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478");
            final int pStart = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31767, _mut31768, _mut31769, _mut31770);
            final int pEnd = FastMath.min(AOR_plus(pStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31771, _mut31772, _mut31773, _mut31774), rows);
            for (int jBlock = 0; ROR_less(jBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31793, _mut31794, _mut31795, _mut31796, _mut31797); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478");
                final int qStart = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31775, _mut31776, _mut31777, _mut31778);
                final int qEnd = FastMath.min(AOR_plus(qStart, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31779, _mut31780, _mut31781, _mut31782), columns);
                final double[] block = blocks[blockIndex];
                int k = 0;
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31788, _mut31789, _mut31790, _mut31791, _mut31792); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478");
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478", _mut31783, _mut31784, _mut31785, _mut31786, _mut31787); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1478");
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
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31882, _mut31883, _mut31884, _mut31885), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31886, _mut31887, _mut31888, _mut31889), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31890, _mut31891, _mut31892, _mut31893, _mut31894); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31803, _mut31804, _mut31805, _mut31806);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31807, _mut31808, _mut31809, _mut31810)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31811, _mut31812, _mut31813, _mut31814), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31815, _mut31816, _mut31817, _mut31818));
            for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31869, _mut31870, _mut31871, _mut31872), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31873, _mut31874, _mut31875, _mut31876), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31877, _mut31878, _mut31879, _mut31880, _mut31881); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503");
                final int jWidth = blockWidth(jBlock);
                final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31819, _mut31820, _mut31821, _mut31822);
                final int qStart = FastMath.max(startColumn, q0);
                final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31823, _mut31824, _mut31825, _mut31826)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31827, _mut31828, _mut31829, _mut31830), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31831, _mut31832, _mut31833, _mut31834));
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31835, _mut31836, _mut31837, _mut31838), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31839, _mut31840, _mut31841, _mut31842)];
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31864, _mut31865, _mut31866, _mut31867, _mut31868); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503");
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31843, _mut31844, _mut31845, _mut31846)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31847, _mut31848, _mut31849, _mut31850), qStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31851, _mut31852, _mut31853, _mut31854), q0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31855, _mut31856, _mut31857, _mut31858);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503", _mut31859, _mut31860, _mut31861, _mut31862, _mut31863); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1503");
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
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534");
        MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
        visitor.start(rows, columns, startRow, endRow, startColumn, endColumn);
        for (int iBlock = startRow / BLOCK_SIZE; ROR_less(iBlock, AOR_plus(1, AOR_divide(endRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31974, _mut31975, _mut31976, _mut31977), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31978, _mut31979, _mut31980, _mut31981), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31982, _mut31983, _mut31984, _mut31985, _mut31986); ++iBlock) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534");
            final int p0 = AOR_multiply(iBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31895, _mut31896, _mut31897, _mut31898);
            final int pStart = FastMath.max(startRow, p0);
            final int pEnd = FastMath.min(AOR_multiply((AOR_plus(iBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31899, _mut31900, _mut31901, _mut31902)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31903, _mut31904, _mut31905, _mut31906), AOR_plus(1, endRow, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31907, _mut31908, _mut31909, _mut31910));
            for (int jBlock = startColumn / BLOCK_SIZE; ROR_less(jBlock, AOR_plus(1, AOR_divide(endColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31961, _mut31962, _mut31963, _mut31964), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31965, _mut31966, _mut31967, _mut31968), "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31969, _mut31970, _mut31971, _mut31972, _mut31973); ++jBlock) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534");
                final int jWidth = blockWidth(jBlock);
                final int q0 = AOR_multiply(jBlock, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31911, _mut31912, _mut31913, _mut31914);
                final int qStart = FastMath.max(startColumn, q0);
                final int qEnd = FastMath.min(AOR_multiply((AOR_plus(jBlock, 1, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31915, _mut31916, _mut31917, _mut31918)), BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31919, _mut31920, _mut31921, _mut31922), AOR_plus(1, endColumn, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31923, _mut31924, _mut31925, _mut31926));
                final double[] block = blocks[AOR_plus(AOR_multiply(iBlock, blockColumns, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31927, _mut31928, _mut31929, _mut31930), jBlock, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31931, _mut31932, _mut31933, _mut31934)];
                for (int p = pStart; ROR_less(p, pEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31956, _mut31957, _mut31958, _mut31959, _mut31960); ++p) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534");
                    int k = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(p, p0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31935, _mut31936, _mut31937, _mut31938)), jWidth, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31939, _mut31940, _mut31941, _mut31942), qStart, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31943, _mut31944, _mut31945, _mut31946), q0, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31947, _mut31948, _mut31949, _mut31950);
                    for (int q = qStart; ROR_less(q, qEnd, "org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534", _mut31951, _mut31952, _mut31953, _mut31954, _mut31955); ++q) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.walkInOptimizedOrder_1534");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.blockHeight_1569");
        return (ROR_equals(blockRow, AOR_minus(blockRows, 1, "org.apache.commons.math3.linear.BlockRealMatrix.blockHeight_1569", _mut31987, _mut31988, _mut31989, _mut31990), "org.apache.commons.math3.linear.BlockRealMatrix.blockHeight_1569", _mut31991, _mut31992, _mut31993, _mut31994, _mut31995)) ? AOR_minus(rows, AOR_multiply(blockRow, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.blockHeight_1569", _mut31996, _mut31997, _mut31998, _mut31999), "org.apache.commons.math3.linear.BlockRealMatrix.blockHeight_1569", _mut32000, _mut32001, _mut32002, _mut32003) : BLOCK_SIZE;
    }

    /**
     * Get the width of a block.
     * @param blockColumn column index (in block sense) of the block
     * @return width (number of columns) of the block
     */
    private int blockWidth(final int blockColumn) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BlockRealMatrix.blockWidth_1578");
        return (ROR_equals(blockColumn, AOR_minus(blockColumns, 1, "org.apache.commons.math3.linear.BlockRealMatrix.blockWidth_1578", _mut32004, _mut32005, _mut32006, _mut32007), "org.apache.commons.math3.linear.BlockRealMatrix.blockWidth_1578", _mut32008, _mut32009, _mut32010, _mut32011, _mut32012)) ? AOR_minus(columns, AOR_multiply(blockColumn, BLOCK_SIZE, "org.apache.commons.math3.linear.BlockRealMatrix.blockWidth_1578", _mut32013, _mut32014, _mut32015, _mut32016), "org.apache.commons.math3.linear.BlockRealMatrix.blockWidth_1578", _mut32017, _mut32018, _mut32019, _mut32020) : BLOCK_SIZE;
    }
}
