package com.logic.math;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.rki.bank.InterestRate;
import com.rki.rki.Rating;

public class LoanCalculator {

    public static Invoice[] låneBeregner(Agreement agreement) {
        Invoice [] Betalinger = new Invoice[agreement.getFixedTerms()];
        double rente = rente(agreement);
        double månedligBetaling = fastYdelse(agreement.getVehicle().getPrice() - agreement.getStartValue(), rente, agreement.getFixedTerms());
        int terminer = agreement.getFixedTerms();
        double totalBeløb = terminer * månedligBetaling;
        agreement.setEndPrice (totalBeløb);
        totalBeløb = agreement.getVehicle().getPrice() - agreement.getStartValue();
        Date firstDate = agreement.getStart();
        for (int i = 1; i <= terminer; i++) {
            LocalDate firstPlus = firstDate.toLocalDate();
            firstPlus = firstPlus.plus(1, ChronoUnit.MONTHS);
            Date lastDate = Date.valueOf(firstPlus);

            double Restgæld = restgæld(totalBeløb, månedligBetaling, rente, i -1);
            double Afdrag = afdrag(Restgæld, rente);
            Restgæld = restgæld(totalBeløb, månedligBetaling, rente, i );
            double plus = månedligBetaling - Afdrag;
            //double primo = Restgæld - plus;

            Betalinger [i - 1] = new Invoice(agreement, i, firstDate, lastDate, plus, Afdrag, totalBeløb, Restgæld, "");
            firstDate = Date.valueOf(firstPlus);
            totalBeløb = Restgæld;
        }
        return Betalinger;
    }

    public static double rente(Agreement agreement) {
        double dagsRente = agreement.getDaysRate();
        double RKIværdi = switch(agreement.getRki()) {
            case Rating.A -> 1;
            case Rating.B -> 2;
            case Rating.C -> 3;
            default -> 3;
        };
        LocalDate first = agreement.getStart().toLocalDate().plus(3, ChronoUnit.YEARS);
        double tidsRente = Date.valueOf(first).getTime() > agreement.getEnd().getTime() ? 1: 0;
        return ((RKIværdi + dagsRente + tidsRente) / 100)/12;

    }
    public static double fastYdelse(double låneBeløb, double rente, int antalTerminer) {
        return låneBeløb / ((1 - Math.pow (1 + rente, - antalTerminer)) / rente);
    }
    public static double restgæld(double startbeløb, double betaling, double rente, int n) {
        return (startbeløb * Math.pow((1 + rente), n)) - (betaling *((Math.pow((1 + rente), n) - 1) / rente));
    }
    public static double afdrag(double total, double rente) {
        return total * rente;
    }
}
