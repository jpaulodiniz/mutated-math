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
package org.apache.commons.math3.fraction;

import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.NullArgumentException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Formats a BigFraction number in proper format.  The number format for each of
 * the whole number, numerator and, denominator can be configured.
 * <p>
 * Minus signs are only allowed in the whole number part - i.e.,
 * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
 * will result in a <code>ParseException</code>.</p>
 *
 * @since 1.1
 */
public class ProperBigFractionFormat extends BigFractionFormat {

    @Conditional
    public static boolean _mut278 = false, _mut279 = false, _mut280 = false, _mut281 = false, _mut282 = false, _mut283 = false, _mut284 = false, _mut285 = false, _mut286 = false, _mut287 = false, _mut288 = false, _mut289 = false, _mut290 = false, _mut291 = false, _mut292 = false, _mut293 = false, _mut294 = false, _mut295 = false, _mut296 = false, _mut297 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -6337346779577272307L;

    /**
     * The format used for the whole number.
     */
    private NumberFormat wholeFormat;

    /**
     * Create a proper formatting instance with the default number format for
     * the whole, numerator, and denominator.
     */
    public ProperBigFractionFormat() {
        this(getDefaultNumberFormat());
    }

    /**
     * Create a proper formatting instance with a custom number format for the
     * whole, numerator, and denominator.
     * @param format the custom format for the whole, numerator, and
     *        denominator.
     */
    public ProperBigFractionFormat(final NumberFormat format) {
        this(format, (NumberFormat) format.clone(), (NumberFormat) format.clone());
    }

    /**
     * Create a proper formatting instance with a custom number format for each
     * of the whole, numerator, and denominator.
     * @param wholeFormat the custom format for the whole.
     * @param numeratorFormat the custom format for the numerator.
     * @param denominatorFormat the custom format for the denominator.
     */
    public ProperBigFractionFormat(final NumberFormat wholeFormat, final NumberFormat numeratorFormat, final NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
        setWholeFormat(wholeFormat);
    }

    /**
     * Formats a {@link BigFraction} object to produce a string.  The BigFraction
     * is output in proper format.
     *
     * @param fraction the object to format.
     * @param toAppendTo where the text is to be appended
     * @param pos On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return the value passed in as toAppendTo.
     */
    @Override
    public StringBuffer format(final BigFraction fraction, final StringBuffer toAppendTo, final FieldPosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.ProperBigFractionFormat.format_87");
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        BigInteger num = fraction.getNumerator();
        BigInteger den = fraction.getDenominator();
        BigInteger whole = num.divide(den);
        num = num.remainder(den);
        if (!BigInteger.ZERO.equals(whole)) {
            getWholeFormat().format(whole, toAppendTo, pos);
            toAppendTo.append(' ');
            if (ROR_less(num.compareTo(BigInteger.ZERO), 0, "org.apache.commons.math3.fraction.ProperBigFractionFormat.format_87", _mut278, _mut279, _mut280, _mut281, _mut282)) {
                num = num.negate();
            }
        }
        getNumeratorFormat().format(num, toAppendTo, pos);
        toAppendTo.append(" / ");
        getDenominatorFormat().format(den, toAppendTo, pos);
        return toAppendTo;
    }

    /**
     * Access the whole format.
     * @return the whole format.
     */
    public NumberFormat getWholeFormat() {
        return wholeFormat;
    }

    /**
     * Parses a string to produce a {@link BigFraction} object.  This method
     * expects the string to be formatted as a proper BigFraction.
     * <p>
     * Minus signs are only allowed in the whole number part - i.e.,
     * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
     * will result in a <code>ParseException</code>.</p>
     *
     * @param source the string to parse
     * @param pos input/ouput parsing parameter.
     * @return the parsed {@link BigFraction} object.
     */
    @Override
    public BigFraction parse(final String source, final ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.ProperBigFractionFormat.parse_133");
        // try to parse improper BigFraction
        BigFraction ret = super.parse(source, pos);
        if (ret != null) {
            return ret;
        }
        final int initialIndex = pos.getIndex();
        // parse whitespace
        parseAndIgnoreWhitespace(source, pos);
        // parse whole
        BigInteger whole = parseNextBigInteger(source, pos);
        if (whole == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        // parse whitespace
        parseAndIgnoreWhitespace(source, pos);
        // parse numerator
        BigInteger num = parseNextBigInteger(source, pos);
        if (num == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        if (ROR_less(num.compareTo(BigInteger.ZERO), 0, "org.apache.commons.math3.fraction.ProperBigFractionFormat.parse_133", _mut283, _mut284, _mut285, _mut286, _mut287)) {
            // minus signs should be leading, invalid expression
            pos.setIndex(initialIndex);
            return null;
        }
        // parse '/'
        final int startIndex = pos.getIndex();
        final char c = parseNextCharacter(source, pos);
        switch(c) {
            case 0:
                // return num as a BigFraction
                return new BigFraction(num);
            case '/':
                // found '/', continue parsing denominator
                break;
            default:
                // character examined.
                pos.setIndex(initialIndex);
                pos.setErrorIndex(startIndex);
                return null;
        }
        // parse whitespace
        parseAndIgnoreWhitespace(source, pos);
        // parse denominator
        final BigInteger den = parseNextBigInteger(source, pos);
        if (den == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        if (ROR_less(den.compareTo(BigInteger.ZERO), 0, "org.apache.commons.math3.fraction.ProperBigFractionFormat.parse_133", _mut288, _mut289, _mut290, _mut291, _mut292)) {
            // minus signs must be leading, invalid
            pos.setIndex(initialIndex);
            return null;
        }
        boolean wholeIsNeg = ROR_less(whole.compareTo(BigInteger.ZERO), 0, "org.apache.commons.math3.fraction.ProperBigFractionFormat.parse_133", _mut293, _mut294, _mut295, _mut296, _mut297);
        if (wholeIsNeg) {
            whole = whole.negate();
        }
        num = whole.multiply(den).add(num);
        if (wholeIsNeg) {
            num = num.negate();
        }
        return new BigFraction(num, den);
    }

    /**
     * Modify the whole format.
     * @param format The new whole format value.
     * @throws NullArgumentException if {@code format} is {@code null}.
     */
    public void setWholeFormat(final NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT);
        }
        this.wholeFormat = format;
    }
}
