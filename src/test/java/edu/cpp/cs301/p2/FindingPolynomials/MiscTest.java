package edu.cpp.cs301.p2.FindingPolynomials;

import org.junit.Assert;
import org.junit.Test;

public class MiscTest {

    @Test
    public void Test1() {
        Assert.assertTrue(Misc.isNumber("-12.000000"));
        Assert.assertTrue(Misc.isNumber("-23.2"));
        Assert.assertTrue(Misc.isNumber("807684680000"));
        Assert.assertTrue(Misc.isNumber("807684.680000"));
        Assert.assertTrue(Misc.isNumber("0.05"));
    }

    @Test
    public void Test2() {
        Assert.assertFalse(Misc.isNumber("--12.000000"));
        Assert.assertFalse(Misc.isNumber("-A23.2"));
        Assert.assertFalse(Misc.isNumber("+807684680000"));
        Assert.assertFalse(Misc.isNumber("807684.680000.0"));
        Assert.assertFalse(Misc.isNumber("0.05E-2"));
    }


}
