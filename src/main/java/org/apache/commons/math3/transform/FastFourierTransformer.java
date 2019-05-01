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
package org.apache.commons.math3.transform;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the Fast Fourier Transform for transformation of one-dimensional
 * real or complex data sets. For reference, see <em>Applied Numerical Linear
 * Algebra</em>, ISBN 0898713897, chapter 6.
 * <p>
 * There are several variants of the discrete Fourier transform, with various
 * normalization conventions, which are specified by the parameter
 * {@link DftNormalization}.
 * <p>
 * The current implementation of the discrete Fourier transform as a fast
 * Fourier transform requires the length of the data set to be a power of 2.
 * This greatly simplifies and speeds up the code. Users can pad the data with
 * zeros to meet this requirement. There are other flavors of FFT, for
 * reference, see S. Winograd,
 * <i>On computing the discrete Fourier transform</i>, Mathematics of
 * Computation, 32 (1978), 175 - 199.
 *
 * @see DftNormalization
 * @since 1.2
 */
public class FastFourierTransformer implements Serializable {

    @Conditional
    public static boolean _mut1063 = false, _mut1064 = false, _mut1065 = false, _mut1066 = false, _mut1067 = false, _mut1068 = false, _mut1069 = false, _mut1070 = false, _mut1071 = false, _mut1072 = false, _mut1073 = false, _mut1074 = false, _mut1075 = false, _mut1076 = false, _mut1077 = false, _mut1078 = false, _mut1079 = false, _mut1080 = false, _mut1081 = false, _mut1082 = false, _mut1083 = false, _mut1084 = false, _mut1085 = false, _mut1086 = false, _mut1087 = false, _mut1088 = false, _mut1089 = false, _mut1090 = false, _mut1091 = false, _mut1092 = false, _mut1093 = false, _mut1094 = false, _mut1095 = false, _mut1096 = false, _mut1097 = false, _mut1098 = false, _mut1099 = false, _mut1100 = false, _mut1101 = false, _mut1102 = false, _mut1103 = false, _mut1104 = false, _mut1105 = false, _mut1106 = false, _mut1107 = false, _mut1108 = false, _mut1109 = false, _mut1110 = false, _mut1111 = false, _mut1112 = false, _mut1113 = false, _mut1114 = false, _mut1115 = false, _mut1116 = false, _mut1117 = false, _mut1118 = false, _mut1119 = false, _mut1120 = false, _mut1121 = false, _mut1122 = false, _mut1123 = false, _mut1124 = false, _mut1125 = false, _mut1126 = false, _mut1127 = false, _mut1128 = false, _mut1129 = false, _mut1130 = false, _mut1131 = false, _mut1132 = false, _mut1133 = false, _mut1134 = false, _mut1135 = false, _mut1136 = false, _mut1137 = false, _mut1138 = false, _mut1139 = false, _mut1140 = false, _mut1141 = false, _mut1142 = false, _mut1143 = false, _mut1144 = false, _mut1145 = false, _mut1146 = false, _mut1147 = false, _mut1148 = false, _mut1149 = false, _mut1150 = false, _mut1151 = false, _mut1152 = false, _mut1153 = false, _mut1154 = false, _mut1155 = false, _mut1156 = false, _mut1157 = false, _mut1158 = false, _mut1159 = false, _mut1160 = false, _mut1161 = false, _mut1162 = false, _mut1163 = false, _mut1164 = false, _mut1165 = false, _mut1166 = false, _mut1167 = false, _mut1168 = false, _mut1169 = false, _mut1170 = false, _mut1171 = false, _mut1172 = false, _mut1173 = false, _mut1174 = false, _mut1175 = false, _mut1176 = false, _mut1177 = false, _mut1178 = false, _mut1179 = false, _mut1180 = false, _mut1181 = false, _mut1182 = false, _mut1183 = false, _mut1184 = false, _mut1185 = false, _mut1186 = false, _mut1187 = false, _mut1188 = false, _mut1189 = false, _mut1190 = false, _mut1191 = false, _mut1192 = false, _mut1193 = false, _mut1194 = false, _mut1195 = false, _mut1196 = false, _mut1197 = false, _mut1198 = false, _mut1199 = false, _mut1200 = false, _mut1201 = false, _mut1202 = false, _mut1203 = false, _mut1204 = false, _mut1205 = false, _mut1206 = false, _mut1207 = false, _mut1208 = false, _mut1209 = false, _mut1210 = false, _mut1211 = false, _mut1212 = false, _mut1213 = false, _mut1214 = false, _mut1215 = false, _mut1216 = false, _mut1217 = false, _mut1218 = false, _mut1219 = false, _mut1220 = false, _mut1221 = false, _mut1222 = false, _mut1223 = false, _mut1224 = false, _mut1225 = false, _mut1226 = false, _mut1227 = false, _mut1228 = false, _mut1229 = false, _mut1230 = false, _mut1231 = false, _mut1232 = false, _mut1233 = false, _mut1234 = false, _mut1235 = false, _mut1236 = false, _mut1237 = false, _mut1238 = false, _mut1239 = false, _mut1240 = false, _mut1241 = false, _mut1242 = false, _mut1243 = false, _mut1244 = false, _mut1245 = false, _mut1246 = false, _mut1247 = false, _mut1248 = false, _mut1249 = false, _mut1250 = false, _mut1251 = false, _mut1252 = false, _mut1253 = false, _mut1254 = false, _mut1255 = false, _mut1256 = false, _mut1257 = false, _mut1258 = false, _mut1259 = false, _mut1260 = false, _mut1261 = false, _mut1262 = false, _mut1263 = false, _mut1264 = false, _mut1265 = false, _mut1266 = false, _mut1267 = false, _mut1268 = false, _mut1269 = false, _mut1270 = false, _mut1271 = false, _mut1272 = false, _mut1273 = false, _mut1274 = false, _mut1275 = false, _mut1276 = false, _mut1277 = false, _mut1278 = false, _mut1279 = false, _mut1280 = false, _mut1281 = false, _mut1282 = false, _mut1283 = false, _mut1284 = false, _mut1285 = false, _mut1286 = false, _mut1287 = false, _mut1288 = false, _mut1289 = false, _mut1290 = false, _mut1291 = false, _mut1292 = false, _mut1293 = false, _mut1294 = false, _mut1295 = false, _mut1296 = false, _mut1297 = false, _mut1298 = false, _mut1299 = false, _mut1300 = false, _mut1301 = false, _mut1302 = false, _mut1303 = false, _mut1304 = false, _mut1305 = false, _mut1306 = false, _mut1307 = false, _mut1308 = false, _mut1309 = false, _mut1310 = false, _mut1311 = false, _mut1312 = false, _mut1313 = false, _mut1314 = false, _mut1315 = false, _mut1316 = false, _mut1317 = false, _mut1318 = false, _mut1319 = false, _mut1320 = false, _mut1321 = false, _mut1322 = false, _mut1323 = false, _mut1324 = false, _mut1325 = false, _mut1326 = false, _mut1327 = false, _mut1328 = false, _mut1329 = false, _mut1330 = false, _mut1331 = false, _mut1332 = false, _mut1333 = false, _mut1334 = false, _mut1335 = false, _mut1336 = false, _mut1337 = false, _mut1338 = false, _mut1339 = false, _mut1340 = false, _mut1341 = false, _mut1342 = false, _mut1343 = false, _mut1344 = false, _mut1345 = false, _mut1346 = false, _mut1347 = false, _mut1348 = false, _mut1349 = false, _mut1350 = false, _mut1351 = false, _mut1352 = false, _mut1353 = false, _mut1354 = false, _mut1355 = false, _mut1356 = false, _mut1357 = false, _mut1358 = false, _mut1359 = false, _mut1360 = false, _mut1361 = false, _mut1362 = false, _mut1363 = false, _mut1364 = false, _mut1365 = false, _mut1366 = false, _mut1367 = false, _mut1368 = false, _mut1369 = false, _mut1370 = false, _mut1371 = false, _mut1372 = false, _mut1373 = false, _mut1374 = false, _mut1375 = false, _mut1376 = false, _mut1377 = false, _mut1378 = false, _mut1379 = false, _mut1380 = false, _mut1381 = false, _mut1382 = false, _mut1383 = false, _mut1384 = false, _mut1385 = false, _mut1386 = false, _mut1387 = false, _mut1388 = false, _mut1389 = false, _mut1390 = false, _mut1391 = false, _mut1392 = false, _mut1393 = false, _mut1394 = false, _mut1395 = false, _mut1396 = false, _mut1397 = false, _mut1398 = false, _mut1399 = false, _mut1400 = false, _mut1401 = false, _mut1402 = false, _mut1403 = false, _mut1404 = false, _mut1405 = false, _mut1406 = false, _mut1407 = false, _mut1408 = false, _mut1409 = false, _mut1410 = false, _mut1411 = false, _mut1412 = false, _mut1413 = false, _mut1414 = false, _mut1415 = false, _mut1416 = false, _mut1417 = false, _mut1418 = false, _mut1419 = false, _mut1420 = false, _mut1421 = false, _mut1422 = false, _mut1423 = false, _mut1424 = false, _mut1425 = false, _mut1426 = false, _mut1427 = false, _mut1428 = false, _mut1429 = false, _mut1430 = false, _mut1431 = false, _mut1432 = false, _mut1433 = false, _mut1434 = false, _mut1435 = false, _mut1436 = false, _mut1437 = false, _mut1438 = false, _mut1439 = false, _mut1440 = false, _mut1441 = false, _mut1442 = false, _mut1443 = false, _mut1444 = false, _mut1445 = false, _mut1446 = false, _mut1447 = false, _mut1448 = false, _mut1449 = false, _mut1450 = false, _mut1451 = false, _mut1452 = false, _mut1453 = false, _mut1454 = false, _mut1455 = false, _mut1456 = false, _mut1457 = false, _mut1458 = false, _mut1459 = false, _mut1460 = false, _mut1461 = false, _mut1462 = false, _mut1463 = false, _mut1464 = false, _mut1465 = false, _mut1466 = false, _mut1467 = false, _mut1468 = false, _mut1469 = false, _mut1470 = false, _mut1471 = false, _mut1472 = false, _mut1473 = false, _mut1474 = false, _mut1475 = false, _mut1476 = false, _mut1477 = false, _mut1478 = false, _mut1479 = false, _mut1480 = false, _mut1481 = false, _mut1482 = false, _mut1483 = false, _mut1484 = false, _mut1485 = false, _mut1486 = false, _mut1487 = false, _mut1488 = false, _mut1489 = false, _mut1490 = false, _mut1491 = false, _mut1492 = false, _mut1493 = false, _mut1494 = false, _mut1495 = false, _mut1496 = false, _mut1497 = false, _mut1498 = false, _mut1499 = false, _mut1500 = false, _mut1501 = false, _mut1502 = false, _mut1503 = false, _mut1504 = false, _mut1505 = false, _mut1506 = false, _mut1507 = false, _mut1508 = false, _mut1509 = false, _mut1510 = false, _mut1511 = false, _mut1512 = false, _mut1513 = false, _mut1514 = false, _mut1515 = false, _mut1516 = false, _mut1517 = false, _mut1518 = false, _mut1519 = false, _mut1520 = false, _mut1521 = false, _mut1522 = false, _mut1523 = false, _mut1524 = false, _mut1525 = false, _mut1526 = false, _mut1527 = false, _mut1528 = false, _mut1529 = false, _mut1530 = false, _mut1531 = false, _mut1532 = false, _mut1533 = false, _mut1534 = false, _mut1535 = false, _mut1536 = false, _mut1537 = false, _mut1538 = false, _mut1539 = false, _mut1540 = false, _mut1541 = false, _mut1542 = false, _mut1543 = false, _mut1544 = false, _mut1545 = false, _mut1546 = false, _mut1547 = false, _mut1548 = false, _mut1549 = false, _mut1550 = false, _mut1551 = false, _mut1552 = false, _mut1553 = false, _mut1554 = false, _mut1555 = false, _mut1556 = false, _mut1557 = false, _mut1558 = false, _mut1559 = false, _mut1560 = false, _mut1561 = false, _mut1562 = false, _mut1563 = false, _mut1564 = false, _mut1565 = false, _mut1566 = false, _mut1567 = false, _mut1568 = false, _mut1569 = false, _mut1570 = false, _mut1571 = false, _mut1572 = false, _mut1573 = false, _mut1574 = false, _mut1575 = false, _mut1576 = false, _mut1577 = false, _mut1578 = false, _mut1579 = false, _mut1580 = false, _mut1581 = false, _mut1582 = false, _mut1583 = false, _mut1584 = false, _mut1585 = false, _mut1586 = false, _mut1587 = false, _mut1588 = false, _mut1589 = false, _mut1590 = false, _mut1591 = false, _mut1592 = false, _mut1593 = false, _mut1594 = false, _mut1595 = false, _mut1596 = false, _mut1597 = false, _mut1598 = false, _mut1599 = false, _mut1600 = false, _mut1601 = false, _mut1602 = false, _mut1603 = false, _mut1604 = false, _mut1605 = false, _mut1606 = false, _mut1607 = false;

    /**
     * Serializable version identifier.
     */
    static final long serialVersionUID = 20120210L;

    /**
     * {@code W_SUB_N_R[i]} is the real part of
     * {@code exp(- 2 * i * pi / n)}:
     * {@code W_SUB_N_R[i] = cos(2 * pi/ n)}, where {@code n = 2^i}.
     */
    private static final double[] W_SUB_N_R = { 0x1.0p0, -0x1.0p0, 0x1.1a62633145c07p-54, 0x1.6a09e667f3bcdp-1, 0x1.d906bcf328d46p-1, 0x1.f6297cff75cbp-1, 0x1.fd88da3d12526p-1, 0x1.ff621e3796d7ep-1, 0x1.ffd886084cd0dp-1, 0x1.fff62169b92dbp-1, 0x1.fffd8858e8a92p-1, 0x1.ffff621621d02p-1, 0x1.ffffd88586ee6p-1, 0x1.fffff62161a34p-1, 0x1.fffffd8858675p-1, 0x1.ffffff621619cp-1, 0x1.ffffffd885867p-1, 0x1.fffffff62161ap-1, 0x1.fffffffd88586p-1, 0x1.ffffffff62162p-1, 0x1.ffffffffd8858p-1, 0x1.fffffffff6216p-1, 0x1.fffffffffd886p-1, 0x1.ffffffffff621p-1, 0x1.ffffffffffd88p-1, 0x1.fffffffffff62p-1, 0x1.fffffffffffd9p-1, 0x1.ffffffffffff6p-1, 0x1.ffffffffffffep-1, 0x1.fffffffffffffp-1, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0, 0x1.0p0 };

    /**
     * {@code W_SUB_N_I[i]} is the imaginary part of
     * {@code exp(- 2 * i * pi / n)}:
     * {@code W_SUB_N_I[i] = -sin(2 * pi/ n)}, where {@code n = 2^i}.
     */
    private static final double[] W_SUB_N_I = { 0x1.1a62633145c07p-52, -0x1.1a62633145c07p-53, -0x1.0p0, -0x1.6a09e667f3bccp-1, -0x1.87de2a6aea963p-2, -0x1.8f8b83c69a60ap-3, -0x1.917a6bc29b42cp-4, -0x1.91f65f10dd814p-5, -0x1.92155f7a3667ep-6, -0x1.921d1fcdec784p-7, -0x1.921f0fe670071p-8, -0x1.921f8becca4bap-9, -0x1.921faaee6472dp-10, -0x1.921fb2aecb36p-11, -0x1.921fb49ee4ea6p-12, -0x1.921fb51aeb57bp-13, -0x1.921fb539ecf31p-14, -0x1.921fb541ad59ep-15, -0x1.921fb5439d73ap-16, -0x1.921fb544197ap-17, -0x1.921fb544387bap-18, -0x1.921fb544403c1p-19, -0x1.921fb544422c2p-20, -0x1.921fb54442a83p-21, -0x1.921fb54442c73p-22, -0x1.921fb54442cefp-23, -0x1.921fb54442d0ep-24, -0x1.921fb54442d15p-25, -0x1.921fb54442d17p-26, -0x1.921fb54442d18p-27, -0x1.921fb54442d18p-28, -0x1.921fb54442d18p-29, -0x1.921fb54442d18p-30, -0x1.921fb54442d18p-31, -0x1.921fb54442d18p-32, -0x1.921fb54442d18p-33, -0x1.921fb54442d18p-34, -0x1.921fb54442d18p-35, -0x1.921fb54442d18p-36, -0x1.921fb54442d18p-37, -0x1.921fb54442d18p-38, -0x1.921fb54442d18p-39, -0x1.921fb54442d18p-40, -0x1.921fb54442d18p-41, -0x1.921fb54442d18p-42, -0x1.921fb54442d18p-43, -0x1.921fb54442d18p-44, -0x1.921fb54442d18p-45, -0x1.921fb54442d18p-46, -0x1.921fb54442d18p-47, -0x1.921fb54442d18p-48, -0x1.921fb54442d18p-49, -0x1.921fb54442d18p-50, -0x1.921fb54442d18p-51, -0x1.921fb54442d18p-52, -0x1.921fb54442d18p-53, -0x1.921fb54442d18p-54, -0x1.921fb54442d18p-55, -0x1.921fb54442d18p-56, -0x1.921fb54442d18p-57, -0x1.921fb54442d18p-58, -0x1.921fb54442d18p-59, -0x1.921fb54442d18p-60 };

    /**
     * The type of DFT to be performed.
     */
    private final DftNormalization normalization;

    /**
     * Creates a new instance of this class, with various normalization
     * conventions.
     *
     * @param normalization the type of normalization to be applied to the
     * transformed data
     */
    public FastFourierTransformer(final DftNormalization normalization) {
        this.normalization = normalization;
    }

    /**
     * Performs identical index bit reversal shuffles on two arrays of identical
     * size. Each element in the array is swapped with another element based on
     * the bit-reversal of the index. For example, in an array with length 16,
     * item at binary index 0011 (decimal 3) would be swapped with the item at
     * binary index 1100 (decimal 12).
     *
     * @param a the first array to be shuffled
     * @param b the second array to be shuffled
     */
    private static void bitReversalShuffle2(double[] a, double[] b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128");
        final int n = a.length;
        assert ROR_equals(b.length, n, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1063, _mut1064, _mut1065, _mut1066, _mut1067);
        final int halfOfN = n >> 1;
        int j = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1084, _mut1085, _mut1086, _mut1087, _mut1088); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128");
            if (ROR_less(i, j, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1068, _mut1069, _mut1070, _mut1071, _mut1072)) {
                // swap indices i & j
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                temp = b[i];
                b[i] = b[j];
                b[j] = temp;
            }
            int k = halfOfN;
            while ((_mut1083 ? (ROR_less_equals(k, j, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1073, _mut1074, _mut1075, _mut1076, _mut1077) || ROR_greater(k, 0, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1078, _mut1079, _mut1080, _mut1081, _mut1082)) : (ROR_less_equals(k, j, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1073, _mut1074, _mut1075, _mut1076, _mut1077) && ROR_greater(k, 0, "org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128", _mut1078, _mut1079, _mut1080, _mut1081, _mut1082)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.bitReversalShuffle2_128");
                j -= k;
                k >>= 1;
            }
            j += k;
        }
    }

    /**
     * Applies the proper normalization to the specified transformed data.
     *
     * @param dataRI the unscaled transformed data
     * @param normalization the normalization to be applied
     * @param type the type of transform (forward, inverse) which resulted in the specified data
     */
    private static void normalizeTransformedData(final double[][] dataRI, final DftNormalization normalization, final TransformType type) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162");
        final double[] dataR = dataRI[0];
        final double[] dataI = dataRI[1];
        final int n = dataR.length;
        assert ROR_equals(dataI.length, n, "org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162", _mut1089, _mut1090, _mut1091, _mut1092, _mut1093);
        switch(normalization) {
            case STANDARD:
                if (type == TransformType.INVERSE) {
                    final double scaleFactor = AOR_divide(1.0, ((double) n), "org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162", _mut1094, _mut1095, _mut1096, _mut1097);
                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162", _mut1098, _mut1099, _mut1100, _mut1101, _mut1102); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162");
                        dataR[i] *= scaleFactor;
                        dataI[i] *= scaleFactor;
                    }
                }
                break;
            case UNITARY:
                final double scaleFactor = AOR_divide(1.0, FastMath.sqrt(n), "org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162", _mut1103, _mut1104, _mut1105, _mut1106);
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162", _mut1107, _mut1108, _mut1109, _mut1110, _mut1111); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.normalizeTransformedData_162");
                    dataR[i] *= scaleFactor;
                    dataI[i] *= scaleFactor;
                }
                break;
            default:
                /*
                 * This should never occur in normal conditions. However this
                 * clause has been added as a safeguard if other types of
                 * normalizations are ever implemented, and the corresponding
                 * test is forgotten in the present switch.
                 */
                throw new MathIllegalStateException();
        }
    }

    /**
     * Computes the standard transform of the specified complex data. The
     * computation is done in place. The input data is laid out as follows
     * <ul>
     *   <li>{@code dataRI[0][i]} is the real part of the {@code i}-th data point,</li>
     *   <li>{@code dataRI[1][i]} is the imaginary part of the {@code i}-th data point.</li>
     * </ul>
     *
     * @param dataRI the two dimensional array of real and imaginary parts of the data
     * @param normalization the normalization to be applied to the transformed data
     * @param type the type of transform (forward, inverse) to be performed
     * @throws DimensionMismatchException if the number of rows of the specified
     *   array is not two, or the array is not rectangular
     * @throws MathIllegalArgumentException if the number of data points is not
     *   a power of two
     */
    public static void transformInPlace(final double[][] dataRI, final DftNormalization normalization, final TransformType type) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
        if (ROR_not_equals(dataRI.length, 2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1112, _mut1113, _mut1114, _mut1115, _mut1116)) {
            throw new DimensionMismatchException(dataRI.length, 2);
        }
        final double[] dataR = dataRI[0];
        final double[] dataI = dataRI[1];
        if (ROR_not_equals(dataR.length, dataI.length, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1117, _mut1118, _mut1119, _mut1120, _mut1121)) {
            throw new DimensionMismatchException(dataI.length, dataR.length);
        }
        final int n = dataR.length;
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(n));
        }
        if (ROR_equals(n, 1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1122, _mut1123, _mut1124, _mut1125, _mut1126)) {
            return;
        } else if (ROR_equals(n, 2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1127, _mut1128, _mut1129, _mut1130, _mut1131)) {
            final double srcR0 = dataR[0];
            final double srcI0 = dataI[0];
            final double srcR1 = dataR[1];
            final double srcI1 = dataI[1];
            // X_0 = x_0 + x_1
            dataR[0] = AOR_plus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1132, _mut1133, _mut1134, _mut1135);
            dataI[0] = AOR_plus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1136, _mut1137, _mut1138, _mut1139);
            // X_1 = x_0 - x_1
            dataR[1] = AOR_minus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1140, _mut1141, _mut1142, _mut1143);
            dataI[1] = AOR_minus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1144, _mut1145, _mut1146, _mut1147);
            normalizeTransformedData(dataRI, normalization, type);
            return;
        }
        bitReversalShuffle2(dataR, dataI);
        // Do 4-term DFT.
        if (type == TransformType.INVERSE) {
            for (int i0 = 0; ROR_less(i0, n, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1369, _mut1370, _mut1371, _mut1372, _mut1373); i0 += 4) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
                final int i1 = AOR_plus(i0, 1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1261, _mut1262, _mut1263, _mut1264);
                final int i2 = AOR_plus(i0, 2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1265, _mut1266, _mut1267, _mut1268);
                final int i3 = AOR_plus(i0, 3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1269, _mut1270, _mut1271, _mut1272);
                final double srcR0 = dataR[i0];
                final double srcI0 = dataI[i0];
                final double srcR1 = dataR[i2];
                final double srcI1 = dataI[i2];
                final double srcR2 = dataR[i1];
                final double srcI2 = dataI[i1];
                final double srcR3 = dataR[i3];
                final double srcI3 = dataI[i3];
                // X_0 = x_0 + x_1 + x_2 + x_3
                dataR[i0] = AOR_plus(AOR_plus(AOR_plus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1273, _mut1274, _mut1275, _mut1276), srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1277, _mut1278, _mut1279, _mut1280), srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1281, _mut1282, _mut1283, _mut1284);
                dataI[i0] = AOR_plus(AOR_plus(AOR_plus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1285, _mut1286, _mut1287, _mut1288), srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1289, _mut1290, _mut1291, _mut1292), srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1293, _mut1294, _mut1295, _mut1296);
                // X_1 = x_0 - x_2 + j * (x_3 - x_1)
                dataR[i1] = AOR_plus(AOR_minus(srcR0, srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1297, _mut1298, _mut1299, _mut1300), (AOR_minus(srcI3, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1301, _mut1302, _mut1303, _mut1304)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1305, _mut1306, _mut1307, _mut1308);
                dataI[i1] = AOR_plus(AOR_minus(srcI0, srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1309, _mut1310, _mut1311, _mut1312), (AOR_minus(srcR1, srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1313, _mut1314, _mut1315, _mut1316)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1317, _mut1318, _mut1319, _mut1320);
                // X_2 = x_0 - x_1 + x_2 - x_3
                dataR[i2] = AOR_minus(AOR_plus(AOR_minus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1321, _mut1322, _mut1323, _mut1324), srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1325, _mut1326, _mut1327, _mut1328), srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1329, _mut1330, _mut1331, _mut1332);
                dataI[i2] = AOR_minus(AOR_plus(AOR_minus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1333, _mut1334, _mut1335, _mut1336), srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1337, _mut1338, _mut1339, _mut1340), srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1341, _mut1342, _mut1343, _mut1344);
                // X_3 = x_0 - x_2 + j * (x_1 - x_3)
                dataR[i3] = AOR_plus(AOR_minus(srcR0, srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1345, _mut1346, _mut1347, _mut1348), (AOR_minus(srcI1, srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1349, _mut1350, _mut1351, _mut1352)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1353, _mut1354, _mut1355, _mut1356);
                dataI[i3] = AOR_plus(AOR_minus(srcI0, srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1357, _mut1358, _mut1359, _mut1360), (AOR_minus(srcR3, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1361, _mut1362, _mut1363, _mut1364)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1365, _mut1366, _mut1367, _mut1368);
            }
        } else {
            for (int i0 = 0; ROR_less(i0, n, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1256, _mut1257, _mut1258, _mut1259, _mut1260); i0 += 4) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
                final int i1 = AOR_plus(i0, 1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1148, _mut1149, _mut1150, _mut1151);
                final int i2 = AOR_plus(i0, 2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1152, _mut1153, _mut1154, _mut1155);
                final int i3 = AOR_plus(i0, 3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1156, _mut1157, _mut1158, _mut1159);
                final double srcR0 = dataR[i0];
                final double srcI0 = dataI[i0];
                final double srcR1 = dataR[i2];
                final double srcI1 = dataI[i2];
                final double srcR2 = dataR[i1];
                final double srcI2 = dataI[i1];
                final double srcR3 = dataR[i3];
                final double srcI3 = dataI[i3];
                // X_0 = x_0 + x_1 + x_2 + x_3
                dataR[i0] = AOR_plus(AOR_plus(AOR_plus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1160, _mut1161, _mut1162, _mut1163), srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1164, _mut1165, _mut1166, _mut1167), srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1168, _mut1169, _mut1170, _mut1171);
                dataI[i0] = AOR_plus(AOR_plus(AOR_plus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1172, _mut1173, _mut1174, _mut1175), srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1176, _mut1177, _mut1178, _mut1179), srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1180, _mut1181, _mut1182, _mut1183);
                // X_1 = x_0 - x_2 + j * (x_3 - x_1)
                dataR[i1] = AOR_plus(AOR_minus(srcR0, srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1184, _mut1185, _mut1186, _mut1187), (AOR_minus(srcI1, srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1188, _mut1189, _mut1190, _mut1191)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1192, _mut1193, _mut1194, _mut1195);
                dataI[i1] = AOR_plus(AOR_minus(srcI0, srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1196, _mut1197, _mut1198, _mut1199), (AOR_minus(srcR3, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1200, _mut1201, _mut1202, _mut1203)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1204, _mut1205, _mut1206, _mut1207);
                // X_2 = x_0 - x_1 + x_2 - x_3
                dataR[i2] = AOR_minus(AOR_plus(AOR_minus(srcR0, srcR1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1208, _mut1209, _mut1210, _mut1211), srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1212, _mut1213, _mut1214, _mut1215), srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1216, _mut1217, _mut1218, _mut1219);
                dataI[i2] = AOR_minus(AOR_plus(AOR_minus(srcI0, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1220, _mut1221, _mut1222, _mut1223), srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1224, _mut1225, _mut1226, _mut1227), srcI3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1228, _mut1229, _mut1230, _mut1231);
                // X_3 = x_0 - x_2 + j * (x_1 - x_3)
                dataR[i3] = AOR_plus(AOR_minus(srcR0, srcR2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1232, _mut1233, _mut1234, _mut1235), (AOR_minus(srcI3, srcI1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1236, _mut1237, _mut1238, _mut1239)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1240, _mut1241, _mut1242, _mut1243);
                dataI[i3] = AOR_plus(AOR_minus(srcI0, srcI2, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1244, _mut1245, _mut1246, _mut1247), (AOR_minus(srcR1, srcR3, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1248, _mut1249, _mut1250, _mut1251)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1252, _mut1253, _mut1254, _mut1255);
            }
        }
        int lastN0 = 4;
        int lastLogN0 = 2;
        while (ROR_less(lastN0, n, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1512, _mut1513, _mut1514, _mut1515, _mut1516)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
            int n0 = lastN0 << 1;
            int logN0 = AOR_plus(lastLogN0, 1, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1374, _mut1375, _mut1376, _mut1377);
            double wSubN0R = W_SUB_N_R[logN0];
            double wSubN0I = W_SUB_N_I[logN0];
            if (type == TransformType.INVERSE) {
                wSubN0I = -wSubN0I;
            }
            // Combine even/odd transforms of size lastN0 into a transform of size N0 (lastN0 * 2).
            for (int destEvenStartIndex = 0; ROR_less(destEvenStartIndex, n, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1507, _mut1508, _mut1509, _mut1510, _mut1511); destEvenStartIndex += n0) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
                int destOddStartIndex = AOR_plus(destEvenStartIndex, lastN0, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1378, _mut1379, _mut1380, _mut1381);
                double wSubN0ToRR = 1;
                double wSubN0ToRI = 0;
                for (int r = 0; ROR_less(r, lastN0, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1502, _mut1503, _mut1504, _mut1505, _mut1506); r++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214");
                    double grR = dataR[AOR_plus(destEvenStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1382, _mut1383, _mut1384, _mut1385)];
                    double grI = dataI[AOR_plus(destEvenStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1386, _mut1387, _mut1388, _mut1389)];
                    double hrR = dataR[AOR_plus(destOddStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1390, _mut1391, _mut1392, _mut1393)];
                    double hrI = dataI[AOR_plus(destOddStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1394, _mut1395, _mut1396, _mut1397)];
                    // dest[destEvenStartIndex + r] = Gr + WsubN0ToR * Hr
                    dataR[AOR_plus(destEvenStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1398, _mut1399, _mut1400, _mut1401)] = AOR_minus(AOR_plus(grR, AOR_multiply(wSubN0ToRR, hrR, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1402, _mut1403, _mut1404, _mut1405), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1406, _mut1407, _mut1408, _mut1409), AOR_multiply(wSubN0ToRI, hrI, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1410, _mut1411, _mut1412, _mut1413), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1414, _mut1415, _mut1416, _mut1417);
                    dataI[AOR_plus(destEvenStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1418, _mut1419, _mut1420, _mut1421)] = AOR_plus(AOR_plus(grI, AOR_multiply(wSubN0ToRR, hrI, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1422, _mut1423, _mut1424, _mut1425), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1426, _mut1427, _mut1428, _mut1429), AOR_multiply(wSubN0ToRI, hrR, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1430, _mut1431, _mut1432, _mut1433), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1434, _mut1435, _mut1436, _mut1437);
                    // dest[destOddStartIndex + r] = Gr - WsubN0ToR * Hr
                    dataR[AOR_plus(destOddStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1438, _mut1439, _mut1440, _mut1441)] = AOR_minus(grR, (AOR_minus(AOR_multiply(wSubN0ToRR, hrR, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1442, _mut1443, _mut1444, _mut1445), AOR_multiply(wSubN0ToRI, hrI, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1446, _mut1447, _mut1448, _mut1449), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1450, _mut1451, _mut1452, _mut1453)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1454, _mut1455, _mut1456, _mut1457);
                    dataI[AOR_plus(destOddStartIndex, r, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1458, _mut1459, _mut1460, _mut1461)] = AOR_minus(grI, (AOR_plus(AOR_multiply(wSubN0ToRR, hrI, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1462, _mut1463, _mut1464, _mut1465), AOR_multiply(wSubN0ToRI, hrR, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1466, _mut1467, _mut1468, _mut1469), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1470, _mut1471, _mut1472, _mut1473)), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1474, _mut1475, _mut1476, _mut1477);
                    // WsubN0ToR *= WsubN0R
                    double nextWsubN0ToRR = AOR_minus(AOR_multiply(wSubN0ToRR, wSubN0R, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1478, _mut1479, _mut1480, _mut1481), AOR_multiply(wSubN0ToRI, wSubN0I, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1482, _mut1483, _mut1484, _mut1485), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1486, _mut1487, _mut1488, _mut1489);
                    double nextWsubN0ToRI = AOR_plus(AOR_multiply(wSubN0ToRR, wSubN0I, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1490, _mut1491, _mut1492, _mut1493), AOR_multiply(wSubN0ToRI, wSubN0R, "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1494, _mut1495, _mut1496, _mut1497), "org.apache.commons.math3.transform.FastFourierTransformer.transformInPlace_214", _mut1498, _mut1499, _mut1500, _mut1501);
                    wSubN0ToRR = nextWsubN0ToRR;
                    wSubN0ToRI = nextWsubN0ToRI;
                }
            }
            lastN0 = n0;
            lastLogN0 = logN0;
        }
        normalizeTransformedData(dataRI, normalization, type);
    }

    /**
     * Returns the (forward, inverse) transform of the specified real data set.
     *
     * @param f the real data array to be transformed
     * @param type the type of transform (forward, inverse) to be performed
     * @return the complex transformed array
     * @throws MathIllegalArgumentException if the length of the data array is not a power of two
     */
    public Complex[] transform(final double[] f, final TransformType type) {
        final double[][] dataRI = new double[][] { MathArrays.copyOf(f, f.length), new double[f.length] };
        transformInPlace(dataRI, normalization, type);
        return TransformUtils.createComplexArray(dataRI);
    }

    /**
     * Returns the (forward, inverse) transform of the specified real function,
     * sampled on the specified interval.
     *
     * @param f the function to be sampled and transformed
     * @param min the (inclusive) lower bound for the interval
     * @param max the (exclusive) upper bound for the interval
     * @param n the number of sample points
     * @param type the type of transform (forward, inverse) to be performed
     * @return the complex transformed array
     * @throws org.apache.commons.math3.exception.NumberIsTooLargeException
     *   if the lower bound is greater than, or equal to the upper bound
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     *   if the number of sample points {@code n} is negative
     * @throws MathIllegalArgumentException if the number of sample points
     *   {@code n} is not a power of two
     */
    public Complex[] transform(final UnivariateFunction f, final double min, final double max, final int n, final TransformType type) {
        final double[] data = FunctionUtils.sample(f, min, max, n);
        return transform(data, type);
    }

    /**
     * Returns the (forward, inverse) transform of the specified complex data set.
     *
     * @param f the complex data array to be transformed
     * @param type the type of transform (forward, inverse) to be performed
     * @return the complex transformed array
     * @throws MathIllegalArgumentException if the length of the data array is not a power of two
     */
    public Complex[] transform(final Complex[] f, final TransformType type) {
        final double[][] dataRI = TransformUtils.createRealImaginaryArray(f);
        transformInPlace(dataRI, normalization, type);
        return TransformUtils.createComplexArray(dataRI);
    }

    /**
     * Performs a multi-dimensional Fourier transform on a given array. Use
     * {@link #transform(Complex[], TransformType)} in a row-column
     * implementation in any number of dimensions with
     * O(N&times;log(N)) complexity with
     * N = n<sub>1</sub> &times; n<sub>2</sub> &times;n<sub>3</sub> &times; ...
     * &times; n<sub>d</sub>, where n<sub>k</sub> is the number of elements in
     * dimension k, and d is the total number of dimensions.
     *
     * @param mdca Multi-Dimensional Complex Array, i.e. {@code Complex[][][][]}
     * @param type the type of transform (forward, inverse) to be performed
     * @return transform of {@code mdca} as a Multi-Dimensional Complex Array, i.e. {@code Complex[][][][]}
     * @throws IllegalArgumentException if any dimension is not a power of two
     * @deprecated see MATH-736
     */
    @Deprecated
    public Object mdfft(Object mdca, TransformType type) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_435");
        MultiDimensionalComplexMatrix mdcm = (MultiDimensionalComplexMatrix) new MultiDimensionalComplexMatrix(mdca).clone();
        int[] dimensionSize = mdcm.getDimensionSizes();
        // cycle through each dimension
        for (int i = 0; ROR_less(i, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_435", _mut1517, _mut1518, _mut1519, _mut1520, _mut1521); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_435");
            mdfft(mdcm, type, i, new int[0]);
        }
        return mdcm.getArray();
    }

    /**
     * Performs one dimension of a multi-dimensional Fourier transform.
     *
     * @param mdcm input matrix
     * @param type the type of transform (forward, inverse) to be performed
     * @param d index of the dimension to process
     * @param subVector recursion subvector
     * @throws IllegalArgumentException if any dimension is not a power of two
     * @deprecated see MATH-736
     */
    @Deprecated
    private void mdfft(MultiDimensionalComplexMatrix mdcm, TransformType type, int d, int[] subVector) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457");
        int[] dimensionSize = mdcm.getDimensionSizes();
        // if done
        if (ROR_equals(subVector.length, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1522, _mut1523, _mut1524, _mut1525, _mut1526)) {
            Complex[] temp = new Complex[dimensionSize[d]];
            for (int i = 0; ROR_less(i, dimensionSize[d], "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1541, _mut1542, _mut1543, _mut1544, _mut1545); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457");
                // fft along dimension d
                subVector[d] = i;
                temp[i] = mdcm.get(subVector);
            }
            temp = transform(temp, type);
            for (int i = 0; ROR_less(i, dimensionSize[d], "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1546, _mut1547, _mut1548, _mut1549, _mut1550); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457");
                subVector[d] = i;
                mdcm.set(temp[i], subVector);
            }
        } else {
            int[] vector = new int[AOR_plus(subVector.length, 1, "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1527, _mut1528, _mut1529, _mut1530)];
            System.arraycopy(subVector, 0, vector, 0, subVector.length);
            if (ROR_equals(subVector.length, d, "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1531, _mut1532, _mut1533, _mut1534, _mut1535)) {
                // then an fft will be applied along the dimension d.
                vector[d] = 0;
                mdfft(mdcm, type, d, vector);
            } else {
                for (int i = 0; ROR_less(i, dimensionSize[subVector.length], "org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457", _mut1536, _mut1537, _mut1538, _mut1539, _mut1540); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.mdfft_457");
                    vector[subVector.length] = i;
                    // further split along the next dimension
                    mdfft(mdcm, type, d, vector);
                }
            }
        }
    }

    /**
     * Complex matrix implementation. Not designed for synchronized access may
     * eventually be replaced by jsr-83 of the java community process
     * http://jcp.org/en/jsr/detail?id=83
     * may require additional exception throws for other basic requirements.
     *
     * @deprecated see MATH-736
     */
    @Deprecated
    private static class MultiDimensionalComplexMatrix implements Cloneable {

        /**
         * Size in all dimensions.
         */
        protected int[] dimensionSize;

        /**
         * Storage array.
         */
        protected Object multiDimensionalComplexArray;

        /**
         * Simple constructor.
         *
         * @param multiDimensionalComplexArray array containing the matrix
         * elements
         */
        MultiDimensionalComplexMatrix(Object multiDimensionalComplexArray) {
            this.multiDimensionalComplexArray = multiDimensionalComplexArray;
            // count dimensions
            int numOfDimensions = 0;
            for (Object lastDimension = multiDimensionalComplexArray; lastDimension instanceof Object[]; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.MultiDimensionalComplexMatrix_519");
                final Object[] array = (Object[]) lastDimension;
                numOfDimensions++;
                lastDimension = array[0];
            }
            // allocate array with exact count
            dimensionSize = new int[numOfDimensions];
            // fill array
            numOfDimensions = 0;
            for (Object lastDimension = multiDimensionalComplexArray; lastDimension instanceof Object[]; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.MultiDimensionalComplexMatrix_519");
                final Object[] array = (Object[]) lastDimension;
                dimensionSize[numOfDimensions++] = array.length;
                lastDimension = array[0];
            }
        }

        /**
         * Get a matrix element.
         *
         * @param vector indices of the element
         * @return matrix element
         * @exception DimensionMismatchException if dimensions do not match
         */
        public Complex get(int... vector) throws DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.get_553");
            if (vector == null) {
                if (ROR_greater(dimensionSize.length, 0, "org.apache.commons.math3.transform.FastFourierTransformer.get_553", _mut1551, _mut1552, _mut1553, _mut1554, _mut1555)) {
                    throw new DimensionMismatchException(0, dimensionSize.length);
                }
                return null;
            }
            if (ROR_not_equals(vector.length, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.get_553", _mut1556, _mut1557, _mut1558, _mut1559, _mut1560)) {
                throw new DimensionMismatchException(vector.length, dimensionSize.length);
            }
            Object lastDimension = multiDimensionalComplexArray;
            for (int i = 0; ROR_less(i, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.get_553", _mut1561, _mut1562, _mut1563, _mut1564, _mut1565); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.get_553");
                lastDimension = ((Object[]) lastDimension)[vector[i]];
            }
            return (Complex) lastDimension;
        }

        /**
         * Set a matrix element.
         *
         * @param magnitude magnitude of the element
         * @param vector indices of the element
         * @return the previous value
         * @exception DimensionMismatchException if dimensions do not match
         */
        public Complex set(Complex magnitude, int... vector) throws DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.set_586");
            if (vector == null) {
                if (ROR_greater(dimensionSize.length, 0, "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1566, _mut1567, _mut1568, _mut1569, _mut1570)) {
                    throw new DimensionMismatchException(0, dimensionSize.length);
                }
                return null;
            }
            if (ROR_not_equals(vector.length, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1571, _mut1572, _mut1573, _mut1574, _mut1575)) {
                throw new DimensionMismatchException(vector.length, dimensionSize.length);
            }
            Object[] lastDimension = (Object[]) multiDimensionalComplexArray;
            for (int i = 0; ROR_less(i, AOR_minus(dimensionSize.length, 1, "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1576, _mut1577, _mut1578, _mut1579), "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1580, _mut1581, _mut1582, _mut1583, _mut1584); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.set_586");
                lastDimension = (Object[]) lastDimension[vector[i]];
            }
            Complex lastValue = (Complex) lastDimension[vector[AOR_minus(dimensionSize.length, 1, "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1585, _mut1586, _mut1587, _mut1588)]];
            lastDimension[vector[AOR_minus(dimensionSize.length, 1, "org.apache.commons.math3.transform.FastFourierTransformer.set_586", _mut1589, _mut1590, _mut1591, _mut1592)]] = magnitude;
            return lastValue;
        }

        /**
         * Get the size in all dimensions.
         *
         * @return size in all dimensions
         */
        public int[] getDimensionSizes() {
            return dimensionSize.clone();
        }

        /**
         * Get the underlying storage array.
         *
         * @return underlying storage array
         */
        public Object getArray() {
            return multiDimensionalComplexArray;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object clone() {
            MultiDimensionalComplexMatrix mdcm = new MultiDimensionalComplexMatrix(Array.newInstance(Complex.class, dimensionSize));
            clone(mdcm);
            return mdcm;
        }

        /**
         * Copy contents of current array into mdcm.
         *
         * @param mdcm array where to copy data
         */
        private void clone(MultiDimensionalComplexMatrix mdcm) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.clone_647");
            int[] vector = new int[dimensionSize.length];
            int size = 1;
            for (int i = 0; ROR_less(i, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.clone_647", _mut1593, _mut1594, _mut1595, _mut1596, _mut1597); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.clone_647");
                size *= dimensionSize[i];
            }
            int[][] vectorList = new int[size][dimensionSize.length];
            for (int[] nextVector : vectorList) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.clone_647");
                System.arraycopy(vector, 0, nextVector, 0, dimensionSize.length);
                for (int i = 0; ROR_less(i, dimensionSize.length, "org.apache.commons.math3.transform.FastFourierTransformer.clone_647", _mut1603, _mut1604, _mut1605, _mut1606, _mut1607); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.clone_647");
                    vector[i]++;
                    if (ROR_less(vector[i], dimensionSize[i], "org.apache.commons.math3.transform.FastFourierTransformer.clone_647", _mut1598, _mut1599, _mut1600, _mut1601, _mut1602)) {
                        break;
                    } else {
                        vector[i] = 0;
                    }
                }
            }
            for (int[] nextVector : vectorList) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastFourierTransformer.clone_647");
                mdcm.set(get(nextVector), nextVector);
            }
        }
    }
}
