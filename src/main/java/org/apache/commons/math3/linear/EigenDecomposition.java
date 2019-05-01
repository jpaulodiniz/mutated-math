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

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the eigen decomposition of a real matrix.
 * <p>The eigen decomposition of matrix A is a set of two matrices:
 * V and D such that A = V &times; D &times; V<sup>T</sup>.
 * A, V and D are all m &times; m matrices.</p>
 * <p>This class is similar in spirit to the <code>EigenvalueDecomposition</code>
 * class from the <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a>
 * library, with the following changes:</p>
 * <ul>
 *   <li>a {@link #getVT() getVt} method has been added,</li>
 *   <li>two {@link #getRealEigenvalue(int) getRealEigenvalue} and {@link #getImagEigenvalue(int)
 *   getImagEigenvalue} methods to pick up a single eigenvalue have been added,</li>
 *   <li>a {@link #getEigenvector(int) getEigenvector} method to pick up a single
 *   eigenvector has been added,</li>
 *   <li>a {@link #getDeterminant() getDeterminant} method has been added.</li>
 *   <li>a {@link #getSolver() getSolver} method has been added.</li>
 * </ul>
 * <p>
 * As of 3.1, this class supports general real matrices (both symmetric and non-symmetric):
 * </p>
 * <p>
 * If A is symmetric, then A = V*D*V' where the eigenvalue matrix D is diagonal and the eigenvector
 * matrix V is orthogonal, i.e. A = V.multiply(D.multiply(V.transpose())) and
 * V.multiply(V.transpose()) equals the identity matrix.
 * </p>
 * <p>
 * If A is not symmetric, then the eigenvalue matrix D is block diagonal with the real eigenvalues
 * in 1-by-1 blocks and any complex eigenvalues, lambda + i*mu, in 2-by-2 blocks:
 * <pre>
 *    [lambda, mu    ]
 *    [   -mu, lambda]
 * </pre>
 * The columns of V represent the eigenvectors in the sense that A*V = V*D,
 * i.e. A.multiply(V) equals V.multiply(D).
 * The matrix V may be badly conditioned, or even singular, so the validity of the equation
 * A = V*D*inverse(V) depends upon the condition of V.
 * </p>
 * <p>
 * This implementation is based on the paper by A. Drubrulle, R.S. Martin and
 * J.H. Wilkinson "The Implicit QL Algorithm" in Wilksinson and Reinsch (1971)
 * Handbook for automatic computation, vol. 2, Linear algebra, Springer-Verlag,
 * New-York
 * </p>
 * @see <a href="http://mathworld.wolfram.com/EigenDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Eigendecomposition_of_a_matrix">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class EigenDecomposition {

    @Conditional
    public static boolean _mut34647 = false, _mut34648 = false, _mut34649 = false, _mut34650 = false, _mut34651 = false, _mut34652 = false, _mut34653 = false, _mut34654 = false, _mut34655 = false, _mut34656 = false, _mut34657 = false, _mut34658 = false, _mut34659 = false, _mut34660 = false, _mut34661 = false, _mut34662 = false, _mut34663 = false, _mut34664 = false, _mut34665 = false, _mut34666 = false, _mut34667 = false, _mut34668 = false, _mut34669 = false, _mut34670 = false, _mut34671 = false, _mut34672 = false, _mut34673 = false, _mut34674 = false, _mut34675 = false, _mut34676 = false, _mut34677 = false, _mut34678 = false, _mut34679 = false, _mut34680 = false, _mut34681 = false, _mut34682 = false, _mut34683 = false, _mut34684 = false, _mut34685 = false, _mut34686 = false, _mut34687 = false, _mut34688 = false, _mut34689 = false, _mut34690 = false, _mut34691 = false, _mut34692 = false, _mut34693 = false, _mut34694 = false, _mut34695 = false, _mut34696 = false, _mut34697 = false, _mut34698 = false, _mut34699 = false, _mut34700 = false, _mut34701 = false, _mut34702 = false, _mut34703 = false, _mut34704 = false, _mut34705 = false, _mut34706 = false, _mut34707 = false, _mut34708 = false, _mut34709 = false, _mut34710 = false, _mut34711 = false, _mut34712 = false, _mut34713 = false, _mut34714 = false, _mut34715 = false, _mut34716 = false, _mut34717 = false, _mut34718 = false, _mut34719 = false, _mut34720 = false, _mut34721 = false, _mut34722 = false, _mut34723 = false, _mut34724 = false, _mut34725 = false, _mut34726 = false, _mut34727 = false, _mut34728 = false, _mut34729 = false, _mut34730 = false, _mut34731 = false, _mut34732 = false, _mut34733 = false, _mut34734 = false, _mut34735 = false, _mut34736 = false, _mut34737 = false, _mut34738 = false, _mut34739 = false, _mut34740 = false, _mut34741 = false, _mut34742 = false, _mut34743 = false, _mut34744 = false, _mut34745 = false, _mut34746 = false, _mut34747 = false, _mut34748 = false, _mut34749 = false, _mut34750 = false, _mut34751 = false, _mut34752 = false, _mut34753 = false, _mut34754 = false, _mut34755 = false, _mut34756 = false, _mut34757 = false, _mut34758 = false, _mut34759 = false, _mut34760 = false, _mut34761 = false, _mut34762 = false, _mut34763 = false, _mut34764 = false, _mut34765 = false, _mut34766 = false, _mut34767 = false, _mut34768 = false, _mut34769 = false, _mut34770 = false, _mut34771 = false, _mut34772 = false, _mut34773 = false, _mut34774 = false, _mut34775 = false, _mut34776 = false, _mut34777 = false, _mut34778 = false, _mut34779 = false, _mut34780 = false, _mut34781 = false, _mut34782 = false, _mut34783 = false, _mut34784 = false, _mut34785 = false, _mut34786 = false, _mut34787 = false, _mut34788 = false, _mut34789 = false, _mut34790 = false, _mut34791 = false, _mut34792 = false, _mut34793 = false, _mut34794 = false, _mut34795 = false, _mut34796 = false, _mut34797 = false, _mut34798 = false, _mut34799 = false, _mut34800 = false, _mut34801 = false, _mut34802 = false, _mut34803 = false, _mut34804 = false, _mut34805 = false, _mut34806 = false, _mut34807 = false, _mut34808 = false, _mut34809 = false, _mut34810 = false, _mut34811 = false, _mut34812 = false, _mut34813 = false, _mut34814 = false, _mut34815 = false, _mut34816 = false, _mut34817 = false, _mut34818 = false, _mut34819 = false, _mut34820 = false, _mut34821 = false, _mut34822 = false, _mut34823 = false, _mut34824 = false, _mut34825 = false, _mut34826 = false, _mut34827 = false, _mut34828 = false, _mut34829 = false, _mut34830 = false, _mut34831 = false, _mut34832 = false, _mut34833 = false, _mut34834 = false, _mut34835 = false, _mut34836 = false, _mut34837 = false, _mut34838 = false, _mut34839 = false, _mut34840 = false, _mut34841 = false, _mut34842 = false, _mut34843 = false, _mut34844 = false, _mut34845 = false, _mut34846 = false, _mut34847 = false, _mut34848 = false, _mut34849 = false, _mut34850 = false, _mut34851 = false, _mut34852 = false, _mut34853 = false, _mut34854 = false, _mut34855 = false, _mut34856 = false, _mut34857 = false, _mut34858 = false, _mut34859 = false, _mut34860 = false, _mut34861 = false, _mut34862 = false, _mut34863 = false, _mut34864 = false, _mut34865 = false, _mut34866 = false, _mut34867 = false, _mut34868 = false, _mut34869 = false, _mut34870 = false, _mut34871 = false, _mut34872 = false, _mut34873 = false, _mut34874 = false, _mut34875 = false, _mut34876 = false, _mut34877 = false, _mut34878 = false, _mut34879 = false, _mut34880 = false, _mut34881 = false, _mut34882 = false, _mut34883 = false, _mut34884 = false, _mut34885 = false, _mut34886 = false, _mut34887 = false, _mut34888 = false, _mut34889 = false, _mut34890 = false, _mut34891 = false, _mut34892 = false, _mut34893 = false, _mut34894 = false, _mut34895 = false, _mut34896 = false, _mut34897 = false, _mut34898 = false, _mut34899 = false, _mut34900 = false, _mut34901 = false, _mut34902 = false, _mut34903 = false, _mut34904 = false, _mut34905 = false, _mut34906 = false, _mut34907 = false, _mut34908 = false, _mut34909 = false, _mut34910 = false, _mut34911 = false, _mut34912 = false, _mut34913 = false, _mut34914 = false, _mut34915 = false, _mut34916 = false, _mut34917 = false, _mut34918 = false, _mut34919 = false, _mut34920 = false, _mut34921 = false, _mut34922 = false, _mut34923 = false, _mut34924 = false, _mut34925 = false, _mut34926 = false, _mut34927 = false, _mut34928 = false, _mut34929 = false, _mut34930 = false, _mut34931 = false, _mut34932 = false, _mut34933 = false, _mut34934 = false, _mut34935 = false, _mut34936 = false, _mut34937 = false, _mut34938 = false, _mut34939 = false, _mut34940 = false, _mut34941 = false, _mut34942 = false, _mut34943 = false, _mut34944 = false, _mut34945 = false, _mut34946 = false, _mut34947 = false, _mut34948 = false, _mut34949 = false, _mut34950 = false, _mut34951 = false, _mut34952 = false, _mut34953 = false, _mut34954 = false, _mut34955 = false, _mut34956 = false, _mut34957 = false, _mut34958 = false, _mut34959 = false, _mut34960 = false, _mut34961 = false, _mut34962 = false, _mut34963 = false, _mut34964 = false, _mut34965 = false, _mut34966 = false, _mut34967 = false, _mut34968 = false, _mut34969 = false, _mut34970 = false, _mut34971 = false, _mut34972 = false, _mut34973 = false, _mut34974 = false, _mut34975 = false, _mut34976 = false, _mut34977 = false, _mut34978 = false, _mut34979 = false, _mut34980 = false, _mut34981 = false, _mut34982 = false, _mut34983 = false, _mut34984 = false, _mut34985 = false, _mut34986 = false, _mut34987 = false, _mut34988 = false, _mut34989 = false, _mut34990 = false, _mut34991 = false, _mut34992 = false, _mut34993 = false, _mut34994 = false, _mut34995 = false, _mut34996 = false, _mut34997 = false, _mut34998 = false, _mut34999 = false, _mut35000 = false, _mut35001 = false, _mut35002 = false, _mut35003 = false, _mut35004 = false, _mut35005 = false, _mut35006 = false, _mut35007 = false, _mut35008 = false, _mut35009 = false, _mut35010 = false, _mut35011 = false, _mut35012 = false, _mut35013 = false, _mut35014 = false, _mut35015 = false, _mut35016 = false, _mut35017 = false, _mut35018 = false, _mut35019 = false, _mut35020 = false, _mut35021 = false, _mut35022 = false, _mut35023 = false, _mut35024 = false, _mut35025 = false, _mut35026 = false, _mut35027 = false, _mut35028 = false, _mut35029 = false, _mut35030 = false, _mut35031 = false, _mut35032 = false, _mut35033 = false, _mut35034 = false, _mut35035 = false, _mut35036 = false, _mut35037 = false, _mut35038 = false, _mut35039 = false, _mut35040 = false, _mut35041 = false, _mut35042 = false, _mut35043 = false, _mut35044 = false, _mut35045 = false, _mut35046 = false, _mut35047 = false, _mut35048 = false, _mut35049 = false, _mut35050 = false, _mut35051 = false, _mut35052 = false, _mut35053 = false, _mut35054 = false, _mut35055 = false, _mut35056 = false, _mut35057 = false, _mut35058 = false, _mut35059 = false, _mut35060 = false, _mut35061 = false, _mut35062 = false, _mut35063 = false, _mut35064 = false, _mut35065 = false, _mut35066 = false, _mut35067 = false, _mut35068 = false, _mut35069 = false, _mut35070 = false, _mut35071 = false, _mut35072 = false, _mut35073 = false, _mut35074 = false, _mut35075 = false, _mut35076 = false, _mut35077 = false, _mut35078 = false, _mut35079 = false, _mut35080 = false, _mut35081 = false, _mut35082 = false, _mut35083 = false, _mut35084 = false, _mut35085 = false, _mut35086 = false, _mut35087 = false, _mut35088 = false, _mut35089 = false, _mut35090 = false, _mut35091 = false, _mut35092 = false, _mut35093 = false, _mut35094 = false, _mut35095 = false, _mut35096 = false, _mut35097 = false, _mut35098 = false, _mut35099 = false, _mut35100 = false, _mut35101 = false, _mut35102 = false, _mut35103 = false, _mut35104 = false, _mut35105 = false, _mut35106 = false, _mut35107 = false, _mut35108 = false, _mut35109 = false, _mut35110 = false, _mut35111 = false, _mut35112 = false, _mut35113 = false, _mut35114 = false, _mut35115 = false, _mut35116 = false, _mut35117 = false, _mut35118 = false, _mut35119 = false, _mut35120 = false, _mut35121 = false, _mut35122 = false, _mut35123 = false, _mut35124 = false, _mut35125 = false, _mut35126 = false, _mut35127 = false, _mut35128 = false, _mut35129 = false, _mut35130 = false, _mut35131 = false, _mut35132 = false, _mut35133 = false, _mut35134 = false, _mut35135 = false, _mut35136 = false, _mut35137 = false, _mut35138 = false, _mut35139 = false, _mut35140 = false, _mut35141 = false, _mut35142 = false, _mut35143 = false, _mut35144 = false, _mut35145 = false, _mut35146 = false, _mut35147 = false, _mut35148 = false, _mut35149 = false, _mut35150 = false, _mut35151 = false, _mut35152 = false, _mut35153 = false, _mut35154 = false, _mut35155 = false, _mut35156 = false, _mut35157 = false, _mut35158 = false, _mut35159 = false, _mut35160 = false, _mut35161 = false, _mut35162 = false, _mut35163 = false, _mut35164 = false, _mut35165 = false, _mut35166 = false, _mut35167 = false, _mut35168 = false, _mut35169 = false, _mut35170 = false, _mut35171 = false, _mut35172 = false, _mut35173 = false, _mut35174 = false, _mut35175 = false, _mut35176 = false, _mut35177 = false, _mut35178 = false, _mut35179 = false, _mut35180 = false, _mut35181 = false, _mut35182 = false, _mut35183 = false, _mut35184 = false, _mut35185 = false, _mut35186 = false, _mut35187 = false, _mut35188 = false, _mut35189 = false, _mut35190 = false, _mut35191 = false, _mut35192 = false, _mut35193 = false, _mut35194 = false, _mut35195 = false, _mut35196 = false, _mut35197 = false, _mut35198 = false, _mut35199 = false, _mut35200 = false, _mut35201 = false, _mut35202 = false, _mut35203 = false, _mut35204 = false, _mut35205 = false, _mut35206 = false, _mut35207 = false, _mut35208 = false, _mut35209 = false, _mut35210 = false, _mut35211 = false, _mut35212 = false, _mut35213 = false, _mut35214 = false, _mut35215 = false, _mut35216 = false, _mut35217 = false, _mut35218 = false, _mut35219 = false, _mut35220 = false, _mut35221 = false, _mut35222 = false, _mut35223 = false, _mut35224 = false, _mut35225 = false, _mut35226 = false, _mut35227 = false, _mut35228 = false, _mut35229 = false, _mut35230 = false, _mut35231 = false, _mut35232 = false, _mut35233 = false, _mut35234 = false, _mut35235 = false, _mut35236 = false, _mut35237 = false, _mut35238 = false, _mut35239 = false, _mut35240 = false, _mut35241 = false, _mut35242 = false, _mut35243 = false, _mut35244 = false, _mut35245 = false, _mut35246 = false, _mut35247 = false, _mut35248 = false, _mut35249 = false, _mut35250 = false, _mut35251 = false, _mut35252 = false, _mut35253 = false, _mut35254 = false, _mut35255 = false, _mut35256 = false, _mut35257 = false, _mut35258 = false, _mut35259 = false, _mut35260 = false, _mut35261 = false, _mut35262 = false, _mut35263 = false, _mut35264 = false, _mut35265 = false, _mut35266 = false, _mut35267 = false, _mut35268 = false, _mut35269 = false, _mut35270 = false, _mut35271 = false, _mut35272 = false, _mut35273 = false, _mut35274 = false, _mut35275 = false, _mut35276 = false, _mut35277 = false, _mut35278 = false, _mut35279 = false, _mut35280 = false, _mut35281 = false, _mut35282 = false, _mut35283 = false, _mut35284 = false, _mut35285 = false, _mut35286 = false, _mut35287 = false, _mut35288 = false, _mut35289 = false, _mut35290 = false, _mut35291 = false, _mut35292 = false, _mut35293 = false, _mut35294 = false, _mut35295 = false, _mut35296 = false, _mut35297 = false, _mut35298 = false, _mut35299 = false, _mut35300 = false, _mut35301 = false, _mut35302 = false, _mut35303 = false, _mut35304 = false, _mut35305 = false, _mut35306 = false, _mut35307 = false, _mut35308 = false, _mut35309 = false, _mut35310 = false, _mut35311 = false, _mut35312 = false, _mut35313 = false, _mut35314 = false, _mut35315 = false, _mut35316 = false, _mut35317 = false, _mut35318 = false, _mut35319 = false, _mut35320 = false, _mut35321 = false, _mut35322 = false, _mut35323 = false, _mut35324 = false, _mut35325 = false, _mut35326 = false, _mut35327 = false, _mut35328 = false, _mut35329 = false, _mut35330 = false, _mut35331 = false, _mut35332 = false, _mut35333 = false, _mut35334 = false, _mut35335 = false, _mut35336 = false, _mut35337 = false, _mut35338 = false, _mut35339 = false, _mut35340 = false, _mut35341 = false, _mut35342 = false, _mut35343 = false, _mut35344 = false, _mut35345 = false, _mut35346 = false, _mut35347 = false, _mut35348 = false, _mut35349 = false, _mut35350 = false, _mut35351 = false, _mut35352 = false, _mut35353 = false, _mut35354 = false, _mut35355 = false, _mut35356 = false, _mut35357 = false, _mut35358 = false, _mut35359 = false, _mut35360 = false, _mut35361 = false, _mut35362 = false, _mut35363 = false, _mut35364 = false, _mut35365 = false, _mut35366 = false, _mut35367 = false, _mut35368 = false, _mut35369 = false, _mut35370 = false, _mut35371 = false, _mut35372 = false, _mut35373 = false, _mut35374 = false, _mut35375 = false, _mut35376 = false, _mut35377 = false, _mut35378 = false, _mut35379 = false, _mut35380 = false, _mut35381 = false, _mut35382 = false, _mut35383 = false, _mut35384 = false, _mut35385 = false, _mut35386 = false, _mut35387 = false, _mut35388 = false, _mut35389 = false, _mut35390 = false, _mut35391 = false, _mut35392 = false, _mut35393 = false, _mut35394 = false, _mut35395 = false, _mut35396 = false, _mut35397 = false, _mut35398 = false, _mut35399 = false, _mut35400 = false, _mut35401 = false, _mut35402 = false, _mut35403 = false, _mut35404 = false, _mut35405 = false, _mut35406 = false, _mut35407 = false, _mut35408 = false, _mut35409 = false, _mut35410 = false, _mut35411 = false, _mut35412 = false, _mut35413 = false, _mut35414 = false, _mut35415 = false, _mut35416 = false, _mut35417 = false, _mut35418 = false, _mut35419 = false, _mut35420 = false, _mut35421 = false, _mut35422 = false, _mut35423 = false, _mut35424 = false, _mut35425 = false, _mut35426 = false, _mut35427 = false, _mut35428 = false, _mut35429 = false, _mut35430 = false, _mut35431 = false, _mut35432 = false, _mut35433 = false, _mut35434 = false, _mut35435 = false, _mut35436 = false, _mut35437 = false, _mut35438 = false, _mut35439 = false, _mut35440 = false, _mut35441 = false, _mut35442 = false, _mut35443 = false, _mut35444 = false, _mut35445 = false, _mut35446 = false, _mut35447 = false, _mut35448 = false, _mut35449 = false, _mut35450 = false, _mut35451 = false, _mut35452 = false, _mut35453 = false, _mut35454 = false, _mut35455 = false, _mut35456 = false, _mut35457 = false, _mut35458 = false, _mut35459 = false, _mut35460 = false, _mut35461 = false, _mut35462 = false, _mut35463 = false, _mut35464 = false, _mut35465 = false, _mut35466 = false, _mut35467 = false, _mut35468 = false, _mut35469 = false, _mut35470 = false, _mut35471 = false, _mut35472 = false, _mut35473 = false, _mut35474 = false, _mut35475 = false, _mut35476 = false, _mut35477 = false, _mut35478 = false, _mut35479 = false, _mut35480 = false, _mut35481 = false, _mut35482 = false, _mut35483 = false, _mut35484 = false, _mut35485 = false, _mut35486 = false, _mut35487 = false, _mut35488 = false, _mut35489 = false, _mut35490 = false, _mut35491 = false, _mut35492 = false, _mut35493 = false, _mut35494 = false, _mut35495 = false, _mut35496 = false, _mut35497 = false, _mut35498 = false, _mut35499 = false, _mut35500 = false, _mut35501 = false, _mut35502 = false, _mut35503 = false, _mut35504 = false, _mut35505 = false, _mut35506 = false, _mut35507 = false, _mut35508 = false, _mut35509 = false, _mut35510 = false, _mut35511 = false, _mut35512 = false, _mut35513 = false, _mut35514 = false, _mut35515 = false, _mut35516 = false, _mut35517 = false, _mut35518 = false, _mut35519 = false, _mut35520 = false, _mut35521 = false, _mut35522 = false, _mut35523 = false, _mut35524 = false, _mut35525 = false, _mut35526 = false, _mut35527 = false, _mut35528 = false, _mut35529 = false, _mut35530 = false, _mut35531 = false, _mut35532 = false, _mut35533 = false, _mut35534 = false, _mut35535 = false, _mut35536 = false, _mut35537 = false, _mut35538 = false, _mut35539 = false, _mut35540 = false, _mut35541 = false, _mut35542 = false, _mut35543 = false, _mut35544 = false, _mut35545 = false, _mut35546 = false, _mut35547 = false, _mut35548 = false, _mut35549 = false, _mut35550 = false, _mut35551 = false, _mut35552 = false, _mut35553 = false, _mut35554 = false, _mut35555 = false, _mut35556 = false, _mut35557 = false, _mut35558 = false, _mut35559 = false, _mut35560 = false, _mut35561 = false, _mut35562 = false, _mut35563 = false, _mut35564 = false, _mut35565 = false, _mut35566 = false, _mut35567 = false, _mut35568 = false, _mut35569 = false, _mut35570 = false, _mut35571 = false, _mut35572 = false, _mut35573 = false, _mut35574 = false, _mut35575 = false, _mut35576 = false, _mut35577 = false, _mut35578 = false, _mut35579 = false, _mut35580 = false, _mut35581 = false, _mut35582 = false, _mut35583 = false, _mut35584 = false, _mut35585 = false, _mut35586 = false, _mut35587 = false, _mut35588 = false, _mut35589 = false, _mut35590 = false, _mut35591 = false, _mut35592 = false, _mut35593 = false, _mut35594 = false, _mut35595 = false, _mut35596 = false, _mut35597 = false, _mut35598 = false, _mut35599 = false, _mut35600 = false, _mut35601 = false, _mut35602 = false, _mut35603 = false, _mut35604 = false, _mut35605 = false, _mut35606 = false, _mut35607 = false, _mut35608 = false, _mut35609 = false, _mut35610 = false, _mut35611 = false, _mut35612 = false, _mut35613 = false, _mut35614 = false, _mut35615 = false, _mut35616 = false, _mut35617 = false, _mut35618 = false, _mut35619 = false, _mut35620 = false, _mut35621 = false, _mut35622 = false, _mut35623 = false, _mut35624 = false, _mut35625 = false, _mut35626 = false, _mut35627 = false, _mut35628 = false, _mut35629 = false, _mut35630 = false, _mut35631 = false, _mut35632 = false, _mut35633 = false, _mut35634 = false, _mut35635 = false, _mut35636 = false, _mut35637 = false, _mut35638 = false, _mut35639 = false, _mut35640 = false, _mut35641 = false, _mut35642 = false, _mut35643 = false, _mut35644 = false, _mut35645 = false, _mut35646 = false, _mut35647 = false, _mut35648 = false, _mut35649 = false, _mut35650 = false, _mut35651 = false, _mut35652 = false, _mut35653 = false, _mut35654 = false, _mut35655 = false, _mut35656 = false, _mut35657 = false, _mut35658 = false, _mut35659 = false, _mut35660 = false, _mut35661 = false, _mut35662 = false, _mut35663 = false, _mut35664 = false, _mut35665 = false, _mut35666 = false, _mut35667 = false, _mut35668 = false, _mut35669 = false, _mut35670 = false, _mut35671 = false, _mut35672 = false, _mut35673 = false, _mut35674 = false, _mut35675 = false, _mut35676 = false, _mut35677 = false, _mut35678 = false, _mut35679 = false, _mut35680 = false, _mut35681 = false, _mut35682 = false, _mut35683 = false, _mut35684 = false, _mut35685 = false, _mut35686 = false, _mut35687 = false, _mut35688 = false, _mut35689 = false, _mut35690 = false, _mut35691 = false, _mut35692 = false, _mut35693 = false, _mut35694 = false, _mut35695 = false, _mut35696 = false, _mut35697 = false, _mut35698 = false, _mut35699 = false, _mut35700 = false, _mut35701 = false, _mut35702 = false, _mut35703 = false, _mut35704 = false, _mut35705 = false, _mut35706 = false, _mut35707 = false, _mut35708 = false, _mut35709 = false, _mut35710 = false, _mut35711 = false, _mut35712 = false, _mut35713 = false, _mut35714 = false, _mut35715 = false, _mut35716 = false, _mut35717 = false, _mut35718 = false, _mut35719 = false, _mut35720 = false, _mut35721 = false, _mut35722 = false, _mut35723 = false, _mut35724 = false, _mut35725 = false, _mut35726 = false, _mut35727 = false, _mut35728 = false, _mut35729 = false, _mut35730 = false, _mut35731 = false, _mut35732 = false, _mut35733 = false, _mut35734 = false, _mut35735 = false, _mut35736 = false, _mut35737 = false, _mut35738 = false, _mut35739 = false, _mut35740 = false, _mut35741 = false, _mut35742 = false, _mut35743 = false, _mut35744 = false, _mut35745 = false, _mut35746 = false, _mut35747 = false, _mut35748 = false, _mut35749 = false, _mut35750 = false, _mut35751 = false, _mut35752 = false, _mut35753 = false, _mut35754 = false, _mut35755 = false, _mut35756 = false, _mut35757 = false, _mut35758 = false, _mut35759 = false, _mut35760 = false, _mut35761 = false, _mut35762 = false, _mut35763 = false, _mut35764 = false, _mut35765 = false, _mut35766 = false, _mut35767 = false, _mut35768 = false, _mut35769 = false, _mut35770 = false, _mut35771 = false, _mut35772 = false, _mut35773 = false, _mut35774 = false, _mut35775 = false, _mut35776 = false, _mut35777 = false, _mut35778 = false, _mut35779 = false, _mut35780 = false, _mut35781 = false, _mut35782 = false, _mut35783 = false, _mut35784 = false, _mut35785 = false, _mut35786 = false, _mut35787 = false, _mut35788 = false, _mut35789 = false, _mut35790 = false, _mut35791 = false, _mut35792 = false, _mut35793 = false, _mut35794 = false, _mut35795 = false, _mut35796 = false, _mut35797 = false, _mut35798 = false, _mut35799 = false, _mut35800 = false, _mut35801 = false, _mut35802 = false, _mut35803 = false, _mut35804 = false, _mut35805 = false, _mut35806 = false, _mut35807 = false, _mut35808 = false, _mut35809 = false, _mut35810 = false, _mut35811 = false, _mut35812 = false, _mut35813 = false, _mut35814 = false, _mut35815 = false, _mut35816 = false, _mut35817 = false, _mut35818 = false, _mut35819 = false, _mut35820 = false, _mut35821 = false, _mut35822 = false, _mut35823 = false, _mut35824 = false, _mut35825 = false, _mut35826 = false, _mut35827 = false, _mut35828 = false, _mut35829 = false, _mut35830 = false, _mut35831 = false, _mut35832 = false, _mut35833 = false, _mut35834 = false, _mut35835 = false, _mut35836 = false, _mut35837 = false, _mut35838 = false, _mut35839 = false, _mut35840 = false, _mut35841 = false, _mut35842 = false, _mut35843 = false, _mut35844 = false, _mut35845 = false, _mut35846 = false, _mut35847 = false, _mut35848 = false, _mut35849 = false, _mut35850 = false, _mut35851 = false, _mut35852 = false, _mut35853 = false, _mut35854 = false, _mut35855 = false, _mut35856 = false, _mut35857 = false, _mut35858 = false, _mut35859 = false, _mut35860 = false, _mut35861 = false, _mut35862 = false, _mut35863 = false;

    /**
     * Internally used epsilon criteria.
     */
    private static final double EPSILON = 1e-12;

    /**
     * Maximum number of iterations accepted in the implicit QL transformation
     */
    private byte maxIter = 30;

    /**
     * Main diagonal of the tridiagonal matrix.
     */
    private double[] main;

    /**
     * Secondary diagonal of the tridiagonal matrix.
     */
    private double[] secondary;

    /**
     * Transformer to tridiagonal (may be null if matrix is already
     * tridiagonal).
     */
    private TriDiagonalTransformer transformer;

    /**
     * Real part of the realEigenvalues.
     */
    private double[] realEigenvalues;

    /**
     * Imaginary part of the realEigenvalues.
     */
    private double[] imagEigenvalues;

    /**
     * Eigenvectors.
     */
    private ArrayRealVector[] eigenvectors;

    /**
     * Cached value of V.
     */
    private RealMatrix cachedV;

    /**
     * Cached value of D.
     */
    private RealMatrix cachedD;

    /**
     * Cached value of Vt.
     */
    private RealMatrix cachedVt;

    /**
     * Whether the matrix is symmetric.
     */
    private final boolean isSymmetric;

    /**
     * Calculates the eigen decomposition of the given real matrix.
     * <p>
     * Supports decomposition of a general matrix since 3.1.
     *
     * @param matrix Matrix to decompose.
     * @throws MaxCountExceededException if the algorithm fails to converge.
     * @throws MathArithmeticException if the decomposition of a general matrix
     * results in a matrix with zero norm
     * @since 3.1
     */
    public EigenDecomposition(final RealMatrix matrix) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_116");
        final double symTol = AOR_multiply(AOR_multiply(AOR_multiply(10, matrix.getRowDimension(), "org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_116", _mut34647, _mut34648, _mut34649, _mut34650), matrix.getColumnDimension(), "org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_116", _mut34651, _mut34652, _mut34653, _mut34654), Precision.EPSILON, "org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_116", _mut34655, _mut34656, _mut34657, _mut34658);
        isSymmetric = MatrixUtils.isSymmetric(matrix, symTol);
        if (isSymmetric) {
            transformToTridiagonal(matrix);
            findEigenVectors(transformer.getQ().getData());
        } else {
            final SchurTransformer t = transformToSchur(matrix);
            findEigenVectorsFromSchur(t);
        }
    }

    /**
     * Calculates the eigen decomposition of the given real matrix.
     *
     * @param matrix Matrix to decompose.
     * @param splitTolerance Dummy parameter (present for backward
     * compatibility only).
     * @throws MathArithmeticException  if the decomposition of a general matrix
     * results in a matrix with zero norm
     * @throws MaxCountExceededException if the algorithm fails to converge.
     * @deprecated in 3.1 (to be removed in 4.0) due to unused parameter
     */
    @Deprecated
    public EigenDecomposition(final RealMatrix matrix, final double splitTolerance) throws MathArithmeticException {
        this(matrix);
    }

    /**
     * Calculates the eigen decomposition of the symmetric tridiagonal
     * matrix.  The Householder matrix is assumed to be the identity matrix.
     *
     * @param main Main diagonal of the symmetric tridiagonal form.
     * @param secondary Secondary of the tridiagonal form.
     * @throws MaxCountExceededException if the algorithm fails to converge.
     * @since 3.1
     */
    public EigenDecomposition(final double[] main, final double[] secondary) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_156");
        isSymmetric = true;
        this.main = main.clone();
        this.secondary = secondary.clone();
        transformer = null;
        final int size = main.length;
        final double[][] z = new double[size][size];
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_156", _mut34659, _mut34660, _mut34661, _mut34662, _mut34663); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.EigenDecomposition_156");
            z[i][i] = 1.0;
        }
        findEigenVectors(z);
    }

    /**
     * Calculates the eigen decomposition of the symmetric tridiagonal
     * matrix.  The Householder matrix is assumed to be the identity matrix.
     *
     * @param main Main diagonal of the symmetric tridiagonal form.
     * @param secondary Secondary of the tridiagonal form.
     * @param splitTolerance Dummy parameter (present for backward
     * compatibility only).
     * @throws MaxCountExceededException if the algorithm fails to converge.
     * @deprecated in 3.1 (to be removed in 4.0) due to unused parameter
     */
    @Deprecated
    public EigenDecomposition(final double[] main, final double[] secondary, final double splitTolerance) {
        this(main, secondary);
    }

    /**
     * Gets the matrix V of the decomposition.
     * V is an orthogonal matrix, i.e. its transpose is also its inverse.
     * The columns of V are the eigenvectors of the original matrix.
     * No assumption is made about the orientation of the system axes formed
     * by the columns of V (e.g. in a 3-dimension space, V can form a left-
     * or right-handed system).
     *
     * @return the V matrix.
     */
    public RealMatrix getV() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getV_196");
        if (cachedV == null) {
            final int m = eigenvectors.length;
            cachedV = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; ROR_less(k, m, "org.apache.commons.math3.linear.EigenDecomposition.getV_196", _mut34664, _mut34665, _mut34666, _mut34667, _mut34668); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getV_196");
                cachedV.setColumnVector(k, eigenvectors[k]);
            }
        }
        // return the cached matrix
        return cachedV;
    }

    /**
     * Gets the block diagonal matrix D of the decomposition.
     * D is a block diagonal matrix.
     * Real eigenvalues are on the diagonal while complex values are on
     * 2x2 blocks { {real +imaginary}, {-imaginary, real} }.
     *
     * @return the D matrix.
     *
     * @see #getRealEigenvalues()
     * @see #getImagEigenvalues()
     */
    public RealMatrix getD() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getD_220");
        if (cachedD == null) {
            // cache the matrix for subsequent calls
            cachedD = MatrixUtils.createRealDiagonalMatrix(realEigenvalues);
            for (int i = 0; ROR_less(i, imagEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.getD_220", _mut34687, _mut34688, _mut34689, _mut34690, _mut34691); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getD_220");
                if (ROR_greater(Precision.compareTo(imagEigenvalues[i], 0.0, EPSILON), 0, "org.apache.commons.math3.linear.EigenDecomposition.getD_220", _mut34669, _mut34670, _mut34671, _mut34672, _mut34673)) {
                    cachedD.setEntry(i, AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.getD_220", _mut34683, _mut34684, _mut34685, _mut34686), imagEigenvalues[i]);
                } else if (ROR_less(Precision.compareTo(imagEigenvalues[i], 0.0, EPSILON), 0, "org.apache.commons.math3.linear.EigenDecomposition.getD_220", _mut34674, _mut34675, _mut34676, _mut34677, _mut34678)) {
                    cachedD.setEntry(i, AOR_minus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.getD_220", _mut34679, _mut34680, _mut34681, _mut34682), imagEigenvalues[i]);
                }
            }
        }
        return cachedD;
    }

    /**
     * Gets the transpose of the matrix V of the decomposition.
     * V is an orthogonal matrix, i.e. its transpose is also its inverse.
     * The columns of V are the eigenvectors of the original matrix.
     * No assumption is made about the orientation of the system axes formed
     * by the columns of V (e.g. in a 3-dimension space, V can form a left-
     * or right-handed system).
     *
     * @return the transpose of the V matrix.
     */
    public RealMatrix getVT() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getVT_247");
        if (cachedVt == null) {
            final int m = eigenvectors.length;
            cachedVt = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; ROR_less(k, m, "org.apache.commons.math3.linear.EigenDecomposition.getVT_247", _mut34692, _mut34693, _mut34694, _mut34695, _mut34696); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getVT_247");
                cachedVt.setRowVector(k, eigenvectors[k]);
            }
        }
        // return the cached matrix
        return cachedVt;
    }

    /**
     * Returns whether the calculated eigen values are complex or real.
     * <p>The method performs a zero check for each element of the
     * {@link #getImagEigenvalues()} array and returns {@code true} if any
     * element is not equal to zero.
     *
     * @return {@code true} if the eigen values are complex, {@code false} otherwise
     * @since 3.1
     */
    public boolean hasComplexEigenvalues() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.hasComplexEigenvalues_270");
        for (int i = 0; ROR_less(i, imagEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.hasComplexEigenvalues_270", _mut34697, _mut34698, _mut34699, _mut34700, _mut34701); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.hasComplexEigenvalues_270");
            if (!Precision.equals(imagEigenvalues[i], 0.0, EPSILON)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a copy of the real parts of the eigenvalues of the original matrix.
     *
     * @return a copy of the real parts of the eigenvalues of the original matrix.
     *
     * @see #getD()
     * @see #getRealEigenvalue(int)
     * @see #getImagEigenvalues()
     */
    public double[] getRealEigenvalues() {
        return realEigenvalues.clone();
    }

    /**
     * Returns the real part of the i<sup>th</sup> eigenvalue of the original
     * matrix.
     *
     * @param i index of the eigenvalue (counting from 0)
     * @return real part of the i<sup>th</sup> eigenvalue of the original
     * matrix.
     *
     * @see #getD()
     * @see #getRealEigenvalues()
     * @see #getImagEigenvalue(int)
     */
    public double getRealEigenvalue(final int i) {
        return realEigenvalues[i];
    }

    /**
     * Gets a copy of the imaginary parts of the eigenvalues of the original
     * matrix.
     *
     * @return a copy of the imaginary parts of the eigenvalues of the original
     * matrix.
     *
     * @see #getD()
     * @see #getImagEigenvalue(int)
     * @see #getRealEigenvalues()
     */
    public double[] getImagEigenvalues() {
        return imagEigenvalues.clone();
    }

    /**
     * Gets the imaginary part of the i<sup>th</sup> eigenvalue of the original
     * matrix.
     *
     * @param i Index of the eigenvalue (counting from 0).
     * @return the imaginary part of the i<sup>th</sup> eigenvalue of the original
     * matrix.
     *
     * @see #getD()
     * @see #getImagEigenvalues()
     * @see #getRealEigenvalue(int)
     */
    public double getImagEigenvalue(final int i) {
        return imagEigenvalues[i];
    }

    /**
     * Gets a copy of the i<sup>th</sup> eigenvector of the original matrix.
     *
     * @param i Index of the eigenvector (counting from 0).
     * @return a copy of the i<sup>th</sup> eigenvector of the original matrix.
     * @see #getD()
     */
    public RealVector getEigenvector(final int i) {
        return eigenvectors[i].copy();
    }

    /**
     * Computes the determinant of the matrix.
     *
     * @return the determinant of the matrix.
     */
    public double getDeterminant() {
        double determinant = 1;
        for (double lambda : realEigenvalues) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getDeterminant_355");
            determinant *= lambda;
        }
        return determinant;
    }

    /**
     * Computes the square-root of the matrix.
     * This implementation assumes that the matrix is symmetric and positive
     * definite.
     *
     * @return the square-root of the matrix.
     * @throws MathUnsupportedOperationException if the matrix is not
     * symmetric or not positive definite.
     * @since 3.1
     */
    public RealMatrix getSquareRoot() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getSquareRoot_373");
        if (!isSymmetric) {
            throw new MathUnsupportedOperationException();
        }
        final double[] sqrtEigenValues = new double[realEigenvalues.length];
        for (int i = 0; ROR_less(i, realEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.getSquareRoot_373", _mut34707, _mut34708, _mut34709, _mut34710, _mut34711); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getSquareRoot_373");
            final double eigen = realEigenvalues[i];
            if (ROR_less_equals(eigen, 0, "org.apache.commons.math3.linear.EigenDecomposition.getSquareRoot_373", _mut34702, _mut34703, _mut34704, _mut34705, _mut34706)) {
                throw new MathUnsupportedOperationException();
            }
            sqrtEigenValues[i] = FastMath.sqrt(eigen);
        }
        final RealMatrix sqrtEigen = MatrixUtils.createRealDiagonalMatrix(sqrtEigenValues);
        final RealMatrix v = getV();
        final RealMatrix vT = getVT();
        return v.multiply(sqrtEigen).multiply(vT);
    }

    /**
     * Gets a solver for finding the A &times; X = B solution in exact
     * linear sense.
     * <p>
     * Since 3.1, eigen decomposition of a general matrix is supported,
     * but the {@link DecompositionSolver} only supports real eigenvalues.
     *
     * @return a solver
     * @throws MathUnsupportedOperationException if the decomposition resulted in
     * complex eigenvalues
     */
    public DecompositionSolver getSolver() {
        if (hasComplexEigenvalues()) {
            throw new MathUnsupportedOperationException();
        }
        return new Solver(realEigenvalues, imagEigenvalues, eigenvectors);
    }

    /**
     * Specialized solver.
     */
    private static class Solver implements DecompositionSolver {

        /**
         * Real part of the realEigenvalues.
         */
        private double[] realEigenvalues;

        /**
         * Imaginary part of the realEigenvalues.
         */
        private double[] imagEigenvalues;

        /**
         * Eigenvectors.
         */
        private final ArrayRealVector[] eigenvectors;

        /**
         * Builds a solver from decomposed matrix.
         *
         * @param realEigenvalues Real parts of the eigenvalues.
         * @param imagEigenvalues Imaginary parts of the eigenvalues.
         * @param eigenvectors Eigenvectors.
         */
        private Solver(final double[] realEigenvalues, final double[] imagEigenvalues, final ArrayRealVector[] eigenvectors) {
            this.realEigenvalues = realEigenvalues;
            this.imagEigenvalues = imagEigenvalues;
            this.eigenvectors = eigenvectors;
        }

        /**
         * Solves the linear equation A &times; X = B for symmetric matrices A.
         * <p>
         * This method only finds exact linear solutions, i.e. solutions for
         * which ||A &times; X - B|| is exactly 0.
         * </p>
         *
         * @param b Right-hand side of the equation A &times; X = B.
         * @return a Vector X that minimizes the two norm of A &times; X - B.
         *
         * @throws DimensionMismatchException if the matrices dimensions do not match.
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public RealVector solve(final RealVector b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_448");
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            final int m = realEigenvalues.length;
            if (ROR_not_equals(b.getDimension(), m, "org.apache.commons.math3.linear.EigenDecomposition.solve_448", _mut34712, _mut34713, _mut34714, _mut34715, _mut34716)) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            final double[] bp = new double[m];
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_448", _mut34730, _mut34731, _mut34732, _mut34733, _mut34734); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_448");
                final ArrayRealVector v = eigenvectors[i];
                final double[] vData = v.getDataRef();
                final double s = AOR_divide(v.dotProduct(b), realEigenvalues[i], "org.apache.commons.math3.linear.EigenDecomposition.solve_448", _mut34717, _mut34718, _mut34719, _mut34720);
                for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_448", _mut34725, _mut34726, _mut34727, _mut34728, _mut34729); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_448");
                    bp[j] += AOR_multiply(s, vData[j], "org.apache.commons.math3.linear.EigenDecomposition.solve_448", _mut34721, _mut34722, _mut34723, _mut34724);
                }
            }
            return new ArrayRealVector(bp, false);
        }

        /**
         * {@inheritDoc}
         */
        public RealMatrix solve(RealMatrix b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            final int m = realEigenvalues.length;
            if (ROR_not_equals(b.getRowDimension(), m, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34735, _mut34736, _mut34737, _mut34738, _mut34739)) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            final int nColB = b.getColumnDimension();
            final double[][] bp = new double[m][nColB];
            final double[] tmpCol = new double[m];
            for (int k = 0; ROR_less(k, nColB, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34768, _mut34769, _mut34770, _mut34771, _mut34772); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34740, _mut34741, _mut34742, _mut34743, _mut34744); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
                    tmpCol[i] = b.getEntry(i, k);
                    bp[i][k] = 0;
                }
                for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34763, _mut34764, _mut34765, _mut34766, _mut34767); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
                    final ArrayRealVector v = eigenvectors[i];
                    final double[] vData = v.getDataRef();
                    double s = 0;
                    for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34749, _mut34750, _mut34751, _mut34752, _mut34753); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
                        s += AOR_multiply(v.getEntry(j), tmpCol[j], "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34745, _mut34746, _mut34747, _mut34748);
                    }
                    s /= realEigenvalues[i];
                    for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34758, _mut34759, _mut34760, _mut34761, _mut34762); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.solve_472");
                        bp[j][k] += AOR_multiply(s, vData[j], "org.apache.commons.math3.linear.EigenDecomposition.solve_472", _mut34754, _mut34755, _mut34756, _mut34757);
                    }
                }
            }
            return new Array2DRowRealMatrix(bp, false);
        }

        /**
         * Checks whether the decomposed matrix is non-singular.
         *
         * @return true if the decomposed matrix is non-singular.
         */
        public boolean isNonSingular() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514");
            double largestEigenvalueNorm = 0.0;
            // order of their norm).
            for (int i = 0; ROR_less(i, realEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514", _mut34773, _mut34774, _mut34775, _mut34776, _mut34777); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514");
                largestEigenvalueNorm = FastMath.max(largestEigenvalueNorm, eigenvalueNorm(i));
            }
            // Corner case: zero matrix, all exactly 0 eigenvalues
            if (ROR_equals(largestEigenvalueNorm, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514", _mut34778, _mut34779, _mut34780, _mut34781, _mut34782)) {
                return false;
            }
            for (int i = 0; ROR_less(i, realEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514", _mut34787, _mut34788, _mut34789, _mut34790, _mut34791); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514");
                // than the largest eigenvalue to be effectively 0.
                if (Precision.equals(AOR_divide(eigenvalueNorm(i), largestEigenvalueNorm, "org.apache.commons.math3.linear.EigenDecomposition.isNonSingular_514", _mut34783, _mut34784, _mut34785, _mut34786), 0, EPSILON)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * @param i which eigenvalue to find the norm of
         * @return the norm of ith (complex) eigenvalue.
         */
        private double eigenvalueNorm(int i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.eigenvalueNorm_539");
            final double re = realEigenvalues[i];
            final double im = imagEigenvalues[i];
            return FastMath.sqrt(AOR_plus(AOR_multiply(re, re, "org.apache.commons.math3.linear.EigenDecomposition.eigenvalueNorm_539", _mut34792, _mut34793, _mut34794, _mut34795), AOR_multiply(im, im, "org.apache.commons.math3.linear.EigenDecomposition.eigenvalueNorm_539", _mut34796, _mut34797, _mut34798, _mut34799), "org.apache.commons.math3.linear.EigenDecomposition.eigenvalueNorm_539", _mut34800, _mut34801, _mut34802, _mut34803));
        }

        /**
         * Get the inverse of the decomposed matrix.
         *
         * @return the inverse matrix.
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public RealMatrix getInverse() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getInverse_551");
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            final int m = realEigenvalues.length;
            final double[][] invData = new double[m][m];
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.EigenDecomposition.getInverse_551", _mut34822, _mut34823, _mut34824, _mut34825, _mut34826); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getInverse_551");
                final double[] invI = invData[i];
                for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.linear.EigenDecomposition.getInverse_551", _mut34817, _mut34818, _mut34819, _mut34820, _mut34821); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getInverse_551");
                    double invIJ = 0;
                    for (int k = 0; ROR_less(k, m, "org.apache.commons.math3.linear.EigenDecomposition.getInverse_551", _mut34812, _mut34813, _mut34814, _mut34815, _mut34816); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.getInverse_551");
                        final double[] vK = eigenvectors[k].getDataRef();
                        invIJ += AOR_divide(AOR_multiply(vK[i], vK[j], "org.apache.commons.math3.linear.EigenDecomposition.getInverse_551", _mut34804, _mut34805, _mut34806, _mut34807), realEigenvalues[k], "org.apache.commons.math3.linear.EigenDecomposition.getInverse_551", _mut34808, _mut34809, _mut34810, _mut34811);
                    }
                    invI[j] = invIJ;
                }
            }
            return MatrixUtils.createRealMatrix(invData);
        }
    }

    /**
     * Transforms the matrix to tridiagonal form.
     *
     * @param matrix Matrix to transform.
     */
    private void transformToTridiagonal(final RealMatrix matrix) {
        // transform the matrix to tridiagonal
        transformer = new TriDiagonalTransformer(matrix);
        main = transformer.getMainDiagonalRef();
        secondary = transformer.getSecondaryDiagonalRef();
    }

    /**
     * Find eigenvalues and eigenvectors (Dubrulle et al., 1971)
     *
     * @param householderMatrix Householder matrix of the transformation
     * to tridiagonal form.
     */
    private void findEigenVectors(final double[][] householderMatrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
        final double[][] z = householderMatrix.clone();
        final int n = main.length;
        realEigenvalues = new double[n];
        imagEigenvalues = new double[n];
        final double[] e = new double[n];
        for (int i = 0; ROR_less(i, AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34827, _mut34828, _mut34829, _mut34830), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34831, _mut34832, _mut34833, _mut34834, _mut34835); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            realEigenvalues[i] = main[i];
            e[i] = secondary[i];
        }
        realEigenvalues[AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34836, _mut34837, _mut34838, _mut34839)] = main[AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34840, _mut34841, _mut34842, _mut34843)];
        e[AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34844, _mut34845, _mut34846, _mut34847)] = 0;
        // Determine the largest main and secondary value in absolute term.
        double maxAbsoluteValue = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34858, _mut34859, _mut34860, _mut34861, _mut34862); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            if (ROR_greater(FastMath.abs(realEigenvalues[i]), maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34848, _mut34849, _mut34850, _mut34851, _mut34852)) {
                maxAbsoluteValue = FastMath.abs(realEigenvalues[i]);
            }
            if (ROR_greater(FastMath.abs(e[i]), maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34853, _mut34854, _mut34855, _mut34856, _mut34857)) {
                maxAbsoluteValue = FastMath.abs(e[i]);
            }
        }
        // Make null any main and secondary value too small to be significant
        if (ROR_not_equals(maxAbsoluteValue, 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34863, _mut34864, _mut34865, _mut34866, _mut34867)) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34886, _mut34887, _mut34888, _mut34889, _mut34890); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                if (ROR_less_equals(FastMath.abs(realEigenvalues[i]), AOR_multiply(Precision.EPSILON, maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34868, _mut34869, _mut34870, _mut34871), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34872, _mut34873, _mut34874, _mut34875, _mut34876)) {
                    realEigenvalues[i] = 0;
                }
                if (ROR_less_equals(FastMath.abs(e[i]), AOR_multiply(Precision.EPSILON, maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34877, _mut34878, _mut34879, _mut34880), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34881, _mut34882, _mut34883, _mut34884, _mut34885)) {
                    e[i] = 0;
                }
            }
        }
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35168, _mut35169, _mut35170, _mut35171, _mut35172); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            int its = 0;
            int m;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                for (m = j; ROR_less(m, AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34908, _mut34909, _mut34910, _mut34911), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34912, _mut34913, _mut34914, _mut34915, _mut34916); m++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                    double delta = AOR_plus(FastMath.abs(realEigenvalues[m]), FastMath.abs(realEigenvalues[AOR_plus(m, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34891, _mut34892, _mut34893, _mut34894)]), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34895, _mut34896, _mut34897, _mut34898);
                    if (ROR_equals(AOR_plus(FastMath.abs(e[m]), delta, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34899, _mut34900, _mut34901, _mut34902), delta, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34903, _mut34904, _mut34905, _mut34906, _mut34907)) {
                        break;
                    }
                }
                if (ROR_not_equals(m, j, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34917, _mut34918, _mut34919, _mut34920, _mut34921)) {
                    if (ROR_equals(its, maxIter, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34922, _mut34923, _mut34924, _mut34925, _mut34926)) {
                        throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, maxIter);
                    }
                    its++;
                    double q = AOR_divide((AOR_minus(realEigenvalues[AOR_plus(j, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34927, _mut34928, _mut34929, _mut34930)], realEigenvalues[j], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34931, _mut34932, _mut34933, _mut34934)), (AOR_multiply(2, e[j], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34935, _mut34936, _mut34937, _mut34938)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34939, _mut34940, _mut34941, _mut34942);
                    double t = FastMath.sqrt(AOR_plus(1, AOR_multiply(q, q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34943, _mut34944, _mut34945, _mut34946), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34947, _mut34948, _mut34949, _mut34950));
                    if (ROR_less(q, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34951, _mut34952, _mut34953, _mut34954, _mut34955)) {
                        q = AOR_plus(AOR_minus(realEigenvalues[m], realEigenvalues[j], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34972, _mut34973, _mut34974, _mut34975), AOR_divide(e[j], (AOR_minus(q, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34976, _mut34977, _mut34978, _mut34979)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34980, _mut34981, _mut34982, _mut34983), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34984, _mut34985, _mut34986, _mut34987);
                    } else {
                        q = AOR_plus(AOR_minus(realEigenvalues[m], realEigenvalues[j], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34956, _mut34957, _mut34958, _mut34959), AOR_divide(e[j], (AOR_plus(q, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34960, _mut34961, _mut34962, _mut34963)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34964, _mut34965, _mut34966, _mut34967), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34968, _mut34969, _mut34970, _mut34971);
                    }
                    double u = 0.0;
                    double s = 1.0;
                    double c = 1.0;
                    int i;
                    for (i = m - 1; ROR_greater_equals(i, j, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35147, _mut35148, _mut35149, _mut35150, _mut35151); i--) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                        double p = AOR_multiply(s, e[i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34988, _mut34989, _mut34990, _mut34991);
                        double h = AOR_multiply(c, e[i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34992, _mut34993, _mut34994, _mut34995);
                        if (ROR_greater_equals(FastMath.abs(p), FastMath.abs(q), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut34996, _mut34997, _mut34998, _mut34999, _mut35000)) {
                            c = AOR_divide(q, p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35025, _mut35026, _mut35027, _mut35028);
                            t = FastMath.sqrt(AOR_plus(AOR_multiply(c, c, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35029, _mut35030, _mut35031, _mut35032), 1.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35033, _mut35034, _mut35035, _mut35036));
                            e[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35037, _mut35038, _mut35039, _mut35040)] = AOR_multiply(p, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35041, _mut35042, _mut35043, _mut35044);
                            s = AOR_divide(1.0, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35045, _mut35046, _mut35047, _mut35048);
                            c *= s;
                        } else {
                            s = AOR_divide(p, q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35001, _mut35002, _mut35003, _mut35004);
                            t = FastMath.sqrt(AOR_plus(AOR_multiply(s, s, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35005, _mut35006, _mut35007, _mut35008), 1.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35009, _mut35010, _mut35011, _mut35012));
                            e[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35013, _mut35014, _mut35015, _mut35016)] = AOR_multiply(q, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35017, _mut35018, _mut35019, _mut35020);
                            c = AOR_divide(1.0, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35021, _mut35022, _mut35023, _mut35024);
                            s *= c;
                        }
                        if (ROR_equals(e[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35049, _mut35050, _mut35051, _mut35052)], 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35053, _mut35054, _mut35055, _mut35056, _mut35057)) {
                            realEigenvalues[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35058, _mut35059, _mut35060, _mut35061)] -= u;
                            e[m] = 0.0;
                            break;
                        }
                        q = AOR_minus(realEigenvalues[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35062, _mut35063, _mut35064, _mut35065)], u, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35066, _mut35067, _mut35068, _mut35069);
                        t = AOR_plus(AOR_multiply((AOR_minus(realEigenvalues[i], q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35070, _mut35071, _mut35072, _mut35073)), s, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35074, _mut35075, _mut35076, _mut35077), AOR_multiply(AOR_multiply(2.0, c, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35078, _mut35079, _mut35080, _mut35081), h, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35082, _mut35083, _mut35084, _mut35085), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35086, _mut35087, _mut35088, _mut35089);
                        u = AOR_multiply(s, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35090, _mut35091, _mut35092, _mut35093);
                        realEigenvalues[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35094, _mut35095, _mut35096, _mut35097)] = AOR_plus(q, u, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35098, _mut35099, _mut35100, _mut35101);
                        q = AOR_minus(AOR_multiply(c, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35102, _mut35103, _mut35104, _mut35105), h, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35106, _mut35107, _mut35108, _mut35109);
                        for (int ia = 0; ROR_less(ia, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35142, _mut35143, _mut35144, _mut35145, _mut35146); ia++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                            p = z[ia][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35110, _mut35111, _mut35112, _mut35113)];
                            z[ia][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35114, _mut35115, _mut35116, _mut35117)] = AOR_plus(AOR_multiply(s, z[ia][i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35118, _mut35119, _mut35120, _mut35121), AOR_multiply(c, p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35122, _mut35123, _mut35124, _mut35125), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35126, _mut35127, _mut35128, _mut35129);
                            z[ia][i] = AOR_minus(AOR_multiply(c, z[ia][i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35130, _mut35131, _mut35132, _mut35133), AOR_multiply(s, p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35134, _mut35135, _mut35136, _mut35137), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35138, _mut35139, _mut35140, _mut35141);
                        }
                    }
                    if ((_mut35162 ? (ROR_equals(t, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35152, _mut35153, _mut35154, _mut35155, _mut35156) || ROR_greater_equals(i, j, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35157, _mut35158, _mut35159, _mut35160, _mut35161)) : (ROR_equals(t, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35152, _mut35153, _mut35154, _mut35155, _mut35156) && ROR_greater_equals(i, j, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35157, _mut35158, _mut35159, _mut35160, _mut35161)))) {
                        continue;
                    }
                    realEigenvalues[j] -= u;
                    e[j] = q;
                    e[m] = 0.0;
                }
            } while (ROR_not_equals(m, j, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35163, _mut35164, _mut35165, _mut35166, _mut35167));
        }
        // Sort the eigen values (and vectors) in increase order
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35193, _mut35194, _mut35195, _mut35196, _mut35197); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            int k = i;
            double p = realEigenvalues[i];
            for (int j = i + 1; ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35178, _mut35179, _mut35180, _mut35181, _mut35182); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                if (ROR_greater(realEigenvalues[j], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35173, _mut35174, _mut35175, _mut35176, _mut35177)) {
                    k = j;
                    p = realEigenvalues[j];
                }
            }
            if (ROR_not_equals(k, i, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35183, _mut35184, _mut35185, _mut35186, _mut35187)) {
                realEigenvalues[k] = realEigenvalues[i];
                realEigenvalues[i] = p;
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35188, _mut35189, _mut35190, _mut35191, _mut35192); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                    p = z[j][i];
                    z[j][i] = z[j][k];
                    z[j][k] = p;
                }
            }
        }
        // Determine the largest eigen value in absolute term.
        maxAbsoluteValue = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35203, _mut35204, _mut35205, _mut35206, _mut35207); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            if (ROR_greater(FastMath.abs(realEigenvalues[i]), maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35198, _mut35199, _mut35200, _mut35201, _mut35202)) {
                maxAbsoluteValue = FastMath.abs(realEigenvalues[i]);
            }
        }
        // Make null any eigen value too small to be significant
        if (ROR_not_equals(maxAbsoluteValue, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35208, _mut35209, _mut35210, _mut35211, _mut35212)) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35222, _mut35223, _mut35224, _mut35225, _mut35226); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                if (ROR_less(FastMath.abs(realEigenvalues[i]), AOR_multiply(Precision.EPSILON, maxAbsoluteValue, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35213, _mut35214, _mut35215, _mut35216), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35217, _mut35218, _mut35219, _mut35220, _mut35221)) {
                    realEigenvalues[i] = 0;
                }
            }
        }
        eigenvectors = new ArrayRealVector[n];
        final double[] tmp = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35232, _mut35233, _mut35234, _mut35235, _mut35236); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592", _mut35227, _mut35228, _mut35229, _mut35230, _mut35231); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors_592");
                tmp[j] = z[j][i];
            }
            eigenvectors[i] = new ArrayRealVector(tmp);
        }
    }

    /**
     * Transforms the matrix to Schur form and calculates the eigenvalues.
     *
     * @param matrix Matrix to transform.
     * @return the {@link SchurTransformer Shur transform} for this matrix
     */
    private SchurTransformer transformToSchur(final RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749");
        final SchurTransformer schurTransform = new SchurTransformer(matrix);
        final double[][] matT = schurTransform.getT().getData();
        realEigenvalues = new double[matT.length];
        imagEigenvalues = new double[matT.length];
        for (int i = 0; ROR_less(i, realEigenvalues.length, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35303, _mut35304, _mut35305, _mut35306, _mut35307); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749");
            if ((_mut35250 ? (ROR_equals(i, (AOR_minus(realEigenvalues.length, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35237, _mut35238, _mut35239, _mut35240)), "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35241, _mut35242, _mut35243, _mut35244, _mut35245) && Precision.equals(matT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35246, _mut35247, _mut35248, _mut35249)][i], 0.0, EPSILON)) : (ROR_equals(i, (AOR_minus(realEigenvalues.length, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35237, _mut35238, _mut35239, _mut35240)), "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35241, _mut35242, _mut35243, _mut35244, _mut35245) || Precision.equals(matT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35246, _mut35247, _mut35248, _mut35249)][i], 0.0, EPSILON)))) {
                realEigenvalues[i] = matT[i][i];
            } else {
                final double x = matT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35255, _mut35256, _mut35257, _mut35258)][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35251, _mut35252, _mut35253, _mut35254)];
                final double p = AOR_multiply(0.5, (AOR_minus(matT[i][i], x, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35259, _mut35260, _mut35261, _mut35262)), "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35263, _mut35264, _mut35265, _mut35266);
                final double z = FastMath.sqrt(FastMath.abs(AOR_plus(AOR_multiply(p, p, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35267, _mut35268, _mut35269, _mut35270), AOR_multiply(matT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35271, _mut35272, _mut35273, _mut35274)][i], matT[i][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35275, _mut35276, _mut35277, _mut35278)], "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35279, _mut35280, _mut35281, _mut35282), "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35283, _mut35284, _mut35285, _mut35286)));
                realEigenvalues[i] = AOR_plus(x, p, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35287, _mut35288, _mut35289, _mut35290);
                imagEigenvalues[i] = z;
                realEigenvalues[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35291, _mut35292, _mut35293, _mut35294)] = AOR_plus(x, p, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35295, _mut35296, _mut35297, _mut35298);
                imagEigenvalues[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.transformToSchur_749", _mut35299, _mut35300, _mut35301, _mut35302)] = -z;
                i++;
            }
        }
        return schurTransform;
    }

    /**
     * Performs a division of two complex numbers.
     *
     * @param xr real part of the first number
     * @param xi imaginary part of the first number
     * @param yr real part of the second number
     * @param yi imaginary part of the second number
     * @return result of the complex division
     */
    private Complex cdiv(final double xr, final double xi, final double yr, final double yi) {
        return new Complex(xr, xi).divide(new Complex(yr, yi));
    }

    /**
     * Find eigenvectors from a matrix transformed to Schur form.
     *
     * @param schur the schur transformation of the matrix
     * @throws MathArithmeticException if the Schur form has a norm of zero
     */
    private void findEigenVectorsFromSchur(final SchurTransformer schur) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
        final double[][] matrixT = schur.getT().getData();
        final double[][] matrixP = schur.getP().getData();
        final int n = matrixT.length;
        // compute matrix norm
        double norm = 0.0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35313, _mut35314, _mut35315, _mut35316, _mut35317); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
            for (int j = FastMath.max(i - 1, 0); ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35308, _mut35309, _mut35310, _mut35311, _mut35312); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                norm += FastMath.abs(matrixT[i][j]);
            }
        }
        // we can not handle a matrix with zero norm
        if (Precision.equals(norm, 0.0, EPSILON)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        double r = 0.0;
        double s = 0.0;
        double z = 0.0;
        for (int idx = n - 1; ROR_greater_equals(idx, 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35822, _mut35823, _mut35824, _mut35825, _mut35826); idx--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
            double p = realEigenvalues[idx];
            double q = imagEigenvalues[idx];
            if (Precision.equals(q, 0.0)) {
                // Real vector
                int l = idx;
                matrixT[idx][idx] = 1.0;
                for (int i = idx - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35817, _mut35818, _mut35819, _mut35820, _mut35821); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                    double w = AOR_minus(matrixT[i][i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35683, _mut35684, _mut35685, _mut35686);
                    r = 0.0;
                    for (int j = l; ROR_less_equals(j, idx, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35691, _mut35692, _mut35693, _mut35694, _mut35695); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                        r += AOR_multiply(matrixT[i][j], matrixT[j][idx], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35687, _mut35688, _mut35689, _mut35690);
                    }
                    if (ROR_less(Precision.compareTo(imagEigenvalues[i], 0.0, EPSILON), 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35696, _mut35697, _mut35698, _mut35699, _mut35700)) {
                        z = w;
                        s = r;
                    } else {
                        l = i;
                        if (Precision.equals(imagEigenvalues[i], 0.0)) {
                            if (ROR_not_equals(w, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35782, _mut35783, _mut35784, _mut35785, _mut35786)) {
                                matrixT[i][idx] = AOR_divide(-r, w, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35795, _mut35796, _mut35797, _mut35798);
                            } else {
                                matrixT[i][idx] = AOR_divide(-r, (AOR_multiply(Precision.EPSILON, norm, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35787, _mut35788, _mut35789, _mut35790)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35791, _mut35792, _mut35793, _mut35794);
                            }
                        } else {
                            // Solve real equations
                            double x = matrixT[i][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35701, _mut35702, _mut35703, _mut35704)];
                            double y = matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35705, _mut35706, _mut35707, _mut35708)][i];
                            q = AOR_plus(AOR_multiply((AOR_minus(realEigenvalues[i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35709, _mut35710, _mut35711, _mut35712)), (AOR_minus(realEigenvalues[i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35713, _mut35714, _mut35715, _mut35716)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35717, _mut35718, _mut35719, _mut35720), AOR_multiply(imagEigenvalues[i], imagEigenvalues[i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35721, _mut35722, _mut35723, _mut35724), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35725, _mut35726, _mut35727, _mut35728);
                            double t = AOR_divide((AOR_minus(AOR_multiply(x, s, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35729, _mut35730, _mut35731, _mut35732), AOR_multiply(z, r, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35733, _mut35734, _mut35735, _mut35736), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35737, _mut35738, _mut35739, _mut35740)), q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35741, _mut35742, _mut35743, _mut35744);
                            matrixT[i][idx] = t;
                            if (ROR_greater(FastMath.abs(x), FastMath.abs(z), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35745, _mut35746, _mut35747, _mut35748, _mut35749)) {
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35766, _mut35767, _mut35768, _mut35769)][idx] = AOR_divide((AOR_minus(-r, AOR_multiply(w, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35770, _mut35771, _mut35772, _mut35773), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35774, _mut35775, _mut35776, _mut35777)), x, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35778, _mut35779, _mut35780, _mut35781);
                            } else {
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35750, _mut35751, _mut35752, _mut35753)][idx] = AOR_divide((AOR_minus(-s, AOR_multiply(y, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35754, _mut35755, _mut35756, _mut35757), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35758, _mut35759, _mut35760, _mut35761)), z, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35762, _mut35763, _mut35764, _mut35765);
                            }
                        }
                        // Overflow control
                        double t = FastMath.abs(matrixT[i][idx]);
                        if (ROR_greater(AOR_multiply((AOR_multiply(Precision.EPSILON, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35799, _mut35800, _mut35801, _mut35802)), t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35803, _mut35804, _mut35805, _mut35806), 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35807, _mut35808, _mut35809, _mut35810, _mut35811)) {
                            for (int j = i; ROR_less_equals(j, idx, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35812, _mut35813, _mut35814, _mut35815, _mut35816); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                                matrixT[j][idx] /= t;
                            }
                        }
                    }
                }
            } else if (ROR_less(q, 0.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35318, _mut35319, _mut35320, _mut35321, _mut35322)) {
                // Complex vector
                int l = AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35323, _mut35324, _mut35325, _mut35326);
                // Last vector component imaginary so matrix is triangular
                if (ROR_greater(FastMath.abs(matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35327, _mut35328, _mut35329, _mut35330)]), FastMath.abs(matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35331, _mut35332, _mut35333, _mut35334)][idx]), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35335, _mut35336, _mut35337, _mut35338, _mut35339)) {
                    matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35372, _mut35373, _mut35374, _mut35375)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35368, _mut35369, _mut35370, _mut35371)] = AOR_divide(q, matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35376, _mut35377, _mut35378, _mut35379)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35380, _mut35381, _mut35382, _mut35383);
                    matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35384, _mut35385, _mut35386, _mut35387)][idx] = AOR_divide(-(AOR_minus(matrixT[idx][idx], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35388, _mut35389, _mut35390, _mut35391)), matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35392, _mut35393, _mut35394, _mut35395)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35396, _mut35397, _mut35398, _mut35399);
                } else {
                    final Complex result = cdiv(0.0, -matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35340, _mut35341, _mut35342, _mut35343)][idx], AOR_minus(matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35348, _mut35349, _mut35350, _mut35351)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35344, _mut35345, _mut35346, _mut35347)], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35352, _mut35353, _mut35354, _mut35355), q);
                    matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35360, _mut35361, _mut35362, _mut35363)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35356, _mut35357, _mut35358, _mut35359)] = result.getReal();
                    matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35364, _mut35365, _mut35366, _mut35367)][idx] = result.getImaginary();
                }
                matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35400, _mut35401, _mut35402, _mut35403)] = 0.0;
                matrixT[idx][idx] = 1.0;
                for (int i = idx - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35678, _mut35679, _mut35680, _mut35681, _mut35682); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                    double ra = 0.0;
                    double sa = 0.0;
                    for (int j = l; ROR_less_equals(j, idx, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35416, _mut35417, _mut35418, _mut35419, _mut35420); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                        ra += AOR_multiply(matrixT[i][j], matrixT[j][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35404, _mut35405, _mut35406, _mut35407)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35408, _mut35409, _mut35410, _mut35411);
                        sa += AOR_multiply(matrixT[i][j], matrixT[j][idx], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35412, _mut35413, _mut35414, _mut35415);
                    }
                    double w = AOR_minus(matrixT[i][i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35421, _mut35422, _mut35423, _mut35424);
                    if (ROR_less(Precision.compareTo(imagEigenvalues[i], 0.0, EPSILON), 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35425, _mut35426, _mut35427, _mut35428, _mut35429)) {
                        z = w;
                        r = ra;
                        s = sa;
                    } else {
                        l = i;
                        if (Precision.equals(imagEigenvalues[i], 0.0)) {
                            final Complex c = cdiv(-ra, -sa, w, q);
                            matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35648, _mut35649, _mut35650, _mut35651)] = c.getReal();
                            matrixT[i][idx] = c.getImaginary();
                        } else {
                            // Solve complex equations
                            double x = matrixT[i][AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35430, _mut35431, _mut35432, _mut35433)];
                            double y = matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35434, _mut35435, _mut35436, _mut35437)][i];
                            double vr = AOR_minus(AOR_plus(AOR_multiply((AOR_minus(realEigenvalues[i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35438, _mut35439, _mut35440, _mut35441)), (AOR_minus(realEigenvalues[i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35442, _mut35443, _mut35444, _mut35445)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35446, _mut35447, _mut35448, _mut35449), AOR_multiply(imagEigenvalues[i], imagEigenvalues[i], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35450, _mut35451, _mut35452, _mut35453), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35454, _mut35455, _mut35456, _mut35457), AOR_multiply(q, q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35458, _mut35459, _mut35460, _mut35461), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35462, _mut35463, _mut35464, _mut35465);
                            final double vi = AOR_multiply(AOR_multiply((AOR_minus(realEigenvalues[i], p, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35466, _mut35467, _mut35468, _mut35469)), 2.0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35470, _mut35471, _mut35472, _mut35473), q, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35474, _mut35475, _mut35476, _mut35477);
                            if ((_mut35478 ? (Precision.equals(vr, 0.0) || Precision.equals(vi, 0.0)) : (Precision.equals(vr, 0.0) && Precision.equals(vi, 0.0)))) {
                                vr = AOR_multiply(AOR_multiply(Precision.EPSILON, norm, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35479, _mut35480, _mut35481, _mut35482), (AOR_plus(AOR_plus(AOR_plus(AOR_plus(FastMath.abs(w), FastMath.abs(q), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35483, _mut35484, _mut35485, _mut35486), FastMath.abs(x), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35487, _mut35488, _mut35489, _mut35490), FastMath.abs(y), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35491, _mut35492, _mut35493, _mut35494), FastMath.abs(z), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35495, _mut35496, _mut35497, _mut35498)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35499, _mut35500, _mut35501, _mut35502);
                            }
                            final Complex c = cdiv(AOR_plus(AOR_minus(AOR_multiply(x, r, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35503, _mut35504, _mut35505, _mut35506), AOR_multiply(z, ra, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35507, _mut35508, _mut35509, _mut35510), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35511, _mut35512, _mut35513, _mut35514), AOR_multiply(q, sa, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35515, _mut35516, _mut35517, _mut35518), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35519, _mut35520, _mut35521, _mut35522), AOR_minus(AOR_minus(AOR_multiply(x, s, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35523, _mut35524, _mut35525, _mut35526), AOR_multiply(z, sa, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35527, _mut35528, _mut35529, _mut35530), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35531, _mut35532, _mut35533, _mut35534), AOR_multiply(q, ra, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35535, _mut35536, _mut35537, _mut35538), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35539, _mut35540, _mut35541, _mut35542), vr, vi);
                            matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35543, _mut35544, _mut35545, _mut35546)] = c.getReal();
                            matrixT[i][idx] = c.getImaginary();
                            if (ROR_greater(FastMath.abs(x), (AOR_plus(FastMath.abs(z), FastMath.abs(q), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35547, _mut35548, _mut35549, _mut35550)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35551, _mut35552, _mut35553, _mut35554, _mut35555)) {
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35592, _mut35593, _mut35594, _mut35595)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35588, _mut35589, _mut35590, _mut35591)] = AOR_divide((AOR_plus(AOR_minus(-ra, AOR_multiply(w, matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35596, _mut35597, _mut35598, _mut35599)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35600, _mut35601, _mut35602, _mut35603), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35604, _mut35605, _mut35606, _mut35607), AOR_multiply(q, matrixT[i][idx], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35608, _mut35609, _mut35610, _mut35611), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35612, _mut35613, _mut35614, _mut35615)), x, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35616, _mut35617, _mut35618, _mut35619);
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35620, _mut35621, _mut35622, _mut35623)][idx] = AOR_divide((AOR_minus(AOR_minus(-sa, AOR_multiply(w, matrixT[i][idx], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35624, _mut35625, _mut35626, _mut35627), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35628, _mut35629, _mut35630, _mut35631), AOR_multiply(q, matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35632, _mut35633, _mut35634, _mut35635)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35636, _mut35637, _mut35638, _mut35639), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35640, _mut35641, _mut35642, _mut35643)), x, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35644, _mut35645, _mut35646, _mut35647);
                            } else {
                                final Complex c2 = cdiv(AOR_minus(-r, AOR_multiply(y, matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35556, _mut35557, _mut35558, _mut35559)], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35560, _mut35561, _mut35562, _mut35563), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35564, _mut35565, _mut35566, _mut35567), AOR_minus(-s, AOR_multiply(y, matrixT[i][idx], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35568, _mut35569, _mut35570, _mut35571), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35572, _mut35573, _mut35574, _mut35575), z, q);
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35580, _mut35581, _mut35582, _mut35583)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35576, _mut35577, _mut35578, _mut35579)] = c2.getReal();
                                matrixT[AOR_plus(i, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35584, _mut35585, _mut35586, _mut35587)][idx] = c2.getImaginary();
                            }
                        }
                        // Overflow control
                        double t = FastMath.max(FastMath.abs(matrixT[i][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35652, _mut35653, _mut35654, _mut35655)]), FastMath.abs(matrixT[i][idx]));
                        if (ROR_greater(AOR_multiply((AOR_multiply(Precision.EPSILON, t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35656, _mut35657, _mut35658, _mut35659)), t, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35660, _mut35661, _mut35662, _mut35663), 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35664, _mut35665, _mut35666, _mut35667, _mut35668)) {
                            for (int j = i; ROR_less_equals(j, idx, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35673, _mut35674, _mut35675, _mut35676, _mut35677); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                                matrixT[j][AOR_minus(idx, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35669, _mut35670, _mut35671, _mut35672)] /= t;
                                matrixT[j][idx] /= t;
                            }
                        }
                    }
                }
            }
        }
        // Back transformation to get eigenvectors of original matrix
        for (int j = n - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35849, _mut35850, _mut35851, _mut35852, _mut35853); j--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
            for (int i = 0; ROR_less_equals(i, AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35840, _mut35841, _mut35842, _mut35843), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35844, _mut35845, _mut35846, _mut35847, _mut35848); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                z = 0.0;
                for (int k = 0; ROR_less_equals(k, FastMath.min(j, AOR_minus(n, 1, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35831, _mut35832, _mut35833, _mut35834)), "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35835, _mut35836, _mut35837, _mut35838, _mut35839); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                    z += AOR_multiply(matrixP[i][k], matrixT[k][j], "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35827, _mut35828, _mut35829, _mut35830);
                }
                matrixP[i][j] = z;
            }
        }
        eigenvectors = new ArrayRealVector[n];
        final double[] tmp = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35859, _mut35860, _mut35861, _mut35862, _mut35863); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794", _mut35854, _mut35855, _mut35856, _mut35857, _mut35858); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.EigenDecomposition.findEigenVectorsFromSchur_794");
                tmp[j] = matrixP[j][i];
            }
            eigenvectors[i] = new ArrayRealVector(tmp);
        }
    }
}
