package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.City;
import com.model.entities.Customer;
import com.rki.bank.InterestRate;
import com.rki.rki.CreditRator;

public class RatesData implements Data {
    private ConnectionData db;
    public RatesData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        return null;
    } 

    @Override
    public Object read(Object parameter) {
        Agreement agreement = (Agreement)parameter;
        Agreement newAgreement = new Agreement();
        newAgreement.setDaysRate(InterestRate.i().todaysRate());
        newAgreement.setRki(CreditRator.i().rate(agreement.getCustomer().getCpr()));
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
