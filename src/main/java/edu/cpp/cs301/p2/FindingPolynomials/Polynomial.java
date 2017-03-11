package edu.cpp.cs301.p2.FindingPolynomials;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 *         <p>
 *         Polynomial class with coefficients array starting from x^0
 *         Incomplete class which works only for this project.
 */
public class Polynomial {
    /**
     * for degree of 3, array should be
     * [x^0, x^1, x^2,x^3]
     */
    private Map<Integer, Double> coefficient;
    private int degree;

    public Polynomial(int degree) {
        this.degree = degree;
        coefficient = new TreeMap<Integer, Double>();
    }

    public Polynomial(Map<Integer, Double> coefficient) {
        this.coefficient = coefficient;
        degree = coefficient.size() - 1;
    }

    /**
     * @param constant number
     * @return new polynomial with a constant
     */
    public Polynomial multiplyConstant(double constant) {
        Polynomial result = new Polynomial(degree);
        for (Map.Entry<Integer, Double> each : coefficient.entrySet()) {
            result.setCoefficientAtIndex(each.getKey(), each.getValue() * constant);
        }
        return result;
    }

    public int getDegree() {
        return degree;
    }

    /**
     * NOTE: NO index validation
     *
     * @param index of array
     * @return value of cell at index
     */
    public Double getCoefficientAtIndex(int index) {
        return coefficient.get(index);
    }

    public Set<Integer> getDegreeSet() {
        return coefficient.keySet();
    }

    /**
     * NOTE: NO index validation.
     *
     * @param index of array
     * @param value   new
     * @return updated polynomial
     */
    public Polynomial setCoefficientAtIndex(int index, double value) {
        coefficient.put(index, value);
        return this;
    }

    /**
     * Adding 2 polynomials
     * Create new polynomial with bigger degree of the 2 input
     * Adding 2 values till minimum degree.
     * copy the rest from polynomial with higher degree
     *
     * @param right polynomial
     * @return new polynomial
     */
    public Polynomial add(Polynomial right) {
        Polynomial result = new Polynomial(Math.max(degree, right.getDegree()));

        Set<Integer> leftSet = coefficient.keySet();
        Set<Integer> rightSet = right.getDegreeSet();
        for (Integer each : leftSet) {
            double rightCell = 0;
            if (rightSet.contains(each)) {
                rightCell = right.getCoefficientAtIndex(each);
                rightSet.remove(each);
            }
            result.setCoefficientAtIndex(each, coefficient.get(each) + rightCell);
        }
        for (Integer each : rightSet) {
            result.setCoefficientAtIndex(each, right.getCoefficientAtIndex(each));
        }
        return result;
    }

    public Polynomial multiply(Polynomial right) {
        Polynomial result = new Polynomial(degree + right.getDegree());
        for (Integer rightKey : right.getDegreeSet()) {
            for (Map.Entry<Integer, Double> each : coefficient.entrySet()) {
                int newDegree = rightKey + each.getKey();
                double newValue = each.getValue() * right.getCoefficientAtIndex(rightKey);
                if (result.getCoefficientAtIndex(newDegree) != null) {
                    newValue = newValue + result.getCoefficientAtIndex(newDegree);
                }
                result.setCoefficientAtIndex(newDegree, newValue);
            }
        }
        return result;
    }

    /**
     * Printing polynomial in readable form.
     * NOTE: keeping Zero values for verification
     * NOTE: keeping x^0 to keep the result uniform.
     */
    public String toString() {
        if (coefficient == null || coefficient.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean firstTerm = true;
        for (Map.Entry<Integer, Double> each : coefficient.entrySet()) {
            if (firstTerm) {
                firstTerm = false;
            } else {
                result.append(" + ");
            }
            result.append(each.getValue()).append("x^").append(each.getKey());
        }
        return result.toString();
    }
}
