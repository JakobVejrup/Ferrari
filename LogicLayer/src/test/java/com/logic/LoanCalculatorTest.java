package com.logic;

import com.logic.math.LoanCalculator;

import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;
import junit.framework.TestCase;

import java.sql.Date;
//anders
//all tests ran true
public class LoanCalculatorTest extends TestCase {
    //testing if the arrays are the same, test values came from a website
    @SuppressWarnings("deprecation")
    public void testLåneBeregner() {
        Agreement agreement = new Agreement();
        agreement.setVehicle(new Vehicle(0,"", 3000000d));
        agreement.setStart(new Date(2000, 1, 1));
        agreement.setEnd(new Date(2005, 1, 1));
        agreement.setRki(Rating.C);
        agreement.setFixedTerms(3);
        //agreement.setDaysRate(0);
        //0,0025
        Invoice[] tests = { new Invoice(agreement, 1, new Date(2000, 1, 1), new Date(2000, 2, 1), 997504.16, 7500,2002495.84, 3000000, 1005004.16, ""),
                           new Invoice(agreement, 2, new Date(2000, 2, 1), new Date(2000, 3, 1), 999997.92, 5006.24, 1002497.92, 2002495.84, 1005004.16, ""),
                           new Invoice(agreement, 3, new Date(2000, 3, 1), new Date(2000, 4, 1), 1002497.92, 2506.24, 0, 1002497.92, 1005004.16, "")};
        Invoice[] loans = LoanCalculator.låneBeregner(agreement);
        boolean one = tests[0].equals(loans[0]);
        boolean two = tests[1].equals(loans[1]);
        boolean three = tests[2].equals(loans[2]);

        assertTrue(one && two && three);
    }
    //testing if the values for monthly payments are the same
    public void testFastYdelse() {
        assertEquals(266546.36 , LoanCalculator.fastYdelse(3000000, 0.01, 12), 0.1);
    }
    //testing if the rate for monthly payment are the same
    @SuppressWarnings("deprecation")
    public void testRente() {
        Agreement agreement = new Agreement();
        agreement.setVehicle(new Vehicle(0,"", 3000000d));
        agreement.setStart(new Date(2000, 1, 1));
        agreement.setEnd(new Date(2005, 1, 1));
        agreement.setRki(Rating.C);
        // (3+1+1)/100/12 = 0.0041666666666667
        double real = LoanCalculator.renteProcent(agreement);
        assertEquals(0.0041666666666667, real, 0.0001);
    }
}
