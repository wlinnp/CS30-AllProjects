package edu.cpp.cs301.p1.Functions;

/**
 * @author waiphyo
 *         1/28/17.
 * Fixed Sine function for testing
 */
public class CustomSineFunction implements MathDerivativeFunction {
    public double getResult(double xValue) {
        return Math.sin(xValue);
    }

    public String toString() {
        return "e^-x - x";
    }

    public double getDerivativeResult(double xValue) {
        return Math.cos(xValue);
    }
}
