package edu.cpp.cs301.p1.Roots.Bracketing;


import edu.cpp.cs301.p1.Functions.MathFunction;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class Bisection extends BracketingMethod {
    public Bisection(int maxIterations, double left, double right, MathFunction mathFunction, Double realRoot) {
        super(maxIterations, left, right, mathFunction, realRoot);
    }

    @Override
    public double getNewCenter(double left, double right) {
        return (left + right) / 2;
    }

    @Override
    protected boolean isDivergent() {
        if (guessedRootFuncList.size() < 2) {
            return false;
        }
        double lastResult = guessedRootFuncList.get(getLastIndex(guessedRootFuncList));
        double secondLastResult = guessedRootFuncList.get(getLastIndex(guessedRootFuncList) - 1);
        if ((lastResult > 0 && secondLastResult > 0 && secondLastResult <= lastResult) ||
                (lastResult < 0 && secondLastResult < 0 && secondLastResult >= lastResult)) {
            return true;
        }
        return false;
    }

    @Override
    protected String getMethodName() {
        return "Bisection";
    }
}
