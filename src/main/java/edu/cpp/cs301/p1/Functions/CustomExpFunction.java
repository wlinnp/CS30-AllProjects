package edu.cpp.cs301.p1.Functions;

/**
 * @author waiphyo
 *         1/28/17.
 * Fixed function for second part of project
 */
public class CustomExpFunction implements MathDerivativeFunction {
    public double getResult(double xValue) {
        return Math.exp((-1) * xValue) - xValue;
    }

    public String toString() {
        return "e^-x - x";
    }

    public double getDerivativeResult(double xValue) {
        return ((-1) * Math.exp((-1) * xValue)) - 1;
    }
}
