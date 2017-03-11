package edu.cpp.cs301.p1;

import edu.cpp.cs301.p1.Functions.CustomExpFunction;
import edu.cpp.cs301.p1.Functions.MathDerivativeFunction;
import edu.cpp.cs301.p1.Functions.MathFunction;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author waiphyo
 *         1/28/17.
 */
public class CustomExpFunctionTest {
    @Test
    public void customExpFunctionTest1() {
        MathFunction customExpFunction = new CustomExpFunction();
        System.out.println(customExpFunction.toString());
        Assert.assertEquals(customExpFunction.getResult(0), 1, 0);
        Assert.assertEquals(customExpFunction.getResult(2.3), -2.19974, 0.00001);
        Assert.assertEquals(customExpFunction.getResult(-2.3), 12.27418, 0.00001);
        Assert.assertEquals(customExpFunction.getResult(Math.PI), -3.09838, 0.00001);
    }

    @Test
    public void customExpFunctionTest2() {
        MathDerivativeFunction customExpFunction = new CustomExpFunction();
        System.out.println(customExpFunction.toString());
        Assert.assertEquals(customExpFunction.getDerivativeResult(0), -2, 0);
        Assert.assertEquals(customExpFunction.getDerivativeResult(2.3), -1.10026, 0.00001);
        Assert.assertEquals(customExpFunction.getDerivativeResult(-2.3), -10.97418, 0.00001);
        Assert.assertEquals(customExpFunction.getDerivativeResult(Math.PI), -1.04321, 0.00001);
    }
}
