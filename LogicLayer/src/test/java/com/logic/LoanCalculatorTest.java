package com.logic;

import com.logic.math.LoanCalculator;

import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;
import junit.framework.TestCase;

import java.sql.Date;

public class LoanCalculatorTest extends TestCase {

    public void testLåneBeregner() {
        Agreement agreement = new Agreement();
        agreement.setVehicle(new Vehicle(0,"", 3000000d));
        agreement.setStart(new Date(2000, 1, 1));
        agreement.setEnd(new Date(2005, 1, 1));
        agreement.setRki(Rating.C);
        agreement.setFixedTerms(3);
        //agreement.setDaysRate(0);
        //0,0025
        Invoice[] tests = {new Invoice(agreement, 1, new Date(2000, 1, 1), new Date(2000, 2, 1), 997504.16, 7500, 3000000, 2002495.84, ""),
                           new Invoice(agreement, 2, new Date(2000, 2, 1), new Date(2000, 3, 1), 999997.92, 5006.24, 2002495.84, 1002497.92, ""),
                           new Invoice(agreement, 3, new Date(2000, 3, 1), new Date(2000, 4, 1), 1002497.92, 2506.24, 1002497.92, 0, "")};
        Invoice[] loans = LoanCalculator.låneBeregner(agreement);
        boolean one = tests[0].equals(loans[0]);
        boolean two = tests[1].equals(loans[1]);
        boolean three = tests[2].equals(loans[2]);

        assertTrue(one && two && three);
    }

    public void testAfdrag() {
        assertEquals(27634.54, LoanCalculator.afdrag(2763453.63, 0.01), 0.1);
    }
    public void testFastYdelse() {
        assertEquals(266546.36 , LoanCalculator.fastYdelse(3000000, 0.01, 12), 0.1);
    }

    public void testRestGæld() {
        assertEquals(2524541.80 , LoanCalculator.restgæld(3000000, 266546.36, 0.01, 2), 0.1);
        assertEquals(2003292.75 , LoanCalculator.restgæld(3000000, 1006607.25, 0.0033, 1), 0.1);
    }
    public void testRente() {
        Agreement agreement = new Agreement();
        agreement.setVehicle(new Vehicle(0,"", 3000000d));
        agreement.setStart(new Date(2000, 1, 1));
        agreement.setEnd(new Date(2005, 1, 1));
        agreement.setRki(Rating.C);
        assertEquals(0.0025 , LoanCalculator.rente(agreement));
    }
}
