package com.data;

import com.model.threads.Action;
import com.model.threads.Function;
import com.model.threads.ObjectWithTimer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLData {
    private ObjectWithTimer connectionTimer;
    private static final String CONNECTION_STRING = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=Ferrari;" +
            "integratedSecurity=true;" +
            "trustServerCertificate=true;";
    public SQLData() {
        //connectionTimer will check every 20 seconds if its been run in the meanwhile, if it hasn't it will disconnect the sql connection
        connectionTimer = new ObjectWithTimer(new Function() {
            @Override
            public Object function() {
                try {
                    return DriverManager.getConnection(CONNECTION_STRING);
                } catch (Exception e) {
                    return null;
                }
            }
        }, 20, new Action() {
            @Override
            public void action() {
                try {
                    ((Connection)connectionTimer.getValue()).close();
                } catch (Exception e) {
                }}
        });
        //loadDriver();
    }
    public CallableStatement makeCall(String spCall) {
        try {
            return ((Connection)connectionTimer.getValue()).prepareCall(spCall);
        }
        catch(Exception e) {
            return null;
        }
    }

}
