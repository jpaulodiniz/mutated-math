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
package org.apache.commons.math3.stat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Maintains a frequency distribution.
 * <p>
 * Accepts int, long, char or Comparable values.  New values added must be
 * comparable to those that have been added, otherwise the add method will
 * throw an IllegalArgumentException.</p>
 * <p>
 * Integer values (int, long, Integer, Long) are not distinguished by type --
 * i.e. <code>addValue(Long.valueOf(2)), addValue(2), addValue(2l)</code> all have
 * the same effect (similarly for arguments to <code>getCount,</code> etc.).</p>
 * <p>NOTE: byte and short values will be implicitly converted to int values
 * by the compiler, thus there are no explicit overloaded methods for these
 * primitive types.</p>
 * <p>
 * char values are converted by <code>addValue</code> to Character instances.
 * As such, these values are not comparable to integral values, so attempts
 * to combine integral types with chars in a frequency distribution will fail.
 * </p>
 * <p>
 * Float is not coerced to Double.
 * Since they are not Comparable with each other the user must do any necessary coercion.
 * Float.NaN and Double.NaN are not treated specially; they may occur in input and will
 * occur in output if appropriate.
 * </b>
 * <p>
 * The values are ordered using the default (natural order), unless a
 * <code>Comparator</code> is supplied in the constructor.</p>
 */
public class Frequency implements Serializable {

    @Conditional
    public static boolean _mut5245 = false, _mut5246 = false, _mut5247 = false, _mut5248 = false, _mut5249 = false, _mut5250 = false, _mut5251 = false, _mut5252 = false, _mut5253 = false, _mut5254 = false, _mut5255 = false, _mut5256 = false, _mut5257 = false, _mut5258 = false, _mut5259 = false, _mut5260 = false, _mut5261 = false, _mut5262 = false, _mut5263 = false, _mut5264 = false, _mut5265 = false, _mut5266 = false, _mut5267 = false, _mut5268 = false, _mut5269 = false, _mut5270 = false, _mut5271 = false, _mut5272 = false, _mut5273 = false, _mut5274 = false, _mut5275 = false, _mut5276 = false, _mut5277 = false, _mut5278 = false, _mut5279 = false, _mut5280 = false, _mut5281 = false, _mut5282 = false, _mut5283 = false, _mut5284 = false, _mut5285 = false, _mut5286 = false, _mut5287 = false, _mut5288 = false, _mut5289 = false, _mut5290 = false, _mut5291 = false, _mut5292 = false, _mut5293 = false, _mut5294 = false, _mut5295 = false, _mut5296 = false, _mut5297 = false, _mut5298 = false, _mut5299 = false, _mut5300 = false, _mut5301 = false, _mut5302 = false, _mut5303 = false, _mut5304 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -3845586908418844111L;

    /**
     * underlying collection
     */
    private final SortedMap<Comparable<?>, Long> freqTable;

    /**
     * Default constructor.
     */
    public Frequency() {
        freqTable = new TreeMap<Comparable<?>, Long>();
    }

    /**
     * Constructor allowing values Comparator to be specified.
     *
     * @param comparator Comparator used to order values
     */
    // TODO is the cast OK?
    @SuppressWarnings("unchecked")
    public Frequency(Comparator<?> comparator) {
        freqTable = new TreeMap<Comparable<?>, Long>((Comparator<? super Comparable<?>>) comparator);
    }

    /**
     * Return a string representation of this frequency distribution.
     *
     * @return a string representation.
     */
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getPercentInstance();
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
        Iterator<Comparable<?>> iter = freqTable.keySet().iterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.toString_95");
            Comparable<?> value = iter.next();
            outBuffer.append(value);
            outBuffer.append('\t');
            outBuffer.append(getCount(value));
            outBuffer.append('\t');
            outBuffer.append(nf.format(getPct(value)));
            outBuffer.append('\t');
            outBuffer.append(nf.format(getCumPct(value)));
            outBuffer.append('\n');
        }
        return outBuffer.toString();
    }

    /**
     * Adds 1 to the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if <code>v</code> is not comparable with previous entries
     */
    public void addValue(Comparable<?> v) throws MathIllegalArgumentException {
        incrementValue(v, 1);
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     */
    public void addValue(int v) throws MathIllegalArgumentException {
        addValue(Long.valueOf(v));
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     */
    public void addValue(long v) throws MathIllegalArgumentException {
        addValue(Long.valueOf(v));
    }

    /**
     * Adds 1 to the frequency count for v.
     *
     * @param v the value to add.
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Char
     */
    public void addValue(char v) throws MathIllegalArgumentException {
        addValue(Character.valueOf(v));
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if <code>v</code> is not comparable with previous entries
     * @since 3.1
     */
    public void incrementValue(Comparable<?> v, long increment) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.incrementValue_174");
        Comparable<?> obj = v;
        if (v instanceof Integer) {
            obj = Long.valueOf(((Integer) v).longValue());
        }
        try {
            Long count = freqTable.get(obj);
            if (count == null) {
                freqTable.put(obj, Long.valueOf(increment));
            } else {
                freqTable.put(obj, Long.valueOf(AOR_plus(count.longValue(), increment, "org.apache.commons.math3.stat.Frequency.incrementValue_174", _mut5245, _mut5246, _mut5247, _mut5248)));
            }
        } catch (ClassCastException ex) {
            // TreeMap will throw ClassCastException if v is not comparable
            throw new MathIllegalArgumentException(LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES, v.getClass().getName());
        }
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     * @since 3.3
     */
    public void incrementValue(int v, long increment) throws MathIllegalArgumentException {
        incrementValue(Long.valueOf(v), increment);
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Long
     * @since 3.3
     */
    public void incrementValue(long v, long increment) throws MathIllegalArgumentException {
        incrementValue(Long.valueOf(v), increment);
    }

    /**
     * Increments the frequency count for v.
     * <p>
     * If other objects have already been added to this Frequency, v must
     * be comparable to those that have already been added.
     * </p>
     *
     * @param v the value to add.
     * @param increment the amount by which the value should be incremented
     * @throws MathIllegalArgumentException if the table contains entries not
     * comparable to Char
     * @since 3.3
     */
    public void incrementValue(char v, long increment) throws MathIllegalArgumentException {
        incrementValue(Character.valueOf(v), increment);
    }

    /**
     * Clears the frequency table
     */
    public void clear() {
        freqTable.clear();
    }

    /**
     * Returns an Iterator over the set of values that have been added.
     * <p>
     * If added values are integral (i.e., integers, longs, Integers, or Longs),
     * they are converted to Longs when they are added, so the objects returned
     * by the Iterator will in this case be Longs.</p>
     *
     * @return values Iterator
     */
    public Iterator<Comparable<?>> valuesIterator() {
        return freqTable.keySet().iterator();
    }

    /**
     * Return an Iterator over the set of keys and values that have been added.
     * Using the entry set to iterate is more efficient in the case where you
     * need to access respective counts as well as values, since it doesn't
     * require a "get" for every key...the value is provided in the Map.Entry.
     * <p>
     * If added values are integral (i.e., integers, longs, Integers, or Longs),
     * they are converted to Longs when they are added, so the values of the
     * map entries returned by the Iterator will in this case be Longs.</p>
     *
     * @return entry set Iterator
     * @since 3.1
     */
    public Iterator<Map.Entry<Comparable<?>, Long>> entrySetIterator() {
        return freqTable.entrySet().iterator();
    }

    /**
     * Returns the sum of all frequencies.
     *
     * @return the total frequency count.
     */
    public long getSumFreq() {
        long result = 0;
        Iterator<Long> iterator = freqTable.values().iterator();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getSumFreq_287");
            result += iterator.next().longValue();
        }
        return result;
    }

    /**
     * Returns the number of values equal to v.
     * Returns 0 if the value is not comparable.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(Comparable<?> v) {
        if (v instanceof Integer) {
            return getCount(((Integer) v).longValue());
        }
        long result = 0;
        try {
            Long count = freqTable.get(v);
            if (count != null) {
                result = count.longValue();
            }
        } catch (ClassCastException ex) {
        }
        return result;
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(int v) {
        return getCount(Long.valueOf(v));
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(long v) {
        return getCount(Long.valueOf(v));
    }

    /**
     * Returns the number of values equal to v.
     *
     * @param v the value to lookup.
     * @return the frequency of v.
     */
    public long getCount(char v) {
        return getCount(Character.valueOf(v));
    }

    /**
     * Returns the number of values in the frequency table.
     *
     * @return the number of unique values that have been added to the frequency table.
     * @see #valuesIterator()
     */
    public int getUniqueCount() {
        return freqTable.keySet().size();
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns <code>Double.NaN</code> if no values have been added.
     * Returns 0 if at least one value has been added, but v is not comparable
     * to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(Comparable<?> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getPct_370");
        final long sumFreq = getSumFreq();
        if (ROR_equals(sumFreq, 0, "org.apache.commons.math3.stat.Frequency.getPct_370", _mut5249, _mut5250, _mut5251, _mut5252, _mut5253)) {
            return Double.NaN;
        }
        return AOR_divide((double) getCount(v), (double) sumFreq, "org.apache.commons.math3.stat.Frequency.getPct_370", _mut5254, _mut5255, _mut5256, _mut5257);
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(int v) {
        return getPct(Long.valueOf(v));
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(long v) {
        return getPct(Long.valueOf(v));
    }

    /**
     * Returns the percentage of values that are equal to v
     * (as a proportion between 0 and 1).
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public double getPct(char v) {
        return getPct(Character.valueOf(v));
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup.
     * @return the proportion of values equal to v
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public long getCumFreq(Comparable<?> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getCumFreq_421");
        if (ROR_equals(getSumFreq(), 0, "org.apache.commons.math3.stat.Frequency.getCumFreq_421", _mut5258, _mut5259, _mut5260, _mut5261, _mut5262)) {
            return 0;
        }
        if (v instanceof Integer) {
            return getCumFreq(((Integer) v).longValue());
        }
        Comparator<Comparable<?>> c = (Comparator<Comparable<?>>) freqTable.comparator();
        if (c == null) {
            c = new NaturalComparator();
        }
        long result = 0;
        try {
            Long value = freqTable.get(v);
            if (value != null) {
                result = value.longValue();
            }
        } catch (ClassCastException ex) {
            // v is not comparable
            return result;
        }
        if (ROR_less(c.compare(v, freqTable.firstKey()), 0, "org.apache.commons.math3.stat.Frequency.getCumFreq_421", _mut5263, _mut5264, _mut5265, _mut5266, _mut5267)) {
            // v is comparable, but less than first value
            return 0;
        }
        if (ROR_greater_equals(c.compare(v, freqTable.lastKey()), 0, "org.apache.commons.math3.stat.Frequency.getCumFreq_421", _mut5268, _mut5269, _mut5270, _mut5271, _mut5272)) {
            // v is comparable, but greater than the last value
            return getSumFreq();
        }
        Iterator<Comparable<?>> values = valuesIterator();
        while (values.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getCumFreq_421");
            Comparable<?> nextValue = values.next();
            if (ROR_greater(c.compare(v, nextValue), 0, "org.apache.commons.math3.stat.Frequency.getCumFreq_421", _mut5273, _mut5274, _mut5275, _mut5276, _mut5277)) {
                result += getCount(nextValue);
            } else {
                return result;
            }
        }
        return result;
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(int v) {
        return getCumFreq(Long.valueOf(v));
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(long v) {
        return getCumFreq(Long.valueOf(v));
    }

    /**
     * Returns the cumulative frequency of values less than or equal to v.
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values equal to v
     */
    public long getCumFreq(char v) {
        return getCumFreq(Character.valueOf(v));
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns <code>Double.NaN</code> if no values have been added.
     * Returns 0 if at least one value has been added, but v is not comparable
     * to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(Comparable<?> v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getCumPct_513");
        final long sumFreq = getSumFreq();
        if (ROR_equals(sumFreq, 0, "org.apache.commons.math3.stat.Frequency.getCumPct_513", _mut5278, _mut5279, _mut5280, _mut5281, _mut5282)) {
            return Double.NaN;
        }
        return AOR_divide((double) getCumFreq(v), (double) sumFreq, "org.apache.commons.math3.stat.Frequency.getCumPct_513", _mut5283, _mut5284, _mut5285, _mut5286);
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(int v) {
        return getCumPct(Long.valueOf(v));
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(long v) {
        return getCumPct(Long.valueOf(v));
    }

    /**
     * Returns the cumulative percentage of values less than or equal to v
     * (as a proportion between 0 and 1).
     * <p>
     * Returns 0 if v is not comparable to the values set.</p>
     *
     * @param v the value to lookup
     * @return the proportion of values less than or equal to v
     */
    public double getCumPct(char v) {
        return getCumPct(Character.valueOf(v));
    }

    /**
     * Returns the mode value(s) in comparator order.
     *
     * @return a list containing the value(s) which appear most often.
     * @since 3.3
     */
    public List<Comparable<?>> getMode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getMode_566");
        // frequencies are always positive
        long mostPopular = 0;
        // Get the max count first, so we avoid having to recreate the List each time
        for (Long l : freqTable.values()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getMode_566");
            long frequency = l.longValue();
            if (ROR_greater(frequency, mostPopular, "org.apache.commons.math3.stat.Frequency.getMode_566", _mut5287, _mut5288, _mut5289, _mut5290, _mut5291)) {
                mostPopular = frequency;
            }
        }
        List<Comparable<?>> modeList = new ArrayList<Comparable<?>>();
        for (Entry<Comparable<?>, Long> ent : freqTable.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.getMode_566");
            long frequency = ent.getValue().longValue();
            if (ROR_equals(frequency, mostPopular, "org.apache.commons.math3.stat.Frequency.getMode_566", _mut5292, _mut5293, _mut5294, _mut5295, _mut5296)) {
                modeList.add(ent.getKey());
            }
        }
        return modeList;
    }

    /**
     * Merge another Frequency object's counts into this instance.
     * This Frequency's counts will be incremented (or set when not already set)
     * by the counts represented by other.
     *
     * @param other the other {@link Frequency} object to be merged
     * @throws NullArgumentException if {@code other} is null
     * @since 3.1
     */
    public void merge(final Frequency other) throws NullArgumentException {
        MathUtils.checkNotNull(other, LocalizedFormats.NULL_NOT_ALLOWED);
        final Iterator<Map.Entry<Comparable<?>, Long>> iter = other.entrySetIterator();
        while (iter.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.merge_598");
            final Map.Entry<Comparable<?>, Long> entry = iter.next();
            incrementValue(entry.getKey(), entry.getValue().longValue());
        }
    }

    /**
     * Merge a {@link Collection} of {@link Frequency} objects into this instance.
     * This Frequency's counts will be incremented (or set when not already set)
     * by the counts represented by each of the others.
     *
     * @param others the other {@link Frequency} objects to be merged
     * @throws NullArgumentException if the collection is null
     * @since 3.1
     */
    public void merge(final Collection<Frequency> others) throws NullArgumentException {
        MathUtils.checkNotNull(others, LocalizedFormats.NULL_NOT_ALLOWED);
        for (final Frequency freq : others) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.merge_617");
            merge(freq);
        }
    }

    /**
     * A Comparator that compares comparable objects using the
     * natural order.  Copied from Commons Collections ComparableComparator.
     * @param <T> the type of the objects compared
     */
    private static class NaturalComparator<T extends Comparable<T>> implements Comparator<Comparable<T>>, Serializable {

        /**
         * Serializable version identifier
         */
        private static final long serialVersionUID = -3852193713161395148L;

        /**
         * Compare the two {@link Comparable Comparable} arguments.
         * This method is equivalent to:
         * <pre>(({@link Comparable Comparable})o1).{@link Comparable#compareTo compareTo}(o2)</pre>
         *
         * @param  o1 the first object
         * @param  o2 the second object
         * @return  result of comparison
         * @throws NullPointerException when <i>o1</i> is <code>null</code>,
         *         or when <code>((Comparable)o1).compareTo(o2)</code> does
         * @throws ClassCastException when <i>o1</i> is not a {@link Comparable Comparable},
         *         or when <code>((Comparable)o1).compareTo(o2)</code> does
         */
        // cast to (T) may throw ClassCastException, see Javadoc
        @SuppressWarnings("unchecked")
        public int compare(Comparable<T> o1, Comparable<T> o2) {
            return o1.compareTo((T) o2);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.Frequency.hashCode_657");
        final int prime = 31;
        int result = 1;
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.stat.Frequency.hashCode_657", _mut5297, _mut5298, _mut5299, _mut5300), ((freqTable == null) ? 0 : freqTable.hashCode()), "org.apache.commons.math3.stat.Frequency.hashCode_657", _mut5301, _mut5302, _mut5303, _mut5304);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Frequency)) {
            return false;
        }
        Frequency other = (Frequency) obj;
        if (freqTable == null) {
            if (other.freqTable != null) {
                return false;
            }
        } else if (!freqTable.equals(other.freqTable)) {
            return false;
        }
        return true;
    }
}
