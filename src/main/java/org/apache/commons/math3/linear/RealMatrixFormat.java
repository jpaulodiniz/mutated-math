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
package org.apache.commons.math3.linear;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.util.CompositeFormat;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Formats a {@code nxm} matrix in components list format
 * "{{a<sub>0</sub><sub>0</sub>,a<sub>0</sub><sub>1</sub>, ...,
 * a<sub>0</sub><sub>m-1</sub>},{a<sub>1</sub><sub>0</sub>,
 * a<sub>1</sub><sub>1</sub>, ..., a<sub>1</sub><sub>m-1</sub>},{...},{
 * a<sub>n-1</sub><sub>0</sub>, a<sub>n-1</sub><sub>1</sub>, ...,
 * a<sub>n-1</sub><sub>m-1</sub>}}".
 * <p>The prefix and suffix "{" and "}", the row prefix and suffix "{" and "}",
 * the row separator "," and the column separator "," can be replaced by any
 * user-defined strings. The number format for components can be configured.</p>
 *
 * <p>White space is ignored at parse time, even if it is in the prefix, suffix
 * or separator specifications. So even if the default separator does include a space
 * character that is used at format time, both input string "{{1,1,1}}" and
 * " { { 1 , 1 , 1 } } " will be parsed without error and the same matrix will be
 * returned. In the second case, however, the parse position after parsing will be
 * just after the closing curly brace, i.e. just before the trailing space.</p>
 *
 * <p><b>Note:</b> the grouping functionality of the used {@link NumberFormat} is
 * disabled to prevent problems when parsing (e.g. 1,345.34 would be a valid number
 * but conflicts with the default column separator).</p>
 *
 * @since 3.1
 */
public class RealMatrixFormat {

    @Conditional
    public static boolean _mut33160 = false, _mut33161 = false, _mut33162 = false, _mut33163 = false, _mut33164 = false, _mut33165 = false, _mut33166 = false, _mut33167 = false, _mut33168 = false, _mut33169 = false, _mut33170 = false, _mut33171 = false, _mut33172 = false, _mut33173 = false, _mut33174 = false, _mut33175 = false, _mut33176 = false, _mut33177 = false, _mut33178 = false, _mut33179 = false, _mut33180 = false, _mut33181 = false, _mut33182 = false, _mut33183 = false, _mut33184 = false, _mut33185 = false, _mut33186 = false, _mut33187 = false, _mut33188 = false, _mut33189 = false, _mut33190 = false, _mut33191 = false, _mut33192 = false, _mut33193 = false, _mut33194 = false, _mut33195 = false, _mut33196 = false, _mut33197 = false, _mut33198 = false, _mut33199 = false, _mut33200 = false, _mut33201 = false, _mut33202 = false, _mut33203 = false, _mut33204 = false, _mut33205 = false;

    /**
     * The default prefix: "{".
     */
    private static final String DEFAULT_PREFIX = "{";

    /**
     * The default suffix: "}".
     */
    private static final String DEFAULT_SUFFIX = "}";

    /**
     * The default row prefix: "{".
     */
    private static final String DEFAULT_ROW_PREFIX = "{";

    /**
     * The default row suffix: "}".
     */
    private static final String DEFAULT_ROW_SUFFIX = "}";

    /**
     * The default row separator: ",".
     */
    private static final String DEFAULT_ROW_SEPARATOR = ",";

    /**
     * The default column separator: ",".
     */
    private static final String DEFAULT_COLUMN_SEPARATOR = ",";

    /**
     * Prefix.
     */
    private final String prefix;

    /**
     * Suffix.
     */
    private final String suffix;

    /**
     * Row prefix.
     */
    private final String rowPrefix;

    /**
     * Row suffix.
     */
    private final String rowSuffix;

    /**
     * Row separator.
     */
    private final String rowSeparator;

    /**
     * Column separator.
     */
    private final String columnSeparator;

    /**
     * The format used for components.
     */
    private final NumberFormat format;

    /**
     * Create an instance with default settings.
     * <p>The instance uses the default prefix, suffix and row/column separator:
     * "[", "]", ";" and ", " and the default number format for components.</p>
     */
    public RealMatrixFormat() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_ROW_PREFIX, DEFAULT_ROW_SUFFIX, DEFAULT_ROW_SEPARATOR, DEFAULT_COLUMN_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    /**
     * Create an instance with a custom number format for components.
     * @param format the custom format for components.
     */
    public RealMatrixFormat(final NumberFormat format) {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_ROW_PREFIX, DEFAULT_ROW_SUFFIX, DEFAULT_ROW_SEPARATOR, DEFAULT_COLUMN_SEPARATOR, format);
    }

    /**
     * Create an instance with custom prefix, suffix and separator.
     * @param prefix prefix to use instead of the default "{"
     * @param suffix suffix to use instead of the default "}"
     * @param rowPrefix row prefix to use instead of the default "{"
     * @param rowSuffix row suffix to use instead of the default "}"
     * @param rowSeparator tow separator to use instead of the default ";"
     * @param columnSeparator column separator to use instead of the default ", "
     */
    public RealMatrixFormat(final String prefix, final String suffix, final String rowPrefix, final String rowSuffix, final String rowSeparator, final String columnSeparator) {
        this(prefix, suffix, rowPrefix, rowSuffix, rowSeparator, columnSeparator, CompositeFormat.getDefaultNumberFormat());
    }

    /**
     * Create an instance with custom prefix, suffix, separator and format
     * for components.
     * @param prefix prefix to use instead of the default "{"
     * @param suffix suffix to use instead of the default "}"
     * @param rowPrefix row prefix to use instead of the default "{"
     * @param rowSuffix row suffix to use instead of the default "}"
     * @param rowSeparator tow separator to use instead of the default ";"
     * @param columnSeparator column separator to use instead of the default ", "
     * @param format the custom format for components.
     */
    public RealMatrixFormat(final String prefix, final String suffix, final String rowPrefix, final String rowSuffix, final String rowSeparator, final String columnSeparator, final NumberFormat format) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.rowPrefix = rowPrefix;
        this.rowSuffix = rowSuffix;
        this.rowSeparator = rowSeparator;
        this.columnSeparator = columnSeparator;
        this.format = format;
        // disable grouping to prevent parsing problems
        this.format.setGroupingUsed(false);
    }

    /**
     * Get the set of locales for which real vectors formats are available.
     * <p>This is the same set as the {@link NumberFormat} set.</p>
     * @return available real vector format locales.
     */
    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    /**
     * Get the format prefix.
     * @return format prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Get the format suffix.
     * @return format suffix.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Get the format prefix.
     * @return format prefix.
     */
    public String getRowPrefix() {
        return rowPrefix;
    }

    /**
     * Get the format suffix.
     * @return format suffix.
     */
    public String getRowSuffix() {
        return rowSuffix;
    }

    /**
     * Get the format separator between rows of the matrix.
     * @return format separator for rows.
     */
    public String getRowSeparator() {
        return rowSeparator;
    }

    /**
     * Get the format separator between components.
     * @return format separator between components.
     */
    public String getColumnSeparator() {
        return columnSeparator;
    }

    /**
     * Get the components format.
     * @return components format.
     */
    public NumberFormat getFormat() {
        return format;
    }

    /**
     * Returns the default real vector format for the current locale.
     * @return the default real vector format.
     */
    public static RealMatrixFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    /**
     * Returns the default real vector format for the given locale.
     * @param locale the specific locale used by the format.
     * @return the real vector format specific to the given locale.
     */
    public static RealMatrixFormat getInstance(final Locale locale) {
        return new RealMatrixFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    /**
     * This method calls {@link #format(RealMatrix,StringBuffer,FieldPosition)}.
     *
     * @param m RealMatrix object to format.
     * @return a formatted matrix.
     */
    public String format(RealMatrix m) {
        return format(m, new StringBuffer(), new FieldPosition(0)).toString();
    }

    /**
     * Formats a {@link RealMatrix} object to produce a string.
     * @param matrix the object to format.
     * @param toAppendTo where the text is to be appended
     * @param pos On input: an alignment field, if desired. On output: the
     *            offsets of the alignment field
     * @return the value passed in as toAppendTo.
     */
    public StringBuffer format(RealMatrix matrix, StringBuffer toAppendTo, FieldPosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.format_244");
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        // format prefix
        toAppendTo.append(prefix);
        // format rows
        final int rows = matrix.getRowDimension();
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.linear.RealMatrixFormat.format_244", _mut33179, _mut33180, _mut33181, _mut33182, _mut33183); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.format_244");
            toAppendTo.append(rowPrefix);
            for (int j = 0; ROR_less(j, matrix.getColumnDimension(), "org.apache.commons.math3.linear.RealMatrixFormat.format_244", _mut33165, _mut33166, _mut33167, _mut33168, _mut33169); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.format_244");
                if (ROR_greater(j, 0, "org.apache.commons.math3.linear.RealMatrixFormat.format_244", _mut33160, _mut33161, _mut33162, _mut33163, _mut33164)) {
                    toAppendTo.append(columnSeparator);
                }
                CompositeFormat.formatDouble(matrix.getEntry(i, j), format, toAppendTo, pos);
            }
            toAppendTo.append(rowSuffix);
            if (ROR_less(i, AOR_minus(rows, 1, "org.apache.commons.math3.linear.RealMatrixFormat.format_244", _mut33170, _mut33171, _mut33172, _mut33173), "org.apache.commons.math3.linear.RealMatrixFormat.format_244", _mut33174, _mut33175, _mut33176, _mut33177, _mut33178)) {
                toAppendTo.append(rowSeparator);
            }
        }
        // format suffix
        toAppendTo.append(suffix);
        return toAppendTo;
    }

    /**
     * Parse a string to produce a {@link RealMatrix} object.
     *
     * @param source String to parse.
     * @return the parsed {@link RealMatrix} object.
     * @throws MathParseException if the beginning of the specified string
     * cannot be parsed.
     */
    public RealMatrix parse(String source) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.parse_283");
        final ParsePosition parsePosition = new ParsePosition(0);
        final RealMatrix result = parse(source, parsePosition);
        if (ROR_equals(parsePosition.getIndex(), 0, "org.apache.commons.math3.linear.RealMatrixFormat.parse_283", _mut33184, _mut33185, _mut33186, _mut33187, _mut33188)) {
            throw new MathParseException(source, parsePosition.getErrorIndex(), Array2DRowRealMatrix.class);
        }
        return result;
    }

    /**
     * Parse a string to produce a {@link RealMatrix} object.
     *
     * @param source String to parse.
     * @param pos input/ouput parsing parameter.
     * @return the parsed {@link RealMatrix} object.
     */
    public RealMatrix parse(String source, ParsePosition pos) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.parse_301");
        int initialIndex = pos.getIndex();
        final String trimmedPrefix = prefix.trim();
        final String trimmedSuffix = suffix.trim();
        final String trimmedRowPrefix = rowPrefix.trim();
        final String trimmedRowSuffix = rowSuffix.trim();
        final String trimmedColumnSeparator = columnSeparator.trim();
        final String trimmedRowSeparator = rowSeparator.trim();
        // parse prefix
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, trimmedPrefix, pos)) {
            return null;
        }
        // parse components
        List<List<Number>> matrix = new ArrayList<List<Number>>();
        List<Number> rowComponents = new ArrayList<Number>();
        for (boolean loop = true; loop; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.parse_301");
            if (!rowComponents.isEmpty()) {
                CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                if (!CompositeFormat.parseFixedstring(source, trimmedColumnSeparator, pos)) {
                    if ((_mut33200 ? (ROR_not_equals(trimmedRowSuffix.length(), 0, "org.apache.commons.math3.linear.RealMatrixFormat.parse_301", _mut33195, _mut33196, _mut33197, _mut33198, _mut33199) || !CompositeFormat.parseFixedstring(source, trimmedRowSuffix, pos)) : (ROR_not_equals(trimmedRowSuffix.length(), 0, "org.apache.commons.math3.linear.RealMatrixFormat.parse_301", _mut33195, _mut33196, _mut33197, _mut33198, _mut33199) && !CompositeFormat.parseFixedstring(source, trimmedRowSuffix, pos)))) {
                        return null;
                    } else {
                        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                        if (CompositeFormat.parseFixedstring(source, trimmedRowSeparator, pos)) {
                            matrix.add(rowComponents);
                            rowComponents = new ArrayList<Number>();
                            continue;
                        } else {
                            loop = false;
                        }
                    }
                }
            } else {
                CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                if ((_mut33194 ? (ROR_not_equals(trimmedRowPrefix.length(), 0, "org.apache.commons.math3.linear.RealMatrixFormat.parse_301", _mut33189, _mut33190, _mut33191, _mut33192, _mut33193) || !CompositeFormat.parseFixedstring(source, trimmedRowPrefix, pos)) : (ROR_not_equals(trimmedRowPrefix.length(), 0, "org.apache.commons.math3.linear.RealMatrixFormat.parse_301", _mut33189, _mut33190, _mut33191, _mut33192, _mut33193) && !CompositeFormat.parseFixedstring(source, trimmedRowPrefix, pos)))) {
                    return null;
                }
            }
            if (loop) {
                CompositeFormat.parseAndIgnoreWhitespace(source, pos);
                Number component = CompositeFormat.parseNumber(source, format, pos);
                if (component != null) {
                    rowComponents.add(component);
                } else {
                    if (rowComponents.isEmpty()) {
                        loop = false;
                    } else {
                        // set index back to initial, error index should already be set
                        pos.setIndex(initialIndex);
                        return null;
                    }
                }
            }
        }
        if (!rowComponents.isEmpty()) {
            matrix.add(rowComponents);
        }
        // parse suffix
        CompositeFormat.parseAndIgnoreWhitespace(source, pos);
        if (!CompositeFormat.parseFixedstring(source, trimmedSuffix, pos)) {
            return null;
        }
        // do not allow an empty matrix
        if (matrix.isEmpty()) {
            pos.setIndex(initialIndex);
            return null;
        }
        // build vector
        double[][] data = new double[matrix.size()][];
        int row = 0;
        for (List<Number> rowList : matrix) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.parse_301");
            data[row] = new double[rowList.size()];
            for (int i = 0; ROR_less(i, rowList.size(), "org.apache.commons.math3.linear.RealMatrixFormat.parse_301", _mut33201, _mut33202, _mut33203, _mut33204, _mut33205); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.RealMatrixFormat.parse_301");
                data[row][i] = rowList.get(i).doubleValue();
            }
            row++;
        }
        return MatrixUtils.createRealMatrix(data);
    }
}
