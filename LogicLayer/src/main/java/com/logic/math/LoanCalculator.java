package com.logic.math;

import java.sql.Date;
import java.util.Calendar;

import com.model.entities.Agreement;
import com.model.entities.DueDatePayment;
import com.rki.bank.InterestRate;
import com.rki.rki.Rating;

public class LoanCalculator {

    public static DueDatePayment[] LåneBeregner(Agreement agreement) {
        DueDatePayment [] Betalinger = new DueDatePayment[agreement.getfixedterms()];
        double rente = rente(agreement);
        double månedligBetaling = beregnmånedligBetaling(agreement.getVehicle().getPrice(), rente, agreement.getfixedterms());
        double totalBeløb = agreement.getfixedterms() * månedligBetaling;
        agreement.setEndprice (totalBeløb);
        Date firstDate = agreement.getStart();
        for (int i = 0; i < agreement.getfixedterms(); i++) {
            Calendar.getInstance().setTime(firstDate);
            Calendar.getInstance().add(Calendar.MONTH, 1);
            Date lastDate = new Date(Calendar.getInstance().getTime().getTime());

            double Restgæld = Restgæld(totalBeløb, månedligBetaling, (double) i, månedligBetaling);
            double Afdrag = Afdrag(agreement.getStartvalue(), rente, i, agreement.getfixedterms());
            double plus = månedligBetaling - Afdrag;
            double primo = Restgæld - plus; 

            Betalinger [i] = new DueDatePayment(agreement, i, firstDate, lastDate, plus, Afdrag, Restgæld, primo);
        }
        return Betalinger;
    }

    private static double rente(Agreement agreement) {
        double dagsRente = InterestRate.i().todaysRate();
        double RKIværdi = switch(agreement.getRKi()) {
            case Rating.A -> 1;
            case Rating.B -> 2;
            case Rating.C -> 3;
            default -> 3;
        };
        long timeValue = agreement.getStart().getTime()-agreement.getEnd().getTime();
        double tidsRente = timeValue > 94608000000L ? 1: 0;
        return RKIværdi + dagsRente + tidsRente;
    }
    private static double beregnmånedligBetaling(double låneBeløb, double rente, int antalTerminer) {
        double månedligRente = rente / 100 / 12;
        double månedligBetaling = (låneBeløb * månedligRente) / (1 - Math.pow (1 + månedligRente, - antalTerminer));
        return månedligBetaling;
    }
    private static double Restgæld(double startbeløb, double månedligRente, double terminerIalt, double n) {
        return startbeløb * (Math.pow((1+månedligRente),terminerIalt) - Math.pow((1 + månedligRente),n) / Math.pow((1+månedligRente),terminerIalt - 1));
    }
    private static double månedligAfdrag(double startbeløb, double månedligeRente, double n) {
        return startbeløb * (Math.pow(månedligeRente * (1 + månedligeRente),n) / Math.pow((1+månedligeRente),n-1));
    }
    private static double Afdrag(double startbeløb, double månedligRente, double n, double terminerIalt) {
        return startbeløb * (månedligRente - (månedligAfdrag(startbeløb, månedligRente, n) * (Math.pow(1+månedligRente,terminerIalt-1) / Math.pow(1+månedligRente,n-1))));
    }
}
    
// Afdrag=P*(r*(1+r)^n /((1+r)^n−1)

// P = lånebeløb 
// r = rente
// n = termin 

// Restgæld = P * ((1+r)^n - (1 + r)^n) / ((1+r)^n - 1)

// afdrag = P * (r - (R * (1+r)^t-1) / ((1+r)^n-1))

// P * (r * (1 + r)^n) / ((1+r)^n-1)