package edu.cpp.cs301.p2.FindingPolynomials;

import edu.cpp.cs301.p2.FindingPolynomials.Methods.Interpolation;
import edu.cpp.cs301.p2.FindingPolynomials.Methods.LagrangeMethod;
import edu.cpp.cs301.p2.FindingPolynomials.Methods.NewtonMethod;

import java.util.List;
import java.util.Scanner;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 */
public class App {
    /**
     * Check file path is given from arguments.
     * If not, ask for input file path
     * Convert input to correctly formatted list
     *
     * @param args Input File Path
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            args = new String[1];
            System.out.println("Please enter input file path.");
            Scanner scanner = new Scanner(System.in);
            args[0] = scanner.nextLine();
            scanner.close();
        }
        calculatePolynomials((new GetInputsFromFile(args[0])).getPolynomialPairsFromFile());
    }

    /**
     * Compute polynomials based on input.
     * Print Newton method's 3 forms
     * Print Lagrange method's 2 forms
     * Verify visually whether the methods are correct by comparing simplified forms
     *
     * @param allPairs
     */
    private static void calculatePolynomials(List<XYPair> allPairs) {

        NewtonMethod newtonMethod = new NewtonMethod(allPairs);
        System.out.println("Newton Method");
        System.out.println("================================");
        System.out.println("Interpolating polynomial is: ");
        System.out.println("================================");
        System.out.println(newtonMethod.execute());
        System.out.println("Simplified polynomial is: ");
        System.out.println("================================");
        System.out.println(newtonMethod.convertToPolynomial().toString());
        System.out.println("Divided Difference Table");
        System.out.println("================================");
        System.out.println(newtonMethod.printDividedDifference());


        Interpolation lagrangeMethod = new LagrangeMethod(allPairs);
        System.out.println("Lagrange Method");
        System.out.println("================================");
        System.out.println("Interpolating polynomial is: ");
        System.out.println("================================");
        System.out.println(lagrangeMethod.execute());
        System.out.println("Simplified polynomial is: ");
        System.out.println("================================");
        System.out.println(lagrangeMethod.convertToPolynomial().toString());
    }
}

