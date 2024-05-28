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
            array[i][0] = ""+invoices.get(i).getNumber();
            array[i][1] = invoices.get(i).getDatestart().toString();
            array[i][2] = invoices.get(i).getDateend().toString();
            array[i][3] = ""+invoices.get(i).getPlus();
            array[i][4] = ""+invoices.get(i).getMinus();
            array[i][5] = ""+invoices.get(i).getUltimovalue();
            array[i][6] = ""+invoices.get(i).getPrimoprice();
            array[i][7] = invoices.get(i).getDetails().toString();
            
        }
        CSVWriter writer = new CSVWriter(pathinvoices + Filename, HEADER, array);
        writer.write();
    }
    
    
    



    
    
}
