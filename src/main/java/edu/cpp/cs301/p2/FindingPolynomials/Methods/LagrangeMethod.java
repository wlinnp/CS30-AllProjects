package edu.cpp.cs301.p2.FindingPolynomials.Methods;

import edu.cpp.cs301.p2.FindingPolynomials.IndividualTerm;
import edu.cpp.cs301.p2.FindingPolynomials.XYPair;

import java.util.List;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 */
public class LagrangeMethod extends Interpolation {

    public LagrangeMethod(List<XYPair> allPairs) {
        super(allPairs);
    }

    @Override
    public String execute() {
        if (allPairs == null || allPairs.size() == 0) {
            return "Empty Function";
        }
        calculateAllTerms();
        return this.toString();
    }

    private void calculateAllTerms() {
        for (int i = 0; i < allPairs.size(); i++) {
            IndividualTerm individualTerm = new IndividualTerm(allPairs.get(i).getyVal());
            double currentXVal = allPairs.get(i).getxVal();
            for (int j = 0; j < allPairs.size(); j++) {
                if (i != j) {
                    double tempXVal = -1 * allPairs.get(j).getxVal();
                    individualTerm.setCoefficient(individualTerm.getCoefficient() / (currentXVal + tempXVal));
                    individualTerm.addConstant(tempXVal);
                }
            }
            allTerms.add(individualTerm);
        }
    }
}
