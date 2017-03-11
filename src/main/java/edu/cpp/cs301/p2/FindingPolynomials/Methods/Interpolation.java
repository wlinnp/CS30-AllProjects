package edu.cpp.cs301.p2.FindingPolynomials.Methods;

import edu.cpp.cs301.p2.FindingPolynomials.IndividualTerm;
import edu.cpp.cs301.p2.FindingPolynomials.Polynomial;
import edu.cpp.cs301.p2.FindingPolynomials.XYPair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 *         <p>
 *         It has all input Pairs (x, f(x))
 *         It will compute all output pairs a(x-x_0)(x-x_1)...
 *         and store them in a list
 */
public abstract class Interpolation {
    protected List<XYPair> allPairs;
    protected List<IndividualTerm> allTerms;

    public Interpolation(List<XYPair> allPairs) {
        this.allPairs = allPairs;
        allTerms = new ArrayList<IndividualTerm>();
    }

    public abstract String execute();

    /**
     * Convert each term to polynomial
     * and add them all together
     *
     * @return simplified polynomial
     */
    public Polynomial convertToPolynomial() {
        Polynomial result = new Polynomial(0);
        for (IndividualTerm each : allTerms) {
            result = result.add(each.convertToPolynomial());
        }
        return result;
    }

    /**
     * String representation of each term - a(x-x_0)(x-x_1)...
     * separated by "+"
     */
    public String toString() {
        if (allPairs == null || allPairs.size() == 0) {
            return "Empty Function";
        }
        StringBuilder result = new StringBuilder();
        int counter = 0;
        int maxTerms = allTerms.size() - 1;
        for (IndividualTerm each : allTerms) {
            result.append(each.toString());
            if (counter++ < maxTerms) {
                result.append(" + ");
            }
        }
        return result.toString();

    }

}
