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
package org.apache.commons.math3.geometry.euclidean.twod;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a 2D vector.
 * <p>Instances of this class are guaranteed to be immutable.</p>
 * @since 3.0
 */
public class Vector2D implements Vector<Euclidean2D> {

    @Conditional
    public static boolean _mut84193 = false, _mut84194 = false, _mut84195 = false, _mut84196 = false, _mut84197 = false, _mut84198 = false, _mut84199 = false, _mut84200 = false, _mut84201 = false, _mut84202 = false, _mut84203 = false, _mut84204 = false, _mut84205 = false, _mut84206 = false, _mut84207 = false, _mut84208 = false, _mut84209 = false, _mut84210 = false, _mut84211 = false, _mut84212 = false, _mut84213 = false, _mut84214 = false, _mut84215 = false, _mut84216 = false, _mut84217 = false, _mut84218 = false, _mut84219 = false, _mut84220 = false, _mut84221 = false, _mut84222 = false, _mut84223 = false, _mut84224 = false, _mut84225 = false, _mut84226 = false, _mut84227 = false, _mut84228 = false, _mut84229 = false, _mut84230 = false, _mut84231 = false, _mut84232 = false, _mut84233 = false, _mut84234 = false, _mut84235 = false, _mut84236 = false, _mut84237 = false, _mut84238 = false, _mut84239 = false, _mut84240 = false, _mut84241 = false, _mut84242 = false, _mut84243 = false, _mut84244 = false, _mut84245 = false, _mut84246 = false, _mut84247 = false, _mut84248 = false, _mut84249 = false, _mut84250 = false, _mut84251 = false, _mut84252 = false, _mut84253 = false, _mut84254 = false, _mut84255 = false, _mut84256 = false, _mut84257 = false, _mut84258 = false, _mut84259 = false, _mut84260 = false, _mut84261 = false, _mut84262 = false, _mut84263 = false, _mut84264 = false, _mut84265 = false, _mut84266 = false, _mut84267 = false, _mut84268 = false, _mut84269 = false, _mut84270 = false, _mut84271 = false, _mut84272 = false, _mut84273 = false, _mut84274 = false, _mut84275 = false, _mut84276 = false, _mut84277 = false, _mut84278 = false, _mut84279 = false, _mut84280 = false, _mut84281 = false, _mut84282 = false, _mut84283 = false, _mut84284 = false, _mut84285 = false, _mut84286 = false, _mut84287 = false, _mut84288 = false, _mut84289 = false, _mut84290 = false, _mut84291 = false, _mut84292 = false, _mut84293 = false, _mut84294 = false, _mut84295 = false, _mut84296 = false, _mut84297 = false, _mut84298 = false, _mut84299 = false, _mut84300 = false, _mut84301 = false, _mut84302 = false, _mut84303 = false, _mut84304 = false, _mut84305 = false, _mut84306 = false, _mut84307 = false, _mut84308 = false, _mut84309 = false, _mut84310 = false, _mut84311 = false, _mut84312 = false, _mut84313 = false, _mut84314 = false, _mut84315 = false, _mut84316 = false, _mut84317 = false, _mut84318 = false, _mut84319 = false, _mut84320 = false, _mut84321 = false, _mut84322 = false, _mut84323 = false, _mut84324 = false, _mut84325 = false, _mut84326 = false, _mut84327 = false, _mut84328 = false, _mut84329 = false, _mut84330 = false, _mut84331 = false, _mut84332 = false, _mut84333 = false, _mut84334 = false, _mut84335 = false, _mut84336 = false, _mut84337 = false, _mut84338 = false, _mut84339 = false, _mut84340 = false, _mut84341 = false, _mut84342 = false, _mut84343 = false, _mut84344 = false, _mut84345 = false, _mut84346 = false, _mut84347 = false, _mut84348 = false, _mut84349 = false, _mut84350 = false, _mut84351 = false, _mut84352 = false, _mut84353 = false, _mut84354 = false, _mut84355 = false, _mut84356 = false, _mut84357 = false, _mut84358 = false, _mut84359 = false, _mut84360 = false, _mut84361 = false, _mut84362 = false, _mut84363 = false, _mut84364 = false, _mut84365 = false, _mut84366 = false, _mut84367 = false, _mut84368 = false, _mut84369 = false, _mut84370 = false, _mut84371 = false, _mut84372 = false, _mut84373 = false, _mut84374 = false, _mut84375 = false, _mut84376 = false, _mut84377 = false, _mut84378 = false, _mut84379 = false, _mut84380 = false, _mut84381 = false, _mut84382 = false, _mut84383 = false, _mut84384 = false, _mut84385 = false, _mut84386 = false, _mut84387 = false, _mut84388 = false, _mut84389 = false, _mut84390 = false, _mut84391 = false, _mut84392 = false, _mut84393 = false, _mut84394 = false, _mut84395 = false, _mut84396 = false, _mut84397 = false, _mut84398 = false, _mut84399 = false, _mut84400 = false, _mut84401 = false, _mut84402 = false, _mut84403 = false, _mut84404 = false, _mut84405 = false, _mut84406 = false, _mut84407 = false, _mut84408 = false, _mut84409 = false, _mut84410 = false, _mut84411 = false, _mut84412 = false, _mut84413 = false, _mut84414 = false, _mut84415 = false, _mut84416 = false, _mut84417 = false, _mut84418 = false, _mut84419 = false, _mut84420 = false, _mut84421 = false, _mut84422 = false, _mut84423 = false, _mut84424 = false, _mut84425 = false, _mut84426 = false, _mut84427 = false, _mut84428 = false, _mut84429 = false, _mut84430 = false, _mut84431 = false, _mut84432 = false, _mut84433 = false, _mut84434 = false, _mut84435 = false, _mut84436 = false, _mut84437 = false, _mut84438 = false, _mut84439 = false, _mut84440 = false, _mut84441 = false, _mut84442 = false, _mut84443 = false, _mut84444 = false, _mut84445 = false, _mut84446 = false, _mut84447 = false, _mut84448 = false, _mut84449 = false, _mut84450 = false, _mut84451 = false, _mut84452 = false, _mut84453 = false, _mut84454 = false, _mut84455 = false, _mut84456 = false, _mut84457 = false, _mut84458 = false, _mut84459 = false, _mut84460 = false, _mut84461 = false, _mut84462 = false, _mut84463 = false, _mut84464 = false, _mut84465 = false, _mut84466 = false, _mut84467 = false, _mut84468 = false, _mut84469 = false, _mut84470 = false, _mut84471 = false, _mut84472 = false, _mut84473 = false, _mut84474 = false, _mut84475 = false, _mut84476 = false, _mut84477 = false, _mut84478 = false, _mut84479 = false, _mut84480 = false, _mut84481 = false, _mut84482 = false, _mut84483 = false, _mut84484 = false, _mut84485 = false, _mut84486 = false, _mut84487 = false, _mut84488 = false, _mut84489 = false, _mut84490 = false, _mut84491 = false, _mut84492 = false, _mut84493 = false, _mut84494 = false, _mut84495 = false, _mut84496 = false, _mut84497 = false, _mut84498 = false, _mut84499 = false, _mut84500 = false, _mut84501 = false, _mut84502 = false, _mut84503 = false, _mut84504 = false, _mut84505 = false, _mut84506 = false, _mut84507 = false, _mut84508 = false, _mut84509 = false, _mut84510 = false, _mut84511 = false, _mut84512 = false, _mut84513 = false, _mut84514 = false, _mut84515 = false, _mut84516 = false, _mut84517 = false, _mut84518 = false, _mut84519 = false, _mut84520 = false, _mut84521 = false, _mut84522 = false, _mut84523 = false, _mut84524 = false, _mut84525 = false, _mut84526 = false, _mut84527 = false, _mut84528 = false, _mut84529 = false, _mut84530 = false, _mut84531 = false, _mut84532 = false, _mut84533 = false, _mut84534 = false, _mut84535 = false, _mut84536 = false, _mut84537 = false, _mut84538 = false, _mut84539 = false, _mut84540 = false, _mut84541 = false, _mut84542 = false, _mut84543 = false, _mut84544 = false, _mut84545 = false, _mut84546 = false, _mut84547 = false, _mut84548 = false, _mut84549 = false, _mut84550 = false, _mut84551 = false, _mut84552 = false, _mut84553 = false, _mut84554 = false, _mut84555 = false, _mut84556 = false, _mut84557 = false, _mut84558 = false, _mut84559 = false, _mut84560 = false, _mut84561 = false, _mut84562 = false, _mut84563 = false, _mut84564 = false, _mut84565 = false;

    /**
     * Origin (coordinates: 0, 0).
     */
    public static final Vector2D ZERO = new Vector2D(0, 0);

    /**
     * A vector with all coordinates set to NaN.
     */
    public static final Vector2D NaN = new Vector2D(Double.NaN, Double.NaN);

    /**
     * A vector with all coordinates set to positive infinity.
     */
    public static final Vector2D POSITIVE_INFINITY = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * A vector with all coordinates set to negative infinity.
     */
    public static final Vector2D NEGATIVE_INFINITY = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 266938651998679754L;

    /**
     * Abscissa.
     */
    private final double x;

    /**
     * Ordinate.
     */
    private final double y;

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param x abscissa
     * @param y ordinate
     * @see #getX()
     * @see #getY()
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Simple constructor.
     * Build a vector from its coordinates
     * @param v coordinates array
     * @exception DimensionMismatchException if array does not have 2 elements
     * @see #toArray()
     */
    public Vector2D(double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_80");
        if (ROR_not_equals(v.length, 2, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_80", _mut84193, _mut84194, _mut84195, _mut84196, _mut84197)) {
            throw new DimensionMismatchException(v.length, 2);
        }
        this.x = v[0];
        this.y = v[1];
    }

    /**
     * Multiplicative constructor
     * Build a vector from another one and a scale factor.
     * The vector built will be a * u
     * @param a scale factor
     * @param u base (unscaled) vector
     */
    public Vector2D(double a, Vector2D u) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_94");
        this.x = AOR_multiply(a, u.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_94", _mut84198, _mut84199, _mut84200, _mut84201);
        this.y = AOR_multiply(a, u.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_94", _mut84202, _mut84203, _mut84204, _mut84205);
    }

    /**
     * Linear constructor
     * Build a vector from two other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     */
    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107");
        this.x = AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84206, _mut84207, _mut84208, _mut84209), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84210, _mut84211, _mut84212, _mut84213), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84214, _mut84215, _mut84216, _mut84217);
        this.y = AOR_plus(AOR_multiply(a1, u1.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84218, _mut84219, _mut84220, _mut84221), AOR_multiply(a2, u2.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84222, _mut84223, _mut84224, _mut84225), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_107", _mut84226, _mut84227, _mut84228, _mut84229);
    }

    /**
     * Linear constructor
     * Build a vector from three other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     */
    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122");
        this.x = AOR_plus(AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84230, _mut84231, _mut84232, _mut84233), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84234, _mut84235, _mut84236, _mut84237), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84238, _mut84239, _mut84240, _mut84241), AOR_multiply(a3, u3.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84242, _mut84243, _mut84244, _mut84245), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84246, _mut84247, _mut84248, _mut84249);
        this.y = AOR_plus(AOR_plus(AOR_multiply(a1, u1.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84250, _mut84251, _mut84252, _mut84253), AOR_multiply(a2, u2.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84254, _mut84255, _mut84256, _mut84257), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84258, _mut84259, _mut84260, _mut84261), AOR_multiply(a3, u3.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84262, _mut84263, _mut84264, _mut84265), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_122", _mut84266, _mut84267, _mut84268, _mut84269);
    }

    /**
     * Linear constructor
     * Build a vector from four other ones and corresponding scale factors.
     * The vector built will be a1 * u1 + a2 * u2 + a3 * u3 + a4 * u4
     * @param a1 first scale factor
     * @param u1 first base (unscaled) vector
     * @param a2 second scale factor
     * @param u2 second base (unscaled) vector
     * @param a3 third scale factor
     * @param u3 third base (unscaled) vector
     * @param a4 fourth scale factor
     * @param u4 fourth base (unscaled) vector
     */
    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3, double a4, Vector2D u4) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140");
        this.x = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(a1, u1.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84270, _mut84271, _mut84272, _mut84273), AOR_multiply(a2, u2.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84274, _mut84275, _mut84276, _mut84277), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84278, _mut84279, _mut84280, _mut84281), AOR_multiply(a3, u3.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84282, _mut84283, _mut84284, _mut84285), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84286, _mut84287, _mut84288, _mut84289), AOR_multiply(a4, u4.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84290, _mut84291, _mut84292, _mut84293), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84294, _mut84295, _mut84296, _mut84297);
        this.y = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(a1, u1.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84298, _mut84299, _mut84300, _mut84301), AOR_multiply(a2, u2.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84302, _mut84303, _mut84304, _mut84305), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84306, _mut84307, _mut84308, _mut84309), AOR_multiply(a3, u3.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84310, _mut84311, _mut84312, _mut84313), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84314, _mut84315, _mut84316, _mut84317), AOR_multiply(a4, u4.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84318, _mut84319, _mut84320, _mut84321), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.Vector2D_140", _mut84322, _mut84323, _mut84324, _mut84325);
    }

    /**
     * Get the abscissa of the vector.
     * @return abscissa of the vector
     * @see #Vector2D(double, double)
     */
    public double getX() {
        return x;
    }

    /**
     * Get the ordinate of the vector.
     * @return ordinate of the vector
     * @see #Vector2D(double, double)
     */
    public double getY() {
        return y;
    }

    /**
     * Get the vector coordinates as a dimension 2 array.
     * @return vector coordinates
     * @see #Vector2D(double[])
     */
    public double[] toArray() {
        return new double[] { x, y };
    }

    /**
     * {@inheritDoc}
     */
    public Space getSpace() {
        return Euclidean2D.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D getZero() {
        return ZERO;
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm1() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm1_181");
        return AOR_plus(FastMath.abs(x), FastMath.abs(y), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm1_181", _mut84326, _mut84327, _mut84328, _mut84329);
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm_186");
        return FastMath.sqrt(AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm_186", _mut84330, _mut84331, _mut84332, _mut84333), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm_186", _mut84334, _mut84335, _mut84336, _mut84337), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNorm_186", _mut84338, _mut84339, _mut84340, _mut84341));
    }

    /**
     * {@inheritDoc}
     */
    public double getNormSq() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNormSq_191");
        return AOR_plus(AOR_multiply(x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNormSq_191", _mut84342, _mut84343, _mut84344, _mut84345), AOR_multiply(y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNormSq_191", _mut84346, _mut84347, _mut84348, _mut84349), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.getNormSq_191", _mut84350, _mut84351, _mut84352, _mut84353);
    }

    /**
     * {@inheritDoc}
     */
    public double getNormInf() {
        return FastMath.max(FastMath.abs(x), FastMath.abs(y));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D add(Vector<Euclidean2D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_201");
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(AOR_plus(x, v2.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_201", _mut84354, _mut84355, _mut84356, _mut84357), AOR_plus(y, v2.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_201", _mut84358, _mut84359, _mut84360, _mut84361));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D add(double factor, Vector<Euclidean2D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_207");
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(AOR_plus(x, AOR_multiply(factor, v2.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_207", _mut84362, _mut84363, _mut84364, _mut84365), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_207", _mut84366, _mut84367, _mut84368, _mut84369), AOR_plus(y, AOR_multiply(factor, v2.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_207", _mut84370, _mut84371, _mut84372, _mut84373), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.add_207", _mut84374, _mut84375, _mut84376, _mut84377));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D subtract(Vector<Euclidean2D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_213");
        Vector2D p3 = (Vector2D) p;
        return new Vector2D(AOR_minus(x, p3.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_213", _mut84378, _mut84379, _mut84380, _mut84381), AOR_minus(y, p3.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_213", _mut84382, _mut84383, _mut84384, _mut84385));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D subtract(double factor, Vector<Euclidean2D> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_219");
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(AOR_minus(x, AOR_multiply(factor, v2.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_219", _mut84386, _mut84387, _mut84388, _mut84389), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_219", _mut84390, _mut84391, _mut84392, _mut84393), AOR_minus(y, AOR_multiply(factor, v2.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_219", _mut84394, _mut84395, _mut84396, _mut84397), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.subtract_219", _mut84398, _mut84399, _mut84400, _mut84401));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D normalize() throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.normalize_225");
        double s = getNorm();
        if (ROR_equals(s, 0, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.normalize_225", _mut84402, _mut84403, _mut84404, _mut84405, _mut84406)) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
        }
        return scalarMultiply(AOR_divide(1, s, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.normalize_225", _mut84407, _mut84408, _mut84409, _mut84410));
    }

    /**
     * Compute the angular separation between two vectors.
     * <p>This method computes the angular separation between two
     * vectors using the dot product for well separated vectors and the
     * cross product for almost aligned vectors. This allows to have a
     * good accuracy in all cases, even for vectors very close to each
     * other.</p>
     * @param v1 first vector
     * @param v2 second vector
     * @return angular separation between v1 and v2
     * @exception MathArithmeticException if either vector has a null norm
     */
    public static double angle(Vector2D v1, Vector2D v2) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244");
        double normProduct = AOR_multiply(v1.getNorm(), v2.getNorm(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84411, _mut84412, _mut84413, _mut84414);
        if (ROR_equals(normProduct, 0, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84415, _mut84416, _mut84417, _mut84418, _mut84419)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }
        double dot = v1.dotProduct(v2);
        double threshold = AOR_multiply(normProduct, 0.9999, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84420, _mut84421, _mut84422, _mut84423);
        if ((_mut84434 ? ((ROR_less(dot, -threshold, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84424, _mut84425, _mut84426, _mut84427, _mut84428)) && (ROR_greater(dot, threshold, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84429, _mut84430, _mut84431, _mut84432, _mut84433))) : ((ROR_less(dot, -threshold, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84424, _mut84425, _mut84426, _mut84427, _mut84428)) || (ROR_greater(dot, threshold, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84429, _mut84430, _mut84431, _mut84432, _mut84433))))) {
            // the vectors are almost aligned, compute using the sine
            final double n = FastMath.abs(MathArrays.linearCombination(v1.x, v2.y, -v1.y, v2.x));
            if (ROR_greater_equals(dot, 0, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84435, _mut84436, _mut84437, _mut84438, _mut84439)) {
                return FastMath.asin(AOR_divide(n, normProduct, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84440, _mut84441, _mut84442, _mut84443));
            }
            return AOR_minus(FastMath.PI, FastMath.asin(AOR_divide(n, normProduct, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84444, _mut84445, _mut84446, _mut84447)), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84448, _mut84449, _mut84450, _mut84451);
        }
        // the vectors are sufficiently separated to use the cosine
        return FastMath.acos(AOR_divide(dot, normProduct, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.angle_244", _mut84452, _mut84453, _mut84454, _mut84455));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D negate() {
        return new Vector2D(-x, -y);
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D scalarMultiply(double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.scalarMultiply_273");
        return new Vector2D(AOR_multiply(a, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.scalarMultiply_273", _mut84456, _mut84457, _mut84458, _mut84459), AOR_multiply(a, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.scalarMultiply_273", _mut84460, _mut84461, _mut84462, _mut84463));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNaN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.isNaN_278");
        return (_mut84464 ? (Double.isNaN(x) && Double.isNaN(y)) : (Double.isNaN(x) || Double.isNaN(y)));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfinite() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.isInfinite_283");
        return (_mut84466 ? (!isNaN() || ((_mut84465 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))))) : (!isNaN() && ((_mut84465 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y))))));
    }

    /**
     * {@inheritDoc}
     */
    public double distance1(Vector<Euclidean2D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance1_288");
        Vector2D p3 = (Vector2D) p;
        final double dx = FastMath.abs(AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance1_288", _mut84467, _mut84468, _mut84469, _mut84470));
        final double dy = FastMath.abs(AOR_minus(p3.y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance1_288", _mut84471, _mut84472, _mut84473, _mut84474));
        return AOR_plus(dx, dy, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance1_288", _mut84475, _mut84476, _mut84477, _mut84478);
    }

    /**
     * {@inheritDoc}
     */
    public double distance(Vector<Euclidean2D> p) {
        return distance((Point<Euclidean2D>) p);
    }

    /**
     * {@inheritDoc}
     */
    public double distance(Point<Euclidean2D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302");
        Vector2D p3 = (Vector2D) p;
        final double dx = AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302", _mut84479, _mut84480, _mut84481, _mut84482);
        final double dy = AOR_minus(p3.y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302", _mut84483, _mut84484, _mut84485, _mut84486);
        return FastMath.sqrt(AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302", _mut84487, _mut84488, _mut84489, _mut84490), AOR_multiply(dy, dy, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302", _mut84491, _mut84492, _mut84493, _mut84494), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance_302", _mut84495, _mut84496, _mut84497, _mut84498));
    }

    /**
     * {@inheritDoc}
     */
    public double distanceInf(Vector<Euclidean2D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceInf_310");
        Vector2D p3 = (Vector2D) p;
        final double dx = FastMath.abs(AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceInf_310", _mut84499, _mut84500, _mut84501, _mut84502));
        final double dy = FastMath.abs(AOR_minus(p3.y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceInf_310", _mut84503, _mut84504, _mut84505, _mut84506));
        return FastMath.max(dx, dy);
    }

    /**
     * {@inheritDoc}
     */
    public double distanceSq(Vector<Euclidean2D> p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318");
        Vector2D p3 = (Vector2D) p;
        final double dx = AOR_minus(p3.x, x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318", _mut84507, _mut84508, _mut84509, _mut84510);
        final double dy = AOR_minus(p3.y, y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318", _mut84511, _mut84512, _mut84513, _mut84514);
        return AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318", _mut84515, _mut84516, _mut84517, _mut84518), AOR_multiply(dy, dy, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318", _mut84519, _mut84520, _mut84521, _mut84522), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distanceSq_318", _mut84523, _mut84524, _mut84525, _mut84526);
    }

    /**
     * {@inheritDoc}
     */
    public double dotProduct(final Vector<Euclidean2D> v) {
        final Vector2D v2 = (Vector2D) v;
        return MathArrays.linearCombination(x, v2.x, y, v2.y);
    }

    /**
     * Compute the cross-product of the instance and the given points.
     * <p>
     * The cross product can be used to determine the location of a point
     * with regard to the line formed by (p1, p2) and is calculated as:
     * \[
     *    P = (x_2 - x_1)(y_3 - y_1) - (y_2 - y_1)(x_3 - x_1)
     * \]
     * with \(p3 = (x_3, y_3)\) being this instance.
     * <p>
     * If the result is 0, the points are collinear, i.e. lie on a single straight line L;
     * if it is positive, this point lies to the left, otherwise to the right of the line
     * formed by (p1, p2).
     *
     * @param p1 first point of the line
     * @param p2 second point of the line
     * @return the cross-product
     *
     * @see <a href="http://en.wikipedia.org/wiki/Cross_product">Cross product (Wikipedia)</a>
     */
    public double crossProduct(final Vector2D p1, final Vector2D p2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.crossProduct_351");
        final double x1 = AOR_minus(p2.getX(), p1.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.crossProduct_351", _mut84527, _mut84528, _mut84529, _mut84530);
        final double y1 = AOR_minus(getY(), p1.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.crossProduct_351", _mut84531, _mut84532, _mut84533, _mut84534);
        final double x2 = AOR_minus(getX(), p1.getX(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.crossProduct_351", _mut84535, _mut84536, _mut84537, _mut84538);
        final double y2 = AOR_minus(p2.getY(), p1.getY(), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.crossProduct_351", _mut84539, _mut84540, _mut84541, _mut84542);
        return MathArrays.linearCombination(x1, y1, -x2, y2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>2</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNorm()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the distance between p1 and p2 according to the L<sub>2</sub> norm
     */
    public static double distance(Vector2D p1, Vector2D p2) {
        return p1.distance(p2);
    }

    /**
     * Compute the distance between two vectors according to the L<sub>&infin;</sub> norm.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNormInf()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the distance between p1 and p2 according to the L<sub>&infin;</sub> norm
     */
    public static double distanceInf(Vector2D p1, Vector2D p2) {
        return p1.distanceInf(p2);
    }

    /**
     * Compute the square of the distance between two vectors.
     * <p>Calling this method is equivalent to calling:
     * <code>p1.subtract(p2).getNormSq()</code> except that no intermediate
     * vector is built</p>
     * @param p1 first vector
     * @param p2 second vector
     * @return the square of the distance between p1 and p2
     */
    public static double distanceSq(Vector2D p1, Vector2D p2) {
        return p1.distanceSq(p2);
    }

    /**
     * Test for the equality of two 2D vectors.
     * <p>
     * If all coordinates of two 2D vectors are exactly the same, and none are
     * <code>Double.NaN</code>, the two 2D vectors are considered to be equal.
     * </p>
     * <p>
     * <code>NaN</code> coordinates are considered to affect globally the vector
     * and be equals to each other - i.e, if either (or all) coordinates of the
     * 2D vector are equal to <code>Double.NaN</code>, the 2D vector is equal to
     * {@link #NaN}.
     * </p>
     *
     * @param other Object to test for equality to this
     * @return true if two 2D vector objects are equal, false if
     *         object is null, not an instance of Vector2D, or
     *         not equal to this Vector2D instance
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.equals_414");
        if (this == other) {
            return true;
        }
        if (other instanceof Vector2D) {
            final Vector2D rhs = (Vector2D) other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return (_mut84553 ? ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.equals_414", _mut84543, _mut84544, _mut84545, _mut84546, _mut84547)) || (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.equals_414", _mut84548, _mut84549, _mut84550, _mut84551, _mut84552))) : ((ROR_equals(x, rhs.x, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.equals_414", _mut84543, _mut84544, _mut84545, _mut84546, _mut84547)) && (ROR_equals(y, rhs.y, "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.equals_414", _mut84548, _mut84549, _mut84550, _mut84551, _mut84552))));
        }
        return false;
    }

    /**
     * Get a hashCode for the 2D vector.
     * <p>
     * All NaN values have the same hash code.</p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.twod.Vector2D.hashCode_439");
        if (isNaN()) {
            return 542;
        }
        return AOR_multiply(122, (AOR_plus(AOR_multiply(76, MathUtils.hash(x), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.hashCode_439", _mut84554, _mut84555, _mut84556, _mut84557), MathUtils.hash(y), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.hashCode_439", _mut84558, _mut84559, _mut84560, _mut84561)), "org.apache.commons.math3.geometry.euclidean.twod.Vector2D.hashCode_439", _mut84562, _mut84563, _mut84564, _mut84565);
    }

    /**
     * Get a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return Vector2DFormat.getInstance().format(this);
    }

    /**
     * {@inheritDoc}
     */
    public String toString(final NumberFormat format) {
        return new Vector2DFormat(format).format(this);
    }
}
