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
package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This TansformerMap automates the transformation of mixed object types.
 * It provides a means to set NumberTransformers that will be selected
 * based on the Class of the object handed to the Maps
 * <code>double transform(Object o)</code> method.
 */
public class TransformerMap implements NumberTransformer, Serializable {

    @Conditional
    public static boolean _mut49661 = false, _mut49662 = false, _mut49663 = false, _mut49664 = false, _mut49665 = false, _mut49666 = false, _mut49667 = false, _mut49668 = false, _mut49669 = false, _mut49670 = false, _mut49671 = false, _mut49672 = false, _mut49673 = false, _mut49674 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 4605318041528645258L;

    /**
     * A default Number Transformer for Numbers and numeric Strings.
     */
    private NumberTransformer defaultTransformer = null;

    /**
     * The internal Map.
     */
    private Map<Class<?>, NumberTransformer> map = null;

    /**
     * Build a map containing only the default transformer.
     */
    public TransformerMap() {
        map = new HashMap<Class<?>, NumberTransformer>();
        defaultTransformer = new DefaultTransformer();
    }

    /**
     * Tests if a Class is present in the TransformerMap.
     * @param key Class to check
     * @return true|false
     */
    public boolean containsClass(Class<?> key) {
        return map.containsKey(key);
    }

    /**
     * Tests if a NumberTransformer is present in the TransformerMap.
     * @param value NumberTransformer to check
     * @return true|false
     */
    public boolean containsTransformer(NumberTransformer value) {
        return map.containsValue(value);
    }

    /**
     * Returns the Transformer that is mapped to a class
     * if mapping is not present, this returns null.
     * @param key The Class of the object
     * @return the mapped NumberTransformer or null.
     */
    public NumberTransformer getTransformer(Class<?> key) {
        return map.get(key);
    }

    /**
     * Sets a Class to Transformer Mapping in the Map. If
     * the Class is already present, this overwrites that
     * mapping.
     * @param key The Class
     * @param transformer The NumberTransformer
     * @return the replaced transformer if one is present
     */
    public NumberTransformer putTransformer(Class<?> key, NumberTransformer transformer) {
        return map.put(key, transformer);
    }

    /**
     * Removes a Class to Transformer Mapping in the Map.
     * @param key The Class
     * @return the removed transformer if one is present or
     * null if none was present.
     */
    public NumberTransformer removeTransformer(Class<?> key) {
        return map.remove(key);
    }

    /**
     * Clears all the Class to Transformer mappings.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Returns the Set of Classes used as keys in the map.
     * @return Set of Classes
     */
    public Set<Class<?>> classes() {
        return map.keySet();
    }

    /**
     * Returns the Set of NumberTransformers used as values
     * in the map.
     * @return Set of NumberTransformers
     */
    public Collection<NumberTransformer> transformers() {
        return map.values();
    }

    /**
     * Attempts to transform the Object against the map of
     * NumberTransformers. Otherwise it returns Double.NaN.
     *
     * @param o the Object to be transformed.
     * @return the double value of the Object.
     * @throws MathIllegalArgumentException if the Object can not be
     * transformed into a Double.
     * @see org.apache.commons.math3.util.NumberTransformer#transform(java.lang.Object)
     */
    public double transform(Object o) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.TransformerMap.transform_140");
        double value = Double.NaN;
        if ((_mut49661 ? (o instanceof Number && o instanceof String) : (o instanceof Number || o instanceof String))) {
            value = defaultTransformer.transform(o);
        } else {
            NumberTransformer trans = getTransformer(o.getClass());
            if (trans != null) {
                value = trans.transform(o);
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.TransformerMap.equals_156");
        if (this == other) {
            return true;
        }
        if (other instanceof TransformerMap) {
            TransformerMap rhs = (TransformerMap) other;
            if (!defaultTransformer.equals(rhs.defaultTransformer)) {
                return false;
            }
            if (ROR_not_equals(map.size(), rhs.map.size(), "org.apache.commons.math3.util.TransformerMap.equals_156", _mut49662, _mut49663, _mut49664, _mut49665, _mut49666)) {
                return false;
            }
            for (Map.Entry<Class<?>, NumberTransformer> entry : map.entrySet()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.TransformerMap.equals_156");
                if (!entry.getValue().equals(rhs.map.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.TransformerMap.hashCode_180");
        int hash = defaultTransformer.hashCode();
        for (NumberTransformer t : map.values()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.TransformerMap.hashCode_180");
            hash = AOR_plus(AOR_multiply(hash, 31, "org.apache.commons.math3.util.TransformerMap.hashCode_180", _mut49667, _mut49668, _mut49669, _mut49670), t.hashCode(), "org.apache.commons.math3.util.TransformerMap.hashCode_180", _mut49671, _mut49672, _mut49673, _mut49674);
        }
        return hash;
    }
}
