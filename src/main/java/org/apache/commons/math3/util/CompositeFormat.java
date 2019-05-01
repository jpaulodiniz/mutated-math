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

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for formatters of composite objects (complex numbers, vectors ...).
 */
public class CompositeFormat {

    @Conditional
    public static boolean _mut40285 = false, _mut40286 = false, _mut40287 = false, _mut40288 = false, _mut40289 = false, _mut40290 = false, _mut40291 = false, _mut40292 = false, _mut40293 = false, _mut40294 = false, _mut40295 = false, _mut40296 = false, _mut40297 = false, _mut40298 = false, _mut40299 = false, _mut40300 = false, _mut40301 = false, _mut40302 = false, _mut40303 = false, _mut40304 = false, _mut40305 = false, _mut40306 = false, _mut40307 = false, _mut40308 = false, _mut40309 = false, _mut40310 = false, _mut40311 = false, _mut40312 = false, _mut40313 = false, _mut40314 = false, _mut40315 = false, _mut40316 = false, _mut40317 = false, _mut40318 = false, _mut40319 = false, _mut40320 = false, _mut40321 = false, _mut40322 = false, _mut40323 = false, _mut40324 = false, _mut40325 = false, _mut40326 = false, _mut40327 = false, _mut40328 = false, _mut40329 = false, _mut40330 = false, _mut40331 = false, _mut40332 = false, _mut40333 = false, _mut40334 = false, _mut40335 = false, _mut40336 = false, _mut40337 = false, _mut40338 = false, _mut40339 = false, _mut40340 = false, _mut40341 = false, _mut40342 = false, _mut40343 = false, _mut40344 = false, _mut40345 = false, _mut40346 = false, _mut40347 = false, _mut40348 = false, _mut40349 = false, _mut40350 = false, _mut40351 = false;

    /**
     * Class contains only static methods.
     */
    private CompositeFormat() {
    }

    /**
     * Create a default number format.  The default number format is based on
     * {@link NumberFormat#getInstance()} with the only customizing that the
     * maximum number of fraction digits is set to 10.
     * @return the default number format.
     */
    public static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    /**
     * Create a default number format.  The default number format is based on
     * {@link NumberFormat#getInstance(java.util.Locale)} with the only
     * customizing that the maximum number of fraction digits is set to 10.
     * @param locale the specific locale used by the format.
     * @return the default number format specific to the given locale.
     */
    public static NumberFormat getDefaultNumberFormat(final Locale locale) {
        final NumberFormat nf = NumberFormat.getInstance(locale);
        nf.setMaximumFractionDigits(10);
        return nf;
    }

    /**
     * Parses <code>source</code> until a non-whitespace character is found.
     *
     * @param source the string to parse
     * @param pos input/output parsing parameter.  On output, <code>pos</code>
     *        holds the index of the next non-whitespace character.
     */
    public static void parseAndIgnoreWhitespace(final String source, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseAndIgnoreWhitespace_65");
        parseNextCharacter(source, pos);
        pos.setIndex(AOR_minus(pos.getIndex(), 1, "org.apache.commons.math3.util.CompositeFormat.parseAndIgnoreWhitespace_65", _mut40285, _mut40286, _mut40287, _mut40288));
    }

    /**
     * Parses <code>source</code> until a non-whitespace character is found.
     *
     * @param source the string to parse
     * @param pos input/output parsing parameter.
     * @return the first non-whitespace character.
     */
    public static char parseNextCharacter(final String source, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78");
        int index = pos.getIndex();
        final int n = source.length();
        char ret = 0;
        if (ROR_less(index, n, "org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78", _mut40289, _mut40290, _mut40291, _mut40292, _mut40293)) {
            char c;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78");
                c = source.charAt(index++);
            } while ((_mut40299 ? (Character.isWhitespace(c) || ROR_less(index, n, "org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78", _mut40294, _mut40295, _mut40296, _mut40297, _mut40298)) : (Character.isWhitespace(c) && ROR_less(index, n, "org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78", _mut40294, _mut40295, _mut40296, _mut40297, _mut40298))));
            pos.setIndex(index);
            if (ROR_less(index, n, "org.apache.commons.math3.util.CompositeFormat.parseNextCharacter_78", _mut40300, _mut40301, _mut40302, _mut40303, _mut40304)) {
                ret = c;
            }
        }
        return ret;
    }

    /**
     * Parses <code>source</code> for special double values.  These values
     * include Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY.
     *
     * @param source the string to parse
     * @param value the special value to parse.
     * @param pos input/output parsing parameter.
     * @return the special number.
     */
    private static Number parseNumber(final String source, final double value, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseNumber_108");
        Number ret = null;
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(value);
        sb.append(')');
        final int n = sb.length();
        final int startIndex = pos.getIndex();
        final int endIndex = AOR_plus(startIndex, n, "org.apache.commons.math3.util.CompositeFormat.parseNumber_108", _mut40305, _mut40306, _mut40307, _mut40308);
        if ((_mut40319 ? (ROR_less(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseNumber_108", _mut40309, _mut40310, _mut40311, _mut40312, _mut40313) || ROR_equals(source.substring(startIndex, endIndex).compareTo(sb.toString()), 0, "org.apache.commons.math3.util.CompositeFormat.parseNumber_108", _mut40314, _mut40315, _mut40316, _mut40317, _mut40318)) : (ROR_less(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseNumber_108", _mut40309, _mut40310, _mut40311, _mut40312, _mut40313) && ROR_equals(source.substring(startIndex, endIndex).compareTo(sb.toString()), 0, "org.apache.commons.math3.util.CompositeFormat.parseNumber_108", _mut40314, _mut40315, _mut40316, _mut40317, _mut40318)))) {
            ret = Double.valueOf(value);
            pos.setIndex(endIndex);
        }
        return ret;
    }

    /**
     * Parses <code>source</code> for a number.  This method can parse normal,
     * numeric values as well as special values.  These special values include
     * Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY.
     *
     * @param source the string to parse
     * @param format the number format used to parse normal, numeric values.
     * @param pos input/output parsing parameter.
     * @return the parsed number.
     */
    public static Number parseNumber(final String source, final NumberFormat format, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseNumber_139");
        final int startIndex = pos.getIndex();
        Number number = format.parse(source, pos);
        final int endIndex = pos.getIndex();
        // check for error parsing number
        if (ROR_equals(startIndex, endIndex, "org.apache.commons.math3.util.CompositeFormat.parseNumber_139", _mut40320, _mut40321, _mut40322, _mut40323, _mut40324)) {
            // try parsing special numbers
            final double[] special = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
            for (int i = 0; ROR_less(i, special.length, "org.apache.commons.math3.util.CompositeFormat.parseNumber_139", _mut40325, _mut40326, _mut40327, _mut40328, _mut40329); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseNumber_139");
                number = parseNumber(source, special[i], pos);
                if (number != null) {
                    break;
                }
            }
        }
        return number;
    }

    /**
     * Parse <code>source</code> for an expected fixed string.
     * @param source the string to parse
     * @param expected expected string
     * @param pos input/output parsing parameter.
     * @return true if the expected string was there
     */
    public static boolean parseFixedstring(final String source, final String expected, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169");
        final int startIndex = pos.getIndex();
        final int endIndex = AOR_plus(startIndex, expected.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40330, _mut40331, _mut40332, _mut40333);
        if ((_mut40350 ? ((_mut40344 ? ((ROR_greater_equals(startIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40334, _mut40335, _mut40336, _mut40337, _mut40338)) && (ROR_greater(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40339, _mut40340, _mut40341, _mut40342, _mut40343))) : ((ROR_greater_equals(startIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40334, _mut40335, _mut40336, _mut40337, _mut40338)) || (ROR_greater(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40339, _mut40340, _mut40341, _mut40342, _mut40343)))) && (ROR_not_equals(source.substring(startIndex, endIndex).compareTo(expected), 0, "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40345, _mut40346, _mut40347, _mut40348, _mut40349))) : ((_mut40344 ? ((ROR_greater_equals(startIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40334, _mut40335, _mut40336, _mut40337, _mut40338)) && (ROR_greater(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40339, _mut40340, _mut40341, _mut40342, _mut40343))) : ((ROR_greater_equals(startIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40334, _mut40335, _mut40336, _mut40337, _mut40338)) || (ROR_greater(endIndex, source.length(), "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40339, _mut40340, _mut40341, _mut40342, _mut40343)))) || (ROR_not_equals(source.substring(startIndex, endIndex).compareTo(expected), 0, "org.apache.commons.math3.util.CompositeFormat.parseFixedstring_169", _mut40345, _mut40346, _mut40347, _mut40348, _mut40349))))) {
            // set index back to start, error index should be the start index
            pos.setIndex(startIndex);
            pos.setErrorIndex(startIndex);
            return false;
        }
        // the string was here
        pos.setIndex(endIndex);
        return true;
    }

    /**
     * Formats a double value to produce a string.  In general, the value is
     * formatted using the formatting rules of <code>format</code>.  There are
     * three exceptions to this:
     * <ol>
     * <li>NaN is formatted as '(NaN)'</li>
     * <li>Positive infinity is formatted as '(Infinity)'</li>
     * <li>Negative infinity is formatted as '(-Infinity)'</li>
     * </ol>
     *
     * @param value the double to format.
     * @param format the format used.
     * @param toAppendTo where the text is to be appended
     * @param pos On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return the value passed in as toAppendTo.
     */
    public static StringBuffer formatDouble(final double value, final NumberFormat format, final StringBuffer toAppendTo, final FieldPosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CompositeFormat.formatDouble_206");
        if ((_mut40351 ? (Double.isNaN(value) && Double.isInfinite(value)) : (Double.isNaN(value) || Double.isInfinite(value)))) {
            toAppendTo.append('(');
            toAppendTo.append(value);
            toAppendTo.append(')');
        } else {
            format.format(value, toAppendTo, pos);
        }
        return toAppendTo;
    }
}
