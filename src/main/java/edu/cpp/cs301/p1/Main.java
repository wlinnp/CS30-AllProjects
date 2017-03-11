package edu.cpp.cs301.p1;

import edu.cpp.cs301.p1.Misc.General;
import edu.cpp.cs301.p1.Roots.Bracketing.Bisection;
import edu.cpp.cs301.p1.Roots.Bracketing.FalsePosition;
import edu.cpp.cs301.p1.Roots.Open.ModifiedSecant;
import edu.cpp.cs301.p1.Roots.Open.Newton;
import edu.cpp.cs301.p1.Roots.Open.Secant;
import edu.cpp.cs301.p1.Functions.CustomExpFunction;
import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;
import edu.cpp.cs301.p1.Functions.MathFunction;
import edu.cpp.cs301.p1.Functions.PolynomialFunction;

import java.util.Scanner;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter maximum iterations");
        int maxIterations = scanner.nextInt();
        System.out.println("Enter delta");
        double delta = scanner.nextDouble();
        scanner.close();
        System.out.println(General.BLOCK_SEPARATOR + "PART - 1" +General.BLOCK_SEPARATOR);
        polyFunc1(maxIterations, delta);
        System.out.println(General.BLOCK_SEPARATOR + "PART - 2" +General.BLOCK_SEPARATOR);
        expFunc1(maxIterations, delta);
    }

    private static void polyFunc1(final int maxIterations, final double delta) {
        MathFunction function = new PolynomialFunction(3, new double[] {2, -11.7, 17.7, -5});
        MathDerivativeFunction derivativeFunction = (PolynomialFunction)function;
        System.out.println("at x = 0, f(x) = " + function.getResult(0));
        System.out.println("at x = 1, f(x) = " + function.getResult(1));
        System.out.println("at x = 2, f(x) = " + function.getResult(2));
        System.out.println("at x = 3, f(x) = " + function.getResult(3));
        System.out.println("at x = 4, f(x) = " + function.getResult(4));
        System.out.println(General.BLOCK_SEPARATOR);

        System.out.println(new Bisection(maxIterations, 0, 1, function, null).findRoot().toString());
        System.out.println(new Bisection(maxIterations, 1, 2, function, null).findRoot().toString());
        try {
            System.out.println(new Bisection(maxIterations, 2, 3, function, null).findRoot().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(new Bisection(maxIterations, 3, 4, function, null).findRoot().toString());

        System.out.println(new FalsePosition(maxIterations, 0, 1, function, null).findRoot().toString());
        System.out.println(new FalsePosition(maxIterations, 1, 2, function, null).findRoot().toString());
        try {
            System.out.println(new FalsePosition(maxIterations, 2, 3, function, null).findRoot().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(new FalsePosition(maxIterations, 3, 4, function, null).findRoot().toString());

        System.out.println(new Newton(maxIterations, derivativeFunction, null, 0).findRoot().toString());
        System.out.println(new Newton(maxIterations, derivativeFunction, null, 1).findRoot().toString());
        System.out.println(new Newton(maxIterations, derivativeFunction, null, 2).findRoot().toString());
        System.out.println(new Newton(maxIterations, derivativeFunction, null, 3).findRoot().toString());
        System.out.println(new Newton(maxIterations, derivativeFunction, null, 4).findRoot().toString());

        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, null, 0.1, delta).findRoot().toString());
        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, null, 1, delta).findRoot().toString());
        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, null, 2, delta).findRoot().toString());
        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, null, 3, delta).findRoot().toString());
        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, null, 4, delta).findRoot().toString());

        System.out.println(new Secant(maxIterations, function, null, -1, 0).findRoot().toString());
        System.out.println(new Secant(maxIterations, function, null, 0.5, 1.5).findRoot().toString());
        System.out.println(new Secant(maxIterations, function, null, 1, 1.5).findRoot().toString());
        System.out.println(new Secant(maxIterations, function, null, 2.5, 3.5).findRoot().toString());

    }

    private static void expFunc1(final int maxIterations, final double delta) {
        MathFunction function = new CustomExpFunction();
        MathDerivativeFunction derivativeFunction = (CustomExpFunction)function;

        System.out.println(new Bisection(maxIterations, 0, 1, function, 0.56714329).findRoot().toString());
        System.out.println(new FalsePosition(maxIterations, 0, 1, function, 0.56714329).findRoot().toString());
        System.out.println(new Newton(maxIterations, derivativeFunction, 0.56714329, 1).findRoot().toString());
        System.out.println(new ModifiedSecant(maxIterations, derivativeFunction, 0.56714329, 1, delta).findRoot().toString());
        System.out.println(new Secant(maxIterations, function, 0.56714329, 0, -1).findRoot().toString());
    }
}
