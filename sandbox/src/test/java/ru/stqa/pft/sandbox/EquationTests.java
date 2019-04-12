package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test0() {
    Equation eq = new Equation(1, 1, 1);
    Assert.assertEquals(eq.rootNumber(), 0);
  }

  @Test
  public void test1() {
    Equation eq = new Equation(1, 2, 1);
    Assert.assertEquals(eq.rootNumber(), 1);
  }

  @Test
  public void test2() {
    Equation eq = new Equation(1, 5, 6);
    Assert.assertEquals(eq.rootNumber(), 2);
  }

  @Test
  public void testLinear() {
    Equation eq = new Equation(0, 1, 1);
    Assert.assertEquals(eq.rootNumber(), 1);
  }

  @Test
  public void testConstant() {
    Equation eq = new Equation(0, 0, 1);
    Assert.assertEquals(eq.rootNumber(), 0);
  }

  @Test
  public void testZero() {
    Equation eq = new Equation(0, 0, 0);
    Assert.assertEquals(eq.rootNumber(), -1);
  }


}
