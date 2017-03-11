package edu.cpp.cs301.p2.FindingPolynomials.Methods;

import edu.cpp.cs301.p2.FindingPolynomials.IndividualTerm;
import edu.cpp.cs301.p2.FindingPolynomials.Misc;
import edu.cpp.cs301.p2.FindingPolynomials.XYPair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 */
public class NewtonMethod extends Interpolation {
    private List<List<Double>> coefficientList;

    public NewtonMethod(List<XYPair> allPairs) {
        super(allPairs);
        coefficientList = new ArrayList<List<Double>>();
    }

    @Override
    public String execute() {
        if (allPairs == null || allPairs.size() == 0) {
            return "Empty Function";
        }
        addOriginalsToList();
        addNewCoefficientsToList();
        createIndividualTerms();
        return this.toString();
    }

    /**
     * Store f(x) values from original list to divided different list.
     */
    private void addOriginalsToList() {
        List<Double> originalValues = new ArrayList<Double>();
        for (XYPair each : allPairs) {
            originalValues.add(each.getyVal());
        }
        coefficientList.add(originalValues);
    }

    /**
     * Calculate new divided differences for each level
     * and add new list to divided different list
     */
    private void addNewCoefficientsToList() {
        for (int level = 0; level < allPairs.size() - 1; level++) {
            List<Double> oldList = coefficientList.get(coefficientList.size() - 1);
            List<Double> newList = new ArrayList<Double>();
            for (int i = 0; i < oldList.size() - 1; i++) {
                newList.add((oldList.get(i + 1) - oldList.get(i)) / (allPairs.get(i + 1 + level).getxVal() - allPairs.get(i).getxVal()));
            }
            coefficientList.add(newList);
        }
    }

    /**
     * Create new individual objects for each first slope value
     * When adding (x - x_0)(x - x_1)...
     * individualTerm just keeps (-x_0, -x_1, ...) in a list
     * So multiply with -1 before storing them to list.
     */
    private void createIndividualTerms() {
        for (int i = 0; i < coefficientList.size(); i++) {
            IndividualTerm individualTerm = new IndividualTerm(coefficientList.get(i).get(0));
            for (int j = 0; j < i; j++) {
                individualTerm.addConstant(-1 * allPairs.get(j).getxVal());
            }
            allTerms.add(individualTerm);
        }
    }

    /**
     * printing in this format
     * x, f[], f[,], f[,,], ...
     * value, value, value, value, ...
     * value, value, value,...
     * .
     * .
     * .
     * <p>
     * Output is suitable to view in excel as comma separated list
     *
     * @return Comma separated divided different list
     */
    public String printDividedDifference() {
        StringBuilder result = new StringBuilder();
        result.append("x").append(Misc.COMMA);
        for (int i = 0; i < allPairs.size(); i++) {
            result.append(getDividedNotation(i)).append(Misc.COMMA);
        }
        result.append(Misc.NEXT_LINE);

        for (int i = 0; i < allPairs.size(); i++) {
            result.append(allPairs.get(i).getxVal()).append(Misc.COMMA);
            for (int j = 0; j < coefficientList.size() - i; j++) {
                result.append(coefficientList.get(j).get(i)).append(Misc.COMMA);
            }
            result.append(Misc.NEXT_LINE);

        }

        return result.toString();
    }

    /**
     * helper method for printDividedDifference
     * printing f[number of commas]
     *
     * @param cycle number of commas needed
     * @return String f[number of commas]
     */
    private StringBuilder getDividedNotation(final int cycle) {
        StringBuilder result = new StringBuilder("f[");
        for (int i = 0; i < cycle; i++) {
            result.append(" , ");
        }
        result.append("]");
        return result;
    }
}

