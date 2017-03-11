package edu.cpp.cs301.p1.Roots.Open;

import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;
import edu.cpp.cs301.p1.Misc.General;
import edu.cpp.cs301.p1.Roots.BasicRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waiphyo
 *         2/6/17.
 */
public abstract class OpenMethod extends BasicRoot {
    protected double initialValue;
    protected List<Double> initFuncList;
    protected MathDerivativeFunction mathDerivativeFunction;

    public OpenMethod(int maxIterations, MathDerivativeFunction mathDerivativeFunction, Double realRoot, double initialValue) {
        super(maxIterations, realRoot);
        this.initialValue = initialValue;
        this.mathDerivativeFunction = mathDerivativeFunction;
        initFuncList = new ArrayList<Double>();
        guessedRootList.add(initialValue);
        initFuncList.add(mathDerivativeFunction.getResult(initialValue));
    }

    @Override
    public BasicRoot findRoot() {
        for (int i = 0; i < getMaxIterations(); i++) {
            initialValue = getNewInitialValue();
            guessedRootList.add(initialValue);
            initFuncList.add(mathDerivativeFunction.getResult(initialValue));
            calculatePercentError();
            calculateTrueError();
            if (checkCondition(i)) {
                break;
            }
        }
        return this;
    }

    protected abstract double getNewInitialValue();

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(General.BLOCK_SEPARATOR).append(getMethodName()).append(General.LINE_SEPARATOR);
        result.append("Math Function: ").append(mathDerivativeFunction.toString()).append(General.NEXT_LINE);
        result.append("Total Iterations: ").append(guessedRootList.size()).append(General.NEXT_LINE);
        result.append("a: ").append(guessedRootList.toString()).append(General.NEXT_LINE);
        result.append("f(a): ").append(initFuncList.toString()).append(General.NEXT_LINE);
        result.append(getErrorString()).append(General.BLOCK_SEPARATOR);
        return result.toString();
    }
}
