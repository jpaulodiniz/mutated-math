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
package org.apache.commons.math3.analysis.differentiation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class holding "compiled" computation rules for derivative structures.
 * <p>This class implements the computation rules described in Dan Kalman's paper <a
 * href="http://www1.american.edu/cas/mathstat/People/kalman/pdffiles/mmgautodiff.pdf">Doubly
 * Recursive Multivariate Automatic Differentiation</a>, Mathematics Magazine, vol. 75,
 * no. 3, June 2002. However, in order to avoid performances bottlenecks, the recursive
 * rules are "compiled" once in an unfold form. This class does this recursion unrolling
 * and stores the computation rules as simple loops with pre-computed indirection arrays.</p>
 * <p>
 * This class maps all derivative computation into single dimension arrays that hold the
 * value and partial derivatives. The class does not hold these arrays, which remains under
 * the responsibility of the caller. For each combination of number of free parameters and
 * derivation order, only one compiler is necessary, and this compiler will be used to
 * perform computations on all arrays provided to it, which can represent hundreds or
 * thousands of different parameters kept together with all theur partial derivatives.
 * </p>
 * <p>
 * The arrays on which compilers operate contain only the partial derivatives together
 * with the 0<sup>th</sup> derivative, i.e. the value. The partial derivatives are stored in
 * a compiler-specific order, which can be retrieved using methods {@link
 * #getPartialDerivativeIndex(int...) getPartialDerivativeIndex} and {@link
 * #getPartialDerivativeOrders(int)}. The value is guaranteed to be stored as the first element
 * (i.e. the {@link #getPartialDerivativeIndex(int...) getPartialDerivativeIndex} method returns
 * 0 when called with 0 for all derivation orders and {@link #getPartialDerivativeOrders(int)
 * getPartialDerivativeOrders} returns an array filled with 0 when called with 0 as the index).
 * </p>
 * <p>
 * Note that the ordering changes with number of parameters and derivation order. For example
 * given 2 parameters x and y, df/dy is stored at index 2 when derivation order is set to 1 (in
 * this case the array has three elements: f, df/dx and df/dy). If derivation order is set to
 * 2, then df/dy will be stored at index 3 (in this case the array has six elements: f, df/dx,
 * df/dxdx, df/dy, df/dxdy and df/dydy).
 * </p>
 * <p>
 * Given this structure, users can perform some simple operations like adding, subtracting
 * or multiplying constants and negating the elements by themselves, knowing if they want to
 * mutate their array or create a new array. These simple operations are not provided by
 * the compiler. The compiler provides only the more complex operations between several arrays.
 * </p>
 * <p>This class is mainly used as the engine for scalar variable {@link DerivativeStructure}.
 * It can also be used directly to hold several variables in arrays for more complex data
 * structures. User can for example store a vector of n variables depending on three x, y
 * and z free parameters in one array as follows:</p> <pre>
 *   // parameter 0 is x, parameter 1 is y, parameter 2 is z
 *   int parameters = 3;
 *   DSCompiler compiler = DSCompiler.getCompiler(parameters, order);
 *   int size = compiler.getSize();
 *
 *   // pack all elements in a single array
 *   double[] array = new double[n * size];
 *   for (int i = 0; i &lt; n; ++i) {
 *
 *     // we know value is guaranteed to be the first element
 *     array[i * size] = v[i];
 *
 *     // we don't know where first derivatives are stored, so we ask the compiler
 *     array[i * size + compiler.getPartialDerivativeIndex(1, 0, 0) = dvOnDx[i][0];
 *     array[i * size + compiler.getPartialDerivativeIndex(0, 1, 0) = dvOnDy[i][0];
 *     array[i * size + compiler.getPartialDerivativeIndex(0, 0, 1) = dvOnDz[i][0];
 *
 *     // we let all higher order derivatives set to 0
 *
 *   }
 * </pre>
 * <p>Then in another function, user can perform some operations on all elements stored
 * in the single array, such as a simple product of all variables:</p> <pre>
 *   // compute the product of all elements
 *   double[] product = new double[size];
 *   prod[0] = 1.0;
 *   for (int i = 0; i &lt; n; ++i) {
 *     double[] tmp = product.clone();
 *     compiler.multiply(tmp, 0, array, i * size, product, 0);
 *   }
 *
 *   // value
 *   double p = product[0];
 *
 *   // first derivatives
 *   double dPdX = product[compiler.getPartialDerivativeIndex(1, 0, 0)];
 *   double dPdY = product[compiler.getPartialDerivativeIndex(0, 1, 0)];
 *   double dPdZ = product[compiler.getPartialDerivativeIndex(0, 0, 1)];
 *
 *   // cross derivatives (assuming order was at least 2)
 *   double dPdXdX = product[compiler.getPartialDerivativeIndex(2, 0, 0)];
 *   double dPdXdY = product[compiler.getPartialDerivativeIndex(1, 1, 0)];
 *   double dPdXdZ = product[compiler.getPartialDerivativeIndex(1, 0, 1)];
 *   double dPdYdY = product[compiler.getPartialDerivativeIndex(0, 2, 0)];
 *   double dPdYdZ = product[compiler.getPartialDerivativeIndex(0, 1, 1)];
 *   double dPdZdZ = product[compiler.getPartialDerivativeIndex(0, 0, 2)];
 * </pre>
 * @see DerivativeStructure
 * @since 3.1
 */
public class DSCompiler {

    @Conditional
    public static boolean _mut97210 = false, _mut97211 = false, _mut97212 = false, _mut97213 = false, _mut97214 = false, _mut97215 = false, _mut97216 = false, _mut97217 = false, _mut97218 = false, _mut97219 = false, _mut97220 = false, _mut97221 = false, _mut97222 = false, _mut97223 = false, _mut97224 = false, _mut97225 = false, _mut97226 = false, _mut97227 = false, _mut97228 = false, _mut97229 = false, _mut97230 = false, _mut97231 = false, _mut97232 = false, _mut97233 = false, _mut97234 = false, _mut97235 = false, _mut97236 = false, _mut97237 = false, _mut97238 = false, _mut97239 = false, _mut97240 = false, _mut97241 = false, _mut97242 = false, _mut97243 = false, _mut97244 = false, _mut97245 = false, _mut97246 = false, _mut97247 = false, _mut97248 = false, _mut97249 = false, _mut97250 = false, _mut97251 = false, _mut97252 = false, _mut97253 = false, _mut97254 = false, _mut97255 = false, _mut97256 = false, _mut97257 = false, _mut97258 = false, _mut97259 = false, _mut97260 = false, _mut97261 = false, _mut97262 = false, _mut97263 = false, _mut97264 = false, _mut97265 = false, _mut97266 = false, _mut97267 = false, _mut97268 = false, _mut97269 = false, _mut97270 = false, _mut97271 = false, _mut97272 = false, _mut97273 = false, _mut97274 = false, _mut97275 = false, _mut97276 = false, _mut97277 = false, _mut97278 = false, _mut97279 = false, _mut97280 = false, _mut97281 = false, _mut97282 = false, _mut97283 = false, _mut97284 = false, _mut97285 = false, _mut97286 = false, _mut97287 = false, _mut97288 = false, _mut97289 = false, _mut97290 = false, _mut97291 = false, _mut97292 = false, _mut97293 = false, _mut97294 = false, _mut97295 = false, _mut97296 = false, _mut97297 = false, _mut97298 = false, _mut97299 = false, _mut97300 = false, _mut97301 = false, _mut97302 = false, _mut97303 = false, _mut97304 = false, _mut97305 = false, _mut97306 = false, _mut97307 = false, _mut97308 = false, _mut97309 = false, _mut97310 = false, _mut97311 = false, _mut97312 = false, _mut97313 = false, _mut97314 = false, _mut97315 = false, _mut97316 = false, _mut97317 = false, _mut97318 = false, _mut97319 = false, _mut97320 = false, _mut97321 = false, _mut97322 = false, _mut97323 = false, _mut97324 = false, _mut97325 = false, _mut97326 = false, _mut97327 = false, _mut97328 = false, _mut97329 = false, _mut97330 = false, _mut97331 = false, _mut97332 = false, _mut97333 = false, _mut97334 = false, _mut97335 = false, _mut97336 = false, _mut97337 = false, _mut97338 = false, _mut97339 = false, _mut97340 = false, _mut97341 = false, _mut97342 = false, _mut97343 = false, _mut97344 = false, _mut97345 = false, _mut97346 = false, _mut97347 = false, _mut97348 = false, _mut97349 = false, _mut97350 = false, _mut97351 = false, _mut97352 = false, _mut97353 = false, _mut97354 = false, _mut97355 = false, _mut97356 = false, _mut97357 = false, _mut97358 = false, _mut97359 = false, _mut97360 = false, _mut97361 = false, _mut97362 = false, _mut97363 = false, _mut97364 = false, _mut97365 = false, _mut97366 = false, _mut97367 = false, _mut97368 = false, _mut97369 = false, _mut97370 = false, _mut97371 = false, _mut97372 = false, _mut97373 = false, _mut97374 = false, _mut97375 = false, _mut97376 = false, _mut97377 = false, _mut97378 = false, _mut97379 = false, _mut97380 = false, _mut97381 = false, _mut97382 = false, _mut97383 = false, _mut97384 = false, _mut97385 = false, _mut97386 = false, _mut97387 = false, _mut97388 = false, _mut97389 = false, _mut97390 = false, _mut97391 = false, _mut97392 = false, _mut97393 = false, _mut97394 = false, _mut97395 = false, _mut97396 = false, _mut97397 = false, _mut97398 = false, _mut97399 = false, _mut97400 = false, _mut97401 = false, _mut97402 = false, _mut97403 = false, _mut97404 = false, _mut97405 = false, _mut97406 = false, _mut97407 = false, _mut97408 = false, _mut97409 = false, _mut97410 = false, _mut97411 = false, _mut97412 = false, _mut97413 = false, _mut97414 = false, _mut97415 = false, _mut97416 = false, _mut97417 = false, _mut97418 = false, _mut97419 = false, _mut97420 = false, _mut97421 = false, _mut97422 = false, _mut97423 = false, _mut97424 = false, _mut97425 = false, _mut97426 = false, _mut97427 = false, _mut97428 = false, _mut97429 = false, _mut97430 = false, _mut97431 = false, _mut97432 = false, _mut97433 = false, _mut97434 = false, _mut97435 = false, _mut97436 = false, _mut97437 = false, _mut97438 = false, _mut97439 = false, _mut97440 = false, _mut97441 = false, _mut97442 = false, _mut97443 = false, _mut97444 = false, _mut97445 = false, _mut97446 = false, _mut97447 = false, _mut97448 = false, _mut97449 = false, _mut97450 = false, _mut97451 = false, _mut97452 = false, _mut97453 = false, _mut97454 = false, _mut97455 = false, _mut97456 = false, _mut97457 = false, _mut97458 = false, _mut97459 = false, _mut97460 = false, _mut97461 = false, _mut97462 = false, _mut97463 = false, _mut97464 = false, _mut97465 = false, _mut97466 = false, _mut97467 = false, _mut97468 = false, _mut97469 = false, _mut97470 = false, _mut97471 = false, _mut97472 = false, _mut97473 = false, _mut97474 = false, _mut97475 = false, _mut97476 = false, _mut97477 = false, _mut97478 = false, _mut97479 = false, _mut97480 = false, _mut97481 = false, _mut97482 = false, _mut97483 = false, _mut97484 = false, _mut97485 = false, _mut97486 = false, _mut97487 = false, _mut97488 = false, _mut97489 = false, _mut97490 = false, _mut97491 = false, _mut97492 = false, _mut97493 = false, _mut97494 = false, _mut97495 = false, _mut97496 = false, _mut97497 = false, _mut97498 = false, _mut97499 = false, _mut97500 = false, _mut97501 = false, _mut97502 = false, _mut97503 = false, _mut97504 = false, _mut97505 = false, _mut97506 = false, _mut97507 = false, _mut97508 = false, _mut97509 = false, _mut97510 = false, _mut97511 = false, _mut97512 = false, _mut97513 = false, _mut97514 = false, _mut97515 = false, _mut97516 = false, _mut97517 = false, _mut97518 = false, _mut97519 = false, _mut97520 = false, _mut97521 = false, _mut97522 = false, _mut97523 = false, _mut97524 = false, _mut97525 = false, _mut97526 = false, _mut97527 = false, _mut97528 = false, _mut97529 = false, _mut97530 = false, _mut97531 = false, _mut97532 = false, _mut97533 = false, _mut97534 = false, _mut97535 = false, _mut97536 = false, _mut97537 = false, _mut97538 = false, _mut97539 = false, _mut97540 = false, _mut97541 = false, _mut97542 = false, _mut97543 = false, _mut97544 = false, _mut97545 = false, _mut97546 = false, _mut97547 = false, _mut97548 = false, _mut97549 = false, _mut97550 = false, _mut97551 = false, _mut97552 = false, _mut97553 = false, _mut97554 = false, _mut97555 = false, _mut97556 = false, _mut97557 = false, _mut97558 = false, _mut97559 = false, _mut97560 = false, _mut97561 = false, _mut97562 = false, _mut97563 = false, _mut97564 = false, _mut97565 = false, _mut97566 = false, _mut97567 = false, _mut97568 = false, _mut97569 = false, _mut97570 = false, _mut97571 = false, _mut97572 = false, _mut97573 = false, _mut97574 = false, _mut97575 = false, _mut97576 = false, _mut97577 = false, _mut97578 = false, _mut97579 = false, _mut97580 = false, _mut97581 = false, _mut97582 = false, _mut97583 = false, _mut97584 = false, _mut97585 = false, _mut97586 = false, _mut97587 = false, _mut97588 = false, _mut97589 = false, _mut97590 = false, _mut97591 = false, _mut97592 = false, _mut97593 = false, _mut97594 = false, _mut97595 = false, _mut97596 = false, _mut97597 = false, _mut97598 = false, _mut97599 = false, _mut97600 = false, _mut97601 = false, _mut97602 = false, _mut97603 = false, _mut97604 = false, _mut97605 = false, _mut97606 = false, _mut97607 = false, _mut97608 = false, _mut97609 = false, _mut97610 = false, _mut97611 = false, _mut97612 = false, _mut97613 = false, _mut97614 = false, _mut97615 = false, _mut97616 = false, _mut97617 = false, _mut97618 = false, _mut97619 = false, _mut97620 = false, _mut97621 = false, _mut97622 = false, _mut97623 = false, _mut97624 = false, _mut97625 = false, _mut97626 = false, _mut97627 = false, _mut97628 = false, _mut97629 = false, _mut97630 = false, _mut97631 = false, _mut97632 = false, _mut97633 = false, _mut97634 = false, _mut97635 = false, _mut97636 = false, _mut97637 = false, _mut97638 = false, _mut97639 = false, _mut97640 = false, _mut97641 = false, _mut97642 = false, _mut97643 = false, _mut97644 = false, _mut97645 = false, _mut97646 = false, _mut97647 = false, _mut97648 = false, _mut97649 = false, _mut97650 = false, _mut97651 = false, _mut97652 = false, _mut97653 = false, _mut97654 = false, _mut97655 = false, _mut97656 = false, _mut97657 = false, _mut97658 = false, _mut97659 = false, _mut97660 = false, _mut97661 = false, _mut97662 = false, _mut97663 = false, _mut97664 = false, _mut97665 = false, _mut97666 = false, _mut97667 = false, _mut97668 = false, _mut97669 = false, _mut97670 = false, _mut97671 = false, _mut97672 = false, _mut97673 = false, _mut97674 = false, _mut97675 = false, _mut97676 = false, _mut97677 = false, _mut97678 = false, _mut97679 = false, _mut97680 = false, _mut97681 = false, _mut97682 = false, _mut97683 = false, _mut97684 = false, _mut97685 = false, _mut97686 = false, _mut97687 = false, _mut97688 = false, _mut97689 = false, _mut97690 = false, _mut97691 = false, _mut97692 = false, _mut97693 = false, _mut97694 = false, _mut97695 = false, _mut97696 = false, _mut97697 = false, _mut97698 = false, _mut97699 = false, _mut97700 = false, _mut97701 = false, _mut97702 = false, _mut97703 = false, _mut97704 = false, _mut97705 = false, _mut97706 = false, _mut97707 = false, _mut97708 = false, _mut97709 = false, _mut97710 = false, _mut97711 = false, _mut97712 = false, _mut97713 = false, _mut97714 = false, _mut97715 = false, _mut97716 = false, _mut97717 = false, _mut97718 = false, _mut97719 = false, _mut97720 = false, _mut97721 = false, _mut97722 = false, _mut97723 = false, _mut97724 = false, _mut97725 = false, _mut97726 = false, _mut97727 = false, _mut97728 = false, _mut97729 = false, _mut97730 = false, _mut97731 = false, _mut97732 = false, _mut97733 = false, _mut97734 = false, _mut97735 = false, _mut97736 = false, _mut97737 = false, _mut97738 = false, _mut97739 = false, _mut97740 = false, _mut97741 = false, _mut97742 = false, _mut97743 = false, _mut97744 = false, _mut97745 = false, _mut97746 = false, _mut97747 = false, _mut97748 = false, _mut97749 = false, _mut97750 = false, _mut97751 = false, _mut97752 = false, _mut97753 = false, _mut97754 = false, _mut97755 = false, _mut97756 = false, _mut97757 = false, _mut97758 = false, _mut97759 = false, _mut97760 = false, _mut97761 = false, _mut97762 = false, _mut97763 = false, _mut97764 = false, _mut97765 = false, _mut97766 = false, _mut97767 = false, _mut97768 = false, _mut97769 = false, _mut97770 = false, _mut97771 = false, _mut97772 = false, _mut97773 = false, _mut97774 = false, _mut97775 = false, _mut97776 = false, _mut97777 = false, _mut97778 = false, _mut97779 = false, _mut97780 = false, _mut97781 = false, _mut97782 = false, _mut97783 = false, _mut97784 = false, _mut97785 = false, _mut97786 = false, _mut97787 = false, _mut97788 = false, _mut97789 = false, _mut97790 = false, _mut97791 = false, _mut97792 = false, _mut97793 = false, _mut97794 = false, _mut97795 = false, _mut97796 = false, _mut97797 = false, _mut97798 = false, _mut97799 = false, _mut97800 = false, _mut97801 = false, _mut97802 = false, _mut97803 = false, _mut97804 = false, _mut97805 = false, _mut97806 = false, _mut97807 = false, _mut97808 = false, _mut97809 = false, _mut97810 = false, _mut97811 = false, _mut97812 = false, _mut97813 = false, _mut97814 = false, _mut97815 = false, _mut97816 = false, _mut97817 = false, _mut97818 = false, _mut97819 = false, _mut97820 = false, _mut97821 = false, _mut97822 = false, _mut97823 = false, _mut97824 = false, _mut97825 = false, _mut97826 = false, _mut97827 = false, _mut97828 = false, _mut97829 = false, _mut97830 = false, _mut97831 = false, _mut97832 = false, _mut97833 = false, _mut97834 = false, _mut97835 = false, _mut97836 = false, _mut97837 = false, _mut97838 = false, _mut97839 = false, _mut97840 = false, _mut97841 = false, _mut97842 = false, _mut97843 = false, _mut97844 = false, _mut97845 = false, _mut97846 = false, _mut97847 = false, _mut97848 = false, _mut97849 = false, _mut97850 = false, _mut97851 = false, _mut97852 = false, _mut97853 = false, _mut97854 = false, _mut97855 = false, _mut97856 = false, _mut97857 = false, _mut97858 = false, _mut97859 = false, _mut97860 = false, _mut97861 = false, _mut97862 = false, _mut97863 = false, _mut97864 = false, _mut97865 = false, _mut97866 = false, _mut97867 = false, _mut97868 = false, _mut97869 = false, _mut97870 = false, _mut97871 = false, _mut97872 = false, _mut97873 = false, _mut97874 = false, _mut97875 = false, _mut97876 = false, _mut97877 = false, _mut97878 = false, _mut97879 = false, _mut97880 = false, _mut97881 = false, _mut97882 = false, _mut97883 = false, _mut97884 = false, _mut97885 = false, _mut97886 = false, _mut97887 = false, _mut97888 = false, _mut97889 = false, _mut97890 = false, _mut97891 = false, _mut97892 = false, _mut97893 = false, _mut97894 = false, _mut97895 = false, _mut97896 = false, _mut97897 = false, _mut97898 = false, _mut97899 = false, _mut97900 = false, _mut97901 = false, _mut97902 = false, _mut97903 = false, _mut97904 = false, _mut97905 = false, _mut97906 = false, _mut97907 = false, _mut97908 = false, _mut97909 = false, _mut97910 = false, _mut97911 = false, _mut97912 = false, _mut97913 = false, _mut97914 = false, _mut97915 = false, _mut97916 = false, _mut97917 = false, _mut97918 = false, _mut97919 = false, _mut97920 = false, _mut97921 = false, _mut97922 = false, _mut97923 = false, _mut97924 = false, _mut97925 = false, _mut97926 = false, _mut97927 = false, _mut97928 = false, _mut97929 = false, _mut97930 = false, _mut97931 = false, _mut97932 = false, _mut97933 = false, _mut97934 = false, _mut97935 = false, _mut97936 = false, _mut97937 = false, _mut97938 = false, _mut97939 = false, _mut97940 = false, _mut97941 = false, _mut97942 = false, _mut97943 = false, _mut97944 = false, _mut97945 = false, _mut97946 = false, _mut97947 = false, _mut97948 = false, _mut97949 = false, _mut97950 = false, _mut97951 = false, _mut97952 = false, _mut97953 = false, _mut97954 = false, _mut97955 = false, _mut97956 = false, _mut97957 = false, _mut97958 = false, _mut97959 = false, _mut97960 = false, _mut97961 = false, _mut97962 = false, _mut97963 = false, _mut97964 = false, _mut97965 = false, _mut97966 = false, _mut97967 = false, _mut97968 = false, _mut97969 = false, _mut97970 = false, _mut97971 = false, _mut97972 = false, _mut97973 = false, _mut97974 = false, _mut97975 = false, _mut97976 = false, _mut97977 = false, _mut97978 = false, _mut97979 = false, _mut97980 = false, _mut97981 = false, _mut97982 = false, _mut97983 = false, _mut97984 = false, _mut97985 = false, _mut97986 = false, _mut97987 = false, _mut97988 = false, _mut97989 = false, _mut97990 = false, _mut97991 = false, _mut97992 = false, _mut97993 = false, _mut97994 = false, _mut97995 = false, _mut97996 = false, _mut97997 = false, _mut97998 = false, _mut97999 = false, _mut98000 = false, _mut98001 = false, _mut98002 = false, _mut98003 = false, _mut98004 = false, _mut98005 = false, _mut98006 = false, _mut98007 = false, _mut98008 = false, _mut98009 = false, _mut98010 = false, _mut98011 = false, _mut98012 = false, _mut98013 = false, _mut98014 = false, _mut98015 = false, _mut98016 = false, _mut98017 = false, _mut98018 = false, _mut98019 = false, _mut98020 = false, _mut98021 = false, _mut98022 = false, _mut98023 = false, _mut98024 = false, _mut98025 = false, _mut98026 = false, _mut98027 = false, _mut98028 = false, _mut98029 = false, _mut98030 = false, _mut98031 = false, _mut98032 = false, _mut98033 = false, _mut98034 = false, _mut98035 = false, _mut98036 = false, _mut98037 = false, _mut98038 = false, _mut98039 = false, _mut98040 = false, _mut98041 = false, _mut98042 = false, _mut98043 = false, _mut98044 = false, _mut98045 = false, _mut98046 = false, _mut98047 = false, _mut98048 = false, _mut98049 = false, _mut98050 = false, _mut98051 = false, _mut98052 = false, _mut98053 = false, _mut98054 = false, _mut98055 = false, _mut98056 = false, _mut98057 = false, _mut98058 = false, _mut98059 = false, _mut98060 = false, _mut98061 = false, _mut98062 = false, _mut98063 = false, _mut98064 = false, _mut98065 = false, _mut98066 = false, _mut98067 = false, _mut98068 = false, _mut98069 = false, _mut98070 = false, _mut98071 = false, _mut98072 = false, _mut98073 = false, _mut98074 = false, _mut98075 = false, _mut98076 = false, _mut98077 = false, _mut98078 = false, _mut98079 = false, _mut98080 = false, _mut98081 = false, _mut98082 = false, _mut98083 = false, _mut98084 = false, _mut98085 = false, _mut98086 = false, _mut98087 = false, _mut98088 = false, _mut98089 = false, _mut98090 = false, _mut98091 = false, _mut98092 = false, _mut98093 = false, _mut98094 = false, _mut98095 = false, _mut98096 = false, _mut98097 = false, _mut98098 = false, _mut98099 = false, _mut98100 = false, _mut98101 = false, _mut98102 = false, _mut98103 = false, _mut98104 = false, _mut98105 = false, _mut98106 = false, _mut98107 = false, _mut98108 = false, _mut98109 = false, _mut98110 = false, _mut98111 = false, _mut98112 = false, _mut98113 = false, _mut98114 = false, _mut98115 = false, _mut98116 = false, _mut98117 = false, _mut98118 = false, _mut98119 = false, _mut98120 = false, _mut98121 = false, _mut98122 = false, _mut98123 = false, _mut98124 = false, _mut98125 = false, _mut98126 = false, _mut98127 = false, _mut98128 = false, _mut98129 = false, _mut98130 = false, _mut98131 = false, _mut98132 = false, _mut98133 = false, _mut98134 = false, _mut98135 = false, _mut98136 = false, _mut98137 = false, _mut98138 = false, _mut98139 = false, _mut98140 = false, _mut98141 = false, _mut98142 = false, _mut98143 = false, _mut98144 = false, _mut98145 = false, _mut98146 = false, _mut98147 = false, _mut98148 = false, _mut98149 = false, _mut98150 = false, _mut98151 = false, _mut98152 = false, _mut98153 = false, _mut98154 = false, _mut98155 = false, _mut98156 = false, _mut98157 = false, _mut98158 = false, _mut98159 = false, _mut98160 = false, _mut98161 = false, _mut98162 = false, _mut98163 = false, _mut98164 = false, _mut98165 = false, _mut98166 = false, _mut98167 = false, _mut98168 = false, _mut98169 = false, _mut98170 = false, _mut98171 = false, _mut98172 = false, _mut98173 = false, _mut98174 = false, _mut98175 = false, _mut98176 = false, _mut98177 = false, _mut98178 = false, _mut98179 = false, _mut98180 = false, _mut98181 = false, _mut98182 = false, _mut98183 = false, _mut98184 = false, _mut98185 = false, _mut98186 = false, _mut98187 = false, _mut98188 = false, _mut98189 = false, _mut98190 = false, _mut98191 = false, _mut98192 = false, _mut98193 = false, _mut98194 = false, _mut98195 = false, _mut98196 = false, _mut98197 = false, _mut98198 = false, _mut98199 = false, _mut98200 = false, _mut98201 = false, _mut98202 = false, _mut98203 = false, _mut98204 = false, _mut98205 = false, _mut98206 = false, _mut98207 = false, _mut98208 = false, _mut98209 = false, _mut98210 = false, _mut98211 = false, _mut98212 = false, _mut98213 = false, _mut98214 = false, _mut98215 = false, _mut98216 = false, _mut98217 = false, _mut98218 = false, _mut98219 = false, _mut98220 = false, _mut98221 = false, _mut98222 = false, _mut98223 = false, _mut98224 = false, _mut98225 = false, _mut98226 = false, _mut98227 = false, _mut98228 = false, _mut98229 = false, _mut98230 = false, _mut98231 = false, _mut98232 = false, _mut98233 = false, _mut98234 = false, _mut98235 = false, _mut98236 = false, _mut98237 = false, _mut98238 = false, _mut98239 = false, _mut98240 = false, _mut98241 = false, _mut98242 = false, _mut98243 = false, _mut98244 = false, _mut98245 = false, _mut98246 = false, _mut98247 = false, _mut98248 = false, _mut98249 = false, _mut98250 = false, _mut98251 = false, _mut98252 = false, _mut98253 = false, _mut98254 = false, _mut98255 = false, _mut98256 = false, _mut98257 = false, _mut98258 = false, _mut98259 = false, _mut98260 = false, _mut98261 = false, _mut98262 = false, _mut98263 = false, _mut98264 = false, _mut98265 = false, _mut98266 = false, _mut98267 = false, _mut98268 = false, _mut98269 = false, _mut98270 = false, _mut98271 = false, _mut98272 = false, _mut98273 = false, _mut98274 = false, _mut98275 = false, _mut98276 = false, _mut98277 = false, _mut98278 = false, _mut98279 = false, _mut98280 = false, _mut98281 = false, _mut98282 = false, _mut98283 = false, _mut98284 = false, _mut98285 = false, _mut98286 = false, _mut98287 = false, _mut98288 = false, _mut98289 = false, _mut98290 = false, _mut98291 = false, _mut98292 = false, _mut98293 = false, _mut98294 = false, _mut98295 = false, _mut98296 = false, _mut98297 = false, _mut98298 = false, _mut98299 = false, _mut98300 = false, _mut98301 = false, _mut98302 = false, _mut98303 = false, _mut98304 = false, _mut98305 = false, _mut98306 = false, _mut98307 = false, _mut98308 = false, _mut98309 = false, _mut98310 = false, _mut98311 = false, _mut98312 = false, _mut98313 = false, _mut98314 = false, _mut98315 = false, _mut98316 = false, _mut98317 = false, _mut98318 = false, _mut98319 = false, _mut98320 = false, _mut98321 = false, _mut98322 = false, _mut98323 = false, _mut98324 = false, _mut98325 = false, _mut98326 = false, _mut98327 = false, _mut98328 = false, _mut98329 = false, _mut98330 = false, _mut98331 = false, _mut98332 = false, _mut98333 = false, _mut98334 = false, _mut98335 = false, _mut98336 = false, _mut98337 = false, _mut98338 = false, _mut98339 = false, _mut98340 = false, _mut98341 = false, _mut98342 = false, _mut98343 = false, _mut98344 = false, _mut98345 = false, _mut98346 = false, _mut98347 = false, _mut98348 = false, _mut98349 = false, _mut98350 = false, _mut98351 = false, _mut98352 = false, _mut98353 = false, _mut98354 = false, _mut98355 = false, _mut98356 = false, _mut98357 = false, _mut98358 = false, _mut98359 = false, _mut98360 = false, _mut98361 = false, _mut98362 = false, _mut98363 = false, _mut98364 = false, _mut98365 = false, _mut98366 = false, _mut98367 = false, _mut98368 = false, _mut98369 = false, _mut98370 = false, _mut98371 = false, _mut98372 = false, _mut98373 = false, _mut98374 = false, _mut98375 = false, _mut98376 = false, _mut98377 = false, _mut98378 = false, _mut98379 = false, _mut98380 = false, _mut98381 = false, _mut98382 = false, _mut98383 = false, _mut98384 = false, _mut98385 = false, _mut98386 = false, _mut98387 = false, _mut98388 = false, _mut98389 = false, _mut98390 = false, _mut98391 = false, _mut98392 = false, _mut98393 = false, _mut98394 = false, _mut98395 = false, _mut98396 = false, _mut98397 = false, _mut98398 = false, _mut98399 = false, _mut98400 = false, _mut98401 = false, _mut98402 = false, _mut98403 = false, _mut98404 = false, _mut98405 = false, _mut98406 = false, _mut98407 = false, _mut98408 = false, _mut98409 = false, _mut98410 = false, _mut98411 = false, _mut98412 = false, _mut98413 = false, _mut98414 = false, _mut98415 = false, _mut98416 = false, _mut98417 = false, _mut98418 = false, _mut98419 = false, _mut98420 = false, _mut98421 = false, _mut98422 = false, _mut98423 = false, _mut98424 = false, _mut98425 = false, _mut98426 = false, _mut98427 = false, _mut98428 = false, _mut98429 = false, _mut98430 = false, _mut98431 = false, _mut98432 = false, _mut98433 = false, _mut98434 = false, _mut98435 = false, _mut98436 = false, _mut98437 = false, _mut98438 = false, _mut98439 = false, _mut98440 = false, _mut98441 = false, _mut98442 = false, _mut98443 = false, _mut98444 = false, _mut98445 = false, _mut98446 = false, _mut98447 = false, _mut98448 = false, _mut98449 = false, _mut98450 = false, _mut98451 = false, _mut98452 = false, _mut98453 = false, _mut98454 = false, _mut98455 = false, _mut98456 = false, _mut98457 = false, _mut98458 = false, _mut98459 = false, _mut98460 = false, _mut98461 = false, _mut98462 = false, _mut98463 = false, _mut98464 = false, _mut98465 = false, _mut98466 = false, _mut98467 = false, _mut98468 = false, _mut98469 = false, _mut98470 = false, _mut98471 = false, _mut98472 = false, _mut98473 = false, _mut98474 = false, _mut98475 = false, _mut98476 = false, _mut98477 = false, _mut98478 = false, _mut98479 = false, _mut98480 = false, _mut98481 = false, _mut98482 = false, _mut98483 = false, _mut98484 = false, _mut98485 = false, _mut98486 = false, _mut98487 = false, _mut98488 = false, _mut98489 = false, _mut98490 = false, _mut98491 = false, _mut98492 = false, _mut98493 = false, _mut98494 = false, _mut98495 = false, _mut98496 = false, _mut98497 = false, _mut98498 = false, _mut98499 = false, _mut98500 = false, _mut98501 = false, _mut98502 = false, _mut98503 = false, _mut98504 = false, _mut98505 = false, _mut98506 = false, _mut98507 = false, _mut98508 = false, _mut98509 = false, _mut98510 = false, _mut98511 = false, _mut98512 = false, _mut98513 = false, _mut98514 = false, _mut98515 = false, _mut98516 = false, _mut98517 = false, _mut98518 = false, _mut98519 = false, _mut98520 = false, _mut98521 = false, _mut98522 = false, _mut98523 = false, _mut98524 = false, _mut98525 = false, _mut98526 = false, _mut98527 = false, _mut98528 = false, _mut98529 = false, _mut98530 = false, _mut98531 = false, _mut98532 = false, _mut98533 = false, _mut98534 = false, _mut98535 = false, _mut98536 = false, _mut98537 = false, _mut98538 = false, _mut98539 = false, _mut98540 = false, _mut98541 = false, _mut98542 = false, _mut98543 = false, _mut98544 = false, _mut98545 = false, _mut98546 = false, _mut98547 = false, _mut98548 = false, _mut98549 = false, _mut98550 = false, _mut98551 = false, _mut98552 = false, _mut98553 = false, _mut98554 = false, _mut98555 = false, _mut98556 = false, _mut98557 = false, _mut98558 = false, _mut98559 = false, _mut98560 = false, _mut98561 = false, _mut98562 = false, _mut98563 = false, _mut98564 = false, _mut98565 = false, _mut98566 = false, _mut98567 = false, _mut98568 = false, _mut98569 = false, _mut98570 = false, _mut98571 = false, _mut98572 = false, _mut98573 = false, _mut98574 = false, _mut98575 = false, _mut98576 = false, _mut98577 = false, _mut98578 = false, _mut98579 = false, _mut98580 = false, _mut98581 = false, _mut98582 = false, _mut98583 = false, _mut98584 = false, _mut98585 = false, _mut98586 = false, _mut98587 = false, _mut98588 = false, _mut98589 = false, _mut98590 = false, _mut98591 = false, _mut98592 = false, _mut98593 = false, _mut98594 = false, _mut98595 = false, _mut98596 = false, _mut98597 = false, _mut98598 = false, _mut98599 = false, _mut98600 = false, _mut98601 = false, _mut98602 = false, _mut98603 = false, _mut98604 = false, _mut98605 = false, _mut98606 = false, _mut98607 = false, _mut98608 = false, _mut98609 = false, _mut98610 = false, _mut98611 = false, _mut98612 = false, _mut98613 = false, _mut98614 = false, _mut98615 = false, _mut98616 = false, _mut98617 = false, _mut98618 = false, _mut98619 = false, _mut98620 = false, _mut98621 = false, _mut98622 = false, _mut98623 = false, _mut98624 = false, _mut98625 = false, _mut98626 = false, _mut98627 = false, _mut98628 = false, _mut98629 = false, _mut98630 = false, _mut98631 = false, _mut98632 = false, _mut98633 = false, _mut98634 = false, _mut98635 = false, _mut98636 = false, _mut98637 = false, _mut98638 = false, _mut98639 = false, _mut98640 = false, _mut98641 = false, _mut98642 = false, _mut98643 = false, _mut98644 = false, _mut98645 = false, _mut98646 = false, _mut98647 = false, _mut98648 = false, _mut98649 = false, _mut98650 = false, _mut98651 = false, _mut98652 = false, _mut98653 = false, _mut98654 = false, _mut98655 = false, _mut98656 = false, _mut98657 = false, _mut98658 = false, _mut98659 = false, _mut98660 = false, _mut98661 = false, _mut98662 = false, _mut98663 = false, _mut98664 = false, _mut98665 = false, _mut98666 = false, _mut98667 = false, _mut98668 = false, _mut98669 = false, _mut98670 = false, _mut98671 = false, _mut98672 = false, _mut98673 = false, _mut98674 = false, _mut98675 = false, _mut98676 = false, _mut98677 = false, _mut98678 = false, _mut98679 = false, _mut98680 = false, _mut98681 = false, _mut98682 = false, _mut98683 = false, _mut98684 = false, _mut98685 = false, _mut98686 = false, _mut98687 = false, _mut98688 = false, _mut98689 = false, _mut98690 = false, _mut98691 = false, _mut98692 = false, _mut98693 = false, _mut98694 = false, _mut98695 = false, _mut98696 = false, _mut98697 = false, _mut98698 = false, _mut98699 = false, _mut98700 = false, _mut98701 = false, _mut98702 = false, _mut98703 = false, _mut98704 = false, _mut98705 = false, _mut98706 = false, _mut98707 = false, _mut98708 = false, _mut98709 = false, _mut98710 = false, _mut98711 = false, _mut98712 = false, _mut98713 = false, _mut98714 = false, _mut98715 = false, _mut98716 = false, _mut98717 = false, _mut98718 = false, _mut98719 = false, _mut98720 = false, _mut98721 = false, _mut98722 = false, _mut98723 = false, _mut98724 = false, _mut98725 = false, _mut98726 = false, _mut98727 = false, _mut98728 = false, _mut98729 = false, _mut98730 = false, _mut98731 = false, _mut98732 = false, _mut98733 = false, _mut98734 = false, _mut98735 = false, _mut98736 = false, _mut98737 = false, _mut98738 = false, _mut98739 = false, _mut98740 = false, _mut98741 = false, _mut98742 = false, _mut98743 = false, _mut98744 = false, _mut98745 = false, _mut98746 = false, _mut98747 = false, _mut98748 = false, _mut98749 = false, _mut98750 = false, _mut98751 = false, _mut98752 = false, _mut98753 = false, _mut98754 = false, _mut98755 = false, _mut98756 = false, _mut98757 = false, _mut98758 = false, _mut98759 = false, _mut98760 = false, _mut98761 = false, _mut98762 = false, _mut98763 = false, _mut98764 = false, _mut98765 = false, _mut98766 = false, _mut98767 = false, _mut98768 = false, _mut98769 = false, _mut98770 = false, _mut98771 = false, _mut98772 = false, _mut98773 = false, _mut98774 = false, _mut98775 = false, _mut98776 = false, _mut98777 = false, _mut98778 = false, _mut98779 = false, _mut98780 = false, _mut98781 = false, _mut98782 = false, _mut98783 = false, _mut98784 = false, _mut98785 = false, _mut98786 = false, _mut98787 = false, _mut98788 = false, _mut98789 = false, _mut98790 = false, _mut98791 = false, _mut98792 = false, _mut98793 = false, _mut98794 = false, _mut98795 = false, _mut98796 = false, _mut98797 = false, _mut98798 = false, _mut98799 = false, _mut98800 = false, _mut98801 = false, _mut98802 = false, _mut98803 = false, _mut98804 = false, _mut98805 = false, _mut98806 = false, _mut98807 = false, _mut98808 = false, _mut98809 = false, _mut98810 = false, _mut98811 = false, _mut98812 = false, _mut98813 = false, _mut98814 = false, _mut98815 = false, _mut98816 = false, _mut98817 = false, _mut98818 = false, _mut98819 = false, _mut98820 = false, _mut98821 = false, _mut98822 = false, _mut98823 = false, _mut98824 = false, _mut98825 = false, _mut98826 = false, _mut98827 = false, _mut98828 = false, _mut98829 = false, _mut98830 = false, _mut98831 = false, _mut98832 = false, _mut98833 = false, _mut98834 = false, _mut98835 = false, _mut98836 = false, _mut98837 = false, _mut98838 = false, _mut98839 = false, _mut98840 = false, _mut98841 = false, _mut98842 = false, _mut98843 = false, _mut98844 = false, _mut98845 = false, _mut98846 = false, _mut98847 = false, _mut98848 = false, _mut98849 = false, _mut98850 = false, _mut98851 = false, _mut98852 = false, _mut98853 = false, _mut98854 = false, _mut98855 = false, _mut98856 = false, _mut98857 = false, _mut98858 = false, _mut98859 = false, _mut98860 = false, _mut98861 = false, _mut98862 = false, _mut98863 = false, _mut98864 = false, _mut98865 = false, _mut98866 = false, _mut98867 = false, _mut98868 = false, _mut98869 = false, _mut98870 = false, _mut98871 = false, _mut98872 = false, _mut98873 = false, _mut98874 = false, _mut98875 = false, _mut98876 = false, _mut98877 = false, _mut98878 = false, _mut98879 = false, _mut98880 = false, _mut98881 = false, _mut98882 = false, _mut98883 = false, _mut98884 = false, _mut98885 = false, _mut98886 = false, _mut98887 = false, _mut98888 = false, _mut98889 = false, _mut98890 = false, _mut98891 = false, _mut98892 = false, _mut98893 = false, _mut98894 = false, _mut98895 = false, _mut98896 = false, _mut98897 = false, _mut98898 = false, _mut98899 = false, _mut98900 = false, _mut98901 = false, _mut98902 = false, _mut98903 = false, _mut98904 = false, _mut98905 = false, _mut98906 = false, _mut98907 = false, _mut98908 = false, _mut98909 = false, _mut98910 = false, _mut98911 = false, _mut98912 = false, _mut98913 = false, _mut98914 = false, _mut98915 = false, _mut98916 = false, _mut98917 = false, _mut98918 = false, _mut98919 = false, _mut98920 = false, _mut98921 = false, _mut98922 = false, _mut98923 = false, _mut98924 = false, _mut98925 = false, _mut98926 = false, _mut98927 = false, _mut98928 = false, _mut98929 = false, _mut98930 = false, _mut98931 = false, _mut98932 = false, _mut98933 = false, _mut98934 = false, _mut98935 = false, _mut98936 = false, _mut98937 = false, _mut98938 = false, _mut98939 = false, _mut98940 = false, _mut98941 = false, _mut98942 = false, _mut98943 = false, _mut98944 = false, _mut98945 = false, _mut98946 = false, _mut98947 = false, _mut98948 = false, _mut98949 = false, _mut98950 = false, _mut98951 = false, _mut98952 = false, _mut98953 = false, _mut98954 = false, _mut98955 = false, _mut98956 = false, _mut98957 = false, _mut98958 = false, _mut98959 = false, _mut98960 = false, _mut98961 = false, _mut98962 = false, _mut98963 = false, _mut98964 = false, _mut98965 = false, _mut98966 = false, _mut98967 = false, _mut98968 = false, _mut98969 = false, _mut98970 = false, _mut98971 = false, _mut98972 = false, _mut98973 = false, _mut98974 = false, _mut98975 = false, _mut98976 = false, _mut98977 = false, _mut98978 = false, _mut98979 = false, _mut98980 = false, _mut98981 = false, _mut98982 = false, _mut98983 = false, _mut98984 = false, _mut98985 = false, _mut98986 = false, _mut98987 = false, _mut98988 = false, _mut98989 = false, _mut98990 = false, _mut98991 = false, _mut98992 = false, _mut98993 = false, _mut98994 = false, _mut98995 = false, _mut98996 = false, _mut98997 = false, _mut98998 = false, _mut98999 = false, _mut99000 = false, _mut99001 = false, _mut99002 = false, _mut99003 = false, _mut99004 = false, _mut99005 = false, _mut99006 = false, _mut99007 = false, _mut99008 = false, _mut99009 = false, _mut99010 = false, _mut99011 = false, _mut99012 = false, _mut99013 = false, _mut99014 = false, _mut99015 = false, _mut99016 = false, _mut99017 = false, _mut99018 = false, _mut99019 = false, _mut99020 = false, _mut99021 = false;

    /**
     * Array of all compilers created so far.
     */
    private static AtomicReference<DSCompiler[][]> compilers = new AtomicReference<DSCompiler[][]>(null);

    /**
     * Number of free parameters.
     */
    private final int parameters;

    /**
     * Derivation order.
     */
    private final int order;

    /**
     * Number of partial derivatives (including the single 0 order derivative element).
     */
    private final int[][] sizes;

    /**
     * Indirection array for partial derivatives.
     */
    private final int[][] derivativesIndirection;

    /**
     * Indirection array of the lower derivative elements.
     */
    private final int[] lowerIndirection;

    /**
     * Indirection arrays for multiplication.
     */
    private final int[][][] multIndirection;

    /**
     * Indirection arrays for function composition.
     */
    private final int[][][] compIndirection;

    /**
     * Private constructor, reserved for the factory method {@link #getCompiler(int, int)}.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @param derivativeCompiler compiler for the derivative part
     * @throws NumberIsTooLargeException if order is too large
     */
    private DSCompiler(final int parameters, final int order, final DSCompiler valueCompiler, final DSCompiler derivativeCompiler) throws NumberIsTooLargeException {
        this.parameters = parameters;
        this.order = order;
        this.sizes = compileSizes(parameters, order, valueCompiler);
        this.derivativesIndirection = compileDerivativesIndirection(parameters, order, valueCompiler, derivativeCompiler);
        this.lowerIndirection = compileLowerIndirection(parameters, order, valueCompiler, derivativeCompiler);
        this.multIndirection = compileMultiplicationIndirection(parameters, order, valueCompiler, derivativeCompiler, lowerIndirection);
        this.compIndirection = compileCompositionIndirection(parameters, order, valueCompiler, derivativeCompiler, sizes, derivativesIndirection);
    }

    /**
     * Get the compiler for number of free parameters and order.
     * @param parameters number of free parameters
     * @param order derivation order
     * @return cached rules set
     * @throws NumberIsTooLargeException if order is too large
     */
    public static DSCompiler getCompiler(int parameters, int order) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188");
        // get the cached compilers
        final DSCompiler[][] cache = compilers.get();
        if ((_mut97222 ? ((_mut97221 ? ((_mut97215 ? (cache != null || ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214)) : (cache != null && ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214))) || ROR_greater(cache[parameters].length, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97216, _mut97217, _mut97218, _mut97219, _mut97220)) : ((_mut97215 ? (cache != null || ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214)) : (cache != null && ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214))) && ROR_greater(cache[parameters].length, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97216, _mut97217, _mut97218, _mut97219, _mut97220))) || cache[parameters][order] != null) : ((_mut97221 ? ((_mut97215 ? (cache != null || ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214)) : (cache != null && ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214))) || ROR_greater(cache[parameters].length, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97216, _mut97217, _mut97218, _mut97219, _mut97220)) : ((_mut97215 ? (cache != null || ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214)) : (cache != null && ROR_greater(cache.length, parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97210, _mut97211, _mut97212, _mut97213, _mut97214))) && ROR_greater(cache[parameters].length, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97216, _mut97217, _mut97218, _mut97219, _mut97220))) && cache[parameters][order] != null))) {
            // the compiler has already been created
            return cache[parameters][order];
        }
        // we need to create more compilers
        final int maxParameters = FastMath.max(parameters, cache == null ? 0 : cache.length);
        final int maxOrder = FastMath.max(order, cache == null ? 0 : cache[0].length);
        final DSCompiler[][] newCache = new DSCompiler[AOR_plus(maxParameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97223, _mut97224, _mut97225, _mut97226)][AOR_plus(maxOrder, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97227, _mut97228, _mut97229, _mut97230)];
        if (cache != null) {
            // preserve the already created compilers
            for (int i = 0; ROR_less(i, cache.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97231, _mut97232, _mut97233, _mut97234, _mut97235); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188");
                System.arraycopy(cache[i], 0, newCache[i], 0, cache[i].length);
            }
        }
        // create the array in increasing diagonal order
        for (int diag = 0; ROR_less_equals(diag, AOR_plus(parameters, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97263, _mut97264, _mut97265, _mut97266), "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97267, _mut97268, _mut97269, _mut97270, _mut97271); ++diag) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188");
            for (int o = FastMath.max(0, diag - parameters); ROR_less_equals(o, FastMath.min(order, diag), "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97258, _mut97259, _mut97260, _mut97261, _mut97262); ++o) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188");
                final int p = AOR_minus(diag, o, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97236, _mut97237, _mut97238, _mut97239);
                if (newCache[p][o] == null) {
                    final DSCompiler valueCompiler = (ROR_equals(p, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97240, _mut97241, _mut97242, _mut97243, _mut97244)) ? null : newCache[AOR_minus(p, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97245, _mut97246, _mut97247, _mut97248)][o];
                    final DSCompiler derivativeCompiler = (ROR_equals(o, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97249, _mut97250, _mut97251, _mut97252, _mut97253)) ? null : newCache[p][AOR_minus(o, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getCompiler_188", _mut97254, _mut97255, _mut97256, _mut97257)];
                    newCache[p][o] = new DSCompiler(p, o, valueCompiler, derivativeCompiler);
                }
            }
        }
        // atomically reset the cached compilers array
        compilers.compareAndSet(cache, newCache);
        return newCache[parameters][order];
    }

    /**
     * Compile the sizes array.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @return sizes array
     */
    private static int[][] compileSizes(final int parameters, final int order, final DSCompiler valueCompiler) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236");
        final int[][] sizes = new int[AOR_plus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97272, _mut97273, _mut97274, _mut97275)][AOR_plus(order, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97276, _mut97277, _mut97278, _mut97279)];
        if (ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97280, _mut97281, _mut97282, _mut97283, _mut97284)) {
            Arrays.fill(sizes[0], 1);
        } else {
            System.arraycopy(valueCompiler.sizes, 0, sizes, 0, parameters);
            sizes[parameters][0] = 1;
            for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97301, _mut97302, _mut97303, _mut97304, _mut97305); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236");
                sizes[parameters][AOR_plus(i, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97285, _mut97286, _mut97287, _mut97288)] = AOR_plus(sizes[parameters][i], sizes[AOR_minus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97293, _mut97294, _mut97295, _mut97296)][AOR_plus(i, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97289, _mut97290, _mut97291, _mut97292)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileSizes_236", _mut97297, _mut97298, _mut97299, _mut97300);
            }
        }
        return sizes;
    }

    /**
     * Compile the derivatives indirection array.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @param derivativeCompiler compiler for the derivative part
     * @return derivatives indirection array
     */
    private static int[][] compileDerivativesIndirection(final int parameters, final int order, final DSCompiler valueCompiler, final DSCompiler derivativeCompiler) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261");
        if ((_mut97316 ? (ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97306, _mut97307, _mut97308, _mut97309, _mut97310) && ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97311, _mut97312, _mut97313, _mut97314, _mut97315)) : (ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97306, _mut97307, _mut97308, _mut97309, _mut97310) || ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97311, _mut97312, _mut97313, _mut97314, _mut97315)))) {
            return new int[1][parameters];
        }
        final int vSize = valueCompiler.derivativesIndirection.length;
        final int dSize = derivativeCompiler.derivativesIndirection.length;
        final int[][] derivativesIndirection = new int[AOR_plus(vSize, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97317, _mut97318, _mut97319, _mut97320)][parameters];
        // set up the indices for the value part
        for (int i = 0; ROR_less(i, vSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97325, _mut97326, _mut97327, _mut97328, _mut97329); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261");
            // copy the first indices, the last one remaining set to 0
            System.arraycopy(valueCompiler.derivativesIndirection[i], 0, derivativesIndirection[i], 0, AOR_minus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97321, _mut97322, _mut97323, _mut97324));
        }
        // set up the indices for the derivative part
        for (int i = 0; ROR_less(i, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97342, _mut97343, _mut97344, _mut97345, _mut97346); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261");
            // copy the indices
            System.arraycopy(derivativeCompiler.derivativesIndirection[i], 0, derivativesIndirection[AOR_plus(vSize, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97330, _mut97331, _mut97332, _mut97333)], 0, parameters);
            // increment the derivation order for the last parameter
            derivativesIndirection[AOR_plus(vSize, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97338, _mut97339, _mut97340, _mut97341)][AOR_minus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileDerivativesIndirection_261", _mut97334, _mut97335, _mut97336, _mut97337)]++;
        }
        return derivativesIndirection;
    }

    /**
     * Compile the lower derivatives indirection array.
     * <p>
     * This indirection array contains the indices of all elements
     * except derivatives for last derivation order.
     * </p>
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @param derivativeCompiler compiler for the derivative part
     * @return lower derivatives indirection array
     */
    private static int[] compileLowerIndirection(final int parameters, final int order, final DSCompiler valueCompiler, final DSCompiler derivativeCompiler) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309");
        if ((_mut97357 ? (ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97347, _mut97348, _mut97349, _mut97350, _mut97351) && ROR_less_equals(order, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97352, _mut97353, _mut97354, _mut97355, _mut97356)) : (ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97347, _mut97348, _mut97349, _mut97350, _mut97351) || ROR_less_equals(order, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97352, _mut97353, _mut97354, _mut97355, _mut97356)))) {
            return new int[] { 0 };
        }
        // this is an implementation of definition 6 in Dan Kalman's paper.
        final int vSize = valueCompiler.lowerIndirection.length;
        final int dSize = derivativeCompiler.lowerIndirection.length;
        final int[] lowerIndirection = new int[AOR_plus(vSize, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97358, _mut97359, _mut97360, _mut97361)];
        System.arraycopy(valueCompiler.lowerIndirection, 0, lowerIndirection, 0, vSize);
        for (int i = 0; ROR_less(i, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97370, _mut97371, _mut97372, _mut97373, _mut97374); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309");
            lowerIndirection[AOR_plus(vSize, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97362, _mut97363, _mut97364, _mut97365)] = AOR_plus(valueCompiler.getSize(), derivativeCompiler.lowerIndirection[i], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileLowerIndirection_309", _mut97366, _mut97367, _mut97368, _mut97369);
        }
        return lowerIndirection;
    }

    /**
     * Compile the multiplication indirection array.
     * <p>
     * This indirection array contains the indices of all pairs of elements
     * involved when computing a multiplication. This allows a straightforward
     * loop-based multiplication (see {@link #multiply(double[], int, double[], int, double[], int)}).
     * </p>
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @param derivativeCompiler compiler for the derivative part
     * @param lowerIndirection lower derivatives indirection array
     * @return multiplication indirection array
     */
    private static int[][][] compileMultiplicationIndirection(final int parameters, final int order, final DSCompiler valueCompiler, final DSCompiler derivativeCompiler, final int[] lowerIndirection) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343");
        if ((_mut97385 ? ((ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97375, _mut97376, _mut97377, _mut97378, _mut97379)) && (ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97380, _mut97381, _mut97382, _mut97383, _mut97384))) : ((ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97375, _mut97376, _mut97377, _mut97378, _mut97379)) || (ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97380, _mut97381, _mut97382, _mut97383, _mut97384))))) {
            return new int[][][] { { { 1, 0, 0 } } };
        }
        // this is an implementation of definition 3 in Dan Kalman's paper.
        final int vSize = valueCompiler.multIndirection.length;
        final int dSize = derivativeCompiler.multIndirection.length;
        final int[][][] multIndirection = new int[AOR_plus(vSize, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97386, _mut97387, _mut97388, _mut97389)][][];
        System.arraycopy(valueCompiler.multIndirection, 0, multIndirection, 0, vSize);
        for (int i = 0; ROR_less(i, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97437, _mut97438, _mut97439, _mut97440, _mut97441); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343");
            final int[][] dRow = derivativeCompiler.multIndirection[i];
            List<int[]> row = new ArrayList<int[]>(AOR_multiply(dRow.length, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97390, _mut97391, _mut97392, _mut97393));
            for (int j = 0; ROR_less(j, dRow.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97402, _mut97403, _mut97404, _mut97405, _mut97406); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343");
                row.add(new int[] { dRow[j][0], lowerIndirection[dRow[j][1]], AOR_plus(vSize, dRow[j][2], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97394, _mut97395, _mut97396, _mut97397) });
                row.add(new int[] { dRow[j][0], AOR_plus(vSize, dRow[j][1], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97398, _mut97399, _mut97400, _mut97401), lowerIndirection[dRow[j][2]] });
            }
            // combine terms with similar derivation orders
            final List<int[]> combined = new ArrayList<int[]>(row.size());
            for (int j = 0; ROR_less(j, row.size(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97428, _mut97429, _mut97430, _mut97431, _mut97432); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343");
                final int[] termJ = row.get(j);
                if (ROR_greater(termJ[0], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97407, _mut97408, _mut97409, _mut97410, _mut97411)) {
                    for (int k = j + 1; ROR_less(k, row.size(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97423, _mut97424, _mut97425, _mut97426, _mut97427); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343");
                        final int[] termK = row.get(k);
                        if ((_mut97422 ? (ROR_equals(termJ[1], termK[1], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97412, _mut97413, _mut97414, _mut97415, _mut97416) || ROR_equals(termJ[2], termK[2], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97417, _mut97418, _mut97419, _mut97420, _mut97421)) : (ROR_equals(termJ[1], termK[1], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97412, _mut97413, _mut97414, _mut97415, _mut97416) && ROR_equals(termJ[2], termK[2], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97417, _mut97418, _mut97419, _mut97420, _mut97421)))) {
                            // combine termJ and termK
                            termJ[0] += termK[0];
                            // make sure we will skip termK later on in the outer loop
                            termK[0] = 0;
                        }
                    }
                    combined.add(termJ);
                }
            }
            multIndirection[AOR_plus(vSize, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileMultiplicationIndirection_343", _mut97433, _mut97434, _mut97435, _mut97436)] = combined.toArray(new int[combined.size()][]);
        }
        return multIndirection;
    }

    /**
     * Compile the function composition indirection array.
     * <p>
     * This indirection array contains the indices of all sets of elements
     * involved when computing a composition. This allows a straightforward
     * loop-based composition (see {@link #compose(double[], int, double[], double[], int)}).
     * </p>
     * @param parameters number of free parameters
     * @param order derivation order
     * @param valueCompiler compiler for the value part
     * @param derivativeCompiler compiler for the derivative part
     * @param sizes sizes array
     * @param derivativesIndirection derivatives indirection array
     * @return multiplication indirection array
     * @throws NumberIsTooLargeException if order is too large
     */
    private static int[][][] compileCompositionIndirection(final int parameters, final int order, final DSCompiler valueCompiler, final DSCompiler derivativeCompiler, final int[][] sizes, final int[][] derivativesIndirection) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
        if ((_mut97452 ? ((ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97442, _mut97443, _mut97444, _mut97445, _mut97446)) && (ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97447, _mut97448, _mut97449, _mut97450, _mut97451))) : ((ROR_equals(parameters, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97442, _mut97443, _mut97444, _mut97445, _mut97446)) || (ROR_equals(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97447, _mut97448, _mut97449, _mut97450, _mut97451))))) {
            return new int[][][] { { { 1, 0 } } };
        }
        final int vSize = valueCompiler.compIndirection.length;
        final int dSize = derivativeCompiler.compIndirection.length;
        final int[][][] compIndirection = new int[AOR_plus(vSize, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97453, _mut97454, _mut97455, _mut97456)][][];
        // the composition rules from the value part can be reused as is
        System.arraycopy(valueCompiler.compIndirection, 0, compIndirection, 0, vSize);
        // underlying one did not handle
        for (int i = 0; ROR_less(i, dSize, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97528, _mut97529, _mut97530, _mut97531, _mut97532); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
            List<int[]> row = new ArrayList<int[]>();
            for (int[] term : derivativeCompiler.compIndirection[i]) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                // derive the first factor in the term: f_k with respect to new parameter
                int[] derivedTermF = new int[AOR_plus(term.length, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97457, _mut97458, _mut97459, _mut97460)];
                // p
                derivedTermF[0] = term[0];
                // f_(k+1)
                derivedTermF[1] = AOR_plus(term[1], 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97461, _mut97462, _mut97463, _mut97464);
                int[] orders = new int[parameters];
                orders[AOR_minus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97465, _mut97466, _mut97467, _mut97468)] = 1;
                // g_1
                derivedTermF[term.length] = getPartialDerivativeIndex(parameters, order, sizes, orders);
                for (int j = 2; ROR_less(j, term.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97469, _mut97470, _mut97471, _mut97472, _mut97473); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                    // is different from the mapping with one less order
                    derivedTermF[j] = convertIndex(term[j], parameters, derivativeCompiler.derivativesIndirection, parameters, order, sizes);
                }
                Arrays.sort(derivedTermF, 2, derivedTermF.length);
                row.add(derivedTermF);
                // derive the various g_l
                for (int l = 2; ROR_less(l, term.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97488, _mut97489, _mut97490, _mut97491, _mut97492); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                    int[] derivedTermG = new int[term.length];
                    derivedTermG[0] = term[0];
                    derivedTermG[1] = term[1];
                    for (int j = 2; ROR_less(j, term.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97483, _mut97484, _mut97485, _mut97486, _mut97487); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                        // is different from the mapping with one less order
                        derivedTermG[j] = convertIndex(term[j], parameters, derivativeCompiler.derivativesIndirection, parameters, order, sizes);
                        if (ROR_equals(j, l, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97474, _mut97475, _mut97476, _mut97477, _mut97478)) {
                            // derive this term
                            System.arraycopy(derivativesIndirection[derivedTermG[j]], 0, orders, 0, parameters);
                            orders[AOR_minus(parameters, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97479, _mut97480, _mut97481, _mut97482)]++;
                            derivedTermG[j] = getPartialDerivativeIndex(parameters, order, sizes, orders);
                        }
                    }
                    Arrays.sort(derivedTermG, 2, derivedTermG.length);
                    row.add(derivedTermG);
                }
            }
            // combine terms with similar derivation orders
            final List<int[]> combined = new ArrayList<int[]>(row.size());
            for (int j = 0; ROR_less(j, row.size(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97519, _mut97520, _mut97521, _mut97522, _mut97523); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                final int[] termJ = row.get(j);
                if (ROR_greater(termJ[0], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97493, _mut97494, _mut97495, _mut97496, _mut97497)) {
                    for (int k = j + 1; ROR_less(k, row.size(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97514, _mut97515, _mut97516, _mut97517, _mut97518); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                        final int[] termK = row.get(k);
                        boolean equals = ROR_equals(termJ.length, termK.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97498, _mut97499, _mut97500, _mut97501, _mut97502);
                        for (int l = 1; (_mut97513 ? (equals || ROR_less(l, termJ.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97508, _mut97509, _mut97510, _mut97511, _mut97512)) : (equals && ROR_less(l, termJ.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97508, _mut97509, _mut97510, _mut97511, _mut97512))); ++l) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408");
                            equals &= ROR_equals(termJ[l], termK[l], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97503, _mut97504, _mut97505, _mut97506, _mut97507);
                        }
                        if (equals) {
                            // combine termJ and termK
                            termJ[0] += termK[0];
                            // make sure we will skip termK later on in the outer loop
                            termK[0] = 0;
                        }
                    }
                    combined.add(termJ);
                }
            }
            compIndirection[AOR_plus(vSize, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compileCompositionIndirection_408", _mut97524, _mut97525, _mut97526, _mut97527)] = combined.toArray(new int[combined.size()][]);
        }
        return compIndirection;
    }

    /**
     * Get the index of a partial derivative in the array.
     * <p>
     * If all orders are set to 0, then the 0<sup>th</sup> order derivative
     * is returned, which is the value of the function.
     * </p>
     * <p>The indices of derivatives are between 0 and {@link #getSize() getSize()} - 1.
     * Their specific order is fixed for a given compiler, but otherwise not
     * publicly specified. There are however some simple cases which have guaranteed
     * indices:
     * </p>
     * <ul>
     *   <li>the index of 0<sup>th</sup> order derivative is always 0</li>
     *   <li>if there is only 1 {@link #getFreeParameters() free parameter}, then the
     *   derivatives are sorted in increasing derivation order (i.e. f at index 0, df/dp
     *   at index 1, d<sup>2</sup>f/dp<sup>2</sup> at index 2 ...
     *   d<sup>k</sup>f/dp<sup>k</sup> at index k),</li>
     *   <li>if the {@link #getOrder() derivation order} is 1, then the derivatives
     *   are sorted in increasing free parameter order (i.e. f at index 0, df/dx<sub>1</sub>
     *   at index 1, df/dx<sub>2</sub> at index 2 ... df/dx<sub>k</sub> at index k),</li>
     *   <li>all other cases are not publicly specified</li>
     * </ul>
     * <p>
     * This method is the inverse of method {@link #getPartialDerivativeOrders(int)}
     * </p>
     * @param orders derivation orders with respect to each parameter
     * @return index of the partial derivative
     * @exception DimensionMismatchException if the numbers of parameters does not
     * match the instance
     * @exception NumberIsTooLargeException if sum of derivation orders is larger
     * than the instance limits
     * @see #getPartialDerivativeOrders(int)
     */
    public int getPartialDerivativeIndex(final int... orders) throws DimensionMismatchException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_539");
        // safety check
        if (ROR_not_equals(orders.length, getFreeParameters(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_539", _mut97533, _mut97534, _mut97535, _mut97536, _mut97537)) {
            throw new DimensionMismatchException(orders.length, getFreeParameters());
        }
        return getPartialDerivativeIndex(parameters, order, sizes, orders);
    }

    /**
     * Get the index of a partial derivative in an array.
     * @param parameters number of free parameters
     * @param order derivation order
     * @param sizes sizes array
     * @param orders derivation orders with respect to each parameter
     * (the lenght of this array must match the number of parameters)
     * @return index of the partial derivative
     * @exception NumberIsTooLargeException if sum of derivation orders is larger
     * than the instance limits
     */
    private static int getPartialDerivativeIndex(final int parameters, final int order, final int[][] sizes, final int... orders) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561");
        // this is theorem 2 of his paper, with recursion replaced by iteration
        int index = 0;
        int m = order;
        int ordersSum = 0;
        for (int i = parameters - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561", _mut97548, _mut97549, _mut97550, _mut97551, _mut97552); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561");
            // derivative order for current free parameter
            int derivativeOrder = orders[i];
            // safety check
            ordersSum += derivativeOrder;
            if (ROR_greater(ordersSum, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561", _mut97538, _mut97539, _mut97540, _mut97541, _mut97542)) {
                throw new NumberIsTooLargeException(ordersSum, order, true);
            }
            while (ROR_greater(derivativeOrder--, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561", _mut97543, _mut97544, _mut97545, _mut97546, _mut97547)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.getPartialDerivativeIndex_561");
                // so we add the size of the value part to the base index
                index += sizes[i][m--];
            }
        }
        return index;
    }

    /**
     * Convert an index from one (parameters, order) structure to another.
     * @param index index of a partial derivative in source derivative structure
     * @param srcP number of free parameters in source derivative structure
     * @param srcDerivativesIndirection derivatives indirection array for the source
     * derivative structure
     * @param destP number of free parameters in destination derivative structure
     * @param destO derivation order in destination derivative structure
     * @param destSizes sizes array for the destination derivative structure
     * @return index of the partial derivative with the <em>same</em> characteristics
     * in destination derivative structure
     * @throws NumberIsTooLargeException if order is too large
     */
    private static int convertIndex(final int index, final int srcP, final int[][] srcDerivativesIndirection, final int destP, final int destO, final int[][] destSizes) throws NumberIsTooLargeException {
        int[] orders = new int[destP];
        System.arraycopy(srcDerivativesIndirection[index], 0, orders, 0, FastMath.min(srcP, destP));
        return getPartialDerivativeIndex(destP, destO, destSizes, orders);
    }

    /**
     * Get the derivation orders for a specific index in the array.
     * <p>
     * This method is the inverse of {@link #getPartialDerivativeIndex(int...)}.
     * </p>
     * @param index of the partial derivative
     * @return orders derivation orders with respect to each parameter
     * @see #getPartialDerivativeIndex(int...)
     */
    public int[] getPartialDerivativeOrders(final int index) {
        return derivativesIndirection[index];
    }

    /**
     * Get the number of free parameters.
     * @return number of free parameters
     */
    public int getFreeParameters() {
        return parameters;
    }

    /**
     * Get the derivation order.
     * @return derivation order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Get the array size required for holding partial derivatives data.
     * <p>
     * This number includes the single 0 order derivative element, which is
     * guaranteed to be stored in the first element of the array.
     * </p>
     * @return array size required for holding partial derivatives data
     */
    public int getSize() {
        return sizes[parameters][order];
    }

    /**
     * Compute linear combination.
     * The derivative structure built will be a1 * ds1 + a2 * ds2
     * @param a1 first scale factor
     * @param c1 first base (unscaled) component
     * @param offset1 offset of first operand in its array
     * @param a2 second scale factor
     * @param c2 second base (unscaled) component
     * @param offset2 offset of second operand in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void linearCombination(final double a1, final double[] c1, final int offset1, final double a2, final double[] c2, final int offset2, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664");
        for (int i = 0; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664", _mut97565, _mut97566, _mut97567, _mut97568, _mut97569); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664", _mut97553, _mut97554, _mut97555, _mut97556)] = MathArrays.linearCombination(a1, c1[AOR_plus(offset1, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664", _mut97557, _mut97558, _mut97559, _mut97560)], a2, c2[AOR_plus(offset2, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_664", _mut97561, _mut97562, _mut97563, _mut97564)]);
        }
    }

    /**
     * Compute linear combination.
     * The derivative structure built will be a1 * ds1 + a2 * ds2 + a3 * ds3 + a4 * ds4
     * @param a1 first scale factor
     * @param c1 first base (unscaled) component
     * @param offset1 offset of first operand in its array
     * @param a2 second scale factor
     * @param c2 second base (unscaled) component
     * @param offset2 offset of second operand in its array
     * @param a3 third scale factor
     * @param c3 third base (unscaled) component
     * @param offset3 offset of third operand in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void linearCombination(final double a1, final double[] c1, final int offset1, final double a2, final double[] c2, final int offset2, final double a3, final double[] c3, final int offset3, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688");
        for (int i = 0; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688", _mut97586, _mut97587, _mut97588, _mut97589, _mut97590); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688", _mut97570, _mut97571, _mut97572, _mut97573)] = MathArrays.linearCombination(a1, c1[AOR_plus(offset1, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688", _mut97574, _mut97575, _mut97576, _mut97577)], a2, c2[AOR_plus(offset2, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688", _mut97578, _mut97579, _mut97580, _mut97581)], a3, c3[AOR_plus(offset3, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_688", _mut97582, _mut97583, _mut97584, _mut97585)]);
        }
    }

    /**
     * Compute linear combination.
     * The derivative structure built will be a1 * ds1 + a2 * ds2 + a3 * ds3 + a4 * ds4
     * @param a1 first scale factor
     * @param c1 first base (unscaled) component
     * @param offset1 offset of first operand in its array
     * @param a2 second scale factor
     * @param c2 second base (unscaled) component
     * @param offset2 offset of second operand in its array
     * @param a3 third scale factor
     * @param c3 third base (unscaled) component
     * @param offset3 offset of third operand in its array
     * @param a4 fourth scale factor
     * @param c4 fourth base (unscaled) component
     * @param offset4 offset of fourth operand in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void linearCombination(final double a1, final double[] c1, final int offset1, final double a2, final double[] c2, final int offset2, final double a3, final double[] c3, final int offset3, final double a4, final double[] c4, final int offset4, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718");
        for (int i = 0; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97611, _mut97612, _mut97613, _mut97614, _mut97615); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97591, _mut97592, _mut97593, _mut97594)] = MathArrays.linearCombination(a1, c1[AOR_plus(offset1, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97595, _mut97596, _mut97597, _mut97598)], a2, c2[AOR_plus(offset2, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97599, _mut97600, _mut97601, _mut97602)], a3, c3[AOR_plus(offset3, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97603, _mut97604, _mut97605, _mut97606)], a4, c4[AOR_plus(offset4, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.linearCombination_718", _mut97607, _mut97608, _mut97609, _mut97610)]);
        }
    }

    /**
     * Perform addition of two derivative structures.
     * @param lhs array holding left hand side of addition
     * @param lhsOffset offset of the left hand side in its array
     * @param rhs array right hand side of addition
     * @param rhsOffset offset of the right hand side in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void add(final double[] lhs, final int lhsOffset, final double[] rhs, final int rhsOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741");
        for (int i = 0; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741", _mut97632, _mut97633, _mut97634, _mut97635, _mut97636); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741", _mut97616, _mut97617, _mut97618, _mut97619)] = AOR_plus(lhs[AOR_plus(lhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741", _mut97620, _mut97621, _mut97622, _mut97623)], rhs[AOR_plus(rhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741", _mut97624, _mut97625, _mut97626, _mut97627)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.add_741", _mut97628, _mut97629, _mut97630, _mut97631);
        }
    }

    /**
     * Perform subtraction of two derivative structures.
     * @param lhs array holding left hand side of subtraction
     * @param lhsOffset offset of the left hand side in its array
     * @param rhs array right hand side of subtraction
     * @param rhsOffset offset of the right hand side in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void subtract(final double[] lhs, final int lhsOffset, final double[] rhs, final int rhsOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757");
        for (int i = 0; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757", _mut97653, _mut97654, _mut97655, _mut97656, _mut97657); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757", _mut97637, _mut97638, _mut97639, _mut97640)] = AOR_minus(lhs[AOR_plus(lhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757", _mut97641, _mut97642, _mut97643, _mut97644)], rhs[AOR_plus(rhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757", _mut97645, _mut97646, _mut97647, _mut97648)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.subtract_757", _mut97649, _mut97650, _mut97651, _mut97652);
        }
    }

    /**
     * Perform multiplication of two derivative structures.
     * @param lhs array holding left hand side of multiplication
     * @param lhsOffset offset of the left hand side in its array
     * @param rhs array right hand side of multiplication
     * @param rhsOffset offset of the right hand side in its array
     * @param result array where result must be stored (for
     * multiplication the result array <em>cannot</em> be one of
     * the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void multiply(final double[] lhs, final int lhsOffset, final double[] rhs, final int rhsOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775");
        for (int i = 0; ROR_less(i, multIndirection.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97683, _mut97684, _mut97685, _mut97686, _mut97687); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775");
            final int[][] mappingI = multIndirection[i];
            double r = 0;
            for (int j = 0; ROR_less(j, mappingI.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97674, _mut97675, _mut97676, _mut97677, _mut97678); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775");
                r += AOR_multiply(AOR_multiply(mappingI[j][0], lhs[AOR_plus(lhsOffset, mappingI[j][1], "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97658, _mut97659, _mut97660, _mut97661)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97662, _mut97663, _mut97664, _mut97665), rhs[AOR_plus(rhsOffset, mappingI[j][2], "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97666, _mut97667, _mut97668, _mut97669)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97670, _mut97671, _mut97672, _mut97673);
            }
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.multiply_775", _mut97679, _mut97680, _mut97681, _mut97682)] = r;
        }
    }

    /**
     * Perform division of two derivative structures.
     * @param lhs array holding left hand side of division
     * @param lhsOffset offset of the left hand side in its array
     * @param rhs array right hand side of division
     * @param rhsOffset offset of the right hand side in its array
     * @param result array where result must be stored (for
     * division the result array <em>cannot</em> be one of
     * the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void divide(final double[] lhs, final int lhsOffset, final double[] rhs, final int rhsOffset, final double[] result, final int resultOffset) {
        final double[] reciprocal = new double[getSize()];
        pow(rhs, lhsOffset, -1, reciprocal, 0);
        multiply(lhs, lhsOffset, reciprocal, 0, result, resultOffset);
    }

    /**
     * Perform remainder of two derivative structures.
     * @param lhs array holding left hand side of remainder
     * @param lhsOffset offset of the left hand side in its array
     * @param rhs array right hand side of remainder
     * @param rhsOffset offset of the right hand side in its array
     * @param result array where result must be stored (it may be
     * one of the input arrays)
     * @param resultOffset offset of the result in its array
     */
    public void remainder(final double[] lhs, final int lhsOffset, final double[] rhs, final int rhsOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817");
        // compute k such that lhs % rhs = lhs - k rhs
        final double rem = FastMath.IEEEremainder(lhs[lhsOffset], rhs[rhsOffset]);
        final double k = FastMath.rint(AOR_divide((AOR_minus(lhs[lhsOffset], rem, "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97688, _mut97689, _mut97690, _mut97691)), rhs[rhsOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97692, _mut97693, _mut97694, _mut97695));
        // set up value
        result[resultOffset] = rem;
        // set up partial derivatives
        for (int i = 1; ROR_less(i, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97716, _mut97717, _mut97718, _mut97719, _mut97720); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817");
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97696, _mut97697, _mut97698, _mut97699)] = AOR_minus(lhs[AOR_plus(lhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97700, _mut97701, _mut97702, _mut97703)], AOR_multiply(k, rhs[AOR_plus(rhsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97704, _mut97705, _mut97706, _mut97707)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97708, _mut97709, _mut97710, _mut97711), "org.apache.commons.math3.analysis.differentiation.DSCompiler.remainder_817", _mut97712, _mut97713, _mut97714, _mut97715);
        }
    }

    /**
     * Compute power of a double to a derivative structure.
     * @param a number to exponentiate
     * @param operand array holding the power
     * @param operandOffset offset of the power in its array
     * @param result array where result must be stored (for
     * power the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     * @since 3.3
     */
    public void pow(final double a, final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845");
        // [a^x, ln(a) a^x, ln(a)^2 a^x,, ln(a)^3 a^x, ... ]
        final double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97721, _mut97722, _mut97723, _mut97724)];
        if (ROR_equals(a, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97725, _mut97726, _mut97727, _mut97728, _mut97729)) {
            if (ROR_equals(operand[operandOffset], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97743, _mut97744, _mut97745, _mut97746, _mut97747)) {
                function[0] = 1;
                double infinity = Double.POSITIVE_INFINITY;
                for (int i = 1; ROR_less(i, function.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97753, _mut97754, _mut97755, _mut97756, _mut97757); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845");
                    infinity = -infinity;
                    function[i] = infinity;
                }
            } else if (ROR_less(operand[operandOffset], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97748, _mut97749, _mut97750, _mut97751, _mut97752)) {
                Arrays.fill(function, Double.NaN);
            }
        } else {
            function[0] = FastMath.pow(a, operand[operandOffset]);
            final double lnA = FastMath.log(a);
            for (int i = 1; ROR_less(i, function.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97738, _mut97739, _mut97740, _mut97741, _mut97742); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845");
                function[i] = AOR_multiply(lnA, function[AOR_minus(i, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97730, _mut97731, _mut97732, _mut97733)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_845", _mut97734, _mut97735, _mut97736, _mut97737);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute power of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param p power to apply
     * @param result array where result must be stored (for
     * power the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void pow(final double[] operand, final int operandOffset, final double p, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886");
        // [x^p, px^(p-1), p(p-1)x^(p-2), ... ]
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886", _mut97758, _mut97759, _mut97760, _mut97761)];
        double xk = FastMath.pow(operand[operandOffset], AOR_minus(p, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886", _mut97762, _mut97763, _mut97764, _mut97765));
        for (int i = order; ROR_greater(i, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886", _mut97766, _mut97767, _mut97768, _mut97769, _mut97770); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886");
            function[i] = xk;
            xk *= operand[operandOffset];
        }
        function[0] = xk;
        double coefficient = p;
        for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886", _mut97775, _mut97776, _mut97777, _mut97778, _mut97779); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886");
            function[i] *= coefficient;
            coefficient *= AOR_minus(p, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_886", _mut97771, _mut97772, _mut97773, _mut97774);
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute integer power of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param n power to apply
     * @param result array where result must be stored (for
     * power the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void pow(final double[] operand, final int operandOffset, final int n, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918");
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97780, _mut97781, _mut97782, _mut97783, _mut97784)) {
            // special case, x^0 = 1 for all x
            result[resultOffset] = 1.0;
            Arrays.fill(result, AOR_plus(resultOffset, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97785, _mut97786, _mut97787, _mut97788), AOR_plus(resultOffset, getSize(), "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97789, _mut97790, _mut97791, _mut97792), 0);
            return;
        }
        // [x^n, nx^(n-1), n(n-1)x^(n-2), ... ]
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97793, _mut97794, _mut97795, _mut97796)];
        if (ROR_greater(n, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97797, _mut97798, _mut97799, _mut97800, _mut97801)) {
            // strictly positive power
            final int maxOrder = FastMath.min(order, n);
            double xk = FastMath.pow(operand[operandOffset], AOR_minus(n, maxOrder, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97811, _mut97812, _mut97813, _mut97814));
            for (int i = maxOrder; ROR_greater(i, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97815, _mut97816, _mut97817, _mut97818, _mut97819); --i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918");
                function[i] = xk;
                xk *= operand[operandOffset];
            }
            function[0] = xk;
        } else {
            // strictly negative power
            final double inv = AOR_divide(1.0, operand[operandOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97802, _mut97803, _mut97804, _mut97805);
            double xk = FastMath.pow(inv, -n);
            for (int i = 0; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97806, _mut97807, _mut97808, _mut97809, _mut97810); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918");
                function[i] = xk;
                xk *= inv;
            }
        }
        double coefficient = n;
        for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97824, _mut97825, _mut97826, _mut97827, _mut97828); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918");
            function[i] *= coefficient;
            coefficient *= AOR_minus(n, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.pow_918", _mut97820, _mut97821, _mut97822, _mut97823);
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute power of a derivative structure.
     * @param x array holding the base
     * @param xOffset offset of the base in its array
     * @param y array holding the exponent
     * @param yOffset offset of the exponent in its array
     * @param result array where result must be stored (for
     * power the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void pow(final double[] x, final int xOffset, final double[] y, final int yOffset, final double[] result, final int resultOffset) {
        final double[] logX = new double[getSize()];
        log(x, xOffset, logX, 0);
        final double[] yLogX = new double[getSize()];
        multiply(logX, 0, y, yOffset, yLogX, 0);
        exp(yLogX, 0, result, resultOffset);
    }

    /**
     * Compute n<sup>th</sup> root of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param n order of the root
     * @param result array where result must be stored (for
     * n<sup>th</sup> root the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void rootN(final double[] operand, final int operandOffset, final int n, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991");
        // [x^(1/n), (1/n)x^((1/n)-1), (1-n)/n^2x^((1/n)-2), ... ]
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97829, _mut97830, _mut97831, _mut97832)];
        double xk;
        if (ROR_equals(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97833, _mut97834, _mut97835, _mut97836, _mut97837)) {
            function[0] = FastMath.sqrt(operand[operandOffset]);
            xk = AOR_divide(0.5, function[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97871, _mut97872, _mut97873, _mut97874);
        } else if (ROR_equals(n, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97838, _mut97839, _mut97840, _mut97841, _mut97842)) {
            function[0] = FastMath.cbrt(operand[operandOffset]);
            xk = AOR_divide(1.0, (AOR_multiply(AOR_multiply(3.0, function[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97859, _mut97860, _mut97861, _mut97862), function[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97863, _mut97864, _mut97865, _mut97866)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97867, _mut97868, _mut97869, _mut97870);
        } else {
            function[0] = FastMath.pow(operand[operandOffset], AOR_divide(1.0, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97843, _mut97844, _mut97845, _mut97846));
            xk = AOR_divide(1.0, (AOR_multiply(n, FastMath.pow(function[0], AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97847, _mut97848, _mut97849, _mut97850)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97851, _mut97852, _mut97853, _mut97854)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97855, _mut97856, _mut97857, _mut97858);
        }
        final double nReciprocal = AOR_divide(1.0, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97875, _mut97876, _mut97877, _mut97878);
        final double xReciprocal = AOR_divide(1.0, operand[operandOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97879, _mut97880, _mut97881, _mut97882);
        for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97891, _mut97892, _mut97893, _mut97894, _mut97895); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991");
            function[i] = xk;
            xk *= AOR_multiply(xReciprocal, (AOR_minus(nReciprocal, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97883, _mut97884, _mut97885, _mut97886)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.rootN_991", _mut97887, _mut97888, _mut97889, _mut97890);
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute exponential of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * exponential the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void exp(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.exp_1028");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.exp_1028", _mut97896, _mut97897, _mut97898, _mut97899)];
        Arrays.fill(function, FastMath.exp(operand[operandOffset]));
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute exp(x) - 1 of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * exponential the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void expm1(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.expm1_1048");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.expm1_1048", _mut97900, _mut97901, _mut97902, _mut97903)];
        function[0] = FastMath.expm1(operand[operandOffset]);
        Arrays.fill(function, 1, AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.expm1_1048", _mut97904, _mut97905, _mut97906, _mut97907), FastMath.exp(operand[operandOffset]));
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute natural logarithm of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * logarithm the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void log(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069", _mut97908, _mut97909, _mut97910, _mut97911)];
        function[0] = FastMath.log(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069", _mut97912, _mut97913, _mut97914, _mut97915, _mut97916)) {
            double inv = AOR_divide(1.0, operand[operandOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069", _mut97917, _mut97918, _mut97919, _mut97920);
            double xk = inv;
            for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069", _mut97925, _mut97926, _mut97927, _mut97928, _mut97929); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069");
                function[i] = xk;
                xk *= AOR_multiply(-i, inv, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log_1069", _mut97921, _mut97922, _mut97923, _mut97924);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Computes shifted logarithm of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * shifted logarithm the result array <em>cannot</em> be the input array)
     * @param resultOffset offset of the result in its array
     */
    public void log1p(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97930, _mut97931, _mut97932, _mut97933)];
        function[0] = FastMath.log1p(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97934, _mut97935, _mut97936, _mut97937, _mut97938)) {
            double inv = AOR_divide(1.0, (AOR_plus(1.0, operand[operandOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97939, _mut97940, _mut97941, _mut97942)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97943, _mut97944, _mut97945, _mut97946);
            double xk = inv;
            for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97951, _mut97952, _mut97953, _mut97954, _mut97955); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096");
                function[i] = xk;
                xk *= AOR_multiply(-i, inv, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log1p_1096", _mut97947, _mut97948, _mut97949, _mut97950);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Computes base 10 logarithm of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * base 10 logarithm the result array <em>cannot</em> be the input array)
     * @param resultOffset offset of the result in its array
     */
    public void log10(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97956, _mut97957, _mut97958, _mut97959)];
        function[0] = FastMath.log10(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97960, _mut97961, _mut97962, _mut97963, _mut97964)) {
            double inv = AOR_divide(1.0, operand[operandOffset], "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97965, _mut97966, _mut97967, _mut97968);
            double xk = AOR_divide(inv, FastMath.log(10.0), "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97969, _mut97970, _mut97971, _mut97972);
            for (int i = 1; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97977, _mut97978, _mut97979, _mut97980, _mut97981); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123");
                function[i] = xk;
                xk *= AOR_multiply(-i, inv, "org.apache.commons.math3.analysis.differentiation.DSCompiler.log10_1123", _mut97973, _mut97974, _mut97975, _mut97976);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute cosine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * cosine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void cos(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151", _mut97982, _mut97983, _mut97984, _mut97985)];
        function[0] = FastMath.cos(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151", _mut97986, _mut97987, _mut97988, _mut97989, _mut97990)) {
            function[1] = -FastMath.sin(operand[operandOffset]);
            for (int i = 2; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151", _mut97995, _mut97996, _mut97997, _mut97998, _mut97999); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151");
                function[i] = -function[AOR_minus(i, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cos_1151", _mut97991, _mut97992, _mut97993, _mut97994)];
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute sine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * sine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void sin(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177", _mut98000, _mut98001, _mut98002, _mut98003)];
        function[0] = FastMath.sin(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177", _mut98004, _mut98005, _mut98006, _mut98007, _mut98008)) {
            function[1] = FastMath.cos(operand[operandOffset]);
            for (int i = 2; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177", _mut98013, _mut98014, _mut98015, _mut98016, _mut98017); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177");
                function[i] = -function[AOR_minus(i, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sin_1177", _mut98009, _mut98010, _mut98011, _mut98012)];
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute tangent of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * tangent the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void tan(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203");
        // create the function value and derivatives
        final double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98018, _mut98019, _mut98020, _mut98021)];
        final double t = FastMath.tan(operand[operandOffset]);
        function[0] = t;
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98022, _mut98023, _mut98024, _mut98025, _mut98026)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[AOR_plus(order, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98027, _mut98028, _mut98029, _mut98030)];
            p[1] = 1;
            final double t2 = AOR_multiply(t, t, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98031, _mut98032, _mut98033, _mut98034);
            for (int n = 1; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98103, _mut98104, _mut98105, _mut98106, _mut98107); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203");
                // update and evaluate polynomial P_n(t)
                double v = 0;
                p[AOR_plus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98035, _mut98036, _mut98037, _mut98038)] = AOR_multiply(n, p[n], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98039, _mut98040, _mut98041, _mut98042);
                for (int k = n + 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98093, _mut98094, _mut98095, _mut98096, _mut98097); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203");
                    v = AOR_plus(AOR_multiply(v, t2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98043, _mut98044, _mut98045, _mut98046), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98047, _mut98048, _mut98049, _mut98050);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98051, _mut98052, _mut98053, _mut98054, _mut98055)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98061, _mut98062, _mut98063, _mut98064)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98065, _mut98066, _mut98067, _mut98068)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98069, _mut98070, _mut98071, _mut98072)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98073, _mut98074, _mut98075, _mut98076), AOR_multiply((AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98077, _mut98078, _mut98079, _mut98080)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98081, _mut98082, _mut98083, _mut98084)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98085, _mut98086, _mut98087, _mut98088), "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98089, _mut98090, _mut98091, _mut98092);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98056, _mut98057, _mut98058, _mut98059, _mut98060)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tan_1203", _mut98098, _mut98099, _mut98100, _mut98101, _mut98102)) {
                    v *= t;
                }
                function[n] = v;
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute arc cosine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * arc cosine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void acos(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98108, _mut98109, _mut98110, _mut98111)];
        final double x = operand[operandOffset];
        function[0] = FastMath.acos(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98112, _mut98113, _mut98114, _mut98115, _mut98116)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[order];
            p[0] = -1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98117, _mut98118, _mut98119, _mut98120);
            final double f = AOR_divide(1.0, (AOR_minus(1, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98121, _mut98122, _mut98123, _mut98124)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98125, _mut98126, _mut98127, _mut98128);
            double coeff = FastMath.sqrt(f);
            function[1] = AOR_multiply(coeff, p[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98129, _mut98130, _mut98131, _mut98132);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98217, _mut98218, _mut98219, _mut98220, _mut98221); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258");
                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98133, _mut98134, _mut98135, _mut98136)] = AOR_multiply((AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98137, _mut98138, _mut98139, _mut98140)), p[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98141, _mut98142, _mut98143, _mut98144)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98145, _mut98146, _mut98147, _mut98148);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98203, _mut98204, _mut98205, _mut98206, _mut98207); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98149, _mut98150, _mut98151, _mut98152), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98153, _mut98154, _mut98155, _mut98156);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98157, _mut98158, _mut98159, _mut98160, _mut98161)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98167, _mut98168, _mut98169, _mut98170)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98171, _mut98172, _mut98173, _mut98174)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98175, _mut98176, _mut98177, _mut98178)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98179, _mut98180, _mut98181, _mut98182), AOR_multiply((AOR_minus(AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98183, _mut98184, _mut98185, _mut98186), k, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98187, _mut98188, _mut98189, _mut98190)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98191, _mut98192, _mut98193, _mut98194)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98195, _mut98196, _mut98197, _mut98198), "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98199, _mut98200, _mut98201, _mut98202);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98162, _mut98163, _mut98164, _mut98165, _mut98166)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98208, _mut98209, _mut98210, _mut98211, _mut98212)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acos_1258", _mut98213, _mut98214, _mut98215, _mut98216);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute arc sine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * arc sine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void asin(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98222, _mut98223, _mut98224, _mut98225)];
        final double x = operand[operandOffset];
        function[0] = FastMath.asin(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98226, _mut98227, _mut98228, _mut98229, _mut98230)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[order];
            p[0] = 1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98231, _mut98232, _mut98233, _mut98234);
            final double f = AOR_divide(1.0, (AOR_minus(1, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98235, _mut98236, _mut98237, _mut98238)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98239, _mut98240, _mut98241, _mut98242);
            double coeff = FastMath.sqrt(f);
            function[1] = AOR_multiply(coeff, p[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98243, _mut98244, _mut98245, _mut98246);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98331, _mut98332, _mut98333, _mut98334, _mut98335); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315");
                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98247, _mut98248, _mut98249, _mut98250)] = AOR_multiply((AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98251, _mut98252, _mut98253, _mut98254)), p[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98255, _mut98256, _mut98257, _mut98258)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98259, _mut98260, _mut98261, _mut98262);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98317, _mut98318, _mut98319, _mut98320, _mut98321); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98263, _mut98264, _mut98265, _mut98266), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98267, _mut98268, _mut98269, _mut98270);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98271, _mut98272, _mut98273, _mut98274, _mut98275)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98281, _mut98282, _mut98283, _mut98284)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98285, _mut98286, _mut98287, _mut98288)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98289, _mut98290, _mut98291, _mut98292)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98293, _mut98294, _mut98295, _mut98296), AOR_multiply((AOR_minus(AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98297, _mut98298, _mut98299, _mut98300), k, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98301, _mut98302, _mut98303, _mut98304)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98305, _mut98306, _mut98307, _mut98308)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98309, _mut98310, _mut98311, _mut98312), "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98313, _mut98314, _mut98315, _mut98316);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98276, _mut98277, _mut98278, _mut98279, _mut98280)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98322, _mut98323, _mut98324, _mut98325, _mut98326)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asin_1315", _mut98327, _mut98328, _mut98329, _mut98330);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute arc tangent of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * arc tangent the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void atan(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98336, _mut98337, _mut98338, _mut98339)];
        final double x = operand[operandOffset];
        function[0] = FastMath.atan(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98340, _mut98341, _mut98342, _mut98343, _mut98344)) {
            // as per polynomial parity, we can store coefficients of both Q_(n-1) and Q_n in the same array
            final double[] q = new double[order];
            q[0] = 1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98345, _mut98346, _mut98347, _mut98348);
            final double f = AOR_divide(1.0, (AOR_plus(1, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98349, _mut98350, _mut98351, _mut98352)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98353, _mut98354, _mut98355, _mut98356);
            double coeff = f;
            function[1] = AOR_multiply(coeff, q[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98357, _mut98358, _mut98359, _mut98360);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98445, _mut98446, _mut98447, _mut98448, _mut98449); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372");
                // update and evaluate polynomial Q_n(x)
                double v = 0;
                q[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98361, _mut98362, _mut98363, _mut98364)] = AOR_multiply(-n, q[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98365, _mut98366, _mut98367, _mut98368)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98369, _mut98370, _mut98371, _mut98372);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98431, _mut98432, _mut98433, _mut98434, _mut98435); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98373, _mut98374, _mut98375, _mut98376), q[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98377, _mut98378, _mut98379, _mut98380);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98381, _mut98382, _mut98383, _mut98384, _mut98385)) {
                        q[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98391, _mut98392, _mut98393, _mut98394)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98395, _mut98396, _mut98397, _mut98398)), q[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98399, _mut98400, _mut98401, _mut98402)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98403, _mut98404, _mut98405, _mut98406), AOR_multiply((AOR_minus(AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98407, _mut98408, _mut98409, _mut98410), AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98411, _mut98412, _mut98413, _mut98414), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98415, _mut98416, _mut98417, _mut98418)), q[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98419, _mut98420, _mut98421, _mut98422)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98423, _mut98424, _mut98425, _mut98426), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98427, _mut98428, _mut98429, _mut98430);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98386, _mut98387, _mut98388, _mut98389, _mut98390)) {
                        q[0] = q[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98436, _mut98437, _mut98438, _mut98439, _mut98440)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan_1372", _mut98441, _mut98442, _mut98443, _mut98444);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute two arguments arc tangent of a derivative structure.
     * @param y array holding the first operand
     * @param yOffset offset of the first operand in its array
     * @param x array holding the second operand
     * @param xOffset offset of the second operand in its array
     * @param result array where result must be stored (for
     * two arguments arc tangent the result array <em>cannot</em>
     * be the input array)
     * @param resultOffset offset of the result in its array
     */
    public void atan2(final double[] y, final int yOffset, final double[] x, final int xOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431");
        // compute r = sqrt(x^2+y^2)
        double[] tmp1 = new double[getSize()];
        // x^2
        multiply(x, xOffset, x, xOffset, tmp1, 0);
        double[] tmp2 = new double[getSize()];
        // y^2
        multiply(y, yOffset, y, yOffset, tmp2, 0);
        // x^2 + y^2
        add(tmp1, 0, tmp2, 0, tmp2, 0);
        // r = sqrt(x^2 + y^2)
        rootN(tmp2, 0, 2, tmp1, 0);
        if (ROR_greater_equals(x[xOffset], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98450, _mut98451, _mut98452, _mut98453, _mut98454)) {
            // r + x
            add(tmp1, 0, x, xOffset, tmp2, 0);
            // y /(r + x)
            divide(y, yOffset, tmp2, 0, tmp1, 0);
            // atan(y / (r + x))
            atan(tmp1, 0, tmp2, 0);
            for (int i = 0; ROR_less(i, tmp2.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98489, _mut98490, _mut98491, _mut98492, _mut98493); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431");
                // 2 * atan(y / (r + x))
                result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98481, _mut98482, _mut98483, _mut98484)] = AOR_multiply(2, tmp2[i], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98485, _mut98486, _mut98487, _mut98488);
            }
        } else {
            // r - x
            subtract(tmp1, 0, x, xOffset, tmp2, 0);
            // y /(r - x)
            divide(y, yOffset, tmp2, 0, tmp1, 0);
            // atan(y / (r - x))
            atan(tmp1, 0, tmp2, 0);
            result[resultOffset] = AOR_minus(((ROR_less_equals(tmp2[0], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98455, _mut98456, _mut98457, _mut98458, _mut98459)) ? -FastMath.PI : FastMath.PI), AOR_multiply(2, tmp2[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98460, _mut98461, _mut98462, _mut98463), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98464, _mut98465, _mut98466, _mut98467);
            for (int i = 1; ROR_less(i, tmp2.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98476, _mut98477, _mut98478, _mut98479, _mut98480); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431");
                // +/-pi - 2 * atan(y / (r - x))
                result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98468, _mut98469, _mut98470, _mut98471)] = AOR_multiply(-2, tmp2[i], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atan2_1431", _mut98472, _mut98473, _mut98474, _mut98475);
            }
        }
        // fix value to take special cases (+0/+0, +0/-0, -0/+0, -0/-0, +/-infinity) correctly
        result[resultOffset] = FastMath.atan2(y[yOffset], x[xOffset]);
    }

    /**
     * Compute hyperbolic cosine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * hyperbolic cosine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void cosh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480", _mut98494, _mut98495, _mut98496, _mut98497)];
        function[0] = FastMath.cosh(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480", _mut98498, _mut98499, _mut98500, _mut98501, _mut98502)) {
            function[1] = FastMath.sinh(operand[operandOffset]);
            for (int i = 2; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480", _mut98507, _mut98508, _mut98509, _mut98510, _mut98511); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480");
                function[i] = function[AOR_minus(i, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.cosh_1480", _mut98503, _mut98504, _mut98505, _mut98506)];
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute hyperbolic sine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * hyperbolic sine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void sinh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506", _mut98512, _mut98513, _mut98514, _mut98515)];
        function[0] = FastMath.sinh(operand[operandOffset]);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506", _mut98516, _mut98517, _mut98518, _mut98519, _mut98520)) {
            function[1] = FastMath.cosh(operand[operandOffset]);
            for (int i = 2; ROR_less_equals(i, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506", _mut98525, _mut98526, _mut98527, _mut98528, _mut98529); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506");
                function[i] = function[AOR_minus(i, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.sinh_1506", _mut98521, _mut98522, _mut98523, _mut98524)];
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute hyperbolic tangent of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * hyperbolic tangent the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void tanh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532");
        // create the function value and derivatives
        final double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98530, _mut98531, _mut98532, _mut98533)];
        final double t = FastMath.tanh(operand[operandOffset]);
        function[0] = t;
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98534, _mut98535, _mut98536, _mut98537, _mut98538)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[AOR_plus(order, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98539, _mut98540, _mut98541, _mut98542)];
            p[1] = 1;
            final double t2 = AOR_multiply(t, t, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98543, _mut98544, _mut98545, _mut98546);
            for (int n = 1; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98615, _mut98616, _mut98617, _mut98618, _mut98619); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532");
                // update and evaluate polynomial P_n(t)
                double v = 0;
                p[AOR_plus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98547, _mut98548, _mut98549, _mut98550)] = AOR_multiply(-n, p[n], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98551, _mut98552, _mut98553, _mut98554);
                for (int k = n + 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98605, _mut98606, _mut98607, _mut98608, _mut98609); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532");
                    v = AOR_plus(AOR_multiply(v, t2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98555, _mut98556, _mut98557, _mut98558), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98559, _mut98560, _mut98561, _mut98562);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98563, _mut98564, _mut98565, _mut98566, _mut98567)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98573, _mut98574, _mut98575, _mut98576)] = AOR_minus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98577, _mut98578, _mut98579, _mut98580)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98581, _mut98582, _mut98583, _mut98584)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98585, _mut98586, _mut98587, _mut98588), AOR_multiply((AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98589, _mut98590, _mut98591, _mut98592)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98593, _mut98594, _mut98595, _mut98596)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98597, _mut98598, _mut98599, _mut98600), "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98601, _mut98602, _mut98603, _mut98604);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98568, _mut98569, _mut98570, _mut98571, _mut98572)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.tanh_1532", _mut98610, _mut98611, _mut98612, _mut98613, _mut98614)) {
                    v *= t;
                }
                function[n] = v;
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute inverse hyperbolic cosine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * inverse hyperbolic cosine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void acosh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98620, _mut98621, _mut98622, _mut98623)];
        final double x = operand[operandOffset];
        function[0] = FastMath.acosh(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98624, _mut98625, _mut98626, _mut98627, _mut98628)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[order];
            p[0] = 1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98629, _mut98630, _mut98631, _mut98632);
            final double f = AOR_divide(1.0, (AOR_minus(x2, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98633, _mut98634, _mut98635, _mut98636)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98637, _mut98638, _mut98639, _mut98640);
            double coeff = FastMath.sqrt(f);
            function[1] = AOR_multiply(coeff, p[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98641, _mut98642, _mut98643, _mut98644);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98729, _mut98730, _mut98731, _mut98732, _mut98733); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587");
                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98645, _mut98646, _mut98647, _mut98648)] = AOR_multiply((AOR_minus(1, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98649, _mut98650, _mut98651, _mut98652)), p[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98653, _mut98654, _mut98655, _mut98656)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98657, _mut98658, _mut98659, _mut98660);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98715, _mut98716, _mut98717, _mut98718, _mut98719); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98661, _mut98662, _mut98663, _mut98664), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98665, _mut98666, _mut98667, _mut98668);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98669, _mut98670, _mut98671, _mut98672, _mut98673)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98679, _mut98680, _mut98681, _mut98682)] = AOR_plus(AOR_multiply((AOR_minus(1, k, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98683, _mut98684, _mut98685, _mut98686)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98687, _mut98688, _mut98689, _mut98690)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98691, _mut98692, _mut98693, _mut98694), AOR_multiply((AOR_minus(k, AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98695, _mut98696, _mut98697, _mut98698), "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98699, _mut98700, _mut98701, _mut98702)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98703, _mut98704, _mut98705, _mut98706)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98707, _mut98708, _mut98709, _mut98710), "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98711, _mut98712, _mut98713, _mut98714);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98674, _mut98675, _mut98676, _mut98677, _mut98678)) {
                        p[0] = -p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98720, _mut98721, _mut98722, _mut98723, _mut98724)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.acosh_1587", _mut98725, _mut98726, _mut98727, _mut98728);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute inverse hyperbolic sine of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * inverse hyperbolic sine the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void asinh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98734, _mut98735, _mut98736, _mut98737)];
        final double x = operand[operandOffset];
        function[0] = FastMath.asinh(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98738, _mut98739, _mut98740, _mut98741, _mut98742)) {
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[order];
            p[0] = 1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98743, _mut98744, _mut98745, _mut98746);
            final double f = AOR_divide(1.0, (AOR_plus(1, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98747, _mut98748, _mut98749, _mut98750)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98751, _mut98752, _mut98753, _mut98754);
            double coeff = FastMath.sqrt(f);
            function[1] = AOR_multiply(coeff, p[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98755, _mut98756, _mut98757, _mut98758);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98843, _mut98844, _mut98845, _mut98846, _mut98847); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644");
                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98759, _mut98760, _mut98761, _mut98762)] = AOR_multiply((AOR_minus(1, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98763, _mut98764, _mut98765, _mut98766)), p[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98767, _mut98768, _mut98769, _mut98770)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98771, _mut98772, _mut98773, _mut98774);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98829, _mut98830, _mut98831, _mut98832, _mut98833); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98775, _mut98776, _mut98777, _mut98778), p[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98779, _mut98780, _mut98781, _mut98782);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98783, _mut98784, _mut98785, _mut98786, _mut98787)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98793, _mut98794, _mut98795, _mut98796)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98797, _mut98798, _mut98799, _mut98800)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98801, _mut98802, _mut98803, _mut98804)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98805, _mut98806, _mut98807, _mut98808), AOR_multiply((AOR_minus(k, AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98809, _mut98810, _mut98811, _mut98812), "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98813, _mut98814, _mut98815, _mut98816)), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98817, _mut98818, _mut98819, _mut98820)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98821, _mut98822, _mut98823, _mut98824), "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98825, _mut98826, _mut98827, _mut98828);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98788, _mut98789, _mut98790, _mut98791, _mut98792)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98834, _mut98835, _mut98836, _mut98837, _mut98838)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.asinh_1644", _mut98839, _mut98840, _mut98841, _mut98842);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute inverse hyperbolic tangent of a derivative structure.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param result array where result must be stored (for
     * inverse hyperbolic tangent the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void atanh(final double[] operand, final int operandOffset, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701");
        // create the function value and derivatives
        double[] function = new double[AOR_plus(1, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98848, _mut98849, _mut98850, _mut98851)];
        final double x = operand[operandOffset];
        function[0] = FastMath.atanh(x);
        if (ROR_greater(order, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98852, _mut98853, _mut98854, _mut98855, _mut98856)) {
            // as per polynomial parity, we can store coefficients of both Q_(n-1) and Q_n in the same array
            final double[] q = new double[order];
            q[0] = 1;
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98857, _mut98858, _mut98859, _mut98860);
            final double f = AOR_divide(1.0, (AOR_minus(1, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98861, _mut98862, _mut98863, _mut98864)), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98865, _mut98866, _mut98867, _mut98868);
            double coeff = f;
            function[1] = AOR_multiply(coeff, q[0], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98869, _mut98870, _mut98871, _mut98872);
            for (int n = 2; ROR_less_equals(n, order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98957, _mut98958, _mut98959, _mut98960, _mut98961); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701");
                // update and evaluate polynomial Q_n(x)
                double v = 0;
                q[AOR_minus(n, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98873, _mut98874, _mut98875, _mut98876)] = AOR_multiply(n, q[AOR_minus(n, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98877, _mut98878, _mut98879, _mut98880)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98881, _mut98882, _mut98883, _mut98884);
                for (int k = n - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98943, _mut98944, _mut98945, _mut98946, _mut98947); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701");
                    v = AOR_plus(AOR_multiply(v, x2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98885, _mut98886, _mut98887, _mut98888), q[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98889, _mut98890, _mut98891, _mut98892);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98893, _mut98894, _mut98895, _mut98896, _mut98897)) {
                        q[AOR_minus(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98903, _mut98904, _mut98905, _mut98906)] = AOR_plus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98907, _mut98908, _mut98909, _mut98910)), q[AOR_minus(k, 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98911, _mut98912, _mut98913, _mut98914)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98915, _mut98916, _mut98917, _mut98918), AOR_multiply((AOR_plus(AOR_minus(AOR_multiply(2, n, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98919, _mut98920, _mut98921, _mut98922), k, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98923, _mut98924, _mut98925, _mut98926), 1, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98927, _mut98928, _mut98929, _mut98930)), q[AOR_minus(k, 3, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98931, _mut98932, _mut98933, _mut98934)], "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98935, _mut98936, _mut98937, _mut98938), "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98939, _mut98940, _mut98941, _mut98942);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98898, _mut98899, _mut98900, _mut98901, _mut98902)) {
                        q[0] = q[1];
                    }
                }
                if (ROR_equals((n & 0x1), 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98948, _mut98949, _mut98950, _mut98951, _mut98952)) {
                    v *= x;
                }
                coeff *= f;
                function[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.differentiation.DSCompiler.atanh_1701", _mut98953, _mut98954, _mut98955, _mut98956);
            }
        }
        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);
    }

    /**
     * Compute composition of a derivative structure by a function.
     * @param operand array holding the operand
     * @param operandOffset offset of the operand in its array
     * @param f array of value and derivatives of the function at
     * the current point (i.e. at {@code operand[operandOffset]}).
     * @param result array where result must be stored (for
     * composition the result array <em>cannot</em> be the input
     * array)
     * @param resultOffset offset of the result in its array
     */
    public void compose(final double[] operand, final int operandOffset, final double[] f, final double[] result, final int resultOffset) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760");
        for (int i = 0; ROR_less(i, compIndirection.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98984, _mut98985, _mut98986, _mut98987, _mut98988); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760");
            final int[][] mappingI = compIndirection[i];
            double r = 0;
            for (int j = 0; ROR_less(j, mappingI.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98975, _mut98976, _mut98977, _mut98978, _mut98979); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760");
                final int[] mappingIJ = mappingI[j];
                double product = AOR_multiply(mappingIJ[0], f[mappingIJ[1]], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98962, _mut98963, _mut98964, _mut98965);
                for (int k = 2; ROR_less(k, mappingIJ.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98970, _mut98971, _mut98972, _mut98973, _mut98974); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760");
                    product *= operand[AOR_plus(operandOffset, mappingIJ[k], "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98966, _mut98967, _mut98968, _mut98969)];
                }
                r += product;
            }
            result[AOR_plus(resultOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.compose_1760", _mut98980, _mut98981, _mut98982, _mut98983)] = r;
        }
    }

    /**
     * Evaluate Taylor expansion of a derivative structure.
     * @param ds array holding the derivative structure
     * @param dsOffset offset of the derivative structure in its array
     * @param delta parameters offsets (&Delta;x, &Delta;y, ...)
     * @return value of the Taylor expansion at x + &Delta;x, y + &Delta;y, ...
     * @throws MathArithmeticException if factorials becomes too large
     */
    public double taylor(final double[] ds, final int dsOffset, final double... delta) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784");
        double value = 0;
        for (int i = getSize() - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784", _mut99007, _mut99008, _mut99009, _mut99010, _mut99011); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784");
            final int[] orders = getPartialDerivativeOrders(i);
            double term = ds[AOR_plus(dsOffset, i, "org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784", _mut98989, _mut98990, _mut98991, _mut98992)];
            for (int k = 0; ROR_less(k, orders.length, "org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784", _mut99002, _mut99003, _mut99004, _mut99005, _mut99006); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784");
                if (ROR_greater(orders[k], 0, "org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784", _mut98993, _mut98994, _mut98995, _mut98996, _mut98997)) {
                    try {
                        term *= AOR_divide(FastMath.pow(delta[k], orders[k]), CombinatoricsUtils.factorial(orders[k]), "org.apache.commons.math3.analysis.differentiation.DSCompiler.taylor_1784", _mut98998, _mut98999, _mut99000, _mut99001);
                    } catch (NotPositiveException e) {
                        // this cannot happen
                        throw new MathInternalError(e);
                    }
                }
            }
            value += term;
        }
        return value;
    }

    /**
     * Check rules set compatibility.
     * @param compiler other compiler to check against instance
     * @exception DimensionMismatchException if number of free parameters or orders are inconsistent
     */
    public void checkCompatibility(final DSCompiler compiler) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.DSCompiler.checkCompatibility_1810");
        if (ROR_not_equals(parameters, compiler.parameters, "org.apache.commons.math3.analysis.differentiation.DSCompiler.checkCompatibility_1810", _mut99012, _mut99013, _mut99014, _mut99015, _mut99016)) {
            throw new DimensionMismatchException(parameters, compiler.parameters);
        }
        if (ROR_not_equals(order, compiler.order, "org.apache.commons.math3.analysis.differentiation.DSCompiler.checkCompatibility_1810", _mut99017, _mut99018, _mut99019, _mut99020, _mut99021)) {
            throw new DimensionMismatchException(order, compiler.order);
        }
    }
}
