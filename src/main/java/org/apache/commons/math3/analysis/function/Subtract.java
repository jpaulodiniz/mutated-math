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
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.BivariateFunction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Subtract the second operand from the first.
 *
 * @since 3.0
 */
public class Subtract implements BivariateFunction {

    @Conditional
    public static boolean _mut91946 = false, _mut91947 = false, _mut91948 = false, _mut91949 = false;

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Subtract.value_29");
        return AOR_minus(x, y, "org.apache.commons.math3.analysis.function.Subtract.value_29", _mut91946, _mut91947, _mut91948, _mut91949);
    }
}
