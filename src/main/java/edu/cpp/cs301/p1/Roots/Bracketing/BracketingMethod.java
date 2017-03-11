package edu.cpp.cs301.p1.Roots.Bracketing;

import edu.cpp.cs301.p1.Functions.MathFunction;
import edu.cpp.cs301.p1.Misc.General;
import edu.cpp.cs301.p1.Roots.BasicRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waiphyo
 *         1/28/17.
 */
public abstract class BracketingMethod extends BasicRoot {
    private double left;
    private double right;
    private MathFunction mathFunction;
    private List<Double> leftList;
    private List<Double> rightList;
    private List<Double> leftFuncList;
    private List<Double> rightFuncList;
    protected List<Double> guessedRootFuncList;

    public BracketingMethod(int maxIterations, double left, double right, MathFunction mathFunction, Double realRoot) {
        super(maxIterations, realRoot);
        this.mathFunction = mathFunction;
        if (!initialPointsValidations(left, right)) {
            throw new IllegalArgumentException("Invalid initial points");
        }
        this.left = left;
        this.right = right;
        leftList = new ArrayList<Double>();
        rightList = new ArrayList<Double>();
        leftFuncList = new ArrayList<Double>();
        rightFuncList = new ArrayList<Double>();
        guessedRootFuncList = new ArrayList<Double>();
    }

    /**
     * Find the roots based on number of iterations
     * @return current class for caller to use as a chain function
     */
    public BasicRoot findRoot() {
        firstIteration();
        for (int i = 1; i < getMaxIterations(); i++) {
            eachIteration();
            if (checkCondition(i)) {
                break;
            }
        }
        return this;
    }

    protected double getLastLeftFunction() {
        return leftFuncList.get(getLastIndex(leftFuncList));
    }

    protected double getLastRightFunction() {
        return rightFuncList.get(getLastIndex(rightFuncList));
    }

    private void firstIteration() {
        leftList.add(left);
        rightList.add(right);
        leftFuncList.add(mathFunction.getResult(left));
        rightFuncList.add(mathFunction.getResult(right));

        guessedRoot = getNewCenter(left, right);
        guessedRootList.add(guessedRoot);
        guessedRootFuncList.add(mathFunction.getResult(guessedRoot));
        calculateTrueError();
    }

    private void eachIteration() {
        if (IsRootInRight()) {
            rightList.add(guessedRootList.get(getLastIndex(guessedRootList)));
            rightFuncList.add(guessedRootFuncList.get(getLastIndex(guessedRootFuncList)));
            leftList.add(leftList.get(getLastIndex(leftList)));
            leftFuncList.add(leftFuncList.get(getLastIndex(leftFuncList)));
            right = guessedRoot;

        } else {
            leftList.add(guessedRootList.get(getLastIndex(guessedRootList)));
            leftFuncList.add(guessedRootFuncList.get(getLastIndex(guessedRootFuncList)));
            rightList.add(rightList.get(getLastIndex(rightList)));
            rightFuncList.add(rightFuncList.get(getLastIndex(rightFuncList)));
            left = guessedRoot;
        }
        guessedRoot = getNewCenter(left, right);
        guessedRootList.add(guessedRoot);
        guessedRootFuncList.add(mathFunction.getResult(guessedRoot));
        calculatePercentError();
        calculateTrueError();
    }

    private boolean IsRootInRight() {
        return !((leftFuncList.get(getLastIndex(leftFuncList)) <= 0 &&
            guessedRootFuncList.get(getLastIndex(guessedRootFuncList)) <= 0) ||
            (leftFuncList.get(getLastIndex(leftFuncList)) >= 0 &&
            guessedRootFuncList.get(getLastIndex(guessedRootFuncList)) >= 0));
    }

    public abstract double getNewCenter(double left, double right);

    private boolean initialPointsValidations( double left, double right) {
        double leftMath = mathFunction.getResult(left);
        double rightMath = mathFunction.getResult(right);

        return (leftMath >= 0 && rightMath < 0) ||
                (leftMath > 0 && rightMath <= 0) ||
                (rightMath > 0 && leftMath <= 0) ||
                (rightMath >= 0 && leftMath < 0);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(General.BLOCK_SEPARATOR).append(getMethodName()).append(General.LINE_SEPARATOR);
        result.append("Math Function: ").append(mathFunction.toString()).append(General.NEXT_LINE);
        result.append("Total Iterations: ").append(guessedRootList.size()).append(General.NEXT_LINE);
        result.append("a: ").append(leftList.toString()).append(General.NEXT_LINE);
        result.append("b: ").append(rightList.toString()).append(General.NEXT_LINE);
        result.append("c: ").append(guessedRootList.toString()).append(General.NEXT_LINE);
        result.append("f(a): ").append(leftFuncList.toString()).append(General.NEXT_LINE);
        result.append("f(b): ").append(rightFuncList.toString()).append(General.NEXT_LINE);
        result.append("f(c): ").append(guessedRootFuncList.toString()).append(General.NEXT_LINE);
        result.append(getErrorString()).append(General.BLOCK_SEPARATOR);
        return result.toString();
    }
}
