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
package org.apache.commons.math3.geometry.enclosing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class implementing Emo Welzl algorithm to find the smallest enclosing ball in linear time.
 * <p>
 * The class implements the algorithm described in paper <a
 * href="http://www.inf.ethz.ch/personal/emo/PublFiles/SmallEnclDisk_LNCS555_91.pdf">Smallest
 * Enclosing Disks (Balls and Ellipsoids)</a> by Emo Welzl, Lecture Notes in Computer Science
 * 555 (1991) 359-370. The pivoting improvement published in the paper <a
 * href="http://www.inf.ethz.ch/personal/gaertner/texts/own_work/esa99_final.pdf">Fast and
 * Robust Smallest Enclosing Balls</a>, by Bernd Gärtner and further modified in
 * paper <a
 * href=http://www.idt.mdh.se/kurser/ct3340/ht12/MINICONFERENCE/FinalPapers/ircse12_submission_30.pdf">
 * Efficient Computation of Smallest Enclosing Balls in Three Dimensions</a> by Linus Källberg
 * to avoid performing local copies of data have been included.
 * </p>
 * @param <S> Space type.
 * @param <P> Point type.
 * @since 3.3
 */
public class WelzlEncloser<S extends Space, P extends Point<S>> implements Encloser<S, P> {

    @Conditional
    public static boolean _mut78974 = false, _mut78975 = false, _mut78976 = false, _mut78977 = false, _mut78978 = false, _mut78979 = false, _mut78980 = false, _mut78981 = false, _mut78982 = false, _mut78983 = false, _mut78984 = false, _mut78985 = false, _mut78986 = false, _mut78987 = false, _mut78988 = false, _mut78989 = false, _mut78990 = false, _mut78991 = false, _mut78992 = false, _mut78993 = false, _mut78994 = false, _mut78995 = false, _mut78996 = false, _mut78997 = false, _mut78998 = false, _mut78999 = false, _mut79000 = false, _mut79001 = false, _mut79002 = false, _mut79003 = false, _mut79004 = false, _mut79005 = false, _mut79006 = false, _mut79007 = false, _mut79008 = false, _mut79009 = false, _mut79010 = false, _mut79011 = false, _mut79012 = false, _mut79013 = false, _mut79014 = false, _mut79015 = false;

    /**
     * Tolerance below which points are consider to be identical.
     */
    private final double tolerance;

    /**
     * Generator for balls on support.
     */
    private final SupportBallGenerator<S, P> generator;

    /**
     * Simple constructor.
     * @param tolerance below which points are consider to be identical
     * @param generator generator for balls on support
     */
    public WelzlEncloser(final double tolerance, final SupportBallGenerator<S, P> generator) {
        this.tolerance = tolerance;
        this.generator = generator;
    }

    /**
     * {@inheritDoc}
     */
    public EnclosingBall<S, P> enclose(final Iterable<P> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.enclose_61");
        if ((_mut78974 ? (points == null && !points.iterator().hasNext()) : (points == null || !points.iterator().hasNext()))) {
            // return an empty ball
            return generator.ballOnSupport(new ArrayList<P>());
        }
        // Emo Welzl algorithm with Bernd Gärtner and Linus Källberg improvements
        return pivotingBall(points);
    }

    /**
     * Compute enclosing ball using Gärtner's pivoting heuristic.
     * @param points points to be enclosed
     * @return enclosing ball
     */
    private EnclosingBall<S, P> pivotingBall(final Iterable<P> points) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.pivotingBall_77");
        final P first = points.iterator().next();
        final List<P> extreme = new ArrayList<P>(AOR_plus(first.getSpace().getDimension(), 1, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.pivotingBall_77", _mut78975, _mut78976, _mut78977, _mut78978));
        final List<P> support = new ArrayList<P>(AOR_plus(first.getSpace().getDimension(), 1, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.pivotingBall_77", _mut78979, _mut78980, _mut78981, _mut78982));
        // start with only first point selected as a candidate support
        extreme.add(first);
        EnclosingBall<S, P> ball = moveToFrontBall(extreme, extreme.size(), support);
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.pivotingBall_77");
            // select the point farthest to current ball
            final P farthest = selectFarthest(points, ball);
            if (ball.contains(farthest, tolerance)) {
                // we have found a ball containing all points
                return ball;
            }
            // recurse search, restricted to the small subset containing support and farthest point
            support.clear();
            support.add(farthest);
            EnclosingBall<S, P> savedBall = ball;
            ball = moveToFrontBall(extreme, extreme.size(), support);
            if (ROR_less(ball.getRadius(), savedBall.getRadius(), "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.pivotingBall_77", _mut78983, _mut78984, _mut78985, _mut78986, _mut78987)) {
                // this should never happen
                throw new MathInternalError();
            }
            // according to Gärtner's heuristic
            extreme.add(0, farthest);
            // prune the least interesting points
            extreme.subList(ball.getSupportSize(), extreme.size()).clear();
        }
    }

    /**
     * Compute enclosing ball using Welzl's move to front heuristic.
     * @param extreme subset of extreme points
     * @param nbExtreme number of extreme points to consider
     * @param support points that must belong to the ball support
     * @return enclosing ball, for the extreme subset only
     */
    private EnclosingBall<S, P> moveToFrontBall(final List<P> extreme, final int nbExtreme, final List<P> support) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124");
        // create a new ball on the prescribed support
        EnclosingBall<S, P> ball = generator.ballOnSupport(support);
        if (ROR_less_equals(ball.getSupportSize(), ball.getCenter().getSpace().getDimension(), "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124", _mut78988, _mut78989, _mut78990, _mut78991, _mut78992)) {
            for (int i = 0; ROR_less(i, nbExtreme, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124", _mut79006, _mut79007, _mut79008, _mut79009, _mut79010); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124");
                final P pi = extreme.get(i);
                if (!ball.contains(pi, tolerance)) {
                    // enlarge the ball by adding it to the support
                    support.add(pi);
                    ball = moveToFrontBall(extreme, i, support);
                    support.remove(AOR_minus(support.size(), 1, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124", _mut78993, _mut78994, _mut78995, _mut78996));
                    // according to Welzl's heuristic
                    for (int j = i; ROR_greater(j, 0, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124", _mut79001, _mut79002, _mut79003, _mut79004, _mut79005); --j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124");
                        extreme.set(j, extreme.get(AOR_minus(j, 1, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.moveToFrontBall_124", _mut78997, _mut78998, _mut78999, _mut79000)));
                    }
                    extreme.set(0, pi);
                }
            }
        }
        return ball;
    }

    /**
     * Select the point farthest to the current ball.
     * @param points points to be enclosed
     * @param ball current ball
     * @return farthest point
     */
    public P selectFarthest(final Iterable<P> points, final EnclosingBall<S, P> ball) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.selectFarthest_163");
        final P center = ball.getCenter();
        P farthest = null;
        double dMax = -1.0;
        for (final P point : points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.enclosing.WelzlEncloser.selectFarthest_163");
            final double d = point.distance(center);
            if (ROR_greater(d, dMax, "org.apache.commons.math3.geometry.enclosing.WelzlEncloser.selectFarthest_163", _mut79011, _mut79012, _mut79013, _mut79014, _mut79015)) {
                farthest = point;
                dMax = d;
            }
        }
        return farthest;
    }
}
