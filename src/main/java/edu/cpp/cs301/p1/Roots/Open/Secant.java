package edu.cpp.cs301.p1.Roots.Open;

import edu.cpp.cs301.p1.Functions.MathFunction;
import edu.cpp.cs301.p1.Misc.General;
import edu.cpp.cs301.p1.Roots.BasicRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class Secant extends BasicRoot {
    private double xInitial;
    private double xPrevious;
    private MathFunction mathFunction;

    private List<Double> initList;
    private List<Double> prevList;
    private List<Double> initFuncList;
    private List<Double> prevFuncList;
    private List<Double> nextFuncList;

    public Secant(int maxIterations, MathFunction mathFunction, Double realRoot, double xInitial, double xPrevious) {
        super(maxIterations, realRoot);
        this.xInitial = xInitial;
        this.xPrevious = xPrevious;
        this.mathFunction = mathFunction;
        initList = new ArrayList<Double>();
        prevList = new ArrayList<Double>();
        prevFuncList = new ArrayList<Double>();
        initFuncList = new ArrayList<Double>();
        prevFuncList = new ArrayList<Double>();
        nextFuncList = new ArrayList<Double>();
    }

    @Override
    public BasicRoot findRoot() {
        swapPlaces();
        updateLists();
        calculatePercentError();
        calculateTrueError();
        for (int i = 1; i < getMaxIterations(); i++) {
            swapPlaces();
            updateLists();
            calculatePercentError();
            calculateTrueError();
            if (checkCondition(i)) {
                break;
            }
        }
        return this;
    }

    private void swapPlaces() {
        double initMath = Math.abs(mathFunction.getResult(xInitial));
        double prevMath = Math.abs(mathFunction.getResult(xPrevious));

        if (initMath > prevMath) {
            double temp = xInitial;
            xInitial = xPrevious;
            xPrevious = temp;
        }
    }

    private void updateLists() {
        initList.add(xInitial);
        prevList.add(xPrevious);
        initFuncList.add(mathFunction.getResult(xInitial));
        prevFuncList.add(mathFunction.getResult(xPrevious));
        guessedRoot = getNewInitialValue();
        guessedRootList.add(guessedRoot);
        nextFuncList.add(mathFunction.getResult(guessedRoot));
        xPrevious = xInitial;
        xInitial = guessedRoot;
    }
    private double getNewInitialValue() {
        double initMath = initFuncList.get(getLastIndex(initFuncList));
        double prevMath = prevFuncList.get(getLastIndex(prevFuncList));
        return xInitial - (initMath * (xInitial - xPrevious)/(initMath - prevMath));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(General.BLOCK_SEPARATOR).append(getMethodName()).append(General.LINE_SEPARATOR);
        result.append("Math Function: ").append(mathFunction.toString()).append(General.NEXT_LINE);
        result.append("Total Iterations: ").append(guessedRootList.size()).append(General.NEXT_LINE);
        result.append("x_i-1 ").append(prevList.toString()).append(General.NEXT_LINE);
        result.append("x_i ").append(initList.toString()).append(General.NEXT_LINE);
        result.append("x_i+1 ").append(guessedRootList.toString()).append(General.NEXT_LINE);
        result.append("f(x_i-1): ").append(prevFuncList.toString()).append(General.NEXT_LINE);
        result.append("f(x_i): ").append(initFuncList.toString()).append(General.NEXT_LINE);
        result.append("f(x_i+1): ").append(nextFuncList.toString()).append(General.NEXT_LINE);
        result.append(getErrorString()).append(General.BLOCK_SEPARATOR);
        return result.toString();
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
        return "Secant";
    }
}
