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
package org.apache.commons.math3.stat.inference;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the <a href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test">
 * Kolmogorov-Smirnov (K-S) test</a> for equality of continuous distributions.
 * <p>
 * The K-S test uses a statistic based on the maximum deviation of the empirical distribution of
 * sample data points from the distribution expected under the null hypothesis. For one-sample tests
 * evaluating the null hypothesis that a set of sample data points follow a given distribution, the
 * test statistic is \(D_n=\sup_x |F_n(x)-F(x)|\), where \(F\) is the expected distribution and
 * \(F_n\) is the empirical distribution of the \(n\) sample data points. The distribution of
 * \(D_n\) is estimated using a method based on [1] with certain quick decisions for extreme values
 * given in [2].
 * </p>
 * <p>
 * Two-sample tests are also supported, evaluating the null hypothesis that the two samples
 * {@code x} and {@code y} come from the same underlying distribution. In this case, the test
 * statistic is \(D_{n,m}=\sup_t | F_n(t)-F_m(t)|\) where \(n\) is the length of {@code x}, \(m\) is
 * the length of {@code y}, \(F_n\) is the empirical distribution that puts mass \(1/n\) at each of
 * the values in {@code x} and \(F_m\) is the empirical distribution of the {@code y} values. The
 * default 2-sample test method, {@link #kolmogorovSmirnovTest(double[], double[])} works as
 * follows:
 * <ul>
 * <li>For small samples (where the product of the sample sizes is less than
 * {@value #LARGE_SAMPLE_PRODUCT}), the method presented in [4] is used to compute the
 * exact p-value for the 2-sample test.</li>
 * <li>When the product of the sample sizes exceeds {@value #LARGE_SAMPLE_PRODUCT}, the asymptotic
 * distribution of \(D_{n,m}\) is used. See {@link #approximateP(double, int, int)} for details on
 * the approximation.</li>
 * </ul></p><p>
 * If the product of the sample sizes is less than {@value #LARGE_SAMPLE_PRODUCT} and the sample
 * data contains ties, random jitter is added to the sample data to break ties before applying
 * the algorithm above. Alternatively, the {@link #bootstrap(double[], double[], int, boolean)}
 * method, modeled after <a href="http://sekhon.berkeley.edu/matching/ks.boot.html">ks.boot</a>
 * in the R Matching package [3], can be used if ties are known to be present in the data.
 * </p>
 * <p>
 * In the two-sample case, \(D_{n,m}\) has a discrete distribution. This makes the p-value
 * associated with the null hypothesis \(H_0 : D_{n,m} \ge d \) differ from \(H_0 : D_{n,m} > d \)
 * by the mass of the observed value \(d\). To distinguish these, the two-sample tests use a boolean
 * {@code strict} parameter. This parameter is ignored for large samples.
 * </p>
 * <p>
 * The methods used by the 2-sample default implementation are also exposed directly:
 * <ul>
 * <li>{@link #exactP(double, int, int, boolean)} computes exact 2-sample p-values</li>
 * <li>{@link #approximateP(double, int, int)} uses the asymptotic distribution The {@code boolean}
 * arguments in the first two methods allow the probability used to estimate the p-value to be
 * expressed using strict or non-strict inequality. See
 * {@link #kolmogorovSmirnovTest(double[], double[], boolean)}.</li>
 * </ul>
 * </p>
 * <p>
 * References:
 * <ul>
 * <li>[1] <a href="http://www.jstatsoft.org/v08/i18/"> Evaluating Kolmogorov's Distribution</a> by
 * George Marsaglia, Wai Wan Tsang, and Jingbo Wang</li>
 * <li>[2] <a href="http://www.jstatsoft.org/v39/i11/"> Computing the Two-Sided Kolmogorov-Smirnov
 * Distribution</a> by Richard Simard and Pierre L'Ecuyer</li>
 * <li>[3] Jasjeet S. Sekhon. 2011. <a href="http://www.jstatsoft.org/article/view/v042i07">
 * Multivariate and Propensity Score Matching Software with Automated Balance Optimization:
 * The Matching package for R</a> Journal of Statistical Software, 42(7): 1-52.</li>
 * <li>[4] Wilcox, Rand. 2012. Introduction to Robust Estimation and Hypothesis Testing,
 * Chapter 5, 3rd Ed. Academic Press.</li>
 * </ul>
 * <br/>
 * Note that [1] contains an error in computing h, refer to <a
 * href="https://issues.apache.org/jira/browse/MATH-437">MATH-437</a> for details.
 * </p>
 *
 * @since 3.3
 */
public class KolmogorovSmirnovTest {

    @Conditional
    public static boolean _mut6240 = false, _mut6241 = false, _mut6242 = false, _mut6243 = false, _mut6244 = false, _mut6245 = false, _mut6246 = false, _mut6247 = false, _mut6248 = false, _mut6249 = false, _mut6250 = false, _mut6251 = false, _mut6252 = false, _mut6253 = false, _mut6254 = false, _mut6255 = false, _mut6256 = false, _mut6257 = false, _mut6258 = false, _mut6259 = false, _mut6260 = false, _mut6261 = false, _mut6262 = false, _mut6263 = false, _mut6264 = false, _mut6265 = false, _mut6266 = false, _mut6267 = false, _mut6268 = false, _mut6269 = false, _mut6270 = false, _mut6271 = false, _mut6272 = false, _mut6273 = false, _mut6274 = false, _mut6275 = false, _mut6276 = false, _mut6277 = false, _mut6278 = false, _mut6279 = false, _mut6280 = false, _mut6281 = false, _mut6282 = false, _mut6283 = false, _mut6284 = false, _mut6285 = false, _mut6286 = false, _mut6287 = false, _mut6288 = false, _mut6289 = false, _mut6290 = false, _mut6291 = false, _mut6292 = false, _mut6293 = false, _mut6294 = false, _mut6295 = false, _mut6296 = false, _mut6297 = false, _mut6298 = false, _mut6299 = false, _mut6300 = false, _mut6301 = false, _mut6302 = false, _mut6303 = false, _mut6304 = false, _mut6305 = false, _mut6306 = false, _mut6307 = false, _mut6308 = false, _mut6309 = false, _mut6310 = false, _mut6311 = false, _mut6312 = false, _mut6313 = false, _mut6314 = false, _mut6315 = false, _mut6316 = false, _mut6317 = false, _mut6318 = false, _mut6319 = false, _mut6320 = false, _mut6321 = false, _mut6322 = false, _mut6323 = false, _mut6324 = false, _mut6325 = false, _mut6326 = false, _mut6327 = false, _mut6328 = false, _mut6329 = false, _mut6330 = false, _mut6331 = false, _mut6332 = false, _mut6333 = false, _mut6334 = false, _mut6335 = false, _mut6336 = false, _mut6337 = false, _mut6338 = false, _mut6339 = false, _mut6340 = false, _mut6341 = false, _mut6342 = false, _mut6343 = false, _mut6344 = false, _mut6345 = false, _mut6346 = false, _mut6347 = false, _mut6348 = false, _mut6349 = false, _mut6350 = false, _mut6351 = false, _mut6352 = false, _mut6353 = false, _mut6354 = false, _mut6355 = false, _mut6356 = false, _mut6357 = false, _mut6358 = false, _mut6359 = false, _mut6360 = false, _mut6361 = false, _mut6362 = false, _mut6363 = false, _mut6364 = false, _mut6365 = false, _mut6366 = false, _mut6367 = false, _mut6368 = false, _mut6369 = false, _mut6370 = false, _mut6371 = false, _mut6372 = false, _mut6373 = false, _mut6374 = false, _mut6375 = false, _mut6376 = false, _mut6377 = false, _mut6378 = false, _mut6379 = false, _mut6380 = false, _mut6381 = false, _mut6382 = false, _mut6383 = false, _mut6384 = false, _mut6385 = false, _mut6386 = false, _mut6387 = false, _mut6388 = false, _mut6389 = false, _mut6390 = false, _mut6391 = false, _mut6392 = false, _mut6393 = false, _mut6394 = false, _mut6395 = false, _mut6396 = false, _mut6397 = false, _mut6398 = false, _mut6399 = false, _mut6400 = false, _mut6401 = false, _mut6402 = false, _mut6403 = false, _mut6404 = false, _mut6405 = false, _mut6406 = false, _mut6407 = false, _mut6408 = false, _mut6409 = false, _mut6410 = false, _mut6411 = false, _mut6412 = false, _mut6413 = false, _mut6414 = false, _mut6415 = false, _mut6416 = false, _mut6417 = false, _mut6418 = false, _mut6419 = false, _mut6420 = false, _mut6421 = false, _mut6422 = false, _mut6423 = false, _mut6424 = false, _mut6425 = false, _mut6426 = false, _mut6427 = false, _mut6428 = false, _mut6429 = false, _mut6430 = false, _mut6431 = false, _mut6432 = false, _mut6433 = false, _mut6434 = false, _mut6435 = false, _mut6436 = false, _mut6437 = false, _mut6438 = false, _mut6439 = false, _mut6440 = false, _mut6441 = false, _mut6442 = false, _mut6443 = false, _mut6444 = false, _mut6445 = false, _mut6446 = false, _mut6447 = false, _mut6448 = false, _mut6449 = false, _mut6450 = false, _mut6451 = false, _mut6452 = false, _mut6453 = false, _mut6454 = false, _mut6455 = false, _mut6456 = false, _mut6457 = false, _mut6458 = false, _mut6459 = false, _mut6460 = false, _mut6461 = false, _mut6462 = false, _mut6463 = false, _mut6464 = false, _mut6465 = false, _mut6466 = false, _mut6467 = false, _mut6468 = false, _mut6469 = false, _mut6470 = false, _mut6471 = false, _mut6472 = false, _mut6473 = false, _mut6474 = false, _mut6475 = false, _mut6476 = false, _mut6477 = false, _mut6478 = false, _mut6479 = false, _mut6480 = false, _mut6481 = false, _mut6482 = false, _mut6483 = false, _mut6484 = false, _mut6485 = false, _mut6486 = false, _mut6487 = false, _mut6488 = false, _mut6489 = false, _mut6490 = false, _mut6491 = false, _mut6492 = false, _mut6493 = false, _mut6494 = false, _mut6495 = false, _mut6496 = false, _mut6497 = false, _mut6498 = false, _mut6499 = false, _mut6500 = false, _mut6501 = false, _mut6502 = false, _mut6503 = false, _mut6504 = false, _mut6505 = false, _mut6506 = false, _mut6507 = false, _mut6508 = false, _mut6509 = false, _mut6510 = false, _mut6511 = false, _mut6512 = false, _mut6513 = false, _mut6514 = false, _mut6515 = false, _mut6516 = false, _mut6517 = false, _mut6518 = false, _mut6519 = false, _mut6520 = false, _mut6521 = false, _mut6522 = false, _mut6523 = false, _mut6524 = false, _mut6525 = false, _mut6526 = false, _mut6527 = false, _mut6528 = false, _mut6529 = false, _mut6530 = false, _mut6531 = false, _mut6532 = false, _mut6533 = false, _mut6534 = false, _mut6535 = false, _mut6536 = false, _mut6537 = false, _mut6538 = false, _mut6539 = false, _mut6540 = false, _mut6541 = false, _mut6542 = false, _mut6543 = false, _mut6544 = false, _mut6545 = false, _mut6546 = false, _mut6547 = false, _mut6548 = false, _mut6549 = false, _mut6550 = false, _mut6551 = false, _mut6552 = false, _mut6553 = false, _mut6554 = false, _mut6555 = false, _mut6556 = false, _mut6557 = false, _mut6558 = false, _mut6559 = false, _mut6560 = false, _mut6561 = false, _mut6562 = false, _mut6563 = false, _mut6564 = false, _mut6565 = false, _mut6566 = false, _mut6567 = false, _mut6568 = false, _mut6569 = false, _mut6570 = false, _mut6571 = false, _mut6572 = false, _mut6573 = false, _mut6574 = false, _mut6575 = false, _mut6576 = false, _mut6577 = false, _mut6578 = false, _mut6579 = false, _mut6580 = false, _mut6581 = false, _mut6582 = false, _mut6583 = false, _mut6584 = false, _mut6585 = false, _mut6586 = false, _mut6587 = false, _mut6588 = false, _mut6589 = false, _mut6590 = false, _mut6591 = false, _mut6592 = false, _mut6593 = false, _mut6594 = false, _mut6595 = false, _mut6596 = false, _mut6597 = false, _mut6598 = false, _mut6599 = false, _mut6600 = false, _mut6601 = false, _mut6602 = false, _mut6603 = false, _mut6604 = false, _mut6605 = false, _mut6606 = false, _mut6607 = false, _mut6608 = false, _mut6609 = false, _mut6610 = false, _mut6611 = false, _mut6612 = false, _mut6613 = false, _mut6614 = false, _mut6615 = false, _mut6616 = false, _mut6617 = false, _mut6618 = false, _mut6619 = false, _mut6620 = false, _mut6621 = false, _mut6622 = false, _mut6623 = false, _mut6624 = false, _mut6625 = false, _mut6626 = false, _mut6627 = false, _mut6628 = false, _mut6629 = false, _mut6630 = false, _mut6631 = false, _mut6632 = false, _mut6633 = false, _mut6634 = false, _mut6635 = false, _mut6636 = false, _mut6637 = false, _mut6638 = false, _mut6639 = false, _mut6640 = false, _mut6641 = false, _mut6642 = false, _mut6643 = false, _mut6644 = false, _mut6645 = false, _mut6646 = false, _mut6647 = false, _mut6648 = false, _mut6649 = false, _mut6650 = false, _mut6651 = false, _mut6652 = false, _mut6653 = false, _mut6654 = false, _mut6655 = false, _mut6656 = false, _mut6657 = false, _mut6658 = false, _mut6659 = false, _mut6660 = false, _mut6661 = false, _mut6662 = false, _mut6663 = false, _mut6664 = false, _mut6665 = false, _mut6666 = false, _mut6667 = false, _mut6668 = false, _mut6669 = false, _mut6670 = false, _mut6671 = false, _mut6672 = false, _mut6673 = false, _mut6674 = false, _mut6675 = false, _mut6676 = false, _mut6677 = false, _mut6678 = false, _mut6679 = false, _mut6680 = false, _mut6681 = false, _mut6682 = false, _mut6683 = false, _mut6684 = false, _mut6685 = false, _mut6686 = false, _mut6687 = false, _mut6688 = false, _mut6689 = false, _mut6690 = false, _mut6691 = false, _mut6692 = false, _mut6693 = false, _mut6694 = false, _mut6695 = false, _mut6696 = false, _mut6697 = false, _mut6698 = false, _mut6699 = false, _mut6700 = false, _mut6701 = false, _mut6702 = false, _mut6703 = false, _mut6704 = false, _mut6705 = false, _mut6706 = false, _mut6707 = false, _mut6708 = false, _mut6709 = false, _mut6710 = false, _mut6711 = false, _mut6712 = false, _mut6713 = false, _mut6714 = false, _mut6715 = false, _mut6716 = false, _mut6717 = false, _mut6718 = false, _mut6719 = false, _mut6720 = false, _mut6721 = false, _mut6722 = false, _mut6723 = false, _mut6724 = false, _mut6725 = false, _mut6726 = false, _mut6727 = false, _mut6728 = false, _mut6729 = false, _mut6730 = false, _mut6731 = false, _mut6732 = false, _mut6733 = false, _mut6734 = false, _mut6735 = false, _mut6736 = false, _mut6737 = false, _mut6738 = false, _mut6739 = false, _mut6740 = false, _mut6741 = false, _mut6742 = false, _mut6743 = false, _mut6744 = false, _mut6745 = false, _mut6746 = false, _mut6747 = false, _mut6748 = false, _mut6749 = false, _mut6750 = false, _mut6751 = false, _mut6752 = false, _mut6753 = false, _mut6754 = false, _mut6755 = false, _mut6756 = false, _mut6757 = false, _mut6758 = false, _mut6759 = false, _mut6760 = false, _mut6761 = false, _mut6762 = false, _mut6763 = false, _mut6764 = false, _mut6765 = false, _mut6766 = false, _mut6767 = false, _mut6768 = false, _mut6769 = false, _mut6770 = false, _mut6771 = false, _mut6772 = false, _mut6773 = false, _mut6774 = false, _mut6775 = false, _mut6776 = false, _mut6777 = false, _mut6778 = false, _mut6779 = false, _mut6780 = false, _mut6781 = false, _mut6782 = false, _mut6783 = false, _mut6784 = false, _mut6785 = false, _mut6786 = false, _mut6787 = false, _mut6788 = false, _mut6789 = false, _mut6790 = false, _mut6791 = false, _mut6792 = false, _mut6793 = false, _mut6794 = false, _mut6795 = false, _mut6796 = false, _mut6797 = false, _mut6798 = false, _mut6799 = false, _mut6800 = false, _mut6801 = false, _mut6802 = false, _mut6803 = false, _mut6804 = false, _mut6805 = false, _mut6806 = false, _mut6807 = false, _mut6808 = false, _mut6809 = false, _mut6810 = false, _mut6811 = false, _mut6812 = false, _mut6813 = false, _mut6814 = false, _mut6815 = false, _mut6816 = false, _mut6817 = false, _mut6818 = false, _mut6819 = false, _mut6820 = false, _mut6821 = false, _mut6822 = false, _mut6823 = false, _mut6824 = false, _mut6825 = false, _mut6826 = false, _mut6827 = false, _mut6828 = false, _mut6829 = false, _mut6830 = false, _mut6831 = false, _mut6832 = false, _mut6833 = false, _mut6834 = false, _mut6835 = false, _mut6836 = false, _mut6837 = false, _mut6838 = false, _mut6839 = false, _mut6840 = false, _mut6841 = false, _mut6842 = false, _mut6843 = false, _mut6844 = false, _mut6845 = false, _mut6846 = false, _mut6847 = false, _mut6848 = false, _mut6849 = false, _mut6850 = false, _mut6851 = false, _mut6852 = false, _mut6853 = false, _mut6854 = false, _mut6855 = false, _mut6856 = false, _mut6857 = false, _mut6858 = false, _mut6859 = false, _mut6860 = false, _mut6861 = false, _mut6862 = false, _mut6863 = false, _mut6864 = false, _mut6865 = false, _mut6866 = false, _mut6867 = false, _mut6868 = false, _mut6869 = false, _mut6870 = false, _mut6871 = false, _mut6872 = false, _mut6873 = false, _mut6874 = false, _mut6875 = false, _mut6876 = false, _mut6877 = false, _mut6878 = false, _mut6879 = false, _mut6880 = false, _mut6881 = false, _mut6882 = false, _mut6883 = false, _mut6884 = false, _mut6885 = false, _mut6886 = false, _mut6887 = false, _mut6888 = false, _mut6889 = false, _mut6890 = false, _mut6891 = false, _mut6892 = false, _mut6893 = false, _mut6894 = false, _mut6895 = false, _mut6896 = false, _mut6897 = false, _mut6898 = false, _mut6899 = false, _mut6900 = false, _mut6901 = false, _mut6902 = false, _mut6903 = false, _mut6904 = false, _mut6905 = false, _mut6906 = false, _mut6907 = false, _mut6908 = false, _mut6909 = false, _mut6910 = false, _mut6911 = false, _mut6912 = false, _mut6913 = false, _mut6914 = false, _mut6915 = false, _mut6916 = false, _mut6917 = false, _mut6918 = false, _mut6919 = false, _mut6920 = false, _mut6921 = false, _mut6922 = false, _mut6923 = false, _mut6924 = false, _mut6925 = false, _mut6926 = false, _mut6927 = false, _mut6928 = false, _mut6929 = false, _mut6930 = false, _mut6931 = false, _mut6932 = false, _mut6933 = false, _mut6934 = false, _mut6935 = false, _mut6936 = false, _mut6937 = false, _mut6938 = false, _mut6939 = false, _mut6940 = false, _mut6941 = false, _mut6942 = false, _mut6943 = false, _mut6944 = false, _mut6945 = false, _mut6946 = false, _mut6947 = false, _mut6948 = false, _mut6949 = false, _mut6950 = false, _mut6951 = false, _mut6952 = false, _mut6953 = false, _mut6954 = false, _mut6955 = false, _mut6956 = false, _mut6957 = false, _mut6958 = false, _mut6959 = false, _mut6960 = false, _mut6961 = false, _mut6962 = false, _mut6963 = false, _mut6964 = false, _mut6965 = false, _mut6966 = false, _mut6967 = false, _mut6968 = false, _mut6969 = false, _mut6970 = false, _mut6971 = false, _mut6972 = false, _mut6973 = false, _mut6974 = false, _mut6975 = false, _mut6976 = false, _mut6977 = false, _mut6978 = false, _mut6979 = false, _mut6980 = false, _mut6981 = false, _mut6982 = false, _mut6983 = false, _mut6984 = false, _mut6985 = false, _mut6986 = false, _mut6987 = false, _mut6988 = false, _mut6989 = false, _mut6990 = false, _mut6991 = false, _mut6992 = false, _mut6993 = false, _mut6994 = false, _mut6995 = false, _mut6996 = false, _mut6997 = false, _mut6998 = false, _mut6999 = false, _mut7000 = false, _mut7001 = false, _mut7002 = false, _mut7003 = false, _mut7004 = false, _mut7005 = false, _mut7006 = false, _mut7007 = false, _mut7008 = false, _mut7009 = false, _mut7010 = false, _mut7011 = false, _mut7012 = false, _mut7013 = false, _mut7014 = false, _mut7015 = false, _mut7016 = false, _mut7017 = false, _mut7018 = false, _mut7019 = false, _mut7020 = false, _mut7021 = false, _mut7022 = false, _mut7023 = false, _mut7024 = false, _mut7025 = false, _mut7026 = false, _mut7027 = false, _mut7028 = false, _mut7029 = false, _mut7030 = false, _mut7031 = false, _mut7032 = false, _mut7033 = false, _mut7034 = false, _mut7035 = false, _mut7036 = false, _mut7037 = false, _mut7038 = false, _mut7039 = false, _mut7040 = false, _mut7041 = false, _mut7042 = false, _mut7043 = false, _mut7044 = false, _mut7045 = false, _mut7046 = false, _mut7047 = false, _mut7048 = false, _mut7049 = false, _mut7050 = false, _mut7051 = false, _mut7052 = false, _mut7053 = false, _mut7054 = false, _mut7055 = false, _mut7056 = false, _mut7057 = false, _mut7058 = false, _mut7059 = false, _mut7060 = false, _mut7061 = false, _mut7062 = false, _mut7063 = false, _mut7064 = false, _mut7065 = false, _mut7066 = false, _mut7067 = false, _mut7068 = false, _mut7069 = false, _mut7070 = false, _mut7071 = false, _mut7072 = false, _mut7073 = false, _mut7074 = false, _mut7075 = false, _mut7076 = false, _mut7077 = false, _mut7078 = false, _mut7079 = false, _mut7080 = false, _mut7081 = false, _mut7082 = false, _mut7083 = false, _mut7084 = false, _mut7085 = false, _mut7086 = false, _mut7087 = false, _mut7088 = false, _mut7089 = false, _mut7090 = false, _mut7091 = false, _mut7092 = false, _mut7093 = false, _mut7094 = false, _mut7095 = false, _mut7096 = false, _mut7097 = false, _mut7098 = false, _mut7099 = false, _mut7100 = false, _mut7101 = false, _mut7102 = false, _mut7103 = false, _mut7104 = false, _mut7105 = false, _mut7106 = false, _mut7107 = false, _mut7108 = false, _mut7109 = false, _mut7110 = false, _mut7111 = false, _mut7112 = false, _mut7113 = false, _mut7114 = false, _mut7115 = false, _mut7116 = false, _mut7117 = false, _mut7118 = false, _mut7119 = false, _mut7120 = false, _mut7121 = false, _mut7122 = false, _mut7123 = false, _mut7124 = false, _mut7125 = false, _mut7126 = false, _mut7127 = false, _mut7128 = false, _mut7129 = false, _mut7130 = false, _mut7131 = false, _mut7132 = false, _mut7133 = false, _mut7134 = false, _mut7135 = false, _mut7136 = false, _mut7137 = false, _mut7138 = false, _mut7139 = false, _mut7140 = false, _mut7141 = false, _mut7142 = false, _mut7143 = false, _mut7144 = false, _mut7145 = false, _mut7146 = false, _mut7147 = false, _mut7148 = false, _mut7149 = false, _mut7150 = false, _mut7151 = false, _mut7152 = false, _mut7153 = false, _mut7154 = false, _mut7155 = false, _mut7156 = false, _mut7157 = false, _mut7158 = false, _mut7159 = false, _mut7160 = false, _mut7161 = false, _mut7162 = false, _mut7163 = false, _mut7164 = false, _mut7165 = false, _mut7166 = false, _mut7167 = false, _mut7168 = false, _mut7169 = false, _mut7170 = false, _mut7171 = false, _mut7172 = false, _mut7173 = false, _mut7174 = false, _mut7175 = false, _mut7176 = false, _mut7177 = false, _mut7178 = false, _mut7179 = false, _mut7180 = false, _mut7181 = false, _mut7182 = false, _mut7183 = false, _mut7184 = false, _mut7185 = false, _mut7186 = false, _mut7187 = false, _mut7188 = false, _mut7189 = false, _mut7190 = false, _mut7191 = false, _mut7192 = false, _mut7193 = false, _mut7194 = false, _mut7195 = false, _mut7196 = false, _mut7197 = false, _mut7198 = false, _mut7199 = false, _mut7200 = false, _mut7201 = false, _mut7202 = false, _mut7203 = false, _mut7204 = false, _mut7205 = false, _mut7206 = false, _mut7207 = false, _mut7208 = false, _mut7209 = false, _mut7210 = false, _mut7211 = false, _mut7212 = false, _mut7213 = false, _mut7214 = false, _mut7215 = false, _mut7216 = false, _mut7217 = false, _mut7218 = false, _mut7219 = false, _mut7220 = false, _mut7221 = false, _mut7222 = false, _mut7223 = false, _mut7224 = false, _mut7225 = false, _mut7226 = false, _mut7227 = false, _mut7228 = false, _mut7229 = false, _mut7230 = false, _mut7231 = false, _mut7232 = false, _mut7233 = false, _mut7234 = false, _mut7235 = false, _mut7236 = false, _mut7237 = false, _mut7238 = false, _mut7239 = false, _mut7240 = false, _mut7241 = false, _mut7242 = false, _mut7243 = false, _mut7244 = false, _mut7245 = false, _mut7246 = false, _mut7247 = false, _mut7248 = false, _mut7249 = false, _mut7250 = false, _mut7251 = false, _mut7252 = false, _mut7253 = false, _mut7254 = false, _mut7255 = false, _mut7256 = false, _mut7257 = false, _mut7258 = false, _mut7259 = false, _mut7260 = false, _mut7261 = false, _mut7262 = false, _mut7263 = false, _mut7264 = false, _mut7265 = false, _mut7266 = false, _mut7267 = false, _mut7268 = false, _mut7269 = false, _mut7270 = false, _mut7271 = false, _mut7272 = false, _mut7273 = false, _mut7274 = false, _mut7275 = false, _mut7276 = false, _mut7277 = false, _mut7278 = false, _mut7279 = false, _mut7280 = false, _mut7281 = false, _mut7282 = false, _mut7283 = false, _mut7284 = false, _mut7285 = false, _mut7286 = false, _mut7287 = false, _mut7288 = false, _mut7289 = false, _mut7290 = false, _mut7291 = false, _mut7292 = false, _mut7293 = false, _mut7294 = false, _mut7295 = false, _mut7296 = false, _mut7297 = false, _mut7298 = false, _mut7299 = false, _mut7300 = false, _mut7301 = false, _mut7302 = false, _mut7303 = false, _mut7304 = false, _mut7305 = false, _mut7306 = false, _mut7307 = false, _mut7308 = false, _mut7309 = false, _mut7310 = false, _mut7311 = false, _mut7312 = false, _mut7313 = false, _mut7314 = false, _mut7315 = false, _mut7316 = false, _mut7317 = false, _mut7318 = false, _mut7319 = false, _mut7320 = false, _mut7321 = false, _mut7322 = false, _mut7323 = false, _mut7324 = false, _mut7325 = false, _mut7326 = false, _mut7327 = false, _mut7328 = false, _mut7329 = false, _mut7330 = false, _mut7331 = false, _mut7332 = false, _mut7333 = false, _mut7334 = false, _mut7335 = false, _mut7336 = false, _mut7337 = false, _mut7338 = false, _mut7339 = false, _mut7340 = false, _mut7341 = false, _mut7342 = false, _mut7343 = false, _mut7344 = false, _mut7345 = false, _mut7346 = false, _mut7347 = false, _mut7348 = false, _mut7349 = false, _mut7350 = false, _mut7351 = false, _mut7352 = false, _mut7353 = false, _mut7354 = false, _mut7355 = false, _mut7356 = false, _mut7357 = false, _mut7358 = false, _mut7359 = false, _mut7360 = false, _mut7361 = false, _mut7362 = false, _mut7363 = false, _mut7364 = false, _mut7365 = false, _mut7366 = false, _mut7367 = false, _mut7368 = false, _mut7369 = false, _mut7370 = false, _mut7371 = false, _mut7372 = false, _mut7373 = false, _mut7374 = false, _mut7375 = false, _mut7376 = false, _mut7377 = false, _mut7378 = false, _mut7379 = false, _mut7380 = false, _mut7381 = false, _mut7382 = false, _mut7383 = false, _mut7384 = false, _mut7385 = false, _mut7386 = false, _mut7387 = false, _mut7388 = false, _mut7389 = false, _mut7390 = false, _mut7391 = false, _mut7392 = false, _mut7393 = false, _mut7394 = false, _mut7395 = false, _mut7396 = false, _mut7397 = false, _mut7398 = false, _mut7399 = false, _mut7400 = false, _mut7401 = false, _mut7402 = false, _mut7403 = false, _mut7404 = false, _mut7405 = false, _mut7406 = false, _mut7407 = false, _mut7408 = false, _mut7409 = false, _mut7410 = false, _mut7411 = false, _mut7412 = false, _mut7413 = false, _mut7414 = false, _mut7415 = false, _mut7416 = false, _mut7417 = false, _mut7418 = false, _mut7419 = false, _mut7420 = false, _mut7421 = false, _mut7422 = false, _mut7423 = false, _mut7424 = false, _mut7425 = false, _mut7426 = false, _mut7427 = false, _mut7428 = false, _mut7429 = false, _mut7430 = false, _mut7431 = false, _mut7432 = false, _mut7433 = false, _mut7434 = false, _mut7435 = false, _mut7436 = false, _mut7437 = false, _mut7438 = false, _mut7439 = false, _mut7440 = false, _mut7441 = false, _mut7442 = false, _mut7443 = false, _mut7444 = false, _mut7445 = false, _mut7446 = false, _mut7447 = false, _mut7448 = false, _mut7449 = false, _mut7450 = false, _mut7451 = false, _mut7452 = false, _mut7453 = false, _mut7454 = false, _mut7455 = false, _mut7456 = false, _mut7457 = false, _mut7458 = false, _mut7459 = false, _mut7460 = false, _mut7461 = false, _mut7462 = false, _mut7463 = false, _mut7464 = false, _mut7465 = false, _mut7466 = false, _mut7467 = false, _mut7468 = false, _mut7469 = false, _mut7470 = false, _mut7471 = false, _mut7472 = false, _mut7473 = false, _mut7474 = false, _mut7475 = false, _mut7476 = false, _mut7477 = false, _mut7478 = false, _mut7479 = false, _mut7480 = false, _mut7481 = false, _mut7482 = false, _mut7483 = false, _mut7484 = false, _mut7485 = false, _mut7486 = false, _mut7487 = false, _mut7488 = false, _mut7489 = false, _mut7490 = false, _mut7491 = false, _mut7492 = false, _mut7493 = false, _mut7494 = false, _mut7495 = false, _mut7496 = false, _mut7497 = false, _mut7498 = false, _mut7499 = false, _mut7500 = false, _mut7501 = false, _mut7502 = false, _mut7503 = false, _mut7504 = false, _mut7505 = false, _mut7506 = false, _mut7507 = false, _mut7508 = false, _mut7509 = false, _mut7510 = false, _mut7511 = false, _mut7512 = false, _mut7513 = false, _mut7514 = false, _mut7515 = false, _mut7516 = false, _mut7517 = false, _mut7518 = false, _mut7519 = false, _mut7520 = false, _mut7521 = false, _mut7522 = false, _mut7523 = false, _mut7524 = false, _mut7525 = false, _mut7526 = false, _mut7527 = false, _mut7528 = false, _mut7529 = false, _mut7530 = false, _mut7531 = false, _mut7532 = false, _mut7533 = false, _mut7534 = false, _mut7535 = false, _mut7536 = false, _mut7537 = false, _mut7538 = false, _mut7539 = false, _mut7540 = false, _mut7541 = false, _mut7542 = false, _mut7543 = false, _mut7544 = false, _mut7545 = false, _mut7546 = false, _mut7547 = false, _mut7548 = false, _mut7549 = false, _mut7550 = false, _mut7551 = false, _mut7552 = false, _mut7553 = false, _mut7554 = false, _mut7555 = false, _mut7556 = false, _mut7557 = false, _mut7558 = false, _mut7559 = false, _mut7560 = false, _mut7561 = false, _mut7562 = false, _mut7563 = false, _mut7564 = false, _mut7565 = false, _mut7566 = false, _mut7567 = false, _mut7568 = false, _mut7569 = false, _mut7570 = false, _mut7571 = false, _mut7572 = false, _mut7573 = false, _mut7574 = false, _mut7575 = false, _mut7576 = false, _mut7577 = false, _mut7578 = false, _mut7579 = false, _mut7580 = false, _mut7581 = false, _mut7582 = false, _mut7583 = false, _mut7584 = false, _mut7585 = false, _mut7586 = false, _mut7587 = false, _mut7588 = false, _mut7589 = false, _mut7590 = false, _mut7591 = false, _mut7592 = false, _mut7593 = false, _mut7594 = false, _mut7595 = false, _mut7596 = false, _mut7597 = false, _mut7598 = false, _mut7599 = false, _mut7600 = false, _mut7601 = false, _mut7602 = false, _mut7603 = false, _mut7604 = false, _mut7605 = false;

    /**
     * Bound on the number of partial sums in {@link #ksSum(double, double, int)}
     */
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;

    /**
     * Convergence criterion for {@link #ksSum(double, double, int)}
     */
    protected static final double KS_SUM_CAUCHY_CRITERION = 1E-20;

    /**
     * Convergence criterion for the sums in #pelzGood(double, double, int)}
     */
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0e-10;

    /**
     * No longer used.
     */
    @Deprecated
    protected static final int SMALL_SAMPLE_PRODUCT = 200;

    /**
     * When product of sample sizes exceeds this value, 2-sample K-S test uses asymptotic
     * distribution to compute the p-value.
     */
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;

    /**
     * Default number of iterations used by {@link #monteCarloP(double, int, int, boolean, int)}.
     *  Deprecated as of version 3.6, as this method is no longer needed.
     */
    @Deprecated
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;

    /**
     * Random data generator used by {@link #monteCarloP(double, int, int, boolean, int)}
     */
    private final RandomGenerator rng;

    /**
     * Construct a KolmogorovSmirnovTest instance with a default random data generator.
     */
    public KolmogorovSmirnovTest() {
        rng = new Well19937c();
    }

    /**
     * Construct a KolmogorovSmirnovTest with the provided random data generator.
     * The #monteCarloP(double, int, int, boolean, int) that uses the generator supplied to this
     * constructor is deprecated as of version 3.6.
     *
     * @param rng random data generator used by {@link #monteCarloP(double, int, int, boolean, int)}
     */
    @Deprecated
    public KolmogorovSmirnovTest(RandomGenerator rng) {
        this.rng = rng;
    }

    /**
     * Computes the <i>p-value</i>, or <i>observed significance level</i>, of a one-sample <a
     * href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov test</a>
     * evaluating the null hypothesis that {@code data} conforms to {@code distribution}. If
     * {@code exact} is true, the distribution used to compute the p-value is computed using
     * extended precision. See {@link #cdfExact(double, int)}.
     *
     * @param distribution reference distribution
     * @param data sample being being evaluated
     * @param exact whether or not to force exact computation of the p-value
     * @return the p-value associated with the null hypothesis that {@code data} is a sample from
     *         {@code distribution}
     * @throws InsufficientDataException if {@code data} does not have length at least 2
     * @throws NullArgumentException if {@code data} is null
     */
    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data, boolean exact) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_185");
        return AOR_minus(1d, cdf(kolmogorovSmirnovStatistic(distribution, data), data.length, exact), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_185", _mut6240, _mut6241, _mut6242, _mut6243);
    }

    /**
     * Computes the one-sample Kolmogorov-Smirnov test statistic, \(D_n=\sup_x |F_n(x)-F(x)|\) where
     * \(F\) is the distribution (cdf) function associated with {@code distribution}, \(n\) is the
     * length of {@code data} and \(F_n\) is the empirical distribution that puts mass \(1/n\) at
     * each of the values in {@code data}.
     *
     * @param distribution reference distribution
     * @param data sample being evaluated
     * @return Kolmogorov-Smirnov statistic \(D_n\)
     * @throws InsufficientDataException if {@code data} does not have length at least 2
     * @throws NullArgumentException if {@code data} is null
     */
    public double kolmogorovSmirnovStatistic(RealDistribution distribution, double[] data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201");
        checkArray(data);
        final int n = data.length;
        final double nd = n;
        final double[] dataCopy = new double[n];
        System.arraycopy(data, 0, dataCopy, 0, n);
        Arrays.sort(dataCopy);
        double d = 0d;
        for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6273, _mut6274, _mut6275, _mut6276, _mut6277); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201");
            final double yi = distribution.cumulativeProbability(dataCopy[AOR_minus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6244, _mut6245, _mut6246, _mut6247)]);
            final double currD = FastMath.max(AOR_minus(yi, AOR_divide((AOR_minus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6248, _mut6249, _mut6250, _mut6251)), nd, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6252, _mut6253, _mut6254, _mut6255), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6256, _mut6257, _mut6258, _mut6259), AOR_minus(AOR_divide(i, nd, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6260, _mut6261, _mut6262, _mut6263), yi, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6264, _mut6265, _mut6266, _mut6267));
            if (ROR_greater(currD, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_201", _mut6268, _mut6269, _mut6270, _mut6271, _mut6272)) {
                d = currD;
            }
        }
        return d;
    }

    /**
     * Computes the <i>p-value</i>, or <i>observed significance level</i>, of a two-sample <a
     * href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov test</a>
     * evaluating the null hypothesis that {@code x} and {@code y} are samples drawn from the same
     * probability distribution. Specifically, what is returned is an estimate of the probability
     * that the {@link #kolmogorovSmirnovStatistic(double[], double[])} associated with a randomly
     * selected partition of the combined sample into subsamples of sizes {@code x.length} and
     * {@code y.length} will strictly exceed (if {@code strict} is {@code true}) or be at least as
     * large as {@code strict = false}) as {@code kolmogorovSmirnovStatistic(x, y)}.
     * <ul>
     * <li>For small samples (where the product of the sample sizes is less than
     * {@value #LARGE_SAMPLE_PRODUCT}), the exact p-value is computed using the method presented
     * in [4], implemented in {@link #exactP(double, int, int, boolean)}. </li>
     * <li>When the product of the sample sizes exceeds {@value #LARGE_SAMPLE_PRODUCT}, the
     * asymptotic distribution of \(D_{n,m}\) is used. See {@link #approximateP(double, int, int)}
     * for details on the approximation.</li>
     * </ul><p>
     * If {@code x.length * y.length} < {@value #LARGE_SAMPLE_PRODUCT} and the combined set of values in
     * {@code x} and {@code y} contains ties, random jitter is added to {@code x} and {@code y} to
     * break ties before computing \(D_{n,m}\) and the p-value. The jitter is uniformly distributed
     * on (-minDelta / 2, minDelta / 2) where minDelta is the smallest pairwise difference between
     * values in the combined sample.</p>
     * <p>
     * If ties are known to be present in the data, {@link #bootstrap(double[], double[], int, boolean)}
     * may be used as an alternative method for estimating the p-value.</p>
     *
     * @param x first sample dataset
     * @param y second sample dataset
     * @param strict whether or not the probability to compute is expressed as a strict inequality
     *        (ignored for large samples)
     * @return p-value associated with the null hypothesis that {@code x} and {@code y} represent
     *         samples from the same distribution
     * @throws InsufficientDataException if either {@code x} or {@code y} does not have length at
     *         least 2
     * @throws NullArgumentException if either {@code x} or {@code y} is null
     * @see #bootstrap(double[], double[], int, boolean)
     */
    public double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_256");
        final long lengthProduct = AOR_multiply((long) x.length, y.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_256", _mut6278, _mut6279, _mut6280, _mut6281);
        double[] xa = null;
        double[] ya = null;
        if ((_mut6287 ? (ROR_less(lengthProduct, LARGE_SAMPLE_PRODUCT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_256", _mut6282, _mut6283, _mut6284, _mut6285, _mut6286) || hasTies(x, y)) : (ROR_less(lengthProduct, LARGE_SAMPLE_PRODUCT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_256", _mut6282, _mut6283, _mut6284, _mut6285, _mut6286) && hasTies(x, y)))) {
            xa = MathArrays.copyOf(x);
            ya = MathArrays.copyOf(y);
            fixTies(xa, ya);
        } else {
            xa = x;
            ya = y;
        }
        if (ROR_less(lengthProduct, LARGE_SAMPLE_PRODUCT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_256", _mut6288, _mut6289, _mut6290, _mut6291, _mut6292)) {
            return exactP(kolmogorovSmirnovStatistic(xa, ya), x.length, y.length, strict);
        }
        return approximateP(kolmogorovSmirnovStatistic(x, y), x.length, y.length);
    }

    /**
     * Computes the <i>p-value</i>, or <i>observed significance level</i>, of a two-sample <a
     * href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov test</a>
     * evaluating the null hypothesis that {@code x} and {@code y} are samples drawn from the same
     * probability distribution. Assumes the strict form of the inequality used to compute the
     * p-value. See {@link #kolmogorovSmirnovTest(RealDistribution, double[], boolean)}.
     *
     * @param x first sample dataset
     * @param y second sample dataset
     * @return p-value associated with the null hypothesis that {@code x} and {@code y} represent
     *         samples from the same distribution
     * @throws InsufficientDataException if either {@code x} or {@code y} does not have length at
     *         least 2
     * @throws NullArgumentException if either {@code x} or {@code y} is null
     */
    public double kolmogorovSmirnovTest(double[] x, double[] y) {
        return kolmogorovSmirnovTest(x, y, true);
    }

    /**
     * Computes the two-sample Kolmogorov-Smirnov test statistic, \(D_{n,m}=\sup_x |F_n(x)-F_m(x)|\)
     * where \(n\) is the length of {@code x}, \(m\) is the length of {@code y}, \(F_n\) is the
     * empirical distribution that puts mass \(1/n\) at each of the values in {@code x} and \(F_m\)
     * is the empirical distribution of the {@code y} values.
     *
     * @param x first sample
     * @param y second sample
     * @return test statistic \(D_{n,m}\) used to evaluate the null hypothesis that {@code x} and
     *         {@code y} represent samples from the same underlying distribution
     * @throws InsufficientDataException if either {@code x} or {@code y} does not have length at
     *         least 2
     * @throws NullArgumentException if either {@code x} or {@code y} is null
     */
    public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_307");
        return AOR_divide(integralKolmogorovSmirnovStatistic(x, y), ((double) (AOR_multiply(x.length, (long) y.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_307", _mut6293, _mut6294, _mut6295, _mut6296))), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovStatistic_307", _mut6297, _mut6298, _mut6299, _mut6300);
    }

    /**
     * Computes the two-sample Kolmogorov-Smirnov test statistic, \(D_{n,m}=\sup_x |F_n(x)-F_m(x)|\)
     * where \(n\) is the length of {@code x}, \(m\) is the length of {@code y}, \(F_n\) is the
     * empirical distribution that puts mass \(1/n\) at each of the values in {@code x} and \(F_m\)
     * is the empirical distribution of the {@code y} values. Finally \(n m D_{n,m}\) is returned
     * as long value.
     *
     * @param x first sample
     * @param y second sample
     * @return test statistic \(n m D_{n,m}\) used to evaluate the null hypothesis that {@code x} and
     *         {@code y} represent samples from the same underlying distribution
     * @throws InsufficientDataException if either {@code x} or {@code y} does not have length at
     *         least 2
     * @throws NullArgumentException if either {@code x} or {@code y} is null
     */
    private long integralKolmogorovSmirnovStatistic(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326");
        checkArray(x);
        checkArray(y);
        // Copy and sort the sample arrays
        final double[] sx = MathArrays.copyOf(x);
        final double[] sy = MathArrays.copyOf(y);
        Arrays.sort(sx);
        Arrays.sort(sy);
        final int n = sx.length;
        final int m = sy.length;
        int rankX = 0;
        int rankY = 0;
        long curD = 0l;
        // Find the max difference between cdf_x and cdf_y
        long supD = 0l;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326");
            double z = ROR_less_equals(Double.compare(sx[rankX], sy[rankY]), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6301, _mut6302, _mut6303, _mut6304, _mut6305) ? sx[rankX] : sy[rankY];
            while ((_mut6316 ? (ROR_less(rankX, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6306, _mut6307, _mut6308, _mut6309, _mut6310) || ROR_equals(Double.compare(sx[rankX], z), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6311, _mut6312, _mut6313, _mut6314, _mut6315)) : (ROR_less(rankX, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6306, _mut6307, _mut6308, _mut6309, _mut6310) && ROR_equals(Double.compare(sx[rankX], z), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6311, _mut6312, _mut6313, _mut6314, _mut6315)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326");
                rankX += 1;
                curD += m;
            }
            while ((_mut6327 ? (ROR_less(rankY, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6317, _mut6318, _mut6319, _mut6320, _mut6321) || ROR_equals(Double.compare(sy[rankY], z), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6322, _mut6323, _mut6324, _mut6325, _mut6326)) : (ROR_less(rankY, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6317, _mut6318, _mut6319, _mut6320, _mut6321) && ROR_equals(Double.compare(sy[rankY], z), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6322, _mut6323, _mut6324, _mut6325, _mut6326)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326");
                rankY += 1;
                curD -= n;
            }
            if (ROR_greater(curD, supD, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6328, _mut6329, _mut6330, _mut6331, _mut6332)) {
                supD = curD;
            } else if (ROR_greater(-curD, supD, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6333, _mut6334, _mut6335, _mut6336, _mut6337)) {
                supD = -curD;
            }
        } while ((_mut6348 ? (ROR_less(rankX, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6338, _mut6339, _mut6340, _mut6341, _mut6342) || ROR_less(rankY, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6343, _mut6344, _mut6345, _mut6346, _mut6347)) : (ROR_less(rankX, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6338, _mut6339, _mut6340, _mut6341, _mut6342) && ROR_less(rankY, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralKolmogorovSmirnovStatistic_326", _mut6343, _mut6344, _mut6345, _mut6346, _mut6347))));
        return supD;
    }

    /**
     * Computes the <i>p-value</i>, or <i>observed significance level</i>, of a one-sample <a
     * href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov test</a>
     * evaluating the null hypothesis that {@code data} conforms to {@code distribution}.
     *
     * @param distribution reference distribution
     * @param data sample being being evaluated
     * @return the p-value associated with the null hypothesis that {@code data} is a sample from
     *         {@code distribution}
     * @throws InsufficientDataException if {@code data} does not have length at least 2
     * @throws NullArgumentException if {@code data} is null
     */
    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data) {
        return kolmogorovSmirnovTest(distribution, data, false);
    }

    /**
     * Performs a <a href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov
     * test</a> evaluating the null hypothesis that {@code data} conforms to {@code distribution}.
     *
     * @param distribution reference distribution
     * @param data sample being being evaluated
     * @param alpha significance level of the test
     * @return true iff the null hypothesis that {@code data} is a sample from {@code distribution}
     *         can be rejected with confidence 1 - {@code alpha}
     * @throws InsufficientDataException if {@code data} does not have length at least 2
     * @throws NullArgumentException if {@code data} is null
     */
    public boolean kolmogorovSmirnovTest(RealDistribution distribution, double[] data, double alpha) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391");
        if ((_mut6359 ? ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391", _mut6349, _mut6350, _mut6351, _mut6352, _mut6353)) && (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391", _mut6354, _mut6355, _mut6356, _mut6357, _mut6358))) : ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391", _mut6349, _mut6350, _mut6351, _mut6352, _mut6353)) || (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391", _mut6354, _mut6355, _mut6356, _mut6357, _mut6358))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(kolmogorovSmirnovTest(distribution, data), alpha, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.kolmogorovSmirnovTest_391", _mut6360, _mut6361, _mut6362, _mut6363, _mut6364);
    }

    /**
     * Estimates the <i>p-value</i> of a two-sample
     * <a href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test"> Kolmogorov-Smirnov test</a>
     * evaluating the null hypothesis that {@code x} and {@code y} are samples drawn from the same
     * probability distribution. This method estimates the p-value by repeatedly sampling sets of size
     * {@code x.length} and {@code y.length} from the empirical distribution of the combined sample.
     * When {@code strict} is true, this is equivalent to the algorithm implemented in the R function
     * {@code ks.boot}, described in <pre>
     * Jasjeet S. Sekhon. 2011. 'Multivariate and Propensity Score Matching
     * Software with Automated Balance Optimization: The Matching package for R.'
     * Journal of Statistical Software, 42(7): 1-52.
     * </pre>
     * @param x first sample
     * @param y second sample
     * @param iterations number of bootstrap resampling iterations
     * @param strict whether or not the null hypothesis is expressed as a strict inequality
     * @return estimated p-value
     */
    public double bootstrap(double[] x, double[] y, int iterations, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416");
        final int xLength = x.length;
        final int yLength = y.length;
        final double[] combined = new double[AOR_plus(xLength, yLength, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6365, _mut6366, _mut6367, _mut6368)];
        System.arraycopy(x, 0, combined, 0, xLength);
        System.arraycopy(y, 0, combined, xLength, yLength);
        final EnumeratedRealDistribution dist = new EnumeratedRealDistribution(rng, combined);
        final long d = integralKolmogorovSmirnovStatistic(x, y);
        int greaterCount = 0;
        int equalCount = 0;
        double[] curX;
        double[] curY;
        long curD;
        for (int i = 0; ROR_less(i, iterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6379, _mut6380, _mut6381, _mut6382, _mut6383); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416");
            curX = dist.sample(xLength);
            curY = dist.sample(yLength);
            curD = integralKolmogorovSmirnovStatistic(curX, curY);
            if (ROR_greater(curD, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6369, _mut6370, _mut6371, _mut6372, _mut6373)) {
                greaterCount++;
            } else if (ROR_equals(curD, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6374, _mut6375, _mut6376, _mut6377, _mut6378)) {
                equalCount++;
            }
        }
        return strict ? AOR_divide(greaterCount, (double) iterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6392, _mut6393, _mut6394, _mut6395) : AOR_divide((AOR_plus(greaterCount, equalCount, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6384, _mut6385, _mut6386, _mut6387)), (double) iterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.bootstrap_416", _mut6388, _mut6389, _mut6390, _mut6391);
    }

    /**
     * Computes {@code bootstrap(x, y, iterations, true)}.
     * This is equivalent to ks.boot(x,y, nboots=iterations) using the R Matching
     * package function. See #bootstrap(double[], double[], int, boolean).
     *
     * @param x first sample
     * @param y second sample
     * @param iterations number of bootstrap resampling iterations
     * @return estimated p-value
     */
    public double bootstrap(double[] x, double[] y, int iterations) {
        return bootstrap(x, y, iterations, true);
    }

    /**
     * Calculates \(P(D_n < d)\) using the method described in [1] with quick decisions for extreme
     * values given in [2] (see above). The result is not exact as with
     * {@link #cdfExact(double, int)} because calculations are based on
     * {@code double} rather than {@link org.apache.commons.math3.fraction.BigFraction}.
     *
     * @param d statistic
     * @param n sample size
     * @return \(P(D_n < d)\)
     * @throws MathArithmeticException if algorithm fails to convert {@code h} to a
     *         {@link org.apache.commons.math3.fraction.BigFraction} in expressing {@code d} as \((k
     *         - h) / m\) for integer {@code k, m} and \(0 \le h < 1\)
     */
    public double cdf(double d, int n) throws MathArithmeticException {
        return cdf(d, n, false);
    }

    /**
     * Calculates {@code P(D_n < d)}. The result is exact in the sense that BigFraction/BigReal is
     * used everywhere at the expense of very slow execution time. Almost never choose this in real
     * applications unless you are very sure; this is almost solely for verification purposes.
     * Normally, you would choose {@link #cdf(double, int)}. See the class
     * javadoc for definitions and algorithm description.
     *
     * @param d statistic
     * @param n sample size
     * @return \(P(D_n < d)\)
     * @throws MathArithmeticException if the algorithm fails to convert {@code h} to a
     *         {@link org.apache.commons.math3.fraction.BigFraction} in expressing {@code d} as \((k
     *         - h) / m\) for integer {@code k, m} and \(0 \le h < 1\)
     */
    public double cdfExact(double d, int n) throws MathArithmeticException {
        return cdf(d, n, true);
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] with quick decisions for extreme
     * values given in [2] (see above).
     *
     * @param d statistic
     * @param n sample size
     * @param exact whether the probability should be calculated exact using
     *        {@link org.apache.commons.math3.fraction.BigFraction} everywhere at the expense of
     *        very slow execution time, or if {@code double} should be used convenient places to
     *        gain speed. Almost never choose {@code true} in real applications unless you are very
     *        sure; {@code true} is almost solely for verification purposes.
     * @return \(P(D_n < d)\)
     * @throws MathArithmeticException if algorithm fails to convert {@code h} to a
     *         {@link org.apache.commons.math3.fraction.BigFraction} in expressing {@code d} as \((k
     *         - h) / m\) for integer {@code k, m} and \(0 \le h < 1\).
     */
    public double cdf(double d, int n, boolean exact) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510");
        final double ninv = AOR_divide(1, ((double) n), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6396, _mut6397, _mut6398, _mut6399);
        final double ninvhalf = AOR_multiply(0.5, ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6400, _mut6401, _mut6402, _mut6403);
        if (ROR_less_equals(d, ninvhalf, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6404, _mut6405, _mut6406, _mut6407, _mut6408)) {
            return 0;
        } else if ((_mut6419 ? (ROR_less(ninvhalf, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6409, _mut6410, _mut6411, _mut6412, _mut6413) || ROR_less_equals(d, ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6414, _mut6415, _mut6416, _mut6417, _mut6418)) : (ROR_less(ninvhalf, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6409, _mut6410, _mut6411, _mut6412, _mut6413) && ROR_less_equals(d, ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6414, _mut6415, _mut6416, _mut6417, _mut6418)))) {
            double res = 1;
            final double f = AOR_minus(AOR_multiply(2, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6452, _mut6453, _mut6454, _mut6455), ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6456, _mut6457, _mut6458, _mut6459);
            // n! f^n = n*f * (n-1)*f * ... * 1*x
            for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6464, _mut6465, _mut6466, _mut6467, _mut6468); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510");
                res *= AOR_multiply(i, f, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6460, _mut6461, _mut6462, _mut6463);
            }
            return res;
        } else if ((_mut6434 ? (ROR_less_equals(AOR_minus(1, ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6420, _mut6421, _mut6422, _mut6423), d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6424, _mut6425, _mut6426, _mut6427, _mut6428) || ROR_less(d, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6429, _mut6430, _mut6431, _mut6432, _mut6433)) : (ROR_less_equals(AOR_minus(1, ninv, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6420, _mut6421, _mut6422, _mut6423), d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6424, _mut6425, _mut6426, _mut6427, _mut6428) && ROR_less(d, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6429, _mut6430, _mut6431, _mut6432, _mut6433)))) {
            return AOR_minus(1, AOR_multiply(2, Math.pow(AOR_minus(1, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6440, _mut6441, _mut6442, _mut6443), n), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6444, _mut6445, _mut6446, _mut6447), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6448, _mut6449, _mut6450, _mut6451);
        } else if (ROR_less_equals(1, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6435, _mut6436, _mut6437, _mut6438, _mut6439)) {
            return 1;
        }
        if (exact) {
            return exactK(d, n);
        }
        if (ROR_less_equals(n, 140, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.cdf_510", _mut6469, _mut6470, _mut6471, _mut6472, _mut6473)) {
            return roundedK(d, n);
        }
        return pelzGood(d, n);
    }

    /**
     * Calculates the exact value of {@code P(D_n < d)} using the method described in [1] (reference
     * in class javadoc above) and {@link org.apache.commons.math3.fraction.BigFraction} (see
     * above).
     *
     * @param d statistic
     * @param n sample size
     * @return the two-sided probability of \(P(D_n < d)\)
     * @throws MathArithmeticException if algorithm fails to convert {@code h} to a
     *         {@link org.apache.commons.math3.fraction.BigFraction} in expressing {@code d} as \((k
     *         - h) / m\) for integer {@code k, m} and \(0 \le h < 1\).
     */
    private double exactK(double d, int n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552");
        final int k = (int) Math.ceil(AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552", _mut6474, _mut6475, _mut6476, _mut6477));
        final FieldMatrix<BigFraction> H = this.createExactH(d, n);
        final FieldMatrix<BigFraction> Hpower = H.power(n);
        BigFraction pFrac = Hpower.getEntry(AOR_minus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552", _mut6478, _mut6479, _mut6480, _mut6481), AOR_minus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552", _mut6482, _mut6483, _mut6484, _mut6485));
        for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552", _mut6486, _mut6487, _mut6488, _mut6489, _mut6490); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactK_552");
            pFrac = pFrac.multiply(i).divide(n);
        }
        /*
         * BigFraction.doubleValue converts numerator to double and the denominator to double and
         * divides afterwards. That gives NaN quite easy. This does not (scale is the number of
         * digits):
         */
        return pFrac.bigDecimalValue(20, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] and doubles (see above).
     *
     * @param d statistic
     * @param n sample size
     * @return \(P(D_n < d)\)
     */
    private double roundedK(double d, int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581");
        final int k = (int) Math.ceil(AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581", _mut6491, _mut6492, _mut6493, _mut6494));
        final RealMatrix H = this.createRoundedH(d, n);
        final RealMatrix Hpower = H.power(n);
        double pFrac = Hpower.getEntry(AOR_minus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581", _mut6495, _mut6496, _mut6497, _mut6498), AOR_minus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581", _mut6499, _mut6500, _mut6501, _mut6502));
        for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581", _mut6507, _mut6508, _mut6509, _mut6510, _mut6511); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581");
            pFrac *= AOR_divide((double) i, (double) n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.roundedK_581", _mut6503, _mut6504, _mut6505, _mut6506);
        }
        return pFrac;
    }

    /**
     * Computes the Pelz-Good approximation for \(P(D_n < d)\) as described in [2] in the class javadoc.
     *
     * @param d value of d-statistic (x in [2])
     * @param n sample size
     * @return \(P(D_n < d)\)
     * @since 3.4
     */
    public double pelzGood(double d, int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
        // Change the variable since approximation is for the distribution evaluated at d / sqrt(n)
        final double sqrtN = FastMath.sqrt(n);
        final double z = AOR_multiply(d, sqrtN, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6512, _mut6513, _mut6514, _mut6515);
        final double z2 = AOR_multiply(AOR_multiply(d, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6516, _mut6517, _mut6518, _mut6519), n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6520, _mut6521, _mut6522, _mut6523);
        final double z4 = AOR_multiply(z2, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6524, _mut6525, _mut6526, _mut6527);
        final double z6 = AOR_multiply(z4, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6528, _mut6529, _mut6530, _mut6531);
        final double z8 = AOR_multiply(z4, z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6532, _mut6533, _mut6534, _mut6535);
        // Eventual return value
        double ret = 0;
        // Compute K_0(z)
        double sum = 0;
        double increment = 0;
        double kTerm = 0;
        double z2Term = AOR_divide(MathUtils.PI_SQUARED, (AOR_multiply(8, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6536, _mut6537, _mut6538, _mut6539)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6540, _mut6541, _mut6542, _mut6543);
        int k = 1;
        for (; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6569, _mut6570, _mut6571, _mut6572, _mut6573); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm = AOR_minus(AOR_multiply(2, k, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6544, _mut6545, _mut6546, _mut6547), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6548, _mut6549, _mut6550, _mut6551);
            increment = FastMath.exp(AOR_multiply(AOR_multiply(-z2Term, kTerm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6552, _mut6553, _mut6554, _mut6555), kTerm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6556, _mut6557, _mut6558, _mut6559));
            sum += increment;
            if (ROR_less_equals(increment, AOR_multiply(PG_SUM_RELATIVE_ERROR, sum, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6560, _mut6561, _mut6562, _mut6563), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6564, _mut6565, _mut6566, _mut6567, _mut6568)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6574, _mut6575, _mut6576, _mut6577, _mut6578)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        ret = AOR_divide(AOR_multiply(sum, FastMath.sqrt(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6579, _mut6580, _mut6581, _mut6582)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6583, _mut6584, _mut6585, _mut6586), z, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6587, _mut6588, _mut6589, _mut6590);
        // twice the sum from k = 0 to inf (k = -1 is same as 0, -2 same as 1, ...)
        final double twoZ2 = AOR_multiply(2, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6591, _mut6592, _mut6593, _mut6594);
        sum = 0;
        kTerm = 0;
        double kTerm2 = 0;
        for (k = 0; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6632, _mut6633, _mut6634, _mut6635, _mut6636); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm = AOR_plus(k, 0.5, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6595, _mut6596, _mut6597, _mut6598);
            kTerm2 = AOR_multiply(kTerm, kTerm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6599, _mut6600, _mut6601, _mut6602);
            increment = AOR_multiply((AOR_minus(AOR_multiply(MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6603, _mut6604, _mut6605, _mut6606), z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6607, _mut6608, _mut6609, _mut6610)), FastMath.exp(AOR_divide(AOR_multiply(-MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6611, _mut6612, _mut6613, _mut6614), twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6615, _mut6616, _mut6617, _mut6618)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6619, _mut6620, _mut6621, _mut6622);
            sum += increment;
            if (ROR_less(FastMath.abs(increment), AOR_multiply(PG_SUM_RELATIVE_ERROR, FastMath.abs(sum), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6623, _mut6624, _mut6625, _mut6626), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6627, _mut6628, _mut6629, _mut6630, _mut6631)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6637, _mut6638, _mut6639, _mut6640, _mut6641)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        final double sqrtHalfPi = FastMath.sqrt(AOR_divide(FastMath.PI, 2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6642, _mut6643, _mut6644, _mut6645));
        // Instead of doubling sum, divide by 3 instead of 6
        ret += AOR_divide(AOR_multiply(sum, sqrtHalfPi, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6646, _mut6647, _mut6648, _mut6649), (AOR_multiply(AOR_multiply(3, z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6650, _mut6651, _mut6652, _mut6653), sqrtN, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6654, _mut6655, _mut6656, _mut6657)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6658, _mut6659, _mut6660, _mut6661);
        // Same drill as K_1, but with two doubly infinite sums, all k terms are even powers.
        final double z4Term = AOR_multiply(2, z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6662, _mut6663, _mut6664, _mut6665);
        final double z6Term = AOR_multiply(6, z6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6666, _mut6667, _mut6668, _mut6669);
        z2Term = AOR_multiply(5, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6670, _mut6671, _mut6672, _mut6673);
        final double pi4 = AOR_multiply(MathUtils.PI_SQUARED, MathUtils.PI_SQUARED, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6674, _mut6675, _mut6676, _mut6677);
        sum = 0;
        kTerm = 0;
        kTerm2 = 0;
        for (k = 0; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6747, _mut6748, _mut6749, _mut6750, _mut6751); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm = AOR_plus(k, 0.5, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6678, _mut6679, _mut6680, _mut6681);
            kTerm2 = AOR_multiply(kTerm, kTerm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6682, _mut6683, _mut6684, _mut6685);
            increment = AOR_multiply((AOR_plus(AOR_plus(AOR_plus(z6Term, z4Term, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6686, _mut6687, _mut6688, _mut6689), AOR_multiply(AOR_multiply(MathUtils.PI_SQUARED, (AOR_minus(z4Term, z2Term, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6690, _mut6691, _mut6692, _mut6693)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6694, _mut6695, _mut6696, _mut6697), kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6698, _mut6699, _mut6700, _mut6701), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6702, _mut6703, _mut6704, _mut6705), AOR_multiply(AOR_multiply(AOR_multiply(pi4, (AOR_minus(1, twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6706, _mut6707, _mut6708, _mut6709)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6710, _mut6711, _mut6712, _mut6713), kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6714, _mut6715, _mut6716, _mut6717), kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6718, _mut6719, _mut6720, _mut6721), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6722, _mut6723, _mut6724, _mut6725)), FastMath.exp(AOR_divide(AOR_multiply(-MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6726, _mut6727, _mut6728, _mut6729), twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6730, _mut6731, _mut6732, _mut6733)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6734, _mut6735, _mut6736, _mut6737);
            sum += increment;
            if (ROR_less(FastMath.abs(increment), AOR_multiply(PG_SUM_RELATIVE_ERROR, FastMath.abs(sum), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6738, _mut6739, _mut6740, _mut6741), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6742, _mut6743, _mut6744, _mut6745, _mut6746)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6752, _mut6753, _mut6754, _mut6755, _mut6756)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        double sum2 = 0;
        kTerm2 = 0;
        for (k = 1; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6786, _mut6787, _mut6788, _mut6789, _mut6790); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm2 = AOR_multiply(k, k, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6757, _mut6758, _mut6759, _mut6760);
            increment = AOR_multiply(AOR_multiply(MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6761, _mut6762, _mut6763, _mut6764), FastMath.exp(AOR_divide(AOR_multiply(-MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6765, _mut6766, _mut6767, _mut6768), twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6769, _mut6770, _mut6771, _mut6772)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6773, _mut6774, _mut6775, _mut6776);
            sum2 += increment;
            if (ROR_less(FastMath.abs(increment), AOR_multiply(PG_SUM_RELATIVE_ERROR, FastMath.abs(sum2), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6777, _mut6778, _mut6779, _mut6780), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6781, _mut6782, _mut6783, _mut6784, _mut6785)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6791, _mut6792, _mut6793, _mut6794, _mut6795)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        // Again, adjust coefficients instead of doubling sum, sum2
        ret += AOR_multiply((AOR_divide(sqrtHalfPi, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6796, _mut6797, _mut6798, _mut6799)), (AOR_minus(AOR_divide(sum, (AOR_multiply(AOR_multiply(AOR_multiply(AOR_multiply(36, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6800, _mut6801, _mut6802, _mut6803), z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6804, _mut6805, _mut6806, _mut6807), z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6808, _mut6809, _mut6810, _mut6811), z, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6812, _mut6813, _mut6814, _mut6815)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6816, _mut6817, _mut6818, _mut6819), AOR_divide(sum2, (AOR_multiply(AOR_multiply(18, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6820, _mut6821, _mut6822, _mut6823), z, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6824, _mut6825, _mut6826, _mut6827)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6828, _mut6829, _mut6830, _mut6831), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6832, _mut6833, _mut6834, _mut6835)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6836, _mut6837, _mut6838, _mut6839);
        // Multiply coefficient denominators by 2, so omit doubling sums.
        final double pi6 = AOR_multiply(pi4, MathUtils.PI_SQUARED, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6840, _mut6841, _mut6842, _mut6843);
        sum = 0;
        double kTerm4 = 0;
        double kTerm6 = 0;
        for (k = 0; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6961, _mut6962, _mut6963, _mut6964, _mut6965); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm = AOR_plus(k, 0.5, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6844, _mut6845, _mut6846, _mut6847);
            kTerm2 = AOR_multiply(kTerm, kTerm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6848, _mut6849, _mut6850, _mut6851);
            kTerm4 = AOR_multiply(kTerm2, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6852, _mut6853, _mut6854, _mut6855);
            kTerm6 = AOR_multiply(kTerm4, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6856, _mut6857, _mut6858, _mut6859);
            increment = AOR_multiply((AOR_minus(AOR_minus(AOR_plus(AOR_plus(AOR_multiply(AOR_multiply(pi6, kTerm6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6860, _mut6861, _mut6862, _mut6863), (AOR_minus(5, AOR_multiply(30, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6864, _mut6865, _mut6866, _mut6867), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6868, _mut6869, _mut6870, _mut6871)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6872, _mut6873, _mut6874, _mut6875), AOR_multiply(AOR_multiply(pi4, kTerm4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6876, _mut6877, _mut6878, _mut6879), (AOR_plus(AOR_multiply(-60, z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6880, _mut6881, _mut6882, _mut6883), AOR_multiply(212, z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6884, _mut6885, _mut6886, _mut6887), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6888, _mut6889, _mut6890, _mut6891)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6892, _mut6893, _mut6894, _mut6895), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6896, _mut6897, _mut6898, _mut6899), AOR_multiply(AOR_multiply(MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6900, _mut6901, _mut6902, _mut6903), (AOR_minus(AOR_multiply(135, z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6904, _mut6905, _mut6906, _mut6907), AOR_multiply(96, z6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6908, _mut6909, _mut6910, _mut6911), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6912, _mut6913, _mut6914, _mut6915)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6916, _mut6917, _mut6918, _mut6919), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6920, _mut6921, _mut6922, _mut6923), AOR_multiply(30, z6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6924, _mut6925, _mut6926, _mut6927), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6928, _mut6929, _mut6930, _mut6931), AOR_multiply(90, z8, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6932, _mut6933, _mut6934, _mut6935), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6936, _mut6937, _mut6938, _mut6939)), FastMath.exp(AOR_divide(AOR_multiply(-MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6940, _mut6941, _mut6942, _mut6943), twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6944, _mut6945, _mut6946, _mut6947)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6948, _mut6949, _mut6950, _mut6951);
            sum += increment;
            if (ROR_less(FastMath.abs(increment), AOR_multiply(PG_SUM_RELATIVE_ERROR, FastMath.abs(sum), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6952, _mut6953, _mut6954, _mut6955), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6956, _mut6957, _mut6958, _mut6959, _mut6960)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6966, _mut6967, _mut6968, _mut6969, _mut6970)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        sum2 = 0;
        for (k = 1; ROR_less(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7020, _mut7021, _mut7022, _mut7023, _mut7024); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603");
            kTerm2 = AOR_multiply(k, k, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6971, _mut6972, _mut6973, _mut6974);
            kTerm4 = AOR_multiply(kTerm2, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6975, _mut6976, _mut6977, _mut6978);
            increment = AOR_multiply((AOR_plus(AOR_multiply(-pi4, kTerm4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6979, _mut6980, _mut6981, _mut6982), AOR_multiply(AOR_multiply(AOR_multiply(3, MathUtils.PI_SQUARED, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6983, _mut6984, _mut6985, _mut6986), kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6987, _mut6988, _mut6989, _mut6990), z2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6991, _mut6992, _mut6993, _mut6994), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6995, _mut6996, _mut6997, _mut6998)), FastMath.exp(AOR_divide(AOR_multiply(-MathUtils.PI_SQUARED, kTerm2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut6999, _mut7000, _mut7001, _mut7002), twoZ2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7003, _mut7004, _mut7005, _mut7006)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7007, _mut7008, _mut7009, _mut7010);
            sum2 += increment;
            if (ROR_less(FastMath.abs(increment), AOR_multiply(PG_SUM_RELATIVE_ERROR, FastMath.abs(sum2), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7011, _mut7012, _mut7013, _mut7014), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7015, _mut7016, _mut7017, _mut7018, _mut7019)) {
                break;
            }
        }
        if (ROR_equals(k, MAXIMUM_PARTIAL_SUM_COUNT, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7025, _mut7026, _mut7027, _mut7028, _mut7029)) {
            throw new TooManyIterationsException(MAXIMUM_PARTIAL_SUM_COUNT);
        }
        return AOR_plus(ret, AOR_multiply((AOR_divide(sqrtHalfPi, (AOR_multiply(sqrtN, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7030, _mut7031, _mut7032, _mut7033)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7034, _mut7035, _mut7036, _mut7037)), (AOR_plus(AOR_divide(sum, (AOR_multiply(AOR_multiply(3240, z6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7038, _mut7039, _mut7040, _mut7041), z4, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7042, _mut7043, _mut7044, _mut7045)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7046, _mut7047, _mut7048, _mut7049), AOR_divide(+sum2, (AOR_multiply(108, z6, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7050, _mut7051, _mut7052, _mut7053)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7054, _mut7055, _mut7056, _mut7057), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7058, _mut7059, _mut7060, _mut7061)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7062, _mut7063, _mut7064, _mut7065), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.pelzGood_603", _mut7066, _mut7067, _mut7068, _mut7069);
    }

    /**
     *  Creates {@code H} of size {@code m x m} as described in [1] (see above).
     *
     *  @param d statistic
     *  @param n sample size
     *  @return H matrix
     *  @throws NumberIsTooLargeException if fractional part is greater than 1
     *  @throws FractionConversionException if algorithm fails to convert {@code h} to a
     *          {@link org.apache.commons.math3.fraction.BigFraction} in expressing {@code d} as \((k
     *          - h) / m\) for integer {@code k, m} and \(0 <= h < 1\).
     */
    private FieldMatrix<BigFraction> createExactH(double d, int n) throws NumberIsTooLargeException, FractionConversionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
        final int k = (int) Math.ceil(AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7070, _mut7071, _mut7072, _mut7073));
        final int m = AOR_minus(AOR_multiply(2, k, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7074, _mut7075, _mut7076, _mut7077), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7078, _mut7079, _mut7080, _mut7081);
        final double hDouble = AOR_minus(k, AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7082, _mut7083, _mut7084, _mut7085), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7086, _mut7087, _mut7088, _mut7089);
        if (ROR_greater_equals(hDouble, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7090, _mut7091, _mut7092, _mut7093, _mut7094)) {
            throw new NumberIsTooLargeException(hDouble, 1.0, false);
        }
        BigFraction h = null;
        try {
            h = new BigFraction(hDouble, 1.0e-20, 10000);
        } catch (final FractionConversionException e1) {
            try {
                h = new BigFraction(hDouble, 1.0e-10, 10000);
            } catch (final FractionConversionException e2) {
                h = new BigFraction(hDouble, 1.0e-5, 10000);
            }
        }
        final BigFraction[][] Hdata = new BigFraction[m][m];
        /*
         * Start by filling everything with either 0 or 1.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7113, _mut7114, _mut7115, _mut7116, _mut7117); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7108, _mut7109, _mut7110, _mut7111, _mut7112); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
                if (ROR_less(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7095, _mut7096, _mut7097, _mut7098), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7099, _mut7100, _mut7101, _mut7102), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7103, _mut7104, _mut7105, _mut7106, _mut7107)) {
                    Hdata[i][j] = BigFraction.ZERO;
                } else {
                    Hdata[i][j] = BigFraction.ONE;
                }
            }
        }
        /*
         * Setting up power-array to avoid calculating the same value twice: hPowers[0] = h^1 ...
         * hPowers[m-1] = h^m
         */
        final BigFraction[] hPowers = new BigFraction[m];
        hPowers[0] = h;
        for (int i = 1; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7122, _mut7123, _mut7124, _mut7125, _mut7126); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
            hPowers[i] = h.multiply(hPowers[AOR_minus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7118, _mut7119, _mut7120, _mut7121)]);
        }
        /*
         * First column and last row has special values (each other reversed).
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7143, _mut7144, _mut7145, _mut7146, _mut7147); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
            Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7127, _mut7128, _mut7129, _mut7130)][i] = Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7139, _mut7140, _mut7141, _mut7142)][i].subtract(hPowers[AOR_minus(AOR_minus(m, i, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7131, _mut7132, _mut7133, _mut7134), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7135, _mut7136, _mut7137, _mut7138)]);
        }
        /*
         * [1] states: "For 1/2 < h < 1 the bottom left element of the matrix should be (1 - 2*h^m +
         * (2h - 1)^m )/m!" Since 0 <= h < 1, then if h > 1/2 is sufficient to check:
         */
        if (ROR_equals(h.compareTo(BigFraction.ONE_HALF), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7148, _mut7149, _mut7150, _mut7151, _mut7152)) {
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7153, _mut7154, _mut7155, _mut7156)][0] = Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7157, _mut7158, _mut7159, _mut7160)][0].add(h.multiply(2).subtract(1).pow(m));
        }
        /*
         * Aside from the first column and last row, the (i, j)-th element is 1/(i - j + 1)! if i -
         * j + 1 >= 0, else 0. 1's and 0's are already put, so only division with (i - j + 1)! is
         * needed in the elements that have 1's. There is no need to calculate (i - j + 1)! and then
         * divide - small steps avoid overflows. Note that i - j + 1 > 0 <=> i + 1 > j instead of
         * j'ing all the way to m. Also note that it is started at g = 2 because dividing by 1 isn't
         * really necessary.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7196, _mut7197, _mut7198, _mut7199, _mut7200); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
            for (int j = 0; ROR_less(j, AOR_plus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7187, _mut7188, _mut7189, _mut7190), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7191, _mut7192, _mut7193, _mut7194, _mut7195); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
                if (ROR_greater(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7161, _mut7162, _mut7163, _mut7164), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7165, _mut7166, _mut7167, _mut7168), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7169, _mut7170, _mut7171, _mut7172, _mut7173)) {
                    for (int g = 2; ROR_less_equals(g, AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7174, _mut7175, _mut7176, _mut7177), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7178, _mut7179, _mut7180, _mut7181), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747", _mut7182, _mut7183, _mut7184, _mut7185, _mut7186); ++g) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH_747");
                        Hdata[i][j] = Hdata[i][j].divide(g);
                    }
                }
            }
        }
        return new Array2DRowFieldMatrix<BigFraction>(BigFractionField.getInstance(), Hdata);
    }

    /**
     *  Creates {@code H} of size {@code m x m} as described in [1] (see above)
     *  using double-precision.
     *
     *  @param d statistic
     *  @param n sample size
     *  @return H matrix
     *  @throws NumberIsTooLargeException if fractional part is greater than 1
     */
    private RealMatrix createRoundedH(double d, int n) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
        final int k = (int) Math.ceil(AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7201, _mut7202, _mut7203, _mut7204));
        final int m = AOR_minus(AOR_multiply(2, k, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7205, _mut7206, _mut7207, _mut7208), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7209, _mut7210, _mut7211, _mut7212);
        final double h = AOR_minus(k, AOR_multiply(n, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7213, _mut7214, _mut7215, _mut7216), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7217, _mut7218, _mut7219, _mut7220);
        if (ROR_greater_equals(h, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7221, _mut7222, _mut7223, _mut7224, _mut7225)) {
            throw new NumberIsTooLargeException(h, 1.0, false);
        }
        final double[][] Hdata = new double[m][m];
        /*
         * Start by filling everything with either 0 or 1.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7244, _mut7245, _mut7246, _mut7247, _mut7248); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7239, _mut7240, _mut7241, _mut7242, _mut7243); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
                if (ROR_less(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7226, _mut7227, _mut7228, _mut7229), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7230, _mut7231, _mut7232, _mut7233), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7234, _mut7235, _mut7236, _mut7237, _mut7238)) {
                    Hdata[i][j] = 0;
                } else {
                    Hdata[i][j] = 1;
                }
            }
        }
        /*
         * Setting up power-array to avoid calculating the same value twice: hPowers[0] = h^1 ...
         * hPowers[m-1] = h^m
         */
        final double[] hPowers = new double[m];
        hPowers[0] = h;
        for (int i = 1; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7257, _mut7258, _mut7259, _mut7260, _mut7261); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
            hPowers[i] = AOR_multiply(h, hPowers[AOR_minus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7249, _mut7250, _mut7251, _mut7252)], "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7253, _mut7254, _mut7255, _mut7256);
        }
        /*
         * First column and last row has special values (each other reversed).
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7278, _mut7279, _mut7280, _mut7281, _mut7282); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
            Hdata[i][0] = AOR_minus(Hdata[i][0], hPowers[i], "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7262, _mut7263, _mut7264, _mut7265);
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7266, _mut7267, _mut7268, _mut7269)][i] -= hPowers[AOR_minus(AOR_minus(m, i, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7270, _mut7271, _mut7272, _mut7273), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7274, _mut7275, _mut7276, _mut7277)];
        }
        /*
         * [1] states: "For 1/2 < h < 1 the bottom left element of the matrix should be (1 - 2*h^m +
         * (2h - 1)^m )/m!" Since 0 <= h < 1, then if h > 1/2 is sufficient to check:
         */
        if (ROR_greater(Double.compare(h, 0.5), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7283, _mut7284, _mut7285, _mut7286, _mut7287)) {
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7288, _mut7289, _mut7290, _mut7291)][0] += FastMath.pow(AOR_minus(AOR_multiply(2, h, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7292, _mut7293, _mut7294, _mut7295), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7296, _mut7297, _mut7298, _mut7299), m);
        }
        /*
         * Aside from the first column and last row, the (i, j)-th element is 1/(i - j + 1)! if i -
         * j + 1 >= 0, else 0. 1's and 0's are already put, so only division with (i - j + 1)! is
         * needed in the elements that have 1's. There is no need to calculate (i - j + 1)! and then
         * divide - small steps avoid overflows. Note that i - j + 1 > 0 <=> i + 1 > j instead of
         * j'ing all the way to m. Also note that it is started at g = 2 because dividing by 1 isn't
         * really necessary.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7335, _mut7336, _mut7337, _mut7338, _mut7339); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
            for (int j = 0; ROR_less(j, AOR_plus(i, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7326, _mut7327, _mut7328, _mut7329), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7330, _mut7331, _mut7332, _mut7333, _mut7334); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
                if (ROR_greater(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7300, _mut7301, _mut7302, _mut7303), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7304, _mut7305, _mut7306, _mut7307), 0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7308, _mut7309, _mut7310, _mut7311, _mut7312)) {
                    for (int g = 2; ROR_less_equals(g, AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7313, _mut7314, _mut7315, _mut7316), 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7317, _mut7318, _mut7319, _mut7320), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836", _mut7321, _mut7322, _mut7323, _mut7324, _mut7325); ++g) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createRoundedH_836");
                        Hdata[i][j] /= g;
                    }
                }
            }
        }
        return MatrixUtils.createRealMatrix(Hdata);
    }

    /**
     * Verifies that {@code array} has length at least 2.
     *
     * @param array array to test
     * @throws NullArgumentException if array is null
     * @throws InsufficientDataException if array is too short
     */
    private void checkArray(double[] array) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.checkArray_913");
        if (array == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED);
        }
        if (ROR_less(array.length, 2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.checkArray_913", _mut7340, _mut7341, _mut7342, _mut7343, _mut7344)) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, array.length, 2);
        }
    }

    /**
     * Computes \( 1 + 2 \sum_{i=1}^\infty (-1)^i e^{-2 i^2 t^2} \) stopping when successive partial
     * sums are within {@code tolerance} of one another, or when {@code maxIterations} partial sums
     * have been computed. If the sum does not converge before {@code maxIterations} iterations a
     * {@link TooManyIterationsException} is thrown.
     *
     * @param t argument
     * @param tolerance Cauchy criterion for partial sums
     * @param maxIterations maximum number of partial sums to compute
     * @return Kolmogorov sum evaluated at t
     * @throws TooManyIterationsException if the series does not converge
     */
    public double ksSum(double t, double tolerance, int maxIterations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935");
        if (ROR_equals(t, 0.0, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7345, _mut7346, _mut7347, _mut7348, _mut7349)) {
            return 0.0;
        }
        final double x = AOR_multiply(AOR_multiply(-2, t, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7350, _mut7351, _mut7352, _mut7353), t, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7354, _mut7355, _mut7356, _mut7357);
        int sign = -1;
        long i = 1;
        double partialSum = 0.5d;
        double delta = 1;
        while ((_mut7380 ? (ROR_greater(delta, tolerance, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7370, _mut7371, _mut7372, _mut7373, _mut7374) || ROR_less(i, maxIterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7375, _mut7376, _mut7377, _mut7378, _mut7379)) : (ROR_greater(delta, tolerance, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7370, _mut7371, _mut7372, _mut7373, _mut7374) && ROR_less(i, maxIterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7375, _mut7376, _mut7377, _mut7378, _mut7379)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935");
            delta = FastMath.exp(AOR_multiply(AOR_multiply(x, i, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7358, _mut7359, _mut7360, _mut7361), i, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7362, _mut7363, _mut7364, _mut7365));
            partialSum += AOR_multiply(sign, delta, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7366, _mut7367, _mut7368, _mut7369);
            sign *= -1;
            i++;
        }
        if (ROR_equals(i, maxIterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7381, _mut7382, _mut7383, _mut7384, _mut7385)) {
            throw new TooManyIterationsException(maxIterations);
        }
        return AOR_multiply(partialSum, 2, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.ksSum_935", _mut7386, _mut7387, _mut7388, _mut7389);
    }

    /**
     * Given a d-statistic in the range [0, 1] and the two sample sizes n and m,
     * an integral d-statistic in the range [0, n*m] is calculated, that can be used for
     * comparison with other integral d-statistics. Depending whether {@code strict} is
     * {@code true} or not, the returned value divided by (n*m) is greater than
     * (resp greater than or equal to) the given d value (allowing some tolerance).
     *
     * @param d a d-statistic in the range [0, 1]
     * @param n first sample size
     * @param m second sample size
     * @param strict whether the returned value divided by (n*m) is allowed to be equal to d
     * @return the integral d-statistic in the range [0, n*m]
     */
    private static long calculateIntegralD(double d, int n, int m, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973");
        // d-values within tol of one another are considered equal
        final double tol = 1e-12;
        long nm = AOR_multiply(n, (long) m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7390, _mut7391, _mut7392, _mut7393);
        long upperBound = (long) FastMath.ceil(AOR_multiply((AOR_minus(d, tol, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7394, _mut7395, _mut7396, _mut7397)), nm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7398, _mut7399, _mut7400, _mut7401));
        long lowerBound = (long) FastMath.floor(AOR_multiply((AOR_plus(d, tol, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7402, _mut7403, _mut7404, _mut7405)), nm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7406, _mut7407, _mut7408, _mut7409));
        if ((_mut7415 ? (strict || ROR_equals(lowerBound, upperBound, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7410, _mut7411, _mut7412, _mut7413, _mut7414)) : (strict && ROR_equals(lowerBound, upperBound, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7410, _mut7411, _mut7412, _mut7413, _mut7414)))) {
            return AOR_plus(upperBound, 1l, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.calculateIntegralD_973", _mut7416, _mut7417, _mut7418, _mut7419);
        } else {
            return upperBound;
        }
    }

    /**
     * Computes \(P(D_{n,m} > d)\) if {@code strict} is {@code true}; otherwise \(P(D_{n,m} \ge
     * d)\), where \(D_{n,m}\) is the 2-sample Kolmogorov-Smirnov statistic. See
     * {@link #kolmogorovSmirnovStatistic(double[], double[])} for the definition of \(D_{n,m}\).
     * <p>
     * The returned probability is exact, implemented by unwinding the recursive function
     * definitions presented in [4] (class javadoc).
     * </p>
     *
     * @param d D-statistic value
     * @param n first sample size
     * @param m second sample size
     * @param strict whether or not the probability to compute is expressed as a strict inequality
     * @return probability that a randomly selected m-n partition of m + n generates \(D_{n,m}\)
     *         greater than (resp. greater than or equal to) {@code d}
     */
    public double exactP(double d, int n, int m, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactP_1002");
        return AOR_minus(1, AOR_divide(n(m, n, m, n, calculateIntegralD(d, m, n, strict), strict), CombinatoricsUtils.binomialCoefficientDouble(AOR_plus(n, m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactP_1002", _mut7420, _mut7421, _mut7422, _mut7423), m), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactP_1002", _mut7424, _mut7425, _mut7426, _mut7427), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.exactP_1002", _mut7428, _mut7429, _mut7430, _mut7431);
    }

    /**
     * Uses the Kolmogorov-Smirnov distribution to approximate \(P(D_{n,m} > d)\) where \(D_{n,m}\)
     * is the 2-sample Kolmogorov-Smirnov statistic. See
     * {@link #kolmogorovSmirnovStatistic(double[], double[])} for the definition of \(D_{n,m}\).
     * <p>
     * Specifically, what is returned is \(1 - k(d \sqrt{mn / (m + n)})\) where \(k(t) = 1 + 2
     * \sum_{i=1}^\infty (-1)^i e^{-2 i^2 t^2}\). See {@link #ksSum(double, double, int)} for
     * details on how convergence of the sum is determined. This implementation passes {@code ksSum}
     * {@value #KS_SUM_CAUCHY_CRITERION} as {@code tolerance} and
     * {@value #MAXIMUM_PARTIAL_SUM_COUNT} as {@code maxIterations}.
     * </p>
     *
     * @param d D-statistic value
     * @param n first sample size
     * @param m second sample size
     * @return approximate probability that a randomly selected m-n partition of m + n generates
     *         \(D_{n,m}\) greater than {@code d}
     */
    public double approximateP(double d, int n, int m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025");
        final double dm = m;
        final double dn = n;
        return AOR_minus(1, ksSum(AOR_multiply(d, FastMath.sqrt(AOR_divide((AOR_multiply(dm, dn, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025", _mut7432, _mut7433, _mut7434, _mut7435)), (AOR_plus(dm, dn, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025", _mut7436, _mut7437, _mut7438, _mut7439)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025", _mut7440, _mut7441, _mut7442, _mut7443)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025", _mut7444, _mut7445, _mut7446, _mut7447), KS_SUM_CAUCHY_CRITERION, MAXIMUM_PARTIAL_SUM_COUNT), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.approximateP_1025", _mut7448, _mut7449, _mut7450, _mut7451);
    }

    /**
     * Fills a boolean array randomly with a fixed number of {@code true} values.
     * The method uses a simplified version of the Fisher-Yates shuffle algorithm.
     * By processing first the {@code true} values followed by the remaining {@code false} values
     * less random numbers need to be generated. The method is optimized for the case
     * that the number of {@code true} values is larger than or equal to the number of
     * {@code false} values.
     *
     * @param b boolean array
     * @param numberOfTrueValues number of {@code true} values the boolean array should finally have
     * @param rng random data generator
     */
    static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(final boolean[] b, final int numberOfTrueValues, final RandomGenerator rng) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fillBooleanArrayRandomlyWithFixedNumberTrueValues_1044");
        Arrays.fill(b, true);
        for (int k = numberOfTrueValues; ROR_less(k, b.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fillBooleanArrayRandomlyWithFixedNumberTrueValues_1044", _mut7456, _mut7457, _mut7458, _mut7459, _mut7460); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fillBooleanArrayRandomlyWithFixedNumberTrueValues_1044");
            final int r = rng.nextInt(AOR_plus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fillBooleanArrayRandomlyWithFixedNumberTrueValues_1044", _mut7452, _mut7453, _mut7454, _mut7455));
            b[(b[r]) ? r : k] = false;
        }
    }

    /**
     * Uses Monte Carlo simulation to approximate \(P(D_{n,m} > d)\) where \(D_{n,m}\) is the
     * 2-sample Kolmogorov-Smirnov statistic. See
     * {@link #kolmogorovSmirnovStatistic(double[], double[])} for the definition of \(D_{n,m}\).
     * <p>
     * The simulation generates {@code iterations} random partitions of {@code m + n} into an
     * {@code n} set and an {@code m} set, computing \(D_{n,m}\) for each partition and returning
     * the proportion of values that are greater than {@code d}, or greater than or equal to
     * {@code d} if {@code strict} is {@code false}.
     * </p>
     *
     * @param d D-statistic value
     * @param n first sample size
     * @param m second sample size
     * @param iterations number of random partitions to generate
     * @param strict whether or not the probability to compute is expressed as a strict inequality
     * @return proportion of randomly generated m-n partitions of m + n that result in \(D_{n,m}\)
     *         greater than (resp. greater than or equal to) {@code d}
     */
    public double monteCarloP(final double d, final int n, final int m, final boolean strict, final int iterations) {
        return integralMonteCarloP(calculateIntegralD(d, n, m, strict), n, m, iterations);
    }

    /**
     * Uses Monte Carlo simulation to approximate \(P(D_{n,m} >= d/(n*m))\) where \(D_{n,m}\) is the
     * 2-sample Kolmogorov-Smirnov statistic.
     * <p>
     * Here d is the D-statistic represented as long value.
     * The real D-statistic is obtained by dividing d by n*m.
     * See also {@link #monteCarloP(double, int, int, boolean, int)}.
     *
     * @param d integral D-statistic
     * @param n first sample size
     * @param m second sample size
     * @param iterations number of random partitions to generate
     * @return proportion of randomly generated m-n partitions of m + n that result in \(D_{n,m}\)
     *         greater than or equal to {@code d/(n*m))}
     */
    private double integralMonteCarloP(final long d, final int n, final int m, final int iterations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091");
        // ensure that nn is always the max of (n, m) to require fewer random numbers
        final int nn = FastMath.max(n, m);
        final int mm = FastMath.min(n, m);
        final int sum = AOR_plus(nn, mm, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7461, _mut7462, _mut7463, _mut7464);
        int tail = 0;
        final boolean[] b = new boolean[sum];
        for (int i = 0; ROR_less(i, iterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7480, _mut7481, _mut7482, _mut7483, _mut7484); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091");
            fillBooleanArrayRandomlyWithFixedNumberTrueValues(b, nn, rng);
            long curD = 0l;
            for (int j = 0; ROR_less(j, b.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7475, _mut7476, _mut7477, _mut7478, _mut7479); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091");
                if (b[j]) {
                    curD += mm;
                    if (ROR_greater_equals(curD, d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7470, _mut7471, _mut7472, _mut7473, _mut7474)) {
                        tail++;
                        break;
                    }
                } else {
                    curD -= nn;
                    if (ROR_less_equals(curD, -d, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7465, _mut7466, _mut7467, _mut7468, _mut7469)) {
                        tail++;
                        break;
                    }
                }
            }
        }
        return AOR_divide((double) tail, iterations, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.integralMonteCarloP_1091", _mut7485, _mut7486, _mut7487, _mut7488);
    }

    /**
     * If there are no ties in the combined dataset formed from x and y, this
     * method is a no-op.  If there are ties, a uniform random deviate in
     * (-minDelta / 2, minDelta / 2) - {0} is added to each value in x and y, where
     * minDelta is the minimum difference between unequal values in the combined
     * sample.  A fixed seed is used to generate the jitter, so repeated activations
     * with the same input arrays result in the same values.
     *
     * NOTE: if there are ties in the data, this method overwrites the data in
     * x and y with the jittered values.
     *
     * @param x first sample
     * @param y second sample
     */
    private static void fixTies(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136");
        final double[] values = MathArrays.unique(MathArrays.concatenate(x, y));
        if (ROR_equals(values.length, AOR_plus(x.length, y.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7489, _mut7490, _mut7491, _mut7492), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7493, _mut7494, _mut7495, _mut7496, _mut7497)) {
            // There are no ties
            return;
        }
        // Find the smallest difference between values, or 1 if all values are the same
        double minDelta = 1;
        double prev = values[0];
        double delta = 1;
        for (int i = 1; ROR_less(i, values.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7507, _mut7508, _mut7509, _mut7510, _mut7511); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136");
            delta = AOR_minus(prev, values[i], "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7498, _mut7499, _mut7500, _mut7501);
            if (ROR_less(delta, minDelta, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7502, _mut7503, _mut7504, _mut7505, _mut7506)) {
                minDelta = delta;
            }
            prev = values[i];
        }
        minDelta /= 2;
        // low-initialization-overhead generator
        final RealDistribution dist = new UniformRealDistribution(new JDKRandomGenerator(100), -minDelta, minDelta);
        // until all ties are gone.  Bound the loop and throw MIE if bound is exceeded.
        int ct = 0;
        boolean ties = true;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136");
            jitter(x, dist);
            jitter(y, dist);
            ties = hasTies(x, y);
            ct++;
        } while ((_mut7517 ? (ties || ROR_less(ct, 1000, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7512, _mut7513, _mut7514, _mut7515, _mut7516)) : (ties && ROR_less(ct, 1000, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.fixTies_1136", _mut7512, _mut7513, _mut7514, _mut7515, _mut7516))));
        if (ties) {
            // Should never happen
            throw new MathInternalError();
        }
    }

    /**
     * Returns true iff there are ties in the combined sample
     * formed from x and y.
     *
     * @param x first sample
     * @param y second sample
     * @return true if x and y together contain ties
     */
    private static boolean hasTies(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.hasTies_1183");
        final HashSet<Double> values = new HashSet<Double>();
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.hasTies_1183", _mut7518, _mut7519, _mut7520, _mut7521, _mut7522); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.hasTies_1183");
            if (!values.add(x[i])) {
                return true;
            }
        }
        for (int i = 0; ROR_less(i, y.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.hasTies_1183", _mut7523, _mut7524, _mut7525, _mut7526, _mut7527); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.hasTies_1183");
            if (!values.add(y[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds random jitter to {@code data} using deviates sampled from {@code dist}.
     * <p>
     * Note that jitter is applied in-place - i.e., the array
     * values are overwritten with the result of applying jitter.</p>
     *
     * @param data input/output data array - entries overwritten by the method
     * @param dist probability distribution to sample for jitter values
     * @throws NullPointerException if either of the parameters is null
     */
    private static void jitter(double[] data, RealDistribution dist) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.jitter_1208");
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.jitter_1208", _mut7528, _mut7529, _mut7530, _mut7531, _mut7532); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.jitter_1208");
            data[i] += dist.sample();
        }
    }

    /**
     * The function C(i, j) defined in [4] (class javadoc), formula (5.5).
     * defined to return 1 if |i/n - j/m| <= c; 0 otherwise. Here c is scaled up
     * and recoded as a long to avoid rounding errors in comparison tests, so what
     * is actually tested is |im - jn| <= cmn.
     *
     * @param i first path parameter
     * @param j second path paramter
     * @param m first sample size
     * @param n second sample size
     * @param cmn integral D-statistic (see {@link #calculateIntegralD(double, int, int, boolean)})
     * @param strict whether or not the null hypothesis uses strict inequality
     * @return C(i,j) for given m, n, c
     */
    private static int c(int i, int j, int m, int n, long cmn, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228");
        if (strict) {
            return ROR_less_equals(FastMath.abs(AOR_minus(AOR_multiply(i, (long) n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7533, _mut7534, _mut7535, _mut7536), AOR_multiply(j, (long) m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7537, _mut7538, _mut7539, _mut7540), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7541, _mut7542, _mut7543, _mut7544)), cmn, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7545, _mut7546, _mut7547, _mut7548, _mut7549) ? 1 : 0;
        }
        return ROR_less(FastMath.abs(AOR_minus(AOR_multiply(i, (long) n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7550, _mut7551, _mut7552, _mut7553), AOR_multiply(j, (long) m, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7554, _mut7555, _mut7556, _mut7557), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7558, _mut7559, _mut7560, _mut7561)), cmn, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.c_1228", _mut7562, _mut7563, _mut7564, _mut7565, _mut7566) ? 1 : 0;
    }

    /**
     * The function N(i, j) defined in [4] (class javadoc).
     * Returns the number of paths over the lattice {(i,j) : 0 <= i <= n, 0 <= j <= m}
     * from (0,0) to (i,j) satisfying C(h,k, m, n, c) = 1 for each (h,k) on the path.
     * The return value is integral, but subject to overflow, so it is maintained and
     * returned as a double.
     *
     * @param i first path parameter
     * @param j second path parameter
     * @param m first sample size
     * @param n second sample size
     * @param cnm integral D-statistic (see {@link #calculateIntegralD(double, int, int, boolean)})
     * @param strict whether or not the null hypothesis uses strict inequality
     * @return number or paths to (i, j) from (0,0) representing D-values as large as c for given m, n
     */
    private static double n(int i, int j, int m, int n, long cnm, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250");
        /*
         * Unwind the recursive definition given in [4].
         * Compute n(1,1), n(1,2)...n(2,1), n(2,2)... up to n(i,j), one row at a time.
         * When n(i,*) are being computed, lag[] holds the values of n(i - 1, *).
         */
        final double[] lag = new double[n];
        double last = 0;
        for (int k = 0; ROR_less(k, n, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7571, _mut7572, _mut7573, _mut7574, _mut7575); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250");
            lag[k] = c(0, AOR_plus(k, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7567, _mut7568, _mut7569, _mut7570), m, n, cnm, strict);
        }
        for (int k = 1; ROR_less_equals(k, i, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7601, _mut7602, _mut7603, _mut7604, _mut7605); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250");
            last = c(k, 0, m, n, cnm, strict);
            for (int l = 1; ROR_less_equals(l, j, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7596, _mut7597, _mut7598, _mut7599, _mut7600); l++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250");
                lag[AOR_minus(l, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7576, _mut7577, _mut7578, _mut7579)] = AOR_multiply(c(k, l, m, n, cnm, strict), (AOR_plus(last, lag[AOR_minus(l, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7580, _mut7581, _mut7582, _mut7583)], "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7584, _mut7585, _mut7586, _mut7587)), "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7588, _mut7589, _mut7590, _mut7591);
                last = lag[AOR_minus(l, 1, "org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.n_1250", _mut7592, _mut7593, _mut7594, _mut7595)];
            }
        }
        return last;
    }
}
