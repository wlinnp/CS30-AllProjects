package edu.cpp.cs301.p2.FindingPolynomials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 *         <p>
 *         Each term for polynomials
 */
public class IndividualTerm {
    private double coefficient;
    private List<Double> polyFirstConstants;

    public IndividualTerm(double coefficient) {
        this.coefficient = coefficient;
        polyFirstConstants = new ArrayList<Double>();
    }

    public void addConstant(double constant) {
        polyFirstConstants.add(constant);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * Create new polynomial with coefficient.
     * Multiply to that polynomial for each constants
     * by considering each term as polynomial of degree 1
     * <p>
     * Example: for the term 6(x - 1)(x - 2)(x - 3).
     * Individual term keep it as 6 & [-1, -2, -3].
     * So first convert 6 to polynomial. 6
     * convert each term -1 to (x + -1) and multiply to 6.
     * repeat
     *
     * @return new Polynomial
     */
    public Polynomial convertToPolynomial() {
        Polynomial result = new Polynomial(0);
        result.setCoefficientAtIndex(0, coefficient);
        for (Double each : polyFirstConstants) {
            result = result.multiply(
                    (new Polynomial(1))
                            .setCoefficientAtIndex(0, each)
                            .setCoefficientAtIndex(1, 1));
        }
        return result;
    }

    public String toString() {
        if (coefficient == 0) {
            return "0";
        }
        if (polyFirstConstants.size() == 0) {
            return Double.toString(coefficient);
        }
        StringBuilder result = new StringBuilder();
        result.append(coefficient);
        for (Double each : polyFirstConstants) {
            result.append("(x + ").append(each).append(")");
        }
        return result.toString();
    }


}
