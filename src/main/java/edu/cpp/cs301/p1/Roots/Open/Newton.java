package edu.cpp.cs301.p1.Roots.Open;

import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class Newton extends OpenMethod {

    public Newton(int maxIterations, MathDerivativeFunction mathDerivativeFunction, Double realRoot, double initialValue) {
        super(maxIterations, mathDerivativeFunction, realRoot, initialValue);
    }

    @Override
    protected double getNewInitialValue() {
        return initialValue - (mathDerivativeFunction.getResult(initialValue) / mathDerivativeFunction.getDerivativeResult(initialValue));
    }

    @Override
    protected boolean isDivergent() {
        if (realRoot == null || guessedRootList.size() < 2) {
            return false;
        }
        return Math.abs(guessedRootList.get(getLastIndex(guessedRootList)) - realRoot) > Math.abs(guessedRootList.get(getLastIndex(guessedRootList) - 1) - realRoot);
    }

    @Override
    protected String getMethodName() {
        return "Newton";
    }
}
