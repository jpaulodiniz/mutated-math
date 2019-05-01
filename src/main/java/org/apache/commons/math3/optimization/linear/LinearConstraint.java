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
package org.apache.commons.math3.optimization.linear;

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
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class LinearConstraint implements Serializable {

    @Conditional
    public static boolean _mut71075 = false, _mut71076 = false, _mut71077 = false, _mut71078 = false, _mut71079 = false, _mut71080 = false, _mut71081 = false, _mut71082 = false, _mut71083 = false, _mut71084 = false, _mut71085 = false, _mut71086 = false, _mut71087 = false, _mut71088 = false, _mut71089 = false, _mut71090 = false, _mut71091 = false, _mut71092 = false, _mut71093 = false, _mut71094 = false, _mut71095 = false, _mut71096 = false, _mut71097 = false, _mut71098 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_125");
        double[] sub = new double[lhsCoefficients.length];
        for (int i = 0; ROR_less(i, sub.length, "org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_125", _mut71079, _mut71080, _mut71081, _mut71082, _mut71083); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_125");
            sub[i] = AOR_minus(lhsCoefficients[i], rhsCoefficients[i], "org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_125", _mut71075, _mut71076, _mut71077, _mut71078);
        }
        this.coefficients = new ArrayRealVector(sub, false);
        this.relationship = relationship;
        this.value = AOR_minus(rhsConstant, lhsConstant, "org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_125", _mut71084, _mut71085, _mut71086, _mut71087);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_156");
        this.coefficients = lhsCoefficients.subtract(rhsCoefficients);
        this.relationship = relationship;
        this.value = AOR_minus(rhsConstant, lhsConstant, "org.apache.commons.math3.optimization.linear.LinearConstraint.LinearConstraint_156", _mut71088, _mut71089, _mut71090, _mut71091);
    }

    /**
     * Get the coefficients of the constraint (left hand side).
     * @return coefficients of the constraint (left hand side)
     */
    public RealVector getCoefficients() {
        return coefficients;
    }

    /**
     * Get the relationship between left and right hand sides.
     * @return relationship between left and right hand sides
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * Get the value of the constraint (right hand side).
     * @return value of the constraint (right hand side)
     */
    public double getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.linear.LinearConstraint.equals_189");
        if (this == other) {
            return true;
        }
        if (other instanceof LinearConstraint) {
            LinearConstraint rhs = (LinearConstraint) other;
            return (_mut71098 ? ((_mut71097 ? ((relationship == rhs.relationship) || (ROR_equals(value, rhs.value, "org.apache.commons.math3.optimization.linear.LinearConstraint.equals_189", _mut71092, _mut71093, _mut71094, _mut71095, _mut71096))) : ((relationship == rhs.relationship) && (ROR_equals(value, rhs.value, "org.apache.commons.math3.optimization.linear.LinearConstraint.equals_189", _mut71092, _mut71093, _mut71094, _mut71095, _mut71096)))) || coefficients.equals(rhs.coefficients)) : ((_mut71097 ? ((relationship == rhs.relationship) || (ROR_equals(value, rhs.value, "org.apache.commons.math3.optimization.linear.LinearConstraint.equals_189", _mut71092, _mut71093, _mut71094, _mut71095, _mut71096))) : ((relationship == rhs.relationship) && (ROR_equals(value, rhs.value, "org.apache.commons.math3.optimization.linear.LinearConstraint.equals_189", _mut71092, _mut71093, _mut71094, _mut71095, _mut71096)))) && coefficients.equals(rhs.coefficients)));
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
