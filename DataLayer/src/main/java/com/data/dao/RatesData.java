package com.data.dao;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.rki.bank.InterestRate;
import com.rki.rki.CreditRator;

public class RatesData implements Data {
// Klasse for dataoperation CRUD på rate
    private ConnectionData db;
// Konstruktør som har db som parameter  
    public RatesData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        return null;
    } 

    @Override
    public Object read(Object parameter) {
// Caster parameteren til agreement, som er et objekt
        Agreement agreement = (Agreement)parameter;
// Opretter nyt agreement objekt
        Agreement newAgreement = new Agreement();
// Sætter dagsrente i det nye agreement objekt
        newAgreement.setDaysRate(InterestRate.i().todaysRate());
// Henter kreditvurdering i det nye agreement objekt
        newAgreement.setRki(CreditRator.i().rate(agreement.getCustomer().getCpr()));
// returner den nye agreement
        return newAgreement;
    }

    @Override
    public Object readAll(Object parameter) {
        return null;

    }

    @Override
    public Object update(Object parameter) {
        return null;

    }

    @Override
    public boolean delete(Object parameter) {
        return false;
    }
}
