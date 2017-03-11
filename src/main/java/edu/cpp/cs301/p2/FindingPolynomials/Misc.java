package edu.cpp.cs301.p2.FindingPolynomials;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 */
public class Misc {

    /**
     * Generic symbols used in printing outputs
     */
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String NEXT_LINE = "\n";
    public static final String COMMA = ",";

    /**
     * Checking whether string is a valid double
     * <p>
     * start with "-" for negative
     * start with nothing for positives.
     * <p>
     * If it is integer, should have only digits.
     * If it is double, should have 1 or more digits
     * followed by "."
     * followed by one or more digits
     *
     * @param input String
     * @return boolean
     */
    public static boolean isNumber(String input) {
        return input.matches("(^-?\\d+$)|(^-?\\d+\\.\\d+$)");
    }
}
