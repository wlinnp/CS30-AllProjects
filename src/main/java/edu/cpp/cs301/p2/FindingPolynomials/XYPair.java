package edu.cpp.cs301.p2.FindingPolynomials;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 *         <p>
 *         Pair class for doubles
 */
public class XYPair {
    private double xVal;
    private double yVal;

    public XYPair(double xVal, double yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public double getxVal() {
        return xVal;
    }

    public void setxVal(double xVal) {
        this.xVal = xVal;
    }

    public double getyVal() {
        return yVal;
    }

    public void setyVal(double yVal) {
        this.yVal = yVal;
    }


}
