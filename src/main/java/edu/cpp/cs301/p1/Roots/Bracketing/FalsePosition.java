package edu.cpp.cs301.p1.Roots.Bracketing;

import edu.cpp.cs301.p1.Functions.MathFunction;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class FalsePosition extends BracketingMethod {
    public FalsePosition(int maxIterations, double left, double right, MathFunction mathFunction, Double realRoot) {
        super(maxIterations, left, right, mathFunction, realRoot);
    }

    @Override
    public double getNewCenter(double left, double right) {
        return ((left * getLastRightFunction()) - (right * getLastLeftFunction())) / (getLastRightFunction() - getLastLeftFunction());
    }

    @Override
    protected boolean isDivergent() {
        return Math.abs(guessedRootFuncList.get(getLastIndex(guessedRootFuncList) - 1)) < Math.abs(guessedRootFuncList.get(getLastIndex(guessedRootFuncList)));
    }


    @Override
    protected String getMethodName() {
        return "False Position";
    }
}
