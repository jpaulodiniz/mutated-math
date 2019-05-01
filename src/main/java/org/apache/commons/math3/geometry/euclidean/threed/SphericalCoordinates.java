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
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class provides conversions related to <a
 * href="http://mathworld.wolfram.com/SphericalCoordinates.html">spherical coordinates</a>.
 * <p>
 * The conventions used here are the mathematical ones, i.e. spherical coordinates are
 * related to Cartesian coordinates as follows:
 * </p>
 * <ul>
 *   <li>x = r cos(&theta;) sin(&Phi;)</li>
 *   <li>y = r sin(&theta;) sin(&Phi;)</li>
 *   <li>z = r cos(&Phi;)</li>
 * </ul>
 * <ul>
 *   <li>r       = &radic;(x<sup>2</sup>+y<sup>2</sup>+z<sup>2</sup>)</li>
 *   <li>&theta; = atan2(y, x)</li>
 *   <li>&Phi;   = acos(z/r)</li>
 * </ul>
 * <p>
 * r is the radius, &theta; is the azimuthal angle in the x-y plane and &Phi; is the polar
 * (co-latitude) angle. These conventions are <em>different</em> from the conventions used
 * in physics (and in particular in spherical harmonics) where the meanings of &theta; and
 * &Phi; are reversed.
 * </p>
 * <p>
 * This class provides conversion of coordinates and also of gradient and Hessian
 * between spherical and Cartesian coordinates.
 * </p>
 * @since 3.2
 */
public class SphericalCoordinates implements Serializable {

    @Conditional
    public static boolean _mut80018 = false, _mut80019 = false, _mut80020 = false, _mut80021 = false, _mut80022 = false, _mut80023 = false, _mut80024 = false, _mut80025 = false, _mut80026 = false, _mut80027 = false, _mut80028 = false, _mut80029 = false, _mut80030 = false, _mut80031 = false, _mut80032 = false, _mut80033 = false, _mut80034 = false, _mut80035 = false, _mut80036 = false, _mut80037 = false, _mut80038 = false, _mut80039 = false, _mut80040 = false, _mut80041 = false, _mut80042 = false, _mut80043 = false, _mut80044 = false, _mut80045 = false, _mut80046 = false, _mut80047 = false, _mut80048 = false, _mut80049 = false, _mut80050 = false, _mut80051 = false, _mut80052 = false, _mut80053 = false, _mut80054 = false, _mut80055 = false, _mut80056 = false, _mut80057 = false, _mut80058 = false, _mut80059 = false, _mut80060 = false, _mut80061 = false, _mut80062 = false, _mut80063 = false, _mut80064 = false, _mut80065 = false, _mut80066 = false, _mut80067 = false, _mut80068 = false, _mut80069 = false, _mut80070 = false, _mut80071 = false, _mut80072 = false, _mut80073 = false, _mut80074 = false, _mut80075 = false, _mut80076 = false, _mut80077 = false, _mut80078 = false, _mut80079 = false, _mut80080 = false, _mut80081 = false, _mut80082 = false, _mut80083 = false, _mut80084 = false, _mut80085 = false, _mut80086 = false, _mut80087 = false, _mut80088 = false, _mut80089 = false, _mut80090 = false, _mut80091 = false, _mut80092 = false, _mut80093 = false, _mut80094 = false, _mut80095 = false, _mut80096 = false, _mut80097 = false, _mut80098 = false, _mut80099 = false, _mut80100 = false, _mut80101 = false, _mut80102 = false, _mut80103 = false, _mut80104 = false, _mut80105 = false, _mut80106 = false, _mut80107 = false, _mut80108 = false, _mut80109 = false, _mut80110 = false, _mut80111 = false, _mut80112 = false, _mut80113 = false, _mut80114 = false, _mut80115 = false, _mut80116 = false, _mut80117 = false, _mut80118 = false, _mut80119 = false, _mut80120 = false, _mut80121 = false, _mut80122 = false, _mut80123 = false, _mut80124 = false, _mut80125 = false, _mut80126 = false, _mut80127 = false, _mut80128 = false, _mut80129 = false, _mut80130 = false, _mut80131 = false, _mut80132 = false, _mut80133 = false, _mut80134 = false, _mut80135 = false, _mut80136 = false, _mut80137 = false, _mut80138 = false, _mut80139 = false, _mut80140 = false, _mut80141 = false, _mut80142 = false, _mut80143 = false, _mut80144 = false, _mut80145 = false, _mut80146 = false, _mut80147 = false, _mut80148 = false, _mut80149 = false, _mut80150 = false, _mut80151 = false, _mut80152 = false, _mut80153 = false, _mut80154 = false, _mut80155 = false, _mut80156 = false, _mut80157 = false, _mut80158 = false, _mut80159 = false, _mut80160 = false, _mut80161 = false, _mut80162 = false, _mut80163 = false, _mut80164 = false, _mut80165 = false, _mut80166 = false, _mut80167 = false, _mut80168 = false, _mut80169 = false, _mut80170 = false, _mut80171 = false, _mut80172 = false, _mut80173 = false, _mut80174 = false, _mut80175 = false, _mut80176 = false, _mut80177 = false, _mut80178 = false, _mut80179 = false, _mut80180 = false, _mut80181 = false, _mut80182 = false, _mut80183 = false, _mut80184 = false, _mut80185 = false, _mut80186 = false, _mut80187 = false, _mut80188 = false, _mut80189 = false, _mut80190 = false, _mut80191 = false, _mut80192 = false, _mut80193 = false, _mut80194 = false, _mut80195 = false, _mut80196 = false, _mut80197 = false, _mut80198 = false, _mut80199 = false, _mut80200 = false, _mut80201 = false, _mut80202 = false, _mut80203 = false, _mut80204 = false, _mut80205 = false, _mut80206 = false, _mut80207 = false, _mut80208 = false, _mut80209 = false, _mut80210 = false, _mut80211 = false, _mut80212 = false, _mut80213 = false, _mut80214 = false, _mut80215 = false, _mut80216 = false, _mut80217 = false, _mut80218 = false, _mut80219 = false, _mut80220 = false, _mut80221 = false, _mut80222 = false, _mut80223 = false, _mut80224 = false, _mut80225 = false, _mut80226 = false, _mut80227 = false, _mut80228 = false, _mut80229 = false, _mut80230 = false, _mut80231 = false, _mut80232 = false, _mut80233 = false, _mut80234 = false, _mut80235 = false, _mut80236 = false, _mut80237 = false, _mut80238 = false, _mut80239 = false, _mut80240 = false, _mut80241 = false, _mut80242 = false, _mut80243 = false, _mut80244 = false, _mut80245 = false, _mut80246 = false, _mut80247 = false, _mut80248 = false, _mut80249 = false, _mut80250 = false, _mut80251 = false, _mut80252 = false, _mut80253 = false, _mut80254 = false, _mut80255 = false, _mut80256 = false, _mut80257 = false, _mut80258 = false, _mut80259 = false, _mut80260 = false, _mut80261 = false, _mut80262 = false, _mut80263 = false, _mut80264 = false, _mut80265 = false, _mut80266 = false, _mut80267 = false, _mut80268 = false, _mut80269 = false, _mut80270 = false, _mut80271 = false, _mut80272 = false, _mut80273 = false, _mut80274 = false, _mut80275 = false, _mut80276 = false, _mut80277 = false, _mut80278 = false, _mut80279 = false, _mut80280 = false, _mut80281 = false, _mut80282 = false, _mut80283 = false, _mut80284 = false, _mut80285 = false, _mut80286 = false, _mut80287 = false, _mut80288 = false, _mut80289 = false, _mut80290 = false, _mut80291 = false, _mut80292 = false, _mut80293 = false, _mut80294 = false, _mut80295 = false, _mut80296 = false, _mut80297 = false, _mut80298 = false, _mut80299 = false, _mut80300 = false, _mut80301 = false, _mut80302 = false, _mut80303 = false, _mut80304 = false, _mut80305 = false, _mut80306 = false, _mut80307 = false, _mut80308 = false, _mut80309 = false, _mut80310 = false, _mut80311 = false, _mut80312 = false, _mut80313 = false, _mut80314 = false, _mut80315 = false, _mut80316 = false, _mut80317 = false, _mut80318 = false, _mut80319 = false, _mut80320 = false, _mut80321 = false, _mut80322 = false, _mut80323 = false, _mut80324 = false, _mut80325 = false, _mut80326 = false, _mut80327 = false, _mut80328 = false, _mut80329 = false, _mut80330 = false, _mut80331 = false, _mut80332 = false, _mut80333 = false, _mut80334 = false, _mut80335 = false, _mut80336 = false, _mut80337 = false, _mut80338 = false, _mut80339 = false, _mut80340 = false, _mut80341 = false, _mut80342 = false, _mut80343 = false, _mut80344 = false, _mut80345 = false, _mut80346 = false, _mut80347 = false, _mut80348 = false, _mut80349 = false, _mut80350 = false, _mut80351 = false, _mut80352 = false, _mut80353 = false, _mut80354 = false, _mut80355 = false, _mut80356 = false, _mut80357 = false, _mut80358 = false, _mut80359 = false, _mut80360 = false, _mut80361 = false, _mut80362 = false, _mut80363 = false, _mut80364 = false, _mut80365 = false, _mut80366 = false, _mut80367 = false, _mut80368 = false, _mut80369 = false, _mut80370 = false, _mut80371 = false, _mut80372 = false, _mut80373 = false, _mut80374 = false, _mut80375 = false, _mut80376 = false, _mut80377 = false, _mut80378 = false, _mut80379 = false, _mut80380 = false, _mut80381 = false, _mut80382 = false, _mut80383 = false, _mut80384 = false, _mut80385 = false, _mut80386 = false, _mut80387 = false, _mut80388 = false, _mut80389 = false, _mut80390 = false, _mut80391 = false, _mut80392 = false, _mut80393 = false, _mut80394 = false, _mut80395 = false, _mut80396 = false, _mut80397 = false, _mut80398 = false, _mut80399 = false, _mut80400 = false, _mut80401 = false, _mut80402 = false, _mut80403 = false, _mut80404 = false, _mut80405 = false, _mut80406 = false, _mut80407 = false, _mut80408 = false, _mut80409 = false, _mut80410 = false, _mut80411 = false, _mut80412 = false, _mut80413 = false, _mut80414 = false, _mut80415 = false, _mut80416 = false, _mut80417 = false, _mut80418 = false, _mut80419 = false, _mut80420 = false, _mut80421 = false, _mut80422 = false, _mut80423 = false, _mut80424 = false, _mut80425 = false, _mut80426 = false, _mut80427 = false, _mut80428 = false, _mut80429 = false, _mut80430 = false, _mut80431 = false, _mut80432 = false, _mut80433 = false, _mut80434 = false, _mut80435 = false, _mut80436 = false, _mut80437 = false, _mut80438 = false, _mut80439 = false, _mut80440 = false, _mut80441 = false, _mut80442 = false, _mut80443 = false, _mut80444 = false, _mut80445 = false, _mut80446 = false, _mut80447 = false, _mut80448 = false, _mut80449 = false, _mut80450 = false, _mut80451 = false, _mut80452 = false, _mut80453 = false, _mut80454 = false, _mut80455 = false, _mut80456 = false, _mut80457 = false, _mut80458 = false, _mut80459 = false, _mut80460 = false, _mut80461 = false, _mut80462 = false, _mut80463 = false, _mut80464 = false, _mut80465 = false, _mut80466 = false, _mut80467 = false, _mut80468 = false, _mut80469 = false, _mut80470 = false, _mut80471 = false, _mut80472 = false, _mut80473 = false, _mut80474 = false, _mut80475 = false, _mut80476 = false, _mut80477 = false, _mut80478 = false, _mut80479 = false, _mut80480 = false, _mut80481 = false, _mut80482 = false, _mut80483 = false, _mut80484 = false, _mut80485 = false, _mut80486 = false, _mut80487 = false, _mut80488 = false, _mut80489 = false, _mut80490 = false, _mut80491 = false, _mut80492 = false, _mut80493 = false, _mut80494 = false, _mut80495 = false, _mut80496 = false, _mut80497 = false, _mut80498 = false, _mut80499 = false, _mut80500 = false, _mut80501 = false, _mut80502 = false, _mut80503 = false, _mut80504 = false, _mut80505 = false, _mut80506 = false, _mut80507 = false, _mut80508 = false, _mut80509 = false, _mut80510 = false, _mut80511 = false, _mut80512 = false, _mut80513 = false, _mut80514 = false, _mut80515 = false, _mut80516 = false, _mut80517 = false, _mut80518 = false, _mut80519 = false, _mut80520 = false, _mut80521 = false, _mut80522 = false, _mut80523 = false, _mut80524 = false, _mut80525 = false, _mut80526 = false, _mut80527 = false, _mut80528 = false, _mut80529 = false, _mut80530 = false, _mut80531 = false, _mut80532 = false, _mut80533 = false, _mut80534 = false, _mut80535 = false, _mut80536 = false, _mut80537 = false, _mut80538 = false, _mut80539 = false, _mut80540 = false, _mut80541 = false, _mut80542 = false, _mut80543 = false, _mut80544 = false, _mut80545 = false, _mut80546 = false, _mut80547 = false, _mut80548 = false, _mut80549 = false, _mut80550 = false, _mut80551 = false, _mut80552 = false, _mut80553 = false, _mut80554 = false, _mut80555 = false, _mut80556 = false, _mut80557 = false, _mut80558 = false, _mut80559 = false, _mut80560 = false, _mut80561 = false, _mut80562 = false, _mut80563 = false, _mut80564 = false, _mut80565 = false, _mut80566 = false, _mut80567 = false, _mut80568 = false, _mut80569 = false, _mut80570 = false, _mut80571 = false, _mut80572 = false, _mut80573 = false, _mut80574 = false, _mut80575 = false, _mut80576 = false, _mut80577 = false, _mut80578 = false, _mut80579 = false, _mut80580 = false, _mut80581 = false, _mut80582 = false, _mut80583 = false, _mut80584 = false, _mut80585 = false, _mut80586 = false, _mut80587 = false, _mut80588 = false, _mut80589 = false, _mut80590 = false, _mut80591 = false, _mut80592 = false, _mut80593 = false, _mut80594 = false, _mut80595 = false, _mut80596 = false, _mut80597 = false, _mut80598 = false, _mut80599 = false, _mut80600 = false, _mut80601 = false, _mut80602 = false, _mut80603 = false, _mut80604 = false, _mut80605 = false, _mut80606 = false, _mut80607 = false, _mut80608 = false, _mut80609 = false, _mut80610 = false, _mut80611 = false, _mut80612 = false, _mut80613 = false, _mut80614 = false, _mut80615 = false, _mut80616 = false, _mut80617 = false, _mut80618 = false, _mut80619 = false, _mut80620 = false, _mut80621 = false, _mut80622 = false, _mut80623 = false, _mut80624 = false, _mut80625 = false, _mut80626 = false, _mut80627 = false, _mut80628 = false, _mut80629 = false, _mut80630 = false, _mut80631 = false, _mut80632 = false, _mut80633 = false, _mut80634 = false, _mut80635 = false, _mut80636 = false, _mut80637 = false, _mut80638 = false, _mut80639 = false, _mut80640 = false, _mut80641 = false, _mut80642 = false, _mut80643 = false, _mut80644 = false, _mut80645 = false, _mut80646 = false, _mut80647 = false, _mut80648 = false, _mut80649 = false, _mut80650 = false, _mut80651 = false, _mut80652 = false, _mut80653 = false, _mut80654 = false, _mut80655 = false, _mut80656 = false, _mut80657 = false, _mut80658 = false, _mut80659 = false, _mut80660 = false, _mut80661 = false, _mut80662 = false, _mut80663 = false, _mut80664 = false, _mut80665 = false, _mut80666 = false, _mut80667 = false, _mut80668 = false, _mut80669 = false, _mut80670 = false, _mut80671 = false, _mut80672 = false, _mut80673 = false, _mut80674 = false, _mut80675 = false, _mut80676 = false, _mut80677 = false, _mut80678 = false, _mut80679 = false, _mut80680 = false, _mut80681 = false, _mut80682 = false, _mut80683 = false, _mut80684 = false, _mut80685 = false, _mut80686 = false, _mut80687 = false, _mut80688 = false, _mut80689 = false, _mut80690 = false, _mut80691 = false, _mut80692 = false, _mut80693 = false, _mut80694 = false, _mut80695 = false, _mut80696 = false, _mut80697 = false, _mut80698 = false, _mut80699 = false, _mut80700 = false, _mut80701 = false, _mut80702 = false, _mut80703 = false, _mut80704 = false, _mut80705 = false, _mut80706 = false, _mut80707 = false, _mut80708 = false, _mut80709 = false, _mut80710 = false, _mut80711 = false, _mut80712 = false, _mut80713 = false, _mut80714 = false, _mut80715 = false, _mut80716 = false, _mut80717 = false, _mut80718 = false, _mut80719 = false, _mut80720 = false, _mut80721 = false, _mut80722 = false, _mut80723 = false, _mut80724 = false, _mut80725 = false, _mut80726 = false, _mut80727 = false, _mut80728 = false, _mut80729 = false, _mut80730 = false, _mut80731 = false, _mut80732 = false, _mut80733 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20130206L;

    /**
     * Cartesian coordinates.
     */
    private final Vector3D v;

    /**
     * Radius.
     */
    private final double r;

    /**
     * Azimuthal angle in the x-y plane &theta;.
     */
    private final double theta;

    /**
     * Polar angle (co-latitude) &Phi;.
     */
    private final double phi;

    /**
     * Jacobian of (r, &theta; &Phi).
     */
    private double[][] jacobian;

    /**
     * Hessian of radius.
     */
    private double[][] rHessian;

    /**
     * Hessian of azimuthal angle in the x-y plane &theta;.
     */
    private double[][] thetaHessian;

    /**
     * Hessian of polar (co-latitude) angle &Phi;.
     */
    private double[][] phiHessian;

    /**
     * Build a spherical coordinates transformer from Cartesian coordinates.
     * @param v Cartesian coordinates
     */
    public SphericalCoordinates(final Vector3D v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_84");
        // Cartesian coordinates
        this.v = v;
        // remaining spherical coordinates
        this.r = v.getNorm();
        this.theta = v.getAlpha();
        this.phi = FastMath.acos(AOR_divide(v.getZ(), r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_84", _mut80018, _mut80019, _mut80020, _mut80021));
    }

    /**
     * Build a spherical coordinates transformer from spherical coordinates.
     * @param r radius
     * @param theta azimuthal angle in x-y plane
     * @param phi polar (co-latitude) angle
     */
    public SphericalCoordinates(final double r, final double theta, final double phi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101");
        final double cosTheta = FastMath.cos(theta);
        final double sinTheta = FastMath.sin(theta);
        final double cosPhi = FastMath.cos(phi);
        final double sinPhi = FastMath.sin(phi);
        // spherical coordinates
        this.r = r;
        this.theta = theta;
        this.phi = phi;
        // Cartesian coordinates
        this.v = new Vector3D(AOR_multiply(AOR_multiply(r, cosTheta, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101", _mut80022, _mut80023, _mut80024, _mut80025), sinPhi, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101", _mut80026, _mut80027, _mut80028, _mut80029), AOR_multiply(AOR_multiply(r, sinTheta, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101", _mut80030, _mut80031, _mut80032, _mut80033), sinPhi, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101", _mut80034, _mut80035, _mut80036, _mut80037), AOR_multiply(r, cosPhi, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.SphericalCoordinates_101", _mut80038, _mut80039, _mut80040, _mut80041));
    }

    /**
     * Get the Cartesian coordinates.
     * @return Cartesian coordinates
     */
    public Vector3D getCartesian() {
        return v;
    }

    /**
     * Get the radius.
     * @return radius r
     * @see #getTheta()
     * @see #getPhi()
     */
    public double getR() {
        return r;
    }

    /**
     * Get the azimuthal angle in x-y plane.
     * @return azimuthal angle in x-y plane &theta;
     * @see #getR()
     * @see #getPhi()
     */
    public double getTheta() {
        return theta;
    }

    /**
     * Get the polar (co-latitude) angle.
     * @return polar (co-latitude) angle &Phi;
     * @see #getR()
     * @see #getTheta()
     */
    public double getPhi() {
        return phi;
    }

    /**
     * Convert a gradient with respect to spherical coordinates into a gradient
     * with respect to Cartesian coordinates.
     * @param sGradient gradient with respect to spherical coordinates
     * {df/dr, df/d&theta;, df/d&Phi;}
     * @return gradient with respect to Cartesian coordinates
     * {df/dx, df/dy, df/dz}
     */
    public double[] toCartesianGradient(final double[] sGradient) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161");
        // lazy evaluation of Jacobian
        computeJacobian();
        // the expressions have been simplified since we know jacobian[1][2] = dTheta/dZ = 0
        return new double[] { AOR_plus(AOR_plus(AOR_multiply(sGradient[0], jacobian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80042, _mut80043, _mut80044, _mut80045), AOR_multiply(sGradient[1], jacobian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80046, _mut80047, _mut80048, _mut80049), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80050, _mut80051, _mut80052, _mut80053), AOR_multiply(sGradient[2], jacobian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80054, _mut80055, _mut80056, _mut80057), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80058, _mut80059, _mut80060, _mut80061), AOR_plus(AOR_plus(AOR_multiply(sGradient[0], jacobian[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80062, _mut80063, _mut80064, _mut80065), AOR_multiply(sGradient[1], jacobian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80066, _mut80067, _mut80068, _mut80069), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80070, _mut80071, _mut80072, _mut80073), AOR_multiply(sGradient[2], jacobian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80074, _mut80075, _mut80076, _mut80077), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80078, _mut80079, _mut80080, _mut80081), AOR_plus(AOR_multiply(sGradient[0], jacobian[0][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80082, _mut80083, _mut80084, _mut80085), AOR_multiply(sGradient[2], jacobian[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80086, _mut80087, _mut80088, _mut80089), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianGradient_161", _mut80090, _mut80091, _mut80092, _mut80093) };
    }

    /**
     * Convert a Hessian with respect to spherical coordinates into a Hessian
     * with respect to Cartesian coordinates.
     * <p>
     * As Hessian are always symmetric, we use only the lower left part of the provided
     * spherical Hessian, so the upper part may not be initialized. However, we still
     * do fill up the complete array we create, with guaranteed symmetry.
     * </p>
     * @param sHessian Hessian with respect to spherical coordinates
     * {{d<sup>2</sup>f/dr<sup>2</sup>, d<sup>2</sup>f/drd&theta;, d<sup>2</sup>f/drd&Phi;},
     *  {d<sup>2</sup>f/drd&theta;, d<sup>2</sup>f/d&theta;<sup>2</sup>, d<sup>2</sup>f/d&theta;d&Phi;},
     *  {d<sup>2</sup>f/drd&Phi;, d<sup>2</sup>f/d&theta;d&Phi;, d<sup>2</sup>f/d&Phi;<sup>2</sup>}
     * @param sGradient gradient with respect to spherical coordinates
     * {df/dr, df/d&theta;, df/d&Phi;}
     * @return Hessian with respect to Cartesian coordinates
     * {{d<sup>2</sup>f/dx<sup>2</sup>, d<sup>2</sup>f/dxdy, d<sup>2</sup>f/dxdz},
     *  {d<sup>2</sup>f/dxdy, d<sup>2</sup>f/dy<sup>2</sup>, d<sup>2</sup>f/dydz},
     *  {d<sup>2</sup>f/dxdz, d<sup>2</sup>f/dydz, d<sup>2</sup>f/dz<sup>2</sup>}}
     */
    public double[][] toCartesianHessian(final double[][] sHessian, final double[] sGradient) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194");
        computeJacobian();
        computeHessians();
        // and H_theta is only a 2x2 matrix as it does not depend on z
        final double[][] hj = new double[3][3];
        final double[][] cHessian = new double[3][3];
        // beware we use ONLY the lower-left part of sHessian
        hj[0][0] = AOR_plus(AOR_plus(AOR_multiply(sHessian[0][0], jacobian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80094, _mut80095, _mut80096, _mut80097), AOR_multiply(sHessian[1][0], jacobian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80098, _mut80099, _mut80100, _mut80101), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80102, _mut80103, _mut80104, _mut80105), AOR_multiply(sHessian[2][0], jacobian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80106, _mut80107, _mut80108, _mut80109), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80110, _mut80111, _mut80112, _mut80113);
        hj[0][1] = AOR_plus(AOR_plus(AOR_multiply(sHessian[0][0], jacobian[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80114, _mut80115, _mut80116, _mut80117), AOR_multiply(sHessian[1][0], jacobian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80118, _mut80119, _mut80120, _mut80121), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80122, _mut80123, _mut80124, _mut80125), AOR_multiply(sHessian[2][0], jacobian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80126, _mut80127, _mut80128, _mut80129), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80130, _mut80131, _mut80132, _mut80133);
        hj[0][2] = AOR_plus(AOR_multiply(sHessian[0][0], jacobian[0][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80134, _mut80135, _mut80136, _mut80137), AOR_multiply(sHessian[2][0], jacobian[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80138, _mut80139, _mut80140, _mut80141), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80142, _mut80143, _mut80144, _mut80145);
        hj[1][0] = AOR_plus(AOR_plus(AOR_multiply(sHessian[1][0], jacobian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80146, _mut80147, _mut80148, _mut80149), AOR_multiply(sHessian[1][1], jacobian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80150, _mut80151, _mut80152, _mut80153), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80154, _mut80155, _mut80156, _mut80157), AOR_multiply(sHessian[2][1], jacobian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80158, _mut80159, _mut80160, _mut80161), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80162, _mut80163, _mut80164, _mut80165);
        hj[1][1] = AOR_plus(AOR_plus(AOR_multiply(sHessian[1][0], jacobian[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80166, _mut80167, _mut80168, _mut80169), AOR_multiply(sHessian[1][1], jacobian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80170, _mut80171, _mut80172, _mut80173), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80174, _mut80175, _mut80176, _mut80177), AOR_multiply(sHessian[2][1], jacobian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80178, _mut80179, _mut80180, _mut80181), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80182, _mut80183, _mut80184, _mut80185);
        // don't compute hj[1][2] as it is not used below
        hj[2][0] = AOR_plus(AOR_plus(AOR_multiply(sHessian[2][0], jacobian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80186, _mut80187, _mut80188, _mut80189), AOR_multiply(sHessian[2][1], jacobian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80190, _mut80191, _mut80192, _mut80193), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80194, _mut80195, _mut80196, _mut80197), AOR_multiply(sHessian[2][2], jacobian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80198, _mut80199, _mut80200, _mut80201), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80202, _mut80203, _mut80204, _mut80205);
        hj[2][1] = AOR_plus(AOR_plus(AOR_multiply(sHessian[2][0], jacobian[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80206, _mut80207, _mut80208, _mut80209), AOR_multiply(sHessian[2][1], jacobian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80210, _mut80211, _mut80212, _mut80213), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80214, _mut80215, _mut80216, _mut80217), AOR_multiply(sHessian[2][2], jacobian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80218, _mut80219, _mut80220, _mut80221), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80222, _mut80223, _mut80224, _mut80225);
        hj[2][2] = AOR_plus(AOR_multiply(sHessian[2][0], jacobian[0][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80226, _mut80227, _mut80228, _mut80229), AOR_multiply(sHessian[2][2], jacobian[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80230, _mut80231, _mut80232, _mut80233), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80234, _mut80235, _mut80236, _mut80237);
        // compute lower-left part of J^T . H_f . J
        cHessian[0][0] = AOR_plus(AOR_plus(AOR_multiply(jacobian[0][0], hj[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80238, _mut80239, _mut80240, _mut80241), AOR_multiply(jacobian[1][0], hj[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80242, _mut80243, _mut80244, _mut80245), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80246, _mut80247, _mut80248, _mut80249), AOR_multiply(jacobian[2][0], hj[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80250, _mut80251, _mut80252, _mut80253), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80254, _mut80255, _mut80256, _mut80257);
        cHessian[1][0] = AOR_plus(AOR_plus(AOR_multiply(jacobian[0][1], hj[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80258, _mut80259, _mut80260, _mut80261), AOR_multiply(jacobian[1][1], hj[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80262, _mut80263, _mut80264, _mut80265), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80266, _mut80267, _mut80268, _mut80269), AOR_multiply(jacobian[2][1], hj[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80270, _mut80271, _mut80272, _mut80273), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80274, _mut80275, _mut80276, _mut80277);
        cHessian[2][0] = AOR_plus(AOR_multiply(jacobian[0][2], hj[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80278, _mut80279, _mut80280, _mut80281), AOR_multiply(jacobian[2][2], hj[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80282, _mut80283, _mut80284, _mut80285), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80286, _mut80287, _mut80288, _mut80289);
        cHessian[1][1] = AOR_plus(AOR_plus(AOR_multiply(jacobian[0][1], hj[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80290, _mut80291, _mut80292, _mut80293), AOR_multiply(jacobian[1][1], hj[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80294, _mut80295, _mut80296, _mut80297), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80298, _mut80299, _mut80300, _mut80301), AOR_multiply(jacobian[2][1], hj[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80302, _mut80303, _mut80304, _mut80305), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80306, _mut80307, _mut80308, _mut80309);
        cHessian[2][1] = AOR_plus(AOR_multiply(jacobian[0][2], hj[0][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80310, _mut80311, _mut80312, _mut80313), AOR_multiply(jacobian[2][2], hj[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80314, _mut80315, _mut80316, _mut80317), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80318, _mut80319, _mut80320, _mut80321);
        cHessian[2][2] = AOR_plus(AOR_multiply(jacobian[0][2], hj[0][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80322, _mut80323, _mut80324, _mut80325), AOR_multiply(jacobian[2][2], hj[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80326, _mut80327, _mut80328, _mut80329), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80330, _mut80331, _mut80332, _mut80333);
        // add gradient contribution
        cHessian[0][0] += AOR_plus(AOR_plus(AOR_multiply(sGradient[0], rHessian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80334, _mut80335, _mut80336, _mut80337), AOR_multiply(sGradient[1], thetaHessian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80338, _mut80339, _mut80340, _mut80341), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80342, _mut80343, _mut80344, _mut80345), AOR_multiply(sGradient[2], phiHessian[0][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80346, _mut80347, _mut80348, _mut80349), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80350, _mut80351, _mut80352, _mut80353);
        cHessian[1][0] += AOR_plus(AOR_plus(AOR_multiply(sGradient[0], rHessian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80354, _mut80355, _mut80356, _mut80357), AOR_multiply(sGradient[1], thetaHessian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80358, _mut80359, _mut80360, _mut80361), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80362, _mut80363, _mut80364, _mut80365), AOR_multiply(sGradient[2], phiHessian[1][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80366, _mut80367, _mut80368, _mut80369), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80370, _mut80371, _mut80372, _mut80373);
        cHessian[2][0] += AOR_plus(AOR_multiply(sGradient[0], rHessian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80374, _mut80375, _mut80376, _mut80377), AOR_multiply(sGradient[2], phiHessian[2][0], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80378, _mut80379, _mut80380, _mut80381), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80382, _mut80383, _mut80384, _mut80385);
        cHessian[1][1] += AOR_plus(AOR_plus(AOR_multiply(sGradient[0], rHessian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80386, _mut80387, _mut80388, _mut80389), AOR_multiply(sGradient[1], thetaHessian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80390, _mut80391, _mut80392, _mut80393), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80394, _mut80395, _mut80396, _mut80397), AOR_multiply(sGradient[2], phiHessian[1][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80398, _mut80399, _mut80400, _mut80401), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80402, _mut80403, _mut80404, _mut80405);
        cHessian[2][1] += AOR_plus(AOR_multiply(sGradient[0], rHessian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80406, _mut80407, _mut80408, _mut80409), AOR_multiply(sGradient[2], phiHessian[2][1], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80410, _mut80411, _mut80412, _mut80413), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80414, _mut80415, _mut80416, _mut80417);
        cHessian[2][2] += AOR_plus(AOR_multiply(sGradient[0], rHessian[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80418, _mut80419, _mut80420, _mut80421), AOR_multiply(sGradient[2], phiHessian[2][2], "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80422, _mut80423, _mut80424, _mut80425), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.toCartesianHessian_194", _mut80426, _mut80427, _mut80428, _mut80429);
        // ensure symmetry
        cHessian[0][1] = cHessian[1][0];
        cHessian[0][2] = cHessian[2][0];
        cHessian[1][2] = cHessian[2][1];
        return cHessian;
    }

    /**
     * Lazy evaluation of (r, &theta;, &phi;) Jacobian.
     */
    private void computeJacobian() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244");
        if (jacobian == null) {
            // intermediate variables
            final double x = v.getX();
            final double y = v.getY();
            final double z = v.getZ();
            final double rho2 = AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80430, _mut80431, _mut80432, _mut80433), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80434, _mut80435, _mut80436, _mut80437), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80438, _mut80439, _mut80440, _mut80441);
            final double rho = FastMath.sqrt(rho2);
            final double r2 = AOR_plus(rho2, AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80442, _mut80443, _mut80444, _mut80445), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80446, _mut80447, _mut80448, _mut80449);
            jacobian = new double[3][3];
            // row representing the gradient of r
            jacobian[0][0] = AOR_divide(x, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80450, _mut80451, _mut80452, _mut80453);
            jacobian[0][1] = AOR_divide(y, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80454, _mut80455, _mut80456, _mut80457);
            jacobian[0][2] = AOR_divide(z, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80458, _mut80459, _mut80460, _mut80461);
            // row representing the gradient of theta
            jacobian[1][0] = AOR_divide(-y, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80462, _mut80463, _mut80464, _mut80465);
            jacobian[1][1] = AOR_divide(x, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80466, _mut80467, _mut80468, _mut80469);
            // row representing the gradient of phi
            jacobian[2][0] = AOR_divide(AOR_multiply(x, z, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80470, _mut80471, _mut80472, _mut80473), (AOR_multiply(rho, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80474, _mut80475, _mut80476, _mut80477)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80478, _mut80479, _mut80480, _mut80481);
            jacobian[2][1] = AOR_divide(AOR_multiply(y, z, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80482, _mut80483, _mut80484, _mut80485), (AOR_multiply(rho, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80486, _mut80487, _mut80488, _mut80489)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80490, _mut80491, _mut80492, _mut80493);
            jacobian[2][2] = AOR_divide(-rho, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeJacobian_244", _mut80494, _mut80495, _mut80496, _mut80497);
        }
    }

    /**
     * Lazy evaluation of Hessians.
     */
    private void computeHessians() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277");
        if (rHessian == null) {
            // intermediate variables
            final double x = v.getX();
            final double y = v.getY();
            final double z = v.getZ();
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80498, _mut80499, _mut80500, _mut80501);
            final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80502, _mut80503, _mut80504, _mut80505);
            final double z2 = AOR_multiply(z, z, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80506, _mut80507, _mut80508, _mut80509);
            final double rho2 = AOR_plus(x2, y2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80510, _mut80511, _mut80512, _mut80513);
            final double rho = FastMath.sqrt(rho2);
            final double r2 = AOR_plus(rho2, z2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80514, _mut80515, _mut80516, _mut80517);
            final double xOr = AOR_divide(x, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80518, _mut80519, _mut80520, _mut80521);
            final double yOr = AOR_divide(y, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80522, _mut80523, _mut80524, _mut80525);
            final double zOr = AOR_divide(z, r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80526, _mut80527, _mut80528, _mut80529);
            final double xOrho2 = AOR_divide(x, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80530, _mut80531, _mut80532, _mut80533);
            final double yOrho2 = AOR_divide(y, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80534, _mut80535, _mut80536, _mut80537);
            final double xOr3 = AOR_divide(xOr, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80538, _mut80539, _mut80540, _mut80541);
            final double yOr3 = AOR_divide(yOr, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80542, _mut80543, _mut80544, _mut80545);
            final double zOr3 = AOR_divide(zOr, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80546, _mut80547, _mut80548, _mut80549);
            // lower-left part of Hessian of r
            rHessian = new double[3][3];
            rHessian[0][0] = AOR_plus(AOR_multiply(y, yOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80550, _mut80551, _mut80552, _mut80553), AOR_multiply(z, zOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80554, _mut80555, _mut80556, _mut80557), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80558, _mut80559, _mut80560, _mut80561);
            rHessian[1][0] = AOR_multiply(-x, yOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80562, _mut80563, _mut80564, _mut80565);
            rHessian[2][0] = AOR_multiply(-z, xOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80566, _mut80567, _mut80568, _mut80569);
            rHessian[1][1] = AOR_plus(AOR_multiply(x, xOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80570, _mut80571, _mut80572, _mut80573), AOR_multiply(z, zOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80574, _mut80575, _mut80576, _mut80577), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80578, _mut80579, _mut80580, _mut80581);
            rHessian[2][1] = AOR_multiply(-y, zOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80582, _mut80583, _mut80584, _mut80585);
            rHessian[2][2] = AOR_plus(AOR_multiply(x, xOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80586, _mut80587, _mut80588, _mut80589), AOR_multiply(y, yOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80590, _mut80591, _mut80592, _mut80593), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80594, _mut80595, _mut80596, _mut80597);
            // upper-right part is symmetric
            rHessian[0][1] = rHessian[1][0];
            rHessian[0][2] = rHessian[2][0];
            rHessian[1][2] = rHessian[2][1];
            // lower-left part of Hessian of azimuthal angle theta
            thetaHessian = new double[2][2];
            thetaHessian[0][0] = AOR_multiply(AOR_multiply(2, xOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80598, _mut80599, _mut80600, _mut80601), yOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80602, _mut80603, _mut80604, _mut80605);
            thetaHessian[1][0] = AOR_minus(AOR_multiply(yOrho2, yOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80606, _mut80607, _mut80608, _mut80609), AOR_multiply(xOrho2, xOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80610, _mut80611, _mut80612, _mut80613), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80614, _mut80615, _mut80616, _mut80617);
            thetaHessian[1][1] = AOR_multiply(AOR_multiply(-2, xOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80618, _mut80619, _mut80620, _mut80621), yOrho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80622, _mut80623, _mut80624, _mut80625);
            // upper-right part is symmetric
            thetaHessian[0][1] = thetaHessian[1][0];
            // lower-left part of Hessian of polar (co-latitude) angle phi
            final double rhor2 = AOR_multiply(rho, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80626, _mut80627, _mut80628, _mut80629);
            final double rho2r2 = AOR_multiply(rho, rhor2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80630, _mut80631, _mut80632, _mut80633);
            final double rhor4 = AOR_multiply(rhor2, r2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80634, _mut80635, _mut80636, _mut80637);
            final double rho3r4 = AOR_multiply(rhor4, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80638, _mut80639, _mut80640, _mut80641);
            final double r2P2rho2 = AOR_plus(AOR_multiply(3, rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80642, _mut80643, _mut80644, _mut80645), z2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80646, _mut80647, _mut80648, _mut80649);
            phiHessian = new double[3][3];
            phiHessian[0][0] = AOR_divide(AOR_multiply(z, (AOR_minus(rho2r2, AOR_multiply(x2, r2P2rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80650, _mut80651, _mut80652, _mut80653), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80654, _mut80655, _mut80656, _mut80657)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80658, _mut80659, _mut80660, _mut80661), rho3r4, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80662, _mut80663, _mut80664, _mut80665);
            phiHessian[1][0] = AOR_divide(AOR_multiply(AOR_multiply(AOR_multiply(-x, y, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80666, _mut80667, _mut80668, _mut80669), z, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80670, _mut80671, _mut80672, _mut80673), r2P2rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80674, _mut80675, _mut80676, _mut80677), rho3r4, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80678, _mut80679, _mut80680, _mut80681);
            phiHessian[2][0] = AOR_divide(AOR_multiply(x, (AOR_minus(rho2, z2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80682, _mut80683, _mut80684, _mut80685)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80686, _mut80687, _mut80688, _mut80689), rhor4, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80690, _mut80691, _mut80692, _mut80693);
            phiHessian[1][1] = AOR_divide(AOR_multiply(z, (AOR_minus(rho2r2, AOR_multiply(y2, r2P2rho2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80694, _mut80695, _mut80696, _mut80697), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80698, _mut80699, _mut80700, _mut80701)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80702, _mut80703, _mut80704, _mut80705), rho3r4, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80706, _mut80707, _mut80708, _mut80709);
            phiHessian[2][1] = AOR_divide(AOR_multiply(y, (AOR_minus(rho2, z2, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80710, _mut80711, _mut80712, _mut80713)), "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80714, _mut80715, _mut80716, _mut80717), rhor4, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80718, _mut80719, _mut80720, _mut80721);
            phiHessian[2][2] = AOR_divide(AOR_multiply(AOR_multiply(2, rho, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80722, _mut80723, _mut80724, _mut80725), zOr3, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80726, _mut80727, _mut80728, _mut80729), r, "org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.computeHessians_277", _mut80730, _mut80731, _mut80732, _mut80733);
            // upper-right part is symmetric
            phiHessian[0][1] = phiHessian[1][0];
            phiHessian[0][2] = phiHessian[2][0];
            phiHessian[1][2] = phiHessian[2][1];
        }
    }

    /**
     * Replace the instance with a data transfer object for serialization.
     * @return data transfer object that will be serialized
     */
    private Object writeReplace() {
        return new DataTransferObject(v.getX(), v.getY(), v.getZ());
    }

    /**
     * Internal class used only for serialization.
     */
    private static class DataTransferObject implements Serializable {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 20130206L;

        /**
         * Abscissa.
         * @serial
         */
        private final double x;

        /**
         * Ordinate.
         * @serial
         */
        private final double y;

        /**
         * Height.
         * @serial
         */
        private final double z;

        /**
         * Simple constructor.
         * @param x abscissa
         * @param y ordinate
         * @param z height
         */
        DataTransferObject(final double x, final double y, final double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Replace the deserialized data transfer object with a {@link SphericalCoordinates}.
         * @return replacement {@link SphericalCoordinates}
         */
        private Object readResolve() {
            return new SphericalCoordinates(new Vector3D(x, y, z));
        }
    }
}
