package edu.cpp.cs301.p1.Roots;

import edu.cpp.cs301.p1.Misc.General;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waiphyo
 *         1/28/17.
 * Base class for general methods
 */
public abstract class BasicRoot {
    /**
     * NOTE:
     * Project states that MIN_ERROR can be 1% or 0.01.
     * For the purpose of getting more graphs, it is reduced to 0.0001
     */
    private static final double MIN_ERROR = 0.0001;
    protected static final int MIN_ITERATIONS = 10;
    protected double guessedRoot;
    protected Double realRoot;

    protected List<Double> guessedRootList;
    protected List<Double> approxRelativeErrList;
    protected List<Double> trueRelativeErrList;
    private int maxIterations;

    public BasicRoot(int maxIterations, Double realRoot) {
        updateIterations(maxIterations);
        approxRelativeErrList = new ArrayList<Double>();
        trueRelativeErrList = new ArrayList<Double>();
        this.realRoot = realRoot;
        guessedRootList = new ArrayList<Double>();
    }

    private void updateIterations(int maxIterations) {
        if (maxIterations < 0) {
            throw new IllegalArgumentException("Number of Iterations must be positive");
        }
        this.maxIterations = maxIterations;
    }

    protected int getMaxIterations() {
        return maxIterations;
    }

    protected void calculatePercentError() {
        if (guessedRootList.size() >= 2) {
            int lastIndex = getLastIndex(guessedRootList);
            approxRelativeErrList.add((Math.abs(guessedRootList.get(lastIndex) - guessedRootList.get(lastIndex - 1))) / guessedRootList.get(lastIndex));
        }
    }

    protected void calculateTrueError() {
        if (realRoot != null) {
            trueRelativeErrList.add(Math.abs(realRoot - guessedRootList.get(getLastIndex(guessedRootList))) / realRoot);
        }
    }

    protected int getLastIndex(List list) {
        return list.size() - 1;
    }

    public abstract BasicRoot findRoot();

    /**
     * Check if the results are diverging based on individual methods
     * <p></p>
     * If there is real root, check true error is getting smaller
     * <p></p>
     * Check relative error is getting smaller.
     * @return whether the method results are getting close to 0.
     */
    protected boolean checkCondition(int ithIteration) {
        Double lastApproxErr = approxRelativeErrList.get(getLastIndex(approxRelativeErrList));
        if (lastApproxErr.isNaN() || lastApproxErr.isInfinite() || lastApproxErr == 0) {
            return true;
        }
        return (ithIteration >= MIN_ITERATIONS) &&
                (isDivergent() ||
                (realRoot != null && trueRelativeErrList.get(getLastIndex(trueRelativeErrList)) < BasicRoot.MIN_ERROR) ||
                (lastApproxErr < BasicRoot.MIN_ERROR));
    }

    protected abstract boolean isDivergent();

    protected abstract String getMethodName();

    protected StringBuilder getErrorString() {
        StringBuilder result = new StringBuilder();
        result.append("Error_a: ").append(approxRelativeErrList.toString()).append(General.NEXT_LINE);
        if (realRoot != null) {
            result.append("Error_t: ").append(trueRelativeErrList.toString()).append(General.NEXT_LINE);
            result.append("Real Root: " + realRoot).append(General.NEXT_LINE);
        }
        return result;
    }
}
