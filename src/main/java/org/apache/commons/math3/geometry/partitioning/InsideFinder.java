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
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utility class checking if inside nodes can be found
 * on the plus and minus sides of an hyperplane.
 * @param <S> Type of the space.
 * @since 3.4
 */
class InsideFinder<S extends Space> {

    @Conditional
    public static boolean _mut86058 = false, _mut86059 = false, _mut86060 = false, _mut86061 = false, _mut86062 = false, _mut86063 = false, _mut86064 = false;

    /**
     * Region on which to operate.
     */
    private final Region<S> region;

    /**
     * Indicator of inside leaf nodes found on the plus side.
     */
    private boolean plusFound;

    /**
     * Indicator of inside leaf nodes found on the plus side.
     */
    private boolean minusFound;

    /**
     * Simple constructor.
     * @param region region on which to operate
     */
    InsideFinder(final Region<S> region) {
        this.region = region;
        plusFound = false;
        minusFound = false;
    }

    /**
     * Search recursively for inside leaf nodes on each side of the given hyperplane.
     *
     * <p>The algorithm used here is directly derived from the one
     * described in section III (<i>Binary Partitioning of a BSP
     * Tree</i>) of the Bruce Naylor, John Amanatides and William
     * Thibault paper <a
     * href="http://www.cs.yorku.ca/~amana/research/bsptSetOp.pdf">Merging
     * BSP Trees Yields Polyhedral Set Operations</a> Proc. Siggraph
     * '90, Computer Graphics 24(4), August 1990, pp 115-124, published
     * by the Association for Computing Machinery (ACM)..</p>
     *
     * @param node current BSP tree node
     * @param sub sub-hyperplane
     */
    public void recurseSides(final BSPTree<S> node, final SubHyperplane<S> sub) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.partitioning.InsideFinder.recurseSides_60");
        if (node.getCut() == null) {
            if ((Boolean) node.getAttribute()) {
                // this is an inside cell expanding across the hyperplane
                plusFound = true;
                minusFound = true;
            }
            return;
        }
        final Hyperplane<S> hyperplane = node.getCut().getHyperplane();
        final SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
        switch(split.getSide()) {
            case PLUS:
                // the sub-hyperplane is entirely in the plus sub-tree
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!region.isEmpty(node.getMinus())) {
                        plusFound = true;
                    }
                } else {
                    if (!region.isEmpty(node.getMinus())) {
                        minusFound = true;
                    }
                }
                if (!((_mut86058 ? (plusFound || minusFound) : (plusFound && minusFound)))) {
                    recurseSides(node.getPlus(), sub);
                }
                break;
            case MINUS:
                // the sub-hyperplane is entirely in the minus sub-tree
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!region.isEmpty(node.getPlus())) {
                        plusFound = true;
                    }
                } else {
                    if (!region.isEmpty(node.getPlus())) {
                        minusFound = true;
                    }
                }
                if (!((_mut86059 ? (plusFound || minusFound) : (plusFound && minusFound)))) {
                    recurseSides(node.getMinus(), sub);
                }
                break;
            case BOTH:
                // explore first the plus sub-tree
                recurseSides(node.getPlus(), split.getPlus());
                // if needed, explore the minus sub-tree
                if (!((_mut86060 ? (plusFound || minusFound) : (plusFound && minusFound)))) {
                    recurseSides(node.getMinus(), split.getMinus());
                }
                break;
            default:
                // the sub-hyperplane and the cut sub-hyperplane share the same hyperplane
                if (node.getCut().getHyperplane().sameOrientationAs(sub.getHyperplane())) {
                    if ((_mut86063 ? ((node.getPlus().getCut() != null) && ((Boolean) node.getPlus().getAttribute())) : ((node.getPlus().getCut() != null) || ((Boolean) node.getPlus().getAttribute())))) {
                        plusFound = true;
                    }
                    if ((_mut86064 ? ((node.getMinus().getCut() != null) && ((Boolean) node.getMinus().getAttribute())) : ((node.getMinus().getCut() != null) || ((Boolean) node.getMinus().getAttribute())))) {
                        minusFound = true;
                    }
                } else {
                    if ((_mut86061 ? ((node.getPlus().getCut() != null) && ((Boolean) node.getPlus().getAttribute())) : ((node.getPlus().getCut() != null) || ((Boolean) node.getPlus().getAttribute())))) {
                        minusFound = true;
                    }
                    if ((_mut86062 ? ((node.getMinus().getCut() != null) && ((Boolean) node.getMinus().getAttribute())) : ((node.getMinus().getCut() != null) || ((Boolean) node.getMinus().getAttribute())))) {
                        plusFound = true;
                    }
                }
        }
    }

    /**
     * Check if inside leaf nodes have been found on the plus side.
     * @return true if inside leaf nodes have been found on the plus side
     */
    public boolean plusFound() {
        return plusFound;
    }

    /**
     * Check if inside leaf nodes have been found on the minus side.
     * @return true if inside leaf nodes have been found on the minus side
     */
    public boolean minusFound() {
        return minusFound;
    }
}
