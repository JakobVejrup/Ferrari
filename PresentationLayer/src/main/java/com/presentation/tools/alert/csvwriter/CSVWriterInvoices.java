package com.presentation.tools.alert.csvwriter;

import java.io.FilenameFilter;
import java.util.List;

import com.model.entities.Invoice;

public class CSVWriterInvoices {
private String Filename;
private String pathinvoices;
private List < Invoice > invoices;
private static final String HEADER[] = { "NR", "Dato Start", "Dato slut", "Plus", "Minus", "UltimoValue", "PrimoPrice",
        "Details" };

    public CSVWriterInvoices(String filename, String pathinvoices, List < Invoice > invoices) {
        this.Filename = filename;
        this.pathinvoices = pathinvoices;
        this.invoices = invoices;
    }

    public void WriteCSV(){
        int rows = invoices.size();
        int columns = 8;
        String [][] array = new String[rows][columns];
        
        for (int i = 0; i < rows; i++ ){
            array[i][0] = ""+invoices.get(i+1).getNumber();
            array[i][1] = invoices.get(i+1).getDatestart().toString();
            array[i][2] = invoices.get(i+1).getDateend().toString();
            array[i][3] = ""+invoices.get(i+1).getPlus();
            array[i][4] = ""+invoices.get(i+1).getMinus();
            array[i][5] = ""+invoices.get(i+1).getUltimovalue();
            array[i][6] = ""+invoices.get(i+1).getPrimoprice();
            array[i][7] = invoices.get(i+1).getDetails().toString();
            
        }
        CSVWriter writer = new CSVWriter(pathinvoices + Filename, HEADER, array);
        writer.write();
    }
    
    
    



    
    
}
