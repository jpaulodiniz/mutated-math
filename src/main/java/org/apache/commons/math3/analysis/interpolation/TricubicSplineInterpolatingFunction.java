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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Function that implements the
 * <a href="http://en.wikipedia.org/wiki/Tricubic_interpolation">
 * tricubic spline interpolation</a>, as proposed in
 * <blockquote>
 *  Tricubic interpolation in three dimensions,
 *  F. Lekien and J. Marsden,
 *  <em>Int. J. Numer. Meth. Engng</em> 2005; <b>63</b>:455-471
 * </blockquote>
 *
 * @since 2.2
 * @deprecated To be removed in 4.0 (see MATH-1166).
 */
@Deprecated
public class TricubicSplineInterpolatingFunction implements TrivariateFunction {

    @Conditional
    public static boolean _mut93292 = false, _mut93293 = false, _mut93294 = false, _mut93295 = false, _mut93296 = false, _mut93297 = false, _mut93298 = false, _mut93299 = false, _mut93300 = false, _mut93301 = false, _mut93302 = false, _mut93303 = false, _mut93304 = false, _mut93305 = false, _mut93306 = false, _mut93307 = false, _mut93308 = false, _mut93309 = false, _mut93310 = false, _mut93311 = false, _mut93312 = false, _mut93313 = false, _mut93314 = false, _mut93315 = false, _mut93316 = false, _mut93317 = false, _mut93318 = false, _mut93319 = false, _mut93320 = false, _mut93321 = false, _mut93322 = false, _mut93323 = false, _mut93324 = false, _mut93325 = false, _mut93326 = false, _mut93327 = false, _mut93328 = false, _mut93329 = false, _mut93330 = false, _mut93331 = false, _mut93332 = false, _mut93333 = false, _mut93334 = false, _mut93335 = false, _mut93336 = false, _mut93337 = false, _mut93338 = false, _mut93339 = false, _mut93340 = false, _mut93341 = false, _mut93342 = false, _mut93343 = false, _mut93344 = false, _mut93345 = false, _mut93346 = false, _mut93347 = false, _mut93348 = false, _mut93349 = false, _mut93350 = false, _mut93351 = false, _mut93352 = false, _mut93353 = false, _mut93354 = false, _mut93355 = false, _mut93356 = false, _mut93357 = false, _mut93358 = false, _mut93359 = false, _mut93360 = false, _mut93361 = false, _mut93362 = false, _mut93363 = false, _mut93364 = false, _mut93365 = false, _mut93366 = false, _mut93367 = false, _mut93368 = false, _mut93369 = false, _mut93370 = false, _mut93371 = false, _mut93372 = false, _mut93373 = false, _mut93374 = false, _mut93375 = false, _mut93376 = false, _mut93377 = false, _mut93378 = false, _mut93379 = false, _mut93380 = false, _mut93381 = false, _mut93382 = false, _mut93383 = false, _mut93384 = false, _mut93385 = false, _mut93386 = false, _mut93387 = false, _mut93388 = false, _mut93389 = false, _mut93390 = false, _mut93391 = false, _mut93392 = false, _mut93393 = false, _mut93394 = false, _mut93395 = false, _mut93396 = false, _mut93397 = false, _mut93398 = false, _mut93399 = false, _mut93400 = false, _mut93401 = false, _mut93402 = false, _mut93403 = false, _mut93404 = false, _mut93405 = false, _mut93406 = false, _mut93407 = false, _mut93408 = false, _mut93409 = false, _mut93410 = false, _mut93411 = false, _mut93412 = false, _mut93413 = false, _mut93414 = false, _mut93415 = false, _mut93416 = false, _mut93417 = false, _mut93418 = false, _mut93419 = false, _mut93420 = false, _mut93421 = false, _mut93422 = false, _mut93423 = false, _mut93424 = false, _mut93425 = false, _mut93426 = false, _mut93427 = false, _mut93428 = false, _mut93429 = false, _mut93430 = false, _mut93431 = false, _mut93432 = false, _mut93433 = false, _mut93434 = false, _mut93435 = false, _mut93436 = false, _mut93437 = false, _mut93438 = false, _mut93439 = false, _mut93440 = false, _mut93441 = false, _mut93442 = false, _mut93443 = false, _mut93444 = false, _mut93445 = false, _mut93446 = false, _mut93447 = false, _mut93448 = false, _mut93449 = false, _mut93450 = false, _mut93451 = false, _mut93452 = false, _mut93453 = false, _mut93454 = false, _mut93455 = false, _mut93456 = false, _mut93457 = false, _mut93458 = false, _mut93459 = false, _mut93460 = false, _mut93461 = false, _mut93462 = false, _mut93463 = false, _mut93464 = false, _mut93465 = false, _mut93466 = false, _mut93467 = false, _mut93468 = false, _mut93469 = false, _mut93470 = false, _mut93471 = false, _mut93472 = false, _mut93473 = false, _mut93474 = false, _mut93475 = false, _mut93476 = false, _mut93477 = false, _mut93478 = false, _mut93479 = false, _mut93480 = false, _mut93481 = false, _mut93482 = false, _mut93483 = false, _mut93484 = false, _mut93485 = false, _mut93486 = false, _mut93487 = false, _mut93488 = false, _mut93489 = false, _mut93490 = false, _mut93491 = false, _mut93492 = false, _mut93493 = false, _mut93494 = false, _mut93495 = false, _mut93496 = false, _mut93497 = false, _mut93498 = false, _mut93499 = false, _mut93500 = false, _mut93501 = false, _mut93502 = false, _mut93503 = false, _mut93504 = false, _mut93505 = false, _mut93506 = false, _mut93507 = false, _mut93508 = false, _mut93509 = false, _mut93510 = false, _mut93511 = false, _mut93512 = false, _mut93513 = false, _mut93514 = false, _mut93515 = false, _mut93516 = false, _mut93517 = false, _mut93518 = false, _mut93519 = false, _mut93520 = false, _mut93521 = false, _mut93522 = false, _mut93523 = false, _mut93524 = false, _mut93525 = false, _mut93526 = false, _mut93527 = false, _mut93528 = false, _mut93529 = false, _mut93530 = false, _mut93531 = false, _mut93532 = false, _mut93533 = false, _mut93534 = false, _mut93535 = false, _mut93536 = false, _mut93537 = false, _mut93538 = false, _mut93539 = false, _mut93540 = false, _mut93541 = false, _mut93542 = false, _mut93543 = false, _mut93544 = false, _mut93545 = false, _mut93546 = false, _mut93547 = false, _mut93548 = false, _mut93549 = false, _mut93550 = false, _mut93551 = false, _mut93552 = false, _mut93553 = false, _mut93554 = false, _mut93555 = false, _mut93556 = false, _mut93557 = false, _mut93558 = false, _mut93559 = false, _mut93560 = false, _mut93561 = false, _mut93562 = false, _mut93563 = false, _mut93564 = false, _mut93565 = false, _mut93566 = false, _mut93567 = false, _mut93568 = false, _mut93569 = false, _mut93570 = false, _mut93571 = false, _mut93572 = false, _mut93573 = false, _mut93574 = false, _mut93575 = false, _mut93576 = false, _mut93577 = false, _mut93578 = false, _mut93579 = false, _mut93580 = false, _mut93581 = false, _mut93582 = false, _mut93583 = false, _mut93584 = false, _mut93585 = false, _mut93586 = false, _mut93587 = false;

    /**
     * Matrix to compute the spline coefficients from the function values
     * and function derivatives values
     */
    private static final double[][] AINV = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -3, 3, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, -2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 9, -9, -9, 9, 0, 0, 0, 0, 6, 3, -6, -3, 0, 0, 0, 0, 6, -6, 3, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -6, 6, 6, -6, 0, 0, 0, 0, -3, -3, 3, 3, 0, 0, 0, 0, -4, 4, -2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -2, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -6, 6, 6, -6, 0, 0, 0, 0, -4, -2, 4, 2, 0, 0, 0, 0, -3, 3, -3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, -4, -4, 4, 0, 0, 0, 0, 2, 2, -2, -2, 0, 0, 0, 0, 2, -2, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, -9, -9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 3, -6, -3, 0, 0, 0, 0, 6, -6, 3, -3, 0, 0, 0, 0, 4, 2, 2, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, 6, 6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -3, 3, 3, 0, 0, 0, 0, -4, 4, -2, 2, 0, 0, 0, 0, -2, -2, -1, -1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, 6, 6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, -2, 4, 2, 0, 0, 0, 0, -3, 3, -3, 3, 0, 0, 0, 0, -2, -1, -2, -1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, -4, -4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, -2, -2, 0, 0, 0, 0, 2, -2, 2, -2, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 }, { -3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 9, -9, 0, 0, -9, 9, 0, 0, 6, 3, 0, 0, -6, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, -6, 0, 0, 3, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 2, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -6, 6, 0, 0, 6, -6, 0, 0, -3, -3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, 4, 0, 0, -2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -2, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, -9, 0, 0, -9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 3, 0, 0, -6, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, -6, 0, 0, 3, -3, 0, 0, 4, 2, 0, 0, 2, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, 6, 0, 0, 6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, 4, 0, 0, -2, 2, 0, 0, -2, -2, 0, 0, -1, -1, 0, 0 }, { 9, 0, -9, 0, -9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 3, 0, -6, 0, -3, 0, 6, 0, -6, 0, 3, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, -9, 0, -9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 3, 0, -6, 0, -3, 0, 6, 0, -6, 0, 3, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 2, 0, 2, 0, 1, 0 }, { -27, 27, 27, -27, 27, -27, -27, 27, -18, -9, 18, 9, 18, 9, -18, -9, -18, 18, -9, 9, 18, -18, 9, -9, -18, 18, 18, -18, -9, 9, 9, -9, -12, -6, -6, -3, 12, 6, 6, 3, -12, -6, 12, 6, -6, -3, 6, 3, -12, 12, -6, 6, -6, 6, -3, 3, -8, -4, -4, -2, -4, -2, -2, -1 }, { 18, -18, -18, 18, -18, 18, 18, -18, 9, 9, -9, -9, -9, -9, 9, 9, 12, -12, 6, -6, -12, 12, -6, 6, 12, -12, -12, 12, 6, -6, -6, 6, 6, 6, 3, 3, -6, -6, -3, -3, 6, 6, -6, -6, 3, 3, -3, -3, 8, -8, 4, -4, 4, -4, 2, -2, 4, 4, 2, 2, 2, 2, 1, 1 }, { -6, 0, 6, 0, 6, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, -3, 0, 3, 0, 3, 0, -4, 0, 4, 0, -2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -2, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -6, 0, 6, 0, 6, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, -3, 0, 3, 0, 3, 0, -4, 0, 4, 0, -2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -2, 0, -1, 0, -1, 0 }, { 18, -18, -18, 18, -18, 18, 18, -18, 12, 6, -12, -6, -12, -6, 12, 6, 9, -9, 9, -9, -9, 9, -9, 9, 12, -12, -12, 12, 6, -6, -6, 6, 6, 3, 6, 3, -6, -3, -6, -3, 8, 4, -8, -4, 4, 2, -4, -2, 6, -6, 6, -6, 3, -3, 3, -3, 4, 2, 4, 2, 2, 1, 2, 1 }, { -12, 12, 12, -12, 12, -12, -12, 12, -6, -6, 6, 6, 6, 6, -6, -6, -6, 6, -6, 6, 6, -6, 6, -6, -8, 8, 8, -8, -4, 4, 4, -4, -3, -3, -3, -3, 3, 3, 3, 3, -4, -4, 4, 4, -2, -2, 2, 2, -4, 4, -4, 4, -2, 2, -2, 2, -2, -2, -2, -2, -1, -1, -1, -1 }, { 2, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -6, 6, 0, 0, 6, -6, 0, 0, -4, -2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, -3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, -4, 0, 0, -4, 4, 0, 0, 2, 2, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, 6, 0, 0, 6, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, -2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, -3, 3, 0, 0, -2, -1, 0, 0, -2, -1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, -4, 0, 0, -4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 2, -2, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { -6, 0, 6, 0, 6, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, 0, -2, 0, 4, 0, 2, 0, -3, 0, 3, 0, -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -6, 0, 6, 0, 6, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4, 0, -2, 0, 4, 0, 2, 0, -3, 0, 3, 0, -3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, -1, 0, -2, 0, -1, 0 }, { 18, -18, -18, 18, -18, 18, 18, -18, 12, 6, -12, -6, -12, -6, 12, 6, 12, -12, 6, -6, -12, 12, -6, 6, 9, -9, -9, 9, 9, -9, -9, 9, 8, 4, 4, 2, -8, -4, -4, -2, 6, 3, -6, -3, 6, 3, -6, -3, 6, -6, 3, -3, 6, -6, 3, -3, 4, 2, 2, 1, 4, 2, 2, 1 }, { -12, 12, 12, -12, 12, -12, -12, 12, -6, -6, 6, 6, 6, 6, -6, -6, -8, 8, -4, 4, 8, -8, 4, -4, -6, 6, 6, -6, -6, 6, 6, -6, -4, -4, -2, -2, 4, 4, 2, 2, -3, -3, 3, 3, -3, -3, 3, 3, -4, 4, -2, 2, -4, 4, -2, 2, -2, -2, -1, -1, -2, -2, -1, -1 }, { 4, 0, -4, 0, -4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, -2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, -4, 0, -4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, -2, 0, -2, 0, 2, 0, -2, 0, 2, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { -12, 12, 12, -12, 12, -12, -12, 12, -8, -4, 8, 4, 8, 4, -8, -4, -6, 6, -6, 6, 6, -6, 6, -6, -6, 6, 6, -6, -6, 6, 6, -6, -4, -2, -4, -2, 4, 2, 4, 2, -4, -2, 4, 2, -4, -2, 4, 2, -3, 3, -3, 3, -3, 3, -3, 3, -2, -1, -2, -1, -2, -1, -2, -1 }, { 8, -8, -8, 8, -8, 8, 8, -8, 4, 4, -4, -4, -4, -4, 4, 4, 4, -4, 4, -4, -4, 4, -4, 4, 4, -4, -4, 4, 4, -4, -4, 4, 2, 2, 2, 2, -2, -2, -2, -2, 2, 2, -2, -2, 2, 2, -2, -2, 2, -2, 2, -2, 2, -2, 2, -2, 1, 1, 1, 1, 1, 1, 1, 1 } };

    /**
     * Samples x-coordinates
     */
    private final double[] xval;

    /**
     * Samples y-coordinates
     */
    private final double[] yval;

    /**
     * Samples z-coordinates
     */
    private final double[] zval;

    /**
     * Set of cubic splines pacthing the whole data grid
     */
    private final TricubicSplineFunction[][][] splines;

    /**
     * @param x Sample values of the x-coordinate, in increasing order.
     * @param y Sample values of the y-coordinate, in increasing order.
     * @param z Sample values of the y-coordinate, in increasing order.
     * @param f Values of the function on every grid point.
     * @param dFdX Values of the partial derivative of function with respect to x on every grid point.
     * @param dFdY Values of the partial derivative of function with respect to y on every grid point.
     * @param dFdZ Values of the partial derivative of function with respect to z on every grid point.
     * @param d2FdXdY Values of the cross partial derivative of function on every grid point.
     * @param d2FdXdZ Values of the cross partial derivative of function on every grid point.
     * @param d2FdYdZ Values of the cross partial derivative of function on every grid point.
     * @param d3FdXdYdZ Values of the cross partial derivative of function on every grid point.
     * @throws NoDataException if any of the arrays has zero length.
     * @throws DimensionMismatchException if the various arrays do not contain the expected number of elements.
     * @throws NonMonotonicSequenceException if {@code x}, {@code y} or {@code z} are not strictly increasing.
     */
    public TricubicSplineInterpolatingFunction(double[] x, double[] y, double[] z, double[][][] f, double[][][] dFdX, double[][][] dFdY, double[][][] dFdZ, double[][][] d2FdXdY, double[][][] d2FdXdZ, double[][][] d2FdYdZ, double[][][] d3FdXdYdZ) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138");
        final int xLen = x.length;
        final int yLen = y.length;
        final int zLen = z.length;
        if ((_mut93320 ? ((_mut93314 ? ((_mut93308 ? ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) && ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307)) : ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) || ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93309, _mut93310, _mut93311, _mut93312, _mut93313)) : ((_mut93308 ? ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) && ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307)) : ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) || ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93309, _mut93310, _mut93311, _mut93312, _mut93313))) && ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93315, _mut93316, _mut93317, _mut93318, _mut93319)) : ((_mut93314 ? ((_mut93308 ? ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) && ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307)) : ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) || ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93309, _mut93310, _mut93311, _mut93312, _mut93313)) : ((_mut93308 ? ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) && ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307)) : ((_mut93302 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93292, _mut93293, _mut93294, _mut93295, _mut93296) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93297, _mut93298, _mut93299, _mut93300, _mut93301))) || ROR_equals(z.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93303, _mut93304, _mut93305, _mut93306, _mut93307))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93309, _mut93310, _mut93311, _mut93312, _mut93313))) || ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93315, _mut93316, _mut93317, _mut93318, _mut93319)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xLen, f.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93321, _mut93322, _mut93323, _mut93324, _mut93325)) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (ROR_not_equals(xLen, dFdX.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93326, _mut93327, _mut93328, _mut93329, _mut93330)) {
            throw new DimensionMismatchException(xLen, dFdX.length);
        }
        if (ROR_not_equals(xLen, dFdY.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93331, _mut93332, _mut93333, _mut93334, _mut93335)) {
            throw new DimensionMismatchException(xLen, dFdY.length);
        }
        if (ROR_not_equals(xLen, dFdZ.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93336, _mut93337, _mut93338, _mut93339, _mut93340)) {
            throw new DimensionMismatchException(xLen, dFdZ.length);
        }
        if (ROR_not_equals(xLen, d2FdXdY.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93341, _mut93342, _mut93343, _mut93344, _mut93345)) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
        }
        if (ROR_not_equals(xLen, d2FdXdZ.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93346, _mut93347, _mut93348, _mut93349, _mut93350)) {
            throw new DimensionMismatchException(xLen, d2FdXdZ.length);
        }
        if (ROR_not_equals(xLen, d2FdYdZ.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93351, _mut93352, _mut93353, _mut93354, _mut93355)) {
            throw new DimensionMismatchException(xLen, d2FdYdZ.length);
        }
        if (ROR_not_equals(xLen, d3FdXdYdZ.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93356, _mut93357, _mut93358, _mut93359, _mut93360)) {
            throw new DimensionMismatchException(xLen, d3FdXdYdZ.length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        MathArrays.checkOrder(z);
        xval = x.clone();
        yval = y.clone();
        zval = z.clone();
        final int lastI = AOR_minus(xLen, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93361, _mut93362, _mut93363, _mut93364);
        final int lastJ = AOR_minus(yLen, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93365, _mut93366, _mut93367, _mut93368);
        final int lastK = AOR_minus(zLen, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93369, _mut93370, _mut93371, _mut93372);
        splines = new TricubicSplineFunction[lastI][lastJ][lastK];
        for (int i = 0; ROR_less(i, lastI, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93475, _mut93476, _mut93477, _mut93478, _mut93479); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138");
            if (ROR_not_equals(f[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93373, _mut93374, _mut93375, _mut93376, _mut93377)) {
                throw new DimensionMismatchException(f[i].length, yLen);
            }
            if (ROR_not_equals(dFdX[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93378, _mut93379, _mut93380, _mut93381, _mut93382)) {
                throw new DimensionMismatchException(dFdX[i].length, yLen);
            }
            if (ROR_not_equals(dFdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93383, _mut93384, _mut93385, _mut93386, _mut93387)) {
                throw new DimensionMismatchException(dFdY[i].length, yLen);
            }
            if (ROR_not_equals(dFdZ[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93388, _mut93389, _mut93390, _mut93391, _mut93392)) {
                throw new DimensionMismatchException(dFdZ[i].length, yLen);
            }
            if (ROR_not_equals(d2FdXdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93393, _mut93394, _mut93395, _mut93396, _mut93397)) {
                throw new DimensionMismatchException(d2FdXdY[i].length, yLen);
            }
            if (ROR_not_equals(d2FdXdZ[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93398, _mut93399, _mut93400, _mut93401, _mut93402)) {
                throw new DimensionMismatchException(d2FdXdZ[i].length, yLen);
            }
            if (ROR_not_equals(d2FdYdZ[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93403, _mut93404, _mut93405, _mut93406, _mut93407)) {
                throw new DimensionMismatchException(d2FdYdZ[i].length, yLen);
            }
            if (ROR_not_equals(d3FdXdYdZ[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93408, _mut93409, _mut93410, _mut93411, _mut93412)) {
                throw new DimensionMismatchException(d3FdXdYdZ[i].length, yLen);
            }
            final int ip1 = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93413, _mut93414, _mut93415, _mut93416);
            for (int j = 0; ROR_less(j, lastJ, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93470, _mut93471, _mut93472, _mut93473, _mut93474); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138");
                if (ROR_not_equals(f[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93417, _mut93418, _mut93419, _mut93420, _mut93421)) {
                    throw new DimensionMismatchException(f[i][j].length, zLen);
                }
                if (ROR_not_equals(dFdX[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93422, _mut93423, _mut93424, _mut93425, _mut93426)) {
                    throw new DimensionMismatchException(dFdX[i][j].length, zLen);
                }
                if (ROR_not_equals(dFdY[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93427, _mut93428, _mut93429, _mut93430, _mut93431)) {
                    throw new DimensionMismatchException(dFdY[i][j].length, zLen);
                }
                if (ROR_not_equals(dFdZ[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93432, _mut93433, _mut93434, _mut93435, _mut93436)) {
                    throw new DimensionMismatchException(dFdZ[i][j].length, zLen);
                }
                if (ROR_not_equals(d2FdXdY[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93437, _mut93438, _mut93439, _mut93440, _mut93441)) {
                    throw new DimensionMismatchException(d2FdXdY[i][j].length, zLen);
                }
                if (ROR_not_equals(d2FdXdZ[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93442, _mut93443, _mut93444, _mut93445, _mut93446)) {
                    throw new DimensionMismatchException(d2FdXdZ[i][j].length, zLen);
                }
                if (ROR_not_equals(d2FdYdZ[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93447, _mut93448, _mut93449, _mut93450, _mut93451)) {
                    throw new DimensionMismatchException(d2FdYdZ[i][j].length, zLen);
                }
                if (ROR_not_equals(d3FdXdYdZ[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93452, _mut93453, _mut93454, _mut93455, _mut93456)) {
                    throw new DimensionMismatchException(d3FdXdYdZ[i][j].length, zLen);
                }
                final int jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93457, _mut93458, _mut93459, _mut93460);
                for (int k = 0; ROR_less(k, lastK, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93465, _mut93466, _mut93467, _mut93468, _mut93469); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138");
                    final int kp1 = AOR_plus(k, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.TricubicSplineInterpolatingFunction_138", _mut93461, _mut93462, _mut93463, _mut93464);
                    final double[] beta = new double[] { f[i][j][k], f[ip1][j][k], f[i][jp1][k], f[ip1][jp1][k], f[i][j][kp1], f[ip1][j][kp1], f[i][jp1][kp1], f[ip1][jp1][kp1], dFdX[i][j][k], dFdX[ip1][j][k], dFdX[i][jp1][k], dFdX[ip1][jp1][k], dFdX[i][j][kp1], dFdX[ip1][j][kp1], dFdX[i][jp1][kp1], dFdX[ip1][jp1][kp1], dFdY[i][j][k], dFdY[ip1][j][k], dFdY[i][jp1][k], dFdY[ip1][jp1][k], dFdY[i][j][kp1], dFdY[ip1][j][kp1], dFdY[i][jp1][kp1], dFdY[ip1][jp1][kp1], dFdZ[i][j][k], dFdZ[ip1][j][k], dFdZ[i][jp1][k], dFdZ[ip1][jp1][k], dFdZ[i][j][kp1], dFdZ[ip1][j][kp1], dFdZ[i][jp1][kp1], dFdZ[ip1][jp1][kp1], d2FdXdY[i][j][k], d2FdXdY[ip1][j][k], d2FdXdY[i][jp1][k], d2FdXdY[ip1][jp1][k], d2FdXdY[i][j][kp1], d2FdXdY[ip1][j][kp1], d2FdXdY[i][jp1][kp1], d2FdXdY[ip1][jp1][kp1], d2FdXdZ[i][j][k], d2FdXdZ[ip1][j][k], d2FdXdZ[i][jp1][k], d2FdXdZ[ip1][jp1][k], d2FdXdZ[i][j][kp1], d2FdXdZ[ip1][j][kp1], d2FdXdZ[i][jp1][kp1], d2FdXdZ[ip1][jp1][kp1], d2FdYdZ[i][j][k], d2FdYdZ[ip1][j][k], d2FdYdZ[i][jp1][k], d2FdYdZ[ip1][jp1][k], d2FdYdZ[i][j][kp1], d2FdYdZ[ip1][j][kp1], d2FdYdZ[i][jp1][kp1], d2FdYdZ[ip1][jp1][kp1], d3FdXdYdZ[i][j][k], d3FdXdYdZ[ip1][j][k], d3FdXdYdZ[i][jp1][k], d3FdXdYdZ[ip1][jp1][k], d3FdXdYdZ[i][j][kp1], d3FdXdYdZ[ip1][j][kp1], d3FdXdYdZ[i][jp1][kp1], d3FdXdYdZ[ip1][jp1][kp1] };
                    splines[i][j][k] = new TricubicSplineFunction(computeSplineCoefficients(beta));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws OutOfRangeException if any of the variables is outside its interpolation range.
     */
    public double value(double x, double y, double z) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307");
        final int i = searchIndex(x, xval);
        if (ROR_equals(i, -1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93480, _mut93481, _mut93482, _mut93483, _mut93484)) {
            throw new OutOfRangeException(x, xval[0], xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93485, _mut93486, _mut93487, _mut93488)]);
        }
        final int j = searchIndex(y, yval);
        if (ROR_equals(j, -1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93489, _mut93490, _mut93491, _mut93492, _mut93493)) {
            throw new OutOfRangeException(y, yval[0], yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93494, _mut93495, _mut93496, _mut93497)]);
        }
        final int k = searchIndex(z, zval);
        if (ROR_equals(k, -1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93498, _mut93499, _mut93500, _mut93501, _mut93502)) {
            throw new OutOfRangeException(z, zval[0], zval[AOR_minus(zval.length, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93503, _mut93504, _mut93505, _mut93506)]);
        }
        final double xN = AOR_divide((AOR_minus(x, xval[i], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93507, _mut93508, _mut93509, _mut93510)), (AOR_minus(xval[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93511, _mut93512, _mut93513, _mut93514)], xval[i], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93515, _mut93516, _mut93517, _mut93518)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93519, _mut93520, _mut93521, _mut93522);
        final double yN = AOR_divide((AOR_minus(y, yval[j], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93523, _mut93524, _mut93525, _mut93526)), (AOR_minus(yval[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93527, _mut93528, _mut93529, _mut93530)], yval[j], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93531, _mut93532, _mut93533, _mut93534)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93535, _mut93536, _mut93537, _mut93538);
        final double zN = AOR_divide((AOR_minus(z, zval[k], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93539, _mut93540, _mut93541, _mut93542)), (AOR_minus(zval[AOR_plus(k, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93543, _mut93544, _mut93545, _mut93546)], zval[k], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93547, _mut93548, _mut93549, _mut93550)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.value_307", _mut93551, _mut93552, _mut93553, _mut93554);
        return splines[i][j][k].value(xN, yN, zN);
    }

    /**
     * @param c Coordinate.
     * @param val Coordinate samples.
     * @return the index in {@code val} corresponding to the interval containing {@code c}, or {@code -1}
     *   if {@code c} is out of the range defined by the end values of {@code val}.
     */
    private int searchIndex(double c, double[] val) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335");
        if (ROR_less(c, val[0], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335", _mut93555, _mut93556, _mut93557, _mut93558, _mut93559)) {
            return -1;
        }
        final int max = val.length;
        for (int i = 1; ROR_less(i, max, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335", _mut93569, _mut93570, _mut93571, _mut93572, _mut93573); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335");
            if (ROR_less_equals(c, val[i], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335", _mut93560, _mut93561, _mut93562, _mut93563, _mut93564)) {
                return AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.searchIndex_335", _mut93565, _mut93566, _mut93567, _mut93568);
            }
        }
        return -1;
    }

    /**
     * Compute the spline coefficients from the list of function values and
     * function partial derivatives values at the four corners of a grid
     * element. They must be specified in the following order:
     * <ul>
     *  <li>f(0,0,0)</li>
     *  <li>f(1,0,0)</li>
     *  <li>f(0,1,0)</li>
     *  <li>f(1,1,0)</li>
     *  <li>f(0,0,1)</li>
     *  <li>f(1,0,1)</li>
     *  <li>f(0,1,1)</li>
     *  <li>f(1,1,1)</li>
     *
     *  <li>f<sub>x</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>x</sub>(1,1,1)</li>
     *
     *  <li>f<sub>y</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>y</sub>(1,1,1)</li>
     *
     *  <li>f<sub>z</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>z</sub>(1,1,1)</li>
     *
     *  <li>f<sub>xy</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>xy</sub>(1,1,1)</li>
     *
     *  <li>f<sub>xz</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>xz</sub>(1,1,1)</li>
     *
     *  <li>f<sub>yz</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>yz</sub>(1,1,1)</li>
     *
     *  <li>f<sub>xyz</sub>(0,0,0)</li>
     *  <li>... <em>(same order as above)</em></li>
     *  <li>f<sub>xyz</sub>(1,1,1)</li>
     * </ul>
     * where the subscripts indicate the partial derivative with respect to
     * the corresponding variable(s).
     *
     * @param beta List of function values and function partial derivatives values.
     * @return the spline coefficients.
     */
    private double[] computeSplineCoefficients(double[] beta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398");
        final int sz = 64;
        final double[] a = new double[sz];
        for (int i = 0; ROR_less(i, sz, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398", _mut93583, _mut93584, _mut93585, _mut93586, _mut93587); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398");
            double result = 0;
            final double[] row = AINV[i];
            for (int j = 0; ROR_less(j, sz, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398", _mut93578, _mut93579, _mut93580, _mut93581, _mut93582); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398");
                result += AOR_multiply(row[j], beta[j], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction.computeSplineCoefficients_398", _mut93574, _mut93575, _mut93576, _mut93577);
            }
            a[i] = result;
        }
        return a;
    }
}

/**
 * 3D-spline function.
 */
class TricubicSplineFunction implements TrivariateFunction {

    @Conditional
    public static boolean _mut93588 = false, _mut93589 = false, _mut93590 = false, _mut93591 = false, _mut93592 = false, _mut93593 = false, _mut93594 = false, _mut93595 = false, _mut93596 = false, _mut93597 = false, _mut93598 = false, _mut93599 = false, _mut93600 = false, _mut93601 = false, _mut93602 = false, _mut93603 = false, _mut93604 = false, _mut93605 = false, _mut93606 = false, _mut93607 = false, _mut93608 = false, _mut93609 = false, _mut93610 = false, _mut93611 = false, _mut93612 = false, _mut93613 = false, _mut93614 = false, _mut93615 = false, _mut93616 = false, _mut93617 = false, _mut93618 = false, _mut93619 = false, _mut93620 = false, _mut93621 = false, _mut93622 = false, _mut93623 = false, _mut93624 = false, _mut93625 = false, _mut93626 = false, _mut93627 = false, _mut93628 = false, _mut93629 = false, _mut93630 = false, _mut93631 = false, _mut93632 = false, _mut93633 = false, _mut93634 = false, _mut93635 = false, _mut93636 = false, _mut93637 = false, _mut93638 = false, _mut93639 = false, _mut93640 = false, _mut93641 = false, _mut93642 = false, _mut93643 = false, _mut93644 = false, _mut93645 = false, _mut93646 = false, _mut93647 = false, _mut93648 = false, _mut93649 = false, _mut93650 = false, _mut93651 = false, _mut93652 = false, _mut93653 = false, _mut93654 = false, _mut93655 = false, _mut93656 = false, _mut93657 = false, _mut93658 = false, _mut93659 = false, _mut93660 = false, _mut93661 = false, _mut93662 = false, _mut93663 = false, _mut93664 = false, _mut93665 = false, _mut93666 = false, _mut93667 = false, _mut93668 = false, _mut93669 = false, _mut93670 = false, _mut93671 = false, _mut93672 = false, _mut93673 = false, _mut93674 = false, _mut93675 = false, _mut93676 = false, _mut93677 = false, _mut93678 = false, _mut93679 = false, _mut93680 = false, _mut93681 = false, _mut93682 = false, _mut93683 = false, _mut93684 = false, _mut93685 = false, _mut93686 = false, _mut93687 = false, _mut93688 = false, _mut93689 = false, _mut93690 = false, _mut93691 = false, _mut93692 = false, _mut93693 = false, _mut93694 = false, _mut93695 = false, _mut93696 = false, _mut93697 = false, _mut93698 = false, _mut93699 = false, _mut93700 = false, _mut93701 = false, _mut93702 = false;

    /**
     * Number of points.
     */
    private static final short N = 4;

    /**
     * Coefficients
     */
    private final double[][][] a = new double[N][N][N];

    /**
     * @param aV List of spline coefficients.
     */
    TricubicSplineFunction(double[] aV) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429");
        for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93614, _mut93615, _mut93616, _mut93617, _mut93618); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429");
            for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93609, _mut93610, _mut93611, _mut93612, _mut93613); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429");
                for (int k = 0; ROR_less(k, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93604, _mut93605, _mut93606, _mut93607, _mut93608); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429");
                    a[i][j][k] = aV[AOR_plus(i, AOR_multiply(N, (AOR_plus(j, AOR_multiply(N, k, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93588, _mut93589, _mut93590, _mut93591), "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93592, _mut93593, _mut93594, _mut93595)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93596, _mut93597, _mut93598, _mut93599), "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.TricubicSplineFunction_429", _mut93600, _mut93601, _mut93602, _mut93603)];
                }
            }
        }
    }

    /**
     * @param x x-coordinate of the interpolation point.
     * @param y y-coordinate of the interpolation point.
     * @param z z-coordinate of the interpolation point.
     * @return the interpolated value.
     * @throws OutOfRangeException if {@code x}, {@code y} or
     * {@code z} are not in the interval {@code [0, 1]}.
     */
    public double value(double x, double y, double z) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447");
        if ((_mut93629 ? (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93619, _mut93620, _mut93621, _mut93622, _mut93623) && ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93624, _mut93625, _mut93626, _mut93627, _mut93628)) : (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93619, _mut93620, _mut93621, _mut93622, _mut93623) || ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93624, _mut93625, _mut93626, _mut93627, _mut93628)))) {
            throw new OutOfRangeException(x, 0, 1);
        }
        if ((_mut93640 ? (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93630, _mut93631, _mut93632, _mut93633, _mut93634) && ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93635, _mut93636, _mut93637, _mut93638, _mut93639)) : (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93630, _mut93631, _mut93632, _mut93633, _mut93634) || ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93635, _mut93636, _mut93637, _mut93638, _mut93639)))) {
            throw new OutOfRangeException(y, 0, 1);
        }
        if ((_mut93651 ? (ROR_less(z, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93641, _mut93642, _mut93643, _mut93644, _mut93645) && ROR_greater(z, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93646, _mut93647, _mut93648, _mut93649, _mut93650)) : (ROR_less(z, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93641, _mut93642, _mut93643, _mut93644, _mut93645) || ROR_greater(z, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93646, _mut93647, _mut93648, _mut93649, _mut93650)))) {
            throw new OutOfRangeException(z, 0, 1);
        }
        final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93652, _mut93653, _mut93654, _mut93655);
        final double x3 = AOR_multiply(x2, x, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93656, _mut93657, _mut93658, _mut93659);
        final double[] pX = { 1, x, x2, x3 };
        final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93660, _mut93661, _mut93662, _mut93663);
        final double y3 = AOR_multiply(y2, y, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93664, _mut93665, _mut93666, _mut93667);
        final double[] pY = { 1, y, y2, y3 };
        final double z2 = AOR_multiply(z, z, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93668, _mut93669, _mut93670, _mut93671);
        final double z3 = AOR_multiply(z2, z, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93672, _mut93673, _mut93674, _mut93675);
        final double[] pZ = { 1, z, z2, z3 };
        double result = 0;
        for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93698, _mut93699, _mut93700, _mut93701, _mut93702); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447");
            for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93693, _mut93694, _mut93695, _mut93696, _mut93697); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447");
                for (int k = 0; ROR_less(k, N, "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93688, _mut93689, _mut93690, _mut93691, _mut93692); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447");
                    result += AOR_multiply(AOR_multiply(AOR_multiply(a[i][j][k], pX[i], "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93676, _mut93677, _mut93678, _mut93679), pY[j], "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93680, _mut93681, _mut93682, _mut93683), pZ[k], "org.apache.commons.math3.analysis.interpolation.TricubicSplineFunction.value_447", _mut93684, _mut93685, _mut93686, _mut93687);
                }
            }
        }
        return result;
    }
}
