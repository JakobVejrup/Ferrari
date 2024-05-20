package com.logic;

import com.logic.math.LoanCalculator;

import junit.framework.TestCase;

public class LoanCalculatorTest extends TestCase{
    public void testAfdrag() {
        assertEquals(0d, LoanCalculator.Afdrag(0, 0, 0, 0));
        assertEquals(-197142.86, LoanCalculator.Afdrag(3000000, 1, 6, 12));



    }
}
