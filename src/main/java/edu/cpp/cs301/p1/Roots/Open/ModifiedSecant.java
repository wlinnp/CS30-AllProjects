package edu.cpp.cs301.p1.Roots.Open;

import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class ModifiedSecant extends OpenMethod {
    private double delta;

    public ModifiedSecant(int maxIterations, MathDerivativeFunction mathDerivativeFunction, Double realRoot, double initialValue, double delta) {
        super(maxIterations, mathDerivativeFunction, realRoot, initialValue);
        this.delta = delta;
    }

    @Override
    protected double getNewInitialValue() {
        double xPlusH = initialValue * ( 1 + delta);
        double initMath = mathDerivativeFunction.getResult(initialValue);
        double xPlusHMath = mathDerivativeFunction.getResult(xPlusH);
        return initialValue - ((initMath * delta * initialValue) / (xPlusHMath - initMath));
    }

    @Override
    protected boolean isDivergent() {
        if (realRoot == null || guessedRootList.size() < 2) {
            return false;
        } else {
            return Math.abs(guessedRootList.get(getLastIndex(guessedRootList)) - realRoot) > Math.abs(guessedRootList.get(getLastIndex(guessedRootList) - 1) - realRoot);
        }
    }

    @Override
    protected String getMethodName() {
        return "Modified Secant";
    }
}
