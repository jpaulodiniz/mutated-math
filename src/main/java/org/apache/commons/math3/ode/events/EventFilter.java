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
package org.apache.commons.math3.ode.events;

import java.util.Arrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class EventFilter implements EventHandler {

    @Conditional
    public static boolean _mut12399 = false, _mut12400 = false, _mut12401 = false, _mut12402 = false, _mut12403 = false, _mut12404 = false, _mut12405 = false, _mut12406 = false, _mut12407 = false, _mut12408 = false, _mut12409 = false, _mut12410 = false, _mut12411 = false, _mut12412 = false, _mut12413 = false, _mut12414 = false, _mut12415 = false, _mut12416 = false, _mut12417 = false, _mut12418 = false, _mut12419 = false, _mut12420 = false, _mut12421 = false, _mut12422 = false, _mut12423 = false, _mut12424 = false, _mut12425 = false, _mut12426 = false, _mut12427 = false, _mut12428 = false, _mut12429 = false, _mut12430 = false, _mut12431 = false, _mut12432 = false, _mut12433 = false, _mut12434 = false, _mut12435 = false, _mut12436 = false, _mut12437 = false, _mut12438 = false, _mut12439 = false, _mut12440 = false, _mut12441 = false, _mut12442 = false, _mut12443 = false, _mut12444 = false, _mut12445 = false, _mut12446 = false, _mut12447 = false, _mut12448 = false, _mut12449 = false, _mut12450 = false, _mut12451 = false, _mut12452 = false, _mut12453 = false;

    /**
     * Number of past transformers updates stored.
     */
    private static final int HISTORY_SIZE = 100;

    /**
     * Wrapped event handler.
     */
    private final EventHandler rawHandler;

    /**
     * Filter to use.
     */
    private final FilterType filter;

    /**
     * Transformers of the g function.
     */
    private final Transformer[] transformers;

    /**
     * Update time of the transformers.
     */
    private final double[] updates;

    /**
     * Indicator for forward integration.
     */
    private boolean forward;

    /**
     * Extreme time encountered so far.
     */
    private double extremeT;

    /**
     * Wrap an {@link EventHandler event handler}.
     * @param rawHandler event handler to wrap
     * @param filter filter to use
     */
    public EventFilter(final EventHandler rawHandler, final FilterType filter) {
        this.rawHandler = rawHandler;
        this.filter = filter;
        this.transformers = new Transformer[HISTORY_SIZE];
        this.updates = new double[HISTORY_SIZE];
    }

    /**
     *  {@inheritDoc}
     */
    public void init(double t0, double[] y0, double t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventFilter.init_90");
        // delegate to raw handler
        rawHandler.init(t0, y0, t);
        // initialize events triggering logic
        forward = ROR_greater_equals(t, t0, "org.apache.commons.math3.ode.events.EventFilter.init_90", _mut12399, _mut12400, _mut12401, _mut12402, _mut12403);
        extremeT = forward ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        Arrays.fill(transformers, Transformer.UNINITIALIZED);
        Arrays.fill(updates, extremeT);
    }

    /**
     *  {@inheritDoc}
     */
    public double g(double t, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventFilter.g_104");
        final double rawG = rawHandler.g(t, y);
        // search which transformer should be applied to g
        if (forward) {
            final int last = AOR_minus(transformers.length, 1, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12435, _mut12436, _mut12437, _mut12438);
            if (ROR_less(extremeT, t, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12439, _mut12440, _mut12441, _mut12442, _mut12443)) {
                // check if a new rough root has been crossed
                final Transformer previous = transformers[last];
                final Transformer next = filter.selectTransformer(previous, rawG, forward);
                if (next != previous) {
                    // is not applied too close of the root
                    System.arraycopy(updates, 1, updates, 0, last);
                    System.arraycopy(transformers, 1, transformers, 0, last);
                    updates[last] = extremeT;
                    transformers[last] = next;
                }
                extremeT = t;
                // apply the transform
                return next.transformed(rawG);
            } else {
                // select the transformer
                for (int i = last; ROR_greater(i, 0, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12449, _mut12450, _mut12451, _mut12452, _mut12453); --i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventFilter.g_104");
                    if (ROR_less_equals(updates[i], t, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12444, _mut12445, _mut12446, _mut12447, _mut12448)) {
                        // apply the transform
                        return transformers[i].transformed(rawG);
                    }
                }
                return transformers[0].transformed(rawG);
            }
        } else {
            if (ROR_less(t, extremeT, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12404, _mut12405, _mut12406, _mut12407, _mut12408)) {
                // check if a new rough root has been crossed
                final Transformer previous = transformers[0];
                final Transformer next = filter.selectTransformer(previous, rawG, forward);
                if (next != previous) {
                    // is not applied too close of the root
                    System.arraycopy(updates, 0, updates, 1, AOR_minus(updates.length, 1, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12427, _mut12428, _mut12429, _mut12430));
                    System.arraycopy(transformers, 0, transformers, 1, AOR_minus(transformers.length, 1, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12431, _mut12432, _mut12433, _mut12434));
                    updates[0] = extremeT;
                    transformers[0] = next;
                }
                extremeT = t;
                // apply the transform
                return next.transformed(rawG);
            } else {
                // select the transformer
                for (int i = 0; ROR_less(i, AOR_minus(updates.length, 1, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12414, _mut12415, _mut12416, _mut12417), "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12418, _mut12419, _mut12420, _mut12421, _mut12422); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventFilter.g_104");
                    if (ROR_less_equals(t, updates[i], "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12409, _mut12410, _mut12411, _mut12412, _mut12413)) {
                        // apply the transform
                        return transformers[i].transformed(rawG);
                    }
                }
                return transformers[AOR_minus(updates.length, 1, "org.apache.commons.math3.ode.events.EventFilter.g_104", _mut12423, _mut12424, _mut12425, _mut12426)].transformed(rawG);
            }
        }
    }

    /**
     *  {@inheritDoc}
     */
    public Action eventOccurred(double t, double[] y, boolean increasing) {
        // delegate to raw handler, fixing increasing status on the fly
        return rawHandler.eventOccurred(t, y, filter.getTriggeredIncreasing());
    }

    /**
     *  {@inheritDoc}
     */
    public void resetState(double t, double[] y) {
        // delegate to raw handler
        rawHandler.resetState(t, y);
    }
}
