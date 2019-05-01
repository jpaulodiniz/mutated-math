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
package org.apache.commons.math3.stat.regression;

import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class is a concrete implementation of the {@link UpdatingMultipleLinearRegression} interface.
 *
 * <p>The algorithm is described in: <pre>
 * Algorithm AS 274: Least Squares Routines to Supplement Those of Gentleman
 * Author(s): Alan J. Miller
 * Source: Journal of the Royal Statistical Society.
 * Series C (Applied Statistics), Vol. 41, No. 2
 * (1992), pp. 458-478
 * Published by: Blackwell Publishing for the Royal Statistical Society
 * Stable URL: http://www.jstor.org/stable/2347583 </pre></p>
 *
 * <p>This method for multiple regression forms the solution to the OLS problem
 * by updating the QR decomposition as described by Gentleman.</p>
 *
 * @since 3.0
 */
public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {

    @Conditional
    public static boolean _mut8255 = false, _mut8256 = false, _mut8257 = false, _mut8258 = false, _mut8259 = false, _mut8260 = false, _mut8261 = false, _mut8262 = false, _mut8263 = false, _mut8264 = false, _mut8265 = false, _mut8266 = false, _mut8267 = false, _mut8268 = false, _mut8269 = false, _mut8270 = false, _mut8271 = false, _mut8272 = false, _mut8273 = false, _mut8274 = false, _mut8275 = false, _mut8276 = false, _mut8277 = false, _mut8278 = false, _mut8279 = false, _mut8280 = false, _mut8281 = false, _mut8282 = false, _mut8283 = false, _mut8284 = false, _mut8285 = false, _mut8286 = false, _mut8287 = false, _mut8288 = false, _mut8289 = false, _mut8290 = false, _mut8291 = false, _mut8292 = false, _mut8293 = false, _mut8294 = false, _mut8295 = false, _mut8296 = false, _mut8297 = false, _mut8298 = false, _mut8299 = false, _mut8300 = false, _mut8301 = false, _mut8302 = false, _mut8303 = false, _mut8304 = false, _mut8305 = false, _mut8306 = false, _mut8307 = false, _mut8308 = false, _mut8309 = false, _mut8310 = false, _mut8311 = false, _mut8312 = false, _mut8313 = false, _mut8314 = false, _mut8315 = false, _mut8316 = false, _mut8317 = false, _mut8318 = false, _mut8319 = false, _mut8320 = false, _mut8321 = false, _mut8322 = false, _mut8323 = false, _mut8324 = false, _mut8325 = false, _mut8326 = false, _mut8327 = false, _mut8328 = false, _mut8329 = false, _mut8330 = false, _mut8331 = false, _mut8332 = false, _mut8333 = false, _mut8334 = false, _mut8335 = false, _mut8336 = false, _mut8337 = false, _mut8338 = false, _mut8339 = false, _mut8340 = false, _mut8341 = false, _mut8342 = false, _mut8343 = false, _mut8344 = false, _mut8345 = false, _mut8346 = false, _mut8347 = false, _mut8348 = false, _mut8349 = false, _mut8350 = false, _mut8351 = false, _mut8352 = false, _mut8353 = false, _mut8354 = false, _mut8355 = false, _mut8356 = false, _mut8357 = false, _mut8358 = false, _mut8359 = false, _mut8360 = false, _mut8361 = false, _mut8362 = false, _mut8363 = false, _mut8364 = false, _mut8365 = false, _mut8366 = false, _mut8367 = false, _mut8368 = false, _mut8369 = false, _mut8370 = false, _mut8371 = false, _mut8372 = false, _mut8373 = false, _mut8374 = false, _mut8375 = false, _mut8376 = false, _mut8377 = false, _mut8378 = false, _mut8379 = false, _mut8380 = false, _mut8381 = false, _mut8382 = false, _mut8383 = false, _mut8384 = false, _mut8385 = false, _mut8386 = false, _mut8387 = false, _mut8388 = false, _mut8389 = false, _mut8390 = false, _mut8391 = false, _mut8392 = false, _mut8393 = false, _mut8394 = false, _mut8395 = false, _mut8396 = false, _mut8397 = false, _mut8398 = false, _mut8399 = false, _mut8400 = false, _mut8401 = false, _mut8402 = false, _mut8403 = false, _mut8404 = false, _mut8405 = false, _mut8406 = false, _mut8407 = false, _mut8408 = false, _mut8409 = false, _mut8410 = false, _mut8411 = false, _mut8412 = false, _mut8413 = false, _mut8414 = false, _mut8415 = false, _mut8416 = false, _mut8417 = false, _mut8418 = false, _mut8419 = false, _mut8420 = false, _mut8421 = false, _mut8422 = false, _mut8423 = false, _mut8424 = false, _mut8425 = false, _mut8426 = false, _mut8427 = false, _mut8428 = false, _mut8429 = false, _mut8430 = false, _mut8431 = false, _mut8432 = false, _mut8433 = false, _mut8434 = false, _mut8435 = false, _mut8436 = false, _mut8437 = false, _mut8438 = false, _mut8439 = false, _mut8440 = false, _mut8441 = false, _mut8442 = false, _mut8443 = false, _mut8444 = false, _mut8445 = false, _mut8446 = false, _mut8447 = false, _mut8448 = false, _mut8449 = false, _mut8450 = false, _mut8451 = false, _mut8452 = false, _mut8453 = false, _mut8454 = false, _mut8455 = false, _mut8456 = false, _mut8457 = false, _mut8458 = false, _mut8459 = false, _mut8460 = false, _mut8461 = false, _mut8462 = false, _mut8463 = false, _mut8464 = false, _mut8465 = false, _mut8466 = false, _mut8467 = false, _mut8468 = false, _mut8469 = false, _mut8470 = false, _mut8471 = false, _mut8472 = false, _mut8473 = false, _mut8474 = false, _mut8475 = false, _mut8476 = false, _mut8477 = false, _mut8478 = false, _mut8479 = false, _mut8480 = false, _mut8481 = false, _mut8482 = false, _mut8483 = false, _mut8484 = false, _mut8485 = false, _mut8486 = false, _mut8487 = false, _mut8488 = false, _mut8489 = false, _mut8490 = false, _mut8491 = false, _mut8492 = false, _mut8493 = false, _mut8494 = false, _mut8495 = false, _mut8496 = false, _mut8497 = false, _mut8498 = false, _mut8499 = false, _mut8500 = false, _mut8501 = false, _mut8502 = false, _mut8503 = false, _mut8504 = false, _mut8505 = false, _mut8506 = false, _mut8507 = false, _mut8508 = false, _mut8509 = false, _mut8510 = false, _mut8511 = false, _mut8512 = false, _mut8513 = false, _mut8514 = false, _mut8515 = false, _mut8516 = false, _mut8517 = false, _mut8518 = false, _mut8519 = false, _mut8520 = false, _mut8521 = false, _mut8522 = false, _mut8523 = false, _mut8524 = false, _mut8525 = false, _mut8526 = false, _mut8527 = false, _mut8528 = false, _mut8529 = false, _mut8530 = false, _mut8531 = false, _mut8532 = false, _mut8533 = false, _mut8534 = false, _mut8535 = false, _mut8536 = false, _mut8537 = false, _mut8538 = false, _mut8539 = false, _mut8540 = false, _mut8541 = false, _mut8542 = false, _mut8543 = false, _mut8544 = false, _mut8545 = false, _mut8546 = false, _mut8547 = false, _mut8548 = false, _mut8549 = false, _mut8550 = false, _mut8551 = false, _mut8552 = false, _mut8553 = false, _mut8554 = false, _mut8555 = false, _mut8556 = false, _mut8557 = false, _mut8558 = false, _mut8559 = false, _mut8560 = false, _mut8561 = false, _mut8562 = false, _mut8563 = false, _mut8564 = false, _mut8565 = false, _mut8566 = false, _mut8567 = false, _mut8568 = false, _mut8569 = false, _mut8570 = false, _mut8571 = false, _mut8572 = false, _mut8573 = false, _mut8574 = false, _mut8575 = false, _mut8576 = false, _mut8577 = false, _mut8578 = false, _mut8579 = false, _mut8580 = false, _mut8581 = false, _mut8582 = false, _mut8583 = false, _mut8584 = false, _mut8585 = false, _mut8586 = false, _mut8587 = false, _mut8588 = false, _mut8589 = false, _mut8590 = false, _mut8591 = false, _mut8592 = false, _mut8593 = false, _mut8594 = false, _mut8595 = false, _mut8596 = false, _mut8597 = false, _mut8598 = false, _mut8599 = false, _mut8600 = false, _mut8601 = false, _mut8602 = false, _mut8603 = false, _mut8604 = false, _mut8605 = false, _mut8606 = false, _mut8607 = false, _mut8608 = false, _mut8609 = false, _mut8610 = false, _mut8611 = false, _mut8612 = false, _mut8613 = false, _mut8614 = false, _mut8615 = false, _mut8616 = false, _mut8617 = false, _mut8618 = false, _mut8619 = false, _mut8620 = false, _mut8621 = false, _mut8622 = false, _mut8623 = false, _mut8624 = false, _mut8625 = false, _mut8626 = false, _mut8627 = false, _mut8628 = false, _mut8629 = false, _mut8630 = false, _mut8631 = false, _mut8632 = false, _mut8633 = false, _mut8634 = false, _mut8635 = false, _mut8636 = false, _mut8637 = false, _mut8638 = false, _mut8639 = false, _mut8640 = false, _mut8641 = false, _mut8642 = false, _mut8643 = false, _mut8644 = false, _mut8645 = false, _mut8646 = false, _mut8647 = false, _mut8648 = false, _mut8649 = false, _mut8650 = false, _mut8651 = false, _mut8652 = false, _mut8653 = false, _mut8654 = false, _mut8655 = false, _mut8656 = false, _mut8657 = false, _mut8658 = false, _mut8659 = false, _mut8660 = false, _mut8661 = false, _mut8662 = false, _mut8663 = false, _mut8664 = false, _mut8665 = false, _mut8666 = false, _mut8667 = false, _mut8668 = false, _mut8669 = false, _mut8670 = false, _mut8671 = false, _mut8672 = false, _mut8673 = false, _mut8674 = false, _mut8675 = false, _mut8676 = false, _mut8677 = false, _mut8678 = false, _mut8679 = false, _mut8680 = false, _mut8681 = false, _mut8682 = false, _mut8683 = false, _mut8684 = false, _mut8685 = false, _mut8686 = false, _mut8687 = false, _mut8688 = false, _mut8689 = false, _mut8690 = false, _mut8691 = false, _mut8692 = false, _mut8693 = false, _mut8694 = false, _mut8695 = false, _mut8696 = false, _mut8697 = false, _mut8698 = false, _mut8699 = false, _mut8700 = false, _mut8701 = false, _mut8702 = false, _mut8703 = false, _mut8704 = false, _mut8705 = false, _mut8706 = false, _mut8707 = false, _mut8708 = false, _mut8709 = false, _mut8710 = false, _mut8711 = false, _mut8712 = false, _mut8713 = false, _mut8714 = false, _mut8715 = false, _mut8716 = false, _mut8717 = false, _mut8718 = false, _mut8719 = false, _mut8720 = false, _mut8721 = false, _mut8722 = false, _mut8723 = false, _mut8724 = false, _mut8725 = false, _mut8726 = false, _mut8727 = false, _mut8728 = false, _mut8729 = false, _mut8730 = false, _mut8731 = false, _mut8732 = false, _mut8733 = false, _mut8734 = false, _mut8735 = false, _mut8736 = false, _mut8737 = false, _mut8738 = false, _mut8739 = false, _mut8740 = false, _mut8741 = false, _mut8742 = false, _mut8743 = false, _mut8744 = false, _mut8745 = false, _mut8746 = false, _mut8747 = false, _mut8748 = false, _mut8749 = false, _mut8750 = false, _mut8751 = false, _mut8752 = false, _mut8753 = false, _mut8754 = false, _mut8755 = false, _mut8756 = false, _mut8757 = false, _mut8758 = false, _mut8759 = false, _mut8760 = false, _mut8761 = false, _mut8762 = false, _mut8763 = false, _mut8764 = false, _mut8765 = false, _mut8766 = false, _mut8767 = false, _mut8768 = false, _mut8769 = false, _mut8770 = false, _mut8771 = false, _mut8772 = false, _mut8773 = false, _mut8774 = false, _mut8775 = false, _mut8776 = false, _mut8777 = false, _mut8778 = false, _mut8779 = false, _mut8780 = false, _mut8781 = false, _mut8782 = false, _mut8783 = false, _mut8784 = false, _mut8785 = false, _mut8786 = false, _mut8787 = false, _mut8788 = false, _mut8789 = false, _mut8790 = false, _mut8791 = false, _mut8792 = false, _mut8793 = false, _mut8794 = false, _mut8795 = false, _mut8796 = false, _mut8797 = false, _mut8798 = false, _mut8799 = false, _mut8800 = false, _mut8801 = false, _mut8802 = false, _mut8803 = false, _mut8804 = false, _mut8805 = false, _mut8806 = false, _mut8807 = false, _mut8808 = false, _mut8809 = false, _mut8810 = false, _mut8811 = false, _mut8812 = false, _mut8813 = false, _mut8814 = false, _mut8815 = false, _mut8816 = false, _mut8817 = false, _mut8818 = false, _mut8819 = false, _mut8820 = false, _mut8821 = false, _mut8822 = false, _mut8823 = false, _mut8824 = false, _mut8825 = false, _mut8826 = false, _mut8827 = false, _mut8828 = false, _mut8829 = false, _mut8830 = false, _mut8831 = false, _mut8832 = false, _mut8833 = false, _mut8834 = false, _mut8835 = false, _mut8836 = false, _mut8837 = false, _mut8838 = false, _mut8839 = false, _mut8840 = false, _mut8841 = false, _mut8842 = false, _mut8843 = false, _mut8844 = false, _mut8845 = false, _mut8846 = false, _mut8847 = false, _mut8848 = false, _mut8849 = false, _mut8850 = false, _mut8851 = false, _mut8852 = false, _mut8853 = false, _mut8854 = false, _mut8855 = false, _mut8856 = false, _mut8857 = false, _mut8858 = false, _mut8859 = false, _mut8860 = false, _mut8861 = false, _mut8862 = false, _mut8863 = false, _mut8864 = false, _mut8865 = false, _mut8866 = false, _mut8867 = false, _mut8868 = false, _mut8869 = false, _mut8870 = false, _mut8871 = false, _mut8872 = false, _mut8873 = false, _mut8874 = false, _mut8875 = false, _mut8876 = false, _mut8877 = false, _mut8878 = false, _mut8879 = false, _mut8880 = false, _mut8881 = false, _mut8882 = false, _mut8883 = false, _mut8884 = false, _mut8885 = false, _mut8886 = false, _mut8887 = false, _mut8888 = false, _mut8889 = false, _mut8890 = false, _mut8891 = false, _mut8892 = false, _mut8893 = false, _mut8894 = false, _mut8895 = false, _mut8896 = false, _mut8897 = false, _mut8898 = false, _mut8899 = false, _mut8900 = false, _mut8901 = false, _mut8902 = false, _mut8903 = false, _mut8904 = false, _mut8905 = false, _mut8906 = false, _mut8907 = false, _mut8908 = false, _mut8909 = false, _mut8910 = false, _mut8911 = false, _mut8912 = false, _mut8913 = false, _mut8914 = false, _mut8915 = false, _mut8916 = false, _mut8917 = false, _mut8918 = false, _mut8919 = false, _mut8920 = false, _mut8921 = false, _mut8922 = false, _mut8923 = false, _mut8924 = false, _mut8925 = false, _mut8926 = false, _mut8927 = false, _mut8928 = false, _mut8929 = false, _mut8930 = false, _mut8931 = false, _mut8932 = false, _mut8933 = false, _mut8934 = false, _mut8935 = false, _mut8936 = false, _mut8937 = false, _mut8938 = false, _mut8939 = false, _mut8940 = false, _mut8941 = false, _mut8942 = false, _mut8943 = false, _mut8944 = false, _mut8945 = false, _mut8946 = false, _mut8947 = false, _mut8948 = false, _mut8949 = false, _mut8950 = false, _mut8951 = false, _mut8952 = false, _mut8953 = false, _mut8954 = false, _mut8955 = false, _mut8956 = false, _mut8957 = false, _mut8958 = false, _mut8959 = false, _mut8960 = false, _mut8961 = false, _mut8962 = false, _mut8963 = false, _mut8964 = false, _mut8965 = false, _mut8966 = false, _mut8967 = false, _mut8968 = false, _mut8969 = false, _mut8970 = false, _mut8971 = false, _mut8972 = false, _mut8973 = false, _mut8974 = false, _mut8975 = false, _mut8976 = false, _mut8977 = false, _mut8978 = false, _mut8979 = false, _mut8980 = false, _mut8981 = false, _mut8982 = false, _mut8983 = false, _mut8984 = false, _mut8985 = false, _mut8986 = false, _mut8987 = false, _mut8988 = false, _mut8989 = false, _mut8990 = false, _mut8991 = false, _mut8992 = false, _mut8993 = false, _mut8994 = false, _mut8995 = false, _mut8996 = false, _mut8997 = false, _mut8998 = false, _mut8999 = false, _mut9000 = false, _mut9001 = false, _mut9002 = false, _mut9003 = false, _mut9004 = false, _mut9005 = false, _mut9006 = false, _mut9007 = false, _mut9008 = false, _mut9009 = false, _mut9010 = false, _mut9011 = false, _mut9012 = false, _mut9013 = false, _mut9014 = false, _mut9015 = false, _mut9016 = false, _mut9017 = false, _mut9018 = false, _mut9019 = false, _mut9020 = false, _mut9021 = false, _mut9022 = false, _mut9023 = false, _mut9024 = false, _mut9025 = false, _mut9026 = false, _mut9027 = false, _mut9028 = false, _mut9029 = false, _mut9030 = false, _mut9031 = false, _mut9032 = false, _mut9033 = false, _mut9034 = false, _mut9035 = false, _mut9036 = false, _mut9037 = false, _mut9038 = false, _mut9039 = false, _mut9040 = false, _mut9041 = false, _mut9042 = false, _mut9043 = false, _mut9044 = false, _mut9045 = false, _mut9046 = false, _mut9047 = false, _mut9048 = false, _mut9049 = false, _mut9050 = false, _mut9051 = false, _mut9052 = false, _mut9053 = false, _mut9054 = false, _mut9055 = false, _mut9056 = false, _mut9057 = false, _mut9058 = false, _mut9059 = false, _mut9060 = false, _mut9061 = false, _mut9062 = false, _mut9063 = false, _mut9064 = false, _mut9065 = false, _mut9066 = false, _mut9067 = false, _mut9068 = false, _mut9069 = false, _mut9070 = false, _mut9071 = false, _mut9072 = false, _mut9073 = false, _mut9074 = false, _mut9075 = false, _mut9076 = false, _mut9077 = false, _mut9078 = false, _mut9079 = false, _mut9080 = false, _mut9081 = false, _mut9082 = false, _mut9083 = false, _mut9084 = false, _mut9085 = false, _mut9086 = false, _mut9087 = false, _mut9088 = false, _mut9089 = false, _mut9090 = false, _mut9091 = false, _mut9092 = false, _mut9093 = false, _mut9094 = false, _mut9095 = false, _mut9096 = false, _mut9097 = false, _mut9098 = false, _mut9099 = false, _mut9100 = false, _mut9101 = false, _mut9102 = false, _mut9103 = false, _mut9104 = false, _mut9105 = false, _mut9106 = false, _mut9107 = false, _mut9108 = false, _mut9109 = false, _mut9110 = false, _mut9111 = false, _mut9112 = false, _mut9113 = false, _mut9114 = false, _mut9115 = false, _mut9116 = false, _mut9117 = false, _mut9118 = false, _mut9119 = false, _mut9120 = false, _mut9121 = false, _mut9122 = false, _mut9123 = false, _mut9124 = false, _mut9125 = false, _mut9126 = false, _mut9127 = false, _mut9128 = false, _mut9129 = false, _mut9130 = false, _mut9131 = false, _mut9132 = false, _mut9133 = false, _mut9134 = false, _mut9135 = false, _mut9136 = false, _mut9137 = false, _mut9138 = false, _mut9139 = false, _mut9140 = false, _mut9141 = false, _mut9142 = false, _mut9143 = false, _mut9144 = false, _mut9145 = false, _mut9146 = false, _mut9147 = false, _mut9148 = false, _mut9149 = false, _mut9150 = false, _mut9151 = false, _mut9152 = false, _mut9153 = false, _mut9154 = false, _mut9155 = false, _mut9156 = false, _mut9157 = false, _mut9158 = false, _mut9159 = false, _mut9160 = false, _mut9161 = false, _mut9162 = false, _mut9163 = false, _mut9164 = false, _mut9165 = false, _mut9166 = false, _mut9167 = false, _mut9168 = false, _mut9169 = false, _mut9170 = false, _mut9171 = false, _mut9172 = false, _mut9173 = false, _mut9174 = false, _mut9175 = false, _mut9176 = false, _mut9177 = false, _mut9178 = false, _mut9179 = false, _mut9180 = false, _mut9181 = false, _mut9182 = false, _mut9183 = false, _mut9184 = false, _mut9185 = false, _mut9186 = false, _mut9187 = false, _mut9188 = false, _mut9189 = false, _mut9190 = false, _mut9191 = false, _mut9192 = false, _mut9193 = false, _mut9194 = false, _mut9195 = false, _mut9196 = false, _mut9197 = false, _mut9198 = false, _mut9199 = false, _mut9200 = false, _mut9201 = false, _mut9202 = false, _mut9203 = false, _mut9204 = false, _mut9205 = false, _mut9206 = false, _mut9207 = false, _mut9208 = false, _mut9209 = false, _mut9210 = false, _mut9211 = false, _mut9212 = false, _mut9213 = false, _mut9214 = false, _mut9215 = false, _mut9216 = false, _mut9217 = false, _mut9218 = false, _mut9219 = false, _mut9220 = false, _mut9221 = false, _mut9222 = false, _mut9223 = false, _mut9224 = false, _mut9225 = false, _mut9226 = false, _mut9227 = false, _mut9228 = false, _mut9229 = false, _mut9230 = false, _mut9231 = false, _mut9232 = false, _mut9233 = false, _mut9234 = false, _mut9235 = false, _mut9236 = false, _mut9237 = false, _mut9238 = false, _mut9239 = false, _mut9240 = false, _mut9241 = false, _mut9242 = false, _mut9243 = false, _mut9244 = false, _mut9245 = false, _mut9246 = false, _mut9247 = false, _mut9248 = false, _mut9249 = false, _mut9250 = false, _mut9251 = false, _mut9252 = false, _mut9253 = false, _mut9254 = false, _mut9255 = false, _mut9256 = false, _mut9257 = false, _mut9258 = false, _mut9259 = false, _mut9260 = false, _mut9261 = false, _mut9262 = false, _mut9263 = false, _mut9264 = false, _mut9265 = false, _mut9266 = false, _mut9267 = false, _mut9268 = false, _mut9269 = false, _mut9270 = false, _mut9271 = false, _mut9272 = false, _mut9273 = false, _mut9274 = false, _mut9275 = false, _mut9276 = false, _mut9277 = false, _mut9278 = false, _mut9279 = false, _mut9280 = false, _mut9281 = false, _mut9282 = false, _mut9283 = false, _mut9284 = false, _mut9285 = false, _mut9286 = false, _mut9287 = false, _mut9288 = false, _mut9289 = false, _mut9290 = false, _mut9291 = false, _mut9292 = false, _mut9293 = false, _mut9294 = false, _mut9295 = false, _mut9296 = false, _mut9297 = false, _mut9298 = false, _mut9299 = false, _mut9300 = false, _mut9301 = false, _mut9302 = false, _mut9303 = false, _mut9304 = false, _mut9305 = false, _mut9306 = false, _mut9307 = false, _mut9308 = false, _mut9309 = false, _mut9310 = false, _mut9311 = false, _mut9312 = false, _mut9313 = false, _mut9314 = false, _mut9315 = false, _mut9316 = false, _mut9317 = false, _mut9318 = false, _mut9319 = false, _mut9320 = false, _mut9321 = false, _mut9322 = false, _mut9323 = false, _mut9324 = false, _mut9325 = false, _mut9326 = false, _mut9327 = false, _mut9328 = false, _mut9329 = false, _mut9330 = false, _mut9331 = false, _mut9332 = false, _mut9333 = false, _mut9334 = false, _mut9335 = false, _mut9336 = false, _mut9337 = false, _mut9338 = false, _mut9339 = false, _mut9340 = false, _mut9341 = false, _mut9342 = false, _mut9343 = false, _mut9344 = false, _mut9345 = false, _mut9346 = false, _mut9347 = false, _mut9348 = false, _mut9349 = false, _mut9350 = false, _mut9351 = false, _mut9352 = false, _mut9353 = false, _mut9354 = false, _mut9355 = false, _mut9356 = false, _mut9357 = false, _mut9358 = false, _mut9359 = false, _mut9360 = false, _mut9361 = false, _mut9362 = false, _mut9363 = false, _mut9364 = false, _mut9365 = false, _mut9366 = false, _mut9367 = false, _mut9368 = false, _mut9369 = false, _mut9370 = false, _mut9371 = false, _mut9372 = false, _mut9373 = false, _mut9374 = false, _mut9375 = false, _mut9376 = false, _mut9377 = false, _mut9378 = false, _mut9379 = false, _mut9380 = false, _mut9381 = false, _mut9382 = false, _mut9383 = false, _mut9384 = false, _mut9385 = false, _mut9386 = false, _mut9387 = false, _mut9388 = false, _mut9389 = false, _mut9390 = false, _mut9391 = false, _mut9392 = false, _mut9393 = false, _mut9394 = false, _mut9395 = false, _mut9396 = false, _mut9397 = false, _mut9398 = false, _mut9399 = false, _mut9400 = false, _mut9401 = false, _mut9402 = false, _mut9403 = false, _mut9404 = false, _mut9405 = false, _mut9406 = false, _mut9407 = false, _mut9408 = false, _mut9409 = false, _mut9410 = false, _mut9411 = false, _mut9412 = false, _mut9413 = false, _mut9414 = false, _mut9415 = false, _mut9416 = false, _mut9417 = false, _mut9418 = false, _mut9419 = false, _mut9420 = false, _mut9421 = false, _mut9422 = false, _mut9423 = false, _mut9424 = false, _mut9425 = false, _mut9426 = false, _mut9427 = false, _mut9428 = false, _mut9429 = false, _mut9430 = false, _mut9431 = false, _mut9432 = false, _mut9433 = false, _mut9434 = false, _mut9435 = false, _mut9436 = false, _mut9437 = false, _mut9438 = false, _mut9439 = false, _mut9440 = false, _mut9441 = false, _mut9442 = false, _mut9443 = false, _mut9444 = false, _mut9445 = false, _mut9446 = false, _mut9447 = false, _mut9448 = false, _mut9449 = false, _mut9450 = false, _mut9451 = false, _mut9452 = false, _mut9453 = false, _mut9454 = false, _mut9455 = false, _mut9456 = false, _mut9457 = false, _mut9458 = false, _mut9459 = false, _mut9460 = false, _mut9461 = false, _mut9462 = false, _mut9463 = false, _mut9464 = false, _mut9465 = false, _mut9466 = false, _mut9467 = false, _mut9468 = false, _mut9469 = false, _mut9470 = false, _mut9471 = false, _mut9472 = false, _mut9473 = false, _mut9474 = false, _mut9475 = false, _mut9476 = false, _mut9477 = false, _mut9478 = false, _mut9479 = false, _mut9480 = false, _mut9481 = false, _mut9482 = false, _mut9483 = false, _mut9484 = false, _mut9485 = false, _mut9486 = false, _mut9487 = false, _mut9488 = false, _mut9489 = false, _mut9490 = false, _mut9491 = false, _mut9492 = false, _mut9493 = false, _mut9494 = false, _mut9495 = false, _mut9496 = false, _mut9497 = false, _mut9498 = false, _mut9499 = false, _mut9500 = false, _mut9501 = false, _mut9502 = false, _mut9503 = false, _mut9504 = false, _mut9505 = false, _mut9506 = false, _mut9507 = false, _mut9508 = false, _mut9509 = false, _mut9510 = false, _mut9511 = false, _mut9512 = false, _mut9513 = false, _mut9514 = false, _mut9515 = false, _mut9516 = false, _mut9517 = false, _mut9518 = false, _mut9519 = false, _mut9520 = false, _mut9521 = false, _mut9522 = false, _mut9523 = false, _mut9524 = false, _mut9525 = false, _mut9526 = false, _mut9527 = false, _mut9528 = false, _mut9529 = false, _mut9530 = false, _mut9531 = false, _mut9532 = false, _mut9533 = false, _mut9534 = false, _mut9535 = false, _mut9536 = false, _mut9537 = false, _mut9538 = false, _mut9539 = false, _mut9540 = false, _mut9541 = false, _mut9542 = false, _mut9543 = false, _mut9544 = false, _mut9545 = false, _mut9546 = false, _mut9547 = false, _mut9548 = false, _mut9549 = false, _mut9550 = false, _mut9551 = false, _mut9552 = false, _mut9553 = false, _mut9554 = false, _mut9555 = false, _mut9556 = false, _mut9557 = false, _mut9558 = false, _mut9559 = false, _mut9560 = false, _mut9561 = false, _mut9562 = false, _mut9563 = false, _mut9564 = false, _mut9565 = false, _mut9566 = false, _mut9567 = false, _mut9568 = false, _mut9569 = false, _mut9570 = false, _mut9571 = false, _mut9572 = false, _mut9573 = false, _mut9574 = false, _mut9575 = false, _mut9576 = false, _mut9577 = false, _mut9578 = false, _mut9579 = false, _mut9580 = false, _mut9581 = false, _mut9582 = false, _mut9583 = false, _mut9584 = false, _mut9585 = false, _mut9586 = false, _mut9587 = false, _mut9588 = false, _mut9589 = false, _mut9590 = false, _mut9591 = false, _mut9592 = false, _mut9593 = false, _mut9594 = false, _mut9595 = false, _mut9596 = false, _mut9597 = false, _mut9598 = false, _mut9599 = false, _mut9600 = false, _mut9601 = false, _mut9602 = false, _mut9603 = false, _mut9604 = false, _mut9605 = false, _mut9606 = false, _mut9607 = false, _mut9608 = false, _mut9609 = false, _mut9610 = false, _mut9611 = false, _mut9612 = false, _mut9613 = false, _mut9614 = false, _mut9615 = false, _mut9616 = false, _mut9617 = false, _mut9618 = false, _mut9619 = false, _mut9620 = false, _mut9621 = false, _mut9622 = false, _mut9623 = false, _mut9624 = false, _mut9625 = false, _mut9626 = false, _mut9627 = false, _mut9628 = false, _mut9629 = false, _mut9630 = false, _mut9631 = false, _mut9632 = false, _mut9633 = false, _mut9634 = false, _mut9635 = false, _mut9636 = false, _mut9637 = false, _mut9638 = false, _mut9639 = false, _mut9640 = false, _mut9641 = false, _mut9642 = false, _mut9643 = false, _mut9644 = false, _mut9645 = false, _mut9646 = false, _mut9647 = false, _mut9648 = false, _mut9649 = false, _mut9650 = false, _mut9651 = false, _mut9652 = false, _mut9653 = false, _mut9654 = false, _mut9655 = false, _mut9656 = false, _mut9657 = false, _mut9658 = false, _mut9659 = false, _mut9660 = false, _mut9661 = false, _mut9662 = false, _mut9663 = false, _mut9664 = false, _mut9665 = false, _mut9666 = false, _mut9667 = false, _mut9668 = false, _mut9669 = false, _mut9670 = false, _mut9671 = false, _mut9672 = false, _mut9673 = false, _mut9674 = false, _mut9675 = false, _mut9676 = false, _mut9677 = false, _mut9678 = false, _mut9679 = false, _mut9680 = false, _mut9681 = false, _mut9682 = false, _mut9683 = false, _mut9684 = false, _mut9685 = false, _mut9686 = false, _mut9687 = false, _mut9688 = false, _mut9689 = false, _mut9690 = false, _mut9691 = false, _mut9692 = false, _mut9693 = false, _mut9694 = false, _mut9695 = false, _mut9696 = false, _mut9697 = false, _mut9698 = false, _mut9699 = false, _mut9700 = false, _mut9701 = false, _mut9702 = false, _mut9703 = false, _mut9704 = false, _mut9705 = false, _mut9706 = false, _mut9707 = false, _mut9708 = false, _mut9709 = false, _mut9710 = false, _mut9711 = false, _mut9712 = false, _mut9713 = false, _mut9714 = false, _mut9715 = false, _mut9716 = false, _mut9717 = false, _mut9718 = false, _mut9719 = false, _mut9720 = false, _mut9721 = false, _mut9722 = false, _mut9723 = false, _mut9724 = false, _mut9725 = false, _mut9726 = false, _mut9727 = false, _mut9728 = false, _mut9729 = false, _mut9730 = false, _mut9731 = false, _mut9732 = false, _mut9733 = false, _mut9734 = false, _mut9735 = false, _mut9736 = false, _mut9737 = false, _mut9738 = false, _mut9739 = false, _mut9740 = false, _mut9741 = false, _mut9742 = false, _mut9743 = false, _mut9744 = false, _mut9745 = false, _mut9746 = false, _mut9747 = false, _mut9748 = false, _mut9749 = false, _mut9750 = false, _mut9751 = false, _mut9752 = false, _mut9753 = false, _mut9754 = false, _mut9755 = false, _mut9756 = false, _mut9757 = false, _mut9758 = false, _mut9759 = false, _mut9760 = false, _mut9761 = false, _mut9762 = false, _mut9763 = false, _mut9764 = false, _mut9765 = false, _mut9766 = false, _mut9767 = false, _mut9768 = false, _mut9769 = false, _mut9770 = false, _mut9771 = false, _mut9772 = false, _mut9773 = false, _mut9774 = false, _mut9775 = false, _mut9776 = false, _mut9777 = false, _mut9778 = false, _mut9779 = false, _mut9780 = false, _mut9781 = false, _mut9782 = false, _mut9783 = false, _mut9784 = false, _mut9785 = false, _mut9786 = false, _mut9787 = false, _mut9788 = false, _mut9789 = false, _mut9790 = false, _mut9791 = false, _mut9792 = false, _mut9793 = false, _mut9794 = false, _mut9795 = false, _mut9796 = false, _mut9797 = false, _mut9798 = false;

    /**
     * number of variables in regression
     */
    private final int nvars;

    /**
     * diagonals of cross products matrix
     */
    private final double[] d;

    /**
     * the elements of the R`Y
     */
    private final double[] rhs;

    /**
     * the off diagonal portion of the R matrix
     */
    private final double[] r;

    /**
     * the tolerance for each of the variables
     */
    private final double[] tol;

    /**
     * residual sum of squares for all nested regressions
     */
    private final double[] rss;

    /**
     * order of the regressors
     */
    private final int[] vorder;

    /**
     * scratch space for tolerance calc
     */
    private final double[] work_tolset;

    /**
     * number of observations entered
     */
    private long nobs = 0;

    /**
     * sum of squared errors of largest regression
     */
    private double sserr = 0.0;

    /**
     * has rss been called?
     */
    private boolean rss_set = false;

    /**
     * has the tolerance setting method been called
     */
    private boolean tol_set = false;

    /**
     * flags for variables with linear dependency problems
     */
    private final boolean[] lindep;

    /**
     * singular x values
     */
    private final double[] x_sing;

    /**
     * workspace for singularity method
     */
    private final double[] work_sing;

    /**
     * summation of Y variable
     */
    private double sumy = 0.0;

    /**
     * summation of squared Y values
     */
    private double sumsqy = 0.0;

    /**
     * boolean flag whether a regression constant is added
     */
    private boolean hasIntercept;

    /**
     * zero tolerance
     */
    private final double epsilon;

    /**
     *  Set the default constructor to private access
     *  to prevent inadvertent instantiation
     */
    @SuppressWarnings("unused")
    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    /**
     * This is the augmented constructor for the MillerUpdatingRegression class.
     *
     * @param numberOfVariables number of regressors to expect, not including constant
     * @param includeConstant include a constant automatically
     * @param errorTolerance  zero tolerance, how machine zero is determined
     * @throws ModelSpecificationException if {@code numberOfVariables is less than 1}
     */
    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant, double errorTolerance) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99");
        if (ROR_less(numberOfVariables, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8255, _mut8256, _mut8257, _mut8258, _mut8259)) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS);
        }
        if (includeConstant) {
            this.nvars = AOR_plus(numberOfVariables, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8260, _mut8261, _mut8262, _mut8263);
        } else {
            this.nvars = numberOfVariables;
        }
        this.hasIntercept = includeConstant;
        this.nobs = 0;
        this.d = new double[this.nvars];
        this.rhs = new double[this.nvars];
        this.r = new double[AOR_divide(AOR_multiply(this.nvars, (AOR_minus(this.nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8264, _mut8265, _mut8266, _mut8267)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8268, _mut8269, _mut8270, _mut8271), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8272, _mut8273, _mut8274, _mut8275)];
        this.tol = new double[this.nvars];
        this.rss = new double[this.nvars];
        this.vorder = new int[this.nvars];
        this.x_sing = new double[this.nvars];
        this.work_sing = new double[this.nvars];
        this.work_tolset = new double[this.nvars];
        this.lindep = new boolean[this.nvars];
        for (int i = 0; ROR_less(i, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8276, _mut8277, _mut8278, _mut8279, _mut8280); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99");
            vorder[i] = i;
        }
        if (ROR_greater(errorTolerance, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.MillerUpdatingRegression_99", _mut8281, _mut8282, _mut8283, _mut8284, _mut8285)) {
            this.epsilon = errorTolerance;
        } else {
            this.epsilon = -errorTolerance;
        }
    }

    /**
     * Primary constructor for the MillerUpdatingRegression.
     *
     * @param numberOfVariables maximum number of potential regressors
     * @param includeConstant include a constant automatically
     * @throws ModelSpecificationException if {@code numberOfVariables is less than 1}
     */
    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant) throws ModelSpecificationException {
        this(numberOfVariables, includeConstant, Precision.EPSILON);
    }

    /**
     * A getter method which determines whether a constant is included.
     * @return true regression has an intercept, false no intercept
     */
    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    /**
     * Gets the number of observations added to the regression model.
     * @return number of observations
     */
    public long getN() {
        return this.nobs;
    }

    /**
     * Adds an observation to the regression model.
     * @param x the array with regressor values
     * @param y  the value of dependent variable given these regressors
     * @exception ModelSpecificationException if the length of {@code x} does not equal
     * the number of independent variables in the model
     */
    public void addObservation(final double[] x, final double y) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166");
        if ((_mut8302 ? (((_mut8291 ? (!this.hasIntercept || ROR_not_equals(x.length, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8286, _mut8287, _mut8288, _mut8289, _mut8290)) : (!this.hasIntercept && ROR_not_equals(x.length, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8286, _mut8287, _mut8288, _mut8289, _mut8290)))) && ((_mut8301 ? (this.hasIntercept || ROR_not_equals(AOR_plus(x.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8292, _mut8293, _mut8294, _mut8295), nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8296, _mut8297, _mut8298, _mut8299, _mut8300)) : (this.hasIntercept && ROR_not_equals(AOR_plus(x.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8292, _mut8293, _mut8294, _mut8295), nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8296, _mut8297, _mut8298, _mut8299, _mut8300))))) : (((_mut8291 ? (!this.hasIntercept || ROR_not_equals(x.length, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8286, _mut8287, _mut8288, _mut8289, _mut8290)) : (!this.hasIntercept && ROR_not_equals(x.length, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8286, _mut8287, _mut8288, _mut8289, _mut8290)))) || ((_mut8301 ? (this.hasIntercept || ROR_not_equals(AOR_plus(x.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8292, _mut8293, _mut8294, _mut8295), nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8296, _mut8297, _mut8298, _mut8299, _mut8300)) : (this.hasIntercept && ROR_not_equals(AOR_plus(x.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8292, _mut8293, _mut8294, _mut8295), nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8296, _mut8297, _mut8298, _mut8299, _mut8300))))))) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, x.length, nvars);
        }
        if (!this.hasIntercept) {
            include(MathArrays.copyOf(x, x.length), 1.0, y);
        } else {
            final double[] tmp = new double[AOR_plus(x.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservation_166", _mut8303, _mut8304, _mut8305, _mut8306)];
            System.arraycopy(x, 0, tmp, 1, x.length);
            tmp[0] = 1.0;
            include(tmp, 1.0, y);
        }
        ++nobs;
    }

    /**
     * Adds multiple observations to the model.
     * @param x observations on the regressors
     * @param y observations on the regressand
     * @throws ModelSpecificationException if {@code x} is not rectangular, does not match
     * the length of {@code y} or does not contain sufficient data to estimate the model
     */
    public void addObservations(double[][] x, double[] y) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193");
        if ((_mut8313 ? ((_mut8307 ? ((x == null) && (y == null)) : ((x == null) || (y == null))) && (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8308, _mut8309, _mut8310, _mut8311, _mut8312))) : ((_mut8307 ? ((x == null) && (y == null)) : ((x == null) || (y == null))) || (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8308, _mut8309, _mut8310, _mut8311, _mut8312))))) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, (x == null) ? 0 : x.length, (y == null) ? 0 : y.length);
        }
        if (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8314, _mut8315, _mut8316, _mut8317, _mut8318)) {
            // Must be no y data either
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA);
        }
        if (ROR_greater(AOR_plus(x[0].length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8319, _mut8320, _mut8321, _mut8322), x.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8323, _mut8324, _mut8325, _mut8326, _mut8327)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, x.length, x[0].length);
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193", _mut8328, _mut8329, _mut8330, _mut8331, _mut8332); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.addObservations_193");
            addObservation(x[i], y[i]);
        }
    }

    /**
     * The include method is where the QR decomposition occurs. This statement forms all
     * intermediate data which will be used for all derivative measures.
     * According to the miller paper, note that in the original implementation the x vector
     * is overwritten. In this implementation, the include method is passed a copy of the
     * original data vector so that there is no contamination of the data. Additionally,
     * this method differs slightly from Gentleman's method, in that the assumption is
     * of dense design matrices, there is some advantage in using the original gentleman algorithm
     * on sparse matrices.
     *
     * @param x observations on the regressors
     * @param wi weight of the this observation (-1,1)
     * @param yi observation on the regressand
     */
    private void include(final double[] x, final double wi, final double yi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228");
        int nextr = 0;
        double w = wi;
        double y = yi;
        double xi;
        double di;
        double wxi;
        double dpi;
        double xk;
        double _w;
        this.rss_set = false;
        sumy = smartAdd(yi, sumy);
        sumsqy = smartAdd(sumsqy, AOR_multiply(yi, yi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8333, _mut8334, _mut8335, _mut8336));
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8452, _mut8453, _mut8454, _mut8455, _mut8456); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228");
            if (ROR_equals(w, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8337, _mut8338, _mut8339, _mut8340, _mut8341)) {
                return;
            }
            xi = x[i];
            if (ROR_equals(xi, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8342, _mut8343, _mut8344, _mut8345, _mut8346)) {
                nextr += AOR_minus(AOR_minus(nvars, i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8347, _mut8348, _mut8349, _mut8350), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8351, _mut8352, _mut8353, _mut8354);
                continue;
            }
            di = d[i];
            wxi = AOR_multiply(w, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8355, _mut8356, _mut8357, _mut8358);
            _w = w;
            if (ROR_not_equals(di, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8359, _mut8360, _mut8361, _mut8362, _mut8363)) {
                dpi = smartAdd(di, AOR_multiply(wxi, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8368, _mut8369, _mut8370, _mut8371));
                final double tmp = AOR_divide(AOR_multiply(wxi, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8372, _mut8373, _mut8374, _mut8375), di, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8376, _mut8377, _mut8378, _mut8379);
                if (ROR_greater(FastMath.abs(tmp), Precision.EPSILON, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8380, _mut8381, _mut8382, _mut8383, _mut8384)) {
                    w = AOR_divide((AOR_multiply(di, w, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8385, _mut8386, _mut8387, _mut8388)), dpi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8389, _mut8390, _mut8391, _mut8392);
                }
            } else {
                dpi = AOR_multiply(wxi, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8364, _mut8365, _mut8366, _mut8367);
                w = 0.0;
            }
            d[i] = dpi;
            for (int k = i + 1; ROR_less(k, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8422, _mut8423, _mut8424, _mut8425, _mut8426); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228");
                xk = x[k];
                x[k] = smartAdd(xk, AOR_multiply(-xi, r[nextr], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8393, _mut8394, _mut8395, _mut8396));
                if (ROR_not_equals(di, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8397, _mut8398, _mut8399, _mut8400, _mut8401)) {
                    r[nextr] = AOR_divide(smartAdd(AOR_multiply(di, r[nextr], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8406, _mut8407, _mut8408, _mut8409), AOR_multiply((AOR_multiply(_w, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8410, _mut8411, _mut8412, _mut8413)), xk, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8414, _mut8415, _mut8416, _mut8417)), dpi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8418, _mut8419, _mut8420, _mut8421);
                } else {
                    r[nextr] = AOR_divide(xk, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8402, _mut8403, _mut8404, _mut8405);
                }
                ++nextr;
            }
            xk = y;
            y = smartAdd(xk, AOR_multiply(-xi, rhs[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8427, _mut8428, _mut8429, _mut8430));
            if (ROR_not_equals(di, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8431, _mut8432, _mut8433, _mut8434, _mut8435)) {
                rhs[i] = AOR_divide(smartAdd(AOR_multiply(di, rhs[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8440, _mut8441, _mut8442, _mut8443), AOR_multiply(wxi, xk, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8444, _mut8445, _mut8446, _mut8447)), dpi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8448, _mut8449, _mut8450, _mut8451);
            } else {
                rhs[i] = AOR_divide(xk, xi, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8436, _mut8437, _mut8438, _mut8439);
            }
        }
        sserr = smartAdd(sserr, AOR_multiply(AOR_multiply(w, y, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8457, _mut8458, _mut8459, _mut8460), y, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.include_228", _mut8461, _mut8462, _mut8463, _mut8464));
    }

    /**
     * Adds to number a and b such that the contamination due to
     * numerical smallness of one addend does not corrupt the sum.
     * @param a - an addend
     * @param b - an addend
     * @return the sum of the a and b
     */
    private double smartAdd(double a, double b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293");
        final double _a = FastMath.abs(a);
        final double _b = FastMath.abs(b);
        if (ROR_greater(_a, _b, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8465, _mut8466, _mut8467, _mut8468, _mut8469)) {
            final double eps = AOR_multiply(_a, Precision.EPSILON, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8483, _mut8484, _mut8485, _mut8486);
            if (ROR_greater(_b, eps, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8487, _mut8488, _mut8489, _mut8490, _mut8491)) {
                return AOR_plus(a, b, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8492, _mut8493, _mut8494, _mut8495);
            }
            return a;
        } else {
            final double eps = AOR_multiply(_b, Precision.EPSILON, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8470, _mut8471, _mut8472, _mut8473);
            if (ROR_greater(_a, eps, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8474, _mut8475, _mut8476, _mut8477, _mut8478)) {
                return AOR_plus(a, b, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.smartAdd_293", _mut8479, _mut8480, _mut8481, _mut8482);
            }
            return b;
        }
    }

    /**
     * As the name suggests,  clear wipes the internals and reorders everything in the
     * canonical order.
     */
    public void clear() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.clear_315");
        Arrays.fill(this.d, 0.0);
        Arrays.fill(this.rhs, 0.0);
        Arrays.fill(this.r, 0.0);
        Arrays.fill(this.tol, 0.0);
        Arrays.fill(this.rss, 0.0);
        Arrays.fill(this.work_tolset, 0.0);
        Arrays.fill(this.work_sing, 0.0);
        Arrays.fill(this.x_sing, 0.0);
        Arrays.fill(this.lindep, false);
        for (int i = 0; ROR_less(i, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.clear_315", _mut8496, _mut8497, _mut8498, _mut8499, _mut8500); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.clear_315");
            this.vorder[i] = i;
        }
        this.nobs = 0;
        this.sserr = 0.0;
        this.sumy = 0.0;
        this.sumsqy = 0.0;
        this.rss_set = false;
        this.tol_set = false;
    }

    /**
     * This sets up tolerances for singularity testing.
     */
    private void tolset() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339");
        int pos;
        double total;
        final double eps = this.epsilon;
        for (int i = 0; ROR_less(i, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8501, _mut8502, _mut8503, _mut8504, _mut8505); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339");
            this.work_tolset[i] = FastMath.sqrt(d[i]);
        }
        tol[0] = AOR_multiply(eps, this.work_tolset[0], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8506, _mut8507, _mut8508, _mut8509);
        for (int col = 1; ROR_less(col, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8535, _mut8536, _mut8537, _mut8538, _mut8539); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339");
            pos = AOR_minus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8510, _mut8511, _mut8512, _mut8513);
            total = work_tolset[col];
            for (int row = 0; ROR_less(row, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8526, _mut8527, _mut8528, _mut8529, _mut8530); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339");
                total += AOR_multiply(FastMath.abs(r[pos]), work_tolset[row], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8514, _mut8515, _mut8516, _mut8517);
                pos += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8518, _mut8519, _mut8520, _mut8521), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8522, _mut8523, _mut8524, _mut8525);
            }
            tol[col] = AOR_multiply(eps, total, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.tolset_339", _mut8531, _mut8532, _mut8533, _mut8534);
        }
        tol_set = true;
    }

    /**
     * The regcf method conducts the linear regression and extracts the
     * parameter vector. Notice that the algorithm can do subset regression
     * with no alteration.
     *
     * @param nreq how many of the regressors to include (either in canonical
     * order, or in the current reordered state)
     * @return an array with the estimated slope coefficients
     * @throws ModelSpecificationException if {@code nreq} is less than 1
     * or greater than the number of independent variables
     */
    private double[] regcf(int nreq) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370");
        int nextr;
        if (ROR_less(nreq, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8540, _mut8541, _mut8542, _mut8543, _mut8544)) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS);
        }
        if (ROR_greater(nreq, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8545, _mut8546, _mut8547, _mut8548, _mut8549)) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, nreq, this.nvars);
        }
        if (!this.tol_set) {
            tolset();
        }
        final double[] ret = new double[nreq];
        boolean rankProblem = false;
        for (int i = nreq - 1; ROR_greater(i, -1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8584, _mut8585, _mut8586, _mut8587, _mut8588); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370");
            if (ROR_less(FastMath.sqrt(d[i]), tol[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8550, _mut8551, _mut8552, _mut8553, _mut8554)) {
                ret[i] = 0.0;
                d[i] = 0.0;
                rankProblem = true;
            } else {
                ret[i] = rhs[i];
                nextr = AOR_divide(AOR_multiply(i, (AOR_minus(AOR_minus(AOR_plus(nvars, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8555, _mut8556, _mut8557, _mut8558), i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8559, _mut8560, _mut8561, _mut8562), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8563, _mut8564, _mut8565, _mut8566)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8567, _mut8568, _mut8569, _mut8570), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8571, _mut8572, _mut8573, _mut8574);
                for (int j = i + 1; ROR_less(j, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8579, _mut8580, _mut8581, _mut8582, _mut8583); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370");
                    ret[i] = smartAdd(ret[i], AOR_multiply(-r[nextr], ret[j], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8575, _mut8576, _mut8577, _mut8578));
                    ++nextr;
                }
            }
        }
        if (rankProblem) {
            for (int i = 0; ROR_less(i, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370", _mut8589, _mut8590, _mut8591, _mut8592, _mut8593); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regcf_370");
                if (this.lindep[i]) {
                    ret[i] = Double.NaN;
                }
            }
        }
        return ret;
    }

    /**
     * The method which checks for singularities and then eliminates the offending
     * columns.
     */
    private void singcheck() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412");
        int pos;
        for (int i = 0; ROR_less(i, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8594, _mut8595, _mut8596, _mut8597, _mut8598); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412");
            work_sing[i] = FastMath.sqrt(d[i]);
        }
        for (int col = 0; ROR_less(col, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8676, _mut8677, _mut8678, _mut8679, _mut8680); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412");
            // multiplier
            final double temp = tol[col];
            pos = AOR_minus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8599, _mut8600, _mut8601, _mut8602);
            for (int row = 0; ROR_less(row, AOR_minus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8620, _mut8621, _mut8622, _mut8623), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8624, _mut8625, _mut8626, _mut8627, _mut8628); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412");
                if (ROR_less(AOR_multiply(FastMath.abs(r[pos]), work_sing[row], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8603, _mut8604, _mut8605, _mut8606), temp, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8607, _mut8608, _mut8609, _mut8610, _mut8611)) {
                    r[pos] = 0.0;
                }
                pos += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8612, _mut8613, _mut8614, _mut8615), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8616, _mut8617, _mut8618, _mut8619);
            }
            // the lower rows of the orthogonalization.
            lindep[col] = false;
            if (ROR_less(work_sing[col], temp, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8629, _mut8630, _mut8631, _mut8632, _mut8633)) {
                lindep[col] = true;
                if (ROR_less(col, AOR_minus(nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8634, _mut8635, _mut8636, _mut8637), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8638, _mut8639, _mut8640, _mut8641, _mut8642)) {
                    Arrays.fill(x_sing, 0.0);
                    int _pi = AOR_divide(AOR_multiply(col, (AOR_minus(AOR_minus(AOR_plus(nvars, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8651, _mut8652, _mut8653, _mut8654), col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8655, _mut8656, _mut8657, _mut8658), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8659, _mut8660, _mut8661, _mut8662)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8663, _mut8664, _mut8665, _mut8666), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8667, _mut8668, _mut8669, _mut8670);
                    for (int _xi = col + 1; ROR_less(_xi, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8671, _mut8672, _mut8673, _mut8674, _mut8675); _xi++, _pi++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412");
                        x_sing[_xi] = r[_pi];
                        r[_pi] = 0.0;
                    }
                    final double y = rhs[col];
                    final double weight = d[col];
                    d[col] = 0.0;
                    rhs[col] = 0.0;
                    this.include(x_sing, weight, y);
                } else {
                    sserr += AOR_multiply(AOR_multiply(d[col], rhs[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8643, _mut8644, _mut8645, _mut8646), rhs[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.singcheck_412", _mut8647, _mut8648, _mut8649, _mut8650);
                }
            }
        }
    }

    /**
     * Calculates the sum of squared errors for the full regression
     * and all subsets in the following manner: <pre>
     * rss[] ={
     * ResidualSumOfSquares_allNvars,
     * ResidualSumOfSquares_FirstNvars-1,
     * ResidualSumOfSquares_FirstNvars-2,
     * ..., ResidualSumOfSquares_FirstVariable} </pre>
     */
    private void ss() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463");
        double total = sserr;
        rss[AOR_minus(nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463", _mut8681, _mut8682, _mut8683, _mut8684)] = sserr;
        for (int i = nvars - 1; ROR_greater(i, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463", _mut8697, _mut8698, _mut8699, _mut8700, _mut8701); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463");
            total += AOR_multiply(AOR_multiply(d[i], rhs[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463", _mut8685, _mut8686, _mut8687, _mut8688), rhs[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463", _mut8689, _mut8690, _mut8691, _mut8692);
            rss[AOR_minus(i, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.ss_463", _mut8693, _mut8694, _mut8695, _mut8696)] = total;
        }
        rss_set = true;
    }

    /**
     * Calculates the cov matrix assuming only the first nreq variables are
     * included in the calculation. The returned array contains a symmetric
     * matrix stored in lower triangular form. The matrix will have
     * ( nreq + 1 ) * nreq / 2 elements. For illustration <pre>
     * cov =
     * {
     *  cov_00,
     *  cov_10, cov_11,
     *  cov_20, cov_21, cov22,
     *  ...
     * } </pre>
     *
     * @param nreq how many of the regressors to include (either in canonical
     * order, or in the current reordered state)
     * @return an array with the variance covariance of the included
     * regressors in lower triangular form
     */
    private double[] cov(int nreq) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491");
        if (ROR_less_equals(this.nobs, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8702, _mut8703, _mut8704, _mut8705, _mut8706)) {
            return null;
        }
        double rnk = 0.0;
        for (int i = 0; ROR_less(i, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8707, _mut8708, _mut8709, _mut8710, _mut8711); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491");
            if (!this.lindep[i]) {
                rnk += 1.0;
            }
        }
        final double var = AOR_divide(rss[AOR_minus(nreq, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8712, _mut8713, _mut8714, _mut8715)], (AOR_minus(nobs, rnk, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8716, _mut8717, _mut8718, _mut8719)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8720, _mut8721, _mut8722, _mut8723);
        final double[] rinv = new double[AOR_divide(AOR_multiply(nreq, (AOR_minus(nreq, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8724, _mut8725, _mut8726, _mut8727)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8728, _mut8729, _mut8730, _mut8731), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8732, _mut8733, _mut8734, _mut8735)];
        inverse(rinv, nreq);
        final double[] covmat = new double[AOR_divide(AOR_multiply(nreq, (AOR_plus(nreq, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8736, _mut8737, _mut8738, _mut8739)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8740, _mut8741, _mut8742, _mut8743), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8744, _mut8745, _mut8746, _mut8747)];
        Arrays.fill(covmat, Double.NaN);
        int pos2;
        int pos1;
        int start = 0;
        double total = 0;
        for (int row = 0; ROR_less(row, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8827, _mut8828, _mut8829, _mut8830, _mut8831); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491");
            pos2 = start;
            if (!this.lindep[row]) {
                for (int col = row; ROR_less(col, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8814, _mut8815, _mut8816, _mut8817, _mut8818); col++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491");
                    if (!this.lindep[col]) {
                        pos1 = AOR_minus(AOR_plus(start, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8756, _mut8757, _mut8758, _mut8759), row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8760, _mut8761, _mut8762, _mut8763);
                        if (ROR_equals(row, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8764, _mut8765, _mut8766, _mut8767, _mut8768)) {
                            total = AOR_divide(1.0, d[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8777, _mut8778, _mut8779, _mut8780);
                        } else {
                            total = AOR_divide(rinv[AOR_minus(pos1, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8769, _mut8770, _mut8771, _mut8772)], d[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8773, _mut8774, _mut8775, _mut8776);
                        }
                        for (int k = col + 1; ROR_less(k, nreq, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8789, _mut8790, _mut8791, _mut8792, _mut8793); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491");
                            if (!this.lindep[k]) {
                                total += AOR_divide(AOR_multiply(rinv[pos1], rinv[pos2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8781, _mut8782, _mut8783, _mut8784), d[k], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8785, _mut8786, _mut8787, _mut8788);
                            }
                            ++pos1;
                            ++pos2;
                        }
                        covmat[AOR_plus(AOR_divide(AOR_multiply((AOR_plus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8794, _mut8795, _mut8796, _mut8797)), col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8798, _mut8799, _mut8800, _mut8801), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8802, _mut8803, _mut8804, _mut8805), row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8806, _mut8807, _mut8808, _mut8809)] = AOR_multiply(total, var, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8810, _mut8811, _mut8812, _mut8813);
                    } else {
                        pos2 += AOR_minus(AOR_minus(nreq, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8748, _mut8749, _mut8750, _mut8751), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8752, _mut8753, _mut8754, _mut8755);
                    }
                }
            }
            start += AOR_minus(AOR_minus(nreq, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8819, _mut8820, _mut8821, _mut8822), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.cov_491", _mut8823, _mut8824, _mut8825, _mut8826);
        }
        return covmat;
    }

    /**
     * This internal method calculates the inverse of the upper-triangular portion
     * of the R matrix.
     * @param rinv  the storage for the inverse of r
     * @param nreq how many of the regressors to include (either in canonical
     * order, or in the current reordered state)
     */
    private void inverse(double[] rinv, int nreq) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546");
        int pos = AOR_minus(AOR_divide(AOR_multiply(nreq, (AOR_minus(nreq, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8832, _mut8833, _mut8834, _mut8835)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8836, _mut8837, _mut8838, _mut8839), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8840, _mut8841, _mut8842, _mut8843), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8844, _mut8845, _mut8846, _mut8847);
        int pos1 = -1;
        int pos2 = -1;
        double total = 0.0;
        Arrays.fill(rinv, Double.NaN);
        for (int row = nreq - 1; ROR_greater(row, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8902, _mut8903, _mut8904, _mut8905, _mut8906); --row) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546");
            if (!this.lindep[row]) {
                final int start = AOR_divide(AOR_multiply((AOR_minus(row, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8852, _mut8853, _mut8854, _mut8855)), (AOR_minus(AOR_plus(nvars, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8856, _mut8857, _mut8858, _mut8859), row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8860, _mut8861, _mut8862, _mut8863)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8864, _mut8865, _mut8866, _mut8867), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8868, _mut8869, _mut8870, _mut8871);
                for (int col = nreq; ROR_greater(col, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8897, _mut8898, _mut8899, _mut8900, _mut8901); --col) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546");
                    pos1 = start;
                    pos2 = pos;
                    total = 0.0;
                    for (int k = row; ROR_less(k, AOR_minus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8884, _mut8885, _mut8886, _mut8887), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8888, _mut8889, _mut8890, _mut8891, _mut8892); k++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546");
                        pos2 += AOR_minus(AOR_minus(nreq, k, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8872, _mut8873, _mut8874, _mut8875), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8876, _mut8877, _mut8878, _mut8879);
                        if (!this.lindep[k]) {
                            total += AOR_multiply(-r[pos1], rinv[pos2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8880, _mut8881, _mut8882, _mut8883);
                        }
                        ++pos1;
                    }
                    rinv[pos] = AOR_minus(total, r[pos1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8893, _mut8894, _mut8895, _mut8896);
                    --pos;
                }
            } else {
                pos -= AOR_minus(nreq, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.inverse_546", _mut8848, _mut8849, _mut8850, _mut8851);
            }
        }
    }

    /**
     * In the original algorithm only the partial correlations of the regressors
     * is returned to the user. In this implementation, we have <pre>
     * corr =
     * {
     *   corrxx - lower triangular
     *   corrxy - bottom row of the matrix
     * }
     * Replaces subroutines PCORR and COR of:
     * ALGORITHM AS274  APPL. STATIST. (1992) VOL.41, NO. 2 </pre>
     *
     * <p>Calculate partial correlations after the variables in rows
     * 1, 2, ..., IN have been forced into the regression.
     * If IN = 1, and the first row of R represents a constant in the
     * model, then the usual simple correlations are returned.</p>
     *
     * <p>If IN = 0, the value returned in array CORMAT for the correlation
     * of variables Xi & Xj is: <pre>
     * sum ( Xi.Xj ) / Sqrt ( sum (Xi^2) . sum (Xj^2) )</pre></p>
     *
     * <p>On return, array CORMAT contains the upper triangle of the matrix of
     * partial correlations stored by rows, excluding the 1's on the diagonal.
     * e.g. if IN = 2, the consecutive elements returned are:
     * (3,4) (3,5) ... (3,ncol), (4,5) (4,6) ... (4,ncol), etc.
     * Array YCORR stores the partial correlations with the Y-variable
     * starting with YCORR(IN+1) = partial correlation with the variable in
     * position (IN+1). </p>
     *
     * @param in how many of the regressors to include (either in canonical
     * order, or in the current reordered state)
     * @return an array with the partial correlations of the remainder of
     * regressors with each other and the regressand, in lower triangular form
     */
    public double[] getPartialCorrelations(int in) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
        final double[] output = new double[AOR_divide(AOR_multiply((AOR_plus(AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8907, _mut8908, _mut8909, _mut8910), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8911, _mut8912, _mut8913, _mut8914)), (AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8915, _mut8916, _mut8917, _mut8918)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8919, _mut8920, _mut8921, _mut8922), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8923, _mut8924, _mut8925, _mut8926)];
        int pos;
        int pos1;
        int pos2;
        final int rms_off = -in;
        final int wrk_off = -(AOR_plus(in, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8927, _mut8928, _mut8929, _mut8930));
        final double[] rms = new double[AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8931, _mut8932, _mut8933, _mut8934)];
        final double[] work = new double[AOR_minus(AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8935, _mut8936, _mut8937, _mut8938), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8939, _mut8940, _mut8941, _mut8942)];
        double sumxx;
        double sumxy;
        double sumyy;
        final int offXX = AOR_divide(AOR_multiply((AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8943, _mut8944, _mut8945, _mut8946)), (AOR_minus(AOR_minus(nvars, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8947, _mut8948, _mut8949, _mut8950), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8951, _mut8952, _mut8953, _mut8954)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8955, _mut8956, _mut8957, _mut8958), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8959, _mut8960, _mut8961, _mut8962);
        if ((_mut8973 ? (ROR_less(in, -1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8963, _mut8964, _mut8965, _mut8966, _mut8967) && ROR_greater_equals(in, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8968, _mut8969, _mut8970, _mut8971, _mut8972)) : (ROR_less(in, -1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8963, _mut8964, _mut8965, _mut8966, _mut8967) || ROR_greater_equals(in, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8968, _mut8969, _mut8970, _mut8971, _mut8972)))) {
            return null;
        }
        final int nvm = AOR_minus(nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8974, _mut8975, _mut8976, _mut8977);
        final int base_pos = AOR_minus(r.length, AOR_divide(AOR_multiply((AOR_minus(nvm, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8978, _mut8979, _mut8980, _mut8981)), (AOR_plus(AOR_minus(nvm, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8982, _mut8983, _mut8984, _mut8985), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8986, _mut8987, _mut8988, _mut8989)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8990, _mut8991, _mut8992, _mut8993), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8994, _mut8995, _mut8996, _mut8997), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut8998, _mut8999, _mut9000, _mut9001);
        if (ROR_greater(d[in], 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9002, _mut9003, _mut9004, _mut9005, _mut9006)) {
            rms[AOR_plus(in, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9007, _mut9008, _mut9009, _mut9010)] = AOR_divide(1.0, FastMath.sqrt(d[in]), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9011, _mut9012, _mut9013, _mut9014);
        }
        for (int col = in + 1; ROR_less(col, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9065, _mut9066, _mut9067, _mut9068, _mut9069); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
            pos = AOR_minus(AOR_minus(AOR_plus(base_pos, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9015, _mut9016, _mut9017, _mut9018), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9019, _mut9020, _mut9021, _mut9022), in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9023, _mut9024, _mut9025, _mut9026);
            sumxx = d[col];
            for (int row = in; ROR_less(row, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9043, _mut9044, _mut9045, _mut9046, _mut9047); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
                sumxx += AOR_multiply(AOR_multiply(d[row], r[pos], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9027, _mut9028, _mut9029, _mut9030), r[pos], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9031, _mut9032, _mut9033, _mut9034);
                pos += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9035, _mut9036, _mut9037, _mut9038), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9039, _mut9040, _mut9041, _mut9042);
            }
            if (ROR_greater(sumxx, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9048, _mut9049, _mut9050, _mut9051, _mut9052)) {
                rms[AOR_plus(col, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9057, _mut9058, _mut9059, _mut9060)] = AOR_divide(1.0, FastMath.sqrt(sumxx), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9061, _mut9062, _mut9063, _mut9064);
            } else {
                rms[AOR_plus(col, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9053, _mut9054, _mut9055, _mut9056)] = 0.0;
            }
        }
        sumyy = sserr;
        for (int row = in; ROR_less(row, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9078, _mut9079, _mut9080, _mut9081, _mut9082); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
            sumyy += AOR_multiply(AOR_multiply(d[row], rhs[row], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9070, _mut9071, _mut9072, _mut9073), rhs[row], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9074, _mut9075, _mut9076, _mut9077);
        }
        if (ROR_greater(sumyy, 0.0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9083, _mut9084, _mut9085, _mut9086, _mut9087)) {
            sumyy = AOR_divide(1.0, FastMath.sqrt(sumyy), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9088, _mut9089, _mut9090, _mut9091);
        }
        pos = 0;
        for (int col1 = in; ROR_less(col1, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9235, _mut9236, _mut9237, _mut9238, _mut9239); col1++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
            sumxy = 0.0;
            Arrays.fill(work, 0.0);
            pos1 = AOR_minus(AOR_minus(AOR_plus(base_pos, col1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9092, _mut9093, _mut9094, _mut9095), in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9096, _mut9097, _mut9098, _mut9099), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9100, _mut9101, _mut9102, _mut9103);
            for (int row = in; ROR_less(row, col1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9141, _mut9142, _mut9143, _mut9144, _mut9145); row++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
                pos2 = AOR_plus(pos1, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9104, _mut9105, _mut9106, _mut9107);
                for (int col2 = col1 + 1; ROR_less(col2, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9120, _mut9121, _mut9122, _mut9123, _mut9124); col2++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
                    work[AOR_plus(col2, wrk_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9108, _mut9109, _mut9110, _mut9111)] += AOR_multiply(AOR_multiply(d[row], r[pos1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9112, _mut9113, _mut9114, _mut9115), r[pos2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9116, _mut9117, _mut9118, _mut9119);
                    pos2++;
                }
                sumxy += AOR_multiply(AOR_multiply(d[row], r[pos1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9125, _mut9126, _mut9127, _mut9128), rhs[row], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9129, _mut9130, _mut9131, _mut9132);
                pos1 += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9133, _mut9134, _mut9135, _mut9136), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9137, _mut9138, _mut9139, _mut9140);
            }
            pos2 = AOR_plus(pos1, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9146, _mut9147, _mut9148, _mut9149);
            for (int col2 = col1 + 1; ROR_less(col2, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9206, _mut9207, _mut9208, _mut9209, _mut9210); col2++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608");
                work[AOR_plus(col2, wrk_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9150, _mut9151, _mut9152, _mut9153)] += AOR_multiply(d[col1], r[pos2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9154, _mut9155, _mut9156, _mut9157);
                ++pos2;
                output[AOR_minus(AOR_plus(AOR_divide(AOR_multiply((AOR_minus(AOR_minus(col2, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9158, _mut9159, _mut9160, _mut9161), in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9162, _mut9163, _mut9164, _mut9165)), (AOR_minus(col2, in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9166, _mut9167, _mut9168, _mut9169)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9170, _mut9171, _mut9172, _mut9173), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9174, _mut9175, _mut9176, _mut9177), col1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9178, _mut9179, _mut9180, _mut9181), in, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9182, _mut9183, _mut9184, _mut9185)] = AOR_multiply(AOR_multiply(work[AOR_plus(col2, wrk_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9186, _mut9187, _mut9188, _mut9189)], rms[AOR_plus(col1, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9190, _mut9191, _mut9192, _mut9193)], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9194, _mut9195, _mut9196, _mut9197), rms[AOR_plus(col2, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9198, _mut9199, _mut9200, _mut9201)], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9202, _mut9203, _mut9204, _mut9205);
                ++pos;
            }
            sumxy += AOR_multiply(d[col1], rhs[col1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9211, _mut9212, _mut9213, _mut9214);
            output[AOR_plus(AOR_plus(col1, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9215, _mut9216, _mut9217, _mut9218), offXX, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9219, _mut9220, _mut9221, _mut9222)] = AOR_multiply(AOR_multiply(sumxy, rms[AOR_plus(col1, rms_off, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9223, _mut9224, _mut9225, _mut9226)], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9227, _mut9228, _mut9229, _mut9230), sumyy, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getPartialCorrelations_608", _mut9231, _mut9232, _mut9233, _mut9234);
        }
        return output;
    }

    /**
     * ALGORITHM AS274 APPL. STATIST. (1992) VOL.41, NO. 2.
     * Move variable from position FROM to position TO in an
     * orthogonal reduction produced by AS75.1.
     *
     * @param from initial position
     * @param to destination
     */
    private void vmove(int from, int to) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
        double d1;
        double d2;
        double X;
        double d1new;
        double d2new;
        double cbar;
        double sbar;
        double Y;
        int first;
        int inc;
        int m1;
        int m2;
        int mp1;
        int pos;
        boolean bSkipTo40 = false;
        if (ROR_equals(from, to, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9240, _mut9241, _mut9242, _mut9243, _mut9244)) {
            return;
        }
        if (!this.rss_set) {
            ss();
        }
        int count = 0;
        if (ROR_less(from, to, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9245, _mut9246, _mut9247, _mut9248, _mut9249)) {
            first = from;
            inc = 1;
            count = AOR_minus(to, from, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9258, _mut9259, _mut9260, _mut9261);
        } else {
            first = AOR_minus(from, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9250, _mut9251, _mut9252, _mut9253);
            inc = -1;
            count = AOR_minus(from, to, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9254, _mut9255, _mut9256, _mut9257);
        }
        int m = first;
        int idx = 0;
        while (ROR_less(idx, count, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9479, _mut9480, _mut9481, _mut9482, _mut9483)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
            m1 = AOR_divide(AOR_multiply(m, (AOR_minus(AOR_minus(AOR_plus(nvars, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9262, _mut9263, _mut9264, _mut9265), m, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9266, _mut9267, _mut9268, _mut9269), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9270, _mut9271, _mut9272, _mut9273)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9274, _mut9275, _mut9276, _mut9277), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9278, _mut9279, _mut9280, _mut9281);
            m2 = AOR_minus(AOR_minus(AOR_plus(m1, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9282, _mut9283, _mut9284, _mut9285), m, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9286, _mut9287, _mut9288, _mut9289), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9290, _mut9291, _mut9292, _mut9293);
            mp1 = AOR_plus(m, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9294, _mut9295, _mut9296, _mut9297);
            d1 = d[m];
            d2 = d[mp1];
            // Special cases.
            if ((_mut9308 ? (ROR_greater(d1, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9298, _mut9299, _mut9300, _mut9301, _mut9302) && ROR_greater(d2, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9303, _mut9304, _mut9305, _mut9306, _mut9307)) : (ROR_greater(d1, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9298, _mut9299, _mut9300, _mut9301, _mut9302) || ROR_greater(d2, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9303, _mut9304, _mut9305, _mut9306, _mut9307)))) {
                X = r[m1];
                if (ROR_less(AOR_multiply(FastMath.abs(X), FastMath.sqrt(d1), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9309, _mut9310, _mut9311, _mut9312), tol[mp1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9313, _mut9314, _mut9315, _mut9316, _mut9317)) {
                    X = 0.0;
                }
                if ((_mut9328 ? (ROR_less(d1, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9318, _mut9319, _mut9320, _mut9321, _mut9322) && ROR_less(FastMath.abs(X), this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9323, _mut9324, _mut9325, _mut9326, _mut9327)) : (ROR_less(d1, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9318, _mut9319, _mut9320, _mut9321, _mut9322) || ROR_less(FastMath.abs(X), this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9323, _mut9324, _mut9325, _mut9326, _mut9327)))) {
                    d[m] = d2;
                    d[mp1] = d1;
                    r[m1] = 0.0;
                    for (int col = m + 2; ROR_less(col, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9363, _mut9364, _mut9365, _mut9366, _mut9367); col++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
                        ++m1;
                        X = r[m1];
                        r[m1] = r[m2];
                        r[m2] = X;
                        ++m2;
                    }
                    X = rhs[m];
                    rhs[m] = rhs[mp1];
                    rhs[mp1] = X;
                    bSkipTo40 = true;
                } else if (ROR_less(d2, this.epsilon, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9329, _mut9330, _mut9331, _mut9332, _mut9333)) {
                    d[m] = AOR_multiply(AOR_multiply(d1, X, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9334, _mut9335, _mut9336, _mut9337), X, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9338, _mut9339, _mut9340, _mut9341);
                    r[m1] = AOR_divide(1.0, X, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9342, _mut9343, _mut9344, _mut9345);
                    for (int _i = m1 + 1; ROR_less(_i, AOR_minus(AOR_minus(AOR_plus(m1, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9346, _mut9347, _mut9348, _mut9349), m, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9350, _mut9351, _mut9352, _mut9353), 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9354, _mut9355, _mut9356, _mut9357), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9358, _mut9359, _mut9360, _mut9361, _mut9362); _i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
                        r[_i] /= X;
                    }
                    rhs[m] /= X;
                    bSkipTo40 = true;
                }
                if (!bSkipTo40) {
                    d1new = AOR_plus(d2, AOR_multiply(AOR_multiply(d1, X, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9368, _mut9369, _mut9370, _mut9371), X, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9372, _mut9373, _mut9374, _mut9375), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9376, _mut9377, _mut9378, _mut9379);
                    cbar = AOR_divide(d2, d1new, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9380, _mut9381, _mut9382, _mut9383);
                    sbar = AOR_divide(AOR_multiply(X, d1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9384, _mut9385, _mut9386, _mut9387), d1new, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9388, _mut9389, _mut9390, _mut9391);
                    d2new = AOR_multiply(d1, cbar, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9392, _mut9393, _mut9394, _mut9395);
                    d[m] = d1new;
                    d[mp1] = d2new;
                    r[m1] = sbar;
                    for (int col = m + 2; ROR_less(col, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9416, _mut9417, _mut9418, _mut9419, _mut9420); col++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
                        ++m1;
                        Y = r[m1];
                        r[m1] = AOR_plus(AOR_multiply(cbar, r[m2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9396, _mut9397, _mut9398, _mut9399), AOR_multiply(sbar, Y, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9400, _mut9401, _mut9402, _mut9403), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9404, _mut9405, _mut9406, _mut9407);
                        r[m2] = AOR_minus(Y, AOR_multiply(X, r[m2], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9408, _mut9409, _mut9410, _mut9411), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9412, _mut9413, _mut9414, _mut9415);
                        ++m2;
                    }
                    Y = rhs[m];
                    rhs[m] = AOR_plus(AOR_multiply(cbar, rhs[mp1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9421, _mut9422, _mut9423, _mut9424), AOR_multiply(sbar, Y, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9425, _mut9426, _mut9427, _mut9428), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9429, _mut9430, _mut9431, _mut9432);
                    rhs[mp1] = AOR_minus(Y, AOR_multiply(X, rhs[mp1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9433, _mut9434, _mut9435, _mut9436), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9437, _mut9438, _mut9439, _mut9440);
                }
            }
            if (ROR_greater(m, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9441, _mut9442, _mut9443, _mut9444, _mut9445)) {
                pos = m;
                for (int row = 0; ROR_less(row, m, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9462, _mut9463, _mut9464, _mut9465, _mut9466); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686");
                    X = r[pos];
                    r[pos] = r[AOR_minus(pos, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9446, _mut9447, _mut9448, _mut9449)];
                    r[AOR_minus(pos, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9450, _mut9451, _mut9452, _mut9453)] = X;
                    pos += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9454, _mut9455, _mut9456, _mut9457), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9458, _mut9459, _mut9460, _mut9461);
                }
            }
            // the vector of residual sums of squares (RSS).
            m1 = vorder[m];
            vorder[m] = vorder[mp1];
            vorder[mp1] = m1;
            X = tol[m];
            tol[m] = tol[mp1];
            tol[mp1] = X;
            rss[m] = AOR_plus(rss[mp1], AOR_multiply(AOR_multiply(d[mp1], rhs[mp1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9467, _mut9468, _mut9469, _mut9470), rhs[mp1], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9471, _mut9472, _mut9473, _mut9474), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.vmove_686", _mut9475, _mut9476, _mut9477, _mut9478);
            m += inc;
            ++idx;
        }
    }

    /**
     * ALGORITHM AS274  APPL. STATIST. (1992) VOL.41, NO. 2
     *
     * <p> Re-order the variables in an orthogonal reduction produced by
     * AS75.1 so that the N variables in LIST start at position POS1,
     * though will not necessarily be in the same order as in LIST.
     * Any variables in VORDER before position POS1 are not moved.
     * Auxiliary routine called: VMOVE. </p>
     *
     * <p>This internal method reorders the regressors.</p>
     *
     * @param list the regressors to move
     * @param pos1 where the list will be placed
     * @return -1 error, 0 everything ok
     */
    private int reorderRegressors(int[] list, int pos1) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819");
        int next;
        int i;
        int l;
        if ((_mut9502 ? (ROR_less(list.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9484, _mut9485, _mut9486, _mut9487, _mut9488) && ROR_greater(list.length, AOR_minus(AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9489, _mut9490, _mut9491, _mut9492), pos1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9493, _mut9494, _mut9495, _mut9496), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9497, _mut9498, _mut9499, _mut9500, _mut9501)) : (ROR_less(list.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9484, _mut9485, _mut9486, _mut9487, _mut9488) || ROR_greater(list.length, AOR_minus(AOR_plus(nvars, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9489, _mut9490, _mut9491, _mut9492), pos1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9493, _mut9494, _mut9495, _mut9496), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9497, _mut9498, _mut9499, _mut9500, _mut9501)))) {
            return -1;
        }
        next = pos1;
        i = pos1;
        while (ROR_less(i, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9528, _mut9529, _mut9530, _mut9531, _mut9532)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819");
            l = vorder[i];
            for (int j = 0; ROR_less(j, list.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9523, _mut9524, _mut9525, _mut9526, _mut9527); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819");
                if ((_mut9513 ? (ROR_equals(l, list[j], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9503, _mut9504, _mut9505, _mut9506, _mut9507) || ROR_greater(i, next, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9508, _mut9509, _mut9510, _mut9511, _mut9512)) : (ROR_equals(l, list[j], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9503, _mut9504, _mut9505, _mut9506, _mut9507) && ROR_greater(i, next, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9508, _mut9509, _mut9510, _mut9511, _mut9512)))) {
                    this.vmove(i, next);
                    ++next;
                    if (ROR_greater_equals(next, AOR_plus(list.length, pos1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9514, _mut9515, _mut9516, _mut9517), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.reorderRegressors_819", _mut9518, _mut9519, _mut9520, _mut9521, _mut9522)) {
                        return 0;
                    } else {
                        break;
                    }
                }
            }
            ++i;
        }
        return 0;
    }

    /**
     * Gets the diagonal of the Hat matrix also known as the leverage matrix.
     *
     * @param  row_data returns the diagonal of the hat matrix for this observation
     * @return the diagonal element of the hatmatrix
     */
    public double getDiagonalOfHatMatrix(double[] row_data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852");
        double[] wk = new double[this.nvars];
        int pos;
        double total;
        if (ROR_greater(row_data.length, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9533, _mut9534, _mut9535, _mut9536, _mut9537)) {
            return Double.NaN;
        }
        double[] xrow;
        if (this.hasIntercept) {
            xrow = new double[AOR_plus(row_data.length, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9538, _mut9539, _mut9540, _mut9541)];
            xrow[0] = 1.0;
            System.arraycopy(row_data, 0, xrow, 1, row_data.length);
        } else {
            xrow = row_data;
        }
        double hii = 0.0;
        for (int col = 0; ROR_less(col, xrow.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9576, _mut9577, _mut9578, _mut9579, _mut9580); col++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852");
            if (ROR_less(FastMath.sqrt(d[col]), tol[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9542, _mut9543, _mut9544, _mut9545, _mut9546)) {
                wk[col] = 0.0;
            } else {
                pos = AOR_minus(col, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9547, _mut9548, _mut9549, _mut9550);
                total = xrow[col];
                for (int row = 0; ROR_less(row, col, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9563, _mut9564, _mut9565, _mut9566, _mut9567); row++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852");
                    total = smartAdd(total, AOR_multiply(-wk[row], r[pos], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9551, _mut9552, _mut9553, _mut9554));
                    pos += AOR_minus(AOR_minus(nvars, row, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9555, _mut9556, _mut9557, _mut9558), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9559, _mut9560, _mut9561, _mut9562);
                }
                wk[col] = total;
                hii = smartAdd(hii, AOR_divide((AOR_multiply(total, total, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9568, _mut9569, _mut9570, _mut9571)), d[col], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.getDiagonalOfHatMatrix_852", _mut9572, _mut9573, _mut9574, _mut9575));
            }
        }
        return hii;
    }

    /**
     * Gets the order of the regressors, useful if some type of reordering
     * has been called. Calling regress with int[]{} args will trigger
     * a reordering.
     *
     * @return int[] with the current order of the regressors
     */
    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(vorder);
    }

    /**
     * Conducts a regression on the data in the model, using all regressors.
     *
     * @return RegressionResults the structure holding all regression results
     * @exception  ModelSpecificationException - thrown if number of observations is
     * less than the number of variables
     */
    public RegressionResults regress() throws ModelSpecificationException {
        return regress(this.nvars);
    }

    /**
     * Conducts a regression on the data in the model, using a subset of regressors.
     *
     * @param numberOfRegressors many of the regressors to include (either in canonical
     * order, or in the current reordered state)
     * @return RegressionResults the structure holding all regression results
     * @exception  ModelSpecificationException - thrown if number of observations is
     * less than the number of variables or number of regressors requested
     * is greater than the regressors in the model
     */
    public RegressionResults regress(int numberOfRegressors) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
        if (ROR_less_equals(this.nobs, numberOfRegressors, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9581, _mut9582, _mut9583, _mut9584, _mut9585)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, this.nobs, numberOfRegressors);
        }
        if (ROR_greater(numberOfRegressors, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9586, _mut9587, _mut9588, _mut9589, _mut9590)) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, numberOfRegressors, this.nvars);
        }
        tolset();
        singcheck();
        double[] beta = this.regcf(numberOfRegressors);
        ss();
        double[] cov = this.cov(numberOfRegressors);
        int rnk = 0;
        for (int i = 0; ROR_less(i, this.lindep.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9591, _mut9592, _mut9593, _mut9594, _mut9595); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
            if (!this.lindep[i]) {
                ++rnk;
            }
        }
        boolean needsReorder = false;
        for (int i = 0; ROR_less(i, numberOfRegressors, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9601, _mut9602, _mut9603, _mut9604, _mut9605); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
            if (ROR_not_equals(this.vorder[i], i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9596, _mut9597, _mut9598, _mut9599, _mut9600)) {
                needsReorder = true;
                break;
            }
        }
        if (!needsReorder) {
            return new RegressionResults(beta, new double[][] { cov }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        } else {
            double[] betaNew = new double[beta.length];
            double[] covNew = new double[cov.length];
            int[] newIndices = new int[beta.length];
            for (int i = 0; ROR_less(i, nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9616, _mut9617, _mut9618, _mut9619, _mut9620); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
                for (int j = 0; ROR_less(j, numberOfRegressors, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9611, _mut9612, _mut9613, _mut9614, _mut9615); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
                    if (ROR_equals(this.vorder[j], i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9606, _mut9607, _mut9608, _mut9609, _mut9610)) {
                        betaNew[i] = beta[j];
                        newIndices[i] = j;
                    }
                }
            }
            int idx1 = 0;
            int idx2;
            int _i;
            int _j;
            for (int i = 0; ROR_less(i, beta.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9663, _mut9664, _mut9665, _mut9666, _mut9667); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
                _i = newIndices[i];
                for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9658, _mut9659, _mut9660, _mut9661, _mut9662); j++, idx1++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918");
                    _j = newIndices[j];
                    if (ROR_greater(_i, _j, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9621, _mut9622, _mut9623, _mut9624, _mut9625)) {
                        idx2 = AOR_plus(AOR_divide(AOR_multiply(_i, (AOR_plus(_i, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9642, _mut9643, _mut9644, _mut9645)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9646, _mut9647, _mut9648, _mut9649), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9650, _mut9651, _mut9652, _mut9653), _j, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9654, _mut9655, _mut9656, _mut9657);
                    } else {
                        idx2 = AOR_plus(AOR_divide(AOR_multiply(_j, (AOR_plus(_j, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9626, _mut9627, _mut9628, _mut9629)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9630, _mut9631, _mut9632, _mut9633), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9634, _mut9635, _mut9636, _mut9637), _i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_918", _mut9638, _mut9639, _mut9640, _mut9641);
                    }
                    covNew[idx1] = cov[idx2];
                }
            }
            return new RegressionResults(betaNew, new double[][] { covNew }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        }
    }

    /**
     * Conducts a regression on the data in the model, using regressors in array
     * Calling this method will change the internal order of the regressors
     * and care is required in interpreting the hatmatrix.
     *
     * @param  variablesToInclude array of variables to include in regression
     * @return RegressionResults the structure holding all regression results
     * @exception  ModelSpecificationException - thrown if number of observations is
     * less than the number of variables, the number of regressors requested
     * is greater than the regressors in the model or a regressor index in
     * regressor array does not exist
     */
    public RegressionResults regress(int[] variablesToInclude) throws ModelSpecificationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
        if (ROR_greater(variablesToInclude.length, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9668, _mut9669, _mut9670, _mut9671, _mut9672)) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, variablesToInclude.length, this.nvars);
        }
        if (ROR_less_equals(this.nobs, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9673, _mut9674, _mut9675, _mut9676, _mut9677)) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, this.nobs, this.nvars);
        }
        Arrays.sort(variablesToInclude);
        int iExclude = 0;
        for (int i = 0; ROR_less(i, variablesToInclude.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9698, _mut9699, _mut9700, _mut9701, _mut9702); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
            if (ROR_greater_equals(i, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9678, _mut9679, _mut9680, _mut9681, _mut9682)) {
                throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, i, this.nvars);
            }
            if ((_mut9697 ? (ROR_greater(i, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9683, _mut9684, _mut9685, _mut9686, _mut9687) || ROR_equals(variablesToInclude[i], variablesToInclude[AOR_minus(i, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9688, _mut9689, _mut9690, _mut9691)], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9692, _mut9693, _mut9694, _mut9695, _mut9696)) : (ROR_greater(i, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9683, _mut9684, _mut9685, _mut9686, _mut9687) && ROR_equals(variablesToInclude[i], variablesToInclude[AOR_minus(i, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9688, _mut9689, _mut9690, _mut9691)], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9692, _mut9693, _mut9694, _mut9695, _mut9696)))) {
                variablesToInclude[i] = -1;
                ++iExclude;
            }
        }
        int[] series;
        if (ROR_greater(iExclude, 0, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9703, _mut9704, _mut9705, _mut9706, _mut9707)) {
            int j = 0;
            series = new int[AOR_minus(variablesToInclude.length, iExclude, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9708, _mut9709, _mut9710, _mut9711)];
            for (int i = 0; ROR_less(i, variablesToInclude.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9717, _mut9718, _mut9719, _mut9720, _mut9721); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
                if (ROR_greater(variablesToInclude[i], -1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9712, _mut9713, _mut9714, _mut9715, _mut9716)) {
                    series[j] = variablesToInclude[i];
                    ++j;
                }
            }
        } else {
            series = variablesToInclude;
        }
        reorderRegressors(series, 0);
        tolset();
        singcheck();
        double[] beta = this.regcf(series.length);
        ss();
        double[] cov = this.cov(series.length);
        int rnk = 0;
        for (int i = 0; ROR_less(i, this.lindep.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9722, _mut9723, _mut9724, _mut9725, _mut9726); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
            if (!this.lindep[i]) {
                ++rnk;
            }
        }
        boolean needsReorder = false;
        for (int i = 0; ROR_less(i, this.nvars, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9732, _mut9733, _mut9734, _mut9735, _mut9736); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
            if (ROR_not_equals(this.vorder[i], series[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9727, _mut9728, _mut9729, _mut9730, _mut9731)) {
                needsReorder = true;
                break;
            }
        }
        if (!needsReorder) {
            return new RegressionResults(beta, new double[][] { cov }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        } else {
            double[] betaNew = new double[beta.length];
            int[] newIndices = new int[beta.length];
            for (int i = 0; ROR_less(i, series.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9747, _mut9748, _mut9749, _mut9750, _mut9751); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
                for (int j = 0; ROR_less(j, this.vorder.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9742, _mut9743, _mut9744, _mut9745, _mut9746); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
                    if (ROR_equals(this.vorder[j], series[i], "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9737, _mut9738, _mut9739, _mut9740, _mut9741)) {
                        betaNew[i] = beta[j];
                        newIndices[i] = j;
                    }
                }
            }
            double[] covNew = new double[cov.length];
            int idx1 = 0;
            int idx2;
            int _i;
            int _j;
            for (int i = 0; ROR_less(i, beta.length, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9794, _mut9795, _mut9796, _mut9797, _mut9798); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
                _i = newIndices[i];
                for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9789, _mut9790, _mut9791, _mut9792, _mut9793); j++, idx1++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004");
                    _j = newIndices[j];
                    if (ROR_greater(_i, _j, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9752, _mut9753, _mut9754, _mut9755, _mut9756)) {
                        idx2 = AOR_plus(AOR_divide(AOR_multiply(_i, (AOR_plus(_i, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9773, _mut9774, _mut9775, _mut9776)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9777, _mut9778, _mut9779, _mut9780), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9781, _mut9782, _mut9783, _mut9784), _j, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9785, _mut9786, _mut9787, _mut9788);
                    } else {
                        idx2 = AOR_plus(AOR_divide(AOR_multiply(_j, (AOR_plus(_j, 1, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9757, _mut9758, _mut9759, _mut9760)), "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9761, _mut9762, _mut9763, _mut9764), 2, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9765, _mut9766, _mut9767, _mut9768), _i, "org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress_1004", _mut9769, _mut9770, _mut9771, _mut9772);
                    }
                    covNew[idx1] = cov[idx2];
                }
            }
            return new RegressionResults(betaNew, new double[][] { covNew }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        }
    }
}
