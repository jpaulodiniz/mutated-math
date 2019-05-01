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
package org.apache.commons.math3.optim.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A linear constraint for a linear optimization problem.
 * <p>
 * A linear constraint has one of the forms:
 * <ul>
 *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> = v</li>
 *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> &lt;= v</li>
 *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> >= v</li>
 *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> =
 *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
 *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> &lt;=
 *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
 *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> >=
 *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
 * </ul>
 * The c<sub>i</sub>, l<sub>i</sub> or r<sub>i</sub> are the coefficients of the constraints, the x<sub>i</sub>
 * are the coordinates of the current point and v is the value of the constraint.
 * </p>
 *
 * @since 2.0
 */
public class LinearConstraint implements Serializable {

    @Conditional
    public static boolean _mut59520 = false, _mut59521 = false, _mut59522 = false, _mut59523 = false, _mut59524 = false, _mut59525 = false, _mut59526 = false, _mut59527 = false, _mut59528 = false, _mut59529 = false, _mut59530 = false, _mut59531 = false, _mut59532 = false, _mut59533 = false, _mut59534 = false, _mut59535 = false, _mut59536 = false, _mut59537 = false, _mut59538 = false, _mut59539 = false, _mut59540 = false, _mut59541 = false, _mut59542 = false, _mut59543 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -764632794033034092L;

    /**
     * Coefficients of the constraint (left hand side).
     */
    private final transient RealVector coefficients;

    /**
     * Relationship between left and right hand sides (=, &lt;=, >=).
     */
    private final Relationship relationship;

    /**
     * Value of the constraint (right hand side).
     */
    private final double value;

    /**
     * Build a constraint involving a single linear equation.
     * <p>
     * A linear constraint with a single linear equation has one of the forms:
     * <ul>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> = v</li>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> &lt;= v</li>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> >= v</li>
     * </ul>
     * </p>
     * @param coefficients The coefficients of the constraint (left hand side)
     * @param relationship The type of (in)equality used in the constraint
     * @param value The value of the constraint (right hand side)
     */
    public LinearConstraint(final double[] coefficients, final Relationship relationship, final double value) {
        this(new ArrayRealVector(coefficients), relationship, value);
    }

    /**
     * Build a constraint involving a single linear equation.
     * <p>
     * A linear constraint with a single linear equation has one of the forms:
     * <ul>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> = v</li>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> &lt;= v</li>
     *   <li>c<sub>1</sub>x<sub>1</sub> + ... c<sub>n</sub>x<sub>n</sub> >= v</li>
     * </ul>
     * </p>
     * @param coefficients The coefficients of the constraint (left hand side)
     * @param relationship The type of (in)equality used in the constraint
     * @param value The value of the constraint (right hand side)
     */
    public LinearConstraint(final RealVector coefficients, final Relationship relationship, final double value) {
        this.coefficients = coefficients;
        this.relationship = relationship;
        this.value = value;
    }

    /**
     * Build a constraint involving two linear equations.
     * <p>
     * A linear constraint with two linear equation has one of the forms:
     * <ul>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> =
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> &lt;=
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> >=
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     * </ul>
     * </p>
     * @param lhsCoefficients The coefficients of the linear expression on the left hand side of the constraint
     * @param lhsConstant The constant term of the linear expression on the left hand side of the constraint
     * @param relationship The type of (in)equality used in the constraint
     * @param rhsCoefficients The coefficients of the linear expression on the right hand side of the constraint
     * @param rhsConstant The constant term of the linear expression on the right hand side of the constraint
     */
    public LinearConstraint(final double[] lhsCoefficients, final double lhsConstant, final Relationship relationship, final double[] rhsCoefficients, final double rhsConstant) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_119");
        double[] sub = new double[lhsCoefficients.length];
        for (int i = 0; ROR_less(i, sub.length, "org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_119", _mut59524, _mut59525, _mut59526, _mut59527, _mut59528); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_119");
            sub[i] = AOR_minus(lhsCoefficients[i], rhsCoefficients[i], "org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_119", _mut59520, _mut59521, _mut59522, _mut59523);
        }
        this.coefficients = new ArrayRealVector(sub, false);
        this.relationship = relationship;
        this.value = AOR_minus(rhsConstant, lhsConstant, "org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_119", _mut59529, _mut59530, _mut59531, _mut59532);
    }

    /**
     * Build a constraint involving two linear equations.
     * <p>
     * A linear constraint with two linear equation has one of the forms:
     * <ul>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> =
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> &lt;=
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     *   <li>l<sub>1</sub>x<sub>1</sub> + ... l<sub>n</sub>x<sub>n</sub> + l<sub>cst</sub> >=
     *       r<sub>1</sub>x<sub>1</sub> + ... r<sub>n</sub>x<sub>n</sub> + r<sub>cst</sub></li>
     * </ul>
     * </p>
     * @param lhsCoefficients The coefficients of the linear expression on the left hand side of the constraint
     * @param lhsConstant The constant term of the linear expression on the left hand side of the constraint
     * @param relationship The type of (in)equality used in the constraint
     * @param rhsCoefficients The coefficients of the linear expression on the right hand side of the constraint
     * @param rhsConstant The constant term of the linear expression on the right hand side of the constraint
     */
    public LinearConstraint(final RealVector lhsCoefficients, final double lhsConstant, final Relationship relationship, final RealVector rhsCoefficients, final double rhsConstant) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_150");
        this.coefficients = lhsCoefficients.subtract(rhsCoefficients);
        this.relationship = relationship;
        this.value = AOR_minus(rhsConstant, lhsConstant, "org.apache.commons.math3.optim.linear.LinearConstraint.LinearConstraint_150", _mut59533, _mut59534, _mut59535, _mut59536);
    }

    /**
     * Gets the coefficients of the constraint (left hand side).
     *
     * @return the coefficients of the constraint (left hand side).
     */
    public RealVector getCoefficients() {
        return coefficients;
    }

    /**
     * Gets the relationship between left and right hand sides.
     *
     * @return the relationship between left and right hand sides.
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * Gets the value of the constraint (right hand side).
     *
     * @return the value of the constraint (right hand side).
     */
    public double getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.linear.LinearConstraint.equals_186");
        if (this == other) {
            return true;
        }
        if (other instanceof LinearConstraint) {
            LinearConstraint rhs = (LinearConstraint) other;
            return (_mut59543 ? ((_mut59542 ? (relationship == rhs.relationship || ROR_equals(value, rhs.value, "org.apache.commons.math3.optim.linear.LinearConstraint.equals_186", _mut59537, _mut59538, _mut59539, _mut59540, _mut59541)) : (relationship == rhs.relationship && ROR_equals(value, rhs.value, "org.apache.commons.math3.optim.linear.LinearConstraint.equals_186", _mut59537, _mut59538, _mut59539, _mut59540, _mut59541))) || coefficients.equals(rhs.coefficients)) : ((_mut59542 ? (relationship == rhs.relationship || ROR_equals(value, rhs.value, "org.apache.commons.math3.optim.linear.LinearConstraint.equals_186", _mut59537, _mut59538, _mut59539, _mut59540, _mut59541)) : (relationship == rhs.relationship && ROR_equals(value, rhs.value, "org.apache.commons.math3.optim.linear.LinearConstraint.equals_186", _mut59537, _mut59538, _mut59539, _mut59540, _mut59541))) && coefficients.equals(rhs.coefficients)));
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return relationship.hashCode() ^ Double.valueOf(value).hashCode() ^ coefficients.hashCode();
    }

    /**
     * Serialize the instance.
     * @param oos stream where object should be written
     * @throws IOException if object cannot be written to stream
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealVector(coefficients, oos);
    }

    /**
     * Deserialize the instance.
     * @param ois stream from which the object should be read
     * @throws ClassNotFoundException if a class in the stream cannot be found
     * @throws IOException if object cannot be read from the stream
     */
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealVector(this, "coefficients", ois);
    }
}
