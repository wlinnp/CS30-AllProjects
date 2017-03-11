package edu.cpp.cs301.p1;

import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;
import edu.cpp.cs301.p1.Functions.MathFunction;
import edu.cpp.cs301.p1.Functions.PolynomialFunction;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class PolynomialFunctionTest {

    @Test
    public void polynomialTest1() {
        MathFunction mathFunction = new PolynomialFunction(2, new double[] {3, 2, 1});
        System.out.println(mathFunction.toString());
        Assert.assertEquals(mathFunction.getResult(0), 1.00, 0.00);
        Assert.assertEquals(mathFunction.getResult(2.5), 24.75, 0.00);
    }

    @Test
    public void polynomialTest2() {
        MathFunction mathFunction = new PolynomialFunction(3, new double[] {-4, 3,  -2, 1});
        System.out.println(mathFunction.toString());
        Assert.assertEquals(mathFunction.getResult(0), 1.00, 0.00);
        Assert.assertEquals(mathFunction.getResult(-2.5), 87.25, 0.00);
    }

    @Test
    public void polynomialTest3() {
        MathFunction mathFunction = new PolynomialFunction(5, new double[] {-4, 3, 0, -2, 0, -1});
        System.out.println(mathFunction.toString());
        Assert.assertEquals(mathFunction.getResult(0), -1.00, 0.00);
        Assert.assertEquals(mathFunction.getResult(-3.2), 1635.27008, 0.000001);
    }

    @Test
    public void polynomialTest4() {
        MathDerivativeFunction mathFunction = new PolynomialFunction(2, new double[] {3, 2, 1});
        System.out.println(mathFunction.toString());

        Assert.assertEquals(mathFunction.getDerivativeResult(0), 2.00, 0.00);
        Assert.assertEquals(mathFunction.getDerivativeResult(2.5), 17.00, 0.00);
    }

    @Test
    public void polynomialTest5() {
        MathDerivativeFunction mathFunction = new PolynomialFunction(3, new double[] {-4, 3,  -2, 1});
        System.out.println(mathFunction.toString());

        Assert.assertEquals(mathFunction.getDerivativeResult(0), -2.00, 0.00);
        Assert.assertEquals(mathFunction.getDerivativeResult(-2.5), -92.00, 0.00);
    }

    @Test
    public void polynomialTest6() {
        MathDerivativeFunction mathFunction = new PolynomialFunction(5, new double[] {-4, 3, 0, -2, 0, -1});
        System.out.println(mathFunction.toString());

        Assert.assertEquals(mathFunction.getDerivativeResult(0), 0.00, 0.00);
        Assert.assertEquals(mathFunction.getDerivativeResult(-3.2), -2477.568, 0.001);
    }
}
