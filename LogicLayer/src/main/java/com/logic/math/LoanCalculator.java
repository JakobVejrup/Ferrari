package com.logic.math;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.rki.bank.InterestRate;
import com.rki.rki.Rating;
//jakob
public class LoanCalculator {

    public static Invoice[] låneBeregner(Agreement agreement) {
        Invoice [] Betalinger = new Invoice[agreement.getFixedTerms()];
        double rente = renteProcent(agreement);
        double totalBeløb = agreement.getVehicle().getPrice() - agreement.getStartValue();
        double månedligBetaling = fastYdelse(totalBeløb, rente, agreement.getFixedTerms());
        int terminer = agreement.getFixedTerms();
        agreement.setEndPrice (terminer * månedligBetaling);
        Date firstDate = agreement.getStart();
        double primoGæld = totalBeløb;
        for (int i = 1; i <= terminer; i++) {
            LocalDate firstPlus = firstDate.toLocalDate();
            firstPlus = firstPlus.plus(1, ChronoUnit.MONTHS);
            Date lastDate = Date.valueOf(firstPlus);
            double renten = primoGæld * rente;
            if(i == terminer)
                månedligBetaling = primoGæld + renten;
            double afdrag = månedligBetaling - renten;
            double ultimoGæld = primoGæld - afdrag;
            Betalinger [i - 1] = new Invoice(agreement, i, firstDate, lastDate, afdrag, renten, ultimoGæld, primoGæld, månedligBetaling, "");
            firstDate = Date.valueOf(firstPlus);
            primoGæld = ultimoGæld;
        }
        return Betalinger;
    }
    public static double renteProcent(Agreement agreement) {
        double dagsRente = agreement.getDaysRate();
        double RKIværdi = switch(agreement.getRki()) {
            case Rating.A -> 1;
            case Rating.B -> 2;
            case Rating.C -> 3;
            default -> 3;
        };
        LocalDate first = agreement.getStart().toLocalDate().plus(3, ChronoUnit.YEARS);
        double amountRente = agreement.getStartValue() >= agreement.getVehicle().getPrice() / 2  ? 0 : 1;
        double tidsRente = Date.valueOf(first).getTime() < agreement.getEnd().getTime() ? 1: 0;
        agreement.setTotalRate(RKIværdi + dagsRente + tidsRente + amountRente);
        return ((RKIværdi + dagsRente + tidsRente + amountRente) / 100)/12;

    }
    public static double fastYdelse(double låneBeløb, double rente, int antalTerminer) {
        return (låneBeløb * rente) / (1- Math.pow (1 + rente, -antalTerminer));
    }
}
