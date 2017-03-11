package edu.cpp.cs301.p1.Functions;

import edu.cpp.cs301.p1.Misc.General;

/**
 * @author waiphyo
 *         1/28/17.
 * Generic Polynomial function class.
 * <p></p>
 * consumed by First part of project
 */
public class PolynomialFunction implements MathDerivativeFunction {

    private int degree;
    private double[] coefficient;
    private double[] derivativeCoefficient;

    /**
     * @param degree Highest degree for polynomial function, 0 to MAX_INT
     * @param coefficient double array holding coefficients for each degree.
     */
    public PolynomialFunction(final int degree, final double[] coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException("Degree cannot be less than 0");
        }
        if (degree + 1 != coefficient.length) {
            throw new IllegalArgumentException("Coefficient mismatch. Please fill empty coefficients with Zeroes");
        }
        this.degree = degree;
        this.coefficient = coefficient;
        getDerivative();
    }

    /**
     * First first order derivative of the polynomial function.
     * It assumes that the polynomial form is correct.
     */
    private void getDerivative() {
        derivativeCoefficient = new double[degree];
        int currentDegree = degree;
        for (int i = 0; i < degree; i++) {
            derivativeCoefficient[i] = coefficient[i] * currentDegree;
            currentDegree--;
        }
    }

    /**
     * @param xValue double: any x
     * @return double: f(x)
     */
    public double getResult(double xValue) {
        double result = 0;
        for(int i = 0; i < degree; i++) {
            result = (coefficient[i] + result) * xValue;
        }
        return result + coefficient[degree];
    }

    /**
     * Helper function for printing polynomial
     * @param index int: index of coefficient array
     * @return value of index is less than 0.
     */
    private boolean isNegative(int index) {
        return coefficient[index] < 0;
    }

    /**
     * Helper function for printing polynomial
     * @param index int: index of coefficient array
     * @return add "+" in the beginning.
     */
    private StringBuilder polynomialConnector(int index) {
        return new StringBuilder(isNegative(index) ? "" : "+");
    }

    public String toString() {
        if (degree < 0) {
            return "";
        }
        int currentDegree = degree;
        StringBuilder result = new StringBuilder();
        result.append(coefficient[0]).append("x^").append(currentDegree).append(General.SPACE);
        for(int i = 1; i < degree; i++) {
            currentDegree--;
            if (coefficient[i] != 0) {
                result.append(polynomialConnector(i));
                result.append(coefficient[i]).append("x^").append(currentDegree).append(General.SPACE);
            }
        }
        result.append(polynomialConnector(degree));
        result.append(coefficient[degree]).append(General.NEXT_LINE);
        return result.toString();
    }

    /**
     * @param xValue double: any x
     * @return double: f'(x)
     */
    public double getDerivativeResult(double xValue) {
        double result = 0;
        for(int i = 0; i < degree - 1; i++) {
            result = (derivativeCoefficient[i] + result) * xValue;
        }
        return result + derivativeCoefficient[degree - 1];
    }
}
