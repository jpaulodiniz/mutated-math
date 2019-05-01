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

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Formats a Fraction number in proper format.  The number format for each of
 * the whole number, numerator and, denominator can be configured.
 * <p>
 * Minus signs are only allowed in the whole number part - i.e.,
 * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
 * will result in a <code>ParseException</code>.</p>
 *
 * @since 1.1
 */
public class ProperFractionFormat extends FractionFormat {

    @Conditional
    public static boolean _mut672 = false, _mut673 = false, _mut674 = false, _mut675 = false, _mut676 = false, _mut677 = false, _mut678 = false, _mut679 = false, _mut680 = false, _mut681 = false, _mut682 = false, _mut683 = false, _mut684 = false, _mut685 = false, _mut686 = false, _mut687 = false, _mut688 = false, _mut689 = false, _mut690 = false, _mut691 = false, _mut692 = false, _mut693 = false, _mut694 = false, _mut695 = false, _mut696 = false, _mut697 = false, _mut698 = false, _mut699 = false, _mut700 = false, _mut701 = false, _mut702 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 760934726031766749L;

    /**
     * The format used for the whole number.
     */
    private NumberFormat wholeFormat;

    /**
     * Create a proper formatting instance with the default number format for
     * the whole, numerator, and denominator.
     */
    public ProperFractionFormat() {
        this(getDefaultNumberFormat());
    }

    /**
     * Create a proper formatting instance with a custom number format for the
     * whole, numerator, and denominator.
     * @param format the custom format for the whole, numerator, and
     *        denominator.
     */
    public ProperFractionFormat(NumberFormat format) {
        this(format, (NumberFormat) format.clone(), (NumberFormat) format.clone());
    }

    /**
     * Create a proper formatting instance with a custom number format for each
     * of the whole, numerator, and denominator.
     * @param wholeFormat the custom format for the whole.
     * @param numeratorFormat the custom format for the numerator.
     * @param denominatorFormat the custom format for the denominator.
     */
    public ProperFractionFormat(NumberFormat wholeFormat, NumberFormat numeratorFormat, NumberFormat denominatorFormat) {
        super(numeratorFormat, denominatorFormat);
        setWholeFormat(wholeFormat);
    }

    /**
     * Formats a {@link Fraction} object to produce a string.  The fraction
     * is output in proper format.
     *
     * @param fraction the object to format.
     * @param toAppendTo where the text is to be appended
     * @param pos On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return the value passed in as toAppendTo.
     */
    @Override
    public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.ProperFractionFormat.format_89");
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        int num = fraction.getNumerator();
        int den = fraction.getDenominator();
        int whole = AOR_divide(num, den, "org.apache.commons.math3.fraction.ProperFractionFormat.format_89", _mut672, _mut673, _mut674, _mut675);
        num %= den;
        if (ROR_not_equals(whole, 0, "org.apache.commons.math3.fraction.ProperFractionFormat.format_89", _mut676, _mut677, _mut678, _mut679, _mut680)) {
            getWholeFormat().format(whole, toAppendTo, pos);
            toAppendTo.append(' ');
            num = FastMath.abs(num);
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
     * Parses a string to produce a {@link Fraction} object.  This method
     * expects the string to be formatted as a proper fraction.
     * <p>
     * Minus signs are only allowed in the whole number part - i.e.,
     * "-3 1/2" is legitimate and denotes -7/2, but "-3 -1/2" is invalid and
     * will result in a <code>ParseException</code>.</p>
     *
     * @param source the string to parse
     * @param pos input/ouput parsing parameter.
     * @return the parsed {@link Fraction} object.
     */
    @Override
    public Fraction parse(String source, ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.ProperFractionFormat.parse_133");
        // try to parse improper fraction
        Fraction ret = super.parse(source, pos);
        if (ret != null) {
            return ret;
        }
        int initialIndex = pos.getIndex();
        // parse whitespace
        parseAndIgnoreWhitespace(source, pos);
        // parse whole
        Number whole = getWholeFormat().parse(source, pos);
        if (whole == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        // parse whitespace
        parseAndIgnoreWhitespace(source, pos);
        // parse numerator
        Number num = getNumeratorFormat().parse(source, pos);
        if (num == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        if (ROR_less(num.intValue(), 0, "org.apache.commons.math3.fraction.ProperFractionFormat.parse_133", _mut681, _mut682, _mut683, _mut684, _mut685)) {
            // minus signs should be leading, invalid expression
            pos.setIndex(initialIndex);
            return null;
        }
        // parse '/'
        int startIndex = pos.getIndex();
        char c = parseNextCharacter(source, pos);
        switch(c) {
            case 0:
                // return num as a fraction
                return new Fraction(num.intValue(), 1);
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
        Number den = getDenominatorFormat().parse(source, pos);
        if (den == null) {
            // character examined.
            pos.setIndex(initialIndex);
            return null;
        }
        if (ROR_less(den.intValue(), 0, "org.apache.commons.math3.fraction.ProperFractionFormat.parse_133", _mut686, _mut687, _mut688, _mut689, _mut690)) {
            // minus signs must be leading, invalid
            pos.setIndex(initialIndex);
            return null;
        }
        int w = whole.intValue();
        int n = num.intValue();
        int d = den.intValue();
        return new Fraction(AOR_multiply((AOR_plus((AOR_multiply(FastMath.abs(w), d, "org.apache.commons.math3.fraction.ProperFractionFormat.parse_133", _mut691, _mut692, _mut693, _mut694)), n, "org.apache.commons.math3.fraction.ProperFractionFormat.parse_133", _mut695, _mut696, _mut697, _mut698)), MathUtils.copySign(1, w), "org.apache.commons.math3.fraction.ProperFractionFormat.parse_133", _mut699, _mut700, _mut701, _mut702), d);
    }

    /**
     * Modify the whole format.
     * @param format The new whole format value.
     * @throws NullArgumentException if {@code format} is {@code null}.
     */
    public void setWholeFormat(NumberFormat format) {
        if (format == null) {
            throw new NullArgumentException(LocalizedFormats.WHOLE_FORMAT);
        }
        this.wholeFormat = format;
    }
}
